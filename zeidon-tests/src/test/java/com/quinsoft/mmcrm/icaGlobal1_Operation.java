package com.quinsoft.mmcrm;

import java.nio.CharBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.CharBuffer;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.text.ParseException;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

/*
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
*/
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.FileUtils;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.net.URI;
import java.util.List;
import java.net.*;
import java.util.UUID;

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.vml.VmlOperation;
import com.quinsoft.zeidon.vml.zVIEW;
import com.quinsoft.zeidon.vml.VmlOperation.DateTimeRecord;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;
import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DomainType;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.validator.routines.EmailValidator;

// Libraries for LDAP authentication
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.ldap.*;
import javax.naming.*;
import javax.naming.directory.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

// SmartyStreets address verification API
import java.util.ArrayList;

/**
* @author QuinSoft
*
*/

public class icaGlobal1_Operation extends VmlOperation
{
private final ZDRVROPR m_ZDRVROPR;
private final KZOEP1AA m_KZOEP1AA;

public icaGlobal1_Operation( View view )
{
   super( view );
   m_ZDRVROPR = new ZDRVROPR( view );
   m_KZOEP1AA = new KZOEP1AA( view );
}

protected static final int Minute =       1;
protected static final int Hour =         (Minute * 60);
protected static final int Day =          (Hour * 24);
protected static final int Week =         (Day * 7);
protected static final int Year =         (Day * 365);
protected static final int LeapYear =     (Day * 366);
protected static final int Century =      ((Year * 76) + (LeapYear * 24));
protected static final int LeapCentury =  ((Year * 75) + (LeapYear * 25));
protected static final int Year1900 =     ((Century * 14) + (LeapCentury * 5));
protected static final int Year2000 =     ((Century * 15) + (LeapCentury * 5));
protected static final int lNullInteger = -2147483647 - 1;
protected static final                     // J  F  M  A  M  J  J  A  S  O  N  D
                       char cMonth_Val[ ] = { 6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

protected static final int usDayTable[ ] = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
protected static final int usLeapDayTable[ ] = { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };

// Internal DateTime structure
public static class DateTimeRecord
{
   private int  ulDateMinutes;    // Minutes since year zero
   private int  usTSeconds;       // Thousandths of seconds
};


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

  stringTimeStamp = m_KZOEP1AA.SysGetDateTime( stringTimeStamp );
  rc = SetAttributeFromString( View, entityName, attributeName, stringTimeStamp );
  return rc;
}

public int
GetDateTime( StringBuilder szDate )
{

   KZOEP1AA.SysGetDateTime( szDate );

   return 0;
}


public int
GetDateTimeDATEONLY( StringBuilder szDate )
{
   //yyyyMMddHHmmssSSS
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
   Date date = new Date();

   szDate.insert(0, dateFormat.format(date));
   return 0;
}


protected int
fnValidateDayLocal( int usMonth, int usDay, int ulYear )
{
   int   usMax;

   if ( usDay >= 1 && usDay <= 28 )
      return( usDay );

   if ( usDay < 1 )
      return( 1 );

   switch ( usMonth )
   {
      case 2:
         usMax = 28;
         if ( (ulYear % 4) == 0 &&
              ((ulYear % 100) != 0 || (ulYear % 400) == 0) )
         {
            usMax = 29;
         }

         break;

      case 4:
      case 6:
      case 9:
      case 11:
         usMax = 30;
         break;

      default:
         usMax = 31;
   }

   if ( usDay > usMax )
      return( usMax );
   else
      return( usDay );

} /*** END fnValidateDay ***/

public int
GetDate( StringBuilder szDate )
{
   DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
   Date date = new Date();

   szDate.insert(0, dateFormat.format(date));
   return 0;
}

public int
GetDayNumber( )
{
   Calendar cal = Calendar.getInstance();
   // You cannot use Date class to extract individual Date fields
   int day = cal.get(Calendar.DAY_OF_WEEK);

   return day;
}

public int
PreviousMonth(StringBuilder startDate, StringBuilder endDate, int offset)
{
	Calendar prevMonth = Calendar.getInstance();
	prevMonth.add(Calendar.MONTH, offset);
	prevMonth.set(Calendar.DATE, 1);
	SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
	Date firstDayOfPreviousMonth = prevMonth.getTime();

	startDate.insert(0, dt.format(firstDayOfPreviousMonth));

	prevMonth.set(Calendar.DATE, prevMonth.getActualMaximum(Calendar.DATE));

	Date lastDayOfPreviousMonth = prevMonth.getTime();
	endDate.insert(0, dt.format(lastDayOfPreviousMonth));
	return 0;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>AddMinutesToDateTime
//
// .Description: Take a datetime and generate a new datetime which is later by the given number of minutes
//
// .Return Value: int
//
// .Parameter:
//    Data type       Name        (I/O/U) Description
//    String          szOriginalDate (I)  Input datetime
//    int             nMinutes       (I)  Minutes to add
//    StringBuilder   szReturnDate   (O)  Output datetime
//
/////////////////////////////////////////////////////////////////////////////////////////////////////
public int
AddMinutesToDateTime( String szOriginalDate,
                      int nMinutes,
                      StringBuilder szReturnDate )
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    Calendar cal = Calendar.getInstance();

    // Parse the original date as a Calendar object
    try
    {
        cal.setTime(dateFormat.parse(szOriginalDate));
    }
    catch (java.text.ParseException e)
    {
         return( zCALL_ERROR );
    }
    // Increment the minutes
    cal.add(Calendar.MINUTE, nMinutes);
    // Format as string and overwrite the return parameter StringBuilder
    szReturnDate.setLength(0);
    szReturnDate.append(dateFormat.format(cal.getTime()));
    return 0;
}

public int
AddMinutesToDate( View view, String entityName, String attributeName, int minutes )
{
   int nRC;

   nRC = AddToAttributeFromVariable( view, entityName,
                                     attributeName, minutes,
                                     zTYPE_INTEGER, 0, "Minute" );
   return nRC;
}

public int
GetEntityCount(View view, String rootEntity){
	int count = 0;
	int nRC = 0;

	nRC = SetCursorFirstEntity(view, rootEntity, "");
	while(nRC > zCURSOR_UNCHANGED)
	{
		nRC = SetCursorNextEntity(view, rootEntity, "");
		count++;
	}

	return count;
}

public int
InsertOI_TemplateFile(View view, View workView, String fromFile, String toFile, String stringRootEntityName) throws IOException
{
	BufferedWriter bw;
	StringBuilder sbInsertTemplate  = new StringBuilder();
	StringBuilder sbRawTemplate = new StringBuilder();
	StringBuilder sbEntityBuffer;
	StringBuilder sbAttributeBuffer;
	String swapString = null;
	String stringStart  = "{";
	String stringEnd    = "}";
	int nRC = 0;
	int lSelectedCount = 0;
	int lTemplateLth = 0;

	nRC = SetCursorFirstEntity(workView, stringRootEntityName, "");
	while(nRC > zCURSOR_UNCHANGED){
		lSelectedCount++;
		nRC = SetCursorNextEntity(workView, stringRootEntityName, "");
	}

	if (lSelectedCount <= 0)
		return 0;

	lTemplateLth = ReadFileDataIntoMemory(workView, fromFile, lTemplateLth, sbRawTemplate);

	if (lTemplateLth > Integer.MAX_VALUE)
		return 0;

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
				sbRawTemplate.replace(j, i, GetStringFromAttribute(workView,
																   sbEntityBuffer.toString(),
																   sbAttributeBuffer.toString()));

			}
		}

		sbInsertTemplate.append(sbRawTemplate);
		sbRawTemplate = new StringBuilder();
		nRC = SetCursorNextEntity(workView, stringRootEntityName, "");
	}

	sbInsertTemplate.insert(0, stringStart);
	sbInsertTemplate.append(stringEnd);

	bw = new BufferedWriter(new FileWriter(toFile));
	bw.write(sbInsertTemplate.toString());
	bw.flush();
	bw.close();

	return 0;
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
   int RESULT;

   decimalSum = 0.0;

   RESULT = SetCursorFirstEntity( vSum, entityName, stringParentName );
   while ( RESULT > zCURSOR_UNCHANGED )
   {
     decimalSum += GetDecimalFromAttribute( decimalValue, vSum, entityName, attributeName );
      RESULT = SetCursorNextEntity( vSum, entityName, stringParentName );
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

	  lValue.setValue(lValueInt);
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: ValidateEmailAddress
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public int
    ValidateEmailAddress (String emailEntered)
    {
       boolean validemail = EmailValidator.getInstance().isValid(emailEntered);
       if (validemail)
	  return 0;
       else
          return -1;
    } // ValidateEmailAddress



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
   stringReturnValue = m_KZOEP1AA.SysGetEnvVar( stringReturnValue, stringVariableName, nMaxReturnLth );
   return stringReturnValue;
}

/*
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>strIsInteger
//
// .Description: Return 1 if argument represents an integer, 0 if not
//
// .Return Value: int
//    1 - is number
//    0 - is not number
//
// .Parameter:
//    Data type,      Name    (I/O/U) Description
//    String          StringIn  (I)   string to be tested
//
/////////////////////////////////////////////////////////////////////////////////////////////////////
*/
public int
strIsInteger( String StringIn )
{
    if (StringIn.matches("^[-+]?\\d+$"))
        return 1;
    else
        return 0;
}
/*
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>DecimalToInt
//
// .Description: Convert an Decimal to a Int
//
// .Return Value: Int
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
public Integer
DecimalToInt( Double intStr )
{

      return intStr.intValue();

}
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>IntToDecimal
//
// .Description: Convert an Int to a Decimal
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

public Double
IntToDecimal( Integer intStr )
{

   return intStr.doubleValue();

}

/*
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>IsNumber
//
// .Description: Is String a Number
//
// .Return Value: int  0=yes, 2=No
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
IsNumber( String str )
{
   try {
      Double.valueOf( str);
   } catch (Exception e) {
      return 2;
   }
   return 0;
}

/*
/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// .Function Name: >>>strIsNumber
//
// .Description: Return 1 if argument represents a number (float or integer), 0 if not
//
// .Return Value: int
//    1 - is number
//    0 - is not number
//
// .Parameter:
//    Data type,      Name    (I/O/U) Description
//    String          StringIn  (I)   string to be tested
//
/////////////////////////////////////////////////////////////////////////////////////////////////////
*/
public int
strIsNumber( String StringIn )
{
    if (StringIn.matches("^[-+]?\\d+(\\.\\d+)?$"))
        return 1;
    else
        return 0;
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
	   if ( stringStr == null )
		   return 0.0;

	   if ( stringStr.equals("") )
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
//./ ADD NAME=UfDateTimeDiff
// Source Module=kzoeufaa.c
/////////////////////////////////////////////////////////////////////////////
//
// ENTRY:      UfDateTimeDiff
//
// PARAMETERS:
//             plDiff      - pointer to variable returning difference
//             lpDateTime1 - First Timestamp
//             lpDateTime2 - Second Timestamp
//             nDiffType   - unit for difference (zDT_SECOND, ...)
//
// RETURNS:     0 - Success
//             -1 - overflow
//    zCALL_ERROR - error during call (invalid AmountType)
//
/////////////////////////////////////////////////////////////////////////////
//./ END + 4
public int
UfDateTimeDiffLocal( int  lDiff,
                     DateTimeRecord   lpDateTime1,
                     DateTimeRecord   lpDateTime2,
                     int  nDiffType )
{
	if (nDiffType == zDT_DAY)
	{
		   lDiff = lpDateTime1.ulDateMinutes - lpDateTime2.ulDateMinutes;
		   lDiff = lDiff / 60 / 24;
	}
   return( lDiff );
} // UfDateTimeDiff

public MutableInt
GetDateAttributeDifferenceInDays( MutableInt    lDays,
                                  View   srcView,
                                  String srcEntityName,
                                  String srcAttributeName,
                                  View   tgtView,
                                  String tgtEntityName,
                                  String tgtAttributeName )
{
   DateTimeRecord  SourceDate = new DateTimeRecord();
   DateTimeRecord  TargetDate = new DateTimeRecord();
   String          stringSourceDate = null;
   String          stringTargetDate = null;
   int             lDaysTmp;

   // read the attributes
   stringSourceDate = GetStringFromAttribute( stringSourceDate, srcView, srcEntityName, srcAttributeName );
   stringTargetDate = GetStringFromAttribute( stringTargetDate, tgtView, tgtEntityName, tgtAttributeName );

   UfStringToDateTimeLocal( stringSourceDate, SourceDate );
   UfStringToDateTimeLocal( stringTargetDate, TargetDate );

   // subtract the values
   lDaysTmp = lDays.intValue();
   lDaysTmp = UfDateTimeDiffLocal( lDaysTmp, TargetDate, SourceDate, zDT_DAY );

   lDays.setValue(lDaysTmp);

   return lDays;
}

public int
UfStringToDateTimeLocal( String cpcDateTimeString, DateTimeRecord lpDateTime )
{
   int           usStringLth;
   int           usMonth;
   int           usDay;
   int           usDayOrg;
   int           usSeconds;
   int           usTSeconds;
   int           ulYear;
   int           ulHours;
   int           usMinutes;
   int           ulDateMinutes;
   int           ulDays;
   int           ulWorkYear;
   StringBuilder sbWorkString = new StringBuilder( 20 );
   DateTimeRecord lpDTInternal;
   boolean       bDateSet;
   int           nRC = 0;

   lpDTInternal = lpDateTime;

   // Null string will set the DateTime to 'NULL'
   if ( StringUtils.isBlank( cpcDateTimeString ) )
   {
      lpDTInternal.ulDateMinutes = lNullInteger;
      lpDTInternal.usTSeconds = 0;
      return( 0 );
   }

   usMonth = 0;
   usDay = 0;
   ulYear = 0;
   ulHours = 0;
   usMinutes = 0;
   usSeconds = 0;
   usTSeconds = 0;

   usStringLth = zstrlen( cpcDateTimeString );
   switch ( usStringLth )
   {
      case 17:   // YYYYMMDDHHmmSSTht
      case 16:   // YYYYMMDDHHmmSSTh
      case 15:   // YYYYMMDDHHmmSST
         // Get Thousandths of seconds Value
         zstrcpy( sbWorkString, cpcDateTimeString.substring( 14 ) );
         usTSeconds = zatol( sbWorkString.toString( ) );
         if ( usStringLth < 17 )
            usTSeconds *= (usStringLth == 16) ? 10 : 100 ;

      case 14:   // YYYYMMDDHHmmSS
         // Get Seconds Value
         sbWorkString.insert( 0, cpcDateTimeString.charAt( 12 ) );
         sbWorkString.insert( 1, cpcDateTimeString.charAt( 13 ) );
         sbWorkString.insert( 2, '\0' );
         usSeconds = zatol( sbWorkString.toString( ) );
         if ( usSeconds > 59 )
         {
            usSeconds = 59;
            nRC = zCALL_ERROR;
         }

      case 12:   // YYYYMMDDHHmm
         // Get Minutes Value
         sbWorkString.insert( 0, cpcDateTimeString.charAt( 10 ) );
         sbWorkString.insert( 1, cpcDateTimeString.charAt( 11 ) );
         sbWorkString.insert( 2, '\0' );
         usMinutes = zatol( sbWorkString.toString( ) );
         if ( usMinutes > 59 )
         {
            usMinutes = 59;
            nRC = zCALL_ERROR;
         }

         // Get Hours Value
         sbWorkString.insert( 0, cpcDateTimeString.charAt( 8 ) );
         sbWorkString.insert( 1, cpcDateTimeString.charAt( 9 ) );
         sbWorkString.insert( 2, '\0' );
         ulHours = zatol( sbWorkString.toString( ) );
         if ( ulHours > 23 )
         {
            ulHours = 23;
            nRC = zCALL_ERROR;
         }

      case 8:    // YYYYMMDD
         // Get Day Value
         //sbWorkString.insert( 0, cpcDateTimeString.charAt( 6 ) );
         //sbWorkString.insert( 1, cpcDateTimeString.charAt( 7 ) );
         //sbWorkString.insert( 2, '\0' );
         //usDay = zatol( sbWorkString.toString( ) );
         String str = new String(cpcDateTimeString.substring(6, 8));
         usDay = zatol( str );

         // Get Month Value
         //sbWorkString.insert( 0, cpcDateTimeString.charAt( 4 ) );
         //sbWorkString.insert( 1, cpcDateTimeString.charAt( 5 ) );
         //sbWorkString.insert( 2, '\0' );
         //usMonth = zatol( sbWorkString.toString( ) );
         str = cpcDateTimeString.substring(4, 6);
         usMonth = zatol( str );

         // Get Year Value
         //zstrncpy( sbWorkString, cpcDateTimeString, 4 );
         //sbWorkString.insert( 4, '\0' );
         //ulYear = zatol( sbWorkString.toString( ) );
         str = cpcDateTimeString.substring(0, 4);
         ulYear = zatol( str );

         // Check to see if we have date/datetime or only time without date.
         bDateSet = (ulYear != 0 || usMonth != 0 || usDay != 0);

         if ( ulYear == 0 )
         {
           ulYear = 1900;
           if ( bDateSet )
             nRC = zCALL_ERROR;
         }
         // the year will be multiplied with minutes/year and then stored as
         // unsigned long. These means, max. can be not much more than 8000
         if ( ulYear > 8000 )
         {
           ulYear = 8000;
           nRC = zCALL_ERROR;
         }

         // if month out of range, make it January
         if ( usMonth < 1 || usMonth > 12 )
         {
            usMonth = 1;
            if ( bDateSet )
               nRC = zCALL_ERROR;
         }

         // Get valid day for the month
         usDayOrg = usDay;
         usDay = fnValidateDayLocal( usMonth, usDayOrg, ulYear );
         if ( usDay != usDayOrg )
         {
           if ( bDateSet )
             nRC = zCALL_ERROR;
         }
         break;

      default:
         return( zCALL_ERROR );
   }


   /* Calculate Year in Minutes */
   ulWorkYear = 0;
   ulDateMinutes = 0;

   // Fast path for dates starting Jan 1, 1900.
   // Start point set to beginning of century.
   if ( ulYear >= 1900L )
   {
      ulWorkYear = 1900;
      ulDateMinutes = Year1900;
      if ( ulYear >= 2000L )
      {
         ulWorkYear = 2000;
         ulDateMinutes += Century;
      }
   }

   // This will get us to Jan 1, of the desired year.
   // This will take a bit longer when the year is less than 1900.
   while ( ulWorkYear < ulYear )
   {
      if ( (ulWorkYear % 4) == 0 &&
           ((ulWorkYear % 100) != 0 || (ulWorkYear % 400) == 0) )
      {
         ulDateMinutes += LeapYear;
      }
      else
      {
         ulDateMinutes += Year;
      }

      ulWorkYear++;
   }

   // This will get the number of days from the Jan 1,
   // to the beginning of the desired month.
   if ( (ulWorkYear % 4) == 0 &&
        ((ulWorkYear % 100) == 0 || (ulWorkYear % 400) == 0) )
   {
      ulDays = usLeapDayTable[ usMonth - 1 ];
   }
   else
   {
      ulDays = usDayTable[ usMonth - 1 ];
   }

   ulDays += usDay - 1;                 // add day of the month, for days
                                        // this year
   ulDateMinutes += ulDays * Day;       // add days_minutes to total minutes
   ulDateMinutes += ulHours * Hour;     // add hours_minutes to total minutes
   // add minutes to total minutes
   ulDateMinutes += (int)(usMinutes * Minute);

   // Now save this, before we forget...
   lpDTInternal.ulDateMinutes = ulDateMinutes;

   // Convert seconds to thousandths, and save it too.
   lpDTInternal.usTSeconds = usTSeconds + (usSeconds * 1000);

   return( nRC );

}  /* END of StringToDateTime */

///////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: ReturnFileNameFromPath
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public int
ReturnFileNameFromPath( StringBuilder stringReturnedFileName,
                        String stringFileName )
{
   String fNameFound;
   fNameFound=FilenameUtils.getName(stringFileName);
   stringReturnedFileName.setLength( 0 );
   stringReturnedFileName.append( fNameFound);
   return 0;

} // ReturnFileNameFromPath


public int
GetEntityNameFromStructure( String stringInternalEntityStructure, StringBuilder returnEntityName )
{
    returnEntityName.setLength( 0 );
    returnEntityName.append( stringInternalEntityStructure );
    return 0;
}

public String
GetEntityNameFromStructure( String stringInternalEntityStructure, String returnEntityName )
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
//  Method Name: AddYearsToDate
//
// Add Years To a Date Attribute
// This is using AddToAttributeFromVariable which was changed to include "Minute" on 03/18/20 in
// zeidon-operations 1.8.0
//
public int
AddYearsToDate( View view,
	   String entityName,
	   String attributeName,
	   int years )  // Days or Months?
{
int nRC;

nRC = AddToAttributeFromVariable( view, entityName,
				 attributeName, years,
				 zTYPE_INTEGER, 0, "Year" );
return nRC;
} // AddDaysToDate

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


////////////////////////////////////////////////////////////////////////////////////////////////////
//  Method Name: NewUUID
//
// Generate and return a UUID.
// Return values:
//   0 Success
//   1 Error
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public int
NewUUID( View ViewToWindow,
         StringBuilder uuidOut )
{
   try
   {
      UUID uuid = UUID.randomUUID();
      uuidOut.setLength(0);
      uuidOut.append(uuid.toString());
      return 0;
   }
   catch (Exception e)
   {
      System.out.println(e);
      e.printStackTrace();
      return 1;
   }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: ReturnUUID
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public int
ReturnUUID( StringBuilder stringReturnedUUID)
{
   UUID uuid = UUID.randomUUID();

   stringReturnedUUID.setLength( 0 );
   stringReturnedUUID.append(uuid.toString());
   return 0;
}

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

protected int
DecimalToString( Object dDecimalValue, StringBuilder sbReturn, int ulNumberOfDecimals )
{
   sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
   String formatString="%."+Integer.toString(ulNumberOfDecimals) +"f";
   String s = String.format(formatString, dDecimalValue);
   sbReturn.append( s );
   return 0;
}

  ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: strReplace
    //
    // returns 1 if the substring exists, else 0
    //
    //
    public int
    strReplace( String originalString, String textToFind, String textToReplaceWith, StringBuilder modifiedString )
    {
    	String replacedString = "";
    	modifiedString.setLength( 0 );
	if ( originalString.contains(textToFind) ) {
		replacedString=originalString.replace(textToFind,textToReplaceWith);
       		modifiedString.replace( 0, replacedString.length(), replacedString);
		return 1;
		}
        else
        	{
        	modifiedString.replace( 0, originalString.length(), originalString);
		return 0;
		}
    } // strSubstringCompareIgnoreCase

///////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: strSubstringCompareIgnoreCase
//
// returns 1 if the substring exists, else 0
//
//
public int
strSubstringCompareIgnoreCase( String MainStringVal, String SubStringVal )
{
String MainStringNoCase;

MainStringNoCase = MainStringVal.toLowerCase();
if ( MainStringNoCase.contains(SubStringVal.toLowerCase()) )
	return 1;
    else
	return 0;
} // strSubstringCompareIgnoreCase

   ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: strLower
    //
    // return string in lowercase
    //
    //
    public int
    strLower( StringBuilder stringStringInOut )
    {
        String s = StringUtils.trim( stringStringInOut.toString() );
        stringStringInOut.replace( 0, stringStringInOut.length(), s.toLowerCase() );
        return 0;
    } // strLower
   ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: strLen
    //
    // return length of a string.
    //
    //
    public int
    strLen( String StringIn )
    {
 	return StringIn.length();
    } // strLen

   ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: strUpper
    //
    // return string in upper case
    //
    //
    public int
    strUpper( StringBuilder stringStringInOut )
    {
        String s = StringUtils.trim( stringStringInOut.toString() );
        stringStringInOut.replace( 0, stringStringInOut.length(), s.toUpperCase() );
        return 0;
    } // strUpper

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: strLeft
    //
    // return left number of characters of a string.
    //
    //
    public int
    strLeft( StringBuilder stringStringInOut, int retlen )
    {
        String s = stringStringInOut.toString();
	if ( retlen > s.length() )
		return -2;
        stringStringInOut.replace( 0, stringStringInOut.length(), s.substring(0, retlen) );
        return 0;
    } // strLeft

   ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Method Name: strRight
    //
    // return right number of characters of a string.
    //
    //
    public int
    strRight( StringBuilder stringStringInOut, int retlen )
    {
        String s = stringStringInOut.toString();
	if ( retlen > s.length() )
		return -2;
        stringStringInOut.replace( 0, stringStringInOut.length(), s.substring(s.length() -1, s.length()) );
        return 0;
    } // strLeft

////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: RemoveLeadingBlanksFromAttrib
//
////////////////////////////////////////////////////////////////////////////////////////////////////
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

   hDocumentFile = m_KZOEP1AA.SysOpenFile(vResultSet, stringDocumentFile, COREFILE_READ);

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

   m_KZOEP1AA.SysReadFile( vResultSet, hDocumentFile, sbDocumentData, lDocumentLth );
   m_KZOEP1AA.SysCloseFile( vResultSet, hDocumentFile, 0 );


  /* if ( sbDocumentData == null )
   {
      sbDocumentData = null;
      lDocumentLth = 0;
      IssueError( vResultSet, 0, 0, "Read Error on Document file" );
      return -1;
   }*/

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
	   return 0;
}

public int ZipFile ( String sourceFile, String outputFile )
{
	// Zip a single file into a zip file.
	try
	{
		FileOutputStream fos = new FileOutputStream(outputFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
	}
	catch (Exception e)
	{
		return -1;
	}
    return 0;
}

public int ZipDirectory ( String sourceFile, String outputFile, String tempDirName )
{
	// Zip all of the files/directories under tempDirName into zipped file (outputFile)
	try
	{
	    FileOutputStream fos = new FileOutputStream(outputFile);
	    ZipOutputStream zipOut = new ZipOutputStream(fos);
	    File fileToZip = new File(sourceFile);
	    // For zipping up files for odt or docx, we don't want tempDirName to be part of the
	    // zip, we want all of the subfiles and directories.
        tempDirName = tempDirName + "/";
	    zipDirectoryFile(fileToZip, fileToZip.getName(), zipOut, tempDirName, true);
	    zipOut.close();
	    fos.close();
	}
	catch( Exception e )
	{
		return -1;
	}
    return 0;
}
private  void zipDirectoryFile(File fileToZip, String fileName, ZipOutputStream zipOut, String tempDirName, boolean firstTime) throws IOException {

	// KJS 10/27/20 - Not sure why the .isHidden is here but I know for document merge we want to zip all files.
    if (fileToZip.isHidden())
    {
        //return;
    }

    // Don't include the root directory as part of the zip.
	fileName = fileName.replace(tempDirName, "");
	TraceLineS("***** FILE BEING ZIPPED *** ", fileName );
    if (fileToZip.isDirectory()) {
    	// We don't want the root directory included (this is at least true for odt/docx file processing where we are unzipping/zipping.
    	// Maybe we want another flag to indicate if this is true or not.
    	if (!firstTime)
    	{
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
    	}
        File[] children = fileToZip.listFiles();
    	//TraceLineI("***** IS DIRECTORY with length *** ", children.length );
        for (File childFile : children) {
        	zipDirectoryFile(childFile, fileName + "/" + childFile.getName(), zipOut, tempDirName, false);
        }
        return;
    }
    //TraceLineS("***** IS NOT DIRECTORY *** ", "" );
    // Zip up the individual file.
    FileInputStream fis = new FileInputStream(fileToZip);
    ZipEntry zipEntry = new ZipEntry(fileName);
    zipOut.putNextEntry(zipEntry);
    byte[] bytes = new byte[1024];
    int length;
    while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
    }
    fis.close();
}

public int UnZipFile ( String zipFile, String outputDir )
{
	// Unzip file. For our odt/docx files, should we be deleting contents of directory first? Or maybe before we call this we should
	// be creating a directory that we delete after. Because I suppose we need a unique directory name in case more than one person is
	// running this.
	try
	{
        String fileZip = zipFile;
        File destDir = new File(outputDir);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null)
        {
        	TraceLineS("***** FILE BEING UPZIPPED *** ", zipEntry.getName() );
        	if ( zipEntry.isDirectory())
        	{
        		//TraceLineS("***** THIS IS A DIRECTORY **** ", "" );
        		String dirName = outputDir + zipEntry.getName();
                File file = new File(dirName);
                if (!file.exists())
                    file.mkdirs();
        	}
        	else
        	{
        		//TraceLineS("***** THIS IS A FILE **** ", destDir + " " + zipEntry + " " + outputDir );
                File newFile = newFile(destDir, zipEntry, outputDir);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
        	}
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
	catch (Exception e)
	{
		return -1;
	}
    return 0;
}
public static File newFile(File destinationDir, ZipEntry zipEntry, String outputDir) throws IOException
{
	if ( zipEntry.getName().contains("/") )
	{
		String dirName =  outputDir + zipEntry.getName().substring(0, zipEntry.getName().lastIndexOf("/"));
        File file = new File(dirName);
        if (!file.exists())
            file.mkdirs();
	}
    File destFile = new File(destinationDir, zipEntry.getName());

    String destDirPath = destinationDir.getCanonicalPath();
    String destFilePath = destFile.getCanonicalPath();

    if (!destFilePath.startsWith(destDirPath + File.separator))
        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());

    return destFile;
}

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

   return nRC;

} // ReadLine5000

    public static int ValidateDateString(String value, String format )
    {
    	Date date = null;
    	//dd.MM.yyyy
    	format = format.replaceAll("YYYY", "yyyy");
    	format = format.replaceAll("YY", "yy");
    	format = format.replaceAll("DD", "dd");
    	try {
    	    SimpleDateFormat sdf = new SimpleDateFormat(format);
    	    date = sdf.parse(value);
    	    if (!value.equals(sdf.format(date))) {
    	        return -1;
    	    }
    	} catch (ParseException ex) {
    		return -1;
    	}
    	return 0;
    }

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
   return 0;
} // ConvertLineToEntity

public int SetAttrFromStrByContext( View view, String entityName, String attributeName, String value, String context )
{
   int      RESULT = 0;
   view.cursor( entityName ).getAttribute( attributeName ).setValue( value, context );

   RESULT = 0;
   return RESULT;
}


public int SendEmail( zVIEW sHost,
         String szUserEmailAddress,
         String szRecipientEmailAddress,
         String szRecipientCCEmailAddress,
         String szRecipientBCCEmailAddress,
         String szSubjectText,
         String szMessageBody,
         String szAttachmentFileName,
         StringBuilder szError )

{
   zVIEW   wXferO = new zVIEW( );

   GetViewByName( wXferO, "wXferO", sHost, zLEVEL_TASK );
   wXferO.cursor("Root").getAttribute("SendEmailError").setValue("");
   szError.setLength( 0 );
   szError.append("");

   if ( !sHost.cursor("Host").getAttribute("UsesServerEmail").getString().equals("Y"))
   {
	  task.log().error( "*** SendEmail: Server Email Disabled ****  " );
	  wXferO.cursor("Root").getAttribute("SendEmailError").setValue("Server Email Disabled. This is Host.UsesServerEmail field.");
	  szError.setLength( 0 );
	  szError.append( "Server Email Disabled. This is Host.UsesServerEmail field." );
      return -3;
   }
	InternetAddress fromAddress = null;
	InternetAddress usernameAddress = null;
	MimeBodyPart attachPart;
	String host = sHost.cursor("Host").getAttribute("SMTPServer").getString();
	String port = sHost.cursor("Host").getAttribute("SMTPPort").getString();
	String useSmtpUser=sHost.cursor("Host").getAttribute("UseSMTPUserAsSender").getString();
	final String username = sHost.cursor("Host").getAttribute("SMTPUser").getString();
	final String pswd = sHost.cursor("Host").getAttribute("SMTPPassword").getString();
	String enableTLS = sHost.cursor("Host").getAttribute("SMTPEnableTLS").getString();
	String enableSSL = sHost.cursor("Host").getAttribute("SMTPEnableSSL").getString();
	String from = szUserEmailAddress;

	String to[] = szRecipientEmailAddress.split("[\\s,;]+");
	InternetAddress[] toAddress = new InternetAddress[to.length];
	String cc[] = szRecipientCCEmailAddress.split("[\\s,;]+");
	InternetAddress[] ccAddress = new InternetAddress[cc.length];
	String bcc[] = szRecipientBCCEmailAddress.split("[\\s,;]+");
	InternetAddress[] bccAddress = new InternetAddress[bcc.length];


	// Set properties
	Properties props = new Properties();
	if (enableTLS.equals("Y")) {
	   props.put("mail.smtp.starttls.enable", "true");
	} else
	   props.put("mail.smtp.starttls.enable", "false");

	if (enableSSL.equals("Y"))
		props.put("mail.smtp.ssl.enable", "true");
	else
	props.put("mail.smtp.ssl.enable", "false");

	props.put("mail.transport.protocol", "smtp");

	props.put("mail.smtp.host", host);
	props.put("mail.smtp.ssl.trust", host);
	props.put("mail.smtp.port", port);
	props.put("mail.smtp.auth", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	// Get session
	//Session session = Session.getDefaultInstance(props,
	Session session = Session.getInstance(props,
	new javax.mail.Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, pswd);
	}
	});

	Transport transport = null;
	try {
		transport = session.getTransport();
	} catch (NoSuchProviderException e1) {
		// TODO Auto-generated catch block
		task.log().error( "*** SendEmail: " + e1.getMessage() + " ****  " );
		wXferO.cursor("Root").getAttribute("SendEmailError").setValue(e1.getMessage());
		szError.setLength( 0 );
		szError.append( e1.getMessage().substring(250) );
		e1.printStackTrace();
		return -3;
	}


	try {
	// Instantiate a message
	Message msg = new MimeMessage(session);
	// FOR ATTACHMENT
	Multipart multipart = new MimeMultipart();
	// creates body part for the message
	MimeBodyPart messageBodyPart = new MimeBodyPart();
	try
	{
		if ( from != null && !from.isEmpty() ) {
			fromAddress = new InternetAddress(from);
			}

			if ( username != null && !username.isEmpty() && useSmtpUser.equals("Y") ) {
			usernameAddress = new InternetAddress(username);
			}
		if ( szRecipientEmailAddress != null && !szRecipientEmailAddress.isEmpty() )
		{
			for(int iCnt =0; iCnt< to.length; iCnt++)
			{
				toAddress[iCnt] = new InternetAddress(to[iCnt]);
			}
		}
				if ( szRecipientCCEmailAddress != null && !szRecipientCCEmailAddress.isEmpty() )
		{
			for(int iCnt =0; iCnt< cc.length; iCnt++)
			{
				ccAddress[iCnt] = new InternetAddress(cc[iCnt]);
			}
		}
		if ( szRecipientBCCEmailAddress != null && !szRecipientBCCEmailAddress.isEmpty() )
		{
			for(int iCnt =0; iCnt< bcc.length; iCnt++)
			{
				bccAddress[iCnt] = new InternetAddress(bcc[iCnt]);
			}
		}
	} catch (AddressException e) {
		task.log().error( "*** SendEmail: setting addresses **** " );
		task.log().error( e );
		wXferO.cursor("Root").getAttribute("SendEmailError").setValue(e.getMessage());
		szError.setLength( 0 );
		szError.append( e.getMessage().substring(250) );
		return -1;
	}
	if (useSmtpUser.equals("Y")) {
		msg.setFrom(usernameAddress);
		msg.setReplyTo(new InternetAddress[]
      {fromAddress});
	} else {
    	msg.setFrom(fromAddress);
    }

	if ( szRecipientEmailAddress != null && !szRecipientEmailAddress.isEmpty() )
		msg.setRecipients(Message.RecipientType.TO, toAddress);
	if ( szRecipientCCEmailAddress != null && !szRecipientCCEmailAddress.isEmpty() )
		msg.setRecipients(Message.RecipientType.CC, ccAddress);
	if ( szRecipientBCCEmailAddress != null && !szRecipientBCCEmailAddress.isEmpty() )
		msg.setRecipients(Message.RecipientType.BCC, bccAddress);

	// Set the message subject and date we sent it.
	// msg.setSubject(szSubjectText);

	msg.setSubject(MimeUtility.encodeText(szSubjectText, "utf-8", "B"));
	msg.setSentDate(new Date());

	// Set message content
	//msg.setText(szMessageBody);
//	messageBodyPart.setContent(szMessageBody, "text/html");
	messageBodyPart.setContent(szMessageBody, "text/html; charset=utf-8");

	// code to add attachment
	if ( szAttachmentFileName != null && !szAttachmentFileName.equals("") )
	{
		boolean bMoreAttachments = true;
		int pos = 0;
		int startPos = 0;
		while ( szAttachmentFileName.indexOf(";", pos) >= 0 || bMoreAttachments  )
		{
			String attachFile = "";
			pos = szAttachmentFileName.indexOf(";", pos);
			if ( pos == -1 )
			{
				pos = szAttachmentFileName.length();
				bMoreAttachments = false;
			}
			attachFile = szAttachmentFileName.substring(startPos, pos);
			pos++;
			startPos = pos;
			attachPart = new MimeBodyPart();
			attachPart.attachFile(attachFile);
			multipart.addBodyPart(attachPart);
		}
	}

	// adds parts to the multipart
	multipart.addBodyPart(messageBodyPart);

	// sets the multipart as message's content
	msg.setContent(multipart);

	// Send the message
	transport.send(msg);
	}
	catch(Exception mex)
	{
		task.log().error( "*** SendEmail: Transport.send error ****  " );
		task.log().error( mex );
		wXferO.cursor("Root").getAttribute("SendEmailError").setValue("From: " + szUserEmailAddress + " To: " + szRecipientEmailAddress + " " + mex.getMessage());
		szError.setLength( 0 );
		szError.append( mex.getMessage());
		// Email was bad?
		if (mex instanceof SendFailedException)
		return -1;

		// SMTP connection bad?
		return -2;
	}

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
WL_QC( int    lFile,
       String stringInput,
       String stringTransChar,
       int    nBlankLineCnt ) throws IOException
{
   View   taskView = null; // TODO ??? = GetDefaultViewForActiveTask( );

   stringInput = stringInput.replaceAll( stringTransChar, "\"" );
   m_KZOEP1AA.SysWriteLine( taskView, lFile, stringInput );
   while ( nBlankLineCnt-- > 0 )
	   m_KZOEP1AA.SysWriteLine( taskView, lFile, "" );

   return 0;
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

////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: CheckImportDate
//
//    Validate a string is valud DD/MM/YYYY value string for a date import.
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public int
CheckImportDate (String DateVal)
{
int nRC = 0;
int day=0;
int month=0;
int year=0;
if (DateVal.length() != 10)
    return -1; // not valid size, must be exactly DD/MM/YYYY
if (!DateVal.substring(2,3).equals("/"))
    return -2; // missing first slash
if (!DateVal.substring(5,6).equals("/"))
    return -3; // missing second slash
day = Integer.parseInt(DateVal.substring(0,2));
month = Integer.parseInt(DateVal.substring(3,5));
year = Integer.parseInt(DateVal.substring(6,10));

if (year < 1800 || year > 2200)
   return -4; // invalid year
if (month < 0 || month > 12)
   return -5; // invalid month
if (day < 1)
   return -6; // invalid day
if (month == 2 && day > 29)
   return -6; // invalid day - ignore issues with leap year
if ((month == 1 || month == 3 || month == 5  || month == 7 || month == 8 || month == 10 || month == 12) && day > 31)
   return -6; // invalid day
if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
   return -6; // invalid day
return 0;

} // CheckImportDate

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

public int
CleanStateProvince(StringBuilder sbSource)
{
	if (sbSource == null){
		return 0;
	}

	String chkSource[] = {"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","HI","IA","ID","IL","IN","KS","KY","LA",
			              "MA","MO","MD","ME","MI","MN","MT","NC","NH","NJ","NM","NV","NY","OH","OK","OR","PA","RI","SC",
			              "SD","TN","TX","UT","VA","VT","WA","WI","WV"};
	String sTarget = sbSource.toString().toUpperCase();
	sbSource.setLength(0);
	int index = 0;

	while (index < 47){
		if (sTarget.equals(chkSource[index])){
			sbSource.insert(0, sTarget);
			break;
		}
		index++;
	}

	return 0;
}

public int
CleanDate(StringBuilder sbTarget)
{
	String pattern = "^000";

	Pattern regexp = Pattern.compile(pattern);

	Matcher m = regexp.matcher(sbTarget.toString());

	if (m.find()){
		sbTarget.setLength(0);
	}
	return 0;
}

public int
CleanPhone( StringBuilder sbTarget,
            StringBuilder sbSource )
{
	  StringBuilder sbTarget1;
	  int nRC;
      int targetIdx;
      int sourceIdx;
      int nbrToCopy;
      int lth;

      if ( sbTarget == null || sbSource == null )
      {
         SysMessageBox( null, "JOE System Error",
                        "CleanPhone: Invalid parameter.", 1 );
         return( qINVALIDPARAMETER );
      }
      sbTarget1 = new StringBuilder( "" );
      targetIdx = 0;
      sourceIdx = 0;
      nbrToCopy = 10;

      while ( sourceIdx < sbSource.length( ) && sbSource.charAt( sourceIdx ) != '\0' )
      {
         if ( sbSource.charAt( sourceIdx ) != '(' && sbSource.charAt( sourceIdx ) != ')' &&
    		  sbSource.charAt( sourceIdx ) != ' ' && sbSource.charAt( sourceIdx ) != '-' &&
    		  sbSource.charAt( sourceIdx ) != '.' )
            sbTarget1.insert( targetIdx++, sbSource.charAt( sourceIdx++ ) );
         else
        	sourceIdx++ ;
      }
      sbTarget.insert(0, sbTarget1.toString());
      return( sbTarget.length( ) );
}

public int
CleanSSN( StringBuilder sbTarget,
          String sbSource )
{
	StringBuilder sbTarget1;
	int nRC;
	int targetIdx;
	int sourceIdx;
	int nbrToCopy;
	int lth;

	if ( sbTarget == null || sbSource == null )
	{
		SysMessageBox( null, "JOE System Error",
				"CleanPhone: Invalid parameter.", 1 );
		return( qINVALIDPARAMETER );
	}
	sbTarget1 = new StringBuilder( "" );
	targetIdx = 0;
	sourceIdx = 0;
	nbrToCopy = 10;

	while ( sourceIdx < sbSource.length( ) && sbSource.charAt( sourceIdx ) != '\0' )
	{
		if ( sbSource.charAt( sourceIdx ) != '(' && sbSource.charAt( sourceIdx ) != ')' &&
				sbSource.charAt( sourceIdx ) != ' ' && sbSource.charAt( sourceIdx ) != '-' &&
				sbSource.charAt( sourceIdx ) != '.' )
			sbTarget1.insert( targetIdx++, sbSource.charAt( sourceIdx++ ) );
		else
			sourceIdx++ ;
	}
	sbTarget.insert(0, sbTarget1.toString());
	return( sbTarget.length( ) );
}

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

public int
SetCtrlState( View view, String ctrlTag, int statusType, int value )
{
    zVIEW  wXferO = new zVIEW( );

    if ( GetViewByName( wXferO, "wXferO", view, zLEVEL_TASK ) < 0 )
    	return -1;

    // I'm going to delete this ControlAction if it exists and just have it get created again...
	if ( wXferO.cursor("ControlAction").setFirst("ControlTag", ctrlTag).toInt() >= 0 )
		wXferO.cursor("ControlAction").deleteEntity();


    // Either disable or hide the control. We don't care if value = TRUE because then we
    // are not disabling or hiding and on refresh of the page, they will be visible again.
    if ( value == FALSE )
    {
    	// If this control tag does not already exist, then we need to create it.
    	if ( wXferO.cursor("ControlAction").setFirst("ControlTag", ctrlTag ).toInt() < zCURSOR_SET )
    	{
            wXferO.cursor("ControlAction").createEntity();
    		wXferO.cursor("ControlAction").getAttribute("ControlTag").setValue(ctrlTag);
    	}
    	if ( statusType == zCONTROL_STATUS_ENABLED )
    		wXferO.cursor("ControlAction").getAttribute("ActionType").setValue("Disable");

    	if ( statusType == zCONTROL_STATUS_VISIBLE )
    		wXferO.cursor("ControlAction").getAttribute("ActionType").setValue("Hide");

    }
    // If the value is TRUE (so we are enabling and showing), then get rid of the entity.
    else if ( value == TRUE )
    {
    	if ( wXferO.cursor("ControlAction").setFirst("ControlTag", ctrlTag ).toInt() >= zCURSOR_SET )
            wXferO.cursor("ControlAction").deleteEntity();
    }
   return 0;
}

public int
GetPasswordHash ( View ViewToWindow, String InputString, StringBuilder OutHashString, StringBuilder OutSalt) throws IOException
{
   StringBuilder HashedString;
   int retVal = 0;
   int RESULT = 0;
   String encryptchoice = "SHA-256";
   String saltchoice = "N";
   String saltVal = "";

   HashedString = new StringBuilder();
   zVIEW sHostHash = new zVIEW();
   RESULT = ActivateObjectInstance( sHostHash, "sHost", ViewToWindow, 0, zSINGLE );

   if (RESULT < 0) { // error occurred
      return -1;
   }
   encryptchoice = sHostHash.cursor("Host").getAttribute("HashMethod").getString().trim();
   saltchoice = sHostHash.cursor("Host").getAttribute("HashSalt").getString().trim();
   DropView( sHostHash);

   if ( InputString.equals("") || InputString.equals(null)) {
      zstrcpy( OutHashString,"");
      return 1;  // empty InputString not allowed
   }

   if ( !encryptchoice.equals("MD5") && !encryptchoice.equals("SHA-1") && !encryptchoice.equals("SHA-256") && !encryptchoice.equals("SHA-384") && !encryptchoice.equals("SHA-512") ) {
      zstrcpy( OutHashString,"");  // still null
      zstrcpy( OutSalt,"");
      return 2;
   }   // not a supported encryption type

   if ( encryptchoice.equals("") || encryptchoice.equals(null)) {
      encryptchoice = "SHA-256";
   }

   if (saltchoice.equals("") || saltchoice.equals(null)) {
      saltchoice = "N";
   }

   if (saltchoice.equals("Y")) {
      try {
         saltVal = getSalt();
         InputString = saltVal+InputString;
      }
      catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
         zstrcpy( OutHashString,"");
         zstrcpy( OutSalt,"");
         return -1;
      }
   }

   retVal = GetHexHash(InputString,HashedString,encryptchoice);
   if (retVal < 0 ) {
      zstrcpy( OutHashString,"");
      return -1;  // error
   }
   zstrcpy( OutHashString,HashedString);
   zstrcpy( OutSalt, saltVal);
   return 0;
} // GetPasswordHash

////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Method Name: getSalt()
//
// Creates a random salt to use with a password hash.
//
// Gotten from site: http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public String getSalt() throws NoSuchAlgorithmException
{
	String saltStr;
	StringBuffer sb = new StringBuffer();
	SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

	//Create array for salt
	byte[] salt = new byte[20];

	//Get a random salt
	sr.nextBytes(salt);

	// change to hex string
	for(int i=0; i< salt.length ;i++)
		 {
			sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
		 }

	// convert to string
	saltStr = sb.toString();
	//return salt as hex string
	return saltStr;
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Method Name: GetHexHash
//
// Creates a hash of a string in hex format
//
// Hash types allowed: MD5, SHA-1, SHA-256, SHA-384, SHA-512
// Currently salts are not supported
// Hash string is returned in HashString
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public int
GetHexHash ( String InputString, StringBuilder HashString, String EncryptType) throws IOException
{
   StringBuffer sb = new StringBuffer();

   if ( !EncryptType.equals("MD5") && !EncryptType.equals("SHA-1") && !EncryptType.equals("SHA-256") && !EncryptType.equals("SHA-384") && !EncryptType.equals("SHA-512") )
   {
      zstrcpy( HashString,""); // still null
      return -1;
   }   // not a supported encryption type


   if ( InputString == "" || InputString == null)
   {
  	   zstrcpy( HashString,""); // still null
	   return -1;  // empty InputString not allowed
   }
   try
   {
        MessageDigest md = MessageDigest.getInstance(EncryptType);
        md.update(InputString.getBytes());
        byte[] bytes = md.digest();

        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
   }
   catch (NoSuchAlgorithmException e)
   {
      e.printStackTrace();
      zstrcpy( HashString,"");
      return -1;
   }

    zstrcpy( HashString,sb.toString());
    return 0;
} // GetHexHash

public void
DELETE_File(String fileName)
{
   File f = null;
   boolean bool = false;

   try {

      // create new file
      f = new File(fileName);

      // tries to delete a non-existing file
      bool = f.delete();
    } catch(Exception e) {

      // if any error occurs
      e.printStackTrace();
   }
}

public int
CHECK_FileExists(String fileName)
{
      File f = null;
      boolean bool = false;

      try {

         // create new file
         f = new File(fileName);
         if (f.exists()) {
			 return 0;
		 }

    } catch(Exception e) {

         // if any error occurs
         e.printStackTrace();
      }
	  	 return 2;
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: strFirstWord
//
// Get the first characters up to a space in a string
//
//
public int
strFirstWord( StringBuilder stringStringInOut )
{
    String s = StringUtils.trim( stringStringInOut.toString() );
    if (s.contains(" ") ) // if there is a space, return string chopped, otherwise return all of the string.
       stringStringInOut.replace( 0, stringStringInOut.length(), s.substring(0, s.indexOf(" ")) );
    return 0;
} // strFirstWord

public int
RENAME_File(String fileName,String fileName2)
{
   File f = null;
   File f2 = null;
   boolean bool = false;

   try {
	   TraceLineS("rename ","1");
	   if (fileName==fileName2) {
		  return 0;
	   }

		// create new file
		f = new File(fileName);
		f2 = new File(fileName2);

		TraceLineS("rename ",fileName);
		TraceLineS("rename ",fileName2);
		if (f2.exists()) {
		   f2.delete();
		   TraceLineS("rename ","delete");
		}
		if (!f.exists())
		{
		   TraceLineS("rename ","file 1 doesnt exists");
		   return 2;
		}
		boolean success = f.renameTo(f2);

		if (!success)
		{
		   TraceLineS("rename ","no success");
		   return 2;
		}
    }
    catch(Exception e)
    {
		// if any error occurs
		e.printStackTrace();
	}
	TraceLineS("rename ","success");
	return 0;
}

public int
COPY_File(String fileName1,String fileName2)
{
      File f1 = null;
      File f2 = null;
      boolean bool = false;

      try {
      	 TraceLineS("copy ","1");
         // create new file
         f1 = new File(fileName1);
	 f2 = new File(fileName2);
         TraceLineS("copy from ",fileName1);
	 TraceLineS("copy to ",fileName2);
         if (f2.exists()) {
		f2.delete();
		TraceLineS("copy ","delete");
		}
	 if (!f1.exists()) {
	        TraceLineS("copy ","file 1 does not exist");
	         return 2;
	 	}
	FileUtils.copyFile(f1,f2);
	} catch(Exception e) {

         // if any error occurs
         e.printStackTrace();
        }
	TraceLineS("copy ","success");
	return 0;
}

public int
ShowHideMenuOption ( View ViewToWindow, String menuName, String menuOptionName, String showHide )
{
	zVIEW  KZXMLPGO = new zVIEW( );
    int RESULT = 0;
   // Hide a menu option during runtime

   if ( showHide.toLowerCase().equals("hide"))
   {
	   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );
	   // Loop through the DisableMenuOptions and only add one if it doesn't already exist.
	   RESULT = SetCursorFirstEntity( KZXMLPGO, "DisableMenuOption", "" );
	   while ( RESULT > zCURSOR_UNCHANGED )
	   {
	      if ( CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuName", menuName ) == 0  && CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuOptionName", menuOptionName ) == 0)
	      {
	         // This DisableMenuOption already exists so don't create it again, just exit.
	         return 0;
	      }

	      RESULT = SetCursorNextEntity( KZXMLPGO, "DisableMenuOption", "" );
	   }
	   // We didn't find it above so create one.
	   RESULT = CreateEntity( KZXMLPGO, "DisableMenuOption", zPOS_AFTER );
	   SetAttributeFromString( KZXMLPGO, "DisableMenuOption", "MenuName", menuName );
	   SetAttributeFromString( KZXMLPGO, "DisableMenuOption", "MenuOptionName", menuOptionName );

   } // End of Hiding
   else if ( showHide.toLowerCase().equals("show"))
   {
	   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );

	   //ShowOptionFromExistingMenu
	   RESULT = SetCursorFirstEntity( KZXMLPGO, "DisableMenuOption", "" );
	   while ( RESULT > zCURSOR_UNCHANGED )
	   {
	      if ( CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuName", menuName ) == 0  && CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuOptionName", menuOptionName ) == 0)
	      {
	         RESULT = DeleteEntity( KZXMLPGO, "DisableMenuOption", zPOS_NONE );
	      }

	      RESULT = SetCursorNextEntity( KZXMLPGO, "DisableMenuOption", "" );
	   }
   }
   else
   {
	   // Not a valid showHide option... return -3
	   return -3;
   }
   return 0;
}
public int
HideMenuOption ( View ViewToWindow, String menuName, String menuOptionName )
{

   zVIEW  KZXMLPGO = new zVIEW( );
   int RESULT = 0;
   // Hide a menu option during runtime

   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );
   // Loop through the DisableMenuOptions and only add one if it doesn't already exist.
   RESULT = SetCursorFirstEntity( KZXMLPGO, "DisableMenuOption", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   {
      if ( CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuName", menuName ) == 0  && CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuOptionName", menuOptionName ) == 0)
      {
         // This DisableMenuOption already exists so don't create it again, just exit.
         return 0;
      }

      RESULT = SetCursorNextEntity( KZXMLPGO, "DisableMenuOption", "" );
   }
   // We didn't find it above so create one.
   RESULT = CreateEntity( KZXMLPGO, "DisableMenuOption", zPOS_AFTER );
   SetAttributeFromString( KZXMLPGO, "DisableMenuOption", "MenuName", menuName );
   SetAttributeFromString( KZXMLPGO, "DisableMenuOption", "MenuOptionName", menuOptionName );

   return 0;
}
public int
ShowMenuOption ( View ViewToWindow,   String menuName, String menuOptionName )
{
   zVIEW  KZXMLPGO = new zVIEW( );
   int RESULT = 0;

   // If a menu option was hidden but we now want to see it

   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );

   //ShowOptionFromExistingMenu
   RESULT = SetCursorFirstEntity( KZXMLPGO, "DisableMenuOption", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   {
      if ( CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuName", menuName ) == 0  && CompareAttributeToString( KZXMLPGO, "DisableMenuOption", "MenuOptionName", menuOptionName ) == 0)
      {
         RESULT = DeleteEntity( KZXMLPGO, "DisableMenuOption", zPOS_NONE );
      }
      RESULT = SetCursorNextEntity( KZXMLPGO, "DisableMenuOption", "" );
   }

   // If we return -1 it means that we never found that menu option. It must not exist
   return -1;
}

//--------------------------------------------
//-------------------------------------------- Cookie Code
//--------------------------------------------
public int
cookieCheck (View ViewToWindow, HttpServletRequest request, HttpServletResponse response)
{
   zVIEW wXferO = new zVIEW();
   int RESULT = 0;
   int nDoReturn = 1;
   RESULT = GetViewByName(wXferO, "wXferO", ViewToWindow, zLEVEL_TASK);
   try {
      SetAttributeFromString( wXferO, "Root", "WorkInteger", "0" );

      Cookie[] cookies = request.getCookies();
      int i = 0;
      for (Cookie cookie : cookies ) {
        TraceLineS("Name+++++++++++++++++++++++++++++++++++++++++++++++++++",cookies[i].getName());
        TraceLineS("Value++++++++++++++++++++++++++++++++++++++++++++++++++",cookies[i].getValue());
        if (cookies[i].getName().equals("NAZID") )  {
           SetAttributeFromString( wXferO, "Root", "WorkInteger",cookies[i].getValue() );
           nDoReturn++;
        }
        i++;
      }
   }
   catch (Exception e) {
      return -1;
   }
   // return 1;
   return 1;
}

public int
setTrustCookie (View ViewToWindow, HttpServletRequest request, HttpServletResponse response)
{
   zVIEW mCurrentWebUser = new zVIEW();
   zVIEW PBSystemValues = new zVIEW();
   int RESULT = 0;
   RESULT = GetViewByName(mCurrentWebUser, "mCurrentWebUser", ViewToWindow, zLEVEL_TASK);
   RESULT = GetViewByName(PBSystemValues, "PBSystemValues", ViewToWindow, zLEVEL_TASK);

   try {
      String cvalue = mCurrentWebUser.cursor("WebUserLogEntry").getAttribute("wRememberIPAddress").getString();

      int cookieLife = 90;


      String cexists = "N";

      TraceLineS("-=-=-=-=-==-=-==-=-=-=-=-=-=- wRememberIPAddress before Java proc: ",cvalue);
      if (cvalue.equals("Y")) {
         TraceLineS("-=-=-=-=-==-=-==-=-=-=-=-=-=- testing if the remember cookie exists","");
         Cookie[] cookies = request.getCookies();
         int i = 0;
         for (Cookie cookie : cookies ) {
            TraceLineS("Name+++++++++++++++++++++++++++++++++++++++++++++++++++",cookies[i].getName());
            TraceLineS("Value++++++++++++++++++++++++++++++++++++++++++++++++++",cookies[i].getValue());
            if (cookies[i].getName().equals("PBA_X581211") && cookies[i].getValue().equals("OK")) {
               TraceLineS("YESYESYESYESYESYESYESYESYESYESYESYESYESYESYESYES","");
               cexists = "Y";
            }
            i++;
         }
         if (cexists.equals("N")) {
            TraceLineS("-=-=-=-=-==-=-==-=-=-=-=-=-=- the remember does not cookie exist - creating it","");
            Cookie rcookie = new Cookie("PBA_X581211", "OK");
            //rcookie.setPath("\\pbportal");
            rcookie.setMaxAge(365*24*60*60*1000);
            response.addCookie(rcookie);
            TraceLineS("SETTINGCOOKIE SETTINGCOOKIE SETTINGCOOKIE SETTINGCOOKIE SETTINGCOOKIE SETTINGCOOKIE ","");
         }
      }
      TraceLineS("-=-=-=-=-==-=-==-=-=-=-=-=-=- after the process is done","");
      Cookie[] acookies = request.getCookies();
      int i = 0;
      for (Cookie cookie : acookies ) {
         TraceLineS("Name+++++++++++++++++++++++++++++++++++++++++++++++++++",acookies[i].getName());
         TraceLineS("Value++++++++++++++++++++++++++++++++++++++++++++++++++",acookies[i].getValue());
         if (acookies[i].getName().equals("PBA_X581211") && acookies[i].getValue().equals("OK")) {
            TraceLineS("YESYESYESYESYESYESYESYESYESYESYESYESYESYESYESYES","");
            cexists = "Y";
         }
         i++;
      }
}
   catch (Exception e) {
      return -1;
   }
   return 1;

}


/////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Authenticate User Login with id.nazarene.org ldap
//
/////////////////////////////////////////////////////////////////////////////////////////////////////

public int
authenticateJndi(View ViewToWindow, String username, String password)
{
   zVIEW sHost = new zVIEW();
   String ldapServer;
   String ldapServerURL;
   DirContext searchCtx = null;
   DirContext loginCtx = null;
   NamingEnumeration<SearchResult> answers = null;
   int retVal = 1;
   String searchString;
   String principalString;
   int RESULT;

   // The initial bind to the LDAP server uses no username/password; it's just
   // for the purpose of being able to search for the user's dn
   Hashtable props = new Hashtable();
   props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
   props.put(Context.SECURITY_AUTHENTICATION, "simple");

   try {
      // Get LDAP server info from the sHost object
      RESULT = GetViewByName(sHost, "sHost", ViewToWindow, zLEVEL_TASK);
      ldapServer = sHost.cursor("Host").getAttribute("LDAPServer").getString();
      // This is to enable testing on localhost that does not work through secure LDAP
      if (sHost.cursor("Host").getAttribute("EnvironmentString").getString().equals("NO-SECURE")) {
         ldapServerURL = "ldap://" + ldapServer;
      }
      else {
         ldapServerURL = "ldaps://" + ldapServer + ":636";
      }
      TraceLineS("PROVIDER_URL is: ", ldapServerURL);
      props.put(Context.PROVIDER_URL, ldapServerURL);

      try {
         searchCtx = new InitialDirContext(props);
         TraceLineS("Initial connection successful", "");
      }
      catch (AuthenticationNotSupportedException ex) {
         TraceLineS("Binduser: Authentication is not supported by the server", "");
         retVal = -1;
      }
      catch (AuthenticationException ex) {
         TraceLineS("Binduser: Incorrect password or username:", ex.toString());
         retVal = -1;
      }
      catch (NamingException ex) {
         TraceLineS("Binduser: Error when trying to create the context:", ex.toString());
         retVal = -1;
      }
      if (retVal < 1) { // problem connecting to LDAP
         TraceLineS("Problem connecting to LDAP", "");
         return retVal;
      }
      // Search the directory for the user who's trying to log in
      SearchControls ctrls = new SearchControls();
      ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
      // Match their NMM username against either their email address or uid in LDAP
      searchString = "(&(authServices=NMM)(|(mail=" + username + ")(uid=" + username + ")))";
      answers = searchCtx.search("ou=people,dc=nazarene,dc=org", searchString, ctrls);
      if (answers.hasMore()) {
         javax.naming.directory.SearchResult result = answers.nextElement();
         String userDN = result.getNameInNamespace();
         TraceLineS("Found DN:", userDN);
         // Use the DN we just found to bind a second time, with the password entered by the user
         props.put(Context.SECURITY_PRINCIPAL, userDN);
         props.put(Context.SECURITY_CREDENTIALS, password);
         try {
            loginCtx = new InitialDirContext(props);
            TraceLineS("Login connection successful", "");
         }
         catch (AuthenticationException ex) {
            TraceLineS("Login user: Incorrect password or username", "");
            retVal = -1;
         }
      }
      else {
         TraceLineS("User with the correct authServices not found in LDAP:", username);
         retVal = -1;
      }
   }
   catch (Exception ex) {
      TraceLineS("Unhandled exception:", ex.toString());
      return -1;
   }
   return retVal;
}
public int
LodContainsEntity( View view, String entityName )
{
  try {
      EntityDef ve = view.getLodDef( ).getEntityDef( entityName );
      if ( ve != null )
      {
         return 1;
      }
      else
      {
         return -1;
      }
  }catch( Exception e) {
	  return -1;
  }
}

public int
LodContainsAttribute( View view, String entityName, String attribName )
{
  try {
      EntityDef ve = view.getLodDef( ).getEntityDef( entityName );
      if ( ve.getAttribute( attribName ) != null )
      {
         return 1;
      }
      else
      {
         return -1;
      }
  }catch( Exception e) {
	  return -1;
  }
}
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

               stringAltMemory = stringAltMemoryStartArea;

               // Perform the normal mapping code here.
               if ( nRC < 0 )
                  return nRC;

               // Finally copy the closing brace to the output file.
               stringAltMemoryNew = stringAltMemoryEndArea;
            }
            else
               stringAltMemory = "";

            stringMemoryNew = stringMemoryNewHold;
            lCurrentCount++;

            // Initialize Output values.
            stringMemoryStartEmailBody = stringMemoryNew;
            lLthNew = 0;

            // Copy the first brace that starts the file.
           stringMemory = stringMemoryStartArea;
           // Perform the normal mapping code here.
           stringEmbeddedImages = "";
           if ( nRC < 0 )
              return nRC;
           m_ZDRVROPR.CreateSeeMessage( lConnection, stringSMTPServer,
                              stringSenderEMailAddress,
                              stringRecipientEMailAddress,
                              "", "", stringSubjectLine, nMimeType,
                              stringMemoryStartEmailBody,
                              stringAltMemory, stringEmbeddedImages,
                              nHasAttachment, stringAttachmentFileName,
                              stringEMailUserName, stringEMailPassword );
         }
      }
      nRC = SetCursorNextEntity( vResultSet, stringRootEntityName, "" );
   }

   m_ZDRVROPR.CloseSeeConnection( lConnection );
   if ( lAltTemplateLth != 0 )
   {
   }

   return 0;

} // InsertOI_DataIntoEmailTemplate

 public int
 NumericDayOfWeek( String englishDay )
 {
 	// Wanted to use the format 'e' for the Date context 'Day' but that was returning 1 for Monday instead of 2.
 	// Our DaysOfWeek domain has Sunday as 1. So we will try this.
 	if (englishDay.toUpperCase().equals("SUNDAY"))
 		return 1;
 	else if (englishDay.toUpperCase().equals("MONDAY"))
 		return 2;
 	else if (englishDay.toUpperCase().equals("TUESDAY"))
 		return 3;
 	else if (englishDay.toUpperCase().equals("WEDNEDAY"))
 		return 4;
 	else if (englishDay.toUpperCase().equals("THURSDAY"))
 		return 5;
 	else if (englishDay.toUpperCase().equals("FRIDAY"))
 		return 6;
 	else if (englishDay.toUpperCase().equals("SATURDAY"))
 		return 7;
 	else
 		return -1;
}

// This is a rewrite of zGLOBAL2.RetrieveCommaDeliminatedValue. In AdvancedQuery, using "is in list" with a long list was taking a long time.
// Using this one is much faster but since it has not been tested in all of the areas I am afraid to get rid of the original.
public int
RetrieveCommaDeliminatedValueN( StringBuilder   ReturnedValue,
                                String   szLine,
                                int      ParameterNumber )
{
	   int      Length = 0;

	   // With a long string list, the original RetrieveCommaDeliminatedValue (writting in vml zGLOBAL2) took
	   // a long time to run through the whole list.
	   // This operation is much faster.
	   Length = zstrlen( szLine );
	   int pos1 = 0;

	    int pos = szLine.indexOf(",");
	    // Find the "ParameterNumber" comma in the list.
	    while (--ParameterNumber > 0 && pos != -1)
	    {
	    	if ( ParameterNumber >= 1 )
	    		pos1 = pos + 1;
	        pos = szLine.indexOf(",", pos + 1);
	    }

	    String str = "";
	    // Get the value between the previous comma and the ParameterNumber comma.
	    if (pos1 >= 0 && pos >= 0 )
	    	str = szLine.substring( pos1, pos );
	    else if (pos < 0 && ParameterNumber == 0 && pos1 != Length ) // Trying to send back value when there is no comma at end, and if there is comma at end send back "" on last time.
	    	str = szLine.substring( pos1, Length );
	   ReturnedValue.setLength( 0 );
	   ReturnedValue.append( str );
	return 0;
}

public int SearchAndReplace( StringBuilder sbString, String searchString, String replaceString )
{
    String szTest = sbString.toString();
	String szNew = szTest.replaceAll(searchString, replaceString);

    int position = szTest.indexOf( searchString );

	sbString.setLength( 0 );
	sbString.append(szNew);

   return sbString.length( );
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

   // For each entity, append the specified data to the list.
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

    return 0;
}

// StartEmailClientWithFiles

////////////////////////////////////////////////////////////////////////////////////////////////////
//
//  Method Name: ConvImportExcelToEntity
//
//    Imports an Excel file into an Object Entity
//
////////////////////////////////////////////////////////////////////////////////////////////////////
public int
ConvImportExcelToEntity ( View ViewToWindow, String FilenamePath, View ImportView, String EntityName) throws IOException
{
    return 0;
} // OpenExcelFile

}
