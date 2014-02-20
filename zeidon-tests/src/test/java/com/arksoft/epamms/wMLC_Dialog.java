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

package com.arksoft.epamms;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.arksoft.epamms.ZGlobalV_Operation;
import com.arksoft.epamms.ZGlobal1_Operation;
import com.arksoft.epamms.mMasLC_Object;
import com.arksoft.epamms.lMLCATgt_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class wMLC_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   private final KZOEP1AA m_KZOEP1AA;
   public wMLC_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:DIALOG OPERATION
//:CancelDeleteMasterProduct( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
CancelDeleteMasterProduct( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   return( 0 );
// // // We don't think there is anything to do here ... just get back to list
// // // after accepting the master product.
// // AcceptUpdateMasterProduct( ViewToWindow )
// END
} 


private int 
o_fnLocalBuildQual_27( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ePamms" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "EPA_ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_21( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_22( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_23( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_24( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_25( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_26( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_1( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_2( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_5( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_6( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_7( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lMasterProductID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lMasterProductID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_8( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_9( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_10( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_11( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lPrimaryRegistrantID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lPrimaryRegistrantID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_12( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lPrimaryRegistrantID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lPrimaryRegistrantID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_13( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lMasterProductID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lMasterProductID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_14( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ePamms" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "EPA_ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_15( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ePamms" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "EPA_ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_16( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ePamms" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "EPA_ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ChemicalFamily" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_17( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_18( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_19( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_20( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:DeleteSelectedClaims( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
DeleteSelectedClaims( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szSelected
   String   szSelected = null;
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" )
   nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szSelected = mMasLC.MI_UsageList.wkSelected
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSelected;
      if ( szSelected == null )
         sb_szSelected = new StringBuilder( 32 );
      else
         sb_szSelected = new StringBuilder( szSelected );
             GetVariableFromAttribute( sb_szSelected, mi_lTempInteger_0, 'S', 2, mMasLC, "MI_UsageList", "wkSelected", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSelected = sb_szSelected.toString( );}
      //:IF szSelected = "Y"
      if ( ZeidonStringCompare( szSelected, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
         nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
         //:IF nRC = zCURSOR_SET
         if ( nRC == zCURSOR_SET )
         { 
            //:szUsageType = mMasLC.M_Usage.UsageType // "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_1, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szUsageType = sb_szUsageType.toString( );}
            //:IF szUsageType = "C"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
            { 
               //:// ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
               //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
               DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" )
      nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSelectedSurfaces( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
DeleteSelectedSurfaces( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szSelected
   String   szSelected = null;
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" )
   nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szSelected = mMasLC.MI_UsageList.wkSelected
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSelected;
      if ( szSelected == null )
         sb_szSelected = new StringBuilder( 32 );
      else
         sb_szSelected = new StringBuilder( szSelected );
             GetVariableFromAttribute( sb_szSelected, mi_lTempInteger_0, 'S', 2, mMasLC, "MI_UsageList", "wkSelected", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSelected = sb_szSelected.toString( );}
      //:IF szSelected = "Y"
      if ( ZeidonStringCompare( szSelected, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
         nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
         //:IF nRC = zCURSOR_SET
         if ( nRC == zCURSOR_SET )
         { 
            //:szUsageType = mMasLC.M_Usage.UsageType // "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_1, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szUsageType = sb_szUsageType.toString( );}
            //:IF szUsageType = "S"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
            { 
               //:// ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
               //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
               DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" )
      nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:fnInitListMasterProducts( VIEW ViewToWindow, SHORT bMasProd )

//:   VIEW lPrimReg REGISTERED AS lPrimReg
private int 
o_fnInitListMasterProducts( View     ViewToWindow,
                            int      bMasProd )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:INTEGER lProductID
   int      lProductID = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:TraceLineS( "ZeidonOperation: ", "fnInitListMasterProducts" )
   TraceLineS( "ZeidonOperation: ", "fnInitListMasterProducts" );

   //:// lProductID = 0  done by VML generated initializations
   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:IF mPrimReg != 0
   if ( getView( mPrimReg ) != null )
   { 
      //:nRC = CheckExistenceOfEntity( mPrimReg, "MasterProduct" )
      nRC = CheckExistenceOfEntity( mPrimReg, "MasterProduct" );
      //:IF nRC = 0
      if ( nRC == 0 )
      { 
         //:lProductID = mPrimReg.MasterProduct.ID
         {MutableInt mi_lProductID = new MutableInt( lProductID );
                   GetIntegerFromAttribute( mi_lProductID, mPrimReg, "MasterProduct", "ID" );
         lProductID = mi_lProductID.intValue( );}
      } 

      //:END

      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
      //:ELSE
   } 
   else
   { 
      //:IF mMasProd != 0
      if ( getView( mMasProd ) != null )
      { 
         //:nRC = CheckExistenceOfEntity( mMasProd, "MasterProduct" )
         nRC = CheckExistenceOfEntity( mMasProd, "MasterProduct" );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:lProductID = mMasProd.MasterProduct.ID
            {MutableInt mi_lProductID = new MutableInt( lProductID );
                         GetIntegerFromAttribute( mi_lProductID, mMasProd, "MasterProduct", "ID" );
            lProductID = mi_lProductID.intValue( );}
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END

   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:IF bMasProd != 0
   if ( bMasProd != 0 )
   { 
      //:SET CURSOR FIRST mPrimReg.MasterProduct
      //:    WHERE mPrimReg.MasterProduct.ID = lProductID
      RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterProduct", "ID", lProductID, "" );
   } 

   //:END

   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMaintainSurfacesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMaintainSurfacesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "S", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "S"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "S" );
   //:// wWebXfer.Type.StatementCSV = "" not needed
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "S"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
      { 
         //:CREATE ENTITY wWebXfer.Usage
         RESULT = CreateEntity( wWebXfer, "Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// If the previous window is not the Surfaces content, then rebuild the list.
   //:IF wWebXfer.Root.CurrentDialog != "wMLC" OR wWebXfer.Root.CurrentWindow != "MaintainSurfacesList"
   if ( CompareAttributeToString( wWebXfer, "Root", "CurrentDialog", "wMLC" ) != 0 || CompareAttributeToString( wWebXfer, "Root", "CurrentWindow", "MaintainSurfacesList" ) != 0 )
   { 

      //:// Set up Surfaces list in wWebXfer.Root.CurrentStatementText to be
      //:// set to multiline edit box.
      //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
      //:                              "CurrentStatementText",
      //:                              wWebXfer, "Usage", "Name", 0 )
      m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   } 

   //:END
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "MaintainSurfacesList"
// END
} 


//:DIALOG OPERATION
//:ImportSurfacesFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportSurfacesFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 512 ) szMessage
   String   szMessage = null;
   //:STRING ( 512 ) szDirectoryName
   String   szDirectoryName = null;
   //:STRING ( 256 ) szFileName
   String   szFileName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Initialize Input FileName.
   //:szDirectoryName = ""
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringCopy( sb_szDirectoryName, 1, 0, "", 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:SysReadZeidonIni( -1, "App.epamms", "WebDirectory", szDirectoryName )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysReadZeidonIni( -1, "App.epamms", "WebDirectory", sb_szDirectoryName );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:IF szDirectoryName = ""
   if ( ZeidonStringCompare( szDirectoryName, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Surfaces Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Surfaces Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:SysAppendcDirSep( szDirectoryName )
      {StringBuilder sb_szDirectoryName;
      if ( szDirectoryName == null )
         sb_szDirectoryName = new StringBuilder( 32 );
      else
         sb_szDirectoryName = new StringBuilder( szDirectoryName );
             m_KZOEP1AA.SysAppendcDirSep( sb_szDirectoryName );
      szDirectoryName = sb_szDirectoryName.toString( );}
   } 

   //:END

   //:szFileName = wWebXfer.Root.String
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetVariableFromAttribute( sb_szFileName, mi_lTempInteger_0, 'S', 257, wWebXfer, "Root", "String", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szFileName = sb_szFileName.toString( );}
   //:IF szFileName = ""
   if ( ZeidonStringCompare( szFileName, 1, 0, "", 1, 0, 257 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Surfaces Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Surfaces Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "S", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "S"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "S" );

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName );
   }
   catch ( Exception e )
   {
      // check this out
      throw ZeidonException.wrapException( e );
   }
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:IF nRC = -1
      if ( nRC == -1 )
      { 
         //:szMessage = "The Import File " + szDirectoryName + " cannot be opened."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " cannot be opened.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szMessage = "The Import File " + szDirectoryName + " does not contain a valid entity.attribute header."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " does not contain a valid entity.attribute header.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
      } 

      //:END

      //:MessageSend( ViewToWindow, "", "Import Surfaces Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Surfaces Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set up Surfaces list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              wWebXfer, "Usage", "Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "S", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportSurfacesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportSurfacesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of surfaces.
   //:nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" )
   nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "S"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" )
      nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "S", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:// Set up Surfaces list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "S"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "S" );
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             wWebXfer, "Usage", "Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   //:FOR EACH wWebXfer.Usage
   RESULT = SetCursorFirstEntity( wWebXfer, "Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mMasLC.M_Usage
      RESULT = CreateEntity( mMasLC, "M_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL )
      SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL );
      //:mMasLC.M_Usage.UsageType = "S"
      SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "S" );
      RESULT = SetCursorNextEntity( wWebXfer, "Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "S", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMaintainClaimsList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMaintainClaimsList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "C", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "C"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "C" );
   //:// wWebXfer.Type.StatementCSV = "" not needed
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:CREATE ENTITY wWebXfer.Usage
         RESULT = CreateEntity( wWebXfer, "Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// If the previous window is not the Claims content, then rebuild the list.
   //:IF wWebXfer.Root.CurrentDialog != "wMLC" OR wWebXfer.Root.CurrentWindow != "MaintainClaimsList"
   if ( CompareAttributeToString( wWebXfer, "Root", "CurrentDialog", "wMLC" ) != 0 || CompareAttributeToString( wWebXfer, "Root", "CurrentWindow", "MaintainClaimsList" ) != 0 )
   { 

      //:// Set up Claims list in wWebXfer.Root.CurrentStatementText to be
      //:// set to multiline edit box.
      //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
      //:                              "CurrentStatementText",
      //:                              wWebXfer, "Usage", "ClaimsClassification,Name", 0 )
      m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "ClaimsClassification,Name", 0 );
   } 

   //:END
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "MaintainClaimsList"
// END
} 


//:DIALOG OPERATION
//:ImportClaimsFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportClaimsFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 512 ) szMessage
   String   szMessage = null;
   //:STRING ( 512 ) szDirectoryName
   String   szDirectoryName = null;
   //:STRING ( 256 ) szFileName
   String   szFileName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Initialize Input FileName.
   //:szDirectoryName = ""
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringCopy( sb_szDirectoryName, 1, 0, "", 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:SysReadZeidonIni( -1, "App.epamms", "WebDirectory", szDirectoryName )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysReadZeidonIni( -1, "App.epamms", "WebDirectory", sb_szDirectoryName );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:IF szDirectoryName = ""
   if ( ZeidonStringCompare( szDirectoryName, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Claims Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Claims Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:SysAppendcDirSep( szDirectoryName )
      {StringBuilder sb_szDirectoryName;
      if ( szDirectoryName == null )
         sb_szDirectoryName = new StringBuilder( 32 );
      else
         sb_szDirectoryName = new StringBuilder( szDirectoryName );
             m_KZOEP1AA.SysAppendcDirSep( sb_szDirectoryName );
      szDirectoryName = sb_szDirectoryName.toString( );}
   } 

   //:END

   //:szFileName = wWebXfer.Root.String
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetVariableFromAttribute( sb_szFileName, mi_lTempInteger_0, 'S', 257, wWebXfer, "Root", "String", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szFileName = sb_szFileName.toString( );}
   //:IF szFileName = ""
   if ( ZeidonStringCompare( szFileName, 1, 0, "", 1, 0, 257 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Claims Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Claims Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "C", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "C"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "C" );

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName );
   }
   catch ( Exception e )
   {
      // check this out
      throw ZeidonException.wrapException( e );
   }
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:IF nRC = -1
      if ( nRC == -1 )
      { 
         //:szMessage = "The Import File " + szDirectoryName + " cannot be opened."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " cannot be opened.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szMessage = "The Import File " + szDirectoryName + " does not contain a valid entity.attribute header."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " does not contain a valid entity.attribute header.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
      } 

      //:END

      //:MessageSend( ViewToWindow, "", "Import Claims Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Claims Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set up Claims list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              wWebXfer, "Usage", "ClaimsClassification,Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "ClaimsClassification,Name", 0 );
   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "C", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportClaimsList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportClaimsList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of claims.
   //:nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" )
   nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" )
      nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "C", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:// Set up Claims list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "C"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "C" );
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             wWebXfer, "Usage", "ClaimsClassification,Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "ClaimsClassification,Name", 0 );
   //:FOR EACH wWebXfer.Usage
   RESULT = SetCursorFirstEntity( wWebXfer, "Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mMasLC.M_Usage
      RESULT = CreateEntity( mMasLC, "M_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mMasLC, "M_Usage",  wWebXfer, "Usage", zSET_NULL )
      SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL );
      //:mMasLC.M_Usage.UsageType = "C"
      SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "C" );
      RESULT = SetCursorNextEntity( wWebXfer, "Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "C", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMaintainAreasOfUseList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMaintainAreasOfUseList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "U", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "U"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "U" );
   //:// wWebXfer.Type.StatementCSV = "" not needed
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "U"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
      { 
         //:CREATE ENTITY wWebXfer.Usage
         RESULT = CreateEntity( wWebXfer, "Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// If the previous window is not the Areas of Use content, then rebuild the list.
   //:IF wWebXfer.Root.CurrentDialog != "wMLC" OR wWebXfer.Root.CurrentWindow != "MaintainAreasOfUseList"
   if ( CompareAttributeToString( wWebXfer, "Root", "CurrentDialog", "wMLC" ) != 0 || CompareAttributeToString( wWebXfer, "Root", "CurrentWindow", "MaintainAreasOfUseList" ) != 0 )
   { 

      //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText to be
      //:// set to multiline edit box.
      //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
      //:                              "CurrentStatementText",
      //:                              wWebXfer, "Usage", "Name", 0 )
      m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   } 

   //:END
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "MaintainAreasOfUseList"
// END
} 


//:DIALOG OPERATION
//:InitMaintainAppTypesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMaintainAppTypesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "T", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "T"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "T" );
   //:// wWebXfer.Type.StatementCSV = "" not needed
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "T"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
      { 
         //:CREATE ENTITY wWebXfer.Usage
         RESULT = CreateEntity( wWebXfer, "Usage", zPOS_AFTER );
         //:SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL )
         SetMatchingAttributesByName( wWebXfer, "Usage", mMasLC, "M_Usage", zSET_NULL );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:// If the previous window is not the AppTypes content, then rebuild the list.
   //:IF wWebXfer.Root.CurrentDialog != "wMLC" OR wWebXfer.Root.CurrentWindow != "MaintainAppTypesList"
   if ( CompareAttributeToString( wWebXfer, "Root", "CurrentDialog", "wMLC" ) != 0 || CompareAttributeToString( wWebXfer, "Root", "CurrentWindow", "MaintainAppTypesList" ) != 0 )
   { 

      //:// Set up AppTypes list in wWebXfer.Root.CurrentStatementText to be
      //:// set to multiline edit box.
      //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
      //:                              "CurrentStatementText",
      //:                              wWebXfer, "Usage", "Name", 0 )
      m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   } 

   //:END
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "MaintainAppTypesList"
// END
} 


//:DIALOG OPERATION
//:ImportAreasOfUseFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAreasOfUseFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 512 ) szMessage
   String   szMessage = null;
   //:STRING ( 512 ) szDirectoryName
   String   szDirectoryName = null;
   //:STRING ( 256 ) szFileName
   String   szFileName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Initialize Input FileName.
   //:szDirectoryName = ""
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringCopy( sb_szDirectoryName, 1, 0, "", 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:SysReadZeidonIni( -1, "App.epamms", "WebDirectory", szDirectoryName )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysReadZeidonIni( -1, "App.epamms", "WebDirectory", sb_szDirectoryName );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:IF szDirectoryName = ""
   if ( ZeidonStringCompare( szDirectoryName, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Areas Of Use Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas Of Use Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:SysAppendcDirSep( szDirectoryName )
      {StringBuilder sb_szDirectoryName;
      if ( szDirectoryName == null )
         sb_szDirectoryName = new StringBuilder( 32 );
      else
         sb_szDirectoryName = new StringBuilder( szDirectoryName );
             m_KZOEP1AA.SysAppendcDirSep( sb_szDirectoryName );
      szDirectoryName = sb_szDirectoryName.toString( );}
   } 

   //:END

   //:szFileName = wWebXfer.Root.String
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetVariableFromAttribute( sb_szFileName, mi_lTempInteger_0, 'S', 257, wWebXfer, "Root", "String", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szFileName = sb_szFileName.toString( );}
   //:IF szFileName = ""
   if ( ZeidonStringCompare( szFileName, 1, 0, "", 1, 0, 257 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Areas Of Use Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas Of Use Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "U", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "U"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "U" );

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName );
   }
   catch ( Exception e )
   {
      // check this out
      throw ZeidonException.wrapException( e );
   }
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:IF nRC = -1
      if ( nRC == -1 )
      { 
         //:szMessage = "The Import File " + szDirectoryName + " cannot be opened."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " cannot be opened.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szMessage = "The Import File " + szDirectoryName + " does not contain a valid entity.attribute header."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " does not contain a valid entity.attribute header.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
      } 

      //:END

      //:MessageSend( ViewToWindow, "", "Import Areas Of Use Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas Of Use Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              wWebXfer, "Usage", "Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "U", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportAppTypesFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAppTypesFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 512 ) szMessage
   String   szMessage = null;
   //:STRING ( 512 ) szDirectoryName
   String   szDirectoryName = null;
   //:STRING ( 256 ) szFileName
   String   szFileName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Initialize Input FileName.
   //:szDirectoryName = ""
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringCopy( sb_szDirectoryName, 1, 0, "", 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:SysReadZeidonIni( -1, "App.epamms", "WebDirectory", szDirectoryName )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysReadZeidonIni( -1, "App.epamms", "WebDirectory", sb_szDirectoryName );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:IF szDirectoryName = ""
   if ( ZeidonStringCompare( szDirectoryName, 1, 0, "", 1, 0, 513 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Application Types Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Application Types Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:SysAppendcDirSep( szDirectoryName )
      {StringBuilder sb_szDirectoryName;
      if ( szDirectoryName == null )
         sb_szDirectoryName = new StringBuilder( 32 );
      else
         sb_szDirectoryName = new StringBuilder( szDirectoryName );
             m_KZOEP1AA.SysAppendcDirSep( sb_szDirectoryName );
      szDirectoryName = sb_szDirectoryName.toString( );}
   } 

   //:END

   //:szFileName = wWebXfer.Root.String
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetVariableFromAttribute( sb_szFileName, mi_lTempInteger_0, 'S', 257, wWebXfer, "Root", "String", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szFileName = sb_szFileName.toString( );}
   //:IF szFileName = ""
   if ( ZeidonStringCompare( szFileName, 1, 0, "", 1, 0, 257 ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Import Application Types Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Application Types Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "T", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "T"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "T" );

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( wWebXfer, szDirectoryName );
   }
   catch ( Exception e )
   {
      // check this out
      throw ZeidonException.wrapException( e );
   }
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:IF nRC = -1
      if ( nRC == -1 )
      { 
         //:szMessage = "The Import File " + szDirectoryName + " cannot be opened."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " cannot be opened.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szMessage = "The Import File " + szDirectoryName + " does not contain a valid entity.attribute header."
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringCopy( sb_szMessage, 1, 0, "The Import File ", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, " does not contain a valid entity.attribute header.", 1, 0, 513 );
         szMessage = sb_szMessage.toString( );}
      } 

      //:END

      //:MessageSend( ViewToWindow, "", "Import AppTypes Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import AppTypes Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set up AppTypes list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              wWebXfer, "Usage", "Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "T", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportAreasOfUseList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportAreasOfUseList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of areas of use.
   //:nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" )
   nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "U"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" )
      nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "U", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "U"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "U" );
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             wWebXfer, "Usage", "Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   //:FOR EACH wWebXfer.Usage
   RESULT = SetCursorFirstEntity( wWebXfer, "Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mMasLC.M_Usage
      RESULT = CreateEntity( mMasLC, "M_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL )
      SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL );
      //:mMasLC.M_Usage.UsageType = "U"
      SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "U" );
      RESULT = SetCursorNextEntity( wWebXfer, "Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "U", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportAppTypesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportAppTypesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of application types.
   //:nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" )
   nRC = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "T"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" )
      nRC = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "T", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:// Set up Application Types list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:CREATE ENTITY wWebXfer.Type
   RESULT = CreateEntity( wWebXfer, "Type", zPOS_AFTER );
   //:wWebXfer.Type.UsageType = "T"
   SetAttributeFromString( wWebXfer, "Type", "UsageType", "T" );
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             wWebXfer, "Usage", "Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", wWebXfer, "Usage", "Name", 0 );
   //:FOR EACH wWebXfer.Usage
   RESULT = SetCursorFirstEntity( wWebXfer, "Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mMasLC.M_Usage
      RESULT = CreateEntity( mMasLC, "M_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL )
      SetMatchingAttributesByName( mMasLC, "M_Usage", wWebXfer, "Usage", zSET_NULL );
      //:mMasLC.M_Usage.UsageType = "T"
      SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "T" );
      RESULT = SetCursorNextEntity( wWebXfer, "Usage", "" );
   } 

   //:END

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "T", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelImportAreasOfUseList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportAreasOfUseList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "U", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelImportAppTypesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportAppTypesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "T", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelImportSurfacesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportSurfacesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "S", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelImportClaimsList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportClaimsList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST wWebXfer.Type WHERE wWebXfer.Type.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( wWebXfer, "Type", "UsageType", "C", "" );
   //:IF RESULT = 0
   if ( RESULT == 0 )
   { 
      //:DELETE ENTITY wWebXfer.Type
      RESULT = DeleteEntity( wWebXfer, "Type", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelAddSurfacesStmts( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelAddSurfacesStmts( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelAddAppTypesStmts( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelAddAppTypesStmts( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ListMLCs( VIEW ViewToWindow )

//:   VIEW lMLC BASED ON LOD lMLC
public int 
ListMLCs( View     ViewToWindow )
{
   zVIEW    lMLC = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lMLC NAMED "lMLC"
   RESULT = GetViewByName( lMLC, "lMLC", ViewToWindow, zLEVEL_TASK );
   //:IF lMLC != 0
   if ( getView( lMLC ) != null )
   { 
      //:DropObjectInstance( lMLC )
      DropObjectInstance( lMLC );
   } 

   //:END

   //:ACTIVATE lMLC Multiple
   RESULT = ActivateObjectInstance( lMLC, "lMLC", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lMLC "lMLC"
   SetNameForView( lMLC, "lMLC", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GenerateNewMLC_Version( VIEW ViewToWindow )

//:   VIEW mMasProd    REGISTERED AS mMasProd
public int 
GenerateNewMLC_Version( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW OriginalMLC BASED ON LOD  mMasLC
   zVIEW    OriginalMLC = new zVIEW( );
   //:VIEW NewMLC      BASED ON LOD  mMasLC
   zVIEW    NewMLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:// We have to make sure the Product is in good shape before we go on!
   //:lID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasProd, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:// Generate new MLC from the selected MLC.
      //:ACTIVATE OriginalMLC WHERE OriginalMLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_21( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( OriginalMLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW OriginalMLC "OriginalMLC"
      SetNameForView( OriginalMLC, "OriginalMLC", null, zLEVEL_TASK );

      //:ACTIVATE NewMLC EMPTY
      RESULT = ActivateEmptyObjectInstance( NewMLC, "mMasLC", ViewToWindow, zSINGLE );
      //:NAME VIEW NewMLC "NewMLC"
      SetNameForView( NewMLC, "NewMLC", null, zLEVEL_TASK );

      //:BuildNewMLC_Version( NewMLC, OriginalMLC )    // Create NewMLC from OriginalMLC
      {
       mMasLC_Object m_mMasLC_Object = new mMasLC_Object( NewMLC );
       m_mMasLC_Object.omMasLC_BuildNewMLC_Version( NewMLC, OriginalMLC );
       // m_mMasLC_Object = null;  // permit gc  (unnecessary)
      }
      //:NewMLC.MasterLabelContent.Finalized = "N"
      SetAttributeFromString( NewMLC, "MasterLabelContent", "Finalized", "N" );

      //:/* Remove these lines prior to deployment!!!
      //:// Make modifications to Target for compare purposes.
      //:NewMLC.M_IngredientsStatement.Percent = 20.141
      //:NewMLC.M_IngredientsStatement.ChemicalName = "Don Test Didecyl dimethyl ammonium bromide"
      //:NewMLC.M_StorageDisposalSection.Title = "STORAGE AND DISPOSAL II"
      //:SET CURSOR FIRST NewMLC.M_DirectionsForUseSection
      //:SET CURSOR NEXT NewMLC.M_DirectionsForUseSection
      //:NewMLC.M_DirectionsForUseStatement.Text = "Don Directions Test"
      //:CREATE ENTITY NewMLC.M_Usage
      //:NewMLC.M_Usage.UsageType = "C"
      //:NewMLC.M_Usage.Name = "Don Claim"
      //:NewMLC.M_Usage.BoldItalic = "R"
      //:CREATE ENTITY NewMLC.M_MarketingUsageOrdering
      //:INCLUDE NewMLC.M_MarketingUsage FROM NewMLC.M_Usage
      //:// End of: Remove these lines prior to deployment!!!
      //:*/
      //:DropObjectInstance( OriginalMLC )
      DropObjectInstance( OriginalMLC );

      //:COMMIT NewMLC
      RESULT = CommitObjectInstance( NewMLC );
      //:DropObjectInstance( NewMLC )
      DropObjectInstance( NewMLC );
      //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = lID
      o_fnLocalBuildQual_22( ViewToWindow, vTempViewVar_1, lID );
      RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:DeleteMLC( VIEW ViewToWindow )

//:   VIEW lMLC   REGISTERED AS lMLC
public int 
DeleteMLC( View     ViewToWindow )
{
   zVIEW    lMLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lMLC, "lMLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = MessagePrompt( ViewToWindow, "", "Delete", "OK to delete MLC?", 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
   nRC = MessagePrompt( ViewToWindow, "", "Delete", "OK to delete MLC?", 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 );
   //:IF nRC = zRESPONSE_NO
   if ( nRC == zRESPONSE_NO )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lMLC.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lMLC, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_25( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:DELETE ENTITY mMasLC.MasterLabelContent
   RESULT = DeleteEntity( mMasLC, "MasterLabelContent", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );

   //:ListMLCs( ViewToWindow )
   ListMLCs( ViewToWindow );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CopyMLC_ToNewProductMLC( VIEW ViewToWindow )

//:   VIEW mMasProd  REGISTERED AS mMasProd
public int 
CopyMLC_ToNewProductMLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd2 BASED ON LOD  mMasProd
   zVIEW    mMasProd2 = new zVIEW( );
   //:VIEW mMasLC    REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW NewMLC    BASED ON LOD  mMasLC
   zVIEW    NewMLC = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// This check is done by JavaScript, so should never happen.
   //:IF mMasProd.MasterProduct.Name = ""
   if ( CompareAttributeToString( mMasProd, "MasterProduct", "Name", "" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Copy MLC to new Product MLC",
      //:             "A name must be specified.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Copy MLC to new Product MLC", "A name must be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// If a Product by same name exists, error.
   //:ACTIVATE mMasProd2 WHERE mMasProd2.MasterProduct.Name = mMasProd.MasterProduct.Name
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mMasProd, "MasterProduct", "Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_20( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mMasProd2, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:DropObjectInstance( mMasProd2 )
   DropObjectInstance( mMasProd2 );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Copy MLC to New Product MLC",
      //:             "A unique Product Name must be specified.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Copy MLC to New Product MLC", "A unique Product Name must be specified.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:ACTIVATE NewMLC EMPTY
   RESULT = ActivateEmptyObjectInstance( NewMLC, "mMasLC", ViewToWindow, zSINGLE );
   //:CREATE ENTITY NewMLC.MasterLabelContent
   RESULT = CreateEntity( NewMLC, "MasterLabelContent", zPOS_AFTER );
   //:NAME VIEW NewMLC "NewMLC"
   SetNameForView( NewMLC, "NewMLC", null, zLEVEL_TASK );

   //:SetMatchingAttributesByName( mMasProd, "MasterProduct", mMasLC, "MasterProduct", zSET_NULL )
   SetMatchingAttributesByName( mMasProd, "MasterProduct", mMasLC, "MasterProduct", zSET_NULL );
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );
   //:INCLUDE NewMLC.MasterProduct FROM mMasProd.MasterProduct
   RESULT = IncludeSubobjectFromSubobject( NewMLC, "MasterProduct", mMasProd, "MasterProduct", zPOS_AFTER );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );

   //:CopyMLCToNewProduct( NewMLC, mMasLC )
   {
    mMasLC_Object m_mMasLC_Object = new mMasLC_Object( NewMLC );
    m_mMasLC_Object.omMasLC_CopyMLCToNewProduct( NewMLC, mMasLC );
    // m_mMasLC_Object = null;  // permit gc  (unnecessary)
   }

   //:COMMIT NewMLC
   RESULT = CommitObjectInstance( NewMLC );
   return( 0 );
// // ListMLCs( ViewToWindow )
// END
} 


//:DIALOG OPERATION
//:CancelMLC( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
CancelMLC( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitClaimsStmtsForInsert( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
InitClaimsStmtsForInsert( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     BASED ON LOD  mEPA
   zVIEW    mEPA = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitClaimsStmtsForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitClaimsStmtsForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:GET VIEW mEPA NAMED "mEPA"
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );
   //:IF mEPA != 0
   if ( getView( mEPA ) != null )
   { 
      //:DropObjectInstance( mEPA )
      DropObjectInstance( mEPA );
   } 

   //:END

   //:ACTIVATE mEPA WHERE mEPA.EPA_ChemicalFamily.ChemicalFamily = mMasLC.MasterProduct.ChemicalFamily
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mMasLC, "MasterProduct", "ChemicalFamily" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSurfacesStmtsForInsert( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
InitSurfacesStmtsForInsert( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     BASED ON LOD  mEPA
   zVIEW    mEPA = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitSurfacesStmtsForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitSurfacesStmtsForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:GET VIEW mEPA NAMED "mEPA"
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );
   //:IF mEPA != 0
   if ( getView( mEPA ) != null )
   { 
      //:DropObjectInstance( mEPA )
      DropObjectInstance( mEPA );
   } 

   //:END

   //:ACTIVATE mEPA WHERE mEPA.EPA_ChemicalFamily.ChemicalFamily = mMasLC.MasterProduct.ChemicalFamily
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mMasLC, "MasterProduct", "ChemicalFamily" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmAddClaimsStmts( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmAddClaimsStmts( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mEPA.EPA_Claim
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_Claim.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_Claim", "wkSelected", "Y" ) == 0 )
      { 
         //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
         CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
         //:mMasLC.M_Usage.UsageType = "C" // Claim
         SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "C" );
         //:mMasLC.M_Usage.ClaimsClassification = mEPA.EPA_Claim.ClaimsClassification
         SetAttributeFromAttribute( mMasLC, "M_Usage", "ClaimsClassification", mEPA, "EPA_Claim", "ClaimsClassification" );
         //:mMasLC.M_Usage.Name = mEPA.EPA_Claim.Name
         SetAttributeFromAttribute( mMasLC, "M_Usage", "Name", mEPA, "EPA_Claim", "Name" );
         //:mMasLC.M_Usage.BoldItalic = mEPA.EPA_Claim.BoldItalic
         SetAttributeFromAttribute( mMasLC, "M_Usage", "BoldItalic", mEPA, "EPA_Claim", "BoldItalic" );
      } 

      RESULT = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
      //:END
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmAddSurfacesStmts( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmAddSurfacesStmts( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mEPA.EPA_Surface
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Surface", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_Surface.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_Surface", "wkSelected", "Y" ) == 0 )
      { 
         //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
         CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
         //:mMasLC.M_Usage.UsageType = "S" // Surface
         SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "S" );
         //:mMasLC.M_Usage.Name = mEPA.EPA_Surface.Name
         SetAttributeFromAttribute( mMasLC, "M_Usage", "Name", mEPA, "EPA_Surface", "Name" );
         //:mMasLC.M_Usage.BoldItalic = mEPA.EPA_Surface.BoldItalic
         SetAttributeFromAttribute( mMasLC, "M_Usage", "BoldItalic", mEPA, "EPA_Surface", "BoldItalic" );
      } 

      RESULT = SetCursorNextEntity( mEPA, "EPA_Surface", "" );
      //:END
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmAddAreasOfUseStmts( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmAddAreasOfUseStmts( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mEPA.EPA_AreaOfUse
   RESULT = SetCursorFirstEntity( mEPA, "EPA_AreaOfUse", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_AreaOfUse.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_AreaOfUse", "wkSelected", "Y" ) == 0 )
      { 
         //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
         CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
         //:mMasLC.M_Usage.UsageType = "U" // Area of Use
         SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "U" );
         //:mMasLC.M_Usage.Name = mEPA.EPA_AreaOfUse.Name
         SetAttributeFromAttribute( mMasLC, "M_Usage", "Name", mEPA, "EPA_AreaOfUse", "Name" );
         //:mMasLC.M_Usage.BoldItalic = mEPA.EPA_AreaOfUse.BoldItalic
         SetAttributeFromAttribute( mMasLC, "M_Usage", "BoldItalic", mEPA, "EPA_AreaOfUse", "BoldItalic" );
      } 

      RESULT = SetCursorNextEntity( mEPA, "EPA_AreaOfUse", "" );
      //:END
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmAddAppTypesStmts( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmAddAppTypesStmts( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mEPA.EPA_AreaOfUse
   RESULT = SetCursorFirstEntity( mEPA, "EPA_AreaOfUse", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_AreaOfUse.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_AreaOfUse", "wkSelected", "Y" ) == 0 )
      { 
         //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
         CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
         //:mMasLC.M_Usage.UsageType = "T" // Application Type
         SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "T" );
         //:mMasLC.M_Usage.Name = mEPA.EPA_ApplicationType.Name
         SetAttributeFromAttribute( mMasLC, "M_Usage", "Name", mEPA, "EPA_ApplicationType", "Name" );
         //:mMasLC.M_Usage.BoldItalic = mEPA.EPA_AreaOfUse.BoldItalic
         SetAttributeFromAttribute( mMasLC, "M_Usage", "BoldItalic", mEPA, "EPA_AreaOfUse", "BoldItalic" );
      } 

      RESULT = SetCursorNextEntity( mEPA, "EPA_AreaOfUse", "" );
      //:END
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelAddAreasOfUseStmts( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelAddAreasOfUseStmts( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelAddClaimsStmts( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelAddClaimsStmts( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAreasOfUseStmtsForInsert( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
InitAreasOfUseStmtsForInsert( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     BASED ON LOD  mEPA
   zVIEW    mEPA = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitAreasOfUseStmtsForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitAreasOfUseStmtsForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:GET VIEW mEPA NAMED "mEPA"
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );
   //:IF mEPA != 0
   if ( getView( mEPA ) != null )
   { 
      //:DropObjectInstance( mEPA )
      DropObjectInstance( mEPA );
   } 

   //:END

   //:ACTIVATE mEPA WHERE mEPA.EPA_ChemicalFamily.ChemicalFamily = mMasLC.MasterProduct.ChemicalFamily
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mMasLC, "MasterProduct", "ChemicalFamily" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveMarketingSectUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMarketingSectUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_MarketingSection
      RESULT = SetCursorPrevEntity( mTempLC, "M_MarketingSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_MarketingSection",
   //:               mMasLC, "M_MarketingSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_MarketingSection", mMasLC, "M_MarketingSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveMarketingSectDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMarketingSectDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_MarketingSection
      RESULT = SetCursorNextEntity( mTempLC, "M_MarketingSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_MarketingSection",
   //:               mMasLC, "M_MarketingSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_MarketingSection", mMasLC, "M_MarketingSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMarketingSectForDelete( VIEW ViewToWindow )

public int 
SelectMarketingSectForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterProduct( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
ConfirmDeleteMasterProduct( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   zVIEW    vTempViewVar_2 = new zVIEW( );


   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END

   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END

   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:lID = mPrimReg.MasterProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mPrimReg, "MasterProduct", "ID" );
   lID = mi_lID.intValue( );}

   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = lID
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF mMasProd.SubregProduct EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasProd, "SubregProduct" );
   if ( lTempInteger_0 == 0 )
   { 

      //:IF mMasProd.MasterLabelContent EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mMasProd, "MasterLabelContent" );
      if ( lTempInteger_1 == 0 )
      { 
         //:MessageSend( ViewToWindow, "", "Delete Master Product",
         //:             "The Master Product has associated Subregistrant Products.  It cannot be deleted",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Delete Master Product", "The Master Product has associated Subregistrant Products.  It cannot be deleted", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
         //:ELSE
      } 
      else
      { 
         //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mMasProd.SubregProduct.ID
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mMasProd, "SubregProduct", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
         RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_1, zSINGLE );
         DropView( vTempViewVar_1 );
         //:DELETE ENTITY mSubProd.SubregProduct
         RESULT = DeleteEntity( mSubProd, "SubregProduct", zPOS_NEXT );
         //:COMMIT mSubProd
         RESULT = CommitObjectInstance( mSubProd );
         //:DropObjectInstance( mSubProd )
         DropObjectInstance( mSubProd );

         //:// Reactivate mMasProd since the restricted delete entity should be gone
         //:DropObjectInstance( mMasProd )
         DropObjectInstance( mMasProd );
         //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = lID
         o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_2, lID );
         RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_2, zSINGLE );
         DropView( vTempViewVar_2 );
      } 

      //:END
   } 

   //:END

   //:DELETE ENTITY mMasProd.MasterProduct
   RESULT = DeleteEntity( mMasProd, "MasterProduct", zPOS_NEXT );
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );

   //:fnInitListMasterProducts( ViewToWindow, 0 )
   o_fnInitListMasterProducts( ViewToWindow, 0 );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterProductForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterProductForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMasterProductForDelete" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMasterProductForDelete" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END

   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = mPrimReg.MasterProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mPrimReg, "MasterProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptProductName = mMasProd.MasterProduct.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductName", mMasProd, "MasterProduct", "Name" );
   //:wWebXfer.Root.AttemptProductNumber = mMasProd.MasterProduct.Number
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductNumber", mMasProd, "MasterProduct", "Number" );
   //:// wWebXfer.Root.AttemptContentVersion = mMasProd.MasterLabelContent.Version
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteMasterProduct( VIEW ViewToWindow )

public int 
DeleteMasterProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:EditMasterLabelVersionData( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditMasterLabelVersionData( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditMasterLabelVersionData: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditMasterLabelVersionData: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitMasterLabelContentForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterLabelContentForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:wWebXfer.Root.AttemptContentVersion = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptContentVersion", "" );

   //:ACTIVATE mMasLC EMPTY
   RESULT = ActivateEmptyObjectInstance( mMasLC, "mMasLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

   //:// We need to create a new MasterLabelContent entity.
   //:// CreateTemporalEntity( mMasLC, "MasterLabelContent", zPOS_FIRST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_FIRST, "mMasLC", "MasterLabelContent", "InitMasterLabelContentForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_FIRST, "mMasLC", "MasterLabelContent", "InitMasterLabelContentForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.MasterLabelContent.Finalized = "N"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "Finalized", "N" );
   //:mMasLC.MasterLabelContent.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   //:mMasLC.MasterLabelContent.RevisionDate = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "RevisionDate", wWebXfer, "Root", "dCurrentDateTime" );
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:IncludeSubobjectFromSubobject( mMasLC, "MasterProduct",
   //:                               mMasProd, "MasterProduct", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mMasLC, "MasterProduct", mMasProd, "MasterProduct", zPOS_BEFORE );
   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterLabelContentForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterLabelContentForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mMasLC NAMED "mMasLC"
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   //:IF mMasLC != 0
   if ( getView( mMasLC ) != null )
   { 
      //:DropObjectInstance( mMasLC )
      DropObjectInstance( mMasLC );
   } 

   //:END

   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mMasProd, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptContentVersion = mMasLC.MasterLabelContent.Version
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptContentVersion", mMasLC, "MasterLabelContent", "Version" );

   //:// We are going to update the existing MasterLabelContent entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MasterLabelContent", "InitMasterLabelContentForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MasterLabelContent", "InitMasterLabelContentForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF mMasLC.MasterLabelContent.CreatedDateTime = ""
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "CreatedDateTime", "" ) == 0 )
   { 
      //:mMasLC.MasterLabelContent.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
      SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:// wWebXfer.Root.Banner1 = qOrganiz.Organization.dLoginUserName
   //:// wWebXfer.Root.Banner2 = qOrganiz.Organization.dLoginUserRole
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = mMasProd.MasterProduct.dContentVersionRevDate
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner4", mMasProd, "MasterProduct", "dContentVersionRevDate" );
   //:wWebXfer.Root.Banner5 = mMasProd.MasterProduct.dDescription
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner5", mMasProd, "MasterProduct", "dDescription" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterLabelContentForDelete( VIEW ViewToWindow )

public int 
InitMasterLabelContentForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewMasterLabelContent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewMasterLabelContent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING (  50  ) szContentVersion
   String   szContentVersion = null;
   //:INTEGER lContentVersionLth
   int      lContentVersionLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section name is not blank and is unique.
   //:szContentVersion = wWebXfer.Root.AttemptContentVersion
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szContentVersion;
   if ( szContentVersion == null )
      sb_szContentVersion = new StringBuilder( 32 );
   else
      sb_szContentVersion = new StringBuilder( szContentVersion );
       GetVariableFromAttribute( sb_szContentVersion, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptContentVersion", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szContentVersion = sb_szContentVersion.toString( );}
   //:lContentVersionLth = zGetStringLen( szContentVersion )
   lContentVersionLth = zGetStringLen( szContentVersion );
   //:TraceLineS( "Product Content Name: ", szContentVersion )
   TraceLineS( "Product Content Name: ", szContentVersion );
   //:TraceLineI( "Product Content Length: ", lContentVersionLth )
   TraceLineI( "Product Content Length: ", lContentVersionLth );
   //:IF lContentVersionLth < 1
   if ( lContentVersionLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Master Product Content",
      //:             "The Master Product Content Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Master Product Content", "The Master Product Content Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
      lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
      //:IF SetEntityCursor( mMasLC, "MasterLabelContent", "Version", lControl,
      //:                    szContentVersion, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mMasLC, "MasterLabelContent", "Version", lControl, szContentVersion, "", "", 0, "", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Master Product Content",
         //:             "The Master Product Content Version must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Master Product Content", "The Master Product Content Version must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END

   //:mMasLC.MasterLabelContent.RevisionDate = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "RevisionDate", wWebXfer, "Root", "dCurrentDateTime" );
   //:mMasLC.MasterLabelContent.Version = szContentVersion
   SetAttributeFromString( mMasLC, "MasterLabelContent", "Version", szContentVersion );
   //:// AcceptSubobject( mMasLC, "MasterLabelContent" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptNewMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptNewMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:SetMatchingAttributesByName( mMasProd, "MasterLabelContent",
   //:                             mMasLC, "MasterLabelContent", zSET_ALL )
   SetMatchingAttributesByName( mMasProd, "MasterLabelContent", mMasLC, "MasterLabelContent", zSET_ALL );
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );

   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "PrimaryRegistrant", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mPrimReg.MasterLabelContent
   //:    WHERE mPrimReg.MasterLabelContent.ID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mMasLC, "MasterLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lTempInteger_3, "" );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateMasterLabelContent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateMasterLabelContent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING (  50  ) szContentVersion
   String   szContentVersion = null;
   //:INTEGER lContentVersionLth
   int      lContentVersionLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section name is not blank and is unique.
   //:szContentVersion = wWebXfer.Root.AttemptContentVersion
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szContentVersion;
   if ( szContentVersion == null )
      sb_szContentVersion = new StringBuilder( 32 );
   else
      sb_szContentVersion = new StringBuilder( szContentVersion );
       GetVariableFromAttribute( sb_szContentVersion, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptContentVersion", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szContentVersion = sb_szContentVersion.toString( );}
   //:lContentVersionLth = zGetStringLen( szContentVersion )
   lContentVersionLth = zGetStringLen( szContentVersion );
   //:TraceLineS( "Product Content Version: ", szContentVersion )
   TraceLineS( "Product Content Version: ", szContentVersion );
   //:TraceLineI( "Product Version Length: ", lContentVersionLth )
   TraceLineI( "Product Version Length: ", lContentVersionLth );
   //:IF lContentVersionLth < 1
   if ( lContentVersionLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Master Product Content",
      //:             "The Master Product Content Version cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Master Product Content", "The Master Product Content Version cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:IF mMasLC.MasterLabelContent.Version != szContentVersion
      if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "Version", szContentVersion ) != 0 )
      { 

         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mMasLC, "MasterLabelContent", "Version", lControl,
         //:                    szContentVersion, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mMasLC, "MasterLabelContent", "Version", lControl, szContentVersion, "", "", 0, "", "" );
         if ( lTempInteger_1 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Master Product Content",
            //:             "The Master Product Content Version must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Master Product Content", "The Master Product Content Version must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );
         } 


         //:END

         //:mMasLC.MasterLabelContent.Version = szContentVersion
         SetAttributeFromString( mMasLC, "MasterLabelContent", "Version", szContentVersion );
      } 


      //:END
   } 

   //:END

   //:// AcceptSubobject( mMasLC, "MasterLabelContent" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdateMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdateMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:SetMatchingAttributesByName( mMasProd, "MasterLabelContent",
   //:                             mMasLC, "MasterLabelContent", zSET_ALL )
   SetMatchingAttributesByName( mMasProd, "MasterLabelContent", mMasLC, "MasterLabelContent", zSET_ALL );
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );

   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF mPrimReg != 0
   if ( getView( mPrimReg ) != null )
   { 
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
   } 

   //:END

   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "PrimaryRegistrant", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mPrimReg.MasterProduct
   //:    WHERE mPrimReg.MasterProduct.ID = mMasLC.MasterProduct.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mMasLC, "MasterProduct", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterProduct", "ID", lTempInteger_3, "" );
   //:SET CURSOR FIRST mPrimReg.MasterLabelContent
   //:    WHERE mPrimReg.MasterLabelContent.ID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, mMasLC, "MasterLabelContent", "ID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lTempInteger_4, "" );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
CancelNewMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:INTEGER  lPrimaryRegistrantID
   int      lPrimaryRegistrantID = 0;
   //:INTEGER  lMasterProductID
   int      lMasterProductID = 0;
   //:INTEGER  lMasterLabelContentID
   int      lMasterLabelContentID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lPrimaryRegistrantID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lPrimaryRegistrantID = new MutableInt( lPrimaryRegistrantID );
       GetIntegerFromAttribute( mi_lPrimaryRegistrantID, mMasLC, "PrimaryRegistrant", "ID" );
   lPrimaryRegistrantID = mi_lPrimaryRegistrantID.intValue( );}
   //:lMasterProductID = mMasLC.MasterProduct.ID
   {MutableInt mi_lMasterProductID = new MutableInt( lMasterProductID );
       GetIntegerFromAttribute( mi_lMasterProductID, mMasLC, "MasterProduct", "ID" );
   lMasterProductID = mi_lMasterProductID.intValue( );}
   //:lMasterLabelContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lMasterLabelContentID = new MutableInt( lMasterLabelContentID );
       GetIntegerFromAttribute( mi_lMasterLabelContentID, mMasLC, "MasterLabelContent", "ID" );
   lMasterLabelContentID = mi_lMasterLabelContentID.intValue( );}
   //:// CancelSubobject( mMasLC, "MasterLabelContent" )
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lPrimaryRegistrantID
   o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, lPrimaryRegistrantID );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mPrimReg.MasterProduct
   //:    WHERE mPrimReg.MasterProduct.ID = lMasterProductID
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterProduct", "ID", lMasterProductID, "" );
   //:SET CURSOR FIRST mPrimReg.MasterLabelContent
   //:    WHERE mPrimReg.MasterLabelContent.ID = lMasterLabelContentID
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lMasterLabelContentID, "" );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
CancelUpdateMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER  lPrimaryRegistrantID
   int      lPrimaryRegistrantID = 0;
   //:INTEGER  lMasterProductID
   int      lMasterProductID = 0;
   //:INTEGER  lMasterLabelContentID
   int      lMasterLabelContentID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lPrimaryRegistrantID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lPrimaryRegistrantID = new MutableInt( lPrimaryRegistrantID );
       GetIntegerFromAttribute( mi_lPrimaryRegistrantID, mMasLC, "PrimaryRegistrant", "ID" );
   lPrimaryRegistrantID = mi_lPrimaryRegistrantID.intValue( );}
   //:lMasterProductID = mMasLC.MasterProduct.ID
   {MutableInt mi_lMasterProductID = new MutableInt( lMasterProductID );
       GetIntegerFromAttribute( mi_lMasterProductID, mMasLC, "MasterProduct", "ID" );
   lMasterProductID = mi_lMasterProductID.intValue( );}
   //:lMasterLabelContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lMasterLabelContentID = new MutableInt( lMasterLabelContentID );
       GetIntegerFromAttribute( mi_lMasterLabelContentID, mMasLC, "MasterLabelContent", "ID" );
   lMasterLabelContentID = mi_lMasterLabelContentID.intValue( );}
   //:CancelSubobject( mMasLC, "MasterLabelContent" )
   CancelSubobject( mMasLC, "MasterLabelContent" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelUpdateMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelUpdateMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lPrimaryRegistrantID
   o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_0, lPrimaryRegistrantID );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mPrimReg.MasterProduct
   //:    WHERE mPrimReg.MasterProduct.ID = lMasterProductID
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterProduct", "ID", lMasterProductID, "" );
   //:SET CURSOR FIRST mPrimReg.MasterLabelContent
   //:    WHERE mPrimReg.MasterLabelContent.ID = lMasterLabelContentID
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lMasterLabelContentID, "" );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateMasterProduct( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
CancelUpdateMasterProduct( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:// CancelSubobject( mMasProd, "MasterProduct" )
      //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewMasterProduct: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewMasterProduct: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CompareToPreviousMLC( VIEW ViewToWindow )

//:   VIEW mMasProd    REGISTERED AS mMasProd
public int 
CompareToPreviousMLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lMLCATgt    BASED ON LOD  lMLCATgt
   zVIEW    lMLCATgt = new zVIEW( );
   //:VIEW lMLCASrc    BASED ON LOD  lMLCASrc
   zVIEW    lMLCASrc = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasProd, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:ACTIVATE lMLCATgt WHERE lMLCATgt.MasterLabelContent.ID = lID
   o_fnLocalBuildQual_23( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lMLCATgt, "lMLCATgt", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lMLCATgt "lMLCATgt"
   SetNameForView( lMLCATgt, "lMLCATgt", null, zLEVEL_TASK );

   //:IF lMLCATgt.MP_MasterLabelContent DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( lMLCATgt, "MP_MasterLabelContent" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Compare MLC's",
      //:             "The selected MLC is not a source MLC.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Compare MLC's", "The selected MLC is not a source MLC.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( lMLCATgt )
      DropObjectInstance( lMLCATgt );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE lMLCASrc WHERE lMLCASrc.MasterLabelContent.ID = lMLCATgt.MP_MasterLabelContent.ID
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, lMLCATgt, "MP_MasterLabelContent", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      o_fnLocalBuildQual_24( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
      RESULT = ActivateObjectInstance( lMLCASrc, "lMLCASrc", ViewToWindow, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW lMLCASrc "lMLCASrc"
      SetNameForView( lMLCASrc, "lMLCASrc", null, zLEVEL_TASK );
      //:BuildDifferencesMLC( lMLCATgt, lMLCASrc )
      {
       lMLCATgt_Object m_lMLCATgt_Object = new lMLCATgt_Object( lMLCATgt );
       m_lMLCATgt_Object.olMLCATgt_BuildDifferencesMLC( lMLCATgt, lMLCASrc );
       // m_lMLCATgt_Object = null;  // permit gc  (unnecessary)
      }
      //:DropObjectInstance( lMLCASrc )
      DropObjectInstance( lMLCASrc );
      //:nRC = 0
      nRC = 0;
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateMasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateMasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:STRING (  50  ) szProductName
   String   szProductName = null;
   //:STRING (  50  ) szProductNumber
   String   szProductNumber = null;
   //:STRING (  50  ) szVersion
   String   szVersion = null;
   //:INTEGER lProductNameLth
   int      lProductNameLth = 0;
   //:INTEGER lProductNumberLth
   int      lProductNumberLth = 0;
   //:INTEGER lVersionLth
   int      lVersionLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:INTEGER Ignore
   int      Ignore = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:IF mMasProd = 0
   if ( getView( mMasProd ) == null )
   { 
      //:RETURN
      if(8==8)return( 0 );
   } 

   //:END

   //:// Ensure product name is not blank and is unique.
   //:szProductName = wWebXfer.Root.AttemptProductName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szProductName;
   if ( szProductName == null )
      sb_szProductName = new StringBuilder( 32 );
   else
      sb_szProductName = new StringBuilder( szProductName );
       GetVariableFromAttribute( sb_szProductName, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptProductName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szProductName = sb_szProductName.toString( );}
   //:lProductNameLth = zGetStringLen( szProductName )
   lProductNameLth = zGetStringLen( szProductName );
   //:TraceLineS( "Product Name: ", szProductName )
   TraceLineS( "Product Name: ", szProductName );
   //:TraceLineI( "Product Name Length: ", lProductNameLth )
   TraceLineI( "Product Name Length: ", lProductNameLth );
   //:IF lProductNameLth < 1
   if ( lProductNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Master Product",
      //:             "The Master Product Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:IF szProductName != mMasProd.MasterProduct.Name
      if ( CompareAttributeToString( mMasProd, "MasterProduct", "Name", szProductName ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mPrimReg, "MasterProduct", "Name", lControl,
         //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mPrimReg, "MasterProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
         if ( lTempInteger_1 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "New Master Product",
            //:             "The Master Product Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );
         } 


         //:END
      } 

      //:END
   } 

   //:END

   //:// Ensure product number is not blank and is unique.
   //:szProductNumber = wWebXfer.Root.AttemptProductNumber
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szProductNumber;
   if ( szProductNumber == null )
      sb_szProductNumber = new StringBuilder( 32 );
   else
      sb_szProductNumber = new StringBuilder( szProductNumber );
       GetVariableFromAttribute( sb_szProductNumber, mi_lTempInteger_2, 'S', 51, wWebXfer, "Root", "AttemptProductNumber", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szProductNumber = sb_szProductNumber.toString( );}
   //:lProductNumberLth = zGetStringLen( szProductNumber )
   lProductNumberLth = zGetStringLen( szProductNumber );
   //:TraceLineS( "Product Number: ", szProductNumber )
   TraceLineS( "Product Number: ", szProductNumber );
   //:TraceLineI( "Product Number Length: ", lProductNumberLth )
   TraceLineI( "Product Number Length: ", lProductNumberLth );
   //:IF lProductNumberLth < 1
   if ( lProductNumberLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Master Product",
      //:             "The Master Product Number cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Number cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:IF szProductNumber != mMasProd.MasterProduct.Number
      if ( CompareAttributeToString( mMasProd, "MasterProduct", "Number", szProductNumber ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mPrimReg, "MasterProduct", "Number", lControl,
         //:                    szProductNumber, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_3 = SetEntityCursor( mPrimReg, "MasterProduct", "Number", lControl, szProductNumber, "", "", 0, "", "" );
         if ( lTempInteger_3 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "New Master Product",
            //:             "The Master Product Number must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Number must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );
         } 


         //:END
      } 

      //:END
   } 

   //:END

   //:mMasProd.MasterProduct.Name = szProductName
   SetAttributeFromString( mMasProd, "MasterProduct", "Name", szProductName );
   //:mMasProd.MasterProduct.Number = szProductNumber
   SetAttributeFromString( mMasProd, "MasterProduct", "Number", szProductNumber );
   //:// mMasProd.MasterLabelContent.Version = szVersion
   //:// AcceptSubobject( mMasProd, "MasterProduct" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdateMasterProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdateMasterProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );

   //:fnInitListMasterProducts( ViewToWindow, 1 )
   o_fnInitListMasterProducts( ViewToWindow, 1 );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMarketingStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectMarketingStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateMarketingStmt: " )
   //:nRC = AcceptMarketingSect( ViewToWindow )
   nRC = AcceptMarketingSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:// CreateTemporalSubobjectVersion( mMasLC, "M_MarketingStatement" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingStatement", "UpdateMarketingStmt: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingStatement", "UpdateMarketingStmt: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:wWebXfer.Root.CurrentContentType = "M"  // Marketing
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:SelectHazardStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectHazardStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_HazardSection entity. We have
   //:// position on the M_HazardStatement, but need to get position on
   //:// the M_GeneralStatement that corresponds to the M_HazardStatement.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_HazardStatement", "" );
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "UpdateHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "UpdateHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // Environmental/Physical Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectHazardSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
SelectHazardSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectFirstAidStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectFirstAidStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_FirstAidSection entity.  We have
   //:// position on the M_FirstAidStatement, but need to get position on
   //:// the M_GeneralStatement that corresponds to the M_FirstAidStatement.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_FirstAidStatement", "" );
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "UpdateFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "UpdateFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "F"  // FirstAid
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectDirectionsUseStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectDirectionsUseStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateDirectionsUseStmt: " )
   //:nRC = AcceptDirectionsUseSect( ViewToWindow )
   nRC = AcceptDirectionsUseSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:// We need to update the existing M_DirectionsForUseStatement entity.
      //:// CreateTemporalSubobjectVersion( mMasLC, "M_DirectionsForUseStatement" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseStatement", "SelectDirectionsUseStmtForUpdate: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseStatement", "SelectDirectionsUseStmtForUpdate: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:wWebXfer.Root.CurrentContentType = "U"  // DirectionsForUse
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
public int 
SelectDirectionsUseSectForUpdate( View     ViewToWindow )
{

   //:SelectDirectionsUseSectForUpdate( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectDirectionsUseSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectDirectionsUseSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectStorDispStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectStorDispStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalStatement", "UpdateStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalStatement", "UpdateStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectStorDispSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectStorDispSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalSection", "SelectStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalSection", "SelectStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptStorDispStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptStorDispStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptStorDispSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptStorDispSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorageDisposal"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelStorDispStmt( View     ViewToWindow )
{

   //:CancelStorDispStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelStorDispSect( View     ViewToWindow )
{

   //:CancelStorDispSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitStorDispStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_StorageDisposalStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_StorageDisposalStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_StorageDisposalStatement", "InitStorDispStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_StorageDisposalStatement", "InitStorDispStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_StorageDisposalStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_StorageDisposalStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitStorDispStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an M_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalStatement", "InitStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalStatement", "InitStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitStorDispSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new M_StorageDisposalSection entity.
   //:// CreateTemporalEntity( mMasLC, "M_StorageDisposalSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_StorageDisposalSection", "InitStorDispSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_StorageDisposalSection", "InitStorDispSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_StorageDisposalSection.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_StorageDisposalSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitStorDispSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_StorageDisposalSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_StorageDisposalSection", "Title" );

   //:// We need to update the existing M_StorageDisposalSection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalSection", "InitStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalSection", "InitStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispSectForDelete( VIEW ViewToWindow )

public int 
InitStorDispSectForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitStorDispContent( View     ViewToWindow )
{

   //:InitStorDispContent( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewDirectionsUseStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_DirectionsForUseStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_DirectionsForUseStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_DirectionsForUseStatement", "AddNewDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_DirectionsForUseStatement", "AddNewDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_DirectionsForUseStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_DirectionsForUseStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an M_DirectionsForUseStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_DirectionsForUseStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseStatement", "InitDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseStatement", "InitDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF mMasLC.M_DirectionsForUseStatement.NotForUseType = ""
   if ( CompareAttributeToString( mMasLC, "M_DirectionsForUseStatement", "NotForUseType", "" ) == 0 )
   { 
      //:mMasLC.M_DirectionsForUseStatement.NotForUseType = "NA"
      SetAttributeFromString( mMasLC, "M_DirectionsForUseStatement", "NotForUseType", "NA" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:IF mMasLC.M_Usage DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_Usage" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Master Label Content",
      //:             "Organism Claims, Types of Surfaces and Areas of Use should be set up before Directions of Use.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Master Label Content", "Organism Claims, Types of Surfaces and Areas of Use should be set up before Directions of Use.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   } 

   //:END

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_DirectionsForUseSection entity.
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_DirectionsForUseSection", "AddNewDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_DirectionsForUseSection", "AddNewDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_DirectionsForUseSection.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_DirectionsForUseSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitDirectionsUseSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_DirectionsForUseSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_DirectionsForUseSection", "Title" );

   //:// We need to update the existing M_DirectionsForUseSection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_DirectionsForUseSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseSection", "InitDirectionsUseSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseSection", "InitDirectionsUseSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:IF mMasLC.M_DirectionsForUseSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_DirectionsForUseSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:IF MiEntityVersioned( mMasLC, "M_DirectionsForUseSection" ) = 0
      lTempInteger_1 = MiEntityVersioned( mMasLC, "M_DirectionsForUseSection" );
      if ( lTempInteger_1 == 0 )
      { 
         //:// CreateTemporalSubobjectVersion( mMasLC, "M_DirectionsForUseSection" )
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseSection", "InitDirectionsUseSection1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseSection", "InitDirectionsUseSection1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mMasLC, "M_DirectionsForUseSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_DirectionsForUseSection", "InitDirectionsUseSection2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_DirectionsForUseSection", "InitDirectionsUseSection2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_DirectionsForUseSection.BoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_DirectionsForUseSection", "BoldItalic", "R" );
   } 

   //:END

   //:LoadDirectionsUsageList( ViewToWindow, mMasLC )
   o_LoadDirectionsUsageList( ViewToWindow, mMasLC );
   //:wWebXfer.Root.CurrentContentType = "U"  // DirectionsForUse
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseSectForDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitDirectionsUseSectForDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_DirectionsForUseSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_DirectionsForUseSection", "Title" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitDirectionsUseContent( View     ViewToWindow )
{

   //:InitDirectionsUseContent( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanupDirectionsWorkEntities( VIEW mMasLC )

//:   VIEW mTempLC  BASED ON LOD  mMasLC
public int 
CleanupDirectionsWorkEntities( View     mMasLC )
{
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szClaimsClassification
   String   szClaimsClassification = null;
   //:STRING (   1  ) szUsageType
   String   szUsageType = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:GET VIEW mTempLC NAMED "mTempLC"
   RESULT = GetViewByName( mTempLC, "mTempLC", mMasLC, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to traverse M_DirectionsUsageOrdering entities and delete the work sub-entities.
   //:FOR EACH mTempLC.M_DirectionsUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "M_DirectionsUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mTempLC.M_DirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mTempLC, "M_DirectionsUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mTempLC.M_DirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mTempLC, "M_DirectionsUsage", "ClaimsClassification", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringCopy( sb_szClaimsClassification, 1, 0, "Directions", 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringConcat( sb_szClaimsClassification, 1, 0, szTempString_0, 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
         //:nRC = CheckExistenceOfEntity( mTempLC, szClaimsClassification )
         nRC = CheckExistenceOfEntity( mTempLC, szClaimsClassification );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:DeleteEntity( mTempLC, szClaimsClassification, zREPOS_NONE )
            DeleteEntity( mTempLC, szClaimsClassification, zREPOS_NONE );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:nRC = CheckExistenceOfEntity( mTempLC, "DirectionsSurface" )
            nRC = CheckExistenceOfEntity( mTempLC, "DirectionsSurface" );
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:DELETE ENTITY mTempLC.DirectionsSurface
               RESULT = DeleteEntity( mTempLC, "DirectionsSurface", zPOS_NEXT );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:nRC = CheckExistenceOfEntity( mTempLC, "DirectionsAppType" )
               nRC = CheckExistenceOfEntity( mTempLC, "DirectionsAppType" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:DELETE ENTITY mTempLC.DirectionsAppType
                  RESULT = DeleteEntity( mTempLC, "DirectionsAppType", zPOS_NEXT );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:nRC = CheckExistenceOfEntity( mTempLC, "DirectionsAreasOfUse" )
                  nRC = CheckExistenceOfEntity( mTempLC, "DirectionsAreasOfUse" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:DELETE ENTITY mTempLC.DirectionsAreasOfUse
                     RESULT = DeleteEntity( mTempLC, "DirectionsAreasOfUse", zPOS_NEXT );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_DirectionsUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadDirectionsUsageList( VIEW ViewToWindow,
//:                         VIEW mMasLC_In BASED ON LOD mMasLC )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadDirectionsUsageList( View     ViewToWindow,
                           View     mMasLC_In )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC 
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPosLC   BASED ON LOD  mMasLC
   zVIEW    mPosLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING ( 32 ) szClaimsClassification
   String   szClaimsClassification = null;
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:INTEGER  lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:CleanupDirectionsWorkEntities( mMasLC_In )
   CleanupDirectionsWorkEntities( mMasLC_In );

   //:CreateViewFromView( mPosLC, mMasLC_In )
   CreateViewFromView( mPosLC, mMasLC_In );
   //:NAME VIEW mPosLC "mPosLC"
   SetNameForView( mPosLC, "mPosLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mTempLC, mMasLC_In )
   CreateViewFromView( mTempLC, mMasLC_In );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mMasLC, mMasLC_In )
   CreateViewFromView( mMasLC, mMasLC_In );
   //:NAME VIEW mMasLC "mMasLC1"
   SetNameForView( mMasLC, "mMasLC1", null, zLEVEL_TASK );

   //:// Get position on included M_DirectionsUsage entities (which will be marked as selected).
   //:SetCursorFirstEntity( mPosLC, "M_DirectionsUsageOrdering", "" )
   SetCursorFirstEntity( mPosLC, "M_DirectionsUsageOrdering", "" );

   //:// Mark included M_DirectionsUsage entities as "selected" and include M_Usage not
   //:// already included into the M_DirectionsUsage entity and mark as "not selected".
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lID = mMasLC.M_Usage.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mMasLC, "M_Usage", "ID" );
      lID = mi_lID.intValue( );}
      //:SET CURSOR FIRST mTempLC.M_DirectionsUsage WITHIN mTempLC.M_DirectionsForUseSection
      //:                                           WHERE mTempLC.M_DirectionsUsage.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mTempLC, "M_DirectionsUsage", "ID", lID, "M_DirectionsForUseSection" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mPosLC.M_DirectionsUsage WITHIN mPosLC.M_DirectionsForUseSection
         //:                                          WHERE mPosLC.M_DirectionsUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mPosLC, "M_DirectionsUsage", "ID", lID, "M_DirectionsForUseSection" );
         //:mPosLC.M_DirectionsUsage.wkSelected = "Y"
         SetAttributeFromString( mPosLC, "M_DirectionsUsage", "wkSelected", "Y" );
         //:ELSE
      } 
      else
      { 
         //:CreateEntity( mPosLC, "M_DirectionsUsageOrdering", zPOS_AFTER )
         CreateEntity( mPosLC, "M_DirectionsUsageOrdering", zPOS_AFTER );
         //:IncludeSubobjectFromSubobject( mPosLC, "M_DirectionsUsage",
         //:                               mMasLC, "M_Usage", zPOS_NEXT )
         IncludeSubobjectFromSubobject( mPosLC, "M_DirectionsUsage", mMasLC, "M_Usage", zPOS_NEXT );
         //:mPosLC.M_DirectionsUsage.wkSelected = ""
         SetAttributeFromString( mPosLC, "M_DirectionsUsage", "wkSelected", "" );
      } 

      //:END

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mMasLC.M_Usage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mMasLC, "M_Usage", "ClaimsClassification", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringCopy( sb_szClaimsClassification, 1, 0, "Directions", 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringConcat( sb_szClaimsClassification, 1, 0, szTempString_0, 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
         //:CreateEntity( mPosLC, szClaimsClassification, zPOS_AFTER )
         CreateEntity( mPosLC, szClaimsClassification, zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:CreateEntity( mPosLC, "DirectionsSurface", zPOS_AFTER )
            CreateEntity( mPosLC, "DirectionsSurface", zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:CreateEntity( mPosLC, "DirectionsAppType", zPOS_AFTER )
               CreateEntity( mPosLC, "DirectionsAppType", zPOS_AFTER );
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:CreateEntity( mPosLC, "DirectionsAreasOfUse", zPOS_AFTER )
                  CreateEntity( mPosLC, "DirectionsAreasOfUse", zPOS_AFTER );
                  //:ELSE
               } 
               else
               { 
                  //:IssueError( ViewToWindow, 0, 0, "Invalid DirectionsUsage Type" )
                  IssueError( ViewToWindow, 0, 0, "Invalid DirectionsUsage Type" );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 


   //:END

   //:DropView( mPosLC )
   DropView( mPosLC );
   //:DropView( mTempLC )
   DropView( mTempLC );
   //:DropView( mMasLC )
   DropView( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptDirectionsUseStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}

   //:CleanupDirectionsWorkEntities( mMasLC )
   CleanupDirectionsWorkEntities( mMasLC );

   //:GET VIEW mTempLC NAMED "mTempLC"
   RESULT = GetViewByName( mTempLC, "mTempLC", ViewToWindow, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to exclude M_DirectionsUsage entities that are not selected.  In
   //:// the new structure, we need to traverse M_DirectionsUsageOrdering entities
   //:// and delete the work sub-entities.
   //:FOR EACH mTempLC.M_DirectionsUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "M_DirectionsUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF mTempLC.M_DirectionsUsage.wkSelected != "Y"
      if ( CompareAttributeToString( mTempLC, "M_DirectionsUsage", "wkSelected", "Y" ) != 0 )
      { 
         //:// ExcludeEntity( mTempLC, "M_DirectionsUsage", zREPOS_NONE )
         //:DeleteEntity( mTempLC, "M_DirectionsUsageOrdering", zREPOS_NONE )
         DeleteEntity( mTempLC, "M_DirectionsUsageOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_DirectionsUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NextDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
NextDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
   lControl = zPOS_NEXT + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mMasLC, "M_DirectionsForUseSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mMasLC, "M_DirectionsForUseSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Next Directions For Use Section",
      //:             "There is not a next directions for use section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Next Directions For Use Section", "There is not a next directions for use section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mMasLC.M_DirectionsForUseSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mMasLC, "M_DirectionsForUseSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptDirectionsUseSect( ViewToWindow )
      nRC = AcceptDirectionsUseSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mMasLC.M_DirectionsForUseSection
      //:    WHERE mMasLC.M_DirectionsForUseSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DirectionsForUseSection", "ID", lID, "" );
      //:SET CURSOR NEXT mMasLC.M_DirectionsForUseSection
      RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseSection", "NextDirectionsUseSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Next Directions For Use Section",
      //://              "Error saving directions for use section.",
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:PreviousDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
PreviousDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_PREV + zTEST_CSR_RESULT
   lControl = zPOS_PREV + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mMasLC, "M_DirectionsForUseSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mMasLC, "M_DirectionsForUseSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Previous Directions For Use Section",
      //:             "There is not a previous directions for use section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Previous Directions For Use Section", "There is not a previous directions for use section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mMasLC.M_DirectionsForUseSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mMasLC, "M_DirectionsForUseSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptDirectionsUseSect( ViewToWindow )
      nRC = AcceptDirectionsUseSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mMasLC.M_DirectionsForUseSection
      //:    WHERE mMasLC.M_DirectionsForUseSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DirectionsForUseSection", "ID", lID, "" );
      //:SET CURSOR PREVIOUS mMasLC.M_DirectionsForUseSection
      RESULT = SetCursorPrevEntity( mMasLC, "M_DirectionsForUseSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_DirectionsForUseSection", "PreviousDirectionsUseSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Previous Directions For Use Section",
      //://              "Error saving directions for use section.",
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:AcceptAreasOfUseSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptAreasOfUseSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelAreasOfUseSect( View     ViewToWindow )
{

   //:CancelAreasOfUseSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAreasOfUseStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptAreasOfUseStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAppTypesStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptAppTypesStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelAreasOfUseStmt( View     ViewToWindow )
{

   //:CancelAreasOfUseStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDirectionsUseStmt( View     ViewToWindow )
{

   //:CancelDirectionsUseStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptMarketingStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptMarketingStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptMarketingSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptMarketingSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}

   //:CleanupMarketingWorkEntities( mMasLC )
   CleanupMarketingWorkEntities( mMasLC );

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to exclude M_MarketingUsage entities that are not selected.  In
   //:// the new structure, we need to traverse M_MarketingUsageOrdering entities
   //:// and delete the work sub-entities.
   //:FOR EACH mTempLC.M_MarketingUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "M_MarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF mTempLC.M_MarketingUsage.wkSelected != "Y"
      if ( CompareAttributeToString( mTempLC, "M_MarketingUsage", "wkSelected", "Y" ) != 0 )
      { 
         //:// ExcludeEntity( mTempLC, "M_MarketingUsage", zREPOS_NONE )
         //:DeleteEntity( mTempLC, "M_MarketingUsageOrdering", zREPOS_NONE )
         DeleteEntity( mTempLC, "M_MarketingUsageOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_MarketingUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NextMarketingSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
NextMarketingSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
   lControl = zPOS_NEXT + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mMasLC, "M_MarketingSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mMasLC, "M_MarketingSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Next Marketing Section",
      //:             "There is not a next marketing section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Next Marketing Section", "There is not a next marketing section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mMasLC.M_MarketingSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mMasLC, "M_MarketingSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptMarketingSect( ViewToWindow )
      nRC = AcceptMarketingSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mMasLC.M_MarketingSection
      //:    WHERE mMasLC.M_MarketingSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingSection", "ID", lID, "" );
      //:SET CURSOR NEXT mMasLC.M_MarketingSection
      RESULT = SetCursorNextEntity( mMasLC, "M_MarketingSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingSection", "NextMarketingSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Next Marketing Section",
      //://              "Error saving marketing section.",
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:PreviousMarketingSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
PreviousMarketingSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_PREV + zTEST_CSR_RESULT
   lControl = zPOS_PREV + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mMasLC, "M_MarketingSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mMasLC, "M_MarketingSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Previous Marketing Section",
      //:             "There is not a previous marketing section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Previous Marketing Section", "There is not a previous marketing section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mMasLC.M_MarketingSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mMasLC, "M_MarketingSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptMarketingSect( ViewToWindow )
      nRC = AcceptMarketingSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mMasLC.M_MarketingSection
      //:    WHERE mMasLC.M_MarketingSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingSection", "ID", lID, "" );
      //:SET CURSOR PREVIOUS mMasLC.M_MarketingSection
      RESULT = SetCursorPrevEntity( mMasLC, "M_MarketingSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingSection", "PrevMarketingSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Previous Marketing Section",
      //://              "Error saving marketing section.",
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
public int 
CancelMarketingStmt( View     ViewToWindow )
{

   //:CancelMarketingStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelMarketingSect( View     ViewToWindow )
{

   //:CancelMarketingSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   return( 0 );
// /*
// IssueError( ViewToWindow, 0, 0, "InitMarketingStmtForUpdate" )
//    AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForUpdate: " )
//    // We need to update an M_MarketingStatement entity.
// // CreateTemporalSubobjectVersion( mMasLC, "M_MarketingStatement" )
//    CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingStatement", "InitMarketingStmtForUpdate: " )
//    wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
// */
// END
} 


//:DIALOG OPERATION
//:AddNewMarketingSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewMarketingSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:IF mMasLC.M_Usage DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_Usage" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Master Label Content",
      //:             "Organism Claims, Types of Surfaces and Areas of Use should be set up before Marketing sections.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Master Label Content", "Organism Claims, Types of Surfaces and Areas of Use should be set up before Marketing sections.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   } 

   //:END

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new M_MarketingSection entity.
   //:// CreateTemporalEntity( mMasLC, "M_MarketingSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_MarketingSection", "AddNewMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_MarketingSection", "AddNewMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_MarketingSection.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_MarketingSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitMarketingSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_MarketingSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_MarketingSection", "Title" );

   //:// We need to update the existing M_MarketingSection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_MarketingSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingSection", "InitMarketingSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingSection", "InitMarketingSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mMasLC.M_MarketingSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_MarketingSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mMasLC, "M_MarketingSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingSection", "InitMarketingSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_MarketingSection", "InitMarketingSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mMasLC, "M_MarketingSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_MarketingSection", "InitMarketingSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_MarketingSection", "InitMarketingSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_MarketingSection.BoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_MarketingSection", "BoldItalic", "R" );
   } 

   //:END

   //:LoadMarketingUsageList( ViewToWindow, mMasLC )
   o_LoadMarketingUsageList( ViewToWindow, mMasLC );
   //:wWebXfer.Root.CurrentContentType = "M"  // Marketing
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterProductForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterProductForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END

   //:ACTIVATE mMasProd EMPTY
   RESULT = ActivateEmptyObjectInstance( mMasProd, "mMasProd", ViewToWindow, zSINGLE );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:// CreateTemporalEntity( mMasProd, "MasterProduct", zPOS_FIRST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_FIRST, "mMasProd", "MasterProduct", "InitMasterProductForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_FIRST, "mMasProd", "MasterProduct", "InitMasterProductForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:mMasProd.MasterProduct.ChemicalFamily = "DQ" // Quat Disinfectant
   SetAttributeFromString( mMasProd, "MasterProduct", "ChemicalFamily", "DQ" );
   //:wWebXfer.Root.AttemptProductName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductName", "" );
   //:wWebXfer.Root.AttemptProductNumber = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductNumber", "" );
   //:// wWebXfer.Root.AttemptContentVersion = "Please specify content version"

   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterProductForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterProductForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END

   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = mPrimReg.MasterProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mPrimReg, "MasterProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptProductName = mMasProd.MasterProduct.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductName", mMasProd, "MasterProduct", "Name" );
   //:wWebXfer.Root.AttemptProductNumber = mMasProd.MasterProduct.Number
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductNumber", mMasProd, "MasterProduct", "Number" );
   //:// wWebXfer.Root.AttemptContentVersion = mMasProd.MasterLabelContent.Version

   //:// CreateTemporalSubobjectVersion( mMasProd, "MasterProduct" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasProd", "MasterProduct", "InitMasterProductForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasProd", "MasterProduct", "InitMasterProductForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );
   //:OrderEntityForView( mMasProd, "MasterLabelContent", "CreatedDateTime D" )
   OrderEntityForView( mMasProd, "MasterLabelContent", "CreatedDateTime D" );

   //:// wWebXfer.Root.Banner1 = qOrganiz.Organization.dLoginUserName
   //:// wWebXfer.Root.Banner2 = qOrganiz.Organization.dLoginUserRole
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner4", "" );
   //:wWebXfer.Root.Banner5 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner5", "" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptClaimsStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptClaimsStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptClaimsSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptClaimsSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelClaimsStmt( View     ViewToWindow )
{

   //:CancelClaimsStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelClaimsSect( View     ViewToWindow )
{

   //:CancelClaimsSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelAppTypesSect( View     ViewToWindow )
{

   //:CancelAppTypesSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingSectForDelete( VIEW ViewToWindow )

public int 
InitMarketingSectForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelAppTypesStmt( View     ViewToWindow )
{

   //:CancelAppTypesStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDirectionsUseSect( View     ViewToWindow )
{

   //:CancelDirectionsUseSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewClaimsStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewClaimsStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_Usage entity.
   //:// CreateTemporalEntity( mMasLC, "M_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_Usage.UsageType = "C" // "Claim"
   SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "C" );
   //:mMasLC.M_Usage.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewAreasOfUseStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewAreasOfUseStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_Usage entity.
   //:// CreateTemporalEntity( mMasLC, "M_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_Usage.UsageType = "U" // "AreasOfUse"
   SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "U" );
   //:mMasLC.M_Usage.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAreasOfUseStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAreasOfUseStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (AreasOfUse) entity. We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (AreasOfUse) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );

   //:// We need to update an M_Usage entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "InitAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "InitAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewAppTypesStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewAppTypesStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_Usage entity.
   //:// CreateTemporalEntity( mMasLC, "M_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_Usage.UsageType = "T" // "AppTypes"
   SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "T" );
   //:mMasLC.M_Usage.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "T"  // "AppTypes"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectAreasOfUseStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectAreasOfUseStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_Usage (AreasOfUse) entity. We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (AreasOfUse) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );

   //:// We need to update an M_Usage entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectAppTypesStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectAppTypesStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_Usage (AppTypes) entity.  We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (AppTypes) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );

   //:// We need to update an M_Usage entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectAppTypesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectAppTypesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "T"  // "AppTypes"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitClaimsSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitClaimsSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.MI_UsageList
   RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
      ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mMasLC.MI_UsageList
      RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:// We need to create MI_UsageList entities.
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.UsageType = "C" // "Claim"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "UsageType", "C" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList",
         //:                               mMasLC, "M_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList", mMasLC, "M_Usage", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentStatementText = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatementText", "" );
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectClaimsStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectClaimsStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_Usage (Claims) entity.  We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (Claims) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );

   //:// We need to update an M_Usage entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectClaimsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectClaimsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSurfacesSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSurfacesSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.MI_UsageList
   RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
      ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mMasLC.MI_UsageList
      RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:// We need to create MI_UsageList entities.
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.UsageType = "S" // "Surface"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "UsageType", "S" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList",
         //:                               mMasLC, "M_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList", mMasLC, "M_Usage", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentStatementText = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatementText", "" );
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAppTypesSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAppTypesSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.MI_UsageList
   RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
      ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mMasLC.MI_UsageList
      RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:// We need to create MI_UsageList entities.
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.UsageType = "T" // "AppTypes"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "UsageType", "T" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList",
         //:                               mMasLC, "M_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList", mMasLC, "M_Usage", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentStatementText = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatementText", "" );
   //:wWebXfer.Root.CurrentContentType = "T"  // "AppTypes"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSurfacesStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptSurfacesStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSurfacesSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptSurfacesSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAppTypesSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptAppTypesSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelSurfacesStmt( View     ViewToWindow )
{

   //:CancelSurfacesStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelSurfacesSect( View     ViewToWindow )
{

   //:CancelSurfacesSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewSurfacesStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewSurfacesStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_Usage entity.
   //:// CreateTemporalEntity( mMasLC, "M_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_Usage", "AddNewSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_Usage.UsageType = "S" // "Surface"
   SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "S" );
   //:mMasLC.M_Usage.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectSurfacesStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectSurfacesStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_Usage (Surfaces) entity.  We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (Surfaces) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );

   //:// We need to update an M_Usage entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectSurfacesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_Usage", "SelectSurfacesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptHumanHazardSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptHumanHazardSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelHumanHazardSect( View     ViewToWindow )
{

   //:CancelHumanHazardSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionaryStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrecautionaryStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", szTempString_0, "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:GetStrFromAttrByContext( szSectionType, 33, wWebXfer,
      //:                         "Root", "CurrentContentType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( wWebXfer );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, wWebXfer, "Root", "CurrentContentType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szTitle = "New " + szSectionType + " Statement"
       {StringBuilder sb_szTitle;
      if ( szTitle == null )
         sb_szTitle = new StringBuilder( 32 );
      else
         sb_szTitle = new StringBuilder( szTitle );
            ZeidonStringCopy( sb_szTitle, 1, 0, "New ", 1, 0, 257 );
      szTitle = sb_szTitle.toString( );}
       {StringBuilder sb_szTitle;
      if ( szTitle == null )
         sb_szTitle = new StringBuilder( 32 );
      else
         sb_szTitle = new StringBuilder( szTitle );
            ZeidonStringConcat( sb_szTitle, 1, 0, szSectionType, 1, 0, 257 );
      szTitle = sb_szTitle.toString( );}
       {StringBuilder sb_szTitle;
      if ( szTitle == null )
         sb_szTitle = new StringBuilder( 32 );
      else
         sb_szTitle = new StringBuilder( szTitle );
            ZeidonStringConcat( sb_szTitle, 1, 0, " Statement", 1, 0, 257 );
      szTitle = sb_szTitle.toString( );}
      //:szMessage = "The " + szSectionType + " Section does not exist"
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringCopy( sb_szMessage, 1, 0, "The ", 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringConcat( sb_szMessage, 1, 0, szSectionType, 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringConcat( sb_szMessage, 1, 0, " Section does not exist", 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
      //:MessageSend( ViewToWindow, "", szTitle,
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", szTitle, szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionaryStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionaryStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_GeneralStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralStatement", "InitPrecautionaryStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralStatement", "InitPrecautionaryStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_GeneralStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionaryStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrecautionaryStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "P" // "Precautionary"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "P", "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Precautionary Statement",
      //:             "The Precautionary Section does not exist",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Precautionary Statement", "The Precautionary Section does not exist", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an M_GeneralStatement entity.
   //:TraceLineS( "InitPrecautionaryStmtForUpdate: ", "M_GeneralStatement" )
   TraceLineS( "InitPrecautionaryStmtForUpdate: ", "M_GeneralStatement" );
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "InitPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "InitPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionarySect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrecautionarySect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "P" // Precautionary
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "P", "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:// We get here the first time into the section ... need to create Precautionary Section entity.
      //:// CreateEntity( mMasLC, "M_GeneralSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitPrecautionarySect: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitPrecautionarySect: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_GeneralSection.SectionType = "P" // Precautionary
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "P" );
      //:mMasLC.M_GeneralSection.BoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_GeneralSection", "BoldItalic", "R" );
      //:ELSE
   } 
   else
   { 
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitPrecautionarySect: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitPrecautionarySect: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:// Guarantee we maintain position in mMasLC.
   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:// LOOP WHILE mTempLC.MI_PrecautionarySection EXISTS
   //://    ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE )
   //:// END

   //:SET CURSOR FIRST mTempLC.MI_PrecautionarySection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_PrecautionarySection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_PrecautionarySection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_PrecautionarySection", "" );
   } 

   //:END

   //:SET CURSOR FIRST mTempLC.MI_HazardSection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_HazardSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_HazardSection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_HazardSection", "" );
   } 

   //:END

   //:SET CURSOR FIRST mTempLC.MI_FirstAidSection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_FirstAidSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_FirstAidSection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_FirstAidSection", "" );
   } 

   //:END

   //:FOR EACH mTempLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( mTempLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mTempLC.M_GeneralSection.SectionType = "P" // Precautionary
      if ( CompareAttributeToString( mTempLC, "M_GeneralSection", "SectionType", "P" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mTempLC, "MI_PrecautionarySection",
         //:                               mTempLC, "M_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mTempLC, "MI_PrecautionarySection", mTempLC, "M_GeneralSection", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_GeneralSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MI_PrecautionarySection", "InitPrecautionarySect: " )
      //:END
   } 

   //:END
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHazardStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:/*
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   //:IF RESULT < 0
   //:   GetStrFromAttrByContext( szSectionType, 33, wWebXfer,
   //:                            "Root", "CurrentContentType", "ContentSectionType" )
   //:   szTitle = "New " + szSectionType + " Statement"
   //:   szMessage = "The " + szSectionType + " Section does not exist"
   //:   MessageSend( ViewToWindow, "", szTitle,
   //:                szMessage,
   //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:   SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //:   RETURN 2
   //:END
   //:*/
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_GeneralStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralStatement", "InitHazardStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralStatement", "InitHazardStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_GeneralStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHazardStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   //:/*
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "E" // "Environmental/Physical Hazard"
   //:IF RESULT < 0
   //:   MessageSend( ViewToWindow, "", "Update Hazard Statement",
   //:                "The Environmental/Physical Hazard Section does not exist",
   //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:   SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //:   RETURN 2
   //:END
   //:*/
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_GeneralStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "InitHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "InitHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHazardSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:/* We have already achieved position on the correct Hazard section!!!
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
   //:IF RESULT < 0
   //:   // We get here the first time into the section ... need to create Environmental/Physical Hazard Section entity.
   //:// CreateEntity( mMasLC, "M_GeneralSection", zPOS_LAST )
   //:   CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitHazardSect: " )
   //:   mMasLC.M_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
   //:   mMasLC.M_GeneralSection.BoldItalic = "R"
   //:ELSE
   //:   CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitHazardSect: " )
   //:END
   //:*/
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// Guarantee we maintain position in mMasLC.
   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:// LOOP WHILE mTempLC.MI_HazardSection EXISTS
   //://    ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE )
   //:// END

   //:SET CURSOR FIRST mTempLC.MI_PrecautionarySection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_PrecautionarySection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_PrecautionarySection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_PrecautionarySection", "" );
   } 

   //:END

   //:SET CURSOR FIRST mTempLC.MI_HazardSection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_HazardSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_HazardSection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_HazardSection", "" );
   } 

   //:END

   //:SET CURSOR FIRST mTempLC.MI_FirstAidSection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_FirstAidSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_FirstAidSection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_FirstAidSection", "" );
   } 

   //:END

   //:FOR EACH mTempLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( mTempLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mTempLC.M_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
      if ( CompareAttributeToString( mTempLC, "M_GeneralSection", "SectionType", "E" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mTempLC, "MI_HazardSection",
         //:                               mTempLC, "M_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mTempLC, "MI_HazardSection", mTempLC, "M_GeneralSection", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_GeneralSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MI_HazardSection", "InitHazardSect: " )
      //:END
   } 

   //:END
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:wWebXfer.Root.CurrentContentType = "E"  // Environmental/Physical Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardContent( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
InitHazardContent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.MI_HazardSection
   RESULT = SetCursorFirstEntity( mMasLC, "MI_HazardSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mMasLC, "MI_HazardSection", zREPOS_NONE )
      ExcludeEntity( mMasLC, "MI_HazardSection", zREPOS_NONE );
      //:SET CURSOR FIRST mMasLC.MI_HazardSection
      RESULT = SetCursorFirstEntity( mMasLC, "MI_HazardSection", "" );
   } 

   //:END

   //:FOR EACH mMasLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
      if ( CompareAttributeToString( mMasLC, "M_GeneralSection", "SectionType", "E" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mMasLC, "MI_HazardSection",
         //:                               mMasLC, "M_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mMasLC, "MI_HazardSection", mMasLC, "M_GeneralSection", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_GeneralSection", "" );
      //:END
   } 

   //:END
   return( 0 );
// //?CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MasterLabelContent", "InitHazardContent: " )
// END
} 


//:DIALOG OPERATION
//:AddNewFirstAidStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewFirstAidStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", szTempString_0, "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:GetStrFromAttrByContext( szSectionType, 33, wWebXfer,
      //:                         "Root", "CurrentContentType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( wWebXfer );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, wWebXfer, "Root", "CurrentContentType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szTitle = "New " + szSectionType + " Statement"
       {StringBuilder sb_szTitle;
      if ( szTitle == null )
         sb_szTitle = new StringBuilder( 32 );
      else
         sb_szTitle = new StringBuilder( szTitle );
            ZeidonStringCopy( sb_szTitle, 1, 0, "New ", 1, 0, 257 );
      szTitle = sb_szTitle.toString( );}
       {StringBuilder sb_szTitle;
      if ( szTitle == null )
         sb_szTitle = new StringBuilder( 32 );
      else
         sb_szTitle = new StringBuilder( szTitle );
            ZeidonStringConcat( sb_szTitle, 1, 0, szSectionType, 1, 0, 257 );
      szTitle = sb_szTitle.toString( );}
       {StringBuilder sb_szTitle;
      if ( szTitle == null )
         sb_szTitle = new StringBuilder( 32 );
      else
         sb_szTitle = new StringBuilder( szTitle );
            ZeidonStringConcat( sb_szTitle, 1, 0, " Statement", 1, 0, 257 );
      szTitle = sb_szTitle.toString( );}
      //:szMessage = "The " + szSectionType + " Section does not exist"
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringCopy( sb_szMessage, 1, 0, "The ", 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringConcat( sb_szMessage, 1, 0, szSectionType, 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringConcat( sb_szMessage, 1, 0, " Section does not exist", 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
      //:MessageSend( ViewToWindow, "", szTitle,
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", szTitle, szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_GeneralStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralStatement", "AddNewFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralStatement", "AddNewFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_GeneralStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidStmtForUpdate( VIEW ViewToWindow )

public int 
InitFirstAidStmtForUpdate( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitFirstAidSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING ( 1024 ) szNote
   String   szNote = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "F" // FirstAid
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "F", "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:// We get here the first time into the section ... need to create FirstAid Section entity.
      //:// CreateEntity( mMasLC, "M_GeneralSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitFirstAidSection: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitFirstAidSection: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_GeneralSection.SectionType = "F" // FirstAid
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "F" );
      //:mMasLC.M_GeneralSection.BoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_GeneralSection", "BoldItalic", "R" );
      //:mMasLC.M_GeneralSection.Title = "FIRST AID"
      SetAttributeFromString( mMasLC, "M_GeneralSection", "Title", "FIRST AID" );

      //:szNote = "Call a poison control center or doctor for treatment advice. Have the product container or label with you when "
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringCopy( sb_szNote, 1, 0, "Call a poison control center or doctor for treatment advice. Have the product container or label with you when ", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "calling a poison control center or doctor, or going for treatment. You may also contact 1-800-xxx-xxxx "
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "calling a poison control center or doctor, or going for treatment. You may also contact 1-800-xxx-xxxx ", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "for emergency medical treatment information." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "for emergency medical treatment information.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "[Toll free phone number language may be modified to fit company's policy. Suggestions follow:]" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "[Toll free phone number language may be modified to fit company's policy. Suggestions follow:]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "[National Pesticide Information Center (NPIC) - 1-800-858-7378]" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "[National Pesticide Information Center (NPIC) - 1-800-858-7378]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "[American Association of Poison Control Centers - 1-800-222-1222]" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "[American Association of Poison Control Centers - 1-800-222-1222]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "[Animal Poison Control Center (APCC) - 1-888-426-4435]"
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "[Animal Poison Control Center (APCC) - 1-888-426-4435]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:mMasLC.M_GeneralSection.ContactNote = szNote
      SetAttributeFromString( mMasLC, "M_GeneralSection", "ContactNote", szNote );

      //:szNote = "[" + zQUOTES + "NOTE TO PHYSICIAN"
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringCopy( sb_szNote, 1, 0, "[", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zQUOTES, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "NOTE TO PHYSICIAN", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + zQUOTES + " is required in the following instances]" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zQUOTES, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, " is required in the following instances]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "Probable mucosal damage may contraindicate the use of gastric lavage." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "Probable mucosal damage may contraindicate the use of gastric lavage.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   [Only required if Toxicity Category I eye and skin irritants]" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   [Only required if Toxicity Category I eye and skin irritants]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "Contains petroleum distillate - vomiting may cause aspiration pneumonia." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "Contains petroleum distillate - vomiting may cause aspiration pneumonia.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   [Only required if product contains $10% petroleum distillate]" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   [Only required if product contains $10% petroleum distillate]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "[Notes may also address the following information; as appropriate:" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "[Notes may also address the following information; as appropriate:", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "- technical information on symptoms;" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "- technical information on symptoms;", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "- use of supportive treatments to maintain life functions;" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "- use of supportive treatments to maintain life functions;", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "- medicine that will counteract the specific physiological effects of the pesticide;" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "- medicine that will counteract the specific physiological effects of the pesticide;", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "- company telephone number to specific medical personnel who can provide specialized medical advice.]"
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "- company telephone number to specific medical personnel who can provide specialized medical advice.]", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:mMasLC.M_GeneralSection.NoteToPhysician = szNote
      SetAttributeFromString( mMasLC, "M_GeneralSection", "NoteToPhysician", szNote );

      //:CREATE ENTITY mMasLC.M_GeneralStatement
      RESULT = CreateEntity( mMasLC, "M_GeneralStatement", zPOS_AFTER );
      //:szNote = "If in eyes" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringCopy( sb_szNote, 1, 0, "If in eyes", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Hold eye open and rinse slowly and gently with water for 15-20 minutes." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Hold eye open and rinse slowly and gently with water for 15-20 minutes.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Remove contact lenses, if present, after the first 5 minutes, then continue rinsing eye."
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Remove contact lenses, if present, after the first 5 minutes, then continue rinsing eye.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:mMasLC.M_GeneralStatement.Text = szNote
      SetAttributeFromString( mMasLC, "M_GeneralStatement", "Text", szNote );

      //:CREATE ENTITY mMasLC.M_GeneralStatement
      RESULT = CreateEntity( mMasLC, "M_GeneralStatement", zPOS_AFTER );
      //:szNote = "If swallowed" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringCopy( sb_szNote, 1, 0, "If swallowed", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Call a poison control center or doctor immediately for treatment advice." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Call a poison control center or doctor immediately for treatment advice.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Have person sip a glass of water if able to swallow." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Have person sip a glass of water if able to swallow.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Do not induce vomiting unless told to do so by the poison control center or doctor." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Do not induce vomiting unless told to do so by the poison control center or doctor.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Do not give anything by mouth to an unconscious person."
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Do not give anything by mouth to an unconscious person.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:mMasLC.M_GeneralStatement.Text = szNote
      SetAttributeFromString( mMasLC, "M_GeneralStatement", "Text", szNote );

      //:CREATE ENTITY mMasLC.M_GeneralStatement
      RESULT = CreateEntity( mMasLC, "M_GeneralStatement", zPOS_AFTER );
      //:szNote = "If on skin or clothing" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringCopy( sb_szNote, 1, 0, "If on skin or clothing", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Take off contaminated clothing." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Take off contaminated clothing.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Rinse skin immediately with plenty of water for 15-20 minutes."
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Rinse skin immediately with plenty of water for 15-20 minutes.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:mMasLC.M_GeneralStatement.Text = szNote
      SetAttributeFromString( mMasLC, "M_GeneralStatement", "Text", szNote );

      //:CREATE ENTITY mMasLC.M_GeneralStatement
      RESULT = CreateEntity( mMasLC, "M_GeneralStatement", zPOS_AFTER );
      //:szNote = "If inhaled" + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringCopy( sb_szNote, 1, 0, "If inhaled", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - Move person to fresh air." + zNEW_LINE
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - Move person to fresh air.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, zNEW_LINE, 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + "   - If person is not breathing, call 911 or an ambulance, then give artificial respiration,"
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, "   - If person is not breathing, call 911 or an ambulance, then give artificial respiration,", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:szNote = szNote + " preferably by mouth-to-mouth, if possible."
       {StringBuilder sb_szNote;
      if ( szNote == null )
         sb_szNote = new StringBuilder( 32 );
      else
         sb_szNote = new StringBuilder( szNote );
            ZeidonStringConcat( sb_szNote, 1, 0, " preferably by mouth-to-mouth, if possible.", 1, 0, 1025 );
      szNote = sb_szNote.toString( );}
      //:mMasLC.M_GeneralStatement.Text = szNote
      SetAttributeFromString( mMasLC, "M_GeneralStatement", "Text", szNote );

      //:ELSE
   } 
   else
   { 
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitFirstAidSect: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitFirstAidSect: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:// Guarantee we maintain position in mMasLC.
   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:// LOOP WHILE mTempLC.MI_FirstAidSection EXISTS
   //://    ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE )
   //:// END

   //:SET CURSOR FIRST mTempLC.MI_PrecautionarySection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_PrecautionarySection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_PrecautionarySection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_PrecautionarySection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_PrecautionarySection", "" );
   } 

   //:END

   //:SET CURSOR FIRST mTempLC.MI_HazardSection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_HazardSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_HazardSection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_HazardSection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_HazardSection", "" );
   } 

   //:END

   //:SET CURSOR FIRST mTempLC.MI_FirstAidSection
   RESULT = SetCursorFirstEntity( mTempLC, "MI_FirstAidSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE )
      ExcludeEntity( mTempLC, "MI_FirstAidSection", zREPOS_NONE );
      //:SET CURSOR FIRST mTempLC.MI_FirstAidSection
      RESULT = SetCursorFirstEntity( mTempLC, "MI_FirstAidSection", "" );
   } 

   //:END

   //:FOR EACH mTempLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( mTempLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mTempLC.M_GeneralSection.SectionType = "F" // FirstAid
      if ( CompareAttributeToString( mTempLC, "M_GeneralSection", "SectionType", "F" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mTempLC, "MI_FirstAidSection",
         //:                               mTempLC, "M_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mTempLC, "MI_FirstAidSection", mTempLC, "M_GeneralSection", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_GeneralSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MI_FirstAidSection", "InitFirstAidSect: " )
      //:END
   } 

   //:END
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:wWebXfer.Root.CurrentContentType = "F"  // FirstAid
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitFirstAidSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new M_FirstAidSection entity.
   //:// CreateTemporalEntity( mMasLC, "M_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitFirstAidSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitFirstAidSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAreasOfUseSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAreasOfUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mMasLC.MI_UsageList
   RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
      ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mMasLC.MI_UsageList
      RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:// We need to create MI_UsageList (AreasOfUse) entities.
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.UsageType = "U" // "AreasOfUse"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "UsageType", "U" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList",
         //:                               mMasLC, "M_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList", mMasLC, "M_Usage", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );

      //:   // We do not create a temporal UsageList entity.
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MI_UsageList", "InitAreasOfUseSect1: " )
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentStatementText = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatementText", "" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHumanHazardSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHumanHazardSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mMasLC.M_HumanHazardSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_HumanHazardSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mMasLC, "M_HumanHazardSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_HumanHazardSection", "InitHumanHazardSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_HumanHazardSection", "InitHumanHazardSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mMasLC, "M_HumanHazardSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_HumanHazardSection", "InitHumanHazardSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_HumanHazardSection", "InitHumanHazardSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_HumanHazardSection.PrecautionaryStatement = "See {{Precautionary Position}} Panel for Precautionary Statements"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "PrecautionaryStatement", "See {{Precautionary Position}} Panel for Precautionary Statements" );
      //:mMasLC.M_HumanHazardSection.Location1 = "Back"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "Location1", "Back" );
      //:mMasLC.M_HumanHazardSection.Location2 = "Side"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "Location2", "Side" );
      //:mMasLC.M_HumanHazardSection.Location3 = "Left"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "Location3", "Left" );
      //:mMasLC.M_HumanHazardSection.Location4 = "Right"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "Location4", "Right" );
      //:mMasLC.M_HumanHazardSection.BoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "BoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "H"  // Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "H" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitIngredientsStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.FormValidationDLL = ""
   SetAttributeFromString( wWebXfer, "Root", "FormValidationDLL", "" );
   //:wWebXfer.Root.FormValidationOperation = ""
   SetAttributeFromString( wWebXfer, "Root", "FormValidationOperation", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "InitIngredientsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "InitIngredientsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_IngredientsStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_IngredientsStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_IngredientsStatement", "InitIngredientsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_IngredientsStatement", "InitIngredientsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_IngredientsStatement.Active = "A"
   SetAttributeFromString( mMasLC, "M_IngredientsStatement", "Active", "A" );
   //:mMasLC.M_IngredientsStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_IngredientsStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitIngredientsSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mMasLC.M_IngredientsSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_IngredientsSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mMasLC, "M_IngredientsSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsSection", "InitIngredientsSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsSection", "InitIngredientsSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mMasLC, "M_IngredientsSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_IngredientsSection", "InitIngredientsSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_IngredientsSection", "InitIngredientsSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_IngredientsSection.ActiveBoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_IngredientsSection", "ActiveBoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   //:wWebXfer.Root.FormValidationDLL = "wMLC"
   SetAttributeFromString( wWebXfer, "Root", "FormValidationDLL", "wMLC" );
   //:wWebXfer.Root.FormValidationOperation = "ValidateIngredientsSection"
   SetAttributeFromString( wWebXfer, "Root", "FormValidationOperation", "ValidateIngredientsSection" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitIngredientsStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.FormValidationDLL = ""
   SetAttributeFromString( wWebXfer, "Root", "FormValidationDLL", "" );
   //:wWebXfer.Root.FormValidationOperation = ""
   SetAttributeFromString( wWebXfer, "Root", "FormValidationOperation", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an M_IngredientsStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_IngredientsStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsStatement", "InitIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsStatement", "InitIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptIngredientsStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptIngredientsStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptIngredientsSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptIngredientsSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = ValidateIngredientsSect( ViewToWindow )
   nRC = ValidateIngredientsSect( ViewToWindow );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   //:wWebXfer.Root.FormValidationOperation = ""
   SetAttributeFromString( wWebXfer, "Root", "FormValidationOperation", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectIngredientsStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectIngredientsStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateIngredientsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateIngredientsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_IngredientsStatement entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_IngredientsStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsStatement", "UpdateIngredientsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsStatement", "UpdateIngredientsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelIngredientsStmt( View     ViewToWindow )
{

   //:CancelIngredientsStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelIngredientsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelIngredientsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptHazardStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptHazardStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptFirstAidStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptFirstAidStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptFirstAidSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptFirstAidSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 
      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelHazardSect( View     ViewToWindow )
{

   //:CancelHazardSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptHazardSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptHazardSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 
      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelFirstAidStmt( View     ViewToWindow )
{

   //:CancelFirstAidStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelFirstAidSect( View     ViewToWindow )
{

   //:CancelFirstAidSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelFirstAidSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelFirstAidSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectPrecautionaryStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectPrecautionaryStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdatePrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdatePrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing M_PrecautionarySection entity.  We have
   //:// position on the M_PrecautionaryStatement, but need to get position on
   //:// the M_GeneralStatement that corresponds to the M_PrecautionaryStatement.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_PrecautionaryStatement", "" );
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "UpdatePrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralStatement", "UpdatePrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateMasterProduct( VIEW ViewToWindow )

public int 
UpdateMasterProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
public int 
InitListMasterProducts( View     ViewToWindow )
{

   //:InitListMasterProducts( VIEW ViewToWindow )

   //:fnInitListMasterProducts( ViewToWindow, 0 )
   o_fnInitListMasterProducts( ViewToWindow, 0 );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditHazardSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditHazardSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitHazardSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHazardSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new M_GeneralSection entity.
   //:// CreateTemporalEntity( mMasLC, "M_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitHazardSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitHazardSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_GeneralSection", "BoldItalic", "R" );
   //:mMasLC.M_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
   SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "E" );
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitHazardSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_GeneralSection", "Title" );

   //:// We need to update the existing M_GeneralSection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitHazardSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditPrecautionarySect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditPrecautionarySect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionarySectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrecautionarySectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new M_PrecautionarySection entity.
   //:// CreateTemporalEntity( mMasLC, "M_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitPrecautionarySectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_GeneralSection", "InitPrecautionarySectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionarySectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitPrecautionarySectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_GeneralSection", "Title" );

   //:// We need to update the existing M_PrecautionarySection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitPrecautionarySectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitPrecautionarySectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditFirstAidSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditFirstAidSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditFirstAidSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditFirstAidSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:AddNewMarketingStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewMarketingStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddNewMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new M_MarketingStatement entity.
   //:// CreateTemporalEntity( mMasLC, "M_MarketingStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_MarketingStatement", "AddNewMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_MarketingStatement", "AddNewMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_MarketingStatement.BoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_MarketingStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitFirstAidSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_GeneralSection", "Title" );

   //:// We need to update the existing M_FirstAidSection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitFirstAidSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_GeneralSection", "InitFirstAidSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditIngredientsSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditIngredientsSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditIngredientsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditIngredientsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitIngredientsSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new M_IngredientsSection entity.
   //:// CreateTemporalEntity( mMasLC, "M_IngredientsSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_IngredientsSection", "InitIngredientsSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_IngredientsSection", "InitIngredientsSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mMasLC.M_IngredientsSection.ActiveBoldItalic = "R"
   SetAttributeFromString( mMasLC, "M_IngredientsSection", "ActiveBoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsSectForUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
InitIngredientsSectForUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mMasLC.M_IngredientsSection.ActiveTitle
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mMasLC, "M_IngredientsSection", "ActiveTitle" );

   //:// We need to update the existing M_IngredientsSection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_IngredientsSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsSection", "InitIngredientsSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_IngredientsSection", "InitIngredientsSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelIngredientsSect( View     ViewToWindow )
{

   //:CancelIngredientsSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelIngredientsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelIngredientsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ValidateIngredientsSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ValidateIngredientsSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   //:// STRING (  50  ) szSectionTitle
   //:STRING (  32  ) szPercent
   String   szPercent = null;
   //:// INTEGER lSectionTitleLth
   //:DECIMAL dPercent
   double  dPercent = 0.0;
   //:SHORT   nRC
   int      nRC = 0;
   double  dTempDecimal_0 = 0.0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section title is not blank.
   //:// szSectionTitle = mMasLC.M_GeneralSection.Title
   //:// lSectionTitleLth = zGetStringLen( szSectionTitle )
   //:// TraceLineS( "Product Section Title: ", szSectionTitle )
   //:// TraceLineI( "Product Section Title Length: ", lSectionTitleLth )
   //:// IF lSectionTitleLth < 1
   //://
   //://    MessageSend( ViewToWindow, "", "Ingredients Section",
   //://                 "The Master Product Section Name cannot be blank.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    RETURN 2
   //://
   //:// END

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:dPercent = mTempLC.M_IngredientsSection.GeneralInactivePercent
   {MutableDouble md_dPercent = new MutableDouble( dPercent );
       GetDecimalFromAttribute( md_dPercent, mTempLC, "M_IngredientsSection", "GeneralInactivePercent" );
   dPercent = md_dPercent.doubleValue( );}
   //:FOR EACH mTempLC.M_IngredientsStatement
   RESULT = SetCursorFirstEntity( mTempLC, "M_IngredientsStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:dPercent = dPercent + mTempLC.M_IngredientsStatement.Percent
      {MutableDouble md_dTempDecimal_0 = new MutableDouble( dTempDecimal_0 );
             GetDecimalFromAttribute( md_dTempDecimal_0, mTempLC, "M_IngredientsStatement", "Percent" );
      dTempDecimal_0 = md_dTempDecimal_0.doubleValue( );}
      dPercent = dPercent + dTempDecimal_0;
      RESULT = SetCursorNextEntity( mTempLC, "M_IngredientsStatement", "" );
   } 

   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );
   //:IF dPercent != 100.000
   if ( dPercent != 100.000 )
   { 

      //:wWebXfer.Root.Decimal3 = dPercent
      SetAttributeFromDecimal( wWebXfer, "Root", "Decimal3", dPercent );
      //:szPercent = wWebXfer.Root.Decimal3
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szPercent;
      if ( szPercent == null )
         sb_szPercent = new StringBuilder( 32 );
      else
         sb_szPercent = new StringBuilder( szPercent );
             GetVariableFromAttribute( sb_szPercent, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "Decimal3", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szPercent = sb_szPercent.toString( );}
      //:szMessage = "The Ingredients total percentage must add up to 100% - currently: "
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringCopy( sb_szMessage, 1, 0, "The Ingredients total percentage must add up to 100% - currently: ", 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
      //:szMessage = szMessage + szPercent
       {StringBuilder sb_szMessage;
      if ( szMessage == null )
         sb_szMessage = new StringBuilder( 32 );
      else
         sb_szMessage = new StringBuilder( szMessage );
            ZeidonStringConcat( sb_szMessage, 1, 0, szPercent, 1, 0, 257 );
      szMessage = sb_szMessage.toString( );}
      //:MessageSend( ViewToWindow, "", "Ingredients Section",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Ingredients Section", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditHumanHazardSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditHumanHazardSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "H"  // "HumanHazard"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "H" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:EditStorDispSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditStorDispSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitStorDispSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitStorDispSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.Units = "Gallons"
   SetAttributeFromString( wWebXfer, "Root", "Units", "Gallons" );
   //:IF mMasLC.M_StorageDisposalSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_StorageDisposalSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalSection", "InitStorDispSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_StorageDisposalSection", "InitStorDispSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mMasLC, "M_StorageDisposalSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_StorageDisposalSection", "InitStorDispSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mMasLC", "M_StorageDisposalSection", "InitStorDispSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mMasLC.M_StorageDisposalSection.BoldItalic = "R"
      SetAttributeFromString( mMasLC, "M_StorageDisposalSection", "BoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:EditMarketingSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditMarketingSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:EditClaimsSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditClaimsSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "C"  // "Claims"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:EditSurfacesSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditSurfacesSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "S"  // "Surfaces"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:EditAppTypesSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditAppTypesSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "T"  // "AppTypes"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMasterProductUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMasterProductUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mTempReg BASED ON LOD  mPrimReg
   zVIEW    mTempReg = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempReg, mPrimReg )
   CreateViewFromView( mTempReg, mPrimReg );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempReg.MasterProduct
      RESULT = SetCursorPrevEntity( mTempReg, "MasterProduct", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempReg, "MasterProduct",
   //:               mPrimReg, "MasterProduct",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempReg, "MasterProduct", mPrimReg, "MasterProduct", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempReg )
   DropView( mTempReg );

   //:// We now accept the Master Label to maintain order!
   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewMasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewMasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:STRING (  50  ) szProductName
   String   szProductName = null;
   //:STRING (  50  ) szProductNumber
   String   szProductNumber = null;
   //:STRING (  50  ) szVersion
   String   szVersion = null;
   //:INTEGER lProductNameLth
   int      lProductNameLth = 0;
   //:INTEGER lProductNumberLth
   int      lProductNumberLth = 0;
   //:INTEGER lVersionLth
   int      lVersionLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:INTEGER Ignore
   int      Ignore = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:// Ensure product name is not blank and is unique.
   //:szProductName = wWebXfer.Root.AttemptProductName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szProductName;
   if ( szProductName == null )
      sb_szProductName = new StringBuilder( 32 );
   else
      sb_szProductName = new StringBuilder( szProductName );
       GetVariableFromAttribute( sb_szProductName, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptProductName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szProductName = sb_szProductName.toString( );}
   //:lProductNameLth = zGetStringLen( szProductName )
   lProductNameLth = zGetStringLen( szProductName );
   //:TraceLineS( "Product Name: ", szProductName )
   TraceLineS( "Product Name: ", szProductName );
   //:TraceLineI( "Product Name Length: ", lProductNameLth )
   TraceLineI( "Product Name Length: ", lProductNameLth );
   //:IF lProductNameLth < 1
   if ( lProductNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Master Product",
      //:             "The Master Product Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
      lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
      //:IF SetEntityCursor( mPrimReg, "MasterProduct", "Name", lControl,
      //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mPrimReg, "MasterProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Master Product",
         //:             "The Master Product Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END

   //:// Ensure product number is not blank and is unique.
   //:szProductNumber = wWebXfer.Root.AttemptProductNumber
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szProductNumber;
   if ( szProductNumber == null )
      sb_szProductNumber = new StringBuilder( 32 );
   else
      sb_szProductNumber = new StringBuilder( szProductNumber );
       GetVariableFromAttribute( sb_szProductNumber, mi_lTempInteger_2, 'S', 51, wWebXfer, "Root", "AttemptProductNumber", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szProductNumber = sb_szProductNumber.toString( );}
   //:lProductNumberLth = zGetStringLen( szProductNumber )
   lProductNumberLth = zGetStringLen( szProductNumber );
   //:TraceLineS( "Product Number: ", szProductNumber )
   TraceLineS( "Product Number: ", szProductNumber );
   //:TraceLineI( "Product Number Length: ", lProductNumberLth )
   TraceLineI( "Product Number Length: ", lProductNumberLth );
   //:IF lProductNumberLth < 1
   if ( lProductNumberLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Master Product",
      //:             "The Master Product Number cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Number cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
      lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
      //:IF SetEntityCursor( mPrimReg, "MasterProduct", "Number", lControl,
      //:                    szProductNumber, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_3 = SetEntityCursor( mPrimReg, "MasterProduct", "Number", lControl, szProductNumber, "", "", 0, "", "" );
      if ( lTempInteger_3 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Master Product",
         //:             "The Master Product Number must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Master Product", "The Master Product Number must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END
   //:/*
   //:// Ensure label version is not blank and is unique.
   //:szVersion = wWebXfer.Root.AttemptContentVersion
   //:lVersionLth = zGetStringLen( szVersion )
   //:TraceLineS( "Product Version: ", szVersion )
   //:TraceLineI( "Product Version Length: ", lVersionLth )
   //:IF lVersionLth < 1
   //:   MessageSend( ViewToWindow, "", "New Master Product",
   //:                "The Master Label Version cannot be blank.",
   //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:   SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //:   RETURN 2
   //:// ELSE ... it's got to be unique since the product is new
   //:// lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
   //:// IF SetEntityCursor( mMasProd, "MasterLabelContent", "Version", lControl,
   //://                     szVersion, "", "", 0, "", "" ) >= zCURSOR_SET
   //://    MessageSend( ViewToWindow, "", "New Master Product",
   //://                 "The Master Label Version must be unique.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    RETURN 2
   //:// END
   //:END
   //:*/
   //:mMasProd.MasterProduct.Name = szProductName
   SetAttributeFromString( mMasProd, "MasterProduct", "Name", szProductName );
   //:mMasProd.MasterProduct.Number = szProductNumber
   SetAttributeFromString( mMasProd, "MasterProduct", "Number", szProductNumber );
   //:// mMasProd.MasterLabelContent.Version = szVersion
   //:IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant",
   //:                               mPrimReg, "PrimaryRegistrant", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant", mPrimReg, "PrimaryRegistrant", zPOS_BEFORE );
   //:// AcceptSubobject( mMasProd, "MasterProduct" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptNewMasterProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptNewMasterProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );

   //:fnInitListMasterProducts( ViewToWindow, 1 )
   o_fnInitListMasterProducts( ViewToWindow, 1 );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewMasterProduct( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
CancelNewMasterProduct( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:IF mMasProd != 0
   if ( getView( mMasProd ) != null )
   { 
      //:// CancelSubobject( mMasProd, "MasterProduct" )
      //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewMasterProduct: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewMasterProduct: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:DropObjectInstance( mMasProd )
      DropObjectInstance( mMasProd );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptPrecautionaryStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
AcceptPrecautionaryStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptPrecautionarySect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptPrecautionarySect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF szEntityName = "M_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "M_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 
      //:// szSectionType = mMasLC.M_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mMasLC,
      //:                         "M_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mMasLC, "M_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "MI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "MI_", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, szSectionType, 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringConcat( sb_szEntityName, 1, 0, "Section", 1, 0, 33 );
      szEntityName = sb_szEntityName.toString( );}
      //:IncludeSubobjectFromSubobject( mMasLC, szEntityName,
      //:                               mMasLC, "M_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mMasLC, szEntityName, mMasLC, "M_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:FinalizeMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
FinalizeMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// This is checked by JavaScript
   //:// nRC = OperatorWebPrompt( ViewToWindow, "Finalize Master Label Content",
   //://                          "Once the Master Label Content has been Finalized,"
   //://                            "it cannot be updated.  Are you sure you want it Finalized?" )
   //:// IF nRC = 8
   //://    RETURN 2      // Return 2 to indicate Web client must prompt operator
   //:// END

   //:IF mMasLC.MasterLabelContent.Finalized = "Y"
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "Finalized", "Y" ) == 0 )
   { 
      //:mMasLC.MasterLabelContent.Finalized = "N"
      SetAttributeFromString( mMasLC, "MasterLabelContent", "Finalized", "N" );
      //:ELSE
   } 
   else
   { 
      //:mMasLC.MasterLabelContent.Finalized = "Y"
      SetAttributeFromString( mMasLC, "MasterLabelContent", "Finalized", "Y" );
   } 

   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "FinalizeMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "FinalizeMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveMasterLabelContent( VIEW ViewToWindow )

public int 
SaveMasterLabelContent( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSplitMasterSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSplitMasterSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 50 ) szSectionName
   String   szSectionName = null;
   //:STRING ( 50 ) szSectionNameNew
   String   szSectionNameNew = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mMasLC, "M_PrecautionarySection" )
   AcceptSubobject( mMasLC, "M_PrecautionarySection" );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //://?szSectionName = mMasLC.M_PrecautionarySection.Title
   //:szSectionNameNew = szSectionName + "a"
    {StringBuilder sb_szSectionNameNew;
   if ( szSectionNameNew == null )
      sb_szSectionNameNew = new StringBuilder( 32 );
   else
      sb_szSectionNameNew = new StringBuilder( szSectionNameNew );
      ZeidonStringCopy( sb_szSectionNameNew, 1, 0, szSectionName, 1, 0, 51 );
   szSectionNameNew = sb_szSectionNameNew.toString( );}
    {StringBuilder sb_szSectionNameNew;
   if ( szSectionNameNew == null )
      sb_szSectionNameNew = new StringBuilder( 32 );
   else
      sb_szSectionNameNew = new StringBuilder( szSectionNameNew );
      ZeidonStringConcat( sb_szSectionNameNew, 1, 0, "a", 1, 0, 51 );
   szSectionNameNew = sb_szSectionNameNew.toString( );}
   //:wWebXfer.Root.NameBefore = szSectionNameNew
   SetAttributeFromString( wWebXfer, "Root", "NameBefore", szSectionNameNew );
   //:szSectionNameNew = szSectionName + "b"
    {StringBuilder sb_szSectionNameNew;
   if ( szSectionNameNew == null )
      sb_szSectionNameNew = new StringBuilder( 32 );
   else
      sb_szSectionNameNew = new StringBuilder( szSectionNameNew );
      ZeidonStringCopy( sb_szSectionNameNew, 1, 0, szSectionName, 1, 0, 51 );
   szSectionNameNew = sb_szSectionNameNew.toString( );}
    {StringBuilder sb_szSectionNameNew;
   if ( szSectionNameNew == null )
      sb_szSectionNameNew = new StringBuilder( 32 );
   else
      sb_szSectionNameNew = new StringBuilder( szSectionNameNew );
      ZeidonStringConcat( sb_szSectionNameNew, 1, 0, "b", 1, 0, 51 );
   szSectionNameNew = sb_szSectionNameNew.toString( );}
   //:wWebXfer.Root.NameAfter = szSectionNameNew
   SetAttributeFromString( wWebXfer, "Root", "NameAfter", szSectionNameNew );
   //:wWebXfer.Root.SplitHTML_Before = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_Before", "" );
   //:wWebXfer.Root.SplitHTML_After = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_After", "" );

   //:// We may update the existing M_PrecautionarySection entity.
   //:// CreateTemporalSubobjectVersion( mMasLC, "M_PrecautionarySection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_PrecautionarySection", "InitSplitMasterSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "M_PrecautionarySection", "InitSplitMasterSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSplitMasterSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptSplitMasterSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING ( 50 ) szSectionName
   String   szSectionName = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:INTEGER lSectionNameLth
   int      lSectionNameLth = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
   lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
   //:IF wWebXfer.Root.SplitHTML_Before != ""
   if ( CompareAttributeToString( wWebXfer, "Root", "SplitHTML_Before", "" ) != 0 )
   { 
      //:szSectionName = wWebXfer.Root.NameBefore
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSectionName;
      if ( szSectionName == null )
         sb_szSectionName = new StringBuilder( 32 );
      else
         sb_szSectionName = new StringBuilder( szSectionName );
             GetVariableFromAttribute( sb_szSectionName, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "NameBefore", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSectionName = sb_szSectionName.toString( );}
      //:lSectionNameLth = zGetStringLen( szSectionName )
      lSectionNameLth = zGetStringLen( szSectionName );
      //:IF lSectionNameLth < 1
      if ( lSectionNameLth < 1 )
      { 

         //:MessageSend( ViewToWindow, "", "Split Master Product Section",
         //:             "The Previous Master Product Section Name cannot be blank.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Split Master Product Section", "The Previous Master Product Section Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:nRC = SetEntityCursor( mMasLC, "M_PrecautionarySection", "Name", lControl,
      //:                       szSectionName, "", "", 0, "", "" )
      nRC = SetEntityCursor( mMasLC, "M_PrecautionarySection", "Name", lControl, szSectionName, "", "", 0, "", "" );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "Split Master Product Section",
         //:             "The Previous Master Product Section Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Split Master Product Section", "The Previous Master Product Section Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:IF wWebXfer.Root.SplitHTML_After != ""
   if ( CompareAttributeToString( wWebXfer, "Root", "SplitHTML_After", "" ) != 0 )
   { 
      //:szSectionName = wWebXfer.Root.NameAfter
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szSectionName;
      if ( szSectionName == null )
         sb_szSectionName = new StringBuilder( 32 );
      else
         sb_szSectionName = new StringBuilder( szSectionName );
             GetVariableFromAttribute( sb_szSectionName, mi_lTempInteger_1, 'S', 51, wWebXfer, "Root", "NameAfter", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szSectionName = sb_szSectionName.toString( );}
      //:lSectionNameLth = zGetStringLen( szSectionName )
      lSectionNameLth = zGetStringLen( szSectionName );
      //:IF lSectionNameLth < 1
      if ( lSectionNameLth < 1 )
      { 

         //:MessageSend( ViewToWindow, "", "Split Master Product Section",
         //:             "The Next Master Product Section Name cannot be blank.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Split Master Product Section", "The Next Master Product Section Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:nRC = SetEntityCursor( mMasLC, "M_PrecautionarySection", "Name", lControl,
      //:                       szSectionName, "", "", 0, "", "" )
      nRC = SetEntityCursor( mMasLC, "M_PrecautionarySection", "Name", lControl, szSectionName, "", "", 0, "", "" );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "Split Master Product Section",
         //:             "The Next Master Product Section Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Split Master Product Section", "The Next Master Product Section Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:IF wWebXfer.Root.SplitHTML_Before != ""
   if ( CompareAttributeToString( wWebXfer, "Root", "SplitHTML_Before", "" ) != 0 )
   { 

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:CreateEntity( mTempLC, "M_PrecautionarySection", zPOS_BEFORE )
      CreateEntity( mTempLC, "M_PrecautionarySection", zPOS_BEFORE );
      //:SetMatchingAttributesByName( mTempLC, "M_PrecautionarySection",
      //:                             mMasLC, "M_PrecautionarySection", zSET_NOTNULL )
      SetMatchingAttributesByName( mTempLC, "M_PrecautionarySection", mMasLC, "M_PrecautionarySection", zSET_NOTNULL );
      //://?   mTempLC.M_PrecautionarySection.Title = wWebXfer.Root.NameBefore
      //:mTempLC.M_PrecautionaryStatement.Text = wWebXfer.Root.SplitHTML_Before
      SetAttributeFromAttribute( mTempLC, "M_PrecautionaryStatement", "Text", wWebXfer, "Root", "SplitHTML_Before" );
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 


   //:END

   //:IF wWebXfer.Root.SplitHTML_After != ""
   if ( CompareAttributeToString( wWebXfer, "Root", "SplitHTML_After", "" ) != 0 )
   { 

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:CreateEntity( mTempLC, "M_PrecautionarySection", zPOS_AFTER )
      CreateEntity( mTempLC, "M_PrecautionarySection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mTempLC, "M_PrecautionarySection",
      //:                             mMasLC, "M_PrecautionarySection", zSET_NOTNULL )
      SetMatchingAttributesByName( mTempLC, "M_PrecautionarySection", mMasLC, "M_PrecautionarySection", zSET_NOTNULL );
      //://?   mTempLC.M_PrecautionarySection.Title = wWebXfer.Root.NameAfter
      //:mTempLC.M_PrecautionaryStatement.Text = wWebXfer.Root.SplitHTML_After
      SetAttributeFromAttribute( mTempLC, "M_PrecautionaryStatement", "Text", wWebXfer, "Root", "SplitHTML_After" );
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END

   //:AcceptSubobject( mMasLC, "M_PrecautionarySection" )
   AcceptSubobject( mMasLC, "M_PrecautionarySection" );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:wWebXfer.Root.SplitHTML_Before = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_Before", "" );
   //:wWebXfer.Root.SplitHTML_After = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_After", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelSplitMasterSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelSplitMasterSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelSplitMasterSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelSplitMasterSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.SplitHTML_Before = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_Before", "" );
   //:wWebXfer.Root.SplitHTML_After = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_After", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterSectForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterSectForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   return( 0 );
// // VIEW mMasLC REGISTERED AS mMasLC
// // TraceLineS( "", "" )
// // TraceLineS( "InitMasterSectForDelete", "" )
// // DisplayObjectInstance( mMasLC, "", "" )
// END
} 


//:DIALOG OPERATION
//:DeleteMasterSect( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
DeleteMasterSect( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lSectionID
   int      lSectionID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mMasLC, "MasterLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mMasLC.M_PrecautionarySection.ID

   //:// We have to make sure the Content is in good shape before we go on!
   //:nRC = AcceptUpdateMasterLabelContent( ViewToWindow )
   nRC = AcceptUpdateMasterLabelContent( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_17( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );
   } 

   //://?   SET CURSOR FIRST mMasLC.M_PrecautionarySection
   //://?       WHERE mMasLC.M_PrecautionarySection.ID = lSectionID
   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterSect( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
ConfirmDeleteMasterSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //://?DELETE ENTITY mMasLC.M_PrecautionarySection
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteMasterSect( VIEW ViewToWindow )

public int 
CancelDeleteMasterSect( View     ViewToWindow )
{

   return( 0 );
// // VIEW mMasLC REGISTERED AS mMasLC
// END
} 


//:DIALOG OPERATION
//:MoveMasterSectUp( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
MoveMasterSectUp( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lSectionID
   int      lSectionID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mMasLC, "MasterLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mMasLC.M_PrecautionarySection.ID

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterLabelContent( ViewToWindow )
   nRC = AcceptUpdateMasterLabelContent( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_18( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );
      //://?   SET CURSOR FIRST mMasLC.M_PrecautionarySection
      //://?       WHERE mMasLC.M_PrecautionarySection.ID = lSectionID

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //://?   SET CURSOR PREVIOUS mTempLC.M_PrecautionarySection
      //:MoveSubobject( mTempLC, "M_PrecautionarySection",
      //:               mMasLC, "M_PrecautionarySection",
      //:               zPOS_PREV, zREPOS_PREV )
      MoveSubobject( mTempLC, "M_PrecautionarySection", mMasLC, "M_PrecautionarySection", zPOS_PREV, zREPOS_PREV );
      //:DropView( mTempLC )
      DropView( mTempLC );
      //:COMMIT mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMasterSectDown( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
MoveMasterSectDown( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lSectionID
   int      lSectionID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mMasLC, "MasterLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mMasLC.M_PrecautionarySection.ID

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterLabelContent( ViewToWindow )
   nRC = AcceptUpdateMasterLabelContent( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_19( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );
      //://?   SET CURSOR FIRST mMasLC.M_PrecautionarySection
      //://?       WHERE mMasLC.M_PrecautionarySection.ID = lSectionID

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //://?   SET CURSOR NEXT mTempLC.M_PrecautionarySection
      //:MoveSubobject( mTempLC, "M_PrecautionarySection",
      //:               mMasLC, "M_PrecautionarySection",
      //:               zPOS_NEXT, zREPOS_NEXT )
      MoveSubobject( mTempLC, "M_PrecautionarySection", mMasLC, "M_PrecautionarySection", zPOS_NEXT, zREPOS_NEXT );
      //:DropView( mTempLC )
      DropView( mTempLC );
      //:COMMIT mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMasterProductDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMasterProductDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mTempReg BASED ON LOD  mPrimReg
   zVIEW    mTempReg = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempReg, mPrimReg )
   CreateViewFromView( mTempReg, mPrimReg );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempReg.MasterProduct
      RESULT = SetCursorNextEntity( mTempReg, "MasterProduct", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempReg, "MasterProduct",
   //:               mPrimReg, "MasterProduct",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempReg, "MasterProduct", mPrimReg, "MasterProduct", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempReg )
   DropView( mTempReg );

   //:// We now accept the Master Label to maintain order!
   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasProd BASED ON LOD  mMasProd
public int 
DeleteMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   //:INTEGER lMasterProductID
   int      lMasterProductID = 0;
   //:INTEGER lMasterLabelContentID
   int      lMasterLabelContentID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:IF mMasProd != 0 AND mMasProd.MasterLabelContent.Finalized = "Y"
   if ( mMasProd != null && CompareAttributeToString( mMasProd, "MasterLabelContent", "Finalized", "Y" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Master Label Content",
      //:             "The Master Label Content has been Finalized and cannot be deleted.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Master Label Content", "The Master Label Content has been Finalized and cannot be deleted.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:lMasterProductID = mMasProd.MasterProduct.ID
   {MutableInt mi_lMasterProductID = new MutableInt( lMasterProductID );
       GetIntegerFromAttribute( mi_lMasterProductID, mMasProd, "MasterProduct", "ID" );
   lMasterProductID = mi_lMasterProductID.intValue( );}
   //:lMasterLabelContentID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lMasterLabelContentID = new MutableInt( lMasterLabelContentID );
       GetIntegerFromAttribute( mi_lMasterLabelContentID, mMasProd, "MasterLabelContent", "ID" );
   lMasterLabelContentID = mi_lMasterLabelContentID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteMasterLabelContent: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteMasterLabelContent: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:fnInitListMasterProducts( ViewToWindow, 0 )
      o_fnInitListMasterProducts( ViewToWindow, 0 );

      //:GET VIEW mMasProd NAMED "mMasProd"
      RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
      //:IF mMasProd != 0
      if ( getView( mMasProd ) != null )
      { 
         //:DropObjectInstance( mMasProd )
         DropObjectInstance( mMasProd );
      } 

      //:END

      //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = lMasterProductID
      o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_0, lMasterProductID );
      RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasProd "mMasProd"
      SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasProd.MasterLabelContent
      //:    WHERE mMasProd.MasterLabelContent.ID = lMasterLabelContentID
      RESULT = SetCursorFirstEntityByInteger( mMasProd, "MasterLabelContent", "ID", lMasterLabelContentID, "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:CopyMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
CopyMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.Finalized = "N"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "Finalized", "N" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelPrecautionaryStmt( View     ViewToWindow )
{

   //:CancelPrecautionaryStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelPrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelPrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelPrecautionarySect( View     ViewToWindow )
{

   //:CancelPrecautionarySect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewMasterProduct( VIEW ViewToWindow )

public int 
NewMasterProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here
// END
} 


//:DIALOG OPERATION
//:NewMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasProd BASED ON LOD  mMasProd
public int 
NewMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   //:lID = mMasProd.MasterProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasProd, "MasterProduct", "ID" );
   lID = mi_lID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = lID
      o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasProd "mMasProd"
      SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MovePrecautionaryStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MovePrecautionaryStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_GeneralStatement
      RESULT = SetCursorPrevEntity( mTempLC, "M_GeneralStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_GeneralStatement",
   //:               mMasLC, "M_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MovePrecautionaryStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MovePrecautionaryStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_GeneralStatement
      RESULT = SetCursorNextEntity( mTempLC, "M_GeneralStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_GeneralStatement",
   //:               mMasLC, "M_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveDirectionsUseStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveDirectionsUseStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtDown: " )
   //:nRC = AcceptDirectionsUseSect( ViewToWindow )
   nRC = AcceptDirectionsUseSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //:lMove = wWebXfer.Root.MoveIncrement
      {MutableInt mi_lMove = new MutableInt( lMove );
             GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
      lMove = mi_lMove.intValue( );}
      //:IF lMove <= 0
      if ( lMove <= 0 )
      { 
         //:lMove = 1
         lMove = 1;
      } 

      //:END

      //:LOOP WHILE lMove > 0
      while ( lMove > 0 )
      { 
         //:SET CURSOR NEXT mTempLC.M_DirectionsForUseStatement
         RESULT = SetCursorNextEntity( mTempLC, "M_DirectionsForUseStatement", "" );
         //:lMove = lMove - 1
         lMove = lMove - 1;
      } 

      //:END


      //:MoveSubobject( mTempLC, "M_DirectionsForUseStatement",
      //:               mMasLC, "M_DirectionsForUseStatement",
      //:               zPOS_NEXT, zREPOS_NEXT )
      MoveSubobject( mTempLC, "M_DirectionsForUseStatement", mMasLC, "M_DirectionsForUseStatement", zPOS_NEXT, zREPOS_NEXT );
      //:DropView( mTempLC )
      DropView( mTempLC );

      //:// We now commit the Master Label Content to maintain order!
      //:COMMIT mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveDirectionsUseSectDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveDirectionsUseSectDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_DirectionsForUseSection
      RESULT = SetCursorNextEntity( mTempLC, "M_DirectionsForUseSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_DirectionsForUseSection",
   //:               mMasLC, "M_DirectionsForUseSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_DirectionsForUseSection", mMasLC, "M_DirectionsForUseSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveDirectionsUseSectUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveDirectionsUseSectUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_DirectionsForUseSection
      RESULT = SetCursorPrevEntity( mTempLC, "M_DirectionsForUseSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_DirectionsForUseSection",
   //:               mMasLC, "M_DirectionsForUseSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_DirectionsForUseSection", mMasLC, "M_DirectionsForUseSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveAreasOfUseStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveAreasOfUseStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.MI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveAppTypesStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveAppTypesStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.MI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAppTypesStmtsForInsert( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
InitAppTypesStmtsForInsert( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     BASED ON LOD  mEPA
   zVIEW    mEPA = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitAppTypesStmtsForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitAppTypesStmtsForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:GET VIEW mEPA NAMED "mEPA"
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );
   //:IF mEPA != 0
   if ( getView( mEPA ) != null )
   { 
      //:DropObjectInstance( mEPA )
      DropObjectInstance( mEPA );
   } 

   //:END

   //:ACTIVATE mEPA WHERE mEPA.EPA_ChemicalFamily.ChemicalFamily = mMasLC.MasterProduct.ChemicalFamily
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mMasLC, "MasterProduct", "ChemicalFamily" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_27( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveAppTypesStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveAppTypesStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.MI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveAreasOfUseStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveAreasOfUseStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.MI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveSurfacesStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveSurfacesStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.MI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// //?CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MasterLabelContent", "MoveSurfacesStmtDown: " )
// END
} 


//:DIALOG OPERATION
//:MoveSurfacesStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveSurfacesStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.MI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// //?CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MasterLabelContent", "MoveSurfacesStmtUp: " )
// END
} 


//:DIALOG OPERATION
//:MoveClaimsStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveClaimsStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.MI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveClaimsStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveClaimsStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.MI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "MI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_Usage", mTempLC, "MI_UsageList", "" );

   //:MoveSubobject( mTempLC, "M_Usage",
   //:               mMasLC, "M_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_Usage", mMasLC, "M_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "MI_UsageList",
   //:               mMasLC, "MI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "MI_UsageList", mMasLC, "MI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveDirectionsUseStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveDirectionsUseStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtUp: " )
   //:nRC = AcceptDirectionsUseSect( ViewToWindow )
   nRC = AcceptDirectionsUseSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //:lMove = wWebXfer.Root.MoveIncrement
      {MutableInt mi_lMove = new MutableInt( lMove );
             GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
      lMove = mi_lMove.intValue( );}
      //:IF lMove <= 0
      if ( lMove <= 0 )
      { 
         //:lMove = 1
         lMove = 1;
      } 

      //:END

      //:LOOP WHILE lMove > 0
      while ( lMove > 0 )
      { 
         //:SET CURSOR PREVIOUS mTempLC.M_DirectionsForUseStatement
         RESULT = SetCursorPrevEntity( mTempLC, "M_DirectionsForUseStatement", "" );
         //:lMove = lMove - 1
         lMove = lMove - 1;
      } 

      //:END

      //:MoveSubobject( mTempLC, "M_DirectionsForUseStatement",
      //:               mMasLC, "M_DirectionsForUseStatement",
      //:               zPOS_PREV, zREPOS_PREV )
      MoveSubobject( mTempLC, "M_DirectionsForUseStatement", mMasLC, "M_DirectionsForUseStatement", zPOS_PREV, zREPOS_PREV );
      //:DropView( mTempLC )
      DropView( mTempLC );

      //:// We now commit the Master Label Content to maintain order!
      //:COMMIT mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMarketingStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMarketingStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtDown: " )
   //:nRC = AcceptMarketingSect( ViewToWindow )
   nRC = AcceptMarketingSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //:lMove = wWebXfer.Root.MoveIncrement
      {MutableInt mi_lMove = new MutableInt( lMove );
             GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
      lMove = mi_lMove.intValue( );}
      //:IF lMove <= 0
      if ( lMove <= 0 )
      { 
         //:lMove = 1
         lMove = 1;
      } 

      //:END

      //:LOOP WHILE lMove > 0
      while ( lMove > 0 )
      { 
         //:SET CURSOR NEXT mTempLC.M_MarketingStatement
         RESULT = SetCursorNextEntity( mTempLC, "M_MarketingStatement", "" );
         //:lMove = lMove - 1
         lMove = lMove - 1;
      } 

      //:END

      //:MoveSubobject( mTempLC, "M_MarketingStatement",
      //:               mMasLC, "M_MarketingStatement",
      //:               zPOS_NEXT, zREPOS_NEXT )
      MoveSubobject( mTempLC, "M_MarketingStatement", mMasLC, "M_MarketingStatement", zPOS_NEXT, zREPOS_NEXT );
      //:DropView( mTempLC )
      DropView( mTempLC );

      //:// We now commit the Master Label Content to maintain order!
      //:COMMIT mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMarketingStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMarketingStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtUp: " )
   //:nRC = AcceptMarketingSect( ViewToWindow )
   nRC = AcceptMarketingSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:CreateViewFromView( mTempLC, mMasLC )
      CreateViewFromView( mTempLC, mMasLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //:lMove = wWebXfer.Root.MoveIncrement
      {MutableInt mi_lMove = new MutableInt( lMove );
             GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
      lMove = mi_lMove.intValue( );}
      //:IF lMove <= 0
      if ( lMove <= 0 )
      { 
         //:lMove = 1
         lMove = 1;
      } 

      //:END

      //:LOOP WHILE lMove > 0
      while ( lMove > 0 )
      { 
         //:SET CURSOR PREVIOUS mTempLC.M_MarketingStatement
         RESULT = SetCursorPrevEntity( mTempLC, "M_MarketingStatement", "" );
         //:lMove = lMove - 1
         lMove = lMove - 1;
      } 

      //:END

      //:MoveSubobject( mTempLC, "M_MarketingStatement",
      //:               mMasLC, "M_MarketingStatement",
      //:               zPOS_PREV, zREPOS_PREV )
      MoveSubobject( mTempLC, "M_MarketingStatement", mMasLC, "M_MarketingStatement", zPOS_PREV, zREPOS_PREV );
      //:DropView( mTempLC )
      DropView( mTempLC );

      //:// We now commit the Master Label Content to maintain order!
      //:COMMIT mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveStorDispStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveStorDispStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_StorageDisposalStatement
      RESULT = SetCursorPrevEntity( mTempLC, "M_StorageDisposalStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_StorageDisposalStatement",
   //:               mMasLC, "M_StorageDisposalStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_StorageDisposalStatement", mMasLC, "M_StorageDisposalStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveStorDispStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveStorDispStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_StorageDisposalStatement
      RESULT = SetCursorNextEntity( mTempLC, "M_StorageDisposalStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_StorageDisposalStatement",
   //:               mMasLC, "M_StorageDisposalStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_StorageDisposalStatement", mMasLC, "M_StorageDisposalStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveStorDispSectDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveStorDispSectDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_StorageDisposalSection
      RESULT = SetCursorNextEntity( mTempLC, "M_StorageDisposalSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_StorageDisposalSection",
   //:               mMasLC, "M_StorageDisposalSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_StorageDisposalSection", mMasLC, "M_StorageDisposalSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveStorDispSectUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveStorDispSectUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_StorageDisposalSection
      RESULT = SetCursorPrevEntity( mTempLC, "M_StorageDisposalSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_StorageDisposalSection",
   //:               mMasLC, "M_StorageDisposalSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_StorageDisposalSection", mMasLC, "M_StorageDisposalSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveIngredientsStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveIngredientsStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_IngredientsStatement
      RESULT = SetCursorPrevEntity( mTempLC, "M_IngredientsStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_IngredientsStatement",
   //:               mMasLC, "M_IngredientsStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_IngredientsStatement", mMasLC, "M_IngredientsStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveIngredientsStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveIngredientsStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_IngredientsStatement
      RESULT = SetCursorNextEntity( mTempLC, "M_IngredientsStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_IngredientsStatement",
   //:               mMasLC, "M_IngredientsStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_IngredientsStatement", mMasLC, "M_IngredientsStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveHazardStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveHazardStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_GeneralStatement
      RESULT = SetCursorNextEntity( mTempLC, "M_GeneralStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_GeneralStatement",
   //:               mMasLC, "M_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// //?CreateCurrentTemporalVersion( ViewToWindow, 0, "mMasLC", "MasterLabelContent", "MoveHazardStmtDown: " )
// END
} 


//:DIALOG OPERATION
//:MoveHazardSectDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveHazardSectDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.MI_HazardSection
      RESULT = SetCursorNextEntity( mTempLC, "MI_HazardSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_GeneralSection", mTempLC, "MI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_GeneralSection", mTempLC, "MI_HazardSection", "" );

   //:// SetCursorFirstEntityByAttr( mMasLC, "M_GeneralSection", "ID",
   //://                             mMasLC, "M_HazardSection", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "M_GeneralSection", "ID",
   //://                             mTempLC, "M_HazardSection", "ID", "" )

   //:MoveSubobject( mTempLC, "MI_HazardSection",
   //:               mMasLC, "MI_HazardSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "MI_HazardSection", mMasLC, "MI_HazardSection", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "M_GeneralSection",
   //:               mMasLC, "M_GeneralSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_GeneralSection", mMasLC, "M_GeneralSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveHazardSectUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveHazardSectUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.MI_HazardSection
      RESULT = SetCursorPrevEntity( mTempLC, "MI_HazardSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "M_GeneralSection", mTempLC, "MI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "M_GeneralSection", mTempLC, "MI_HazardSection", "" );

   //:// SetCursorFirstEntityByAttr( mMasLC, "M_GeneralSection", "ID",
   //://                             mMasLC, "M_HazardSection", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "M_GeneralSection", "ID",
   //://                             mTempLC, "M_HazardSection", "ID", "" )

   //:MoveSubobject( mTempLC, "MI_HazardSection",
   //:               mMasLC, "MI_HazardSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "MI_HazardSection", mMasLC, "MI_HazardSection", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "M_GeneralSection",
   //:               mMasLC, "M_GeneralSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_GeneralSection", mMasLC, "M_GeneralSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveHazardStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveHazardStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_GeneralStatement
      RESULT = SetCursorPrevEntity( mTempLC, "M_GeneralStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_GeneralStatement",
   //:               mMasLC, "M_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveFirstAidStmtDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveFirstAidStmtDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempLC.M_GeneralStatement
      RESULT = SetCursorNextEntity( mTempLC, "M_GeneralStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_GeneralStatement",
   //:               mMasLC, "M_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveFirstAidStmtUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveFirstAidStmtUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:lMove = wWebXfer.Root.MoveIncrement
   {MutableInt mi_lMove = new MutableInt( lMove );
       GetIntegerFromAttribute( mi_lMove, wWebXfer, "Root", "MoveIncrement" );
   lMove = mi_lMove.intValue( );}
   //:IF lMove <= 0
   if ( lMove <= 0 )
   { 
      //:lMove = 1
      lMove = 1;
   } 

   //:END

   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempLC.M_GeneralStatement
      RESULT = SetCursorPrevEntity( mTempLC, "M_GeneralStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "M_GeneralStatement",
   //:               mMasLC, "M_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelHazardStmt( View     ViewToWindow )
{

   //:CancelHazardStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectPrecautionaryStmtForDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
SelectPrecautionaryStmtForDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_PrecautionaryStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectDirectionsUseStmtForDelete( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SelectDirectionsUseStmtForDelete( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptDirectionsUseSect( ViewToWindow )
   nRC = AcceptDirectionsUseSect( ViewToWindow );
   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:SelectDirectionsUseSectForDelete( VIEW ViewToWindow )

public int 
SelectDirectionsUseSectForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:SelectAreasOfUseStmtForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectAreasOfUseStmtForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to delete the existing M_Usage (AreasOfUse) entity. We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (AreasOfUse) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:DELETE ENTITY mMasLC.M_Usage
   RESULT = DeleteEntity( mMasLC, "M_Usage", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectAppTypesStmtForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectAppTypesStmtForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to delete the existing M_Usage (AppTypes) entity.  We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (AppTypes) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:DELETE ENTITY mMasLC.M_Usage
   RESULT = DeleteEntity( mMasLC, "M_Usage", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:wWebXfer.Root.CurrentContentType = "T"  // "AppType"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectClaimsStmtForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectClaimsStmtForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to delete the existing M_Usage (Claims) entity.  We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (Claims) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:DELETE ENTITY mMasLC.M_Usage
   RESULT = DeleteEntity( mMasLC, "M_Usage", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectSurfacesStmtForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectSurfacesStmtForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to delete the existing M_Usage (Surfaces) entity.  We have
   //:// position on the MI_UsageList entity, but need to get position on
   //:// the M_Usage (Surfaces) entity that corresponds to the MI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
   //:DELETE ENTITY mMasLC.M_Usage
   RESULT = DeleteEntity( mMasLC, "M_Usage", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMarketingStmtForDelete( VIEW ViewToWindow )

//:   // nothing to do here ... just for positioning

//:   SHORT nRC
public int 
SelectMarketingStmtForDelete( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptMarketingSect( ViewToWindow )
   nRC = AcceptMarketingSect( ViewToWindow );
   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:SelectStorDispStmtForDelete( VIEW ViewToWindow )

public int 
SelectStorDispStmtForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:SelectStorDispSectForDelete( VIEW ViewToWindow )

public int 
SelectStorDispSectForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:SelectIngredientsStmtForDelete( VIEW ViewToWindow )

public int 
SelectIngredientsStmtForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:SelectHazardStmtForDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
SelectHazardStmtForDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_HazardStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectHazardSectForDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
SelectHazardSectForDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralSection", mMasLC, "MI_HazardSection", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectFirstAidStmtForDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
SelectFirstAidStmtForDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mMasLC, "M_GeneralStatement", mMasLC, "M_FirstAidStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteFirstAidStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteFirstAidStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_GeneralStatement
   RESULT = DeleteEntity( mMasLC, "M_GeneralStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteHazardStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteHazardStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_GeneralStatement
   RESULT = DeleteEntity( mMasLC, "M_GeneralStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteHazardSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteHazardSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_GeneralSection
   RESULT = DeleteEntity( mMasLC, "M_GeneralSection", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteHazardStmt( View     ViewToWindow )
{

   //:CancelDeleteHazardStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteHazardSect( View     ViewToWindow )
{

   //:CancelDeleteHazardSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteFirstAidStmt( View     ViewToWindow )
{

   //:CancelDeleteFirstAidStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeletePrecautionaryStmt( View     ViewToWindow )
{

   //:CancelDeletePrecautionaryStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeletePrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeletePrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteIngredientStmt( View     ViewToWindow )
{

   //:CancelDeleteIngredientStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteIngredientStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteIngredientStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteIngredientStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteIngredientStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteIngredientStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteIngredientStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_IngredientsStatement
   RESULT = DeleteEntity( mMasLC, "M_IngredientsStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteStorDispStmt( View     ViewToWindow )
{

   //:CancelDeleteStorDispStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteStorDispSect( View     ViewToWindow )
{

   //:CancelDeleteStorDispSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteStorDispStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteStorDispStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_StorageDisposalStatement
   RESULT = DeleteEntity( mMasLC, "M_StorageDisposalStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteStorDispSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteStorDispSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_StorageDisposalSection
   RESULT = DeleteEntity( mMasLC, "M_StorageDisposalSection", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteMarketingStmt( View     ViewToWindow )
{

   //:CancelDeleteMarketingStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteMarketingSect( View     ViewToWindow )
{

   //:CancelDeleteMarketingSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMarketingStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteMarketingStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_MarketingStatement
   RESULT = DeleteEntity( mMasLC, "M_MarketingStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMarketingSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteMarketingSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_MarketingSection
   RESULT = DeleteEntity( mMasLC, "M_MarketingSection", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteDirectionsUseStmt( View     ViewToWindow )
{

   //:CancelDeleteDirectionsUseStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteDirectionsUseSect( View     ViewToWindow )
{

   //:CancelDeleteDirectionsUseSect( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteDirectionsUseStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_DirectionsForUseStatement
   RESULT = DeleteEntity( mMasLC, "M_DirectionsForUseStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeleteDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_DirectionsForUseSection
   RESULT = DeleteEntity( mMasLC, "M_DirectionsForUseSection", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteAreasOfUseStmt( View     ViewToWindow )
{

   //:CancelDeleteAreasOfUseStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteAreasOfUseStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmDeleteAreasOfUseStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_Usage
   RESULT = DeleteEntity( mSubLC, "S_Usage", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeletePrecautionaryStmt( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
ConfirmDeletePrecautionaryStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeletePrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeletePrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.M_GeneralStatement
   RESULT = DeleteEntity( mMasLC, "M_GeneralStatement", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditAreasOfUseSect( VIEW ViewToWindow )

//:   VIEW  wWebXfer REGISTERED AS wWebXfer
public int 
EditAreasOfUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    nRC = m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
      SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
CancelDeleteMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We don't think there's much to do here ... just get back to Master Product update.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
ConfirmDeleteMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMasterLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMasterLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mMasLC.MasterLabelContent
   RESULT = DeleteEntity( mMasLC, "MasterLabelContent", zPOS_NEXT );
   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DesignMasterLabel( VIEW ViewToWindow )

public int 
DesignMasterLabel( View     ViewToWindow )
{

   return( 0 );
//    // Don't need to do anything except have this OPERATION: to cause
//    // positioning code to be done in the JSP.
// END
} 


//:DIALOG OPERATION
//:SaveAddNewMarketingStmt( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveAddNewMarketingStmt( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptMarketingStmt( ViewToWindow )
   nRC = AcceptMarketingStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewMarketingStmt( ViewToWindow )
      AddNewMarketingStmt( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Marketing Statement",
      //:             "Error saving marketing statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Marketing Statement", "Error saving marketing statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewMarketingSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveAddNewMarketingSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptMarketingSect( ViewToWindow )
   nRC = AcceptMarketingSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewMarketingSect( ViewToWindow )
      AddNewMarketingSect( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Save And Add New Marketing Section",
      //://              "Error saving marketing section.",
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewDirectionsUseSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveAddNewDirectionsUseSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptDirectionsUseSect( ViewToWindow )
   nRC = AcceptDirectionsUseSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewDirectionsUseSect( ViewToWindow )
      AddNewDirectionsUseSect( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Save And Add New DirectionsUse Section",
      //://              "Error saving DirectionsUse section.",
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewDirectionsUseStmt( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveAddNewDirectionsUseStmt( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptDirectionsUseStmt( ViewToWindow )
   nRC = AcceptDirectionsUseStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewDirectionsUseStmt( ViewToWindow )
      AddNewDirectionsUseStmt( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Directions for Use Statement",
      //:             "Error saving Directions for Use statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Directions for Use Statement", "Error saving Directions for Use statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewIngredient( VIEW ViewToWindow )

//:   VIEW  mMasLC   REGISTERED AS mMasLC
public int 
SaveAddNewIngredient( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 256 ) szPrompt
   String   szPrompt = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptIngredientsStmt( ViewToWindow )
   nRC = AcceptIngredientsStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:szPrompt = mMasLC.M_IngredientsStatement.Prompt
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szPrompt;
      if ( szPrompt == null )
         sb_szPrompt = new StringBuilder( 32 );
      else
         sb_szPrompt = new StringBuilder( szPrompt );
             GetVariableFromAttribute( sb_szPrompt, mi_lTempInteger_0, 'S', 257, mMasLC, "M_IngredientsStatement", "Prompt", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szPrompt = sb_szPrompt.toString( );}
      //:InitIngredientsStmtForInsert( ViewToWindow )
      InitIngredientsStmtForInsert( ViewToWindow );
      //:mMasLC.M_IngredientsStatement.Prompt = szPrompt
      SetAttributeFromString( mMasLC, "M_IngredientsStatement", "Prompt", szPrompt );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Ingredients Statement",
      //:             "Error saving ingredient statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Ingredients Statement", "Error saving ingredient statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewClaim( VIEW ViewToWindow )

//:   VIEW  mMasLC   REGISTERED AS mMasLC
public int 
SaveAddNewClaim( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptClaimsStmt( ViewToWindow )
   nRC = AcceptClaimsStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewClaimsStmt( ViewToWindow )
      AddNewClaimsStmt( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Claims Statement",
      //:             "Error saving Claims statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Claims Statement", "Error saving Claims statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewSurface( VIEW ViewToWindow )

//:   VIEW  mMasLC   REGISTERED AS mMasLC
public int 
SaveAddNewSurface( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptSurfacesStmt( ViewToWindow )
   nRC = AcceptSurfacesStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewSurfacesStmt( ViewToWindow )
      AddNewSurfacesStmt( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Surfaces Statement",
      //:             "Error saving surfaces statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Surfaces Statement", "Error saving surfaces statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewAreasOfUse( VIEW ViewToWindow )

//:   VIEW  mMasLC  REGISTERED AS mMasLC
public int 
SaveAddNewAreasOfUse( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptAreasOfUseStmt( ViewToWindow )
   nRC = AcceptAreasOfUseStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewAreasOfUseStmt( ViewToWindow )
      AddNewAreasOfUseStmt( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Area of Use Statement",
      //:             "Error saving area of use statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Area of Use Statement", "Error saving area of use statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewAppType( VIEW ViewToWindow )

//:   VIEW  mMasLC   REGISTERED AS mMasLC
public int 
SaveAddNewAppType( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptAppTypesStmt( ViewToWindow )
   nRC = AcceptAppTypesStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:AddNewAppTypesStmt( ViewToWindow )
      AddNewAppTypesStmt( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Application Type Statement",
      //:             "Error saving application type statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Application Type Statement", "Error saving application type statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
SelectMarketingSectForUpdate( View     ViewToWindow )
{

   //:SelectMarketingSectForUpdate( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectMarketingSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectMarketingSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitMarketingContent( View     ViewToWindow )
{

   //:InitMarketingContent( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanupMarketingWorkEntities( VIEW mMasLC )

//:   VIEW mTempLC  BASED ON LOD  mMasLC
public int 
CleanupMarketingWorkEntities( View     mMasLC )
{
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szClaimsClassification
   String   szClaimsClassification = null;
   //:STRING (   1  ) szUsageType
   String   szUsageType = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:GET VIEW mTempLC NAMED "mTempLC"
   RESULT = GetViewByName( mTempLC, "mTempLC", mMasLC, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mMasLC )
   CreateViewFromView( mTempLC, mMasLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to traverse M_MarketingUsageOrdering entities and delete the work sub-entities.
   //:FOR EACH mTempLC.M_MarketingUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "M_MarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mTempLC.M_MarketingUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mTempLC, "M_MarketingUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mTempLC.M_MarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mTempLC, "M_MarketingUsage", "ClaimsClassification", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringCopy( sb_szClaimsClassification, 1, 0, "Marketing", 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringConcat( sb_szClaimsClassification, 1, 0, szTempString_0, 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
         //:nRC = CheckExistenceOfEntity( mTempLC, szClaimsClassification )
         nRC = CheckExistenceOfEntity( mTempLC, szClaimsClassification );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:DeleteEntity( mTempLC, szClaimsClassification, zREPOS_NONE )
            DeleteEntity( mTempLC, szClaimsClassification, zREPOS_NONE );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:nRC = CheckExistenceOfEntity( mTempLC, "MarketingSurface" )
            nRC = CheckExistenceOfEntity( mTempLC, "MarketingSurface" );
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:DELETE ENTITY mTempLC.MarketingSurface
               RESULT = DeleteEntity( mTempLC, "MarketingSurface", zPOS_NEXT );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:nRC = CheckExistenceOfEntity( mTempLC, "MarketingAppType" )
               nRC = CheckExistenceOfEntity( mTempLC, "MarketingAppType" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:DELETE ENTITY mTempLC.MarketingAppType
                  RESULT = DeleteEntity( mTempLC, "MarketingAppType", zPOS_NEXT );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:nRC = CheckExistenceOfEntity( mTempLC, "MarketingAreasOfUse" )
                  nRC = CheckExistenceOfEntity( mTempLC, "MarketingAreasOfUse" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:DELETE ENTITY mTempLC.MarketingAreasOfUse
                     RESULT = DeleteEntity( mTempLC, "MarketingAreasOfUse", zPOS_NEXT );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mTempLC, "M_MarketingUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadMarketingUsageList( VIEW ViewToWindow,
//:                        VIEW mMasLC_In BASED ON LOD mMasLC )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadMarketingUsageList( View     ViewToWindow,
                          View     mMasLC_In )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC 
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPosLC   BASED ON LOD  mMasLC
   zVIEW    mPosLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mMasLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING ( 32 ) szClaimsClassification
   String   szClaimsClassification = null;
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:INTEGER  lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:CleanupMarketingWorkEntities( mMasLC_In )
   CleanupMarketingWorkEntities( mMasLC_In );

   //:CreateViewFromView( mPosLC, mMasLC_In )
   CreateViewFromView( mPosLC, mMasLC_In );
   //:NAME VIEW mPosLC "mPosLC"
   SetNameForView( mPosLC, "mPosLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mTempLC, mMasLC_In )
   CreateViewFromView( mTempLC, mMasLC_In );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mMasLC, mMasLC_In )
   CreateViewFromView( mMasLC, mMasLC_In );
   //:NAME VIEW mMasLC "mMasLC1"
   SetNameForView( mMasLC, "mMasLC1", null, zLEVEL_TASK );

   //:// Get position on included M_MarketingUsage entities (which will be marked as selected).
   //:SetCursorFirstEntity( mPosLC, "M_MarketingUsageOrdering", "" )
   SetCursorFirstEntity( mPosLC, "M_MarketingUsageOrdering", "" );

   //:// Mark included M_MarketingUsage entities as "selected" and include M_Usage not
   //:// already included into the M_MarketingUsage entity and mark as "not selected".
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lID = mMasLC.M_Usage.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mMasLC, "M_Usage", "ID" );
      lID = mi_lID.intValue( );}
      //:SET CURSOR FIRST mTempLC.M_MarketingUsage WITHIN mTempLC.M_MarketingSection
      //:                                          WHERE mTempLC.M_MarketingUsage.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mTempLC, "M_MarketingUsage", "ID", lID, "M_MarketingSection" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mPosLC.M_MarketingUsage WITHIN mPosLC.M_MarketingSection
         //:                                         WHERE mPosLC.M_MarketingUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mPosLC, "M_MarketingUsage", "ID", lID, "M_MarketingSection" );
         //:mPosLC.M_MarketingUsage.wkSelected = "Y"
         SetAttributeFromString( mPosLC, "M_MarketingUsage", "wkSelected", "Y" );
         //:ELSE
      } 
      else
      { 
         //:CreateEntity( mPosLC, "M_MarketingUsageOrdering", zPOS_AFTER )
         CreateEntity( mPosLC, "M_MarketingUsageOrdering", zPOS_AFTER );
         //:IncludeSubobjectFromSubobject( mPosLC, "M_MarketingUsage",
         //:                               mMasLC, "M_Usage", zPOS_NEXT )
         IncludeSubobjectFromSubobject( mPosLC, "M_MarketingUsage", mMasLC, "M_Usage", zPOS_NEXT );
         //:mPosLC.M_MarketingUsage.wkSelected = ""
         SetAttributeFromString( mPosLC, "M_MarketingUsage", "wkSelected", "" );
      } 

      //:END

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mMasLC.M_Usage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mMasLC.M_Usage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mMasLC, "M_Usage", "ClaimsClassification", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringCopy( sb_szClaimsClassification, 1, 0, "Marketing", 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
          {StringBuilder sb_szClaimsClassification;
         if ( szClaimsClassification == null )
            sb_szClaimsClassification = new StringBuilder( 32 );
         else
            sb_szClaimsClassification = new StringBuilder( szClaimsClassification );
                  ZeidonStringConcat( sb_szClaimsClassification, 1, 0, szTempString_0, 1, 0, 33 );
         szClaimsClassification = sb_szClaimsClassification.toString( );}
         //:CreateEntity( mPosLC, szClaimsClassification, zPOS_AFTER )
         CreateEntity( mPosLC, szClaimsClassification, zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:CreateEntity( mPosLC, "MarketingSurface", zPOS_AFTER )
            CreateEntity( mPosLC, "MarketingSurface", zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:CreateEntity( mPosLC, "MarketingAppType", zPOS_AFTER )
               CreateEntity( mPosLC, "MarketingAppType", zPOS_AFTER );
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:CreateEntity( mPosLC, "MarketingAreasOfUse", zPOS_AFTER )
                  CreateEntity( mPosLC, "MarketingAreasOfUse", zPOS_AFTER );
                  //:ELSE
               } 
               else
               { 
                  //:IssueError( ViewToWindow, 0, 0, "Invalid MarketingUsage Type" )
                  IssueError( ViewToWindow, 0, 0, "Invalid MarketingUsage Type" );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 


   //:END

   //:DropView( mPosLC )
   DropView( mPosLC );
   //:DropView( mTempLC )
   DropView( mTempLC );
   //:DropView( mMasLC )
   DropView( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewStorDispSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveAddNewStorDispSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptStorDispSect( ViewToWindow )
   nRC = AcceptStorDispSect( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitStorDispSectForInsert( ViewToWindow )
      InitStorDispSectForInsert( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal Section",
      //:             "Error saving Storage and Disposal section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal Section", "Error saving Storage and Disposal section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewStorDispStmt( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveAddNewStorDispStmt( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = AcceptStorDispStmt( ViewToWindow )
   nRC = AcceptStorDispStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitStorDispStmtForInsert( ViewToWindow )
      InitStorDispStmtForInsert( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal statement",
      //:             "Error saving Storage and Disposal statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal statement", "Error saving Storage and Disposal statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitCopyNewProductMLC( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
InitCopyNewProductMLC( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mPrimReg.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mPrimReg, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_26( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

   //:ACTIVATE mMasProd EMPTY
   RESULT = ActivateEmptyObjectInstance( mMasProd, "mMasProd", ViewToWindow, zSINGLE );
   //:CREATE ENTITY mMasProd.MasterProduct
   RESULT = CreateEntity( mMasProd, "MasterProduct", zPOS_AFTER );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );
   //:INCLUDE mMasProd.PrimaryRegistrant FROM mMasLC.PrimaryRegistrant
   RESULT = IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant", mMasLC, "PrimaryRegistrant", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:BulkAreasOfUseListMaintenance( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
BulkAreasOfUseListMaintenance( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:// VIEW mMasLC   REGISTERED AS mMasLC

   //:CancelCurrentTemporalSubobject( ViewToWindow, "BulkAreasOfUseListMaintenance: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "BulkAreasOfUseListMaintenance: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// COMMIT mMasLC

   //:wWebXfer.Root.String = "AreasOfUse.csv"
   SetAttributeFromString( wWebXfer, "Root", "String", "AreasOfUse.csv" );
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "AreasOfUse"
// END
} 


//:DIALOG OPERATION
//:BulkAppTypesListMaintenance( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
BulkAppTypesListMaintenance( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:// VIEW mMasLC   REGISTERED AS mMasLC

   //:CancelCurrentTemporalSubobject( ViewToWindow, "BulkAppTypesListMaintenance: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "BulkAppTypesListMaintenance: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// COMMIT mMasLC

   //:wWebXfer.Root.String = "AppTypes.csv"
   SetAttributeFromString( wWebXfer, "Root", "String", "AppTypes.csv" );
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "AppTypes"
// END
} 


//:DIALOG OPERATION
//:BulkSurfacesListMaintenance( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
BulkSurfacesListMaintenance( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:// VIEW mMasLC   REGISTERED AS mMasLC

   //:CancelCurrentTemporalSubobject( ViewToWindow, "BulkSurfacesListMaintenance: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "BulkSurfacesListMaintenance: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// COMMIT mMasLC

   //:wWebXfer.Root.String = "Surfaces.csv"
   SetAttributeFromString( wWebXfer, "Root", "String", "Surfaces.csv" );
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "Surfaces"
// END
} 


//:DIALOG OPERATION
//:BulkClaimsListMaintenance( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
BulkClaimsListMaintenance( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:// VIEW mMasLC   REGISTERED AS mMasLC

   //:CancelCurrentTemporalSubobject( ViewToWindow, "BulkClaimsListMaintenance: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "BulkClaimsListMaintenance: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// COMMIT mMasLC

   //:wWebXfer.Root.String = "Claims.csv"
   SetAttributeFromString( wWebXfer, "Root", "String", "Claims.csv" );
   return( 0 );
// // wWebXfer.Root.CurrentDialog = "wMLC"
// // wWebXfer.Root.CurrentWindow = "Claims"
// END
} 


//:DIALOG OPERATION
//:UpdateMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
UpdateMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:STRING (  50  ) szUserId
   String   szUserId = null;
   //:INTEGER lMasterProductID
   int      lMasterProductID = 0;
   //:INTEGER lMasterLabelContentID
   int      lMasterLabelContentID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mMasProd NAMED "mMasProd"
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:SfGetUserIdForTask( ViewToWindow, szUserId )
   {StringBuilder sb_szUserId;
   if ( szUserId == null )
      sb_szUserId = new StringBuilder( 32 );
   else
      sb_szUserId = new StringBuilder( szUserId );
       SfGetUserIdForTask( ViewToWindow, sb_szUserId );
   szUserId = sb_szUserId.toString( );}
   //:IF szUserId != "Admin" // let admin go through without question ... javascript checked to be sure admin wanted to update Finalized MLC
   if ( ZeidonStringCompare( szUserId, 1, 0, "Admin", 1, 0, 51 ) != 0 )
   { 
      //:IF mMasProd != 0 AND mMasProd.MasterLabelContent.Finalized = "Y"
      if ( mMasProd != null && CompareAttributeToString( mMasProd, "MasterLabelContent", "Finalized", "Y" ) == 0 )
      { 

         //:MessageSend( ViewToWindow, "", "Update Master Label Content",
         //:             "The Master Label Content has been Finalized and cannot be updated.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Update Master Label Content", "The Master Label Content has been Finalized and cannot be updated.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:lMasterProductID = mMasProd.MasterProduct.ID
   {MutableInt mi_lMasterProductID = new MutableInt( lMasterProductID );
       GetIntegerFromAttribute( mi_lMasterProductID, mMasProd, "MasterProduct", "ID" );
   lMasterProductID = mi_lMasterProductID.intValue( );}
   //:lMasterLabelContentID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lMasterLabelContentID = new MutableInt( lMasterLabelContentID );
       GetIntegerFromAttribute( mi_lMasterLabelContentID, mMasProd, "MasterLabelContent", "ID" );
   lMasterLabelContentID = mi_lMasterLabelContentID.intValue( );}

   //:// We have to ensure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:// GET VIEW mMasProd NAMED "mMasProd"  // mMasProd has been dropped by AcceptUpdateMasterProduct
      //:// IF mMasProd != 0
      //://    DropObjectInstance( mMasProd )
      //:// END

      //:GET VIEW mPrimReg NAMED "mPrimReg"
      RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
      //:IF mPrimReg != 0
      if ( getView( mPrimReg ) != null )
      { 
         //:SET CURSOR FIRST mPrimReg.MasterProduct
         //:    WHERE mPrimReg.MasterProduct.ID = lMasterProductID
         RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterProduct", "ID", lMasterProductID, "" );
      } 

      //:// TraceLineS( "mPrimReg: ", "EditMasterLabelContent" )
      //:// DisplayEntityInstance( mPrimReg, "MasterProduct" )
      //:END

      //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = lMasterProductID
      o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lMasterProductID );
      RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasProd "mMasProd"
      SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasProd.MasterLabelContent
      //:    WHERE mMasProd.MasterLabelContent.ID = lMasterLabelContentID
      RESULT = SetCursorFirstEntityByInteger( mMasProd, "MasterLabelContent", "ID", lMasterLabelContentID, "" );

      //:SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wMLC", "PrimaryRegistrantLabel" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:CleanMarketingStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanMarketingStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanMarketingStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanMarketingStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_MarketingStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_MarketingStatement", "Text", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanAppTypesStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanAppTypesStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanAppTypesStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanAppTypesStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanAreasOfUseStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanAreasOfUseStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanAreasOfUseStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanAreasOfUseStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanClaimsStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanClaimsStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanClaimsStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanClaimsStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanDirectionsUseStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanDirectionsUseStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanDirectionsUseStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanDirectionsUseStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_DirectionsForUseStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_DirectionsForUseStatement", "Text", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanFirstAidStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanFirstAidStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanFirstAidStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanFirstAidStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_GeneralStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_GeneralStatement", "Text", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanHazardStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanHazardStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanHazardStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanHazardStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_GeneralStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_GeneralStatement", "Text", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanIngredientsStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanIngredientsStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanIngredientsStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanIngredientsStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_IngredientsStatement", "ChemicalName", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_IngredientsStatement", "ChemicalName", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanPrecautionaryStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanPrecautionaryStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanPrecautionaryStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanPrecautionaryStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_GeneralStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_GeneralStatement", "Text", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanStorDispStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanStorDispStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanStorDispStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanStorDispStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_StorageDisposalStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_StorageDisposalStatement", "Text", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanSurfacesStmtHTML( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CleanSurfacesStmtHTML( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanSurfacesStmtHTML: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "CleanSurfacesStmtHTML: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_Usage", "Name", "p,strong,sup,sub" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSelectedAreasOfUse( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
DeleteSelectedAreasOfUse( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szSelected
   String   szSelected = null;
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" )
   nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szSelected = mMasLC.MI_UsageList.wkSelected
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSelected;
      if ( szSelected == null )
         sb_szSelected = new StringBuilder( 32 );
      else
         sb_szSelected = new StringBuilder( szSelected );
             GetVariableFromAttribute( sb_szSelected, mi_lTempInteger_0, 'S', 2, mMasLC, "MI_UsageList", "wkSelected", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSelected = sb_szSelected.toString( );}
      //:IF szSelected = "Y"
      if ( ZeidonStringCompare( szSelected, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
         nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
         //:IF nRC = zCURSOR_SET
         if ( nRC == zCURSOR_SET )
         { 
            //:szUsageType = mMasLC.M_Usage.UsageType // "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_1, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szUsageType = sb_szUsageType.toString( );}
            //:IF szUsageType = "U"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
            { 
               //:// ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
               //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
               DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" )
      nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSelectedAppTypes( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
DeleteSelectedAppTypes( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 )  szSelected
   String   szSelected = null;
   //:STRING ( 1 )  szUsageType
   String   szUsageType = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" )
   nRC = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szSelected = mMasLC.MI_UsageList.wkSelected
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSelected;
      if ( szSelected == null )
         sb_szSelected = new StringBuilder( 32 );
      else
         sb_szSelected = new StringBuilder( szSelected );
             GetVariableFromAttribute( sb_szSelected, mi_lTempInteger_0, 'S', 2, mMasLC, "MI_UsageList", "wkSelected", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSelected = sb_szSelected.toString( );}
      //:IF szSelected = "Y"
      if ( ZeidonStringCompare( szSelected, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" )
         nRC = SetCursorFirstEntityByEntityCsr( mMasLC, "M_Usage", mMasLC, "MI_UsageList", "" );
         //:IF nRC = zCURSOR_SET
         if ( nRC == zCURSOR_SET )
         { 
            //:szUsageType = mMasLC.M_Usage.UsageType // "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szUsageType;
            if ( szUsageType == null )
               sb_szUsageType = new StringBuilder( 32 );
            else
               sb_szUsageType = new StringBuilder( szUsageType );
                         GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_1, 'S', 2, mMasLC, "M_Usage", "UsageType", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szUsageType = sb_szUsageType.toString( );}
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:// ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE )
               //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
               DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" )
      nRC = SetCursorNextEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END

   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 



}
