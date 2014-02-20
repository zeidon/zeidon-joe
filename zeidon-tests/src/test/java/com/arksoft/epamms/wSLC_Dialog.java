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
import com.arksoft.epamms.mSubLC_Object;
import com.arksoft.epamms.lMLCATgt_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class wSLC_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   private final KZOEP1AA m_KZOEP1AA;
   public wSLC_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:DIALOG OPERATION
//:CancelDeleteSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
CancelDeleteSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   return( 0 );
// // // We don't think there is anything to do here ... just get back to list
// // // after accepting the subregistrant product.
// // AcceptUpdateSubregProduct( ViewToWindow )
// END
} 


private int 
o_fnLocalBuildQual_35( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_16( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lSubregID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lSubregID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_20( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lSubregID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lSubregID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_22( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lContentID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lContentID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_23( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
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
                       int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_26( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_27( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubRegLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubRegLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_28( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_29( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_30( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_31( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_32( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lProductID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lProductID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_33( View     vSubtask,
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
o_fnLocalBuildQual_34( View     vSubtask,
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
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_2( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lSubregID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lSubregID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_5( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_6( View     vSubtask,
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
o_fnLocalBuildQual_7( View     vSubtask,
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
o_fnLocalBuildQual_8( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_9( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_10( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_11( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_12( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_13( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_6 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_6 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_14( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_7 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_7 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_15( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_9 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_9 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:LOCAL OPERATION
//:fnCompareSLC_ToNextMLC( VIEW ViewToWindow, SHORT bNet )

//:   VIEW mSubProd REGISTERED AS mSubProd
private int 
o_fnCompareSLC_ToNextMLC( View     ViewToWindow,
                          int      bNet )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSLCAnal BASED ON LOD  lSLCAnal
   zVIEW    lSLCAnal = new zVIEW( );
   //:VIEW lMLCATgt BASED ON LOD  lMLCATgt
   zVIEW    lMLCATgt = new zVIEW( );
   //:VIEW lMLCASrc BASED ON LOD  lMLCASrc
   zVIEW    lMLCASrc = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Compare SLC (source) to MLC (target)
   //:// Three objects are involved in the comparison.
   //://   lSLCAnal - The SLC being analyzed
   //://   lMLCASrc - The MLC that is was created from
   //://   lMLCATgt - The new MLC generated from lMLCASrc, which is the target of the compare.

   //:ACTIVATE lSLCAnal WHERE lSLCAnal.SubRegLabelContent.ID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_27( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lSLCAnal, "lSLCAnal", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lSLCAnal "lSLCAnal"
   SetNameForView( lSLCAnal, "lSLCAnal", null, zLEVEL_TASK );

   //:IF lSLCAnal.MN_MasterLabelContent DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( lSLCAnal, "MN_MasterLabelContent" );
   if ( lTempInteger_1 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Compare MLS's",
      //:             "The MLC for the selected SLC does not have an MLC generated from it.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Compare MLS's", "The MLC for the selected SLC does not have an MLC generated from it.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:DropObjectInstance( lSLCAnal )
      DropObjectInstance( lSLCAnal );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:ACTIVATE lMLCASrc WHERE lMLCASrc.MasterLabelContent.ID = lSLCAnal.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, lSLCAnal, "MasterLabelContent", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_28( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
   RESULT = ActivateObjectInstance( lMLCASrc, "lMLCASrc", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lMLCASrc "lMLCASrc"
   SetNameForView( lMLCASrc, "lMLCASrc", null, zLEVEL_TASK );

   //:ACTIVATE lMLCATgt WHERE lMLCATgt.MasterLabelContent.ID = lSLCAnal.MN_MasterLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, lSLCAnal, "MN_MasterLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   o_fnLocalBuildQual_29( ViewToWindow, vTempViewVar_2, lTempInteger_3 );
   RESULT = ActivateObjectInstance( lMLCATgt, "lMLCATgt", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:NAME VIEW lMLCATgt "lMLCATgt"
   SetNameForView( lMLCATgt, "lMLCATgt", null, zLEVEL_TASK );

   //:BuildDifferencesSLC( lMLCATgt, lMLCASrc, lSLCAnal )
   {
    lMLCATgt_Object m_lMLCATgt_Object = new lMLCATgt_Object( lMLCATgt );
    m_lMLCATgt_Object.olMLCATgt_BuildDifferencesSLC( lMLCATgt, lMLCASrc, lSLCAnal );
    // m_lMLCATgt_Object = null;  // permit gc  (unnecessary)
   }
   //:IF bNet = 1
   if ( bNet == 1 )
   { 
      //:DeleteEmptyTitles( lMLCATgt )
      {
       lMLCATgt_Object m_lMLCATgt_Object = new lMLCATgt_Object( lMLCATgt );
       m_lMLCATgt_Object.olMLCATgt_DeleteEmptyTitles( lMLCATgt );
       // m_lMLCATgt_Object = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:DropObjectInstance( lMLCASrc )
   DropObjectInstance( lMLCASrc );
   //:DropObjectInstance( lSLCAnal )
   DropObjectInstance( lSLCAnal );
   //:RETURN 0
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewAppType( VIEW ViewToWindow )

//:   VIEW  mSubLC   REGISTERED AS mSubLC
public int 
SaveAddNewAppType( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptAppTypesStmt( ViewToWindow )
   nRC = AcceptAppTypesStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitAppTypesStmtForInsert( ViewToWindow )
      InitAppTypesStmtForInsert( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Application Type Statement",
      //:             "Error saving area of use statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Application Type Statement", "Error saving area of use statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:ImportAppTypesStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAppTypesStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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
      //:MessageSend( ViewToWindow, "", "Import Areas of Use Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas of Use Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mSubLC, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSubLC, szDirectoryName );
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

      //:MessageSend( ViewToWindow, "", "Import Areas of Use Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas of Use Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
//:AcceptAppTypesStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptAppTypesStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAppTypesStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAppTypesStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (AppTypes) entity.  We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (AppTypes) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:// We need to update an S_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitAppTypesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitAppTypesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "T"  // "V"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAppTypesStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAppTypesStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_Usage entity.
   //:// CreateTemporalEntity( mSubLC, "S_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitAppTypesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitAppTypesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_Usage.UsageType = "T" // "C"
   SetAttributeFromString( mSubLC, "S_Usage", "UsageType", "T" );
   //:mSubLC.S_Usage.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "T"  // "V"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.SI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.SI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportAppTypesStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportAppTypesStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitImportAppTypesStatements: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitImportAppTypesStatements: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_UsageList
   RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_UsageList
      RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   } 

   //:END

   //:// We need to create SI_UsageList entities.
   //:FOR EACH mSubLC.S_Usage
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_Usage.UsageType = "T" // "AppTypes"
      if ( CompareAttributeToString( mSubLC, "S_Usage", "UsageType", "T" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList",
         //:                               mSubLC, "S_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList", mSubLC, "S_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitAppTypesSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitAppTypesSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "T"  // "AppTypes"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (AppTypes) entity.  We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (AppTypes) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:wWebXfer.Root.CurrentContentType = "T"  // "V"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
//:AcceptAppTypesSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptAppTypesSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GenerateNewSLC_FromMLC( VIEW ViewToWindow )

//:   VIEW wWebXfer  REGISTERED AS wWebXfer
public int 
GenerateNewSLC_FromMLC( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg   REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd  BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mMasProd  BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mMasLC    BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mSubLC    BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING ( 20 ) szVersion
   String   szVersion = null;
   //:INTEGER       lProductID
   int      lProductID = 0;
   //:INTEGER       lID
   int      lID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "GenerateNewSLC_FromMLC" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "GenerateNewSLC_FromMLC" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END

   //:lID = mSubreg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:lProductID = mSubreg.SubregProduct.ID
   {MutableInt mi_lProductID = new MutableInt( lProductID );
       GetIntegerFromAttribute( mi_lProductID, mSubreg, "SubregProduct", "ID" );
   lProductID = mi_lProductID.intValue( );}

   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lProductID
   o_fnLocalBuildQual_32( ViewToWindow, vTempViewVar_0, lProductID );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = mSubProd.MasterProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "MasterProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_33( ViewToWindow, vTempViewVar_1, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:// Remove these lines prior to deployment!!!
   //:// To get an SLC from a previous version MLC (not the current one).
   //:// IssueError( ViewToWindow, 0, 0, "Build SLC based on previous version of the MLC" )
   //:// End of: Remove these lines prior to deployment!!!

   //:SET CURSOR LAST mMasProd.MasterLabelContent  // this might not be the correct one ... how to determine???
   RESULT = SetCursorLastEntity( mMasProd, "MasterLabelContent", "" );
   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mMasProd, "MasterLabelContent", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_34( ViewToWindow, vTempViewVar_2, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

   //:SetMatchingAttributesByName( mSubProd, "SubregProduct", mMasLC, "MasterProduct", zSET_NULL )
   SetMatchingAttributesByName( mSubProd, "SubregProduct", mMasLC, "MasterProduct", zSET_NULL );
   //:AcceptSubobject( mSubProd, "SubregProduct" )
   AcceptSubobject( mSubProd, "SubregProduct" );
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );

   //:ACTIVATE mSubLC EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubLC, "mSubLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   //:CREATE ENTITY mSubLC.SubregLabelContent
   RESULT = CreateEntity( mSubLC, "SubregLabelContent", zPOS_AFTER );
   //:SetAttributeFromCurrentDateTime( mSubLC, "SubregLabelContent", "RevisionDate" )
   {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
    m_ZGlobal1_Operation.SetAttributeFromCurrentDateTime( mSubLC, "SubregLabelContent", "RevisionDate" );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   }
   //:szVersion = mSubLC.SubregLabelContent.RevisionDate
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szVersion;
   if ( szVersion == null )
      sb_szVersion = new StringBuilder( 32 );
   else
      sb_szVersion = new StringBuilder( szVersion );
       GetVariableFromAttribute( sb_szVersion, mi_lTempInteger_2, 'S', 21, mSubLC, "SubregLabelContent", "RevisionDate", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szVersion = sb_szVersion.toString( );}
   //:zLeft( szVersion, 8, szVersion, 9 )
   {StringBuilder sb_szVersion;
   if ( szVersion == null )
      sb_szVersion = new StringBuilder( 32 );
   else
      sb_szVersion = new StringBuilder( szVersion );
       zLeft( szVersion, 8, sb_szVersion, 9 );
   szVersion = sb_szVersion.toString( );}
   //:mSubLC.SubregLabelContent.Version = szVersion
   SetAttributeFromString( mSubLC, "SubregLabelContent", "Version", szVersion );
   //:mSubLC.SubregLabelContent.Description = "_New"
   SetAttributeFromString( mSubLC, "SubregLabelContent", "Description", "_New" );
   //:mSubLC.SubregLabelContent.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mSubLC, "SubregLabelContent", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );

   //:INCLUDE mSubLC.SubregProduct FROM mSubProd.SubregProduct
   RESULT = IncludeSubobjectFromSubobject( mSubLC, "SubregProduct", mSubProd, "SubregProduct", zPOS_AFTER );

   //:BuildSLC_FromMLC( mSubLC, mMasLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_BuildSLC_FromMLC( mSubLC, mMasLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );

   //:DropObjectInstance( mSubreg )  // in case there were changes
   DropObjectInstance( mSubreg );
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_35( ViewToWindow, vTempViewVar_3, lID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_3, zSINGLE );
   DropView( vTempViewVar_3 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.SubregProduct WHERE mSubreg.SubregProduct.ID = lProductID
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lProductID, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewSPLD( VIEW ViewToWindow )

public int 
NewSPLD( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do
// END
} 


//:DIALOG OPERATION
//:MoveSPLD_Up( VIEW ViewToWindow )

//:   VIEW wWebXfer  REGISTERED AS wWebXfer
public int 
MoveSPLD_Up( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd  REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mTempProd BASED ON LOD  mSubProd
   zVIEW    mTempProd = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempProd, mSubProd )
   CreateViewFromView( mTempProd, mSubProd );
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
      //:SET CURSOR PREVIOUS mTempProd.SubregPhysicalLabelDef
      RESULT = SetCursorPrevEntity( mTempProd, "SubregPhysicalLabelDef", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempProd, "SubregPhysicalLabelDef",
   //:               mSubProd, "SubregPhysicalLabelDef",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempProd, "SubregPhysicalLabelDef", mSubProd, "SubregPhysicalLabelDef", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempProd )
   DropView( mTempProd );

   //:// We now accept the SPLD to maintain order!
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveSPLD_Down( VIEW ViewToWindow )

//:   VIEW wWebXfer  REGISTERED AS wWebXfer
public int 
MoveSPLD_Down( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd  REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mTempProd BASED ON LOD  mSubProd
   zVIEW    mTempProd = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempProd, mSubProd )
   CreateViewFromView( mTempProd, mSubProd );
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
      //:SET CURSOR NEXT mTempProd.SubregPhysicalLabelDef
      RESULT = SetCursorNextEntity( mTempProd, "SubregPhysicalLabelDef", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempProd, "SubregPhysicalLabelDef",
   //:               mSubProd, "SubregPhysicalLabelDef",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempProd, "SubregPhysicalLabelDef", mSubProd, "SubregPhysicalLabelDef", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempProd )
   DropView( mTempProd );

   //:// We now accept the SPLD to maintain order!
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveSLC_Down( VIEW ViewToWindow )

//:   VIEW wWebXfer  REGISTERED AS wWebXfer
public int 
MoveSLC_Down( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd  REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mTempProd BASED ON LOD  mSubProd
   zVIEW    mTempProd = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mSubProd, "SubregProduct" )
   AcceptSubobject( mSubProd, "SubregProduct" );
   //:CreateViewFromView( mTempProd, mSubProd )
   CreateViewFromView( mTempProd, mSubProd );
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
      //:SET CURSOR NEXT mTempProd.SubregLabelContent
      RESULT = SetCursorNextEntity( mTempProd, "SubregLabelContent", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempProd, "SubregLabelContent",
   //:               mSubProd, "SubregLabelContent",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempProd, "SubregLabelContent", mSubProd, "SubregLabelContent", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempProd )
   DropView( mTempProd );

   //:// We now accept the SubProd to maintain order!
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CompareSLC_ToNextMLC( VIEW ViewToWindow )

//:   SHORT nRC
public int 
CompareSLC_ToNextMLC( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = fnCompareSLC_ToNextMLC( ViewToWindow, 0 )
   nRC = o_fnCompareSLC_ToNextMLC( ViewToWindow, 0 );
   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:CompareSLC_ToNextMLC_Net( VIEW ViewToWindow )

//:   SHORT nRC
public int 
CompareSLC_ToNextMLC_Net( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = fnCompareSLC_ToNextMLC( ViewToWindow, 1 )
   nRC = o_fnCompareSLC_ToNextMLC( ViewToWindow, 1 );
   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:DeleteSLC( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
DeleteSLC( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// This prompt must occur from the JavaScript.
   //:// nRC = MessagePrompt( ViewToWindow, "", "Delete", "OK to delete SLC?", 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
   //:// IF nRC = zRESPONSE_NO
   //://    RETURN -1
   //:// END

   //:lID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubProd, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = lID
   o_fnLocalBuildQual_30( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   //:DELETE ENTITY mSubLC.SubregLabelContent
   RESULT = DeleteEntity( mSubLC, "SubregLabelContent", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );

   //:// mSubProd needs to reflect deleted SLC.
   //:lID = mSubProd.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubProd, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lID
   o_fnLocalBuildQual_31( ViewToWindow, vTempViewVar_1, lID );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitCopyNewProductSLC( VIEW ViewToWindow )

//:   VIEW mSubProd BASED ON LOD  mSubProd
public int 
InitCopyNewProductSLC( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mSubProd EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubProd, "mSubProd", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   //:CREATE ENTITY mSubProd.SubregProduct
   RESULT = CreateEntity( mSubProd, "SubregProduct", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveSLC_Up( VIEW ViewToWindow )

//:   VIEW wWebXfer  REGISTERED AS wWebXfer
public int 
MoveSLC_Up( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd  REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mTempProd BASED ON LOD  mSubProd
   zVIEW    mTempProd = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mSubProd, "SubregProduct" )
   AcceptSubobject( mSubProd, "SubregProduct" );
   //:CreateViewFromView( mTempProd, mSubProd )
   CreateViewFromView( mTempProd, mSubProd );
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
      //:SET CURSOR PREVIOUS mTempProd.SubregLabelContent
      RESULT = SetCursorPrevEntity( mTempProd, "SubregLabelContent", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempProd, "SubregLabelContent",
   //:               mSubProd, "SubregLabelContent",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempProd, "SubregLabelContent", mSubProd, "SubregLabelContent", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempProd )
   DropView( mTempProd );

   //:// We now accept the SubProd to maintain order!
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptHazardContent( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptHazardContent( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelHazardContent( View     ViewToWindow )
{

   //:CancelHazardContent( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelHazardContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelHazardContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateSubregLabelContent( VIEW ViewToWindow )

public int 
UpdateSubregLabelContent( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:InitImportClaimsStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportClaimsStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitImportClaimsStatements: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitImportClaimsStatements: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportSurfacesStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportSurfacesStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitImportSurfacesStatements: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitImportSurfacesStatements: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportClaimsStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportClaimsStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mSubLC, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSubLC, szDirectoryName );
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

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportSurfacesStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportSurfacesStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mSubLC, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSubLC, szDirectoryName );
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

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportAreasOfUseStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAreasOfUseStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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
      //:MessageSend( ViewToWindow, "", "Import Areas of Use Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas of Use Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mSubLC, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSubLC, szDirectoryName );
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

      //:MessageSend( ViewToWindow, "", "Import Areas of Use Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Areas of Use Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportAreasOfUseStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportAreasOfUseStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "InitImportAreasOfUseStatements: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "InitImportAreasOfUseStatements: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_MarketingSection
      RESULT = SetCursorPrevEntity( mTempLC, "S_MarketingSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_MarketingSection",
   //:               mSubLC, "S_MarketingSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_MarketingSection", mSubLC, "S_MarketingSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_MarketingSection
      RESULT = SetCursorNextEntity( mTempLC, "S_MarketingSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_MarketingSection",
   //:               mSubLC, "S_MarketingSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_MarketingSection", mSubLC, "S_MarketingSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
//:ConfirmDeleteSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
ConfirmDeleteSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:lID = mSubreg.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}

   //:// // We have to make sure the Product is in good shape before we go on!
   //:// nRC = AcceptUpdateSubregProduct( ViewToWindow )
   //:// IF nRC = 0
   //:   ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lID
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:   DELETE ENTITY mSubProd.SubregProduct
   RESULT = DeleteEntity( mSubProd, "SubregProduct", zPOS_NEXT );
   //:   COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );
   //:   DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:// END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitSubregProductForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregProductForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END

   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubreg.SubregProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "SubregProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );

   //:// wWebXfer.Root.AttemptProductNumber = mSubProd.SubregProduct.Number
   //:// wWebXfer.Root.AttemptContentVersion = mSubProd.SubregLabelContent.Version
   //:// wWebXfer.Root.CurrentContentType = ""

   //:SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSubregProduct( VIEW ViewToWindow )

public int 
DeleteSubregProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:EditSubregLabelVersionData( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
EditSubregLabelVersionData( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditSubregLabelVersionData: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditSubregLabelVersionData: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:lID = mSubLC.SubregLabelContent.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
      lID = mi_lID.intValue( );}
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:IF mSubProd.SubregLabelContent DOES NOT EXIST  // should never happen!!!
   lTempInteger_0 = CheckExistenceOfEntity( mSubProd, "SubregLabelContent" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Product Label Content",
      //:             "The Product Label Content does not exist.  Please initialize it.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Product Label Content", "The Product Label Content does not exist.  Please initialize it.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:lID = mSubProd.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubProd, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSubProd, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow )
   nRC = AcceptUpdateSubregProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:InitListSubregProducts( ViewToWindow )
      InitListSubregProducts( ViewToWindow );
      //:GET VIEW mSubreg NAMED "mSubreg"
      RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubreg.SubregProduct
      //:    WHERE mSubreg.SubregProduct.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lID, "" );

      //:GET VIEW mSubProd NAMED "mSubProd"
      RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
      //:IF mSubProd != 0
      if ( getView( mSubProd ) != null )
      { 
         //:DropObjectInstance( mSubProd )
         DropObjectInstance( mSubProd );
      } 

      //:END

      //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lID
      o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubProd "mSubProd"
      SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubProd.SubregLabelContent
      //:    WHERE mSubProd.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSubProd, "SubregLabelContent", "ID", lContentID, "" );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitSubregLabelContentForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer  REGISTERED AS wWebXfer
public int 
InitSubregLabelContentForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd  REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC    BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:// STRING ( 20 ) szVersion

   //:wWebXfer.Root.AttemptContentVersion = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptContentVersion", "" );
   //:wWebXfer.Root.AttemptRevision = mSubProd.SubregProduct.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptRevision", mSubProd, "SubregProduct", "Name" );

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:ACTIVATE mSubLC EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubLC, "mSubLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );

   //:// SetAttributeFromCurrentDateTime( wWebXfer, "Root", "AttemptContentVersion" )
   //:// szVersion = wWebXfer.Root.AttemptContentVersion
   //:// zLeft( szVersion, 8, szVersion, 9 )
   //:// wWebXfer.Root.AttemptContentVersion = szVersion

   //:// Don't create a temporal version, just delete instance on cancel.
   //:// CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "SubregLabelContent", "InitSubregLabelContentForInsert: " )

   //:SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregLabelContentForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregLabelContentForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptContentVersion = mSubProd.SubregLabelContent.Version
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptContentVersion", mSubProd, "SubregLabelContent", "Version" );
   //:wWebXfer.Root.AttemptRevision = mSubProd.SubregLabelContent.Description
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptRevision", mSubProd, "SubregLabelContent", "Description" );

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SubregLabelContent", "InitSubregLabelContentForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SubregLabelContent", "InitSubregLabelContentForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF mSubLC.MasterLabelContent.CreatedDateTime = ""
   if ( CompareAttributeToString( mSubLC, "MasterLabelContent", "CreatedDateTime", "" ) == 0 )
   { 
      //:mSubLC.MasterLabelContent.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
      SetAttributeFromAttribute( mSubLC, "MasterLabelContent", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   } 

   //:END

   //:IF mSubLC.Subregistrant DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mSubLC, "Subregistrant" );
   if ( lTempInteger_1 != 0 )
   { 
      //:IncludeSubobjectFromSubobject( mSubLC, "SubregProduct",
      //:                               mSubProd, "SubregProduct", zPOS_LAST )
      IncludeSubobjectFromSubobject( mSubLC, "SubregProduct", mSubProd, "SubregProduct", zPOS_LAST );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:wWebXfer.Root.Banner1 = mSubLC.Subregistrant.dNameEPA_Number
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", mSubLC, "Subregistrant", "dNameEPA_Number" );
   //:wWebXfer.Root.Banner2 = mSubProd.SubregProduct.dSubregProductNameNbr
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner2", mSubProd, "SubregProduct", "dSubregProductNameNbr" );
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = mSubLC.PrimaryRegistrant.dRegistrantNameID
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner4", mSubLC, "PrimaryRegistrant", "dRegistrantNameID" );
   //:wWebXfer.Root.Banner5 = mSubProd.MasterProduct.dMasterProductNameNbr
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner5", mSubProd, "MasterProduct", "dMasterProductNameNbr" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewSubregLabelContent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewSubregLabelContent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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
      //:IF SetEntityCursor( mSubLC, "SubregLabelContent", "Version", lControl,
      //:                    szContentVersion, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mSubLC, "SubregLabelContent", "Version", lControl, szContentVersion, "", "", 0, "", "" );
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

   //:mSubLC.SubregLabelContent.Version = szContentVersion
   SetAttributeFromString( mSubLC, "SubregLabelContent", "Version", szContentVersion );
   //:mSubLC.SubregLabelContent.Description = wWebXfer.Root.AttemptRevision
   SetAttributeFromAttribute( mSubLC, "SubregLabelContent", "Description", wWebXfer, "Root", "AttemptRevision" );

   //:IncludeSubobjectFromSubobject( mSubLC, "SubregProduct",
   //:                               mSubProd, "SubregProduct", zPOS_LAST )
   IncludeSubobjectFromSubobject( mSubLC, "SubregProduct", mSubProd, "SubregProduct", zPOS_LAST );

   //:IncludeSubobjectFromSubobject( mSubreg, "SubregLabelContent",
   //:                               mSubLC, "SubregLabelContent", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mSubreg, "SubregLabelContent", mSubLC, "SubregLabelContent", zPOS_BEFORE );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );

   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubLC.SubregProduct.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSubLC, "SubregProduct", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubProd.SubregLabelContent
   //:    WHERE mSubProd.SubregLabelContent.ID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSubLC, "SubregLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSubProd, "SubregLabelContent", "ID", lTempInteger_3, "" );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSubregLabelContent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateSubregLabelContent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

      //:IF mSubLC.SubregLabelContent.Version != szContentVersion
      if ( CompareAttributeToString( mSubLC, "SubregLabelContent", "Version", szContentVersion ) != 0 )
      { 

         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSubProd, "SubregLabelContent", "Version", lControl,
         //:                    szContentVersion, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mSubProd, "SubregLabelContent", "Version", lControl, szContentVersion, "", "", 0, "", "" );
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

         //:mSubLC.SubregLabelContent.Version = szContentVersion
         SetAttributeFromString( mSubLC, "SubregLabelContent", "Version", szContentVersion );
      } 

      //:END
   } 


   //:END

   //:// mSubLC.SubregLabelContent.Version = szContentVersion  <-- done above!
   //:mSubLC.SubregLabelContent.Description = wWebXfer.Root.AttemptRevision
   SetAttributeFromAttribute( mSubLC, "SubregLabelContent", "Description", wWebXfer, "Root", "AttemptRevision" );

   //:AcceptSubobject( mSubLC, "SubregLabelContent" )
   AcceptSubobject( mSubLC, "SubregLabelContent" );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubLC.SubregProduct.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSubLC, "SubregProduct", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubProd.SubregLabelContent
   //:    WHERE mSubProd.SubregLabelContent.ID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSubLC, "SubregLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSubProd, "SubregLabelContent", "ID", lTempInteger_3, "" );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregLabelContent( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelNewSubregLabelContent( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC  REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "SubregLabelContent" )
   CancelSubobject( mSubreg, "SubregLabelContent" );

   //:// No temporal subobject created!
   //:// CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSubregLabelContent: " )

   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregLabelContent( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelUpdateSubregLabelContent( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC  REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "SubregLabelContent" )
   CancelSubobject( mSubreg, "SubregLabelContent" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSubregLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSubregLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DesignSubregLabel( VIEW ViewToWindow )

public int 
DesignSubregLabel( View     ViewToWindow )
{

   return( 0 );
//    // Don't need to do anything except have this OPERATION: to cause
//    // positioning code to be done in the JSP.
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
CancelUpdateSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSubregProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateSubregProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
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
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

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

      //:MessageSend( ViewToWindow, "", "Update Subregistrant Product",
      //:             "The Subregistrant Product Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant Product", "The Subregistrant Product Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:IF szProductName != mSubProd.SubregProduct.Name
      if ( CompareAttributeToString( mSubProd, "SubregProduct", "Name", szProductName ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSubProd, "SubregProduct", "Name", lControl,
         //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mSubProd, "SubregProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
         if ( lTempInteger_1 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Subregistrant Product",
            //:             "The Subregistrant Product Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Subregistrant Product", "The Subregistrant Product Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

      //:MessageSend( ViewToWindow, "", "Update Subregistrant Product",
      //:             "The Subregistrant Product Number cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant Product", "The Subregistrant Product Number cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:IF szProductNumber != mSubProd.SubregProduct.Number
      if ( CompareAttributeToString( mSubProd, "SubregProduct", "Number", szProductNumber ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSubProd, "SubregProduct", "Number", lControl,
         //:                    szProductNumber, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_3 = SetEntityCursor( mSubProd, "SubregProduct", "Number", lControl, szProductNumber, "", "", 0, "", "" );
         if ( lTempInteger_3 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Subregistrant Product",
            //:             "The Subregistrant Product Number must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Subregistrant Product", "The Subregistrant Product Number must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:mSubProd.SubregProduct.Name = szProductName
   SetAttributeFromString( mSubProd, "SubregProduct", "Name", szProductName );
   //:mSubProd.SubregProduct.Number = szProductNumber
   SetAttributeFromString( mSubProd, "SubregProduct", "Number", szProductNumber );
   //:// mMasProd.MasterLabelContent.Version = szVersion
   //:mSubProd.SubregProduct.ChemicalFamily = mSubProd.MasterProduct.ChemicalFamily
   SetAttributeFromAttribute( mSubProd, "SubregProduct", "ChemicalFamily", mSubProd, "MasterProduct", "ChemicalFamily" );
   //:AcceptSubobject( mSubProd, "SubregProduct" )
   AcceptSubobject( mSubProd, "SubregProduct" );
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );

   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// CreateTemporalSubobjectVersion( mSubLC, "S_MarketingStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingStatement", "SelectMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingStatement", "SelectMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "M"  // Marketing
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_HazardSection entity. We have
   //:// position on the S_HazardStatement, but need to get position on
   //:// the S_GeneralStatement that corresponds to the S_HazardStatement.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" );
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "SelectHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "SelectHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // Environmental/Physical Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectHazardSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
SelectHazardSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectFirstAidStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_FirstAidSection entity.  We have
   //:// position on the S_FirstAidStatement, but need to get position on
   //:// the S_GeneralStatement that corresponds to the S_FirstAidStatement.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" );
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "SelectFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "SelectFirstAidStmtForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateDirectionsUseStatement: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateDirectionsUseStatement: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_DirectionsForUseStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_DirectionsForUseStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseStatement", "UpdateDirectionsUseStatement: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseStatement", "UpdateDirectionsUseStatement: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // DirectionsForUse
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_StorageDisposalStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalStatement", "SelectStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalStatement", "SelectStorDispStmtForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_StorageDisposalSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalSection", "SelectStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalSection", "SelectStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptStorDispStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptStorDispStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_StorageDisposalStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_StorageDisposalStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_StorageDisposalStatement", "InitStorDispStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_StorageDisposalStatement", "InitStorDispStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_StorageDisposalStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_StorageDisposalStatement", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an S_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_StorageDisposalStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalStatement", "InitStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalStatement", "InitStorDispStmtForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_StorageDisposalSection entity.
   //:// CreateTemporalEntity( mSubLC, "S_StorageDisposalSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_StorageDisposalSection", "InitStorDispSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_StorageDisposalSection", "InitStorDispSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_StorageDisposalSection.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_StorageDisposalSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitStorDispSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_StorageDisposalSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_StorageDisposalSection", "Title" );

   //:// We need to update the existing S_StorageDisposalSection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_StorageDisposalSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalSection", "InitStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalSection", "InitStorDispSectForUpdate: " );
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
//:InitDirectionsUseStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_DirectionsForUseStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_DirectionsForUseStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_DirectionsForUseStatement", "InitDirectionsUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_DirectionsForUseStatement", "InitDirectionsUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_DirectionsForUseStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_DirectionsForUseStatement", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an S_DirectionsForUseStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_DirectionsForUseStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseStatement", "InitDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseStatement", "InitDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_DirectionsForUseSection entity.
   //:// CreateTemporalEntity( mSubLC, "S_DirectionsForUseSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_DirectionsForUseSection.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_DirectionsForUseSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitDirectionsUseSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_DirectionsForUseSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_DirectionsForUseSection", "Title" );

   //:// We need to update the existing S_DirectionsForUseSection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_DirectionsForUseSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
//:CleanupDirectionsWorkEntities( VIEW mSubLC )

//:   VIEW mTempLC  BASED ON LOD  mSubLC
public int 
CleanupDirectionsWorkEntities( View     mSubLC )
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
   RESULT = GetViewByName( mTempLC, "mTempLC", mSubLC, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to traverse S_DirectionsUsageOrdering entities and delete the work sub-entities.
   //:FOR EACH mTempLC.S_DirectionsUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "S_DirectionsUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mTempLC.S_DirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mTempLC, "S_DirectionsUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mTempLC.S_DirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mTempLC, "S_DirectionsUsage", "ClaimsClassification", "", 0 );
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

      RESULT = SetCursorNextEntity( mTempLC, "S_DirectionsUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptDirectionsUseStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szClaimsClassification
   String   szClaimsClassification = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING (   1  ) szUsageType
   String   szUsageType = null;
   //:INTEGER lSectionTitleLth
   int      lSectionTitleLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:GET VIEW mTempLC NAMED "mTempLC"
   RESULT = GetViewByName( mTempLC, "mTempLC", ViewToWindow, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to exclude S_DirectionsUsage entities that are not selected, but
   //:// the new structure, we need to traverse S_DirectionsUsageOrdering entities.
   //:FOR EACH mTempLC.S_DirectionsUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "S_DirectionsUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szUsageType = mTempLC.S_DirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_1, 'S', 2, mTempLC, "S_DirectionsUsage", "UsageType", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mTempLC.S_DirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 17, mTempLC, "S_DirectionsUsage", "ClaimsClassification", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
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

         //:/*
         //:ELSE
         //:   DisplayEntityInstance( mTempLC, "S_DirectionsUsage" )
         //:   MessageSend( ViewToWindow, "", "Delete Error???",
         //:                "Expected DirectionsClaim.",
         //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:   DropView( mTempLC )
         //:   RETURN 2
         //:*/
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

            //:/*
            //:ELSE
            //:DisplayEntityInstance( mTempLC, "S_DirectionsUsage" )
            //:MessageSend( ViewToWindow, "", "Delete Error???",
            //:             "Expected DirectionsSurface.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            //:DropView( mTempLC )
            //:RETURN 2
            //:*/
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

               //:/*
               //:ELSE
               //:DisplayEntityInstance( mTempLC, "S_DirectionsUsage" )
               //:MessageSend( ViewToWindow, "", "Delete Error???",
               //:          "Expected DirectionsAppType.",
               //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               //:DropView( mTempLC )
               //:RETURN 2
               //:*/
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

                  //:/*
                  //:ELSE
                  //:DisplayEntityInstance( mTempLC, "S_DirectionsUsage" )
                  //:MessageSend( ViewToWindow, "", "Delete Error???",
                  //:       "Expected DirectionsAreasOfUse.",
                  //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  //:DropView( mTempLC )
                  //:RETURN 2
                  //:*/
                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:DisplayEntityInstance( mTempLC, "DirectionsAreasOfUse" )
                  DisplayEntityInstance( mTempLC, "DirectionsAreasOfUse" );
                  //:MessageSend( ViewToWindow, "", "Unexpected Type",
                  //:    szUsageType,
                  //:    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( ViewToWindow, "", "Unexpected Type", szUsageType, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:DropView( mTempLC )
                  DropView( mTempLC );
                  //:RETURN 2
                  if(8==8)return( 2 );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:IF mTempLC.S_DirectionsUsage.wkSelected != "Y"
      if ( CompareAttributeToString( mTempLC, "S_DirectionsUsage", "wkSelected", "Y" ) != 0 )
      { 
         //:// ExcludeEntity( mTempLC, "S_DirectionsUsage", zREPOS_NONE )
         //:DeleteEntity( mTempLC, "S_DirectionsUsageOrdering", zREPOS_NONE )
         DeleteEntity( mTempLC, "S_DirectionsUsageOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "S_DirectionsUsageOrdering", "" );
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
   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAreasOfUseSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptAreasOfUseSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptAreasOfUseStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptMarketingStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szClaimsClassification
   String   szClaimsClassification = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING (   1  ) szUsageType
   String   szUsageType = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:GET VIEW mTempLC NAMED "mTempLC"
   RESULT = GetViewByName( mTempLC, "mTempLC", ViewToWindow, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to exclude S_MarketingUsage entities that are not selected, but
   //:// the new structure, we need to traverse S_MarketingUsageOrdering entities.
   //:FOR EACH mTempLC.S_MarketingUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "S_MarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szUsageType = mTempLC.S_MarketingUsage.UsageType
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_1, 'S', 2, mTempLC, "S_MarketingUsage", "UsageType", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mTempLC.S_MarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 17, mTempLC, "S_MarketingUsage", "ClaimsClassification", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
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

         //:/*
         //:ELSE
         //:   DisplayEntityInstance( mTempLC, "S_MarketingUsage" )
         //:   MessageSend( ViewToWindow, "", "Delete Error???",
         //:                "Expected MarketingClaim.",
         //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:   DropView( mTempLC )
         //:   RETURN 2
         //:*/
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

            //:/*
            //:ELSE
            //:DisplayEntityInstance( mTempLC, "S_MarketingUsage" )
            //:MessageSend( ViewToWindow, "", "Delete Error???",
            //:             "Expected MarketingSurface.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            //:DropView( mTempLC )
            //:RETURN 2
            //:*/
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

               //:/*
               //:ELSE
               //:DisplayEntityInstance( mTempLC, "S_MarketingUsage" )
               //:MessageSend( ViewToWindow, "", "Delete Error???",
               //:          "Expected MarketingAppType.",
               //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               //:DropView( mTempLC )
               //:RETURN 2
               //:*/
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

                  //:/*
                  //:ELSE
                  //:DisplayEntityInstance( mTempLC, "S_MarketingUsage" )
                  //:MessageSend( ViewToWindow, "", "Delete Error???",
                  //:       "Expected MarketingAreasOfUse.",
                  //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  //:DropView( mTempLC )
                  //:RETURN 2
                  //:*/
                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:DisplayEntityInstance( mTempLC, "MarketingAreasOfUse" )
                  DisplayEntityInstance( mTempLC, "MarketingAreasOfUse" );
                  //:MessageSend( ViewToWindow, "", "Unexpected Type",
                  //:    szUsageType,
                  //:    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( ViewToWindow, "", "Unexpected Type", szUsageType, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:DropView( mTempLC )
                  DropView( mTempLC );
                  //:RETURN 2
                  if(8==8)return( 2 );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:IF mTempLC.S_MarketingUsage.wkSelected != "Y"
      if ( CompareAttributeToString( mTempLC, "S_MarketingUsage", "wkSelected", "Y" ) != 0 )
      { 
         //:// ExcludeEntity( mTempLC, "S_MarketingUsage", zREPOS_NONE )
         //:DeleteEntity( mTempLC, "S_MarketingUsageOrdering", zREPOS_NONE )
         DeleteEntity( mTempLC, "S_MarketingUsageOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mTempLC, "S_MarketingUsageOrdering", "" );
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
   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
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
//:InitMarketingStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_MarketingStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_MarketingStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_MarketingStatement", "InitMarketingStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_MarketingStatement", "InitMarketingStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_MarketingStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_MarketingStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an S_MarketingStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_MarketingStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingStatement", "InitMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingStatement", "InitMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_MarketingSection entity.
   //:// CreateTemporalEntity( mSubLC, "S_MarketingSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_MarketingSection", "InitMarketingSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_MarketingSection", "InitMarketingSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_MarketingSection.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_MarketingSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitMarketingSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_MarketingSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_MarketingSection", "Title" );

   //:// We need to update the existing S_MarketingSection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_MarketingSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingSection", "InitMarketingSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingSection", "InitMarketingSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSubLC.S_MarketingSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_MarketingSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSubLC, "S_MarketingSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingSection", "InitMarketingSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingSection", "InitMarketingSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSubLC, "S_MarketingSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_MarketingSection", "InitMarketingSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_MarketingSection", "InitMarketingSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSubLC.S_MarketingSection.BoldItalic = "R"
      SetAttributeFromString( mSubLC, "S_MarketingSection", "BoldItalic", "R" );
   } 

   //:END

   //:LoadMarketingUsageList( ViewToWindow, mSubLC )
   o_LoadMarketingUsageList( ViewToWindow, mSubLC );
   //:wWebXfer.Root.CurrentContentType = "M"  // Marketing
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregProductForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregProductForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END

   //:ACTIVATE mSubProd EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubProd, "mSubProd", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );

   //:CreateEntity( mSubProd, "SubregProduct", zPOS_FIRST )  // want latest version first
   CreateEntity( mSubProd, "SubregProduct", zPOS_FIRST );
   //:// CreateEntity( mSubProd, "SubregLabelContent", zPOS_FIRST )

   //:wWebXfer.Root.AttemptProductName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductName", "" );
   //:wWebXfer.Root.AttemptProductNumber = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductNumber", "" );
   //:// wWebXfer.Root.AttemptContentVersion = "Please specify content version"

   //:SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregProductForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregProductForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END

   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubreg.SubregProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "SubregProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptProductName = mSubProd.SubregProduct.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductName", mSubProd, "SubregProduct", "Name" );
   //:wWebXfer.Root.AttemptProductNumber = mSubProd.SubregProduct.Number
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductNumber", mSubProd, "SubregProduct", "Number" );
   //:// wWebXfer.Root.AttemptContentVersion = mSubProd.MasterLabelContent.Version

   //:CreateTemporalSubobjectVersion( mSubProd, "SubregProduct" )
   CreateTemporalSubobjectVersion( mSubProd, "SubregProduct" );
   //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubProd", "SubregProduct", "InitSubregProductForUpdate: " )
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );
   //:OrderEntityForView( mSubProd, "SubregLabelContent", "CreatedDateTime D" )
   OrderEntityForView( mSubProd, "SubregLabelContent", "CreatedDateTime D" );

   //:wWebXfer.Root.Banner1 = mSubreg.Subregistrant.dNameEPA_Number
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", mSubreg, "Subregistrant", "dNameEPA_Number" );
   //:wWebXfer.Root.Banner2 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner2", "" );
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = mSubreg.PrimaryRegistrant.dNameEPA_Number
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner4", mSubreg, "PrimaryRegistrant", "dNameEPA_Number" );
   //:wWebXfer.Root.Banner5 = mSubProd.MasterProduct.dMasterProductNameNbr
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner5", mSubProd, "MasterProduct", "dMasterProductNameNbr" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptClaimsStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptClaimsStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptClaimsSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptClaimsSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectClaimsStmtForUpdate( VIEW ViewToWindow )

public int 
SelectClaimsStmtForUpdate( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
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
//:InitClaimsStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitClaimsStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_Usage entity.
   //:// CreateTemporalEntity( mSubLC, "S_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitClaimsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitClaimsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_Usage.UsageType = "C" // "Claim"
   SetAttributeFromString( mSubLC, "S_Usage", "UsageType", "C" );
   //:mSubLC.S_Usage.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAreasOfUseStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAreasOfUseStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_Usage entity.
   //:// CreateTemporalEntity( mSubLC, "S_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitAreasOfUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitAreasOfUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_Usage.UsageType = "U" // "AreasOfUse"
   SetAttributeFromString( mSubLC, "S_Usage", "UsageType", "U" );
   //:mSubLC.S_Usage.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_Usage", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (AreasOfUse) entity. We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (AreasOfUse) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:// We need to update an S_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_UsageList
   RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_UsageList
      RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   } 

   //:END

   //:// We need to create SI_UsageList entities.
   //:FOR EACH mSubLC.S_Usage
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_Usage.UsageType = "C" // "Claim"
      if ( CompareAttributeToString( mSubLC, "S_Usage", "UsageType", "C" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList",
         //:                               mSubLC, "S_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList", mSubLC, "S_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitClaimsSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitClaimsSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitClaimsStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitClaimsStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (Claims) entity.  We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (Claims) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:// We need to update an S_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitClaimsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitClaimsStmtForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_UsageList
   RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_UsageList
      RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   } 

   //:END

   //:// We need to create SI_UsageList entities.
   //:FOR EACH mSubLC.S_Usage
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_Usage.UsageType = "S" // "Surface"
      if ( CompareAttributeToString( mSubLC, "S_Usage", "UsageType", "S" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList",
         //:                               mSubLC, "S_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList", mSubLC, "S_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitSurfacesSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitSurfacesSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSurfacesStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptSurfacesStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSurfacesSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptSurfacesSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectSurfacesStmtForUpdate( VIEW ViewToWindow )

public int 
SelectSurfacesStmtForUpdate( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
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
//:InitSurfacesStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSurfacesStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_Usage entity.
   //:// CreateTemporalEntity( mSubLC, "S_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitSurfacesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_Usage", "InitSurfacesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_Usage.UsageType = "S" // "Surface"
   SetAttributeFromString( mSubLC, "S_Usage", "UsageType", "S" );
   //:mSubLC.S_Usage.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSurfacesStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSurfacesStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (Surfaces) entity.  We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (Surfaces) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:// We need to update an S_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitSurfacesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_Usage", "InitSurfacesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptHumanHazardSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptHumanHazardSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", szTempString_0, "" );
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

   //:// We need to create a new S_GeneralStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralStatement", "InitPrecautionaryStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralStatement", "InitPrecautionaryStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_GeneralStatement", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = "P" // "Precautionary"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", "P", "" );
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

   //:// We need to update an S_GeneralStatement entity.
   //:TraceLineS( "InitPrecautionaryStmtForUpdate: ", "S_GeneralStatement" )
   TraceLineS( "InitPrecautionaryStmtForUpdate: ", "S_GeneralStatement" );
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "InitPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "InitPrecautionaryStmtForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_PrecautionarySection
   RESULT = SetCursorFirstEntity( mSubLC, "SI_PrecautionarySection", "" );
   //:LOOP WHILE RESULT = 0
   while ( RESULT == 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_PrecautionarySection", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_PrecautionarySection", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_PrecautionarySection
      RESULT = SetCursorFirstEntity( mSubLC, "SI_PrecautionarySection", "" );
   } 

   //:END

   //:FOR EACH mSubLC.S_GeneralSection
   RESULT = SetCursorFirstEntity( mSubLC, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_GeneralSection.SectionType = "P" // Precautionary
      if ( CompareAttributeToString( mSubLC, "S_GeneralSection", "SectionType", "P" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubLC, "SI_PrecautionarySection",
         //:                               mSubLC, "S_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_PrecautionarySection", mSubLC, "S_GeneralSection", zPOS_LAST );

         //:// We need to create a temporal Precautionary Section entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_PrecautionarySection", "InitPrecautionarySect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_PrecautionarySection", "InitPrecautionarySect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_GeneralSection", "" );
      //:END
   } 

   //:END

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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", szTempString_0, "" );
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

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_GeneralStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralStatement", "InitHazardStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralStatement", "InitHazardStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_GeneralStatement", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = "E" // "Environmental/Physical Hazard"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", "E", "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Environmental/Physical Hazard Statement",
      //:             "The Environmental/Physical Hazard Section does not exist",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Environmental/Physical Hazard Statement", "The Environmental/Physical Hazard Section does not exist", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_GeneralStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "InitHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "InitHazardStmtForUpdate: " );
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

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentContentType = "E"  // Environmental/Physical Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// /* don't think we need this ...
//    VIEW mSubLC   REGISTERED AS mSubLC
//    AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSect: " )
//    FOR EACH mSubLC.S_GeneralSection
//       IF mSubLC.S_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
//          IncludeSubobjectFromSubobject( mSubLC, "SI_HazardSection",
//                                         mSubLC, "S_GeneralSection", zPOS_LAST )
//          // We need to create a temporal Environmental/Physical Hazard Section entity.
//          CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_HazardSection", "InitHazardSect1: " )
//       END
//    END
//  ... don't think we need this */
// END
} 


//:DIALOG OPERATION
//:InitHazardContent( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
InitHazardContent( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_HazardSection
   RESULT = SetCursorFirstEntity( mSubLC, "SI_HazardSection", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_HazardSection", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_HazardSection", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_HazardSection
      RESULT = SetCursorFirstEntity( mSubLC, "SI_HazardSection", "" );
   } 

   //:END

   //:// IF mSubLC.MI_HazardSection DOES NOT EXIST
   //:   FOR EACH mSubLC.S_GeneralSection
   RESULT = SetCursorFirstEntity( mSubLC, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:   IF mSubLC.S_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
      if ( CompareAttributeToString( mSubLC, "S_GeneralSection", "SectionType", "E" ) == 0 )
      { 
         //:   IncludeSubobjectFromSubobject( mSubLC, "SI_HazardSection",
         //:                                  mSubLC, "S_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_HazardSection", mSubLC, "S_GeneralSection", zPOS_LAST );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_GeneralSection", "" );
      //:   END
   } 

   //:   END
   return( 0 );
// // END
// END
} 


//:DIALOG OPERATION
//:InitFirstAidStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitFirstAidStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", szTempString_0, "" );
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

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_GeneralStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralStatement", "InitFirstAidStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralStatement", "InitFirstAidStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_GeneralStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitFirstAidStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = "F" // "FirstAid"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", "F", "" );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Update FirstAid Statement",
      //:             "The FirstAid Section does not exist",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update FirstAid Statement", "The FirstAid Section does not exist", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_GeneralStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "InitFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "InitFirstAidStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// // DisplayEntityInstance( wWebXfer, "Root" )
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_FirstAidSection
   RESULT = SetCursorFirstEntity( mSubLC, "SI_FirstAidSection", "" );
   //:LOOP WHILE RESULT = 0
   while ( RESULT == 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_FirstAidSection", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_FirstAidSection", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_FirstAidSection
      RESULT = SetCursorFirstEntity( mSubLC, "SI_FirstAidSection", "" );
   } 

   //:END

   //:FOR EACH mSubLC.S_GeneralSection
   RESULT = SetCursorFirstEntity( mSubLC, "S_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_GeneralSection.SectionType = "F" // FirstAid
      if ( CompareAttributeToString( mSubLC, "S_GeneralSection", "SectionType", "F" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubLC, "SI_FirstAidSection",
         //:                               mSubLC, "S_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_FirstAidSection", mSubLC, "S_GeneralSection", zPOS_LAST );

         //:// We need to create a temporal FirstAid Section entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_FirstAidSection", "InitFirstAidSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_FirstAidSection", "InitFirstAidSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_GeneralSection", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "F"  // FirstAid
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSubLC.SI_UsageList
   RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSubLC, "SI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSubLC.SI_UsageList
      RESULT = SetCursorFirstEntity( mSubLC, "SI_UsageList", "" );
   } 

   //:END

   //:// We need to create SI_UsageList (AreasOfUse) entities.
   //:FOR EACH mSubLC.S_Usage
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_Usage.UsageType = "U" // "AreasOfUse"
      if ( CompareAttributeToString( mSubLC, "S_Usage", "UsageType", "U" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList",
         //:                               mSubLC, "S_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSubLC, "SI_UsageList", mSubLC, "S_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitAreasOfUseSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "SI_UsageList", "InitAreasOfUseSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSubLC.S_HumanHazardSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_HumanHazardSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSubLC, "S_HumanHazardSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_HumanHazardSection", "InitHumanHazardSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_HumanHazardSection", "InitHumanHazardSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSubLC, "S_HumanHazardSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_HumanHazardSection", "InitHumanHazardSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_HumanHazardSection", "InitHumanHazardSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSubLC.S_HumanHazardSection.BoldItalic = "R"
      SetAttributeFromString( mSubLC, "S_HumanHazardSection", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "InitIngredientsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "InitIngredientsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new S_IngredientsStatement entity.
   //:// CreateTemporalEntity( mSubLC, "S_IngredientsStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_IngredientsStatement", "InitIngredientsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_IngredientsStatement", "InitIngredientsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_IngredientsStatement.Active = "A"
   SetAttributeFromString( mSubLC, "S_IngredientsStatement", "Active", "A" );
   //:mSubLC.S_IngredientsStatement.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_IngredientsStatement", "BoldItalic", "R" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSubLC.S_IngredientsSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_IngredientsSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSubLC, "S_IngredientsSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsSection", "InitIngredientsSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsSection", "InitIngredientsSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSubLC, "S_IngredientsSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_IngredientsSection", "InitIngredientsSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_IngredientsSection", "InitIngredientsSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSubLC.S_IngredientsSection.ActiveBoldItalic = "R"
      SetAttributeFromString( mSubLC, "S_IngredientsSection", "ActiveBoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an S_IngredientsStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_IngredientsStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsStatement", "InitIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsStatement", "InitIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptIngredientsStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptIngredientsStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_IngredientsStatement entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_IngredientsStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsStatement", "SelectIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsStatement", "SelectIngredientsStmtForUpdate: " );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptHazardStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptFirstAidStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptFirstAidStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_PrecautionarySection entity.  We have
   //:// position on the S_PrecautionaryStatement, but need to get position on
   //:// the S_GeneralStatement that corresponds to the S_PrecautionaryStatement.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" );
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "SelectPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralStatement", "SelectPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateSubregProduct( VIEW ViewToWindow )

public int 
UpdateSubregProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:InitListSubregProducts( VIEW ViewToWindow )

//:   VIEW lSubreg  REGISTERED AS lSubreg
public int 
InitListSubregProducts( View     ViewToWindow )
{
   zVIEW    lSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg != 0
   if ( getView( mSubreg ) != null )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END

   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lSubreg.Subregistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSubreg, "Subregistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSLC", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_GeneralSection entity.
   //:// CreateTemporalEntity( mSubLC, "S_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralSection", "InitHazardSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralSection", "InitHazardSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitHazardSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_GeneralSection", "Title" );

   //:// We need to update the existing S_GeneralSection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralSection", "InitHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralSection", "InitHazardSectForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_PrecautionarySection entity.
   //:// CreateTemporalEntity( mSubLC, "S_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralSection", "InitPrecautionarySectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralSection", "InitPrecautionarySectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionarySectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitPrecautionarySectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_GeneralSection", "Title" );

   //:// We need to update the existing S_PrecautionarySection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralSection", "InitPrecautionarySectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralSection", "InitPrecautionarySectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
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
//:InitFirstAidSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitFirstAidSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_FirstAidSection entity.
   //:// CreateTemporalEntity( mSubLC, "S_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralSection", "InitFirstAidSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_GeneralSection", "InitFirstAidSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitFirstAidSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_GeneralSection", "Title" );

   //:// We need to update the existing S_FirstAidSection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralSection", "InitFirstAidSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_GeneralSection", "InitFirstAidSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new S_IngredientsSection entity.
   //:// CreateTemporalEntity( mSubLC, "S_IngredientsSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_IngredientsSection", "InitIngredientsSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_IngredientsSection", "InitIngredientsSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSubLC.S_IngredientsSection.ActiveBoldItalic = "R"
   SetAttributeFromString( mSubLC, "S_IngredientsSection", "ActiveBoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsSectForUpdate( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
InitIngredientsSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSubLC.S_IngredientsSection.ActiveTitle
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSubLC, "S_IngredientsSection", "ActiveTitle" );

   //:// We need to update the existing S_IngredientsSection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_IngredientsSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsSection", "InitIngredientsSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_IngredientsSection", "InitIngredientsSectForUpdate: " );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.Units = "Gallons"
   SetAttributeFromString( wWebXfer, "Root", "Units", "Gallons" );
   //:IF mSubLC.S_StorageDisposalSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_StorageDisposalSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSubLC, "S_StorageDisposalSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalSection", "InitStorDispSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_StorageDisposalSection", "InitStorDispSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSubLC, "S_StorageDisposalSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_StorageDisposalSection", "InitStorDispSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_StorageDisposalSection", "InitStorDispSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSubLC.S_StorageDisposalSection.BoldItalic = "R"
      SetAttributeFromString( mSubLC, "S_StorageDisposalSection", "BoldItalic", "R" );
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
//:InitDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSubLC.S_DirectionsForUseSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "S_DirectionsForUseSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSubLC, "S_DirectionsForUseSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSubLC, "S_DirectionsForUseSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSubLC", "S_DirectionsForUseSection", "InitDirectionsUseSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSubLC.S_DirectionsForUseSection.BoldItalic = "R"
      SetAttributeFromString( mSubLC, "S_DirectionsForUseSection", "BoldItalic", "R" );
   } 

   //:END

   //:LoadDirectionsUsageList( ViewToWindow, mSubLC )
   o_LoadDirectionsUsageList( ViewToWindow, mSubLC );
   //:wWebXfer.Root.CurrentContentType = "U"  // DirectionsForUse
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
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
//:MoveSubregProductUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveSubregProductUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mTempReg BASED ON LOD  mSubreg
   zVIEW    mTempReg = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempReg, mSubreg )
   CreateViewFromView( mTempReg, mSubreg );
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
      //:SET CURSOR PREVIOUS mTempReg.SubregProduct
      RESULT = SetCursorPrevEntity( mTempReg, "SubregProduct", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempReg, "SubregProduct",
   //:               mSubreg, "SubregProduct",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempReg, "SubregProduct", mSubreg, "SubregProduct", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempReg )
   DropView( mTempReg );

   //:// We now accept the Master Label to maintain order!
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewSubregProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewSubregProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
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
   //:INTEGER lProductID
   int      lProductID = 0;
   //:INTEGER lSubregID
   int      lSubregID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

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

      //:MessageSend( ViewToWindow, "", "New Subregistrant Product",
      //:             "The Product Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant Product", "The Product Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:IF SetEntityCursor( mSubreg, "SubregProduct", "Name", lControl,
      //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mSubreg, "SubregProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Subregistrant Product",
         //:             "The Product Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Subregistrant Product", "The Product Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:IF SetEntityCursor( mSubreg, "SubregProduct", "Number", lControl,
      //:                    szProductNumber, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_3 = SetEntityCursor( mSubreg, "SubregProduct", "Number", lControl, szProductNumber, "", "", 0, "", "" );
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
   //:// IF SetEntityCursor( mSubProd, "SubregLabelContent", "Version", lControl,
   //://                     szVersion, "", "", 0, "", "" ) >= zCURSOR_SET
   //://    MessageSend( ViewToWindow, "", "New Master Product",
   //://                 "The Master Label Version must be unique.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    RETURN 2
   //:// END
   //:END
   //:*/

   //:// Preliminary check is performed by the JSP to ensure that exactly one Master Product is selected.
   //:nRC = 2
   nRC = 2;
   //:IF mSubreg.PrimaryRegistrant EXISTS
   lTempInteger_4 = CheckExistenceOfEntity( mSubreg, "PrimaryRegistrant" );
   if ( lTempInteger_4 == 0 )
   { 

      //:SET CURSOR FIRST mSubreg.ListMasterProduct WHERE mSubreg.ListMasterProduct.wkSelected = "Y"
      RESULT = SetCursorFirstEntityByString( mSubreg, "ListMasterProduct", "wkSelected", "Y", "" );
      //:IF RESULT = 0
      if ( RESULT == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubProd, "MasterProduct",
         //:                               mSubreg, "ListMasterProduct", zPOS_BEFORE )
         IncludeSubobjectFromSubobject( mSubProd, "MasterProduct", mSubreg, "ListMasterProduct", zPOS_BEFORE );
         //:mSubProd.SubregProduct.ChemicalFamily = mSubProd.MasterProduct.ChemicalFamily
         SetAttributeFromAttribute( mSubProd, "SubregProduct", "ChemicalFamily", mSubProd, "MasterProduct", "ChemicalFamily" );
         //:nRC = 0
         nRC = 0;
      } 

      //:END
   } 

   //:END

   //:IF nRC != 0
   if ( nRC != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant Product",
      //:             "One Master Product must be selected.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant Product", "One Master Product must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:mSubProd.SubregProduct.Name = szProductName
   SetAttributeFromString( mSubProd, "SubregProduct", "Name", szProductName );
   //:mSubProd.SubregProduct.Number = szProductNumber
   SetAttributeFromString( mSubProd, "SubregProduct", "Number", szProductNumber );
   //:// mSubProd.SubregLabelContent.Version = szVersion
   //:IncludeSubobjectFromSubobject( mSubProd, "Subregistrant",
   //:                               mSubreg, "Subregistrant", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mSubProd, "Subregistrant", mSubreg, "Subregistrant", zPOS_BEFORE );
   //:AcceptSubobject( mSubProd, "SubregProduct" )
   AcceptSubobject( mSubProd, "SubregProduct" );
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );

   //:lProductID = mSubProd.SubregProduct.ID
   {MutableInt mi_lProductID = new MutableInt( lProductID );
       GetIntegerFromAttribute( mi_lProductID, mSubProd, "SubregProduct", "ID" );
   lProductID = mi_lProductID.intValue( );}
   //:lSubregID = mSubreg.Subregistrant.ID
   {MutableInt mi_lSubregID = new MutableInt( lSubregID );
       GetIntegerFromAttribute( mi_lSubregID, mSubreg, "Subregistrant", "ID" );
   lSubregID = mi_lSubregID.intValue( );}

   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );

   //:// Set up for the following GenerateNewSLC_FromMLC.
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lSubregID
   o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0, lSubregID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.SubregProduct WHERE mSubreg.SubregProduct.ID = lProductID
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lProductID, "" );

   //:GenerateNewSLC_FromMLC( ViewToWindow )
   GenerateNewSLC_FromMLC( ViewToWindow );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
CancelNewSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:IF mSubProd != 0
   if ( getView( mSubProd ) != null )
   { 
      //:DropObjectInstance( mSubProd )
      DropObjectInstance( mSubProd );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptPrecautionaryStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptPrecautionaryStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING (  50  ) szSectionTitle
   String   szSectionTitle = null;
   //:INTEGER lSectionTitleLth
   int      lSectionTitleLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section title is not blank.
   //:szSectionTitle = mSubLC.S_GeneralSection.Title
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szSectionTitle;
   if ( szSectionTitle == null )
      sb_szSectionTitle = new StringBuilder( 32 );
   else
      sb_szSectionTitle = new StringBuilder( szSectionTitle );
       GetVariableFromAttribute( sb_szSectionTitle, mi_lTempInteger_0, 'S', 51, mSubLC, "S_GeneralSection", "Title", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szSectionTitle = sb_szSectionTitle.toString( );}
   //:lSectionTitleLth = zGetStringLen( szSectionTitle )
   lSectionTitleLth = zGetStringLen( szSectionTitle );
   //:TraceLineS( "Product Section Title: ", szSectionTitle )
   TraceLineS( "Product Section Title: ", szSectionTitle );
   //:TraceLineI( "Product Section Title Length: ", lSectionTitleLth )
   TraceLineI( "Product Section Title Length: ", lSectionTitleLth );
   //:IF lSectionTitleLth < 1
   if ( lSectionTitleLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "Accept Precautionary Section",
      //:             "The Precautionary Section Title cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Accept Precautionary Section", "The Precautionary Section Title cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_1, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF szEntityName = "S_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "S_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSubLC.S_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSubLC,
      //:                         "S_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubLC );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSubLC, "S_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSubLC, szEntityName,
      //:                               mSubLC, "S_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSubLC, szEntityName, mSubLC, "S_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:FinalizeSLC( VIEW ViewToWindow )

public int 
FinalizeSLC( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveSLC( VIEW ViewToWindow )

public int 
SaveSLC( View     ViewToWindow )
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
   //:VIEW mSubLC REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:STRING ( 50 ) szSectionName
   String   szSectionName = null;
   //:STRING ( 50 ) szSectionNameNew
   String   szSectionNameNew = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mSubLC, "S_PrecautionarySection" )
   AcceptSubobject( mSubLC, "S_PrecautionarySection" );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //://?szSectionName = mSubLC.S_PrecautionarySection.Title
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

   //:// We may update the existing S_PrecautionarySection entity.
   //:// CreateTemporalSubobjectVersion( mSubLC, "S_PrecautionarySection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_PrecautionarySection", "InitSplitMasterSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_PrecautionarySection", "InitSplitMasterSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
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
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

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

      //:nRC = SetEntityCursor( mSubLC, "S_PrecautionarySection", "Name", lControl,
      //:                       szSectionName, "", "", 0, "", "" )
      nRC = SetEntityCursor( mSubLC, "S_PrecautionarySection", "Name", lControl, szSectionName, "", "", 0, "", "" );
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

      //:nRC = SetEntityCursor( mSubLC, "S_PrecautionarySection", "Name", lControl,
      //:                       szSectionName, "", "", 0, "", "" )
      nRC = SetEntityCursor( mSubLC, "S_PrecautionarySection", "Name", lControl, szSectionName, "", "", 0, "", "" );
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

      //:CreateViewFromView( mTempLC, mSubLC )
      CreateViewFromView( mTempLC, mSubLC );
      //:CreateEntity( mTempLC, "S_PrecautionarySection", zPOS_BEFORE )
      CreateEntity( mTempLC, "S_PrecautionarySection", zPOS_BEFORE );
      //:SetMatchingAttributesByName( mTempLC, "S_PrecautionarySection",
      //:                             mSubLC, "S_PrecautionarySection", zSET_NOTNULL )
      SetMatchingAttributesByName( mTempLC, "S_PrecautionarySection", mSubLC, "S_PrecautionarySection", zSET_NOTNULL );
      //://?   mTempLC.S_PrecautionarySection.Title = wWebXfer.Root.NameBefore
      //:mTempLC.S_PrecautionaryStatement.Text = wWebXfer.Root.SplitHTML_Before
      SetAttributeFromAttribute( mTempLC, "S_PrecautionaryStatement", "Text", wWebXfer, "Root", "SplitHTML_Before" );
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 


   //:END

   //:IF wWebXfer.Root.SplitHTML_After != ""
   if ( CompareAttributeToString( wWebXfer, "Root", "SplitHTML_After", "" ) != 0 )
   { 

      //:CreateViewFromView( mTempLC, mSubLC )
      CreateViewFromView( mTempLC, mSubLC );
      //:CreateEntity( mTempLC, "S_PrecautionarySection", zPOS_AFTER )
      CreateEntity( mTempLC, "S_PrecautionarySection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mTempLC, "S_PrecautionarySection",
      //:                             mSubLC, "S_PrecautionarySection", zSET_NOTNULL )
      SetMatchingAttributesByName( mTempLC, "S_PrecautionarySection", mSubLC, "S_PrecautionarySection", zSET_NOTNULL );
      //://?   mTempLC.S_PrecautionarySection.Title = wWebXfer.Root.NameAfter
      //:mTempLC.S_PrecautionaryStatement.Text = wWebXfer.Root.SplitHTML_After
      SetAttributeFromAttribute( mTempLC, "S_PrecautionaryStatement", "Text", wWebXfer, "Root", "SplitHTML_After" );
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END

   //:AcceptSubobject( mSubLC, "S_PrecautionarySection" )
   AcceptSubobject( mSubLC, "S_PrecautionarySection" );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

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
//:DeleteMasterSect( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
DeleteMasterSect( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubLC REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
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

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSubLC, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mSubLC.S_PrecautionarySection.ID

   //:// We have to make sure the Content is in good shape before we go on!
   //:nRC = AcceptUpdateSubregLabelContent( ViewToWindow )
   nRC = AcceptUpdateSubregLabelContent( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = lID
      o_fnLocalBuildQual_17( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubLC "mSubLC"
      SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubLC.SubregLabelContent
      //:    WHERE mSubLC.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "SubregLabelContent", "ID", lContentID, "" );
   } 

   //://?   SET CURSOR FIRST mSubLC.S_PrecautionarySection
   //://?       WHERE mSubLC.S_PrecautionarySection.ID = lSectionID
   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterSect( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
ConfirmDeleteMasterSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //://?DELETE ENTITY mSubLC.S_PrecautionarySection
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteMasterSect( VIEW ViewToWindow )

public int 
CancelDeleteMasterSect( View     ViewToWindow )
{

   return( 0 );
// // VIEW mSubLC REGISTERED AS mSubLC
// END
} 


//:DIALOG OPERATION
//:MoveMasterSectUp( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
MoveMasterSectUp( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempLC  BASED ON LOD  mSubLC
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

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSubLC, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mSubLC.S_PrecautionarySection.ID

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateSubregLabelContent( ViewToWindow )
   nRC = AcceptUpdateSubregLabelContent( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = lID
      o_fnLocalBuildQual_18( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubLC "mSubLC"
      SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubLC.SubregLabelContent
      //:    WHERE mSubLC.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "SubregLabelContent", "ID", lContentID, "" );
      //://?   SET CURSOR FIRST mSubLC.S_PrecautionarySection
      //://?       WHERE mSubLC.S_PrecautionarySection.ID = lSectionID

      //:CreateViewFromView( mTempLC, mSubLC )
      CreateViewFromView( mTempLC, mSubLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //://?   SET CURSOR PREVIOUS mTempLC.S_PrecautionarySection
      //:MoveSubobject( mTempLC, "S_PrecautionarySection",
      //:               mSubLC, "S_PrecautionarySection",
      //:               zPOS_PREV, zREPOS_PREV )
      MoveSubobject( mTempLC, "S_PrecautionarySection", mSubLC, "S_PrecautionarySection", zPOS_PREV, zREPOS_PREV );
      //:DropView( mTempLC )
      DropView( mTempLC );
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMasterSectDown( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
MoveMasterSectDown( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempLC  BASED ON LOD  mSubLC
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

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSubLC, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mSubLC.S_PrecautionarySection.ID

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateSubregLabelContent( ViewToWindow )
   nRC = AcceptUpdateSubregLabelContent( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = lID
      o_fnLocalBuildQual_19( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubLC "mSubLC"
      SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubLC.SubregLabelContent
      //:    WHERE mSubLC.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "SubregLabelContent", "ID", lContentID, "" );
      //://?   SET CURSOR FIRST mSubLC.S_PrecautionarySection
      //://?       WHERE mSubLC.S_PrecautionarySection.ID = lSectionID

      //:CreateViewFromView( mTempLC, mSubLC )
      CreateViewFromView( mTempLC, mSubLC );
      //:NAME VIEW mTempLC "mTempLC"
      SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
      //://?   SET CURSOR NEXT mTempLC.S_PrecautionarySection
      //:MoveSubobject( mTempLC, "S_PrecautionarySection",
      //:               mSubLC, "S_PrecautionarySection",
      //:               zPOS_NEXT, zREPOS_NEXT )
      MoveSubobject( mTempLC, "S_PrecautionarySection", mSubLC, "S_PrecautionarySection", zPOS_NEXT, zREPOS_NEXT );
      //:DropView( mTempLC )
      DropView( mTempLC );
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveSubregProductDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveSubregProductDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mTempReg BASED ON LOD  mSubreg
   zVIEW    mTempReg = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempReg, mSubreg )
   CreateViewFromView( mTempReg, mSubreg );
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
      //:SET CURSOR NEXT mTempReg.SubregProduct
      RESULT = SetCursorNextEntity( mTempReg, "SubregProduct", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempReg, "SubregProduct",
   //:               mSubreg, "SubregProduct",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempReg, "SubregProduct", mSubreg, "SubregProduct", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempReg )
   DropView( mTempReg );

   //:// We now accept the Master Label to maintain order!
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSubregLabelContent( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
DeleteSubregLabelContent( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:lID = mSubreg.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow )
   nRC = AcceptUpdateSubregProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitListSubregProducts( ViewToWindow )
      InitListSubregProducts( ViewToWindow );
      //:GET VIEW mSubreg NAMED "mSubreg"
      RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubreg.SubregProduct
      //:    WHERE mSubreg.SubregProduct.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lID, "" );

      //:ACTIVATE mSubLC WHERE mSubLC.SubregProduct.ID = lID
      o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubLC "mSubLC"
      SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:CopySLC_ToNewVersion( VIEW ViewToWindow )

public int 
CopySLC_ToNewVersion( View     ViewToWindow )
{

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
//:NewSubregProduct( VIEW ViewToWindow )

public int 
NewSubregProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here
// END
} 


//:DIALOG OPERATION
//:NewSubregLabelContent( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
NewSubregLabelContent( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewSubregLabelContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewSubregLabelContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:lID = mSubLC.SubregLabelContent.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
      lID = mi_lID.intValue( );}
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   //:lID = mSubProd.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubProd, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow )
   nRC = AcceptUpdateSubregProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:InitListSubregProducts( ViewToWindow )
      InitListSubregProducts( ViewToWindow );
      //:GET VIEW mSubreg NAMED "mSubreg"
      RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubreg.SubregProduct
      //:    WHERE mSubreg.SubregProduct.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lID, "" );

      //:GET VIEW mSubProd NAMED "mSubProd"
      RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
      //:IF mSubProd != 0
      if ( getView( mSubProd ) != null )
      { 
         //:DropObjectInstance( mSubProd )
         DropObjectInstance( mSubProd );
      } 

      //:END

      //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lID
      o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubProd "mSubProd"
      SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_PrecautionaryStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_PrecautionaryStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_PrecautionaryStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralStatement", "ID",
   //://                             mSubLC, "S_PrecautionaryStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralStatement", "ID",
   //://                             mTempLC, "S_PrecautionaryStatement", "ID", "" )

   //:MoveSubobject( mTempLC, "S_PrecautionaryStatement",
   //:               mSubLC, "S_PrecautionaryStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_PrecautionaryStatement", mSubLC, "S_PrecautionaryStatement", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "S_GeneralStatement",
   //:               mSubLC, "S_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_GeneralStatement", mSubLC, "S_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_PrecautionaryStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_PrecautionaryStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_PrecautionaryStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralStatement", "ID",
   //://                             mSubLC, "S_PrecautionaryStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralStatement", "ID",
   //://                             mTempLC, "S_PrecautionaryStatement", "ID", "" )

   //:MoveSubobject( mTempLC, "S_PrecautionaryStatement",
   //:               mSubLC, "S_PrecautionaryStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_PrecautionaryStatement", mSubLC, "S_PrecautionaryStatement", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "S_GeneralStatement",
   //:               mSubLC, "S_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_GeneralStatement", mSubLC, "S_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_DirectionsForUseStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_DirectionsForUseStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_DirectionsForUseStatement",
   //:               mSubLC, "S_DirectionsForUseStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_DirectionsForUseStatement", mSubLC, "S_DirectionsForUseStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_DirectionsForUseSection
      RESULT = SetCursorNextEntity( mTempLC, "S_DirectionsForUseSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_DirectionsForUseSection",
   //:               mSubLC, "S_DirectionsForUseSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_DirectionsForUseSection", mSubLC, "S_DirectionsForUseSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_DirectionsForUseSection
      RESULT = SetCursorPrevEntity( mTempLC, "S_DirectionsForUseSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_DirectionsForUseSection",
   //:               mSubLC, "S_DirectionsForUseSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_DirectionsForUseSection", mSubLC, "S_DirectionsForUseSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.SI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.SI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.SI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.SI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.SI_UsageList
      RESULT = SetCursorNextEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.SI_UsageList
      RESULT = SetCursorPrevEntity( mTempLC, "SI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, "SI_UsageList", "" );

   //:MoveSubobject( mTempLC, "S_Usage",
   //:               mSubLC, "S_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_Usage", mSubLC, "S_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "SI_UsageList",
   //:               mSubLC, "SI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "SI_UsageList", mSubLC, "SI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_DirectionsForUseStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_DirectionsForUseStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_DirectionsForUseStatement",
   //:               mSubLC, "S_DirectionsForUseStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_DirectionsForUseStatement", mSubLC, "S_DirectionsForUseStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_MarketingStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_MarketingStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_MarketingStatement",
   //:               mSubLC, "S_MarketingStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_MarketingStatement", mSubLC, "S_MarketingStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_MarketingStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_MarketingStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_MarketingStatement",
   //:               mSubLC, "S_MarketingStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_MarketingStatement", mSubLC, "S_MarketingStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_StorageDisposalStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_StorageDisposalStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_StorageDisposalStatement",
   //:               mSubLC, "S_StorageDisposalStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_StorageDisposalStatement", mSubLC, "S_StorageDisposalStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_StorageDisposalStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_StorageDisposalStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_StorageDisposalStatement",
   //:               mSubLC, "S_StorageDisposalStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_StorageDisposalStatement", mSubLC, "S_StorageDisposalStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_StorageDisposalSection
      RESULT = SetCursorNextEntity( mTempLC, "S_StorageDisposalSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_StorageDisposalSection",
   //:               mSubLC, "S_StorageDisposalSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_StorageDisposalSection", mSubLC, "S_StorageDisposalSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_StorageDisposalSection
      RESULT = SetCursorPrevEntity( mTempLC, "S_StorageDisposalSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_StorageDisposalSection",
   //:               mSubLC, "S_StorageDisposalSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_StorageDisposalSection", mSubLC, "S_StorageDisposalSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_IngredientsStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_IngredientsStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_IngredientsStatement",
   //:               mSubLC, "S_IngredientsStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_IngredientsStatement", mSubLC, "S_IngredientsStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_IngredientsStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_IngredientsStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempLC, "S_IngredientsStatement",
   //:               mSubLC, "S_IngredientsStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_IngredientsStatement", mSubLC, "S_IngredientsStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_HazardStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_HazardStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_HazardStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralStatement", "ID",
   //://                             mSubLC, "S_HazardStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralStatement", "ID",
   //://                             mTempLC, "S_HazardStatement", "ID", "" )

   //:MoveSubobject( mTempLC, "S_HazardStatement",
   //:               mSubLC, "S_HazardStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_HazardStatement", mSubLC, "S_HazardStatement", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "S_GeneralStatement",
   //:               mSubLC, "S_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_GeneralStatement", mSubLC, "S_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.SI_HazardSection
      RESULT = SetCursorNextEntity( mTempLC, "SI_HazardSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralSection", mTempLC, "SI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralSection", mTempLC, "SI_HazardSection", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralSection", "ID",
   //://                             mSubLC, "S_HazardSection", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralSection", "ID",
   //://                             mTempLC, "S_HazardSection", "ID", "" )

   //:MoveSubobject( mTempLC, "SI_HazardSection",
   //:               mSubLC, "SI_HazardSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "SI_HazardSection", mSubLC, "SI_HazardSection", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "S_GeneralSection",
   //:               mSubLC, "S_GeneralSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_GeneralSection", mSubLC, "S_GeneralSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.SI_HazardSection
      RESULT = SetCursorPrevEntity( mTempLC, "SI_HazardSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralSection", mTempLC, "SI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralSection", mTempLC, "SI_HazardSection", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralSection", "ID",
   //://                             mSubLC, "S_HazardSection", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralSection", "ID",
   //://                             mTempLC, "S_HazardSection", "ID", "" )

   //:MoveSubobject( mTempLC, "SI_HazardSection",
   //:               mSubLC, "SI_HazardSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "SI_HazardSection", mSubLC, "SI_HazardSection", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "S_GeneralSection",
   //:               mSubLC, "S_GeneralSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_GeneralSection", mSubLC, "S_GeneralSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_HazardStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_HazardStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_HazardStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralStatement", "ID",
   //://                             mSubLC, "S_HazardStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralStatement", "ID",
   //://                             mTempLC, "S_HazardStatement", "ID", "" )

   //:MoveSubobject( mTempLC, "S_HazardStatement",
   //:               mSubLC, "S_HazardStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_HazardStatement", mSubLC, "S_HazardStatement", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "S_GeneralStatement",
   //:               mSubLC, "S_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_GeneralStatement", mSubLC, "S_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR NEXT mTempLC.S_FirstAidStatement
      RESULT = SetCursorNextEntity( mTempLC, "S_FirstAidStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_FirstAidStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralStatement", "ID",
   //://                             mSubLC, "S_FirstAidStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralStatement", "ID",
   //://                             mTempLC, "S_FirstAidStatement", "ID", "" )

   //:MoveSubobject( mTempLC, "S_FirstAidStatement",
   //:               mSubLC, "S_FirstAidStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_FirstAidStatement", mSubLC, "S_FirstAidStatement", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempLC, "S_GeneralStatement",
   //:               mSubLC, "S_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempLC, "S_GeneralStatement", mSubLC, "S_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
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
      //:SET CURSOR PREVIOUS mTempLC.S_FirstAidStatement
      RESULT = SetCursorPrevEntity( mTempLC, "S_FirstAidStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, "S_FirstAidStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSubLC, "S_GeneralStatement", "ID",
   //://                             mSubLC, "S_FirstAidStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempLC, "S_GeneralStatement", "ID",
   //://                             mTempLC, "S_FirstAidStatement", "ID", "" )

   //:MoveSubobject( mTempLC, "S_FirstAidStatement",
   //:               mSubLC, "S_FirstAidStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_FirstAidStatement", mSubLC, "S_FirstAidStatement", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempLC, "S_GeneralStatement",
   //:               mSubLC, "S_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempLC, "S_GeneralStatement", mSubLC, "S_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempLC )
   DropView( mTempLC );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
SelectPrecautionaryStmtForDelete( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_PrecautionaryStatement", "" );
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
//:ConfirmDeletePrecautionaryStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeletePrecautionaryStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeletePrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeletePrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_GeneralStatement
   RESULT = DeleteEntity( mSubLC, "S_GeneralStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectDirectionsUseStmtForDelete( VIEW ViewToWindow )

public int 
SelectDirectionsUseStmtForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (AreasOfUse) entity. We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (AreasOfUse) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
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
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (Claims) entity.  We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (Claims) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteSurfacesStmt( View     ViewToWindow )
{

   //:CancelDeleteSurfacesStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteSurfacesStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmDeleteSurfacesStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_Usage
   RESULT = DeleteEntity( mSubLC, "S_Usage", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMarketingStmtForDelete( VIEW ViewToWindow )

public int 
SelectMarketingStmtForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
SelectHazardStmtForDelete( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_HazardStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectHazardSectForDelete( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
SelectHazardSectForDelete( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralSection", mSubLC, "SI_HazardSection", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectFirstAidStmtForDelete( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
SelectFirstAidStmtForDelete( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_GeneralStatement", mSubLC, "S_FirstAidStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteFirstAidStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteFirstAidStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_GeneralStatement
   RESULT = DeleteEntity( mSubLC, "S_GeneralStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteEnvironStmt( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteEnvironStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteEnvironStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteEnvironStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_GeneralStatement
   RESULT = DeleteEntity( mSubLC, "S_GeneralStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteEnvironStmt( View     ViewToWindow )
{

   //:CancelDeleteEnvironStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteEnvironStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteEnvironStmt: " );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteIngredientStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteIngredientStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteIngredientStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_IngredientsStatement
   RESULT = DeleteEntity( mSubLC, "S_IngredientsStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteStorDispStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_StorageDisposalStatement
   RESULT = DeleteEntity( mSubLC, "S_StorageDisposalStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteStorDispSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteStorDispSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_StorageDisposalSection
   RESULT = DeleteEntity( mSubLC, "S_StorageDisposalSection", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteMarketingStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_MarketingStatement
   RESULT = DeleteEntity( mSubLC, "S_MarketingStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMarketingSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteMarketingSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_MarketingSection
   RESULT = DeleteEntity( mSubLC, "S_MarketingSection", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_DirectionsForUseStatement
   RESULT = DeleteEntity( mSubLC, "S_DirectionsForUseStatement", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
ConfirmDeleteDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_DirectionsForUseSection
   RESULT = DeleteEntity( mSubLC, "S_DirectionsForUseSection", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteClaimsStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmDeleteClaimsStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_Usage
   RESULT = DeleteEntity( mSubLC, "S_Usage", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelDeleteClaimsStmt( View     ViewToWindow )
{

   //:CancelDeleteClaimsStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
public int 
CancelDeleteAppTypesStmt( View     ViewToWindow )
{

   //:CancelDeleteAppTypesStmt( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelDeleteAppTypesStmt: " );
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
//:ConfirmDeleteAppTypesStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmDeleteAppTypesStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSubLC.S_Usage
   RESULT = DeleteEntity( mSubLC, "S_Usage", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "T"  // "AppTypes"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
      //:InitMarketingStmtForInsert( ViewToWindow )
      InitMarketingStmtForInsert( ViewToWindow );
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
      //:InitMarketingSectForInsert( ViewToWindow )
      InitMarketingSectForInsert( ViewToWindow );
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
//:NextMarketingSect( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
NextMarketingSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
   lControl = zPOS_NEXT + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mSubLC, "S_MarketingSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mSubLC, "S_MarketingSection", "", lControl, szVoid, "", "", 0, "", "" );
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
      //:lID = mSubLC.S_MarketingSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "S_MarketingSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptMarketingSect( ViewToWindow )
      nRC = AcceptMarketingSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mSubLC.S_MarketingSection
      //:    WHERE mSubLC.S_MarketingSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_MarketingSection", "ID", lID, "" );
      //:SET CURSOR NEXT mSubLC.S_MarketingSection
      RESULT = SetCursorNextEntity( mSubLC, "S_MarketingSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingSection", "NextMarketingSect: " )
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

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
PreviousMarketingSect( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_PREV + zTEST_CSR_RESULT
   lControl = zPOS_PREV + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mSubLC, "S_MarketingSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mSubLC, "S_MarketingSection", "", lControl, szVoid, "", "", 0, "", "" );
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
      //:lID = mSubLC.S_MarketingSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "S_MarketingSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptMarketingSect( ViewToWindow )
      nRC = AcceptMarketingSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mSubLC.S_MarketingSection
      //:    WHERE mSubLC.S_MarketingSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_MarketingSection", "ID", lID, "" );
      //:SET CURSOR PREVIOUS mSubLC.S_MarketingSection
      RESULT = SetCursorPrevEntity( mSubLC, "S_MarketingSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSubLC", "S_MarketingSection", "PreviousMarketingSect: " )
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
      //:InitDirectionsUseSectForInsert( ViewToWindow )
      InitDirectionsUseSectForInsert( ViewToWindow );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New DirectionsUse Section",
      //:             "Error saving DirectionsUse section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New DirectionsUse Section", "Error saving DirectionsUse section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:InitDirectionsUseStmtForInsert( ViewToWindow )
      InitDirectionsUseStmtForInsert( ViewToWindow );
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

//:   VIEW  mSubLC   REGISTERED AS mSubLC
public int 
SaveAddNewIngredient( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 256 ) szPrompt
   String   szPrompt = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptIngredientsStmt( ViewToWindow )
   nRC = AcceptIngredientsStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:szPrompt = mSubLC.S_IngredientsStatement.Prompt
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szPrompt;
      if ( szPrompt == null )
         sb_szPrompt = new StringBuilder( 32 );
      else
         sb_szPrompt = new StringBuilder( szPrompt );
             GetVariableFromAttribute( sb_szPrompt, mi_lTempInteger_0, 'S', 257, mSubLC, "S_IngredientsStatement", "Prompt", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szPrompt = sb_szPrompt.toString( );}
      //:InitIngredientsStmtForInsert( ViewToWindow )
      InitIngredientsStmtForInsert( ViewToWindow );
      //:mSubLC.S_IngredientsStatement.Prompt = szPrompt
      SetAttributeFromString( mSubLC, "S_IngredientsStatement", "Prompt", szPrompt );
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

//:   VIEW  mSubLC   REGISTERED AS mSubLC
public int 
SaveAddNewClaim( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptClaimsStmt( ViewToWindow )
   nRC = AcceptClaimsStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitClaimsStmtForInsert( ViewToWindow )
      InitClaimsStmtForInsert( ViewToWindow );
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

//:   VIEW  mSubLC   REGISTERED AS mSubLC
public int 
SaveAddNewSurface( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptSurfacesStmt( ViewToWindow )
   nRC = AcceptSurfacesStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitSurfacesStmtForInsert( ViewToWindow )
      InitSurfacesStmtForInsert( ViewToWindow );
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
//:SelectSurfacesStmtForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectSurfacesStmtForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing S_Usage (Surfaces) entity.  We have
   //:// position on the SI_UsageList entity, but need to get position on
   //:// the S_Usage (Surfaces) entity that corresponds to the SI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSubLC, "S_Usage", mSubLC, "SI_UsageList", "" );

   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewAreasOfUse( VIEW ViewToWindow )

//:   VIEW  mSubLC  REGISTERED AS mSubLC
public int 
SaveAddNewAreasOfUse( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptAreasOfUseStmt( ViewToWindow )
   nRC = AcceptAreasOfUseStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitAreasOfUseStmtForInsert( ViewToWindow )
      InitAreasOfUseStmtForInsert( ViewToWindow );
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
//:CleanupMarketingWorkEntities( VIEW mSubLC )

//:   VIEW mTempLC  BASED ON LOD  mSubLC
public int 
CleanupMarketingWorkEntities( View     mSubLC )
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
   RESULT = GetViewByName( mTempLC, "mTempLC", mSubLC, zLEVEL_TASK );
   //:IF mTempLC != 0
   if ( getView( mTempLC ) != null )
   { 
      //:DropView( mTempLC )
      DropView( mTempLC );
   } 

   //:END
   //:CreateViewFromView( mTempLC, mSubLC )
   CreateViewFromView( mTempLC, mSubLC );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );

   //:// We need to traverse S_MarketingUsageOrdering entities and delete the work sub-entities.
   //:FOR EACH mTempLC.S_MarketingUsageOrdering
   RESULT = SetCursorFirstEntity( mTempLC, "S_MarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mTempLC.S_MarketingUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mTempLC, "S_MarketingUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mTempLC.S_MarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mTempLC, "S_MarketingUsage", "ClaimsClassification", "", 0 );
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

      RESULT = SetCursorNextEntity( mTempLC, "S_MarketingUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempLC )
   DropView( mTempLC );
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
//:ReloadSLC( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ReloadSLC( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lSubregID
   int      lSubregID = 0;
   //:INTEGER lProductID
   int      lProductID = 0;
   //:INTEGER lSLC_ID
   int      lSLC_ID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_7 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   zVIEW    vTempViewVar_4 = new zVIEW( );
   zVIEW    vTempViewVar_5 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:IF mSubLC.SubregLabelContent EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "SubregLabelContent" );
      if ( lTempInteger_0 == 0 )
      { 
         //:DeleteEntity( mSubLC, "SubregLabelContent", zREPOS_NONE )
         DeleteEntity( mSubLC, "SubregLabelContent", zREPOS_NONE );
         //:COMMIT mSubLC
         RESULT = CommitObjectInstance( mSubLC );
      } 

      //:END

      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg.SubregLabelContent EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSubreg, "SubregLabelContent" );
   if ( lTempInteger_1 == 0 )
   { 
      //:AcceptSubobject( mSubreg, "SubregProduct" )
      AcceptSubobject( mSubreg, "SubregProduct" );
      //:SET CURSOR FIRST mSubreg.SubregLabelContent
      RESULT = SetCursorFirstEntity( mSubreg, "SubregLabelContent", "" );
      //:LOOP WHILE RESULT >= 0
      while ( RESULT >= 0 )
      { 
         //:DeleteEntity( mSubreg, "SubregLabelContent", zREPOS_NONE )
         DeleteEntity( mSubreg, "SubregLabelContent", zREPOS_NONE );
         //:// ExcludeEntity( mSubreg, "SubregLabelContent", zREPOS_NONE )
         //:SET CURSOR FIRST mSubreg.SubregLabelContent
         RESULT = SetCursorFirstEntity( mSubreg, "SubregLabelContent", "" );
      } 

      //:END

      //:// ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubreg.SubregLabelContent.ID
      //:// IF mSubLC.SubregLabelContent EXISTS
      //:ACTIVATE mSubLC WHERE mSubLC.SubregProduct.ID = mSubreg.SubregProduct.ID
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mSubreg, "SubregProduct", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:IF mSubLC.SubregLabelContent EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( mSubLC, "SubregLabelContent" );
      if ( lTempInteger_3 == 0 )
      { 
         //:DELETE ENTITY mSubLC.SubregLabelContent
         RESULT = DeleteEntity( mSubLC, "SubregLabelContent", zPOS_NEXT );
         //:COMMIT mSubLC
         RESULT = CommitObjectInstance( mSubLC );
      } 

      //:END

      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );

      //:AcceptSubobject( mSubreg, "SubregProduct" )
      AcceptSubobject( mSubreg, "SubregProduct" );
      //:COMMIT mSubreg
      RESULT = CommitObjectInstance( mSubreg );
   } 

   //:END

   //:// Let's first get rid of all Product Label Content definitions for this product
   //:// since we only want one???
   //:ACTIVATE mSubLC WHERE mSubLC.SubregProduct.ID = mSubreg.SubregProduct.ID
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, mSubreg, "SubregProduct", "ID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_1, lTempInteger_4 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:LOOP WHILE mSubLC.SubregLabelContent EXISTS
   lTempInteger_5 = CheckExistenceOfEntity( mSubLC, "SubregLabelContent" );
   while ( lTempInteger_5 == 0 )
   { 
      //:DeleteEntity( mSubLC, "SubregLabelContent", zREPOS_NONE )
      DeleteEntity( mSubLC, "SubregLabelContent", zREPOS_NONE );
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
      //:ACTIVATE mSubLC WHERE mSubLC.SubregProduct.ID = mSubreg.SubregProduct.ID
      {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
             GetIntegerFromAttribute( mi_lTempInteger_6, mSubreg, "SubregProduct", "ID" );
      lTempInteger_6 = mi_lTempInteger_6.intValue( );}
      o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_2, lTempInteger_6 );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_2, zSINGLE );
      DropView( vTempViewVar_2 );
      lTempInteger_5 = CheckExistenceOfEntity( mSubLC, "SubregLabelContent" );
   } 

   //:END

   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:// // We need to create a new SubregLabelContent entity.
   //:// CreateEntity( mSubreg, "SubregLabelContent", zPOS_LAST )

   //:// It is now time to initialize a new Product Label Content.  That is a
   //:// bit of work, but here goes ...
   //:// First, activate a MasterProduct OI so we can figure out the latest
   //:// Master Label Content version.
   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = mSubreg.MasterProduct.ID
   {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
       GetIntegerFromAttribute( mi_lTempInteger_7, mSubreg, "MasterProduct", "ID" );
   lTempInteger_7 = mi_lTempInteger_7.intValue( );}
   o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_3, lTempInteger_7 );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_3, zSINGLE );
   DropView( vTempViewVar_3 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:// Now because we keep the latest version of the Master Product Label
   //:// first in the list, we will just make sure we have an entity and use it.
   //:IF mMasProd.MasterLabelContent EXISTS
   lTempInteger_8 = CheckExistenceOfEntity( mMasProd, "MasterLabelContent" );
   if ( lTempInteger_8 == 0 )
   { 

      //:// Now activate the latest Master Label Content OI.
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
             GetIntegerFromAttribute( mi_lTempInteger_9, mMasProd, "MasterLabelContent", "ID" );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );}
      o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_4, lTempInteger_9 );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_4, zSINGLE );
      DropView( vTempViewVar_4 );
      //:NAME VIEW mMasLC "mMasLC_ReloadPLC"
      SetNameForView( mMasLC, "mMasLC_ReloadPLC", null, zLEVEL_TASK );

      //:// We need to create and initialize a new SubregLabelContent entity.
      //:ACTIVATE mSubLC EMPTY
      RESULT = ActivateEmptyObjectInstance( mSubLC, "mSubLC", ViewToWindow, zSINGLE );
      //:NAME VIEW mSubLC "mSubLC"
      SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
      //:CREATE ENTITY mSubLC.SubregLabelContent
      RESULT = CreateEntity( mSubLC, "SubregLabelContent", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "SubregLabelContent",
      //:                             mMasLC, "MasterLabelContent", zSET_NOTNULL )
      SetMatchingAttributesByName( mSubLC, "SubregLabelContent", mMasLC, "MasterLabelContent", zSET_NOTNULL );
      //:SetMatchingAttributesByName( mSubLC, "SubregLabelContent",
      //:                             mMasLC, "MasterProduct", zSET_NOTNULL )
      SetMatchingAttributesByName( mSubLC, "SubregLabelContent", mMasLC, "MasterProduct", zSET_NOTNULL );
      //:IncludeSubobjectFromSubobject( mSubLC, "SubregProduct",
      //:                               mSubreg, "SubregProduct", zPOS_BEFORE )
      IncludeSubobjectFromSubobject( mSubLC, "SubregProduct", mSubreg, "SubregProduct", zPOS_BEFORE );

      //:// Now run through the Master Label Content entities and create the corresponding
      //:// Subregistrant Label Content entities.
      //:BuildSLC_FromMLC( mSubLC, mMasLC )
      {
       mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
       m_mSubLC_Object.omSubLC_BuildSLC_FromMLC( mSubLC, mMasLC );
       // m_mSubLC_Object = null;  // permit gc  (unnecessary)
      }

      //:DropObjectInstance( mMasLC )
      DropObjectInstance( mMasLC );
   } 


   //:END

   //:lSubregID = mSubreg.Subregistrant.ID
   {MutableInt mi_lSubregID = new MutableInt( lSubregID );
       GetIntegerFromAttribute( mi_lSubregID, mSubreg, "Subregistrant", "ID" );
   lSubregID = mi_lSubregID.intValue( );}
   //:lProductID = mSubLC.SubregProduct.ID
   {MutableInt mi_lProductID = new MutableInt( lProductID );
       GetIntegerFromAttribute( mi_lProductID, mSubLC, "SubregProduct", "ID" );
   lProductID = mi_lProductID.intValue( );}
   //:lSLC_ID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lSLC_ID = new MutableInt( lSLC_ID );
       GetIntegerFromAttribute( mi_lSLC_ID, mSubLC, "SubregLabelContent", "ID" );
   lSLC_ID = mi_lSLC_ID.intValue( );}

   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );

   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lSubregID
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_5, lSubregID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_5, zSINGLE );
   DropView( vTempViewVar_5 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lProductID, "" )
   SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lProductID, "" );
   //:SetCursorFirstEntityByInteger( mSubreg, "SubregLabelContent", "ID", lSLC_ID, "" )
   SetCursorFirstEntityByInteger( mSubreg, "SubregLabelContent", "ID", lSLC_ID, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdatePhysicalLabel( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
UpdatePhysicalLabel( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lSubregID
   int      lSubregID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );
   zVIEW    vTempViewVar_2 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdatePhysicalLabel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdatePhysicalLabel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:lID = mSubLC.SubregLabelContent.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
      lID = mi_lID.intValue( );}
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:GET VIEW mSubProd NAMED "mSubProd"
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:IF mSubProd.SubregLabelContent DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSubProd, "SubregLabelContent" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Physical Label Definition",
      //:             "The Product Label Content does not exist.  Please initialize it.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Physical Label Definition", "The Product Label Content does not exist.  Please initialize it.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:lSubregID = mSubreg.Subregistrant.ID
   {MutableInt mi_lSubregID = new MutableInt( lSubregID );
       GetIntegerFromAttribute( mi_lSubregID, mSubreg, "Subregistrant", "ID" );
   lSubregID = mi_lSubregID.intValue( );}
   //:lID = mSubProd.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubProd, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSubProd, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow ) // this kills mSubreg and mSubProd
   nRC = AcceptUpdateSubregProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 

      //:GET VIEW mSubreg NAMED "mSubreg"
      RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
      //:IF mSubreg != 0
      if ( getView( mSubreg ) != null )
      { 
         //:DropObjectInstance( mSubreg )
         DropObjectInstance( mSubreg );
      } 

      //:END

      //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lSubregID
      o_fnLocalBuildQual_20( ViewToWindow, vTempViewVar_0, lSubregID );
      RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubreg "mSubreg"
      SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubreg.SubregProduct WHERE mSubreg.SubregProduct.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lID, "" );

      //:GET VIEW mSubProd NAMED "mSubProd"
      RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
      //:IF mSubProd != 0
      if ( getView( mSubProd ) != null )
      { 
         //:DropObjectInstance( mSubProd )
         DropObjectInstance( mSubProd );
      } 

      //:END

      //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lID
      o_fnLocalBuildQual_21( ViewToWindow, vTempViewVar_1, lID );
      RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW mSubProd "mSubProd"
      SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubProd.SubregLabelContent WHERE mSubProd.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSubProd, "SubregLabelContent", "ID", lContentID, "" );
      //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = lContentID
      o_fnLocalBuildQual_22( ViewToWindow, vTempViewVar_2, lContentID );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_2, zSINGLE );
      DropView( vTempViewVar_2 );
      //:NAME VIEW mSubLC "mSubLC"
      SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
      //:SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantLabel" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantLabel" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:LOCAL OPERATION
//:LoadMarketingUsageList( VIEW ViewToWindow,
//:                        VIEW mSubLC_In BASED ON LOD mSubLC )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadMarketingUsageList( View     ViewToWindow,
                          View     mSubLC_In )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mPosLC   BASED ON LOD  mSubLC
   zVIEW    mPosLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
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

   //:CleanupMarketingWorkEntities( mSubLC_In )
   CleanupMarketingWorkEntities( mSubLC_In );

   //:CreateViewFromView( mPosLC, mSubLC_In )
   CreateViewFromView( mPosLC, mSubLC_In );
   //:NAME VIEW mPosLC "mPosLC"
   SetNameForView( mPosLC, "mPosLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mTempLC, mSubLC_In )
   CreateViewFromView( mTempLC, mSubLC_In );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mSubLC, mSubLC_In )
   CreateViewFromView( mSubLC, mSubLC_In );
   //:NAME VIEW mSubLC "mSubLC1"
   SetNameForView( mSubLC, "mSubLC1", null, zLEVEL_TASK );

   //:// Get position on included S_MarketingUsage entities (which will be marked as selected).
   //:SetCursorFirstEntity( mPosLC, "S_MarketingUsageOrdering", "" )
   SetCursorFirstEntity( mPosLC, "S_MarketingUsageOrdering", "" );

   //:// Mark included S_MarketingUsage entities as "selected" and include S_OriginalMarketingUsage not
   //:// already included into the S_MarketingUsage entity and mark as "not selected".
   //:FOR EACH mSubLC.S_OriginalMarketingUsageOrdering
   RESULT = SetCursorFirstEntity( mSubLC, "S_OriginalMarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lID = mSubLC.S_OriginalMarketingUsage.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "S_OriginalMarketingUsage", "ID" );
      lID = mi_lID.intValue( );}
      //:SET CURSOR FIRST mTempLC.S_MarketingUsage WITHIN mTempLC.S_MarketingSection
      //:                                          WHERE mTempLC.S_MarketingUsage.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mTempLC, "S_MarketingUsage", "ID", lID, "S_MarketingSection" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mPosLC.S_MarketingUsage WITHIN mPosLC.S_MarketingSection
         //:                                         WHERE mPosLC.S_MarketingUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mPosLC, "S_MarketingUsage", "ID", lID, "S_MarketingSection" );
         //:mPosLC.S_MarketingUsage.wkSelected = "Y"
         SetAttributeFromString( mPosLC, "S_MarketingUsage", "wkSelected", "Y" );
         //:ELSE
      } 
      else
      { 
         //:CreateEntity( mPosLC, "S_MarketingUsageOrdering", zPOS_AFTER )
         CreateEntity( mPosLC, "S_MarketingUsageOrdering", zPOS_AFTER );
         //:IncludeSubobjectFromSubobject( mPosLC, "S_MarketingUsage",
         //:                               mSubLC, "S_OriginalMarketingUsage", zPOS_NEXT )
         IncludeSubobjectFromSubobject( mPosLC, "S_MarketingUsage", mSubLC, "S_OriginalMarketingUsage", zPOS_NEXT );
         //:mPosLC.S_MarketingUsage.wkSelected = ""
         SetAttributeFromString( mPosLC, "S_MarketingUsage", "wkSelected", "" );
      } 

      //:END

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mSubLC.S_OriginalMarketingUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mSubLC, "S_OriginalMarketingUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mSubLC.S_OriginalMarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mSubLC, "S_OriginalMarketingUsage", "ClaimsClassification", "", 0 );
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

      RESULT = SetCursorNextEntity( mSubLC, "S_OriginalMarketingUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mPosLC )
   DropView( mPosLC );
   //:DropView( mTempLC )
   DropView( mTempLC );
   //:DropView( mSubLC )
   DropView( mSubLC );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadDirectionsUsageList( VIEW ViewToWindow,
//:                         VIEW mSubLC_In BASED ON LOD mSubLC )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadDirectionsUsageList( View     ViewToWindow,
                           View     mSubLC_In )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mPosLC   BASED ON LOD  mSubLC
   zVIEW    mPosLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
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

   //:CleanupDirectionsWorkEntities( mSubLC_In )
   CleanupDirectionsWorkEntities( mSubLC_In );

   //:CreateViewFromView( mPosLC, mSubLC_In )
   CreateViewFromView( mPosLC, mSubLC_In );
   //:NAME VIEW mPosLC "mPosLC"
   SetNameForView( mPosLC, "mPosLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mTempLC, mSubLC_In )
   CreateViewFromView( mTempLC, mSubLC_In );
   //:NAME VIEW mTempLC "mTempLC"
   SetNameForView( mTempLC, "mTempLC", null, zLEVEL_TASK );
   //:CreateViewFromView( mSubLC, mSubLC_In )
   CreateViewFromView( mSubLC, mSubLC_In );
   //:NAME VIEW mSubLC "mSubLC1"
   SetNameForView( mSubLC, "mSubLC1", null, zLEVEL_TASK );

   //:// Get position on included S_DirectionsUsage entities (which will be marked as selected).
   //:SetCursorFirstEntity( mPosLC, "S_DirectionsUsageOrdering", "" )
   SetCursorFirstEntity( mPosLC, "S_DirectionsUsageOrdering", "" );

   //:// Mark included S_DirectionsUsage entities as "selected" and include S_OriginalDirectionsUsage not
   //:// already included into the S_DirectionsUsage entity and mark as "not selected".
   //:FOR EACH mSubLC.S_OriginalDirectionUsageOrdering
   RESULT = SetCursorFirstEntity( mSubLC, "S_OriginalDirectionUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lID = mSubLC.S_OriginalDirectionsUsage.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubLC, "S_OriginalDirectionsUsage", "ID" );
      lID = mi_lID.intValue( );}
      //:SET CURSOR FIRST mTempLC.S_DirectionsUsage WITHIN mTempLC.S_DirectionsForUseSection
      //:                                           WHERE mTempLC.S_DirectionsUsage.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mTempLC, "S_DirectionsUsage", "ID", lID, "S_DirectionsForUseSection" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mPosLC.S_DirectionsUsage WITHIN mPosLC.S_DirectionsForUseSection
         //:                                          WHERE mPosLC.S_DirectionsUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mPosLC, "S_DirectionsUsage", "ID", lID, "S_DirectionsForUseSection" );
         //:mPosLC.S_DirectionsUsage.wkSelected = "Y"
         SetAttributeFromString( mPosLC, "S_DirectionsUsage", "wkSelected", "Y" );
         //:ELSE
      } 
      else
      { 
         //:CreateEntity( mPosLC, "S_DirectionsUsageOrdering", zPOS_AFTER )
         CreateEntity( mPosLC, "S_DirectionsUsageOrdering", zPOS_AFTER );
         //:IncludeSubobjectFromSubobject( mPosLC, "S_DirectionsUsage",
         //:                               mSubLC, "S_OriginalDirectionsUsage", zPOS_NEXT )
         IncludeSubobjectFromSubobject( mPosLC, "S_DirectionsUsage", mSubLC, "S_OriginalDirectionsUsage", zPOS_NEXT );
         //:mPosLC.S_DirectionsUsage.wkSelected = ""
         SetAttributeFromString( mPosLC, "S_DirectionsUsage", "wkSelected", "" );
      } 

      //:END

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mSubLC.S_OriginalDirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mSubLC, "S_OriginalDirectionsUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mSubLC.S_OriginalDirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mSubLC, "S_OriginalDirectionsUsage", "ClaimsClassification", "", 0 );
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

      RESULT = SetCursorNextEntity( mSubLC, "S_OriginalDirectionUsageOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mPosLC )
   DropView( mPosLC );
   //:DropView( mTempLC )
   DropView( mTempLC );
   //:DropView( mSubLC )
   DropView( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptStorDispContent( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
AcceptStorDispContent( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CancelStorDispContent( View     ViewToWindow )
{

   //:CancelStorDispContent( VIEW ViewToWindow )

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelStorDispContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelStorDispContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GenerateNewSLC_Version( VIEW ViewToWindow )

//:   VIEW mSubProd    REGISTERED AS mSubProd
public int 
GenerateNewSLC_Version( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW OriginalSLC BASED ON LOD  mSubLC
   zVIEW    OriginalSLC = new zVIEW( );
   //:VIEW NewSLC      BASED ON LOD  mSubLC
   zVIEW    NewSLC = new zVIEW( );
   //:VIEW SourceMLC   BASED ON LOD  mMasLC
   zVIEW    SourceMLC = new zVIEW( );
   //:VIEW PreviousMLC BASED ON LOD  lMLCASrc
   zVIEW    PreviousMLC = new zVIEW( );
   //:STRING ( 64 ) szVersionNew
   String   szVersionNew = null;
   //:INTEGER lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   zVIEW    vTempViewVar_3 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Generate new SLC from the selected SLC.

   //:// Source SLC
   //:ACTIVATE OriginalSLC WHERE OriginalSLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_23( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( OriginalSLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW OriginalSLC "OriginalSLC"
   SetNameForView( OriginalSLC, "OriginalSLC", null, zLEVEL_TASK );

   //:// MLC for Source SLC
   //:ACTIVATE PreviousMLC WHERE PreviousMLC.MasterLabelContent.ID = OriginalSLC.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, OriginalSLC, "MasterLabelContent", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_24( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( PreviousMLC, "lMLCASrc", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW PreviousMLC "PreviousMLC"
   SetNameForView( PreviousMLC, "PreviousMLC", null, zLEVEL_TASK );

   //:// MLC for next version of MLC
   //:IF PreviousMLC.MN_MasterLabelContent DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( PreviousMLC, "MN_MasterLabelContent" );
   if ( lTempInteger_2 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Generate SLC",
      //:             "The MLC for the selected SLC does not have a next version.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Generate SLC", "The MLC for the selected SLC does not have a next version.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:DropObjectInstance( OriginalSLC )
      DropObjectInstance( OriginalSLC );
      //:DropObjectInstance( PreviousMLC )
      DropObjectInstance( PreviousMLC );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:ACTIVATE SourceMLC WHERE SourceMLC.MasterLabelContent.ID = PreviousMLC.MN_MasterLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, PreviousMLC, "MN_MasterLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   o_fnLocalBuildQual_25( ViewToWindow, vTempViewVar_2, lTempInteger_3 );
   RESULT = ActivateObjectInstance( SourceMLC, "mMasLC", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:NAME VIEW SourceMLC "SourceMLC"
   SetNameForView( SourceMLC, "SourceMLC", null, zLEVEL_TASK );

   //:ACTIVATE NewSLC EMPTY
   RESULT = ActivateEmptyObjectInstance( NewSLC, "mSubLC", ViewToWindow, zSINGLE );
   //:NAME VIEW NewSLC "NewSLC"
   SetNameForView( NewSLC, "NewSLC", null, zLEVEL_TASK );

   //:// Set the new version here since we have all the information.
   //:DetermineNextVersion( szVersionNew, mSubProd, "SubregLabelContent", "Version" )
   {
    ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSubProd );
    m_ZGlobal1_Operation.DetermineNextVersion( szVersionNew, mSubProd, "SubregLabelContent", "Version" );
    // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
   }

   //:BuildNewSLC_Version( NewSLC, OriginalSLC, SourceMLC, PreviousMLC )    // Create NewSLC from OriginalSLC
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( NewSLC );
    m_mSubLC_Object.omSubLC_BuildNewSLC_Version( NewSLC, OriginalSLC, SourceMLC, PreviousMLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   //:NewSLC.SubregLabelContent.Version = szVersionNew
   SetAttributeFromString( NewSLC, "SubregLabelContent", "Version", szVersionNew );

   //:COMMIT NewSLC
   RESULT = CommitObjectInstance( NewSLC );
   //:DropObjectInstance( OriginalSLC )
   DropObjectInstance( OriginalSLC );
   //:DropObjectInstance( PreviousMLC )
   DropObjectInstance( PreviousMLC );
   //:DropObjectInstance( SourceMLC )
   DropObjectInstance( SourceMLC );
   //:DropObjectInstance( NewSLC )
   DropObjectInstance( NewSLC );

   //:// mSubProd needs to reflect new SLC.
   //:lID = mSubProd.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubProd, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = lID
   o_fnLocalBuildQual_26( ViewToWindow, vTempViewVar_3, lID );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_3, zSINGLE );
   DropView( vTempViewVar_3 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   return( 0 );
// END
} 



}
