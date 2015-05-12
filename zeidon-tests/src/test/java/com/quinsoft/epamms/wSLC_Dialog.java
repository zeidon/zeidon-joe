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

import com.quinsoft.epamms.mSubLC_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class wSLC_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public wSLC_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:DIALOG OPERATION
//:CancelAndReturnSLC( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
CancelAndReturnSLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Drop current SLC.
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectRemoveMktgStatements( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectRemoveMktgStatements( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Make sure the SLC Components are built.
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
//:SelectMLC_ComponentsForSLC( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
SelectMLC_ComponentsForSLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// For each selected MLC Marketing Statement, add the corresponding SLC Marketing Statement, along
   //:// with the Marketing Section parent, if necessary.
   //:// Then rebuild the SLC Component entries.

   //:// Create SLC Maketing Statements and necessary parent Marketing Sections.
   //:FOR EACH mMasLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mMasLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.CompositeComponentList.Selected = "Y" AND 
      //:   mMasLC.CompositeComponentList.Type = "M_MarketingStatement"
      if ( CompareAttributeToString( mMasLC, "CompositeComponentList", "Selected", "Y" ) == 0 && CompareAttributeToString( mMasLC, "CompositeComponentList", "Type", "M_MarketingStatement" ) == 0 )
      { 

         //:SET CURSOR FIRST mMasLC.M_MarketingStatement WITHIN mMasLC.MasterLabelContent 
         //:           WHERE mMasLC.M_MarketingStatement.ID = mMasLC.CompositeComponentList.OriginalTypeID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "CompositeComponentList", "OriginalTypeID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingStatement", "ID", lTempInteger_0, "MasterLabelContent" );
         //:SET CURSOR FIRST mSubLC.M_MarketingSection WITHIN mSubLC.SubregLabelContent 
         //:           WHERE mSubLC.M_MarketingSection.ID = mMasLC.M_MarketingSection.ID 
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mMasLC, "M_MarketingSection", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "M_MarketingSection", "ID", lTempInteger_1, "SubregLabelContent" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY mSubLC.S_MarketingSection 
            RESULT = CreateEntity( mSubLC, "S_MarketingSection", zPOS_AFTER );
            //:SetMatchingAttributesByName( mSubLC, "S_MarketingSection", mMasLC, "M_MarketingSection", zSET_NULL )
            SetMatchingAttributesByName( mSubLC, "S_MarketingSection", mMasLC, "M_MarketingSection", zSET_NULL );
            //:INCLUDE mSubLC.M_MarketingSection FROM mMasLC.M_MarketingSection  
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_MarketingSection", mMasLC, "M_MarketingSection", zPOS_AFTER );
         } 

         //:END
         //:SET CURSOR FIRST mSubLC.M_MarketingStatement WITHIN mSubLC.S_MarketingSection 
         //:           WHERE mSubLC.M_MarketingStatement.ID = mMasLC.M_MarketingStatement.ID 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "M_MarketingStatement", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "M_MarketingStatement", "ID", lTempInteger_2, "S_MarketingSection" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY mSubLC.S_MarketingStatement 
            RESULT = CreateEntity( mSubLC, "S_MarketingStatement", zPOS_AFTER );
            //:SetMatchingAttributesByName( mSubLC, "S_MarketingStatement", mMasLC, "M_MarketingStatement", zSET_NULL )
            SetMatchingAttributesByName( mSubLC, "S_MarketingStatement", mMasLC, "M_MarketingStatement", zSET_NULL );
            //:INCLUDE mSubLC.M_MarketingStatement FROM mMasLC.M_MarketingStatement  
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_MarketingStatement", mMasLC, "M_MarketingStatement", zPOS_AFTER );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mMasLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END

   //:// Rebuild the Marketing Components.
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
//:DeleteMLC_ComponentsForSLC( VIEW ViewToWindow )

//:   VIEW mSubLC  REGISTERED AS mSubLC
public int 
DeleteMLC_ComponentsForSLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC  REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// For each selected Marketing Statement from the Components subobject, delete the corresponding
   //:// S_MarketingStatement entity. 
   //:// Then delete any Marketing Section entities without a MarketStatement subobject.
   //:// Finally, rebuild the Components subobject.

   //:// Delete selected Marketing Statement entries.
   //:FOR EACH mSubLC.CompositeComponentList 
   RESULT = SetCursorFirstEntity( mSubLC, "CompositeComponentList", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.CompositeComponentList.Selected = "Y" AND 
      //:   mSubLC.CompositeComponentList.Type = "M_MarketingStatement"
      if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Selected", "Y" ) == 0 && CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingStatement" ) == 0 )
      { 

         //:SET CURSOR FIRST mSubLC.S_MarketingStatement WITHIN mSubLC.SubregLabelContent 
         //:           WHERE mSubLC.S_MarketingStatement.ID = mSubLC.CompositeComponentList.SLC_OriginalTypeID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "CompositeComponentList", "SLC_OriginalTypeID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_MarketingStatement", "ID", lTempInteger_0, "SubregLabelContent" );
         //:DELETE ENTITY mSubLC.S_MarketingStatement   
         RESULT = DeleteEntity( mSubLC, "S_MarketingStatement", zPOS_NEXT );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "CompositeComponentList", "" );
      //:END
   } 

   //:END

   //:// Delete Marketing Sections without Marketing Statements.
   //:FOR EACH mSubLC.S_MarketingSection 
   RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_MarketingStatement DOES NOT EXIST
      lTempInteger_1 = CheckExistenceOfEntity( mSubLC, "S_MarketingStatement" );
      if ( lTempInteger_1 != 0 )
      { 
         //:DELETE ENTITY mSubLC.S_MarketingSection  
         RESULT = DeleteEntity( mSubLC, "S_MarketingSection", zPOS_NEXT );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_MarketingSection", "" );
      //:END
   } 

   //:END

   //:// Rebuild the Marketing Components.
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
//:GOTO_SelectRemoveDUEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectRemoveDUEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC  REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on corresponding DirectionsForUseStatement entity in MLC to get list of potential Usage entries.
   //:SET CURSOR FIRST mMasLC.M_DirectionsForUseStatement 
   //:           WHERE mMasLC.M_DirectionsForUseStatement.ID = mSubLC.M_DirectionsForUseStatement.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "M_DirectionsForUseStatement", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DirectionsForUseStatement", "ID", lTempInteger_0, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectDirsForUseUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
SelectDirsForUseUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Include each selected Usage Source entry in the Target subobject.
   //:FOR EACH mSubLC.S_SelectableDirsForUseUsage 
   RESULT = SetCursorFirstEntity( mSubLC, "S_SelectableDirsForUseUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_SelectableDirsForUseUsage.wSelected = "Y"
      if ( CompareAttributeToString( mSubLC, "S_SelectableDirsForUseUsage", "wSelected", "Y" ) == 0 )
      { 
         //:SET CURSOR FIRST mSubLC.S_DirectionsUsage WITHIN mSubLC.S_DirectionsForUseStatement 
         //:           WHERE mSubLC.S_DirectionsUsage.ID = mSubLC.S_SelectableDirsForUseUsage.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "S_SelectableDirsForUseUsage", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_DirectionsUsage", "ID", lTempInteger_0, "S_DirectionsForUseStatement" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY mSubLC.S_DirectionsUsageOrdering 
            RESULT = CreateEntity( mSubLC, "S_DirectionsUsageOrdering", zPOS_AFTER );
            //:INCLUDE mSubLC.S_DirectionsUsage FROM mSubLC.S_SelectableDirsForUseUsage 
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "S_DirectionsUsage", mSubLC, "S_SelectableDirsForUseUsage", zPOS_AFTER );
         } 

         //:END 
         //:mSubLC.S_SelectableDirsForUseUsage.wSelected = ""
         SetAttributeFromString( mSubLC, "S_SelectableDirsForUseUsage", "wSelected", "" );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_SelectableDirsForUseUsage", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST mSubLC.S_DirectionsUsageOrdering 
   RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:RemoveDirsForUseUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
RemoveDirsForUseUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Remove each selected Usage entry.
   //:FOR EACH mSubLC.S_DirectionsUsageOrdering 
   RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_DirectionsUsageOrdering.wSelected = "Y"
      if ( CompareAttributeToString( mSubLC, "S_DirectionsUsageOrdering", "wSelected", "Y" ) == 0 )
      { 
         //:DELETE ENTITY mSubLC.S_DirectionsUsageOrdering NONE 
         RESULT = DeleteEntity( mSubLC, "S_DirectionsUsageOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST mSubLC.S_DirectionsUsageOrdering 
   RESULT = SetCursorFirstEntity( mSubLC, "S_DirectionsUsageOrdering", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMarketingUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
SelectMarketingUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Include each selected Usage Source entry in the Target subobject.
   //:FOR EACH mSubLC.S_SelectableMarketingUsage 
   RESULT = SetCursorFirstEntity( mSubLC, "S_SelectableMarketingUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_SelectableMarketingUsage.wSelected = "Y"
      if ( CompareAttributeToString( mSubLC, "S_SelectableMarketingUsage", "wSelected", "Y" ) == 0 )
      { 
         //:SET CURSOR FIRST mSubLC.S_MarketingUsage WITHIN mSubLC.S_MarketingStatement 
         //:           WHERE mSubLC.S_MarketingUsage.ID = mSubLC.S_SelectableMarketingUsage.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "S_SelectableMarketingUsage", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_MarketingUsage", "ID", lTempInteger_0, "S_MarketingStatement" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY mSubLC.S_MarketingUsageOrdering 
            RESULT = CreateEntity( mSubLC, "S_MarketingUsageOrdering", zPOS_AFTER );
            //:INCLUDE mSubLC.S_MarketingUsage FROM mSubLC.S_SelectableMarketingUsage 
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "S_MarketingUsage", mSubLC, "S_SelectableMarketingUsage", zPOS_AFTER );
         } 

         //:END 
         //:mSubLC.S_SelectableMarketingUsage.wSelected = ""
         SetAttributeFromString( mSubLC, "S_SelectableMarketingUsage", "wSelected", "" );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_SelectableMarketingUsage", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST mSubLC.S_MarketingUsageOrdering 
   RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingUsageOrdering", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:RemoveMarketingUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
RemoveMarketingUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Remove each selected Usage entry.
   //:FOR EACH mSubLC.S_MarketingUsageOrdering 
   RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_MarketingUsageOrdering.wSelected = "Y"
      if ( CompareAttributeToString( mSubLC, "S_MarketingUsageOrdering", "wSelected", "Y" ) == 0 )
      { 
         //:DELETE ENTITY mSubLC.S_MarketingUsageOrdering NONE 
         RESULT = DeleteEntity( mSubLC, "S_MarketingUsageOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_MarketingUsageOrdering", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST mSubLC.S_MarketingUsageOrdering 
   RESULT = SetCursorFirstEntity( mSubLC, "S_MarketingUsageOrdering", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveSLC( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
SaveSLC( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplaySurfacesSection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplaySurfacesSection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "S" 
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "S", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "S" 
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "S" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplayPrecautionarySection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplayPrecautionarySection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on "Precautionary" General Statement.
   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = "P"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", "P", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplayHazardsSection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplayHazardsSection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on "Hazards" General Statement.
   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = "E"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", "E", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplayFirstAidSection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplayFirstAidSection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on "First Aid" General Statement.
   //:SET CURSOR FIRST mSubLC.S_GeneralSection WHERE mSubLC.S_GeneralSection.SectionType = "F"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_GeneralSection", "SectionType", "F", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplayClaimsSection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplayClaimsSection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "C" 
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "C", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "C" 
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "C" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplayAreasOfUseSection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplayAreasOfUseSection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "U" 
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "U", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "U" 
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "U" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DisplayApplicationTypesSection( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
DisplayApplicationTypesSection( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "T" 
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "T", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "T" 
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "T" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMLC_UsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
SelectMLC_UsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Include each selected Usage Source entry in the Target subobject, S_Usage.
   //:FOR EACH mMasLC.M_Usage 
   RESULT = SetCursorFirstEntity( mMasLC, "M_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mMasLC.M_Usage.wSelected = "Y"
      if ( CompareAttributeToString( mMasLC, "M_Usage", "wSelected", "Y" ) == 0 )
      { 
         //:SET CURSOR FIRST mSubLC.S_Usage WHERE mSubLC.S_Usage.ID = mMasLC.M_Usage.ID 
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
                   GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "M_Usage", "ID" );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_Usage", "ID", lTempInteger_0, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY mSubLC.S_Usage 
            RESULT = CreateEntity( mSubLC, "S_Usage", zPOS_AFTER );
            //:SetMatchingAttributesByName( mSubLC, "S_Usage", mMasLC, "M_Usage", zSET_NULL ) 
            SetMatchingAttributesByName( mSubLC, "S_Usage", mMasLC, "M_Usage", zSET_NULL );
            //:mSubLC.S_Usage.wSelected = ""
            SetAttributeFromString( mSubLC, "S_Usage", "wSelected", "" );
            //:INCLUDE mSubLC.M_Usage FROM mMasLC.M_Usage 
            RESULT = IncludeSubobjectFromSubobject( mSubLC, "M_Usage", mMasLC, "M_Usage", zPOS_AFTER );
         } 

         //:END 
         //:mMasLC.M_Usage.wSelected = ""
         SetAttributeFromString( mMasLC, "M_Usage", "wSelected", "" );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_Usage", "" );
      //:END
   } 

   //:END
   //:OrderEntityForView( mSubLC, "S_Usage", "dDisplayUsageName A" )
   OrderEntityForView( mSubLC, "S_Usage", "dDisplayUsageName A" );
   //:SET CURSOR FIRST mSubLC.S_Usage  
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:RemoveSLC_UsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
RemoveSLC_UsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Remove each selected S_Usage entry.
   //:FOR EACH mSubLC.S_Usage 
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSubLC.S_Usage.wSelected = "Y"
      if ( CompareAttributeToString( mSubLC, "S_Usage", "wSelected", "Y" ) == 0 )
      { 
         //:DELETE ENTITY mSubLC.S_Usage NONE 
         RESULT = DeleteEntity( mSubLC, "S_Usage", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mSubLC, "S_Usage", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST mSubLC.S_Usage 
   RESULT = SetCursorFirstEntity( mSubLC, "S_Usage", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectClaimsUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectClaimsUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Give Error Message if MLC doesn't have Claims Usage Types.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "C", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Select MLC Claims Entries",
      //:             "The Master Product does not have any Claims entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select MLC Claims Entries", "The Master Product does not have any Claims entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:// Position on SLC and MLC Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "C"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "C", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "C"
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "C" );
   } 

   //:END
   //:CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" )
   CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectApplsUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectApplsUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Give Error Message if MLC doesn't have Claims Usage Types.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "T", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Select MLC Claims Entries",
      //:             "The Master Product does not have any Claims entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select MLC Claims Entries", "The Master Product does not have any Claims entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:// Position on SLC and MLC Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "T"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "T", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "T"
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "T" );
   } 

   //:END
   //:CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" )
   CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectSurfacesUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectSurfacesUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Give Error Message if MLC doesn't have Claims Usage Types.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "S", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Select MLC Claims Entries",
      //:             "The Master Product does not have any Claims entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select MLC Claims Entries", "The Master Product does not have any Claims entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:// Position on SLC and MLC Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "S"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "S", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "S"
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "S" );
   } 

   //:END
   //:CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" )
   CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectAreasUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectAreasUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Give Error Message if MLC doesn't have Claims Usage Types.
   //:SET CURSOR FIRST mMasLC.M_UsageType WHERE mMasLC.M_UsageType.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( mMasLC, "M_UsageType", "UsageType", "U", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Select MLC Claims Entries",
      //:             "The Master Product does not have any Claims entries.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select MLC Claims Entries", "The Master Product does not have any Claims entries.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 

   //:// Position on SLC and MLC Claims Usage Type.
   //:SET CURSOR FIRST mSubLC.S_UsageType WHERE mSubLC.S_UsageType.UsageType = "U"
   RESULT = SetCursorFirstEntityByString( mSubLC, "S_UsageType", "UsageType", "U", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY mSubLC.S_UsageType 
      RESULT = CreateEntity( mSubLC, "S_UsageType", zPOS_AFTER );
      //:mSubLC.S_UsageType.UsageType = "U"
      SetAttributeFromString( mSubLC, "S_UsageType", "UsageType", "U" );
   } 

   //:END
   //:CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" )
   CreateTemporalSubobjectVersion( mSubLC, "S_UsageType" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_MarketingStatementDetail( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_MarketingStatementDetail( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Position on the correct Marketing Statement based on position on the corresponding Components entry.
   //:// If we're positioned on a Marketing Section component, give error.
   //:IF mSubLC.CompositeComponentList.Type = "M_MarketingSection"
   if ( CompareAttributeToString( mSubLC, "CompositeComponentList", "Type", "M_MarketingSection" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Marketing Statement Detail",
      //:             "Only a Marketing Statement can be selected.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Marketing Statement Detail", "Only a Marketing Statement can be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST mSubLC.S_MarketingStatement WITHIN mSubLC.SubregLabelContent 
      //:           WHERE mSubLC.S_MarketingStatement.ID = mSubLC.CompositeComponentList.SLC_OriginalTypeID 
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "CompositeComponentList", "SLC_OriginalTypeID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_MarketingStatement", "ID", lTempInteger_0, "SubregLabelContent" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GOTO_SelectMarketingUsageEntries( VIEW ViewToWindow )

//:   VIEW mSubLC REGISTERED AS mSubLC
public int 
GOTO_SelectMarketingUsageEntries( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mSubLC.S_SelectableMarketingUsage
   RESULT = SetCursorFirstEntity( mSubLC, "S_SelectableMarketingUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE mSubLC.S_SelectableMarketingUsage NONE
      RESULT = ExcludeEntity( mSubLC, "S_SelectableMarketingUsage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSubLC, "S_SelectableMarketingUsage", "" );
   } 

   //:END

   //:// Set up the list of selectable Usage Statements. This is the subset of MLC Usage Statements tied
   //:// to the corresponding MLC Statement is ARE ALSO IN the list of Usage Statements selected for this SLC.
   //:SET CURSOR FIRST mMasLC.M_MarketingStatement WITHIN mMasLC.MasterLabelContent 
   //:           WHERE mMasLC.M_MarketingStatement.ID = mSubLC.M_MarketingStatement.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "M_MarketingStatement", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingStatement", "ID", lTempInteger_0, "MasterLabelContent" );
   //:FOR EACH mMasLC.M_MarketingUsageOrdering 
   RESULT = SetCursorFirstEntity( mMasLC, "M_MarketingUsageOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSubLC.M_Usage WITHIN mSubLC.SubregLabelContent 
      //:           WHERE mSubLC.M_Usage.ID = mMasLC.M_MarketingUsage.ID  
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mMasLC, "M_MarketingUsage", "ID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSubLC, "M_Usage", "ID", lTempInteger_1, "SubregLabelContent" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// An SLC Usage entry exists for the MLC Statement Usage entry.
         //:INCLUDE mSubLC.S_SelectableMarketingUsage FROM mSubLC.S_Usage 
         RESULT = IncludeSubobjectFromSubobject( mSubLC, "S_SelectableMarketingUsage", mSubLC, "S_Usage", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( mMasLC, "M_MarketingUsageOrdering", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 



}
