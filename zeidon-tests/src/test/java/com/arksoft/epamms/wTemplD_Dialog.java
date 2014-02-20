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
import com.arksoft.epamms.mTempl_Object;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class wTemplD_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public wTemplD_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:DIALOG OPERATION
//:InitTemplateList( VIEW ViewToWindow )

//:   VIEW lTemplLST BASED ON LOD lTempl
public int 
InitTemplateList( View     ViewToWindow )
{
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lTemplLST NAMED "lTemplLST"
   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( lTemplLST )
      DropObjectInstance( lTemplLST );
   } 

   //:END

   //:ACTIVATE lTemplLST RootOnlyMultiple
   RESULT = ActivateObjectInstance( lTemplLST, "lTempl", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW lTemplLST "lTemplLST"
   SetNameForView( lTemplLST, "lTemplLST", null, zLEVEL_TASK );
   //:SetDynamicBannerName( ViewToWindow, "wTemplD", "LabelTemplate" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wTemplD", "LabelTemplate" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Template" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Template" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Template" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Template" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:DeleteDisplayStatement( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
DeleteDisplayStatement( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 10 ) szEntityType
   String   szEntityType = null;
   //:STRING ( 32 ) szTemplateEntity
   String   szTemplateEntity = null;
   //:INTEGER lControl
   int      lControl = 0;
   //:INTEGER lEntityID
   int      lEntityID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:nRC = zCALL_ERROR
   nRC = zCALL_ERROR;
   //:lEntityID = mTempl.DisplayStatement.EntityID
   {MutableInt mi_lEntityID = new MutableInt( lEntityID );
       GetIntegerFromAttribute( mi_lEntityID, mTempl, "DisplayStatement", "EntityID" );
   lEntityID = mi_lEntityID.intValue( );}
   //:lControl = zQUAL_INTEGER + zPOS_FIRST + zQUAL_SCOPE_OI
   lControl = zQUAL_INTEGER + zPOS_FIRST + zQUAL_SCOPE_OI;
   //:szEntityType = mTempl.DisplayStatement.EntityType
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityType;
   if ( szEntityType == null )
      sb_szEntityType = new StringBuilder( 32 );
   else
      sb_szEntityType = new StringBuilder( szEntityType );
       GetVariableFromAttribute( sb_szEntityType, mi_lTempInteger_0, 'S', 11, mTempl, "DisplayStatement", "EntityType", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityType = sb_szEntityType.toString( );}
   //:szTemplateEntity = "Template" + szEntityType
    {StringBuilder sb_szTemplateEntity;
   if ( szTemplateEntity == null )
      sb_szTemplateEntity = new StringBuilder( 32 );
   else
      sb_szTemplateEntity = new StringBuilder( szTemplateEntity );
      ZeidonStringCopy( sb_szTemplateEntity, 1, 0, "Template", 1, 0, 33 );
   szTemplateEntity = sb_szTemplateEntity.toString( );}
    {StringBuilder sb_szTemplateEntity;
   if ( szTemplateEntity == null )
      sb_szTemplateEntity = new StringBuilder( 32 );
   else
      sb_szTemplateEntity = new StringBuilder( szTemplateEntity );
      ZeidonStringConcat( sb_szTemplateEntity, 1, 0, szEntityType, 1, 0, 33 );
   szTemplateEntity = sb_szTemplateEntity.toString( );}

   //:// Position on the real entity in the hierarchical structure.
   //:// Then, delete that entity as well as the DisplayStatement entity.
   //:nRC = SetEntityCursor( mTempl, szTemplateEntity, "wTempID", lControl,
   //:                       lEntityID, "", "", 0, "", "" )
   nRC = SetEntityCursor( mTempl, szTemplateEntity, "wTempID", lControl, lEntityID, "", "", 0, "", "" );
   //:IF nRC >= zCURSOR_UNCHANGED
   if ( nRC >= zCURSOR_UNCHANGED )
   { 

      //:IF szEntityType = "Panel"
      if ( ZeidonStringCompare( szEntityType, 1, 0, "Panel", 1, 0, 11 ) == 0 )
      { 
         //:DELETE ENTITY mTempl.TemplatePanel
         RESULT = DeleteEntity( mTempl, "TemplatePanel", zPOS_NEXT );
         //:ELSE
      } 
      else
      { 
         //:IF szEntityType = "Group"
         if ( ZeidonStringCompare( szEntityType, 1, 0, "Group", 1, 0, 11 ) == 0 )
         { 
            //:DELETE ENTITY mTempl.TemplateGroup
            RESULT = DeleteEntity( mTempl, "TemplateGroup", zPOS_NEXT );
            //:ELSE
         } 
         else
         { 
            //:IF szEntityType = "Section"
            if ( ZeidonStringCompare( szEntityType, 1, 0, "Section", 1, 0, 11 ) == 0 )
            { 
               //:DELETE ENTITY mTempl.TemplateSection
               RESULT = DeleteEntity( mTempl, "TemplateSection", zPOS_NEXT );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:DELETE ENTITY mTempl.DisplayStatement
      RESULT = DeleteEntity( mTempl, "DisplayStatement", zPOS_NEXT );
      //:COMMIT mTempl
      RESULT = CommitObjectInstance( mTempl );
      //:// DropObjectInstance( mTempl )

      //:ELSE
   } 
   else
   { 

      //:// Template statement not found!
      //:TraceLineS( "Template statement not found: ", szTemplateEntity )
      TraceLineS( "Template statement not found: ", szTemplateEntity );
      //:MessageSend( ViewToWindow, "", "Delete Display Statement",
      //:             "Template Statement not found.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Display Statement", "Template Statement not found.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:CancelTemplateGroup( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
CancelTemplateGroup( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mTempl, "TemplateGroup" )
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplateGroup: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplateGroup: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplateGroup( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
DeleteTemplateGroup( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mTempl.TemplateGroup
   RESULT = DeleteEntity( mTempl, "TemplateGroup", zPOS_NEXT );
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplateGroup( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
UpdateTemplateGroup( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplateGroup: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplateGroup: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// CreateTemporalSubobjectVersion( mTempl, "TemplateGroup" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateGroup", "UpdateTemplateGroup: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateGroup", "UpdateTemplateGroup: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewTemplateSection( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
NewTemplateSection( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// CreateTemporalEntity( mTempl, "TemplateSection", zPOS_AFTER )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateSection", "NewTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateSection", "NewTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mTempl.TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptTemplateSection( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
AcceptTemplateSection( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mTempl, "TemplateSection" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );

   //:// If the previous window is the Display List, then rebuild the list.
   //:// IF KZXMLPGO.PagePath.LastPageName = "wTemplDTemplateStatementListAll"
   //:   BuildDisplayStructure( mTempl )
   {
    mTempl_Object m_mTempl_Object = new mTempl_Object( mTempl );
    m_mTempl_Object.omTempl_BuildDisplayStructure( mTempl );
    // m_mTempl_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// // END
// END
} 


//:DIALOG OPERATION
//:CancelTemplateSection( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
CancelTemplateSection( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mTempl, "TemplateSection" )
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplateSection( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
DeleteTemplateSection( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mTempl.TemplateSection
   RESULT = DeleteEntity( mTempl, "TemplateSection", zPOS_NEXT );
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplateSection( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
UpdateTemplateSection( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// CreateTemporalSubobjectVersion( mTempl, "TemplateSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateSection", "UpdateTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateSection", "UpdateTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAndReturnTemplate( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
SaveAndReturnTemplate( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// DeleteEntity( mTempl, "Template", zREPOS_FIRST );
   //:// CommitObjectInstance( mTempl );
   //:// DropObjectInstance( mTempl );
   //:// ActivateOI_FromFile( &mTempl, "mTempl", ViewToWindow,
   //://                      "c:\\lplr\\ePamms\\mTempl1.por", zIGNORE_ERRORS );
   //:// SetNameForView( mTempl, "mTempl", 0, zLEVEL_TASK );
   //:// MiSetInstanceUpdateFlag( mTempl, 1 );
   //:// MessageSend( ViewToWindow, "", "Save Template",
   //://              "Check view: mTempl", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

   //:IF mTempl.Template.Name = ""
   if ( CompareAttributeToString( mTempl, "Template", "Name", "" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Save Template",
      //:             "The Template Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Save Template", "The Template Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2      // return 2 to indicate Web client must prompt operator
      if(8==8)return( 2 );
   } 

   //:END

   //:IF mTempl.Template.CSS_FileName = ""
   if ( CompareAttributeToString( mTempl, "Template", "CSS_FileName", "" ) == 0 )
   { 
      //:mTempl.Template.CSS_FileName = mTempl.Template.Name
      SetAttributeFromAttribute( mTempl, "Template", "CSS_FileName", mTempl, "Template", "Name" );
   } 

   //:END

   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );
   //:DropObjectInstance( mTempl )
   DropObjectInstance( mTempl );
   return( 0 );
// // InitTemplateList( ViewToWindow )
// END
} 


//:DIALOG OPERATION
//:AddNewTemplate( VIEW ViewToWindow )

//:   VIEW mTempl BASED ON LOD mTempl
public int 
AddNewTemplate( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW mTempl NAMED "mTempl"
   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mTempl )
      DropObjectInstance( mTempl );
   } 

   //:END

   //:ACTIVATE mTempl EMPTY
   RESULT = ActivateEmptyObjectInstance( mTempl, "mTempl", ViewToWindow, zSINGLE );
   //:NAME VIEW mTempl "mTempl"
   SetNameForView( mTempl, "mTempl", null, zLEVEL_TASK );
   //:CREATE ENTITY mTempl.Template
   RESULT = CreateEntity( mTempl, "Template", zPOS_AFTER );
   //:CREATE ENTITY mTempl.TemplatePanel
   RESULT = CreateEntity( mTempl, "TemplatePanel", zPOS_AFTER );
   //:CREATE ENTITY mTempl.TemplateGroup
   RESULT = CreateEntity( mTempl, "TemplateGroup", zPOS_AFTER );
   //:mTempl.TemplateGroup.Name = "New"
   SetAttributeFromString( mTempl, "TemplateGroup", "Name", "New" );
   //:CREATE ENTITY mTempl.TemplateSection
   RESULT = CreateEntity( mTempl, "TemplateSection", zPOS_AFTER );
   //:mTempl.TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
   //:BuildDisplayStructure( mTempl )
   {
    mTempl_Object m_mTempl_Object = new mTempl_Object( mTempl );
    m_mTempl_Object.omTempl_BuildDisplayStructure( mTempl );
    // m_mTempl_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelAndReturnTemplate( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
CancelAndReturnTemplate( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mTempl )
   DropObjectInstance( mTempl );
   return( 0 );
// // InitTemplateList( ViewToWindow )
// END
} 


//:DIALOG OPERATION
//:UpdateDisplayStatement( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
UpdateDisplayStatement( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateDisplayStatement: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateDisplayStatement: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// Position on the real entity in the hierarchical structure.
   //:// Then, create that entity as temporal and go to the correct window based on type.
   //:IF mTempl.DisplayStatement.EntityType = "Panel"
   if ( CompareAttributeToString( mTempl, "DisplayStatement", "EntityType", "Panel" ) == 0 )
   { 

      //:// Panel
      //:SET CURSOR FIRST mTempl.TemplatePanel
      //:           WHERE mTempl.TemplatePanel.wTempID = mTempl.DisplayStatement.EntityID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mTempl, "DisplayStatement", "EntityID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mTempl, "TemplatePanel", "wTempID", lTempInteger_0, "" );
      //:// CreateTemporalSubobjectVersion( mTempl, "TemplatePanel" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplatePanel", "UpdateDisplayStatement: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplatePanel", "UpdateDisplayStatement: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplatePanelDetail" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplatePanelDetail" );
      //:RETURN 1
      if(8==8)return( 1 );

      //:ELSE
   } 
   else
   { 
      //:IF mTempl.DisplayStatement.EntityType = "Group"
      if ( CompareAttributeToString( mTempl, "DisplayStatement", "EntityType", "Group" ) == 0 )
      { 

         //:// Group
         //:SET CURSOR FIRST mTempl.TemplateGroup WITHIN mTempl.Template
         //:        WHERE mTempl.TemplateGroup.wTempID = mTempl.DisplayStatement.EntityID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mTempl, "DisplayStatement", "EntityID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mTempl, "TemplateGroup", "wTempID", lTempInteger_1, "Template" );
         //:// CreateTemporalSubobjectVersion( mTempl, "TemplateGroup" )
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateGroup", "UpdateDisplayStatement: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateGroup", "UpdateDisplayStatement: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateGroupDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateGroupDetail" );
         //:RETURN 1
         if(8==8)return( 1 );

         //:ELSE
      } 
      else
      { 
         //:// Section
         //:SET CURSOR FIRST mTempl.TemplateSection WITHIN mTempl.Template
         //:        WHERE mTempl.TemplateSection.wTempID = mTempl.DisplayStatement.EntityID
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mTempl, "DisplayStatement", "EntityID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mTempl, "TemplateSection", "wTempID", lTempInteger_2, "Template" );
         //:// CreateTemporalSubobjectVersion( mTempl, "TemplateSection" )
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateSection", "UpdateDisplayStatement: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplateSection", "UpdateDisplayStatement: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateSectionDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateSectionDetail" );
         //:RETURN 1
         if(8==8)return( 1 );
      } 


      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ListAllStatements( VIEW ViewToWindow )

public int 
ListAllStatements( View     ViewToWindow )
{

   return( 0 );
//  /*VIEW mTempl     REGISTERED AS mTempl
//    VIEW mTemplHier BASED ON LOD mTempl
//    STRING ( 32 ) szEntityName
//    STRING ( 50 ) szSectionType
//    INTEGER       lTempID
//    SHORT         lLevel
//    SHORT         nRC
//    // Build the DisplayStatement structure, which is a single list of all Panel, Group and Section entries.
//    SET CURSOR FIRST mTempl.TemplatePanel
//    CreateViewFromView( mTemplHier, mTempl )
//    DefineHierarchicalCursor( mTemplHier, "Template" )
//    lTempID = 0
//    nRC = SetCursorNextEntityHierarchical( lLevel, szEntityName, mTemplHier )
//    LOOP WHILE nRC >= zCURSOR_SET
//       lTempID = lTempID + 1
//       IF szEntityName = "TemplatePanel"
//          // PANEL
//          CREATE ENTITY mTempl.DisplayStatement
//          mTempl.DisplayStatement.EntityType = "Panel"
//          mTempl.DisplayStatement.DisplayText = mTemplHier.TemplatePanel.dPanelName
//          mTempl.TemplatePanel.wTempID = lTempID
//          mTempl.DisplayStatement.EntityID = lTempID
//       ELSE
//       IF szEntityName = "TemplateGroup"
//          // GROUP
//          CREATE ENTITY mTempl.DisplayStatement
//          mTempl.DisplayStatement.EntityType = "Group"
//          mTempl.DisplayStatement.DisplayText = "    " + mTemplHier.TemplateGroup.dBlockName
//          mTempl.TemplateGroup.wTempID = lTempID
//          mTempl.DisplayStatement.EntityID = lTempID
//       ELSE
//       IF szEntityName = "TemplateSection"
//          // SECTION
//          CREATE ENTITY mTempl.DisplayStatement
//          mTempl.DisplayStatement.EntityType = "Section"
//          GetStringFromAttributeByContext( szSectionType, mTempl, "TemplateSection", "SectionType", "", 50 )
//          mTempl.DisplayStatement.DisplayText = "        " + szSectionType
//          mTempl.TemplateSection.wTempID = lTempID
//          mTempl.DisplayStatement.EntityID = lTempID
//       END
//       END
//       END
//       nRC = SetCursorNextEntityHierarchical( lLevel, szEntityName, mTemplHier )
//    END
//    DropHierarchicalCursor( mTemplHier )
//    DropView( mTemplHier )
//    // Save generated ID for next assignment.
//    lTempID = lTempID + 1
//    mTempl.Template.wNextTempID = lTempID*/
// END
} 


//:DIALOG OPERATION
//:UpdateTemplate( VIEW ViewToWindow )

//:   VIEW lTemplLST REGISTERED AS lTemplLST
public int 
UpdateTemplate( View     ViewToWindow )
{
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempl    BASED ON LOD  mTempl
   zVIEW    mTempl = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mTempl NAMED "mTempl"
   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );
   //:IF mTempl != 0
   if ( getView( mTempl ) != null )
   { 
      //:DropObjectInstance( mTempl )
      DropObjectInstance( mTempl );
   } 

   //:END

   //:ACTIVATE mTempl WHERE mTempl.Template.ID = lTemplLST.Template.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lTemplLST, "Template", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mTempl, "mTempl", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mTempl "mTempl"
   SetNameForView( mTempl, "mTempl", null, zLEVEL_TASK );

   //:BuildDisplayStructure( mTempl )
   {
    mTempl_Object m_mTempl_Object = new mTempl_Object( mTempl );
    m_mTempl_Object.omTempl_BuildDisplayStructure( mTempl );
    // m_mTempl_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptTemplateGroup( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
AcceptTemplateGroup( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mTempl, "TemplateGroup" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateGroup: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateGroup: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );

   //:// If the previous window is the Display List, then rebuild the list.
   //:// IF KZXMLPGO.PagePath.LastPageName = "wTemplDTemplateStatementListAll"
   //:   BuildDisplayStructure( mTempl )
   {
    mTempl_Object m_mTempl_Object = new mTempl_Object( mTempl );
    m_mTempl_Object.omTempl_BuildDisplayStructure( mTempl );
    // m_mTempl_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// // END
// END
} 


//:DIALOG OPERATION
//:NewTemplatePanel( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
NewTemplatePanel( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// CreateTemporalEntity( mTempl, "TemplatePanel", zPOS_AFTER )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplatePanel", "NewTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplatePanel", "NewTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mTempl.TemplateGroup
   RESULT = CreateEntity( mTempl, "TemplateGroup", zPOS_AFTER );
   //:mTempl.TemplateGroup.Name = "New"
   SetAttributeFromString( mTempl, "TemplateGroup", "Name", "New" );
   //:CREATE ENTITY mTempl.TemplateSection
   RESULT = CreateEntity( mTempl, "TemplateSection", zPOS_AFTER );
   //:mTempl.TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptTemplatePanel( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
AcceptTemplatePanel( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mTempl, "TemplatePanel" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );

   //:// If the previous window is the Display List, then rebuild the list.
   //:// IF KZXMLPGO.PagePath.LastPageName = "wTemplDTemplateStatementListAll"
   //:   BuildDisplayStructure( mTempl )
   {
    mTempl_Object m_mTempl_Object = new mTempl_Object( mTempl );
    m_mTempl_Object.omTempl_BuildDisplayStructure( mTempl );
    // m_mTempl_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// // END
// END
} 


//:DIALOG OPERATION
//:CancelTemplatePanel( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
CancelTemplatePanel( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mTempl, "TemplatePanel" )
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplatePanel( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
DeleteTemplatePanel( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mTempl.TemplatePanel
   RESULT = DeleteEntity( mTempl, "TemplatePanel", zPOS_NEXT );
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewTemplateGroup( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
NewTemplateGroup( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewTemplateGroup: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewTemplateGroup: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// CreateTemporalEntity( mTempl, "TemplateGroup", zPOS_AFTER )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateGroup", "NewTemplateGroup: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateGroup", "NewTemplateGroup: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mTempl.TemplateSection
   RESULT = CreateEntity( mTempl, "TemplateSection", zPOS_AFTER );
   //:mTempl.TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplatePanel( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
UpdateTemplatePanel( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// CreateTemporalSubobjectVersion( mTempl, "TemplatePanel" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplatePanel", "UpdateTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mTempl", "TemplatePanel", "UpdateTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewDisplayStatement( VIEW ViewToWindow )

//:   VIEW mTempl REGISTERED AS mTempl
public int 
NewDisplayStatement( View     ViewToWindow )
{
   zVIEW    mTempl = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mTempl, "mTempl", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewDisplayStatement: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "NewDisplayStatement: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// Position on the real entity in the hierarchical structure.
   //:// Then, create that entity as temporal and go to the correct window based on type.
   //:IF mTempl.DisplayStatement.EntityType = "Panel"
   if ( CompareAttributeToString( mTempl, "DisplayStatement", "EntityType", "Panel" ) == 0 )
   { 

      //:// Panel
      //:SET CURSOR FIRST mTempl.TemplatePanel
      //:           WHERE mTempl.TemplatePanel.wTempID = mTempl.DisplayStatement.EntityID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mTempl, "DisplayStatement", "EntityID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mTempl, "TemplatePanel", "wTempID", lTempInteger_0, "" );
      //:// CreateTemporalEntity( mTempl, "TemplatePanel", zPOS_AFTER )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplatePanel", "NewDisplayStatement: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplatePanel", "NewDisplayStatement: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:CREATE ENTITY mTempl.TemplateGroup
      RESULT = CreateEntity( mTempl, "TemplateGroup", zPOS_AFTER );
      //:mTempl.TemplateGroup.Name = "New"
      SetAttributeFromString( mTempl, "TemplateGroup", "Name", "New" );
      //:CREATE ENTITY mTempl.TemplateSection
      RESULT = CreateEntity( mTempl, "TemplateSection", zPOS_AFTER );
      //:mTempl.TemplateSection.TSectionType = "Marketing"
      SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplatePanelDetail" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplatePanelDetail" );
      //:RETURN 1
      if(8==8)return( 1 );

      //:ELSE
   } 
   else
   { 
      //:IF mTempl.DisplayStatement.EntityType = "Group"
      if ( CompareAttributeToString( mTempl, "DisplayStatement", "EntityType", "Group" ) == 0 )
      { 

         //:// Group
         //:SET CURSOR FIRST mTempl.TemplateGroup WITHIN mTempl.Template
         //:        WHERE mTempl.TemplateGroup.wTempID = mTempl.DisplayStatement.EntityID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mTempl, "DisplayStatement", "EntityID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mTempl, "TemplateGroup", "wTempID", lTempInteger_1, "Template" );
         //:// CreateTemporalEntity( mTempl, "TemplateGroup", zPOS_AFTER )
         //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateGroup", "NewDisplayStatement: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateGroup", "NewDisplayStatement: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
         //:mTempl.TemplateGroup.Name = "New"
         SetAttributeFromString( mTempl, "TemplateGroup", "Name", "New" );
         //:CREATE ENTITY mTempl.TemplateSection
         RESULT = CreateEntity( mTempl, "TemplateSection", zPOS_AFTER );
         //:mTempl.TemplateSection.TSectionType = "Marketing"
         SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateGroupDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateGroupDetail" );
         //:RETURN 1
         if(8==8)return( 1 );

         //:ELSE
      } 
      else
      { 
         //:// Section
         //:SET CURSOR FIRST mTempl.TemplateSection WITHIN mTempl.Template
         //:        WHERE mTempl.TemplateSection.wTempID = mTempl.DisplayStatement.EntityID
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, mTempl, "DisplayStatement", "EntityID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mTempl, "TemplateSection", "wTempID", lTempInteger_2, "Template" );
         //:// CreateTemporalEntity( mTempl, "TemplateSection", zPOS_AFTER )
         //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateSection", "NewDisplayStatement: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mTempl", "TemplateSection", "NewDisplayStatement: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
         //:mTempl.TemplateSection.TSectionType = "Marketing"
         SetAttributeFromString( mTempl, "TemplateSection", "TSectionType", "Marketing" );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateSectionDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wTemplD", "TemplateSectionDetail" );
         //:RETURN 1
         if(8==8)return( 1 );
      } 


      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplate( VIEW ViewToWindow )

//:   VIEW lTemplLST REGISTERED AS lTemplLST
public int 
DeleteTemplate( View     ViewToWindow )
{
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempl    BASED ON LOD  mTempl
   zVIEW    mTempl = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mTempl WHERE mTempl.Template.ID =  lTemplLST.Template.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lTemplLST, "Template", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mTempl, "mTempl", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mTempl "mTempl"
   SetNameForView( mTempl, "mTempl", null, zLEVEL_TASK );
   //:DELETE ENTITY mTempl.Template
   RESULT = DeleteEntity( mTempl, "Template", zPOS_NEXT );
   //:COMMIT mTempl
   RESULT = CommitObjectInstance( mTempl );

   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Delete Template",
      //:             "A database error occurred during the delete. Please notify Systems Suppoart.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Template", "A database error occurred during the delete. Please notify Systems Suppoart.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2      // return 2 to indicate Web client must prompt operator.
      if(8==8)return( 2 );
   } 

   //:END

   //:DropObjectInstance( mTempl )
   DropObjectInstance( mTempl );
   //:DropEntity( lTemplLST, "Template", zREPOS_NONE )
   DropEntity( lTemplLST, "Template", zREPOS_NONE );
   return( 0 );
// END
} 



}
