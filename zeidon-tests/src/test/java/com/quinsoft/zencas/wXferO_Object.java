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

package com.quinsoft.zencas;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class wXferO_Object extends VmlObjectOperations
{
   private final KZOEP1AA m_KZOEP1AA;
   public wXferO_Object( View view )
   {
      super( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:DERIVED ATTRIBUTE OPERATION
//:dCurrentDateTime( VIEW wXferO BASED ON LOD wXferO,
//:                  STRING ( 32 ) InternalEntityStructure,
//:                  STRING ( 32 ) InternalAttribStructure,
//:                  SHORT GetOrSetFlag )

//:   STRING (  18  ) szDateTime
public int 
owXferO_dCurrentDateTime( View     wXferO,
                          String InternalEntityStructure,
                          String InternalAttribStructure,
                          Integer   GetOrSetFlag )
{
   String   szDateTime = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:SysGetDateTime( szDateTime )
          {StringBuilder sb_szDateTime;
         if ( szDateTime == null )
            sb_szDateTime = new StringBuilder( 32 );
         else
            sb_szDateTime = new StringBuilder( szDateTime );
                  m_KZOEP1AA.SysGetDateTime( sb_szDateTime );
         szDateTime = sb_szDateTime.toString( );}
         //:StoreStringInRecord ( wXferO,
         //:                      InternalEntityStructure, InternalAttribStructure, szDateTime )
         StoreStringInRecord( wXferO, InternalEntityStructure, InternalAttribStructure, szDateTime );
         break ;
      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCurrentTime( VIEW wXferO BASED ON LOD wXferO,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )
//:   STRING (  18  ) szTime
public int 
owXferO_dCurrentTime( View     wXferO,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{
   String   szTime = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:szTime = wXferO.Root.dCurrentDateTime
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTime;
         if ( szTime == null )
            sb_szTime = new StringBuilder( 32 );
         else
            sb_szTime = new StringBuilder( szTime );
                   GetVariableFromAttribute( sb_szTime, mi_lTempInteger_0, 'S', 19, wXferO, "Root", "dCurrentDateTime", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTime = sb_szTime.toString( );}
         //:StoreStringInRecord ( wXferO,
         //:                      InternalEntityStructure, InternalAttribStructure, szTime )
         StoreStringInRecord( wXferO, InternalEntityStructure, InternalAttribStructure, szTime );
         break ;
      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dSalaryDataName( VIEW wXferO BASED ON LOD wXferO,
//:                 STRING ( 32 ) InternalEntityStructure,
//:                 STRING ( 32 ) InternalAttribStructure,
//:                 SHORT GetOrSetFlag )

//:   STRING ( 20 ) szDisplayName
public int 
owXferO_dSalaryDataName( View     wXferO,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szDisplayName = null;
   //:STRING ( 10 ) szDate
   String   szDate = null;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

         //:GetStringFromAttributeByContext( szDate, wXferO, "StandardSalaryData", "EffectiveBeginDate", "", 10 )
         {StringBuilder sb_szDate;
         if ( szDate == null )
            sb_szDate = new StringBuilder( 32 );
         else
            sb_szDate = new StringBuilder( szDate );
                   GetStringFromAttributeByContext( sb_szDate, wXferO, "StandardSalaryData", "EffectiveBeginDate", "", 10 );
         szDate = sb_szDate.toString( );}
         //:szDisplayName = wXferO.StandardSalaryData.Year + " - " + szDate
         {StringBuilder sb_szDisplayName;
         if ( szDisplayName == null )
            sb_szDisplayName = new StringBuilder( 32 );
         else
            sb_szDisplayName = new StringBuilder( szDisplayName );
                   GetStringFromAttribute( sb_szDisplayName, wXferO, "StandardSalaryData", "Year" );
         szDisplayName = sb_szDisplayName.toString( );}
          {StringBuilder sb_szDisplayName;
         if ( szDisplayName == null )
            sb_szDisplayName = new StringBuilder( 32 );
         else
            sb_szDisplayName = new StringBuilder( szDisplayName );
                  ZeidonStringConcat( sb_szDisplayName, 1, 0, " - ", 1, 0, 21 );
         szDisplayName = sb_szDisplayName.toString( );}
          {StringBuilder sb_szDisplayName;
         if ( szDisplayName == null )
            sb_szDisplayName = new StringBuilder( 32 );
         else
            sb_szDisplayName = new StringBuilder( szDisplayName );
                  ZeidonStringConcat( sb_szDisplayName, 1, 0, szDate, 1, 0, 21 );
         szDisplayName = sb_szDisplayName.toString( );}

         //:StoreStringInRecord ( wXferO,
         //:                   InternalEntityStructure, InternalAttribStructure, szDisplayName )
         StoreStringInRecord( wXferO, InternalEntityStructure, InternalAttribStructure, szDisplayName );
         break ;
      //:              

      //:  /* end zDERIVED_GET */
      //:OF   zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:     /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dCurrentDate( VIEW wXferO BASED ON LOD wXferO,
//:              STRING ( 32 ) InternalEntityStructure,
//:              STRING ( 32 ) InternalAttribStructure,
//:              SHORT GetOrSetFlag )
//:   STRING (  18  ) szDate
public int 
owXferO_dCurrentDate( View     wXferO,
                      String InternalEntityStructure,
                      String InternalAttribStructure,
                      Integer   GetOrSetFlag )
{
   String   szDate = null;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF zDERIVED_GET:
      case zDERIVED_GET :
         //:szDate = wXferO.Root.dCurrentDateTime
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szDate;
         if ( szDate == null )
            sb_szDate = new StringBuilder( 32 );
         else
            sb_szDate = new StringBuilder( szDate );
                   GetVariableFromAttribute( sb_szDate, mi_lTempInteger_0, 'S', 19, wXferO, "Root", "dCurrentDateTime", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szDate = sb_szDate.toString( );}
         //:StoreStringInRecord ( wXferO,
         //:                      InternalEntityStructure, InternalAttribStructure, szDate )
         StoreStringInRecord( wXferO, InternalEntityStructure, InternalAttribStructure, szDate );
         break ;
      //:/* end zDERIVED_GET */
      //:OF zDERIVED_SET:
      case zDERIVED_SET :
         break ;
   } 


   //:   /* end zDERIVED_SET */
   //:END  /* case */
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
owXferO_SetUpAllQueryValues( View     wXferO )
{
   int      lTempInteger_0 = 0;
   int      RESULT = 0;

   //:SetUpAllQueryValues( VIEW wXferO BASED ON LOD wXferO )

   //:// Set up the necessary QueryObject entries in wXferO for the Reports and Mailing interfaces.
   //:IF wXferO.QueryValues DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( wXferO, "QueryValues" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY wXferO.QueryValues   
      RESULT = CreateEntity( wXferO, "QueryValues", zPOS_AFTER );
   } 

   //:END
   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Prospects"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Prospects", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Prospects"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Prospects" );
      //:wXferO.QueryObject.ObjectName      = "qProspct"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qProspct" );
   } 

   //:END
   //:wXferO.QueryObject.DisplayEntityName    = "Person"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Person" );
   //:wXferO.QueryObject.DisplayAttributeName = "dFullName"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dFullName" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "People"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "People", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "People"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "People" );
      //:wXferO.QueryObject.ObjectName      = "qPerson"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qPerson" );
   } 

   //:END
   //:wXferO.QueryObject.DisplayEntityName    = "Person"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Person" );
   //:wXferO.QueryObject.DisplayAttributeName = "dFullName"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dFullName" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Churches"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Churches", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Churches"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Churches" );
      //:wXferO.QueryObject.ObjectName      = "qChurch"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qChurch" );
   } 

   //:END
   //:wXferO.QueryObject.DisplayEntityName    = "Church"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Church" );
   //:wXferO.QueryObject.DisplayAttributeName = "Name"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "Name" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Organizations"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Organizations", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Organizations"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Organizations" );
      //:wXferO.QueryObject.ObjectName      = "qOrg"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qOrg" );
   } 

   //:END
   //:wXferO.QueryObject.DisplayEntityName    = "Organization"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Organization" );
   //:wXferO.QueryObject.DisplayAttributeName = "Name"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "Name" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Schools"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Schools", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Schools"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Schools" );
      //:wXferO.QueryObject.ObjectName      = "qSchool"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qSchool" );
   } 

   //:END
   //:wXferO.QueryObject.DisplayEntityName    = "School"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "School" );
   //:wXferO.QueryObject.DisplayAttributeName = "Name"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "Name" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Alumni"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Alumni", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Alumni"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Alumni" );
      //:wXferO.QueryObject.ObjectName      = "qAlumni"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qAlumni" );
   } 

   //:END
   //:wXferO.QueryObject.DisplayEntityName    = "Person"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Person" );
   //:wXferO.QueryObject.DisplayAttributeName = "dFullName"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dFullName" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Donors"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Donors", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Donors"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Donors" );
      //:wXferO.QueryObject.ObjectName      = "qDonor"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qDonor" );
   } 

   //:END 
   //:wXferO.QueryObject.DisplayEntityName    = "Donor"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Donor" );
   //:wXferO.QueryObject.DisplayAttributeName = "dName"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dName" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Student Life"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Student Life", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Student Life"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Student Life" );
      //:wXferO.QueryObject.ObjectName      = "qStudent"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qStudent" );
   } 

   //:END 
   //:wXferO.QueryObject.DisplayEntityName    = "Person"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Person" );
   //:wXferO.QueryObject.DisplayAttributeName = "dFullName"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dFullName" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Student Academics"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Student Academics", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Student Academics"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Student Academics" );
      //:wXferO.QueryObject.ObjectName      = "qStudenA"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qStudenA" );
   } 

   //:END 
   //:wXferO.QueryObject.DisplayEntityName    = "Person"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "Person" );
   //:wXferO.QueryObject.DisplayAttributeName = "dFullName"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dFullName" );

   //:SET CURSOR FIRST wXferO.QueryObject WHERE wXferO.QueryObject.ExternalName = "Lead Faculty/Classes"
   RESULT = SetCursorFirstEntityByString( wXferO, "QueryObject", "ExternalName", "Lead Faculty/Classes", "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:CREATE ENTITY wXferO.QueryObject
      RESULT = CreateEntity( wXferO, "QueryObject", zPOS_AFTER );
      //:wXferO.QueryObject.ExternalName    = "Lead Faculty"
      SetAttributeFromString( wXferO, "QueryObject", "ExternalName", "Lead Faculty" );
      //:wXferO.QueryObject.ObjectName      = "qLeadFac"
      SetAttributeFromString( wXferO, "QueryObject", "ObjectName", "qLeadFac" );
   } 

   //:END 
   //:wXferO.QueryObject.DisplayEntityName    = "FacultyPerson"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayEntityName", "FacultyPerson" );
   //:wXferO.QueryObject.DisplayAttributeName = "dFullNameLFM"
   SetAttributeFromString( wXferO, "QueryObject", "DisplayAttributeName", "dFullNameLFM" );
   return( 0 );
// END
} 



}
