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

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class wSubR_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public wSubR_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:DIALOG OPERATION
//:AcceptUpdateSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:STRING (  50  ) szLoginName
   String   szLoginName = null;
   //:STRING (  50  ) szAttemptLoginName
   String   szAttemptLoginName = null;
   //:INTEGER         lControl
   int      lControl = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:szLoginName = mSubreg.SubregOrganization.LoginName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szLoginName;
   if ( szLoginName == null )
      sb_szLoginName = new StringBuilder( 32 );
   else
      sb_szLoginName = new StringBuilder( szLoginName );
       GetVariableFromAttribute( sb_szLoginName, mi_lTempInteger_0, 'S', 51, mSubreg, "SubregOrganization", "LoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szLoginName = sb_szLoginName.toString( );}
   //:szAttemptLoginName = wWebXfer.Root.AttemptUserName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szAttemptLoginName;
   if ( szAttemptLoginName == null )
      sb_szAttemptLoginName = new StringBuilder( 32 );
   else
      sb_szAttemptLoginName = new StringBuilder( szAttemptLoginName );
       GetVariableFromAttribute( sb_szAttemptLoginName, mi_lTempInteger_1, 'S', 51, wWebXfer, "Root", "AttemptUserName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szAttemptLoginName = sb_szAttemptLoginName.toString( );}
   //:IF szLoginName != szAttemptLoginName
   if ( ZeidonStringCompare( szLoginName, 1, 0, szAttemptLoginName, 1, 0, 51 ) != 0 )
   { 

      //:ACTIVATE qOrganiz WHERE qOrganiz.Organization.LoginName = szAttemptLoginName
      o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_0, szAttemptLoginName );
      RESULT = ActivateObjectInstance( qOrganiz, "qOrganiz", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:IF qOrganiz.Organization EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( qOrganiz, "Organization" );
      if ( lTempInteger_2 == 0 )
      { 

         //:DropObjectInstance( qOrganiz )
         DropObjectInstance( qOrganiz );
         //:MessageSend( ViewToWindow, "", "Update Subregistrant User",
         //:             "The Login Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Update Subregistrant User", "The Login Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END

      //:DropObjectInstance( qOrganiz )
      DropObjectInstance( qOrganiz );

      //:// Set changed LoginName
      //:mSubreg.SubregOrganization.LoginName = szAttemptLoginName
      SetAttributeFromString( mSubreg, "SubregOrganization", "LoginName", szAttemptLoginName );
   } 


   //:END


   //:// AcceptSubobject( mSubreg, "User" )
   //:// AcceptSubobject( mSubreg, "Person" )
   //:// AcceptSubobject( mSubreg, "PhysicalAddress" )
   //:// AcceptSubobject( mSubreg, "MailingAddress" )
   //:AcceptSubobject( mSubreg, "Subregistrant" )
   AcceptSubobject( mSubreg, "Subregistrant" );
   //:IF wWebXfer.Root.SameAs = "Y"
   if ( CompareAttributeToString( wWebXfer, "Root", "SameAs", "Y" ) == 0 )
   { 
      //:DELETE ENTITY mSubreg.MailingAddress
      RESULT = DeleteEntity( mSubreg, "MailingAddress", zPOS_NEXT );
   } 

   //:END

   //:mSubreg.SubregOrganization.Role = "S"
   SetAttributeFromString( mSubreg, "SubregOrganization", "Role", "S" );
   //:Commit mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_2( View     vSubtask,
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
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szAttemptLoginName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LoginName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szAttemptLoginName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szAttemptLoginName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LoginName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szAttemptLoginName.toString( ) );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
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
o_fnLocalBuildQual_8( View     vSubtask,
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
o_fnLocalBuildQual_9( View     vSubtask,
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
o_fnLocalBuildQual_10( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_11( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_12( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_5 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MasterLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "MasterLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_5 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregLabelContent" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregLabelContent" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_14( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SubregProduct" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SubregProduct" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
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
o_fnLocalBuildQual_17( View     vSubtask,
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:ConfirmChangeSubregUserPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmChangeSubregUserPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );

   //:// 1: Ensure old password is correct.
   //:// IF mCurrentUser.User.UserPassword != wWebXfer.Root.VerifiedPassword
   //:szAttemptPassword = wWebXfer.Root.CurrentPassword
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_0, 'S', 129, wWebXfer, "Root", "CurrentPassword", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:nRC = CompareAttributeToString( mCurrentUser, "User", "UserPassword", szAttemptPassword )
   nRC = CompareAttributeToString( mCurrentUser, "User", "UserPassword", szAttemptPassword );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 

      //:// TraceLineS( "//////* ", "Invalid Current User Password" )
      //:MessageSend( ViewToWindow, "", "Change Subregistrant User Password",
      //:             "Current password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Subregistrant User Password", "Current password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_1, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:szConfirmPassword = wWebXfer.Root.ConfirmPassword
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_2, 'S', 129, wWebXfer, "Root", "ConfirmPassword", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szConfirmPassword = sb_szConfirmPassword.toString( );}

   //:// 1: Ensure attempted password matches confirm password.
   //:IF szAttemptPassword != szConfirmPassword
   if ( ZeidonStringCompare( szAttemptPassword, 1, 0, szConfirmPassword, 1, 0, 129 ) != 0 )
   { 
      //:// TraceLineS( szAttemptPassword, szConfirmPassword )
      //:MessageSend( ViewToWindow, "", "Change Password",
      //:             "The new password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Password", "The new password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// 3: Ensure new password is at least 8 characters long.
   //:lPasswordLth = zGetStringLen( szConfirmPassword )
   lPasswordLth = zGetStringLen( szConfirmPassword );
   //:TraceLineI( "Password Length: ", lPasswordLth )
   TraceLineI( "Password Length: ", lPasswordLth );
   //:IF lPasswordLth < 8
   if ( lPasswordLth < 8 )
   { 
      //:MessageSend( ViewToWindow, "", "Change Password",
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Password", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:mCurrentUser.User.UserPassword = szConfirmPassword
   SetAttributeFromString( mCurrentUser, "User", "UserPassword", szConfirmPassword );
   //:COMMIT mCurrentUser
   RESULT = CommitObjectInstance( mCurrentUser );
   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitChangeSubregUserPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitChangeSubregUserPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser BASED ON LOD  mUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:// We are activating a User instance for update.
   //:ACTIVATE mCurrentUser WHERE mCurrentUser.User.ID = mSubreg.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_17( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mCurrentUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mCurrentUser "mCurrentUser"
   SetNameForView( mCurrentUser, "mCurrentUser", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelChangeSubregUserPassword( VIEW ViewToWindow )

//:   VIEW mCurrentUser REGISTERED AS mCurrentUser
public int 
CancelChangeSubregUserPassword( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSubregLabelContent( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
AcceptUpdateSubregLabelContent( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC  REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lLabelID
   int      lLabelID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mSubreg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:lLabelID = mSubreg.SubregLabelContent.ID
   {MutableInt mi_lLabelID = new MutableInt( lLabelID );
       GetIntegerFromAttribute( mi_lLabelID, mSubreg, "SubregLabelContent", "ID" );
   lLabelID = mi_lLabelID.intValue( );}

   //:AcceptSubobject( mSubreg, "SubregLabelContent" )
   AcceptSubobject( mSubreg, "SubregLabelContent" );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.SubregLabelContent WHERE mSubreg.SubregLabelContent.ID = lLabelID
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregLabelContent", "ID", lLabelID, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewSubregLabelContent( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
NewSubregLabelContent( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );

   //:INTEGER lProductID
   int      lProductID = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:lProductID = mSubreg.SubregProduct.ID
   {MutableInt mi_lProductID = new MutableInt( lProductID );
       GetIntegerFromAttribute( mi_lProductID, mSubreg, "SubregProduct", "ID" );
   lProductID = mi_lProductID.intValue( );}

   //:// If there was a problem in the accept, the messages and action behavior
   //:// should have been set in the accept code.
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow )
   nRC = AcceptUpdateSubregProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:// Reposition on the "correct" SubregLabelContent.
      //:GET VIEW mSubreg NAMED "mSubreg"  // it was re-activated, so need to get the view
      RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubreg.SubregProduct WHERE mSubreg.SubregProduct.ID = lProductID
      RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lProductID, "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:AcceptDesignSubregProductLabel( VIEW ViewToWindow )

public int 
AcceptDesignSubregProductLabel( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelDesignSubregProductLabel( VIEW ViewToWindow )

public int 
CancelDesignSubregProductLabel( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReturnFromSubregistrant( VIEW ViewToWindow )

//:   VIEW lSubreg  BASED ON LOD  lSubreg
public int 
ReturnFromSubregistrant( View     ViewToWindow )
{
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg != 0
   if ( getView( mSubreg ) != null )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelNewSubregUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "Employee" )
   CancelSubobject( mSubreg, "Employee" );
   return( 0 );
// // IF wWebXfer.Root.LoginName = "Admin"
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
// //                             "wSubR", "SubregUsers" )
// // END
// END
} 


//:DIALOG OPERATION
//:AcceptNewSubregUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewSubregUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW qSubreg  BASED ON LOD  qSubreg
   zVIEW    qSubreg = new zVIEW( );
   //:STRING (  50  ) szUserName
   String   szUserName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lUserNameLth
   int      lUserNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:INTEGER         lControl
   int      lControl = 0;
   //:INTEGER         lID
   int      lID = 0;
   //:INTEGER         lPersonID
   int      lPersonID = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure user login name is not blank and is unique.
   //:szUserName = wWebXfer.Root.AttemptUserName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptUserName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szUserName = sb_szUserName.toString( );}
   //:lUserNameLth = zGetStringLen( szUserName )
   lUserNameLth = zGetStringLen( szUserName );
   //:TraceLineS( "User Login Name: ", szUserName )
   TraceLineS( "User Login Name: ", szUserName );
   //:TraceLineI( "User Login Name Length: ", lUserNameLth )
   TraceLineI( "User Login Name Length: ", lUserNameLth );
   //:IF lUserNameLth < 1
   if ( lUserNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Subregistrant User",
      //:             "The User Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant User", "The User Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:IF SetEntityCursor( mSubreg, "User", "UserName", lControl,
      //:                    szUserName, "", "", 0, "SubregOrganization", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( mSubreg, "User", "UserName", lControl, szUserName, "", "", 0, "SubregOrganization", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Subregistrant User",
         //:             "The User Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Subregistrant User", "The User Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
   lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
   //:IF SetEntityCursor( mSubreg, "User", "UserName", lControl,
   //:                    "Admin", "", "", 0, "SubregOrganization", "" ) < zCURSOR_SET
   lTempInteger_2 = SetEntityCursor( mSubreg, "User", "UserName", lControl, "Admin", "", "", 0, "SubregOrganization", "" );
   if ( lTempInteger_2 < zCURSOR_SET )
   { 
      //:TraceLineS( "AcceptNewSubregUser: ", "Admin must be first login found!!!" )
      TraceLineS( "AcceptNewSubregUser: ", "Admin must be first login found!!!" );
      //:MessageSend( ViewToWindow, "", "New Subregistrant User",
      //:             "The first User Login Name must be 'Admin'.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant User", "The first User Login Name must be 'Admin'.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:mSubreg.User.UserName = szUserName
   SetAttributeFromString( mSubreg, "User", "UserName", szUserName );

   //:// Ensure user first and last names are not blank.
   //:szUserName = mSubreg.Employee.FirstName
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_3, 'S', 51, mSubreg, "Employee", "FirstName", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szUserName = sb_szUserName.toString( );}
   //:lUserNameLth = zGetStringLen( szUserName )
   lUserNameLth = zGetStringLen( szUserName );
   //:TraceLineS( "First Name: ", szUserName )
   TraceLineS( "First Name: ", szUserName );
   //:TraceLineI( "First Name Length: ", lUserNameLth )
   TraceLineI( "First Name Length: ", lUserNameLth );
   //:IF lUserNameLth < 1
   if ( lUserNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant User",
      //:             "The user First Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant User", "The user First Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szUserName = mSubreg.Employee.LastName
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_4, 'S', 51, mSubreg, "Employee", "LastName", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szUserName = sb_szUserName.toString( );}
   //:lUserNameLth = zGetStringLen( szUserName )
   lUserNameLth = zGetStringLen( szUserName );
   //:TraceLineS( "Last Name: ", szUserName )
   TraceLineS( "Last Name: ", szUserName );
   //:TraceLineI( "Last Name Length: ", lUserNameLth )
   TraceLineI( "Last Name Length: ", lUserNameLth );
   //:IF lUserNameLth < 1
   if ( lUserNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant User",
      //:             "The user Last Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant User", "The user Last Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_5, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:szConfirmPassword = wWebXfer.Root.ConfirmPassword
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_6, 'S', 129, wWebXfer, "Root", "ConfirmPassword", "", 0 );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );
   szConfirmPassword = sb_szConfirmPassword.toString( );}

   //:// 1: Ensure attempted password matches confirm password.
   //:IF szAttemptPassword != szConfirmPassword
   if ( ZeidonStringCompare( szAttemptPassword, 1, 0, szConfirmPassword, 1, 0, 129 ) != 0 )
   { 
      //:// TraceLineS( szAttemptPassword, szConfirmPassword )
      //:MessageSend( ViewToWindow, "", "New Subregistrant User",
      //:             "The password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant User", "The password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// 2: Ensure password is at least 8 characters long.
   //:lPasswordLth = zGetStringLen( szConfirmPassword )
   lPasswordLth = zGetStringLen( szConfirmPassword );
   //:TraceLineI( "Password Length: ", lPasswordLth )
   TraceLineI( "Password Length: ", lPasswordLth );
   //:IF lPasswordLth < 8
   if ( lPasswordLth < 8 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant User",
      //:             "The password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant User", "The password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( qSubreg, "User", "UserPassword", szVerifyPassword, "Password" )
   //:mSubreg.User.UserPassword = szConfirmPassword
   SetAttributeFromString( mSubreg, "User", "UserPassword", szConfirmPassword );

   //:AcceptSubobject( mSubreg, "Employee" )
   AcceptSubobject( mSubreg, "Employee" );
   //:Commit mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   return( 0 );
// // IF wWebXfer.Root.LoginName = "Admin"
// //
// //    // Activate the "selected" subregistrant ... just in case someone added or
// //    // deleted a subregistrant user.
// //    ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
// //    NAME VIEW mSubreg "mSubreg"
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
// //                             "wSubR", "SubregUsers" )
// // END
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelUpdateSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "SubregProduct" )
   CancelSubobject( mSubreg, "SubregProduct" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewSubregLabelX( VIEW ViewToWindow )

//:   VIEW mSubreg  BASED ON LOD  mSubreg
public int 
NewSubregLabelX( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   //:INTEGER lProductID
   int      lProductID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:lProductID = mSubreg.SubregProduct.ID
   {MutableInt mi_lProductID = new MutableInt( lProductID );
       GetIntegerFromAttribute( mi_lProductID, mSubreg, "SubregProduct", "ID" );
   lProductID = mi_lProductID.intValue( );}

   //:// If there was a problem in the accept, the messages and action behavior
   //:// are set in the accept code.
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow )
   nRC = AcceptUpdateSubregProduct( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:// Reposition on the "correct" SubregLabelContent.
      //:GET VIEW mSubreg NAMED "mSubreg"  // it was re-activated, so need to get the view
      RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
      //:SET CURSOR FIRST mSubreg.SubregProduct WHERE mSubreg.SubregProduct.ID = lProductID
      RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregProduct", "ID", lProductID, "" );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
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
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregProductForUpdate( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
InitSubregProductForUpdate( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SubregProduct entity.
   //:CreateTemporalSubobjectVersion( mSubreg, "SubregProduct" )
   CreateTemporalSubobjectVersion( mSubreg, "SubregProduct" );

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantProduct" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
AcceptUpdateSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mSubreg, "SubregProduct" )
   AcceptSubobject( mSubreg, "SubregProduct" );
   //:Commit mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   return( 0 );
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
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregistrant( VIEW ViewToWindow )

//:   VIEW mSubreg BASED ON LOD mSubreg
public int 
CancelNewSubregistrant( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:// CancelSubobject( mSubreg, "User" )
   //:// CancelSubobject( mSubreg, "Person" )
   //:// CancelSubobject( mSubreg, "PhysicalAddress" )
   //:// CancelSubobject( mSubreg, "MailingAddress" )
   //:CancelSubobject( mSubreg, "Subregistrant" )
   CancelSubobject( mSubreg, "Subregistrant" );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregProductForInsert( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
InitSubregProductForInsert( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// We need to create a new SubregProduct entity.
   //:CreateTemporalEntity( mSubreg, "SubregProduct", zPOS_AFTER )
   CreateTemporalEntity( mSubreg, "SubregProduct", zPOS_AFTER );
   //:SetAttributeFromString( mSubreg, "SubregProduct", "Number", "0" )
   SetAttributeFromString( mSubreg, "SubregProduct", "Number", "0" );
   //:SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantProduct" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantProduct" );
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
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW mSubProd BASED ON LOD  mSubProd
   zVIEW    mSubProd = new zVIEW( );
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// We need to update the existing SubregLabelContent entity.
   //:CreateTemporalSubobjectVersion( mSubreg, "SubregLabelContent" )
   CreateTemporalSubobjectVersion( mSubreg, "SubregLabelContent" );

   //:ACTIVATE mSubLC WHERE mSubLC.SubregLabelContent.ID = mSubreg.SubregLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "SubregLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubLC "mSubLC"
   SetNameForView( mSubLC, "mSubLC", null, zLEVEL_TASK );

   //:ACTIVATE mSubProd WHERE mSubProd.SubregProduct.ID = mSubLC.SubregProduct.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSubLC, "SubregProduct", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mSubProd, "mSubProd", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mSubProd "mSubProd"
   SetNameForView( mSubProd, "mSubProd", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregLabelContentForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregLabelContentForInsert( View     ViewToWindow )
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
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:AcceptSubobject( mSubreg, "SubregProduct" )
   AcceptSubobject( mSubreg, "SubregProduct" );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );

   //:// // We need to create a new SubregLabelContent entity.
   //:// CreateEntity( mSubreg, "SubregLabelContent", zPOS_LAST )

   //:// It is now time to initialize a new Product Label Content.  That is a
   //:// bit of work, but here goes ...
   //:// First, activate a MasterProduct OI so we can figure out the latest
   //:// Master Label Content version.
   //:ACTIVATE mMasProd WHERE mMasProd.MasterProduct.ID = mSubreg.MasterProduct.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "MasterProduct", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasProd, "mMasProd", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasProd "mMasProd"
   SetNameForView( mMasProd, "mMasProd", null, zLEVEL_TASK );

   //:// Let's first get rid of all Product Label Content definitions for this product
   //:// since we only want one???
   //:ACTIVATE mSubLC WHERE mSubLC.SubregProduct.ID = mSubreg.SubregProduct.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, mSubreg, "SubregProduct", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:LOOP WHILE mSubLC.SubregLabelContent EXISTS
   lTempInteger_2 = CheckExistenceOfEntity( mSubLC, "SubregLabelContent" );
   while ( lTempInteger_2 == 0 )
   { 
      //:DeleteEntity( mSubLC, "SubregLabelContent", zREPOS_NONE )
      DeleteEntity( mSubLC, "SubregLabelContent", zREPOS_NONE );
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
      //:ACTIVATE mSubLC WHERE mSubLC.SubregProduct.ID = mSubreg.SubregProduct.ID
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mSubreg, "SubregProduct", "ID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_2, lTempInteger_3 );
      RESULT = ActivateObjectInstance( mSubLC, "mSubLC", ViewToWindow, vTempViewVar_2, zSINGLE );
      DropView( vTempViewVar_2 );
      lTempInteger_2 = CheckExistenceOfEntity( mSubLC, "SubregLabelContent" );
   } 

   //:END

   //:IF mSubLC != 0
   if ( getView( mSubLC ) != null )
   { 
      //:DropObjectInstance( mSubLC )
      DropObjectInstance( mSubLC );
   } 

   //:END

   //:// Now because we keep the latest version of the Master Product Label
   //:// first in the list, we will just make sure we have an entity and use it.
   //:IF mMasProd.MasterLabelContent EXISTS
   lTempInteger_4 = CheckExistenceOfEntity( mMasProd, "MasterLabelContent" );
   if ( lTempInteger_4 == 0 )
   { 

      //:// Now activate the latest Master Label Content OI.
      //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = mMasProd.MasterLabelContent.ID
      {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
             GetIntegerFromAttribute( mi_lTempInteger_5, mMasProd, "MasterLabelContent", "ID" );
      lTempInteger_5 = mi_lTempInteger_5.intValue( );}
      o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_3, lTempInteger_5 );
      RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_3, zSINGLE );
      DropView( vTempViewVar_3 );
      //:NAME VIEW mMasLC "mMasLC_NewPLC"
      SetNameForView( mMasLC, "mMasLC_NewPLC", null, zLEVEL_TASK );

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

      //:// Now run through the Master Label Content entities and create the
      //:// corresponding Subregistrant Label Content entities.
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

   //:DropObjectInstance( mMasProd )
   DropObjectInstance( mMasProd );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitDesignSubregProductLabel( View     ViewToWindow )
{

   //:InitDesignSubregProductLabel( VIEW ViewToWindow )

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "SubregistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregistrantForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregistrantForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:INTEGER       lID
   int      lID = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wSubR.InitSubregistrantForInsert cannot find Transfer View", "" )
      TraceLineS( "wSubR.InitSubregistrantForInsert cannot find Transfer View", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg = 0
   if ( getView( mSubreg ) == null )
   { 
      //:MessageSend( ViewToWindow, "", "Subregistrant",
      //:             "Invalid Subregistrant.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Subregistrant", "Invalid Subregistrant.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:ACTIVATE mSubreg EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubreg, "mSubreg", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:CreateTemporalEntity( mSubreg, "Subregistrant", zPOS_LAST )
   CreateTemporalEntity( mSubreg, "Subregistrant", zPOS_LAST );
   //:CreateTemporalEntity( mSubreg, "PhysicalAddress", zPOS_LAST )
   CreateTemporalEntity( mSubreg, "PhysicalAddress", zPOS_LAST );
   //:CreateTemporalEntity( mSubreg, "MailingAddress", zPOS_LAST )
   CreateTemporalEntity( mSubreg, "MailingAddress", zPOS_LAST );
   //:CreateTemporalEntity( mSubreg, "ContactPerson", zPOS_LAST )
   CreateTemporalEntity( mSubreg, "ContactPerson", zPOS_LAST );
   //:mSubreg.PhysicalAddress.Country = "USA"
   SetAttributeFromString( mSubreg, "PhysicalAddress", "Country", "USA" );
   //:mSubreg.MailingAddress.Country = "USA"
   SetAttributeFromString( mSubreg, "MailingAddress", "Country", "USA" );
   //:wWebXfer.Root.SameAs = "Y"
   SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" );
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
         //:mSubreg.SubregProduct.ChemicalFamily = mSubreg.ListMasterProduct.ChemicalFamily
         SetAttributeFromAttribute( mSubreg, "SubregProduct", "ChemicalFamily", mSubreg, "ListMasterProduct", "ChemicalFamily" );
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
//:AcceptNewSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:STRING (  50  ) szAttemptLoginName
   String   szAttemptLoginName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_0, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:szConfirmPassword = wWebXfer.Root.ConfirmPassword
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_1, 'S', 129, wWebXfer, "Root", "ConfirmPassword", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szConfirmPassword = sb_szConfirmPassword.toString( );}

   //:// 1: Ensure attempted password matches confirm password.
   //:IF szAttemptPassword != szConfirmPassword
   if ( ZeidonStringCompare( szAttemptPassword, 1, 0, szConfirmPassword, 1, 0, 129 ) != 0 )
   { 
      //:// TraceLineS( szAttemptPassword, szConfirmPassword )
      //:MessageSend( ViewToWindow, "", "New Subregistrant",
      //:             "The new password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant", "The new password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// 2: Ensure new password is at least 8 characters long.
   //:lPasswordLth = zGetStringLen( szConfirmPassword )
   lPasswordLth = zGetStringLen( szConfirmPassword );
   //:TraceLineI( "Password Length: ", lPasswordLth )
   TraceLineI( "Password Length: ", lPasswordLth );
   //:IF lPasswordLth < 8
   if ( lPasswordLth < 8 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant",
      //:             "The password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant", "The password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szAttemptLoginName = wWebXfer.Root.AttemptUserName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szAttemptLoginName;
   if ( szAttemptLoginName == null )
      sb_szAttemptLoginName = new StringBuilder( 32 );
   else
      sb_szAttemptLoginName = new StringBuilder( szAttemptLoginName );
       GetVariableFromAttribute( sb_szAttemptLoginName, mi_lTempInteger_2, 'S', 51, wWebXfer, "Root", "AttemptUserName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szAttemptLoginName = sb_szAttemptLoginName.toString( );}
   //:ACTIVATE qOrganiz WHERE qOrganiz.Organization.LoginName = szAttemptLoginName
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_0, szAttemptLoginName );
   RESULT = ActivateObjectInstance( qOrganiz, "qOrganiz", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF qOrganiz.Organization EXISTS
   lTempInteger_3 = CheckExistenceOfEntity( qOrganiz, "Organization" );
   if ( lTempInteger_3 == 0 )
   { 

      //:DropObjectInstance( qOrganiz )
      DropObjectInstance( qOrganiz );
      //:MessageSend( ViewToWindow, "", "Update Subregistrant User",
      //:             "The Login Name must be unique.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant User", "The Login Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:DropObjectInstance( qOrganiz )
   DropObjectInstance( qOrganiz );

   //:// Set LoginName
   //:mSubreg.SubregOrganization.LoginName = szAttemptLoginName
   SetAttributeFromString( mSubreg, "SubregOrganization", "LoginName", szAttemptLoginName );

   //:// Set user password to new password.
   //:mSubreg.User.UserPassword = szConfirmPassword
   SetAttributeFromString( mSubreg, "User", "UserPassword", szConfirmPassword );

   //:// AcceptSubobject( mSubreg, "User" )
   //:// AcceptSubobject( mSubreg, "Person" )
   //:// AcceptSubobject( mSubreg, "PhysicalAddress" )
   //:// AcceptSubobject( mSubreg, "MailingAddress" )
   //:AcceptSubobject( mSubreg, "Subregistrant" )
   AcceptSubobject( mSubreg, "Subregistrant" );
   //:IF wWebXfer.Root.SameAs = "Y"
   if ( CompareAttributeToString( wWebXfer, "Root", "SameAs", "Y" ) == 0 )
   { 
      //:DELETE ENTITY mSubreg.MailingAddress
      RESULT = DeleteEntity( mSubreg, "MailingAddress", zPOS_NEXT );
   } 

   //:END

   //:mSubreg.SubregOrganization.Role = "S"
   SetAttributeFromString( mSubreg, "SubregOrganization", "Role", "S" );
   //:Commit mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateSubregProduct( VIEW ViewToWindow )

public int 
UpdateSubregProduct( View     ViewToWindow )
{

   return( 0 );
//    // Just here to cause selection processing
// END
} 


//:DIALOG OPERATION
//:NewSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
NewSubregProduct( View     ViewToWindow )
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
//:UpdateSubregUser( VIEW ViewToWindow )

public int 
UpdateSubregUser( View     ViewToWindow )
{

   return( 0 );
//   // shouldn't need to do anything here ... just for positioning
// END
} 


//:DIALOG OPERATION
//:DeleteSubregUser( VIEW ViewToWindow )

public int 
DeleteSubregUser( View     ViewToWindow )
{

   return( 0 );
//    // Just for positioning ...
// END
} 


//:DIALOG OPERATION
public int 
InitListSubregProducts( View     ViewToWindow )
{

   //:InitListSubregProducts( VIEW ViewToWindow )

   //:// we get the list from the mSubreg view ... so nothing to do

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" );
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
//    // Just for positioning ...
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelUpdateSubregUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mSubreg, "Employee" )
   CancelSubobject( mSubreg, "Employee" );
   return( 0 );
// // IF wWebXfer.Root.LoginName = "Admin"
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
// //                             "wSubR", "SubregUsers" )
// // END
// END
} 


//:DIALOG OPERATION
//:InitSubregUserForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregUserForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:// VIEW qSubreg  BASED ON LOD  qSubreg

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:IF mSubreg = 0
   if ( getView( mSubreg ) == null )
   { 
      //:MessageSend( ViewToWindow, "", "Initialize Subregistrant User",
      //:             "The registrant list is empty.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Initialize Subregistrant User", "The registrant list is empty.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// GET VIEW qSubreg NAMED "qSubreg"
   //:// IF qSubreg != 0
   //://    DropObjectInstance( qSubreg )
   //:// END
   //://
   //:// ACTIVATE qSubreg WHERE qSubreg.Subregistrant.ID = lSubreg.Subregistrant.ID
   //:// NAME VIEW qSubreg "qSubreg"
   //://
   //:// IF qSubreg.Subregistrant DOES NOT EXIST
   //://    TraceLineI( "InitSubregUserForUpdate cannot activate Subregistrant: ",
   //://                lSubreg.Subregistrant.ID )
   //://    MessageSend( ViewToWindow, "", "Initialize Subregistrant User",
   //://                 "Cannot activate Subregistrant.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    RETURN 2
   //:// END
   //://
   //:// DropObjectInstance( qSubreg )

   //:DisplayObjectInstance( mSubreg, "", "" )
   DisplayObjectInstance( mSubreg, "", "" );
   //:wWebXfer.Root.AttemptUserName = mSubreg.User.UserName
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptUserName", mSubreg, "User", "UserName" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:CreateTemporalSubobjectVersion( mSubreg, "Employee" )
   CreateTemporalSubobjectVersion( mSubreg, "Employee" );
   return( 0 );
// // CreateTemporalSubobjectVersion( mPerson, "Person" )
// // CreateTemporalSubobjectVersion( mPerson, "Address" )
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSubregUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateSubregUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:// VIEW lSubreg  BASED ON LOD  lSubreg
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING (  50  ) szUserName
   String   szUserName = null;
   //:STRING (  50  ) szAttemptUserName
   String   szAttemptUserName = null;
   //:INTEGER         lUserNameLth
   int      lUserNameLth = 0;
   //:INTEGER         lControl
   int      lControl = 0;
   //:INTEGER         lID
   int      lID = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:// GET VIEW lSubreg NAMED "lSubreg"
   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure user login name is not blank and is unique.
   //:szUserName = mSubreg.User.UserName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_0, 'S', 51, mSubreg, "User", "UserName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szUserName = sb_szUserName.toString( );}
   //:lUserNameLth = zGetStringLen( szUserName )
   lUserNameLth = zGetStringLen( szUserName );
   //:TraceLineS( "User Login Name: ", szUserName )
   TraceLineS( "User Login Name: ", szUserName );
   //:TraceLineI( "User Login Name Length: ", lUserNameLth )
   TraceLineI( "User Login Name Length: ", lUserNameLth );
   //:IF lUserNameLth < 1
   if ( lUserNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Subregistrant User",
      //:             "The User Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant User", "The User Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:szAttemptUserName = wWebXfer.Root.AttemptUserName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szAttemptUserName;
      if ( szAttemptUserName == null )
         sb_szAttemptUserName = new StringBuilder( 32 );
      else
         sb_szAttemptUserName = new StringBuilder( szAttemptUserName );
             GetVariableFromAttribute( sb_szAttemptUserName, mi_lTempInteger_1, 'S', 51, wWebXfer, "Root", "AttemptUserName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szAttemptUserName = sb_szAttemptUserName.toString( );}
      //:nRC = zstrcmpi( szUserName, szAttemptUserName )
      nRC = zstrcmpi( szUserName, szAttemptUserName );
      //:IF nRC != 0
      if ( nRC != 0 )
      { 

         //:lControl = zQUAL_STRING + zPOS_FIRST + zQUAL_SCOPE_OI + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zQUAL_SCOPE_OI + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( mSubreg, "User", "UserName", lControl,
         //:                    szUserName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_2 = SetEntityCursor( mSubreg, "User", "UserName", lControl, szUserName, "", "", 0, "", "" );
         if ( lTempInteger_2 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Subregistrant User",
            //:             "The User Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Subregistrant User", "The User Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
            m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
            //:RETURN 2
            if(8==8)return( 2 );

            //:ELSE
         } 
         else
         { 
            //:szUserName = szAttemptUserName
             {StringBuilder sb_szUserName;
            if ( szUserName == null )
               sb_szUserName = new StringBuilder( 32 );
            else
               sb_szUserName = new StringBuilder( szUserName );
                        ZeidonStringCopy( sb_szUserName, 1, 0, szAttemptUserName, 1, 0, 51 );
            szUserName = sb_szUserName.toString( );}
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:mSubreg.User.UserName = szUserName
   SetAttributeFromString( mSubreg, "User", "UserName", szUserName );

   //:// Ensure user first and last names are not blank.
   //:szAttemptUserName = mSubreg.Employee.FirstName
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szAttemptUserName;
   if ( szAttemptUserName == null )
      sb_szAttemptUserName = new StringBuilder( 32 );
   else
      sb_szAttemptUserName = new StringBuilder( szAttemptUserName );
       GetVariableFromAttribute( sb_szAttemptUserName, mi_lTempInteger_3, 'S', 51, mSubreg, "Employee", "FirstName", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szAttemptUserName = sb_szAttemptUserName.toString( );}
   //:lUserNameLth = zGetStringLen( szAttemptUserName )
   lUserNameLth = zGetStringLen( szAttemptUserName );
   //:TraceLineS( "First Name: ", szAttemptUserName )
   TraceLineS( "First Name: ", szAttemptUserName );
   //:TraceLineI( "First Name Length: ", lUserNameLth )
   TraceLineI( "First Name Length: ", lUserNameLth );
   //:IF lUserNameLth < 1
   if ( lUserNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Subregistrant User",
      //:             "The user First Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant User", "The user First Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szAttemptUserName = mSubreg.Employee.LastName
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szAttemptUserName;
   if ( szAttemptUserName == null )
      sb_szAttemptUserName = new StringBuilder( 32 );
   else
      sb_szAttemptUserName = new StringBuilder( szAttemptUserName );
       GetVariableFromAttribute( sb_szAttemptUserName, mi_lTempInteger_4, 'S', 51, mSubreg, "Employee", "LastName", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szAttemptUserName = sb_szAttemptUserName.toString( );}
   //:lUserNameLth = zGetStringLen( szAttemptUserName )
   lUserNameLth = zGetStringLen( szAttemptUserName );
   //:TraceLineS( "Last Name: ", szAttemptUserName )
   TraceLineS( "Last Name: ", szAttemptUserName );
   //:TraceLineI( "Last Name Length: ", lUserNameLth )
   TraceLineI( "Last Name Length: ", lUserNameLth );
   //:IF lUserNameLth < 1
   if ( lUserNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Subregistrant User",
      //:             "The user Last Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant User", "The user Last Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:AcceptSubobject( mSubreg, "Employee" )
   AcceptSubobject( mSubreg, "Employee" );

   //:Commit mSubreg
   RESULT = CommitObjectInstance( mSubreg );

   //:lID = mSubreg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );

   //:// Activate the "selected" subregistrant ... just in case someone added or
   //:// deleted a subregistrant user.
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.Employee WHERE mSubreg.Employee.ID = lID
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "Employee", "ID", lID, "" );
   return( 0 );
// // IF szUserName = "Admin"
// //    SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
// //                             "wSubR", "SubregUsers" )
// // END
// END
} 


//:DIALOG OPERATION
//:InitSubregistrantForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregistrantForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSubreg  REGISTERED AS lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wSubR.InitSubregistrantForUpdate cannot find Transfer View", "" )
      TraceLineS( "wSubR.InitSubregistrantForUpdate cannot find Transfer View", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg != 0
   if ( getView( mSubreg ) != null )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END

   //:// If this is the first time into the system, this is the administrator.
   //:// Otherwise, just create a new new registrant.
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lSubreg.Subregistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSubreg, "Subregistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:IF mSubreg.Subregistrant DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mSubreg, "Subregistrant" );
   if ( lTempInteger_1 != 0 )
   { 

      //:// This should never happen!!!
      //:CREATE ENTITY mSubreg.Subregistrant
      RESULT = CreateEntity( mSubreg, "Subregistrant", zPOS_AFTER );
      //:CREATE ENTITY mSubreg.SubregOrganization
      RESULT = CreateEntity( mSubreg, "SubregOrganization", zPOS_AFTER );
      //:CREATE ENTITY mSubreg.PhysicalAddress
      RESULT = CreateEntity( mSubreg, "PhysicalAddress", zPOS_AFTER );
      //:CREATE ENTITY mSubreg.MailingAddress
      RESULT = CreateEntity( mSubreg, "MailingAddress", zPOS_AFTER );
      //:CREATE ENTITY mSubreg.ContactPerson
      RESULT = CreateEntity( mSubreg, "ContactPerson", zPOS_AFTER );
      //:mSubreg.PhysicalAddress.Country = "USA"
      SetAttributeFromString( mSubreg, "PhysicalAddress", "Country", "USA" );
      //:mSubreg.MailingAddress.Country = "USA"
      SetAttributeFromString( mSubreg, "MailingAddress", "Country", "USA" );
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

   //:IF mSubreg.PhysicalAddress DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( mSubreg, "PhysicalAddress" );
   if ( lTempInteger_2 != 0 )
   { 
      //:CREATE ENTITY mSubreg.PhysicalAddress
      RESULT = CreateEntity( mSubreg, "PhysicalAddress", zPOS_AFTER );
      //:mSubreg.PhysicalAddress.Country = "USA"
      SetAttributeFromString( mSubreg, "PhysicalAddress", "Country", "USA" );
   } 

   //:END

   //:IF mSubreg.MailingAddress DOES NOT EXIST
   lTempInteger_3 = CheckExistenceOfEntity( mSubreg, "MailingAddress" );
   if ( lTempInteger_3 != 0 )
   { 
      //:CREATE ENTITY mSubreg.MailingAddress
      RESULT = CreateEntity( mSubreg, "MailingAddress", zPOS_AFTER );
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mSubreg, "MailingAddress",
      //:                             mSubreg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mSubreg, "MailingAddress", mSubreg, "PhysicalAddress", zSET_NOTNULL );
   } 

   //:END

   //:IF mSubreg.ContactPerson DOES NOT EXIST
   lTempInteger_4 = CheckExistenceOfEntity( mSubreg, "ContactPerson" );
   if ( lTempInteger_4 != 0 )
   { 
      //:CREATE ENTITY mSubreg.ContactPerson
      RESULT = CreateEntity( mSubreg, "ContactPerson", zPOS_AFTER );
   } 

   //:END

   //:wWebXfer.Root.AttemptLoginName = mSubreg.SubregOrganization.LoginName
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptLoginName", mSubreg, "SubregOrganization", "LoginName" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:CreateTemporalSubobjectVersion( mSubreg, "Subregistrant" )
   CreateTemporalSubobjectVersion( mSubreg, "Subregistrant" );
   //:// CreateTemporalSubobjectVersion( mSubreg, "SubregOrganization" )  // done by "Registrant"
   //:// CreateTemporalSubobjectVersion( mSubreg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "ContactPerson" )

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregistrant( VIEW ViewToWindow )

//:   VIEW mSubreg BASED ON LOD mSubreg
public int 
CancelUpdateSubregistrant( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:// CancelSubobject( mSubreg, "User" )
   //:// CancelSubobject( mSubreg, "Person" )
   //:// CancelSubobject( mSubreg, "PhysicalAddress" )
   //:// CancelSubobject( mSubreg, "MailingAddress" )
   //:CancelSubobject( mSubreg, "Subregistrant" )
   CancelSubobject( mSubreg, "Subregistrant" );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListSubregistrants( VIEW ViewToWindow )

//:   VIEW qSubreg BASED ON LOD qSubreg
public int 
InitListSubregistrants( View     ViewToWindow )
{
   zVIEW    qSubreg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW qSubreg NAMED "qSubreg"
   RESULT = GetViewByName( qSubreg, "qSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF  qSubreg != 0
   if ( getView( qSubreg ) != null )
   { 
      //:DropObjectInstance( qSubreg )
      DropObjectInstance( qSubreg );
   } 

   //:END

   //:// Activate all subregistrants at the root level.
   //:ACTIVATE qSubreg ROOTONLYMULTIPLE
   RESULT = ActivateObjectInstance( qSubreg, "qSubreg", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW qSubreg "qSubreg"
   SetNameForView( qSubreg, "qSubreg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregUserForInsert( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregUserForInsert( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:// VIEW qSubreg  BASED ON LOD  qSubreg

   //:wWebXfer.Root.AttemptUserName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptUserName", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:IF mSubreg = 0 OR mSubreg.Subregistrant DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( mSubreg, "Subregistrant" );
   if ( mSubreg == null || lTempInteger_0 != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Initialize Subregistrant User",
      //:             "The registrant list is empty.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Initialize Subregistrant User", "The registrant list is empty.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// GET VIEW qSubreg NAMED "qSubreg"
   //:// IF qSubreg != 0
   //://    DropObjectInstance( qSubreg )
   //:// END
   //://
   //:// ACTIVATE qSubreg WHERE qSubreg.Subregistrant.ID = mSubreg.Subregistrant.ID
   //:// NAME VIEW qSubreg "qSubreg"
   //://
   //:// IF qSubreg.Subregistrant DOES NOT EXIST
   //://    TraceLineI( "InitSubregUser cannot activate Subregistrant: ",
   //://                mSubreg.Subregistrant.ID )
   //://    MessageSend( ViewToWindow, "", "Initialize Subregistrant User",
   //://                 "Cannot activate Subregistrant.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    DropObjectInstance( qSubreg )
   //://    RETURN 2
   //:// END
   //://
   //:// DropObjectInstance( qSubreg )


   //:CreateTemporalEntity( mSubreg, "Employee", zPOS_FIRST )
   CreateTemporalEntity( mSubreg, "Employee", zPOS_FIRST );
   //:CreateTemporalEntity( mSubreg, "User", zPOS_FIRST )
   CreateTemporalEntity( mSubreg, "User", zPOS_FIRST );
   //:CreateTemporalEntity( mSubreg, "Address", zPOS_FIRST )
   CreateTemporalEntity( mSubreg, "Address", zPOS_FIRST );

   //:mSubreg.User.Status = "B"  // beginner
   SetAttributeFromString( mSubreg, "User", "Status", "B" );
   //:mSubreg.Address.Country = "USA"
   SetAttributeFromString( mSubreg, "Address", "Country", "USA" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSubreg  REGISTERED AS lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:INTEGER       lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wSubR.InitSubregistrant cannot find Transfer View", "" )
      TraceLineS( "wSubR.InitSubregistrant cannot find Transfer View", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

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
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

   //:IF mSubreg.MailingAddress DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mSubreg, "MailingAddress" );
   if ( lTempInteger_1 != 0 )
   { 
      //:CREATE ENTITY mSubreg.MailingAddress
      RESULT = CreateEntity( mSubreg, "MailingAddress", zPOS_AFTER );
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mSubreg, "MailingAddress",
      //:                             mSubreg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mSubreg, "MailingAddress", mSubreg, "PhysicalAddress", zSET_NOTNULL );
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

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DesignSubregProductLabel( VIEW ViewToWindow )

public int 
DesignSubregProductLabel( View     ViewToWindow )
{

   return( 0 );
//    // Just here to cause selection processing
// END
} 


//:DIALOG OPERATION
//:UpdateSubregLabelContent( VIEW ViewToWindow )

//:   SHORT   nRC
public int 
UpdateSubregLabelContent( View     ViewToWindow )
{
   int      nRC = 0;


   //:// If there was a problem in the accept, the messages and action behavior
   //:// should have been set in the accept code.
   //:nRC = AcceptUpdateSubregProduct( ViewToWindow )
   nRC = AcceptUpdateSubregProduct( ViewToWindow );

   //:// Nothing to do except go ...
   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:AcceptNewSubregLabelContent( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
AcceptNewSubregLabelContent( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC  REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lLabelID
   int      lLabelID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:lID = mSubreg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, mSubreg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:lLabelID = mSubreg.SubregLabelContent.ID
   {MutableInt mi_lLabelID = new MutableInt( lLabelID );
       GetIntegerFromAttribute( mi_lLabelID, mSubreg, "SubregLabelContent", "ID" );
   lLabelID = mi_lLabelID.intValue( );}

   //:AcceptSubobject( mSubreg, "SubregLabelContent" )
   AcceptSubobject( mSubreg, "SubregLabelContent" );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:COMMIT mSubLC
   RESULT = CommitObjectInstance( mSubLC );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:DropObjectInstance( mSubLC )
   DropObjectInstance( mSubLC );
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:SET CURSOR FIRST mSubreg.SubregLabelContent WHERE mSubreg.SubregLabelContent.ID = lLabelID
   RESULT = SetCursorFirstEntityByInteger( mSubreg, "SubregLabelContent", "ID", lLabelID, "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmDeleteSubregUser( VIEW ViewToWindow )

//:   VIEW mCurrentUser BASED ON LOD  mUser
public int 
ConfirmDeleteSubregUser( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// We are activating a User instance for delete.
   //:ACTIVATE mCurrentUser WHERE mCurrentUser.User.ID = mSubreg.User.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, mSubreg, "User", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mCurrentUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );

   //:DELETE ENTITY mSubreg.User
   RESULT = DeleteEntity( mSubreg, "User", zPOS_NEXT );
   //:DELETE ENTITY mSubreg.Employee
   RESULT = DeleteEntity( mSubreg, "Employee", zPOS_NEXT );
   //:DELETE ENTITY mCurrentUser.User
   RESULT = DeleteEntity( mCurrentUser, "User", zPOS_NEXT );

   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:COMMIT mCurrentUser
   RESULT = CommitObjectInstance( mCurrentUser );

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
   return( 0 );
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
   o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_0, lID );
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
//:InitListSubregUsers( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitListSubregUsers( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSubreg  REGISTERED AS lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
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
   o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

   //:wWebXfer.Root.Banner4 = mSubreg.Subregistrant.dNameEPA_Number
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner4", mSubreg, "Subregistrant", "dNameEPA_Number" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReturnFromProductList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ReturnFromProductList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW qPrimReg NAMED "qPrimRegLogin"
   RESULT = GetViewByName( qPrimReg, "qPrimRegLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wStartUp", "AdminListSubregistrants" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminListSubregistrants" );
      //:ELSE
   } 
   else
   { 
      //:IF wWebXfer.Root.LoginName = "Admin"
      if ( CompareAttributeToString( wWebXfer, "Root", "LoginName", "Admin" ) == 0 )
      { 
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //:                      "wSubR", "SubregDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wSubR", "SubregDetail" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregDetail( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregDetail( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lSubreg  REGISTERED AS lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:INTEGER       lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wSubR.InitSubregDetail cannot find Transfer View", "" )
      TraceLineS( "wSubR.InitSubregDetail cannot find Transfer View", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

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

   //:IF mSubreg.MailingAddress DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mSubreg, "MailingAddress" );
   if ( lTempInteger_1 != 0 )
   { 
      //:CREATE ENTITY mSubreg.MailingAddress
      RESULT = CreateEntity( mSubreg, "MailingAddress", zPOS_AFTER );
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mSubreg, "MailingAddress",
      //:                             mSubreg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mSubreg, "MailingAddress", mSubreg, "PhysicalAddress", zSET_NOTNULL );
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

   //:SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wSubR", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReturnFromSubregUsersList( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ReturnFromSubregUsersList( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW qPrimReg NAMED "qPrimRegLogin"
   RESULT = GetViewByName( qPrimReg, "qPrimRegLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wStartUp", "AdminListSubregistrants" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminListSubregistrants" );
      //:ELSE
   } 
   else
   { 
      //:IF wWebXfer.Root.LoginName = "Admin"
      if ( CompareAttributeToString( wWebXfer, "Root", "LoginName", "Admin" ) == 0 )
      { 
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //:                         "wSubR", "SubregDetail" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wSubR", "SubregDetail" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 



}
