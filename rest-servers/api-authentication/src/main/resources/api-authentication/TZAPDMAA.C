#define  KZSYSSVC_INCL
#include "kzoengaa.h"

#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <ctype.h>

#define zGLOBAL_DATA
#include "tzapdmaa.hg"

#define  eDM_TEXT       1
#define  eDM_QSNAME     2
#define  __DO_MSGSEND__

int
fnHexToLong( zPCHAR  szHexString,
             zPLONG  lIn );

zSHORT
fnText( zSHORT       nID,
        zLONG        lEntryType,
        zCHAR        cType,
        zPVOID       lpData,
        zPCHAR       szContextName,
        zVIEW        zView,
        LPVIEWENTITY lpViewEntity,
        LPVIEWATTRIB lpViewAttribute,
        zUSHORT      uMaxStringLength );
//
// *******************************************************************
// * Copyright (c) 1993 QuinSoft Corporation. All rights reserved.   *
// * Confidential and Proprietary material subject to license -      *
// * do not reproduce or disclose. This material is an unpublished   *
// * work and is considered a trade secret belonging to the          *
// * copyright holder.                                               *
// *******************************************************************
//  AUTHOR:  John Price
//  DATE:    9/4/92
//  API:     TBA
//  ENVIRONMENT: ANY
//
// MODULE NAME:  TZAPDMAA  -  Application Domain library
//
// DESCRIPTION:  This is the source file which contains standard domains
//               that are supplied for the Zeidon product.
//
//
//./ ADD NAME=StandardDomainInterface
// Source Module=tzapdmaa.c
//***********************************************************************
//
// ENTRY:  StandardDomainInterface
//
//         This is a description of the Domain Interface supplied with
//         the Zeidon product.
//
//         A Domain is used for four purposes:
//         1. Validation - It validates any dat enterd through a window
//         or through a SetAttribute operation.
//         2. Presentation - It formats data when displaying it
//         on windows and reports and it accepts formatted information
//         from data entry on a window.
//         3. Data Conversion - It provides for the controlled
//         conversion of data from one valid Domain to another valid
//         Domain.
//         4. Data Comparison - It provides for the controlled
//         comparison of data from one valid Domain to another valid
//         Domain.
//
// OPERATIONS:   Each Domain entry should handle each of the possible
//               entry types.
// PARAMETERS:
//
// 1. lEntryType -
//       Indicates how to handle the other parameters.  Valid values are:
//       -  zDME_SET_ATTRIBUTE
//             Set Attribute, this entry is always used for setting an
//             attribute.  If the data type is string, it is probably the
//             normal data entry interface for validating string input.
//       -  zDME_GET_VARIABLE -
//             Get Variable, this entry is always used for setting a
//             variable from an attribute.  If the data type is string it
//             is probably the normal data presentation interface for
//             formatting data to a string.
//       -  zDME_COMPARE_ATTRIBUTE -
//             Compare Attribute, this entry will be called whenever an
//             attribute is compared to another attribute or variable.  The
//             Domain for the must include the code to handle the
//             comparison to source with any context defined for it.
//       -  zDME_GET_FIRST_TBL_ENT_FOR_ATTR
//       -  zDME_GET_NEXT_TBL_ENT_FOR_ATTR
//             These two entries are necessary to create the list of
//             entries for a Combo Box.  It could also be used by any
//             interface that wanted to list all the Domain values for a
//             given Context.
//       -  zDME_SET_ATTRIBUTE_VALUE_NEXT
//       -  zDME_SET_ATTRIBUTE_VALUE_PREV
//             These entries are necessary to handle a Spin Button.  It
//             would increment/decrement a numeric value or retrieve the
//             next or previous table entry for a given Context.
//       -  zDME_ADD_TO_ATTRIBUTE
//
//       -  zDME_GET_COMPARE_VALUE
//             Used by SetEntityCursor for performance.  SetEntityCursor
//             will ask for the internal attribute value for its input
//             qualifying value, and use the returned value in determining
//             if the entity instance matches the qualification request.
//             See Return Codes below.
//
//       -  zDME_VALIDATE_LPDATA
//             Go through validation procedures as if this was a
//             zDME_SET_ATTRIBUTE call, but do NOT set any attribute
//             values, just set the return code.
//
// 2. cType -
//       The Source/Target Data Type, identifies the type of data (of valid
//       Zeidon data types) that is the source in a Set call and the target
//       in a Get call.
// 3. lpData -
//       The Source/Target Data Pointer, points to the data that is the
//       source in a Set call and the target in a Get call.
// 4. szContextName    -
//       The Context Name to be applied to this Domain function.
// 5. zView -
//       Identifies the View that is the target in a Set call and the
//       source in a Get call.
// 6. lpViewEntity -
//       Points to the entity that is the target in a Set call and the
//       source in a Get call.
// 7. lpViewAttribute -
//       Points to the attribute that is the target in a Set call and the
//       source in a Get call.
// 8. nMaxStringLength -
//       For a Get call with data type 'string', it identifies the maximum
//       length of the string variable (should be used only with a
//       GetVariableFromAttribute operation).
//
// RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has been set
//
//  For zDME_GET_VARIABLE:
//              0 - Attribute value returned
//
//  For zDME_COMPARE_ATTRIBUTE:
//             -1 - Attribute value < lpData value
//              0 - Attribute value = lpData value
//              1 - Attribute value > lpData value
//
//  For zDME_GET_FIRST_TBL_ENT_FOR_ATTR:
//              0 - Value returned
//             -1 - No value returned, Context has no table entries
//
//  For zDME_GET_NEXT_TBL_ENT_FOR_ATTR:
//              0 - Value returned
//             -1 - No value returned, end of table has been reached
//
//  For zDME_SET_ATTRIBUTE_VALUE_NEXT:
//              0 - Attribute value has been set
//              1 - Attribute value is null
//
//
//  For zDME_SET_ATTRIBUTE_VALUE_PREV:
//             Same as zDME_SET_ATTRIBUTE_VALUE_NEXT.
//
//  For zDME_ADD_TO_ATTRIBUTE:
//
//  For zDME_GET_COMPARE_VALUE:
//             -2 - lpData ignored, this tells SetEntityCursor that
//                  it should invoked domain processing with the
//                  zDME_COMPARE_ATTRIBUTE entry type, each time
//                  that is determining if the EntityInstance
//                  matches the requested qualifications.
//             -1 - lpData invalid
//              0 - lpData unchanged, okay to use as is.
//              1 - lpData returned, string value
//              2 - lpData returned, integer value
//              3 - lpData returned, decimal value
//
//  For zDME_VALIDATE_LPDATA:
//              0 - Data is okay.
//
//  For all of the above:
//    zCALL_ERROR - error on call
//
//***********************************************************************
//./ END + 0

zSHORT
SendDomainError( zVIEW     zView,
                 LPDOMAIN  lpDomain,
                 zSHORT    nSeverity,
                 zLONG     lMessageID,
                 zLONG     lInfo,
                 zCPCHAR   szInfo1,
                 zCPCHAR   szInfo2 )
{
   zPCHAR      pszTitle;
   zLONG       lMsgType;
   zCHAR       szMsg[ 512 ];
   zCHAR       szMsgID[ 16 ];

   pszTitle = szlApplicationLogicError;
   if ( nSeverity >= 0 && nSeverity < 16 )
      lMsgType = zMSGQ_DOMAIN_ERROR;
   else
      lMsgType = zMSGQ_SYSTEM_ERROR;

   switch ( lMessageID )
   {
      case 1:
         zstrcpy ( szMsgID, "TZDME001" );
         zstrcpy ( szMsg, "Invalid Input Data Type" );
         break;

      case 2:
         zstrcpy ( szMsgID, "TZDME002" );
         zstrcpy ( szMsg, "Text String exceeds attribute length " );
         break;

      case 3:
         zstrcpy ( szMsgID, "TZDME003" );
         zstrcpy ( szMsg, "Attribute Type invalid for this Domain" );
         break;

      case 4:
         zstrcpy ( szMsgID, "TZDME004" );
         zstrcpy ( szMsg, "Invalid Domain Entry Type " );
         break;

      case 5:
         zstrcpy ( szMsgID, "TZDME005" );
         zstrcpy ( szMsg, "Table_Handler invalid for this Domain " );
         break;

      case 6:
         zstrcpy ( szMsgID, "TZDME006" );
         zstrcpy ( szMsg, "Integer overflow" );
         break;

      case 7:
         zstrcpy ( szMsgID, "TZDME007" );
         zstrcpy ( szMsg, "Integer underflow" );
         break;

      case 8:
         zstrcpy ( szMsgID, "TZDME008" );
         zstrcpy ( szMsg, "Could not find context for Domain " );
         break;

      case 9:
         zstrcpy ( szMsgID, "TZDME009" );
         zstrcpy ( szMsg, "Context edit string is invalid for Domain " );
         break;

      case 10:
         zstrcpy ( szMsgID, "TZDME010" );
         zstrcpy ( szMsg, "DateTime input string invalid " );
         break;

      case 11:
         zstrcpy ( szMsgID, "TZDME011" );
         zstrcpy ( szMsg, "Error storing value in record " );
         break;

      case 12:
         zstrcpy ( szMsgID, "TZDME012" );
         zstrcpy ( szMsg, "Context Required when cType is zTYPE_INTEGER " );
         break;

      case 13:
         zstrcpy ( szMsgID, "TZDME013" );
         zstrcpy ( szMsg, "Context/cType Combination is invalid " );
         break;

      case 14:
         zstrcpy ( szMsgID, "TZDME014" );
         zstrcpy ( szMsg, "Context is for retrieval only " );
         break;

      case 15:
         zstrcpy ( szMsgID, "TZDME015" );
         zstrcpy ( szMsg, "Context only used for arithmetic operations " );
         break;

      case 16:
         zstrcpy ( szMsgID, "TZDME016" );
         zstrcpy ( szMsg, "Input invalid for context " );
         break;

      case 17:
         zstrcpy ( szMsgID, "TZDME017" );
         zstrcpy ( szMsg, "Context Required when cType is zTYPE_DECIMAL " );
         break;

      case 18:
         zstrcpy ( szMsgID, "TZDME018" );
         zstrcpy ( szMsg, "Context edit string is null " );
         break;

      case 19:
         zstrcpy ( szMsgID, "TZDME019" );
         zstrcpy ( szMsg, "International number formatting is not available " );
         break;

      case 20:
         zstrcpy ( szMsgID, "TZDME020" );
         zstrcpy ( szMsg, "Invalid decimal string " );
         break;

      case 21:
         zstrcpy ( szMsgID, "TZDME021" );
         zstrcpy ( szMsg, "Return area not large enough for formatted string " );
         break;

      case 22:
         zstrcpy ( szMsgID, "TZDME022" );
         zstrcpy ( szMsg, "Only AlphaNumeric and '_' chars are allowed." );
         break;

      case 23:
         zstrcpy ( szMsgID, "TZDME023" );
         zstrcpy ( szMsg, "Value is out of valid range " );
         break;

      case 24:
         zstrcpy ( szMsgID, "TZDME024" );
         zstrcpy ( szMsg, "Invalid integer string " );
         break;

      default:
         zstrcpy( szMsg, "Unknown Error Message " );
   }

   return( MessageSend( zView, szMsgID, pszTitle, szMsg, lMsgType, 0 ) );
}

//./ ADD NAME=zdmQSName
// Source Module=tzapdmaa.c
//***********************************************************************
//
//  ENTRY:      zdmQSName
//
//  PURPOSE:    To retrieve a string representing an logical value in
//              Zeidon.  A QSName can contain only 'a'-'z', '0' - '9',
//              and '_'.
//
//  PARAMETERS: standard Domain Entry parameter list
//
//  RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has set
//    zCALL_ERROR - Error on call
//
//***********************************************************************
//./ END + 9
int OPERATION
zdmQSName( zLONG        lEntryType,
           zCHAR        cType,
           zPVOID       lpData,
           zPCHAR       szContextName,
           zVIEW        zView,
           LPVIEWENTITY lpViewEntity,
           LPVIEWATTRIB lpViewAttribute,
           zUSHORT      uMaxStringLength )
{
   return( fnText( eDM_QSNAME, lEntryType, cType, lpData,
                   szContextName, zView, lpViewEntity, lpViewAttribute,
                   uMaxStringLength ) );
}

//./ ADD NAME=zdmText
// Source Module=tzapdmaa.c
//***********************************************************************
//
//  ENTRY:      Text
//
//  PURPOSE:    To retrieve a string representing an attribute value
//
//  PARAMETERS: standard Domain Entry parameter list
//
//  RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has set
//    zCALL_ERROR - Error on call
//
//***********************************************************************
//./ END + 9
int OPERATION
zdmText( zLONG        lEntryType,
         zCHAR        cType,
         zPVOID       lpData,
         zPCHAR       szContextName,
         zVIEW        zView,
         LPVIEWENTITY lpViewEntity,
         LPVIEWATTRIB lpViewAttribute,
         zUSHORT      uMaxStringLength )
{
   return( fnText( eDM_TEXT, lEntryType, cType, lpData, szContextName,
                   zView, lpViewEntity, lpViewAttribute,
                   uMaxStringLength ) );

}

//./ ADD NAME=fnText
// Source Module=tzapdmaa.c
//***********************************************************************
//
//  ENTRY:      fnText
//
//  PURPOSE:    To retrieve a string representing an attribute value
//
//  PARAMETERS: standard Domain Entry parameter list
//
//  RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has set
//    zCALL_ERROR - Error on call
//
//***********************************************************************
//./ END + 9
zSHORT
fnText( zSHORT       nID,
        zLONG        lEntryType,
        zCHAR        cType,
        zPVOID       lpData,
        zPCHAR       szContextName,
        zVIEW        zView,
        LPVIEWENTITY lpViewEntity,
        LPVIEWATTRIB lpViewAttribute,
        zUSHORT      uMaxStringLength )
{
   zSHORT         nRC;
   zCHAR          szWorkString[ 256 ];
   zPCHAR         pszStringToStore;
   zPLONG         lplInteger;
   zLONG          lWork;
   zPDECIMAL      lpdDecimal;
   zDECIMAL       dWork;
   LPDOMAIN       lpDomain;

   nRC = zCALL_ERROR;         // Default to in error status...

   lpDomain = lpViewAttribute->lpDomain;

#if 0
   TraceLineS( "(apdm) ", "zdmText" );
   TraceLineI( "(apdm) Entry type is ", lEntryType );
   szWorkString[ 0 ] = cType;
   szWorkString[ 1 ] = (zCHAR) 0;
   TraceLineS( "(apdm) cType is ", szWorkString );
   TraceLineS( "(apdm) szContextName is ", szContextName );
   TraceLineS( "(apdm) EntityName is ", lpViewEntity->lpName );
   TraceLineS( "(apdm) AttributeName is ", lpViewAttribute->lpName );
#endif
   switch ( lEntryType )
   {
      case zDME_VALIDATE_LPDATA:
      case zDME_SET_ATTRIBUTE:
         if ( lpViewAttribute->cType == zTYPE_STRING )
         {
            szWorkString[ 0 ] = 0;

            // determine input data type
            switch ( cType )
            {
               case zTYPE_STRING:
                  // string to string
                  // if this is for the QSName domain, check for imbedded
                  // spaces as they are not allowed.
                  if ( nID == eDM_QSNAME )
                  {
                     zPCHAR   psz;

                     for ( psz = (zPCHAR) lpData; *psz; psz++ )
                     {
                        if ( !zisalnum( *psz ) && *psz != '_' )
                        {
                           // "TZDME022 - AlphaNumerica chars only are allowed"
                           SendDomainError( zView, lpDomain, 0, 22, 0,
                                            lpViewEntity->lpName,
                                            lpViewAttribute->lpName );
                           return( zCALL_ERROR );
                        }
                     }

                  } // end if ( nID == eDM_QSNAME )...

                  pszStringToStore = (zPCHAR) lpData;
                  if ( lpData == 0 )
                     pszStringToStore = szWorkString;

                  break;

               case zTYPE_INTEGER:
                  // convert long to a string
                  lplInteger = (zPLONG) lpData;
                  if ( *lplInteger != lNullInteger )
                     zltoa( *lplInteger, szWorkString );

                  pszStringToStore = szWorkString;
                  break;

               case zTYPE_DECIMAL:
                  // convert decimal to string
                  lpdDecimal = (zPDECIMAL) lpData;
                  if ( *lpdDecimal != dNullDecimal )
                     ConvertDecimalToString( szWorkString, lpViewAttribute,
                                             *lpdDecimal, szContextName );
                  pszStringToStore = szWorkString;
                  break;

               case zTYPE_DATETIME:
                  // convert DateTime to String
                  nRC = UfDateTimeToString( (LPDATETIME) lpData,
                                            szWorkString, 18 );
                  if ( nRC == zCALL_ERROR )
                     return( nRC );

                  pszStringToStore = szWorkString;
                  break;

               default:
                  szWorkString[ 0 ] = cType;
                  szWorkString[ 1 ] = 0;
                  zstrcat( szWorkString, ", " );
                  zstrcat( szWorkString, lpViewEntity->lpName );
                  // "TZDME001 - Invalid Input Data Type"
                  SendDomainError( zView, lpDomain, 8, 1, 0,
                                   szWorkString, lpViewAttribute->lpName );
                  return( zCALL_ERROR );
            }

            if ( zstrlen( pszStringToStore ) <= lpViewAttribute->uLth - 1 )
            {
               if ( lEntryType == zDME_SET_ATTRIBUTE )
                  nRC = StoreValueInRecord( zView, lpViewEntity,
                                            lpViewAttribute,
                                            (zPVOID) pszStringToStore, 0 );
               else
                  nRC = 0;
            }
            else
            {
               if ( lEntryType == zDME_SET_ATTRIBUTE )
                  nRC = StoreValueInRecord( zView, lpViewEntity,
                                            lpViewAttribute,
                                            (zPVOID) pszStringToStore, 0 );
               // "TZDME002 - Text String exceeds attribute length "
               SendDomainError( zView, lpDomain, 8, 2,
                                lpViewAttribute->uLth - 1,
                                lpViewEntity->lpName,
                                lpViewAttribute->lpName );
               return( zCALL_ERROR );
            }
         }
         else
         {
            szWorkString[ 0 ] = lpViewAttribute->cType;
            szWorkString[ 1 ] = 0;
            zstrcat( szWorkString, ", " );
            zstrcat( szWorkString, lpViewEntity->lpName );

            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             szWorkString, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         return( nRC );

      case zDME_GET_VARIABLE:
         if ( lpViewAttribute->cType == zTYPE_STRING )
         {
            zPCHAR   lpAttrAddr;
            zCHAR    cAttrType;
            zUSHORT  uAttrLength;

            if ( cType != zTYPE_PIC )
            {
               nRC = GetValueFromRecord( zView, lpViewEntity, lpViewAttribute,
                                         &lpAttrAddr, &cAttrType, &uAttrLength );
               if ( nRC )
                  return( zCALL_ERROR );
            }

            // determine input data type
            switch ( cType )
            {
               case zTYPE_STRING:
                  if ( (zPCHAR) lpAttrAddr == 0 ) //null string
                  {
                     *((zPCHAR) lpData) = 0;
                     nRC = -1;   // indicate attribute is null
                  }
                  else
                  {
                     // string to string
   //    Gig, zstrncpy NULLS for complete length!!!!
   //                  zstrcpy( (zPCHAR) lpData, (zPCHAR) lpAttrAddr );
   //                zstrncpy( (zPCHAR) lpData, (zPCHAR) lpAttrAddr,
   //                          uMaxStringLength );
   //                *((zPCHAR) lpData + uMaxStringLength - 1 ) = 0;

                     *((zPCHAR) lpData ) = 0;
                     zstrncat( (zPCHAR) lpData, (zPCHAR) lpAttrAddr,
                                uMaxStringLength );
                     nRC = 0;
                  }

                  break;

               case zTYPE_INTEGER:
                  // convert string to long
                  if ( (zPCHAR) lpAttrAddr == 0 ) //null string
                  {
                     *((zPLONG) lpData) = 0;
                     nRC = -1;   // indicate attribute is null
                  }
                  else
                  {
                     zstrcpy( szWorkString, (zPCHAR) lpAttrAddr );
                     *((zPLONG) lpData) = atol( szWorkString );
                     nRC = 0;
                  }

                  break;

               case zTYPE_DECIMAL:
                  // convert string to decimal
                  if ( (zPCHAR) lpAttrAddr == 0 ) //null string
                  {
                     *((zPDECIMAL) lpData) = 0.0;
                     nRC = -1;   // indicate attribute is null
                  }
                  else
                  {
                     zstrcpy( szWorkString, (zPCHAR) lpAttrAddr );
                     *((zPDECIMAL) lpData) = atof( szWorkString );
                     nRC = 0;
                  }

                  break;

               case zTYPE_PIC:
                  // return the picture string for the given context
                  {
                     zLPCONTEXT   lpContext;

                     if ( GetContext( &lpContext, lpDomain, szContextName ) )
                     {
                        if ( lpContext->lpEditString && *(lpContext->lpEditString) )
                           zstrcpy( (zPCHAR) lpData, lpContext->lpEditString );
                        else
                           *((zPCHAR) lpData) = 0;
                        nRC = 0;
                     }
                     else
                     {
                        // "TZDME008 - Could not find context for Domain "
                        SendDomainError( zView, lpDomain, 8, 8, 0,
                                         TrueName( szContextName, zSHOW_ZKEY ),
                                         lpViewAttribute->lpName );
                        nRC = zCALL_ERROR;
                     }
                  }

                  break;

               default:
                  szWorkString[ 0 ] = cType;
                  szWorkString[ 1 ] = 0;
                  zstrcat( szWorkString, ", " );
                  zstrcat( szWorkString, lpViewEntity->lpName );
                  // "TZDME001 - Invalid Input Data Type"
                  SendDomainError( zView, lpDomain, 8, 1,
                                   0, szWorkString, lpViewAttribute->lpName );
                  return( zCALL_ERROR );
            }

            nRC = 0;
         }
         else
         {
            szWorkString[ 0 ] = lpViewAttribute->cType;
            szWorkString[ 1 ] = 0;
            zstrcat( szWorkString, ", " );
            zstrcat( szWorkString, lpViewEntity->lpName );
            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             szWorkString, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         return( nRC );

      case zDME_ADD_TO_ATTRIBUTE:
         break;

      case zDME_COMPARE_ATTRIBUTE:
         if ( lpViewAttribute->cType == zTYPE_STRING )
         {
            zPCHAR   lpAttrAddr;
            zCHAR    cAttrType;
            zUSHORT  uAttrLength;

            nRC = GetValueFromRecord( zView, lpViewEntity, lpViewAttribute,
                                      &lpAttrAddr, &cAttrType, &uAttrLength );
            if ( nRC )
               return( zCALL_ERROR );

            if ( lpAttrAddr == 0 )
               lpAttrAddr = szNullS;

            // determine input data type
            switch ( cType )
            {
               case zTYPE_STRING:
                  if ( lpViewAttribute->bCaseSens )
                     nRC = zstrcmp( lpAttrAddr, (zPCHAR) lpData );
                  else
                     nRC = zstrcmpi( lpAttrAddr, (zPCHAR) lpData );

                  break;

               case zTYPE_INTEGER:
   //             plInteger = (zPLONG) lpVariable;
                  lWork = atol( lpAttrAddr );
                  lWork -= *((zPLONG) lpData);
                  nRC = (lWork == 0) ? 0 : (lWork < 0) ? -1 : 1;
                  break;

               case zTYPE_DECIMAL:
   //             pdDecimal = (zPDECIMAL) lpVariable;
                  dWork = (zDECIMAL) ( atof( lpAttrAddr ) );
                  dWork -= *((zPDECIMAL) lpData );
                  nRC = (dWork == 0) ? 0 : (dWork < 0) ? -1 : 1;
                  break;

               default:
                  szWorkString[ 0 ] = cType;
                  szWorkString[ 1 ] = 0;
                  zstrcat( szWorkString, ", " );
                  zstrcat( szWorkString, lpViewEntity->lpName );
                  // "TZDME001 - Invalid Input Data Type"
                  SendDomainError( zView, lpDomain, 8, 1, 0,
                                   szWorkString, lpViewAttribute->lpName );
                  return( zCALL_ERROR );
            }
         }
         else
         {
            szWorkString[ 0 ] = lpViewAttribute->cType;
            szWorkString[ 1 ] = 0;
            zstrcat( szWorkString, ", " );
            zstrcat( szWorkString, lpViewEntity->lpName );
            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             szWorkString, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         break;

      case zDME_GET_FIRST_TBL_ENT_FOR_ATTR:
         break;

      case zDME_GET_NEXT_TBL_ENT_FOR_ATTR:
         break;

      case zDME_SET_ATTRIBUTE_VALUE_NEXT:
         break;

      case zDME_SET_ATTRIBUTE_VALUE_PREV:
         break;

      case zDME_GET_COMPARE_VALUE:
         switch ( cType )
         {
            case zTYPE_STRING:
               nRC = 0;
               break;

            case zTYPE_INTEGER:
               zltoa( *((zPLONG) lpData), szWorkString );
               zstrcpy( (zPCHAR) lpData, szWorkString );
               nRC = 1; // indicate returning a string value
               break;

            case zTYPE_DECIMAL:
               ConvertDecimalToString( szWorkString, lpViewAttribute,
                                       *((zPDECIMAL) lpData), szContextName );
               zstrcpy( (zPCHAR) lpData, szWorkString );
               nRC = 1; // indicate returning a string value
               break;

            default:
               szWorkString[ 0 ] = cType;
               szWorkString[ 1 ] = 0;
               zstrcat( szWorkString, ", " );
               zstrcat( szWorkString, lpViewEntity->lpName );

               // "TZDME001 - Invalid Input Data Type"
               SendDomainError( zView, lpDomain, 8, 1, 0,
                                szWorkString, lpViewAttribute->lpName );
               nRC = -2;
         }

         break;

      default:
         // "TZDME004 - Invalid Domain Entry Type "
         SendDomainError( zView, lpDomain, 8, 4, lEntryType,
                          lpViewEntity->lpName, lpViewAttribute->lpName );
         return( zCALL_ERROR );
   }

   return( nRC );
}

//./ ADD NAME=Integer
// Source Module=tzapdmaa.c
//***********************************************************************
//
//  ENTRY:      Integer
//
//  PURPOSE:    To retrieve an integer representing an attribute value
//
//  PARAMETERS: standard Domain Entry parameter list
//
//  RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has been set
//    zCALL_ERROR - Error in call
//
//***********************************************************************
//./ END + 9
int OPERATION
zdmInteger( zLONG        lEntryType,
            zCHAR        cType,
            zPVOID       lpData,
            zPCHAR       szContextName,
            zVIEW        zView,
            LPVIEWENTITY lpViewEntity,
            LPVIEWATTRIB lpViewAttribute,
            zUSHORT      uMaxStringLength )
{
   zSHORT     nRC;
   zPLONG     lplLongToStore;
   zLONG      lLongWork;
   zCHAR      sz[ 256 ];
   LPDOMAIN   lpDomain;
   zPCHAR     psz;
   zSHORT     i, j, nStart;
   zLONG      range;
   zBOOL      bCompare;
   zLPCONTEXT lpContext;
   zSHORT     nPos, nPos2;
   zCHAR      cThouComma;
   zCHAR      szThou[ 10 ];
   zCHAR      szDecPt[ 10 ];
   zLONG      lPrecision;

   nRC = 0;      // Default to ok status.
   lpDomain = lpViewAttribute->lpDomain;

#if 0
   TraceLineS( "(apdm) ", "zdmInteger" );
   TraceLineI( "(apdm) Entry type is ", lEntryType );
   sz[ 0 ] = cType;
   sz[ 1 ] = 0;
   TraceLineS( "(apdm) cType is ", sz );
   TraceLineS( "(apdm) szContextName is ", szContextName );
   TraceLineS( "(apdm) EntityName is ", lpViewEntity->lpName );
   TraceLineS( "(apdm) AttributeName is ", lpViewAttribute->lpName );
#endif
   switch ( lEntryType )
   {
      case zDME_VALIDATE_LPDATA:
      case zDME_SET_ATTRIBUTE:
         if ( lpViewAttribute->cType == zTYPE_INTEGER )
         {
            // determine input data type
            switch ( cType )
            {
               case zTYPE_STRING:
                  if ( lpData == 0 || *((zPCHAR) lpData) == 0 )
                     lplLongToStore = &lNullInteger;
                  else
                  {
                     // Skip past any leading blanks.
                     while ( *( zPCHAR )lpData == ' ' )
                        lpData = (zPVOID) ( (zPCHAR) lpData + 1 );

                     // Convert string to long.  If the string has a minus
                     // sign or a plus sign at the beginning, we will copy it
                     // as is.  If the string has neither at the beginning, we
                     // will copy it with a plus sign.  This simplifies the
                     // validations below.
                     if ( *( (zPCHAR) lpData ) == '-' ||
                          *( (zPCHAR) lpData ) == '+' )
                     {
                        zstrcpy( sz, (zPCHAR) lpData );
                     }
                     else
                     {
                        sz[ 0 ] = '+';
                        zstrcpy( sz + 1, (zPCHAR) lpData );
                     }
                     if ( szContextName && zstrcmpi( szContextName, "hex" ) == 0 )
                     {
                        fnHexToLong( sz + 1, &lLongWork );
                     }
                     else
                     {
                        // We will first validate any commas that might be a
                        // part of the integer and remove them.  The first
                        // comma must be one of the first four characters.
                        // Each additional comma must be four positions from
                        // the last.
                        nRC = 0;
                        nPos = 1;
                        SysGetNumberFormat( szThou, szDecPt, &lPrecision );
                        cThouComma = szThou[ 0 ];
                        // Loop until end of string or until an error occurs.
                        while ( sz[ nPos ] != 0 && nRC == 0 )
                        {
                           if ( sz[ nPos ] == cThouComma )
                           {
                              if ( nPos > 4 )
                                 nRC = -1;
                              else
                              {
                                 // The first comma was ok, remove it and
                                 // check the rest.
                                 nPos2 = nPos;
                                 // Loop through each set of 4 characters, starting with
                                 // the first comma, and check that they are of the form,
                                 // ",XXX", where the X's are not null.  Note that we will
                                 // check that the X's are valid digits below.  Then shift
                                 // the digits but not the comma.
                                 while ( sz[ nPos ] != 0 && nRC == 0 )
                                 {
                                    if ( sz[ nPos ] != cThouComma ||
                                         sz[ nPos+1 ] == 0    ||
                                         sz[ nPos+2 ] == 0    ||
                                         sz[ nPos+3 ] == 0 )
                                    {
                                        nRC = -1;
                                    }
                                    else
                                    {
                                       sz[ nPos2 ] = sz[ nPos + 1 ];
                                       sz[ nPos2 + 1 ] = sz[ nPos + 2 ];
                                       sz[ nPos2 + 2 ] = sz[ nPos + 3 ];
                                       sz[ nPos2 + 3 ] = 0;
                                       nPos  = nPos  + 4;
                                       nPos2 = nPos2 + 3;
                                    }
                                 }
                              }
                           }
                           else
                              nPos++;
                        }

                        // Validate each character as a numeric digit, if things are ok to this
                        // point.
                        if ( nRC == 0 )
                        {
                           for ( nPos = 1; sz[ nPos ]; nPos++ )
                           {
                              if ( isdigit( sz[ nPos ] ) == 0 )
                                 nRC = -1;
                           }
                        }

                        // Validate that the number entered is not greater
                        // than 2,147,483,647 (max value of an integer).
                        if ( nPos > 11 )
                           nRC = -1;
                        else
                           if ( nPos == 11 )
                           {
                              if ( sz[ 0 ] > '2' )
                                 nRC = -1;
                              else
                                 if ( sz[ 1 ] == '2' )
                                    if ( sz[ 2 ] > '1' )
                                       nRC = -1;
                              else
                                 if ( sz[ 2 ] == '1' )
                                    if ( sz[ 3 ] > '4' )
                                       nRC = -1;
                              else
                                 if ( sz[ 3 ] == '4' )
                                    if ( sz[ 4 ] > '7' )
                                       nRC = -1;
                              else
                                 if ( sz[ 4 ] == '7' )
                                    if ( sz[ 5 ] > '4' )
                                       nRC = -1;
                              else
                                 if ( sz[ 5 ] == '4' )
                                    if ( sz[ 6 ] > '8' )
                                       nRC = -1;
                              else
                                 if ( sz[ 6 ] == '8' )
                                    if ( sz[ 7 ] > '3' )
                                       nRC = -1;
                              else
                                 if ( sz[ 7 ] == '3' )
                                    if ( sz[ 8 ] > '6' )
                                       nRC = -1;
                              else
                                 if ( sz[ 8 ] == '6' )
                                    if ( sz[ 9 ] > '4' )
                                       nRC = -1;
                              else
                                 if ( sz[ 9 ] == '4' )
                                    if ( sz[ 10 ] > '7' )
                                       nRC = -1;
                           }

                        // If an error has occurred, give error message and return.
                        if ( nRC != 0 )
                        {
                           zCHAR sz2[ 70 ];
                           zstrcpy( sz2, lpViewEntity->lpName );
                           zstrcat( sz2, ", " );
                           zstrcat( sz2, lpViewAttribute->lpName );

                           // "TZDME024 - Invalid integer string "
                           SendDomainError( zView, lpDomain, 0, 24, 0, sz, sz2 );
                           return( zCALL_ERROR );
                        }

                        lLongWork = atol( sz );
                     }

                     lplLongToStore = (zPLONG) &lLongWork;
                  }

                  break;

               case zTYPE_INTEGER:
                  // long to long
                  lplLongToStore = (zPLONG) lpData;
                  break;

               case zTYPE_DECIMAL:
                  // convert decimal to long
                  if ( *((zPDECIMAL) lpData) == dNullDecimal )
                     lplLongToStore = &lNullInteger;
                 else
                 {
                     if ( (zLONG) *((zPDECIMAL) lpData) > LONG_MAX )
                     {
                        //  "TZDME006 - Integer overflow"
                        SendDomainError( zView, lpDomain,
                                         8, 6, 0,
                                         lpViewEntity->lpName,
                                         lpViewAttribute->lpName );
                        return( zCALL_ERROR );
                     }
                     else
                     if ( (zLONG) *((zPDECIMAL) lpData) < LONG_MIN )
                     {
                        //  "TZDME007 - Integer underflow"
                        SendDomainError( zView, lpDomain,
                                         8, 7, 0,
                                         lpViewEntity->lpName,
                                         lpViewAttribute->lpName );
                        return( zCALL_ERROR );
                     }
                     else
                     {
                        lLongWork = (zLONG) *((zPDECIMAL) lpData);
                        lplLongToStore = (zPLONG) &lLongWork;
                     }
                  }


                  break;

               default:
                  sz[ 0 ] = cType;
                  sz[ 1 ] = 0;
                  zstrcat( sz, ", " );
                  zstrcat( sz, lpViewEntity->lpName );

                  // "TZDME001 - Invalid Input Data Type"
                  SendDomainError( zView, lpDomain, 8, 1,
                                   0, sz, lpViewAttribute->lpName );
                  return( zCALL_ERROR );
            }

            // Now perform any range checking to make sure the integer value is
            // within range.  The range value is in the EditString variable and
            // needs to be parsed.

            if ( GetContext( &lpContext, lpDomain, szContextName ) &&
                 lpContext->lpEditString )
            {
               zCHAR  szEditString[ 100 ];

               zstrcpy( szEditString, lpContext->lpEditString );

               // Loop for each range check in EditString.
               // At each pass of loop, copy the range check to sz.
               nStart = 0;
               for ( i = 0; szEditString[i] != ';' && szEditString[i] != 0; i++ )
               {
                  ;
               }

               for ( ;; ) // loop until colon is found
               {
                  zstrncpy( sz, szEditString + nStart, i - nStart );
                  sz[ i - nStart ] = '\0';

                  if ( sz[0] == '>' || sz[0] == '<' || sz[0] == '=' )
                  {
                     // First convert the range value to an integer.
                     // Stop at first nonblank after compare symbol.
                     for ( j = 0; sz[j+2] == ' '; j++ );

                     psz = sz + 2 + j;
                     range = atol( psz );

                     // Next perform the correct compare.

                     if ( sz[0] == '=' )
                        bCompare = ( *lplLongToStore == range );
                     else

                     if ( sz[0] == '>' && sz[1] == '=' )
                        bCompare = ( *lplLongToStore >=  range );
                     else

                     if ( sz[0] == '>' && sz[1] == ' ' )
                        bCompare = ( *lplLongToStore > range );
                     else

                     if ( sz[0] == '<' && sz[1] == ' ' )
                        bCompare = ( *lplLongToStore <  range );
                     else

                     if ( sz[0] == '<' && sz[1] == '=' )
                        bCompare = ( *lplLongToStore <= range );

                     if ( bCompare == 0 )
                     {
                        *sz = cType;
                        *(sz + 1) = 0;
                        zstrcat( sz, ", " );
                        zstrcat( sz, lpViewEntity->lpName );

                        // "TZDME023 - Value out of valid range"
                        SendDomainError( zView, lpDomain, 8, 23,
                                       0, sz, lpViewAttribute->lpName );
                        return( zCALL_ERROR );
                     }
                  }

                  if ( szEditString[i] == ';' )
                  {
                     // Find first nonblank character after the colon.
                     for ( i++; szEditString[i] == ' '; i++ );
                     nStart = i;
                     // Find end of next range value.
                     for ( i = nStart; szEditString[i] != ';' && szEditString[i] != 0; i++ )
                     {
                        ;
                     }
                  }
                  else
                     break;

               } // End of EditString loop
            }

            if ( lEntryType == zDME_SET_ATTRIBUTE )
               nRC = StoreValueInRecord( zView, lpViewEntity,
                                         lpViewAttribute,
                                         (zPVOID) lplLongToStore, 0 );
         }
         else
         {
            sz[ 0 ] = lpViewAttribute->cType;
            sz[ 1 ] = 0;
            zstrcat( sz, ", " );
            zstrcat( sz, lpViewEntity->lpName );
            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             sz, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         return( nRC );

      case zDME_GET_VARIABLE:
         if ( lpViewAttribute->cType == zTYPE_INTEGER )
         {
            zPCHAR   lpAttrValue;
            zCHAR    cAttrType;
            zUSHORT  uAttrLength;

            if ( cType != zTYPE_PIC )
            {
               nRC = GetValueFromRecord( zView, lpViewEntity, lpViewAttribute,
                                         &lpAttrValue, &cAttrType,
                                         &uAttrLength );
               if ( nRC )
                  return( zCALL_ERROR );

               // if there is no lpRecord, lpAttrValue returns a null...
               if ( lpAttrValue == 0 )
                  lpAttrValue = (zPCHAR) &lNullInteger;
            }

            // determine output data type
            switch ( cType )
            {
               case zTYPE_STRING:
                  // long to string
                  if ( *((zPLONG) lpAttrValue) == lNullInteger )
                  {
                     *((zPCHAR) lpData) = 0;
                     nRC = -1;   // indicate attribute is null
                  }
                  else
                  {
                     zLONG lLocal;
                     lLocal = *((zPLONG) lpAttrValue);
                     if ( szContextName && zstrcmpi( szContextName, "hex" ) == 0 )
                     {
                        zltox( lLocal, sz );
                     }
                     else
                     {
                        zltoa( lLocal, sz );
                     }

                     // If the Context requests the inclusion of the Thousand
                     // character, add it.
                     if ( szContextName && zstrcmpi( szContextName, "INTEGER_THOUSAND" ) == 0 )
                     {
                        SysGetNumberFormat( szThou, szDecPt, &lPrecision );
                        cThouComma = szThou[ 0 ];
                        // Set psz to beginning of first digit that is not
                        // a minus sign.
                        if ( sz[ 0 ] == '-' )
                           psz = sz + 1;
                        else
                           psz = sz;
                        nPos = zstrlen( psz );
                        if ( nPos > 9 )
                           j = 3;
                        else
                        if ( nPos > 6 )
                           j = 2;
                        else
                        if ( nPos > 3 )
                           j = 1;
                        else
                           j = 0;
                        *( psz + nPos + j ) = *( psz + nPos );
                        while ( j > 0 )
                        {
                           // sz[ nPos + j - 1 ] = sz[ nPos - 1 ]; etc.
                           *( psz + nPos + j - 1 ) = *( psz + nPos - 1 );
                           *( psz + nPos + j - 2 ) = *( psz + nPos - 2 );
                           *( psz + nPos + j - 3 ) = *( psz + nPos - 3 );
                           *( psz + nPos + j - 4 ) = cThouComma;
                           nPos = nPos - 3;
                           j--;
                        }
                     }

                     // Store the result and return.
                     zstrcpy( (zPCHAR) lpData, sz );
                     nRC = 0;
                  }

                  break;

               case zTYPE_INTEGER:
                  // long to long
                  if ( *((zPLONG) lpAttrValue) == lNullInteger )
                  {
                     *((zPLONG) lpData) = 0;
                     nRC = -1;   // indicate attribute is null
                  }
                  else
                  {
                     *((zPLONG) lpData) = *((zPLONG) lpAttrValue);
                     nRC = 0;
                  }

                  break;

               case zTYPE_DECIMAL:
                  // long to decimal
                  if ( *((zPLONG) lpAttrValue) == lNullInteger )
                  {
                     *((zPDECIMAL) lpData) = 0.0;
                     nRC = -1;   // indicate attribute is null
                  }
                  else
                  {
                     *((zPDECIMAL) lpData) = (zDECIMAL) *((zPLONG) lpAttrValue);
                     nRC = 0;
                  }

                  break;

               case zTYPE_PIC:
                  // return the picture string for the given context
                  {
                     zLPCONTEXT   lpContext;

                     if ( GetContext( &lpContext, lpDomain, szContextName ) )
                     {
                        if ( lpContext->lpEditString && *(lpContext->lpEditString) )
                           zstrcpy( (zPCHAR) lpData, lpContext->lpEditString );
                        else
                           *((zPCHAR) lpData) = 0;
                        nRC = 0;
                     }
                     else
                     {
                        // "TZDME008 - Could not find context for Domain "
                        SendDomainError( zView, lpDomain, 8, 8, 0,
                                         TrueName( szContextName, zSHOW_ZKEY ),
                                         lpViewAttribute->lpName );
                        nRC = zCALL_ERROR;
                     }
                  }

                  break;

               default:
                  sz[ 0 ] = cType;
                  sz[ 1 ] = 0;
                  zstrcat( sz, ", " );
                  zstrcat( sz, lpViewEntity->lpName );

                  // "TZDME001 - Invalid Input Data Type"
                  SendDomainError( zView, lpDomain, 8, 1,
                                   0, sz, lpViewAttribute->lpName );
                  return( zCALL_ERROR );
            }
         }
         else
         {
            sz[ 0 ] = lpViewAttribute->cType;
            sz[ 1 ] = 0;
            zstrcat( sz, ", " );
            zstrcat( sz, lpViewEntity->lpName );
            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             sz, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         return( nRC );

      case zDME_ADD_TO_ATTRIBUTE:
         if ( lpViewAttribute->cType == zTYPE_INTEGER )
         {
            zPCHAR   lpAttrValue;
            zCHAR    cAttrType;
            zUSHORT  uAttrLength;
            zLONG    lWork;
            zDECIMAL  dWork1;

            // Only allow string, integer or decimal types to be added.
            // If input is null, return immediately.
            switch ( cType )
            {
               case zTYPE_STRING:
                  if ( lpData == 0 || *((zPCHAR) lpData) == 0 )
                     return( 0 );

                  break;

               case zTYPE_INTEGER:
                  if ( *((zPLONG) lpData) == lNullInteger ||
                       *((zPLONG) lpData) == 0L )
                  {
                     return( 0 );
                  }

                  break;

               case zTYPE_DECIMAL:
                  if ( *((zPDECIMAL) lpData) == dNullDecimal ||
                       *((zPDECIMAL) lpData) == 0 )
                  {
                     return( 0 );
                  }

                  break;

               default:
                  return( zCALL_ERROR );
            }

            nRC = GetValueFromRecord( zView, lpViewEntity, lpViewAttribute,
                                      &lpAttrValue, &cAttrType, &uAttrLength );
            if ( nRC )
               return( zCALL_ERROR );

            // if there is no lpRecord, lpAttrValue returns a null...
            if ( lpAttrValue == 0 || *((zPLONG) lpAttrValue) == lNullInteger )
               dWork1 = 0;
            else
               dWork1 = *((zPLONG) lpAttrValue);

            // determine input data type
            switch ( cType )
            {
               case zTYPE_STRING:
                  // string to long
                  zstrcpy( sz, (zPCHAR) lpData );
                  dWork1 += atof( sz );
                  break;

               case zTYPE_INTEGER:
                  // long to long
                  dWork1 += *((zPLONG) lpData);
                  break;

               case zTYPE_DECIMAL:
                  // decimal to long
                  // convert decimal to long
                  dWork1 += *((zPDECIMAL) lpData);
                  if ( dWork1 > LONG_MAX )
                  {
                     //  "TZDME006 - Integer overflow"
                     SendDomainError( zView, lpDomain,
                                      8, 6, 0,
                                      lpViewEntity->lpName,
                                      lpViewAttribute->lpName );
                     return( zCALL_ERROR );
                  }
                  else
                  if ( dWork1 < LONG_MIN )
                  {
                     //  "TZDME007 - Integer underflow"
                     SendDomainError( zView, lpDomain,
                                      8, 7, 0,
                                      lpViewEntity->lpName,
                                      lpViewAttribute->lpName );
                     return( zCALL_ERROR );
                  }

                  break;

               default:
                  sz[ 0 ] = cType;
                  sz[ 1 ] = 0;
                  zstrcat( sz, ", " );
                  zstrcat( sz, lpViewEntity->lpName );
                  // "TZDME001 - Invalid Input Data Type"
                  SendDomainError( zView, lpDomain, 8, 1,
                                   0, sz, lpViewAttribute->lpName );
                  return( zCALL_ERROR );
            }

            lWork = (zLONG) dWork1;
            lplLongToStore = &lWork;
            nRC = StoreValueInRecord( zView, lpViewEntity,
                                      lpViewAttribute,
                                      (zPVOID) lplLongToStore, 0 );
         }
         else
         {
            sz[ 0 ] = lpViewAttribute->cType;
            sz[ 1 ] = 0;
            zstrcat( sz, ", " );
            zstrcat( sz, lpViewEntity->lpName );
            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             sz, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         return( nRC );

      case zDME_COMPARE_ATTRIBUTE:
         if ( lpViewAttribute->cType == zTYPE_INTEGER )
         {
            zPCHAR   lpAttrAddr;
            zCHAR    cAttrType;
            zUSHORT  uAttrLength;
            zLONG    lAttrWork;

            nRC = GetValueFromRecord( zView, lpViewEntity, lpViewAttribute,
                                      &lpAttrAddr, &cAttrType, &uAttrLength );
            if ( nRC )
               return( zCALL_ERROR );

            if ( lpAttrAddr == 0 || *((zPLONG) lpAttrAddr ) == lNullInteger )
               lAttrWork = 0;
            else
               lAttrWork = *((zPLONG) lpAttrAddr );

            lLongWork = 0;    // set in case lpData input is null
            // determine input data type
            switch ( cType )
            {
             case zTYPE_STRING:
               if ( lpData && *((zPCHAR) lpData ) )
                  lLongWork = atol( (zPCHAR) lpData );
               break;

             case zTYPE_INTEGER:
               // long to long
               if ( *((zPLONG) lpData) != lNullInteger )
                  lLongWork = *((zPLONG) lpData );
               break;

             case zTYPE_DECIMAL:
               // convert decimal to long
               if ( *((zPDECIMAL) lpData) != dNullDecimal )
               {
                  if ( (zLONG) *((zPDECIMAL) lpData) > LONG_MAX )
                  {
                     //  "TZDME006 - Integer overflow"
                     SendDomainError( zView, lpViewAttribute->lpDomain,
                                      8, 6, 0,
                                      lpViewEntity->lpName,
                                      lpViewAttribute->lpName );
                     return( zCALL_ERROR );
                  }
                  else
                  if ( (zLONG) *((zPDECIMAL) lpData) < LONG_MIN )
                  {
                     //  "TZDME007 - Integer underflow"
                     SendDomainError( zView, lpViewAttribute->lpDomain,
                                      8, 7, 0,
                                      lpViewEntity->lpName,
                                      lpViewAttribute->lpName );
                     return( zCALL_ERROR );
                  }
                  else
                  {
                     lLongWork = (zLONG) *((zPDECIMAL) lpData);
                  }
               }
               break;

             default:
               sz[ 0 ] = cType;
               sz[ 1 ] = 0;
               zstrcat( sz, ", " );
               zstrcat( sz, lpViewEntity->lpName );
               // "TZDME001 - Invalid Input Data Type"
               SendDomainError( zView, lpDomain, 8, 1,
                                0, sz, lpViewAttribute->lpName );
               return( zCALL_ERROR );
            }

            lAttrWork -= lLongWork;
            nRC = ( lAttrWork == 0 ) ? 0 : ( lAttrWork < 0 ) ? -1 : 1;
         }
         else
         {
            sz[ 0 ] = lpViewAttribute->cType;
            sz[ 1 ] = 0;
            zstrcat( sz, ", " );
            zstrcat( sz, lpViewEntity->lpName );
            // "TZDME003 - Attribute Type invalid for this Domain"
            SendDomainError( zView, lpDomain, 8, 3, 0,
                             sz, lpViewAttribute->lpName );
            return( zCALL_ERROR );
         }

         break;

      case zDME_GET_FIRST_TBL_ENT_FOR_ATTR:
         break;

      case zDME_GET_NEXT_TBL_ENT_FOR_ATTR:
         break;

      case zDME_SET_ATTRIBUTE_VALUE_NEXT:
         break;

      case zDME_SET_ATTRIBUTE_VALUE_PREV:
         break;

      case zDME_GET_COMPARE_VALUE:
         // determine input data type
         switch ( cType )
         {
            case zTYPE_STRING:
               if ( lpData && *((zPCHAR) lpData ) )
               {
                  lLongWork = atol( (zPCHAR) lpData );
                  *((zPLONG) lpData) = lLongWork;
                  nRC = 2; // indicate we're returning an integer
               }

               break;

            case zTYPE_INTEGER:
               nRC = 0;
               break;

            case zTYPE_DECIMAL:
               // convert decimal to long
               if ( *((zPDECIMAL) lpData) != dNullDecimal )
               {
                  if ( (zLONG) *((zPDECIMAL) lpData) > LONG_MAX )
                  {
                     //  "TZDME006 - Integer overflow"
                     SendDomainError( zView, lpDomain,
                                      8, 6, 0,
                                      lpViewEntity->lpName,
                                      lpViewAttribute->lpName );
                     nRC = -2;
                  }
                  else
                  if ( (zLONG) *((zPDECIMAL) lpData) < LONG_MIN )
                  {
                     //  "TZDME007 - Integer underflow"
                     SendDomainError( zView, lpDomain,
                                      8, 7, 0,
                                      lpViewEntity->lpName,
                                      lpViewAttribute->lpName );
                     nRC = -2;
                  }
                  else
                  {
                     lLongWork = (zLONG) *((zPDECIMAL) lpData);
                     *((zPLONG) lpData) = lLongWork;
                     nRC = 2;
                  }
               }

               break;

            default:
               sz[ 0 ] = cType;
               sz[ 1 ] = 0;
               zstrcat( sz, ", " );
               zstrcat( sz, lpViewEntity->lpName );

               // "TZDME001 - Invalid Input Data Type"
               SendDomainError( zView, lpDomain, 8, 1,
                                0, sz, lpViewAttribute->lpName );
               nRC = -2;
         }

         break;

      default:
         // "TZDME004 - Invalid Domain Entry Type "
         SendDomainError( zView, lpDomain, 8, 4, lEntryType,
                          lpViewEntity->lpName, lpViewAttribute->lpName );
         return( zCALL_ERROR );
   }

   return( nRC );
}


// Added by Phil
int
fnHexToLong( zPCHAR  szHexString,
             zPLONG  lIn )
{
   zLONG  lWk;
   zCHAR  szWk[9];
   zPCHAR p;
   unsigned char c;
   int i, ii;

   lWk = 0;
   zstrcpy( szWk, "00000000" );
   ii = 8 - zstrlen( szHexString );
   if ( ii < 0 ) ii = 0;
   zstrcpy( &szWk[ ii ], szHexString );


   p = (zPCHAR) &lWk;
   p = p + 3;
   for ( i = 0; i < 7; )
   {
      c = szWk[ i++ ];
      if ( c >= 97 )  // lower case 'a'
         c = c -87;
      else
      if ( c >= 65 )  // upper case 'A'
         c = c -55;
      else
         c = c -48;  // character '0'
      if ( c > 15 ) c = 0;
      *p = c << 4;      // shift c because p is a 'signed' char
      c = szWk[ i++ ];
      if ( c >= 97 )  // lower case 'a'
         c = c -87;
      else
      if ( c >= 65 )  // upper case 'A'
         c = c -55;
      else
         c = c -48;  // character '0'

      if ( c > 15 )
         c = 0;

      *p = c | *p;
      p--;
   }

   *lIn = lWk;
   return( 0 );
}

zSHORT OPERATION
fnDyamicT_fnLocalBuildQual_0( zVIEW     vSubtask,
                              zPVIEW    vQualObject,
                              zPCHAR    ContextName ) ;

//./ ADD NAME=DynamicTable
// Source Module=tzapdmaa.c
//***********************************************************************
//
//  ENTRY:      DynamicTable
//
//  PURPOSE:    To process a Dynamic Table Domain
//
//  PARAMETERS: standard Domain Entry parameter list
//
//  RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has set
//    zCALL_ERROR - Error on call
//
//***********************************************************************
//./ END + 9
zSHORT OPERATION
zdmDynamicTable( zLONG        lEntryType,
                 zCHAR        cType,
                 zPVOID       lpData,
                 zPCHAR       szContextName,
                 zVIEW        zView,
                 LPVIEWENTITY lpViewEntity,
                 LPVIEWATTRIB lpViewAttribute,
                 zUSHORT      uMaxStrLth )

{
   zVIEW     vDomainTable;
   zSHORT    nRC;
   zCHAR     szSavedTableName[ 36 ];
   zCHAR     szStringAttributeValue[ 65 ];
   zLONG     lIntegerAttributeValue;
   zVIEW     vTempViewVar_0;
   LPDOMAIN  lpDomain;
   zPCHAR    lpInternalValue;
   zCHAR     cAttrType;
   zUSHORT   uAttrLength;

   // Activate the Dynamic Table and name it, if it does not currently
   // exist in memory with a name of "X_" + Domain Name.
   zstrcpy( szSavedTableName, "X_" );
   zstrcat( szSavedTableName, szContextName );
   nRC = GetViewByName( &vDomainTable, szSavedTableName, 0, zLEVEL_TASK );
   if ( nRC < 0 )
   {
      fnDyamicT_fnLocalBuildQual_0( zView, &vTempViewVar_0, szContextName );
      nRC = ActivateObjectInstance( &vDomainTable, "DOMAINT", zView,
                                    vTempViewVar_0, zSINGLE );
      if ( nRC >= 0 )
      {
         SetNameForView( vDomainTable, szSavedTableName, 0, zLEVEL_TASK );
      }
      else
      {
         MessageSend( zView, "TZDMD002", "Data Validation",
                      "Validation table cannot be loaded.", zMSGQ_DOMAIN_ERROR, 0 );
         return zCALL_ERROR;
      }

   }

   nRC = 0;
   lpDomain = lpViewAttribute->lpDomain;
   switch( lEntryType )
   {
      case zDME_SET_ATTRIBUTE :

         // For String Values.
         nRC = SetCursorFirstEntityByString( vDomainTable,
                                             "DomainValue",
                                             "ExternalDescription",
                                             ( zPCHAR )lpData, "" );
         if ( nRC >= zCURSOR_SET )
         {
            if ( lpDomain->cType == 'S' )
            {
               GetStringFromAttribute( szStringAttributeValue,
                                       vDomainTable, "DomainValue", "InternalStringValue" );
               StoreValueInRecord( zView,
                                   lpViewEntity,
                                   lpViewAttribute,
                                   (zPVOID) szStringAttributeValue, 0 );
            }
            if ( lpDomain->cType == 'L' )
            {
               GetIntegerFromAttribute( &lIntegerAttributeValue,
                                       vDomainTable, "DomainValue", "InternalIntegerValue" );
               StoreValueInRecord( zView,
                                   lpViewEntity,
                                   lpViewAttribute,
                                   (zPVOID) &lIntegerAttributeValue, 0 );
            }
         }
         else
         {
            nRC = MessageSend( zView, "TZDMD003", "Data Validation",
                               "Invalid input value", zMSGQ_DOMAIN_ERROR, 0 );
            return zCALL_ERROR;
         }


         break  ;
      case zDME_GET_VARIABLE :

         if ( lpDomain->cType == 'S' )
         {
            GetValueFromRecord( zView,
                                lpViewEntity,
                                lpViewAttribute,
                                &lpInternalValue,
                                &cAttrType,
                                &uAttrLength );

            nRC = SetCursorFirstEntityByString( vDomainTable,
                                                "DomainValue",
                                                "InternalStringValue",
                                                ( zPCHAR )lpInternalValue, "" );
         }
         else
         if ( lpDomain->cType == 'L' )
         {
            GetValueFromRecord( zView,
                                lpViewEntity,
                                lpViewAttribute,
                                &lpInternalValue,
                                &cAttrType,
                                &uAttrLength );

            lIntegerAttributeValue = ( zLONG )(*lpInternalValue);
            nRC = SetCursorFirstEntityByInteger( vDomainTable,
                                                 "DomainValue",
                                                 "InternalIntegerValue",
                                                 lIntegerAttributeValue, "" );
         }
         else
            nRC = -1;
         if ( nRC >= zCURSOR_SET )
         {
            nRC = GetStringFromAttribute( lpData,
                                          vDomainTable,
                                          "DomainValue",
                                          "ExternalDescription" );
         }

         break  ;
      case zDME_COMPARE_ATTRIBUTE :

         // Get internal value of attribute and simply compare to the string
         // or integer passed in.
         if ( lpDomain->cType == 'S' )
         {
            GetStringFromRecord( zView, lpViewEntity,
                                 lpViewAttribute,
                                 szStringAttributeValue, 64 );
            nRC = zstrcmp( (zPCHAR) lpData,
                            szStringAttributeValue );
         }

         if ( lpDomain->cType == 'L' )
         {
            GetValueFromRecord( zView,
                                lpViewEntity,
                                lpViewAttribute,
                                &lpInternalValue,
                                &cAttrType,
                                &uAttrLength );
            if ( *((zPLONG) lpData) == *((zPLONG) lpInternalValue) )
               nRC = 1;
            else
               nRC = 0;
         }

         break  ;
      case zDME_GET_FIRST_TBL_ENT_FOR_ATTR :
         nRC = SetCursorFirstEntity( vDomainTable, "DomainValue", "" );
         if ( nRC >= zCURSOR_SET )
         {
            nRC = GetStringFromAttribute( lpData, vDomainTable, "DomainValue",
                                          "ExternalDescription" );
            nRC = 0;
         }
         else
         {
            nRC = nRC;
         }

         break  ;
      case zDME_GET_NEXT_TBL_ENT_FOR_ATTR :
         nRC = SetCursorNextEntity( vDomainTable, "DomainValue", "" );
         if ( nRC >= zCURSOR_SET )
         {
            nRC = GetStringFromAttribute( lpData, vDomainTable, "DomainValue",
                                          "ExternalDescription" );
            nRC = 0;
         }
         else
         {
            nRC = nRC;
         }

         break  ;
      case zDME_SET_ATTRIBUTE_VALUE_NEXT :
         nRC = SetCursorNextEntity( vDomainTable, "DomainValue", "" );
         break  ;
      case zDME_SET_ATTRIBUTE_VALUE_PREV :
         nRC = SetCursorPrevEntity( vDomainTable, "DomainValue", "" );
         break  ;
      case zDME_ADD_TO_ATTRIBUTE :
         break  ;
      case zDME_GET_COMPARE_VALUE :
         break  ;
      case zDME_VALIDATE_LPDATA :
         break  ;
   }

   return nRC;
}


zSHORT OPERATION
fnDyamicT_fnLocalBuildQual_0( zVIEW     vSubtask,
                              zPVIEW    vQualObject,
                              zPCHAR    szContextName )
{
   zSHORT    nRC;

   nRC = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask,
                                  zMULTIPLE );
   nRC = CreateEntity( *vQualObject, "EntitySpec", zPOS_AFTER );
   nRC = SetAttributeFromString( *vQualObject, "EntitySpec", "EntityName",
                                       "Domain" );
   nRC = CreateEntity( *vQualObject, "QualAttrib", zPOS_AFTER );
   nRC = SetAttributeFromString( *vQualObject, "QualAttrib", "EntityName",
                                       "Domain" );
   nRC = SetAttributeFromString( *vQualObject, "QualAttrib", "AttributeName",
                                       "Name" );
   nRC = SetAttributeFromString( *vQualObject, "QualAttrib", "Value",
                                       szContextName );
   nRC = SetAttributeFromString( *vQualObject, "QualAttrib", "Oper",
                                       "=" );
   return ( 0 );
}

//./ ADD NAME=DynamicTableM
// Source Module=tzapdmaa.c
//***********************************************************************
//
//  ENTRY:      DynamicTableM
//
//  PURPOSE:    To process a Dynamic Table Domain with multiple Domain
//              tables.
//
//              In this routine, the EditString of the Domain Context defines
//              the two Attribute names which will hold the internal table
//              value and the external table value.  The two names are separated
//              by a slash ("/"). The Context name is used for the name of the
//              Object to be activated.
//
//  PARAMETERS: standard Domain Entry parameter list
//
//  RETURNS:
//
//  For zDME_SET_ATTRIBUTE:
//              0 - Attribute value has set
//    zCALL_ERROR - Error on call
//
//***********************************************************************
//./ END + 9
zSHORT OPERATION
zdmDynamicTableM( zLONG        lEntryType,
                  zCHAR        cType,
                  zPVOID       lpData,
                  zPCHAR       szContextName,
                  zVIEW        zView,
                  LPVIEWENTITY lpViewEntity,
                  LPVIEWATTRIB lpViewAttribute,
                  zUSHORT      uMaxStrLth )

{
   zVIEW      vDomainTable;
   zSHORT     nRC;
   zSHORT     ndx;
   zCHAR      szInternalAttributeName[ 33 ];
   zCHAR      szExternalAttributeName[ 33 ];
   zPCHAR     lpWorkString;
   LPDOMAIN   lpDomain;
   zLPCONTEXT lpContext;
   zPCHAR     lpTempReturn;
   zCHAR      szSavedTableName[ 36 ];
   zCHAR      szStringAttributeValue[ 65 ];
   zLONG      lIntegerAttributeValue;
   zPCHAR     lpInternalValue;
   zCHAR      cAttrType;
   zUSHORT    uAttrLength;

   //*******
   // Get the Attribute names for the Internal and External table value
   // from the EditString in the Context.
   //*******

   lpDomain = lpViewAttribute->lpDomain;
   if ( GetContext( &lpContext, lpDomain, szContextName ) )
   {
      lpWorkString = lpContext->lpEditString;
   }
   else
   {
      // "TZDME008 - Could not find context for Domain "
      SendDomainError( zView, lpDomain, 8, 8, 0,
                       TrueName( szContextName, zSHOW_ZKEY ),
                       lpViewAttribute->lpName );
      return zCALL_ERROR;
   }

   // Parse the lpWorkString to get Internal and External table value names.
   szInternalAttributeName[ 0 ] = 0;
   szExternalAttributeName[ 0 ] = 0;
   ndx = 0;
   if ( lpWorkString != 0 )
   {
      while ( *lpWorkString != 0 &&
            *lpWorkString != '/' )
      {
         szInternalAttributeName[ ndx++ ] = *(lpWorkString++);
      }
      szInternalAttributeName[ ndx ] = 0;

      if ( *lpWorkString == '/' )
      {
         ndx = 0;
         lpWorkString++;
         while ( *lpWorkString != 0 )
         {
            szExternalAttributeName[ ndx++ ] = *(lpWorkString++);
         }
         szExternalAttributeName[ ndx ] = 0;
      }
   }


   //*******
   // Activate the Dynamic Table and name it, if it does not currently
   // exist in memory with a name of "X_" + Domain Name.
   //*******
   zstrcpy( szSavedTableName, "X_" );
   zstrcat( szSavedTableName, szContextName );
   nRC = GetViewByName( &vDomainTable, szSavedTableName, 0, zLEVEL_TASK );
   if ( nRC < 0 )
   {
      nRC = ActivateObjectInstance( &vDomainTable, szContextName, zView,
                                    0, zACTIVATE_ROOTONLY_MULTIPLE );
      if ( nRC >= 0 )
      {
         SetNameForView( vDomainTable, szSavedTableName, 0, zLEVEL_TASK );
      }
      else
      {
         MessageSend( zView, "TZDMD002", "Data Validation",
                      "Validation table cannot be loaded.", zMSGQ_DOMAIN_ERROR, 0 );
         return zCALL_ERROR;
      }

   }

   // Validate that the entity name and the two attribute names exist.
   // We will do this by simply issueing a GetValue for each attribute.
   nRC = GetAddrForAttribute( (zPVOID) &lpTempReturn,
                              vDomainTable,
                              szContextName,
                              szInternalAttributeName );
   if ( nRC < -1 )
   {
      MessageSend( zView, "TZDMD004", "Data Validation",
                   "Internal Attribute definition is in error.", zMSGQ_DOMAIN_ERROR, 0 );
      return zCALL_ERROR;
   }

   nRC = GetAddrForAttribute( (zPVOID) &lpTempReturn,
                              vDomainTable,
                              szContextName,
                              szExternalAttributeName );
   if ( nRC < -1 )
   {
      MessageSend( zView, "TZDMD005", "Data Validation",
                   "External Attribute definition is in error.", zMSGQ_DOMAIN_ERROR, 0 );
      return zCALL_ERROR;
   }


   //*******
   // Now process the actual domain request.
   //*******
   nRC = 0;
   switch( lEntryType )
   {
      case zDME_SET_ATTRIBUTE :

         // For String Values.
         nRC = SetCursorFirstEntityByString( vDomainTable,
                                             szContextName,
                                             szExternalAttributeName,
                                             ( zPCHAR )lpData, "" );
         if ( nRC >= zCURSOR_SET )
         {
            if ( lpDomain->cType == 'S' )
            {
               GetStringFromAttribute( szStringAttributeValue,
                                       vDomainTable, szContextName, szInternalAttributeName );
               StoreValueInRecord( zView,
                                   lpViewEntity,
                                   lpViewAttribute,
                                   (zPVOID) szStringAttributeValue, 0 );
            }
            if ( lpDomain->cType == 'L' )
            {
               GetIntegerFromAttribute( &lIntegerAttributeValue,
                                       vDomainTable, szContextName, szInternalAttributeName );
               StoreValueInRecord( zView,
                                   lpViewEntity,
                                   lpViewAttribute,
                                   (zPVOID) &lIntegerAttributeValue, 0 );
            }
         }
         else
         {
            nRC = MessageSend( zView, "TZDMD003", "Data Validation",
                               "Invalid input value", zMSGQ_DOMAIN_ERROR, 0 );
            return zCALL_ERROR;
         }


         break  ;
      case zDME_GET_VARIABLE :

         if ( lpDomain->cType == 'S' )
         {
            GetValueFromRecord( zView,
                                lpViewEntity,
                                lpViewAttribute,
                                &lpInternalValue,
                                &cAttrType,
                                &uAttrLength );

            nRC = SetCursorFirstEntityByString( vDomainTable,
                                                szContextName,
                                                szInternalAttributeName,
                                                ( zPCHAR )lpInternalValue, "" );
         }
         else
         if ( lpDomain->cType == 'L' )
         {
            GetValueFromRecord( zView,
                                lpViewEntity,
                                lpViewAttribute,
                                &lpInternalValue,
                                &cAttrType,
                                &uAttrLength );

            lIntegerAttributeValue = ( zLONG )(*lpInternalValue);
            nRC = SetCursorFirstEntityByInteger( vDomainTable,
                                                 szContextName,
                                                 szInternalAttributeName,
                                                 lIntegerAttributeValue, "" );
         }
         else
            nRC = -1;
         if ( nRC >= zCURSOR_SET )
         {
            nRC = GetStringFromAttribute( lpData,
                                          vDomainTable,
                                          szContextName,
                                          szExternalAttributeName );
         }

         break  ;
      case zDME_COMPARE_ATTRIBUTE :

         // Get internal value of attribute and simply compare to the string
         // or integer passed in.
         if ( lpDomain->cType == 'S' )
         {
            GetStringFromRecord( zView, lpViewEntity,
                                 lpViewAttribute,
                                 szStringAttributeValue, 64 );
            nRC = zstrcmp( (zPCHAR) lpData,
                            szStringAttributeValue );
         }

         if ( lpDomain->cType == 'L' )
         {
            GetValueFromRecord( zView,
                                lpViewEntity,
                                lpViewAttribute,
                                &lpInternalValue,
                                &cAttrType,
                                &uAttrLength );
            if ( *((zPLONG) lpData) == *((zPLONG) lpInternalValue) )
               nRC = 1;
            else
               nRC = 0;
         }

         break  ;
      case zDME_GET_FIRST_TBL_ENT_FOR_ATTR :
         nRC = SetCursorFirstEntity( vDomainTable, szContextName, "" );
         if ( nRC >= zCURSOR_SET )
         {
            nRC = GetStringFromAttribute( lpData, vDomainTable, szContextName,
                                          szExternalAttributeName );
            nRC = 0;
         }
         else
         {
            nRC = nRC;
         }

         break  ;
      case zDME_GET_NEXT_TBL_ENT_FOR_ATTR :
         nRC = SetCursorNextEntity( vDomainTable, szContextName, "" );
         if ( nRC >= zCURSOR_SET )
         {
            nRC = GetStringFromAttribute( lpData, vDomainTable, szContextName,
                                          szExternalAttributeName );
            nRC = 0;
         }
         else
         {
            nRC = nRC;
         }

         break  ;
      case zDME_SET_ATTRIBUTE_VALUE_NEXT :
         nRC = SetCursorNextEntity( vDomainTable, szContextName, "" );
         break  ;
      case zDME_SET_ATTRIBUTE_VALUE_PREV :
         nRC = SetCursorPrevEntity( vDomainTable, szContextName, "" );
         break  ;
      case zDME_ADD_TO_ATTRIBUTE :
         break  ;
      case zDME_GET_COMPARE_VALUE :
         break  ;
      case zDME_VALIDATE_LPDATA :
         break  ;
   }

   return nRC;
}

