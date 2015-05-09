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

/**
   @author QuinSoft
**/

public class wStartUp_Dialog extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public wStartUp_Dialog( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:DIALOG OPERATION
//:ProcessUserLogin( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ProcessUserLogin( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW pePamms  REGISTERED AS pePamms
   zVIEW    pePamms = new zVIEW( );
   //:VIEW mePamms  BASED ON LOD  mePamms
   zVIEW    mePamms = new zVIEW( );
   //:VIEW sePamms  BASED ON LOD  sePamms
   zVIEW    sePamms = new zVIEW( );
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:VIEW mOrganiz BASED ON LOD  mOrganiz
   zVIEW    mOrganiz = new zVIEW( );
   //:VIEW mOrganizInit BASED ON LOD mOrganiz
   zVIEW    mOrganizInit = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:STRING ( 256  ) szMessage
   String   szMessage = null;
   //:STRING (  50  ) szLoginName
   String   szLoginName = null;
   //:STRING (  50  ) szUserName
   String   szUserName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:STRING (   1  ) szKeyRole
   String   szKeyRole = null;
   //:INTEGER       lControl
   int      lControl = 0;
   //:INTEGER       lID
   int      lID = 0;
   //:SHORT         nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   int      lTempInteger_10 = 0;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   int      lTempInteger_16 = 0;
   String   szTempString_1 = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( pePamms, "pePamms", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wStartUp.ProcessUserLogin cannot find Transfer View", "" )
      TraceLineS( "wStartUp.ProcessUserLogin cannot find Transfer View", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:wWebXfer.Root.KeyRole = "U"  // U is for Unknown (so far)
   SetAttributeFromString( wWebXfer, "Root", "KeyRole", "U" );
   //:IF pePamms.ePamms DOES NOT EXIST OR pePamms.ePamms.ID != "1"
   lTempInteger_0 = CheckExistenceOfEntity( pePamms, "ePamms" );
   if ( lTempInteger_0 != 0 || CompareAttributeToString( pePamms, "ePamms", "ID", "1" ) != 0 )
   { 
      //:// No administrator found!  We bootstrap here.
      //:TraceLineS( "ProcessUserLogin: ", "No Administrator found!!!" )
      TraceLineS( "ProcessUserLogin: ", "No Administrator found!!!" );
      //:ACTIVATE mePamms EMPTY
      RESULT = ActivateEmptyObjectInstance( mePamms, "mePamms", ViewToWindow, zSINGLE );
      //:NAME VIEW mePamms "mePamms"
      SetNameForView( mePamms, "mePamms", null, zLEVEL_TASK );
      //:CREATE ENTITY mePamms.ePamms
      RESULT = CreateEntity( mePamms, "ePamms", zPOS_AFTER );
      //:CREATE ENTITY mePamms.PrimaryRegistrant 
      RESULT = CreateEntity( mePamms, "PrimaryRegistrant", zPOS_AFTER );
      //:CREATE ENTITY mePamms.Organization
      RESULT = CreateEntity( mePamms, "Organization", zPOS_AFTER );
      //:mePamms.ePamms.ID = 1
      SetAttributeFromInteger( mePamms, "ePamms", "ID", 1 );
      //:mePamms.Organization.Name = "ePamms Administrator"
      SetAttributeFromString( mePamms, "Organization", "Name", "ePamms Administrator" );
      //:mePamms.Organization.LoginName = "Admin"
      SetAttributeFromString( mePamms, "Organization", "LoginName", "Admin" );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wStartUp", "AdminNewAdministrator" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminNewAdministrator" );
      //:DropObjectInstance( pePamms )
      DropObjectInstance( pePamms );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END

   //:// OK ... we know that Admin exists ... if there are no Primary Registrants, the only login possible is Admin, which can
   //:// create Primary Registrants.  If there are no Subregistrants for a Primary Registrant, the only login possible for the
   //:// specified Primary Registrant is the administrator for that Primary Registrant, which can create Subregistrants.  If
   //:// there are no Users for a Subregistrant, the only login possible for the specified Subregistrant is the administrator
   //:// for that Subregistrant.

   //:DisplayObjectInstance( pePamms, "", "" )
   DisplayObjectInstance( pePamms, "", "" );
   //:DisplayEntityInstance( pePamms, "Organization" )
   DisplayEntityInstance( pePamms, "Organization" );

   //:szLoginName = wWebXfer.Root.AttemptLoginName  // e.g. Lonza (a primary registrant) or ATP (Alpha Tech Pet - a subregistrant)
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szLoginName;
   if ( szLoginName == null )
      sb_szLoginName = new StringBuilder( 32 );
   else
      sb_szLoginName = new StringBuilder( szLoginName );
       GetVariableFromAttribute( sb_szLoginName, mi_lTempInteger_1, 'S', 51, wWebXfer, "Root", "AttemptLoginName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szLoginName = sb_szLoginName.toString( );}
   //:IF pePamms.Organization.LoginName = "Admin"  // cannot use szLoginName since we need a case insensitive comparison
   if ( CompareAttributeToString( pePamms, "Organization", "LoginName", "Admin" ) == 0 )
   { 
      //:// SET CURSOR FIRST pePamms.Organization WHERE pePamms.Organization.LoginName = szLoginName
      //:RESULT = SetCursorFirstEntityByString( pePamms, "Organization", "LoginName", szLoginName, "ePamms" )
      RESULT = SetCursorFirstEntityByString( pePamms, "Organization", "LoginName", szLoginName, "ePamms" );

      //:IF RESULT < 0
      if ( RESULT < 0 )
      { 
         //:TraceLineS( "Login Organization not found within Admin: ", szLoginName )
         TraceLineS( "Login Organization not found within Admin: ", szLoginName );
         //:MessageSend( ViewToWindow, "", "User Login",
         //:             "Invalid User Login.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "User Login", "Invalid User Login.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:// Note that Activate always returns at least an empty view.
      //:ACTIVATE qOrganiz WHERE qOrganiz.Organization.LoginName = szLoginName
      o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, szLoginName );
      RESULT = ActivateObjectInstance( qOrganiz, "qOrganiz", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:ELSE
   } 
   else
   { 
      //:// Within a Primary Registrant (not Admin)
      //:ACTIVATE sePamms WHERE sePamms.PrimaryRegistrant.ID = pePamms.PrimaryRegistrant.ID
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, pePamms, "PrimaryRegistrant", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
      RESULT = ActivateObjectInstance( sePamms, "sePamms", ViewToWindow, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW sePamms "sePamms"
      SetNameForView( sePamms, "sePamms", null, zLEVEL_TASK );
      //:DisplayObjectInstance( sePamms, "", "" )
      DisplayObjectInstance( sePamms, "", "" );

      //:// We cannot use SET CURSOR FIRST WHERE since we need a case insensitive comparison.
      //:// SET CURSOR FIRST sePamms.Subregistrant WHERE sePamms.Organization.LoginName = szLoginName
      //:TraceLineS( "Looking for Subregistrant Login: ", wWebXfer.Root.AttemptUserName )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, wWebXfer, "Root", "AttemptUserName" );
      szTempString_0 = sb_szTempString_0.toString( );}
      TraceLineS( "Looking for Subregistrant Login: ", szTempString_0 );
      //:RESULT = SetCursorFirstEntityByString( sePamms, "Organization", "LoginName", szLoginName, "PrimaryRegistrant" )
      RESULT = SetCursorFirstEntityByString( sePamms, "Organization", "LoginName", szLoginName, "PrimaryRegistrant" );
      //:/*
      //:SET CURSOR FIRST sePamms.Subregistrant
      //:LOOP WHILE RESULT >= 0 AND wWebXfer.Root.AttemptUserName != sePamms.Organization.LoginName // case insensitive comparison
      //:   SET CURSOR NEXT sePamms.Subregistrant
      //:END
      //:*/
      //:DisplayEntityInstance( sePamms, "Organization" )
      DisplayEntityInstance( sePamms, "Organization" );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //: ACTIVATE qOrganiz WHERE qOrganiz.Organization.ID = sePamms.Organization.ID
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                   GetIntegerFromAttribute( mi_lTempInteger_3, sePamms, "Organization", "ID" );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );}
         o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2, lTempInteger_3 );
         RESULT = ActivateObjectInstance( qOrganiz, "qOrganiz", ViewToWindow, vTempViewVar_2, zSINGLE );
         DropView( vTempViewVar_2 );
         //:DropObjectInstance( sePamms )
         DropObjectInstance( sePamms );
         //:ELSE
      } 
      else
      { 
         //:// Organization not found!
         //:szMessage = pePamms.Organization.Name
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                   GetVariableFromAttribute( sb_szMessage, mi_lTempInteger_4, 'S', 257, pePamms, "Organization", "Name", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szMessage = sb_szMessage.toString( );}
         //:szMessage = szMessage + "    Login: "
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, "    Login: ", 1, 0, 257 );
         szMessage = sb_szMessage.toString( );}
         //:szMessage = szMessage + szLoginName
          {StringBuilder sb_szMessage;
         if ( szMessage == null )
            sb_szMessage = new StringBuilder( 32 );
         else
            sb_szMessage = new StringBuilder( szMessage );
                  ZeidonStringConcat( sb_szMessage, 1, 0, szLoginName, 1, 0, 257 );
         szMessage = sb_szMessage.toString( );}
         //:TraceLineS( "Login Organization not found within Primary Registrant: ", szMessage )
         TraceLineS( "Login Organization not found within Primary Registrant: ", szMessage );
         //:MessageSend( ViewToWindow, "", "User Login",
         //:             "Invalid User Login.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "User Login", "Invalid User Login.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:DropObjectInstance( sePamms )
         DropObjectInstance( sePamms );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:NAME VIEW qOrganiz "qOrganizLogin"
   SetNameForView( qOrganiz, "qOrganizLogin", null, zLEVEL_TASK );
   //:DisplayObjectInstance( qOrganiz, "", "" )
   DisplayObjectInstance( qOrganiz, "", "" );
   //:IF qOrganiz.Organization DOES NOT EXIST
   lTempInteger_5 = CheckExistenceOfEntity( qOrganiz, "Organization" );
   if ( lTempInteger_5 != 0 )
   { 
      //:// Organization not found!
      //:TraceLineS( "Login Organization not found: ", szLoginName )
      TraceLineS( "Login Organization not found: ", szLoginName );
      //:MessageSend( ViewToWindow, "", "User Login",
      //:             "Invalid User Login.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "User Login", "Invalid User Login.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( qOrganiz )
      DropObjectInstance( qOrganiz );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:wWebXfer.Root.LoginName = qOrganiz.Organization.LoginName // e.g. Lonza
   SetAttributeFromAttribute( wWebXfer, "Root", "LoginName", qOrganiz, "Organization", "LoginName" );
   //:szUserName = wWebXfer.Root.AttemptUserName  // e.g. Admin
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_6, 'S', 51, wWebXfer, "Root", "AttemptUserName", "", 0 );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );
   szUserName = sb_szUserName.toString( );}
   //:szKeyRole = wWebXfer.Root.KeyRole
   {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
   StringBuilder sb_szKeyRole;
   if ( szKeyRole == null )
      sb_szKeyRole = new StringBuilder( 32 );
   else
      sb_szKeyRole = new StringBuilder( szKeyRole );
       GetVariableFromAttribute( sb_szKeyRole, mi_lTempInteger_7, 'S', 2, wWebXfer, "Root", "KeyRole", "", 0 );
   lTempInteger_7 = mi_lTempInteger_7.intValue( );
   szKeyRole = sb_szKeyRole.toString( );}

   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_8, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_8 = mi_lTempInteger_8.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:nRC = 0  // initialize to normal processing
   nRC = 0;

   //:// Cannot use szLoginName (in place of wWebXfer.Root.AttemptUserName) since we need a case insensitive comparison.
   //:IF wWebXfer.Root.AttemptUserName = "Admin" // logging in as user Admin for specified registrant
   if ( CompareAttributeToString( wWebXfer, "Root", "AttemptUserName", "Admin" ) == 0 )
   { 

      //:wWebXfer.Root.LoginUser = "Admin"
      SetAttributeFromString( wWebXfer, "Root", "LoginUser", "Admin" );

      //:// Match the password.
      //:nRC = CompareAttributeToString( qOrganiz, "Organization", "AdministratorPassword", szAttemptPassword )
      nRC = CompareAttributeToString( qOrganiz, "Organization", "AdministratorPassword", szAttemptPassword );
      //:IF nRC != 0
      if ( nRC != 0 )
      { 

         //:// Remove these lines prior to deployment!!!
         //:TraceLineS( "//////* Invalid Login Password: ", szAttemptPassword )
         TraceLineS( "//////* Invalid Login Password: ", szAttemptPassword );
         //:szConfirmPassword = qOrganiz.Organization.AdministratorPassword
         {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
         StringBuilder sb_szConfirmPassword;
         if ( szConfirmPassword == null )
            sb_szConfirmPassword = new StringBuilder( 32 );
         else
            sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
                   GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_9, 'S', 129, qOrganiz, "Organization", "AdministratorPassword", "", 0 );
         lTempInteger_9 = mi_lTempInteger_9.intValue( );
         szConfirmPassword = sb_szConfirmPassword.toString( );}
         //:wWebXfer.Root.TracePassword = szAttemptPassword
         SetAttributeFromString( wWebXfer, "Root", "TracePassword", szAttemptPassword );
         //:szAttemptPassword = wWebXfer.Root.TracePassword
         {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
         StringBuilder sb_szAttemptPassword;
         if ( szAttemptPassword == null )
            sb_szAttemptPassword = new StringBuilder( 32 );
         else
            sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
                   GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_10, 'S', 129, wWebXfer, "Root", "TracePassword", "", 0 );
         lTempInteger_10 = mi_lTempInteger_10.intValue( );
         szAttemptPassword = sb_szAttemptPassword.toString( );}
         //:TraceLineS( "//////*+++++ CurrentUser: ", szConfirmPassword )
         TraceLineS( "//////*+++++ CurrentUser: ", szConfirmPassword );
         //:TraceLineS( "//////*+++++ Attempted  : ", szAttemptPassword )
         TraceLineS( "//////*+++++ Attempted  : ", szAttemptPassword );
         //:// End of: Remove these lines prior to deployment!!!

         //:MessageSend( ViewToWindow, "", "Login",
         //:             "Invalid Administrator Login",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Login", "Invalid Administrator Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:DropObjectInstance( qOrganiz )
         DropObjectInstance( qOrganiz );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:// Cannot use szLoginUser (in place of wWebXfer.Root.AttemptUserName) since we need a case insensitive comparison.
      //:IF qOrganiz.Organization.LoginName = "Admin"
      if ( CompareAttributeToString( qOrganiz, "Organization", "LoginName", "Admin" ) == 0 )
      { 

         //:wWebXfer.Root.LoginName = "Admin"
         SetAttributeFromString( wWebXfer, "Root", "LoginName", "Admin" );
         //:wWebXfer.Root.KeyRole = "P" // Primary registrant
         SetAttributeFromString( wWebXfer, "Root", "KeyRole", "P" );

         //:ACTIVATE lPrimReg
         RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, 0, zSINGLE );
         //:NAME VIEW lPrimReg "lPrimReg"
         SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow,
         //:                         "wSystem", "ListPrimaryRegistrants" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow, "wSystem", "ListPrimaryRegistrants" );
         //:nRC = 1  // not going to usual window
         nRC = 1;

         //:ELSE
      } 
      else
      { 

         //:IF qOrganiz.PrimaryRegistrant EXISTS
         lTempInteger_11 = CheckExistenceOfEntity( qOrganiz, "PrimaryRegistrant" );
         if ( lTempInteger_11 == 0 )
         { 
            //:IF qOrganiz.Subregistrant EXISTS
            lTempInteger_12 = CheckExistenceOfEntity( qOrganiz, "Subregistrant" );
            if ( lTempInteger_12 == 0 )
            { 
               //:wWebXfer.Root.KeyRole = "D"  // Dual registrant
               SetAttributeFromString( wWebXfer, "Root", "KeyRole", "D" );
               //:ELSE
            } 
            else
            { 
               //:wWebXfer.Root.KeyRole = "P"  // Primary rgistrant
               SetAttributeFromString( wWebXfer, "Root", "KeyRole", "P" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF qOrganiz.Subregistrant EXISTS
            lTempInteger_13 = CheckExistenceOfEntity( qOrganiz, "Subregistrant" );
            if ( lTempInteger_13 == 0 )
            { 
               //:wWebXfer.Root.KeyRole = "S"  // Subregistrant (for now)
               SetAttributeFromString( wWebXfer, "Root", "KeyRole", "S" );
               //:ELSE
            } 
            else
            { 
               //:wWebXfer.Root.KeyRole = qOrganiz.Organization.Role
               SetAttributeFromAttribute( wWebXfer, "Root", "KeyRole", qOrganiz, "Organization", "Role" );
            } 

            //:END
         } 

         //:END
      } 


      //:END

      //:wWebXfer.Root.UserStatus = "X"  // expert
      SetAttributeFromString( wWebXfer, "Root", "UserStatus", "X" );

      //:ELSE
   } 
   else
   { 

      //:lControl = zQUAL_STRING + zPOS_FIRST + zQUAL_SCOPE_OI + zTEST_CSR_RESULT
      lControl = zQUAL_STRING + zPOS_FIRST + zQUAL_SCOPE_OI + zTEST_CSR_RESULT;
      //:IF SetEntityCursor( qOrganiz, "User", "UserName", lControl,
      //:                    szUserName, "", "", 0, "", "" ) < zCURSOR_SET
      lTempInteger_14 = SetEntityCursor( qOrganiz, "User", "UserName", lControl, szUserName, "", "", 0, "", "" );
      if ( lTempInteger_14 < zCURSOR_SET )
      { 
         //:// Organization user not found!
         //:TraceLineS( "Login User not found: ", szUserName )
         TraceLineS( "Login User not found: ", szUserName );
         //:MessageSend( ViewToWindow, "", "User Login",
         //:             "Invalid User Login.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "User Login", "Invalid User Login.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:DropObjectInstance( qOrganiz )
         DropObjectInstance( qOrganiz );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END

      //:// Match the password.
      //:nRC = CompareAttributeToString( qOrganiz, "User", "UserPassword", szAttemptPassword )
      nRC = CompareAttributeToString( qOrganiz, "User", "UserPassword", szAttemptPassword );
      //:IF nRC != 0
      if ( nRC != 0 )
      { 

         //:TraceLineS( "//////* Invalid Login Password: ", szAttemptPassword )
         TraceLineS( "//////* Invalid Login Password: ", szAttemptPassword );
         //:MessageSend( ViewToWindow, "", "Login",
         //:             "Invalid User Login",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Login", "Invalid User Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:DropObjectInstance( qOrganiz )
         DropObjectInstance( qOrganiz );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:wWebXfer.Root.LoginName = qOrganiz.User.UserName
      SetAttributeFromAttribute( wWebXfer, "Root", "LoginName", qOrganiz, "User", "UserName" );
      //:wWebXfer.Root.KeyRole = qOrganiz.Organization.Role
      SetAttributeFromAttribute( wWebXfer, "Root", "KeyRole", qOrganiz, "Organization", "Role" );
      //:wWebXfer.Root.UserStatus = qOrganiz.User.Status
      SetAttributeFromAttribute( wWebXfer, "Root", "UserStatus", qOrganiz, "User", "Status" );
   } 


   //:END

   //:GET VIEW mOrganizInit NAMED "mOrganizInit"
   RESULT = GetViewByName( mOrganizInit, "mOrganizInit", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mOrganiz WHERE mOrganiz.Organization.ID = qOrganiz.Organization.ID
   {MutableInt mi_lTempInteger_15 = new MutableInt( lTempInteger_15 );
       GetIntegerFromAttribute( mi_lTempInteger_15, qOrganiz, "Organization", "ID" );
   lTempInteger_15 = mi_lTempInteger_15.intValue( );}
   o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_3, lTempInteger_15 );
   RESULT = ActivateObjectInstance( mOrganiz, "mOrganiz", ViewToWindow, vTempViewVar_3, zSINGLE );
   DropView( vTempViewVar_3 );
   //:DisplayObjectInstance( mOrganiz, "", "" )
   DisplayObjectInstance( mOrganiz, "", "" );
   //:NAME VIEW mOrganiz "mOrganiz"
   SetNameForView( mOrganiz, "mOrganiz", null, zLEVEL_TASK );
   //:IF mOrganizInit != 0
   if ( getView( mOrganizInit ) != null )
   { 
      //:FOR EACH mOrganizInit.Feedback
      RESULT = SetCursorFirstEntity( mOrganizInit, "Feedback", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY mOrganiz.Feedback LAST
         RESULT = CreateEntity( mOrganiz, "Feedback", zPOS_LAST );
         //:szLoginName = wWebXfer.Root.LoginName
         {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
         StringBuilder sb_szLoginName;
         if ( szLoginName == null )
            sb_szLoginName = new StringBuilder( 32 );
         else
            sb_szLoginName = new StringBuilder( szLoginName );
                   GetVariableFromAttribute( sb_szLoginName, mi_lTempInteger_16, 'S', 51, wWebXfer, "Root", "LoginName", "", 0 );
         lTempInteger_16 = mi_lTempInteger_16.intValue( );
         szLoginName = sb_szLoginName.toString( );}
         //:mOrganizInit.Feedback.UserId = szLoginName
         SetAttributeFromString( mOrganizInit, "Feedback", "UserId", szLoginName );
         //:SetMatchingAttributesByName( mOrganiz, "Feedback",
         //:                             mOrganizInit, "Feedback", zSET_NOTNULL )
         SetMatchingAttributesByName( mOrganiz, "Feedback", mOrganizInit, "Feedback", zSET_NOTNULL );
         //:COMMIT mOrganiz
         RESULT = CommitObjectInstance( mOrganiz );
         RESULT = SetCursorNextEntity( mOrganizInit, "Feedback", "" );
      } 

      //:END

      //:DropObjectInstance( mOrganizInit )
      DropObjectInstance( mOrganizInit );
   } 

   //:END

   //:SfSetUserIdForTask( ViewToWindow, wWebXfer.Root.LoginUser )
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, wWebXfer, "Root", "LoginUser" );
   szTempString_1 = sb_szTempString_1.toString( );}
   SfSetUserIdForTask( ViewToWindow, szTempString_1 );
   //:wWebXfer.Root.AttemptUserName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptUserName", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:// Trace Login.
   //:TraceLineS( "*** Login successful for user: ", szUserName )
   TraceLineS( "*** Login successful for user: ", szUserName );

   //:wWebXfer.Root.Banner1 = qOrganiz.Organization.dLoginUserName
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", qOrganiz, "Organization", "dLoginUserName" );
   //:wWebXfer.Root.Banner2 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner2", "" );
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner4", "" );
   //:wWebXfer.Root.Banner5 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner5", "" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:RETURN nRC
   return( nRC );
// END
} 


private int 
o_fnLocalBuildQual_27( View     vSubtask,
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
o_fnLocalBuildQual_28( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_29( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Person" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Person" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_30( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_6 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_6 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lID );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_32( View     vSubtask,
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
o_fnLocalBuildQual_33( View     vSubtask,
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
o_fnLocalBuildQual_34( View     vSubtask,
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
o_fnLocalBuildQual_35( View     vSubtask,
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
o_fnLocalBuildQual_36( View     vSubtask,
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
o_fnLocalBuildQual_37( View     vSubtask,
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
o_fnLocalBuildQual_38( View     vSubtask,
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
o_fnLocalBuildQual_39( View     vSubtask,
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
o_fnLocalBuildQual_40( View     vSubtask,
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
o_fnLocalBuildQual_10( View     vSubtask,
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
o_fnLocalBuildQual_11( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szRegistrantName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LoginName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szRegistrantName.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_12( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LoginName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Admin" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_13( View     vSubtask,
                       zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ePamms" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ePamms" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_14( View     vSubtask,
                       zVIEW    vQualObject,
                       String   szAttemptRegistrantName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LoginName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szAttemptRegistrantName.toString( ) );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_16( View     vSubtask,
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
o_fnLocalBuildQual_17( View     vSubtask,
                       zVIEW    vQualObject,
                       int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_18( View     vSubtask,
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
o_fnLocalBuildQual_19( View     vSubtask,
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
o_fnLocalBuildQual_20( View     vSubtask,
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
o_fnLocalBuildQual_21( View     vSubtask,
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
o_fnLocalBuildQual_22( View     vSubtask,
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
o_fnLocalBuildQual_23( View     vSubtask,
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
o_fnLocalBuildQual_24( View     vSubtask,
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
o_fnLocalBuildQual_25( View     vSubtask,
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
o_fnLocalBuildQual_26( View     vSubtask,
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
                      int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
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
o_fnLocalBuildQual_1( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szLoginName )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "LoginName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szLoginName.toString( ) );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_3 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_3 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_15 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Organization" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Organization" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_15 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_5( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "PrimaryRegistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "PrimaryRegistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Subregistrant" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Subregistrant" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_2 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_0( View     vSubtask,
                      zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "ePamms" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "ePamms" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:InitColorForAdd( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS  mSubreg
public int 
InitColorForAdd( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:CREATE ENTITY mSubreg.Color
   RESULT = CreateEntity( mSubreg, "Color", zPOS_AFTER );
   //:mSubreg.Color.wkCreated = "Y"
   SetAttributeFromString( mSubreg, "Color", "wkCreated", "Y" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteColor( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS  mSubreg
public int 
DeleteColor( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mSubreg.Color
   RESULT = DeleteEntity( mSubreg, "Color", zPOS_NEXT );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveAndAddNewColor( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS  mSubreg
public int 
SaveAndAddNewColor( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   return( 0 );
// // COMMIT mSubreg
// // CREATE ENTITY mSubreg.Color
// END
} 


//:DIALOG OPERATION
//:SaveColor( VIEW ViewToWindow )

public int 
SaveColor( View     ViewToWindow )
{

   return( 0 );
// // VIEW mSubreg REGISTERED AS  mSubreg
// // COMMIT mSubreg
// END
} 


//:DIALOG OPERATION
//:InitColorForUpdate( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS  mSubreg
public int 
InitColorForUpdate( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:mSubreg.Color.wkCreated = "N"
   SetAttributeFromString( mSubreg, "Color", "wkCreated", "N" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewAdministrator( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelNewAdministrator( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mePamms  REGISTERED AS mePamms
   zVIEW    mePamms = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mePamms, "mePamms", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:DropObjectInstance( mePamms )
   DropObjectInstance( mePamms );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:Template( VIEW ViewToWindow )

public int 
Template( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:NewLLD( VIEW ViewToWindow )

public int 
NewLLD( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:OpenLLD( VIEW ViewToWindow )

public int 
OpenLLD( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SaveLLD( VIEW ViewToWindow )

public int 
SaveLLD( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ExitLLD( VIEW ViewToWindow )

public int 
ExitLLD( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
ContrivedError( View     ViewToWindow )
{

   //:ContrivedError( VIEW ViewToWindow )

   //:MessageSend( ViewToWindow, "", "Cause Error",
   //:             "Contrived Error!!!", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Cause Error", "Contrived Error!!!", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
//:ProductManagement( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ProductManagement( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW qSubreg  BASED ON LOD  qSubreg
   zVIEW    qSubreg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING (   1  ) szKeyRole
   String   szKeyRole = null;
   //:INTEGER       lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wStartUp.ProductManagement cannot find Transfer View", "" )
      TraceLineS( "wStartUp.ProductManagement cannot find Transfer View", "" );
      //:MessageSend( ViewToWindow, "", "Product Management",
      //:             "Invalid Communications View ... being redirected to Login",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Product Management", "Invalid Communications View ... being redirected to Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow,
      //:                         "wStartUp", "UserLogin" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow, "wStartUp", "UserLogin" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END

   //:wWebXfer.Root.Banner1 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner1", "" );
   //:wWebXfer.Root.Banner2 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner2", "" );
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner4", "" );
   //:wWebXfer.Root.Banner5 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner5", "" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:GET VIEW qOrganiz NAMED "qOrganizLogin"
   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qOrganiz = 0
   if ( getView( qOrganiz ) == null )
   { 
      //:TraceLineS( "wStartUp.ProductManagement cannot find Organization View", "" )
      TraceLineS( "wStartUp.ProductManagement cannot find Organization View", "" );
      //:MessageSend( ViewToWindow, "", "Product Management",
      //:             "Invalid Organization View ... being redirected to Login",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Product Management", "Invalid Organization View ... being redirected to Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow,
      //:                         "wStartUp", "UserLogin" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow, "wStartUp", "UserLogin" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu ProductManagement" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu ProductManagement" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW qPrimReg NAMED "qPrimRegLogin"
   RESULT = GetViewByName( qPrimReg, "qPrimRegLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
   } 

   //:END

   //:GET VIEW qSubreg NAMED "qSubregLogin"
   RESULT = GetViewByName( qSubreg, "qSubregLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qSubreg != 0
   if ( getView( qSubreg ) != null )
   { 
      //:DropObjectInstance( qSubreg )
      DropObjectInstance( qSubreg );
   } 

   //:END

   //:GET VIEW qPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( qPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg != 0
   if ( getView( lPrimReg ) != null )
   { 
      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
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

   //:szKeyRole = wWebXfer.Root.KeyRole
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szKeyRole;
   if ( szKeyRole == null )
      sb_szKeyRole = new StringBuilder( 32 );
   else
      sb_szKeyRole = new StringBuilder( szKeyRole );
       GetVariableFromAttribute( sb_szKeyRole, mi_lTempInteger_0, 'S', 2, wWebXfer, "Root", "KeyRole", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szKeyRole = sb_szKeyRole.toString( );}
   //:IF szKeyRole = "D" OR szKeyRole = "P"  // Dual or Primary registrant
   if ( ZeidonStringCompare( szKeyRole, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( szKeyRole, 1, 0, "P", 1, 0, 2 ) == 0 )
   { 

      //:// Cannot use szLoginName since we need a case insensitive comparison.
      //:IF wWebXfer.Root.LoginName = "Admin"
      if ( CompareAttributeToString( wWebXfer, "Root", "LoginName", "Admin" ) == 0 )
      { 

         //:ACTIVATE lPrimReg
         RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, 0, zSINGLE );
         //:NAME VIEW lPrimReg "lPrimReg"
         SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //:                         "wStartUp", "AdminListPrimaryRegistrants" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminListPrimaryRegistrants" );

         //:ELSE
      } 
      else
      { 

         //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = qOrganiz.PrimaryRegistrant.ID
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                   GetIntegerFromAttribute( mi_lTempInteger_1, qOrganiz, "PrimaryRegistrant", "ID" );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );}
         o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_0, lTempInteger_1 );
         RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:NAME VIEW lPrimReg "lPrimReg"
         SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //:                         "wMLC", "ListMasterProducts" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wMLC", "ListMasterProducts" );
      } 

      //:END

      //:wWebXfer.Root.Banner1 = lPrimReg.PrimaryRegistrant.dRegistrantNameID
      SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", lPrimReg, "PrimaryRegistrant", "dRegistrantNameID" );
      //:wWebXfer.Root.UserStatus = "X"  // expert
      SetAttributeFromString( wWebXfer, "Root", "UserStatus", "X" );
      //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantProduct" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantProduct" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }

      //:ELSE  // IF wWebXfer.Root.KeyRole = "S"  // Subregistrant
   } 
   else
   { 

      //://   ACTIVATE mSubreg //WHERE mSubreg.SubregOrganization.Name = wWebXfer.Root.AttemptLoginName 
      //://   NAME VIEW mSubreg "mSubreg"

      //://   ACTIVATE lSubreg WHERE lSubreg.Subregistrant.ID = qOrganiz.Subregistrant.ID
      //://   NAME VIEW lSubreg "lSubreg"
      //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = qOrganiz.Subregistrant.ID
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, qOrganiz, "Subregistrant", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      o_fnLocalBuildQual_6( ViewToWindow, vTempViewVar_1, lTempInteger_2 );
      RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_1, zSINGLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW mSubreg "mSubreg"
      SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wSPLD", "SubregProductsList" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wSPLD", "SubregProductsList" );
      //:wWebXfer.Root.Banner1 = mSubreg.Subregistrant.dNameEPA_Number
      SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", mSubreg, "Subregistrant", "dNameEPA_Number" );
      //:SetDynamicBannerName( ViewToWindow, "wStartUp", "SubregistrantProduct" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "SubregistrantProduct" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 


   //:END

   //:RETURN 1
   return( 1 );
// END
} 


//:DIALOG OPERATION
//:SelectListMasterProducts( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD  lPrimReg
public int 
SelectListMasterProducts( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg.Organization.LoginName = "Admin"
   if ( CompareAttributeToString( lPrimReg, "Organization", "LoginName", "Admin" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "List Master Products",
      //:             "Admin does not have Master Products",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "List Master Products", "Admin does not have Master Products", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
public int 
StateRegistrations( View     ViewToWindow )
{

   //:StateRegistrations( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu StateRegistrations" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu StateRegistrations" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:MessageSend( ViewToWindow, "", "State Registrations",
   //:             "State Registrations not yet implemented.",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "State Registrations", "State Registrations not yet implemented.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
public int 
MarketingFulfillment( View     ViewToWindow )
{

   //:MarketingFulfillment( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu MarketingFulfillment" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu MarketingFulfillment" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:MessageSend( ViewToWindow, "", "Marketing & Fulfillment",
   //:             "Marketing and Fulfillment not yet implemented.",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Marketing & Fulfillment", "Marketing and Fulfillment not yet implemented.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
public int 
SelectSubregProductForUpdate( View     ViewToWindow )
{

   //:SelectSubregProductForUpdate( VIEW ViewToWindow )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewMasterProduct( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD  lPrimReg
public int 
AddNewMasterProduct( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg.Organization.LoginName = "Admin"
   if ( CompareAttributeToString( lPrimReg, "Organization", "LoginName", "Admin" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "New Master Product",
      //:             "Admin does not have Master Products",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Master Product", "Admin does not have Master Products", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:UpdateMasterProduct( VIEW ViewToWindow )

public int 
UpdateMasterProduct( View     ViewToWindow )
{

   return( 0 );
//    // nothing to do here ... just for positioning
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
   //:lMove = 1
   lMove = 1;

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
   //:lMove = 1
   lMove = 1;

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
public int 
SelectSubregProductForDelete( View     ViewToWindow )
{

   //:SelectSubregProductForDelete( VIEW ViewToWindow )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitEmailProspects( VIEW ViewToWindow )
//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitEmailProspects( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.EmailSubjectLine = ""
   SetAttributeFromString( wWebXfer, "Root", "EmailSubjectLine", "" );
   //:wWebXfer.Root.EmailMessage = ""
   SetAttributeFromString( wWebXfer, "Root", "EmailMessage", "" );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectPrimaryRegistrantForDelete( VIEW ViewToWindow )

public int 
SelectPrimaryRegistrantForDelete( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do here other than to get proper position
// END
} 


//:DIALOG OPERATION
//:SelectPrimaryRegistrantForUpdate( VIEW ViewToWindow )

public int 
SelectPrimaryRegistrantForUpdate( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do here other than to get proper position
// END
} 


//:DIALOG OPERATION
//:SelectPrimRegUserForUpdate( VIEW ViewToWindow )

public int 
SelectPrimRegUserForUpdate( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectPrimRegUserForDelete( VIEW ViewToWindow )

public int 
SelectPrimRegUserForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectSubregistrantForUpdate( VIEW ViewToWindow )
//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectSubregistrantForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW lSubreg  BASED ON LOD  lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:// Activate the "selected" Subregistrant.
   //:lID = lPrimReg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:ACTIVATE lSubreg WHERE lSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_40( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lSubreg, "lSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lSubreg "lSubreg"
   SetNameForView( lSubreg, "lSubreg", null, zLEVEL_TASK );
   return( 0 );
//    
// END
} 


//:DIALOG OPERATION
//:SelectSubregUserForDelete( VIEW ViewToWindow )

public int 
SelectSubregUserForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectSubregistrantForDelete( VIEW ViewToWindow )

public int 
SelectSubregistrantForDelete( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregUserForInsert( VIEW ViewToWindow )

public int 
InitSubregUserForInsert( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:PrimaryRegistrantCompanySetup( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
PrimaryRegistrantCompanySetup( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW lSubreg  BASED ON LOD  lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:STRING (   1  ) szKeyRole
   String   szKeyRole = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wStartUp.PrimaryRegistrantCompanySetup cannot find Transfer View", "" )
      TraceLineS( "wStartUp.PrimaryRegistrantCompanySetup cannot find Transfer View", "" );
      //:MessageSend( ViewToWindow, "", "Product Management",
      //:             "Invalid Communications View ... being redirected to Login",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Product Management", "Invalid Communications View ... being redirected to Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow,
      //:                         "wStartUp", "UserLogin" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow, "wStartUp", "UserLogin" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END

   //:wWebXfer.Root.Banner1 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner1", "" );
   //:wWebXfer.Root.Banner2 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner2", "" );
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner4", "" );
   //:wWebXfer.Root.Banner5 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner5", "" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:// We need to set up the proper view and go the the appropriate window.

   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF  lPrimReg != 0
   if ( getView( lPrimReg ) != null )
   { 
      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF  lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu CompanySetup" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu CompanySetup" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW qOrganiz NAMED "qOrganizLogin"
   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   //:szKeyRole = wWebXfer.Root.KeyRole
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szKeyRole;
   if ( szKeyRole == null )
      sb_szKeyRole = new StringBuilder( 32 );
   else
      sb_szKeyRole = new StringBuilder( szKeyRole );
       GetVariableFromAttribute( sb_szKeyRole, mi_lTempInteger_0, 'S', 2, wWebXfer, "Root", "KeyRole", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szKeyRole = sb_szKeyRole.toString( );}

   //:IF qOrganiz.PrimaryRegistrant EXISTS AND szKeyRole != "S"  // not a subregistrant
   lTempInteger_1 = CheckExistenceOfEntity( qOrganiz, "PrimaryRegistrant" );
   if ( lTempInteger_1 == 0 && ZeidonStringCompare( szKeyRole, 1, 0, "S", 1, 0, 2 ) != 0 )
   { 

      //:IF wWebXfer.Root.LoginName = "Admin"
      if ( CompareAttributeToString( wWebXfer, "Root", "LoginName", "Admin" ) == 0 )
      { 
         //:ACTIVATE lPrimReg
         RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, 0, zSINGLE );
         //:NAME VIEW lPrimReg "lPrimReg"
         SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_ReplaceWindowWithModalWindow,
         //:                         "wStartUp", "AdminListPrimaryRegistrants" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ReplaceWindowWithModalWindow, "wStartUp", "AdminListPrimaryRegistrants" );
         //:ELSE
      } 
      else
      { 
         //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = qOrganiz.PrimaryRegistrant.ID
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                   GetIntegerFromAttribute( mi_lTempInteger_2, qOrganiz, "PrimaryRegistrant", "ID" );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );}
         o_fnLocalBuildQual_7( ViewToWindow, vTempViewVar_0, lTempInteger_2 );
         RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
         DropView( vTempViewVar_0 );
         //:NAME VIEW lPrimReg "lPrimReg"
         SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //:                         "wStartUp", "AdminUpdatePrimaryRegistrant" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminUpdatePrimaryRegistrant" );
      } 

      //:END

      //:wWebXfer.Root.Banner1 = lPrimReg.PrimaryRegistrant.dRegistrantNameID
      SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", lPrimReg, "PrimaryRegistrant", "dRegistrantNameID" );

      //:RETURN 1
      if(8==8)return( 1 );

      //:ELSE
   } 
   else
   { 
      //:IF qOrganiz.Subregistrant EXISTS AND szKeyRole != "P"  // not a primary registrant
      lTempInteger_3 = CheckExistenceOfEntity( qOrganiz, "Subregistrant" );
      if ( lTempInteger_3 == 0 && ZeidonStringCompare( szKeyRole, 1, 0, "P", 1, 0, 2 ) != 0 )
      { 

         //:ACTIVATE lSubreg WHERE lSubreg.Subregistrant.ID = qOrganiz.Subregistrant.ID
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                   GetIntegerFromAttribute( mi_lTempInteger_4, qOrganiz, "Subregistrant", "ID" );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );}
         o_fnLocalBuildQual_8( ViewToWindow, vTempViewVar_1, lTempInteger_4 );
         RESULT = ActivateObjectInstance( lSubreg, "lSubreg", ViewToWindow, vTempViewVar_1, zSINGLE );
         DropView( vTempViewVar_1 );
         //:NAME VIEW lSubreg "lSubreg"
         SetNameForView( lSubreg, "lSubreg", null, zLEVEL_TASK );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //:                         "wStartUp", "AdminUpdateSubregistrant" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminUpdateSubregistrant" );
         //:wWebXfer.Root.Banner1 = lSubreg.Subregistrant.dNameEPA_Number
         SetAttributeFromAttribute( wWebXfer, "Root", "Banner1", lSubreg, "Subregistrant", "dNameEPA_Number" );
         //:RETURN 1
         if(8==8)return( 1 );

         //:ELSE
      } 
      else
      { 

         //:MessageSend( ViewToWindow, "", "Primary Registrant Company Setup",
         //:             "Registrant corresponding to role does not exist ... please go to Administration.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Primary Registrant Company Setup", "Registrant corresponding to role does not exist ... please go to Administration.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectListPrimRegUser( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD  lPrimReg
public int 
SelectListPrimRegUser( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg.Organization.LoginName = "Admin"
   if ( CompareAttributeToString( lPrimReg, "Organization", "LoginName", "Admin" ) == 0 )
   { 
      //:MessageSend( ViewToWindow, "", "List Primary Registrant User",
      //:             "Admin does not have Users",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "List Primary Registrant User", "Admin does not have Users", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
//:SubregistrantManagement( VIEW ViewToWindow )

//:   VIEW qOrganiz REGISTERED AS qOrganizLogin
public int 
SubregistrantManagement( View     ViewToWindow )
{
   zVIEW    qOrganiz = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW lSubreg  BASED ON LOD  lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer.Root.KeyRole != "P" // Primary registrant
   if ( CompareAttributeToString( wWebXfer, "Root", "KeyRole", "P" ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant",
      //:             "Must be logged in as a Primary registrant to create new Subregistrants.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant", "Must be logged in as a Primary registrant to create new Subregistrants.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:   
   //:END

   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg != 0
   if ( getView( lPrimReg ) != null )
   { 
      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = qOrganiz.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, qOrganiz, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_16( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:// Need to create the mSubreg view
   //:ACTIVATE lSubreg MULTIPLE WHERE lSubreg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   o_fnLocalBuildQual_17( ViewToWindow, vTempViewVar_1, lTempInteger_1 );
   RESULT = ActivateObjectInstance( lSubreg, "lSubreg", ViewToWindow, vTempViewVar_1, zMULTIPLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lSubreg "lSubreg"
   SetNameForView( lSubreg, "lSubreg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ListSubregistrants( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD lPrimReg
public int 
ListSubregistrants( View     ViewToWindow )
{
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW lSubreg  BASED ON LOD lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:// Need to create the mSubreg view
   //:ACTIVATE lSubreg WHERE lSubreg.Subregistrant.ID = lPrimReg.Subregistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "Subregistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_18( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lSubreg, "lSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lSubreg "lSubreg"
   SetNameForView( lSubreg, "lSubreg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmChangePrimRegPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmChangePrimRegPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
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
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// 1: Ensure old password is correct.
   //:// IF mPrimReg.Organization.AdministratorPassword != wWebXfer.Root.CurrentPassword
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
   //:nRC = CompareAttributeToString( mPrimReg, "Organization", "AdministratorPassword", szAttemptPassword )
   nRC = CompareAttributeToString( mPrimReg, "Organization", "AdministratorPassword", szAttemptPassword );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 

      //:// TraceLineS( "//////* Invalid Current User Password: ", szAttemptPassword )
      //:MessageSend( ViewToWindow, "", "Change Primary Registrant User Password",
      //:             "Current password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Primary Registrant User Password", "Current password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:// 2: Ensure attempted password matches confirm password.
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

   //:mPrimReg.Organization.AdministratorPassword = szConfirmPassword
   SetAttributeFromString( mPrimReg, "Organization", "AdministratorPassword", szConfirmPassword );
   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmChangePrimRegUserPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmChangePrimRegUserPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmChangeSubregUserPassword( VIEW ViewToWindow )

public int 
ConfirmChangeSubregUserPassword( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelChangeSubregPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelChangeSubregPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelChangePrimRegPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelChangePrimRegPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelChangePrimRegUserPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelChangePrimRegUserPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewPrimRegUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewPrimRegUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser BASED ON LOD  mUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mPerson  BASED ON LOD  mPerson
   zVIEW    mPerson = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:IF lPrimReg = 0
   if ( getView( lPrimReg ) == null )
   { 
      //:MessageSend( ViewToWindow, "", "Initialize Primary Registrant User",
      //:             "The registrant list is empty.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Initialize Primary Registrant User", "The registrant list is empty.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:GET VIEW qPrimReg NAMED "qPrimReg"
   RESULT = GetViewByName( qPrimReg, "qPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
   } 

   //:END

   //:ACTIVATE qPrimReg WHERE qPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_26( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( qPrimReg, "qPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW qPrimReg "qPrimReg"
   SetNameForView( qPrimReg, "qPrimReg", null, zLEVEL_TASK );

   //:IF qPrimReg.PrimaryRegistrant DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( qPrimReg, "PrimaryRegistrant" );
   if ( lTempInteger_1 != 0 )
   { 
      //:TraceLineI( "InitializePrimRegUser cannot activate Primary Registrant: ",
      //:            lPrimReg.PrimaryRegistrant.ID )
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, lPrimReg, "PrimaryRegistrant", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      TraceLineI( "InitializePrimRegUser cannot activate Primary Registrant: ", lTempInteger_2 );
      //:MessageSend( ViewToWindow, "", "Initialize New Primary Registrant User",
      //:             "Cannot activate Primary Registrant.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Initialize New Primary Registrant User", "Cannot activate Primary Registrant.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:DropObjectInstance( qPrimReg )
   DropObjectInstance( qPrimReg );

   //:GET VIEW mCurrentUser NAMED "mCurrentUser"
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   //:IF mCurrentUser != 0
   if ( getView( mCurrentUser ) != null )
   { 
      //:DropObjectInstance( mCurrentUser )
      DropObjectInstance( mCurrentUser );
   } 

   //:END

   //:GET VIEW mPerson NAMED "mPerson"
   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );
   //:IF mPerson != 0
   if ( getView( mPerson ) != null )
   { 
      //:DropObjectInstance( mPerson )
      DropObjectInstance( mPerson );
   } 

   //:END

   //:// We are activating empty OI's, so create all the entities.
   //:ACTIVATE mCurrentUser EMPTY
   RESULT = ActivateEmptyObjectInstance( mCurrentUser, "mUser", ViewToWindow, zSINGLE );
   //:NAME VIEW mCurrentUser "mCurrentUser"
   SetNameForView( mCurrentUser, "mCurrentUser", null, zLEVEL_TASK );
   //:CREATE ENTITY mCurrentUser.User
   RESULT = CreateEntity( mCurrentUser, "User", zPOS_AFTER );
   //:mCurrentUser.User.Status = "B"  // beginner
   SetAttributeFromString( mCurrentUser, "User", "Status", "B" );

   //:ACTIVATE mPerson EMPTY
   RESULT = ActivateEmptyObjectInstance( mPerson, "mPerson", ViewToWindow, zSINGLE );
   //:NAME VIEW mPerson "mPerson"
   SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );
   //:CREATE ENTITY mPerson.Person
   RESULT = CreateEntity( mPerson, "Person", zPOS_AFTER );
   //:CREATE ENTITY mPerson.Address
   RESULT = CreateEntity( mPerson, "Address", zPOS_AFTER );

   //:wWebXfer.Root.AttemptUserName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptUserName", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:mPerson.Address.Country = "USA"
   SetAttributeFromString( mPerson, "Address", "Country", "USA" );

   //:// CreateTemporalSubobjectVersion( mPerson, "Address" )
   //:CreateTemporalSubobjectVersion( mPerson, "Person" )
   CreateTemporalSubobjectVersion( mPerson, "Person" );
   //:CreateTemporalSubobjectVersion( mCurrentUser, "User" )
   CreateTemporalSubobjectVersion( mCurrentUser, "User" );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregUser( VIEW ViewToWindow )

public int 
CancelUpdateSubregUser( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrimRegUserForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrimRegUserForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser BASED ON LOD  mUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mPerson  BASED ON LOD  mPerson
   zVIEW    mPerson = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );

   //:IF lPrimReg = 0
   if ( getView( lPrimReg ) == null )
   { 
      //:MessageSend( ViewToWindow, "", "Initialize Primary Registrant User",
      //:             "The registrant list is empty.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Initialize Primary Registrant User", "The registrant list is empty.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:GET VIEW qPrimReg NAMED "qPrimReg"
   RESULT = GetViewByName( qPrimReg, "qPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
   } 

   //:END

   //:ACTIVATE qPrimReg WHERE qPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_27( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( qPrimReg, "qPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW qPrimReg "qPrimReg"
   SetNameForView( qPrimReg, "qPrimReg", null, zLEVEL_TASK );

   //:IF qPrimReg.PrimaryRegistrant DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( qPrimReg, "PrimaryRegistrant" );
   if ( lTempInteger_1 != 0 )
   { 
      //:TraceLineI( "InitPrimRegUserForUpdate cannot activate Primary Registrant: ",
      //:            lPrimReg.PrimaryRegistrant.ID )
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
             GetIntegerFromAttribute( mi_lTempInteger_2, lPrimReg, "PrimaryRegistrant", "ID" );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );}
      TraceLineI( "InitPrimRegUserForUpdate cannot activate Primary Registrant: ", lTempInteger_2 );
      //:MessageSend( ViewToWindow, "", "Initialize Primary Registrant User",
      //:             "Cannot activate Primary Registrant.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Initialize Primary Registrant User", "Cannot activate Primary Registrant.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:DropObjectInstance( qPrimReg )
   DropObjectInstance( qPrimReg );

   //:GET VIEW mCurrentUser NAMED "mCurrentUser"
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   //:IF mCurrentUser != 0
   if ( getView( mCurrentUser ) != null )
   { 
      //:DropObjectInstance( mCurrentUser )
      DropObjectInstance( mCurrentUser );
   } 

   //:END

   //:// We are activating a User instance for update.
   //:ACTIVATE mCurrentUser WHERE mCurrentUser.User.ID = lPrimReg.User.ID
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
       GetIntegerFromAttribute( mi_lTempInteger_3, lPrimReg, "User", "ID" );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );}
   o_fnLocalBuildQual_28( ViewToWindow, vTempViewVar_1, lTempInteger_3 );
   RESULT = ActivateObjectInstance( mCurrentUser, "mUser", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW mCurrentUser "mCurrentUser"
   SetNameForView( mCurrentUser, "mCurrentUser", null, zLEVEL_TASK );
   //:ACTIVATE mPerson WHERE mPerson.Person.ID = mCurrentUser.Employee.ID
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, mCurrentUser, "Employee", "ID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   o_fnLocalBuildQual_29( ViewToWindow, vTempViewVar_2, lTempInteger_4 );
   RESULT = ActivateObjectInstance( mPerson, "mPerson", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:NAME VIEW mPerson "mPerson"
   SetNameForView( mPerson, "mPerson", null, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptUserName = mCurrentUser.User.UserName
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptUserName", mCurrentUser, "User", "UserName" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:CreateTemporalSubobjectVersion( mCurrentUser, "User" )
   CreateTemporalSubobjectVersion( mCurrentUser, "User" );
   //:// CreateTemporalSubobjectVersion( mPerson, "Person" )
   //:// CreateTemporalSubobjectVersion( mPerson, "Address" )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregUserForUpdate( VIEW ViewToWindow )

public int 
InitSubregUserForUpdate( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListSubregProducts( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitListSubregProducts( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg != 0
   if ( getView( mSubreg ) != null )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END

   //:// Activate the "selected" Subregistrant.
   //:lID = lPrimReg.Subregistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "Subregistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lID
   o_fnLocalBuildQual_39( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mSubreg, "ListMasterProduct", "Subregistrant" )
   nRC = SetCursorFirstEntity( mSubreg, "ListMasterProduct", "Subregistrant" );
   //:LOOP WHILE nRC = 0
   while ( nRC == 0 )
   { 
      //:lID = mSubreg.ListMasterProduct.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubreg, "ListMasterProduct", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = SetCursorFirstEntityByInteger( mSubreg, "ValidMasterProduct", "ID", lID, "" )
      nRC = SetCursorFirstEntityByInteger( mSubreg, "ValidMasterProduct", "ID", lID, "" );
      //:IF nRC = 0
      if ( nRC == 0 )
      { 
         //:mSubreg.ListMasterProduct.wkSelected = "Y"
         SetAttributeFromString( mSubreg, "ListMasterProduct", "wkSelected", "Y" );
      } 

      //:END

      //:nRC = SetCursorNextEntity( mSubreg, "ListMasterProduct", "Subregistrant" )
      nRC = SetCursorNextEntity( mSubreg, "ListMasterProduct", "Subregistrant" );
   } 

   //:END

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.Banner4 = mSubreg.Subregistrant.dNameEPA_Number
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner4", mSubreg, "Subregistrant", "dNameEPA_Number" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregProducts( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
CancelUpdateSubregProducts( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateSubregProduct( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
AcceptUpdateSubregProduct( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:nRC = SetCursorFirstEntity( mSubreg, "ListMasterProduct", "Subregistrant" )
   nRC = SetCursorFirstEntity( mSubreg, "ListMasterProduct", "Subregistrant" );
   //:LOOP WHILE nRC = 0
   while ( nRC == 0 )
   { 
      //:lID = mSubreg.ListMasterProduct.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubreg, "ListMasterProduct", "ID" );
      lID = mi_lID.intValue( );}
      //:nRC = SetCursorFirstEntityByInteger( mSubreg, "ValidMasterProduct", "ID", lID, "" )
      nRC = SetCursorFirstEntityByInteger( mSubreg, "ValidMasterProduct", "ID", lID, "" );
      //:IF mSubreg.ListMasterProduct.wkSelected = "Y"
      if ( CompareAttributeToString( mSubreg, "ListMasterProduct", "wkSelected", "Y" ) == 0 )
      { 
         //:IF nRC != 0
         if ( nRC != 0 )
         { 
            //:IncludeSubobjectFromSubobject( mSubreg, "ValidMasterProduct",
            //:                               mSubreg, "ListMasterProduct", zPOS_LAST )
            IncludeSubobjectFromSubobject( mSubreg, "ValidMasterProduct", mSubreg, "ListMasterProduct", zPOS_LAST );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:ExcludeEntity( mSubreg, "ValidMasterProduct", zREPOS_NONE )
            ExcludeEntity( mSubreg, "ValidMasterProduct", zREPOS_NONE );
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( mSubreg, "ListMasterProduct", "Subregistrant" )
      nRC = SetCursorNextEntity( mSubreg, "ListMasterProduct", "Subregistrant" );
   } 

   //:END

   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListPrimRegUsers( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD lPrimReg
public int 
InitListPrimRegUsers( View     ViewToWindow )
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
   //:// deleted a primary registrant user.
   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_25( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewPrimaryRegistrant( View     ViewToWindow )
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
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" )
   CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" );
   //:// CreateTemporalSubobjectVersion( mPrimReg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "ContactPerson" )

   //:IF wWebXfer.Root.KeyRole = "U"  // i think U is for undefined???
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

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitChangePrimRegPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitChangePrimRegPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitChangePrimRegUserPassword( VIEW ViewToWindow )

public int 
InitChangePrimRegUserPassword( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ConfirmChangeSubregPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ConfirmChangeSubregPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING ( 128 ) szCurrentPassword
   String   szCurrentPassword = null;
   //:STRING ( 128 ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128 ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER        lPasswordLth
   int      lPasswordLth = 0;
   //:SHORT  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// 1: Ensure old password is correct.
   //:// IF mSubreg.SubregOrganization.AdministratorPassword != wWebXfer.Root.CurrentPassword
   //:szCurrentPassword = wWebXfer.Root.CurrentPassword
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szCurrentPassword;
   if ( szCurrentPassword == null )
      sb_szCurrentPassword = new StringBuilder( 32 );
   else
      sb_szCurrentPassword = new StringBuilder( szCurrentPassword );
       GetVariableFromAttribute( sb_szCurrentPassword, mi_lTempInteger_0, 'S', 129, wWebXfer, "Root", "CurrentPassword", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szCurrentPassword = sb_szCurrentPassword.toString( );}
   //:nRC = CompareAttributeToString( mSubreg, "SubregOrganization", "AdministratorPassword", szCurrentPassword )
   nRC = CompareAttributeToString( mSubreg, "SubregOrganization", "AdministratorPassword", szCurrentPassword );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 

      //:// TraceLineS( "//////* Invalid Current User Password", szCurrentPassword )
      //:// DisplayEntityInstance( mSubreg, "SubregOrganization" )
      //:MessageSend( ViewToWindow, "", "Change Subregistrant User Password",
      //:             "Current password is incorrect.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Subregistrant User Password", "Current password is incorrect.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:// 2: Ensure attempted password matches confirm password.
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

   //:mSubreg.SubregOrganization.AdministratorPassword = szConfirmPassword
   SetAttributeFromString( mSubreg, "SubregOrganization", "AdministratorPassword", szConfirmPassword );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeletePrimRegUser( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
DeletePrimRegUser( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mPrimReg.User
   RESULT = DeleteEntity( mPrimReg, "User", zPOS_NEXT );
   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSelectPrimRegistrant( VIEW ViewToWindow )

public int 
InitSelectPrimRegistrant( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListMasterLabels( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD lPrimReg
public int 
InitListMasterLabels( View     ViewToWindow )
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
   //:// deleted a primary registrant label.
   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_33( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewMasterLabel( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewMasterLabel( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mMasLC NAMED "mMasLC"
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   //:IF mMasLC != 0
   if ( getView( mMasLC ) != null )
   { 
      //:DropObjectInstance( mMasLC )
      DropObjectInstance( mMasLC );
   } 

   //:END

   //:// We are activating an empty OI, so create all the entities.
   //:ACTIVATE mMasLC EMPTY
   RESULT = ActivateEmptyObjectInstance( mMasLC, "mMasLC", ViewToWindow, zSINGLE );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );
   //:CREATE ENTITY mMasLC.MasterLabelContent
   RESULT = CreateEntity( mMasLC, "MasterLabelContent", zPOS_AFTER );
   //:// CREATE ENTITY mMasLC.MasterLabelSection
   //:// CREATE ENTITY mMasLC.MasterLabelSection

   //:wWebXfer.Root.AttemptProductName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptProductName", "" );

   //:CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" )
   CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" );
   //:// CreateTemporalSubobjectVersion( mMasLC, "MasterLabelSection" )
   //:// CreateTemporalSubobjectVersion( mMasLC, "MasterLabelSection" )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitMasterLabelForUpdate( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitMasterLabelForUpdate( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   BASED ON LOD  mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mMasLC NAMED "mMasLC"
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );
   //:IF mMasLC != 0
   if ( getView( mMasLC ) != null )
   { 
      //:DropObjectInstance( mMasLC )
      DropObjectInstance( mMasLC );
   } 

   //:END

   //:ACTIVATE mMasLC WHERE mMasLC.MasterLabelContent.ID = lPrimReg.MasterLabelContent.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "MasterLabelContent", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_34( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mMasLC, "mMasLC", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mMasLC "mMasLC"
   SetNameForView( mMasLC, "mMasLC", null, zLEVEL_TASK );

   //:// IF mMasLC.MasterLabelSection DOES NOT EXIST
   //://    CREATE ENTITY mMasLC.MasterLabelSection
   //:// END

   //:// IF mMasLC.MasterLabelSection DOES NOT EXIST
   //://    CREATE ENTITY mMasLC.MasterLabelSection
   //:// END

   //:wWebXfer.Root.AttemptProductName = mMasLC.MasterProduct.Name
   SetAttributeFromAttribute( wWebXfer, "Root", "AttemptProductName", mMasLC, "MasterProduct", "Name" );

   //:CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" )
   CreateTemporalSubobjectVersion( mMasLC, "MasterLabelContent" );
   //:// CreateTemporalSubobjectVersion( mMasLC, "MasterLabelSection" )
   //:// CreateTemporalSubobjectVersion( mMasLC, "MasterLabelSection" )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantLabel" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrantLabel" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewMasterLabel( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewMasterLabel( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:STRING (  50  ) szProductName
   String   szProductName = null;
   //:INTEGER         lProductNameLth
   int      lProductNameLth = 0;
   //:INTEGER         lControl
   int      lControl = 0;
   //:INTEGER         lID
   int      lID = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Ensure user login name is not blank and is unique.
   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:szProductName = mMasLC.MasterProduct.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szProductName;
   if ( szProductName == null )
      sb_szProductName = new StringBuilder( 32 );
   else
      sb_szProductName = new StringBuilder( szProductName );
       GetVariableFromAttribute( sb_szProductName, mi_lTempInteger_0, 'S', 51, mMasLC, "MasterProduct", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szProductName = sb_szProductName.toString( );}
   //:lProductNameLth = zGetStringLen( szProductName )
   lProductNameLth = zGetStringLen( szProductName );
   //:TraceLineS( "Label Name: ", szProductName )
   TraceLineS( "Label Name: ", szProductName );
   //:TraceLineI( "Label Name Length: ", lProductNameLth )
   TraceLineI( "Label Name Length: ", lProductNameLth );
   //:IF lProductNameLth < 1
   if ( lProductNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "New Primary Registrant Label",
      //:             "The Label Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant Label", "The Label Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:IF SetEntityCursor( lPrimReg, "MasterLabelContent", "ProductName", lControl,
      //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( lPrimReg, "MasterLabelContent", "ProductName", lControl, szProductName, "", "", 0, "", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Primary Registrant Label",
         //:             "The Label Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Primary Registrant Label", "The Label Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END

   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_35( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );

   //:IncludeSubobjectFromSubobject( mMasLC, "PrimaryRegistrant",
   //:                               mPrimReg, "PrimaryRegistrant", zPOS_LAST )
   IncludeSubobjectFromSubobject( mMasLC, "PrimaryRegistrant", mPrimReg, "PrimaryRegistrant", zPOS_LAST );

   //:AcceptSubobject( mMasLC, "MasterLabelContent" )
   AcceptSubobject( mMasLC, "MasterLabelContent" );
   //:// AcceptSubobject( mMasLC, "MasterLabelSection" )
   //:// AcceptSubobject( mMasLC, "MasterLabelSection" )

   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_36( ViewToWindow, vTempViewVar_1, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdateMasterLabel( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdateMasterLabel( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:STRING (  50  ) szProductName
   String   szProductName = null;
   //:STRING (  50  ) szAttemptProductName
   String   szAttemptProductName = null;
   //:INTEGER         lProductNameLth
   int      lProductNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:INTEGER         lControl
   int      lControl = 0;
   //:INTEGER         lID
   int      lID = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:// Ensure user login name is not blank and is unique.
   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:szProductName = mMasLC.MasterProduct.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szProductName;
   if ( szProductName == null )
      sb_szProductName = new StringBuilder( 32 );
   else
      sb_szProductName = new StringBuilder( szProductName );
       GetVariableFromAttribute( sb_szProductName, mi_lTempInteger_0, 'S', 51, mMasLC, "MasterProduct", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szProductName = sb_szProductName.toString( );}
   //:lProductNameLth = zGetStringLen( szProductName )
   lProductNameLth = zGetStringLen( szProductName );
   //:TraceLineS( "User Login Name: ", szProductName )
   TraceLineS( "User Login Name: ", szProductName );
   //:TraceLineI( "User Login Name Length: ", lProductNameLth )
   TraceLineI( "User Login Name Length: ", lProductNameLth );
   //:IF lProductNameLth < 1
   if ( lProductNameLth < 1 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Primary Registrant Label",
      //:             "The Label Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant Label", "The Label Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:szAttemptProductName = wWebXfer.Root.AttemptProductName
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szAttemptProductName;
      if ( szAttemptProductName == null )
         sb_szAttemptProductName = new StringBuilder( 32 );
      else
         sb_szAttemptProductName = new StringBuilder( szAttemptProductName );
             GetVariableFromAttribute( sb_szAttemptProductName, mi_lTempInteger_1, 'S', 51, wWebXfer, "Root", "AttemptProductName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szAttemptProductName = sb_szAttemptProductName.toString( );}
      //:IF szProductName != szAttemptProductName
      if ( ZeidonStringCompare( szProductName, 1, 0, szAttemptProductName, 1, 0, 51 ) != 0 )
      { 

         //:lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( lPrimReg, "MasterLabelContent", "ProductName", lControl,
         //:                    szProductName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_2 = SetEntityCursor( lPrimReg, "MasterLabelContent", "ProductName", lControl, szProductName, "", "", 0, "", "" );
         if ( lTempInteger_2 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Primary Registrant Label",
            //:             "The Label Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Primary Registrant Label", "The Label Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}

   //:AcceptSubobject( mMasLC, "MasterLabelContent" )
   AcceptSubobject( mMasLC, "MasterLabelContent" );
   //:// AcceptSubobject( mMasLC, "MasterLabelSection" )
   //:// AcceptSubobject( mMasLC, "MasterLabelSection" )

   //:Commit mMasLC
   RESULT = CommitObjectInstance( mMasLC );

   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_37( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewMasterLabel( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
CancelNewMasterLabel( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mMasLC, "MasterLabelContent" )
   CancelSubobject( mMasLC, "MasterLabelContent" );
   //:// CancelSubobject( mMasLC, "MasterLabelSection" )
   //:// CancelSubobject( mMasLC, "MasterLabelSection" )
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateMasterLabel( VIEW ViewToWindow )

//:   VIEW mMasLC   REGISTERED AS mMasLC
public int 
CancelUpdateMasterLabel( View     ViewToWindow )
{
   zVIEW    mMasLC = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:CancelSubobject( mMasLC, "MasterLabelContent" )
   CancelSubobject( mMasLC, "MasterLabelContent" );
   //:// CancelSubobject( mMasLC, "MasterLabelSection" )
   //:// CancelSubobject( mMasLC, "MasterLabelSection" )
   //:DropObjectInstance( mMasLC )
   DropObjectInstance( mMasLC );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteMasterLabel( VIEW ViewToWindow )

//:   VIEW mPrimReg REGISTERED AS mPrimReg
public int 
DeleteMasterLabel( View     ViewToWindow )
{
   zVIEW    mPrimReg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DELETE ENTITY mPrimReg.MasterLabelContent
   RESULT = DeleteEntity( mPrimReg, "MasterLabelContent", zPOS_NEXT );
   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ChangeUserPassword( VIEW ViewToWindow )

//:   VIEW mCurrentUser REGISTERED AS mCurrentUser
public int 
ChangeUserPassword( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wWebXfer REGISTERED AS wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:SHORT  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

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

   //:// 1: Ensure old password is correct.
   //:// IF mCurrentUser.User.UserPassword != mCurrentUser.User.AttemptPassword
   //:nRC = CompareAttributeToString( mCurrentUser, "User", "UserPassword", szAttemptPassword )
   nRC = CompareAttributeToString( mCurrentUser, "User", "UserPassword", szAttemptPassword );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Change Password",
      //:             "Current password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Password", "Current password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

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

   //:// 2: Ensure attempted password matches confirm password.
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

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( mCurrentUser, "User", "UserPassword", szConfirmPassword, "Password" )
   //:mCurrentUser.User.UserPassword = szConfirmPassword
   SetAttributeFromString( mCurrentUser, "User", "UserPassword", szConfirmPassword );

   //:// TraceLineS( "Newly Set Password:", mCurrentUser.User.UserPassword )

   //:// Commit change
   //:COMMIT mCurrentUser
   RESULT = CommitObjectInstance( mCurrentUser );
   //:mCurrentUser.User.wkAttemptPassword = ""
   SetAttributeFromString( mCurrentUser, "User", "wkAttemptPassword", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ValidatePrimRegistrantPassword( VIEW ViewToWindow )

public int 
ValidatePrimRegistrantPassword( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ValidateSubregistrantPassword( VIEW ViewToWindow )

public int 
ValidateSubregistrantPassword( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeletePrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
DeletePrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING ( 128 ) szAttemptPassword
   String   szAttemptPassword = null;
   //:SHORT  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:IF mPrimReg.PrimarySub EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( mPrimReg, "PrimarySub" );
   if ( lTempInteger_0 == 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Delete Primary Registrant",
      //:             "Subregistrants exist for primary registrant.  Delete Cancelled",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Primary Registrant", "Subregistrants exist for primary registrant.  Delete Cancelled", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:IF mPrimReg.MasterLabelContent EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mPrimReg, "MasterLabelContent" );
   if ( lTempInteger_1 == 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Delete Primary Registrant",
      //:             "Primary Label Data Definitions exist for primary registrant.  Delete Cancelled",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Primary Registrant", "Primary Label Data Definitions exist for primary registrant.  Delete Cancelled", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:// Match the password.
   //:// IF mPrimReg.Organization.AdministratorPassword != wWebXfer.Root.VerifiedPassword
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
   //:nRC = CompareAttributeToString( mPrimReg, "Organization", "AdministratorPassword", szAttemptPassword )
   nRC = CompareAttributeToString( mPrimReg, "Organization", "AdministratorPassword", szAttemptPassword );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 

      //:// TraceLineS( "//////* Invalid Administrator Password: ", szAttemptPassword )
      //:MessageSend( ViewToWindow, "", "Delete Primary Registrant",
      //:             "Current password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Primary Registrant", "Current password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:DELETE ENTITY mPrimReg.PrimaryRegistrant
   RESULT = DeleteEntity( mPrimReg, "PrimaryRegistrant", zPOS_NEXT );
   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
DeleteSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING ( 128 ) szAttemptPassword
   String   szAttemptPassword = null;
   //:INTEGER lID
   int      lID = 0;
   //:INTEGER lSubregID
   int      lSubregID = 0;
   //:INTEGER lControl
   int      lControl = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lPrimReg.Subregistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "Subregistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_23( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mSubreg, "mSubreg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );
   //:IF mSubreg.SubregProduct EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( mSubreg, "SubregProduct" );
   if ( lTempInteger_1 == 0 )
   { 

      //: MessageSend( ViewToWindow, "", "Delete Subregistrant",
      //:             "Product Definitions exist for subregistrant.  Delete Cancelled",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Subregistrant", "Product Definitions exist for subregistrant.  Delete Cancelled", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:// Match the password.
   //:// IF mSubreg.Organization.AdministratorPassword != wWebXfer.Root.AttemptPassword
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
   //:nRC = CompareAttributeToString( mSubreg, "Organization", "AdministratorPassword", szAttemptPassword )
   nRC = CompareAttributeToString( mSubreg, "Organization", "AdministratorPassword", szAttemptPassword );
   //:IF nRC != 0
   if ( nRC != 0 )
   { 

      //:// TraceLineS( "//////* Invalid Administrator Password: ", szAttemptPassword )
      //:MessageSend( ViewToWindow, "", "Delete Subregistrant",
      //:             "Current password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Subregistrant", "Current password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:lSubregID = mSubreg.Subregistrant.ID
   {MutableInt mi_lSubregID = new MutableInt( lSubregID );
       GetIntegerFromAttribute( mi_lSubregID, mSubreg, "Subregistrant", "ID" );
   lSubregID = mi_lSubregID.intValue( );}

   //:DELETE ENTITY mSubreg.Subregistrant
   RESULT = DeleteEntity( mSubreg, "Subregistrant", zPOS_NEXT );
   //:COMMIT mSubreg
   RESULT = CommitObjectInstance( mSubreg );

   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:// Activate the "selected" primary registrant ... because we just deleted
   //:// a subregistrant.
   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_24( ViewToWindow, vTempViewVar_1, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitChangeSubregPassword( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitChangeSubregPassword( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:DeleteSubregUser( VIEW ViewToWindow )

public int 
DeleteSubregUser( View     ViewToWindow )
{

   return( 0 );
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
   //:STRING (   1  ) szKeyRole
   String   szKeyRole = null;
   //:INTEGER lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;

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

   //:// If this is the first time into the system, this is the administrator.
   //:// Otherwise, just create a new primary registrant.
   //:ACTIVATE mSubreg WHERE mSubreg.Subregistrant.ID = lSubreg.Subregistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSubreg, "Subregistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_20( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
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
      //:lID = mSubreg.MailingAddress.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubreg, "MailingAddress", "ID" );
      lID = mi_lID.intValue( );}
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mSubreg, "MailingAddress",
      //:                             mSubreg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mSubreg, "MailingAddress", mSubreg, "PhysicalAddress", zSET_NOTNULL );
      //:lID = mSubreg.MailingAddress.ID
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, mSubreg, "MailingAddress", "ID" );
      lID = mi_lID.intValue( );}
   } 

   //:// mSubreg.MailingAddress.ID = lID
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
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:CreateTemporalSubobjectVersion( mSubreg, "Subregistrant" )
   CreateTemporalSubobjectVersion( mSubreg, "Subregistrant" );
   //:// CreateTemporalSubobjectVersion( mSubreg, "SubregOrganization" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "ContactPerson" )

   //:szKeyRole = wWebXfer.Root.KeyRole
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szKeyRole;
   if ( szKeyRole == null )
      sb_szKeyRole = new StringBuilder( 32 );
   else
      sb_szKeyRole = new StringBuilder( szKeyRole );
       GetVariableFromAttribute( sb_szKeyRole, mi_lTempInteger_5, 'S', 2, wWebXfer, "Root", "KeyRole", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szKeyRole = sb_szKeyRole.toString( );}
   //:IF szKeyRole = "S" // Subregistrant
   if ( ZeidonStringCompare( szKeyRole, 1, 0, "S", 1, 0, 2 ) == 0 )
   { 
      //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:wWebXfer.Root.Banner4 = mSubreg.Subregistrant.dNameEPA_Number
   SetAttributeFromAttribute( wWebXfer, "Root", "Banner4", mSubreg, "Subregistrant", "dNameEPA_Number" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AddNewSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AddNewSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  BASED ON LOD  mSubreg
   zVIEW    mSubreg = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mSubreg NAMED "mSubreg"
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF mSubreg != 0
   if ( getView( mSubreg ) != null )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END

   //:ACTIVATE mSubreg EMPTY
   RESULT = ActivateEmptyObjectInstance( mSubreg, "mSubreg", ViewToWindow, zSINGLE );
   //:NAME VIEW mSubreg "mSubreg"
   SetNameForView( mSubreg, "mSubreg", null, zLEVEL_TASK );

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

   //:wWebXfer.Root.AttemptLoginName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptLoginName", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:CreateTemporalSubobjectVersion( mSubreg, "Subregistrant" )
   CreateTemporalSubobjectVersion( mSubreg, "Subregistrant" );
   //:// CreateTemporalSubobjectVersion( mSubreg, "SubregOrganization" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mSubreg, "ContactPerson" )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdateSubregistrant( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelUpdateSubregistrant( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSubreg, "ContactPerson" )
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
//:AcceptUpdateSubregistrant( VIEW ViewToWindow )

//:   VIEW mSubreg  REGISTERED AS mSubreg
public int 
AcceptUpdateSubregistrant( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:nRC = ValidateSubregistrant( ViewToWindow )
   nRC = ValidateSubregistrant( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:DropObjectInstance( mSubreg )
      DropObjectInstance( mSubreg );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregistrant( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS mSubreg
public int 
CancelNewSubregistrant( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mSubreg, "ContactPerson" )
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
//:AcceptUpdateSubregUser( VIEW ViewToWindow )

public int 
AcceptUpdateSubregUser( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewSubregUser( VIEW ViewToWindow )

public int 
CancelNewSubregUser( View     ViewToWindow )
{

   return( 0 );
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
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:STRING (  50  ) szAttemptLoginName
   String   szAttemptLoginName = null;
   //:STRING (  50  ) szRegistrantName
   String   szRegistrantName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lAttemptLoginNameLth
   int      lAttemptLoginNameLth = 0;
   //:INTEGER         lRegistrantNameLth
   int      lRegistrantNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:INTEGER         lID
   int      lID = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure registrant login name is not blank and is unique.
   //:szAttemptLoginName = wWebXfer.Root.AttemptLoginName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szAttemptLoginName;
   if ( szAttemptLoginName == null )
      sb_szAttemptLoginName = new StringBuilder( 32 );
   else
      sb_szAttemptLoginName = new StringBuilder( szAttemptLoginName );
       GetVariableFromAttribute( sb_szAttemptLoginName, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptLoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szAttemptLoginName = sb_szAttemptLoginName.toString( );}
   //:lAttemptLoginNameLth = zGetStringLen( szAttemptLoginName )
   lAttemptLoginNameLth = zGetStringLen( szAttemptLoginName );
   //:TraceLineS( "Registrant Login Name: ", szAttemptLoginName )
   TraceLineS( "Registrant Login Name: ", szAttemptLoginName );
   //:TraceLineI( "Registrant Login Name Length: ", lAttemptLoginNameLth )
   TraceLineI( "Registrant Login Name Length: ", lAttemptLoginNameLth );
   //:IF lAttemptLoginNameLth < 1
   if ( lAttemptLoginNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "New Subregistrant",
      //:             "The registrant Login Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Subregistrant", "The registrant Login Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST lPrimReg.Subregistrant WHERE lPrimReg.SubregOrganization.LoginName = szAttemptLoginName
      RESULT = SetCursorFirstEntity( lPrimReg, "Subregistrant", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( lPrimReg, "SubregOrganization", "LoginName", szAttemptLoginName ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( lPrimReg, "Subregistrant", "" );
         } 

      } 

      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:MessageSend( ViewToWindow, "", "New Subregistrant",
         //:             "The registrant Login Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Subregistrant", "The registrant Login Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:// Ensure registrant name is not blank.
   //:szRegistrantName = mSubreg.SubregOrganization.Name
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szRegistrantName;
   if ( szRegistrantName == null )
      sb_szRegistrantName = new StringBuilder( 32 );
   else
      sb_szRegistrantName = new StringBuilder( szRegistrantName );
       GetVariableFromAttribute( sb_szRegistrantName, mi_lTempInteger_1, 'S', 51, mSubreg, "SubregOrganization", "Name", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szRegistrantName = sb_szRegistrantName.toString( );}
   //:lRegistrantNameLth = zGetStringLen( szRegistrantName )
   lRegistrantNameLth = zGetStringLen( szRegistrantName );
   //:TraceLineS( "Registrant Name: ", szRegistrantName )
   TraceLineS( "Registrant Name: ", szRegistrantName );
   //:TraceLineI( "Registrant Name Length: ", lRegistrantNameLth )
   TraceLineI( "Registrant Name Length: ", lRegistrantNameLth );
   //:IF lRegistrantNameLth < 1
   if ( lRegistrantNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Subregistrant",
      //:             "The registrant Organization Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant", "The registrant Organization Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

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
      //:MessageSend( ViewToWindow, "", "Update Subregistrant",
      //:             "The new password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant", "The new password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
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
      //:MessageSend( ViewToWindow, "", "Update Subregistrant",
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set login name to new login name.
   //:mSubreg.SubregOrganization.LoginName = szAttemptLoginName
   SetAttributeFromString( mSubreg, "SubregOrganization", "LoginName", szAttemptLoginName );

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( mSubreg, "User", "UserPassword", szConfirmPassword, "Password" )
   //:mSubreg.SubregOrganization.AdministratorPassword = szConfirmPassword
   SetAttributeFromString( mSubreg, "SubregOrganization", "AdministratorPassword", szConfirmPassword );
   //:mSubreg.SubregOrganization.Role = "S"  // Subregistrant
   SetAttributeFromString( mSubreg, "SubregOrganization", "Role", "S" );

   //:// AcceptSubobject( mSubreg, "ContactPerson" )
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

   //:CREATE ENTITY mSubreg.PrimarySub
   RESULT = CreateEntity( mSubreg, "PrimarySub", zPOS_AFTER );
   //:IncludeSubobjectFromSubobject( mSubreg, "PrimaryRegistrant",
   //:                               lPrimReg, "PrimaryRegistrant", zPOS_LAST )
   IncludeSubobjectFromSubobject( mSubreg, "PrimaryRegistrant", lPrimReg, "PrimaryRegistrant", zPOS_LAST );
   //:// MessageSend( ViewToWindow, ", "Accept New Subregistrant",
   //://              "Check out mPrimReg in Object Browser.",
   //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )

   //:Commit mSubreg
   RESULT = CommitObjectInstance( mSubreg );

   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}
   //:DropObjectInstance( mSubreg )
   DropObjectInstance( mSubreg );
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_21( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
   return( 0 );
// // MessageSend( ViewToWindow, ", "Accept New Subregistrant",
// //              "Check out lPrimReg in Object Browser.",
// //              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
// END
} 


//:DIALOG OPERATION
//:AcceptNewSubregUser( VIEW ViewToWindow )

public int 
AcceptNewSubregUser( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewPrimRegUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewPrimRegUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mPerson  REGISTERED AS mPerson
   zVIEW    mPerson = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:// VIEW mPrimReg BASED ON LOD  mPrimReg
   //:STRING (  50  ) szUserName
   String   szUserName = null;
   //:STRING ( 128 ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128 ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lUserNameLth
   int      lUserNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
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
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure user login name is not blank and is unique.
   //:szUserName = mCurrentUser.User.UserName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_0, 'S', 51, mCurrentUser, "User", "UserName", "", 0 );
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

      //:MessageSend( ViewToWindow, "", "New Primary Registrant User",
      //:             "The User Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant User", "The User Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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
      //:IF SetEntityCursor( lPrimReg, "User", "UserName", lControl,
      //:                    szUserName, "", "", 0, "", "" ) >= zCURSOR_SET
      lTempInteger_1 = SetEntityCursor( lPrimReg, "User", "UserName", lControl, szUserName, "", "", 0, "", "" );
      if ( lTempInteger_1 >= zCURSOR_SET )
      { 
         //:MessageSend( ViewToWindow, "", "New Primary Registrant User",
         //:             "The User Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Primary Registrant User", "The User Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END
   } 

   //:END

   //:// Ensure user first and last names are not blank.
   //:szUserName = mPerson.Person.FirstName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_2, 'S', 51, mPerson, "Person", "FirstName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
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
      //:MessageSend( ViewToWindow, "", "New Primary Registrant User",
      //:             "The user First Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant User", "The user First Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szUserName = mPerson.Person.LastName
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_3, 'S', 51, mPerson, "Person", "LastName", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
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
      //:MessageSend( ViewToWindow, "", "New Primary Registrant User",
      //:             "The user Last Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant User", "The user Last Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_4, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:szConfirmPassword = wWebXfer.Root.ConfirmPassword
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_5, 'S', 129, wWebXfer, "Root", "ConfirmPassword", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
   szConfirmPassword = sb_szConfirmPassword.toString( );}

   //:// 1: Ensure attempted password matches confirm password.
   //:IF szAttemptPassword != szConfirmPassword
   if ( ZeidonStringCompare( szAttemptPassword, 1, 0, szConfirmPassword, 1, 0, 129 ) != 0 )
   { 
      //:// TraceLineS( szAttemptPassword, szConfirmPassword )
      //:MessageSend( ViewToWindow, "", "New Primary Registrant User",
      //:             "The new password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant User", "The new password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
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
      //:MessageSend( ViewToWindow, "", "New Primary Registrant User",
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant User", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( qPrimReg, "User", "UserPassword", szConfirmPassword, "Password" )
   //:mCurrentUser.User.UserPassword = szConfirmPassword
   SetAttributeFromString( mCurrentUser, "User", "UserPassword", szConfirmPassword );

   //:ACTIVATE qPrimReg WHERE qPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
       GetIntegerFromAttribute( mi_lTempInteger_6, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_6 = mi_lTempInteger_6.intValue( );}
   o_fnLocalBuildQual_30( ViewToWindow, vTempViewVar_0, lTempInteger_6 );
   RESULT = ActivateObjectInstance( qPrimReg, "qPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW qPrimReg "qPrimReg"
   SetNameForView( qPrimReg, "qPrimReg", null, zLEVEL_TASK );

   //:// AcceptSubobject( mPerson, "Address" )
   //:AcceptSubobject( mPerson, "Person" )
   AcceptSubobject( mPerson, "Person" );
   //:IncludeSubobjectFromSubobject( mPerson, "PrimaryRegistrant",
   //:                               qPrimReg, "PrimaryRegistrant", zPOS_LAST )
   IncludeSubobjectFromSubobject( mPerson, "PrimaryRegistrant", qPrimReg, "PrimaryRegistrant", zPOS_LAST );
   //:Commit mPerson
   RESULT = CommitObjectInstance( mPerson );

   //:AcceptSubobject( mCurrentUser, "User" )
   AcceptSubobject( mCurrentUser, "User" );
   //:IncludeSubobjectFromSubobject( mCurrentUser, "Person",
   //:                               mPerson, "Person", zPOS_BEFORE )
   IncludeSubobjectFromSubobject( mCurrentUser, "Person", mPerson, "Person", zPOS_BEFORE );
   //:Commit mCurrentUser
   RESULT = CommitObjectInstance( mCurrentUser );

   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}

   //:DropObjectInstance( mPerson )
   DropObjectInstance( mPerson );
   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
   //:DropObjectInstance( qPrimReg )
   DropObjectInstance( qPrimReg );
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:// Activate the "selected" primary registrant ... just in case someone added or
   //:// deleted a primary registrant user.
   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_31( ViewToWindow, vTempViewVar_1, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewPrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW iePamms  BASED ON LOD  iePamms
   zVIEW    iePamms = new zVIEW( );
   //:VIEW mUser    BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING (  50  ) szRegistrantName
   String   szRegistrantName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lRegistrantNameLth
   int      lRegistrantNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_2 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure registrant login name is not blank and is unique.
   //:szRegistrantName = mPrimReg.Organization.LoginName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szRegistrantName;
   if ( szRegistrantName == null )
      sb_szRegistrantName = new StringBuilder( 32 );
   else
      sb_szRegistrantName = new StringBuilder( szRegistrantName );
       GetVariableFromAttribute( sb_szRegistrantName, mi_lTempInteger_0, 'S', 51, mPrimReg, "Organization", "LoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szRegistrantName = sb_szRegistrantName.toString( );}
   //:lRegistrantNameLth = zGetStringLen( szRegistrantName )
   lRegistrantNameLth = zGetStringLen( szRegistrantName );
   //:TraceLineS( "Registrant Login Name: ", szRegistrantName )
   TraceLineS( "Registrant Login Name: ", szRegistrantName );
   //:TraceLineI( "Registrant Login Name Length: ", lRegistrantNameLth )
   TraceLineI( "Registrant Login Name Length: ", lRegistrantNameLth );
   //:IF lRegistrantNameLth < 1
   if ( lRegistrantNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "New Primary Registrant",
      //:             "The registrant Login Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "New Primary Registrant", "The registrant Login Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE lPrimReg WHERE lPrimReg.Organization.LoginName = szRegistrantName
      o_fnLocalBuildQual_11( ViewToWindow, vTempViewVar_0, szRegistrantName );
      RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW lPrimReg "lPrimRegX"
      SetNameForView( lPrimReg, "lPrimRegX", null, zLEVEL_TASK );
      //:IF lPrimReg.PrimaryRegistrant EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( lPrimReg, "PrimaryRegistrant" );
      if ( lTempInteger_1 == 0 )
      { 
         //:MessageSend( ViewToWindow, "", "New Primary Registrant",
         //:             "The registrant Login Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Primary Registrant", "The registrant Login Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:DropObjectInstance( lPrimReg )
         DropObjectInstance( lPrimReg );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:// Note that Activate always returns at least an empty view.
   //:ACTIVATE qPrimReg WHERE qPrimReg.Organization.LoginName = "Admin"
   o_fnLocalBuildQual_12( ViewToWindow, vTempViewVar_1 );
   RESULT = ActivateObjectInstance( qPrimReg, "qPrimReg", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:NAME VIEW qPrimReg "qPrimReg"
   SetNameForView( qPrimReg, "qPrimReg", null, zLEVEL_TASK );
   //:IF qPrimReg.PrimaryRegistrant DOES NOT EXIST
   lTempInteger_2 = CheckExistenceOfEntity( qPrimReg, "PrimaryRegistrant" );
   if ( lTempInteger_2 != 0 )
   { 
      //:// No primary registrant found!  The first login name must be "Admin".
      //:nRC = zstrcmpi( szRegistrantName, "Admin" )
      nRC = zstrcmpi( szRegistrantName, "Admin" );
      //:IF nRC != 0
      if ( nRC != 0 )
      { 
         //:TraceLineS( "ProcessAdministratorLogin: ", "Admin must be first login found!!!" )
         TraceLineS( "ProcessAdministratorLogin: ", "Admin must be first login found!!!" );
         //:MessageSend( ViewToWindow, "", "New Primary Registrant",
         //:             "The first registrant Login Name must be 'Admin'.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "New Primary Registrant", "The first registrant Login Name must be 'Admin'.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:// SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
         //://                          "wStartUp", "AdminNewPrimaryRegistrant" )
         //:DropObjectInstance( qPrimReg )
         DropObjectInstance( qPrimReg );
         //:RETURN 2
         if(8==8)return( 2 );
         //:ELSE
      } 
      else
      { 
         //:mPrimReg.Organization.LoginName = "Admin"
         SetAttributeFromString( mPrimReg, "Organization", "LoginName", "Admin" );
      } 

      //:END
   } 

   //:END

   //:DropObjectInstance( qPrimReg )
   DropObjectInstance( qPrimReg );

   //:// Ensure registrant name is not blank.
   //:szRegistrantName = mPrimReg.Organization.Name
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szRegistrantName;
   if ( szRegistrantName == null )
      sb_szRegistrantName = new StringBuilder( 32 );
   else
      sb_szRegistrantName = new StringBuilder( szRegistrantName );
       GetVariableFromAttribute( sb_szRegistrantName, mi_lTempInteger_3, 'S', 51, mPrimReg, "Organization", "Name", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szRegistrantName = sb_szRegistrantName.toString( );}
   //:lRegistrantNameLth = zGetStringLen( szRegistrantName )
   lRegistrantNameLth = zGetStringLen( szRegistrantName );
   //:TraceLineS( "Registrant Name: ", szRegistrantName )
   TraceLineS( "Registrant Name: ", szRegistrantName );
   //:TraceLineI( "Registrant Name Length: ", lRegistrantNameLth )
   TraceLineI( "Registrant Name Length: ", lRegistrantNameLth );
   //:IF lRegistrantNameLth < 1
   if ( lRegistrantNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The registrant Organization Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The registrant Organization Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szAttemptPassword = wWebXfer.Root.AttemptPassword
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szAttemptPassword;
   if ( szAttemptPassword == null )
      sb_szAttemptPassword = new StringBuilder( 32 );
   else
      sb_szAttemptPassword = new StringBuilder( szAttemptPassword );
       GetVariableFromAttribute( sb_szAttemptPassword, mi_lTempInteger_4, 'S', 129, wWebXfer, "Root", "AttemptPassword", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
   szAttemptPassword = sb_szAttemptPassword.toString( );}
   //:szConfirmPassword = wWebXfer.Root.ConfirmPassword
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_5, 'S', 129, wWebXfer, "Root", "ConfirmPassword", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
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
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
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
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( mPrimReg, "User", "UserPassword", szVerifyPassword, "Password" )
   //:mPrimReg.Organization.AdministratorPassword = szConfirmPassword
   SetAttributeFromString( mPrimReg, "Organization", "AdministratorPassword", szConfirmPassword );

   //:ACTIVATE iePamms WHERE iePamms.ePamms.ID = 1
   o_fnLocalBuildQual_13( ViewToWindow, vTempViewVar_2 );
   RESULT = ActivateObjectInstance( iePamms, "iePamms", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:IncludeSubobjectFromSubobject( mPrimReg, "ePamms",
   //:                               iePamms, "ePamms", zPOS_FIRST )
   IncludeSubobjectFromSubobject( mPrimReg, "ePamms", iePamms, "ePamms", zPOS_FIRST );
   //:// AcceptSubobject( mPrimReg, "ContactPerson" )
   //:// AcceptSubobject( mPrimReg, "PhysicalAddress" )
   //:// AcceptSubobject( mPrimReg, "MailingAddress" )
   //:// AcceptSubobject( mPrimReg, "Organization" )
   //:AcceptSubobject( mPrimReg, "PrimaryRegistrant" )
   AcceptSubobject( mPrimReg, "PrimaryRegistrant" );
   //:IF wWebXfer.Root.SameAs = "Y"
   if ( CompareAttributeToString( wWebXfer, "Root", "SameAs", "Y" ) == 0 )
   { 
      //:DELETE ENTITY mPrimReg.MailingAddress
      RESULT = DeleteEntity( mPrimReg, "MailingAddress", zPOS_NEXT );
   } 

   //:END

   //:COMMIT mPrimReg
   RESULT = CommitObjectInstance( mPrimReg );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptNewAdministrator( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptNewAdministrator( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mePamms  REGISTERED AS mePamms
   zVIEW    mePamms = new zVIEW( );
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lRegistrantNameLth
   int      lRegistrantNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mePamms, "mePamms", ViewToWindow, zLEVEL_TASK );

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
      //:MessageSend( ViewToWindow, "", "Accept New Administrator",
      //:             "The new password and the confirmation password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Accept New Administrator", "The new password and the confirmation password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
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
      //:MessageSend( ViewToWindow, "", "Accept New Administrator",
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Accept New Administrator", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Set user password to new password.
   //:// SetAttrFromStrByContext( mPrimReg, "User", "UserPassword", szVerifyPassword, "Password" )
   //:mePamms.Organization.AdministratorPassword = szConfirmPassword
   SetAttributeFromString( mePamms, "Organization", "AdministratorPassword", szConfirmPassword );
   //:mePamms.Organization.LastLoginDateTime = wWebXfer.Root.dCurrentDateTime
   SetAttributeFromAttribute( mePamms, "Organization", "LastLoginDateTime", wWebXfer, "Root", "dCurrentDateTime" );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:COMMIT mePamms
   RESULT = CommitObjectInstance( mePamms );
   //:DropObjectInstance( mePamms )
   DropObjectInstance( mePamms );
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
   o_fnLocalBuildQual_10( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
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
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mPrimReg, "MailingAddress",
      //:                             mPrimReg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mPrimReg, "MailingAddress", mPrimReg, "PhysicalAddress", zSET_NOTNULL );
   } 

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
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );

   //:CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" )
   CreateTemporalSubobjectVersion( mPrimReg, "PrimaryRegistrant" );
   //:// CreateTemporalSubobjectVersion( mPrimReg, "PhysicalAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "MailingAddress" )
   //:// CreateTemporalSubobjectVersion( mPrimReg, "ContactPerson" )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
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

   //:// CancelSubobject( mPrimReg, "ContactPerson" )
   //:// CancelSubobject( mPrimReg, "PhysicalAddress" )
   //:// CancelSubobject( mPrimReg, "MailingAddress" )
   //:// CancelSubobject( mPrimReg, "Organization" )
   //:CancelSubobject( mPrimReg, "PrimaryRegistrant" )
   CancelSubobject( mPrimReg, "PrimaryRegistrant" );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelUpdatePrimRegUser( VIEW ViewToWindow )

//:   VIEW mCurrentUser REGISTERED AS mCurrentUser
public int 
CancelUpdatePrimRegUser( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPerson REGISTERED AS mPerson
   zVIEW    mPerson = new zVIEW( );

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mPerson, "Address" )
   //:CancelSubobject( mPerson, "Person" )
   CancelSubobject( mPerson, "Person" );
   //:CancelSubobject( mCurrentUser, "User" )
   CancelSubobject( mCurrentUser, "User" );
   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
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
   //:SHORT nRC
   int      nRC = 0;

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:DisplayObjectInstance( mPrimReg, "", "" )
   DisplayObjectInstance( mPrimReg, "", "" );
   //:nRC = ValidatePrimaryRegistrant( ViewToWindow )
   nRC = ValidatePrimaryRegistrant( ViewToWindow );
   //:IF nRC = 0
   if ( nRC == 0 )
   { 
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
   } 

   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:DIALOG OPERATION
//:ValidatePrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ValidatePrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mUser    BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mPrimReg REGISTERED AS mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   //:STRING (  50  ) szRegistrantName
   String   szRegistrantName = null;
   //:STRING (  50  ) szAttemptRegistrantName
   String   szAttemptRegistrantName = null;
   //:INTEGER         lRegistrantNameLth
   int      lRegistrantNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure registrant login name is not blank and is unique.
   //:szAttemptRegistrantName = mPrimReg.Organization.LoginName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szAttemptRegistrantName;
   if ( szAttemptRegistrantName == null )
      sb_szAttemptRegistrantName = new StringBuilder( 32 );
   else
      sb_szAttemptRegistrantName = new StringBuilder( szAttemptRegistrantName );
       GetVariableFromAttribute( sb_szAttemptRegistrantName, mi_lTempInteger_0, 'S', 51, mPrimReg, "Organization", "LoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szAttemptRegistrantName = sb_szAttemptRegistrantName.toString( );}
   //:lRegistrantNameLth = zGetStringLen( szAttemptRegistrantName )
   lRegistrantNameLth = zGetStringLen( szAttemptRegistrantName );
   //:TraceLineI( "Registrant Login Name Length: ", lRegistrantNameLth )
   TraceLineI( "Registrant Login Name Length: ", lRegistrantNameLth );
   //:IF lRegistrantNameLth < 1
   if ( lRegistrantNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The registrant Login Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The registrant Login Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szRegistrantName = wWebXfer.Root.AttemptLoginName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szRegistrantName;
   if ( szRegistrantName == null )
      sb_szRegistrantName = new StringBuilder( 32 );
   else
      sb_szRegistrantName = new StringBuilder( szRegistrantName );
       GetVariableFromAttribute( sb_szRegistrantName, mi_lTempInteger_1, 'S', 51, wWebXfer, "Root", "AttemptLoginName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szRegistrantName = sb_szRegistrantName.toString( );}
   //:IF szRegistrantName != szAttemptRegistrantName
   if ( ZeidonStringCompare( szRegistrantName, 1, 0, szAttemptRegistrantName, 1, 0, 51 ) != 0 )
   { 
      //:ACTIVATE lPrimReg WHERE lPrimReg.Organization.LoginName = szAttemptRegistrantName
      o_fnLocalBuildQual_14( ViewToWindow, vTempViewVar_0, szAttemptRegistrantName );
      RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW lPrimReg "lPrimRegX"
      SetNameForView( lPrimReg, "lPrimRegX", null, zLEVEL_TASK );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
         //:             "The registrant Login Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Update Primary Registrant", "The registrant Login Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:DropObjectInstance( lPrimReg )
         DropObjectInstance( lPrimReg );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END

      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:// Ensure registrant name is not blank.
   //:szAttemptRegistrantName = mPrimReg.Organization.Name
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szAttemptRegistrantName;
   if ( szAttemptRegistrantName == null )
      sb_szAttemptRegistrantName = new StringBuilder( 32 );
   else
      sb_szAttemptRegistrantName = new StringBuilder( szAttemptRegistrantName );
       GetVariableFromAttribute( sb_szAttemptRegistrantName, mi_lTempInteger_2, 'S', 51, mPrimReg, "Organization", "Name", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szAttemptRegistrantName = sb_szAttemptRegistrantName.toString( );}
   //:lRegistrantNameLth = zGetStringLen( szAttemptRegistrantName )
   lRegistrantNameLth = zGetStringLen( szAttemptRegistrantName );
   //:TraceLineI( "Registrant Name Length: ", lRegistrantNameLth )
   TraceLineI( "Registrant Name Length: ", lRegistrantNameLth );
   //:IF lRegistrantNameLth < 1
   if ( lRegistrantNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant",
      //:             "The registrant Organization Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant", "The registrant Organization Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Ensure password matches primary registrant password.
   //:// IF mPrimReg.PrimaryRegistrant.AdministratorPassword != wWebXfer.Root.VerifiedPassword
   //://
   //://    MessageSend( ViewToWindow, "", "Update Primary Registrant",
   //://                 "Invalid password.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    RETURN 2
   //:// END

   //:// AcceptSubobject( mPrimReg, "ContactPerson" )
   //:// AcceptSubobject( mPrimReg, "PhysicalAddress" )
   //:// AcceptSubobject( mPrimReg, "MailingAddress" )
   //:// AcceptSubobject( mPrimReg, "Organization" )
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
   //:RETURN 0
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ValidateSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
ValidateSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubreg  REGISTERED AS mSubreg
   zVIEW    mSubreg = new zVIEW( );
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:STRING (  50  ) szAttemptLoginName
   String   szAttemptLoginName = null;
   //:STRING (  50  ) szRegistrantName
   String   szRegistrantName = null;
   //:STRING ( 128  ) szAttemptPassword
   String   szAttemptPassword = null;
   //:STRING ( 128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:INTEGER         lAttemptLoginNameLth
   int      lAttemptLoginNameLth = 0;
   //:INTEGER         lRegistrantNameLth
   int      lRegistrantNameLth = 0;
   //:INTEGER         lPasswordLth
   int      lPasswordLth = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure registrant login name is not blank and is unique.
   //:szAttemptLoginName = wWebXfer.Root.AttemptLoginName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szAttemptLoginName;
   if ( szAttemptLoginName == null )
      sb_szAttemptLoginName = new StringBuilder( 32 );
   else
      sb_szAttemptLoginName = new StringBuilder( szAttemptLoginName );
       GetVariableFromAttribute( sb_szAttemptLoginName, mi_lTempInteger_0, 'S', 51, wWebXfer, "Root", "AttemptLoginName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szAttemptLoginName = sb_szAttemptLoginName.toString( );}
   //:lAttemptLoginNameLth = zGetStringLen( szAttemptLoginName )
   lAttemptLoginNameLth = zGetStringLen( szAttemptLoginName );
   //:TraceLineS( "Registrant Login Name: ", szAttemptLoginName )
   TraceLineS( "Registrant Login Name: ", szAttemptLoginName );
   //:TraceLineI( "Registrant Login Name Length: ", lAttemptLoginNameLth )
   TraceLineI( "Registrant Login Name Length: ", lAttemptLoginNameLth );
   //:IF lAttemptLoginNameLth < 1
   if ( lAttemptLoginNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Subregistrant",
      //:             "The registrant Login Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant", "The registrant Login Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szRegistrantName = mSubreg.SubregOrganization.LoginName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szRegistrantName;
   if ( szRegistrantName == null )
      sb_szRegistrantName = new StringBuilder( 32 );
   else
      sb_szRegistrantName = new StringBuilder( szRegistrantName );
       GetVariableFromAttribute( sb_szRegistrantName, mi_lTempInteger_1, 'S', 51, mSubreg, "SubregOrganization", "LoginName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szRegistrantName = sb_szRegistrantName.toString( );}
   //:IF szRegistrantName != szAttemptLoginName
   if ( ZeidonStringCompare( szRegistrantName, 1, 0, szAttemptLoginName, 1, 0, 51 ) != 0 )
   { 
      //:ACTIVATE qOrganiz WHERE qOrganiz.Organization.LoginName = szAttemptLoginName
      o_fnLocalBuildQual_22( ViewToWindow, vTempViewVar_0, szAttemptLoginName );
      RESULT = ActivateObjectInstance( qOrganiz, "qOrganiz", ViewToWindow, vTempViewVar_0, zSINGLE );
      DropView( vTempViewVar_0 );
      //:IF qOrganiz.Organization EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( qOrganiz, "Organization" );
      if ( lTempInteger_2 == 0 )
      { 

         //:DropObjectInstance( qOrganiz )
         DropObjectInstance( qOrganiz );
         //:MessageSend( ViewToWindow, "", "Update Subregistrant",
         //:             "The registrant Login Name must be unique.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( ViewToWindow, "", "Update Subregistrant", "The registrant Login Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
         m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
         //:RETURN 2
         if(8==8)return( 2 );
      } 


      //:END

      //:DropObjectInstance( qOrganiz )
      DropObjectInstance( qOrganiz );
   } 

   //:END

   //:// Ensure registrant name is not blank.
   //:szRegistrantName = mSubreg.SubregOrganization.Name
   {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
   StringBuilder sb_szRegistrantName;
   if ( szRegistrantName == null )
      sb_szRegistrantName = new StringBuilder( 32 );
   else
      sb_szRegistrantName = new StringBuilder( szRegistrantName );
       GetVariableFromAttribute( sb_szRegistrantName, mi_lTempInteger_3, 'S', 51, mSubreg, "SubregOrganization", "Name", "", 0 );
   lTempInteger_3 = mi_lTempInteger_3.intValue( );
   szRegistrantName = sb_szRegistrantName.toString( );}
   //:lRegistrantNameLth = zGetStringLen( szRegistrantName )
   lRegistrantNameLth = zGetStringLen( szRegistrantName );
   //:TraceLineI( "Registrant Name Length: ", lRegistrantNameLth )
   TraceLineI( "Registrant Name Length: ", lRegistrantNameLth );
   //:IF lRegistrantNameLth < 1
   if ( lRegistrantNameLth < 1 )
   { 
      //:MessageSend( ViewToWindow, "", "Update Subregistrant",
      //:             "The registrant Organization Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Subregistrant", "The registrant Organization Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Ensure password matches primary registrant password.
   //:// IF mSubreg.Subregistrant.AdministratorPassword != wWebXfer.Root.AttemptPassword
   //://
   //://    MessageSend( ViewToWindow, "", "Update Subregistrant",
   //://                 "Invalid password.",
   //://                 zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //://    SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   //://    RETURN 2
   //:// END

   //:// Set login name just in case it's new.
   //:mSubreg.SubregOrganization.LoginName = szAttemptLoginName
   SetAttributeFromString( mSubreg, "SubregOrganization", "LoginName", szAttemptLoginName );

   //:// AcceptSubobject( mSubreg, "ContactPerson" )
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
   //:RETURN 0
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

   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mPrimReg, "ContactPerson" )
   //:// CancelSubobject( mPrimReg, "PhysicalAddress" )
   //:// CancelSubobject( mPrimReg, "MailingAddress" )
   //:// CancelSubobject( mPrimReg, "Organization" )
   //:CancelSubobject( mPrimReg, "PrimaryRegistrant" )
   CancelSubobject( mPrimReg, "PrimaryRegistrant" );
   //:DropObjectInstance( mPrimReg )
   DropObjectInstance( mPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CancelNewPrimRegUser( VIEW ViewToWindow )

//:   VIEW mCurrentUser REGISTERED AS mCurrentUser
public int 
CancelNewPrimRegUser( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mPerson REGISTERED AS mPerson
   zVIEW    mPerson = new zVIEW( );

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );

   //:// CancelSubobject( mPerson, "Address" )
   //:CancelSubobject( mPerson, "Person" )
   CancelSubobject( mPerson, "Person" );
   //:CancelSubobject( mCurrentUser, "User" )
   CancelSubobject( mCurrentUser, "User" );
   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AcceptUpdatePrimRegUser( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptUpdatePrimRegUser( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mPerson  REGISTERED AS mPerson
   zVIEW    mPerson = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:STRING (  50  ) szUserName
   String   szUserName = null;
   //:STRING (  50  ) szAttemptUserName
   String   szAttemptUserName = null;
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
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mPerson, "mPerson", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:// Ensure the user is the same one that logged in, or that the user knows
   //:// the password of this user in order to update the information.
   //:GET VIEW qPrimReg NAMED "qPrimRegLogin"
   RESULT = GetViewByName( qPrimReg, "qPrimRegLogin", ViewToWindow, zLEVEL_TASK );
   //:// IF qPrimReg.PrimaryRegistrant.ID != lPrimReg.PrimaryRegistrant.ID AND
   //://    lPrimReg.User.UserPassword != wWebXfer.Root.AttemptPassword

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
   //:nRC = CompareAttributeToString( lPrimReg, "User", "UserPassword", szAttemptPassword )
   nRC = CompareAttributeToString( lPrimReg, "User", "UserPassword", szAttemptPassword );
   //:IF nRC != 0 AND
   //:   qPrimReg.PrimaryRegistrant.ID != lPrimReg.PrimaryRegistrant.ID
   if ( nRC != 0 && CompareAttributeToAttribute( qPrimReg, "PrimaryRegistrant", "ID", lPrimReg, "PrimaryRegistrant", "ID" ) != 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Update Primary Registrant User",
      //:             "Verification password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant User", "Verification password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Ensure user login name is not blank and is unique.
   //:szUserName = mCurrentUser.User.UserName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_1, 'S', 51, mCurrentUser, "User", "UserName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
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

      //:MessageSend( ViewToWindow, "", "Update Primary Registrant User",
      //:             "The User Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant User", "The User Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );

      //:ELSE
   } 
   else
   { 

      //:szAttemptUserName = wWebXfer.Root.AttemptUserName
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szAttemptUserName;
      if ( szAttemptUserName == null )
         sb_szAttemptUserName = new StringBuilder( 32 );
      else
         sb_szAttemptUserName = new StringBuilder( szAttemptUserName );
             GetVariableFromAttribute( sb_szAttemptUserName, mi_lTempInteger_2, 'S', 51, wWebXfer, "Root", "AttemptUserName", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szAttemptUserName = sb_szAttemptUserName.toString( );}
      //:IF szUserName != szAttemptUserName
      if ( ZeidonStringCompare( szUserName, 1, 0, szAttemptUserName, 1, 0, 51 ) != 0 )
      { 

         //:lControl = zQUAL_STRING + zPOS_FIRST + zQUAL_SCOPE_OI + zTEST_CSR_RESULT
         lControl = zQUAL_STRING + zPOS_FIRST + zQUAL_SCOPE_OI + zTEST_CSR_RESULT;
         //:IF SetEntityCursor( lPrimReg, "User", "UserName", lControl,
         //:                    szUserName, "", "", 0, "", "" ) >= zCURSOR_SET
         lTempInteger_3 = SetEntityCursor( lPrimReg, "User", "UserName", lControl, szUserName, "", "", 0, "", "" );
         if ( lTempInteger_3 >= zCURSOR_SET )
         { 
            //:MessageSend( ViewToWindow, "", "Update Primary Registrant User",
            //:             "The User Name must be unique.",
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( ViewToWindow, "", "Update Primary Registrant User", "The User Name must be unique.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
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

   //:// Ensure user first and last names are not blank.
   //:szUserName = mPerson.Person.FirstName
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_4, 'S', 51, mPerson, "Person", "FirstName", "", 0 );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );
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
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant User",
      //:             "The user First Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant User", "The user First Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:szUserName = mPerson.Person.LastName
   {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
   StringBuilder sb_szUserName;
   if ( szUserName == null )
      sb_szUserName = new StringBuilder( 32 );
   else
      sb_szUserName = new StringBuilder( szUserName );
       GetVariableFromAttribute( sb_szUserName, mi_lTempInteger_5, 'S', 51, mPerson, "Person", "LastName", "", 0 );
   lTempInteger_5 = mi_lTempInteger_5.intValue( );
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
      //:MessageSend( ViewToWindow, "", "Update Primary Registrant User",
      //:             "The user Last Name cannot be blank.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Update Primary Registrant User", "The user Last Name cannot be blank.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// AcceptSubobject( mPerson, "Person" )
   //:// AcceptSubobject( mPerson, "Address" )
   //:AcceptSubobject( mCurrentUser, "User" )
   AcceptSubobject( mCurrentUser, "User" );

   //:Commit mPerson
   RESULT = CommitObjectInstance( mPerson );
   //:Commit mCurrentUser
   RESULT = CommitObjectInstance( mCurrentUser );

   //:lID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lID = new MutableInt( lID );
       GetIntegerFromAttribute( mi_lID, lPrimReg, "PrimaryRegistrant", "ID" );
   lID = mi_lID.intValue( );}

   //:DropObjectInstance( mPerson )
   DropObjectInstance( mPerson );
   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
   //:DropObjectInstance( lPrimReg )
   DropObjectInstance( lPrimReg );

   //:// Activate the "selected" primary registrant ... just in case someone added or
   //:// deleted a primary registrant user.
   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = lID
   o_fnLocalBuildQual_32( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitListSubregistrants( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitListSubregistrants( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg BASED ON LOD lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:INTEGER lID
   int      lID = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

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
   o_fnLocalBuildQual_19( ViewToWindow, vTempViewVar_0, lID );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   //:wWebXfer.Root.Banner4 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner4", "" );
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

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
InitSelectSubregistrant( View     ViewToWindow )
{

   //:InitSelectSubregistrant( VIEW ViewToWindow )

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ProcessLogout( VIEW ViewToWindow )

//:   VIEW pePamms  BASED ON LOD pePamms
public int 
ProcessLogout( View     ViewToWindow )
{
   zVIEW    pePamms = new zVIEW( );
   //:VIEW mCurrentUser BASED ON LOD mUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW wWebXfer BASED ON LOD  wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW qSubreg  BASED ON LOD  qSubreg
   zVIEW    qSubreg = new zVIEW( );
   //:VIEW lSubreg  BASED ON LOD  lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mOrganiz BASED ON LOD  mOrganiz
   zVIEW    mOrganiz = new zVIEW( );
   //:VIEW KZXMLPGO
   zVIEW    KZXMLPGO = new zVIEW( );
   int      RESULT = 0;


   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu Logout" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu Logout" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW pePamms NAMED "pePamms"
   RESULT = GetViewByName( pePamms, "pePamms", ViewToWindow, zLEVEL_TASK );
   //:IF pePamms != 0
   if ( getView( pePamms ) != null )
   { 
      //:DropObjectInstance( pePamms )
      DropObjectInstance( pePamms );
   } 

   //:END

   //:GET VIEW mCurrentUser NAMED "mCurrentUser"
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   //:IF mCurrentUser != 0
   if ( getView( mCurrentUser ) != null )
   { 
      //:DropObjectInstance( mCurrentUser )
      DropObjectInstance( mCurrentUser );
   } 

   //:END

   //:GET VIEW wWebXfer NAMED "wWebXfer"
   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   //:IF wWebXfer != 0
   if ( getView( wWebXfer ) != null )
   { 
      //:DropObjectInstance( wWebXfer )
      DropObjectInstance( wWebXfer );
   } 

   //:END

   //:GET VIEW qPrimReg NAMED "qPrimRegLogin"
   RESULT = GetViewByName( qPrimReg, "qPrimRegLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
   } 

   //:END

   //:GET VIEW qSubreg NAMED "qSubregLogin"
   RESULT = GetViewByName( qSubreg, "qSubregLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qSubreg != 0
   if ( getView( qSubreg ) != null )
   { 
      //:DropObjectInstance( qSubreg )
      DropObjectInstance( qSubreg );
   } 

   //:END

   //:GET VIEW qOrganiz NAMED "qOrganizLogin"
   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qOrganiz != 0
   if ( getView( qOrganiz ) != null )
   { 
      //:DropObjectInstance( qOrganiz )
      DropObjectInstance( qOrganiz );
   } 

   //:END

   //:GET VIEW lPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg != 0
   if ( getView( lPrimReg ) != null )
   { 
      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:GET VIEW KZXMLPGO NAMED "KZXMLPGO"
   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );
   //:IF KZXMLPGO != 0
   if ( getView( KZXMLPGO ) != null )
   { 
      //:FOR EACH KZXMLPGO.NextDialogWindow
      RESULT = SetCursorFirstEntity( KZXMLPGO, "NextDialogWindow", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY KZXMLPGO.NextDialogWindow
         RESULT = DeleteEntity( KZXMLPGO, "NextDialogWindow", zPOS_NEXT );
         RESULT = SetCursorNextEntity( KZXMLPGO, "NextDialogWindow", "" );
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
public int 
ProcessLogin( View     ViewToWindow )
{

   //:ProcessLogin( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu Login" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu Login" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ListSubregProducts( VIEW ViewToWindow )

public int 
ListSubregProducts( View     ViewToWindow )
{

   return( 0 );
//    // Nothing to do at this point.
// END
} 


//:DIALOG OPERATION
//:InitLoginWindow( VIEW ViewToWindow )

//:   VIEW wWebXfer BASED ON LOD wWebXfer
public int 
InitLoginWindow( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   //:VIEW pePamms  BASED ON LOD pePamms
   zVIEW    pePamms = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:ProcessLogout( ViewToWindow )  // just to ensure clean up
   ProcessLogout( ViewToWindow );

   //:ACTIVATE wWebXfer EMPTY
   RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", ViewToWindow, zSINGLE );
   //:NAME VIEW wWebXfer "wWebXfer"
   SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );
   //:CREATE ENTITY wWebXfer.Root
   RESULT = CreateEntity( wWebXfer, "Root", zPOS_AFTER );

   //:wWebXfer.Root.Banner1 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner1", "" );
   //:wWebXfer.Root.Banner2 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner2", "" );
   //:wWebXfer.Root.Banner3 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner3", "" );
   //:wWebXfer.Root.Banner4 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner4", "" );
   //:wWebXfer.Root.Banner5 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner5", "" );
   //:wWebXfer.Root.Banner6 = ""
   SetAttributeFromString( wWebXfer, "Root", "Banner6", "" );

   //:// Remove these lines prior to deployment!!!
   //:// wWebXfer.Root.AttemptLoginName = "Lonza"
   //:// wWebXfer.Root.AttemptLoginName = "Admin"
   //:wWebXfer.Root.AttemptLoginName = "atp"
   SetAttributeFromString( wWebXfer, "Root", "AttemptLoginName", "atp" );
   //:wWebXfer.Root.AttemptUserName = "Admin"
   SetAttributeFromString( wWebXfer, "Root", "AttemptUserName", "Admin" );
   //:wWebXfer.Root.AttemptPassword = "xxxxxxxx"
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "xxxxxxxx" );
   //:// End of: Remove these lines prior to deployment!!!

   //:// Note that Activate always returns at least an empty view.
   //:ACTIVATE pePamms WHERE pePamms.ePamms.ID = 1
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( pePamms, "pePamms", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW pePamms "pePamms"
   SetNameForView( pePamms, "pePamms", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Default" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Default" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSelectPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW lPrimReg BASED ON LOD lPrimReg
public int 
InitSelectPrimaryRegistrant( View     ViewToWindow )
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

   //:ACTIVATE lPrimReg ROOTONLYMULTIPLE
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSelectSubregistrants( VIEW ViewToWindow )

//:   VIEW qSubreg BASED ON LOD qSubreg
public int 
InitSelectSubregistrants( View     ViewToWindow )
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

   //:ACTIVATE qSubreg ROOTONLYMULTIPLE
   RESULT = ActivateObjectInstance( qSubreg, "qSubreg", ViewToWindow, 0, zACTIVATE_ROOTONLY_MULTIPLE );
   //:NAME VIEW qSubreg "qSubreg"
   SetNameForView( qSubreg, "qSubreg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CheckExistencePrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW qPrimReg BASED ON LOD qPrimReg
public int 
CheckExistencePrimaryRegistrant( View     ViewToWindow )
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

   //:// Ensure that at least the primary registrant exists.
   //:ACTIVATE qPrimReg ROOTONLY
   RESULT = ActivateObjectInstance( qPrimReg, "qPrimReg", ViewToWindow, 0, zACTIVATE_ROOTONLY );
   //:NAME VIEW qPrimReg "qPrimReg"
   SetNameForView( qPrimReg, "qPrimReg", null, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Select Primary Registrant",
      //:             "No Primary Registrants exist ... please go to Administration.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Primary Registrant", "No Primary Registrants exist ... please go to Administration.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:DropObjectInstance( qPrimReg )
   DropObjectInstance( qPrimReg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:CheckExistenceSubregistrant( VIEW ViewToWindow )

//:   VIEW qSubreg BASED ON LOD qSubreg
public int 
CheckExistenceSubregistrant( View     ViewToWindow )
{
   zVIEW    qSubreg = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:GET VIEW qSubreg NAMED "qSubreg"
   RESULT = GetViewByName( qSubreg, "qSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF  qSubreg != 0
   if ( getView( qSubreg ) != null )
   { 
      //:DropObjectInstance( qSubreg )
      DropObjectInstance( qSubreg );
   } 

   //:END

   //:ACTIVATE qSubreg ROOTONLY
   RESULT = ActivateObjectInstance( qSubreg, "qSubreg", ViewToWindow, 0, zACTIVATE_ROOTONLY );
   //:NAME VIEW qSubreg "qSubreg"
   SetNameForView( qSubreg, "qSubreg", null, zLEVEL_TASK );

   //:IF qSubreg.Subregistrant DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( qSubreg, "Subregistrant" );
   if ( lTempInteger_0 != 0 )
   { 

      //:MessageSend( ViewToWindow, "", "Select Subregistrant",
      //:             "No Subregistrants exist ... please go to Administration.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Select Subregistrant", "No Subregistrants exist ... please go to Administration.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:DropObjectInstance( qSubreg )
      DropObjectInstance( qSubreg );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:DropObjectInstance( qSubreg )
   DropObjectInstance( qSubreg );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AutoLoginSubregistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AutoLoginSubregistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptLoginName = "SmallCorp"
   SetAttributeFromString( wWebXfer, "Root", "AttemptLoginName", "SmallCorp" );
   //:wWebXfer.Root.AttemptUserName = "Admin"
   SetAttributeFromString( wWebXfer, "Root", "AttemptUserName", "Admin" );
   //:wWebXfer.Root.AttemptPassword = "xxxxxxxx"
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "xxxxxxxx" );
   //:RefreshWindow( ViewToWindow )
   m_ZDRVROPR.RefreshWindow( ViewToWindow );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
//:InitPrimaryRegistrantForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrimaryRegistrantForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

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

   //:// If the primary registrant is not found, kind of a surprise!!!
   //:ACTIVATE mPrimReg WHERE mPrimReg.PrimaryRegistrant.ID = lPrimReg.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lPrimReg, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_15( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );
   //:IF mPrimReg.PrimaryRegistrant DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mPrimReg, "PrimaryRegistrant" );
   if ( lTempInteger_1 != 0 )
   { 

      //:// Cannot find primary registrant ... go back.
      //:MessageSend( ViewToWindow, "", "Delete Primary Registrant",
      //:             "Primary Registrant not found",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Delete Primary Registrant", "Primary Registrant not found", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wStartUp", "ListPrimaryRegistrants" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "ListPrimaryRegistrants" );
      //:DropObjectInstance( mPrimReg )
      DropObjectInstance( mPrimReg );
      //:RETURN 1
      if(8==8)return( 1 );
   } 


   //:END

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Administration" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:wWebXfer.Root.AttemptLoginName = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptLoginName", "" );
   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitSubregistrantForDelete( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitSubregistrantForDelete( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "" );
   //:wWebXfer.Root.ConfirmPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "ConfirmPassword", "" );
   //:wWebXfer.Root.CurrentPassword = ""
   SetAttributeFromString( wWebXfer, "Root", "CurrentPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectListSubregistrants( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
SelectListSubregistrants( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW qOrganiz BASED ON LOD  qOrganiz
   zVIEW    qOrganiz = new zVIEW( );
   //:VIEW qPrimReg BASED ON LOD  qPrimReg
   zVIEW    qPrimReg = new zVIEW( );
   //:VIEW lPrimReg BASED ON LOD  lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW qSubreg  BASED ON LOD  qSubreg
   zVIEW    qSubreg = new zVIEW( );
   //:VIEW lSubreg  BASED ON LOD  lSubreg
   zVIEW    lSubreg = new zVIEW( );
   //:STRING ( 1  ) szKeyRole
   String   szKeyRole = null;
   //:INTEGER       lID
   int      lID = 0;
   //:SHORT         nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:IF wWebXfer = 0
   if ( getView( wWebXfer ) == null )
   { 
      //:TraceLineS( "wStartUp.SelectListSubregistrants cannot find Transfer View", "" )
      TraceLineS( "wStartUp.SelectListSubregistrants cannot find Transfer View", "" );
      //:MessageSend( ViewToWindow, "", "Product Management",
      //:             "Invalid Transfer View ... being redirected to Login",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Product Management", "Invalid Transfer View ... being redirected to Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow,
      //:                         "wStartUp", "UserLogin" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow, "wStartUp", "UserLogin" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END

   //:GET VIEW qOrganiz NAMED "qOrganizLogin"
   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qOrganiz = 0
   if ( getView( qOrganiz ) == null )
   { 
      //:TraceLineS( "wStartUp.SelectListSubregistrants cannot find Organization View", "" )
      TraceLineS( "wStartUp.SelectListSubregistrants cannot find Organization View", "" );
      //:MessageSend( ViewToWindow, "", "Product Management",
      //:             "Invalid Organization View ... being redirected to Login",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Product Management", "Invalid Organization View ... being redirected to Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow,
      //:                         "wStartUp", "UserLogin" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_ResetTopWindow, "wStartUp", "UserLogin" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END

   //:IF wWebXfer.Root.LoginName = "Admin"
   if ( CompareAttributeToString( wWebXfer, "Root", "LoginName", "Admin" ) == 0 )
   { 

      //:MessageSend( ViewToWindow, "", "List Subregistrants",
      //:             "Admin does not have Subregistrants",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "List Subregistrants", "Admin does not have Subregistrants", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
      //:RETURN 2
      if(8==8)return( 2 );
   } 


   //:END

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu List Subregistrants" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu List Subregistrants" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:GET VIEW qPrimReg NAMED "qPrimRegLogin"
   RESULT = GetViewByName( qPrimReg, "qPrimRegLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qPrimReg != 0
   if ( getView( qPrimReg ) != null )
   { 
      //:DropObjectInstance( qPrimReg )
      DropObjectInstance( qPrimReg );
   } 

   //:END

   //:GET VIEW qSubreg NAMED "qSubregLogin"
   RESULT = GetViewByName( qSubreg, "qSubregLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qSubreg != 0
   if ( getView( qSubreg ) != null )
   { 
      //:DropObjectInstance( qSubreg )
      DropObjectInstance( qSubreg );
   } 

   //:END

   //:GET VIEW qPrimReg NAMED "lPrimReg"
   RESULT = GetViewByName( qPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF lPrimReg != 0
   if ( getView( lPrimReg ) != null )
   { 
      //:DropObjectInstance( lPrimReg )
      DropObjectInstance( lPrimReg );
   } 

   //:END

   //:GET VIEW lSubreg NAMED "lSubreg"
   RESULT = GetViewByName( lSubreg, "lSubreg", ViewToWindow, zLEVEL_TASK );
   //:IF lSubreg != 0
   if ( getView( lSubreg ) != null )
   { 
      //:DropObjectInstance( lSubreg )
      DropObjectInstance( lSubreg );
   } 

   //:END

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:ACTIVATE lPrimReg WHERE lPrimReg.PrimaryRegistrant.ID = qOrganiz.PrimaryRegistrant.ID
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, qOrganiz, "PrimaryRegistrant", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   o_fnLocalBuildQual_38( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( lPrimReg, "lPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW lPrimReg "lPrimReg"
   SetNameForView( lPrimReg, "lPrimReg", null, zLEVEL_TASK );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:EmailSelectedSubregistrants( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
EmailSelectedSubregistrants( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:// INTEGER lCount
   //:STRING ( 1000 ) szEmailErrors
   String   szEmailErrors = null;
   //:STRING ( 2000 ) szErrorMsg
   String   szErrorMsg = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:szEmailErrors = ""
    {StringBuilder sb_szEmailErrors;
   if ( szEmailErrors == null )
      sb_szEmailErrors = new StringBuilder( 32 );
   else
      sb_szEmailErrors = new StringBuilder( szEmailErrors );
      ZeidonStringCopy( sb_szEmailErrors, 1, 0, "", 1, 0, 1001 );
   szEmailErrors = sb_szEmailErrors.toString( );}

   //:/* Done in jsp
   //:// Make sure at least one Subregistrant was selected.
   //:lCount = 0
   //:FOR EACH lPrimReg.PrimarySub
   //:   IF lPrimReg.Subregistrant.wkSelected = "Y"
   //:      lCount = lCount + 1
   //:   END
   //:END
   //:IF lCount = 0
   //:   MessageSend( ViewToWindow, "", "Email Selected Subregistrants",
   //:                "At least one Subregistrant must be selected.",
   //:                zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   //:   RETURN 2
   //:END
   //:*/

   //:// Create subobject of email addresses for each SELECTED Subregistrant.

   //:FOR EACH wWebXfer.EmailListEntry
   RESULT = SetCursorFirstEntity( wWebXfer, "EmailListEntry", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY wWebXfer.EmailListEntry NONE
      RESULT = DeleteEntity( wWebXfer, "EmailListEntry", zREPOS_NONE );
      RESULT = SetCursorNextEntity( wWebXfer, "EmailListEntry", "" );
   } 

   //:END

   //:FOR EACH lPrimReg.PrimarySub
   RESULT = SetCursorFirstEntity( lPrimReg, "PrimarySub", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF lPrimReg.Subregistrant.wkSelected = "Y" AND
      //:   lPrimReg.SubregContactPerson.EmailAddress != ""
      if ( CompareAttributeToString( lPrimReg, "Subregistrant", "wkSelected", "Y" ) == 0 && CompareAttributeToString( lPrimReg, "SubregContactPerson", "EmailAddress", "" ) != 0 )
      { 

         //:CREATE ENTITY wWebXfer.EmailListEntry
         RESULT = CreateEntity( wWebXfer, "EmailListEntry", zPOS_AFTER );
         //:wWebXfer.EmailListEntry.FirstName    = lPrimReg.SubregContactPerson.FirstName
         SetAttributeFromAttribute( wWebXfer, "EmailListEntry", "FirstName", lPrimReg, "SubregContactPerson", "FirstName" );
         //:wWebXfer.EmailListEntry.MiddleName   = lPrimReg.SubregContactPerson.MiddleName
         SetAttributeFromAttribute( wWebXfer, "EmailListEntry", "MiddleName", lPrimReg, "SubregContactPerson", "MiddleName" );
         //:wWebXfer.EmailListEntry.LastName     = lPrimReg.SubregContactPerson.LastName
         SetAttributeFromAttribute( wWebXfer, "EmailListEntry", "LastName", lPrimReg, "SubregContactPerson", "LastName" );
         //:wWebXfer.EmailListEntry.EmailAddress = lPrimReg.SubregContactPerson.EmailAddress
         SetAttributeFromAttribute( wWebXfer, "EmailListEntry", "EmailAddress", lPrimReg, "SubregContactPerson", "EmailAddress" );
         //:wWebXfer.EmailListEntry.ID           = lPrimReg.Subregistrant.ID
         SetAttributeFromAttribute( wWebXfer, "EmailListEntry", "ID", lPrimReg, "Subregistrant", "ID" );
      } 

      //:END

      //:IF lPrimReg.Subregistrant.wkSelected = "Y" AND
      //:   lPrimReg.SubregContactPerson.EmailAddress = ""
      if ( CompareAttributeToString( lPrimReg, "Subregistrant", "wkSelected", "Y" ) == 0 && CompareAttributeToString( lPrimReg, "SubregContactPerson", "EmailAddress", "" ) == 0 )
      { 
         //:IF  szEmailErrors != ""
         if ( ZeidonStringCompare( szEmailErrors, 1, 0, "", 1, 0, 1001 ) != 0 )
         { 
            //:szEmailErrors = szEmailErrors + "," + NEW_LINE
             {StringBuilder sb_szEmailErrors;
            if ( szEmailErrors == null )
               sb_szEmailErrors = new StringBuilder( 32 );
            else
               sb_szEmailErrors = new StringBuilder( szEmailErrors );
                        ZeidonStringConcat( sb_szEmailErrors, 1, 0, ",", 1, 0, 1001 );
            szEmailErrors = sb_szEmailErrors.toString( );}
             {StringBuilder sb_szEmailErrors;
            if ( szEmailErrors == null )
               sb_szEmailErrors = new StringBuilder( 32 );
            else
               sb_szEmailErrors = new StringBuilder( szEmailErrors );
                        ZeidonStringConcat( sb_szEmailErrors, 1, 0, NEW_LINE, 1, 0, 1001 );
            szEmailErrors = sb_szEmailErrors.toString( );}
         } 

         //:END

         //:// We must give an error for each Subregistrant that was not emailed.
         //:szEmailErrors = szEmailErrors + lPrimReg.SubregContactPerson.LastName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 65, lPrimReg, "SubregContactPerson", "LastName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szEmailErrors;
         if ( szEmailErrors == null )
            sb_szEmailErrors = new StringBuilder( 32 );
         else
            sb_szEmailErrors = new StringBuilder( szEmailErrors );
                  ZeidonStringConcat( sb_szEmailErrors, 1, 0, szTempString_0, 1, 0, 1001 );
         szEmailErrors = sb_szEmailErrors.toString( );}
         //:szEmailErrors = szEmailErrors + ", "
          {StringBuilder sb_szEmailErrors;
         if ( szEmailErrors == null )
            sb_szEmailErrors = new StringBuilder( 32 );
         else
            sb_szEmailErrors = new StringBuilder( szEmailErrors );
                  ZeidonStringConcat( sb_szEmailErrors, 1, 0, ", ", 1, 0, 1001 );
         szEmailErrors = sb_szEmailErrors.toString( );}
         //:szEmailErrors = szEmailErrors + lPrimReg.SubregContactPerson.FirstName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 65, lPrimReg, "SubregContactPerson", "FirstName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szEmailErrors;
         if ( szEmailErrors == null )
            sb_szEmailErrors = new StringBuilder( 32 );
         else
            sb_szEmailErrors = new StringBuilder( szEmailErrors );
                  ZeidonStringConcat( sb_szEmailErrors, 1, 0, szTempString_1, 1, 0, 1001 );
         szEmailErrors = sb_szEmailErrors.toString( );}
      } 

      //:END

      //:lPrimReg.Subregistrant.wkSelected = ""
      SetAttributeFromString( lPrimReg, "Subregistrant", "wkSelected", "" );
      RESULT = SetCursorNextEntity( lPrimReg, "PrimarySub", "" );
   } 

   //:END

   //:wWebXfer.Root.RecipientEmailAddress = wWebXfer.Root.UserEmailAddress
   SetAttributeFromAttribute( wWebXfer, "Root", "RecipientEmailAddress", wWebXfer, "Root", "UserEmailAddress" );

   //:IF  szEmailErrors != ""
   if ( ZeidonStringCompare( szEmailErrors, 1, 0, "", 1, 0, 1001 ) != 0 )
   { 
      //:// The following line causes extensive errors.
      //:// szEmailErrors = "The was an error sending emails to the following Subregistrant(s): " + NEW_LINE + szEmailErrors
      //:szErrorMsg = "The following Subregistrants had no email address and are not shown in list below: " + NEW_LINE + NEW_LINE + szEmailErrors
       {StringBuilder sb_szErrorMsg;
      if ( szErrorMsg == null )
         sb_szErrorMsg = new StringBuilder( 32 );
      else
         sb_szErrorMsg = new StringBuilder( szErrorMsg );
            ZeidonStringCopy( sb_szErrorMsg, 1, 0, "The following Subregistrants had no email address and are not shown in list below: ", 1, 0, 2001 );
      szErrorMsg = sb_szErrorMsg.toString( );}
       {StringBuilder sb_szErrorMsg;
      if ( szErrorMsg == null )
         sb_szErrorMsg = new StringBuilder( 32 );
      else
         sb_szErrorMsg = new StringBuilder( szErrorMsg );
            ZeidonStringConcat( sb_szErrorMsg, 1, 0, NEW_LINE, 1, 0, 2001 );
      szErrorMsg = sb_szErrorMsg.toString( );}
       {StringBuilder sb_szErrorMsg;
      if ( szErrorMsg == null )
         sb_szErrorMsg = new StringBuilder( 32 );
      else
         sb_szErrorMsg = new StringBuilder( szErrorMsg );
            ZeidonStringConcat( sb_szErrorMsg, 1, 0, NEW_LINE, 1, 0, 2001 );
      szErrorMsg = sb_szErrorMsg.toString( );}
       {StringBuilder sb_szErrorMsg;
      if ( szErrorMsg == null )
         sb_szErrorMsg = new StringBuilder( 32 );
      else
         sb_szErrorMsg = new StringBuilder( szErrorMsg );
            ZeidonStringConcat( sb_szErrorMsg, 1, 0, szEmailErrors, 1, 0, 2001 );
      szErrorMsg = sb_szErrorMsg.toString( );}
      //:MessageSend( ViewToWindow, "", "Send Email",
      //:             szErrorMsg,
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Send Email", szErrorMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectListSubregistrantUsers( VIEW ViewToWindow )

public int 
SelectListSubregistrantUsers( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:InitPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW lPrimReg REGISTERED AS lPrimReg
   zVIEW    lPrimReg = new zVIEW( );
   //:VIEW mPrimReg BASED ON LOD  mPrimReg
   zVIEW    mPrimReg = new zVIEW( );
   int      lTempInteger_0 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( lPrimReg, "lPrimReg", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mPrimReg NAMED "mPrimReg"
   RESULT = GetViewByName( mPrimReg, "mPrimReg", ViewToWindow, zLEVEL_TASK );
   //:IF  mPrimReg != 0
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
   o_fnLocalBuildQual_9( ViewToWindow, vTempViewVar_0, lTempInteger_0 );
   RESULT = ActivateObjectInstance( mPrimReg, "mPrimReg", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:NAME VIEW mPrimReg "mPrimReg"
   SetNameForView( mPrimReg, "mPrimReg", null, zLEVEL_TASK );

   //:IF mPrimReg.MailingAddress DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( mPrimReg, "MailingAddress" );
   if ( lTempInteger_1 != 0 )
   { 
      //:CREATE ENTITY mPrimReg.MailingAddress
      RESULT = CreateEntity( mPrimReg, "MailingAddress", zPOS_AFTER );
      //:wWebXfer.Root.SameAs = "Y"
      SetAttributeFromString( wWebXfer, "Root", "SameAs", "Y" );
      //:SetMatchingAttributesByName( mPrimReg, "MailingAddress",
      //:                             mPrimReg, "PhysicalAddress", zSET_NOTNULL )
      SetMatchingAttributesByName( mPrimReg, "MailingAddress", mPrimReg, "PhysicalAddress", zSET_NOTNULL );
   } 

   //:END

   //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReturnFromAdminPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW qOrganiz BASED ON LOD  qOrganiz
public int 
ReturnFromAdminPrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    qOrganiz = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW qOrganiz NAMED "qOrganizLogin"
   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qOrganiz != 0 AND qOrganiz.Organization.LoginName = "Admin"
   if ( qOrganiz != null && CompareAttributeToString( qOrganiz, "Organization", "LoginName", "Admin" ) == 0 )
   { 
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wStartUp", "AdminListPrimaryRegistrants" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminListPrimaryRegistrants" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ReturnFromAdminSubregistrantList( VIEW ViewToWindow )

//:   VIEW qOrganiz BASED ON LOD  qOrganiz
public int 
ReturnFromAdminSubregistrantList( View     ViewToWindow )
{
   zVIEW    qOrganiz = new zVIEW( );
   int      RESULT = 0;


   //:GET VIEW qOrganiz NAMED "qOrganizLogin"
   RESULT = GetViewByName( qOrganiz, "qOrganizLogin", ViewToWindow, zLEVEL_TASK );
   //:IF qOrganiz != 0 AND qOrganiz.Organization.LoginName = "Admin"
   if ( qOrganiz != null && CompareAttributeToString( qOrganiz, "Organization", "LoginName", "Admin" ) == 0 )
   { 
      //:SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow,
      //:                         "wStartUp", "AdminListPrimaryRegistrants" )
      m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StartTopWindow, "wStartUp", "AdminListPrimaryRegistrants" );
      //:RETURN 1
      if(8==8)return( 1 );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:AutoLoginPrimaryRegistrant( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AutoLoginPrimaryRegistrant( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:wWebXfer.Root.AttemptLoginName = "Lonza"
   SetAttributeFromString( wWebXfer, "Root", "AttemptLoginName", "Lonza" );
   //:wWebXfer.Root.AttemptUserName = "Admin"
   SetAttributeFromString( wWebXfer, "Root", "AttemptUserName", "Admin" );
   //:wWebXfer.Root.AttemptPassword = "xxxxxxxx"
   SetAttributeFromString( wWebXfer, "Root", "AttemptPassword", "xxxxxxxx" );
   //:RefreshWindow( ViewToWindow )
   m_ZDRVROPR.RefreshWindow( ViewToWindow );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
public int 
WebDevelopment( View     ViewToWindow )
{

   //:WebDevelopment( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu WebDevelopment" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu WebDevelopment" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:MessageSend( ViewToWindow, "", "Web Development",
   //:             "Web Development and Maintenance not yet implemented.",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Web Development", "Web Development and Maintenance not yet implemented.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
public int 
TrackingNotificationCompliance( View     ViewToWindow )
{

   //:TrackingNotificationCompliance( VIEW ViewToWindow )

   //:AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu TrackingNotificationCompliance" )
   {
    ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
    m_ZGlobalV_Operation.AcceptCurrentTemporalSubobject( ViewToWindow, TRUE, "TopMenu TrackingNotificationCompliance" );
    // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
   }

   //:MessageSend( ViewToWindow, "", "Tracking/Notification/Compliance",
   //:             "Tracking/Notification/Compliance not yet implemented.",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Tracking/Notification/Compliance", "Tracking/Notification/Compliance not yet implemented.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
public int 
SubregistrantMaintenance( View     ViewToWindow )
{

   //:SubregistrantMaintenance( VIEW ViewToWindow )

   //:MessageSend( ViewToWindow, "", "Subregistrant Maintenance",
   //:             "Subregistrant Maintenance not yet implemented.",
   //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Subregistrant Maintenance", "Subregistrant Maintenance not yet implemented.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
   m_ZDRVROPR.SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" );
   //:RETURN 2
   return( 2 );
// END
} 


//:DIALOG OPERATION
//:InitPortal( VIEW ViewToWindow )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
InitPortal( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING (   1  ) szKeyRole
   String   szKeyRole = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:szKeyRole = wWebXfer.Root.KeyRole
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szKeyRole;
   if ( szKeyRole == null )
      sb_szKeyRole = new StringBuilder( 32 );
   else
      sb_szKeyRole = new StringBuilder( szKeyRole );
       GetVariableFromAttribute( sb_szKeyRole, mi_lTempInteger_0, 'S', 2, wWebXfer, "Root", "KeyRole", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szKeyRole = sb_szKeyRole.toString( );}
   //:IF szKeyRole = "S"
   if ( ZeidonStringCompare( szKeyRole, 1, 0, "S", 1, 0, 2 ) == 0 )
   { 
      //:SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "Subregistrant" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
      //:ELSE
   } 
   else
   { 
      //:SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" )
      {
       ZGlobalV_Operation m_ZGlobalV_Operation = new ZGlobalV_Operation( ViewToWindow );
       m_ZGlobalV_Operation.SetDynamicBannerName( ViewToWindow, "wStartUp", "PrimaryRegistrant" );
       // m_ZGlobalV_Operation = null;  // permit gc  (unnecessary)
      }
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:SelectListSubregistrantProducts( VIEW ViewToWindow )

public int 
SelectListSubregistrantProducts( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:LoadCommitForTestPurposes( VIEW ViewToWindow )
//:/*
public int 
LoadCommitForTestPurposes( View     ViewToWindow )
{

   return( 0 );
//    VIEW mTempl   BASED ON LOD  mTempl
//    VIEW mPrimReg BASED ON LOD  mPrimReg
//    VIEW mEPA     BASED ON LOD  mEPA
//    VIEW mMasLC   BASED ON LOD  mMasLC
//    VIEW mMasProd BASED ON LOD  mMasProd
//    GET VIEW mTempl NAMED "mTempl"
//    IF mTempl != 0
//       DropObjectInstance( mTempl )
//    END
//    ActivateOI_FromFile( mTempl, "mTempl", ViewToWindow,
//                         "c:\lplr\epamms\bin\mTempl1.por", zIGNORE_ERRORS )
//    SetNameForView( mTempl, "mTempl", 0, zLEVEL_TASK )
//    MiSetInstanceUpdateFlag( mTempl, 1 )
// // MessageSend( ViewToWindow, "", "Save Template",
// //              "Check view: mTempl", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//    COMMIT mTempl
// // DropObjectInstance( mTempl )
//    GET VIEW mEPA NAMED "mEPA"
//    IF mEPA != 0
//       DropObjectInstance( mEPA )
//    END
//    ActivateOI_FromFile( mEPA, "mEPA", ViewToWindow,
//                         "c:\lplr\epamms\bin\mEPA.por", zIGNORE_ERRORS )
//    SetNameForView( mEPA, "mEPA", 0, zLEVEL_TASK )
//    MiSetInstanceUpdateFlag( mEPA, 1 )
// // MessageSend( ViewToWindow, "", "Save mEPA",
// //              "Check view: mEPA", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//    COMMIT mEPA
// // DropObjectInstance( mEPA )
//    GET VIEW mPrimReg NAMED "mPrimReg"
//    IF  mPrimReg != 0
//       DropObjectInstance( mPrimReg )
//    END
//    ActivateOI_FromFile( mPrimReg, "mPrimReg", ViewToWindow,
//                         "c:\lplr\epamms\bin\mPrimReg.por", zIGNORE_ERRORS )
//    SetNameForView( mPrimReg, "mPrimReg", 0, zLEVEL_TASK )
//    MiSetInstanceUpdateFlag( mPrimReg, 1 )
// // MessageSend( ViewToWindow, "", "Save mPrimReg",
// //              "Check view: mPrimReg", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//    COMMIT mPrimReg
// // DropObjectInstance( mPrimReg )
//    GET VIEW mMasProd NAMED "mMasProd"
//    IF mMasProd != 0
//       DropObjectInstance( mMasProd )
//    END
//    ActivateOI_FromFile( mMasProd, "mMasProd", ViewToWindow,
//                         "c:\lplr\epamms\bin\mMasProd.por", zIGNORE_ERRORS )
//    SetNameForView( mMasProd, "mMasProd", 0, zLEVEL_TASK )
//    MiSetInstanceUpdateFlag( mMasProd, 1 )
// // MessageSend( ViewToWindow, "", "Save mMasProd",
// //              "Check view: mMasProd", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//    COMMIT mMasProd
// // DropObjectInstance( mMasProd )
//    GET VIEW mMasLC NAMED "mMasLC"
//    IF mMasLC != 0
//       DropObjectInstance( mMasLC )
//    END
//    ActivateOI_FromFile( mMasLC, "mMasLC", ViewToWindow,
//                         "c:\lplr\epamms\bin\mMasLC.por", zIGNORE_ERRORS )
//    SetNameForView( mMasLC, "mMasLC", 0, zLEVEL_TASK )
//    MiSetInstanceUpdateFlag( mMasLC, 1 )
// // MessageSend( ViewToWindow, "", "Save mMasLC",
// //              "Check view: mMasLC", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//    COMMIT mMasLC
// // DropObjectInstance( mMasLC )
// */
// END
} 


//:DIALOG OPERATION
//:SelectSubregUserForUpdate( VIEW ViewToWindow )

public int 
SelectSubregUserForUpdate( View     ViewToWindow )
{

   return( 0 );
// END
} 


//:DIALOG OPERATION
//:UpdateColor( VIEW ViewToWindow )

public int 
UpdateColor( View     ViewToWindow )
{

   return( 0 );
//    // Just for positioning.
// END
} 


//:DIALOG OPERATION
//:CancelColor( VIEW ViewToWindow )

//:   VIEW mSubreg REGISTERED AS  mSubreg
public int 
CancelColor( View     ViewToWindow )
{
   zVIEW    mSubreg = new zVIEW( );
   int      RESULT = 0;

   RESULT = GetViewByName( mSubreg, "mSubreg", ViewToWindow, zLEVEL_TASK );

   //:IF mSubreg.Color.wkCreated = "Y"
   if ( CompareAttributeToString( mSubreg, "Color", "wkCreated", "Y" ) == 0 )
   { 
      //:DELETE ENTITY mSubreg.Color
      RESULT = DeleteEntity( mSubreg, "Color", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 



}
