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
import com.quinsoft.epamms.mMasLC_Object;
import com.quinsoft.epamms.lMLCATgt_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class wMLC_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public wMLC_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:DIALOG OPERATION
//:GOTO_PrecautionaryStmtDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_PrecautionaryStmtDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Precautionary Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Precautionary Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_GeneralStatement.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_GeneralStatement", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_GeneralStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_GeneralStatement" );
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_7( View     vSubtask,
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
o_fnLocalBuildQual_10( View     vSubtask,
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
o_fnLocalBuildQual_11( View     vSubtask,
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
o_fnLocalBuildQual_12( View     vSubtask,
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
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject,
                      int      MasProdID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", MasProdID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_1( View     vSubtask,
                      zVIEW    vQualObject,
                      int      MasProdID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", MasProdID );
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
   o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
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
   //:STRING ( 256 ) szName
   String   szName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szName = mMasLC.M_Usage.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
             GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mMasLC, "M_Usage", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szName = sb_szName.toString( );}
      //:IF szName = ""
      if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   //:FOR EACH mEPA.EPA_AreaOfUse
   RESULT = SetCursorFirstEntity( mEPA, "EPA_AreaOfUse", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_AreaOfUse.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_AreaOfUse", "wkSelected", "Y" ) == 0 )
      { 
         //:szName = mEPA.EPA_AreaOfUse.Name
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szName;
         if ( szName == null )
            sb_szName = new StringBuilder( 32 );
         else
            sb_szName = new StringBuilder( szName );
                   GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 257, mEPA, "EPA_AreaOfUse", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szName = sb_szName.toString( );}
         //:IF szName != ""
         if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) != 0 )
         { 
            //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
            CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
            //:mMasLC.M_Usage.UsageType = "U" // Area of Use
            SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "U" );
            //:mMasLC.M_Usage.Name = szName
            SetAttributeFromString( mMasLC, "M_Usage", "Name", szName );
         } 

         //:END
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
   o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
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
   //:STRING ( 256 ) szName
   String   szName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szName = mMasLC.M_Usage.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
             GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mMasLC, "M_Usage", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szName = sb_szName.toString( );}
      //:IF szName = ""
      if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   //:FOR EACH mEPA.EPA_ApplicationType
   RESULT = SetCursorFirstEntity( mEPA, "EPA_ApplicationType", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_ApplicationType.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_ApplicationType", "wkSelected", "Y" ) == 0 )
      { 
         //:szName = mEPA.EPA_ApplicationType.Name
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szName;
         if ( szName == null )
            sb_szName = new StringBuilder( 32 );
         else
            sb_szName = new StringBuilder( szName );
                   GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 257, mEPA, "EPA_ApplicationType", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szName = sb_szName.toString( );}
         //:IF szName != ""
         if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) != 0 )
         { 
            //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
            CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
            //:mMasLC.M_Usage.UsageType = "T" // Application Type
            SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "T" );
            //:mMasLC.M_Usage.Name = szName
            SetAttributeFromString( mMasLC, "M_Usage", "Name", szName );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mEPA, "EPA_ApplicationType", "" );
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
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
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
   //:STRING ( 256 ) szName
   String   szName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szName = mMasLC.M_Usage.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
             GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mMasLC, "M_Usage", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szName = sb_szName.toString( );}
      //:IF szName = ""
      if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   //:FOR EACH mEPA.EPA_Surface
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Surface", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_Surface.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_Surface", "wkSelected", "Y" ) == 0 )
      { 
         //:szName = mEPA.EPA_Surface.Name
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szName;
         if ( szName == null )
            sb_szName = new StringBuilder( 32 );
         else
            sb_szName = new StringBuilder( szName );
                   GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 257, mEPA, "EPA_Surface", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szName = sb_szName.toString( );}
         //:IF szName != ""
         if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) != 0 )
         { 
            //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
            CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
            //:mMasLC.M_Usage.UsageType = "S" // Surface
            SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "S" );
            //:mMasLC.M_Usage.Name = szName
            SetAttributeFromString( mMasLC, "M_Usage", "Name", szName );
         } 

         //:END
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
//:InitClaimsStmtsForInsert( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
InitClaimsStmtsForInsert( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     BASED ON LOD  mEPA
   zVIEW    mEPA = new zVIEW( );
   //:STRING ( 16 ) szClass
   String   szClass = null;
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_0 = 0;

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
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:FOR EACH mEPA.EPA_Claim
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szClass = mEPA.EPA_Claim.ClaimsClassification
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szClass;
      if ( szClass == null )
         sb_szClass = new StringBuilder( 32 );
      else
         sb_szClass = new StringBuilder( szClass );
             GetVariableFromAttribute( sb_szClass, mi_lTempInteger_0, 'S', 17, mEPA, "EPA_Claim", "ClaimsClassification", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szClass = sb_szClass.toString( );}
      //:IF szClass = "Bacteria" OR szClass = "Fungi" OR szClass = "Viruses" OR szClass = "Protozoa"
      if ( ZeidonStringCompare( szClass, 1, 0, "Bacteria", 1, 0, 17 ) == 0 || ZeidonStringCompare( szClass, 1, 0, "Fungi", 1, 0, 17 ) == 0 || ZeidonStringCompare( szClass, 1, 0, "Viruses", 1, 0, 17 ) == 0 ||
           ZeidonStringCompare( szClass, 1, 0, "Protozoa", 1, 0, 17 ) == 0 )
      { 
         //:CreateEntity( mEPA, szClass, zPOS_LAST )
         CreateEntity( mEPA, szClass, zPOS_LAST );
         //:ELSE
      } 
      else
      { 
         //:TraceLineS( "Invalid ClaimsClassification: ", szClass )
         TraceLineS( "Invalid ClaimsClassification: ", szClass );
      } 

      RESULT = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
      //:END
   } 

   //:END

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
   //:STRING ( 256 ) szName
   String   szName = null;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szName = mMasLC.M_Usage.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szName;
      if ( szName == null )
         sb_szName = new StringBuilder( 32 );
      else
         sb_szName = new StringBuilder( szName );
             GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mMasLC, "M_Usage", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szName = sb_szName.toString( );}
      //:IF szName = ""
      if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) == 0 )
      { 
         //:DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE )
         DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   //:FOR EACH mEPA.EPA_Claim
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.EPA_Claim.wkSelected = "Y"
      if ( CompareAttributeToString( mEPA, "EPA_Claim", "wkSelected", "Y" ) == 0 )
      { 
         //:szName = mEPA.EPA_Claim.Name
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szName;
         if ( szName == null )
            sb_szName = new StringBuilder( 32 );
         else
            sb_szName = new StringBuilder( szName );
                   GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 257, mEPA, "EPA_Claim", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szName = sb_szName.toString( );}
         //:IF szName != ""
         if ( ZeidonStringCompare( szName, 1, 0, "", 1, 0, 257 ) != 0 )
         { 
            //:CreateEntity( mMasLC, "M_Usage", zPOS_LAST )
            CreateEntity( mMasLC, "M_Usage", zPOS_LAST );
            //:mMasLC.M_Usage.UsageType = "C" // Claim
            SetAttributeFromString( mMasLC, "M_Usage", "UsageType", "C" );
            //:mMasLC.M_Usage.ClaimsClassification = mEPA.EPA_Claim.ClaimsClassification
            SetAttributeFromAttribute( mMasLC, "M_Usage", "ClaimsClassification", mEPA, "EPA_Claim", "ClaimsClassification" );
            //:mMasLC.M_Usage.Name = szName
            SetAttributeFromString( mMasLC, "M_Usage", "Name", szName );
         } 

         //:END
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
//:GOTO_SelectRemoveMktgEntries( VIEW ViewToWindow )

//:   VIEW mMasLC  REGISTERED AS mMasLC
public int 
GOTO_SelectRemoveMktgEntries( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Initialize the mMasLC for selecting Statement Usage entries.
   //:mMasLC.MasterLabelContent.wSelectedUsageType = "M"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_FootnoteStmtDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_FootnoteStmtDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Footnote Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Footnote Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_UsageFootnote.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_UsageFootnote", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_UsageFootnote"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_UsageFootnote" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_DilutionChartItemDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_DilutionChartItemDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Dilution Chart Item"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Dilution Chart Item" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_DilutionChartEntry.ProductAmountText 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_DilutionChartEntry", "ProductAmountText" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_DilutionChartEntry"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_DilutionChartEntry" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_DilutionGroupDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_DilutionGroupDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Dilution Group"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Dilution Group" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_DilutionGroup.DilutionRatioText 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_DilutionGroup", "DilutionRatioText" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_DilutionGroup"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_DilutionGroup" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_DilutionGroupItemDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_DilutionGroupItemDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Dilution Group Item"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Dilution Group Item" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_DilutionGroupItem.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_DilutionGroupItem", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_DilutionGroupItem"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_DilutionGroupItem" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SETALL_MLC_UsageSource( VIEW ViewToWindow )

//:   VIEW mMasLC  REGISTERED AS mMasLC
public int 
SETALL_MLC_UsageSource( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:mMasLC.M_Usage.wSelected = "Y"
      SetAttributeFromString( mMasLC, "M_Usage", "wSelected", "Y" );
      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_DirsForUseStatementDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_DirsForUseStatementDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Directions for Use Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Directions for Use Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_DirectionsForUseStatement.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_DirectionsForUseStatement", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_DirectionsForUseStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_DirectionsForUseStatement" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_MarketingSectionDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_MarketingSectionDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Marketing Section"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Marketing Section" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_MarketingSection.Name 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_MarketingSection", "Name" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_MarketingSection"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_MarketingSection" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMLC_UsageEntries( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
SelectMLC_UsageEntries( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Include each selected Usage Source entry in the Target subobject.
   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.wSelected = "Y"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "wSelected", "Y" ) == 0 )
      { 
         //:// The target entity depends on the type of select set earlier.
         //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "DC"   // Dir for Use Driving Claim
         if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "DC" ) == 0 )
         { 
            //:SET CURSOR FIRST mMasLC.M_DrivingUsage WHERE mMasLC.M_DrivingUsage.ID = mMasLC.M_Usage.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "M_Usage", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DrivingUsage", "ID", lTempInteger_0, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:INCLUDE mMasLC.M_DrivingUsage FROM mMasLC.M_Usage 
               RESULT = IncludeSubobjectFromSubobject( mMasLC, "M_DrivingUsage", mMasLC, "M_Usage", zPOS_AFTER );
            } 

            //:END 
         } 

         //:END
         //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "D"   // Dir for Use Statement
         if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "D" ) == 0 )
         { 
            //:SET CURSOR FIRST mMasLC.M_DirectionsUsage WITHIN mMasLC.M_DirectionsForUseStatement 
            //:           WHERE mMasLC.M_DirectionsUsage.ID = mMasLC.M_Usage.ID 
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mMasLC, "M_Usage", "ID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DirectionsUsage", "ID", lTempInteger_1, "M_DirectionsForUseStatement" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:SET CURSOR LAST mMasLC.M_DirectionsUsageOrdering 
               RESULT = SetCursorLastEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
               //:CREATE ENTITY  mMasLC.M_DirectionsUsageOrdering
               RESULT = CreateEntity( mMasLC, "M_DirectionsUsageOrdering", zPOS_AFTER );
               //:INCLUDE mMasLC.M_DirectionsUsage FROM mMasLC.M_Usage 
               RESULT = IncludeSubobjectFromSubobject( mMasLC, "M_DirectionsUsage", mMasLC, "M_Usage", zPOS_AFTER );
            } 

            //:END
         } 

         //:END
         //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "M"   // Marketing Statement
         if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "M" ) == 0 )
         { 
            //:SET CURSOR FIRST mMasLC.M_MarketingUsage WITHIN mMasLC.M_MarketingStatement 
            //:           WHERE mMasLC.M_MarketingUsage.ID = mMasLC.M_Usage.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "M_Usage", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingUsage", "ID", lTempInteger_2, "M_MarketingStatement" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:SET CURSOR LAST mMasLC.M_MarketingUsageOrdering 
               RESULT = SetCursorLastEntity( mMasLC, "M_MarketingUsageOrdering", "" );
               //:CREATE ENTITY  mMasLC.M_MarketingUsageOrdering
               RESULT = CreateEntity( mMasLC, "M_MarketingUsageOrdering", zPOS_AFTER );
               //:INCLUDE mMasLC.M_MarketingUsage FROM mMasLC.M_Usage 
               RESULT = IncludeSubobjectFromSubobject( mMasLC, "M_MarketingUsage", mMasLC, "M_Usage", zPOS_AFTER );
            } 

            //:END
         } 

         //:END

         //:mMasLC.M_Usage.wSelected = ""
         SetAttributeFromString( mMasLC, "M_Usage", "wSelected", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
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

   //:// Remove depends on the target entity.
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "DC"   // Dir for Use Driving Claim
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "DC" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_DrivingUsage 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DrivingUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mMasLC.M_DrivingUsage.wSelected = "Y"
         if ( CompareAttributeToString( mMasLC, "M_DrivingUsage", "wSelected", "Y" ) == 0 )
         { 
            //:EXCLUDE  mMasLC.M_DrivingUsage NONE
            RESULT = ExcludeEntity( mMasLC, "M_DrivingUsage", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mMasLC, "M_DrivingUsage", "" );
         //:END
      } 

      //:END
   } 

   //:END
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "D"   // Dir for Use Statement
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "D" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_DirectionsUsageOrdering 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mMasLC.M_DirectionsUsageOrdering.wSelected = "Y"
         if ( CompareAttributeToString( mMasLC, "M_DirectionsUsageOrdering", "wSelected", "Y" ) == 0 )
         { 
            //:DELETE ENTITY mMasLC.M_DirectionsUsageOrdering NONE
            RESULT = DeleteEntity( mMasLC, "M_DirectionsUsageOrdering", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
         //:END
      } 

      //:END
   } 

   //:END
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "M"   // Marketing Statement
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "M" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mMasLC.M_MarketingUsageOrdering.wSelected = "Y"
         if ( CompareAttributeToString( mMasLC, "M_MarketingUsageOrdering", "wSelected", "Y" ) == 0 )
         { 
            //:DELETE ENTITY mMasLC.M_MarketingUsageOrdering NONE
            RESULT = DeleteEntity( mMasLC, "M_MarketingUsageOrdering", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mMasLC, "M_MarketingUsageOrdering", "" );
         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CLEAR_MLC_UsageTarget( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
CLEAR_MLC_UsageTarget( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Clear depends on the target entity.
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "DC"   // Dir for Use Driving Claim
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "DC" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_DrivingUsage 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DrivingUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mMasLC.M_DrivingUsage.wSelected = ""
         SetAttributeFromString( mMasLC, "M_DrivingUsage", "wSelected", "" );
         RESULT = SetCursorNextEntity( mMasLC, "M_DrivingUsage", "" );
      } 

      //:END
   } 

   //:END
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "D"   // Dir for Use Statement
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "D" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_DirectionsUsageOrdering 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mMasLC.M_DirectionsUsageOrdering.wSelected = ""
         SetAttributeFromString( mMasLC, "M_DirectionsUsageOrdering", "wSelected", "" );
         RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
      } 

      //:END
   } 

   //:END
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "M"   // Marketing Statement
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "M" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mMasLC.M_MarketingUsageOrdering.wSelected = ""
         SetAttributeFromString( mMasLC, "M_MarketingUsageOrdering", "wSelected", "" );
         RESULT = SetCursorNextEntity( mMasLC, "M_MarketingUsageOrdering", "" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SETALL_MLC_UsageTarget( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
SETALL_MLC_UsageTarget( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Set All depends on the target entity.
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "DC"   // Dir for Use Driving Claim
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "DC" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_DrivingUsage 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DrivingUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mMasLC.M_DrivingUsage.wSelected = "Y"
         SetAttributeFromString( mMasLC, "M_DrivingUsage", "wSelected", "Y" );
         RESULT = SetCursorNextEntity( mMasLC, "M_DrivingUsage", "" );
      } 

      //:END
   } 

   //:END
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "D"   // Dir for Use Statement
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "D" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_DirectionsUsageOrdering 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mMasLC.M_DirectionsUsageOrdering.wSelected = "Y"
         SetAttributeFromString( mMasLC, "M_DirectionsUsageOrdering", "wSelected", "Y" );
         RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
      } 

      //:END
   } 

   //:END
   //:IF mMasLC.MasterLabelContent.wSelectedUsageType = "M"   // Marketing Statement
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "M" ) == 0 )
   { 
      //:FOR EACH mMasLC.M_MarketingUsageOrdering 
      RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingUsageOrdering", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:mMasLC.M_MarketingUsageOrdering.wSelected = "Y"
         SetAttributeFromString( mMasLC, "M_MarketingUsageOrdering", "wSelected", "Y" );
         RESULT = SetCursorNextEntity( mMasLC, "M_MarketingUsageOrdering", "" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CLEAR_MLC_UsageSource( VIEW ViewToWindow )

//:   VIEW mMasLC  REGISTERED AS mMasLC
public int 
CLEAR_MLC_UsageSource( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:mMasLC.M_Usage.wSelected = ""
      SetAttributeFromString( mMasLC, "M_Usage", "wSelected", "" );
      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_DirsForUseStatementAdd( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_DirsForUseStatementAdd( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Add new temporal entity as last statement under section.
   //:SET CURSOR LAST mMasLC.M_DirectionsForUseStatement 
   RESULT = SetCursorLastEntity( mMasLC, "M_DirectionsForUseStatement", "" );
   //:CreateTemporalEntity( mMasLC, "M_DirectionsForUseStatement", zPOS_AFTER )
   CreateTemporalEntity( mMasLC, "M_DirectionsForUseStatement", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectRemoveDUEntries( VIEW ViewToWindow )

//:   VIEW mMasLC  REGISTERED AS mMasLC
public int 
GOTO_SelectRemoveDUEntries( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Initialize the mMasLC for selecting Statement Usage entries.
   //:mMasLC.MasterLabelContent.wSelectedUsageType = "D"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewUsage( VIEW ViewToWindow )

//:   VIEW  mMasLC REGISTERED AS mMasLC
public int 
SaveAddNewUsage( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptSubobject( mMasLC, "M_Usage" )
   nRC = AcceptSubobject( mMasLC, "M_Usage" );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Usage Statement",
      //:             "Error accepting Statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Usage Statement", "Error accepting Statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:CreateTemporalEntity( mMasLC, "M_Usage", zPOS_AFTER )
   CreateTemporalEntity( mMasLC, "M_Usage", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteUsageEntry( VIEW ViewToWindow )

//:/*
//:   VIEW mMasLC REGISTERED AS mMasLC
//:   VIEW mMasLC2 BASED ON LOD mMasLC
//:   SHORT nContinue
//:   CreateViewFromView( mMasLC2, mMasLC )
//:   // Delete all selected Usage entries.
//:   FOR EACH mMasLC.M_Usage
//:      IF mMasLC.M_Usage.wSelected = "Y"
//:         nContinue = 1
//:         SET CURSOR FIRST mMasLC2.M_MarketingSection
//:         LOOP WHILE RESULT >= zCURSOR_SET AND nContinue > 0
//:            SET CURSOR FIRST mMasLC2.M_MarketingStatement
//:            LOOP WHILE RESULT >= zCURSOR_SET AND nContinue > 0
//:               SET CURSOR FIRST mMasLC2.M_MarketingUsage  WHERE mMasLC2.M_MarketingUsage.ID = mMasLC.M_Usage.ID 
//:               IF RESULT >= zCURSOR_SET
//:                  DELETE ENTITY mMasLC2.M_MarketingUsageOrdering NONE
//:                  nContinue = -1
//:                  RESULT = zCURSOR_UNCHANGED
//:               END
//:               IF nContinue > 0
//:                  SET CURSOR NEXT mMasLC2.M_MarketingStatement
//:               END
//:            END
//:            IF nContinue > 0
//:               SET CURSOR NEXT mMasLC2.M_MarketingSection
//:            END
//:         END
//:         DELETE ENTITY mMasLC.M_Usage NONE
//:      END
//:   END
//:*/

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
ConfirmDeleteUsageEntry( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Delete all selected Usage entries.
   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.wSelected = "Y"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "wSelected", "Y" ) == 0 )
      { 
         //:// Delete any Usage entries that have been tied to a Marketing Statement.
         //:FOR EACH mMasLC.M_MarketingStatement WITHIN mMasLC.MasterLabelContent 
         RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingStatement", "MasterLabelContent" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:SET CURSOR FIRST mMasLC.M_MarketingUsage WITHIN mMasLC.M_MarketingStatement 
            //:           WHERE mMasLC.M_MarketingUsage.ID = mMasLC.M_Usage.ID 
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                         GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "M_Usage", "ID" );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingUsage", "ID", lTempInteger_0, "M_MarketingStatement" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:DELETE ENTITY mMasLC.M_MarketingUsageOrdering  
               RESULT = DeleteEntity( mMasLC, "M_MarketingUsageOrdering", zPOS_NEXT );
            } 

            RESULT = SetCursorNextEntity( mMasLC, "M_MarketingStatement", "MasterLabelContent" );
            //:END 
         } 

         //:END

         //:// Delete any Usage entries that have been tied to a Directions for Use Statement or that 
         //:// drive a Directions for Use Section.
         //:FOR EACH mMasLC.M_DirectionsForUseSection
         RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseSection", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:FOR EACH mMasLC.M_DirectionsForUseStatement
            RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseStatement", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:SET CURSOR FIRST mMasLC.M_DirectionsUsage WITHIN mMasLC.M_DirectionsForUseStatement 
               //:           WHERE mMasLC.M_DirectionsUsage.ID = mMasLC.M_Usage.ID 
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                               GetIntegerFromAttribute( mi_lTempInteger_1, mMasLC, "M_Usage", "ID" );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DirectionsUsage", "ID", lTempInteger_1, "M_DirectionsForUseStatement" );
               //:IF RESULT >= zCURSOR_SET
               if ( RESULT >= zCURSOR_SET )
               { 
                  //:DELETE ENTITY mMasLC.M_DirectionsUsageOrdering   
                  RESULT = DeleteEntity( mMasLC, "M_DirectionsUsageOrdering", zPOS_NEXT );
               } 

               RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseStatement", "" );
               //:END 
            } 

            //:END
            //:SET CURSOR FIRST mMasLC.M_DrivingUsage WHERE mMasLC.M_DrivingUsage.ID = mMasLC.M_Usage.ID 
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "M_Usage", "ID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DrivingUsage", "ID", lTempInteger_2, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:EXCLUDE mMasLC.M_DrivingUsage 
               RESULT = ExcludeEntity( mMasLC, "M_DrivingUsage", zREPOS_AFTER );
            } 

            RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseSection", "" );
            //:END
         } 

         //:END

         //:// Delete the actual Usage entry.
         //:DELETE ENTITY mMasLC.M_Usage NONE 
         RESULT = DeleteEntity( mMasLC, "M_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteUsageEntry( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
CancelDeleteUsageEntry( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Cancel delete and clear selected entries.
   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.wSelected = "Y"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "wSelected", "Y" ) == 0 )
      { 
         //:mMasLC.M_Usage.wSelected = "" 
         SetAttributeFromString( mMasLC, "M_Usage", "wSelected", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:DELETE_SelectedUsageEntries( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
DELETE_SelectedUsageEntries( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.wSelected  = "Y"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "wSelected", "Y" ) == 0 )
      { 
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:   
      //:END
   } 

   //:END
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

   //:IF mMasLC.M_HumanHazardSection DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_HumanHazardSection" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY mMasLC.M_HumanHazardSection  
      RESULT = CreateEntity( mMasLC, "M_HumanHazardSection", zPOS_AFTER );
      //:mMasLC.M_HumanHazardSection.PrecautionaryStatement = "See {{Precautionary Panel Position}} Panel {{Precautionary Label Position}} for Precautionary Statements"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "PrecautionaryStatement", "See {{Precautionary Panel Position}} Panel {{Precautionary Label Position}} for Precautionary Statements" );
      //:mMasLC.M_HumanHazardSection.PanelLoc1 = "[Back]"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "PanelLoc1", "[Back]" );
      //:mMasLC.M_HumanHazardSection.PanelLoc2 = "[Side]"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "PanelLoc2", "[Side]" );
      //:mMasLC.M_HumanHazardSection.PanelLoc3 = "[Left]"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "PanelLoc3", "[Left]" );
      //:mMasLC.M_HumanHazardSection.PanelLoc4 = "[Right]"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "PanelLoc4", "[Right]" );
      //:mMasLC.M_HumanHazardSection.LabelLoc1 = "[of Panel]"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "LabelLoc1", "[of Panel]" );
      //:mMasLC.M_HumanHazardSection.LabelLoc2 = "[below]"
      SetAttributeFromString( mMasLC, "M_HumanHazardSection", "LabelLoc2", "[below]" );
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
//:ConfirmDeleteMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
ConfirmDeleteMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mMasProd, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLCDelete"
   SetNameForView( mMasLC, "mMasLCDelete", null, zLEVEL_TASK );
   //:DELETE ENTITY mMasLC.MasterLabelContent 
   RESULT = DeleteEntity( mMasLC, "MasterLabelContent", zPOS_NEXT );
   //:COMMIT  mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_StorageDispSectionUpdate( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_StorageDispSectionUpdate( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Create Temporal subobject version and create a Container entry if there isn't one currently.
   //:CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalSection" )
   CreateTemporalSubobjectVersion( mMasLC, "M_StorageDisposalSection" );
   //:IF mMasLC.M_StorageDisposalDrivingConVol DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_StorageDisposalDrivingConVol" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY mMasLC.M_StorageDisposalDrivingConVol  
      RESULT = CreateEntity( mMasLC, "M_StorageDisposalDrivingConVol", zPOS_AFTER );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DELETE_StorageDisposalContainer( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
DELETE_StorageDisposalContainer( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mMasLC.M_StorageDisposalDrivingConVol 
   RESULT = DeleteEntity( mMasLC, "M_StorageDisposalDrivingConVol", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterProduct( VIEW ViewToWindow )

//:   VIEW mMasProdLST REGISTERED AS mMasProdLST
public int 
ConfirmDeleteMasterProduct( View     ViewToWindow )
{
   zVIEW    mMasProdLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd    REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mMasProdLST, "mMasProdLST", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:IF mMasProd.SubregProduct EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mMasProd, "SubregProduct" );
   if ( lTempInteger_0 == 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Delete Master Product",
      //:             "The Master Product has associated Subregistrant Products.  It cannot be deleted",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Master Product", "The Master Product has associated Subregistrant Products.  It cannot be deleted", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:DELETE ENTITY mMasProd.MasterProduct
   RESULT = DeleteEntity( mMasProd, "MasterProduct", zPOS_NEXT );
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );

   //:InitListMasterProducts( ViewToWindow )
   InitListMasterProducts( ViewToWindow );
   return( 0 );
// END
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
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateMLC( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
GOTO_UpdateMLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;

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
   o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:OrderEntityForView( mMasLC, "M_Usage", "UsageType A ClaimsClassification A Name A" )
   OrderEntityForView( mMasLC, "M_Usage", "UsageType A ClaimsClassification A Name A" );
   //:SET CURSOR FIRST mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );

   //:// Create/Include Hazard, Precautionary and First Aid derived sections.

   //:// First Aid
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "F"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "F", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_GeneralSection 
      RESULT = CreateEntity( mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:mMasLC.M_GeneralSection.SectionType = "F" // First Aid
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "F" );
      //:mMasLC.M_GeneralSection.Title = "FIRST AID"
      SetAttributeFromString( mMasLC, "M_GeneralSection", "Title", "FIRST AID" );
   } 

   //:END 
   //:INCLUDE mMasLC.MI_FirstAidSection FROM mMasLC.M_GeneralSection 
   RESULT = IncludeSubobjectFromSubobject( mMasLC, "MI_FirstAidSection", mMasLC, "M_GeneralSection", zPOS_AFTER );

   //:// Precautionary
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "P"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "P", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_GeneralSection 
      RESULT = CreateEntity( mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:mMasLC.M_GeneralSection.SectionType = "P" // Precautionary
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "P" );
   } 

   //:END 
   //:INCLUDE mMasLC.MI_PrecautionarySection FROM mMasLC.M_GeneralSection 
   RESULT = IncludeSubobjectFromSubobject( mMasLC, "MI_PrecautionarySection", mMasLC, "M_GeneralSection", zPOS_AFTER );

   //:// Hazard
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "E"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "E", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_GeneralSection 
      RESULT = CreateEntity( mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:mMasLC.M_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "E" );
   } 

   //:END 
   //:INCLUDE mMasLC.MI_HazardSection FROM mMasLC.M_GeneralSection 
   RESULT = IncludeSubobjectFromSubobject( mMasLC, "MI_HazardSection", mMasLC, "M_GeneralSection", zPOS_AFTER );

   //:// Convert any original Usage entries to the new UsageType subobject.
   //:FOR EACH mMasLC.OrigM_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "OrigM_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = mMasLC.OrigM_Usage.UsageType 
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, mMasLC, "OrigM_Usage", "UsageType" );
      szTempString_0 = sb_szTempString_0.toString( );}
      RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", szTempString_0, "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY mMasLC.M_UsageType 
         RESULT = CreateEntity( mMasLC, "M_UsageType", zPOS_AFTER );
         //:mMasLC.M_UsageType.UsageType = mMasLC.OrigM_Usage.UsageType  
         SetAttributeFromAttribute( mMasLC, "M_UsageType", "UsageType", mMasLC, "OrigM_Usage", "UsageType" );
      } 

      //:END 
      //:CREATE ENTITY mMasLC.M_Usage 
      RESULT = CreateEntity( mMasLC, "M_Usage", zPOS_AFTER );
      //:SetMatchingAttributesByName( mMasLC, "M_Usage", mMasLC, "OrigM_Usage", zSET_NULL )
      SetMatchingAttributesByName( mMasLC, "M_Usage", mMasLC, "OrigM_Usage", zSET_NULL );
      //:DELETE ENTITY mMasLC.OrigM_Usage NONE  
      RESULT = DeleteEntity( mMasLC, "OrigM_Usage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mMasLC, "OrigM_Usage", "" );
   } 

   //:END

   //:// Delete any M_DirectionsUsageOrdering entities without children.
   //:FOR EACH mMasLC.M_DirectionsForUseSection 
   RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mMasLC.M_DirectionsForUseStatement 
      RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsForUseStatement", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FOR EACH mMasLC.M_DirectionsUsageOrdering 
         RESULT = SetCursorFirstEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF mMasLC.M_DirectionsUsage DOES NOT EXIST
            lTempInteger_1 = CheckExistenceOfEntity( mMasLC, "M_DirectionsUsage" );
            if ( lTempInteger_1 != 0 )
            { 
               //:DELETE ENTITY mMasLC.M_DirectionsUsageOrdering NONE 
               RESULT = DeleteEntity( mMasLC, "M_DirectionsUsageOrdering", zREPOS_NONE );
            } 

            RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsUsageOrdering", "" );
            //:END
         } 

         RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseStatement", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_DirectionsForUseSection", "" );
      //:END
   } 

   //:END

   //:// Make sure Usage Entries are sorted.
   //:FOR EACH mMasLC.M_UsageType 
   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageType", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:OrderEntityForView( mMasLC, "M_Usage", "ClaimsClassification A Name A" )
      OrderEntityForView( mMasLC, "M_Usage", "ClaimsClassification A Name A" );
      RESULT = SetCursorNextEntity( mMasLC, "M_UsageType", "" );
   } 

   //:END
   //:SET CURSOR FIRST mMasLC.M_UsageType  
   RESULT = SetCursorFirstEntity( mMasLC, "M_UsageType", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NEW_MLC( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
NEW_MLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mMasLC EMPTY 
   RESULT = ActivateEmptyObjectInstance( mMasLC, "mMasLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mMasLC "mMasLC" 
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:CREATE ENTITY mMasLC.MasterLabelContent 
   RESULT = CreateEntity( mMasLC, "MasterLabelContent", zPOS_AFTER );
   //:INCLUDE mMasLC.MasterProduct FROM mMasProd.MasterProduct 
   RESULT = IncludeSubobjectFromSubobject( mMasLC, "MasterProduct", mMasProd, "MasterProduct", zPOS_AFTER );
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
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:// Generate new MLC from the selected MLC.
   //:ACTIVATE OriginalMLC WHERE OriginalMLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mMasProd, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( OriginalMLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW OriginalMLC "OriginalMLC"
   SetNameForView( OriginalMLC, "OriginalMLC", null, zLEVEL_TASK );

   //:ACTIVATE NewMLC EMPTY
   RESULT = ActivateEmptyObjectInstance( NewMLC, "mMasLC", ViewToWindow, zSINGLE );
   //:NAME VIEW NewMLC "mMasLC"
   SetNameForView( NewMLC, "mMasLC", null, zLEVEL_TASK );

   //:BuildNewMLC_Version( NewMLC, OriginalMLC )    // Create NewMLC from OriginalMLC
   {
    mMasLC_Object m_mMasLC_Object = new mMasLC_Object( NewMLC );
    m_mMasLC_Object.omMasLC_BuildNewMLC_Version( NewMLC, OriginalMLC );
    // m_mMasLC_Object = null;  // permit gc  (unnecessary)
   }
   //:NewMLC.MasterLabelContent.Finalized = "N"
   SetAttributeFromString( NewMLC, "MasterLabelContent", "Finalized", "N" );
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
   o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lID );
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
      o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
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
//:CANCEL_MasterProduct( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
CANCEL_MasterProduct( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:TraceLineS( "$$$$$ Before Drop", "" )
   TraceLineS( "$$$$$ Before Drop", "" );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:TraceLineS( "$$$$$ After Drop", "" ) 
   TraceLineS( "$$$$$ After Drop", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SAVE_MasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SAVE_MasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
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
   int      lTempInteger_4 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
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
   //://TraceLineS( "Product Name: ", szProductName )
   //://TraceLineI( "Product Name Length: ", lProductNameLth )
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
         //:IF SetEntityCursor( lPrimReg, "MasterProduct", "Name", lControl,
         //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( lPrimReg, "MasterProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
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
   //://TraceLineS( "Product Number: ", szProductNumber )
   //://TraceLineI( "Product Number Length: ", lProductNumberLth )
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
         //:IF SetEntityCursor( lPrimReg, "MasterProduct", "Number", lControl,
         //:                    szProductNumber, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_3 = SetEntityCursor( lPrimReg, "MasterProduct", "Number", lControl, szProductNumber, "", "", 0, "", "" );
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
   //:IF mMasProd.PrimaryRegistrant DOES NOT EXIST
   lTempInteger_4 = CheckExistenceOfEntity( mMasProd, "PrimaryRegistrant" );
   if ( lTempInteger_4 != 0 )
   { 
      //:IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant",
      //:                               lPrimReg, "PrimaryRegistrant", zPOS_BEFORE )
      IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant", lPrimReg, "PrimaryRegistrant", zPOS_BEFORE );
   } 

   //:END
   //:// AcceptSubobject( mMasProd, "MasterProduct" )
   //:// AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptNewMasterProduct" )
   //:COMMIT mMasProd
   RESULT = CommitObjectInstance( mMasProd );

   //:// fnInitListMasterProducts( ViewToWindow, 1 )
   //:InitListMasterProducts( ViewToWindow )
   InitListMasterProducts( ViewToWindow );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_UpdateMasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
GOTO_UpdateMasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProdLST REGISTERED AS mMasProdLST
   zVIEW    mMasProdLST = new zVIEW( );
   //:VIEW mMasProd    BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProdLST, "mMasProdLST", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = mMasProdLST.MasterProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mMasProdLST, "MasterProduct", "ID" );
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
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NEW_MasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NEW_MasterProduct( View     ViewToWindow )
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

   //:mMasProd.MasterProduct.ChemicalFamily = "DQ" // QuatDisinfectant
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
//:InitListMasterProducts( VIEW ViewToWindow )

//:   VIEW lPrimReg    REGISTERED AS lPrimReg
public int 
InitListMasterProducts( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProdLST BASED ON LOD mMasProd
   zVIEW    mMasProdLST = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mMasProdLST RootOnlyMultiple WHERE mMasProdLST.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasProdLST, "mMasProd", ViewToWindow, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasProdLST "mMasProdLST"
   SetNameForView( mMasProdLST, "mMasProdLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteComponent( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
CancelDeleteComponent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType = ""
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "" );
   //:mMasLC.MasterLabelContent.wDeleteText = ""
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteText", "" );
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteComponent( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
ConfirmDeleteComponent( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// The entity to delete is passed in the wDeleteEntityName attribute.
   //:szEntityName = mMasLC.MasterLabelContent.wDeleteEntityName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, mMasLC, "MasterLabelContent", "wDeleteEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:DeleteEntity( mMasLC, szEntityName, zREPOS_NONE )
   DeleteEntity( mMasLC, szEntityName, zREPOS_NONE );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAddNewStorDispStmt( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
AcceptAddNewStorDispStmt( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptSubobject( mMasLC, "M_StorageDisposalStatement" )
   nRC = AcceptSubobject( mMasLC, "M_StorageDisposalStatement" );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal statement",
      //:             "The Storage and Disposal Statement contains errors.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal statement", "The Storage and Disposal Statement contains errors.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   //:CreateTemporalEntity( mMasLC, "M_StorageDisposalStatement", zPOS_AFTER )
   CreateTemporalEntity( mMasLC, "M_StorageDisposalStatement", zPOS_AFTER );
   //:mMasLC.M_StorageDisposalStatement.NotForUseType = "NA"
   SetAttributeFromString( mMasLC, "M_StorageDisposalStatement", "NotForUseType", "NA" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAddNewStorDispSect( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
AcceptAddNewStorDispSect( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptSubobject( mMasLC, "M_StorageDisposalSection" )
   nRC = AcceptSubobject( mMasLC, "M_StorageDisposalSection" );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal Section",
      //:             "The Storage and Disposal Section contains errors.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Storage and Disposal Section", "The Storage and Disposal Section contains errors.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   //:CreateTemporalEntity( mMasLC, "M_StorageDisposalSection", zPOS_AFTER )
   CreateTemporalEntity( mMasLC, "M_StorageDisposalSection", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_StorageDispStatementDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_StorageDispStatementDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Storage and Disposal Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Storage and Disposal Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_StorageDisposalStatement.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_StorageDisposalStatement", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_StorageDisposalStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_StorageDisposalStatement" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CleanStorDispStmtHTML( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
CleanStorDispStmtHTML( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:RemoveFormattingFromHTML_Attr( mMasLC, "M_StorageDisposalStatement", "Text", "p,strong,sup,sub" )
   m_ZDRVROPR.RemoveFormattingFromHTML_Attr( mMasLC, "M_StorageDisposalStatement", "Text", "p,strong,sup,sub" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_StorageDispSectionDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_StorageDispSectionDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Storage and Disposal Section"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Storage and Disposal Section" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_StorageDisposalSection.Title 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_StorageDisposalSection", "Title" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_StorageDisposalSection"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_StorageDisposalSection" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_IngredientsStatementDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_IngredientsStatementDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Ingredients Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Ingredients Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_IngredientsStatement.ChemicalName 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_IngredientsStatement", "ChemicalName" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_IngredientsStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_IngredientsStatement" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAddNewIngredient( VIEW ViewToWindow )

//:   VIEW  mMasLC   REGISTERED AS mMasLC
public int 
AcceptAddNewIngredient( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 256 ) szPrompt
   String   szPrompt = null;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Accept Ingredients Statement and add new temporal one.
   //:nRC = AcceptSubobject( mMasLC, "M_IngredientsStatement" )
   nRC = AcceptSubobject( mMasLC, "M_IngredientsStatement" );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save And Add New Ingredients Statement",
      //:             "Error saving ingredient statement.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save And Add New Ingredients Statement", "Error saving ingredient statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:CreateTemporalEntity( mMasLC, "M_IngredientsStatement", zPOS_AFTER )
   CreateTemporalEntity( mMasLC, "M_IngredientsStatement", zPOS_AFTER );
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

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

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
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAndReturnMLC( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
SaveAndReturnMLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 ) szFound
   String   szFound = null;
   //:INTEGER      MasProdID
   int      MasProdID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Reuse Save operation and drop current MLC.
   //:SaveMLC( ViewToWindow )
   SaveMLC( ViewToWindow );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );

   //:// Reactivate mMasProd to get updated list of MasterLabelContent entries.
   //:MasProdID = mMasProd.MasterProduct.ID 
   {MutableInt mi_MasProdID = new MutableInt( MasProdID );
       GetIntegerFromAttribute( mi_MasProdID, mMasProd, "MasterProduct", "ID" );
   MasProdID = mi_MasProdID.intValue( );}
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = MasProdID
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, MasProdID );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelAndReturnMLC( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
CancelAndReturnMLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 1 ) szFound
   String   szFound = null;
   //:INTEGER      MasProdID
   int      MasProdID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Drop current MLC.
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );

   //:// Reactivate mMasProd to get updated list of MasterLabelContent entries, in case MLC was saved earlier.
   //:MasProdID = mMasProd.MasterProduct.ID 
   {MutableInt mi_MasProdID = new MutableInt( MasProdID );
       GetIntegerFromAttribute( mi_MasProdID, mMasProd, "MasterProduct", "ID" );
   MasProdID = mi_MasProdID.intValue( );}
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = MasProdID
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, MasProdID );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveMLC( VIEW ViewToWindow )

//:   VIEW mMasProd REGISTERED AS mMasProd
public int 
SaveMLC( View     ViewToWindow )
{
   zVIEW    mMasProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:STRING ( 1 ) szFound
   String   szFound = null;
   //:INTEGER      MasProdID
   int      MasProdID = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section name is not blank and is unique.
   //:IF mMasLC.MasterLabelContent.Version = ""
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "Version", "" ) == 0 )
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
      //:// See if there is a Version in the mMasProd object with the current Version value that is not the current mMasLC.
      //:szFound = ""
       {StringBuilder sb_szFound;
      if ( szFound == null )
         sb_szFound = new StringBuilder( 32 );
      else
         sb_szFound = new StringBuilder( szFound );
            ZeidonStringCopy( sb_szFound, 1, 0, "", 1, 0, 2 );
      szFound = sb_szFound.toString( );}
      //:FOR EACH mMasProd.MasterLabelContent 
      RESULT = SetCursorFirstEntity( mMasProd, "MasterLabelContent", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mMasProd.MasterLabelContent.Version = mMasLC.MasterLabelContent.Version AND 
         //:   mMasProd.MasterLabelContent.ID     != mMasLC.MasterLabelContent.ID  
         if ( CompareAttributeToAttribute( mMasProd, "MasterLabelContent", "Version", mMasLC, "MasterLabelContent", "Version" ) == 0 && CompareAttributeToAttribute( mMasProd, "MasterLabelContent", "ID", mMasLC, "MasterLabelContent", "ID" ) != 0 )
         { 

            //:szFound = "Y"
             {StringBuilder sb_szFound;
            if ( szFound == null )
               sb_szFound = new StringBuilder( 32 );
            else
               sb_szFound = new StringBuilder( szFound );
                        ZeidonStringCopy( sb_szFound, 1, 0, "Y", 1, 0, 2 );
            szFound = sb_szFound.toString( );}
         } 

         RESULT = SetCursorNextEntity( mMasProd, "MasterLabelContent", "" );
         //:END
      } 

      //:END
      //:IF szFound = "Y"
      if ( ZeidonStringCompare( szFound, 1, 0, "Y", 1, 0, 2 ) == 0 )
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
   } 

   //:END

   //:IF mMasLC.MasterLabelContent.Finalized = ""
   if ( CompareAttributeToString( mMasLC, "MasterLabelContent", "Finalized", "" ) == 0 )
   { 
      //:mMasLC.MasterLabelContent.Finalized = "N"
      SetAttributeFromString( mMasLC, "MasterLabelContent", "Finalized", "N" );
      //:mMasLC.MasterLabelContent.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
      SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
      //:mMasLC.MasterLabelContent.RevisionDate = wWebXfer.Root.dCurrentDateTime
      SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "RevisionDate", wWebXfer, "Root", "dCurrentDateTime" );
   } 

   //:END
   //:IF mMasLC.MasterProduct DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "MasterProduct" );
   if ( lTempInteger_0 != 0 )
   { 
      //:IncludeSubobjectFromSubobject( mMasLC, "MasterProduct",
      //:                               mMasProd, "MasterProduct", zPOS_BEFORE )
      IncludeSubobjectFromSubobject( mMasLC, "MasterProduct", mMasProd, "MasterProduct", zPOS_BEFORE );
   } 

   //:END
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:COMMIT mMasLC
   RESULT = CommitObjectInstance( mMasLC );
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
//:GOTO_StorageDispStatementAdd( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_StorageDispStatementAdd( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// We are creating the new temporal entity in this operation, rather than as auto action, so that
   //:// the NotForUseType attribute can be set, since it's currently required in the database.
   //:CreateTemporalEntity( mMasLC, "M_StorageDisposalStatement", zPOS_AFTER )
   CreateTemporalEntity( mMasLC, "M_StorageDisposalStatement", zPOS_AFTER );
   //:mMasLC.M_StorageDisposalStatement.NotForUseType = "NA"
   SetAttributeFromString( mMasLC, "M_StorageDisposalStatement", "NotForUseType", "NA" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ADD_StorageDisposalContainer( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
ADD_StorageDisposalContainer( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CREATE ENTITY mMasLC.M_StorageDisposalDrivingConVol  
   RESULT = CreateEntity( mMasLC, "M_StorageDisposalDrivingConVol", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditPrecautionarySection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditPrecautionarySection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on "Precautionary" General Statement.
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "P"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "P", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_GeneralSection 
      RESULT = CreateEntity( mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:mMasLC.M_GeneralSection.SectionType = "P" 
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "P" );
   } 

   //:END 
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_FirstAidStmtDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_FirstAidStmtDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "First Aid Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "First Aid Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_GeneralStatement.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_GeneralStatement", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_GeneralStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_GeneralStatement" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditFirstAidSection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditFirstAidSection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on "First Aid" General Statement.
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "F"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "F", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_GeneralSection 
      RESULT = CreateEntity( mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:mMasLC.M_GeneralSection.SectionType = "F" 
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "F" );
   } 

   //:END 
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_HazardsStmtDelete( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
GOTO_HazardsStmtDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Hazards Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Hazards Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_GeneralStatement.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_GeneralStatement", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_GeneralStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_GeneralStatement" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditHazardsSection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditHazardsSection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on "Hazards" General Statement.
   //:SET CURSOR FIRST mMasLC.M_GeneralSection WHERE mMasLC.M_GeneralSection.SectionType = "E"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_GeneralSection", "SectionType", "E", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_GeneralSection 
      RESULT = CreateEntity( mMasLC, "M_GeneralSection", zPOS_AFTER );
      //:mMasLC.M_GeneralSection.SectionType = "E" 
      SetAttributeFromString( mMasLC, "M_GeneralSection", "SectionType", "E" );
   } 

   //:END 
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:PostbuildDeleteUsageStatements( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
PostbuildDeleteUsageStatements( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Create list of Usage Entries selected for delete.
   //:FOR EACH mMasLC.MI_UsageList 
   RESULT = SetCursorFirstEntity( mMasLC, "MI_UsageList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE mMasLC.MI_UsageList NONE
      RESULT = ExcludeEntity( mMasLC, "MI_UsageList", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mMasLC, "MI_UsageList", "" );
   } 

   //:END
   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.wSelected = "Y"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "wSelected", "Y" ) == 0 )
      { 
         //:INCLUDE mMasLC.MI_UsageList FROM mMasLC.M_Usage 
         RESULT = IncludeSubobjectFromSubobject( mMasLC, "MI_UsageList", mMasLC, "M_Usage", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditClaimsSection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditClaimsSection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "C" 
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "C", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_UsageType
      RESULT = CreateEntity( mMasLC, "M_UsageType", zPOS_AFTER );
      //:mMasLC.M_UsageType.UsageType = "C"
      SetAttributeFromString( mMasLC, "M_UsageType", "UsageType", "C" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditAreasOfUseSection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditAreasOfUseSection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "U" 
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "U", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_UsageType
      RESULT = CreateEntity( mMasLC, "M_UsageType", zPOS_AFTER );
      //:mMasLC.M_UsageType.UsageType = "U"
      SetAttributeFromString( mMasLC, "M_UsageType", "UsageType", "U" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditSurfacesSection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditSurfacesSection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "S" 
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "S", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_UsageType
      RESULT = CreateEntity( mMasLC, "M_UsageType", zPOS_AFTER );
      //:mMasLC.M_UsageType.UsageType = "S"
      SetAttributeFromString( mMasLC, "M_UsageType", "UsageType", "S" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditApplicationTypesSection( VIEW ViewToWindow )

//:   VIEW mMasLC REGISTERED AS mMasLC
public int 
EditApplicationTypesSection( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "T" 
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "T", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mMasLC.M_UsageType
      RESULT = CreateEntity( mMasLC, "M_UsageType", zPOS_AFTER );
      //:mMasLC.M_UsageType.UsageType = "T"
      SetAttributeFromString( mMasLC, "M_UsageType", "UsageType", "T" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_DirsForUseSectionDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_DirsForUseSectionDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Directions for Use Section"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Directions for Use Section" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_DirectionsForUseSection.Name 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_DirectionsForUseSection", "Name" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_DirectionsForUseSection"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_DirectionsForUseSection" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectRemoveDrivingDU( VIEW ViewToWindow )

//:   VIEW mMasLC  REGISTERED AS mMasLC
public int 
GOTO_SelectRemoveDrivingDU( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Initialize the mMasLC  for selecting Driving Claims.
   //:mMasLC.MasterLabelContent.wSelectedUsageType = "DC"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wSelectedUsageType", "DC" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_MarketingStatementDelete( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
GOTO_MarketingStatementDelete( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:mMasLC.MasterLabelContent.wDeleteType       = "Marketing Statement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteType", "Marketing Statement" );
   //:mMasLC.MasterLabelContent.wDeleteText       = mMasLC.M_MarketingStatement.Text 
   SetAttributeFromAttribute( mMasLC, "MasterLabelContent", "wDeleteText", mMasLC, "M_MarketingStatement", "Text" );
   //:mMasLC.MasterLabelContent.wDeleteEntityName = "M_MarketingStatement"
   SetAttributeFromString( mMasLC, "MasterLabelContent", "wDeleteEntityName", "M_MarketingStatement" );
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

   //:IF mMasLC.M_IngredientsSection DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mMasLC, "M_IngredientsSection" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY mMasLC.M_IngredientsSection
      RESULT = CreateEntity( mMasLC, "M_IngredientsSection", zPOS_AFTER );
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



}
