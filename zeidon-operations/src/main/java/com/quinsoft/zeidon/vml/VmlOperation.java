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

package com.quinsoft.zeidon.vml;

import static org.ini4j.zeidon.Config.PROP_PATH_SEPARATOR;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.EndTagType;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.HTMLElements;
import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.StartTagType;
import net.htmlparser.jericho.Tag;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;
import org.ini4j.zeidon.Config;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.InvalidViewException;
import com.quinsoft.zeidon.ObjectConstraintException;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.SetMatchingFlags;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownAttributeDefException;
import com.quinsoft.zeidon.UnknownEntityDefException;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiFlags;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.domains.TableDomain;
import com.quinsoft.zeidon.domains.TableEntry;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.standardoe.IncrementalEntityFlags;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/*
//package org.kodejava.example.lowagie;

import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
*/

//import com.quinsoft.zeidon.vml.VmlDialog.MemoryItem;
//import com.quinsoft.zeidon.vml.VmlDialog.MemoryList;
//import com.quinsoft.zeidon.vml.VmlDialog.ZHTML_Attribute;
//import com.quinsoft.zeidon.vml.VmlDialog.ZHTML_Parse;
//import com.quinsoft.zeidon.vml.VmlDialog.ZHTML_ParseHTML;
//import com.quinsoft.zeidon.vml.VmlDialog.ZHTML_TagAttributeList;
//import com.quinsoft.zeidon.vml.VmlDialog.ZNameItem;

/**
 * This is a helper class for Java code generated from VML.  It has some standard
 * "operations" defined so that the generated java can look similar to the generated C.
 *
 * Code that is generated from VML should extend this class.
 *
 * @author DG
 *
 */

// public static boolean isEmpty(String str)  Checks if a String is empty ("") or null.

// StringUtils.isEmpty(null) = true
// StringUtils.isEmpty("") = true
// StringUtils.isEmpty(" ") = false
// StringUtils.isEmpty(" bob ") = false

// public static boolean isBlank(String str)  Checks if a String is whitespace, empty ("") or null.
// StringUtils.isBlank(null) = true
// StringUtils.isBlank("") = true
// StringUtils.isBlank(" ") = true
// StringUtils.isBlank(" bob ") = false

public abstract class VmlOperation
{
   Object lockMessage = new Object();

   public static final int zLEVEL_SUBTASK     = 1;
   public static final int zLEVEL_TASK        = 2;
   public static final int zLEVEL_APPLICATION = 4;
   public static final int zLEVEL_SYSTEM      = 8;
   public static final int zLEVEL_ALL         = 255;
   public static final int zLEVEL_ANY         = 15;
   public static final int zNAME_AUTODROP     = 64;

   public static final int zOCE_ACTIVATE       = 1;
   public static final int zOCE_ACTIVATE_EMPTY = 2;
   public static final int zOCE_COMMIT         = 3;
   public static final int zOCE_DROPOI         = 4;

   public static final int zSET_NULL       = 0x00000000;
   public static final int zSET_KEYS       = 0x00000001;
   public static final int zSET_NOTNULL    = 0x00000002;
   public static final int zSET_SRCNOTNULL = 0x00000004;
   public static final int zSET_ALL        = zSET_NULL | zSET_KEYS | zSET_NOTNULL;

   public static final int zPOS_NONE   = 0;
   public static final int zPOS_FIRST  = 1;
   public static final int zPOS_LAST   = 2;
   public static final int zPOS_NEXT   = 3;
   public static final int zPOS_AFTER  = 3;
   public static final int zPOS_PREV   = 4;
   public static final int zPOS_BEFORE = 4;

   public static final int zREPOS_NONE   = 0;
   public static final int zREPOS_FIRST  = 1;
   public static final int zREPOS_LAST   = 2;
   public static final int zREPOS_AFTER  = 3;
   public static final int zREPOS_NEXT   = 3;
   public static final int zREPOS_BEFORE = 4;
   public static final int zREPOS_PREV   = 4;

   public static final int zSET_INCR_CREATED          = 2;
   public static final int zSET_INCR_PERSISTENT       = 4;
   public static final int zSET_INCR_UPDATED          = 8;
   public static final int zSET_INCR_NOT_UPDATED      = 16;
   public static final int zSET_INCR_INCLUDED         = 32;
   public static final int zSET_INCR_EXCLUDED         = 0;  // undefined in C version
   public static final int zSET_INCR_HIDDEN           = 1;  // undefined in C version
   public static final int zSET_INCR_NOT_INCLUDED     = 64;
   public static final int zSET_INCR_CURSORPOS        = 128;
   public static final int zSET_INCR_HANGING_ENTITY   = 2048;
   public static final int zSET_INCR_TEMPORAL_ENTITY  = 16384;
   public static final int zSET_INCR_DELETED          = 32768;
   public static final int zSET_INCR_ATTR_UPDATED     = 4096;
   public static final int zSET_INCR_ATTR_NOT_UPDATED = 8192;

   public static final int zCOMMIT_NOCLEANUP          = 256;      // 0x00000100L
   public static final int zCOMMIT_FORCETRAN          = 1024;     // 0x00000400L
   public static final int zCOMMIT_DROPLOCKSONLY      = 2048;     // 0x00000800L
   public static final int zCOMMIT_KEEPLOCKS          = 4096;     // 0x00001000L
   public static final int zCOMMIT_DROPVIEW           = 8192;     // 0x00002000L
   public static final int zCOMMIT_DROPOBJECTINSTANCE = 16384;    // 0x00004000L
   public static final int zCOMMIT_NOCONSTRAINTS      = 16777216; // 0x01000000L

   public static final int zASCII                     = 0;        // 0x00000000L
   public static final int zINCREMENTAL               = 65536;    // 0x00010000L
   public static final int zCOMPRESSED                = 131072;   // 0x00020000L  // Store in compressed form.
   public static final int zSAVE_CURSORS              = 262144;   // 0x00040000L  // Save cursor positions.
   public static final int zENTITY_TAGS               = 524288;   // 0x00080000L  // Write entity tags (pointers).
   public static final int zWRITE_KEYS_ONLY           = 1048576;  // 0x00100000L  // Write key attrs only.
   public static final int zENTITY_KEYS               = 2097152;  // 0x00200000L  // Write entity keys.
   public static final int zENCODE_BLOBS              = 4194304;  // 0x00400000L  // Encode blobs from binary to ASCII
   public static final int zNO_NULL_STRING_TERM       = 8388608;  // 0x00800000L  // Don't use null terminator for strings

   /**
    * A map to convert C-style cursor positions (int) to JOE-style (enum).
    */
   private static final Map<Integer, CursorPosition> CURSOR_POS =
       Collections.unmodifiableMap( new HashMap<Integer, CursorPosition>() {
       private static final long serialVersionUID = 1L;
   {
       put( zREPOS_NONE,  CursorPosition.NONE  );
       put( zREPOS_FIRST, CursorPosition.FIRST );
       put( zREPOS_LAST,  CursorPosition.LAST  );
       put( zREPOS_NEXT,  CursorPosition.NEXT  );
       put( zREPOS_PREV,  CursorPosition.PREV  );
   }} );

   public static final int TRUE  = 1;
   public static final int FALSE = 0;

   public static final char zTYPE_STRING = 'S';
   public static final char zTYPE_INTEGER = 'L';
   public static final char zTYPE_DECIMAL = 'M';
   public static final char zTYPE_BLOB = 'B';
   public static final char zTYPE_DATETIME = 'T';
   public static final char zTYPE_PIC = 'P';
   public static final char zTYPE_FIXEDCHAR = 'F';

   public static final int zDERIVED_SET = AttributeDef.DERIVED_SET;
   public static final int zDERIVED_GET = AttributeDef.DERIVED_GET;

   public static final int zCURSOR_SET = CursorResult.SET.toInt();
   public static final int zCURSOR_UNCHANGED = CursorResult.UNCHANGED.toInt();
   public static final int zCURSOR_NULL = CursorResult.NULL.toInt();
   public static final int zCURSOR_UNDEFINED = CursorResult.NULL.toInt();
   public static final int zCURSOR_SET_RECURSIVECHILD = CursorResult.SET_RECURSIVE_CHILD.toInt();
   public static final int zCURSOR_SET_NEWPARENT = CursorResult.SET_NEWPARENT.toInt();

   public static final int zCONTROL_STATUS_ENABLED               = 1;
   public static final int zCONTROL_STATUS_CHECKED               = 2;
   public static final int zCONTROL_STATUS_VISIBLE               = 3;
   public static final int zCONTROL_STATUS_BACKGROUND_COLOR      = 4;
   public static final int zCONTROL_STATUS_TEXT_COLOR            = 5;
   public static final int zCONTROL_STATUS_FONT                  = 6;
   public static final int zCONTROL_STATUS_ENTER                 = 7;
   public static final int zCONTROL_STATUS_ERROR                 = 8;
   public static final int zCONTROL_STATUS_AUTOGRAY              = 9;
   public static final int zCONTROL_STATUS_TOPMOST               = 10;
   public static final int zCONTROL_STATUS_BOTTOMMOST            = 11;
   public static final int zCONTROL_STATUS_HWND                  = 12;
   public static final int zCONTROL_STATUS_AUTOMAP_FROM_OI       = 13;
   public static final int zCONTROL_STATUS_AUTOMAP_TO_OI         = 14;
   public static final int zCONTROL_STATUS_DISABLE_READONLY      = 15;
   public static final int zCONTROL_STATUS_REPAINT               = 16;
   public static final int zCONTROL_STATUS_MARK_READONLY_ENABLED = 17;
   public static final int zCONTROL_STATUS_ENABLE_TABSTOP        = 18;
   public static final int zCONTROL_STATUS_SELECT_INDEX          = 19;
   public static final int zCONTROL_STATUS_ABSOLUTE_PIXEL_SIZE   = 20;
   public static final int zCONTROL_STATUS_ABSOLUTE_PIXEL_POS    = 21;
   public static final int zCONTROL_STATUS_HASBUTTONS            = 22;
   public static final int zCONTROL_STATUS_HASLINES              = 23;
   public static final int zCONTROL_STATUS_LINESATROOT           = 24;
   public static final int zCONTROL_STATUS_DISABLEDRAGDROP       = 25;
   public static final int zCONTROL_STATUS_NOSHOW_ENTITY         = 26;
   public static final int zCONTROL_STATUS_MULTIPLEROOT          = 27;
   public static final int zCONTROL_STATUS_MULTISELECT           = 28;
   public static final int zCONTROL_STATUS_SINGLEEXPAND          = 29;
   public static final int zCONTROL_STATUS_EXPAND                = 30;
   public static final int zCONTROL_STATUS_EXPANDALL             = 31;
   public static final int zCONTROL_STATUS_MAP_ERROR             = 32;
   public static final int zCONTROL_STATUS_SELECT_ALL            = 33;
   public static final int zCONTROL_STATUS_RESET                 = 34;
   public static final int zCONTROL_STATUS_CREATED               = 35;
   public static final int zCONTROL_STATUS_MAPPED                = 36;

// protected static final Set<ActivateFlags> zSINGLE   = ActivateFlags.SINGLE;
// protected static final Set<ActivateFlags> zMULTIPLE = ActivateFlags.MULTIPLE;
// protected static final Set<ActivateFlags> zACTIVATE_ROOTONLY_MULTIPLE = ActivateFlags.ROOT_ONLY_MULTIPLE;
// protected static final Set<ActivateFlags> zACTIVATE_ROOTONLY = ActivateFlags.ROOT_ONLY_MULTIPLE;
// protected static final Set<ActivateFlags> zIGNORE_ENTITY_ERRORS = ActivateFlags.IGNORE_ENTITY_ERRORS;
// protected static final Set<ActivateFlags> zIGNORE_ATTRIB_ERRORS = ActivateFlags.IGNORE_ATTRIB_ERRORS;
// protected static final Set<ActivateFlags> zIGNORE_ERRORS = ActivateFlags.IGNORE_ERRORS;
// protected static final Set<ActivateFlags> zSINGLE_FOR_UPDATE = ActivateFlags.SINGLE;

   public static final int zICON_ERROR = 0;
   public static final int zICON_QUESTION = 0;
   public static final int zEXPANDALL = 0;

   public static final int zSINGLE                 = 0;          // 0x00000000L
   public static final int zAPPLICATION            = 4;          // 0x00000004L
   public static final int zMULTIPLE               = 256;        // 0x00000100L
   public static final int zNOI_OKAY               = 512;        // 0x00000200L
   public static final int zNOENQUEUE              = 1024;       // 0x00000400L
   public static final int zREADONLY               = 2048;       // 0x00000800L
   public static final int zIGNORE_ENTITY_ERRORS   = 4096;       // 0x00001000L
   public static final int zIGNORE_ATTRIB_ERRORS   = 8192;       // 0x00002000L
   public static final int zIGNORE_ERRORS          = 12288;      // 0x00003000L
   public static final int zACTIVATE_OPTION        = 16384;      // 0x00004000L
   public static final int zACTIVATE_OPTION2       = 32768;      // 0x00008000L
   public static final int zACTIVATE_NOCONSTRAINTS = 16777216;   // 0x01000000L
   public static final int zNEW_OBJECT_NAME        = 33554432;   // 0x02000000L
   public static final int zSINGLE_FOR_UPDATE      = 67108864;   // 0x04000000L
   public static final int zACTIVATE_CONTINUE      = 134217728;  // 0x08000000L
   public static final int zACTIVATE_LOCAL         = 1073741824; // 0x40000000L
   public static final int zACTIVATE_SYSTEM        = 536870912;  // 0x20000000L
   public static final int zACTIVATE_ROOTONLY      = 268435456;  // 0x10000000L
   public static final int zACTIVATE_ROOTONLY_MULTIPLE = 268435712;  // 0x10000000L + 0x00000100L
   public static final int zIGNORE_JOINS           = 1048576;    // 0x00100000L
   public static final int zASYNCHRONOUS           = 2097152;    // 0x00200000L

   /**
    * A map to convert C-style activate flags (int) to JOE-style (enum).
    */
   private static final Map<Integer, EnumSet<ActivateFlags>> ACTIVATE_CONTROL =
       Collections.unmodifiableMap( new HashMap<Integer, EnumSet<ActivateFlags>>() {
       private static final long serialVersionUID = 2L;
   {
       put( zSINGLE, ActivateFlags.SINGLE );
       put( zMULTIPLE, ActivateFlags.MULTIPLE );
       put( zACTIVATE_ROOTONLY_MULTIPLE, ActivateFlags.ROOT_ONLY_MULTIPLE );
       put( zACTIVATE_ROOTONLY, ActivateFlags.ROOT_ONLY );
       put( zIGNORE_ENTITY_ERRORS, ActivateFlags.IGNORE_ENTITY_ERRORS );
       put( zIGNORE_ATTRIB_ERRORS, ActivateFlags.IGNORE_ATTRIB_ERRORS );
       put( zIGNORE_ERRORS, ActivateFlags.IGNORE_ERRORS );
       put( zSINGLE_FOR_UPDATE, ActivateFlags.SINGLE );
       put( zIGNORE_JOINS, ActivateFlags.IGNORE_JOINS );
       put( zASYNCHRONOUS, ActivateFlags.ASYNCHRONOUS );

       // Ignore zAPPLICATION whan paired with SINGLE
       put( zSINGLE + zAPPLICATION, ActivateFlags.SINGLE );
   }} );

   /**
    * A map to convert C-style incremental flags (int) to JOE-style (enum).
    */

   private static final Map<Integer, EnumSet<IncrementalEntityFlags>> SET_INCREMENTAL =
       Collections.unmodifiableMap( new HashMap<Integer, EnumSet<IncrementalEntityFlags>>() {
       private static final long serialVersionUID = 3L;
   {
       put( zSET_INCR_CREATED, IncrementalEntityFlags.CREATED );
       put( zSET_INCR_UPDATED, IncrementalEntityFlags.UPDATED );
       put( zSET_INCR_DELETED, IncrementalEntityFlags.DELETED );
       put( zSET_INCR_EXCLUDED, IncrementalEntityFlags.EXCLUDED );
       put( zSET_INCR_INCLUDED, IncrementalEntityFlags.INCLUDED );
       put( zSET_INCR_HIDDEN, IncrementalEntityFlags.HIDDEN  );
   }} );

   public static final String zNEW_LINE = "\n";
   public static final String NEW_LINE = "\n";
   public static final String zQUOTES = "\"";
   public static final String QUOTES = "\"";
   public static final String zTAB = "\t";

   public static final int zQUAL_EQUAL = 16;
   public static final int zQUAL_GREATER_THAN = 32;
   public static final int zQUAL_LESS_THAN = 64;
   public static final int zQUAL_NOT = 128;
   public static final int zQUAL_OPERATOR = 240;
   public static final int zQUAL_INTEGER = 256;
   public static final int zQUAL_DECIMAL = 512;
   public static final int zQUAL_STRING = 1024;
   public static final int zQUAL_DATETIME = 2048;
   public static final int zQUAL_ENTITYATTR = 4096;
   public static final int zQUAL_ENTITYCSR = 2097152;  // 0x00200000L
   public static final int zQUAL_ENTITYKEY = 8388608;  // 0x00800000L
   public static final int zQUAL_ATTR_NULL = 32768;  // 0x00008000L
   public static final int zQUAL_ATTR_HIDDEN = 16777216;  // 0x01000000L
   public static final int zQUAL_OPERAND = 40704;  // 0x00009F00L
   public static final int zQUAL_SELECTED = 8192;  // 0x00002000L
   public static final int zQUAL_UNSELECTED = 16384;  // 0x00004000L
   public static final int zTEST_CSR_RESULT = 65536;  // 0x00010000L
   public static final int zRECURS = 131072;  // 0x00020000L
   public static final int zQUAL_ZKEYS = 262144;  // 0x00040000L
   public static final int zQUAL_SCOPE_OI = 524288;  // 0x00080000L
   public static final int zSPARENT_KEEPCHILDREN = 1048576;  // 0x00100000L
   public static final int zIGNORE_ERROR_358 = 4194304;  // 0x00400000L
   public static final int zLBITS = -1;  // 0xFFFFFFFFL

   public static final int zUSE_DEFAULT_CONTEXT = 8;
   public static final int zACCEPT_NULL_ENTITY  = 1;
   public static final int zVALIDATE = 4;
   public static final int zIGNORE_KEYS = 16;
   public static final int zUSE_TASK = 4096;

   public static final int COREFILE_READ = 128;              // 0x0080;
   public static final int COREFILE_WRITE = 64;              // 0x0040;
   public static final int COREFILE_UPDATE = 192;            // (COREFILE_READ | COREFILE_WRITE);
   public static final int COREFILE_CREATE = 32;             // 0x0020;
   public static final int COREFILE_DELETE = 8;              // 0x0008;
   public static final int COREFILE_RENAME = 16;             // 0x0010;
   public static final int COREFILE_EXIST = 4;               // 0x0004;
   public static final int COREFILE_EXISTS = 4;              // 0x0004;
   public static final int COREFILE_WRITELINE_BUFFERED = 1;  // 0x0001;
   public static final int COREFILE_APPEND = 448;            // (0x0100 | COREFILE_UPDATE);
   public static final int COREFILE_BACKUP = 4096;           // 0x1000;
   public static final int COREFILE_CREATE_NEW = 562;        // (0x0200 | COREFILE_CREATE);

   public static final int zREPORT_PRINTREMOTE = 131072;     // 0x00020000

   public static final int zAPPL_NAME = 0;
   public static final int zAPPL_DIR_LIB = 1;
   public static final int zAPPL_DIR_OBJECT = 2;
   public static final int zAPPL_DIR_LOCAL = 3;
   public static final int zAPPL_DIR_SHARED = 4;
   public static final int zAPPL_DIR_QLPLR = 5;
   public static final int zAPPL_DIR_SOURCE = 6;

   protected static final int zBUTTONS_YESNO = 1;
   protected static final int zBUTTONS_YESNOCANCEL = 2;
   protected static final int zBUTTONS_OK = 3;
   protected static final int zBUTTONS_OKCANCEL = 4;
   protected static final int zBUTTONS_RETRYCANCEL = 5;
   protected static final int zBUTTONS_ABORTRETRYIGNORE = 6;

   protected static final int zRESPONSE_YES = 1;
   protected static final int zRESPONSE_NO = 2;
   protected static final int zRESPONSE_CANCEL = 3;
   protected static final int zRESPONSE_OK = 4;
   protected static final int zRESPONSE_ABORT = 5;
   protected static final int zRESPONSE_RETRY = 6;
   protected static final int zRESPONSE_IGNORE = 7;

   public static final int zCALL_ERROR = -16;
   public static final int zMSGQ_OBJECT_CONSTRAINT_INFORMATION = 50;
   public static final int zMSGQ_OBJECT_CONSTRAINT_WARNING = 100;
   public static final int zMSGQ_DOMAIN_ERROR = 200;
   public static final int zMSGQ_OBJECT_CONSTRAINT_ERROR = 300;
   public static final int zMSGQ_REQUIRED_DATA_ITEM_ERROR = 400;
   public static final int zMSGQ_MODAL_ERROR = 500;
   public static final int zMSGQ_SYSTEM_ERROR = 1000;

   protected static final int zMSGQ_PROCESS_ACCEPT = 1;
   protected static final int zMSGQ_PROCESS_COMMIT = 2;
   protected static final int zMSGQ_PROCESS_DOMAIN = 3;

   protected static final int zCONSTRAINT_VIOLATION = -12;
   protected static final int zCALL_MIN_INFORC = -10;
   protected static final int zDB_UNAVAIL = -2;
   protected static final int zDB_DEADLOCK = -3;
   protected static final int zDUPLICATE_ROOT = -4;
   protected static final int zOPTIMISTIC_LOCK_ERROR = -5;
   protected static final int zLOCK_ERROR = -5;
   protected static final int zNETWORK_ERROR = -6;
   protected static final int zMEMORY_LIMIT = -7;

   protected static final int  zVAR_SET = 0;
   protected static final int  zVAR_NULL = -1; // Indicates that the attribute value is a null value
   protected static final int  zENTITY_NULL = -2;

   protected static final int zCOREMEM_ALLOC = 1;  // just to get things going
   protected static final int MB_OK = 1;  // just to get things going
   protected static final int REG_SZ = 1;  // just to get things going
   protected static final int REG_EXPAND_SZ = 2;  // just to get things going

   protected static final int zDT_YEAR   = 0;
   protected static final int zDT_MONTH  = 1;
   protected static final int zDT_DAY    = 2;
   protected static final int zDT_HOUR   = 3;
   protected static final int zDT_MINUTE = 4;
   protected static final int zDT_SECOND = 5;

   protected static final int qMAXRECEIVEISNEGATIVE = -2;
   protected static final int qMAXCOPYISNEGATIVE = -3;
   protected static final int qMAXRECEIVEEXCEEDSTARGETLEN = -4;
   protected static final int qINVALIDPARAMETER = -5;

   // defines for LPLR Types
   public static final int zREFER_HDR_META     = 2000;
   public static final int zREFER_SRC_META     = 2001;
   public static final int zREFER_GO_META      = 2002;
   public static final int zREFER_DOMAIN_META  = 2003;
   public static final int zREFER_ERD_META     = 2004;
   public static final int zREFER_SA_META      = 2005;
   public static final int zREFER_DTE_META     = 2006;
   public static final int zREFER_LOD_META     = 2007;
   public static final int zREFER_POD_META     = 2008;
   public static final int zREFER_VOR_META     = 2009;
   public static final int zREFER_PENV_META    = 2010;
   public static final int zREFER_DIALOG_META  = 2011;
   public static final int zREFER_UIS_META     = 2012;
   public static final int zREFER_DOMAINGRP_META = 2013;
   public static final int zREFER_GOPGRP_META  = 2014;
   public static final int zREFER_REPORT_META  = 2015;
   public static final int zREFER_XSLT_META    = 2016;
   public static final int zREFER_MAX_META     = 2016;

   public static final int zSOURCE_HDR_META     = 0;
   public static final int zSOURCE_SRC_META     = 1;
   public static final int zSOURCE_GO_META      = 2;
   public static final int zSOURCE_DOMAIN_META  = 3;
   public static final int zSOURCE_ERD_META     = 4;
   public static final int zSOURCE_SA_META      = 5;
   public static final int zSOURCE_DTE_META     = 6;
   public static final int zSOURCE_LOD_META     = 7;
   public static final int zSOURCE_POD_META     = 8;
   public static final int zSOURCE_VOR_META     = 9;
   public static final int zSOURCE_PENV_META    = 10;
   public static final int zSOURCE_DIALOG_META  = 11;
   public static final int zSOURCE_UIS_META     = 12;
   public static final int zSOURCE_DOMAINGRP_META = 13;
   public static final int zSOURCE_GOPGRP_META  = 14;
   public static final int zSOURCE_REPORT_META  = 15;
   public static final int zSOURCE_XSLT_META    = 16;
   public static final int zSOURCE_MAX_META     = 16;

   public static final int   zWAB_DeterminedInDialogOperation     = 0;
   public static final int   zWAB_StayOnWindow                    = 1;          // (PREVENT ACTION)
   public static final int   zWAB_StayOnWindowWithRefresh         = 2;
   public static final int   zWAB_StayOnWindowWebRefresh          = 3;

   public static final int   zWAB_StartOrFocusModelessDialog      = 30;
   public static final int   zWAB_StartDialogTask                 = 31;
   public static final int   zWAB_StartModelessWindow             = 32;
   public static final int   zWAB_StartOrFocusModelessWindow      = 38;
   public static final int   zWAB_StartModalWindow                = 36;
   public static final int   zWAB_StartModelessSubwindow          = 33;
   public static final int   zWAB_StartOrFocusModelessSubwindow   = 39;
   public static final int   zWAB_StartModalSubwindow             = 34;
   public static final int   zWAB_StartOrFocusModelessApp         = 37;
   public static final int   zWAB_StartNewApp                     = 35;

   public static final int   zWAB_ReplaceDialogWithDialog         = 51;
   public static final int   zWAB_ReplaceWindowWithModelessWindow = 52;
   public static final int   zWAB_ReplaceWindowWithModalWindow    = 53;
   public static final int   zWAB_ReplaceCurrentAppWithApp        = 54;

   public static final int   zWAB_SwitchToAction                  = -1;
   public static final int   zWAB_ReturnToParent                  = 71;
   public static final int   zWAB_ReturnToParentWithRefresh       = 72;
   public static final int   zWAB_ReturnToTopWindow               = 73;
   public static final int   zWAB_ReturnToTopWindowWithRefresh    = 74;
   public static final int   zWAB_StartTopWindow                  = 75;
   public static final int   zWAB_ResetTopWindow                  = 76;

   public static final int   zWAB_ExitDialogTask                  = 99;

   public static final int   zWAB_SuspendToDebugger               = 201;
   public static final int   zWAB_ResumeFromDebugger              = 202;

   public static final int   zWAB_TerminateActionForError         = 9999;
   public static final int   zWAB_ProcessImmediateAction          = 268435456;                         // 0x10000000L;
   public static final int   zWAB_NoDriverMessageOnError          = 536870912;                         // 0x20000000L;
   public static final int   zWAB_ProcessImmediateReturn          = 1073741824;                        // 0x40000000L;

   protected static final String[] g_Actions = {
           "DeterminedInDialogOperation", // 0
           "(1)StayOnWindow(NoRefresh)", // 1
           "(2)StayOnWindowWithRefresh", // 2

           "", "", "", "", "", "", "", "", "", "", // 3-12
           "", "", "", "", "", "", "", "", "", "", // 13-22
           "", "", "", "", "", "", "", // 23-29

           "(30)StartOrFocusModelessDialog", // 30
           "(31)StartDialogTask", // 31
           "(32)StartModelessWindow", // 32
           "(33)StartModelessSubwindow", // 33
           "(34)StartModalSubwindow", // 34
           "(35)StartNewApp", // 35
           "(36)StartModalWindow", // 36
           "(37)StartOrFocusModelessApp", // 37
           "(38)StartOrFocusModelessWindow", // 38
           "(39)StartOrFocusModelessSubwindow", // 39

           "(40)StartPopupMenu", // 40
           "(41)StartBrowserHTML_Page", // 41
           "(42)StartEmailClient", // 42
           "(43)StartEmailClientBCC", // 43
           "(44)StartEmailClientCC", // 44
           "(45)LinkToHTML_Address", // 45
           "(46)ProcessDownloadFile", // 46
           "(47)StartBrowserPDF_Page", //  47
           "", "", "", // 48-50

           "(51)ReplaceDialogWithDialog", // 51
           "(52)ReplaceWindowWithModelessWindow", // 52
           "(53)ReplaceWindowWithModalWindow", // 53
           "(54)ReplaceCurrentAppWithApp", // 54
           "(55)ReCAPTCHA Replace Window", // 55
           "", "", "", "", // 56-59

           "(60)PrintWindow", //  60
           "(61)StartSplitWindow", // 61
           "(62)ReplaceSplitWindow", // 62
           "(63)DeleteSplitWindow", // 63
           "", "", "", "", "", "", "", // 64-70

           "(71)ReturnToParent", // 71
           "(72)ReturnToParentWithRefresh", // 72
           "(73)ReturnToTopWindow", // 73
           "(74)ReturnToTopWindowWithRefresh", // 74
           "(75)StartTopWindow", // 75
           "(76)ReplaceTopWindow", // 76
           "", // 77
           "(78)StartModalWebPopup", // 78
           "(79)StartModelessWebPopup", // 79

           "", "", "", "", // 80-83
           "(84)ReturnFromWebPopupNoRefresh", // 84
           "(85)ReturnFromWebPopupWithRefresh", // 85

           "", "", "", "", "", // 86-90
           "", "", "", "", "", "", "", "", // 91-98

           "(99)ExitDialogTask", // 99
           "???", //  100
   };

   public static final String MSGQ_OBJECT_NAME = "__MSGQ";
   public static final String WEB_SESSION_VIEW_NAME = "_KZXMLPGO";
   public static final String MESSAGE_OBJECT_NAME = "KZMSGQOO";  // a version of this object exists in each LPLR

   //
   // Fields should not be initialized with anything but static values (preferably null).
   // VmlOperation will be instantiated many times and initialization must be cheap.
   //

   static final Map<Integer, String> BaseMsgTable = ImmutableMap.<Integer, String>builder()
      .put( 1, "KZOEE001 - Error communicating with client window" )
      .put( 2, "KZOEE002 - ZEIDON environment variable not set" )
      .put( 3, "KZOEE003 - Error starting Object Services" )
      .put( 4, "KZOEE004 - Error communicating with Object Services" )
      .put( 5, "KZOEE005 - Error communicating with Object Services" )
      .put( 6, "KZOEE006 - Internal error freeing Anchor Block" )
      .put( 7, "KZOEE007 - Internal Anchor block error, system restart recommended" )
      .put( 8, "KZOEE008 - Application definition file missing ZEIDON_BIN or ZEIDON_SYS" )
      .put( 9, "KZOEE009 - Error opening application definition file" )
      .put( 10, "KZOEE010 - Requested application not found" )
      .put( 11, "KZOEE011 - Error opening application domains file" )
      .put( 12, "KZOEE012 - Error allocating memory" )
      .put( 13, "KZOEE013 - Internal operation call error" )
      .put( 14, "KZOEE014 - Invalid suballoc memory free - ID=" )
      .put( 15, "KZOEE015 - Invalid suballoc memory free" )
      .put( 16, "KZOEE016 - Invalid Task" )
      .put( 17, "KZOEE017 - Task is Disabled" )
      .put( 18, "KZOEE018 - Error in Domain file, line: " )
      .put( 19, "KZOEE019 - Invalid GetTaskInfo request: " )
      .put( 20, "KZOEE020 - Invalid Operation call " )
      .put( 21, "KZOEE021 - Internal error, TaskOperation block has no Object/Entity/Attr" )
      .put( 22, "KZOEE022 - Error writing file" )
      .put( 23, "KZOEE023 - Invalid parameter, " )
      .put( 24, "KZOEE024 - Error opening file" )
      .put( 25, "KZOEE025 - Error in directory specification" )
      .put( 26, "KZOEE026 - Error creating directory" )

  // Errors in system services
      .put( 30, "KZOEE030 - Error loading library rc=" )
      .put( 31, "KZOEE031 - Error locating Operation rc=" )
      .put( 32, "KZOEE032 - Mutex name is invalid" )

  // beginning of application logic errors
  // Activate object errors
      .put( 50, "KZOEE050 - Error opening View Object file " )
      .put( 51, "KZOEE051 - Invalid View Object file header" )
      .put( 52, "KZOEE052 - Invalid Object file, Attrib w/o lth in defin prev to line " )
      .put( 53, "KZOEE053 - Invalid Entity level found on line " )
      .put( 54, "KZOEE054 - Invalid record found on line " )
      .put( 55, "KZOEE055 - Invalid file, LTH or TYPE before DOMAIN on line " )
      .put( 56, "KZOEE056 - Domain not found on line " )
      .put( 57, "KZOEE057 - Invalid file, LTH before type and DOMAIN on line " )
      .put( 58, "KZOEE058 - Invalid file, PERSIST after DOMAIN, TYPE or LTH on line " )
      .put( 59, "KZOEE059 - Invalid file, Invalid Attribute Token located on line " )
      .put( 60, "KZOEE060 - Invalid maximum root qualifier " )
      .put( 61, "KZOEE061 - Persistent Attribute mismatch on Token " )
      .put( 62, "KZOEE062 - Conflict between E/R Attribute Type and TE Field Type for: " )
      .put( 63, "KZOEE063 - Entity attrib RECURSIVE Y, but not structurally recursive " )
      .put( 64, "KZOEE064 - TYPE obsolete, use APDM_TOK " )
      .put( 65, "KZOEE065 - LTH only valid for Attribute w/type string line " )
      .put( 66, "KZOEE066 - Attribute length zero " )
      .put( 67, "KZOEE067 - Could not initialize the Core-MQ interface " )

  // Activate/Commit instance errors
      .put( 70, "KZOEE070 - Object instance is empty" )
      .put( 71, "KZOEE071 - Error opening instance file " )
      .put( 72, "KZOEE072 - Error reading instance file " )
      .put( 73, "KZOEE073 - Invalid instance file header" )
      .put( 74, "KZOEE074 - Invalid Entity name on line " )
      .put( 75, "KZOEE075 - Invalid Entity level on line " )
      .put( 76, "KZOEE076 - Object instance contains versioned entity instances" )
      .put( 77, "KZOEE077 - Invalid record size in binary object instance" )
      .put( 78, "KZOEE078 - Internal error, linked instance has no visible owner" )
      .put( 79, "KZOEE079 - Trying to commit a read-only view" )
      .put( 80, "KZOEE080 - Object Definition does not have a Database handler specified" )
      .put( 81, "KZOEE081 - Object Definition does not have a Genkey handler specified" )
      .put( 82, "KZOEE082 - Error starting Genkey handler" )
      .put( 83, "KZOEE083 - LOD does not have physical information.  Possibly the LOD was saved without DB information or the entities are work entities." )
      .put( 84, "KZOEE084 - Entity in Object Definition does not have Data Fields" )
      .put( 85, "KZOEE085 - Entity in Object Definition does not have Relationship Records" )
      .put( 86, "KZOEE086 - Trying to commit 0 views" )
      .put( 87, "KZOEE087 - Too many views in View array" )
      .put( 88, "KZOEE088 - Compressed OI is out of sync with LOD/XOD" )
   // .put( 89, "KZOEE089 - This error number may be re-used" )
      .put( 90, "KZOEE090 - Maximum number of entities in portable file exceeded: " )
      .put( 91, "KZOEE091 - Maximum number of entities in binary file exceeded" )
      .put( 92, "KZOEE092 - Couldn't re-activate OI for optimistic lock check" )
      .put( 93, "KZOEE093 - Optimistic locking error -- OI has changed since it was activated" )
      .put( 94, "KZOEE094 - Locking violation" )
      .put( 95, "KZOEE095 - A recursive child was found that matches a parent.  This will cause an infinite loop in the recursive subobject.  See Trace for more." )
      .put( 96, "KZOEE096 - Error retrieving OI from blob." )

  // View, entity, and attribute validation errors
      .put( 100, "KZOEE100 - Invalid View, view is a Subtask View" )
      .put( 101, "KZOEE101 - Invalid View" )
      .put( 102, "KZOEE102 - Invalid View, view is hidden" )
      .put( 103, "KZOEE103 - Invalid Entity name for View" )
      .put( 104, "KZOEE104 - Invalid Attribute name for LOD/Entity/Attribute" )
      .put( 105, "KZOEE105 - Invalid scoping Entity name for View" )
      .put( 106, "KZOEE106 - Rules violation " )
      .put( 107, "KZOEE107 - Invalid Subtask View" )
      .put( 108, "KZOEE108 - Attempt to drop Subtask View" )
      .put( 109, "KZOEE109 - Invalid View, view contains no instance" )
      .put( 110, "KZOEE110 - Invalid level for View name" )
      .put( 111, "KZOEE111 - Attempt to set subtask view with a view from another task" )
      .put( 112, "KZOEE112 - Attempt to set subtask view attached to an application" )
      .put( 113, "KZOEE113 - Invalid View, view contains an instance" )
      .put( 114, "KZOEE114 - Source and Target entities do not match" )
      .put( 115, "KZOEE115 - Attempt to move an instance under one of its children" )
      .put( 116, "KZOEE116 - Attempt to include an instance created under a versioned parent" )
      .put( 117, "KZOEE117 - Entity Instance Keys do not match" )
      .put( 118, "KZOEE118 - Internal Key error relinking versioned Subobject" )
      .put( 119, "KZOEE119 - Invalid View, View is Read Only" )
   // More at 450

  // Entity creation errors
      .put( 120, "KZOEE120 - Invalid position parameter" )
      .put( 121, "KZOEE121 - Attempt to insert twin to root of Instance" )
      .put( 122, "KZOEE122 - Attempt to create an Entity without a parent" )
      .put( 124, "KZOEE124 - Entity parent is included in another path containing same entity type" )
      .put( 125, "KZOEE125 - Target and Source Entities are not the same E/R Entity" )
      .put( 126, "KZOEE126 - Target or Source Entity is not Includeable" )
      .put( 127, "KZOEE127 - Source & target do not match on both Entity & Relationship." )
      .put( 128, "KZOEE128 - Target Entity cardinality max not 1 for inverted Subobject" )
      .put( 129, "KZOEE129 - Trying to create a relationship (via Include, possibly as part of a spawn ) that already exists.  See trace for more." )
      .put( 131, "KZOEE131 - Attempt to include Subobject from another Application Task" )
      .put( 132, "KZOEE132 - Error establishing cursor for Subobject include" )
      .put( 134, "KZOEE134 - Attempt to version an Entity Instance versioned via a differen, path" )
      .put( 135, "KZOEE135 - Entity Instance not versioned" )
      .put( 136, "KZOEE136 - Entity Instance already versioned" )
      .put( 137, "KZOEE137 - Entity Instance descendent versioned" )
      .put( 138, "KZOEE138 - Attempt to include target entity with recursive behavior" )
      .put( 139, "KZOEE139 - Error establishing cursor for CreateEntity spawn" )
      .put( 140, "KZOEE140 - Target and source Object instance are the same instance" )
      .put( 141, "KZOEE141 - Attempt to include a subobject whose root is a Temporal Entity" )

  // *More* view, entity, and attribute validation errors
      .put( 190, "KZOEE190 - Error opening temporary file" )
      .put( 191, "KZOEE191 - Attribute must be a Blob or String for this operation." )

  // Attribute processing errors
      .put( 231, "KZOEE231 - Decimal attribute overflow (garbage )" )
      .put( 232, "KZOEE232 - Operation indicates use default context, none found" )
      .put( 233, "KZOEE233 - Could not locate derived library/operation" )
      .put( 234, "KZOEE234 - Attempt to add integer or decimal to invalid attribute type" )
      .put( 235, "KZOEE235 - Integer overflow" )
      .put( 236, "KZOEE236 - Integer underflow" )
      .put( 237, "KZOEE237 - Attempt to update a non-updateable attribute" )
      .put( 238, "KZOEE238 - Attempt to update a non-updateable persistent attribute" )
      .put( 239, "KZOEE239 - Invalid Operation for attribute type" )
      .put( 240, "KZOEE240 - Binary large object (Blob ) exceeds passed length" )
      .put( 241, "KZOEE241 - Invalid Variable Type " )
      .put( 242, "KZOEE242 - Invalid Domain Entry Type" )
      .put( 243, "KZOEE243 - Context invalid for Domain" )
      .put( 244, "KZOEE244 - Attribute has no Domain" )
      .put( 245, "KZOEE245 - Null string not allowed for a required attribute" )
      .put( 246, "KZOEE246 - Invalid Attribute type " )
      .put( 247, "KZOEE247 - Blob Attribute does not match Target Entity Type" )
      .put( 248, "KZOEE248 - Required attribute is null" )

  // Cursor errors
      .put( 250, "KZOEE250 - Object instance is empty" )

      .put( 252, "KZOEE252 - Root of view has been deleted" )
      .put( 253, "KZOEE253 - Entity cursor is NULL" )
      .put( 254, "KZOEE254 - Entity cursor is undefined" )
      .put( 255, "KZOEE255 - Attempt to update a previous entity version" )
      .put( 256, "KZOEE256 - Entity is root of view" )
      .put( 257, "KZOEE257 - Version mismatch between scoping and target Entities" )
      .put( 258, "KZOEE258 - Scoping Entity cursor is undefined" )
      .put( 259, "KZOEE259 - Scoping Entity cursor is NULL" )
      .put( 263, "KZOEE263 - Input Qualifier cannot be converted to internal attribute data type" )
      .put( 264, "KZOEE264 - Invalid Operation for Hierarchical processing" )
      .put( 265, "KZOEE265 - Invalid Operation for non-Hierarchical processing" )
      .put( 266, "KZOEE266 - Invalid Operation for Hierarchical cursor position" )
      .put( 267, "KZOEE267 - Hierarchical cursor is undefined" )
      .put( 268, "KZOEE268 - Source and target entity types do not match" )
      .put( 269, "KZOEE269 - Invalid Select Set ID, ID=" )

  // View creation errors
      .put( 270, "KZOEE270 - View name is already in use" )
      .put( 271, "KZOEE271 - Subobject Entity is root of View Object Definition" )
      .put( 272, "KZOEE272 - Subobject Parent Cursor is NULL" )
      .put( 273, "KZOEE273 - Parent Entity instance for View deleted" )
      .put( 274, "KZOEE274 - Target and Source views are different View Object types" )

  // Meta Inquiry errors

      .put( 300, "KZOEE300 - Problems finding a parent " )
      .put( 301, "KZOEE301 - Return buffer not large enough to contain concatenated keys" )
      .put( 302, "KZOEE302 - Invalid option" )

  // Message Object errors

      .put( 330, "KZOEE330 - Message Object Definition not loaded for Application" )

  // Domain processing errors (Table Handler )
      .put( 350, "KZOEE350 - Context Not Valid for Domain" )
      .put( 351, "KZOEE351 - Invalid Input Data Type" )
      .put( 352, "KZOEE352 - Text String exceeds attribute length " )
      .put( 353, "KZOEE353 - Attribute Type invalid for this Domain" )
      .put( 354, "KZOEE354 - Invalid Domain Entry Type " )
      .put( 355, "KZOEE355 - Table_Handler invalid for this Domain Type " )
      .put( 356, "KZOEE356 - Domain has an unnamed Context " )
      .put( 357, "KZOEE357 - Invalid Input Data" )
      .put( 358, "KZOEE358 - Value not in Context for Domain " )
      .put( 359, "KZOEE359 - Invalid Input Data Type for Domain Entry Type" )
      .put( 360, "KZOEE360 - Missing ending delimiter " )
      .put( 361, "KZOEE361 - Invalid data Format " )
      .put( 362, "KZOEE362 - Variable Type not allowed for this Domain Type " )
      .put( 363, "KZOEE363 - Context Edit string is invalid " )
      .put( 364, "KZOEE364 - Picture length exceeds input length " )

  // Network Errors
      .put( 400, "KZOEE400 - Error transmitting OI -- some lines lost." )
      .put( 401, "KZOEE401 - Invalid User Name." )
      .put( 402, "KZOEE402 - Invalid Password." )
      .put( 403, "KZOEE403 - Application not supported by Security Object." )
      .put( 404, "KZOEE404 - Can't find UserGroup." )
      .put( 405, "KZOEE405 - UserGroup doesn't have authority for operation." )

      .put( 450, "KZOEE450 - Name for view is too long" )
      .put( 451, "KZOEE451 - Include source task does not match target task" )

      .put( 9999, "" )
      .build( );

/*
  // General Domain errors
  #if 0
  struct MsgTableStruct
   DomainMsgTable [] =
   {
     {1,"TZDME001 - Invalid Input Data Type"},
     {2,"TZDME002 - Text String exceeds attribute length "},
     {3,"TZDME003 - Attribute Type invalid for this Domain"},
     {4,"TZDME004 - Invalid Domain Entry Type "},
     {5,"TZDME005 - Table_Handler invalid for this Domain "},
     {6,"TZDME006 - Integer overflow"},
     {7,"TZDME007 - Integer underflow"},
     {8,"TZDME008 - Could not find context for Domain "},
     {9,"TZDME009 - Context edit string is invalid for Domain "},
    {10,"TZDME010 - DateTime input string invalid "},
    {11,"TZDME011 - Error storing value in record "},
    {12,"TZDME012 - Context Required when cType is zTYPE_INTEGER "},
    {13,"TZDME013 - Context/cType Combination is invalid "},
    {14,"TZDME014 - Context is for retrieval only "},
    {15,"TZDME015 - Context only used for arithmetic operations "},
    {16,"TZDME016 - Input invalid for context "},
    {17,"TZDME017 - Context Required when cType is zTYPE_DECIMAL "},
    {18,"TZDME018 - Context edit string is null "},
    {19,"TZDME019 - International number formatting is not available "},
    {20,"TZDME020 - Invalid decimal string "},
    {21,"TZDME021 - Return area not large enough for formatted string "},
    {22,"TZDME022 - AlphaNumeric and '_' are the only chars allowed "},
    {99,"TZDME099 - Forgot this one! "},
    {9999,""}
   };
  #endif
*/

   protected Task task;

   // Used by hierarchical cursors.
   private Iterator<? extends EntityInstance> hierInstanceIterator;
   private String                             hierInstanceEntityName;

   /**
    * This attempts to find a task qualification object from a list of unknown objects.
    *
    * @param objects
    * @return
    */
   public static TaskQualification findTaskQual( Object...objects)
   {
       for ( Object o : objects )
       {
         if ( o instanceof TaskQualification )  // Null values return false.
         {
            return (TaskQualification) o;
         }
      }

      return null;
   }

   public VmlOperation( TaskQualification taskQual )
   {
       super( );
       if ( taskQual != null )
           setTaskQual( taskQual );
   }

   protected void setTaskQual( TaskQualification taskQual )
   {
      if ( taskQual != null )
          this.task = taskQual.getTask();
   }

   public static void SetZeidonSessionAttribute( HttpSession session, TaskQualification qual, String strCallingJSP, String strActionToProcess )
   {
      Task task = qual.getTask();
      if ( session != null )
      {
         session.setAttribute( "ZeidonAction", strActionToProcess );
         task.log().debug( "ZeidonAction: " + strCallingJSP + "." + strActionToProcess ); // remove for deployment
      }
      else
      {
         task.log().debug( "ZeidonOperation: " + strActionToProcess + " called from " + strCallingJSP ); // remove for deployment
      }
   }

   // Returns the underlying view of zVIEW if view is an instance of zVIEW.
   // This is used in a few cases where we need to have the View instead of zVIEW.
   //
   // @param view
   // @return
   //
   private View getViewFromzVIEW( View view )
   {
      int count = 0;
      while ( view instanceof zVIEW )
      {
         if ( ++count == 1000 )
         {
            throw new ZeidonException( "Unreasonable number of nested zVIEWs" );
         }

         view = ((zVIEW) view).getView( );
      }

      return view;
   }

   protected static View getView( View v )
   {
      if ( v != null )
      {
         if ( v instanceof zVIEW )
         {
            v = ((zVIEW) v).getView( );
         }
      }

      return v;
   }

   public static boolean isValid( View v )
   {
       return getView( v ) != null;
   }

   public static void SfSetUserIdForTask( TaskQualification qual, String userId )
   {
      qual.getTask( ).setUserId( userId );
   }

   public static String SfGetUserIdForTask( TaskQualification qual )
   {
      return qual.getTask( ).getUserId( );
   }

   public static void SfGetUserIdForTask( TaskQualification qual, StringBuilder sbUserId )
   {
      sbUserId.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbUserId.append( qual.getTask( ).getUserId( ) );
   }

   protected void SfSetApplicationTask( TaskQualification qual, int i )
   {
      // TODO Auto-generated method stub
   }

   protected int SfGetApplicationForSubtask( StringBuilder sbApp, View view )
   {
      Application app = view.getApplication( );
      sbApp.setLength( 0 );
      sbApp.append( app.getName( ) );
      return sbApp.length( );
   }

   // TODO:  Do we need both of the following getMessageObject ops?  dks 2011.04.15
   private static View getMessageObject( TaskQualification taskQual )
   {
      View vMsgQ = taskQual.getViewByName( MSGQ_OBJECT_NAME );
      if ( vMsgQ == null )
      {
         vMsgQ = taskQual.activateEmptyObjectInstance( MESSAGE_OBJECT_NAME );
         vMsgQ.cursor( "Task" ).createEntity().setAttribute( "Id", taskQual.getTask().getTaskId() );
         vMsgQ.setName( MSGQ_OBJECT_NAME );
      }

      return vMsgQ;
   }

   public static View getMessageObject( Task task )
   {
      View vMsgQ = task.getViewByName( MSGQ_OBJECT_NAME );
      if ( vMsgQ == null )
      {
         vMsgQ = task.activateEmptyObjectInstance( MESSAGE_OBJECT_NAME );
         vMsgQ.cursor( "Task" ).createEntity().setAttribute( "Id", task.getTask().getTaskId() );
         vMsgQ.setName( MSGQ_OBJECT_NAME );
      }

      return vMsgQ;
   }

   /**
    * Resets the message object.
    *
    * @param taskQual
    */
   public static void resetMessageObject( TaskQualification taskQual )
   {
      View vMsgQ = taskQual.getViewByName( MSGQ_OBJECT_NAME );
      if ( vMsgQ != null )
      {
         vMsgQ.drop();
      }
   }

   public static void resetMessageObject( Task task )
   {
      View vMsgQ = task.getViewByName( MSGQ_OBJECT_NAME );
      if ( vMsgQ != null )
      {
         vMsgQ.drop();
      }
   }

   /**
    * Looks for the message object and if it finds one throws an ObjectConstraintException.
    * Called from the JOE to support legacy VML applications.
    *
    * @param task
    * @param rc
    */
   public static void throwMessageObject( View view, int rc ) throws ObjectConstraintException
   {
      View vMsgQ = getMessageObject( view.getTask( ) );
      StringBuilder sb = new StringBuilder();
      for ( EntityInstance ei : vMsgQ.cursor( "WebMsg" ).eachEntity() )
      {
         sb.append( ei.getStringFromAttribute( "ErrorMsg" ) );
         sb.append( "\n" );
      }

      throw new ObjectConstraintException( view, rc, sb.toString() );
   }

   /**
    * Adds a message to the message object for the current task.
    *
    * @param taskQual
    * @param msgId
    * @param msgTitle
    * @param msgText
    * @param msgType
    * @param beep
    */
   public static final int  MessageSend( TaskQualification taskQual,
                                         String            msgId,
                                         String            msgTitle,
                                         String            msgText,
                                         int               msgType,
                                         int               beep )
   {
      View vMsgQ = getMessageObject( taskQual );
      vMsgQ.cursor( "WebMsg" ).createEntity()
                              .setAttribute( "Id", msgId )
                              .setAttribute( "Title", msgTitle )
                              .setAttribute( "ErrorMsg", msgText )
                              .setAttribute( "Type", msgType )
                              .setAttribute( "Beep", beep );
      return 0;
   }

   public static final int MessageSend( View view, String msgId, String msgTitle, StringBuilder sbMsgText, int msgType, int beep )
   {
      return MessageSend( view, msgId, msgTitle, sbMsgText.toString( ), msgType, beep );
   }

   public static final int MessageSend( View view, int msgId, String msgTitle, String msgText, int msgType, int beep )
   {
      return MessageSend( view, "", msgTitle, msgText, msgType, beep );
   }

   public static final int MessagePrompt( View view, String msgId, String msgTitle, String msgText,
                                          int beep, int buttons, int defaultButton, int icon )
   {
      return MessageSend( view, msgId, msgTitle, msgText, 0, beep );
   }

   public static final int MessagePrompt( View view, int msgId, String msgTitle, String msgText,
                                          int beep, int buttons, int defaultButton, int icon )
   {
      return MessageSend( view, "", msgTitle, msgText, 0, beep );
   }

   public static final int MessagePromptForInput( View view, String msgTitle, String msgText,
                                                  String string3, int beep, StringBuilder sbPasswordEntered, int j )
   {
      // TODO Not doing the right thing!!!
      return MessageSend( view, "", msgTitle, msgText, 0, beep );
   }

   /**
    * This is public so it can be called from non-generated code if necessary.
    *
    * @param view
    * @param msgTitle
    * @param msgText
    * @param beep
    */
   public static final void SysMessageBox( View view, String msgTitle, String msgText, int beep )
   {
      JoeUtils.sysMessageBox( msgTitle, msgText );
   }

   public static final void SysMessageBox( String msgTitle, String msgText )
   {
      SysMessageBox( null, msgTitle, msgText, 0 );
   }

   protected static final int OperatorPrompt( View view, String msgTitle, String msgText, int beep, int buttons, int defaultButton, int icon )
   {
      // TODO finish up
      return MessageSend( view, "", msgTitle, msgText, 0, beep );
   }

   protected static final int OperatorPromptForInput( View view, String msgTitle, String msgText, int buttons, StringBuilder sbReturnString, int icon )
   {
      // TODO this does not do what is required!!!
      return MessageSend( view, "", msgTitle, msgText, 0, 0 );
   }

   protected static final int OperatorPromptForFile( View view, StringBuilder sbFileName, int i, String string, String string2, int j )
   {
      // TODO ... DKS???
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SysGetBaseMessage
   //
   // PROTOTYPE:
   //
   //     int  OPERATION
   //     SysGetBaseMessage( zPCHAR pchReturnString, long lMessageID, int nMaxLth )
   //
   // PURPOSE:  To return a base object engine message from the base
   //           message table.
   //
   // RETURNS: The length of the message returned
   //
   /////////////////////////////////////////////////////////////////////////////
   private int SysGetBaseMessage( StringBuilder sbReturnString, int lID, int nMaxLth )
   {
      sbReturnString.setLength( 0 );
      sbReturnString.append( BaseMsgTable.get( lID ) );
      if ( sbReturnString.length() > nMaxLth )
      {
         sbReturnString.setLength( nMaxLth );
      }

      return sbReturnString.length();
   }

   protected static final void IssueError( View view, int nSeverity, int nID, String stringMsg )
   {
      SysMessageBox( view, nSeverity < 16 ? "Application Logic Error" : "OE System Error", stringMsg, 0 );
   }

   protected static final void IssueError( View view, int nSeverity, double d, String stringMsg )
   {
      SysMessageBox( view, nSeverity < 16 ? "Application Logic Error" : "OE System Error", stringMsg, 0 );
   }

   public static final void IssueError( View view, String stringMsg )
   {
      SysMessageBox( view, "Logic Error", stringMsg, 0 );
   }

   //./ ADD NAME=IssueError
   // Source Module=kzoeeraa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      IssueError
   //
   //  PURPOSE:    To log an error situation
   //
   //  DESCRIPTION: Object Services, clients, and system software can
   //               call this operation to raise an error situation at
   //               any time during processing.
   //
   //               Severity: 16 - catastrophic error      (Object services)
   //                         12 - Application logic error (Object services)
   //                          8 - Application logic error (Application)
   //                          4 - Domain errors           (Application)
   //                          0 - Information logging msg
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   IssueError( zVIEW       lpView,
               int         nSeverity,
               int         nID,
               String      cpcMessage )
   {
      String    szTitle;

      if ( nSeverity < 16 )
      {
         szTitle = "Object Services - Application Logic Error";
      }
      else
      {
         szTitle = "OE System Error";
      }

      // Log the error with the subtask
      // TEMP For now, we just spit all errors out to the window!!! TEMP
      TraceLineS( "IssueError: ", cpcMessage );
      SysMessageBox( lpView, szTitle, cpcMessage, 1 );         // TEMP

      return 0;   // Error creating error object
   }

   //./ ADD NAME=fnIssueCoreError
   /////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:    fnIssueCoreError
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   fnIssueCoreError( TaskQualification task,
                     View         view,
                     int          nSeverity,
                     int          nID,
                     int          lLong,         // Additional info
                     String       cpcMessage1,   // Additional info
                     String       cpcMessage2 )  // Additional info
   {
      synchronized( lockMessage )
      {
         return IssueOE_Error( task, nSeverity, nID, zMSGQ_SYSTEM_ERROR,
                               lLong, cpcMessage1, cpcMessage2 );
      }
   }

   //./ ADD NAME=IssueOE_Error
   /////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:    IssueOE_Error
   //
   //  nSeverity    4 - Data Validation Error
   //               8 - Application Logic Error
   //              16 - OE System Error
   //              20 - Catastrophic Error (straight to SysMessageBox)
   //
   /////////////////////////////////////////////////////////////////////////////
   private int
   IssueOE_Error( TaskQualification  taskQual,
                  int         nSeverity,
                  int         nID,
                  int         lMsgType,
                  int         lLong,          // additional info
                  String      cpcMessage1,    // additional info
                  String      cpcMessage2 )   // additional info
   {
      Task      task;
      StringBuilder sbCoreMessage = new StringBuilder();
      String    cpcMsgId;
      zVIEW     zView = new zVIEW();
      String    pchTitle;
      LodDef    lpLodDef;

      task = taskQual.getTask();
      if ( task.isValid() )
      {
         for ( View v : task.getViewList() )
         {
            if ( isValid( v ) )
            {
               zView.setView( v );
               break;
            }
         }
      }

      // If the view is valid we'll pass it along, otherwise we will pass
      // the first SubtaskView for the task.
      if ( isValid( zView ) == false )
      {
         task = null;
         zView = null;
         lpLodDef = null;
      }

      SysGetBaseMessage( sbCoreMessage, nID, 256 );
      if ( sbCoreMessage.length() == 0 )
      {
         zstrcpy( sbCoreMessage, "Unknown Error Message (" );
         sbCoreMessage.append( zltoa( nID ) );
         zstrcat( sbCoreMessage, ") " );
      }

      if ( lLong != 0 )
      {
         sbCoreMessage.append( zltoa( lLong ) );
      }

      if ( cpcMessage1.isEmpty() == false )  // if any additional information
      {
         zstrcat( sbCoreMessage, ",\n\n " );
         zstrcat( sbCoreMessage, cpcMessage1 );
         if ( cpcMessage2.isEmpty() == false )
         {
            zstrcat( sbCoreMessage, ",\n" );
            zstrcat( sbCoreMessage, cpcMessage2 );
         }
      }

      TraceLineS( "IssueOE_Error : ", sbCoreMessage.toString() );

      // See if we can determine the view object resulting in the error.
      // But don't do it for EE071 or EE101 as it implies erroneous information.
      if ( isValid( zView ) && zView.getLodDef() != null && nID != 71 && nID != 101 )  // dks 2006.04.27
      {
          zstrcat( sbCoreMessage, ",\n\nView Object: " );
          zstrcat( sbCoreMessage, zView.getLodDef().getName() );
      }

      /**********
      // See if we can determine the operation.
      if ( lpTask != null && lpTask->bAudit && lpTask->nOperIdx > -1 )
      {
         String szOperationName[ 32 ];

         szOperationName[ 0 ] = 0;
         SysGetOperationMessage( szOperationName,
                              // lpTask->nOper[ 0 ], 31 );
                                 lpTask->nOper[ lpTask->nOperIdx ], 31 );
         if ( szOperationName[ 0 ] == 0 )
         {
            szOperationName[ 0 ] = '(';
         // zltoa( lpTask->nOper[ 0 ], &szOperationName[ 1 ] );
            zltoa( lpTask->nOper[ lpTask->nOperIdx ], &szOperationName[ 1 ] );
            zstrcat( szOperationName, ")" );
         }

         zstrcat( sbCoreMessage, ",\nOperation: " );
         zstrcat( sbCoreMessage, szOperationName );
         if ( lpTask->nOperIdx > 0 )
         {
            for ( int k = lpTask->nOperIdx - 1; k > -1 ; k-- )
            {
               zstrcat( sbCoreMessage, ", " );
               szOperationName[ 0 ] = 0;
               SysGetOperationMessage( szOperationName, lpTask->nOper[ k ], 31 );
               if ( szOperationName[ 0 ] == 0 )
               {
                  szOperationName[ 0 ] = '(';
                  zltoa( lpTask->nOper[ k ], &szOperationName[ 1 ] );
                  zstrcat( szOperationName, ") " );
               }

               zstrcat( sbCoreMessage, szOperationName );
            }
         }
      }
      */

      TraceLine( "IssueOE_Error:  %s  Severity: %d", sbCoreMessage, nSeverity );

      if ( nSeverity > 16 )
      {
         return IssueError( zView, nSeverity, nID, sbCoreMessage.toString() );
      }

/* #ifndef __DO_MSGOBJ__
      if ( lpTask == 0 )
         return( IssueError( zView, nSeverity, nID, szCoreMessage ) );
   #endif
*/
      if ( nSeverity == 16 )
      {
         pchTitle = "OE System Error";
      }
      else
      {
         pchTitle = "Object Services - Application Logic Error";
      }

      cpcMsgId = zltoa( nID ); // convert message id to a string
      return MessageSend( zView, cpcMsgId, "Object Services - Application Logic Error", sbCoreMessage,
                          lMsgType + nSeverity, 0 );
   }

   public static void CreateMessage( Task task, String controlTag, String errorReason, String mapValue )
   {
      // Create an entity in the message queue object.
      View vMsgQ = VmlOperation.getMessageObject( task );  // message queue
      vMsgQ.cursor( "WebMsg" ).createEntity( CursorPosition.NEXT );
      vMsgQ.cursor( "WebMsg" ).setAttribute( "ControlTag", controlTag );
      if ( errorReason.equals( "Invalid table domain value." ) )
      {
         errorReason = "The value '" + mapValue + "' is invalid.";
      }
      // The whole message wasn't being displayed in the jsp code if double quotes exist in the
      // error message. Take double quotes out.
      errorReason = errorReason.replace("\"", "\'");

      vMsgQ.cursor( "WebMsg" ).setAttribute( "ErrorMsg", errorReason );
      vMsgQ.cursor( "WebMsg" ).setAttribute( "ErrorMapValue", mapValue );
   }

   public static void ReplaceMessage( Task task, String controlTag, String errorReason, String mapValue, boolean evenWithError )
   {
      // Create an entity in the message queue object.
      View vMsgQ = VmlOperation.getMessageObject( task );  // message queue

      vMsgQ = vMsgQ.newView( );

      if ( vMsgQ.cursor( "Task" ).setFirst( "Id", task.getTask( ).getTaskId( ) ).isSet() &&
           vMsgQ.cursor( "WebMsg" ).setFirst( "ControlTag", controlTag ).isSet() )
      {
         String s = vMsgQ.cursor( "WebMsg" ).getStringFromAttribute( "ErrorMsg" );
         if ( s == null || s.isEmpty( ) || evenWithError )
         {
            vMsgQ.cursor( "WebMsg" ).setAttribute( "ErrorMsg", errorReason );
            vMsgQ.cursor( "WebMsg" ).setAttribute( "ErrorMapValue", mapValue );
         }
      }
      else
      {
         CreateMessage( task, controlTag, errorReason, mapValue );
      }
   }

   protected static final int ZeidonStringFind( StringBuilder sbTarget, int tgtIdx, String searchString )
   {
      return sbTarget.toString( ).lastIndexOf( searchString, tgtIdx - 1 );
   }

   protected static final int ZeidonStringFind( String tgtString, int tgtIdx, String searchString )
   {
      return tgtString.lastIndexOf( searchString, tgtIdx - 1 );
   }

   protected int SetNameForView( View view, String name, TaskQualification taskQual, int level )
   {
      Task t;
      Application a;
      View v;
      boolean autodrop;

      view = VmlOperation.getView( view );
      if ( taskQual instanceof zVIEW || taskQual instanceof View )
      {
         v = VmlOperation.getView( (View) taskQual );
         a = v.getApplication();
         t = v.getTask();
      }
      else
      {
         if ( taskQual == null )
         {
            v = view;
            a = v.getApplication();
            t = v.getTask();
         }
         else
         {
            v = null;
            a = taskQual.getApplication();
            t = taskQual.getTask();
         }
      }

      if ( (level & zNAME_AUTODROP) != 0 )
      {
         autodrop = true;
         level &= ~zNAME_AUTODROP;
      }
      else
      {
         autodrop = false;
      }

      switch ( level )
      {
         case zLEVEL_SUBTASK:
            if ( isValid( v ) == false )
            {
            // v = view;
               throw new ZeidonException( "Qualification view required for SetNameForView at the subtask level" );
            }

            if ( DriverApplication.isValidSubtaskView( v ) )
            {
               TraceLine( "SetNameForView (%d) Subtask (%d) level for Name: %s  Task: %s", view.getId(), v.getId(), name, t.getTaskId() );
               v.setNameForSubtask( name, view );
               break;
            }

            TraceLine( "SetNameForView (%d) Invalid Subtask (%d) view for Name: %s  Task: %s", view.getId(), v.getId(), name, t.getTaskId() );
            throw new ZeidonException( "Invalid subtask used in SetNameForView: %s", name );

         case zLEVEL_TASK:
            TraceLine( "SetNameForView (%d) Task level for Name: %s  Task: %s", view.getId(), name, t.getTaskId() );
            t.setNameForView( name, view );
            break;

         case zLEVEL_APPLICATION:
            TraceLine( "SetNameForView (%d) Application (%s) level for Name: %s  Task: %s", view.getId(), a.getName(), name, t.getTaskId() );
            a.setNameForView( name, view );
            break;

         case zLEVEL_SYSTEM:
            TraceLine( "SetNameForView (%d) System level for Name: %s  Task: %s", view.getId(), name, t.getSystemTask().getTaskId() );
            t.getSystemTask().setNameForView( name, view );
            break;

         default:
            throw new ZeidonException( "Invalid flag used in SetNameForView: %d", level );
      }

      return 0;
   }

   /**
    * This is defined so we can handle the (majority of) cases where the viewQual is generated as a 0.
    */
   protected int SetNameForView( View view, String name, int viewQual, int level )
   {
      return SetNameForView( view, name, null, level );
   }

   public int GetViewByName( zVIEW returnView, String name, TaskQualification taskQual, int level )
   {
      Task t;
      Application a;
      View v;

      // The view for KZXMPGO is named _KZXMLPGO.
      if ( name.equals( "KZXMLPGO") )
      {
         name = "_KZXMLPGO";
      }

      returnView.setView( null );  // initialize return view to null

      if ( taskQual == null )
      {
         throw new ZeidonException( "TaskQualification required for GetViewByName" );
      }
      else
      {
         if ( taskQual instanceof zVIEW || taskQual instanceof View )
         {
            v = VmlOperation.getView( (View) taskQual );
            a = v.getApplication();
            t = v.getTask();
         }
         else
         {
            v = null;
            a = taskQual.getApplication();
            t = taskQual.getTask();
         }
      }

      do  // purist's goto
      {
         // Check for valid name and scope.
         if ( name.length() == 0 )
         {
            throw new ZeidonException( "Empty name used in GetViewByName: %s", name );
         }

         if ( (level == zLEVEL_SUBTASK || level == zLEVEL_TASK ||
               level == zLEVEL_APPLICATION || level == zLEVEL_SYSTEM || level == zLEVEL_ANY) )
         {
            // If user wants a subtask view make sure subtask is OK.
            if ( level == zLEVEL_SUBTASK || level == zLEVEL_ANY  )
            {
               if ( DriverApplication.isValidSubtaskView( v ) )
               {
                  returnView.setView( v.getViewByNameForSubtask( name ) );
               }
               else
               if ( level == zLEVEL_SUBTASK )
               {
                  TraceLine( "GetViewByName Invalid Subtask view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
                  throw new ZeidonException( "Invalid subtask used in GetViewByName: %s", name );
               }

               if ( isValid( returnView ) )
               {
                  TraceLine( "GetViewByName located Subtask level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
                  return zLEVEL_SUBTASK;
               }

               if ( level == zLEVEL_SUBTASK )
               {
                  TraceLine( "GetViewByName did not locate Subtask level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
                  return -1;  // view invalid
               }
            }

            if ( level == zLEVEL_TASK || level == zLEVEL_ANY )
            {
               returnView.setView( t.getViewByName( name ) );
               if ( isValid( returnView ) )
               {
                 if ( v == null )
                  {
                     TraceLine( "GetViewByName located Task level view for Name: %s  Task: %s", name, t.getTaskId() );
                  }
                  else
                  {
                     TraceLine( "GetViewByName located Task level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
                  }

                  return zLEVEL_TASK;
               }

               if ( level == zLEVEL_TASK )
               {
                  if ( v == null )
                  {
                     TraceLine( "GetViewByName did not locate Task level view for Name: %s  Task: %s", name, t.getTaskId() );
                  }
                  else
                  {
                     TraceLine( "GetViewByName did not locate Task level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
                  }

                  return -1;  // view invalid
               }
            }

            if ( level == zLEVEL_APPLICATION || level == zLEVEL_ANY )
            {
               returnView.setView( a.getViewByName( name ) );
               if ( isValid( returnView ) )
               {
                  if ( v == null )
                  {
                     TraceLine( "GetViewByName located Application (%s) level view for Name: %s  Task: %s", a.getName(), name, t.getTaskId() );
                  }
                  else
                  {
                     TraceLine( "GetViewByName located Application (%s) level view: %s  for Name: %s  Task: %s", a.getName(), v.getId(), name, t.getTaskId() );
                  }

                  return zLEVEL_APPLICATION;
               }

               if ( level == zLEVEL_APPLICATION )
               {
                  if ( v == null )
                  {
                     TraceLine( "GetViewByName did not locate Application (%s) level view for Name: %s  Task: %s", a.getName(), name, t.getTaskId() );
                  }
                  else
                  {
                     TraceLine( "GetViewByName did not locate Application (%s) level view: %s  for Name: %s  Task: %s", a.getName(), v.getId(), name, t.getTaskId() );
                  }

                  return -1;  // view invalid
               }
            }

            // It has to be SYSTEM or ANY level.
            t = t.getSystemTask();
            returnView.setView( t.getViewByName( name ) );
            if ( isValid( returnView ) )
            {
               if ( v == null )
               {
                  TraceLine( "GetViewByName located System level view for Name: %s  Task: %s", name, t.getTaskId() );
               }
               else
               {
                  TraceLine( "GetViewByName located System level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
               }

               return zLEVEL_SYSTEM;
            }

            if ( level == zLEVEL_SYSTEM )
            {
               if ( v == null )
               {
                  TraceLine( "GetViewByName did not locate System level view for Name: %s  Task: %s", name, t.getTaskId() );
               }
               else
               {
                  TraceLine( "GetViewByName did not locate System level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
               }

               return -1;  // view invalid
            }
         }
         else
         {
            throw new ZeidonException( "Invalid level used in GetViewByName: %d", level );
         }
      } while ( false );

      if ( v == null )
      {
         TraceLine( "GetViewByName did not locate ANY level view for Name: %s  Task: %s", name, t.getTaskId() );
      }
      else
      {
         TraceLine( "GetViewByName did not locate ANY level view: %d  for Name: %s  Task: %s", v.getId(), name, t.getTaskId() );
      }

      return -1;  // view invalid
   }

/*
   protected int GetViewByName( zVIEW returnView, String name, View qual, int level )
   {
      View view = null;
      int  nRC;

      // The view for KZXMPGO is named _KZXMLPGO.
      if ( name.equals( "KZXMLPGO") )
      {
         name = "_KZXMLPGO";
      }

      // Check for valid scope.
      if ( name.isEmpty() == false &&
           (level == zLEVEL_SUBTASK || level == zLEVEL_TASK || level == zLEVEL_APPLICATION ||
            level == zLEVEL_SYSTEM || level == zLEVEL_ANY) )
      {
      switch ( level )
      {
         case zLEVEL_ALL:
         case zLEVEL_SUBTASK:
            nRC = zLEVEL_SUBTASK;
            view = ((View) qual).getViewByNameForSubtask( name );
            if ( view != null || level == zLEVEL_SUBTASK )
            {
               break;
            }

            // Fall through for zLEVEL_ALL...

         case zLEVEL_TASK:
            nRC = zLEVEL_TASK;
            view = qual.getViewByName( name );
            if ( view != null || level == zLEVEL_TASK )
            {
               break;
            }

            // Fall through for zLEVEL_ALL...

         case zLEVEL_APPLICATION:
            nRC = zLEVEL_APPLICATION;
            view = qual.getViewByName( name );
            if ( view != null || level == zLEVEL_APPLICATION )
            {
               break;
            }

            // Fall through for zLEVEL_ALL...

         case zLEVEL_SYSTEM:
            nRC = zLEVEL_SYSTEM;
            view = qual.getSystemTask().getViewByName( name );
            if ( view != null )
            {
               break;
            }

         default:
            throw new ZeidonException( "Invalid flag used in GetViewByName: %d", level);
      }
      }

      if ( view == null )
      {
         nRC = -1;
      }
   // else
   // {
   //    TraceLineS( "GetViewByName: ", name );
   //    DisplayObjectInstance( view, "", "" );
   // }

      returnView.setView( view );
      return nRC;
   }
*/

   protected int DropNameForView( View view, String name, View viewQual, int level )
   {
      switch ( level )
      {
         case zLEVEL_SUBTASK:
            viewQual.dropNameForSubtask( name, view );
            break;

         case zLEVEL_TASK:
            view.dropNameForView( name );
            break;

         case zLEVEL_APPLICATION:
            Application app = viewQual.getApplication( );
            app.dropNameForView( name, view );
            break;

         case zLEVEL_SYSTEM:
            viewQual.getSystemTask().dropNameForView( name, view );
            break;

         default:
            throw new ZeidonException( "Invalid flag used in DropNameForView: %d", level );
      }

      return 0;
   }

   protected int DropNameForView( View view, String name, int viewQual, int level )
   {
      return DropNameForView( view, name, null, level );
   }

   protected int CreateViewFromView( zVIEW tgtView, View srcView )
   {
      srcView = getViewFromzVIEW( srcView );
      View newView = srcView.newView( );
      newView.copyCursors( srcView );
      tgtView.setView( newView );
      return 0;
   }

   protected int CreateViewFromViewForTask( zVIEW tgtView, View srcView, View vTask )
   {
      srcView = getViewFromzVIEW( srcView );
      View newView = srcView.activateOiFromOi( ACTIVATE_CONTROL.get( 0 ) );
      newView.copyCursors( srcView );
      tgtView.setView( newView );
      tgtView.setTaskQual( vTask );
      return 0;
   }

   protected int SetViewFromView( View tgtView, View srcView )
   {
      tgtView.copyCursors( getViewFromzVIEW( srcView ) );
      return 0;
   }

   protected int SetViewFromView( zVIEW tgtView, View srcView )
   {
      if ( tgtView.getView() == null )
      {
         View newView = srcView.newView( );
         tgtView.setView( newView );
      }

      tgtView.copyCursors( getViewFromzVIEW( srcView ) );
      return 0;
   }

   // RETURNS: 0           - View successfully set for subobject
   //          1           - View is currently established on the subobject
   //          zCALL_ERROR - error creating new view
   protected int SetViewToSubobject( View view, String entityName )
   {
      int nRC;
      LodDef lodDef = view.getLodDef();
      EntityDef entityDef = lodDef.getEntityDef( entityName );

      // The C OE doesn't mind the user trying to set the subobject for non-recursive
      // entities but the JOE will throw an exception. To emulate the C OE we'll ignore
      // non recursive entities.
      // TODO: some day we need to restrict the OI to the subobject.
      if ( ! entityDef.isRecursive() )
          return 0;

      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.setToSubobject() )
      {
         nRC = 0;
      }
      else
      {
         nRC = -1;
      }

      return nRC;
   }

   //  RETURNS: 0           - View successfully reset
   //           1           - View is not currently on a subobject
   //           zCALL_ERROR - error creating new view
   protected int ResetViewFromSubobject( View view )
   {
      int nRC = 0;
      if ( isValid( view ) == false )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         view.resetSubobject();
      }

      return nRC;
   }

   protected int MoveSubobject( View tgtView, String tgtEntityName,
                                View srcView, String srcEntityName, int pos, int repos )
   {
      EntityCursor tgtCursor = tgtView.cursor( tgtEntityName );
      EntityCursor srcCursor = srcView.cursor( srcEntityName );
      int  nRC = tgtCursor.moveSubobject( CURSOR_POS.get( pos ), srcCursor, CURSOR_POS.get( repos ) ).toInt();

      return nRC;
   }

   /**
    * Concatenates two strings and returns the results.
    * Intended to be used in code created by the VML generator.
    * @param s1 - Source string
    * @param s2 -
    * @return
    */
   protected static final String zstrcat( String s1, Object... arr )
   {
      return zstrcat( new StringBuilder( s1 ), arr ).toString( );
   }

   /**
    * Appends a string to a StringBuilder and returns the results.
    * Intended to be used in coded created by the VML generator.
    * @param s1 - Source string builder
    * @param s2 -
    * @return
    */
   protected static final StringBuilder zstrcat( StringBuilder sb, Object... arr )
   {
      for ( Object o : arr )
      {
         sb.append( o );
      }

      return sb;
   }

   protected String zsprintf( String tgtString, String strFormat, Object... arr )
   {
      tgtString = String.format( strFormat, arr );
      return tgtString;
   }

   protected void zsprintf( StringBuilder sbTgt, String strFormat, Object... arr )
   {
      sbTgt.setLength( 0 );
      sbTgt.append( String.format( strFormat, arr ) );
   }

   protected static final int zstrcpy( StringBuilder sb, Object s2, Object... arr )
   {
      sb.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sb.append( s2.toString( ) );

      for ( Object o : arr )
      {
         sb.append( o );
      }

      return sb.length( );
   }

   protected static final String zstrcpy( String s, Object s2, Object... arr )
   {
      StringBuilder sb = new StringBuilder( s2.toString( ) );
      for ( Object o : arr )
      {
         sb.append( o );
      }

      return sb.toString( );
   }

   protected static final int zstrcpy( CharBuffer charBuffer, int nOffset, String s )
   {
      int k;

      for ( k = 0; k < s.length( ); k++ )
      {
         charBuffer.put( nOffset++, s.charAt( k ) );
      }

      return nOffset;  // current used length of charBuffer
   }

   protected static final int zstrcpy( StringBuilder sb, int nOffset, String s )
   {
      sb.setLength( 0 );
      if ( s != null )  // insert unfortunately appends the string "null" if inserting a null string
      {
         sb.insert( nOffset, s );
      }

      return sb.length( );
   }

   protected static final int zstrncpy( StringBuilder sb, String s2, int nLth )
   {
      sb.setLength( 0 );
      if ( s2.length() < nLth )
      {
         nLth = s2.length();
      }

      sb.append( s2.substring( 0, nLth ) );
      return sb.length( );
   }

   protected static final String zstrncpy( String s, String s2, int nLth )
   {
      if ( s2.length() < nLth )
      {
         nLth = s2.length();
      }

      s = s2.substring( 0, nLth );
      return s;
   }

   protected static final int zstrncpy( StringBuilder sb, int nOffset, String s, int nLth )
   {
      int k;

      if ( s.length() < nLth )
      {
         nLth = s.length();
      }

      for ( k = 0; k < nLth; k++ )
      {
         sb.insert( nOffset++, s.charAt( k ) );
      }

      return nOffset;  // current used length of charBuffer
   }

   protected static final int zstrncpy( CharBuffer cb, int nOffset, String s, int nLth )
   {
      int k;

      if ( s.length() < nLth )
      {
         nLth = s.length();
      }

      for ( k = 0; k < nLth; k++ )
      {
         cb.put( nOffset++, s.charAt( k ) );
      }

      return nOffset;  // current used length of charBuffer
   }

   protected static final void zmemset( StringBuilder sb, char c, int nLth )
   {
      int k;

      for ( k = 0; k < nLth; k++ )
      {
         sb.insert( k, c );
      }
   }

   protected static final void zmemset( StringBuilder sb, int nOffset, char c, int nLth )
   {
      int k;

      for ( k = 0; k < nLth; k++ )
      {
         sb.insert( k + nOffset, c );
      }
   }

   protected static final int zstrlen( String string )
   {
      return (string == null) ? 0 : string.length( );
   }

   protected static final int zstrlen( StringBuilder sb )
   {
      return (sb == null) ? 0 : sb.length( );
   }

   protected static final int zGetStringLen( String string )
   {
      return (string == null) ? 0 : string.length( );
   }

   protected static final int zGetStringLen( StringBuilder sb )
   {
      return (sb == null) ? 0 : sb.length( );
   }

   // Remove whitespace at the beginning and end of a string.
   protected static final String zstrtrim( String stringSource )
   {
      return stringSource.trim( );
   }

   // Remove leading and trailing blanks.
   protected static final int zGetStringWithoutBlank( String string, StringBuilder sbValueClean )
   {
      sbValueClean.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( StringUtils.isBlank( string ) == false )
      {
         sbValueClean.append( string.trim( ) ); // returns a copy of the string, with leading and trailing whitespace omitted.
      }

      return 0;
   }

   // Remove leading and trailing blanks.
   protected static final int zGetStringLenWithoutBlank( String string )
   {
      if ( StringUtils.isBlank( string ) )
      {
         return 0;
      }

      string = string.trim();
      int len = string.length();

      return len;
   }

   protected static final int zViewIsUpdateable( View view )
   {
	  // TODO - put code in for this. used in mFAProf.vml
      return ( 0 );
   }
   protected static final int zRemoveAllBlanks( String courseNumberIn, StringBuilder sbCourseNumberOut, int maxLth )
   {
      sbCourseNumberOut.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbCourseNumberOut.append( StringUtils.deleteWhitespace( courseNumberIn ) );
      return 0;
   }

   protected static final String zToUpper( String srcString )
   {
      return srcString.toUpperCase( );
   }

   protected static final String zToUpper( String srcString, String tgtString )
   {
      return srcString.toUpperCase( );
   }

   protected static final int zToUpper( String srcString, StringBuilder sbTarget )
   {
      sbTarget.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( StringUtils.isEmpty( srcString ) == false )
      {
         sbTarget.append( srcString.toUpperCase( ) );
      }

      return 0;
   }

   protected static final int zSearchSubString( String searchString, String subString, String direction, int startPosition )
   {
      if ( direction.startsWith( "B" ) )
      {
         return searchString.lastIndexOf( subString, startPosition - 1 );
      }
      else
      {
         return searchString.indexOf( subString, startPosition - 1 );
      }
   }

   protected static final String zstrstr( String searchString, String subString )
   {
      int position = searchString.indexOf( subString );
      if ( position >= 0 )
      {
         return searchString.substring( position );
      }
      else
      {
         return null;
      }
   }

   protected static final String zstrstr( String searchString, int nOffset, String subString )
   {
      int position = searchString.indexOf( subString, nOffset );
      if ( position >= 0 )
      {
         return searchString.substring( position );
      }
      else
      {
         return null;
      }
   }

   protected static final int zstrstr( StringBuilder sbString, int nOffset, String subString )
   {
      return sbString.indexOf( subString, nOffset );
   }

   protected static final int zstrchr( String searchString, int nOffset, char searchChar )
   {
      return searchString.indexOf( searchChar, nOffset );
   }

   protected static final int zstrchr( String searchString, char searchChar )
   {
      return searchString.indexOf( searchChar );
   }

   protected static final int zstrchr( StringBuilder sbSearchString, char searchChar )
   {
      return sbSearchString.toString( ).indexOf( searchChar );
   }

   protected static final int zstrrchr( String searchString, char searchChar )
   {
      return searchString.lastIndexOf( searchChar );
   }

   protected static final int zstrcmp( String string1, String string2 )
   {
      // KJS 10/31/13 - When I do a "IF "aaaa" <= "bbbb"", this should return > 0 but it's returning -1 so I am
	  // switching the strings.
      //return string1.compareTo( string2 );
      return string2.compareTo( string1 );
   }

   protected static final int zstrncmp( String string1, String string2, int nLth )
   {
      if ( nLth > 0 )
      {
          // KJS 10/31/13 - When I do a "IF "aaaa" <= "bbbb"", this should return > 0 but it's returning -1 so I am
    	  // switching the strings.
          //return string1.substring( 0, nLth ).compareTo( string2.substring( 0, nLth ) );
          return string2.substring( 0, nLth ).compareTo( string1.substring( 0, nLth ) );
      }
      else
      {
          // KJS 10/31/13 - When I do a "IF "aaaa" <= "bbbb"", this should return > 0 but it's returning -1 so I am
    	  // switching the strings.
          //return string1.compareTo( string2 );
          return string2.compareTo( string1 );
      }
   }

   protected static final int zstrnicmp( String string1, String string2, int nLth )
   {
      // KJS 10/31/13 - When I do a "IF "aaaa" <= "bbbb"", this should return > 0 but it's returning -1 so I am
 	  // switching the strings.
      //return string1.substring( 0, nLth - 1 ).compareToIgnoreCase( string2.substring( 0, nLth - 1 ) );
      return string2.substring( 0, nLth - 1 ).compareToIgnoreCase( string1.substring( 0, nLth - 1 ) );
   }

   protected static final int zstrcmpi( String string1, String string2 )
   {
      // KJS 10/31/13 - When I do a "IF "aaaa" <= "bbbb"", this should return > 0 but it's returning -1 so I am
 	  // switching the strings.
      //return string1.compareToIgnoreCase( string2 );
      return string2.compareToIgnoreCase( string1 );
   }

   protected static final String zSearchAndReplace( String tgtString, int tgtLth, String searchString, String replaceString )
   {
      StringBuilder sb = new StringBuilder( tgtString );
      int position = sb.indexOf( searchString );
      while ( position >= 0 )
      {
         //sbString.replace( position, position + searchString.length( ) - 1, replaceString );
         sb.replace( position, position + searchString.length( ), replaceString );

         position = position + replaceString.length();
         position = sb.indexOf( searchString, position );
      }

      return sb.toString( );
   }

   protected static final int zSearchAndReplace( StringBuilder sbString, int tgtLth, String searchString, String replaceString )
   {
      int position = sbString.indexOf( searchString );
      while ( position >= 0 )
      {
         //sbString.replace( position, position + searchString.length( ) - 1, replaceString );
         sbString.replace( position, position + searchString.length( ), replaceString );

         position = position + replaceString.length();
         position = sbString.indexOf( searchString, position );
      }

      return sbString.length( );
   }

   protected static final String zltoa( int value )
   {
      return Integer.toString( value, 10 );
   }

   protected static final String zltoa( int value, String string )
   {
      return Integer.toString( value, 10 );
   }

   protected static final String zIntegerToString( String string, int length, int value )
   {
      string = Integer.toString( value, 10 );
      if ( string.length( ) > length )
      {
         string = string.substring( 0, length );
      }

      return string;
   }

   public static final int zIntegerToString( StringBuilder sb, int length, int value )
   {
      String s = "";

      sb.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      s = zIntegerToString( s, length, value );
      sb.append( s ) ;

      return 0;
   }

   protected static final int zatol( String string )
   {
      return Integer.parseInt( string );
   }

   protected static final int zStringToInteger( String string )
   {
      return Integer.parseInt( string );
   }

   protected static final String zGetFirstEntityNameForView( View view, String entityName )
   {
      return view.getLodDef( ).getEntityDef( 0 ).getName( );
   }

   protected static final String zGetNextEntityNameForView( View view, String entityName )
   {
      int k;
      int nCnt = view.getLodDef( ).getEntityCount( );

      for ( k = 0; k < nCnt; k++ )
      {
         if ( view.getLodDef( ).getEntityDef( k ).getName( ).compareTo( entityName ) == 0 )
         {
            if ( k < nCnt - 1 )
            {
               return view.getLodDef( ).getEntityDef( k + 1 ).getName( );
            }
         }
      }

      return view.getLodDef( ).getEntityDef( 0 ).getName( );
   }

   protected static final String zGetFirstAttributeNameForEntity( View view, String entityName, String attributeName )
   {
      EntityDef ve = view.getLodDef( ).getEntityDef( entityName );
      if ( ve == null )
      {
         return null;
      }

      return ve.getAttribute( 0 ).getName( );
   }

   protected static final String zGetNextAttributeNameForEntity( View view, String entityName, String attributeName )
   {
      EntityDef ve = view.getLodDef( ).getEntityDef( entityName );
      if ( ve == null )
         return null;

      int nCnt = ve.getAttributeCount( );
      int k;

      for ( k = 0; k < nCnt; k++ )
      {
         if ( ve.getAttribute( k ).getName( ).compareTo( attributeName ) == 0 )
         {
            if ( k < nCnt - 1 )
            {
               return ve.getAttribute( k + 1 ).getName( );
            }
         }
      }

      return ve.getAttribute( 0 ).getName( );
   }

   protected static final int zDateIsLeapYear( String date )
   {
      // TODO
      return 0;
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // Function Name:  zLeft
   // Author:         ASE
   // Date:           13.December 2001
   // last changed:   15.January 2002
   //
   // description:    puts the 'nbrLeft' leftmost characters of 'String' in 'ResultString'.
   //                 if 'nbrLeft' exceeds the Input Strings' length, 'nbrLeft' will be
   //                 reduced to the String's actual length.
   //
   // parameters:     stringIn     - Input String
   //                 nbrLeft      - Number of characters to copy
   //                 resultString    - Output/Result String
   //                 resultStringLth - Initial length of the Output String
   //                                   ResultString is going to be null-terminated, so
   //                                   the ResultString has to be 1 byte larger than the
   //                                   Number of characters to copy
   //
   // returns:         0           - successful
   //                  -1           - failed: ResultString too short
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   zLeft( String stringIn, int nbrLeft, StringBuilder sbResult, int resultStringLth )
   {
      // check if 'nbrLeft' is within range
      if ( nbrLeft > zstrlen( stringIn ) )
      {
         nbrLeft = zstrlen( stringIn );
      }

      // check if ResultString is large enough
      if ( resultStringLth < (nbrLeft + 1) )
      {
         return 0;
      }

      // copy and append terminating null
      sbResult.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbResult.append( stringIn.substring( 0, nbrLeft - 1 ) );

      return sbResult.length( );
   }

   //
   //  PARAMETERS: strOne       - Compare string1.
   //              strOneIdx    - The index into target at which comparing will begin. Note that
   //                             Zeidon Strings are indexed starting at 1 rather than 0.
   //              strOneMaxCompare - The maximum number of characters to be compared in the target string
   //                             (including the null terminator).  If this is 0, then set it to be
   //                             the length of the target string.
   //              strTwo       - Compare string2.
   //              strTwoIdx    - The index into source at which comparing will begin. Note that
   //                             Zeidon Strings are indexed starting at 1 rather than 0.
   //              strTwoMaxCompare - The maximum number of characters to be compared in the source string
   //                             (including the null terminator).  If this is 0, then compare until
   //                             the end of the source, target, or maxReceive, whichever comes first.
   //              maxCompareLth - The maximum length of the compare INCLUDING the null terminator
   //                             (i.e.  if the target buffer is defined as:  zCHAR szTgtBuff[ 33 ];
   //                             maxTgtLth should be 33).  It is guaranteed by this operation that
   //                             the length of the compare will not exceed maxTgtLth.
   //
   //    There are a couple defaults. If maxReceive is 0, then compare to the target until the end of
   //    the target, never exceeding maxTargetLth. If maxCompare is 0, then compare until the end of
   //    the source, target, or maxReceive; whichever comes first.
   //
   //  RETURNS:    -1  - Source string < Target string
   //               0  - Source string = Target string
   //               1  - Source string > Target string
   //              -2  - maxReceive is negative
   //              -3  - maxCompare is negative
   //              -4  - maxReceive exceeds maxTargetLth
   //              -5  - Invalid parameters (null string pointers or 0 indexes)
   //
   // Where szKeyRole could be "Dual" or "D" ... either should compare equal ...
   // if ( ZeidonStringCompare( szKeyRole, 1, 0, "D", 1, 0, 2 ) == 0 )
   //
   protected int ZeidonStringCompare( String strOne,
                                      int    strOneIdx,
                                      int    strOneMaxCompare,
                                      String strTwo,
                                      int    strTwoIdx,
                                      int    strTwoMaxCompare,
                                      int    maxCompareLth )
   {
      int  nRC;

      // Ensure all parms sync up and that strOneIdx + strOneMaxReceive is less than maxCompareLth.
      if ( maxCompareLth <= 0 )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCompare: Maximum compare length is not greater than zero.", 1 );
         return( qMAXRECEIVEISNEGATIVE );
      }

      if ( strOneMaxCompare < 0 )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCompare: String One has negative length.", 1 );
         return( qMAXRECEIVEISNEGATIVE );
      }

      if ( strTwoMaxCompare < 0 )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCompare: String Two has negative length.", 1 );
         return( qMAXCOPYISNEGATIVE );
      }

      if ( (strOneIdx + strOneMaxCompare) > maxCompareLth )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCompare: strOne string is too short.", 1 );
         return( qMAXRECEIVEISNEGATIVE );
      }

      if ( StringUtils.isBlank( strOne ) && StringUtils.isBlank( strTwo ) )
      {
         return 0;  // considered to be the same (empty)
      }

      strOneIdx--;  // convert 1-based index to 0-based index
      strTwoIdx--;
      maxCompareLth--;
      if ( strOneIdx < 0 || strTwoIdx < 0 ||      // 0-based index
           (strOne == null && strOneIdx > 0) ||   // gotta have strings
           (strTwo == null && strTwoIdx > 0) ||
           (strOne != null && strOneIdx > strOne.length( )) ||
           (strTwo != null && strTwoIdx > strTwo.length( )) )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCompare: Invalid parameter.", 1 );
         return( qINVALIDPARAMETER );
      }

      String string1 = null;
      String string2 = null;

      if ( strOneMaxCompare <= 0 )
      {
         string1 = (strOne == null) ? "" : strOne.substring( strOneIdx );
      }
      else
      {
         string1 = (strOne == null) ? "" : strOne.substring( strOneIdx, strOneIdx + strOneMaxCompare );
      }

      if ( strTwoMaxCompare <= 0 )
      {
         string2 = (strTwo == null) ? "" : strTwo.substring( strTwoIdx );
      }
      else
      {
         string2 = (strTwo == null) ? "" : strTwo.substring( strTwoIdx, strTwoMaxCompare );
      }

      //string1 = (strTwo == null) ? "" : strTwo.substring( strTwoIdx );
      //string2 = (strOne == null) ? "" : strOne.substring( strOneIdx );
      int string1Lth = string1.length( );
      int string2Lth = string2.length( );

      // KJS 10/06/11 - Running into a situation where the comparison of "3,950" and "3,950.00" are being compare
      // as equal because the strOneMaxReceive and strTwoMaxReceive are both zero which then compares both to
      // the least length (5).
      if ( string1Lth <= maxCompareLth && string2Lth <= maxCompareLth )
      {
         nRC = zstrcmp( string1, string2 );
      }
      else
      {
         // KJS 10/12/11 - Should we ever really get here?  Do we really want to use the max length
         // of the first string anywhere???!!!
         string1 = string1.substring( 0, maxCompareLth < string1Lth ? maxCompareLth : string1Lth );
         string2 = string2.substring( 0, maxCompareLth < string2Lth ? maxCompareLth : string2Lth );

//       // If strOneMaxReceive is the default of 0 then strOneMaxReceive is really (string2Lth - strOneIdx).
//       if ( strOneMaxReceive == 0 )
//          strOneMaxReceive = string2Lth;
//
//       if ( strTwoMaxReceive == 0 )
//          strTwoMaxReceive = string1Lth < strOneMaxReceive ? string1Lth : strOneMaxReceive;
//       else
//          strTwoMaxReceive = string1Lth < strOneMaxReceive ? (strTwoMaxReceive < strOneMaxReceive ? strTwoMaxReceive : strOneMaxReceive) : (strTwoMaxReceive < string1Lth ? strTwoMaxReceive : string1Lth);
//
//       // Compare the lesser of strOneMaxReceive or strTwoMaxReceive.
//       int nbrToCompare = (strOneMaxReceive < strTwoMaxReceive) ? strOneMaxReceive : strTwoMaxReceive;
//       if ( nbrToCompare < string1Lth || nbrToCompare < string2Lth )
//          nRC = zstrncmp( string1, string2, nbrToCompare );
//       else
             nRC = zstrcmp( string1, string2 );
      }

      nRC = nRC == 0 ? 0 : nRC > 0 ? 1 : -1;
      return nRC;
   }

   protected int ZeidonStringCompare( StringBuilder sbTarget,
                                      int    targetIdx,
                                      int    maxReceive,
                                      String source,
                                      int    sourceIdx,
                                      int    maxCompare,
                                      int    maxTargetLth )
   {
      return ZeidonStringCompare( sbTarget.toString(), targetIdx, maxReceive,
                                  source.toString(), sourceIdx, maxCompare, maxTargetLth );
   }

   protected int ZeidonStringCompare( String target,
                                      int    targetIdx,
                                      int    maxReceive,
                                      StringBuilder sbSource,
                                      int    sourceIdx,
                                      int    maxCompare,
                                      int    maxTargetLth )
   {
      return ZeidonStringCompare( target.toString(), targetIdx, maxReceive,
                                  sbSource.toString(), sourceIdx, maxCompare, maxTargetLth );
   }

   protected int ZeidonStringCompare( StringBuilder sbTarget,
                                      int    targetIdx,
                                      int    maxReceive,
                                      StringBuilder sbSource,
                                      int    sourceIdx,
                                      int    maxCompare,
                                      int    maxTargetLth )
   {
      return ZeidonStringCompare( sbTarget.toString(), targetIdx, maxReceive,
                                  sbSource.toString(), sourceIdx, maxCompare, maxTargetLth );
   }

   //  PARAMETERS: sbTarget      - The resultant string (copied into).
   //              targetIdx     - The index into the string at which copying
   //                              will begin. Note that Zeidon Strings are
   //                              indexed starting at 1 rather than 0.
   //              maxReceive    - The maximum number of characters permitted
   //                              in the target string (including the null
   //                              terminator).  If this is 0, then copy until
   //                              the target is full or the end of the source,
   //                              whichever comes first.  If this is a -1, we
   //                              will move the characters into the target
   //                              string, without a null character at end.
   //              sbSource      - The source string of the copy.
   //              sourceIdx     - The index into the string at which reading
   //                              will begin.  Note that Zeidon Strings are
   //                              indexed starting at 1 rather than 0.
   //              maxCopy       - The maximum number of characters to copy
   //                              from the source string.  If this is 0, then
   //                              copy until the end of the source, target, or
   //                              maxCopy, whichever comes first.
   //              maxTargetLth  - The maximum length of the target string
   //                              INCLUDING the null terminator (i.e.  if the
   //                              target buffer is defined as:
   //                                     zCHAR szTgtBuff[ 33 ];
   //                              maxTargetLth should be 33).  It is guaranteed
   //                              by this operation that the length of the
   //                              result string + 1 (for the null terminator)
   //                              will not exceed maxTargetLth.
   //
   public int ZeidonStringCopy( StringBuilder sbTarget,
                                int    targetIdx,
                                int    maxReceive,
                                StringBuilder sbSource,
                                int    sourceIdx,
                                int    maxCopy,
                                int    maxTargetLth )
   {
      int  nRC;
      int nbrToCopy;
      int lth;

      if ( sbTarget == null || sbSource == null ||     // gotta have strings
           targetIdx == 0 || sourceIdx == 0 )          // 1-based index
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCopy: Invalid parameter.", 1 );
         return( qINVALIDPARAMETER );
      }

      targetIdx--;  // convert 1-based index to 0-based index for
      sourceIdx--;

      // Ensure all parms sync up and that targetIdx + maxReceive is less than maxTargetLth.
      if ( maxReceive < -1 )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCopy: Target string has negative length", 1 );
         return( qMAXRECEIVEISNEGATIVE );
      }

      if ( maxCopy < 0 )
      {
         return( qMAXCOPYISNEGATIVE );
      }

      if ( (targetIdx + maxReceive) > maxTargetLth )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCopy: Target string is too small", 1 );
         return( qMAXRECEIVEEXCEEDSTARGETLEN );
      }

      // Ensure the source index does not point beyond the end of the source string.
      if ( sourceIdx > zstrlen(sbSource) )
      {
         SysMessageBox( null, "JOE System Error",
                        "ZeidonStringCopy: Invalid parameter (source index).", 1 );
         return( qINVALIDPARAMETER );
      }

      // If maxReceive is -1, move the characters to the target without a null at end.
      if ( maxReceive == -1 )
      {
         if ( maxCopy == 0 )
         {
            maxCopy = maxTargetLth - targetIdx - 1;
         }

         while ( sourceIdx < sbSource.length( ) && sbSource.charAt( sourceIdx ) != '\0' && maxCopy > 0 )
         {
            sbTarget.setCharAt( targetIdx++, sbSource.charAt( sourceIdx++ ) );
            maxCopy--;
         }

         return( sbTarget.length( ) );
      }

      // If maxReceive is the default of 0 then the Max is really
      // maxTargetLth - targetIdx - 1 (for zero-based index).
      if ( maxReceive == 0 )
      {
         maxReceive = maxTargetLth - targetIdx - 1;
      }

      if ( maxCopy == 0 )
      {
         maxCopy = maxReceive;
      }

      // Copy the lesser of maxReceive or maxCopy.
      nbrToCopy = (maxReceive < maxCopy) ? maxReceive : maxCopy;
      lth = sbSource.length( ) - sourceIdx;
      if ( lth < nbrToCopy )
      {
         nbrToCopy = lth; // remove the "+ 1" from the original C ... requested by Kelly  2011.10.27
      }

      sbTarget.setLength( targetIdx + nbrToCopy );
      while ( sourceIdx < sbSource.length( ) && sbSource.charAt( sourceIdx ) != '\0' && nbrToCopy > 0 )
      {
         sbTarget.setCharAt( targetIdx++, sbSource.charAt( sourceIdx++ ) );
         nbrToCopy--;
      }

   // sbTarget.setCharAt( targetIdx, '\0' );
      return sbTarget.length( );

/*
      // Currently ZeidonStringCopy is being generated with the targetIdx and sourceIdx as 1.
      // The start of a string is 0.
      if ( targetIdx > 0 )
         targetIdx--;

      if ( sourceIdx > 0 )
         sourceIdx--;

      if ( maxTargetLth > 0 )
         maxTargetLth--;

      if ( maxReceive == 0 )
       maxReceive = maxTargetLth;

      if ( maxCopy == 0 && sbSource != null )
       maxCopy = sbSource.length( );

      if ( sbSource != null )
         sbTarget.replace( targetIdx, maxReceive, sbSource.substring( sourceIdx, maxCopy ) );
      // target.replace( targetIdx, -1, source.substring( sourceIdx ) );

      nRC = sbTarget.length( );
      if ( nRC > maxTargetLth )
      {
         sbTarget.setLength( maxTargetLth );
         nRC = maxTargetLth;
      }

      return nRC;
*/
   }

   protected int ZeidonStringCopy( StringBuilder sbTarget,
                                   int    targetIdx,
                                   int    maxReceive,
                                   String source,
                                   int    sourceIdx,
                                   int    maxCopy,
                                   int    maxTargetLth )
   {
      int  nRC;
      StringBuilder sb;

      if ( source == null )
      {
         sb = new StringBuilder( );
      }
      else
      {
         sb = new StringBuilder( source );
      }

      nRC = ZeidonStringCopy( sbTarget, targetIdx, maxReceive, sb, sourceIdx, maxCopy, maxTargetLth );

      return nRC;
   }

   protected String ZeidonStringCopy( String target,
                                      int    targetIdx,
                                      int    maxReceive,
                                      StringBuilder sbSource,
                                      int    sourceIdx,
                                      int    maxCopy,
                                      int    maxTargetLth )
   {
      int  nRC;
      StringBuilder sb;

      if ( target == null )
      {
         sb = new StringBuilder( );
      }
      else
      {
         sb = new StringBuilder( target );
      }

      nRC = ZeidonStringCopy( sb, targetIdx, maxReceive, sbSource, sourceIdx, maxCopy, maxTargetLth );

      return sb.toString( );
   }

   protected String ZeidonStringCopy( String target,
                                      int    targetIdx,
                                      int    maxReceive,
                                      String source,
                                      int    sourceIdx,
                                      int    maxCopy,
                                      int    maxTargetLth )
   {
      int  nRC;

      StringBuilder sbTgt = new StringBuilder( );
      StringBuilder sbSrc = new StringBuilder( );
      if ( target != null )
      {
         sbTgt.append( target );
      }

      if ( source != null )
      {
         sbSrc.append( source );
      }

      ZeidonStringCopy( sbTgt, targetIdx, maxReceive, sbSrc, sourceIdx, maxCopy, maxTargetLth );

      nRC = sbTgt.length( );
      return sbTgt.toString( );
   }

   protected StringBuilder ZeidonStringConcat( StringBuilder sbTarget,
                                               int    targetIdx,
                                               int    maxReceive,
                                               StringBuilder sbSource,
                                               int    sourceIdx,
                                               int    maxCopy,
                                               int    maxTargetLth )
   {
      if ( sbSource != null )
      {
         if ( sourceIdx > 0 )
         {
            sbTarget.append( sbSource.substring( sourceIdx - 1 ) );
         }
         else
         {
            sbTarget.append( sbSource );
         }
      }

      // RESULT = sbTarget.length( );
      return sbTarget;
   }

   protected StringBuilder ZeidonStringConcat( StringBuilder sbTarget,
                                               int    targetIdx,
                                               int    maxReceive,
                                               String source,
                                               int    sourceIdx,
                                               int    maxCopy,
                                               int    maxTargetLth )
   {
      if ( sourceIdx > 0 )
      {
         sbTarget.append( source.substring( sourceIdx - 1 ) );
      }
      else
      {
         sbTarget.append( source );
      }

   // RESULT = sbTarget.length( );
      return sbTarget;
   }

   protected String ZeidonStringConcat( String target,
                                        int    targetIdx,
                                        int    maxReceive,
                                        StringBuilder sbSource,
                                        int    sourceIdx,
                                        int    maxCopy,
                                        int    maxTargetLth )
   {
      if ( sourceIdx > 0 )
      {
         target = target + sbSource.substring( sourceIdx - 1 );
      }
      else
      {
         target = target + sbSource;
      }

   // nRC = target.length();
      return target;
   }

   protected String ZeidonStringConcat( String target,
                                        int    targetIdx,
                                        int    maxReceive,
                                        String source,
                                        int    sourceIdx,
                                        int    maxCopy,
                                        int    maxTargetLth )
   {
      if ( targetIdx > 0 )
      {
         targetIdx--;
      }

      if ( sourceIdx > 0 )
      {
         sourceIdx--;
      }

      if ( source != null )
      {
         if ( sourceIdx > 0 )
         {
            target = target + source.substring( sourceIdx );
         }
         else
         {
            target = target + source;
         }
      }

   // RESULT = target.length( );
      return target;
   }

   protected int ZeidonStringConvertFromNumber( StringBuilder sbSuffix, int stringIndex, int maxSourceLth,
                                                int maxTargetLth, int intValue, double dblValue, String convType )
   {
      // ZeidonStringConvertFromNumber( STRING /* Target String */,
      //        INTEGER /* String Index */,
      //        INTEGER /* Max Receive Length */,
      //        INTEGER /* Max String Length */,
      //        INTEGER /* Integer Value to Convert */,
      //        DOUBLE /* Decimal Value to Convert */,
      //        STRING /* Type of Conversion - Integer or Decimal */ )

      if ( convType.equals( "I" ) )
      {
         zstrcpy( sbSuffix, intValue );
      }
      else
      {
         zstrcpy( sbSuffix, dblValue );
      }

      return sbSuffix.length( );
   }

   protected int ZeidonStringConvertFromNumber( StringBuilder sbSuffix, int stringIndex, int maxSourceLth,
                                                int maxTargetLth, int intValue, int nValue, String convType )
   {
      // ZeidonStringConvertFromNumber( STRING /* Target String */,
      //        INTEGER /* String Index */,
      //        INTEGER /* Max Receive Length */,
      //        INTEGER /* Max String Length */,
      //        INTEGER /* Integer Value to Convert */,
      //        DOUBLE /* Decimal Value to Convert */,
      //        STRING /* Type of Conversion - Integer or Decimal */ )

      if ( convType.equals( "I" ) )
      {
         zstrcpy( sbSuffix, intValue );
      }
      else
      {
         zstrcpy( sbSuffix, nValue );
      }

      return sbSuffix.length( );
   }

   //  RETURNS: zCURSOR_NULL - Entity cursor is NULL
   //           zCURSOR_SET  - Entity position established on first entity
   //           zCALL_ERROR  - Error in call
   protected int SetCursorFirstEntity( View view, String entityName, String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      nRC = cursor.setFirst( scopingEntity ).toInt();

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL - Entity cursor is NULL
   //           zCURSOR_SET  - Entity position established on next entity
   //           zCALL_ERROR  - Error in call
   protected int SetCursorNextEntity( View view, String entityName, String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      nRC = cursor.setNext( scopingEntity ).toInt();

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL - Entity cursor is NULL
   //           zCURSOR_SET  - Entity position established on previous entity
   //           zCALL_ERROR  - Error in call
   protected int SetCursorPrevEntity( View view, String entityName, String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      nRC = cursor.setPrev( scopingEntity ).toInt();

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL - Entity cursor is NULL
   //           zCURSOR_SET  - Entity position established on last entity
   //           zCALL_ERROR  - Error in call
   protected int SetCursorLastEntity( View view, String entityName, String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      nRC = cursor.setLast( scopingEntity ).toInt();

      return nRC;
   }

   protected int SetCursorFirstEntityByAttribute( View   view,
                                                  String entityName,
                                                  String attributeName,
                                                  String value,
                                                  String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = cursor.setFirst( attributeName, value, scopingEntity ).toInt();
      }

      return nRC;
   }

   protected int SetCursorFirstEntityByAttr( View   tgtView,
                                             String tgtEntity,
                                             String tgtAttribute,
                                             View   srcView,
                                             String srcEntity,
                                             String srcAttribute,
                                             String scopingEntity )
   {
      int    nRC;

      EntityCursor cursor = tgtView.cursor( tgtEntity );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         String value = srcView.cursor( srcEntity ).getStringFromAttribute( srcAttribute );
         nRC = cursor.setFirst( tgtAttribute, value, scopingEntity ).toInt();
      }

      return nRC;
   }

   protected int SetCursorNextEntityByAttr( View   tgtView,
                                            String tgtEntity,
                                            String tgtAttribute,
                                            View   srcView,
                                            String srcEntity,
                                            String srcAttribute,
                                            String scopingEntity )
   {
      int    nRC;

      EntityCursor cursor = tgtView.cursor( tgtEntity );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         String value = srcView.cursor( srcEntity ).getStringFromAttribute( srcAttribute );
      // nRC = cursor.setFirst( tgtAttribute, value, scopingEntity ).toInt();
         nRC = cursor.setFirst( scopingEntity ).toInt();
      }

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL      - No entity instances exist
   //           zCURSOR_UNCHANGED - Entity cursor unchanged
   //           zCURSOR_SET       - Entity position established
   //           zCALL_ERROR       - Error in call
   public int SetCursorFirstEntityByEntityCsr( View tgtView, String tgtEntityName,
                                               View srcView, String srcEntityName, String scopingEntity )
   {
      EntityCursor tgtCursor = tgtView.cursor( tgtEntityName );
      EntityCursor srcCursor = srcView.cursor( srcEntityName );
      if ( tgtCursor.hasAny( scopingEntity ) == false || srcCursor.isNull() )
      {
         return zCURSOR_NULL;
      }

      EntityInstance original = tgtCursor.getEntityInstance(); // Save original in case we don't find a match.
      for ( CursorResult rc = tgtCursor.setFirst( scopingEntity );
            rc.isSet();
            rc = tgtCursor.setNextContinue() )
      {
          if ( tgtCursor.isLinked( srcCursor ) )
          {
              // We found what we want so return.
              return CursorResult.SET.toInt();
          }
      }

      tgtCursor.setCursor( original ); // Reset back to the original.
      return CursorResult.UNCHANGED.toInt();
   }

   //  RETURNS: zCURSOR_NULL          - No entity instances exist
   //           zCURSOR_UNCHANGED     - Entity cursor unchanged
   //           zCURSOR_SET           - Entity position established
   //           zCURSOR_SET_NEWPARENT - Entity position changed within new parent
   //           zCALL_ERROR           - Error in call
   protected int SetCursorNextEntityByEntityCsr( View tgtView, String tgtEntityName,
                                                 View srcView, String srcEntityName, String scopingEntity )
   {
      EntityCursor tgtCursor = tgtView.cursor( tgtEntityName );
      EntityCursor srcCursor = srcView.cursor( srcEntityName );
      if ( tgtCursor.hasAny( scopingEntity ) == false || srcCursor.isNull() )
      {
         return zCURSOR_NULL;
      }

      EntityInstance original = tgtCursor.getEntityInstance(); // Save original in case we don't find a match.
      CursorResult rc = tgtCursor.setNextContinue( );
      while ( rc.isSet() )
      {
          if ( tgtCursor.isLinked( srcCursor ) )
          {
              // We found what we want so return.
              return CursorResult.SET.toInt();
          }

          rc = tgtCursor.setNextContinue( );
      }

      tgtCursor.setCursor( original ); // Reset back to the original.
      return CursorResult.UNCHANGED.toInt();
   }

   public int SetCursorScopeOI( View view, String entityName, int pos )
   {
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny() )
      {
         if ( pos == zPOS_FIRST )
         {
            return cursor.setFirstWithinOi().toInt();
         }
         else
         if ( pos == zPOS_NEXT )
         {
            return cursor.setNextWithinOi().toInt();
         }
      // else
      // if ( pos == zPOS_PREVIOUS )
      //    return cursor.setPreviousWithinOi();
      }

      return zCURSOR_NULL;
   }

   //  RETURNS: zCURSOR_NULL          - No entity instances exist
   //           zCURSOR_UNCHANGED     - Entity cursor unchanged
   //           zCURSOR_SET           - Entity position established
   //           zCURSOR_SET_NEWPARENT - Entity position changed within new parent
   //           zCALL_ERROR           - Error in call

   protected final int zSCOPE_OI = 1;

   protected int SetEntityCursor( View     view,
                                  String   entityName,
                                  String   attributeName,
                                  int      lControl,
                                  double   dDecimalSearchValue,
                                  String   srcEntityName,
                                  String   srcAttributeName,
                                  int      relativePosition,
                                  String   scopingEntity,
                                  String   contextName )
   {
      if ( (lControl & (zQUAL_STRING + zQUAL_INTEGER)) != 0 )
      {
         TraceLineS( "SetEntityCursor Control Error ... Decimal looking for ", (lControl & zQUAL_STRING) == 0 ? "String" : "Integer" );
         return zCALL_ERROR;
      }

      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         return zCURSOR_NULL;
      }

      boolean bTestCsrResult = (lControl & zTEST_CSR_RESULT) != 0;
      boolean bQualScopeOI = (lControl & zQUAL_SCOPE_OI) != 0;
      lControl &= 0x7;
      if ( bTestCsrResult )
      {
         if ( bQualScopeOI )
         {
            if ( lControl == zPOS_FIRST || lControl == zPOS_LAST )
            {
               return cursor.hasAnyWithinOi( attributeName, dDecimalSearchValue ) ? zCURSOR_SET : zCURSOR_NULL;
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.hasNext( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.hasPrev( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            {
               // error!!!
            }
         }
         else
         {
            if ( lControl == zPOS_FIRST || lControl == zPOS_LAST )
            {
               return cursor.hasAny( attributeName, dDecimalSearchValue, scopingEntity ) ? zCURSOR_SET : zCURSOR_NULL;
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.hasNext( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.hasPrev( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            {
               // error!
            }
         }
      }
      else
      {
         if ( bQualScopeOI )
         {
            if ( lControl == zPOS_FIRST )
            {
               return cursor.setFirstWithinOi( attributeName, dDecimalSearchValue ).toInt();
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.setNextWithinOi( ).toInt();
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.setPrevWithinOi( ).toInt();
            }
            else
            if ( lControl == zPOS_LAST )
            {
               return cursor.setLastWithinOi( attributeName, dDecimalSearchValue ).toInt();
            }
            else
            {
               // error!!!
            }
         }
         else
         {
            if ( lControl == zPOS_FIRST )
            {
               return cursor.setFirst( attributeName, dDecimalSearchValue, scopingEntity ).toInt();
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.setNext( attributeName, dDecimalSearchValue, scopingEntity ).toInt();

            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.setPrev( attributeName, dDecimalSearchValue, scopingEntity ).toInt();
            }
            else
            if ( lControl == zPOS_LAST )
            {
               return cursor.setLast( attributeName, dDecimalSearchValue, scopingEntity ).toInt();
            }
            else
            {
               // error
            }
         }
      }

      return zCURSOR_NULL;
   }

   protected int SetEntityCursor( View     view,
                                  String   entityName,
                                  String   attributeName,
                                  int      lControl,
                                  int      lIntegerSearchValue,
                                  String   srcEntityName,
                                  String   srcAttributeName,
                                  int      relativePosition,
                                  String   scopingEntity,
                                  String   contextName )
   {
      if ( (lControl & (zQUAL_STRING + zQUAL_DECIMAL)) != 0 )
      {
         TraceLineS( "SetEntityCursor Control Error ... Integer looking for ", (lControl & zQUAL_STRING) == 0 ? "String" : "Decimal" );
         return zCALL_ERROR;
      }

      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         return zCURSOR_NULL;
      }

      boolean bTestCsrResult = (lControl & zTEST_CSR_RESULT) != 0;
      boolean bQualScopeOI = (lControl & zQUAL_SCOPE_OI) != 0;
      lControl &= 0x7;
      if ( bTestCsrResult )
      {
         if ( bQualScopeOI )
         {
            if ( lControl == zPOS_FIRST || lControl == zPOS_LAST )
            {
               return cursor.hasAnyWithinOi( attributeName, lIntegerSearchValue ) ? zCURSOR_SET : zCURSOR_NULL;
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.hasNext( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.hasPrev( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            {
               // error!!!
            }
         }
         else
         {
            if ( lControl == zPOS_FIRST || lControl == zPOS_LAST )
            {
               return cursor.hasAny( attributeName, lIntegerSearchValue, scopingEntity ) ? zCURSOR_SET : zCURSOR_NULL;
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.hasNext( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.hasPrev( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            {
               // error!
            }
         }
      }
      else
      {
         if ( bQualScopeOI )
         {
            if ( lControl == zPOS_FIRST )
            {
               return cursor.setFirstWithinOi( attributeName, lIntegerSearchValue ).toInt();
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.setNextWithinOi( ).toInt();
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.setPrevWithinOi( ).toInt();
            }
            else
            if ( lControl == zPOS_LAST )
            {
               return cursor.setLastWithinOi( attributeName, lIntegerSearchValue ).toInt();
            }
            else
            {
               // error!!!
            }
         }
         else
         {
            if ( lControl == zPOS_FIRST )
            {
               return cursor.setFirst( attributeName, lIntegerSearchValue, scopingEntity ).toInt();
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.setNext( attributeName, lIntegerSearchValue, scopingEntity ).toInt();
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.setPrev( attributeName, lIntegerSearchValue, scopingEntity ).toInt();
            }
            else
            if ( lControl == zPOS_LAST )
            {
               return cursor.setLast( attributeName, lIntegerSearchValue, scopingEntity ).toInt();
            }
            else
            {
               // error
            }
         }
      }

      return zCURSOR_NULL;
   }

   protected int SetEntityCursor( View     view,
                                  String   entityName,
                                  String   attributeName,
                                  int      lControl,
                                  String   stringSearchValue,
                                  String   srcEntityName,
                                  String   srcAttributeName,
                                  int      relativePosition,
                                  String   scopingEntity,
                                  String   contextName )
   {
      if ( (lControl & (zQUAL_INTEGER + zQUAL_DECIMAL)) != 0 )
      {
         TraceLineS( "SetEntityCursor Control Error ... String looking for ", (lControl & zQUAL_INTEGER) == 0 ? "Integer" : "Decimal" );
         return zCALL_ERROR;
      }

      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         return zCURSOR_NULL;
      }

      boolean bTestCsrResult = (lControl & zTEST_CSR_RESULT) != 0;
      boolean bQualScopeOI = (lControl & zQUAL_SCOPE_OI) != 0;
      lControl &= 0x7;
      if ( bTestCsrResult )  // we're testing the cursor ... not setting it!
      {
         if ( bQualScopeOI )
         {
            if ( lControl == zPOS_FIRST || lControl == zPOS_LAST )
            {
               if ( StringUtils.isEmpty( stringSearchValue ) )
               {
                  return cursor.hasAnyWithinOi( ) ? zCURSOR_SET : zCURSOR_NULL;
               }
               else
               {
                  return cursor.hasAnyWithinOi( attributeName, stringSearchValue ) ? zCURSOR_SET : zCURSOR_NULL;
               }
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.hasNext( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.hasPrev( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            {
                // error!!!
            }
         }
         else
         {
            if ( lControl == zPOS_FIRST || lControl == zPOS_LAST )
            {
               if ( StringUtils.isEmpty( stringSearchValue ) )
               {
                  return cursor.hasAny( scopingEntity ) ? zCURSOR_SET : zCURSOR_NULL;
               }
               else
               {
                  return cursor.hasAny( attributeName, stringSearchValue, scopingEntity ) ? zCURSOR_SET : zCURSOR_NULL;
               }
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.hasNext( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.hasPrev( ) ? zCURSOR_SET : zCURSOR_UNCHANGED;
            }
            else
            {
               // error!
            }
         }
      }
      else  // we're not testing the cursor ... setting it!
      {
         if ( bQualScopeOI )
         {
            if ( lControl == zPOS_FIRST )
            {
               if ( StringUtils.isEmpty( stringSearchValue ) )
               {
                  return cursor.setFirstWithinOi( ).toInt();
               }
               else
               {
                  return cursor.setFirstWithinOi( attributeName, stringSearchValue ).toInt();
               }
            }
            else
            if ( lControl == zPOS_NEXT )
            {
               return cursor.setNextWithinOi( ).toInt();
            }
            else
            if ( lControl == zPOS_PREV )
            {
               return cursor.setPrevWithinOi( ).toInt();
            }
            else
            if ( lControl == zPOS_LAST )
            {
               if ( StringUtils.isEmpty( stringSearchValue ) )
               {
                  return cursor.setLastWithinOi( ).toInt();
               }
               else
               {
                  return cursor.setLastWithinOi( attributeName, stringSearchValue ).toInt();
               }
            }
            else
            {
               // error!
            }
         }
         else
         {
            if ( StringUtils.isEmpty( stringSearchValue ) )
            {
               if ( lControl == zPOS_FIRST )
               {
                  return cursor.setFirst( scopingEntity ).toInt();
               }
               else
               if ( lControl == zPOS_NEXT )
               {
                  return cursor.setNext( ).toInt(); // , scopingEntity );
               }
               else
               if ( lControl == zPOS_PREV )
               {
                  return cursor.setPrev( ).toInt(); // , scopingEntity );
               }
               else
               if ( lControl == zPOS_LAST )
               {
                  return cursor.setLast( scopingEntity ).toInt();
               }
            }
            else
            {
               if ( lControl == zPOS_FIRST )
               {
                  return cursor.setFirst( attributeName, stringSearchValue, scopingEntity ).toInt();
               }
               else
               if ( lControl == zPOS_NEXT )
               {
                  return cursor.setNext( attributeName, stringSearchValue, scopingEntity ).toInt(); // , scopingEntity );
               }
               else
               if ( lControl == zPOS_PREV )
               {
                  return cursor.setPrev( attributeName, stringSearchValue, scopingEntity ).toInt(); // , scopingEntity );
               }
               else
               if ( lControl == zPOS_LAST )
               {
                  return cursor.setLast( attributeName, stringSearchValue, scopingEntity ).toInt();
               }
               else
               {
                  // error
               }
            }
         }
      }

      return zCURSOR_NULL;
   }

   public int TraceLineS( String label, String text )
   {
      task.log( ).info( label + " " + text );
      return 0;
   }

   public int TraceLineI( String label, int i )
   {
      return TraceLineS( label, Integer.toString( i ) );
   }

   public int TraceLine( String strFormat, Object... arr )
   {
      String tgtString = String.format( strFormat, arr );
      return TraceLineS( tgtString, "" );
   }

   //  RETURNS: Entity key
   //           0 on error (cursor undefined, etc.)
   protected int GetEntityKey( View view, String entityName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = 0;
      }
      else
      {
         nRC = (int) cursor.getEntityKey( );
      }

      return nRC;
   }

   //  RETURNS:    0           - Length successfully retrieved
   //              zCALL_ERROR - Error retrieving length
   protected int GetAttributeLength( MutableInt miLth, View view, String entityName, String attributeName )
   {
      int nRC;
      int nLth;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
         nLth = 0;
      }
      else
      {
         nLth = cursor.getEntityDef( ).getAttribute( attributeName ).getLength( );
         nRC = 0;
      }

       miLth.setValue( nLth );
       return nRC;
   }

   //  RETURNS:    0           - Length successfully retrieved
   //              zCALL_ERROR - Error retrieving length
   protected int GetAttributeDisplayLength( int nAttributeLth, View view,
                                            String entityName, String attributeName, String contextName )
   {
      // TODO ... DG, this is probably not the correct length.
      int nLth;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nLth = zCALL_ERROR;
      }
      else
      {
         nLth = cursor.getEntityDef( ).getAttribute( attributeName ).getLength( );
      }

      return nLth;
   }

   //  RETURNS:    0           - Attribute successfully retrieved
   //              -1          - Returned 0 in lplReturnInteger, the attribute value was null.
   //              zCALL_ERROR - Error retrieving attribute
   protected int GetIntegerFromAttribute( View view, String entityName, String attributeName )
   {
      Integer k;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         k = 0;
      }
      else
      {
         //k = cursor.getIntegerFromAttribute( attributeName );
         AttributeInstance attrib = cursor.getAttribute( attributeName );
         k = attrib.getInteger();
         if ( k == null )
         {
            k = 0;
         }
      }

      return k;
   }

   protected int GetIntegerFromAttribute( MutableInt i, View view, String entityName, String attributeName )
   {
      int nRC;
      Integer k;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         k = 0;
         nRC = zCALL_ERROR;
      }
      else
      {
         //k = cursor.getIntegerFromAttribute( attributeName );
         AttributeInstance attrib = cursor.getAttribute( attributeName );
         k = attrib.getInteger();
         if ( k == null )
         {
            k = 0;
            nRC = -1;
         }
         else
         {
            nRC = 0;
         }
      }

      i.setValue( k );
      return k;
   }

   protected int GetIntegerFromAttribute( int i, View view, String entityName, String attributeName )
   {
      return GetIntegerFromAttribute( view, entityName, attributeName );
   }

   //  RETURNS:    0           - Attribute successfully retrieved
   //              -1          - Returned null string in pchReturnString, the attribute value was null.
   //              -2          - Returned null string in pchReturnString, the entity was null.
   //              zCALL_ERROR - Error retrieving attribute
   protected String GetStringFromAttribute( View view, String entityName, String attributeName )
   {
      String s;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         s = "";
      }
      else
      {
         AttributeInstance attrib = cursor.getAttribute( attributeName );
         s = attrib.getString();


         /*  dgc 2013-08-10 This shouldn't be necessary because it's handled by the domain.  If there's
          * an error then it is in the domain, not here.

         // Because in our vml code we compare a null to "", we should return a "" instead of null.
         if ( s == null )
         {
            s = "";
         }
         */
      }

      return s;
   }

   protected String GetStringFromAttribute( String string, View view, String entityName, String attributeName )
   {
      String s = "";

      // Following kept calling recursive.
      //return GetStringFromAttribute( s, view, entityName, attributeName );
      return GetStringFromAttribute( view, entityName, attributeName );
   }

   protected int GetStringFromAttribute( StringBuilder sbReturn, View view, String entityName, String attributeName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = -2;
         sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      }
      else
      {
         AttributeInstance attr = cursor.getAttribute( attributeName );
         String s;
         s = attr.getString();

         if ( s == null )
         {
            s = "";
            nRC = -1;
         }
         else
         {
             nRC = 0;
         }

         sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
         sbReturn.append( s );
      }

      return nRC;
   }

   //  RETURNS:    0           - Address successfully retrieved
   //              zCALL_ERROR - Error retrieving attribute
   protected String GetAddrForAttribute( String string, View view, String entityName, String attributeName )
   {
      return GetStringFromAttribute( string, view, entityName, attributeName );
   }

   //  RETURNS:     0          - Attribute successfully retrieved
   //              -1          - Returned null string in pchReturnString, the attribute value was null.
   //              -2          - Returned null string in pchReturnString, the entity was null.
   //              zCALL_ERROR - Error retrieving attribute
   protected String GetStringFromAttributeByContext( String returnString, View view, String entityName, String attributeName,
                                                     String context, int length )
   {
      String s;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         s = "";
      }
      else
      {
         s = cursor.getStringFromAttribute( attributeName, context );

         // Because in our vml code we compare a null to "", we should return a "" instead of null.
         if ( s == null )
         {
            s = "";
         }
      }

      return s;
   }

   protected StringBuilder GetStringFromAttributeByContext( StringBuilder sbReturn, View view,
                                                            String entityName, String attributeName,
                                                            String context, int maxLength )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = -2;
         sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      }
      else
      {
         String s = cursor.getStringFromAttribute( attributeName, context );
         if ( s == null )
         {
            s = "";
            nRC = -1;
         }
         else
         {
            nRC = 0;
         }

         sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
         sbReturn.append( s );

      }

      return sbReturn;
   }

   //  RETURNS:    0           - Attribute successfully retrieved
   //              -1          - Returned 0 in lpdReturnDecimal, the attribute value was null.
   //              zCALL_ERROR - Error retrieving attribute
   protected int GetDecimalFromAttribute( MutableDouble returnDecimalValue, View view,
                                          String entityName, String attributeName )
   {
      int nRC;
      Double d;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
         d = 0.0;
      }
      else
      {
         d = cursor.getDoubleFromAttribute( attributeName );
         if ( d == null )
         {
            nRC = -1;
            d = 0.0;
         }
         else
         {
            nRC = 0;
         }
      }

      returnDecimalValue.setValue( d );
      return nRC;
   }

   protected Double GetDecimalFromAttribute( Double decimalValue, View view,
                                             String entityName, String attributeName )
   {
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         decimalValue = 0.0;
      }
      else
      {
         decimalValue = cursor.getDoubleFromAttribute( attributeName );
      }

      // KJS 05/26/10 - Having a problem in my derived attribute when this returns a null.
      if ( decimalValue == null )
      {
         decimalValue = 0.0;
      }

      return decimalValue;
   }

   protected int SysConvertDecimalToString( Object dDecimalValue, StringBuilder sbReturn, int ulNumberOfDecimals )
   {
      sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      String s = dDecimalValue.toString();

      // If string has a decimal point, truncate it.
      int idx = s.indexOf( '.' );
      if ( idx > -1 )
      {
         s = s.substring( 0, Math.min( s.length( ) - 1, idx + ulNumberOfDecimals ) );
      }

      sbReturn.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturn.append( s );
      return 0;
   }

   protected int SysConvertStringToDecimal( String stringValue, MutableDouble returnDouble )
   {
      returnDouble.setValue( Double.parseDouble( stringValue ) );
      return 0;
   }

   //  RETURNS:    1 - Attr > decimal
   //              0 - Attributes are logically equal
   //             -1 - Attr < decimal
   //    zCALL_ERROR - Error in call
   protected int CompareAttributeToDecimal( View view, String entityName, String attributeName, double d )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         return zCALL_ERROR;
      }
      else
      {
         Double d1 = cursor.getDoubleFromAttribute( attributeName );
         if ( d1 == null )
         {
            d1 = 0.0;
         }

         if ( d1 > d )
         {
            return 1;
         }
         else
         if ( d1 < d )
         {
            return -1;
         }
         else
         {
            return 0;
         }
      }
   }

   //
   // lpVariable       - pointer to the variable to receive the the attribute value.
   // pulReturnLth     - Used to return the length of blobs.
   // cVariableType    - Zeidon data type, indicates what lpVariable pointer to.
   // ulVariableLth    - Indicates length of variable that is pointed to by lpVariable.
   //                    Only used when cVariableType is zTYPE_STRING or zTYPE_BLOB.
   // lpView           - View to Object instance
   // cpcEntityName    - Entity name containing the attribute to get
   // cpcAttributeName - Attribute name to retrieve.
   // cpcContextName   - Optional context name, used in conjunction
   //                    with the domain associated with the specified attribute.
   // nFlag            - Should be set to zACCEPT_NULL_ENTITY, if user wants to allow
   //                    a return code of zNULL_ENTITY, otherwise should be 0. If this
   //                    flag is zero and the entity is null, a zCALL_ERROR is returned.
   //                    zUSE_DEFAULT_CONTEXT indicates that the default Context of the
   //                    Domain associated with the attribute will be used in processing
   //                    this request.  Any value specified by the cpcContextName
   //                    parameter is ignored.
   //
   // RETURNS: zVAR_SET     (0)  - Attribute successfully retrieved
   //          zVAR_NULL    (-1) - Indicates that the attribute value is a null value
   //          zENTITY_NULL (-2) - Indicates that the entity is null ... only returned when
   //                              zACCEPT_NULL_ENTITY (1) is specified in the control parameter.
   //          zCALL_ERROR       - error updating attribute
   protected int GetVariableFromAttribute( StringBuilder sb, MutableInt returnLth, char variableType, int variableLth, View view,
                                           String entityName, String attributeName, String context, int control )
   {
      sb.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.

      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         returnLth.setValue( 0 );
         return (control & zACCEPT_NULL_ENTITY) != 0 ? zENTITY_NULL : zCALL_ERROR;
      }
      else
      {
         String s;
         if ( StringUtils.isBlank( context ) && control != zUSE_DEFAULT_CONTEXT )
         {
             s = cursor.getStringFromAttribute( attributeName );
         }
         else
         {
             s = cursor.getStringFromAttribute( attributeName, context );
         }

         if ( s == null )
         {
            returnLth.setValue( 0 );
            return zVAR_NULL;
         }

         if ( variableLth > 0 )
         {
            variableLth--;
         }

         // DKS 2013.01.18 re-activated the test for variable length.
         // KJS 10/20/11 - I am commenting out these lines because they are causing
         // me problems because getStringFromArribute is returning the external value not
         // internal when there is no context.
         if ( variableLth > 0 && variableLth < s.length( ) )
         {
            sb.append( s.substring( 0, variableLth ) );
         }
         else
         {
            sb.append( s );
         }

         returnLth.setValue( sb.length( ) );
         return zVAR_SET;
      }
   }

   protected int GetVariableFromAttribute( StringBuilder sb, int returnLth, char variableType, int variableLth, View view,
                                           String entityName, String attributeName, String context, int control )
   {
      MutableInt i = new MutableInt( returnLth );
      return GetVariableFromAttribute( sb, i, variableType, variableLth, view, entityName, attributeName, context, control );
   }

   protected String GetVariableFromAttribute( String s, int returnLth, char variableType, int variableLth, View view,
                                              String entityName, String attributeName, String context, int control )
   {
      StringBuilder sb = new StringBuilder();
      MutableInt i = new MutableInt( returnLth );
      GetVariableFromAttribute( sb, i, variableType, variableLth, view, entityName, attributeName, context, control );
      return sb.toString();
   }

   protected String GetVariableFromAttribute( int returnLth, char variableType, int variableLth, View view,
                                              String entityName, String attributeName, String context, int control )
   {
      StringBuilder sb = new StringBuilder();
      MutableInt i = new MutableInt( returnLth );
      GetVariableFromAttribute( sb, i, variableType, variableLth, view, entityName, attributeName, context, control );
      return sb.toString();
   }

   protected int GetVariableFromAttribute( MutableInt lValue, int returnLth, char variableType, int variableLth, View view,
                                           String entityName, String attributeName, String context, int control )
   {
      int nRC;
      int i;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         i = 0;
         nRC = zCURSOR_NULL;
      }
      else
      {
         if ( StringUtils.isBlank( context ) )
         {
             i = cursor.getIntegerFromAttribute( attributeName );
         }
         else
         {
             i = cursor.getIntegerFromAttribute( attributeName, context );
         }

         nRC = 0;
      }

      lValue.setValue( i );
      return nRC;
   }

   protected int GetVariableFromAttribute( int returnValue, int returnLth, char variableType, int variableLth, View view,
                                           String entityName, String attributeName, String context, int control )
   {
      MutableInt i = new MutableInt( );
      GetVariableFromAttribute( i, returnLth, variableType, variableLth, view, entityName, attributeName, context, control );
      return i.intValue();
   }

   //  RETURNS: zCURSOR_NULL         Cursor position for Entity is NULL
   //           zCURSOR_UNDEFINED    Cursor position for Entity is undefined
   //           zCURSOR_SET          Cursor position for Entity is set
   //           zCALL_ERROR          Error in call
   protected int CheckExistenceOfEntity( View view, String entityName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = cursor.checkExistenceOfEntity().toInt();
      }

      return nRC;
   }

   //  RETURNS:    0           - Entity Instance created
   //              zCALL_ERROR - Error creating entity instance
   protected int CreateEntity( View view, String entityName, int pos )
   {
      view.cursor( entityName ).createEntity( CURSOR_POS.get( pos ) );
      return 0;
   }

   //  RETURNS:    zCURSOR_... - Entity deleted, and this is the status for setting the cursor.
   //                            These return codes are shown under the operations for SetCursor...Entity.
   //              zCALL_ERROR - Error deleting entity instance
   protected int DeleteEntity( View view, String entityName, int pos )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = cursor.deleteEntity( CURSOR_POS.get( pos ) ).toInt();
      }

      return nRC;
   }

   //  RETURNS:    0           - Attributes successfully set
   //              zCALL_ERROR - Error setting attributes
   protected int SetMatchingAttributesByName( View tgtView, String tgtEntity,
                                              View srcView, String srcEntity, int control )
   {
      int nRC;
      EntityCursor tgtCursor = tgtView.cursor( tgtEntity );
      EntityCursor srcCursor = srcView.cursor( srcEntity );
      if ( tgtCursor.isNull() || srcCursor.isNull() )
      {
         return zCALL_ERROR;
      }

      EnumSet<SetMatchingFlags> flags = EnumSet.noneOf( SetMatchingFlags.class );
      if ( (control & zSET_KEYS) != 0 )
      {
          flags.add( SetMatchingFlags.fSET_KEYS );
      }

      if ( (control & zSET_NOTNULL) != 0 )
      {
          flags.add( SetMatchingFlags.fSET_NOTNULL );
      }

      if ( (control & zSET_SRCNOTNULL) != 0 )
      {
          flags.add( SetMatchingFlags.fSET_SRCNOTNULL );
      }

      nRC = tgtCursor.setMatchingAttributesByName( srcView.getCursor( srcEntity ), flags );

      return nRC;
   }

   // RETURNS:    0 - Attribute value has been set
   //   zCALL_ERROR - Error in call
   protected int StoreValueInRecord( View view, String entityName, String attributeName, Object value, int length )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         //if ( value instanceof DateTime )
         //    return externalValue;
         cursor.setAttribute( attributeName, value );
      }

      return 0;
   }

   protected int StoreStringInRecord( View view, String entityName, String attributeName, String value )
   {
      return StoreValueInRecord( view, entityName, attributeName, value, 0 );
   }

   //  RETURNS: zCURSOR_NULL      - No entity instances exist
   //           zCURSOR_UNCHANGED - Entity cursor unchanged
   //           zCURSOR_SET       - Entity position established
   //           zCALL_ERROR       - Error in call
   protected int SetCursorFirstEntityByString( View view, String entityName, String attributeName, String stringValue, String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = cursor.setFirst( attributeName, stringValue, scopingEntity ).toInt();
      }

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL          - No entity instances exist
   //           zCURSOR_UNCHANGED     - Entity cursor unchanged
   //           zCURSOR_SET           - Entity position established
   //           zCURSOR_SET_NEWPARENT - Entity position changed within new parent
   //           zCALL_ERROR           - Error in call
   protected int SetCursorNextEntityByString( View view, String entityName, String attributeName, String stringValue, String scopingEntity )
   {
      // TODO - need setNext using scopingEntity???
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = cursor.setNext( attributeName, stringValue, scopingEntity ).toInt();
      }

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL      - No entity instances exist
   //           zCURSOR_UNCHANGED - Entity cursor unchanged
   //           zCURSOR_SET       - Entity position established
   //           zCALL_ERROR       - Error in call
   public static int  SetCursorFirstEntityByInteger( View view, String entityName, String attributeName, int lID, String scopingEntity )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = cursor.setFirst( attributeName, lID, scopingEntity ).toInt();
      }

      return nRC;
   }

   //  RETURNS: zCURSOR_NULL          - No entity instances exist
   //           zCURSOR_UNCHANGED     - Entity cursor unchanged
   //           zCURSOR_SET           - Entity position established
   //           zCURSOR_SET_NEWPARENT - Entity position changed within new parent
   //           zCALL_ERROR           - Error in call
   public static int  SetCursorNextEntityByInteger( View view, String entityName, String attributeName, int lID, String scopingEntity )
   {
      // TODO - need setNext using scopingEntity???
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny( scopingEntity ) == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = cursor.setNext( attributeName, lID, scopingEntity ).toInt(); // , scopingEntity );
      }

      return nRC;
   }

   //  RETURNS:    1 - Attr > string
   //              0 - Attributes are logically equal
   //             -1 - Attr < string
   //            -16 - Error in call
   protected int CompareAttributeToString( View view, String entityName, String attributeName, String value )
   {
      EntityCursor cursor = view.cursor( entityName );

      if ( cursor.isNull() )
      {
         return zCURSOR_NULL;
      }
      else
      {
         int nRC = cursor.compareAttribute( attributeName, value );
         return nRC == 0 ? 0 : nRC > 0 ? 1 : -1;
      }
   }

   protected int CompareAttributeToString( View view, String entityName, String attributeName, StringBuilder sbValue )
   {
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         return zCURSOR_NULL;
      }
      else
      {
         int nRC = cursor.compareAttribute( attributeName, sbValue.toString( ) );
         return nRC == 0 ? 0 : nRC > 0 ? 1 : -1;
      }
   }

   //  RETURNS:    1 - Attr > integer
   //              0 - Attributes are logically equal
   //             -1 - Attr < integer
   //            -16 - Error in call
   protected int CompareAttributeToInteger( View view, String entityName, String attributeName, Integer value )
   {
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         return zCURSOR_NULL;
      }
      else
      {
         int nRC = cursor.compareAttribute( attributeName, value );
         return nRC == 0 ? 0 : nRC > 0 ? 1 : -1;
      }
   }

   //  RETURNS:    1 - Target attr > source attr
   //              0 - Attributes are logically equal
   //             -1 - Target attr < source attr
   //            -16 - Error in call
   protected int CompareAttributeToAttribute( View view1, String entityName1, String attributeName1, View view2, String entityName2, String attributeName2 )
   {
      EntityCursor cursor1 = view1.cursor( entityName1 );
      EntityCursor cursor2 = view2.cursor( entityName2 );
      if ( cursor1.isNull() || cursor2.isNull() )
      {
         return zCURSOR_NULL;
      }
      else
      {
         int nRC = cursor1.compareAttribute( attributeName1, cursor2.getEntityInstance(), attributeName2 );
         return nRC == 0 ? 0 : nRC > 0 ? 1 : -1;
      }
   }

   protected int DropObjectInstance( View view )
   {
      view.drop();
   // view = null;
      return 0;
   }

   //  RETURNS:    0           - Attribute successfully set
   //              zCALL_ERROR - Error updating attribute
   protected int SetAttributeFromString( View view, String entityName, String attributeName, String value)
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, value );
      }

      return nRC;
   }

   protected int SetAttributeFromString( View view, String entityName, String attributeName, StringBuilder sbValue )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, sbValue.toString( ) );
      }

      return nRC;
   }

   //  RETURNS:    0           - Attribute successfully set
   //              zCALL_ERROR - Error updating attribute
   protected int SetAttributeFromInteger( View view, String entityName, String attributeName, int value )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, value );
      }

      return nRC;
   }

   //  RETURNS:    0           - Attribute successfully set
   //              zCALL_ERROR - Error updating attribute
   protected int SetAttributeFromDecimal( View view, String entityName, String attributeName, double value )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, value );
      }

      return nRC;
   }

   //  RETURNS:    0           - Attribute successfully set
   //              zCALL_ERROR - Error updating attribute
   protected int SetAttributeFromAttribute( View tgtView, String tgtEntity, String tgtAttribute,
                                            View srcView, String srcEntity, String srcAttribute )
   {
      int nRC;
      EntityCursor tgtCursor = tgtView.cursor( tgtEntity );
      EntityCursor srcCursor = srcView.cursor( srcEntity );
      if ( tgtCursor.isNull( ) || srcCursor.isNull( ) )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = 0;
         tgtCursor.setAttributeFromAttribute( tgtAttribute, srcView, srcEntity, srcAttribute );
      }

      return nRC;
   }

   // RETURNS: 0           - Attribute successfully set
   //          zCALL_ERROR - error updating attribute
   protected int SetAttributeFromVariable( View view, String entityName, String attributeName, Object value,
                                           char variableType, int maxLth, String contextName, int control )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, value );
      }

      return nRC;
   }

   protected int AddToAttributeFromDecimal( View view, String entityName, String attributeName, double journalActual )
   {
      // TODO DG???
      return 0;
   }

   protected int SetAttributeFromCurrentDateTime( View view, String entityName, String attributeName )
   {
      // TODO - Create code.
      return 0;
   }

   //  RETURNS:    0           - Entity Attributes successfully set
   //              zCALL_ERROR - Error updating attribute
   protected int SetBlobFromOI( View view, String entityName, String attributeName, View srcView, int control )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, srcView );
      }

      return nRC;
   }

   //  RETURNS:    0           - Attribute successfully set
   //              zCALL_ERROR - Error updating attribute
   protected int  SetAttributeFromBlob( View view, String entityName, String attributeName, CharBuffer blob, int blobLth )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.setAttribute( attributeName, blob, "" );
      }

      return nRC;
   }

   protected int WriteBlobToFile( View view, String entityName, String attributeName, String fileName )
   {
      // TODO:  write blob to file
      return 0;
   }

   protected int PrintTextBlob( View view, String entityName, View printView,
                                String string2, String string3, String string4, int i )
   {
      // TODO Auto-generated method stub
      return 0;
   }

   protected String SysConvertDecimalToString( double decimalNbr, String returnString, int flag )
   {
      returnString = "" + decimalNbr;
   // RESULT = 0;
      return returnString;
   }

   // RETURNS: 0           - Attribute successfully changed
   //          zCALL_ERROR - error updating attribute
   protected int AddToAttributeFromVariable( View view, String entityName, String attributeName,
                                             Object value, char variableType, int maxLth,
                                             String contextName )
   {
      int nRC = 0;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         if ( contextName.equals("Month"))
         {
            int nMonths = ((Number) value).intValue();
            DateTime date = (DateTime) view.cursor(entityName).getInternalAttributeValue(attributeName);
            DateTime date2 = date.plusMonths(nMonths);
            view.cursor(entityName).setAttribute(attributeName, date2);
         }
         else
         if ( contextName.equals("Day"))
         {
            int nDays = ((Number) value).intValue();
            DateTime date = (DateTime) view.cursor(entityName).getInternalAttributeValue(attributeName);
            DateTime date2 = date.plusDays(nDays);
            view.cursor(entityName).setAttribute(attributeName, date2);
         }
         else
         {
            cursor.addToAttribute( attributeName, value );
         }
      }

      return nRC;
   }

   protected int SfActivateSysEmptyOI( zVIEW returnView, String lodDefName, TaskQualification qualView, int control )
   {
      View view = null;
      try
      {
          // Try with ZeidonSystem
          view = qualView.activateEmptyObjectInstance( lodDefName, task.getSystemTask().getApplication() );
      }
      catch ( UnknownLodDefException e )
      {
          // Try with ZeidonTools
          view = qualView.activateEmptyObjectInstance( lodDefName, task.getApplication( "ZeidonTools" ) );
      }

      returnView.setView( view );
      return 0;
   }

   /**
    * Activates an OI from file.  The application for the lodDefName is either ZeidonSystem or ZeidonTools.
    *
    * @param returnView
    * @param lodDefName
    * @param qualView
    * @param fileName
    * @param control
    * @return
    */
    protected int SfActivateSysOI_FromFile( zVIEW returnView, String lodDefName, TaskQualification qualView,
                                            String fileName, int control )
    {
        View view = null;
        try
        {
            // Try with ZeidonSystem
            view = qualView.deserializeOi()
                           .fromFile( fileName )
                           .setFlags( control )
                           .setApplication( task.getSystemTask().getApplication() )
                           .setLodDef( lodDefName )
                           .activateFirst();
        }
        catch ( UnknownLodDefException e )
        {
            // Try with ZeidonTools
            view = qualView.deserializeOi()
                            .fromFile( fileName )
                            .setFlags( control )
                            .setApplication( task.getApplication( "ZeidonTools" ) )
                            .setLodDef( lodDefName )
                            .activateFirst();
        }

        returnView.setView( view );
        return 0;
    }

   //./ ADD NAME=fnValidView
   // Source Module=kzoevlaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  FUNCTION:   fnValidView
   //
   //  ASSUMES:    Task passed is valid
   //
   //  RETURNS:    1 - View valid
   //              0 - View invalid
   //
   /////////////////////////////////////////////////////////////////////////////
   private int fnValidView( Task task, View view )
   {
      if ( isValid( view ) )
      {
         if ( view.getTask() == task )
         {
            return 1;
         }
      }

      //  "KZOEE101 - Invalid View"
      fnIssueCoreError( task, view, 8, 101, 0, "", "" );

      return( 0 );  // return error
   }

   /**
    * Activate empty OI.  Since JOE doesn't care about the difference between single/multiple roots
    * we ignore the flag.
    */
   protected int ActivateEmptyObjectInstance( zVIEW returnView, String lodDefName, TaskQualification qual, int control )
   {
      View view = qual.activateEmptyObjectInstance( lodDefName, qual.getApplication() );
      returnView.setView( view );
      return 0;
   }

   protected int ActivateObjectInstance( zVIEW returnView, String lodDefName, View qual,
                                         View activateQualificationView, int control )
   {
      int nRC = 0;

  // if ( control == 0 )
  //     control = zASYNCHRONOUS;
      // A couple of times in Windows vml we have an activate with zSINGLE + zLEVE_APPLICATION. That is not
      // applicable in the JOE so ignore and just use zSINGLE.
      if ( control == 4 )
    	  control = 0;

      View view = qual.activateObjectInstance( lodDefName, activateQualificationView, ACTIVATE_CONTROL.get( control ) );
      LodDef lodDef = view.getLodDef();
      returnView.setView( view );
      switch ( view.cursor( lodDef.getRoot().getName() ).getEntityCount() )
      {
         case 0:
            nRC = -1;
            break;

         case 1:
            nRC = 0;
            break;

         default:
            nRC = 1;
            break;
      }

      TraceLineS( "Display object instance from ActivateObjectInstance for OD: ", view.getLodDef().getName() );
   // DisplayObjectInstance( view, "", "" );
      return nRC;
   }

   protected int ActivateObjectInstance( zVIEW returnView, String lodDefName, TaskQualification qual,
                                         View activateQualificationView, int control )
   {
      if ( ! ( qual instanceof View ) )
      {
         throw new ZeidonException( "qual is not a valid qualification view: " + qual.toString( ) );
      }

      return ActivateObjectInstance( returnView, lodDefName, ((View) qual), activateQualificationView, control );
   }

   protected int ActivateObjectInstance( zVIEW returnView, String lodDefName, TaskQualification qual, int view, int control )
   {
      return ActivateObjectInstance( returnView, lodDefName, qual, null, control );
   }

   public int ActivateOI_FromFile( zVIEW view, String lodDefName, View qualView, String fileName, int control )
   {
       view.setView( task.deserializeOi()
                         .fromFile( fileName )
                         .setLodDef( lodDefName )
                         .setFlags( control )
                         .setApplication( qualView == null ? task.getApplication() : qualView.getApplication() )
                         .activateFirst() );
      return 0;
   }

   protected int ActivateOI_FromXML_File( zVIEW view, String lodDefName, View viewToWindow, String fileName, int control )
   {
      // TODO Auto-generated method stub
      int nRC = 0; // view.activateOiFromXML_File( view, lodDefName, viewToWindow, fileName, control );
      return nRC;
   }

   protected int ActivateOI_FromOI( zVIEW returnView, View view, int control )
   {
      returnView.setView( view.activateOiFromOi( ACTIVATE_CONTROL.get( control ) ) );
      return 0;
   }

   protected int ActivateOI_FromOI( zVIEW returnView, zVIEW view, int control )
   {
      returnView.setView( view.activateOiFromOi( ACTIVATE_CONTROL.get( control ) ) );
      return 0;
   }

   protected int ActivateOI_FromOI_ForTask( zVIEW returnView, View srcView, View taskView, int control )
   {
      if ( isValid( taskView ) )
      {
         returnView.setView( taskView.activateOiFromOi( ACTIVATE_CONTROL.get( control ) ) );
      }
      else
      {
         returnView.setView( srcView.activateOiFromOi( ACTIVATE_CONTROL.get( control ) ) );
      }

      return 0;
   }

   protected int CommitOI_ToFile( View view, String fileName, int control )
   {
      view.writeOiToFile( fileName, WriteOiFlags.convertLongFlags( control ) );
      return 0;
   }

   protected int CommitOI_ToXML_File( View view, String fileName, int control )
   {
      view.writeOiToXml( fileName, WriteOiFlags.convertLongFlags( control ) );
      return 0;
   }

   //  RETURNS:   -1 - Blob is NULL.  No OI is returned.
   //              0 - Object instance activated, single root found
   //              1 - Object instance activated, multiple roots found.  If
   //                  zSINGLE was specified, only the first was activated.
   //    zCALL_ERROR - Error Activating object instance
   protected int SetOI_FromBlob( zVIEW returnView, StringBuilder sbLodDefName, TaskQualification qualView,
                                 View srcView, String srcEntity, String srcAttribute, int control )
   {
      EntityCursor cursor = srcView.cursor( srcEntity );
      if ( cursor.isNull() )
      {
         return zCALL_ERROR;
      }

      Application application = qualView.getApplication();
      Blob blob = srcView.cursor( srcEntity ).getBlobFromAttribute( srcAttribute );
      if ( blob == null )
      {
         return -1;
      }

      View v = qualView.deserializeOi()
                       .fromInputStream( new ByteArrayInputStream( blob.getBytes() ) )
                       .setFlags( ACTIVATE_CONTROL.get(control) )
                       .setApplication( application )
                       .activateFirst();

      returnView.setView( v );
      sbLodDefName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbLodDefName.append( v.getLodDef().getName() );
      return 0;
   }

   protected String SetOI_FromBlob( zVIEW returnView, String lodDefName, TaskQualification qualView,
                                    View srcView, String srcEntity, String srcAttribute, int control )
   {
      StringBuilder sb = new StringBuilder( lodDefName );
      int rc = SetOI_FromBlob( returnView, sb, qualView, srcView, srcEntity, srcAttribute, control );
      if ( rc < 0 )
      {
         return null;
      }
      else
      {
         return sb.toString( );
      }
   }

   // Not sure why but we have a lot of calls to SetOI_FromBlob where the lodDefName is
   // 0. We never seem to use this value... So I am adding this override.
   //SetOI_FromBlob( mPerson, 0, mChurch, mChurch, "ACR_BatchItem", "BlobOI", zSINGLE );
   protected String SetOI_FromBlob( zVIEW returnView, int lodDefName, TaskQualification qualView,
                                    View srcView, String srcEntity, String srcAttribute, int control )
   {
      StringBuilder sb = new StringBuilder( );
      int rc = SetOI_FromBlob( returnView, sb, qualView, srcView, srcEntity, srcAttribute, control );
      if ( rc < 0 )
      {
         return null;
      }
      else
      {
         return sb.toString( );
      }
   }

   protected int CommitObjectInstance( View view ) throws InvalidViewException
   {
	  if ( !isValid(view) )
		  throw new InvalidViewException( "Commit" );

      view.commit( );
      return 0;
   }

   // TODO - DG ... Create code if not already done.
   //./ ADD NAME=GenerateQualFromEntityList
   // Source Module=kzoeoiaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      GenerateQualFromEntityList
   //
   //  PURPOSE:    Generates a qualification object to load all the entities in
   //              the supplied list of root entities.
   //
   //  PARAMETERS:  pvQual        - Qual view that is passed back.
   //               vEntityList   - View containing the entity list.
   //               pchEntityName - Name of the entity.
   //               pchScoping    - Scoping entity.
   //               lControl      - Not currently used.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   GenerateQualFromEntityList( zVIEW  returnView,
                               View   vEntityList,
                               String pchEntityName,
                               String pchScoping,
                               int    lControl )
   {
       returnView.setView( QualificationBuilder.generateQualFromEntityList( vEntityList, pchEntityName, pchScoping ) );
       return 0;
   }

   protected int ObjectInstanceUpdated( View view )
   {
      return view.isUpdated() ? 1 : 0;
   }

   /**
    * Commit the views in the view cluster.
    *
    * @param viewCluster
    * @param errorIndex - ignored.
    * @return
    */
   protected int CommitMultipleObjectInstances( int viewCluster, MutableInt errorIndex )
   {
       List<View> list = getClusterInfo().getViewList( viewCluster );
       task.getTask().commitMultipleOis( list );
       return 0;
   }

   protected int CommitMultipleObjectInstances( MutableInt viewCluster, MutableInt errorIndex )
   {
       List<View> list = getClusterInfo().getViewList( viewCluster.getValue() );
       task.getTask().commitMultipleOis( list );
       return 0;
   }

   protected int CreateViewCluster( View viewToWindow, MutableInt viewCluster )
   {
       ClusterInfo info = getClusterInfo();
       viewCluster.setValue( info.createCluster() );
       return 0;
   }

   protected int AddToViewCluster( int viewCluster, View view, int commitFlags )
   {
       if ( commitFlags != 0 )
           throw new ZeidonException( "Commit flags not supported for AddToViewCluster" );

       if ( view instanceof zVIEW )
           view = ((zVIEW) view).getView();

       getClusterInfo().getViewList( viewCluster ).add( view );
       return 0;
   }

   protected int AddToViewCluster( MutableInt viewCluster, View view, int commitFlags )
   {
       return AddToViewCluster( viewCluster.getValue(), view, commitFlags );
   }

   protected int DropViewCluster( int viewCluster )
   {
       getClusterInfo().deleteCluster( viewCluster );
       return 0;
   }

   protected int DropViewCluster( MutableInt viewCluster )
   {
       getClusterInfo().deleteCluster( viewCluster.getValue() );
       return 0;
   }

   //  RETURNS:    0           - Temporal Subobject version successfully accepted
   //              zCALL_ERROR - Error accepting temporal version of Subobject
   protected int AcceptSubobject( View view, String entityName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )  // Kelly ... I don't know which way to go with this one  dks 2013.02.15
         nRC = zCURSOR_NULL;
      else
      {
         if ( cursor.isVersioned( ) )
         {
            cursor.acceptSubobject( );
            nRC = 0;
         }
         else
         {
            nRC = zCALL_ERROR;
         }
      }

      return nRC;
   }

   //  RETURNS:    zCURSOR_SET - The version was canceled and the cursor has been re-established on the previous version
   //              zCURSOR_UNDEFINED - The version was canceled and the temporal entity has been deleted!
   //              zCALL_ERROR - Error dropping current version of Subobject
   protected int CancelSubobject( View view, String entityName )
   {

      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         if ( cursor.isVersioned( ) )
         {
            cursor.cancelSubobject( );
            nRC = 0;
         }
         else
         {
            nRC = zCALL_ERROR;
         }
      }

      return nRC;
   }

   protected int DropView( View view )
   {
      view.drop( );
      return 0;
   }

   /**
    * Drop all the views in this task that reference the same OI.
    *
    * This potentially won't drop all views if there are views in other
    * tasks that reference this view but it will make some educated
    * guesses on which tasks to look.
    *
    * @param anyView
    * @param szQueryOIName
    * @param false1
    * @return
    */
   protected int DropViewObject( View anyView, String szQueryOIName, int false1 )
   {
       long id = anyView.getOiId();
       if ( anyView.getTask() != task.getTask() )
       {
           for ( View view : anyView.getTask().getViewList() )
           {
               if ( view.getOiId() == id )
                   view.drop();
           }
       }

       if ( task.getSystemTask() != task.getTask() )
       {
           for ( View view : task.getSystemTask().getViewList() )
           {
               if ( view.getOiId() == id )
                   view.drop();
           }
       }

       for ( View view : task.getTask().getViewList() )
       {
           if ( view.getOiId() == id )
               view.drop();
       }

       return 0;
   }

   //  RETURNS:  0            sort successfull
   //            zCALL_ERROR  error in call
   public static int OrderEntityForView( View view, String entityName, String orderKeys )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny() == false )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = 0;
         cursor.orderEntities( orderKeys );
      }

      return nRC;
   }

   //  RETURNS:    zCURSOR_... - Entity deleted, and this is the status for setting the cursor.
   //                            These return codes are shown under the operations for SetCursor...Entity.
   //              zCALL_ERROR - Error excluding entity instance
   protected int ExcludeEntity( View view, String entityName, int pos )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.excludeEntity( CURSOR_POS.get( pos ) );
      }

      return 0;
   }

   //  RETURNS:    0           - Target subobject inclusion successful
   //              zCALL_ERROR - Error including subobject instance
   protected int IncludeSubobjectFromSubobject( View tgtView, String tgtEntityName,
                                                View srcView, String srcEntityName, int pos )
   {
      int nRC;
      EntityCursor tgtCursor = tgtView.cursor( tgtEntityName );
      EntityCursor srcCursor = srcView.cursor( srcEntityName );
      if ( srcCursor.isNull() )
      {
         nRC = zCURSOR_NULL;
      }
      else
      {
         nRC = 0;
         tgtView.cursor( tgtEntityName ).includeSubobject( srcView.cursor( srcEntityName ), CURSOR_POS.get( pos ) );
      }

      return nRC;
   }

   //  RETURNS:    0           - Entity Instance created
   //              zCALL_ERROR - Error creating entity instance
   protected int CreateTemporalEntity( View view, String entityName, int pos )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor == null ) // I'm not sure we want any test here...
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.createTemporalEntity( CURSOR_POS.get( pos ) );
      }

      return nRC;
   }

   //  RETURNS:    0           - Subobject SuccessfullyVersioned
   //              zCALL_ERROR - Error versioning Subobject
   protected int CreateTemporalSubobjectVersion( View view, String entityName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor == null ) // I'm not sure we want any test here...
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = 0;
         cursor.createTemporalSubobjectVersion( );
      }

      return nRC;
   }

   protected int SetViewReadOnly( View view )
   {
      view.setReadOnly( true );
      return 0;
   }

   protected void DisplayEntityInstance( View view, String entityName )
   {
      // TODO DG???
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() == false && cursor.checkExistenceOfEntity() == CursorResult.SET )
      {
         cursor.logEntity( false );
      }
      else
      {
         task.log().debug( "*** %s: %s", entityName, "<empty?/deleted?>" );
      }
   }

   protected void DisplayObjectInstance( View view, String entityName, String scopingEntity )
   {
      view.logObjectInstance( );
   }

   protected int MiSetInstanceUpdateFlag( View view, int trueFalse )
   {
      // TODO DG
      // view.setInstanceUpdated( trueFalse == 0 ? false : true );
      return 0;
   }

   //  RETURNS: 0 - instance not versioned
   //           1 - instance versioned
   //           zCALL_ERROR           - error on call
   protected int MiEntityVersioned( View view, String entityName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = cursor.isVersioned( ) ? 1 : 0;
      }

      return nRC;
   }

   protected int MiGetUpdateForView( View view )
   {
      int nRC = view.isReadOnly( ) ? 0 : 1;
      return nRC;
   }

   protected int MiGetObjectNameForView( StringBuilder sbObjectName, View view )
   {
      int nRC = 0;
      sbObjectName.setLength( 0 );
      sbObjectName.append( view.getLodDef( ).getName( ) );
      return 0;
   }

   protected String MiGetObjectNameForView( String strObjectName, View view )
   {
      return view.getLodDef( ).getName( );
   }

   protected void SetViewUpdate( View view )
   {
      // TODO DG
      // view.setInstanceUpdated( true );
   }

   //  RETURNS:    0 or greater  - Number of unhidden Entities.
   //              zCALL_ERROR   - Error in call
   protected int CountEntitiesForView( View view, String entityName )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny() == false )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = cursor.getEntityCount( );
      }

      return nRC;
   }

   protected String MiGetParentEntityNameForView( String stringParentEntity, View view, String entityName )
   {
      return view.getLodDef( ).getEntityDef( entityName ).getParent( ).getName( );
   }

   protected int ResetView( View view )
   {
      view.reset();
      return 0;
   }

   public static int AppendPathSeparator( StringBuilder sbDirectoryName )
   {
      char pathSeparator = Config.getSystemProperty( PROP_PATH_SEPARATOR, String.valueOf( Config.DEFAULT_PATH_SEPARATOR ) ).charAt( 0 );
      char replaceCharacter = pathSeparator == '/' ? '\\' : '/';
      String s = sbDirectoryName.toString( );
      s = s.replace( replaceCharacter, pathSeparator );
      int nLth = s.length( );
      sbDirectoryName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbDirectoryName.append( s );
      if ( nLth > 1 && sbDirectoryName.charAt( nLth - 1 ) != pathSeparator )
      {
         sbDirectoryName.insert( nLth++, pathSeparator );
      }

      return nLth;
   }

   public static String GetApplDirectoryFromView( TaskQualification qual, int lControl )
   {
      StringBuilder sbFileName = new StringBuilder();
      GetApplDirectoryFromView( sbFileName, qual, lControl, 256 );
      AppendPathSeparator( sbFileName );
      return sbFileName.toString();
   }

   protected static int GetApplDirectoryFromView( StringBuilder sbFileName, TaskQualification qual, int lControl, int lMaxLth )
   {
      Application app = qual.getApplication( );
      sbFileName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.

      switch ( lControl )
      {
         case zAPPL_NAME:
            sbFileName.append( app.getName( ) );
            break;

         case zAPPL_DIR_LIB:
            sbFileName.append( app.getBinDir( ) );
            break;

         case zAPPL_DIR_OBJECT:
            sbFileName.append( app.getObjectDir( ) );
            break;

         case zAPPL_DIR_LOCAL:
            sbFileName.append( app.getLocalDir( ) );
            break;

         case zAPPL_DIR_SHARED:
            sbFileName.append( app.getSharedDir( ) );
            break;

         case zAPPL_DIR_QLPLR:
            sbFileName.append( app.getQlplrDir( ) );
            break;

         case zAPPL_DIR_SOURCE:
            sbFileName.append( app.getSourceDir( ) );
            break;

         default:
            throw new ZeidonException( "Unknown control value %d", lControl );
      }

      return 0;
   }

   protected static int GetApplDirectoryFromView( StringBuilder sbFileName, View view, int lControl, int lMaxLth )
   {
      return GetApplDirectoryFromView( sbFileName, view.getTask(), lControl, lMaxLth );
   }

   //  RETURNS:          0 - Hierarchical cursor position set
   //          zCALL_ERROR - Error in establishing hierarchical cursor
    protected int DefineHierarchicalCursor( View view, String entityName )
    {
        int nRC;
        EntityCursor cursor = view.cursor( entityName );
        nRC = 0;
        hierInstanceIterator = cursor.getChildrenHier( false ).iterator();
        hierInstanceEntityName = entityName;

        return nRC;
    }

    protected int GetEntityNameForHierarchicalCsr( int lLevel, StringBuilder sbEntityName, int lPos, View view )
    {
        sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
        sbEntityName.append( hierInstanceEntityName );
        return 0;
    }

   //  RETURNS:    0 - OK
   //              zCURSOR_UNDEFINED     - Entity cursor is undefined
   //              zCURSOR_NULL          - Entity cursor is null
   //              zCALL_ERROR
   protected int GetEntityNameForHierarchicalCsr( MutableInt miLevel, StringBuilder sbEntityName, MutableInt miPos, View view )
   {
      int nRC;
      sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      EntityCursor cursor = view.cursor( hierInstanceEntityName );
      if ( cursor.isNull() )
      {
         nRC = zCURSOR_NULL;
         miLevel.setValue( -1 );
      }
      else
      {
         nRC = 0;
         miLevel.setValue( cursor.getLevel( ) );
         sbEntityName.append( hierInstanceEntityName );
         miPos.setValue( 0 );
      }

      // TODO: DG???  miPos.setValue( view.cursor( hierInstanceEntityName ).getPosition( ) );
      return nRC;
   }

   //  RETURNS:
   //    zCURSOR_UNCHANGED          - Hierarchical cursor already positioned on last hierarchical position for view (UNCHANGED)
   //    zCURSOR_SET                - Hierarchical cursor positioned on next hierarchical entity
   //    zCURSOR_SET_NEWPARENT      - Hierarchical cursor positioned on next hierarchical entity - NEW PARENT
   //    zCURSOR_SET_RECURSIVECHILD - Hierarchical cursor positioned on next hierarchical entity RECURSIVECHILD
   //    zCALL_ERROR                - Error in call
   protected int
   SetCursorNextEntityHierarchical( int lLevel, StringBuilder sbEntityName, View view )
   {
      sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( hierInstanceIterator == null )
      {
         return zCURSOR_UNCHANGED;
      }

      if ( ! hierInstanceIterator.hasNext() )
      {
         DropHierarchicalCursor( view ); // We'll drop it since it's no longer needed.
         return zCURSOR_UNCHANGED;
      }

      EntityInstance ei = hierInstanceIterator.next();
      hierInstanceEntityName = ei.getEntityDef().getName();
      int nRC = view.cursor( hierInstanceEntityName ).setCursor( ei ).toInt();
      sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbEntityName.append( hierInstanceEntityName );
      return nRC;
   }

   protected int SetCursorNextEntityHierarchical( MutableInt miLevel, StringBuilder sbEntityName, View view )
   {
      miLevel.setValue( 0 );
      sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( hierInstanceIterator == null )
      {
         return zCURSOR_UNCHANGED;
      }

      if ( ! hierInstanceIterator.hasNext() )
      {
         DropHierarchicalCursor( view ); // We'll drop it since it's no longer needed.
         return zCURSOR_UNCHANGED;
      }

      EntityInstance ei = hierInstanceIterator.next();
      hierInstanceEntityName = ei.getEntityDef().getName();
      int nRC = view.cursor( hierInstanceEntityName ).setCursor( ei ).toInt();
      sbEntityName.append( hierInstanceEntityName );
      miLevel.setValue( view.cursor( hierInstanceEntityName ).getLevel( ) );
      return nRC;
   }

   protected int DropHierarchicalCursor( View view )
   {
      hierInstanceIterator = null;  // Allows GC to clean up the iterator.
      return 0;
   }

   public int SetSelectStateOfEntityForSet( View view, String entityName, int nState, int nSelectSet )
   {
       SelectSet selectSet = view.getSelectSet( nSelectSet );
       EntityCursor cursor = view.cursor( entityName );
       if ( nState == 0 )
       {
           // Is it off?
           if ( ! selectSet.isSelected( cursor ) )
               return 1;  // Yes.  Return 1 to indicate it was already off.

           selectSet.deselect( cursor );
           return 0;
       }

       // Is it on?
       if ( selectSet.isSelected( cursor ) )
           return 1;  // Yes.  Return 1 to indicate it was already on.

       selectSet.select( cursor );
       return 0;
   }

   private CursorResult findSelectedEntity( View view, EntityCursor cursor, CursorResult rc )
   {
      SelectSet selectSet = view.getSelectSet();
      while ( rc.isSet() && ! selectSet.isSelected( cursor ) )  // isSet ==> set, setNewParent, setRecursiveChild
      {
         rc = cursor.setNextContinue();
      }

      return rc;
   }

   //  RETURNS: zCURSOR_NULL      - No entity instances exist
   //           zCURSOR_UNCHANGED - Entity cursor unchanged
   //           zCURSOR_SET       - Entity position established
   //           zCALL_ERROR       - Error in call
   protected int SetCursorFirstSelectedEntity( View view, String entityName, String scopingEntity )
   {
      EntityCursor cursor = view.cursor( entityName );
      return findSelectedEntity( view, cursor, cursor.setFirst( scopingEntity ) ).toInt();
   }

   //  RETURNS: zCURSOR_NULL          - No entity instances exist
   //           zCURSOR_UNCHANGED     - Entity cursor unchanged
   //           zCURSOR_SET           - Entity position established
   //           zCURSOR_SET_NEWPARENT - Entity position changed within new parent
   //           zCALL_ERROR           - Error in call
   protected int SetCursorNextSelectedEntity( View view, String entityName, String scopingEntity )
   {
      EntityCursor cursor = view.cursor( entityName );
      return findSelectedEntity( view, cursor, cursor.setNextContinue() ).toInt();
   }

   //  RETURNS: 0           - Entity Instance has not been selected
   //           1           - Entity Instance has been selected
   //           zCALL_ERROR - Error in call
   protected int GetSelectStateOfEntity( View view, String entityName )
   {
      SelectSet selectSet = view.getSelectSet();
      EntityCursor cursor = view.cursor( entityName );
      return selectSet.isSelected( cursor ) ? 1 : 0;
   }

   //  RETURNS: 0           - Entity Instance has been successfully selected/deselected
   //           1           - Entity already selected/deselected
   //           zCALL_ERROR - Error excluding entity instance
   protected int SetSelectStateOfEntity( View view, String entityName, int selectState )
   {
      EntityCursor cursor = view.cursor( entityName );
      SelectSet selectSet = view.getSelectSet();
      if ( selectState == 1 )
      {
         if ( selectSet.isSelected( cursor ) )
         {
            return 1;
         }

         selectSet.select( cursor );
      }
      else
      {
         if ( ! selectSet.isSelected( cursor ) )
         {
            return 1;
         }

         selectSet.deselect( cursor );
      }

      return 0;
   }

   protected int SetAllSelectStatesForEntity( View view, String entityName, int selectState, String scopingEntity )
   {
      SelectSet selectSet = view.getSelectSet();

      view = view.newView(); // Create a copy so we can muck the cursors.
      EntityCursor cursor = view.cursor( entityName );

      for ( CursorResult rc = cursor.setFirst( scopingEntity );
            rc.isSet(); // isSet ==> set, setNewParent, setRecursiveChild
            rc = cursor.setNextContinue() )
      {
         if ( selectState == 1 )
         {
            selectSet.select( cursor );
         }
         else
         {
            selectSet.deselect( cursor );
         }
      }

      return 0;
   }

   protected int SetAllSelectStatesForEntity( View view, String entityName, int selectState, int scopingEntity )
   {
      if ( scopingEntity == zSCOPE_OI )
      {
         return SetAllSelectStatesForEntity( view, entityName, selectState, view.getLodDef( ).getRoot( ).getName( ) );
      }
      else
      {
         return SetAllSelectStatesForEntity( view, entityName, selectState, "" );
      }
   }

   //  RETURNS: >= 0                  - Absolute Entity number of returned ok
   //           zCURSOR_NULL          - Entity cursor is null
   protected long GetAbsolutePositionForEntity( MutableInt position, View view, String entityName )
   {
      long lRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.hasAny() == false )
      {
         lRC = zCURSOR_NULL;
      }
      else
      {
         lRC = cursor.getHierPosition( );
         position.setValue( lRC );
      }

      return lRC;
   }

   //  RETURNS: zCURSOR_NULL          - Entity cursor is null
   //           zCURSOR_SET           - Entity position changed within
   //                                   current parent
   //           zCALL_ERROR           - Error in call
   protected int SetCursorAbsolutePosition( StringBuilder sbReturnEntityName, View view, long position )
   {
      sbReturnEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      EntityInstance ei = view.getEntityByHierPosition( position );
      if ( ei == null )
      {
         return zCURSOR_UNCHANGED;
      }

      sbReturnEntityName.append( ei.getEntityDef().getName() );
      return view.cursor( sbReturnEntityName.toString() ).setCursor( ei ).toInt();
   }

   private int getTableEntry( StringBuilder sbTableValue,
                              View view,
                              String entityName,
                              String attributeName,
                              String contextName,
                              MutableInt index )
   {
      AttributeDef attributeDef = view.getLodDef().getEntityDef( entityName ).getAttribute( attributeName );
      TableDomain domain = (TableDomain) attributeDef.getDomain();
      List<TableEntry> entries = domain.getTableEntries( view.getTask(), contextName );

      sbTableValue.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( index.intValue() >= entries.size() )
      {
         return -1;
      }

      sbTableValue.append( entries.get( 0 ).toString() );
      return 0;
   }

   protected int GetFirstTableEntryForAttribute( StringBuilder sbTableValue,
                                                 View   view,
                                                 String entityName,
                                                 String attributeName,
                                                 String contextName,
                                                 MutableInt index )
   {
      index.setValue( 0 );
      return getTableEntry( sbTableValue, view, entityName, attributeName, contextName, index );
   }

   protected int GetNextTableEntryForAttribute( StringBuilder sbTableValue,
                                                View   view,
                                                String entityName,
                                                String attributeName,
                                                String contextName,
                                                MutableInt index )
   {
      index.setValue( index.intValue() + 1 );
      return getTableEntry( sbTableValue, view, entityName, attributeName, contextName, index );
   }

   protected int zGetFirstEntityNameForView( View view, StringBuilder sbEntityName )
   {
      LodDef lodDef = view.getLodDef();
      EntityDef entityDef = lodDef.getRoot();
      sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbEntityName.append( entityDef.getName() );
      return 0;
   }

   protected int zGetNextEntityNameForView( View view, StringBuilder sbEntityName )
   {
      LodDef lodDef = view.getLodDef();
      EntityDef entityDef = lodDef.getEntityDef( sbEntityName.toString() );
      entityDef = entityDef.getNextHier();
      if ( entityDef == null )
         return -1;

      sbEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbEntityName.append( entityDef.getName() );
      return 0;
   }

   protected int zGetFirstAttributeNameForEntity( View view, String entityName, StringBuilder sbAttribName )
   {
      EntityDef entityDef = view.getLodDef().getEntityDef( entityName );
      AttributeDef AttributeDef = entityDef.getAttribute( 0 );
      //if ( sbAttribName != null ) // Do we need this?
      sbAttribName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbAttribName.append( AttributeDef.getName() );
      return 0;
   }

   protected int zGetNextAttributeNameForEntity( View view, String entityName, StringBuilder sbAttribName )
   {
      EntityDef entityDef = view.getLodDef().getEntityDef( entityName );
      AttributeDef AttributeDef = entityDef.getAttribute( sbAttribName.toString() );
      AttributeDef = AttributeDef.getNextAttributeDef();
      if ( AttributeDef == null )
         return -1;

      sbAttribName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbAttribName.append( AttributeDef.getName() );
      return 0;
   }

   //./ ADD NAME=ObjectInstanceUpdatedFromFile
   // Source Module=kzoeoiaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      ObjectInstanceUpdatedFile
   //
   //  PURPOSE:    To determine if an Object instance has been updated since
   //              it was last activated/committed from/to a portable file.
   //
   //  PARAMETERS: lpView - View of object instance to check.
   //
   //  RETURNS:    0           - Instance has not been updated
   //              1           - Instance has been updated
   //              zCALL_ERROR - Error in call
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   ObjectInstanceUpdatedFromFile( View  lpView )
   {
   // LPVIEWCSR     lpViewCsr;
   // LPVIEWOI      lpViewOI;
      int           nRC = 0;

   // lpViewCsr = zGETPTR( lpView->hViewCsr );
   // lpViewOI  = zGETPTR( lpViewCsr->hViewOI );
   // nRC       = lpViewOI->bUpdatedFile;

      return( nRC );
   }

   static final char Encrypt1[] = { 22, 5, 13, 16, 15, 0, 6, 23, 2, 19, 12, 9, 10,
                                     1, 25, 4, 17, 7, 18, 11, 21, 14, 24, 20, 3, 8, 0 };

   static final char Encrypt2[] = { 10, 17, 4, 22, 1, 6, 20, 13, 12, 19, 5, 24, 8,
                                    15, 7, 23, 2, 11, 21, 9, 14, 0, 25, 3, 16, 18, 0 };

   static final char EncryptNonAlpha[] = {  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                       '\"',    // ' '
                                       '@',     // '\"'
                                       '%',     // '!'
                                       '4',     // '#'
                                       '&',     // '$'
                                       '$',     // '%'
                                       '\'',    // '&'
                                       '3',     // '\''
                                       '[',     // '('
                                       '(',     // ')'
                                       ':',     // '*'
                                       '5',     // '+'
                                       '+',     // ','
                                       '!',     // '-'
                                       '#',     // '.'
                                       '?',     // '/'
                                       '|',     // '0'
                                       '.',     // '1'
                                       '/',     // '2'
                                       '`',     // '3'
                                       '6',     // '4'
                                       ',',     // '5'
                                       '7',     // '6'
                                       '-',     // '7'
                                       '9',     // '8'
                                       '8',     // '9'
                                       '*',     // ':'
                                       '1',     // ';'
                                       ';',     // '<'
                                       '>',     // '='
                                       '<',     // '>'
                                       ')',     // '?'
                                       ' ',     // '@'
                                       0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                       0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                       0, 0, 0, 0, 0, 0,
                                       '}',     // '['
                                       '=',     // '\\'
                                       '2',     // ']'
                                       '{',     // '^'
                                       '\\',    // '_'
                                       ']',     // '`'
                                       0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                       0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                       0, 0, 0, 0, 0, 0,
                                       '^',     // '{'
                                       '_',     // '|'
                                       '~',     // '}'
                                       '0'  };  // '~'

  /**
    * This should really be named "UfObfuscateString" because it doesn't encrypt the string,
    * just makes it harder to read it in an .ini file.
    *
    * Logic copied directly from kzoeufaa.c
    *
    * @param sbEncryptedString
    * @param originalString
    * @param length
    */
   public static final int UfEncryptString( StringBuilder sbEncryptedString, String originalString, int length )
   {
      sbEncryptedString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      int uLth = originalString.length();
      //        if ( uLth > 26 )
      //            uLth = 26;

      char[] pchOut = new char[ uLth + 1 ];
      char[] pchIn = originalString.toCharArray();

      char uChar = 0;
      int nOrderIdx = 0;
      boolean bNullFound = false;
      pchOut[ uLth ] = 0;
      while ( Encrypt2[ nOrderIdx ] > (uLth - 1) )
      {
          nOrderIdx++;
      }

      int nInLth = pchIn.length;
      while ( nInLth > uLth || nInLth > 25 )
      {
          nInLth -= uLth;
      }

      char cChar = 0;

      pchOut[ Encrypt2[ nOrderIdx ] ] = Encrypt1[ nInLth ];
      nOrderIdx++;
      while ( uChar < (uLth - 1) )
      {
          while ( Encrypt2[ nOrderIdx ] > (uLth - 1) )
          {
              nOrderIdx++;
          }

          if ( ! bNullFound && pchIn[ uChar ] > 0 )
          {
              cChar = pchIn[ uChar ];

              if ( Character.isLetter( cChar ) )
              {
                  while ( cChar >= 'a' )
                  {
                      cChar -= ' ';
                  }

                  while ( cChar < 'A' )
                  {
                      cChar += 11;
                  }

                  cChar -= 'A';
                  if ( uChar % 2 > 0 )
                  {
                      pchOut[ Encrypt2[ nOrderIdx ] ] = Encrypt1[ cChar ];
                  }
                  else
                  {
                      pchOut[ Encrypt2[ nOrderIdx ] ] = Encrypt2[ cChar ];
                  }
              }
              else
              {
                  pchOut[ Encrypt2[ nOrderIdx ] ] = (char) ( EncryptNonAlpha[ cChar ] - 'a' );
              }
          }
          else
          {
              cChar += 17;
              if ( cChar > 25 )
              {
                  cChar -= 25;
              }

              bNullFound = true;
              if ( uChar % 2 > 0 )
              {
                  pchOut[ Encrypt2[ nOrderIdx ] ] = Encrypt2[ cChar ];
              }
              else
              {
                  pchOut[ Encrypt2[ nOrderIdx ] ] = Encrypt1[ cChar ];
              }
          }

          uChar++;
          nOrderIdx++;
      }

      for ( uChar = 0 ; uChar < uLth ; uChar++ )
      {
          pchOut[ uChar ] += 'a';
      }

      sbEncryptedString.append( pchOut );
      return sbEncryptedString.length( );
   }

   public static final String UfEncryptString( String encryptedString, String originalString, int length )
   {
      StringBuilder sb = new StringBuilder( originalString );
      UfEncryptString( sb, originalString, length );
      return sb.toString( );
   }

   public static final int UfDecryptString( StringBuilder sbDecryptedString, String original, int length )
   {
      sbDecryptedString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      if ( original.length() == 0 )
      {
         return( 0 );
      }

      int uLth = original.length();
//    if ( uLth > 26 )
//       uLth = 26;

      char[] pchIn = original.toCharArray();
      char[] pchOut = new char[ length + 1 ];
      char uChar = 0;
      int nOrderIdx = 0;
      while ( Encrypt2[ nOrderIdx ] > (uLth - 1) )
      {
         nOrderIdx++;
      }

      int cReturnLth = pchIn[ Encrypt2[ nOrderIdx ] ] - 'a';
      int nReturnLth = 0;
      while ( Encrypt1[ nReturnLth ] != cReturnLth )
      {
         nReturnLth++;
      }

      cReturnLth = nReturnLth;
      nOrderIdx++;
      while ( cReturnLth > 0 )
      {
         while ( Encrypt2[ nOrderIdx ] > (uLth - 1) )
         {
            nOrderIdx++;
         }

         char ucChar = pchIn[ Encrypt2[ nOrderIdx ] ];
         if ( Character.isLetter( ucChar ) )
         {
            ucChar -= 'a';
            if ( uChar % 2 > 0 )
            {
               int cWk = 0;
               while ( Encrypt1[ cWk ] != ucChar )
               {
                  cWk++;
               }

               pchOut[ uChar ] = (char) (cWk + 'a');
            }
            else
            {
               int cWk = 0;
               while ( Encrypt2[ cWk ] != ucChar )
               {
                  cWk++;
               }

               pchOut[ uChar ] = (char) ( cWk + 'a' );
            }
         }
         else
         {
            int nIdx;

            // Look for the encrypted char in the non-alpha table.
            for ( nIdx = 32; nIdx < 127; nIdx++ )
            {
               if ( EncryptNonAlpha[ nIdx ] == ucChar )
               {
                  break;
               }
            }

            if ( nIdx == 127 )
            {
               SysMessageBox( null, "Zeidon Internal Error", "Error decrypting string", 0 );
               return( zCALL_ERROR );
            }

            pchOut[ uChar ] = (char) nIdx;
         }

         cReturnLth--;
         uChar++;
         nOrderIdx++;
      }

      pchOut[ uChar ] = 0;
      sbDecryptedString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbDecryptedString.append( pchOut );
      return( nReturnLth );
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
      private long ulDateMinutes;    // Minutes since year zero
      private int  usTSeconds;       // Thousandths of seconds
   };


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
   UfDateTimeDiff( int  lDiff,
                   DateTimeRecord   lpDateTime1,
                   DateTimeRecord   lpDateTime2,
                   int  nDiffType )
   {
   /*****
      int             nRC = 0;
      int             usDiffLo;
      int             ulDiffHi;
      int             lY1, lY2, lM1, lM2, lD1, lD2;
      DateTimeRecord  lpDT1, lpDT2;
      DateTimeRecord  dt, dt1, dt2;
      boolean         bNegative;
      boolean         bOverflow = false;

      lDiff = 0;

   // memcpy( dt1, lpDateTime1, sizeof(DateTimeInternalRecord) );
   // memcpy( dt2, lpDateTime2, sizeof(DateTimeInternalRecord) );
      lpDT1 = dt1;
      lpDT2 = dt2;

      // For Year, Month or Date
      if ( nDiffType == zDT_YEAR ||
           nDiffType == zDT_MONTH ||
           nDiffType == zDT_DAY )
      {
        // use only the date part of the timestamp
        lpDT1.usTSeconds = 0;
        lpDT2.usTSeconds = 0;
        ulDiffHi = lpDT1.ulDateMinutes % Day;
        lpDT1.ulDateMinutes -= ulDiffHi;
        ulDiffHi = lpDT2.ulDateMinutes % Day;
        lpDT2.ulDateMinutes -= ulDiffHi;
      }

      // determine the sign of the difference
      nRC = fnCompareDateTimeToDateTime( &dt1, &dt2 );
      bNegative = (nRC > 0);

      // shortcut for equal values.
      if ( nRC == 0 )
         return( 0 );

      // If negative, exchange the two date time values.
      if ( bNegative )
      {
        lpDT1 = &dt2;
        lpDT2 = &dt1;
      }

      // calculate the difference with carry bit
      ulDiffHi = lpDT2.ulDateMinutes - lpDT1.ulDateMinutes;
      if ( lpDT1.usTSeconds > lpDT2.usTSeconds )
      {
        ulDiffHi--;
        usDiffLo = (int) (lpDT2.usTSeconds - lpDT1.usTSeconds + 60000);
      }
      else
      {
        usDiffLo = lpDT2.usTSeconds - lpDT1.usTSeconds;
      }

      switch ( nDiffType )
      {
         case zDT_YEAR:
            // calculate the difference of the year number
            lY1 = fnGetDateTimeComponent( (LPDATETIME)lpDT1, zDT_YEAR );
            lY2 = fnGetDateTimeComponent( (LPDATETIME)lpDT2, zDT_YEAR );
            lDiff = lY2 - lY1;

            // check for carry (e.g. "19970101" - "19961231" has lDiff=1
            // and has to be corrected!)
            UfAddToDateTime( (LPDATETIME)lpDT1, lDiff, zDT_YEAR);
            if ( fnCompareDateTimeToDateTime( lpDT1, lpDT2 ) == 1 )
            {
              lDiff--;
            }

            break;

         case zDT_MONTH:
            // get the days for additional carry, then set it 0 for calculation
            lD1 = fnGetDateTimeComponent( (LPDATETIME)lpDT1, zDT_DAY );
            lD2 = fnGetDateTimeComponent( (LPDATETIME)lpDT2, zDT_DAY );
            fnSetDateTimeComponent( (LPDATETIME)lpDT1, zDT_DAY, 0L );
            fnSetDateTimeComponent( (LPDATETIME)lpDT2, zDT_DAY, 0L );

            // calculate the difference of the years and the months
            lY1 = fnGetDateTimeComponent( (LPDATETIME)lpDT1, zDT_YEAR );
            lY2 = fnGetDateTimeComponent( (LPDATETIME)lpDT2, zDT_YEAR );
            lM1 = fnGetDateTimeComponent( (LPDATETIME)lpDT1, zDT_MONTH );
            lM2 = fnGetDateTimeComponent( (LPDATETIME)lpDT2, zDT_MONTH );
            lDiff = (lY2 - lY1) * 12 + lM2 - lM1;

            // check for carry caused by leap year (has to be corrected!)
            do
            {
              memcpy( &dt, lpDT1, sizeof(DateTimeInternalRecord) );
              UfAddToDateTime( (LPDATETIME)&dt, lDiff, zDT_MONTH);
              nRC = fnCompareDateTimeToDateTime( &dt, lpDT2 );

              lDiff += nRC;

            } while ( nRC );

            // check for carry caused by difference in days (and correct it)
            if ( lD2 < lD1 )
              lDiff--;

            break;

         case zDT_DAY:
            lDiff = (int) (ulDiffHi / Day);
            break;

         case zDT_HOUR:
            lDiff = (int) (ulDiffHi / Hour);
            break;

         case zDT_MINUTE:
            if (ulDiffHi > 0x7fffffff)
            {
              bOverflow = true;
              break;
            }

            lDiff = (int) ulDiffHi;
            break;

         case zDT_SECOND:
            if (ulDiffHi > (0x7fffffff / 60 - 1) )
            {
              bOverflow = true;
              break;
            }

            lDiff = (int) (ulDiffHi * 60 + usDiffLo / 1000);
            break;

         default:
            // "KZOEE023 - Invalid parameter, "
            fnSysMessageBox( 0, "Zeidon Error", "Invalid parameter", 0 );
         // fnIssueCoreError( null, lpView, 8, 23, 0, "nDiffType", "" );
         // fnOperationReturn( iUfAddToDateTime, lpCurrentTask );
            return( zCALL_ERROR );
      }

      if ( bOverflow )
      {
      // fnOperationReturn( iUfAddToDateTime, lpCurrentTask );
         return( -1 );
      }

      if ( bNegative )
        lDiff *= -1;
   ***/
      return( lDiff );

   } // UfDateTimeDiff


   // Source Module=kzoeufaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   // FUNCTION:   UfStringToDateTime
   //
   // PARAMETERS: lpDateTime       - pointer to the DateTime data
   //              cpcDateTimeString - a date-time string (YYYYMMDDHHmmSSTht)
   //                                 contains one of the following formats:
   //                                    YYYYMMDDHHmmSSTht
   //                                    YYYYMMDDHHmmSSTh
   //                                    YYYYMMDDHHmmSST
   //                                    YYYYMMDDHHmmSS
   //                                    YYYYMMDDHHmm
   //                                    YYYYMMDD
   //
   //                                        YYYY - year
   //                                        MM   - month
   //                                        DD   - day
   //                                        HH   - hour
   //                                        mm   - minute
   //                                        ss   - seconds
   //                                        T    - tenths of seconds
   //                                        h    - hundredths of seconds
   //                                        t    - thousandths of seconds
   //
   // PURPOSE:    To convert a string to a DateTime (T) attribute type
   //              The string is checked for correct values (e.g. 19960100
   //              results in zCALL_ERROR, but 00000000112233 is valid,
   //              because this is just a time).
   //
   // RETURNS:    0 - String converted, info set in DateTime area
   //    zCALL_ERROR - Error in call
   //
   /////////////////////////////////////////////////////////////////////////////
   //./ END + 2
   public int
   UfStringToDateTime( String cpcDateTimeString, DateTimeRecord lpDateTime )
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
            {
               usTSeconds *= (usStringLth == 16) ? 10 : 100 ;
            }

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
            sbWorkString.insert( 0, cpcDateTimeString.charAt( 6 ) );
            sbWorkString.insert( 1, cpcDateTimeString.charAt( 7 ) );
            usDay = zatol( sbWorkString.toString( ) );

            // Get Month Value
            sbWorkString.setLength(0);
            sbWorkString.insert( 0, cpcDateTimeString.charAt( 4 ) );
            sbWorkString.insert( 1, cpcDateTimeString.charAt( 5 ) );
            usMonth = zatol( sbWorkString.toString( ) );

            // Get Year Value
            zstrncpy( sbWorkString, cpcDateTimeString, 4 );
            ulYear = zatol( sbWorkString.toString( ) );

            // Check to see if we have date/datetime or only time without date.
            bDateSet = (ulYear != 0 || usMonth != 0 || usDay != 0);

            if ( ulYear == 0 )
            {
              ulYear = 1900;
              if ( bDateSet )
              {
                nRC = zCALL_ERROR;
              }
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
               {
                  nRC = zCALL_ERROR;
               }
            }

            // Get valid day for the month
            usDayOrg = usDay;
            usDay = fnValidateDay( usMonth, usDayOrg, ulYear );
            if ( usDay != usDayOrg )
            {
               if ( bDateSet )
               {
                  nRC = zCALL_ERROR;
               }
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
      ulDateMinutes += (usMinutes * Minute);

      // Now save this, before we forget...
      lpDTInternal.ulDateMinutes = ulDateMinutes;

      // Convert seconds to thousandths, and save it too.
      lpDTInternal.usTSeconds = usTSeconds + (usSeconds * 1000);

      return( nRC );

   }  /* END of StringToDateTime */

   private int
   fnValidateDay( int usMonth, int usDay, int ulYear )
   {
      int   usMax;

      if ( usDay >= 1 && usDay <= 28 )
      {
         return( usDay );
      }

      if ( usDay < 1 )
      {
         return( 1 );
      }

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
      {
         return( usMax );
      }
      else
      {
         return( usDay );
      }

   } /*** END fnValidateDay ***/

   // Signed long to Asciiz right justified (with leading zeros) for length
   // INPUT: Long value to be converted to Ascii string
   //         Ascii string address at which to set converted string
   //         Return string length - not including null terminator
   //
   private int
   zltoal( int    lValue,
           StringBuilder sb,
           int    nOffset,
           int    nLth )
   {
      String szTemp = null;
      int    nPos;
      int    k;

      szTemp = zltoa( lValue, szTemp );
      k = zstrlen( szTemp );
      if ( k < nLth )
      {
         nPos = nLth - k;
         zmemset( sb, nOffset, '0', nPos );
      }
      else
      {
         nPos = 0;
         k = nLth;
      }

      zstrncpy( sb, nPos + nOffset, szTemp, k );
      sb.insert( nLth + nOffset, '\0' );
      return nLth + nOffset;
   }

   //./ ADD NAME=UfDateTimeToString
   // Source Module=kzoeufaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   // FUNCTION:   UfDateTimeToString
   //
   // PARAMETERS: lpDateTime       - pointer to the DateTime data
   //              pchReturnString  - pointer to area to return string
   //              nMaxLth          - length of pchReturnString (including
   //                                 null terminator.)
   //
   // PURPOSE:    To convert a DateTime (T) attribute type into a string.
   //              If the DateTime attribute is 'NULL', a null string will
   //              be returned.
   //
   // RETURNS:    0 - String returned.
   //             -1 - Null string returned.
   //
   //           When nMaxLth is     format of returned string is
   //
   //                    9           YYYYMMDD
   //                    13          YYYYMMDDHHmm
   //                    15          YYYYMMDDHHmmSS
   //                    16          YYYYMMDDHHmmSST
   //                    17          YYYYMMDDHHmmSSTh
   //                    18          YYYYMMDDHHmmSSTht
   //
   //
   //                               (YYYYMMDDHHmmSSTht)
   //                                MM   - month
   //                                DD   - day
   //                                HH   - hour
   //                                mm   - minute
   //                                SS   - seconds
   //                                T    - tenths of seconds
   //                                h    - hundredths of seconds
   //                                t    - thousandths of seconds
   //
   //    zCALL_ERROR - Error in call
   //
   /////////////////////////////////////////////////////////////////////////////
   //./ END + 3
   public String
   UfDateTimeToString( DateTimeRecord lpDateTime, String pchReturnString, int nMaxLth )
   {
      StringBuilder sb = new StringBuilder( 20 );
      long       ulDateMinutes;
      int        k;
      int        usYear, usMonth, usDay, usHour, usMinute;
      int        usSeconds, usTSeconds;
      DateTimeRecord lpDTInternal;

      lpDTInternal = lpDateTime;

      if ( lpDTInternal.ulDateMinutes == lNullInteger )
      {
         pchReturnString = "";
         return pchReturnString;
      }

      if ( nMaxLth < 9 )
      {
         return null;
      }
      else
      if ( nMaxLth < 13 )
      {
         nMaxLth = 9;      // Let's assume 9.
      }
      else
      if ( nMaxLth < 15 )
      {
         nMaxLth = 13;     // Let's assume 13.
      }

      ulDateMinutes = lpDTInternal.ulDateMinutes;
      usTSeconds = lpDTInternal.usTSeconds;

      if ( ulDateMinutes >= Year2000 )
      {
         usYear = 2000;
         ulDateMinutes -= Year2000;
      }
      else
      if ( ulDateMinutes >= Year1900 )
      {
         usYear = 1900;
         ulDateMinutes -= Year1900;
      }
      else
      {
         usYear = 0;
      }

      for ( ; ; )
      {
         if ( (usYear % 4) == 0 &&
               ((usYear % 100) != 0 || (usYear % 400) == 0) )
         {
            if ( ulDateMinutes >= LeapYear )
            {
               usYear++;
               ulDateMinutes -= LeapYear;
            }
            else
            {
               break;
            }
         }
         else
         {
            if ( ulDateMinutes >= Year )
            {
               usYear++;
               ulDateMinutes -= Year;
            }
            else
            {
               break;
            }
         }
      }

      usDay = (int) (ulDateMinutes / Day);
      ulDateMinutes %= Day;

      k = 1;
      if ( (usYear % 4) == 0 &&
           ((usYear % 100) != 0 || (usYear % 400) == 0) )
      {
         while ( k < 12 && usDay >= usLeapDayTable[ k ] )
         {
            k++;
         }

         usMonth = k;
         if ( k == 12 || usDay <= usLeapDayTable[ k ] )
         {
            k--;
         }

         usDay -= usLeapDayTable[ k ];
      }
      else
      {
         while ( k < 12 && usDay >= usDayTable[ k ] )
         {
            k++;
         }

         usMonth = k;
         if ( k == 12 || usDay <= usDayTable[ k ] )
         {
            k--;
         }

         usDay -= usDayTable[ k ];
      }

      usDay++;
      usHour = (int) (ulDateMinutes / Hour) ;
      usMinute = (int) (ulDateMinutes % Hour );
      if ( usTSeconds != 0 )
      {
         usSeconds = usTSeconds / 1000;
         usTSeconds %= 1000;
      }
      else
      {
         usSeconds = 0;
      }

      zltoal( usYear, sb, 0, 4 );          // year to string
      zltoal( usMonth, sb, 4, 2 );     // month to string
      zltoal( usDay, sb,  6, 2 );       // day to string
      if ( nMaxLth == 9 )
      {
         return sb.toString( );
      }

      zltoal( usHour, sb,  8, 2 );      // hour to string
      zltoal( usMinute, sb, 10, 2 );   // minute to string
      if ( nMaxLth == 13 )
      {
         return sb.toString( );
      }

      zltoal( usSeconds, sb, 12, 2 );  // seconds to string
      if ( nMaxLth == 15 )
      {
         return sb.toString( );
      }

      // milliseconds to string
      zltoal( usTSeconds, sb, 14, (((nMaxLth - 15 ) > 3) ? 3 : (nMaxLth - 15)));

      return sb.toString( );

   }  /* END of DateTimeToString */

   protected int WebReturnToHomePage( View viewXfer, View view )
   {
      // TODO This should be somewhere else.
      return 0;
   }

   protected void ActivateDynamicDomain( View view, String string )
   {
     // TODO This should be somewhere else.
   }


   protected String GetRegistryCLSID( String stringCLSID, String string )
   {
      // TODO This should be somewhere else.
      return stringCLSID;
   }

   protected String GetRegistryPrintValue( String string, String string2,
                                           String stringCLSID, int regSz, String stringReturn, int i )
   {
      // TODO This should be somewhere else.
      return string;
   }

   protected String GetRegistryGeneralValue( String string, String string2,
                                             String stringCLSID, int regSz, String stringReturn, int i )
   {
      // TODO This should be somewhere else.
      return string;
   }

/* These are in ZGLOBAL1
   public int GetEntityNameFromStructure( String stringInternalEntityStructure, StringBuilder sbReturnEntityName )
   {
      sbReturnEntityName.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturnEntityName.append( stringInternalEntityStructure );
      return 0;
   }

   public String GetEntityNameFromStructure( String stringInternalEntityStructure, String returnEntityName )
   {
   // returnEntityName = stringInternalEntityStructure;
   // return returnEntityName;
      return stringInternalEntityStructure;
   }
*/

   /**
    * The C version of this method converts internalEntityStructure to an LPVIEWATTRIB and
    * compares attributeName to lpAttributeDef.szName.  In Java we don't pass the internal
    * value, just the name of the attribute, so we just do a simple compare here.
    *
    * @param attributeName
    * @param stringInternalEntityStructure
    * @return
    */
   protected int IsValidAttribute( String attributeName, String stringInternalEntityStructure )
   {
      int nRC = attributeName.equalsIgnoreCase( stringInternalEntityStructure ) ? TRUE : FALSE;
      return nRC;
   }

   //@override
   protected int IsValidAttribute( View view, String attributeName, String entityName )
   {
         EntityCursor cursor = null;

	      try{
	         cursor = view.cursor( entityName );
          }
          catch ( UnknownEntityDefException e )
          {
             return -1;
          }
          try{
 	         AttributeInstance attr = cursor.getAttribute( attributeName );
 	         if ( attr.getAttributeDef().isHidden() )
 	        	 return -1;
          }
          catch  ( UnknownAttributeDefException e )
          {
 	         return -1;

          }
      return 0;
   }

   //  RETURNS:    zCURSOR_... - Entity removed, and this is the status for setting the cursor.
   //                            These return codes are shown under the operations for SetCursor...Entity.
   //              zCALL_ERROR - Error removing entity instance
   protected int DropEntity( View view, String entityName, int pos )
   {
      int nRC;
      EntityCursor cursor = view.cursor( entityName );
      if ( cursor.isNull() )
      {
         nRC = zCALL_ERROR;
      }
      else
      {
         nRC = view.cursor(entityName).dropEntity( CURSOR_POS.get( pos ) ).toInt();
      }

      return nRC;
   }

   protected int zRTrim( String stringT, StringBuilder sbReturnString, int maxLth )
   {
      // remove trailing whitespace
      sbReturnString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturnString.append( stringT.replaceAll( "\\s+$", "" ) );
      return sbReturnString.length( );
   }

   protected int zLTrim( String stringT, StringBuilder sbReturnString, int maxLth )
   {
      // remove leading whitespace
      sbReturnString.setLength( 0 ); // Use sb.setLength( 0 ); to clear a string buffer.
      sbReturnString.append( stringT.replaceAll( "^\\s+", "" ) );
      return sbReturnString.length( );
   }

   protected String zExpungeAllSubstring( String string, String subString )
   {
      int nLth = subString.length( );
      int k;
      if ( nLth > 0 )
      {
         StringBuilder sb = new StringBuilder( string );
         nLth--;
         while ( (k = sb.indexOf( subString )) >= 0 )
         {
            sb.replace( k, k + nLth, "" );
         }

         string = sb.toString( );
      }

      return string;
   }

   protected String zReplaceSubString( String string, int nPosStart, int nPosEnd, String replaceString )
   {
      StringBuilder sb = new StringBuilder( string );
      sb.replace( nPosStart, nPosEnd, replaceString );
      return sb.toString( );
   }

   protected int zReplaceSubString( StringBuilder sb, int nPosStart, int nPosEnd, String replaceString )
   {
      sb.replace( nPosStart, nPosEnd, replaceString );
      return sb.length( );
   }

   protected int ExecuteQueryForContactList( View queryView )
   {
      // TODO - Create Code.
      return 0;
   }

   public void RefreshWindow( View view )
   {
      // TODO - Create code
   }

   protected zVIEW AttachSubtaskToPane( zVIEW view, String splitName, String dialogName, String windowName, int action )
   {
      // TODO - Create code.
      // action  /* 1 - Right; 2 - Down (matching SplitFrame) */
      return view;
   }

   protected void RelinkInstanceToInstance( View tgtView, String tgtEntityName, View srcView, String srcEntityName2 )
   {
       tgtView.cursor( tgtEntityName ).linkInstances( srcView.cursor( srcEntityName2 ).getEntityInstance() );
   }

   protected int UfFormatDateTime( StringBuilder sbDayOfWeek, String szDate, String strFormat )
   {
      //TODO: This is slow.  Do we need to improve it?
      DateTime date = JoeUtils.parseStandardDateString( szDate );
      String javaFormat = JoeUtils.convertZeidonDateFormatToJavaFormat( strFormat );
      DateTimeFormatter formatter = DateTimeFormat.forPattern( javaFormat );
      sbDayOfWeek.append( formatter.print( date ) );
      return 0;
   }

   //./ ADD NAME=SetIncrementalUpdateFlags
   // Source Module=kzoeeiaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      SetIncrementalUpdateFlags
   //
   //  PURPOSE:    To reset the record ownership and relationship ownership
   //              flags in an object instance and optionally mark the
   //              instance as either created or persistent from a
   //              database update point of view.
   //
   //  PARAMETERS: lpView        - View to instance to be reset
   //
   //              cpcEntityName - Optional Entity name, NULL to process all
   //                              Entity types
   //
   //              nOption       - marking flags
   //
   //                zSET_INCR_CREATED         0x0002
   //                     Mark all instances as created
   //
   //                zSET_INCR_PERSISTENT      0x0004
   //                     Mark all instances as existing on database
   //                     (i.e. not created)
   //
   //                zSET_INCR_UPDATED         0x0008
   //                     Mark all instances as updated
   //
   //                zSET_INCR_NOT_UPDATED     0x0010
   //                     Mark all instances as not updated
   //
   //                zSET_INCR_INCLUDED        0x0020
   //                     Mark all instances as included
   //
   //                zSET_INCR_NOT_INCLUDED    0x0040
   //                     Mark all instances as not included
   //
   //                zSET_INCR_CURSORPOS       0x0080
   //                     Do setting of entity instance at the cursor
   //                     position ONLY, requires EntityName
   //
   //                zSET_INCR_OWNERSHIP       0x0100
   //                     Set record/relationship ownership
   //
   //                NOTE: The following 4 settings SHOULD NOT be used in
   //                      Conjunction withe zSET_INCR_OWNERSHIP! If
   //                      specified, they will be IGNORED!
   //
   //                zSET_INCR_RECORDOWNER     0x0200
   //                     Force record ownership ON
   //
   //                zSET_INCR_NOT_RECORDOWNER 0x0400
   //                     Force record ownership OFF
   //
   //                zSET_INCR_RELOWNER     0x1000
   //                     Force relationship ownership ON
   //
   //                zSET_INCR_NOT_RELOWNER    0x2000
   //                     Force relationship ownership OFF
   //
   //  RETURNS:    0           - Successful
   //              zCALL_ERROR - Invalid view passed
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   SetIncrementalUpdateFlags( View   lpView,
                              String cpcEntityName,
                              int    nOption )
   {
      boolean          bCreated;
      boolean          bPersist;
      boolean          bUpdated;
      boolean          bNotUpdated;
      boolean          bIncluded;
      boolean          bNotIncluded;
      boolean          bSingleEntity;
      boolean          bAttrUpdated;
      boolean          bAttrNotUpdated;
      EntityInstance   lpRootEntityInstance;
      EntityInstance   lpEntityInstance = null;
      boolean          bMustCheckOIUpdated = false;

      if ( lpView.cursor( cpcEntityName ).isNull() )
      {
         return( zCALL_ERROR );
      }

      bSingleEntity = ((nOption & zSET_INCR_CURSORPOS) != 0);
      if ( bSingleEntity )
      {
         lpEntityInstance = lpView.cursor( cpcEntityName ).getEntityInstance();
         lpView.cursor( cpcEntityName ).setCursor( lpEntityInstance );
      }
      else
      {
         bSingleEntity = false;
      // lpEntityDef = null;
      }

      // Get the root of the object instance
      lpRootEntityInstance = lpEntityInstance;
      if ( bSingleEntity == false )
      {
         lpRootEntityInstance = lpEntityInstance;
         while ( lpRootEntityInstance.getParent() != null )
         {
            lpRootEntityInstance = lpRootEntityInstance.getParent();
         }
      }

      if ( lpRootEntityInstance == null )
      {
         return( 0 );
      }

      bCreated  = ((nOption & zSET_INCR_CREATED ) != 0);
      if ( bCreated == false )
      {
         bPersist = ((nOption & zSET_INCR_PERSISTENT) != 0);
         bUpdated = ((nOption & zSET_INCR_UPDATED) != 0);
         if ( bUpdated )
         {
         // lpEntityInstance.setUpdated();
         // lpViewOI.setUpdatedFile();
            bNotUpdated = false;
         }
         else
         {
            bNotUpdated = ((nOption & zSET_INCR_NOT_UPDATED) != 0);
         }
      }

      bIncluded = ((nOption & zSET_INCR_INCLUDED) != 0);
      if ( bIncluded == false )
      {
         bNotIncluded = ((nOption & zSET_INCR_NOT_INCLUDED) != 0);
      }
      else
      {
         bNotIncluded = false;
      }

      bAttrUpdated = ((nOption & zSET_INCR_ATTR_UPDATED) != 0);
      if ( bAttrUpdated == false )
      {
         bAttrNotUpdated = ((nOption & zSET_INCR_ATTR_NOT_UPDATED) != 0);
      }
/******
      // Loop through the entity instances.
      for ( lpEntityInstance = lpRootEntityInstance;
            lpEntityInstance != null;
            lpEntityInstance = bSingleEntity ? null : lpEntityInstance.getNextHier() ) )
      {
         LPVIEWENTITY lpTempEntityDef;
         LPVIEWATTRIB lpAttributeDef;
         boolean      bWorkEntity; // indicate entity is work or derived

         lpTempEntityDef = zGETPTR( lpEntityInstance->hEntityDef );

         // If lpEntityDef is not null, then we only want to change the flags
         // for EI's that match that LodDef.
         if ( lpEntityDef && lpTempEntityDef != lpEntityDef )
            continue;  // They don't match so continue with next EI.

         bWorkEntity = false;
         if ( lpTempEntityDef->bDerived || lpTempEntityDef->bDerivedPath ||
              lpTempEntityDef->hFirstDataRecord == 0 )
         {
            bWorkEntity = true;
         }

         if ( bCreated )
         {
            cursor.bCreated = true;
            cursor.bUpdated = false;
            lpViewOI->bUpdatedFile = true;
            if ( bWorkEntity == false )
               lpViewOI->bUpdated = true;
         }
         else
         {
            if ( bPersist )
            {
               cursor.bCreated = false;
               bMustCheckOIUpdated = true;
            }

            if ( bUpdated )
            {
               cursor.bUpdated = true;
            }
            else
            if ( bNotUpdated )
            {
               cursor.bUpdated = false;
               bMustCheckOIUpdated = true;

               /*
               HH, 2002.05.02
               This code was commented out for following reasons:
               - it is obviously wrong, the "created" flag is not turned on,
                 but it is turned off.
               - to "correct" it would mean, that turn "updated" off for a
                 deleted entity means to "undelete" it.

               If we would want that behavior, then we should correct and
               re-activate the code. However, this is a runtime change which
               can influence existing applications.

               Removing the code is not a runtime change, because of the bug it
               did not have any significant effect.

               // We don't want the entities to be flagged as updated.  If
               // the current entity is flagged as deleted then we will turn
               // on the "created" flag so the EI is considered "dead".
               if ( cursor.bDeleted )
               {
                  cursor.bCreated = false; // wrong, = true is correct
               }
               */ /****
            }
         }

         if ( bIncluded && lpEntityInstance->hParent )
            cursor.bIncluded = true;
         else
         if ( bNotIncluded )
            cursor.bIncluded = false;

         // Now check to see if we need to update the attributes.
         if ( bAttrUpdated == false && bAttrNotUpdated == false )
            continue; // Nope--continue with the next EI.

         // Set attribute flags.
         for ( lpAttributeDef = zGETPTR( lpTempEntityDef->hFirstOD_Attrib );
               lpAttributeDef;
               lpAttributeDef = zGETPTR( lpAttributeDef->hNextOD_Attrib ) )
         {
            LPATTRIBFLAGS lpAttribFlags = fnGetAttribFlagsPtr( lpEntityInstance,
                                                               lpAttributeDef );

            // Set the update flag for the entity.  Since we KNOW that either
            // bAttrUpdated flag or bAttrNotUpdated flag is true, and since they
            // can't both be true, we can just set the update flag for the
            // attribute to bAttrUpdated.
            if ( lpAttribFlags )
               lpAttribFlags->u.bFlags.bUpdated = bAttrUpdated;
         }

         if ( bAttrUpdated && cursor.bUpdated == false )
         {
            cursor.bUpdated = true;
            lpViewOI->bUpdatedFile = true;
            if ( bWorkEntity == false )
               lpViewOI->bUpdated = true;
         }
         else
         if ( bAttrNotUpdated && cursor.bUpdated )
         {
            cursor.bUpdated = false;
            bMustCheckOIUpdated = true;
         }
      }

      // if an update flag was removed, we have to re-check the
      //  OI update flags from scratch
      if ( bMustCheckOIUpdated )
         fnCheckOIUpdated( lpViewOI );
*/
      // Everything has been set, return
      return 0;
   }

   //./ ADD NAME=GetIncrementalUpdateFlags
   // Source Module=kzoeeiaa.c
   /////////////////////////////////////////////////////////////////////////////
   //
   //  ENTRY:      GetIncrementalUpdateFlags
   //
   //  PURPOSE:    To get the value of incremental flags for an entity.
   //
   //  PARAMETERS: lpView        - View to instance to be reset
   //              cpcEntityName - Required Entity name.
   //              nOption       - marking flags
   //
   //                zSET_INCR_CREATED         0x0002
   //                     Check if entity instance is created
   //
   //                zSET_INCR_UPDATED         0x0008
   //                     Check if entity instance is updated
   //
   //                zSET_INCR_INCLUDED        0x0020
   //                     Check if entity instance is included
   //
   //                zSET_INCR_HANGING_ENTITY  0x0800
   //                     Check if entity is a hanging entity.
   //
   //                zSET_INCR_TEMPORAL_ENTITY 0x4000
   //                     Check if entity is a temporal entity.
   //
   //                zSET_INCR_DELETED         0x8000
   //                     Check if entity is deleted.  Can only be used from
   //                     a dbhandler.
   //
   //  RETURNS:    0           - Flag for entity not set.
   //              1           - Flag is set.
   //              zCALL_ERROR - Invalid view passed
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   GetIncrementalUpdateFlags( View   lpView,
                              String cpcEntityName,
                              int    nOption )
   {
      int  nRC = 0;
      if ( isValid( lpView ) && lpView.cursor( cpcEntityName ).isNull() == false )
      {
         EntityCursor cursor = lpView.cursor( cpcEntityName );
         switch ( nOption )
         {
            case zSET_INCR_CREATED:
               nRC = cursor.isCreated() ? 1 : 0;
               break;

            case zSET_INCR_UPDATED:
               nRC = cursor.isUpdated() ? 1 : 0;
               break;

            case zSET_INCR_INCLUDED:
               nRC = cursor.isIncluded() ? 1 : 0;
               break;

            case zSET_INCR_HANGING_ENTITY:
            // nRC = cursor.isHangingEntity() ? 1 : 0; // TODO: DKS???
               break;

            case zSET_INCR_TEMPORAL_ENTITY:
            // nRC = cursor.isTemporal() ? 1 : 0;  // TODO: DKS???
               break;

            case zSET_INCR_DELETED:
               nRC = cursor.isDeleted() ? 1 : 0;
               break;

            default:
               //  "KZOEE120 - Invalid position parameter"
               fnIssueCoreError( lpView.getTask(), lpView, 16, 302, nOption, "", "" );
               nRC = zCALL_ERROR;
               break;
         }
      }

      return nRC;
   }

   protected int AttributeUpdated( View view, String entityName, String attributeName )
   {
	  boolean bRC = false;

	  EntityInstance entityInstance = view.cursor(entityName).getEntityInstance();

	  AttributeDef attributeDef = view.getLodDef().getEntityDef( entityName ).getAttribute( attributeName );

	  if ( entityInstance.isAttributeUpdated(attributeDef) )
	     return 1;
	  else
		 return 0;
   }

   protected int zLodContainsEntity( View view, String entityName )
   {
      EntityDef ve = view.getLodDef( ).getEntityDef( entityName );
      if ( ve != null )
      {
         return 1;
      }
      else
      {
         return -1;
      }
   }

   protected int zLodContainsAttribute( View view, String entityName, String attribName )
   {
      EntityDef ve = view.getLodDef( ).getEntityDef( entityName );
      if ( ve.getAttribute( attribName ) != null )
      {
         return 1;
      }
      else
      {
         return -1;
      }
   }

   //./ ADD NAME=GetWebRedirection
   // Source Module=VmlOperation.java
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  DIALOG OPERATION: GetWebRedirection
   //
   //  PURPOSE:   To get the current Web KZXMLPGO "next window".
   //
   //  PARAMETERS: vSubtask    - The subtask view for the current window
   //              nWindowActionBehavior - set the action behavior
   //              stringDlgTag - Dialog name containing new window
   //              stringWndTag - Window name to which to transfer
   //
   //  RETURNS:      0  - Action performed
   //               -1  - Error locating window
   //               -2  - Action not located
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public String
   GetWebRedirection( View  vKZXMLPGO )
   {
      StringBuilder sbFunctionCall = new StringBuilder( "" );
      StringBuilder sbDlgWnd = new StringBuilder( "" );
      StringBuilder sbDlgTag = new StringBuilder( "" );
      StringBuilder sbWndTag = new StringBuilder( "" );
      int    nRC = 0;

      if ( isValid( vKZXMLPGO ) )
      {
         GetStringFromAttribute( sbDlgTag, vKZXMLPGO, "NextDialogWindow", "DialogName" );
         GetStringFromAttribute( sbWndTag, vKZXMLPGO, "NextDialogWindow", "WindowName" );
         GetStringFromAttribute( sbFunctionCall, vKZXMLPGO, "NextDialogWindow", "FunctionCall" );
         if ( sbDlgTag.length() > 0 && sbWndTag.length() > 0 )
         {
            zstrcpy( sbDlgWnd, sbDlgTag );
            zstrcat( sbDlgWnd, sbWndTag );
         }

         DeleteEntity( vKZXMLPGO, "NextDialogWindow", zREPOS_LAST );

         if ( sbFunctionCall.toString().equals( "StartSubwindow" ) )
         {
            CreateEntity( vKZXMLPGO, "PagePath", zPOS_AFTER );
            SetAttributeFromString( vKZXMLPGO, "PagePath", "LastPageName", sbDlgWnd.toString( ) );
            nRC = 1;
         }
      }

   // TraceLineS( "", "" );
   // TraceLineS( "GetWebRedirection KZXMLPGO (post)", "" );
      TraceLine( "GetWebRedirection KZXMLPGO (post) Dlg: %s   Wnd: %s", sbDlgTag.toString( ), sbWndTag.toString( ) );
   // DisplayObjectInstance( vKZXMLPGO, "", "" );

      if ( sbDlgWnd.length() > 0 )
      {
         return sbDlgWnd.toString( ) + ".jsp";
      }
      else
      {
         return "";
      }
   }

   //./ ADD NAME=SetWebRedirection
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   //  DIALOG OPERATION: SetWebRedirection
   //
   //  PURPOSE:   To set up the Web KZXMLPGO entities.
   //
   //  PARAMETERS:  vKZXMLPGO    - The subtask view for the current window
   //               lWindowActionBehavior - set the action behavior
   //               stringDlgTag - Dialog name containing new window
   //               stringWndTag - Window name to which to transfer
   //
   //  RETURNS:      0  - Action performed
   //               -1  - Error locating window
   //               -2  - Action not located
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public String
   SetWebRedirection( View   vKZXMLPGO,
                      int    lWindowBehavior,
                      String stringDlgTag,
                      String stringWndTag )
   {
      StringBuilder sbNextJSP = new StringBuilder( "" );
   // StringBuilder sbDlgWnd = new StringBuilder( "" );
      StringBuilder sbDlgTag = new StringBuilder( "" );
      StringBuilder sbWndTag = new StringBuilder( "" );

      TraceLine( "SetWebRedirection KZXMLPGO %s   Dlg: %s   Wnd: %s", g_Actions[ lWindowBehavior ], stringDlgTag == null ? "" : stringDlgTag, stringWndTag == null ? "" : stringWndTag );
   // DisplayObjectInstance( vKZXMLPGO, "", "" );

      if ( lWindowBehavior == zWAB_StayOnWindow )
      {
         // nothing to do
      }
      else
      {
         if ( isValid( vKZXMLPGO ) )
         {
            while ( SetCursorFirstEntity( vKZXMLPGO, "NextDialogWindow", "" ) == zCURSOR_SET )
            {
               DeleteEntity( vKZXMLPGO, "NextDialogWindow", zREPOS_NONE );
            }

         // TraceLine( "SetWebRedirection KZXMLPGO Display 1" );
         // DisplayObjectInstance( vKZXMLPGO, "", "" );
            CreateEntity( vKZXMLPGO, "NextDialogWindow", zPOS_FIRST );
         // TraceLine( "SetWebRedirection KZXMLPGO Display 2" );
         // DisplayObjectInstance( vKZXMLPGO, "", "" );
            if ( StringUtils.isBlank( stringDlgTag ) == false && StringUtils.isBlank( stringWndTag ) == false )
            {
               SetAttributeFromString( vKZXMLPGO, "NextDialogWindow", "DialogName", stringDlgTag );
               SetAttributeFromString( vKZXMLPGO, "NextDialogWindow", "WindowName", stringWndTag );
            }

         // TraceLine( "SetWebRedirection KZXMLPGO Display 3" );
         // DisplayObjectInstance( vKZXMLPGO, "", "" );
            if ( lWindowBehavior == zWAB_StayOnWindowWithRefresh )
            {
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "DialogName", vKZXMLPGO, "DialogWindowList", "DialogName" );
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "WindowName", vKZXMLPGO, "DialogWindowList", "WindowName" );
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "FunctionCall", vKZXMLPGO, "DialogWindowList", "FunctionCall" );
            }
            else
            if ( lWindowBehavior == zWAB_StartTopWindow || lWindowBehavior == zWAB_ResetTopWindow )
            {
               boolean bCreated = false;

               while ( SetCursorFirstEntity( vKZXMLPGO, "DialogWindowList", "" ) == zCURSOR_SET )
               {
                  DeleteEntity( vKZXMLPGO, "DialogWindowList", zREPOS_NONE );
               }

               CreateEntity( vKZXMLPGO, "DialogWindowList", zPOS_FIRST );
               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "DialogName", stringDlgTag );
               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "WindowName", stringWndTag );

               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "FunctionCall", "StartTopSubwindow" );
               SetAttributeFromString( vKZXMLPGO, "NextDialogWindow", "FunctionCall", "StartTopSubwindow" );

               if ( CheckExistenceOfEntity( vKZXMLPGO, "TopDialogWindow" ) != zCURSOR_SET )
               {
                  bCreated = true;
                  CreateEntity( vKZXMLPGO, "TopDialogWindow", zPOS_FIRST );
               }

               if ( bCreated || lWindowBehavior == zWAB_ResetTopWindow )
               {
                  SetAttributeFromString( vKZXMLPGO, "TopDialogWindow", "DialogName", stringDlgTag );
                  SetAttributeFromString( vKZXMLPGO, "TopDialogWindow", "WindowName", stringWndTag );
                  SetAttributeFromString( vKZXMLPGO, "TopDialogWindow", "FunctionCall", "StartTopSubwindow" );
               }
            }
            else
            if ( lWindowBehavior == zWAB_StartModalSubwindow || lWindowBehavior == zWAB_StartModelessSubwindow )
            {
               CreateEntity( vKZXMLPGO, "DialogWindowList", zPOS_LAST );
               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "DialogName", stringDlgTag );
               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "WindowName", stringWndTag );

               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "FunctionCall", "StartSubwindow" );
               SetAttributeFromString( vKZXMLPGO, "NextDialogWindow", "FunctionCall", "StartSubwindow" );
            }
            else
            if ( lWindowBehavior == zWAB_ReplaceWindowWithModalWindow || lWindowBehavior == zWAB_ReplaceWindowWithModelessWindow )
            {
               if ( SetCursorLastEntity( vKZXMLPGO, "DialogWindowList", "" ) == zCURSOR_SET )
               {
                  DeleteEntity( vKZXMLPGO, "DialogWindowList", zREPOS_LAST );
               }

               CreateEntity( vKZXMLPGO, "DialogWindowList", zPOS_LAST );
               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "DialogName", stringDlgTag );
               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "WindowName", stringWndTag );

               SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "FunctionCall", "ReplaceWindow" );
               SetAttributeFromString( vKZXMLPGO, "NextDialogWindow", "FunctionCall", "ReplaceWindow" );
            }
            else
            if ( lWindowBehavior == zWAB_ReturnToParentWithRefresh || lWindowBehavior == zWAB_ReturnToParent )
            {
               int nRC = zCURSOR_UNDEFINED;

               // If a "return to" window is specified, look for it in the list.  If it is found,
               // delete all following entities.  If the "return to" window is not located, just
               // do a normal return to the previous window.
               if ( StringUtils.isBlank( stringDlgTag ) == false && StringUtils.isBlank( stringWndTag ) == false )
               {
                  nRC = SetCursorFirstEntityByString( vKZXMLPGO, "DialogWindowList", "DialogName", stringDlgTag, "" );
                  while ( nRC == zCURSOR_SET )
                  {
                     if ( CompareAttributeToString( vKZXMLPGO, "DialogWindowList", "WindowName", stringWndTag ) == 0 )
                     {
                        while ( SetCursorNextEntity( vKZXMLPGO, "DialogWindowList", "" ) == zCURSOR_SET )
                        {
                           DeleteEntity( vKZXMLPGO, "DialogWindowList", zREPOS_NONE );
                        }

                        break;  // we found the right one
                     }

                     nRC = SetCursorNextEntityByString( vKZXMLPGO, "DialogWindowList", "DialogName", stringDlgTag, "" );
                  }

                  // We won't delete all remaining entities ... just do a normal return.
               // nRC = SetCursorFirstEntity( vKZXMLPGO, "DialogWindowList", "" );
               // while ( nRC == zCURSOR_SET )
               // {
               //    DeleteEntity( vKZXMLPGO, "DialogWindowList", zREPOS_NONE );
               //    nRC = SetCursorNextEntity( vKZXMLPGO, "DialogWindowList", "" );
               // }
               }

               if ( nRC != zCURSOR_SET && SetCursorLastEntity( vKZXMLPGO, "DialogWindowList", "" ) == zCURSOR_SET )
               {
               // TraceLineS( "Before DeleteEntity DialogWindowList", "" );
               // DisplayObjectInstance( vKZXMLPGO, "", "" );
               // DisplayEntityInstance( vKZXMLPGO, "DialogWindowList" );
                  DeleteEntity( vKZXMLPGO, "DialogWindowList", zREPOS_LAST );
               // SetCursorLastEntity( vKZXMLPGO, "DialogWindowList", "" );
               // DisplayEntityInstance( vKZXMLPGO, "DialogWindowList" );
               // TraceLineS( "After DeleteEntity DialogWindowList", "" );
               // DisplayObjectInstance( vKZXMLPGO, "", "" );
               }

               if ( CheckExistenceOfEntity( vKZXMLPGO, "DialogWindowList" ) != zCURSOR_SET )
               {
                  CreateEntity( vKZXMLPGO, "DialogWindowList", zPOS_LAST );
                  SetAttributeFromAttribute( vKZXMLPGO, "DialogWindowList", "DialogName", vKZXMLPGO, "TopDialogWindow", "DialogName" );
                  SetAttributeFromAttribute( vKZXMLPGO, "DialogWindowList", "WindowName", vKZXMLPGO, "TopDialogWindow", "WindowName" );
                  SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "FunctionCall", "ReturnToParent" );
               }

               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "DialogName", vKZXMLPGO, "DialogWindowList", "DialogName" );
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "WindowName", vKZXMLPGO, "DialogWindowList", "WindowName" );
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "FunctionCall", vKZXMLPGO, "DialogWindowList", "FunctionCall" );
            }
            else
            if ( lWindowBehavior == zWAB_ReturnToTopWindow )
            {
               if ( SetCursorFirstEntity( vKZXMLPGO, "DialogWindowList", "" ) == 0 )
               {
                  while ( SetCursorNextEntity( vKZXMLPGO, "DialogWindowList", "" ) == zCURSOR_SET )
                  {
                     DeleteEntity( vKZXMLPGO, "DialogWindowList", zREPOS_NONE );
                  }

                  SetCursorFirstEntity( vKZXMLPGO, "DialogWindowList", "" );
               }

               if ( CheckExistenceOfEntity( vKZXMLPGO, "DialogWindowList" ) != zCURSOR_SET )
               {
                  CreateEntity( vKZXMLPGO, "DialogWindowList", zPOS_LAST );
                  SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "DialogName", stringDlgTag );
                  SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "WindowName", stringWndTag );
                  SetAttributeFromString( vKZXMLPGO, "DialogWindowList", "FunctionCall", "ReturnToTopWindow" );
               }

               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "DialogName", vKZXMLPGO, "DialogWindowList", "DialogName" );
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "WindowName", vKZXMLPGO, "DialogWindowList", "WindowName" );
               SetAttributeFromAttribute( vKZXMLPGO, "NextDialogWindow", "FunctionCall", vKZXMLPGO, "DialogWindowList", "FunctionCall" );
            }
            else
            {
               SetAttributeFromString( vKZXMLPGO, "NextDialogWindow", "FunctionCall", "???????" );
            }
         }
      }

      if ( isValid( vKZXMLPGO ) && CheckExistenceOfEntity( vKZXMLPGO, "NextDialogWindow" ) == zCURSOR_SET )
      {
         GetStringFromAttribute( sbDlgTag, vKZXMLPGO, "NextDialogWindow", "DialogName" );
         GetStringFromAttribute( sbWndTag, vKZXMLPGO, "NextDialogWindow", "WindowName" );
      }

      if ( sbDlgTag.length() == 0 || sbWndTag.length() == 0 )
      {
         zstrcpy( sbDlgTag, stringDlgTag );
         zstrcpy( sbWndTag, stringWndTag );
      }

      if ( sbDlgTag.length() > 0 && sbWndTag.length() > 0 )
      {
         zstrcpy( sbNextJSP, sbDlgTag );
         zstrcat( sbNextJSP, sbWndTag );
         zstrcat( sbNextJSP, ".jsp" );
      }

   // DisplayObjectInstance( vKZXMLPGO, "", "" );
      return sbNextJSP.toString();
   }

   public int
   CallDialogOperation( View vSubtask, String szFormValidationDLL, String szFormValidationOperation )
   {
      // TODO Auto-generated method stub
      return 0;
   }

   public int SaveFeedback( String viewName, String dialogTag, String windowTag, String feedback )
   {
      String szUserId = SfGetUserIdForTask( task );
      View v = task.getViewByName( viewName );

      if ( v == null )
      {
         // Assume LOD name matches view name since this is not a delivered operation.
         v = task.activateEmptyObjectInstance( viewName );
         v.setName( viewName );
      }

      // if ( v.cursor( "Feedback" ).checkExistenceOfEntity() != zCURSOR_SET )
      if ( StringUtils.isBlank( feedback ) == false )
      {
         v.cursor( "Feedback" ).createEntity( CursorPosition.LAST );
         v.cursor( "Feedback" ).setAttribute( "UserId", szUserId );
         v.cursor( "Feedback" ).setAttribute( "Dialog", dialogTag );
         v.cursor( "Feedback" ).setAttribute( "Window", windowTag );
         v.cursor( "Feedback" ).setAttribute( "Comment", feedback );
         CommitObjectInstance( v );
         return 0;
      }

      return -1;
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: CreateMemoryHandle
   //
   // N.B.  The user is responsible for calling DeleteMemoryHandle with the
   // handle returned from this call.  If not, a memory leak will occur!!!
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////

   private MemoryList memoryList = null;

   public int
   CreateMemoryHandle( int lInitialSize )
   {
      if ( memoryList == null )
      {
          memoryList = new MemoryList( );
      }

      if ( lInitialSize < 8192 )
      {
          lInitialSize = 8192;
      }

      int memoryHandle = memoryList.memoryCount.incrementAndGet( );
      memoryList.memoryMap.putIfAbsent( memoryHandle, new MemoryItem( lInitialSize ) );

      return memoryHandle;
   }

   public void
   DeleteMemoryHandle( int hMemory )
   {
      MemoryItem item = memoryList.memoryMap.get( hMemory );
      memoryList.memoryMap.remove( item );
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: AddAttributeToMemory
   //
   // Returns: >= 0 - Resultant length of string in memory
   //                 Attribute successfully retrieved
   //           < 0 - Error retrieving attribute
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AddAttributeToMemory( int    hMemory,
                         View   vApp,
                         String cpcEntityName,
                         String cpcAttributeName,
                         int    lFlag )
   {
      String pch = null;

      pch = GetStringFromAttribute( pch, vApp, cpcEntityName, cpcAttributeName );
      if ( pch != null )
      {
         return( AddStringToMemory( hMemory, pch, lFlag ) );
      }
      else
      {
         return( zCALL_ERROR );
      }
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: AddStringToMemory
   //
   // Parameters:  cpcString - address of string to add
   //                  lFlag - 0 ==> just add string as is
   //                          1 ==> add string surrounded by single quotes
   //                          2 ==> add string surrounded by double quotes
   //                          4 ==> add string and terminate with CRLF
   //
   // Returns: Resultant length of string in memory
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   AddStringToMemory( int    hMemory,
                      String cpcString,
                      int    lFlag )
   {
      MemoryItem item = memoryList.memoryMap.get( hMemory );
      return( item.AddStringToMemory( cpcString, lFlag ) );
   }

   public void
   ClearMemory( int    hMemory )
   {
      MemoryItem item = memoryList.memoryMap.get( hMemory );
      item.ClearMemory( );
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: SetMemoryToAttribute
   //
   // Returns: 0  - Attribute successfully set
   //         otw - Error setting attribute
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   SetMemoryToAttribute( int    hMemory,
                         View   vApp,
                         String cpcEntityName,
                         String cpcAttributeName )
   {
      MemoryItem item = memoryList.memoryMap.get( hMemory );
      return( SetAttributeFromString( vApp, cpcEntityName, cpcAttributeName, item.toString( ) ) );
   }

   private ClusterInfo getClusterInfo()
   {
       ClusterInfo info = task.getTask().getCacheMap( ClusterInfo.class );
       if ( info == null )
           info = task.getTask().putCacheMap( ClusterInfo.class, new ClusterInfo() );

       return info;
   }

   /**
    * Information for storing cluster information in a task.  This is used to translate between
    * VML clusters and JOE commits.
    *
    * @author dgc
    *
    */
   private static class ClusterInfo
   {
      private final AtomicInteger clusterCount = new AtomicInteger( 0 );
      private final ConcurrentHashMap<Integer, List<View>> clusterMap = new ConcurrentHashMap<Integer, List<View>>();

      private int createCluster()
      {
          int num = clusterCount.incrementAndGet();
          clusterMap.put( num, new ArrayList<View>() );
          return num;
      }

      private void deleteCluster( int clusterNum )
      {
          clusterMap.remove( clusterNum );
      }

      private List<View> getViewList( int clusterNum )
      {
          return clusterMap.get( clusterNum );
      }
   }


   /***
    * Contains information regarding memory handles for a task.
    ***/

   private static class MemoryList
   {
      private final AtomicInteger memoryCount = new AtomicInteger( 0 );
      private final ConcurrentHashMap<Integer, MemoryItem> memoryMap = new ConcurrentHashMap<Integer, MemoryItem>();
   }

   private static class MemoryItem
   {
      private CharBuffer cb = null;
      private int nLth;

      private MemoryItem( int lInitialSize )
      {
         cb = CharBuffer.allocate( lInitialSize );
         ClearMemory( );
      }

      private void ClearMemory( )
      {
         nLth = 0;
         cb.clear( );        // one of these two lines should be
         cb.put( 0, '\0' );  // enough in and of itself!!!
      }

      private int AddStringToMemory( String s, int lFlag )
      {
         int k;

         if ( (lFlag & 1) != 0 )
         {
            cb.put( nLth++, '\'' );
         }

         if ( (lFlag & 2) != 0 )
         {
            cb.put( nLth++, '"' );
         }

         for ( k = 0; k < s.length( ); k++ )
         {
            cb.put( nLth++, s.charAt( k ) );
         }

         if ( (lFlag & 1) != 0 )
         {
            cb.put( nLth++, '\'' );
         }

         if ( (lFlag & 2) != 0 )
         {
            cb.put( nLth++, '"' );
         }

         if ( (lFlag & 4) != 0 )
         {
            cb.put( nLth++, '\n' );
         }

         return nLth;  // current used length of charBuffer
      }
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////

   public static class ZNameItem
   {
      private String m_csName;
      private int    m_lFlag;

      public ZNameItem( String cpcName, int lFlag )
      {
         m_csName = cpcName;
         m_lFlag = lFlag;
      }

      public String getName( ) { return m_csName; }
      public void setName( String csName ) { m_csName = csName; }
      public int getFlag( ) { return m_lFlag; }
      public void setFlag( int lFlag ) { m_lFlag = lFlag; }
   }

   // Determine if the error list (tab-separated strings) contain the widget Tag.  If so,
   // return the message associated with that Tag, otherwise return empty string.
   public static final String CheckError( String widgetTag, String errorList )
   {
      // Parse a tab/newline-separated string (e.g. "Y\tChemicalName\tMax length exceeded\t\nMapping value in error\t\nY\tPercent\tInvalid numeric\t\n6.84%\t\n ...")
      String[] fields = errorList.split( "\t\n" );

      int arraySize = fields.length - 1; // get the size of the array
      for ( int k = 0; k < arraySize; k += 2 )  // skip every other one
      {
         String[] tagError = fields[ k ].split( "\t" );
         if ( widgetTag.compareTo( tagError[ 1 ] ) == 0 )
         {
            return( fields[ k + 1 ] );
         }
      }

      return "";
   }

/**
 * Provides facilities to sanitize HTML containing unwanted or invalid tags into clean HTML.
 * <p>
 * The sanitation process consists of the following steps:
 * <ul>
 *  <li>
 *   Find all potential HTML tags in the input text. For each tag:
 *   <ul>
 *    <li>If it is one of the allowed tags
 *     (<code>&lt;br&gt;</code>, <code>&lt;p&gt;</code>, <code>&lt;b&gt;</code>, <code>&lt;i&gt;</code>,
 *      <code>&lt;ol&gt;</code>, <code>&lt;ul&gt;</code>, <code>&lt;li&gt;</code>, <code>&lt;a&gt;</code>) then:
 *     <ul>
 *      <li>If a matching end tag is required, check that the end tag exists and is correctly nested. If not, reject the tag.
 *      <li>Check that the element is in a valid position (e.g. <code>&lt;li&gt;</code> elements must be inside <code>&lt;ul&gt;</code> or <code>&lt;ol&gt;</code> elements). If not, reject the element.
 *      <li>Keep only the allowed attributes (<code>id</code>, <code>class</code>, <code>href</code>, <code>target</code>, <code>title</code>) and strip any others.
 *      <li>Ensure all attributes are XHTML compliant (all values enclosed in double quotes and fully encoded)
 *      <li>Ensure tags are XHTML compliant (convert to lower case and add closing slash to empty element tag, e.g. <code>&lt;br /&gt;</code>)
 *     </ul>
 *    </li>
 *    <li>If it is not one of the allowed tags or was rejected for any reason:
 *     <ul>
 *      <li>If the method strips invalid markup, completely remove the tag or element from the output,
 *       otherwise encode it so that it renders verbatim.
 *     </ul>
 *    </li>
 *   </ul>
 *  </li>
 *  <li>
 *   If the <code>formatWhiteSpace</code> option is enabled:
 *   <ul>
 *    <li>Line breaks, being Carriage Return (U+000D) or Line Feed (U+000A) characters, and Form Feed characters (U+000C)
 *     are converted to "<code>&lt;br /&gt;</code>".  CR/LF pairs are treated as a single line break.
 *    <li>Multiple consecutive spaces are converted so that every second space is converted to "<code>&amp;nbsp;</code>"
 *     while ensuring the last is always a normal space.
 *    <li>Tab characters (U+0009) are converted as if they were four consecutive spaces.
 *   </ul>
 *  </li>
 *  <li>Ensure all remaining text is fully encoded.
 * </ul>
 */
   public class HTMLSanitizer
   {
      public HTMLSanitizer( String validHTML_TagList, String validHTML_AttributeList ) // private ==> not instantiable
      {
         if ( StringUtils.isBlank( validHTML_TagList ) )
         {
            VALID_ELEMENT_NAMES = ImmutableSet.of( HTMLElementName.A,
                                                   HTMLElementName.B,
                                                   HTMLElementName.BR,
                                                   HTMLElementName.I,
                                                   HTMLElementName.LI,
                                                   HTMLElementName.OL,
                                                   HTMLElementName.P,
                                                   HTMLElementName.STRONG,
                                                   HTMLElementName.SUB,
                                                   HTMLElementName.U,
                                                   HTMLElementName.UL );
         }
         else
         {
            String[] strings = validHTML_TagList.split( "[, ]" ); // split() uses reg-ex
            VALID_ELEMENT_NAMES = ImmutableSet.copyOf( strings );
         }

         if ( StringUtils.isBlank( validHTML_AttributeList ) )
         {
            VALID_ATTRIBUTE_NAMES = ImmutableSet.of( "class",
                                                     "href",
                                                     "id",
                                                     "target",
                                                     "title" );
            // If set is not immutable, use...
//               VALID_ATTRIBUTE_NAMES = Sets.newHashSet( "class",
//                                                        "href",
//                                                        "id",
//                                                        "target",
//                                                        "title" );
         }
         else
         {
            String[] strings = validHTML_TagList.split( "[, ]" );
            VALID_ATTRIBUTE_NAMES = ImmutableSet.copyOf( strings );
         }
      }

      // list of HTML elements that will be retained in the final output:
      private final Set<String> VALID_ELEMENT_NAMES;

      // list of HTML attributes that will be retained in the final output:
      private final Set<String> VALID_ATTRIBUTE_NAMES;

      private final Object VALID_MARKER=new Object();

      /**
       * Returns a sanitized version of the specified HTML, encoding any unwanted tags.
       * <p>
       * Calling this method is equivalent to {@link #encodeInvalidMarkup(String,boolean) encodeInvalidMarkup(pseudoHTML,false)}.
       * <p>
       * <dl>
       *  <dt><b>Example:</b></dt>
       *  <dd>
       *   <table border="1">
       *    <tr><td>Method call:</td><td><pre style="margin:0">HTMLSanitizer.encodeInvalidMarkup("&lt;P&gt;&lt;u&gt;Line   1&lt;/u&gt;\n&lt;b&gt;Line   2&lt;/b&gt;\n&lt;script&gt;doBadStuff()&lt;/script&gt;")</pre></td></tr>
       *    <tr><td>Output:</td><td><pre style="margin:0">&lt;p&gt;&amp;lt;u&amp;gt;Line   1&amp;lt;/u&amp;gt;\n&lt;b&gt;Line   2&lt;/b&gt;\n&amp;lt;script&amp;gt;doBadStuff()&amp;lt;/script&amp;gt;&lt;/p&gt;</pre></td></tr>
       *    <tr><td>Rendered output:</td><td><p>&lt;u&gt;Line   1&lt;/u&gt; <b>Line   2</b> &lt;script&gt;doBadStuff()&lt;/script&gt;</p></td></tr>
       *   </table>
       *   In this example:
       *   <ul>
       *    <li>The <code>&lt;P&gt;</code> tag is kept and converted to lower case
       *    <li>The optional end tag <code>&lt;/p&gt;</code> is added
       *    <li>The <code>&lt;b&gt;</code> element is kept
       *    <li>The unwanted <code>&lt;u&gt;</code> and <code>&lt;script&gt;</code> elements are encoded so that they render verbatim
       *   </ul>
       *  </dd>
       * </dl>
       *
       * @param pseudoHTML  The potentially invalid HTML to sanitize.
       * @return a sanitized version of the specified HTML, encoding any unwanted tags.
       */
      public String encodeInvalidMarkup(String pseudoHTML)
      {
         return encodeInvalidMarkup(pseudoHTML,false);
      }

      /**
       * Returns a sanitized version of the specified HTML, encoding any unwanted tags.
       * <p>
       * Encoding unwanted and invalid tags results in them appearing verbatim in the rendered output,
       * helping to highlight the problem so that the source HTML can be fixed.
       * <p>
       * Specifying a value of <code>true</code> as an argument to the <code>formatWhiteSpace</code> parameter
       * results in the formatting of white space as described in the sanitisation process in the class description above.
       * <p>
       * <dl>
       *  <dt><b>Example:</b></dt>
       *  <dd>
       *   <table border="1">
       *    <tr><td>Method call:</td><td><pre style="margin:0">HTMLSanitizer.encodeInvalidMarkup("&lt;P&gt;&lt;u&gt;Line   1&lt;/u&gt;\n&lt;b&gt;Line   2&lt;/b&gt;\n&lt;script&gt;doBadStuff()&lt;/script&gt;",true)</pre></td></tr>
       *    <tr><td>Output:</td><td><pre style="margin:0">&lt;p&gt;&amp;lt;u&amp;gt;Line &amp;nbsp; 1&amp;lt;/u&amp;gt;&lt;br /&gt;&lt;b&gt;Line &amp;nbsp; 2&lt;/b&gt;&lt;br /&gt;&amp;lt;script&amp;gt;doBadStuff()&amp;lt;/script&amp;gt;&lt;/p&gt;</pre></td></tr>
       *    <tr><td>Rendered output:</td><td><p>&lt;u&gt;Line &nbsp; 1&lt;/u&gt;<br /><b>Line &nbsp; 2</b><br />&lt;script&gt;doBadStuff()&lt;/script&gt;</p></td></tr>
       *   </table>
       *   In this example:
       *   <ul>
       *    <li>The <code>&lt;P&gt;</code> tag is kept and converted to lower case
       *    <li>The optional end tag <code>&lt;/p&gt;</code> is added
       *    <li>The <code>&lt;b&gt;</code> element is kept
       *    <li>The unwanted <code>&lt;u&gt;</code> and <code>&lt;script&gt;</code> elements are encoded so that they render verbatim
       *    <li>The line feed characters are converted to <code>&lt;br /&gt;</code> elements
       *    <li>Non-breaking spaces (<code>&amp;nbsp;</code>) are added to ensure the multiple spaces are rendered as they appear in the input.
       *   </ul>
       *  </dd>
       * </dl>
       *
       * @param pseudoHTML  The potentially invalid HTML to sanitize.
       * @param formatWhiteSpace  Specifies whether white space should be marked up in the output.
       * @return a sanitized version of the specified HTML, encoding any unwanted tags.
       */
      public String encodeInvalidMarkup(String pseudoHTML, boolean formatWhiteSpace)
      {
         return sanitize(pseudoHTML,formatWhiteSpace,false);
      }

      /**
       * Returns a sanitized version of the specified HTML, stripping any unwanted tags.
       * <p>
       * Calling this method is equivalent to {@link #stripInvalidMarkup(String,boolean) stripInvalidMarkup(pseudoHTML,false)}.
       * <p>
       * <dl>
       *  <dt><b>Example:</b></dt>
       *  <dd>
       *   <table border="1">
       *    <tr><td>Method call:</td><td><pre style="margin:0">HTMLSanitizer.stripInvalidMarkup("&lt;P&gt;&lt;u&gt;Line   1&lt;/u&gt;\n&lt;b&gt;Line   2&lt;/b&gt;\n&lt;script&gt;doBadStuff()&lt;/script&gt;")</pre></td></tr>
       *    <tr><td>Output:</td><td><pre style="margin:0">&lt;p&gt;Line   1\n&lt;b&gt;Line   2&lt;/b&gt;\n&lt;/p&gt;</pre></td></tr>
       *    <tr><td>Rendered output:</td><td><p>Line   1 <b>Line   2</b> </p></td></tr>
       *   </table>
       *   In this example:
       *   <ul>
       *    <li>The <code>&lt;P&gt;</code> tag is kept and converted to lower case
       *    <li>The optional end tag <code>&lt;/p&gt;</code> is added
       *    <li>The <code>&lt;b&gt;</code> element is kept
       *    <li>The unwanted <code>&lt;u&gt;</code> and <code>&lt;script&gt;</code> elements are stripped from the output
       *   </ul>
       *  </dd>
       * </dl>
       *
       * @param pseudoHTML  The potentially invalid HTML to sanitize.
       * @return a sanitized version of the specified HTML, stripping any unwanted tags.
       */
      public String stripInvalidMarkup(String pseudoHTML)
      {
         return stripInvalidMarkup(pseudoHTML,false);
      }

      /**
       * Returns a sanitized version of the specified HTML, stripping any unwanted tags.
       * <p>
       * Stripping unwanted and invalid tags is the preferred option if the output is for public consumption.
       * <p>
       * Specifying a value of <code>true</code> as an argument to the <code>formatWhiteSpace</code> parameter
       * results in the formatting of white space as described in the sanitisation process in the class description above.
       * <p>
       * <dl>
       *  <dt><b>Example:</b></dt>
       *  <dd>
       *   <table border="1">
       *    <tr><td>Method call:</td><td><pre style="margin:0">HTMLSanitizer.stripInvalidMarkup("&lt;P&gt;&lt;u&gt;Line   1&lt;/u&gt;\n&lt;b&gt;Line   2&lt;/b&gt;\n&lt;script&gt;doBadStuff()&lt;/script&gt;",true)</pre></td></tr>
       *    <tr><td>Output:</td><td><pre style="margin:0">&lt;p&gt;Line &amp;nbsp; 1&lt;br /&gt;&lt;b&gt;Line &amp;nbsp; 2&lt;/b&gt;&lt;br /&gt;&lt;/p&gt;</pre></td></tr>
       *    <tr><td>Rendered output:</td><td><p>Line &nbsp; 1<br /><b>Line &nbsp; 2</b><br /></p></td></tr>
       *   </table>
       *   In this example:
       *   <ul>
       *    <li>The <code>&lt;P&gt;</code> tag is kept and converted to lower case
       *    <li>The optional end tag <code>&lt;/p&gt;</code> is added
       *    <li>The <code>&lt;b&gt;</code> element is kept
       *    <li>The unwanted <code>&lt;u&gt;</code> and <code>&lt;script&gt;</code> elements are stripped from the output
       *    <li>The line feed characters are converted to <code>&lt;br /&gt;</code> elements
       *    <li>Non-breaking spaces (<code>&amp;nbsp;</code>) are added to ensure the multiple spaces are rendered as they appear in the input.
       *   </ul>
       *  </dd>
       * </dl>
       *
       * @param pseudoHTML  The potentially invalid HTML to sanitize.
       * @param formatWhiteSpace  Specifies whether white space should be marked up in the output.
       * @return a sanitized version of the specified HTML, stripping any unwanted tags.
       */
      public String stripInvalidMarkup(String pseudoHTML, boolean formatWhiteSpace)
      {
         return sanitize(pseudoHTML,formatWhiteSpace,true);
      }

      private String sanitize(String pseudoHTML, boolean formatWhiteSpace, boolean stripInvalidElements)
      {
         Source source=new Source(pseudoHTML);
         source.fullSequentialParse();
         OutputDocument outputDocument=new OutputDocument(source);
         List<Tag> tags=source.getAllTags();
         int pos=0;
         for (Tag tag : tags)
         {
            if (processTag(tag,outputDocument))
            {
              tag.setUserData(VALID_MARKER);
            }
            else
            {
               if (!stripInvalidElements)
               {
                  continue; // element will be encoded along with surrounding text
               }

               outputDocument.remove(tag);
            }

            reencodeTextSegment(source,outputDocument,pos,tag.getBegin(),formatWhiteSpace);
            pos=tag.getEnd();
         }

         reencodeTextSegment(source,outputDocument,pos,source.getEnd(),formatWhiteSpace);
         return outputDocument.toString();
      }

      private boolean processTag(Tag tag, OutputDocument outputDocument)
      {
         String elementName=tag.getName();
         if (!VALID_ELEMENT_NAMES.contains(elementName))
         {
            return false;
         }

         if (tag.getTagType()==StartTagType.NORMAL)
         {
            Element element=tag.getElement();
            if (HTMLElements.getEndTagRequiredElementNames().contains(elementName))
            {
               if (element.getEndTag()==null)
               {
                  return false; // reject start tag if its required end tag is missing
               }
            }
            else
            if (HTMLElements.getEndTagOptionalElementNames().contains(elementName))
            {
               if (elementName==HTMLElementName.LI && !isValidLITag(tag))
               {
                  return false; // reject invalid LI tags
               }

               if (element.getEndTag()==null)
               {
                  outputDocument.insert(element.getEnd(),getEndTagHTML(elementName)); // insert optional end tag if it is missing
               }
            }

            outputDocument.replace(tag,getStartTagHTML(element.getStartTag()));
         }
         else
         if (tag.getTagType()==EndTagType.NORMAL)
         {
            if (tag.getElement()==null)
            {
               return false;
            } // reject end tags that aren't associated with a start tag

            if (elementName==HTMLElementName.LI && !isValidLITag(tag))
            {
               return false;
            } // reject invalid LI tags

            outputDocument.replace(tag,getEndTagHTML(elementName));
         }
         else
         {
            return false; // reject abnormal tags
         }

         return true;
      }

      private boolean isValidLITag(Tag tag)
      {
         Element parentElement=tag.getElement().getParentElement();
         if (parentElement==null)
         {
            return false; // ignore LI elements without a parent
         }

         if (parentElement.getStartTag().getUserData()!=VALID_MARKER)
         {
            return false; // ignore LI elements who's parent is not valid
         }

         return parentElement.getName()==HTMLElementName.UL || parentElement.getName()==HTMLElementName.OL; // only accept LI tags who's immediate parent is UL or OL.
      }

      private void reencodeTextSegment(Source source, OutputDocument outputDocument, int begin, int end, boolean formatWhiteSpace)
      {
        if (begin>=end)
        {
           return;
        }

        Segment textSegment=new Segment(source,begin,end);
        String decodedText=CharacterReference.decode(textSegment);
        String encodedText=formatWhiteSpace ? CharacterReference.encodeWithWhiteSpaceFormatting(decodedText) : CharacterReference.encode(decodedText);
        outputDocument.replace(textSegment,encodedText);
      }

      private CharSequence getStartTagHTML(StartTag startTag)
      {
         // tidies and filters out non-approved attributes
         StringBuilder sb = new StringBuilder();
         sb.append('<').append(startTag.getName());
         for (Attribute attribute : startTag.getAttributes())
         {
            if (VALID_ATTRIBUTE_NAMES.contains(attribute.getKey()))
            {
               sb.append(' ').append(attribute.getName());
               if (attribute.getValue()!=null)
               {
                  sb.append("=\"");
                  sb.append(CharacterReference.encode(attribute.getValue()));
                  sb.append('"');
               }
            }
         }

         if (startTag.getElement().getEndTag()==null && !HTMLElements.getEndTagOptionalElementNames().contains(startTag.getName()))
         {
            sb.append(" /");
         }

         sb.append('>');
         return sb;
      }

      private String getEndTagHTML(String tagName)
      {
         return "</" + tagName + '>';
      }

      //////////////////////////////////////////////////////////////////////////////////////
      // THE METHODS BELOW ARE USED ONLY FOR DEMONSTRATING THE FUNCTIONALITY OF THE CLASS //
      //////////////////////////////////////////////////////////////////////////////////////
      // See test/src/samples/HTMLSanitizerTest.java for a comprehensive test suite.

      public void testHTMLSanitizer() //throws Exception
      {
         System.out.println("Examples of HTMLSanitizer.encodeInvalidMarkup:");
         System.out.println("----------------------------------------------\n");

         displayEncodeInvalidMarkup("ab & c","encode text");
         displayEncodeInvalidMarkup("abc <u>def</u> geh","<U> element not allowed");
         displayEncodeInvalidMarkup("<p>abc","add optional end tag");
         displayEncodeInvalidMarkup("<script>abc</script>","remove potentially dangerous script");
         displayEncodeInvalidMarkup("<p class=\"xyz\" onmouseover=\"nastyscript\">abc</p>","keep approved attributes but strip non-approved attributes");
         displayEncodeInvalidMarkup("<p id=abc class='xyz'>abc</p>","tidy up attributes to make them XHTML compliant");
         displayEncodeInvalidMarkup("List:<ul><li>A</li><li>B<li>C</ul>","inserts optional end tags");

         System.out.println("Examples of HTMLSanitizer.stripInvalidMarkup:");
         System.out.println("---------------------------------------------\n");

         displayStripInvalidMarkup("ab & c","encode text");
         displayStripInvalidMarkup("abc <u>def</u> geh","<U> element not allowed");
         displayStripInvalidMarkup("<p>abc","add optional end tag");
         displayStripInvalidMarkup("<script>abc</script>","remove potentially dangerous script");
         displayStripInvalidMarkup("<p class=\"xyz\" onmouseover=\"nastyscript\">abc</p>","keep approved attributes but strip non-approved attributes");
         displayStripInvalidMarkup("<p id=abc class='xyz'>abc</p>","tidy up attributes to make them XHTML compliant");
         displayStripInvalidMarkup("List:<ul><li>A</li><li>B<li>C</ul>","inserts optional end tags");
         displayStripInvalidMarkup("List:<li>A</li><li>B<li>C","missing required <UL> or <OL> element");
         displayStripInvalidMarkup("List:<ul><li>A</li><b><li>B</b><li>C</ul>","<LI> is invalid as it is not directly under <UL> or <OL>");

         System.out.println("Examples of HTMLSanitizer.stripInvalidMarkup with formatWhiteSpace=true:");
         System.out.println("------------------------------------------------------------------------\n");

         displayStripInvalidMarkup("abc\ndef",true,"convert LF to <BR>");
         displayStripInvalidMarkup("    abc",true,"ensure consecutive spaces are rendered");
         displayStripInvalidMarkup("\tabc",true,"convert TAB to equivalent of four spaces");
      }

      private void displayEncodeInvalidMarkup(String input, String explanation)
      {
         display(input, explanation, encodeInvalidMarkup(input));
      }

      private void displayStripInvalidMarkup(String input, String explanation)
      {
         display(input, explanation, stripInvalidMarkup(input));
      }

      private void displayStripInvalidMarkup(String input, boolean formatWhiteSpace, String explanation)
      {
         display(input, explanation, stripInvalidMarkup(input, formatWhiteSpace));
      }

      private void display(String input, String explanation, String output)
      {
         System.out.println(explanation+":\ninput : "+input+"\noutput: "+output+"\n");
      }
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // RemoveFormattingFromHTML_Attr
   //
   //    vHTML                 HTML View
   //    cpcHTML_Entity        HTML entity
   //    cpcHTML_Attribute     HTML attribute
   //    cpcValidHTML_TagList  Valid HTML tags (comma separated list that will
   //                          not be removed)
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public int
   RemoveFormattingFromHTML_Attr( View   vHTML,
                                  String  cpcHTML_Entity,
                                  String  cpcHTML_Attribute,
                                  String  cpcValidHTML_TagList )
   {
      String pchHTML = null;
      int    lth;

      pchHTML = GetAddrForAttribute( pchHTML, vHTML, cpcHTML_Entity, cpcHTML_Attribute );
      lth = zstrlen( pchHTML );
      String pchUnformattedHTML = pchHTML;
      pchHTML = RemoveFormattingFromHTML_Text( vHTML, pchUnformattedHTML, lth, cpcValidHTML_TagList );
      SetAttributeFromString( vHTML, cpcHTML_Entity, cpcHTML_Attribute, pchHTML );

      // done with pchUnformattedHTML
      return 0;
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   //
   // RemoveFormattingFromHTML_Text
   //
   //    vTaskView             Task View
   //    pchHTML_Text          Text potentially containing HTML tags
   //    lMaxLth               Maximum length of HTML text
   //    cpcValidHTML_TagList  Valid HTML tags (comma separated list of tags
   //                          that will not be removed)
   //
   ///////////////////////////////////////////////////////////////////////////////////////////////////////
   public String
   RemoveFormattingFromHTML_Text( View    vTaskView,
                                  String  strHTML_Text,
                                  int     lMaxLth,
                                  String  strValidHTML_TagList )
   {
      if ( StringUtils.isBlank( strHTML_Text ) )
      {
         TraceLine( "RemoveFormattingFromHTML_Text HTML is empty", "" );
         return "";
      }
      else
      {
         HTMLSanitizer xx = new HTMLSanitizer( strValidHTML_TagList, "" );
         String strPage = xx.sanitize( strHTML_Text, true, true );

         // done with pParseHTML

         int lth = strPage.length( );
         if ( lth > lMaxLth )
         {
            TraceLine( "RemoveFormattingFromHTML length: %d  exceeded maximum: %d", lth, lMaxLth );
            TraceLineS( "RemoveFormattingFromHTML original: ", strHTML_Text );
            TraceLineS( "RemoveFormattingFromHTML reformat: ", strPage );
         }

         return strPage;
      }
   }

   public final String
   SanitizeHTML( String strHTML, String validHTML_TagList, String validHTML_AttributeList )
   {
      if ( StringUtils.isBlank( strHTML ) )
      {
      // TraceLine( "SanitizeHTML HTML is empty", "" );
         return "";
      }
      else
      {
         HTMLSanitizer xx = new HTMLSanitizer( validHTML_TagList, validHTML_AttributeList );
         String strPage = xx.stripInvalidMarkup( strHTML, false );

         // done with pParseHTML
         return strPage;
      }
   }

   public int
   ConvertXML_SpecialCharacters( View vReportDef, StringBuilder sb_szConvertedString, StringBuilder sb_szSourceString, int MaxLth )
   {
      String szOutput=sb_szSourceString.toString();

      szOutput = szOutput.replace("&","&amp;");
      szOutput = szOutput.replace("<","&lt;");
      szOutput = szOutput.replace(">","&gt;");
      szOutput = szOutput.replace("\"","&quot;");
      szOutput = szOutput.replace("\'","&apos;");

      // KJS 12/17/13 - I am using &#xA; to put in a newline character. But I don't
      // want the & to have been switched to &amp. See how this works...
      //&#xA;
      szOutput = szOutput.replace("&amp;#xA;","&#xA;");

      sb_szConvertedString.append(szOutput);

      return( 0 );
   }

   // NOT EXACTLY SURE WHAT TO DO HERE... THE FOLLOWING CODE IS TECHNICALLY CREATED FROM TZ VML CODE
   // AND IS IN ZeidonTools... but then we want to call that code from here... but I'm not sure that
   // DG wants the ZeidonTools to need to be in JOE... which perhaps vmlOperations whould be moved
   // out as well... So I am copying these three Operations into vmlOperations temporarily to see how things
   // work...

   //:   VIEW vReportDef     BASED ON LOD TZRPSRCO


   public static String
   FindOpenFile( Task task)
   {
      String szDir;
      String szFileName;
      String szFileExt;

      szFileName = null;
      View vKZXMLPGO = task.getViewByName( WEB_SESSION_VIEW_NAME );

      szFileName = vKZXMLPGO.cursor("Session").getStringFromAttribute("PrintFileName");
      szFileExt = vKZXMLPGO.cursor("Session").getStringFromAttribute("PrintFileType");
      if ( szFileName != null && szFileName != "" )
      {
         szDir = "./pdf/";
         szFileName = szDir + szFileName;
         if ( szFileName.indexOf(".") < 0 )
         {
            szFileName = szFileName + ".";

            if ( szFileExt != null )
            {
               szFileName = szFileName + szFileExt;
            }
            else
            {
               szFileName = szFileName + "html";
            }
         }
      }
      else
      {
         szFileName = "";
      }

      //TraceLineS( "Get Open File ===========>> ", szFileName );
      vKZXMLPGO.cursor("Session").setAttribute("PrintFileName", "");
      vKZXMLPGO.cursor("Session").setAttribute("PrintFileType", "");

      // Don't think we will need to do the following...?
      /*
         nRC = SysMakeWebFileName( szTemp, vSubtask, 0 );
         strcat( szTemp, ".html" );
         nRC = GetViewByName( &vTemp, szTemp, vSubtask, zLEVEL_TASK );
         if ( vTemp )
            nRC = DropNameForView( vTemp, szTemp, vSubtask, zLEVEL_TASK );
         else
            szTemp[ 0 ] = 0;
      */

      return( szFileName ) ;
   }

   public static String
   GetFocusCtrl( Task task, String szDialog, String szWindow )
   {
      String szDir;
      String szFileName;
      String szFileExt;

      szFileName = null;
      View vKZXMLPGO = task.getViewByName( WEB_SESSION_VIEW_NAME );

      if ( vKZXMLPGO.cursor( "Dialog" ).setFirst( "DialogName", szDialog ).isSet() )
      {
         if ( vKZXMLPGO.cursor( "Window" ).setFirst( "WindowName", szWindow ).isSet() )
         {
            szFileName = vKZXMLPGO.cursor("Window").getStringFromAttribute("FocusCtrl");
         }
      }

      return( szFileName ) ;
   }

   public void
   WriteOiToJson( View view, String filename, int control )
   {
       view.serializeOi().asJson()
                          .setFlags( WriteOiFlags.convertLongFlags( control ) )
                          .toFile( filename );
   }

   public View
   ActivateOiFromJson( View view, String filename ) throws Exception
   {
       return new DeserializeOi( view )
                       .asJson()
                       .fromResource( filename )
                       .activateFirst();
   }

   private final class TextTransfer implements ClipboardOwner
   {
     /**
      * Empty implementation of the ClipboardOwner interface.
      */
      @Override
    public void lostOwnership( Clipboard aClipboard, Transferable aContents)
      {
         // do nothing
      }

     /**
      * Place a String on the clipboard, and make this class the owner of the Clipboard's contents.
      */
      public void setClipboardContents( String str )
      {
         StringSelection stringSelection = new StringSelection( str );
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
         clipboard.setContents( stringSelection, this );
      }

     /**
      * Get the String residing on the clipboard.
      *
      * @return any text found on the Clipboard; if none found, return an empty String.
      */
      public String getClipboardContents()
      {
         String result = "";
         Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

         // odd: the Object param of getContents is not currently used
         Transferable contents = clipboard.getContents(null);
         boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
         if ( hasTransferableText )
         {
            try
            {
               result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (UnsupportedFlavorException ex)
            {
               //highly unlikely since we are using a standard DataFlavor
               System.out.println(ex);
               ex.printStackTrace();
            }
            catch (IOException ex)
            {
               System.out.println(ex);
               ex.printStackTrace();
            }
         }

         return result;
      }
   }

   public String SysGetClipboardText( )
   {
      TextTransfer textTransfer = new TextTransfer( );
      return textTransfer.getClipboardContents( );
   }

}
/*
protected static final char[] g_specialChar = {
      '<':'&lt;', '>':'&gt;', '&':'&amp;', '"':'&quot;', "'":'&#039;', '#':'&#035;', '%':'&#037;'
   };

   public String SysEncodeHTML( String str )
   {
     return str.replace( /[<&>'"#%]/g, function(s) { return g_specialChar[s]; } );
   }

<html>
<head>
<script type="text/javascript">
function ConvChar( str ) {
  c = {'<':'&lt;', '>':'&gt;', '&':'&amp;', '"':'&quot;', "'":'&#039;',
    '#':'&#035;', '%':'&#037;' };
  return str.replace( /[<&>'"#%]/g, function(s) { return c[s]; } );
}
function displaymessage()
{
alert( ConvChar('<-"-&-"->-<-\'-#-\'->%') );
}
</script>
</head>

<body>
<form>
<input type="button" value="Click me!" onclick="displaymessage()" />
</form>

<p>By pressing the button above, a function will be called. The function will alert a message.</p>

</body>
</html>
*/

// strErrorMapValue = strErrorMapValue.replace("/&/g", "&amp;").replace("/>/g", "&gt;").replace("/</g", "&lt;").replace("/\"/g", "&quot;").replace("/%/g", "&#037;");

