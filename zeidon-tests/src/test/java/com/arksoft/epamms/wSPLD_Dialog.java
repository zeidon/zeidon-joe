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
import com.arksoft.epamms.mSPLDef_Object;

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
// END
} 


private int 
o_fnLocalBuildQual_11( View     vSubtask,
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
o_fnLocalBuildQual_12( View     vSubtask,
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
o_fnLocalBuildQual_13( View     vSubtask,
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
o_fnLocalBuildQual_14( View     vSubtask,
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
o_fnLocalBuildQual_15( View     vSubtask,
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
o_fnLocalBuildQual_16( View     vSubtask,
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
o_fnLocalBuildQual_17( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Template" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Template" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:SaveShowPrevMarketingSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveShowPrevMarketingSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = PreviousMarketingSect( ViewToWindow )
   nRC = PreviousMarketingSect( ViewToWindow );
   //:RETURN nRC
   return( nRC );
// // VIEW mSPLDef  REGISTERED AS mSPLDef
// // SHORT nRC
// //
// // nRC = AcceptMarketingSect( ViewToWindow )
// // IF nRC = 0
// //    SET CURSOR PREVIOUS mSPLDef.SPLD_MarketingStatement
// //    IF RESULT = 0
// //       InitMarketingSectForInsert( ViewToWindow )
// //    ELSE
// //       MessageSend( ViewToWindow, "", "Save And Show Previous Marketing Section",
// //                    "There is no previous Marketing section.",
// //                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //       SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //       RETURN 2
// //    END
// // ELSE
// //    MessageSend( ViewToWindow, "", "Save And Show Previous Marketing Section",
// //                 "Error saving Marketing section.",
// //                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //    RETURN 2
// // END
// END
} 


//:DIALOG OPERATION
//:SaveShowNextMarketingSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveShowNextMarketingSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = NextMarketingSect( ViewToWindow )
   nRC = NextMarketingSect( ViewToWindow );
   //:RETURN nRC
   return( nRC );
// // VIEW mSPLDef  REGISTERED AS mSPLDef
// // SHORT nRC
// //
// // nRC = AcceptMarketingSect( ViewToWindow )
// // IF nRC = 0
// //    SET CURSOR NEXT mSPLDef.SPLD_MarketingStatement
// //    IF RESULT = 0
// //       InitMarketingSectForInsert( ViewToWindow )
// //    ELSE
// //       MessageSend( ViewToWindow, "", "Save And Show Next Marketing Section",
// //                    "There is no next Marketing section.",
// //                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //       SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //       RETURN 2
// //    END
// // ELSE
// //    MessageSend( ViewToWindow, "", "Save And Show Next Marketing Section",
// //                 "Error saving Marketing section.",
// //                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //    RETURN 2
// // END
// END
} 


//:DIALOG OPERATION
//:AcceptTemplateBlock( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
AcceptTemplateBlock( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mSPLDef, "SPLD_TemplateBlock" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateBlock: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateBlock: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewTemplateSection( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
NewTemplateSection( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CreateTemporalEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mSPLDef", "SPLD_TemplateSection", "NewTemplateBlock: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mSPLDef", "SPLD_TemplateSection", "NewTemplateBlock: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplateSection( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
UpdateTemplateSection( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplateSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateSection", "UpdateTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateSection", "UpdateTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplateSection( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
DeleteTemplateSection( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = DeleteEntity( mSPLDef, "SPLD_TemplateSection", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelTemplateBlock( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CancelTemplateBlock( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSPLDef, "SPLD_TemplateBlock" )
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplateBlock: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplateBlock: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelTemplate( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CancelTemplate( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSPLDef, "SPLD_Template" )
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelTemplate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptTemplateSection( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
AcceptTemplateSection( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mSPLDef, "SPLD_TemplateSection" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateSection: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplateSection: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelTemplateSection( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CancelTemplateSection( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSPLDef, "SPLD_TemplateSection" )
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
//:AcceptTemplatePanel( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
AcceptTemplatePanel( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mSPLDef, "SPLD_TemplatePanel" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewTemplateBlock( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
NewTemplateBlock( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CreateTemporalEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mSPLDef", "SPLD_TemplateBlock", "NewTemplateBlock: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mSPLDef", "SPLD_TemplateBlock", "NewTemplateBlock: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "Hazards"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Hazards" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewTemplatePanel( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
NewTemplatePanel( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CreateTemporalEntity( mSPLDef, "SPLD_TemplatePanel", zPOS_AFTER )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mSPLDef", "SPLD_TemplatePanel", "NewTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_AFTER, "mSPLDef", "SPLD_TemplatePanel", "NewTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "Hazards"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Hazards" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplateBlock( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
UpdateTemplateBlock( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateTemplateBlock: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateTemplateBlock: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplateBlock" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateBlock", "UpdateTemplateBlock: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateBlock", "UpdateTemplateBlock: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplatePanel( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
UpdateTemplatePanel( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "UpdateTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplatePanel" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplatePanel", "UpdateTemplatePanel: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplatePanel", "UpdateTemplatePanel: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplateBlock( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
DeleteTemplateBlock( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mSPLDef.SPLD_TemplateBlock
   RESULT = DeleteEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteTemplatePanel( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
DeleteTemplatePanel( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mSPLDef.SPLD_TemplatePanel
   RESULT = DeleteEntity( mSPLDef, "SPLD_TemplatePanel", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelTemplatePanel( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CancelTemplatePanel( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSPLDef, "SPLD_TemplatePanel" )
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
//:InitReselectTemplate( VIEW ViewToWindow )

//:   VIEW lTemplLST BASED ON LOD lTempl
public int 
InitReselectTemplate( View     ViewToWindow )
{
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;


   //:// Make sure that Template list exists.
   //:GET VIEW lTemplLST NAMED "lTemplLST"
   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE lTemplLST RootOnlyMultiple
      RESULT = ActivateObjectInstance( lTemplLST, "lTempl", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
      //:NAME VIEW lTemplLST "lTemplLST"
      SetNameForView( lTemplLST, "lTemplLST", null, zLEVEL_TASK );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ShowMarketingUsages( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ShowMarketingUsages( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 20 ) szUsageType
   String   szUsageType = null;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Build the display entities for Marketing and related Usages.
   //:// First delete existing entries.
   //:FOR EACH mSPLDef.MarketingUsage
   RESULT = SetCursorFirstEntity( mSPLDef, "MarketingUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.MarketingUsage NONE
      RESULT = DeleteEntity( mSPLDef, "MarketingUsage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "MarketingUsage", "" );
   } 

   //:END

   //:FOR EACH mSPLDef.SPLD_MarketingSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.MarketingUsage
      RESULT = CreateEntity( mSPLDef, "MarketingUsage", zPOS_AFTER );
      //:mSPLDef.MarketingUsage.MarketingSectionID = mSPLDef.SPLD_MarketingSection.ID
      SetAttributeFromAttribute( mSPLDef, "MarketingUsage", "MarketingSectionID", mSPLDef, "SPLD_MarketingSection", "ID" );
      //:mSPLDef.MarketingUsage.DisplayEntry       = mSPLDef.SPLD_MarketingSection.Title
      SetAttributeFromAttribute( mSPLDef, "MarketingUsage", "DisplayEntry", mSPLDef, "SPLD_MarketingSection", "Title" );
      //:FOR EACH mSPLDef.SPLD_MarketingUsage
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.MarketingUsage
         RESULT = CreateEntity( mSPLDef, "MarketingUsage", zPOS_AFTER );
         //:mSPLDef.MarketingUsage.MarketingSectionID = mSPLDef.SPLD_MarketingSection.ID
         SetAttributeFromAttribute( mSPLDef, "MarketingUsage", "MarketingSectionID", mSPLDef, "SPLD_MarketingSection", "ID" );
         //:mSPLDef.MarketingUsage.UsageID            = mSPLDef.SPLD_MarketingUsage.ID
         SetAttributeFromAttribute( mSPLDef, "MarketingUsage", "UsageID", mSPLDef, "SPLD_MarketingUsage", "ID" );
         //:GetStringFromAttributeByContext( szUsageType, mSPLDef, "SPLD_MarketingUsage", "UsageType", "", 20 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   GetStringFromAttributeByContext( sb_szUsageType, mSPLDef, "SPLD_MarketingUsage", "UsageType", "", 20 );
         szUsageType = sb_szUsageType.toString( );}
         //:mSPLDef.MarketingUsage.DisplayEntry        = "......" + szUsageType + ": " + mSPLDef.SPLD_MarketingUsage.Name
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, "......", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szUsageType, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, ": ", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_0, 'S', 255, mSPLDef, "SPLD_MarketingUsage", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mSPLDef, "MarketingUsage", "DisplayEntry", szTempString_0 );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingUsage", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddMarketingGroup( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AddMarketingGroup( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefF BASED ON LOD  mSPLDef
   zVIEW    mSPLDefF = new zVIEW( );
   //:VIEW mSPLDefT BASED ON LOD  mSPLDef
   zVIEW    mSPLDefT = new zVIEW( );
   //:INTEGER  lID
   int      lID = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We must be positioned on a Template Group
   //:SET CURSOR FIRST mSPLDef.DisplayTemplateStatement WHERE mSPLDef.DisplayTemplateStatement.wkSelected = "Y"
   RESULT = SetCursorFirstEntityByString( mSPLDef, "DisplayTemplateStatement", "wkSelected", "Y", "" );
   //:IF RESULT < zCURSOR_SET OR mSPLDef.DisplayTemplateStatement.EntityType != "Group"
   if ( RESULT < zCURSOR_SET || CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Group" ) != 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Add Marketing Group",
      //:             "You must be positioned on a valid Group.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Add Marketing Group", "You must be positioned on a valid Group.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:lID = mSPLDef.DisplayTemplateStatement.EntityID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSPLDef, "DisplayTemplateStatement", "EntityID" );
   lID = mi_lID.intValue( );}
   //:SET CURSOR FIRST mSPLDef.SPLD_TemplateBlock WITHIN mSPLDef.SPLD_Template
   //:           WHERE mSPLDef.SPLD_TemplateBlock.ID = lID
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_TemplateBlock", "ID", lID, "SPLD_Template" );
   //:// TraceLineI( "After SetCursorFirst for SPLD_TemplateBlock.ID: ", lID )
   //:// DisplayObjectInstance( mSPLDef, 0, 0 )
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:IssueError( ViewToWindow, 0, 0, "Programming Error 15" )
      IssueError( ViewToWindow, 0, 0, "Programming Error 15" );
      //:ELSE
   } 
   else
   { 
      //:// Check if the last Section for the current Group and the first Section of the next Group
      //:// are both Directions for Use. This is invalid as it would place a Marketing Section within
      //:// Directions for Use Sections.
      //:CreateViewFromView( mSPLDefF, mSPLDef )
      CreateViewFromView( mSPLDefF, mSPLDef );
      //:CreateViewFromView( mSPLDefT, mSPLDef )
      CreateViewFromView( mSPLDefT, mSPLDef );
      //:SET CURSOR PREVIOUS mSPLDefF.SPLD_TemplateSection
      RESULT = SetCursorPrevEntity( mSPLDefF, "SPLD_TemplateSection", "" );
      //:SET CURSOR NEXT mSPLDefT.SPLD_TemplateBlock WITHIN mSPLDefT.SPLD_Template
      RESULT = SetCursorNextEntity( mSPLDefT, "SPLD_TemplateBlock", "SPLD_Template" );
      //:IF mSPLDefF.SPLD_TemplateSection.TSectionType  = "DirectionsForUse" AND
      //:   mSPLDefT.SPLD_TemplateSection.TSectionType = "DirectionsForUse"
      if ( CompareAttributeToString( mSPLDefF, "SPLD_TemplateSection", "TSectionType", "DirectionsForUse" ) == 0 && CompareAttributeToString( mSPLDefT, "SPLD_TemplateSection", "TSectionType", "DirectionsForUse" ) == 0 )
      { 

         //:DropView( mSPLDefF )
         DropView( mSPLDefF );
         //:DropView( mSPLDefT )
         DropView( mSPLDefT );
         //:MessageSend( ViewToWindow, "", "Add Marketing Group",
         //:             "You cannot add a Marketing Section in the middle of Directions for Use.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Add Marketing Group", "You cannot add a Marketing Section in the middle of Directions for Use.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:DropView( mSPLDefF )
      DropView( mSPLDefF );
      //:DropView( mSPLDefT )
      DropView( mSPLDefT );
   } 

   //:END

   //:// Simply add the single Marketing Group and Section.
   //:CREATE ENTITY mSPLDef.SPLD_TemplateBlock
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateBlock.Name = "New Marketing"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateBlock", "Name", "New Marketing" );
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Marketing" );
   //:lID = mSPLDef.SubregPhysicalLabelDef.wNextwTemplIDValue
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSPLDef, "SubregPhysicalLabelDef", "wNextwTemplIDValue" );
   lID = mi_lID.intValue( );}
   //:lID = lID + 1
   lID = lID + 1;
   //:mSPLDef.SubregPhysicalLabelDef.wNextwTemplIDValue = lID
   SetAttributeFromInteger( mSPLDef, "SubregPhysicalLabelDef", "wNextwTemplIDValue", lID );
   //:mSPLDef.SPLD_TemplateSection.wTempID = lID
   SetAttributeFromInteger( mSPLDef, "SPLD_TemplateSection", "wTempID", lID );

   //:// We've got to commit the SPLD, but not rebuild the DisplayStructure in the Web
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// // TraceLineI( "After Create NewMarketing after SPLD_TemplateBlock.ID: ", lID )
// // DisplayObjectInstance( mSPLDef, 0, 0 )
// // // Rebuild the display structure.
// // BuildDisplayStructure( mSPLDef )
// END
} 


//:DIALOG OPERATION
//:ConfirmSelectMarketingSections( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
ConfirmSelectMarketingSections( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Include each selected Marketing Section under the Template Section.
   //:FOR EACH mSPLDef.SelectableMarketingSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SelectableMarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:nRC = GetSelectStateOfEntity( mSPLDef, "SelectableMarketingSection" )
      nRC = GetSelectStateOfEntity( mSPLDef, "SelectableMarketingSection" );
      //:IF nRC = 1
      if ( nRC == 1 )
      { 
         //:INCLUDE mSPLDef.SPLDT_MarketingSection FROM mSPLDef.SelectableMarketingSection
         RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_MarketingSection", mSPLDef, "SelectableMarketingSection", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SelectableMarketingSection", "" );
      //:END
   } 

   //:END

   //:// Rebuild the display structure.
   //:BuildDisplayStructure( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildDisplayStructure( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
AcceptUpdateSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdateSPLD: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdateSPLD: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
CancelUpdateSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelUpdateSPLD: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelUpdateSPLD: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:MoveDirectionsUseSections( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
MoveDirectionsUseSections( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefT BASED ON LOD  mSPLDef
   zVIEW    mSPLDefT = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// First validate that only Directions for Use Sections have been selected.
   //:FOR EACH mSPLDef.DisplayTemplateStatement
   RESULT = SetCursorFirstEntity( mSPLDef, "DisplayTemplateStatement", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.DisplayTemplateStatement.wkSelected = "Y"
      if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "wkSelected", "Y" ) == 0 )
      { 
         //:IF mSPLDef.DisplayTemplateStatement.EntityType != "DirectionsForUseSection"
         if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "DirectionsForUseSection" ) != 0 )
         { 
            //:MessageSend( ViewToWindow, "", "Select Directions Sections",
            //:             "You can only select 'Directions for Use' Sections.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Select Directions Sections", "You can only select 'Directions for Use' Sections.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );
         } 

         //:END

         //:SetSelectStateOfEntity( mSPLDef, "DisplayTemplateStatement", 1 )
         SetSelectStateOfEntity( mSPLDef, "DisplayTemplateStatement", 1 );
         //:ELSE
      } 
      else
      { 
         //:SetSelectStateOfEntity( mSPLDef, "DisplayTemplateStatement", 0 )
         SetSelectStateOfEntity( mSPLDef, "DisplayTemplateStatement", 0 );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "DisplayTemplateStatement", "" );
      //:END
   } 

   //:END

   //:// Position on TemplateSection for selected entries.
   //:nRC = SetCursorFirstSelectedEntity( mSPLDef, "DisplayTemplateStatement", "" )
   nRC = SetCursorFirstSelectedEntity( mSPLDef, "DisplayTemplateStatement", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Select Directions Sections",
      //:             "You must select at least one 'Directions for Use' Section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Directions Sections", "You must select at least one 'Directions for Use' Section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:SET CURSOR FIRST mSPLDef.SPLDT_DirectionsForUseSection WITHIN mSPLDef.SPLD_Template
   //:           WHERE mSPLDef.SPLDT_DirectionsForUseSection.ID = mSPLDef.DisplayTemplateStatement.EntityID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "DisplayTemplateStatement", "EntityID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLDT_DirectionsForUseSection", "ID", lTempInteger_0, "SPLD_Template" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:IssueError( ViewToWindow, 0, 0, "Programming Error 11 " )
      IssueError( ViewToWindow, 0, 0, "Programming Error 11 " );
   } 

   //:END

   //:// Create list of Directions for Use Template Sections.
   //:// Note that each entry points to the TemplateSection we just positioned on.
   //:FOR EACH mSPLDef.SelectDirectionsForUse
   RESULT = SetCursorFirstEntity( mSPLDef, "SelectDirectionsForUse", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.SelectDirectionsForUse NONE
      RESULT = DeleteEntity( mSPLDef, "SelectDirectionsForUse", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "SelectDirectionsForUse", "" );
   } 

   //:END

   //:CreateViewFromView( mSPLDefT, mSPLDef )
   CreateViewFromView( mSPLDefT, mSPLDef );
   //:FOR EACH mSPLDefT.SPLD_TemplateSection WITHIN mSPLDefT.SPLD_Template
   RESULT = SetCursorFirstEntity( mSPLDefT, "SPLD_TemplateSection", "SPLD_Template" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDefT.SPLD_TemplateSection.TSectionType = "DirectionsForUse" AND
      //:   mSPLDefT.SPLD_TemplateSection.ID != mSPLDef.SPLD_TemplateSection.ID
      if ( CompareAttributeToString( mSPLDefT, "SPLD_TemplateSection", "TSectionType", "DirectionsForUse" ) == 0 && CompareAttributeToAttribute( mSPLDefT, "SPLD_TemplateSection", "ID", mSPLDef, "SPLD_TemplateSection", "ID" ) != 0 )
      { 

         //:// Create three entries for Panel, Group and Section.
         //:CREATE ENTITY mSPLDef.SelectDirectionsForUse
         RESULT = CreateEntity( mSPLDef, "SelectDirectionsForUse", zPOS_AFTER );
         //:mSPLDef.SelectDirectionsForUse.TemplateSectionID = mSPLDefT.SPLD_TemplateSection.ID
         SetAttributeFromAttribute( mSPLDef, "SelectDirectionsForUse", "TemplateSectionID", mSPLDefT, "SPLD_TemplateSection", "ID" );
         //:mSPLDef.SelectDirectionsForUse.DisplayEntry = "Panel:   " + mSPLDefT.SPLD_TemplatePanel.dPanelName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, mSPLDefT, "SPLD_TemplatePanel", "dPanelName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, "Panel:   ", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mSPLDef, "SelectDirectionsForUse", "DisplayEntry", szTempString_0 );
         //:CREATE ENTITY mSPLDef.SelectDirectionsForUse
         RESULT = CreateEntity( mSPLDef, "SelectDirectionsForUse", zPOS_AFTER );
         //:mSPLDef.SelectDirectionsForUse.TemplateSectionID = mSPLDefT.SPLD_TemplateSection.ID
         SetAttributeFromAttribute( mSPLDef, "SelectDirectionsForUse", "TemplateSectionID", mSPLDefT, "SPLD_TemplateSection", "ID" );
         //:mSPLDef.SelectDirectionsForUse.DisplayEntry = "...Group:   " + mSPLDefT.SPLD_TemplateBlock.Name
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_2, 'S', 129, mSPLDefT, "SPLD_TemplateBlock", "Name", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                  ZeidonStringCopy( sb_szTempString_2, 1, 0, "...Group:   ", 1, 0, 255 );
         szTempString_2 = sb_szTempString_2.toString( );}
          {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                  ZeidonStringConcat( sb_szTempString_2, 1, 0, szTempString_3, 1, 0, 255 );
         szTempString_2 = sb_szTempString_2.toString( );}
         SetAttributeFromString( mSPLDef, "SelectDirectionsForUse", "DisplayEntry", szTempString_2 );
         //:CREATE ENTITY mSPLDef.SelectDirectionsForUse
         RESULT = CreateEntity( mSPLDef, "SelectDirectionsForUse", zPOS_AFTER );
         //:mSPLDef.SelectDirectionsForUse.TemplateSectionID = mSPLDefT.SPLD_TemplateSection.ID
         SetAttributeFromAttribute( mSPLDef, "SelectDirectionsForUse", "TemplateSectionID", mSPLDefT, "SPLD_TemplateSection", "ID" );
         //:mSPLDef.SelectDirectionsForUse.DisplayEntry = "......Section:      Directions for Use"
         SetAttributeFromString( mSPLDef, "SelectDirectionsForUse", "DisplayEntry", "......Section:      Directions for Use" );
      } 

      RESULT = SetCursorNextEntity( mSPLDefT, "SPLD_TemplateSection", "SPLD_Template" );
      //:END
   } 

   //:END

   //:DropView( mSPLDefT )
   DropView( mSPLDefT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ShowDirectionsUsages( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ShowDirectionsUsages( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 20 ) szUsageType
   String   szUsageType = null;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Build the display entities for Directions of Use and related Usages.
   //:// First delete existing entries.
   //:FOR EACH mSPLDef.DirectionsUsage
   RESULT = SetCursorFirstEntity( mSPLDef, "DirectionsUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY mSPLDef.DirectionsUsage NONE
      RESULT = DeleteEntity( mSPLDef, "DirectionsUsage", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "DirectionsUsage", "" );
   } 

   //:END

   //:FOR EACH mSPLDef.SPLD_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY mSPLDef.DirectionsUsage
      RESULT = CreateEntity( mSPLDef, "DirectionsUsage", zPOS_AFTER );
      //:mSPLDef.DirectionsUsage.DirectionsSectionID = mSPLDef.SPLD_DirectionsForUseSection.ID
      SetAttributeFromAttribute( mSPLDef, "DirectionsUsage", "DirectionsSectionID", mSPLDef, "SPLD_DirectionsForUseSection", "ID" );
      //:mSPLDef.DirectionsUsage.DisplayEntry        = mSPLDef.SPLD_DirectionsForUseSection.Title
      SetAttributeFromAttribute( mSPLDef, "DirectionsUsage", "DisplayEntry", mSPLDef, "SPLD_DirectionsForUseSection", "Title" );
      //:FOR EACH mSPLDef.SPLD_DirectionsUsage
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsUsage", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mSPLDef.DirectionsUsage
         RESULT = CreateEntity( mSPLDef, "DirectionsUsage", zPOS_AFTER );
         //:mSPLDef.DirectionsUsage.DirectionsSectionID = mSPLDef.SPLD_DirectionsForUseSection.ID
         SetAttributeFromAttribute( mSPLDef, "DirectionsUsage", "DirectionsSectionID", mSPLDef, "SPLD_DirectionsForUseSection", "ID" );
         //:mSPLDef.DirectionsUsage.UsageID             = mSPLDef.SPLD_DirectionsUsage.ID
         SetAttributeFromAttribute( mSPLDef, "DirectionsUsage", "UsageID", mSPLDef, "SPLD_DirectionsUsage", "ID" );
         //:GetStringFromAttributeByContext( szUsageType, mSPLDef, "SPLD_DirectionsUsage", "UsageType", "", 20 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   GetStringFromAttributeByContext( sb_szUsageType, mSPLDef, "SPLD_DirectionsUsage", "UsageType", "", 20 );
         szUsageType = sb_szUsageType.toString( );}
         //:mSPLDef.DirectionsUsage.DisplayEntry        = "......" + szUsageType + ": " + mSPLDef.SPLD_DirectionsUsage.Name
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, "......", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szUsageType, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, ": ", 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_0, 'S', 255, mSPLDef, "SPLD_DirectionsUsage", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( mSPLDef, "DirectionsUsage", "DisplayEntry", szTempString_0 );
         RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsUsage", "" );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateSPLD( VIEW ViewToWindow )

public int 
UpdateSPLD( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do ... just position
// END
} 


//:DIALOG OPERATION
public int 
InitSPLD_List( View     ViewToWindow )
{

   //:InitSPLD_List( VIEW ViewToWindow )

   //:// not much to do here ... the list of SPLDs is in the mSubLC OI.
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
//:NewSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef   BASED ON LOD mSPLDef
public int 
NewSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW lTemplLST BASED ON LOD lTempl
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSPLDef )
      DropObjectInstance( mSPLDef );
   } 

   //:END

   //:// Initialize a new SPLD.
   //:ACTIVATE mSPLDef EMPTY
   RESULT = ActivateEmptyObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, zSINGLE );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   //:CREATE ENTITY mSPLDef.SubregPhysicalLabelDef
   RESULT = CreateEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_AFTER );

   //:// Make sure select lists of SLC and Template entries exist.
   //:GET VIEW lTemplLST NAMED "lTemplLST"
   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE lTemplLST RootOnlyMultiple
      RESULT = ActivateObjectInstance( lTemplLST, "lTempl", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
      //:NAME VIEW lTemplLST "lTemplLST"
      SetNameForView( lTemplLST, "lTemplLST", null, zLEVEL_TASK );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSPLD( VIEW ViewToWindow )

//:   VIEW mSubLC   REGISTERED AS mSubLC
public int 
DeleteSPLD( View     ViewToWindow )
{
   zVIEW    mSubLC = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// This prompt must occur from the JavaScript.
   //:// nRC = MessagePrompt( ViewToWindow, "", "Delete", "OK to delete SPLD?", 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
   //:// IF nRC = zRESPONSE_NO
   //://    RETURN -1
   //:// END

   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSPLDef )
      DropObjectInstance( mSPLDef );
   } 

   //:END

   //:// Initialize a new SPLD.
   //:ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = mSubLC.SubregPhysicalLabelDef.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubLC, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   //:DELETE ENTITY mSPLDef.SubregPhysicalLabelDef
   RESULT = DeleteEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );

   //:lID = mSubLC.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubLC, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = lID
   o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_1, lID );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectMarketingSections( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
SelectMarketingSections( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDefT BASED ON LOD  mSPLDef
   zVIEW    mSPLDefT = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We must be positioned on a Template Marketing Section.
   //:SET CURSOR FIRST mSPLDef.DisplayTemplateStatement WHERE mSPLDef.DisplayTemplateStatement.wkSelected = "Y"
   RESULT = SetCursorFirstEntityByString( mSPLDef, "DisplayTemplateStatement", "wkSelected", "Y", "" );
   //:IF RESULT < zCURSOR_SET OR mSPLDef.DisplayTemplateStatement.EntityType != "Section"
   if ( RESULT < zCURSOR_SET || CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Section" ) != 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Select Marketing Sections",
      //:             "You must be positioned on a valid Marketing Section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Marketing Sections", "You must be positioned on a valid Marketing Section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:SET CURSOR FIRST mSPLDef.SPLD_TemplateSection WITHIN mSPLDef.SPLD_Template
   //:           WHERE mSPLDef.SPLD_TemplateSection.ID = mSPLDef.DisplayTemplateStatement.EntityID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "DisplayTemplateStatement", "EntityID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_TemplateSection", "ID", lTempInteger_0, "SPLD_Template" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:SET CURSOR FIRST mSPLDef.SPLD_TemplateSection WITHIN mSPLDef.SPLD_Template
      //:           WHERE mSPLDef.SPLD_TemplateSection.wTempID = mSPLDef.DisplayTemplateStatement.wTempID
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
             GetIntegerFromAttribute( mi_lTempInteger_1, mSPLDef, "DisplayTemplateStatement", "wTempID" );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_TemplateSection", "wTempID", lTempInteger_1, "SPLD_Template" );
   } 

   //:END

   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:IssueError( ViewToWindow, 0, 0, "Programming Error 10" )
      IssueError( ViewToWindow, 0, 0, "Programming Error 10" );
      //:ELSE
   } 
   else
   { 
      //:IF mSPLDef.SPLD_TemplateSection.TSectionType != "Marketing"
      if ( CompareAttributeToString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Marketing" ) != 0 )
      { 
         //:MessageSend( ViewToWindow, "", "Select Marketing Sections",
         //:             "You must be positioned on a valid Marketing Section.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Select Marketing Sections", "You must be positioned on a valid Marketing Section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:// Create a list of Marketing Sections that are not already used in the SPLD.
   //:FOR EACH mSPLDef.SelectableMarketingSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SelectableMarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:EXCLUDE mSPLDef.SelectableMarketingSection NONE
      RESULT = ExcludeEntity( mSPLDef, "SelectableMarketingSection", zREPOS_NONE );
      RESULT = SetCursorNextEntity( mSPLDef, "SelectableMarketingSection", "" );
   } 

   //:END

   //:CreateViewFromView( mSPLDefT, mSPLDef )
   CreateViewFromView( mSPLDefT, mSPLDef );
   //:FOR EACH mSPLDef.SPLD_MarketingSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST mSPLDefT.SPLDT_MarketingSection WITHIN mSPLDefT.SPLD_Template
      //:           WHERE mSPLDefT.SPLDT_MarketingSection.ID = mSPLDef.SPLD_MarketingSection.ID
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "SPLD_MarketingSection", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSPLDefT, "SPLDT_MarketingSection", "ID", lTempInteger_2, "SPLD_Template" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:INCLUDE mSPLDef.SelectableMarketingSection FROM mSPLDef.SPLD_MarketingSection
         RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SelectableMarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
      //:END
   } 

   //:END

   //:DropView( mSPLDefT )
   DropView( mSPLDefT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReselectTemplate( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
ReselectTemplate( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lTemplLST REGISTERED AS lTemplLST
   zVIEW    lTemplLST = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );

   //:IF lTemplLST != 0
   if ( getView( lTemplLST ) != null )
   { 
      //:DropObjectInstance( lTemplLST )
      DropObjectInstance( lTemplLST );
   } 

   //:END

   //:// Use the selected Template to rebuild that part of the SPLD.
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "ReselectTemplate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "ReselectTemplate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:BuildSPLD_Template( mSPLDef, mSPLDef.SelectedTemplate.ID )
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "SelectedTemplate", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_Template( mSPLDef, lTempInteger_0 );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// Build Display Structure.
   //:BuildDisplayStructure( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildDisplayStructure( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelReselectTemplate( VIEW ViewToWindow )

//:   VIEW lTemplLST REGISTERED AS lTemplLST
public int 
CancelReselectTemplate( View     ViewToWindow )
{
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );

   //:IF lTemplLST != 0
   if ( getView( lTemplLST ) != null )
   { 
      //:DropObjectInstance( lTemplLST )
      DropObjectInstance( lTemplLST );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CreateSLC( VIEW ViewToWindow )

//:   VIEW mMasLC  BASED ON LOD mMasLC
public int 
CreateSLC( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mSubLC  BASED ON LOD mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mSubLC2 BASED ON LOD mSubLC
   zVIEW    mSubLC2 = new zVIEW( );
   //:VIEW mSPLDef BASED ON LOD mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = 2
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0 );
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
//:TestHTML_Generation( VIEW ViewToWindow )

//:   VIEW mSPLDef BASED ON LOD mSPLDef
public int 
TestHTML_Generation( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;


   //:// Test HTML generation agains a test object.

   //:// Create an initial test object.
   //:ACTIVATE mSPLDef EMPTY
   RESULT = ActivateEmptyObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, zSINGLE );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   //:CREATE ENTITY mSPLDef.SubregPhysicalLabelDef
   RESULT = CreateEntity( mSPLDef, "SubregPhysicalLabelDef", zPOS_AFTER );
   //:mSPLDef.SubregPhysicalLabelDef.Name = "Test1"
   SetAttributeFromString( mSPLDef, "SubregPhysicalLabelDef", "Name", "Test1" );

   //:// Set up Marketing example.
   //:CREATE ENTITY mSPLDef.SPLD_MarketingSection
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingSection.ID = 1
   SetAttributeFromInteger( mSPLDef, "SPLD_MarketingSection", "ID", 1 );
   //:CREATE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingStatement.Text = "Recommended for use in <AreaOfUse>. Disinfects, cleans, and deodorizes the following hard nonporous inanimate surfaces: <Surface>. " +
   //:                                       "Recommeded for use in: Federally inspected meat and poultry plants as a disinfectant agent for use in all departments. " +
   //:                                       "Food products and packaging materials must be removed from the room or carefully protected. Use product in accordance with its label. " +
   //:                                       "All surfaces must be thoroughly rinsed with potable water."
    {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
      ZeidonStringCopy( sb_szTempString_0, 1, 0, "Recommended for use in <AreaOfUse>. Disinfects, cleans, and deodorizes the following hard nonporous inanimate surfaces: <Surface>. ", 1, 0, 32001 );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
      ZeidonStringConcat( sb_szTempString_0, 1, 0, "Recommeded for use in: Federally inspected meat and poultry plants as a disinfectant agent for use in all departments. ", 1, 0, 32001 );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
      ZeidonStringConcat( sb_szTempString_0, 1, 0, "Food products and packaging materials must be removed from the room or carefully protected. Use product in accordance with its label. ", 1, 0, 32001 );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
      ZeidonStringConcat( sb_szTempString_0, 1, 0, "All surfaces must be thoroughly rinsed with potable water.", 1, 0, 32001 );
   szTempString_0 = sb_szTempString_0.toString( );}
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "Text", szTempString_0 );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "kennels"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "kennels" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "pet shops"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "pet shops" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "veterinary clinics"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "veterinary clinics" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "life science laboratories"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "life science laboratories" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "S"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "S" );
   //:mSPLDef.SPLD_Usage.Name = "floors"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "floors" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "S"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "S" );
   //:mSPLDef.SPLD_Usage.Name = "walls"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "walls" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "S"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "S" );
   //:mSPLDef.SPLD_Usage.Name = "nonmedical metal surfaces,"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "nonmedical metal surfaces," );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "S"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "S" );
   //:mSPLDef.SPLD_Usage.Name = "plastic surfaces (such as polypropylene, polystyrene, etc.)"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "plastic surfaces (such as polypropylene, polystyrene, etc.)" );
   //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );

   //:// Set up 2nd MARKETING example.
   //:CREATE ENTITY mSPLDef.SPLD_MarketingSection
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingSection.ID = 2
   SetAttributeFromInteger( mSPLDef, "SPLD_MarketingSection", "ID", 2 );
   //:CREATE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingStatement.Text = "First unnumbered statement."
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "Text", "First unnumbered statement." );
   //:CREATE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingStatement.Text = "1. First numbered statement."
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "Text", "1. First numbered statement." );
   //:CREATE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingStatement.Text = "2. Second numbered statement. We want to test multiple lines, so this statement will go on and on and on until we finally get to at least another line."
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "Text", "2. Second numbered statement. We want to test multiple lines, so this statement will go on and on and on until we finally get to at least another line." );
   //:CREATE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingStatement.Text = "3. Third numbered statement."
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "Text", "3. Third numbered statement." );

   //:// Set up 3rd MARKETING example.
   //:CREATE ENTITY mSPLDef.SPLD_MarketingSection
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingSection.ID = 3
   SetAttributeFromInteger( mSPLDef, "SPLD_MarketingSection", "ID", 3 );
   //:CREATE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_MarketingStatement.Text = "A multi-purpose, neutral pH, gemicidal detergent and deodorant effective in hardwater up to 400 ppm (calculated as CaCO3) in the presence of a moderate amount of soil."
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "Text", "A multi-purpose, neutral pH, gemicidal detergent and deodorant effective in hardwater up to 400 ppm (calculated as CaCO3) in the presence of a moderate amount of soil." );
   //:FOR EACH mSPLDef.SPLD_Usage
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:INCLUDE mSPLDef.SPLD_MarketingUsage FROM mSPLDef.SPLD_Usage
      RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, "SPLD_Usage", zPOS_AFTER );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
   } 

   //:END

   //:// Set up DIRECTIONS FOR USE example.
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseSection
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseSection.ID = 1
   SetAttributeFromInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", 1 );
   //:mSPLDef.SPLD_DirectionsForUseSection.GeneralUse = "Y"
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseSection", "GeneralUse", "Y" );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "It is a violation of Federal law to use this product in a manner inconsistent with its labeling."
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text", "It is a violation of Federal law to use this product in a manner inconsistent with its labeling." );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "This product is not for use on medical device surfaces."
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text", "This product is not for use on medical device surfaces." );

   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseSection
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseSection.ID = 2
   SetAttributeFromInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", 2 );
   //:mSPLDef.SPLD_DirectionsForUseSection.Title = "DISINFECTION / CLEANING / DEODORIZING DIRECTIONS:"
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseSection", "Title", "DISINFECTION / CLEANING / DEODORIZING DIRECTIONS:" );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "Remove heavy soil deposits from surface. Then thoroughly wet surface with a use-solution of 1/2 oz. of the concentrate per gallon of water. " +
   //:                                              "The use-solution can be applied with a cloth, mop, sponge, or coarse spray, or by soaking. " +
   //:                                              "For sprayer applications, use a coarse spray device.Spray 6-8 inches from the surface, rub with a brush, cloth or sponge. " +
   //:                                              "Do not breathe spray. Let solution remain on surface for a minimum of 10 minutes. Rinse or allow to air dry. Rinsing of floors is not necessary unless they are to be waxed or polished. "
    {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
      ZeidonStringCopy( sb_szTempString_1, 1, 0, "Remove heavy soil deposits from surface. Then thoroughly wet surface with a use-solution of 1/2 oz. of the concentrate per gallon of water. ", 1, 0, 32001 );
   szTempString_1 = sb_szTempString_1.toString( );}
    {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
      ZeidonStringConcat( sb_szTempString_1, 1, 0, "The use-solution can be applied with a cloth, mop, sponge, or coarse spray, or by soaking. ", 1, 0, 32001 );
   szTempString_1 = sb_szTempString_1.toString( );}
    {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
      ZeidonStringConcat( sb_szTempString_1, 1, 0, "For sprayer applications, use a coarse spray device.Spray 6-8 inches from the surface, rub with a brush, cloth or sponge. ", 1, 0, 32001 );
   szTempString_1 = sb_szTempString_1.toString( );}
    {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
      ZeidonStringConcat( sb_szTempString_1, 1, 0, "Do not breathe spray. Let solution remain on surface for a minimum of 10 minutes. Rinse or allow to air dry. Rinsing of floors is not necessary unless they are to be waxed or polished. ", 1, 0, 32001 );
   szTempString_1 = sb_szTempString_1.toString( );}
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text", szTempString_1 );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "Food contact surfaces must be thoroughly rinsed with potable water. This product must not be used to clean the following food contact surfaces: utensils, glassware and dishes."
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text",
                        "Food contact surfaces must be thoroughly rinsed with potable water. This product must not be used to clean the following food contact surfaces: utensils, glassware and dishes." );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "Prepare a fresh solution daily or more often if the solution becomes visibly dirty or diluted."
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text", "Prepare a fresh solution daily or more often if the solution becomes visibly dirty or diluted." );

   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseSection
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseSection.ID = 3
   SetAttributeFromInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", 3 );
   //:mSPLDef.SPLD_DirectionsForUseSection.Title = "FUNGICIDAL DIRECTIONS:"
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseSection", "Title", "FUNGICIDAL DIRECTIONS:" );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "For use in areas such as locker rooms, dressing rooms, shower and bath areas and exercise facilities follow disinfection directions."
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text", "For use in areas such as locker rooms, dressing rooms, shower and bath areas and exercise facilities follow disinfection directions." );

   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseSection
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseSection.ID = 4
   SetAttributeFromInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", 4 );
   //:mSPLDef.SPLD_DirectionsForUseSection.Title = "MILDEWSTATIC INSTRUCTIONS:"
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseSection", "Title", "MILDEWSTATIC INSTRUCTIONS:" );
   //:CREATE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = CreateEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_AFTER );
   //:mSPLDef.SPLD_DirectionsForUseStatement.Text = "Will effectively control the growth of mold and mildew plus the odors caused by them when applied to hard, nonporous surfaces such as <AreaOfUse>. " +
   //:                                              "Apply use-solution of 1/2 oz. per gallon of water with a cloth, mop, sponge, or coarse spray. Make sure to wet all surfaces completely." +
   //:                                              " Let air dry. Repeat application weekly or when growth reappears."
    {StringBuilder sb_szTempString_2;
   if ( szTempString_2 == null )
      sb_szTempString_2 = new StringBuilder( 32 );
   else
      sb_szTempString_2 = new StringBuilder( szTempString_2 );
      ZeidonStringCopy( sb_szTempString_2, 1, 0, "Will effectively control the growth of mold and mildew plus the odors caused by them when applied to hard, nonporous surfaces such as <AreaOfUse>. ", 1, 0, 32001 );
   szTempString_2 = sb_szTempString_2.toString( );}
    {StringBuilder sb_szTempString_2;
   if ( szTempString_2 == null )
      sb_szTempString_2 = new StringBuilder( 32 );
   else
      sb_szTempString_2 = new StringBuilder( szTempString_2 );
      ZeidonStringConcat( sb_szTempString_2, 1, 0, "Apply use-solution of 1/2 oz. per gallon of water with a cloth, mop, sponge, or coarse spray. Make sure to wet all surfaces completely.", 1, 0, 32001 );
   szTempString_2 = sb_szTempString_2.toString( );}
    {StringBuilder sb_szTempString_2;
   if ( szTempString_2 == null )
      sb_szTempString_2 = new StringBuilder( 32 );
   else
      sb_szTempString_2 = new StringBuilder( szTempString_2 );
      ZeidonStringConcat( sb_szTempString_2, 1, 0, " Let air dry. Repeat application weekly or when growth reappears.", 1, 0, 32001 );
   szTempString_2 = sb_szTempString_2.toString( );}
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "Text", szTempString_2 );
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "walls"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "walls" );
   //://   INCLUDE mSPLDef.SPLD_DirectionsForUseUsage FROM mSPLDef.SPLD_Usage
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "floors"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "floors" );
   //://   INCLUDE mSPLDef.SPLD_DirectionsForUseUsage FROM mSPLDef.SPLD_Usage
   //:CREATE ENTITY mSPLDef.SPLD_Usage
   RESULT = CreateEntity( mSPLDef, "SPLD_Usage", zPOS_AFTER );
   //:mSPLDef.SPLD_Usage.UsageType = "U"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.Name = "table tops"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "Name", "table tops" );
   //://   INCLUDE mSPLDef.SPLD_DirectionsForUseUsage FROM mSPLDef.SPLD_Usage

   //:// Set up Template data.
   //:CREATE ENTITY mSPLDef.SPLD_Template
   RESULT = CreateEntity( mSPLDef, "SPLD_Template", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_TemplatePanel
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplatePanel", zPOS_AFTER );

   //:// First Marketing Section
   //:CREATE ENTITY mSPLDef.SPLD_TemplateBlock
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Marketing" );
   //:mSPLDef.SPLD_TemplateSection.UsageSeparatorCharacters = ", "
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "UsageSeparatorCharacters", ", " );
   //:mSPLDef.SPLD_TemplateSection.StatementFormat = "PU"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "StatementFormat", "PU" );
   //:SET CURSOR FIRST mSPLDef.SPLD_MarketingSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_MarketingSection", "" );
   //:INCLUDE mSPLDef.SPLDT_MarketingSection FROM mSPLDef.SPLD_MarketingSection
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_MarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );

   //:// Directions for Use Section
   //:CREATE ENTITY mSPLDef.SPLD_TemplateBlock
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "DirectionsForUse"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "DirectionsForUse" );
   //:mSPLDef.SPLD_TemplateSection.UsageSeparatorCharacters = ", "
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "UsageSeparatorCharacters", ", " );
   //:mSPLDef.SPLD_TemplateSection.StatementFormat = "PU"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "StatementFormat", "PU" );
   //:FOR EACH mSPLDef.SPLD_DirectionsForUseSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:INCLUDE mSPLDef.SPLDT_DirectionsForUseSection FROM mSPLDef.SPLD_DirectionsForUseSection
      RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_DirectionsForUseSection", mSPLDef, "SPLD_DirectionsForUseSection", zPOS_AFTER );
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
   } 

   //:END

   //:// Second Marketing Section
   //:CREATE ENTITY mSPLDef.SPLD_TemplateBlock
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Marketing" );
   //:mSPLDef.SPLD_TemplateSection.UsageSeparatorCharacters = ", "
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "UsageSeparatorCharacters", ", " );
   //:mSPLDef.SPLD_TemplateSection.StatementFormat = "SN"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "StatementFormat", "SN" );
   //:SET CURSOR NEXT mSPLDef.SPLD_MarketingSection
   RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
   //:INCLUDE mSPLDef.SPLDT_MarketingSection FROM mSPLDef.SPLD_MarketingSection
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_MarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );

   //:// Third Marketing Section
   //:CREATE ENTITY mSPLDef.SPLD_TemplateBlock
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateBlock", zPOS_AFTER );
   //:CREATE ENTITY mSPLDef.SPLD_TemplateSection
   RESULT = CreateEntity( mSPLDef, "SPLD_TemplateSection", zPOS_AFTER );
   //:mSPLDef.SPLD_TemplateSection.TSectionType = "Marketing"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "TSectionType", "Marketing" );
   //:mSPLDef.SPLD_TemplateSection.StatementFormat = "L3"
   SetAttributeFromString( mSPLDef, "SPLD_TemplateSection", "StatementFormat", "L3" );
   //:SET CURSOR NEXT mSPLDef.SPLD_MarketingSection
   RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
   //:INCLUDE mSPLDef.SPLDT_MarketingSection FROM mSPLDef.SPLD_MarketingSection
   RESULT = IncludeSubobjectFromSubobject( mSPLDef, "SPLDT_MarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_AFTER );

   //:// Call the generation routine.
   //:IssueError( ViewToWindow, 0, 0, "Before call1" )
   IssueError( ViewToWindow, 0, 0, "Before call1" );
   //:GeneratePDF_Label( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_GeneratePDF_Label( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// //</body>
// //<%
// //   strHTML_FileName = mSPLDef.MakeWebFileName( strSessionId, 2 ) + ".html";
// //%>
// ////   <iframe src="<%=strHTML_FileName%>"></iframe>
// //     <iframe src="C:\\Program Files\\Apache Group\\Tomcat 5.5\\webapps\\ROOT\\ePamms\\HTML_Temp.html"></iframe>
// END
} 


//:DIALOG OPERATION
//:RemoveMarketingUsages( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
RemoveMarketingUsages( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szUsageID
   String   szUsageID = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// For each selected Usage work entitiy, position on the corresponding Usage under Marketing for Use and
   //:// remove it. We will ignore any Marketing for Use entries that are selected.
   //:FOR EACH mSPLDef.MarketingUsage
   RESULT = SetCursorFirstEntity( mSPLDef, "MarketingUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.MarketingUsage.wkSelected = "Y"
      if ( CompareAttributeToString( mSPLDef, "MarketingUsage", "wkSelected", "Y" ) == 0 )
      { 

         //:// A work entity with a null UsageID is a Marketing for Use entry.
         //:szUsageID = mSPLDef.MarketingUsage.UsageID
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szUsageID;
         if ( szUsageID == null )
            sb_szUsageID = new StringBuilder( 32 );
         else
            sb_szUsageID = new StringBuilder( szUsageID );
                   GetVariableFromAttribute( sb_szUsageID, mi_lTempInteger_0, 'S', 33, mSPLDef, "MarketingUsage", "UsageID", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szUsageID = sb_szUsageID.toString( );}
         //:IF szUsageID != ""
         if ( ZeidonStringCompare( szUsageID, 1, 0, "", 1, 0, 33 ) != 0 )
         { 
            //:SET CURSOR FIRST mSPLDef.SPLD_MarketingSection WHERE mSPLDef.SPLD_MarketingSection.ID = mSPLDef.MarketingUsage.MarketingSectionID
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mSPLDef, "MarketingUsage", "MarketingSectionID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_MarketingSection", "ID", lTempInteger_1, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( ViewToWindow, 0, 0, "Programming Error 13" )
               IssueError( ViewToWindow, 0, 0, "Programming Error 13" );
            } 

            //:END

            //:SET CURSOR FIRST mSPLDef.SPLD_MarketingUsage WHERE mSPLDef.SPLD_MarketingUsage.ID = mSPLDef.MarketingUsage.UsageID
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "MarketingUsage", "UsageID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_MarketingUsage", "ID", lTempInteger_2, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( ViewToWindow, 0, 0, "Programming Error 14" )
               IssueError( ViewToWindow, 0, 0, "Programming Error 14" );
            } 

            //:END

            //:EXCLUDE mSPLDef.SPLD_MarketingUsage
            RESULT = ExcludeEntity( mSPLDef, "SPLD_MarketingUsage", zREPOS_AFTER );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "MarketingUsage", "" );
      //:END
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// Rebuild work structure for display.
   //:ShowMarketingUsages( ViewToWindow )
   ShowMarketingUsages( ViewToWindow );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:RemoveDirectionsUsages( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
RemoveDirectionsUsages( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szUsageID
   String   szUsageID = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// For each selected Usage work entitiy, position on the corresponding Usage under Directions for Use and
   //:// remove it. We will ignore any Directions for Use entries that are selected.
   //:FOR EACH mSPLDef.DirectionsUsage
   RESULT = SetCursorFirstEntity( mSPLDef, "DirectionsUsage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.DirectionsUsage.wkSelected = "Y"
      if ( CompareAttributeToString( mSPLDef, "DirectionsUsage", "wkSelected", "Y" ) == 0 )
      { 

         //:// A work entity with a null UsageID is a Directions for Use entry.
         //:szUsageID = mSPLDef.DirectionsUsage.UsageID
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szUsageID;
         if ( szUsageID == null )
            sb_szUsageID = new StringBuilder( 32 );
         else
            sb_szUsageID = new StringBuilder( szUsageID );
                   GetVariableFromAttribute( sb_szUsageID, mi_lTempInteger_0, 'S', 33, mSPLDef, "DirectionsUsage", "UsageID", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szUsageID = sb_szUsageID.toString( );}
         //:IF szUsageID != ""
         if ( ZeidonStringCompare( szUsageID, 1, 0, "", 1, 0, 33 ) != 0 )
         { 
            //:SET CURSOR FIRST mSPLDef.SPLD_DirectionsForUseSection WHERE mSPLDef.SPLD_DirectionsForUseSection.ID = mSPLDef.DirectionsUsage.DirectionsSectionID
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                         GetIntegerFromAttribute( mi_lTempInteger_1, mSPLDef, "DirectionsUsage", "DirectionsSectionID" );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", lTempInteger_1, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( ViewToWindow, 0, 0, "Programming Error 13" )
               IssueError( ViewToWindow, 0, 0, "Programming Error 13" );
            } 

            //:END

            //:SET CURSOR FIRST mSPLDef.SPLD_DirectionsUsage WHERE mSPLDef.SPLD_DirectionsUsage.ID = mSPLDef.DirectionsUsage.UsageID
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "DirectionsUsage", "UsageID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_DirectionsUsage", "ID", lTempInteger_2, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:IssueError( ViewToWindow, 0, 0, "Programming Error 14" )
               IssueError( ViewToWindow, 0, 0, "Programming Error 14" );
            } 

            //:END

            //:EXCLUDE mSPLDef.SPLD_DirectionsUsage
            RESULT = ExcludeEntity( mSPLDef, "SPLD_DirectionsUsage", zREPOS_AFTER );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "DirectionsUsage", "" );
      //:END
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// Rebuild work structure for display.
   //:ShowDirectionsUsages( ViewToWindow )
   ShowDirectionsUsages( ViewToWindow );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CreateSPLD( VIEW ViewToWindow )

//:   VIEW mSPLDef   REGISTERED AS mSPLDef
public int 
CreateSPLD( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC    BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW lTemplLST BASED ON LOD  lTempl
   zVIEW    lTemplLST = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Make sure select lists of Template entries exist.
   //:GET VIEW lTemplLST NAMED "lTemplLST"
   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );
   //:IF lTemplLST != 0
   if ( getView( lTemplLST ) != null )
   { 
      //:DropObjectInstance( lTemplLST )
      DropObjectInstance( lTemplLST );
   } 

   //:END

   //:// Use the selected Template and SLC to build a new SPLD.
   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:// Build the main part of the SPLD from the selected SLC.
   //:BuildSPLD_FromSLC( mSPLDef, mSubLC )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_FromSLC( mSPLDef, mSubLC );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:// Add the Template subobject to the SPLD from the selected Template
   //:BuildSPLD_Template( mSPLDef, mSPLDef.SelectedTemplate.ID )
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "SelectedTemplate", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_Template( mSPLDef, lTempInteger_0 );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// Build Display Structure.
   //:BuildDisplayStructure( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_BuildDisplayStructure( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:// Reset cursor to beginning for all entities.
   //:SET CURSOR FIRST mSPLDef.SubregPhysicalLabelDef
   RESULT = SetCursorFirstEntity( mSPLDef, "SubregPhysicalLabelDef", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardContent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHazardContent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_HazardSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_HazardSection", "" );
   //:LOOP WHILE RESULT = 0
   while ( RESULT == 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_HazardSection", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_HazardSection", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_HazardSection
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_HazardSection", "" );
   } 

   //:END

   //:FOR EACH mSPLDef.SPLD_GeneralSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_GeneralSection.SectionType = "E" // Environmental/Physical Hazard
      if ( CompareAttributeToString( mSPLDef, "SPLD_GeneralSection", "SectionType", "E" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_HazardSection",
         //:                               mSPLDef, "SPLD_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_HazardSection", mSPLDef, "SPLD_GeneralSection", zPOS_LAST );

         //:// We need to create a temporal Environmental/Physical Hazard Section entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_HazardSection", "InitHazardContent1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_HazardSection", "InitHazardContent1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralSection", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "E"  // Environmental/Physical Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
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
//:ImportSurfacesStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportSurfacesStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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
   //:nRC = ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName );
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

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
CopyPhysicalLabelDef( View     ViewToWindow )
{

   //:CopyPhysicalLabelDef( VIEW ViewToWindow )

   //:IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... CopyPhysicalLabelDef" )
   IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... CopyPhysicalLabelDef" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
FinalizePhysicalLabelDef( View     ViewToWindow )
{

   //:FinalizePhysicalLabelDef( VIEW ViewToWindow )

   //:IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... FinalizePhysicalLabelDef" )
   IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... FinalizePhysicalLabelDef" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
SavePhysicalLabelDef( View     ViewToWindow )
{

   //:SavePhysicalLabelDef( VIEW ViewToWindow )

   //:IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... SavePhysicalLabelDef" )
   IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... SavePhysicalLabelDef" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:STRING ( 50 ) szSectionName
   String   szSectionName = null;
   //:STRING ( 50 ) szSectionNameNew
   String   szSectionNameNew = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mSPLDef, "SPLD_PrecautionarySection" )
   AcceptSubobject( mSPLDef, "SPLD_PrecautionarySection" );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //://?szSectionName = mSPLDef.SPLD_PrecautionarySection.Title
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

   //:// We may update the existing SPLD_PrecautionarySection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_PrecautionarySection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_PrecautionarySection", "InitSplitMasterSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_PrecautionarySection", "InitSplitMasterSect: " );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

      //:nRC = SetEntityCursor( mSPLDef, "SPLD_PrecautionarySection", "Name", lControl,
      //:                       szSectionName, "", "", 0, "", "" )
      nRC = SetEntityCursor( mSPLDef, "SPLD_PrecautionarySection", "Name", lControl, szSectionName, "", "", 0, "", "" );
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

      //:nRC = SetEntityCursor( mSPLDef, "SPLD_PrecautionarySection", "Name", lControl,
      //:                       szSectionName, "", "", 0, "", "" )
      nRC = SetEntityCursor( mSPLDef, "SPLD_PrecautionarySection", "Name", lControl, szSectionName, "", "", 0, "", "" );
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

      //:CreateViewFromView( mTempPL, mSPLDef )
      CreateViewFromView( mTempPL, mSPLDef );
      //:CreateEntity( mTempPL, "SPLD_PrecautionarySection", zPOS_BEFORE )
      CreateEntity( mTempPL, "SPLD_PrecautionarySection", zPOS_BEFORE );
      //:SetMatchingAttributesByName( mTempPL, "SPLD_PrecautionarySection",
      //:                             mSPLDef, "SPLD_PrecautionarySection", zSET_NOTNULL )
      SetMatchingAttributesByName( mTempPL, "SPLD_PrecautionarySection", mSPLDef, "SPLD_PrecautionarySection", zSET_NOTNULL );
      //://?   mTempPL.SPLD_PrecautionarySection.Title = wWebXfer.Root.NameBefore
      //:mTempPL.SPLD_PrecautionaryStatement.Text = wWebXfer.Root.SplitHTML_Before
      SetAttributeFromAttribute( mTempPL, "SPLD_PrecautionaryStatement", "Text", wWebXfer, "Root", "SplitHTML_Before" );
      //:DropView( mTempPL )
      DropView( mTempPL );
   } 


   //:END

   //:IF wWebXfer.Root.SplitHTML_After != ""
   if ( CompareAttributeToString( wWebXfer, "Root", "SplitHTML_After", "" ) != 0 )
   { 

      //:CreateViewFromView( mTempPL, mSPLDef )
      CreateViewFromView( mTempPL, mSPLDef );
      //:CreateEntity( mTempPL, "SPLD_PrecautionarySection", zPOS_AFTER )
      CreateEntity( mTempPL, "SPLD_PrecautionarySection", zPOS_AFTER );
      //:SetMatchingAttributesByName( mTempPL, "SPLD_PrecautionarySection",
      //:                             mSPLDef, "SPLD_PrecautionarySection", zSET_NOTNULL )
      SetMatchingAttributesByName( mTempPL, "SPLD_PrecautionarySection", mSPLDef, "SPLD_PrecautionarySection", zSET_NOTNULL );
      //://?   mTempPL.SPLD_PrecautionarySection.Title = wWebXfer.Root.NameAfter
      //:mTempPL.SPLD_PrecautionaryStatement.Text = wWebXfer.Root.SplitHTML_After
      SetAttributeFromAttribute( mTempPL, "SPLD_PrecautionaryStatement", "Text", wWebXfer, "Root", "SplitHTML_After" );
      //:DropView( mTempPL )
      DropView( mTempPL );
   } 

   //:END

   //:AcceptSubobject( mSPLDef, "SPLD_PrecautionarySection" )
   AcceptSubobject( mSPLDef, "SPLD_PrecautionarySection" );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSPLDef, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSPLDef, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mSPLDef.SPLD_PrecautionarySection.ID

   //:// We have to make sure the Content is in good shape before we go on!
   //:nRC = AcceptUpdatePhysicalLabelDef( ViewToWindow )
   nRC = AcceptUpdatePhysicalLabelDef( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mSPLDef WHERE mSPLDef.SubregLabelContent.ID = lID
      o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSPLDef "mSPLDef"
      SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSPLDef.SubregLabelContent
      //:    WHERE mSPLDef.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SubregLabelContent", "ID", lContentID, "" );
   } 

   //://?   SET CURSOR FIRST mSPLDef.SPLD_PrecautionarySection
   //://?       WHERE mSPLDef.SPLD_PrecautionarySection.ID = lSectionID
   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterSect( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
ConfirmDeleteMasterSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //://?DELETE ENTITY mSPLDef.SPLD_PrecautionarySection
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteMasterSect( VIEW ViewToWindow )

public int 
CancelDeleteMasterSect( View     ViewToWindow )
{

   return( 0 );
// // VIEW mSPLDef REGISTERED AS mSPLDef
// END
} 


//:DIALOG OPERATION
//:MoveMasterSectUp( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
MoveMasterSectUp( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lSectionID
   int      lSectionID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSPLDef, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSPLDef, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mSPLDef.SPLD_PrecautionarySection.ID

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdatePhysicalLabelDef( ViewToWindow )
   nRC = AcceptUpdatePhysicalLabelDef( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mSPLDef WHERE mSPLDef.SubregLabelContent.ID = lID
      o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSPLDef "mSPLDef"
      SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSPLDef.SubregLabelContent
      //:    WHERE mSPLDef.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SubregLabelContent", "ID", lContentID, "" );
      //://?   SET CURSOR FIRST mSPLDef.SPLD_PrecautionarySection
      //://?       WHERE mSPLDef.SPLD_PrecautionarySection.ID = lSectionID

      //:CreateViewFromView( mTempPL, mSPLDef )
      CreateViewFromView( mTempPL, mSPLDef );
      //:NAME VIEW mTempPL "mTempPL"
      SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
      //://?   SET CURSOR PREVIOUS mTempPL.SPLD_PrecautionarySection
      //:MoveSubobject( mTempPL, "SPLD_PrecautionarySection",
      //:               mSPLDef, "SPLD_PrecautionarySection",
      //:               zPOS_PREV, zREPOS_PREV )
      MoveSubobject( mTempPL, "SPLD_PrecautionarySection", mSPLDef, "SPLD_PrecautionarySection", zPOS_PREV, zREPOS_PREV );
      //:DropView( mTempPL )
      DropView( mTempPL );
      //:COMMIT mSPLDef
      RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lSectionID
   int      lSectionID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSPLDef, "SubregLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSPLDef, "SubregLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}
   //://?lSectionID = mSPLDef.SPLD_PrecautionarySection.ID

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdatePhysicalLabelDef( ViewToWindow )
   nRC = AcceptUpdatePhysicalLabelDef( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mSPLDef WHERE mSPLDef.SubregLabelContent.ID = lID
      o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSPLDef "mSPLDef"
      SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mSPLDef.SubregLabelContent
      //:    WHERE mSPLDef.SubregLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SubregLabelContent", "ID", lContentID, "" );
      //://?   SET CURSOR FIRST mSPLDef.SPLD_PrecautionarySection
      //://?       WHERE mSPLDef.SPLD_PrecautionarySection.ID = lSectionID

      //:CreateViewFromView( mTempPL, mSPLDef )
      CreateViewFromView( mTempPL, mSPLDef );
      //:NAME VIEW mTempPL "mTempPL"
      SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
      //://?   SET CURSOR NEXT mTempPL.SPLD_PrecautionarySection
      //:MoveSubobject( mTempPL, "SPLD_PrecautionarySection",
      //:               mSPLDef, "SPLD_PrecautionarySection",
      //:               zPOS_NEXT, zREPOS_NEXT )
      MoveSubobject( mTempPL, "SPLD_PrecautionarySection", mSPLDef, "SPLD_PrecautionarySection", zPOS_NEXT, zREPOS_NEXT );
      //:DropView( mTempPL )
      DropView( mTempPL );
      //:COMMIT mSPLDef
      RESULT = CommitObjectInstance( mSPLDef );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_MarketingSection
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_MarketingSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_MarketingSection",
   //:               mSPLDef, "SPLD_MarketingSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_MarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_MarketingSection
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_MarketingSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_MarketingSection",
   //:               mSPLDef, "SPLD_MarketingSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_MarketingSection", mSPLDef, "SPLD_MarketingSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSubProd REGISTERED AS mSubProd
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:lID = mSubreg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}

   //:DELETE ENTITY mSubProd.SubregProduct
   RESULT = DeleteEntity( mSubProd, "SubregProduct", zPOS_NEXT );
   //:COMMIT mSubProd
   RESULT = CommitObjectInstance( mSubProd );

   //:DropObjectInstance( mSubProd )
   DropObjectInstance( mSubProd );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );

   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   return( 0 );
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

   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubreg.SubregProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "SubregProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );
   //:// TraceLineS( "", "" )
   //:// TraceLineS( "InitSubregProductForDelete", "" )
   //:// DisplayObjectInstance( mSubreg, "", "" )

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
//:DeleteSubregProduct( VIEW ViewToWindow )

public int 
DeleteSubregProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:InitPhysicalLabelDefForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPhysicalLabelDefForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubLC NAMED "mSubLC"
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );
   //:IF mSubLC = 0
   if ( getView( mSubLC ) == null )
   { 
      //:MessageSend( ViewToWindow, "", "Update Physical Label Definition",
      //:             "The Product Label Content view does not exist.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Physical Label Definition", "The Product Label Content view does not exist.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:IF mSubLC.SubregProduct DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSubLC, "SubregProduct" );
   if ( lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Physical Label Definition",
      //:             "The Subregistrant Product Label Content view does not exist.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Physical Label Definition", "The Subregistrant Product Label Content view does not exist.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:IF mSPLDef != 0
   if ( getView( mSPLDef ) != null )
   { 
      //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewPhysicalLabelDef" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewPhysicalLabelDef" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:DropObjectInstance( mSPLDef )
      DropObjectInstance( mSPLDef );
   } 

   //:END

   //:IF mSubLC.SubregPhysicalLabelDef DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mSubLC, "SubregPhysicalLabelDef" );
   if ( lTempInteger_1 != 0 )
   { 

      //:CreateSPLD( ViewToWindow )
      CreateSPLD( ViewToWindow );

      //:COMMIT mSPLDef
      RESULT = CommitObjectInstance( mSPLDef );
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );

      //:ELSE
   } 
   else
   { 

      //:ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = mSubLC.SubregPhysicalLabelDef.ID
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, mSubLC, "SubregPhysicalLabelDef", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSPLDef "mSPLDef"
      SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   } 


   //:END

   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SubregPhysicalLabelDef", "InitPhysicalLabelDefForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SubregPhysicalLabelDef", "InitPhysicalLabelDefForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF mSPLDef.SubregPhysicalLabelDef.CreatedDateTime = ""
   if ( CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "CreatedDateTime", "" ) == 0 )
   { 
      //:mSPLDef.SubregPhysicalLabelDef.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
      SetAttributeFromAttribute( mSPLDef, "SubregPhysicalLabelDef", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   } 

   //:END

   //:wWebXfer.Root.AttemptProductName = mSPLDef.SubregPhysicalLabelDef.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductName", mSPLDef, "SubregPhysicalLabelDef", "Name" );
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewPhysicalLabelDef( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewPhysicalLabelDef( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING (  50  ) szProductName
   String   szProductName = null;
   //:INTEGER lProductNameLth
   int      lProductNameLth = 0;
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section name is not blank and is unique.
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
   //:TraceLineS( "Product Content Name: ", szProductName )
   TraceLineS( "Product Content Name: ", szProductName );
   //:TraceLineI( "Product Content Length: ", lProductNameLth )
   TraceLineI( "Product Content Length: ", lProductNameLth );
   //:IF lProductNameLth < 1
   if ( lProductNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Physical Label Definition",
      //:             "The Physical Label Definition Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Physical Label Definition", "The Physical Label Definition Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:IF SetEntityCursor( mSPLDef, "SubregPhysicalLabelDef", "Name", lControl,
      //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mSPLDef, "SubregPhysicalLabelDef", "Name", lControl, szProductName, "", "", 0, "", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Physical Label Definition",
         //:             "The Physical Label Definition Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Physical Label Definition", "The Physical Label Definition Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END

   //:mSPLDef.SubregPhysicalLabelDef.Name = szProductName
   SetAttributeFromString( mSPLDef, "SubregPhysicalLabelDef", "Name", szProductName );

   //:IncludeSubobjectFromSubobject( mSubreg, "SubregLabelContent",
   //:                               mSPLDef, "SubregLabelContent", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mSubreg, "SubregLabelContent", mSPLDef, "SubregLabelContent", zPOS_BEFORE );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewPhysicalLabelDef" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "AcceptNewPhysicalLabelDef" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = mSPLDef.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "PrimaryRegistrant", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.SubregLabelContent
   //:    WHERE mSubreg.SubregLabelContent.ID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSPLDef, "SubregLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregLabelContent", "ID", lTempInteger_3, "" );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplateSPLD( VIEW ViewToWindow )

public int 
UpdateTemplateSPLD( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do ... just position
// END
} 


//:DIALOG OPERATION
//:CancelNewSPLD( VIEW ViewToWindow )

//:   VIEW lTemplLST BASED ON LOD  lTempl
public int 
CancelNewSPLD( View     ViewToWindow )
{
   zVIEW    lTemplLST = new zVIEW( );
   int      RESULT = 0;


   //:// Make sure select lists of Template entries exist.
   //:GET VIEW lTemplLST NAMED "lTemplLST"
   RESULT = GetViewByName( lTemplLST, "lTemplLST", ViewToWindow, zLEVEL_TASK );
   //:IF lTemplLST != 0
   if ( getView( lTemplLST ) != null )
   { 
      //:DropObjectInstance( lTemplLST )
      DropObjectInstance( lTemplLST );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdatePhysicalLabelDef( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelUpdatePhysicalLabelDef( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "SubregLabelContent" )
   CancelSubobject( mSubreg, "SubregLabelContent" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewPhysicalLabelDef: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewPhysicalLabelDef: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DesignSubregLabel( VIEW ViewToWindow )

public int 
DesignSubregLabel( View     ViewToWindow )
{

   return( 0 );
//    // Don't need to do anything except have this operation to cause
//    // positioning code to be done in the JSP.
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
//:AcceptUpdatePhysicalLabelDef( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdatePhysicalLabelDef( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING (  50  ) szProductName
   String   szProductName = null;
   //:INTEGER lProductNameLth
   int      lProductNameLth = 0;
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section name is not blank and is unique.
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
   //:TraceLineS( "Product Content Version: ", szProductName )
   TraceLineS( "Product Content Version: ", szProductName );
   //:TraceLineI( "Product Version Length: ", lProductNameLth )
   TraceLineI( "Product Version Length: ", lProductNameLth );
   //:IF lProductNameLth < 1
   if ( lProductNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Physical Label Definition",
      //:             "The Physical Label Definition Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Physical Label Definition", "The Physical Label Definition Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:IF mSPLDef.SubregPhysicalLabelDef.Name != szProductName
      if ( CompareAttributeToString( mSPLDef, "SubregPhysicalLabelDef", "Name", szProductName ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSPLDef, "SubregPhysicalLabelDef", "Name", lControl,
         //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mSPLDef, "SubregPhysicalLabelDef", "Name", lControl, szProductName, "", "", 0, "", "" );
         if ( lTempInteger_1 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Physical Label Definition",
            //:             "The Physical Label Definition Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Physical Label Definition", "The Physical Label Definition Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );
         } 

         //:END

         //:mSPLDef.SubregPhysicalLabelDef.Name = szProductName
         SetAttributeFromString( mSPLDef, "SubregPhysicalLabelDef", "Name", szProductName );
      } 

      //:END
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdatePhysicalLabelDef: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptUpdatePhysicalLabelDef: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:// AcceptSubobject( mSPLDef, "SubregLabelContent" )
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = mSPLDef.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "PrimaryRegistrant", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.SubregLabelContent
   //:    WHERE mSubreg.SubregLabelContent.ID = mSPLDef.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mSPLDef, "SubregLabelContent", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregLabelContent", "ID", lTempInteger_3, "" );
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewPhysicalLabelDef( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelNewPhysicalLabelDef( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "SubregLabelContent" )
   CancelSubobject( mSubreg, "SubregLabelContent" );
   //:CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewPhysicalLabelDef: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "CancelNewPhysicalLabelDef: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DropObjectInstance( mSPLDef )
   DropObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_MarketingStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingStatement", "SelectMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingStatement", "SelectMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "M"  // Marketing
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_MarketingStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_MarketingStatement", "InitMarketingStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_MarketingStatement", "InitMarketingStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_MarketingStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_MarketingStatement", "BoldItalic", "R" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an SPLD_MarketingStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_MarketingStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingStatement", "InitMarketingStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingStatement", "InitMarketingStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SPLD_HazardSection entity. We have
   //:// position on the SPLD_HazardStatement, but need to get position on
   //:// the SPLD_GeneralStatement that corresponds to the SPLD_HazardStatement.
   //:// SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement",  // fix this to set cursor properly before deployment DKS???
   //://                                  mSPLDef, "SPLD_HazardStatement", "" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "SelectHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "SelectHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // Environmental/Physical Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", szTempString_0, "" );
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

   //:// We need to create a new SPLD_GeneralStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralStatement", "InitHazardStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralStatement", "InitHazardStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_GeneralStatement", "BoldItalic", "R" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "E" // "Environmental/Physical Hazard"
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "E", "" );
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

   //:// We need to create a new SPLD_GeneralStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "InitHazardStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "InitHazardStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectHazardSectForUpdate( VIEW ViewToWindow )

//:   // just here for positioning ...
//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
SelectHazardSectForUpdate( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralSection", mSPLDef, "SPLDI_HazardSection", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralSection", mSPLDef, "SPLDI_HazardSection", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectHazardSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_GeneralSection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralSection", "InitHazardSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralSection", "InitHazardSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitHazardSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitHazardSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_GeneralSection", "Title" );

   //:// We need to update the existing SPLD_GeneralSection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralSection", "InitHazardSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralSection", "InitHazardSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "E"  // "Environmental/Physical Hazard"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "E" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SPLD_FirstAidSection entity.  We have
   //:// position on the SPLD_FirstAidStatement, but need to get position on
   //:// the SPLD_GeneralStatement that corresponds to the SPLD_FirstAidStatement.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectFirstAidStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "SelectFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "SelectFirstAidStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "F"  // FirstAid
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", szTempString_0, "" );
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

   //:// We need to create a new SPLD_GeneralStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralStatement", "InitFirstAidStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralStatement", "InitFirstAidStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_GeneralStatement", "BoldItalic", "R" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "F" // "FirstAid"
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "F", "" );
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

   //:// We need to create a new SPLD_GeneralStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "InitFirstAidStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "InitFirstAidStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// // DisplayEntityInstance( wWebXfer, "Root" )
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_DirectionsForUseStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_DirectionsForUseStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseStatement", "SelectDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseStatement", "SelectDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // DirectionsForUse
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_DirectionsForUseStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_DirectionsForUseStatement", "InitDirectionsUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_DirectionsForUseStatement", "InitDirectionsUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_DirectionsForUseStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseStatement", "BoldItalic", "R" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an SPLD_DirectionsForUseStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_DirectionsForUseStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseStatement", "InitDirectionsUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseStatement", "InitDirectionsUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
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
//:InitDirectionsUseSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_DirectionsForUseSection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_DirectionsForUseSection.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitDirectionsUseSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDirectionsUseSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_DirectionsForUseSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_DirectionsForUseSection", "Title" );

   //:// We need to update the existing SPLD_DirectionsForUseSection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_DirectionsForUseSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSectForUpdate: " );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_StorageDisposalStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalStatement", "SelectStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalStatement", "SelectStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_StorageDisposalStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_StorageDisposalStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_StorageDisposalStatement", "InitStorDispStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_StorageDisposalStatement", "InitStorDispStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_StorageDisposalStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_StorageDisposalStatement", "BoldItalic", "R" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an SPLD_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_StorageDisposalStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalStatement", "InitStorDispStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalStatement", "InitStorDispStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_StorageDisposalStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_StorageDisposalSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalSection", "SelectStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalSection", "SelectStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptStorDispStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptStorDispStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:CleanupDirectionsWorkEntities( VIEW mSPLDef )

//:   VIEW mTempPL  BASED ON LOD  mSPLDef
public int 
CleanupDirectionsWorkEntities( View     mSPLDef )
{
   zVIEW    mTempPL = new zVIEW( );
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


   //:GET VIEW mTempPL NAMED "mTempPL"
   RESULT = GetViewByName( mTempPL, "mTempPL", mSPLDef, zLEVEL_TASK );
   //:IF mTempPL != 0
   if ( getView( mTempPL ) != null )
   { 
      //:DropView( mTempPL )
      DropView( mTempPL );
   } 

   //:END
   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );

   //:// We need to traverse SPLD_DirectionsOrdering entities and delete the work sub-entities.
   //:FOR EACH mTempPL.SPLD_DirectionsOrdering
   RESULT = SetCursorFirstEntity( mTempPL, "SPLD_DirectionsOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mTempPL.SPLD_DirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mTempPL, "SPLD_DirectionsUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mTempPL.SPLD_DirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mTempPL, "SPLD_DirectionsUsage", "ClaimsClassification", "", 0 );
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
         //:nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification )
         nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE )
            DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:nRC = CheckExistenceOfEntity( mTempPL, "DirectionsSurface" )
            nRC = CheckExistenceOfEntity( mTempPL, "DirectionsSurface" );
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:DELETE ENTITY mTempPL.DirectionsSurface
               RESULT = DeleteEntity( mTempPL, "DirectionsSurface", zPOS_NEXT );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAppType" )
               nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAppType" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:DELETE ENTITY mTempPL.DirectionsAppType
                  RESULT = DeleteEntity( mTempPL, "DirectionsAppType", zPOS_NEXT );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAreasOfUse" )
                  nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAreasOfUse" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:DELETE ENTITY mTempPL.DirectionsAreasOfUse
                     RESULT = DeleteEntity( mTempPL, "DirectionsAreasOfUse", zPOS_NEXT );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mTempPL, "SPLD_DirectionsOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempPL )
   DropView( mTempPL );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadDirectionsUsageList( VIEW ViewToWindow,
//:                         VIEW mSPLDefIn BASED ON LOD mSPLDef )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadDirectionsUsageList( View     ViewToWindow,
                           View     mSPLDefIn )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mPosPL   BASED ON LOD  mSPLDef
   zVIEW    mPosPL = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
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

   //:CleanupDirectionsWorkEntities( mSPLDefIn )
   CleanupDirectionsWorkEntities( mSPLDefIn );

   //:CreateViewFromView( mPosPL, mSPLDefIn )
   CreateViewFromView( mPosPL, mSPLDefIn );
   //:NAME VIEW mPosPL "mPosPL"
   SetNameForView( mPosPL, "mPosPL", null, zLEVEL_TASK );
   //:CreateViewFromView( mTempPL, mSPLDefIn )
   CreateViewFromView( mTempPL, mSPLDefIn );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
   //:CreateViewFromView( mSPLDef, mSPLDefIn )
   CreateViewFromView( mSPLDef, mSPLDefIn );
   //:NAME VIEW mSPLDef "mSPLDef1"
   SetNameForView( mSPLDef, "mSPLDef1", null, zLEVEL_TASK );

   //:// Get position on included SPLD_DirectionsUsage entities (which will be marked as selected).
   //:SetCursorFirstEntity( mPosPL, "SPLD_DirectionsOrdering", "" )
   SetCursorFirstEntity( mPosPL, "SPLD_DirectionsOrdering", "" );

   //:// Mark included SPLD_DirectionsUsage entities as "selected" and include SPLD_OriginalDirectionsUsage not
   //:// already included into the SPLD_DirectionsUsage entity and mark as "not selected".
   //:FOR EACH mSPLDef.SPLD_OriginalDirectionsOrdering
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_OriginalDirectionsOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lID = mSPLDef.SPLD_OriginalDirectionsUsage.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SPLD_OriginalDirectionsUsage", "ID" );
      lID = mi_lID.intValue( );}
      //:SET CURSOR FIRST mTempPL.SPLD_DirectionsUsage WITHIN mTempPL.SPLD_DirectionsForUseSection
      //:                                              WHERE mTempPL.SPLD_DirectionsUsage.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mTempPL, "SPLD_DirectionsUsage", "ID", lID, "SPLD_DirectionsForUseSection" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mPosPL.SPLD_DirectionsUsage WITHIN mPosPL.SPLD_DirectionsForUseSection
         //:                                             WHERE mPosPL.SPLD_DirectionsUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mPosPL, "SPLD_DirectionsUsage", "ID", lID, "SPLD_DirectionsForUseSection" );
         //:mPosPL.SPLD_DirectionsUsage.wkSelected = "Y"
         SetAttributeFromString( mPosPL, "SPLD_DirectionsUsage", "wkSelected", "Y" );
         //:ELSE
      } 
      else
      { 
         //:CreateEntity( mPosPL, "SPLD_DirectionsOrdering", zPOS_AFTER )
         CreateEntity( mPosPL, "SPLD_DirectionsOrdering", zPOS_AFTER );
         //:IncludeSubobjectFromSubobject( mPosPL, "SPLD_DirectionsUsage",
         //:                               mSPLDef, "SPLD_OriginalDirectionsUsage", zPOS_NEXT )
         IncludeSubobjectFromSubobject( mPosPL, "SPLD_DirectionsUsage", mSPLDef, "SPLD_OriginalDirectionsUsage", zPOS_NEXT );
         //:mPosPL.SPLD_DirectionsUsage.wkSelected = ""
         SetAttributeFromString( mPosPL, "SPLD_DirectionsUsage", "wkSelected", "" );
      } 

      //:END

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mPosPL.SPLD_OriginalDirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mPosPL, "SPLD_OriginalDirectionsUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mPosPL.SPLD_OriginalDirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mPosPL, "SPLD_OriginalDirectionsUsage", "ClaimsClassification", "", 0 );
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
         //:CreateEntity( mPosPL, szClaimsClassification, zPOS_AFTER )
         CreateEntity( mPosPL, szClaimsClassification, zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:CreateEntity( mPosPL, "DirectionsSurface", zPOS_AFTER )
            CreateEntity( mPosPL, "DirectionsSurface", zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:CreateEntity( mPosPL, "DirectionsAppType", zPOS_AFTER )
               CreateEntity( mPosPL, "DirectionsAppType", zPOS_AFTER );
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:CreateEntity( mPosPL, "DirectionsAreasOfUse", zPOS_AFTER )
                  CreateEntity( mPosPL, "DirectionsAreasOfUse", zPOS_AFTER );
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_OriginalDirectionsOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mPosPL )
   DropView( mPosPL );
   //:DropView( mTempPL )
   DropView( mTempPL );
   //:DropView( mSPLDef )
   DropView( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptDirectionsUseStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING (  50  ) szSectionTitle
   String   szSectionTitle = null;
   //:STRING (  32  ) szClaimsClassification
   String   szClaimsClassification = null;
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
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section title is not blank.
   //:szSectionTitle = mSPLDef.SPLD_GeneralSection.Title
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szSectionTitle;
   if ( szSectionTitle == null )
      sb_szSectionTitle = new StringBuilder( 32 );
   else
      sb_szSectionTitle = new StringBuilder( szSectionTitle );
       GetVariableFromAttribute( sb_szSectionTitle, mi_lTempInteger_0, 'S', 51, mSPLDef, "SPLD_GeneralSection", "Title", "", 0 );
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

      //:MessageSend( ViewToWindow, "", "Directions for Use Section",
      //:             "The Directions for Use Section Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Directions for Use Section", "The Directions for Use Section Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:GET VIEW mTempPL NAMED "mTempPL"
   RESULT = GetViewByName( mTempPL, "mTempPL", ViewToWindow, zLEVEL_TASK );
   //:IF mTempPL != 0
   if ( getView( mTempPL ) != null )
   { 
      //:DropView( mTempPL )
      DropView( mTempPL );
   } 

   //:END
   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );

   //:// We need to exclude SPLD_DirectionsUsage entities that are not selected, but
   //:// the new structure, we need to traverse SPLD_DirectionsOrdering entities.
   //:FOR EACH mTempPL.SPLD_DirectionsOrdering
   RESULT = SetCursorFirstEntity( mTempPL, "SPLD_DirectionsOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szUsageType = mTempPL.SPLD_DirectionsUsage.UsageType
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_2, 'S', 2, mTempPL, "SPLD_DirectionsUsage", "UsageType", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Directions" + mTempPL.SPLD_DirectionsUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 17, mTempPL, "SPLD_DirectionsUsage", "ClaimsClassification", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
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
         //:nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification )
         nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE )
            DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE );
         } 

         //:/*
         //:ELSE
         //:   DisplayEntityInstance( mTempPL, "SPLD_DirectionsUsage" )
         //:   MessageSend( ViewToWindow, "", "Delete Error???",
         //:                "Expected DirectionsClaim.",
         //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:   DropView( mTempPL )
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
            //:nRC = CheckExistenceOfEntity( mTempPL, "DirectionsSurface" )
            nRC = CheckExistenceOfEntity( mTempPL, "DirectionsSurface" );
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:DELETE ENTITY mTempPL.DirectionsSurface
               RESULT = DeleteEntity( mTempPL, "DirectionsSurface", zPOS_NEXT );
            } 

            //:/*
            //:ELSE
            //:DisplayEntityInstance( mTempPL, "SPLD_DirectionsUsage" )
            //:MessageSend( ViewToWindow, "", "Delete Error???",
            //:             "Expected DirectionsSurface.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            //:DropView( mTempPL )
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
               //:nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAppType" )
               nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAppType" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:DELETE ENTITY mTempPL.DirectionsAppType
                  RESULT = DeleteEntity( mTempPL, "DirectionsAppType", zPOS_NEXT );
               } 

               //:/*
               //:ELSE
               //:DisplayEntityInstance( mTempPL, "SPLD_DirectionsUsage" )
               //:MessageSend( ViewToWindow, "", "Delete Error???",
               //:          "Expected DirectionsAppType.",
               //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               //:DropView( mTempPL )
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
                  //:nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAreasOfUse" )
                  nRC = CheckExistenceOfEntity( mTempPL, "DirectionsAreasOfUse" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:DELETE ENTITY mTempPL.DirectionsAreasOfUse
                     RESULT = DeleteEntity( mTempPL, "DirectionsAreasOfUse", zPOS_NEXT );
                  } 

                  //:/*
                  //:ELSE
                  //:DisplayEntityInstance( mTempPL, "SPLD_DirectionsUsage" )
                  //:MessageSend( ViewToWindow, "", "Delete Error???",
                  //:       "Expected DirectionsAreasOfUse.",
                  //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  //:DropView( mTempPL )
                  //:RETURN 2
                  //:*/
                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:DisplayEntityInstance( mTempPL, "DirectionsAreasOfUse" )
                  DisplayEntityInstance( mTempPL, "DirectionsAreasOfUse" );
                  //:MessageSend( ViewToWindow, "", "Unexpected Type",
                  //:    szUsageType,
                  //:    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( ViewToWindow, "", "Unexpected Type", szUsageType, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:DropView( mTempPL )
                  DropView( mTempPL );
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

      //:IF mTempPL.SPLD_DirectionsUsage.wkSelected != "Y"
      if ( CompareAttributeToString( mTempPL, "SPLD_DirectionsUsage", "wkSelected", "Y" ) != 0 )
      { 
         //:// ExcludeEntity( mTempPL, "SPLD_DirectionsUsage", zREPOS_NONE )
         //:DeleteEntity( mTempPL, "SPLD_DirectionsOrdering", zREPOS_NONE )
         DeleteEntity( mTempPL, "SPLD_DirectionsOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mTempPL, "SPLD_DirectionsOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempPL )
   DropView( mTempPL );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:wWebXfer.Root.CurrentContentType = "U"  // "DirectionsForUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAreasOfUseSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptAreasOfUseSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_Usage entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitAreasOfUseStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitAreasOfUseStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_Usage.UsageType = "U" // "AreasOfUse"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "U" );
   //:mSPLDef.SPLD_Usage.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAppTypesSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptAppTypesSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_Usage entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitAppTypesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitAppTypesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_Usage.UsageType = "T" // "ApplicationType"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "T" );
   //:mSPLDef.SPLD_Usage.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "T"  // "ApplicationType"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
//:AcceptAreasOfUseStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptAreasOfUseStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptAppTypesStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptAppTypesStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewApplicationType( VIEW ViewToWindow )

//:   VIEW  mSPLDef REGISTERED AS mSPLDef
public int 
SaveAddNewApplicationType( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptMarketingStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:STRING (  32  ) szEntityName
   String   szEntityName = null;
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING (  50  ) szSectionTitle
   String   szSectionTitle = null;
   //:STRING (  32  ) szClaimsClassification
   String   szClaimsClassification = null;
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
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section title is not blank.
   //:szSectionTitle = mSPLDef.SPLD_GeneralSection.Title
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szSectionTitle;
   if ( szSectionTitle == null )
      sb_szSectionTitle = new StringBuilder( 32 );
   else
      sb_szSectionTitle = new StringBuilder( szSectionTitle );
       GetVariableFromAttribute( sb_szSectionTitle, mi_lTempInteger_0, 'S', 51, mSPLDef, "SPLD_GeneralSection", "Title", "", 0 );
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

      //:MessageSend( ViewToWindow, "", "Marketing Section",
      //:             "The Marketing Section Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Marketing Section", "The Marketing Section Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:GET VIEW mTempPL NAMED "mTempPL"
   RESULT = GetViewByName( mTempPL, "mTempPL", ViewToWindow, zLEVEL_TASK );
   //:IF mTempPL != 0
   if ( getView( mTempPL ) != null )
   { 
      //:DropView( mTempPL )
      DropView( mTempPL );
   } 

   //:END
   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );

   //:// We need to exclude SPLD_MarketingUsage entities that are not selected, but
   //:// the new structure, we need to traverse SPLD_MarketingOrdering entities.
   //:FOR EACH mTempPL.SPLD_MarketingOrdering
   RESULT = SetCursorFirstEntity( mTempPL, "SPLD_MarketingOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:szUsageType = mTempPL.SPLD_MarketingUsage.UsageType
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_2, 'S', 2, mTempPL, "SPLD_MarketingUsage", "UsageType", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mTempPL.SPLD_MarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 17, mTempPL, "SPLD_MarketingUsage", "ClaimsClassification", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
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
         //:nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification )
         nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE )
            DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE );
         } 

         //:/*
         //:ELSE
         //:   DisplayEntityInstance( mTempPL, "SPLD_MarketingUsage" )
         //:   MessageSend( ViewToWindow, "", "Delete Error???",
         //:                "Expected MarketingClaim.",
         //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         //:   DropView( mTempPL )
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
            //:nRC = CheckExistenceOfEntity( mTempPL, "MarketingSurface" )
            nRC = CheckExistenceOfEntity( mTempPL, "MarketingSurface" );
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:DELETE ENTITY mTempPL.MarketingSurface
               RESULT = DeleteEntity( mTempPL, "MarketingSurface", zPOS_NEXT );
            } 

            //:/*
            //:ELSE
            //:DisplayEntityInstance( mTempPL, "SPLD_MarketingUsage" )
            //:MessageSend( ViewToWindow, "", "Delete Error???",
            //:             "Expected MarketingSurface.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            //:DropView( mTempPL )
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
               //:nRC = CheckExistenceOfEntity( mTempPL, "MarketingAppType" )
               nRC = CheckExistenceOfEntity( mTempPL, "MarketingAppType" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:DELETE ENTITY mTempPL.MarketingAppType
                  RESULT = DeleteEntity( mTempPL, "MarketingAppType", zPOS_NEXT );
               } 

               //:/*
               //:ELSE
               //:DisplayEntityInstance( mTempPL, "SPLD_MarketingUsage" )
               //:MessageSend( ViewToWindow, "", "Delete Error???",
               //:          "Expected MarketingAppType.",
               //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               //:DropView( mTempPL )
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
                  //:nRC = CheckExistenceOfEntity( mTempPL, "MarketingAreasOfUse" )
                  nRC = CheckExistenceOfEntity( mTempPL, "MarketingAreasOfUse" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:DELETE ENTITY mTempPL.MarketingAreasOfUse
                     RESULT = DeleteEntity( mTempPL, "MarketingAreasOfUse", zPOS_NEXT );
                  } 

                  //:/*
                  //:ELSE
                  //:DisplayEntityInstance( mTempPL, "SPLD_MarketingUsage" )
                  //:MessageSend( ViewToWindow, "", "Delete Error???",
                  //:       "Expected MarketingAreasOfUse.",
                  //:       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  //:DropView( mTempPL )
                  //:RETURN 2
                  //:*/
                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:DisplayEntityInstance( mTempPL, "MarketingAreasOfUse" )
                  DisplayEntityInstance( mTempPL, "MarketingAreasOfUse" );
                  //:MessageSend( ViewToWindow, "", "Unexpected Type",
                  //:    szUsageType,
                  //:    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( ViewToWindow, "", "Unexpected Type", szUsageType, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:DropView( mTempPL )
                  DropView( mTempPL );
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

      //:IF mTempPL.SPLD_MarketingUsage.wkSelected != "Y"
      if ( CompareAttributeToString( mTempPL, "SPLD_MarketingUsage", "wkSelected", "Y" ) != 0 )
      { 
         //:// ExcludeEntity( mTempPL, "SPLD_MarketingUsage", zREPOS_NONE )
         //:DeleteEntity( mTempPL, "SPLD_MarketingOrdering", zREPOS_NONE )
         DeleteEntity( mTempPL, "SPLD_MarketingOrdering", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( mTempPL, "SPLD_MarketingOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempPL )
   DropView( mTempPL );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:AcceptClaimsStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptClaimsStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptClaimsSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptClaimsSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:InitClaimsStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitClaimsStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_Usage entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitClaimsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitClaimsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_Usage.UsageType = "C" // "Claim"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "C" );
   //:mSPLDef.SPLD_Usage.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "C"  // "Claim"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "C" );
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
//:InitAreasOfUseStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAreasOfUseStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_Usage (AreasOfUse) entity. We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (AreasOfUse) entity that corresponds to the SPLDI_FirstAidSection entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );

   //:// We need to update an SPLD_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitAreasOfUseStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitAreasOfUseStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_Usage (AppTypes) entity.  We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (AppTypes) entity that corresponds to the SPLDI_FirstAidSection entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );

   //:// We need to update an SPLD_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitAppTypesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitAppTypesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "T"  // "ApplicationType"
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   } 

   //:END

   //:// We need to create SPLDI_UsageList entities.
   //:FOR EACH mSPLDef.SPLD_Usage
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_Usage.UsageType = "C" // "Claim"
      if ( CompareAttributeToString( mSPLDef, "SPLD_Usage", "UsageType", "C" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList",
         //:                               mSPLDef, "SPLD_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList", mSPLDef, "SPLD_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitClaimsSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitClaimsSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitClaimsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_Usage (Claims) entity.  We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (Claims) entity that corresponds to the SPLDI_FirstAidSection entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );

   //:// We need to update an SPLD_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitClaimsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitClaimsStmtForUpdate: " );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   } 

   //:END

   //:// We need to create SPLDI_UsageList entities.
   //:FOR EACH mSPLDef.SPLD_Usage
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_Usage.UsageType = "S" // "Surface"
      if ( CompareAttributeToString( mSPLDef, "SPLD_Usage", "UsageType", "S" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList",
         //:                               mSPLDef, "SPLD_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList", mSPLDef, "SPLD_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitSurfacesSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitSurfacesSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptSurfacesStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptSurfacesSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptSurfacesSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptSurfacesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:InitSurfacesStmtForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSurfacesStmtForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_Usage entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_Usage", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitSurfacesStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_Usage", "InitSurfacesStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_Usage.UsageType = "S" // "Surface"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "UsageType", "S" );
   //:mSPLDef.SPLD_Usage.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_Usage", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
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
//:InitSurfacesStmtForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSurfacesStmtForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitSurfacesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_Usage (Surfaces) entity.  We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (Surfaces) entity that corresponds to the SPLDI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );

   //:// We need to update an SPLD_Usage entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_Usage" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitSurfacesStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Usage", "InitSurfacesStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptHumanHazardSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptHumanHazardSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:InitPrecautionarySect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrecautionarySect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionarySect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitPrecautionarySect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_PrecautionarySection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_PrecautionarySection", "" );
   //:LOOP WHILE RESULT = 0
   while ( RESULT == 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_PrecautionarySection", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_PrecautionarySection", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_PrecautionarySection
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_PrecautionarySection", "" );
   } 

   //:END

   //:FOR EACH mSPLDef.SPLD_GeneralSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_GeneralSection.SectionType = "P" // Precautionary
      if ( CompareAttributeToString( mSPLDef, "SPLD_GeneralSection", "SectionType", "P" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_PrecautionarySection",
         //:                               mSPLDef, "SPLD_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_PrecautionarySection", mSPLDef, "SPLD_GeneralSection", zPOS_LAST );

         //:// We need to create a temporal Precautionary Section entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_PrecautionarySection", "InitPrecautionarySect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_PrecautionarySection", "InitPrecautionarySect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralSection", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_PrecautionarySection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralSection", "InitPrecautionarySectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralSection", "InitPrecautionarySectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrecautionarySectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrecautionarySectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_GeneralSection", "Title" );

   //:// We need to update the existing SPLD_PrecautionarySection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralSection", "InitPrecautionarySectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralSection", "InitPrecautionarySectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitHazardSect( View     ViewToWindow )
{

   //:InitHazardSect( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitFirstAidSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_FirstAidSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_FirstAidSection", "" );
   //:LOOP WHILE RESULT = 0
   while ( RESULT == 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_FirstAidSection", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_FirstAidSection", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_FirstAidSection
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_FirstAidSection", "" );
   } 

   //:END

   //:FOR EACH mSPLDef.SPLD_GeneralSection
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_GeneralSection", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_GeneralSection.SectionType = "F" // FirstAid
      if ( CompareAttributeToString( mSPLDef, "SPLD_GeneralSection", "SectionType", "F" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_FirstAidSection",
         //:                               mSPLDef, "SPLD_GeneralSection", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_FirstAidSection", mSPLDef, "SPLD_GeneralSection", zPOS_LAST );

         //:// We need to create a temporal FirstAid Section entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_FirstAidSection", "InitFirstAidSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_FirstAidSection", "InitFirstAidSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_GeneralSection", "" );
      //:END
   } 

   //:END

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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_FirstAidSection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_GeneralSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralSection", "InitFirstAidSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralSection", "InitFirstAidSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_GeneralSection.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_GeneralSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "F"  // "FirstAid"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "F" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitFirstAidSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitFirstAidSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_GeneralSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_GeneralSection", "Title" );

   //:// We need to update the existing SPLD_FirstAidSection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralSection", "InitFirstAidSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralSection", "InitFirstAidSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAreasOfUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   } 

   //:END

   //:// We need to create SPLDI_UsageList (AreasOfUse) entities.
   //:FOR EACH mSPLDef.SPLD_Usage
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_Usage.UsageType = "U" // "AreasOfUse"
      if ( CompareAttributeToString( mSPLDef, "SPLD_Usage", "UsageType", "U" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList",
         //:                               mSPLDef, "SPLD_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList", mSPLDef, "SPLD_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitAreasOfUseSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitAreasOfUseSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
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
//:InitAppTypesSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitAppTypesSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitAppTypesSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   //:LOOP WHILE RESULT >= 0
   while ( RESULT >= 0 )
   { 
      //:ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE )
      ExcludeEntity( mSPLDef, "SPLDI_UsageList", zREPOS_NONE );
      //:SET CURSOR FIRST mSPLDef.SPLDI_UsageList
      RESULT = SetCursorFirstEntity( mSPLDef, "SPLDI_UsageList", "" );
   } 

   //:END

   //:// We need to create SPLDI_UsageList (ApplicationType) entities.
   //:FOR EACH mSPLDef.SPLD_Usage
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_Usage", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mSPLDef.SPLD_Usage.UsageType = "T" // "ApplicationType"
      if ( CompareAttributeToString( mSPLDef, "SPLD_Usage", "UsageType", "T" ) == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList",
         //:                               mSPLDef, "SPLD_Usage", zPOS_LAST )
         IncludeSubobjectFromSubobject( mSPLDef, "SPLDI_UsageList", mSPLDef, "SPLD_Usage", zPOS_LAST );

         //:// We need to create a temporal UsageList entity.
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitAppTypesSect1: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLDI_UsageList", "InitAppTypesSect1: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
      } 

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_Usage", "" );
      //:END
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "T"  // "ApplicationType"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHumanHazardSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitHumanHazardSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSPLDef.SPLD_HumanHazardSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_HumanHazardSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_HumanHazardSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_HumanHazardSection", "InitHumanHazardSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_HumanHazardSection", "InitHumanHazardSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSPLDef, "SPLD_HumanHazardSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_HumanHazardSection", "InitHumanHazardSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_HumanHazardSection", "InitHumanHazardSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSPLDef.SPLD_HumanHazardSection.BoldItalic = "R"
      SetAttributeFromString( mSPLDef, "SPLD_HumanHazardSection", "BoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "H"  // Hazard
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "H" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSPLDef.SPLD_IngredientsSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_IngredientsSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_IngredientsSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSPLDef, "SPLD_IngredientsSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSPLDef.SPLD_IngredientsSection.ActiveBoldItalic = "R"
      SetAttributeFromString( mSPLDef, "SPLD_IngredientsSection", "ActiveBoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_IngredientsSection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_IngredientsSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_IngredientsSection.ActiveBoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_IngredientsSection", "ActiveBoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitIngredientsSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitIngredientsSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_IngredientsSection.ActiveTitle
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_IngredientsSection", "ActiveTitle" );

   //:// We need to update the existing SPLD_IngredientsSection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_IngredientsSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsSection", "InitIngredientsSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update an SPLD_IngredientsStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_IngredientsStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsStatement", "InitIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsStatement", "InitIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptIngredientsStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptIngredientsStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptIngredientsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_IngredientsStatement entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_IngredientsStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsStatement", "SelectIngredientsStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_IngredientsStatement", "SelectIngredientsStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "I"  // Ingredients
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "I" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "InitIngredientsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "InitIngredientsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to create a new SPLD_IngredientsStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_IngredientsStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_IngredientsStatement", "InitIngredientsStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_IngredientsStatement", "InitIngredientsStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_IngredientsStatement.Active = "A"
   SetAttributeFromString( mSPLDef, "SPLD_IngredientsStatement", "Active", "A" );
   //:mSPLDef.SPLD_IngredientsStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_IngredientsStatement", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "I"  // "Ingredients"
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
//:AcceptHazardStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptHazardStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectHazardStmtForDelete( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
SelectHazardStmtForDelete( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_HazardStatement", "" );
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
//:ConfirmDeleteEnvironStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteEnvironStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteEnvironStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteEnvironStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_GeneralStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_GeneralStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptFirstAidStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptFirstAidStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SPLD_PrecautionarySection entity.  We have
   //:// position on the SPLD_PrecautionaryStatement, but need to get position on
   //:// the SPLD_GeneralStatement that corresponds to the SPLD_PrecautionaryStatement.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "SelectPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "SelectPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "SelectPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "SelectPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // Precautionary
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:STRING (  32  ) szSectionType
   String   szSectionType = null;
   //:STRING ( 256  ) szTitle
   String   szTitle = null;
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = wWebXfer.Root.CurrentContentType
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "CurrentContentType" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", szTempString_0, "" );
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

   //:// We need to create a new SPLD_GeneralStatement entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_GeneralStatement", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralStatement", "InitPrecautionaryStmtForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_GeneralStatement", "InitPrecautionaryStmtForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_GeneralStatement.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_GeneralStatement", "BoldItalic", "R" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SET CURSOR FIRST mSPLDef.SPLD_GeneralSection WHERE mSPLDef.SPLD_GeneralSection.SectionType = "P" // "Precautionary"
   RESULT = SetCursorFirstEntityByString( mSPLDef, "SPLD_GeneralSection", "SectionType", "P", "" );
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

   //:// We need to update an SPLD_GeneralStatement entity.
   //:TraceLineS( "InitPrecautionaryStmtForUpdate: ", "SPLD_GeneralStatement" )
   TraceLineS( "InitPrecautionaryStmtForUpdate: ", "SPLD_GeneralStatement" );
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_GeneralStatement" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "InitPrecautionaryStmtForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_GeneralStatement", "InitPrecautionaryStmtForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitListSubregProducts( View     ViewToWindow )
{

   //:InitListSubregProducts( VIEW ViewToWindow )

   //:// VIEW lPrimReg REGISTERED AS lPrimReg
   //:// VIEW mSubreg  BASED ON LOD  mSubreg
   //://
   //:// GET VIEW mSubreg NAMED "mSubreg"
   //:// IF mSubreg != 0
   //://    DropObjectInstance( mSubreg )
   //:// END
   //://
   //:// ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lPrimReg.PrimaryRegistrant.ID
   //:// NAME VIEW mSubreg "mSubreg"

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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.Units = "Gallons"
   SetAttributeFromString( wWebXfer, "Root", "Units", "Gallons" );
   //:IF mSPLDef.SPLD_StorageDisposalSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_StorageDisposalSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_StorageDisposalSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSPLDef, "SPLD_StorageDisposalSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSPLDef.SPLD_StorageDisposalSection.BoldItalic = "R"
      SetAttributeFromString( mSPLDef, "SPLD_StorageDisposalSection", "BoldItalic", "R" );
   } 

   //:END

   //:wWebXfer.Root.CurrentContentType = "D"  // StorageDisposal
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_StorageDisposalSection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_StorageDisposalSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_StorageDisposalSection.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_StorageDisposalSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "D" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitStorDispSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitStorDispSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_StorageDisposalSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_StorageDisposalSection", "Title" );

   //:// We need to update the existing SPLD_StorageDisposalSection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_StorageDisposalSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_StorageDisposalSection", "InitStorDispSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "D"  // "StorDisp"
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitDirectionsUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSPLDef.SPLD_DirectionsForUseSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_DirectionsForUseSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_DirectionsForUseSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_DirectionsForUseSection", "InitDirectionsUseSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSPLDef.SPLD_DirectionsForUseSection.BoldItalic = "R"
      SetAttributeFromString( mSPLDef, "SPLD_DirectionsForUseSection", "BoldItalic", "R" );
   } 

   //:END

   //:LoadDirectionsUsageList( ViewToWindow, mSPLDef )
   o_LoadDirectionsUsageList( ViewToWindow, mSPLDef );
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
//:InitMarketingSect( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingSect( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "InitMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:IF mSPLDef.SPLD_MarketingSection EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SPLD_MarketingSection" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_MarketingSection" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSect1: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSect1: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:// Get here the first time into the section.
      //:// CreateTemporalEntity( mSPLDef, "SPLD_MarketingSection", zPOS_LAST )
      //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSect2: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSect2: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:mSPLDef.SPLD_MarketingSection.BoldItalic = "R"
      SetAttributeFromString( mSPLDef, "SPLD_MarketingSection", "BoldItalic", "R" );
   } 

   //:END

   //:LoadMarketingUsageList( ViewToWindow, mSPLDef )
   o_LoadMarketingUsageList( ViewToWindow, mSPLDef );
   //:wWebXfer.Root.CurrentContentType = "M"  // Marketing
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
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
//:AcceptPrecautionaryStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptPrecautionaryStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptPrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// Ensure section title is not blank.
   //:szSectionTitle = mSPLDef.SPLD_GeneralSection.Title
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szSectionTitle;
   if ( szSectionTitle == null )
      sb_szSectionTitle = new StringBuilder( 32 );
   else
      sb_szSectionTitle = new StringBuilder( szSectionTitle );
       GetVariableFromAttribute( sb_szSectionTitle, mi_lTempInteger_0, 'S', 51, mSPLDef, "SPLD_GeneralSection", "Title", "", 0 );
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

   //:IF szEntityName = "SPLD_GeneralSection" AND wWebXfer.Root.CurrentUpdate = "N"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLD_GeneralSection", 1, 0, 33 ) == 0 && CompareAttributeToString( wWebXfer, "Root", "CurrentUpdate", "N" ) == 0 )
   { 

      //:// szSectionType = mSPLDef.SPLD_GeneralSection.SectionType
      //:GetStrFromAttrByContext( szSectionType, 33, mSPLDef,
      //:                         "SPLD_GeneralSection", "SectionType", "ContentSectionType" )
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mSPLDef );
       {StringBuilder sb_szSectionType;
      if ( szSectionType == null )
         sb_szSectionType = new StringBuilder( 32 );
      else
         sb_szSectionType = new StringBuilder( szSectionType );
             m_ZGlobal1_Operation.GetStrFromAttrByContext( sb_szSectionType, 33, mSPLDef, "SPLD_GeneralSection", "SectionType", "ContentSectionType" );
      szSectionType = sb_szSectionType.toString( );}
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:szEntityName = "SPLDI_" + szSectionType + "Section"
       {StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
            ZeidonStringCopy( sb_szEntityName, 1, 0, "SPLDI_", 1, 0, 33 );
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
      //:IncludeSubobjectFromSubobject( mSPLDef, szEntityName,
      //:                               mSPLDef, "SPLD_GeneralSection", zPOS_FIRST )
      IncludeSubobjectFromSubobject( mSPLDef, szEntityName, mSPLDef, "SPLD_GeneralSection", zPOS_FIRST );
   } 

   //:END

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   //:wWebXfer.Root.CurrentContentType = "P"  // "Precautionary"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "P" );
   return( 0 );
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

   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
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
//:UpdateSubregProduct( VIEW ViewToWindow )

public int 
UpdateSubregProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
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
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptProductName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductName", "" );
   //:wWebXfer.Root.AttemptProductNumber = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductNumber", "" );

   //:// We need to create a new SubregProduct entity.
   //:CreateTemporalEntity( mSubreg, "SubregProduct", zPOS_LAST )
   CreateTemporalEntity( mSubreg, "SubregProduct", zPOS_LAST );

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
//:InitSubregProductForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregProductForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptProductName = mSubreg.SubregProduct.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductName", mSubreg, "SubregProduct", "Name" );
   //:wWebXfer.Root.AttemptProductNumber = mSubreg.SubregProduct.Number
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductNumber", mSubreg, "SubregProduct", "Number" );

   //:CreateTemporalSubobjectVersion( mSubreg, "SubregProduct" )
   CreateTemporalSubobjectVersion( mSubreg, "SubregProduct" );
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );
   //:OrderEntityForView( mSubreg, "SubregLabelContent", "CreatedDateTime D" )
   OrderEntityForView( mSubreg, "SubregLabelContent", "CreatedDateTime D" );

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
//:AcceptNewSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
AcceptNewSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Preliminary check is performed by the JSP to ensure that exactly one Master Product is selected.
   //:IF mSubreg.PrimaryRegistrant EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "PrimaryRegistrant" );
   if ( lTempInteger_0 == 0 )
   { 

      //:SET CURSOR FIRST mSubreg.ListMasterProduct WHERE mSubreg.ListMasterProduct.wkSelected = "Y"
      RESULT = SetCursorFirstEntityByString( mSubreg, "ListMasterProduct", "wkSelected", "Y", "" );
      //:IF RESULT = 0
      if ( RESULT == 0 )
      { 
         //:IncludeSubobjectFromSubobject( mSubreg, "MasterProduct",
         //:                               mSubreg, "ListMasterProduct", zPOS_BEFORE )
         IncludeSubobjectFromSubobject( mSubreg, "MasterProduct", mSubreg, "ListMasterProduct", zPOS_BEFORE );
         //:AcceptSubobject( mSubreg, "SubregProduct" )
         AcceptSubobject( mSubreg, "SubregProduct" );
         //:Commit mSubreg
         RESULT = CommitObjectInstance( mSubreg );
         //:RETURN 0
         if(8==8)return( 0 );
      } 

      //:END
   } 

   //:END

   //:MessageSend( ViewToWindow, "", "New Subregistrant Product",
   //:             "One Master Product must be selected.",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "New Subregistrant Product", "One Master Product must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
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
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

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
      //:IF szProductName != mSubreg.SubregProduct.Name
      if ( CompareAttributeToString( mSubreg, "SubregProduct", "Name", szProductName ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSubreg, "SubregProduct", "Name", lControl,
         //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mSubreg, "SubregProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
         if ( lTempInteger_1 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Subregistrant Product",
            //:             "The Subregistrant Product Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Subregistrant Product", "The Subregistrant Product Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:TraceLineS( "Original Product Name: ", mSubreg.SubregProduct.Name )
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, mSubreg, "SubregProduct", "Name" );
            szTempString_0 = sb_szTempString_0.toString( );}
            TraceLineS( "Original Product Name: ", szTempString_0 );
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
      //:IF szProductNumber != mSubreg.SubregProduct.Number
      if ( CompareAttributeToString( mSubreg, "SubregProduct", "Number", szProductNumber ) != 0 )
      { 
         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSubreg, "SubregProduct", "Number", lControl,
         //:                    szProductNumber, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_3 = SetEntityCursor( mSubreg, "SubregProduct", "Number", lControl, szProductNumber, "", "", 0, "", "" );
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

   //:mSubreg.SubregProduct.Name = szProductName
   SetAttributeFromString( mSubreg, "SubregProduct", "Name", szProductName );
   //:mSubreg.SubregProduct.Number = szProductNumber
   SetAttributeFromString( mSubreg, "SubregProduct", "Number", szProductNumber );
   //:AcceptSubobject( mSubreg, "SubregProduct" )
   AcceptSubobject( mSubreg, "SubregProduct" );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   return( 0 );
// // DropObjectInstance( mSubreg )
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelNewSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "SubregProduct" )
   CancelSubobject( mSubreg, "SubregProduct" );
   //:SET CURSOR FIRST mSubreg.SubregProduct
   RESULT = SetCursorFirstEntity( mSubreg, "SubregProduct", "" );
   return( 0 );
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

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSubreg, "SubregLabelContent" )
   //:CancelSubobject( mSubreg, "SubregProduct" )
   CancelSubobject( mSubreg, "SubregProduct" );
   //:SET CURSOR FIRST mSubreg.SubregProduct
   RESULT = SetCursorFirstEntity( mSubreg, "SubregProduct", "" );
   return( 0 );
// // DropObjectInstance( mSubreg )
// END
} 


//:DIALOG OPERATION
//:DeletePhysicalLabelDef( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
DeletePhysicalLabelDef( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... DeletePhysicalLabelDef" )
   IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... DeletePhysicalLabelDef" );
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

      //:ACTIVATE mSPLDef WHERE mSPLDef.SubregProduct.ID = lID
      o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mSPLDef "mSPLDef"
      SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
public int 
NewPhysicalLabelDef( View     ViewToWindow )
{

   //:NewPhysicalLabelDef( VIEW ViewToWindow )

   //:// nothing to be done as far as I can tell at this point
   //:IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... NewPhysicalLabelDef" )
   IssueError( ViewToWindow, 0, 0, "Didn't expect to be here ... NewPhysicalLabelDef" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EditPhysicalLabelVersionData( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
EditPhysicalLabelVersionData( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditPhysicalLabelVersionData: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "EditPhysicalLabelVersionData: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:IF mSPLDef != 0
   if ( getView( mSPLDef ) != null )
   { 
      //:lID = mSPLDef.SubregLabelContent.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SubregLabelContent", "ID" );
      lID = mi_lID.intValue( );}
      //:DropObjectInstance( mSPLDef )
      DropObjectInstance( mSPLDef );
   } 

   //:END

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:IF mSubreg.SubregLabelContent DOES NOT EXIST  // should never happen!!!
   lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "SubregLabelContent" );
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

   //:lID = mSubreg.SubregProduct.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "SubregProduct", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mSubreg.SubregLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mSubreg, "SubregLabelContent", "ID" );
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
      o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lID );
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
//:InitPhysicalLabelDefForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPhysicalLabelDefForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:IF mSPLDef != 0
   if ( getView( mSPLDef ) != null )
   { 
      //:DropObjectInstance( mSPLDef )
      DropObjectInstance( mSPLDef );
   } 

   //:END

   //:CreateSPLD( ViewToWindow )
   CreateSPLD( ViewToWindow );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:mSPLDef.SubregPhysicalLabelDef.CreatedDateTime = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mSPLDef, "SubregPhysicalLabelDef", "CreatedDateTime", wWebXfer, "Root", "dCurrentDateTime" );
   //:wWebXfer.Root.CurrentContentType = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "" );

   //:SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_PrecautionaryStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_PrecautionaryStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_PrecautionaryStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSPLDef, "SPLD_GeneralStatement", "ID",
   //://                             mSPLDef, "SPLD_PrecautionaryStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempPL, "SPLD_GeneralStatement", "ID",
   //://                             mTempPL, "SPLD_PrecautionaryStatement", "ID", "" )

   //:MoveSubobject( mTempPL, "SPLD_PrecautionaryStatement",
   //:               mSPLDef, "SPLD_PrecautionaryStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_PrecautionaryStatement", mSPLDef, "SPLD_PrecautionaryStatement", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLD_GeneralStatement",
   //:               mSPLDef, "SPLD_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_GeneralStatement", mSPLDef, "SPLD_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MovePrecautionaryStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_PrecautionaryStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_PrecautionaryStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_PrecautionaryStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSPLDef, "SPLD_GeneralStatement", "ID",
   //://                             mSPLDef, "SPLD_PrecautionaryStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempPL, "SPLD_GeneralStatement", "ID",
   //://                             mTempPL, "SPLD_PrecautionaryStatement", "ID", "" )

   //:MoveSubobject( mTempPL, "SPLD_PrecautionaryStatement",
   //:               mSPLDef, "SPLD_PrecautionaryStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_PrecautionaryStatement", mSPLDef, "SPLD_PrecautionaryStatement", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLD_GeneralStatement",
   //:               mSPLDef, "SPLD_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_GeneralStatement", mSPLDef, "SPLD_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_DirectionsForUseStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_DirectionsForUseStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_DirectionsForUseStatement",
   //:               mSPLDef, "SPLD_DirectionsForUseStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_DirectionsForUseStatement", mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_DirectionsForUseSection
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_DirectionsForUseSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_DirectionsForUseSection",
   //:               mSPLDef, "SPLD_DirectionsForUseSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_DirectionsForUseSection", mSPLDef, "SPLD_DirectionsForUseSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_DirectionsForUseSection
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_DirectionsForUseSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_DirectionsForUseSection",
   //:               mSPLDef, "SPLD_DirectionsForUseSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_DirectionsForUseSection", mSPLDef, "SPLD_DirectionsForUseSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
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
      //:SET CURSOR NEXT mTempPL.SPLDI_UsageList
      RESULT = SetCursorNextEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:ImportAreasOfUseStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAreasOfUseStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName );
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

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLDI_UsageList
      RESULT = SetCursorNextEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:ImportAppTypesStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportAppTypesStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

   //:szDirectoryName = szDirectoryName + szFileName
    {StringBuilder sb_szDirectoryName;
   if ( szDirectoryName == null )
      sb_szDirectoryName = new StringBuilder( 32 );
   else
      sb_szDirectoryName = new StringBuilder( szDirectoryName );
      ZeidonStringConcat( sb_szDirectoryName, 1, 0, szFileName, 1, 0, 513 );
   szDirectoryName = sb_szDirectoryName.toString( );}
   //:nRC = ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName );
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

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAppTypesStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLDI_UsageList
      RESULT = SetCursorPrevEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveAreasOfUseStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLDI_UsageList
      RESULT = SetCursorPrevEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLDI_UsageList
      RESULT = SetCursorNextEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveSurfacesStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLDI_UsageList
      RESULT = SetCursorPrevEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLDI_UsageList
      RESULT = SetCursorNextEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
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
//:ImportClaimsStatements( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ImportClaimsStatements( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
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
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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
   //:nRC = ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName )
   try
   {
       nRC = m_ZDRVROPR.ImportCSV_ToZeidonOI( mSPLDef, szDirectoryName );
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

   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveClaimsStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLDI_UsageList
      RESULT = SetCursorPrevEntity( mTempPL, "SPLDI_UsageList", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, "SPLDI_UsageList", "" );

   //:MoveSubobject( mTempPL, "SPLD_Usage",
   //:               mSPLDef, "SPLD_Usage",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_Usage", mSPLDef, "SPLD_Usage", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLDI_UsageList",
   //:               mSPLDef, "SPLDI_UsageList",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLDI_UsageList", mSPLDef, "SPLDI_UsageList", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveDirectionsUseStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_DirectionsForUseStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_DirectionsForUseStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_DirectionsForUseStatement",
   //:               mSPLDef, "SPLD_DirectionsForUseStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_DirectionsForUseStatement", mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_MarketingStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_MarketingStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_MarketingStatement",
   //:               mSPLDef, "SPLD_MarketingStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_MarketingStatement", mSPLDef, "SPLD_MarketingStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveMarketingStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_MarketingStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_MarketingStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_MarketingStatement",
   //:               mSPLDef, "SPLD_MarketingStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_MarketingStatement", mSPLDef, "SPLD_MarketingStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_StorageDisposalStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_StorageDisposalStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_StorageDisposalStatement",
   //:               mSPLDef, "SPLD_StorageDisposalStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_StorageDisposalStatement", mSPLDef, "SPLD_StorageDisposalStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
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
      //:SET CURSOR NEXT mTempPL.SPLD_StorageDisposalStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_StorageDisposalStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_StorageDisposalStatement",
   //:               mSPLDef, "SPLD_StorageDisposalStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_StorageDisposalStatement", mSPLDef, "SPLD_StorageDisposalStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_StorageDisposalSection
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_StorageDisposalSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_StorageDisposalSection",
   //:               mSPLDef, "SPLD_StorageDisposalSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_StorageDisposalSection", mSPLDef, "SPLD_StorageDisposalSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveStorDispSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_StorageDisposalSection
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_StorageDisposalSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_StorageDisposalSection",
   //:               mSPLDef, "SPLD_StorageDisposalSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_StorageDisposalSection", mSPLDef, "SPLD_StorageDisposalSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_IngredientsStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_IngredientsStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_IngredientsStatement",
   //:               mSPLDef, "SPLD_IngredientsStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_IngredientsStatement", mSPLDef, "SPLD_IngredientsStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveIngredientsStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_IngredientsStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_IngredientsStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_IngredientsStatement",
   //:               mSPLDef, "SPLD_IngredientsStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_IngredientsStatement", mSPLDef, "SPLD_IngredientsStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_HazardStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_HazardStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_HazardStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_HazardStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSPLDef, "SPLD_GeneralStatement", "ID",
   //://                             mSPLDef, "SPLD_HazardStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempPL, "SPLD_GeneralStatement", "ID",
   //://                             mTempPL, "SPLD_HazardStatement", "ID", "" )

   //:MoveSubobject( mTempPL, "SPLD_HazardStatement",
   //:               mSPLDef, "SPLD_HazardStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_HazardStatement", mSPLDef, "SPLD_HazardStatement", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLD_GeneralStatement",
   //:               mSPLDef, "SPLD_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_GeneralStatement", mSPLDef, "SPLD_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_HumanHazardSection
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_HumanHazardSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_HumanHazardSection",
   //:               mSPLDef, "SPLD_HumanHazardSection",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_HumanHazardSection", mSPLDef, "SPLD_HumanHazardSection", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardSectUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_HumanHazardSection
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_HumanHazardSection", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempPL, "SPLD_HumanHazardSection",
   //:               mSPLDef, "SPLD_HumanHazardSection",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_HumanHazardSection", mSPLDef, "SPLD_HumanHazardSection", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveHazardStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_HazardStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_HazardStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_HazardStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_HazardStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_HazardStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSPLDef, "SPLD_GeneralStatement", "ID",
   //://                             mSPLDef, "SPLD_HazardStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempPL, "SPLD_GeneralStatement", "ID",
   //://                             mTempPL, "SPLD_HazardStatement", "ID", "" )

   //:MoveSubobject( mTempPL, "SPLD_HazardStatement",
   //:               mSPLDef, "SPLD_HazardStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_HazardStatement", mSPLDef, "SPLD_HazardStatement", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLD_GeneralStatement",
   //:               mSPLDef, "SPLD_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_GeneralStatement", mSPLDef, "SPLD_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtDown: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtDown: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR NEXT mTempPL.SPLD_FirstAidStatement
      RESULT = SetCursorNextEntity( mTempPL, "SPLD_FirstAidStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_FirstAidStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSPLDef, "SPLD_GeneralStatement", "ID",
   //://                             mSPLDef, "SPLD_FirstAidStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempPL, "SPLD_GeneralStatement", "ID",
   //://                             mTempPL, "SPLD_FirstAidStatement", "ID", "" )

   //:MoveSubobject( mTempPL, "SPLD_FirstAidStatement",
   //:               mSPLDef, "SPLD_FirstAidStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_FirstAidStatement", mSPLDef, "SPLD_FirstAidStatement", zPOS_NEXT, zREPOS_NEXT );
   //:MoveSubobject( mTempPL, "SPLD_GeneralStatement",
   //:               mSPLDef, "SPLD_GeneralStatement",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempPL, "SPLD_GeneralStatement", mSPLDef, "SPLD_GeneralStatement", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtUp: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "MoveFirstAidStmtUp: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
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
      //:SET CURSOR PREVIOUS mTempPL.SPLD_FirstAidStatement
      RESULT = SetCursorPrevEntity( mTempPL, "SPLD_FirstAidStatement", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" );
   //:SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, "SPLD_FirstAidStatement", "" );

   //:// SetCursorFirstEntityByAttr( mSPLDef, "SPLD_GeneralStatement", "ID",
   //://                             mSPLDef, "SPLD_FirstAidStatement", "ID", "" )
   //:// SetCursorFirstEntityByAttr( mTempPL, "SPLD_GeneralStatement", "ID",
   //://                             mTempPL, "SPLD_FirstAidStatement", "ID", "" )

   //:MoveSubobject( mTempPL, "SPLD_FirstAidStatement",
   //:               mSPLDef, "SPLD_FirstAidStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_FirstAidStatement", mSPLDef, "SPLD_FirstAidStatement", zPOS_PREV, zREPOS_PREV );
   //:MoveSubobject( mTempPL, "SPLD_GeneralStatement",
   //:               mSPLDef, "SPLD_GeneralStatement",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempPL, "SPLD_GeneralStatement", mSPLDef, "SPLD_GeneralStatement", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempPL )
   DropView( mTempPL );

   //:// We now commit the Master Label Content to maintain order!
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
SelectPrecautionaryStmtForDelete( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_PrecautionaryStatement", "" );
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeletePrecautionaryStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeletePrecautionaryStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeletePrecautionaryStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_GeneralStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_GeneralStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAreasOfUseStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// We need to update the existing SPLD_Usage (AreasOfUse) entity. We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (AreasOfUse) entity that corresponds to the SPLDI_FirstAidSection entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );

   //:wWebXfer.Root.CurrentContentType = "U"  // "AreasOfUse"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "U" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAreasOfUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAreasOfUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_Usage
   RESULT = DeleteEntity( mSPLDef, "SPLD_Usage", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SPLD_Usage (AppTypes) entity.  We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (AppTypes) entity that corresponds to the SPLDI_FirstAidSection entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectAppTypesStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.CurrentContentType = "T"  // "ApplicationType"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "T" );
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
//:SelectClaimsStmtForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectClaimsStmtForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SPLD_Usage (Claims) entity.  We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (Claims) entity that corresponds to the SPLDI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectClaimsStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SPLD_Usage (Surfaces) entity.  We have
   //:// position on the SPLDI_UsageList entity, but need to get position on
   //:// the SPLD_Usage (Surfaces) entity that corresponds to the SPLDI_UsageList entity.
   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_Usage", mSPLDef, "SPLDI_UsageList", "" );
   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForDelete: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "SelectSurfacesStmtForDelete: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.CurrentContentType = "S"  // "Surface"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "S" );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteSurfacesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteSurfacesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_Usage
   RESULT = DeleteEntity( mSPLDef, "SPLD_Usage", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

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
//:SelectFirstAidStmtForDelete( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
SelectFirstAidStmtForDelete( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" )
   SetCursorFirstEntityByEntityCsr( mSPLDef, "SPLD_GeneralStatement", mSPLDef, "SPLD_FirstAidStatement", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteFirstAidStmt( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteFirstAidStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFirstAidStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteFirstAidStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_GeneralStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_GeneralStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteIngredientStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteIngredientStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteIngredientStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_IngredientsStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_IngredientsStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteStorDispStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_StorageDisposalStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_StorageDisposalStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteStorDispSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteStorDispSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteStorDispSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_StorageDisposalSection
   RESULT = DeleteEntity( mSPLDef, "SPLD_StorageDisposalSection", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteMarketingStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_MarketingStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_MarketingStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMarketingSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteMarketingSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteMarketingSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_MarketingSection
   RESULT = DeleteEntity( mSPLDef, "SPLD_MarketingSection", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteDirectionsUseStmt( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionsUseStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_DirectionsForUseStatement
   RESULT = DeleteEntity( mSPLDef, "SPLD_DirectionsForUseStatement", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
ConfirmDeleteDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionUseSect: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteDirectionUseSect: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_DirectionsForUseSection
   RESULT = DeleteEntity( mSPLDef, "SPLD_DirectionsForUseSection", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteClaimsStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteClaimsStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_Usage
   RESULT = DeleteEntity( mSPLDef, "SPLD_Usage", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

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
//:ConfirmDeleteAppTypesStmt( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmDeleteAppTypesStmt( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// We will cancel any changes including those for entities that may be involved.
   //:// We could accept, but a problem could arise if the accept triggered an error.
   //:CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAppTypesStmt: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CancelCurrentTemporalSubobject( ViewToWindow, "ConfirmDeleteAppTypesStmt: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:DELETE ENTITY mSPLDef.SPLD_Usage
   RESULT = DeleteEntity( mSPLDef, "SPLD_Usage", zPOS_NEXT );
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );

   //:wWebXfer.Root.CurrentUpdate = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
   //:wWebXfer.Root.CurrentContentType = "T"  // "ApplicationType"
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
      //:RETURN 2
      if(8==8)return( 2 );
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
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAddNewIngredient( VIEW ViewToWindow )

//:   VIEW  mSPLDef REGISTERED AS mSPLDef
public int 
SaveAddNewIngredient( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 256 ) szPrompt
   String   szPrompt = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:nRC = AcceptIngredientsStmt( ViewToWindow )
   nRC = AcceptIngredientsStmt( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:szPrompt = mSPLDef.SPLD_IngredientsStatement.Prompt
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szPrompt;
      if ( szPrompt == null )
         sb_szPrompt = new StringBuilder( 32 );
      else
         sb_szPrompt = new StringBuilder( szPrompt );
             GetVariableFromAttribute( sb_szPrompt, mi_lTempInteger_0, 'S', 257, mSPLDef, "SPLD_IngredientsStatement", "Prompt", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szPrompt = sb_szPrompt.toString( );}
      //:InitIngredientsStmtForInsert( ViewToWindow )
      InitIngredientsStmtForInsert( ViewToWindow );
      //:mSPLDef.SPLD_IngredientsStatement.Prompt = szPrompt
      SetAttributeFromString( mSPLDef, "SPLD_IngredientsStatement", "Prompt", szPrompt );
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

//:   VIEW  mSPLDef REGISTERED AS mSPLDef
public int 
SaveAddNewClaim( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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

//:   VIEW  mSPLDef REGISTERED AS mSPLDef
public int 
SaveAddNewSurface( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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
//:SaveAddNewAreasOfUse( VIEW ViewToWindow )

//:   VIEW  mSPLDef REGISTERED AS mSPLDef
public int 
SaveAddNewAreasOfUse( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

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
//:InitMarketingSectForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingSectForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptSectionName", "" );

   //:// We need to create a new SPLD_MarketingSection entity.
   //:// CreateTemporalEntity( mSPLDef, "SPLD_MarketingSection", zPOS_LAST )
   //:CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSectForInsert: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, zPOS_LAST, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSectForInsert: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:mSPLDef.SPLD_MarketingSection.BoldItalic = "R"
   SetAttributeFromString( mSPLDef, "SPLD_MarketingSection", "BoldItalic", "R" );
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMarketingSectForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMarketingSectForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptSectionName = mSPLDef.SPLD_MarketingSection.Title
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptSectionName", mSPLDef, "SPLD_MarketingSection", "Title" );

   //:// We need to update the existing SPLD_MarketingSection entity.
   //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_MarketingSection" )
   //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSectForUpdate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingSection", "InitMarketingSectForUpdate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.CurrentContentType = "M"  // "Marketing"
   SetAttributeFromString( wWebXfer, "Root", "CurrentContentType", "M" );
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
//:CleanupMarketingWorkEntities( VIEW mSPLDef )

//:   VIEW mTempPL  BASED ON LOD  mSPLDef
public int 
CleanupMarketingWorkEntities( View     mSPLDef )
{
   zVIEW    mTempPL = new zVIEW( );
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


   //:GET VIEW mTempPL NAMED "mTempPL"
   RESULT = GetViewByName( mTempPL, "mTempPL", mSPLDef, zLEVEL_TASK );
   //:IF mTempPL != 0
   if ( getView( mTempPL ) != null )
   { 
      //:DropView( mTempPL )
      DropView( mTempPL );
   } 

   //:END
   //:CreateViewFromView( mTempPL, mSPLDef )
   CreateViewFromView( mTempPL, mSPLDef );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );

   //:// We need to traverse SPLD_MarketingOrdering entities and delete the work sub-entities.
   //:FOR EACH mTempPL.SPLD_MarketingOrdering
   RESULT = SetCursorFirstEntity( mTempPL, "SPLD_MarketingOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mTempPL.SPLD_MarketingUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mTempPL, "SPLD_MarketingUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mTempPL.SPLD_MarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mTempPL, "SPLD_MarketingUsage", "ClaimsClassification", "", 0 );
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
         //:nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification )
         nRC = CheckExistenceOfEntity( mTempPL, szClaimsClassification );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE )
            DeleteEntity( mTempPL, szClaimsClassification, zREPOS_NONE );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:nRC = CheckExistenceOfEntity( mTempPL, "MarketingSurface" )
            nRC = CheckExistenceOfEntity( mTempPL, "MarketingSurface" );
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:DELETE ENTITY mTempPL.MarketingSurface
               RESULT = DeleteEntity( mTempPL, "MarketingSurface", zPOS_NEXT );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:nRC = CheckExistenceOfEntity( mTempPL, "MarketingAppType" )
               nRC = CheckExistenceOfEntity( mTempPL, "MarketingAppType" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:DELETE ENTITY mTempPL.MarketingAppType
                  RESULT = DeleteEntity( mTempPL, "MarketingAppType", zPOS_NEXT );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:nRC = CheckExistenceOfEntity( mTempPL, "MarketingAreasOfUse" )
                  nRC = CheckExistenceOfEntity( mTempPL, "MarketingAreasOfUse" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:DELETE ENTITY mTempPL.MarketingAreasOfUse
                     RESULT = DeleteEntity( mTempPL, "MarketingAreasOfUse", zPOS_NEXT );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( mTempPL, "SPLD_MarketingOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mTempPL )
   DropView( mTempPL );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:LoadMarketingUsageList( VIEW ViewToWindow,
//:                        VIEW mSPLDefIn BASED ON LOD mSPLDef )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
private int 
o_LoadMarketingUsageList( View     ViewToWindow,
                          View     mSPLDefIn )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mPosPL   BASED ON LOD  mSPLDef
   zVIEW    mPosPL = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
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

   //:CleanupMarketingWorkEntities( mSPLDefIn )
   CleanupMarketingWorkEntities( mSPLDefIn );

   //:CreateViewFromView( mPosPL, mSPLDefIn )
   CreateViewFromView( mPosPL, mSPLDefIn );
   //:NAME VIEW mPosPL "mPosPL"
   SetNameForView( mPosPL, "mPosPL", null, zLEVEL_TASK );
   //:CreateViewFromView( mTempPL, mSPLDefIn )
   CreateViewFromView( mTempPL, mSPLDefIn );
   //:NAME VIEW mTempPL "mTempPL"
   SetNameForView( mTempPL, "mTempPL", null, zLEVEL_TASK );
   //:CreateViewFromView( mSPLDef, mSPLDefIn )
   CreateViewFromView( mSPLDef, mSPLDefIn );
   //:NAME VIEW mSPLDef "mSPLDef1"
   SetNameForView( mSPLDef, "mSPLDef1", null, zLEVEL_TASK );

   //:// Get position on included SPLD_MarketingUsage entities (which will be marked as selected).
   //:SetCursorFirstEntity( mPosPL, "SPLD_MarketingOrdering", "" )
   SetCursorFirstEntity( mPosPL, "SPLD_MarketingOrdering", "" );

   //:// Mark included SPLD_MarketingUsage entities as "selected" and include SPLD_OriginalMarketingUsage not
   //:// already included into the SPLD_MarketingUsage entity and mark as "not selected".
   //:FOR EACH mSPLDef.SPLD_OriginalMarketingOrdering
   RESULT = SetCursorFirstEntity( mSPLDef, "SPLD_OriginalMarketingOrdering", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:lID = mSPLDef.SPLD_OriginalMarketingUsage.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SPLD_OriginalMarketingUsage", "ID" );
      lID = mi_lID.intValue( );}
      //:SET CURSOR FIRST mTempPL.SPLD_MarketingUsage WITHIN mTempPL.SPLD_MarketingSection
      //:                                             WHERE mTempPL.SPLD_MarketingUsage.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mTempPL, "SPLD_MarketingUsage", "ID", lID, "SPLD_MarketingSection" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:SET CURSOR FIRST mPosPL.SPLD_MarketingUsage WITHIN mPosPL.SPLD_MarketingSection
         //:                                            WHERE mPosPL.SPLD_MarketingUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mPosPL, "SPLD_MarketingUsage", "ID", lID, "SPLD_MarketingSection" );
         //:mPosPL.SPLD_MarketingUsage.wkSelected = "Y"
         SetAttributeFromString( mPosPL, "SPLD_MarketingUsage", "wkSelected", "Y" );
         //:ELSE
      } 
      else
      { 
         //:CreateEntity( mPosPL, "SPLD_MarketingOrdering", zPOS_AFTER )
         CreateEntity( mPosPL, "SPLD_MarketingOrdering", zPOS_AFTER );
         //:IncludeSubobjectFromSubobject( mPosPL, "SPLD_MarketingUsage",
         //:                               mSPLDef, "SPLD_OriginalMarketingUsage", zPOS_NEXT )
         IncludeSubobjectFromSubobject( mPosPL, "SPLD_MarketingUsage", mSPLDef, "SPLD_OriginalMarketingUsage", zPOS_NEXT );
         //:mPosPL.SPLD_MarketingUsage.wkSelected = ""
         SetAttributeFromString( mPosPL, "SPLD_MarketingUsage", "wkSelected", "" );
      } 

      //:END

      //:// "C"-Claim; "S"-Surface; "T"-ApplicationType; "U"-AreasOfUse
      //:szUsageType = mPosPL.SPLD_OriginalMarketingUsage.UsageType
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szUsageType;
      if ( szUsageType == null )
         sb_szUsageType = new StringBuilder( 32 );
      else
         sb_szUsageType = new StringBuilder( szUsageType );
             GetVariableFromAttribute( sb_szUsageType, mi_lTempInteger_0, 'S', 2, mPosPL, "SPLD_OriginalMarketingUsage", "UsageType", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szUsageType = sb_szUsageType.toString( );}
      //:IF szUsageType = "C"
      if ( ZeidonStringCompare( szUsageType, 1, 0, "C", 1, 0, 2 ) == 0 )
      { 
         //:szClaimsClassification = "Marketing" + mPosPL.SPLD_OriginalMarketingUsage.ClaimsClassification
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 17, mPosPL, "SPLD_OriginalMarketingUsage", "ClaimsClassification", "", 0 );
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
         //:CreateEntity( mPosPL, szClaimsClassification, zPOS_AFTER )
         CreateEntity( mPosPL, szClaimsClassification, zPOS_AFTER );
         //:ELSE
      } 
      else
      { 
         //:IF szUsageType = "S"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:CreateEntity( mPosPL, "MarketingSurface", zPOS_AFTER )
            CreateEntity( mPosPL, "MarketingSurface", zPOS_AFTER );
            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "T"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "T", 1, 0, 2 ) == 0 )
            { 
               //:CreateEntity( mPosPL, "MarketingAppType", zPOS_AFTER )
               CreateEntity( mPosPL, "MarketingAppType", zPOS_AFTER );
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "U"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "U", 1, 0, 2 ) == 0 )
               { 
                  //:CreateEntity( mPosPL, "MarketingAreasOfUse", zPOS_AFTER )
                  CreateEntity( mPosPL, "MarketingAreasOfUse", zPOS_AFTER );
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

      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_OriginalMarketingOrdering", "" );
      //:END
   } 


   //:END

   //:DropView( mPosPL )
   DropView( mPosPL );
   //:DropView( mTempPL )
   DropView( mTempPL );
   //:DropView( mSPLDef )
   DropView( mSPLDef );
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
//:AcceptHazardContent( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
AcceptHazardContent( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardContent: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptHazardContent: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
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
//:InitSPLD_ForUpdate( VIEW ViewToWindow )

//:// VIEW mSPLDef  REGISTERED AS mSPLDef     retrieved old code (next six lines) 2012.05.14
//:   VIEW mSubProd REGISTERED AS mSubProd
public int 
InitSPLD_ForUpdate( View     ViewToWindow )
{
   zVIEW    mSubProd = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  BASED ON LOD  mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mSubProd, "mSubProd", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSPLDef NAMED "mSPLDef"
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( mSPLDef )
      DropObjectInstance( mSPLDef );
   } 

   //:END

   //:ACTIVATE mSPLDef WHERE mSPLDef.SubregPhysicalLabelDef.ID = mSubProd.SubregPhysicalLabelDef.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubProd, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSPLDef, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSPLDef "mSPLDef"
   SetNameForView( mSPLDef, "mSPLDef", null, zLEVEL_TASK );

   //:// Build Display Structure.
   //:IF mSPLDef.SPLD_Template EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSPLDef, "SPLD_Template" );
   if ( lTempInteger_1 == 0 )
   { 
      //:BuildDisplayStructure( mSPLDef )
      {
       mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
       m_mSPLDef_Object.omSPLDef_BuildDisplayStructure( mSPLDef );
       // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:SET CURSOR FIRST mSPLDef.SubregPhysicalLabelDef
   RESULT = SetCursorFirstEntity( mSPLDef, "SubregPhysicalLabelDef", "" );
   //:SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSPLD", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GeneratePDF( ViewToWindow )
   GeneratePDF( ViewToWindow );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GeneratePDF( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
GeneratePDF( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// mSPLDef.SPLD_TemplateBlock.ExternalGraphicFileName = "KennelSolHeader.jpg"
   //:// SET CURSOR NEXT mSPLDef.SPLD_TemplateBlock
   //:// mSPLDef.SPLD_TemplateBlock.ShowBox = "Y"
   //:// FOR EACH mSPLDef.SPLD_TemplatePanel
   //://    mSPLDef.SPLD_TemplatePanel.SizeX = 620
   //:// END

   //:// FOR EACH mSPLDef.SPLD_TemplateSection WITHIN mSPLDef.SubregPhysicalLabelDef
   //://    IF mSPLDef.SPLD_TemplateSection.TSectionType = "Marketing" OR
   //://       mSPLDef.SPLD_TemplateSection.TSectionType = "DirectionsForUse"
   //://
   //://       mSPLDef.SPLD_TemplateSection.StatementFormat = "PU"
   //://    END
   //:// END

   //:// Build Display Structure.
   //:GeneratePDF_Label( mSPLDef )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( mSPLDef );
    m_mSPLDef_Object.omSPLDef_GeneratePDF_Label( mSPLDef );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateTemplateStatement( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
UpdateTemplateStatement( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplateStatement: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "UpdateTemplateStatement: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:// Position on the real entity in the hierarchical structure.
   //:// Then, create that entity as temporal and go to the correct window based on type.
   //:IF mSPLDef.DisplayTemplateStatement.EntityType = "Template"
   if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Template" ) == 0 )
   { 

      //:// Panel
      //:SET CURSOR FIRST mSPLDef.SPLD_Template
      //:           WHERE mSPLDef.SPLD_Template.ID = mSPLDef.DisplayTemplateStatement.EntityID
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
             GetIntegerFromAttribute( mi_lTempInteger_0, mSPLDef, "DisplayTemplateStatement", "EntityID" );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );}
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_Template", "ID", lTempInteger_0, "" );
      //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplatePanel" )
      //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Template", "UpdateTemplate: " )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_Template", "UpdateTemplate: " );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplateDetail" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplateDetail" );
      //:RETURN 1
      if(8==8)return( 1 );

      //:ELSE
   } 
   else
   { 
      //:IF mSPLDef.DisplayTemplateStatement.EntityType = "Panel"
      if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Panel" ) == 0 )
      { 

         //:// Panel
         //:SET CURSOR FIRST mSPLDef.SPLD_TemplatePanel
         //:        WHERE mSPLDef.SPLD_TemplatePanel.ID = mSPLDef.DisplayTemplateStatement.EntityID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, mSPLDef, "DisplayTemplateStatement", "EntityID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_TemplatePanel", "ID", lTempInteger_1, "" );
         //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplatePanel" )
         //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplatePanel", "UpdateTemplatePanel: " )
         {
          ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
          m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplatePanel", "UpdateTemplatePanel: " );
          // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
         }
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplatePanelDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplatePanelDetail" );
         //:RETURN 1
         if(8==8)return( 1 );

         //:ELSE
      } 
      else
      { 
         //:IF mSPLDef.DisplayTemplateStatement.EntityType = "Group"
         if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Group" ) == 0 )
         { 

            //:// Group
            //:SET CURSOR FIRST mSPLDef.SPLD_TemplateBlock WITHIN mSPLDef.SPLD_Template
            //:     WHERE mSPLDef.SPLD_TemplateBlock.ID = mSPLDef.DisplayTemplateStatement.EntityID
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                         GetIntegerFromAttribute( mi_lTempInteger_2, mSPLDef, "DisplayTemplateStatement", "EntityID" );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );}
            RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_TemplateBlock", "ID", lTempInteger_2, "SPLD_Template" );
            //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplateBlock" )
            //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateBlock", "UpdateTemplateBlock: " )
            {
             ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
             m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateBlock", "UpdateTemplateBlock: " );
             // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
            }
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplateBlockDetail" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplateBlockDetail" );
            //:RETURN 1
            if(8==8)return( 1 );

            //:ELSE
         } 
         else
         { 
            //:IF mSPLDef.DisplayTemplateStatement.EntityType = "Section"
            if ( CompareAttributeToString( mSPLDef, "DisplayTemplateStatement", "EntityType", "Section" ) == 0 )
            { 

               //:// Section
               //:SET CURSOR FIRST mSPLDef.SPLD_TemplateSection WITHIN mSPLDef.SPLD_Template
               //:  WHERE mSPLDef.SPLD_TemplateSection.ID = mSPLDef.DisplayTemplateStatement.EntityID
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                               GetIntegerFromAttribute( mi_lTempInteger_3, mSPLDef, "DisplayTemplateStatement", "EntityID" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_TemplateSection", "ID", lTempInteger_3, "SPLD_Template" );
               //:// CreateTemporalSubobjectVersion( mSPLDef, "SPLD_TemplateSection" )
               //:CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateSection", "UpdateTemplateSection: " )
               {
                ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
                m_ZGlobalV_Operation.CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_TemplateSection", "UpdateTemplateSection: " );
                // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
               }
               //:SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplateSectionDetail" )
               m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartModalSubwindow, "wSPLD", "TemplateSectionDetail" );
               //:RETURN 1
               if(8==8)return( 1 );

               //:ELSE
            } 
            else
            { 

               //:MessageSend( ViewToWindow, "", "Update Template Statement",
               //:    "Cannot update statement.",
               //:    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( ViewToWindow, "", "Update Template Statement", "Cannot update statement.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:GenerateNewSPLD_Version( VIEW ViewToWindow )

//:   VIEW lSPLDLST   REGISTERED AS lSPLDLST
public int 
GenerateNewSPLD_Version( View     ViewToWindow )
{
   zVIEW    lSPLDLST = new zVIEW( );
   int      RESULT = 0;
   //:VIEW NewSPLD    BASED ON LOD  mSPLDef
   zVIEW    NewSPLD = new zVIEW( );
   //:VIEW SourceSPLD BASED ON LOD  mSPLDef
   zVIEW    SourceSPLD = new zVIEW( );
   //:VIEW SourceSLC  BASED ON LOD  mSubLC
   zVIEW    SourceSLC = new zVIEW( );
   //:VIEW ParentSLC  BASED ON LOD  mSubLC   // This is the parent SLC of the SPLD to be created.
   zVIEW    ParentSLC = new zVIEW( );
   //:VIEW mTempl     BASED ON LOD  mTempl
   zVIEW    mTempl = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );

   //:// Build a new SPLD version from an existing SPLD.

   //:// Activate selected SPLD, which will be source of new SPLD.
   //:ACTIVATE SourceSPLD WHERE SourceSPLD.SubregPhysicalLabelDef.ID = lSPLDLST.SubregPhysicalLabelDef.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSPLDLST, "SubregPhysicalLabelDef", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( SourceSPLD, "mSPLDef", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW SourceSPLD "SourceSPLD"
   SetNameForView( SourceSPLD, "SourceSPLD", null, zLEVEL_TASK );

   //:// Activate Source SLC of source SPLD just activated.
   //:ACTIVATE SourceSLC WHERE SourceSLC.SubregLabelContent.ID = SourceSPLD.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, SourceSPLD, "SubregLabelContent", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( SourceSLC, "mSubLC", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW SourceSLC "SourceSLC"
   SetNameForView( SourceSLC, "SourceSLC", null, zLEVEL_TASK );

   //:// Activate the next version of the Source SLC, which will become the Parent SLC of new SPLD.
   //:ACTIVATE ParentSLC WHERE ParentSLC.SubregLabelContent.ID = SourceSLC.SN_SubregLabelContent.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, SourceSLC, "SN_SubregLabelContent", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_2, lTempInteger_2 );
   RESULT = ActivateObjectInstance( ParentSLC, "mSubLC", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:NAME VIEW ParentSLC "ParentSLC"
   SetNameForView( ParentSLC, "ParentSLC", null, zLEVEL_TASK );

   //:// Activate new empty SPLD.
   //:ACTIVATE NewSPLD EMPTY
   RESULT = ActivateEmptyObjectInstance( NewSPLD, "mSPLDef", ViewToWindow, zSINGLE );
   //:NAME VIEW NewSPLD "NewSPLD"
   SetNameForView( NewSPLD, "NewSPLD", null, zLEVEL_TASK );
   //:CREATE ENTITY NewSPLD.SubregPhysicalLabelDef
   RESULT = CreateEntity( NewSPLD, "SubregPhysicalLabelDef", zPOS_AFTER );

   //:// Build the main part of the SPLD from the Source SPLD.
   //:BuildSPLD_FromSPLD( NewSPLD, SourceSPLD, ParentSLC )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( NewSPLD );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_FromSPLD( NewSPLD, SourceSPLD, ParentSLC );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:// Add the Template subobject to the SPLD from the original SPLD. We will activate mTempl so we
   //:// can pass the Template ID into the operation.
   //:ACTIVATE mTempl RootOnly WHERE mTempl.Template.Name = SourceSPLD.SPLD_Template.Name
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, SourceSPLD, "SPLD_Template", "Name" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_17( ViewToWindow, vTempViewVar_3, szTempString_0 );
   RESULT = ActivateObjectInstance( mTempl, "mTempl", ViewToWindow, vTempViewVar_3, zACTIVATE_ROOTONLY );
   DropView( vTempViewVar_3 );
   //:NAME VIEW mTempl "mTempl"
   SetNameForView( mTempl, "mTempl", null, zLEVEL_TASK );
   //:BuildSPLD_Template( NewSPLD, mTempl.Template.ID )
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, mTempl, "Template", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( NewSPLD );
    m_mSPLDef_Object.omSPLDef_BuildSPLD_Template( NewSPLD, lTempInteger_3 );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:COMMIT NewSPLD
   RESULT = CommitObjectInstance( NewSPLD );

   //:// Build Display NewSPLD.
   //:BuildDisplayStructure( NewSPLD )
   {
    mSPLDef_Object m_mSPLDef_Object = new mSPLDef_Object( NewSPLD );
    m_mSPLDef_Object.omSPLDef_BuildDisplayStructure( NewSPLD );
    // m_mSPLDef_Object = null;  // permit gc  (unnecessary)
   }

   //:// Reset cursor to beginning for all entties.
   //:SET CURSOR FIRST NewSPLD.SubregPhysicalLabelDef
   RESULT = SetCursorFirstEntity( NewSPLD, "SubregPhysicalLabelDef", "" );

   //:// Rebuild SPLD list.
   //:GET VIEW lSPLDLST NAMED "lSPLDLST"
   RESULT = GetViewByName( lSPLDLST, "lSPLDLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( lSPLDLST )
      DropObjectInstance( lSPLDLST );
   } 

   //:END

   //:ACTIVATE lSPLDLST Multiple
   RESULT = ActivateObjectInstance( lSPLDLST, "lSPLDLST", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lSPLDLST "lSPLDLST"
   SetNameForView( lSPLDLST, "lSPLDLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:StartBrowserWithHTML( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
StartBrowserWithHTML( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 512 ) szReturnFileName
   String   szReturnFileName = null;
   //:STRING ( 512 ) szFileName
   String   szFileName = null;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:SysMakeWebFileName( szFileName, ViewToWindow, 0 )
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       m_KZOEP1AA.SysMakeWebFileName( sb_szFileName, ViewToWindow, 0 );
   szFileName = sb_szFileName.toString( );}
   //:StartBrowserWindowWithURL( ViewToWindow, szReturnFileName, 512,
   //:                           "", szFileName, 0 )
   {StringBuilder sb_szReturnFileName;
   if ( szReturnFileName == null )
      sb_szReturnFileName = new StringBuilder( 32 );
   else
      sb_szReturnFileName = new StringBuilder( szReturnFileName );
       m_ZDRVROPR.StartBrowserWindowWithURL( ViewToWindow, sb_szReturnFileName, 512, "", szFileName, 0 );
   szReturnFileName = sb_szReturnFileName.toString( );}
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveShowNextDirectionsUseSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveShowNextDirectionsUseSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = NextDirectionsUseSect( ViewToWindow )
   nRC = NextDirectionsUseSect( ViewToWindow );
   //:RETURN nRC
   return( nRC );
// // VIEW mSPLDef  REGISTERED AS mSPLDef
// // SHORT nRC
// //
// // nRC = AcceptDirectionsUseSect( ViewToWindow )
// // IF nRC = 0
// //    SET CURSOR NEXT mSPLDef.SPLD_DirectionsForUseStatement
// //    IF RESULT = 0
// //       InitDirectionsUseSectForInsert( ViewToWindow )
// //    ELSE
// //       MessageSend( ViewToWindow, "", "Save And Show Next DirectionsForUse Section",
// //                    "There is no next Directions For Use section.",
// //                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //       SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //       RETURN 2
// //    END
// // ELSE
// //    MessageSend( ViewToWindow, "", "Save And Show Next DirectionsForUse Section",
// //                 "Error saving DirectionsUse section.",
// //                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //    RETURN 2
// // END
// END
} 


//:DIALOG OPERATION
//:SaveShowPrevDirectionsUseSect( VIEW ViewToWindow )

//:   SHORT nRC
public int 
SaveShowPrevDirectionsUseSect( View     ViewToWindow )
{
   int      nRC = 0;


   //:nRC = NextDirectionsUseSect( ViewToWindow )
   nRC = NextDirectionsUseSect( ViewToWindow );
   //:RETURN nRC
   return( nRC );
// // VIEW mSPLDef  REGISTERED AS mSPLDef
// // SHORT nRC
// //
// // nRC = AcceptDirectionsUseSect( ViewToWindow )
// // IF nRC = 0
// //    SET CURSOR PREVIOUS mSPLDef.SPLD_DirectionsForUseStatement
// //    IF RESULT = 0
// //       InitDirectionsUseSectForInsert( ViewToWindow )
// //    ELSE
// //       MessageSend( ViewToWindow, "", "Save And Show Previous DirectionsForUse Section",
// //                    "There is no previous DirectionsForUse section.",
// //                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //       SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //       RETURN 2
// //    END
// // ELSE
// //    MessageSend( ViewToWindow, "", "Save And Show Previous DirectionsForUse Section",
// //                 "Error saving DirectionsForUse section.",
// //                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
// //    RETURN 2
// // END
// END
} 


//:DIALOG OPERATION
//:NextDirectionsUseSect( VIEW ViewToWindow )

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
NextDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
   lControl = zPOS_NEXT + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mSPLDef, "SPLD_DirectionsForUseSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mSPLDef, "SPLD_DirectionsForUseSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Next DirectionsUse Section",
      //:             "There is not a next marketing section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Next DirectionsUse Section", "There is not a next marketing section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mSPLDef.SPLD_DirectionsForUseSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SPLD_DirectionsForUseSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptDirectionsUseSect( ViewToWindow )
      nRC = AcceptDirectionsUseSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mSPLDef.SPLD_DirectionsForUseSection
      //:    WHERE mSPLDef.SPLD_DirectionsForUseSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", lID, "" );
      //:SET CURSOR NEXT mSPLDef.SPLD_DirectionsForUseSection
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseSection", "NextDirectionsUseSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Next DirectionsUse Section",
      //://              "Error saving Directions for Use section.",
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
PreviousDirectionsUseSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_PREV + zTEST_CSR_RESULT
   lControl = zPOS_PREV + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mSPLDef, "SPLD_DirectionsForUseSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mSPLDef, "SPLD_DirectionsForUseSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Previous DirectionsUse Section",
      //:             "There is not a previous Directions for Use section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Previous DirectionsUse Section", "There is not a previous Directions for Use section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mSPLDef.SPLD_DirectionsForUseSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SPLD_DirectionsForUseSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptDirectionsUseSect( ViewToWindow )
      nRC = AcceptDirectionsUseSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mSPLDef.SPLD_DirectionsForUseSection
      //:    WHERE mSPLDef.SPLD_DirectionsForUseSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_DirectionsForUseSection", "ID", lID, "" );
      //:SET CURSOR PREVIOUS mSPLDef.SPLD_DirectionsForUseSection
      RESULT = SetCursorPrevEntity( mSPLDef, "SPLD_DirectionsForUseSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_DirectionsForUseSection", "PreviousDirectionsUseSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Previous DirectionsUse Section",
      //://              "Error saving Directions for Use section.",
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
PreviousMarketingSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_PREV + zTEST_CSR_RESULT
   lControl = zPOS_PREV + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mSPLDef, "SPLD_MarketingSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mSPLDef, "SPLD_MarketingSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Previous Marketing Section",
      //:             "There is not a previous Marketing section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Previous Marketing Section", "There is not a previous Marketing section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mSPLDef.SPLD_MarketingSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SPLD_MarketingSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptMarketingSect( ViewToWindow )
      nRC = AcceptMarketingSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mSPLDef.SPLD_MarketingSection
      //:    WHERE mSPLDef.SPLD_MarketingSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_MarketingSection", "ID", lID, "" );
      //:SET CURSOR PREVIOUS mSPLDef.SPLD_MarketingSection
      RESULT = SetCursorPrevEntity( mSPLDef, "SPLD_MarketingSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingSection", "PreviousMarketingSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Previous Marketing Section",
      //://              "Error saving Marketing section.",
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

//:   VIEW mSPLDef  REGISTERED AS mSPLDef
public int 
NextMarketingSect( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 ) szVoid
   String   szVoid = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:lControl = zPOS_NEXT + zTEST_CSR_RESULT
   lControl = zPOS_NEXT + zTEST_CSR_RESULT;
   //:nRC = SetEntityCursor( mSPLDef, "SPLD_MarketingSection", "", lControl,
   //:                       szVoid, "", "", 0, "", "" )
   nRC = SetEntityCursor( mSPLDef, "SPLD_MarketingSection", "", lControl, szVoid, "", "", 0, "", "" );
   //:IF nRC < zCURSOR_SET
   if ( nRC < zCURSOR_SET )
   { 
      //:MessageSend( ViewToWindow, "", "Next Marketing Section",
      //:             "There is not a next Marketing section.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Next Marketing Section", "There is not a next Marketing section.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:nRC = 2
      nRC = 2;
      //:ELSE
   } 
   else
   { 
      //:lID = mSPLDef.SPLD_MarketingSection.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSPLDef, "SPLD_MarketingSection", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = AcceptMarketingSect( ViewToWindow )
      nRC = AcceptMarketingSect( ViewToWindow );
   } 

   //:END

   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:SET CURSOR FIRST mSPLDef.SPLD_MarketingSection
      //:    WHERE mSPLDef.SPLD_MarketingSection.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mSPLDef, "SPLD_MarketingSection", "ID", lID, "" );
      //:SET CURSOR NEXT mSPLDef.SPLD_MarketingSection
      RESULT = SetCursorNextEntity( mSPLDef, "SPLD_MarketingSection", "" );
      //:// CreateCurrentTemporalVersion( ViewToWindow, 0, "mSPLDef", "SPLD_MarketingSection", "NextMarketingSect: " )
      //:ELSE
   } 
   else
   { 
      //:// MessageSend( ViewToWindow, "", "Next Marketing Section",
      //://              "Error saving Marketing section.",
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
//:AcceptTemplate( VIEW ViewToWindow )

//:   VIEW mSPLDef REGISTERED AS mSPLDef
public int 
AcceptTemplate( View     ViewToWindow )
{
   zVIEW    mSPLDef = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:// AcceptSubobject( mSPLDef, "SPLD_Template" )
   //:AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplate: " )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, FALSE, "AcceptTemplate: " );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:COMMIT mSPLDef
   RESULT = CommitObjectInstance( mSPLDef );
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_1( View     vSubtask,
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
o_fnLocalBuildQual_2( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregPhysicalLabelDef" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_7( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_9( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lID )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregPhysicalLabelDef" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_10( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "2" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 



}
