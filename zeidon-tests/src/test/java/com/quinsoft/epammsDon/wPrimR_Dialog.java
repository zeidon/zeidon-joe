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
import com.quinsoft.epamms.ZGlobal1_Operation;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class wPrimR_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public wPrimR_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
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


private int 
o_fnLocalBuildQual_1( View     vSubtask,
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_10( View     vSubtask,
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
o_fnLocalBuildQual_11( View     vSubtask,
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
o_fnLocalBuildQual_12( View     vSubtask,
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
o_fnLocalBuildQual_13( View     vSubtask,
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
o_fnLocalBuildQual_14( View     vSubtask,
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
o_fnLocalBuildQual_15( View     vSubtask,
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


//:DIALOG OPERATION
//:InitMasterProductForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterProductForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:// VIEW mMasLC REGISTERED AS mMasLC

   //:// TraceLineS( "", "" )
   //:// TraceLineS( "InitMasterProductForDelete", "" )
   //:// DisplayObjectInstance( mMasLC, "", "" )

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteMasterProduct( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
CancelDeleteMasterProduct( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteMasterProduct( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
DeleteMasterProduct( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC BASED ON LOD    mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:lID = mPrimReg.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mPrimReg, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitListMasterProducts( ViewToWindow )
      InitListMasterProducts( ViewToWindow );
      //:GET VIEW mPrimReg NAMED "mPrimReg"
      RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mPrimReg.MasterLabelContent
      //:    WHERE mPrimReg.MasterLabelContent.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lID, "" );

      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitListPrimaryRegistrants( VIEW ViewToWindow )

//:   VIEW qPrimReg BASED ON LOD  qPrimReg
public int 
InitListPrimaryRegistrants( View     ViewToWindow )
{
   zVIEW    qPrimReg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW qPrimReg NAMED "qPrimReg"
   RESULT = GetViewByName( qPrimReg, "qPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF  qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
   } 

   //:END

   //:// Activate all primary registrants at the root level.
   //:ACTIVATE qPrimReg ROOTONLYMULTIPLE
   RESULT = ActivateObjectInstance( qPrimReg, "qPrimReg", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW qPrimReg "qPrimReg"
   SetNameForView( qPrimReg, "qPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdatePrimaryRegistrant( VIEW ViewToWindow )

public int 
UpdatePrimaryRegistrant( View     ViewToWindow )
{

   return( 0 );
//    // Don't need to do anything except have this operation to cause
//    // positioning code to be done in the JSP.
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
   //:CREATE ENTITY mPrimReg.PhysicalAddress
   RESULT = CreateEntity( mPrimReg, "PhysicalAddress", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.MailingAddress
   RESULT = CreateEntity( mPrimReg, "MailingAddress", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.Employee
   RESULT = CreateEntity( mPrimReg, "Employee", zPOS_AFTER );
   //:CREATE ENTITY mPrimReg.User
   RESULT = CreateEntity( mPrimReg, "User", zPOS_AFTER );

   //:mPrimReg.PhysicalAddress.Country = "USA"
   SetAttributeFromString( mPrimReg, "PhysicalAddress", "Country", "USA" );
   //:mPrimReg.MailingAddress.Country = "USA"
   SetAttributeFromString( mPrimReg, "MailingAddress", "Country", "USA" );
   //:wWebXfer.Root.SameAs = "Y"
   SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );

   //:CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" )
   CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" );
   //:// CreateTemporalSubobjectVersion( mPrimReg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "Person" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "User" )

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrimaryRegistrantForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrimaryRegistrantForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF mPrimReg != 0
   if ( getView( mPrimReg ) != null )
   { 
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
   } 

   //:END

   //:// If this is the first time into the system, this is the administrator.
   //:// Otherwise, just create a new primary registrant.
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:IF mPrimReg.PrimaryRegistrant DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mPrimReg, "PrimaryRegistrant" );
   if ( lTempInteger_1 != 0 )
   { 

      //:CREATE ENTITY mPrimReg.PrimaryRegistrant
      RESULT = CreateEntity( mPrimReg, "PrimaryRegistrant", zPOS_AFTER );
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

      //:ELSE
   } 
   else
   { 
      //:wWebXfer.Root.SameAs = ""
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "" );
   } 

   //:END

   //:IF mPrimReg.PhysicalAddress DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( mPrimReg, "PhysicalAddress" );
   if ( lTempInteger_2 != 0 )
   { 
      //:CREATE ENTITY mPrimReg.PhysicalAddress
      RESULT = CreateEntity( mPrimReg, "PhysicalAddress", zPOS_AFTER );
      //:mPrimReg.PhysicalAddress.Country = "USA"
      SetAttributeFromString( mPrimReg, "PhysicalAddress", "Country", "USA" );
   } 

   //:END

   //:IF mPrimReg.MailingAddress DOES NOT EXIST
   lTempInteger_3 = CheckExistenceOfEntity( mPrimReg, "MailingAddress" );
   if ( lTempInteger_3 != 0 )
   { 
      //:CREATE ENTITY mPrimReg.MailingAddress
      RESULT = CreateEntity( mPrimReg, "MailingAddress", zPOS_AFTER );
      //:lID = mPrimReg.MailingAddress.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mPrimReg, "MailingAddress", "ID" );
      lID = mi_lID.intValue( );}
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mPrimReg, "MailingAddress",
      //:                             mPrimReg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mPrimReg, "MailingAddress", mPrimReg, "PhysicalAddress", zSET_NOTNULL );
      //:lID = mPrimReg.MailingAddress.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mPrimReg, "MailingAddress", "ID" );
      lID = mi_lID.intValue( );}
   } 

   //:// mPrimReg.MailingAddress.ID = lID
   //:END

   //:IF mPrimReg.ContactPerson DOES NOT EXIST
   lTempInteger_4 = CheckExistenceOfEntity( mPrimReg, "ContactPerson" );
   if ( lTempInteger_4 != 0 )
   { 
      //:CREATE ENTITY mPrimReg.ContactPerson
      RESULT = CreateEntity( mPrimReg, "ContactPerson", zPOS_AFTER );
   } 

   //:END

   //:wWebXfer.Root.AttemptLoginName = mPrimReg.Organization.LoginName
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptLoginName", mPrimReg, "Organization", "LoginName" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" )
   CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" );
   //:// CreateTemporalSubobjectVersion( mPrimReg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "ContactPerson" )

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
AcceptNewPrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:STRING ( 256  ) szName
   String   szName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lLth
   int      lLth = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );


   //:// Ensure registrant name is not blank.
   //:szName = mPrimReg.Organization.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
       GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 257, mPrimReg, "Organization", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szName = sb_szName.toString( );}
   //:lLth = zGetStringLen( szName )
   lLth = zGetStringLen( szName );
   //:TraceLineI( "Registrant Name Length: ", lLth )
   TraceLineI( "Registrant Name Length: ", lLth );
   //:IF lLth < 1
   if ( lLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The registrant name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The registrant name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Ensure user name is not blank.
   //:szName = mPrimReg.User.UserName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
       GetVariableFromAttribute( sb_szName, mi_lTempInteger_1, 'S', 257, mPrimReg, "User", "UserName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szName = sb_szName.toString( );}
   //:lLth = zGetStringLen( szName )
   lLth = zGetStringLen( szName );
   //:TraceLineI( "Registrant User Name Length: ", lLth )
   TraceLineI( "Registrant User Name Length: ", lLth );
   //:IF lLth < 1
   if ( lLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The User Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The User Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Validate password.
   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_2, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:szConfirmPassword = wWebXfer.Root.ConfirmPassword
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_3, 'S', 129, wWebXfer, "Root", "ConfirmPassword", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szConfirmPassword = sb_szConfirmPassword.toString( );}

   //:// 1: Ensure attempted password matches confirm password.
   //:IF szAttemptPassword != szConfirmPassword
   if ( ZeidonStringCompare( szAttemptPassword, 1, 0, szConfirmPassword, 1, 0, 129 ) != 0 )
   { 
      //:// TraceLineS( szAttemptPassword, szConfirmPassword )
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The new password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The new password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// 2: Ensure new password is at least 8 characters long.
   //:lLth = zGetStringLen( szConfirmPassword )
   lLth = zGetStringLen( szConfirmPassword );
   //:TraceLineI( "Password Length: ", lLth )
   TraceLineI( "Password Length: ", lLth );
   //:IF lLth < 8
   if ( lLth < 8 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( mPrimReg, "User", "UserPassword", szVerifyPassword, "Password" )
   //:mPrimReg.User.UserPassword = szConfirmPassword
   SetAttributeFromString( mPrimReg, "User", "UserPassword", szConfirmPassword );

   //:// AcceptSubobject( mPrimReg, "User" )
   //:// AcceptSubobject( mPrimReg, "Person" )
   //:// AcceptSubobject( mPrimReg, "PhysicalAddress" )
   //:// AcceptSubobject( mPrimReg, "MailingAddress" )
   //:AcceptSubobject( mPrimReg, "PrimaryRegistrant" )
   AcceptSubobject( mPrimReg, "PrimaryRegistrant" );
   //:IF wWebXfer.Root.SameAs = "Y"
   if ( CompareAttributeToString( wWebXfer, "Root", "SameAs", "Y" ) == 0 )
   { 
      //:DELETE ENTITY mPrimReg.MailingAddress
      RESULT = DeleteEntity( mPrimReg, "MailingAddress", zPOS_NEXT );
   } 

   //:END

   //:Commit mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdatePrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
AcceptUpdatePrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:STRING (  32  ) szName
   String   szName = null;
   //:INTEGER         lLth
   int      lLth = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:szName = mPrimReg.Organization.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szName;
   if ( szName == null )
      sb_szName = new StringBuilder( 32 );
   else
      sb_szName = new StringBuilder( szName );
       GetVariableFromAttribute( sb_szName, mi_lTempInteger_0, 'S', 33, mPrimReg, "Organization", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szName = sb_szName.toString( );}

   //:// Ensure registrant name is not blank.
   //:lLth = zGetStringLen( szName )
   lLth = zGetStringLen( szName );
   //:TraceLineI( "Registrant Name Length: ", lLth )
   TraceLineI( "Registrant Name Length: ", lLth );
   //:IF lLth < 1
   if ( lLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The registrant name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The registrant name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// AcceptSubobject( mPrimReg, "User" )
   //:// AcceptSubobject( mPrimReg, "Person" )
   //:// AcceptSubobject( mPrimReg, "PhysicalAddress" )
   //:// AcceptSubobject( mPrimReg, "MailingAddress" )
   //:AcceptSubobject( mPrimReg, "PrimaryRegistrant" )
   AcceptSubobject( mPrimReg, "PrimaryRegistrant" );
   //:IF wWebXfer.Root.SameAs = "Y"
   if ( CompareAttributeToString( wWebXfer, "Root", "SameAs", "Y" ) == 0 )
   { 
      //:DELETE ENTITY mPrimReg.MailingAddress
      RESULT = DeleteEntity( mPrimReg, "MailingAddress", zPOS_NEXT );
   } 

   //:END

   //:Commit mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
CancelNewPrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mPrimReg, "User" )
   //:// CancelSubobject( mPrimReg, "Person" )
   //:// CancelSubobject( mPrimReg, "PhysicalAddress" )
   //:// CancelSubobject( mPrimReg, "MailingAddress" )
   //:CancelSubobject( mPrimReg, "PrimaryRegistrant" )
   CancelSubobject( mPrimReg, "PrimaryRegistrant" );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdatePrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
CancelUpdatePrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mPrimReg, "User" )
   //:// CancelSubobject( mPrimReg, "Person" )
   //:// CancelSubobject( mPrimReg, "PhysicalAddress" )
   //:// CancelSubobject( mPrimReg, "MailingAddress" )
   //:CancelSubobject( mPrimReg, "PrimaryRegistrant" )
   CancelSubobject( mPrimReg, "PrimaryRegistrant" );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterProduct( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
ConfirmDeleteMasterProduct( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mPrimReg.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mPrimReg, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
   o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_0, lID );
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
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
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
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mTempReg BASED ON LOD  mPrimReg
   zVIEW    mTempReg = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempReg, mPrimReg )
   CreateViewFromView( mTempReg, mPrimReg );
   //:lMove = 1
   lMove = 1;
   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR NEXT mTempReg.MasterLabelContent
      RESULT = SetCursorNextEntity( mTempReg, "MasterLabelContent", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempReg, "MasterLabelContent",
   //:               mPrimReg, "MasterLabelContent",
   //:               zPOS_NEXT, zREPOS_NEXT )
   MoveSubobject( mTempReg, "MasterLabelContent", mPrimReg, "MasterLabelContent", zPOS_NEXT, zREPOS_NEXT );
   //:DropView( mTempReg )
   DropView( mTempReg );

   //:// We now accept the Master Product to maintain order!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );

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
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mTempReg BASED ON LOD  mPrimReg
   zVIEW    mTempReg = new zVIEW( );
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:CreateViewFromView( mTempReg, mPrimReg )
   CreateViewFromView( mTempReg, mPrimReg );
   //:lMove = 1
   lMove = 1;
   //:LOOP WHILE lMove > 0
   while ( lMove > 0 )
   { 
      //:SET CURSOR PREVIOUS mTempReg.MasterLabelContent
      RESULT = SetCursorPrevEntity( mTempReg, "MasterLabelContent", "" );
      //:lMove = lMove - 1
      lMove = lMove - 1;
   } 

   //:END

   //:MoveSubobject( mTempReg, "MasterLabelContent",
   //:               mPrimReg, "MasterLabelContent",
   //:               zPOS_PREV, zREPOS_PREV )
   MoveSubobject( mTempReg, "MasterLabelContent", mPrimReg, "MasterLabelContent", zPOS_PREV, zREPOS_PREV );
   //:DropView( mTempReg )
   DropView( mTempReg );

   //:// We now accept the Master Product to maintain order!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitDesignMasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitDesignMasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC BASED ON LOD    mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:INTEGER hMemory
   int      hMemory = 0;
   //:STRING ( 32 ) szLDD
   String   szLDD = null;
   //:STRING ( 64 ) szContent
   String   szContent = null;
   //:STRING ( 96 ) szSection
   String   szSection = null;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing MasterLabelContent entity.
   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mPrimReg.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mPrimReg, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" )
   CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" );

   //:hMemory = CreateMemoryHandle( 32000 )
   hMemory = m_ZDRVROPR.CreateMemoryHandle( 32000 );
   //:FOR EACH mMasLC.MasterLabelContent
   RESULT = SetCursorFirstEntity( mMasLC, "MasterLabelContent", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szLDD = mMasLC.MasterLabelContent.ID
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szLDD;
      if ( szLDD == null )
         sb_szLDD = new StringBuilder( 32 );
      else
         sb_szLDD = new StringBuilder( szLDD );
             GetVariableFromAttribute( sb_szLDD, mi_lTempInteger_1, 'S', 33, mMasLC, "MasterLabelContent", "ID", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szLDD = sb_szLDD.toString( );}
      //:AddStringToMemory( hMemory, "<div zid=", 0 )
      m_ZDRVROPR.AddStringToMemory( hMemory, "<div zid=", 0 );
      //:AddStringToMemory( hMemory, szLDD, 2 )
      m_ZDRVROPR.AddStringToMemory( hMemory, szLDD, 2 );
      //:AddStringToMemory( hMemory, " style=", 0 )
      m_ZDRVROPR.AddStringToMemory( hMemory, " style=", 0 );
      //:AddStringToMemory( hMemory, "border:0px; margin:0px;", 2 )
      m_ZDRVROPR.AddStringToMemory( hMemory, "border:0px; margin:0px;", 2 );
      //:AddStringToMemory( hMemory, ">", 4 )
      m_ZDRVROPR.AddStringToMemory( hMemory, ">", 4 );

      //:FOR EACH mMasLC.MasterLabelContent
      RESULT = SetCursorFirstEntity( mMasLC, "MasterLabelContent", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szContent = szLDD + "."
          {StringBuilder sb_szContent;
         if ( szContent == null )
            sb_szContent = new StringBuilder( 32 );
         else
            sb_szContent = new StringBuilder( szContent );
                  ZeidonStringCopy( sb_szContent, 1, 0, szLDD, 1, 0, 65 );
         szContent = sb_szContent.toString( );}
          {StringBuilder sb_szContent;
         if ( szContent == null )
            sb_szContent = new StringBuilder( 32 );
         else
            sb_szContent = new StringBuilder( szContent );
                  ZeidonStringConcat( sb_szContent, 1, 0, ".", 1, 0, 65 );
         szContent = sb_szContent.toString( );}
         //:szContent = szContent + mMasLC.MasterLabelContent.ID
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 11, mMasLC, "MasterLabelContent", "ID", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szContent;
         if ( szContent == null )
            sb_szContent = new StringBuilder( 32 );
         else
            sb_szContent = new StringBuilder( szContent );
                  ZeidonStringConcat( sb_szContent, 1, 0, szTempString_0, 1, 0, 65 );
         szContent = sb_szContent.toString( );}
         //:AddStringToMemory( hMemory, "<div zid=", 0 )
         m_ZDRVROPR.AddStringToMemory( hMemory, "<div zid=", 0 );
         //:AddStringToMemory( hMemory, szContent, 2 )
         m_ZDRVROPR.AddStringToMemory( hMemory, szContent, 2 );
         //:AddStringToMemory( hMemory, " style=", 0 )
         m_ZDRVROPR.AddStringToMemory( hMemory, " style=", 0 );
         //:AddStringToMemory( hMemory, "border:0px; margin:0px;", 2 )
         m_ZDRVROPR.AddStringToMemory( hMemory, "border:0px; margin:0px;", 2 );
         //:AddStringToMemory( hMemory, ">", 4 )
         m_ZDRVROPR.AddStringToMemory( hMemory, ">", 4 );

         //:// FOR EACH mMasLC.MasterProductSection
         //://    szSection = szContent + "."
         //://    szSection = szSection + mMasLC.MasterProductSection.ID
         //://    AddStringToMemory( hMemory, "<div zid=", 0 )
         //://    AddStringToMemory( hMemory, szSection, 2 )
         //://    AddStringToMemory( hMemory, " style=", 0 )
         //://    AddStringToMemory( hMemory, "border:0px; margin:0px;", 2 )
         //://    AddStringToMemory( hMemory, ">", 4 )

         //://    AddAttributeToMemory( hMemory, mMasLC, "MasterProductSection", "SectionText", 0 )
         //://    AddStringToMemory( hMemory, "</div>", 4 )
         //:// END

         //:AddStringToMemory( hMemory, "</div>", 4 )
         m_ZDRVROPR.AddStringToMemory( hMemory, "</div>", 4 );
         RESULT = SetCursorNextEntity( mMasLC, "MasterLabelContent", "" );
      } 

      //:END

      //:AddStringToMemory( hMemory, "</div>", 4 )
      m_ZDRVROPR.AddStringToMemory( hMemory, "</div>", 4 );
      RESULT = SetCursorNextEntity( mMasLC, "MasterLabelContent", "" );
   } 

   //:END

   //:SetMemoryToAttribute( hMemory, wWebXfer, "Root", "SplitHTML_Before" )
   m_ZDRVROPR.SetMemoryToAttribute( hMemory, wWebXfer, "Root", "SplitHTML_Before" );
   //:DeleteMemoryHandle( hMemory )
   m_ZDRVROPR.DeleteMemoryHandle( hMemory );

   //:ParseHTML_AttrToZeidonOI( wWebXfer, "Root", "SplitHTML_Before",
   //:                          wWebXfer, "Div", "zid", "Tag", "Name", "Value", "Level", "TagYN", "UsageType",
   //:                          "Attribute", "Name", "Value", "Delimiter" )
   m_ZDRVROPR.ParseHTML_AttrToZeidonOI( wWebXfer, "Root", "SplitHTML_Before", wWebXfer, "Div", "zid", "Tag", "Name", "Value", "Level", "TagYN", "UsageType", "Attribute", "Name", "Value", "Delimiter" );
   //:ConstructHTML_AttrFromZeidonOI( wWebXfer, "Root", "wHTML",
   //:                                wWebXfer, "Div", "zid", "Tag", "Name", "Value", "Level", "TagYN", "UsageType",
   //:                                "Attribute", "Name", "Value", "Delimiter" )
   try
   {
       m_ZDRVROPR.ConstructHTML_AttrFromZeidonOI( wWebXfer, "Root", "wHTML", wWebXfer, "Div", "zid", "Tag", "Name", "Value", "Level", "TagYN", "UsageType", "Attribute", "Name", "Value", "Delimiter" );
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Clean up work entities.
   //:// FOR EACH wWebXfer.Div
   //://    DELETE ENTITY wWebXfer.Div NONE
   //:// END

   //:// wWebXfer.Root.SplitHTML_Before = ""
   //:// wWebXfer.Root.wHTML = ""
   //:// wWebXfer.Root.SplitHTML_After = ""
   //:// FindLinks( mMasLC, "MasterProductSection", "SectionText" );

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
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
   //:INTEGER lProductNameLth
   int      lProductNameLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:// Ensure label name is not blank and is unique.
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
   //:TraceLineS( "Product Product Name: ", szProductName )
   TraceLineS( "Product Product Name: ", szProductName );
   //:TraceLineI( "Product Product Length: ", lProductNameLth )
   TraceLineI( "Product Product Length: ", lProductNameLth );
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

      //:IF mMasProd.MasterProduct.Name != szProductName
      if ( CompareAttributeToString( mMasProd, "MasterProduct", "Name", szProductName ) != 0 )
      { 

         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mMasProd, "MasterProduct", "ProductName",
         //:                    lControl, szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_1 = SetEntityCursor( mMasProd, "MasterProduct", "ProductName", lControl, szProductName, "", "", 0, "", "" );
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

         //:mMasProd.MasterProduct.Name = szProductName
         SetAttributeFromString( mMasProd, "MasterProduct", "Name", szProductName );
      } 

      //:END
   } 

   //:END

   //:IF mMasProd.MasterLabelContent EXISTS                 // for the case when the Content
   lTempInteger_2 = CheckExistenceOfEntity( mMasProd, "MasterLabelContent" );
   if ( lTempInteger_2 == 0 )
   { 
      //:AcceptSubobject( mMasProd, "MasterLabelContent" )  // is versioned, but not the Product
      AcceptSubobject( mMasProd, "MasterLabelContent" );
   } 

   //:END

   //:AcceptSubobject( mMasProd, "MasterProduct" )
   AcceptSubobject( mMasProd, "MasterProduct" );
   //:Commit mMasProd
   RESULT = CommitObjectInstance( mMasProd );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:IF mPrimReg != 0
   if ( getView( mPrimReg ) != null )
   { 
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
   } 

   //:END
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
   //:INTEGER lProductNameLth
   int      lProductNameLth = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:INTEGER ViewCluster
   int      ViewCluster = 0;
   //:INTEGER Ignore
   int      Ignore = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:// Ensure label name is not blank and is unique.
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
   //:TraceLineS( "Product Product Name: ", szProductName )
   TraceLineS( "Product Product Name: ", szProductName );
   //:TraceLineI( "Product Product Length: ", lProductNameLth )
   TraceLineI( "Product Product Length: ", lProductNameLth );
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
      //:IF SetEntityCursor( mMasProd, "MasterProduct", "Name", lControl,
      //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mMasProd, "MasterProduct", "Name", lControl, szProductName, "", "", 0, "", "" );
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

   //:mMasProd.MasterProduct.Name = szProductName
   SetAttributeFromString( mMasProd, "MasterProduct", "Name", szProductName );
   //:AcceptSubobject( mMasProd, "MasterProduct" )
   AcceptSubobject( mMasProd, "MasterProduct" );
   //:// IncludeSubobjectFromSubobject( mPrimReg, "MasterProduct",
   //://                                mMasProd, "MasterProduct", zPOS_BEFORE )

   //:// Commit mMasProd
   //:// Commit mPrimReg
   //:CreateViewCluster( ViewToWindow, ViewCluster )
   {MutableInt mi_ViewCluster = new MutableInt( ViewCluster );
       CreateViewCluster( ViewToWindow, mi_ViewCluster );
   ViewCluster = mi_ViewCluster.intValue( );}
   //:AddToViewCluster( ViewCluster, mMasProd, zCOMMIT_KEEPLOCKS )
   AddToViewCluster( ViewCluster, mMasProd, zCOMMIT_KEEPLOCKS );
   //:AddToViewCluster( ViewCluster, mPrimReg, zCOMMIT_KEEPLOCKS )
   AddToViewCluster( ViewCluster, mPrimReg, zCOMMIT_KEEPLOCKS );
   //:nRC = CommitMultipleObjectInstances( ViewCluster, Ignore )
   {MutableInt mi_Ignore = new MutableInt( Ignore );
       nRC = CommitMultipleObjectInstances( ViewCluster, mi_Ignore );
   Ignore = mi_Ignore.intValue( );}
   //:DropViewCluster( ViewCluster )
   DropViewCluster( ViewCluster );

   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
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

   //:CancelSubobject( mMasProd, "MasterProduct" )
   CancelSubobject( mMasProd, "MasterProduct" );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListSubregistrants( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD lPrimReg
public int 
InitListSubregistrants( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:// Activate the "selected" primary registrant ... just in case someone added or
   //:// deleted a subregistrant.
   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
NewMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:UpdateMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
UpdateMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
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

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );
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
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptContentVersion = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptContentVersion", "" );

   //:// We need to create a new MasterLabelContent entity.
   //:CREATE ENTITY mMasLC.MasterLabelContent
   RESULT = CreateEntity( mMasLC, "MasterLabelContent", zPOS_AFTER );
   //:CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" )
   CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" );
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
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptContentVersion = mMasLC.MasterLabelContent.Version
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptContentVersion", mMasLC, "MasterLabelContent", "Version" );

   //:// We need to update the existing MasterLabelContent entity.
   //:CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" )
   CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" );
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
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING (  50  ) szContentVersion
   String   szContentVersion = null;
   //:INTEGER         lContentVersionLth
   int      lContentVersionLth = 0;
   //:INTEGER         lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
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

   //:mMasLC.MasterLabelContent.Version = szContentVersion
   SetAttributeFromString( mMasLC, "MasterLabelContent", "Version", szContentVersion );
   //:AcceptSubobject( mMasLC, "MasterLabelContent" )
   AcceptSubobject( mMasLC, "MasterLabelContent" );
   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "PrimaryRegistrant", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
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
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING (  50  ) szContentVersion
   String   szContentVersion = null;
   //:INTEGER         lContentVersionLth
   int      lContentVersionLth = 0;
   //:INTEGER         lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
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

   //:AcceptSubobject( mMasLC, "MasterLabelContent" )
   AcceptSubobject( mMasLC, "MasterLabelContent" );
   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mMasLC, "PrimaryRegistrant", "ID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
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
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mMasLC, "MasterLabelContent" )
   CancelSubobject( mMasLC, "MasterLabelContent" );
   //:DELETE ENTITY mMasLC.MasterLabelContent
   RESULT = DeleteEntity( mMasLC, "MasterLabelContent", zPOS_NEXT );
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mPrimReg.MasterLabelContent
   //:    WHERE mPrimReg.MasterLabelContent.ID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mMasLC, "MasterLabelContent", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lTempInteger_1, "" );
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
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mMasLC, "MasterLabelContent" )
   CancelSubobject( mMasLC, "MasterLabelContent" );
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = mMasLC.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mMasLC, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mPrimReg.MasterLabelContent
   //:    WHERE mPrimReg.MasterLabelContent.ID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mMasLC, "MasterLabelContent", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lTempInteger_1, "" );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewMasterProduct( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
CancelNewMasterProduct( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mMasProd, "MasterProduct" )
   CancelSubobject( mMasProd, "MasterProduct" );
   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
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

   //:// We need to update the existing MasterLabelContent entity.
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
   //:CreateTemporalSubobjectVersion( mMasProd, "MasterProduct" )
   CreateTemporalSubobjectVersion( mMasProd, "MasterProduct" );

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasProd BASED ON LOD  mMasProd
   zVIEW    mMasProd = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// We need to create a new MasterLabelContent entity.
   //:ACTIVATE mMasProd EMPTY
   RESULT = ActivateEmptyObjectInstance( mMasProd, "mMasProd", ViewToWindow, zSINGLE );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:CREATE ENTITY mMasProd.MasterProduct
   RESULT = CreateEntity( mMasProd, "MasterProduct", zPOS_AFTER );
   //:IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant",
   //:                               mPrimReg, "PrimaryRegistrant", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mMasProd, "PrimaryRegistrant", mPrimReg, "PrimaryRegistrant", zPOS_BEFORE );
   //:wWebXfer.Root.AttemptProductName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductName", "" );
   //:CreateTemporalSubobjectVersion( mMasProd, "MasterProduct" )
   CreateTemporalSubobjectVersion( mMasProd, "MasterProduct" );

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListMasterProducts( VIEW ViewToWindow )

//:   VIEW lPrimReg REGISTERED AS lPrimReg
public int 
InitListMasterProducts( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF mPrimReg != 0
   if ( getView( mPrimReg ) != null )
   { 
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
   } 

   //:END

   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateMasterProduct( VIEW ViewToWindow )

public int 
UpdateMasterProduct( View     ViewToWindow )
{

   return( 0 );
//    // Don't need to do anything except have this operation to cause
//    // positioning code to be done in the JSP.
// END
} 


//:DIALOG OPERATION
//:DesignMasterProduct( VIEW ViewToWindow )

public int 
DesignMasterProduct( View     ViewToWindow )
{

   return( 0 );
//    // Don't need to do anything except have this operation to cause
//    // positioning code to be done in the JSP.
// END
} 


//:DIALOG OPERATION
//:AcceptDesignMasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptDesignMasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 500000 ) szTempString  // use this to prevent stack overflow
   String   szTempString = null;
   //:SHORT nRC
   int      nRC = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Clean up work entities.
   //:FOR EACH wWebXfer.Div
   RESULT = SetCursorFirstEntity( wWebXfer, "Div", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY wWebXfer.Div NONE
      RESULT = DeleteEntity( wWebXfer, "Div", zREPOS_NONE );
      RESULT = SetCursorNextEntity( wWebXfer, "Div", "" );
   } 

   //:END

   //:ParseHTML_AttrToZeidonOI( wWebXfer, "Root", "wHTML",
   //:                          wWebXfer, "Div", "zid", "Tag", "Name", "Value", "Level", "TagYN", "UsageType",
   //:                          "Attribute", "Name", "Value", "Delimiter" )
   m_ZDRVROPR.ParseHTML_AttrToZeidonOI( wWebXfer, "Root", "wHTML", wWebXfer, "Div", "zid", "Tag", "Name", "Value", "Level", "TagYN", "UsageType", "Attribute", "Name", "Value", "Delimiter" );

   //:MessageSend( ViewToWindow, "", "AcceptDesignMasterProduct",
   //:             "Check out WebXfer.Div entities!!!",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "AcceptDesignMasterProduct", "Check out WebXfer.Div entities!!!", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

   //:FOR EACH wWebXfer.Div
   RESULT = SetCursorFirstEntity( wWebXfer, "Div", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:nRC = PositionOnEntityByZID( mMasLC, wWebXfer.Div.zid )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Div", "zid" );
      szTempString_0 = sb_szTempString_0.toString( );}
      {
       ZGlobal1_Operation m_ZGlobal1_Operation = new ZGlobal1_Operation( mMasLC );
       nRC = m_ZGlobal1_Operation.PositionOnEntityByZID( mMasLC, szTempString_0 );
       // m_ZGlobal1_Operation = null;  // permit gc  (unnecessary)
      }
      //:IF nRC <= 0  // error
      if ( nRC <= 0 )
      { 
         //:IF nRC = 0  // on normal div (part of original html)
         if ( nRC == 0 )
         { 
            //:// don't know what to do yet.
            //:ELSE
         } 
         else
         { 
            //:TraceLineS( "AcceptDesignMasterProduct zid error: ", wWebXfer.Div.zid )
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, wWebXfer, "Div", "zid" );
            szTempString_1 = sb_szTempString_1.toString( );}
            TraceLineS( "AcceptDesignMasterProduct zid error: ", szTempString_1 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// If positioned on MasterLabelContent or MasterLabelContent, there
         //:// should be nothing to do (just for verification of position).
         //:IF nRC = 1 OR nRC = 2
         if ( nRC == 1 || nRC == 2 )
         { 

            //:IF wWebXfer.Tag EXISTS
            lTempInteger_0 = CheckExistenceOfEntity( wWebXfer, "Tag" );
            if ( lTempInteger_0 == 0 )
            { 
               //:// TraceLineS( "AcceptDesignMasterProduct Tag error: ", wWebXfer.Tag.Name ) // causes stack overflow
               //:GetStringFromAttribute( szTempString, wWebXfer, "Tag", "Name" )
               {StringBuilder sb_szTempString;
               if ( szTempString == null )
                  sb_szTempString = new StringBuilder( 32 );
               else
                  sb_szTempString = new StringBuilder( szTempString );
                               GetStringFromAttribute( sb_szTempString, wWebXfer, "Tag", "Name" );
               szTempString = sb_szTempString.toString( );}
               //:TraceLineS( "AcceptDesignMasterProduct Tag error: ", szTempString )
               TraceLineS( "AcceptDesignMasterProduct Tag error: ", szTempString );
            } 

            //:END

            //:ELSE
         } 
         else
         { 

            //:// nRC is 3 ==> positioned on MasterProductSection
            //:FOR EACH wWebXfer.Tag
            RESULT = SetCursorFirstEntity( wWebXfer, "Tag", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 

               //:nRC = IsDivZID( wWebXfer )
               nRC = o_IsDivZID( wWebXfer );
               //:IF  nRC != 0  // not a Zeidon constructed div ==> process entity/attributes
               if ( nRC != 0 )
               { 
               } 

               RESULT = SetCursorNextEntity( wWebXfer, "Tag", "" );

               //:// IF wWebXfer.Tag.TagYN = "Y"
               //://    // Check each attribute for change.
               //://    FOR EACH wWebXfer.Attribute
               //://       IF wWebXfer.Tag.Value != mMasLC.MasterProductSection.SectionText

               //://       // TraceLineS( "wWebXfer.Tag.Value: ", wWebXfer.Tag.Value )  // causes stack overflow
               //://          GetStringFromAttribute( szTempString, wWebXfer, "Tag", "Value" )
               //://          TraceLineS( "wWebXfer.Tag.Value: ", szTempString )
               //://       // TraceLineS( "SectionText: ", mMasLC.MasterProductSection.SectionText ) // causes stack overflow
               //://          GetStringFromAttribute( szTempString, mMasLC, "MasterProductSection", "SectionText" )
               //://          TraceLineS( "SectionText: ", szTempString )
               //://          MessageSend( ViewToWindow, "", "Value Check",
               //://                       "Attributes do not match ... see Zeidon Trace!!!",
               //://                       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               //://       END
               //://    END
               //:// ELSE
               //://    // Check name only
               //://    IF wWebXfer.Tag.Name != mMasLC.MasterProductSection.SectionText

               //://    // TraceLineS( "wWebXfer.Tag.Name: ", wWebXfer.Tag.Name )  // causes stack overflow
               //://       GetStringFromAttribute( szTempString, wWebXfer, "Tag", "Name" )
               //://       TraceLineS( "wWebXfer.Tag.Name: ", szTempString )
               //://    // TraceLineS( "SectionText: ", mMasLC.MasterProductSection.SectionText ) // causes stack overflow
               //://       GetStringFromAttribute( szTempString, mMasLC, "MasterProductSection", "SectionText" )
               //://       TraceLineS( "SectionText: ", szTempString )
               //://       MessageSend( ViewToWindow, "", "Name Check",
               //://                    "Attributes do not match!!!",
               //://                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               //://    END
               //:// END

               //:END
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( wWebXfer, "Div", "" );
      //:END
   } 

   //:END

   //:AcceptSubobject( mMasLC, "MasterLabelContent" )
   AcceptSubobject( mMasLC, "MasterLabelContent" );
   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );

   //:// Clean up work entities.
   //:FOR EACH wWebXfer.Div
   RESULT = SetCursorFirstEntity( wWebXfer, "Div", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY wWebXfer.Div NONE
      RESULT = DeleteEntity( wWebXfer, "Div", zREPOS_NONE );
      RESULT = SetCursorNextEntity( wWebXfer, "Div", "" );
   } 

   //:END

   //:wWebXfer.Root.SplitHTML_Before = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_Before", "" );
   //:wWebXfer.Root.HTML = ""
   SetAttributeFromString( wWebXfer, "Root", "HTML", "" );
   //:wWebXfer.Root.SplitHTML_After = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_After", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDesignMasterProduct( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelDesignMasterProduct( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mMasLC, "MasterLabelContent" )
   CancelSubobject( mMasLC, "MasterLabelContent" );
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );

   //:// Clean up work entities.
   //:FOR EACH wWebXfer.Div
   RESULT = SetCursorFirstEntity( wWebXfer, "Div", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY wWebXfer.Div NONE
      RESULT = DeleteEntity( wWebXfer, "Div", zREPOS_NONE );
      RESULT = SetCursorNextEntity( wWebXfer, "Div", "" );
   } 

   //:END

   //:wWebXfer.Root.SplitHTML_Before = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_Before", "" );
   //:wWebXfer.Root.HTML = ""
   SetAttributeFromString( wWebXfer, "Root", "HTML", "" );
   //:wWebXfer.Root.SplitHTML_After = ""
   SetAttributeFromString( wWebXfer, "Root", "SplitHTML_After", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mPrimReg BASED ON LOD  mPrimReg
public int 
DeleteMasterLabelContent( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC BASED ON LOD    mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW mMasLC NAMED "mMasLC"
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mMasLC, "MasterLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:InitListMasterProducts( ViewToWindow )
      InitListMasterProducts( ViewToWindow );
      //:GET VIEW mPrimReg NAMED "mPrimReg"
      RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mPrimReg.MasterLabelContent
      //:    WHERE mPrimReg.MasterLabelContent.ID = lID
      RESULT = SetCursorFirstEntityByInteger( mPrimReg, "MasterLabelContent", "ID", lID, "" );

      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:InitMasterLabelContentForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterLabelContentForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:// VIEW mMasLC REGISTERED AS mMasLC

   //:// TraceLineS( "", "" )
   //:// TraceLineS( "InitMasterLabelContentForDelete", "" )
   //:// DisplayObjectInstance( mMasLC, "", "" )

   //:SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantContent" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wPrimR", "PrimaryRegistrantContent" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteMasterLabelContent( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
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
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDeleteMasterLabelContent( VIEW ViewToWindow )

public int 
CancelDeleteMasterLabelContent( View     ViewToWindow )
{

   return( 0 );
// // VIEW mMasLC REGISTERED AS mMasLC
// END
} 


//:DIALOG OPERATION
//:MoveMasterLabelContentUp( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMasterLabelContentUp( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLDD BASED ON LOD  mMasLC
   zVIEW    mTempLDD = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mMasLC, "MasterLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );

      //:CreateViewFromView( mTempLDD, mMasLC )
      CreateViewFromView( mTempLDD, mMasLC );
      //:lMove = 1
      lMove = 1;
      //:LOOP WHILE lMove > 0
      while ( lMove > 0 )
      { 
         //:SET CURSOR PREVIOUS mTempLDD.MasterLabelContent
         RESULT = SetCursorPrevEntity( mTempLDD, "MasterLabelContent", "" );
         //:lMove = lMove - 1
         lMove = lMove - 1;
      } 

      //:END

      //:MoveSubobject( mTempLDD, "MasterLabelContent",
      //:               mMasLC, "MasterLabelContent",
      //:               zPOS_PREV, zREPOS_PREV )
      MoveSubobject( mTempLDD, "MasterLabelContent", mMasLC, "MasterLabelContent", zPOS_PREV, zREPOS_PREV );
      //:DropView( mTempLDD )
      DropView( mTempLDD );
      //:Commit mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:MoveMasterLabelContentDown( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
MoveMasterLabelContentDown( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mTempLDD BASED ON LOD  mMasLC
   zVIEW    mTempLDD = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lContentID
   int      lContentID = 0;
   //:INTEGER lMove
   int      lMove = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mMasLC, "MasterLabelContent", "ID" );
   lID = mi_lID.intValue( );}
   //:lContentID = mMasLC.MasterLabelContent.ID
   {MutableInt mi_lContentID = new MutableInt( lContentID );
       GetIntegerFromAttribute( mi_lContentID, mMasLC, "MasterLabelContent", "ID" );
   lContentID = mi_lContentID.intValue( );}

   //:// We have to make sure the Product is in good shape before we go on!
   //:nRC = AcceptUpdateMasterProduct( ViewToWindow )
   nRC = AcceptUpdateMasterProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lID
      o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_0, lID );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW mMasLC "mMasLC"
      SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
      //:SET CURSOR FIRST mMasLC.MasterLabelContent
      //:    WHERE mMasLC.MasterLabelContent.ID = lContentID
      RESULT = SetCursorFirstEntityByInteger( mMasLC, "MasterLabelContent", "ID", lContentID, "" );

      //:CreateViewFromView( mTempLDD, mMasLC )
      CreateViewFromView( mTempLDD, mMasLC );
      //:lMove = 1
      lMove = 1;
      //:LOOP WHILE lMove > 0
      while ( lMove > 0 )
      { 
         //:SET CURSOR NEXT mTempLDD.MasterLabelContent
         RESULT = SetCursorNextEntity( mTempLDD, "MasterLabelContent", "" );
         //:lMove = lMove - 1
         lMove = lMove - 1;
      } 

      //:END

      //:MoveSubobject( mTempLDD, "MasterLabelContent",
      //:               mMasLC, "MasterLabelContent",
      //:               zPOS_NEXT, zREPOS_NEXT )
      MoveSubobject( mTempLDD, "MasterLabelContent", mMasLC, "MasterLabelContent", zPOS_NEXT, zREPOS_NEXT );
      //:DropView( mTempLDD )
      DropView( mTempLDD );
      //:Commit mMasLC
      RESULT = CommitObjectInstance( mMasLC );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:LOCAL OPERATION
//:IsDivZID( VIEW vHTML )

//:   SHORT nRC
private int 
o_IsDivZID( View     vHTML )
{
   int      nRC = 0;


   //:nRC = CheckExistenceOfEntity( vHTML, "Attribute" )
   nRC = CheckExistenceOfEntity( vHTML, "Attribute" );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:nRC = CompareAttributeToString( vHTML, "Attribute", "Name", "<zid>" )
      nRC = CompareAttributeToString( vHTML, "Attribute", "Name", "<zid>" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 



}
