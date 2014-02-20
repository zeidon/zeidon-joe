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

package com.quinsoft.noa;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.noa.ZGLOBAL1_Operation;

/**
 * @author QuinSoft
 *
 */

public class wNoaTest_Dialog extends VmlDialog
{
   public wNoaTest_Dialog( View view )
   {
      super( view );
   }


//:DIALOG OPERATION
//:LoginClose( VIEW ViewToWindow )
//:   VIEW mCurrentUser  REGISTERED AS mCurrentUser
public int 
LoginClose( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wXferO REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   //:DropObjectInstance( mCurrentUser )
   DropObjectInstance( mCurrentUser );
   //:DropObjectInstance( wXferO )
   DropObjectInstance( wXferO );
   return( 0 );
// END
} 


private int 
o_fnLocalBuildQual_2( View     vSubtask,
                      zVIEW    vQualObject,
                      int      lTempInteger_4 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_4 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_3( View     vSubtask,
                      zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Currency" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Currency" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "CurrencyID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "1" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_4( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_2 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "LanguageLabels" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "LanguageLabels" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Language_z" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_2.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_5( View     vSubtask,
                      zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "LanguageLabels" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "LanguageLabels" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Language_z" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "EN" );
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
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "InActive" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
o_fnLocalBuildQual_1( View     vSubtask,
                      zVIEW    vQualObject,
                      String   szTempString_0 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "User" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "UserName" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0.toString( ) );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "AND" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "User" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "InActive" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:DIALOG OPERATION
//:SELECT_Division( VIEW ViewToWindow )
//:   VIEW wWebXfer     REGISTERED AS wWebXfer
public int 
SELECT_Division( View     ViewToWindow )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser REGISTERED AS mCurrentUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW lSysDefLST   BASED ON LOD  mSysDef
   zVIEW    lSysDefLST = new zVIEW( );
   //:VIEW KZXMLPGO
   zVIEW    KZXMLPGO = new zVIEW( );
   //:VIEW mView 
   zVIEW    mView = new zVIEW( );
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );

   //:// Transfer to the correct starting window. Execute any special code for the selected role as necessary.
   //:// Note that the SelectedFlag has been set for the PersonRole.

   //:GET VIEW KZXMLPGO NAMED "KZXMLPGO"
   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );
   //:CREATE ENTITY KZXMLPGO.NextDialogWindow
   RESULT = CreateEntity( KZXMLPGO, "NextDialogWindow", zPOS_AFTER );
   //:KZXMLPGO.NextDialogWindow.DialogName = wWebXfer.PersonRole.DialogName 
   SetAttributeFromAttribute( KZXMLPGO, "NextDialogWindow", "DialogName", wWebXfer, "PersonRole", "DialogName" );
   //:KZXMLPGO.NextDialogWindow.WindowName = wWebXfer.PersonRole.WindowName 
   SetAttributeFromAttribute( KZXMLPGO, "NextDialogWindow", "WindowName", wWebXfer, "PersonRole", "WindowName" );

   //:IF mCurrentUser.CurrentDivision EXISTS 
   lTempInteger_0 = CheckExistenceOfEntity( mCurrentUser, "CurrentDivision" );
   if ( lTempInteger_0 == 0 )
   { 
      //:EXCLUDE  mCurrentUser.CurrentDivision  
      RESULT = ExcludeEntity( mCurrentUser, "CurrentDivision", zREPOS_AFTER );
   } 

   //:END

   //:INCLUDE mCurrentUser.CurrentDivision FROM mCurrentUser.Division 
   RESULT = IncludeSubobjectFromSubobject( mCurrentUser, "CurrentDivision", mCurrentUser, "Division", zPOS_AFTER );

   //:IF mCurrentUser.CurrentUserGroup EXISTS 
   lTempInteger_1 = CheckExistenceOfEntity( mCurrentUser, "CurrentUserGroup" );
   if ( lTempInteger_1 == 0 )
   { 
      //:EXCLUDE mCurrentUser.CurrentUserGroup    
      RESULT = ExcludeEntity( mCurrentUser, "CurrentUserGroup", zREPOS_AFTER );
   } 

   //:END
   //:SET CURSOR FIRST mCurrentUser.UserGroupDivision WITHIN mCurrentUser.User 
   //:   WHERE mCurrentUser.UserGroupDivision.DivisionID = mCurrentUser.CurrentDivision.DivisionID 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
       GetIntegerFromAttribute( mi_lTempInteger_2, mCurrentUser, "CurrentDivision", "DivisionID" );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );}
   RESULT = mCurrentUser.cursor( "UserGroupDivision" ).setFirst( "DivisionID", lTempInteger_2, "User" ).toInt();
   //:IF RESULT >= zCURSOR_SET 
   if ( RESULT >= zCURSOR_SET )
   { 
      //: INCLUDE mCurrentUser.CurrentUserGroup FROM mCurrentUser.UserGroup 
      RESULT = IncludeSubobjectFromSubobject( mCurrentUser, "CurrentUserGroup", mCurrentUser, "UserGroup", zPOS_AFTER );
   } 

   //:END 

   //:GET VIEW lSysDefLST NAMED "lSysDefLST"
   RESULT = GetViewByName( lSysDefLST, "lSysDefLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( lSysDefLST ) 
      DropView( lSysDefLST );
   } 

   //:END

   //:ACTIVATE lSysDefLST MULTIPLE
   RESULT = ActivateObjectInstance( lSysDefLST, "mSysDef", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lSysDefLST  "lSysDefLST"
   SetNameForView( lSysDefLST, "lSysDefLST", null, zLEVEL_TASK );

   //:GET VIEW mView NAMED "lAccountLST"
   RESULT = GetViewByName( mView, "lAccountLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lAccountLIASLST"
   RESULT = GetViewByName( mView, "lAccountLIASLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lBudgetEntityLST"
   RESULT = GetViewByName( mView, "lBudgetEntityLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lAreaOfBenefitLST"
   RESULT = GetViewByName( mView, "lAreaOfBenefitLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lBankActLST"
   RESULT = GetViewByName( mView, "lBankActLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lProjectLST"
   RESULT = GetViewByName( mView, "lProjectLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lEmployLST"
   RESULT = GetViewByName( mView, "lEmployLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   //:GET VIEW mView NAMED "lVendorLST"
   RESULT = GetViewByName( mView, "lVendorLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( mView )
      DropView( mView );
   } 

   //:END 
   return( 0 );
//    
// END 
} 


//:DIALOG OPERATION
//:CHANGE_Password( VIEW ViewToWindow )

//:   VIEW mCurrentUser   REGISTERED AS mCurrentUser
public int 
CHANGE_Password( View     ViewToWindow )
{
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   //:STRING (  128  ) szOldPassword
   String   szOldPassword = null;
   //:STRING (  128  ) szNewPassword
   String   szNewPassword = null;
   //:STRING (  128  ) szConfirmPassword
   String   szConfirmPassword = null;
   //:STRING (  128  ) szCurrentPassword
   String   szCurrentPassword = null;
   //:INTEGER          PasswordLength
   int      PasswordLength = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( mCurrentUser, "mCurrentUser", ViewToWindow, zLEVEL_TASK );

   //:szOldPassword = mCurrentUser.User.wOldPassword 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szOldPassword;
   if ( szOldPassword == null )
      sb_szOldPassword = new StringBuilder( 32 );
   else
      sb_szOldPassword = new StringBuilder( szOldPassword );
       GetVariableFromAttribute( sb_szOldPassword, mi_lTempInteger_0, 'S', 129, mCurrentUser, "User", "wOldPassword", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szOldPassword = sb_szOldPassword.toString( );}
   //:szNewPassword = mCurrentUser.User.wNewPassword 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szNewPassword;
   if ( szNewPassword == null )
      sb_szNewPassword = new StringBuilder( 32 );
   else
      sb_szNewPassword = new StringBuilder( szNewPassword );
       GetVariableFromAttribute( sb_szNewPassword, mi_lTempInteger_1, 'S', 129, mCurrentUser, "User", "wNewPassword", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szNewPassword = sb_szNewPassword.toString( );}
   //:szConfirmPassword = mCurrentUser.User.wConfirmNewPassword 
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szConfirmPassword;
   if ( szConfirmPassword == null )
      sb_szConfirmPassword = new StringBuilder( 32 );
   else
      sb_szConfirmPassword = new StringBuilder( szConfirmPassword );
       GetVariableFromAttribute( sb_szConfirmPassword, mi_lTempInteger_2, 'S', 129, mCurrentUser, "User", "wConfirmNewPassword", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szConfirmPassword = sb_szConfirmPassword.toString( );}
   //:GetStringFromAttributeByContext( szCurrentPassword, mCurrentUser, "User", "UserPassword", "Password", 128 )
   {StringBuilder sb_szCurrentPassword;
   if ( szCurrentPassword == null )
      sb_szCurrentPassword = new StringBuilder( 32 );
   else
      sb_szCurrentPassword = new StringBuilder( szCurrentPassword );
       GetStringFromAttributeByContext( sb_szCurrentPassword, mCurrentUser, "User", "UserPassword", "Password", 128 );
   szCurrentPassword = sb_szCurrentPassword.toString( );}
   //:TraceLineS( "Current Password:", szCurrentPassword )
   TraceLineS( "Current Password:", szCurrentPassword );

   //:// 1: Make sure old passord is correct
   //:IF szOldPassword != szCurrentPassword
   if ( ZeidonStringCompare( szOldPassword, 1, 0, szCurrentPassword, 1, 0, 129 ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Change Password", 
      //:             "Existing password is not correct.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Password", "Existing password is not correct.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:// 2: Make sure new password is at least 8 characters long.
   //:PasswordLength = zGetStringLen( szNewPassword )
   PasswordLength = zGetStringLen( szNewPassword );
   //:TraceLineI( "Password Length: ", PasswordLength )
   TraceLineI( "Password Length: ", PasswordLength );
   //:IF PasswordLength < 8
   if ( PasswordLength < 8 )
   { 
      //:MessageSend( ViewToWindow, "", "Change Password", 
      //:             "The new password must be at least 8 characters long.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Password", "The new password must be at least 8 characters long.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// 3: make sure new and confirmed password matches.
   //:IF szNewPassword != szConfirmPassword
   if ( ZeidonStringCompare( szNewPassword, 1, 0, szConfirmPassword, 1, 0, 129 ) != 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Change Password", 
      //:             "The new password and the re-entered password do not match.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Change Password", "The new password and the re-entered password do not match.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:// Set password to new password
   //:// mCurrentUser.User.Password = szNewPassword
   //:SetAttrFromStrByContext(mCurrentUser, "User", "UserPassword", szNewPassword, "Password" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( mCurrentUser );
    m_ZGLOBAL1_Operation.SetAttrFromStrByContext( mCurrentUser, "User", "UserPassword", szNewPassword, "Password" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //://TraceLineS( "Newly Set Password:", mCurrentUser.User.Password )
   //:// Commit change
   //:COMMIT mCurrentUser 
   RESULT = CommitObjectInstance( mCurrentUser );
   //:mCurrentUser.User.wOldPassword = ""
   SetAttributeFromString( mCurrentUser, "User", "wOldPassword", "" );
   //:mCurrentUser.User.wNewPassword = ""
   SetAttributeFromString( mCurrentUser, "User", "wNewPassword", "" );
   //:mCurrentUser.User.wConfirmNewPassword = ""
   SetAttributeFromString( mCurrentUser, "User", "wConfirmNewPassword", "" );
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:ProcessLogin( VIEW ViewToWindow )
//:   VIEW wXferO       REGISTERED AS wXferO
public int 
ProcessLogin( View     ViewToWindow )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mCurrentUser BASED ON LOD  mUser
   zVIEW    mCurrentUser = new zVIEW( );
   //:VIEW mUser        BASED ON LOD  mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW mCurr        BASED ON LOD  mCurr
   zVIEW    mCurr = new zVIEW( );
   //:VIEW lSysDefLST   BASED ON LOD  mSysDef
   zVIEW    lSysDefLST = new zVIEW( );
   //:VIEW wWebXfer     BASED ON LOD  wWebXfer
   zVIEW    wWebXfer = new zVIEW( );
   //:VIEW mLang        BASED ON LOD  mLang
   zVIEW    mLang = new zVIEW( );
   //:VIEW KZXMLPGO
   zVIEW    KZXMLPGO = new zVIEW( );
   //:SHORT         nRC
   int      nRC = 0;
   //:INTEGER       DivCount
   int      DivCount = 0;
   //:INTEGER       nTaskID
   int      nTaskID = 0;
   //:STRING (100) szInfo
   String   szInfo = null;
   //:INTEGER       nAttempt
   int      nAttempt = 0;
   //:INTEGER       nMax
   int      nMax = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   zVIEW    vTempViewVar_1 = new zVIEW( );
   zVIEW    vTempViewVar_2 = new zVIEW( );
   String   szTempString_2 = null;
   zVIEW    vTempViewVar_3 = new zVIEW( );
   zVIEW    vTempViewVar_4 = new zVIEW( );

   RESULT = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );


   //:IF wXferO = 0
   if ( getView( wXferO ) == null )
   { 
      //:TraceLineS( "wStartUp.ProcessLogin cannot find Transfer View", "" )
      TraceLineS( "wStartUp.ProcessLogin cannot find Transfer View", "" );
      //:return( 2 )
      if(8==8)return( ( 2 ) );
   } 

   //:END
   //:szInfo =  wXferO.Root.wAttemptedUserName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szInfo;
   if ( szInfo == null )
      sb_szInfo = new StringBuilder( 32 );
   else
      sb_szInfo = new StringBuilder( szInfo );
       GetVariableFromAttribute( sb_szInfo, mi_lTempInteger_0, 'S', 101, wXferO, "Root", "wAttemptedUserName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szInfo = sb_szInfo.toString( );}
   //:   TraceLineS( "szInfo szInfo szInfo szInfo szInfo ", szInfo )
   TraceLineS( "szInfo szInfo szInfo szInfo szInfo ", szInfo );

   //:   MessageSend( ViewToWindow, "", "Login", szInfo , zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Login", szInfo, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:// Find a user by that name.
   //:ACTIVATE mCurrentUser 
   //:  WHERE mCurrentUser.User.UserName = wXferO.Root.wAttemptedUserName
   //:    AND mCurrentUser.User.InActive = ""
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wXferO, "Root", "wAttemptedUserName" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_1( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mCurrentUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:// no user found
      //:DropObjectInstance( mCurrentUser )
      DropObjectInstance( mCurrentUser );
      //:wXferO.Root.wAttemptedUserName = ""
      SetAttributeFromString( wXferO, "Root", "wAttemptedUserName", "" );
      //:wXferO.Root.wAttemptedPassword = ""
      SetAttributeFromString( wXferO, "Root", "wAttemptedPassword", "" );
      //:TraceLineS( "******* ", "Invalid Login" )
      TraceLineS( "******* ", "Invalid Login" );
      //:MessageSend( ViewToWindow, "", "Login", "Invalid Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Login", "Invalid Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:NAME VIEW mCurrentUser "mCurrentUser"
   SetNameForView( mCurrentUser, "mCurrentUser", null, zLEVEL_TASK );

   //:GET VIEW lSysDefLST NAMED "lSysDefLST"
   RESULT = GetViewByName( lSysDefLST, "lSysDefLST", ViewToWindow, zLEVEL_TASK );
   //:IF RESULT > 0 
   if ( RESULT > 0 )
   { 
      //:DropView( lSysDefLST ) 
      DropView( lSysDefLST );
   } 

   //:END
   //:ACTIVATE lSysDefLST MULTIPLE
   RESULT = ActivateObjectInstance( lSysDefLST, "mSysDef", ViewToWindow, 0, zMULTIPLE );
   //:NAME VIEW lSysDefLST  "lSysDefLST"
   SetNameForView( lSysDefLST, "lSysDefLST", null, zLEVEL_TASK );

   //:SET CURSOR FIRST lSysDefLST.SysDefault 
   //:   WHERE lSysDefLST.SysDefault.DefaultName = "MaxLoginAttempt"
   RESULT = lSysDefLST.cursor( "SysDefault" ).setFirst( "DefaultName", "MaxLoginAttempt" ).toInt();
   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:nMax = lSysDefLST.SysDefault.DefaultValue 
      {MutableInt mi_nMax = new MutableInt( nMax );
             GetIntegerFromAttribute( mi_nMax, lSysDefLST, "SysDefault", "DefaultValue" );
      nMax = mi_nMax.intValue( );}
      //:ELSE 
   } 
   else
   { 
      //:nMax = 3
      nMax = 3;
   } 

   //:END
   //:DropView( lSysDefLST )
   DropView( lSysDefLST );


   //:// Match the password
   //:nAttempt = mCurrentUser.User.LoginAttempt
   {MutableInt mi_nAttempt = new MutableInt( nAttempt );
       GetIntegerFromAttribute( mi_nAttempt, mCurrentUser, "User", "LoginAttempt" );
   nAttempt = mi_nAttempt.intValue( );}
   //:IF mCurrentUser.User.LoginAttempt >= nMax
   if ( CompareAttributeToInteger( mCurrentUser, "User", "LoginAttempt", nMax ) >= 0 )
   { 
      //:MessageSend( ViewToWindow, "", "Login", "You have reached the maximum number of login attempt. Please contact your RFC", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Login", "You have reached the maximum number of login attempt. Please contact your RFC", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END 
   //:IF mCurrentUser.User.UserPassword != wXferO.Root.wAttemptedPassword
   if ( CompareAttributeToAttribute( mCurrentUser, "User", "UserPassword", wXferO, "Root", "wAttemptedPassword" ) != 0 )
   { 
      //:nAttempt = nAttempt + 1
      nAttempt = nAttempt + 1;
      //:wXferO.Root.wAttemptedUserName = ""
      SetAttributeFromString( wXferO, "Root", "wAttemptedUserName", "" );
      //:wXferO.Root.wAttemptedPassword = ""
      SetAttributeFromString( wXferO, "Root", "wAttemptedPassword", "" );
      //:TraceLineS( "******* ", "Invalid Login" )
      TraceLineS( "******* ", "Invalid Login" );
      //:mCurrentUser.User.LoginAttempt  = nAttempt
      SetAttributeFromInteger( mCurrentUser, "User", "LoginAttempt", nAttempt );
      //:COMMIT mCurrentUser
      RESULT = CommitObjectInstance( mCurrentUser );
      //:MessageSend( ViewToWindow, "", "Login", "Invalid Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Login", "Invalid Login", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:mCurrentUser.User.LoginAttempt  = 0
   SetAttributeFromInteger( mCurrentUser, "User", "LoginAttempt", 0 );
   //:COMMIT mCurrentUser
   RESULT = CommitObjectInstance( mCurrentUser );

   //:FOR EACH  mCurrentUser.Division 
   RESULT = mCurrentUser.cursor( "Division" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF mCurrentUser.Division.InActive = "Y" 
      if ( CompareAttributeToString( mCurrentUser, "Division", "InActive", "Y" ) == 0 )
      { 
         //:DropEntity(  mCurrentUser, "Division", zREPOS_NONE )
         DropEntity( mCurrentUser, "Division", zREPOS_NONE );
      } 

      RESULT = mCurrentUser.cursor( "Division" ).setNextContinue().toInt();;
      //:END   
   } 

   //:END
   //:DivCount = 0
   DivCount = 0;
   //:FOR EACH mCurrentUser.Division 
   RESULT = mCurrentUser.cursor( "Division" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DivCount = DivCount + 1
      DivCount = DivCount + 1;
      RESULT = mCurrentUser.cursor( "Division" ).setNextContinue().toInt();;
   } 

   //:END
   //:IF DivCount < 1  
   if ( DivCount < 1 )
   { 
      //:TraceLineS( "******* ", "Invalid Division" )
      TraceLineS( "******* ", "Invalid Division" );
      //:MessageSend( ViewToWindow, "", "Login", "This user is not assigned to any active divisions", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( ViewToWindow, "", "Login", "This user is not assigned to any active divisions", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:IF mCurrentUser.CurrentDivision EXISTS 
   lTempInteger_1 = CheckExistenceOfEntity( mCurrentUser, "CurrentDivision" );
   if ( lTempInteger_1 == 0 )
   { 
      //:EXCLUDE  mCurrentUser.CurrentDivision  
      RESULT = ExcludeEntity( mCurrentUser, "CurrentDivision", zREPOS_AFTER );
   } 

   //:END
   //:IF mCurrentUser.CurrentUserGroup EXISTS 
   lTempInteger_2 = CheckExistenceOfEntity( mCurrentUser, "CurrentUserGroup" );
   if ( lTempInteger_2 == 0 )
   { 
      //:EXCLUDE mCurrentUser.CurrentUserGroup    
      RESULT = ExcludeEntity( mCurrentUser, "CurrentUserGroup", zREPOS_AFTER );
   } 

   //:END

   //:SET CURSOR FIRST mCurrentUser.Division 
   RESULT = mCurrentUser.cursor( "Division" ).setFirst().toInt();
   //:IF RESULT >= zCURSOR_SET  
   if ( RESULT >= zCURSOR_SET )
   { 
      //:INCLUDE mCurrentUser.CurrentDivision FROM mCurrentUser.Division 
      RESULT = IncludeSubobjectFromSubobject( mCurrentUser, "CurrentDivision", mCurrentUser, "Division", zPOS_AFTER );
      //://SET CURSOR FIRST mCurrentUser.UserGroup 
      //://   WHERE mCurrentUser.UserGroupDivision.DivisionID = mCurrentUser.CurrentDivision.DivisionID 
      //:SET CURSOR FIRST mCurrentUser.UserGroupDivision WITHIN mCurrentUser.User 
      //:   WHERE mCurrentUser.UserGroupDivision.DivisionID = mCurrentUser.CurrentDivision.DivisionID 
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetIntegerFromAttribute( mi_lTempInteger_3, mCurrentUser, "CurrentDivision", "DivisionID" );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      RESULT = mCurrentUser.cursor( "UserGroupDivision" ).setFirst( "DivisionID", lTempInteger_3, "User" ).toInt();
      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:INCLUDE mCurrentUser.CurrentUserGroup FROM mCurrentUser.UserGroup 
         RESULT = IncludeSubobjectFromSubobject( mCurrentUser, "CurrentUserGroup", mCurrentUser, "UserGroup", zPOS_AFTER );
      } 

      //:END 
   } 

   //:END 
   //:// Trace Logon.
   //:TraceLineS( "Logon for: ", mCurrentUser.User.UserName )
   {StringBuilder sb_szTempString_1;
   if ( szTempString_1 == null )
      sb_szTempString_1 = new StringBuilder( 32 );
   else
      sb_szTempString_1 = new StringBuilder( szTempString_1 );
       GetStringFromAttribute( sb_szTempString_1, mCurrentUser, "User", "UserName" );
   szTempString_1 = sb_szTempString_1.toString( );}
   TraceLineS( "Logon for: ", szTempString_1 );
   //:// add last login information
   //:ACTIVATE mUser 
   //:   WHERE mUser.User.UserID = mCurrentUser.User.UserID
   {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
       GetIntegerFromAttribute( mi_lTempInteger_4, mCurrentUser, "User", "UserID" );
   lTempInteger_4 = mi_lTempInteger_4.intValue( );}
   o_fnLocalBuildQual_2( ViewToWindow, vTempViewVar_1, lTempInteger_4 );
   RESULT = ActivateObjectInstance( mUser, "mUser", ViewToWindow, vTempViewVar_1, zSINGLE );
   DropView( vTempViewVar_1 );
   //:SET CURSOR LAST mUser.User 
   RESULT = mUser.cursor( "User" ).setLast().toInt();;
   //:CREATE ENTITY  mUser.UserLogEntry 
   RESULT = CreateEntity( mUser, "UserLogEntry", zPOS_AFTER );
   //:mUser.UserLogEntry.Description = "Last Logon"
   SetAttributeFromString( mUser, "UserLogEntry", "Description", "Last Logon" );
   //:mUser.UserLogEntry.DateTime = wXferO.Root.dCurrentDateTime      
   SetAttributeFromAttribute( mUser, "UserLogEntry", "DateTime", wXferO, "Root", "dCurrentDateTime" );
   //:COMMIT mUser
   RESULT = CommitObjectInstance( mUser );
   //:DropView( mUser )
   DropView( mUser );
   //:// add last login information
   //:SET CURSOR FIRST mCurrentUser.UserLogEntry 
   RESULT = mCurrentUser.cursor( "UserLogEntry" ).setFirst().toInt();
   //: 
   //:ACTIVATE wWebXfer EMPTY 
   RESULT = ActivateEmptyObjectInstance( wWebXfer, "wWebXfer", ViewToWindow, zSINGLE );
   //:NAME VIEW wWebXfer "wWebXfer"
   SetNameForView( wWebXfer, "wWebXfer", null, zLEVEL_TASK );
   //:CREATE ENTITY wWebXfer.Work
   RESULT = CreateEntity( wWebXfer, "Work", zPOS_AFTER );
   //:wWebXfer.Work.UserEmailAddress = "" // szEmailAddress
   SetAttributeFromString( wWebXfer, "Work", "UserEmailAddress", "" );

   //:// Determine what kind of login this is.
   //:// If a Person has multiple roles, create role display entries and take them to the LoginType window.
   //:DivCount = 0
   DivCount = 0;
   //:FOR EACH mCurrentUser.Division 
   RESULT = mCurrentUser.cursor( "Division" ).setFirst().toInt();
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DivCount = DivCount + 1
      DivCount = DivCount + 1;
      RESULT = mCurrentUser.cursor( "Division" ).setNextContinue().toInt();;
   } 

   //:END
   //:CREATE ENTITY wWebXfer.PersonRole
   RESULT = CreateEntity( wWebXfer, "PersonRole", zPOS_AFTER );
   //:wWebXfer.PersonRole.Type = "User"
   SetAttributeFromString( wWebXfer, "PersonRole", "Type", "User" );
   //:IF mCurrentUser.User.ReportOnlyUser = "Y"
   if ( CompareAttributeToString( mCurrentUser, "User", "ReportOnlyUser", "Y" ) == 0 )
   { 
      //:wWebXfer.PersonRole.DialogName = "wNOA"
      SetAttributeFromString( wWebXfer, "PersonRole", "DialogName", "wNOA" );
      //:wWebXfer.PersonRole.WindowName = "HomePage"
      SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "HomePage" );
      //:mCurrentUser.User.wRoleCount = DivCount
      SetAttributeFromInteger( mCurrentUser, "User", "wRoleCount", DivCount );
      //://DivCount = 1
      //:ELSE  
   } 
   else
   { 
      //:wWebXfer.PersonRole.DialogName = "wNOA"
      SetAttributeFromString( wWebXfer, "PersonRole", "DialogName", "wNOA" );
      //:wWebXfer.PersonRole.WindowName = "HomePage"
      SetAttributeFromString( wWebXfer, "PersonRole", "WindowName", "HomePage" );
      //:mCurrentUser.User.wRoleCount = DivCount 
      SetAttributeFromInteger( mCurrentUser, "User", "wRoleCount", DivCount );
   } 

   //:END

   //:ACTIVATE mCurr
   //:   WHERE mCurr.Currency.CurrencyID = 1 
   o_fnLocalBuildQual_3( ViewToWindow, vTempViewVar_2 );
   RESULT = ActivateObjectInstance( mCurr, "mCurr", ViewToWindow, vTempViewVar_2, zSINGLE );
   DropView( vTempViewVar_2 );
   //:INCLUDE wXferO.BaseCurrency   FROM mCurr.Currency 
   RESULT = IncludeSubobjectFromSubobject( wXferO, "BaseCurrency", mCurr, "Currency", zPOS_AFTER );
   //:DropView( mCurr )
   DropView( mCurr );

   //:IF mCurrentUser.User.Language = ""
   if ( CompareAttributeToString( mCurrentUser, "User", "Language", "" ) == 0 )
   { 
      //: mCurrentUser.User.Language = "EN"
      SetAttributeFromString( mCurrentUser, "User", "Language", "EN" );
   } 

   //:END
   //:ACTIVATE mLang
   //:   WHERE mLang.LanguageLabels.Language_z  = mCurrentUser.User.Language
   {StringBuilder sb_szTempString_2;
   if ( szTempString_2 == null )
      sb_szTempString_2 = new StringBuilder( 32 );
   else
      sb_szTempString_2 = new StringBuilder( szTempString_2 );
       GetStringFromAttribute( sb_szTempString_2, mCurrentUser, "User", "Language" );
   szTempString_2 = sb_szTempString_2.toString( );}
   o_fnLocalBuildQual_4( ViewToWindow, vTempViewVar_3, szTempString_2 );
   RESULT = ActivateObjectInstance( mLang, "mLang", ViewToWindow, vTempViewVar_3, zSINGLE );
   DropView( vTempViewVar_3 );
   //:IF RESULT < 0 
   if ( RESULT < 0 )
   { 
      //:ACTIVATE mLang
      //:   WHERE mLang.LanguageLabels.Language_z = "EN"
      o_fnLocalBuildQual_5( ViewToWindow, vTempViewVar_4 );
      RESULT = ActivateObjectInstance( mLang, "mLang", ViewToWindow, vTempViewVar_4, zSINGLE );
      DropView( vTempViewVar_4 );
   } 

   //:END
   //:NAME VIEW mLang "mLang"
   SetNameForView( mLang, "mLang", null, zLEVEL_TASK );

   //:// Process the Role Count and save it for later use.
   //:mCurrentUser.User.wRoleCount = DivCount 
   SetAttributeFromInteger( mCurrentUser, "User", "wRoleCount", DivCount );
   //:IF DivCount > 1
   if ( DivCount > 1 )
   { 
      //:GET VIEW KZXMLPGO NAMED "KZXMLPGO"
      RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );
      //:CREATE ENTITY KZXMLPGO.NextDialogWindow
      RESULT = CreateEntity( KZXMLPGO, "NextDialogWindow", zPOS_AFTER );
      //:KZXMLPGO.NextDialogWindow.DialogName = "wStartUp"
      SetAttributeFromString( KZXMLPGO, "NextDialogWindow", "DialogName", "wStartUp" );
      //:KZXMLPGO.NextDialogWindow.WindowName = "SelectDivision"
      SetAttributeFromString( KZXMLPGO, "NextDialogWindow", "WindowName", "SelectDivision" );
      //:ELSE
   } 
   else
   { 
      //:GET VIEW KZXMLPGO NAMED "KZXMLPGO"
      RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );
      //:CREATE ENTITY KZXMLPGO.NextDialogWindow
      RESULT = CreateEntity( KZXMLPGO, "NextDialogWindow", zPOS_AFTER );
      //:KZXMLPGO.NextDialogWindow.DialogName = wWebXfer.PersonRole.DialogName 
      SetAttributeFromAttribute( KZXMLPGO, "NextDialogWindow", "DialogName", wWebXfer, "PersonRole", "DialogName" );
      //:KZXMLPGO.NextDialogWindow.WindowName = wWebXfer.PersonRole.WindowName
      SetAttributeFromAttribute( KZXMLPGO, "NextDialogWindow", "WindowName", wWebXfer, "PersonRole", "WindowName" );

      //:GET VIEW lSysDefLST NAMED "lSysDefLST"
      RESULT = GetViewByName( lSysDefLST, "lSysDefLST", ViewToWindow, zLEVEL_TASK );
      //:IF RESULT > 0 
      if ( RESULT > 0 )
      { 
         //:DropView( lSysDefLST ) 
         DropView( lSysDefLST );
      } 

      //:END
      //:ACTIVATE lSysDefLST MULTIPLE
      RESULT = ActivateObjectInstance( lSysDefLST, "mSysDef", ViewToWindow, 0, zMULTIPLE );
      //:NAME VIEW lSysDefLST  "lSysDefLST"
      SetNameForView( lSysDefLST, "lSysDefLST", null, zLEVEL_TASK );
   } 

   //:END
   return( 0 );
// END
} 


//:DIALOG OPERATION
//:PrebuildLoginWindow( VIEW ViewToWindow )
//:   VIEW wXferO   BASED ON LOD wXferO
public int 
PrebuildLoginWindow( View     ViewToWindow )
{
   zVIEW    wXferO = new zVIEW( );
   //:VIEW mCurrentUser        BASED ON LOD  mUser
   zVIEW    mCurrentUser = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;
   zVIEW    vTempViewVar_0 = new zVIEW( );


   //:ACTIVATE wXferO EMPTY
   RESULT = ActivateEmptyObjectInstance( wXferO, "wXferO", ViewToWindow, zSINGLE );
   //:CREATE ENTITY wXferO.Root
   RESULT = CreateEntity( wXferO, "Root", zPOS_AFTER );
   //:NAME VIEW wXferO "wXferO"
   SetNameForView( wXferO, "wXferO", null, zLEVEL_TASK );
   //:MessageSend( ViewToWindow, "", "Login", "OK I am in Prebuild 2", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
   MessageSend( ViewToWindow, "", "Login", "OK I am in Prebuild 2", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   //:wXferO.Root.wAttemptedUserName = "jbeam"
   SetAttributeFromString( wXferO, "Root", "wAttemptedUserName", "jbeam" );
   //:ACTIVATE mCurrentUser 
   //:  WHERE mCurrentUser.User.UserName = wXferO.Root.wAttemptedUserName
   //:    AND mCurrentUser.User.InActive = ""
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wXferO, "Root", "wAttemptedUserName" );
   szTempString_0 = sb_szTempString_0.toString( );}
   o_fnLocalBuildQual_0( ViewToWindow, vTempViewVar_0, szTempString_0 );
   RESULT = ActivateObjectInstance( mCurrentUser, "mUser", ViewToWindow, vTempViewVar_0, zSINGLE );
   DropView( vTempViewVar_0 );
   return( 0 );
//        
// END
} 



}
