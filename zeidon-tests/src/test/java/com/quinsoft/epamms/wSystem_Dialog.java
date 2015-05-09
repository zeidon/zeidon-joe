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

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class wSystem_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   private final KZOEP1AA m_KZOEP1AA;
   public wSystem_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


private int 
o_fnLocalBuildQual_2( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:DeleteSelectedBacteria( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteSelectedBacteria( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 1 )  szSelected
   String   szSelected = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:szSelected = mEPA.EPA_Claim.wkSelected
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szSelected;
      if ( szSelected == null )
         sb_szSelected = new StringBuilder( 32 );
      else
         sb_szSelected = new StringBuilder( szSelected );
             GetVariableFromAttribute( sb_szSelected, mi_lTempInteger_0, 'S', 2, mEPA, "EPA_Claim", "wkSelected", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szSelected = sb_szSelected.toString( );}
      //:IF szSelected = "Y"
      if ( ZeidonStringCompare( szSelected, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:IF mEPA.EPA_Claim EXISTS
         lTempInteger_1 = CheckExistenceOfEntity( mEPA, "EPA_Claim" );
         if ( lTempInteger_1 == 0 )
         { 
            //:DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE )
            DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE );
            //:ELSE
         } 
         else
         { 
            //:DisplayObjectInstance( mEPA, "", "" )
            DisplayObjectInstance( mEPA, "", "" );
            //:MessageSend( ViewToWindow, "", "Delete Selected Bacteria",
            //:             "Found Selection that was not Bacteria?",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Delete Selected Bacteria", "Found Selection that was not Bacteria?", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
   } 

   //:END

   //:Commit mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFeedbackCommentForDelete( VIEW ViewToWindow )

public int 
InitFeedbackCommentForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here
// END
} 


//:DIALOG OPERATION
public int 
UpdateAppType( View     ViewToWindow )
{

   //:UpdateAppType( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateAppType: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateAppType: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportAppTypesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportAppTypesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.String = "AppTypes.epa"
   SetAttributeFromString( wWebXfer, "Root", "String", "AppTypes.epa" );
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              mEPA, "EPA_ApplicationType", "Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_ApplicationType", "Name", 0 );
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
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of areas of use.
   //:nRC = SetCursorFirstEntity( mEPA, "EPA_ApplicationType", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_ApplicationType", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:DeleteEntity( mEPA, "EPA_ApplicationType", zREPOS_NONE )
      DeleteEntity( mEPA, "EPA_ApplicationType", zREPOS_NONE );
      //:nRC = SetCursorNextEntity( mEPA, "EPA_ApplicationType", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_ApplicationType", "" );
   } 

   //:END

   //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             mEPA, "EPA_ApplicationType", "Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_ApplicationType", "Name", 0 );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSystemKeywordForInsert( VIEW ViewToWindow )

public int 
InitSystemKeywordForInsert( View     ViewToWindow )
{

   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mEPA", "Keyword", "InitSystemKeywordForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mEPA", "Keyword", "InitSystemKeywordForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSystemKeywordForUpdate( VIEW ViewToWindow )

public int 
InitSystemKeywordForUpdate( View     ViewToWindow )
{

   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "Keyword", "InitSystemKeywordForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "Keyword", "InitSystemKeywordForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSystemKeyword( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelNewSystemKeyword( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSystemKeyword( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelUpdateSystemKeyword( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteAreasOfUse( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteAreasOfUse( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mEPA.EPA_AreaOfUse
   RESULT = DeleteEntity( mEPA, "EPA_AreaOfUse", zPOS_NEXT );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteAreasOfUse: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteAreasOfUse: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteAppType( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteAppType( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mEPA.EPA_ApplicationType
   RESULT = DeleteEntity( mEPA, "EPA_ApplicationType", zPOS_NEXT );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteAppType: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteAppType: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteClaim( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteClaim( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mEPA.EPA_Claim
   RESULT = DeleteEntity( mEPA, "EPA_Claim", zPOS_NEXT );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteClaim: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteClaim: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSurface( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteSurface( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mEPA.EPA_Surface
   RESULT = DeleteEntity( mEPA, "EPA_Surface", zPOS_NEXT );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteSurface: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteSurface: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CopyToNewSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CopyToNewSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mNew     BASED ON LOD  mEPA
   zVIEW    mNew = new zVIEW( );

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );

   //:CreateViewFromView( mNew, mEPA )
   CreateViewFromView( mNew, mEPA );
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_NEXT, "mEPA", "EPA_ChemicalFamily", "CopyToNewSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_NEXT, "mEPA", "EPA_ChemicalFamily", "CopyToNewSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:SetMatchingAttributesByName( mNew, "EPA_ChemicalFamily",
   //:                             mEPA, "EPA_ChemicalFamily", zSET_ALL )
   SetMatchingAttributesByName( mNew, "EPA_ChemicalFamily", mEPA, "EPA_ChemicalFamily", zSET_ALL );
   //:mNew.EPA_ChemicalFamily.Name = ""
   SetAttributeFromString( mNew, "EPA_ChemicalFamily", "Name", "" );

   //:FOR EACH mEPA.EPA_StorageDisposal
   RESULT = SetCursorFirstEntity( mEPA, "EPA_StorageDisposal", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mNew.EPA_StorageDisposal
      RESULT = CreateEntity( mNew, "EPA_StorageDisposal", zPOS_AFTER );
      //:SetMatchingAttributesByName( mNew, "EPA_StorageDisposal",
      //:                             mEPA, "EPA_StorageDisposal", zSET_ALL )
      SetMatchingAttributesByName( mNew, "EPA_StorageDisposal", mEPA, "EPA_StorageDisposal", zSET_ALL );
      RESULT = SetCursorNextEntity( mEPA, "EPA_StorageDisposal", "" );
   } 

   //:END

   //:FOR EACH mEPA.EPA_AreaOfUse
   RESULT = SetCursorFirstEntity( mEPA, "EPA_AreaOfUse", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mNew.EPA_AreaOfUse
      RESULT = CreateEntity( mNew, "EPA_AreaOfUse", zPOS_AFTER );
      //:SetMatchingAttributesByName( mNew, "EPA_AreaOfUse",
      //:                             mEPA, "EPA_AreaOfUse", zSET_ALL )
      SetMatchingAttributesByName( mNew, "EPA_AreaOfUse", mEPA, "EPA_AreaOfUse", zSET_ALL );
      RESULT = SetCursorNextEntity( mEPA, "EPA_AreaOfUse", "" );
   } 

   //:END

   //:FOR EACH mEPA.EPA_Surface
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Surface", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mNew.EPA_Surface
      RESULT = CreateEntity( mNew, "EPA_Surface", zPOS_AFTER );
      //:SetMatchingAttributesByName( mNew, "EPA_Surface",
      //:                             mEPA, "EPA_Surface", zSET_ALL )
      SetMatchingAttributesByName( mNew, "EPA_Surface", mEPA, "EPA_Surface", zSET_ALL );
      RESULT = SetCursorNextEntity( mEPA, "EPA_Surface", "" );
   } 

   //:END

   //:FOR EACH mEPA.EPA_Claim
   RESULT = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mNew.EPA_Claim
      RESULT = CreateEntity( mNew, "EPA_Claim", zPOS_AFTER );
      //:SetMatchingAttributesByName( mNew, "EPA_Claim",
      //:                             mEPA, "EPA_Claim", zSET_ALL )
      SetMatchingAttributesByName( mNew, "EPA_Claim", mEPA, "EPA_Claim", zSET_ALL );
      RESULT = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
AcceptUpdateSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptUpdateSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptUpdateSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSystemKeyword( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
AcceptUpdateSystemKeyword( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelUpdateSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
AcceptNewSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelNewSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
ConfirmDeleteSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "AcceptUpdateSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "AcceptUpdateSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mEPA.EPA_ChemicalFamily
   RESULT = DeleteEntity( mEPA, "EPA_ChemicalFamily", zPOS_NEXT );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteFeedbackComment( VIEW ViewToWindow )

//:   VIEW mOrganiz REGISTERED AS mOrganiz
public int 
ConfirmDeleteFeedbackComment( View     ViewToWindow )
{
   zVIEW    mOrganiz = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mOrganiz, "mOrganiz", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFeedbackComment: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFeedbackComment: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mOrganiz.Feedback
   RESULT = DeleteEntity( mOrganiz, "Feedback", zPOS_NEXT );
   //:COMMIT mOrganiz
   RESULT = CommitObjectInstance( mOrganiz );
   //:DropObjectInstance( mOrganiz )
   DropObjectInstance( mOrganiz );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteFeedbackComment( VIEW ViewToWindow )

//:   VIEW mOrganiz REGISTERED AS mOrganiz
public int 
CancelDeleteFeedbackComment( View     ViewToWindow )
{
   zVIEW    mOrganiz = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mOrganiz, "mOrganiz", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mOrganiz )
   DropObjectInstance( mOrganiz );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSystemChemicalForDelete( VIEW ViewToWindow )

public int 
InitSystemChemicalForDelete( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here
// END
} 


//:DIALOG OPERATION
//:CancelDeleteSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
CancelDeleteSystemChemical( View     ViewToWindow )
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
public int 
NewSystemChemical( View     ViewToWindow )
{

   //:NewSystemChemical( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
NewSystemKeyword( View     ViewToWindow )
{

   //:NewSystemKeyword( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateSystemChemical( View     ViewToWindow )
{

   //:UpdateSystemChemical( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSystemChemical( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteSystemChemical( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteSystemChemical: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteSystemChemical: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSystemChemicalForUpdate( VIEW ViewToWindow )

public int 
InitSystemChemicalForUpdate( View     ViewToWindow )
{

   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_ChemicalFamily", "InitSystemChemicalForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_ChemicalFamily", "InitSystemChemicalForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitAreasOfUseForUpdate( VIEW ViewToWindow )

public int 
InitAreasOfUseForUpdate( View     ViewToWindow )
{

   return( 0 );
// // CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_AreaOfUse", "InitAreasOfUseForUpdate: " )
// END
} 


//:DIALOG OPERATION
//:InitAppTypesForUpdate( VIEW ViewToWindow )

public int 
InitAppTypesForUpdate( View     ViewToWindow )
{

   return( 0 );
// // CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_ApplicationType", "InitAppTypesForUpdate: " )
// END
} 


//:DIALOG OPERATION
//:InitSurfacesForUpdate( VIEW ViewToWindow )

public int 
InitSurfacesForUpdate( View     ViewToWindow )
{

   return( 0 );
// // CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_Surface", "InitSurfacesForUpdate: " )
// END
} 


//:DIALOG OPERATION
//:InitBacteriaForUpdate( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
InitBacteriaForUpdate( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_Claim", "InitBacteriaForUpdate: " )
   //:FOR EACH mEPA.EPA_Claim WHERE mEPA.EPA_Claim.ClaimsClassification = "Bacteria"
   RESULT = SetCursorFirstEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Bacteria", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.Bacteria DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mEPA, "Bacteria" );
      if ( lTempInteger_0 != 0 )
      { 
         //:CREATE ENTITY mEPA.Bacteria
         RESULT = CreateEntity( mEPA, "Bacteria", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Bacteria", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitBacteriaForDelete( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
InitBacteriaForDelete( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_Claim", "InitBacteriaForDelete: " )
   //:FOR EACH mEPA.EPA_Claim WHERE mEPA.EPA_Claim.ClaimsClassification = "Bacteria"
   RESULT = SetCursorFirstEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Bacteria", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.Bacteria DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mEPA, "Bacteria" );
      if ( lTempInteger_0 != 0 )
      { 
         //:CREATE ENTITY mEPA.Bacteria
         RESULT = CreateEntity( mEPA, "Bacteria", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Bacteria", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFungiForUpdate( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
InitFungiForUpdate( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_Claim", "InitFungiForUpdate: " )
   //:FOR EACH mEPA.EPA_Claim WHERE mEPA.EPA_Claim.ClaimsClassification = "Fungi"
   RESULT = SetCursorFirstEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Fungi", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.Fungi DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mEPA, "Fungi" );
      if ( lTempInteger_0 != 0 )
      { 
         //:CREATE ENTITY mEPA.Fungi
         RESULT = CreateEntity( mEPA, "Fungi", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Fungi", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitVirusesForUpdate( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
InitVirusesForUpdate( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "EPA_Claim", "InitVirusesForUpdate: " )
   //:FOR EACH mEPA.EPA_Claim WHERE mEPA.EPA_Claim.ClaimsClassification = "Viruses"
   RESULT = SetCursorFirstEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Viruses", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mEPA.Viruses DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mEPA, "Viruses" );
      if ( lTempInteger_0 != 0 )
      { 
         //:CREATE ENTITY mEPA.Viruses
         RESULT = CreateEntity( mEPA, "Viruses", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntityByString( mEPA, "EPA_Claim", "ClaimsClassification", "Viruses", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportSurfacesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportSurfacesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Set up Surfaces list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.String = "Surfaces.epa"
   SetAttributeFromString( wWebXfer, "Root", "String", "Surfaces.epa" );
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              mEPA, "EPA_Surface", "Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_Surface", "Name", 0 );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportSurfacesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportSurfacesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportSurfacesStatementList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportSurfacesStatementList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportBacteriaList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportBacteriaList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Set up Bacteria list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.String = "Bacteria.epa"
   SetAttributeFromString( wWebXfer, "Root", "String", "Bacteria.epa" );

   //:LoadUsageList( ViewToWindow, mEPA, "Bacteria" )
   o_LoadUsageList( ViewToWindow, mEPA, "Bacteria" );
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              mEPA, "Bacteria.EPA_ChemicalFamily", "EPA_Claim.Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", mEPA, "Bacteria.EPA_ChemicalFamily", "EPA_Claim.Name", 0 );
   //:LoadUsageList( ViewToWindow, mEPA, "" )  // just delete Bacteria entities
   o_LoadUsageList( ViewToWindow, mEPA, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportFungiList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportFungiList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Set up Fungi list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.String = "Fungi.epa"
   SetAttributeFromString( wWebXfer, "Root", "String", "Fungi.epa" );

   //:LoadUsageList( ViewToWindow, mEPA, "Fungi" )
   o_LoadUsageList( ViewToWindow, mEPA, "Fungi" );
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              mEPA, "Fungi.EPA_ChemicalFamily", "EPA_Claim.Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", mEPA, "Fungi.EPA_ChemicalFamily", "EPA_Claim.Name", 0 );
   //:LoadUsageList( ViewToWindow, mEPA, "" )  // just delete Fungi entities
   o_LoadUsageList( ViewToWindow, mEPA, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportVirusesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportVirusesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Set up Viruses list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.String = "Viruses.epa"
   SetAttributeFromString( wWebXfer, "Root", "String", "Viruses.epa" );

   //:LoadUsageList( ViewToWindow, mEPA, "Viruses" )
   o_LoadUsageList( ViewToWindow, mEPA, "Viruses" );
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              mEPA, "Viruses.EPA_ChemicalFamily", "EPA_Claim.Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", mEPA, "Viruses.EPA_ChemicalFamily", "EPA_Claim.Name", 0 );
   //:LoadUsageList( ViewToWindow, mEPA, "" )  // just delete Viruses entities
   o_LoadUsageList( ViewToWindow, mEPA, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportBacteriaList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportBacteriaList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportBacteriaStatementList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportBacteriaStatementList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportFungiList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportFungiList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportFungiStatementList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportFungiStatementList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportVirusesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportVirusesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportVirusesStatementList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportVirusesStatementList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportBacteriaFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportBacteriaFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
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
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

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
      //:MessageSend( ViewToWindow, "", "Import Bacteria Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Bacteria Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
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
      //:MessageSend( ViewToWindow, "", "Import Bacteria Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Bacteria Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szMessage = szDirectoryName + szFileName // borrow szMessage for a sec...
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringCopy( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringConcat( sb_szMessage, 1, 0, szFileName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
   //:SysConvertEnvironmentString( szDirectoryName, szMessage )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectoryName, szMessage );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mEPA, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mEPA, szDirectoryName );
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

      //:MessageSend( ViewToWindow, "", "Import Bacteria Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Bacteria Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:CancelImportBacteriaList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportBacteriaList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportFungiFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportFungiFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
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
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

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
      //:MessageSend( ViewToWindow, "", "Import Fungi Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Fungi Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
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
      //:MessageSend( ViewToWindow, "", "Import Fungi Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Fungi Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szMessage = szDirectoryName + szFileName // borrow szMessage for a sec...
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringCopy( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringConcat( sb_szMessage, 1, 0, szFileName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
   //:SysConvertEnvironmentString( szDirectoryName, szMessage )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectoryName, szMessage );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mEPA, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mEPA, szDirectoryName );
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

      //:MessageSend( ViewToWindow, "", "Import Fungi Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Fungi Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:CancelImportFungiList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportFungiList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportVirusesFromFile( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportVirusesFromFile( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
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
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

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
      //:MessageSend( ViewToWindow, "", "Import Viruses Statements",
      //:             "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Viruses Statements", "Zeidon INI file does not have WebDirectory entry in Application: App.epamms.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
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
      //:MessageSend( ViewToWindow, "", "Import Viruses Statements",
      //:             "The Import File Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Viruses Statements", "The Import File Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szMessage = szDirectoryName + szFileName // borrow szMessage for a sec...
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringCopy( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringConcat( sb_szMessage, 1, 0, szFileName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
   //:SysConvertEnvironmentString( szDirectoryName, szMessage )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectoryName, szMessage );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mEPA, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mEPA, szDirectoryName );
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

      //:MessageSend( ViewToWindow, "", "Import Viruses Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Viruses Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:CancelImportVirusesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportVirusesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   return( 0 );
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
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
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
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

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

   //:szMessage = szDirectoryName + szFileName // borrow szMessage for a sec...
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringCopy( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringConcat( sb_szMessage, 1, 0, szFileName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
   //:SysConvertEnvironmentString( szDirectoryName, szMessage )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectoryName, szMessage );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mEPA, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mEPA, szDirectoryName );
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

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitImportAreasOfUseList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitImportAreasOfUseList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.String = "AreasOfUse.epa"
   SetAttributeFromString( wWebXfer, "Root", "String", "AreasOfUse.epa" );
   //:BuildCSV_FromEntityAttribute( wWebXfer, "Root",
   //:                              "CurrentStatementText",
   //:                              mEPA, "EPA_AreaOfUse", "Name", 0 )
   m_ZDRVROPR.BuildCSV_FromEntityAttribute( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_AreaOfUse", "Name", 0 );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportAreasOfUseList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAreasOfUseList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportAreasOfUseList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportAreasOfUseList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
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
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
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
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

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

   //:szMessage = szDirectoryName + szFileName // borrow szMessage for a sec...
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringCopy( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringConcat( sb_szMessage, 1, 0, szFileName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
   //:SysConvertEnvironmentString( szDirectoryName, szMessage )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectoryName, szMessage );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mEPA, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mEPA, szDirectoryName );
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

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
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
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
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
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

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

   //:szMessage = szDirectoryName + szFileName // borrow szMessage for a sec...
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringCopy( sb_szMessage, 1, 0, szDirectoryName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
    {StringBuilder sb_szMessage;
   if ( szMessage == null )
      sb_szMessage = new StringBuilder( 32 );
   else
      sb_szMessage = new StringBuilder( szMessage );
      ZeidonStringConcat( sb_szMessage, 1, 0, szFileName, 1, 0, 513 );
   szMessage = sb_szMessage.toString( );}
   //:SysConvertEnvironmentString( szDirectoryName, szMessage )
   {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
       m_KZOEP1AA.SysConvertEnvironmentString( sb_szDirectoryName, szMessage );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mEPA, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mEPA, szDirectoryName );
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

      //:MessageSend( ViewToWindow, "", "Import Application Types Statements",
      //:             szMessage,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Import Application Types Statements", szMessage, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:CancelImportAppTypesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelImportAppTypesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ImportAppTypesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAppTypesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.String = ""
   SetAttributeFromString( wWebXfer, "Root", "String", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportAppTypesList: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "ImportAppTypesList: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListPrimaryRegistrants( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD lPrimReg
public int 
InitListPrimaryRegistrants( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF  lPrimReg != 0
   if ( getView( lPrimReg ) != null )
   { 
      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:// Activate all primary registrants.
   //:ACTIVATE lPrimReg MULTIPLE
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wSystem", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSystem", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectUpdatePrimaryRegistrant( VIEW ViewToWindow )

public int 
SelectUpdatePrimaryRegistrant( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do here other than to get proper position
// END
} 


//:DIALOG OPERATION
//:InitPrimaryRegistrantForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrimaryRegistrantForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF mPrimReg != 0
   if ( getView( mPrimReg ) != null )
   { 
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
   } 

   //:END

   //:ACTIVATE mPrimReg EMPTY
   RESULT = ActivateEmptyObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, zSINGLE );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );

   //:CREATE ENTITY mPrimReg.PrimaryRegistrant
   RESULT = CreateEntity( mPrimReg, "PrimaryRegistrant", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.Organization
   RESULT = CreateEntity( mPrimReg, "Organization", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.PhysicalAddress
   RESULT = CreateEntity( mPrimReg, "PhysicalAddress", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.MailingAddress
   RESULT = CreateEntity( mPrimReg, "MailingAddress", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.ContactPerson
   RESULT = CreateEntity( mPrimReg, "ContactPerson", zPOS_AFTER );

   //:mPrimReg.PhysicalAddress.Country = "USA"
   SetAttributeFromString( mPrimReg, "PhysicalAddress", "Country", "USA" );
   //:mPrimReg.MailingAddress.Country = "USA"
   SetAttributeFromString( mPrimReg, "MailingAddress", "Country", "USA" );
   //:wWebXfer.Root.SameAs = "Y"
   SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );

   //:wWebXfer.Root.AttemptLoginName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptLoginName", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" )
   CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" );
   //:// CreateTemporalSubobjectVersion( mPrimReg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "ContactPerson" )

   //:IF wWebXfer.Root.KeyRole = "U"
   if ( CompareAttributeToString( wWebXfer, "Root", "KeyRole", "U" ) == 0 )
   { 

      //:mPrimReg.Organization.LoginName = "Admin"
      SetAttributeFromString( mPrimReg, "Organization", "LoginName", "Admin" );
      //:mPrimReg.Organization.Name = "Administrator"
      SetAttributeFromString( mPrimReg, "Organization", "Name", "Administrator" );
      //:mPrimReg.Organization.Description = "ePamms Administrator"
      SetAttributeFromString( mPrimReg, "Organization", "Description", "ePamms Administrator" );
      //:mPrimReg.Organization.Role = "P"
      SetAttributeFromString( mPrimReg, "Organization", "Role", "P" );
   } 


   //:END

   //:SetDynamicBannerName( ViewToWindow, "wSystem", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSystem", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectDeletePrimaryRegistrant( VIEW ViewToWindow )

public int 
SelectDeletePrimaryRegistrant( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do here other than to get proper position
// END
} 


//:DIALOG OPERATION
//:DeleteUser( VIEW ViewToWindow )

//:   VIEW mUser REGISTERED AS mUser
public int 
DeleteUser( View     ViewToWindow )
{
   zVIEW    mUser = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );

   //:IF mUser.User EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mUser, "User" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mUser.User
      RESULT = DeleteEntity( mUser, "User", zPOS_NEXT );
      //:COMMIT mUser
      RESULT = CommitObjectInstance( mUser );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveUser( VIEW ViewToWindow )

//:   VIEW mUser REGISTERED AS mUser
public int 
SaveUser( View     ViewToWindow )
{
   zVIEW    mUser = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );

   //:COMMIT mUser
   RESULT = CommitObjectInstance( mUser );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUser( VIEW ViewToWindow )

//:   VIEW mUser REGISTERED AS mUser
public int 
CancelUser( View     ViewToWindow )
{
   zVIEW    mUser = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mUser, "mUser", ViewToWindow, zLEVEL_TASK );

   //:DropView( mUser )
   DropView( mUser );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateUser( VIEW ViewToWindow )

//:   VIEW mUser      BASED ON LOD  mUser
public int 
UpdateUser( View     ViewToWindow )
{
   zVIEW    mUser = new zVIEW( );
   //:VIEW lUserLST   REGISTERED AS lUserLST
   zVIEW    lUserLST = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lUserLST, "lUserLST", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mUser WHERE mUser.User.ID = lUserLST.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lUserLST, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mUser "mUser"
   SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeletePreUser( VIEW ViewToWindow )

//:   VIEW lUserLST REGISTERED AS lUserLST
public int 
DeletePreUser( View     ViewToWindow )
{
   zVIEW    lUserLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mUser    BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lUserLST, "lUserLST", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mUser WHERE mUser.User.ID = lUserLST.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lUserLST, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mUser "mUser"
   SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddUser( VIEW ViewToWindow )

//:   VIEW mUser BASED ON LOD mUser
public int 
AddUser( View     ViewToWindow )
{
   zVIEW    mUser = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE mUser EMPTY
   RESULT = ActivateEmptyObjectInstance( mUser, "mUser", ViewToWindow, zSINGLE );
   //:NAME VIEW mUser "mUser"
   SetNameForView( mUser, "mUser", null, zLEVEL_TASK );
   //:CREATE ENTITY mUser.User
   RESULT = CreateEntity( mUser, "User", zPOS_AFTER );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdatePerson( VIEW ViewToWindow )

//:   VIEW mCurrentUser REGISTERED AS mCurrentUser
public int 
UpdatePerson( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPersonLST   REGISTERED AS lPersonLST
   zVIEW    lPersonLST = new zVIEW( );
   //:VIEW mPerson      BASED ON LOD  mPerson
   zVIEW    mPerson = new zVIEW( );
   //:// VIEW lDivSelect   REGISTERED AS lDivSelect
   //:VIEW wWebXfer     REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPersonLST, "lPersonLST", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mPerson WHERE mPerson.Person.ID = lPersonLST.Person.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPersonLST, "Person", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPerson "mPerson"
   SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
   //:mPerson.Person.ModifiedDateTime = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mPerson, "Person", "ModifiedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   return( 0 );
// /*
//    mPerson.Person.ModifiedUser = mCurrentUser.User.UserName
//    FOR EACH lDivSelect.Division
//      SET CURSOR FIRST mPerson.Division
//         WHERE mPerson.Division.DivisionID = lDivSelect.Division.DivisionID
//      IF RESULT >= zCURSOR_SET
//         lDivSelect.Division.wkSelected = "Y"
//      END
//    END
//    IF mPerson.Division EXISTS
//       SET CURSOR FIRST mCurrentUser.Division
//          WHERE mCurrentUser.Division.DivisionID = mPerson.Division.DivisionID
//    END
// */
// END
} 


//:DIALOG OPERATION
//:DeletePrePerson( VIEW ViewToWindow )
//:   VIEW lPersonLST REGISTERED AS lPersonLST
public int 
DeletePrePerson( View     ViewToWindow )
{
   zVIEW    lPersonLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPerson    BASED ON LOD mPerson
   zVIEW    mPerson = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lPersonLST, "lPersonLST", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mPerson
   //:   WHERE mPerson.Person.ID = lPersonLST.Person.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPersonLST, "Person", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPerson "mPerson"
   SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddPerson( VIEW ViewToWindow )

//:   VIEW mPerson      BASED ON LOD  mPerson
public int 
AddPerson( View     ViewToWindow )
{
   zVIEW    mPerson = new zVIEW( );
   //:VIEW wWebXfer     REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mPerson EMPTY
   RESULT = ActivateEmptyObjectInstance( mPerson, "mPerson", ViewToWindow, zSINGLE );
   //:NAME VIEW mPerson "mPerson"
   SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
   //:CREATE ENTITY  mPerson.Person
   RESULT = CreateEntity( mPerson, "Person", zPOS_AFTER );
   //:mPerson.Person.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mPerson, "Person", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:PostBuildPersons( VIEW ViewToWindow )

//:   VIEW lPersonLST    BASED ON LOD mPerson
public int 
PostBuildPersons( View     ViewToWindow )
{
   zVIEW    lPersonLST = new zVIEW( );
   //:VIEW mCurrentUser  REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE lPersonLST MULTIPLE
   RESULT = ActivateObjectInstance( lPersonLST, "mPerson", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lPersonLST "lPersonLST"
   SetNameForView( lPersonLST, "lPersonLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeletePerson( VIEW ViewToWindow )

//:   VIEW mPerson    REGISTERED AS mPerson
public int 
DeletePerson( View     ViewToWindow )
{
   zVIEW    mPerson = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );

   //:IF mPerson.Person EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mPerson, "Person" );
   if ( lTempInteger_0 == 0 )
   { 
      //:DELETE ENTITY mPerson.Person
      RESULT = DeleteEntity( mPerson, "Person", zPOS_NEXT );
      //:COMMIT mPerson
      RESULT = CommitObjectInstance( mPerson );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SavePerson( VIEW ViewToWindow )
//:   VIEW mPerson REGISTERED AS mPerson
public int 
SavePerson( View     ViewToWindow )
{
   zVIEW    mPerson = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPersonLST BASED ON LOD mPerson
   zVIEW    lPersonLST = new zVIEW( );
   //:STRING (  50  ) szNewCode
   String   szNewCode = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );

   //:// IncludePersonDivision( ViewToWindow )
   //:COMMIT mPerson
   RESULT = CommitObjectInstance( mPerson );

   //:GET VIEW  lPersonLST   NAMED "lPersonLST"
   RESULT = GetViewByName( lPersonLST, "lPersonLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0
   if ( RESULT > 0 )
   { 
      //:SET CURSOR FIRST lPersonLST.Person
      //:   WHERE lPersonLST.Person.ID = mPerson.Person.ID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mPerson, "Person", "ID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( lPersonLST, "Person", "ID", lTempInteger_0, "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:INCLUDE  lPersonLST.Person FROM  mPerson.Person
         RESULT = IncludeSubobjectFromSubobject( lPersonLST, "Person", mPerson, "Person", zPOS_AFTER );
         //:OrderEntityForView( lPersonLST, "Person", "LastName A" )
         OrderEntityForView( lPersonLST, "Person", "LastName A" );
         //:SET CURSOR FIRST lPersonLST.Person
         //:   WHERE lPersonLST.Person.ID = mPerson.Person.ID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mPerson, "Person", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( lPersonLST, "Person", "ID", lTempInteger_1, "" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:PostBuildUser( VIEW ViewToWindow )

//:   VIEW lUserLST BASED ON LOD mUser
public int 
PostBuildUser( View     ViewToWindow )
{
   zVIEW    lUserLST = new zVIEW( );
   int      RESULT = 0;


   //:ACTIVATE lUserLST MULTIPLE
   RESULT = ActivateObjectInstance( lUserLST, "mUser", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lUserLST "lUserLST"
   SetNameForView( lUserLST, "lUserLST", null, zLEVEL_TASK );
   return( 0 );
// // LoadPersonList( ViewToWindow )
// END
} 


//:DIALOG OPERATION
//:LoginClose( VIEW ViewToWindow )

//:   VIEW mCurrentUser REGISTERED AS mCurrentUser
public int 
LoginClose( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
   //:DropObjectInstance( wWebXfer )
   DropObjectInstance( wWebXfer );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:PostBuildPerson( VIEW ViewToWindow )

//:   VIEW lPersonLST   BASED ON LOD  mPerson
public int 
PostBuildPerson( View     ViewToWindow )
{
   zVIEW    lPersonLST = new zVIEW( );
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE lPersonLST MULTIPLE
   RESULT = ActivateObjectInstance( lPersonLST, "mPerson", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lPersonLST "lPersonLST"
   SetNameForView( lPersonLST, "lPersonLST", null, zLEVEL_TASK );
   return( 0 );
// // LoadPersonList( ViewToWindow )
// END
} 


//:DIALOG OPERATION
//:InitSystemProperties( VIEW ViewToWindow )

//:   VIEW mEPA   BASED ON LOD  mEPA
public int 
InitSystemProperties( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:GET VIEW  mEPA   NAMED "mEPA"
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );
   //:IF mEPA != 0
   if ( getView( mEPA ) != null )
   { 
      //:DropObjectInstance( mEPA )
      DropObjectInstance( mEPA );
   } 

   //:END

   //:ACTIVATE mEPA MULTIPLE
   RESULT = ActivateObjectInstance( mEPA, "mEPA", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW mEPA "mEPA"
   SetNameForView( mEPA, "mEPA", null, zLEVEL_TASK );
   //:IF mEPA.ePamms DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mEPA, "ePamms" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY mEPA.ePamms
      RESULT = CreateEntity( mEPA, "ePamms", zPOS_AFTER );
   } 

   //:END

   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "ePamms", "InitSystemProperties: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mEPA", "ePamms", "InitSystemProperties: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSystemChemicalForInsert( VIEW ViewToWindow )

public int 
InitSystemChemicalForInsert( View     ViewToWindow )
{

   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mEPA", "EPA_ChemicalFamily", "InitSystemChemicalForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mEPA", "EPA_ChemicalFamily", "InitSystemChemicalForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of surfaces.
   //:nRC = SetCursorFirstEntity( mEPA, "EPA_Surface", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_Surface", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:DeleteEntity( mEPA, "EPA_Surface", zREPOS_NONE )
      DeleteEntity( mEPA, "EPA_Surface", zREPOS_NONE );
      //:nRC = SetCursorNextEntity( mEPA, "EPA_Surface", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_Surface", "" );
   } 

   //:END

   //:// Set up Surfaces list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             mEPA, "EPA_Surface", "Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_Surface", "Name", 0 );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportVirusesList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportVirusesList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );

   //:// Clean out previous list of Viruses.
   //:nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:IF mEPA.EPA_Claim.ClaimsClassification = "Viruses"
      if ( CompareAttributeToString( mEPA, "EPA_Claim", "ClaimsClassification", "Viruses" ) == 0 )
      { 
         //:DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE )
         DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
   } 

   //:END

   //:// Set up Viruses list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             mEPA, "EPA_Claim", "ClaimsClassification=Viruses,Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_Claim", "ClaimsClassification=Viruses,Name", 0 );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportBacteriaList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportBacteriaList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );

   //:// Clean out previous list of Bacteria.
   //:nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:IF mEPA.EPA_Claim.ClaimsClassification = "Bacteria"
      if ( CompareAttributeToString( mEPA, "EPA_Claim", "ClaimsClassification", "Bacteria" ) == 0 )
      { 
         //:DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE )
         DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
   } 

   //:END

   //:// Set up Bacteria list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             mEPA, "EPA_Claim", "ClaimsClassification=Bacteria,Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_Claim", "ClaimsClassification=Bacteria,Name", 0 );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmImportFungiList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmImportFungiList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );

   //:// Clean out previous list of Fungi.
   //:nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_Claim", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:IF mEPA.EPA_Claim.ClaimsClassification = "Fungi"
      if ( CompareAttributeToString( mEPA, "EPA_Claim", "ClaimsClassification", "Fungi" ) == 0 )
      { 
         //:DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE )
         DeleteEntity( mEPA, "EPA_Claim", zREPOS_NONE );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_Claim", "" );
   } 

   //:END

   //:// Set up Fungi list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             mEPA, "EPA_Claim", "ClaimsClassification=Fungi,Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_Claim", "ClaimsClassification=Fungi,Name", 0 );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
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
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Clean out previous list of areas of use.
   //:nRC = SetCursorFirstEntity( mEPA, "EPA_AreaOfUse", "" )
   nRC = SetCursorFirstEntity( mEPA, "EPA_AreaOfUse", "" );
   //:LOOP WHILE nRC = zCURSOR_SET
   while ( nRC == zCURSOR_SET )
   { 
      //:DeleteEntity( mEPA, "EPA_AreaOfUse", zREPOS_NONE )
      DeleteEntity( mEPA, "EPA_AreaOfUse", zREPOS_NONE );
      //:nRC = SetCursorNextEntity( mEPA, "EPA_AreaOfUse", "" )
      nRC = SetCursorNextEntity( mEPA, "EPA_AreaOfUse", "" );
   } 

   //:END

   //:// Set up Areas of Use list in wWebXfer.Root.CurrentStatementText
   //:// to be set to multiline edit box.
   //:BuildEntityAttributeFromCSV( wWebXfer, "Root",
   //:                             "CurrentStatementText",
   //:                             mEPA, "EPA_AreaOfUse", "Name", 0 )
   m_ZDRVROPR.BuildEntityAttributeFromCSV( wWebXfer, "Root", "CurrentStatementText", mEPA, "EPA_AreaOfUse", "Name", 0 );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewAreasOfUse( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewAreasOfUse( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Name guaranteed to be non-blank in JSP
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewAreasOfUse: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewAreasOfUse: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mEPA.EPA_AreaOfUse
   RESULT = CreateEntity( mEPA, "EPA_AreaOfUse", zPOS_AFTER );
   //:mEPA.EPA_AreaOfUse.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_AreaOfUse", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:mEPA.EPA_AreaOfUse.BoldItalic = wWebXfer.Root.CurrentBoldItalic
   SetAttributeFromAttribute( mEPA, "EPA_AreaOfUse", "BoldItalic", wWebXfer, "Root", "CurrentBoldItalic" );
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.CurrentBoldItalic = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentBoldItalic", "" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewAppType( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewAppType( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Name guaranteed to be non-blank in JSP
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewAppType: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewAppType: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mEPA.EPA_ApplicationType
   RESULT = CreateEntity( mEPA, "EPA_ApplicationType", zPOS_AFTER );
   //:mEPA.EPA_ApplicationType.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_ApplicationType", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:mEPA.EPA_ApplicationType.BoldItalic = wWebXfer.Root.CurrentBoldItalic
   SetAttributeFromAttribute( mEPA, "EPA_ApplicationType", "BoldItalic", wWebXfer, "Root", "CurrentBoldItalic" );
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.CurrentBoldItalic = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentBoldItalic", "" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewSurface( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewSurface( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Name guaranteed to be non-blank in JSP
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewSurface: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewSurface: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mEPA.EPA_Surface
   RESULT = CreateEntity( mEPA, "EPA_Surface", zPOS_AFTER );
   //:mEPA.EPA_Surface.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Surface", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:mEPA.EPA_Surface.BoldItalic = wWebXfer.Root.CurrentBoldItalic
   SetAttributeFromAttribute( mEPA, "EPA_Surface", "BoldItalic", wWebXfer, "Root", "CurrentBoldItalic" );
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.CurrentBoldItalic = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentBoldItalic", "" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewBacteria( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewBacteria( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Name guaranteed to be non-blank in JSP
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewBacteria: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewBacteria: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mEPA.EPA_Claim
   RESULT = CreateEntity( mEPA, "EPA_Claim", zPOS_AFTER );
   //:mEPA.EPA_Claim.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:mEPA.EPA_Claim.BoldItalic = wWebXfer.Root.CurrentBoldItalic
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "BoldItalic", wWebXfer, "Root", "CurrentBoldItalic" );
   //:mEPA.EPA_Claim.ClaimsClassification = "Bacteria"
   SetAttributeFromString( mEPA, "EPA_Claim", "ClaimsClassification", "Bacteria" );
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.CurrentBoldItalic = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentBoldItalic", "" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewFungi( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewFungi( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Name guaranteed to be non-blank in JSP
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewFungi: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewFungi: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mEPA.EPA_Claim
   RESULT = CreateEntity( mEPA, "EPA_Claim", zPOS_AFTER );
   //:mEPA.EPA_Claim.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:mEPA.EPA_Claim.BoldItalic = wWebXfer.Root.CurrentBoldItalic
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "BoldItalic", wWebXfer, "Root", "CurrentBoldItalic" );
   //:mEPA.EPA_Claim.ClaimsClassification = "Fungi"
   SetAttributeFromString( mEPA, "EPA_Claim", "ClaimsClassification", "Fungi" );
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.CurrentBoldItalic = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentBoldItalic", "" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewVirus( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewVirus( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:// Name guaranteed to be non-blank in JSP
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewVirus: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "NewVirus: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mEPA.EPA_Claim
   RESULT = CreateEntity( mEPA, "EPA_Claim", zPOS_AFTER );
   //:mEPA.EPA_Claim.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:mEPA.EPA_Claim.BoldItalic = wWebXfer.Root.CurrentBoldItalic
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "BoldItalic", wWebXfer, "Root", "CurrentBoldItalic" );
   //:mEPA.EPA_Claim.ClaimsClassification = "Viruses"
   SetAttributeFromString( mEPA, "EPA_Claim", "ClaimsClassification", "Viruses" );
   //:wWebXfer.Root.CurrentStatement = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentStatement", "" );
   //:wWebXfer.Root.CurrentBoldItalic = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentBoldItalic", "" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateBacteria( View     ViewToWindow )
{

   //:UpdateBacteria( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateBacteria: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateBacteria: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateSurface( VIEW ViewToWindow )

//:   VIEW mEPA   REGISTERED AS mEPA
public int 
UpdateSurface( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateSurface: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateSurface: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateAreasOfUse( View     ViewToWindow )
{

   //:UpdateAreasOfUse( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateAreasOfUse: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateAreasOfUse: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateAreasOfUseFromCurrent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
UpdateAreasOfUseFromCurrent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateAreasOfUseFromCurrent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateAreasOfUseFromCurrent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mEPA.EPA_AreaOfUse.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_AreaOfUse", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateBacteriaFromCurrent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
UpdateBacteriaFromCurrent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateBacteriaFromCurrent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateBacteriaFromCurrent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mEPA.EPA_Claim.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateVirusesFromCurrent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
UpdateVirusesFromCurrent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateVirusesFromCurrent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateVirusesFromCurrent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mEPA.EPA_Claim.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateSurfaceFromCurrent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
UpdateSurfaceFromCurrent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateSurfaceFromCurrent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateSurfaceFromCurrent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mEPA.EPA_Surface.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Surface", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateAppTypeFromCurrent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
UpdateAppTypeFromCurrent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateAppTypeFromCurrent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateAppTypeFromCurrent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mEPA.EPA_ApplicationType.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_ApplicationType", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReturnFromAnalysis( VIEW ViewToWindow )

//:   VIEW lMLCATgt BASED ON LOD  lMLCATgt
public int 
ReturnFromAnalysis( View     ViewToWindow )
{
   zVIEW    lMLCATgt = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lMLCATgt NAMED "lMLCATgt"
   RESULT = GetViewByName( lMLCATgt, "lMLCATgt", ViewToWindow, zLEVEL_TASK );
   //:IF lMLCATgt != 0
   if ( getView( lMLCATgt ) != null )
   { 
      //:DropObjectInstance( lMLCATgt )
      DropObjectInstance( lMLCATgt );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSystemProperties( VIEW ViewToWindow )

//:   VIEW mEPA   REGISTERED AS mEPA
public int 
AcceptSystemProperties( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptSystemProperties: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptSystemProperties: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelSystemProperties( VIEW ViewToWindow )

//:   VIEW mEPA   REGISTERED AS mEPA
public int 
CancelSystemProperties( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelSystemProperties: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelSystemProperties: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitAnalysisDifferences( View     ViewToWindow )
{

   //:InitAnalysisDifferences( VIEW ViewToWindow )

   //:SetDynamicBannerName( ViewToWindow, "wSystem", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSystem", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitShowFeedback( VIEW ViewToWindow )

//:   VIEW mOrganiz BASED ON LOD  mOrganiz
public int 
InitShowFeedback( View     ViewToWindow )
{
   zVIEW    mOrganiz = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW mOrganiz NAMED "mOrganiz"
   RESULT = GetViewByName( mOrganiz, "mOrganiz", ViewToWindow, zLEVEL_TASK );
   //:IF mOrganiz != 0
   if ( getView( mOrganiz ) != null )
   { 
      //:DropObjectInstance( mOrganiz )
      DropObjectInstance( mOrganiz );
   } 

   //:END

   //:ACTIVATE mOrganiz MULTIPLE
   RESULT = ActivateObjectInstance( mOrganiz, "mOrganiz", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW mOrganiz "mOrganiz"
   SetNameForView( mOrganiz, "mOrganiz", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wSystem", "InitShowFeedback" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSystem", "InitShowFeedback" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewSystemKeyword( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
AcceptNewSystemKeyword( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSystemKeyword( VIEW ViewToWindow )

//:   VIEW mEPA     REGISTERED AS mEPA
public int 
DeleteSystemKeyword( View     ViewToWindow )
{
   zVIEW    mEPA = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mEPA.Keyword
   RESULT = DeleteEntity( mEPA, "Keyword", zPOS_NEXT );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   //:DropObjectInstance( mEPA )
   DropObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateSystemKeyword( View     ViewToWindow )
{

   //:UpdateSystemKeyword( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateSystemKeyword: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateSystemKeyword: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateKeywordAndAddNew( View     ViewToWindow )
{

   //:UpdateKeywordAndAddNew( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateKeywordAndAddNew: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateKeywordAndAddNew: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// // CreateCurrentTemporalVersion( ViewToWindow, zPOS_NEXT, "mEPA", "Keyword", "UpdateKeywordAndAddNew: " )
// END
} 


//:DIALOG OPERATION
//:UpdateFungiFromCurrent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
UpdateFungiFromCurrent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateFungiFromCurrent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateFungiFromCurrent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mEPA.EPA_Claim.Name = wWebXfer.Root.CurrentStatement
   SetAttributeFromAttribute( mEPA, "EPA_Claim", "Name", wWebXfer, "Root", "CurrentStatement" );
   //:COMMIT mEPA
   RESULT = CommitObjectInstance( mEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateFungi( View     ViewToWindow )
{

   //:UpdateFungi( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateFungi: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateFungi: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
UpdateViruses( View     ViewToWindow )
{

   //:UpdateViruses( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateViruses: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateViruses: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
AddKeywordAndAddNew( View     ViewToWindow )
{

   //:AddKeywordAndAddNew( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddKeywordAndAddNew: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AddKeywordAndAddNew: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// // CreateCurrentTemporalVersion( ViewToWindow, zPOS_NEXT, "mEPA", "Keyword", "AddKeywordAndAddNew: " )
// END
} 


//:LOCAL OPERATION
//:LoadUsageList( VIEW ViewToWindow,
//:               VIEW mEPA BASED ON LOD mEPA,
//:               STRING ( 32 ) szClaimsClassification )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadUsageList( View     ViewToWindow,
                 View     mEPA,
                 String   szClaimsClassification )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPosEPA  BASED ON LOD  mEPA
   zVIEW    mPosEPA = new zVIEW( );
   //:STRING ( 32 )  szCurrentClaimsClassification
   String   szCurrentClaimsClassification = null;
   //:INTEGER  lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mPosEPA, mEPA )
   CreateViewFromView( mPosEPA, mEPA );
   //:NAME VIEW mPosEPA "mPosEPA"
   SetNameForView( mPosEPA, "mPosEPA", null, zLEVEL_TASK );

   //:// Create Classification entities for each matching EPA_Claim entity.
   //:FOR EACH mPosEPA.EPA_Claim
   RESULT = SetCursorFirstEntity( mPosEPA, "EPA_Claim", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF mPosEPA.Bacteria EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( mPosEPA, "Bacteria" );
      if ( lTempInteger_0 == 0 )
      { 
         //:DELETE ENTITY mPosEPA.Bacteria
         RESULT = DeleteEntity( mPosEPA, "Bacteria", zPOS_NEXT );
      } 

      //:END

      //:IF mPosEPA.Fungi EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( mPosEPA, "Fungi" );
      if ( lTempInteger_1 == 0 )
      { 
         //:DELETE ENTITY mPosEPA.Fungi
         RESULT = DeleteEntity( mPosEPA, "Fungi", zPOS_NEXT );
      } 

      //:END

      //:IF mPosEPA.Viruses EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( mPosEPA, "Viruses" );
      if ( lTempInteger_2 == 0 )
      { 
         //:DELETE ENTITY mPosEPA.Viruses
         RESULT = DeleteEntity( mPosEPA, "Viruses", zPOS_NEXT );
      } 

      //:END

      //:IF mPosEPA.Protozoa EXISTS
      lTempInteger_3 = CheckExistenceOfEntity( mPosEPA, "Protozoa" );
      if ( lTempInteger_3 == 0 )
      { 
         //:DELETE ENTITY mPosEPA.Protozoa
         RESULT = DeleteEntity( mPosEPA, "Protozoa", zPOS_NEXT );
      } 

      //:END

      //:IF szClaimsClassification != ""
      if ( ZeidonStringCompare( szClaimsClassification, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:szCurrentClaimsClassification = mPosEPA.EPA_Claim.ClaimsClassification
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szCurrentClaimsClassification;
         if ( szCurrentClaimsClassification == null )
            sb_szCurrentClaimsClassification = new StringBuilder( 32 );
         else
            sb_szCurrentClaimsClassification = new StringBuilder( szCurrentClaimsClassification );
                   GetVariableFromAttribute( sb_szCurrentClaimsClassification, mi_lTempInteger_4, 'S', 33, mPosEPA, "EPA_Claim", "ClaimsClassification", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szCurrentClaimsClassification = sb_szCurrentClaimsClassification.toString( );}
         //:IF szCurrentClaimsClassification = szClaimsClassification
         if ( ZeidonStringCompare( szCurrentClaimsClassification, 1, 0, szClaimsClassification, 1, 0, 33 ) == 0 )
         { 
            //:CreateEntity( mPosEPA, szClaimsClassification, zPOS_AFTER )
            CreateEntity( mPosEPA, szClaimsClassification, zPOS_AFTER );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mPosEPA, "EPA_Claim", "" );
      //:END
   } 

   //:END

   //:DropView( mPosEPA )
   DropView( mPosEPA );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteFeedbackComment( VIEW ViewToWindow )

public int 
DeleteFeedbackComment( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteAllFeedbackComments( VIEW ViewToWindow )

//:   VIEW mOrganiz REGISTERED AS mOrganiz
public int 
DeleteAllFeedbackComments( View     ViewToWindow )
{
   zVIEW    mOrganiz = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mOrganiz, "mOrganiz", ViewToWindow, zLEVEL_TASK );

   //:// Validated that user wants to delete all Feedback Comments in JSP.
   //:FOR EACH mOrganiz.Organization
   RESULT = SetCursorFirstEntity( mOrganiz, "Organization", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mOrganiz.Feedback
      RESULT = SetCursorFirstEntity( mOrganiz, "Feedback", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY mOrganiz.Feedback NONE
         RESULT = DeleteEntity( mOrganiz, "Feedback", zREPOS_NONE );
         RESULT = SetCursorNextEntity( mOrganiz, "Feedback", "" );
      } 

      RESULT = SetCursorNextEntity( mOrganiz, "Organization", "" );
      //:END
   } 

   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteAllFeedbackComments: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteAllFeedbackComments: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mOrganiz
   RESULT = CommitObjectInstance( mOrganiz );
   //:DropObjectInstance( mOrganiz )
   DropObjectInstance( mOrganiz );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteBlankFeedback( VIEW ViewToWindow )

//:   VIEW mOrganiz REGISTERED AS mOrganiz
public int 
DeleteBlankFeedback( View     ViewToWindow )
{
   zVIEW    mOrganiz = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mOrganiz, "mOrganiz", ViewToWindow, zLEVEL_TASK );

   //:FOR EACH mOrganiz.Organization
   RESULT = SetCursorFirstEntity( mOrganiz, "Organization", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH mOrganiz.Feedback
      RESULT = SetCursorFirstEntity( mOrganiz, "Feedback", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF mOrganiz.Feedback.Comment = ""
         if ( CompareAttributeToString( mOrganiz, "Feedback", "Comment", "" ) == 0 )
         { 
            //:DELETE ENTITY mOrganiz.Feedback NONE
            RESULT = DeleteEntity( mOrganiz, "Feedback", zREPOS_NONE );
         } 

         RESULT = SetCursorNextEntity( mOrganiz, "Feedback", "" );
         //:END
      } 

      RESULT = SetCursorNextEntity( mOrganiz, "Organization", "" );
      //:END
   } 

   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteBlankFeedback: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "DeleteBlankFeedback: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mOrganiz
   RESULT = CommitObjectInstance( mOrganiz );
   //:DropObjectInstance( mOrganiz )
   DropObjectInstance( mOrganiz );
   return( 0 );
// END
} 



}
