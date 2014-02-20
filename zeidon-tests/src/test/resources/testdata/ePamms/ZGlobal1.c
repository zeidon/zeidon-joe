/////////////////////////////////////////////////////////////////////////////
//
// .Name:         ZGLOBAL1.c
//
// .Version:      0.9e
//
// .Last change:  10.10.1996
//
// .Description:  Common global operations for Zeidon
//
/////////////////////////////////////////////////////////////////////////////
// .Change notes
//
// 0.9e = New
//        (25.09.1996) Quinsoft / Ton Beller GmbH
//
/////////////////////////////////////////////////////////////////////////////

#ifdef __cplusplus
extern "C"
{
#endif

#include <windows.h>

#define KZSYSSVC_INCL
#define KZOESYS_INCL
#include <KZOENGAA.H>
#include <ZDRVROPR.H>

#include <windows.h> // jsb 7/25/06

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include <wininet.h> // jsb 7/25/06

#include "ZEIDONOP.H"

#ifdef zGETPTR
   #undef zGETPTR
#endif

#define zGETPTR  SysGetPointerFromHandle


zPCHAR lpBufferArea;
typedef zSHORT (POPERATION zFARPROC_DYNOBJ)( zPVIEW, zVIEW );

zBOOL
FindItemByName( HKEY    hKey,
                zCPCHAR cpcValueName,
                DWORD   dwReturnType,
                zPCHAR  pchReturnData,
                DWORD   dwMaxReturnLth );

zOPER_EXPORT zSHORT  OPERATION
FTPSendFile( zVIEW  vSubtask,
             zPCHAR pszServerAddress,
             zPCHAR pszUserName,
             zPCHAR pszPassword,
             zPCHAR pszLocalFileName,
             zPCHAR pszServerFileName,
             zLONG  lControl );

/////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>SetAttributeFromCurrentDateTime
//
// .Description: Set an Attribut to Current Date Time
//
// .Return Value: zSHORT
//    (Same as SetAttributeFromString() )
//
// .Parameter:
//    Data type,      Name,         (I/O/U) Description
//    zVIEW           View            (I)   View for Attribute
//    zPCHAR          pchEntityName   (I)   Name of Entity
//    zPCHAR          pchAttributeName(I)   Name of Attribute
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT  OPERATION
SetAttributeFromCurrentDateTime( zVIEW  View,
                                 zPCHAR pchEntityName,
                                 zPCHAR pchAttributeName )
{
  zCHAR  szTimeStamp[ 22 ];
  zSHORT rc;

  SysGetDateTime( szTimeStamp );
  rc = SetAttributeFromString( View, pchEntityName, pchAttributeName,
                               szTimeStamp );
  return( rc );
}

/////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>DecimalSumOf
//
// .Description: Compute the sum of a decimal attribute over all
//               instances of an entity
//
// .Return Value: zDECIMAL - sum
//
// .Parameter:
//    Datentyp,       Name,         (I/O/U) Description
//    zVIEW           View            (I)   View for Entity
//    zPCHAR          pchEntityName   (I)   Name of Entity
//    zPCHAR          pchAttributeName(I)   Name of Attribute
//    zPCHAR          pchParentName   (I)   Name of Parent Entity
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zDECIMAL OPERATION
DecimalSumOf( zVIEW  vSum,
              zPCHAR pchEntityName,
              zPCHAR pchAttributeName,
              zPCHAR pchParentName )
{
   zDecimal DecimalSum;
   zDecimal DecimalValue;
   zINTEGER RESULT;

   DecimalSum = 0;

   RESULT = SetCursorFirstEntity( vSum, pchEntityName, pchParentName );
   while ( RESULT > zCURSOR_UNCHANGED )
   {
      GetDecimalFromAttribute( &DecimalValue,
                               vSum, pchEntityName, pchAttributeName );
      DecimalSum += DecimalValue;
      RESULT = SetCursorNextEntity( vSum, pchEntityName, pchParentName );
   }

   return( DecimalSum );
}

// Sets the cursor to the latest entity based on a date/time stamp
// attribute.  The attribute passed should be a date/time stamp, but
// could be any attribute with ascending colating sequence.
zOPER_EXPORT zSHORT OPERATION
SetCursorLatestEntity( zVIEW  View,
                       zPCHAR pchEntityName,
                       zPCHAR pchAttributeName )
{
   OrderEntityForView( View, pchEntityName, pchAttributeName );
   SetCursorLastEntity( View, pchEntityName, "" );

   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
GetIntFromAttrByContext( zPINTEGER lValue,
                         zVIEW  vView,
                         zPCHAR pchEntity,
                         zPCHAR pchAttribute,
                         zPCHAR pchContext )
{
   zSHORT rc;

   rc = GetVariableFromAttribute( (zPVOID) lValue, NULL, zTYPE_INTEGER, 0,
                                  vView, pchEntity, pchAttribute, pchContext, 0 );
   return( rc );
}

zOPER_EXPORT zSHORT OPERATION
GetStrFromAttrByContext( zPCHAR pchValue,
                         zLONG  lOrigLth,
                         zVIEW  vView,
                         zPCHAR pchEntity,
                         zPCHAR pchAttribute,
                         zPCHAR pchContext )
{
  zSHORT rc;
  zLONG  lLth;
  zCHAR  sz[ 500000 ];

  // If the Context value is null, use the default Context.
  if ( lOrigLth < sizeof( sz ) )
     lLth = lOrigLth;
  else
     lLth = sizeof( sz ) - 1;

  if ( *pchContext == 0 )
     rc = GetVariableFromAttribute( (zPVOID) &sz, NULL,
                                    zTYPE_STRING, lLth,
                                    vView, pchEntity, pchAttribute, 0, zUSE_DEFAULT_CONTEXT );
  else
     rc = GetVariableFromAttribute( (zPVOID) &sz, NULL,
                                    zTYPE_STRING, lLth,
                                    vView, pchEntity, pchAttribute, pchContext, 0);
  zstrcpy( pchValue, sz );
  return( rc );
}

zOPER_EXPORT zSHORT OPERATION
SetAttrFromIntByContext( zVIEW  vView,
                         zPCHAR pchEntity,
                         zPCHAR pchAttribute,
                         zINTEGER lValue,
                         zPCHAR pchContext )
{
  zSHORT rc;

  rc = SetAttributeFromVariable( vView, pchEntity, pchAttribute,
                                (zPVOID) &lValue, zTYPE_INTEGER, 0, pchContext, 0);
  return( rc );
}

zOPER_EXPORT zSHORT OPERATION
SetAttrFromStrByContext( zVIEW  vView,
                         zPCHAR pchEntity,
                         zPCHAR pchAttribute,
                         zPCHAR pchValue,
                         zPCHAR pchContext )
{
  zSHORT rc;

  // If the Context value is null, use the default Context.
  if ( *pchContext == 0 )
     rc = SetAttributeFromVariable( vView, pchEntity, pchAttribute,
                                    (zPVOID) pchValue, zTYPE_STRING, strlen( pchValue ),
                                    0, zUSE_DEFAULT_CONTEXT  );
  else
     rc = SetAttributeFromVariable( vView, pchEntity, pchAttribute,
                                    pchValue, zTYPE_STRING, strlen( pchValue ),
                                    pchContext, 0);
  return( rc );
}


zOPER_EXPORT zSHORT OPERATION
AddToAttrFromIntByContext( zVIEW vView,
                           zPCHAR pchEntity,
                           zPCHAR pchAttribute,
                           zLONG lValue,
                           zPCHAR pchContext )
{
  zSHORT rc;

  rc = AddToAttributeFromVariable( vView, pchEntity, pchAttribute,
                                   (zPVOID) &lValue, zTYPE_INTEGER, 0,
                                   pchContext);
  return( rc );
}

/////////////////////////////////////////////////////////////////////////////
//
// .Function name: >>>GetEnvVariable
//
// .Description: Get an environment variable
//
// .Return value: zSHORT
//    0 - OK
//    else Error
//
// .Parameter:
//    Data type,      Name,        (I/O/U) Description
//    zPCHAR          pchReturnWert   (O)   value of the env var (returned)
//    zPCHAR          pchVariableName (I)   name of the env var
//    zSHORT          nMaxReturnLth  (I)   max. length of pchReturnWert
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
GetEnvVariable( zPCHAR pchReturnWert,
                zPCHAR pchVariableName,
                zSHORT nMaxReturnLth )
{
   zSHORT nRC;

   nRC = SysGetEnvVar( pchReturnWert, pchVariableName, nMaxReturnLth );

   return( nRC );
}

/////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>StrToInt
//
// .Description: Convert an String to an Integer
//
// .Return Value: zLONG
//    (Integer Value of String )
//
// .Parameter:
//    Data type,      Name,       (I/O/U) Description
//    zPCHAR          szStr         (I)   String to convert
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zLONG  OPERATION
StrToInt( zPCHAR szStr )
{
   return( atol( szStr ) );
}

/////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>StrToDecimal
//
// .Description: Convert an String to a Decimal
//
// .Return Value: zDECIMAL
//             0 - OK
//             else: invalid string
//
// .Parameter:
//    Data type,      Name,       (I/O/U) Description
//    zPCHAR          szStr         (I)   String to convert
//
/////////////////////////////////////////////////////////////////////////////
// .Detail description
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zDECIMAL  OPERATION
StrToDecimal( zPCHAR szStr )
{
   return( (zDECIMAL) (zDecimal) szStr );
}

/////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>CodeToChar
//
// .Description: Returns the Char for the given code
//
// .Return Value: zVOID
//
// .Parameter:
//    Data type,      Name,       (I/O/U) Description
//    zPCHAR          szStr         (O)   String, which contains the char
//    zSHORT          sCode         (I)   Code for Char
//
/////////////////////////////////////////////////////////////////////////////
// .Detail description
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zVOID OPERATION
CodeToChar( zPCHAR szStr, zSHORT sCode )
{
   szStr[ 0 ] = (zCHAR) (sCode & 0x00ff);
   szStr[ 1 ] = (zCHAR) 0;
}

/////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>CharToCode
//
// .Description: Returns the code of a char (first char in string)
//
// .Return Value: zSHORT - code for char
//
// .Parameter:
//    Data type,      Name,       (I/O/U) Description
//    zPCHAR          szStr         (I)   String with char on first pos
//
/////////////////////////////////////////////////////////////////////////////
// .Detail description
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
CharToCode( zPCHAR szStr )
{
   zSHORT nCode = 0;

   nCode = (zSHORT) (zUSHORT) szStr[ 0 ];
   return( nCode );
}

zOPER_EXPORT zSHORT  OPERATION
GetDateAttributeDifferenceInDays( zPLONG plDays,
                                  zVIEW  vSourceView,
                                  zPCHAR pchSourceEntityName,
                                  zPCHAR pchSourceAttributeName,
                                  zVIEW  vTargetView,
                                  zPCHAR pchTargetEntityName,
                                  zPCHAR pchTargetAttributeName )
{
   DateTimeRecord SourceDate;
   DateTimeRecord TargetDate;
   zCHAR          szSourceDate[ 18 ];
   zCHAR          szTargetDate[ 18 ];
   zSHORT         nRC;

   // read the attributes
   nRC = GetStringFromAttribute( szSourceDate,
                                 vSourceView,
                                 pchSourceEntityName,
                                 pchSourceAttributeName );
   if ( nRC < 0 )
      return( zCALL_ERROR );

   nRC = GetStringFromAttribute( szTargetDate,
                                 vTargetView,
                                 pchTargetEntityName,
                                 pchTargetAttributeName );
   if ( nRC < 0 )
      return( zCALL_ERROR );

   nRC = UfStringToDateTime( szSourceDate, &SourceDate );
   if ( nRC < 0 )
   {
      MessageSend( vSourceView, 0, "Date Difference",
                   "Invalid type for attribute 1", 0, 0);
      return( zCALL_ERROR );
   }

   nRC = UfStringToDateTime( szTargetDate, &TargetDate );
   if ( nRC < 0 )
   {
      MessageSend( vSourceView, 0, "Date Difference",
                   "Invalid type for attribute 2", 0, 0 );
      return( zCALL_ERROR );
   }

   // subtract the values
   nRC = UfDateTimeDiff( plDays, &TargetDate, &SourceDate, zDT_DAY );

   return( nRC );
}

zOPER_EXPORT zSHORT  OPERATION
AddWorkingDaysToDate( zVIEW  vTargetView,
                      zPCHAR pchTargetEntityName,
                      zPCHAR pchTargetAttributeName,
                      zLONG  lWorkingDays )
{
   zLONG  lRegularDays;
   zLONG  lRemainder;
   zSHORT nRC;

   // Convert Working Days to Regular Days and add them to a Date
   // Attribute.
   // To determine regular days, we will take working daYS and
   // simply multiply by 7/5, using remainder and not fraction.
   // Thus 8 working days is divided by 5 to get 1 with remainder 3.
   // We multiply the 1 by 7 and add the 3 to get 10.

   lRegularDays = lWorkingDays / 5;
   lRemainder   = lWorkingDays - ( lRegularDays * 5 );
   lRegularDays = ( lRegularDays * 7 ) + lRemainder;

   nRC = AddToAttributeFromVariable( vTargetView,
                                    pchTargetEntityName,
                                    pchTargetAttributeName,
                                    (zPVOID) &lRegularDays,
                                     zTYPE_INTEGER, 0,
                                    "Day" );

   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
CompareAttributeByShortString( zVIEW  vView,
                               zPCHAR pchEntityName,
                               zPCHAR pchAttributeName,
                               zPCHAR pchStringValue )

{
   zSHORT nRC;
   zCHAR  szTempString[ 254 ];
   zSHORT nLth;

   GetVariableFromAttribute( szTempString, 0, zTYPE_STRING, 254,
                             vView, pchEntityName, pchAttributeName, 0, 0);
   nLth = zstrlen( pchStringValue );
   nRC = zstrncmp( szTempString, pchStringValue, nLth );

   return( nRC );
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ActivateWorkObjectFromFile
//
// Activate a work object from a
// file
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
ActivateWorkObjectFromFile( zPVIEW pvWorkView,
                            zPCHAR pchFileName,
                            zPCHAR pchLOD_Name,
                            zVIEW  ViewToWindow )
{
   zSHORT nRC;
   HFILE  hFile;

   // First make sure the file exists. If not, return an error code.
   hFile = (HFILE) SysOpenFile( ViewToWindow, pchFileName, COREFILE_READ );
   if ( hFile <= 0 )
      return( -1 );

   SysCloseFile( ViewToWindow, hFile, 0 );

   // Next Activate the OI from the file just created.
   nRC = ActivateOI_FromFile( pvWorkView, pchLOD_Name, ViewToWindow,
                              pchFileName,
                              zSINGLE );

   return( nRC );

} // ActivateWorkObjectFromFile

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: AddDaysToDate
//
// Add Days To a Date
// Attribute
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT  OPERATION
AddDaysToDate( zVIEW  vTargetView,
               zPCHAR pchTargetEntityName,
               zPCHAR pchTargetAttributeName,
               zLONG  lDays )
{
   zSHORT nRC;

   nRC = AddToAttributeFromVariable( vTargetView,
                                     pchTargetEntityName,
                                     pchTargetAttributeName,
                                     (zPVOID) &lDays,
                                     zTYPE_INTEGER, 0,
                                     "Day" );

   return( nRC );
} // AddDaysToDate

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: AddMonthsToDate
//
// Add Days To a Date
// Attribute
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT  OPERATION
AddMonthsToDate( zVIEW  vTargetView,
                 zPCHAR pchTargetEntityName,
                 zPCHAR pchTargetAttributeName,
                 zLONG  lDays )
{
   zSHORT nRC;

   nRC = AddToAttributeFromVariable( vTargetView,
                                     pchTargetEntityName,
                                     pchTargetAttributeName,
                                     (zPVOID) &lDays,
                                     zTYPE_INTEGER, 0,
                                     "Month" );

   return( nRC );
} // AddDaysToDate

zOPER_EXPORT zSHORT OPERATION
SetAttributeFromTabField( zVIEW  mCustO,
                          zPCHAR lpEntityName,
                          zPCHAR lpAttributeName,
                          zLONG   lFieldPosition )
{
   zPCHAR lpCurrent;
   zPCHAR lpNext;
   zLONG  i;
   zSHORT nRC;

   // Position on field based on Field position, assuming fields
   // are delineated by tabs.
   lpCurrent = lpBufferArea;

   for ( i = lFieldPosition; i > 1; i-- )
   {
      for ( lpNext = lpCurrent; *lpNext != '\t'; lpNext++ );
      lpCurrent = lpNext + 1;
   }

   // Find next tab to change to string end.
   for ( lpNext = lpCurrent; *lpNext != '\t'; lpNext++ );
   *lpNext = 0;

   // Set the Attribute value.
   nRC = SetAttributeFromString( mCustO, lpEntityName, lpAttributeName, lpCurrent );

   // Set string end back to tab.
   *lpNext = '\t';

   return( nRC );
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ReadLineToLongPointer
//
// Read a line passing the pointer back as a long.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
ReadLineIntoGlobalBuffer( zVIEW ViewToWindow, zLONG hFile )
{
   zSHORT nRC;

   nRC = SysReadLine( ViewToWindow, &lpBufferArea, hFile );

   return( nRC );

} // ReadLineToLongPointer

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: SeparateName
//
// Separate a name into first and last names.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
SeparateName( zPCHAR lpFullName,
                 zPCHAR lpFirstName,
                 zPCHAR lpLastName )
{
   zPCHAR lpCurrent;
   zPCHAR lpNext;
   zSHORT nLth;

   lpCurrent= lpFullName;

   // Eliminate any "Mr." characters that are at the front of name.
   if ( zstrncmp( lpCurrent, "Mr.", 3 ) == 0 )
   {
      // Skip the Mr. characters to the first blank and then nonblank.
      for ( lpCurrent = lpCurrent+3; *lpCurrent == ' '; lpCurrent++ );
   }

   // Process the First Name (all chars to the next blank).
   for ( lpNext = lpCurrent; *lpNext != ' ' && *lpNext != 0; lpNext++ );
   nLth = lpNext - lpCurrent;
   zstrncpy( lpFirstName, lpCurrent, nLth );
   *( lpFirstName + nLth ) = 0;

   // Skip to next nonblank or end of string.
   for ( lpCurrent = lpNext; *lpCurrent == ' ' && *lpCurrent != 0; lpCurrent++ );

   if ( *lpCurrent == 0 )
      // We are at end of name so set name to a dummy value.
      zstrcpy( lpLastName, "No Last Name" );

   else
      // Process the rest of the name as last name.
      zstrcpy( lpLastName, lpCurrent );

   return( 0 );
} // SeparateName

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: GetViewFromBlobAttribute
//
// Get a view from a blob attribute.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
GetViewFromBlobAttribute( zPVIEW pvRetrievedView,
               zVIEW vOI_View,
               zPCHAR pchOI_EntityName,
               zPCHAR pchOI_AttributeName )
{
   zSHORT nRC;
   zULONG lLth;

   lLth = sizeof( vOI_View );
   nRC = GetBlobFromAttribute( pvRetrievedView, &lLth, vOI_View, pchOI_EntityName, pchOI_AttributeName );

   return( 0 );
} // GetViewFromBlobAttribute

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: SetBlobAttributeFromView
//
// Set a blob attribute from a view.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
SetBlobAttributeFromView( zVIEW vOI_View,
                          zPCHAR pchOI_EntityName,
                          zPCHAR pchOI_AttributeName,
                          zVIEW  ViewToSet )
{
   zSHORT nRC;

   nRC = SetAttributeFromBlob( vOI_View, pchOI_EntityName, pchOI_AttributeName,
                               &ViewToSet, sizeof( ViewToSet ) );

   return( nRC );
} // SetBlobAttributeFromView

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: AddressFormatToMultiLine
//
// Can be used for display or labels.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
AddressFormatToMultiLine( zPCHAR pchMultiLineAddress,
                          zPCHAR pchLine1,
                          zPCHAR pchLine2,
                          zPCHAR pchLine3,
                          zPCHAR pchCity,
                          zPCHAR pchState,
                          zPCHAR pchPostalCode )
{
   zCHAR szFirstPart[ 250 ];

   if ( zstrlen( pchLine1 ) != 0 )
      zsprintf( szFirstPart, "%s\n", pchLine1 );

   if ( zstrlen( pchLine2 ) != 0 )
      zsprintf( szFirstPart, "%s%s\n", szFirstPart, pchLine2 );

   if ( zstrlen( pchLine3 ) != 0 )
      zsprintf( szFirstPart, "%s%s\n", szFirstPart, pchLine3 );

   zsprintf( pchMultiLineAddress, "%s\n%s, %s %s", szFirstPart,
             pchCity, pchState, pchPostalCode );

   return( 0 );
} // AddressFormatToMultiLine

zOPER_EXPORT zSHORT OPERATION
GetEntityNameFromStructure( zPVOID pvStructure,
                            zPCHAR pchEntityName )
{
   zstrcpy( pchEntityName, ((LPVIEWENTITY) pvStructure)->szName );
   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
GetAttribNameFromStructure( zPVOID pchStructure,
                            zPCHAR pchAttribName )
{
   zstrcpy( pchAttribName, ((LPVIEWATTRIB) pchStructure)->szName );
   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
IsValidAttribute( zPCHAR szAttributeName,
                  zPVOID szInternalEntityStructure )
{
   LPVIEWENTITY lpViewEntity;
   LPVIEWATTRIB lpViewAttrib;

   lpViewEntity = (LPVIEWENTITY) szInternalEntityStructure;
#ifdef VIEWENTITY_OD
   for ( lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewEntity->hFirstOD_Attrib );
         lpViewAttrib;
         lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewAttrib->hNextOD_Attrib ) )
#else
   for ( lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewEntity->hFirstViewAttrib );
         lpViewAttrib;
         lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewAttrib->hNextViewAttrib ) )
#endif
   {
       if ( lpViewAttrib->bHidden )
         continue;

      if ( zstrcmp( szAttributeName, lpViewAttrib->szName ) == 0 )
          return( 0 );
   }

   return( -1 );

} // IsValidAttribute

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: dAdressLabel
//
// multiline address (without name)
//
/////////////////////////////////////////////////////////////////////////////
zSHORT OPERATION
fnAdressLabelText( zVIEW vAnyObject, // BASED ON LOD "any object with entity that has address attributes"
                   zPVOID szInternalEntityStructure,
                   zPVOID szInternalAttribStructure,
                   zPCHAR szReturnText )
{
   zCHAR szEntityName[ 33 ];
   zCHAR szAttribName[ 33 ];
   zCHAR szMultiLineAddress[ 2001 ];
   zCHAR sz[ 255 ];
   zCHAR szAttn[ 255 ];
   zCHAR szCity[ 121 ];
   zCHAR szState[ 121 ];
   zCHAR szZipCode[ 12 ];
   zCHAR szZipCodeFormatted[ 13 ];
   zCHAR szCountry[ 51 ];
   zCHAR szSep[ 4 ];      // set to /r/n or "; "

   zstrcpy( szEntityName, ((LPVIEWENTITY) szInternalEntityStructure)->szName );
   zstrcpy( szAttribName, ((LPVIEWATTRIB) szInternalAttribStructure)->szName );
   if ( ZeidonStringCompare( szAttribName, 1, 5, "dLine", 1, 5, 33 ) == 0 )
      zstrcpy( szSep, "; " );
   else
      zstrcpy( szSep, "\r\n" );

   szMultiLineAddress[ 0 ] = 0;
   szAttn[ 0 ] = 0;
   sz[ 0 ] = 0;
   if ( IsValidAttribute ( "AttentionLine1", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "AttentionLine1" );

   if ( zstrlen( sz ) != 0 )
   {
      zsprintf( szAttn, "Attn:  %s%s", sz, szSep );
      sz[ 0 ] = 0;
   }

   if ( IsValidAttribute ( "AttentionLine2", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "AttentionLine2" );

   if ( zstrlen( sz ) != 0 )
   {
      zsprintf( szAttn, "%s         %s%s", szAttn, sz, szSep );
      sz[ 0 ] = 0;
   }

   if ( zstrlen( szAttn ) != 0 )
      ZeidonStringCopy( szMultiLineAddress, 1, 0, szAttn, 1, 0, 2000 );

   if ( IsValidAttribute ( "Line1", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "Line1" );

   if ( zstrlen( sz ) != 0 )
      zsprintf( szMultiLineAddress, "%s%s%s", szMultiLineAddress, sz, szSep );

   if ( IsValidAttribute ( "Line2", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "Line2" );

   if ( zstrlen( sz ) != 0 )
      zsprintf( szMultiLineAddress, "%s%s%s", szMultiLineAddress, sz, szSep );

   if ( IsValidAttribute ( "Line3", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "Line3" );

   if ( zstrlen( sz ) != 0 )
      zsprintf( szMultiLineAddress, "%s%s%s", szMultiLineAddress, sz, szSep );

   GetStringFromAttribute( szCity, vAnyObject, szEntityName, "City" );
   //GetStringFromAttribute( szState, vAnyObject, szEntityName, "State" );
   GetVariableFromAttribute( (zPVOID) &szState, NULL, zTYPE_STRING, 120,
                             vAnyObject, szEntityName,
                             "StateProvince", "State", 0 );

   // For ZipCodes larger than five characters, we want to format them with a
   // dash, if they don't already have a dash.
   GetVariableFromAttribute( (zPVOID) &szZipCode, NULL, zTYPE_STRING, 11,
                             vAnyObject, szEntityName, "PostalCode", "", 0 );
   zstrcpy( szZipCodeFormatted, szZipCode );
   if ( zstrlen( szZipCode ) > 5 )
   {
      if ( szZipCode[ 5 ] != '-' )
      {
         szZipCodeFormatted[ 5 ] = '-';
         zstrcpy( szZipCodeFormatted + 6, szZipCode + 5 );
      }
   }

   *szCountry = 0;
   if ( IsValidAttribute ( "Country", szInternalEntityStructure ) == 0 )
      GetStrFromAttrByContext( szCountry, 50, vAnyObject,
                               szEntityName, "Country", "Country" );
   if ( zstrcmp ( szCountry, "US" ) == 0 )
      szCountry[ 0 ] = 0;
   if ( zstrlen( szCity ) != 0 )
      zsprintf( szMultiLineAddress, "%s%s, %s %s %s", szMultiLineAddress,
                szCity, szState, szZipCodeFormatted, szCountry );
   else
      zsprintf( szMultiLineAddress, "%s%s %s %s", szMultiLineAddress,
                szState, szZipCodeFormatted, szCountry );

   ZeidonStringCopy( szReturnText, 1, 0, szMultiLineAddress, 1, 0, 255 );

   return( 0 );
} // fnAdressLabelText

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: dAdressLabel
//
// multiline address (without name)
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT /* DERIVED ATTRIBUTE */  OPERATION
dAdressLabel( zVIEW vAnyObject, // BASED ON LOD "any object with entity that has address attributes"
              zPVOID szInternalEntityStructure,
              zPVOID szInternalAttribStructure,
              zSHORT nGetOrSetFlag )
{
   zCHAR szMultiLineAddress[ 2001 ];
   zCHAR szEntityName[ 33 ];

   zstrcpy( szEntityName, ((LPVIEWENTITY) szInternalEntityStructure)->szName );
   if ( CheckExistenceOfEntity( vAnyObject, szEntityName ) != 0)
   {
      return( 0 );
   }

   szMultiLineAddress[0] = 0;
   fnAdressLabelText( vAnyObject, szInternalEntityStructure, szInternalAttribStructure,
                      szMultiLineAddress );

   StoreStringInRecord( vAnyObject, (LPVIEWENTITY) szInternalEntityStructure,
            (LPVIEWATTRIB) szInternalAttribStructure, szMultiLineAddress );

   return( 0 );
} // dAdressLabel

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: dAdressLabel
//
// multiline address (with name)
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT /* DERIVED ATTRIBUTE */  OPERATION
dAdressLabelFull( zVIEW vAnyObject, // BASED ON LOD "any object with entity that has address attributes"
                  zPVOID szInternalEntityStructure,
                  zPVOID szInternalAttribStructure,
                  zSHORT nGetOrSetFlag )
{
   zCHAR szEntityName[ 33 ];
   zCHAR szMultiLineAddress[ 2001 ];
   zCHAR sz[ 2001 ];
   zCHAR szCompanyName[ 121 ];

   zstrcpy( szEntityName, ((LPVIEWENTITY) szInternalEntityStructure)->szName );
   if ( CheckExistenceOfEntity( vAnyObject, szEntityName ) != 0 )
   {
      return( 0 );
   }

   szMultiLineAddress[ 0 ] = 0;
   szCompanyName[ 0 ] = 0;
   if ( IsValidAttribute ( "CompanyName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( szCompanyName, vAnyObject, szEntityName, "CompanyName" );

   if ( zstrlen( szCompanyName ) != 0 )
      zsprintf( szMultiLineAddress, "%s\r\n", szCompanyName );

   fnAdressLabelText( vAnyObject, szInternalEntityStructure, szInternalAttribStructure, sz );

   ZeidonStringConcat( szMultiLineAddress, 1, 0, sz, 1, 0, 2000 );

   StoreStringInRecord( vAnyObject, (LPVIEWENTITY) szInternalEntityStructure,
                        (LPVIEWATTRIB) szInternalAttribStructure, szMultiLineAddress );

   return( 0 );
} // dAdressLabelFull

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: PersonName_LastFirstMiddle
//
// Person's name formated Lastname first
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT /* DERIVED ATTRIBUTE */  OPERATION
PersonName_LastFirstMiddle( zVIEW vAnyObject, // BASED ON LOD "any object with entity that has "NAME" attributes"
                            zPVOID szInternalEntityStructure,
                            zPVOID szInternalAttribStructure,
                            zSHORT nGetOrSetFlag )
{
   zCHAR szReturnName[ 101 ];
   zCHAR szEntityName[ 33 ];
   zCHAR sz[ 255 ];

   zstrcpy( szEntityName, ((LPVIEWENTITY) szInternalEntityStructure)->szName );
   szReturnName[ 0 ] = 0;
   sz[ 0 ] = 0;
   if ( IsValidAttribute( "LastName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( szReturnName, vAnyObject, szEntityName, "LastName" );

   if ( IsValidAttribute( "FirstName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "FirstName" );

   if ( zstrlen( sz ) != 0 )
   {
      ZeidonStringConcat( szReturnName, 1, 0, ", ", 1, 0, 101 );
      ZeidonStringConcat( szReturnName, 1, 0, sz, 1, 0, 101 );
      sz[ 0 ] = 0;
   }

   if ( IsValidAttribute( "MiddleName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "MiddleName" );

   if ( zstrlen( sz ) != 0 )
   {
      ZeidonStringConcat( szReturnName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szReturnName, 1, 0, sz, 1, 0, 101 );
      if ( zstrlen( sz ) == 1 )
         ZeidonStringConcat( szReturnName, 1, 0, ".", 1, 0, 101 );

      sz[ 0 ] = 0;
   }

   StoreStringInRecord( vAnyObject, (LPVIEWENTITY) szInternalEntityStructure,
                        (LPVIEWATTRIB) szInternalAttribStructure, szReturnName );

   return( 0 );
} // dAdressLabel

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: PersonName_FirstMiddleLast
//
// Person's name formated Lastname last
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT /* DERIVED ATTRIBUTE */  OPERATION
PersonName_FirstMiddleLast( zVIEW vAnyObject, // BASED ON LOD "any object with entity that has "NAME" attributes"
                            zPVOID szInternalEntityStructure,
                            zPVOID szInternalAttribStructure,
                            zSHORT nGetOrSetFlag )
{
   zCHAR szReturnName[ 101 ];
   zCHAR szEntityName[ 33 ];
   zCHAR sz[ 255 ];

   zstrcpy( szEntityName, ( (LPVIEWENTITY) szInternalEntityStructure )->szName );
   szReturnName[ 0 ] = 0;
   sz[ 0 ] = 0;
   if ( IsValidAttribute( "FirstName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( szReturnName, vAnyObject, szEntityName, "FirstName" );

   if ( IsValidAttribute( "MiddleName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "MiddleName" );

   if ( zstrlen( sz ) != 0 )
   {
      ZeidonStringConcat( szReturnName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szReturnName, 1, 0, sz, 1, 0, 101 );
      if ( zstrlen( sz ) == 1 )
         ZeidonStringConcat( szReturnName, 1, 0, ".", 1, 0, 101 );

      sz[ 0 ] = 0;
   }

   if ( IsValidAttribute( "LastName", szInternalEntityStructure ) == 0 )
      GetStringFromAttribute( sz, vAnyObject, szEntityName, "LastName" );

   if ( zstrlen( sz ) != 0 )
   {
      ZeidonStringConcat( szReturnName, 1, 0, " ", 1, 0, 101 );
      ZeidonStringConcat( szReturnName, 1, 0, sz, 1, 0, 101 );
      sz[ 0 ] = 0;
   }

   StoreStringInRecord( vAnyObject, (LPVIEWENTITY) szInternalEntityStructure,
            (LPVIEWATTRIB) szInternalAttribStructure, szReturnName );

   return( 0 );
} // dAdressLabel


// Remove whitespaces at the beginning and end of a string.
void zstrtrim( zPCHAR pchSource )
{
   zPCHAR pch;
   zSHORT iLth;

   // Find first non-space.
   for ( pch = pchSource; zisspace( *pch ); pch++ )
      ; // Nothing needs to be done here.

   if ( pch != pchSource )
      zstrcpy( pchSource, pch );

   iLth = zstrlen( pchSource );
   if ( iLth > 0 )
   {
      for ( pch = &pchSource[ iLth - 1 ];
            zisspace( *pch ) && pch >= pchSource;
            pch-- )
      {
         ; // Nothing needs to be done here.
      }

      *(pch + 1) = 0;
   }
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: zTrim
//
// trim whitespace from front and back
zOPER_EXPORT zSHORT OPERATION
zTrim( zPCHAR pchStringInOut )
{
   zstrtrim( pchStringInOut );

   return( 0 );
} // zTrim

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: SetDecimalPrecision
//
zLONG OPERATION
SetDecimalPrecisionRounded( zPDECIMAL pdDecimalValue,
                     zLONG ulNumberOfDecimals )
{
   zCHAR sz[ 255 ];

   SysConvertDecimalToString( pdDecimalValue, sz, (zSHORT) ulNumberOfDecimals );

   return( SysConvertStringToDecimal( sz, pdDecimalValue ) );

} // SetDecimalPrecision

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: RemoveLeadingBlanksFromAttrib
//
zSHORT OPERATION
RemoveLeadingBlanksFromAttrib( zVIEW  vView,
                               zPCHAR pchEntity,
                               zPCHAR pchAttribute )
{
   zPCHAR pch;
   zCHAR  sz[ 254 ];

   // Remove any leading blanks from the attribute.

   GetVariableFromAttribute( (zPVOID) &sz, NULL,
                              zTYPE_STRING, 253,
                              vView, pchEntity, pchAttribute, 0, 0 );
   if ( sz[ 1 ] == ' ' )
   {
      // The first character is blank so lets go through the process.
      for ( pch = sz; *pch == ' '; pch++ )
         ; // do nothing

      SetAttributeFromString( vView, pchEntity, pchAttribute, pch );
   }

   return( 0 );
} // RemoveLeadingBlanksFromAttrib

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: GetButtonHeaderName
//
// this can only be used inside the button action for the new list
// box
zLONG OPERATION
GetButtonHeaderName( zVIEW  ViewToWindow,
                     zPCHAR pchButtonHeaderName )
{

   zstrcpy( pchButtonHeaderName, ( (zPCHAR) GetActionParameters( ViewToWindow ) ) );
   //pchButtonHeaderName = (zPCHAR) GetActionParameters( ViewToWindow );

   return( 0 );

} // GetButtonHeaderName

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: GetWindowsUserName
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zLONG OPERATION
GetWindowsUserName( zPCHAR pchUserName,
                    zLONG ulMaxLengthOfName )
{
   DWORD lth;

   lth = ulMaxLengthOfName;

   if (GetUserName( pchUserName, &lth ) == 0 )
      *pchUserName = 0;

   return( 0 );
} // GetWindowsUserName

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: CallObjectOperation
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zLONG OPERATION
CallObjectOperation( zPVIEW pvRetView,
                     zVIEW  ViewToWindow,
                     zPCHAR pchObject,
                     zPCHAR pchOperationName )
{
   zCHAR szOperation[ 255 ];
   zCHAR szPrefix[ 16 ];
   zFARPROC_DYNOBJ lpfnDynRoutine;
   zSHORT  nRC = -1;
   LPLIBRARY hLibrary;

   hLibrary = 0;

   zstrcpy( szPrefix, "o" );
   zstrcat( szPrefix, pchObject );
   zstrcat( szPrefix, "_" );


   zstrcpy( szOperation, szPrefix );
   zstrcat( szOperation, pchOperationName );

   lpfnDynRoutine = (zFARPROC_DYNOBJ)
      GetOperationDynamicCallAddress( ViewToWindow, &hLibrary,
                                      "LODOPS_R", szOperation, "Reports" );
   if ( lpfnDynRoutine )
   {
      nRC = (*lpfnDynRoutine)( (zPVIEW) pvRetView, (zVIEW) ViewToWindow );
   }

   return( nRC );

} // CallObjectOperation

// FindItemByName - enumerates the subkeys of a given key and the associated
//    values.
// hKey - key whose subkeys and values are to be enumerated

zBOOL
FindItemByName( HKEY    hKey,
                zCPCHAR cpcValueName,
                DWORD   dwReturnType,
                zPCHAR  pchReturnData,
                DWORD   dwMaxReturnLth )
{
   char     szKey[ MAX_PATH ];
   char     szClass[ MAX_PATH ] = "";   // buffer for class name
   DWORD    dwClassName = MAX_PATH;     // length of class string
   DWORD    dwSubKeys;                  // number of subkeys
   DWORD    dwMaxSubKey;                // longest subkey size
   DWORD    dwMaxClass;                 // longest class string
   DWORD    dwValues;                   // number of values for key
   DWORD    dwMaxValue;                 // longest value name
   DWORD    dwMaxValueData;             // longest value data
   DWORD    dwSecurityDescriptor;       // size of security descriptor
   DWORD    dwType;
   FILETIME ftLastWriteTime;            // last write time

   DWORD k, j;
   DWORD dwRC;
   zLONG lRC;
   zBOOL bFound = FALSE;

   char  szValue[ MAX_PATH ];
   DWORD dwValue = MAX_PATH;
   char  szBuff[ 80 ];

   // Get the class name and the value count.
   RegQueryInfoKey( hKey,                  // key handle
                    szClass,               // buffer for class name
                    &dwClassName,          // length of class string
                    NULL,                  // reserved
                    &dwSubKeys,            // number of subkeys
                    &dwMaxSubKey,          // longest subkey size
                    &dwMaxClass,           // longest class string
                    &dwValues,             // number of values for this key
                    &dwMaxValue,           // longest value name
                    &dwMaxValueData,       // longest value data
                    &dwSecurityDescriptor, // security descriptor
                    &ftLastWriteTime );    // last write time

   // Enumerate the child keys, looping until RegEnumKey fails. Then
   // get the name of each child key and copy it into the list box.
   SetCursor( LoadCursor( NULL, IDC_WAIT ) );
   for ( k = 0, dwRC = ERROR_SUCCESS; dwRC == ERROR_SUCCESS; k++ )
   {
      dwRC = RegEnumKey( hKey, k, szKey, MAX_PATH );
      //if ( dwRC == (DWORD) ERROR_SUCCESS )
      //{
      //   TraceLineS( "IDE_CLASS?: ", szKey );
      //}
   }

   SetCursor( LoadCursor( NULL, IDC_ARROW ) );

   // Enumerate the key values.
   SetCursor( LoadCursor( NULL, IDC_WAIT ) );

   if ( dwValues )
   {
      for ( j = 0, dwRC = ERROR_SUCCESS; j < dwValues; j++ )
      {
         dwValue = MAX_PATH;
         szValue[ 0 ] = 0;
         dwRC = RegEnumValue( hKey, j, szValue,
                              &dwValue,
                              NULL,
                              &dwType, // &dwType,
                              NULL,    // &bData,
                              NULL );  // &bcData

         if ( dwRC != (DWORD) ERROR_SUCCESS &&
              dwRC != ERROR_INSUFFICIENT_BUFFER )
         {
            wsprintf( szBuff,
                      "Line:%d 0 based index = %d, dwRC = %d, "
                      "ValueLen = %d",
                      __LINE__, j, dwRC, dwValue );
            //TraceLineS( "Debug", szBuff );
         }

         szBuff[ 0 ] = 0;

         if ( dwType == dwReturnType && zstrcmp( cpcValueName, szValue ) == 0 )
         {
            bFound = TRUE;

            lRC = RegQueryValueEx( hKey,               // handle to key
                                   szValue,
                                   0,                  // reserved must be null
                                   &dwType,            // value type
                                   (LPBYTE) pchReturnData, // data buffer
                                   &dwMaxReturnLth );  // data buffer size
            if ( zstrlen( szValue ) == 0 )
               zstrcpy( szValue, "(Default)" );

            //TraceLineS( "Found Key ===> ", szValue );
            break;
         }

         // Add each value to a list box.
         if ( zstrlen( szValue ) == 0 )
            zstrcpy( szValue, "(Default)" );

         wsprintf( szBuff, "%d) %s ", j, szValue );
         // TraceLineS( "???:  ", szBuff );
      }
   }

   SetCursor( LoadCursor( NULL, IDC_ARROW ) );
   return( bFound );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryImagingValue( zCPCHAR cpcValueName,
                         zCPCHAR cpcCLSID_ID,
                         DWORD   dwReturnType,
                         zPCHAR  pchReturnData,
                         DWORD   dwMaxReturnLth )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hSoftware;
   HKEY  hKeyClasses;
   HKEY  hKeyCLSID;
   HKEY  hKeyID;
   HKEY  hKeyLocalServer;
   zSHORT nRC = -1;

   *pchReturnData = 0;  // initialize

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
      return( nRC );
   }

   // get thr CLSID here
   lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx Classes", " failed"  );
      return( nRC );
   }

   // get thr CLSID here
   lRC = RegOpenKeyEx( hKeyClasses, "CLSID", 0, KEY_READ, &hKeyCLSID );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx CLSID", " failed"  );
      return( nRC );
   }

   // get thr CLSID here
   lRC = RegOpenKeyEx( hKeyCLSID, cpcCLSID_ID, 0, KEY_READ, &hKeyID );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hKeyID", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hKeyID, "LocalServer32", 0, KEY_READ, &hKeyLocalServer );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hKeyLocalServer", " failed"  );
      return( nRC );
   }

   nRC = FindItemByName( hKeyLocalServer, cpcValueName, dwReturnType,
                         pchReturnData, dwMaxReturnLth );
   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryImagingPrintValue( zCPCHAR cpcValueName,
                              zCPCHAR cpcCLSID_ID,
                              DWORD   dwReturnType,
                              zPCHAR  pchReturnData,
                              DWORD   dwMaxReturnLth )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hSoftware;
   HKEY  hKeyShell;
   HKEY  hKeyClasses;
   HKEY  hKeyPrint;
   HKEY  hKeyCommand;
   HKEY  hKeyTif;
   zSHORT nRC = -1;

   *pchReturnData = 0;  // initialize

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
      return( nRC );
   }

   // get thr CLSID here
   lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx Classes", " failed"  );
      return( nRC );
   }

   // get the TIF.Document here
   lRC = RegOpenKeyEx( hKeyClasses, "TIFImage.Document", 0, KEY_READ, &hKeyTif );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx TIF.Docuement", " failed"  );
      return( nRC );
   }

   // get the shell here
   lRC = RegOpenKeyEx( hKeyTif, "shell", 0, KEY_READ, &hKeyShell );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx shell", " failed"  );
      return( nRC );
   }

   // get the print key here
   lRC = RegOpenKeyEx( hKeyShell, "print", 0, KEY_READ, &hKeyPrint );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx print:", ":failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx command ", " failed"  );
      return( nRC );
   }

   nRC = FindItemByName( hKeyCommand, cpcValueName, dwReturnType,
                         pchReturnData, dwMaxReturnLth );

   TraceLineS( cpcValueName, pchReturnData );

   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryImagingViewValue( zCPCHAR cpcValueName,
                             zCPCHAR cpcCLSID_ID,
                             DWORD   dwReturnType,
                             zPCHAR  pchReturnData,
                             DWORD   dwMaxReturnLth )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hSoftware;
   HKEY  hKeyShell;
   HKEY  hKeyClasses;
   HKEY  hKeyPrint;
   HKEY  hKeyCommand;
   HKEY  hKeyTif;
   zSHORT nRC = -1;

   *pchReturnData = 0;  // initialize

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
      return( nRC );
   }
   // get thr CLSID here
   lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx Classes", " failed"  );
      return( nRC );
   }
   // get the TIF.Document here
   lRC = RegOpenKeyEx( hKeyClasses, "TIFImage.Document", 0, KEY_READ, &hKeyTif );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx TIF.Docuement", " failed"  );
      return( nRC );
   }
   // get the shell here
   lRC = RegOpenKeyEx( hKeyTif, "shell", 0, KEY_READ, &hKeyShell );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx shell", " failed"  );
      return( nRC );
   }
   // get the print key here
   lRC = RegOpenKeyEx( hKeyShell, "open", 0, KEY_READ, &hKeyPrint );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx open:", ":failed"  );
      return( nRC );
   }
   lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx command ", " failed"  );
      return( nRC );
   }

   nRC = FindItemByName( hKeyCommand, cpcValueName, dwReturnType,
                         pchReturnData, dwMaxReturnLth );
   TraceLineS( cpcValueName, pchReturnData );

   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryHTMLViewValue( zCPCHAR cpcValueName,
                          zCPCHAR cpcCLSID_ID,
                          DWORD   dwReturnType,
                          zPCHAR  pchReturnData,
                          DWORD   dwMaxReturnLth )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hSoftware;
   HKEY  hKeyShell;
   HKEY  hKeyClasses;
   HKEY  hKeyPrint;
   HKEY  hKeyCommand;
   HKEY  hKeyHtml;
   zSHORT nRC = -1;

   *pchReturnData = 0;  // initialize

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
      return( nRC );
   }
   // get thr CLSID here
   lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx Classes", " failed"  );
      return( nRC );
   }
   // get the hKeyHtml here
   lRC = RegOpenKeyEx( hKeyClasses, "htmlfile", 0, KEY_READ, &hKeyHtml );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx htmlfile", " failed"  );
      return( nRC );
   }
   // get the shell here
   lRC = RegOpenKeyEx( hKeyHtml, "shell", 0, KEY_READ, &hKeyShell );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx shell", " failed"  );
      return( nRC );
   }
   // get the print key here
   lRC = RegOpenKeyEx( hKeyShell, "open", 0, KEY_READ, &hKeyPrint );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx open:", ":failed"  );
      return( nRC );
   }
   lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx command ", " failed"  );
      return( nRC );
   }

   nRC = FindItemByName( hKeyCommand, cpcValueName, dwReturnType,
                         pchReturnData, dwMaxReturnLth );
   TraceLineS( cpcValueName, pchReturnData );

   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryGeneralValue( zCPCHAR cpcValueName,
                         zPCHAR  pchFileType,
                         zCPCHAR cpcCLSID_ID,
                         DWORD   dwReturnType,
                         zPCHAR  pchReturnData,
                         DWORD   dwMaxReturnLth )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hSoftware;
   HKEY  hKeyShell;
   HKEY  hKeyClasses;
   HKEY  hKeyPrint;
   HKEY  hKeyCommand;
   HKEY  hKeyHtml;
   zSHORT nRC = -1;

   *pchReturnData = 0;  // initialize

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
      return( nRC );
   }
   // get thr CLSID here
   lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx Classes", " failed"  );
      return( nRC );
   }
   // get the hKeyHtml here
   lRC = RegOpenKeyEx( hKeyClasses, pchFileType, 0, KEY_READ, &hKeyHtml );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx pchFileType", " failed"  );
      return( nRC );
   }
   // get the shell here
   lRC = RegOpenKeyEx( hKeyHtml, "shell", 0, KEY_READ, &hKeyShell );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx shell", " failed"  );
      return( nRC );
   }
   // get the print key here
   lRC = RegOpenKeyEx( hKeyShell, "open", 0, KEY_READ, &hKeyPrint );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx open:", ":failed"  );
      return( nRC );
   }
   lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx command ", " failed"  );
      return( nRC );
   }

   nRC = FindItemByName( hKeyCommand, cpcValueName, dwReturnType,
                         pchReturnData, dwMaxReturnLth );
   TraceLineS( cpcValueName, pchReturnData );

   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryPrintValue( zCPCHAR cpcValueName,
                       zPCHAR  pchFileType,
                       zCPCHAR cpcCLSID_ID,
                       DWORD   dwReturnType,
                       zPCHAR  pchReturnData,
                       DWORD   dwMaxReturnLth )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hSoftware;
   HKEY  hKeyShell;
   HKEY  hKeyClasses;
   HKEY  hKeyPrint;
   HKEY  hKeyCommand;
   HKEY  hKeyHtml;
   zSHORT nRC = -1;

   *pchReturnData = 0;  // initialize

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
      return( nRC );
   }

   // get thr CLSID here
   lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx Classes", " failed"  );
      return( nRC );
   }

   // get the hKeyHtml here
   lRC = RegOpenKeyEx( hKeyClasses, pchFileType, 0, KEY_READ, &hKeyHtml );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx pchFileType", " failed"  );
      return( nRC );
   }

   // get the shell here
   lRC = RegOpenKeyEx( hKeyHtml, "shell", 0, KEY_READ, &hKeyShell );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx shell", " failed"  );
      return( nRC );
   }

   // get the print key here
   lRC = RegOpenKeyEx( hKeyShell, "print", 0, KEY_READ, &hKeyPrint );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx print:", ":failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx print command ", " failed"  );
      return( nRC );
   }

   nRC = FindItemByName( hKeyCommand, cpcValueName, dwReturnType,
                         pchReturnData, dwMaxReturnLth );
   TraceLineS( cpcValueName, pchReturnData );

   return( nRC );
}

zOPER_EXPORT zSHORT OPERATION
GetRegistryCLSID( zPCHAR  pchReturnData, zPCHAR pchClassName )
{
   HKEY  hConnect;
   HKEY  hClassesRoot;
   HKEY  hCLSID;
   HKEY  hKeyCLSID;
   DWORD dwReturnTypeCLSID = REG_SZ;
   DWORD dwMaxReturnCLSIDLth = 128;
   zSHORT nRC = -1;

   zLONG lRC = RegConnectRegistry( NULL, HKEY_LOCAL_MACHINE, &hConnect );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegConnectRegistry", " failed"  );
      return( nRC );
   }

   // open the local key
   lRC = RegOpenKeyEx( HKEY_CLASSES_ROOT, 0, 0, KEY_READ, &hClassesRoot );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx HKEY_CLASSES_ROOT", " failed"  );
      return( nRC );
   }

   lRC = RegOpenKeyEx( hClassesRoot, pchClassName, 0, KEY_READ, &hCLSID );
   if ( lRC != ERROR_SUCCESS )
   {

      TraceLineS( "RegOpenKeyEx Failed for: ", pchClassName  );
      return( nRC );
   }

   // get thr CLSID here
   lRC = RegOpenKeyEx( hCLSID, "CLSID", 0, KEY_READ, &hKeyCLSID );
   if ( lRC != ERROR_SUCCESS )
   {
      TraceLineS( "RegOpenKeyEx CLSID", " failed"  );
      return( nRC );
   }

   // get the CLSID Value
   nRC = FindItemByName( hKeyCLSID, "", dwReturnTypeCLSID, pchReturnData, dwMaxReturnCLSIDLth );
   TraceLineS( "CLSID ReturnValue", pchReturnData );
   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
GetImagingPath( zVIEW vSubtask, zLONG lFlag, zPCHAR pchTarget )
{
   char   szReturn[ 256 ];
   char   szCLSID[ 256 ];
   zSHORT nRC = FALSE;

   szReturn[ 0 ] = 0;  // initially empty
   if ( lFlag == 1 )   //open for edit
   {
      GetRegistryCLSID( szCLSID, "TIFImage.Document" );
      GetRegistryImagingValue( "", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );
   }

   if ( lFlag == 2 ) //open for print
   {
      GetRegistryCLSID( szCLSID, "TIFImage.Document" );
      nRC = GetRegistryImagingPrintValue( "", szCLSID, REG_EXPAND_SZ, szReturn, sizeof( szReturn ) );
      if ( nRC == FALSE )
      {
         nRC = GetRegistryImagingPrintValue( "", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );
      }
   }
   // zstrcpy( pchTarget, szReturn );
   if ( lFlag == 3 ) //open for view
   {
      GetRegistryCLSID( szCLSID, "TIFImage.Document" );
      nRC = GetRegistryImagingViewValue( "", szCLSID, REG_EXPAND_SZ, szReturn, sizeof( szReturn ) );
      if ( nRC == FALSE )
      {
         nRC = GetRegistryImagingViewValue( "", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );
      }
   }

   zstrcpy( pchTarget, szReturn );
   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
GetHTMLPath( zVIEW   vSubtask, zLONG lFlag, zPCHAR pchTarget )
{
   char   szReturn[ 256 ];
   char   szCLSID[ 256 ];
   zSHORT nRC = FALSE;

   szReturn[0] = 0;
   lFlag = 3; // set flag to 3 as that is all we currently support
   if ( lFlag == 3 ) //open for view
   {
      GetRegistryCLSID( szCLSID, "htmlfile" );
      nRC = GetRegistryHTMLViewValue( "", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );
      TraceLineS( "Flag 3 szCLSID !", szCLSID ) ;
      TraceLineS( "Flag 3 Return !", szReturn ) ;
      if ( nRC == FALSE )
      {
         // for win98 in case we are not in win2K
         nRC = GetRegistryHTMLViewValue( "", szCLSID, REG_EXPAND_SZ, szReturn, sizeof( szReturn ) );
         TraceLineS( "Flag 3C szCLSID !", szCLSID ) ;
         TraceLineS( "Flag 3C Return !", szReturn ) ;
      }
   }
         TraceLineS( "RIGHT BEFORE STRCopy:", szReturn ) ;
   zstrcpy( pchTarget, szReturn );
         TraceLineS( "RIGHT AFTER STRCopy", "") ;
   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
GetGeneralPath( zVIEW vSubtask, zLONG lFlag, zPCHAR pchFileType, zPCHAR pchTarget )
{
   char   szReturn[ 256 ];
   char   szCLSID[ 256 ];
   zSHORT nRC = FALSE;

   szReturn[0] = 0;
   lFlag = 3; // set flag to 3 as that is all we currently support
   if ( lFlag == 3 ) //open for view
   {
      GetRegistryCLSID( szCLSID, pchFileType );
      // nRC = GetRegistryHTMLViewValue( "", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );
      nRC = GetRegistryGeneralValue( "", "rtffile", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );

      TraceLineS( "Flag 3 szCLSID !", szCLSID ) ;
      TraceLineS( "Flag 3 Return !", szReturn ) ;
      if ( nRC == FALSE )
      {
         // for win98 in case we are not in win2K
         nRC = GetRegistryGeneralValue( "", "rtffile", szCLSID, REG_EXPAND_SZ,
                                        szReturn, sizeof( szReturn ) );
         TraceLineS( "Flag 3C szCLSID !", szCLSID ) ;
         TraceLineS( "Flag 3C Return !", szReturn ) ;
      }
   }

   TraceLineS( "RIGHT BEFORE STRCopy:", szReturn ) ;
   zstrcpy( pchTarget, szReturn );
   TraceLineS( "RIGHT AFTER STRCopy", "" ) ;
   return( 0 );
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: FindStringInAttribute
//
//    Find a string within a string attribute. Not case sensitive.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
FindStringInAttribute( zPCHAR pchSearchString,
                       zVIEW  vView,
                       zPCHAR pchEntityName,
                       zPCHAR pchAttributeName )
{
   zPCHAR pAttributeValue;
   zPCHAR pFoundValue;

   // Look for a match on the string pchSearchString within the attribute.
   // Return 0 if found.
   // Return -1 if not found.
   GetAddrForAttribute( &pAttributeValue,
                        vView, pchEntityName, pchAttributeName );
   pFoundValue = strstr( pAttributeValue, pchSearchString );
   if ( pFoundValue == 0 )
      return( -1 );  // The string was not found.
   else
      return( 0 );   // The string was found.

} // FindStringInAttribute

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ConvertExternalValueOfAttribute
//
// Convert an external value for an attribute to its internal value.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
ConvertExternalValueOfAttribute( zPCHAR lpReturnedString,
                                 zPCHAR pchSourceString,
                                 zVIEW  lpView,
                                 zPCHAR pchEntityName,
                                 zPCHAR pchAttributeName )
{
   zVIEW  wXferO;
   zVIEW  vDynamicT;
   zVIEW  vQualObject;
   LPVIEWENTITY lpViewEntity;
   LPVIEWATTRIB lpViewAttrib;
   LPDOMAIN     lpDomain;
   zSHORT nRC;
   zSHORT nLth;
   zCHAR  DataType;
   zCHAR  Msg[ 200 ];
   zCHAR  SavedTableName[ 36 ];

   GetViewByName( &wXferO, "wXferO", lpView, zLEVEL_TASK );
   *lpReturnedString = 0;
   lpViewEntity = (LPVIEWENTITY) MiGetViewEntityForView( lpView, pchEntityName );
   if ( lpViewEntity == 0 )
      return( -16 );

   // Position on attribute.
#ifdef VIEWENTITY_OD
   lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewEntity->hFirstOD_Attrib );
   nRC = 1;
   while ( lpViewAttrib > 0 && nRC > 0 )
   {
      if ( zstrcmp( lpViewAttrib->szName, pchAttributeName ) == 0 )
         nRC = 0;

      if ( nRC > 0 )
         lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewAttrib->hNextOD_Attrib );
   }
#else
   lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewEntity->hFirstViewAttrib );
   nRC = 1;
   while ( lpViewAttrib > 0 && nRC > 0 )
   {
      if ( zstrcmp( lpViewAttrib->szName, pchAttributeName ) == 0 )
         nRC = 0;

      if ( nRC > 0 )
         lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewAttrib->hNextViewAttrib );
   }
#endif
   if ( nRC > 0 )
   {
      MessageSend( lpView, "", "Data Conversion",
                   "The attribute specified was not found.",
                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      return( -1 );
   }

   // If input is null, simply return because output has already been set to null.
   if ( *pchSourceString == 0 )
      return( 0 );

   nLth = zstrlen( pchSourceString );

   // Process depending on whether or not the Domain is a Table.
   lpDomain = (LPDOMAIN) zGETPTR( lpViewAttrib->hDomain );
   if ( lpDomain->cDomainType == 'T' )
   {
      if ( *(lpDomain->szDomainOper) == 0 )
      {
         // The domain is a static table so convert the value through the table interface.
         nRC = TableEntryExtToInt( lpReturnedString, lpView, lpDomain, 0, pchSourceString );
         if ( nRC < 0 )
         {
            zstrcpy( Msg, "Invalid input value for attribute, " );
            zstrcat( Msg, pchAttributeName );
            zstrcat( Msg, "." );
            MessageSend( lpView, "", "Data Conversion",
                         Msg,
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return( -1 );
         }
      }
      else
      {
         // The domain is a dynamic table so use the object in memory or activate it.
         zstrcpy( SavedTableName, "X_" );
         zstrcat( SavedTableName, lpDomain->szName );
         nRC = GetViewByName( &vDynamicT, SavedTableName, lpView, zLEVEL_TASK );
         if ( nRC < 0 )
            nRC = GetViewByName( &vDynamicT, SavedTableName, lpView, zLEVEL_APPLICATION );

         if ( nRC < 0 )
         {
            // The table wasn't in memory, so call the dynamic table routine to load it.
            // Note that we will call the routine with an invalid request type, which will load
            // the table but not take action.
            SfActivateSysEmptyOI( &vQualObject, "KZDBHQUA", lpView, zMULTIPLE );
            CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
            SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "Domain" );
            CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
            SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "Domain" );
            SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "Name" );
            SetAttributeFromString( vQualObject, "QualAttrib", "Value", lpDomain->szName );
            SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
            nRC = ActivateObjectInstance( &vDynamicT, "DOMAINT", lpView,
                                          vQualObject, zSINGLE | zLEVEL_APPLICATION );
            SetNameForView( vDynamicT, SavedTableName, lpView, zLEVEL_APPLICATION );
         }

         // Locate the entry in the table by external value and return the internal value.
         nRC = SetCursorFirstEntityByString( vDynamicT, "DomainValue",
                                             "ExternalDescription",
                                             pchSourceString, 0 );
         if ( nRC < 0 )
         {
            zstrcpy( Msg, "Invalid input value for attribute, " );
            zstrcat( Msg, pchAttributeName );
            zstrcat( Msg, "." );
            MessageSend( lpView, "", "Data Conversion",
                         Msg,
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return( -1 );
         }

         GetStringFromAttribute( lpReturnedString, vDynamicT,
                                 "DomainValue", "InternalStringValue" );
      }
   }
   else
   {
      // If Domain Type is not Table, use data type for conversion through wXferO attribute.
      DataType = lpDomain->cType;
      if ( DataType == 'L' )
      {
         nRC = SetAttributeFromVariable( wXferO, "Root", "WorkInteger",
                                        (zPVOID) pchSourceString, zTYPE_STRING,
                                        nLth, 0, zUSE_DEFAULT_CONTEXT );
         if ( nRC >= 0 )
            GetStringFromAttribute( lpReturnedString, wXferO, "Root", "WorkInteger" );
         else
            return( -1 );
      }
      else
      if ( DataType == 'T' )
      {
         nRC = SetAttributeFromVariable( wXferO, "Root", "WorkDate",
                                         (zPVOID) pchSourceString, zTYPE_STRING,
                                         nLth, "M/D/YYYY", 0 );
         if ( nRC >= 0 )
            GetStringFromAttribute( lpReturnedString, wXferO, "Root", "WorkDate" );
         else
            return( -1 );
      }
      else
      if ( DataType == 'D' )
      {
         nRC = SetAttributeFromVariable( wXferO, "Root", "WorkDate",
                                         (zPVOID) pchSourceString, zTYPE_STRING,
                                         nLth, "M/D/YYYY", 0 );
         if ( nRC >= 0 )
            GetStringFromAttribute( lpReturnedString, wXferO, "Root", "WorkDate" );
         else
            return( -1 );
      }
      else
      if ( DataType == 'M' )
      {
         nRC = SetAttributeFromVariable( wXferO, "Root", "WorkDecimal",
                                        (zPVOID) pchSourceString, zTYPE_STRING,
                                        nLth, 0, zUSE_DEFAULT_CONTEXT );
         if ( nRC >= 0 )
            GetStringFromAttribute( lpReturnedString, wXferO, "Root", "WorkDecimal" );
         else
            return( -1 );
      }
      else
         zstrcpy( lpReturnedString, pchSourceString );
   }

   return( 0 );
} // ConvertExternalValueOfAttribute

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: AddSpacesToString
//
//    Insert spaces within a Zeidon string name where capital letters exist.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
AddSpacesToString( zPCHAR pchWorkString  )
{
   zCHAR  szWorkString2[ 100 ];
   zSHORT i, k;

   szWorkString2[ 0 ] = pchWorkString[ 0 ];
   i = 1;
   k = 1;
   while ( pchWorkString[ i ] != 0 )
   {
      if ( pchWorkString[ i ] >= 'A' && pchWorkString[ i ] <= 'Z' )
      {
         szWorkString2[ k++ ] = ' ';
         szWorkString2[ k++ ] = pchWorkString[ i++ ];
      }
      else
         szWorkString2[ k++ ] = pchWorkString[ i++ ];
   }

   szWorkString2[ k ] = 0;
   zstrcpy( pchWorkString, szWorkString2 );
   return( 0 );

   return( 0 );
} // AddSpacesToString

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: GetDataTypeForAttribute
//
//    Return the Data Type for an attribute
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
GetDataTypeForAttribute( zPCHAR pchDataType,
                         zVIEW  lpView,
                         zPCHAR pchEntityName,
                         zPCHAR pchAttributeName )
{
   LPVIEWENTITY lpViewEntity;
   LPVIEWATTRIB lpViewAttrib;
   zSHORT nRC;

   lpViewEntity = (LPVIEWENTITY) zGETPTR( MiGetViewEntityForView( lpView, pchEntityName ) );
   if ( lpViewEntity == 0 )
      return( -16 );

   // Position on attribute.
#ifdef VIEWENTITY_OD
   lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewEntity->hFirstOD_Attrib );
   nRC = 1;
   while ( lpViewAttrib > 0 && nRC > 0 )
   {
      if ( zstrcmp( lpViewAttrib->szName, pchAttributeName ) == 0 )
         nRC = 0;

      if ( nRC > 0 )
         lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewAttrib->hNextOD_Attrib );
   }
#else
   lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewEntity->hFirstViewAttrib );
   nRC = 1;
   while ( lpViewAttrib > 0 && nRC > 0 )
   {
      if ( zstrcmp( lpViewAttrib->szName, pchAttributeName ) == 0 )
         nRC = 0;

      if ( nRC > 0 )
         lpViewAttrib = (LPVIEWATTRIB) zGETPTR( lpViewAttrib->hNextViewAttrib );
   }
#endif
   if ( nRC > 0 )
   {
      MessageSend( lpView, "", "GetDataTypeForAttribute",
                   "The attribute specified was not found.",
                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      return( -1 );
   }

   // Set single character datatype followed by a string terminator.
   *pchDataType = lpViewAttrib->hDomain->cType;
   *(pchDataType + 1) = 0;

   return( 0 );
} // GetDataTypeForAttribute

zSHORT OPERATION
ParseOutEntityAttribute( zPCHAR pchString,
                         zPCHAR pchEntityName,
                         zPCHAR pchAttributeName,
                         zPLONG plSkipLth )
{
   zLONG lStartPos;

   // pchString is pointing to the first character of the entity name on entry to this routine.
   // Parse out Entity Name
   lStartPos = (zLONG) pchString;
   while ( *pchString != '.' && *pchString != ']' && *pchString != '}' )
   {
      *pchEntityName = *pchString;
      pchEntityName++;
      pchString++;
   }

   if ( *pchString == '}' )
      return( -2 );

   *pchEntityName = 0;
   if ( *pchString != ']' )
   {
      // There is an attribute, so keep going.
      pchString++;
      // Parse out Attribute Name
      while ( *pchString != ']' && *pchString != '}' )
      {
         *pchAttributeName = *pchString;
         pchAttributeName++;
         pchString++;
      }

      if ( *pchString == '}' )
         return( -2 );

      *pchAttributeName = 0;
   }

   *plSkipLth = (zLONG) pchString - lStartPos + 1;

   return( 0 );
}

zSHORT OPERATION
ConvertCharacterString( zPPCHAR ppchTarget,
                        zPCHAR  pchSource,
                        zPCHAR  pchOrigMemory,
                        zSHORT  nFileType )  // 1-Text   2-HTML
{
   zPCHAR pchBack;
   zPCHAR pchTarget;
   zLONG  lTabCount;
   zLONG  i;

   // This code checks for "carriage return/line feed" combinations in the
   // text and inserts the correct \par and \tab strings in the target text.
   pchTarget = *ppchTarget;

   // First, determine if the start of the text is preceded by tab characters
   // and if so, count them.
   pchBack = pchOrigMemory - 5;
   lTabCount = 0;
   while ( zstrncmp( pchBack, "\\tab", 4 ) == 0 )
   {
      lTabCount++;
      pchBack = pchBack - 5;
   }

   // Copy the characters, inserting \par and \tab strings as necessary
   // for new lines.
   while ( *pchSource != 0 )
   {
      // Search for carriage return/line feed and insert \par and \tab
      // strings.
      if ( *pchSource == 13 && *(pchSource + 1) == 10 )
      {
         // Copy carriage control and line feed characters.
         *pchTarget = *pchSource;
         pchTarget++;
         pchSource++;
         *pchTarget = *pchSource;
         pchTarget++;
         pchSource++;
         // Insert \par and \tab characters.
         if ( nFileType == 1 )
         {
            zstrcpy( pchTarget, "\\par " );
            pchTarget += 5;
         }
         else
         {
            zstrcpy( pchTarget, "<br />" );
            pchTarget += 6;
         }

         for ( i = 0; i < lTabCount; i++ )
         {
            zstrcpy( pchTarget, "\\tab " );
            pchTarget = pchTarget + 5;
         }
      }
      else
      {
         *pchTarget = *pchSource;
         pchTarget++;
         pchSource++;
      }
   }

   *ppchTarget = pchTarget;

   return( 0 );
}

zSHORT OPERATION
CopyWithInsertMemoryArea( zVIEW   vResultSet,
                          zPPCHAR ppchStartMappingArea,
                          zPPCHAR ppchEndMappingArea,
                          zPPCHAR ppchMemoryNew,
                          zPCHAR  pchInnerLoopEntityName,
                          zPCHAR  pchStartMemoryNew,
                          zLONG   lTotalOutputSize,
                          zPCHAR  pchEmbeddedImages,
                          zSHORT  nFileType )  // 1-Text   2-HTML
{
   zPCHAR pchMemory;
   zPCHAR pchEndMappingArea;
   zPCHAR pchMemoryNew;
   zPCHAR pchMemoryLoopStartArea;
   zPCHAR pchMemoryLoopEndArea;
   zPCHAR pchOrigMemory;
   zPCHAR pchRBracket;
   zCHAR  VariableBuffer[ 1000 ];
   zCHAR  LoopEntityName[ 33 ];
   zCHAR  EntityName[ 33 ];
   zCHAR  AttributeName[ 33 ];
   zLONG  lSkipLth;
   zSHORT nRC;

   pchEndMappingArea = *ppchEndMappingArea;
   pchMemory = *ppchStartMappingArea;
   pchMemoryNew = *ppchMemoryNew;
   while ( pchMemory < pchEndMappingArea )
   {
      if ( pchMemory[ 0 ] == '[' )
      {
         pchRBracket = zstrchr( pchMemory, ']' );
         if ( pchRBracket )
         {
            *pchRBracket = 0;
            if ( zstrchr( pchMemory, '<' ) ||
                 zstrchr( pchMemory, '>' ) ||
                 zstrchr( pchMemory, '/' ) )
            {
               *pchRBracket = ']';
               pchRBracket = 0;
            }
            else
            {
               *pchRBracket = ']';
            }
         }

         if ( pchRBracket && pchMemory[ 1 ] == 'Z' && pchMemory[ 2 ] == ':' )  // "[Z:"
         {
            if ( pchMemory[ 3 ] == '#' )  // "[Z:#"
            {
               if ( pchMemory[ 4 ] == 'C' )  // "[Z:#C"
               {
                  // Just set the cursor to the next entity.
                  pchMemory = pchMemory + 6;
                  SetCursorNextEntity( vResultSet, pchInnerLoopEntityName, 0 );
               }
               else
               {
                  if ( pchMemory[ 4 ] == 'S' && pchMemory[ 5 ] == ':' )  // "[Z:#S:"
                  {
                     // Parse out the Loop Entity name.
                     nRC = ParseOutEntityAttribute( pchMemory + 6,
                                                    LoopEntityName,
                                                    AttributeName,
                                                    &lSkipLth );
                     if ( nRC < 0 )
                        return( nRC );

                     pchMemory = pchMemory + lSkipLth + 6;

                     // Identify the beginning and end of the data area to
                     // be repeated in variables:
                     //   pchMemoryLoopStartArea and pchMemoryLoopEndArea.
                     pchMemoryLoopStartArea = pchMemory;
                     while ( *pchMemory != ']' &&
                             pchMemory < pchEndMappingArea )
                     {
                        pchMemory++;
                     }

                     pchMemory++;

                     while ( zstrncmp( pchMemory, "[Z:#E]", 6 ) != 0 &&
                             pchMemory < pchEndMappingArea )
                     {
                        pchMemory++;
                     }

                     pchMemoryLoopEndArea = pchMemory;

                     // Process the looping on the specified Entity Name.
                     nRC = SetCursorFirstEntity( vResultSet,
                                                 LoopEntityName, 0 );
                     while ( nRC >= zCURSOR_SET )
                     {
                        // Perform the normal mapping code here.
                        nRC = CopyWithInsertMemoryArea( vResultSet,
                                                        &pchMemoryLoopStartArea,
                                                        &pchMemoryLoopEndArea,
                                                        &pchMemoryNew,
                                                        LoopEntityName,
                                                        pchStartMemoryNew,
                                                        lTotalOutputSize,
                                                        pchEmbeddedImages,
                                                        nFileType );

                        if ( nRC < 0 )
                           return( nRC );

                        nRC = SetCursorNextEntity( vResultSet,
                                                   LoopEntityName, 0 );
                     }

                     // Skip repeating area and the [Z:#E] characters ending
                     // the loop.
                     pchMemory = pchMemoryLoopEndArea + 6;
                  }
                  else
                  {
                     // The else condition is ignored as an error.
                     while ( *pchMemory != ']' &&
                             pchMemory < pchMemoryLoopEndArea )
                     {
                        pchMemory++;
                     }

                     pchMemory++;
                  }
               }
            }
            else
            {
               // Get the Entity and Attribute Names.
               // We save original memory position in pchOrigMemory so the
               // subroutine can search for preceding tab characters.
               pchOrigMemory = pchMemory;
               nRC = ParseOutEntityAttribute( pchMemory + 3, EntityName,
                                              AttributeName, &lSkipLth );
               if ( nRC < 0 )
                  return( nRC );

               pchMemory = pchMemory + lSkipLth + 3;
               // Get the data from the object.
               if ( CheckExistenceOfEntity( vResultSet,
                                            EntityName ) >= zCURSOR_SET )
               {
                  nRC = GetStringFromAttributeByContext( VariableBuffer,
                                                         vResultSet,
                                                         EntityName,
                                                         AttributeName,
                                                         "", 1000 );
                  if ( nRC >= 0 )
                  {
                     ConvertCharacterString( &pchMemoryNew, VariableBuffer,
                                             pchOrigMemory, nFileType );
                  }
               }
            }
         }
         else
         {
            // Just copy the character
            *pchMemoryNew = *pchMemory;
            pchMemory++;
            pchMemoryNew++;
         }
      }
      else
      // <IMG SRC="cid:message-root.1.d:\10e\a\tz\image1.gif" ALT="image1.gif">
      if ( pchEmbeddedImages && pchMemory[ 0 ] == '<' &&
           zstrnicmp( pchMemory + 1, "IMG SRC=", 0 ) == 0 &&
           zstrncmp( pchMemory + 10, "cid:message-root.", 17 ) == 0 )
      {
         zstrncpy( pchMemoryNew, pchMemory, 27 );
         pchMemoryNew += 27;
         pchMemory += 27;
         while ( pchMemory[ 0 ] &&
                 pchMemory[ 0 ] != '.' &&
                 pchMemory[ 0 ] != '"' )
         {
            // Just copy the character
            *pchMemoryNew = *pchMemory;
            pchMemory++;
            pchMemoryNew++;
         }

         if ( pchMemory[ 0 ] == '.' )
         {
            pchMemory++;  // skip past '.'
            lSkipLth = zstrlen( pchEmbeddedImages );
            if ( lSkipLth > 0 )
               pchEmbeddedImages[ lSkipLth++ ] = ';';

            while ( pchMemory[ 0 ] &&
                    pchMemory[ 0 ] != '"' )
            {
               pchEmbeddedImages[ lSkipLth++ ] = *pchMemory;
               pchMemory++;
            }

            pchEmbeddedImages[ lSkipLth ] = 0;
         }

         // Finally copy the terminating quote
         *pchMemoryNew = *pchMemory;
         pchMemory++;
         pchMemoryNew++;
      }
      else
      {
         // Just copy the character
         *pchMemoryNew = *pchMemory;
         pchMemory++;
         pchMemoryNew++;
      }
   }

   if ( pchMemoryNew - pchStartMemoryNew > lTotalOutputSize )
   {
      MessageBox( 0, "CopyWithInsertMemoryArea Overwrite Buffer",
                  "Memory Overwrite", MB_OK );
   }

   *ppchMemoryNew = pchMemoryNew;
   return( 0 );
}

zLONG
ReadFileDataIntoMemory( zVIEW   vResultSet,
                        zPCHAR  szDocumentFile,
                        zPLONG  phDocumentMemory,
                        zPCHAR  *ppvDocumentData )
{
   zLONG  hFileDocument;
   zLONG  lDocumentLth;

   *phDocumentMemory = 0;
   *ppvDocumentData = 0;
   lDocumentLth = 0;

   hFileDocument = SysOpenFile( vResultSet, szDocumentFile, COREFILE_READ );
   if ( hFileDocument < 0 )
   {
   // IssueError( vResultSet, 0, 0, "Can't open Document file." );
      return( -1 );
   }

   lDocumentLth = GetFileSize( (HANDLE) hFileDocument, 0 );

   // Exit if the document file is empty.
   if ( lDocumentLth == 0 )
   {
      SysCloseFile( vResultSet, hFileDocument, 0 );
      return( 0 );
   }

   *phDocumentMemory = SysAllocMemory( ppvDocumentData,
                                       lDocumentLth, 0,
                                       zCOREMEM_ALLOC, 0 );
// DrAllocTaskMemory( ppvDocumentData, lDocumentLth );
// TraceLine( "ReadFileDataIntoMemory: 0x%x   Size: %d",
//            (zLONG) *ppvDocumentData, lDocumentLth );

// **ppvDocumentData = 0;
   SysReadFile( vResultSet, hFileDocument, *ppvDocumentData, lDocumentLth );
   SysCloseFile( vResultSet, hFileDocument, 0 );

   if ( **ppvDocumentData == 0 )
   {
      SysFreeMemory( *phDocumentMemory  );
   // DrFreeTaskMemory( *ppvDocumentData );
      *phDocumentMemory = 0;
      *ppvDocumentData = 0;
      lDocumentLth = 0;
      IssueError( vResultSet, 0, 0, "Read Error on Document file" );
      return( -1 );
   }

   return( lDocumentLth );
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: MergeSingleRS_EntryWithTemplate
//
//    Insert OI variable data in Template
//
//    lFlags  0 - No Attachment / Mime Type Text
//            1 - Has Attachment
//            2 - Mime Type HTML
//           16 - Prompt if there are any recipients that do not have
//                an email address specified
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
MergeSingleRS_EntryWithTemplate( zVIEW  vResultSet,
                                 zPCHAR TemplateFileName,
                                 zPCHAR OutputFileName,
                                 zPCHAR pchEmbeddedImages,
                                 zSHORT nFileType ) // 1-Text   2-HTML
{
   zLONG  hFileTo;
   zPCHAR pchMemoryStartOrig;
   zPCHAR pchMemoryEndOrig;
   zPCHAR pchMemoryStartNew;
   zPCHAR pchMemoryEndNew;
   zPCHAR pchMemoryStartEnd;
   zLONG  selMemoryOrig;
   zLONG  selMemoryNew;
   zLONG  lTemplateLth;
   zLONG  lTotalOutputSize;
   zLONG  lLthNew;
   zULONG ulRC;
   zSHORT nRC;

   // Get memory for output file.
   // The size of the new memory will add 8000 bytes of variable data to the
   // template size to account for merged data.
   lTemplateLth = ReadFileDataIntoMemory( vResultSet, TemplateFileName,
                                          &selMemoryOrig, &pchMemoryStartOrig );
   // Exit if the template file is empty or if there is an error opening it.
   if ( lTemplateLth <= 0 )
   {
      MessageSend( vResultSet, "", "Template File Merge",
                   "The Template File could not be opened.\n"
                     "Check that the file name specified is valid.",
                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      return( -1 );
   }

   pchMemoryEndOrig = pchMemoryStartOrig + lTemplateLth;
   lTotalOutputSize = lTemplateLth + 8000;
   selMemoryNew = SysAllocMemory( (zCOREMEM) &pchMemoryStartNew, lTotalOutputSize,
                                  0, zCOREMEM_ALLOC, 0 );
// DrAllocTaskMemory( (zCOREMEM) &pchMemoryStartNew, lTotalOutputSize );
// TraceLine( "MergeSingleRS_EntryWithTemplate: 0x%x   Size: %d",
//            (zLONG) pchMemoryStartNew, lTotalOutputSize );

   // Perform the normal mapping code here.
   pchMemoryStartEnd = pchMemoryStartNew;
   pchEmbeddedImages[ 0 ] = 0;
   nRC = CopyWithInsertMemoryArea( vResultSet,
                                   &pchMemoryStartOrig,
                                   &pchMemoryEndOrig,
                                   &pchMemoryStartEnd, "",
                                   pchMemoryStartNew,
                                   lTotalOutputSize,
                                   pchEmbeddedImages,
                                   nFileType );
   SysFreeMemory( selMemoryOrig );
// DrFreeTaskMemory( pchMemoryStartOrig );
   if ( nRC < 0 )
   {
      MessageSend( vResultSet, "", "Template File Merge",
                   "A Merge error occurred.\n"
                     "Check the Template file for invalid data or recreate the file.",
                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      return( nRC );
   }

   // End file.
   pchMemoryEndNew = pchMemoryStartEnd;
   *pchMemoryEndNew = 0;
   pchMemoryEndNew++;

   // Write the merged resulting data in memory out to the file.
   lLthNew = (zLONG) (pchMemoryEndNew - pchMemoryStartNew);
   hFileTo = SysOpenFile( vResultSet, OutputFileName, COREFILE_WRITE );
   if ( hFileTo < 0 )
   {
      SysFreeMemory( selMemoryNew );
   // DrFreeTaskMemory( pchMemoryStartNew );
      MessageSend( vResultSet, "", "Template File Merge",
                   "The Output File could not be opened.\n"
                     "Check that the file directory specified is valid.",
                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );;
      return( -1 );
   }

   WriteFile( (HANDLE) hFileTo, pchMemoryStartNew, lLthNew, &ulRC, 0 );
   SysCloseFile( vResultSet, hFileTo, 0 );
   SysFreeMemory( selMemoryNew );
// DrFreeTaskMemory( pchMemoryStartNew );

   return( 0 );

} // MergeSingleRS_EntryWithTemplate

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: InsertOI_DataIntoTemplate
//
//    Insert OI variable data in Template
//
//    lFlags  0 - No Attachment / Mime Type Text
//            1 - Has Attachment
//            2 - Mime Type HTML
//           16 - Prompt if recipient(s) does not have email address
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
InsertOI_DataIntoEmailTemplate( zVIEW  ViewToWindow,
                                zVIEW  vResultSet,
                                zPCHAR pchSMTPServer,
                                zPCHAR pchEMailUserName,
                                zPCHAR pchEMailPassword,
                                zPCHAR pchSenderEMailAddress,
                                zPCHAR pchSubjectLine,
                                zPCHAR pchAttachmentFileName,
                                zPCHAR pchTemplateFileName,
                                zPCHAR pchAltFileName,
                                zPCHAR pchRootEntityName,
                                zLONG  lFlags )
{
   char   szEmbeddedImages[ 16000 ];   // should be WAY more than enough
   char   szRecipientEMailAddress[ 512 ];
   zSHORT nHasAttachment;
   zSHORT nMimeType;
   zLONG  lConnection;
// zLONG  hFileTo;
   zPCHAR pchMemory;
   zPCHAR pchMemoryNew;
   zPCHAR pchMemoryNewHold;
   zPCHAR pchMemoryStartEmailBody;
   zPCHAR pchMemoryStartOld;
   zPCHAR pchMemoryEndOld;
   zPCHAR pchMemoryStartArea;
   zPCHAR pchMemoryEndArea;
   zLONG  selMemory;
   zLONG  selMemoryNew;
   zLONG  lTemplateLth;

   zPCHAR pchAltMemory;
   zPCHAR pchAltMemoryNew;
   zPCHAR pchAltMemoryNewHold;
   zPCHAR pchAltMemoryStartEmailBody;
   zPCHAR pchAltMemoryStartOld;
   zPCHAR pchAltMemoryEndOld;
   zPCHAR pchAltMemoryStartArea;
   zPCHAR pchAltMemoryEndArea;
   zLONG  selAltMemory;
   zLONG  selAltMemoryNew;
   zLONG  lAltTemplateLth;
   zLONG  lAltTotalOutputSize;
   zLONG  lAltLthNew;

   zLONG  lSelectedCount;
   zLONG  lCurrentCount;
   zLONG  lTotalOutputSize;
   zLONG  lLthNew;
   zBOOL  bNoEmailAddress = FALSE;
// zULONG ulRC;
   zSHORT nRC;

   if ( lFlags & 0x00000001 )
      nHasAttachment = 1;  // has attachment
   else
      nHasAttachment = 0;  // no attachment

   if ( lFlags & 0x00000002 )
      nMimeType = 2;  // HTML
   else
      nMimeType = 1;  // Text

   // The size of the new memory will add 8000 bytes of variable data to the
   // template size.
   lSelectedCount = 0;
   nRC = SetCursorFirstEntity( vResultSet, pchRootEntityName, 0 );
   while ( nRC > zCURSOR_UNCHANGED )
   {
      nRC = GetSelectStateOfEntity( vResultSet, pchRootEntityName );
      if ( nRC == 1 )
      {
         GetStringFromAttribute( szRecipientEMailAddress, vResultSet,
                                 "Person", "eMailAddress" );
         if ( szRecipientEMailAddress[ 0 ] )
            lSelectedCount++;
         else
         {
            if ( bNoEmailAddress == FALSE )
            {
               bNoEmailAddress = TRUE;
               TraceLineS( "EMail NOT sent Subject: ", pchSubjectLine );
            }

            GetStringFromAttribute( szRecipientEMailAddress, vResultSet,
                                    "Person", "LastName" );
            lLthNew = zstrlen( szRecipientEMailAddress );
            szRecipientEMailAddress[ lLthNew++ ] = ',';
            szRecipientEMailAddress[ lLthNew++ ] = ' ';

            GetStringFromAttribute( szRecipientEMailAddress + lLthNew,
                                    vResultSet, "Person", "FirstName" );
            lLthNew = zstrlen( szRecipientEMailAddress );
            szRecipientEMailAddress[ lLthNew++ ] = ' ';

            GetStringFromAttribute( szRecipientEMailAddress + lLthNew,
                                    vResultSet, "Person", "MiddleName" );
            TraceLineS( "  No EMail address for: ", szRecipientEMailAddress );
         }
      }

      nRC = SetCursorNextEntity( vResultSet, pchRootEntityName, 0 );
   }

   if ( bNoEmailAddress && (lFlags & 0x00000010) != 0 )
   {
      if ( OperatorPrompt( vResultSet,
                           "EMail not sent to recipient(s)",
                           "See Zeidon Trace for list ... Continue?",
                           TRUE, zBUTTONS_YESNO, zRESPONSE_NO,
                           0 ) == zRESPONSE_NO )
      {
         return( 0 );
      }
   }

   // We'll just exit here if nothing was selected.
   if ( lSelectedCount == 0 )
      return( 0 );

   lTemplateLth = ReadFileDataIntoMemory( vResultSet, pchTemplateFileName,
                                          &selMemory, &pchMemoryStartOld );

   // Exit if the template file is empty or if there is an error opening it.
   if ( lTemplateLth <= 0 )
   {
      IssueError( vResultSet, 0, 0, "Can't open Template file." );
      return( 0 );
   }

   lAltTemplateLth = 0;
   if ( nMimeType == 2 )  // HTML
   {
      if ( pchAltFileName && pchAltFileName[ 0 ] )
      {
         lAltTemplateLth = ReadFileDataIntoMemory( vResultSet,
                                                   pchAltFileName,
                                                   &selAltMemory,
                                                   &pchAltMemoryStartOld );
      }

      // Exit if the alt template file is empty or if there is an error opening it.
      if ( lAltTemplateLth > 0 )
      {
         lAltTotalOutputSize = lAltTemplateLth + 8000;
         selAltMemoryNew = SysAllocMemory( (zCOREMEM) &pchAltMemoryNewHold,
                                           lAltTotalOutputSize, 0,
                                           zCOREMEM_ALLOC, 0 );
      }
      else
      {
         // IssueError( vResultSet, 0, 0, "Can't open Alt Template file." );
         // return( 0 );
      }
   }

   lTotalOutputSize = lTemplateLth + 8000;
   selMemoryNew = SysAllocMemory( (zCOREMEM) &pchMemoryNewHold,
                                  lTotalOutputSize, 0,
                                  zCOREMEM_ALLOC, 0 );

// DrAllocTaskMemory( (zCOREMEM) &pchMemoryNewHold, lTotalOutputSize );
// TraceLine( "InsertOI_DataIntoEmailTemplate: 0x%x   Size: %d",
//            (zLONG) pchMemoryNewHold, lTotalOutputSize );

   lConnection = CreateSeeConnection( pchSMTPServer, pchSenderEMailAddress,
                                      pchEMailUserName, pchEMailPassword );

   // For each selected item, map the repeatable data in the template to
   // the output buffer.
   lCurrentCount = 0;
   nRC = SetCursorFirstEntity( vResultSet, pchRootEntityName, 0 );
   while ( nRC > zCURSOR_UNCHANGED )
   {
      nRC = GetSelectStateOfEntity( vResultSet, pchRootEntityName );
      if ( nRC == 1 )
      {
         GetStringFromAttribute( szRecipientEMailAddress, vResultSet,
                                 "Person", "eMailAddress" );
         if ( szRecipientEMailAddress[ 0 ] )
         {
            if ( lAltTemplateLth > 0 )
            {
               pchAltMemoryNew = pchAltMemoryNewHold;
               lCurrentCount++;
               pchAltMemoryEndOld = pchAltMemoryStartOld + lTemplateLth;

               // Initialize Output values.
               pchAltMemoryStartEmailBody = pchAltMemoryNew;
               lAltLthNew = 0;

               // Copy the first brace that starts the file.
               pchAltMemory = pchAltMemoryStartOld;
               *pchAltMemoryNew = *pchAltMemory;
               pchAltMemory++;
               pchAltMemoryNew++;

               // Set the start of repeatable area (one per document copy) as
               // second character in Template file.
               pchAltMemoryStartArea = pchAltMemory;

               // Set the end of repeatable area as one character before the last
               // character in Template file.
               pchAltMemoryEndArea = pchAltMemoryEndOld - 1;
               pchAltMemory = pchAltMemoryStartArea;

               // Perform the normal mapping code here.
               nRC = CopyWithInsertMemoryArea( vResultSet,
                                               &pchAltMemoryStartArea,
                                               &pchAltMemoryEndArea,
                                               &pchAltMemoryNew, "",
                                               pchAltMemoryNewHold,
                                               lAltTotalOutputSize,
                                               0, 1 );
               if ( nRC < 0 )
                  return( nRC );

               // Finally copy the closing brace to the output file.
               *pchAltMemoryNew = *pchAltMemoryEndArea;
               pchAltMemory++;
               pchAltMemoryNew++;

               lAltLthNew = (zLONG) (pchAltMemoryNew - pchAltMemoryStartEmailBody);
            }
            else
               pchAltMemory = 0;

            pchMemoryNew = pchMemoryNewHold;
            lCurrentCount++;
            pchMemoryEndOld = pchMemoryStartOld + lTemplateLth;

            // Initialize Output values.
            pchMemoryStartEmailBody = pchMemoryNew;
            lLthNew = 0;

            // Copy the first brace that starts the file.
            pchMemory = pchMemoryStartOld;
            *pchMemoryNew = *pchMemory;
            pchMemory++;
            pchMemoryNew++;

            // Set the start of repeatable area (one per document copy) as
            // second character in Template file.
            pchMemoryStartArea = pchMemory;

            // Set the end of repeatable area as one character before the last
            // character in Template file.
            pchMemoryEndArea = pchMemoryEndOld - 1;
            pchMemory = pchMemoryStartArea;

            // Perform the normal mapping code here.
            szEmbeddedImages[ 0 ] = 0;
            nRC = CopyWithInsertMemoryArea( vResultSet,
                                            &pchMemoryStartArea,
                                            &pchMemoryEndArea,
                                            &pchMemoryNew, "",
                                            pchMemoryNewHold,
                                            lTotalOutputSize,
                                            szEmbeddedImages,
                                            nMimeType );
            if ( nRC < 0 )
               return( nRC );

            // Finally copy the closing brace to the output file.
            *pchMemoryNew = *pchMemoryEndArea;
            pchMemory++;
            pchMemoryNew++;

            lLthNew = (zLONG) (pchMemoryNew - pchMemoryStartEmailBody);
         // hFileTo = SysOpenFile( ViewToWindow, pchAttachmentFileName, COREFILE_WRITE );
         // if ( hFileTo < 0 )
         // {
         //    IssueError( vResultSet, 0, 0, "Write Open Error" );
         //    return( -1 );
         // }

         // WriteFile( (HANDLE) hFileTo, pchMemoryStartEmailBody, lLthNew, &ulRC, 0 );
         // SysCloseFile( ViewToWindow, hFileTo, 0 );

            CreateSeeMessage( lConnection, pchSMTPServer,
                              pchSenderEMailAddress,
                              szRecipientEMailAddress,
                              "", "", pchSubjectLine, nMimeType,
                              pchMemoryStartEmailBody,
                              pchAltMemory, szEmbeddedImages,
                              nHasAttachment, pchAttachmentFileName,
                              pchEMailUserName, pchEMailPassword );

         // SysOpenFile( ViewToWindow, pchAttachmentFileName, COREFILE_DELETE );
         }
      }

      nRC = SetCursorNextEntity( vResultSet, pchRootEntityName, 0 );
   }

   CloseSeeConnection( lConnection );
   SysFreeMemory( selMemory );
   SysFreeMemory( selMemoryNew );
   if ( lAltTemplateLth )
   {
      SysFreeMemory( selAltMemory );
      SysFreeMemory( selAltMemoryNew );
   }

// DrFreeTaskMemory( pchMemoryStartOld );
// DrFreeTaskMemory( pchMemoryNewHold );

   return( 0 );

} // InsertOI_DataIntoEmailTemplate

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: InsertOI_DataIntoTemplateFile
//
//    Insert OI variable data in Template File
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
InsertOI_DataIntoTemplateFile( zVIEW  ViewToWindow,
                               zVIEW  vResultSet,
                               zPCHAR pchOutputFileName,
                               zPCHAR pchTemplateFileName,
                               zPCHAR pchAltFileName,
                               zPCHAR pchRootEntityName )
{
   zLONG  hFileTo;
   zPCHAR pchMemory;
   zPCHAR pchMemoryNew;
   zPCHAR pchMemoryStartNew;
   zPCHAR pchMemoryStartOld;
   zPCHAR pchMemoryEndOld;
   zPCHAR pchMemoryStartArea;
   zPCHAR pchMemoryEndArea;
   zLONG  selMemory;
   zLONG  selMemoryNew;
   zLONG  lTemplateLth;
   zLONG  lSelectedCount;
   zLONG  lCurrentCount;
   zLONG  lTotalOutputSize;
   zLONG  lLthNew;
   zULONG ulRC;
   zSHORT nRC;

   // The size of the new memory will try to allow for the number of select
   // copies to be made of the input template.  It will thus add 8000 bytes
   // of variable data to the template size and multiply that by the number
   // of items selected.
   lSelectedCount = 0;
   nRC = SetCursorFirstEntity( vResultSet, pchRootEntityName, 0 );
   while ( nRC > zCURSOR_UNCHANGED )
   {
      nRC = GetSelectStateOfEntity( vResultSet, pchRootEntityName );
      if ( nRC == 1 )
         lSelectedCount++;

      nRC = SetCursorNextEntity( vResultSet, pchRootEntityName, 0 );
   }

   // We'll just exit here if nothing was selected.
   if ( lSelectedCount == 0 )
      return( 0 );

   lTemplateLth = ReadFileDataIntoMemory( vResultSet, pchTemplateFileName,
                                          &selMemory, &pchMemoryStartOld );

   // Exit if the template file is empty or if there is an error opening it.
   if ( lTemplateLth <= 0 )
   {
      IssueError( vResultSet, 0, 0, "Can't open Template file." );
      return( 0 );
   }

   lTotalOutputSize = (lTemplateLth + 8000) * lSelectedCount;
   selMemoryNew = SysAllocMemory( (zCOREMEM) &pchMemoryNew, lTotalOutputSize,
                                  0, zCOREMEM_ALLOC, 0 );
// DrAllocTaskMemory( (zCOREMEM) &pchMemoryNew, lTotalOutputSize );
// TraceLine( "InsertOI_DataIntoTemplateFile: 0x%x   Size: %d",
//            (zLONG) pchMemoryNew, lTotalOutputSize );

   pchMemoryEndOld = pchMemoryStartOld + lTemplateLth;

   // Initialize Output values.
   pchMemoryStartNew = pchMemoryNew;
   lLthNew = 0;

   // Copy the first brace that starts the file.
   pchMemory = pchMemoryStartOld;
   *pchMemoryNew = *pchMemory;
   pchMemory++;
   pchMemoryNew++;

   // Set the start of repeatable area (one per document copy) as second
   // character in Template file.
   pchMemoryStartArea = pchMemory;

   // Set the end of repeatable area as one character before the last
   // character in Template file.
   pchMemoryEndArea = pchMemoryEndOld - 1;

   // For each selected item, map the repeatable data in the template to the
   // output buffer.
   nRC = SetCursorFirstEntity( vResultSet, pchRootEntityName, 0 );
   lCurrentCount = 0;
   while ( nRC > zCURSOR_UNCHANGED )
   {
      nRC = GetSelectStateOfEntity( vResultSet, pchRootEntityName );
      if ( nRC == 1 )
      {
         // If this is any page but the first, add in the page break
         // characters.
         lCurrentCount++;
         if ( lCurrentCount > 1 )
         {
            zstrcpy( pchMemoryNew, "\x0D\x0A\\par \\page \\hich\\af0\\dbch\\af28\\loch\\f0 " );
            pchMemoryNew = pchMemoryNew + 41;
         }

         pchMemory = pchMemoryStartArea;
         // Perform the normal mapping code here.
         nRC = CopyWithInsertMemoryArea( vResultSet,
                                         &pchMemoryStartArea,
                                         &pchMemoryEndArea,
                                         &pchMemoryNew, "",
                                         pchMemoryNew,
                                         lTotalOutputSize,
                                         0,    // no embedded images
                                         1 );  // Text
         if ( nRC < 0 )
            return( nRC );
      }

      nRC = SetCursorNextEntity( vResultSet, pchRootEntityName, 0 );
   }

   // Finally copy the closing brace to the output file.
   *pchMemoryNew = *pchMemoryEndArea;
   pchMemory++;
   pchMemoryNew++;

   lLthNew = (zLONG) (pchMemoryNew - pchMemoryStartNew);
   SysFreeMemory( selMemory );
// DrFreeTaskMemory( pchMemoryStartOld );
   hFileTo = SysOpenFile( ViewToWindow, pchOutputFileName, COREFILE_WRITE );
   if ( hFileTo < 0 )
   {
      SysFreeMemory( selMemoryNew );
   // DrFreeTaskMemory( pchMemoryNew );
      IssueError( vResultSet, 0, 0, "Write Open Error" );
      return( -1 );
   }

   WriteFile( (HANDLE) hFileTo, pchMemoryStartNew, lLthNew, &ulRC, 0 );
   SysCloseFile( ViewToWindow, hFileTo, 0 );
   SysFreeMemory( selMemoryNew );
// DrFreeTaskMemory( pchMemoryNew );

   return( 0 );

} // InsertOI_DataIntoTemplateFile

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ReadLine5000
//
//    Read a line into a 5000 character string
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zLONG OPERATION
ReadLine5000( zVIEW  ViewToWindow,
              zPCHAR pchLineBuffer,
              zLONG  FileHandle )
{
   zLONG lRC;
   zPCHAR pchReturnedBuffer;
   zLONG  lCount;

   lRC = SysReadLine( ViewToWindow, &pchReturnedBuffer, FileHandle );
   if ( lRC == 0 )
      return( lRC );

   // Copy characters from pointer returned to passed string area.
   lCount = 0;
   while ( *pchReturnedBuffer != 0 )
   {
      *pchLineBuffer = *pchReturnedBuffer;
      pchReturnedBuffer++;
      pchLineBuffer++;
      lCount++;
      if ( lCount > 5000 )
         TraceLineS( "************* > 5000", "***********" );
                                  
   }

   *pchLineBuffer = 0;
   return( lRC );

} // ReadLine5000


/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ConvertLineToEntity
//
//    Convert data in a comma or tab delimited record to attribute values in an entity.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
ConvertLineToEntity( zVIEW  vTarget,
                     zVIEW  vXOD,
                     zPCHAR pchRecord,
                     zPCHAR pchDelimiterType,
                     zLONG  lMaxRecordLth )
{
   zSHORT nRC;
   zCHAR  cDelimiter;
   zCHAR  szDataValue[ 1000 ];
   zCHAR  pchEntityName[ 33 ];
   zCHAR  pchAttributeName[ 33 ];
   zCHAR  szTab[ 2 ];
   zCHAR  szDate[ 9 ];
   zPCHAR pchDataValue;
   zPCHAR pchRecordEnd;

   zstrcpy( szTab, "x09" );
   if ( *pchDelimiterType == 'T' )
      cDelimiter = szTab[ 1 ];
   else
      cDelimiter = szTab[ 1 ];

   pchRecordEnd = pchRecord + lMaxRecordLth;
   GetStringFromAttribute( pchEntityName, vXOD, "ENTITY", "NAME" );

   // Loop for each Attribute in the Entity, parsing the record string and setting the Attribute.
   nRC = SetCursorFirstEntity( vXOD, "ATTRIB", 0 );
   while ( nRC >= zCURSOR_SET && pchRecord < pchRecordEnd )
   {
      GetStringFromAttribute( pchAttributeName, vXOD, "ATTRIB", "NAME" );
      pchDataValue = szDataValue;
      *pchDataValue = 0;
      while ( *pchRecord != 9 && pchRecord < pchRecordEnd )
      {
         *pchDataValue = *pchRecord;
         pchRecord++;
         pchDataValue++;
      }

      *pchDataValue = 0;
      pchRecord++;

      if ( CompareAttributeToString( vXOD, "ATTRIB", "TYPE", "T" ) == 0 &&
           zstrlen( szDataValue ) <= 8 )
      {
         // Attribute is Date, so convert properly.
         if ( zstrlen( szDataValue ) == 7 )
         {
            zstrcpy( szDate, "0" );
            zstrcat( szDate, szDataValue );
         }
         else
            zstrcpy( szDate, szDataValue );
         // A value of "0" is really null.
         if ( zstrcmp( szDate, "0" ) == 0 )
            szDate[ 0 ] = 0;

         SetAttrFromStrByContext( vTarget, pchEntityName, pchAttributeName, szDate, "MMDDYYYY" );
      }
      else
         // Attribute is treated as simple Text.
         SetAttributeFromString( vTarget, pchEntityName, pchAttributeName, szDataValue );

      nRC = SetCursorNextEntity( vXOD, "ATTRIB", 0 );
   }

   return( 0 );
} // ConvertLineToEntity


/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: GetCurrentApplicationName
//
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
GetCurrentApplicationName( zPCHAR szReturnedString,
                           zLONG lMaxLength,
                           zVIEW ViewToWindow )
{
   LPAPP  pchApp;
   zPCHAR pchToString;
   zPCHAR pchFromString;

   SfGetApplicationForSubtask( &pchApp, ViewToWindow );
   *szReturnedString = 0;
   if ( pchApp )
   {
      pchFromString = pchApp->szName;
      pchToString   = szReturnedString;
      zstrncpy( pchToString, pchFromString, lMaxLength );
   }

   return( 0 );
} // GetCurrentApplicationName

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: DBQualEntityByString
//
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
DBQualEntityByString( zVIEW  vQualObject,
                      zPCHAR pchEntity,
                      zPCHAR pchAttrib,
                      zPCHAR szOper,
                      zPCHAR szValue,
                      zLONG  bEXISTS )
{
   // add qualification
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", pchEntity );
   if ( bEXISTS == TRUE )
   {
      SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
      CreateEntity( vQualObject, "SubQualAttrib", zPOS_AFTER );
      SetAttributeFromString( vQualObject, "SubQualAttrib", "EntityName", pchEntity );
      SetAttributeFromString( vQualObject, "SubQualAttrib", "AttributeName", pchAttrib );
      SetAttributeFromString( vQualObject, "SubQualAttrib", "Value", szValue );
      SetAttributeFromString( vQualObject, "SubQualAttrib", "Oper", szOper );
   }
   else
   {
      SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", pchAttrib );
      SetAttributeFromString( vQualObject, "QualAttrib", "Value", szValue );
      SetAttributeFromString( vQualObject, "QualAttrib", "Oper", szOper );
   }

   return( 0 );
} // DBQualEntityByString

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ParseBooleanExpression
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
ParseBooleanExpression( zVIEW  zqFrame )
{
   zPCHAR pchValue;
   zPCHAR pchNext;
   zCHAR  szBooleanExpression[ 255 ];
   zCHAR  szConditionValue[ 255 ];

   // Parse the Boolean Expression and create each component value as an entity Component.

   GetStringFromAttributeByContext( szBooleanExpression,
                                    zqFrame, "BooleanExpression",
                                    "TextValue", "", 254 );

   // Skip to first nonblank.
   for ( pchNext = szBooleanExpression;
         *pchNext == ' ' && *pchNext != 0;
         pchNext++ )
   {
   }

   // Loop through all parameters.
   while ( *pchNext != 0 )
   {
      // Find next parameter
      if ( *pchNext == ')' || *pchNext == '(' )
      {
         pchValue = szConditionValue;
         *pchValue = *pchNext;
         pchValue++;
         *pchValue = 0;
         if ( *pchNext == ')' )
            pchNext++;    // We need to do the skip here for close paren
      }
      else
      {
        for ( pchValue = szConditionValue;
              *pchNext != ' ' && *pchNext != 0 && *pchNext != ')';
              pchNext++ )
         {
            *pchValue = *pchNext;
            pchValue++;
         }

         *pchValue = 0;

      }

      CreateEntity( zqFrame, "Component", zPOS_AFTER );
      SetAttributeFromString( zqFrame, "Component", "Value", szConditionValue );

      if ( *pchNext != 0 && *pchNext != ')' )
         pchNext++;

      // Skip to next nonblank.
      while ( *pchNext == ' ' && *pchNext != 0 )
         pchNext++;
   }

   return( 0 );
} // ParseBooleanExpression

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: WinShellExecute
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
WinShellExecute( zVIEW  vSubtask,
                 zPCHAR szFileOrExeName,
                 zPCHAR szFileOpenCommand,
                 zPCHAR szExeParams )
{
   //HWND hWnd;
   zULONG hWnd;
   zULONG lControlReturn;
   zULONG hInstance;

   GetWindowHandle( &hWnd,   &lControlReturn, vSubtask, "" );
   //GetWindowHandle( PTR UNSIGNED zLONG /* Window Return */,
   //                PTR UNSIGNED zLONG /* ControlReturn */,
   //                zVIEW /* Subtask */,
   //                zCHAR /* CtrlTag */ );

   if ( szFileOpenCommand != 0 && *szFileOpenCommand != 0 )
      TraceLineS( "WinShellExecute FileOpenCommand: ", szFileOpenCommand );

   if ( szFileOrExeName != 0 && *szFileOrExeName != 0 )
      TraceLineS( "WinShellExecute FileOrExeName: ", szFileOrExeName );

   if ( szExeParams != 0 && *szExeParams != 0 )
      TraceLineS( "WinShellExecute ExeParams: ", szExeParams );

   hInstance = (zULONG) ShellExecute( (HWND) hWnd, szFileOpenCommand,
                                      szFileOrExeName, szExeParams,
                                      NULL, SW_SHOWNORMAL );
   if ( hInstance > 32 )
   {
      TraceLineI( "WinShellExecute Success - Result: ", hInstance );
      return( 0 );
   }
   else
   {
      TraceLineI( "WinShellExecute Failed Result: ", hInstance );
      return( (zSHORT)hInstance );
   }

    return( 0 );
} // WinShellExecute


zOPER_EXPORT zSHORT OPERATION
GetRTFPath( zVIEW vSubtask, zLONG lFlag,  zPCHAR pchTarget )
{
   char   szReturn[ 256 ];
   char   szCLSID[ 256 ];
   zSHORT nRC = FALSE;

   szReturn[ 0 ] = 0;
   if ( lFlag == 2 ) // open for print
   {
      GetRegistryCLSID( szCLSID, "rtffile" );
      nRC = GetRegistryPrintValue( "", "rtffile", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );

      TraceLineS( "RTF-Print-Flag 3 szCLSID !", szCLSID ) ;
      TraceLineS( "RTF-Print-Flag 3 Return !", szReturn ) ;
      if ( nRC == FALSE )
      {
         nRC = GetRegistryPrintValue( "", "rtffile", szCLSID, REG_SZ, szReturn, sizeof( szReturn ));
         TraceLineS( "RTF-Print-Flag 3 szCLSID [win98]!", szCLSID ) ;
         TraceLineS( "RTF-Print-Flag 3 Return [win98]!", szReturn ) ;
      }
   }

   if ( lFlag == 3 ) // open for view
   {
      GetRegistryCLSID( szCLSID, "rtffile" );
      nRC = GetRegistryGeneralValue( "", "rtffile", szCLSID, REG_SZ, szReturn, sizeof( szReturn ) );

      TraceLineS( "RTF-Flag 3 szCLSID !", szCLSID ) ;
      TraceLineS( "RTF-Flag 3 Return !", szReturn ) ;
      if ( nRC == FALSE )
      {
         // for win98 in case we are not in win2K
         nRC = GetRegistryGeneralValue( "", "rtffile", szCLSID,
                                        REG_EXPAND_SZ, szReturn,
                                        sizeof( szReturn ) );
         TraceLineS( "RTF-Flag 3C szCLSID [win98]!", szCLSID ) ;
         TraceLineS( "RTF-Flag 3C Return [win98]!", szReturn ) ;
      }
   }

   zstrcpy( pchTarget, szReturn );
   return( 0 );
}

zOPER_EXPORT zLONG OPERATION
StartEmailClientForListReus( zVIEW   vResult,
                             zPCHAR  cpcEntityName,
                             zPCHAR  cpcAttributeName,
                             zPCHAR  cpcContext,
                             zPCHAR  cpcScope,
                             zSHORT  bUseOnlySelectedEntities,
                             zSHORT  bUseParentSelectedEntities,
                             zPCHAR  cpcSubject,
                             zPCHAR  cpcCopyTo,        // comma separated list
                             zPCHAR  cpcBlindCopy,     // comma separated list
                             zPCHAR  cpcBody,
                             zPCHAR  cpcAttachment,
                             zPCHAR  cpcEmailClient,
                             zLONG   lFlags,
                             zPCHAR  cpcBlindCopyFlag )          // reserved
{
   char   szParentEntity[ 33 ];
   zPCHAR pchMemory;
   zLONG  lEntityCnt;
   zULONG ulAttributeLth;
   zLONG  lTotalSize;
   zLONG  lLth = 0;
   zLONG  lRC;
   zSHORT nRC;

   if ( bUseParentSelectedEntities )
      MiGetParentEntityNameForView( szParentEntity, vResult, cpcEntityName );

   lEntityCnt = CountEntitiesForView( vResult, cpcEntityName );
   GetAttributeDisplayLength( &ulAttributeLth, vResult, cpcEntityName,
                              cpcAttributeName, cpcContext );
   lTotalSize = lEntityCnt * (zLONG) ulAttributeLth;  // a starting point
   DrAllocTaskMemory( (zCOREMEM) &pchMemory, lTotalSize + 1 );

   // For each entity, append the specified data to the list.
// nRC = SetCursorFirstEntity( vResult, cpcEntityName, cpcScope );
   nRC = SetEntityCursor( vResult, cpcEntityName, 0, zPOS_FIRST,
                          0, 0, 0, 0, cpcScope, 0 );
   while ( nRC > zCURSOR_UNCHANGED )
   {
      if ( bUseOnlySelectedEntities == 0 ||
           ((bUseOnlySelectedEntities) &&
            GetSelectStateOfEntity( vResult, cpcEntityName ) != 0) ||
           ((bUseParentSelectedEntities) &&
            GetSelectStateOfEntity( vResult, szParentEntity ) != 0) )
      {
         GetVariableFromAttribute( pchMemory + lLth, 0, zTYPE_STRING,
                                   lTotalSize - lLth - 1, vResult,
                                   cpcEntityName, cpcAttributeName,
                                   cpcContext,
                                   cpcContext && *cpcContext ?
                                      0: zUSE_DEFAULT_CONTEXT );
         lLth = zstrlen( pchMemory );
         while ( lLth > 0 && pchMemory[ lLth - 1 ] == ' ' )
         {
            lLth--;
            pchMemory[ lLth ] = 0;
         }
      }

   // nRC = SetCursorNextEntity( vResult, cpcEntityName, cpcScope );
      nRC = SetEntityCursor( vResult, cpcEntityName, 0, zPOS_NEXT,
                             0, 0, 0, 0, cpcScope, 0 );
      if ( nRC > zCURSOR_UNCHANGED )
      {
      // lLth = zstrlen( pchMemory );
         if ( lTotalSize - lLth < (zLONG) ulAttributeLth )
         {
            zPCHAR pchTemp;

            lEntityCnt *= 2;
            lTotalSize = lEntityCnt * (zLONG) ulAttributeLth;

            DrAllocTaskMemory( (zCOREMEM) &pchTemp, lTotalSize + 1 );
            zmemcpy( pchTemp, pchMemory, lLth );
            DrFreeTaskMemory( pchMemory );
            pchMemory = pchTemp;
         }

         if ( lLth > 0 && pchMemory[ lLth - 1 ] != ',' )
         {
            pchMemory[ lLth++ ] = ',';
            pchMemory[ lLth ] = 0;
         }
      }
   }

   if ( *cpcBlindCopyFlag == 'Y' )
   {
      // Email Addresses are to be put in Blind Copy parameter.
      TraceLineS( "Blind Copies: ", pchMemory );
      lRC = StartEmailClient( cpcBlindCopy, // Regular send parameter
                              cpcSubject,
                              cpcCopyTo,    // comma separated list
                              pchMemory,     // Blind Copy parameter
                              cpcBody,
                              cpcAttachment,
                              "",
                              lFlags );          // reserved
   }
   else
   {
      // Email Addresses are to be put in regular Send parameter.
      TraceLineS( "Regular Copies: ", pchMemory );
      lRC = StartEmailClient( pchMemory,  // comma separated list
                              cpcSubject,
                              cpcCopyTo,        // comma separated list
                              cpcBlindCopy,     // comma separated list
                              cpcBody,
                              cpcAttachment,
                              cpcEmailClient,
                              lFlags );          // reserved
   }


   DrFreeTaskMemory( (zPCHAR) pchMemory );
   return( lRC );
}

zOPER_EXPORT zLONG OPERATION
StartEmailClientForList( zVIEW   vResult,
                         zPCHAR  cpcEntityName,
                         zPCHAR  cpcAttributeName,
                         zPCHAR  cpcContext,
                         zPCHAR  cpcScope,
                         zSHORT  bUseOnlySelectedEntities,
                         zSHORT  bUseParentSelectedEntities,
                         zPCHAR  cpcSubject,
                         zPCHAR  cpcCopyTo,        // comma separated list
                         zPCHAR  cpcBlindCopy,     // comma separated list
                         zPCHAR  cpcBody,
                         zPCHAR  cpcAttachment,
                         zPCHAR  cpcEmailClient,
                         zLONG   lFlags )          // reserved
{
   zLONG  lRC;

   // Call reusable routine.
   lRC = StartEmailClientForListReus( vResult,
                                      cpcEntityName,
                                      cpcAttributeName,
                                      cpcContext,
                                      cpcScope,
                                      bUseOnlySelectedEntities,
                                      bUseParentSelectedEntities,
                                      cpcSubject,
                                      cpcCopyTo,
                                      cpcBlindCopy,
                                      cpcBody,
                                      cpcAttachment,
                                      cpcEmailClient,
                                      lFlags,
                                      "" );
   return( lRC );
}

zOPER_EXPORT zLONG OPERATION
StartBlindEmailClientForList( zVIEW   vResult,
                              zPCHAR  cpcEntityName,
                              zPCHAR  cpcAttributeName,
                              zPCHAR  cpcContext,
                              zPCHAR  cpcScope,
                              zSHORT  bUseOnlySelectedEntities,
                              zSHORT  bUseParentSelectedEntities,
                              zPCHAR  cpcSubject,
                              zPCHAR  cpcCopyTo,        // comma separated list
                              zPCHAR  cpcBlindCopy,     // comma separated list
                              zPCHAR  cpcBody,
                              zPCHAR  cpcAttachment,
                              zPCHAR  cpcEmailClient,
                              zLONG   lFlags )          // reserved
{
   zVIEW  mUser;
   zCHAR  szBlindEmailAddress[ 100 ];
   zLONG  lRC;

   // Get the Target Email Address from mUser, unless a BlindCopy is passed.
   if ( *cpcBlindCopy == 0 )
   {
      *szBlindEmailAddress = 0;
      GetViewByName( &mUser, "mUser", vResult, zLEVEL_APPLICATION );
      lRC = CheckExistenceOfEntity( mUser, "Employee" );
      if ( lRC >= 0 )
         GetStringFromAttribute( szBlindEmailAddress, mUser, "Employee", "eMailAddress" );
      if ( *szBlindEmailAddress == 0 )
      {
         lRC = CheckExistenceOfEntity( mUser, "Student" );
         if ( lRC >= 0 )
            GetStringFromAttribute( szBlindEmailAddress, mUser, "Student", "eMailAddress" );
         if ( *szBlindEmailAddress == 0 )
         {
            GetStringFromAttribute( szBlindEmailAddress, mUser, "Person", "eMailAddress" );
         }
      }
   }
   else
      zstrcpy( szBlindEmailAddress, cpcBlindCopy );

   // Call reusable routine.
   lRC = StartEmailClientForListReus( vResult,
                                      cpcEntityName,
                                      cpcAttributeName,
                                      cpcContext,
                                      cpcScope,
                                      bUseOnlySelectedEntities,
                                      bUseParentSelectedEntities,
                                      cpcSubject,
                                      cpcCopyTo,
                                      szBlindEmailAddress ,
                                      cpcBody,
                                      cpcAttachment,
                                      cpcEmailClient,
                                      lFlags,
                                      "Y" );
   return( lRC );
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: StartEmailClientWithFiles
//
//    Start Email Client passing in file names for body and attachment
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
StartEmailClientWithFiles( zVIEW  vAny,
                           zPCHAR szEmailAddress,
                           zPCHAR szSubjectLine,
                           zPCHAR szCopyToEmailAddress,
                           zPCHAR szBlindCopyEmailAddress,
                           zPCHAR szBodyFileName,
                           zPCHAR szAttachmentFileName,
                           zPCHAR szEmailClientOverride,
                           zLONG ulFlags )
{
   // Read the data from the Body and Attachment files into memory and call StartEmailClient
   // with those values.
   zPCHAR pchBodyMemoryStart;
   zPCHAR pchAttachmentMemoryStart;
   zLONG  selBodyMemory;
   zLONG  selAttachmentMemory;
   zLONG  lFileLth;

   // Read the Body into memory.
   lFileLth = ReadFileDataIntoMemory( vAny, szBodyFileName,
                                      &selBodyMemory, &pchBodyMemoryStart );
   // Exit if the file is empty or if there is an error opening it.
   if ( lFileLth <= 0 )
   {
      IssueError( vAny, 0, 0, "Can't open Email file." );
      return( -1 );
   }

   // If there is an attachment file, also read it into memory.
   // Then call StartEmailClientWithFiles with or without an attachment.
   if ( szAttachmentFileName[ 0 ] )
   {
      lFileLth = ReadFileDataIntoMemory( vAny, szAttachmentFileName,
                                         &selAttachmentMemory, &pchAttachmentMemoryStart );
      // Exit if the file is empty or if there is an error opening it.
      if ( lFileLth <= 0 )
         return( -1 );

      StartEmailClient( szEmailAddress,
                        szSubjectLine,
                        szCopyToEmailAddress,
                        szBlindCopyEmailAddress,
                        pchBodyMemoryStart,
                        pchAttachmentMemoryStart,
                        szEmailClientOverride,
                        0 );
      SysFreeMemory( selAttachmentMemory );
   // DrFreeTaskMemory( pchAttachmentMemoryStart );
   }
   else
   {
      StartEmailClient( szEmailAddress,
                        szSubjectLine,
                        szCopyToEmailAddress,
                        szBlindCopyEmailAddress,
                        pchBodyMemoryStart,
                        "",
                        szEmailClientOverride,
                        0 );
   }

   SysFreeMemory( selBodyMemory );
// DrFreeTaskMemory( pchBodyMemoryStart );
   return( 0 );

} // StartEmailClientWithFiles

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: IsEmailAddressValid
//
//    Validates an email address
//
/////////////////////////////////////////////////////////////////////////////
zBOOL
IsEmailAddressValid( zCPCHAR cpcEmailAddress )
{
   return( ValidateEmailAddressFormat( cpcEmailAddress ) ? TRUE : FALSE );

#if 0

   // Contains at least one character preceding the "@"
   // Contains a "@" following the preceding character(s)
   // Contains at least one character following the "@", followed
   // by a dot (.), followed by either a two character or three
   // character string (a two character country code or the standard
   // three character US code, such as com, edu etc)

   zPCHAR pchAt;
   zPCHAR pchDot;
   zPCHAR pch;

   if ( zstrchr( cpcEmailAddress, ' ' )  ||
        zstrchr( cpcEmailAddress, '\t' ) ||
        zstrchr( cpcEmailAddress, '\n' ) ||
        zstrchr( cpcEmailAddress, '\r' ) )
   {
      return( FALSE );
   }

   pchAt = zstrchr( cpcEmailAddress, '@' );
   if ( pchAt )
   {
      pchDot = zstrchr( pchAt, '.' );
      pchAt = zstrchr( pchAt + 1, '@' );
      if ( pchAt == 0 && pchDot )
      {
         pchDot = zstrrchr( pchDot, '.' );
         if ( pchDot[ 1 ] && pchDot[ 2 ] &&
              ((pchDot[ 3 ] == 0) ||
               (pchDot[ 3 ] && pchDot[ 4 ] == 0)) )
         {
            pch = (zPCHAR) cpcEmailAddress;
            while ( *pch )
            {
               if ( *pch > (char) 127 )
                  return( FALSE );

               pch++;
            }

            return( TRUE );
         }
      }
   }

   return( FALSE );

#endif
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: SendEmailForFiles
//
//    Start Email Client passing in file names for body and attachment
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT  OPERATION
SendEmailForFiles( zVIEW  ViewToWindow,
                   zVIEW  ResultSet,
                   zPCHAR pchSmtpServer,
                   zPCHAR pchRecipientEMailAddress,
                   zPCHAR pchSenderEMailAddress,
                   zPCHAR pchEMailUserName,
                   zPCHAR pchEMailPassword,
                   zPCHAR pchSubjectLine,
                   zPCHAR pchBodyFileName,
                   zPCHAR pchAltTextFileName,
                   zPCHAR pchEmbeddedImages,
                   zPCHAR pchAttachmentFileName,
                   zSHORT nMimeType,     // 1-Text, 2-HTML
                   zLONG  lConnection )
{
// zPCHAR pchBodyMemoryStart;
   char   szAtBodyFileName[ 260 ];
   char   szAtAltTextFileName[ 260 ];
// zLONG  selBodyMemory;
// zLONG  lFileLth;
   zVIEW  zqMDocOLST;
   zVIEW  wXferO;
   zSHORT nRC;

// TraceLine( "SendEmailForFiles Server: %s   Sender: %s   Recipient: %s"
//            "   Subject: %s   Mime Type: %d"
//            "   User: %s   Password %s",
//            pchSmtpServer, pchSenderEMailAddress, pchRecipientEMailAddress,
//            pchSubjectLine, nMimeType, pchEMailUserName, pchEMailPassword );

   // First make sure the email address is valid. If not exit with return code of 2.
   if ( IsEmailAddressValid( pchRecipientEMailAddress ) == FALSE )
      return( 2 );

   GetViewByName( &zqMDocOLST, "zqMDocOLST", ResultSet, zLEVEL_TASK );
   GetViewByName( &wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );

   if ( pchBodyFileName )
   {
      if ( pchBodyFileName[ 0 ] && pchBodyFileName[ 0 ] != '@' )
      {
         szAtBodyFileName[ 0 ] = '@';
         zstrcpy( szAtBodyFileName + 1, pchBodyFileName );
      }
      else
         zstrcpy( szAtBodyFileName, pchBodyFileName );
   }
   else
      szAtBodyFileName[ 0 ] = 0;

   if ( pchAltTextFileName )
   {
      if ( pchAltTextFileName[ 0 ] && pchAltTextFileName[ 0 ] != '@' )
      {
         szAtAltTextFileName[ 0 ] = '@';
         zstrcpy( szAtAltTextFileName + 1, pchAltTextFileName );
      }
      else
         zstrcpy( szAtAltTextFileName, pchAltTextFileName );
   }
   else
      szAtAltTextFileName[ 0 ] = 0;

   // Read the data from the Body and Attachment files into memory and call
   // StartEmailClient with those values.

   // Read the Body into memory.
// lFileLth = ReadFileDataIntoMemory( ResultSet, pchBodyFileName,
//                                    &selBodyMemory, &pchBodyMemoryStart );

   // Exit if the file is empty or if there is an error opening it.
// if ( lFileLth <= 0 )
// {
      // The memory allocated to hold the body has been freed.
//    IssueError( ResultSet, 0, 0, "Can't open Email file." );
//    return( -1 );
// }

   if ( pchSubjectLine == 0 || *pchSubjectLine == 0 )
      pchSubjectLine = " ";

// TraceLine( "SendEmailForFiles2 Server: %s   Sender: %s   Recipient: %s"
//            "   Subject: %s   Mime Type: %d"
//            "   User: %s   Password %s",
//            pchSmtpServer, pchSenderEMailAddress, pchRecipientEMailAddress,
//            pchSubjectLine, nMimeType, pchEMailUserName, pchEMailPassword );

   // If there is an attachment file, also read it into memory.
   // Then call CreateSeeMessage with or without an attachment.
   if ( *pchAttachmentFileName )
   {
      nRC = CreateSeeMessage( lConnection,
                              pchSmtpServer,
                              pchSenderEMailAddress,
                              pchRecipientEMailAddress,
                              "", "",
                              pchSubjectLine,
                              nMimeType,
                              szAtBodyFileName,
                              szAtAltTextFileName,
                              pchEmbeddedImages,
                              1,    // has attachment
                              pchAttachmentFileName,
                              pchEMailUserName,
                              pchEMailPassword );
   }
   else
   {
      nRC = CreateSeeMessage( lConnection,
                              pchSmtpServer,
                              pchSenderEMailAddress,
                              pchRecipientEMailAddress,
                              "", "",
                              pchSubjectLine,
                              nMimeType,
                              szAtBodyFileName,
                              szAtAltTextFileName,
                              pchEmbeddedImages,
                              0,    // no attachment
                              "",   // blank attachment file name
                              pchEMailUserName,
                              pchEMailPassword );
   }

// SysFreeMemory( selBodyMemory );
// DrFreeTaskMemory( pchBodyMemoryStart );

   return( nRC );

} // StartEmailClientWithFiles

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: ReturnSuffixOfFileName
//
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
ReturnSuffixOfFileName( zPCHAR szReturnedSuffix,
                        zPCHAR szFileName )
{
#if 1

   zPCHAR pchSuffix;

   szReturnedSuffix[ 0 ] = 0;  // initialize to empty extension
   pchSuffix = zstrrchr( szFileName, '.' );  // find last period
   if ( pchSuffix )  // if we found the last period ...
      zstrcpy( szReturnedSuffix, pchSuffix + 1 );  // ... we have our ext!

#else

   zPCHAR pchFileNameEnd;
   zPCHAR pchMaxBegin;

   pchFileNameEnd = szFileName + zstrlen( szFileName );
   pchMaxBegin = szFileName - 4;  // we could be pointing into space here!
   while ( *pchFileNameEnd != '.' && pchFileNameEnd != pchMaxBegin )
      pchFileNameEnd--;

   pchFileNameEnd++;
   zstrcpy( szReturnedSuffix, pchFileNameEnd );

#endif

   SysTranslateString( szReturnedSuffix, 'L' );  // force lower case
   return( 0 );
} // ReturnSuffixOfFileName


/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: WL_QC
//
//  PURPOSE:    This routine Converts an instance of a special character in
//              a buffer and then writes out the buffer. The character to
//              be translated is szTransChar and any instance of it is
//              converted to a double quote.
//
//  PARAMETERS: lFile - File handle
//              pchBuffer - the string to be converted.
//              pchTransChar - The character to be converted to a quote.
//              nAddBlankLineCnt - Number of blank lines to append.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
WL_QC( zVIEW  vAny,
       zLONG  lFile,
       zPCHAR pchBuffer,
       zPCHAR pchTransChar,
       zSHORT nBlankLineCnt )
{
   zPCHAR pch;
   zVIEW  pchTaskView = GetDefaultViewForActiveTask( );

// pch = zstrstr( pchBuffer, "DIRECTIONS FOR USE" );
// if ( pch )
//    TraceLineS( "Found DIRECTIONS FOR USE", "" );

   pch = zstrchr( pchBuffer, pchTransChar[ 0 ] );
   while ( pch )
   {
      *pch = '"';
      pch = zstrchr( pch + 1, pchTransChar[ 0 ] );
   }

   TraceLineS( "WL_QC --> ", pchBuffer );
   SysWriteLine( vAny, lFile, pchBuffer );
   while ( nBlankLineCnt-- > 0 )
      SysWriteLine( pchTaskView, lFile, "" );

   pchBuffer[ 0 ] = 0;  // null out line
   return( 0 );
}

zOPER_EXPORT zSHORT OPERATION
PositionOnEntityByZID( zVIEW   vLDD,
                       zPCHAR  pchZID )
{
   zCHAR  szZID[ 33 ];
   zPCHAR pch;
   zPCHAR pchDot;
   zSHORT nRC;

   if ( pchZID == 0 || pchZID[ 0 ] == 0 )
      return( 0 );

   zstrcpy( szZID, pchZID );
   pch = szZID;
   pchDot = zstrchr( pch, '.' );
   if ( pchDot )
      *pchDot = 0;

   nRC = SetCursorFirstEntityByString( vLDD, "MasterLabelContent", "ID", pch, 0 );
   if ( nRC == zCURSOR_SET )
   {
      if ( pchDot == 0 )
         return( 1 );  // found MasterLabelContent

      pch = pchDot + 1;
      pchDot = zstrchr( pch, '.' );
      if ( pchDot )
         *pchDot = 0;

      nRC = SetCursorFirstEntityByString( vLDD, "MasterLabelSection", "ID", pch, 0 );
      if ( nRC == zCURSOR_SET )
      {
         if ( pchDot == 0 )
            return( 2 );  // found MasterLabelSection

         pch = pchDot + 1;
         nRC = SetCursorFirstEntityByString( vLDD, "MasterLabelParagraph", "ID", pch, 0 );
         if ( nRC == zCURSOR_SET )
            return( 3 );  // found MasterLabelParagraph
      }
   }

   return( -1 );  // did not find proper entity
}

zSHORT
AddAttributeToCSV( zPCHAR pchBuffer, zVIEW lLibPers, zCPCHAR cpcEntityName,
                   zCPCHAR cpcAttribute, zBOOL bNumeric )
{
   zPCHAR pchQuote;
   zPCHAR pchEnd;
   zSHORT nLth;

// if ( bNumeric )
// {
      GetStringFromAttribute( pchBuffer + 1, lLibPers,
                              cpcEntityName, cpcAttribute );
// }
// else
// {
//    zPCHAR pchAttrib;
//
//    GetAddrForAttribute( &pchAttrib, lLibPers, cpcEntityName, cpcAttribute );
//    zstrcpy( pchBuffer, pchAttrib );
// }

   pchBuffer[ 0 ] = '"';
   if ( (pchQuote = zstrchr( pchBuffer + 1, '"' )) != 0 )
   {
      // Double any quotes.
      while ( pchQuote )
      {
         nLth = zstrlen( pchBuffer );
         pchEnd = pchBuffer + nLth;
         *(pchEnd + 1) = 0;
         while ( pchEnd > pchQuote )
         {
            *pchEnd = *(pchEnd - 1);
            pchEnd--;
         }

         pchQuote = zstrchr( pchQuote + 2, '"' );
      }
   }

   nLth = zstrlen( pchBuffer );
   pchBuffer[ nLth++ ] = '"';
   pchBuffer[ nLth++ ] = ',';
   return( nLth );
}

zSHORT
WriteCSV_RecordFromEntity( zVIEW lLibPers, zCPCHAR cpcEntity, zLONG lFile )
{
   char   szBuffer[ 500000 ];
   zSHORT nLth;

   szBuffer[ 0 ] = '"';
   szBuffer[ 1 ] = cpcEntity[ 0 ];  // S E P (Student Employee Prospect)
   szBuffer[ 2 ] = '"';
   szBuffer[ 3 ] = ',';
   nLth = 4;

   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                              "Status", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "CampusID", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "ID", TRUE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "LastName", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "FirstName", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "MiddleName", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "Suffix", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "PreferedFirstName", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "Gender", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "MaritalStatus", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "HomePhone", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "WorkPhone", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "Extension", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "eMailAddress", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Person",
                              "DateOfBirth", FALSE );
   if ( CheckExistenceOfEntity( lLibPers, "Address" ) == 0 )
   {
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Address",
                                 "Line1", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Address",
                                 "City", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Address",
                                 "StateProvince", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Address",
                                 "PostalCode", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "Address",
                                 "Country", FALSE );
   }
   else
   {
      zstrcpy( szBuffer + nLth, ",,,,," );
      nLth += 5;
   }

   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                              "ID", FALSE );
   nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                              "eMailAddress", FALSE );
   if ( cpcEntity[ 0 ] == 'S' )
   {
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                                 "CurrentLevel", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, "AdministrativeDivision",
                                 "Name", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                                 "ClearingHouseGradDate", FALSE );
   }
   else
   if ( cpcEntity[ 0 ] == 'P' )
   {
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                                 "ExpectedEntryTerm", FALSE );
      nLth += AddAttributeToCSV( szBuffer + nLth, lLibPers, cpcEntity,
                                 "ExpectedEntryYear", FALSE );
   }

   if ( nLth > 0 && szBuffer[ nLth - 1 ] == ',' )
      szBuffer[ nLth - 1 ] = 0;  // drop terminating ',' and null terminate
   else
      szBuffer[ nLth++ ] = 0;    // ensure null termination

   SysWriteLine( lLibPers, lFile, szBuffer );
   return( nLth );
}


///////////////////////////////////
//
zSHORT OPERATION
TraceLastError( DWORD dwError )
{
   zPCHAR lpMsgBuf;
   zCHAR  szBuffer[ 1000 ];
   zULONG ulLth = sizeof( szBuffer );

   FormatMessage( FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM |
                  FORMAT_MESSAGE_IGNORE_INSERTS, NULL, dwError,
                  MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
                  (LPTSTR) &lpMsgBuf, 0, NULL );

   if ( lpMsgBuf && *lpMsgBuf )
   {
      TraceLineI( "Win32 Error Message: ", dwError );
      TraceLineS( "Win32 Error Message: ", (zPCHAR) lpMsgBuf );
   }

   // Free the buffer.
   LocalFree( lpMsgBuf );

   //InternetGetLastResponseInfo( &dwError, szBuffer, &ulLth );
   if ( ulLth > 0 )
      TraceLineS( "Internet error: ", szBuffer );

   return 0;
}

zOPER_EXPORT zSHORT  OPERATION
FTPSendFile( zVIEW  vSubtask,
             zPCHAR pszServerAddress,
             zPCHAR pszUserName,
             zPCHAR pszPassword,
             zPCHAR pszLocalFileName,
             zPCHAR pszServerFileName,
             zLONG  lControl )
{
   zSHORT    nRC         = FALSE;
   HINTERNET hConnection = 0;
   HINTERNET hFtp        = 0;

   /*hConnection = InternetOpen( "ftp", INTERNET_OPEN_TYPE_PRECONFIG, 0, 0,
                               INTERNET_FLAG_ASYNC );
   if ( hConnection == 0 )
   {
      DWORD dwError = GetLastError();
      TraceLineS( "*ERROR*", "" );
      TraceLastError( dwError );
      TraceLineS( "Server Address   = ", pszServerAddress );
      TraceLineS( "User Name        = ", pszUserName );
      TraceLineS( "Local File Name  = ", pszLocalFileName );
      TraceLineS( "Server File Name = ", pszServerFileName );
      MessageSend ( vSubtask,
                    "AD0450",
                    "FTP",
                    "Error creating an internet connection.",
                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      goto EndOfFunction;
   }
   hFtp = InternetConnect( hConnection, pszServerAddress, INTERNET_DEFAULT_FTP_PORT,
                           pszUserName, pszPassword, INTERNET_SERVICE_FTP, 0, 0 );
   if ( hFtp == 0 )
   {
      DWORD dwError = GetLastError();
      TraceLineS( "*ERROR*", "" );
      TraceLastError( dwError );
      TraceLineS( "Server Address   = ", pszServerAddress );
      TraceLineS( "User Name        = ", pszUserName );
      MessageSend ( vSubtask,
                    "AD0451",
                    "FTP",
                    "Error connecting to ftp server.",
                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      goto EndOfFunction;
   }
   if ( !FtpPutFile( hFtp, pszLocalFileName, pszServerFileName,
                     FTP_TRANSFER_TYPE_BINARY, 0 ) )
   {
      DWORD dwError = GetLastError();
      TraceLineS( "*ERROR*", "" );
      TraceLastError( dwError );
      TraceLineS( "Server Address   = ", pszServerAddress );
      TraceLineS( "User Name        = ", pszUserName );
      TraceLineS( "Local File Name  = ", pszLocalFileName );
      TraceLineS( "Server File Name = ", pszServerFileName );
      MessageSend ( vSubtask,
                    "AD0452",
                    "FTP",
                    "Error sending file to the server.",
                    zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      goto EndOfFunction;
   }
   // If we get here then everything's ok, so set return value.
   nRC = TRUE;
EndOfFunction:
   if ( hConnection != 0 )
      //InternetCloseHandle( hConnection );
   if ( hFtp != 0 )
      //InternetCloseHandle( hFtp );*/

   return nRC;
}

/////////////////////////////////////////////////////////////////////////////
//    
// OPERATION: InsertUsageWordsIntoString
//    
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
InsertUsageWordsIntoString( zVIEW mSPLDef,
                            zPCHAR pchString, // original data and return data
                            zLONG  lMaxLth,
                            zPCHAR szUsageType,
                            zPCHAR szUsageKeyword,
                            zPCHAR szUsageEntityName,
                            zPCHAR szSeparatorCharacters )
{
   zVIEW  mSPLDefT;
   zCHAR  szCurrentType[ 2 ];
   zCHAR  szCurrentName[ 51 ];
   zPCHAR pchFromString;
   zPCHAR pchToString;
   zPCHAR pchToStringHold;
   zLONG  lMemHandle;
   zSHORT nUsageKeywordLth;
   zSHORT nSeparatorCharsLth;
   zSHORT nNameLth;
   zSHORT nCount;
   zSHORT nRC;

   // Insert Usage text into a position in szStringArea that is identified by a Usage Keyword.
   // The entries inserted will be separated by one or more characters as identified by the variable szSeparatorCharacters.
   // After determining the position of the insertion, we will loop through Usage entries, formatting each entry as we go.
   // Which entries we insert depend on the UsageType as follows:
   // A - Insert all Usage entries.
   // C - Insert only Claim Usage entries.
   // S - Insert only Surface Usage entries.
   // T - Insert only Application Type Usage entries.
   // U - Insert only Area of Use Usage entries.
   
   // Copy all characters up to the point of the keyword. If there is no keyword match, we simply copy all characters.
   // If we get a match on the keyword, insert the characters and finish copying the rest of the text.

   lMemHandle = SysAllocMemory( &pchToString, lMaxLth, 0, zCOREMEM_ALLOC, 0 );
   pchToStringHold = pchToString;
   nSeparatorCharsLth = zstrlen( szSeparatorCharacters );
   nUsageKeywordLth = zstrlen( szUsageKeyword );
   pchFromString = pchString;
   while ( *pchFromString )
   {
      // Compare the keyword to the characters in the string.
      if ( zstrncmp( pchFromString, szUsageKeyword, nUsageKeywordLth ) == 0 )
      {
         // There was a keyword match ... insert Usage entries.
         nCount = 0;
         nRC = SetCursorFirstEntity( mSPLDef, szUsageEntityName, "" );
         while ( nRC >= zCURSOR_SET )
         {
            GetVariableFromAttribute( szCurrentType, 0, zTYPE_STRING, 2, mSPLDef, szUsageEntityName, "Type", 0, 0 );
            GetVariableFromAttribute( szCurrentName, 0, zTYPE_STRING, 51, mSPLDef, szUsageEntityName, "Name", 0, 0 );
            
            // Insert this entry, if the Usage entry is the same Type or "All" is requested.
            if ( szUsageType[ 0 ] == 'A' || szUsageType[ 0 ] == szCurrentType[ 0 ] )
            {
               nNameLth = zstrlen( szCurrentName );
               // For any entry but the first or last, copy the separator characters.
               // For the first entry, don't add any characters at all.
               // For the last entry, add the characters "and".
               nCount++;
               CreateViewFromView( &mSPLDefT, mSPLDef );
               if ( szUsageType[ 0 ] == 'A' )
                  nRC = SetCursorNextEntity( mSPLDefT, szUsageEntityName, "" );
               else
                  nRC = SetCursorNextEntityByString( mSPLDefT, szUsageEntityName, "Type", szUsageType, "" );

               DropView( mSPLDefT );
               if ( nRC < zCURSOR_SET )
               {
                  zstrcpy( pchToString, " and " );
                  pchToString += 5;
               }
               else
               {
                  if ( nCount > 1 )
                  {
                     zstrcpy( pchToString, szSeparatorCharacters );
                     pchToString += nSeparatorCharsLth;
                  }
               }

               zstrcpy( pchToString, szCurrentName );
               nNameLth = zstrlen( szCurrentName );
               pchToString += nNameLth;
            }
            
            nRC = SetCursorNextEntity( mSPLDef, szUsageEntityName, "" );
         }

         pchFromString = pchFromString + nUsageKeywordLth;    // skip past keyword
      }
      else
      {
         // There was no keyword match, simply copy the character.
         *pchToString = *pchFromString;
         pchToString++;
         pchFromString++;
      }
   }

   zstrcpy( pchString, pchToStringHold );  // copy data back into original string
   SysFreeMemory( lMemHandle  );
   return( 0 );

} // InsertUsageWordsIntoString

/////////////////////////////////////////////////////////////////////////////
//    
// OPERATION: SeparateNumberedStatement
//
// Separate a numbered statement into the number and the rest of the
// statement. A return code of -1 means the text didn't start with a number.
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
SeparateNumberedStatement( zPCHAR pchOriginalStatement,
                           zLONG  lMaxLth,
                           zPCHAR pchNumberedText )
{
   zPCHAR pchRemainingText;
   zLONG  lMemHandle;
   zSHORT nCount;

   // Separate a numbered statement into the number and the rest of the statement. 
   // A return code of -1 means the text didn't start with a number.
   
   lMemHandle = SysAllocMemory( &pchRemainingText, lMaxLth, 0, zCOREMEM_ALLOC, 0 );
   pchRemainingText[ 0 ] = 0;
   if ( isdigit( *pchOriginalStatement ))
   {
      // The first character is a digit, so separate the non-blank chars from the rest.
      
      // First separate numbered text.
      nCount = 0;
      while ( *pchOriginalStatement != ' ' && *pchOriginalStatement != 0 )
      {
         // Make sure text doesn't go over 5 characters.
         nCount++;
         if ( nCount > 5 )
         {
            SysFreeMemory( lMemHandle  );
            return( -2 );
         }

         *pchNumberedText = *pchOriginalStatement;
         pchNumberedText++;
         pchOriginalStatement++;
      }

      *pchNumberedText = 0;
      
      // Skip next nonblank text.
      while ( *pchOriginalStatement == ' ' && *pchOriginalStatement != 0 )
         pchOriginalStatement++;
      
      // Copy rest of text.
      zstrcpy( pchRemainingText, pchOriginalStatement );
   }
   else
   {
      // The first character is not a digit, so return with RC -1.
      SysFreeMemory( lMemHandle  );
      return( -1 );
   }

   zstrcpy( pchOriginalStatement, pchRemainingText );  // copy data back into original string
   SysFreeMemory( lMemHandle  );
   return( 0 );

} // SeparateNumberedStatement

zOPER_EXPORT zSHORT OPERATION
fnListTasks( zVIEW vSubtask )
{
   LPANCHOR  AnchorBlock;
   LPTASK    lpTask = (LPTASK) SysGetTaskFromView( vSubtask );
   LPTASK    lpSearchTask;

   AnchorBlock = SysGetAnchorBlock( );

   // Make sure nobody is currently deleting a task.
// fnStartBrowseOfTaskList( lpTask, FALSE );

   for ( lpSearchTask = (LPTASK) zGETPTR( AnchorBlock->hFirstTask );
         lpSearchTask;
         lpSearchTask = (LPTASK) zGETPTR( lpSearchTask->hNextTask ) )
   {
      // Don't bother for the current task.
      if ( lpSearchTask == lpTask )
         continue;

      TraceLine( "ListTasks: 0x%08x", lpSearchTask );
   }

// fnEndBrowseOfTaskList( FALSE );

   return( 0 );
}

/////////////////////////////////////////////////////////////////////////////
//
// OPERATION: DetermineNextVersion
//
// Determine the next available version.  If the current version is 1.2 and
// version 1.3 exists, the next available version is 1.2.1, otherwise, the
// next available version is 1.3.
//
// Algorithm:  Match dots and sub-versions.  If second runs out of dots or
//   has a greater corresponding sub-version, simply increment the final
//   sub-version of the first version as the new version.  Otherwise, keep
//   adding the second's sub-version on to the end if the first and, if
//   necessary, add a new sub-version.
//
/////////////////////////////////////////////////////////////////////////////
zOPER_EXPORT zSHORT OPERATION
DetermineNextVersion( zPCHAR  pchVersionNew,
                      zVIEW   vListVersion,
                      zPCHAR  cpcListVersionEntity,
                      zPCHAR  cpcListVersionAttribute )
{
   // "SubregLabelContent", "Version", mSubProd, "SubregLabelContent", "Version" )
   zPCHAR pchDot1;
   zPCHAR pchDot2;
   zPCHAR pchDotNext1;
   zPCHAR pchDotNext2;
   zCHAR  szVersion[ 64 ];
   zCHAR  szVersionNext[ 64 ];
   zLONG  lVersion = 0;
   zLONG  lVersionNext = 0;
   zSHORT nDotCnt = 0;
   zSHORT nDotCntNext = 0;
   zSHORT nRC;

   CreateViewFromView( &vListVersion, vListVersion );
   GetStringFromAttribute( szVersion, vListVersion, cpcListVersionEntity, cpcListVersionAttribute );
   nRC = SetCursorNextEntity( vListVersion, cpcListVersionEntity, 0 );
   if ( nRC == zCURSOR_SET )
      GetStringFromAttribute( szVersionNext, vListVersion, cpcListVersionEntity, cpcListVersionAttribute );
   else
      szVersionNext[ 0 ] = 0;

   /* test stuff
   zstrcpy( szVersion, "1.2" );
   zstrcpy( szVersionNext, "1.3" );
   zstrcpy( szVersion, "1.2" );
   zstrcpy( szVersionNext, "1.2.1" );
   zstrcpy( szVersion, "1.2" );
   zstrcpy( szVersionNext, "2" );
   zstrcpy( szVersion, "1" );
   zstrcpy( szVersionNext, "" );
   end of test stuff */

   // Count the number of Dots and compare the sub-versions in the two versions and determine
   // where the versions quit matching up.
   pchDot1 = szVersion;
   pchDotNext1 = szVersionNext;
   do
   {
      pchDot2 = zstrchr( pchDot1, '.' );
      pchDotNext2 = zstrchr( pchDotNext1, '.' );
      if ( pchDot2 ) // still more versions for first
      {
         nDotCnt++;
         *pchDot2 = 0;
         lVersion = zatol( pchDot1 );
         *pchDot2 = '.';
         pchDot1 = pchDot2 + 1;
      }
      else
      {
         lVersion = zatol( pchDot1 );
         pchDot1 = 0;
      }

      if ( pchDotNext2 ) // still more versions for second
      {
         nDotCntNext++;
         *pchDotNext2 = 0;
         lVersionNext = zatol( pchDotNext1 );
         *pchDotNext2 = '.';
         pchDotNext1 = pchDotNext2 + 1;
      }
      else
      {
         lVersionNext = zatol( pchDotNext1 );
         pchDotNext1 = 0;
      }

      if ( (lVersionNext > lVersion + 1) ||
           (lVersionNext > lVersion && pchDot1 != 0 && pchDotNext1 == 0) ||
           (lVersionNext == 0) )
      {
         // Simply increment final sub-version of the first version.
         zstrcpy( pchVersionNew, szVersion );
         pchDot1 = zstrrchr( pchVersionNew, '.' );
         if ( pchDot1 )
            pchDot1++;
         else
            pchDot1 = pchVersionNew;

         lVersion = zatol( pchDot1 );
         lVersion++;
         zltoa( lVersion, pchDot1 );  // now pchVersionNew contains the next version
         break;  // we are done ... get out of loop
      }

      if ( pchDot1 == 0 || pchDotNext1 == 0 )
      {
         zstrcpy( pchVersionNew, szVersion );
         if ( pchDotNext1 != 0 )  // something in second version
         {
            zLONG lLth = zstrlen( pchVersionNew );
            lVersionNext = zatol( pchDotNext1 );
            pchVersionNew[ lLth++ ] = '.';
            zltoa( lVersionNext - 1, pchVersionNew + lLth );  // now pchVersionNew contains the next version
         }
         else
         {
            // On the last version for both ... just extend the next version
            zstrcat( pchVersionNew, ".5" );
         }

         break;  // we are done ... get out of loop
      }

   }  while ( pchDot1 );

   DropView( vListVersion );
   return( 0 );
}

#ifdef __cplusplus
}
#endif



