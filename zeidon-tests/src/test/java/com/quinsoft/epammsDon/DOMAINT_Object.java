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

/**
   @author QuinSoft
**/

public class DOMAINT_Object extends VmlObjectOperations
{
   public DOMAINT_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
//:ActivateDomainList( VIEW DOMAINTLST BASED ON LOD DOMAINT,
//:                    VIEW zAnyView )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
public int 
oDOMAINT_ActivateDomainList( zVIEW    DOMAINTLST,
                             View     zAnyView )
{
   zVIEW    DOMAINT = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:ACTIVATE DOMAINTLST RootOnlyMultiple
   RESULT = ActivateObjectInstance( DOMAINTLST, "DOMAINT", zAnyView, 0, zACTIVATE_ROOTONLY_MULTIPLE );

   //:GET VIEW DOMAINT NAMED "DOMAINT"
   RESULT = GetViewByName( DOMAINT, "DOMAINT", zAnyView, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:IF DOMAINTLST.Domain EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( DOMAINTLST, "Domain" );
      if ( lTempInteger_0 == 0 )
      { 
         //:SET CURSOR FIRST DOMAINTLST.Domain WHERE
         //:   DOMAINT.Domain.Name = DOMAINTLST.Domain.Name
         RESULT = SetCursorFirstEntity( DOMAINTLST, "Domain", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( DOMAINT, "Domain", "Name", DOMAINTLST, "Domain", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( DOMAINTLST, "Domain", "" );
            } 

         } 

      } 

      //:END
      //:DropView ( DOMAINT )
      DropView( DOMAINT );
   } 

   //:END

   //:NAME VIEW DOMAINTLST "DOMAINTLST"
   SetNameForView( DOMAINTLST, "DOMAINTLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


private int 
oDOMAINT_fnLocalBuildQual_25( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_22( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_23( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_24( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_19( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_20( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_21( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_16( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_17( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_18( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_13( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_14( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_15( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_10( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_11( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_12( View     vSubtask,
                              zVIEW    vQualObject,
                              String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_7( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_8( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_9( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_4( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_5( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_6( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_1( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_2( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_3( View     vSubtask,
                             zVIEW    vQualObject,
                             String   szDomainName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szDomainName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
oDOMAINT_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "ActivityStatus" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
public int 
oDOMAINT_PreloadSystemDomainsR( zVIEW    DOMAINTLST,
                                View     AnyReferenceView,
                                String   InstallApp )
{
   int      RESULT = 0;

   //:PreloadSystemDomainsR( VIEW DOMAINTLST  BASED ON LOD DOMAINT,
   //:                   VIEW AnyReferenceView,
   //:                   STRING ( 32 ) InstallApp )
   //:ACTIVATE DOMAINTLST RootOnlyMultiple
   RESULT = ActivateObjectInstance( DOMAINTLST, "DOMAINT", AnyReferenceView, 0, zACTIVATE_ROOTONLY_MULTIPLE );

   //:// ActivityStatus
   //:LoadActivityStatus( DOMAINTLST  )
   oDOMAINT_LoadActivityStatus( DOMAINTLST );
   //:LoadActivityType( DOMAINTLST   )
   oDOMAINT_LoadActivityType( DOMAINTLST );
   //:LoadCommPlanType( DOMAINTLST   )
   oDOMAINT_LoadCommPlanType( DOMAINTLST );
   //:LoadContactActType( DOMAINTLST   )
   oDOMAINT_LoadContactActType( DOMAINTLST );
   //:LoadEventCode( DOMAINTLST  )
   oDOMAINT_LoadEventCode( DOMAINTLST );
   //:LoadFacultyStatus( DOMAINTLST  )
   oDOMAINT_LoadFacultyStatus( DOMAINTLST );
   //:LoadFacultyType( DOMAINTLST  )
   oDOMAINT_LoadFacultyType( DOMAINTLST );
   //:LoadFundRestrictions( DOMAINTLST  )
   oDOMAINT_LoadFundRestrictions( DOMAINTLST );
   //:LoadProspectStatus( DOMAINTLST  )
   oDOMAINT_LoadProspectStatus( DOMAINTLST );
   //:LoadProspectType( DOMAINTLST  )
   oDOMAINT_LoadProspectType( DOMAINTLST );
   //:LoadStaffStatus( DOMAINTLST  )
   oDOMAINT_LoadStaffStatus( DOMAINTLST );
   //:LoadStaffType( DOMAINTLST  )
   oDOMAINT_LoadStaffType( DOMAINTLST );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
oDOMAINT_PreloadSystemDomainsO( zVIEW    DOMAINTLST,
                                View     AnyReferenceView,
                                String   InstallApp )
{
   int      RESULT = 0;

   //:PreloadSystemDomainsO( VIEW DOMAINTLST  BASED ON LOD DOMAINT,
   //:                   VIEW AnyReferenceView,
   //:                   STRING ( 32 ) InstallApp )
   //:ACTIVATE DOMAINTLST RootOnlyMultiple
   RESULT = ActivateObjectInstance( DOMAINTLST, "DOMAINT", AnyReferenceView, 0, zACTIVATE_ROOTONLY_MULTIPLE );

   //:LoadDenomination( DOMAINTLST   )
   oDOMAINT_LoadDenomination( DOMAINTLST );
   //:LoadChurchRelatedRole( DOMAINTLST   )
   oDOMAINT_LoadChurchRelatedRole( DOMAINTLST );
   //:LoadDegreeEarned( DOMAINTLST   )
   oDOMAINT_LoadDegreeEarned( DOMAINTLST );
   //:LoadDistrictCode( DOMAINTLST   )
   oDOMAINT_LoadDistrictCode( DOMAINTLST );
   //:LoadExamType( DOMAINTLST   )
   oDOMAINT_LoadExamType( DOMAINTLST );
   //:LoadIndustry( DOMAINTLST   )
   oDOMAINT_LoadIndustry( DOMAINTLST );
   //:LoadInfluencerType( DOMAINTLST   )
   oDOMAINT_LoadInfluencerType( DOMAINTLST );
   //:LoadInterestType( DOMAINTLST   )
   oDOMAINT_LoadInterestType( DOMAINTLST );
   //:LoadOrgRelatedRole( DOMAINTLST   )
   oDOMAINT_LoadOrgRelatedRole( DOMAINTLST );
   //:LoadParentGuardianRole( DOMAINTLST   )
   oDOMAINT_LoadParentGuardianRole( DOMAINTLST );
   //:LoadSchoolCategory( DOMAINTLST   )
   oDOMAINT_LoadSchoolCategory( DOMAINTLST );
   //:LoadSchoolRelatedRole( DOMAINTLST   )
   oDOMAINT_LoadSchoolRelatedRole( DOMAINTLST );
   //:LoadCountry( DOMAINTLST   )
   oDOMAINT_LoadCountry( DOMAINTLST );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadActivityStatus( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private void 
oDOMAINT_LoadActivityStatus( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = "ActivityStatus"
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", "ActivityStatus", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = "ActivityStatus"
      SetAttributeFromString( DOMAINT, "Domain", "Name", "ActivityStatus" );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = "ActivityStatus"
      oDOMAINT_fnLocalBuildQual_0( DOMAINTLST, vTempViewVar_0 );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Scheduled"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Scheduled" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "0"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "0", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Completed"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Completed" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "0"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "0" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return;
// END
} 


//:LOCAL OPERATION
//:LoadActivityType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadActivityType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ActivityType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ActivityType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}

   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_1( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "ContactList"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "ContactList" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Meeting"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Meeting" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Appointment"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Appointment" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "PhoneCall"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "PhoneCall" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "5"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "5", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Fax"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Fax" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "5"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "5" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "6"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "6", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "EMail"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "EMail" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "6"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "6" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "7"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "7", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mailing"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mailing" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "7"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "7" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "8"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "8", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Todo"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Todo" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "8"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "8" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );



   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadDenomination( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadDenomination( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ChurchDenomination"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ChurchDenomination", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_2( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Church of the Nazarene"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Church of the Nazarene" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Advent Christian Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Advent Christian Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Anglican Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Anglican Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Assembly of God Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Assembly of God Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "5"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "5", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Baptist Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Baptist Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "5"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "5" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "6"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "6", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Church of the Brethren"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Church of the Brethren" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "6"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "6" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "7"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "7", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Catholic Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Catholic Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "7"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "7" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "8"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "8", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Community Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Community Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "8"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "8" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "9"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "9", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Christian Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Christian Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "9"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "9" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "10"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "10", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Christian Missionary Alliance"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Christian Missionary Alliance" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "10"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "10" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "11"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "11", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Congregational Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Congregational Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "11"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "11" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "12"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "12", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Episcopal Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Episcopal Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "12"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "12" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "13"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "13", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Free Methodist"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Free Methodist" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "13"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "13" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "14"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "14", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Church of God"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Church of God" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "14"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "14" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "15"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "15", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Greek Evangelical Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Greek Evangelical Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "15"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "15" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "16"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "16", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Greek Orthodox Church"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Greek Orthodox Church" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "16"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "16" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "17"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "17", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Southern Baptist"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Southern Baptist" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "17"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "17" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "18"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "18", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Roman Catholic"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Roman Catholic" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "18"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "18" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "19"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "19", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Independent"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Independent" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "19"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "19" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "20"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "20", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Presbyterian"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Presbyterian" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "20"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "20" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadChurchRelatedRole( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadChurchRelatedRole( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ChurchRelatedRole"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ChurchRelatedRole", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_3( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Senior Pastor"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Senior Pastor" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Youth Pastor"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Youth Pastor" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:Load( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_Load( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = ""
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_24( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = ""
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadCommPlanType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadCommPlanType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "CommunicationPlanType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "CommunicationPlanType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_4( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Admissions"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Admissions" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Development"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Development" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Alumni"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Alumni" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadContactActType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadContactActType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ContactActivityType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ContactActivityType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_5( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "ContactList"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "ContactList" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Meeting"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Meeting" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Appointment"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Appointment" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "PhoneCall"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "PhoneCall" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "5"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "5", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Fax"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Fax" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "5"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "5" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "6"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "6", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "EMail"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "EMail" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "6"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "6" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "7"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "7", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mailing"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mailing" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "7"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "7" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadDegreeEarned( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadDegreeEarned( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "DegreeEarned"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "DegreeEarned", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_6( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "AA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Associate in Arts"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Associate in Arts" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "AA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AA" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "AB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bachelor of Arts"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bachelor of Arts" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "AB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AB" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "AS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Associate in Science"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Associate in Science" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "AS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AS" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "BA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bachelor of Arts"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bachelor of Arts" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "BA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BA" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "BAS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BAS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bachelor of Arts/Bachelor of Science"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bachelor of Arts/Bachelor of Science" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "BAS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BAS" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "BMU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BMU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bachelor of Music"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bachelor of Music" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "BAM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BAM" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "BS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bachelor of Science"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bachelor of Science" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "BAM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BAM" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "BSBA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BSBA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bachelor of Science/ Business Administration"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bachelor of Science/ Business Administration" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "BSBA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BSBA" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "MA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Master of Arts"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Master of Arts" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "MA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MA" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "MED"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MED", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Master of Education"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Master of Education" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "MED"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MED" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadDistrictCode( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadDistrictCode( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "DistrictCode"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "DistrictCode", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_7( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "01"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "01", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "District 01"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "District 01" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "01"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "01" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadEventCode( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadEventCode( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "EventCode"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "EventCode", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_8( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "College Faire"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "College Faire" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Print Advertisment"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Print Advertisment" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "TV/Radio Advertisment"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "TV/Radio Advertisment" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mailing"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mailing" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "5"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "5", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Contact List"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Contact List" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "5"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "5" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "6"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "6", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Other"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Other" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "6"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "6" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadExamType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadExamType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ExamType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ExamType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_9( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "S"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "S", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "SAT"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "SAT" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "S"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "S" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "A"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "A", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "ACT"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "ACT" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "A"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "A" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadFacultyStatus( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadFacultyStatus( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "FacultyStatus"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "FacultyStatus", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_10( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Active"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Active" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "0"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "0", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Inactive"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Inactive" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "0"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "0" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadFacultyType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadFacultyType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "FacultyType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "FacultyType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_11( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Professor"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Professor" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Assistant Professor"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Assistant Professor" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Associate Professor"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Associate Professor" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Lecturer"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Lecturer" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "5"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "5", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Adjunct Instructor"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Adjunct Instructor" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "5"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "5" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadFundRestrictions( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadFundRestrictions( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "FundRestrictions"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "FundRestrictions", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_12( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "R"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "R", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Restricted"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Restricted" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "R"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "R" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "U"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "U", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Unrestricted"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Unrestricted" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "U"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "U" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "C"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "C", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Capital"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Capital" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "C"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "C" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "O"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "O", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Other"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Other" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "O"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "O" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadIndustry( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadIndustry( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "Industry"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "Industry", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_13( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "General"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "General" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadInfluencerType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadInfluencerType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "InfluencerType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "InfluencerType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_14( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "General"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "General" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadInterestType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadInterestType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "InterestType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "InterestType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_15( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sports"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sports" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadOrgRelatedRole( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadOrgRelatedRole( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "OrgRelatedRole"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "OrgRelatedRole", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_16( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Owner"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Owner" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Officer"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Officer" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Other"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Other" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadParentGuardianRole( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadParentGuardianRole( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ParentGuardianRole"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ParentGuardianRole", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_17( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Father"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Father" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mother"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mother" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Guardian"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Guardian" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Grandparent"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Grandparent" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadProspectStatus( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadProspectStatus( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ProspectStatus"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ProspectStatus", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_18( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Active"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Active" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "0"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "0", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Inactive"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Inactive" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "0"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "0" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadProspectType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadProspectType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "ProspectType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "ProspectType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_19( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Inquiry"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Inquiry" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Lead"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Lead" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "3"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "3", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Prospect"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Prospect" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "3"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "3" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Applied"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Applied" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "5"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "5", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Accepted"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Accepted" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "5"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "5" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadSchoolCategory( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadSchoolCategory( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "SchoolCategory"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "SchoolCategory", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_20( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "COLCOMM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "COLCOMM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "College - Community"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "College - Community" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "COLCOMM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "COLCOMM" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "COLPRIV2"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "COLPRIV2", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "College - Two Year Private"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "College - Two Year Private" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "COLPRIV2"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "COLPRIV2" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "COLPRIV4"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "COLPRIV4", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "College - Four Year Private"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "College - Four Year Private" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "COLPRIV4"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "COLPRIV4" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "COLSTATE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "COLSTATE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "College - State"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "College - State" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "COLSTATE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "COLSTATE" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "PRIMPRIV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PRIMPRIV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Primary Private (K-8)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Primary Private (K-8)" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "PRIMPRIV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PRIMPRIV" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "PRIMPUB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PRIMPUB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Primary Public (K-8)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Primary Public (K-8)" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "PRIMPUB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PRIMPUB" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "SECONPRIV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SECONPRIV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Secondary Private (9-12)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Secondary Private (9-12)" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "SECONPRIV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SECONPRIV" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "SECONPUB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SECONPUB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Secondary Public (9-12)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Secondary Public (9-12)" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "SECONPUB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SECONPUB" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadSchoolRelatedRole( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadSchoolRelatedRole( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "SchoolRelatedRole"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "SchoolRelatedRole", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_21( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Principal"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Principal" );
   } 

   //:END
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );


   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadStaffStatus( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadStaffStatus( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "StaffStatus"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "StaffStatus", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_22( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Active"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Active" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "1"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "1" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "0"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "0", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Inactive"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Inactive" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "0"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "0" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadStaffType( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadStaffType( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:szDomainName = "StaffType"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "StaffType", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_23( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "F"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "F", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "FullTime"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "FullTime" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "F"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "F" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "P"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "P", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Parttime"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Parttime" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = "P"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "P" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );

   //:COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadCountry( VIEW DOMAINTLST BASED ON LOD DOMAINT )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
private int 
oDOMAINT_LoadCountry( View     DOMAINTLST )
{
   zVIEW    DOMAINT = new zVIEW( );
   //:STRING (  30  ) szDomainName
   String   szDomainName = null;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:// because country is so large, we will delete the list and re-add it
   //:szDomainName = "Country"
    {StringBuilder sb_szDomainName;
   if ( szDomainName == null )
      sb_szDomainName = new StringBuilder( 32 );
   else
      sb_szDomainName = new StringBuilder( szDomainName );
      ZeidonStringCopy( sb_szDomainName, 1, 0, "Country", 1, 0, 31 );
   szDomainName = sb_szDomainName.toString( );}
   //:// Go through the domain list Manually and activate each domain.
   //:// If the system required values are not there, insert and save them
   //:// load ... First
   //:SET CURSOR FIRST DOMAINTLST.Domain
   //:   WHERE DOMAINTLST.Domain.Name = szDomainName
   RESULT = SetCursorFirstEntityByString( DOMAINTLST, "Domain", "Name", szDomainName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:ACTIVATE  DOMAINT EMPTY
      RESULT = ActivateEmptyObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, zSINGLE );
      //:CREATE ENTITY DOMAINT.Domain
      RESULT = CreateEntity( DOMAINT, "Domain", zPOS_AFTER );
      //:DOMAINT.Domain.Name = szDomainName
      SetAttributeFromString( DOMAINT, "Domain", "Name", szDomainName );
      //:DOMAINT.Domain.SystemRequired = "Y"
      SetAttributeFromString( DOMAINT, "Domain", "SystemRequired", "Y" );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE  DOMAINT
      //:   WHERE DOMAINT.Domain.Name = szDomainName
      oDOMAINT_fnLocalBuildQual_25( DOMAINTLST, vTempViewVar_0, szDomainName );
      RESULT = ActivateObjectInstance( DOMAINT, "DOMAINT", DOMAINTLST, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
   } 

   //:END

   //:SET CURSOR FIRST DOMAINT.DomainValue
   //:   WHERE DOMAINT.DomainValue.InternalStringValue = "1"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "1", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = ""
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalIntegerValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalIntegerValue", "" );
   //:DOMAINT.DomainValue.InternalStringValue = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "" );
   //:DOMAINT.DomainValue.WorkDecimal = ""
   SetAttributeFromString( DOMAINT, "DomainValue", "WorkDecimal", "" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Antigua"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Antigua" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AF"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AF", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Afghanistan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Afghanistan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AF"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AF" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Algeria"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Algeria" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Albania"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Albania" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Andorra"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Andorra" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Angola"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Angola" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "American Samoa"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "American Samoa" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Argentina"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Argentina" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Australia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Australia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ashmore And Cartier Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ashmore And Cartier Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Austria"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Austria" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "AY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "AY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Antarctica"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Antarctica" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "AY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "AY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bahrein Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bahrein Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Barbados"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Barbados" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BB" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Botswana"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Botswana" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BD"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BD", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bermuda"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bermuda" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BD"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BD" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Belgium"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Belgium" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BF"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BF", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bahamas"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bahamas" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BF"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BF" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "British Honduras"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "British Honduras" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bolivia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bolivia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Burma"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Burma" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BM" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bangladesh"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bangladesh" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "British Solomon Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "British Solomon Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "US Caribbean Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "US Caribbean Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Brazil"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Brazil" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bhutan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bhutan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bulgaria"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bulgaria" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Bouvet Island"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Bouvet Island" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BV" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BX"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BX", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Brunei"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Brunei" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BX"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BX" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Burundi"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Burundi" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "BZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "BZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Belize"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Belize" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "BZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "BZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Canada"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Canada" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cambodia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cambodia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CB" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "China, Mainland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "China, Mainland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CD"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CD", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Chad"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Chad" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CD"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CD" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ceylon"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ceylon" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CF"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CF", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Congo (Brazza Ville)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Congo (Brazza Ville)" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CF"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CF" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Congo (Kinshasa)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Congo (Kinshasa)" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "People's Republic of China"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "People's Republic of China" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Chile"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Chile" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CJ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CJ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cayman Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cayman Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CJ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CJ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cocos Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cocos Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Central/Southern Line Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Central/Southern Line Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cameroon"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cameroon" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CM" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Comoro Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Comoro Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Colombia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Colombia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Costa Rica"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Costa Rica" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Central African Republic"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Central African Republic" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cuba"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cuba" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cape Verde"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cape Verde" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CV" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CW"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CW", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cook Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cook Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CW"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CW" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Cyprus"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Cyprus" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "CZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "CZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Czechoslovakia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Czechoslovakia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "CZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "CZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "DA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "DA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Denmark"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Denmark" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "DA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "DA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "DM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "DM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Dahomey"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Dahomey" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "DM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "DM" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "DO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "DO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Dominica"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Dominica" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "DO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "DO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "DR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "DR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Dominican Republic"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Dominican Republic" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "DR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "DR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "EC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "EC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ecuador"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ecuador" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "EC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "EC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "EG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "EG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "United Arab Rep. (Egypt)"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "United Arab Rep. (Egypt)" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "EG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "EG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "EK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "EK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Equatorial Guinea"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Equatorial Guinea" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "EK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "EK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "EN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "EN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "England"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "England" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "EN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "EN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "EQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "EQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Canton And Enderbury Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Canton And Enderbury Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "EQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "EQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ES"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ES", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "El Salvador"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "El Salvador" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ES"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ES" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ET"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ET", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ethiopia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ethiopia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ET"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ET" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Falkland Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Falkland Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "French Guiana"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "French Guiana" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Finland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Finland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FJ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FJ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Fiji"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Fiji" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FJ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FJ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Faeroe Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Faeroe Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "French Polynesia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "French Polynesia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "France"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "France" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "French So./Antartic Lands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "French So./Antartic Lands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "FT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "FT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Afars/Issas"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Afars/Issas" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "FT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "FT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Gambia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Gambia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Gabon"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Gabon" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GB" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Germany, Soviet Zone Of"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Germany, Soviet Zone Of" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Germany"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Germany" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ghana"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ghana" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Gibraltar"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Gibraltar" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GJ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GJ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Grenada"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Grenada" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GJ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GJ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Greenland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Greenland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Gilbert/Ellice Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Gilbert/Ellice Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Guadeloupe"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Guadeloupe" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Guam"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Guam" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Greece"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Greece" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Guatemala"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Guatemala" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Guinea"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Guinea" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Guyana"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Guyana" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "GZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "GZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Gaza Strip"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Gaza Strip" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "GZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "GZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "HA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "HA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Haiti"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Haiti" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "HA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "HA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "HK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "HK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Hong Kong"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Hong Kong" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "HK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "HK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "HM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "HM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Heard/McDonald Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Heard/McDonald Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "HM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "HM" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "HO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "HO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Honduras"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Honduras" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "HO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "HO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "HU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "HU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Hungary"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Hungary" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "HU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "HU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Iceland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Iceland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ID"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ID", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Indonesia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Indonesia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ID"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ID" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ireland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ireland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "India"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "India" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "British Indian Ocean Terr"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "British Indian Ocean Terr" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Iran"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Iran" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Israel"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Israel" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Italy"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Italy" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Israel - Syria DMZ"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Israel - Syria DMZ" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Ivory Coast"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Ivory Coast" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IV" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Iraq - Saudia Arabia DMZ"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Iraq - Saudia Arabia DMZ" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "IZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "IZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Iraq"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Iraq" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "IZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "IZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "JA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "JA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Japan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Japan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "JA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "JA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "JM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "JM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Jamaica"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Jamaica" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "JM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "JM" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "JN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "JN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Jan Mayen"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Jan Mayen" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "JN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "JN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "JO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "JO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Jordan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Jordan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "JO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "JO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "JQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "JQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Johnston Atoll"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Johnston Atoll" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "JQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "JQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "KE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "KE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Kenya"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Kenya" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "KE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "KE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "KN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "KN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Korea, North"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Korea, North" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "KN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "KN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "KS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "KS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Korea, Republic Of"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Korea, Republic Of" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "KS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "KS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "KT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "KT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Christmas Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Christmas Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "KT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "KT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "KU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "KU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Kuwait"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Kuwait" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "KU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "KU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Laos"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Laos" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Lebanon"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Lebanon" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Liberia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Liberia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Liechtenstein"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Liechtenstein" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Lesotho"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Lesotho" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Luxembourg"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Luxembourg" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "LY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "LY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Libya"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Libya" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "LY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "LY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Madagascar"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Madagascar" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Martinque"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Martinque" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MB" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Macao"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Macao" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ME"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ME", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Northern Morocco"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Northern Morocco" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ME"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ME" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mongolia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mongolia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Montserrat"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Montserrat" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Malawi"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Malawi" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ML"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ML", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mali"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mali" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ML"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ML" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Monaco"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Monaco" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Morocco"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Morocco" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mauritius"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mauritius" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Midway Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Midway Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mauritania"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mauritania" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Malta"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Malta" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Muscat And Oman"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Muscat And Oman" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Maldives"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Maldives" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MV" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MX"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MX", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mexico"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mexico" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MX"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MX" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Malaysia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Malaysia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "MZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "MZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Mozambique"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Mozambique" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "MZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "MZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Netherlands, Antilles"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Netherlands, Antilles" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "New Caledonia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "New Caledonia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Niue"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Niue" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NF"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NF", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Norfolk Island"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Norfolk Island" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NF"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NF" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Niger"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Niger" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "New Hebrides"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "New Hebrides" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Nigeria"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Nigeria" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Netherlands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Netherlands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Norway"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Norway" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Nepal"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Nepal" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Nauru"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Nauru" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Nicaragua"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Nicaragua" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "NZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "NZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "New Zealand"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "New Zealand" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "NZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "NZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Paraguay"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Paraguay" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Pitcairn Island"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Pitcairn Island" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Peru"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Peru" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PF"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PF", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Paracal Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Paracal Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PF"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PF" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Philippines"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Philippines" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Pakistan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Pakistan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Poland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Poland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Panama"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Panama" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Portugal"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Portugal" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Papau And New Guinea"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Papau And New Guinea" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Puerto Rico"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Puerto Rico" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "PU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "PU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Portuguese Guinea"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Portuguese Guinea" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "PU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "PU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "QA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "QA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Qatar"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Qatar" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "QA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "QA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "RE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "RE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Reunion"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Reunion" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "RE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "RE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "RH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "RH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Rhodesia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Rhodesia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "RH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "RH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "RO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "RO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Romania"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Romania" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "RO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "RO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "RU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "RU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Russia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Russia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "RU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "RU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "RW"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "RW", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Rwanda"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Rwanda" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "RW"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "RW" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Saudi Arabia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Saudi Arabia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SB"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SB", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "St. Pierre/Miquelon"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "St. Pierre/Miquelon" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SB"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SB" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "St. Christopher/Nevis"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "St. Christopher/Nevis" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Seychelles"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Seychelles" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SF"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SF", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "South Africa"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "South Africa" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SF"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SF" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Senegal"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Senegal" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "St. Helena"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "St. Helena" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sri Lanka"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sri Lanka" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sikkim"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sikkim" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sierra Leone"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sierra Leone" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SM"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SM", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "San Marino"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "San Marino" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SM"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SM" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Singapore"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Singapore" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Solmalia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Solmalia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Spain"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Spain" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Swan Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Swan Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SR"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SR", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Surinam"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Surinam" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SR"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SR" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Spanish Sahara"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Spanish Sahara" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ST"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ST", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "St. Lucia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "St. Lucia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ST"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ST" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sudan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sudan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SV"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SV", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Svalbard"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Svalbard" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SV"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SV" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SW"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SW", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sweden"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sweden" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SW"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SW" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SX"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SX", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Swaziland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Swaziland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SX"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SX" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SY"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SY", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Syria"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Syria" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SY"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SY" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "SZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "SZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Switzerland"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Switzerland" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "SZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "SZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Tasmania"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Tasmania" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TC"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TC", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Trucial States"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Trucial States" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TC"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TC" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TD"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TD", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Trinidad And Tobago"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Trinidad And Tobago" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TD"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TD" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TH"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TH", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Thailand"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Thailand" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TH"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TH" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Turks/ Caicos Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Turks/ Caicos Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TL"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TL", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Tokelau Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Tokelau Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TL"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TL" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TN"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TN", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Tonga"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Tonga" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TN"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TN" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TO"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TO", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Toga"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Toga" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TO"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TO" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TP"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TP", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Sao Tome And Principe"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Sao Tome And Principe" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TP"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TP" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Trust Terr. of Pacific Islands"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Trust Terr. of Pacific Islands" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Tunisia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Tunisia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Turkey"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Turkey" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TW"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TW", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Taiwan"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Taiwan" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TW"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TW" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "TZ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "TZ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Tanzania"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Tanzania" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "TZ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "TZ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "UG"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "UG", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Uganda"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Uganda" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "UG"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "UG" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "UK"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "UK", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "United Kingdom"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "United Kingdom" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "UK"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "UK" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "US"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "US", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "US"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "US" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "US"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "US" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "VI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "VI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "US Virgin Island"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "US Virgin Island" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "VI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "VI" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "VS"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "VS", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Vietnam, Republic Of"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Vietnam, Republic Of" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "VS"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "VS" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "VT"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "VT", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Vatican City"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Vatican City" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "VT"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "VT" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "WA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "WA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Wallis And Futuna"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Wallis And Futuna" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "WA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "WA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "WQ"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "WQ", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Wake Island"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Wake Island" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "WQ"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "WQ" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "YE"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "YE", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Yemen"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Yemen" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "YE"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "YE" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "YU"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "YU", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Yugoslavia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Yugoslavia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "YU"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "YU" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ZA"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ZA", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Zambia"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Zambia" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ZA"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ZA" );
   //:SET CURSOR FIRST DOMAINT.DomainValue WHERE DOMAINT.DomainValue.InternalStringValue = "ZI"
   RESULT = SetCursorFirstEntityByString( DOMAINT, "DomainValue", "InternalStringValue", "ZI", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY  DOMAINT.DomainValue
      RESULT = CreateEntity( DOMAINT, "DomainValue", zPOS_AFTER );
      //:DOMAINT.DomainValue.ExternalDescription = "Zaire"
      SetAttributeFromString( DOMAINT, "DomainValue", "ExternalDescription", "Zaire" );
   } 

   //:END
   //:DOMAINT.DomainValue.SystemRequired = "Y"
   SetAttributeFromString( DOMAINT, "DomainValue", "SystemRequired", "Y" );
   //:DOMAINT.DomainValue.InternalStringValue = "ZI"
   SetAttributeFromString( DOMAINT, "DomainValue", "InternalStringValue", "ZI" );
   //:   COMMIT DOMAINT
   RESULT = CommitObjectInstance( DOMAINT );
   //:DropView( DOMAINT )
   DropView( DOMAINT );
   return( 0 );
// END
} 



}
