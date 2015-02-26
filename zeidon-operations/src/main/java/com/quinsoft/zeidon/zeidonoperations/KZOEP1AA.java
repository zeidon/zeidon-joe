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

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.zeidonoperations;

import com.quinsoft.zeidon.ActivateFlags;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
//import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.Domain;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.vml.VmlOperation;
import com.quinsoft.zeidon.vml.DriverApplication;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
//import org.apache.fop.datatypes.Length;
//import se.mbaeumer.fxmessagebox.MessageBox;

//import com.quinsoft.zeidon.vml.VmlOperation.FileItem;
//import com.quinsoft.zeidon.vml.VmlOperation.FileList;

/**
 * This file belongs to the c-to-java conversion system.  These files convert functionality
 * defined in older .c files into Java.  They use non-Java naming conventions to hopefully
 * make it easier to generate the correct .java files using the VML generator.
 *
 * This is the list of Zeidon global operations that are defined in KZOEP1AA.h.
 *
 * @author DG
 *
 */
public class KZOEP1AA extends VmlOperation
{
   final Object lockActMeta = new Object();

   public static final int zTASK_DIALOG = 1;
   public static final int zTASK_COMMAND = 2;

   // View flags.
   public static final int zVF_MESSAGEONDROP = 0x00000001;
   public static final int zVF_TRACEONDROP = 0x00000002;

   public static final int zASCENDING  = 0;
   public static final int zDESCENDING = 1;

   public static final int zCURRENT_OI = 0;
   public static final int zFORCE_RELOAD = 1;
   public static final int zSYS_CURRENT_OI = 2;
   public static final int zSYS_FORCE_RELOAD = 3;

   public static final int zNAME_AUTODROP = 0x0040;   // For SetNameFromView
   public static final int CM_ACTIVE_TYPE = 0;
   public static final int CM_REFER_TYPE = 2;

   protected static final String releaseCompatible = "1.0a2   ";
   protected static final String releaseCurrent = "1.0b0   ";

   private static class CMOD_Struct
   {
      String szOD;
      String szOD_ROOT;
      String szOD_NAME;
      String szOD_EXT;
      String szOD_PREFIX;
   // private int count = 0;

      private CMOD_Struct( String od, String odRoot, String odName, String odExt, String odPrefix )
      {
         szOD = od;
         szOD_ROOT = odRoot;
         szOD_NAME = odName;
         szOD_EXT = odExt;
         szOD_PREFIX = odPrefix;
      // count++;
      }
   };

   // ObjectDefinition Structure
   private static CMOD_Struct SRC_CMOD[] =
   {
      new CMOD_Struct( "TZOPHDRO", "HeaderFile",           "Name", ".HDR", "h" ),
      new CMOD_Struct( "--------", "SourceFile",           "Name", ".SRC", "src" ),
      new CMOD_Struct( "TZOGSRCO", "Operation",            "Name", ".POG", "gg" ),
      new CMOD_Struct( "TZDGSRCO", "Domain",               "Name", ".PDG", "dg" ),
      new CMOD_Struct( "TZEREMDO", "EntpER_Model",         "Name", ".PMD", "md" ),
      new CMOD_Struct( "TZERSASO", "SubjectArea",          "Name", ".PSA", "sa" ),
      new CMOD_Struct( "TZTENVRO", "TE_DB_Environ",        "Name", ".DTE", "te" ),
      new CMOD_Struct( "TZZOLODO", "LOD",                  "Name", ".LOD", "od" ),
      new CMOD_Struct( "TZZOPODO", "POD",                  "Name", ".POD", "pod" ),
      new CMOD_Struct( "TZWDVORO", "ViewObjRef",           "Name", ".PVR", "vr" ),
      new CMOD_Struct( "TZPESRCO", "PresEnvDef",           "Name", ".PPE", "pe" ),
      new CMOD_Struct( "TZWDLGSO", "Dialog",               "Tag",  ".PWD", "wd" ),
      new CMOD_Struct( "TZADCSDO", "UI_Spec",              "Name", ".PUI", "ui" ),
      new CMOD_Struct( "TZDGSRCO", "DomainGroup",          "Name", ".PDG", "dg" ),
      new CMOD_Struct( "TZOGSRCO", "GlobalOperationGroup", "Name", ".POG", "gg" ),
      new CMOD_Struct( "TZRPSRCO", "Report",               "Tag",  ".PRP", "pr" )
   };

   // DGC
   // NOTE: the second value (Root entity name) for TZOGSRCO and TZDGSRCO
   // is used (at least) in fnGetAndSetZKey.

   private static CMOD_Struct REFER_CMOD[] =
   {
      new CMOD_Struct( "TZOPHDRO", "HeaderFile",           "Name", ".HDR", "hr"   ),
      new CMOD_Struct( "--------", "SourceFile",           "Name", ".SRC", "srcr" ),
      new CMOD_Struct( "TZOGSRCO", "Operation",            "Name", ".POG", "ggr"  ),
      new CMOD_Struct( "TZDGSRCO", "Domain",               "Name", ".PDG", "dgr"  ),
      new CMOD_Struct( "TZEREMDO", "EntpER_Model",         "Name", ".PMD", "mdr"  ),
      new CMOD_Struct( "TZERSASO", "SubjectArea",          "Name", ".PSA", "sar"  ),
      new CMOD_Struct( "TZTENVRO", "TE_DB_Environ",        "Name", ".DTE", "ter"  ),
      new CMOD_Struct( "TZZOLODO", "LOD",                  "Name", ".LOD", "odr"  ),
      new CMOD_Struct( "TZZOPODO", "POD",                  "Name", ".POD", "podr" ),
      new CMOD_Struct( "TZWDVORO", "ViewObjRef",           "Name", ".PVR", "vrr"  ),
      new CMOD_Struct( "TZPESRCO", "PresEnvDef",           "Name", ".PPE", "per"  ),
      new CMOD_Struct( "TZWDLGSO", "Dialog",               "Tag",  ".PWD", "wdr"  ),
      new CMOD_Struct( "TZADCSDO", "UI_Spec",              "Name", ".PUI", "uir"  ),
      new CMOD_Struct( "TZDGSRCO", "DomainGroup",          "Name", ".PDG", "dgr"  ),
      new CMOD_Struct( "TZOGSRCO", "GlobalOperationGroup", "Name", ".POG", "ggr"  ),
      new CMOD_Struct( "TZRPSRCO", "Report",               "Tag",  ".PRP", "pr" )
   };

   public KZOEP1AA( TaskQualification taskQual )
   {
      super( taskQual );
   }

   public KZOEP1AA( Object requiredObject, Object...objects )
   {
      this( findTaskQual( objects ) );
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   public static int CM_GetTypePrefix( int nType, StringBuilder sbPrefix )
   {
      if ( nType <= zSOURCE_MAX_META )
      {
         zstrcpy( sbPrefix, SRC_CMOD[ nType ].szOD_PREFIX );
         return( 0 );
      }

      zstrcpy( sbPrefix, REFER_CMOD[ nType - 2000 ].szOD_PREFIX );
      return( 2 );
   }

   /**
    * Contains information regarding open files for a task.
   **/
   private static class FileList
   {
      private AtomicInteger fileCount = new AtomicInteger( 0 );
      private ConcurrentHashMap<Integer, FileItem> fileMap = new ConcurrentHashMap<Integer, FileItem>();
   }

   private static class FileItem
   {
      private File file;
      private BufferedReader reader;
      private BufferedWriter writer;

      private FileItem(File file, BufferedReader reader, BufferedWriter writer)
      {
         this.file = file;
         this.reader = reader;
         this.writer = writer;
      }
   }

   private BufferedReader getReaderFromInt( Task task, int fileHandle )
   {
      FileList fileList = (FileList) task.getCacheMap( FileList.class );
      return fileList.fileMap.get( fileHandle ).reader;
   }

   protected BufferedWriter getWriterFromInt( Task task, int fileHandle )
   {
      FileList fileList = (FileList) task.getCacheMap( FileList.class );
      return fileList.fileMap.get( fileHandle ).writer;
   }

   // To copy a file.  If bOverwrite = false, don't copy file if destination file already exists.
   public int SysCopyFile( View taskView, String fromFileName, String toFileName, int bOverwrite ) throws IOException
   {
      File file = JoeUtils.getFile( toFileName );
      if ( file.exists() && bOverwrite == 0 )
      {
         return -1;
      }

      IOUtils.copy( new FileReader( fromFileName ), new FileWriter( toFileName ) );
      return 0;
   }

   // To rename a file.  If bOverwrite = false, don't rename file if destination file already exists.
   public int SysRenameFile( View taskView, String fromFileName, String toFileName, int bOverwrite )
   {
      File from = JoeUtils.getFile( fromFileName );
      File to   = JoeUtils.getFile( toFileName );
      if ( to.exists() )
      {
         to.delete();
      }

      from.renameTo( to );
      return 0;
   }

   public String SysReadFile( TaskQualification taskView, int file, String returnBuffer, int bufferLth ) throws IOException
   {
      StringBuilder sb = new StringBuilder( bufferLth );
      SysReadFile( taskView, file, sb, bufferLth );
      return sb.toString( );
   }

   public int zSysReadLine( TaskQualification taskView, StringBuilder sbReturnBuffer, int file, int i ) throws IOException
   {
      return SysReadLine( taskView, sbReturnBuffer, file );
   }

   public String SysReadLineLth( TaskQualification taskView, String fileBuffer, File file, int lth )
   {
      throw new ZeidonException( "SysReadLineLth not supported." );
   }

   public int SysWriteFile( TaskQualification taskView, int file, String fileBuffer, int nLth ) throws IOException
   {
      BufferedWriter writer = getWriterFromInt( taskView.getTask( ), file );
      writer.write( fileBuffer.substring( 0, nLth ) );
      return 0;
   }

   public int SysWriteLineLth( TaskQualification taskView, int file, String fileBuffer, int nLth ) throws IOException
   {
      BufferedWriter writer = getWriterFromInt( taskView.getTask( ), file );
      writer.write( fileBuffer.substring( 0, nLth ) );
      writer.newLine();
      return 0;
   }

   public int SysSetFileTime( String fileName, String dateTime, int lControl )
   {
      //TODO: The problem with this function is that the dateTime string format is not the
      // same as Java's formatter.  Some work needs to be done to support the old
      // Zeidon format.  We'll do that some other time.
      throw new ZeidonException( "SysSetFileTime not supported." );
   }

   private static DateTimeFormatter dateFormat = DateTimeFormat.forPattern( "yyyyMMddHHmmssSSS" );
   public static final String SysGetDateTime( String currentDateTime )
   {
      DateTime date = new DateTime( );
      return dateFormat.print( date );
   }

   public static final void SysGetDateTime( StringBuilder sbCurrentDateTime )
   {
      sbCurrentDateTime.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbCurrentDateTime.append( SysGetDateTime( "dummystring" ) );
   }

   public static final String SysGetEnvVar( String stringReturnValue, String stringVariableName, int nMaxReturnLth )
   {
      stringReturnValue = JoeUtils.getEnvProperty( stringVariableName, false );
      return stringReturnValue;
   }

   public static final int SysGetEnvVar( StringBuilder sbReturnValue, String stringVariableName, int nMaxReturnLth )
   {
      sbReturnValue.setLength( 0 );
      sbReturnValue.append( JoeUtils.getEnvProperty( stringVariableName, false ) );
      return sbReturnValue.length( );
   }

   public final String SysReadZeidonIni( int file, String group, String parameter )
   {
      return task.readZeidonConfig( group, parameter );
   }

   public final String SysReadZeidonIni( int file, String group, String parameter, String returnValue )
   {
      return task.readZeidonConfig( group, parameter );
   }

   public int SysReadZeidonIni( int file, String group, String parameter, StringBuilder sbReturnValue )
   {
      String v = task.readZeidonConfig( group, parameter );
      sbReturnValue.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( v != null )  // if v is null and we insert it into the sb, sb is set to the string "null"
      {
         sbReturnValue.append( v );
         return 0;
      }
      else
      {
         task.log().error( "Error Loading Zeidon.ini from: %s for %s:%s", JavaObjectEngine.getInstance().getHomeDirectory( ), group, parameter );
         return -1;  // not found
      }
   }

   protected final int SysGetLocalDirectory( StringBuilder sbZDR )
   {
      // TODO Auto-generated method stub
      return 0;  // AnchorBlock.szZeidonBin;
   }

   public String SysGetFileDateTime( File file )
   {
      Date date = new java.util.Date( file.lastModified() );
      DateFormat df = new SimpleDateFormat( "yyyyMMdd HH:mm:ss" );
      return df.format( date );
   }

   public int SysOpenFile( TaskQualification taskView, String fileName, int control ) throws IOException
   {
      File file = JoeUtils.getFile( fileName );
      BufferedReader bufferedReader = null;
      BufferedWriter bufferedWriter = null;

      switch ( control )
      {
         case COREFILE_READ:
            if ( ! file.exists( ) )
            {
               return -1;
            }

            bufferedReader = new BufferedReader( new FileReader( file ) );
            break;

         case COREFILE_WRITE:
         case COREFILE_CREATE_NEW:
         case COREFILE_CREATE:
         case COREFILE_WRITELINE_BUFFERED:
            if ( file.exists( ) )
            {
               file.delete( );
            }

            file.createNewFile( );

            bufferedWriter = new BufferedWriter( new FileWriter( file ) );
            break;

         case COREFILE_APPEND:
         case COREFILE_UPDATE:
            bufferedWriter = new BufferedWriter( new FileWriter( file, true ) );
            break;

         case COREFILE_DELETE:
            if ( file.exists( ) )
            {
               file.delete( );
            }

            return 0;

         case COREFILE_RENAME:
            throw new ZeidonException( "Cannot Rename a file using SysOpenFile File=%s", fileName );

         case COREFILE_EXISTS:
            if ( file.exists( ) )
            {
               return 1;
            }

            return -1;

         case COREFILE_BACKUP:
            throw new ZeidonException( "COREFILE_BACKUP not currently supported.  File=%s", fileName );

         default:
            throw new ZeidonException( "Unknown option %d.  File=%s", control, fileName );
      }

      // Generated VML uses ints as file handles so we need to convert a File to a int.
      // We'll increment a file count and store the File in a concurrent hashmap.
      FileList fileList = task.getTask().getCacheMap( FileList.class );
      if ( fileList == null )
      {
         fileList = task.getTask().putCacheMap( FileList.class, new FileList() );
      }

      int fileHandle = fileList.fileCount.incrementAndGet( );
      fileList.fileMap.putIfAbsent( fileHandle, new FileItem( file, bufferedReader, bufferedWriter ) );

      return fileHandle;
   }

   public int SysCloseFile( TaskQualification taskView, int file, Task owningTask ) throws IOException
   {
      // owningTask is not necessary for JOE and is ignored.
      return SysCloseFile( taskView, file, 0 );
   }

   public int SysCloseFile( TaskQualification taskView, int file, int control ) throws IOException
   {
      FileList fileList = (FileList) taskView.getTask().getCacheMap( FileList.class );
      FileItem item = fileList.fileMap.get( file );
      fileList.fileMap.remove( file );
      if ( item.reader != null )
      {
         item.reader.close( );
      }

      if ( item.writer != null )
      {
         item.writer.close( );
      }

      return 0;
   }

   public int SysWriteLine( TaskQualification taskView, int file, String fileBuffer ) throws IOException
   {
      BufferedWriter writer = getWriterFromInt( taskView.getTask( ), file );
      writer.write( fileBuffer );
      writer.newLine( );
      return 0;
   }

   public int SysReadLine( TaskQualification taskView, StringBuilder sbReturnBuffer, int file ) throws IOException
   {
      BufferedReader reader = getReaderFromInt( taskView.getTask( ), file );
      String str = reader.readLine( );
      sbReturnBuffer.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( str == null )
      {
         return 0;  // we're done
      }

      sbReturnBuffer.append( str );
      return 1;  // still more to read
   }

   // To read a block of data from an opened file.
   public int SysReadFile( TaskQualification taskView, int file, StringBuilder sbReturnBuffer, int bufferLth ) throws IOException
   {
      BufferedReader reader = getReaderFromInt( taskView.getTask( ), file );
      char[] line = new char[ bufferLth ];
      int charsRead = reader.read( line );
      sbReturnBuffer.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturnBuffer.append( line, 0, charsRead );
      return charsRead;
   }

   public int SysGetFileSize( TaskQualification qual, int fileHandle )
   {
      FileList fileList = qual.getTask().getCacheMap( FileList.class );
      File file = fileList.fileMap.get( fileHandle ).file;
      return (int) file.length();
   }

   public String SysTranslateString( String pchReturnString, char UL )
   {
      if ( UL == 'U' || UL == 'u')
      {
         return pchReturnString.toUpperCase();
      }
      else
      {
         return pchReturnString.toLowerCase();
      }
   }

   public void SysTranslateString( StringBuilder sbReturnString, char UL )
   {
      String s = sbReturnString.toString( );
      if ( UL == 'U' || UL == 'u' )
      {
         s = s.toUpperCase();
      }
      else
      {
         s = s.toLowerCase();
      }

      sbReturnString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturnString.append( s );
   }

   public static int SysAppendcDirSep( StringBuilder sbDirectoryName )
   {
      String s = sbDirectoryName.toString( );
      s = s.replace( '\\', '/' );
      int nLth = s.length( );
      sbDirectoryName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbDirectoryName.append( s );
      if ( nLth > 1 && sbDirectoryName.charAt( nLth - 1 ) != '/' )
      {
         sbDirectoryName.insert( nLth++, '/' );
      }

      return nLth;
   }

/* deprecated by above implementation
   public int SysAppendcDirSep( StringBuilder sbReturnName )
   {
      int nLth = sbReturnName.length( );
      if ( nLth > 0 )
      {
         char c = sbReturnName.charAt( nLth - 1 );
         if ( c != '\\' && c != '/' )
         {
            sbReturnName.append( '\\' );
            nLth++;
         }
      }
      return nLth;
   }
*/

   public static String SysAppendcDirSep( String returnName )
   {
      StringBuilder sb = new StringBuilder( returnName );
      SysAppendcDirSep( sb );
      return sb.toString( );
   }

   public static String SysMakeWebFileName( Task task, Application app, int lFlags )
   {
      StringBuilder sb = new StringBuilder( 256 );
      sb.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( (lFlags & 0x00000001) != 0 )  // use Application
      {
         if ( app != null )
         {
            zstrcpy( sb, app.getName( ) );
         }
      }
      else
      if ( (lFlags & 0x00000002) != 0 )  // use Ini WebDirectory
      {
         if ( app != null )
         {
            sb.append( task.readZeidonConfig( "[App." + app.getName( ) + "]", "WebDirectory" ) );
         }

         if ( sb.length( ) == 0 )
         {
            sb.append( task.readZeidonConfig( "[Workstation]", "WebDirectory" ) );
         }
      }

      SysAppendcDirSep( sb );
      sb.append( "si" + task.getTaskId( ) );

   // task.log().debug( "SysMakeWebFileName: %s", sbReturnName );
      return sb.toString( );
   }

   public int SysMakeWebFileName( StringBuilder sbReturnName, View v, int lFlags )
   {
      Application app = v.getApplication( );
      sbReturnName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturnName.append( SysMakeWebFileName( v.getTask( ), v.getApplication( ), lFlags ) );
      return( sbReturnName.length( ) );
   }

   public String SysMakeWebFileName( TaskQualification tq, int lFlags )
   {
      return SysMakeWebFileName( tq.getTask( ), tq.getApplication( ), lFlags );
   }

   // Converts environment strings in source.
   //   For example:            %kzd%:%kzv%\bin
   //   Could be converted to:  c:\10c\bin
   public void SysConvertEnvironmentString( StringBuilder sbTarget, String source )
   {
      int  targetLength = 0;
      int  pos;
      int  k;
      sbTarget.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      for ( k = 0; k < source.length(); k++ )
      {
         if ( source.charAt( k ) != '%' )
         {
            sbTarget.append( source.charAt( k ) );
            targetLength++;
            continue;
         }

         k++;   // bump up to skip past %
         pos = k;
         while ( k < source.length() && source.charAt( k ) != '%' )
         {
            k++;
         }

         if ( k < source.length() && source.charAt( k ) == '%' )  // found terminating %
         {
         // String s = SysGetEnvVar( s, source.substring( pos, k ), 256 );
            String s = JoeUtils.getEnvProperty( source.substring( pos, k ), false );
            sbTarget.append( s );
            targetLength += s.length();
         }
      }

      sbTarget.setLength( targetLength );
      for ( k = 0; k < targetLength; k++ ) {
         if ( sbTarget.charAt( k ) == '\\' ) {
            sbTarget.setCharAt( k, '/' );
         }
      }
   }

   public String SysConvertEnvironmentString( String target, String source )
   {
      StringBuilder sb = new StringBuilder();
      SysConvertEnvironmentString( sb, source );
      return sb.toString();
   }

   //./ ADD NAME=SendDomainError
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:       SendDomainError
   //
   //  PURPOSE:     To log an error situation
   //
   //  DESCRIPTION: Domain processors can call this operation to raise an
   //               error situation at any time during processing.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SendDomainError( zVIEW zView, Domain lpDomain, int nSeverity, int lMessageID, int lInfo, String cpcInfo1, String cpcInfo2 )
   {
      String      pchTitle;
      int         lMsgType;
      String      szMsg;
      String      szMsgID;
      int         nEntityAttrFlag = 1;

      pchTitle = "Object Services - Application Logic Error";
      if ( nSeverity >= 0 && nSeverity < 16 )
      {
         lMsgType = zMSGQ_DOMAIN_ERROR;
      }
      else
      {
         lMsgType = zMSGQ_SYSTEM_ERROR;
      }

      switch ( lMessageID )
      {
         case 1:
            szMsgID = "TZDME001";
            szMsg = "Invalid Input Data Type.";
            nEntityAttrFlag = 0;
            break;

         case 2:
            szMsgID = "TZDME002";
            szMsg = "Text String exceeds attribute length.";
            break;

         case 3:
            szMsgID = "TZDME003";
            szMsg = "Attribute Type invalid for this Domain.";
            break;

         case 4:
            szMsgID = "TZDME004";
            szMsg = "Invalid Domain Entry Type.";
            break;

         case 5:
            szMsgID = "TZDME005";
            szMsg = "Table_Handler invalid for this Domain.";
            break;

         case 6:
            szMsgID = "TZDME006";
            szMsg = "Integer overflow.";
            break;

         case 7:
            szMsgID = "TZDME007";
            szMsg = "Integer underflow.";
            break;

         case 8:
            szMsgID = "TZDME008";
            szMsg = "Could not find context for Domain.";
            break;

         case 9:
            szMsgID = "TZDME009";
            szMsg = "Context edit string is invalid for Domain.";
            break;

         case 10:
            szMsgID = "TZDME010";
            szMsg = "DateTime input string invalid.";
            nEntityAttrFlag = 0;
            break;

         case 11:
            szMsgID = "TZDME011";
            szMsg = "Error storing value in record.";
            break;

         case 12:
            szMsgID = "TZDME012";
            szMsg = "Context Required when Type is INTEGER. ";
            break;

         case 13:
            szMsgID = "TZDME013";
            szMsg = "Context/cType Combination is invalid.";
            break;

         case 14:
            szMsgID = "TZDME014";
            szMsg = "Context is for retrieval only.";
            break;

         case 15:
            szMsgID = "TZDME015";
            szMsg = "Context only used for arithmetic operations.";
            break;

         case 16:
            szMsgID = "TZDME016";
            szMsg = "Input invalid for context.";
            break;

         case 17:
            szMsgID = "TZDME017";
            szMsg = "Context Required when Type is DECIMAL.";
            break;

         case 18:
            szMsgID = "TZDME018";
            szMsg = "Context edit string is null.";
            break;

         case 19:
            szMsgID = "TZDME019";
            szMsg = "International number formatting is not available.";
            break;

         case 20:
            szMsgID = "TZDME020";
            szMsg = "Invalid decimal string.";
            nEntityAttrFlag = 0;
            break;

         case 21:
            szMsgID = "TZDME021";
            szMsg = "Return area not large enough for formatted string.";
            break;

         case 22:
            szMsgID = "TZDME022";
            szMsg = "Only AlphaNumeric chars are allowed.";
            nEntityAttrFlag = 0;
            break;

         case 23:
            szMsgID = "TZDME023";
            szMsg = "Value is out of valid range.";
            nEntityAttrFlag = 0;
            break;

         case 24:
            szMsgID = "TZDME024";
            szMsg = "Invalid integer string.";
            nEntityAttrFlag = 0;
            break;

         case 25:
            szMsgID = "TZDME025";
            szMsg = "Invalid table value.";
            nEntityAttrFlag = 0;
            break;

         default:
            szMsgID = "TZDME???";
            szMsg = "Unknown Error Message ";
      }

      // If flag is off then check Zeidon INI file to see if we should turn it
      // on anyway.
      if ( nEntityAttrFlag == 0 )
      {
         String sz;

         sz = SysReadZeidonIni( -1, "[Debug]", "DomainMessage" );
         if ( sz.charAt( 0 ) == 'Y' || sz.charAt( 0 ) == 'y' )
         {
            nEntityAttrFlag = 1;
         }
      }

      if ( nEntityAttrFlag == 1 )
      {
         // If the message created was for a programming error (not an expected
         // end-user message), then add the Entity and Attribute.  This information
         // only appears when there is no LAND.MSG ... otherwise the german text
         // overwrites everything!
         if ( cpcInfo1.isEmpty() == false )
         {
            zstrcat( szMsg, "\n\n Debug Info 1:\t" );
            zstrcat( szMsg, cpcInfo1 );
         }

         if ( cpcInfo2.isEmpty() == false )
         {
            zstrcat( szMsg, "\n Debug Info 2:\t" );
            zstrcat( szMsg, cpcInfo2 );
         }
      }

      TraceLine( "DomainError: %s - %s   Type: %c   for Domain: %s  Info: %s.%s",
                 szMsgID, szMsg, lpDomain != null ? lpDomain.getDomainType() : '?',
                 lpDomain != null? lpDomain.getName() : "Domain Name Unknown",
                 cpcInfo1 != null ? cpcInfo1 : "", cpcInfo2 != null ? cpcInfo2 : "" );
      if ( nSeverity == -1 ) // domain validation only ... no error message!
      {
         return 0;
      }
      else
      {
         return MessageSend( zView, szMsgID, pchTitle, szMsg, lMsgType, 0 );
      }
   }

   //./ ADD NAME=SetViewFlags
   // Source Module=kzoevmaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:   SetViewFlags
   //
   //  PURPOSE: To set view flags.
   //
   //       Current flags:
   //          zVF_MESSAGEONDROP - Pops up a message when the view is about to
   //                              be dropped.
   //          zVF_TRACEONDROP   - Traces a message when the view is about to
   //                              be dropped.
   //
   //  RETURNS: 0           - OK
   //           zCALL_ERROR - View passed is invalid
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SetViewFlags( View lpView, int lControl )
   {
      if ( (lControl & zVF_MESSAGEONDROP) == 0 )
      {
         // TODO: DG???
      // lpView.bFlagDropView = false;
      }
      else
      {
      // lpView.bFlagDropView = true;
      }

      if ( (lControl & zVF_TRACEONDROP) == 0 )
      {
      // lpView.bTraceDropView = false;
      }
      else
      {
      // lpView.bTraceDropView = true;
      }

      return 0;
   }

   //./ ADD NAME=GetViewFlags
   // Source Module=kzoevmaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:   GetViewFlags
   //
   //  PURPOSE: To get view flags.
   //       Current flags:
   //          zVF_MESSAGEONDROP - Pops up a message when the view is about to
   //                              be dropped.
   //
   //  RETURNS: 0           - OK
   //           zCALL_ERROR - View passed is invalid
   //
   /////////////////////////////////////////////////////////////////////////////
   public int GetViewFlags( View  lpView )
   {
      int  lReturn = 0;

   // TODO: DG???
   // if ( lpView.bFlagDropView )
   //    lReturn |= zVF_MESSAGEONDROP;

   // if ( lpView.bTraceDropView )
   //    lReturn |= zVF_TRACEONDROP;

      return( lReturn );
   }

   public int ActivateWorkStation( zVIEW vTZCMWKSO, zVIEW vQual, int lControl )
   {
      StringBuilder sbZeidonWKS = new StringBuilder();

      if ( GetWKS_FileName( sbZeidonWKS ) == 0 )
      {
         return( ActivateOI_FromFile( vTZCMWKSO, "TZCMWKSO", vQual,
                                      sbZeidonWKS.toString(), lControl ) );
      }

      return -1;
   }

   public int SysSetFocusToWebCtrl( View v, String cpcDialog, String cpcWindow, String cpcEntity, String cpcCtrlTag )
   {
      //String szFullCtrlTag = "";

       //public static final String WEB_SESSION_VIEW_NAME = "_KZXMLPGO";
       View view = v.getViewByName( WEB_SESSION_VIEW_NAME );
       if ( view != null )
       {
           if ( ! view.cursor( "Dialog" ).setFirst( "DialogName", cpcDialog ).isSet() )
           {
               view.cursor( "Dialog" ).createEntity().setAttribute( "DialogName", cpcDialog );
           }
           if ( ! view.cursor( "Window" ).setFirst( "WindowName", cpcWindow ).isSet() )
           {
               view.cursor( "Window" ).createEntity().setAttribute( "WindowName", cpcWindow );
           }

      // Right now I am skipping code for cpcEntity because I don't fully understand and
      // Jeff doesn't seem to use that.  Will come back to that...
      view.cursor( "Window" ).setAttribute( "FocusCtrl", cpcCtrlTag );

      // Code to look at...
      /*
      *
      if ( cpcEntity && cpcEntity[ 0 ] )
      {
      int    lKey = GetEntityKey( v, cpcEntity );
      int   nLth;

      szFullCtrlTag[ 0 ] = '@';
      zstrcpy( szFullCtrlTag + 1, cpcCtrlTag );
      nLth = zstrlen( szFullCtrlTag );
      szFullCtrlTag[ nLth++ ] = '#';
      zltoa( lKey, szFullCtrlTag + nLth );
      }
      else
      zstrcpy( szFullCtrlTag, cpcCtrlTag );

      */
      }

      return 0;
   }

   //./ ADD NAME=zgSortEntityWithinParent
   // Source Module=tzcmutil.c
   /////////////////////////////////////////////////////////////////////////////////////////
   //
   //  TZ OPERATION: zgSortEntityWithinParent
   //
   //  PURPOSE:    This routine will sort an Entity (keeping its dependents)
   //              by any given 2 attributes. (if the 2nd attr is a null string,
   //              the only the first attr is used)
   //
   //              It is not efficient?, but it tends to work.
   //
   //  PARAMETERS: bAscDsc  -- Indicator for ascending or descending
   //                          (zASCENDING or zDESCENDING)
   //              view      -- view that contains the entity to be sorted AND
   //                          its' parent
   //              szEntityName  -- Entity to be sorted
   //              szAttribName  -- Name of the attribute for primary sort
   //              szAttribName2 -- Name of a second sort attribute or
   //                               null string
   //
   //  RETURNS:        number of entity swaps required to sort
   //             -1 - more than 32000 swaps required
   //
   /////////////////////////////////////////////////////////////////////////////////////////
   public int zgSortEntityWithinParent( int nAscDsc, View view, String szEntityName, String szAttribName, String szAttribName2 )
   {
      StringBuilder  sb = new StringBuilder();

      zstrcpy( sb, szAttribName );
      if ( nAscDsc == zDESCENDING )
      {
         zstrcat( sb, " D " );
      }
      else
      {
         zstrcat( sb, " A " );
      }

      if ( szAttribName2 != null && szAttribName2.isEmpty() == false )
      {
         zstrcat( sb, szAttribName2 );
         if ( nAscDsc == zDESCENDING )
         {
            zstrcat( sb, " D" );
         }
         else
         {
            zstrcat( sb, " A" );
         }
      }

      return( OrderEntityForView( view, szEntityName, sb.toString() ) );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  OPERATION:  GetWKS_FileName
   //
   //  PURPOSE:    To retrieve the Zeidon WorkStation( RepositoryClient )
   //              file name
   //
   //  PARAMETERS: pchZeidonWKS - a pointer to a string which is returned
   //                with the fully qualified RepositoryClient File Name.
   //
   //  RETURNS:    0 - FileName successfully retrieved
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   private int GetWKS_FileName( StringBuilder sbZeidonWKS )
   {
      sbZeidonWKS.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbZeidonWKS.append( SysGetEnvVar( sbZeidonWKS.toString(), "ZEIDON", 128 ) );
      if ( sbZeidonWKS.length() > 0 )
      {
         SysAppendcDirSep( sbZeidonWKS );
         zstrcat( sbZeidonWKS, "TZCMWKS8.POR" );
         return 0;
      }

      return -1;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CommitWorkstation
   //
   //  PURPOSE:    To localize the Commit To File of the Work Station Object,
   //              setting the correct flags.
   //
   //  PARAMETERS: WKS_View - view to the Work Station OI
   //
   //  RETURNS:    0           - Instance successfully Committed
   //              zCALL_ERROR - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int CommitWorkstation( View WKS_View )
   {
      StringBuilder szZeidonWKS = new StringBuilder();

      if ( GetWKS_FileName( szZeidonWKS ) == 0 )
      {
         // To avoid error messages on checkin of objects, that have RepositoryClient
         //  included, we turn the created flag off, before we save the workstation
         //  to por file.
         // zSET_INCR_PERSISTENT clears the "created" flag.
      // WKS_View.cursor( "RepositoryClient" ).setIncrementalFlags( zSET_INCR_PERSISTENT | zSET_INCR_CURSORPOS );
      // SetIncrementalUpdateFlags( WKS_View, "RepositoryClient",
      //                            zSET_INCR_PERSISTENT | zSET_INCR_CURSORPOS );
         return( CommitOI_ToFile( WKS_View, szZeidonWKS.toString(), zSINGLE | zINCREMENTAL ) );
      }

      return -1;
   }

   private int fnBreakDownZKey( int ulZKey, MutableInt plWKS_Id, MutableInt plMaxZKey )
   {
      plWKS_Id.setValue( ulZKey / 1000000 );
      while ( ulZKey > 999999999 )
      {
         ulZKey -= 1000000000;
      }

      while ( ulZKey > 99999999 )
      {
         ulZKey -= 100000000;
      }

      while ( ulZKey > 9999999 )
      {
         ulZKey -= 10000000;
      }

      while ( ulZKey > 999999 )
      {
         ulZKey -= 1000000;
      }

      plMaxZKey.setValue( ulZKey );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CreateMetaEntity
   //
   //  PURPOSE:    Creates a Zeidon Meta Entity and sets the ZKey attribute
   //              from the MaxZKey attribute found in the WorkStation
   //              Object
   //
   //  PARAMETERS: lpView - the meta object view to create the entity for
   //              szEntityName - the Zeidon meta entity name
   //              nPosition - integer indicating BEFORE or AFTER
   //
   //  RETURNS:    0 - Meta entity created
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   protected int CreateMetaEntity( View  vSubtask, View  lpView, String szEntityName, int nPosition )
   {
      zVIEW WKS_View = new zVIEW();
      zVIEW vZeidonCM = new zVIEW();
      zVIEW vTZCMREPO = new zVIEW();
      MutableInt miWKS_Id = new MutableInt();
      MutableInt miWkZKey = new MutableInt();
      int   lWKS_Id;
      int   lWkZKey;
      int   ulMaxZKey;

      GetViewByName( vZeidonCM, "ZeidonCM", vSubtask,  zLEVEL_APPLICATION );
      GetViewByName( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( WKS_View ) == false )  // View isn't there
      {
         MessageSend( lpView, "CM00459", "Configuration Management",
                      "The RepositoryClient View ID was not found",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      ulMaxZKey = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "MaxZKey" );

      // PETTIT  Add this code when we have a CPL to work with!!!!!!
      fnBreakDownZKey( ulMaxZKey, miWKS_Id, miWkZKey );
   // lWKS_Id = miWKS_Id.intValue();
      lWkZKey = miWkZKey.intValue();
      if ( ulMaxZKey < 1000000 || lWkZKey > 999999 )  // the lowest ZKey is 1 million
      {
         GetViewByName( vTZCMREPO, "TZCMREPO", vSubtask, zLEVEL_TASK );
         if ( isValid( vTZCMREPO ) == false )
         {
            if ( ActivateObjectInstance( vTZCMREPO, "TZCMREPO", lpView, 0, zSINGLE ) != 0 )
            {
               MessageSend( lpView, "CM00460", "Configuration Management",
                            "MaxZKey limit exceeded, unable to access Installation object to assign new WKS_Id",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            // return -1;
            }
         }

         if ( ulMaxZKey < 1000000 )  // the lowest ZKey is 1 million
         {
            MessageSend( lpView, "CM00472", "Configuration Management",
                         "Resetting Workstation Generated Key\nPlease check with system administrator",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         }

         if ( isValid( vTZCMREPO ) )
         {
            lWKS_Id = GetIntegerFromAttribute( vTZCMREPO, "Installation", "NextWKS_Id" );
            SetAttributeFromInteger( WKS_View, "RepositoryClient", "WKS_Id", lWKS_Id );
         }
         else
         {
            lWKS_Id = 92;
         }

         ulMaxZKey = lWKS_Id * 1000000;
         lWKS_Id++;
         if ( isValid( vTZCMREPO ) )
         {
            SetAttributeFromInteger( vTZCMREPO, "Installation", "NextWKS_Id", lWKS_Id );
            CommitObjectInstance( vTZCMREPO );
         }

         CommitWorkstation( WKS_View );
      }

      ulMaxZKey++;
      if ( CreateEntity( lpView, szEntityName, nPosition ) < 0 )
      {
         return -1;
      }

      if ( SetAttributeFromInteger( lpView, szEntityName, "ZKey", ulMaxZKey ) != 0 )
      {
         DeleteEntity( lpView, szEntityName, zREPOS_NONE );
         MessageSend( lpView, "CM00461", "Configuration Management",
                      "Unable to set MaxZKey in operation CreateMetaEntity",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      SetAttributeFromInteger( WKS_View, "RepositoryClient", "MaxZKey", ulMaxZKey );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CreateTemporalMetaEntity
   //
   //  PURPOSE:    Creates a Zeidon Meta Entity and sets the ZKey attribute
   //              from the MaxZKey attribute found in the WorkStation
   //              Object
   //
   //  PARAMETERS: lpView - the meta object view to create the entity for
   //              szEntityName - the Zeidon meta entity name
   //              nPosition - integer indicating BEFORE or AFTER
   //
   //  RETURNS:    0 - Meta entity created
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   protected int CreateTemporalMetaEntity( View vSubtask, View lpView, String szEntityName, int nPosition )
   {
      View vTZCMREPO;
      MutableInt plWKS_Id = new MutableInt();
      MutableInt plWkZKey = new MutableInt();
      int lWKS_Id;
      int lWkZKey;
      int ulMaxZKey;

      View vZeidonCM = task.getViewByName( "ZeidonCM" ); // zLEVEL_APPLICATION
      if ( isValid( vZeidonCM ) == false )
      {
         return -1;
      }

      View WKS_View = vZeidonCM.getViewByName( "TZCMWKSO" );  // zLEVEL_SUBTASK
   // GetViewByName( &WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( WKS_View ) == false ) // View isn't there
      {
         MessageSend( lpView, "CM00462", "Configuration Management",
                      "The RepositoryClient View ID was not found",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      ulMaxZKey = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "MaxZKey" );

   // PETTIT  Add this code when we have a CPL to work with!!!!!!
      fnBreakDownZKey( ulMaxZKey, plWKS_Id, plWkZKey );
   // lWKS_Id = plWKS_Id.toInteger();
      lWkZKey = plWkZKey.toInteger();
      if ( ulMaxZKey < 1000000 ||   // the lowest ZKey is 1 million
           lWkZKey > 999999 )
      {
         vTZCMREPO = task.getViewByName( "TZCMREPO" ); // zLEVEL_TASK
         if ( isValid( vTZCMREPO ) == false )
         {
            vTZCMREPO = lpView.activateObjectInstance( "TZCMREPO", null, ActivateFlags.SINGLE );
            if ( isValid( vTZCMREPO ) == false )
            {
               MessageSend( lpView, "CM00463", "Configuration Management",
                            "MaxZKey limit exceeded, unable to access Installation object to assign new WKS_Id",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            // return -1;
            }
         }

         if ( ulMaxZKey < 1000000 )  // the lowest ZKey is 1 million
         {
            MessageSend( lpView, "CM00472", "Configuration Management",
                         "Resetting Workstation Generated Key\nPlease check with system administrator",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         }

         if ( isValid( vTZCMREPO ) )
         {
            GetIntegerFromAttribute( plWKS_Id, vTZCMREPO, "Installation", "NextWKS_Id" );
            lWKS_Id = plWKS_Id.toInteger();
            SetAttributeFromInteger( WKS_View, "RepositoryClient", "WKS_Id", lWKS_Id );
         }
         else
         {
            lWKS_Id = 92;
         }

         ulMaxZKey = lWKS_Id * 1000000;
         lWKS_Id++;
         if ( isValid( vTZCMREPO ) )
         {
            SetAttributeFromInteger( vTZCMREPO, "Installation", "NextWKS_Id", lWKS_Id );
            CommitObjectInstance( vTZCMREPO );
         }

         CommitWorkstation( WKS_View );
      }

      ulMaxZKey++;
      if ( CreateTemporalEntity( lpView, szEntityName, nPosition ) < 0 )
      {
         return -1;
      }

      if ( SetAttributeFromInteger( lpView, szEntityName, "ZKey", ulMaxZKey ) != 0 )
      {
         DeleteEntity( lpView, szEntityName, zREPOS_NONE );
         MessageSend( lpView, "CM00464", "Configuration Management",
                      "Unable to set MaxZKey in operation CreateMetaEntity",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      SetAttributeFromInteger( WKS_View, "RepositoryClient", "MaxZKey", ulMaxZKey );
      return 0;
   }

   //./ ADD NAME=SfGetTaskInfo
   // Source Module=kzoetmaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfGetTaskInfo
   //
   //  PURPOSE:    To retrieve some task related information
   //
   //  PARAMETERS: sbReturnString - pointer to string where info is returned.
   //              nInfoRequest   - indicates what info is to be returned.
   //                            zTASK_DIALOG  - returns name of the current
   //                                            dialog.  May be null.
   //                            zTASK_COMMAND - returns command line info
   //                                            of the current task.  May
   //                                            be null.
   //              lpTask         - Task pointer.  User can use SfGetCurrentTask
   //                               to obtain this value from a view tied to the
   //                               current task (ViewToWindow should be great).
   //
   //  RETURNS:    0           - Information has been returned
   //              zCALL_ERROR - Error on call
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SfGetTaskInfo( StringBuilder sbReturnString, int nInfoRequest, TaskQualification taskQual, int subtask )
   {
      String s;
      int    nRC = 0;

      sbReturnString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      switch ( nInfoRequest )
      {
         case zTASK_DIALOG:
            s = DriverApplication.GetDialogTagFromSubtask( taskQual, subtask );
            if ( s.isEmpty( ) == false )
            {
               sbReturnString.append( s );
               SysTranslateString( sbReturnString, 'U' ); // guarantee upper-case
            }

            break;

         case zTASK_COMMAND:
            s = DriverApplication.GetCommandFromSubtask( taskQual, subtask );
            sbReturnString.append( s );
            break;

         default:
            // "KZOEE019 - Invalid GetTaskInfo request: "
            fnIssueCoreError( taskQual, null, 8, 19, nInfoRequest, "", "" );
            nRC = zCALL_ERROR;
      }

      return( nRC );
   }

   //./ ADD NAME=SfIsObjectServicesTask
   // Source Module=kzoetmaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfIsObjectServicesTask
   //
   //  PURPOSE:    To determine if a tesk is a registered task with
   //              object services. If a 0 task id is passed, assume
   //              the request is for the current task.
   //
   //  RETURNS:    0 - Task is not the ObjectServices Task
   //              1 - Task is the ObjectServices Task
   //
   /////////////////////////////////////////////////////////////////////////////
   public boolean SfIsObjectServicesTask( int task )
   {
      // TODO: DG???
   // if ( task != null && task.isValid( ) )
   // {
   //    return true;
   // }
   // else
      {
         return false;
      }
   }

   public Task SysGetTaskFromView( View v )
   {
      return v.getTask();
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private int fnCleanupActiveMetas( View vActiveMetas, View vZeidonCM )
   {
      String  szViewName;
      int     nRC;
      int     lTaskID;
      zVIEW   vActiveLookup = new zVIEW();
      zVIEW   vMeta = new zVIEW();

      CreateViewFromViewForTask( vActiveLookup, vActiveMetas, vZeidonCM );
      nRC = SetCursorFirstEntity( vActiveLookup, "W_MetaDef", "LPLR" );
      while ( nRC >= zCURSOR_SET )
      {
         lTaskID = GetIntegerFromAttribute( vActiveLookup, "W_MetaDef", "TaskID" );
         if ( lTaskID != 0 ) // any non-zero task id is valid (Win9x has negative IDs)
         {
            if ( SfIsObjectServicesTask( lTaskID ) == false )
            {
               szViewName = GetStringFromAttribute( vActiveLookup, "W_MetaDef", "CM_ViewName" );
               zstrcat( szViewName, ".u" );
               if ( GetViewByName( vMeta, szViewName, vZeidonCM, zLEVEL_SUBTASK ) > 0 )
               {
                  DropObjectInstance( vMeta );
               }
            }
         }

         nRC = SetCursorNextEntity( vActiveLookup, "W_MetaDef", "LPLR" );
      }

      DropView( vActiveLookup );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   private String fnGetTaskOI_ListName( View vSubtask )
   {
      StringBuilder sbTaskID = new StringBuilder();
      StringBuilder sbTaskOI_ListName = new StringBuilder();
      Task   t;

      t = SysGetTaskFromView( vSubtask );
      zsprintf( sbTaskID, "%08x", Integer.decode( t.getTaskId() ) );
      zstrcpy( sbTaskOI_ListName, "__CM." );
      zstrcat( sbTaskOI_ListName, sbTaskID.toString() );
      return sbTaskOI_ListName.toString();
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private int fnVerifyType( int nType )
   {
      if ( (nType < 0) ||
           ((nType > zSOURCE_MAX_META) && (nType < 1000)) ||
           ((nType >= 1000) && (nType < 2000)) ||
           (nType > zREFER_MAX_META) )
      {
         MessageSend( task.getTask(), "CM00701", "Configuration Management",
                      "Invalid Zeidon Type passed to CM Operation",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( nType <= zSOURCE_MAX_META )
      {
         return 0;
      }
      else
      {
         return( 2 );
      }
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    TruncateName8
   //
   // PURPOSE:  Force a name to <= 8 characters
   //
   /////////////////////////////////////////////////////////////////////////////
   public int TruncateName8( StringBuilder sbName )
   {
      int k;

      int nLth = sbName.length() < 8 ? sbName.length() : 8;
      for ( k = 0; k < nLth; k++ )
      {
         if ( sbName.charAt( k ) == 0 )
         {
            break;
         }

         if ( sbName.charAt( k ) == ' ' )
         {
            sbName.setCharAt( k, '_' );
         }
      }

      sbName.setLength( k );
      return( k );
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  fnGetActiveMetaByType
   //
   //  PURPOSE:   This function is used by PHASES 1 and 2 of PostActivate to
   //             return views to active instances in memory
   //
   //////////////////////////////////////////////////////////////////////////////
   private int fnGetActiveMetaByType( zVIEW vMeta, View vCM_Subtask, View vActiveMetas, int nType, int nRelNbr )
   {
      String  szViewName;
      int     nCursor;
      int     lTaskID;

      // if nRelNbr is 0, position on the first active instance of the type
      // requested
      // If we've positioned on an instance which has at one time been active
      // in memory attempt to retrieve the activated view to the instance
      if ( nRelNbr == 0 )
      {
         if ( nType <= zSOURCE_MAX_META )
         {
            nCursor = SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType",
                                                     "Type", nType, "" );
            if ( nCursor == zCURSOR_SET )
            {
               nCursor = SetCursorFirstEntity( vActiveMetas, "W_MetaDef", "" );
               while ( nCursor == zCURSOR_SET )
               {
                  lTaskID = GetIntegerFromAttribute( vActiveMetas, "W_MetaDef", "TaskID" );
                  if ( lTaskID != 0 ) // any non-zero task id is valid (Win9x has negative IDs)
                  {  // get the active view name for the meta in memory
                     szViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" ) + ".u";
                     GetViewByName( vMeta, szViewName, vCM_Subtask, zLEVEL_SUBTASK );
                     return( ++nRelNbr );
                  }

                  nCursor = SetCursorNextEntity( vActiveMetas, "W_MetaDef", "" );
               }
            }
         }
         else
         {
            nCursor = SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type", nType - 2000, "" );
            if ( nCursor == zCURSOR_SET )
            {
               nCursor = SetCursorFirstEntity( vActiveMetas, "W_MetaDef", "" );
               while ( nCursor == zCURSOR_SET )
               {
                  // get the active view name for the meta in memory
                  szViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" ) + ".r";
                  if ( GetViewByName( vMeta, szViewName, vCM_Subtask, zLEVEL_SUBTASK ) >= 0 )
                  {
                     return ++nRelNbr;
                  }

                  nCursor = SetCursorNextEntity( vActiveMetas, "W_MetaDef", "" );
               }
            }
         }
      }
      else
      {
         nCursor = SetCursorNextEntity( vActiveMetas, "W_MetaDef", "" );
         if ( nType <= zSOURCE_MAX_META )
         {
            while ( nCursor == zCURSOR_SET )
            {
               lTaskID = GetIntegerFromAttribute( vActiveMetas, "W_MetaDef", "TaskID" );
               if ( lTaskID  != 0 ) // any non-zero task id is valid (Win9x has negative IDs)
               {  // get the active view name for the meta in memory
                  szViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" ) + ".u";
                  GetViewByName( vMeta, szViewName, vCM_Subtask, zLEVEL_SUBTASK );
                  return ++nRelNbr;
               }

               nCursor = SetCursorNextEntity( vActiveMetas, "W_MetaDef", "" );
            }
         }
         else
         {
            while ( nCursor == zCURSOR_SET )
            {
               // get the active view name for the meta in memory
               szViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" ) + ".r";
               if ( GetViewByName( vMeta, szViewName, vCM_Subtask, zLEVEL_SUBTASK ) >= 0 )
               {
                  return ++nRelNbr;
               }

               nCursor = SetCursorNextEntity( vActiveMetas, "W_MetaDef", "" );
            }
         }
      }

      // Return 0, there are no more
      return 0;
   }

   private int RelinkAllSubobjectsForOI( View lpTgtView, String cpcTgtEntityName, View lpSrcView, String cpcSrcEntityName )
   {
      return lpTgtView.relinkOis( lpSrcView );
   }

   //////////////////////////////////////////////////////////////////////////////
   //////////////////////////////////////////////////////////////////////////////
   private int fnActivateDependentBySyncDate( View vSubtask, StringBuilder sbReturnSyncDate, View vWkTZCMLPLO,
                                              String pchSyncDate, int nDependentType, boolean bForce )
   {
      zVIEW   vMeta = new zVIEW();
      String  szDepSyncDate;
      int     nRC;

      // PETTIT Bypass of sync date code
      if ( bForce == false )
      {
         return 0;
      }

      nRC = 0;
      if ( SetCursorFirstEntityByInteger( vWkTZCMLPLO, "W_MetaType", "Type", nDependentType, "" ) == zCURSOR_SET )
      {
         if ( SetCursorFirstEntity( vWkTZCMLPLO, "W_MetaDef", "" ) == zCURSOR_SET )
         {
            do
            {
               szDepSyncDate = GetStringFromAttribute( vWkTZCMLPLO, "W_MetaDef", "LastSyncDate" );
               if ( bForce || zstrcmp( szDepSyncDate, pchSyncDate ) > 0 )
               {
                  synchronized( lockActMeta )  //  SysMutexLock( vSubtask, "ActMeta", 0, 0 );
                  {
                  // TraceLineX( "fnActivateDependentBySyncDate Locked Mutex: ActMeta  for Task: ",
                  //             (int) vSubtask->hTask );
                     fnActivateMetaOI( vSubtask, vMeta, vWkTZCMLPLO, nDependentType, 0 );
                  // TraceLineX( "fnActivateDependentBySyncDate Unlocking Mutex: ActMeta  for Task: ",
                  //             (int) vSubtask->hTask );

                  } // SysMutexUnlock( vSubtask, "ActMeta", 0 );

                  // After the object instance has been forced in, get its
                  // syncronization date again in case it was in turn updated
                  // by being forced in.
                  szDepSyncDate = GetStringFromAttribute( vWkTZCMLPLO, "W_MetaDef", "LastSyncDate" );
                  if ( zstrcmp( szDepSyncDate, sbReturnSyncDate.toString() ) > 0 )
                  {
                     zstrcpy( sbReturnSyncDate, szDepSyncDate );
                  }

                  nRC++;
                  if ( isValid( vMeta ) )
                  {
                     DropView( vMeta );
                  }
               }
            }  while ( SetCursorNextEntity( vWkTZCMLPLO, "W_MetaDef", "" ) == zCURSOR_SET );
         }
      }

      // Return the number of dependents forced in.
      return( nRC );
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //     fnTZZOLODO_BldFlatFromChild
   //           Post checkout of a LOD build's the derived flat side
   //
   //////////////////////////////////////////////////////////////////////////////
   public int ofnTZZOLODO_BldFlatFromChild( View vTZZOLOD1, View vTZZOLODO )
   {
      int  nRC;

      nRC = CheckExistenceOfEntity( vTZZOLOD1, "LOD_EntityChild" );
      while ( nRC >= zCURSOR_SET )
      {
         SetViewToSubobject( vTZZOLOD1, "LOD_EntityChild" );

         // Include Recursive Representation to Flat Representation
         IncludeSubobjectFromSubobject( vTZZOLODO, "LOD_Entity", vTZZOLOD1, "LOD_EntityParent", zPOS_AFTER );

         nRC = CheckExistenceOfEntity( vTZZOLOD1, "LOD_EntityChild" );
         if ( nRC >= zCURSOR_SET )
         {
            ofnTZZOLODO_BldFlatFromChild( vTZZOLOD1, vTZZOLODO );
         }

         ResetViewFromSubobject( vTZZOLOD1 );
         SetCursorNextEntity( vTZZOLOD1, "LOD_EntityChild", "" );
      }

      return( nRC );
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //     fnTZZOLODO_BldFlatFromRec
   //           Post checkout of a LOD build's the derived flat side
   //
   //////////////////////////////////////////////////////////////////////////////
   private int fnTZZOLODO_BuildFlatFromRec( View vLOD )
   {
      zVIEW   vTZZOLOD1 = new zVIEW();
      zVIEW   vTZZOLODO = new zVIEW();
      int     nRC;

      CreateViewFromViewForTask( vTZZOLOD1, vLOD, null );
      CreateViewFromViewForTask( vTZZOLODO, vLOD, null );

      SetCursorFirstEntity( vTZZOLOD1, "LOD_EntityParent", "" );

      // Include Recursive Representation to Flat Representation
      IncludeSubobjectFromSubobject( vTZZOLODO, "LOD_Entity", vTZZOLOD1, "LOD_EntityParent", zPOS_AFTER );

      nRC = CheckExistenceOfEntity( vTZZOLOD1, "LOD_EntityChild" );
      if ( nRC >= zCURSOR_SET )
      {
         nRC = ofnTZZOLODO_BldFlatFromChild( vTZZOLOD1, vTZZOLODO );
      }

      return( nRC );
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZOPSIGO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZOPSIGO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZOPSIGO, View vTZCMLPLO, int PhaseCtl, boolean bCheckin )
   {
      String   szWork;
      zVIEW    vActiveMetas = new zVIEW();
      zVIEW    vWkTZCMLPLO = new zVIEW();

      // The Operation Signature has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those instances.  Also, go
      // through all instance which are dependent on it and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?
                                                     // dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////

      // Bypass Phase 2 if this is an activation of a SOURCE Type Meta
      if ( PhaseCtl == 1 )
      {
         /////////////////////////////////////////////
         // Post Activate PHASE 2: Link up to all object instances in memory
         // which depend on us
         /////////////////////////////////////////////
      }

      /////////////////////////////////////////////
      // Post Activate PHASE 3: For all meta types we depend on, issue an
      // activate for all instances whose synchronization date is later than
      // our synchronization date.
      /////////////////////////////////////////////

      // For each type which has a syncronization date later than our own,
      // issue an activate for that lod
      CreateViewFromViewForTask( vWkTZCMLPLO, vTZCMLPLO, null );
      ResetViewFromSubobjectTop( vWkTZCMLPLO );
      szWork = GetStringFromAttribute( vTZCMLPLO, "W_MetaDef", "LastSyncDate" );
      DropView( vWkTZCMLPLO );
      // return success
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZEREMDO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZEREMDO_PostCheckout( zVIEW vCM_Subtask, View vTZEREMDO, View vTZCMLPLO, boolean bRepository )
   {
      // if the object instance just checked out has come from the
      // repository. Re-establish the derived paths used by the
      // ER/Model dialog
      if ( bRepository )
      {
         // For Every RelType_1 found in the object instance, find the RelLink to
         // the Other Entity and include it under the RelType_1 Entity.
         if ( SetCursorFirstEntity( vTZEREMDO, "ER_RelType_1", "EntpER_Model" ) >= zCURSOR_SET )
         {
            do
            {
               if ( CheckExistenceOfEntity( vTZEREMDO, "ER_RelLink_Other" ) < zCURSOR_SET )
               {
                  //BL, 2000.01.08 This Code does not work
   //             if ( SetCursorFirstEntityByEntityCsr( vTZEREMDO, "ER_RelLink_2",
   //                                                   vTZEREMDO, "ER_RelLink",
   //                                                   "EntpER_Model" ) >= zCURSOR_SET )
                  if ( SetCursorFirstEntityByAttr( vTZEREMDO, "ER_RelLink_2", "ZKey",
                                                   vTZEREMDO, "ER_RelLink", "ZKey",
                                                   "EntpER_Model" ) >= zCURSOR_SET )
                  {
                     if ( SetCursorNextEntity( vTZEREMDO, "ER_RelLink_2", "" ) != zCURSOR_SET )
                     {
                        SetCursorPrevEntity( vTZEREMDO, "ER_RelLink_2", "" );
                     }

                     IncludeSubobjectFromSubobject( vTZEREMDO, "ER_RelLink_Other",
                                                    vTZEREMDO, "ER_RelLink_2", zPOS_AFTER );
                  }
               }
            }  while( SetCursorNextEntity( vTZEREMDO, "ER_RelType_1", "EntpER_Model" ) >= zCURSOR_SET );
         }
      }

      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZEREMDO_PostActivate
   //
   //  PURPOSE:   This function post processes the activate of an incremental
   //             E/R model incremental object instance. It is intended to
   //             be called by configuration management AFTER the file is
   //             brought into memory AND the configuration management
   //             name is created for it in the configuration management
   //             subtask.
   //
   //             This function links up to any memory active Domains and LODS.
   //
   //             Additionally, if the ER/MODEL incremental file's last
   //             refresh date is less than the LPLR's last refresh date,
   //             then all domains whose refresh date
   //             is later than the ER/MODEL's date are activated. Note that
   //             simply activating those files will insure their linking
   //             to this object instance.
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZEREMDO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZEREMDO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW    vActiveMetas = new zVIEW();
      zVIEW    vTZDMSRCO = new zVIEW();
      zVIEW    vDomainLPLR = new zVIEW();
      zVIEW    vTZEREMDO_Temp = new zVIEW();
      int      lZKey;
      int      nRC;
      String   szDomainName;
      String   szTempName;
      boolean  bRelinkError;

      // The E/R Model has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this? dks ... 2004.09.16

   // Modification made by Don Christensen, 2/10/95
   // This modification assumes the forced top-down relink.  It forces
   // a relink to all Domains and deletes any ER_Attribute entities that
   // reference a non-existent Domain.  It then skips the rest of the
   // normal relink processing.
   // This code has been enhanced to re-include a Domain by the same name as
   // the original and to use the Text Domain if such a Domain could not be found.

      bRelinkError = false;
      CreateViewFromViewForTask( vDomainLPLR, vTZCMLPLO, null );
      SetCursorFirstEntityByInteger( vDomainLPLR, "W_MetaType", "Type", zREFER_DOMAIN_META, "" );
      CreateViewFromViewForTask( vTZEREMDO_Temp, vTZEREMDO, null );
      for ( nRC = SetCursorFirstEntity( vTZEREMDO_Temp, "Domain", "EntpER_Model" );
            nRC >= zCURSOR_SET;
            nRC = SetCursorNextEntity( vTZEREMDO_Temp, "Domain", "EntpER_Model" ) )
      {
         lZKey = GetIntegerFromAttribute( vTZEREMDO_Temp, "Domain", "ZKey" );
         TraceLineI( "cfTZEREMDO_PostActivate Relinking ZKey: ", lZKey );
         nRC = ActivateMetaOI_ByZKey( vSubtask, vTZDMSRCO, null, zREFER_DOMAIN_META, zSINGLE, lZKey, 0 );
         if ( nRC >= 0 )
         {
            SetNameForView( vTZEREMDO_Temp, "DKS_TZEREMDO", vSubtask, zLEVEL_TASK );
            SetNameForView( vTZDMSRCO, "DKS_TZDMSRCO", vSubtask, zLEVEL_TASK );
            RelinkInstanceToInstance( vTZEREMDO_Temp, "Domain", vTZDMSRCO, "Domain" );
            DropMetaOI( vSubtask, vTZDMSRCO );
         }
         else
         {
            szDomainName = GetStringFromAttribute( vTZEREMDO_Temp, "Domain", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vTZDMSRCO, null, zREFER_DOMAIN_META, zSINGLE, szDomainName, 0 );
            if ( nRC < 0 )
            {
               bRelinkError = true;
               TraceLineI( "Can't relink Domain. ZKey: ", lZKey );
               TraceLineS( "Can't relink Domain. Name: ", szDomainName );
               szTempName = GetStringFromAttribute( vTZEREMDO_Temp, "ER_Entity", "Name" );
               TraceLineS( "Can't relink Domain. Entity: ", szTempName );
               szTempName = GetStringFromAttribute( vTZEREMDO_Temp, "ER_Attribute", "Name" );
               TraceLineS( "Can't relink Domain. Attribute: ", szTempName );
               nRC = SetCursorFirstEntityByString( vDomainLPLR, "W_MetaDef", "Name", "TextForMissingZKey", "" );
               if ( nRC >= 0 )
               {
                  ActivateMetaOI_ByName( vSubtask, vTZDMSRCO, null, zREFER_DOMAIN_META, zSINGLE, "TextForMissingZKey", 0 );
               }
            }

            if ( nRC < 0 )
            {
               nRC = ActivateMetaOI_ByName( vSubtask, vTZDMSRCO, null, zREFER_DOMAIN_META, zSINGLE, "Text", 0 );
            }

            if ( nRC >= 0 )
            {
               ExcludeEntity( vTZEREMDO_Temp, "Domain", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vTZEREMDO_Temp, "Domain", vTZDMSRCO, "Domain", zPOS_AFTER );
               DropMetaOI( vSubtask, vTZDMSRCO );
            }
            else
            {
               String szMsg = "Deleting Attribute because of missing Domain: " + szDomainName;
               MessageSend( vSubtask, "CM00901", "Configuration Management",
                            szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               DeleteEntity( vTZEREMDO_Temp, "ER_Attribute", zREPOS_NONE );
            }
         }
      }

      DropView( vTZEREMDO_Temp );
      if ( bRelinkError )
      {
         MessageSend( vSubtask, "CM00904", "Configuration Management",
                      "Error in relinking Domains. See trace for more information.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      }

      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZERSASO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZERSASO_PostCheckout( View vCM_Subtask, View vTZERSASO, View vTZCMLPLO, boolean bRepository )
   {
      zVIEW vSA2 = new zVIEW();

      if ( bRepository )
      {
         // For every ER_RelType in the subject area, instantiate the ER_RelLink
         // under both the source and target entity.
         if ( SetCursorFirstEntity( vTZERSASO, "ER_RelType", "SubjectArea" )
                                                               >= zCURSOR_SET )
         {
            CreateViewFromViewForTask( vSA2, vTZERSASO, null );
            do
            {
               SetViewFromView( vSA2, vTZERSASO );
               if ( SetCursorNextEntity( vSA2, "ER_RelLink_2", "" ) >= zCURSOR_SET &&
                    SetCursorFirstEntityByEntityCsr( vTZERSASO, "ER_Entity", vTZERSASO, "ER_Entity_2", "SubjectArea" ) >= zCURSOR_SET &&
                    SetCursorFirstEntityByEntityCsr( vSA2, "ER_Entity", vSA2, "ER_Entity_2", "SubjectArea" ) >= zCURSOR_SET )
               {
                  // We only need to do the following includes if we just
                  // activated the SA directly from the database and not
                  // from shadow objects.  If we activated from shadow objects,
                  // then the relationships have already been created.
                  if ( SetCursorFirstEntityByAttr( vTZERSASO, "ER_RelLink",   "ZKey",
                                                   vTZERSASO, "ER_RelLink_2", "ZKey", "" ) < zCURSOR_SET )
                  {
                     SetCursorLastEntity( vTZERSASO, "ER_RelLink", "" );
                     IncludeSubobjectFromSubobject( vTZERSASO, "ER_RelLink", vTZERSASO, "ER_RelLink_2", zPOS_LAST );
                     IncludeSubobjectFromSubobject( vTZERSASO, "ER_RelLink_Other", vSA2, "ER_RelLink_2", zPOS_FIRST );
                     IncludeSubobjectFromSubobject( vSA2, "ER_RelLink", vSA2, "ER_RelLink_2", zPOS_LAST );
                     IncludeSubobjectFromSubobject( vSA2, "ER_RelLink_Other", vTZERSASO, "ER_RelLink_2", zPOS_FIRST );
                  }
               }
            }  while ( SetCursorNextEntity( vTZERSASO, "ER_RelType", "SubjectArea" ) >= zCURSOR_SET );

            DropView( vSA2 );
         }
      }

      return 0;
   }

   private int oTZERSASO_SA_RelinkDelete( View vSA, View vSubtask )
   {
      zVIEW  vERD = new zVIEW();
      zVIEW  vLPLR = new zVIEW();
      int    nRC;
      int    lZKey;

      RetrieveViewForMetaList( vSubtask, vLPLR, zREFER_ERD_META );
      ActivateMetaOI( vSubtask, vERD, vLPLR, zREFER_ERD_META, 0 );
      DropMetaOI( vSubtask, vLPLR );

      nRC = SetCursorFirstEntity( vSA, "SA_Entity", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lZKey = GetIntegerFromAttribute( vSA, "ER_Entity", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( vERD, "ER_Entity", "ZKey", lZKey, "" );
         if ( nRC >= zCURSOR_SET )
         {
            // Relink ER_Entity.  Will this create a problem for ER_RelLink entries?
            // Do we need to relink ER_RelLink entries & ER_RelLink_Other entries?
            // Or will this be taken care of under SA_RelType processing below?
            nRC = SetCursorFirstEntity( vSA, "ER_RelLink", "" );
            while ( nRC > zCURSOR_UNCHANGED )
            {
               lZKey = GetIntegerFromAttribute( vSA, "ER_RelType_1", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vERD, "ER_RelType", "ZKey", lZKey, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  // ??
               }
               else
               {
                  // MG_ErrorMessage = "Excluding SA RelLink: " + vSA.ER_RelLink.Name
                  // MessageSend( vSubtask, "ER00405", "E/R Model Maintainance",
                  //              MG_ErrorMessage, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
                  ExcludeEntity( vSA, "ER_RelLink", zREPOS_NONE );
               }

               nRC = SetCursorNextEntity( vSA, "ER_RelLink", "" );
            }
         }
         else
         {
            // MG_ErrorMessage = "Deleting SA Entity: " + vSA.ER_Entity.Name
            // MessageSend( vSubtask, "ER00406", "E/R Model Maintainance",
            //              MG_ErrorMessage, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            DeleteEntity( vSA, "SA_Entity", zREPOS_NONE );
         }

         nRC = SetCursorNextEntity( vSA, "SA_Entity", "" );
      }

      nRC = SetCursorFirstEntity( vSA, "SA_RelType", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lZKey = GetIntegerFromAttribute( vSA, "ER_RelType", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( vERD, "ER_RelType", "ZKey", lZKey, "" );
         if ( nRC >= zCURSOR_SET )
         {
            // Relink ER_RelType
         }
         else
         {
            // MG_ErrorMessage = "Deleting SA RelType for: " + vSA.ER_RelLink_2.Name
            // MessageSend( vSubtask, "ER00407", "E/R Model Maintainance",
            //              MG_ErrorMessage, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            DeleteEntity( vSA, "SA_RelType", zREPOS_NONE );
         }

         nRC = SetCursorNextEntity( vSA, "SA_RelType", "" );
      }

      nRC = SetCursorFirstEntity( vSA, "OwnedER_Entity", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lZKey = GetIntegerFromAttribute( vSA, "OwnedER_Entity", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( vERD, "ER_Entity", "ZKey", lZKey, "" );
         if ( nRC >= zCURSOR_SET )
         {
            // Relink OwnedER_Entity
         }
         else
         {
            // MG_ErrorMessage = "Excluding SA Owned Entity: " + vSA.OwnedER_Entity.Name
            // MessageSend( vSubtask, "ER00408", "E/R Model Maintainance",
            //              MG_ErrorMessage, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            ExcludeEntity( vSA, "OwnedER_Entity", zREPOS_NONE );
         }

         nRC = SetCursorNextEntity( vSA, "OwnedER_Entity", "" );
      }

      nRC = SetCursorFirstEntity( vSA, "OwnedER_RelType", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lZKey = GetIntegerFromAttribute( vSA, "OwnedER_RelType", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( vERD, "ER_RelType", "ZKey", lZKey, "" );
         if ( nRC >= zCURSOR_SET )
         {
            // Relink OwnedER_RelType
         }
         else
         {
            // MG_ErrorMessage = "Excluding SA Owned RelType"
            // MessageSend( vSubtask, "ER00409", "E/R Model Maintainance",
            //              MG_ErrorMessage, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 )
            ExcludeEntity( vSA, "OwnedER_RelType", zREPOS_NONE );
         }

         nRC = SetCursorNextEntity( vSA, "OwnedER_RelType", "" );
      }

      DropMetaOI( vSubtask, vERD );
      return 0;
   }

   private void fnLinkAndSyncER_ToSA( View vSubtask, View vTZEREMDO, View vTZERSASO )
   {
      zVIEW vER2 = new zVIEW();
      zVIEW vSA2 = new zVIEW();
      zVIEW vSA3 = new zVIEW();
      int   lZKey1;
      int   lZKey2;

      // We have retrieved a view to an active ERD, link up the
      // Subject Area to the ER_MODEL.
      RelinkAllSubobjectsForOI( vTZERSASO, "ER_Entity", vTZEREMDO, "ER_Entity" );
      RelinkAllSubobjectsForOI( vTZERSASO, "ER_RelType", vTZEREMDO, "ER_RelType" );
      RelinkAllSubobjectsForOI( vTZERSASO, "EntpER_Model", vTZEREMDO, "EntpER_Model" );
      RelinkAllSubobjectsForOI( vTZERSASO, "ER_RelLink", vTZEREMDO, "ER_RelLink" );
      RelinkAllSubobjectsForOI( vTZERSASO, "ER_RelLink_Other", vTZEREMDO, "ER_RelLink_Other" );
      RelinkAllSubobjectsForOI( vTZERSASO, "OwnedER_Entity", vTZEREMDO, "ER_Entity" );
      RelinkAllSubobjectsForOI( vTZERSASO, "OwnedER_RelType", vTZEREMDO, "ER_RelType" );
      RelinkAllSubobjectsForOI( vTZEREMDO, "EntOwningSA", vTZERSASO, "SubjectArea" );
      RelinkAllSubobjectsForOI( vTZEREMDO, "RelOwningSA", vTZERSASO, "SubjectArea" );

      // Now that we have relinked to the er_model, we need to syncronize the SA with the model
      if ( SetCursorFirstEntity( vTZERSASO, "SA_Entity", "SubjectArea" ) >= zCURSOR_SET )
      {
         do
         {
            lZKey1 = GetIntegerFromAttribute( vTZERSASO, "ER_Entity", "ZKey" );
            if ( SetCursorFirstEntityByInteger( vTZEREMDO, "ER_Entity", "ZKey", lZKey1, "" ) != zCURSOR_SET )
            {
               DeleteEntity( vTZERSASO, "SA_Entity", zREPOS_NONE );
            }

         }  while ( SetCursorNextEntity( vTZERSASO, "SA_Entity", "SubjectArea" ) > zCURSOR_UNCHANGED );
      }

      // Now that we have relinked to the er_model, we need to
      // syncronize the SA with the model
      if ( SetCursorFirstEntity( vTZERSASO, "SA_RelType", "SubjectArea" ) >= zCURSOR_SET )
      {
         do
         {
            lZKey1 = GetIntegerFromAttribute( vTZERSASO, "ER_RelType", "ZKey" );
            if ( SetCursorFirstEntityByInteger( vTZEREMDO, "ER_RelType", "ZKey", lZKey1, "" ) != zCURSOR_SET )
            {
               if ( SetCursorFirstEntityByInteger( vTZERSASO, "ER_RelType_1", "ZKey", lZKey1, "SubjectArea" ) != 0 )
               {
                  ExcludeEntity( vTZERSASO, "ER_RelLink", zREPOS_NONE );
                  if ( SetCursorNextEntityByInteger( vTZERSASO, "ER_RelType_1", "ZKey", lZKey1, "SubjectArea" ) >= zCURSOR_SET )
                  {
                     ExcludeEntity( vTZERSASO, "ER_RelLink", zREPOS_NONE );
                  }
               }

               DeleteEntity( vTZERSASO, "SA_RelType", zREPOS_NONE );
            }
         }  while ( SetCursorNextEntity( vTZERSASO, "SA_RelType", "SubjectArea" ) > zCURSOR_UNCHANGED );
      }

      // Now re-establish the RelLink_Other path
      if ( SetCursorFirstEntity( vTZERSASO, "ER_RelType_1", "SubjectArea" ) >= zCURSOR_SET )
      {
         do
         {
            if ( CheckExistenceOfEntity( vTZERSASO, "ER_RelLink_Other" ) < zCURSOR_SET )
            {
               lZKey1 = GetIntegerFromAttribute( vTZERSASO, "ER_RelLink", "ZKey" );
               SetCursorFirstEntityByInteger( vTZEREMDO, "ER_RelLink", "ZKey", lZKey1, "EntpER_Model" );
               IncludeSubobjectFromSubobject( vTZERSASO, "ER_RelLink_Other", vTZEREMDO, "ER_RelLink_Other", zPOS_AFTER );
            }
         } while ( SetCursorNextEntity( vTZERSASO, "ER_RelType_1", "SubjectArea" ) >= zCURSOR_SET );
      }

      // STEP 3, add in all Reltypes from the model whose source and
      //         Target Entities both exist in the subject area
      CreateViewFromViewForTask( vER2, vTZEREMDO, null );
      CreateViewFromViewForTask( vSA2, vTZERSASO, null );
      CreateViewFromViewForTask( vSA3, vTZERSASO, null );
      if ( SetCursorFirstEntity( vTZEREMDO, "ER_RelType", null ) >= zCURSOR_SET )
      {
         do
         {
            lZKey1 = GetIntegerFromAttribute( vTZEREMDO, "ER_RelType", "ZKey" );
            if ( SetCursorFirstEntityByInteger( vTZERSASO, "ER_RelType", "ZKey", lZKey1, "SubjectArea" ) < zCURSOR_SET )
            {
               lZKey1 = GetIntegerFromAttribute( vTZEREMDO, "ER_Entity_2", "ZKey" );
               SetViewFromView( vER2, vTZEREMDO );
               SetCursorNextEntity( vER2, "ER_RelLink_2", "" );
               lZKey2 = GetIntegerFromAttribute( vER2, "ER_Entity_2", "ZKey" );
               if ( SetCursorFirstEntityByInteger( vSA2, "ER_Entity", "ZKey", lZKey1, "SubjectArea" ) >= zCURSOR_SET &&
                    SetCursorFirstEntityByInteger( vSA3, "ER_Entity", "ZKey", lZKey2, "SubjectArea" ) >= zCURSOR_SET )
               {
                  CreateMetaEntity( vSubtask, vTZERSASO, "SA_RelType", zPOS_LAST );
                  IncludeSubobjectFromSubobject( vTZERSASO, "ER_RelType", vTZEREMDO, "ER_RelType", zPOS_BEFORE );
                  SetCursorLastEntity( vSA2, "ER_RelLink", "" );
                  IncludeSubobjectFromSubobject( vSA2, "ER_RelLink", vTZEREMDO, "ER_RelLink_2", zPOS_AFTER );
                  IncludeSubobjectFromSubobject( vSA2, "ER_RelLink_Other", vER2, "ER_RelLink_2", zPOS_AFTER );
                  SetCursorLastEntity( vSA3, "ER_RelLink", "" );
                  IncludeSubobjectFromSubobject( vSA3, "ER_RelLink", vER2, "ER_RelLink_2", zPOS_AFTER );
                  IncludeSubobjectFromSubobject( vSA3, "ER_RelLink_Other", vTZEREMDO, "ER_RelLink_2", zPOS_AFTER );
               }
            }
         }  while ( SetCursorNextEntity( vTZEREMDO, "ER_RelType", "" ) >= zCURSOR_SET );
      }

      DropView( vER2 );
      DropView( vSA2 );
      DropView( vSA3 );
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZERSASO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZERSASO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZERSASO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      String   szWork;
      zVIEW    vActiveMetas = new zVIEW();
      zVIEW    vMeta = new zVIEW();
      zVIEW    vWkTZCMLPLO = new zVIEW();
      int      nRelNbr;
      boolean  bERD;

      // The SA has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?
                                                     // dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////

      // TE_RelinkDelete deletes components that reference ER_Entities that
      // no longer exist.  It does not currently do any relinking.
      oTZERSASO_SA_RelinkDelete( vTZERSASO, vSubtask );

      // For each ERD active in memory, relink the Subject Area to the model
      nRelNbr = 0;
      bERD = false;
      if ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zSOURCE_ERD_META, nRelNbr )) > 0 )
      {
         if ( isValid( vMeta ) )
         {
            // We have retrieved a view to a refer er, link up the er to the sa
            fnLinkAndSyncER_ToSA( vSubtask, vMeta, vTZERSASO );
            bERD = true;
         }
      }
      else
      if ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zREFER_ERD_META, nRelNbr )) > 0 )
      {
         if ( isValid( vMeta ) )
         {
            // We have retrieved a view to a refer er, link up the er to the sa
            fnLinkAndSyncER_ToSA( vSubtask, vMeta, vTZERSASO );
         }
      }

   /* Bypass Phase 2 if this is an activation of a SOURCE Type Meta */

      /////////////////////////////////////////////
      // Post Activate PHASE 2: Link up to all object instances in memory
      // which depend on us
      /////////////////////////////////////////////
      // Nothing to DO!

      /////////////////////////////////////////////
      // Post Activate PHASE 3: For all meta types we depend on, issue an
      // activate for all instances whose synchronization date is later than
      // our synchronization date.
      /////////////////////////////////////////////

      // For each type which has a syncronization date later than our own,
      // issue an activate for that type
      CreateViewFromViewForTask( vWkTZCMLPLO, vTZCMLPLO, null );
      ResetViewFromSubobjectTop( vWkTZCMLPLO );
      szWork = GetStringFromAttribute( vTZCMLPLO, "W_MetaDef", "LastSyncDate" );

      // FORCE THE ER/MODEL IN REGARDLESS OF THE SYNC DATE
      if ( bERD == false )
      {
         fnActivateDependentBySyncDate( vCM_Subtask, sbReturnSyncDate, vWkTZCMLPLO, szWork, zREFER_ERD_META, true );
      }

      DropView( vWkTZCMLPLO );
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZTENVRO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZTENVRO_PostCheckout( View vCM_Subtask, View vTZTENVRO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   private int oTZTENVRO_TE_RelinkDelete( View vTE, View vSubtask )
   {
      zVIEW  vERD = new zVIEW();
      zVIEW  vLPLR = new zVIEW();

      StringBuilder sbMsg = new StringBuilder();
      String szName;
      int    nDeleteFlag = 0;
      int    lZKey;
      int    nRC;

      nDeleteFlag = 0;

      RetrieveViewForMetaList( vSubtask, vLPLR, zREFER_ERD_META );
      ActivateMetaOI( vSubtask, vERD, vLPLR, zREFER_ERD_META, 0 );
      DropView( vLPLR );

      nRC = SetCursorFirstEntity( vTE, "TE_DBMS_Source", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = SetCursorFirstEntity( vTE, "TE_TablRec", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            nRC = CheckExistenceOfEntity( vTE, "ER_Entity" );
            if ( nRC == 0 )
            {
               lZKey = GetIntegerFromAttribute( vTE, "ER_Entity", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vERD, "ER_Entity", "ZKey", lZKey, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  // Relink ER_Entity
                  RelinkInstanceToInstance( vTE, "ER_Entity", vERD, "ER_Entity" );
               }
               else
               {
                  szName = GetStringFromAttribute( vTE, "ER_Entity", "Name" );
                  nRC = SetCursorFirstEntityByString( vERD, "ER_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     ExcludeEntity( vTE, "ER_Entity", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vTE, "ER_Entity", vERD, "ER_Entity", zPOS_AFTER );
                  }
                  else
                  {
                     szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "ER_Entity", "Name", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "The Entity, ", 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, ", in the ER", 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, " for TE table, ", 1, 0, 255 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "TE_TablRec", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, ", has been", 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, " deleted.", 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 255 );
                     ZeidonStringConcat( sbMsg, 1, 0, "The TE table is thus being deleted.", 1, 0, 255 );
                     MessageSend( vTE, "TE00508", "Technical Environment", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vTE, "TE_TablRec", zREPOS_NONE );
                     nDeleteFlag = 1;
                  }
               }
            }

            nRC = CheckExistenceOfEntity( vTE, "ER_RelType" );
            if ( nRC == 0 )
            {
               lZKey = GetIntegerFromAttribute( vTE, "ER_RelType", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vERD, "ER_RelType", "ZKey", lZKey, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  // Relink ER_RelType
                  RelinkInstanceToInstance( vTE, "ER_RelType", vERD, "ER_RelType" );
               }
               else
               {
                  ZeidonStringCopy( sbMsg, 1, 0, "There is no relationship match in the ERD for ", 1, 0, 255 );
                  ZeidonStringConcat( sbMsg, 1, 0, "Relalationship Table ", 1, 0, 255 );
                  szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "TE_TablRec", "Name", "", 0 );
                  ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                  ZeidonStringConcat( sbMsg, 1, 0, ".", 1, 0, 255 );
                  ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 255 );
                  ZeidonStringConcat( sbMsg, 1, 0, "The Relationship Table is thus being deleted.", 1, 0, 255 );
                  MessageSend( vTE, "TE00509", "Technical Environment", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                  DeleteEntity( vTE, "TE_TablRec", zREPOS_NONE );
                  nDeleteFlag = 1;
               }
            }

            nRC = CheckExistenceOfEntity( vTE, "TE_TablRec" );
            if ( nRC == 0 )
            {
               nRC = SetCursorFirstEntity( vTE, "TE_FieldDataRel", "" );
               while ( nRC > zCURSOR_UNCHANGED )
               {
                  nRC = CheckExistenceOfEntity( vTE, "ER_Attribute" );
                  if ( nRC == 0 )
                  {
                     lZKey = GetIntegerFromAttribute( vTE, "ER_Attribute", "ZKey" );
                     nRC = SetCursorFirstEntityByInteger( vERD, "ER_Attribute", "ZKey", lZKey, "" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        // Relink ER_Attribute - Currently handled at end
                        //RelinkInstanceToInstance( vTE, "ER_Attribute", vERD, "ER_Attribute" )
                        //RelinkInstanceToInstance( vTE, "Domain", vERD, "Domain" )
                     }
                     else
                     {
                        szName = GetStringFromAttribute( vTE, "ER_Attribute", "Name" );
                        nRC = SetCursorFirstEntityByString( vERD, "ER_Attribute", "Name", szName, "" );
                        if ( nRC >= zCURSOR_SET )
                        {
                           ExcludeEntity( vTE, "ER_Attribute", zREPOS_AFTER );
                           IncludeSubobjectFromSubobject( vTE, "ER_Attribute", vERD, "ER_Attribute", zPOS_AFTER );
                        }
                        else
                        {
                           szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "ER_Attribute", "Name", "", 0 );
                           ZeidonStringCopy( sbMsg, 1, 0, "The Attribute, ", 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, ", in the ER", 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, " for TE column, ", 1, 0, 255 );
                           szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "TE_FieldDataRel", "Name", "", 0 );
                           ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, ", has been", 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, " deleted.", 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 255 );
                           ZeidonStringConcat( sbMsg, 1, 0, "The TE column is thus being deleted.", 1, 0, 255 );
                           MessageSend( vTE, "TE00510", "Technical Environment", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                           DeleteEntity( vTE, "TE_FieldDataRel", zREPOS_NONE );
                           nDeleteFlag = 1;
                        }
                     }
                  }

                  nRC = CheckExistenceOfEntity( vTE, "ER_RelLink" );
                  if ( nRC == 0 )
                  {
                     lZKey = GetIntegerFromAttribute( vTE, "ER_RelLink", "ZKey" );
                     nRC = SetCursorFirstEntityByInteger( vERD, "ER_RelLink_2", "ZKey", lZKey, "EntpER_Model" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        // Relink ER_RelLink - Currently handled at end
                        //RelinkInstanceToInstance( vTE, "ER_RelLink", vERD, "ER_RelLink_2" )
                        //RelinkInstanceToInstance( vTE, "ER_RelType_O", vERD, "ER_RelType" )
                        //RelinkInstanceToInstance( vTE, "ER_EntityRelLink", vERD, "ER_Entity_2" )
                     }
                     else
                     {
                        ZeidonStringCopy( sbMsg, 1, 0, "There is no relationship match in the ERD for ", 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, "Foreign Key ", 1, 0, 255 );
                        szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "TE_FieldDataRel", "Name", "", 0 );
                        ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, ".", 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, "The Foreign Key is thus being deleted.", 1, 0, 255 );
                        MessageSend( vTE, "TE00511", "Technical Environment", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                        DeleteEntity( vTE, "TE_FieldDataRel", zREPOS_NONE );
                        nDeleteFlag = 1;
                     }
                  }

                  nRC = SetCursorNextEntity( vTE, "TE_FieldDataRel", "" );
               }

               nRC = SetCursorFirstEntity( vTE, "TE_TablRecKey", "" );
               while ( nRC > zCURSOR_UNCHANGED )
               {
                  nRC = CheckExistenceOfEntity( vTE, "ER_EntIdentifier" );
                  if ( nRC == 0 )
                  {
                     lZKey = GetIntegerFromAttribute( vTE, "ER_EntIdentifier", "ZKey" );
                     nRC = SetCursorFirstEntityByInteger( vERD, "ER_EntIdentifier", "ZKey", lZKey, "" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        // Relink ER_EntIdentifier
                        RelinkInstanceToInstance( vTE, "ER_EntIdentifier", vERD, "ER_EntIdentifier" );
                     }
                     else
                     {
                        szName = GetVariableFromAttribute( 0, 'S', 33, vTE, "ER_EntIdentifier", "Name", "", 0 );
                        ZeidonStringCopy( sbMsg, 1, 0, "The Identifier, ", 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, ", in the ER", 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, ", has been deleted.", 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 255 );
                        ZeidonStringConcat( sbMsg, 1, 0, "The corresponding key field in the TE is thus being deleted.", 1, 0, 255 );
                        MessageSend( vTE, "TE00512", "Technical Environment", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                        DeleteEntity( vTE, "TE_TablRecKey", zREPOS_NONE );
                        nDeleteFlag = 1;
                     }
                  }

                  nRC = SetCursorNextEntity( vTE, "TE_TablRecKey", "" );
               }
            }

            nRC = SetCursorNextEntity( vTE, "TE_TablRec", "" );
         }

         nRC = SetCursorNextEntity( vTE, "TE_DBMS_Source", "" );
      }

      // Now relink the ER_Attribute and ER_RelLink subobjects.  They have to be handled with the RelinkAllSubobjectsForOI to
      // correctly reset the include flags.  In the future a version of RelinkInstanceToInstance may be created that can also
      // reset the include flag.  If so, the following will be deleted.

      RelinkAllSubobjectsForOI( vTE, "ER_Attribute", vERD.getView(), "ER_Attribute" );
      RelinkAllSubobjectsForOI( vTE, "ER_RelLink", vERD.getView(), "ER_RelLink" );

      DropMetaOI( vSubtask, vERD );

      return nDeleteFlag;
   // END
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZTENVRO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZTENVRO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZTENVRO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW    vActiveMetas = new zVIEW();

      // This starts the standard processing for all Metas
      GetViewByName( vActiveMetas, "OpenCM_Metas",
                     vCM_Subtask, zLEVEL_SUBTASK );

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory which we depend on
      /////////////////////////////////////////////
      // For each ERD active in memory, relink the TE to the ER_MODEL

      oTZTENVRO_TE_RelinkDelete( vTZTENVRO, vSubtask );
      return 0;
/*
      String   szWork;
      zVIEW    vMeta = new zVIEW();
      zVIEW    vWkTZCMLPLO = new zVIEW();
      int      nRelNbr;

      nRelNbr = 0;
      while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas,
                                                zREFER_ERD_META, nRelNbr )) > 0 )
      {
         if ( isValid( vMeta ) )
         {
            // We have retrieved a view to an active ERD, link up the ER_Model to the TE
            RelinkAllSubobjectsForOI( vTZTENVRO, "ER_Attribute", vMeta.getView(), "ER_Attribute" );
            RelinkAllSubobjectsForOI( vTZTENVRO, "ER_RelLink", vMeta.getView(), "ER_RelLink" );
            RelinkAllSubobjectsForOI( vTZTENVRO, "ER_Entity", vMeta.getView(), "ER_Entity" );
            RelinkAllSubobjectsForOI( vTZTENVRO, "ER_RelType", vMeta.getView(), "ER_RelType" );
            RelinkAllSubobjectsForOI( vTZTENVRO, "ER_EntIdentifier", vMeta.getView(), "ER_EntIdentifier" );
         }
      }

      // Bypass Phase 2 if this is an activation of a SOURCE Type Meta
      if ( nPhaseCtl == 1 )
      {
         /////////////////////////////////////////////
         // Post Activate PHASE 2: Link up to all object instances in memory which depend on us
         /////////////////////////////////////////////
         // For each REFER LOD active in memory, relink the TE to the LOD
         nRelNbr = 0;
         while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask,  vActiveMetas, zREFER_LOD_META, nRelNbr )) > 0 )
         {
            if ( isValid( vMeta ) )
            {
               // We have retrieved a view to an active LOD, link up the LOD to the TE
               RelinkAllSubobjectsForOI( vMeta, "TE_DB_Environ", vTZTENVRO, "TE_DB_Environ" );
            }
         }

         // For each SOURCE LOD active in memory, relink the TE to the LOD
         nRelNbr = 0;
         while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zSOURCE_LOD_META, nRelNbr )) > 0 )
         {
            if ( isValid( vMeta ) )
            {
               // We have retrieved a view to an active LOD, link up the LOD to the TE.
               RelinkAllSubobjectsForOI( vMeta, "TE_DB_Environ", vTZTENVRO, "TE_DB_Environ" );
            }
         }
      }

      /////////////////////////////////////////////
      // Post Activate PHASE 3: For all meta types we depend on, issue an
      // activate for all instances whose synchronization date is later than
      // our synchronization date.
      /////////////////////////////////////////////

      // For each type which has a syncronization date later than our own,
      // issue an activate for that type
      CreateViewFromViewForTask( vWkTZCMLPLO, vTZCMLPLO, null );
      while ( ResetViewFromSubobject( vWkTZCMLPLO ) == 0 )
      {
         ;
      }

      szWork = GetStringFromAttribute( vTZCMLPLO, "W_MetaDef", "LastSyncDate" );
      fnActivateDependentBySyncDate( vCM_Subtask, sbReturnSyncDate, vWkTZCMLPLO, szWork, zREFER_ERD_META, 1 );

      DropView( vWkTZCMLPLO );
      // Return success
      return 0;
   */
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZZOLODO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZZOLODO_PostCheckout( View vCM_Subtask, View vTZZOLODO, View vTZCMLPLO, boolean bRepository )
   {
      // After a lod has been checked out, we need to instantiate
      // the right 'flat' side of the lod from the left 'recursive'
      // side.  We will only do this, however, if it doesn't already exist.
      if ( CheckExistenceOfEntity( vTZZOLODO, "LOD_Entity" ) < zCURSOR_SET )
      {
         fnTZZOLODO_BuildFlatFromRec( vTZZOLODO );
      }

      return 0;
   }

   private int oTZZOLODO_fnRelinkWorkAttrib( View vLOD, View DomainLPLR, View vSubtask )
   {
      zVIEW  DomainLPLR2 = new zVIEW();
      zVIEW  vDomain = new zVIEW();
      String szName;
      int    nRC;
      int    lZKey;

      // Relink Domains for attributes under a work entity.
      nRC = CheckExistenceOfEntity( vLOD, "Domain" );
      if ( nRC == 0 )
      {
         lZKey = GetIntegerFromAttribute( vLOD, "Domain", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( DomainLPLR, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
         if ( nRC == zCURSOR_SET )
         {
            CreateViewFromViewForTask( DomainLPLR2, DomainLPLR, null );
            ActivateMetaOI( vSubtask, vDomain, DomainLPLR2, zREFER_DOMAIN_META, 0 );
            DropView( DomainLPLR2 );
            RelinkInstanceToInstance( vLOD, "Domain", vDomain, "Domain" );
            DropMetaOI( vSubtask, vDomain );
         }
         else
         {
            szName = GetStringFromAttribute( vLOD, "Domain", "Name" );
            nRC = SetCursorFirstEntityByString( DomainLPLR, "W_MetaDef", "Name", szName, "" );
            if ( nRC < zCURSOR_SET )
            {
               nRC = SetCursorFirstEntityByString( DomainLPLR, "W_MetaDef", "Name", "Text", "" );
            }

            if ( nRC == zCURSOR_SET )
            {
               CreateViewFromViewForTask( DomainLPLR2, DomainLPLR, null );
               ActivateMetaOI( vSubtask, vDomain, DomainLPLR2, zREFER_DOMAIN_META, 0 );
               DropView( DomainLPLR2 );
               ExcludeEntity( vLOD, "Domain", zREPOS_AFTER );
               IncludeSubobjectFromSubobject( vLOD, "Domain", vDomain, "Domain", zPOS_AFTER );
               DropMetaOI( vSubtask, vDomain );
            }
            else
            {
               // If the Domain isn't in LPLR, it must have been deleted.  Thereby exclude
               // the Domain entity.  We'll leave the LOD_Attribute without any Domain.
               MessageSend( vSubtask, "ZO00412", "LOD Relink", "Excluding Domain", zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               ExcludeEntity( vLOD, "Domain", zREPOS_AFTER );
            }
         }
      }

      return 0;
   }

   private int oTZZOLODO_fnRelinkAttributes( View vSubtask, View vLOD, View vERD, View DomainLPLR )
   {
      int     DeleteFlag = 0;
      int     nRemapFlag = 0;
      StringBuilder sbMsg = new StringBuilder();
      String  szName;
      int     lZKey;
      int     nRC;

      nRC = SetCursorFirstEntity( vLOD, "LOD_Attribute", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = CheckExistenceOfEntity( vLOD, "ER_Attribute" );
         if ( nRC == 0 )
         {
            lZKey = GetIntegerFromAttribute( vLOD, "ER_Attribute", "ZKey" );
            nRC = SetCursorFirstEntityByInteger( vERD, "ER_Attribute", "ZKey", lZKey, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( nRC >= zCURSOR_SET )
            {
            // RelinkInstanceToInstance( vLOD, "ER_Attribute", vERD, "ER_Attribute" )
            // RelinkInstanceToInstance( vLOD, "Domain", vERD, "Domain" )
            }
            else
            {
               if ( CompareAttributeToString( vLOD, "LOD_Attribute", "Work", "Y" ) == 0 )
               {
                  // Relink Domains for work attributes.
                  oTZZOLODO_fnRelinkWorkAttrib( vLOD, DomainLPLR, vSubtask );
               }
               else
               {
                  // Since there was no match on ZKey, try to find a match on ER Entity
                  // name and ER Attribute name, in case the Attribute had been deleted
                  // and recreated.  In this case, re-include the Attribute.
                  szName = GetStringFromAttribute( vLOD, "ER_Entity", "Name" );
                  nRC = SetCursorFirstEntityByString( vERD, "ER_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     szName = GetStringFromAttribute( vLOD, "ER_Attribute", "Name" );
                     nRC = SetCursorFirstEntityByString( vERD, "ER_Attribute", "Name", szName, "" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        nRemapFlag = 0;
                        ExcludeEntity( vLOD, "ER_Attribute", zREPOS_AFTER );
                        IncludeSubobjectFromSubobject( vLOD, "ER_Attribute", vERD, "ER_Attribute", zPOS_AFTER );
                     }
                     else
                     {
                        nRemapFlag = -1;
                     }
                  }
                  else
                  {
                     nRemapFlag = -1;
                  }

                  if ( nRemapFlag == -1 )
                  {
                     szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "LOD_Entity", "Name", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting LOD Entity/Attribute: ", 1, 0, 201 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
                     ZeidonStringConcat( sbMsg, 1, 0, "/", 1, 0, 201 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "ER_Attribute", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
                     MessageSend( vSubtask, "ZO00410", "LOD Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vLOD, "LOD_Attribute", zREPOS_NONE );
                     DeleteFlag = 1;
                  }
               }
            }
         }

         nRC = SetCursorNextEntity( vLOD, "LOD_Attribute", "" );
      }

      return 0;
   }

   private int oTZZOLODO_fnLocateParentName( View vRecursLOD, int RelationshipZKey, int ChildEntityZKey, StringBuilder sbReturnedParentName )
   {
      zVIEW  vParentLOD = new zVIEW();
      int    nRC;

      // Recursive routine to locate the parent entity name for a particular RelationshipName and ChildEntityName.
      // Keep searching recursively until there is a match on entity name and relationship name. When a match is found,
      // get the entity name of the parent and return.
      nRC = SetCursorFirstEntity( vRecursLOD, "LOD_EntityParent", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         // Evaluate each LOD_EntityParent
         nRC = CheckExistenceOfEntity( vRecursLOD, "ER_RelLinkRec" );
         if ( nRC == 0 )
         {
            if ( CompareAttributeToInteger( vRecursLOD, "LOD_EntityParent", "ZKey", ChildEntityZKey ) == 0 &&
                 CompareAttributeToInteger( vRecursLOD, "ER_RelLinkRec", "ZKey", RelationshipZKey ) == 0 )
            {
               CreateViewFromViewForTask( vParentLOD, vRecursLOD, null );
               ResetViewFromSubobject( vParentLOD );
               GetVariableFromAttribute( sbReturnedParentName, 0, 'S', 33, vParentLOD, "ER_EntityRec", "Name", "", 0 );
               DropView( vParentLOD );
               return 0;
            }
         }

         // Process LOD_EntityChild subobjects
         nRC = CheckExistenceOfEntity( vRecursLOD, "LOD_EntityChild" );
         if ( nRC == 0 )
         {
            SetViewToSubobject( vRecursLOD, "LOD_EntityChild" );
            oTZZOLODO_fnLocateParentName( vRecursLOD, RelationshipZKey, ChildEntityZKey, sbReturnedParentName );
            ResetViewFromSubobject( vRecursLOD );
            if ( ZeidonStringCompare( sbReturnedParentName, 1, 0, "", 1, 0, 33 ) != 0 )
            {
               return 0;
            }
         }

         nRC = SetCursorNextEntity( vRecursLOD, "LOD_EntityParent", "" );
      }

      return 0;
   }

   private int oTZZOLODO_fnRelinkRelationships( View vSubtask, View vLOD, View vERD )
   {
      StringBuilder sbReturnedParentName = new StringBuilder();
      int    RelationshipZKey = 0;
      int    ChildEntityZKey = 0;
      StringBuilder sbMsg = new StringBuilder();
      String szName;
      int    lZKey;
      int    nRC;

      nRC = SetCursorFirstEntity( vLOD, "ER_RelLink", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         //:SET CURSOR FIRST vERD.ER_RelLink_Other WITHIN vERD.ER_Entity WHERE
         //:    vERD.ER_RelLink_Other.ZKey = vLOD.ER_RelLink.ZKey
         lZKey = GetIntegerFromAttribute( vLOD, "ER_RelLink", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( vERD, "ER_RelLink_Other", "ZKey", lZKey, "ER_Entity" );
         //:IF RESULT < zCURSOR_SET
         if ( nRC < zCURSOR_SET )
         {
            // There is no match on ZKey, so try to match on Relationship name.
            // If a match, re-include. If not, give error message.
            // This is going to require locating the parent entity name by looking
            // through the recursive subobject structure.
            RelationshipZKey = GetIntegerFromAttribute( vLOD, "ER_RelLink", "ZKey" );
            ChildEntityZKey = GetIntegerFromAttribute( vLOD, "LOD_Entity", "ZKey" );
            SetViewToSubobject( vLOD, "LOD_EntityChild" );
            ZeidonStringCopy( sbReturnedParentName, 1, 0, "", 1, 0, 33 );
            oTZZOLODO_fnLocateParentName( vLOD, RelationshipZKey, ChildEntityZKey, sbReturnedParentName );
            ResetViewFromSubobject( vLOD );
            nRC = SetCursorFirstEntity( vERD, "ER_RelLink_Other", "ER_Entity" );
            if ( nRC > zCURSOR_UNCHANGED )
            {
               while ( nRC > zCURSOR_UNCHANGED &&
                      (CompareAttributeToAttribute( vERD, "ER_RelLink_Other", "Name", vLOD, "ER_RelLink", "Name" ) != 0 ||
                       CompareAttributeToString( vERD, "ER_Entity_Other", "Name", sbReturnedParentName.toString() ) != 0) )
               {
                  nRC = SetCursorNextEntity( vERD, "ER_RelLink_Other", "ER_Entity" );
               }
            }

            if ( nRC >= zCURSOR_SET )
            {
               lZKey = GetIntegerFromAttribute( vERD, "ER_RelLink_Other", "ZKey" );
               SetCursorFirstEntityByInteger( vERD, "ER_RelLink_2", "ZKey", lZKey, "EntpER_Model" );
               ExcludeEntity( vLOD, "ER_RelLink", zREPOS_AFTER );
               IncludeSubobjectFromSubobject( vLOD, "ER_RelLink", vERD, "ER_RelLink_2", zPOS_AFTER );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "LOD", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "There is no entity/relationship name match in the ERD for LOD ", 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, ", with relationship: ", 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, sbReturnedParentName, 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, "  ", 1, 0, 201 );
               szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "ER_RelLink", "Name", "", 0 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, "  ", 1, 0, 201 );
               szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "ER_Entity", "Name", "", 0 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, NEW_LINE, 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, "The LOD Entity will be deleted.", 1, 0, 201 );
               MessageSend( vSubtask, "ZO00410", "LOD Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               DeleteEntity( vLOD, "LOD_Entity", zPOS_NEXT );
            }
         }

         nRC = SetCursorNextEntity( vLOD, "ER_RelLink", "" );
      }

      return 0;
   }

   private int oTZZOLODO_LOD_RelinkDelete( View vLOD, View vSubtask )
   {
      zVIEW   vERD = new zVIEW();
      zVIEW   DomainLPLR = new zVIEW();
      zVIEW   vLPLR = new zVIEW();
      zVIEW   vDTE = new zVIEW();
      int     DeleteFlag;
      int     nERD_Flag;
      int     nDTE_Flag;
      StringBuilder sbMsg = new StringBuilder();
      String  szSourceName;
      String  szName;
      int     lZKey;
      int     nRC;

      DeleteFlag = 0;
      RetrieveViewForMetaList( vSubtask, vLPLR, zREFER_ERD_META );
      SetNameForView( vLPLR, "vERD_LPLR", 0, zLEVEL_TASK );

      // Check if there is an ERD, because we will skip certain relinking if there is not.
      nRC = CheckExistenceOfEntity( vLPLR, "W_MetaDef" );
      if ( nRC == 0 )
      {
         nERD_Flag = ActivateMetaOI( vSubtask, vERD, vLPLR, zREFER_ERD_META, 0 );
      }
      else
      {
         nERD_Flag = -1;
      }

      DropView( vLPLR );

      // Note that we are not relinking the vLOD.TE_DB_Environ entity, as this
      // is only necessary at XOD build, where it is already activated.

      // Relink subobjects against ERD.  This code should go away when a RelinkInstanceToInstance
      // operation is created that can reset include flags.
      if ( nERD_Flag >= zCURSOR_SET )
      {
         RelinkAllSubobjectsForOI( vLOD, "ER_RelLinkRec", vERD.getView(), "ER_RelLink_2" );
         RelinkAllSubobjectsForOI( vLOD, "ER_AttributeRec", vERD.getView(), "ER_Attribute" );
      }

      RetrieveViewForMetaList( vSubtask, DomainLPLR, zREFER_DOMAIN_META );
      SetNameForView( DomainLPLR, "DomainLPLR", 0, zLEVEL_TASK );

      // Relink Each LOD Entity and subordinate entities
      nRC = SetCursorFirstEntity( vLOD, "LOD_Entity", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = CheckExistenceOfEntity( vLOD, "ER_Entity" );
         if ( nRC == 0 && nERD_Flag >= zCURSOR_SET )
         {
            // The following relink is only done if there is an ERD
            lZKey = GetIntegerFromAttribute( vLOD, "ER_Entity", "ZKey" );
            nRC = SetCursorFirstEntityByInteger( vERD, "ER_Entity", "ZKey", lZKey, "" );
            if ( nRC >= zCURSOR_SET )
            {
               RelinkInstanceToInstance( vLOD, "ER_Entity", vERD, "ER_Entity" );

               // Attributes
               oTZZOLODO_fnRelinkAttributes( vSubtask, vLOD, vERD, DomainLPLR );

               // Relationship
               oTZZOLODO_fnRelinkRelationships( vSubtask, vLOD, vERD );
            }
            else
            {
               // No match on ER_Entity ZKey
               if ( CompareAttributeToString( vLOD, "LOD_Entity", "Work", "Y" ) == 0 )
               {
                  nRC = SetCursorFirstEntity( vLOD, "LOD_Attribute", "" );
                  while ( nRC > zCURSOR_UNCHANGED )
                  {
                     // Relink Domains for attributes under a work entity.
                     oTZZOLODO_fnRelinkWorkAttrib( vLOD, DomainLPLR, vSubtask );
                     nRC = SetCursorNextEntity( vLOD, "LOD_Attribute", "" );
                  }
               }
               else
               {
                  // If we are here, this must be an ER type LOD_Attribute with
                  // no match on ER_Attribute ZKey.
                  // Since there is no match on ZKey, try to find a match on
                  // ER_Attribute name, in case the Attribute had been deleted
                  // and recreated.
                  szName = GetStringFromAttribute( vLOD, "ER_Entity", "Name" );
                  nRC = SetCursorFirstEntityByString( vERD, "ER_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     // First reinclude the ER_Entity
                     ExcludeEntity( vLOD, "ER_Entity", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vLOD, "ER_Entity", vERD, "ER_Entity", zPOS_AFTER );

                     // Attributes.
                     oTZZOLODO_fnRelinkAttributes( vSubtask, vLOD, vERD, DomainLPLR );

                     // Relationships
                     oTZZOLODO_fnRelinkRelationships( vSubtask, vLOD, vERD );
                  }
                  else
                  {
                     szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "LOD_Entity", "Name", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting LOD Entity: ", 1, 0, 201 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
                     MessageSend( vSubtask, "ZO00413", "LOD Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vLOD, "LOD_Entity", zREPOS_NONE );
                     DeleteFlag = 1;
                  }
               }
            }
         }
         else
         {
            if ( CompareAttributeToString( vLOD, "LOD_Entity", "Work", "Y" ) == 0 )
            {
               nRC = SetCursorFirstEntity( vLOD, "LOD_Attribute", "" );
               while ( nRC > zCURSOR_UNCHANGED )
               {
                  // Relink Domains for attributes under a work entity.
                  oTZZOLODO_fnRelinkWorkAttrib( vLOD, DomainLPLR, vSubtask );
                  nRC = SetCursorNextEntity( vLOD, "LOD_Attribute", "" );
               }
            }
            else
            {
               // Since this is not a work entity, it is an error to have a LOD_Entity without a
               // corresponding ER_Entity.  If this happens, there is some kind of Zeidon error.
               szName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "LOD_Entity", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting LOD Entity for logic error.\nEntity: ", 1, 0, 201 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 201 );
               MessageSend( vSubtask, "ZO00414", "LOD Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               DeleteEntity( vLOD, "LOD_Entity", zREPOS_NONE );
               DeleteFlag = 1;
            }
         }

         nRC = SetCursorNextEntity( vLOD, "LOD_Entity", "" );
      }

      DropView( DomainLPLR );
      if ( nERD_Flag >= zCURSOR_SET )
      {
         DropMetaOI( vSubtask, vERD );
      }

      //BL, 2000.01.03 Relink TE
      RetrieveViewForMetaList( vSubtask, vLPLR, zREFER_DTE_META );
      SetNameForView( vLPLR, "vDTE_LPLR", 0, zLEVEL_TASK );

      // Check if there is an DTE, because we will skip certain relinking if
      // there is not.
      nRC = CheckExistenceOfEntity( vLPLR, "W_MetaDef" );
      if ( nRC == 0 )
      {
         nDTE_Flag = ActivateMetaOI( vSubtask, vDTE, vLPLR, zREFER_DTE_META, 0 );
      }
      else
      {
         nDTE_Flag = -1;
      }

      DropView( vLPLR );

      // Relink subobjects against DTE.  This code should go away when a RelinkInstanceToInstance
      // operation is created that can reset include flags.
      if ( nDTE_Flag >= zCURSOR_SET )
      {
         // The relink below may cause the vLOD.POD.TE_SourceZKey value to become invalid.
         // This could occur because a DBMS_Source entry was deleted and recreated. We will
         // thus save the DBMS_Source name for the vLOD.POD.TE_SourceZKey value to alter the
         // value as necessary.
         nRC = CheckExistenceOfEntity( vLOD, "POD" );
         if ( nRC == 0 )
         {
            lZKey = GetIntegerFromAttribute( vLOD, "POD", "TE_SourceZKey" );
            SetCursorFirstEntityByInteger( vLOD, "TE_DBMS_Source", "ZKey", lZKey, "" );
            szSourceName = GetVariableFromAttribute( 0, 'S', 33, vLOD, "TE_DBMS_Source", "Name", "", 0 );
            RelinkAllSubobjectsForOI( vLOD, "TE_DB_Environ", vDTE.getView(), "TE_DB_Environ" );
            lZKey = GetIntegerFromAttribute( vLOD, "POD", "TE_SourceZKey" );
            nRC = SetCursorFirstEntityByInteger( vLOD, "TE_DBMS_Source", "ZKey", lZKey, "" );
            if ( nRC < zCURSOR_SET )
            {
               nRC = SetCursorFirstEntityByString( vLOD, "TE_DBMS_Source", "Name", szSourceName, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  SetAttributeFromAttribute( vLOD, "POD", "TE_SourceZKey", vLOD, "TE_DBMS_Source", "ZKey" );
               }
            }
         }
      }

      if ( nDTE_Flag >= zCURSOR_SET )
      {
         DropMetaOI( vSubtask, vDTE );
      }

      return 0;
   // END
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZZOLODO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZZOLODO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZZOLODO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW    vActiveMetas = new zVIEW();

      // The LOD has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?   dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////
      // For each ERD active in memory, relink the ER_MODEL to the LOD

      oTZZOLODO_LOD_RelinkDelete( vTZZOLODO, vSubtask );
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZWDVORO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZWDVORO_PostCheckout( zVIEW vCM_Subtask, View vTZWDVORO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZWDVORO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZWDVORO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZWDVORO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      // This function verifies that the ZKey of the LOD hasn't changed. If
      // it has, then the LOD was deleted and recreated and we need to
      // re-include the LOD within the VOR.

      // This routine used to relink with the corresponding LOD, but this code
      // was removed for performance reasons. The relink wasn't necessary
      // because the only information in the LOD was the LOD Name, which could
      // not be changed. (Relink code added back by DonC on 1999.02.03 because
      // we got incorrect update flags without it.

      zVIEW  vLOD = new zVIEW();
      zVIEW  vTempLPLR = new zVIEW();
      int nRC;
      String  szLOD_Name;

      CreateViewFromViewForTask( vTempLPLR, vTZCMLPLO, null );
      SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaType", "Type", zREFER_LOD_META, "" );
      nRC = SetCursorFirstEntityByAttr( vTempLPLR, "W_MetaDef", "CPLR_ZKey", vTZWDVORO, "LOD", "ZKey", "" );
      if ( nRC < zCURSOR_SET )
      {
         // The LOD in the VOR was not found in the LPLR by ZKey, so re-include.
         szLOD_Name = GetStringFromAttribute( vTZWDVORO, "LOD", "Name" );
         nRC = ActivateMetaOI_ByName( vSubtask, vLOD, null, zREFER_LOD_META, zSINGLE, szLOD_Name, 0 );
         if ( nRC >= 0 )
         {
            ExcludeEntity( vTZWDVORO, "LOD", zREPOS_NONE );
            IncludeSubobjectFromSubobject( vTZWDVORO, "LOD", vLOD, "LOD", zPOS_AFTER );
            DropMetaOI( vSubtask, vLOD );
         }
      }
      else
      {
         // Activate the LOD and relink it.
         szLOD_Name = GetStringFromAttribute( vTZWDVORO, "LOD", "Name" );
         nRC = ActivateMetaOI_ByName( vSubtask, vLOD, null, zREFER_LOD_META, zSINGLE, szLOD_Name, 0 );
         if ( nRC >= 0 )
         {
            RelinkInstanceToInstance( vTZWDVORO, "LOD", vLOD, "LOD" );
            DropMetaOI( vSubtask, vLOD );
         }
      }

      DropView( vTempLPLR );

      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZPESRCO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZPESRCO_PostCheckout( View vCM_Subtask, View vTZPESRCO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZPESRCO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZPESRCO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZPESRCO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW vActiveMetas = new zVIEW();
      zVIEW vMeta = new zVIEW();
      int   nRelNbr;

      // The Penv has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?   dks ... 2004.09.16
                                                   

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////

      // Bypass Phase 2 if this is an activation of a SOURCE Type Meta
      if ( nPhaseCtl == 1 )
      {
      /////////////////////////////////////////////
      // Post Activate PHASE 2: Link up to all object instances in memory
      // which depend on us
      /////////////////////////////////////////////
      // For each REFER Dialog active in memory, relink the PE to the Dialog
         nRelNbr = 0;
         while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zREFER_DIALOG_META, nRelNbr )) > 0 )
         {
            if ( isValid( vMeta ) )
            {
               // We have retrieved a view to an active DIALOG, link up the
               // Dialog to the PE
               RelinkAllSubobjectsForOI( vMeta, "ControlDef", vTZPESRCO, "ControlDef" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlColorDef", vTZPESRCO, "CtrlColorDef" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlColor", vTZPESRCO, "Color" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlFontDef", vTZPESRCO, "CtrlFontDef" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlFont", vTZPESRCO, "Font" );
               RelinkAllSubobjectsForOI( vMeta, "WndStyle", vTZPESRCO, "WindowStyle" );
            // RelinkAllSubobjectsForOI( vMeta, "WndColorDef", vTZPESRCO, "WndColorDef" );
            // RelinkAllSubobjectsForOI( vMeta, "WndColor", vTZPESRCO, "Color" );
            // RelinkAllSubobjectsForOI( vMeta, "WndFontDef", vTZPESRCO, "WndFontDef" );
            // RelinkAllSubobjectsForOI( vMeta, "WndFont", vTZPESRCO, "Font" );
            }
         }

      // For each SOURCE Dialog active in memory, relink the PE to the Dialog
         nRelNbr = 0;
         while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zSOURCE_DIALOG_META, nRelNbr )) > 0 )
         {
            if ( isValid( vMeta ) )
            {
               // We have retrieved a view to an active DIALOG, link up the
               // Dialog to the PE
               RelinkAllSubobjectsForOI( vMeta, "ControlDef", vTZPESRCO, "ControlDef" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlColorDef", vTZPESRCO, "CtrlColorDef" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlColor", vTZPESRCO, "Color" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlFontDef", vTZPESRCO, "CtrlFontDef" );
            // RelinkAllSubobjectsForOI( vMeta, "CtrlFont", vTZPESRCO, "Font" );
               RelinkAllSubobjectsForOI( vMeta, "WndStyle", vTZPESRCO, "WindowStyle" );
            // RelinkAllSubobjectsForOI( vMeta, "WndColorDef", vTZPESRCO, "WndColorDef" );
            // RelinkAllSubobjectsForOI( vMeta, "WndColor", vTZPESRCO, "Color" );
            // RelinkAllSubobjectsForOI( vMeta, "WndFontDef", vTZPESRCO, "WndFontDef" );
            // RelinkAllSubobjectsForOI( vMeta, "WndFont", vTZPESRCO, "Font" );
            }
         }

         // For each REFER UI Spec active in memory, relink the PE to the UI Spec
         nRelNbr = 0;
         while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zREFER_UIS_META, nRelNbr )) > 0 )
         {
            if ( isValid( vMeta ) )
            {
               // We have retrieved a view to an active UI Spec, link up the UI Spec to the PE.
               RelinkAllSubobjectsForOI( vMeta, "WndStyle", vTZPESRCO, "WindowStyle" );
               RelinkAllSubobjectsForOI( vMeta, "DfltWndStyle", vTZPESRCO, "WindowStyle" );
            }
         }

         // For each SOURCE UI Spec active in memory, relink the PE to the UI Spec
         nRelNbr = 0;
         while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas, zSOURCE_UIS_META, nRelNbr )) > 0 )
         {
            if ( isValid( vMeta ) )
            {
               // We have retrieved a view to an active UI Spec, link up the UI Spec to the PE.
               RelinkAllSubobjectsForOI( vMeta, "WndStyle", vTZPESRCO, "WindowStyle" );
               RelinkAllSubobjectsForOI( vMeta, "DfltWndStyle", vTZPESRCO, "WindowStyle" );
            }
         }
      }

      /////////////////////////////////////////////
      // Post Activate PHASE 3: For all meta types we depend on, issue an
      // activate for all instances used by this PE
      /////////////////////////////////////////////
      // Phase III - Nothing to do...

      return 0;
   }

   private int oTZWDLGSO_ControlRelinkDelete( View vDialog, View vControl, zVIEW vLastLOD, MutableInt miLastViewZKey, View vLOD_LPLR, View vSubtask )
   {
      zVIEW     vDomain = new zVIEW();
      zVIEW     vReport = new zVIEW();
      zVIEW     vPE = new zVIEW();
      zVIEW     PE_List = new zVIEW();
      StringBuilder sbMsg = new StringBuilder();
      StringBuilder sbWindowReportName = new StringBuilder();
      String    szContinueParseGen;
      String    szName;
      String    szTag;
      int       nRemapFlag = 0;
      int       lZKey;
      int       lKey;
      int       nRC;

      //  Get View to Presentation Environment.
      nRC = GetViewByName( vPE, "TZPESRCO", vSubtask, zLEVEL_TASK );
      if ( nRC < 0 )
      {
         RetrieveViewForMetaList( vSubtask, PE_List, zREFER_PENV_META );
         nRC = LoadZeidonPPE( vSubtask, vPE, zREFER_PENV_META, PE_List, "Configuration Management", "" );
         DropView( PE_List );
         if ( nRC < 0 )
         {
            return( nRC );
         }

         SetNameForView( vPE, "TZPESRCO", vSubtask, zLEVEL_TASK );
      }

      // Process each subcontrol.
      nRC = SetCursorFirstEntity( vControl, "CtrlCtrl", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         SetViewToSubobject( vControl, "CtrlCtrl" );
         oTZWDLGSO_ControlRelinkDelete( vDialog, vControl, vLastLOD, miLastViewZKey, vLOD_LPLR, vSubtask );
         ResetViewFromSubobject( vControl );
         nRC = SetCursorNextEntity( vControl, "CtrlCtrl", "" );
      }

      // Set szWindowReportName depending on whether or not view "Report" exists.
      // GET VIEW vReport NAMED "vReport"
      GetViewByName( vReport, "vReport", vSubtask, zLEVEL_TASK );
      if ( isValid( vReport ) )
      {
         szTag = GetVariableFromAttribute( 0, 'S', 33, vReport, "Group", "Tag", "", 0 );
         ZeidonStringCopy( sbWindowReportName, 1, 0, "Group: ", 1, 0, 65 );
         ZeidonStringConcat( sbWindowReportName, 1, 0, szTag, 1, 0, 65 );
      }
      else
      {
         szTag = GetVariableFromAttribute( 0, 'S', 33, vDialog, "Window", "Tag", "", 0 );
         ZeidonStringCopy( sbWindowReportName, 1, 0, "Window: ", 1, 0, 65 );
         ZeidonStringConcat( sbWindowReportName, 1, 0, szTag, 1, 0, 65 );
      }

      //BL, 2000.02.03 new search pfad
      lKey = GetIntegerFromAttribute( vControl, "ControlDef", "Key" );
      nRC = SetCursorFirstEntityByInteger( vPE, "ControlDef", "Key", lKey, "" );
      if ( nRC < zCURSOR_SET )
      {
         szTag = GetStringFromAttribute( vControl, "ControlDef", "Tag" );
         nRC = SetCursorFirstEntityByString( vPE, "ControlDef", "Tag", szTag, "" );
         if ( nRC < zCURSOR_SET )
         {
            lZKey = GetIntegerFromAttribute( vControl, "ControlDef", "ZKey" );
            nRC = SetCursorFirstEntityByInteger( vPE, "ControlDef", "ZKey", lZKey, "" );
         }
      }

      if ( nRC >= zCURSOR_SET )
      {
         ExcludeEntity( vControl, "ControlDef", zREPOS_AFTER );
         IncludeSubobjectFromSubobject( vControl, "ControlDef", vPE, "ControlDef", zPOS_AFTER );
      }
      else
      {
         szTag = GetVariableFromAttribute( 0, 'S', 33, vControl, "ControlDef", "Tag", "", 0 );
         ZeidonStringCopy( sbMsg, 1, 0, "ControlDef doesn't exist: ", 1, 0, 256 );
         ZeidonStringConcat( sbMsg, 1, 0, szTag, 1, 0, 256 );
         MessageSend( vSubtask, "WD00204", "ControlRelinkDelete", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
      }

      nRC = SetCursorFirstEntity( vControl, "CtrlMap", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = CheckExistenceOfEntity( vControl, "CtrlMapView" );
         if ( nRC == 0 )
         {
            if ( CompareAttributeToInteger( vControl, "CtrlMapView", "ZKey", miLastViewZKey.intValue() ) != 0 )
            {
               lZKey = GetIntegerFromAttribute( vControl, "CtrlMapView", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vDialog, "ViewObjRef", "ZKey", lZKey, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  nRemapFlag = 0;
               }
               else
               {
                  szName = GetStringFromAttribute( vControl, "CtrlMapView", "Name" );
                  nRC = SetCursorFirstEntityByString( vDialog, "ViewObjRef", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     // If there was a match on Name but not on Zkey,
                     // reinclude the CtrlMapView.
                     ExcludeEntity( vControl, "CtrlMapView", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vControl, "CtrlMapView", vDialog, "ViewObjRef", zPOS_AFTER );
                     nRemapFlag = 0;
                  }
                  else
                  {
                     nRemapFlag = -1;
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting Control Mapping for:\n  ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, sbWindowReportName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\n  Control: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "Control", "Tag", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing View: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "CtrlMapView", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     szContinueParseGen = SysReadZeidonIni( -1, "[Workstation]", "ContinueParseGen" );
                     if ( ZeidonStringCompare( szContinueParseGen, 1, 0, "N", 1, 0, 2 ) == 0 )
                     {
                        TraceLineS( sbMsg.toString(), "" );
                     }
                     else
                     {
                        MessageSend( vSubtask, "WD00506", "Control Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     }

                     DeleteEntity( vControl, "CtrlMap", zREPOS_NONE );
                  }
               }

               if ( nRemapFlag == 0 )
               {
                  // There was a match on CtrlMapView ZKey or Name.
                  // Make sure the corresponding LOD is activated.
                  if ( miLastViewZKey.intValue() != 0 )
                  {
                     DropMetaOI( vSubtask, vLastLOD );
                     miLastViewZKey.setValue( 0 );
                  }

                  lZKey = GetIntegerFromAttribute( vDialog, "LOD", "ZKey" );
                  nRC = ActivateMetaOI_ByZKey( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, lZKey, 0 );
                  if ( nRC < 0 )
                  {
                     szName = GetStringFromAttribute( vDialog, "LOD", "Name" );
                     nRC = ActivateMetaOI_ByName( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, szName, 0 );
                  }

                  if ( nRC < 0 )
                  {
                     // If we get here, we have a Zeidon error.
                     szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ViewObjRef", "Name", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting CtrlMap due to LOD load error.\nRegistered View Name: ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     MessageSend( vSubtask, "WD00507", "Control Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vControl, "CtrlMap", zREPOS_NONE );
                  }
                  else
                  {
                     GetIntegerFromAttribute( miLastViewZKey, vDialog, "ViewObjRef", "ZKey" );
                     SetNameForView( vLastLOD, "vLastLOD", 0, zLEVEL_TASK );
                  }
               }
            }

         // IF vControl.CtrlMapView EXISTS
         //    RelinkInstanceToInstance( vControl, "CtrlMapView", vDialog, "ViewObjRef" )

            nRC = CheckExistenceOfEntity( vControl, "CtrlMapLOD_Attribute" );
            if ( nRC == 0 )
            {
               lZKey = GetIntegerFromAttribute( vControl, "CtrlMapLOD_Attribute", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vLastLOD, "LOD_Attribute", "ZKey", lZKey, "LOD" );
               if ( nRC < 0 )
               {
                  // Since there was no match on ZKey, try to find a match on LOD Entity
                  // name and ER Attribute name, in case the Attribute had been deleted
                  // and recreated.  In this case, re-include the Attribute.
                  szName = GetStringFromAttribute( vControl, "CtrlMapRelatedEntity", "Name" );
                  nRC = SetCursorFirstEntityByString( vLastLOD, "LOD_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     szName = GetStringFromAttribute( vControl, "CtrlMapER_Attribute", "Name" );
                     nRC = SetCursorFirstEntityByString( vLastLOD, "ER_Attribute", "Name", szName, "LOD_Entity" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        nRemapFlag = 0;
                     }
                     else
                     {
                        nRemapFlag = -1;
                     }
                  }
                  else
                  {
                     nRemapFlag = -1;
                  }

                  if ( nRemapFlag == 0 )
                  {
                     ExcludeEntity( vControl, "CtrlMapLOD_Attribute", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vControl, "CtrlMapLOD_Attribute", vLastLOD, "LOD_Attribute", zPOS_AFTER );
                  }
                  else
                  {
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting Control Mapping for:\n  ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, sbWindowReportName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\n  Control: ", 1, 0, 256 );
                     szTag = GetVariableFromAttribute( 0, 'S', 33, vControl, "Control", "Tag", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szTag, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing Attribute: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "CtrlMapER_Attribute", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     szContinueParseGen = SysReadZeidonIni( -1, "[Workstation]", "ContinueParseGen" );
                     if ( ZeidonStringCompare( szContinueParseGen, 1, 0, "N", 1, 0, 2 ) == 0 )
                     {
                        TraceLineS( sbMsg.toString(), "" );
                     }
                     else
                     {
                        MessageSend( vSubtask, "WD00508", "Control Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     }

                     nRC = CheckExistenceOfEntity( vControl, "CtrlMapLOD_Attribute" );
                     if ( nRC == 0 )
                     {
                        ExcludeEntity( vControl, "CtrlMapLOD_Attribute", zREPOS_AFTER );
                     }

                     nRC = CheckExistenceOfEntity( vControl, "CtrlMapLOD_Entity" );
                     if ( nRC == 0 )
                     {
                        ExcludeEntity( vControl, "CtrlMapLOD_Entity", zREPOS_AFTER );
                     }

                     nRC = CheckExistenceOfEntity( vControl, "CtrlMapContext" );
                     if ( nRC == 0 )
                     {
                        ExcludeEntity( vControl, "CtrlMapContext", zREPOS_AFTER );
                     }

                     nRC = CheckExistenceOfEntity( vControl, "CtrlMapView" );
                     if ( nRC == 0 )
                     {
                        ExcludeEntity( vControl, "CtrlMapView", zREPOS_AFTER );
                     }
                  }
               }
               else
               {
                  // Relink or Re-Include CtrlMapLOD_Attribute, & subordinates & Context
               // RelinkInstanceToInstance( vControl, "CtrlMapLOD_Attribute", vLastLOD, "LOD_Attribute" )
               // RelinkInstanceToInstance( vControl, "CtrlMapRelatedEntity", vLastLOD, "LOD_Entity" )
               // RelinkInstanceToInstance( vControl, "CtrlMapER_Attribute", vLastLOD, "ER_Attribute" )
               // RelinkInstanceToInstance( vControl, "CtrlMapER_Domain", vLastLOD, "Domain" )

                  // If ER_Attribute or ER_Domain don't match on ZKey, reinclude the LOD_Attribute entity.
                  if ( CompareAttributeToAttribute( vControl, "CtrlMapER_Attribute", "ZKey", vLastLOD, "ER_Attribute", "ZKey" ) != 0 ||
                       CompareAttributeToAttribute( vControl, "CtrlMapER_Domain", "ZKey", vLastLOD, "Domain", "ZKey" ) != 0 )
                  {
                     ExcludeEntity( vControl, "CtrlMapLOD_Attribute", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vControl, "CtrlMapLOD_Attribute", vLastLOD, "LOD_Attribute", zPOS_AFTER );
                  }

                  nRC = CheckExistenceOfEntity( vControl, "CtrlMapContext" );
                  if ( nRC == 0 )
                  {
                     // Try to find the Domain first by ZKey and then by name.
                     // If found, also find the Context by ZKey and then by name.
                     // If no match was finally found, exclude the Context.
                     lZKey = GetIntegerFromAttribute( vControl, "CtrlMapER_Domain", "ZKey" );
                     nRC = ActivateMetaOI_ByZKey( vSubtask, vDomain, null, zREFER_DOMAIN_META, zSINGLE, lZKey, 0 );
                     if ( nRC >= 0 )
                     {
                        lZKey = GetIntegerFromAttribute( vControl, "CtrlMapContext", "ZKey" );
                        nRC = SetCursorFirstEntityByInteger( vDomain, "Context", "ZKey", lZKey, "" );
                        if ( nRC >= zCURSOR_SET )
                        {
                           nRemapFlag = 0;
                        }
                        else
                        {
                           szName = GetStringFromAttribute( vControl, "CtrlMapContext", "Name" );
                           nRC = SetCursorFirstEntityByString( vDomain, "Context", "Name", szName, "" );
                           if ( nRC >= zCURSOR_SET )
                           {
                              nRemapFlag = 1;
                              ExcludeEntity( vControl, "CtrlMapContext", zREPOS_AFTER );
                              IncludeSubobjectFromSubobject( vControl, "CtrlMapContext", vDomain, "Context", zPOS_AFTER );
                           }
                           else
                           {
                              nRemapFlag = -1;
                           }
                        }
                     }
                     else
                     {
                        szName = GetStringFromAttribute( vControl, "CtrlMapER_Domain", "Name" );
                        nRC = ActivateMetaOI_ByName( vSubtask, vDomain, null, zREFER_DOMAIN_META, zSINGLE, szName, 0 );
                        if ( nRC >= 0 )
                        {
                           szName = GetStringFromAttribute( vControl, "CtrlMapContext", "Name" );
                           nRC = SetCursorFirstEntityByString( vDomain, "Context", "Name", szName, "" );
                           if ( nRC >= zCURSOR_SET )
                           {
                              nRemapFlag = 1;
                              ExcludeEntity( vControl, "CtrlMapContext", zREPOS_AFTER );
                              IncludeSubobjectFromSubobject( vControl, "CtrlMapContext", vDomain, "Context", zPOS_AFTER );
                           }
                           else
                           {
                              nRemapFlag = -1;
                           }
                        }
                        else
                        {
                           nRemapFlag = -1;
                        }
                     }

                     if ( nRemapFlag == -1 )
                     {
                        ZeidonStringCopy( sbMsg, 1, 0, "Excluding Context for:\n  ", 1, 0, 256 );
                        ZeidonStringConcat( sbMsg, 1, 0, sbWindowReportName, 1, 0, 256 );
                        ZeidonStringConcat( sbMsg, 1, 0, "\n  Control: ", 1, 0, 256 );
                        szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "Control", "Tag", "", 0 );
                        ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                        ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing Context: ", 1, 0, 256 );
                        szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "CtrlMapContext", "Name", "", 0 );
                        ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                        MessageSend( vSubtask, "WD00509", "Control Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                        ExcludeEntity( vControl, "CtrlMapContext", zREPOS_NONE );
                     }
                     else
                     {
                        if ( nRemapFlag == 0 )
                        {
                           RelinkInstanceToInstance( vControl, "CtrlMapContext", vDomain, "Context" );
                        }

                        DropMetaOI( vSubtask, vDomain );
                     }
                  }
               }
            }

            nRC = CheckExistenceOfEntity( vControl, "CtrlMapLOD_Entity" );
            if ( nRC == 0 )
            {
               lZKey = GetIntegerFromAttribute( vControl, "CtrlMapLOD_Entity", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vLastLOD, "LOD_Entity", "ZKey", lZKey, "" );
               if ( nRC < 0 )
               {
                  // Since there was no match on ZKey, try again on name in case the
                  // LOD_Entity had been deleted and readded.
                  szName = GetStringFromAttribute( vControl, "CtrlMapLOD_Entity", "Name" );
                  nRC = SetCursorFirstEntityByString( vLastLOD, "LOD_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     ExcludeEntity( vControl, "CtrlMapLOD_Entity", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vControl, "CtrlMapLOD_Entity", vLastLOD, "LOD_Entity", zPOS_AFTER );
                  }
                  else
                  {
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting Control Mapping for:\n  ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, sbWindowReportName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\n  Control: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "Control", "Tag", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing Entity: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vControl, "CtrlMapLOD_Entity", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     szContinueParseGen = SysReadZeidonIni( -1, "[Workstation]", "ContinueParseGen" );
                     if ( ZeidonStringCompare( szContinueParseGen, 1, 0, "N", 1, 0, 2 ) == 0 )
                     {
                        TraceLineS( sbMsg.toString(), "" );
                     }
                     else
                     {
                        MessageSend( vSubtask, "WD00510", "Control Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     }

                     DeleteEntity( vControl, "CtrlMap", zREPOS_NONE );
                  }
               }
               else
               {
                  // Relink CtrlMapLOD_Entity
                  RelinkInstanceToInstance( vControl, "CtrlMapLOD_Entity", vLastLOD, "LOD_Entity" );
               }
            }
         }

         nRC = SetCursorNextEntity( vControl, "CtrlMap", "" );
      }

      return 0;
   }

   private int oTZWDLGSO_OptionRelinkDelete( View vSubtask, View vLOD_LPLR, View vDialog, View vOption, zVIEW vLastLOD, MutableInt miLastViewZKey )
   {
      zVIEW  vDomain = new zVIEW();
      StringBuilder sbMsg = new StringBuilder();
      String szName;
      String szTag;
      int    nRemapFlag = 0;
      int    lZKey;
      int    nRC;

      // Process each subcontrol.
      nRC = SetCursorFirstEntity( vOption, "OptOpt", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         SetViewToSubobject( vOption, "OptOpt" );
         oTZWDLGSO_OptionRelinkDelete( vSubtask, vLOD_LPLR, vDialog, vOption, vLastLOD, miLastViewZKey );
         ResetViewFromSubobject( vOption );
         nRC = SetCursorNextEntity( vOption, "OptOpt", "" );
      }

      nRC = SetCursorFirstEntity( vOption, "OptMap", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         nRC = CheckExistenceOfEntity( vOption, "OptMapView" );
         if ( nRC == 0 )
         {
            // Get the correct LOD, if the last one used isn't the one in this OptMap.
            if ( CompareAttributeToInteger( vOption, "OptMapView", "ZKey", miLastViewZKey.intValue() ) != 0 )
            {
               lZKey = GetIntegerFromAttribute( vOption, "OptMapView", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vDialog, "ViewObjRef", "ZKey", lZKey, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  nRemapFlag = 0;
               }
               else
               {
                  szName = GetStringFromAttribute( vOption, "OptMapView", "Name" );
                  nRC = SetCursorFirstEntityByString( vDialog, "ViewObjRef", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     // If there was a match on Name but not on Zkey,
                     // reinclude the OptMapView.
                     ExcludeEntity( vOption, "OptMapView", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vOption, "OptMapView", vDialog, "ViewObjRef", zPOS_AFTER );
                     nRemapFlag = 0;
                  }
                  else
                  {
                     szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "Window", "Tag", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting Option Mapping for:\n  Window: ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\n  Option: ", 1, 0, 256 );
                     szTag = GetVariableFromAttribute( 0, 'S', 33, vOption, "Option", "Tag", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szTag, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing View: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vOption, "OptMapView", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     MessageSend( vSubtask, "WD00511", "Dialog Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vOption, "OptMap", zREPOS_NONE );
                     nRemapFlag = -1;
                  }
               }

               if ( nRemapFlag == 0 )
               {
                  // There was a match on OptMapView Zkey or Name.
                  // Make sure the corresponding LOD is activated.
                  if ( miLastViewZKey.intValue() != 0 )
                  {
                     DropMetaOI( vSubtask, vLastLOD );
                     miLastViewZKey.setValue( 0 );
                  }

                  lZKey = GetIntegerFromAttribute( vDialog, "LOD", "ZKey" );
                  nRC = ActivateMetaOI_ByZKey( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, lZKey, 0 );
                  if ( nRC < 0 )
                  {
                     // If we get here, we have a Zeidon error.
                     szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ViewObjRef", "Name", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting OptMap due to LOD load error.\nRegistered View Name: ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     MessageSend( vSubtask, "WD00512", "Dialog Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vOption, "OptMap", zREPOS_NONE );
                  }
                  else
                  {
                     GetIntegerFromAttribute( miLastViewZKey, vDialog, "ViewObjRef", "ZKey" );
                     SetNameForView( vLastLOD, "vLastLOD", 0, zLEVEL_TASK );
                  }
               }
            }

         // IF vOption.OptMapView EXISTS
         //    RelinkInstanceToInstance( vOption, "OptMapView", vDialog, "ViewObjRef" )

            nRC = CheckExistenceOfEntity( vOption, "OptMapLOD_Attribute" );
            if ( nRC == 0 )
            {
               lZKey = GetIntegerFromAttribute( vOption, "OptMapLOD_Attribute", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vLastLOD, "LOD_Attribute", "ZKey", lZKey, "LOD" );
               if ( nRC < 0 )
               {
                  // Since there was no match on ZKey, try to find a match on LOD Entity
                  // name and ER Attribute name, in case the Attribute had been deleted
                  // and recreated.  In this case, re-include the Attribute.
                  szName = GetStringFromAttribute( vOption, "OptMapRelatedEntity", "Name" );
                  nRC = SetCursorFirstEntityByString( vLastLOD, "LOD_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     szName = GetStringFromAttribute( vOption, "OptMapER_Attribute", "Name" );
                     nRC = SetCursorFirstEntityByString( vLastLOD, "ER_Attribute", "Name", szName, "LOD_Entity" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        nRemapFlag = 0;
                     }
                     else
                     {
                        nRemapFlag = -1;
                     }
                  }
                  else
                  {
                     nRemapFlag = -1;
                  }

                  if ( nRemapFlag == 0 )
                  {
                     ExcludeEntity( vOption, "OptMapLOD_Attribute", zREPOS_AFTER );
                     IncludeSubobjectFromSubobject( vOption, "OptMapLOD_Attribute", vLastLOD, "LOD_Attribute", zPOS_AFTER );
                  }
                  else
                  {
                     szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "Window", "Tag", "", 0 );
                     ZeidonStringCopy( sbMsg, 1, 0, "Deleting Option Mapping for:\n  Window: ", 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\n  Option: ", 1, 0, 256 );
                     szTag = GetVariableFromAttribute( 0, 'S', 33, vOption, "Option", "Tag", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szTag, 1, 0, 256 );
                     ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing Attribute: ", 1, 0, 256 );
                     szName = GetVariableFromAttribute( 0, 'S', 33, vOption, "OptMapER_Attribute", "Name", "", 0 );
                     ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                     MessageSend( vSubtask, "WD00513", "Dialog Activate", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                     DeleteEntity( vOption, "OptMap", zREPOS_NONE );
                  }
               }
               else
               {
                  // Relink OptMapLOD_Attribute, & subordinates & OptMapContext
               // RelinkInstanceToInstance( vOption,  "OptMapLOD_Attribute", vLastLOD, "LOD_Attribute" )
               // RelinkInstanceToInstance( vOption,  "OptMapRelatedEntity", vLastLOD, "LOD_Entity" )
               // RelinkInstanceToInstance( vOption,  "OptMapER_Attribute", vLastLOD, "ER_Attribute" )
               // RelinkInstanceToInstance( vOption,  "OptMapER_Domain", vLastLOD, "Domain" )
                  nRC = CheckExistenceOfEntity( vOption, "OptMapContext" );
                  if ( nRC == 0 )
                  {
                     // Try to find the Domain first by ZKey and then by name.
                     // If found, also find the Context by ZKey and then by name.
                     // If no match was finally found, exclude the Context.
                     lZKey = GetIntegerFromAttribute( vOption, "OptMapER_Domain", "ZKey" );
                     nRC = ActivateMetaOI_ByZKey( vSubtask, vDomain, null, zREFER_DOMAIN_META, zSINGLE, lZKey, 0 );
                     if ( nRC >= 0 )
                     {
                        lZKey = GetIntegerFromAttribute( vOption, "OptMapContext", "ZKey" );
                        nRC = SetCursorFirstEntityByInteger( vDomain, "Context", "ZKey", lZKey, "" );
                        if ( nRC >= 0 )
                        {
                           nRemapFlag = 0;
                        }
                        else
                        {
                           szName = GetStringFromAttribute( vOption, "OptMapContext", "Name" );
                           nRC = SetCursorFirstEntityByString( vDomain, "Context", "Name", szName, "" );
                           if ( nRC >= zCURSOR_SET )
                           {
                              nRemapFlag = 1;
                              ExcludeEntity( vOption, "OptMapContext", zREPOS_AFTER );
                              IncludeSubobjectFromSubobject( vOption, "OptMapContext", vDomain, "Context", zPOS_AFTER );
                           }
                           else
                           {
                              nRemapFlag = -1;
                           }
                        }
                     }
                     else
                     {
                        szName = GetStringFromAttribute( vOption, "OptMapER_Domain", "Name" );
                        nRC = ActivateMetaOI_ByName( vSubtask, vDomain, null, zREFER_DOMAIN_META, zSINGLE, szName, 0 );
                        if ( nRC >= 0 )
                        {
                           szName = GetStringFromAttribute( vOption, "OptMapContext", "Name" );
                           nRC = SetCursorFirstEntityByString( vDomain, "Context", "Name", szName, "" );
                           if ( nRC >= zCURSOR_SET )
                           {
                              nRemapFlag = 1;
                              ExcludeEntity( vOption, "OptMapContext", zREPOS_AFTER );
                              IncludeSubobjectFromSubobject( vOption, "OptMapContext", vDomain, "Context", zPOS_AFTER );
                           }
                           else
                           {
                              nRemapFlag = -1;
                           }
                        }
                        else
                        {
                           nRemapFlag = -1;
                        }
                     }

                     if ( nRemapFlag == -1 )
                     {
                        szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "Window", "Tag", "", 0 );
                        ZeidonStringCopy( sbMsg, 1, 0, "Excluding Context for:\n  Window: ", 1, 0, 256 );
                        ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                        ZeidonStringConcat( sbMsg, 1, 0, "\n  Option: ", 1, 0, 256 );
                        szTag = GetVariableFromAttribute( 0, 'S', 33, vOption, "Option", "Tag", "", 0 );
                        ZeidonStringConcat( sbMsg, 1, 0, szTag, 1, 0, 256 );
                        ZeidonStringConcat( sbMsg, 1, 0, "\nbecause of missing Context: ", 1, 0, 256 );
                        szName = GetVariableFromAttribute( 0, 'S', 33, vOption, "OptMapContext", "Name", "", 0 );
                        ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                        MessageSend( vSubtask, "WD00514", "Dialog Activate", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                        ExcludeEntity( vOption, "OptMapContext", zREPOS_NONE );
                     }
                     else
                     {
                        if ( nRemapFlag == 0 )
                        {
                           RelinkInstanceToInstance( vOption, "OptMapContext", vDomain, "Context" );
                        }

                        DropMetaOI( vSubtask, vDomain );
                     }
                  }
               }
            }
         }

         nRC = SetCursorNextEntity( vOption, "OptMap", "" );
      }

      return 0;
   }

//////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZWDLGSO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZWDLGSO_PostCheckout( zVIEW vCM_Subtask, View vTZWDLGSO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   private int oTZWDLGSO_DialogRelinkDelete( View vDialog, View vSubtask )
   {
      zVIEW     vLastLOD = new zVIEW();
      zVIEW     vVOR = new zVIEW();
      zVIEW     vLOD_LPLR = new zVIEW();
      zVIEW     vRecursive = new zVIEW();
      MutableInt miLastViewZKey = new MutableInt( 0 );
      StringBuilder sbMsg = new StringBuilder();
      String    szContinueParseGen;
      int       nRC;
      int       lZKey;
      String    szName;

      RetrieveViewForMetaList( vSubtask, vLOD_LPLR, zREFER_LOD_META );

      SetNameForView( vDialog, "vDialog", 0, zLEVEL_TASK );

      nRC = SetCursorFirstEntity( vDialog, "ViewObjRef", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         if ( miLastViewZKey.intValue() != 0 )
         {
            DropMetaOI( vSubtask, vLastLOD );
            miLastViewZKey.setValue( 0 );
         }

         lZKey = GetIntegerFromAttribute( vDialog, "ViewObjRef", "ZKey" );
         nRC = ActivateMetaOI_ByZKey( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, lZKey, 0 );
         if ( nRC >= 0 )
         {
            szName = GetStringFromAttribute( vVOR, "LOD", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, szName, 0 );
            if ( nRC >= 0 )
            {
               miLastViewZKey.setValue( GetIntegerFromAttribute( vDialog, "ViewObjRef", "ZKey" ) );
               SetNameForView( vLastLOD, "vLastLOD", 0, zLEVEL_TASK );

               // Relink ViewObjRef & LOD
            // RelinkInstanceToInstance( vDialog, "ViewObjRef", vVOR, "ViewObjRef" )
            // RelinkInstanceToInstance( vDialog, "LOD", vLastLOD, "LOD" )

               // Since RelinkInstanceToInstance does not reset the include flags,
               // use RelinkAllSubobjectsForOI for relinking entities that need those include
               // flags reset.  When a version of RelinkInstanceToInstance is created
               // that resets those flags, the following code can be removed.
               RelinkAllSubobjectsForOI( vDialog, "ViewObjRef", vVOR.getView(), "ViewObjRef" );
               RelinkAllSubobjectsForOI( vDialog, "CtrlMapLOD_Attribute", vLastLOD.getView(), "LOD_Attribute" );
               RelinkAllSubobjectsForOI( vDialog, "OptMapLOD_Attribute", vLastLOD.getView(), "LOD_Attribute" );
               RelinkAllSubobjectsForOI( vDialog, "ActMapLOD_Entity", vLastLOD.getView(), "LOD_Entity" );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 9, vDialog, "ViewObjRef", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting Dialog View: ", 1, 0, 256 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
               ZeidonStringConcat( sbMsg, 1, 0, "\nMissing LOD: ", 1, 0, 256 );
               szName = GetVariableFromAttribute( 0, 'S', 9, vDialog, "LOD", "Name", "", 0 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
               szContinueParseGen = SysReadZeidonIni( -1, "[Workstation]", "ContinueParseGen" );
               if ( ZeidonStringCompare( szContinueParseGen, 1, 0, "N", 1, 0, 2 ) == 0 )
               {
                  TraceLineS( sbMsg.toString(), "" );
               }
               else
               {
                  MessageSend( vSubtask, "WD00501", "Dialog Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               }

               ExcludeEntity( vDialog, "ViewObjRef", zREPOS_NONE );
            }

            DropMetaOI( vSubtask, vVOR );
         }
         else
         {
            szName = GetStringFromAttribute( vDialog, "ViewObjRef", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, szName, 0 );
            if ( nRC >= 0 )
            {
               ExcludeEntity( vDialog, "ViewObjRef", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vDialog, "ViewObjRef", vVOR, "ViewObjRef", zPOS_AFTER );
               DropMetaOI( vSubtask, vVOR );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ViewObjRef", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting Dialog View: ", 1, 0, 256 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
               ZeidonStringConcat( sbMsg, 1, 0, "\nMissing Registered View.", 1, 0, 256 );
               szContinueParseGen = SysReadZeidonIni( -1, "[Workstation]", "ContinueParseGen" );
               if ( ZeidonStringCompare( szContinueParseGen, 1, 0, "N", 1, 0, 2 ) == 0 )
               {
                  TraceLineS( sbMsg.toString(), "" );
               }
               else
               {
                  MessageSend( vSubtask, "WD00502", "Dialog Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               }

               ExcludeEntity( vDialog, "ViewObjRef", zREPOS_NONE );
            }
         }

         nRC = SetCursorNextEntity( vDialog, "ViewObjRef", "" );
      }

      nRC = SetCursorFirstEntity( vDialog, "Window", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         CreateViewFromViewForTask( vRecursive, vDialog, null );
         SetNameForView( vRecursive, "vRecursive", 0, zLEVEL_TASK );
         nRC = SetCursorFirstEntity( vRecursive, "Control", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            oTZWDLGSO_ControlRelinkDelete( vDialog, vRecursive, vLastLOD, miLastViewZKey, vLOD_LPLR, vSubtask );
            nRC = SetCursorNextEntity( vRecursive, "Control", "" );
         }

         nRC = SetCursorFirstEntity( vDialog, "Action", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            nRC = SetCursorFirstEntity( vDialog, "ActMap", "" );
            while ( nRC > zCURSOR_UNCHANGED )
            {
               nRC = CheckExistenceOfEntity( vDialog, "ActMapView" );
               if ( nRC == 0 )
               {
                  if ( CompareAttributeToInteger( vDialog, "ActMapView", "ZKey", miLastViewZKey.intValue() ) != 0 )
                  {
                     lZKey = GetIntegerFromAttribute( vDialog, "ActMapView", "ZKey" );
                     nRC = SetCursorFirstEntityByInteger( vDialog, "ViewObjRef", "ZKey", lZKey, "" );
                     if ( nRC < zCURSOR_SET )
                     {
                        szName = GetStringFromAttribute( vDialog, "ActMapView", "Name" );
                        nRC = SetCursorFirstEntityByString( vDialog, "ViewObjRef", "Name", szName, "" );
                        if ( nRC >= zCURSOR_SET )
                        {

                           // If there was a match on Name but not on Zkey,
                           // reinclude the ActMapView.
                           ExcludeEntity( vDialog, "ActMapView", zREPOS_AFTER );
                           IncludeSubobjectFromSubobject( vDialog, "ActMapView", vDialog, "ViewObjRef", zPOS_AFTER );

                           // Activate the correct LOD for later processing.
                           if ( miLastViewZKey.intValue() != 0 )
                           {
                              DropMetaOI( vSubtask, vLastLOD );
                              miLastViewZKey.setValue( 0 );
                           }

                           lZKey = GetIntegerFromAttribute( vDialog, "LOD", "ZKey" );
                           nRC = ActivateMetaOI_ByZKey( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, lZKey, 0 );
                           if ( nRC < 0 )
                           {
                              szName = GetStringFromAttribute( vDialog, "LOD", "Name" );
                              nRC = ActivateMetaOI_ByName( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, szName, 0 );
                           }


                           if ( nRC < 0 )
                           {
                              // If we get here, we have a Zeidon error.
                              szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ViewObjRef", "Name", "", 0 );
                              ZeidonStringCopy( sbMsg, 1, 0, "Deleting ActMap due to LOD load error.\nRegistered View Name: ", 1, 0, 256 );
                              ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                              MessageSend( vSubtask, "WD00504", "Dialog Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                              DeleteEntity( vDialog, "ActMap", zREPOS_NONE );
                           }
                           else
                           {
                              GetIntegerFromAttribute( miLastViewZKey, vDialog, "ViewObjRef", "ZKey" );
                              SetNameForView( vLastLOD, "vLastLOD", 0, zLEVEL_TASK );
                           }
                        }
                        else
                        {
                           // There was no match on either ZKey or Name.
                           szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ActMapView", "Name", "", 0 );
                           ZeidonStringCopy( sbMsg, 1, 0, "Deleting Action Mapping due to missing View. \nRegistered View Name: ", 1, 0, 256 );
                           ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                           MessageSend( vSubtask, "WD00503", "Dialog Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                           DeleteEntity( vDialog, "ActMap", zREPOS_NONE );
                        }
                     }
                     else
                     {
                        // There was a match on ActMapView Zkey.  Make sure the corresponding LOD is activated.
                        if ( miLastViewZKey.intValue() != 0 )
                        {
                           DropMetaOI( vSubtask, vLastLOD );
                           miLastViewZKey.setValue( 0 );
                        }

                        lZKey = GetIntegerFromAttribute( vDialog, "LOD", "ZKey" );
                        nRC = ActivateMetaOI_ByZKey( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, lZKey, 0 );
                        if ( nRC < 0 )
                        {
                           szName = GetStringFromAttribute( vDialog, "LOD", "Name" );
                           nRC = ActivateMetaOI_ByName( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, szName, 0 );
                        }

                        if ( nRC < 0 )
                        {
                           // If we get here, we have a Zeidon error.
                           szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ViewObjRef", "Name", "", 0 );
                           ZeidonStringCopy( sbMsg, 1, 0, "Deleting ActMap due to LOD load error.\nRegistered View Name: ", 1, 0, 256 );
                           ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                           MessageSend( vSubtask, "WD00504", "Dialog Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                           DeleteEntity( vDialog, "ActMap", zREPOS_NONE );
                        }
                        else
                        {
                           GetIntegerFromAttribute( miLastViewZKey, vDialog, "ViewObjRef", "ZKey" );
                           SetNameForView( vLastLOD, "vLastLOD", 0, zLEVEL_TASK );
                        }
                     }
                  }

                  nRC = CheckExistenceOfEntity( vDialog, "ActMapLOD_Entity" );
                  if ( nRC == 0 )
                  {
                     lZKey = GetIntegerFromAttribute( vDialog, "ActMapLOD_Entity", "ZKey" );
                     nRC = SetCursorFirstEntityByInteger( vLastLOD, "LOD_Entity", "ZKey", lZKey, "" );
                     if ( nRC < zCURSOR_SET )
                     {
                        szName = GetStringFromAttribute( vDialog, "ActMapLOD_Entity", "Name" );
                        nRC = SetCursorFirstEntityByString( vLastLOD, "LOD_Entity", "Name", szName, "" );
                        if ( nRC >= zCURSOR_SET )
                        {
                           // If there was a match on Name but not on Zkey,
                           // reinclude the LOD_Entity.
                           ExcludeEntity( vDialog, "ActMapLOD_Entity", zREPOS_AFTER );
                           IncludeSubobjectFromSubobject( vDialog, "ActMapLOD_Entity", vLastLOD, "LOD_Entity", zPOS_AFTER );
                        }
                        else
                        {
                           // There was no match on either ZKey or Name.
                           szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ViewObjRef", "Name", "", 0 );
                           ZeidonStringCopy( sbMsg, 1, 0, "Deleting Action Mapping due to missing LOD Entity.\n  Registered View Name: ", 1, 0, 256 );
                           ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                           ZeidonStringConcat( sbMsg, 1, 0, "\n  LOD Entity Name: ", 1, 0, 256 );
                           szName = GetVariableFromAttribute( 0, 'S', 33, vDialog, "ActMapLOD_Entity", "Name", "", 0 );
                           ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 256 );
                           MessageSend( vSubtask, "WD00505", "Dialog Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                           DeleteEntity( vDialog, "ActMap", zREPOS_NONE );
                        }
                     }
                  }
               }

               nRC = SetCursorNextEntity( vDialog, "ActMap", "" );
            }

            nRC = SetCursorNextEntity( vDialog, "Action", "" );
         }

         nRC = SetCursorFirstEntity( vRecursive, "Menu", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            nRC = SetCursorFirstEntity( vDialog, "Option", "" );
            while ( nRC > zCURSOR_UNCHANGED )
            {
               oTZWDLGSO_OptionRelinkDelete( vSubtask, vLOD_LPLR, vDialog, vRecursive, vLastLOD, miLastViewZKey );
               nRC = SetCursorNextEntity( vDialog, "Option", "" );
            }

            nRC = SetCursorNextEntity( vRecursive, "Menu", "" );
         }

         DropView( vRecursive );
         nRC = SetCursorNextEntity( vDialog, "Window", "" );
      }

      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZWDLGSO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZWDLGSO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZWDLGSO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW    vActiveMetas = new zVIEW();

      // The Dialog has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?
                                                     // dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////

      oTZWDLGSO_DialogRelinkDelete( vTZWDLGSO, vSubtask );
      return 0;
   }

   private int oTZRPSRCO_ReportRelinkDelete( View vReport, View vSubtask )
   {
      zVIEW vLastLOD = new zVIEW();
      zVIEW vVOR = new zVIEW();
      zVIEW vLOD_LPLR = new zVIEW();
      zVIEW vRecursive = new zVIEW();
      zVIEW vDialog = new zVIEW();
      MutableInt miLastViewZKey = new MutableInt();
      StringBuilder sbMsg= new StringBuilder();
      String szName;
      int    lZKey;
      int    nRC;

      RetrieveViewForMetaList( vSubtask, vLOD_LPLR, zREFER_LOD_META );
      miLastViewZKey.setValue( 0 );

      // NAME VIEW vReport "vReport"
      SetNameForView( vReport, "vReport", vSubtask, zLEVEL_TASK );

      nRC = SetCursorFirstEntity( vReport, "ViewObjRef", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         if ( miLastViewZKey.intValue() != 0 )
         {
            DropMetaOI( vSubtask, vLastLOD );
            miLastViewZKey.setValue( 0 );
         }

         lZKey = GetIntegerFromAttribute( vReport, "ViewObjRef", "ZKey" );
         nRC = ActivateMetaOI_ByZKey( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, lZKey, 0 );
         if ( nRC >= 0 )
         {
            szName = GetStringFromAttribute( vVOR, "LOD", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vLastLOD, null, zREFER_LOD_META, zSINGLE, szName, 0 );
            if ( nRC >= 0 )
            {
               GetIntegerFromAttribute( miLastViewZKey, vReport, "ViewObjRef", "ZKey" );
               SetNameForView( vLastLOD, "vLastLOD", 0, zLEVEL_TASK );

               // Relink ViewObjRef & LOD
            // RelinkInstanceToInstance( vReport, "ViewObjRef", vVOR, "ViewObjRef" )
            // RelinkInstanceToInstance( vReport, "LOD", vLastLOD, "LOD" )

               // Since RelinkInstanceToInstance does not reset the include flags,
               // use RelinkAllSubobjectsForOI for relinking entities that need those include
               // flags reset.  When a version of RelinkInstanceToInstance is created
               // that resets those flags, the following code can be removed.
               RelinkAllSubobjectsForOI( vReport, "ViewObjRef", vVOR.getView(), "ViewObjRef" );
               RelinkAllSubobjectsForOI( vReport, "CtrlMapLOD_Attribute", vLastLOD.getView(), "LOD_Attribute" );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 9, vReport, "ViewObjRef", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting Report View: ", 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, "Missing LOD: ", 1, 0, 129 );
               szName = GetVariableFromAttribute( 0, 'S', 9, vReport, "LOD", "Name", "", 0 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               MessageSend( vSubtask, "WD00501", "Report Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               ExcludeEntity( vReport, "ViewObjRef", zREPOS_NONE );
            }

            DropMetaOI( vSubtask, vVOR );
         }
         else
         {
            szName = GetStringFromAttribute( vReport, "ViewObjRef", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, szName, 0 );
            if ( nRC >= 0 )
            {
               ExcludeEntity( vReport, "ViewObjRef", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vReport, "ViewObjRef", vVOR, "ViewObjRef", zPOS_AFTER );
               DropMetaOI( vSubtask, vVOR );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 33, vReport, "ViewObjRef", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting Report View: ", 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, "Missing Registered View.", 1, 0, 129 );
               MessageSend( vSubtask, "WD00502", "Report Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               ExcludeEntity( vReport, "ViewObjRef", zREPOS_NONE );
            }
         }

         nRC = SetCursorNextEntity( vReport, "ViewObjRef", "" );
      }

      nRC = SetCursorFirstEntity( vReport, "Group", "Report" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         CreateViewFromViewForTask( vRecursive, vReport, null );
         SetNameForView( vRecursive, "vRecursive", 0, zLEVEL_TASK );
         nRC = SetCursorFirstEntity( vRecursive, "Control", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            // Use Dialog view in ControlRelinkDelete call so that operation is
            // found under the TZWDLGSO object.
            vDialog.setView( vReport );
            oTZWDLGSO_ControlRelinkDelete( vDialog, vRecursive, vLastLOD, miLastViewZKey, vLOD_LPLR, vSubtask );
            nRC = SetCursorNextEntity( vRecursive, "Control", "" );
         }

         DropView( vRecursive );
         nRC = SetCursorNextEntity( vReport, "Group", "Report" );
      }

      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZRPSRCO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZRPSRCO_PostCheckout( zVIEW vCM_Subtask, View vTZWDLGSO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZRPSRCO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZRPSRCO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZRPSRCO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW vActiveMetas = new zVIEW();

      // The Report has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?
                                                     // dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////

      oTZRPSRCO_ReportRelinkDelete( vTZRPSRCO, vSubtask );
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZXSLTSO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZXSLTSO_PostCheckout( View vCM_Subtask, View vTZXSLTSO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZXSLTSO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZXSLTSO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZXSLTSO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW    vActiveMetas = new zVIEW();

      // The Report has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this?
                                                     // dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////

   // oTZXSLTSO_XSLT_RelinkDelete( vTZXSLTSO, vSubtask );
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZADCSDO_PostCheckout
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZADCSDO_PostCheckout( View vCM_Subtask, View vTZADCSDO, View vTZCMLPLO, boolean bRepository )
   {
      // Nothing to do
      return 0;
   }

   private int oTZADCSDO_DS_EntityRelink( View vSubtask, View vDSR, View vLOD )
   {
      zVIEW   vVOR = new zVIEW();
      zVIEW   vLOD_List = new zVIEW();
      StringBuilder sbMsg = new StringBuilder();
      String  szName;
      int     nRemapFlag;
      int     lZKey;
      int     nRC;

      // This is the recursive routine to process each UIS_Entity for relink and
      // delete of LOD_Entity, M_LOD_Attribute, L_LOD_Attribute, I_LOD_Attribute,
      // ListViewObjRef and IncludeViewObjRef entities.

      nRC = SetCursorFirstEntity( vDSR, "UIS_ChildEntity", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         SetViewToSubobject( vDSR, "UIS_ChildEntity" );
         oTZADCSDO_DS_EntityRelink( vDSR, vLOD, vSubtask );
         ResetViewFromSubobject( vDSR );
         nRC = SetCursorNextEntity( vDSR, "UIS_ChildEntity", "" );
      }

      lZKey = GetIntegerFromAttribute( vDSR, "LOD_Entity", "ZKey" );
      nRC = SetCursorFirstEntityByInteger( vLOD, "LOD_Entity", "ZKey", lZKey, "" );
      if ( nRC >= 0 )
      {
         RelinkAllSubobjectsForOI( vDSR, "LOD_Entity", vLOD, "LOD_Entity" );
         nRemapFlag = 0;
      }
      else
      {
         szName = GetStringFromAttribute( vDSR, "LOD_Entity", "Name" );
         nRC = SetCursorFirstEntityByString( vLOD, "LOD_Entity", "Name", szName, "" );
         if ( nRC >= 0 )
         {
            nRemapFlag = 1;
            ExcludeEntity( vDSR, "LOD_Entity", zREPOS_NONE );
            IncludeSubobjectFromSubobject( vDSR, "LOD_Entity", vLOD, "LOD_Entity", zPOS_AFTER );
         }
         else
         {
            nRemapFlag = -1;
            szName = GetVariableFromAttribute( 0, 'S', 33, vDSR, "LOD_Entity", "Name", "", 0 );
            ZeidonStringCopy( sbMsg, 1, 0, "Deleting UIS Entity: ", 1, 0, 129 );
            ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
            ZeidonStringConcat( sbMsg, 1, 0, "Not found in LOD: ", 1, 0, 129 );
            szName = GetVariableFromAttribute( 0, 'S', 9, vLOD, "LOD", "Name", "", 0 );
            ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
            MessageSend( vSubtask, "AD11010", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
            DeleteEntity( vDSR, "UIS_Entity", zREPOS_NONE );
         }
      }

      if ( nRemapFlag >= 0 )
      {
         nRC = SetCursorFirstEntity( vDSR, "M_LOD_Attribute", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            lZKey = GetIntegerFromAttribute( vDSR, "M_LOD_Attribute", "ZKey" );
            nRC = SetCursorFirstEntityByInteger( vLOD, "LOD_Attribute", "ZKey", lZKey, "LOD" );
            if ( nRC < 0 )
            {
               // Since there was no match on ZKey, try to find a match on LOD Entity
               // name and ER Attribute name, in case the Attribute had been deleted
               // and recreated.  In this case, re-include the Attribute.
               szName = GetStringFromAttribute( vDSR, "M_LOD_Entity", "Name" );
               nRC = SetCursorFirstEntityByString( vLOD, "LOD_Entity", "Name", szName, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  szName = GetStringFromAttribute( vDSR, "M_ER_Attribute", "Name" );
                  nRC = SetCursorFirstEntityByString( vLOD, "ER_Attribute", "Name", szName, "LOD_Entity" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     nRemapFlag = 0;
                  }
                  else
                  {
                     nRemapFlag = -1;
                  }
               }
               else
               {
                  nRemapFlag = -1;
               }

               if ( nRemapFlag == 0 )
               {
                  ExcludeEntity( vDSR, "M_LOD_Attribute", zREPOS_NONE );
                  IncludeSubobjectFromSubobject( vDSR, "M_LOD_Attribute", vLOD, "LOD_Attribute", zPOS_AFTER );
               }
               else
               {
                  szName = GetVariableFromAttribute( 0, 'S', 33, vDSR, "M_ER_Attribute", "Name", "", 0 );
                  ZeidonStringCopy( sbMsg, 1, 0, "Deleting Main Attribute: ", 1, 0, 129 );
                  ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
                  ZeidonStringConcat( sbMsg, 1, 0, "Not found in LOD: ", 1, 0, 129 );
                  szName = GetVariableFromAttribute( 0, 'S', 9, vLOD, "LOD", "Name", "", 0 );
                  ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
                  MessageSend( vSubtask, "AD11011", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                  ExcludeEntity( vDSR, "M_LOD_Attribute", zREPOS_NONE );
               }
            }

            nRC = SetCursorNextEntity( vDSR, "M_LOD_Attribute", "" );
         }

         nRC = SetCursorFirstEntity( vDSR, "L_LOD_Attribute", "" );
         while ( nRC > zCURSOR_UNCHANGED )
         {
            nRemapFlag = 0;
            lZKey = GetIntegerFromAttribute( vDSR, "L_LOD_Attribute", "ZKey" );
            nRC = SetCursorFirstEntityByInteger( vLOD, "LOD_Attribute", "ZKey", lZKey, "LOD" );
            if ( nRC < 0 )
            {
               // Since there was no match on ZKey, try to find a match on LOD Entity
               // name and ER Attribute name, in case the Attribute had been deleted
               // and recreated.  In this case, re-include the Attribute.
               szName = GetStringFromAttribute( vDSR, "L_LOD_Entity", "Name" );
               nRC = SetCursorFirstEntityByString( vLOD, "LOD_Entity", "Name", szName, "" );
               if ( nRC >= zCURSOR_SET )
               {
                  szName = GetStringFromAttribute( vDSR, "L_ER_Attribute", "Name" );
                  nRC = SetCursorFirstEntityByString( vLOD, "ER_Attribute", "Name", szName, "LOD_Entity" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     nRemapFlag = 0;
                  }
                  else
                  {
                     nRemapFlag = -1;
                  }
               }
               else
               {
                  nRemapFlag = -1;
               }
            }

            if ( nRemapFlag == 0 )
            {
               ExcludeEntity( vDSR, "L_LOD_Attribute", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vDSR, "L_LOD_Attribute", vLOD, "LOD_Attribute", zPOS_AFTER );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 33, vDSR, "L_ER_Attribute", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting List Attribute: ", 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, "Not found in LOD: ", 1, 0, 129 );
               szName = GetVariableFromAttribute( 0, 'S', 9, vLOD, "LOD", "Name", "", 0 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               MessageSend( vSubtask, "AD11012", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               ExcludeEntity( vDSR, "L_LOD_Attribute", zREPOS_NONE );
            }

            nRC = SetCursorNextEntity( vDSR, "L_LOD_Attribute", "" );
         }
      }

      nRC = CheckExistenceOfEntity( vDSR, "ListViewObjRef" );
      if ( nRC == 0 )
      {
         lZKey = GetIntegerFromAttribute( vDSR, "ListViewObjRef", "ZKey" );
         nRC = ActivateMetaOI_ByZKey( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, lZKey, 0 );
         if ( nRC >= 0 )
         {
            RelinkAllSubobjectsForOI( vDSR, "ListViewObjRef", vVOR.getView(), "ViewObjRef" );
            DropMetaOI( vSubtask, vVOR );
         }
         else
         {
            szName = GetStringFromAttribute( vDSR, "ListViewObjRef", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, szName, 0 );
            if ( nRC >= 0 )
            {
               ExcludeEntity( vDSR, "ListViewObjRef", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vDSR, "ListViewObjRef", vVOR, "ViewObjRef", zPOS_AFTER );
               DropMetaOI( vSubtask, vVOR );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 33, vDSR, "ListViewObjRef", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting UIS Include: ", 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, "Not found in LPLR. ", 1, 0, 129 );
               MessageSend( vSubtask, "AD11013", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               DeleteEntity( vDSR, "UIS_Include", zPOS_NEXT );
            }
         }
      }

      nRC = CheckExistenceOfEntity( vDSR, "IncludeViewObjRef" );
      if ( nRC == 0 )
      {
         lZKey = GetIntegerFromAttribute( vDSR, "IncludeViewObjRef", "ZKey" );
         nRC = ActivateMetaOI_ByZKey( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, lZKey, 0 );
         if ( nRC >= 0 )
         {
            nRemapFlag = 0;
            RelinkAllSubobjectsForOI( vDSR, "IncludeViewObjRef", vVOR.getView(), "ViewObjRef" );
            DropMetaOI( vSubtask, vVOR );
         }
         else
         {
            szName = GetStringFromAttribute( vDSR, "IncludeViewObjRef", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, szName, 0 );
            if ( nRC >= 0 )
            {
               ExcludeEntity( vDSR, "IncludeViewObjRef", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vDSR, "IncludeViewObjRef", vVOR, "ViewObjRef", zPOS_AFTER );
               nRemapFlag = 1;
               DropMetaOI( vSubtask, vVOR );
            }
            else
            {
               szName = GetVariableFromAttribute( 0, 'S', 33, vDSR, "IncludeViewObjRef", "Name", "", 0 );
               ZeidonStringCopy( sbMsg, 1, 0, "Deleting UIS Include View: ", 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
               ZeidonStringConcat( sbMsg, 1, 0, "Not found in LPLR. ", 1, 0, 129 );
               MessageSend( vSubtask, "AD11014", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
               DeleteEntity( vDSR, "UIS_Include", zPOS_NEXT );
               nRemapFlag = -1;
            }
         }

         if ( nRemapFlag >= 0 )
         {
            // Now relink the related I_LOD_Attribute entities.
            lZKey = GetIntegerFromAttribute( vDSR, "ListSubObjLOD", "ZKey" );
            ActivateMetaOI_ByZKey( vSubtask, vLOD_List, null, zREFER_LOD_META, zSINGLE, lZKey, 0 );
            nRC = SetCursorFirstEntity( vDSR, "I_LOD_Attribute", "" );
            while ( nRC > zCURSOR_UNCHANGED )
            {
               nRemapFlag = 0;
               lZKey = GetIntegerFromAttribute( vDSR, "I_LOD_Attribute", "ZKey" );
               nRC = SetCursorFirstEntityByInteger( vLOD_List, "LOD_Attribute", "ZKey", lZKey, "LOD" );
               if ( nRC < 0 )
               {
                  // Since there was no match on ZKey, try to find a match on LOD Entity
                  // name and ER Attribute name, in case the Attribute had been deleted
                  // and recreated.  In this case, re-include the Attribute.
                  szName = GetStringFromAttribute( vDSR, "I_LOD_Entity", "Name" );
                  nRC = SetCursorFirstEntityByString( vLOD_List, "LOD_Entity", "Name", szName, "" );
                  if ( nRC >= zCURSOR_SET )
                  {
                     szName = GetStringFromAttribute( vDSR, "I_ER_Attribute", "Name" );
                     nRC = SetCursorFirstEntityByString( vLOD_List, "ER_Attribute", "Name", szName, "LOD_Entity" );
                     if ( nRC >= zCURSOR_SET )
                     {
                        nRemapFlag = 0;
                     }
                     else
                     {
                        nRemapFlag = -1;
                     }
                  }
                  else
                  {
                     nRemapFlag = -1;
                  }
               }

               if ( nRemapFlag == 0 )
               {
                  ExcludeEntity( vDSR, "I_LOD_Attribute", zREPOS_NONE );
                  IncludeSubobjectFromSubobject( vDSR, "I_LOD_Attribute", vLOD_List, "LOD_Attribute", zPOS_AFTER );
               }
               else
               {
                  szName = GetVariableFromAttribute( 0, 'S', 33, vDSR, "I_ER_Attribute", "Name", "", 0 );
                  ZeidonStringCopy( sbMsg, 1, 0, "Deleting Include List Attribute: \n ", 1, 0, 129 );
                  ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
                  ZeidonStringConcat( sbMsg, 1, 0, "Not found in LOD: ", 1, 0, 129 );
                  szName = GetVariableFromAttribute( 0, 'S', 9, vLOD_List, "LOD", "Name", "", 0 );
                  ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
                  MessageSend( vSubtask, "AD11015", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
                  ExcludeEntity( vDSR, "I_LOD_Attribute", zREPOS_NONE );
               }

               nRC = SetCursorNextEntity( vDSR, "I_LOD_Attribute", "" );
            }

            DropView( vLOD_List );
         }
      }

      return 0;
   }

   private int oTZADCSDO_DS_RelinkDelete( View vDialogSpec, View vSubtask )
   {
      zVIEW  vLOD = new zVIEW();
      zVIEW  vVOR = new zVIEW();
      int    nRemapFlag = 0;
      StringBuilder sbMsg = new StringBuilder();
      String szName;
      int    lZKey;
      int    nRC;

      // Get VOR and LOD for this Dialog Spec and relink.
      // Try to activate first by ZKey and then by Name.  The latter would
      // occur if the VOR or LOD had been deleted and readded under a new ZKey.
      lZKey = GetIntegerFromAttribute( vDialogSpec, "UIS_ViewObjRef", "ZKey" );
      nRC = ActivateMetaOI_ByZKey( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, lZKey, 0 );
      if ( nRC < 0 )
      {
         szName = GetStringFromAttribute( vDialogSpec, "UIS_ViewObjRef", "Name" );
         nRC = ActivateMetaOI_ByName( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, szName, 0 );
         if ( nRC < 0 )
         {
            szName = GetVariableFromAttribute( 0, 'S', 33, vDialogSpec, "UIS_ViewObjRef", "Name", "", 0 );
            ZeidonStringCopy( sbMsg, 1, 0, "Skipping Relink for missing View", 1, 0, 129 );
            ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
            MessageSend( vSubtask, "AD11016", "Dialog Spec Relink", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
            return( ( -1 ) );
         }
         else
         {
            nRemapFlag = 1;
         }
      }
      else
      {
         nRemapFlag = 0;
      }

      lZKey = GetIntegerFromAttribute( vDialogSpec, "UIS_LOD", "ZKey" );
      nRC = ActivateMetaOI_ByZKey( vSubtask, vLOD, null, zREFER_LOD_META, zSINGLE, lZKey, 0 );
      if ( nRC < 0 )
      {
         szName = GetStringFromAttribute( vDialogSpec, "UIS_LOD", "Name" );
         nRC = ActivateMetaOI_ByName( vSubtask, vLOD, null, zREFER_LOD_META, zSINGLE, szName, 0 );
         if ( nRC < 0 )
         {
            szName = GetVariableFromAttribute( 0, 'S', 9, vDialogSpec, "UIS_LOD", "Name", "", 0 );
            ZeidonStringCopy( sbMsg, 1, 0, "Skipping Relink for missing LOD", 1, 0, 129 );
            ZeidonStringConcat( sbMsg, 1, 0, szName, 1, 0, 129 );
            MessageSend( vSubtask, "AD11017", "Dialog Spec Relink", sbMsg, zMSGQ_OBJECT_CONSTRAINT_WARNING, 0 );
            return( ( -1 ) );
         }
         else
         {
            nRemapFlag = 1;
         }
      }
      else
      {
         nRemapFlag = 0;
      }

      // Only Relink if both VOR and LOD were activated by ZKey.
      if ( nRemapFlag == 0 )
      {
         RelinkAllSubobjectsForOI( vDialogSpec, "UIS_ViewObjRef", vVOR.getView(), "ViewObjRef" );
      }

      // Relink ListVOR, if it exists.
      nRC = CheckExistenceOfEntity( vDialogSpec, "ListVOR" );
      if ( nRC == 0 )
      {
         lZKey = GetIntegerFromAttribute( vDialogSpec, "ListVOR", "ZKey" );
         nRC = ActivateMetaOI_ByZKey( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, lZKey, 0 );
         if ( nRC < 0 )
         {
            szName = GetStringFromAttribute( vDialogSpec, "ListVOR", "Name" );
            nRC = ActivateMetaOI_ByName( vSubtask, vVOR, null, zREFER_VOR_META, zSINGLE, szName, 0 );
            if ( nRC < 0 )
            {
               ExcludeEntity( vDialogSpec, "ListVOR", zREPOS_NONE );
            }
            else
            {
               ExcludeEntity( vDialogSpec, "ListVOR", zREPOS_NONE );
               IncludeSubobjectFromSubobject( vDialogSpec, "ListVOR", vVOR, "ViewObjRef", zPOS_AFTER );
            }
         }
         else
         {
            RelinkAllSubobjectsForOI( vDialogSpec, "ListVOR", vVOR.getView(), "ViewObjRef" );
         }
      }

      // Process each UIS_Entity recursively
      oTZADCSDO_DS_EntityRelink( vDialogSpec, vLOD, vSubtask );

      // Relink the LOD_Attributes.  When RelinkInstanceToInstance has a version that
      // allows resetting the include flags, this code would be replaced by
      // RelinkInstanceToInstance calls in EntityRelinkDelete.
      RelinkAllSubobjectsForOI( vDialogSpec, "M_LOD_Attribute", vLOD.getView(), "LOD_Attribute" );
      RelinkAllSubobjectsForOI( vDialogSpec, "L_LOD_Attribute", vLOD.getView(), "LOD_Attribute" );
      return 0;
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfTZADCSDO_PostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfTZADCSDO_PostActivate( View vSubtask, StringBuilder sbReturnSyncDate, View vCM_Subtask,
                                        View vTZADCSDO, View vTZCMLPLO, int nPhaseCtl, boolean bCheckin )
   {
      zVIEW    vActiveMetas = new zVIEW();

      // The UI Spec has been brought into memory, now go through all instances
      // already in memory on which it is dependent and link up to those
      // instances.  Also, go through all instance which are dependent on it
      // and link up to those instances.
      GetViewByName( vActiveMetas, "OpenCM_Metas",   // why in the world
                     vCM_Subtask, zLEVEL_SUBTASK );  // are we doing this? dks ... 2004.09.16

      /////////////////////////////////////////////
      // Post Activate PHASE 1: Link up to all object instances in memory
      // which we depend on
      /////////////////////////////////////////////
      // For each LOD active in memory, relink the UI Spec to the LOD

      oTZADCSDO_DS_RelinkDelete( vTZADCSDO, vSubtask );
      return 0;
   /*
      zVIEW    vMeta = new zVIEW();
      zVIEW    vWkTZADCSDO = new zVIEW();
      zVIEW    vTZWDVORO = new zVIEW();
      zVIEW    vCM_List = new zVIEW();
      String   szName;
      int      lZKey;
      int      nRelNbr;
      int      nRC;

      nRelNbr = 0;
      while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas,
                                                zREFER_LOD_META, nRelNbr )) > 0 )
      {
         if ( isValid( vMeta ) )
         {
            // We have retrieved a view to an active LOD, link up the
            // UI Spec to the LOD
            RelinkAllSubobjectsForOI( vTZADCSDO, "LOD_Entity", vMeta.getView(), "LOD_Entity" );
            RelinkAllSubobjectsForOI( vTZADCSDO, "M_LOD_Attribute", vMeta.getView(), "LOD_Attribute" );
            RelinkAllSubobjectsForOI( vTZADCSDO, "L_LOD_Attribute", vMeta.getView(), "LOD_Attribute" );
         }
      }

      // For each VOR active in memory, relink the UI Spec to the VOR
      nRelNbr = 0;
      while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas,
                                                zREFER_VOR_META, nRelNbr ) ) > 0 )
      {
         if ( isValid( vMeta ) )
         {
            // We have retrieved a view to an active VOR, link up the
            // UI Spec to the VOR
            RelinkAllSubobjectsForOI( vTZADCSDO, "SubObjViewObjRef", vMeta.getView(), "ViewObjRef" );
            RelinkAllSubobjectsForOI( vTZADCSDO, "UIS_ViewObjRef", vMeta.getView(), "ViewObjRef" );
            RelinkAllSubobjectsForOI( vTZADCSDO, "ListVOR", vMeta.getView(), "ViewObjRef" );
         }
      }

      // For each PE active in memory, relink the PE to the UIS
      nRelNbr = 0;
      while ( (nRelNbr = fnGetActiveMetaByType( vMeta, vCM_Subtask, vActiveMetas,
                                                zREFER_PENV_META, nRelNbr )) > 0 )
      {
         if ( isValid( vMeta ) )
         {
            // We have retrieved a view to an active View Object Ref,
            // link up the Dialog to the View Object Ref
            RelinkAllSubobjectsForOI( vTZADCSDO, "WndStyle", vMeta.getView(), "WindowStyle" );
            RelinkAllSubobjectsForOI( vTZADCSDO, "DfltWndStyle", vMeta.getView(), "WindowStyle" );
         }
      }

      // Bypass Phase 2 if this is an activation of a SOURCE Type Meta
      /////////////////////////////////////////////
      // Post Activate PHASE 2: Link up to all object instances in memory
      // which depend on us
      /////////////////////////////////////////////

      /////////////////////////////////////////////
      // Post Activate PHASE 3: For all meta types we depend on, issue an
      // activate for all VOR instances used by this UIS
      /////////////////////////////////////////////

      // For each VOR used by this UIS activate it and inturn activate
      // the corresponding LOD
      if ( (nRC = RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_VOR_META )) < 0 )
      {
         return 0;
      }

      CreateViewFromViewForTask( vWkTZADCSDO, vTZADCSDO, "" );
      nRC = SetCursorFirstEntity( vWkTZADCSDO, "UIS_ViewObjRef", "" );
      while ( nRC >= zCURSOR_SET )
      {
         lZKey = GetIntegerFromAttribute( vWkTZADCSDO, "UIS_ViewObjRef", "ZKey" );
         if ( ActivateMetaOI_ByZKey( vSubtask, vTZWDVORO, vCM_List, zREFER_VOR_META,
                                     zSINGLE | zLEVEL_APPLICATION, lZKey, zCURRENT_OI ) == 1 )
         {
            DropView( vTZWDVORO );
         }
         else
         {
            StringBuilder sbERR_Msg = new StringBuilder();
            zstrcpy( sbERR_Msg, "Unable to activate Registered View ( file " + zltoxa( lZKey ) );
            szName = GetStringFromAttribute( vTZWDVORO, "ViewObjRef", "Name" );
            zstrcat( sbERR_Msg, szName );
            zstrcat( sbERR_Msg, "' for UI Specification '" );
            szName = GetStringFromAttribute( vWkTZADCSDO, "UI_Spec", "Name" );
            zstrcat( sbERR_Msg, szName );
            zstrcat( sbERR_Msg, "'.\n\nA rebuild of the Meta List may be needed." );
            MessageSend( vSubtask, "CM00903", "Configuration Management",
                         sbERR_Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         }

         nRC = SetCursorNextEntity( vWkTZADCSDO, "UIS_ViewObjRef", "" );
      }

      DropView( vCM_List );
      DropView( vWkTZADCSDO );
      return 0;
    */
   }

   //////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:  cfPostActivate
   //
   //////////////////////////////////////////////////////////////////////////////
   private int cfPostActivate( View vSubtask, int nType, StringBuilder sbSyncDate, View vZeidonCM,
                               View vMOI_View, View vTaskLPLR, int PhaseCtl, boolean bCheckin )
   {
      int nRC;

      switch ( nType )
      {
         case  zSOURCE_LOD_META:
         case  zREFER_LOD_META:
            nRC = cfTZZOLODO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_DIALOG_META:
         case  zREFER_DIALOG_META:
            nRC = cfTZWDLGSO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_ERD_META:
         case  zREFER_ERD_META:
            nRC = cfTZEREMDO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_DTE_META:
         case  zREFER_DTE_META:
            nRC = cfTZTENVRO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_SA_META:
         case  zREFER_SA_META:
            nRC = cfTZERSASO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_UIS_META:
         case  zREFER_UIS_META:
            nRC = cfTZADCSDO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_VOR_META:
         case  zREFER_VOR_META:
            nRC = cfTZWDVORO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_PENV_META:
         case  zREFER_PENV_META:
            nRC = cfTZPESRCO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         case  zSOURCE_REPORT_META:
         case  zREFER_REPORT_META:
            nRC = cfTZRPSRCO_PostActivate( vSubtask, sbSyncDate, vZeidonCM,
                                           vMOI_View, vTaskLPLR, PhaseCtl, bCheckin );
            break;

         default:
            nRC = 0;
            break;
      }

      return nRC;
   }

   //./ ADD NAME=InitializeLPLR
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: InitializeLPLR
   //
   //  PURPOSE:    Called at the start of all Zeidon Tools. This will load
   //              the WorkStation and LPLR objects.  The WorkStation object must exist.
   //              If the LPLR Name is NULL, then the current Default LPLR is used.
   //              Temporarily, the LPLR will be rebuilt from the \BIN\SYS
   //              directories, if not found. The LPLR file is TZCMLPLR.XLP.
   //
   //  PARAMETERS: vSubtask - A valid Subtask view
   //              LPLR_Name - The LPLR Name or NULL for Default
   //
   //  RETURNS:    1 - LPL initialized successfully
   //             -1 - Error encountered during LPL initialization
   //
   /////////////////////////////////////////////////////////////////////////////
   public int InitializeLPLR( View view, String pchLPLR_NameIn ) throws IOException
   {
      int    lTaskUseCnt;
      zVIEW  vZeidonCM = new zVIEW();
      zVIEW  vActiveMetas = new zVIEW();
      zVIEW  vLPLR = new zVIEW();
      zVIEW  WKS_View = new zVIEW();
      zVIEW  vTaskMetas = new zVIEW();
      int    ulZKey;
      int    hFile;
      StringBuilder sb = new StringBuilder();
      String szLPLR_Name;
      StringBuilder sbLPLR_FileName = new StringBuilder();
      String szLPLR_Spec;
      int    nRC;

      // Check to make sure that the Workstation Administration tool is not up.
      int subtask = DriverApplication.FindDialogInApplicationList( view, "TZCMLPLD", true );
      if ( subtask >= 0 )
      {
         // We don't allow the Workstation Admin tool, TZCMLPLD, to be running, unless it is the current task.
         Task t = DriverApplication.GetTaskFromSubtask( view, subtask );
         if ( t != view.getTask() )
         {
            MessageSend( view, "CM00299", "Configuration Management",
                         "Regular tools are not allowed to run while Workstation Administration is running.",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         // SetWindowActionBehavior( vSubtask, zWAB_ExitDialogTask, 0, 0 );
            return -1;
         }
      }

      GetViewByName( vZeidonCM, "ZeidonCM", view, zLEVEL_APPLICATION );
      if ( isValid( vZeidonCM ) == false )
      {
         if ( SfCreateSystemSubtask( vZeidonCM, view, "Zeidon_Tools" ) == 0 )
         {
            SetNameForView( vZeidonCM, "ZeidonCM", view, zLEVEL_APPLICATION );
         }

         SetViewFlags( vZeidonCM, zVF_MESSAGEONDROP );
      }

      // Create a work LPLR entity to keep track of ActiveMetas.
      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( vActiveMetas ) == false )
      {
         ActivateEmptyObjectInstance( vActiveMetas, "TZCMLPLO", vZeidonCM,
                                      zMULTIPLE | zLEVEL_APPLICATION );
         SetNameForView( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );
      }

      szLPLR_Name = fnGetTaskOI_ListName( view );  // _CM.taskID
      GetViewByName( vTaskMetas, szLPLR_Name, vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( vTaskMetas ) == false )
      {
         if ( SfCreateSystemSubtask( vTaskMetas, vZeidonCM, "" ) == 0 )
         {
            SetNameForView( vTaskMetas, szLPLR_Name, vZeidonCM, zLEVEL_SUBTASK );
         }
      }

      GetViewByName( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( WKS_View ) == false )  // view isn't there
      {
         // TZCMWKS8.POR
         if ( ActivateWorkStation( WKS_View, vZeidonCM, zSINGLE | zLEVEL_APPLICATION | zIGNORE_ATTRIB_ERRORS ) != 0 )
         {
            String szErrMsg = GetStringFromAttribute( WKS_View, "LPLR", "Name" );
            MessageSend( view, "CM00402", "Configuration Management",
                         "Unable to open the RepositoryClient File - " + szErrMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            zstrcpy( sb, "If you are a new user use the LPLR Dialog" );
            zstrcat( sb, "\nto initialize the Repository Client. Otherwise," );
            zstrcat( sb, "\npossible reasons for your being unable to open" );
            zstrcat( sb, "\nthe Repository Client are:\n" );
            zstrcat( sb, "\n1: The Repository Client File exists but" );
            zstrcat( sb, "\n   cannot be activated." );
            zstrcat( sb, "\n2: The Repository Client File exists but" );
            zstrcat( sb, "\n   is not in the directory identified by the" );
            zstrcat( sb, "\n      ZEIDON environment variable." );
            MessageSend( view, "CM00403", "Configuration Management",
                         sb.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         SetNameForView( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK );
      }

      if ( pchLPLR_NameIn.compareTo( "CM New User" ) == 0 )
      {
         return 0;
      }

      WKS_View.logObjectInstance( );
      if ( pchLPLR_NameIn.isEmpty() == false &&
           SetCursorFirstEntityByString( WKS_View, "LPLR", "Name", pchLPLR_NameIn, "" ) == zCURSOR_SET )
      {
         // nothing to do here
         DisplayEntityInstance( WKS_View, "LPLR" );
      }
      else
      {
         ulZKey = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "DefaultLPLR_ZKey" );
         TraceLineI( "Default LPLR ZKey: ", ulZKey );
         if ( SetCursorFirstEntityByInteger( WKS_View, "LPLR", "ZKey", ulZKey, "" ) != zCURSOR_SET ) // but it is zCURSOR_SET ... so skip
         {
            if ( SetCursorFirstEntity( WKS_View, "LPLR", "" ) == zCURSOR_SET )
            {
               SetAttributeFromAttribute( WKS_View, "RepositoryClient", "DefaultLPLR_ZKey", WKS_View, "LPLR", "ZKey" );
               CommitWorkstation( WKS_View );
            }
            else
            {
               zstrcpy( sb, "Unable to ActivateDefaultLPL because" );
               zstrcat( sb, " no LPL Releases defined for Client." );
               zstrcat( sb, "\nUse LPLR Dialog to define LPLR's" );
               MessageSend( view, "CM00406", "Configuration Management",
                            sb.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }

            DisplayEntityInstance( WKS_View, "LPLR" );
         }
      }

      szLPLR_Name = GetStringFromAttribute( WKS_View, "LPLR", "Name" );  // this better be the same name as pchLPLR_NameIn unless
                                                                         // pchLPLR_NameIn is empty
      GetStringFromAttribute( sb, WKS_View, "LPLR", "ExecDir" );  // borrow sb for a sec...
      SysConvertEnvironmentString( sb, sb.toString() );
      SysAppendcDirSep( sb );
      zstrncpy( sbLPLR_FileName, szLPLR_Name, 8 );
      TruncateName8( sbLPLR_FileName );
      zstrcat( sb, sbLPLR_FileName.toString() );
      zstrcat( sb, ".XLP" );  // epamms.XLP
      szLPLR_Spec = sb.toString();
      GetViewByName( vLPLR, szLPLR_Name, vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( vLPLR ) == false )  // view isn't there
      {
         hFile = SysOpenFile( view, szLPLR_Spec, COREFILE_READ );
         if ( hFile < 0 )
         {
            nRC = -1;
         }
         else
         {
            SysCloseFile( view, hFile, 0 );
            nRC = ActivateOI_FromFile( vLPLR, "TZCMLPLO", vZeidonCM,
                                       szLPLR_Spec, zSINGLE | zLEVEL_APPLICATION | zIGNORE_ERRORS );

            // Make sure Operation lists are alphabetized.
            SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", 2002, "" );
            OrderEntityForView( vLPLR, "W_MetaDef", "Name A" );
            SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", 2, "" );
            OrderEntityForView( vLPLR, "W_MetaDef", "Name A" );
          /*
            // *** TEMPORARY CODE ***
            // The following code is temporary and makes sure there is a Report
            // W_MetaType in the LPLR.  It was created 9/3/96 and only needs to
            // exist until Report is added as an official meta.
            if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", zSOURCE_REPORT_META, "" ) < zCURSOR_SET )
            {
               SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", 14, "" );
               CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
               SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", zSOURCE_REPORT_META );
            }

            if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", zREFER_REPORT_META, "" ) < zCURSOR_SET )
            {
               SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", 2014, "" );
               CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
               SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", zREFER_REPORT_META );
            }

            // *** TEMPORARY CODE ***
            // The following code is temporary and makes sure there is an XSLT
            // W_MetaType in the LPLR.  It was created 2012.06.14 and only needs to
            // exist until XSLT is added as an official meta.
            if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", zSOURCE_XSLT_META, "" ) < zCURSOR_SET )
            {
               SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", 14, "" );
               CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
               SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", zSOURCE_XSLT_META );
            }

            if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", zREFER_XSLT_META, "" ) < zCURSOR_SET )
            {
               SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", 2014, "" );
               CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
               SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", zREFER_XSLT_META );
            }
          */
         }

         if ( nRC == 0 )
         {
            SetNameForView( vLPLR, "TaskLPLR", view, zLEVEL_TASK );
         }
         else
         {
            zstrcpy( sb, "The default Project File " );
            zstrcat( sb, szLPLR_Spec );
            zstrcat( sb, "\nwas not found! Please check the Project Information." );
            MessageSend( view, "CM00406", "Configuration Management", sb.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         SetAttributeFromInteger( vLPLR, "LPLR", "TaskUseCount", 0 );
         SetNameForView( vLPLR, szLPLR_Name, vZeidonCM, zLEVEL_SUBTASK );

         // Create a work LPLR entity to keep track of ActiveMetas.
         if ( CheckExistenceOfEntity( vActiveMetas, "LPLR" ) != zCURSOR_SET )
         {
            CreateEntity( vActiveMetas, "LPLR", zPOS_AFTER );
            SetAttributeFromAttribute( vActiveMetas, "LPLR", "Name", WKS_View, "LPLR", "Name" );
         }
         else
         {
            if ( SetCursorFirstEntityByString( vActiveMetas, "LPLR", "Name", szLPLR_Name, "" ) != zCURSOR_SET )
            {
               CreateEntity( vActiveMetas, "LPLR", zPOS_AFTER );
               SetAttributeFromAttribute( vActiveMetas, "LPLR", "Name", WKS_View, "LPLR", "Name" );
            }
            else
            {
               fnCleanupActiveMetas( vActiveMetas, vZeidonCM );
            }
         }
      }
      else
      {
         if ( SetCursorFirstEntityByString( vActiveMetas, "LPLR", "Name", szLPLR_Name, "" ) >= zCURSOR_SET )
         {
            fnCleanupActiveMetas( vActiveMetas, vZeidonCM );
         }

         SetNameForView( vLPLR, "TaskLPLR", view, zLEVEL_TASK );
      }

      lTaskUseCnt = GetIntegerFromAttribute( vLPLR, "LPLR", "TaskUseCount" );
      lTaskUseCnt++;
      SetAttributeFromInteger( vLPLR, "LPLR", "TaskUseCount", lTaskUseCnt );

      return( 1 );
   }

   //./ ADD NAME=InitializeDefaultLPL
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: InitializeDefaultLPL
   //
   //  PURPOSE:    Calls InitializeLPLR with NULL LPLR Name.  This just for
   //              compatibility with old Code!!!!!!!!!!!!!!!!!!!!
   //
   //  PARAMETERS: vSubtask - A valid  Subtask view
   //
   //  RETURNS:    1 - LPL initialized successfully
   //             -1 - Error encountered during LPL initialization
   //
   /////////////////////////////////////////////////////////////////////////////
   public int InitializeDefaultLPL( View  vSubtask ) throws IOException
   {
      return( InitializeLPLR( vSubtask, "" ) );
   }

   public int CommitLPLR( View vTZCMLPLO )
   {
      StringBuilder sbFileName = new StringBuilder();
      String   szWork;
      StringBuilder sbLPLR_Name = new StringBuilder();
      int      k;

      szWork = GetStringFromAttribute( vTZCMLPLO, "LPLR", "ExecDir" );
      SysConvertEnvironmentString( sbFileName, szWork );
      SysAppendcDirSep( sbFileName );
      GetStringFromAttribute( sbLPLR_Name, vTZCMLPLO, "LPLR", "Name" );
      if ( sbLPLR_Name.length() > 8 )
      {
         sbLPLR_Name.setLength( 8 );
      }

      for ( k = 0; k < sbLPLR_Name.length(); k++ )
      {
         if ( sbLPLR_Name.charAt( k ) == ' ' )
         {
            sbLPLR_Name.setCharAt( k, '_' );
         }
      }

      zstrcat( sbFileName, sbLPLR_Name.toString() );
      zstrcat( sbFileName, ".XLP" );
      zgSortEntityWithinParent( zASCENDING, vTZCMLPLO, "W_MetaType", "Type", "" );
      return( CommitOI_ToFile( vTZCMLPLO, sbFileName.toString(), zSINGLE ) );
   // return( CommitOI_ToFile( vTZCMLPLO, sbFileName, zBINARY | zSINGLE | zINCREMENTAL ) );
   }

   //./ ADD NAME=TerminateLPLR
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: TerminateLPLR
   //
   //  PURPOSE:    Called at the end of all Zeidon Tools. This will drop
   //              the TaskLPLR view and if it the only remaining one it
   //              drop the LPLR Object Instance.
   //
   //  PARAMETERS: vSubtask - A valid  Subtask view
   //
   //  RETURNS:    1 - LPLR terminated successfully
   //             -1 - Error encountered during LPLR termination
   //
   /////////////////////////////////////////////////////////////////////////////
   public int TerminateLPLR( View  vSubtask )
   {
      zVIEW  WorkView = new zVIEW();
      zVIEW  vLPLR = new zVIEW();
      zVIEW  vZeidonCM = new zVIEW();
      zVIEW  vTaskOI = new zVIEW();
      zVIEW  vTaskMetas = new zVIEW();
      zVIEW  vActiveMetas = new zVIEW();
      StringBuilder sbViewName = new StringBuilder();
      String viewName;
      int    lTaskId;
      int    lTaskUseCnt;
      int    nRC;

      GetViewByName( vLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
      if ( isValid( vLPLR ) == false )  // View isn't there
      {
         TraceLineS( "(tzcmoprs) TerminateLPLR: ", "Unable to drop the Project!" );
         return -1;
      }

      // Clean up the CM views for the Task activated Metas.
      GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION );
      if ( isValid( vZeidonCM ) == false )
      {
         MessageSend( vSubtask, "CM00410", "Configuration Management",
                      "Unable to locate ZeidonCM view!",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );
      GetStringFromAttribute( sbViewName, vLPLR, "LPLR", "Name" );
      if ( SetCursorFirstEntityByString( vActiveMetas, "LPLR", "Name", sbViewName.toString(), "" ) < zCURSOR_SET )
      {
         StringBuilder  szMsg = new StringBuilder( );

         // It is possible that this problem occurs because the ZKey in
         // TZCMWKS8.POR for the specified LPLR does not match the ZKey
         // in the .XLP for the LPLR.  If that's the case, fix the ZKey
         // in TZCMWKS8.POR!
         zstrcpy( szMsg, "Unable to locate Project in CM ACTIVE view: " );
         zstrcat( szMsg, sbViewName );
         MessageSend( vSubtask, "CM00411", "Configuration Management",
                      szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      lTaskId = Integer.decode( SysGetTaskFromView( vSubtask ).getTaskId() );
      nRC = SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskId, "LPLR" );
      while ( (nRC == zCURSOR_SET) || (nRC == zCURSOR_SET_NEWPARENT) )
      {
         viewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" );
         GetViewByName( WorkView, viewName + ".u", vZeidonCM, zLEVEL_SUBTASK );
         if ( isValid( WorkView ) )
         {
            DropObjectInstance( WorkView );
         }

         if ( GetViewByName( WorkView, viewName +  ".r", vZeidonCM, zLEVEL_SUBTASK ) > 0 )
         {
            SetAttributeFromString( vActiveMetas, "W_MetaDef", "TaskID", "" );
         }
         else
         {
            ExcludeEntity( vActiveMetas, "W_MetaDef", zREPOS_NONE );
         }

         nRC = SetCursorNextEntityByInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskId, "LPLR" );
      }

      viewName = fnGetTaskOI_ListName( vSubtask );
      GetViewByName( vTaskMetas, viewName, vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( vTaskMetas ) )
      {
         nRC = DriverApplication.SfGetFirstNamedView( vTaskOI, sbViewName, vTaskMetas, zLEVEL_SUBTASK );
         while( nRC > 0 )
         {
            DropView( vTaskOI );
            nRC = DriverApplication.SfGetFirstNamedView( vTaskOI, sbViewName, vTaskMetas, zLEVEL_SUBTASK );
         }

         SfDropSubtask( vTaskMetas, 0 );
      }

      lTaskUseCnt = GetIntegerFromAttribute( vLPLR, "LPLR", "TaskUseCount" );
      if ( lTaskUseCnt > 0 )
      {
         lTaskUseCnt--;
      }

      if ( lTaskUseCnt < 1 )
      {
      // DropObjectInstance( vLPLR );
         SetAttributeFromInteger( vLPLR, "LPLR", "TaskUseCount", lTaskUseCnt );
         nRC = SetCursorFirstEntity( vActiveMetas, "W_MetaDef", "LPLR" );
         while ( nRC == zCURSOR_SET )
         {
            viewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" );
            GetViewByName( WorkView, viewName + ".u", vZeidonCM, zLEVEL_SUBTASK );
            if ( isValid( WorkView ) )
            {
               DropObjectInstance( WorkView );
               ExcludeEntity( vActiveMetas, "W_MetaDef", zREPOS_NEXT );
            }
            else
            {
               SetCursorNextEntity( vActiveMetas, "W_MetaDef", "LPLR" );
            }
         }

   //    DeleteEntity( vActiveMetas, "LPLR", zREPOS_NONE );
      }
      else
      {
         SetAttributeFromInteger( vLPLR, "LPLR", "TaskUseCount", lTaskUseCnt );
      }

      if ( ObjectInstanceUpdatedFromFile( vLPLR ) == 1 )
      {
         CommitLPLR( vLPLR );
      }

      return( 1 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfCreateSubtask
   //
   //  PURPOSE:    To create and application subtask under a System task.
   //
   //  PARAMETERS: pvReturnSubtask - returned view to new subtask
   //              lpView          - Task view.  If cpcAppName is NULL, then the
   //                                new subtask uses the app name from lpView.
   //              cpcAppName      - An optional application name
   //
   //
   //  DESCRIPTION:  The subtask is created for an application and a view
   //                to the subtask is returned which has no object type
   //                associated with it. If an application name is passed,
   //                then the new subtask is created for the application name
   //                passed.  If the application name is not passed
   //                (i.e. NULL), then the new subtask is created for the
   //                application associated with the View passed. If BOTH the
   //                application name and view passed are NULL, then the
   //                application associated with the new subtask is the
   //                application initially defined in the
   //                RegisterZeidonApplication call.
   //
   //  RETURNS:    0           - subtask created successfully
   //              zCALL_ERROR - Error on call
   //
   /////////////////////////////////////////////////////////////////////////////
   private int SfCreateSubtask( zVIEW pvReturnSubtask, View lpView, String cpcAppName )
   {
      Task      t = lpView.getTask();
      Application app;
      int         nRC;

      if ( cpcAppName.isEmpty() == false )
      {
         app = fnCreateApp( t, cpcAppName );
         if ( app == null )
         {
            return( zCALL_ERROR );
         }
      }
      else
      {
         app = lpView.getApplication();
      }

      // Use the current task in the call to fnCreateSubtask
      pvReturnSubtask.setView( fnCreateSubtask( t, app ) );
      if ( isValid( pvReturnSubtask ) )
      {
         // When possibly creating a new App struct, we want to preload the
         // Message Object Definition.  The assumption that we are making
         // below is that the first object definition that is put on the
         // app struct will be the message object definition, so if there
         // is already a pointer in the app struct, to a LodDef chain, then
         // the message object must already be preloaded. (We Hope!!!)
         pvReturnSubtask.getApplication().getLodDef( t, MESSAGE_OBJECT_NAME );
         nRC = 0;
      }
      else
      {
         nRC = zCALL_ERROR;
      }

      return( nRC );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfCreateSystemSubtask
   //
   //  PURPOSE:    To create an application subtask under a System task.
   //
   //  PARAMETERS: pvReturnSubtask - returned view to new subtask
   //              lpView          - a valid View
   //              cpcAppName      - an optional application name
   //
   //
   //  DESCRIPTION:  The subtask is created for an application and a view
   //                to the subtask is returned which has no object type
   //                associated with it. If an application name is passed,
   //                then the new subtask is created for the application name
   //                passed.  If the application name is not passed
   //                (i.e. NULL), then the new subtask is created for the
   //                application associated with the View passed. If BOTH the
   //                application name and view passed are NULL, then the
   //                application associated with the new subtask is the
   //                application initially defined in the
   //                RegisterZeidonApplication call.
   //
   //  RETURNS:    0           - subtask created successfully
   //              zCALL_ERROR - Error on call
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SfCreateSystemSubtask( zVIEW pvReturnSubtask, View lpView, String cpcAppName )
   {
      Task        lpCurrentTask;
      View        vNewSubtaskView;
      Application lpApp;
      int         nRC;

      // If task not active or disabled, or view invalid, return zCALL_ERROR.
      if ( isValid( lpView ) == false )
      {
         return zCALL_ERROR;
      }

      lpCurrentTask = lpView.getTask();

      // We need to lock the anchor mutex because we might create a new app.
   // zLOCK_MUTEX( zMUTEX_ANCHORBLOCK );

      if ( StringUtils.isBlank( cpcAppName ) == false )
      {
         lpApp = fnCreateApp( lpCurrentTask, cpcAppName );
         if ( lpApp == null )
         {
         // zUNLOCK_MUTEX( zMUTEX_ANCHORBLOCK );
            return zCALL_ERROR;
         }
      }
      else
      {
         lpApp = lpView.getApplication();
      }

      // Use the system task in the call to fnCreateSubtask
      vNewSubtaskView = fnCreateSubtask( lpCurrentTask.getSystemTask(), lpApp );

   // zUNLOCK_MUTEX( zMUTEX_ANCHORBLOCK );

      if ( isValid( vNewSubtaskView ) )
      {
         pvReturnSubtask.setView( vNewSubtaskView );
         nRC = 0;
      }
      else
      {
         pvReturnSubtask.setView( null );
         nRC = zCALL_ERROR;
      }

      return( nRC );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:   fnCreateSubtask
   //
   /////////////////////////////////////////////////////////////////////////////
   private View fnCreateSubtask( Task task, Application app )
   {
      View vSubtask = null;

      // application task is invalid.
      if ( task != null && app != null )
      {
         vSubtask = task.activateEmptyObjectInstance( MESSAGE_OBJECT_NAME, app );
         if ( isValid( vSubtask ) )
         {
            DriverApplication.CreateSubtaskForView( vSubtask );
         }
      }

      return vSubtask;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SfDropSubtask
   //
   //  PURPOSE:    To delete an existing subtask
   //
   //  PARAMETERS: vSubtask   - the subtask to delete
   //              nCleanup   - 0 Bypass cleanup of Views
   //                           1 For the Task of the subtask, drop all
   //                             views that are NOT NAMED or NOT LOCKED.
   //                           2 For the Task of the subtask, drop all
   //                             views that are NOT NAMED or NOT LOCKED AND
   //                             are also part of the same application.
   //
   //  RETURNS:    0           - Subtask delete successful
   //              zCALL_ERROR - subtask is invalid
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SfDropSubtask( View vSubtask, int nCleanup )
   {
      if ( isValid( vSubtask ) )
      {
         vSubtask.drop();
         return 0;
      }

      return zCALL_ERROR;
   }

   //./ ADD NAME=fnCreateApp
   // Source Module=kzoeapaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:   fnCreateApp
   //
   //  PURPOSE:    To create application information for subsequent
   //              processing by the task.
   //
   //  PARAMETERS: lpView       - A required view passed to error handling
   //              szAppName    - Application name to load
   //
   //  We don't use a mutex here because every function that calls fnCreateApp
   //  locks the ANCHORBLOCK mutex.  The functions that call fnCreateApp are:
   //          InitializeAnchorBlock
   //          RegisterZeidonApplication
   //          SfCreateSubtask
   //          SfCreateSystemSubtask
   //
   //  RETURNS:    Application - Application pointer
   //              0     - Application creation unsuccessful
   //
   /////////////////////////////////////////////////////////////////////////////
   private Application fnCreateApp( Task lpTask, String cpcAppName )
   {
      Application  lpApp = lpTask.getApplication( cpcAppName );
      return lpApp;
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private int fnGetDirectorySpec( View vSubtask, StringBuilder sbDirectorySpec, int lType )
   {
      zVIEW   vTaskLPLR = new zVIEW();
      String  szWork;

      sbDirectorySpec.setLength( 0 );
   // MessageBox( 0, "TaskLPLR", "fnGetDirectorySpec", MB_OK );
      if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) <= 0 )
      {
         return -1;
      }

      if ( (lType < 0) ||
           ((lType > zSOURCE_MAX_META) && (lType < 1000)) ||
           ((lType > 1000) && (lType < 2000)) ||
           (lType > zREFER_MAX_META) )
      {
         MessageSend( vSubtask, "CM00401", "Configuration Management",
                      "Invalid Zeidon Type passed to CM Operation",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      szWork = GetStringFromAttribute( vTaskLPLR, "LPLR", "MetaSrcDir" );
      SysConvertEnvironmentString( sbDirectorySpec, szWork );
      SysAppendcDirSep( sbDirectorySpec );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: RetrieveViewForMetaList
   //
   //  PURPOSE:    Called to return the View of a subobject of the current LPL.
   //              The View can then be used to list all metas of a particular
   //              type (such as dialogs, LOD's, Model's, etc.)  When the View
   //              is returned it is the developers responsibility to provide
   //              a name for the view.
   //
   //  PARAMETERS: lpListView - a pointer to a View to be returned
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    1 - View retrieved successfully
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int RetrieveViewForMetaList( View vSubtask, zVIEW lpListView, int nType )
   {
      zVIEW vLPLR = new zVIEW();
      zVIEW vZeidonCM = new zVIEW();
      zVIEW vTaskMetas = new zVIEW();
      StringBuilder sbName = new StringBuilder();
      int   nEntityType;

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      GetViewByName( vLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
      if ( isValid( vLPLR ) )  // View is there
      {
         if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) < 0 )
         {
            MessageSend( vSubtask, "CM00412", "Configuration Management",
                         "Unable to locate ZeidonCM view!",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         sbName.setLength( 0 );
         sbName.append( fnGetTaskOI_ListName( vSubtask ) ); // __CM.00000002
         GetViewByName( vTaskMetas, sbName.toString(), vZeidonCM, zLEVEL_SUBTASK );
         if ( isValid( vTaskMetas ) == false )
         {
            MessageSend( vZeidonCM, "CM00471", "TZCMOPRS ERROR",
                         "System Error: vTaskMetas view not found",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         }

         if ( CreateViewFromViewForTask( lpListView, vLPLR, vSubtask ) == 0 )
         {
            if ( SetCursorFirstEntityByInteger( lpListView, "W_MetaType", "Type", nType, "" ) != zCURSOR_SET )
            {
               CreateEntity( lpListView, "W_MetaType", zPOS_AFTER );
               SetAttributeFromInteger( lpListView, "W_MetaType", "Type", nType );
            }

            SetViewToSubobject( lpListView, "W_MetaType" );  // W_MetaType is not a recursive entity
            sbName.setLength( 1 );
            sbName.setCharAt( 0, 'l' );
            sbName.append( lpListView.getId() );
            SetNameForView( lpListView, sbName.toString(), vTaskMetas, zLEVEL_SUBTASK | zNAME_AUTODROP );
            return( 1 );
         }
      }
      else
      if ( isValid( lpListView ) )
      {
         MessageSend( lpListView, "CM00413", "Configuration Management",
                      "Unable to locate TaskLPLR view!", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      }

      return -1;
   }

   //./ ADD NAME=fnActivateMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: fnActivateMetaOI
   //
   //  PURPOSE:    Activates an Object Instance from a MetaList retrieved
   //              via RetrieveViewForMetaList, by declaring a view and then
   //              loading the OI from a portable file.
   //
   //              When activating a meta that is part of a group (e.g. domains)
   //              we activate the group and then position the cursor in the
   //              group to point to the meta requested.
   //
   //  PARAMETERS: pvMOI - a pointer to a View to be returned
   //              ListView - a valid LPL list view
   //              nType - a valid LPL meta type, (e.g. zSOURCE_DIALOG_META)
   //              lControl - NOT USED.  zLEVEL_APPLICATION is always used
   //                         for control so the memory for the OI is kept
   //                         at the application task.
   //
   //  RETURNS:    1 - Meta OI successfully activated
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   private int fnActivateMetaOI( View vSubtask, zVIEW pvMOI, View vListView, int nType, int lControl )
   {
      zVIEW   vZeidonCM = new zVIEW();
      zVIEW   vActiveMetas = new zVIEW();
      zVIEW   vTaskMetas = new zVIEW();
   // View    vApplication;
      zVIEW   vTaskLPLR = new zVIEW();
      zVIEW   CM_View = new zVIEW();
      zVIEW   vWkListView = new zVIEW();
      boolean bCopyOI = false;
      int     nEntityType;
      int     nOrigType;
      int     nActiveType, nPhaseCtl;
      boolean bReactivate;
      int     lStatus;
      int     lTaskID, lCurrentTaskID;
      int     lMetaOI_ZKey;
      int     ulOrigMetaOI_ZKey = 0;
      StringBuilder sbMetaOI_Name = new StringBuilder();
      StringBuilder sbMetaOI_Def = new StringBuilder();
      StringBuilder sbFileSpec = new StringBuilder();
      StringBuilder sbCM_ViewName = new StringBuilder();
      String  szLPLR_Name;
      StringBuilder sbSyncDate = new StringBuilder();
      String  szGroupName;
      int nRC;

      // Check to make sure that type is OK.
      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      if ( CheckExistenceOfEntity( vListView, "W_MetaDef" ) != zCURSOR_SET )
      {
         MessageSend( vSubtask, "CM00414", "Configuration Management",
                      "NULL Meta Def passed to ActivateMetaOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return( -16 );
      }

      if ( CompareAttributeToInteger( vListView, "W_MetaDef", "UpdateInd", 3 ) == 0 )
      {
         MessageSend( vSubtask, "CM00415", "Configuration Management",
                      "Deleted Meta Def passed to ActivateMetaOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return( -16 );
      }

      // I need the view to the TaskLPLR to get the name of the root LPLR in the ActiveMeta OI.
      // It is also needed to properly qualify the task for vWkListView.
      GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
      szLPLR_Name = GetStringFromAttribute( vTaskLPLR, "LPLR", "Name" );  // e.g. Zeidon

      // The following code changes the Type for Domain and Global Operation
      // Activates into Domain Group and Global Operation Group Activates.
      // A new LPLR view, vWkListView, is used because the Domain and Global
      // Operation processing alters the position on the W_MetaType and we
      // want to leave the ListView position unaltered.
   // ulOrigMetaOI_ZKey = GetIntegerFromAttribute( vListView, "W_MetaDef", "CPLR_ZKey" );
   // TraceLineI( "OrigMetaOI_ZKey vListView: ", ulOrigMetaOI_ZKey );
      CreateViewFromViewForTask( vWkListView, vListView, vTaskLPLR );
      ulOrigMetaOI_ZKey = GetIntegerFromAttribute( vWkListView, "W_MetaDef", "CPLR_ZKey" );
   // TraceLineI( "OrigMetaOI_ZKey vWkListView: ", ulOrigMetaOI_ZKey );
      SetNameForView( vWkListView, "WkListView", vSubtask, zLEVEL_TASK );
   /* if ( false )
      {
         MessageSend( vSubtask, "CM00418", "Configuration Management",
                      "Check WkListView for Operations",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      }
   */
      nOrigType = nType;                 // keep track of the original type

      if ( nType == zSOURCE_DOMAIN_META || nType == zREFER_DOMAIN_META )
      {
         // Save Domain ZKey for later positioning.
      // TraceLineS( "vWkListView", " dks " );
      // vWkListView.logObjectInstance( );
         ulOrigMetaOI_ZKey = GetIntegerFromAttribute( vWkListView, "W_MetaDef", "CPLR_ZKey" );
         TraceLineI( "vWkListView W_MetaDef CPLR_ZKey: ", ulOrigMetaOI_ZKey );

         if ( nType == zSOURCE_DOMAIN_META )
         {
            nType = zSOURCE_DOMAINGRP_META;
         }
         else
         {
            nType = zREFER_DOMAINGRP_META;
         }

         // Reposition on the correct group type.
         SetCursorFirstEntityByInteger( vWkListView, "W_MetaType", "Type", zREFER_DOMAINGRP_META, "" );

         // Then position on the correct Domain within the group type.
         SetCursorFirstEntityByAttr( vWkListView, "W_MetaDef", "Name", vListView, "W_MetaDef", "GroupName", "" );
      // szGroupName = GetStringFromAttribute( vListView, "W_MetaDef", "GroupName" );
      // SetCursorFirstEntityByString( vWkListView, "W_MetaDef", "Name", szGroupName, "" );
      }

      if ( nType == zSOURCE_GO_META || nType == zREFER_GO_META )
      {
         // Save Global Operation ZKey for later positioning.
         ulOrigMetaOI_ZKey = GetIntegerFromAttribute( vWkListView, "W_MetaDef", "CPLR_ZKey" );

         if ( nType == zSOURCE_GO_META )
         {
            nType = zSOURCE_GOPGRP_META;
         }
         else
         {
            nType = zREFER_GOPGRP_META;
         }

         // Reposition on the correct group type.
         SetCursorFirstEntityByInteger( vWkListView, "W_MetaType", "Type", zREFER_GOPGRP_META, "" );

         // Then position on the correct Operation within the group type.
         SetCursorFirstEntityByAttr( vWkListView, "W_MetaDef", "Name", vListView, "W_MetaDef", "GroupName", "" );
      // szGroupName = GetStringFromAttribute( vListView, "W_MetaDef", "GroupName" );
      // SetCursorFirstEntityByString( vWkListView, "W_MetaDef", "Name", szGroupName, "" );
      }

      // PETTIT The following if else is necessary until CM handles the Open, New, etc. Menu items.
   /*
      DonC comment on 4/21/1996:  I am eliminating the following code for now because our current position is that
      a user can modify a meta that is not checked out but cannot save it.  However, I am leaving the following
      code in case we change our mind.

      // If the user requested a Source meta, check the status of the MetaDef.
      // If the status is not 1 (e.g. it is not active), then the user can only
      // reference the meta, so change the meta to a Refer meta.
      if ( nType <= zSOURCE_MAX_META )
      {
         if ( CompareAttributeToInteger( vWkListView, "W_MetaDef","Status", 1 ) != 0 )
         {
            nEntityType = CM_REFER_TYPE;
            nType += 2000;  // Change SOURCE to REFER.
         }
      }
   */

      if ( nEntityType == CM_ACTIVE_TYPE )
      {
         nActiveType = nType;
      }
      else
      {
         nActiveType = nType - 2000;
      }

      // Get the view to ZedionCM OI.
      GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION );

      // Get the view to the vTaskMetas subtask.
      sbMetaOI_Name.setLength( 0 );
      sbMetaOI_Name.append( fnGetTaskOI_ListName( vSubtask ) );
      GetViewByName( vTaskMetas, sbMetaOI_Name.toString(), vZeidonCM, zLEVEL_SUBTASK );

      // Get the view to the ActiveMeta OI.
      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );

      TraceLineS( "Looking for TaskLPLR Name: ", GetStringFromAttribute( vTaskLPLR, "LPLR", "Name" ) );
      SetCursorFirstEntityByAttr( vActiveMetas, "LPLR", "Name", vTaskLPLR, "LPLR", "Name", "" );
      bReactivate = true;

      // Check to see if a W_MetaType exists for Type = nActiveType.  If not, then create one.
      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type", nActiveType, "" ) != zCURSOR_SET )
      {
         CreateEntity( vActiveMetas, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vActiveMetas, "W_MetaType", "Type", nActiveType );
      }

      // Check in the Active Meta List for a hit on the requested Meta.
      lMetaOI_ZKey = GetIntegerFromAttribute( vWkListView, "W_MetaDef", "CPLR_ZKey" );
      nRC = SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" );
      if ( nRC == zCURSOR_SET )
      {
         // It is in the Active List, now check if it's really still active.
         GetStringFromAttribute( sbCM_ViewName, vActiveMetas, "W_MetaDef", "CM_ViewName" );
         lTaskID = GetIntegerFromAttribute( vActiveMetas, "W_MetaDef", "TaskID" );
         if ( nEntityType == CM_REFER_TYPE )
         {
            zstrcat( sbCM_ViewName, ".r" );
            if ( GetViewByName( CM_View, sbCM_ViewName.toString(), vZeidonCM, zLEVEL_SUBTASK ) < 1 )
            {
               // It's in vActiveMetas but it's not loaded for REFER.
   /*
         Removed by DonC on Aug 11, 1998.
               if ( TaskID <= 0 )
               {
                  // If it's not loaded for update Exclude it
                  ExcludeEntity( vActiveMetas, "W_MetaDef", zREPOS_NONE );
                  nRC = zCURSOR_UNCHANGED;  // set k so meta gets reloaded
               }
   */
            }
            else
            {
               // It's there so set the flag so it's not reactivated.
               bReactivate = false;
            }
         } // if ( nEntityType == CM_REFER_TYPE )...
         else
         {
            // Requesting an Update View.
            zstrcat( sbCM_ViewName, ".u" );
            if ( GetViewByName( CM_View, sbCM_ViewName.toString(), vZeidonCM, zLEVEL_SUBTASK ) < 1 )
            {
               // It's in vActiveMetas but it's not loaded for UPDATE.

               // Change last String in view name from "u" to "r" and try to get THAT view.
               sbCM_ViewName.setCharAt( sbCM_ViewName.length() - 1, 'r' );
               if ( GetViewByName( CM_View, sbCM_ViewName.toString(), vZeidonCM, zLEVEL_SUBTASK ) < 1 )
               {
                  // It's in vActiveMetas but it's not loaded for REFER either.
                  ExcludeEntity( vActiveMetas, "W_MetaDef", zREPOS_NONE );
                  nRC = zCURSOR_UNCHANGED;  // set k so meta gets reloaded
               }
               else
               {
                  // It's loaded for REFER.
                  if ( lTaskID != 0 )  // any non-zero task id is valid (Win9x has negative IDs)
                  {
                     // Update View might have gotten blown away.
                     SetAttributeFromInteger( vActiveMetas, "W_MetaDef", "TaskID", 0 );
                  }

                  bCopyOI = true;
               }
            }
            else
            {
               // ERROR situation...
               // It's already loaded for update by somebody else.
               if ( lTaskID != 0 ) // added back 5.10.98 **HH**
               {
               // if ( lTaskID == SysGetTaskFromView( vListView ) )
                  if ( lTaskID == Integer.decode( SysGetTaskFromView( vSubtask ).getTaskId() ) )
                  {
                     if ( ObjectInstanceUpdatedFromFile( CM_View ) == 0 )
                     {
                        bCopyOI = true;
                     }
                  }
                  else
                  {
                     MessageSend( vSubtask, "CM00416", "Configuration Management",
                                  "The Meta requested for update is already\nopened for update by another task\nUnable to open!",
                                  zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                     {
                        return -1;
                     }
                  }
               }
            }
         } // if ( nEntityType == CM_REFER_TYPE )...else...

      } // if ( k == zCURSOR_SET )...

      lStatus = GetIntegerFromAttribute( vWkListView, "W_MetaDef", "Status" );
      if ( nRC != zCURSOR_SET ) // not in the Active List
      {
         TraceLineS( "(fnActivateMetaOI) Before IncludeSubobject", "vActiveMetas" );
         vActiveMetas.logObjectInstance();
         TraceLineS( "Before IncludeSubobject", "vWkListView" );
         EntityCursor cursorMetaDef = vWkListView.cursor( "W_MetaDef" );
         cursorMetaDef.logEntity( true );

         if ( CheckExistenceOfEntity( vActiveMetas, "W_MetaType" ) != zCURSOR_SET )
         {
            if ( CheckExistenceOfEntity( vActiveMetas, "LPLR" ) != zCURSOR_SET )
            {
               CreateEntity( vActiveMetas, "LPLR", zPOS_AFTER );
               SetMatchingAttributesByName( vActiveMetas, "LPLR", vWkListView, "LPLR", zSET_ALL );
            }

            CreateEntity( vActiveMetas, "W_MetaType", zPOS_AFTER );
            SetMatchingAttributesByName( vActiveMetas, "W_MetaType", vWkListView, "W_MetaType", zSET_ALL );
         }

         IncludeSubobjectFromSubobject( vActiveMetas, "W_MetaDef", vWkListView, "W_MetaDef", zPOS_AFTER );

         TraceLineS( "(fnActivateMetaOI) After IncludeSubobject", "vActiveMetas" );
         vActiveMetas.logObjectInstance();
         TraceLineS( "After IncludeSubobject", "vWkListView" );
         cursorMetaDef = vWkListView.cursor( "W_MetaDef" );
         cursorMetaDef.logEntity( true );

         if ( (nEntityType == CM_ACTIVE_TYPE) && (lStatus == 1) )
         {
         // lCurrentTaskID = SysGetTaskFromView( vListView );
            lCurrentTaskID = Integer.decode( SysGetTaskFromView( vSubtask ).getTaskId() );
            SetAttributeFromInteger( vActiveMetas, "W_MetaDef", "TaskID", lCurrentTaskID );
         }

         if ( nEntityType == CM_REFER_TYPE )
         {
            // The view may still be hanging around if Zeidon was still open.
            GetStringFromAttribute( sbCM_ViewName, vActiveMetas, "W_MetaDef", "CM_ViewName" );
            zstrcat( sbCM_ViewName, ".r" );
            if ( GetViewByName( CM_View, sbCM_ViewName.toString(), vZeidonCM, zLEVEL_SUBTASK ) > 0 )
            {
               // It's loaded for REFER so set the flag so it's not reactivated.
               bReactivate = false;
            }
         }
      }

   // MessageBox.show( primaryStage, "Message Body", "Message Title",
   //                  MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL );

      // If we don't have to reactivate the object, then create a view and return.
      if ( bReactivate == false ) // REFER is already activated
      {
         CreateViewFromViewForTask( pvMOI, CM_View, vSubtask );
         ResetView( pvMOI );

         sbCM_ViewName.setLength( 1 );
         sbCM_ViewName.setCharAt( 0, 'r' );
         sbCM_ViewName.append( pvMOI.getId() );
         SetNameForView( pvMOI, sbCM_ViewName.toString(), vTaskMetas, zLEVEL_SUBTASK | zNAME_AUTODROP );

   /*
         // We no longer set refer views as read-only.  Instead, we check in
         // fnCommitObjectInstance and don't allow commits for REFER views.
         SetViewReadOnly( *pvMOI );
   */

         // If we are activating a Domain, we must position on the correct
         // Domain within the Domain Group.
         if ( nOrigType == zSOURCE_DOMAIN_META || nOrigType == zREFER_DOMAIN_META )
         {
            if ( SetCursorFirstEntityByInteger( pvMOI, "Domain", "ZKey",
                                                ulOrigMetaOI_ZKey, "" ) != zCURSOR_SET )
            {
               SetNameForView( pvMOI, "__MOI_Error", vSubtask, zLEVEL_SUBTASK );
               TraceLine( "fnActivateMetaOI cannot find Domain (%d) in DomainGroup ... View: 0x%08x (%s or %s)",
                          ulOrigMetaOI_ZKey, pvMOI.getId(), "__MOI_Error", sbCM_ViewName.toString() );
            // DisplayObjectInstance( vWkListView, "", "" );
               MessageSend( vSubtask, "CM00417", "Configuration Management",
                            "Couldn't find Domain in DomainGroup",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

               return -1;
            }
         }

         // If we are activating a Global Operation, we must position on the
         // correct Operation within the Global Operation Group.
         if ( nOrigType == zSOURCE_GO_META || nOrigType == zREFER_GO_META )
         {
            if ( SetCursorFirstEntityByInteger( pvMOI, "Operation", "ZKey",
                                                ulOrigMetaOI_ZKey, "" ) != zCURSOR_SET )
            {
               SetNameForView( pvMOI, "__MetaGroup_Error", vSubtask, zLEVEL_TASK );
               TraceLine( "Couldn't find \"Operation\" (__MetaGroup_Error view: %s) in Global Op Group by ZKey: %d",
                          sbCM_ViewName.toString(), ulOrigMetaOI_ZKey );
            // DisplayObjectInstance( vWkListView, "", "" );
               MessageSend( vSubtask, "CM00418", "Configuration Management",
                            "Couldn't find Global Op in Global Op Group",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }
         }

         DropView( vWkListView );
         return( 1 );
      }

      if ( (nEntityType == CM_ACTIVE_TYPE) && (lStatus == 1) )
      {
      // lCurrentTaskID = SysGetTaskFromView( vListView );
         lCurrentTaskID = Integer.decode( SysGetTaskFromView( vSubtask ).getTaskId() );
         SetAttributeFromInteger( vActiveMetas, "W_MetaDef", "TaskID", lCurrentTaskID );
      }

      // If bCopyOI is true, then the meta already exists for REFER, but we need
      // to activate it for SOURCE (i.e. update).  All we need to do is create a
      // new OI from the REFER OI.
      if ( bCopyOI )
      {
         if ( ActivateOI_FromOI_ForTask( pvMOI, CM_View, null, zLEVEL_APPLICATION ) != 0 )
         {
            MessageSend( vSubtask, "CM00419", "Configuration Management",
                         "Error activating OI from OI",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }
      }
      else
      {
         ///////////////////////////////////////////////////////////////////////
         // The Meta needs to be loaded from a file.  We need to know the
         // following:
         //
         // o  The name of the LOD for the meta.
         // o  The name of the portable file that contains the OI.
         //
         // Knowing these, we can then activate the OI from the file.
         //
         ///////////////////////////////////////////////////////////////////////

         // Retrieve the name of the LOD for meta type we are activating.  Note
         // that the LOD name may be changed in the following 'switch' code.
         if ( nEntityType == CM_ACTIVE_TYPE )
         {
            zstrcpy( sbMetaOI_Def, SRC_CMOD[ nType ].szOD );
         }
         else
         {
            zstrcpy( sbMetaOI_Def, REFER_CMOD[ nType - 2000 ].szOD );
         }

         // Find the name of the portable file containing the OI.
         switch ( nActiveType )
         {
            case zSOURCE_VOR_META:
               lMetaOI_ZKey = GetIntegerFromAttribute( vWkListView, "W_MetaDef", "CPLR_ZKey" );
               sbMetaOI_Name.setLength( 1 );
               sbMetaOI_Name.setCharAt( 0, 'Z' );
               sbMetaOI_Name.append( zltoxa( lMetaOI_ZKey ) );
               break;

            default:
               sbMetaOI_Name.setLength( 0 );
               sbMetaOI_Name.append( GetStringFromAttribute( vWkListView, "W_MetaDef", "Name" ) );
               TruncateName8( sbMetaOI_Name );
               break;

         } // switch ( nActiveType )...

         //BL, 2000.01.13 Load PPE from LPLR, not from system directory
         //               if PPE does not exist in LPLR, then we call the
         //               Function ActivateOI_FromFile, not ActivateMetaOI
         fnGetDirectorySpec( vSubtask, sbFileSpec, nType ); // for CompilerSpec, needed to use vSubtask

         // We will get PEs from the system directory and others from the LPLR.
      // if ( nActiveType == zSOURCE_PENV_META )
      //    zgGetZeidonToolsDir( vSubtask, szFileSpec, zAPPL_DIR_OBJECT );
      // else
      //    fnGetDirectorySpec( szFileSpec, nType );

         zstrcat( sbFileSpec, sbMetaOI_Name.toString() );
         if ( nEntityType == CM_ACTIVE_TYPE )
         {
            zstrcat( sbFileSpec, SRC_CMOD[ nType ].szOD_EXT );
         }
         else
         {
            zstrcat( sbFileSpec, REFER_CMOD[ nType - 2000 ].szOD_EXT );
         }

         // DGC 10/4/95  Added zIGNORE_ERRORS to ignore activation errors (we
         // wanted to ignore a known error). This should be removed at some point.
         // Finally, activate the OI from the portable file.
      // SfCreateSubtask( &vApplication, vSubtask, szLPLR_Name );  // LPLR "Zeidon"
         nRC = ActivateOI_FromFile( pvMOI, sbMetaOI_Def.toString(), vWkListView, // vApplication,
                                    sbFileSpec.toString(), zIGNORE_ERRORS | zLEVEL_APPLICATION );
      // SfDropSubtask( vApplication, 0 );
         if ( nRC != 0 )
         {
            StringBuilder sbMsg = new StringBuilder();

            zsprintf( sbMsg, "Error activating OI from file: %s", sbFileSpec.toString() );
            MessageSend( vSubtask, "CM00420", "Configuration Management",
                         sbMsg.toString(),
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }
      }

      // Recreate the view name either for read-only or update.  nPhaseCtl is used
      // by the PostActivate code--it indicates whether we are activating the meta
      // for read-only or update.
      GetStringFromAttribute( sbCM_ViewName, vActiveMetas, "W_MetaDef", "CM_ViewName" );
      if ( nEntityType == CM_REFER_TYPE )
      {
         nPhaseCtl = 1;
         zstrcat( sbCM_ViewName, ".r" );
      }
      else
      {
         nPhaseCtl = 0;
         zstrcat( sbCM_ViewName, ".u" );
      }

      // Name the new view.  The view is for the Zeidon system.
      SetNameForView( pvMOI, sbCM_ViewName.toString(), vZeidonCM, zLEVEL_SUBTASK );

      // The current view pvMOI will be used for internal CM use.  We need to create
      // a new view to be passed back to the application that requested the meta.
      zVIEW vTemp = new zVIEW();
      CreateViewFromViewForTask( vTemp, pvMOI, vSubtask );  //???
      pvMOI.setView( vTemp.getView() );

      // **** Temporary Hack  05.12.1997
      // If this is a LOD, we are going to make sure that all IndentName values
      // are actually indented, since there has been a bug that removes the
      // indentation.  We will also make sure the Indented name is not blank.
      if ( nOrigType == zSOURCE_LOD_META || nOrigType == zREFER_LOD_META )
      {
         zVIEW   vTempLOD = new zVIEW();
         int     lLevel;
         String  szName;
         String  szIndentName;
         int     k;

         CreateViewFromViewForTask( vTempLOD, pvMOI, null );
         for ( nRC = SetCursorFirstEntity( vTempLOD, "LOD_Entity", "" );
               nRC >= zCURSOR_SET;
               nRC = SetCursorNextEntity( vTempLOD, "LOD_Entity", "" ) )
         {
            lLevel = GetIntegerFromAttribute( vTempLOD, "LOD_Entity", "IndentLvl" );
            szIndentName = GetStringFromAttribute( vTempLOD, "LOD_Entity", "IndentName" );
            if ( (lLevel > 0 && szIndentName.charAt( 0 ) != ' ') || (szIndentName.length() == 0) )
            {
               // There is an indentation level but the IndentName is not
               // indented.  Thus we will indent it.
               // Put x blank chars at beginning of indent name, where x is IndentLvl.
               // Then concat the regular names after the blanks.
               // Don't put more than 21 blanks in name.
               k = lLevel < 21 ? lLevel : 21;
               szIndentName = "                         ".substring( 0, k );

               szName = GetStringFromAttribute( vTempLOD, "LOD_Entity", "Name" );
               zstrcat( szIndentName, szName );
               SetAttributeFromString( vTempLOD, "LOD_Entity", "IndentName", szIndentName );
            }
         }

         DropView( vTempLOD );
      }

      // Now that the object instance has been activated, call the logical
      // object operation for the object type to ensure all appropriate
      // object linking is done.  Use a different view to ensure that the
      // cursors don't get moved in listview.
      sbSyncDate.setLength( 0 );
      cfPostActivate( vSubtask, nType, sbSyncDate, vZeidonCM.getView(), pvMOI.getView(), vWkListView.getView(), nPhaseCtl, false );

      // DGC 8/17/95
      // I don't think the following command is necessary, so I'm going to take it out.
   // ResetView( *pvMOI );

      // If we are activating a Domain, we must position on the correct Domain within the Domain Group.
      if ( nOrigType == zSOURCE_DOMAIN_META || nOrigType == zREFER_DOMAIN_META )
      {
         if ( SetCursorFirstEntityByInteger( pvMOI, "Domain", "ZKey", ulOrigMetaOI_ZKey, "" ) != zCURSOR_SET )
         {
            TraceLine( "fnActivateMetaOI2 cannot find Domain (%d) in DomainGroup ... View: 0x%08x (%s)",
                       ulOrigMetaOI_ZKey, pvMOI.getId(), "__MOI_Error" );
            pvMOI.logObjectInstance( );
            TraceLineS( "EntityInstance", "" );
            DisplayEntityInstance( pvMOI, "Domain" );
            SetNameForView( pvMOI, "__MOI_Error", vSubtask, zLEVEL_SUBTASK );
            MessageSend( vSubtask, "CM00417", "Configuration Management",
                         "Couldn't find Domain in DomainGroup",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }
      }

      // If we are activating a Global Operation, we must position on the
      // correct Operation within the Global Operation Group.
      if ( nOrigType == zSOURCE_GO_META || nOrigType == zREFER_GO_META )
      {
         if ( SetCursorFirstEntityByInteger( pvMOI, "Operation", "ZKey", ulOrigMetaOI_ZKey, "" ) != zCURSOR_SET )
         {
            SetNameForView( pvMOI, "MetaGroup", vSubtask, zLEVEL_TASK );
            TraceLine( "Couldn't find \"Operation\" (MetaGroup view:%s) in Global Op Group by ZKey: %d",
                       sbCM_ViewName, ulOrigMetaOI_ZKey );
            MessageSend( vSubtask, "CM00418", "Configuration Management",
                         "Couldn't find Global Op in Global Op Group",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }
      }

      // Name the view based upon the ZKey and update/refer type for identification by Task.
      sbCM_ViewName.setLength( 1 );
      if ( nEntityType == CM_ACTIVE_TYPE && lStatus == 1 )
      {
         sbCM_ViewName.setCharAt( 0, 'u' );
      }
      else
      {
         sbCM_ViewName.setCharAt( 0, 'r' );
      }

      sbCM_ViewName.append( pvMOI.getId() );
      SetNameForView( pvMOI, sbCM_ViewName.toString(), vTaskMetas, zLEVEL_SUBTASK | zNAME_AUTODROP );

      // We no longer set refer views as read-only.  Instead, we check in
      // fnCommitObjectInstance and don't allow commits for REFER views.
      //
   // if ( (nEntityType != CM_ACTIVE_TYPE) || (lStatus != 1) )
   //    SetViewReadOnly( *pvMOI );

      DropView( vWkListView );

      return( 1 );
   } // fnActivateMetaOI

   //./ ADD NAME=ActivateMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: ActivateMetaOI
   //
   //  PURPOSE:    Activates an Object Instance from a MetaList retrieved
   //              via RetriveViewForMetaList, by declaring a view and then
   //              loading the OI from a portable file.
   //
   //  PARAMETERS: pvMOI - a pointer to a View to be returned
   //              ListView - a valid LPL list view
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    1 - Meta OI successfully activated
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int ActivateMetaOI( View vSubtask, zVIEW pvMOI, View vListView, int nType, int lControl )
   {
      zVIEW  vTaskLPLR = new zVIEW();
      int    nRC = 0;

      pvMOI.setView( null );
      GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );

      synchronized( lockActMeta )  //  SysMutexLock( vSubtask, "ActMeta", 0, 0 );
      {
         try {
            
   // TraceLineX( "ActivateMetaOI Locked Mutex: ActMeta  for Task: ", (int) vSubtask->hTask );
      nRC = fnActivateMetaOI( vSubtask, pvMOI, vListView, nType, 0 );
   // TraceLineX( "ActivateMetaOI Unlocking Mutex: ActMeta  for Task: ", (int) vSubtask->hTask );

         } catch (Exception ex) {
            nRC = -1;
         } finally {
           // just unlock
         }
      }  // SysMutexUnlock( vSubtask, "ActMeta", 0 );

      if ( nRC < 0 )
      {
         return( nRC );
      }

      if ( ObjectInstanceUpdatedFromFile( vTaskLPLR ) == 1 )
      {
         CommitLPLR( vTaskLPLR );
      }

      return( nRC );
   }

   //./ ADD NAME=ActivateMetaOI_ByName
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: ActivateMetaOI_ByName
   //
   //  PURPOSE:    Activates an Object Instance using the Name.  The routine
   //              will get a MetaList if one is NOT specified in the call.
   //              The Meta is "Activated" only if it is not currently loaded
   //              or the call specifies to force a re-load.
   //
   //  PARAMETERS: pvMOI - pointer to a View to be returned
   //              vListView  - valid LPL list view or ZERO
   //              nType      - valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //              lControl   - usually  zSINGLE | zLEVEL_APPLICATION
   //              pchName    - string containing 'name' of Meta
   //              nCurrentOrReload - one of the following:
   //                   zCURRENT_OI         0
   //                   zFORCE_RELOAD       1
   //
   //  RETURNS:    0 - Meta OI View returned from loaded Meta
   //              1 - Meta OI activated and View returned
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int ActivateMetaOI_ByName( View vSubtask, zVIEW pvMOI, View vListView, int nType,
                                     int lControl, String pchName, int nCurrentOrReload )
   {
      zVIEW  vCM_List = new zVIEW();
      int    nEntityType;
      int    nRC;

      if ( pchName.isEmpty() )
      {
         pvMOI.setView( null );
         return -1;
      }

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      synchronized( lockActMeta )  //  SysMutexLock( vSubtask, "ActMeta", 0, 0 );
      {
   // TraceLineX( "ActivateMetaOI_ByName Locked Mutex: ActMeta  for Task: ", vSubtask.getTask().getTaskId() );

      // Get Access to CM List Object
      if ( isValid( vListView ) )
      {
         vCM_List.setView( vListView );

         //*** Change Start  -  Don Christensen  -  2/17/94
         // This set cursor is necessary to position on the correct
         // W_MetaType, when the vListView is not the default.

         if ( nType < 2000 )
         {
            SetCursorFirstEntityByInteger( vCM_List, "W_MetaType", "Type", nType + 2000, "" );
         }
         else
         {
            SetCursorFirstEntityByInteger( vCM_List, "W_MetaType", "Type", nType, "" );
         }
         //*** Change End
      }
      else
      {
         if ( nType < 2000 )
         {
            RetrieveViewForMetaList( vSubtask, vCM_List, (nType + 2000) );
         }
         else
         {
            RetrieveViewForMetaList( vSubtask, vCM_List, nType );
         }
      }

      if ( isValid( vCM_List ) )
      {
         nRC = SetCursorFirstEntityByString( vCM_List, "W_MetaDef", "Name", pchName, "" );
         if ( nRC != zCURSOR_SET )
         {
            Logger logger = Logger.getLogger( KZOEP1AA.class );
            vCM_List.logObjectInstance();
            logger.debug( "Cannot find W_MetaDef by name: " + pchName );
            EntityCursor cursorMetaType = vCM_List.cursor( "W_MetaType" );
            cursorMetaType.logEntity( true );
            pvMOI.setView( null );
            // The following was deleted by DonC on 9/28/07. Coming into this code is not an error condition.
            // It happens every time a new LOD is created and the return code of -1 tells the caller to create
            // the Named View (PVR).
         // SetNameForView( vCM_List, "_CM_List", vSubtask, zLEVEL_TASK );
         // TraceLine( "(tzcmoprs) ActivateMetaOI_ByName - missing Name: %s  in View: _CM_List  Entity: W_MetaDef", pchName );
         // SysMessageBox( 0, "Missing Name in View: _CM_List  Entity: W_MetaDef", pchName, -1 );
            if ( isValid( vListView ) == false )
            {
               DropView( vCM_List );
            }

         // TraceLineX( "ActivateMetaOI_ByName Unlocking Mutex: ActMeta  for Task: ", vSubtask.getTask().getTaskId() );

            nRC = -1;   // SysMutexUnlock( vSubtask, "ActMeta", 0 );
         }
         else
         {
            nRC = ActivateMetaOI( vSubtask, pvMOI, vCM_List, nType, lControl );
            if ( isValid( vListView ) == false )
            {
               DropView( vCM_List );
            }
         }
      }
      else
      {
         nRC = -1;
      }

   // TraceLineX( "ActivateMetaOI_ByName Unlocking Mutex: ActMeta  for Task: ",
   //             (int) vSubtask->hTask );

      }  // SysMutexUnlock( vSubtask, "ActMeta", 0 );

      return( nRC );
   }

   //./ ADD NAME=ActivateMetaOI_ByZKey
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: ActivateMetaOI_ByZKey
   //
   //  PURPOSE:    Activates an Object Instance using a ZKey.  The routine
   //              will get a MetaList if one is NOT specified in the call.
   //              The Meta is "Activated" only if it is not currently loaded
   //              or the call specifies to force a re-load.
   //
   //  PARAMETERS: pvMOI - pointer to a View to be returned
   //              vListView  - valid LPL list view or ZERO
   //              nType      - valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //              lControl   - usually  zSINGLE | zLEVEL_APPLICATION
   //              lZKey      - long containing ZKey of Meta
   //              nCurrentOrReload - one of the following:
   //                   zCURRENT_OI         0
   //                   zFORCE_RELOAD       1
   //
   //  RETURNS:    0 - Meta OI View returned from loaded Meta
   //              1 - Meta OI activated and View returned
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int ActivateMetaOI_ByZKey( View   vSubtask,
                                     zVIEW  pvMOI,
                                     View   vListView,
                                     int    nType,
                                     int    lControl,
                                     int    lZKey,
                                     int    nCurrentOrReload )
   {
      zVIEW  vCM_List = new zVIEW();
      int nEntityType;
      int nRC;

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      synchronized( lockActMeta )  // SysMutexLock( vSubtask, "ActMeta", 0, 0 );
      {
   // TraceLineX( "ActivateMetaOI_ByName Locked Mutex: ActMeta  for Task: ",
   //             (int) vSubtask->hTask );

      // Get Access to CM List Object.
      if ( isValid( vListView ) )
      {
         vCM_List.setView( vListView );
      }
      else
      {
         if ( nType < 2000 )
         {
            RetrieveViewForMetaList( vSubtask, vCM_List, (nType + 2000) );
         }
         else
         {
            RetrieveViewForMetaList( vSubtask, vCM_List, nType );
         }
      }

      nRC = SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
      if ( nRC != zCURSOR_SET )
      {
         pvMOI.setView( null );
         TraceLineI( "(kzoep1aa) ActivteMetaOI_ByZKey - missing W_MetaDef CPLR_ZKey = ", lZKey );
         vCM_List.logObjectInstance();
         TraceLineI( "(kzoep1aa) ActivteMetaOI_ByZKey - missing W_MetaDef CPLR_ZKey = ", lZKey );
      // TraceLineX( "ActivateMetaOI_ByZKey Unlocking Mutex: ActMeta  for Task: ", (int) vSubtask->hTask );

         nRC = -1;  // SysMutexUnlock( vSubtask, "ActMeta", 0 );
      }
      else
      {
         nRC = ActivateMetaOI( vSubtask, pvMOI, vCM_List, nType, lControl );
         if ( isValid( vListView ) == false )
         {
            DropView( vCM_List );
         }
      }

   // TraceLineX( "ActivateMetaOI_ByZKey Unlocking Mutex: ActMeta  for Task: ",
   //             (int) vSubtask->hTask );

      }  // SysMutexUnlock( vSubtask, "ActMeta", 0 );

      return( nRC );
   }

   // Include the ViewObjRef entity from an Application VOR to the dialog.
    public int IncludeVOR_Entity( View vSubtask, View vVOR_Parent, int lZKey )
   {
      zVIEW    vLPLR_VOR_Meta = new zVIEW();
   // zVIEW    vTZWINDOW = new zVIEW();
      zVIEW    vApplVOR = new zVIEW();
      int      nRC;

      nRC = GetViewByName( vLPLR_VOR_Meta, "LPLR_VOR_Meta", vSubtask, zLEVEL_ANY );
      if ( nRC == -1 )
      {
         RetrieveViewForMetaList( vSubtask, vLPLR_VOR_Meta, zREFER_VOR_META );
         SetNameForView( vLPLR_VOR_Meta, "LPLR_VOR_Meta", vSubtask, zLEVEL_TASK );
         OrderEntityForView( vLPLR_VOR_Meta, "W_MetaDef", "Name A" );
      }

      DisplayEntityInstance( vLPLR_VOR_Meta, "W_MetaDef" );
      nRC = SetCursorFirstEntityByInteger( vLPLR_VOR_Meta, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
      System.out.println( "Looking for W_MetaDef by ZKey: " + lZKey + "  RC = " + nRC );
      DisplayEntityInstance( vLPLR_VOR_Meta, "W_MetaDef" );

   // GetViewByName( vTZWINDOW, "TZWINDOWL", vSubtask, zLEVEL_ANY );
   // GetViewByName( vLPLR_VOR_Meta, "LPLR_VOR_Meta", vSubtask, zLEVEL_ANY );
      nRC = ActivateMetaOI( vSubtask, vApplVOR, vLPLR_VOR_Meta, zREFER_VOR_META, 0 );
      if ( nRC == 1 )
      {
         lZKey = GetIntegerFromAttribute( vApplVOR, "ViewObjRef", "ZKey" );
         nRC = SetCursorFirstEntityByInteger( vVOR_Parent, "ViewObjRef", "ZKey", lZKey, "" );
         if ( nRC >= zCURSOR_SET )
         {
            ExcludeEntity( vVOR_Parent, "ViewObjRef", zREPOS_PREV );
         }

         IncludeSubobjectFromSubobject( vVOR_Parent, "ViewObjRef", vApplVOR, "ViewObjRef", zPOS_AFTER );
      }

      DropMetaOI( vSubtask, vApplVOR );
      return( 0 );
   }


   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SEL_ATTR_SelectView
   //
   // PURPOSE:  This function
   //           1. Does the necessary exclude/include of CtrlMapView.
   //           2. Activates the LOD for the View just selected.
   //           3. Refreshes the Entity and Attribute List Boxes and sets
   //              the LOD Entity and Attribute select states.
   //
   //           Note that steps 2 and 3 are not done if the include is
   //           to the same LOD type as for the previous CtrlMapView.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_SelectView( View vSubtask, View vVOR_Parent )
   {
      zVIEW    vLOD = new zVIEW();
      zVIEW    vCM_List = new zVIEW();
      zVIEW    vDialogW = new zVIEW();
      zVIEW    vDomain = new zVIEW();
      int      lZKey;
      int      nRC;
      StringBuilder sbObjectName = new StringBuilder();

  //x GetViewByName( vVOR_Parent, "TZCONTROL", vSubtask, zLEVEL_TASK );
  //x GetViewByName( vDialogW, "TZWINDOW", vSubtask, zLEVEL_TASK );

      // Drop the SEL_LOD meta OI, if it exists.
      nRC = GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );
      if ( nRC >= 0 )
         DropMetaOI( vSubtask, vLOD );

      if ( CheckExistenceOfEntity( vVOR_Parent, "CtrlMapView" ) == zCURSOR_SET )
      {
         // Activate the LOD for the current ViewObjRef subobject.
         RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_LOD_META );
         lZKey = GetIntegerFromAttribute( vDialogW, "LOD", "ZKey" );
         SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
         ActivateMetaOI( vSubtask, vLOD, vCM_List, zREFER_LOD_META, 0 );
         SetNameForView( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

     //x SetSelectStateOfEntityForSet( vLOD, "LOD_Entity", 1, 1 );
     //x if ( CheckExistenceOfEntity( vLOD, "LOD_Attribute" ) == zCURSOR_SET )
     //x    SetSelectStateOfEntityForSet( vLOD, "LOD_Attribute", 1, 1 );

     //x RefreshCtrl( vSubtask, "EntityList" );
     //x RefreshCtrl( vSubtask, "AttributeList" );
      }

      // Make sure MapDomain2 view exists for current Attribute, but no current context exists.

      if ( GetViewByName( vDomain, "MapDomain2", vSubtask, zLEVEL_TASK ) >= 0 )
         DropMetaOI( vSubtask, vDomain );

      if ( CheckExistenceOfEntity( vVOR_Parent, "CtrlMapView" ) == zCURSOR_SET )
      {
         RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_DOMAIN_META );
         GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

         // Activate the Domain list for the current vLOD Attribute.
         lZKey = GetIntegerFromAttribute( vLOD, "Domain", "ZKey" );
         SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
         ActivateMetaOI( vSubtask, vDomain, vCM_List, zREFER_DOMAIN_META, 0 );
         SetNameForView( vDomain, "MapDomain2", vSubtask, zLEVEL_TASK );
      }

      // For Dialogs (ObjectName: TZWDLGSO), remove work Context, if it exists.
//x   MiGetObjectNameForView( sbObjectName, vVOR_Parent );
//x   if ( zstrcmp( sbObjectName.toString(), "TZWDLGSO" ) == 0 )
//x      if ( CheckExistenceOfEntity( vVOR_Parent, "TempMappingContext" ) >= zCURSOR_SET )
//x         ExcludeEntity( vVOR_Parent, "TempMappingContext", zREPOS_PREV );

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SEL_ATTR_SelectEntity
   //
   // PURPOSE:  This function refreshes the Entity and Attribute List
   //           Boxes.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_SelectEntity( View vSubtask )
   {
      zVIEW    vLOD = new zVIEW();
      zVIEW    vDomain = new zVIEW();
      zVIEW    vDialogC = new zVIEW();
      zVIEW    vCM_List = new zVIEW();
      int      lZKey;
      StringBuilder sbObjectName = new StringBuilder();

      GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );
 //x  if ( CheckExistenceOfEntity( vLOD, "LOD_Attribute" ) == zCURSOR_SET )
 //x     SetSelectStateOfEntityForSet( vLOD, "LOD_Attribute", 1, 1 );

      // Make sure MapDomain2 view exists for current Attribute, but no current context exists.

      if ( GetViewByName( vDomain, "MapDomain2", vSubtask, zLEVEL_TASK ) >= 0 )
         DropMetaOI( vSubtask, vDomain );

      RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_DOMAIN_META );
      GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

      // Activate the Domain list for the current vLOD Attribute.
      lZKey = GetIntegerFromAttribute( vLOD, "Domain", "ZKey" );
      SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
      ActivateMetaOI( vSubtask, vDomain, vCM_List, zREFER_DOMAIN_META, 0 );
      SetNameForView( vDomain, "MapDomain2", vSubtask, zLEVEL_TASK );

      // Remove work Context, if it exists.
      GetViewByName( vDialogC, "TZCONTROL", vSubtask, zLEVEL_TASK );
      MiGetObjectNameForView( sbObjectName, vDialogC );
      if ( zstrcmp( sbObjectName.toString(), "TZWDLGSO" ) == 0 )
         if ( CheckExistenceOfEntity( vDialogC, "TempMappingContext" ) >= zCURSOR_SET )
            ExcludeEntity( vDialogC, "TempMappingContext", zREPOS_PREV );

//x   RefreshCtrl( vSubtask, "AttributeList" );
      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SEL_ATTR_SelectAttribute
   //
   // PURPOSE:  This function refreshes the Entity and Attribute List
   //           Boxes.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_SelectAttribute( View vSubtask )
   {
      zVIEW    vLOD = new zVIEW();
      zVIEW    vDomain = new zVIEW();
      zVIEW    vCM_List = new zVIEW();
      zVIEW    vDialogC = new zVIEW();
      int      lZKey;
      StringBuilder sbObjectName = new StringBuilder();

      // Make sure MapDomain2 view exists for current Attribute, but no current context exists.

      if ( GetViewByName( vDomain, "MapDomain2", vSubtask, zLEVEL_TASK ) >= 0 )
         DropMetaOI( vSubtask, vDomain );

      RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_DOMAIN_META );
      GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

      // Activate the Domain list for the current vLOD Attribute.
      lZKey = GetIntegerFromAttribute( vLOD, "Domain", "ZKey" );
      SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
      ActivateMetaOI( vSubtask, vDomain, vCM_List, zREFER_DOMAIN_META, 0 );
      SetNameForView( vDomain, "MapDomain2", vSubtask, zLEVEL_TASK );

      // Remove work Context, if it exists.
      GetViewByName( vDialogC, "TZCONTROL", vSubtask, zLEVEL_TASK );
      MiGetObjectNameForView( sbObjectName, vDialogC );
//x   if ( zstrcmp( sbObjectName.toString(), "TZWDLGSO" ) == 0 )
//x       if ( CheckExistenceOfEntity( vDialogC, "TempMappingContext" ) >= zCURSOR_SET )
//x          ExcludeEntity( vDialogC, "TempMappingContext", zREPOS_PREV );

   //xRefreshCtrl( vSubtask, "AttributeList" );
      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SEL_ATTR_SelectMapping
   //
   // PURPOSE:  This function does the necessary exclude/includes
   //           to set up attribute mapping.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_SelectMapping( View vSubtask )
   {
      zVIEW    vDialog = new zVIEW();
      zVIEW    vLOD = new zVIEW();
      zVIEW    vTZWINDOWL = new zVIEW();
      zVIEW    vTZPNTROO = new zVIEW();
      int      nRC;

      GetViewByName( vTZPNTROO, "TZPNTROO", vSubtask, zLEVEL_ANY );
      GetViewByName( vDialog, "TZCONTROL", vSubtask, zLEVEL_TASK );
      GetViewByName( vTZWINDOWL, "TZWINDOWL", vSubtask, zLEVEL_TASK );
      nRC = GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

      if ( isValid( vLOD ) && CheckExistenceOfEntity( vLOD, "LOD" ) < zCURSOR_SET )
      {
         nRC = -1;
         DropView( vLOD );
      }

      // If CtrlMap exists, Accept CtrlMap
      if ( CheckExistenceOfEntity( vDialog, "CtrlMap" ) >= zCURSOR_SET )
         AcceptSubobject( vDialog, "CtrlMap" );

      if ( MiGetUpdateForView( vDialog ) == 0 )
         return( 0 );

      // There is no mapping view.
      if ( nRC <= 0 )
      {
         // If current CtrlMapContext exists, exclude it.
         if ( CheckExistenceOfEntity( vDialog, "CtrlMapContext" ) == zCURSOR_SET )
            ExcludeEntity( vDialog, "CtrlMapContext", zREPOS_PREV );

         // If current CtrlMapLOD_Attribute exists, exclude it.
         if ( CheckExistenceOfEntity( vDialog, "CtrlMapLOD_Attribute" ) == zCURSOR_SET )
         {
            ExcludeEntity( vDialog, "CtrlMapLOD_Attribute", zREPOS_PREV );
         }

         DeleteEntity( vTZPNTROO, "CtrlWork", zPOS_AFTER );
         return( 0 );
      }

      // Alter mapping only if it changed from existing.
      if ( CheckExistenceOfEntity( vDialog, "CtrlMapLOD_Attribute" ) != zCURSOR_SET ||
           CompareAttributeToAttribute( vDialog, "CtrlMapLOD_Attribute", "ZKey",
                                        vLOD, "LOD_Attribute", "ZKey" ) != 0 )
      {
         // If current CtrlMapContext exists, exclude it.
         if ( CheckExistenceOfEntity( vDialog, "CtrlMapContext" ) == zCURSOR_SET )
            ExcludeEntity( vDialog, "CtrlMapContext", zREPOS_PREV );

         // If current CtrlMapLOD_Attribute exists, exclude it.
         if ( CheckExistenceOfEntity( vDialog, "CtrlMapLOD_Attribute" ) == zCURSOR_SET )
         {
            ExcludeEntity( vDialog, "CtrlMapLOD_Attribute", zREPOS_PREV );
         }

         IncludeSubobjectFromSubobject( vDialog, "CtrlMapLOD_Attribute",
                                        vLOD, "LOD_Attribute", zPOS_AFTER );
      }

      // Set up default View Obj Ref and Entity for next mapping.
      if ( CheckExistenceOfEntity( vTZPNTROO, "CtrlWork" ) != zCURSOR_SET )
         CreateEntity( vTZPNTROO, "CtrlWork", zPOS_AFTER );

      SetAttributeFromAttribute( vTZPNTROO, "CtrlWork", "LastMapViewZKey",
                                 vDialog, "CtrlMapView", "ZKey" );
      SetAttributeFromAttribute( vTZPNTROO, "CtrlWork", "LastMapEntityZKey",
                                 vDialog, "CtrlMapRelatedEntity", "ZKey" );

      DropMetaOI( vSubtask, vLOD );

      // Set up Domain Context mapping.
      CtrlContextMappingInit( vSubtask );

      // Accept control subobject
      // Removed 4/20/07 by DonC because the accept is done when the Control window is OK'd.
   // AcceptSubobject( vDialog, "CtrlMap" );

  //x if ( GetViewByName( vDialog, "NoRefresh", vSubtask, zLEVEL_TASK ) > 0 )
  //x    SetWindowActionBehavior( vSubtask, zWAB_ReturnToParentWithRefresh, 0, 0 );

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    CtrlContextMappingInit
   //
   // PURPOSE:  This function builds the MapDomain View for the Context
   //           Combo Box, if there is current mapping.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int CtrlContextMappingInit( View vSubtask )
   {
      zVIEW    vDialog = new zVIEW();
      zVIEW    vCM_List = new zVIEW();
      zVIEW    vDomain = new zVIEW();
      int      lZKey;

      GetViewByName( vDialog, "TZCONTROL", vSubtask, zLEVEL_TASK );
      RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_DOMAIN_META );

      if ( CheckExistenceOfEntity( vDialog, "CtrlMapER_Attribute" ) == zCURSOR_SET )
      {
         // Activate the Domain for the current CtrlMapER_Domain.
         lZKey = GetIntegerFromAttribute( vDialog, "CtrlMapER_Domain", "ZKey" );
         SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
         ActivateMetaOI( vSubtask, vDomain, vCM_List, zREFER_DOMAIN_META, 0 );
      }
      else
         ActivateEmptyObjectInstance( vDomain, "TZDGSRCO", vSubtask, 0 );

      SetNameForView( vDomain, "MapDomain", vSubtask, zLEVEL_TASK );
      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SEL_ATTR_InitReuse
   //
   // PURPOSE:  This function activates the LOD associated with the
   //           current view.  If no view is currently defined, it
   //           temporarily sets up a dummy view.  I'm not sure what
   //           it should eventually do.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_InitReuse( View vSubtask )
   {
      zVIEW    vDialogC = new zVIEW();
      zVIEW    vDialogW = new zVIEW();
      zVIEW    vLOD = new zVIEW();
      zVIEW    vCM_List = new zVIEW();
      zVIEW    vTZPNTROO = new zVIEW();
      int      lZKey;

      GetViewByName( vDialogW, "TZWINDOW", vSubtask, zLEVEL_TASK );
      GetViewByName( vDialogC, "TZCONTROL", vSubtask, zLEVEL_TASK );
      RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_LOD_META );

      // If CtrlMapView exists, use the corresponding LOD for mapping.
      // Use CtrlMapView ZKey to locate ViewObjRef and then
      // LOD ZKey to locate LPLR LOD meta.
      if ( CheckExistenceOfEntity( vDialogC, "CtrlMapView" ) == zCURSOR_SET )
      {
         lZKey = GetIntegerFromAttribute( vDialogC, "CtrlMapView", "ZKey" );

         // Make sure we find the right ViewObjRef!  If not we will assume that
         // something has happened to the mapping and we will do the default
         // processing below. // DKS 2001.01.09
         if ( SetCursorFirstEntityByInteger( vDialogW, "ViewObjRef", "ZKey",
                                             lZKey, "" ) >= zCURSOR_SET )
         {
            lZKey = GetIntegerFromAttribute( vDialogW, "LOD", "ZKey" );
            SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
            ActivateMetaOI( vSubtask, vLOD, vCM_List, zREFER_LOD_META, 0 );
            SetNameForView( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

            // Select current mapping in LOD.
            if ( CheckExistenceOfEntity( vDialogC, "CtrlMapLOD_Attribute" ) == zCURSOR_SET )
            {
               lZKey = GetIntegerFromAttribute( vDialogC, "CtrlMapRelatedEntity", "ZKey" );
               if ( SetCursorFirstEntityByInteger( vLOD, "LOD_Entity", "ZKey", lZKey, "" ) != zCURSOR_SET )
               {
                  String pchEntity;

                  pchEntity = GetAddrForAttribute( "", vDialogC, "CtrlMapRelatedEntity", "Name" );
                  SetCursorFirstEntityByString( vLOD, "LOD_Entity", "Name", pchEntity, "" );
                  TraceLineS( "LOD Entity not located by ZKey: ", pchEntity );
               }

            //xSetSelectStateOfEntityForSet( vLOD, "LOD_Entity", 1, 1 );
               GetIntegerFromAttribute( lZKey, vDialogC, "CtrlMapLOD_Attribute", "ZKey" );
               if ( SetCursorFirstEntityByInteger( vLOD, "LOD_Attribute", "ZKey", lZKey, "" ) != zCURSOR_SET )
               {
                  String pchAttribute;

                  pchAttribute = GetAddrForAttribute( "", vDialogC, "CtrlMapER_Attribute", "Name" );
                  SetCursorFirstEntityByString( vLOD, "ER_Attribute", "Name", pchAttribute, "LOD_Entity" );
                  TraceLineS( "LOD Attribute not located by ZKey: ", pchAttribute );
               }

            //xSetSelectStateOfEntityForSet( vLOD, "LOD_Attribute", 1, 1 );
            }

            return( 0 );
         }
      }

      // If no current mapping exists, try to use the last mapping
      // specified for the Entity and Attribute list boxes.  Also
      // set view to last view used.
      GetViewByName( vTZPNTROO, "TZPNTROO", vSubtask, zLEVEL_ANY );
      lZKey = GetIntegerFromAttribute( vTZPNTROO, "CtrlWork", "LastMapViewZKey" );
      if ( SetCursorFirstEntityByInteger( vDialogW, "ViewObjRef", "ZKey", lZKey, "" ) == zCURSOR_SET )
      {
         if ( CheckExistenceOfEntity( vDialogC, "CtrlMapView" ) == zCURSOR_SET )
            ExcludeEntity( vDialogC, "CtrlMapView", zREPOS_PREV );

         IncludeSubobjectFromSubobject( vDialogC, "CtrlMapView", vDialogW, "ViewObjRef", zPOS_AFTER );
         lZKey = GetIntegerFromAttribute( vDialogW, "LOD", "ZKey" );
         SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
         ActivateMetaOI( vSubtask, vLOD, vCM_List, zREFER_LOD_META, 0 );
         SetNameForView( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );

         // Position on correct LOD_Entity
         lZKey = GetIntegerFromAttribute( vTZPNTROO, "CtrlWork", "LastMapEntityZKey" );
         SetCursorFirstEntityByInteger( vLOD, "LOD_Entity", "ZKey", lZKey, "" );
      //xSetSelectStateOfEntityForSet( vLOD, "LOD_Entity", 1, 1 );
      }
      else
      {
         // Drop the SEL_LOD meta OI, if it exists.
         GetViewByName( vLOD, "SEL_LOD", vSubtask, zLEVEL_TASK );
         if ( isValid( vLOD ) )
            DropMetaOI( vSubtask, vLOD );
      }

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SEL_ATTR_Init
   //
   // PURPOSE:  This function activates the LOD associated with the
   //           current view.  If no view is currently defined, it
   //           temporarily sets up a dummy view.  I'm not sure what
   //           it should eventually do.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_Init( View vSubtask )
   {
      zVIEW    vDialogC = new zVIEW();
      zVIEW    vTZPNTROO = new zVIEW();

      GetViewByName( vDialogC, "TZCONTROL", vSubtask, zLEVEL_TASK );

      // Create work entity, if it doesn't exist.
      GetViewByName( vTZPNTROO, "TZPNTROO", vSubtask, zLEVEL_ANY );
      if ( CheckExistenceOfEntity( vTZPNTROO, "CtrlWork" ) != zCURSOR_SET )
         CreateEntity( vTZPNTROO, "CtrlWork", zPOS_AFTER );

   //xif ( MiGetUpdateForView( vDialogC ) )
      {
         // If CtrlMap doesn't exist, create it.
         if ( CheckExistenceOfEntity( vDialogC, "CtrlMap" ) != zCURSOR_SET )
         {
            CreateTemporalMetaEntity( vSubtask, vDialogC, "CtrlMap", zPOS_AFTER );
         }
         else
         {
            CreateTemporalSubobjectVersion( vDialogC, "CtrlMap" );
         }
      }

      // Continue initialization in reusable operation.
      SEL_ATTR_InitReuse( vSubtask );

      return( 0 );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //    OPERATION: SEL_ATTR_Postbuild
   //
   /////////////////////////////////////////////////////////////////////////////
   public int SEL_ATTR_Postbuild( View vSubtask )
   {
      zVIEW  vDialog = new zVIEW();

      GetViewByName( vDialog, "TZWINDOW", vSubtask, zLEVEL_TASK );

      if ( MiGetUpdateForView( vDialog ) == 0 )
      {
      //xSetCtrlState( vSubtask, "ViewName", zCONTROL_STATUS_ENABLED, FALSE );
      //xSetCtrlState( vSubtask, "EntityList", zCONTROL_STATUS_ENABLED, FALSE );
      //xSetCtrlState( vSubtask, "AttributeList", zCONTROL_STATUS_ENABLED, FALSE );
      }

      return( 0 );

   } // SEL_ATTR_Postbuild

   //BL, 2000.01.04 Bugfix for Repository -- If an Entity is included and created, then remove the create flag.
   private int fnRemoveCreateFlag( View pvMOI )
   {
      StringBuilder sbEntityName = new StringBuilder();
      MutableInt miLevel = new MutableInt();
      zVIEW  vMOI = new zVIEW();
      int    nRC;

      CreateViewFromViewForTask( vMOI, pvMOI, null );

      // Root-Entity.
      zstrcpy( sbEntityName, "" );
      zGetFirstEntityNameForView( vMOI, sbEntityName );

      DefineHierarchicalCursor( vMOI, sbEntityName.toString() );
      nRC = SetCursorNextEntityHierarchical( miLevel, sbEntityName, vMOI );

      while ( nRC >= zCURSOR_SET )
      {
         if ( GetIncrementalUpdateFlags( vMOI, sbEntityName.toString(), zSET_INCR_CREATED ) == 1 )
         {
            if ( GetIncrementalUpdateFlags( vMOI, sbEntityName.toString(), zSET_INCR_INCLUDED ) == 1 )
            {
               // zSET_INCR_PERSISTENT clears the "created" flag.
               SetIncrementalUpdateFlags( vMOI, sbEntityName.toString(), zSET_INCR_PERSISTENT | zSET_INCR_CURSORPOS );
            }
         }

         nRC = SetCursorNextEntityHierarchical( miLevel, sbEntityName, vMOI );
      }

      DropHierarchicalCursor( vMOI );
      DropView( vMOI );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private int fnCheckForDuplicateName( View MOI_View, View vLPLR, String szMetaOI_Name, int lMetaOI_ZKey, int nNewInd )
   {
      int    nDupFound = 0;
      String szNewName = "";
      int    lType;
      zVIEW  vTempLPLR = new zVIEW();

      if ( nNewInd == 1 )
      {
         // Adding New Meta
         if ( SetCursorFirstEntityByString( vLPLR, "W_MetaDef", "Name", szMetaOI_Name, "" ) == zCURSOR_SET )
         {  // Duplicate Named Meta exists
            szNewName = GetVariableFromAttribute( szNewName, 0, zTYPE_STRING, 125, vLPLR, "W_MetaType", "Type", "CM_Type", 0 );
            nDupFound = 1;
         }
      }
      else
      {
         // Updating existing Meta
         if ( SetCursorFirstEntityByString( vLPLR, "W_MetaDef", "Name", szMetaOI_Name, "" ) == zCURSOR_SET )
         {
            do
            {
               if ( CompareAttributeToInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey ) != 0 )
               {
                  // Duplicately Named Meta exists
                  szNewName = GetVariableFromAttribute( szNewName, 0, zTYPE_STRING, 125, vLPLR, "W_MetaType", "Type", "CM_Type", 0 );
                  nDupFound = 1;
                  break;
               }

            } while ( SetCursorNextEntityByString( vLPLR, "W_MetaDef", "Name", szMetaOI_Name, "" ) == zCURSOR_SET );
         }
      }

      if ( nDupFound == 0 )
      {
         // Check one last time for Duplicate Name with a pending Delete.
         // We won't do the check for Domains and Global Operations since they
         // are "sub metas" and there is no problem if a pending delete exists
         // for a Domain or Global Operation of the same name.

         lType = GetIntegerFromAttribute( vLPLR, "W_MetaType", "Type" );
         if ( lType >= 2000 )
         {
            lType = lType - 2000;
         }

         if ( lType != zSOURCE_DOMAIN_META &&
              lType != zSOURCE_GO_META )
         {
            CreateViewFromViewForTask( vTempLPLR, vLPLR, vLPLR );
            SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaType", "Type", lType, "" );
            if ( SetCursorFirstEntityByString( vTempLPLR, "W_MetaDef", "Name", szMetaOI_Name, "" ) == zCURSOR_SET )
            {
               do
               {
                  if ( CompareAttributeToInteger( vTempLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey ) != 0 )
                  {   // Duplicately Named Meta exists
                     GetVariableFromAttribute( szNewName, 0, zTYPE_STRING, 125, vTempLPLR, "W_MetaType", "Type", "CM_Type", 0 );
                     nDupFound = 1;
                     break;
                  }

               } while ( SetCursorNextEntityByString( vTempLPLR, "W_MetaDef", "Name", szMetaOI_Name, "" ) == zCURSOR_SET );
            }

            DropView( vTempLPLR );
         }
      }

      if ( nDupFound == 1 )
      {
         StringBuilder sbMsg = new StringBuilder();
         zstrcpy( sbMsg, "A component of type " );
         zstrcat( sbMsg, szNewName );
         zstrcat( sbMsg, " already exists\n" );
         zstrcat( sbMsg, "with the name of the component you are trying to add," );
         zstrcat( sbMsg, "\n       " );
         zstrcat( sbMsg, szMetaOI_Name );
         zstrcat( sbMsg, "\nSave of the component is aborting." );
         MessageSend( vLPLR, "CM00702", "Configuration Management", sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }
      else
      {
         return 0;
      }
   }

   /////////////////////////////////////////////////////////////////////////////
   // The following function checks the ZeidonRT.INI and Zeidon.INI for item
   // AllowBinaryDataInSourceMetaFiles in the [ZeidonTools] section.  Return
   // codes are as follows:
   //    1 - INI item is 'Y' or not specified
   //          (the meta file is written with the same manner as it existed)
   //    2 - INI item is 'F' (force to old - with binary - format
   //          regardless of it's current format)
   //    3 - INI item is 'N' (write with the new format regardless of its
   //          current format)
   /////////////////////////////////////////////////////////////////////////////
   private int fnAllowBinaryDataInSourceMetaFiles( View lpTaskView )
   {
      StringBuilder sbFileName = new StringBuilder();
      String szBuffer = "";
    /*
      SysReadZeidonIni( -1, "[Workstation]", "LocalDir", sbFileName );
      if ( sbFileName.length() > 0 )
      {
         SysAppendcDirSep( sbFileName );
         zstrcat( sbFileName, "ZeidonRT.ini" );
         GetPrivateProfileString( "ZeidonTools", "AllowBinaryDataInSourceMetaFiles", "",
                                  szBuffer, 256, sbFileName );
      }
    */
      if ( szBuffer.isEmpty() == true )
      {
         SysReadZeidonIni( -1, "[ZeidonTools]", "AllowBinaryDataInSourceMetaFiles", szBuffer );
      }

      if ( szBuffer.charAt( 0 ) == 'N' || szBuffer.charAt( 0 ) == 'n' )
      {
         return 3;
      }
      else
      if ( szBuffer.charAt( 0 ) == 'F' || szBuffer.charAt( 0 ) == 'f' )
      {
         return 2;
      }
      else
      {
         return 1;
      }
   }

   //./ ADD NAME=MiSetOI_ReleaseForView
   // Source Module=kzoemiaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:   MiSetOI_ReleaseForView
   //
   //  PURPOSE: For the specified view, set the OI Release.  Intended
   //           for use by version checking routines.  The Release string
   //           is set in the caller provided string which should be a
   //           maximum of eight bytes (plus 1 for the null terminator for
   //           a total of 9).  The release strings are of the format:
   //           9999.9x9  where the 9's represent digits and the x represents
   //           a character, a-z (e.g.  10.1b2).
   //
   //  RETURNS:    0 - error
   //              otherwise - new release has been set for the OI
   //
   /////////////////////////////////////////////////////////////////////////////
   private int MiSetOI_ReleaseForView( View lpView, String cpcNewRelease )
   {
    /*
      LPTASK     lpCurrentTask;
      LPVIEWCSR  lpViewCsr;
      LPVIEWOI   lpViewOI;

      // If task not active or disabled, or view csr invalid, return 0.
      if ( (lpCurrentTask = fnOperationCall( iMiSetOI_ReleaseForView, lpView,
                                             zVALID_VIEW_CSR )) == 0 )
      {
         return 0;
      }

      lpViewCsr = zGETPTR( lpView->hViewCsr );
      lpViewOI  = zGETPTR( lpViewCsr->hViewOI );
      zstrncpy( lpViewOI->szRelease, cpcNewRelease, 8 );
      lpViewOI->szRelease[ 8 ] = 0;

      fnOperationReturn( iMiSetOI_ReleaseForView, lpCurrentTask );
    */
      return( 1 );
   }

   //./ ADD NAME=MiCompareOI_ReleaseToRelease
   // Source Module=kzoemiaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:   MiCompareOI_ReleaseToRelease
   //
   //  PURPOSE: Compare the view's release to the specified Release.  Intended
   //           for use by version checking routines.  The Release string
   //           is set in the caller provided string which should be a
   //           maximum of eight byte (plus 1 for the null terminator for
   //           a total of 9).  The release strings are of the format:
   //           9999.9x9  where the 9's represent digits and the x represents
   //           a character, a-z (e.g.  10.1b2).
   //
   //           Note that the CompareRelease buffer will not be altered by
   //           this routine, but processing within the routine is simplified
   //           if it is not declared const.
   //
   //  RETURNS:    < 0 - OI Release < CompareRelease
   //              = 0 - OI Release == CompareRelease
   //              > 0 - OI Release > CompareRelease
   //
   /////////////////////////////////////////////////////////////////////////////
   private int MiCompareOI_ToRelease( View lpView, String pchCompareRelease )
   {
    /*
      LPTASK            lpCurrentTask;
      LPVIEWCSR         lpViewCsr;
      LPVIEWOI          lpViewOI;
      int            nReleaseOI;
      int            nReleaseCmpr;
      zPCHAR            pchOI;
      zPCHAR            pchCmpr;
      zPCHAR            pchTemp;

      // If task not active or disabled, or view csr invalid, return 0.
      if ( (lpCurrentTask = fnOperationCall( iMiCompareOI_ReleaseToRelease,
                                             lpView, zVALID_VIEW_CSR )) == 0 )
      {
         return 0;
      }

      lpViewCsr = zGETPTR( lpView.->hViewCsr );
      lpViewOI  = zGETPTR( lpViewCsr->hViewOI );

      pchOI   = lpViewOI->szRelease;
      pchCmpr = pchCompareRelease;

      // if the number of digits preceding the '.' is larger in one release
      // value than the other, that release value is larger
      pchTemp = zstrchr( pchOI, '.' );
      if ( pchTemp )
      {
         nReleaseOI = pchTemp - pchOI;
      }
      else
      {
         nReleaseOI = 0;
      }

      pchTemp = zstrchr( pchCmpr, '.' );
      if ( pchTemp )
      {
         nReleaseCmpr = pchTemp - pchCmpr;
      }
      else
      {
         nReleaseCmpr = 0;
      }

      if ( nReleaseOI == nReleaseCmpr )
      {
         if ( pchOI && pchCmpr )
         {
            nReleaseCmpr = zstrcmp( pchOI, pchCmpr );
            if ( nReleaseCmpr < 0 )
            {
               nReleaseCmpr = -1;
            }
            else
            if ( nReleaseCmpr > 0 )
            {
               nReleaseCmpr = 1;
            }
         }
         else
         if ( pchOI )
         {
            nReleaseCmpr = 1;
         }
         else
         if ( pchCmpr )
         {
            nReleaseCmpr = -1;
         }
         else
         {
            nReleaseCmpr = 0;
         }
      }
      else
      if ( nReleaseOI > nReleaseCmpr )
      {
         nReleaseCmpr = 1;
      }
      else
      {
         nReleaseCmpr = -1;
      }

      fnOperationReturn( iMiCompareOI_ReleaseToRelease, lpCurrentTask );
      return( nReleaseCmpr );
    */
      return 0;
   }

   private View ConvertDialog( View vSubtask, View vMOI, String s )
   {
      return vMOI;  // TODO:  implement if/when needed
   }

   private View ConvertReport( View vSubtask, View vMOI, String s )
   {
      return vMOI;  // TODO:  implement if/when needed
   }

   private View ConvertXSLT( View vSubtask, View vMOI, String s )
   {
      return vMOI;  // TODO:  implement if/when needed
   }

   private void oTZZOXODO_SaveXOD( View vSubtask, View vMOI )
   {
      // TODO:  implement when needed
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:   MiGetInstanceID_ForView
   //
   //  PURPOSE: Get the unique instance identifier for a view.
   //
   //  RETURNS: zCALL_ERROR  - ERROR in call
   //           !zCALL_ERROR - an instance ID successfully returned
   //                          RETURNED AS A HANDLE!
   //
   /////////////////////////////////////////////////////////////////////////////
   private long MiGetInstanceID_ForView( View lpView )
   {
      int   lRC = zCALL_ERROR;

      if ( isValid( lpView ) )
      {
         return lpView.getId();   // ??? dks
      }

      return( lRC );
   }

   private int fnCommitMetaOI( View vSubtask, View vMOI, int nType, View vActiveMetas, View vZeidonCM, View vLPLR ) throws IOException
   {
      int nRC, nEntityType;
      boolean  bNewMeta;
      int ReferViewsActive, ReferOI_Active;
      zVIEW vWork1 = new zVIEW();
      zVIEW CM_View = new zVIEW();
      zVIEW MOI_ExecView = new zVIEW();
      zVIEW IncludeView = new zVIEW();
      zVIEW WKS_View = new zVIEW();
      zVIEW vTempLPLR = new zVIEW();
      String  szMetaOI_Name;   // protect from long name
      StringBuilder sbMetaOI_File = new StringBuilder();   // protect from long name
      String  szSubEntityName;
      String  szSubOI_Name;
      StringBuilder sbObjectName = new StringBuilder();
      int  lSubType;
      int  lMetaOI_ZKey;
      int  lSubOI_ZKey;
      StringBuilder sbFileSpec = new StringBuilder();
   // String  szTempFileName[ 2 * zMAX_FILESPEC_LTH + 1 ];
   // String  szTempFileName[ zMAX_FILENAME_LTH + 1 ];
      String  szDesc;
      StringBuilder sbMsg = new StringBuilder();
      String  szTimestamp = "";
      int     lTaskID;
      long    lMOI_InstanceID;
      String  szCM_ViewName;
      StringBuilder sbNamedView = new StringBuilder();

      TraceLineS( "fnCommitMetaOI", "=======================================" );

      ReferOI_Active = 0;
      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      szMetaOI_Name = GetStringFromAttribute( vMOI, SRC_CMOD[ nType ].szOD_ROOT, SRC_CMOD[ nType ].szOD_NAME );

      // If the view is read-only, then we can't commit it.
      nRC = MiGetUpdateForView( vMOI );
      if ( (nEntityType == CM_REFER_TYPE) || (nRC == 0) )
      {
         zstrcpy( sbMsg, "The meta passed to CommitMetaOI, " );
         zstrcat( sbMsg, szMetaOI_Name );
         zstrcat( sbMsg, "is not an Active Meta. See Trace" );
         TraceLineS( "Zeidon Configuration Management", sbMsg.toString() );
         TraceLineI( "   Type passed is: ", nType );
         MessageSend( vSubtask, "CM00422", "Configuration Management",
                      sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

         return -1;
      }

      // Get the ZKey for the OI we are about to commit.
      lMetaOI_ZKey = GetIntegerFromAttribute( vMOI, SRC_CMOD[ nType ].szOD_ROOT, "ZKey" );

      // Position on the correct W_MetaType. If it doesn't exist, create one.
      if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nType, "" ) != zCURSOR_SET )
      {
         // DGC? - Why are we creating an entity?  Shouldn't it already exist?
         // If we are commiting an OI, then it has been activated and therefore
         // the MetaType entity should have been created.

         // It does already exist for reports (so we don't get here ... DKS).
         CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", nType );
      }

      // Position on the correct W_MetaType. If it doesn't exist, create one.
      if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nType + 2000, "" ) != zCURSOR_SET )
      {
         // DGC? - Why are we creating an entity?  Shouldn't it already exist?
         // If we are commiting an OI, then it has been activated and therefore
         // the MetaType entity should have been created.

         // It does already exist for reports (so we don't get here ... DKS).
         CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vLPLR, "W_MetaType",
                                 "Type", nType + 2000 );
      }

      if ( SetCursorFirstEntityByAttr( vActiveMetas, "LPLR", "Name", vLPLR, "LPLR", "Name", "" ) != zCURSOR_SET )
      {
         MessageSend( vSubtask, "CM00423", "Configuration Management",
                      "Unable to locate LPLR Entity in ZeidonCM for CommitMetaOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );

         return -1;
      }

      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type", nType, "" ) != zCURSOR_SET )
      {
         CreateEntity( vActiveMetas, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vActiveMetas, "W_MetaType", "Type", nType );
      }

      // Now check for Duplicately named Metas.
      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" ) < zCURSOR_SET )
      {
         nRC = fnCheckForDuplicateName( vMOI, vLPLR, szMetaOI_Name, lMetaOI_ZKey, 1 ); // Adding New Meta
      }
      else
      {
         if ( CompareAttributeToInteger( vActiveMetas, "W_MetaDef",
                                         "UpdateInd", 3 ) == 0 )
         {
            MessageSend( vSubtask, "CM00424", "Configuration Management",
                         "Deleted Meta Def passed to CommitMetaOI",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         nRC = fnCheckForDuplicateName( vMOI, vLPLR, szMetaOI_Name, lMetaOI_ZKey, 0 );  // Updating Meta
      }

      if ( nRC == -1 )
      {
         return -1;
      }

      if ( nRC == 1 ) // duplicate found and renamed
      {
         szMetaOI_Name = GetStringFromAttribute( vMOI, SRC_CMOD[ nType ].szOD_ROOT, SRC_CMOD[ nType ].szOD_NAME );
      }

      if ( nType == zSOURCE_DOMAINGRP_META || nType == zSOURCE_GOPGRP_META )
      {
         // For Global Operation Groups and Domain Groups, check each Global
         // Operation or Domain within the Group to make sure there are no
         // duplicates.
         if ( nType == zSOURCE_DOMAINGRP_META )
         {
            szSubEntityName = "Domain";
            lSubType = zSOURCE_DOMAIN_META;
         }
         else
         {
            szSubEntityName = "Operation";
            lSubType = zSOURCE_GO_META;
         }

         CreateViewFromViewForTask( vTempLPLR, vLPLR, vSubtask );
         SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaType", "Type", lSubType + 2000, "" );
         nRC = SetCursorFirstEntity( vMOI, szSubEntityName, "" );
         while ( nRC >= zCURSOR_SET )
         {
            szSubOI_Name = GetStringFromAttribute( vMOI, szSubEntityName, "Name" );
            lSubOI_ZKey = GetIntegerFromAttribute( vMOI, szSubEntityName, "ZKey" );
            nRC = fnCheckForDuplicateName( vMOI, vTempLPLR, szSubOI_Name, lSubOI_ZKey, 0 );
            if ( nRC == -1 )
            {
               return -1;
            }

            nRC = SetCursorNextEntity( vMOI, szSubEntityName, "" );
         }

         // For Global Operation Groups and Domain Groups, loop through each
         // operation or domain in the Group and update the names, in case any
         // of those names were changed. Do this first for zSOURCE type and
         // then for zREFER type.
         for ( nRC = SetCursorFirstEntity( vMOI, szSubEntityName, "" );
               nRC >= zCURSOR_SET;
               nRC = SetCursorNextEntity( vMOI, szSubEntityName, "" ) )
         {
            szSubOI_Name = GetStringFromAttribute( vMOI, szSubEntityName, "Name" );
            lSubOI_ZKey = GetIntegerFromAttribute( vMOI, szSubEntityName, "ZKey" );

            // zSOURCE type
            SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaType", "Type", lSubType, "" );
            nRC = SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaDef", "CPLR_ZKey", lSubOI_ZKey, "" );
            if ( nRC >= zCURSOR_SET )
            {
               SetAttributeFromString( vTempLPLR, "W_MetaDef", "Name", szSubOI_Name );
            }

            // zREFER type
            SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaType", "Type", lSubType + 2000, "" );
            nRC = SetCursorFirstEntityByInteger( vTempLPLR, "W_MetaDef", "CPLR_ZKey", lSubOI_ZKey, "" );
            if ( nRC >= zCURSOR_SET )
            {
               SetAttributeFromString( vTempLPLR, "W_MetaDef", "Name", szSubOI_Name );
            }
         }

         DropView( vTempLPLR );
      }

   // lTaskID = SysGetTaskFromView( vMOI );
      lTaskID = Integer.decode( SysGetTaskFromView( vSubtask ).getTaskId() );

      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" ) != zCURSOR_SET )
      {
         // New Meta
         bNewMeta = true;
      }
      else
      {
         // a W_MetaDef exists for this OI in the CM Active list
         bNewMeta = false;
         szCM_ViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" );
         if ( GetViewByName( CM_View, szCM_ViewName + ".u", vZeidonCM, zLEVEL_SUBTASK ) < 0 )
         {
            // If such a named view was not found, then the meta was not opened
            // for update.
            MessageSend( vSubtask, "CM00425", "Configuration Management",
                         "The Meta being committed was not Activated for update.",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         lMOI_InstanceID = MiGetInstanceID_ForView( CM_View );
         if ( CompareAttributeToInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskID ) != 0 )
         {
            if ( CompareAttributeToInteger( vActiveMetas, "W_MetaDef", "TaskID", 0 ) == 0 )
            {
               MessageSend( vSubtask, "CM00426", "Configuration Management",
                            "The Meta passed to CommitMetaOI was not opened for Update!",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }
            else
            {
               if ( MiGetInstanceID_ForView( vMOI ) != lMOI_InstanceID )
               {
                  zstrcpy( sbMsg, "The View to the Meta passed to CommitMetaOI" +
                                  "\nis not to the the same Object Instance for" +
                                  "\nwhich the view was originally opened." );
                  MessageSend( vSubtask, "CM00427", "Configuration Management", sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  return -1;
               }
            }
         }

         // Replace the last String in szCM_ViewName with an 'r'.
         if ( GetViewByName( CM_View, szCM_ViewName + 'r', vZeidonCM, zLEVEL_SUBTASK ) > 0 )
         {
            // REFER OI of Meta exists - Check if there are Views to it
            ReferOI_Active = 1;
            ReferViewsActive = 0;
            lMOI_InstanceID = MiGetInstanceID_ForView( CM_View );
            nRC = DriverApplication.SfGetFirstNamedView( vWork1, sbNamedView, vZeidonCM, zLEVEL_SUBTASK );
            while ( nRC > 0 )
            {
               if ( sbNamedView.substring( 0, 5 ).compareTo( "__CM." ) == 0 )
               {
                  zVIEW vWork2 = new zVIEW();
                  StringBuilder sbTaskView = new StringBuilder();


                  nRC = DriverApplication.SfGetFirstNamedView( vWork2, sbTaskView, vWork1, zLEVEL_SUBTASK );
                  while ( nRC > 0 )
                  {
                     if ( sbTaskView.charAt( 0 ) == 'r' )
                     {
                        if ( MiGetInstanceID_ForView( vWork2 ) == lMOI_InstanceID )
                        {
                           ReferViewsActive = 1;
                           break;
                        }
                     }

                     nRC = DriverApplication.SfGetNextNamedView( vWork2, sbTaskView, vWork1, zLEVEL_SUBTASK );
                  }
               }

               if ( ReferViewsActive == 1 )
               {
                  return 0;
               }

               nRC = DriverApplication.SfGetNextNamedView( vWork1, sbNamedView, vZeidonCM, zLEVEL_SUBTASK );
            }

            DropObjectInstance( CM_View );
         }
      }

      if ( GetViewByName( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK ) <= 0 )
      {
         return -1;
      }

      fnGetDirectorySpec( vMOI, sbFileSpec, nType );

      switch ( nType )
      {
         case zSOURCE_VOR_META:
            zltoxa( lMetaOI_ZKey, sbMetaOI_File );
            zstrcat( sbFileSpec, sbMetaOI_File.toString() );
            break;

         default:
            if ( bNewMeta == false &&
                 CompareAttributeToString( vActiveMetas, "W_MetaDef", "Name", szMetaOI_Name ) != 0 )
            {
               // FileName change
               nRC = zstrlen( sbFileSpec );
               StringBuilder sb = new StringBuilder( GetStringFromAttribute( vActiveMetas, "W_MetaDef", "Name" ) );
               TruncateName8( sb );
               sbFileSpec.append( sb );
               sbFileSpec.append( SRC_CMOD[ nType ].szOD_EXT );
               SysOpenFile( vLPLR, sbFileSpec.toString(), COREFILE_DELETE );
               if ( nType == zSOURCE_DIALOG_META || nType == zSOURCE_LOD_META )
               {
                  GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "ExecDir" );
                  SysAppendcDirSep( sbFileSpec );
                  nRC = zstrlen( sbFileSpec );
                  sb.setLength( 0 );
                  sb.append( GetStringFromAttribute( vActiveMetas, "W_MetaDef", "Name" ) );
                  TruncateName8( sb );
                  sbFileSpec.append( sb );
                  if ( nType == zSOURCE_LOD_META )
                  {
                     zstrcat( sbFileSpec, ".XOD" );
                  }
                  else
                  {
                     zstrcat( sbFileSpec, ".XWD" );
                  }

                  SysOpenFile( vLPLR, sbFileSpec.toString(), COREFILE_DELETE );
               }

               fnGetDirectorySpec( vLPLR, sbFileSpec, nType );
            }

            zstrcpy( sbMetaOI_File, szMetaOI_Name );
            TruncateName8( sbMetaOI_File );
            zstrcat( sbFileSpec, sbMetaOI_File );

            break; // default.

      } // switch ( nType )...

      zstrcat( sbFileSpec, SRC_CMOD[ nType ].szOD_EXT );
      szDesc = GetVariableFromAttribute( 0, zTYPE_STRING, 255, vMOI, SRC_CMOD[ nType ].szOD_ROOT, "Desc", "", 0 );

      // Commit the XWD/XRA if a Dialog Meta.
      if ( nType == zSOURCE_DIALOG_META || nType == zSOURCE_REPORT_META || nType == zSOURCE_XSLT_META )
      {
         zVIEW vDialogSubtask = new zVIEW();

         GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "ExecDir" );
         SysAppendcDirSep( sbFileSpec );
         zstrcat( sbFileSpec, sbMetaOI_File );

         GetViewByName( vDialogSubtask, "TZCM_DialogSubtask", vSubtask, zLEVEL_TASK );
   //    MOI_ExecView = ConvertDialog( vDialogSubtask, vMOI, "" );
         if ( nType == zSOURCE_DIALOG_META )
         {
            StringBuilder sbRemotePath = new StringBuilder();
            View  vXRA;
            int nLth;

            nLth = zstrlen( sbFileSpec );
            MOI_ExecView.setView( ConvertDialog( vSubtask, vMOI, "" ) );
            zstrcat( sbFileSpec, ".XWD" );
         }
         else
         if ( nType == zSOURCE_REPORT_META )
         {
            // Create the Report executable file (.XRP) in the executable meta
            // directory, which is a flattened version of the Report source
            // (.PRP) in the source meta directory.  The executable is flattened
            // by putting all controls to the Control level (moving CtrlCtrl
            // entities directly under the appropriate Group entities as Control
            // entities ... no recursive relationships).
         /*
            fnGetDirectorySpec( szFileSpec, nType );
            zstrcpy( szMetaOI_File, szMetaOI_Name );
            TruncateName8( szMetaOI_File );
            zstrcat( szFileSpec, szMetaOI_File );
            zstrcat( szFileSpec, SRC_CMOD[ nType ].szOD_EXT );

            GetViewByName( &vTempLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
            GetStringFromAttribute( szFileSpecExec,
                                    vTempLPLR, "LPLR", "ExecDir" );
            SysAppendcDirSep( szFileSpecExec );
            zstrcat( szFileSpecExec, szMetaOI_File );
            zstrcat( szFileSpecExec, ".XRP" );
            SysCopyFile( szFileSpec, szFileSpecExec, true );
            break;
         */

            MOI_ExecView.setView( ConvertReport( vLPLR, vMOI, "" ) );
            zstrcat( sbFileSpec, ".XRP" );
         }
         else
         {
            // Create the XSLT executable file (.XSL) in the executable meta
            // directory, which is a flattened version of the Report source
            // (.PSL) in the source meta directory.  The executable is flattened
            // by putting all controls to the Control level (moving CtrlCtrl
            // entities directly under the appropriate Group entities as Control
            // entities ... no recursive relationships).

            MOI_ExecView.setView( ConvertXSLT( vLPLR, vMOI, "" ) );
            zstrcat( sbFileSpec, ".XSL" );
         }

         nRC = CommitOI_ToFile( MOI_ExecView, sbFileSpec.toString(), zSINGLE );
         if ( nRC != 0 )
         {
            zstrcpy( sbMsg, "CommitMetaOI failed trying to save " );
            zstrcat( sbMsg, sbFileSpec );
            MessageSend( vSubtask, "CM00428", "Configuration Management", sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         DropObjectInstance( MOI_ExecView );
         fnGetDirectorySpec( vMOI, sbFileSpec, nType );
         zstrcat( sbFileSpec, sbMetaOI_File );
         zstrcat( sbFileSpec, SRC_CMOD[ nType ].szOD_EXT );
      }

      // Decide what control flags should be used based on the current file
      // version and the INI settings.
      nRC = fnAllowBinaryDataInSourceMetaFiles( vLPLR );
      if ( nRC == 1 )
      {
         // Keep it the same format.
         if ( MiCompareOI_ToRelease( vMOI, releaseCompatible ) <= 0 )
         {
            MiSetOI_ReleaseForView( vMOI, releaseCompatible );
            nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), zSINGLE );
         }
         else
         {
            MiSetOI_ReleaseForView( vMOI, releaseCurrent );
            nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), zSINGLE | zENCODE_BLOBS | zNO_NULL_STRING_TERM );
         }
      }
      else
      if ( nRC == 2 )
      {
         // Force it to compatibility format.
         MiSetOI_ReleaseForView( vMOI, releaseCompatible );
         nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), zSINGLE );
      }
      else
      {
         // Use the current release version of the software.
         MiSetOI_ReleaseForView( vMOI, releaseCurrent );
         nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), zSINGLE | zENCODE_BLOBS | zNO_NULL_STRING_TERM );
      }

      if ( nRC != 0 )
      {
         zstrcpy( sbMsg, "CommitMetaOI failed trying to save " );
         zstrcat( sbMsg, sbFileSpec );
         MessageSend( vSubtask, "CM00429", "Configuration Management", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( ReferOI_Active == 1 )
      {
         if ( nType != zSOURCE_DIALOG_META && nType != zSOURCE_REPORT_META &&
              nType != zSOURCE_XSLT_META && nType != zSOURCE_UIS_META )
         {
            ActivateMetaOI_ByZKey( vSubtask, CM_View, vWork1, nType + 2000, zLEVEL_SUBTASK, lMetaOI_ZKey, 0 );

            // If we are committing a DomainGroup, relink it with any ERDs
            // that are currently in memory.
            if ( nType == zSOURCE_DOMAINGRP_META )
            {
               zVIEW vDomainGrp = new zVIEW();

               nRC = DriverApplication.SfGetFirstNamedView( vWork1, sbNamedView, vZeidonCM, zLEVEL_SUBTASK );
               while ( nRC > 0 )
               {
                  if ( sbNamedView.substring( 0, 5 ).compareTo( "__CM." ) == 0 )
                  {
                     sbObjectName.setLength( 0 );
                  }
                  else
                  {
                     MiGetObjectNameForView( sbObjectName, vWork1 );
                  }

                  if ( zstrcmp( sbObjectName.toString(), "TZEREMDO" ) == 0 )
                  {
                     // Loop through all Domains in ER and Relink any of those
                     // Domains to the current DomainGrp that are a part of that
                     // DomainGrp.
                     CreateViewFromViewForTask( vDomainGrp, vMOI, vSubtask );
                     for ( nRC = SetCursorFirstEntity( vWork1, "Domain", "EntpER_Model" );
                           nRC >= zCURSOR_SET;
                           nRC = SetCursorNextEntity( vWork1, "Domain", "EntpER_Model" ) )
                     {
                        nRC = SetCursorFirstEntityByAttr( vDomainGrp, "Domain", "ZKey", vWork1, "Domain", "ZKey", "" );
                        if ( nRC >= 0 )
                        {
                           RelinkInstanceToInstance( vWork1, "Domain", vDomainGrp, "Domain" );
                        }
                     }

                     DropView( vDomainGrp );
                  }

                  nRC = DriverApplication.SfGetNextNamedView( vWork1, sbNamedView, vZeidonCM, zLEVEL_SUBTASK );
               }
            }

            DropMetaOI( vSubtask, CM_View );
         }
      }

      // Reset to correct W_MetaType in case of PostActivate having changed
      // position.
      SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nType + 2000, "" );
      if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" ) != zCURSOR_SET )
      {
         // Corresponding W_MetaDef Not Found - New Meta.
         CreateEntity( vLPLR, "W_MetaDef", zPOS_AFTER );
         SetAttributeFromInteger( vLPLR, "W_MetaDef", "Status", 1 );
      }

      SetAttributeFromString( vLPLR, "W_MetaDef", "Name", szMetaOI_Name );
      szTimestamp = SysGetDateTime( szTimestamp );
      SetAttributeFromString( vLPLR, "W_MetaDef", "LastUpdateDate", szTimestamp );
      SetAttributeFromString( vLPLR, "W_MetaDef", "LastSyncDate", szTimestamp );
      SetAttributeFromInteger( vLPLR, "W_MetaDef", "UpdateInd", 2 );
      SetAttributeFromInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey );
      SetAttributeFromString( vLPLR, "W_MetaDef", "Desc", szDesc );

      // Include W_MetaDef to Active MetaDef if not already there.
      CreateViewFromViewForTask( IncludeView, vLPLR, vSubtask );
      SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nType, "" );
      if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" ) != zCURSOR_SET )
      {
         // New
         IncludeSubobjectFromSubobject( vLPLR, "W_MetaDef", IncludeView, "W_MetaDef", zPOS_AFTER );

         //BL, 1999.12.29  if check in, then this Meta does exists in
         //                View vActiveMetas
         if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" ) != zCURSOR_SET )
         {
            IncludeSubobjectFromSubobject( vActiveMetas, "W_MetaDef", vLPLR, "W_MetaDef", zPOS_AFTER );
         }

         szCM_ViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" );
         CreateViewFromViewForTask( CM_View, vMOI, vSubtask );
         SetNameForView( CM_View, szCM_ViewName + ".u", vZeidonCM, zLEVEL_SUBTASK );
         SetAttributeFromInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskID );
      }

      DropView( IncludeView );
      zgSortEntityWithinParent( zASCENDING, vLPLR, "W_MetaDef", "Name", "" );

      switch ( nType )
      {
         case zSOURCE_ERD_META:
            // Make sure that all LODs are eliminated from the Active Metas list.
            if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type",
                                                zSOURCE_LOD_META, "" ) >= zCURSOR_SET )
            {
               for ( nRC =  SetCursorFirstEntity( vActiveMetas, "W_MetaDef", "" );
                     nRC >= zCURSOR_SET;
                     nRC =  SetCursorNextEntity( vActiveMetas, "W_MetaDef", "" ) )
               {
                  GetStringFromAttribute( sbNamedView, vActiveMetas, "W_MetaDef", "CM_ViewName" );
                  zstrcat( sbNamedView, ".r" );
                  if ( GetViewByName( CM_View, sbNamedView.toString(), vZeidonCM, zLEVEL_SUBTASK ) > 0 )
                  {
                     DropObjectInstance( CM_View );
                  }

                  ExcludeEntity( vActiveMetas, "W_MetaDef", zREPOS_NONE );
               }
            }

            break;

         case zSOURCE_LOD_META:
            SetNameForView( vMOI, "TZZOLODO", vSubtask, zLEVEL_TASK );
            oTZZOXODO_SaveXOD( vSubtask, vMOI );

            // Get access to the newly built XOD.
            if ( GetViewByName( MOI_ExecView, "TZZOXODO", vSubtask, zLEVEL_TASK ) < 1 )
            {
               zstrcpy( sbMsg, "(fnCommitMetaOI) Unable to Access XOD.  XOD must be opened." );
               MessageSend( vSubtask, "CM00430", "Configuration Management", sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }

            // Save the XOD to a file.
            GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "ExecDir" );
            SysAppendcDirSep( sbFileSpec );
            zstrcat( sbFileSpec, sbMetaOI_File );
            zstrcat( sbFileSpec, ".XOD" );
            if ( CommitOI_ToFile( MOI_ExecView, sbFileSpec.toString(), zSINGLE ) != 0 )
            {
               zstrcpy( sbMsg, "Commit of XOD failed." );
               MessageSend( vSubtask, "CM00431", "Configuration Management", sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }

            break;

         case zSOURCE_PENV_META:
            MOI_ExecView.setView( CreatePE_ExecVersion( vLPLR, vMOI ) );
            GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "ExecDir" );
            SysAppendcDirSep( sbFileSpec );
            zstrcat( sbFileSpec, "ZEIDON.XPE" );
            if ( CommitOI_ToFile( MOI_ExecView, sbFileSpec.toString(), zSINGLE ) != 0 )
            {
               zstrcpy( sbMsg, "Commit of Executable Presentation Environment -\n" );
               zstrcpy( sbMsg, sbFileSpec );
               zstrcpy( sbMsg, " failed." );
               MessageSend( vSubtask, "CM00432", "Configuration Management", sbMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }

            break;

         case zSOURCE_GOPGRP_META:
         case zSOURCE_DOMAINGRP_META:
         {
            zVIEW vWorkRefer = new zVIEW();
            zVIEW vWorkSource = new zVIEW();
            int  lStatus;
            int  lSourceItem;
            int  lReferItem;
            String lpchItemName;
            String lpchGroupName;

            // We have just committed a group (e.g.  DomainGroup).  We need to
            // create a W_MetaDef entity for each item in the group (e.g.
            // domain) so that CM can then activate an item by name.

            // The logic is the same for both domain and GO groups, but some of
            // the values are different, so we set up some variables.
            if ( nType == zSOURCE_DOMAINGRP_META )
            {
               lReferItem    = zREFER_DOMAIN_META;
               lSourceItem   = zSOURCE_DOMAIN_META;
               lpchItemName  = "Domain";                 // Name of the item ent.
               lpchGroupName = "DomainGroup";            // Name of group entity.
            }
            else
            {
               lReferItem    = zREFER_GO_META;
               lSourceItem   = zSOURCE_GO_META;
               lpchItemName  = "Operation";              // Name of the item ent.
               lpchGroupName = "GlobalOperationGroup";   // Name of group entity.
            }

            CreateViewFromViewForTask( vWorkRefer, vLPLR, vSubtask );
            CreateViewFromViewForTask( vWorkSource, vLPLR, vSubtask );

// #ifdef DEBUG
//          SetNameForView( vWorkRefer, "WorkRefer", vSubtask, zLEVEL_TASK );
//          SetNameForView( vWorkSource, "WorkSource", vSubtask, zLEVEL_TASK );
// #endif

            // Get the value of W_MetaDef.Status from the group W_MetaDef. If the
            // attribute isn't set then set lStatus = -1. Otherwise lStatus will
            // be 0 or 1.  Later on when we're setting W_MetaDef.Status for the
            // domain, if lStatus = -1 then we'll leave W_MetaDef.Status as null.
            // NOTE:  We are assuming that the view vLPLR is pointing to the
            // correct group W_MetaDef.
            MutableInt mi = new MutableInt();
            if ( GetIntegerFromAttribute( mi, vLPLR, "W_MetaDef", "Status" ) < 0 )
            {
               lStatus = -1;
            }
            else
            {
               lStatus = mi.intValue();
            }

            // Find the item Meta type (eg Domain). If it doesn't exist, create it.
            if ( SetCursorFirstEntityByInteger( vWorkSource, "W_MetaType", "Type", lSourceItem, "" ) != zCURSOR_SET )
            {
               CreateEntity( vWorkSource, "W_MetaType", zPOS_LAST );
               SetAttributeFromInteger( vWorkSource, "W_MetaType", "Type", lSourceItem );
            }

            // Same for REFER
            if ( SetCursorFirstEntityByInteger( vWorkRefer, "W_MetaType", "Type", lReferItem, "" ) != zCURSOR_SET )
            {
               CreateEntity( vWorkRefer, "W_MetaType", zPOS_LAST );
               SetAttributeFromInteger( vWorkRefer, "W_MetaType", "Type", lReferItem );
            }

            // Loop through each of the items in the MetaDef, looking for items
            // with GroupName that matches the name of the group.  If it doesn't
            // exist in the Meta OI, then it's been deleted from the Meta and it
            // needs to be deleted from the item list.
            for ( nRC = SetCursorFirstEntityByAttr( vWorkRefer, "W_MetaDef", "GroupName", vMOI, lpchGroupName, "Name", "" );
                  nRC >= zCURSOR_SET;
                  nRC = SetCursorNextEntityByAttr( vWorkRefer, "W_MetaDef", "GroupName", vMOI, lpchGroupName, "Name", "" ) )
            {
               // Try to find the item in the Meta OI by ZKey.  If it doesn't exist,
               // then it no longer exists in the Meta OI and needs to be deleted
               // from the LPLR.
               if ( SetCursorFirstEntityByAttr( vMOI, lpchItemName, "ZKey", vWorkRefer, "W_MetaDef", "CPLR_ZKey", "" ) != zCURSOR_SET )
               {
                  DeleteEntity( vWorkRefer, "W_MetaDef", zREPOS_NONE );
               }
            }

            // Loop through each of the items in the group.  If it doesn't
            // exist in the Refer LPLR, add it to both source and refer.
            for ( nRC = SetCursorFirstEntity( vMOI, lpchItemName, "" );
                  nRC >= zCURSOR_SET;
                  nRC = SetCursorNextEntity( vMOI, lpchItemName, "" ) )
            {
               // Try to find the W_MetaDef by ZKey.  If it doesn't exist, then it
               // needs to be created.
               if ( SetCursorFirstEntityByAttr( vWorkRefer, "W_MetaDef", "CPLR_ZKey", vMOI, lpchItemName, "ZKey", "" ) != zCURSOR_SET )
               {
                  // Create entity in refer meta type
                  CreateEntity( vWorkRefer, "W_MetaDef", zREPOS_LAST );
                  SetAttributeFromAttribute( vWorkRefer, "W_MetaDef", "Name", vMOI, lpchItemName, "Name" );
                  SetAttributeFromAttribute( vWorkRefer, "W_MetaDef", "CPLR_ZKey", vMOI, lpchItemName, "ZKey" );
                  SetAttributeFromAttribute( vWorkRefer, "W_MetaDef", "GroupName", vMOI, lpchGroupName, "Name" );
                  if ( lStatus >= 0 )
                  {
                     SetAttributeFromInteger( vWorkRefer, "W_MetaDef", "Status", lStatus );
                  }

                  // Create entity in source meta type
                  CreateEntity( vWorkSource, "W_MetaDef", zREPOS_LAST );
                  SetAttributeFromAttribute( vWorkSource, "W_MetaDef", "Name", vMOI, lpchItemName, "Name" );
                  SetAttributeFromAttribute( vWorkSource, "W_MetaDef", "CPLR_ZKey", vMOI, lpchItemName, "ZKey" );
                  SetAttributeFromAttribute( vWorkSource, "W_MetaDef", "GroupName", vMOI, lpchGroupName, "Name" );

                  // If lStatus is not -1, then we need to set status in W_MetaDef.
                  if ( lStatus >= 0 )
                  {
                     SetAttributeFromInteger( vWorkSource, "W_MetaDef", "Status", lStatus );
                  }
               }

            } // for...

            DropView( vWorkRefer );
            DropView( vWorkSource );

            break;

         } // case zSOURCE_DOMAINGRP_META...

      } // switch ( nType )...

      if ( CommitLPLR( vLPLR ) == 0 )
      {
         if ( CommitWorkstation( WKS_View ) == 0 )
         {
            return( 1 );
         }
      }

      return -1;
   } // fnCommitMetaOI

   //./ ADD NAME=CommitMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CommitMetaOI
   //
   //  PURPOSE:    Commits an Zeidon Meta Object Instance by storing it to
   //              a portable file.
   //
   //  PARAMETERS: vMOI - the view of the OI to be stored
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    1 - Meta OI successfully committed
   //              0 - Meta is being referenced by another Zeidon Tool and
   //                  the Meta cannot be saved at this time
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   private int CommitMetaOI( View  vSubtask, View  vMOI, int nType ) throws IOException
   {
      zVIEW vOrigActiveMetas = new zVIEW();
      zVIEW vActiveMetas = new zVIEW();
      zVIEW vZeidonCM = new zVIEW();
      zVIEW vLPLR = new zVIEW();
      int  nRC;

      TraceLineS( "CommitMetaOI ", "========================================" );
      if ( GetViewByName( vLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) <= 0 )
      {
         MessageSend( vSubtask, "CM00433", "Configuration Management",
                      "Unable to locate TaskLPLR in CommitMetaOI", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) <= 0 )
      {
         MessageSend( vSubtask, "CM00434", "Configuration Management",
                      "Unable to locate ZeidonCM in CommitMetaOI", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( GetViewByName( vOrigActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK ) <= 0 )
      {
         MessageSend( vSubtask, "CM00435", "Configuration Management",
                      "Unable to locate OpenCM_Metas in CommitMetaOI", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      // Use a temporay view for vActiveMetas so that no subordinate processing
      // will alter vActiveMetas position (as ConvertDialog does in Domain
      // handling).
      CreateViewFromViewForTask( vActiveMetas, vOrigActiveMetas, vSubtask );

      nRC = fnCommitMetaOI( vSubtask, vMOI, nType, vActiveMetas, vZeidonCM, vLPLR );
      if ( nRC != 0 )
      {
         DropView( vActiveMetas );
         TraceLineI( "CommitMetaOI RC = ", nRC );
         return( nRC );
      }
      else
      {
         StringBuilder sbMsg = new StringBuilder();

         GetVariableFromAttribute( sbMsg, 0, zTYPE_STRING, 125, vActiveMetas,
                                   "W_MetaType", "Type", "CM_Type", 0 );
         sbMsg.append( " " );
         sbMsg.append( GetStringFromAttribute( vActiveMetas, "W_MetaDef", "Name" ) );
         sbMsg.append( " is in use by another tool.\nTo complete save, close the Zeidon Tool(s) and\n" +
                         "retry the save.  If all else fails, check OpenCM_Metas" );
         if ( MessagePrompt( vMOI, "CM00436", "Configuration Management", sbMsg.toString(), 0,
                             zBUTTONS_YESNO, zRESPONSE_YES, 0 ) == zRESPONSE_NO )
         {
            DropView( vActiveMetas );
            return( nRC );
         }

         do
         {
            nRC = fnCommitMetaOI( vSubtask, vMOI, nType, vActiveMetas, vZeidonCM, vLPLR );
            if ( nRC == 0 )
            {
               nRC = MessagePrompt( vMOI, "CM00437", "Configuration Management", sbMsg.toString(), 0,
                                    zBUTTONS_YESNO, zRESPONSE_YES, 0 );
               if ( nRC == zRESPONSE_NO || nRC == zCALL_ERROR )
               {
                  DropView( vActiveMetas );
                  return 0;
               }
            }
         } while ( nRC == 0 );

         DropView( vActiveMetas );
         TraceLineI( "CommitMetaOI nRC = ", nRC );
         return( nRC );
      }
   } // CommitMetaOI

   /////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: oTZ__PRFO_GetViewForProfile
   //
   // PURPOSE:   Get access to PRF View.  Activate if not loaded
   //            and get at application level if loaded.
   //
   /////////////////////////////////////////////////////////////////////////////
   private int oTZ__PRFO_GetViewToProfile( zVIEW  pvTZ__PRFO, String szToolID, View vSubtask, int nCurrentOrReload )
   {
      zVIEW  vT = new zVIEW();
      zVIEW  vTmpSubtask = new zVIEW();
      zVIEW  vLPLR = new zVIEW();
      String  szLPLR;
      String  szPRF_FileName = "";
      String  szAppViewName = "prfToolsForZeidon";
      int     nRScope;
      int     nRC;

      // See if PRF View is at the Application level.
      if ( SfCreateSubtask( vTmpSubtask, vSubtask, "Zeidon_Tools" ) == zCALL_ERROR )
      {
         return( zCALL_ERROR );
      }

      if ( isValid( vTmpSubtask ) == false )
      {
         return( zCALL_ERROR );
      }

      nRScope = GetViewByName( vT, szAppViewName, vTmpSubtask, zLEVEL_APPLICATION );
      if ( nRScope == zCALL_ERROR )
      {
         SfDropSubtask( vTmpSubtask, 0 );
         return( nRScope );
      }

      if ( nRScope == zLEVEL_APPLICATION && nCurrentOrReload == zFORCE_RELOAD )
      {
         DropObjectInstance( vT );
         nRScope = -1;
      }

      if ( nRScope != zLEVEL_APPLICATION )
      {
         // Load it from a file or create a new one if file is missing.
         szPRF_FileName = SysGetEnvVar( szPRF_FileName, "ZEIDON", 256 );
         if ( szPRF_FileName.isEmpty() == false )
         {
            szPRF_FileName = SysAppendcDirSep( szPRF_FileName );
            szPRF_FileName = zstrcat( szPRF_FileName, "TZTOOLS.PRF" );
         }
         else
         {
            return( -2 );
         }

         // dks 2007.06.03 ... added zACTIVATE_SYSTEM in attempt to get tools to live in harmony with Tomcat.
         if ( ActivateOI_FromFile( vT, "TZ__PRFO", vTmpSubtask, szPRF_FileName,
                                   zSINGLE | zACTIVATE_SYSTEM | zLEVEL_SYSTEM /*zLEVEL_APPLICATION*/ | zNOI_OKAY | zIGNORE_ERRORS ) < 0 )
         {
            nRC = ActivateEmptyObjectInstance( vT, "TZ__PRFO", vTmpSubtask,
                                               zSINGLE | zACTIVATE_SYSTEM | zLEVEL_SYSTEM /*zLEVEL_APPLICATION*/ );
            if ( nRC >= 0 )
            {
               CreateEntity( vT, "TZ", zPOS_AFTER );
               CreateEntity( vT, "EMD", zPOS_AFTER );
               CreateEntity( vT, "Model", zPOS_AFTER );
               CreateEntity( vT, "Submodel", zPOS_AFTER );
               CreateEntity( vT, "IDVIEW", zPOS_AFTER );
               CreateEntity( vT, "ATTVIEW", zPOS_AFTER );
               CreateEntity( vT, "RLLVIEW", zPOS_AFTER );
               CreateEntity( vT, "SA", zPOS_AFTER );
               CreateEntity( vT, "ZO", zPOS_AFTER );
               CreateEntity( vT, "WD", zPOS_AFTER );
               CreateEntity( vT, "TE", zPOS_AFTER );
               CreateEntity( vT, "DM", zPOS_AFTER );
               CreateEntity( vT, "OP", zPOS_AFTER );
               CreateEntity( vT, "MS", zPOS_AFTER );
               CreateEntity( vT, "VML", zPOS_AFTER );
               CreateEntity( vT, "OBR", zPOS_AFTER );
               CreateEntity( vT, "ED", zPOS_AFTER );
               SetNameForView( vT, szAppViewName, 0, zLEVEL_APPLICATION );
               CommitOI_ToFile( vT, szPRF_FileName, zASCII );
            }
            else
            {
               SfDropSubtask( vTmpSubtask, 0 );
               return( -1 );
            }
         }
         else
         {
            SetNameForView( vT, szAppViewName, 0, zLEVEL_APPLICATION );
            if ( CheckExistenceOfEntity( vT, "TZ" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "TZ", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "EMD" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "EMD", zPOS_AFTER );
               CreateEntity( vT, "Model", zPOS_AFTER );
               CreateEntity( vT, "Submodel", zPOS_AFTER );
               CreateEntity( vT, "IDVIEW", zPOS_AFTER );
               CreateEntity( vT, "ATTVIEW", zPOS_AFTER );
               CreateEntity( vT, "RLLVIEW", zPOS_AFTER );
               CreateEntity( vT, "ENTVIEW", zPOS_AFTER );
               CreateEntity( vT, "SA", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "Model" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "Model", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "Submodel" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "Submodel", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "IDVIEW" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "IDVIEW", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "ATTVIEW" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "ATTVIEW", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "ENTVIEW" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "ENTVIEW", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "RLLVIEW" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "RLLVIEW", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "SA" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "SA", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "ZO" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "ZO", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "WD" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "WD", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "TE" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "TE", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "DM" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "DM", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "OP" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "OP", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "MS" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "MS", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "VML" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "VML", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "OBR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "OBR", zPOS_AFTER );
            }

            if ( CheckExistenceOfEntity( vT, "ED" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "ED", zPOS_AFTER );
            }
         }

         GetViewByName( vLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
         if ( szToolID.isEmpty() == false && isValid( vLPLR ) )
         {
            szLPLR = GetStringFromAttribute( vLPLR, "LPLR", "Name" );
            nRC = SetCursorFirstEntityByString( vT, "LPLR_Group", "Name", szLPLR, "" );
            if ( nRC < zCURSOR_SET )
            {
               CreateEntity( vT, "LPLR_Group", zPOS_LAST );
            }

            SetMatchingAttributesByName( vT, "LPLR_Group", vLPLR, "LPLR", zSET_ALL );
            SetNameForView( vT, szAppViewName, 0, zLEVEL_APPLICATION );
            if ( CheckExistenceOfEntity( vT, "EMD_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "EMD_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "EMD_LPLR", vT, "EMD", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "SA_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "SA_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "SA_LPLR", vT, "SA", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "ZO_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "ZO_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "ZO_LPLR", vT, "ZO", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "WD_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "WD_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "WD_LPLR", vT, "WD", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "TE_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "TE_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "TE_LPLR", vT, "TE", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "DM_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "DM_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "DM_LPLR", vT, "DM", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "OP_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "OP_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "OP_LPLR", vT, "OP", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "MS_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "MS_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "MS_LPLR", vT, "MS", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "VML_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "VML_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "VML_LPLR", vT, "VML", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "OBR_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "OBR_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "OBR_LPLR", vT, "OBR", zSET_NULL );
            }

            if ( CheckExistenceOfEntity( vT, "ED_LPLR" ) < zCURSOR_SET )
            {
               CreateEntity( vT, "ED_LPLR", zPOS_AFTER );
   //          SetMatchingAttributesByName( vT, "ED_LPLR", vT, "ED", zSET_NULL );
            }
         }
      }

      nRC = CreateViewFromViewForTask( pvTZ__PRFO, vT, vT );
      return( nRC );
   }

   private int CommitDependentOI( View  vSubtask, View  vMOI, int nType )
   {
      int nRC, nEntityType;
      int lFlags;
      zVIEW vZeidonCM = new zVIEW();
      zVIEW MOI_ExecView = new zVIEW();
      zVIEW CM_View = new zVIEW();
      zVIEW vActiveMetas = new zVIEW();
      zVIEW vTaskLPLR = new zVIEW();
      zVIEW WKS_View = new zVIEW();
      zVIEW vProfileXFER = new zVIEW();
      String szMetaOI_Name; // protect from long name
      StringBuilder sbMetaOI_File = new StringBuilder(); // protect from long name
      int lMetaOI_ZKey;
      StringBuilder sbFileSpec = new StringBuilder();
   // String szTempFileName; // includes szFileSpec
      StringBuilder sbMsg = new StringBuilder();
      String szTimestamp = "";

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      if ( nEntityType != CM_REFER_TYPE )
      {
         MessageSend( vSubtask, "CM00438", "Configuration Management",
                      "Meta passed to CommitDependentOI is not a REFER Meta, see Trace",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         TraceLineS( "Zeidon Configuration Management",
          "Meta passed to CommitDependentOI is not a REFER Meta, Type passed is:" );
         TraceLineI( "Zeidon Configuration Management", nType );
         return -1;
      }

      if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) <= 0 )
      {
         return -1;
      }

      if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) <= 0 )
      {
         return -1;
      }

      if ( GetViewByName( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK ) <= 0 )
      {
         return -1;
      }

      if ( SetCursorFirstEntityByInteger( vTaskLPLR, "W_MetaType", "Type", nType, "" ) != zCURSOR_SET )
      {
         MessageSend( vSubtask, "CM00439", "Configuration Management",
                      "Meta type passed to CommitMetaOI is not in LPLR, see Trace",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         TraceLineS( "Zeidon Configuration Management",
          "Meta type passed to CommitMetaOI is not in LPLR, Type passed is:" );
         TraceLineI( "Zeidon Configuration Management", nType );
         return -1;
      }

      fnGetDirectorySpec( vMOI, sbFileSpec, nType );

      lMetaOI_ZKey = GetIntegerFromAttribute( vMOI, REFER_CMOD[ nType - 2000 ].szOD_ROOT, "ZKey" );
      szMetaOI_Name = GetStringFromAttribute( vMOI, REFER_CMOD[ nType - 2000 ].szOD_ROOT, REFER_CMOD[ nType - 2000 ].szOD_NAME );
      if ( nType == zREFER_VOR_META )
      {
         zltoxa( lMetaOI_ZKey, sbMetaOI_File );
      }
      else
      {
         zstrcpy( sbMetaOI_File, szMetaOI_Name );
         TruncateName8( sbMetaOI_File );
      }

      zstrcat( sbFileSpec, sbMetaOI_File );
      zstrcat( sbFileSpec, REFER_CMOD[ nType - 2000 ].szOD_EXT );
      if ( SetCursorFirstEntityByInteger( vTaskLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" ) != zCURSOR_SET )   // Not Found
      {
         zstrcpy( sbMsg, "Meta passed to CommitDependentOI - " );
         zstrcat( sbMsg, szMetaOI_Name );
         zstrcat( sbMsg, " Not found in LPLR!" );
         MessageSend( vSubtask, "CM00440", "Configuration Management", sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( CompareAttributeToInteger( vTaskLPLR, "W_MetaDef", "UpdateInd", 3 ) == 0 )
      {
         MessageSend( vSubtask, "CM00441", "Configuration Management",
                      "Deleted Meta Def passed to CommitDependentOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      lFlags = zINCREMENTAL;
      oTZ__PRFO_GetViewToProfile( vProfileXFER, "TZ", vSubtask, zCURRENT_OI );
      if ( isValid( vProfileXFER ) )
      {
         if ( CompareAttributeToString( vProfileXFER, "TZ",
                                        "CM_FileType", "B" ) != 0 )
   //                                   "CM_FileType", "A" ) != 0 )
         {
   //       lFlags |= zBINARY;
            lFlags |= zASCII;
         }
      }

   ///// All this just to get an updatable Meta  //////////////////////////
   // Get the view to the vTaskMetas subtask - remove if not needed
   // szMetaOI_Name = fnGetTaskOI_ListName( vSubtask );
   // k = GetViewByName( &vTaskMetas, szMetaOI_Name, vZeidonCM, zLEVEL_SUBTASK );
   //
      // Get the view to the ActiveMeta OI
      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );

      // I need the name of the TaskLPLR to get to the root LPLR in the ActiveMeta OI
      GetStringFromAttribute( sbMsg, vTaskLPLR, "LPLR", "Name" );
      SetCursorFirstEntityByString( vActiveMetas, "LPLR", "Name", sbMsg.toString(), "" );

      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type",
                                          nType - 2000, "" ) != zCURSOR_SET )
      {
         zstrcpy( sbMsg, "Meta Type not found in ActiveMeta List.\nType is " );
         sbMsg.append( zltoa( nType - 2000 ) );
         MessageSend( vSubtask, "CM00442", "Configuration Management",
                      sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef",
                                          "CPLR_ZKey", lMetaOI_ZKey, "" ) != zCURSOR_SET )   // Not in the Active List
      {
         zstrcpy( sbMsg, "Meta not found in ActiveMeta List.\nType is " );
         zstrcat( sbMsg, szMetaOI_Name );
         MessageSend( vSubtask, "CM00443", "Configuration Management",
                      sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      GetStringFromAttribute( sbMsg, vActiveMetas, "W_MetaDef", "CM_ViewName" );
      zstrcat( sbMsg, ".r" );
      if ( GetViewByName( CM_View, sbMsg.toString(), vZeidonCM, zLEVEL_SUBTASK ) < 1 )
      {
         // vActiveMetas has him but it's not still active
         zstrcpy( sbMsg, "Meta not found in ActiveMeta List.\nName is " );
         zstrcat( sbMsg, szMetaOI_Name );
         MessageSend( vSubtask, "CM00444", "Configuration Management",
                      sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      ////////////////////////////////////////////////////////////////////////
      // Decide what control flags should be use based on the current file version
      // and the INI settings
      nRC = fnAllowBinaryDataInSourceMetaFiles( vMOI );
      if ( nRC == 1 )
      {
         // keep it the same format as the file
         if ( MiCompareOI_ToRelease( vMOI, releaseCompatible ) <= 0 )
         {
            MiSetOI_ReleaseForView( vMOI, releaseCompatible );
            nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), lFlags );
         }
         else
         {
            MiSetOI_ReleaseForView( vMOI, releaseCurrent );
            nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), lFlags | zENCODE_BLOBS | zNO_NULL_STRING_TERM );
         }
      }
      else
      if ( nRC == 2 )
      {
         // Force it to compatibility format
         MiSetOI_ReleaseForView( vMOI, releaseCompatible );
         nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), lFlags );
      }
      else
      {
         // Use the current format
         MiSetOI_ReleaseForView( vMOI, releaseCurrent );
         nRC = CommitOI_ToFile( vMOI, sbFileSpec.toString(), lFlags | zENCODE_BLOBS | zNO_NULL_STRING_TERM );
      }

      if ( nRC != 0 )
      {
         zstrcpy( sbMsg, "CommitDependentOI failed trying to save " );
         zstrcat( sbMsg, sbFileSpec );
         MessageSend( vSubtask, "CM00445", "Configuration Management",
                      sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      SetAttributeFromString( vTaskLPLR, "W_MetaDef", "Name", szMetaOI_Name );
      szTimestamp = SysGetDateTime( szTimestamp );
      SetAttributeFromString( vTaskLPLR, "W_MetaDef", "LastSyncDate", szTimestamp );
      SetAttributeFromInteger( vTaskLPLR, "W_MetaDef", "UpdateInd", 1 );

      // zgSortEntityWithinParent( zASCENDING, vTaskLPLR, "W_MetaDef", "Name", "" );
      if ( nType == zREFER_LOD_META )
      {
         SetNameForView( vMOI, "TZZOLODO", vMOI, zLEVEL_TASK );
         oTZZOXODO_SaveXOD( vSubtask, vMOI );

         // Get access to the newly built XOD
         if ( GetViewByName( MOI_ExecView, "TZZOXODO", vSubtask, zLEVEL_TASK ) < 1 )
         {
            zstrcpy( sbMsg, "(CommitDependentOI) Unable to Access XOD.  XOD must be opened." );
            MessageSend( vSubtask, "CM00446", "Configuration Management",
                         sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return 0;
         }

         // Save the XOD to a file
         GetStringFromAttribute( sbFileSpec, vTaskLPLR, "LPLR", "ExecDir" );
         SysAppendcDirSep( sbFileSpec );
         zstrcpy( sbMetaOI_File, szMetaOI_Name );
         TruncateName8( sbMetaOI_File );
         zstrcat( sbFileSpec, sbMetaOI_File );
         zstrcat( sbFileSpec, ".XOD" );
         lFlags = zSINGLE;
         if ( CommitOI_ToFile( MOI_ExecView, sbFileSpec.toString(), lFlags ) != 0 )
         {
            zstrcpy( sbMsg, "Commit of XOD failed." );
            MessageSend( vSubtask, "CM00447", "Configuration Management",
                         sbMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         }
      }

      if ( CommitLPLR( vTaskLPLR ) == 0 )
      {
   // PETTIT Probably don't need to commit WKS because MaxZKey shouldn't have been updated.
   //    if ( CommitWorkstation( WKS_View ) == 0 )
            return( 1 );
      }

      return -1;
   }

   //./ ADD NAME=ActivateEmptyMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: ActivateEmptyMetaOI
   //
   //  PURPOSE:    Activates an empty Zeidon Meta OI by declaring a view
   //              and then activating an empty object instance
   //
   //  PARAMETERS: pvMOI - a pointer to a View to be returned
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    1 - Meta OI successfully activated
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int ActivateEmptyMetaOI( View vSubtask, zVIEW pvMOI, int nType, int lControl )
   {
      zVIEW vTaskLPLR = new zVIEW();
      zVIEW vZeidonCM = new zVIEW();
      zVIEW vActiveMetas = new zVIEW();
      zVIEW vTaskMetas = new zVIEW();
      zVIEW vMOI = new zVIEW();
      int nEntityType;
      StringBuilder sbWorkName = new StringBuilder();
      StringBuilder sbOD_RootName = new StringBuilder();
      int nRC;

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) <= 0 )
      {
         return -1;
      }

      if ( SetCursorFirstEntityByInteger( vTaskLPLR, "W_MetaType", "Type", nType, "" ) != zCURSOR_SET )
      {
         CreateEntity( vTaskLPLR, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vTaskLPLR, "W_MetaType", "Type", nType );
      }

      if ( nEntityType == CM_ACTIVE_TYPE )
      {
         zstrcpy( sbWorkName, SRC_CMOD[ nType ].szOD );
         zstrcpy( sbOD_RootName, SRC_CMOD[ nType ].szOD_ROOT );
         lControl = zLEVEL_APPLICATION;
   //    lControl = zLEVEL_TASK;
      }
      else
      {
         zstrcpy( sbWorkName, REFER_CMOD[ nType - 2000 ].szOD );
         zstrcpy( sbOD_RootName, REFER_CMOD[ nType - 2000 ].szOD_ROOT );
         lControl = zLEVEL_APPLICATION;
      }
   /*
      if ( nType == zSOURCE_DIALOG_META || nType == zREFER_DIALOG_META ||
        // nType == zSOURCE_DTE_META    || nType == zREFER_DTE_META ||
           nType == zSOURCE_UIS_META    || nType == zREFER_UIS_META )
   */
      if ( ActivateEmptyObjectInstance( vMOI, sbWorkName.toString(), vTaskLPLR, lControl ) != 0 )
      {
         return -1;
      }

      GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION );

      // Get the view to the vTaskMetas subtask.
      sbWorkName.append( fnGetTaskOI_ListName( vSubtask ) );
      GetViewByName( vTaskMetas, sbWorkName.toString(), vZeidonCM, zLEVEL_SUBTASK );

      // Get the view to the ActiveMeta OI.
      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );
      GetStringFromAttribute( sbWorkName, vTaskLPLR, "LPLR", "Name" );
      SetCursorFirstEntityByString( vActiveMetas, "LPLR", "Name", sbWorkName.toString(), "" );
      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type", nType, "" ) != zCURSOR_SET )
      {
         CreateEntity( vActiveMetas, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vActiveMetas, "W_MetaType", "Type", nType );
      }

   // PETTIT Include the following code when ready
   // k = CreateMetaEntity( vSubtask, *pvMOI, sbOD_RootName, zPOS_AFTER );
   // CreateEntity( vActiveMetas, "W_MetaDef", zPOS_AFTER );
   // SetAttributeFromAttribute( vActiveMetas, "W_MetaDef", "CPLR_ZKey",
   //                            *pvMOI, sbOD_RootName, "ZKey" );
   // GetStringFromAttribute( szWorkName, vActiveMetas,
   //                         "W_MetaDef", "CM_ViewName" );
   // if ( nEntityType == CM_ACTIVE_TYPE )
   // {
   //    SetAttributeFromInteger( vActiveMetas, "W_MetaDef", "TaskID",
   //                             SysGetTaskFromView( vSubtask ) );
   //    sztrcat( szWorkName, ".u" );
   // }
   // else
   //    sztrcat( szWorkName, ".r" );
   //
   // k = SetNameForView( *pvMOI, szWorkName, vZeidonCM, zLEVEL_SUBTASK );

      // Create a separate view for returning to the application
      CreateViewFromViewForTask( pvMOI, vMOI, vSubtask );
      ResetView( pvMOI );
      sbWorkName.setLength( 1 );
      if ( nEntityType == CM_ACTIVE_TYPE )
      {
         sbWorkName.append( 'u' );
      }
      else
      {
         sbWorkName.append( 'r' );
      }

      sbWorkName.append( pvMOI.getId() );
      SetNameForView( pvMOI, sbWorkName.toString(), vTaskMetas, zLEVEL_SUBTASK | zNAME_AUTODROP );
      return( 1 );
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private int fnCheckCM_ForActiveMetaByTask( View vSubtask, long MOI_InstanceID )
   {
      zVIEW  vWork = new zVIEW();
      zVIEW  vZeidonCM = new zVIEW();
      zVIEW  vActiveMetas = new zVIEW();
      int    nOI_Found;
      String szViewName;
      long   lInstanceID;
      int    lTaskID;
      int    nRC;

      GetViewByName( vWork, "TaskLPLR", vSubtask, zLEVEL_TASK );
      nOI_Found = 0;
   // lTaskID = SysGetTaskFromView( vSubtask );
      lTaskID = Integer.decode( SysGetTaskFromView( vSubtask ).getTaskId() );

      // Get the view to ZedionCM OI.
      GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION );

      // Get the view to the ActiveMeta OI.
      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );
      nRC = SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskID, "LPLR" );
      while ( (nRC == zCURSOR_SET) || (nRC == zCURSOR_SET_NEWPARENT) )
      {
         szViewName = GetStringFromAttribute( vActiveMetas, "W_MetaDef", "CM_ViewName" );
         zstrcat( szViewName, ".u" );
         if ( GetViewByName( vWork, szViewName, vZeidonCM, zLEVEL_SUBTASK ) < 0 )
         {
            nRC = SetCursorNextEntityByInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskID, "LPLR" );
         }
         else
         {
            lInstanceID = MiGetInstanceID_ForView( vWork );
            if ( lInstanceID != MOI_InstanceID )
            {
               nRC = SetCursorNextEntityByInteger( vActiveMetas, "W_MetaDef", "TaskID", lTaskID, "LPLR" );
            }
            else
            {
               nRC = zCURSOR_NULL;
               nOI_Found = 1;
            }
         }
      }

      return( nOI_Found );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: DropMetaOI
   //
   //  PURPOSE:    Drops the View to a Meta Object Instance
   //
   //  PARAMETERS: vMOI - the View to be dropped
   //
   //  RETURNS:    0 - View to Meta OI successfully dropped
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int DropMetaOI( View vSubtask, View vMOI )
   {
      zVIEW vZeidonCM = new zVIEW();
      zVIEW vActiveMetas = new zVIEW();
      zVIEW vTaskMetas = new zVIEW();
      zVIEW vTaskLPLR = new zVIEW();
      zVIEW RU_View = new zVIEW();
      long  lMOI_InstanceID;
      String  szLPLR_Name;
      StringBuilder sbViewName = new StringBuilder();

      // Get the Instance Id of the meta to be dropped.
      lMOI_InstanceID = MiGetInstanceID_ForView( vMOI );

      // Get the view to ZedionCM OI.
      GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION );

      // Get the view to the vTaskMetas subtask.
      sbViewName.append( fnGetTaskOI_ListName( vSubtask ) );
      GetViewByName( vTaskMetas, sbViewName.toString(), vZeidonCM, zLEVEL_SUBTASK );
      sbViewName.setLength( 1 );
      sbViewName.setCharAt( 0, 'r' );
      sbViewName.append( vMOI.getId() );
      if ( GetViewByName( RU_View, sbViewName.toString(), vTaskMetas, zLEVEL_SUBTASK ) > 0 )
      {
         DropView( vMOI );
         return 0;
      }

      sbViewName.setCharAt( 0, 'u' );
      if ( GetViewByName( RU_View, sbViewName.toString(), vTaskMetas, zLEVEL_SUBTASK ) < 0 )  // not an Update View
      {
         return -1;
      }

      // OK, so the View is to an Update (SOURCE) Meta. I need to check the
      // OpenCM_Metas for a match on TaskID. If there is a match and the
      // CM_ViewName.u InstanceID matches our InstanceID, drop the Object
      // Instance, remove the TaskID, then check for a CM_ViewName.r and if
      // there isn't one exclude the W_MetaDef.

      // Get the view to the ActiveMeta OI.
      GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK );

      // I need the view to the TaskLPLR to get the name of the root LPLR in the ActiveMeta OI.
      GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
      szLPLR_Name = GetStringFromAttribute( vTaskLPLR, "LPLR", "Name" );
      SetCursorFirstEntityByString( vActiveMetas, "LPLR", "Name", szLPLR_Name, "" );
      if ( fnCheckCM_ForActiveMetaByTask( vSubtask, lMOI_InstanceID ) == 1 )
      {
         // Found!
         SetAttributeFromInteger( vActiveMetas, "W_MetaDef", "TaskID", 0 );
         DropObjectInstance( vMOI );
         GetStringFromAttribute( sbViewName, vActiveMetas, "W_MetaDef", "CM_ViewName" );
         zstrcat( sbViewName, ".r" );
         if ( GetViewByName( RU_View, sbViewName.toString(), vZeidonCM, zLEVEL_SUBTASK ) < 0 )
         {
            ExcludeEntity( vActiveMetas, "W_MetaDef", zREPOS_NONE );
         }

         return 0;
      }

      // The Meta wasn't found in CM so Drop the OI - Could be a new?
      DropObjectInstance( vMOI );
      return 0;
   }

   //./ ADD NAME=DeleteMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: DeleteMetaOI
   //
   //  PURPOSE:    Deletes a Meta Object Instance (portable file)
   //
   //  PARAMETERS: vListView - a valid LPL list view
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    0 - Meta OI successfully deleted
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int DeleteMetaOI( View  vSubtask, View  vListView, int nType ) throws IOException
   {
      int nEntityType;
      int nWorkType;
      int nReferOI_Active = 0;
      int nReferViewsActive = 0;
      int  lTaskID;
      long lMOI_InstanceID;
      int  lMetaOI_ZKey;
      zVIEW vTaskLPLR = new zVIEW();
      zVIEW vMeta = new zVIEW();
      zVIEW vLPLR = new zVIEW();
      zVIEW vLPLR2 = new zVIEW();
      zVIEW vZeidonCM = new zVIEW();
      zVIEW vActiveMetas = new zVIEW();
      zVIEW vActiveList = new zVIEW();
      zVIEW vWork1 = new zVIEW();
      zVIEW vWork2 = new zVIEW();
      zVIEW CM_View = new zVIEW();
      zVIEW vVOR = new zVIEW();
      zVIEW vVOR_LPLR = new zVIEW();
      StringBuilder sbMetaOI_Name = new StringBuilder();
      String szEntityName;
      String szCM_ViewName;
      StringBuilder sbNamedView = new StringBuilder();
      StringBuilder sbTaskView = new StringBuilder();
      StringBuilder sbFileSpec = new StringBuilder();
      StringBuilder sbErrMsg = new StringBuilder();
      int nRC, nRC2;

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
      CreateViewFromViewForTask( vLPLR, vTaskLPLR, vSubtask );

      if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) <= 0 )
      {
         MessageSend( vSubtask, "CM00448", "Configuration Management",
                      "Unable to locate ZeidonCM in DeleteMetaOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK ) <= 0 )
      {
         MessageSend( vSubtask, "CM00449", "Configuration Management",
                      "Unable to locate OpenCM_Metas in DeleteMetaOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      CreateViewFromViewForTask( vActiveList, vActiveMetas, vSubtask );

      if ( nEntityType == CM_ACTIVE_TYPE )
         nWorkType = nType;
      else
         nWorkType = nType - 2000;

      lMetaOI_ZKey = GetIntegerFromAttribute( vListView, "W_MetaDef", "CPLR_ZKey" );
      sbMetaOI_Name.append( GetStringFromAttribute( vListView, "W_MetaDef", "Name" ) );
      nRC = SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "LPLR" );
      if ( nRC < zCURSOR_SET )
      {
         MessageSend( vSubtask, "CM00450", "Configuration Management", "Component to be Deleted not found in the LPLR.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( CompareAttributeToInteger( vLPLR, "W_MetaType", "Type", nWorkType ) != 0 )
      {
         MessageSend( vSubtask, "CM00451", "Configuration Management", "Component to be Deleted not active in the LPLR.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      if ( CompareAttributeToString( vLPLR, "W_MetaDef", "Name", sbMetaOI_Name.toString() ) != 0 )
      {
         MessageSend( vSubtask, "CM00452", "Configuration Management", "Component to be Deleted found in the LPLR under a different name", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      nRC = SetCursorFirstEntityByEntityCsr( vActiveList, "W_MetaDef", vLPLR, "W_MetaDef", "LPLR" );
      if ( nRC >= zCURSOR_SET )
      {
         lTaskID = GetIntegerFromAttribute( vActiveList, "W_MetaDef", "TaskID" );
         if ( lTaskID != 0 )
         {
            szCM_ViewName = GetStringFromAttribute( vActiveList, "W_MetaDef", "CM_ViewName" );
            if ( GetViewByName( CM_View, szCM_ViewName + ".u", vZeidonCM, zLEVEL_SUBTASK ) > 0 )
            {
               MessageSend( vSubtask, "CM00453", "Configuration Management",
                            "Component to be Deleted is currently opened for update.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }
         }
      }

      if ( nRC >= zCURSOR_SET )
      {
         szCM_ViewName = GetStringFromAttribute( vActiveList, "W_MetaDef", "CM_ViewName" );
         nReferOI_Active = 0;
         if ( GetViewByName( CM_View, szCM_ViewName + ".r", vZeidonCM, zLEVEL_SUBTASK ) > 0 )
         {
            // REFER OI of Meta exists - Check if there are Views to it
            nReferOI_Active = 1;
            nReferViewsActive = 0;
            lMOI_InstanceID = MiGetInstanceID_ForView( CM_View );
            nRC = DriverApplication.SfGetFirstNamedView( vWork1, sbNamedView, vZeidonCM, zLEVEL_SUBTASK );
            while ( nRC > 0 )
            {
               if ( sbNamedView.substring( 0, 5 ).compareTo( "__CM." ) == 0 )  // if ( zstrncmp( sbNamedView, "__CM.", 5 ) == 0 )
               {
                  nRC = DriverApplication.SfGetFirstNamedView( vWork2, sbTaskView, vWork1, zLEVEL_SUBTASK );
                  while ( nRC > 0 )
                  {
                     if ( sbTaskView.charAt( 0 ) == 'r' )  // if ( zstrncmp( sbTaskView, "r", 1 ) == 0 )
                     {
                        if ( MiGetInstanceID_ForView( vWork2 ) == lMOI_InstanceID )
                        {
                           nReferViewsActive = 1;
                           break;
                        }
                     }

                     nRC = DriverApplication.SfGetNextNamedView( vWork2, sbTaskView, vWork1, zLEVEL_SUBTASK );
                  }
               }

               if ( nReferViewsActive == 1 )
               {
                  sbErrMsg.setLength( 0 );
                  sbErrMsg.append( "The Meta you are trying to delete,\n" );
                  sbErrMsg.append( GetStringFromAttribute( vActiveMetas, "W_MetaDef", "Name" ) );
                  sbErrMsg.append( ", Type " );
                  sbErrMsg.append( GetVariableFromAttribute( 0, zTYPE_STRING, 125, vActiveMetas, "W_MetaType", "Type", "CM_Type", 0 ) );
                  sbErrMsg.append( ",\nis being referenced by a Zeidon Tool.\nTo delete this meta close the other Zeidon Tool.\n" );
                  MessageSend( vSubtask, "CM00454", "Configuration Management", sbErrMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  return 0;
               }

               nRC = DriverApplication.SfGetNextNamedView( vWork1, sbNamedView, vZeidonCM, zLEVEL_SUBTASK );
            }
         }
      }

      if ( nReferOI_Active != 0 && nReferViewsActive == 0 )
      {
         DropObjectInstance( CM_View );
      }
      else
      if ( nReferOI_Active != 0 )
      {
         DropView( CM_View );
      }

      // Determine fully contatenated file name for delete.
      fnGetDirectorySpec( vListView, sbFileSpec, nType );
      if ( nWorkType == zSOURCE_VOR_META )
      {
         zltoxa( lMetaOI_ZKey, sbMetaOI_Name );
      }

      zstrcat( sbFileSpec, sbMetaOI_Name.toString() );
      zstrcat( sbFileSpec, SRC_CMOD[ nWorkType ].szOD_EXT );

      nRC = ActivateMetaOI_ByZKey( vSubtask, vMeta, vListView, nWorkType,
                                   zSINGLE | zLEVEL_APPLICATION, lMetaOI_ZKey, zCURRENT_OI );
      if ( nRC < 0 )
      {
         MessageSend( vSubtask, "CM00455", "Configuration Management",
                      "Removing meta without deleting file.",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         DeleteEntity( vLPLR, "W_MetaDef", zREPOS_NONE );
         return -1;
      }

      if ( CheckExistenceOfEntity( vMeta, "Z_MetaDef" ) >= zCURSOR_SET )
      {
         if ( CompareAttributeToInteger( vLPLR, "W_MetaDef", "UpdateInd", 3 ) == 0 )
         {
            MessageSend( vSubtask, "CM00456", "Configuration Management",
                         "Component to be Deleted has already been deleted.",
                         zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            return -1;
         }

         SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nWorkType, "" );
         SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" );
         SetAttributeFromInteger( vLPLR, "W_MetaDef", "UpdateInd", 3 );
         SetAttributeFromString( vLPLR, "W_MetaDef", "Desc", "*** Deleted ***" );
         SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nWorkType + 2000, "" );
         nRC = SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lMetaOI_ZKey, "" );
         nRC2 = SetCursorFirstEntityByEntityCsr( vActiveList, "W_MetaDef", vLPLR, "W_MetaDef", "LPLR" );
         if ( nRC2 >= zCURSOR_SET )
         {
            ExcludeEntity( vActiveList, "W_MetaDef", zREPOS_NONE );
         }

         if ( nRC >= zCURSOR_SET )
         {
            ExcludeEntity( vLPLR, "W_MetaDef", zREPOS_NONE );
         }
      }
      else
      {
         if ( SysOpenFile( vLPLR, sbFileSpec.toString(), COREFILE_DELETE ) < 0 )
         {
            zstrcpy( sbErrMsg, "File " );
            zstrcat( sbErrMsg, sbFileSpec );
            zstrcat( sbErrMsg, "\nNot found - deleting entry from CM List" );
            MessageSend( vSubtask, "CM00457", "Configuration Management", sbErrMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         }

         nRC = SetCursorFirstEntityByEntityCsr( vActiveList, "W_MetaDef", vLPLR, "W_MetaDef", "LPLR" );
         if ( nRC >= zCURSOR_SET )
         {
            ExcludeEntity( vActiveList, "W_MetaDef", zREPOS_NONE );
         }

         DeleteEntity( vLPLR, "W_MetaDef", zREPOS_PREV );
         if ( nWorkType == zSOURCE_DIALOG_META || nWorkType == zSOURCE_LOD_META )
         {
            sbFileSpec.setLength( 0 );
            GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "ExecDir" );
            SysAppendcDirSep( sbFileSpec );
            zstrcat( sbFileSpec, sbMetaOI_Name );
            if ( nWorkType == zSOURCE_DIALOG_META )
            {
               zstrcat( sbFileSpec, ".XWD" );
            }
            else
            {
               zstrcat( sbFileSpec, ".XOD" );
               // For LOD, also delete all the related VORs.  This has to be
               // done by activating every VOR to see if it is for the deleted LOD.
               RetrieveViewForMetaList( vSubtask, vVOR_LPLR, zSOURCE_VOR_META );
               nRC = SetCursorFirstEntity( vVOR_LPLR, "W_MetaDef", "" );
               while ( nRC >= zCURSOR_SET )
               {
                  nRC = ActivateMetaOI( vSubtask, vVOR, vVOR_LPLR, zSOURCE_VOR_META, zSINGLE | zLEVEL_APPLICATION );
                  if ( nRC >= 0 )
                  {
                     if ( CompareAttributeToString( vVOR, "LOD", "Name", sbMetaOI_Name.toString() ) == 0 )
                     {
                        DropMetaOI( vSubtask, vVOR );
                        nRC = DeleteMetaOI( vSubtask, vVOR_LPLR, zSOURCE_VOR_META );
                        if ( nRC >= 0 )
                        {
                           SetCursorPrevEntity( vVOR_LPLR, "W_MetaDef", "" );
                        }

                        nRC = CheckExistenceOfEntity( vVOR_LPLR, "W_MetaDef" );
                     }
                     else
                     {
                        DropMetaOI( vSubtask, vVOR );
                        nRC = SetCursorNextEntity( vVOR_LPLR, "W_MetaDef", "" );
                     }
                  }
                  else
                  {
                     nRC = SetCursorNextEntity( vVOR_LPLR, "W_MetaDef", "" );
                  }
               }
            }

            if ( SysOpenFile( vVOR_LPLR, sbFileSpec.toString(), COREFILE_DELETE ) < 0 )
            {
               zstrcpy( sbErrMsg, "Corresponding Executable File " );
               zstrcat( sbErrMsg, sbFileSpec );
               zstrcat( sbErrMsg, " Not found." );
               MessageSend( vSubtask, "CM00458", "Configuration Management",
                            sbErrMsg.toString(), zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            }
         }

         // if this is a Report we have to delete file XRP and for SironReports
         // files REP and XSQ too.
         if ( nWorkType == zSOURCE_REPORT_META || nWorkType == zREFER_REPORT_META ||
              nWorkType == zSOURCE_XSLT_META || nWorkType == zREFER_XSLT_META )
         {
            String szReportFile;
            sbFileSpec.setLength( 0 );
            GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "ExecDir" );
            SysAppendcDirSep( sbFileSpec );
            zstrcat( sbFileSpec, sbMetaOI_Name );
            szReportFile = sbFileSpec.toString();    // Save the name without extension because we have to
                                                     // delete XSQ too for  SironReports
            if ( nWorkType == zSOURCE_REPORT_META || nWorkType == zREFER_REPORT_META )
            {
               zstrcat( sbFileSpec, ".XRP" );
            }
            else
            {
               zstrcat( sbFileSpec, ".XSL" );
            }

            if ( SysOpenFile( vLPLR, sbFileSpec.toString(), COREFILE_DELETE ) < 0 )
            {
               zstrcpy( sbErrMsg, "Corresponding Executable File " );
               zstrcat( sbErrMsg, sbFileSpec );
               zstrcat( sbErrMsg, " Not found." );
               MessageSend( vSubtask, "CM00458", "Configuration Management",
                            sbErrMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            }

            if ( nWorkType == zSOURCE_REPORT_META || nWorkType == zREFER_REPORT_META )
            {
                // Try to delete the XSQ file
                zstrcpy( sbFileSpec, szReportFile );
                zstrcat( sbFileSpec, ".XSQ" );
                SysOpenFile( vLPLR, sbFileSpec.toString(), COREFILE_DELETE ); // We don't test the Returncode of
                                                                              // SysOpenFile, because we get an error
                                                                              // if the report is a zeidon report.
                // Try to delete the REP file
                // We first get the directory where we'll find the REP file if it exists
                GetStringFromAttribute( sbFileSpec, vLPLR, "LPLR", "PgmSrcDir" );
                SysAppendcDirSep( sbFileSpec );
                zstrcat( sbFileSpec, sbMetaOI_Name );
                zstrcat( sbFileSpec, ".REP" );
                SysOpenFile( vLPLR, sbFileSpec.toString(), COREFILE_DELETE ); // We don't test the Returncode of
                                                                              // SysOpenFile, because we get an error
                                                                              // if the report is a zeidon report.
            }
         }
      }

      // If this is a Domain Group, remove all the subordinate Domain entries
      // from the LPLR list.
      if ( nWorkType == zSOURCE_DOMAINGRP_META )
      {
         CreateViewFromViewForTask( vLPLR2, vLPLR, vSubtask );
         SetCursorFirstEntityByInteger( vLPLR2, "W_MetaType", "Type", zSOURCE_DOMAIN_META, "" );
         nRC = SetCursorFirstEntity( vLPLR2, "W_MetaDef", "" );
         while ( nRC >= zCURSOR_SET )
         {
            if ( CompareAttributeToAttribute( vLPLR2, "W_MetaDef", "GroupName", vMeta, "DomainGroup", "Name" ) == 0 )
            {
               DeleteEntity( vLPLR2, "W_MetaDef", zREPOS_NONE );
            }

            nRC = SetCursorNextEntity( vLPLR2, "W_MetaDef", "" );
         }

         SetCursorFirstEntityByInteger( vLPLR2, "W_MetaType", "Type", zREFER_DOMAIN_META, "" );
         nRC = SetCursorFirstEntity( vLPLR2, "W_MetaDef", "" );
         while ( nRC >= zCURSOR_SET )
         {
            if ( CompareAttributeToAttribute( vLPLR2, "W_MetaDef", "GroupName", vMeta, "DomainGroup", "Name" ) == 0 )
            {
               DeleteEntity( vLPLR2, "W_MetaDef", zREPOS_NONE );
            }

            nRC = SetCursorNextEntity( vLPLR2, "W_MetaDef", "" );
         }

         DropView( vLPLR2 );
      }

      // If this is an Operation Group, remove all the subordinate Operation
      // entries from the LPLR list.
      if ( nWorkType == zSOURCE_GOPGRP_META )
      {
         CreateViewFromViewForTask( vLPLR2, vLPLR, vSubtask );
         SetCursorFirstEntityByInteger( vLPLR2, "W_MetaType", "Type", zSOURCE_GO_META, "" );
         nRC = SetCursorFirstEntity( vLPLR2, "W_MetaDef", "" );
         while ( nRC >= zCURSOR_SET )
         {
            if ( CompareAttributeToAttribute( vLPLR2, "W_MetaDef", "GroupName", vMeta, "GlobalOperationGroup", "Name" ) == 0 )
            {
               DeleteEntity( vLPLR2, "W_MetaDef", zREPOS_NONE );
            }

            nRC = SetCursorNextEntity( vLPLR2, "W_MetaDef", "" );
         }

         SetCursorFirstEntityByInteger( vLPLR2, "W_MetaType", "Type",
                                        zREFER_GO_META, "" );
         nRC = SetCursorFirstEntity( vLPLR2, "W_MetaDef", "" );
         while ( nRC >= zCURSOR_SET )
         {
            if ( CompareAttributeToAttribute( vLPLR2, "W_MetaDef", "GroupName", vMeta, "GlobalOperationGroup", "Name" ) == 0 )
            {
               DeleteEntity( vLPLR2, "W_MetaDef", zREPOS_NONE );
            }

            nRC = SetCursorNextEntity( vLPLR2, "W_MetaDef", "" );
         }

         DropView( vLPLR2 );
      }

      if ( CheckExistenceOfEntity( vMeta, "Z_MetaDef" ) >= zCURSOR_SET )
      {
         DeleteEntity( vMeta, SRC_CMOD[ nWorkType ].szOD_ROOT, zREPOS_NONE );
         CommitOI_ToFile( vMeta, sbFileSpec.toString(), zASCII | zINCREMENTAL );
      }

      DropObjectInstance( vMeta );

      if ( CommitLPLR( vLPLR ) == 0 )
      {
         DropView( vLPLR );
         return 0;
      }

      DropView( vLPLR );
      return -1;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    CreatePE_ExecVersion
   //
   // PURPOSE:  Create the executable version of the Presentation
   //           Environment instance (ZEIDON.XPE).
   //
   /////////////////////////////////////////////////////////////////////////////
   public View CreatePE_ExecVersion( View vSubtask, View vPresEnv )
   {
      zVIEW vPresEnvX = new zVIEW();
      zVIEW vTmpExe = new zVIEW();
      int  lRel = 0;
    //int  lLoop;
    //int  lFontCount;
      String  szWorkString;
      String  szGUID;
      int  lIdNbr;
      String pch;
      int nRC;

      TraceLineS( "CreatePE_ExecVersion", "================================" );

      szWorkString = GetStringFromAttribute( vSubtask, "LPLR", "ExecDir" );
      SysAppendcDirSep( szWorkString );
      zstrcat( szWorkString, "ZEIDON.XPE" );

      //BL, 2000.01.30 XPE does not exists, then no error message
      if ( SfActivateSysOI_FromFile( vTmpExe, "KZPEXVOO", vSubtask, szWorkString, zSINGLE | zNOI_OKAY ) >= 0 )
      {
         lRel = GetIntegerFromAttribute( vTmpExe, "PE", "Rel" );
      }

      ActivateEmptyObjectInstance( vPresEnvX, "KZPEXVOO", vSubtask, zSINGLE );
      CreateEntity( vPresEnvX, "PE", zPOS_AFTER );
      SetAttributeFromInteger( vPresEnvX, "PE", "Rel", lRel );

      ResetView( vPresEnv );

      nRC = SetCursorFirstEntity( vPresEnv, "EventDef", "" );
      while ( nRC >= zCURSOR_SET )
      {
         pch = GetStringFromAttribute( vPresEnv, "EventDef", "RegWEB" );
         if ( pch.charAt( 0 ) == 'Y' || pch.charAt( 0 ) == 'y' )
         {
            CreateEntity( vPresEnvX, "WndEvent", zPOS_AFTER );
            SetAttributeFromAttribute( vPresEnvX, "WndEvent", "Type", vPresEnv, "EventDef", "Type" );
         }

         nRC = SetCursorNextEntity( vPresEnv, "EventDef", "" );
      }

      nRC = SetCursorFirstEntity( vPresEnv, "ControlDef", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lIdNbr = GetIntegerFromAttribute( vPresEnv, "ControlDef", "Id" );
         szWorkString = GetStringFromAttribute( vPresEnv, "ControlDef", "RT_OperationName" );
         szGUID = GetStringFromAttribute( vPresEnv, "ControlDef", "GUID" );

         // Don't put any control in the XPE that doesn't have both an Id Number
         // and a Runtime Operation Name.
         if ( (lIdNbr == 0 && szGUID.length() == 0) || szWorkString.length() == 0 )
         {
            lIdNbr = GetIntegerFromAttribute( vPresEnv, "ControlDef", "Key" );
            TraceLineI( "Skipping ControlDef Id = ", lIdNbr );
         }
         else
         {
            CreateEntity( vPresEnvX, "Ctrl", zPOS_AFTER );

            // This is the result of an early design mistake that would be
            // very hard to overturn at this point in time.  The Key of the
            // ControlDef entity is not really a ZKey.  It is a Key (must
            // be unique) and should NOT be migrated.
            SetAttributeFromAttribute( vPresEnvX, "Ctrl", "Key", vPresEnv, "ControlDef", "Key" );

   //       SetAttributeFromAttribute( vPresEnvX, "Ctrl", "Tag", vPresEnv, "ControlDef", "Tag" );
            SetAttributeFromAttribute( vPresEnvX, "Ctrl", "DLL", vPresEnv, "ControlDef", "RT_DLL_Name" );
            SetAttributeFromString( vPresEnvX, "Ctrl", "COP", szWorkString );

   //       SetAttributeFromAttribute( vPresEnvX, "Ctrl", "TagPE", vPresEnv, "ControlDef", "Tag" );
   //       SetAttributeFromString( vPresEnvX, "Ctrl", "GUID", szGUID );
   //       SetAttributeFromAttribute( vPresEnvX, "Ctrl", "Script", vPresEnv, "ControlDef", "ActiveX_Script" );
   //       SetAttributeFromAttribute( vPresEnvX, "Ctrl", "ScriptDLL", vPresEnv, "ControlDef", "ActiveX_ScriptDLL" );
         }

         nRC = SetCursorFirstEntity( vPresEnv, "CtrlEventDef", "" );
         while ( nRC >= zCURSOR_SET )
         {
            pch = GetStringFromAttribute( vPresEnv, "CtrlEventDef", "RegWEB" );
            if ( pch.charAt( 0 ) == 'Y' || pch.charAt( 0 ) == 'y' )
            {
               CreateEntity( vPresEnvX, "CtrlEvent", zPOS_AFTER );
               SetAttributeFromAttribute( vPresEnvX, "CtrlEvent", "Type", vPresEnv, "CtrlEventDef", "Type" );
            }

            nRC = SetCursorNextEntity( vPresEnv, "CtrlEventDef", "" );
         }

         nRC = SetCursorNextEntity( vPresEnv, "ControlDef", "" );
      }
   /*
      lFontCount = 0;
      nRC = SetCursorFirstEntity( vPresEnv, "Font", "" );
      while ( nRC > zCURSOR_UNCHANGED )
      {
         lFontCount++;
         nRC = SetCursorNextEntity( vPresEnv, "Font", "" );
      }

      lLoop = 0;
      while ( lLoop < lFontCount )
      {
         SetCursorFirstEntityByInteger( vPresEnv, "Font", "Id", lLoop, "" );
         CreateEntity( vPresEnvX, "Font", zPOS_AFTER );
      //
      // SetAttributeFromAttribute( vPresEnvX, "Font", "Id",
      //                            vPresEnv, "Font", "Id" );
      //
   //    SetAttributeFromAttribute( vPresEnvX, "Font", "Struct",
         SetAttributeFromAttribute( vPresEnvX, "Font", "LFont",
                                    vPresEnv, "Font", "WindowsStructure" );
         SetAttributeFromAttribute( vPresEnvX, "Font", "RGB",
                                    vPresEnv, "Font", "RGB_Color" );
         SetAttributeFromAttribute( vPresEnvX, "Font", "Size",
                                    vPresEnv, "Font", "PointSize" );

         lLoop++;
      }
   */
      ResetView( vPresEnv );
      return( vPresEnvX );
   }

   //./ ADD NAME=CheckExistenceOfMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CheckExistenceOfMetaOI
   //
   //  PURPOSE:    Determines if a Zeidon Meta OI exists
   //
   //  PARAMETERS: szMetaName - the name of the OI to search for
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    1 - Meta OI exists
   //              0 - Meta OI not found
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   public int CheckExistenceOfMetaOI( View vSubtask, String szMetaName, int nType )
   {
      int nEntityType;
      zVIEW vTaskLPLR = new zVIEW();

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) > 0 )
      {
         if ( SetCursorFirstEntityByInteger( vTaskLPLR, "W_MetaType", "Type", nType, "" ) == zCURSOR_SET )
         {
            if ( SetCursorFirstEntityByString( vTaskLPLR, "W_MetaDef", "Name", szMetaName, "" ) == zCURSOR_SET &&
                 CompareAttributeToInteger( vTaskLPLR, "W_MetaDef", "UpdateInd", 3 ) != 0 )
            {
               return( 1 );
            }
            else
            {
               return 0;
            }
         }
         else
         {
            return 0;
         }
      }

      return -1;

   } // CheckExistenceOfMetaOI

   //./ ADD NAME=CheckExistenceOfMetaOI_ByZKey
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CheckExistenceOfMetaOI_ByZKey
   //
   //  PURPOSE:    Searches the LPLR for a meta matching the ZKey and LPLR
   //              type passed.
   //
   //  PARAMETERS: ZKey - ZKey to the Meta be searched for
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //
   //  RETURNS:    1 - Matching meta found
   //              0 - No matching meta found
   //             -1 - Error encountered, type invalid or LPLR not initialized
   //
   /////////////////////////////////////////////////////////////////////////////
   public int CheckExistenceOfMetaOI_ByZKey( View vSubtask, int ulZKey, int nType )
   {
      zVIEW vTaskLPLR = new zVIEW();
      int nEntityType;

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }

      if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) > 0 )
      {
         if ( SetCursorFirstEntityByInteger( vTaskLPLR, "W_MetaType", "Type", nType, "" ) == zCURSOR_SET )
         {
            if ( SetCursorFirstEntityByInteger( vTaskLPLR, "W_MetaDef", "CPLR_ZKey", ulZKey, "" ) == zCURSOR_SET &&
                 CompareAttributeToInteger( vTaskLPLR, "W_MetaDef", "UpdateInd", 3 ) != 0 )
            {
               return( 1 );
            }
            else
            {
               return 0;
            }
         }
      }

      return -1;
   }

   //./ ADD NAME=CreateFileNameFromZKey
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: CreateFileNameFromZKey
   //
   //  PURPOSE:    Creates an eight character file name based upon the
   //              WKS_Id and MaxZKey values extrapilated from the ZKey
   //              attribute of the entity passed.
   //
   //  PARAMETERS: sbFileName - the address of the file name to be created
   //              lpView - the view for which the entity resides
   //              szEntityName - the Zeidon meta entity name
   //
   //  RETURNS:    0 - FileName created successfully
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   private int CreateFileNameFromZKey( StringBuilder sbFileName, View lpView, StringBuilder sbEntity )
   {
      int  ulZKey = GetIntegerFromAttribute( lpView, sbEntity.toString(), "ZKey" );
      zltoxa( ulZKey, sbFileName );
      return 0;
   }

   //./ ADD NAME=zltoxa
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: zltoxa
   //
   //  PURPOSE:    Converts a long to a hex string with the first character
   //              set to 'Z' if it is a '0'.
   //
   //  PARAMETERS: lIn         - the long integer to be coverted
   //              szHexString - the string returned (must be 8 chars + null)
   //
   //  RETURNS:    0 - allways
   //
   /////////////////////////////////////////////////////////////////////////////
   private String zltoxa( int  lIn )
   {
      return Integer.toHexString( lIn );
   }

   private int zltoxa( int lIn, StringBuilder sbHexString )
   {
      sbHexString.setLength( 0 );
      sbHexString.append( zltoxa( lIn ) );
      return sbHexString.length();
   }
   //./ ADD NAME=zxatol
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: zxatol
   //
   //  PURPOSE:    Converts a long to a hex string with the first character
   //              set to 'Z' if it is a '0'.
   //
   //  PARAMETERS: szHexString - the string input (must be 8 chars + null)
   //              plIn        - pointer to the long integer to be returned
   //
   //  RETURNS:    0 - allways
   //
   /////////////////////////////////////////////////////////////////////////////
   private int zxatol( String szHexString, MutableInt miReturn )
   {
      if ( szHexString.charAt( 0 ) == 'Z' )
      {
         szHexString = szHexString.substring( 1 );
      }

      miReturn.setValue( Integer.parseInt( szHexString, 16 ) );
      return 0;
   }

   //./ ADD NAME=AssignZKeyToMetaOI
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: AssignZKeyToMetaOI
   //
   //  PURPOSE:    Checks an Object Instance's Entities for valid ZKey
   //              attributes and if they are not set or are invalid or
   //              reassignment is requested, a new ZKey is set.
   //
   //  PARAMETERS: OI_View - the view of the Object Instance to be checked
   //              nType - a valid LPL meta type, i.e. zSOURCE_DIALOG_META
   //              Assign - indicator telling function to reassign all
   //                       ZKeys (1) or only invalid ZKeys (0)
   //
   //  RETURNS:    0 - ZKeys successfully converted
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   private int AssignZKeyToMetaOI( View vSubtask, View OI_View, int nType, int Assign )
   {
      StringBuilder sbErrMsg = new StringBuilder();
      StringBuilder sbEntityName = new StringBuilder();
      int  ulZKey;
      MutableInt miLevel = new MutableInt();
      int  nEntityType;
      int  nRC;

      nEntityType = fnVerifyType( nType );
      if ( nEntityType < 0 )
      {
         return -1;
      }
      if ( nEntityType == CM_ACTIVE_TYPE )
      {
         zstrcpy( sbEntityName, SRC_CMOD[ nType ].szOD_ROOT );
         if ( DefineHierarchicalCursor( OI_View, SRC_CMOD[ nType ].szOD_ROOT ) != 0 )
         {
            return -1;
         }
      }
      else
      if ( nEntityType == CM_REFER_TYPE )
      {
         zstrcpy( sbEntityName, REFER_CMOD[ nType - 10000 ].szOD_ROOT );
         if ( DefineHierarchicalCursor( OI_View, REFER_CMOD[ nType - 2000 ].szOD_ROOT ) != 0 )
         {
            return -1;
         }
      }

      nRC = zCURSOR_SET;
      do
      {
         ulZKey = GetIntegerFromAttribute( OI_View, sbEntityName.toString(), "ZKey" );
         if ( (ulZKey > 999999999) || (ulZKey <= 0) || (Assign == 1) )
         {
            if ( SetZKeyToNextZKey( vSubtask, OI_View, sbEntityName.toString() ) < 0 )
            {
               TraceLineS( "Configuration Management", "Assign of ZKey Failed!!!" );
               zstrcpy( sbErrMsg, "Entity is: " );
               zstrcat( sbErrMsg, sbEntityName );
               TraceLineS( "Configuration Management", sbErrMsg.toString() );
               DisplayEntityInstance( OI_View, sbEntityName.toString() );
               zstrcpy( sbErrMsg, "Root Entity is: " );
               zstrcat( sbErrMsg, SRC_CMOD[ nType ].szOD_ROOT );
               TraceLineS( "Configuration Management", sbErrMsg.toString() );
               return -1;
            }
         }

         if ( nRC == zCURSOR_SET_RECURSIVECHILD )
         {
            SetViewToSubobject( OI_View, sbEntityName.toString() );
         }

         nRC = SetCursorNextEntityHierarchical( miLevel, sbEntityName, OI_View );

      } while ( (nRC != zCURSOR_UNCHANGED) && (nRC != zCALL_ERROR) );

      DropHierarchicalCursor( OI_View );
      return 0;
   }

   //./ ADD NAME=SetZKeyToNextZKey
   // Source Module=tzcmoprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  CM OPERATION: SetZKeyToNextZKey
   //
   //  PURPOSE:    Set the "ZKey" attribute of the input MetaEntity to the
   //              next vailable ZKey for the workstation.
   //
   //  PARAMETERS: OI_View    - the view of the Object Instance
   //              EntityName - of the currently positioned MetaEntity
   //
   //  RETURNS:    0 - ZKeys successfully set
   //             -1 - Error encountered
   //
   /////////////////////////////////////////////////////////////////////////////
   //./ END + 3
   public int SetZKeyToNextZKey( View vSubtask, View lpView, String szEntityName )
   {
      zVIEW vZeidonCM = new zVIEW();
      zVIEW WKS_View = new zVIEW();
      zVIEW vTZCMREPO = new zVIEW();
      int   lWKS_Id;
      int   lWkZKey;
      int   ulMaxZKey;
      MutableInt miWKS_Id = new MutableInt();
      MutableInt miWkZKey = new MutableInt();

      if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) <= 0 )
      {
         return -1;
      }

      GetViewByName( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK );
      if ( isValid( WKS_View ) == false )  // View isn't there
      {
         MessageSend( lpView, "CM00465", "Configuration Management",
                      "The RepositoryClient View ID was not found",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      ulMaxZKey = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "MaxZKey" );
      if ( ulMaxZKey == 0 )
      {
         lWKS_Id = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "WKS_Id" );
         ulMaxZKey = lWKS_Id * 1000000;
      }

   // PETTIT  Add this code when we have a CPL to work with!!!!!!
      fnBreakDownZKey( ulMaxZKey, miWKS_Id, miWkZKey );
   // lWKS_Id = miWKS_Id.intValue();
      lWkZKey = miWkZKey.intValue();
      if ( lWkZKey > 999999 )
      {
         GetViewByName( vTZCMREPO, "TZCMREPO", vSubtask, zLEVEL_TASK );
         if ( isValid( vTZCMREPO ) == false )
         {
            if ( ActivateObjectInstance( vTZCMREPO, "TZCMREPO", lpView, 0, zSINGLE ) != 0 )
            {
               MessageSend( lpView, "CM00466", "Configuration Management",
                            "MaxZKey limit exceeded, unable to access Installation object to assign new WKS_Id",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }
         }

         lWKS_Id = GetIntegerFromAttribute( vTZCMREPO, "Installation", "NextWKS_Id" );
         SetAttributeFromInteger( WKS_View, "RepositoryClient", "WKS_Id", lWKS_Id );
         ulMaxZKey = lWKS_Id * 1000000;
         lWKS_Id++;
         SetAttributeFromInteger( vTZCMREPO, "Installation", "NextWKS_Id", lWKS_Id );
         CommitObjectInstance( vTZCMREPO );
         CommitWorkstation( WKS_View );
      }

      ulMaxZKey++;
      if ( SetAttributeFromInteger( lpView, szEntityName, "ZKey", ulMaxZKey ) != 0 )
      {
         MessageSend( lpView, "CM00467", "Configuration Management",
                      "Unable to set MaxZKey in operation AssignZKeyToMetaOI",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      SetAttributeFromInteger( WKS_View, "RepositoryClient", "MaxZKey", ulMaxZKey );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private int fnSetCPLR_ZKey( View vSubtask, View vTaskLPLR, String szEntityName )
   {
      zVIEW  vZeidonCM = new zVIEW();
      zVIEW  WKS_View = new zVIEW();
      zVIEW  vTZCMREPO = new zVIEW();
      MutableInt miWKS_Id = new MutableInt();
      MutableInt miWkZKey = new MutableInt();
      int  lWKS_Id;
      int  lWkZKey;
      int  ulMaxZKey;

      if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) <= 0 )
      {
         return -1;
      }

      if ( GetViewByName( WKS_View, "TZCMWKSO", vZeidonCM, zLEVEL_SUBTASK ) < 0 )
      {
         MessageSend( vTaskLPLR, "CM00468", "Configuration Management",
                      "The RepositoryClient View ID was not found",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      ulMaxZKey = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "MaxZKey" );
      if ( ulMaxZKey == 0 )
      {
         lWKS_Id = GetIntegerFromAttribute( WKS_View, "RepositoryClient", "WKS_Id" );
         ulMaxZKey = lWKS_Id * 1000000;
      }

   // PETTIT  Add this code when we have a CPL to work with!!!!!!
      fnBreakDownZKey( ulMaxZKey, miWKS_Id, miWkZKey );
   // lWKS_Id = miWKS_Id.intValue();
      lWkZKey = miWkZKey.intValue();
      if ( lWkZKey > 999999 )
      {
         if ( GetViewByName( vTZCMREPO, "TZCMREPO", vSubtask, zLEVEL_TASK ) < 0 )
         {
            if ( ActivateObjectInstance( vTZCMREPO, "TZCMREPO", vTaskLPLR, 0, zSINGLE ) != 0 )
            {
               MessageSend( vTaskLPLR, "CM00469", "Configuration Management",
                            "MaxZKey limit exceeded, unable to access Installation object to assign new WKS_Id",
                            zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               return -1;
            }
         }

         lWKS_Id = GetIntegerFromAttribute( vTZCMREPO, "Installation", "NextWKS_Id" );
         SetAttributeFromInteger( WKS_View, "RepositoryClient", "WKS_Id", lWKS_Id );
         ulMaxZKey = lWKS_Id * 1000000;
         lWKS_Id++;
         SetAttributeFromInteger( vTZCMREPO, "Installation", "NextWKS_Id", lWKS_Id );
         CommitObjectInstance( vTZCMREPO );
         CommitWorkstation( WKS_View );
      }

      ulMaxZKey++;
      if ( SetAttributeFromInteger( vTaskLPLR, szEntityName, "CPLR_ZKey", ulMaxZKey ) != 0 )
      {
         MessageSend( vTaskLPLR, "CM00470", "Configuration Management",
                      "Unable to set MaxZKey in operation SetCPLR_ZKey",
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }

      SetAttributeFromInteger( WKS_View, "RepositoryClient", "MaxZKey", ulMaxZKey );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   /////////////////////////////////////////////////////////////////////////////
   private void fnGetAndSetZKey( View vSubtask, View vLPLR, String szFileName )
   {
      zVIEW  vOI_View = new zVIEW();
      zVIEW  vTaskLPLR = new zVIEW();
      String szTaskLPLR_Name;
      String szLPLR_Name;
      int    lType;
      int    lFlags;
      int    ulZKey;
      StringBuilder sbERR_Msg = new StringBuilder();
      int    nRC;

      lType = GetIntegerFromAttribute( vLPLR, "W_MetaType", "Type" );

      if ( lType == zREFER_POD_META )
      {
         return;
      }

      if ( lType == zREFER_VOR_META ||
           lType == zREFER_DOMAINGRP_META ||
           lType == zREFER_GOPGRP_META )
      {
         lFlags = zSINGLE | zIGNORE_ERRORS;
      }
      else
      {
         lFlags = zSINGLE | zACTIVATE_ROOTONLY | zIGNORE_ERRORS;
      }

      if ( ActivateOI_FromFile( vOI_View, SRC_CMOD[ lType - 2000 ].szOD, vLPLR, szFileName, lFlags ) != 0 )
      {
         DeleteEntity( vLPLR, "W_MetaDef", zREPOS_PREV );
         zstrcpy( sbERR_Msg, "Unable to activate file: " );
         zstrcat( sbERR_Msg, szFileName );
         TraceLineS( "Zeidon Configuration Management", sbERR_Msg.toString() );
         return;
      }

      ulZKey = GetIntegerFromAttribute( vOI_View, SRC_CMOD[ lType -2000 ].szOD_ROOT, "ZKey" );
      if ( ulZKey == 0 )
      {
         zstrcpy( sbERR_Msg, "Root entity, " );
         zstrcat( sbERR_Msg, SRC_CMOD[ lType -2000 ].szOD_ROOT );
         zstrcat( sbERR_Msg, ", ZKey is 0 in File: " );
         zstrcat( sbERR_Msg, szFileName );
         TraceLineS( "Zeidon Configuration Management", sbERR_Msg.toString() );
         return;
      }

      SetAttributeFromInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", ulZKey );

      if ( lType == zREFER_VOR_META )
      {
         SetAttributeFromAttribute( vLPLR, "W_MetaDef", "Name", vOI_View,
                                    SRC_CMOD[ lType - 2000 ].szOD_ROOT,
                                    SRC_CMOD[ lType - 2000 ].szOD_NAME );
         GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
         szTaskLPLR_Name = GetStringFromAttribute( vTaskLPLR, "LPLR", "Name" );
         szLPLR_Name = GetStringFromAttribute( vLPLR, "LPLR", "Name" );
         if ( zstrcmp( szLPLR_Name, szTaskLPLR_Name ) != 0 )
         {
            SetNameForView( vTaskLPLR, "TZCMhold", vSubtask, zLEVEL_TASK );
            SetNameForView( vLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
         }

         if ( GetViewByName( vTaskLPLR, "TZCMhold", vSubtask, zLEVEL_TASK ) > 0 )
         {
            DropNameForView( vTaskLPLR, "TZCMhold", vSubtask, zLEVEL_TASK );
            SetNameForView( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK );
         }
      }

      // For group types we need to copy the sub-metas to their respective W_MetaDef.
      if ( lType == zREFER_DOMAINGRP_META || lType == zREFER_GOPGRP_META )
      {
         int   nTargetType;
         zVIEW vTarget = new zVIEW();
         int   lTemp;

         CreateViewFromViewForTask( vTarget, vLPLR, null );

         /*
            SetNameForView( vTarget, "TempTargetView", vSubtask, zLEVEL_TASK );
            SetNameForView( vOI_View, "OI_View", vSubtask, zLEVEL_TASK );
         */

         // DGC - DOMGRP
         // Set the W_MetaType cursor to reflect the current target type.
         if ( lType == zREFER_DOMAINGRP_META )
         {
            nTargetType = zREFER_DOMAIN_META;
         }
         else
         {
            nTargetType = zREFER_GO_META;
         }

         SetCursorFirstEntityByInteger( vTarget, "W_MetaType", "Type", nTargetType, "" );

         // For each of the sub-metas (e.g. "Domains") in the meta we just activated
         // (e.g. "DomainGroup") copy the sub-meta to the W_MetaDef entity in the LPLR.
         for ( nRC = SetCursorFirstEntity( vOI_View, REFER_CMOD[ nTargetType - 2000 ].szOD_ROOT, "" );
               nRC == zCURSOR_SET;
               nRC = SetCursorNextEntity( vOI_View, REFER_CMOD[ nTargetType - 2000 ].szOD_ROOT, "" ) )
         {
            lTemp = GetIntegerFromAttribute( vOI_View, REFER_CMOD[ nTargetType - 2000 ].szOD_ROOT, "ZKey" );

            // Copy Name, ZKey, and GroupName from vOI_View to vTarget (the LPLR view).
            CreateEntity( vTarget, "W_MetaDef", zREPOS_AFTER );
            SetAttributeFromAttribute( vTarget, "W_MetaDef", "Name", vOI_View,
                                       REFER_CMOD[ nTargetType - 2000 ].szOD_ROOT,
                                       REFER_CMOD[ nTargetType - 2000 ].szOD_NAME );
            SetAttributeFromAttribute( vTarget, "W_MetaDef", "CPLR_ZKey", vOI_View,
                                       REFER_CMOD[ nTargetType - 2000 ].szOD_ROOT, "ZKey" );
            SetAttributeFromAttribute( vTarget, "W_MetaDef", "GroupName", vOI_View,
                                       REFER_CMOD[ lType - 2000 ].szOD_ROOT,
                                       REFER_CMOD[ lType - 2000 ].szOD_NAME );

            // If the following values need to be changed, they also need to be
            // changed where fnGetAndSetZKey is called.
            SetAttributeFromInteger( vTarget, "W_MetaDef", "Status", 1 );
            SetAttributeFromInteger( vTarget, "W_MetaDef", "UpdateInd", 2 );
         }

         DropView( vTarget );

      } // if ( lType == zREFER_DOMAINGRP_META || lType == zREFER_GOPGRP_META )...

      SetAttributeFromAttribute( vLPLR, "W_MetaDef", "Desc", vOI_View, SRC_CMOD[ lType - 2000 ].szOD_ROOT, "Desc" );
      DropObjectInstance( vOI_View );

   } // fnGetAndSetZKey

   private int fnBuildMetas( View vSubtask, View vLPLR, String path, int nEntityType ) throws IOException
   {
      zVIEW vOI_View = new zVIEW();
      StringBuilder sbFullName = new StringBuilder();
      StringBuilder sbMetaName = new StringBuilder();
      String szTimestamp;
      MutableInt lZKey = new MutableInt();
      int   lType;
      int   lFlags;
      int   hFile;
      int   nRC;

      // Searches a directory for a file whose name matches the specified
      // file name on the destination site identified by this object.  It
      // examines subdirectory names as well as file names.

      String fileName;
      File folder = new File( path );
      File[] fileList = folder.listFiles();
      File file;

      for ( int k = 0; k < fileList.length; k++ )
      {
         if ( fileList[ k ].isFile() )
         {
            file = fileList[ k ];
            fileName = file.getName();
            if ( fileName.toLowerCase().endsWith( ".txt" ) )
            {
               System.out.println( "fnBuildMetas: " + fileName );
               zstrcpy( sbMetaName, fileName );
               zstrcpy( sbFullName, path );
               sbFullName.setLength( sbFullName.length() - 5 );
               zstrcat( sbFullName, sbMetaName );
               szTimestamp = SysGetFileDateTime( file );
               sbMetaName.setLength( sbMetaName.length() - 4 );
            // AnsiUpper( szMetaName );
               lType = GetIntegerFromAttribute( vLPLR, "W_MetaType", "Type" );
               if ( lType == zREFER_VOR_META )
               {
                  zxatol( sbMetaName.toString(), lZKey );
                  nRC = SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef", "CPLR_ZKey", lZKey.intValue(), "" );
               }
               else
               {
                  nRC = SetCursorFirstEntityByString( vLPLR, "W_MetaDef", "Name", sbMetaName.toString(), "" );
               }

               if ( nRC != zCURSOR_SET ) // New Meta
               {
                  // Get the Name from the root entity of the object, because it is
                  // both upper and lower case there.
                  // Note that the name attribute is actually "Tag" for a Dialog
                  // and that we will keep the computed name for a named view.
                  lFlags = zSINGLE | zACTIVATE_ROOTONLY | zIGNORE_ERRORS;
                  ActivateOI_FromFile( vOI_View, SRC_CMOD[ lType - 2000 ].szOD, vLPLR, sbFullName.toString(), lFlags );
                  if ( lType == zREFER_DIALOG_META || lType == zREFER_REPORT_META || lType == zSOURCE_XSLT_META )
                  {
                     GetStringFromAttribute( sbMetaName, vOI_View, SRC_CMOD[ lType - 2000 ].szOD_ROOT, "Tag" );
                  }
                  else
                  {
                     if ( lType != zREFER_VOR_META )
                     {
                        GetStringFromAttribute( sbMetaName, vOI_View, SRC_CMOD[ lType - 2000 ].szOD_ROOT, "Name" );
                     }
                  }

                  // We will do the compare again here, in case the set cursor above
                  // didn't find a match because of upper/lower case differences.
                  nRC = SetCursorFirstEntityByString( vLPLR, "W_MetaDef", "Name", sbMetaName.toString(), "" );
                  if ( nRC < zCURSOR_SET )
                  {
                     CreateEntity( vLPLR, "W_MetaDef", zPOS_AFTER );
                     SetAttributeFromString( vLPLR, "W_MetaDef", "LastUpdateDate", szTimestamp );
                     SetAttributeFromString( vLPLR, "W_MetaDef", "LastSyncDate", szTimestamp );
                     if ( lType != zREFER_VOR_META )
                     {
                        SetAttributeFromString( vLPLR, "W_MetaDef", "Name", sbMetaName.toString() );
                     }

                     fnGetAndSetZKey( vSubtask, vLPLR, sbFullName.toString() );

                     if ( lType == zREFER_LOD_META )
                     {
                        SetAttributeFromAttribute( vLPLR, "W_MetaDef", "DoNotMergeFlag", vOI_View, "LOD", "DoNotMergeFlag" );
                     }
                  }

                  DropObjectInstance( vOI_View );
               }

               // If the following lines are changed, then they need to be changed in
               // fnGetAndSetZKey as well.
               SetAttributeFromInteger( vLPLR, "W_MetaDef", "Status", 1 );
               SetAttributeFromInteger( vLPLR, "W_MetaDef", "UpdateInd", 2 );
            }
         } // end if it's a file
      } // end for

      zgSortEntityWithinParent( zASCENDING, vLPLR, "W_MetaDef", "Name", "" );

      return 0;
   }

   private int BuildLPLR_MetaTypes( View vSubtask, View vLPLR, int BuildMetas ) throws IOException
   {
      zVIEW     IncludeView  = new zVIEW();
      StringBuilder sbDirectorySpec = new StringBuilder();
      int       nType;
      int       ulZKey;
      int       nRC;

      for ( nType = 2000; nType <= zREFER_MAX_META; nType++ )
      {
         // If W_MetaType exists for the current Type, then delete it to make
         // sure that we are starting from scratch.
         if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nType, "" ) == zCURSOR_SET )
         {
            DeleteEntity( vLPLR, "W_MetaType", zPOS_AFTER );
         }

         CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", nType );

         // DGC - DOMGRP
         // Skip domains and global ops because we will pick them up as part
         // of domain groups and gop groups.  See fnGetAndSetZKey for more.
         if ( nType == zREFER_DOMAIN_META || nType == zREFER_GO_META )
         {
            continue;
         }

         if ( BuildMetas == 1 )
         {
            String  szTempSpec;

            szTempSpec = GetStringFromAttribute( vLPLR, "LPLR", "MetaSrcDir" );
            SysConvertEnvironmentString( sbDirectorySpec, szTempSpec );
            SysAppendcDirSep( sbDirectorySpec );
            zstrcat( sbDirectorySpec, "*" );
            zstrcat( sbDirectorySpec, SRC_CMOD[ nType - 2000 ].szOD_EXT );
            fnBuildMetas( vSubtask, vLPLR, sbDirectorySpec.toString(), CM_REFER_TYPE );
         }
      }

      CreateViewFromViewForTask( IncludeView, vLPLR, null );  // the include view needs to be in the same task
                                                                    // as vLPLR, not in the same task as vSubtask
      for ( nType = 0; nType <= zSOURCE_MAX_META; nType++ )
      {
         // If W_MetaType exists for the current Type, then delete it to make
         // sure that we are starting from scratch.
         if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaType", "Type", nType, "" ) == zCURSOR_SET )
         {
            DeleteEntity( vLPLR, "W_MetaType", zPOS_AFTER );
         }

         CreateEntity( vLPLR, "W_MetaType", zPOS_AFTER );
         SetAttributeFromInteger( vLPLR, "W_MetaType", "Type", nType );

         if ( SetCursorFirstEntityByInteger( IncludeView, "W_MetaType", "Type",
                                             nType + 2000, "" ) == zCURSOR_SET )
         {
            nRC = SetCursorFirstEntity( IncludeView, "W_MetaDef", "" );
            while ( nRC == zCURSOR_SET )
            {
               ulZKey = GetIntegerFromAttribute( IncludeView, "W_MetaDef", "CPLR_ZKey" );
               if ( SetCursorFirstEntityByInteger( vLPLR, "W_MetaDef",
                                                   "CPLR_ZKey", (int) ulZKey,
                                                   "" ) != zCURSOR_SET )
               {
                  IncludeSubobjectFromSubobject( vLPLR, "W_MetaDef",
                                                 IncludeView, "W_MetaDef",
                                                 zPOS_AFTER );
               }

               nRC = SetCursorNextEntity( IncludeView, "W_MetaDef", "" );
            }
         }
      }

      DropView( IncludeView );
      zgSortEntityWithinParent( zASCENDING, vLPLR, "W_MetaType", "Type", "" );
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: oTZEREMDO_GetUpdViewForER
   //
   // PURPOSE:   Get Updatable ER View.  Activate if not loaded and get at application level if loaded.
   //
   //////////////////////////////////////////////////////////////////////////////
   public int oTZEREMDO_GetUpdViewForER( View vSubtask, zVIEW vTZEREMDO, int nCurrentOrReload )
   {
      zVIEW  vTZERMFLO = new zVIEW( );
      int    nRC;

   // Get Access to ERD File List Object
      nRC = RetrieveViewForMetaList( vSubtask, vTZERMFLO, zREFER_ERD_META );
      if ( nRC < 0 )
         return -2;

      if ( SetCursorFirstEntity( vTZERMFLO, "W_MetaDef", "" ) < zCURSOR_SET )
      {
         return( -1 );
      }
      else
      {
         nRC = ActivateMetaOI( vSubtask, vTZEREMDO, vTZERMFLO, zSOURCE_ERD_META, zSINGLE | zLEVEL_APPLICATION );
      }

      return( nRC );
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: oTZEREMDO_GetRefViewForER
   //
   // PURPOSE:   Get access to ER View for Reference.  Activate if not loaded and get at application level if loaded.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int oTZEREMDO_GetRefViewForER( View vSubtask, zVIEW vTZEREMDO, int nCurrentOrReload )
   {
      zVIEW  vTZERMFLO = new zVIEW( );
      int    nRC;

   // Get Access to ERD File List Object
      nRC = RetrieveViewForMetaList( vSubtask, vTZERMFLO, zREFER_ERD_META );
      if ( nRC < 0 )
      {
      // vTZEREMDO = 0;
         return -2 ;
      }

      if ( SetCursorFirstEntity( vTZERMFLO, "W_MetaDef", "" ) < zCURSOR_SET )
      {
         return( -2 );
      }

      nRC = ActivateMetaOI( vSubtask, vTZEREMDO, vTZERMFLO, zREFER_ERD_META, zSINGLE | zLEVEL_APPLICATION );
      return( nRC );
   }

   //./ ADD NAME=zgGetZeidonToolsDir
   // Source Module=tzlooprs.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  TZ OPERATION: zgGetZeidonToolsDir
   //
   //  PURPOSE:    This routine returns the dir for the running Zeidon Tool,
   //              when you may not have a Subtask view...
   //
   //  PARAMETERS: pchFullDir - An String returned (should be [ 256 ]) that is
   //                          directory path with an ending \.
   //              nDirType  - same value as GetApplDirectoryFromView
   //                          zAPPL_DIR_LIB      = 1 - DLL Directory
   //                          zAPPL_DIR_OBJECT   = 2 - Exe Meta Directory
   //                          zAPPL_DIR_LOCAL    = 3 - Local directory
   //                          zAPPL_DIR_SHARED   = 4 - Shared directory
   //
   //  RETURNS:              0 - all ok
   //              zCALL_ERROR - can't locate a directory for the running tool
   //
   /////////////////////////////////////////////////////////////////////////////
   private int zgGetZeidonToolsDir( View vSubtask, StringBuilder sbFullDir, int nDirType )
   {
      zVIEW vT = new zVIEW();

      sbFullDir.setLength( 0 );
      if ( SfCreateSubtask( vT, vSubtask, "Zeidon System" ) == zCALL_ERROR )
      {
         return( zCALL_ERROR );
      }

      GetApplDirectoryFromView( sbFullDir, vT, nDirType, 256 );
      SfDropSubtask( vT, 0 );
      SysAppendcDirSep( sbFullDir );

   // TraceLineS( "zgGetZeidonToolsDir --> ", sbFullDir );

      return 0;
   }

   //BL, 2000.01.13 if PPE does not exist in LPLR directory, then load PPE from
   //               Zeidon Bin/Sys  directory
   public int LoadZeidonPPE( View vSubtask, zVIEW pvPPEView, int nType, zVIEW vMetaView, String pchMessageTitle, String pchMessageText )
   {
      StringBuilder sbFileName = new StringBuilder();
      String   szLPLR_Phat = "";
      StringBuilder szSystem_Phat = new StringBuilder();
      zVIEW vTaskLPLR = new zVIEW();
      zVIEW vSystemPPE = new zVIEW();
      zVIEW vZeidonCM = new zVIEW();
      int nRC;

      RetrieveViewForMetaList( vSubtask, vMetaView, nType );

      if ( SetCursorFirstEntity( vMetaView, "W_MetaDef", "" ) < zCURSOR_SET )
      {
         // No PPE in LPLR, use the one from Zeidon System.

         // First, if we already got it on system level, take the "one and only".
         GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION );
         nRC = GetViewByName( vSystemPPE, "PPE_FromBinSys", vZeidonCM, zLEVEL_SUBTASK );

         if ( nRC >= 0 )
         {
            // ok, we found it ...
            // Create a new view, because it will be renamed.
            CreateViewFromViewForTask( pvPPEView, vSystemPPE, vSubtask );
            return 0;
         }

         zgGetZeidonToolsDir( vSubtask, szSystem_Phat, zAPPL_DIR_OBJECT );
         zstrcpy( sbFileName, szSystem_Phat );
         zstrcat( sbFileName, "ZEIDON.PPE" );

         // zLEVEL_APPLICATION is required, so that the view
         //  would belong to the main task. Strange ...
         nRC = ActivateOI_FromFile( vSystemPPE, "TZPESRCO",
                                    vMetaView, sbFileName.toString(),
                                    zIGNORE_ERRORS | zLEVEL_APPLICATION );
         if ( nRC < 0 )
         {
            if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) > 0 )
            {
               szLPLR_Phat = GetStringFromAttribute( vTaskLPLR, "LPLR", "MetaSrcDir" );
            }
            else
            {
               zstrcpy( szSystem_Phat, "" );
            }

            StringBuilder sbMessage = new StringBuilder( "Presentation Environment was not found in directories\n" );
            zstrcat( sbMessage, szLPLR_Phat );
            zstrcat( sbMessage, "  and  " );
            zstrcat( sbMessage, szSystem_Phat );
            zstrcat( sbMessage, ".\n\n" );
            zstrcat( sbMessage, pchMessageText );
            SysMessageBox( vTaskLPLR, pchMessageTitle, sbMessage.toString(), 0 );
            return -1;
         }

         // Create a copy, PPE must be on on System Level,
         //  because it is linked to TZWDLGSO.
         CreateViewFromViewForTask( pvPPEView, vSystemPPE, vSubtask );
         SetNameForView( vSystemPPE, "PPE_FromBinSys", vZeidonCM, zLEVEL_SUBTASK );
      // SetViewFlags( vSystemPPE, 1 );
      }
      else
      {
         nRC = ActivateMetaOI( vSubtask, pvPPEView, vMetaView, nType, zSINGLE );
      }

      return( nRC );
   }

   //BL, 2000.01.28  if Component check out
   //                RETURNS:    0   - Component (View) is NOT checked out
   //                            1   - Component (View) is checked out
   //                            zCALL_ERROR
   private int ComponentIsCheckedOut( View vSubtask, View vComponentView, int lType )
   {
      String szEntityname;
      String szAttributename;
      zVIEW  vActiveMetas = new zVIEW();
      zVIEW  vZeidonCM = new zVIEW();
      zVIEW  vLPLR = new zVIEW();
      zVIEW  vComponent = new zVIEW();
      int nRC = 1;

      if ( GetViewByName( vLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) <= 0 )
      {
         return( zCALL_ERROR );
      }

      if ( GetViewByName( vZeidonCM, "ZeidonCM", vSubtask, zLEVEL_APPLICATION ) <= 0 )
      {
         return( zCALL_ERROR );
      }

      if ( GetViewByName( vActiveMetas, "OpenCM_Metas", vZeidonCM, zLEVEL_SUBTASK ) <= 0 )
      {
         return( zCALL_ERROR );
      }

      if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type", lType, "" ) != zCURSOR_SET )
      {
         if ( lType >= 2000 )
         {
            lType = lType - 2000;
            if ( SetCursorFirstEntityByInteger( vActiveMetas, "W_MetaType", "Type", lType, "" ) != zCURSOR_SET )
            {
               return( nRC );
            }
         }
      }

      if ( CheckExistenceOfEntity( vActiveMetas, "W_MetaDef" ) == zCURSOR_NULL )
      {
         return( nRC );
      }

      if ( isValid( vComponentView ) == false )
      {
         return( nRC );
      }

      CreateViewFromViewForTask( vComponent, vComponentView, vSubtask );
      ResetView( vComponent );

      szEntityname = "";
      zGetFirstEntityNameForView( vComponent, szEntityname );

      if ( lType == zSOURCE_DIALOG_META || lType == zSOURCE_REPORT_META || lType == zSOURCE_XSLT_META )
      {
         szAttributename = "Tag";
      }
      else
      {
         szAttributename = "Name";
      }

      // Position on the correct Component within the type.
      if ( SetCursorFirstEntityByAttr( vActiveMetas, "W_MetaDef", "Name",
                                       vComponent, szEntityname, szAttributename, "" ) == zCURSOR_SET &&
           CompareAttributeToInteger( vActiveMetas, "W_MetaDef", "Status", 1 ) != 0 )
      {
         nRC = 0;
      }

      DropView( vComponent );
      return( nRC );
   }

   //BL, 2000.02.17  Check: duplicate component types between CPLR and LPLR
   //                       (for ERD, TE, PPE )
   //
   //                RETURNS:    < 0   - duplicate component
   //                            = 0   - no duplicate component
   public int CheckDuplicateComponents( View   vTZCMLPLO,       // LPLR View
                                        View   vTZCMCPRO,       // CPLR View
                                        int   lComponentType,  // Component type
                                        StringBuilder sbName )        // Name for duplicate Component
   {
      zVIEW vTZCMLPLO_Copy = new zVIEW();
      zVIEW vTZCMCPRO_Copy = new zVIEW();
      int   ulZKeyLPLR;
      int   ulZKeyCPRO;
      int   lTypeCPRO;
      int   nRC;

      CreateViewFromViewForTask( vTZCMLPLO_Copy, vTZCMLPLO, null );
      CreateViewFromViewForTask( vTZCMCPRO_Copy, vTZCMCPRO, null );

      nRC = 0;
      zstrcpy( sbName, "" );

      if ( CheckExistenceOfEntity( vTZCMCPRO_Copy, "Z_MetaDef" ) >= zCURSOR_SET  &&
           SetCursorFirstEntityByInteger( vTZCMCPRO_Copy, "Z_MetaDef", "Type",
                                          lComponentType, "" ) == zCURSOR_SET    &&
           CheckExistenceOfEntity( vTZCMLPLO_Copy, "W_MetaType" ) >= zCURSOR_SET &&
           SetCursorFirstEntityByInteger( vTZCMLPLO_Copy, "W_MetaType", "Type",
                                          lComponentType, "" ) == zCURSOR_SET    &&
           CheckExistenceOfEntity( vTZCMLPLO_Copy, "W_MetaDef" ) >= zCURSOR_SET )
      {
         lTypeCPRO = GetIntegerFromAttribute( vTZCMCPRO_Copy, "Z_MetaDef", "Type" );
         if ( lTypeCPRO == lComponentType )
         {
            //2 Components exist, check the Keys
            ulZKeyLPLR = GetIntegerFromAttribute( vTZCMLPLO_Copy, "W_MetaDef", "CPLR_ZKey" );
            ulZKeyCPRO = GetIntegerFromAttribute( vTZCMCPRO_Copy, "Z_MetaDef", "ZKey" );

            if ( ulZKeyLPLR != ulZKeyCPRO )
            {
               // duplicate component
               GetStringFromAttribute( sbName, vTZCMCPRO_Copy, "Z_MetaDef", "Name" );
            // nRC = 1;
               return -1;
            }
         }
      } //endif ( SetCursorFirstEntityByInteger( TZCMLPLO_Copy ....

      DropView( vTZCMLPLO_Copy );
      DropView( vTZCMCPRO_Copy );

      return( nRC );
   }

   //BL, 2000.02.17  Check all component for duplicate component names
   //
   //                RETURNS:    < 0   - duplicate component names
   //                            = 0   - no duplicate component names
   public int CheckAllDuplicateName( View   vTZCMLPLO,   // LPLR View
                                     View   vTZCMCPRO,   // CPLR View
                                     StringBuilder sbTypeCPLR,  // Return Type for duplicate Name
                                     StringBuilder sbTypeLPLR,  // Return Type for duplicate Name
                                     StringBuilder sbName )     // Return Name for duplicate Component
   {
      zVIEW vTZCMLPLO_Copy = new zVIEW();
      int  nRC;

      CreateViewFromViewForTask( vTZCMLPLO_Copy, vTZCMLPLO, null );
      if ( CheckExistenceOfEntity( vTZCMLPLO_Copy, "W_MetaType" ) < zCURSOR_SET )
      {
         return 0;
      }

      nRC = SetCursorFirstEntity( vTZCMLPLO_Copy, "W_MetaDef", "LPLR" );
      while ( nRC >= zCURSOR_SET )
      {
         nRC = CheckDuplicateName( vTZCMLPLO_Copy, vTZCMCPRO, sbTypeCPLR, sbTypeLPLR, sbName );
         if ( nRC < 0 )
         {
            DropView( vTZCMLPLO_Copy );
            return( nRC );
         }

         nRC = SetCursorNextEntity( vTZCMLPLO_Copy, "W_MetaDef", "LPLR" );
      }

      DropView( vTZCMLPLO_Copy );
      return 0;
   }

   //BL, 2000.02.17  Check current component for duplicate component names
   //
   //          RETURNS:    - 1   - duplicate component names within component type
   //                      - 2   - duplicate component names between LODs and Dialogs
   //                      = 0   - no duplicate component names
   public int CheckDuplicateName( View   vTZCMLPLO,       // LPLR View
                                  View   vTZCMCPRO,       // CPLR View
                                  StringBuilder  sbTypeCPLR,      // Return Type for duplicate Name
                                  StringBuilder  sbTypeLPLR,      // Return Type for duplicate Name
                                  StringBuilder  sbName )         // Return Name for duplicate Component
   {
      zVIEW vTZCMCPRO_Copy = new zVIEW();
      int  ulZKeyLPLR;
      int  ulZKeyCPRO;
      int  lTypeCPRO;
      int  lTypeLPLR;
      int  nRC;

      CreateViewFromViewForTask( vTZCMCPRO_Copy, vTZCMCPRO, null );
      GetStringFromAttribute( sbName, vTZCMLPLO, "W_MetaDef", "Name" );

      // If a component exists with this name, then check the Key.
      if ( SetCursorFirstEntityByString( vTZCMCPRO_Copy, "Z_MetaDef", "Name", sbName.toString(), "" ) >= zCURSOR_SET )
      {
         ulZKeyLPLR = GetIntegerFromAttribute( vTZCMLPLO, "W_MetaDef", "CPLR_ZKey" );
         do
         {
            ulZKeyCPRO = GetIntegerFromAttribute( vTZCMCPRO_Copy, "Z_MetaDef", "ZKey" );
            //is a other component, check the type
            if ( ulZKeyLPLR != ulZKeyCPRO )
            {
               lTypeCPRO = GetIntegerFromAttribute( vTZCMCPRO_Copy, "Z_MetaDef", "Type" );
               lTypeLPLR = GetIntegerFromAttribute( vTZCMLPLO, "W_MetaType", "Type" );
               if ( lTypeLPLR >= 2000 )
               {
                  lTypeLPLR = lTypeLPLR - 2000;
               }

               if ( lTypeCPRO >= 2000 )
               {
                  lTypeCPRO = lTypeCPRO - 2000;
               }

               nRC = 0;
               if ( lTypeCPRO == lTypeLPLR )
               {
                  nRC = -1;
               }

               //check duplicate Name between Dialogs and LODs
               if ( (lTypeCPRO == zSOURCE_DIALOG_META && lTypeLPLR == zSOURCE_LOD_META) ||
                    (lTypeCPRO == zSOURCE_LOD_META && lTypeLPLR == zSOURCE_DIALOG_META) )
               {
                  nRC = -2;
               }

               if ( nRC < 0 )
               {
                  GetVariableFromAttribute( sbTypeCPLR, 0, zTYPE_STRING, 125, vTZCMCPRO_Copy, "Z_MetaDef", "Type", "CM_Type", 0 );
                  GetVariableFromAttribute( sbTypeLPLR, 0, zTYPE_STRING, 125, vTZCMLPLO, "W_MetaType", "Type", "CM_Type", 0 );
                  DropView( vTZCMCPRO_Copy );
                  return( nRC );
               }
            } //endif ( ulZKeyLPLR != ulZKeyCPRO )
         }
         while ( SetCursorNextEntityByString( vTZCMCPRO_Copy, "Z_MetaDef", "Name", sbName.toString(), "" ) >= zCURSOR_SET );
      } //endif ( SetCursorFirstEntityByString( vTZCMCPRO_Copy, "Z_MetaDef"...

      DropView( vTZCMCPRO_Copy );

      return 0;
   }

   //BL, 2000.02.17  Check current component for duplicate component names
   //
   //                RETURNS:    < 0   - duplicate component names
   //                            = 0   - no duplicate component names
   public int CheckDuplicateNameForRefresh( View    vTZCMLPLO,   // LPLR View
                                            View    vTZCMCPRO,   // CPLR View
                                            StringBuilder sbTypeCPLR,  // ReturnType for duplicate Name
                                            StringBuilder sbTypeLPLR,  // ReturnType for duplicate Name
                                            StringBuilder sbName,      // ReturnName for duplicate Comp
                                            MutableInt miZKeyLPLR )// ReturnZKey for duplicate Comp
   {
      zVIEW vTZCMLPLO_Copy = new zVIEW();
      int   ulZKeyCPRO;
      int   lTypeCPRO;
      int   lTypeLPLR;
      int   nRC;

      miZKeyLPLR.setValue( 0 );
      CreateViewFromViewForTask( vTZCMLPLO_Copy, vTZCMLPLO, null );

      GetStringFromAttribute( sbName, vTZCMCPRO, "Z_MetaDef", "Name" );

      // If a component exists with this name, then check the Key.
      if ( SetCursorFirstEntityByString( vTZCMLPLO_Copy, "W_MetaDef", "Name", sbName.toString(), "LPLR" ) >= zCURSOR_SET )
      {
         ulZKeyCPRO = GetIntegerFromAttribute( vTZCMCPRO, "Z_MetaDef", "ZKey" );
         do
         {
            GetIntegerFromAttribute( miZKeyLPLR, vTZCMLPLO_Copy, "W_MetaDef", "CPLR_ZKey" );

            // If it is another component ... check the type.
            if ( miZKeyLPLR.intValue() != ulZKeyCPRO )
            {
               lTypeCPRO = GetIntegerFromAttribute( vTZCMCPRO, "Z_MetaDef", "Type" );
               lTypeLPLR = GetIntegerFromAttribute( vTZCMLPLO_Copy, "W_MetaType", "Type" );
               if ( lTypeLPLR >= 2000 )
               {
                  lTypeLPLR = lTypeLPLR - 2000;
               }

               if ( lTypeCPRO >= 2000 )
               {
                  lTypeCPRO = lTypeCPRO - 2000;
               }

               nRC = 0;
               if ( lTypeCPRO == lTypeLPLR )
               {
                  nRC = -1;
               }

               // Check duplicate Name between Dialogs and LODs.
               if ( (lTypeCPRO == zSOURCE_DIALOG_META && lTypeLPLR == zSOURCE_LOD_META) ||
                    (lTypeCPRO == zSOURCE_LOD_META && lTypeLPLR == zSOURCE_DIALOG_META) )
               {
                  nRC = -2;
               }

               if ( nRC < 0 )
               {
                  GetVariableFromAttribute( sbTypeLPLR, 0, zTYPE_STRING, 125, vTZCMLPLO_Copy, "W_MetaType", "Type", "CM_Type", 0 );
                  GetVariableFromAttribute( sbTypeCPLR, 0, zTYPE_STRING, 125, vTZCMCPRO, "Z_MetaDef", "Type", "CM_Type", 0 );
                  DropView( vTZCMLPLO_Copy );
                  return( nRC );
               }
            } //endif ( ulZKeyLPLR != ulZKeyCPRO )
         }
         while ( SetCursorNextEntityByString( vTZCMLPLO_Copy, "W_MetaDef", "Name", sbName.toString(), "LPLR" ) >= zCURSOR_SET );
      } //endif ( SetCursorFirstEntityByString( vTZCMLPLO_Copy, "W_MetaDef"...

      DropView( vTZCMLPLO_Copy );

      return 0;
   }

   private void SetWindowCaptionTitle( View vSubtask, String caption, String title )
   {
      // TODO: dks ... sometime when we have a dialog/window
   }

   //BL, 2000.02.25  set Project name, Tool name, component name and check out state in title
   //
   //                RETURNS:    < 0   - error
   //                            = 0   - no error
   private int SetTitleWithCheckOutState( View vSubtask, String szToolname, String szLodname, zVIEW vToolView,
                                          String  szEntityname, int lType )
   {
      zVIEW   vTaskLPLR = new zVIEW();
      String  szComponentName;
      String  szLPL_Name;
      String  szAttributename;
      String  szTitle;
      String  szCheckedOut;

      if ( GetViewByName( vTaskLPLR, "TaskLPLR", vSubtask, zLEVEL_TASK ) < 0 )
      {
         SetWindowCaptionTitle( vSubtask, szToolname, "" );
         return -1;
      }

      if ( lType == zSOURCE_DIALOG_META || lType == zSOURCE_REPORT_META || lType == zSOURCE_XSLT_META )
      {
         szAttributename = "Tag";
      }
      else
      {
         szAttributename = "Name";
      }

      szLPL_Name = GetStringFromAttribute( vTaskLPLR, "LPLR", "Name" );

      if ( GetViewByName( vToolView, szLodname, vSubtask, zLEVEL_TASK ) >= 0 )
      {
         szComponentName = GetStringFromAttribute( vToolView, szEntityname, szAttributename );

         // if component not checked out, then set new Title
         if ( ComponentIsCheckedOut( vSubtask, vToolView, lType ) == 0 )
         {
            szCheckedOut = "   < not checked out >";
         }
         else
         {
            szCheckedOut = "";
         }
      }
      else
      {
         szComponentName = "[Untitled]";
         szCheckedOut = "";
      }

      szTitle = szToolname + " - " + szComponentName + szCheckedOut;
      SetWindowCaptionTitle( vSubtask, szLPL_Name, szTitle );

      return 0;
   }
/*
   //BL, 2000.02.25  for windows within Tools (for example Open Domain):
   //                set check out state in title and returned checked out state
   //                RETURNS:    0   - Component is NOT checked out
   //                            1   - Component is checked out
   //                            zCALL_ERROR
   public int CheckOutStateForCurrentWindow( View vSubtask, View vToolView )
   {
      String  szTitle;
      String  szNotCheckedOut;
      int nCheckOut = 0;
      HWND   hWnd;

      GetWindowHandle( (zPULONG) &hWnd, 0, vSubtask, 0 );
      GetWindowText( hWnd, szTitle, 249 );

      zstrcpy( szNotCheckedOut, "   < not checked out >" );
      zSearchAndReplace( szTitle, 249, szNotCheckedOut, "" );

      if ( CheckExistenceOfEntity( vToolView, "W_MetaDef" ) >= zCURSOR_UNDEFINED )
      {
         if ( CompareAttributeToInteger( vToolView, "W_MetaDef",
              "Status", 1 ) == 0 )
         {
            nCheckOut = 1;
         }

         // if component not checked out
         if ( nCheckOut )
            zstrcat( szTitle, "" );
         else
            zstrcat( szTitle, szNotCheckedOut );

         SetWindowCaptionTitle( vSubtask, szTitle, "" );
      }

      return( nCheckOut );
   }

   // Save sort order for window open components (for example "Open Domain").
   public int SaveSortOrder( View vSubtask, View vCM_List )
   {
      String pchListCtrl = "Name";  // this is bogus ... TODO: dks defaulting...
      String szAttributeName;
      int    lType;

      if ( CheckExistenceOfEntity( vCM_List, "W_MetaType" ) < zCURSOR_SET )
         return -1;

      // get ListCtrl Tag
   // pchListCtrl = (String) GetActionParameters( vSubtask );  TODO: dks what can we do here?

      // get Component Type
      lType = GetIntegerFromAttribute( vCM_List, "W_MetaType", "Type" );

      if ( zstrcmp( pchListCtrl, "Name" ) == 0 )
         SetAttributeFromInteger( vCM_List, "W_MetaType", "SortOrder", 1 );
      else
      if ( zstrcmp( pchListCtrl, "LastUpdated" ) == 0 )
         SetAttributeFromInteger( vCM_List, "W_MetaType", "SortOrder", 2 );
      else
      {
         if ( lType == zREFER_DOMAIN_META || lType == zREFER_GO_META )
            szAttributeName = "GroupName";
         else
            szAttributeName = "Description";

         if ( zstrcmp( pchListCtrl, szAttributeName ) == 0 )
            SetAttributeFromInteger( vCM_List, "W_MetaType", "SortOrder", 3 );
         else
            SetAttributeFromInteger( vCM_List, "W_MetaType", "SortOrder", 0 );
      }

      return 0;
   }

   // Sort components for window open components (for example "Open Domain").
   private int SortComponents( View vCM_List )
   {
      int  lSortOrder;
      String  szOrderKeys;
      int  lType;

      if ( CheckExistenceOfEntity( vCM_List, "W_MetaType" ) < zCURSOR_SET ||
           CheckExistenceOfEntity( vCM_List, "W_MetaDef" ) < zCURSOR_SET )
      {
         return -1;
      }

      // Get Component Type.
      lSortOrder = GetIntegerFromAttribute( vCM_List, "W_MetaType", "SortOrder" );
      lType = GetIntegerFromAttribute( vCM_List, "W_MetaType", "Type" );

      switch( lSortOrder )
      {
         case 0:        // no sort order
            szOrderKeys = "LastUpdateDate D Name A";
            break;

         case 1:        // sort by name
            szOrderKeys = "Name A LastUpdateDate D";
            break;

         case 2:        // sort by last update date
            szOrderKeys = "LastUpdateDate D Name A";
            break;

         case 3:        // sort by Group or Description
            if ( lType == zSOURCE_DOMAIN_META || lType == zSOURCE_GO_META ||
                 lType == zREFER_DOMAIN_META  || lType == zREFER_GO_META )
            {
               szOrderKeys = "GroupName A Name A";
            }
            else
            {
               szOrderKeys = "Desc A Name A";
            }

            break;

         default:
            szOrderKeys = "";
      }

      // Now order the domains by date and name.
      OrderEntityForView( vCM_List, "W_MetaDef", szOrderKeys );

      return 0;
   }

   // .Operation description
   //
   //  This function return a pathname in variable "output", which is built in the following way
   //  - pathname is full qualified, means that
   //    - pathname begins with a: (where a is any alpha character)
   //    - pathname begins with "\"
   //    - pathname begins with "\\"
   //    output = pathname
   //  - pathname is not full qualified
   //    - pathname = "" or pathname = "." ==> outpur = basedir
   //    - otherwise: output = basedir + \ + pathname
   //
   //  Returncode: = 1, pathname was fully qualified
   //              = 0, relative path is build
   //              = -1, builded pathname is too long
   //
   //----------------------------------------------------------------------
   private int
   GenFullQualPathFromRelPath( String pchPathName,  // Pathname or empty string
                               String pchBaseDir,   // base for relative paths
                               StringBuilder sbOutput, // Result buffer, must be big enough
                               int    nMaxLth )
   {
      SysConvertEnvironmentString( sbOutput, pchBaseDir );

      if ( pchPathName.length() >= 2 )
      {
         // Conditions for full qualified pathnames:
         // (first character = a-z OT A-Z) AND second character = :
         //  or
         //  first character = \

         char ch = pchPathName.charAt( 0 );
         if ( (((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) && pchPathName.charAt( 1 ) == ':') || ch == '\\' )
         {
            zstrcpy( sbOutput, pchPathName ); // fully qualified path
            return( 1 );
         }
         else
         {
            if ( pchBaseDir.endsWith( "\\" ) == false )
            {
               zstrcat( sbOutput, "\\" );  // add backslash, if it's needed
            }

            zstrcat( sbOutput, pchPathName ); // add pathname after basedir
         }
      }

      // Is the result length greater than the maximum length?
      if ( sbOutput.length() < nMaxLth )
      {
         return 0;
      }
      else
      {
         return -1;
      }
   }

   private int
   IntGenFullQualPathFromRelPath( View  vSubtask,
                                  StringBuilder sbFilename,
                                  String pchBaseDir,
                                  int nMaxLth,
                                  String pchDirTxt )
   {
      StringBuilder sbOutput = new StringBuilder();
      int nRC;

      // Build a fully qualified path from a relative path for the Target Executable Directory
      if ( (nRC = GenFullQualPathFromRelPath( sbFilename.toString(), pchBaseDir, sbOutput, nMaxLth )) == -1 )
      {
         StringBuilder sbMsg = new StringBuilder();
         // Path exceeds max. length
         zsprintf( sbMsg, "Path for %s exceeds maximum size", pchDirTxt );
         MessageSend( vSubtask, "ED00105", "Zeidon Compiler", sbMsg.toString(),
                      zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         return -1;
      }
      else
      {
         zstrcpy( sbFilename, sbOutput.toString() );
         return( nRC );
      }
   }

   private int
   SetCompSpecPaths( View  vTarget,
                     String pchSourceCompSrcDir,
                     String pchSourceLPLRName,
                     String pchSourceAktDir,
                     String pchEntityName,
                     String pchAttributeName,
                     String pchDirName,
                     String pchMsgTitle,
                     int nMaxLth )
   {
      StringBuilder sbNewPath = new StringBuilder();
      String szAktDirUpper;
      String szBaseDir;
      String szBaseDirUpper;
      String szLPLRNameUpper;
      String szAktLPLRName;

      int nAnfPos;
      int nAnzahl;
      int nAktLth;
      int nBaseLth;
      int nRC;

      // for comparing change the actual directory-name to uppercase
      szAktDirUpper = zToUpper( pchSourceAktDir );

      // The pathnames in Compilerspecification should be changed.
      // See if the actual directory is relative or fully qualified.
      nRC = GenFullQualPathFromRelPath( pchSourceAktDir, pchSourceCompSrcDir, sbNewPath, nMaxLth );
      switch( nRC )
      {
         case 0 :
            // The pathname was relative. There is nothing to do.
            SetAttributeFromString( vTarget, pchEntityName, pchAttributeName, pchSourceAktDir );
            break;

         case 1 :
            // The path was fully qualified.
            // See if ComponentSourceDirectory is part of the pathname.
            szBaseDirUpper = zToUpper( pchSourceCompSrcDir );
            nRC = zSearchSubString( szAktDirUpper, szBaseDirUpper, "f", 0 );
            if ( nRC != -1 )
            {
               // The Component Source Directory is part of the pathname
               // Take the new component Source directory and add the end of
               // the original pathname
               szBaseDir = GetStringFromAttribute( vTarget, "LPLR", "MetaSrcDir" );
               nAktLth = zstrlen( szAktDirUpper );
               nBaseLth = zstrlen( szBaseDirUpper );
               nAnzahl = nAktLth - nBaseLth;
               nAnfPos = nAktLth - nAnzahl + 1;
               ZeidonStringConcat( szBaseDir, 1, 0, pchSourceAktDir, nAnfPos, nAnzahl, 257);
               SetAttributeFromString( vTarget, pchEntityName, pchAttributeName, szBaseDir );
            }
            else
            {
               // The Component Source Directory isn't part of the pathname
               // See if the LPLR-Name is part of the pathname
               szLPLRNameUpper = zToUpper( pchSourceLPLRName );
               nRC = zSearchSubString( szAktDirUpper, szLPLRNameUpper, "f", 0 );
               if ( nRC == -1 )
               {
                  // LPLR-Name isn't part of the pathname. Take it like it is
                  SetAttributeFromString( vTarget, pchEntityName, pchAttributeName, pchSourceAktDir );
               }
               else
               {
                  // LPLR-Name is part of the pathname. Replace the old LPLR-Name with
                  // the new one.
                  szAktLPLRName = GetStringFromAttribute( vTarget, "LPLR", "Name" );
                  zSearchAndReplace( pchSourceAktDir, 255, pchSourceLPLRName, szAktLPLRName );
                  SetAttributeFromString( vTarget, pchEntityName, pchAttributeName, pchSourceAktDir );
               }
            }

            break;

         default:
         {
            StringBuilder szMsg = new StringBuilder( "Path " );
            ZeidonStringConcat( szMsg, 1, 0, pchDirName, 1, 0, 513 );
            ZeidonStringConcat( szMsg, 1, 0, " exceeds maximum size !", 1, 0, 513 );
            TraceLineS( pchMsgTitle, szMsg.toString() );
            break;
         }
      }

      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // ENTRY:    SetUpMapLOD
   //
   // PURPOSE:  This function sets up the SEL_LOD OI for Entity or
   //           Attribute mapping.  If a current CtrlMapLodDef
   //           instance exists, it positions on the related ViewObjRef
   //           entity and activates the corresponding LOD.
   //
   /////////////////////////////////////////////////////////////////////////////
   private View SetUpMapLOD( View vSubtask, View vDialogW, View vDialogC, String cpcMapView )
   {
      zVIEW  vLOD = new zVIEW();
      zVIEW  vCM_List = new zVIEW();
      int    lZKey;

      // Set up SEL_LOD, if current mapping exists, and any other mapping data required.

      // cpcMapView is CtrlMapView if this was called for Control mapping ... or
      // cpcMapView is ActMapView if this was called for Action mapping.  If the
      // MapView exists, use the corresponding LOD for mapping.  Use cpcMapView
      // ZKey to locate ViewObjRef and then LOD ZKey to locate LPLR LOD meta.
      if ( CheckExistenceOfEntity( vDialogC, cpcMapView ) == zCURSOR_SET )
      {
         RetrieveViewForMetaList( vSubtask, vCM_List, zREFER_LOD_META );
         lZKey = GetIntegerFromAttribute( vDialogC, cpcMapView, "ZKey" );
         SetCursorFirstEntityByInteger( vDialogW, "ViewObjRef", "ZKey", lZKey, "" );
         lZKey = GetIntegerFromAttribute( vDialogW, "LOD", "ZKey" );
         SetCursorFirstEntityByInteger( vCM_List, "W_MetaDef", "CPLR_ZKey", lZKey, "" );
         ActivateMetaOI( vSubtask, vLOD, vCM_List, zREFER_LOD_META, 0 );
      }

      return vLOD.getView();
   }
*/
}
