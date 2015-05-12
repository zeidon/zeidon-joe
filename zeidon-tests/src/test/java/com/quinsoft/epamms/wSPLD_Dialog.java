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
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.epamms.ZGlobalV_Operation;
import com.quinsoft.epamms.mSubLC_Object;
import com.quinsoft.epamms.mMasLC_Object;
import com.quinsoft.epamms.mSPLDef_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class wSPLD_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   private final KZOEP1AA m_KZOEP1AA;
   public wSPLD_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:DIALOG OPERATION
//:InitLoginWindow( VIEW ViewToWindow )

//:   VIEW wWebXfer BASED ON LOD wWebXfer
public int 
InitLoginWindow( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW wWebXfer NAMED "wWebXfer"
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( wWebXfer )
      DropObjectInstance( wWebXfer );
   } 

   //:END

   //:ACTIVATE wWebXfer EMPTY
   RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", ViewToWindow, zSINGLE );
   //:NAME VIEW wWebXfer "wWebXfer"
   SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );
   //:CREATE ENTITY wWebXfer.Root
   RESULT = CreateEntity( wWebXfer, "Root", zPOS_AFTER );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Default" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Default" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_25( View     vSubtask,
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
o_fnLocalBuildQual_26( View     vSubtask,
                       zVIEW    vQualObject,
                       int      SubProdID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", SubProdID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregPhysicalLabelDef" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_28( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_20( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "LLD" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "LLD" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_21( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "LLD" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "LLD" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_22( View     vSubtask,
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
o_fnLocalBuildQual_23( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ">" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_24( View     vSubtask,
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
o_fnLocalBuildQual_17( View     vSubtask,
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
o_fnLocalBuildQual_18( View     vSubtask,
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
o_fnLocalBuildQual_19( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
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
o_fnLocalBuildQual_10( View     vSubtask,
                       zVIEW    vQualObject,
                       int      SubregID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", SubregID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_11( View     vSubtask,
                       zVIEW    vQualObject,
                       int      SubregProductID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", SubregProductID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_12( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregPhysicalLabelDef" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_13( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_14( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregPhysicalLabelDef" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_15( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregPhysicalLabelDef" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "LLD" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "LLD" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregOrganization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
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
                      int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
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
o_fnLocalBuildQual_5( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_7 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_7 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_6( View     vSubtask,
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
o_fnLocalBuildQual_7( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:DuplicateSubregProductSPLD( VIEW ViewToWindow )

//:   VIEW mSubProd    REGISTERED AS mSubProd
public int 
DuplicateSubregProductSPLD( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSPLDLST    REGISTERED AS lSPLDLST
   zVIEW    lSPLDLST = new zVIEW( );
   //:VIEW mSPLDefOrig BASED ON LOD  mSPLDef 
   zVIEW    mSPLDefOrig = new zVIEW( );
   //:VIEW mSPLDefNew  BASED ON LOD  mSPLDef 
   zVIEW    mSPLDefNew = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );

   //:// Activate selected Subreg SLC and duplicate it.
   //://ACTIVATE mSPLDefOrig WHERE mSPLDefOrig.SubregPhysicalLabelDef.ID = mSubProd.SubregPhysicalLabelDef.ID 
   //:ACTIVATE mSPLDefOrig WHERE mSPLDefOrig.SubregPhysicalLabelDef.ID = lSPLDLST.SubregPhysicalLabelDef.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSPLDLST, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_27( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSPLDefOrig, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSPLDefOrig "mSPLDefOrig"
   SetNameForView( mSPLDefOrig, "mSPLDefOrig", null, zLEVEL_TASK );

   //:// Create empty target object.
   //:ACTIVATE mSPLDefNew EMPTY 
   RESULT = ActivateEmptyObjectInstance( mSPLDefNew, "mSPLDef", ViewToWindow, zSINGLE );
   //:NAME VIEW mSPLDefNew "mSPLDefNew"
   SetNameForView( mSPLDefNew, "mSPLDefNew", null, zLEVEL_TASK );

   //:// Execute duplication operation and commit it.
   //:DuplicateSPLD( mSPLDefNew, mSPLDefOrig )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDefNew );
    m_mSPLDef_Object.omSPLDef_DuplicateSPLD( mSPLDefNew, mSPLDefOrig );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDefNew
   RESULT = CommitObjectInstance( mSPLDefNew );

   //:DropObjectInstance( mSPLDefOrig )
   DropObjectInstance( mSPLDefOrig );
   //:DropObjectInstance( mSPLDefNew )
   DropObjectInstance( mSPLDefNew );

   //:// Reactivate mSubProd to pick up new SubLC.
   //:DropObjectInstance( lSPLDLST )
   DropObjectInstance( lSPLDLST );
   //:ACTIVATE lSPLDLST Multiple WHERE lSPLDLST.SubregProduct.ID = mSubProd.SubregProduct.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSubProd, "SubregProduct", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_28( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( lSPLDLST, "lSPLDLST", ViewToWindow, vTempViewVar_1, zMULTIPLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lSPLDLST "lSPLDLST"
   SetNameForView( lSPLDLST, "lSPLDLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DuplicateSubregProductSLC( VIEW ViewToWindow )

//:   VIEW mSubProd   REGISTERED AS mSubProd
public int 
DuplicateSubregProductSLC( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLCOrig BASED ON LOD  mSubLC 
   zVIEW    mSubLCOrig = new zVIEW( );
   //:VIEW mSubLCNew  BASED ON LOD  mSubLC 
   zVIEW    mSubLCNew = new zVIEW( );
   //:INTEGER SubProdID
   int      SubProdID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Activate selected Subreg SLC and duplicate it.
   //:ACTIVATE mSubLCOrig WHERE mSubLCOrig.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_25( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLCOrig, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLCOrig "mSubLCOrig"
   SetNameForView( mSubLCOrig, "mSubLCOrig", null, zLEVEL_TASK );

   //:// Create empty target object.
   //:ACTIVATE mSubLCNew EMPTY 
   RESULT = ActivateEmptyObjectInstance( mSubLCNew, "mSubLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubLCNew "mSubLCNew"
   SetNameForView( mSubLCNew, "mSubLCNew", null, zLEVEL_TASK );

   //:// Execute duplication operation and commit it.
   //:DuplicateSLC( mSubLCNew, mSubLCOrig )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLCNew );
    m_mSubLC_Object.omSubLC_DuplicateSLC( mSubLCNew, mSubLCOrig );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSubLCNew
   RESULT = CommitObjectInstance( mSubLCNew );

   //:DropObjectInstance( mSubLCOrig )
   DropObjectInstance( mSubLCOrig );
   //:DropObjectInstance( mSubLCNew )
   DropObjectInstance( mSubLCNew );

   //:// Reactivate mSubProd to pick up new SubLC.
   //:SubProdID = mSubProd.SubregProduct.ID 
   {MutableInt mi_SubProdID = new MutableInt( SubProdID );
       GetIntegerFromAttribute( mi_SubProdID, mSubProd, "SubregProduct", "ID" );
   SubProdID = mi_SubProdID.intValue( );}
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = SubProdID
   o_fnLocalBuildQual_26( ViewToWindow, vTempViewVar_1, SubProdID );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
ConfirmDeleteSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:lID = mSubreg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}

   //:DELETE ENTITY mSubreg.SubregProduct
   RESULT = DeleteEntity( mSubreg, "SubregProduct", zPOS_NEXT );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteSubregProduct( VIEW ViewToWindow )

public int 
CancelDeleteSubregProduct( View     ViewToWindow )
{

   return( 0 );
// // VIEW mSubProd REGISTERED AS mSubProd
// // DropObjectInstance( mSubProd )
// END
} 


//:DIALOG OPERATION
//:CANCEL_BlockSubBlockDefinition( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CANCEL_BlockSubBlockDefinition( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Cancel both LLD_Block and the associated LLD_Block or LLD_SubBlock entry.
   //:/*IF mSPLDef.LLD_Block.Type = "Block" 
   //:   CancelSubobject( mSPLDef, "LLD_Block" )
   //:ELSE
   //:   CancelSubobject( mSPLDef, "LLD_SubBlock" )
   //:END*/
   //:CancelSubobject( mSPLDef, "LLD_Block" )
   CancelSubobject( mSPLDef, "LLD_Block" );
   //:ResetViewFromSubobject( mSPLDef )
   ResetViewFromSubobject( mSPLDef );
   //:CancelSubobject( mSPLDef, "LLD_Block" )
   CancelSubobject( mSPLDef, "LLD_Block" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ACCEPT_BlockSubBlockDefinition( VIEW ViewToWindow )
//:/*
public int 
ACCEPT_BlockSubBlockDefinition( View     ViewToWindow )
{

   return( 0 );
//    VIEW mSPLDef      REGISTERED AS mSPLDef
//    VIEW mSPLDefPanel REGISTERED AS mSPLDefPanel
//    VIEW mSPLDef2     BASED ON LOD  mSPLDef
//    INTEGER HighestBlockNumber
//    
//    // If this entry doesn't have a wRelativeBlockNumber value, set it to the next highest value.
//    IF mSPLDefPanel.LLD_Block.wRelativeBlockNumber = ""
//       HighestBlockNumber = 0
//       CreateViewFromView( mSPLDef2, mSPLDefPanel )
//       FOR EACH mSPLDef2.LLD_Block 
//          IF HighestBlockNumber < mSPLDef2.LLD_Block.wRelativeBlockNumber 
//             HighestBlockNumber = mSPLDef2.LLD_Block.wRelativeBlockNumber 
//          END
//       END
//       HighestBlockNumber = HighestBlockNumber + 1
//       mSPLDefPanel.LLD_Block.wRelativeBlockNumber = HighestBlockNumber
// //    IF mSPLDefPanel.LLD_Block.Type = "Block"
//          mSPLDefPanel.LLD_Block.wRelativeBlockNumber = HighestBlockNumber
// //    ELSE
// //       mSPLDefPanel.LLD_SubBlock.wRelativeBlockNumber = HighestBlockNumber
// //    END
//       DropView( mSPLDef2 )
//    END
//    
//    // Accept both LLD_Block and the associated LLD_Block or LLD_SubBlock entry.
//    //AcceptSubobject( mSPLDef, "LLD_Block" )
//    SetMatchingAttributesByName( mSPLDefPanel, "LLD_Block", mSPLDef, "LLD_Block", zSET_NOTNULL )
//    ResetViewFromSubobject( mSPLDef )
//    //AcceptSubobject( mSPLDefPanel, "LLD_Block" )
// */
// END
} 


//:DIALOG OPERATION
//:GOTO_NewSpecialFormatDefinition( VIEW ViewToWindow )
//:/*
public int 
GOTO_NewSpecialFormatDefinition( View     ViewToWindow )
{

   return( 0 );
//    VIEW mSPLDef REGISTERED AS mSPLDef
//    
//    // Create the temporal LLD_SpecialSectionAttribute entry.
//    CreateTemporalEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER )
//    CREATE ENTITY mSPLDef.LLD_SpecialSectionAttrBlock 
// */
// END
} 


//:DIALOG OPERATION
//:CLEAN_SPLD_Data( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
CLEAN_SPLD_Data( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef2 BASED ON LOD  mSPLDef
   zVIEW    mSPLDef2 = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// First, do a simple create and see if entity is seen in other view.

   //:CommitOI_ToFile( mSPLDef, "c:\temp\JOE_Test2.por", zINCREMENTAL )
   CommitOI_ToFile( mSPLDef, "c:\\temp\\JOE_Test2.por", zINCREMENTAL );

   //:SET CURSOR LAST mSPLDef.LLD_Panel  
   RESULT = SetCursorLastEntity( mSPLDef, "LLD_Panel", "" );
   //:CreateViewFromView( mSPLDef2, mSPLDef )
   CreateViewFromView( mSPLDef2, mSPLDef );
   //:NAME VIEW mSPLDef2 "mSPLDef2"
   SetNameForView( mSPLDef2, "mSPLDef2", null, zLEVEL_TASK );

   //:CREATE ENTITY mSPLDef.ContinuationStatement 
   RESULT = CreateEntity( mSPLDef, "ContinuationStatement", zPOS_AFTER );
   //:mSPLDef.ContinuationStatement.Title = "Title 1"
   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Title", "Title 1" );
   //:mSPLDef.ContinuationStatement.Text  = "Text 1"
   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Text", "Text 1" );

   //:IF mSPLDef2.ContinuationStatement DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef2, "ContinuationStatement" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 2",
      //:             "ContinuationStatement doesn't exist 1",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 2", "ContinuationStatement doesn't exist 1", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:// Next, do the same test except that the entity is first deleted from the primary view.

   //:DELETE ENTITY mSPLDef.ContinuationStatement NONE 
   RESULT = DeleteEntity( mSPLDef, "ContinuationStatement", zREPOS_NONE );

   //:CREATE ENTITY mSPLDef.ContinuationStatement 
   RESULT = CreateEntity( mSPLDef, "ContinuationStatement", zPOS_AFTER );
   //:mSPLDef.ContinuationStatement.Title = "Title 1"
   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Title", "Title 1" );
   //:mSPLDef.ContinuationStatement.Text  = "Text 1"
   SetAttributeFromString( mSPLDef, "ContinuationStatement", "Text", "Text 1" );

   //:SET CURSOR FIRST mSPLDef2.ContinuationStatement
   RESULT = SetCursorFirstEntity( mSPLDef2, "ContinuationStatement", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 2",
      //:             "ContinuationStatement doesn't exist 2",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 2", "ContinuationStatement doesn't exist 2", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:TraceLineS( "*** JOE Test 2 successfully completed", "" )
   TraceLineS( "*** JOE Test 2 successfully completed", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SELECT_LLD_ForSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
SELECT_LLD_ForSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mLLD_LST REGISTERED AS mLLD_LST
   zVIEW    mLLD_LST = new zVIEW( );
   //:VIEW mLLD  BASED ON LOD  mLLD 
   zVIEW    mLLD = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mLLD_LST, "mLLD_LST", ViewToWindow, zLEVEL_TASK );

   //:// Use the selected LLD to create an LLD subobject in mSPLDef.

   //:// Make sure an entry is selected.
   //:SET CURSOR FIRST mLLD_LST.LLD WHERE mLLD_LST.LLD.wSelected = "Y"
   RESULT = SetCursorFirstEntityByString( mLLD_LST, "LLD", "wSelected", "Y", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Selecte LLD",
      //:             "An LLD entry must be selected.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Selecte LLD", "An LLD entry must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:mLLD_LST.LLD.wSelected = ""
   SetAttributeFromString( mLLD_LST, "LLD", "wSelected", "" );

   //:// Delete any current SPLD_LLD subobject.
   //:IF mSPLDef.SPLD_LLD EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_LLD" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mSPLDef.SPLD_LLD  
      RESULT = DeleteEntity( mSPLDef, "SPLD_LLD", zPOS_NEXT );
   } 

   //:END
   //:ACTIVATE mLLD WHERE mLLD.LLD.ID = mLLD_LST.LLD.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mLLD_LST, "LLD", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_21( ViewToWindow, vTempViewVar_0, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mLLD, "mLLD", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mLLD "mLLD"
   SetNameForView( mLLD, "mLLD", null, zLEVEL_TASK );

   //:// Build the SPLD_LLD subobject from the LLD.
   //:CREATE ENTITY mSPLDef.SPLD_LLD 
   RESULT = CreateEntity( mSPLDef, "SPLD_LLD", zPOS_AFTER );
   //:SetMatchingAttributesByName( mSPLDef, "SPLD_LLD", mLLD, "LLD", zSET_NULL )
   SetMatchingAttributesByName( mSPLDef, "SPLD_LLD", mLLD, "LLD", zSET_NULL );
   //:FOR EACH mLLD.LLD_Page 
   RESULT = SetCursorFirstEntity( mLLD, "LLD_Page", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.LLD_Page 
      RESULT = CreateEntity( mSPLDef, "LLD_Page", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSPLDef, "LLD_Page", mLLD, "LLD_Page", zSET_NULL )
      SetMatchingAttributesByName( mSPLDef, "LLD_Page", mLLD, "LLD_Page", zSET_NULL );
      //:FOR EACH mLLD.LLD_Panel 
      RESULT = SetCursorFirstEntity( mLLD, "LLD_Panel", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.LLD_Panel 
         RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSPLDef, "LLD_Panel", mLLD, "LLD_Panel", zSET_NULL )
         SetMatchingAttributesByName( mSPLDef, "LLD_Panel", mLLD, "LLD_Panel", zSET_NULL );
         //:FOR EACH mLLD.LLD_Block 
         RESULT = SetCursorFirstEntity( mLLD, "LLD_Block", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mSPLDef.LLD_Block 
            RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
            //:SetMatchingAttributesByName( mSPLDef, "LLD_Block", mLLD, "LLD_Block", zSET_NULL )
            SetMatchingAttributesByName( mSPLDef, "LLD_Block", mLLD, "LLD_Block", zSET_NULL );
            //:FOR EACH mLLD.LLD_SubBlock 
            RESULT = SetCursorFirstEntity( mLLD, "LLD_SubBlock", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY mSPLDef.LLD_SubBlock 
               RESULT = CreateEntity( mSPLDef, "LLD_SubBlock", zPOS_AFTER );
               //:SetMatchingAttributesByName( mSPLDef, "LLD_SubBlock", mLLD, "LLD_SubBlock", zSET_NULL )
               SetMatchingAttributesByName( mSPLDef, "LLD_SubBlock", mLLD, "LLD_SubBlock", zSET_NULL );
               RESULT = SetCursorNextEntity( mLLD, "LLD_SubBlock", "" );
            } 

            //:END
            //:FOR EACH mLLD.LLD_SpecialSectionAttribute 
            RESULT = SetCursorFirstEntity( mLLD, "LLD_SpecialSectionAttribute", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttribute 
               RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER );
               //:SetMatchingAttributesByName( mSPLDef, "LLD_SpecialSectionAttribute", mLLD, "LLD_SpecialSectionAttribute", zSET_NULL )
               SetMatchingAttributesByName( mSPLDef, "LLD_SpecialSectionAttribute", mLLD, "LLD_SpecialSectionAttribute", zSET_NULL );
               //:FOR EACH mLLD.LLD_SpecialSectionAttrBlock
               RESULT = SetCursorFirstEntity( mLLD, "LLD_SpecialSectionAttrBlock", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttrBlock 
                  RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttrBlock", zPOS_AFTER );
                  //:SetMatchingAttributesByName( mSPLDef, "LLD_SpecialSectionAttrBlock", mLLD, "LLD_SpecialSectionAttrBlock", zSET_NULL )
                  SetMatchingAttributesByName( mSPLDef, "LLD_SpecialSectionAttrBlock", mLLD, "LLD_SpecialSectionAttrBlock", zSET_NULL );
                  RESULT = SetCursorNextEntity( mLLD, "LLD_SpecialSectionAttrBlock", "" );
               } 

               RESULT = SetCursorNextEntity( mLLD, "LLD_SpecialSectionAttribute", "" );
               //:END
            } 

            RESULT = SetCursorNextEntity( mLLD, "LLD_Block", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( mLLD, "LLD_Panel", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mLLD, "LLD_Page", "" );
      //:END
   } 

   //:END

   //:DropObjectInstance( mLLD )
   DropObjectInstance( mLLD );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DELETE_LLD( VIEW ViewToWindow )

//:   VIEW mLLD_LST REGISTERED AS mLLD_LST
public int 
DELETE_LLD( View     ViewToWindow )
{
   zVIEW    mLLD_LST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mLLD  BASED ON LOD  mLLD 
   zVIEW    mLLD = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mLLD_LST, "mLLD_LST", ViewToWindow, zLEVEL_TASK );

   //:// Delete selected mLLD.
   //:ACTIVATE mLLD WHERE mLLD.LLD.ID = mLLD_LST.LLD.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mLLD_LST, "LLD", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_20( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mLLD, "mLLD", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mLLD "mLLDDelete"
   SetNameForView( mLLD, "mLLDDelete", null, zLEVEL_TASK );
   //:DELETE ENTITY mLLD.LLD 
   RESULT = DeleteEntity( mLLD, "LLD", zPOS_NEXT );
   //:COMMIT mLLD
   RESULT = CommitObjectInstance( mLLD );
   //:DropObjectInstance( mLLD )
   DropObjectInstance( mLLD );
   //:DropEntity( mLLD_LST, "LLD", zREPOS_NONE )
   DropEntity( mLLD_LST, "LLD", zREPOS_NONE );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_AddSubBlockComponent( VIEW ViewToWindow )

//:   VIEW mSPLDef      REGISTERED AS mSPLDef
public int 
GOTO_AddSubBlockComponent( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefPanel BASED ON LOD  mSPLDef
   zVIEW    mSPLDefPanel = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Simply create a temporal LLD_Block of type "SubBlock".
   //:// IF mSPLDef.LLD_Block.Type = "Block"
   //:   SET CURSOR FIRST mSPLDef.LLD_Block
   //:              WHERE mSPLDef.LLD_Block.wRelativeBlockNumber = mSPLDef.LLD_Block.wRelativeBlockNumber 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "LLD_Block", "wRelativeBlockNumber" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "LLD_Block", "wRelativeBlockNumber", lTempInteger_0, "" );
   //:   //CreateTemporalEntity( mSPLDef, "LLD_SubBlock", zPOS_BEFORE )
   //:   CREATE ENTITY mSPLDef.LLD_SubBlock  BEFORE
   RESULT = CreateEntity( mSPLDef, "LLD_SubBlock", zPOS_BEFORE );
   //:   CreateViewFromView( mSPLDefPanel, mSPLDef )
   CreateViewFromView( mSPLDefPanel, mSPLDef );
   //:   NAME VIEW mSPLDefPanel "mSPLDefPanel"
   SetNameForView( mSPLDefPanel, "mSPLDefPanel", null, zLEVEL_TASK );
   //:// ELSE
   //://    SET CURSOR FIRST mSPLDef.LLD_SubBlock WITHIN mSPLDef.LLD_Panel 
   //://               WHERE mSPLDef.LLD_SubBlock.wRelativeBlockNumber = mSPLDef.LLD_Block.wRelativeBlockNumber 
   //://    //CreateTemporalEntity( mSPLDef, "LLD_SubBlock", zPOS_AFTER )
   //://    CREATE ENTITY mSPLDef.LLD_SubBlock
   //://    CreateViewFromView( mSPLDefPanel, mSPLDef )
   //://    NAME VIEW mSPLDefPanel "mSPLDefPanel"
   //:// END

   //://CreateTemporalEntity( mSPLDefPanel, "LLD_Block", zPOS_AFTER )
   //:CREATE ENTITY mSPLDefPanel.LLD_Block  
   RESULT = CreateEntity( mSPLDefPanel, "LLD_Block", zPOS_AFTER );
   return( 0 );
// // mSPLDefPanel.LLD_Block.Type = "SubBlock"
// END
} 


//:DIALOG OPERATION
//:GOTO_AddBlockComponent( VIEW ViewToWindow )

//:   VIEW mSPLDef      REGISTERED AS mSPLDef
public int 
GOTO_AddBlockComponent( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefPanel BASED ON LOD  mSPLDef
   zVIEW    mSPLDefPanel = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Simply create a temporal LLD_Block of type "Block".
   //://CreateTemporalEntity( mSPLDef, "LLD_Block", zPOS_AFTER )
   //://CreateTemporalEntity( mSPLDef, "LLD_Block", zPOS_AFTER )
   //:CREATE ENTITY mSPLDef.LLD_Block 
   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.LLD_Block   
   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
   //:CreateViewFromView( mSPLDefPanel, mSPLDef )
   CreateViewFromView( mSPLDefPanel, mSPLDef );
   //:NAME VIEW mSPLDefPanel "mSPLDefPanel"
   SetNameForView( mSPLDefPanel, "mSPLDefPanel", null, zLEVEL_TASK );
   return( 0 );
// // mSPLDefPanel.LLD_Block.Type = "Block"
//    
// END
} 


//:DIALOG OPERATION
//:GOTO_AddPanelEntry( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
GOTO_AddPanelEntry( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //://CreateTemporalEntity( mSPLDef, "LLD_Panel", zPOS_AFTER )
   //:CREATE ENTITY mSPLDef.LLD_Panel 
   RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.LLD_Block  
   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeletePanelEntry( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
DeletePanelEntry( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mSPLDef.LLD_Panel 
   RESULT = DeleteEntity( mSPLDef, "LLD_Panel", zPOS_NEXT );
   //:SET CURSOR FIRST mSPLDef.LLD_Panel 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSPLDef.LLD_Panel
      RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
      //:mSPLDef.LLD_Panel.Left = 0
      SetAttributeFromInteger( mSPLDef, "LLD_Panel", "Left", 0 );
      //:mSPLDef.LLD_Panel.Top = 0 
      SetAttributeFromInteger( mSPLDef, "LLD_Panel", "Top", 0 );
   } 

   //:END  
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdatePanelEntry( VIEW ViewToWindow )
//:/*
public int 
GOTO_UpdatePanelEntry( View     ViewToWindow )
{

   return( 0 );
//    VIEW mSPLDef  REGISTERED AS mSPLDef
//    VIEW mSPLDef2 BASED ON LOD  mSPLDef
//    INTEGER RelativeBlockNumber
//    
//    // Create a Block if one doesn't already exist for the Panel.
//    SET CURSOR FIRST mSPLDef.LLD_Block
//    IF RESULT < zCURSOR_SET
//       CREATE ENTITY mSPLDef.LLD_Block 
//    END
//    
//    // Make sure there are no existing LLD_Block entries.
//    FOR EACH mSPLDef.LLD_Block
//       DELETE ENTITY mSPLDef.LLD_Block NONE 
//    END
//    
//    // Make sure entries are sorted correctly.
//    CreateViewFromView( mSPLDef2, mSPLDef )
//    ComputeTopPositions( mSPLDef2 )
//    DropView( mSPLDef2 )
//    OrderEntityForView( mSPLDef, "LLD_Block", "Left A wComputedTopPosition A" )
//    FOR EACH mSPLDef.LLD_Block  
//       OrderEntityForView( mSPLDef, "LLD_SubBlock", "Left A wComputedTopPosition A" )
//    END
//    
//    // Build the flat component list from the two-level Block/SubBlock structure.
//    RelativeBlockNumber = 0
//    FOR EACH mSPLDef.LLD_Block 
//       RelativeBlockNumber = RelativeBlockNumber + 1
//       CREATE ENTITY mSPLDef.LLD_Block  
//       SetMatchingAttributesByName( mSPLDef, "LLD_Block", mSPLDef, "LLD_Block", zSET_ALL )
//       mSPLDef.LLD_Block.Type = "Block"
//       mSPLDef.LLD_Block.wRelativeBlockNumber = RelativeBlockNumber
//       mSPLDef.LLD_Block.wRelativeBlockNumber = RelativeBlockNumber
//       FOR EACH mSPLDef.LLD_SubBlock 
//          RelativeBlockNumber = RelativeBlockNumber + 1
//          CREATE ENTITY mSPLDef.LLD_Block  
//          SetMatchingAttributesByName( mSPLDef, "LLD_Block", mSPLDef, "LLD_SubBlock", zSET_ALL )
//          mSPLDef.LLD_Block.Type = "SubBlock"
//          mSPLDef.LLD_Block.wRelativeBlockNumber = RelativeBlockNumber
//          mSPLDef.LLD_SubBlock.wRelativeBlockNumber = RelativeBlockNumber
//       END
//    END
// */
//    // Recursive code that didn't quite work.
//    /*FOR EACH mSPLDef.LLD_Block 
//       CREATE ENTITY mSPLDef.LLD_SubBlock  
//       SetMatchingAttributesByName( mSPLDef, "LLD_SubBlock", mSPLDef, "LLD_Block", zSET_ALL )
//       mSPLDef.LLD_SubBlock.Type = "Block"
//       TraceLineS( "#### Block 2", "" )
//       CreateViewFromView( mSPLDef2, mSPLDef )
//       SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
//       FOR EACH mSPLDef2.LLD_Block 
//          TraceLineS( "#### SubBlock 2", "" )
//          CREATE ENTITY mSPLDef.LLD_SubBlock  
//          SetMatchingAttributesByName( mSPLDef, "LLD_SubBlock", mSPLDef2, "LLD_Block", zSET_ALL )
//          mSPLDef.LLD_SubBlock.Type = "SubBlock"
//       END
//       DropView( mSPLDef2 )
//    END*/
// END
} 


//:DIALOG OPERATION
//:CancelSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CancelSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefPanel BASED ON LOD mSPLDef
   zVIEW    mSPLDefPanel = new zVIEW( );
   //:VIEW mSPLDefBlock BASED ON LOD mSPLDef
   zVIEW    mSPLDefBlock = new zVIEW( );
   //:VIEW mSubLC  BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSPLDefPanel NAMED "mSPLDefPanel"
   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropNameForView( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK )
      DropNameForView( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
      //:DropView( mSPLDefPanel )
      DropView( mSPLDefPanel );
   } 

   //:END

   //:GET VIEW mSPLDefBlock NAMED "mSPLDefBlock"
   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropNameForView( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK )
      DropNameForView( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );
      //:DropView( mSPLDefPanel )
      DropView( mSPLDefPanel );
   } 

   //:END

   //:DropNameForView( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK )
   DropNameForView( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:DropView( mSPLDef )
   DropView( mSPLDef );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropNameForView( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK )
      DropNameForView( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveSPLD( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
SaveSPLD( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW lSPLDLST REGISTERED AS lSPLDLST
   zVIEW    lSPLDLST = new zVIEW( );
   //:INTEGER SubregProductID
   int      SubregProductID = 0;

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );

   //:// Name and Version must be entered.
   //:IF mSPLDef.SubregPhysicalLabelDef.Name = ""
   if ( CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "Name", "" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save Subregistrant Label Content",
      //:             "An SPLD Name must be entered. The SPLD was not saved.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save Subregistrant Label Content", "An SPLD Name must be entered. The SPLD was not saved.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Order SubBlocks according to Y Position.
   //:FOR EACH mSPLDef.LLD_Page 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Page", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSPLDef.LLD_Panel 
      RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FOR EACH mSPLDef.LLD_Block 
         RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Block", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:OrderEntityForView( mSPLDef, "LLD_SubBlock", "wComputedTopPosition A" )
            OrderEntityForView( mSPLDef, "LLD_SubBlock", "wComputedTopPosition A" );
            RESULT = SetCursorNextEntity( mSPLDef, "LLD_Block", "" );
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "LLD_Panel", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "LLD_Page", "" );
      //:END 
   } 

   //:END

   //:// Delete work attributes that cause error curing commit validations.
   //:FOR EACH mSPLDef.DisplaySection 
   RESULT = SetCursorFirstEntity( mSPLDef, "DisplaySection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.DisplaySection NONE 
      RESULT = DeleteEntity( mSPLDef, "DisplaySection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "DisplaySection", "" );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:SaveSPLD_AndReturn( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
SaveSPLD_AndReturn( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSPLDefPanel BASED ON LOD mSPLDef
   zVIEW    mSPLDefPanel = new zVIEW( );
   //:VIEW mSPLDefBlock BASED ON LOD mSPLDef
   zVIEW    mSPLDefBlock = new zVIEW( );
   //:VIEW mSubLC  BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW lSPLDLST REGISTERED AS lSPLDLST
   zVIEW    lSPLDLST = new zVIEW( );
   //:INTEGER SubregProductID
   int      SubregProductID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );

   //:// Name and Version must be entered.
   //:IF mSPLDef.SubregPhysicalLabelDef.Name = ""
   if ( CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "Name", "" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save Subregistrant Label Content",
      //:             "An SPLD Name must be entered. The SPLD was not saved.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save Subregistrant Label Content", "An SPLD Name must be entered. The SPLD was not saved.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Other than the COMMIT, the following block of code is for debugging purposes
   //:// SubregProductID = mSPLDef.SubregPhysicalLabelDef.ID
   //:// TraceLineS( "SaveSPLD_AndReturn", "#1" )
   //:// DisplayObjectInstance( mSPLDef, "", "" )
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:// ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = SubregProductID
   //:// TraceLineS( "SaveSPLD_AndReturn", "#2" )
   //:// DisplayObjectInstance( mSPLDef, "", "" )
   //:// DropObjectInstance( mSPLDef )


   //:GET VIEW mSPLDefPanel NAMED "mSPLDefPanel"
   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropNameForView( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK )
      DropNameForView( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
      //:DropView( mSPLDefPanel )
      DropView( mSPLDefPanel );
   } 

   //:END

   //:GET VIEW mSPLDefBlock NAMED "mSPLDefBlock"
   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropNameForView( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK )
      DropNameForView( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );
      //:DropView( mSPLDefPanel )
      DropView( mSPLDefPanel );
   } 

   //:END

   //:DropNameForView( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK )
   DropNameForView( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:DropView( mSPLDef )
   DropView( mSPLDef );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropNameForView( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK )
      DropNameForView( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:// Reactivate the lSPLDLST object.
   //:SubregProductID = lSPLDLST.SubregProduct.ID 
   {MutableInt mi_SubregProductID = new MutableInt( SubregProductID );
       GetIntegerFromAttribute( mi_SubregProductID, lSPLDLST, "SubregProduct", "ID" );
   SubregProductID = mi_SubregProductID.intValue( );}
   //:DropObjectInstance( lSPLDLST )
   DropObjectInstance( lSPLDLST );
   //:ACTIVATE lSPLDLST Multiple WHERE lSPLDLST.SubregProduct.ID = SubregProductID
   o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, SubregProductID );
   RESULT = ActivateObjectInstance( lSPLDLST, "lSPLDLST", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lSPLDLST "lSPLDLST"
   SetNameForView( lSPLDLST, "lSPLDLST", null, zLEVEL_TASK );
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:DELETE_SubregProductSLC( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
DELETE_SubregProductSLC( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Delete selected mSubLC.
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLCDelete"
   SetNameForView( mSubLC, "mSubLCDelete", null, zLEVEL_TASK );
   //:DELETE ENTITY mSubLC.SubregLabelContent 
   RESULT = DeleteEntity( mSubLC, "SubregLabelContent", zPOS_NEXT );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   //:DropEntity( mSubProd, "SubregLabelContent", zREPOS_NONE )
   DropEntity( mSubProd, "SubregLabelContent", zREPOS_NONE );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SELECT_MLC_ForNewSLC( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
SELECT_MLC_ForNewSLC( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW lMLC     REGISTERED AS lMLC
   zVIEW    lMLC = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lMLC, "lMLC", ViewToWindow, zLEVEL_TASK );

   //:// Make sure that an MLC has been selected.
   //:SET CURSOR FIRST lMLC.MasterLabelContent WHERE lMLC.MasterLabelContent.wSelected = "Y"
   RESULT = SetCursorFirstEntityByString( lMLC, "MasterLabelContent", "wSelected", "Y", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Select Master Label Content",
      //:             "An MLC must be selected.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Master Label Content", "An MLC must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:// Create a new empty Subreg SLC..
   //:ACTIVATE mSubLC EMPTY 
   RESULT = ActivateEmptyObjectInstance( mSubLC, "mSubLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   //:CREATE ENTITY mSubLC.SubregLabelContent 
   RESULT = CreateEntity( mSubLC, "SubregLabelContent", zPOS_AFTER );
   //:INCLUDE mSubLC.SubregProduct FROM mSubProd.SubregProduct 
   RESULT = IncludeSubobjectFromSubobject( mSubLC, "SubregProduct", mSubProd, "SubregProduct", zPOS_AFTER );

   //://CREATE ENTITY mSubLC.CompositeComponentList 
   //://mSubLC.CompositeComponentList.DisplayType  = "-null-"
   //://mSubLC.CompositeComponentList.DisplayValue = "-----------null components------------   "

   //:// Activate the selected MLC, which has the selectable content.
   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lMLC.MasterLabelContent.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lMLC, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:INCLUDE mSubLC.MasterLabelContent FROM mMasLC.MasterLabelContent 
   RESULT = IncludeSubobjectFromSubobject( mSubLC, "MasterLabelContent", mMasLC, "MasterLabelContent", zPOS_AFTER );
   //:BuildCompositeEntries( mMasLC )
   {
    mMasLC_Object m_mMasLC_Object = new mMasLC_Object( mMasLC );
    m_mMasLC_Object.omMasLC_BuildCompositeEntries( mMasLC );
    // m_mMasLC_Object = null;  // permit gc  (unnecessary)
   }

   //:DisplayObjectInstance( mSubLC, "", "" )
   DisplayObjectInstance( mSubLC, "", "" );

   //:// Initialize the data in the SLC from the MLC.
   //:BuildSLC_FromMLC( mSubLC, mMasLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_BuildSLC_FromMLC( mSubLC, mMasLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }

   //:// Build SLC Components subobject.
   //:BuildCompositeEntries( mSubLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_BuildCompositeEntries( mSubLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd 
public int 
CancelSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Simply drop the Subreg Product.
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
SaveSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd REGISTERED AS mSubProd 
   zVIEW    mSubProd = new zVIEW( );
   //:INTEGER SubregID
   int      SubregID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Save the Subreg Product and reactivate the Product list.
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );
   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:SubregID = mSubreg.Subregistrant.ID 
   {MutableInt mi_SubregID = new MutableInt( SubregID );
       GetIntegerFromAttribute( mi_SubregID, mSubreg, "Subregistrant", "ID" );
   SubregID = mi_SubregID.intValue( );}
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = SubregID
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0, SubregID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateSubregProductSLC( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
GOTO_UpdateSubregProductSLC( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_7 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Activate selected Subreg SLC and build the Components work entries from persistent entries.
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );

   //:// Delete any S_Usage entries that aren't tied to an MLC.
   //:// Also delete any Directions for Use or Marketing Ordering entries not tied to a Usage.
   //:FOR EACH mSubLC.S_UsageType 
   RESULT = SetCursorFirstEntity( mSubLC, "S_UsageType", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSubLC.S_Usage 
      RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSubLC.M_Usage DOES NOT EXIST
         lTempInteger_1 = CheckExistenceOfEntity( mSubLC, "M_Usage" );
         if ( lTempInteger_1 != 0 )
         { 
            //:DELETE ENTITY mSubLC.S_Usage NONE 
            RESULT = DeleteEntity( mSubLC, "S_Usage", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_UsageType", "" );
      //:END
   } 

   //:END
   //:FOR EACH mSubLC.S_DirectionsForUseStatement WITHIN mSubLC.SubregLabelContent 
   RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsForUseStatement", "SubregLabelContent" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSubLC.S_DirectionsUsageOrdering 
      RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSubLC.S_DirectionsUsage DOES NOT EXIST
         lTempInteger_2 = CheckExistenceOfEntity( mSubLC, "S_DirectionsUsage" );
         if ( lTempInteger_2 != 0 )
         { 
            //:DELETE ENTITY mSubLC.S_DirectionsUsageOrdering NONE 
            RESULT = DeleteEntity( mSubLC, "S_DirectionsUsageOrdering", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_DirectionsForUseStatement", "SubregLabelContent" );
      //:END
   } 

   //:END
   //:FOR EACH mSubLC.S_MarketingStatement WITHIN mSubLC.SubregLabelContent 
   RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingStatement", "SubregLabelContent" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSubLC.S_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mSubLC.S_MarketingUsage DOES NOT EXIST
         lTempInteger_3 = CheckExistenceOfEntity( mSubLC, "S_MarketingUsage" );
         if ( lTempInteger_3 != 0 )
         { 
            //:DELETE ENTITY mSubLC.S_MarketingUsageOrdering NONE 
            RESULT = DeleteEntity( mSubLC, "S_MarketingUsageOrdering", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mSubLC, "S_MarketingUsageOrdering", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_MarketingStatement", "SubregLabelContent" );
      //:END
   } 

   //:END

   //:FOR EACH mSubLC.S_MarketingSection 
   RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSubLC.S_MarketingStatement 
      RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FOR EACH mSubLC.S_MarketingUsageOrdering 
         RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mSubLC.S_MarketingUsage DOES NOT EXIST
            lTempInteger_4 = CheckExistenceOfEntity( mSubLC, "S_MarketingUsage" );
            if ( lTempInteger_4 != 0 )
            { 
               //:MessageSend( ViewToWindow, "", "Test",
               //:             "Missing usage.",
               //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( ViewToWindow, "", "Test", "Missing usage.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
               m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
               //:RETURN 2            
               if(8==8)return( 2 );
            } 

            RESULT = SetCursorNextEntity( mSubLC, "S_MarketingUsageOrdering", "" );
            //:END         
         } 

         RESULT = SetCursorNextEntity( mSubLC, "S_MarketingStatement", "" );
         //:END      
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_MarketingSection", "" );
      //:END   
   } 

   //:END
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:BuildCompositeEntries( mSubLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_BuildCompositeEntries( mSubLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }

   //:// Relink Usage.
   //:FOR EACH mSubLC.S_DirectionsForUseSection 
   RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mSubLC.S_DirectionsUsageOrdering 
      RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST mSubLC.S_Usage WHERE mSubLC.S_Usage.ID = mSubLC.S_DirectionsUsage.ID 
         {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                   GetIntegerFromAttribute( mi_lTempInteger_5, mSubLC, "S_DirectionsUsage", "ID" );
         lTempInteger_5 = mi_lTempInteger_5.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_Usage", "ID", lTempInteger_5, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:TraceLineS( "######## No DU Link: ", mSubLC.S_DirectionsUsage.Name )
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mSubLC, "S_DirectionsUsage", "Name" );
            szTempString_0 = sb_szTempString_0.toString( );}
            TraceLineS( "######## No DU Link: ", szTempString_0 );
         } 

         RESULT = SetCursorNextEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
         //:END 
      } 

      //:END
      //:FOR EACH mSubLC.S_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST mSubLC.S_Usage WHERE mSubLC.S_Usage.ID = mSubLC.S_MarketingUsage.ID 
         {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                   GetIntegerFromAttribute( mi_lTempInteger_6, mSubLC, "S_MarketingUsage", "ID" );
         lTempInteger_6 = mi_lTempInteger_6.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_Usage", "ID", lTempInteger_6, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:TraceLineS( "######## No Mkt Link: ", mSubLC.S_MarketingUsage.Name )
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, mSubLC, "S_MarketingUsage", "Name" );
            szTempString_1 = sb_szTempString_1.toString( );}
            TraceLineS( "######## No Mkt Link: ", szTempString_1 );
         } 

         RESULT = SetCursorNextEntity( mSubLC, "S_MarketingUsageOrdering", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Activate related MLC, which has the selectable content.
   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mSubProd.MasterLabelContent.ID 
   {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
       GetIntegerFromAttribute( mi_lTempInteger_7, mSubProd, "MasterLabelContent", "ID" );
   lTempInteger_7 = mi_lTempInteger_7.intValue( );}
   o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_1, lTempInteger_7 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:BuildCompositeEntries( mMasLC )
   {
    mMasLC_Object m_mMasLC_Object = new mMasLC_Object( mMasLC );
    m_mMasLC_Object.omMasLC_BuildCompositeEntries( mMasLC );
    // m_mMasLC_Object = null;  // permit gc  (unnecessary)
   }
   //:BuildWorkVariables( mSubLC, mMasLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_BuildWorkVariables( mSubLC, mMasLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:RemoveMLC_UsageEntries( VIEW ViewToWindow )

//:   VIEW mMasLC  REGISTERED AS mMasLC
public int 
RemoveMLC_UsageEntries( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Remove each selected Usage entry in the Target subobject.
   //:FOR EACH mMasLC.M_UsageSelectTarget 
   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageSelectTarget", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_UsageSelectTarget.wSelected = "Y"
      if ( CompareAttributeToString( mMasLC, "M_UsageSelectTarget", "wSelected", "Y" ) == 0 )
      { 
         //:EXCLUDE mMasLC.M_UsageSelectTarget NONE
         RESULT = ExcludeEntity( mMasLC, "M_UsageSelectTarget", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_UsageSelectTarget", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMLC_ComponentsForSLC( VIEW ViewToWindow )

//:   VIEW mSubLC  REGISTERED AS mSubLC
public int 
SelectMLC_ComponentsForSLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC  REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mMasLC1 BASED ON LOD  mMasLC
   zVIEW    mMasLC1 = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   //:STRING ( 1 ) szFirstMarketingSectionFlag
   String   szFirstMarketingSectionFlag = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Copy the selected MLC components to the SLC.

   //:// Note that we will only process Marketing Statements and Usage entries under Directions for Use or Marketing Sections.

   //:// There's a problem keeping the correct order. So, to solve that we will go through any existing SLC Marketing and 
   //:// Directions components and select the corresponding MLC component. Then we'll delete all the existing Marketing and 
   //:// Directions SLC components and recreate them from all selected MLC components.
   //:// Also, we will need to be sure that any Section entries for selected Statement entries are also selected.

   //:// Delete all Directions Usage and Marketing Component entries, after setting the selected flag for the corresponding 
   //:// MLC entry..
   //:FOR EACH mSubLC.CompositeComponentList
   RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.CompositeComponentList.Type = "M_MarketingSection" OR 
      //:   mSubLC.CompositeComponentList.Type = "M_MarketingStatement" //OR 
      if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingSection" ) == 0 || CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingStatement" ) == 0 )
      { 
         //://mSubLC.CompositeComponentList.Type = "M_MarketingUsageOrdering" OR 
         //://mSubLC.CompositeComponentList.Type = "M_DirectionsUsageOrdering"

         //:IF mSubLC.CompositeComponentList.OriginalTypeID != ""
         if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "OriginalTypeID", "" ) != 0 )
         { 
            //:SET CURSOR FIRST mMasLC.CompositeComponentList 
            //:           WHERE mMasLC.CompositeComponentList.OriginalTypeID = mSubLC.CompositeComponentList.OriginalTypeID
            //:             AND mMasLC.CompositeComponentList.DisplayType    = mSubLC.CompositeComponentList.DisplayType 
            RESULT = SetCursorFirstEntity( mMasLC, "CompositeComponentList", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mMasLC, "CompositeComponentList", "OriginalTypeID", mSubLC, "CompositeComponentList", "OriginalTypeID" ) != 0 ||
                       CompareAttributeToAttribute( mMasLC, "CompositeComponentList", "DisplayType", mSubLC, "CompositeComponentList", "DisplayType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mMasLC, "CompositeComponentList", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:mMasLC.CompositeComponentList.Selected = "Y"
               SetAttributeFromString( mMasLC, "CompositeComponentList", "Selected", "Y" );
            } 

            //:END
         } 

         //:END

         //:DELETE ENTITY mSubLC.CompositeComponentList NONE 
         RESULT = DeleteEntity( mSubLC, "CompositeComponentList", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END

   //:// Make sure that if a lower level entry is selected, its parent entry is also selected.
   //:// We will do this in two loops, setting level 2 entries from selected level 3 entries first and then
   //:// setting level 1 entries from selected level 2 entries.
   //:CreateViewFromView( mMasLC1, mMasLC )
   CreateViewFromView( mMasLC1, mMasLC );
   //:FOR EACH mMasLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mMasLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.CompositeComponentList.SelectLevel = 2
      if ( CompareAttributeToInteger( mMasLC, "CompositeComponentList", "SelectLevel", 2 ) == 0 )
      { 
         //:DropView( mMasLC1 )
         DropView( mMasLC1 );
         //:CreateViewFromView( mMasLC1, mMasLC )
         CreateViewFromView( mMasLC1, mMasLC );
         //:ELSE
      } 
      else
      { 
         //:IF mMasLC.CompositeComponentList.SelectLevel = 3
         if ( CompareAttributeToInteger( mMasLC, "CompositeComponentList", "SelectLevel", 3 ) == 0 )
         { 
            //:IF mMasLC.CompositeComponentList.Selected = "Y"
            if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Selected", "Y" ) == 0 )
            { 
               //:mMasLC1.CompositeComponentList.Selected = "Y"
               SetAttributeFromString( mMasLC1, "CompositeComponentList", "Selected", "Y" );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END
   //:FOR EACH mMasLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mMasLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.CompositeComponentList.SelectLevel = 1
      if ( CompareAttributeToInteger( mMasLC, "CompositeComponentList", "SelectLevel", 1 ) == 0 )
      { 
         //:DropView( mMasLC1 )
         DropView( mMasLC1 );
         //:CreateViewFromView( mMasLC1, mMasLC )
         CreateViewFromView( mMasLC1, mMasLC );
         //:ELSE
      } 
      else
      { 
         //:IF mMasLC.CompositeComponentList.SelectLevel = 2
         if ( CompareAttributeToInteger( mMasLC, "CompositeComponentList", "SelectLevel", 2 ) == 0 )
         { 
            //:IF mMasLC.CompositeComponentList.Selected = "Y"
            if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Selected", "Y" ) == 0 )
            { 
               //:mMasLC1.CompositeComponentList.Selected = "Y"
               SetAttributeFromString( mMasLC1, "CompositeComponentList", "Selected", "Y" );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END

   //:// Create an SLC entry for each selected MLC entry, which is either Marketing Section, Marketing Statement,
   //:// Marketing Usage or Directions for Use Usage.
   //:szFirstMarketingSectionFlag = "Y"
    {StringBuilder sb_szFirstMarketingSectionFlag;
   if ( szFirstMarketingSectionFlag == null )
      sb_szFirstMarketingSectionFlag = new StringBuilder( 32 );
   else
      sb_szFirstMarketingSectionFlag = new StringBuilder( szFirstMarketingSectionFlag );
      ZeidonStringCopy( sb_szFirstMarketingSectionFlag, 1, 0, "Y", 1, 0, 2 );
   szFirstMarketingSectionFlag = sb_szFirstMarketingSectionFlag.toString( );}
   //:FOR EACH mMasLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mMasLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// If the Composite Component is a Directions for Use Section, position on the corresponding SLC Composite
      //:// entry for creation of potential Usage entries.
      //:IF mMasLC.CompositeComponentList.Type = "M_DirectionsForUseSection"
      if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Type", "M_DirectionsForUseSection" ) == 0 )
      { 
         //:SET CURSOR FIRST mSubLC.CompositeComponentList 
         //:           WHERE mSubLC.CompositeComponentList.Type           = "M_DirectionsForUseSection" 
         //:             AND mSubLC.CompositeComponentList.OriginalTypeID = mMasLC.CompositeComponentList.OriginalTypeID 
         RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_DirectionsForUseSection" ) != 0 ||
                    CompareAttributeToAttribute( mSubLC, "CompositeComponentList", "OriginalTypeID", mMasLC, "CompositeComponentList", "OriginalTypeID" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSubLC, "CompositeComponentList", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:TraceLineI( "#### No Match on M_Dir ID: ", mMasLC.CompositeComponentList.OriginalTypeID )
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "CompositeComponentList", "OriginalTypeID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            TraceLineI( "#### No Match on M_Dir ID: ", lTempInteger_0 );
         } 

         //:END
      } 

      //:END

      //:// If the Composite Component is a Marketing Section, positioning depends on whether or not this is the first 
      //:// Marketing Section. If so, we need to position on the last Directions for Use Section or Statement. 
      //:// Otherwise, we are already correctly positioned.
      //:IF mMasLC.CompositeComponentList.Type = "M_MarketingSection"
      if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Type", "M_MarketingSection" ) == 0 )
      { 
         //:IF szFirstMarketingSectionFlag = "Y"
         if ( ZeidonStringCompare( szFirstMarketingSectionFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR LAST mSubLC.CompositeComponentList 
            //:          WHERE mSubLC.CompositeComponentList.Type = "M_DirectionsForUseSection" 
            //:             OR mSubLC.CompositeComponentList.Type = "M_DirectionsForUseStatement"
            RESULT = SetCursorLastEntity( mSubLC, "CompositeComponentList", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_DirectionsForUseSection" ) != 0 &&
                       CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_DirectionsForUseStatement" ) != 0 ) )
               { 
                  RESULT = SetCursorPrevEntity( mSubLC, "CompositeComponentList", "" );
               } 

            } 

            //:szFirstMarketingSectionFlag = "N"
             {StringBuilder sb_szFirstMarketingSectionFlag;
            if ( szFirstMarketingSectionFlag == null )
               sb_szFirstMarketingSectionFlag = new StringBuilder( 32 );
            else
               sb_szFirstMarketingSectionFlag = new StringBuilder( szFirstMarketingSectionFlag );
                        ZeidonStringCopy( sb_szFirstMarketingSectionFlag, 1, 0, "N", 1, 0, 2 );
            szFirstMarketingSectionFlag = sb_szFirstMarketingSectionFlag.toString( );}
         } 

         //:END
         //:CREATE ENTITY mSubLC.CompositeComponentList 
         RESULT = CreateEntity( mSubLC, "CompositeComponentList", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, "CompositeComponentList", mMasLC, "CompositeComponentList", zSET_ALL )
         SetMatchingAttributesByName( mSubLC, "CompositeComponentList", mMasLC, "CompositeComponentList", zSET_ALL );
         //:mSubLC.CompositeComponentList.Selected = "" 
         SetAttributeFromString( mSubLC, "CompositeComponentList", "Selected", "" );
      } 

      //:END

      //:// Process the three entry types defined above.
      //:IF mMasLC.CompositeComponentList.Type = "M_DirectionsUsageOrdering" OR 
      //:   mMasLC.CompositeComponentList.Type = "M_MarketingUsageOrdering" OR 
      //:   mMasLC.CompositeComponentList.Type = "M_MarketingStatement"
      if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Type", "M_DirectionsUsageOrdering" ) == 0 || CompareAttributeToString( mMasLC, "CompositeComponentList", "Type", "M_MarketingUsageOrdering" ) == 0 ||
           CompareAttributeToString( mMasLC, "CompositeComponentList", "Type", "M_MarketingStatement" ) == 0 )
      { 

         //:IF mMasLC.CompositeComponentList.Selected = "Y"
         if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Selected", "Y" ) == 0 )
         { 
            //:CREATE ENTITY mSubLC.CompositeComponentList 
            RESULT = CreateEntity( mSubLC, "CompositeComponentList", zPOS_AFTER );
            //:SetMatchingAttributesByName( mSubLC, "CompositeComponentList", mMasLC, "CompositeComponentList", zSET_ALL )
            SetMatchingAttributesByName( mSubLC, "CompositeComponentList", mMasLC, "CompositeComponentList", zSET_ALL );
            //:mSubLC.CompositeComponentList.Selected = "" 
            SetAttributeFromString( mSubLC, "CompositeComponentList", "Selected", "" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:SaveSubregistrantLabelContent( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
SaveSubregistrantLabelContent( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:INTEGER OriginalTypeID
   int      OriginalTypeID = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Name and Version must be entered.
   //:IF mSubLC.SubregLabelContent.Description = "" OR mSubLC.SubregLabelContent.Version = ""
   if ( CompareAttributeToString( mSubLC, "SubregLabelContent", "Description", "" ) == 0 || CompareAttributeToString( mSubLC, "SubregLabelContent", "Version", "" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save Subregistrant Label Content",
      //:             "Both a Name and Version must be entered. The SLC was not saved.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save Subregistrant Label Content", "Both a Name and Version must be entered. The SLC was not saved.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Make sure that any selected Components Usage entries have S_Usage entries.
   //:/*FOR EACH mSubLC.CompositeComponentList
   //:   IF mSubLC.CompositeComponentList.Type = "M_DirectionsUsageOrdering" OR 
   //:      mSubLC.CompositeComponentList.Type = "M_MarketingUsageOrdering"
   //:      
   //:      OriginalTypeID = mSubLC.CompositeComponentList.OriginalTypeID 
   //:      SET CURSOR FIRST mSubLC.M_Usage WITHIN mSubLC.SubregLabelContent 
   //:                 WHERE mSubLC.M_Usage.ID = OriginalTypeID
   //:      IF RESULT < zCURSOR_SET
   //:         SET CURSOR FIRST mMasLC.M_Usage WHERE mMasLC.M_Usage.ID = OriginalTypeID
   //:         SET CURSOR LAST mSubLC.S_Usage 
   //:         CREATE ENTITY mSubLC.S_Usage 
   //:         SetMatchingAttributesByName( mSubLC, "S_Usage", mMasLC, "M_Usage", zSET_NULL )
   //:         INCLUDE mSubLC.M_Usage FROM mMasLC.M_Usage 
   //:      END
   //:   END
   //:END*/

   //:// If the S_Usage Statements are null, then we need to first commit mSubLC to get the ID's set.
   //:/*SET CURSOR FIRST mSubLC.S_Usage 
   //:IF mSubLC.S_Usage.ID = ""
   //:   COMMIT mSubLC
   //:END */
   //:// Commit the mSubLC object to make sure any S_Usage statements have ID's.
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:// Process any Changes in the CompositeComponentList entries against the main subobject structure of the SLC.
   //:// Note that only Marketing Statements and Usage entries under Directions for Use and Marketing are affected.

   //:// First delete any entries that are in the main structure but not in the Composite Component List.
   //:// Note that we only consider Marketing Statements and Directions Usage and Marketing Usage Statements.
   //:/*FOR EACH mSubLC.S_DirectionsForUseSection 
   //:   FOR EACH mSubLC.S_DirectionsUsageOrdering 
   //:      SET CURSOR FIRST mSubLC.CompositeComponentList 
   //:                 WHERE mSubLC.CompositeComponentList.SLC_OriginalTypeID = mSubLC.S_DirectionsUsage.ID 
   //:                   AND mSubLC.CompositeComponentList.Type               = "M_DirectionsUsageOrdering" 
   //:      IF RESULT < zCURSOR_SET
   //:         //SET CURSOR FIRST mSubLC.S_Usage WHERE mSubLC.S_Usage.ID = mSubLC.S_DirectionsUsage.ID 
   //:         //IF RESULT >= zCURSOR_SET
   //:         //   DELETE ENTITY mSubLC.S_Usage NONE 
   //:         //END 
   //:         DELETE ENTITY mSubLC.S_DirectionsUsageOrdering NONE 
   //:      END
   //:   END
   //:END
   //:FOR EACH mSubLC.S_MarketingSection 
   //:   SET CURSOR FIRST mSubLC.CompositeComponentList 
   //:              WHERE mSubLC.CompositeComponentList.SLC_OriginalTypeID = mSubLC.S_MarketingSection.ID 
   //:                AND mSubLC.CompositeComponentList.Type               = "M_MarketingSection" 
   //:   IF RESULT >= zCURSOR_SET
   //:      // The Marketing Section was NOT deleted.
   //:      FOR EACH mSubLC.S_MarketingStatement 
   //:         SET CURSOR FIRST mSubLC.CompositeComponentList 
   //:                    WHERE mSubLC.CompositeComponentList.SLC_OriginalTypeID = mSubLC.S_MarketingStatement.ID 
   //:                      AND mSubLC.CompositeComponentList.Type               = "M_MarketingStatement" 
   //:         IF RESULT < zCURSOR_SET
   //:            // Delete the Marketing Statement.
   //:            DELETE ENTITY mSubLC.S_MarketingStatement NONE
   //:         ELSE
   //:            // See if we need to delete any Marketing Usage entries.
   //:            FOR EACH mSubLC.S_MarketingUsageOrdering 
   //:               SET CURSOR FIRST mSubLC.CompositeComponentList 
   //:                          WHERE mSubLC.CompositeComponentList.SLC_OriginalTypeID = mSubLC.S_MarketingUsage.ID 
   //:                            AND mSubLC.CompositeComponentList.Type               = "M_MarketingUsageOrdering" 
   //:               IF RESULT < zCURSOR_SET
   //:                  DELETE ENTITY mSubLC.S_MarketingUsageOrdering NONE 
   //:               END
   //:            END
   //:         END
   //:      END
   //:   ELSE
   //:      // The Marketing Section WAS deleted.
   //:      DELETE ENTITY mSubLC.S_MarketingSection NONE
   //:   END
   //:END

   //:// Next add any new CompositeComponentList entries.
   //:// Note that we only consider Marketing Statements and Directions and Marketing Usage Statements.
   //:FOR EACH mSubLC.CompositeComponentList 
   //:   OriginalTypeID = mSubLC.CompositeComponentList.OriginalTypeID
   //:   // Directions for Use Section
   //:   IF mSubLC.CompositeComponentList.Type = "M_DirectionsForUseSection"
   //:      SET CURSOR FIRST mSubLC.M_DirectionsForUseSection WITHIN mSubLC.SubregLabelContent 
   //:                 WHERE mSubLC.M_DirectionsForUseSection.ID = OriginalTypeID 
   //:   END
   //:   
   //:   // Marketing Section
   //:   IF mSubLC.CompositeComponentList.Type = "M_MarketingSection" 
   //:      SET CURSOR FIRST mSubLC.M_MarketingSection WITHIN mSubLC.SubregLabelContent 
   //:                 WHERE mSubLC.M_MarketingSection.ID = OriginalTypeID 
   //:      IF RESULT < zCURSOR_SET
   //:         SET CURSOR FIRST mMasLC.M_MarketingSection 
   //:                    WHERE mMasLC.M_MarketingSection.ID = OriginalTypeID 
   //:         IF RESULT < zCURSOR_SET
   //:            TraceLineI( "######### No match on Marketing Section ID", OriginalTypeID )
   //:         END
   //:         // Add Marketing Section
   //:         CREATE ENTITY mSubLC.S_MarketingSection
   //:         SetMatchingAttributesByName( mSubLC, "S_MarketingSection", mMasLC, "M_MarketingSection", zSET_NULL )
   //:         INCLUDE mSubLC.M_MarketingSection FROM mMasLC.M_MarketingSection
   //:      END
   //:   END
   //:   
   //:   // Marketing Statement
   //:   IF mSubLC.CompositeComponentList.Type = "M_MarketingStatement" 
   //:      SET CURSOR FIRST mSubLC.M_MarketingStatement WITHIN mSubLC.S_MarketingSection 
   //:                 WHERE mSubLC.M_MarketingStatement.ID = OriginalTypeID 
   //:      IF RESULT < zCURSOR_SET
   //:         SET CURSOR FIRST mMasLC.M_MarketingStatement WITHIN mMasLC.MasterLabelContent 
   //:                    WHERE mMasLC.M_MarketingStatement.ID = OriginalTypeID 
   //:         IF RESULT < zCURSOR_SET
   //:            TraceLineI( "######### No match on Marketing Statement ID", OriginalTypeID )
   //:         END
   //:         // Add Marketing Statement
   //:         CREATE ENTITY mSubLC.S_MarketingStatement
   //:         SetMatchingAttributesByName( mSubLC, "S_MarketingStatement", mMasLC, "M_MarketingStatement", zSET_NULL )
   //:         INCLUDE mSubLC.M_MarketingStatement FROM mMasLC.M_MarketingStatement
   //:      END
   //:   END
   //:   
   //:   // Directions for Use Usage
   //:   IF mSubLC.CompositeComponentList.Type = "M_DirectionsUsageOrdering"
   //:      SET CURSOR FIRST mSubLC.M_Usage WITHIN mSubLC.SubregLabelContent 
   //:                 WHERE mSubLC.M_Usage.ID = OriginalTypeID 
   //:      SET CURSOR FIRST mSubLC.S_DirectionsUsage WITHIN mSubLC.S_DirectionsForUseSection 
   //:                 WHERE mSubLC.S_DirectionsUsage.ID = mSubLC.S_Usage.ID 
   //:      IF RESULT < zCURSOR_SET
   //:         CREATE ENTITY mSubLC.S_DirectionsUsageOrdering
   //:         INCLUDE mSubLC.S_DirectionsUsage FROM mSubLC.S_Usage
   //:      END
   //:   END
   //:   
   //:   // Marketing Usage
   //:   IF mSubLC.CompositeComponentList.Type = "M_MarketingUsageOrdering"
   //:      SET CURSOR FIRST mSubLC.M_Usage WITHIN mSubLC.SubregLabelContent 
   //:                 WHERE mSubLC.M_Usage.ID = OriginalTypeID 
   //:      SET CURSOR FIRST mSubLC.S_MarketingUsage WITHIN mSubLC.S_MarketingSection 
   //:                 WHERE mSubLC.S_MarketingUsage.ID = mSubLC.S_Usage.ID
   //:      IF RESULT < zCURSOR_SET
   //:         CREATE ENTITY mSubLC.S_MarketingUsageOrdering
   //:         INCLUDE mSubLC.S_MarketingUsage FROM mSubLC.S_Usage
   //:      END
   //:   END
   //:END

   //:SetAttributeFromCurrentDateTime( mSubLC, "SubregLabelContent", "ModifiedDateTime" )
   //:COMMIT mSubLC
   //:IF RESULT < 0
   //:   MessageSend( ViewToWindow, "", "Save Subregistrant Label Content",
   //:                "An error occurred during the commit. The SLC was not saved.",
   //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:   RETURN 2
   //:END*/
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteMLC_ComponentsForSLC( VIEW ViewToWindow )

//:   VIEW mSubLC  REGISTERED AS mSubLC
public int 
DeleteMLC_ComponentsForSLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC  REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mSubLC2 BASED ON LOD  mSubLC
   zVIEW    mSubLC2 = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Delete the selected SLC component entries.

   //:// Make sure that only Marketing Statements Usage entries under Directions for Use and Marketing are marked for removal.
   //:SET CURSOR FIRST mSubLC.CompositeComponentList WHERE mSubLC.CompositeComponentList.Selected = "Y"
   //:                                                 AND mSubLC.CompositeComponentList.Type != "M_DirectionsUsageOrdering"
   //:                                                 AND mSubLC.CompositeComponentList.Type != "M_MarketingUsageOrdering"
   //:                                                 AND mSubLC.CompositeComponentList.Type != "M_MarketingStatement" 
   //:                                                 AND mSubLC.CompositeComponentList.Type != "M_MarketingSection"
   RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Selected", "Y" ) != 0 || CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_DirectionsUsageOrdering" ) == 0 ||
              CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingUsageOrdering" ) == 0 || CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingStatement" ) == 0 ||
              CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingSection" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( mSubLC, "CompositeComponentList", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Remove Components",
      //:             "Only Marketing Statements and Usage Statements can be removed.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Remove Components", "Only Marketing Statements and Usage Statements can be removed.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// First, for each Marketing Section selected, set the delete flag for all corresponding Marketing Statement entries.
   //:FOR EACH mSubLC.CompositeComponentList
   RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.CompositeComponentList.Type = "M_MarketingSection" AND mSubLC.CompositeComponentList.Selected = "Y"
      if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingSection" ) == 0 && CompareAttributeToString( mSubLC, "CompositeComponentList", "Selected", "Y" ) == 0 )
      { 
         //:CreateViewFromView( mSubLC2, mSubLC )
         CreateViewFromView( mSubLC2, mSubLC );
         //:SET CURSOR NEXT mSubLC2.CompositeComponentList 
         RESULT = SetCursorNextEntity( mSubLC2, "CompositeComponentList", "" );
         //:LOOP WHILE RESULT >= zCURSOR_SET AND mSubLC2.CompositeComponentList.Type = "M_MarketingStatement"
         while ( RESULT >= zCURSOR_SET && CompareAttributeToString( mSubLC2, "CompositeComponentList", "Type", "M_MarketingStatement" ) == 0 )
         { 
            //:mSubLC2.CompositeComponentList.Selected = "Y"
            SetAttributeFromString( mSubLC2, "CompositeComponentList", "Selected", "Y" );
            //:SET CURSOR NEXT mSubLC2.CompositeComponentList 
            RESULT = SetCursorNextEntity( mSubLC2, "CompositeComponentList", "" );
         } 

         //:END
         //:DropView( mSubLC2 )
         DropView( mSubLC2 );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END

   //:// Next delete each selected Statement entry.
   //:FOR EACH mSubLC.CompositeComponentList WHERE mSubLC.CompositeComponentList.Selected = "Y"
   RESULT = SetCursorFirstEntityByString( mSubLC, "CompositeComponentList", "Selected", "Y", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.CompositeComponentList.OriginalTypeID != ""
      if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "OriginalTypeID", "" ) != 0 )
      { 
         //:SET CURSOR FIRST mMasLC.CompositeComponentList 
         //:           WHERE mMasLC.CompositeComponentList.OriginalTypeID = mSubLC.CompositeComponentList.OriginalTypeID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "CompositeComponentList", "OriginalTypeID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mMasLC, "CompositeComponentList", "OriginalTypeID", lTempInteger_0, "" );
         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// Turn off mMasLC selected.
            //:mMasLC.CompositeComponentList.Selected = ""
            SetAttributeFromString( mMasLC, "CompositeComponentList", "Selected", "" );
         } 

         //:END
         //:DELETE ENTITY mSubLC.CompositeComponentList NONE 
         RESULT = DeleteEntity( mSubLC, "CompositeComponentList", zREPOS_NONE );
         //:ELSE
      } 
      else
      { 
         //:IF mSubLC.CompositeComponentList.SLC_OriginalTypeID != ""
         if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "SLC_OriginalTypeID", "" ) != 0 )
         { 
            //:DELETE ENTITY mSubLC.CompositeComponentList NONE
            RESULT = DeleteEntity( mSubLC, "CompositeComponentList", zREPOS_NONE );
         } 

         //:   /*IF mSubLC.CompositeComponentList.Type = "M_MarketingStatement"
         //:      SET CURSOR FIRST mSubLC.S_MarketingStatement WITHIN mSubLC.SubregLabelContent 
         //:                 WHERE mSubLC.S_MarketingStatement.ID = mSubLC.CompositeComponentList.SLC_OriginalTypeID
         //:      IF RESULT >= zCURSOR_SET
         //:         mSubLC.S_MarketingStatement.wDeleteFlag = "Y"
         //:         DELETE ENTITY mSubLC.CompositeComponentList NONE 
         //:      END
         //:   ELSE
         //:      SET CURSOR FIRST mSubLC.S_MarketingSection WHERE mSubLC.S_MarketingSection.ID = mSubLC.CompositeComponentList.SLC_OriginalTypeID
         //:      IF RESULT >= zCURSOR_SET
         //:         mSubLC.S_MarketingSection.wDeleteFlag = "Y"
         //:         DELETE ENTITY mSubLC.CompositeComponentList NONE 
         //:      END
         //:   END*/
         //:END
      } 

      RESULT = SetCursorNextEntityByString( mSubLC, "CompositeComponentList", "Selected", "Y", "" );
      //:END
   } 

   //:END

   //:// Next delete any Marketing Section entries without Statement entries.
   //:// (A Section entry does not have DisplayType = "Statement".)
   //:FOR EACH mSubLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.CompositeComponentList.Type = "S_MarketingSection"
      if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "S_MarketingSection" ) == 0 )
      { 
         //:CreateViewFromView( mSubLC2, mSubLC )
         CreateViewFromView( mSubLC2, mSubLC );
         //:SET CURSOR NEXT mSubLC2.CompositeComponentList 
         RESULT = SetCursorNextEntity( mSubLC2, "CompositeComponentList", "" );
         //:IF RESULT >= zCURSOR_SET AND mSubLC2.CompositeComponentList.DisplayType != "Statement"
         if ( RESULT >= zCURSOR_SET && CompareAttributeToString( mSubLC2, "CompositeComponentList", "DisplayType", "Statement" ) != 0 )
         { 
            //:// The next entry is also not a Statement.
            //:SET CURSOR FIRST mMasLC.CompositeComponentList 
            //:           WHERE mMasLC.CompositeComponentList.OriginalTypeID = mSubLC.CompositeComponentList.OriginalTypeID 
            //:             AND mMasLC.CompositeComponentList.DisplayType    = mSubLC.CompositeComponentList.DisplayType 
            RESULT = SetCursorFirstEntity( mMasLC, "CompositeComponentList", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mMasLC, "CompositeComponentList", "OriginalTypeID", mSubLC, "CompositeComponentList", "OriginalTypeID" ) != 0 ||
                       CompareAttributeToAttribute( mMasLC, "CompositeComponentList", "DisplayType", mSubLC, "CompositeComponentList", "DisplayType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mMasLC, "CompositeComponentList", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// Turn off mMasLC selected.
               //:mMasLC.CompositeComponentList.Selected = ""
               SetAttributeFromString( mMasLC, "CompositeComponentList", "Selected", "" );
            } 

            //:END
            //:DELETE ENTITY mSubLC.CompositeComponentList NONE 
            RESULT = DeleteEntity( mSubLC, "CompositeComponentList", zREPOS_NONE );
         } 

         //:END
         //:DropView( mSubLC2 )
         DropView( mSubLC2 );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END

   //:// If all entries have been deleted, put the null entry back.
   //:SET CURSOR FIRST mSubLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.CompositeComponentList 
      RESULT = CreateEntity( mSubLC, "CompositeComponentList", zPOS_AFTER );
      //:mSubLC.CompositeComponentList.DisplayType  = "-null-"
      SetAttributeFromString( mSubLC, "CompositeComponentList", "DisplayType", "-null-" );
      //:mSubLC.CompositeComponentList.DisplayValue = "-----------null components------------" 
      SetAttributeFromString( mSubLC, "CompositeComponentList", "DisplayValue", "-----------null components------------" );
   } 

   //:END
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:CancelSubregistrantLabelContent( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
CancelSubregistrantLabelContent( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitListSubregProducts( View     ViewToWindow )
{

   //:InitListSubregProducts( VIEW ViewToWindow )

   //:SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ProcessUserLogin( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ProcessUserLogin( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mLLD_LST BASED ON LOD  mLLD
   zVIEW    mLLD_LST = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Activate the Subreg for the Organization specified and the list of all LLD entries.

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END

   //:GET VIEW mLLD_LST NAMED "mLLD_LST"
   RESULT = GetViewByName( mLLD_LST, "mLLD_LST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mLLD_LST )
      DropObjectInstance( mLLD_LST );
   } 

   //:END

   //:// *** NOTE THAT WE ARE CURRENTLY ACTIVATING THE ONLY SUBREG.
   //:ACTIVATE mSubreg WHERE mSubreg.SubregOrganization.Name = wWebXfer.Root.AttemptLoginName 
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "AttemptLoginName" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

   //:ACTIVATE mLLD_LST RootOnlyMultiple
   RESULT = ActivateObjectInstance( mLLD_LST, "mLLD", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW mLLD_LST "mLLD_LST" 
   SetNameForView( mLLD_LST, "mLLD_LST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ProcessLogout( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ProcessLogout( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Clean up any views.
   //:DropObjectInstance( wWebXfer )
   DropObjectInstance( wWebXfer );
   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg    REGISTERED AS mSubreg
public int 
GOTO_UpdateSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd   BASED ON LOD  mSubProd 
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC     BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW lSPLDLST   BASED ON LOD  lSPLDLST
   zVIEW    lSPLDLST = new zVIEW( );
   //:STRING ( 30 )  szDateTime
   String   szDateTime = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Activate selected Subreg Product.
   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubreg.SubregProduct.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "SubregProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );

   //:ACTIVATE lSPLDLST Multiple WHERE lSPLDLST.SubregProduct.ID = mSubreg.SubregProduct.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSubreg, "SubregProduct", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( lSPLDLST, "lSPLDLST", ViewToWindow, vTempViewVar_1, zMULTIPLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lSPLDLST "lSPLDLST"
   SetNameForView( lSPLDLST, "lSPLDLST", null, zLEVEL_TASK );

   //:// Temp code to correct name.
   //:SET CURSOR FIRST mSubProd.SubregLabelContent WHERE mSubProd.SubregLabelContent.Description = ""
   RESULT = SetCursorFirstEntityByString( mSubProd, "SubregLabelContent", "Description", "", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mSubProd, "SubregLabelContent", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_2, zSINGLE );
      DropView( vTempViewVar_2 );
      //:NAME VIEW mSubLC "mSubLCName"
      SetNameForView( mSubLC, "mSubLCName", null, zLEVEL_TASK );
      //:mSubLC.SubregLabelContent.Description = "TempName"
      SetAttributeFromString( mSubLC, "SubregLabelContent", "Description", "TempName" );
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END 
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_NewSubregProductSLC( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
GOTO_NewSubregProductSLC( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lMLC     BASED ON LOD  lMLC
   zVIEW    lMLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Activate lMLC for selecting a MLC for creation of a new SLC.
   //:GET VIEW lMLC NAMED "lMLC"
   RESULT = GetViewByName( lMLC, "lMLC", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( lMLC )
      DropObjectInstance( lMLC );
   } 

   //:END
   //:ACTIVATE lMLC Multiple WHERE lMLC.MasterProduct.ID = mSubreg.MasterProduct.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "MasterProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lMLC, "lMLC", ViewToWindow, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lMLC "lMLC"
   SetNameForView( lMLC, "lMLC", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_NewSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
GOTO_NewSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:IF mSubreg.PrimaryRegistrant EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "PrimaryRegistrant" );
   if ( lTempInteger_0 == 0 )
   { 
      //:IF mSubreg.ListMasterProduct EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mSubreg, "ListMasterProduct" );
      if ( lTempInteger_1 == 0 )
      { 
         //:RETURN 0
         if(8==8)return( 0 );
         //:ELSE
      } 
      else
      { 
         //:MessageSend( ViewToWindow, "", "New Subregistrant Product",
         //:             "Primary Registrant must have at least one\nMaster Product to create a Subregistrant Product.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Subregistrant Product", "Primary Registrant must have at least one\\nMaster Product to create a Subregistrant Product.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// This should not be possible.
      //:MessageSend( ViewToWindow, "", "New Subregistrant Product",
      //:             "Subregistrant must be associated with a Primary Registrant\nto create a Subregistrant Product.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant Product", "Subregistrant must be associated with a Primary Registrant\\nto create a Subregistrant Product.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:PostbuildSLC_Update( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
PostbuildSLC_Update( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Remove any S_Usage statements not tied to M_Usage statements.
   //:FOR EACH mSubLC.S_Usage 
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.M_Usage DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "M_Usage" );
      if ( lTempInteger_0 != 0 )
      { 
         //:DELETE ENTITY mSubLC.S_Usage NONE 
         RESULT = DeleteEntity( mSubLC, "S_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
      //:END
   } 

   //:END

   //:// Go to select any mMLC entries that are already in the mSLC.
   //:SetMLC_SelectedFlags( mMasLC, mSubLC )
   {
    mMasLC_Object m_mMasLC_Object = new mMasLC_Object( mMasLC );
    m_mMasLC_Object.omMasLC_SetMLC_SelectedFlags( mMasLC, mSubLC );
    // m_mMasLC_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateSubregProductSPLD( VIEW ViewToWindow )

//:   VIEW lSPLDLST REGISTERED AS lSPLDLST
public int 
GOTO_UpdateSubregProductSPLD( View     ViewToWindow )
{
   zVIEW    lSPLDLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_5 = 0;

   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );

   //:// Activate the mSPLDef object selected in mSubProd.
   //:ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = lSPLDLST.SubregPhysicalLabelDef.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSPLDLST, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );

   //:// Activate the related mSubLC object.
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSPLDef.SubregLabelContent.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSPLDef, "SubregLabelContent", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );

   //:// Rebuild DirectionsForUse and Marketing data.
   //:// If the DirectionsForUse or Marketing Statements are not tied to their SLC counterparts, then rebuild them.
   //:// (This is a correction to a data error.)
   //:IF mSPLDef.S_DirectionsForUseStatement DOES NOT EXIST OR mSPLDef.S_DirectionsForUseSection DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( mSPLDef, "S_DirectionsForUseStatement" );
   lTempInteger_3 = CheckExistenceOfEntity( mSPLDef, "S_DirectionsForUseSection" );
   if ( lTempInteger_2 != 0 || lTempInteger_3 != 0 )
   { 
      //:// The data is in error, so go to correct it.
      //:RebuildSPLD_FromSLC( mSPLDef, mSubLC )
      {
       mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
       m_mSPLDef_Object.omSPLDef_RebuildSPLD_FromSLC( mSPLDef, mSubLC );
       // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:BuildUsageEntriesFrSLC( mSPLDef, mSubLC )
      {
       mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
       m_mSPLDef_Object.omSPLDef_BuildUsageEntriesFrSLC( mSPLDef, mSubLC );
       // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:// Save and reactivate mSPLDef to make the object easier to see in the object browser.
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   //:ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = lSPLDLST.SubregPhysicalLabelDef.ID 
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, lSPLDLST, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_2, lTempInteger_4 );
   RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );

   //:// Sort Panels and SubBlocks by position.
   //:ComputeTopPositions( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_ComputeTopPositions( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:FOR EACH mSPLDef.LLD_Page 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Page", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mSPLDef, "LLD_Panel", "Top A Left A" )
      OrderEntityForView( mSPLDef, "LLD_Panel", "Top A Left A" );
      //:FOR EACH mSPLDef.LLD_Panel 
      RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FOR EACH mSPLDef.LLD_Block 
         RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Block", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:OrderEntityForView( mSPLDef, "LLD_SubBlock", "LLD_SubBlock.Left A wComputedTopPosition A" )
            OrderEntityForView( mSPLDef, "LLD_SubBlock", "LLD_SubBlock.Left A wComputedTopPosition A" );
            RESULT = SetCursorNextEntity( mSPLDef, "LLD_Block", "" );
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "LLD_Panel", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "LLD_Page", "" );
      //:END 
   } 

   //:END 
   //:SET CURSOR FIRST mSPLDef.LLD_Page 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Page", "" );

   //:// Initialize LLD data, if it's not already initialized.
   //:IF mSPLDef.SPLD_LLD DOES NOT EXIST
   lTempInteger_5 = CheckExistenceOfEntity( mSPLDef, "SPLD_LLD" );
   if ( lTempInteger_5 != 0 )
   { 
      //:CREATE ENTITY mSPLDef.SPLD_LLD 
      RESULT = CreateEntity( mSPLDef, "SPLD_LLD", zPOS_AFTER );
      //:CREATE ENTITY mSPLDef.LLD_Page 
      RESULT = CreateEntity( mSPLDef, "LLD_Page", zPOS_AFTER );
      //:CREATE ENTITY mSPLDef.LLD_Panel 
      RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
      //:mSPLDef.LLD_Panel.Left = 0
      SetAttributeFromInteger( mSPLDef, "LLD_Panel", "Left", 0 );
      //:mSPLDef.LLD_Panel.Top = 0 
      SetAttributeFromInteger( mSPLDef, "LLD_Panel", "Top", 0 );
   } 

   //:END

   //:// Build the work components
   //:BuildCompositeEntries( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildCompositeEntries( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:TraceLineS( "$$$$$$ end of oper", "" )
   TraceLineS( "$$$$$$ end of oper", "" );
   return( 0 );
//    
//    // Also activate the corresponding SLC and build the composite subobject.
//    //ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSPLDef.SubregLabelContent.ID 
//    //NAME VIEW mSubLC "mSubLC"
//    //BuildCompositeEntries( mSubLC )
// END
} 


//:DIALOG OPERATION
//:DeleteSubregProductSPLD( VIEW ViewToWindow )

//:   VIEW lSPLDLST REGISTERED AS lSPLDLST
public int 
DeleteSubregProductSPLD( View     ViewToWindow )
{
   zVIEW    lSPLDLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef 
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );

   //:// Delete selected mSPLDef.
   //:ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = lSPLDLST.SubregPhysicalLabelDef.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSPLDLST, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSPLDef "mSPLDefDelete"
   SetNameForView( mSPLDef, "mSPLDefDelete", null, zLEVEL_TASK );
   //:DELETE ENTITY mSPLDef.SubregPhysicalLabelDef 
   RESULT = DeleteEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   //:DropEntity( lSPLDLST, "SubregPhysicalLabelDef", zREPOS_NONE )
   DropEntity( lSPLDLST, "SubregPhysicalLabelDef", zREPOS_NONE );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SELECT_SLC_ForNewSPLD( VIEW ViewToWindow )

//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
SELECT_SLC_ForNewSPLD( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Make sure that an SLC has been selected.
   //:SET CURSOR FIRST mSubProd.SubregLabelContent WHERE mSubProd.SubregLabelContent.wSelected = "Y"
   RESULT = SetCursorFirstEntityByString( mSubProd, "SubregLabelContent", "wSelected", "Y", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "New SPLD",
      //:             "An SLC must be selected.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New SPLD", "An SLC must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Create a new mSPLDef object instance and ititialize it with data from the mSubLC data from the
   //:// selected SubregLabelContent entry in mSubProd.
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );

   //:ACTIVATE mSPLDef EMPTY
   RESULT = ActivateEmptyObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, zSINGLE );
   //:CREATE ENTITY mSPLDef.SubregPhysicalLabelDef
   RESULT = CreateEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_AFTER );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   //:INCLUDE mSPLDef.SubregLabelContent FROM mSubProd.SubregLabelContent
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SubregLabelContent", mSubProd, "SubregLabelContent", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_LLD
   RESULT = CreateEntity( mSPLDef, "SPLD_LLD", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.LLD_Page
   RESULT = CreateEntity( mSPLDef, "LLD_Page", zPOS_AFTER );
   //:mSPLDef.LLD_Page.Height = 14.0
   SetAttributeFromDecimal( mSPLDef, "LLD_Page", "Height", (double) 14.0 );
   //:mSPLDef.LLD_Page.Width = 19.5         
   SetAttributeFromDecimal( mSPLDef, "LLD_Page", "Width", (double) 19.5 );

   //:CREATE ENTITY mSPLDef.LLD_Panel 
   RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
   //:mSPLDef.LLD_Panel.Top = 1.0         
   SetAttributeFromDecimal( mSPLDef, "LLD_Panel", "Top", (double) 1.0 );
   //:mSPLDef.LLD_Panel.Left = 1.0
   SetAttributeFromDecimal( mSPLDef, "LLD_Panel", "Left", (double) 1.0 );
   //:mSPLDef.LLD_Panel.Height = 7.0
   SetAttributeFromDecimal( mSPLDef, "LLD_Panel", "Height", (double) 7.0 );
   //:mSPLDef.LLD_Panel.Width = 9.0
   SetAttributeFromDecimal( mSPLDef, "LLD_Panel", "Width", (double) 9.0 );

   //:// Build the Components of the SPLD from the SLC.
   //:BuildSPLD_FromSLC( mSPLDef, mSubLC )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_FromSLC( mSPLDef, mSubLC );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ACCEPT_SPLD_Panel( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ACCEPT_SPLD_Panel( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef2 BASED ON LOD  mSPLDef
   zVIEW    mSPLDef2 = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Make sure that any Blocks and SubBlocks are in Y position order, but first calculate order, since
   //:// some blocks have Top position specified and others will have it computed.
   //:CreateViewFromView( mSPLDef2, mSPLDef )
   CreateViewFromView( mSPLDef2, mSPLDef );
   //:ComputeTopPositions( mSPLDef2 )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef2 );
    m_mSPLDef_Object.omSPLDef_ComputeTopPositions( mSPLDef2 );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:DropView( mSPLDef2 )
   DropView( mSPLDef2 );
   //:OrderEntityForView( mSPLDef, "LLD_Block", "wComputedTopPosition A" )
   OrderEntityForView( mSPLDef, "LLD_Block", "wComputedTopPosition A" );
   //:FOR EACH mSPLDef.LLD_Block 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Block", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.LLD_SubBlock EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "LLD_SubBlock" );
      if ( lTempInteger_0 == 0 )
      { 
         //:OrderEntityForView( mSPLDef, "LLD_SubBlock", "wComputedTopPosition A" )
         OrderEntityForView( mSPLDef, "LLD_SubBlock", "wComputedTopPosition A" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "LLD_Block", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DELETE_BlockComponentEntry( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
DELETE_BlockComponentEntry( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Simply delete the Component.
   //:// IF mSPLDef.LLD_Block.Type = "Block" 
   //:   SET CURSOR FIRST mSPLDef.LLD_Block WHERE mSPLDef.LLD_Block.wRelativeBlockNumber = mSPLDef.LLD_Block.wRelativeBlockNumber  
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "LLD_Block", "wRelativeBlockNumber" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "LLD_Block", "wRelativeBlockNumber", lTempInteger_0, "" );
   //:   DELETE ENTITY mSPLDef.LLD_Block 
   RESULT = DeleteEntity( mSPLDef, "LLD_Block", zPOS_NEXT );
   //:// ELSE
   //://    SET CURSOR FIRST mSPLDef.LLD_SubBlock WITHIN mSPLDef.LLD_Panel 
   //://               WHERE mSPLDef.LLD_SubBlock.wRelativeBlockNumber = mSPLDef.LLD_Block.wRelativeBlockNumber  
   //://    DELETE ENTITY mSPLDef.LLD_SubBlock 
   //:// END
   //:DELETE ENTITY mSPLDef.LLD_Block  
   RESULT = DeleteEntity( mSPLDef, "LLD_Block", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GENERATE_SPLD_Label( VIEW ViewToWindow )

//:   VIEW mSPLDefPanel  BASED ON LOD  mSPLDef
public int 
GENERATE_SPLD_Label( View     ViewToWindow )
{
   zVIEW    mSPLDefPanel = new zVIEW( );
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szLPLR_Name
   String   szLPLR_Name = null;
   //:STRING ( 64 ) szSystemIniApplName
   String   szSystemIniApplName = null;
   //:STRING ( 64 ) szLabelName
   String   szLabelName = null;
   //:STRING ( 256 ) szDirectory
   String   szDirectory = null;
   //:STRING ( 256 ) szApplication
   String   szApplication = null;
   //:STRING ( 256 ) szXmlName
   String   szXmlName = null;
   //:STRING ( 256 ) szXslName
   String   szXslName = null;
   //:SHORT         nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will use mSPLDefPanel, if it exists, as it will always be pointing to the top of the LLD_Block
   //:// subobject, while mSPLDef sometimes is pointing to a recursive subobject.
   //:GET VIEW mSPLDefPanel NAMED "mSPLDefPanel"
   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:GET VIEW mSPLDefPanel NAMED "mSPLDef"
      RESULT = GetViewByName( mSPLDefPanel, "mSPLDef", ViewToWindow, zLEVEL_TASK );
      //:CreateViewFromView( mSPLDefPanel, mSPLDefPanel )
      CreateViewFromView( mSPLDefPanel, mSPLDefPanel );
      //:ResetViewFromSubobjectTop( mSPLDefPanel )
      ResetViewFromSubobjectTop( mSPLDefPanel );
      //:NAME VIEW mSPLDefPanel "mSPLDefPanel"
      SetNameForView( mSPLDefPanel, "mSPLDefPanel", null, zLEVEL_TASK );
   } 

   //:END

   //:// Generate the label using LLD and SLC data defined in mSPLDef. 
   //:mSPLDefPanel.SubregPhysicalLabelDef.wFormatWithDottedBorders = ""
   SetAttributeFromString( mSPLDefPanel, "SubregPhysicalLabelDef", "wFormatWithDottedBorders", "" );
   //:nRC = GeneratePDF_Label( mSPLDefPanel )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDefPanel );
    nRC = m_mSPLDef_Object.omSPLDef_GeneratePDF_Label( mSPLDefPanel );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 2
   if ( nRC == 2 )
   { 
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Open the File
   //:// SfGetApplicationForSubtask( szLPLR_Name, mSPLDef )
   //:// szSystemIniApplName = "[App." + szLPLR_Name + "]"
   //:// SysReadZeidonIni( -1, szSystemIniApplName, "WebDirectory", szDirectory )
   //:// SysConvertEnvironmentString( szDirectory, szDirectory )
   //:// szDirectory = "C:/Program Files/Apache Group/tomcat 7.0/webapps/ROOT/epamms/"

   //:SysGetEnvVar( szDirectory, "CATALINA_HOME", 256 )
   {StringBuilder sb_szDirectory;
   if ( szDirectory == null )
      sb_szDirectory = new StringBuilder( 32 );
   else
      sb_szDirectory = new StringBuilder( szDirectory );
       m_KZOEP1AA.SysGetEnvVar( sb_szDirectory, "CATALINA_HOME", 256 );
   szDirectory = sb_szDirectory.toString( );}
   //:// SysAppendcDirSep( szDirectory )
   //:SysConvertEnvironmentString( szDirectory, szDirectory )
   {StringBuilder sb_szDirectory;
   if ( szDirectory == null )
      sb_szDirectory = new StringBuilder( 32 );
   else
      sb_szDirectory = new StringBuilder( szDirectory );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectory, szDirectory );
   szDirectory = sb_szDirectory.toString( );}
   //:szDirectory = szDirectory + "/webapps/ROOT"
    {StringBuilder sb_szDirectory;
   if ( szDirectory == null )
      sb_szDirectory = new StringBuilder( 32 );
   else
      sb_szDirectory = new StringBuilder( szDirectory );
      ZeidonStringConcat( sb_szDirectory, 1, 0, "/webapps/ROOT", 1, 0, 257 );
   szDirectory = sb_szDirectory.toString( );}
   //:szApplication = "/" + mSPLDefPanel.SubregOrganization.LoginName + "/"
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 129, mSPLDefPanel, "SubregOrganization", "LoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szApplication;
   if ( szApplication == null )
      sb_szApplication = new StringBuilder( 32 );
   else
      sb_szApplication = new StringBuilder( szApplication );
      ZeidonStringCopy( sb_szApplication, 1, 0, "/", 1, 0, 257 );
   szApplication = sb_szApplication.toString( );}
    {StringBuilder sb_szApplication;
   if ( szApplication == null )
      sb_szApplication = new StringBuilder( 32 );
   else
      sb_szApplication = new StringBuilder( szApplication );
      ZeidonStringConcat( sb_szApplication, 1, 0, szTempString_0, 1, 0, 257 );
   szApplication = sb_szApplication.toString( );}
    {StringBuilder sb_szApplication;
   if ( szApplication == null )
      sb_szApplication = new StringBuilder( 32 );
   else
      sb_szApplication = new StringBuilder( szApplication );
      ZeidonStringConcat( sb_szApplication, 1, 0, "/", 1, 0, 257 );
   szApplication = sb_szApplication.toString( );}

   //:szLabelName = mSPLDefPanel.SubregProduct.Name
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szLabelName;
   if ( szLabelName == null )
      sb_szLabelName = new StringBuilder( 32 );
   else
      sb_szLabelName = new StringBuilder( szLabelName );
       GetVariableFromAttribute( sb_szLabelName, mi_lTempInteger_1, 'S', 65, mSPLDefPanel, "SubregProduct", "Name", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szLabelName = sb_szLabelName.toString( );}

   //:szXmlName = szDirectory + szApplication + "xml/"
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringCopy( sb_szXmlName, 1, 0, szDirectory, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, szApplication, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, "xml/", 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
   //:szXslName = szDirectory + szApplication + "xsl/"
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringCopy( sb_szXslName, 1, 0, szDirectory, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, szApplication, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, "xsl/", 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
   //:SysValidDirOrFile( szXmlName, 1, 1, 256 )
   SysValidDirOrFile( szXmlName, 1, 1, 256 );
   //:SysValidDirOrFile( szXslName, 1, 1, 256 )
   SysValidDirOrFile( szXslName, 1, 1, 256 );
   //:szXmlName = szXmlName + szLabelName + ".xml"
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, szLabelName, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, ".xml", 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
   //:szXslName = szXslName + szLabelName + ".xsl"
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, szLabelName, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, ".xsl", 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
   //:TraceLineS( "Output Xsl Filename: ", szXslName )
   TraceLineS( "Output Xsl Filename: ", szXslName );

   //:// ConvertXMLToPDF( "c:\lplr\epamms\xsl\TestLabel.xml", "c:\lplr\epamms\xsl\TestLabel.xsl", "TestLabel" )
   //:// ConvertXMLToPDF( szXmlName, szXslName, szLabelName )
   //:ConvertXML_ToPDF( szDirectory, szApplication, szLabelName )
   m_ZDRVROPR.ConvertXML_ToPDF( szDirectory, szApplication, szLabelName );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateBlockComponent( VIEW ViewToWindow )

//:   VIEW mSPLDef      REGISTERED AS mSPLDef
public int 
GOTO_UpdateBlockComponent( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefPanel BASED ON LOD  mSPLDef
   zVIEW    mSPLDefPanel = new zVIEW( );
   //:VIEW mSPLDefBlock BASED ON LOD  mSPLDef
   zVIEW    mSPLDefBlock = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Create view, mSPLDefPanel, which will always be pointing at the Panel level, and will not
   //:// step down to a lower level subobject of LLD_Block.
   //:GET VIEW mSPLDefPanel NAMED "mSPLDefPanel"
   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSPLDefPanel )
      DropObjectInstance( mSPLDefPanel );
   } 

   //:END
   //:CreateViewFromView( mSPLDefPanel, mSPLDef )
   CreateViewFromView( mSPLDefPanel, mSPLDef );
   //:NAME VIEW mSPLDefPanel "mSPLDefPanel"
   SetNameForView( mSPLDefPanel, "mSPLDefPanel", null, zLEVEL_TASK );

   //:// Also create named view mSPLDefBlock.
   //:GET VIEW mSPLDefBlock NAMED "mSPLDefBlock"
   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSPLDefBlock )
      DropObjectInstance( mSPLDefBlock );
   } 

   //:END
   //:CreateViewFromView( mSPLDefBlock, mSPLDef )
   CreateViewFromView( mSPLDefBlock, mSPLDef );
   //:NAME VIEW mSPLDefBlock "mSPLDefBlock"
   SetNameForView( mSPLDefBlock, "mSPLDefBlock", null, zLEVEL_TASK );
   return( 0 );
// /*
//    // If the Block/SubBlock is Marketing, redirect to Marketing update window.
//    // Create version first.
// // IF mSPLDef.LLD_Block.Type = "Block" 
//       SET CURSOR FIRST mSPLDef.LLD_Block WHERE mSPLDef.LLD_Block.wRelativeBlockNumber = mSPLDef.LLD_Block.wRelativeBlockNumber  
//       //CreateTemporalSubobjectVersion( mSPLDef, "LLD_Block" )
//       CreateViewFromView( mSPLDefPanel, mSPLDef )
//       NAME VIEW mSPLDefPanel "mSPLDefPanel"
//  //ELSE
//  //   SET CURSOR FIRST mSPLDef.LLD_SubBlock WITHIN mSPLDef.LLD_Panel 
//  //              WHERE mSPLDef.LLD_SubBlock.wRelativeBlockNumber = mSPLDef.LLD_Block.wRelativeBlockNumber
//  //   CreateViewFromView( mSPLDefPanel, mSPLDef )
//  //   NAME VIEW mSPLDefPanel "mSPLDefPanel"
//  //   //CreateTemporalSubobjectVersion( mSPLDef, "LLD_SubBlock" )
//  //   SetViewToSubobject( mSPLDef, "LLD_SubBlock" )
//       //GET VIEW KZXMLPGO NAMED "KZXMLPGO"
//       //CREATE ENTITY KZXMLPGO.PagePath
//       //KZXMLPGO.PagePath.LastPageName = "wSPLDSPLD_PanelUpdate"
//       //SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "SPLD_BlockDefinitionUpdateSub" )
// // END
// */
// END
} 


//:DIALOG OPERATION
//:SELECT_MarketingSectionForBlock( VIEW ViewToWindow )

//:   VIEW mSPLDefPanel REGISTERED AS mSPLDefPanel
public int 
SELECT_MarketingSectionForBlock( View     ViewToWindow )
{
   zVIEW    mSPLDefPanel = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefBlock REGISTERED AS mSPLDefBlock
   zVIEW    mSPLDefBlock = new zVIEW( );
   //:STRING ( 256 ) szName
   String   szName = null;
   //:INTEGER lCount
   int      lCount = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );

   //:lCount = 0
   lCount = 0;
   //:// User selected Marketing Section to set Block Name in component.
   //:SET CURSOR FIRST mSPLDefPanel.SPLD_MarketingSection WHERE mSPLDefPanel.SPLD_MarketingSection.wSelected = "Y"
   RESULT = SetCursorFirstEntityByString( mSPLDefPanel, "SPLD_MarketingSection", "wSelected", "Y", "" );
   //:IF RESULT = zCURSOR_SET
   if ( RESULT == zCURSOR_SET )
   { 
      //:lCount = 1
      lCount = 1;
      //:szName = mSPLDefPanel.SPLD_MarketingSection.Name 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
             GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mSPLDefPanel, "SPLD_MarketingSection", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szName = sb_szName.toString( );}
      //:SET CURSOR NEXT mSPLDefPanel.SPLD_MarketingSection WHERE mSPLDefPanel.SPLD_MarketingSection.wSelected = "Y"
      RESULT = SetCursorNextEntityByString( mSPLDefPanel, "SPLD_MarketingSection", "wSelected", "Y", "" );
      //:IF RESULT = zCURSOR_SET
      if ( RESULT == zCURSOR_SET )
      { 
         //:lCount = 2
         lCount = 2;
      } 

      //:END
   } 

   //:END
   //:IF lCount != 1
   if ( lCount != 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Select Marketing Section",
      //:             "One Section must be selected.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Marketing Section", "One Section must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:mSPLDefBlock.LLD_Block.Name = szName 
   SetAttributeFromString( mSPLDefBlock, "LLD_Block", "Name", szName );

   //:// Remove any existing entries, including the one just processed.
   //:FOR EACH mSPLDefPanel.SPLD_MarketingSection
   RESULT = SetCursorFirstEntity( mSPLDefPanel, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:mSPLDefPanel.SPLD_MarketingSection.wSelected = ""
      SetAttributeFromString( mSPLDefPanel, "SPLD_MarketingSection", "wSelected", "" );
      RESULT = SetCursorNextEntity( mSPLDefPanel, "SPLD_MarketingSection", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:BACKUP_Objects( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
BACKUP_Objects( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubProd REGISTERED AS mSubProd 
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 100 )  szFileName
   String   szFileName = null;
   //:STRING ( 100 )  szNamedView
   String   szNamedView = null;
   //:STRING ( 2 )    szCount
   String   szCount = null;
   //:INTEGER         Count
   int      Count = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:// Commit each object to a file.
   //:CommitOI_ToFile( mSubreg, "c:\lplr\epamms\objectback\mSubreg.por", zASCII )
   CommitOI_ToFile( mSubreg, "c:\\lplr\\epamms\\objectback\\mSubreg.por", zASCII );
   //:CommitOI_ToFile( mSubProd, "c:\lplr\epamms\objectback\mSubProd.por", zASCII )
   CommitOI_ToFile( mSubProd, "c:\\lplr\\epamms\\objectback\\mSubProd.por", zASCII );
   //:Count = 0
   Count = 0;
   //:FOR EACH mSubProd.SubregLabelContent 
   RESULT = SetCursorFirstEntity( mSubProd, "SubregLabelContent", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:szCount = Count
       {StringBuilder sb_szCount;
      if ( szCount == null )
         sb_szCount = new StringBuilder( 32 );
      else
         sb_szCount = new StringBuilder( szCount );
            ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 2, Count, (double) 0.0, "I" );
      szCount = sb_szCount.toString( );}
      //:szFileName = "c:\lplr\epamms\objectback\mSubLC" + szCount + ".por"
       {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
            ZeidonStringCopy( sb_szFileName, 1, 0, "c:\\lplr\\epamms\\objectback\\mSubLC", 1, 0, 101 );
      szFileName = sb_szFileName.toString( );}
       {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
            ZeidonStringConcat( sb_szFileName, 1, 0, szCount, 1, 0, 101 );
      szFileName = sb_szFileName.toString( );}
       {StringBuilder sb_szFileName;
      if ( szFileName == null )
         sb_szFileName = new StringBuilder( 32 );
      else
         sb_szFileName = new StringBuilder( szFileName );
            ZeidonStringConcat( sb_szFileName, 1, 0, ".por", 1, 0, 101 );
      szFileName = sb_szFileName.toString( );}
      //:szNamedView = "mSubLC" + szCount
       {StringBuilder sb_szNamedView;
      if ( szNamedView == null )
         sb_szNamedView = new StringBuilder( 32 );
      else
         sb_szNamedView = new StringBuilder( szNamedView );
            ZeidonStringCopy( sb_szNamedView, 1, 0, "mSubLC", 1, 0, 101 );
      szNamedView = sb_szNamedView.toString( );}
       {StringBuilder sb_szNamedView;
      if ( szNamedView == null )
         sb_szNamedView = new StringBuilder( 32 );
      else
         sb_szNamedView = new StringBuilder( szNamedView );
            ZeidonStringConcat( sb_szNamedView, 1, 0, szCount, 1, 0, 101 );
      szNamedView = sb_szNamedView.toString( );}
      //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubProd.SubregLabelContent.ID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregLabelContent", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      o_fnLocalBuildQual_17( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSubLC szNamedView
      SetNameForView( mSubLC, szNamedView, null, zLEVEL_TASK );
      //:CommitOI_ToFile( mSubLC, szFileName, zASCII )
      CommitOI_ToFile( mSubLC, szFileName, zASCII );
      RESULT = SetCursorNextEntity( mSubProd, "SubregLabelContent", "" );
   } 

   //:END
   //:IF mSubProd.MasterLabelContent EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSubProd, "MasterLabelContent" );
   if ( lTempInteger_1 == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mSubProd.MasterLabelContent.ID 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mSubProd, "MasterLabelContent", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      o_fnLocalBuildQual_18( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:CommitOI_ToFile( mSubProd, "c:\lplr\epamms\objectback\mMasLC.por", zASCII )
      CommitOI_ToFile( mSubProd, "c:\\lplr\\epamms\\objectback\\mMasLC.por", zASCII );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ExecuteJOE_Test1( VIEW ViewToWindow )

//:   VIEW mSPLDef  BASED ON LOD mSPLDef
public int 
ExecuteJOE_Test1( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSPLDef2 BASED ON LOD mSPLDef
   zVIEW    mSPLDef2 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Execute Tests to Check for JOE Bugs.

   //:// TEST 1
   //:// Recursive Subobject Test.
   //:// The current error is that the basic SetViewToSubobject function did not change the view to the subobject.

   //:// Create subobject with one level of recursive subobject.
   //:ACTIVATE mSPLDef EMPTY 
   RESULT = ActivateEmptyObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, zSINGLE );
   //:NAME VIEW mSPLDef "mSPLDef" 
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   //:CREATE ENTITY mSPLDef.SubregPhysicalLabelDef 
   RESULT = CreateEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_LLD 
   RESULT = CreateEntity( mSPLDef, "SPLD_LLD", zPOS_AFTER );
   //:mSPLDef.SPLD_LLD.Name = "Test"
   SetAttributeFromString( mSPLDef, "SPLD_LLD", "Name", "Test" );
   //:CREATE ENTITY mSPLDef.LLD_Page 
   RESULT = CreateEntity( mSPLDef, "LLD_Page", zPOS_AFTER );
   //:mSPLDef.LLD_Page.Width = 10
   SetAttributeFromInteger( mSPLDef, "LLD_Page", "Width", 10 );
   //:CREATE ENTITY mSPLDef.LLD_Panel 
   RESULT = CreateEntity( mSPLDef, "LLD_Panel", zPOS_AFTER );
   //:mSPLDef.LLD_Panel.Width = 11
   SetAttributeFromInteger( mSPLDef, "LLD_Panel", "Width", 11 );

   //:CREATE ENTITY mSPLDef.LLD_Block 
   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
   //:mSPLDef.LLD_Block.Name = "Block Level 1"
   SetAttributeFromString( mSPLDef, "LLD_Block", "Name", "Block Level 1" );
   //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttribute 
   RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER );
   //:mSPLDef.LLD_SpecialSectionAttribute.Name = "Spec Attribute 1"
   SetAttributeFromString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 1" );

   //:SetViewToSubobject( mSPLDef, "LLD_SubBlock" ) 
   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );

   //:IF mSPLDef.LLD_Block EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "LLD_Block" );
   if ( lTempInteger_0 == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 1",
      //:             "SetViewToSubobject didn't work.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 1", "SetViewToSubobject didn't work.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:CREATE ENTITY mSPLDef.LLD_Block 
   RESULT = CreateEntity( mSPLDef, "LLD_Block", zPOS_AFTER );
   //:mSPLDef.LLD_Block.Name = "Block Level 2"
   SetAttributeFromString( mSPLDef, "LLD_Block", "Name", "Block Level 2" );
   //:CREATE ENTITY mSPLDef.LLD_SpecialSectionAttribute 
   RESULT = CreateEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_AFTER );
   //:mSPLDef.LLD_SpecialSectionAttribute.Name = "Spec Attribute 2" 
   SetAttributeFromString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" );

   //:ResetViewFromSubobject( mSPLDef )
   ResetViewFromSubobject( mSPLDef );

   //:// See if we can now read what we created.
   //:IF mSPLDef.LLD_Block.Name != "Block Level 1"
   if ( CompareAttributeToString( mSPLDef, "LLD_Block", "Name", "Block Level 1" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 1",
      //:             "No match on Block Level 1",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 1", "No match on Block Level 1", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:IF mSPLDef.LLD_SpecialSectionAttribute.Name != "Spec Attribute 1"
   if ( CompareAttributeToString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 1" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 1",
      //:             "No match on Spec Attribute 1",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 1", "No match on Spec Attribute 1", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:SetViewToSubobject( mSPLDef, "LLD_SubBlock" )
   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );
   //:IF mSPLDef.LLD_Block.Name != "Block Level 2"
   if ( CompareAttributeToString( mSPLDef, "LLD_Block", "Name", "Block Level 2" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 2",
      //:             "No match on Block Level 1",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 2", "No match on Block Level 1", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:IF mSPLDef.LLD_SpecialSectionAttribute.Name != "Spec Attribute 2"
   if ( CompareAttributeToString( mSPLDef, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 1",
      //:             "No match on Spec Attribute 2",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 1", "No match on Spec Attribute 2", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:ResetViewFromSubobject( mSPLDef )
   ResetViewFromSubobject( mSPLDef );

   //:// Now try the subobject from a different view.
   //:CreateViewFromView( mSPLDef2, mSPLDef )
   CreateViewFromView( mSPLDef2, mSPLDef );

   //:SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
   SetViewToSubobject( mSPLDef2, "LLD_SubBlock" );
   //:IF mSPLDef2.LLD_Block.Name != "Block Level 2"
   if ( CompareAttributeToString( mSPLDef2, "LLD_Block", "Name", "Block Level 2" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 2",
      //:             "No match on Block Level 1",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 2", "No match on Block Level 1", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:IF mSPLDef2.LLD_SpecialSectionAttribute.Name != "Spec Attribute 2"
   if ( CompareAttributeToString( mSPLDef2, "LLD_SpecialSectionAttribute", "Name", "Spec Attribute 2" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "JOE Test 1",
      //:             "No match on Spec Attribute 2",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "JOE Test 1", "No match on Spec Attribute 2", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:ResetViewFromSubobject( mSPLDef2 )
   ResetViewFromSubobject( mSPLDef2 );

   //:DropView( mSPLDef2 )
   DropView( mSPLDef2 );

   //:// Now try creating a new view when the first view is positioned on the subobject.
   //:SetViewToSubobject( mSPLDef, "LLD_SubBlock" )
   SetViewToSubobject( mSPLDef, "LLD_SubBlock" );
   //:CreateViewFromView( mSPLDef2, mSPLDef )
   CreateViewFromView( mSPLDef2, mSPLDef );
   //:DropView( mSPLDef2 )
   DropView( mSPLDef2 );
   //:ResetViewFromSubobject( mSPLDef )
   ResetViewFromSubobject( mSPLDef );

   //:TraceLineS( "*** JOE Test 1 successfully completed", "" )
   TraceLineS( "*** JOE Test 1 successfully completed", "" );
   return( 0 );
//    
//    // Recursive code that didn't quite work.
//    /*FOR EACH mSPLDef.LLD_SubBlock 
//       IF mSPLDef.LLD_SubBlock.Type = "Block"
//          CREATE ENTITY mSPLDef.LLD_Block 
//          SetMatchingAttributesByName( mSPLDef, "LLD_Block", mSPLDef, "LLD_SubBlock", zSET_NULL )
//       ELSE
//          IF mSPLDef.LLD_SubBlock.Type = "SubBlock"
//             CREATE ENTITY mSPLDef.LLD_SubBlock 
//             CreateViewFromView( mSPLDef2, mSPLDef )
//             SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
//             SetMatchingAttributesByName( mSPLDef2, "LLD_Block", mSPLDef, "LLD_SubBlock", zSET_NULL )
//             ResetView( mSPLDef2 )
//             DropView( mSPLDef2 )
//          ELSE
//             TraceLineS( "#### No Match on Block Type", "" )
//          END
//       END
//    END*/
// END
} 


//:DIALOG OPERATION
//:GENERATE_LLD_FromSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
GENERATE_LLD_FromSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mLLD     BASED ON LOD  mLLD
   zVIEW    mLLD = new zVIEW( );
   //:VIEW mLLD_LST BASED ON LOD  mLLD
   zVIEW    mLLD_LST = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Generate LLD from the current SPLD.

   //:// Make sure that the name is not blank.
   //:IF mSPLDef.SubregPhysicalLabelDef.wSavedLLD_Name = ""
   if ( CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "wSavedLLD_Name", "" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Generate LLD",
      //:             "The LLD Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Generate LLD", "The LLD Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:DropObjectInstance( mLLD )
      DropObjectInstance( mLLD );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Make sure the name entered is unique.
   //:ACTIVATE mLLD WHERE mLLD.LLD.Name = mSPLDef.SubregPhysicalLabelDef.wSavedLLD_Name 
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mSPLDef, "SubregPhysicalLabelDef", "wSavedLLD_Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mLLD, "mLLD", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Generate LLD",
      //:             "The LLD Name entered is not unique.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Generate LLD", "The LLD Name entered is not unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:DropObjectInstance( mLLD )
      DropObjectInstance( mLLD );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:DropObjectInstance( mLLD )
   DropObjectInstance( mLLD );

   //:// Copy the SPLD Structure.
   //:ACTIVATE mLLD EMPTY 
   RESULT = ActivateEmptyObjectInstance( mLLD, "mLLD", ViewToWindow, zSINGLE );
   //:NAME VIEW mLLD "mLLD" 
   SetNameForView( mLLD, "mLLD", null, zLEVEL_TASK );
   //:CREATE ENTITY mLLD.LLD 
   RESULT = CreateEntity( mLLD, "LLD", zPOS_AFTER );
   //:SetMatchingAttributesByName( mLLD, "LLD", mSPLDef, "SPLD_LLD", zSET_NULL )
   SetMatchingAttributesByName( mLLD, "LLD", mSPLDef, "SPLD_LLD", zSET_NULL );
   //:mLLD.LLD.Name = mSPLDef.SubregPhysicalLabelDef.wSavedLLD_Name
   SetAttributeFromAttribute( mLLD, "LLD", "Name", mSPLDef, "SubregPhysicalLabelDef", "wSavedLLD_Name" );
   //:FOR EACH mSPLDef.LLD_Page 
   RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Page", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mLLD.LLD_Page 
      RESULT = CreateEntity( mLLD, "LLD_Page", zPOS_AFTER );
      //:SetMatchingAttributesByName( mLLD, "LLD_Page", mSPLDef, "LLD_Page", zSET_NULL )
      SetMatchingAttributesByName( mLLD, "LLD_Page", mSPLDef, "LLD_Page", zSET_NULL );
      //:FOR EACH mSPLDef.LLD_Panel 
      RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Panel", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mLLD.LLD_Panel 
         RESULT = CreateEntity( mLLD, "LLD_Panel", zPOS_AFTER );
         //:SetMatchingAttributesByName( mLLD, "LLD_Panel", mSPLDef, "LLD_Panel", zSET_NULL )
         SetMatchingAttributesByName( mLLD, "LLD_Panel", mSPLDef, "LLD_Panel", zSET_NULL );
         //:FOR EACH mSPLDef.LLD_Block 
         RESULT = SetCursorFirstEntity( mSPLDef, "LLD_Block", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY mLLD.LLD_Block 
            RESULT = CreateEntity( mLLD, "LLD_Block", zPOS_AFTER );
            //:SetMatchingAttributesByName( mLLD, "LLD_Block", mSPLDef, "LLD_Block", zSET_NULL )
            SetMatchingAttributesByName( mLLD, "LLD_Block", mSPLDef, "LLD_Block", zSET_NULL );
            //:FOR EACH mSPLDef.LLD_SubBlock 
            RESULT = SetCursorFirstEntity( mSPLDef, "LLD_SubBlock", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY mLLD.LLD_SubBlock 
               RESULT = CreateEntity( mLLD, "LLD_SubBlock", zPOS_AFTER );
               //:SetMatchingAttributesByName( mLLD, "LLD_SubBlock", mSPLDef, "LLD_SubBlock", zSET_NULL )
               SetMatchingAttributesByName( mLLD, "LLD_SubBlock", mSPLDef, "LLD_SubBlock", zSET_NULL );
               RESULT = SetCursorNextEntity( mSPLDef, "LLD_SubBlock", "" );
            } 

            //:END
            //:FOR EACH mSPLDef.LLD_SpecialSectionAttribute 
            RESULT = SetCursorFirstEntity( mSPLDef, "LLD_SpecialSectionAttribute", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:CREATE ENTITY mLLD.LLD_SpecialSectionAttribute 
               RESULT = CreateEntity( mLLD, "LLD_SpecialSectionAttribute", zPOS_AFTER );
               //:SetMatchingAttributesByName( mLLD, "LLD_SpecialSectionAttribute", mSPLDef, "LLD_SpecialSectionAttribute", zSET_NULL )
               SetMatchingAttributesByName( mLLD, "LLD_SpecialSectionAttribute", mSPLDef, "LLD_SpecialSectionAttribute", zSET_NULL );
               //:FOR EACH mSPLDef.LLD_SpecialSectionAttrBlock
               RESULT = SetCursorFirstEntity( mSPLDef, "LLD_SpecialSectionAttrBlock", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:CREATE ENTITY mLLD.LLD_SpecialSectionAttrBlock 
                  RESULT = CreateEntity( mLLD, "LLD_SpecialSectionAttrBlock", zPOS_AFTER );
                  //:SetMatchingAttributesByName( mLLD, "LLD_SpecialSectionAttrBlock", mSPLDef, "LLD_SpecialSectionAttrBlock", zSET_NULL )
                  SetMatchingAttributesByName( mLLD, "LLD_SpecialSectionAttrBlock", mSPLDef, "LLD_SpecialSectionAttrBlock", zSET_NULL );
                  RESULT = SetCursorNextEntity( mSPLDef, "LLD_SpecialSectionAttrBlock", "" );
               } 

               RESULT = SetCursorNextEntity( mSPLDef, "LLD_SpecialSectionAttribute", "" );
               //:END
            } 

            RESULT = SetCursorNextEntity( mSPLDef, "LLD_Block", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( mSPLDef, "LLD_Panel", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "LLD_Page", "" );
      //:END
   } 

   //:END
   //:COMMIT mLLD
   RESULT = CommitObjectInstance( mLLD );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Generate LLD",
      //:             "An error occurred during writing  the LLD to the database. Please check with Systems Support.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Generate LLD", "An error occurred during writing  the LLD to the database. Please check with Systems Support.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:DropObjectInstance( mLLD )
      DropObjectInstance( mLLD );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:DropObjectInstance( mLLD )
   DropObjectInstance( mLLD );

   //:// Reactivate the list of all LLD's.
   //:GET VIEW mLLD_LST NAMED "mLLD_LST"
   RESULT = GetViewByName( mLLD_LST, "mLLD_LST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mLLD_LST )
      DropObjectInstance( mLLD_LST );
   } 

   //:END
   //:ACTIVATE mLLD_LST RootOnlyMultiple
   RESULT = ActivateObjectInstance( mLLD_LST, "mLLD", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW mLLD_LST "mLLD_LST" 
   SetNameForView( mLLD_LST, "mLLD_LST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:REFRESH_SLC_FromMLC( VIEW ViewToWindow )

//:   VIEW mSubLC  REGISTERED AS mSubLC
public int 
REFRESH_SLC_FromMLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC  REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:RefreshSLC_FromMLC( mSubLC, mMasLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_RefreshSLC_FromMLC( mSubLC, mMasLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   //:BuildCompositeEntries( mSubLC )
   {
    mSubLC_Object m_mSubLC_Object = new mSubLC_Object( mSubLC );
    m_mSubLC_Object.omSubLC_BuildCompositeEntries( mSubLC );
    // m_mSubLC_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:REFRESH_SPLD_FromSLC( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
REFRESH_SPLD_FromSLC( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC  BASED ON LOD  mSubLC 
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Activate the corresponding SLC and build the composite subobject.
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID =mSPLDef.SubregLabelContent.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_22( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   //:BuildSPLD_FromSLC( mSPLDef, mSubLC )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_FromSLC( mSPLDef, mSubLC );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:BuildCompositeEntries( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildCompositeEntries( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ACCEPT_SPLD_StatementUpdate( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
ACCEPT_SPLD_StatementUpdate( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:INTEGER       OriginalID
   int      OriginalID = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Unless this is Storage and Disposal, Directions for Use or Marketing Statement, cancel the subobject.
   //:// For those three Statements, accept changes.
   //:szEntityName = mSPLDef.CompositeComponentList.Type
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, mSPLDef, "CompositeComponentList", "Type", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:IF szEntityName = "SPLD_StorageDisposalStatement" OR 
   //:   szEntityName = "SPLD_DirectionsForUseStatement" OR
   //:   szEntityName = "SPLD_GeneralStatement" OR
   //:   szEntityName = "SPLD_MarketingStatement"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_StorageDisposalStatement", 1, 0, 33 ) == 0 || ZeidonStringCompare( szEntityName, 1, 0, "SPLD_DirectionsForUseStatement", 1, 0, 33 ) == 0 ||
        ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralStatement", 1, 0, 33 ) == 0 || ZeidonStringCompare( szEntityName, 1, 0, "SPLD_MarketingStatement", 1, 0, 33 ) == 0 )
   { 

      //:// It is an error if Continuation Text is specified if no ContinuationBreakFlag is specified.
      //:IF mSPLDef.CompositeComponentList.ContinuationLeadingText != "" AND mSPLDef.CompositeComponentList.ContinuationBreakFlag = ""
      if ( CompareAttributeToString( mSPLDef, "CompositeComponentList", "ContinuationLeadingText", "" ) != 0 && CompareAttributeToString( mSPLDef, "CompositeComponentList", "ContinuationBreakFlag", "" ) == 0 )
      { 
         //:MessageSend( ViewToWindow, "", "Accept Statement",
         //:             "If the statement has been split, a Continuation type must be set.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Accept Statement", "If the statement has been split, a Continuation type must be set.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:// Make sure that if a Partial Split is requested, both the leading and following text attributes have
      //:// non-null values.
      //:// If there is no Partial Continuation request, set those attributes to null.
      //:IF mSPLDef.CompositeComponentList.ContinuationBreakFlag = "M"
      if ( CompareAttributeToString( mSPLDef, "CompositeComponentList", "ContinuationBreakFlag", "M" ) == 0 )
      { 
         //:IF mSPLDef.CompositeComponentList.ContinuationLeadingText = ""
         if ( CompareAttributeToString( mSPLDef, "CompositeComponentList", "ContinuationLeadingText", "" ) == 0 )
         { 
            //:MessageSend( ViewToWindow, "", "Accept Statement",
            //:             "The Statement Text must be split for the partial continuation option.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Accept Statement", "The Statement Text must be split for the partial continuation option.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN 2
            if(8==8)return( 2 );
            //:ELSE
         } 
         else
         { 
            //:// Since this is a split of the Text, make sure that the two components still equal the whole.
            //:mSPLDef.CompositeComponentList.BreakupText = mSPLDef.CompositeComponentList.ContinuationLeadingText +
            //:                                       mSPLDef.CompositeComponentList.ContinuationText 
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mSPLDef, "CompositeComponentList", "ContinuationLeadingText" );
            szTempString_0 = sb_szTempString_0.toString( );}
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, mSPLDef, "CompositeComponentList", "ContinuationText" );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 32001 );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( mSPLDef, "CompositeComponentList", "BreakupText", szTempString_0 );
            //:IF mSPLDef.CompositeComponentList.BreakupText != mSPLDef.CompositeComponentList.Value 
            if ( CompareAttributeToAttribute( mSPLDef, "CompositeComponentList", "BreakupText", mSPLDef, "CompositeComponentList", "Value" ) != 0 )
            { 
               //:// Before giving an error message, reset the breakup text to the original value.
               //:mSPLDef.CompositeComponentList.BreakupText = mSPLDef.CompositeComponentList.Value
               SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "BreakupText", mSPLDef, "CompositeComponentList", "Value" );
               //:MessageSend( ViewToWindow, "", "Accept Statement",
               //:             "The two split components are not equal to the initial Statement Text.",
               //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( ViewToWindow, "", "Accept Statement", "The two split components are not equal to the initial Statement Text.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:RETURN 2
               if(8==8)return( 2 );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:mSPLDef.CompositeComponentList.ContinuationText       = ""
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "ContinuationText", "" );
         //:mSPLDef.CompositeComponentList.ContinuationLeadingText = ""
         SetAttributeFromString( mSPLDef, "CompositeComponentList", "ContinuationLeadingText", "" );
      } 

      //:END

      //:// Position on original Statement entry and set values.
      //:OriginalID = mSPLDef.CompositeComponentList.OriginalTypeID  
      {MutableInt mi_OriginalID = new MutableInt( OriginalID );
             GetIntegerFromAttribute( mi_OriginalID, mSPLDef, "CompositeComponentList", "OriginalTypeID" );
      OriginalID = mi_OriginalID.intValue( );}
      //:SetCursorFirstEntityByInteger( mSPLDef, szEntityName, "ID", OriginalID, "SubregPhysicalLabelDef" )
      SetCursorFirstEntityByInteger( mSPLDef, szEntityName, "ID", OriginalID, "SubregPhysicalLabelDef" );
      //:SetAttributeFromAttribute( mSPLDef, szEntityName, "ContinuationBreakFlag",
      //:                           mSPLDef, "CompositeComponentList", "ContinuationBreakFlag" )
      SetAttributeFromAttribute( mSPLDef, szEntityName, "ContinuationBreakFlag", mSPLDef, "CompositeComponentList", "ContinuationBreakFlag" );
      //:SetAttributeFromAttribute( mSPLDef, szEntityName, "ContinuationText", 
      //:                           mSPLDef, "CompositeComponentList", "ContinuationText" ) 
      SetAttributeFromAttribute( mSPLDef, szEntityName, "ContinuationText", mSPLDef, "CompositeComponentList", "ContinuationText" );
      //:SetAttributeFromAttribute( mSPLDef, szEntityName, "ContinuationLeadingText", 
      //:                           mSPLDef, "CompositeComponentList", "ContinuationLeadingText" )
      SetAttributeFromAttribute( mSPLDef, szEntityName, "ContinuationLeadingText", mSPLDef, "CompositeComponentList", "ContinuationLeadingText" );

      //:// Accept the temporal subobject.
      //:AcceptSubobject( mSPLDef, "CompositeComponentList" )
      AcceptSubobject( mSPLDef, "CompositeComponentList" );
      //:ELSE
   } 
   else
   { 
      //:// Cancel the temporal subobject.
      //:CancelSubobject( mSPLDef, "CompositeComponentList" )
      CancelSubobject( mSPLDef, "CompositeComponentList" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateSPLD_Statement( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
GOTO_UpdateSPLD_Statement( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Create temporal subobject version and initialize the Breakup Text.
   //:CreateTemporalSubobjectVersion( mSPLDef, "CompositeComponentList" )
   CreateTemporalSubobjectVersion( mSPLDef, "CompositeComponentList" );
   //:mSPLDef.CompositeComponentList.BreakupText = mSPLDef.CompositeComponentList.Value 
   SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "BreakupText", mSPLDef, "CompositeComponentList", "Value" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SPLIT_StatementForContinuation( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
SPLIT_StatementForContinuation( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 10000 ) szFullText
   String   szFullText = null;
   //:STRING ( 10000 ) szContinuationLeadingText
   String   szContinuationLeadingText = null;
   //:STRING ( 10000 ) szContinuationFollowingText
   String   szContinuationFollowingText = null;
   //:INTEGER Length
   int      Length = 0;
   //:INTEGER MaxLength
   int      MaxLength = 0;
   //:INTEGER SourceIndex
   int      SourceIndex = 0;
   //:INTEGER MoveStringLength
   int      MoveStringLength = 0;
   //:INTEGER SourceOffset
   int      SourceOffset = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Split the string in attribute BreakupText at the characters || and put the two parts into the Continuation Leading
   //:// Text and Continuation Text attributes.
   //:szFullText                  = mSPLDef.CompositeComponentList.BreakupText 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szFullText;
   if ( szFullText == null )
      sb_szFullText = new StringBuilder( 32 );
   else
      sb_szFullText = new StringBuilder( szFullText );
       GetVariableFromAttribute( sb_szFullText, mi_lTempInteger_0, 'S', 10001, mSPLDef, "CompositeComponentList", "BreakupText", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szFullText = sb_szFullText.toString( );}
   //:szContinuationLeadingText   = ""
    {StringBuilder sb_szContinuationLeadingText;
   if ( szContinuationLeadingText == null )
      sb_szContinuationLeadingText = new StringBuilder( 32 );
   else
      sb_szContinuationLeadingText = new StringBuilder( szContinuationLeadingText );
      ZeidonStringCopy( sb_szContinuationLeadingText, 1, 0, "", 1, 0, 10001 );
   szContinuationLeadingText = sb_szContinuationLeadingText.toString( );}
   //:szContinuationFollowingText = ""
    {StringBuilder sb_szContinuationFollowingText;
   if ( szContinuationFollowingText == null )
      sb_szContinuationFollowingText = new StringBuilder( 32 );
   else
      sb_szContinuationFollowingText = new StringBuilder( szContinuationFollowingText );
      ZeidonStringCopy( sb_szContinuationFollowingText, 1, 0, "", 1, 0, 10001 );
   szContinuationFollowingText = sb_szContinuationFollowingText.toString( );}
   //:SourceIndex  = 1
   SourceIndex = 1;
   //:MoveStringLength = 0
   MoveStringLength = 0;
   //:Length = zGetStringLen( szFullText )
   Length = zGetStringLen( szFullText );
   //:MaxLength = Length - 4    // We need to back off the end of the string so that the two character compare doesn't go beyond the string.
   MaxLength = Length - 4;

   //:// Loop until we've come to the break characters or the end of the string.
   //:LOOP WHILE SourceIndex <= MaxLength AND szContinuationFollowingText = ""
   while ( SourceIndex <= MaxLength && ZeidonStringCompare( szContinuationFollowingText, 1, 0, "", 1, 0, 10001 ) == 0 )
   { 
      //:IF szFullText[SourceIndex:2] = "||||"
      if ( ZeidonStringCompare( szFullText, SourceIndex, 2, "||", 1, 0, 10001 ) == 0 )
      { 

         //:// First copy over the characters we've just skipped over.
         //:ZeidonStringCopy( szContinuationLeadingText, 1, 5000, szFullText, 1, MoveStringLength, 10000 )
         {StringBuilder sb_szContinuationLeadingText;
         if ( szContinuationLeadingText == null )
            sb_szContinuationLeadingText = new StringBuilder( 32 );
         else
            sb_szContinuationLeadingText = new StringBuilder( szContinuationLeadingText );
                   ZeidonStringCopy( sb_szContinuationLeadingText, 1, 5000, szFullText, 1, MoveStringLength, 10000 );
         szContinuationLeadingText = sb_szContinuationLeadingText.toString( );}

         //:// Then copy over the characters after the break characters.
         //:SourceOffset = MoveStringLength + 3
         SourceOffset = MoveStringLength + 3;
         //:MoveStringLength = Length
         MoveStringLength = Length;
         //:ZeidonStringCopy( szContinuationFollowingText, 1, 5000, szFullText, SourceOffset, MoveStringLength, 10000 )
         {StringBuilder sb_szContinuationFollowingText;
         if ( szContinuationFollowingText == null )
            sb_szContinuationFollowingText = new StringBuilder( 32 );
         else
            sb_szContinuationFollowingText = new StringBuilder( szContinuationFollowingText );
                   ZeidonStringCopy( sb_szContinuationFollowingText, 1, 5000, szFullText, SourceOffset, MoveStringLength, 10000 );
         szContinuationFollowingText = sb_szContinuationFollowingText.toString( );}
         //:ELSE
      } 
      else
      { 
         //:SourceIndex      = SourceIndex + 1
         SourceIndex = SourceIndex + 1;
         //:MoveStringLength = MoveStringLength + 1
         MoveStringLength = MoveStringLength + 1;
      } 

      //:END
   } 

   //:END

   //:// Set the final attributes for the Composite.
   //:mSPLDef.CompositeComponentList.ContinuationLeadingText = szContinuationLeadingText
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "ContinuationLeadingText", szContinuationLeadingText );
   //:mSPLDef.CompositeComponentList.ContinuationText       = szContinuationFollowingText
   SetAttributeFromString( mSPLDef, "CompositeComponentList", "ContinuationText", szContinuationFollowingText );
   //:mSPLDef.CompositeComponentList.BreakupText            = mSPLDef.CompositeComponentList.Value 
   SetAttributeFromAttribute( mSPLDef, "CompositeComponentList", "BreakupText", mSPLDef, "CompositeComponentList", "Value" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CLEAN_ClaimEntries( VIEW ViewToWindow )

public int 
CLEAN_ClaimEntries( View     ViewToWindow )
{

   return( 0 );
//    /*VIEW mSubLC  REGISTERED AS mSubLC
//    
//    FOR EACH mSubLC.S_MarketingSection 
//       FOR EACH mSubLC.S_MarketingUsageOrdering 
//          IF mSubLC.S_MarketingUsage.UsageType = "C"
//             DELETE ENTITY mSubLC.S_MarketingUsageOrdering NONE 
//          END
//       END
//    END
//    FOR EACH mSubLC.S_Usage 
//       IF mSubLC.S_Usage.UsageType = "C"
//          DELETE ENTITY mSubLC.S_Usage NONE 
//       END
//    END
//    BuildCompositeEntries( mSubLC )*/
// END
} 


//:DIALOG OPERATION
//:PostbuildBlockDefinitionUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
PostbuildBlockDefinitionUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSPLDefPanel REGISTERED AS mSPLDefPanel
   zVIEW    mSPLDefPanel = new zVIEW( );
   //:VIEW mSPLDefBlock REGISTERED AS mSPLDefBlock
   zVIEW    mSPLDefBlock = new zVIEW( );
   //:STRING ( 2 )   szSpace
   String   szSpace = null;
   //:STRING ( 256 ) szSearch
   String   szSearch = null;
   //:STRING ( 256 ) szName
   String   szName = null;
   //:STRING ( 256 ) szType
   String   szType = null;
   //:STRING ( 256 ) szGraphic
   String   szGraphic = null;
   //:STRING ( 512 ) szMsg
   String   szMsg = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );

   //:szName = mSPLDefBlock.LLD_Block.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
       GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mSPLDefBlock, "LLD_Block", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szName = sb_szName.toString( );}
   //:szType = mSPLDefBlock.LLD_Block.LLD_SectionType
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szType;
   if ( szType == null )
      sb_szType = new StringBuilder( 32 );
   else
      sb_szType = new StringBuilder( szType );
       GetVariableFromAttribute( sb_szType, mi_lTempInteger_1, 'S', 257, mSPLDefBlock, "LLD_Block", "LLD_SectionType", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szType = sb_szType.toString( );}
   //:szGraphic = mSPLDefBlock.LLD_Block.ImageName 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szGraphic;
   if ( szGraphic == null )
      sb_szGraphic = new StringBuilder( 32 );
   else
      sb_szGraphic = new StringBuilder( szGraphic );
       GetVariableFromAttribute( sb_szGraphic, mi_lTempInteger_2, 'S', 257, mSPLDefBlock, "LLD_Block", "ImageName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szGraphic = sb_szGraphic.toString( );}

   //:IF szType != ""
   if ( ZeidonStringCompare( szType, 1, 0, "", 1, 0, 257 ) != 0 )
   { 
      //:szMsg = szType
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, szType, 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
      //:szSpace = ": "
       {StringBuilder sb_szSpace;
      if ( szSpace == null )
         sb_szSpace = new StringBuilder( 32 );
      else
         sb_szSpace = new StringBuilder( szSpace );
            ZeidonStringCopy( sb_szSpace, 1, 0, ": ", 1, 0, 3 );
      szSpace = sb_szSpace.toString( );}
      //:ELSE
   } 
   else
   { 
      //:szMsg = ""
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, "", 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
      //:szSpace = ""
       {StringBuilder sb_szSpace;
      if ( szSpace == null )
         sb_szSpace = new StringBuilder( 32 );
      else
         sb_szSpace = new StringBuilder( szSpace );
            ZeidonStringCopy( sb_szSpace, 1, 0, "", 1, 0, 3 );
      szSpace = sb_szSpace.toString( );}
   } 

   //:END
   //:IF mSPLDefBlock.LLD_Block.ContinuationBlockFlag = "Y"
   if ( CompareAttributeToString( mSPLDefBlock, "LLD_Block", "ContinuationBlockFlag", "Y" ) == 0 )
   { 
      //:szMsg = szMsg + "[**continued**]"
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, "[**continued**]", 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
      //:szSpace = ": "
       {StringBuilder sb_szSpace;
      if ( szSpace == null )
         sb_szSpace = new StringBuilder( 32 );
      else
         sb_szSpace = new StringBuilder( szSpace );
            ZeidonStringCopy( sb_szSpace, 1, 0, ": ", 1, 0, 3 );
      szSpace = sb_szSpace.toString( );}
   } 

   //:END
   //:IF szName != ""
   if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) != 0 )
   { 
      //:szMsg = szMsg + szSpace + szName
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szSpace, 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szName, 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
      //:szSpace = " - "
       {StringBuilder sb_szSpace;
      if ( szSpace == null )
         sb_szSpace = new StringBuilder( 32 );
      else
         sb_szSpace = new StringBuilder( szSpace );
            ZeidonStringCopy( sb_szSpace, 1, 0, " - ", 1, 0, 3 );
      szSpace = sb_szSpace.toString( );}
   } 

   //:END
   //:IF szGraphic != ""
   if ( ZeidonStringCompare( szGraphic, 1, 0, "", 1, 0, 257 ) != 0 )
   { 
      //:szMsg = szMsg + szSpace + szGraphic
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szSpace, 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szGraphic, 1, 0, 513 );
      szMsg = sb_szMsg.toString( );}
   } 

   //:END

   //:wWebXfer.Root.CurrentSectionTitle = szMsg
   SetAttributeFromString( wWebXfer, "Root", "CurrentSectionTitle", szMsg );
   //:szSearch = mSPLDefBlock.LLD_Block.Name
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szSearch;
   if ( szSearch == null )
      sb_szSearch = new StringBuilder( 32 );
   else
      sb_szSearch = new StringBuilder( szSearch );
       GetVariableFromAttribute( sb_szSearch, mi_lTempInteger_3, 'S', 257, mSPLDefBlock, "LLD_Block", "Name", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szSearch = sb_szSearch.toString( );}
   //:TraceLineS( "PosbuildBlock Name: ", szSearch )
   TraceLineS( "PosbuildBlock Name: ", szSearch );

   //:// mSPLDefBlock should have been set up before transferring to this page.
   //:// TraceLineS( "PostbuildBlockDefinitionUpdate Display ", "LLD_Block" )
   //:// DisplayEntityInstance( mSPLDefBlock, "LLD_Block" )
   //:// SetUpFormattingSelect( mSPLDefPanel, mSPLDefBlock.LLD_Block.LLD_SectionType ) 
   //:SetUpKeywordEntries( mSPLDefBlock, mSPLDefBlock.LLD_Block.LLD_SectionType )
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, mSPLDefBlock, "LLD_Block", "LLD_SectionType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDefBlock );
    m_mSPLDef_Object.omSPLDef_SetUpKeywordEntries( mSPLDefBlock, szTempString_0 );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:TraceLineS( "PostbuildBlockDefinitionUpdate Display2 ", "LLD_Block" )
   TraceLineS( "PostbuildBlockDefinitionUpdate Display2 ", "LLD_Block" );
   //:DisplayEntityInstance( mSPLDefBlock, "LLD_Block" )
   DisplayEntityInstance( mSPLDefBlock, "LLD_Block" );
   //:// Select which of the CompositeComponentList entities are to be displayed
   //:FOR EACH mSPLDef.Display WITHIN mSPLDef.SubregPhysicalLabelDef 
   RESULT = SetCursorFirstEntity( mSPLDef, "Display", "SubregPhysicalLabelDef" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.Display NONE
      RESULT = DeleteEntity( mSPLDef, "Display", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "Display", "SubregPhysicalLabelDef" );
   } 

   //:END

   //:IF szName != ""
   if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) != 0 )
   { 
      //:SET CURSOR FIRST mSPLDef.CompositeComponentList WHERE mSPLDef.CompositeComponentList.DisplayType = szType AND
      //:                                                      mSPLDef.CompositeComponentList.Name = szSearch
      RESULT = SetCursorFirstEntity( mSPLDef, "CompositeComponentList", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( mSPLDef, "CompositeComponentList", "DisplayType", szType ) != 0 || CompareAttributeToString( mSPLDef, "CompositeComponentList", "Name", szSearch ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( mSPLDef, "CompositeComponentList", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:szMsg = "Unable to locate " + szName + " within " + szType
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "Unable to locate ", 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szName, 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, " within ", 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szType, 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
         //:MessageSend( ViewToWindow, "", "Block Update",
         //:             szMsg,
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Block Update", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:FOR EACH mSPLDef.CompositeComponentList 
         RESULT = SetCursorFirstEntity( mSPLDef, "CompositeComponentList", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:szMsg = "Component Type: " + mSPLDef.CompositeComponentList.DisplayType
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_4, 'S', 255, mSPLDef, "CompositeComponentList", "DisplayType", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringCopy( sb_szMsg, 1, 0, "Component Type: ", 1, 0, 513 );
            szMsg = sb_szMsg.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_1, 1, 0, 513 );
            szMsg = sb_szMsg.toString( );}
            //:szMsg = szMsg + "   Name: " + mSPLDef.CompositeComponentList.Value
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, "   Name: ", 1, 0, 513 );
            szMsg = sb_szMsg.toString( );}
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_5, 'S', 32001, mSPLDef, "CompositeComponentList", "Value", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szMsg;
            if ( szMsg == null )
               sb_szMsg = new StringBuilder( 32 );
            else
               sb_szMsg = new StringBuilder( szMsg );
                        ZeidonStringConcat( sb_szMsg, 1, 0, szTempString_2, 1, 0, 513 );
            szMsg = sb_szMsg.toString( );}
            //:TraceLineS( szMsg, "" )
            TraceLineS( szMsg, "" );
            RESULT = SetCursorNextEntity( mSPLDef, "CompositeComponentList", "" );
         } 

         //:END
         //:DisplayObjectInstance( mSPLDef, "", "" )
         DisplayObjectInstance( mSPLDef, "", "" );
         //:ELSE
      } 
      else
      { 
         //:szMsg = "Looking for: " + szName + "  within: " + szType
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringCopy( sb_szMsg, 1, 0, "Looking for: ", 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szName, 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, "  within: ", 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
          {StringBuilder sb_szMsg;
         if ( szMsg == null )
            sb_szMsg = new StringBuilder( 32 );
         else
            sb_szMsg = new StringBuilder( szMsg );
                  ZeidonStringConcat( sb_szMsg, 1, 0, szType, 1, 0, 513 );
         szMsg = sb_szMsg.toString( );}
         //:TraceLineS( szMsg, "" )
         TraceLineS( szMsg, "" );
         //:SET CURSOR NEXT mSPLDef.CompositeComponentList
         RESULT = SetCursorNextEntity( mSPLDef, "CompositeComponentList", "" );
         //:LOOP WHILE RESULT = zCURSOR_SET AND mSPLDef.CompositeComponentList.SelectLevel > 1
         while ( RESULT == zCURSOR_SET && CompareAttributeToInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 1 ) > 0 )
         { 
            //:IF mSPLDef.CompositeComponentList.SelectLevel = 2
            if ( CompareAttributeToInteger( mSPLDef, "CompositeComponentList", "SelectLevel", 2 ) == 0 )
            { 
               //:CREATE ENTITY mSPLDef.Display
               RESULT = CreateEntity( mSPLDef, "Display", zPOS_AFTER );
            } 

            //:END
            //:SET CURSOR NEXT mSPLDef.CompositeComponentList
            RESULT = SetCursorNextEntity( mSPLDef, "CompositeComponentList", "" );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ACCEPT_BlockSpecialFormat( VIEW ViewToWindow )

//:   VIEW mSPLDefBlock REGISTERED AS mSPLDefBlock
public int 
ACCEPT_BlockSpecialFormat( View     ViewToWindow )
{
   zVIEW    mSPLDefBlock = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );
   return( 0 );
//    
// // TraceLineS( "ACCEPT_BlockSpecialFormat LLD_Block", "" )
// // DisplayEntityInstance( mSPLDefBlock, "LLD_Block" ) 
// /*
//    VIEW mSPLDef      REGISTERED AS mSPLDef
//    VIEW mSPLDefPanel REGISTERED AS mSPLDefPanel
//    
//    // Set the selected Name in the LLD_SpecialSectionAttribute entry.
//    mSPLDef.LLD_SpecialSectionAttribute.Name = mSPLDefPanel.SpecialFormattingSelectEntry.KeywordName 
//    AcceptSubobject( mSPLDef, "LLD_SpecialSectionAttribute" )
// */
// END
} 


//:DIALOG OPERATION
//:CANCEL_BlockSpecialFormat( VIEW ViewToWindow )
//:/*
public int 
CANCEL_BlockSpecialFormat( View     ViewToWindow )
{

   return( 0 );
//    VIEW mSPLDef REGISTERED AS mSPLDef
//    
//    CancelSubobject( mSPLDef, "LLD_SpecialSectionAttribute" )
// */
// END
} 


//:DIALOG OPERATION
//:PostbuildBlockSpecialFormatDef( VIEW ViewToWindow )

//:   VIEW mSPLDefPanel REGISTERED AS mSPLDefPanel
public int 
PostbuildBlockSpecialFormatDef( View     ViewToWindow )
{
   zVIEW    mSPLDefPanel = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefBlock REGISTERED AS mSPLDefBlock
   zVIEW    mSPLDefBlock = new zVIEW( );
   //:STRING ( 256 ) szName
   String   szName = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDefBlock, "mSPLDefBlock", ViewToWindow, zLEVEL_TASK );

   //:szName = mSPLDefBlock.LLD_SpecialSectionAttribute.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
       GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mSPLDefBlock, "LLD_SpecialSectionAttribute", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szName = sb_szName.toString( );}
   //:// TraceLineS( "PostbuildBlockSpecialFormatDef Position on Name: ", szName )
   //:// DisplayEntityInstance( mSPLDefBlock, "LLD_Block" )
   //:// Position on the correct SpecialFormattingSelectEntry.
   //:SET CURSOR FIRST mSPLDefPanel.SpecialFormattingSelectEntry 
   //:           WHERE mSPLDefPanel.SpecialFormattingSelectEntry.KeywordName = szName
   RESULT = SetCursorFirstEntityByString( mSPLDefPanel, "SpecialFormattingSelectEntry", "KeywordName", szName, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GENERATE_SPLD_LabelDottedBorders( VIEW ViewToWindow )

//:   VIEW mSPLDefPanel  BASED ON LOD  mSPLDef
public int 
GENERATE_SPLD_LabelDottedBorders( View     ViewToWindow )
{
   zVIEW    mSPLDefPanel = new zVIEW( );
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szLPLR_Name
   String   szLPLR_Name = null;
   //:STRING ( 64 ) szSystemIniApplName
   String   szSystemIniApplName = null;
   //:STRING ( 64 ) szLabelName
   String   szLabelName = null;
   //:STRING ( 256 ) szDirectory
   String   szDirectory = null;
   //:STRING ( 256 ) szApplication
   String   szApplication = null;
   //:STRING ( 256 ) szXmlName
   String   szXmlName = null;
   //:STRING ( 256 ) szXslName
   String   szXslName = null;
   //:SHORT         nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// We will use mSPLDefPanel, if it exists, as it will always be pointing to the top of the LLD_Block
   //:// subobject, while mSPLDef sometimes is pointing to a recursive subobject.
   //:GET VIEW mSPLDefPanel NAMED "mSPLDefPanel"
   RESULT = GetViewByName( mSPLDefPanel, "mSPLDefPanel", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:GET VIEW mSPLDefPanel NAMED "mSPLDef"
      RESULT = GetViewByName( mSPLDefPanel, "mSPLDef", ViewToWindow, zLEVEL_TASK );
      //:CreateViewFromView( mSPLDefPanel, mSPLDefPanel )
      CreateViewFromView( mSPLDefPanel, mSPLDefPanel );
      //:ResetViewFromSubobjectTop( mSPLDefPanel )
      ResetViewFromSubobjectTop( mSPLDefPanel );
      //:NAME VIEW mSPLDefPanel "mSPLDefPanel"
      SetNameForView( mSPLDefPanel, "mSPLDefPanel", null, zLEVEL_TASK );
   } 

   //:END

   //:// Generate the label using LLD and SLC data defined in mSPLDef.    
   //:mSPLDefPanel.SubregPhysicalLabelDef.wFormatWithDottedBorders = "Y"
   SetAttributeFromString( mSPLDefPanel, "SubregPhysicalLabelDef", "wFormatWithDottedBorders", "Y" );
   //:nRC = GeneratePDF_Label( mSPLDefPanel )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDefPanel );
    nRC = m_mSPLDef_Object.omSPLDef_GeneratePDF_Label( mSPLDefPanel );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:IF nRC = 2
   if ( nRC == 2 )
   { 
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END


   //:// Open the File
   //:// SfGetApplicationForSubtask( szLPLR_Name, mSPLDef )
   //:// szSystemIniApplName = "[App." + szLPLR_Name + "]"
   //:// SysReadZeidonIni( -1, szSystemIniApplName, "WebDirectory", szDirectory )
   //:// SysConvertEnvironmentString( szDirectory, szDirectory )
   //:// szDirectory = "C:/Program Files/Apache Group/tomcat 7.0/webapps/ROOT/epamms/"

   //:SysGetEnvVar( szDirectory, "CATALINA_HOME", 256 )
   {StringBuilder sb_szDirectory;
   if ( szDirectory == null )
      sb_szDirectory = new StringBuilder( 32 );
   else
      sb_szDirectory = new StringBuilder( szDirectory );
       m_KZOEP1AA.SysGetEnvVar( sb_szDirectory, "CATALINA_HOME", 256 );
   szDirectory = sb_szDirectory.toString( );}
   //:// SysAppendcDirSep( szDirectory )
   //:SysConvertEnvironmentString( szDirectory, szDirectory )
   {StringBuilder sb_szDirectory;
   if ( szDirectory == null )
      sb_szDirectory = new StringBuilder( 32 );
   else
      sb_szDirectory = new StringBuilder( szDirectory );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectory, szDirectory );
   szDirectory = sb_szDirectory.toString( );}
   //:szDirectory = szDirectory + "/webapps/ROOT"
    {StringBuilder sb_szDirectory;
   if ( szDirectory == null )
      sb_szDirectory = new StringBuilder( 32 );
   else
      sb_szDirectory = new StringBuilder( szDirectory );
      ZeidonStringConcat( sb_szDirectory, 1, 0, "/webapps/ROOT", 1, 0, 257 );
   szDirectory = sb_szDirectory.toString( );}
   //:szApplication = "/" + mSPLDefPanel.SubregOrganization.LoginName + "/"
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 129, mSPLDefPanel, "SubregOrganization", "LoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szApplication;
   if ( szApplication == null )
      sb_szApplication = new StringBuilder( 32 );
   else
      sb_szApplication = new StringBuilder( szApplication );
      ZeidonStringCopy( sb_szApplication, 1, 0, "/", 1, 0, 257 );
   szApplication = sb_szApplication.toString( );}
    {StringBuilder sb_szApplication;
   if ( szApplication == null )
      sb_szApplication = new StringBuilder( 32 );
   else
      sb_szApplication = new StringBuilder( szApplication );
      ZeidonStringConcat( sb_szApplication, 1, 0, szTempString_0, 1, 0, 257 );
   szApplication = sb_szApplication.toString( );}
    {StringBuilder sb_szApplication;
   if ( szApplication == null )
      sb_szApplication = new StringBuilder( 32 );
   else
      sb_szApplication = new StringBuilder( szApplication );
      ZeidonStringConcat( sb_szApplication, 1, 0, "/", 1, 0, 257 );
   szApplication = sb_szApplication.toString( );}

   //:szLabelName = mSPLDefPanel.SubregProduct.Name
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szLabelName;
   if ( szLabelName == null )
      sb_szLabelName = new StringBuilder( 32 );
   else
      sb_szLabelName = new StringBuilder( szLabelName );
       GetVariableFromAttribute( sb_szLabelName, mi_lTempInteger_1, 'S', 65, mSPLDefPanel, "SubregProduct", "Name", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szLabelName = sb_szLabelName.toString( );}

   //:szXmlName = szDirectory + szApplication + "xml/"
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringCopy( sb_szXmlName, 1, 0, szDirectory, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, szApplication, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, "xml/", 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
   //:szXslName = szDirectory + szApplication + "xsl/"
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringCopy( sb_szXslName, 1, 0, szDirectory, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, szApplication, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, "xsl/", 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
   //:SysValidDirOrFile( szXmlName, 1, 1, 256 )
   SysValidDirOrFile( szXmlName, 1, 1, 256 );
   //:SysValidDirOrFile( szXslName, 1, 1, 256 )
   SysValidDirOrFile( szXslName, 1, 1, 256 );
   //:szXmlName = szXmlName + szLabelName + ".xml"
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, szLabelName, 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
    {StringBuilder sb_szXmlName;
   if ( szXmlName == null )
      sb_szXmlName = new StringBuilder( 32 );
   else
      sb_szXmlName = new StringBuilder( szXmlName );
      ZeidonStringConcat( sb_szXmlName, 1, 0, ".xml", 1, 0, 257 );
   szXmlName = sb_szXmlName.toString( );}
   //:szXslName = szXslName + szLabelName + ".xsl"
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, szLabelName, 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
    {StringBuilder sb_szXslName;
   if ( szXslName == null )
      sb_szXslName = new StringBuilder( 32 );
   else
      sb_szXslName = new StringBuilder( szXslName );
      ZeidonStringConcat( sb_szXslName, 1, 0, ".xsl", 1, 0, 257 );
   szXslName = sb_szXslName.toString( );}
   //:TraceLineS( "Output Xsl Filename: ", szXslName )
   TraceLineS( "Output Xsl Filename: ", szXslName );

   //:// ConvertXMLToPDF( "c:\lplr\epamms\xsl\TestLabel.xml", "c:\lplr\epamms\xsl\TestLabel.xsl", "TestLabel" )
   //:// ConvertXMLToPDF( szXmlName, szXslName, szLabelName )
   //:ConvertXML_ToPDF( szDirectory, szApplication, szLabelName )
   m_ZDRVROPR.ConvertXML_ToPDF( szDirectory, szApplication, szLabelName );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CONTINUE_BlockSubBlockDefAdd( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CONTINUE_BlockSubBlockDefAdd( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Transfer to the correct next page (block or subblock).

   //:// A Section must be selected.
   //:// IF mSPLDef.LLD_Block.Type = ""
   //:   MessageSend( ViewToWindow, "", "Add Block/SubBlock",
   //:                "A Section Type must be selected.",
   //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Add Block/SubBlock", "A Section Type must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:   RETURN 2
   return( 2 );
// // END
//    
//    /*IF mSPLDef.LLD_Block.Type = "Block" 
//       mSPLDef.LLD_Block.LLD_SectionType = mSPLDef.LLD_Block.LLD_SectionType 
//       SetWindowActionBehavior( ViewToWindow, zWAB_ReplaceWindowWithModalWindow, "wSPLD", "SPLD_BlockDefinitionUpdate" )
//    ELSE
//       mSPLDef.LLD_SubBlock.LLD_SectionType = mSPLDef.LLD_Block.LLD_SectionType 
//       SetWindowActionBehavior( ViewToWindow, zWAB_ReplaceWindowWithModalWindow, "wSPLD", "SPLD_BlockDefinitionUpdateSub" )
//    END*/
// END
} 


//:DIALOG OPERATION
//:ExecuteJOE_Test2( VIEW ViewToWindow )

//:   VIEW ZPLOCKO BASED ON LOD ZPLOCKO
public int 
ExecuteJOE_Test2( View     ViewToWindow )
{
   zVIEW    ZPLOCKO = new zVIEW( );
   //:VIEW mSubreg BASED ON LOD mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:ACTIVATE ZPLOCKO Multiple
   RESULT = ActivateObjectInstance( ZPLOCKO, "ZPLOCKO", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW ZPLOCKO "ZPLOCKO1"
   SetNameForView( ZPLOCKO, "ZPLOCKO1", null, zLEVEL_TASK );

   //://ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID > 0
   //://NAME VIEW mSPLDef "mSPLDef"

   //:ACTIVATE mSubreg SingleForUpdate WHERE mSubreg.Subregistrant.ID = 1
   o_fnLocalBuildQual_19( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE_FOR_UPDATE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

   //:ACTIVATE ZPLOCKO Multiple
   RESULT = ActivateObjectInstance( ZPLOCKO, "ZPLOCKO", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW ZPLOCKO "ZPLOCKO2"
   SetNameForView( ZPLOCKO, "ZPLOCKO2", null, zLEVEL_TASK );

   //:SET CURSOR FIRST ZPLOCKO.ZeidonLock 
   RESULT = SetCursorFirstEntity( ZPLOCKO, "ZeidonLock", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Test Locking",
      //:             "Lock Exists",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Test Locking", "Lock Exists", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:MessageSend( ViewToWindow, "", "Test Locking",
      //:             "Lock DOES NOT Exist",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Test Locking", "Lock DOES NOT Exist", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 
   return( 0 );
//    /*VIEW mSPLDef  BASED ON LOD mSPLDef
//    VIEW mSPLDef2 BASED ON LOD mSPLDef
//    INTEGER SaveID
//    
//    // Execute Tests to Check for JOE Bugs.
//    
//    // TEST 2
//    // Test of Entity created in one view is not showing second view.
//    // This only happens when the object is activated from the database and changed.
//    
//    // Activate the basic object.
//    //ActivateOI_FromFile( mSPLDef, "mSPLDef", ViewToWindow, "c:\temp\JOE_Test2.por", zSINGLE )
//    ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = 5
//    NAME VIEW mSPLDef "mSPLDef"
//    
//    // First, do a simple create and see if entity is seen in other view.
//    
//    SET CURSOR LAST mSPLDef.LLD_Panel  
//    CreateViewFromView( mSPLDef2, mSPLDef )
//    NAME VIEW mSPLDef2 "mSPLDef2"
//    
//    CREATE ENTITY mSPLDef.ContinuationStatement 
//    mSPLDef.ContinuationStatement.Title = "Title 1"
//    mSPLDef.ContinuationStatement.Text  = "Text 1"
//    
//    IF mSPLDef2.ContinuationStatement DOES NOT EXIST
//       MessageSend( ViewToWindow, "", "JOE Test 2",
//                    "ContinuationStatement doesn't exist 1",
//                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//       RETURN 2
//    END 
//    
//    // Next, do the same test except that the entity is first deleted from the primary view.
//    
//    DELETE ENTITY mSPLDef.ContinuationStatement NONE 
//    
//    CREATE ENTITY mSPLDef.ContinuationStatement 
//    mSPLDef.ContinuationStatement.Title = "Title 1"
//    mSPLDef.ContinuationStatement.Text  = "Text 1"
//    
//    SET CURSOR FIRST mSPLDef2.ContinuationStatement
//    IF RESULT < zCURSOR_SET
//       MessageSend( ViewToWindow, "", "JOE Test 2",
//                    "ContinuationStatement doesn't exist 2",
//                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//       RETURN 2
//    END 
//    DropView( mSPLDef2 )
//    
//    // Test accepting and canceling subobjects with this new view.
//    SET CURSOR FIRST mSPLDef.LLD_Panel  
//    CreateViewFromView( mSPLDef2, mSPLDef )
//    NAME VIEW mSPLDef2 "mSPLDef2"
//    SET CURSOR NEXT mSPLDef2.LLD_Panel 
//    SET CURSOR NEXT mSPLDef2.LLD_Panel  
//    TraceLineI( "$$$$ Panel ID: ", mSPLDef2.LLD_Panel.ID )
//    CreateTemporalSubobjectVersion( mSPLDef2, "LLD_Panel" )
//    //SET CURSOR NEXT mSPLDef2.LLD_Block 
//    //SET CURSOR NEXT mSPLDef2.LLD_Block
//    TraceLineI( "$$$$ Block ID: ", mSPLDef2.LLD_Block.ID )
//    SetViewToSubobject( mSPLDef2, "LLD_SubBlock" )
//    CreateTemporalSubobjectVersion( mSPLDef2, "LLD_Block" )
//    TraceLineI( "$$$$ Attribute ID: ", mSPLDef2.LLD_SpecialSectionAttribute.ID )
//    CreateTemporalSubobjectVersion( mSPLDef2, "LLD_SpecialSectionAttribute" )
//    TraceLineS( "$$$$ Trace 1", "" )
//    CancelSubobject( mSPLDef2, "LLD_SpecialSectionAttribute" )
//    TraceLineS( "$$$$ Trace 2", "" )
//    CreateTemporalSubobjectVersion( mSPLDef2, "LLD_SpecialSectionAttribute" )
//    TraceLineS( "$$$$ Trace 3", "" )
//    CancelSubobject( mSPLDef2, "LLD_SpecialSectionAttribute" )
//    TraceLineS( "$$$$ Trace 4", "" )
//    CancelSubobject( mSPLDef2, "LLD_Block" )
//    TraceLineS( "$$$$ Trace 5", "" )
//    ResetViewFromSubobject( mSPLDef2 )
//    TraceLineS( "$$$$ Trace 6", "" )
//    CancelSubobject( mSPLDef2, "LLD_Panel" )
//    TraceLineS( "$$$$ Trace 7", "" )
//    
//    // First CancelSubobject test after delete of a subentity.
//    CreateTemporalSubobjectVersion( mSPLDef, "LLD_Page" )
//    SET CURSOR FIRST mSPLDef.LLD_Panel 
//    SaveID = mSPLDef.LLD_Panel.ID  
//    DELETE ENTITY mSPLDef.LLD_Panel 
//    CancelSubobject( mSPLDef, "LLD_Page" )
//    SET CURSOR FIRST mSPLDef.LLD_Panel WHERE mSPLDef.LLD_Panel.ID = SaveID
//    IF RESULT < zCURSOR_SET
//       MessageSend( ViewToWindow, "", "JOE Test 2",
//                    "CancelSubobject did not restore deleted Panel.",
//                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//       RETURN 2
//    END
//    TraceLineS( "$$$$ Cancel of deleted Panel worked", "" )
//    
//    // Second CancelSubobject test after delete of a subentity.
//    SET CURSOR FIRST mSPLDef.LLD_Panel 
//    SET CURSOR NEXT mSPLDef.LLD_Panel 
//    SET CURSOR NEXT mSPLDef.LLD_Panel
//    CreateTemporalSubobjectVersion( mSPLDef, "LLD_Block" )
//    SaveID = mSPLDef.LLD_SpecialSectionAttribute.ID
//    TraceLineI( "$$$$ Attribute ID: ", SaveID )
//    DELETE ENTITY mSPLDef.LLD_SpecialSectionAttribute 
//    CancelSubobject( mSPLDef, "LLD_Block" )
//    SET CURSOR FIRST mSPLDef.LLD_SpecialSectionAttribute WHERE mSPLDef.LLD_SpecialSectionAttribute.ID = SaveID
//    IF RESULT < zCURSOR_SET
//       MessageSend( ViewToWindow, "", "JOE Test 2",
//                    "CancelSubobject did not restore deleted entity.",
//                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//       RETURN 2
//    END 
//    
//    TraceLineS( "*** JOE Test 2 successfully completed", "" )*/
// END
} 


//:DIALOG OPERATION
//:DELETE_SpecialFormatDef( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
DELETE_SpecialFormatDef( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mSPLDef.LLD_SpecialSectionAttribute  
   RESULT = DeleteEntity( mSPLDef, "LLD_SpecialSectionAttribute", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:TestLocking( VIEW ViewToWindow )

//:   VIEW ZPLOCKO BASED ON LOD ZPLOCKO
public int 
TestLocking( View     ViewToWindow )
{
   zVIEW    ZPLOCKO = new zVIEW( );
   //:VIEW mSubreg BASED ON LOD mSubreg
   zVIEW    mSubreg = new zVIEW( );
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;


   //://ACTIVATE ZPLOCKO Multiple
   //://NAME VIEW ZPLOCKO "ZPLOCKO1"

   //://ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID > 0
   //://NAME VIEW mSPLDef "mSPLDef"

   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID > 0
   o_fnLocalBuildQual_23( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   return( 0 );
//    
//    //ACTIVATE ZPLOCKO Multiple
//    //NAME VIEW ZPLOCKO "ZPLOCKO2"
// END
} 


//:DIALOG OPERATION
//:InitSPLD_ForDelete( VIEW ViewToWindow )

public int 
InitSPLD_ForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CreateSLC( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CreateSLC( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC  BASED ON LOD mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mSubLC  BASED ON LOD mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mSubLC2 BASED ON LOD mSubLC
   zVIEW    mSubLC2 = new zVIEW( );
   //:VIEW mSPLDef BASED ON LOD mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_0 = null;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mSubreg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_24( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

   //:ACTIVATE mSubLC EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubLC, "mSubLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );

   //:CREATE ENTITY mSubLC.SubregLabelContent
   RESULT = CreateEntity( mSubLC, "SubregLabelContent", zPOS_AFTER );

   //:// General Section
   //:FOR EACH mMasLC.M_GeneralSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_GeneralSection
      RESULT = CreateEntity( mSubLC, "S_GeneralSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_GeneralSection", mMasLC, "M_GeneralSection", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_GeneralSection", mMasLC, "M_GeneralSection", zSET_NULL );
      //:INCLUDE mSubLC.M_GeneralSection FROM mMasLC.M_GeneralSection
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_GeneralSection", mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:FOR EACH mMasLC.M_GeneralStatement
      RESULT = SetCursorFirstEntity( mMasLC, "M_GeneralStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSubLC.S_GeneralStatement
         RESULT = CreateEntity( mSubLC, "S_GeneralStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, "S_GeneralStatement", mMasLC, "M_GeneralStatement", zSET_NULL )
         SetMatchingAttributesByName( mSubLC, "S_GeneralStatement", mMasLC, "M_GeneralStatement", zSET_NULL );
         //:INCLUDE mSubLC.M_GeneralStatement FROM mMasLC.M_GeneralStatement
         RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_GeneralStatement", mMasLC, "M_GeneralStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mMasLC, "M_GeneralStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_GeneralSection", "" );
      //:END
   } 

   //:END

   //:// Ingredients Section
   //:FOR EACH mMasLC.M_IngredientsSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_IngredientsSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_IngredientsSection
      RESULT = CreateEntity( mSubLC, "S_IngredientsSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_IngredientsSection", mMasLC, "M_IngredientsSection", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_IngredientsSection", mMasLC, "M_IngredientsSection", zSET_NULL );
      //:INCLUDE mSubLC.M_IngredientsSection FROM mMasLC.M_IngredientsSection
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_IngredientsSection", mMasLC, "M_IngredientsSection", zPOS_AFTER );
      //:FOR EACH mMasLC.M_IngredientsStatement
      RESULT = SetCursorFirstEntity( mMasLC, "M_IngredientsStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSubLC.S_IngredientsStatement
         RESULT = CreateEntity( mSubLC, "S_IngredientsStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, "S_IngredientsStatement", mMasLC, "M_IngredientsStatement", zSET_NULL )
         SetMatchingAttributesByName( mSubLC, "S_IngredientsStatement", mMasLC, "M_IngredientsStatement", zSET_NULL );
         //:INCLUDE mSubLC.M_IngredientsStatement FROM mMasLC.M_IngredientsStatement
         RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_IngredientsStatement", mMasLC, "M_IngredientsStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mMasLC, "M_IngredientsStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_IngredientsSection", "" );
      //:END
   } 

   //:END

   //:// StorageDisposal Section
   //:FOR EACH mMasLC.M_StorageDisposalSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_StorageDisposalSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_StorageDisposalSection
      RESULT = CreateEntity( mSubLC, "S_StorageDisposalSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_StorageDisposalSection", mMasLC, "M_StorageDisposalSection", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_StorageDisposalSection", mMasLC, "M_StorageDisposalSection", zSET_NULL );
      //:INCLUDE mSubLC.M_StorageDisposalSection FROM mMasLC.M_StorageDisposalSection
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_StorageDisposalSection", mMasLC, "M_StorageDisposalSection", zPOS_AFTER );
      //:FOR EACH mMasLC.M_StorageDisposalStatement
      RESULT = SetCursorFirstEntity( mMasLC, "M_StorageDisposalStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSubLC.S_StorageDisposalStatement
         RESULT = CreateEntity( mSubLC, "S_StorageDisposalStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, "S_StorageDisposalStatement", mMasLC, "M_StorageDisposalStatement", zSET_NULL )
         SetMatchingAttributesByName( mSubLC, "S_StorageDisposalStatement", mMasLC, "M_StorageDisposalStatement", zSET_NULL );
         //:INCLUDE mSubLC.M_StorageDisposalStatement FROM mMasLC.M_StorageDisposalStatement
         RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_StorageDisposalStatement", mMasLC, "M_StorageDisposalStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mMasLC, "M_StorageDisposalStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_StorageDisposalSection", "" );
      //:END
   } 

   //:END

   //:// DirectionsForUse Section
   //:FOR EACH mMasLC.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_DirectionsForUseSection
      RESULT = CreateEntity( mSubLC, "S_DirectionsForUseSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_DirectionsForUseSection", mMasLC, "M_DirectionsForUseSection", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_DirectionsForUseSection", mMasLC, "M_DirectionsForUseSection", zSET_NULL );
      //:INCLUDE mSubLC.M_DirectionsForUseSection FROM mMasLC.M_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_DirectionsForUseSection", mMasLC, "M_DirectionsForUseSection", zPOS_AFTER );
      //:FOR EACH mMasLC.M_DirectionsForUseStatement
      RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSubLC.S_DirectionsForUseStatement
         RESULT = CreateEntity( mSubLC, "S_DirectionsForUseStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, "S_DirectionsForUseStatement", mMasLC, "M_DirectionsForUseStatement", zSET_NULL )
         SetMatchingAttributesByName( mSubLC, "S_DirectionsForUseStatement", mMasLC, "M_DirectionsForUseStatement", zSET_NULL );
         //:INCLUDE mSubLC.M_DirectionsForUseStatement FROM mMasLC.M_DirectionsForUseStatement
         RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_DirectionsForUseStatement", mMasLC, "M_DirectionsForUseStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Section
   //:FOR EACH mMasLC.M_MarketingSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_MarketingSection
      RESULT = CreateEntity( mSubLC, "S_MarketingSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_MarketingSection", mMasLC, "M_MarketingSection", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_MarketingSection", mMasLC, "M_MarketingSection", zSET_NULL );
      //:INCLUDE mSubLC.M_MarketingSection FROM mMasLC.M_MarketingSection
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_MarketingSection", mMasLC, "M_MarketingSection", zPOS_AFTER );
      //:FOR EACH mMasLC.M_MarketingStatement
      RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSubLC.S_MarketingStatement
         RESULT = CreateEntity( mSubLC, "S_MarketingStatement", zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, "S_MarketingStatement", mMasLC, "M_MarketingStatement", zSET_NULL )
         SetMatchingAttributesByName( mSubLC, "S_MarketingStatement", mMasLC, "M_MarketingStatement", zSET_NULL );
         //:INCLUDE mSubLC.M_MarketingStatement FROM mMasLC.M_MarketingStatement
         RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_MarketingStatement", mMasLC, "M_MarketingStatement", zPOS_AFTER );
         RESULT = SetCursorNextEntity( mMasLC, "M_MarketingStatement", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// HumanHazard Section
   //:FOR EACH mMasLC.M_HumanHazardSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_HumanHazardSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_HumanHazardSection
      RESULT = CreateEntity( mSubLC, "S_HumanHazardSection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_HumanHazardSection", mMasLC, "M_HumanHazardSection", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_HumanHazardSection", mMasLC, "M_HumanHazardSection", zSET_NULL );
      //:INCLUDE mSubLC.M_HumanHazardSection FROM mMasLC.M_HumanHazardSection
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_HumanHazardSection", mMasLC, "M_HumanHazardSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( mMasLC, "M_HumanHazardSection", "" );
   } 

   //:END

   //:// Usage Section
   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSubLC.S_Usage
      RESULT = CreateEntity( mSubLC, "S_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mSubLC, "S_Usage", mMasLC, "M_Usage", zSET_NULL )
      SetMatchingAttributesByName( mSubLC, "S_Usage", mMasLC, "M_Usage", zSET_NULL );
      //:INCLUDE mSubLC.M_Usage FROM mMasLC.M_Usage
      RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_Usage", mMasLC, "M_Usage", zPOS_AFTER );
      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END

   //:// Eliminate any duplicate Usages.
   //:/*CreateViewFromView( mSubLC2, mSubLC )
   //:FOR EACH mSubLC.S_Usage
   //:   SET CURSOR FIRST mSubLC2.S_Usage
   //:              WHERE mSubLC2.S_Usage.UsageType = mSubLC.S_Usage.UsageType
   //:                AND mSubLC2.S_Usage.Name = mSubLC.S_Usage.Name
   //:                AND mSubLC2.S_Usage.ID  != mSubLC.S_Usage.ID
   //:   IF RESULT >= zCURSOR_SET
   //:      IssueError( ViewToWindow, 0, 0, mSubLC.S_Usage.Name )
   //:      DELETE ENTITY mSubLC2.S_Usage NONE
   //:   END
   //:END
   //:DropView( mSubLC2 )*/

   //:// DirectionsForUse Usage
   //:FOR EACH mMasLC.M_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSubLC.S_DirectionsForUseSection
      //:           WHERE mSubLC.S_DirectionsForUseSection.Title = mMasLC.M_DirectionsForUseSection.Title
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mMasLC, "M_DirectionsForUseSection", "Title" );
      szTempString_0 = sb_szTempString_0.toString( );}
      RESULT = SetCursorFirstEntityByString( mSubLC, "S_DirectionsForUseSection", "Title", szTempString_0, "" );
      //:FOR EACH mMasLC.M_DirectionsUsage
      RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CreateViewFromView( mSubLC2, mSubLC )
         CreateViewFromView( mSubLC2, mSubLC );
         //:SET CURSOR FIRST mSubLC2.S_DirectionsUsage
         //:           WHERE mSubLC2.S_DirectionsUsage.UsageType = mMasLC.M_DirectionsUsage.UsageType
         //:             AND mSubLC2.S_DirectionsUsage.Name = mMasLC.M_DirectionsUsage.Name
         RESULT = SetCursorFirstEntity( mSubLC2, "S_DirectionsUsage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSubLC2, "S_DirectionsUsage", "UsageType", mMasLC, "M_DirectionsUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( mSubLC2, "S_DirectionsUsage", "Name", mMasLC, "M_DirectionsUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSubLC2, "S_DirectionsUsage", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST mSubLC.S_Usage WHERE mSubLC.S_Usage.Name = mMasLC.M_DirectionsUsage.Name
            //:                                  AND mSubLC.S_Usage.UsageType = mMasLC.M_DirectionsUsage.UsageType
            RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSubLC, "S_Usage", "Name", mMasLC, "M_DirectionsUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( mSubLC, "S_Usage", "UsageType", mMasLC, "M_DirectionsUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( ViewToWindow, 0, 0, "Programming Error 1" )
               IssueError( ViewToWindow, 0, 0, "Programming Error 1" );
            } 

            //:END
            //:INCLUDE mSubLC.S_DirectionsUsage FROM mSubLC.S_Usage
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "S_DirectionsUsage", mSubLC, "S_Usage", zPOS_AFTER );
         } 

         //:END
         //:DropView( mSubLC2 )
         DropView( mSubLC2 );
         RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsUsage", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Marketing Usage
   //:FOR EACH mMasLC.M_MarketingSection
   RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSubLC.S_MarketingSection
      //:           WHERE mSubLC.S_MarketingSection.Title = mMasLC.M_MarketingSection.Title
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mMasLC, "M_MarketingSection", "Title" );
      szTempString_0 = sb_szTempString_0.toString( );}
      RESULT = SetCursorFirstEntityByString( mSubLC, "S_MarketingSection", "Title", szTempString_0, "" );
      //:FOR EACH mMasLC.M_MarketingUsage
      RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CreateViewFromView( mSubLC2, mSubLC )
         CreateViewFromView( mSubLC2, mSubLC );
         //:SET CURSOR FIRST mSubLC2.S_MarketingUsage
         //:           WHERE mSubLC2.S_MarketingUsage.UsageType = mMasLC.M_MarketingUsage.UsageType
         //:             AND mSubLC2.S_MarketingUsage.Name = mMasLC.M_MarketingUsage.Name
         RESULT = SetCursorFirstEntity( mSubLC2, "S_MarketingUsage", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSubLC2, "S_MarketingUsage", "UsageType", mMasLC, "M_MarketingUsage", "UsageType" ) != 0 ||
                    CompareAttributeToAttribute( mSubLC2, "S_MarketingUsage", "Name", mMasLC, "M_MarketingUsage", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( mSubLC2, "S_MarketingUsage", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST mSubLC.S_Usage WHERE mSubLC.S_Usage.Name = mMasLC.M_MarketingUsage.Name
            //:                                     AND mSubLC.S_Usage.UsageType = mMasLC.M_MarketingUsage.UsageType
            RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( mSubLC, "S_Usage", "Name", mMasLC, "M_MarketingUsage", "Name" ) != 0 ||
                       CompareAttributeToAttribute( mSubLC, "S_Usage", "UsageType", mMasLC, "M_MarketingUsage", "UsageType" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
               } 

            } 

            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( ViewToWindow, 0, 0, "Programming Error 1" )
               IssueError( ViewToWindow, 0, 0, "Programming Error 1" );
            } 

            //:END
            //:INCLUDE mSubLC.S_MarketingUsage FROM mSubLC.S_Usage
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "S_MarketingUsage", mSubLC, "S_Usage", zPOS_AFTER );
         } 

         //:END
         //:DropView( mSubLC2 )
         DropView( mSubLC2 );
         RESULT = SetCursorNextEntity( mMasLC, "M_MarketingUsage", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_MarketingSection", "" );
      //:END
   } 

   //:END

   //:IssueError( ViewToWindow, 0, 0, "Before Commit" )
   IssueError( ViewToWindow, 0, 0, "Before Commit" );
   //://COMMIT mSubLC
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:IssueError( ViewToWindow, 0, 0, "Database Write Error" )
      IssueError( ViewToWindow, 0, 0, "Database Write Error" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSubregProduct( VIEW ViewToWindow )

public int 
DeleteSubregProduct( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CopySPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CopySPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC  REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:VIEW mSPLDefNew BASED ON LOD mSPLDef
   zVIEW    mSPLDefNew = new zVIEW( );
   //:VIEW lSPLDLST BASED ON LOD lSPLDLST
   zVIEW    lSPLDLST = new zVIEW( );
   //:STRING ( 64 ) szSearchName
   String   szSearchName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE lSPLDLST Multiple
   RESULT = ActivateObjectInstance( lSPLDLST, "lSPLDLST", ViewToWindow, 0, zMULTIPLE );
   //:szSearchName = wWebXfer.Root.SearchName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szSearchName;
   if ( szSearchName == null )
      sb_szSearchName = new StringBuilder( 32 );
   else
      sb_szSearchName = new StringBuilder( szSearchName );
       GetVariableFromAttribute( sb_szSearchName, mi_lTempInteger_0, 'S', 65, wWebXfer, "Root", "SearchName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szSearchName = sb_szSearchName.toString( );}
   //:nRC = SetCursorFirstEntityByString( lSPLDLST, "SubregPhysicalLabelDef", "Name", szSearchName, "" )
   nRC = SetCursorFirstEntityByString( lSPLDLST, "SubregPhysicalLabelDef", "Name", szSearchName, "" );
   //:IF nRC = zCURSOR_SET
   if ( nRC == zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Copy Label",
      //:             "The Label Name must be unique.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Copy Label", "The Label Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END


   //:ACTIVATE mSPLDefNew EMPTY
   RESULT = ActivateEmptyObjectInstance( mSPLDefNew, "mSPLDef", ViewToWindow, zSINGLE );
   //:NAME VIEW mSPLDefNew "NewSPLD"
   SetNameForView( mSPLDefNew, "NewSPLD", null, zLEVEL_TASK );
   //:BuildSPLD_FromSPLD( mSPLDefNew, mSPLDef, mSubLC )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDefNew );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_FromSPLD( mSPLDefNew, mSPLDef, mSubLC );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateSpecialFormatDef( VIEW ViewToWindow )

public int 
GOTO_UpdateSpecialFormatDef( View     ViewToWindow )
{

   return( 0 );
//    // Just for positioning
// END
} 



}
