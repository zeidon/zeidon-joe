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

    Copyright (c) 2009 - 2016 Arksoft, Inc.
**/

package com.quinsoft.epamms;

//import java.io.File;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.lang.Math;
//import java.text.NumberFormat;
//import java.util.*;
import java.nio.CharBuffer;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.joda.time.Days;
import java.util.Date;
import org.joda.time.DateTime;
//import org.joda.time.Days;

//import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.vml.VmlOperation;
import com.quinsoft.zeidon.vml.zVIEW;
//import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;
import com.quinsoft.zeidon.zeidonoperations.ActiveDirectory;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;


/**
 * @author QuinSoft
 *
 * Admin password zPrefixCF6j8nasPa8FAO7iwIB8fR6YDeupqBTOcqJwU9kP1v3kLB8fRpSarg==
 */

public class ZGlobal1_Operation extends VmlOperation
{
   private final ZDRVROPR m_ZDRVROPR;
   private final KZOEP1AA m_KZOEP1AA;
   private final ActiveDirectory m_ActiveDirectory;
   //public ZGLOBAL1_Operation( TaskQualification taskQual )
   public ZGlobal1_Operation( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
      m_KZOEP1AA = new KZOEP1AA( view );
      m_ActiveDirectory = new ActiveDirectory( );
      //private final KZOEP1AA m_KZOEP1AA;
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function Name: >>>SetAttributeFromCurrentDateTime
   //
   // .Description: Set an Attribute to Current Date Time
   //
   // .Return Value: int
   //    (Same as SetAttributeFromString() )
   //
   // .Parameter:
   //    Data type       Name        (I/O/U) Description
   //    View            View          (I)   View for Attribute
   //    String          entityName    (I)   Name of Entity
   //    String          attributeName (I)   Name of Attribute
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   // .Detail description
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SetAttributeFromCurrentDateTime( View   View,
                                    String entityName,
                                    String attributeName )
   {
      String  stringTimeStamp = null;
      int rc;

      stringTimeStamp = KZOEP1AA.SysGetDateTime( stringTimeStamp );
      StringBuilder sb_szDate = new StringBuilder( 32 );
      KZOEP1AA.SysGetDateTime( sb_szDate );

      rc = SetAttributeFromString( View, entityName, attributeName, stringTimeStamp );
      return rc;
   }

   /*
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function Name: >>>DecimalSumOf
   //
   // .Description: Compute the sum of a double attribute over all
   //               instances of an entity
   //
   // .Return Value: BigDecimal - sum
   //
   // .Parameter:
   //    Datatype        Name            (I/O/U) Description
   //    View            View              (I)   View for Entity
   //    String          entityName        (I)   Name of Entity
   //    String          attributeName     (I)   Name of Attribute
   //    String          stringParentName  (I)   Name of Parent Entity
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   // .Detail description
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   public Double
   DecimalSumOf( View   vSum,
                 String entityName,
                 String attributeName,
                 String stringParentName )
   {
      Double decimalSum;
      Double decimalValue = null;
      int rc;

      decimalSum = 0.0;

      rc = SetCursorFirstEntity( vSum, entityName, stringParentName );
      while ( rc > zCURSOR_UNCHANGED )
      {
         decimalSum += GetDecimalFromAttribute( decimalValue, vSum, entityName, attributeName );
         rc = SetCursorNextEntity( vSum, entityName, stringParentName );
      }

      return decimalSum;
   }

   // Sets the cursor to the latest entity based on a date/time stamp
   // attribute.  The attribute passed should be a date/time stamp, but
   // could be any attribute with ascending collating sequence.
   public int
   SetCursorLatestEntity( View   view,
                          String entityName,
                          String attributeName )
   {
      OrderEntityForView( view, entityName, attributeName );
      SetCursorLastEntity( view, entityName, "" );
      return 0;
   }

   public int
   GetIntFromAttrByContext( MutableInt lValue,
                            View   view,
                            String stringEntity,
                            String stringAttribute,
                            String stringContext )
   {
      int lValueInt = 0;

      lValueInt = GetVariableFromAttribute( lValueInt, 0, zTYPE_INTEGER, 0,
                                             view, stringEntity, stringAttribute, stringContext, 0 );
      lValue.setValue( lValueInt );
      return lValue.intValue();
   }

   public int
   GetStrFromAttrByContext( StringBuilder sbValue,
                            int    lOrigLth,
                            View   view,
                            String stringEntity,
                            String stringAttribute,
                            String stringContext )
   {
      MutableInt k = new MutableInt( 0 );
      int     lLth;
   // String  string;

      // If the Context value is null, use the default Context.
      if ( lOrigLth < 10000 )
         lLth = lOrigLth;
      else
         lLth = 10000;

      if ( stringContext == null )
         GetVariableFromAttribute( sbValue, k, zTYPE_STRING, lLth,
                                   view, stringEntity, stringAttribute, "", zUSE_DEFAULT_CONTEXT );
      else
         GetVariableFromAttribute( sbValue, k, zTYPE_STRING, lLth,
                                   view, stringEntity, stringAttribute, stringContext, 0 );

      return k.intValue( );
   }

   public int
   SetAttrFromIntByContext( View   view,
                            String stringEntity,
                            String stringAttribute,
                            int    lValue,
                            String stringContext )
   {
      int rc;

      rc = SetAttributeFromVariable( view, stringEntity, stringAttribute,
                                     lValue, zTYPE_INTEGER, 0, stringContext, 0);
      return rc;
   }

   public int
   AddToAttrFromIntByContext( View   view,
                              String stringEntity,
                              String stringAttribute,
                              int    lValue,
                              String stringContext )
   {
      int rc;

      rc = AddToAttributeFromVariable( view, stringEntity, stringAttribute,
                                       lValue, zTYPE_INTEGER, 0, stringContext );
      return rc;
   }

   /*
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function name: >>>GetEnvVariable
   //
   // .Description: Get an environment variable
   //
   // .Return value: int
   //    0 - OK
   //    else Error
   //
   // .Parameter:
   //    Data type,      Name             (I/O/U) Description
   //    String          stringReturnWert   (O)   value of the env var (returned)
   //    String          stringVariableName (I)   name of the env var
   //    int             nMaxReturnLth      (I)   max. length of stringReturnWert
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   public String
   GetEnvVariable( String stringReturnValue,
                   String stringVariableName,
                   int    nMaxReturnLth )
   {
      stringReturnValue = KZOEP1AA.SysGetEnvVar( stringReturnValue, stringVariableName, nMaxReturnLth );
      return stringReturnValue;
   }

   /*
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function Name: >>>StrToInt
   //
   // .Description: Convert an String to an Integer
   //
   // .Return Value: int
   //    (Integer Value of String )
   //
   // .Parameter:
   //    Data type,      Name,       (I/O/U) Description
   //    String          stringStr         (I)   String to convert
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   // .Detail description
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   public int
   StrToInt( String string )
   {
      return Integer.parseInt( string );
   }

   /*
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function Name: >>>StrToDecimal
   //
   // .Description: Convert an String to a Decimal
   //
   // .Return Value: BigDecimal
   //             0 - OK
   //             else: invalid string
   //
   // .Parameter:
   //    Data type,      Name,       (I/O/U) Description
   //    String          stringStr         (I)   String to convert
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   // .Detail description
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   public Double
   StrToDecimal( String stringStr )
   {
      if ( stringStr == null || stringStr.equals("") )
         return 0.0;

      return Double.valueOf( stringStr );
   }

   /*
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function Name: >>>CodeToChar
   //
   // .Description: Returns the Char for the given code
   //
   // .Return Value: zVOID
   //
   // .Parameter:
   //    Data type,      Name,       (I/O/U) Description
   //    String          stringStr     (O)   String, which contains the char
   //    int             sCode         (I)   Code for Char
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   // .Detail description
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   */
   public String
   CodeToChar( String stringStr, int sCode )
   {
      char[] arrayChar = new char[ 2 ];

      arrayChar[ 0 ] = (char) (sCode & 0x00ff);
      arrayChar[ 1 ] = (char) 0;
      arrayChar[ 2 ] = 'x';  // testing to ensure array bounds checking occurs at run time
      return stringStr = arrayChar.toString( );
   }

   /////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // .Function Name: >>>CharToCode
   //
   // .Description: Returns the code of the first char in string
   //
   // .Return Value: int - code for char
   //
   // .Parameter:
   //    Data type,      Name,       (I/O/U) Description
   //    String          stringStr         (I)   String with char on first pos
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   // .Detail description
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   CharToCode( String stringStr )
   {
      int nCode = 0;

      nCode = (int) stringStr.charAt( 0 );
      return nCode;
   }

   public MutableInt
   GetDateAttributeDifferenceInDays( MutableInt    lDays,
                                     View   srcView,
                                     String srcEntityName,
                                     String srcAttributeName,
                                     View   tgtView,
                                     String tgtEntityName,
                                     String tgtAttributeName )
   {
      DateTimeRecord  SourceDate = null;
      DateTimeRecord  TargetDate = null;
      String          stringSourceDate = null;
      String          stringTargetDate = null;
      int             lDaysTmp;

      // read the attributes
      //stringSourceDate = GetStringFromAttribute( stringSourceDate, srcView, srcEntityName, srcAttributeName );
      //stringTargetDate = GetStringFromAttribute( stringTargetDate, tgtView, tgtEntityName, tgtAttributeName );

      DateTime BeginDate = srcView.cursor(srcEntityName).getAttribute(srcAttributeName).getDateTime();
      DateTime EndDate = tgtView.cursor(tgtEntityName).getAttribute(tgtAttributeName).getDateTime();

      //DateTime BeginDate = new DateTime(stringSourceDate);
      //DateTime EndDate = new DateTime(stringTargetDate);

      //int days = Days.daysBetween( BeginDate, EndDate).getDays();
      int days = Days.daysBetween( EndDate, BeginDate).getDays();
      /*
      UfStringToDateTime( stringSourceDate, SourceDate );
      UfStringToDateTime( stringTargetDate, TargetDate );

      // subtract the values
      lDaysTmp = lDays.intValue();
      lDaysTmp = UfDateTimeDiff( lDaysTmp, TargetDate, SourceDate, zDT_DAY );

      lDays.setValue(lDaysTmp);
      */

      lDays.setValue(days);

      return lDays;
   }

   public int GetEntityNameFromStructure( String stringInternalEntityStructure, StringBuilder returnEntityName )
   {
      returnEntityName.setLength( 0 );
      returnEntityName.append( stringInternalEntityStructure );
      return 0;
   }

   public String GetEntityNameFromStructure( String stringInternalEntityStructure, String returnEntityName )
   {
   // returnEntityName = stringInternalEntityStructure;
   // return returnEntityName;
      return stringInternalEntityStructure;
   }

   public int
   AddWorkingDaysToDate( View   tgtView,
                         String tgtEntityName,
                         String tgtAttributeName,
                         int    lWorkingDays )
   {
      int  lRegularDays;
      int  lRemainder;
      int  nRC;

      // Convert Working Days to Regular Days and add them to a Date Attribute.
      // To determine regular days, we will take working daYS and
      // simply multiply by 7/5, using remainder and not fraction.
      // Thus 8 working days is divided by 5 to get 1 with remainder 3.
      // We multiply the 1 by 7 and add the 3 to get 10.
      lRegularDays = lWorkingDays / 5;
      lRemainder   = lWorkingDays - (lRegularDays * 5);
      lRegularDays = (lRegularDays * 7) + lRemainder;

      nRC = AddToAttributeFromVariable( tgtView, tgtEntityName, tgtAttributeName,
                                        lRegularDays, zTYPE_INTEGER, 0, "Day" );
      return nRC;
   }

   public int
   CompareAttributeByShortString( View   view,
                                  String entityName,
                                  String attributeName,
                                  String compareValue )

   {
      String  stringTempString = null;
      int nLth = 0;
      int nRC;

      stringTempString = GetVariableFromAttribute( stringTempString, nLth, zTYPE_STRING, 254,
                                                   view, entityName, attributeName, "", 0 );

   // nLth = stringStringValue.length( );
      nRC = stringTempString.compareTo( compareValue );
      return nRC;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ActivateWorkObjectFromFile
   //
   // Activate a work object from a file
   //
   public int
   ActivateWorkObjectFromFile( zVIEW  vWorkView,
                               String stringFileName,
                               String stringLOD_Name,
                               View   ViewToWindow ) throws IOException
   {
      int  file;
      int  nRC;

      // First make sure the file exists. If not, return an error code.
      file = m_KZOEP1AA.SysOpenFile( ViewToWindow, stringFileName, COREFILE_READ );
      if ( file == -1 )
         return -1;

      m_KZOEP1AA.SysCloseFile( ViewToWindow, file, 0 );

      // Next Activate the OI from the file just created.
      nRC = ActivateOI_FromFile( vWorkView, stringLOD_Name, ViewToWindow,
                                 stringFileName, zSINGLE );
      return nRC;

   } // ActivateWorkObjectFromFile

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AddDaysToDate
   //
   // Add Days To a Date Attribute
   //
   public int  // TODO
   AddDaysToDate( View view, String entityName, String attributeName, int days )
   {
     int nRC;

     nRC = AddToAttributeFromVariable( view, entityName,
                                       attributeName, days,
                                       zTYPE_INTEGER, 0, "Day" );
     return nRC;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AddMonthsToDate
   //
   // Add Months To a Date Attribute
   //
   public int  // TODO
   AddMonthsToDate( View view,
                  String entityName,
                  String attributeName,
                  int months )  // Days or Months?
   {
      int nRC;

      nRC = AddToAttributeFromVariable( view, entityName,
                                        attributeName, months,
                                        zTYPE_INTEGER, 0, "Month" );
      return nRC;
   } // AddDaysToDate

   /**  these do not seem to be used in Zencas ... good thing because of the global buffer usage.
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ReadLineToLongPointer
   //
   // Read a line passing the pointer back as a LONG
   //
   public int
   ReadLineIntoGlobalBuffer( View  ViewToWindow, int hFile )
   {
      int nRC;

      nRC = SysReadLine( ViewToWindow, lpBufferArea, hFile );

      return nRC;

   } // ReadLineToLongPointer

   public int
   SetAttributeFromTabField( View   mCustO,
                             String lpEntityName,
                             String lpAttributeName,
                             int    lFieldPosition )
   {
      StringBuffer sb;
      String lpNext;
      int    k;
      int    nRC;

      // Position on field based on Field position, assuming fields are delineated by tabs.
      sb = lpBufferArea;

      for ( k = lFieldPosition; k > 1; k-- )
      {
         for ( lpNext = sb; *lpNext != '\t'; lpNext++ )
         {
            ;
         }

         sb = lpNext + 1;
      }

      // Find next tab to change to string end.
      for ( lpNext = sb; *lpNext != '\t'; lpNext++ )
      {
         ;
      }

      *lpNext = 0;

      // Set the Attribute value.
      nRC = SetAttributeFromString( mCustO, lpEntityName, lpAttributeName, sb );

      // Set string end back to tab.
      *lpNext = '\t';

      return nRC;
   }
   **/

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SeparateName
   //
   // Separate a name into first and last names
   //
   public int
   SeparateName( String        fullName,
                 StringBuilder firstName,
                 StringBuilder lastName )
   {
   // String lpNext;
      int k = 0;
      int nLth = fullName.length( );

      String s = fullName.trim( );  // remove whitespace from beginning and end of FullName

      // Eliminate any "Mr." or "Ms." characters in front of the name and then point to first non-blank.
      if ( s.charAt( k ) == 'M' && (s.charAt( k + 1 ) == 'r' || s.charAt( k + 1 ) == 's') && s.charAt( k + 2 ) == '.' )
      {
         k = 3;
         while ( s.charAt( k ) == ' ' )
            k++;
      }

      // put original string into an array of chars
      //
   // char[] charArray = new char[ nLth + 1 ];  // we are eliminating characters, so this should be plenty of room
      int j = 0;
      int nLastBegin = 0;
   // int nFirstEnd = 0;
      while ( k <= nLth )
      {
         if ( nLastBegin == 0 )
         {
            if ( s.charAt( k ) == ' ' || s.charAt( k ) == '\t' )
            {
            // nFirstEnd = k;
               firstName.insert( j++, '\0' );  // terminate first name
               nLastBegin = k + 1;

               // Skip to next non-blank or end of string.
               while ( s.charAt( nLastBegin ) == ' ' || s.charAt( nLastBegin ) == '\t' )
                  nLastBegin++;

               j = 0;  // process last name
               while ( nLastBegin <= nLth )
                  lastName.insert( j++, s.charAt( nLastBegin++ ) );

               break;  // out of outer while loop
               }
            else
            {
               firstName.insert( j++, s.charAt( k++ ) );  // process the First Name (all chars to the first blank)
            }
         }
      }

      return 0;
   } // SeparateName

 /** fortunately these next two methods are not used anywhere
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: GetViewFromBlobAttribute
   //
   // Get a view from a blob attribute
   //
   public int
   GetViewFromBlobAttribute( View  pvRetrievedView,
                          View  vOI_View,
                          String stringOI_EntityName,
                          String stringOI_AttributeName )
   {
      int nRC;
      int lLth;

      lLth = sizeof( vOI_View );
      nRC = GetBlobFromAttribute( pvRetrievedView, lLth, vOI_View, stringOI_EntityName, stringOI_AttributeName );

      return 0;
   } // GetViewFromBlobAttribute

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SetBlobAttributeFromView
   //
   // Set a blob attribute from a view.
   //
   public int
   SetBlobAttributeFromView( View  vOI_View,
                             String stringOI_EntityName,
                             String stringOI_AttributeName,
                             View   ViewToSet )
   {
      int nRC;

      nRC = SetAttributeFromBlob( vOI_View, stringOI_EntityName, stringOI_AttributeName,
                                  ViewToSet, sizeof( ViewToSet ) );

      return nRC;
   } // SetBlobAttributeFromView
 **/

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AddressFormatToMultiLine
   //
   // can be used for display or labels
   //
   public int
   AddressFormatToMultiLine( String stringMultiLineAddress,
                             String stringLine1,
                             String stringLine2,
                             String stringLine3,
                             String stringCity,
                             String stringState,
                             String stringPostalCode )
   {
      String stringFirstPart = null;

      if ( stringLine1.isEmpty( ) == false )
         stringFirstPart = zsprintf( stringFirstPart, "%s\n", stringLine1 );

      if ( stringLine2.isEmpty( ) == false )
         stringFirstPart = zsprintf( stringFirstPart, "%s%s\n", stringFirstPart, stringLine2 );

      if ( stringLine3.isEmpty( ) == false )
         stringFirstPart = zsprintf( stringFirstPart, "%s%s\n", stringFirstPart, stringLine3 );

      stringMultiLineAddress = zsprintf( stringMultiLineAddress, "%s\n%s, %s %s", stringFirstPart,
                                         stringCity, stringState, stringPostalCode );
      return 0;
   } // AddressFormatToMultiLine

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: dAdressLabel
   //
   // multiline address (without name)
   //
   public String
   fnAdressLabelText( View  vAnyObject,  // BASED ON LOD "any object with entity that has address attributes"
                      String stringInternalEntityStructure,
                      String stringInternalAttribStructure,
                      String stringReturnText )
   {
      String entityName = null;
      String stringAttribName = null;
      String stringMultiLineAddress = null;
      StringBuilder sb = new StringBuilder( );
      String stringAttn = null;
      String stringCity = null;
      String stringState = null;
      String stringZipCode = null;
      String stringZipCodeFormatted = null;
      String stringCountry = null;
      StringBuilder stringSep = new StringBuilder();      // set to /r/n or "; "

      // NEED TO FIX THIS CAUSE I GET TOO MANY ERRORS MAINLY WITH IsValidAttribute
      return "";
/*
      entityName = zstrcpy( entityName, stringInternalEntityStructure );
      stringAttribName = zstrcpy( stringAttribName, stringInternalAttribStructure );
      if ( ZeidonStringCompare( stringAttribName, 1, 5, "dLine", 1, 5, 33 ) == 0 )
         zstrcpy( stringSep, "; " );
      else
         zstrcpy( stringSep, "\\r\\n" );

      stringMultiLineAddress = "";
      stringAttn = "";
      //sb.setCharAt( 0, '\0' );

      if ( IsValidAttribute ( "AttentionLine1", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( sb, vAnyObject, entityName, "AttentionLine1" );

      if ( sb.length( ) != 0 )
      {
         stringAttn = zsprintf( stringAttn, "Attn:  %s%s", sb, stringSep );
         sb.setLength( 0 );
      }

      if ( IsValidAttribute ( "AttentionLine2", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( sb, vAnyObject, entityName, "AttentionLine2" );

      if ( sb.length( ) != 0 )
      {
         stringAttn = zsprintf( stringAttn, "%s         %s%s", stringAttn, sb, stringSep );
         sb.setLength( 0 );
      }

      if ( stringAttn.length( ) != 0 )
         ZeidonStringCopy( stringMultiLineAddress, 1, 0, stringAttn, 1, 0, 2000 );

      if ( IsValidAttribute ( "Line1", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( sb, vAnyObject, entityName, "Line1" );

      if ( sb.length( ) != 0 )
         zsprintf( stringMultiLineAddress, "%s%s%s", stringMultiLineAddress, sb, stringSep );

      if ( IsValidAttribute ( "Line2", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( sb, vAnyObject, entityName, "Line2" );
      else
         sb.setLength( 0 );

      if ( sb.length( ) != 0 )
         stringMultiLineAddress = zsprintf( stringMultiLineAddress, "%s%s%s", stringMultiLineAddress, sb, stringSep );

      if ( IsValidAttribute ( "Line3", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( sb, vAnyObject, entityName, "Line3" );
      else
         sb.setLength( 0 );

      if ( sb.length( ) != 0 )
         stringMultiLineAddress = zsprintf( stringMultiLineAddress, "%s%s%s", stringMultiLineAddress, sb, stringSep );

      stringCity = GetStringFromAttribute( stringCity, vAnyObject, entityName, "City" );
      //GetStringFromAttribute( stringState, vAnyObject, entityName, "State" );
      stringState = GetVariableFromAttribute( stringState, 0, zTYPE_STRING, 120,
                                          vAnyObject, entityName,
                                          "StateProvince", "State", 0 );
      if ( stringState.length( ) == 0 )
      {
         if ( IsValidAttribute ( "InternationalRegion", stringInternalEntityStructure ) == 0 )
            stringState = GetVariableFromAttribute( stringState, 0, zTYPE_STRING, 120,
                                                    vAnyObject, entityName,
                                                    "InternationalRegion", "", 0 );
      }

      // For ZipCodes larger than five characters, we want to format them with a
      // dash, if they don't already have a dash.
      stringZipCode = GetVariableFromAttribute( stringZipCode, 0, zTYPE_STRING, 11,
                                           vAnyObject, entityName, "PostalCode", "", 0 );
      if ( stringZipCode.length( ) > 5 && stringZipCode.charAt( 5 ) != '-' )
          stringZipCodeFormatted = stringZipCode.substring( 0, 4 ) + "-" + stringZipCode.substring( 5, -1 );
      else
         stringZipCodeFormatted = stringZipCode;

      stringCountry = "";
      if ( IsValidAttribute ( "Country", stringInternalEntityStructure ) == 0 )
         GetStrFromAttrByContext( sb, 50, vAnyObject, entityName, "Country", "Country" );

      if ( sb.equals( "US" ) == true )
         stringCountry = "";
      else
         stringCountry = sb.toString( );

      if ( stringCity.length( ) != 0 )
         stringMultiLineAddress = zsprintf( stringMultiLineAddress, "%s%s, %s %s %s", stringMultiLineAddress,
                                            stringCity, stringState, stringZipCodeFormatted, stringCountry );
      else
         stringMultiLineAddress = zsprintf( stringMultiLineAddress, "%s%s %s %s", stringMultiLineAddress,
                                            stringState, stringZipCodeFormatted, stringCountry );

      stringReturnText = ZeidonStringCopy( stringReturnText, 1, 0, stringMultiLineAddress, 1, 0, 255 );

      return stringReturnText;
      */
   } // fnAdressLabelText

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: dAdressLabel
   //
   // multiline address (without name)
   //
   public int /* DERIVED ATTRIBUTE */
   dAdressLabel( View  vAnyObject,  // BASED ON LOD "any object with entity that has address attributes"
                 String stringInternalEntityStructure,
                 String stringInternalAttribStructure,
                 int nGetOrSetFlag )
   {
      String stringMultiLineAddress = null;
      String entityName = null;

      entityName = zstrcpy( entityName, stringInternalEntityStructure );
      if ( CheckExistenceOfEntity( vAnyObject, entityName ) != 0)
      {
         return 0;
      }

      stringMultiLineAddress = fnAdressLabelText( vAnyObject, stringInternalEntityStructure,
                                                stringInternalAttribStructure, stringMultiLineAddress );

      StoreStringInRecord( vAnyObject, stringInternalEntityStructure,
                           stringInternalAttribStructure, stringMultiLineAddress );
      return 0;

   } // dAdressLabel

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: dAdressLabel
   //
   // multiline address (with name)
   //
   public int /* DERIVED ATTRIBUTE */
   dAdressLabelFull( View  vAnyObject,  // BASED ON LOD "any object with entity that has address attributes"
                     String stringInternalEntityStructure,
                     String stringInternalAttribStructure,
                     int nGetOrSetFlag )
   {
      String entityName = null;
      String stringMultiLineAddress;
      String string = null;
      String stringCompanyName;

      entityName = zstrcpy( entityName, stringInternalEntityStructure );
      if ( CheckExistenceOfEntity( vAnyObject, entityName ) != 0 )
      {
         return 0;
      }

      stringMultiLineAddress = "";
      stringCompanyName = "";
      //if ( IsValidAttribute ( "CompanyName", stringInternalEntityStructure ) == 0 )
      //   stringCompanyName = GetStringFromAttribute( stringCompanyName, vAnyObject, entityName, "CompanyName" );

      if ( stringCompanyName.length( ) != 0 )
         stringMultiLineAddress = zsprintf( stringMultiLineAddress, "%s\r\n", stringCompanyName );

      string = fnAdressLabelText( vAnyObject, stringInternalEntityStructure, stringInternalAttribStructure, string );

      stringMultiLineAddress = ZeidonStringConcat( stringMultiLineAddress, 1, 0, string, 1, 0, 2000 );

      StoreStringInRecord( vAnyObject, stringInternalEntityStructure,
                           stringInternalAttribStructure, stringMultiLineAddress );
      return 0;
   } // dAdressLabelFull

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: PersonName_LastFirstMiddle
   //
   // Person's name formatted Last Name first
   //
   public int /* DERIVED ATTRIBUTE */
   PersonName_LastFirstMiddle( View  vAnyObject,  // BASED ON LOD "any object with entity that has "NAME" attributes"
                               String stringInternalEntityStructure,
                               String stringInternalAttribStructure,
                               int nGetOrSetFlag )
   {
      String stringLastFirstMiddle;
      String entityName = null;
      String string;

      entityName = zstrcpy( entityName, stringInternalEntityStructure );
      stringLastFirstMiddle = "";
      string = "";

      // Last Name
      if ( IsValidAttribute( "LastName", stringInternalEntityStructure ) == 0 )
         stringLastFirstMiddle = GetStringFromAttribute( stringLastFirstMiddle, vAnyObject, entityName, "LastName" );

      // First Name
      if ( IsValidAttribute( "FirstName", stringInternalEntityStructure ) == 0 )
         string = GetStringFromAttribute( string, vAnyObject, entityName, "FirstName" );

      if ( string.length( ) != 0 )
      {
         stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, ", ", 1, 0, 101 );
         stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, string, 1, 0, 101 );
         string = "";
      }

      // Middle Name
      if ( IsValidAttribute ( "MiddleName", stringInternalEntityStructure ) == 0 )
         string = GetStringFromAttribute( string, vAnyObject, entityName, "MiddleName" );

      if ( string.length( ) != 0 )
      {
         stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, " ", 1, 0, 101 );
         stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, string, 1, 0, 101 );
         if ( string.length( ) == 1 )
            stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, ".", 1, 0, 101 );

         string = "";
      }

      // Suffix
      if ( IsValidAttribute ( "Suffix", stringInternalEntityStructure ) == 0 )
         string = GetStringFromAttribute( string, vAnyObject, entityName, "Suffix" );

      if ( zstrlen( string ) != 0 )
      {
         stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, " ", 1, 0, 101 );
         stringLastFirstMiddle = ZeidonStringConcat( stringLastFirstMiddle, 1, 0, string, 1, 0, 101 );
         string = "";
      }

      StoreStringInRecord( vAnyObject, stringInternalEntityStructure,
                           stringInternalAttribStructure, stringLastFirstMiddle );
      return 0;
   } // dAdressLabel

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: PersonName_FirstMiddleLast
   //
   // Person's name formatted Last Name last
   //
   public int /* DERIVED ATTRIBUTE */
   PersonName_FirstMiddleLast( View  vAnyObject,  // BASED ON LOD "any object with entity that has "NAME" attributes"
                               String stringInternalEntityStructure,
                               String stringInternalAttribStructure,
                               int nGetOrSetFlag )
   {
      StringBuilder stringReturnName = new StringBuilder( );
      String entityName = null;
      StringBuilder string = new StringBuilder( );

      entityName = zstrcpy( entityName, stringInternalEntityStructure );
      stringReturnName.append("");
      string.append("");

      // Last Name
      if ( IsValidAttribute ( "FirstName", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( stringReturnName, vAnyObject, entityName, "FirstName" );

      // Middle Name
      if ( IsValidAttribute ( "MiddleName", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( string, vAnyObject, entityName, "MiddleName" );
      if ( zstrlen( string ) != 0 )
      {
         ZeidonStringConcat( stringReturnName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( stringReturnName, 1, 0, string, 1, 0, 101 );
         if ( zstrlen( string ) == 1 )
            ZeidonStringConcat( stringReturnName, 1, 0, ".", 1, 0, 101 );

         string.append("");
      }

      // Last Name
      if ( IsValidAttribute ( "LastName", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( string, vAnyObject, entityName, "LastName" );

      if ( zstrlen( string ) != 0 )
      {
         ZeidonStringConcat( stringReturnName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( stringReturnName, 1, 0, string, 1, 0, 101 );
         string.append("");
      }

      // Suffix
      if ( IsValidAttribute ( "Suffix", stringInternalEntityStructure ) == 0 )
         GetStringFromAttribute( string, vAnyObject, entityName, "Suffix" );

      if ( zstrlen( string ) != 0 )
      {
         ZeidonStringConcat( stringReturnName, 1, 0, " ", 1, 0, 101 );
         ZeidonStringConcat( stringReturnName, 1, 0, string, 1, 0, 101 );
         string.append("");
      }

      StoreStringInRecord( vAnyObject, stringInternalEntityStructure,
                           stringInternalAttribStructure, stringReturnName.toString() );

      return 0;
   } // dAdressLabel

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: zTrim
   //
   // trim whitespace from front and back
   //
   // Remove whitespace at the beginning and end of a string.
   //
   public int
   zTrim( StringBuilder stringStringInOut )
   {
      String s = StringUtils.trim( stringStringInOut.toString() );
      stringStringInOut.replace( 0, stringStringInOut.length(), s );
      return 0;
   } // zTrim

   public String
   zTrim( String stringStringInOut )
   {
      return stringStringInOut.trim( );
   } // zTrim

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SetDecimalPrecisionRounded
   //
   public Double
   SetDecimalPrecisionRounded( Double pdDecimalValue,
                               int ulNumberOfDecimals )
   {
      StringBuilder sb = new StringBuilder( ulNumberOfDecimals > 0 ? ulNumberOfDecimals + 5 : 25 );

      SysConvertDecimalToString( pdDecimalValue, sb, (int) ulNumberOfDecimals );
      MutableDouble d = new MutableDouble( pdDecimalValue );
      SysConvertStringToDecimal( sb.toString( ), d );
      return d.toDouble( );

   }  // SetDecimalPrecisionRounded

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: RemoveLeadingBlanksFromAttrib
   //
   public int
   RemoveLeadingBlanksFromAttrib( View   view,
                                  String stringEntity,
                                  String stringAttribute )
   {
      StringBuilder sb = new StringBuilder( 256 );
      MutableInt i = new MutableInt( 0 );
      int k;

      // Remove any leading blanks from the attribute.

      GetVariableFromAttribute( sb, i, zTYPE_STRING, 253,
                                view, stringEntity, stringAttribute, "", 0 );
      if ( sb.charAt( 0 ) == ' ' )
      {
         k = 1;
         while ( sb.charAt( k ) == ' ' )
            k++;

         SetAttributeFromString( view, stringEntity, stringAttribute, sb.substring( k ) );
      }

      return 0;

   } // RemoveLeadingBlanksFromAttrib

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: RemoveLeadingZerosFromAttrib
   //
   public int
   RemoveLeadingZerosFromAttrib( View   view,
                                 String stringEntity,
                                 String stringAttribute )
   {
      StringBuilder sb = new StringBuilder( 256 );
      MutableInt i = new MutableInt( 0 );
      int k;

      // Remove any leading zeros from the attribute.

      GetVariableFromAttribute( sb, i, zTYPE_STRING, 253,
                                view, stringEntity, stringAttribute, "", 0 );
      if ( sb.charAt( 0 ) == '0' )
      {
         k = 1;

         while ( sb.charAt( k ) == '0' )
            k++;

         SetAttributeFromString( view, stringEntity, stringAttribute, sb.substring( k ) );
      }

      return 0;

   } // RemoveLeadingZerosFromAttrib

   /** doesn't seem to be used in Zencas (that's a good thing).
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: GetButtonHeaderName
   //
   // this can only be used inside the button action for the new list box
   //
   public String
   GetButtonHeaderName( View   ViewToWindow,
                        String stringButtonHeaderName )
   {

      zstrcpy( stringButtonHeaderName, ( (String) GetActionParameters( ViewToWindow ) ) );
      //stringButtonHeaderName = (String) GetActionParameters( ViewToWindow );

      return 0;

   } // GetButtonHeaderName

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: GetWindowsUserName
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public String
   GetWindowsUserName( String stringUserName,
                       int ulMaxLengthOfName )
   {
      int lth;

      lth = ulMaxLengthOfName;

      if ( GetUserName( stringUserName, lth ) == 0 )
         stringUserName = "";

      return stringUserName;
   } // GetWindowsUserName

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: CallObjectOperation
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   CallObjectOperation( View   returnView,
                        View   ViewToWindow,
                        String stringObject,
                        String stringOperationName )
   {
      String stringOperation;
      String stringPrefix;
      zFARPROC_DYNOBJ lpfnDynRoutine;
      int  nRC = -1;
      LPLIBRARY hLibrary;

      hLibrary = 0;

      zstrcpy( stringPrefix, "o" );
      zstrcat( stringPrefix, stringObject );
      zstrcat( stringPrefix, "_" );

      zstrcpy( stringOperation, stringPrefix );
      zstrcat( stringOperation, stringOperationName );

      lpfnDynRoutine = (zFARPROC_DYNOBJ)
         GetOperationDynamicCallAddress( ViewToWindow, &hLibrary,
                                         "LODOPS_R", stringOperation, "Reports" );
      if ( lpfnDynRoutine )
      {
         nRC = (*lpfnDynRoutine)( returnView, (View ) ViewToWindow );
      }

      return nRC;

   } // CallObjectOperation

   // FindItemByName - enumerates the subkeys of a given key and the associated
   //    values.
   // hKey - key whose subkeys and values are to be enumerated

   public String
   FindItemByName( HKEY    hKey,
                   String  stringValueName,
                   DWORD   dwReturnType,
                   String  stringReturnData,
                   DWORD   dwMaxReturnLth )
   {
      char     stringKey;
      char     stringClass = "";           // buffer for class name
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
      int lRC;
      zBOOL bFound = FALSE;

      char  stringValue;
      DWORD dwValue = MAX_PATH;
      char  stringBuff;

      // Get the class name and the value count.
      RegQueryInfoKey( hKey,                  // key handle
                       stringClass,           // buffer for class name
                       dwClassName,          // length of class string
                       null,                  // reserved
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
      SetCursor( LoadCursor( null, IDC_WAIT ) );
      for ( k = 0, dwRC = ERROR_SUCCESS; dwRC == ERROR_SUCCESS; k++ )
      {
         dwRC = RegEnumKey( hKey, k, stringKey, MAX_PATH );
         //if ( dwRC == (DWORD) ERROR_SUCCESS )
         //{
         //   TraceLineS( "IDE_CLASS?: ", stringKey );
         //}
      }

      SetCursor( LoadCursor( null, IDC_ARROW ) );

      // Enumerate the key values.
      SetCursor( LoadCursor( null, IDC_WAIT ) );

      if ( dwValues )
      {
         for ( j = 0, dwRC = ERROR_SUCCESS; j < dwValues; j++ )
         {
            dwValue = MAX_PATH;
            stringValue = "";
            dwRC = RegEnumValue( hKey, j, stringValue,
                                 &dwValue,
                                 null,
                                 &dwType, // &dwType,
                                 null,    // &bData,
                                 null );  // &bcData

            if ( dwRC != (DWORD) ERROR_SUCCESS &&
                 dwRC != ERROR_INSUFFICIENT_BUFFER )
            {
               wsprintf( stringBuff,
                         "Line:%d 0 based index = %d, dwRC = %d, "
                         "ValueLen = %d",
                         __LINE__, j, dwRC, dwValue );
               //TraceLineS( "Debug", stringBuff );
            }

            stringBuff = "";

            if ( dwType == dwReturnType && zstrcmp( stringValueName, stringValue ) == 0 )
            {
               bFound = TRUE;

               lRC = RegQueryValueEx( hKey,               // handle to key
                                      stringValue,
                                      0,                  // reserved must be null
                                      &dwType,            // value type
                                      (LPBYTE) stringReturnData, // data buffer
                                      &dwMaxReturnLth );  // data buffer size
               if ( zstrlen( stringValue ) == 0 )
                  zstrcpy( stringValue, "(Default)" );

               //TraceLineS( "Found Key ===> ", stringValue );
               break;
            }

            // Add each value to a list box.
            if ( zstrlen( stringValue ) == 0 )
               zstrcpy( stringValue, "(Default)" );

            wsprintf( stringBuff, "%d) %s ", j, stringValue );
            // TraceLineS( "???:  ", stringBuff );
         }
      }

      SetCursor( LoadCursor( null, IDC_ARROW ) );
      return bFound );
   }

   public String
   GetRegistryImagingValue( String stringValueName,
                            String stringCLSID_ID,
                            DWORD   dwReturnType,
                            String  stringReturnData,
                            DWORD   dwMaxReturnLth )
   {
      HKEY  hConnect;
      HKEY  hClassesRoot;
      HKEY  hSoftware;
      HKEY  hKeyClasses;
      HKEY  hKeyCLSID;
      HKEY  hKeyID;
      HKEY  hKeyLocalServer;
      int nRC = -1;

      *stringReturnData = 0;  // initialize

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
         return nRC;
      }

      // get thr CLSID here
      lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx Classes", " failed"  );
         return nRC;
      }

      // get thr CLSID here
      lRC = RegOpenKeyEx( hKeyClasses, "CLSID", 0, KEY_READ, &hKeyCLSID );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx CLSID", " failed"  );
         return nRC;
      }

      // get thr CLSID here
      lRC = RegOpenKeyEx( hKeyCLSID, stringCLSID_ID, 0, KEY_READ, &hKeyID );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hKeyID", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hKeyID, "LocalServer32", 0, KEY_READ, &hKeyLocalServer );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hKeyLocalServer", " failed"  );
         return nRC;
      }

      nRC = FindItemByName( hKeyLocalServer, stringValueName, dwReturnType,
                            stringReturnData, dwMaxReturnLth );
      return nRC;
   }

   public String
   GetRegistryImagingPrintValue( String stringValueName,
                                 String stringCLSID_ID,
                                 DWORD   dwReturnType,
                                 String  stringReturnData,
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
      int nRC = -1;

      *stringReturnData = 0;  // initialize

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
         return nRC;
      }

      // get thr CLSID here
      lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx Classes", " failed"  );
         return nRC;
      }

      // get the TIF.Document here
      lRC = RegOpenKeyEx( hKeyClasses, "TIFImage.Document", 0, KEY_READ, &hKeyTif );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx TIF.Docuement", " failed"  );
         return nRC;
      }

      // get the shell here
      lRC = RegOpenKeyEx( hKeyTif, "shell", 0, KEY_READ, &hKeyShell );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx shell", " failed"  );
         return nRC;
      }

      // get the print key here
      lRC = RegOpenKeyEx( hKeyShell, "print", 0, KEY_READ, &hKeyPrint );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx print:", ":failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx command ", " failed"  );
         return nRC;
      }

      nRC = FindItemByName( hKeyCommand, stringValueName, dwReturnType,
                            stringReturnData, dwMaxReturnLth );

      TraceLineS( stringValueName, stringReturnData );

      return nRC;
   }

   public String
   GetRegistryImagingViewValue( String stringValueName,
                                String stringCLSID_ID,
                                DWORD   dwReturnType,
                                String  stringReturnData,
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
      int nRC = -1;

      *stringReturnData = 0;  // initialize

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
         return nRC;
      }
      // get thr CLSID here
      lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx Classes", " failed"  );
         return nRC;
      }
      // get the TIF.Document here
      lRC = RegOpenKeyEx( hKeyClasses, "TIFImage.Document", 0, KEY_READ, &hKeyTif );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx TIF.Docuement", " failed"  );
         return nRC;
      }
      // get the shell here
      lRC = RegOpenKeyEx( hKeyTif, "shell", 0, KEY_READ, &hKeyShell );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx shell", " failed"  );
         return nRC;
      }
      // get the print key here
      lRC = RegOpenKeyEx( hKeyShell, "open", 0, KEY_READ, &hKeyPrint );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx open:", ":failed"  );
         return nRC;
      }
      lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx command ", " failed"  );
         return nRC;
      }

      nRC = FindItemByName( hKeyCommand, stringValueName, dwReturnType,
                            stringReturnData, dwMaxReturnLth );
      TraceLineS( stringValueName, stringReturnData );

      return nRC;
   }

   public String
   GetRegistryHTMLViewValue( String stringValueName,
                             String stringCLSID_ID,
                             DWORD   dwReturnType,
                             String  stringReturnData,
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
      int nRC = -1;

      *stringReturnData = 0;  // initialize

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
         return nRC;
      }
      // get thr CLSID here
      lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx Classes", " failed"  );
         return nRC;
      }
      // get the hKeyHtml here
      lRC = RegOpenKeyEx( hKeyClasses, "htmlfile", 0, KEY_READ, &hKeyHtml );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx htmlfile", " failed"  );
         return nRC;
      }
      // get the shell here
      lRC = RegOpenKeyEx( hKeyHtml, "shell", 0, KEY_READ, &hKeyShell );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx shell", " failed"  );
         return nRC;
      }
      // get the print key here
      lRC = RegOpenKeyEx( hKeyShell, "open", 0, KEY_READ, &hKeyPrint );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx open:", ":failed"  );
         return nRC;
      }
      lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx command ", " failed"  );
         return nRC;
      }

      nRC = FindItemByName( hKeyCommand, stringValueName, dwReturnType,
                            stringReturnData, dwMaxReturnLth );
      TraceLineS( stringValueName, stringReturnData );

      return nRC;
   }

   public String
   GetRegistryGeneralValue( String stringValueName,
                            String  stringFileType,
                            String stringCLSID_ID,
                            DWORD   dwReturnType,
                            String  stringReturnData,
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
      int nRC = -1;

      *stringReturnData = 0;  // initialize

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
         return nRC;
      }
      // get thr CLSID here
      lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx Classes", " failed"  );
         return nRC;
      }
      // get the hKeyHtml here
      lRC = RegOpenKeyEx( hKeyClasses, stringFileType, 0, KEY_READ, &hKeyHtml );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx stringFileType", " failed"  );
         return nRC;
      }
      // get the shell here
      lRC = RegOpenKeyEx( hKeyHtml, "shell", 0, KEY_READ, &hKeyShell );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx shell", " failed"  );
         return nRC;
      }
      // get the print key here
      lRC = RegOpenKeyEx( hKeyShell, "open", 0, KEY_READ, &hKeyPrint );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx open:", ":failed"  );
         return nRC;
      }
      lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx command ", " failed"  );
         return nRC;
      }

      nRC = FindItemByName( hKeyCommand, stringValueName, dwReturnType,
                            stringReturnData, dwMaxReturnLth );
      TraceLineS( stringValueName, stringReturnData );

      return nRC;
   }

   public String
   GetRegistryPrintValue( String stringValueName,
                          String  stringFileType,
                          String stringCLSID_ID,
                          DWORD   dwReturnType,
                          String  stringReturnData,
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
      int nRC = -1;

      *stringReturnData = 0;  // initialize

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_LOCAL_MACHINE, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_LOCAL_MACHINE", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, "Software", 0, KEY_READ, &hSoftware );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx hSoftware", " failed"  );
         return nRC;
      }

      // get thr CLSID here
      lRC = RegOpenKeyEx( hSoftware, "Classes", 0, KEY_READ, &hKeyClasses );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx Classes", " failed"  );
         return nRC;
      }

      // get the hKeyHtml here
      lRC = RegOpenKeyEx( hKeyClasses, stringFileType, 0, KEY_READ, &hKeyHtml );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx stringFileType", " failed"  );
         return nRC;
      }

      // get the shell here
      lRC = RegOpenKeyEx( hKeyHtml, "shell", 0, KEY_READ, &hKeyShell );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx shell", " failed"  );
         return nRC;
      }

      // get the print key here
      lRC = RegOpenKeyEx( hKeyShell, "print", 0, KEY_READ, &hKeyPrint );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx print:", ":failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hKeyPrint, "command", 0, KEY_READ, &hKeyCommand );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx print command ", " failed"  );
         return nRC;
      }

      nRC = FindItemByName( hKeyCommand, stringValueName, dwReturnType,
                            stringReturnData, dwMaxReturnLth );
      TraceLineS( stringValueName, stringReturnData );

      return nRC;
   }

   public String
   GetRegistryCLSID( String  stringReturnData, String stringClassName )
   {
      HKEY  hConnect;
      HKEY  hClassesRoot;
      HKEY  hCLSID;
      HKEY  hKeyCLSID;
      DWORD dwReturnTypeCLSID = REG_SZ;
      DWORD dwMaxReturnCLSIDLth = 128;
      int nRC = -1;

      int lRC = RegConnectRegistry( null, HKEY_LOCAL_MACHINE, &hConnect );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegConnectRegistry", " failed"  );
         return nRC;
      }

      // open the local key
      lRC = RegOpenKeyEx( HKEY_CLASSES_ROOT, 0, 0, KEY_READ, &hClassesRoot );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx HKEY_CLASSES_ROOT", " failed"  );
         return nRC;
      }

      lRC = RegOpenKeyEx( hClassesRoot, stringClassName, 0, KEY_READ, &hCLSID );
      if ( lRC != ERROR_SUCCESS )
      {

         TraceLineS( "RegOpenKeyEx Failed for: ", stringClassName  );
         return nRC;
      }

      // get thr CLSID here
      lRC = RegOpenKeyEx( hCLSID, "CLSID", 0, KEY_READ, &hKeyCLSID );
      if ( lRC != ERROR_SUCCESS )
      {
         TraceLineS( "RegOpenKeyEx CLSID", " failed"  );
         return nRC;
      }

      // get the CLSID Value
      nRC = FindItemByName( hKeyCLSID, "", dwReturnTypeCLSID, stringReturnData, dwMaxReturnCLSIDLth );
      TraceLineS( "CLSID ReturnValue", stringReturnData );
      return 0;
   }

   public String
   GetImagingPath( View  vSubtask, int lFlag, String stringTarget )
   {
      char   stringReturn;
      char   stringCLSID;
      int nRC = FALSE;

      stringReturn = "";  // initially empty
      if ( lFlag == 1 )   //open for edit
      {
         GetRegistryCLSID( stringCLSID, "TIFImage.Document" );
         GetRegistryImagingValue( "", stringCLSID, REG_SZ, stringReturn, sizeof( stringReturn ) );
      }

      if ( lFlag == 2 ) //open for print
      {
         GetRegistryCLSID( stringCLSID, "TIFImage.Document" );
         nRC = GetRegistryImagingPrintValue( "", stringCLSID, REG_EXPAND_SZ, stringReturn, sizeof( stringReturn ) );
         if ( nRC == FALSE )
         {
            nRC = GetRegistryImagingPrintValue( "", stringCLSID, REG_SZ, stringReturn, sizeof( stringReturn ) );
         }
      }
      // zstrcpy( stringTarget, stringReturn );
      if ( lFlag == 3 ) //open for view
      {
         GetRegistryCLSID( stringCLSID, "TIFImage.Document" );
         nRC = GetRegistryImagingViewValue( "", stringCLSID, REG_EXPAND_SZ, stringReturn, sizeof( stringReturn ) );
         if ( nRC == FALSE )
         {
            nRC = GetRegistryImagingViewValue( "", stringCLSID, REG_SZ, stringReturn, sizeof( stringReturn ) );
         }
      }

      zstrcpy( stringTarget, stringReturn );
      return 0;
   }

   public String
   GetHTMLPath( View  vSubtask, int lFlag, String stringTarget )
   {
      char   stringReturn;
      char   stringCLSID;
      int nRC = FALSE;

      stringReturn[0] = 0;
      lFlag = 3; // set flag to 3 as that is all we currently support
      if ( lFlag == 3 ) //open for view
      {
         GetRegistryCLSID( stringCLSID, "htmlfile" );
         nRC = GetRegistryHTMLViewValue( "", stringCLSID, REG_SZ, stringReturn, sizeof( stringReturn ) );
         TraceLineS( "Flag 3 stringCLSID !", stringCLSID ) ;
         TraceLineS( "Flag 3 Return !", stringReturn ) ;
         if ( nRC == FALSE )
         {
            // for win98 in case we are not in win2K
            nRC = GetRegistryHTMLViewValue( "", stringCLSID, REG_EXPAND_SZ, stringReturn, sizeof( stringReturn ) );
            TraceLineS( "Flag 3C stringCLSID !", stringCLSID ) ;
            TraceLineS( "Flag 3C Return !", stringReturn ) ;
         }
      }
            TraceLineS( "RIGHT BEFORE STRCopy:", stringReturn ) ;
      zstrcpy( stringTarget, stringReturn );
            TraceLineS( "RIGHT AFTER STRCopy", "") ;
      return 0;
   }
    **/

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: FindStringInAttribute
   //
   //    Find a string within a string attribute. Not case sensitive.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   FindStringInAttribute( String stringSearchString,
                          View   view,
                          String entityName,
                          String attributeName )
   {
      String stringAttributeValue = null;
      String stringFoundValue;

      // Look for a match on the string stringSearchString within the attribute.
      // Return 0 if found.
      // Return -1 if not found.
      stringAttributeValue = GetStringFromAttribute( stringAttributeValue, view, entityName, attributeName );
      stringFoundValue = zstrstr( stringAttributeValue, stringSearchString );
      if ( stringFoundValue == null )
         return -1;  // the string was not found
      else
         return 0;   // the string was found

   } // FindStringInAttribute

   public int
   ConvertExternalValueOfAttribute( StringBuilder lpReturnedString,
                                    String srcString,
                                    View   lpView,
                                    String entityName,
                                    String attributeName )
   {
      // TODO - Convert code from "C"
      return( 0 );
   }

 /**
   public String
   GetGeneralPath( View  vSubtask, int lFlag, String stringFileType, String stringTarget )
   {
      char   stringReturn;
      char   stringCLSID;
      int nRC = FALSE;

      stringReturn = "";
      lFlag = 3; // set flag to 3 as that is all we currently support
      if ( lFlag == 3 ) //open for view
      {
         GetRegistryCLSID( stringCLSID, stringFileType );
         // nRC = GetRegistryHTMLViewValue( "", stringCLSID, REG_SZ, stringReturn, sizeof( stringReturn ) );
         nRC = GetRegistryGeneralValue( "", "rtffile", stringCLSID, REG_SZ, stringReturn, sizeof( stringReturn ) );

         TraceLineS( "Flag 3 stringCLSID !", stringCLSID ) ;
         TraceLineS( "Flag 3 Return !", stringReturn ) ;
         if ( nRC == FALSE )
         {
            // for win98 in case we are not in win2K
            nRC = GetRegistryGeneralValue( "", "rtffile", stringCLSID, REG_EXPAND_SZ,
                                           stringReturn, sizeof( stringReturn ) );
            TraceLineS( "Flag 3C stringCLSID !", stringCLSID ) ;
            TraceLineS( "Flag 3C Return !", stringReturn ) ;
         }
      }

      TraceLineS( "RIGHT BEFORE STRCopy:", stringReturn ) ;
      stringTarget = zstrcpy( stringTarget, stringReturn );
      TraceLineS( "RIGHT AFTER STRCopy", "" ) ;
      return stringTarget;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ConvertExternalValueOfAttribute
   //
   //    Convert an external value for an attribute to its internal value.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   ConvertExternalValueOfAttribute( String lpReturnedString,
                                    String srcString,
                                    View   lpView,
                                    String entityName,
                                    String attributeName )
   {
      zVIEW  wWebXfer;
      zVIEW  vDynamicT;
      zVIEW  vQualObject;
      zVIEW  zqFrameOrig;
      zVIEW  zqFrame;
      LPVIEWENTITY lpViewEntity;
      LPVIEWATTRIB lpViewAttrib;
      LPDOMAIN     lpDomain;
      String  DataType;
      String  Msg;
      String  SavedTableName;
      String  stringYearIndicator;
      int  lInternalTableValue;
      int nLth;
      int nRC;

      GetViewByName( wWebXfer, "wWebXfer", lpView, zLEVEL_TASK );
      lpReturnedString = "";
      lpViewEntity = String MiGetViewEntityForView( lpView, entityName );
      if ( lpViewEntity == 0 )
         return -16;

      // Position on attribute.
 #ifdef VIEWENTITY_OD
      lpViewAttrib = String zGETPTR( lpViewEntity->hFirstOD_Attrib );
      nRC = 1;
      while ( lpViewAttrib > 0 && nRC > 0 )
      {
         if ( zstrcmp( lpViewAttrib->stringName, attributeName ) == 0 )
            nRC = 0;

         if ( nRC > 0 )
            lpViewAttrib = String zGETPTR( lpViewAttrib->hNextOD_Attrib );
      }
 #else
      lpViewAttrib = String zGETPTR( lpViewEntity->hFirstViewAttrib );
      nRC = 1;
      while ( lpViewAttrib > 0 && nRC > 0 )
      {
         if ( zstrcmp( lpViewAttrib->stringName, attributeName ) == 0 )
            nRC = 0;

         if ( nRC > 0 )
            lpViewAttrib = String zGETPTR( lpViewAttrib->hNextViewAttrib );
      }
 // #endif
      if ( nRC > 0 )
      {
         MessageSend( lpView, "", "Data Conversion",
                      "The attribute specified was not found.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      // If input is null, simply return because output has already been set to null.
      if ( *srcString == 0 )
         return 0;

      nLth = zstrlen( srcString );

      // Process depending on whether or not the Domain is a Table.
      lpDomain = (LPDOMAIN) zGETPTR( lpViewAttrib->hDomain );
      if ( lpDomain->cDomainType == 'T' )
      {
         if ( *(lpDomain->stringDomainOper) == 0 )
         {
            // The domain is a static table so convert the value through the table interface.
            if ( lpDomain->cType == zTYPE_INTEGER )
            {
               nRC = TableEntryExtToInt( &lInternalTableValue, lpView, lpDomain, 0, srcString );
               zltoa( lInternalTableValue, lpReturnedString );
            }
            else
               nRC = TableEntryExtToInt( lpReturnedString, lpView, lpDomain, 0, srcString );  // Internal value is STRING.
            if ( nRC < 0 )
            {
               zstrcpy( Msg, "Invalid input value for attribute, " );
               zstrcat( Msg, attributeName );
               zstrcat( Msg, "." );
               MessageSend( lpView, "", "Data Conversion",
                            Msg,
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }
         }
         else
         {
            if ( zstrcmp( lpDomain->stringName, "FAISIR_DynamicTableSingle" ) == 0 )
            {
               // Get Year Indicator value as GeneralParameter in zqFrame object.
               // If we get to this section of code, we must have come from zqFrame and the lpView is actually
               // the zqFrame view.
               // (This is very similar to the code in DomainC.)
               GetViewByName( zqFrameOrig, "zqFrame", lpView, zLEVEL_TASK );
               CreateViewFromView( zqFrame, zqFrameOrig );
               nRC = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "AttributeName", "YearIndicator", "" );
               if ( nRC < zCURSOR_SET )
                  *stringYearIndicator = 0;
               else
                  GetStringFromAttribute( stringYearIndicator, zqFrame, "GeneralParameter", "Value" );
               if ( *stringYearIndicator == 0 )
               {
                  MessageSend( lpView, "", "Data Validation",
                               "A YearIndicator value must be specified as a General Parameter in the Query.",
                               zMSGQ_DOMAIN_ERROR, 0 );
                  return zCALL_ERROR;
               }

               zstrcpy( SavedTableName, "X_" );
               zstrcat( SavedTableName, lpDomain->stringName );
               zstrcat( SavedTableName, stringYearIndicator );    // Build the concatenated name.

               // Either get existing view or activate new one.
               nRC = GetViewByName( vDynamicT, SavedTableName, lpView, zLEVEL_TASK );
               if ( nRC < 0 )
               {
                  // Set up Qualification object for YearIndicator.
                  SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", lpView, zMULTIPLE );
                  CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
                  SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "FAISIRDomain" );
                  CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
                  SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "FAISIRDomain" );
                  SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "YearIndicator" );
                  SetAttributeFromString( vQualObject, "QualAttrib", "Value", stringYearIndicator );
                  SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );

                  // Activate the Domains for the YearIndicator.
                  nRC = ActivateObjectInstance( vDynamicT, "mFAISIRD", zqFrame, vQualObject, zMULTIPLE );
                  DropView( vQualObject );
                  if ( nRC < 0 )
                  {
                     MessageSend( lpView, "", "Data Validation",
                                  "A YearIndicator value must be specified as a General Parameter in the Query.",
                                  zMSGQ_DOMAIN_ERROR, 0 );
                     return zCALL_ERROR;
                  }

                  SetNameForView( vDynamicT, SavedTableName, 0, zLEVEL_APPLICATION );
                  CreateViewFromViewForTask( &vDynamicT, vDynamicT, 0 );
                  SetNameForView( vDynamicT, SavedTableName, lpView, zLEVEL_TASK );
               }

               // Position on correct table entry.
               nRC = SetCursorFirstEntityByString( vDynamicT, "FAISIRDomain", "Name", attributeName, "" );
               if ( nRC >= zCURSOR_SET )
                  nRC = SetCursorFirstEntityByString( vDynamicT, "FAISIRDomainValue", "ExternalDescription", srcString, "" );
               if ( nRC < 0 )
               {
                  zstrcpy( Msg, "Invalid input value for attribute, " );
                  zstrcat( Msg, attributeName );
                  zstrcat( Msg, "." );
                  MessageSend( lpView, "", "Data Conversion",
                               Msg,
                               zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  return -1;
               }

               GetStringFromAttribute( lpReturnedString, vDynamicT, "FAISIRDomainValue", "InternalStringValue" );

               DropView( zqFrame );
            }
            else
            {
               // The domain is a regular dynamic table so use the object in memory or activate it.
               zstrcpy( SavedTableName, "X_" );
               zstrcat( SavedTableName, lpDomain->stringName );
               nRC = GetViewByName( vDynamicT, SavedTableName, lpView, zLEVEL_TASK );
               if ( nRC < 0 )
                  nRC = GetViewByName( vDynamicT, SavedTableName, lpView, zLEVEL_APPLICATION );

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
                  SetAttributeFromString( vQualObject, "QualAttrib", "Value", lpDomain->stringName );
                  SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
                  nRC = ActivateObjectInstance( &vDynamicT, "DOMAINT", lpView,
                                                vQualObject, zSINGLE | zLEVEL_APPLICATION );
                  SetNameForView( vDynamicT, SavedTableName, lpView, zLEVEL_APPLICATION );
               }

               // Locate the entry in the table by external value and return the internal value.
               nRC = SetCursorFirstEntityByString( vDynamicT, "DomainValue",
                                                   "ExternalDescription",
                                                   srcString, 0 );
               if ( nRC < 0 )
               {
                  zstrcpy( Msg, "Invalid input value for attribute, " );
                  zstrcat( Msg, attributeName );
                  zstrcat( Msg, "." );
                  MessageSend( lpView, "", "Data Conversion",
                               Msg,
                               zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  return -1;
               }

               GetStringFromAttribute( lpReturnedString, vDynamicT, "DomainValue", "InternalStringValue" );
            }
         }
      }
      else
      {
         // If Domain Type is not Table, use data type for conversion through wWebXfer attribute.
         DataType = lpDomain->cType;
         if ( DataType == 'L' )
         {
            nRC = SetAttributeFromVariable( wWebXfer, "Root", "WorkInteger",
                                           srcString, zTYPE_STRING,
                                           nLth, 0, zUSE_DEFAULT_CONTEXT );
            if ( nRC >= 0 )
               GetStringFromAttribute( lpReturnedString, wWebXfer, "Root", "WorkInteger" );
            else
               return -1;
         }
         else
         if ( DataType == 'T' )
         {
            nRC = SetAttributeFromVariable( wWebXfer, "Root", "WorkDate",
                                            srcString, zTYPE_STRING,
                                            nLth, "M/D/YYYY", 0 );
            if ( nRC >= 0 )
               GetStringFromAttribute( lpReturnedString, wWebXfer, "Root", "WorkDate" );
            else
               return -1;
         }
         else
         if ( DataType == 'D' )
         {
            nRC = SetAttributeFromVariable( wWebXfer, "Root", "WorkDate",
                                            srcString, zTYPE_STRING,
                                            nLth, "M/D/YYYY", 0 );
            if ( nRC >= 0 )
               GetStringFromAttribute( lpReturnedString, wWebXfer, "Root", "WorkDate" );
            else
               return -1;
         }
         else
         if ( DataType == 'M' )
         {
            nRC = SetAttributeFromVariable( wWebXfer, "Root", "WorkDecimal",
                                            srcString, zTYPE_STRING,
                                            nLth, 0, zUSE_DEFAULT_CONTEXT );
            if ( nRC >= 0 )
               GetStringFromAttribute( lpReturnedString, wWebXfer, "Root", "WorkDecimal" );
            else
               return -1;
         }
         else
            zstrcpy( lpReturnedString, srcString );
      }

      return 0;
   } // ConvertExternalValueOfAttribute
 **/

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AddSpacesToString
   //
   //    Insert spaces within a Zeidon string name where capital letters exist.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public String
   AddSpacesToString( String stringZeidonName  )
   {
      StringBuilder sb = new StringBuilder( stringZeidonName );
      int k;

      for ( k = 1; k < sb.length( ); k++ )
      {
         if ( sb.charAt( k ) >= 'A' && sb.charAt( k ) <= 'Z' )
         {
            sb.insert( k, ' ' );
            k++;
         }
      }

      return sb.toString( );

   } // AddSpacesToString

 /**
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: GetDataTypeForAttribute
   //
   //    Return the Data Type for an attribute
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   GetDataTypeForAttribute( String stringDataType,
                            View   lpView,
                            String entityName,
                            String attributeName )
   {
      LPVIEWENTITY lpViewEntity;
      LPVIEWATTRIB lpViewAttrib;
      int nRC;

      lpViewEntity = String zGETPTR( MiGetViewEntityForView( lpView, entityName ) );
      if ( lpViewEntity == 0 )
         return -16;

      // Position on attribute.
   #ifdef VIEWENTITY_OD
      lpViewAttrib = String zGETPTR( lpViewEntity->hFirstOD_Attrib );
      nRC = 1;
      while ( lpViewAttrib > 0 && nRC > 0 )
      {
         if ( zstrcmp( lpViewAttrib->stringName, attributeName ) == 0 )
            nRC = 0;

         if ( nRC > 0 )
            lpViewAttrib = String zGETPTR( lpViewAttrib->hNextOD_Attrib );
      }
   #else
      lpViewAttrib = String zGETPTR( lpViewEntity->hFirstViewAttrib );
      nRC = 1;
      while ( lpViewAttrib > 0 && nRC > 0 )
      {
         if ( zstrcmp( lpViewAttrib->stringName, attributeName ) == 0 )
            nRC = 0;

         if ( nRC > 0 )
            lpViewAttrib = String zGETPTR( lpViewAttrib->hNextViewAttrib );
      }
   #endif
      if ( nRC > 0 )
      {
         MessageSend( lpView, "", "GetDataTypeForAttribute",
                      "The attribute specified was not found.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      // Set single character datatype followed by a string terminator.
      *stringDataType = lpViewAttrib->hDomain->cType;
      *(stringDataType + 1) = 0;

      return 0;
   } // GetDataTypeForAttribute
 **/

   //
   int
   ParseOutEntityAttribute( String entityDotAttribute,
                            StringBuilder entityName,
                            StringBuilder attributeName )
   {
      int k;
      int lSkipLth;

      // Initialize entityName and attributeName.
      entityName.replace( 0, -1, entityDotAttribute );
      attributeName.delete( 0, -1 );
      // entityDotAttribute is pointing to the first character of the entity name on entry to this routine.
      // Parse out Entity Name

      for ( k = 0; k < entityName.length( ); k++ )
      {
         char ch = entityName.charAt( k );
         if ( ch == '.' || ch == ']' || ch == '}' )
         {
            entityName.setCharAt( k, '\0' );
            if ( ch == '}' )
               return -2;

            if ( ch != ']' )  // there is an attribute, so keep going
            {
               int j = 0;
               k++;

                  // Parse out Attribute Name
               ch = entityDotAttribute.charAt( k );
               while ( ch != ']' && ch != '}' )
               {
                  if ( ch == '}' )
                     return -2;

                  attributeName.setCharAt( j, ch );
                  j++;
                  k++;
                  ch = entityDotAttribute.charAt( k );
               }

               attributeName.setCharAt( k, '\0' );
            }
         }
      }

      lSkipLth = k + 1;  // TODO not sure this translation to java is exactly right for SkipLth
      return lSkipLth;
   }

   int
   ConvertCharacterString( StringBuilder sbTarget,
                           StringBuilder sbSource,
                           StringBuilder sbOrigMemory,
                           int  nFileType )  // 1-Text   2-HTML
   {
      char   ch;
      int    lTabCount;
      int    i;  // index to sbTarget
      int    j;  // index to sbSource
      int    k;

      // This code checks for "carriage return/line feed" combinations in the
      // text and inserts the correct \par and \tab strings in the target text.
   // pchTarget = *sbTarget;

      // First, determine if the start of the text is preceded by tab characters and if so, count them.
      lTabCount = 0;

      /** TODO - figure this out and implement java version
      String pchBack = sbOrigMemory - 5;
      while ( zstrncmp( sbOrigMemory, "\\tab", 4 ) == 0 )
      {
         lTabCount++;
         pchBack = pchBack - 5;
      }
       **/

      // Copy the characters, inserting \par and \tab strings as necessary for new lines.
      for ( i = 0, j = 0; (ch = sbSource.charAt( j )) != '\0'; j++ )
      {
         // Search for carriage return/line feed and insert \par and \tab strings.
         if ( ch == 13 && sbSource.charAt( j + 1 ) == 10 )
         {
            // Copy carriage control and line feed characters.
            sbTarget.setCharAt( i++ , sbSource.charAt( j++ ) );
            sbTarget.setCharAt( i++ , sbSource.charAt( j++ ) );

            // Insert \par and \tab characters.
            if ( nFileType == 1 )
            {
               i = zstrcpy( sbTarget, i, "\\par " );
            }
            else
            {
               i = zstrcpy( sbTarget, i, "<br />" );
            }

            for ( k = 0; k < lTabCount; k++ )
            {
               i = zstrcpy( sbTarget, i, "\\tab " );
            }
         }
         else
         {
            sbTarget.setCharAt( i++ , sbSource.charAt( j++ ) );
         }
      }

      sbTarget.setCharAt( i++ , '\0' );

      return( 0 );
   }

 /**
   int
   CopyWithInsertMemoryArea( zVIEW   vResultSet,
                             StringBuilder sbTemplateMemory,
                             int     nTemplateMemoryPosition,
                             String  stringInnerLoopEntityName,
                             String  pchEmbeddedImages,
                             int     nFileType )  // 1-Text   2-HTML
   {
      StringBuilder sbLoopEntityName;
      StringBuilder sbEntityName;
      StringBuilder sbAttributeName;
      StringBuilder sbVariableBuffer;
      int    nTemplateMemoryLth;
      int    lRBracketPos;
      int    lSkipLth;
      int    k;
      int    nRC;

      nTemplateMemoryLth = sbTemplateMemory.length( );
      for ( k = nTemplateMemoryPosition; k < nTemplateMemoryLth; k++ )
      {
         if ( sbTemplateMemory.charAt( k ) == '[' )
         {
            lRBracketPos = zstrchr( sbTemplateMemory, ']' );
            if ( lRBracketPos >= 0 )
            {
            sbTemplateMemory.setCharAt( lRBracketPos, '\0' );
               if ( (zstrchr( sbTemplateMemory, '<' )) >= 0 || (zstrchr( sbTemplateMemory, '>' )) >= 0 || (zstrchr( sbTemplateMemory, '/' )) >= 0 )
               {
                  sbTemplateMemory.setCharAt( lRBracketPos, ']' );
                  lRBracketPos = 0;
               }
               else
               {
                  sbTemplateMemory.setCharAt( lRBracketPos, ']' );
               }
            }

            if ( lRBracketPos >= 0 && sbTemplateMemory.charAt( k + 1 ) == 'Z' && sbTemplateMemory.charAt( k + 2 ) == ':' )  // "[Z:"
            {
               if ( sbTemplateMemory.charAt( k + 3 ) == '#' )  // "[Z:#"
               {
                  if ( sbTemplateMemory.charAt( k + 4 ) == 'C' )  // "[Z:#C"
                  {
                     // Just set the cursor to the next entity.
                    k += 6;
                    nTemplateMemoryPosition = k;
                     SetCursorNextEntity( vResultSet, stringInnerLoopEntityName, "" );
                  }
                  else
                  {
                     if ( sbTemplateMemory.charAt( k + 4 ) == 'S' && sbTemplateMemory.charAt( k + 5 ) == ':' )  // "[Z:#S:"
                     {
                        // Parse out the Loop Entity name.
                      k += 6;
                      lSkipLth = ParseOutEntityAttribute( sbTemplateMemory.substring( k ), sbLoopEntityName, sbAttributeName );
                        if ( lSkipLth < 0 )
                           return lSkipLth;

                        k += lSkipLth;
                        nTemplateMemoryPosition = k;

                        // Identify the beginning and end of the data area to be repeated in variables:
                        //   pchMemoryLoopStartArea and pchMemoryLoopEndArea.
                        while ( sbTemplateMemory.charAt( k ) != ']' && nTemplateMemoryPosition < nTemplateMemoryLth )
                        {
                         nTemplateMemoryPosition++;
                        }

                        nTemplateMemoryPosition++;

                        while ( zstrncmp( sbTemplateMemory, nTemplateMemoryPosition, "[Z:#E]", 6 ) != 0 && nTemplateMemoryPosition < nTemplateMemoryLth )
                        {
                         nTemplateMemoryPosition++;
                        }

                        nTemplateMemoryLth = sbTemplateMemory.length( );

                        // Process the looping on the specified Entity Name.
                        nRC = SetCursorFirstEntity( vResultSet, sbLoopEntityName.toString( ), "" );
                        while ( nRC >= zCURSOR_SET )
                        {
                           // Perform the normal mapping code here.
                           nRC = CopyWithInsertMemoryArea( vResultSet, sbTemplateMemory, nTemplateMemoryPosition,
                                                           sbLoopEntityName.toString( ), pchEmbeddedImages, nFileType );

                           if ( nRC < 0 )
                              return( nRC );

                           nRC = SetCursorNextEntity( vResultSet, sbLoopEntityName.toString( ), "" );
                        }

                        // Skip repeating area and the [Z:#E] characters ending the loop.
                        k += 6;
                        nTemplateMemoryPosition = k;
                     }
                     else
                     {
                        // The else condition is ignored as an error.
                        while ( sbTemplateMemory.charAt( k ) != ']' && k < nTemplateMemoryLth )
                        {
                         k++;
                        }

                        k++;
                        nTemplateMemoryPosition = k;
                     }
                  }
               }
               else
               {
                  // Get the Entity and Attribute Names.
                  // We save original memory position in pchOrigMemory so the
                  // subroutine can search for preceding tab characters.
                  k += 3;
                  lSkipLth = ParseOutEntityAttribute( sbTemplateMemory.substring( k ), sbEntityName, sbAttributeName );
                  if ( lSkipLth < 0 )
                     return lSkipLth;

                  k += lSkipLth;
                  nTemplateMemoryPosition = k;

                  // Get the data from the object.
                  if ( CheckExistenceOfEntity( vResultSet, sbEntityName.toString( ) ) >= zCURSOR_SET )
                  {
                     if ( zstrcmp( sbAttributeName.toString( ), "StudentAccountNotClearedText" ) == 0 )
                        zstrcpy( sbVariableBuffer, "Stop" );    // DonC ???

                     GetStringFromAttributeByContext( sbVariableBuffer, vResultSet,
                                                   sbEntityName.toString( ), sbAttributeName.toString( ), "", 1000 );
                     if ( sbVariableBuffer != null )
                     {
                        ConvertCharacterString( pchMemoryNew, sbVariableBuffer, pchOrigMemory, nFileType );
                     }
                  }
               }
            }
            else
            {
               // Just copy the character
               *pchMemoryNew = *sbTemplateMemory;
               sbTemplateMemory++;
               pchMemoryNew++;
            }
         }
         else
         // <IMG SRC="cid:message-root.1.d:\10c\a\tz\image1.gif" ALT="image1.gif">
         if ( pchEmbeddedImages && sbTemplateMemory[ 0 ] == '<' &&
              zstrnicmp( sbTemplateMemory.substring( 1 ), "IMG SRC=", 8 ) == 0 &&
              zstrncmp( sbTemplateMemory.substring( 10 ), "cid:message-root.", 17 ) == 0 )
         {
            zstrncpy( pchMemoryNew, sbTemplateMemory, 27 );
            pchMemoryNew += 27;
            sbTemplateMemory += 27;
            while ( sbTemplateMemory.charAt( k ) != '\0' &&
                    sbTemplateMemory.charAt( k ) != '.' &&
                    sbTemplateMemory.charAt( k ) != '"' )
            {
               // Just copy the character
               *pchMemoryNew = *sbTemplateMemory;
               sbTemplateMemory++;
               pchMemoryNew++;
            }

            if ( sbTemplateMemory.charAt( k ) == '.' )
            {
               sbTemplateMemory++;  // skip past '.'
               lSkipLth = zstrlen( pchEmbeddedImages );
               if ( lSkipLth > 0 )
                  pchEmbeddedImages[ lSkipLth++ ] = ';';

               while ( sbTemplateMemory.charAt( k ) != '\0' && sbTemplateMemory.charAt( k ) != '"' )
               {
                  pchEmbeddedImages[ lSkipLth++ ] = *sbTemplateMemory;
                  sbTemplateMemory++;
               }

               pchEmbeddedImages[ lSkipLth ] = 0;
            }

            // Finally copy the terminating quote.
            *pchMemoryNew = *sbTemplateMemory;
            sbTemplateMemory++;
            pchMemoryNew++;
         }
         else
         {
            // Just copy the character.
            *pchMemoryNew = *sbTemplateMemory;
            sbTemplateMemory++;
            pchMemoryNew++;
         }
      }

      if ( pchMemoryNew - pchStartMemoryNew > lTotalOutputSize )
      {
         MessageBox( 0, "CopyWithInsertMemoryArea Overwrite Buffer",
                     "Memory Overwrite", MB_OK );
      }

      *sbMemoryNew = pchMemoryNew;
      return 0;
   }
 **/

   int
   ReadFileDataIntoMemory( View    vResultSet,
                           String  stringDocumentFile,
                           long    hDocumentMemory,
                           StringBuilder sbDocumentData ) throws IOException
   {
      int  hDocumentFile;
      int  lDocumentLth;

      hDocumentMemory = 0;
      sbDocumentData.setLength( 0 );
      lDocumentLth = 0;

      hDocumentFile = m_KZOEP1AA.SysOpenFile( vResultSet, stringDocumentFile, COREFILE_READ );
      if ( hDocumentFile < 0 )
      {
      // IssueError( vResultSet, 0, 0, "Can't open Document file." );
         return -1;
      }

      lDocumentLth = m_KZOEP1AA.SysGetFileSize( vResultSet, hDocumentFile );

      // Exit if the document file is empty.
      if ( lDocumentLth == 0 )
      {
         m_KZOEP1AA.SysCloseFile( vResultSet, hDocumentFile, 0 );
         return 0;
      }

   // hDocumentMemory = SysAllocMemory( sbDocumentData.toString( ), lDocumentLth, 0, zCOREMEM_ALLOC, 0 );  TODO  This is all wrong ... recode correctly in Java when needed
   // DrAllocTaskMemory( ppvDocumentData, lDocumentLth );
   // TraceLine( "ReadFileDataIntoMemory: 0x%x   Size: %d",
   //            (int) *ppvDocumentData, lDocumentLth );

   // **ppvDocumentData = 0;
      m_KZOEP1AA.SysReadFile( vResultSet, hDocumentFile, sbDocumentData, lDocumentLth );
      m_KZOEP1AA.SysCloseFile( vResultSet, hDocumentFile, 0 );

      if ( sbDocumentData == null )
      {
      // SysFreeMemory( hDocumentMemory  );
      // DrFreeTaskMemory( *ppvDocumentData );
      // phDocumentMemory = 0;
         sbDocumentData = null;
         lDocumentLth = 0;
         IssueError( vResultSet, 0, 0, "Read Error on Document file" );
         return -1;
      }

      return lDocumentLth;
   }

   public int
   ParseBooleanExpression( View zqFrame )
   {
      //zPCHAR pchValue;
      //zPCHAR pchNext;
      String  szBooleanExpression=null;
      StringBuilder sbBooleanExpression=null;
      String  szConditionValue=null;

      // Parse the Boolean Expression and create each component value as an entity Component.

      GetStringFromAttributeByContext( sbBooleanExpression,
                                       zqFrame, "BooleanExpression",
                                       "TextValue", "", 254 );
 /*
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
 */
      return 0;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: MergeSingleRS_EntryWithTemplate
   //
   //    Insert OI variable data in Template
   //
   //    lFlags  0 - No Attachment / Mime Type Text
   //            1 - Has Attachment
   //            2 - Mime Type HTML
   //           16 - Prompt if there are any recipients that do not have
   //                an email address specified
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   MergeSingleRS_EntryWithTemplate( View   vResultSet,
                                    String TemplateFileName,
                                    String OutputFileName,
                                    String stringEmbeddedImages,
                                    StringBuilder stringReturnedMergedText,
                                    int    lMaxReturnedLth,
                                    int    nFileType ) throws IOException // 1-Text   2-HTML
   {
 /**
      long  hFileTo;
      StringBuilder sbTemplateMemory = new StringBuilder( );
   // String stringMemoryEndOrig = null;
   // String stringMemoryEndNew;
      int  nTemplateMemoryPosition;
      int  hTemplateMemoryUnused = 0;
      int  selMemoryNew;
      int  lTemplateLth;
      int  lTotalOutputSize;
      int  lLthNew = 0;
      int  ulRC;
      int  nRC;
      int  nLth;

      // Get memory for output file.
      // The size of the new memory will add 8000 bytes of variable data to the
      // template size to account for merged data.
      lTemplateLth = ReadFileDataIntoMemory( vResultSet, TemplateFileName,
                                             hTemplateMemoryUnused, sbTemplateMemory );

      // Exit if the template file is empty or if there is an error opening it.
      if ( lTemplateLth <= 0 )
      {
         MessageSend( vResultSet, "", "Template File Merge",
                      "The Template File could not be opened.\nCheck that the file name specified is valid.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return null;
      }

   // stringMemoryEndOrig = cbMemoryStartOrig + lTemplateLth;  TODO  This is all wrong ... recode correctly in Java when needed
      lTotalOutputSize = lTemplateLth + 8000;
   // selMemoryNew = SysAllocMemory( sbMemoryStartNew, lTotalOutputSize, 0, zCOREMEM_ALLOC, 0 );
   // StringBuilder sbMemoryStartNew = new StringBuilder( lTotalOutputSize );
   // DrAllocTaskMemory( (zCOREMEM) &stringMemoryStartNew, lTotalOutputSize );
   // TraceLine( "MergeSingleRS_EntryWithTemplate: 0x%x   Size: %d",
   //            (int) stringMemoryStartNew, lTotalOutputSize );

      // Perform the normal mapping code here.
      nTemplateMemoryPosition = 0;
      stringEmbeddedImages = "";
      nRC = CopyWithInsertMemoryArea( vResultSet,
                                 sbTemplateMemory,
                                      nTemplateMemoryPosition, "",
                                      stringEmbeddedImages,
                                      nFileType );
   // SysFreeMemory( hTemplateMemoryUnused );
   // DrFreeTaskMemory( stringMemoryStartOrig );
      if ( nRC < 0 )
      {
         MessageSend( vResultSet, "", "Template File Merge",
                      "A Merge error occurred.\nCheck the Template file for invalid data or recreate the file.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return null;;
      }

      // End file.
      stringMemoryEndNew = nTemplateMemoryPosition;
      stringMemoryEndNew = "";
   // stringMemoryEndNew++;  TODO  This is all wrong ... recode correctly in Java when needed

      // Write the merged resulting data in memory out to the file.
   // lLthNew = (int) (stringMemoryEndNew - stringMemoryStartNew);  TODO  This is all wrong ... recode correctly in Java when needed
      hFileTo = SysOpenFile( vResultSet, OutputFileName, COREFILE_WRITE );
      if ( hFileTo < 0 )
      {
         SysFreeMemory( selMemoryNew );
      // DrFreeTaskMemory( stringMemoryStartNew );
         MessageSend( vResultSet, "", "Template File Merge",
                      "The Output File could not be opened.\nCheck that the file directory specified is valid.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );;
         return -1;
      }

   // WriteFile( hFileTo, stringMemoryStartNew, lLthNew, ulRC, 0 );  TODO  This is all wrong ... recode correctly in Java when needed
      SysCloseFile( vResultSet, hFileTo, 0 );

      // Returned the Merged text.
      if ( lMaxReturnedLth < lLthNew )
         nLth = lMaxReturnedLth;
      else
         nLth = lLthNew;

   // zstrncpy( pReturnedMergedText, stringMemoryStartNew, nLth );  TODO  This is all wrong ... recode correctly in Java when needed

      SysFreeMemory( selMemoryNew );
   // DrFreeTaskMemory( stringMemoryStartNew );
 **/
      return 0;

   } // MergeSingleRS_EntryWithTemplate

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: InsertOI_DataIntoTemplate
   //
   //    Insert OI variable data in Template
   //
   //    lFlags  0 - No Attachment / Mime Type Text
   //            1 - Has Attachment
   //            2 - Mime Type HTML
   //           16 - Prompt if recipient(s) does not have email address
   //
   /////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   InsertOI_DataIntoEmailTemplate( View   ViewToWindow,
                                   View   vResultSet,
                                   String stringSMTPServer,
                                   String stringEMailUserName,
                                   String stringEMailPassword,
                                   String stringSenderEMailAddress,
                                   String stringSubjectLine,
                                   String stringAttachmentFileName,
                                   String stringTemplateFileName,
                                   String stringAltFileName,
                                   String stringRootEntityName,
                                   int  lFlags ) throws IOException
   {
      String stringEmbeddedImages;   // should be WAY more than enough
      String stringRecipientEMailAddress = null;
      int nHasAttachment;
      int nMimeType;
      int  lConnection;
   // int  hFileTo;
      @SuppressWarnings("unused") String stringMemory;
      String stringMemoryNew;
      String stringMemoryNewHold = null;
      String stringMemoryStartEmailBody;
      StringBuilder sbMemoryStartOld = new StringBuilder( 256 );
      @SuppressWarnings("unused") String stringMemoryEndOld;
      String stringMemoryStartArea = null;
      String stringMemoryEndArea = null;
      long  selMemory = 0;
      long  selMemoryNew;
      int   lTemplateLth;

      String stringAltMemory;
      String stringAltMemoryNew;
      String stringAltMemoryNewHold = null;
      @SuppressWarnings("unused") String stringAltMemoryStartEmailBody;
      StringBuilder sbAltMemoryStartOld = new StringBuilder( 256 );
      @SuppressWarnings("unused") String stringAltMemoryEndOld;
      String stringAltMemoryStartArea = null;
      String stringAltMemoryEndArea = null;
      long  selAltMemory = 0;
      long  selAltMemoryNew = 0;
      long  lAltTemplateLth;
      long  lAltTotalOutputSize = 0;
      @SuppressWarnings("unused") int  lAltLthNew;

      int  lSelectedCount;
      int  lCurrentCount;
      int  lTotalOutputSize;
      @SuppressWarnings("unused") int  lLthNew;
      boolean  bNoEmailAddress = false;
   // zULONG ulRC;
      int nRC;

      if ( (lFlags & 0x00000001) != 0 )
         nHasAttachment = 1;  // has attachment
      else
         nHasAttachment = 0;  // no attachment

      if ( (lFlags & 0x00000002) != 0 )
         nMimeType = 2;  // HTML
      else
         nMimeType = 1;  // Text

      // The size of the new memory will add 8000 bytes of variable data to the
      // template size.
      lSelectedCount = 0;
      nRC = SetCursorFirstEntity( vResultSet, stringRootEntityName, "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = GetSelectStateOfEntity( vResultSet, stringRootEntityName );
         if ( nRC == 1 )
         {
           stringRecipientEMailAddress = GetStringFromAttribute( stringRecipientEMailAddress, vResultSet,
                                                                   "Person", "eMailAddress" );
            if ( stringRecipientEMailAddress.isEmpty( ) == false )
               lSelectedCount++;
            else
            {
              String lastName = null;
              String firstName = null;
              String middleName = null;

               if ( bNoEmailAddress == false )
               {
                  bNoEmailAddress = true;
                  TraceLineS( "EMail NOT sent Subject: ", stringSubjectLine );
               }

               lastName = GetStringFromAttribute( lastName, vResultSet, "Person", "LastName" );
               firstName = GetStringFromAttribute( firstName, vResultSet, "Person", "FirstName" );
               middleName = GetStringFromAttribute( middleName, vResultSet, "Person", "MiddleName" );
               stringRecipientEMailAddress = lastName + ", " + firstName + " " + middleName;
               TraceLineS( "  No EMail address for: ", stringRecipientEMailAddress );
            }
         }

         nRC = SetCursorNextEntity( vResultSet, stringRootEntityName, "" );
      }

      if ( bNoEmailAddress && (lFlags & 0x00000010) != 0 )
      {
         if ( OperatorPrompt( vResultSet,
                              "EMail not sent to recipient(s)",
                              "See Zeidon Trace for list ... Continue?",
                              TRUE, zBUTTONS_YESNO, zRESPONSE_NO, 0 ) == zRESPONSE_NO )
         {
            return 0;
         }
      }

      // We'll just exit here if nothing was selected.
      if ( lSelectedCount == 0 )
         return 0;

      lTemplateLth = ReadFileDataIntoMemory( vResultSet, stringTemplateFileName, selMemory, sbMemoryStartOld );

      // Exit if the template file is empty or if there is an error opening it.
      if ( lTemplateLth <= 0 )
      {
         IssueError( vResultSet, 0, 0, "Can't open Template file." );
         return 0;
      }

      lAltTemplateLth = 0;
      if ( nMimeType == 2 )  // HTML
      {
         if ( stringAltFileName != null && stringAltFileName.isEmpty( ) == false )
         {
         //? lAltTemplateLth = ReadFileDataIntoMemory( vResultSet, stringAltFileName, selAltMemory, sbAltMemoryStartOld );
         }

         // Exit if the alt template file is empty or if there is an error opening it.
         if ( lAltTemplateLth > 0 )
         {
            lAltTotalOutputSize = lAltTemplateLth + 8000;
         //?   selAltMemoryNew = SysAllocMemory( stringAltMemoryNewHold, lAltTotalOutputSize, 0, zCOREMEM_ALLOC, 0 );
         }
         else
         {
            // IssueError( vResultSet, 0, 0, "Can't open Alt Template file." );
            // return 0;
         }
      }

      lTotalOutputSize = lTemplateLth + 8000;
   //? selMemoryNew = SysAllocMemory( stringMemoryNewHold, lTotalOutputSize, 0, zCOREMEM_ALLOC, 0 );

   // DrAllocTaskMemory( (zCOREMEM) &stringMemoryNewHold, lTotalOutputSize );
   // TraceLine( "InsertOI_DataIntoEmailTemplate: 0x%x   Size: %d",
   //            (int) stringMemoryNewHold, lTotalOutputSize );

      lConnection = m_ZDRVROPR.CreateSeeConnection( stringSMTPServer, stringSenderEMailAddress,
                                                    stringEMailUserName, stringEMailPassword );

      // For each selected item, map the repeatable data in the template to the output buffer.
      lCurrentCount = 0;
      nRC = SetCursorFirstEntity( vResultSet, stringRootEntityName, "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = GetSelectStateOfEntity( vResultSet, stringRootEntityName );
         if ( nRC == 1 )
         {
            GetStringFromAttribute( stringRecipientEMailAddress, vResultSet,
                                    "Person", "eMailAddress" );
            if ( stringRecipientEMailAddress.isEmpty( ) == false )
            {
               if ( lAltTemplateLth > 0 )
               {
                  stringAltMemoryNew = stringAltMemoryNewHold;
                  lCurrentCount++;
                  stringAltMemoryEndOld = sbAltMemoryStartOld.subSequence( lTemplateLth, -1 ).toString( );

                  // Initialize Output values.
                  stringAltMemoryStartEmailBody = stringAltMemoryNew;
                  lAltLthNew = 0;

                  // Copy the first brace that starts the file.
               // stringAltMemory = cbAltMemoryStartOld.toString( );  TODO  This is all wrong ... recode correctly in Java when needed
               // stringAltMemoryNew = stringAltMemory;
               // stringAltMemory++;
               // stringAltMemoryNew++;

                  // Set the start of repeatable area (one per document copy) as
                  // second character in Template file.
               // stringAltMemoryStartArea = stringAltMemory;

                  // Set the end of repeatable area as one character before the last
                  // character in Template file.
               // stringAltMemoryEndArea = stringAltMemoryEndOld - 1;
                  stringAltMemory = stringAltMemoryStartArea;

                  // Perform the normal mapping code here.
               //? nRC = CopyWithInsertMemoryArea( vResultSet, stringAltMemoryStartArea, stringAltMemoryEndArea,
               //?                                 stringAltMemoryNew, "", stringAltMemoryNewHold,
               //?                                 lAltTotalOutputSize, "", 1 );
                  if ( nRC < 0 )
                     return nRC;

                  // Finally copy the closing brace to the output file.
                  stringAltMemoryNew = stringAltMemoryEndArea;
               // stringAltMemory++;  TODO  This is all wrong ... recode correctly in Java when needed
               // stringAltMemoryNew++;

               // lAltLthNew = (int) (stringAltMemoryNew - stringAltMemoryStartEmailBody);
               }
               else
                  stringAltMemory = "";

               stringMemoryNew = stringMemoryNewHold;
               lCurrentCount++;
            // stringMemoryEndOld = cbMemoryStartOld + lTemplateLth;  TODO  This is all wrong ... recode correctly in Java when needed

               // Initialize Output values.
               stringMemoryStartEmailBody = stringMemoryNew;
               lLthNew = 0;

               // Copy the first brace that starts the file.
           //  stringMemory = cbMemoryStartOld;  TODO  This is all wrong ... recode correctly in Java when needed
           //  stringMemoryNew = stringMemory;
           //  stringMemory++;
           //  stringMemoryNew++;

               // Set the start of repeatable area (one per document copy) as
               // second character in Template file.
           //  stringMemoryStartArea = stringMemory;

               // Set the end of repeatable area as one character before the last
               // character in Template file.
           //  stringMemoryEndArea = stringMemoryEndOld - 1;
               stringMemory = stringMemoryStartArea;

               // Perform the normal mapping code here.
               stringEmbeddedImages = "";
           //? nRC = CopyWithInsertMemoryArea( vResultSet, stringMemoryStartArea, stringMemoryEndArea,
           //?                                 stringMemoryNew, "", stringMemoryNewHold,
           //?                                 lTotalOutputSize, stringEmbeddedImages, nMimeType );
               if ( nRC < 0 )
                  return nRC;

               // Finally copy the closing brace to the output file.
            // stringMemoryNew = stringMemoryEndArea;  TODO  This is all wrong ... recode correctly in Java when needed
            // stringMemory++;
            // stringMemoryNew++;

            // lLthNew = (int) (stringMemoryNew - stringMemoryStartEmailBody);  TODO  This is all wrong ... recode correctly in Java when needed

               m_ZDRVROPR.CreateSeeMessage( lConnection, stringSMTPServer,
                                            stringSenderEMailAddress,
                                            stringRecipientEMailAddress,
                                            "", "", stringSubjectLine, nMimeType,
                                            stringMemoryStartEmailBody,
                                            stringAltMemory, stringEmbeddedImages,
                                            nHasAttachment, stringAttachmentFileName,
                                            stringEMailUserName, stringEMailPassword );

            // SysOpenFile( stringAttachmentFileName, COREFILE_DELETE );
            }
         }

         nRC = SetCursorNextEntity( vResultSet, stringRootEntityName, "" );
      }

      m_ZDRVROPR.CloseSeeConnection( lConnection );
   //? SysFreeMemory( selMemory );
   //? SysFreeMemory( selMemoryNew );
      if ( lAltTemplateLth != 0 )
      {
      //? SysFreeMemory( selAltMemory );
      //? SysFreeMemory( selAltMemoryNew );
      }

   // DrFreeTaskMemory( stringMemoryStartOld );
   // DrFreeTaskMemory( stringMemoryNewHold );

      return 0;

   } // InsertOI_DataIntoEmailTemplate

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: InsertOI_DataIntoTemplateFile
   //
   //    Insert OI variable data in Template File
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   InsertOI_DataIntoTemplateFile( View view,
                                  View workView,
                                  String toFile,
                                  String fromFile,
                                  String stringRootEntityName) throws IOException
   {
      BufferedWriter bw;
      StringBuilder sbInsertTemplate  = new StringBuilder();
      StringBuilder sbRawTemplate = new StringBuilder();
      StringBuilder sbEntityBuffer;
      StringBuilder sbAttributeBuffer;
      String swapString = null;
      String stringStart  = "{";
      String stringEnd    = "}";
      String szTmp    = null;
      int nRC = 0;
      int lSelectedCount = 0;
      int lTemplateLth = 0;

      nRC = SetCursorFirstEntity( workView, stringRootEntityName, "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lSelectedCount++;
         nRC = SetCursorNextEntity( workView, stringRootEntityName, "" );
      }

      if ( lSelectedCount <= 0 )
         return 0;

      lTemplateLth = ReadFileDataIntoMemory( workView, fromFile, lTemplateLth, sbRawTemplate );

      if ( lTemplateLth > Integer.MAX_VALUE )
         return 0;

      // File not found.
      if ( lTemplateLth < 0 )
         return -1;

      swapString = sbRawTemplate.substring(1, (lTemplateLth - 1));
      lTemplateLth = swapString.length();
      sbRawTemplate = new StringBuilder();

      nRC = SetCursorFirstEntity(workView, stringRootEntityName, "");
      while(nRC > zCURSOR_UNCHANGED)
      {
         sbRawTemplate.insert(0, swapString);

         for(int i = 0; i < sbRawTemplate.length(); i++)
         {
            sbEntityBuffer = new StringBuilder();
            sbAttributeBuffer = new StringBuilder();
            if (sbRawTemplate.charAt(i) == '[' && sbRawTemplate.charAt(i + 1) == 'Z')
            {
               int j = i;
               i += 2;
               while(sbRawTemplate.charAt(++i) != '.')
                  sbEntityBuffer.append(sbRawTemplate.charAt(i));

               while(sbRawTemplate.charAt(++i) != ']')
                  sbAttributeBuffer.append(sbRawTemplate.charAt(i));

               i++;
               szTmp = sbRawTemplate.substring(j, i + 10).toString();
               sbRawTemplate.replace(j, i, GetStringFromAttribute(workView,
                                                      sbEntityBuffer.toString(),
                                                      sbAttributeBuffer.toString()));
               szTmp = sbRawTemplate.substring(j, j+12).toString();

            }
         }

         sbInsertTemplate.append(sbRawTemplate);
         //szTmp = sbInsertTemplate.substring(87284, 87296).toString();
         sbRawTemplate = new StringBuilder();
         nRC = SetCursorNextEntity(workView, stringRootEntityName, "");
      }

      sbInsertTemplate.insert(0, stringStart);
      sbInsertTemplate.append(stringEnd);
      //szTmp = sbInsertTemplate.substring(87285, 87297).toString();
      szTmp = sbInsertTemplate.substring(13917, 13929).toString();

      bw = new BufferedWriter(new FileWriter(toFile));
      //bw.write(sbInsertTemplate.toString());
      szTmp = sbInsertTemplate.toString();
      bw.write(szTmp);
      bw.flush();
      bw.close();

      return 0;
   }

   public int
   InsertOI_DataIntoTemplateFile( View view,
                                  zVIEW workView,
                                  String toFile,
                                  String fromFile,
                                  String altFile,
                                  String stringRootEntityName) throws IOException
   {
      return (InsertOI_DataIntoTemplateFile(view, workView.getView(), toFile, fromFile, stringRootEntityName));
   }
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: InsertOI_DataIntoTemplateFile
   //
   //    Insert OI variable data in Template File
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   InsertOI_DataIntoTemplateFileOld( View   ViewToWindow,
                                     View   vResultSet,
                                     String stringOutputFileName,
                                     String stringTemplateFileName,
                                     String stringAltFileName,
                                     String stringRootEntityName ) throws IOException
   {
      int   hFileTo;
      @SuppressWarnings("unused") String stringMemory;
      String stringMemoryNew = null;
      @SuppressWarnings("unused") String stringMemoryStartNew;
      StringBuilder sbMemoryStartOld = new StringBuilder( );
      @SuppressWarnings("unused") String stringMemoryEndOld;
      String stringMemoryStartArea = null;
      String stringMemoryEndArea = null;
      long selMemory = 0;
      int  selMemoryNew;
      int  lTemplateLth;
      int  lSelectedCount;
      int  lCurrentCount;
      int  lTotalOutputSize;
      @SuppressWarnings("unused") int  lLthNew;
      int nRC;

      // The size of the new memory will try to allow for the number of select
      // copies to be made of the input template.  It will thus add 50000 bytes
      // of variable data to the template size and multiply that by the number
      // of items selected.
      lSelectedCount = 0;
      nRC = SetCursorFirstEntity( vResultSet, stringRootEntityName, "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = GetSelectStateOfEntity( vResultSet, stringRootEntityName );
         if ( nRC == 1 )
            lSelectedCount++;

         nRC = SetCursorNextEntity( vResultSet, stringRootEntityName, "" );
      }

      // We'll just exit here if nothing was selected.
      if ( lSelectedCount == 0 )
         return 0;

      lTemplateLth = ReadFileDataIntoMemory( vResultSet, stringTemplateFileName,
                                             selMemory, sbMemoryStartOld );

      // Exit if the template file is empty or if there is an error opening it.
      if ( lTemplateLth <= 0 )
      {
         IssueError( vResultSet, 0, 0, "Can't open Template file." );
         return 0;
      }

      lTotalOutputSize = (lTemplateLth + 50000) * lSelectedCount;
   //? selMemoryNew = SysAllocMemory( stringMemoryNew, lTotalOutputSize, 0, zCOREMEM_ALLOC, 0 );
   // DrAllocTaskMemory( (zCOREMEM) &stringMemoryNew, lTotalOutputSize );
   // TraceLine( "InsertOI_DataIntoTemplateFile: 0x%x   Size: %d",
   //            (int) stringMemoryNew, lTotalOutputSize );

      stringMemoryEndOld = sbMemoryStartOld.subSequence( lTemplateLth, -1 ).toString( );

      // Initialize Output values.
      stringMemoryStartNew = stringMemoryNew;
      lLthNew = 0;

      // Copy the first brace that starts the file.
   // stringMemory = cbMemoryStartOld.toString( );  TODO  This is all wrong ... recode correctly in Java when needed
   //  stringMemoryNew = stringMemory;
   // stringMemory++;
   // stringMemoryNew++;

      // Set the start of repeatable area (one per document copy) as second character in
      // Template file.
   // stringMemoryStartArea = stringMemory;

      // Set the end of repeatable area as one character before the last character in Template file.
   // stringMemoryEndArea = stringMemoryEndOld - 1;

      // For each selected item, map the repeatable data in the template to the output buffer.
      nRC = SetCursorFirstEntity( vResultSet, stringRootEntityName, "" );
      lCurrentCount = 0;
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = GetSelectStateOfEntity( vResultSet, stringRootEntityName );
         if ( nRC == 1 )
         {
            // If this is any page but the first, add in the page break
            // characters.
            lCurrentCount++;
            if ( lCurrentCount > 1 )
            {
            // TODO  what's the problem with this line??? zstrcpy( stringMemoryNew, "\x0D\x0A\\par \\page \\hich\\af0\\dbch\\af28\\loch\\f0 " );
               stringMemoryNew = stringMemoryNew + 41;
            }

            stringMemory = stringMemoryStartArea;
            // Perform the normal mapping code here.
        //? nRC = CopyWithInsertMemoryArea( vResultSet, stringMemoryStartArea, stringMemoryEndArea,
        //?                                 stringMemoryNew, "", stringMemoryNew, lTotalOutputSize, "",    // no embedded images
        //?                                 1 );  // Text
            if ( nRC < 0 )
               return nRC;
         }

         nRC = SetCursorNextEntity( vResultSet, stringRootEntityName, "" );
      }

      // Finally copy the closing brace to the output file.
   // stringMemoryNew = stringMemoryEndArea;  TODO  This is all wrong ... recode correctly in Java when needed
   //  stringMemory++;
   // stringMemoryNew++;

   // lLthNew = (int) (stringMemoryNew - stringMemoryStartNew);
   //? SysFreeMemory( selMemory );
   // DrFreeTaskMemory( stringMemoryStartOld );
      hFileTo = m_KZOEP1AA.SysOpenFile( ViewToWindow, stringOutputFileName, COREFILE_WRITE );
      if ( hFileTo < 0 )
      {
      //? SysFreeMemory( selMemoryNew );
      // DrFreeTaskMemory( stringMemoryNew );
         IssueError( vResultSet, 0, 0, "Write Open Error" );
         return -1;
      }

   // WriteFile( hFileTo, stringMemoryStartNew, lLthNew, ulRC, 0 );  TODO  This is all wrong ... recode correctly in Java when needed
      m_KZOEP1AA.SysCloseFile( ViewToWindow, hFileTo, 0 );
   //? SysFreeMemory( selMemoryNew );
   // DrFreeTaskMemory( stringMemoryNew );

      return 0;

   } // InsertOI_DataIntoTemplateFile

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ReadLine5000
   //
   //    Read a line into a 5000 character string
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   ReadLine5000( View   ViewToWindow,
                 StringBuilder sbLineBuffer,
                 int    FileHandle ) throws IOException
   {
      int nRC = 0;

      nRC = m_KZOEP1AA.SysReadLine( ViewToWindow, sbLineBuffer, FileHandle );
      if ( sbLineBuffer.length( ) == 0 )
         return 0;

      if ( sbLineBuffer.length( ) > 5000 )
      {
         TraceLineS( "////////////* > 5000", "//////////*" );
         sbLineBuffer.setCharAt( 5000, '\0' );
      }

      //return sbLineBuffer.toString( );
      return nRC;

   } // ReadLine5000

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ConvertLineToEntity
   //
   //    Convert data in a comma or tab delimited record to attribute values in an entity.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   ConvertLineToEntity( View   vTarget,
                        View   vXOD,
                        String stringRecord,
                        String stringDelimiterType,
                        int    lMaxRecordLth )
   {
 /** TODO
      String  cDelimiter;
      String  stringDataValue;
      String  entityName;
      String  attributeName;
      String  stringTab;
      String  stringDate;
      String  stringDataValue;
      String  stringRecordEnd;
      int nRC;

      zstrcpy( stringTab, "x09" );
      if ( stringDelimiterType.charAt( 0 ) == 'T' )
         cDelimiter = stringTab[ 1 ];
      else
         cDelimiter = stringTab[ 1 ];

      stringRecordEnd = stringRecord + lMaxRecordLth;
      GetStringFromAttribute( entityName, vXOD, "ENTITY", "NAME" );

      // Loop for each Attribute in the Entity, parsing the record string and setting the Attribute.
      nRC = SetCursorFirstEntity( vXOD, "ATTRIB", 0 );
      while ( nRC >= zCURSOR_SET && stringRecord < stringRecordEnd )
      {
         GetStringFromAttribute( attributeName, vXOD, "ATTRIB", "NAME" );
         stringDataValue = stringDataValue;
         stringDataValue = "";
         while ( *stringRecord != 9 && stringRecord < stringRecordEnd )
         {
            *stringDataValue = *stringRecord;
            stringRecord++;
            stringDataValue++;
         }

         *stringDataValue = 0;
         stringRecord++;

         if ( CompareAttributeToString( vXOD, "ATTRIB", "TYPE", "T" ) == 0 &&
              zstrlen( stringDataValue ) <= 8 )
         {
            // Attribute is Date, so convert properly.
            if ( zstrlen( stringDataValue ) == 7 )
            {
               zstrcpy( stringDate, "0" );
               zstrcat( stringDate, stringDataValue );
            }
            else
               zstrcpy( stringDate, stringDataValue );
            // A value of "0" is really null.
            if ( zstrcmp( stringDate, "0" ) == 0 )
               stringDate = "";

            SetAttrFromStrByContext( vTarget, entityName, attributeName, stringDate, "MMDDYYYY" );
         }
         else
            // Attribute is treated as simple Text.
            SetAttributeFromString( vTarget, entityName, attributeName, stringDataValue );

         nRC = SetCursorNextEntity( vXOD, "ATTRIB", "" );
      }
 **/
      return 0;
   } // ConvertLineToEntity

   public int SetAttrFromStrByContext( View view, String entityName, String attributeName, String value, String context )
   {
      view.getCursor( entityName ).getAttribute( attributeName ).setValue( value, context );
      return 0;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: GetCurrentApplicationName
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   GetCurrentApplicationName( StringBuilder stringReturnedString,
                              int    lMaxLength,
                              View   ViewToWindow )
   {
      // LPAPP  stringApp;

      return SfGetApplicationForSubtask( stringReturnedString, ViewToWindow );
   }

   // GetCurrentApplicationName

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: DBQualEntityByString
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   DBQualEntityByString( View   vQualObject,
                         String entityName,
                         String attributeName,
                         String operationName,
                         String value,
                         int  bExists )
   {
      if ( entityName.length() == 0 )
         entityName = null;

      if ( attributeName.length() == 0 )
         attributeName = null;

      if ( operationName.length() == 0 )
         operationName = null;

      if ( value.length() == 0 )
         value = null;

      // add qualification
      CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
      SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", entityName );
      if ( bExists == TRUE )
      {
         SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "EXISTS" );
         CreateEntity( vQualObject, "SubQualAttrib", zPOS_AFTER );
         SetAttributeFromString( vQualObject, "SubQualAttrib", "EntityName", entityName );
         SetAttributeFromString( vQualObject, "SubQualAttrib", "AttributeName", attributeName );
         SetAttributeFromString( vQualObject, "SubQualAttrib", "Value", value );
         SetAttributeFromString( vQualObject, "SubQualAttrib", "Oper", operationName );
      }
      else
      {
         SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", attributeName );
         SetAttributeFromString( vQualObject, "QualAttrib", "Value", value );
         SetAttributeFromString( vQualObject, "QualAttrib", "Oper", operationName );
      }

      return 0;
   } // DBQualEntityByString

 /**  TODO when we get some time
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ParseBooleanExpression
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   ParseBooleanExpression( View   zqFrame )
   {
      String  stringValue;
      String  stringNext;
      String  stringBooleanExpression;
      String  stringConditionValue;

      // Parse the Boolean Expression and create each component value as an entity Component.

      stringBooleanExpression = GetStringFromAttributeByContext( stringBooleanExpression,
                                                                 zqFrame, "BooleanExpression",
                                                                 "TextValue", "", 254 );

      // Skip to first nonblank.
      for ( stringNext = stringBooleanExpression;
            *stringNext == ' ' && *stringNext != 0;
            stringNext++ )
      {
      }

      // Loop through all parameters.
      while ( *stringNext != 0 )
      {
         // Find next parameter
         if ( *stringNext == ')' || *stringNext == '(' )
         {
            stringValue = stringConditionValue;
            *stringValue = *stringNext;
            stringValue++;
            *stringValue = 0;
            if ( *stringNext == ')' )
               stringNext++;    // We need to do the skip here for close paren
         }
         else
         {
           for ( stringValue = stringConditionValue;
                 *stringNext != ' ' && *stringNext != 0 && *stringNext != ')';
                 stringNext++ )
            {
               *stringValue = *stringNext;
               stringValue++;
            }

            *stringValue = 0;
         }

         CreateEntity( zqFrame, "Component", zPOS_AFTER );
         SetAttributeFromString( zqFrame, "Component", "Value", stringConditionValue );

         if ( *stringNext != 0 && *stringNext != ')' )
            stringNext++;

         // Skip to next nonblank.
         while ( *stringNext == ' ' && *stringNext != 0 )
            stringNext++;
      }

      return 0;
   } // ParseBooleanExpression
 **/

 /**
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: WinShellExecute
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   WinShellExecute( View   vSubtask,
                    String stringFileOrExeName,
                    String stringFileOpenCommand,
                    String stringExeParams )
   {
      //HWND hWnd;
      int hWnd;
      int lControlReturn;
      int hInstance;

      GetWindowHandle( hWnd, lControlReturn, vSubtask, "" );
      //GetWindowHandle( PTR UNSIGNED int, // Window Return
      //                PTR UNSIGNED int,  // ControlReturn
      //                View,      // Subtask
      //                String );  // CtrlTag

      if ( stringFileOpenCommand != null && stringFileOpenCommand.isEmpty( ) == false )
         TraceLineS( "WinShellExecute FileOpenCommand: ", stringFileOpenCommand );

      if ( stringFileOrExeName != null && stringFileOrExeName.isEmpty( ) == false  )
         TraceLineS( "WinShellExecute FileOrExeName: ", stringFileOrExeName );

      if ( stringExeParams != null && stringExeParams.isEmpty( ) == false )
         TraceLineS( "WinShellExecute ExeParams: ", stringExeParams );

      hInstance = (zULONG) ShellExecute( (HWND) hWnd, stringFileOpenCommand,
                                         stringFileOrExeName, stringExeParams,
                                         null, SW_SHOWNORMAL );
      if ( hInstance > 32 )
      {
         TraceLineI( "WinShellExecute Success - Result: ", hInstance );
         return 0;
      }
      else
      {
         TraceLineI( "WinShellExecute Failed Result: ", hInstance );
         return hInstance;
      }

       return 0;
   } // WinShellExecute
 **/

   public String
   GetRTFPath( View vSubtask, int lFlag,  String stringTarget )
   {
      String stringReturn;
      String stringCLSID = null;
      @SuppressWarnings("unused") int nRC = FALSE;

      stringReturn = "";
      if ( lFlag == 2 ) // open for print
      {
         stringCLSID = GetRegistryCLSID( stringCLSID, "rtffile" );
         stringReturn = GetRegistryPrintValue( "", "rtffile", stringCLSID, REG_SZ, stringReturn, 0 );

         TraceLineS( "RTF-Print-Flag 3 stringCLSID !", stringCLSID ) ;
         TraceLineS( "RTF-Print-Flag 3 Return !", stringReturn ) ;
         if ( stringReturn == null )
         {
           stringReturn = GetRegistryPrintValue( "", "rtffile", stringCLSID, REG_SZ, stringReturn, 0 );
            TraceLineS( "RTF-Print-Flag 3 stringCLSID [win98]!", stringCLSID ) ;
            TraceLineS( "RTF-Print-Flag 3 Return [win98]!", stringReturn ) ;
         }
      }

      if ( lFlag == 3 ) // open for view
      {
         GetRegistryCLSID( stringCLSID, "rtffile" );
         stringReturn = GetRegistryGeneralValue( "", "rtffile", stringCLSID, REG_SZ, stringReturn, 0 );

         TraceLineS( "RTF-Flag 3 stringCLSID !", stringCLSID ) ;
         TraceLineS( "RTF-Flag 3 Return !", stringReturn ) ;
         if ( stringReturn == null )
         {
            // for win98 in case we are not in win2K
          stringReturn = GetRegistryGeneralValue( "", "rtffile", stringCLSID,
                                                    REG_EXPAND_SZ, stringReturn, 0 );
            TraceLineS( "RTF-Flag 3C stringCLSID [win98]!", stringCLSID ) ;
            TraceLineS( "RTF-Flag 3C Return [win98]!", stringReturn ) ;
         }
      }

      stringTarget = zstrcpy( stringTarget, stringReturn );
      return stringTarget;
   }

   public int
   StartEmailClientForListReus( View    vResult,
                                String  entityName,
                                String  attributeName,
                                String  contextName,
                                String  stringScope,
                                int     bUseOnlySelectedEntities,
                                int     bUseParentSelectedEntities,
                                String  stringSubject,
                                String  stringCopyTo,        // comma separated list
                                String  stringBlindCopy,     // comma separated list
                                String  stringBody,
                                String  stringAttachment,
                                String  stringEmailClient,
                                int     lFlags,
                                String  stringBlindCopyFlag )          // reserved
   {
      String stringParentEntity = null;
      String s = null;
      int    lEntityCnt;
      int    ulAttributeLth = 0;
      int    lTotalSize;
      int    lLth = 0;
      int    lRC;

      if ( bUseParentSelectedEntities  != 0 )
         stringParentEntity = MiGetParentEntityNameForView( stringParentEntity, vResult, entityName );

      lEntityCnt = CountEntitiesForView( vResult, entityName );
      ulAttributeLth = GetAttributeDisplayLength( ulAttributeLth, vResult, entityName, attributeName, contextName );
      lTotalSize = lEntityCnt * (int) ulAttributeLth;  // a starting point
      CharBuffer cbMemory = CharBuffer.allocate( lTotalSize + 1 );
   // DrAllocTaskMemory( cbMemory, lTotalSize + 1 );

      // For each entity, append the specified data to the list.
   // lRC = SetCursorFirstEntity( vResult, entityName, stringScope );
      lRC = SetEntityCursor( vResult, entityName, "", zPOS_FIRST, "", "", "", 0, stringScope, "" );

      while ( lRC > zCURSOR_UNCHANGED )
      {
         if ( bUseOnlySelectedEntities == 0 ||
              ((bUseOnlySelectedEntities != 0) &&
               GetSelectStateOfEntity( vResult, entityName ) != 0) ||
              ((bUseParentSelectedEntities != 0) &&
               GetSelectStateOfEntity( vResult, stringParentEntity ) != 0) )
         {
            s = GetVariableFromAttribute( s, 0, zTYPE_STRING,
                                          lTotalSize - lLth - 1, vResult,
                                          entityName, attributeName, contextName,
                                          contextName != null && contextName.isEmpty( ) == false ? 0: zUSE_DEFAULT_CONTEXT );
            lLth = zstrcpy( cbMemory, lLth, s );
            while ( lLth > 0 && cbMemory.charAt( lLth - 1 ) == ' ' )
            {
               lLth--;
               cbMemory.put( lLth, '\0' );
            }
         }

      // lRC = SetCursorNextEntity( vResult, entityName, stringScope );
         lRC = SetEntityCursor( vResult, entityName, "", zPOS_NEXT, "", "", "", 0, stringScope, "" );
         if ( lRC > zCURSOR_UNCHANGED )
         {
         // lLth = zstrlen( stringMemory );
            if ( lTotalSize - lLth < (int) ulAttributeLth )
            {
               s = cbMemory.toString( );

                lEntityCnt *= 2;
                lTotalSize = lEntityCnt * (int) ulAttributeLth;
                cbMemory = CharBuffer.allocate( lTotalSize + 1 );
                zstrcpy( cbMemory, 0, s );
            }

            if ( lLth > 0 && cbMemory.charAt( lLth - 1 ) != ',' )
            {
               cbMemory.put( lLth++, ',' );
               cbMemory.put( lLth, '\0' );
            }
         }
      }

      if ( stringBlindCopyFlag.charAt( 0 ) == 'Y' )
      {
         // Email Addresses are to be put in Blind Copy parameter.
         TraceLineS( "Blind Copies: ", cbMemory.toString( ) );
         lRC = m_ZDRVROPR.StartEmailClient( stringBlindCopy, // Regular send parameter
                                 stringSubject,
                                 stringCopyTo,    // comma separated list
                                 cbMemory.toString( ),     // Blind Copy parameter
                                 stringBody,
                                 stringAttachment,
                                 "",
                                 lFlags );          // reserved
      }
      else
      {
         // Email Addresses are to be put in regular Send parameter.
         TraceLineS( "Regular Copies: ", cbMemory.toString( ) );
         lRC = m_ZDRVROPR.StartEmailClient( cbMemory.toString( ),  // comma separated list
                                 stringSubject,
                                 stringCopyTo,        // comma separated list
                                 stringBlindCopy,     // comma separated list
                                 stringBody,
                                 stringAttachment,
                                 stringEmailClient,
                                 lFlags );          // reserved
      }

   // DrFreeTaskMemory( (String) cbMemory );
      return lRC;
   }

   public int
   StartEmailClientForList( View    vResult,
                            String  entityName,
                            String  attributeName,
                            String  contextName,
                            String  stringScope,
                            int  bUseOnlySelectedEntities,
                            int  bUseParentSelectedEntities,
                            String  stringSubject,
                            String  stringCopyTo,        // comma separated list
                            String  stringBlindCopy,     // comma separated list
                            String  stringBody,
                            String  stringAttachment,
                            String  stringEmailClient,
                            int   lFlags )          // reserved
   {
      int  lRC;

      // Call reusable routine.
      lRC = StartEmailClientForListReus( vResult,
                                         entityName,
                                         attributeName,
                                         contextName,
                                         stringScope,
                                         bUseOnlySelectedEntities,
                                         bUseParentSelectedEntities,
                                         stringSubject,
                                         stringCopyTo,
                                         stringBlindCopy,
                                         stringBody,
                                         stringAttachment,
                                         stringEmailClient,
                                         lFlags,
                                         "" );
      return lRC;
   }

   public int
   StartEmailClientForList( View    vResult,
                            String  entityName,
                            String  attributeName,
                            String  contextName,
                            int  nScope,
                            int  bUseOnlySelectedEntities,
                            int  bUseParentSelectedEntities,
                            String  stringSubject,
                            String  stringCopyTo,        // comma separated list
                            String  stringBlindCopy,     // comma separated list
                            String  stringBody,
                            String  stringAttachment,
                            String  stringEmailClient,
                            int   lFlags )          // reserved
   {
      int  lRC;

      // Call reusable routine.
      lRC = StartEmailClientForListReus( vResult,
                                         entityName,
                                         attributeName,
                                         contextName,
                                         "",
                                         bUseOnlySelectedEntities,
                                         bUseParentSelectedEntities,
                                         stringSubject,
                                         stringCopyTo,
                                         stringBlindCopy,
                                         stringBody,
                                         stringAttachment,
                                         stringEmailClient,
                                         lFlags,
                                         "" );
      return lRC;
   }

   public int
   StartBlindEmailClientForList( View    vResult,
                                 String  entityName,
                                 String  attributeName,
                                 String  contextName,
                                 String  stringScope,
                                 int  bUseOnlySelectedEntities,
                                 int  bUseParentSelectedEntities,
                                 String  stringSubject,
                                 String  stringCopyTo,        // comma separated list
                                 String  stringBlindCopy,     // comma separated list
                                 String  stringBody,
                                 String  stringAttachment,
                                 String  stringEmailClient,
                                 int   lFlags )          // reserved
   {
      zVIEW   mUser = null;
      String  stringBlindEmailAddress = null;
      int  lRC;

      // Get the Target Email Address from mUser, unless a BlindCopy is passed.
      if ( stringBlindCopy == null )
      {
         stringBlindEmailAddress = "";
         GetViewByName( mUser, "mUser", vResult, zLEVEL_APPLICATION );
         lRC = CheckExistenceOfEntity( mUser, "Employee" );
         if ( lRC >= 0 )
            GetStringFromAttribute( stringBlindEmailAddress, mUser, "Employee", "eMailAddress" );
         if ( stringBlindEmailAddress.isEmpty( ) )
         {
            lRC = CheckExistenceOfEntity( mUser, "Student" );
            if ( lRC >= 0 )
             stringBlindEmailAddress = GetStringFromAttribute( stringBlindEmailAddress, mUser, "Student", "eMailAddress" );

            if ( stringBlindEmailAddress.isEmpty( ))
            {
             stringBlindEmailAddress = GetStringFromAttribute( stringBlindEmailAddress, mUser, "Person", "eMailAddress" );
            }
         }
      }
      else
         stringBlindEmailAddress = zstrcpy( stringBlindEmailAddress, stringBlindCopy );

      // Call reusable routine.
      lRC = StartEmailClientForListReus( vResult,
                                         entityName,
                                         attributeName,
                                         contextName,
                                         stringScope,
                                         bUseOnlySelectedEntities,
                                         bUseParentSelectedEntities,
                                         stringSubject,
                                         stringCopyTo,
                                         stringBlindEmailAddress ,
                                         stringBody,
                                         stringAttachment,
                                         stringEmailClient,
                                         lFlags,
                                         "Y" );
      return lRC;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: StartEmailClientWithFiles
   //
   //    Start Email Client passing in file names for body and attachment
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   StartEmailClientWithFiles( View   AnyView,
                              String stringEmailAddress,
                              String stringSubjectLine,
                              String stringCopyToEmailAddress,
                              String stringBlindCopyEmailAddress,
                              String stringBodyFileName,
                              String stringAttachmentFileName,
                              String stringEmailClientOverride,
                              int ulFlags ) throws IOException
   {
      // Read the data from the Body and Attachment files into memory and call StartEmailClient
      // with those values.
      StringBuilder sbBodyMemoryStart = new StringBuilder( );
      StringBuilder sbAttachmentMemoryStart = new StringBuilder( );
      long hBodyMemory = 0;
      long hAttachmentMemory = 0;
      int  lFileLth = 0;

      // Read the Body into memory.
      hBodyMemory = ReadFileDataIntoMemory( AnyView, stringBodyFileName,
                                            hBodyMemory, sbBodyMemoryStart );
      // Exit if the file is empty or if there is an error opening it.
      if ( hBodyMemory == -1 )
      {
         IssueError( AnyView, 0, 0, "Can't open Email file." );
         return -1;
      }

      // If there is an attachment file, also read it into memory.
      // Then call StartEmailClientWithFiles with or without an attachment.
      if ( stringAttachmentFileName.isEmpty( ) == false )
      {
         hAttachmentMemory = ReadFileDataIntoMemory( AnyView, stringAttachmentFileName,
                                                     hAttachmentMemory, sbAttachmentMemoryStart );
         // Exit if the file is empty or if there is an error opening it.
         if ( lFileLth <= 0 )
            return -1;

         m_ZDRVROPR.StartEmailClient( stringEmailAddress,
                           stringSubjectLine,
                           stringCopyToEmailAddress,
                           stringBlindCopyEmailAddress,
                           sbBodyMemoryStart.toString( ),
                           sbAttachmentMemoryStart.toString( ),
                           stringEmailClientOverride,
                           0 );
      //? SysFreeMemory( hAttachmentMemory );
      // DrFreeTaskMemory( stringAttachmentMemoryStart );
      }
      else
      {
       m_ZDRVROPR.StartEmailClient( stringEmailAddress,
                           stringSubjectLine,
                           stringCopyToEmailAddress,
                           stringBlindCopyEmailAddress,
                           sbBodyMemoryStart.toString( ),
                           "",
                           stringEmailClientOverride,
                           0 );
      }

   //? SysFreeMemory( hBodyMemory );
   // DrFreeTaskMemory( stringBodyMemoryStart );
      return 0;
   }

   // StartEmailClientWithFiles

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: IsEmailAddressValid
   //
   //    Validates an email address
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   boolean
   IsEmailAddressValid( String stringEmailAddress )
   {
   // TODO return ValidateEmailAddressFormat( stringEmailAddress ) ? true : false;
      return true;

   /** #if 0

      // Contains at least one character preceding the "@"
      // Contains a "@" following the preceding character(s)
      // Contains at least one character following the "@", followed
      // by a dot (.), followed by either a two character or three
      // character string (a two character country code or the standard
      // three character US code, such as com, edu etc)

      String stringAt;
      String stringDot;
      String string;

      if ( zstrchr( stringEmailAddress, ' ' )  ||
           zstrchr( stringEmailAddress, '\t' ) ||
           zstrchr( stringEmailAddress, '\n' ) ||
           zstrchr( stringEmailAddress, '\r' ) )
      {
         return FALSE );
      }

      stringAt = zstrchr( stringEmailAddress, '@' );
      if ( stringAt )
      {
         stringDot = zstrchr( stringAt, '.' );
         stringAt = zstrchr( stringAt + 1, '@' );
         if ( stringAt == 0 && stringDot )
         {
            stringDot = zstrrchr( stringDot, '.' );
            if ( stringDot[ 1 ] && stringDot &&
                 ((stringDot[ 3 ] == 0) ||
                  (stringDot[ 3 ] && stringDot == 0)) )
            {
               string = (String) stringEmailAddress;
               while ( *string )
               {
                  if ( *string > (char) 127 )
                     return FALSE );

                  string++;
               }

               return TRUE );
            }
         }
      }

      return FALSE );

   #endif **/
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SendEmailForFiles
   //
   //    Start Email Client passing in file names for body and attachment
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SendEmailForFiles( View   ViewToWindow,
                      View   ResultSet,
                      String stringSmtpServer,
                      String stringRecipientEMailAddress,
                      String stringSenderEMailAddress,
                      String stringEMailUserName,
                      String stringEMailPassword,
                      String stringSubjectLine,
                      String stringBodyFileName,
                      String stringAltTextFileName,
                      String stringEmbeddedImages,
                      String stringAttachmentFileName,
                      int    nMimeType,     // 1-Text, 2-HTML
                      int    lConnection )
   {
   // String stringBodyMemoryStart;
      CharBuffer  cbAtBodyFileName = CharBuffer.allocate( 256 );
      CharBuffer  cbAtAltTextFileName = CharBuffer.allocate( 256 );
   // int  selBodyMemory;
   // int  lFileLth;
      zVIEW  zqMDocOLST = null;
      zVIEW  wWebXfer = null;
      int    nRC;

   // TraceLine( "SendEmailForFiles Server: %s   Sender: %s   Recipient: %s"
   //            "   Subject: %s   Mime Type: %d"
   //            "   User: %s   Password %s",
   //            stringSmtpServer, stringSenderEMailAddress, stringRecipientEMailAddress,
   //            stringSubjectLine, nMimeType, stringEMailUserName, stringEMailPassword );

      // First make sure the email address is valid. If not exit with return code of 2.
      if ( IsEmailAddressValid( stringRecipientEMailAddress ) == false )
         return 2;

      GetViewByName( zqMDocOLST, "zqMDocOLST", ResultSet, zLEVEL_TASK );
      GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

      if ( stringBodyFileName != null )
      {
         if ( stringBodyFileName.isEmpty( ) == false && stringBodyFileName.charAt( 0 ) != '@' )
         {
            cbAtBodyFileName.put( 0, '@' );
            zstrcpy( cbAtBodyFileName, 1, stringBodyFileName );
         }
         else
            zstrcpy( cbAtBodyFileName, 0, stringBodyFileName );
      }
      else
         cbAtBodyFileName.put( 0, '\0' );

      if ( stringAltTextFileName != null )
      {
         if ( stringAltTextFileName.isEmpty( ) == false && stringAltTextFileName.charAt( 0 ) != '@' )
         {
            cbAtAltTextFileName.put( 0, '@' );
            zstrcpy( cbAtAltTextFileName, 1, stringAltTextFileName );
         }
         else
            zstrcpy( cbAtAltTextFileName, 0, stringAltTextFileName );
      }
      else
         cbAtAltTextFileName.put( 0, '\0' );

      // Read the data from the Body and Attachment files into memory and call
      // StartEmailClient with those values.

      // Read the Body into memory.
   // lFileLth = ReadFileDataIntoMemory( ResultSet, stringBodyFileName,
   //                                    &selBodyMemory, &stringBodyMemoryStart );

      // Exit if the file is empty or if there is an error opening it.
   // if ( lFileLth <= 0 )
   // {
         // The memory allocated to hold the body has been freed.
   //    IssueError( ResultSet, 0, 0, "Can't open Email file." );
   //    return -1;
   // }

      if ( stringSubjectLine == null || stringSubjectLine.isEmpty( ) )
         stringSubjectLine = " ";

   // TraceLine( "SendEmailForFiles2 Server: %s   Sender: %s   Recipient: %s"
   //            "   Subject: %s   Mime Type: %d"
   //            "   User: %s   Password %s",
   //            stringSmtpServer, stringSenderEMailAddress, stringRecipientEMailAddress,
   //            stringSubjectLine, nMimeType, stringEMailUserName, stringEMailPassword );

      // If there is an attachment file, also read it into memory.
      // Then call CreateSeeMessage with or without an attachment.
      if ( stringAttachmentFileName.isEmpty( ) == false )
      {
         nRC = m_ZDRVROPR.CreateSeeMessage( lConnection,
                                 stringSmtpServer,
                                 stringSenderEMailAddress,
                                 stringRecipientEMailAddress,
                                 "", "",
                                 stringSubjectLine,
                                 nMimeType,
                                 cbAtBodyFileName.toString( ),
                                 cbAtAltTextFileName.toString( ),
                                 stringEmbeddedImages,
                                 1,    // has attachment
                                 stringAttachmentFileName,
                                 stringEMailUserName,
                                 stringEMailPassword );
      }
      else
      {
         nRC = m_ZDRVROPR.CreateSeeMessage( lConnection,
                                 stringSmtpServer,
                                 stringSenderEMailAddress,
                                 stringRecipientEMailAddress,
                                 "", "",
                                 stringSubjectLine,
                                 nMimeType,
                                 cbAtBodyFileName.toString( ),
                                 cbAtAltTextFileName.toString( ),
                                 stringEmbeddedImages,
                                 0,    // no attachment
                                 "",   // blank attachment file name
                                 stringEMailUserName,
                                 stringEMailPassword );
      }

   // SysFreeMemory( selBodyMemory );
   // DrFreeTaskMemory( stringBodyMemoryStart );

      return nRC;

   } // StartEmailClientWithFiles

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SendEmailForFilesWithCC
   //
   //    Same function as SendEmailForFiles, except it supports CC and BCC copies.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SendEmailForFilesWithCC( View   ViewToWindow,
                            View   ResultSet,
                            String stringSmtpServer,
                            String stringRecipientEMailAddress,
                            String stringSenderEMailAddress,
                            String stringCC_EMailAddresses,
                            String stringBCC_EMailAddresses,
                            String stringEMailUserName,
                            String stringEMailPassword,
                            String stringSubjectLine,
                            String stringBodyFileName,
                            String stringAltTextFileName,
                            String stringEmbeddedImages,
                            String stringAttachmentFileName,
                            int    nMimeType,     // 1-Text, 2-HTML
                            int    lConnection )
   {
   // String stringBodyMemoryStart;
      CharBuffer   cbAtBodyFileName = CharBuffer.allocate( 256 );
      CharBuffer   cbAtAltTextFileName = CharBuffer.allocate( 256 );
   // int  selBodyMemory;
   // int  lFileLth;
      zVIEW  zqMDocOLST = null;
      zVIEW  wWebXfer = null;
      int nRC;

   // TraceLine( "SendEmailForFiles Server: %s   Sender: %s   Recipient: %s"
   //            "   Subject: %s   Mime Type: %d"
   //            "   User: %s   Password %s",
   //            stringSmtpServer, stringSenderEMailAddress, stringRecipientEMailAddress,
   //            stringSubjectLine, nMimeType, stringEMailUserName, stringEMailPassword );

      // First make sure the email address is valid. If not exit with return code of 2.
      if ( IsEmailAddressValid( stringRecipientEMailAddress ) == false )
         return 2;

      GetViewByName( zqMDocOLST, "zqMDocOLST", ResultSet, zLEVEL_TASK );
      GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

      if ( stringBodyFileName != null )
      {
         if ( stringBodyFileName.isEmpty( ) == false && stringBodyFileName.charAt( 0 ) != '@' )
         {
            cbAtBodyFileName.put( 0, '@' );
            zstrcpy( cbAtBodyFileName, 1, stringBodyFileName );
         }
         else
            zstrcpy( cbAtBodyFileName, 0, stringBodyFileName );
      }
      else
         cbAtBodyFileName.put( 0, '\0' );

      if ( stringAltTextFileName != null )
      {
         if ( stringAltTextFileName.isEmpty( ) == false && stringAltTextFileName.charAt( 0 ) != '@' )
         {
            cbAtAltTextFileName.put( 0, '@' );
            zstrcpy( cbAtAltTextFileName, 1, stringAltTextFileName );
         }
         else
            zstrcpy( cbAtAltTextFileName, 0, stringAltTextFileName );
      }
      else
         cbAtAltTextFileName.put( 0, '\0' );

      // Read the data from the Body and Attachment files into memory and call
      // StartEmailClient with those values.

      // Read the Body into memory.
   // lFileLth = ReadFileDataIntoMemory( ResultSet, stringBodyFileName,
   //                                    &selBodyMemory, &stringBodyMemoryStart );

      // Exit if the file is empty or if there is an error opening it.
   // if ( lFileLth <= 0 )
   // {
         // The memory allocated to hold the body has been freed.
   //    IssueError( ResultSet, 0, 0, "Can't open Email file." );
   //    return -1;
   // }

      if ( stringSubjectLine == null || stringSubjectLine.isEmpty( ) )
         stringSubjectLine = " ";

   // TraceLine( "SendEmailForFiles2 Server: %s   Sender: %s   Recipient: %s"
   //            "   Subject: %s   Mime Type: %d"
   //            "   User: %s   Password %s",
   //            stringSmtpServer, stringSenderEMailAddress, stringRecipientEMailAddress,
   //            stringSubjectLine, nMimeType, stringEMailUserName, stringEMailPassword );

      // If there is an attachment file, also read it into memory.
      // Then call CreateSeeMessage with or without an attachment.
      if ( stringAttachmentFileName != null && stringAttachmentFileName.isEmpty( ) == false )
      {
         nRC = m_ZDRVROPR.CreateSeeMessage( lConnection,
                                 stringSmtpServer,
                                 stringSenderEMailAddress,
                                 stringRecipientEMailAddress,
                                 stringCC_EMailAddresses,
                                 stringBCC_EMailAddresses,
                                 stringSubjectLine,
                                 nMimeType,
                                 cbAtBodyFileName.toString( ),
                                 cbAtAltTextFileName.toString( ),
                                 stringEmbeddedImages,
                                 1,    // has attachment
                                 stringAttachmentFileName,
                                 stringEMailUserName,
                                 stringEMailPassword );
      }
      else
      {
         nRC = m_ZDRVROPR.CreateSeeMessage( lConnection,
                                 stringSmtpServer,
                                 stringSenderEMailAddress,
                                 stringRecipientEMailAddress,
                                 stringCC_EMailAddresses,
                                 stringBCC_EMailAddresses,
                                 stringSubjectLine,
                                 nMimeType,
                                 cbAtBodyFileName.toString( ),
                                 cbAtAltTextFileName.toString( ),
                                 stringEmbeddedImages,
                                 0,    // no attachment
                                 "",   // blank attachment file name
                                 stringEMailUserName,
                                 stringEMailPassword );
      }

   // SysFreeMemory( selBodyMemory );
   // DrFreeTaskMemory( stringBodyMemoryStart );

      return nRC;

   } // StartEmailClientWithFiles

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ReturnSuffixOfFileName
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public String
   ReturnSuffixOfFileName( String stringReturnedSuffix,
                           String stringFileName )
   {
      int nPosition;

      nPosition = zstrrchr( stringFileName, '.' );  // find last period
      if ( nPosition >= 0 )  // if we found the last period ...
      {
         stringReturnedSuffix = stringFileName.substring( nPosition + 1 );  // ... we have our ext!
         stringReturnedSuffix = stringReturnedSuffix.toLowerCase( );
      }
      else
         stringReturnedSuffix = "";  // initialize to empty extension

      return stringReturnedSuffix;

   } // ReturnSuffixOfFileName

   /////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SubSectionShowHideDisableTabs
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   SubSectionShowHideDisableTabs( View    mUser,
                                  View    vSubtask,
                                  String  stringTabTag )
   {
      return 0;  // TODO fnSubSectionShowHideDisableTabs( mUser, vSubtask, stringTabTag );
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: WL_QC
   //
   //  PURPOSE:    This routine Converts an instance of a special character in
   //              a buffer and then writes out the buffer. The character to
   //              be translated is stringTransChar and any instance of it is
   //              converted to a double quote.
   //
   //  PARAMETERS: lFile - File handle
   //              stringBuffer - the string to be converted.
   //              stringTransChar - The character to be converted to a quote.
   //              nAddBlankLineCnt - Number of blank lines to append.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   WL_QC( View vAnyView,
          int    lFile,
          String stringInput,
          String stringTransChar,
          int    nBlankLineCnt ) throws IOException
   {
      //stringInput = stringInput.replaceAll( stringTransChar, "\"" );
      stringInput = stringInput.replace( stringTransChar, "\"" );
   // TraceLineS( "#### WL_QC: ", stringInput );
      m_KZOEP1AA.SysWriteLine( vAnyView, lFile, stringInput );
      while ( nBlankLineCnt-- > 0 )
         m_KZOEP1AA.SysWriteLine( vAnyView, lFile, "" );

      return 0;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SplitParagraphOnLinefeed
   //
   //  PURPOSE: Convert several lines of string data into separate entity instances.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   /*
   private String trimEnd( String raw ) {
      int pos = raw.length();
      while ( (pos > 0) && Character.isWhitespace( raw.charAt( pos - 1 ) ) ) {
         pos--;
      }
      return (pos < raw.length()) ? raw.substring( 0, pos ) : raw;
   }
   */
   public int
   SplitParagraphOnLinefeed( String paragraph,
                             View   view,
                             String entityName,
                             String attributeName,
                             String delimiters )
   {
   // TraceLineS( "SplitParagraphOnLinefeed delimiters: ", delimiters );
      String replace;
      String value;
      int count = 0;
      EntityCursor ec = view.cursor( entityName );
      int pos = 0;
      int delimEnd = delimiters.length();
      if ( delimiters.isEmpty() ) {
         delimiters = "\r\n";
      } else {
         while ( (pos = delimiters.indexOf( "\\", pos ) ) >= 0 ) {
            replace = "\\";
            int adjust = 1;
            if ( pos < delimEnd - 1 ) {
               adjust = 2;
               char ch = delimiters.charAt( pos + 1 );
               if ( ch == 't' ) {
                  replace = "\t";
               } else if ( ch == 'r' ) {
                  replace = "\r";
               } else if ( ch == 'n' ) {
                  replace = "\n";
               } else {
                  adjust = 1;
               }
            }
            if ( pos > 0 ) {
               value = delimiters.substring( 0, pos ) + delimiters.substring( pos + adjust, delimEnd );
            } else {
               value = delimiters.substring( pos + adjust, delimEnd );
            }
            delimiters = replace + value;
            delimEnd = delimiters.length();
            pos++;
         }
      }

      delimEnd = delimiters.length();
      int lth = view.getLodDef().getEntityDef( entityName ).getAttribute( attributeName ).getLength();
      int posPrev = 0;
      int end = paragraph.length();
      int k;
      String token;
      pos = 0;
      while ( pos <= end && posPrev < end ) {
         for ( k = 0; k < delimEnd; k++ ) {
            if ( (posPrev < pos && pos == end) || paragraph.charAt( pos ) == delimiters.charAt( k ) ) {
               token = paragraph.substring( posPrev, pos );
               posPrev = pos + 1;
               value = token.trim();
               if ( value.length() >= lth ) {
                  TraceLineS( "Truncating value in split: ", value );
                  value = value.substring( 0, lth );
               }
               if ( value.isEmpty() == false ) {
               // TraceLineS( value, "|" );
                  count++;
                  ec.createEntity( CursorPosition.LAST );
                  ec.getAttribute( attributeName ).setValue( value );
               }
               break;
            }
         }
         pos++;
      }

      return count;
   }

   public void
   SetFirstCharacterCase( StringBuilder sbTarget, int bUpper )
   {
      if ( sbTarget.length() > 0 )
      {
         char ch;
         if ( bUpper != 0 )
         {
            ch = Character.toUpperCase(sbTarget.charAt(0));
         }
         else
         {
            ch = Character.toLowerCase(sbTarget.charAt(0));
         }
         sbTarget.setCharAt(0, ch);
      }
   }

   public String
   RemoveInvalidCharactersFromFilename( String in ) {
      // Valid characters: Letters (a-z A-Z)  Digits (0-9)  Underscore (_)   Hyphen (-)   Space   Dot (.)
      StringBuilder sbFileName = new StringBuilder( in.length() );
      char ch;
      int k;
      int pos = 0;
      for ( k = 0; k < sbFileName.length(); k++ ) {
         ch = in.charAt( k );
         // Not permitting spaces or dashes or periods
         if ( (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '_' ) {
            sbFileName.setCharAt( pos++, ch );
         }
      }
      sbFileName.setLength( pos );
      return sbFileName.toString();
   }

   public int
   GenerateKeywordTextIntoString( View mMasLC,
                                  StringBuilder sbTextToModify,
                                  String szKeywordEntityName,
                                  String szKeywordTextEntityName,
                                  String szSeparatorCharacters )
   {
      StringBuilder sbTarget = new StringBuilder();
      String   szOrigSource = sbTextToModify.toString();
      int      sourceLth = szOrigSource.length();
      String   szKeywordName = null;
      String   szKeywordValue = null;
      char     chKeywordType;
      boolean  changed = false;
      int      sourcePos = 0;  // keeps track of last position in source copied to target
      int      openBracePos = 0;
      int      closeBracePos;
      int      traverse = 1;
      int      count;
            
      // Insert Keyword text items into a position in szStringArea that is identified by the Keyword.
      // The entries inserted will be separated by one or more characters as identified by the variable szSeparatorCharacters.
      // After determining the position of the insertion, we will loop through Keyword entries, formatting each entry as we go.
      sbTarget.setLength( 0 );
      openBracePos = szOrigSource.indexOf( "{{", sourcePos );
      // Don't do the hasAny check since there may be only global keywords
      if ( openBracePos >= 0 ) { // && mMasLC.cursor( szKeywordEntityName ).hasAny() ) {  // looks like it's worth processing
         View mMasLC2 = mMasLC.newView( );
         mMasLC2.copyCursors( mMasLC );

         while ( openBracePos >= 0 ) {
            // Get to the innermost set of "{{"
            while ( szOrigSource.charAt( openBracePos + 2) == '{' ) {
               openBracePos++;
            }
            // Copy static text up to the brace to the target.
            sbTarget.append( szOrigSource.substring( sourcePos, openBracePos ) );

            // Parse the Keyword out of the string of the form {{xxxxx}}.
            closeBracePos = szOrigSource.indexOf( "}}", openBracePos + 2 );
            if ( closeBracePos >= 0 ) {
               changed = true;
               szKeywordName = szOrigSource.substring( openBracePos + 2, closeBracePos );
               sourcePos = closeBracePos + 2; // point to the next static text portion in the original source string
               count = 0;

               CursorResult cr = mMasLC2.cursor( szKeywordEntityName ).setFirst( "Name", szKeywordName );
               if ( cr.isSet() ) {
                  // Type values: All optional, Only one allowed, Required (at least one)
                  String szType = mMasLC2.cursor( szKeywordEntityName ).getAttribute( "Type" ).getString();
                  if ( szType == "" ) {
                     chKeywordType = 'A';
                  } else {
                     chKeywordType = szType.charAt( 0 );
                  }
                  if ( chKeywordType == 'R' || chKeywordType == 'X' ) {
                     sbTarget.append( '[' );
                  }
                  while ( cr.isSet() ) {
                     // There are Text entries for the Keyword specified, so loop through all.
                     count++;
                     szKeywordValue = mMasLC2.cursor( szKeywordTextEntityName ).getAttribute( "Text" ).getString();
                     if ( count > 1 ) {
                        // If szSeparatorCharacters are ", ", substitute " and " for the separator characters before the last Usage entry.
                        if ( mMasLC2.cursor( szKeywordTextEntityName ).hasNext() ) {
                           sbTarget.append( szSeparatorCharacters );
                        } else {
                           if ( chKeywordType == 'O' || chKeywordType == 'X' ) {
                              sbTarget.append( " or " );
                           } else {
                              sbTarget.append( szSeparatorCharacters );
                           }
                        }
                     }
                     if ( traverse > 2 ) {
                        sbTarget.append( "{" + szKeywordValue + "}" );
                     } else if ( traverse > 1 ) {
                        sbTarget.append( "<i>{" + szKeywordValue + "}</i>" );
                     } else {
                        sbTarget.append( "<b>{" + szKeywordValue + "}</b>" );
                     }
                     cr = mMasLC2.cursor( szKeywordTextEntityName ).setNext( );
                  }
                  if ( chKeywordType == 'R' || chKeywordType == 'X' ) {
                     sbTarget.append( ']' );
                  }
               } else if ( szKeywordName.length() > 0 && szKeywordName.charAt(0) == '#' ) {
                  // underline "global keywords"
                  sbTarget.append( "<u>{" + szKeywordName + "}</u>" );
               } else {
                  // notify user that the keyword is not found
                  sbTarget.append( "{{'" + szKeywordName + "' not found}} " );
                  TraceLineS( "### Keyword NOT Found: "+ szKeywordName + "   in Statement: ", szOrigSource );
                  cr = mMasLC2.cursor( szKeywordEntityName ).setFirst();
                  while ( cr.isSet() ) {
                     szKeywordValue = mMasLC2.cursor( szKeywordEntityName ).getAttribute( "Name" ).getString();
                     TraceLineS( "### Current Keyword : ", szKeywordValue );
                     cr = mMasLC2.cursor( szKeywordEntityName ).setNext();
                  }
                  break;
               }
            } else {
               break;  // improper close braces (matching not found)
            }
            openBracePos = szOrigSource.indexOf( "{{", sourcePos );
            if ( openBracePos < 0 && changed && traverse < 3 ) {
               traverse++;
               sbTextToModify.setLength( 0 );
               sbTextToModify.append( sbTarget.toString() );

               // Move in remaining characters.
               if ( sourcePos < sourceLth ) {
                  sbTextToModify.append( szOrigSource.substring( sourcePos ) );
               }
               szOrigSource = sbTextToModify.toString();
               sourceLth = szOrigSource.length();
               sourcePos = 0;
               sbTarget.setLength( 0 );
               openBracePos = szOrigSource.indexOf( "{{", sourcePos );
            }
         }
         mMasLC2.drop();
      }
      if ( changed ) {
         sbTextToModify.setLength( 0 );
         sbTextToModify.append( sbTarget.toString() );

         // Move in remaining characters.
         if ( sourcePos < sourceLth ) {
            sbTextToModify.append( szOrigSource.substring( sourcePos ) );
         }
      }

      return( 0 );
   }
   
   public int
   InsertKeywordsIntoString( View     mSPLDef,
                             StringBuilder sbTextToModify,
                             String   szUsageTypeEntityName,
                             String   szLoopingEntityName,
                             String   szSeparatorCharacters )
   {
      StringBuilder sbTarget = new StringBuilder();
      String   szOrigSource = sbTextToModify.toString();
      int      sourceLth = szOrigSource.length();
      String   szUsageType = null;
      String   szUsageValue = null;
      String   szInsertValue = null;
      String   szSelectUsageType = null;
      boolean  changed = false;
      int      count = 0;
      int      sourcePos = 0;  // keeps track of last position in source copied to target
      int      openBracePos = 0;
      int      closeBracePos;
      int      nRC = 0;

      // Insert Usage text into a position in szStringArea that is identified by a Usage Keyword.
      // The entries inserted will be separated by one or more characters as identified by the variable szSeparatorCharacters.
      // After determining the position of the insertion, we will loop through Usage entries, formatting each entry as we go.
   // TraceLineS( "### Insert szUsageTypeEntityName: ", szUsageTypeEntityName )
   // TraceLineS( "### Insert szLoopingEntityName: ", szLoopingEntityName )

   // For use in: {{Location}}. Disinfects, cleans, and deodorizes the following hard nonporous inanimate surfaces: {{Surface}}, etc.).
   // 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123  << lth = 133
   //          1         2         3         4         5         6         7         8         9        10        11        12        13
      sbTarget.setLength( 0 );
      openBracePos = szOrigSource.indexOf( "{{", sourcePos );
      while ( openBracePos >= 0 ) {
         // Copy static text up to the brace to the target.
         sbTarget.append( szOrigSource.substring( sourcePos, openBracePos ) );

         // Parse the Usage Type out of the string of the form {{xxxxx}}.
         closeBracePos = szOrigSource.indexOf( "}}", openBracePos + 2 );
         if ( closeBracePos >= 0 ) {
            changed = true;
            szUsageType = szOrigSource.substring( openBracePos + 2, closeBracePos );
            sourcePos = closeBracePos + 2; // point to the next static text portion in the original source string

            // Copy the Usage values into the text. This will depend on Type.

            // Claim, Surface, Location, and Application Type.
            if ( szUsageType.equals( "Claim" ) || szUsageType.equals( "Claims" ) ||
                 szUsageType.equals( "Surface" ) || szUsageType.equals( "Surfaces" ) ||
                 szUsageType.equals( "Location" ) || szUsageType.equals( "Locations" ) ||
                 szUsageType.equals( "Application Type" ) ||  szUsageType.equals( "Application Types" ) ) {

               if ( szUsageType.equals( "Claim" ) || szUsageType.equals( "Claims" ) ) {
                  szSelectUsageType = "C";
               } else if ( szUsageType.equals( "Surface" ) || szUsageType.equals( "Surfaces" ) ) {
                  szSelectUsageType = "S";
               } else if ( szUsageType.equals( "Location" ) || szUsageType.equals( "Locations" ) ) {
                  szSelectUsageType = "U";
               } else {
                  szSelectUsageType = "T";
               }

               // Process Usage entries if the Looping Entity Name is specified.
               if ( szLoopingEntityName.isEmpty() == false ) {  // SPLD_MarketingUsageOrdering

                  count = 0;
                  nRC = mSPLDef.cursor( szLoopingEntityName ).setFirst().toInt();
                  while ( nRC >= zCURSOR_SET ) {
                     szUsageType = mSPLDef.cursor( szUsageTypeEntityName ).getAttribute( "UsageType" ).getString();  // "U"
                     if ( szUsageType.equals( szSelectUsageType ) ) {
                        // There is a match on UsageType.
                        // Copy Usage variable to text. If not first entry, put in a separator character.
                        count++;
                        szUsageValue = mSPLDef.cursor( szUsageTypeEntityName ).getAttribute( "Name" ).getString();  // "Airports"
                        if ( count > 1 ) {
                           // If szSeparatorCharacters are ", ", substitute " and " for the separator characters before the last Usage entry.
                           if ( mSPLDef.cursor( szLoopingEntityName ).hasNext() == false )
                              sbTarget.append( " and " );
                           else
                              sbTarget.append( szSeparatorCharacters );
                        }
                        sbTarget.append( szUsageValue );
                     }
                     nRC = mSPLDef.cursor( szLoopingEntityName ).setNext().toInt();
                  }
               }
            } else if ( szUsageType.equals( "Product Name" ) ) {
               //:szInsertValue = mSPLDef.SubregPhysicalLabelDef.ProductName
               szInsertValue = mSPLDef.cursor( "SubregPhysicalLabelDef" ).getAttribute( "ProductName" ).getString();
               sbTarget.append( szInsertValue );
            }
         } else {
            sbTextToModify.append( " ### Error replacing keywords" );
            return -1;
         }

         openBracePos = szOrigSource.indexOf( "{{", sourcePos );
      }

      if ( changed ) {
         sbTextToModify.setLength( 0 );
         sbTextToModify.append( sbTarget.toString() );

         // Move in remaining characters.
         if ( sourcePos < sourceLth ) {
            sbTextToModify.append( szOrigSource.substring( sourcePos ) );
         }
      }

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////

   public int
   InsertOptionalSubUsages( View     mMasLC,
                            StringBuilder sbTextToModify,
                            String   stringEntity,
                            int      bInsertBraces )
   {
      StringBuilder sbTarget = new StringBuilder();
      String   szOrigSource = sbTextToModify.toString();
      String   szOpenBrace = "{";
      boolean  changed = false;
      int      openBracePos = 0;
      int      closeBracePos;
      int      nRC = 0;

      if ( bInsertBraces != 0 )
         szOpenBrace = "{";
      else
         szOpenBrace = "";
      
   // Food [{{}}] areas
   // preparation storage
   // Automobile [{{}}]
   // interiors mats crates cabs wheels
   // 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123  << lth = 133
   //          1         2         3         4         5         6         7         8         9        10        11        12        13
      sbTarget.setLength( 0 );

      // Parse the double braces out of the string of the form {{}}.
      openBracePos = szOrigSource.indexOf( "{{", 0 );
      closeBracePos = szOrigSource.indexOf( "}}", openBracePos + 2 );
      if ( openBracePos >= 0 && closeBracePos >= 0 && (nRC = mMasLC.cursor( stringEntity ).setFirst().toInt()) >= zCURSOR_SET ) {
         // Copy static text up to the brace to the target.
         sbTarget.append( szOrigSource.substring( 0, openBracePos ) );

         // Copy the SubUsage values into the text - surounded by single braces to signify the values are optional.
         while ( nRC >= zCURSOR_SET ) {
            sbTarget.append( szOpenBrace + mMasLC.cursor( stringEntity ).getAttribute( "Name" ).getString() );
            if ( bInsertBraces != 0 )
               sbTarget.append( "}" );
            if ( changed == false ) {
               changed = true;
               if ( bInsertBraces != 0 )
                  szOpenBrace = ", {";
               else
                  szOpenBrace = ", ";
            }
            nRC = mMasLC.cursor( stringEntity ).setNext().toInt();
         }
         sbTarget.append( szOrigSource.substring( closeBracePos + 2 ) ); // append remaining static text in the original source string
      }

      if ( changed ) {
         sbTextToModify.setLength( 0 );
         sbTextToModify.append( sbTarget.toString() );
      }

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////

   private String
   ScrunchCamelCase( String s )
   {
      if ( s.length() < 2 )
         return s;

      if ( s.charAt( 0 ) == '(' && s.charAt( s.length() - 1 ) == ')' )
         s = s.substring( 1, s.length() - 1 );

      String[] sub = s.split( " " );
      StringBuilder sb = new StringBuilder();
      int k = 0;
      while ( k < sub.length ) {
         if ( sub[ k ].length() > 0 && (sub.length == 1 || (sub[ k ].compareToIgnoreCase( "or" ) != 0 && sub[ k ].compareToIgnoreCase( "and" ) != 0)) ) {
            sb.append( sub[ k ].substring( 0, 1 ).toUpperCase() ).append( sub[ k ].substring( 1 ) );
         }
         k++;
      }
      k = sb.indexOf( "PpmActive" );
      if ( k >= 0 ) {
         sb.replace( k, k + 9, "PPM" );
      }
      return sb.toString();
   }

   public class TreeNode {
      char type;  // bracket, semaphore, text
      char close; // close bracket/semaphore/null
      int  startPos;
      int  endPos;
      String text;
      String scrunch;
      TreeNode parent;
      LinkedList<TreeNode> children;

      public TreeNode( char type, char close, String text, int startPos, int endPos ) {
         this.type = type;
         this.close = close;
         this.startPos = startPos;
         this.endPos = endPos;
         this.text = text;
         this.children = new LinkedList<TreeNode>();
      }

      public TreeNode addChild( char type, char close, String text, int startPos, int endPos ) {
         TreeNode childNode = new TreeNode( type, close, text, startPos, endPos );
         childNode.parent = this;
         children.add( childNode );
         return childNode;
      }

      // This product {{{{when used as directed}} {{can be used}} {{is formulated to {{{{disinfect}} {{clean}} {{sanitize}} {{deodorize}}}}}} {{is formulated for use}}}} on {{washable}} hard, non-porous surfaces such as: (insert surface)
      //          1         2         3         4         5         6         7         8         9        10        11        12        13        14        15        16        17        18        19        20        21    
      // 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890  << lth = 205
      // This product [{when used as directed} {can be used} {is formulated to [{disinfect} {clean} {sanitize} {deodorize}] {is formulated for use}] on {washable} hard, non-porous surfaces such as: (insert surface)

      private String buildKeywords( View mMasLC, String keywordEntity, String keywordTextEntity, int level ) {

         String statement = "";
         String keyword;
         String keywordValue;

         for ( TreeNode tnode : children ) {

            if ( tnode.type == 'T' ) {
               if ( tnode.parent.type != '[' && tnode.parent.type != 'T' ) {
                  keywordValue = tnode.text;
                  keyword = ScrunchCamelCase( keywordValue );
                  statement += keyword;
                  mMasLC.cursor( keywordTextEntity ).createEntity();
                  mMasLC.cursor( keywordTextEntity ).getAttribute( "Text" ).setValue( keywordValue );
               } else {
                  statement += tnode.text;
               }
            } else if ( type == tnode.type ) { // drop down a level
               statement += tnode.buildKeywords( mMasLC, keywordEntity, keywordTextEntity, level + 1 );
            } else if ( type == '[' ) {
            // if ( tnode.children.size() > 0 ) {  // should always be true
               keywordValue = "";
               keyword = "";
               for ( TreeNode tchild : tnode.children ) {
                  if ( tchild.type == 'T' ) {
                     keywordValue += tchild.text;
                     keyword += ScrunchCamelCase( keywordValue );
                  } else {
                     keywordValue += "{{" + ScrunchCamelCase( tchild.buildKeywords( mMasLC, keywordEntity, keywordTextEntity, level + 1 ) ) + "}}";
                  }
               }
               mMasLC.cursor( keywordTextEntity ).createEntity();  // should happen with type = '[' and tnode.type = '{'
               mMasLC.cursor( keywordTextEntity ).getAttribute( "Text" ).setValue( keywordValue );
               statement += keyword;
            }
         }

      // TraceLine( "### Build Value: %s   level: %d   scrunch: %s", text, level, scrunch );
         return statement;   
      }

      // This product [{when used as directed} {can be used} {is formulated to [{disinfect} {clean} {sanitize} {deodorize}] {is formulated for use}] on {washable} hard, non-porous surfaces such as: (insert surface)
      public String TraverseGoGetIt( View mMasLC, String entity, String attribute, String keywordEntity, String keywordTextEntity, int level ) {

         String statement = "";
         String keyword;
         String keywordValue;
 
         // At the root.
         for ( TreeNode tnode : children ) {
            if ( tnode.type == 'T' ) {
               statement += tnode.text;
               if ( tnode.children.size() > 0 ) {  // should never be true
                  TraceLine( "TraverseGoGetIt has a text ('%s') node with children: %d", statement, tnode.children.size() );
               }
            } else {
               if ( level % 2 == 0 ) {
                  mMasLC.cursor( keywordEntity ).createEntity();
                  mMasLC.cursor( keywordEntity ).getAttribute( "Type" ).setValue( tnode.type == '[' ? "R" : "A" );
                  keywordValue = tnode.buildKeywords( mMasLC, keywordEntity, keywordTextEntity, 0 );
                  keyword = ScrunchCamelCase( keywordValue );
                  mMasLC.cursor( keywordEntity ).getAttribute( "Name" ).setValue( keyword );
                  statement += "{{" + keyword + "}}";
               }
               tnode.TraverseGoGetIt( mMasLC, entity, attribute, keywordEntity, keywordTextEntity, level + 1 );
            }
         }

         TraceLine( "TraverseGoGetIt statement: %s   level: %d", statement, level );
         return statement;
      }
   }

   private int
   ParseRecursiveKeywords( TreeNode tnode,
                           String   statement,
                           int      pos,
                           char     openSemaphore,
                           char     closeSemaphore )
   {
      TreeNode childNode;
      char ch;
      int  k;
      int  rc;
      int  start = pos;

      //          1         2         3         4         5         6         7         8         9        10        11        12        13
      // 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123  << lth = 133
      // [{Malodor Activity} {Odor} {Counteract}] - [{eliminates} {destroys}] odors {and odor-causing bacteria on hard, non-porous surfaces in restroom areas,
      //    behind and under sinks and counters, and storage areas {and other {hard, non-porous} surfaces} where bacterial growth can cause malodors.}
      // Is great for use [{on} {in the}] [{kitchen}, {bathroom}, {floors} {and} {other household areas}].
      // Is effective against household [{germs} {bacteria}].
      // 0.75 oz. of this product per 4 gal. of water {(0.19 oz. per gal. of water)} {(150 ppm active quat)}{(or equivalent use dilution)} 
      //          1         2         3         4         5         6         7         8         9        10        11        12        13        14        15        16        17        18        19        20        21    
      // 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890  << lth = 205
      // This product [{when used as directed} {can be used} {is formulated to [{disinfect} {clean} {sanitize} {deodorize}] {is formulated for use}] on {washable} hard, non-porous surfaces such as: (insert surface)
      for ( k = pos; k < statement.length(); k++ ) {
         ch = statement.charAt( k );
         if ( ch == '[' || ch == openSemaphore ) {  // open
            if ( start < k ) {
               childNode = tnode.addChild( 'T', '\0', statement.substring( start, k ), start, k );
            }
            childNode = tnode.addChild( ch, ch == '[' ? ']' : closeSemaphore, "", k, -1 );
            rc = ParseRecursiveKeywords( childNode, statement, k + 1, openSemaphore, closeSemaphore );
            if ( rc >= 0 ) {
               k = rc;
               start = k + 1;
            } else {
               return rc;
            }
         } else if ( ch == ']' || ch == closeSemaphore ) {
            if ( start < k ) {
               childNode = tnode.addChild( 'T', '\0', statement.substring( start, k ), start, k );
            }
            if ( ch == tnode.close ) {  // close
               tnode.endPos = k;
               return k;
            } else {
               return k - 1;
            }
         } else {
            start = k++;
            while ( k < statement.length() ) {
               ch = statement.charAt( k );
               if ( ch == '[' || ch == openSemaphore || ch == ']' || ch == closeSemaphore ) {
                  k--; // decrement to set up for increment in for loop
                  break;  // out of inner while
               }
               k++;
               if ( k == statement.length() ) {
                  childNode = tnode.addChild( 'T', '\0', statement.substring( start, k ), start, k );
               }
            }
         }
      }
      return statement.length();
   }
   
   public int
   ParseStatementForKeywords( View     mMasLC,
                              String   entity,
                              String   attribute,
                              String   keywordEntity,
                              String   keywordTextEntity,
                              String   keywordSemaphore )
   {

      if ( keywordSemaphore.length() < 2 )
         keywordSemaphore = "{}";

      char openSemaphore = keywordSemaphore.charAt( 0 );
      char closeSemaphore = keywordSemaphore.charAt( 1 );

      Object value = mMasLC.cursor( entity ).getAttribute( attribute ).getValue();
      if ( value == null )
         return -1;

      mMasLC = ((zVIEW) mMasLC).getView( );
      String statement = value.toString();
                // 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123  << lth = 133
   // statement = "This product [{when used as directed} {can be used} {is formulated to [{disinfect} {clean} {sanitize} {deodorize}]} {is formulated for use}] on {washable} hard, non-porous surfaces such as: (insert surface)";
      TreeNode root = new TreeNode( 'T', '\0', "", 0, statement.length() );
      int rc = ParseRecursiveKeywords( root, statement, 0, openSemaphore, closeSemaphore );
      if ( rc >= 0 ) {
         statement = root.TraverseGoGetIt( mMasLC, entity, attribute, keywordEntity, keywordTextEntity, 0 );
         mMasLC.cursor( entity ).getAttribute( attribute ).setValue( statement );
      // mMasLC.cursor( entity ).logEntity( true );
         return 0;
      }

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////

   public int
   FixLegacyReportDate( View wWebXfer, View vLegacyTranscript,
                        String stringEntity, String stringAttribute,
                        String searchString )
   {
      String stringBlob = null;
      String stringDate = null;
      MutableInt  ulBlobLth = new MutableInt( 0 );
      int  lSearchLth = zstrlen( searchString );

      if ( lSearchLth != 0 )
      {
         stringDate = GetStringFromAttributeByContext( stringDate, wWebXfer, "Root",
                                                       "dCurrentDate", "MonthDDYYYY", 64 );
      // lLth = stringDate.length( );
         stringBlob = GetAddrForAttribute( stringBlob, vLegacyTranscript, stringEntity, stringAttribute );
         GetAttributeLength( ulBlobLth, vLegacyTranscript, stringEntity, stringAttribute );
         stringBlob = stringBlob.replaceAll( searchString, stringDate );

         // this could never have worked since the fixed date is not set/returned!!!  dks 2010.04.07
         return ulBlobLth.intValue( );
      }

      return zCALL_ERROR;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  Return number of entities written.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   CreateBlobsFromLegacyTranscript( View    vSubtask,
                                    String  stringObjectName,
                                    String  stringLegacyAttributeName,
                                    String  stringLegacyTranscriptFileSpec )
   {
      // TODO
      return 0; // fnCreateBlobsFromLegacyTranscript( vSubtask, stringObjectName,
                //                                    stringLegacyAttributeName, stringLegacyTranscriptFileSpec );
   }

   int
   AddAttributeToCSV( CharBuffer cb, int nLth, View  lLibPers,
                      String entityName, String attributeName, boolean bNumeric )
   {
      String s = null;

      cb.put( 0, '"' );  // opening quote

   // if ( bNumeric )
   // {
         s = GetStringFromAttribute( s, lLibPers, entityName, attributeName );
         nLth = zstrcpy( cb, 1, s );
   // }
   // else
   // {
   //    String stringAttrib;
   //
   //    GetAddrForAttribute( &stringAttrib, lLibPers, entityName, stringAttribute );
   //    zstrcpy( stringBuffer, stringAttrib );
   // }


      s = cb.toString( );
      if ( s.indexOf( '"', 1 ) > 0 )
      {
         s = s.replace( "\"", "\"\" " );  // double any quotes ...
         s = s.substring( 1 );            // except the first one
      }

      s += "\",";   // terminating quote plus comma
      nLth = zstrcpy( cb, 0, s );
      return nLth;
   }

   int
   WriteCSV_RecordFromEntity( View  lLibPers, String entityName, int lFile ) throws IOException
   {
      CharBuffer charBuffer = CharBuffer.allocate( 32000 );
      int nLth;

      charBuffer.put( 0, '"' );
      charBuffer.put( 1, entityName.charAt( 0 ) );  // S E P (Student Employee Prospect)
      charBuffer.put( 2, '"' );
      charBuffer.put( 3, ',' );
      nLth = 4;

      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                 "Status", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "CampusID", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "ID", true );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "LastName", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "FirstName", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "MiddleName", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "Suffix", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "PreferedFirstName", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "Gender", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "MaritalStatus", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "HomePhone", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "WorkPhone", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "Extension", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "eMailAddress", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Person",
                                 "DateOfBirth", false );
      if ( CheckExistenceOfEntity( lLibPers, "Address" ) == 0 )
      {
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Address",
                                    "Line1", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Address",
                                    "City", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Address",
                                    "StateProvince", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Address",
                                    "PostalCode", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "Address",
                                    "Country", false );
      }
      else
      {
         charBuffer.put(  nLth++, ',' );
         charBuffer.put(  nLth++, ',' );
         charBuffer.put(  nLth++, ',' );
         charBuffer.put(  nLth++, ',' );
         charBuffer.put(  nLth++, ',' );
      }

      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                 "ID", false );
      nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                 "eMailAddress", false );
      if ( entityName.charAt( 0 ) == 'S' )
      {
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                    "CurrentLevel", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, "AdministrativeDivision",
                                    "Name", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                    "ClearingHouseGradDate", false );
      }
      else
      if ( entityName.charAt( 0 ) == 'P' )
      {
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                    "ExpectedEntryTerm", false );
         nLth += AddAttributeToCSV( charBuffer, nLth, lLibPers, entityName,
                                    "ExpectedEntryYear", false );
      }

      if ( nLth > 0 && charBuffer.get( nLth - 1 ) == ',' )
        charBuffer.put(  nLth - 1, '\0' );  // drop terminating ',' and null terminate
      else
        charBuffer.put(  nLth++, '\0' );    // ensure null termination

      m_KZOEP1AA.SysWriteLine( lLibPers, lFile, charBuffer.toString( ) );
      return nLth;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: WriteLibraryOLCN
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   WriteLibraryOLCN( View  lLibPers, int lFile ) throws IOException
   {
      int  lCnt = 0;
      int nRC;

      nRC = SetCursorFirstEntity( lLibPers, "Person", "" );
      while ( nRC >= zCURSOR_SET )
      {
         if ( CheckExistenceOfEntity( lLibPers, "Student" ) == 0 )
         {
            WriteCSV_RecordFromEntity( lLibPers, "Student", lFile );
            lCnt++;
         }
         else
         if ( CheckExistenceOfEntity( lLibPers, "Employee" ) == 0 )
         {
            WriteCSV_RecordFromEntity( lLibPers, "Employee", lFile );
            lCnt++;
         }
         else
         if ( CheckExistenceOfEntity( lLibPers, "Prospect" ) == 0 )
         {
            WriteCSV_RecordFromEntity( lLibPers, "Prospect", lFile );
            lCnt++;
         }

         nRC = SetCursorNextEntity( lLibPers, "Person", "" );
      }

      return lCnt;
   }

 /**
   int
   TraceLastError( DWORD dwError )
   {
      String lpMsgBuf;
      String stringBuffer;
      zULONG ulLth = sizeof( stringBuffer );

      FormatMessage( FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM |
                     FORMAT_MESSAGE_IGNORE_INSERTS, null, dwError,
                     MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // Default language
                     lpMsgBuf, 0, null );

      if ( lpMsgBuf && lpMsgBuf )
      {
         TraceLineI( "Win32 Error Message: ", dwError );
         TraceLineS( "Win32 Error Message: ", (String) lpMsgBuf );
      }

      // Free the buffer.
      LocalFree( lpMsgBuf );

      InternetGetLastResponseInfo( dwError, stringBuffer, ulLth );
      if ( ulLth > 0 )
         TraceLineS( "Internet error: ", stringBuffer );

      return 0;
   }

   public int
   FTPSendFile( View   vSubtask,
                String stringServerAddress,
                String stringUserName,
                String stringPassword,
                String stringLocalFileName,
                String stringServerFileName,
                int  lControl )
   {
      int    nRC         = 0;
      HINTERNET hConnection = 0;
      HINTERNET hFtp        = 0;

      hConnection = InternetOpen( "ftp", INTERNET_OPEN_TYPE_PRECONFIG, 0, 0,
                                  INTERNET_FLAG_ASYNC );
      do  // purist's goto
      {
      if ( hConnection == 0 )
      {
         DWORD dwError = GetLastError();
         TraceLineS( "*ERROR*", "" );
         TraceLastError( dwError );
         TraceLineS( "Server Address   = ", stringServerAddress );
         TraceLineS( "User Name        = ", stringUserName );
         TraceLineS( "Local File Name  = ", stringLocalFileName );
         TraceLineS( "Server File Name = ", stringServerFileName );
         MessageSend ( vSubtask,
                       "AD0450",
                       "FTP",
                       "Error creating an internet connection.",
                       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         break;
      }

      hFtp = InternetConnect( hConnection, stringServerAddress, INTERNET_DEFAULT_FTP_PORT,
                              stringUserName, stringPassword, INTERNET_SERVICE_FTP, 0, 0 );
      if ( hFtp == 0 )
      {
         DWORD dwError = GetLastError();
         TraceLineS( "*ERROR*", "" );
         TraceLastError( dwError );
         TraceLineS( "Server Address   = ", stringServerAddress );
         TraceLineS( "User Name        = ", stringUserName );
         MessageSend ( vSubtask,
                       "AD0451",
                       "FTP",
                       "Error connecting to ftp server.",
                       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         break;
      }

      if ( !FtpPutFile( hFtp, stringLocalFileName, stringServerFileName,
                        FTP_TRANSFER_TYPE_BINARY, 0 ) )
      {
         DWORD dwError = GetLastError();
         TraceLineS( "*ERROR*", "" );
         TraceLastError( dwError );
         TraceLineS( "Server Address   = ", stringServerAddress );
         TraceLineS( "User Name        = ", stringUserName );
         TraceLineS( "Local File Name  = ", stringLocalFileName );
         TraceLineS( "Server File Name = ", stringServerFileName );
         MessageSend ( vSubtask,
                       "AD0452",
                       "FTP",
                       "Error sending file to the server.",
                       zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         break;
      }
      // If we get here then everything's ok, so set return value.
      nRC = 1;

      }  while ( false );  // end of purist's goto

      if ( hConnection != 0 )
         InternetCloseHandle( hConnection );

      if ( hFtp != 0 )
         InternetCloseHandle( hFtp );

      return nRC;
   }
 **/

   //////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //Method Name: ConvertISIR_NumberToAttribute
   //
   //////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   ConvertISIR_NumberToAttribute( View   tgtView,
                                  String tgtEntityName,
                                  String tgtAttributeName,
                                  String stringISIR_Value )
   {
      String stringValue;
      String stringMsg;
      char   charSign;
      char   charLastCharacter = 0;
      char   charLastDigit = 0;
      int    lValue;
      int    nLth;
      @SuppressWarnings("unused") int    nRC;

      // Convert stringISIR_Value from a signed string to the attribute passed into the operation.
      if ( stringISIR_Value == null || stringISIR_Value.isEmpty( ) )
         SetAttributeFromString( tgtView, tgtEntityName, tgtAttributeName, "" );
      else
      {
         nLth = stringISIR_Value.length( );
         stringValue = stringISIR_Value;
         if ( stringValue.compareTo( "N/A" ) == 0 )
            stringValue = null;         // A value of N/A is converted to null.
         else
            charLastCharacter = stringISIR_Value.charAt( nLth - 1 );

         if ( stringValue == null )
         {
            // The value is null, so set result to null.
            SetAttributeFromString( tgtView, tgtEntityName, tgtAttributeName, "" );
         }
         else
         {
            if ( charLastCharacter >= '0' && charLastCharacter <= '9' )
               charSign = '\0';
            else
            {
               charSign = charLastCharacter;

               // Convert last character to digit.
               switch ( charLastCharacter )
               {
                  case '}':
                  case '{':
                     charLastDigit = '0';
                     break;

                  case 'A':
                  case 'J':
                     charLastDigit = '1';
                     break;

                  case 'B':
                  case 'K':
                     charLastDigit = '2';
                     break;

                  case 'C':
                  case 'L':
                     charLastDigit = '3';
                     break;

                  case 'D':
                  case 'M':
                     charLastDigit = '4';
                     break;

                  case 'E':
                  case 'N':
                     charLastDigit = '5';
                     break;

                  case 'F':
                  case 'O':
                     charLastDigit = '6';
                     break;

                  case 'G':
                  case 'P':
                     charLastDigit = '7';
                     break;

                  case 'H':
                  case 'Q':
                     charLastDigit = '8';
                     break;

                  case 'I':
                  case 'R':
                     charLastDigit = '9';
                     break;

                  default:
                     stringMsg = "Unexpected Sign for the following data and attribute:   Entity Name: " + tgtEntityName + zNEW_LINE +
                                 "   Attribute Name: " + tgtAttributeName + zNEW_LINE +"   Data Value: " + stringISIR_Value;
                     IssueError( tgtView, 0, 0, stringMsg );
               }

               stringValue = stringValue.substring( 0, nLth - 2 ) + charLastDigit;
            }

            lValue = StrToInt( stringValue );

            if ( charSign == '}' || ( charSign >= 'J' && charSign <= 'Z' ))
               lValue = -lValue;

            nRC = SetAttributeFromInteger( tgtView, tgtEntityName, tgtAttributeName, lValue );
         }
      }

      return 0;
   }

   // ConvertISIR_NumberToAttribute

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SetEntityCursorByInteger
   //    Does a SetEntityCursor for an Integer, since the regular SetEntityCursor doesn't work for
   //    an integeri in VML.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SetEntityCursorByInteger( View   view,
                             String entityName,
                             String attributeName,
                             int    cursorPosition,
                             int    lIntegerSearchValue,
                             String stringScopingEntityName )
   {
      int nRC;

      nRC = SetEntityCursor( view, entityName, attributeName, cursorPosition, lIntegerSearchValue,
                             "", "", 0, stringScopingEntityName, "" );
      return nRC;
   } // SetEntityCursorByInteger

   public int
   PositionOnEntityByZID( View   vLDD,
                          String pchZID )
   {
      String pchDot;
      int    nDot1;
      int    nDot2;
      int    nRC;

      if ( pchZID == null || pchZID.isEmpty( ) )
         return 0;

      nDot1 = zstrchr( pchZID, '.' );
      if ( nDot1 >= 0 )
         pchDot = pchZID.substring( 0, nDot1 - 1 );
      else
         pchDot = pchZID;

      nRC = SetCursorFirstEntityByString( vLDD, "MasterLabelContent", "ID", pchDot, "" );
      if ( nRC == zCURSOR_SET )
      {
         if ( nDot1 < 0 )
            return 1;  // found MasterLabelContent

         nDot1++;
         nDot2 = zstrchr( pchZID, nDot1, '.' );
         if ( nDot2 >= 0 )
            pchDot = pchZID.substring( nDot1, nDot2 - 1 );
         else
            pchDot = pchZID.substring( nDot1 );

         nRC = SetCursorFirstEntityByString( vLDD, "MasterLabelSection", "ID", pchDot, "" );
         if ( nRC == zCURSOR_SET )
         {
            if ( nDot2 < 0 )
               return 2;  // found MasterLabelSection

            pchDot = pchZID.substring( nDot2 + 1 );
            nRC = SetCursorFirstEntityByString( vLDD, "MasterLabelParagraph", "ID", pchDot, "" );
            if ( nRC == zCURSOR_SET )
               return 3;  // found MasterLabelParagraph
         }
      }

      return -1;  // did not find proper entity
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
   //      has a greater corresponding sub-version, simply increment the final
   //      sub-version of the first version as the new version.  Otherwise, keep
   //      adding the second's sub-version on to the end if the first and, if
   //      necessary, add a new sub-version.
   //
   /////////////////////////////////////////////////////////////////////////////
   public String
   DetermineNextVersion( String  pchVersionNew,
                         View    vListVersionIn,
                         String  cpcListVersionEntity,
                         String  cpcListVersionAttribute )
   {
      // "SubregLabelContent", "Version", mSubProd, "SubregLabelContent", "Version" )
      zVIEW  vListVersion = null;
      String pch;
      String pchDot1;
      String pchDot2;
      String pchDotNext1;
      String pchDotNext2;
      String szVersion = null;
      String szVersionNext = null;
      int    lVersion = 0;
      int    lVersionNext = 0;
      int    nDot1 = 0;
      int    nDot2 = 0;
      int    nDotNext2 = 0;
      int    nDotCnt = 0;
      int    nDotCntNext = 0;
      int    nRC;

      CreateViewFromView( vListVersion, vListVersionIn );
      szVersion = GetStringFromAttribute( szVersion, vListVersion, cpcListVersionEntity, cpcListVersionAttribute );
      nRC = SetCursorNextEntity( vListVersion, cpcListVersionEntity, "" );
      if ( nRC == zCURSOR_SET )
         szVersionNext = GetStringFromAttribute( szVersionNext, vListVersion, cpcListVersionEntity, cpcListVersionAttribute );
      else
         szVersionNext = "";

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
         nDot2 = zstrchr( pchDot1, '.' );
         nDotNext2 = zstrchr( pchDotNext1, '.' );
         if ( nDot2 >= 0 ) // still more versions for first
         {
            nDotCnt++;
            pch = pchDot1.substring( 0, nDot2 - 1 );
            lVersion = zatol( pch );
            pchDot1 = pchDot1.substring( 0, nDot2 + 1 );
         }
         else
         {
            lVersion = zatol( pchDot1 );
            pchDot1 = null;
         }

         if ( nDotNext2 >= 0 ) // still more versions for second
         {
            nDotCntNext++;
            pch = pchDotNext1.substring( 0, nDotNext2 - 1 );
            lVersionNext = zatol( pch );
            pchDotNext1 = pchDotNext1.substring( 0, nDotNext2 + 1 );
         }
         else
         {
            lVersionNext = zatol( pchDotNext1 );
            pchDotNext1 = null;
         }

         if ( (lVersionNext > lVersion + 1) ||
              (lVersionNext > lVersion && pchDot1 != null && pchDotNext1 == null) ||
              (lVersionNext == 0) )
         {
            // Simply increment final sub-version of the first version.
            pchVersionNew = zstrcpy( pchVersionNew, szVersion );
            nDot1 = zstrrchr( pchVersionNew, '.' );
            if ( nDot1 >= 0 )
               pchDot1 = pchVersionNew.substring( nDot1 + 1 );
            else
               pchDot1 = pchVersionNew;

            lVersion = zatol( pchDot1 );
            lVersion++;
            pchDot1 = zltoa( lVersion, pchDot1 );  // now pchVersionNew contains the next version
            break;  // we are done ... get out of loop
         }

         if ( pchDot1 == null || pchDotNext1 == null )
         {
            pchVersionNew = zstrcpy( pchVersionNew, szVersion );
            if ( pchDotNext1 != null )  // something in second version
            {
               lVersionNext = zatol( pchDotNext1 );
               pchVersionNew += '.';
               pchVersionNew += zltoa( lVersionNext - 1, pchVersionNew );  // now pchVersionNew contains the next version
            }
            else
            {
               // On the last version for both ... just extend the next version
               pchVersionNew = zstrcat( pchVersionNew, ".5" );
            }

            break;  // we are done ... get out of loop
         }

      }  while ( pchDot1 != null );

      DropView( vListVersion );
      return pchVersionNew;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: CheckForTableAttribute
   //    Check if an attribute has a table domain
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   CheckForTableAttribute( View  lpView,
                           String entityName,
                           String attributeName,
                           String stringObjectName )
   {
      zVIEW   vXOD = null;
      zVIEW   vXDM = null;
      StringBuilder sbFileName = new StringBuilder( 0 );
      String  stringDomainName = null;
      String  stringDomainType = null;
      int     cursorPosition;
      int  nRC;

      // Check if the attribute passed has a table domain.
      // A 0 indicates it does.
      // A 1 indicates it does not.

      // If necessary, activate the XOD for the requested object.
      nRC = GetViewByName( vXOD, "FindTableXOD", lpView, zLEVEL_TASK );
      if ( nRC < 0 )
      {
         GetApplDirectoryFromView( sbFileName, lpView, zAPPL_DIR_OBJECT, 300 );
         zstrcat( sbFileName, stringObjectName );
         zstrcat( sbFileName, ".XOD" );
         // 536870912 is ACTIVATE_SYSTEM in the following activate statement.
         ActivateOI_FromFile( vXOD, "TZZOXODO", lpView, sbFileName.toString( ), zACTIVATE_ROOTONLY_MULTIPLE );  // zACTIVATE_SYSTEM 536870912 );
         SetNameForView( vXOD, "FindTableXOD", lpView, zLEVEL_TASK );
      }

      // Position on correct entity and attribute (using recursive set cursor on entity) and
      // retrieve Domain Name.
      ResetView( vXOD );
      cursorPosition = zPOS_FIRST;  // TODO to get by an error zQUAL_STRING + zPOS_FIRST + zRECURS;
      SetEntityCursor( vXOD, "ENTITY", "NAME", cursorPosition, entityName, "", "", 0, "OBJECT", "" );

      SetCursorFirstEntityByString( vXOD, "ATTRIB", "NAME", attributeName, "" );
      GetStringFromAttribute( stringDomainName, vXOD, "ATTRIB", "DOMAIN" );
      //TraceLineS( "Domain Name: ", stringDomainName );

      // If necessary, activate the XDM containing all Domain data.
      nRC = GetViewByName( vXDM, "FindTableXDM", lpView, zLEVEL_TASK );
      if ( nRC < 0 )
      {
         GetApplDirectoryFromView( sbFileName, lpView, zAPPL_DIR_OBJECT, 300 );
         zstrcat( sbFileName, "zeidon.xdm" );
         ActivateOI_FromFile( vXDM, "TZDMXGPO", lpView, sbFileName.toString( ), zACTIVATE_ROOTONLY_MULTIPLE );  // zACTIVATE_SYSTEM 536870912 );
         SetNameForView( vXDM, "FindTableXDM", lpView, zLEVEL_TASK );
      }

      // Position on the correct Domain and retrieve the Domain Type, which is "T" for Table Domains.
      SetCursorFirstEntityByString( vXDM, "Domain", "Name", stringDomainName, "" );
      stringDomainType = GetStringFromAttribute( stringDomainType, vXDM, "Domain", "DomainType" );

      // Set return code base on whether or not the Domain is a Table.
      if ( stringDomainType.charAt( 0 ) == 'T' )
         return 0;
      else
         return 1;

   } // CheckForTableAttribute

   public int
   AD_TestAdmin( )
   {
      String ldapurl = "ldap://10.150.0.10";
      String stringADUserName = "";
      String szAD_Password = "";
      // This is using fastbind.
      return m_ActiveDirectory.ActiveDirectoryLoginAuthentication( ldapurl, "enc-ad\\zmail", "F82b7mk,9j" );
   }

   public int
   AD_TestAdminNotFast( )
   {
      String ldapurl = "ldap://10.150.0.10";
      String stringADUserName = "";
      String szAD_Password = "";
      // Try binding w/o fastbind.
      return m_ActiveDirectory.ActiveDirectoryLoginAuthenticationNF( ldapurl, "enc-ad\\zmail", "F82b7mk,9j" );
      //IsAuthenticated = ctx.Authenticate("enc-ad\\zmailxx","F82b7mk,9jssss");
   }

   public int
   AD_TestUserNotFast( )
   {
      String ldapurl = "ldap://10.150.0.10";
      String stringADUserName = "";
      String szAD_Password = "";
      // Try binding w/o fastbind.
      return m_ActiveDirectory.ActiveDirectoryLoginAuthenticationNF( ldapurl, stringADUserName, szAD_Password );
   }

   public int
   AD_TestUser( )
   {
      String ldapurl = "ldap://10.150.0.10";
      String stringADUserName = "";
      String szAD_Password = "";
      // This is using fastbind.
      return m_ActiveDirectory.ActiveDirectoryLoginAuthentication( ldapurl, stringADUserName, szAD_Password );
   }

   public int
   AD_TestChangePassword( )
   {
      String ldapurl = "ldap://10.150.0.10";
      String stringADAdminUserName = "enc-ad\\zmail";
      String stringADAdminPassword = "F82b7mk,9j";
      String stringADUserName = "";
      String stringADOldPassword = "";
      String stringADNewPassword = "";
      int nRC;

      nRC = m_ActiveDirectory.ActiveDirectoryChangePassword( ldapurl, stringADAdminUserName,
                                                             stringADAdminPassword, stringADUserName, stringADOldPassword, stringADNewPassword );
      return 0;
   }

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_AuthenticateUserPassword
   //    Authenticate User Name and Password against Active Directory.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_AuthenticateUserPassword( String szAD_Pathname,
                                String szAD_UserName,
                                String szAD_Password )
   {
      // In the "c" world this is "LDAP://DC=ENC-AD,DC=ENC,DC=EDU"
      // But I couldn't get a connection using that in java, so I am using the
      // ldap server id.
      szAD_Pathname = "ldap://10.150.0.10";
      return m_ActiveDirectory.ActiveDirectoryLoginAuthentication( szAD_Pathname, szAD_UserName, szAD_Password );

   } // AD_AuthenticateUserPassword

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_AddUserPassword
   //    Add Active Directory User and Password
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_AddUserPassword( String stringServerName,
                       String stringServerPort,
                       String stringOrganization,
                       String stringUserName,
                       String stringUserPassword )
   {
      return m_ActiveDirectory.ActiveDirectoryAddUser( stringServerName, stringServerPort, stringOrganization, stringUserName, stringUserPassword );

   } // AD_AddUserPassword

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_RemoveUserPassword
   //    Remove Active Directory User with Password
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_RemoveUserPassword( String stringServerName,
                          String stringServerPort,
                          String stringOrganization,
                          String stringUserName )
   {
      return m_ActiveDirectory.ActiveDirectoryRemoveUser( stringServerName, stringServerPort, stringOrganization, stringUserName );

   } // AD_RemoveUserPassword

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_ChangeUserPassword
   //    Change the user password through Active Directory
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_ChangePassword( String stringAD_Pathname,
                          String stringAD_LoginUserName,
                          String stringAD_LoginPassword,
                          String stringAD_UserName,
                          String stringAD_OldPassword,
                          String stringAD_NewPassword )
   {
      // In the "c" world this is "LDAP://DC=ENC-AD,DC=ENC,DC=EDU"
      // But I couldn't get a connection using that in java, so I am using the
      // ldap server id.
      stringAD_Pathname = "ldap://10.150.0.10";
      // Also, in "c" we store password on the database but I need to do some
      // work with that because we are using different encyrption right now for java and
      // I can't decrypt a password.
      stringAD_LoginUserName = "enc-ad\\zmail";
      stringAD_LoginPassword = "F82b7mk,9j";
      return m_ActiveDirectory.ActiveDirectoryChangePassword( stringAD_Pathname, stringAD_LoginUserName, stringAD_LoginPassword, stringAD_UserName, stringAD_OldPassword, stringAD_NewPassword );
   } // AD_ChangeUserPassword

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_SetPassword
   //    Set password in  Active Directory
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_SetPassword( String stringAD_Pathname,
                   String stringAD_LoginUserName,
                   String stringAD_LoginPassword,
                   String stringAD_UserName,
                   String stringAD_Password )
   {
      return m_ActiveDirectory.ActiveDirectorySetPassword( stringAD_Pathname, stringAD_LoginUserName, stringAD_LoginPassword, stringAD_UserName, stringAD_Password );
   }

   // AD_SetPassword

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_AddNewUser
   //    Add a new user to Active Directory
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_AddNewUser( String stringAD_Pathname,
                  String stringAD_LoginUserName,
                  String stringAD_LoginPassword,
                  String stringAD_NewUserName,
                  String stringAD_NewUserPassword )
   {
      return m_ActiveDirectory.ActiveDirectoryAddUser( stringAD_Pathname, stringAD_LoginUserName, stringAD_LoginPassword, stringAD_NewUserName, stringAD_NewUserPassword );

   }  // AD_AddNewUser

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_GetUserProperty
   //    Get the value of an Active Directory property for the user.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_GetUserProperty( String stringAD_Pathname,
                       String stringAD_UserName,
                       String stringAD_Password,
                       String stringAD_Property,
                       StringBuilder stringReturnProperty )
   {
      return m_ActiveDirectory.ActiveDirectoryGetProperty( stringAD_Pathname, stringAD_UserName, stringAD_Password, stringAD_Property, stringReturnProperty );

   } // AD_GetUserProperty

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: ValidateAndSetState
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   ValidateAndSetState( View   view,
                        String entityName,
                        String attributeName,
                        String stringStateString )
   {
      StringBuilder  sbTableValue = new StringBuilder( );
      MutableInt Pointer = null;
      int  lFoundFlag = 0;
      int  nRC;

      nRC = GetFirstTableEntryForAttribute( sbTableValue, view, entityName, attributeName, "", Pointer );
      while ( lFoundFlag == 0 && nRC >= 0 )
      {
         TraceLineS( "//* Domain Value: ", sbTableValue.toString( ) );
         if ( zstrcmp( stringStateString, sbTableValue.toString( ) ) == 0 )
            lFoundFlag = 1;

         nRC = GetNextTableEntryForAttribute( sbTableValue, view, entityName, attributeName, "", Pointer );
      }

      if ( lFoundFlag == 0 )
         return -1;  // String was NOT found.
      else
      {
         // String WAS found.
         SetAttributeFromString( view, entityName, attributeName, stringStateString );
         return 0;
      }

   } // ValidateAndSetState

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SetStringUpperLowerCase
   //    Set characters in a string to a combination of upper and lower case, with the 1st character
   //    of each "word" in the string to be in upper case and the remainder in lower case.  Eliminate
   //    leading and trailing whitespace and multiple consecutive whitespace characters.  Also check
   //    for Roman Numeral suffix and upper case as necessary.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SetStringUpperLowerCase( StringBuilder sbName )
   {
      String   string;
      boolean  bSuffix = false;
      int nBlankFound = 0;
      @SuppressWarnings("unused") int nLth;
      int k;
      int j;

      // Eliminate leading and trailing blanks.
      string = sbName.toString( );
      string = string.trim( );

      // Force first character to be upper case
      StringBuffer sb = new StringBuffer( string.toLowerCase( ) );  // force string to lower case
      sb.setCharAt( 0, Character.toUpperCase( sb.charAt( 0 ) ) );

      for ( k = 1; k < sb.length( ); k++ )
      {
         // Eliminate multiple consecutive blanks.
         if ( sb.charAt( k ) == ' ' )
         {
            j = k;
            while ( sb.charAt( j + 1 ) == ' ' )
               j++;

            if ( j > k )
             sb.delete( k + 1, j );
         }
         else
      // if ( sb.charAt( k ) != ' ' )
         {
            if ( sb.charAt( k - 1 ) == ' ' )
            {
               nBlankFound++;
               char ch = Character.toUpperCase( sb.charAt( k ) );
               sb.setCharAt( k, ch );

               if ( ch == 'I' || ch == 'V' )
                  bSuffix = true;
               else
                  bSuffix = false;
            }
            else
            {
               if ( nBlankFound > 1 && bSuffix )  // checking for II or III or IV or V or VI or VII or VIII
               {
                  if ( (sb.charAt( k ) == 'i' || sb.charAt( k ) == 'v') &&
                       (sb.charAt( k + 1 ) == '\0' || sb.charAt( k + 1 ) == 'i' || sb.charAt( k + 1 ) == 'v') )
                  {
                     sb.setCharAt( k, Character.toUpperCase( sb.charAt( k ) ) );
                  }
                  else
                     bSuffix = false;
               }
               else
               {
                  bSuffix = false;  // can't start with suffix
               }
            }
         }
      }

      zstrcpy( sbName, sb.toString( ) );
      return sbName.length( );

   } // SetStringUpperLowerCase


   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SetAttributeFromUC_String
   //    Set Attribute from String, converting string to upper case.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SetAttributeFromUC_String( View   tgtView,
                              String tgtEntityName,
                              String tgtAttributeName,
                              String srcString )
   {

      // Convert srcString to upper case and store it in the attribute.
      SetAttributeFromString( tgtView, tgtEntityName, tgtAttributeName, srcString.toUpperCase( ) );

      return 0;
   } // SetAttributeFromUC_String

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: SetAttributeFromUC_Attribute
   //    Set Attribute from Attribute, converting the source string to upper case in the process.
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SetAttributeFromUC_Attribute( View   tgtView,
                                 String tgtEntityName,
                                 String tgtAttributeName,
                                 View   srcView,
                                 String srcEntityName,
                                 String srcAttributeName )
   {
      String  srcString = null;

      // Convert source attribute value to upper case and store it in the target attribute.
      // zToUpper prototype is in TZVMLIP.H
      srcString = GetStringFromAttribute( srcString, srcView, srcEntityName, srcAttributeName );
      SetAttributeFromString( tgtView, tgtEntityName, tgtAttributeName, srcString.toUpperCase( ) );

      return 0;
   } // SetAttributeFromUC_Attribute

   ////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  Method Name: AD_SetUserProperty
   //    Set an AD property for a user
   //
   ////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AD_SetUserProperty( String stringAD_Pathname,
                       String stringAD_AdminName,
                       String stringAD_AdminPassword,
                       String stringAD_UserName,
                       String stringAD_PropertyName,
                       String stringAD_PropertyValue )
   {
      int nRC;

      nRC = m_ActiveDirectory.ActiveDirectorySetProperty( stringAD_Pathname, stringAD_AdminName, stringAD_AdminPassword,
                                                          stringAD_UserName, stringAD_PropertyName, stringAD_PropertyValue );
      return nRC;

   } // AD_SetUserProperty

   /////////////////////////////////////////////////////////////////////////////
   //
   //OPERATION: InsertUsageWordsIntoString
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   InsertUsageWordsIntoStringX( View   view,
                                StringBuilder sbString, // original data and return data
                                int    lMaxLth,
                                String szUsageType,
                                String szUsageKeyword,
                                String szUsageEntityName,
                                String szUsageEntityNameScope,
                                String szSeparatorCharacters )
   {
   /*****
      zVIEW  viewT;
      String  szCurrentType[ 2 ];
      String  szCurrentName[ 51 ];
      String pchFromString;
      String pchToString;
      String pchToStringHold;
      int    lMemHandle;
      int    nUsageKeywordLth;
      int    nSeparatorCharsLth;
      int    nNameLth;
      int    nCount;
      int    nRC;

      // Insert Usage text into a position in szStringArea that is identified by a Usage Keyword.
      // The entries inserted will be separated by one or more characters as identified by the variable szSeparatorCharacters.
      // After determining the position of the insertion, we will loop through Usage entries, formatting each entry as we go.
      // Which entries we insert depend on the UsageType as follows:
      // A - Insert all Usage entries.
      // C - Insert only Claim Usage entries.
      // S - Insert only Surface Usage entries.
      // T - Insert only Application Type Usage entries.
      // U - Insert only Location Usage entries.

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
            nRC = SetCursorFirstEntity( view, szUsageEntityName, szUsageEntityNameScope );
            while ( nRC >= zCURSOR_SET )
            {
               GetVariableFromAttribute( szCurrentType, 0, zTYPE_STRING, 2, view, szUsageEntityName, "UsageType", 0, 0 );
               GetVariableFromAttribute( szCurrentName, 0, zTYPE_STRING, 51, view, szUsageEntityName, "Name", 0, 0 );

               // Insert this entry, if the Usage entry is the same Type or "All" is requested.
               if ( szUsageType[ 0 ] == 'A' || szUsageType[ 0 ] == szCurrentType[ 0 ] )
               {
                  nNameLth = zstrlen( szCurrentName );
                  // For any entry but the first or last, copy the separator characters.
                  // For the first entry, don't add any characters at all.
                  // For the last entry, add the characters "and".
                  nCount++;
                  CreateViewFromView( &viewT, view );
                  if ( szUsageType[ 0 ] == 'A' )
                     nRC = SetCursorNextEntity( viewT, szUsageEntityName, szUsageEntityNameScope );
                  else
                     nRC = SetCursorNextEntityByString( viewT, szUsageEntityName, "UsageType", szUsageType, szUsageEntityNameScope );

                  DropView( viewT );
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

               nRC = SetCursorNextEntity( view, szUsageEntityName, szUsageEntityNameScope );
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
   ****/

      return 0;

   } // InsertUsageWordsIntoString

   /////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: SeparateNumberedStatement
   //
   // Separate a numbered statement into the number and the rest of the
   // statement. A return code of -1 means the text didn't start with a number.
   /////////////////////////////////////////////////////////////////////////////
   public int
   SeparateNumberedStatement( String pchOriginalStatement,
                              int    lMaxLth,
                              StringBuilder sbNumberedText )
   {
      String pchRemainingText;
      int    lMemHandle;
      int    nCount;
   /***
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
   ***/

      return( 0 );

   } // SeparateNumberedStatement

   public int WinShellExecute( View viewToWindow, String szTempString_0,
                               String string, String string2 ) {
      // TODO Auto-generated method stub
      return 0;
   }

   public String GetImagingPath( View viewToWindow, int i, String szPathName )
   {
      // TODO Auto-generated method stub
      return null;
   }

   public int FTPSendFile( View viewToSubtask, String szURL, String szLoginName,
                           String szPassword, String szFullFileName, String szTargetFileName,
                           int i) {
      // TODO Auto-generated method stub
      return 0;
   }


   /*************************************************************************************************
   **
   **    OPERATION: CopyFileToPDF
   **    Copy a file to a pdf file.
   **
   *************************************************************************************************/
   public int CopyFileToPDF( View vMapObject,
                             String szFileToCopy,
                             String szPDFName )
   {
      zVIEW   vKZXMLPGO = new zVIEW( );
      String  szCommandLine = null;
      String  szPathFileName = null;
      String  szFileName = null;

      szCommandLine = "copypdf.bat \"" + szFileToCopy + "\"";

      // KJS 01/05/11 - When we move to java I don't think we will be able to do this anymore.
      // Need to keep JODConverter in mind:http://stackoverflow.com/questions/586411/is-there-a-free-way-to-convert-rtf-to-pdf

      try
      {
        Process proc = Runtime.getRuntime().exec( szCommandLine );
        int exitCode = proc.waitFor();
      }
      catch (IOException e)
      {
        return -1;
      }
      catch (InterruptedException e)
      {
        return -1;
      }
/*
 * Also , you can invoke apps like notepad ;
Runtime rt = Runtime.getRuntime();
try {
rt.exec("notepad");
} catch (IOException ioe) {
ioe.printStackTrace();
}
*/

      // KJS 02/20/2009 - We would like our pdfs to be created in a separate directory.
      // Use PDF_PathFileName to get this.  Currently I have this set as /zencas/pdf/ but
      // I know Aadit would like to put these in a totally different directory not under
      // zencas.  Might need to change something because I had a hard time getting a file
      // to open when PDF_PathFileName was something like "C:\Program Files...".
      StringBuilder sb_szPathName;
      sb_szPathName = new StringBuilder( 200 );
      //SysReadZeidonIni( -1, "[App.Zencas]", "WebDirectory", sb_szDirectoryName );
      m_KZOEP1AA.SysReadZeidonIni( -1, "Workstation", "PDF_PathFileName", sb_szPathName );
      szPathFileName = sb_szPathName.toString( );

      /*
      nZRetCode = GetWorkstationApplicationValues( vMapObject, "PDF_PathFileName",
         szPathFileName, 32, &lFontSize, &lWork, &lWork, &lWork, &lWork, &lWork,
         &lWork, &lWork, &lWork, &lWork );
      */

      //szFileName = szPathFileName + szPDFName;
      szFileName = szPDFName + ".pdf";

      // We set the report name in KZXMLPGO so that
      // we can retrieve this name in FindOpenFile (kzoejava.c) when trying to
      // open the file in the jsp files.
      GetViewByName( vKZXMLPGO, "_KZXMLPGO", vMapObject, zLEVEL_TASK );
      SetAttributeFromString( vKZXMLPGO, "Session", "PrintFileName", szFileName );  //pchReportName );
      SetAttributeFromString( vKZXMLPGO, "Session", "PrintFileType", "pdf" );
      return( 0 );
   } // CopyFileToPDF

   /////////////////////////////////////////////////////////////////////////////
   //
   //  SortEntityWithinParent
   //
   //  PURPOSE:    This routine will sort an Entity (keeping its dependents)
   //              by any given 4 attributes (if the attr's 2 - 4 are null
   //              strings, the only the first attr is used).
   //
   //              It is not efficient?, but it tends to work.
   //
   //  PARAMETERS: bDescending  -- Indicator for ascending or descending
   //                          (zASCENDING or zDESCENDING)
   //              vIn            -- view that contains the entity to be
   //                               sorted AND its' parent
   //              pchEntityName  -- Entity to be sorted
   //              pchAttribName  -- Name of the attribute for primary sort
   //              bDescending    -- Indicator for ascending or descending
   //              pchAttribName2 -- Name of a second sort attribute or null string
   //              bDescending2   -- Indicator for ascending or descending
   //              pchAttribName3 -- Name of a second sort attribute or null string
   //              bDescending3   -- Indicator for ascending or descending
   //              pchAttribName4 -- Name of a second sort attribute or null string
   //              bDescending4   -- Indicator for ascending or descending
   //
   //  RETURNS: number of entity swaps required to sort
   //             -1 - more than 32000 swaps required
   //
   /////////////////////////////////////////////////////////////////////////////
/*
   private int
   SortEntityWithinParent( View   vIn,
                           String strEntityName,
                           String strAttribName1,
                           boolean bDescending,
                           String strAttribName2,
                           boolean bDescending2,
                           String strAttribName3,
                           boolean bDescending3,
                           String strAttribName4,
                           boolean bDescending4 )
   {
      View   vSort1;
      View   vSort2;
      int    nSwap;
      int    nSwapPrev;
      int    nMoves;
      int    lCompares;
      int    nEntities;
      int    nRC;
      int    nRC2;
      int    nRC3;
      int    nRC4;

      nEntities = 0;
      lCompares = 0;
      nMoves = 0;
      nSwap = 0;
      nSwapPrev = 2;
      vSort1 = vIn.newView();
      vSort1.copyCursors( vIn );
      vSort2 = vIn.newView();

      CursorResult cr = vSort1.cursor( strEntityName ).setFirst();
      if ( cr.isSet() )
      {
         vSort2.copyCursors( vSort1 );
         for ( ; ; )
         {
            nEntities++;
            cr = vSort2.cursor( strEntityName ).setNext();
            if ( cr.isSet() )
            {
               if ( nSwap == 0 )
               {
                  break;
               }
               else
               {
                  vSort1.cursor( strEntityName ).setFirst();
                  vSort2.cursor( strEntityName ).setFirst();
                  vSort2.cursor( strEntityName ).setNext();
                  nEntities = 1;
                  nSwapPrev = nSwap;
                  nSwap = 0;
               }
               lCompares++;

               // Compare Attr 1
               nRC = vSort1.cursor( strEntityName ).getAttribute( strAttribName1 ).compare( vSort2.cursor( strEntityName ).getAttribute( strAttribName1 ) );
               if ( bDescending )
                  nRC = nRC * -1;

               // Compare Attr 2
               if ( strAttribName2 != null && strAttribName2.equals( "" ) == false )
               {
                  nRC2 = 0;
               }
               else
               {
                  nRC2 = vSort1.cursor( strEntityName ).getAttribute( strAttribName2 ).compare( vSort2.cursor( strEntityName ).getAttribute( strAttribName2 ) );
                  if ( bDescending2 )
                     nRC2 = nRC2 * -1;
               }

               // Compare Attr 3
               if ( strAttribName3!= null && strAttribName3.equals( "" ) == false )
               {
                  nRC3 = 0;
               }
               else
               {
                  nRC3 = vSort1.cursor( strEntityName ).getAttribute( strAttribName3 ).compare( vSort2.cursor( strEntityName ).getAttribute( strAttribName3 ) );
                  if ( bDescending3 )
                     nRC3 = nRC3 * -1;
               }
               // Compare Attr 4
               if ( strAttribName4!= null && strAttribName4.equals( "" ) == false )
               {
                  nRC4 = 0;
               }
               else
               {
                  nRC4 = vSort1.cursor( strEntityName ).getAttribute( strAttribName4 ).compare( vSort2.cursor( strEntityName ).getAttribute( strAttribName4 ) );
                  if ( bDescending4 )
                     nRC4 = nRC4 * -1;
               }

               // See if swap required
               if ( nRC > 0 || (nRC == 0 && nRC2 > 0) || (nRC == 0 && nRC2 == 0 && nRC3 > 0) || (nRC == 0 && nRC2 == 0 && nRC3 == 0 && nRC4 > 0) )
               {
               // nRC = MoveSubobject( vSort2, strEntityName, vSort1, strEntityName, zPOS_AFTER, zREPOS_NONE );
                  cr = vSort2.cursor( strEntityName ).moveSubobject( CursorPosition.NEXT, vSort1.cursor( strEntityName ), CursorPosition.NONE );
                  if ( cr.isSet() == false )
                     return( -16 );

                  nMoves++;
                  if ( nMoves > 32000 )
                     return( -1 );

                  nSwap++;
                  if ( nSwapPrev > 1 )
                  {
                     // the next two lines pump v1 up - v2 is done at front of loop
                     vSort1.cursor( strEntityName ).setNext();
                     vSort2.cursor( strEntityName ).setNext();
                  }
                  else
                  {
                     vSort1.cursor( strEntityName ).setFirst();
                     vSort2.cursor( strEntityName ).setFirst();
                     nEntities = 0;
                  }
               }
               else
               {
                  vSort1.cursor( strEntityName ).setNext();
               }
            }
         }
      }

      vSort1.drop();
      vSort2.drop();

      logger.debug( "Stats SortEntityWithinParent: " + strEntityName + " -- " + nEntities + " Entities,  " + nMoves + " Moves,  " + lCompares + " Compares" );
      return( nMoves );
   }

*/

   public int
   BuildSimpleStringQualification( View   vSubtask,
                                   zVIEW  vQualificationObject,
                                   String strEntityName,
                                   String strKeyAttributeName,
                                   String strKeyAttributeValue,
                                   String strComparator )
   {
      View view = vSubtask.activateEmptyObjectInstance( "KZDBHQUA", task.getSystemTask().getApplication() );
      view.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      view.cursor( "EntitySpec" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      view.cursor( "QualAttrib" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).getAttribute( "AttributeName" ).setValue( strKeyAttributeName );
      view.cursor( "QualAttrib" ).getAttribute( "Value" ).setValue( strKeyAttributeValue );
      view.cursor( "QualAttrib" ).getAttribute( "Oper" ).setValue( strComparator );
      vQualificationObject.setView( view );
      return( 0 );
   }

   public int
   BuildSimpleIntegerQualification( View   vSubtask,
                                    zVIEW  vQualificationObject,
                                    String strEntityName,
                                    String strKeyAttributeName,
                                    int    lKeyAttributeValue,
                                    String strComparator )
   {
      View view = vSubtask.activateEmptyObjectInstance( "KZDBHQUA", task.getSystemTask().getApplication() );
      view.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
      view.cursor( "EntitySpec" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
      view.cursor( "QualAttrib" ).getAttribute( "EntityName" ).setValue( strEntityName );
      view.cursor( "QualAttrib" ).getAttribute( "AttributeName" ).setValue( strKeyAttributeName );
      view.cursor( "QualAttrib" ).getAttribute( "Value" ).setValue( lKeyAttributeValue );
      view.cursor( "QualAttrib" ).getAttribute( "Oper" ).setValue( strComparator );
      vQualificationObject.setView( view );
      return( 0 );
   }

   // Equine Influenza A Virus {(H3N8)}
   // Escherichia coli O26:H11 {(ATCC BAA-1653)}
   public int
   DecipherUsageKeyword( StringBuilder sbName, String openKey, String closeKey,
                         StringBuilder sbKeyword, int keywordMaxLth, StringBuilder sbKeyValue, int keyValueMaxLth )
   {
      sbKeyword.setLength( 0 );
      sbKeyValue.setLength( 0 );
      int nLth = openKey.length();
      int nPos1 = sbName.indexOf( openKey );
      if ( nPos1 >= 0 )
      {
         int nPos2 = sbName.indexOf( closeKey, nPos1 + nLth );
         if ( nPos2 >= 0 )
         {
            String key = sbName.substring( nPos1 + nLth, nPos2 );
            sbName.delete( nPos1, nPos2 + closeKey.length() );
            sbKeyValue.append( "(" + key + ")" );
            int nPos = key.indexOf( ' ', nLth );
            if ( nPos > 0 && nPos < key.length() - 1 )  // ignore if space is leading or trailing
            {
               key = key.substring( 0, nPos ) + "#";
            }
            sbKeyword.append( key );
            sbName.insert( nPos1, "{{" + key + "}}" );
            return 0;
         }
      }
      return -1;
   }
}