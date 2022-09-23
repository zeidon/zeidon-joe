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

package  com.quinsoft.nazsis;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.nazsis.zqSumR_Object;
import com.quinsoft.nazsis.zGLOBAL6_Operation;
import com.quinsoft.nazsis.ZGLOBALE_Operation;
import com.quinsoft.nazsis.ZGLOBAL1_Operation;
import com.quinsoft.nazsis.ZGLOBAL2_Operation;
import com.quinsoft.nazsis.mChart_Object;
import com.quinsoft.nazsis.zGLOBALQ_Operation;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class zqFrame_Object extends VmlObjectOperations
{
   private final ZDRVROPR m_ZDRVROPR;
   public zqFrame_Object( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:TRANSFORMATION OPERATION
//:BuildQualFromFrame( VIEW zqFrame   BASED ON LOD zqFrame,
//:                    VIEW vQualObject,
//:                    VIEW qConvertData )

//:   VIEW wXferO   REGISTERED AS wXferO
public int 
ozqFrame_BuildQualFromFrame( View     zqFrame,
                             zVIEW    vQualObject,
                             View     qConvertData )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW zqFrameT BASED ON LOD  zqFrame
   zVIEW    zqFrameT = new zVIEW( );
   //:VIEW vQualObject2
   zVIEW    vQualObject2 = new zVIEW( );
   //:STRING ( 10 )  szSearchType
   String   szSearchType = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )  szAttributeName
   String   szAttributeName = null;
   //:STRING ( 32 )  szQualEntityName
   String   szQualEntityName = null;
   //:STRING ( 1 )   szDerivedAttributeFlag
   String   szDerivedAttributeFlag = null;
   //:STRING ( 1 )   szOR_OperatorFlag
   String   szOR_OperatorFlag = null;
   //:STRING ( 1 )   szDatabaseAttributeFlag
   String   szDatabaseAttributeFlag = null;
   //:STRING ( 8 )   szSeasonalAddressDate
   String   szSeasonalAddressDate = null;
   //:STRING ( 10 )  szSeasonalAddressParameterValue
   String   szSeasonalAddressParameterValue = null;
   //:STRING ( 200 ) Msg
   String   Msg = null;
   //:SHORT   nRC
   int      nRC = 0;
   //:INTEGER DisplayCount
   int      DisplayCount = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:// Build the qualification object KZDBHQUA from data in the zqFrame object.

   //:FOR EACH zqFrame.PostActivateQual
   RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQual", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.PostActivateQual NONE
      RESULT = DeleteEntity( zqFrame, "PostActivateQual", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "PostActivateQual", "" );
   } 

   //:END
   //:SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", zqFrame, zMULTIPLE )
   SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", zqFrame, zMULTIPLE );
   //:NAME VIEW vQualObject "vQualObject"
   SetNameForView( vQualObject, "vQualObject", null, zLEVEL_TASK );

   //:// Create root EntitySpec.
   //:CREATE ENTITY vQualObject.EntitySpec
   RESULT = CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   //:vQualObject.EntitySpec.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName
   SetAttributeFromAttribute( vQualObject, "EntitySpec", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" );

   //:// Check for Seasonal Date qualification. If there is a date specified, set it in wXferO and temporarily remove it 
   //:// from zqFrame. We will add it back to zqFrame after we've formatted the Query.
   //:/*wXferO.Root.SeasonalAddressDate = ""
   //:SET CURSOR FIRST zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.AttributeName = "wSeasonalAddressDate"
   //:IF RESULT >= zCURSOR_SET
   //:   IF zqFrame.GeneralParameter.Value != ""
   //:      szSeasonalAddressParameterValue = zqFrame.GeneralParameter.Value
   //:      SetAttrFromStrByContext( wXferO, "Root", "SeasonalAddressDate", szSeasonalAddressParameterValue, "" )
   //:      zqFrame.GeneralParameter.Value      = ""
   //:      zqFrame.GeneralParameter.SearchType = ""
   //:   END
   //:END*/

   //:// Build qualification for root entity.
   //:IF zqFrame.Component EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "Component" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// The root qualification is driven from the Boolean Expression values.
      //:// We will first evaluate the expression to see if there are any OR conditions at the
      //:// top boolean level (assuming anything inside parens is at a lower level), along with
      //:// derived attributes. If there are, then we cannot generate any SQL qualifications at all.
      //:// The first two examples below must be evaluated completely at the post-activate level if C3
      //:// is a derived attribute.
      //:// In the third example, SQL can be generated for conditions C1 and C2, but C3 and C4 must be evaluated
      //:// at the post-activate level if C3 is a derived attribute.
      //:// Example 1: C1 AND C2 OR C3"
      //:// Example 2: C1 AND C2 OR (C3 AND C4)
      //:// Example 3: C1 AND C2 AND (C3 OR C4)

      //:// Delete work Component entities for PostActivate criteria.
      //:FOR EACH zqFrame.PostActivateComponent 
      RESULT = SetCursorFirstEntity( zqFrame, "PostActivateComponent", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY zqFrame.PostActivateComponent NONE
         RESULT = DeleteEntity( zqFrame, "PostActivateComponent", zREPOS_NONE );
         RESULT = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );
      } 

      //:END

      //:// Delete work PostActivateQual entries.
      //:FOR EACH zqFrame.PostActivateQual 
      RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQual", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY zqFrame.PostActivateQual NONE
         RESULT = DeleteEntity( zqFrame, "PostActivateQual", zREPOS_NONE );
         RESULT = SetCursorNextEntity( zqFrame, "PostActivateQual", "" );
      } 

      //:END

      //:// Check for derived attributes along with an OR operator.
      //:/*FOR EACH zqFrame.Component
      //:   IF zqFrame.Component.Value = "("
      //:      nRC = CheckBooleanExpression( zqFrame )
      //:      IF nRC < 0
      //:         // The subexpression contained a derived attribute with an OR, so indicate a derived attribute at top level.
      //:         szDerivedAttributeFlag = "Y"
      //:      ELSE
      //:         szDatabaseAttributeFlag = "Y"
      //:      END
      //:   ELSE
      //:      IF zqFrame.Component.Value != "AND"
      //:         IF zqFrame.Component.Value = "OR"
      //:            szOR_OperatorFlag = "Y"
      //:         ELSE
      //:            SET CURSOR FIRST zqFrame.Condition 
      //:                       WHERE zqFrame.Condition.BooleanConditionName = zqFrame.Component.Value  
      //:            SET CURSOR FIRST zqFrame.GeneralParameter 
      //:                       WHERE zqFrame.GeneralParameter.EntityName    = zqFrame.Condition.EntityName 
      //:                         AND zqFrame.GeneralParameter.AttributeName = zqFrame.Condition.AttributeName 
      //:                         AND zqFrame.GeneralParameter.SearchType    = zqFrame.Condition.SearchType 
      //:                         AND zqFrame.GeneralParameter.Value         = zqFrame.Condition.Value 
      //:            IF zqFrame.GeneralParameter.DerivedAttributeFlag = "Y" OR 
      //:               ( zqFrame.GeneralParameter.SubgroupSearchType != "" AND zqFrame.GeneralParameter.SubgroupSearchType != "ANY" )
      //:            
      //:               szDerivedAttributeFlag = "Y"
      //:            ELSE
      //:               szDatabaseAttributeFlag = "Y"
      //:            END
      //:         END
      //:      END
      //:   END
      //:END*/
      //:nRC = CheckBooleanExpression( zqFrame )
      nRC = ozqFrame_CheckBooleanExpression( zqFrame );
      //:IF nRC = -3
      if ( nRC == -3 )
      { 

         //:Msg = "INFO: There are no persistent attributes at the root level, which means there will be no SQL qualification, " +
         //:      "but only post-activate qualification."// + NEW_LINE +
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringCopy( sb_Msg, 1, 0, "INFO: There are no persistent attributes at the root level, which means there will be no SQL qualification, ", 1, 0, 201 );
         Msg = sb_Msg.toString( );}
          {StringBuilder sb_Msg;
         if ( Msg == null )
            sb_Msg = new StringBuilder( 32 );
         else
            sb_Msg = new StringBuilder( Msg );
                  ZeidonStringConcat( sb_Msg, 1, 0, "but only post-activate qualification.", 1, 0, 201 );
         Msg = sb_Msg.toString( );}
         //:      //"Do you want to continue anyway?"
         //:MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //://nRC = MessagePrompt( zqFrame, "", "Run Query", Msg, 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
         //://IF nRC = zRESPONSE_NO
         //://   RETURN -1
         //://END
         //:ELSE
      } 
      else
      { 
         //:IF nRC = -2
         if ( nRC == -2 )
         { 

            //:Msg = "There are derived attributes at the root level with an OR condition, which means there will be no SQL qualification, " +
            //:      "but only post-activate qualification." // + NEW_LINE +
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringCopy( sb_Msg, 1, 0, "There are derived attributes at the root level with an OR condition, which means there will be no SQL qualification, ", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, "but only post-activate qualification.", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
            //:      //"Do you want to continue anyway?"
            //:MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         } 

         //:   //nRC = MessagePrompt( zqFrame, "", "Run Query", Msg, 0, zBUTTONS_YESNO, zRESPONSE_YES, 0 )
         //:   //IF nRC = zRESPONSE_NO
         //:   //   RETURN -1
         //:   //END
         //:END
      } 

      //:END

      //:// Build SQL statement for expressions not containing derived attributes or SubgroupSearchType attributes.
      //:// Also build PostActivateComponent entities for derived attributes and SubgroupSearchType attributes.
      //:FOR EACH zqFrame.Component
      RESULT = SetCursorFirstEntity( zqFrame, "Component", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:IF zqFrame.Component.Value = "("
         if ( CompareAttributeToString( zqFrame, "Component", "Value", "(" ) == 0 )
         { 
            //:nRC = BuildBooleanExpression( zqFrame, vQualObject, qConvertData )
            nRC = ozqFrame_BuildBooleanExpression( zqFrame, vQualObject, qConvertData );
            //:SET CURSOR LAST zqFrame.PostActivateComponent    // Make sure we're at the end of component. 
            RESULT = SetCursorLastEntity( zqFrame, "PostActivateComponent", "" );
            //:ELSE
         } 
         else
         { 
            //:IF zqFrame.Component.Value = "AND" OR
            //:   zqFrame.Component.Value = "OR"
            if ( CompareAttributeToString( zqFrame, "Component", "Value", "AND" ) == 0 || CompareAttributeToString( zqFrame, "Component", "Value", "OR" ) == 0 )
            { 

               //:// Add the operator, but only if there is already an entry in vQualObject that is not an AND or OR.
               //:// If a derived attribute were the first statement in the boolean, then we would generate an
               //:// AND statement at the beginning of the qualification.
               //:IF vQualObject.QualAttrib EXISTS
               lTempInteger_1 = CheckExistenceOfEntity( vQualObject, "QualAttrib" );
               if ( lTempInteger_1 == 0 )
               { 
                  //:IF vQualObject.QualAttrib.Oper != "AND" AND vQualObject.QualAttrib.Oper != "OR"
                  if ( CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "AND" ) != 0 && CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "OR" ) != 0 )
                  { 
                     //:CREATE ENTITY vQualObject.QualAttrib
                     RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
                     //:vQualObject.QualAttrib.Oper = zqFrame.Component.Value
                     SetAttributeFromAttribute( vQualObject, "QualAttrib", "Oper", zqFrame, "Component", "Value" );
                  } 

                  //:END
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// Add the condition.
               //:SET CURSOR FIRST zqFrame.Condition 
               //:           WHERE zqFrame.Condition.BooleanConditionName = zqFrame.Component.Value  
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, zqFrame, "Component", "Value" );
               szTempString_0 = sb_szTempString_0.toString( );}
               RESULT = SetCursorFirstEntityByString( zqFrame, "Condition", "BooleanConditionName", szTempString_0, "" );
               //:SET CURSOR FIRST zqFrame.GeneralParameter 
               //:           WHERE zqFrame.GeneralParameter.EntityName    = zqFrame.Condition.EntityName 
               //:             AND zqFrame.GeneralParameter.AttributeName = zqFrame.Condition.AttributeName 
               //:             AND zqFrame.GeneralParameter.SearchType    = zqFrame.Condition.SearchType 
               //:             AND zqFrame.GeneralParameter.Value         = zqFrame.Condition.Value 
               RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
               if ( RESULT > zCURSOR_UNCHANGED )
               { 
                  while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "Condition", "EntityName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrame, "GeneralParameter", "AttributeName", zqFrame, "Condition", "AttributeName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrame, "GeneralParameter", "SearchType", zqFrame, "Condition", "SearchType" ) != 0 || CompareAttributeToAttribute( zqFrame, "GeneralParameter", "Value", zqFrame, "Condition", "Value" ) != 0 ) )
                  { 
                     RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
                  } 

               } 

               //:IF zqFrame.GeneralParameter.DerivedAttributeFlag = "" AND
               //:   ( zqFrame.GeneralParameter.SubgroupSearchType = "" OR zqFrame.GeneralParameter.SubgroupSearchType = "ANY" )
               if ( CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "" ) == 0 && ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ||
                    CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) == 0 ) )
               { 

                  //:nRC = BuildConditionSubobj( zqFrame, vQualObject, qConvertData)
                  nRC = ozqFrame_BuildConditionSubobj( zqFrame, vQualObject, qConvertData );
                  //:IF nRC < 0
                  if ( nRC < 0 )
                  { 
                     //:RETURN nRC 
                     if(8==8)return( nRC );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 

                  //:IF vQualObject.QualAttrib EXISTS
                  lTempInteger_2 = CheckExistenceOfEntity( vQualObject, "QualAttrib" );
                  if ( lTempInteger_2 == 0 )
                  { 
                     //:IF vQualObject.QualAttrib.Oper = "AND" OR vQualObject.QualAttrib.Oper = "OR"
                     if ( CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "AND" ) == 0 || CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "OR" ) == 0 )
                     { 
                        //:// Add the last AND or OR to PostActivate access and remove from SQL access.
                        //:// We won't add the last AND or OR, if it would be the first in PostActivate.
                        //:IF zqFrame.PostActivateComponent EXISTS
                        lTempInteger_3 = CheckExistenceOfEntity( zqFrame, "PostActivateComponent" );
                        if ( lTempInteger_3 == 0 )
                        { 
                           //:CREATE ENTITY zqFrame.PostActivateComponent 
                           RESULT = CreateEntity( zqFrame, "PostActivateComponent", zPOS_AFTER );
                           //:zqFrame.PostActivateComponent.Value = vQualObject.QualAttrib.Oper
                           SetAttributeFromAttribute( zqFrame, "PostActivateComponent", "Value", vQualObject, "QualAttrib", "Oper" );
                        } 

                        //:END
                        //:DELETE ENTITY vQualObject.QualAttrib
                        RESULT = DeleteEntity( vQualObject, "QualAttrib", zPOS_NEXT );
                     } 

                     //:END
                  } 

                  //:END

                  //:// Create PostActivateComponent (ex., C1, C2)
                  //:CREATE ENTITY zqFrame.PostActivateComponent 
                  RESULT = CreateEntity( zqFrame, "PostActivateComponent", zPOS_AFTER );
                  //:zqFrame.PostActivateComponent.Value = zqFrame.Component.Value
                  SetAttributeFromAttribute( zqFrame, "PostActivateComponent", "Value", zqFrame, "Component", "Value" );
                  //:// Create corresponding PostActivateQual entry.
                  //:SET CURSOR FIRST zqFrame.Condition 
                  //:           WHERE zqFrame.Condition.BooleanConditionName = zqFrame.Component.Value 
                  {StringBuilder sb_szTempString_0;
                  if ( szTempString_0 == null )
                     sb_szTempString_0 = new StringBuilder( 32 );
                  else
                     sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                     GetStringFromAttribute( sb_szTempString_0, zqFrame, "Component", "Value" );
                  szTempString_0 = sb_szTempString_0.toString( );}
                  RESULT = SetCursorFirstEntityByString( zqFrame, "Condition", "BooleanConditionName", szTempString_0, "" );
                  //:SET CURSOR FIRST zqFrame.GeneralParameter 
                  //:           WHERE zqFrame.GeneralParameter.EntityName    = zqFrame.Condition.EntityName 
                  //:             AND zqFrame.GeneralParameter.AttributeName = zqFrame.Condition.AttributeName 
                  //:             AND zqFrame.GeneralParameter.SearchType    = zqFrame.Condition.SearchType 
                  //:             AND zqFrame.GeneralParameter.Value         = zqFrame.Condition.Value 
                  RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "Condition", "EntityName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrame, "GeneralParameter", "AttributeName", zqFrame, "Condition", "AttributeName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrame, "GeneralParameter", "SearchType", zqFrame, "Condition", "SearchType" ) != 0 || CompareAttributeToAttribute( zqFrame, "GeneralParameter", "Value", zqFrame, "Condition", "Value" ) != 0 ) )
                     { 
                        RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
                     } 

                  } 

                  //:BuildPostQualEntry( zqFrame )
                  ozqFrame_BuildPostQualEntry( zqFrame );
                  //:zqFrame.PostActivateQual.BooleanConditionName = zqFrame.Condition.BooleanConditionName
                  SetAttributeFromAttribute( zqFrame, "PostActivateQual", "BooleanConditionName", zqFrame, "Condition", "BooleanConditionName" );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( zqFrame, "Component", "" );
         //:END
      } 

      //:END

      //:ELSE
   } 
   else
   { 
      //:// The root qualification is driven from the GeneralParameter entities. No boolean expressions exist.
      //:FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.SearchType != ""
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SearchType", "" ) != 0 )
         { 
            //:IF ( zqFrame.GeneralParameter.RootQualificationFlag = "Y" AND
            //:  zqFrame.GeneralParameter.DerivedAttributeFlag  = "" AND
            //:  zqFrame.GeneralParameter.AttributeName        != "GeographicPositionZipCode" AND
            //:  zqFrame.GeneralParameter.AttributeName        != "GeographicPositionMiles" AND
            //:  zqFrame.GeneralParameter.SubgroupSearchType    = "" ) OR
            //:( zqFrame.GeneralParameter.SubgroupSearchType   = "ANY" AND
            //:  zqFrame.GeneralParameter.ScopingEntityName    = zqFrame.zqFrame.QueryObjectRootEntityName AND
            //:  zqFrame.GeneralParameter.DerivedAttributeFlag = "" AND
            //:  zqFrame.GeneralParameter.AttributeName       != "GeographicPositionZipCode" AND
            //:  zqFrame.GeneralParameter.AttributeName       != "GeographicPositionMiles"  )
            if ( ( CompareAttributeToString( zqFrame, "GeneralParameter", "RootQualificationFlag", "Y" ) == 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "" ) == 0 &&
                 CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionZipCode" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionMiles" ) != 0 &&
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ) || ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) == 0 &&
                 CompareAttributeToAttribute( zqFrame, "GeneralParameter", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "" ) == 0 &&
                 CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionZipCode" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionMiles" ) != 0 ) )
            { 

               //://SET CURSOR FIRST vQualObject.EntitySpec
               //://SET CURSOR LAST vQualObject.QualAttrib
               //:nRC = BuildConditionSubobj( zqFrame, vQualObject, qConvertData)
               nRC = ozqFrame_BuildConditionSubobj( zqFrame, vQualObject, qConvertData );
               //:IF nRC < 0
               if ( nRC < 0 )
               { 
                  //:RETURN nRC 
                  if(8==8)return( nRC );
               } 

               //:END

               //:// Add the operator.
               //:CREATE ENTITY vQualObject.QualAttrib
               RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               //:vQualObject.QualAttrib.Oper = zqFrame.zqFrame.FirstLevelOperator
               SetAttributeFromAttribute( vQualObject, "QualAttrib", "Oper", zqFrame, "zqFrame", "FirstLevelOperator" );
            } 

         } 

         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         //:END 
      } 

      //:END
   } 

   //:END

   //:// If a Seasonal Address Date has been specified, add it back to zqFrame.
   //:IF szSeasonalAddressParameterValue != ""
   if ( ZeidonStringCompare( szSeasonalAddressParameterValue, 1, 0, "", 1, 0, 11 ) != 0 )
   { 
      //:SET CURSOR FIRST zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.AttributeName = "wSeasonalAddressDate"
      RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "AttributeName", "wSeasonalAddressDate", "" );
      //:zqFrame.GeneralParameter.Value = szSeasonalAddressParameterValue
      SetAttributeFromString( zqFrame, "GeneralParameter", "Value", szSeasonalAddressParameterValue );
   } 

   //:END

   //:// Activate Optimization
   //:// If optimization has been requested, go to identify entities that do not have to be activated below and then 
   //:// create GeneralParameter entries to restrict their activation.
   //:IF zqFrame.zqFrame.OptimizeQueryFlag = "Y"
   if ( CompareAttributeToString( zqFrame, "zqFrame", "OptimizeQueryFlag", "Y" ) == 0 )
   { 
      //:CreateViewFromView( zqFrameT, zqFrame )
      CreateViewFromView( zqFrameT, zqFrame );
      //:SetViewToSubobject( zqFrameT, "ChildEntity" )
      SetViewToSubobject( zqFrameT, "ChildEntity" );
      //:SetEntityActivateFlagR( zqFrameT, zqFrame )
      ozqFrame_SetEntityActivateFlagR( zqFrameT, zqFrame );
      //:BuildAutoRestrictR( zqFrameT, zqFrame )
      ozqFrame_BuildAutoRestrictR( zqFrameT, zqFrame );
      //:ResetViewFromSubobject( zqFrameT )
      ResetViewFromSubobject( zqFrameT );
      //:DropView( zqFrameT )
      DropView( zqFrameT );
   } 

   //:END

   //:// Build Subqualification (Restricting) logic.
   //:FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.SearchType != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SearchType", "" ) != 0 )
      { 
         //:IF zqFrame.GeneralParameter.RootQualificationFlag != "Y" AND
         //:zqFrame.GeneralParameter.DerivedAttributeFlag = "" AND
         //:zqFrame.GeneralParameter.AttributeName != "GeographicPositionZipCode" AND
         //:zqFrame.GeneralParameter.AttributeName != "GeographicPositionMiles" AND
         //:zqFrame.GeneralParameter.ScopingEntityName != zqFrame.zqFrame.QueryObjectRootEntityName AND
         //:( zqFrame.GeneralParameter.SubgroupSearchType = "ANY" OR
         //:  zqFrame.GeneralParameter.SubgroupSearchType = "" )
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "RootQualificationFlag", "Y" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "" ) == 0 &&
              CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionZipCode" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionMiles" ) != 0 &&
              CompareAttributeToAttribute( zqFrame, "GeneralParameter", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) != 0 && ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) == 0 ||
              CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ) )
         { 

            //:IF zqFrame.GeneralParameter.ScopingEntityName = ""
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "ScopingEntityName", "" ) == 0 )
            { 
               //:szQualEntityName = zqFrame.GeneralParameter.EntityName
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szQualEntityName;
               if ( szQualEntityName == null )
                  sb_szQualEntityName = new StringBuilder( 32 );
               else
                  sb_szQualEntityName = new StringBuilder( szQualEntityName );
                               GetVariableFromAttribute( sb_szQualEntityName, mi_lTempInteger_4, 'S', 33, zqFrame, "GeneralParameter", "EntityName", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szQualEntityName = sb_szQualEntityName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szQualEntityName = zqFrame.GeneralParameter.ScopingEntityName
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szQualEntityName;
               if ( szQualEntityName == null )
                  sb_szQualEntityName = new StringBuilder( 32 );
               else
                  sb_szQualEntityName = new StringBuilder( szQualEntityName );
                               GetVariableFromAttribute( sb_szQualEntityName, mi_lTempInteger_5, 'S', 33, zqFrame, "GeneralParameter", "ScopingEntityName", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szQualEntityName = sb_szQualEntityName.toString( );}
            } 

            //:END
            //:SET CURSOR FIRST vQualObject.EntitySpec
            //:        WHERE vQualObject.EntitySpec.EntityName = szQualEntityName
            RESULT = SetCursorFirstEntityByString( vQualObject, "EntitySpec", "EntityName", szQualEntityName, "" );
            //:IF RESULT < zCURSOR_SET
            if ( RESULT < zCURSOR_SET )
            { 
               //:SET CURSOR LAST vQualObject.EntitySpec
               RESULT = SetCursorLastEntity( vQualObject, "EntitySpec", "" );
               //:CREATE ENTITY vQualObject.EntitySpec
               RESULT = CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
               //:vQualObject.EntitySpec.EntityName = szQualEntityName
               SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", szQualEntityName );
            } 

            //:END
            //:SET CURSOR LAST vQualObject.QualAttrib
            RESULT = SetCursorLastEntity( vQualObject, "QualAttrib", "" );

            //:nRC = BuildConditionSubobj( zqFrame, vQualObject, qConvertData)
            nRC = ozqFrame_BuildConditionSubobj( zqFrame, vQualObject, qConvertData );
            //:IF nRC < 0
            if ( nRC < 0 )
            { 
               //:RETURN nRC 
               if(8==8)return( nRC );
            } 

            //:END

            //:// Add the operator.
            //:CREATE ENTITY vQualObject.QualAttrib
            RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
            //:vQualObject.QualAttrib.Oper = zqFrame.zqFrame.FirstLevelOperator
            SetAttributeFromAttribute( vQualObject, "QualAttrib", "Oper", zqFrame, "zqFrame", "FirstLevelOperator" );
         } 

      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END 
   } 

   //:END

   //:// Build PostActivate processing for those queries without a Boolean Expression.
   //:// When a Boolean Expression is used, the PostActivate entries are built in BuildPostBooleanExp.
   //:IF zqFrame.Component DOES NOT EXIST
   lTempInteger_6 = CheckExistenceOfEntity( zqFrame, "Component" );
   if ( lTempInteger_6 != 0 )
   { 
      //:FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.SearchType != ""
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SearchType", "" ) != 0 )
         { 
            //:IF ( zqFrame.GeneralParameter.SubgroupSearchType != "" AND 
            //:  zqFrame.GeneralParameter.SubgroupSearchType != "ANY" ) OR
            //:  zqFrame.GeneralParameter.DerivedAttributeFlag = "Y"
            if ( ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) != 0 ) ||
                 CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "Y" ) == 0 )
            { 

               //:BuildPostQualEntry( zqFrame )
               ozqFrame_BuildPostQualEntry( zqFrame );
               //:ELSE
            } 
            else
            { 
               //:IF zqFrame.GeneralParameter.AttributeName = "GeographicPositionZipCode"
               if ( CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionZipCode" ) == 0 )
               { 
                  //:BuildPostGeoPosEntry( zqFrame )
                  ozqFrame_BuildPostGeoPosEntry( zqFrame );
               } 

               //:END
            } 

         } 

         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         //:END
      } 

      //:END
   } 

   //:END

   //:// Delete the last AND/OR QualAttrib entity for each EntitySpec subobject.
   //:FOR EACH vQualObject.EntitySpec
   RESULT = SetCursorFirstEntity( vQualObject, "EntitySpec", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR LAST vQualObject.QualAttrib
      RESULT = SetCursorLastEntity( vQualObject, "QualAttrib", "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:IF vQualObject.QualAttrib.Oper = "AND" OR
         //:   vQualObject.QualAttrib.Oper = "OR"
         if ( CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "AND" ) == 0 || CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "OR" ) == 0 )
         { 

            //: DELETE ENTITY vQualObject.QualAttrib
            RESULT = DeleteEntity( vQualObject, "QualAttrib", zPOS_NEXT );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( vQualObject, "EntitySpec", "" );
      //:END
   } 

   //:END

   //:// If the vQualObject.EntitySpec for the root does not have any QualAttrib entries, delete the
   //:// EntitySpec entity.
   //:SET CURSOR FIRST vQualObject.EntitySpec
   RESULT = SetCursorFirstEntity( vQualObject, "EntitySpec", "" );
   //:IF vQualObject.QualAttrib DOES NOT EXIST
   lTempInteger_7 = CheckExistenceOfEntity( vQualObject, "QualAttrib" );
   if ( lTempInteger_7 != 0 )
   { 
      //:DELETE ENTITY vQualObject.EntitySpec
      RESULT = DeleteEntity( vQualObject, "EntitySpec", zPOS_NEXT );
   } 

   //:END
   return( 0 );
// END
} 


public int 
ozqFrame_fnLocalBuildQual_1( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "OriginalDialogText" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "OriginalDialogText" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "IsAdvancedQuery" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


public int 
ozqFrame_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "OriginalDialogText" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "OriginalDialogText" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "IsAdvancedQuery" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "Y" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
//:InitQueryUpdate( VIEW zqFrame BASED ON LOD zqFrame,
//:                 VIEW QueryView )

//:   VIEW zqFrameRecurs BASED ON LOD zqFrame
public int 
ozqFrame_InitQueryUpdate( View     zqFrame,
                          View     QueryView )
{
   zVIEW    zqFrameRecurs = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )  szAttributeName
   String   szAttributeName = null;
   //:STRING ( 32 )  szParentEntityName
   String   szParentEntityName = null;
   //:STRING ( 50 )  szEntityNamePrefix
   String   szEntityNamePrefix = null;
   //:INTEGER        IndentLevel
   int      IndentLevel = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_1 = null;


   //:// Since we have removed the AND/OR selection for root entity qualification, initialize
   //:// the FirstLevelOperator to AND.
   //:zqFrame.zqFrame.FirstLevelOperator = "AND"
   SetAttributeFromString( zqFrame, "zqFrame", "FirstLevelOperator", "AND" );

   //:// Make sure that Condition entities are built.
   //:BuildConditionValues( zqFrame )
   ozqFrame_BuildConditionValues( zqFrame );

   //:// Build the recursive structure used by "Scoping Entity" processing.
   //:FormatDisplay( zqFrame, QueryView )
   ozqFrame_FormatDisplay( zqFrame, QueryView );

   //:// Build the Display Tree Values fresh.
   //:FOR EACH zqFrame.DisplayTreeEntity 
   RESULT = SetCursorFirstEntity( zqFrame, "DisplayTreeEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.DisplayTreeEntity NONE 
      RESULT = DeleteEntity( zqFrame, "DisplayTreeEntity", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "DisplayTreeEntity", "" );
   } 

   //:END
   //:FOR EACH QueryView.LOD_Entity
   RESULT = SetCursorFirstEntity( QueryView, "LOD_Entity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY zqFrame.DisplayTreeEntity 
      RESULT = CreateEntity( zqFrame, "DisplayTreeEntity", zPOS_AFTER );
      //:szEntityName = QueryView.LOD_Entity.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, QueryView, "LOD_Entity", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szEntityName = sb_szEntityName.toString( );}
      //:IndentLevel =  QueryView.LOD_Entity.IndentLvl
      {MutableInt mi_IndentLevel = new MutableInt( IndentLevel );
             GetIntegerFromAttribute( mi_IndentLevel, QueryView, "LOD_Entity", "IndentLvl" );
      IndentLevel = mi_IndentLevel.intValue( );}
      //:IF IndentLevel = 1
      if ( IndentLevel == 1 )
      { 
         //:// The Entity Name is not indented at all.
         //:zqFrame.DisplayTreeEntity.Name       = szEntityName
         SetAttributeFromString( zqFrame, "DisplayTreeEntity", "Name", szEntityName );
         //:zqFrame.DisplayTreeEntity.EntityName = szEntityName
         SetAttributeFromString( zqFrame, "DisplayTreeEntity", "EntityName", szEntityName );
         //:ELSE
      } 
      else
      { 
         //:// The Entity Name is indented based on the Indent Level.
         //:szEntityNamePrefix = ""
          {StringBuilder sb_szEntityNamePrefix;
         if ( szEntityNamePrefix == null )
            sb_szEntityNamePrefix = new StringBuilder( 32 );
         else
            sb_szEntityNamePrefix = new StringBuilder( szEntityNamePrefix );
                  ZeidonStringCopy( sb_szEntityNamePrefix, 1, 0, "", 1, 0, 51 );
         szEntityNamePrefix = sb_szEntityNamePrefix.toString( );}
         //:LOOP WHILE IndentLevel > 0
         while ( IndentLevel > 0 )
         { 
            //:szEntityNamePrefix = szEntityNamePrefix + "....."
             {StringBuilder sb_szEntityNamePrefix;
            if ( szEntityNamePrefix == null )
               sb_szEntityNamePrefix = new StringBuilder( 32 );
            else
               sb_szEntityNamePrefix = new StringBuilder( szEntityNamePrefix );
                        ZeidonStringConcat( sb_szEntityNamePrefix, 1, 0, ".....", 1, 0, 51 );
            szEntityNamePrefix = sb_szEntityNamePrefix.toString( );}
            //:IndentLevel = IndentLevel - 3
            IndentLevel = IndentLevel - 3;
         } 

         //:END
         //:zqFrame.DisplayTreeEntity.Name       = szEntityNamePrefix + szEntityName
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringCopy( sb_szTempString_0, 1, 0, szEntityNamePrefix, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
          {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                  ZeidonStringConcat( sb_szTempString_0, 1, 0, szEntityName, 1, 0, 255 );
         szTempString_0 = sb_szTempString_0.toString( );}
         SetAttributeFromString( zqFrame, "DisplayTreeEntity", "Name", szTempString_0 );
         //:zqFrame.DisplayTreeEntity.EntityName = szEntityName
         SetAttributeFromString( zqFrame, "DisplayTreeEntity", "EntityName", szEntityName );
      } 

      //:END
      //:FOR EACH QueryView.LOD_Attribute
      RESULT = SetCursorFirstEntity( QueryView, "LOD_Attribute", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY zqFrame.DisplayTreeAttribute 
         RESULT = CreateEntity( zqFrame, "DisplayTreeAttribute", zPOS_AFTER );
         //:zqFrame.DisplayTreeAttribute.Name = QueryView.ER_Attribute.Name
         SetAttributeFromAttribute( zqFrame, "DisplayTreeAttribute", "Name", QueryView, "ER_Attribute", "Name" );
         RESULT = SetCursorNextEntity( QueryView, "LOD_Attribute", "" );
      } 

      RESULT = SetCursorNextEntity( QueryView, "LOD_Entity", "" );
      //:END
   } 

   //:END

   //:// Set ParentEntity and cardinality for each DisplayTreeEntity entity.
   //:CreateViewFromView( zqFrameRecurs, zqFrame )
   CreateViewFromView( zqFrameRecurs, zqFrame );
   //:NAME VIEW zqFrameRecurs "zqFrameRecurs"
   SetNameForView( zqFrameRecurs, "zqFrameRecurs", null, zLEVEL_TASK );
   //:szParentEntityName = ""
    {StringBuilder sb_szParentEntityName;
   if ( szParentEntityName == null )
      sb_szParentEntityName = new StringBuilder( 32 );
   else
      sb_szParentEntityName = new StringBuilder( szParentEntityName );
      ZeidonStringCopy( sb_szParentEntityName, 1, 0, "", 1, 0, 33 );
   szParentEntityName = sb_szParentEntityName.toString( );}
   //:SetTreeParentRecurs( zqFrame, zqFrameRecurs, szParentEntityName )
   ozqFrame_SetTreeParentRecurs( zqFrame, zqFrameRecurs, szParentEntityName );
   //:DropView( zqFrameRecurs )
   DropView( zqFrameRecurs );

   //:SET CURSOR FIRST zqFrame.DisplayTreeEntity  
   RESULT = SetCursorFirstEntity( zqFrame, "DisplayTreeEntity", "" );

   //:// Build the Graphics selection entries.
   //:InitializeQueryGraphMapping( zqFrame )
   {
    zGLOBALQ_Operation m_zGLOBALQ_Operation = new zGLOBALQ_Operation( zqFrame );
    m_zGLOBALQ_Operation.InitializeQueryGraphMapping( zqFrame );
    // m_zGLOBALQ_Operation = null;  // permit gc  (unnecessary)
   }

   //:// Make sure that ScopingSourceQueryEntity entries are built afresh.
   //:FOR zqFrame.ScopingSourceQueryEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ScopingSourceQueryEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.ScopingSourceQueryEntity NONE 
      RESULT = DeleteEntity( zqFrame, "ScopingSourceQueryEntity", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "ScopingSourceQueryEntity", "" );
   } 

   //:END
   //:FOR EACH QueryView.LOD_Entity
   RESULT = SetCursorFirstEntity( QueryView, "LOD_Entity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY zqFrame.ScopingSourceQueryEntity 
      RESULT = CreateEntity( zqFrame, "ScopingSourceQueryEntity", zPOS_AFTER );
      //:zqFrame.ScopingSourceQueryEntity.EntityName = QueryView.LOD_Entity.Name
      SetAttributeFromAttribute( zqFrame, "ScopingSourceQueryEntity", "EntityName", QueryView, "LOD_Entity", "Name" );
      RESULT = SetCursorNextEntity( QueryView, "LOD_Entity", "" );
   } 

   //:END

   //:// Also include appropriate ScopingQueryEntity entities based on GeneralParameter.ScopingEntityName.
   //:FOR EACH zqFrame.GeneralParameter 
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF zqFrame.GeneralParameter.ScopingEntityName != ""
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "ScopingEntityName", "" ) != 0 )
      { 
         //:IF zqFrame.ScopingQueryEntity DOES NOT EXIST
         lTempInteger_1 = CheckExistenceOfEntity( zqFrame, "ScopingQueryEntity" );
         if ( lTempInteger_1 != 0 )
         { 
            //:SET CURSOR FIRST zqFrame.ScopingSourceQueryEntity 
            //:           WHERE zqFrame.ScopingSourceQueryEntity.EntityName = zqFrame.GeneralParameter.ScopingEntityName 
            {StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetStringFromAttribute( sb_szTempString_1, zqFrame, "GeneralParameter", "ScopingEntityName" );
            szTempString_1 = sb_szTempString_1.toString( );}
            RESULT = SetCursorFirstEntityByString( zqFrame, "ScopingSourceQueryEntity", "EntityName", szTempString_1, "" );
            //:INCLUDE zqFrame.ScopingQueryEntity FROM zqFrame.ScopingSourceQueryEntity 
            RESULT = IncludeSubobjectFromSubobject( zqFrame, "ScopingQueryEntity", zqFrame, "ScopingSourceQueryEntity", zPOS_AFTER );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   //:SET CURSOR FIRST zqFrame.ScopingSourceQueryEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ScopingSourceQueryEntity", "" );
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDisplayBooleanLong( VIEW zqFrame BASED ON LOD zqFrame,
//:                          STRING ( 32 ) InternalEntityStructure,
//:                          STRING ( 32 ) InternalAttribStructure,
//:                          SHORT GetOrSetFlag )

//:   STRING ( 10000 ) szCondition
public int 
ozqFrame_dDisplayBooleanLong( View     zqFrame,
                              String InternalEntityStructure,
                              String InternalAttribStructure,
                              Integer   GetOrSetFlag )
{
   String   szCondition = null;
   //:STRING ( 10000 ) szCriteria
   String   szCriteria = null;
   //:INTEGER Count
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

          //:END
         //:StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, szCondition )
         StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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


//:LOCAL OPERATION
//:BuildAutoRestrictR( VIEW zqFrame     BASED ON LOD zqFrame,
//:                    VIEW zqFrameRoot BASED ON LOD zqFrame )

//:   VIEW QueryView
public int 
ozqFrame_BuildAutoRestrictR( View     zqFrame,
                             View     zqFrameRoot )
{
   zVIEW    QueryView = new zVIEW( );
   //:STRING ( 1 ) szNoRestrictFlag
   String   szNoRestrictFlag = null;
   //:STRING ( 1 ) szDisplayFlag
   String   szDisplayFlag = null;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:GET VIEW QueryView NAMED "QueryView"
   RESULT = GetViewByName( QueryView, "QueryView", zqFrame, zLEVEL_TASK );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:IssueError( zqFrame,0,0, "No LOD" )
      IssueError( zqFrame, 0, 0, "No LOD" );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Create a RESTRICTING entry of ID for each 0-to-m relationship with 
   //:// 1. No attribute being displayed,
   //:// 2. No NoActivateSubobjectFlag being specified.
   //:// 3. An ID attribute for the Entity in the LOD.
   //:FOR EACH zqFrame.ParentEntity 
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:// Check each Attribute under ParentEntity to see if any of them are to be displayed. If so, don't
      //:// create the RESTRICTING entry.
      //:szDisplayFlag = ""
       {StringBuilder sb_szDisplayFlag;
      if ( szDisplayFlag == null )
         sb_szDisplayFlag = new StringBuilder( 32 );
      else
         sb_szDisplayFlag = new StringBuilder( szDisplayFlag );
            ZeidonStringCopy( sb_szDisplayFlag, 1, 0, "", 1, 0, 2 );
      szDisplayFlag = sb_szDisplayFlag.toString( );}
      //:FOR EACH zqFrame.QueryAttribute 
      RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SET CURSOR FIRST zqFrameRoot.GeneralParameter
         //:           WHERE zqFrameRoot.GeneralParameter.EntityName      = zqFrame.QueryAttribute.MappingEntityName 
         //:             AND zqFrameRoot.GeneralParameter.AttributeName   = zqFrame.QueryAttribute.AttributeName 
         //:             AND zqFrameRoot.GeneralParameter.ListDisplayFlag = "Y" 
         RESULT = SetCursorFirstEntity( zqFrameRoot, "GeneralParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrameRoot, "GeneralParameter", "EntityName", zqFrame, "QueryAttribute", "MappingEntityName" ) != 0 ||
                    CompareAttributeToAttribute( zqFrameRoot, "GeneralParameter", "AttributeName", zqFrame, "QueryAttribute", "AttributeName" ) != 0 || CompareAttributeToString( zqFrameRoot, "GeneralParameter", "ListDisplayFlag", "Y" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrameRoot, "GeneralParameter", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:szDisplayFlag = "Y"
             {StringBuilder sb_szDisplayFlag;
            if ( szDisplayFlag == null )
               sb_szDisplayFlag = new StringBuilder( 32 );
            else
               sb_szDisplayFlag = new StringBuilder( szDisplayFlag );
                        ZeidonStringCopy( sb_szDisplayFlag, 1, 0, "Y", 1, 0, 2 );
            szDisplayFlag = sb_szDisplayFlag.toString( );}
         } 

         RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
         //:END
      } 

      //:END

      //:// Create RESTRICTING entry if there was no Display Attribute for the Entity and the Force entity option wasn't selected.
      //:szNoRestrictFlag = ""
       {StringBuilder sb_szNoRestrictFlag;
      if ( szNoRestrictFlag == null )
         sb_szNoRestrictFlag = new StringBuilder( 32 );
      else
         sb_szNoRestrictFlag = new StringBuilder( szNoRestrictFlag );
            ZeidonStringCopy( sb_szNoRestrictFlag, 1, 0, "", 1, 0, 2 );
      szNoRestrictFlag = sb_szNoRestrictFlag.toString( );}
      //:IF zqFrame.ParentEntity.NoActivateSubobjectFlag = "Y" AND szDisplayFlag = ""
      if ( CompareAttributeToString( zqFrame, "ParentEntity", "NoActivateSubobjectFlag", "Y" ) == 0 && ZeidonStringCompare( szDisplayFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:SET CURSOR FIRST zqFrameRoot.GeneralParameter 
         //:           WHERE zqFrameRoot.GeneralParameter.EntityName    = zqFrame.ParentEntity.EntityName 
         //:             AND zqFrameRoot.GeneralParameter.AttributeName = "ID"
         //:             AND zqFrameRoot.GeneralParameter.SearchType    = "="
         //:             AND zqFrameRoot.GeneralParameter.Value         = "0"
         RESULT = SetCursorFirstEntity( zqFrameRoot, "GeneralParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrameRoot, "GeneralParameter", "EntityName", zqFrame, "ParentEntity", "EntityName" ) != 0 ||
                    CompareAttributeToString( zqFrameRoot, "GeneralParameter", "AttributeName", "ID" ) != 0 || CompareAttributeToString( zqFrameRoot, "GeneralParameter", "SearchType", "=" ) != 0 ||
                    CompareAttributeToString( zqFrameRoot, "GeneralParameter", "Value", "0" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrameRoot, "GeneralParameter", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:SET CURSOR FIRST QueryView.LOD_Entity
            //:           WHERE QueryView.LOD_Entity.Name = zqFrame.ParentEntity.EntityName 
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame, "ParentEntity", "EntityName" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = SetCursorFirstEntityByString( QueryView, "LOD_Entity", "Name", szTempString_0, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:IF QueryView.LOD_Entity.Derived = "Y" OR QueryView.LOD_Entity.Work = "Y"
               if ( CompareAttributeToString( QueryView, "LOD_Entity", "Derived", "Y" ) == 0 || CompareAttributeToString( QueryView, "LOD_Entity", "Work", "Y" ) == 0 )
               { 
                  //:// Since this is a work or derived entity, don't continue checking the subobject.
                  //:szNoRestrictFlag = "Y"
                   {StringBuilder sb_szNoRestrictFlag;
                  if ( szNoRestrictFlag == null )
                     sb_szNoRestrictFlag = new StringBuilder( 32 );
                  else
                     sb_szNoRestrictFlag = new StringBuilder( szNoRestrictFlag );
                                    ZeidonStringCopy( sb_szNoRestrictFlag, 1, 0, "Y", 1, 0, 2 );
                  szNoRestrictFlag = sb_szNoRestrictFlag.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST QueryView.ER_Attribute WITHIN QueryView.LOD_Entity
                  //:           WHERE QueryView.ER_Attribute.Name = "ID"
                  RESULT = SetCursorFirstEntityByString( QueryView, "ER_Attribute", "Name", "ID", "LOD_Entity" );
                  //:IF RESULT >= zCURSOR_SET
                  if ( RESULT >= zCURSOR_SET )
                  { 
                     //:// Restrict the entity and don't continue checking the subobject.
                     //:SET CURSOR FIRST zqFrameRoot.GeneralParameter 
                     //:           WHERE zqFrameRoot.GeneralParameter.EntityName = zqFrame.ParentEntity.EntityName 
                     {StringBuilder sb_szTempString_0;
                     if ( szTempString_0 == null )
                        sb_szTempString_0 = new StringBuilder( 32 );
                     else
                        sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                           GetStringFromAttribute( sb_szTempString_0, zqFrame, "ParentEntity", "EntityName" );
                     szTempString_0 = sb_szTempString_0.toString( );}
                     RESULT = SetCursorFirstEntityByString( zqFrameRoot, "GeneralParameter", "EntityName", szTempString_0, "" );
                     //:CREATE ENTITY zqFrameRoot.GeneralParameter BEFORE
                     RESULT = CreateEntity( zqFrameRoot, "GeneralParameter", zPOS_BEFORE );
                     //:zqFrameRoot.GeneralParameter.EntityName    = zqFrame.ParentEntity.EntityName 
                     SetAttributeFromAttribute( zqFrameRoot, "GeneralParameter", "EntityName", zqFrame, "ParentEntity", "EntityName" );
                     //:zqFrameRoot.GeneralParameter.AttributeName = "ID"
                     SetAttributeFromString( zqFrameRoot, "GeneralParameter", "AttributeName", "ID" );
                     //:zqFrameRoot.GeneralParameter.SearchType    = "="
                     SetAttributeFromString( zqFrameRoot, "GeneralParameter", "SearchType", "=" );
                     //:zqFrameRoot.GeneralParameter.Value         = "0"
                     SetAttributeFromString( zqFrameRoot, "GeneralParameter", "Value", "0" );
                     //:szNoRestrictFlag = "Y"
                      {StringBuilder sb_szNoRestrictFlag;
                     if ( szNoRestrictFlag == null )
                        sb_szNoRestrictFlag = new StringBuilder( 32 );
                     else
                        sb_szNoRestrictFlag = new StringBuilder( szNoRestrictFlag );
                                          ZeidonStringCopy( sb_szNoRestrictFlag, 1, 0, "Y", 1, 0, 2 );
                     szNoRestrictFlag = sb_szNoRestrictFlag.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:IF szNoRestrictFlag = ""
      if ( ZeidonStringCompare( szNoRestrictFlag, 1, 0, "", 1, 0, 2 ) == 0 )
      { 
         //:SetViewToSubobject( zqFrame, "ChildEntity" )
         SetViewToSubobject( zqFrame, "ChildEntity" );
         //:BuildAutoRestrictR( zqFrame, zqFrameRoot )
         ozqFrame_BuildAutoRestrictR( zqFrame, zqFrameRoot );
         //:ResetViewFromSubobject( zqFrame )
         ResetViewFromSubobject( zqFrame );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ParseBooleanVML( VIEW zqFrame BASED ON LOD zqFrame )

//:   STRING ( 1000 ) szBooleanExpression
public int 
ozqFrame_ParseBooleanVML( View     zqFrame )
{
   String   szBooleanExpression = null;
   //:STRING ( 1000 ) szStringValue
   String   szStringValue = null;
   //:STRING ( 1 )    szCurrentCharacter
   String   szCurrentCharacter = null;
   //:INTEGER         Index
   int      Index = 0;
   //:INTEGER         ExpressionLength
   int      ExpressionLength = 0;
   //:INTEGER         ValueLength
   int      ValueLength = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// Parse the Boolean Expression and create each component value as an entity Component.

   //:szBooleanExpression = zqFrame.BooleanExpression.TextValue 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szBooleanExpression;
   if ( szBooleanExpression == null )
      sb_szBooleanExpression = new StringBuilder( 32 );
   else
      sb_szBooleanExpression = new StringBuilder( szBooleanExpression );
       GetVariableFromAttribute( sb_szBooleanExpression, mi_lTempInteger_0, 'S', 1001, zqFrame, "BooleanExpression", "TextValue", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szBooleanExpression = sb_szBooleanExpression.toString( );}
   //:ExpressionLength = zstrlen( szBooleanExpression )
   ExpressionLength = zstrlen( szBooleanExpression );

   //:// Loop through expression creating Components entries for each "(" or ")" or nonblank character string.
   //:Index = 1
   Index = 1;
   //:LOOP WHILE Index <= ExpressionLength
   while ( Index <= ExpressionLength )
   { 
      //:szCurrentCharacter = szBooleanExpression[Index:1]
       {StringBuilder sb_szCurrentCharacter;
      if ( szCurrentCharacter == null )
         sb_szCurrentCharacter = new StringBuilder( 32 );
      else
         sb_szCurrentCharacter = new StringBuilder( szCurrentCharacter );
            ZeidonStringCopy( sb_szCurrentCharacter, 1, 0, szBooleanExpression, Index, 1, 2 );
      szCurrentCharacter = sb_szCurrentCharacter.toString( );}
      //:IF szCurrentCharacter != " "
      if ( ZeidonStringCompare( szCurrentCharacter, 1, 0, " ", 1, 0, 2 ) != 0 )
      { 
         //:IF szCurrentCharacter = "(" OR 
         //:   szCurrentCharacter = ")"
         if ( ZeidonStringCompare( szCurrentCharacter, 1, 0, "(", 1, 0, 2 ) == 0 || ZeidonStringCompare( szCurrentCharacter, 1, 0, ")", 1, 0, 2 ) == 0 )
         { 

            //:// Value is paren, which doesn't require a preceding or following blank.
            //:CREATE ENTITY zqFrame.Component 
            RESULT = CreateEntity( zqFrame, "Component", zPOS_AFTER );
            //:zqFrame.Component.Value = szCurrentCharacter 
            SetAttributeFromString( zqFrame, "Component", "Value", szCurrentCharacter );
            //:Index = Index + 1
            Index = Index + 1;
            //:ELSE
         } 
         else
         { 
            //:// Value is any nonblank string.
            //:szStringValue = szCurrentCharacter
             {StringBuilder sb_szStringValue;
            if ( szStringValue == null )
               sb_szStringValue = new StringBuilder( 32 );
            else
               sb_szStringValue = new StringBuilder( szStringValue );
                        ZeidonStringCopy( sb_szStringValue, 1, 0, szCurrentCharacter, 1, 0, 1001 );
            szStringValue = sb_szStringValue.toString( );}
            //:Index = Index + 1
            Index = Index + 1;
            //:szCurrentCharacter = szBooleanExpression[Index:1]
             {StringBuilder sb_szCurrentCharacter;
            if ( szCurrentCharacter == null )
               sb_szCurrentCharacter = new StringBuilder( 32 );
            else
               sb_szCurrentCharacter = new StringBuilder( szCurrentCharacter );
                        ZeidonStringCopy( sb_szCurrentCharacter, 1, 0, szBooleanExpression, Index, 1, 2 );
            szCurrentCharacter = sb_szCurrentCharacter.toString( );}
            //:LOOP WHILE Index <= ExpressionLength AND szCurrentCharacter != " " AND szCurrentCharacter != ")"
            while ( Index <= ExpressionLength && ZeidonStringCompare( szCurrentCharacter, 1, 0, " ", 1, 0, 2 ) != 0 && ZeidonStringCompare( szCurrentCharacter, 1, 0, ")", 1, 0, 2 ) != 0 )
            { 
               //:szStringValue = szStringValue + szCurrentCharacter
                {StringBuilder sb_szStringValue;
               if ( szStringValue == null )
                  sb_szStringValue = new StringBuilder( 32 );
               else
                  sb_szStringValue = new StringBuilder( szStringValue );
                              ZeidonStringConcat( sb_szStringValue, 1, 0, szCurrentCharacter, 1, 0, 1001 );
               szStringValue = sb_szStringValue.toString( );}
               //:Index = Index + 1
               Index = Index + 1;
               //:szCurrentCharacter = szBooleanExpression[Index:1]
                {StringBuilder sb_szCurrentCharacter;
               if ( szCurrentCharacter == null )
                  sb_szCurrentCharacter = new StringBuilder( 32 );
               else
                  sb_szCurrentCharacter = new StringBuilder( szCurrentCharacter );
                              ZeidonStringCopy( sb_szCurrentCharacter, 1, 0, szBooleanExpression, Index, 1, 2 );
               szCurrentCharacter = sb_szCurrentCharacter.toString( );}
            } 

            //:END
            //:CREATE ENTITY zqFrame.Component 
            RESULT = CreateEntity( zqFrame, "Component", zPOS_AFTER );
            //:zqFrame.Component.Value = szStringValue 
            SetAttributeFromString( zqFrame, "Component", "Value", szStringValue );
            //:IF szCurrentCharacter = " "
            if ( ZeidonStringCompare( szCurrentCharacter, 1, 0, " ", 1, 0, 2 ) == 0 )
            { 
               //:Index = Index + 1
               Index = Index + 1;
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Character is blank, so skip to next character.
         //:Index = Index + 1
         Index = Index + 1;
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatCSV_TitleRecurs( VIEW zqFrame     BASED ON LOD zqFrame,
//:                       VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                       STRING ( 10000 ) OutputLine,
//:                       INTEGER         NextColumnNumber,
//:                       STRING ( 1 )    szTypeFlag,
//:                       INTEGER         RecursiveLevel )

//:   VIEW zqFrameT BASED ON LOD zqFrame
public int 
ozqFrame_FormatCSV_TitleRecurs( View     zqFrame,
                                View     zqFrameRoot,
                                StringBuilder   OutputLine,
                                MutableInt   NextColumnNumber,
                                String   szTypeFlag,
                                int      RecursiveLevel )
{
   zVIEW    zqFrameT = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;


   //:// For each attribute to be displayed, add a title to the title line.
   //:// Also determine the column number for display of each attribute.
   //://TraceLineI( "*** Recursive Level: ", RecursiveLevel )
   //:RecursiveLevel = RecursiveLevel + 1
   RecursiveLevel = RecursiveLevel + 1;

   //:FOR EACH zqFrame.ParentEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF szTypeFlag = "F"
      if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
      { 
         //:// Build full CSV file zqFrame.CSV_OutputLineItem entries.
         //:FOR EACH zqFrame.QueryAttribute
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:CREATE ENTITY zqFrameRoot.CSV_OutputLineItem 
            RESULT = CreateEntity( zqFrameRoot, "CSV_OutputLineItem", zPOS_AFTER );
            //:IF zqFrame.QueryAttribute.GroupOperation != ""
            if ( CompareAttributeToString( zqFrame, "QueryAttribute", "GroupOperation", "" ) != 0 )
            { 
               //:// Format operation attribute.
               //:zqFrameRoot.CSV_OutputLineItem.EntityName         = zqFrame.QueryAttribute.GroupOperationEntityName
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityName", zqFrame, "QueryAttribute", "GroupOperationEntityName" );
               //:zqFrameRoot.CSV_OutputLineItem.AttributeName      = zqFrame.QueryAttribute.GroupOperationAttributeName
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "AttributeName", zqFrame, "QueryAttribute", "GroupOperationAttributeName" );
               //:zqFrameRoot.CSV_OutputLineItem.GroupOperation     = zqFrame.QueryAttribute.GroupOperation 
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "GroupOperation", zqFrame, "QueryAttribute", "GroupOperation" );
               //:zqFrameRoot.CSV_OutputLineItem.SortOrder          = zqFrame.QueryAttribute.DisplayOrder
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "SortOrder", zqFrame, "QueryAttribute", "DisplayOrder" );
               //:zqFrameRoot.CSV_OutputLineItem.Value              = zqFrame.QueryAttribute.Prompt
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "Value", zqFrame, "QueryAttribute", "Prompt" );
               //:zqFrameRoot.CSV_OutputLineItem.RecursiveLevel     = RecursiveLevel
               SetAttributeFromInteger( zqFrameRoot, "CSV_OutputLineItem", "RecursiveLevel", RecursiveLevel );
               //:// Get the EntityNameToFormat of the first parent whose cardinality is > 1.
               //:CreateViewFromView( zqFrameT, zqFrame )
               CreateViewFromView( zqFrameT, zqFrame );
               //:nRC = ResetViewFromSubobject( zqFrameT )
               nRC = ResetViewFromSubobject( zqFrameT );
               //:LOOP WHILE nRC >= 0 AND zqFrameT.ParentEntity.MaxCardinality = 1
               while ( nRC >= 0 && CompareAttributeToInteger( zqFrameT, "ParentEntity", "MaxCardinality", 1 ) == 0 )
               { 
                  //:nRC = ResetViewFromSubobject( zqFrameT )
                  nRC = ResetViewFromSubobject( zqFrameT );
               } 

               //:END
               //:zqFrameRoot.CSV_OutputLineItem.EntityNameToFormat = zqFrameT.ParentEntity.EntityName 
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityNameToFormat", zqFrameT, "ParentEntity", "EntityName" );
               //:DropView( zqFrameT )
               DropView( zqFrameT );
               //:ELSE
            } 
            else
            { 
               //:// Format regular attribute.
               //:zqFrameRoot.CSV_OutputLineItem.EntityName     = zqFrame.QueryAttribute.MappingEntityName
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityName", zqFrame, "QueryAttribute", "MappingEntityName" );
               //:zqFrameRoot.CSV_OutputLineItem.AttributeName  = zqFrame.QueryAttribute.AttributeName 
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "AttributeName", zqFrame, "QueryAttribute", "AttributeName" );
               //:zqFrameRoot.CSV_OutputLineItem.SortOrder      = zqFrame.QueryAttribute.DisplayOrder
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "SortOrder", zqFrame, "QueryAttribute", "DisplayOrder" );
               //:zqFrameRoot.CSV_OutputLineItem.Value          = zqFrame.QueryAttribute.Prompt
               SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "Value", zqFrame, "QueryAttribute", "Prompt" );
               //:zqFrameRoot.CSV_OutputLineItem.RecursiveLevel = RecursiveLevel
               SetAttributeFromInteger( zqFrameRoot, "CSV_OutputLineItem", "RecursiveLevel", RecursiveLevel );
               //:IF zqFrame.ParentEntity.MaxCardinality > 1
               if ( CompareAttributeToInteger( zqFrame, "ParentEntity", "MaxCardinality", 1 ) > 0 )
               { 
                  //:zqFrameRoot.CSV_OutputLineItem.EntityNameToFormat = zqFrameRoot.CSV_OutputLineItem.EntityName 
                  SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityNameToFormat", zqFrameRoot, "CSV_OutputLineItem", "EntityName" );
                  //:ELSE
               } 
               else
               { 
                  //:// Get the EntityNameToFormat  of the first parent whose cardinality is > 1.
                  //:CreateViewFromView( zqFrameT, zqFrame )
                  CreateViewFromView( zqFrameT, zqFrame );
                  //:nRC = ResetViewFromSubobject( zqFrameT )
                  nRC = ResetViewFromSubobject( zqFrameT );
                  //:LOOP WHILE nRC >= 0 AND zqFrameT.ParentEntity.MaxCardinality = 1
                  while ( nRC >= 0 && CompareAttributeToInteger( zqFrameT, "ParentEntity", "MaxCardinality", 1 ) == 0 )
                  { 
                     //:nRC = ResetViewFromSubobject( zqFrameT )
                     nRC = ResetViewFromSubobject( zqFrameT );
                  } 

                  //:END
                  //:zqFrameRoot.CSV_OutputLineItem.EntityNameToFormat = zqFrameT.ParentEntity.EntityName 
                  SetAttributeFromAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityNameToFormat", zqFrameT, "ParentEntity", "EntityName" );
                  //:DropView( zqFrameT )
                  DropView( zqFrameT );
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Build sparse CSV file title.
         //:FOR EACH zqFrame.QueryAttribute
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:NextColumnNumber = NextColumnNumber + 1
            NextColumnNumber.increment( );
            //:zqFrame.QueryAttribute.CSV_ColumnNumber = NextColumnNumber
            SetAttributeFromInteger( zqFrame, "QueryAttribute", "CSV_ColumnNumber", NextColumnNumber.intValue( ) );
            //:OutputLine = OutputLine + QUOTES +
            //:             zqFrame.QueryAttribute.Prompt +
            //:             QUOTES + ","
            ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, zqFrame, "QueryAttribute", "Prompt", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
            ZeidonStringConcat( OutputLine, 1, 0, szTempString_0, 1, 0, 10001 );
            ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
            ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
            RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
         } 

         //:END
      } 

      //:END

      //:// Process subobject attributes.
      //:SetViewToSubobject( zqFrame, "ChildEntity" )
      SetViewToSubobject( zqFrame, "ChildEntity" );
      //:FormatCSV_TitleRecurs( zqFrame, zqFrameRoot, OutputLine, NextColumnNumber, szTypeFlag, RecursiveLevel )
      ozqFrame_FormatCSV_TitleRecurs( zqFrame, zqFrameRoot, OutputLine, NextColumnNumber, szTypeFlag, RecursiveLevel );
      //:ResetViewFromSubobject( zqFrame )
      ResetViewFromSubobject( zqFrame );
      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
ozqFrame_ClearQueryInformation( View     zqFrame )
{
   int      RESULT = 0;

   //:ClearQueryInformation( VIEW zqFrame BASED ON LOD zqFrame )

   //:zqFrame.zqFrame.FirstLevelOperator = "AND"
   SetAttributeFromString( zqFrame, "zqFrame", "FirstLevelOperator", "AND" );
   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:zqFrame.GeneralParameter.Value               = ""
      SetAttributeFromString( zqFrame, "GeneralParameter", "Value", "" );
      //:zqFrame.GeneralParameter.SearchType          = ""
      SetAttributeFromString( zqFrame, "GeneralParameter", "SearchType", "" );
      //:zqFrame.GeneralParameter.ListDisplayFlag     = ""
      SetAttributeFromString( zqFrame, "GeneralParameter", "ListDisplayFlag", "" );
      //:zqFrame.GeneralParameter.SecondLevelOperator = ""
      SetAttributeFromString( zqFrame, "GeneralParameter", "SecondLevelOperator", "" );
      //:zqFrame.GeneralParameter.SubgroupSearchType  = ""
      SetAttributeFromString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" );
      //:FOR EACH zqFrame.GeneralSubParameter
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralSubParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY zqFrame.GeneralSubParameter NONE
         RESULT = DeleteEntity( zqFrame, "GeneralSubParameter", zREPOS_NONE );
         RESULT = SetCursorNextEntity( zqFrame, "GeneralSubParameter", "" );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   //:FOR EACH zqFrame.PostActivateQual
   RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQual", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.PostActivateQual NONE
      RESULT = DeleteEntity( zqFrame, "PostActivateQual", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "PostActivateQual", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildQualEntry( VIEW zqFrame BASED ON LOD zqFrame,
//:                STRING ( 32 )  szEntityName,
//:                STRING ( 32 )  szAttributeName,
//:                STRING ( 100 ) szSearchType,
//:                STRING ( 100 ) szInputSearchValue,
//:                VIEW qConvertData,
//:                VIEW vQualObject )

//:   STRING ( 100 ) Msg
public int 
ozqFrame_BuildQualEntry( View     zqFrame,
                         String   szEntityName,
                         String   szAttributeName,
                         String   szSearchType,
                         String   szInputSearchValue,
                         View     qConvertData,
                         View     vQualObject )
{
   String   Msg = null;
   //:STRING ( 100 ) szSearchValue
   String   szSearchValue = null;
   //:STRING ( 1 )   szErrorFlag
   String   szErrorFlag = null;
   //:INTEGER Length
   int      Length = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// Build a qualification entry based on SearchType and SearchValue.
   //:// If the GeneralParameter datatype is a Date, it is in the format yyyy.mm.dd and needs to be
   //:// converted to yyyymmdd.
   //:// Otherwise it is in regular internal format.
   //:IF szInputSearchValue != ""
   if ( ZeidonStringCompare( szInputSearchValue, 1, 0, "", 1, 0, 101 ) != 0 )
   { 
      //:IF zqFrame.GeneralParameter.DataType = "T"
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "DataType", "T" ) == 0 )
      { 
         //:szSearchValue = szInputSearchValue
          {StringBuilder sb_szSearchValue;
         if ( szSearchValue == null )
            sb_szSearchValue = new StringBuilder( 32 );
         else
            sb_szSearchValue = new StringBuilder( szSearchValue );
                  ZeidonStringCopy( sb_szSearchValue, 1, 0, szInputSearchValue, 1, 0, 101 );
         szSearchValue = sb_szSearchValue.toString( );}
         //:/*szErrorFlag = ""
         //:Length = zGetStringLen( szInputSearchValue )
         //:IF Length != 10
         //:   szErrorFlag = "Y"
         //:END
         //:szSearchValue = szInputSearchValue[1:4] + szInputSearchValue[6:2] + szInputSearchValue[9:2]
         //:IF szErrorFlag = "Y"
         //:   RETURN -1
         //:END*/
         //:ELSE
      } 
      else
      { 
         //:szSearchValue = szInputSearchValue
          {StringBuilder sb_szSearchValue;
         if ( szSearchValue == null )
            sb_szSearchValue = new StringBuilder( 32 );
         else
            sb_szSearchValue = new StringBuilder( szSearchValue );
                  ZeidonStringCopy( sb_szSearchValue, 1, 0, szInputSearchValue, 1, 0, 101 );
         szSearchValue = sb_szSearchValue.toString( );}
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:szSearchValue = szInputSearchValue
       {StringBuilder sb_szSearchValue;
      if ( szSearchValue == null )
         sb_szSearchValue = new StringBuilder( 32 );
      else
         sb_szSearchValue = new StringBuilder( szSearchValue );
            ZeidonStringCopy( sb_szSearchValue, 1, 0, szInputSearchValue, 1, 0, 101 );
      szSearchValue = sb_szSearchValue.toString( );}
   } 

   //:END
   //:CREATE ENTITY vQualObject.QualAttrib
   RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   //:vQualObject.QualAttrib.EntityName    = szEntityName
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", szEntityName );
   //:vQualObject.QualAttrib.AttributeName = szAttributeName
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", szAttributeName );
   //:IF szSearchType = "contains"
   if ( ZeidonStringCompare( szSearchType, 1, 0, "contains", 1, 0, 101 ) == 0 )
   { 
      //:vQualObject.QualAttrib.Value = "%" + szSearchValue + "%"
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringCopy( sb_szTempString_0, 1, 0, "%", 1, 0, 1026 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, szSearchValue, 1, 0, 1026 );
      szTempString_0 = sb_szTempString_0.toString( );}
       {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
            ZeidonStringConcat( sb_szTempString_0, 1, 0, "%", 1, 0, 1026 );
      szTempString_0 = sb_szTempString_0.toString( );}
      SetAttributeFromString( vQualObject, "QualAttrib", "Value", szTempString_0 );
      //:vQualObject.QualAttrib.Oper  = "LIKE"
      SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "LIKE" );
      //:ELSE
   } 
   else
   { 
      //:vQualObject.QualAttrib.Value = szSearchValue
      SetAttributeFromString( vQualObject, "QualAttrib", "Value", szSearchValue );
      //:vQualObject.QualAttrib.Oper  = szSearchType
      SetAttributeFromString( vQualObject, "QualAttrib", "Oper", szSearchType );
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:TraceDecimalValue( VIEW zqFrame BASED ON LOD zqFrame,
//:                   DECIMAL TraceValue,
//:                   STRING ( 20 ) TraceTitle )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
ozqFrame_TraceDecimalValue( View     zqFrame,
                            double  TraceValue,
                            String   TraceTitle )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 20 ) szDecimalValue
   String   szDecimalValue = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:wXferO.Root.WorkDecimal = TraceValue
   SetAttributeFromDecimal( wXferO, "Root", "WorkDecimal", TraceValue );
   //:szDecimalValue = wXferO.Root.WorkDecimal
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szDecimalValue;
   if ( szDecimalValue == null )
      sb_szDecimalValue = new StringBuilder( 32 );
   else
      sb_szDecimalValue = new StringBuilder( szDecimalValue );
       GetVariableFromAttribute( sb_szDecimalValue, mi_lTempInteger_0, 'S', 21, wXferO, "Root", "WorkDecimal", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szDecimalValue = sb_szDecimalValue.toString( );}
   //:TraceLineS( TraceTitle, szDecimalValue )
   TraceLineS( TraceTitle, szDecimalValue );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CheckSubgroupSelection( VIEW zqFrame BASED ON LOD zqFrame,
//:                        VIEW QueryObject,
//:                        STRING ( 32 ) QualifyingEntityName,
//:                        STRING ( 1 )  RootEntityFlag )

//:   SHORT   FinalReturnCode
public int 
ozqFrame_CheckSubgroupSelection( View     zqFrame,
                                 View     QueryObject,
                                 String   QualifyingEntityName,
                                 String   RootEntityFlag )
{
   int      FinalReturnCode = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Loop through any subgroup selection criteria and issue the following return codes.
   //:// -1 if there was selection criteria and the entries didn't meet it.
   //:// 0  if there was no selection criteria.
   //:// 1  if there was selection criteria and the entries did meet it.
   //:// This routine processes the following subgroup operations:
   //://   SUM
   //://   AVG
   //://   COUNT
   //://   MAX
   //://   MIN
   //://   NNULL
   //:// It also processes any entries with a null operation value.

   //:SET CURSOR FIRST zqFrame.PostActivateQual
   //:      WHERE zqFrame.PostActivateQual.ScopingEntityName = QualifyingEntityName
   RESULT = SetCursorFirstEntityByString( zqFrame, "PostActivateQual", "ScopingEntityName", QualifyingEntityName, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:RETURN 0
      if(8==8)return( 0 );
   } 

   //:END

   //:IF zqFrame.PostActivateComponent EXISTS AND RootEntityFlag = "Y"
   lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "PostActivateComponent" );
   if ( lTempInteger_0 == 0 && ZeidonStringCompare( RootEntityFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:// There is a boolean expression for root qualification, so go to process the boolean.
      //:SET CURSOR FIRST zqFrame.PostActivateComponent 
      RESULT = SetCursorFirstEntity( zqFrame, "PostActivateComponent", "" );
      //:FinalReturnCode = CheckPostBooleanExpMn( zqFrame, QueryObject, QualifyingEntityName )
      FinalReturnCode = ozqFrame_CheckPostBooleanExpMn( zqFrame, QueryObject, QualifyingEntityName );
      //:ELSE
   } 
   else
   { 
      //:// There is no boolean structure or we are on a lower level subobject, so process each qualification
      //:// for the specified QualifyingEntityName.
      //:FOR EACH zqFrame.PostActivateQual
      //:   WHERE zqFrame.PostActivateQual.ScopingEntityName = QualifyingEntityName
      RESULT = SetCursorFirstEntityByString( zqFrame, "PostActivateQual", "ScopingEntityName", QualifyingEntityName, "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 

         //:CheckSubgroupSelSingle( zqFrame, QueryObject, QualifyingEntityName )
         ozqFrame_CheckSubgroupSelSingle( zqFrame, QueryObject, QualifyingEntityName );
         RESULT = SetCursorNextEntityByString( zqFrame, "PostActivateQual", "ScopingEntityName", QualifyingEntityName, "" );
      } 

      //:END

      //:// Determine the return code based on whether the first-level boolean operator is AND or OR.
      //:IF zqFrame.zqFrame.FirstLevelOperator = "AND"
      if ( CompareAttributeToString( zqFrame, "zqFrame", "FirstLevelOperator", "AND" ) == 0 )
      { 
         //:FinalReturnCode = 1
         FinalReturnCode = 1;
         //:FOR EACH zqFrame.PostActivateQual
         //:     WHERE zqFrame.PostActivateQual.SearchType != ""
         //:       AND zqFrame.PostActivateQual.ScopingEntityName = QualifyingEntityName
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQual", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SearchType", "" ) != 0 && CompareAttributeToString( zqFrame, "PostActivateQual", "ScopingEntityName", QualifyingEntityName ) == 0 )
            { 
               //:IF zqFrame.PostActivateQual.SelectTrueFlag = "F"
               if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" ) == 0 )
               { 
                  //:FinalReturnCode = -1
                  FinalReturnCode = -1;
               } 

            } 

            RESULT = SetCursorNextEntity( zqFrame, "PostActivateQual", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:FinalReturnCode = 0
         FinalReturnCode = 0;
         //:FOR EACH zqFrame.PostActivateQual
         //:     WHERE zqFrame.PostActivateQual.SearchType != ""
         //:       AND zqFrame.PostActivateQual.ScopingEntityName = QualifyingEntityName
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQual", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SearchType", "" ) != 0 && CompareAttributeToString( zqFrame, "PostActivateQual", "ScopingEntityName", QualifyingEntityName ) == 0 )
            { 
               //:IF zqFrame.PostActivateQual.SelectTrueFlag = "T"
               if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" ) == 0 )
               { 
                  //:FinalReturnCode = 1
                  FinalReturnCode = 1;
                  //:ELSE
               } 
               else
               { 
                  //:IF FinalReturnCode = 0
                  if ( FinalReturnCode == 0 )
                  { 
                     //:FinalReturnCode = -1
                     FinalReturnCode = -1;
                  } 

                  //:END
               } 

            } 

            RESULT = SetCursorNextEntity( zqFrame, "PostActivateQual", "" );
            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:RETURN FinalReturnCode
   return( FinalReturnCode );
// END
} 


//:LOCAL OPERATION
//:CheckPostBooleanExpMn( VIEW zqFrame BASED ON LOD zqFrame,
//:                       VIEW QueryObject,
//:                       STRING ( 32 ) RootEntityName )

//:   VIEW zqFrameT BASED ON LOD zqFrame
public int 
ozqFrame_CheckPostBooleanExpMn( View     zqFrame,
                                View     QueryObject,
                                String   RootEntityName )
{
   zVIEW    zqFrameT = new zVIEW( );
   //:STRING ( 3 ) szOperationCode
   String   szOperationCode = null;
   //:SHORT nRC
   int      nRC = 0;
   //:SHORT nCompareResult
   int      nCompareResult = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:// Evaluate the outer Boolean Expression.

   //:// If there are no outer parens, we will just call the CheckPostBooleanExp operation.
   //:// Otherwise, we will call CheckPostBooleanExp for each group of parens and evaluate the condition
   //:// between the parens here.

   //:nRC = SetCursorFirstEntity( zqFrame, "PostActivateComponent", "" )
   nRC = SetCursorFirstEntity( zqFrame, "PostActivateComponent", "" );
   //:IF zqFrame.PostActivateComponent.Value != "("
   if ( CompareAttributeToString( zqFrame, "PostActivateComponent", "Value", "(" ) != 0 )
   { 
      //:// There are no parens surrounding the expression, so just call CheckPostBooleanExp and return.
      //:nCompareResult = CheckPostBooleanExp( zqFrame, QueryObject, RootEntityName )
      nCompareResult = ozqFrame_CheckPostBooleanExp( zqFrame, QueryObject, RootEntityName );
      //:RETURN nCompareResult
      if(8==8)return( nCompareResult );

      //:ELSE
   } 
   else
   { 

      //:// The expression has groups of parens, so loop through expression processing each parens group.
      //:nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" )
      nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 

         //:// Call CheckPostBooleanExp for each pair of parens and evaluation AND/OR condition here.
         //:szOperationCode = zqFrame.PostActivateComponent.Value
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szOperationCode;
         if ( szOperationCode == null )
            sb_szOperationCode = new StringBuilder( 32 );
         else
            sb_szOperationCode = new StringBuilder( szOperationCode );
                   GetVariableFromAttribute( sb_szOperationCode, mi_lTempInteger_0, 'S', 4, zqFrame, "PostActivateComponent", "Value", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szOperationCode = sb_szOperationCode.toString( );}
         //:IF szOperationCode = "("
         if ( ZeidonStringCompare( szOperationCode, 1, 0, "(", 1, 0, 4 ) == 0 )
         { 
            //:nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" )  // Skip past the left parens.
            nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );
         } 

         //:END

         //:nCompareResult = CheckPostBooleanExp( zqFrame, QueryObject, RootEntityName )
         nCompareResult = ozqFrame_CheckPostBooleanExp( zqFrame, QueryObject, RootEntityName );

         //:nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" )
         nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );

         //:IF nRC >= zCURSOR_SET
         if ( nRC >= zCURSOR_SET )
         { 

            //:szOperationCode = zqFrame.PostActivateComponent.Value
            {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
            StringBuilder sb_szOperationCode;
            if ( szOperationCode == null )
               sb_szOperationCode = new StringBuilder( 32 );
            else
               sb_szOperationCode = new StringBuilder( szOperationCode );
                         GetVariableFromAttribute( sb_szOperationCode, mi_lTempInteger_1, 'S', 4, zqFrame, "PostActivateComponent", "Value", "", 0 );
            lTempInteger_1 = mi_lTempInteger_1.intValue( );
            szOperationCode = sb_szOperationCode.toString( );}
            //:// We've still got more parens to evaluate.
            //:IF szOperationCode = "OR"
            if ( ZeidonStringCompare( szOperationCode, 1, 0, "OR", 1, 0, 4 ) == 0 )
            { 

               //:// On an OR condition, if we have a match, we return the match. Otherwise, we keep looking.
               //:IF nCompareResult = 1
               if ( nCompareResult == 1 )
               { 
                  //://TraceLineS( "*** Return: ", "True" )
                  //:RETURN 1
                  if(8==8)return( 1 );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// On an AND condition, if we don't have a match, we return that no-match. Otherwise, we keep looking.
               //:IF nCompareResult = -1
               if ( nCompareResult == -1 )
               { 
                  //://TraceLineS( "*** Return: ", "False" )
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" )
         nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );
      } 

      //:END

      //:// We've looped through all parens.
      //:IF szOperationCode = "OR"
      if ( ZeidonStringCompare( szOperationCode, 1, 0, "OR", 1, 0, 4 ) == 0 )
      { 

         //:// On an OR condition, if we have a match, we return a match. Otherwise, we return a no-match.
         //:IF nCompareResult = 1
         if ( nCompareResult == 1 )
         { 
            //://TraceLineS( "*** Return: ", "True" )
            //:RETURN 1
            if(8==8)return( 1 );
            //:ELSE
         } 
         else
         { 
            //://TraceLineS( "*** Return: ", "False" )
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// On an AND condition, if we have a match, we return a match. Otherwise, we return a no-match.
         //:IF nCompareResult = 1
         if ( nCompareResult == 1 )
         { 
            //://TraceLineS( "*** Return: ", "True" )
            //:RETURN 1
            if(8==8)return( 1 );
            //:ELSE
         } 
         else
         { 
            //://TraceLineS( "*** Return: ", "False" )
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CheckPostBooleanExp( VIEW zqFrame BASED ON LOD zqFrame,
//:                     VIEW QueryObject,
//:                     STRING ( 32 ) RootEntityName )

//:   VIEW zqFrameT BASED ON LOD zqFrame
public int 
ozqFrame_CheckPostBooleanExp( View     zqFrame,
                              View     QueryObject,
                              String   RootEntityName )
{
   zVIEW    zqFrameT = new zVIEW( );
   //:STRING ( 3 ) szOperationCode
   String   szOperationCode = null;
   //:SHORT nRC
   int      nRC = 0;
   //:SHORT nCompareResult
   int      nCompareResult = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// Evaluate the Boolean Expression.
   //:// This can be a subexpression surrounded by parens, or a full expression, starting with a qualification entry.

   //:// If we entered this operation on an expression in parens, we need to skip the first paren.
   //:// Otherwise, we're positioned on the correct first qualification to evaluation.
   //:IF zqFrame.PostActivateComponent.Value = "("
   if ( CompareAttributeToString( zqFrame, "PostActivateComponent", "Value", "(" ) == 0 )
   { 
      //:nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" )
      nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );
      //:ELSE
   } 
   else
   { 
      //:nRC = 0
      nRC = 0;
   } 

   //:END

   //:// Check to see what boolean operation we're performing by looking at this or the next entry
   //:// for an AND or OR.
   //:szOperationCode = zqFrame.PostActivateComponent.Value
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szOperationCode;
   if ( szOperationCode == null )
      sb_szOperationCode = new StringBuilder( 32 );
   else
      sb_szOperationCode = new StringBuilder( szOperationCode );
       GetVariableFromAttribute( sb_szOperationCode, mi_lTempInteger_0, 'S', 4, zqFrame, "PostActivateComponent", "Value", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szOperationCode = sb_szOperationCode.toString( );}
   //:IF szOperationCode != "AND" AND szOperationCode != "OR"
   if ( ZeidonStringCompare( szOperationCode, 1, 0, "AND", 1, 0, 4 ) != 0 && ZeidonStringCompare( szOperationCode, 1, 0, "OR", 1, 0, 4 ) != 0 )
   { 
      //:CreateViewFromView( zqFrameT, zqFrame )
      CreateViewFromView( zqFrameT, zqFrame );
      //:SetCursorNextEntity( zqFrameT, "PostActivateComponent", "" )
      SetCursorNextEntity( zqFrameT, "PostActivateComponent", "" );
      //:szOperationCode = zqFrameT.PostActivateComponent.Value
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szOperationCode;
      if ( szOperationCode == null )
         sb_szOperationCode = new StringBuilder( 32 );
      else
         sb_szOperationCode = new StringBuilder( szOperationCode );
             GetVariableFromAttribute( sb_szOperationCode, mi_lTempInteger_1, 'S', 4, zqFrameT, "PostActivateComponent", "Value", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szOperationCode = sb_szOperationCode.toString( );}
      //:IF szOperationCode != "AND" AND szOperationCode != "OR"
      if ( ZeidonStringCompare( szOperationCode, 1, 0, "AND", 1, 0, 4 ) != 0 && ZeidonStringCompare( szOperationCode, 1, 0, "OR", 1, 0, 4 ) != 0 )
      { 
         //:szOperationCode = ""
          {StringBuilder sb_szOperationCode;
         if ( szOperationCode == null )
            sb_szOperationCode = new StringBuilder( 32 );
         else
            sb_szOperationCode = new StringBuilder( szOperationCode );
                  ZeidonStringCopy( sb_szOperationCode, 1, 0, "", 1, 0, 4 );
         szOperationCode = sb_szOperationCode.toString( );}
      } 

      //:END
      //:DropView( zqFrameT )
      DropView( zqFrameT );
   } 

   //:END

   //:// Evaluate the criteria.
   //:LOOP WHILE nRC >= zCURSOR_SET AND zqFrame.PostActivateComponent.Value != ")"
   while ( nRC >= zCURSOR_SET && CompareAttributeToString( zqFrame, "PostActivateComponent", "Value", ")" ) != 0 )
   { 
      //:IF zqFrame.PostActivateComponent.Value != "AND" AND zqFrame.PostActivateComponent.Value != "OR"
      if ( CompareAttributeToString( zqFrame, "PostActivateComponent", "Value", "AND" ) != 0 && CompareAttributeToString( zqFrame, "PostActivateComponent", "Value", "OR" ) != 0 )
      { 
         //:IF zqFrame.PostActivateComponent.Value = "("
         if ( CompareAttributeToString( zqFrame, "PostActivateComponent", "Value", "(" ) == 0 )
         { 
            //:nCompareResult = CheckPostBooleanExpMn( zqFrame, QueryObject, RootEntityName )
            nCompareResult = ozqFrame_CheckPostBooleanExpMn( zqFrame, QueryObject, RootEntityName );
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR FIRST zqFrame.PostActivateQual 
            //:           WHERE zqFrame.PostActivateQual.BooleanConditionName = zqFrame.PostActivateComponent.Value 
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame, "PostActivateComponent", "Value" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = SetCursorFirstEntityByString( zqFrame, "PostActivateQual", "BooleanConditionName", szTempString_0, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CheckSubgroupSelSingle( zqFrame, QueryObject, RootEntityName )
               ozqFrame_CheckSubgroupSelSingle( zqFrame, QueryObject, RootEntityName );
               //:IF zqFrame.PostActivateQual.SelectTrueFlag = "T"
               if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" ) == 0 )
               { 
                  //:nCompareResult = 1
                  nCompareResult = 1;
                  //:ELSE
               } 
               else
               { 
                  //:nCompareResult = -1
                  nCompareResult = -1;
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IssueError( zqFrame,0,0, "Boolean not found" )
               IssueError( zqFrame, 0, 0, "Boolean not found" );
            } 

            //:END
         } 

         //:END
         //:      
         //:// Depending on Operation Code, there are two conditions for which we can exit immediately.
         //:IF szOperationCode = "OR" AND nCompareResult = 1
         if ( ZeidonStringCompare( szOperationCode, 1, 0, "OR", 1, 0, 4 ) == 0 && nCompareResult == 1 )
         { 
            //:SET CURSOR NEXT zqFrame.PostActivateComponent WHERE zqFrame.PostActivateComponent.Value = ")" 
            RESULT = SetCursorNextEntityByString( zqFrame, "PostActivateComponent", "Value", ")", "" );
            //:RETURN 1
            if(8==8)return( 1 );
            //:ELSE
         } 
         else
         { 
            //:IF szOperationCode = "AND" AND nCompareResult = -1
            if ( ZeidonStringCompare( szOperationCode, 1, 0, "AND", 1, 0, 4 ) == 0 && nCompareResult == -1 )
            { 
               //:SET CURSOR NEXT zqFrame.PostActivateComponent WHERE zqFrame.PostActivateComponent.Value = ")"
               RESULT = SetCursorNextEntityByString( zqFrame, "PostActivateComponent", "Value", ")", "" );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" )
      nRC = SetCursorNextEntity( zqFrame, "PostActivateComponent", "" );
   } 

   //:END

   //:// If we processed all the entries without exiting, then the true/false condition is whatever was
   //:// the last evaluation.
   //:RETURN nCompareResult
   return( nCompareResult );
// END
} 


//:LOCAL OPERATION
//:CheckSubgroupSelSingle( VIEW zqFrame BASED ON LOD zqFrame,
//:                        VIEW QueryObject,
//:                        STRING ( 32 ) QualifyingEntityName )

//:   DECIMAL Sum
public int 
ozqFrame_CheckSubgroupSelSingle( View     zqFrame,
                                 View     QueryObject,
                                 String   QualifyingEntityName )
{
   double  Sum = 0.0;
   //:DECIMAL Max
   double  Max = 0.0;
   //:DECIMAL Min
   double  Min = 0.0;
   //:DECIMAL Average
   double  Average = 0.0;
   //:DECIMAL AttributeValue
   double  AttributeValue = 0.0;
   //:DECIMAL OperationResult
   double  OperationResult = 0.0;
   //:DECIMAL TargetValue
   double  TargetValue = 0.0;
   //:DECIMAL CenterLatitude
   double  CenterLatitude = 0.0;
   //:DECIMAL CenterLongitude
   double  CenterLongitude = 0.0;
   //:DECIMAL TargetLatitude
   double  TargetLatitude = 0.0;
   //:DECIMAL TargetLongitude
   double  TargetLongitude = 0.0;
   //:DECIMAL DistanceMiles
   double  DistanceMiles = 0.0;
   //:INTEGER Count
   int      Count = 0;
   //:SHORT   FinalReturnCode
   int      FinalReturnCode = 0;
   //:SHORT   nRC
   int      nRC = 0;
   //:STRING ( 10 ) OperationName
   String   OperationName = null;
   //:STRING ( 50 ) CompareValue
   String   CompareValue = null;
   //:STRING ( 1 )  FoundFlag      // Y or N
   String   FoundFlag = null;
   //:STRING ( 1 )  DataType
   String   DataType = null;
   //:STRING ( 2 )  SearchType
   String   SearchType = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   String   szTempString_8 = null;
   String   szTempString_9 = null;
   String   szTempString_10 = null;
   String   szTempString_11 = null;
   String   szTempString_12 = null;
   String   szTempString_13 = null;
   String   szTempString_14 = null;
   String   szTempString_15 = null;
   String   szTempString_16 = null;
   String   szTempString_17 = null;
   String   szTempString_18 = null;
   String   szTempString_19 = null;
   String   szTempString_20 = null;
   String   szTempString_21 = null;
   String   szTempString_22 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_23 = null;
   String   szTempString_24 = null;
   String   szTempString_25 = null;
   String   szTempString_26 = null;
   String   szTempString_27 = null;
   String   szTempString_28 = null;
   String   szTempString_29 = null;


   //:// Process single Subgroup selection entity.
   //:OperationName = zqFrame.PostActivateQual.SubgroupSearchType
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_OperationName;
   if ( OperationName == null )
      sb_OperationName = new StringBuilder( 32 );
   else
      sb_OperationName = new StringBuilder( OperationName );
       GetVariableFromAttribute( sb_OperationName, mi_lTempInteger_0, 'S', 11, zqFrame, "PostActivateQual", "SubgroupSearchType", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   OperationName = sb_OperationName.toString( );}
   //:DataType = zqFrame.PostActivateQual.DataType
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_DataType;
   if ( DataType == null )
      sb_DataType = new StringBuilder( 32 );
   else
      sb_DataType = new StringBuilder( DataType );
       GetVariableFromAttribute( sb_DataType, mi_lTempInteger_1, 'S', 2, zqFrame, "PostActivateQual", "DataType", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   DataType = sb_DataType.toString( );}
   //:IF zqFrame.PostActivateQual.AttributeName = "GeographicPositionZipCode"
   if ( CompareAttributeToString( zqFrame, "PostActivateQual", "AttributeName", "GeographicPositionZipCode" ) == 0 )
   { 
      //:nRC = CheckExistenceOfEntity( QueryObject,
      //:                              zqFrame.PostActivateQual.EntityName )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, zqFrame, "PostActivateQual", "EntityName" );
      szTempString_0 = sb_szTempString_0.toString( );}
      nRC = CheckExistenceOfEntity( QueryObject, szTempString_0 );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 
         //:CenterLatitude  = zqFrame.PostActivateGeographicPosition.CenterLatitude
         {MutableDouble md_CenterLatitude = new MutableDouble( CenterLatitude );
                   GetDecimalFromAttribute( md_CenterLatitude, zqFrame, "PostActivateGeographicPosition", "CenterLatitude" );
         CenterLatitude = md_CenterLatitude.doubleValue( );}
         //:CenterLongitude = zqFrame.PostActivateGeographicPosition.CenterLongitude
         {MutableDouble md_CenterLongitude = new MutableDouble( CenterLongitude );
                   GetDecimalFromAttribute( md_CenterLongitude, zqFrame, "PostActivateGeographicPosition", "CenterLongitude" );
         CenterLongitude = md_CenterLongitude.doubleValue( );}
         //:GetDecimalFromAttribute( TargetLatitude,
         //:                         QueryObject,
         //:                         zqFrame.PostActivateQual.EntityName,
         //:                         "Latitude" )
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, zqFrame, "PostActivateQual", "EntityName" );
         szTempString_1 = sb_szTempString_1.toString( );}
         {MutableDouble md_TargetLatitude = new MutableDouble( TargetLatitude );
                   GetDecimalFromAttribute( md_TargetLatitude, QueryObject, szTempString_1, "Latitude" );
         TargetLatitude = md_TargetLatitude.doubleValue( );}
         //:GetDecimalFromAttribute( TargetLongitude,
         //:                         QueryObject,
         //:                         zqFrame.PostActivateQual.EntityName,
         //:                         "Longitude" )
         {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetStringFromAttribute( sb_szTempString_2, zqFrame, "PostActivateQual", "EntityName" );
         szTempString_2 = sb_szTempString_2.toString( );}
         {MutableDouble md_TargetLongitude = new MutableDouble( TargetLongitude );
                   GetDecimalFromAttribute( md_TargetLongitude, QueryObject, szTempString_2, "Longitude" );
         TargetLongitude = md_TargetLongitude.doubleValue( );}
         //:DistanceMiles = GetDistance( CenterLatitude, CenterLongitude,
         //:                             TargetLatitude, TargetLongitude, 2 )   // 2 is "better" algorithm
         DistanceMiles = m_ZDRVROPR.GetDistance( CenterLatitude, CenterLongitude, TargetLatitude, TargetLongitude, 2 );
         //:SearchType = zqFrame.PostActivateQual.SearchType
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_SearchType;
         if ( SearchType == null )
            sb_SearchType = new StringBuilder( 32 );
         else
            sb_SearchType = new StringBuilder( SearchType );
                   GetVariableFromAttribute( sb_SearchType, mi_lTempInteger_2, 'S', 3, zqFrame, "PostActivateQual", "SearchType", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         SearchType = sb_SearchType.toString( );}
         //:TraceDecimalValue( zqFrame, DistanceMiles, "***DistanceMiles: " )
         ozqFrame_TraceDecimalValue( zqFrame, DistanceMiles, "***DistanceMiles: " );
         //:IF SearchType = "<" OR SearchType = "<="
         if ( ZeidonStringCompare( SearchType, 1, 0, "<", 1, 0, 3 ) == 0 || ZeidonStringCompare( SearchType, 1, 0, "<=", 1, 0, 3 ) == 0 )
         { 
            //:IF DistanceMiles <= zqFrame.PostActivateGeographicPosition.DistanceMiles
            if ( CompareAttributeToDecimal( zqFrame, "PostActivateGeographicPosition", "DistanceMiles", DistanceMiles ) >= 0 )
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "T"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" );
               //:ELSE
            } 
            else
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "F"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" );
               //:TraceDecimalValue( zqFrame, CenterLatitude, "***CenterLatitude: " )
               ozqFrame_TraceDecimalValue( zqFrame, CenterLatitude, "***CenterLatitude: " );
               //:TraceDecimalValue( zqFrame, CenterLongitude, "***CenterLongitude: " )
               ozqFrame_TraceDecimalValue( zqFrame, CenterLongitude, "***CenterLongitude: " );
               //:TraceDecimalValue( zqFrame, TargetLatitude, "***TargetLatitude: " )
               ozqFrame_TraceDecimalValue( zqFrame, TargetLatitude, "***TargetLatitude: " );
               //:TraceDecimalValue( zqFrame, TargetLongitude, "***TargetLongitude: " )
               ozqFrame_TraceDecimalValue( zqFrame, TargetLongitude, "***TargetLongitude: " );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF DistanceMiles >= zqFrame.PostActivateGeographicPosition.DistanceMiles
            if ( CompareAttributeToDecimal( zqFrame, "PostActivateGeographicPosition", "DistanceMiles", DistanceMiles ) <= 0 )
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "T"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" );
               //:ELSE
            } 
            else
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "F"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" );
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF OperationName = ""
      if ( ZeidonStringCompare( OperationName, 1, 0, "", 1, 0, 11 ) == 0 )
      { 
         //:// There is no operation, so process as single compare.
         //:nRC = CheckExistenceOfEntity( QueryObject, zqFrame.PostActivateQual.EntityName )
         {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetStringFromAttribute( sb_szTempString_3, zqFrame, "PostActivateQual", "EntityName" );
         szTempString_3 = sb_szTempString_3.toString( );}
         nRC = CheckExistenceOfEntity( QueryObject, szTempString_3 );
         //:IF nRC < 0 
         if ( nRC < 0 )
         { 
            //:// There is no entity, so set compare result to false.
            //:zqFrame.PostActivateQual.SelectTrueFlag = "F"
            SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" );
            //:ELSE
         } 
         else
         { 
            //:// Compare values depending on data type.
            //:IF DataType = "L" OR DataType = "M"
            if ( ZeidonStringCompare( DataType, 1, 0, "L", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "M", 1, 0, 2 ) == 0 )
            { 
               //:GetDecimalFromAttribute( OperationResult,
               //:                      QueryObject,
               //:                      zqFrame.PostActivateQual.EntityName,
               //:                      zqFrame.PostActivateQual.AttributeName )
               {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetStringFromAttribute( sb_szTempString_4, zqFrame, "PostActivateQual", "EntityName" );
               szTempString_4 = sb_szTempString_4.toString( );}
               {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                               GetStringFromAttribute( sb_szTempString_5, zqFrame, "PostActivateQual", "AttributeName" );
               szTempString_5 = sb_szTempString_5.toString( );}
               {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                               GetDecimalFromAttribute( md_OperationResult, QueryObject, szTempString_4, szTempString_5 );
               OperationResult = md_OperationResult.doubleValue( );}
               //:nRC = fnEvaluateOperatorDec( zqFrame, OperationResult )
               nRC = ozqFrame_fnEvaluateOperatorDec( zqFrame, OperationResult );
               //:ELSE
            } 
            else
            { 
               //:IF DataType = "D" OR DataType = "T"
               if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
               { 
                  //:GetStringFromAttribute( CompareValue,
                  //:                     QueryObject,
                  //:                     zqFrame.PostActivateQual.EntityName,
                  //:                     zqFrame.PostActivateQual.AttributeName )
                  {StringBuilder sb_szTempString_6;
                  if ( szTempString_6 == null )
                     sb_szTempString_6 = new StringBuilder( 32 );
                  else
                     sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                     GetStringFromAttribute( sb_szTempString_6, zqFrame, "PostActivateQual", "EntityName" );
                  szTempString_6 = sb_szTempString_6.toString( );}
                  {StringBuilder sb_szTempString_7;
                  if ( szTempString_7 == null )
                     sb_szTempString_7 = new StringBuilder( 32 );
                  else
                     sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                     GetStringFromAttribute( sb_szTempString_7, zqFrame, "PostActivateQual", "AttributeName" );
                  szTempString_7 = sb_szTempString_7.toString( );}
                  {StringBuilder sb_CompareValue;
                  if ( CompareValue == null )
                     sb_CompareValue = new StringBuilder( 32 );
                  else
                     sb_CompareValue = new StringBuilder( CompareValue );
                                     GetStringFromAttribute( sb_CompareValue, QueryObject, szTempString_6, szTempString_7 );
                  CompareValue = sb_CompareValue.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:GetStrFromAttrByContext( CompareValue, 50,
                  //:                      QueryObject,
                  //:                      zqFrame.PostActivateQual.EntityName,
                  //:                      zqFrame.PostActivateQual.AttributeName,
                  //:                      "" )
                  {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                     GetStringFromAttribute( sb_szTempString_8, zqFrame, "PostActivateQual", "EntityName" );
                  szTempString_8 = sb_szTempString_8.toString( );}
                  {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                     GetStringFromAttribute( sb_szTempString_9, zqFrame, "PostActivateQual", "AttributeName" );
                  szTempString_9 = sb_szTempString_9.toString( );}
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( QueryObject );
                   {StringBuilder sb_CompareValue;
                  if ( CompareValue == null )
                     sb_CompareValue = new StringBuilder( 32 );
                  else
                     sb_CompareValue = new StringBuilder( CompareValue );
                                     m_ZGLOBAL1_Operation.GetStrFromAttrByContext( sb_CompareValue, 50, QueryObject, szTempString_8, szTempString_9, "" );
                  CompareValue = sb_CompareValue.toString( );}
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
               } 

               //:END
               //:nRC = fnEvaluateOperatorStr( zqFrame, CompareValue )  // nRC = 1 is true.
               nRC = ozqFrame_fnEvaluateOperatorStr( zqFrame, CompareValue );
            } 

            //:END
            //:IF nRC = 1
            if ( nRC == 1 )
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "T"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" );
               //:ELSE
            } 
            else
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "F"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" );
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF OperationName = "MAX" OR
         //:OperationName = "MIN" OR
         //:OperationName = "COUNT" OR
         //:OperationName = "NNULL" OR
         //:OperationName = "SUM" OR
         //:OperationName = "AVG"
         if ( ZeidonStringCompare( OperationName, 1, 0, "MAX", 1, 0, 11 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "MIN", 1, 0, 11 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "COUNT", 1, 0, 11 ) == 0 ||
              ZeidonStringCompare( OperationName, 1, 0, "NNULL", 1, 0, 11 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "SUM", 1, 0, 11 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "AVG", 1, 0, 11 ) == 0 )
         { 

            //:IF DataType = "L" OR DataType = "M"
            if ( ZeidonStringCompare( DataType, 1, 0, "L", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "M", 1, 0, 2 ) == 0 )
            { 
               //:// User common routine for computing decimal/integer values for above operations.
               //:ComputeOperationValue( zqFrame, QueryObject, OperationResult, OperationName,
               //:                 zqFrame.PostActivateQual.EntityName,
               //:                 zqFrame.PostActivateQual.AttributeName,
               //:                 QualifyingEntityName )
               {StringBuilder sb_szTempString_10;
               if ( szTempString_10 == null )
                  sb_szTempString_10 = new StringBuilder( 32 );
               else
                  sb_szTempString_10 = new StringBuilder( szTempString_10 );
                               GetStringFromAttribute( sb_szTempString_10, zqFrame, "PostActivateQual", "EntityName" );
               szTempString_10 = sb_szTempString_10.toString( );}
               {StringBuilder sb_szTempString_11;
               if ( szTempString_11 == null )
                  sb_szTempString_11 = new StringBuilder( 32 );
               else
                  sb_szTempString_11 = new StringBuilder( szTempString_11 );
                               GetStringFromAttribute( sb_szTempString_11, zqFrame, "PostActivateQual", "AttributeName" );
               szTempString_11 = sb_szTempString_11.toString( );}
               {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                               ozqFrame_ComputeOperationValue( zqFrame, QueryObject, md_OperationResult, OperationName, szTempString_10, szTempString_11, QualifyingEntityName );
               OperationResult = md_OperationResult.doubleValue( );}
               //:ELSE
            } 
            else
            { 
               //:// User common routine for computing date values for above operations.
               //:ComputeOperationVDate( zqFrame, QueryObject, CompareValue, OperationName,
               //:                 zqFrame.PostActivateQual.EntityName,
               //:                 zqFrame.PostActivateQual.AttributeName,
               //:                 QualifyingEntityName )
               {StringBuilder sb_szTempString_12;
               if ( szTempString_12 == null )
                  sb_szTempString_12 = new StringBuilder( 32 );
               else
                  sb_szTempString_12 = new StringBuilder( szTempString_12 );
                               GetStringFromAttribute( sb_szTempString_12, zqFrame, "PostActivateQual", "EntityName" );
               szTempString_12 = sb_szTempString_12.toString( );}
               {StringBuilder sb_szTempString_13;
               if ( szTempString_13 == null )
                  sb_szTempString_13 = new StringBuilder( 32 );
               else
                  sb_szTempString_13 = new StringBuilder( szTempString_13 );
                               GetStringFromAttribute( sb_szTempString_13, zqFrame, "PostActivateQual", "AttributeName" );
               szTempString_13 = sb_szTempString_13.toString( );}
               {StringBuilder sb_CompareValue;
               if ( CompareValue == null )
                  sb_CompareValue = new StringBuilder( 32 );
               else
                  sb_CompareValue = new StringBuilder( CompareValue );
                               ozqFrame_ComputeOperationVDate( zqFrame, QueryObject, sb_CompareValue, OperationName, szTempString_12, szTempString_13, QualifyingEntityName );
               CompareValue = sb_CompareValue.toString( );}
            } 


            //:END
            //:ELSE
         } 
         else
         { 
            //:IF OperationName = "ANY"
            if ( ZeidonStringCompare( OperationName, 1, 0, "ANY", 1, 0, 11 ) == 0 )
            { 
               //:FoundFlag = "N"
                {StringBuilder sb_FoundFlag;
               if ( FoundFlag == null )
                  sb_FoundFlag = new StringBuilder( 32 );
               else
                  sb_FoundFlag = new StringBuilder( FoundFlag );
                              ZeidonStringCopy( sb_FoundFlag, 1, 0, "N", 1, 0, 2 );
               FoundFlag = sb_FoundFlag.toString( );}
               //:nRC = SetCursorFirstEntity( QueryObject,
               //:                   zqFrame.PostActivateQual.EntityName,
               //:                   QualifyingEntityName )
               {StringBuilder sb_szTempString_14;
               if ( szTempString_14 == null )
                  sb_szTempString_14 = new StringBuilder( 32 );
               else
                  sb_szTempString_14 = new StringBuilder( szTempString_14 );
                               GetStringFromAttribute( sb_szTempString_14, zqFrame, "PostActivateQual", "EntityName" );
               szTempString_14 = sb_szTempString_14.toString( );}
               nRC = SetCursorFirstEntity( QueryObject, szTempString_14, QualifyingEntityName );
               //:LOOP WHILE nRC >= zCURSOR_SET
               while ( nRC >= zCURSOR_SET )
               { 
                  //:IF DataType = "L" OR DataType = "M"
                  if ( ZeidonStringCompare( DataType, 1, 0, "L", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "M", 1, 0, 2 ) == 0 )
                  { 
                     //:GetDecimalFromAttribute( OperationResult,
                     //:                QueryObject,
                     //:                zqFrame.PostActivateQual.EntityName,
                     //:                zqFrame.PostActivateQual.AttributeName )
                     {StringBuilder sb_szTempString_15;
                     if ( szTempString_15 == null )
                        sb_szTempString_15 = new StringBuilder( 32 );
                     else
                        sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                           GetStringFromAttribute( sb_szTempString_15, zqFrame, "PostActivateQual", "EntityName" );
                     szTempString_15 = sb_szTempString_15.toString( );}
                     {StringBuilder sb_szTempString_16;
                     if ( szTempString_16 == null )
                        sb_szTempString_16 = new StringBuilder( 32 );
                     else
                        sb_szTempString_16 = new StringBuilder( szTempString_16 );
                                           GetStringFromAttribute( sb_szTempString_16, zqFrame, "PostActivateQual", "AttributeName" );
                     szTempString_16 = sb_szTempString_16.toString( );}
                     {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                                           GetDecimalFromAttribute( md_OperationResult, QueryObject, szTempString_15, szTempString_16 );
                     OperationResult = md_OperationResult.doubleValue( );}
                     //:nRC = fnEvaluateOperatorDec( zqFrame, OperationResult )
                     nRC = ozqFrame_fnEvaluateOperatorDec( zqFrame, OperationResult );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF DataType = "D" OR DataType = "T"
                     if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
                     { 
                        //:GetStringFromAttribute( CompareValue,
                        //:               QueryObject,
                        //:               zqFrame.PostActivateQual.EntityName,
                        //:               zqFrame.PostActivateQual.AttributeName )
                        {StringBuilder sb_szTempString_17;
                        if ( szTempString_17 == null )
                           sb_szTempString_17 = new StringBuilder( 32 );
                        else
                           sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                                 GetStringFromAttribute( sb_szTempString_17, zqFrame, "PostActivateQual", "EntityName" );
                        szTempString_17 = sb_szTempString_17.toString( );}
                        {StringBuilder sb_szTempString_18;
                        if ( szTempString_18 == null )
                           sb_szTempString_18 = new StringBuilder( 32 );
                        else
                           sb_szTempString_18 = new StringBuilder( szTempString_18 );
                                                 GetStringFromAttribute( sb_szTempString_18, zqFrame, "PostActivateQual", "AttributeName" );
                        szTempString_18 = sb_szTempString_18.toString( );}
                        {StringBuilder sb_CompareValue;
                        if ( CompareValue == null )
                           sb_CompareValue = new StringBuilder( 32 );
                        else
                           sb_CompareValue = new StringBuilder( CompareValue );
                                                 GetStringFromAttribute( sb_CompareValue, QueryObject, szTempString_17, szTempString_18 );
                        CompareValue = sb_CompareValue.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:GetStrFromAttrByContext( CompareValue, 50,
                        //:                QueryObject,
                        //:                zqFrame.PostActivateQual.EntityName,
                        //:                zqFrame.PostActivateQual.AttributeName,
                        //:                "" )
                        {StringBuilder sb_szTempString_19;
                        if ( szTempString_19 == null )
                           sb_szTempString_19 = new StringBuilder( 32 );
                        else
                           sb_szTempString_19 = new StringBuilder( szTempString_19 );
                                                 GetStringFromAttribute( sb_szTempString_19, zqFrame, "PostActivateQual", "EntityName" );
                        szTempString_19 = sb_szTempString_19.toString( );}
                        {StringBuilder sb_szTempString_20;
                        if ( szTempString_20 == null )
                           sb_szTempString_20 = new StringBuilder( 32 );
                        else
                           sb_szTempString_20 = new StringBuilder( szTempString_20 );
                                                 GetStringFromAttribute( sb_szTempString_20, zqFrame, "PostActivateQual", "AttributeName" );
                        szTempString_20 = sb_szTempString_20.toString( );}
                        {
                         ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( QueryObject );
                         {StringBuilder sb_CompareValue;
                        if ( CompareValue == null )
                           sb_CompareValue = new StringBuilder( 32 );
                        else
                           sb_CompareValue = new StringBuilder( CompareValue );
                                                 m_ZGLOBAL1_Operation.GetStrFromAttrByContext( sb_CompareValue, 50, QueryObject, szTempString_19, szTempString_20, "" );
                        CompareValue = sb_CompareValue.toString( );}
                         // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                        }
                     } 

                     //:END
                     //:nRC = fnEvaluateOperatorStr( zqFrame, CompareValue )  // nRC = 1 is true.
                     nRC = ozqFrame_fnEvaluateOperatorStr( zqFrame, CompareValue );
                  } 

                  //:END
                  //:IF nRC = 1
                  if ( nRC == 1 )
                  { 
                     //:FoundFlag = "Y"
                      {StringBuilder sb_FoundFlag;
                     if ( FoundFlag == null )
                        sb_FoundFlag = new StringBuilder( 32 );
                     else
                        sb_FoundFlag = new StringBuilder( FoundFlag );
                                          ZeidonStringCopy( sb_FoundFlag, 1, 0, "Y", 1, 0, 2 );
                     FoundFlag = sb_FoundFlag.toString( );}
                  } 

                  //:END
                  //:nRC = SetCursorNextEntity( QueryObject,
                  //:                  zqFrame.PostActivateQual.EntityName,
                  //:                  QualifyingEntityName )
                  {StringBuilder sb_szTempString_21;
                  if ( szTempString_21 == null )
                     sb_szTempString_21 = new StringBuilder( 32 );
                  else
                     sb_szTempString_21 = new StringBuilder( szTempString_21 );
                                     GetStringFromAttribute( sb_szTempString_21, zqFrame, "PostActivateQual", "EntityName" );
                  szTempString_21 = sb_szTempString_21.toString( );}
                  nRC = SetCursorNextEntity( QueryObject, szTempString_21, QualifyingEntityName );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF OperationName = "ALL"
               if ( ZeidonStringCompare( OperationName, 1, 0, "ALL", 1, 0, 11 ) == 0 )
               { 
                  //:FoundFlag = ""
                   {StringBuilder sb_FoundFlag;
                  if ( FoundFlag == null )
                     sb_FoundFlag = new StringBuilder( 32 );
                  else
                     sb_FoundFlag = new StringBuilder( FoundFlag );
                                    ZeidonStringCopy( sb_FoundFlag, 1, 0, "", 1, 0, 2 );
                  FoundFlag = sb_FoundFlag.toString( );}
                  //:nRC = SetCursorFirstEntity( QueryObject,
                  //:                zqFrame.PostActivateQual.EntityName,
                  //:                QualifyingEntityName )
                  {StringBuilder sb_szTempString_22;
                  if ( szTempString_22 == null )
                     sb_szTempString_22 = new StringBuilder( 32 );
                  else
                     sb_szTempString_22 = new StringBuilder( szTempString_22 );
                                     GetStringFromAttribute( sb_szTempString_22, zqFrame, "PostActivateQual", "EntityName" );
                  szTempString_22 = sb_szTempString_22.toString( );}
                  nRC = SetCursorFirstEntity( QueryObject, szTempString_22, QualifyingEntityName );
                  //:// If the search operator is "<>" and the entity for evaluating the condition
                  //:// does not exist, consider the condition TRUE.
                  //:IF zqFrame.PostActivateQual.SearchType = "subselect"
                  if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SearchType", "subselect" ) == 0 )
                  { 
                     //:SearchType = zqFrame.PostActivateQualSub.SearchType
                     {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                     StringBuilder sb_SearchType;
                     if ( SearchType == null )
                        sb_SearchType = new StringBuilder( 32 );
                     else
                        sb_SearchType = new StringBuilder( SearchType );
                                           GetVariableFromAttribute( sb_SearchType, mi_lTempInteger_3, 'S', 3, zqFrame, "PostActivateQualSub", "SearchType", "", 0 );
                     lTempInteger_3 = mi_lTempInteger_3.intValue( );
                     SearchType = sb_SearchType.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:SearchType = zqFrame.PostActivateQual.SearchType
                     {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                     StringBuilder sb_SearchType;
                     if ( SearchType == null )
                        sb_SearchType = new StringBuilder( 32 );
                     else
                        sb_SearchType = new StringBuilder( SearchType );
                                           GetVariableFromAttribute( sb_SearchType, mi_lTempInteger_4, 'S', 3, zqFrame, "PostActivateQual", "SearchType", "", 0 );
                     lTempInteger_4 = mi_lTempInteger_4.intValue( );
                     SearchType = sb_SearchType.toString( );}
                  } 

                  //:END
                  //:IF SearchType = "<>" AND
                  //:nRC < zCURSOR_SET
                  if ( ZeidonStringCompare( SearchType, 1, 0, "<>", 1, 0, 3 ) == 0 && nRC < zCURSOR_SET )
                  { 

                     //:FoundFlag = "Y"
                      {StringBuilder sb_FoundFlag;
                     if ( FoundFlag == null )
                        sb_FoundFlag = new StringBuilder( 32 );
                     else
                        sb_FoundFlag = new StringBuilder( FoundFlag );
                                          ZeidonStringCopy( sb_FoundFlag, 1, 0, "Y", 1, 0, 2 );
                     FoundFlag = sb_FoundFlag.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:LOOP WHILE nRC >= zCURSOR_SET
                     while ( nRC >= zCURSOR_SET )
                     { 
                        //:IF DataType = "L" OR DataType = "M"
                        if ( ZeidonStringCompare( DataType, 1, 0, "L", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "M", 1, 0, 2 ) == 0 )
                        { 
                           //:GetDecimalFromAttribute( OperationResult,
                           //:             QueryObject,
                           //:             zqFrame.PostActivateQual.EntityName,
                           //:             zqFrame.PostActivateQual.AttributeName )
                           {StringBuilder sb_szTempString_23;
                           if ( szTempString_23 == null )
                              sb_szTempString_23 = new StringBuilder( 32 );
                           else
                              sb_szTempString_23 = new StringBuilder( szTempString_23 );
                                                       GetStringFromAttribute( sb_szTempString_23, zqFrame, "PostActivateQual", "EntityName" );
                           szTempString_23 = sb_szTempString_23.toString( );}
                           {StringBuilder sb_szTempString_24;
                           if ( szTempString_24 == null )
                              sb_szTempString_24 = new StringBuilder( 32 );
                           else
                              sb_szTempString_24 = new StringBuilder( szTempString_24 );
                                                       GetStringFromAttribute( sb_szTempString_24, zqFrame, "PostActivateQual", "AttributeName" );
                           szTempString_24 = sb_szTempString_24.toString( );}
                           {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                                                       GetDecimalFromAttribute( md_OperationResult, QueryObject, szTempString_23, szTempString_24 );
                           OperationResult = md_OperationResult.doubleValue( );}
                           //:nRC = fnEvaluateOperatorDec( zqFrame, OperationResult )
                           nRC = ozqFrame_fnEvaluateOperatorDec( zqFrame, OperationResult );
                           //:ELSE
                        } 
                        else
                        { 
                           //:IF DataType = "D" OR DataType = "T"
                           if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
                           { 
                              //:GetStringFromAttribute( CompareValue,
                              //:            QueryObject,
                              //:            zqFrame.PostActivateQual.EntityName,
                              //:            zqFrame.PostActivateQual.AttributeName )
                              {StringBuilder sb_szTempString_25;
                              if ( szTempString_25 == null )
                                 sb_szTempString_25 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_25 = new StringBuilder( szTempString_25 );
                                                             GetStringFromAttribute( sb_szTempString_25, zqFrame, "PostActivateQual", "EntityName" );
                              szTempString_25 = sb_szTempString_25.toString( );}
                              {StringBuilder sb_szTempString_26;
                              if ( szTempString_26 == null )
                                 sb_szTempString_26 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_26 = new StringBuilder( szTempString_26 );
                                                             GetStringFromAttribute( sb_szTempString_26, zqFrame, "PostActivateQual", "AttributeName" );
                              szTempString_26 = sb_szTempString_26.toString( );}
                              {StringBuilder sb_CompareValue;
                              if ( CompareValue == null )
                                 sb_CompareValue = new StringBuilder( 32 );
                              else
                                 sb_CompareValue = new StringBuilder( CompareValue );
                                                             GetStringFromAttribute( sb_CompareValue, QueryObject, szTempString_25, szTempString_26 );
                              CompareValue = sb_CompareValue.toString( );}
                              //:ELSE
                           } 
                           else
                           { 
                              //:GetStrFromAttrByContext( CompareValue, 50,
                              //:             QueryObject,
                              //:             zqFrame.PostActivateQual.EntityName,
                              //:             zqFrame.PostActivateQual.AttributeName,
                              //:             "" )
                              {StringBuilder sb_szTempString_27;
                              if ( szTempString_27 == null )
                                 sb_szTempString_27 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_27 = new StringBuilder( szTempString_27 );
                                                             GetStringFromAttribute( sb_szTempString_27, zqFrame, "PostActivateQual", "EntityName" );
                              szTempString_27 = sb_szTempString_27.toString( );}
                              {StringBuilder sb_szTempString_28;
                              if ( szTempString_28 == null )
                                 sb_szTempString_28 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_28 = new StringBuilder( szTempString_28 );
                                                             GetStringFromAttribute( sb_szTempString_28, zqFrame, "PostActivateQual", "AttributeName" );
                              szTempString_28 = sb_szTempString_28.toString( );}
                              {
                               ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( QueryObject );
                               {StringBuilder sb_CompareValue;
                              if ( CompareValue == null )
                                 sb_CompareValue = new StringBuilder( 32 );
                              else
                                 sb_CompareValue = new StringBuilder( CompareValue );
                                                             m_ZGLOBAL1_Operation.GetStrFromAttrByContext( sb_CompareValue, 50, QueryObject, szTempString_27, szTempString_28, "" );
                              CompareValue = sb_CompareValue.toString( );}
                               // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                              }
                           } 

                           //:END
                           //:nRC = fnEvaluateOperatorStr( zqFrame, CompareValue )  // nRC = 1 is true.
                           nRC = ozqFrame_fnEvaluateOperatorStr( zqFrame, CompareValue );
                        } 

                        //:END
                        //:IF nRC = 1
                        if ( nRC == 1 )
                        { 
                           //:IF FoundFlag = ""
                           if ( ZeidonStringCompare( FoundFlag, 1, 0, "", 1, 0, 2 ) == 0 )
                           { 
                              //:FoundFlag = "Y"
                               {StringBuilder sb_FoundFlag;
                              if ( FoundFlag == null )
                                 sb_FoundFlag = new StringBuilder( 32 );
                              else
                                 sb_FoundFlag = new StringBuilder( FoundFlag );
                                                            ZeidonStringCopy( sb_FoundFlag, 1, 0, "Y", 1, 0, 2 );
                              FoundFlag = sb_FoundFlag.toString( );}
                           } 

                           //:END
                           //:ELSE
                        } 
                        else
                        { 
                           //:FoundFlag = "N"
                            {StringBuilder sb_FoundFlag;
                           if ( FoundFlag == null )
                              sb_FoundFlag = new StringBuilder( 32 );
                           else
                              sb_FoundFlag = new StringBuilder( FoundFlag );
                                                      ZeidonStringCopy( sb_FoundFlag, 1, 0, "N", 1, 0, 2 );
                           FoundFlag = sb_FoundFlag.toString( );}
                        } 

                        //:END
                        //:nRC = SetCursorNextEntity( QueryObject,
                        //:               zqFrame.PostActivateQual.EntityName,
                        //:               QualifyingEntityName )
                        {StringBuilder sb_szTempString_29;
                        if ( szTempString_29 == null )
                           sb_szTempString_29 = new StringBuilder( 32 );
                        else
                           sb_szTempString_29 = new StringBuilder( szTempString_29 );
                                                 GetStringFromAttribute( sb_szTempString_29, zqFrame, "PostActivateQual", "EntityName" );
                        szTempString_29 = sb_szTempString_29.toString( );}
                        nRC = SetCursorNextEntity( QueryObject, szTempString_29, QualifyingEntityName );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:// Determine Return code.
   //:IF zqFrame.PostActivateQual.SearchType = ""
   if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SearchType", "" ) == 0 )
   { 
      //:zqFrame.PostActivateQual.SelectTrueFlag = ""   // There was no compare type.
      SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "" );
      //:ELSE
   } 
   else
   { 
      //:IF OperationName = "ANY" OR OperationName = "ALL"
      if ( ZeidonStringCompare( OperationName, 1, 0, "ANY", 1, 0, 11 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "ALL", 1, 0, 11 ) == 0 )
      { 
         //:// The only valid operations for ANY or ALL are = and <>.
         //:IF FoundFlag = "Y"
         if ( ZeidonStringCompare( FoundFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:zqFrame.PostActivateQual.SelectTrueFlag = "T"
            SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" );
            //:ELSE
         } 
         else
         { 
            //:zqFrame.PostActivateQual.SelectTrueFlag = "F"
            SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF OperationName != ""
         if ( ZeidonStringCompare( OperationName, 1, 0, "", 1, 0, 11 ) != 0 )
         { 
            //:// Evaluate operator for SUM, AVG, COUNT, NNULL, MAX and MIN.
            //:IF DataType = "L" OR DataType = "M"
            if ( ZeidonStringCompare( DataType, 1, 0, "L", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "M", 1, 0, 2 ) == 0 )
            { 
               //:nRC = fnEvaluateOperatorDec( zqFrame, OperationResult )  // nRC = 1 is true.
               nRC = ozqFrame_fnEvaluateOperatorDec( zqFrame, OperationResult );
               //:ELSE
            } 
            else
            { 
               //:nRC = fnEvaluateOperatorStr( zqFrame, CompareValue )  // nRC = 1 is true.
               nRC = ozqFrame_fnEvaluateOperatorStr( zqFrame, CompareValue );
            } 

            //:END
            //:IF nRC = 1
            if ( nRC == 1 )
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "T"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "T" );
               //:ELSE
            } 
            else
            { 
               //:zqFrame.PostActivateQual.SelectTrueFlag = "F"
               SetAttributeFromString( zqFrame, "PostActivateQual", "SelectTrueFlag", "F" );
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ValidateQuery( VIEW zqFrame BASED ON LOD zqFrame,
//:               STRING ( 10 ) szType )

//:   STRING ( 200 ) Msg
public int 
ozqFrame_ValidateQuery( View     zqFrame,
                        String   szType )
{
   String   Msg = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_4 = 0;


   //:// Validate the query input.

   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// If SubgroupSearchType is ANY or ALL, the SearchType can only be '=', '<>' or subselect.
      //:// Also any SubParameters must be '=' or '<>' and the 2nd level operator must be 'OR'.
      //:// Also if a SubParameter is '=', the 2nd level operatormust be 'OR'.
      //://      If a SubParameter is '<>', the 2nd level operatormust be 'AND'.
      //:IF zqFrame.GeneralParameter.SubgroupSearchType = "ANY" OR
      //:   zqFrame.GeneralParameter.SubgroupSearchType = "ALL"
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) == 0 || CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ALL" ) == 0 )
      { 

         //:IF zqFrame.GeneralParameter.SearchType = "contains"
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SearchType", "contains" ) == 0 )
         { 

            //:Msg = "Qualification Entry, '" + zqFrame.GeneralParameter.Prompt + "', is in error." +
            //:      NEW_LINE + "The 'Search Type contains' is not valid for Subgroup selection ANY or ALL."
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 255, zqFrame, "GeneralParameter", "Prompt", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringCopy( sb_Msg, 1, 0, "Qualification Entry, '", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, szTempString_0, 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, "', is in error.", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, "The 'Search Type contains' is not valid for Subgroup selection ANY or ALL.", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
            //:MessageSend( zqFrame, "", "Query",
            //:             Msg,
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:FOR EACH zqFrame.GeneralSubParameter WHERE zqFrame.GeneralSubParameter.SearchType != ""
         RESULT = SetCursorFirstEntity( zqFrame, "GeneralSubParameter", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "" ) != 0 )
            { 
               //:IF zqFrame.GeneralSubParameter.SearchType != "=" AND
               //:zqFrame.GeneralSubParameter.SearchType != "<>"
               if ( CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "=" ) != 0 && CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "<>" ) != 0 )
               { 

                  //:Msg = "Qualification Entry, '" + zqFrame.GeneralParameter.Prompt + "', is in error." +
                  //:   NEW_LINE + "The subselect 'Search Type' is not valid for Subgroup selection ANY or ALL."
                  {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                  StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, zqFrame, "GeneralParameter", "Prompt", "", 0 );
                  lTempInteger_1 = mi_lTempInteger_1.intValue( );
                  szTempString_1 = sb_szTempString_1.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringCopy( sb_Msg, 1, 0, "Qualification Entry, '", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, szTempString_1, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "', is in error.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "The subselect 'Search Type' is not valid for Subgroup selection ANY or ALL.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                  //:MessageSend( zqFrame, "", "Query",
                  //:          Msg,
                  //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:IF zqFrame.GeneralParameter.SecondLevelOperator = "OR" AND
               //:zqFrame.GeneralSubParameter.SearchType = "<>"
               if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SecondLevelOperator", "OR" ) == 0 && CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "<>" ) == 0 )
               { 

                  //:Msg = "Qualification Entry, '" + zqFrame.GeneralParameter.Prompt + "', is in error." +
                  //:   NEW_LINE + "For 'ANY/ALL', the 'Search Type' must be '=' if the '2nd Level Operator' is 'OR'."
                  {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                  StringBuilder sb_szTempString_2;
                  if ( szTempString_2 == null )
                     sb_szTempString_2 = new StringBuilder( 32 );
                  else
                     sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                     GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 255, zqFrame, "GeneralParameter", "Prompt", "", 0 );
                  lTempInteger_2 = mi_lTempInteger_2.intValue( );
                  szTempString_2 = sb_szTempString_2.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringCopy( sb_Msg, 1, 0, "Qualification Entry, '", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, szTempString_2, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "', is in error.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "For 'ANY/ALL', the 'Search Type' must be '=' if the '2nd Level Operator' is 'OR'.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                  //:MessageSend( zqFrame, "", "Query",
                  //:          Msg,
                  //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:IF zqFrame.GeneralParameter.SecondLevelOperator = "AND" AND
               //:zqFrame.GeneralSubParameter.SearchType = "="
               if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SecondLevelOperator", "AND" ) == 0 && CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "=" ) == 0 )
               { 

                  //:Msg = "Qualification Entry, '" + zqFrame.GeneralParameter.Prompt + "', is in error." +
                  //:   NEW_LINE + "For 'ANY/ALL', the 'Search Type' must be '=' if the '2nd Level Operator' is 'OR'."
                  {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                  StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 255, zqFrame, "GeneralParameter", "Prompt", "", 0 );
                  lTempInteger_3 = mi_lTempInteger_3.intValue( );
                  szTempString_3 = sb_szTempString_3.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringCopy( sb_Msg, 1, 0, "Qualification Entry, '", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, szTempString_3, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "', is in error.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                   {StringBuilder sb_Msg;
                  if ( Msg == null )
                     sb_Msg = new StringBuilder( 32 );
                  else
                     sb_Msg = new StringBuilder( Msg );
                                    ZeidonStringConcat( sb_Msg, 1, 0, "For 'ANY/ALL', the 'Search Type' must be '=' if the '2nd Level Operator' is 'OR'.", 1, 0, 201 );
                  Msg = sb_Msg.toString( );}
                  //:MessageSend( zqFrame, "", "Query",
                  //:          Msg,
                  //:          zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                  MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

            } 

            RESULT = SetCursorNextEntity( zqFrame, "GeneralSubParameter", "" );
            //:END
         } 

         //:END
      } 

      //:END

      //:// For SUM and AVG, the attribute must be an integer or a decimal.
      //:IF zqFrame.GeneralParameter.SubgroupSearchType = "SUM" OR
      //:   zqFrame.GeneralParameter.SubgroupSearchType = "AVG"
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "SUM" ) == 0 || CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "AVG" ) == 0 )
      { 

         //:IF zqFrame.GeneralParameter.DataType != "L" AND
         //:   zqFrame.GeneralParameter.DataType != "M"
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "DataType", "L" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "DataType", "M" ) != 0 )
         { 

            //:Msg = "Qualification Entry, '" + zqFrame.GeneralParameter.Prompt + "', is in error." +
            //:      NEW_LINE + "SUM or AVG operators are not valid unless the attribute is numeric."
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                         GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_4, 'S', 255, zqFrame, "GeneralParameter", "Prompt", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_4 = sb_szTempString_4.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringCopy( sb_Msg, 1, 0, "Qualification Entry, '", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, szTempString_4, 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, "', is in error.", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, NEW_LINE, 1, 0, 201 );
            Msg = sb_Msg.toString( );}
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringConcat( sb_Msg, 1, 0, "SUM or AVG operators are not valid unless the attribute is numeric.", 1, 0, 201 );
            Msg = sb_Msg.toString( );}
            //:MessageSend( zqFrame, "", "Query",
            //:             Msg,
            //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatCSV_EntityRecurs( VIEW zqFrame     BASED ON LOD zqFrame,
//:                        VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                        VIEW vResultSet,
//:                        STRING ( 10000 ) OutputLine,
//:                        STRING ( 1 )   szTypeFlag,
//:                        INTEGER        FilePointer,
//:                        INTEGER        HierarchicalLevel,
//:                        INTEGER        LastHierarchicalLevel,
//:                        STRING ( 32 )  szScopingEntityName,
//:                        INTEGER        ColumnCount )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
ozqFrame_FormatCSV_EntityRecurs( View     zqFrame,
                                 View     zqFrameRoot,
                                 View     vResultSet,
                                 StringBuilder   OutputLine,
                                 String   szTypeFlag,
                                 int      FilePointer,
                                 MutableInt   HierarchicalLevel,
                                 MutableInt   LastHierarchicalLevel,
                                 String   szScopingEntityName,
                                 MutableInt   ColumnCount )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 )   szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )   szAttributeName
   String   szAttributeName = null;
   //:STRING ( 32 )   szMappingEntityName
   String   szMappingEntityName = null;
   //:STRING ( 32 )   szTraceEntityName
   String   szTraceEntityName = null;
   //:STRING ( 10000 ) FormattedValue
   String   FormattedValue = null;
   //:STRING ( 3 )    szHierarchicalLevel
   String   szHierarchicalLevel = null;
   //:STRING ( 3 )    szLastHierarchicalLevel
   String   szLastHierarchicalLevel = null;
   //:STRING ( 100 )  szTraceLine
   String   szTraceLine = null;
   //:INTEGER         NextColumnCount
   int      NextColumnCount = 0;
   //:INTEGER         RecursiveLevel
   int      RecursiveLevel = 0;
   //:DECIMAL         OperationResult
   double  OperationResult = 0.0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_7 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_8 = null;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:// Loop through all entities with max cardinality > 1, since they are the only entities
   //:// that cause an output line to be written.

   //:LastHierarchicalLevel = HierarchicalLevel
   LastHierarchicalLevel.setValue( HierarchicalLevel );
   //:HierarchicalLevel = HierarchicalLevel + 1
   HierarchicalLevel.increment( );

   //:FOR EACH zqFrame.ParentEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //://szTraceEntityName = zqFrame.ParentEntity.EntityName 
      //://TraceLineS( "### Trace Entity: ", szTraceEntityName )
      //://IF szTraceEntityName = "AdmissionsTypeSummary"
      //://   TraceLineS( "### Stop ", "" )
      //://END
      //:IF zqFrame.ParentEntity.MaxCardinality > 1
      if ( CompareAttributeToInteger( zqFrame, "ParentEntity", "MaxCardinality", 1 ) > 0 )
      { 
         //:// Loop for each occurrence of the entity.
         //:szEntityName = zqFrame.ParentEntity.EntityName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, zqFrame, "ParentEntity", "EntityName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szEntityName = sb_szEntityName.toString( );}
         //:nRC = SetCursorFirstEntity( vResultSet, szEntityName, "" )
         nRC = SetCursorFirstEntity( vResultSet, szEntityName, "" );
         //:LOOP WHILE nRC >= zCURSOR_SET
         while ( nRC >= zCURSOR_SET )
         { 
            //:// Only process those entities that have display attributes.
            //:SET CURSOR FIRST zqFrame.QueryAttribute
            RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:NextColumnCount = zqFrame.QueryAttribute.CSV_ColumnNumber
               {MutableInt mi_NextColumnCount = new MutableInt( NextColumnCount );
                               GetIntegerFromAttribute( mi_NextColumnCount, zqFrame, "QueryAttribute", "CSV_ColumnNumber" );
               NextColumnCount = mi_NextColumnCount.intValue( );}
               //:// If the Output field has data, write it out.
               //:IF OutputLine != ""
               if ( ZeidonStringCompare( OutputLine.toString( ), 1, 0, "", 1, 0, 10001 ) != 0 )
               { 
                  //:IF szTypeFlag = "F"
                  if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
                  { 

                     //:// Since we just came across a 0-to-m entity, write out the previous data.

                     //:// Go to format OutputLine from CSV_OutputLineItem, then write it out.
                     //:FormatCSV_FullOutput( zqFrameRoot, OutputLine )
                     ozqFrame_FormatCSV_FullOutput( zqFrameRoot, OutputLine );
                     //:SysWriteLine( zqFrame, FilePointer, OutputLine )
                     try
                     {
                         {
                      zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
                      m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine.toString( ) );
                      // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
                     };
                     }
                     catch ( Exception e )
                     {
                        throw ZeidonException.wrapException( e );
                     }
                     //:OutputLine = ""
                     ZeidonStringCopy( OutputLine, 1, 0, "", 1, 0, 10001 );

                     //:// Null out the format values for this entity, which will be formatted to OutputLine below.
                     //:// Null out the format values for this entity and any entities at the same or a lower level, as
                     //:// defined by the CSV_OutputLineItem.RecursiveLevel attribute.
                     //:SET CURSOR FIRST zqFrameRoot.CSV_OutputLineItem 
                     //:           WHERE zqFrameRoot.CSV_OutputLineItem.EntityNameToFormat = szEntityName
                     RESULT = SetCursorFirstEntityByString( zqFrameRoot, "CSV_OutputLineItem", "EntityNameToFormat", szEntityName, "" );
                     //:IF RESULT >= zCURSOR_SET             
                     if ( RESULT >= zCURSOR_SET )
                     { 
                        //:RecursiveLevel = zqFrameRoot.CSV_OutputLineItem.RecursiveLevel 
                        {MutableInt mi_RecursiveLevel = new MutableInt( RecursiveLevel );
                                                 GetIntegerFromAttribute( mi_RecursiveLevel, zqFrameRoot, "CSV_OutputLineItem", "RecursiveLevel" );
                        RecursiveLevel = mi_RecursiveLevel.intValue( );}
                        //:FOR EACH zqFrameRoot.CSV_OutputLineItem 
                        RESULT = SetCursorFirstEntity( zqFrameRoot, "CSV_OutputLineItem", "" );
                        while ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           //:IF zqFrameRoot.CSV_OutputLineItem.RecursiveLevel >= RecursiveLevel
                           if ( CompareAttributeToInteger( zqFrameRoot, "CSV_OutputLineItem", "RecursiveLevel", RecursiveLevel ) >= 0 )
                           { 
                              //:zqFrameRoot.CSV_OutputLineItem.Value = ""
                              SetAttributeFromString( zqFrameRoot, "CSV_OutputLineItem", "Value", "" );
                           } 

                           RESULT = SetCursorNextEntity( zqFrameRoot, "CSV_OutputLineItem", "" );
                           //:END
                        } 

                        //:END
                     } 

                     //:END

                     //://szHierarchicalLevel = HierarchicalLevel
                     //://szLastHierarchicalLevel = LastHierarchicalLevel
                     //://szTraceLine = szLastHierarchicalLevel + " " + szHierarchicalLevel + " " + OutputLine
                     //://IssueError( zqFrame,0,0, szTraceLine )
                     //:/*IF LastHierarchicalLevel >= HierarchicalLevel
                     //:   SysWriteLine( zqFrame, FilePointer, OutputLine )
                     //:   OutputLine = ""
                     //:END
                     //:LastHierarchicalLevel = HierarchicalLevel*/
                     //:ELSE
                  } 
                  else
                  { 
                     //:SysWriteLine( zqFrame, FilePointer, OutputLine )
                     try
                     {
                         {
                      zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
                      m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine.toString( ) );
                      // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
                     };
                     }
                     catch ( Exception e )
                     {
                        throw ZeidonException.wrapException( e );
                     }
                     //:OutputLine = ""
                     ZeidonStringCopy( OutputLine, 1, 0, "", 1, 0, 10001 );
                     //:IF zqFrameRoot.zqFrame.wCurrentDisplayFormat = "R"
                     if ( CompareAttributeToString( zqFrameRoot, "zqFrame", "wCurrentDisplayFormat", "R" ) == 0 )
                     { 
                        //:CREATE ENTITY zqFrameRoot.ResultSetDisplayRow
                        RESULT = CreateEntity( zqFrameRoot, "ResultSetDisplayRow", zPOS_AFTER );
                        //:ColumnCount = 1
                        ColumnCount.setValue( 1 );
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END

               //:IF szTypeFlag != "F"
               if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) != 0 )
               { 
                  //:// Put in leading commas for prior formatted attribute values.
                  //:FormatCSV_StartLine( zqFrame, OutputLine, NextColumnCount, szTypeFlag )
                  ozqFrame_FormatCSV_StartLine( zqFrame, OutputLine, NextColumnCount, szTypeFlag );
               } 

               //:END

               //:// Format attributes for this entity.
               //:// Note that all the attributes to be mapped with this entity are listed as QueryAttribute entities,
               //:// even if they come from entities that are 1-to-1 children under this entity.
               //:FOR EACH zqFrame.QueryAttribute
               RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 

                  //:IF zqFrame.QueryAttribute.GroupOperation != ""
                  if ( CompareAttributeToString( zqFrame, "QueryAttribute", "GroupOperation", "" ) != 0 )
                  { 
                     //:ComputeOperationValue( zqFrame,
                     //:                       vResultSet,
                     //:                       OperationResult,
                     //:                       zqFrame.QueryAttribute.GroupOperation,
                     //:                       zqFrame.QueryAttribute.GroupOperationEntityName,
                     //:                       zqFrame.QueryAttribute.GroupOperationAttributeName,
                     //:                       zqFrame.QueryAttribute.GroupOperationScopingEntityName )
                     {StringBuilder sb_szTempString_0;
                     if ( szTempString_0 == null )
                        sb_szTempString_0 = new StringBuilder( 32 );
                     else
                        sb_szTempString_0 = new StringBuilder( szTempString_0 );
                                           GetStringFromAttribute( sb_szTempString_0, zqFrame, "QueryAttribute", "GroupOperation" );
                     szTempString_0 = sb_szTempString_0.toString( );}
                     {StringBuilder sb_szTempString_1;
                     if ( szTempString_1 == null )
                        sb_szTempString_1 = new StringBuilder( 32 );
                     else
                        sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                           GetStringFromAttribute( sb_szTempString_1, zqFrame, "QueryAttribute", "GroupOperationEntityName" );
                     szTempString_1 = sb_szTempString_1.toString( );}
                     {StringBuilder sb_szTempString_2;
                     if ( szTempString_2 == null )
                        sb_szTempString_2 = new StringBuilder( 32 );
                     else
                        sb_szTempString_2 = new StringBuilder( szTempString_2 );
                                           GetStringFromAttribute( sb_szTempString_2, zqFrame, "QueryAttribute", "GroupOperationAttributeName" );
                     szTempString_2 = sb_szTempString_2.toString( );}
                     {StringBuilder sb_szTempString_3;
                     if ( szTempString_3 == null )
                        sb_szTempString_3 = new StringBuilder( 32 );
                     else
                        sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                           GetStringFromAttribute( sb_szTempString_3, zqFrame, "QueryAttribute", "GroupOperationScopingEntityName" );
                     szTempString_3 = sb_szTempString_3.toString( );}
                     {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                                           ozqFrame_ComputeOperationValue( zqFrame, vResultSet, md_OperationResult, szTempString_0, szTempString_1, szTempString_2, szTempString_3 );
                     OperationResult = md_OperationResult.doubleValue( );}
                     //:wXferO.QueryValues.WorkRevenue = OperationResult
                     SetAttributeFromDecimal( wXferO, "QueryValues", "WorkRevenue", OperationResult );
                     //:FormattedValue = wXferO.QueryValues.WorkRevenue
                     {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
                     StringBuilder sb_FormattedValue;
                     if ( FormattedValue == null )
                        sb_FormattedValue = new StringBuilder( 32 );
                     else
                        sb_FormattedValue = new StringBuilder( FormattedValue );
                                           GetVariableFromAttribute( sb_FormattedValue, mi_lTempInteger_1, 'S', 10001, wXferO, "QueryValues", "WorkRevenue", "", 0 );
                     lTempInteger_1 = mi_lTempInteger_1.intValue( );
                     FormattedValue = sb_FormattedValue.toString( );}
                     //:IF szTypeFlag = "F"
                     if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
                     { 
                        //:SET CURSOR FIRST zqFrameRoot.CSV_OutputLineItem 
                        //:           WHERE zqFrameRoot.CSV_OutputLineItem.EntityName     = zqFrame.QueryAttribute.GroupOperationEntityName 
                        //:             AND zqFrameRoot.CSV_OutputLineItem.AttributeName  = zqFrame.QueryAttribute.GroupOperationAttributeName 
                        //:             AND zqFrameRoot.CSV_OutputLineItem.GroupOperation = zqFrame.QueryAttribute.GroupOperation 
                        RESULT = SetCursorFirstEntity( zqFrameRoot, "CSV_OutputLineItem", "" );
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityName", zqFrame, "QueryAttribute", "GroupOperationEntityName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrameRoot, "CSV_OutputLineItem", "AttributeName", zqFrame, "QueryAttribute", "GroupOperationAttributeName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrameRoot, "CSV_OutputLineItem", "GroupOperation", zqFrame, "QueryAttribute", "GroupOperation" ) != 0 ) )
                           { 
                              RESULT = SetCursorNextEntity( zqFrameRoot, "CSV_OutputLineItem", "" );
                           } 

                        } 

                        //:IF RESULT < zCURSOR_SET
                        if ( RESULT < zCURSOR_SET )
                        { 
                           //:IssueError( zqFrame,0,0, "Programming Error 1" )
                           IssueError( zqFrame, 0, 0, "Programming Error 1" );
                        } 

                        //:END
                        //:zqFrameRoot.CSV_OutputLineItem.Value = FormattedValue
                        SetAttributeFromString( zqFrameRoot, "CSV_OutputLineItem", "Value", FormattedValue );
                        //:OutputLine = "W"   // Indicate OutputLine is to be formatted and written. We don't check later for a "W", for a not-null.
                        ZeidonStringCopy( OutputLine, 1, 0, "W", 1, 0, 10001 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:OutputLine = OutputLine + QUOTES + FormattedValue + QUOTES + ","
                        ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
                        ZeidonStringConcat( OutputLine, 1, 0, FormattedValue, 1, 0, 10001 );
                        ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
                        ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
                     } 

                     //:   // AddColumnAttribute call should not be necessary here because GroupOperation and Extension Summary are incompatable.
                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:szMappingEntityName = zqFrame.QueryAttribute.MappingEntityName 
                     {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                     StringBuilder sb_szMappingEntityName;
                     if ( szMappingEntityName == null )
                        sb_szMappingEntityName = new StringBuilder( 32 );
                     else
                        sb_szMappingEntityName = new StringBuilder( szMappingEntityName );
                                           GetVariableFromAttribute( sb_szMappingEntityName, mi_lTempInteger_2, 'S', 33, zqFrame, "QueryAttribute", "MappingEntityName", "", 0 );
                     lTempInteger_2 = mi_lTempInteger_2.intValue( );
                     szMappingEntityName = sb_szMappingEntityName.toString( );}
                     //:IF szMappingEntityName = ""
                     if ( ZeidonStringCompare( szMappingEntityName, 1, 0, "", 1, 0, 33 ) == 0 )
                     { 
                        //:FormattedValue = ""
                         {StringBuilder sb_FormattedValue;
                        if ( FormattedValue == null )
                           sb_FormattedValue = new StringBuilder( 32 );
                        else
                           sb_FormattedValue = new StringBuilder( FormattedValue );
                                                ZeidonStringCopy( sb_FormattedValue, 1, 0, "", 1, 0, 10001 );
                        FormattedValue = sb_FormattedValue.toString( );}
                        //:ELSE
                     } 
                     else
                     { 
                        //:nRC = CheckExistenceOfEntity( vResultSet, szMappingEntityName )
                        nRC = CheckExistenceOfEntity( vResultSet, szMappingEntityName );
                        //:IF nRC < zCURSOR_SET
                        if ( nRC < zCURSOR_SET )
                        { 
                           //:FormattedValue = ""
                            {StringBuilder sb_FormattedValue;
                           if ( FormattedValue == null )
                              sb_FormattedValue = new StringBuilder( 32 );
                           else
                              sb_FormattedValue = new StringBuilder( FormattedValue );
                                                      ZeidonStringCopy( sb_FormattedValue, 1, 0, "", 1, 0, 10001 );
                           FormattedValue = sb_FormattedValue.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:// Format the attribute through the Default Context filter, unless value is null.
                           //:nRC = CompareAttributeToString( vResultSet,
                           //:                                szMappingEntityName,
                           //:                                zqFrame.QueryAttribute.AttributeName, "" )
                           {StringBuilder sb_szTempString_4;
                           if ( szTempString_4 == null )
                              sb_szTempString_4 = new StringBuilder( 32 );
                           else
                              sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                                       GetStringFromAttribute( sb_szTempString_4, zqFrame, "QueryAttribute", "AttributeName" );
                           szTempString_4 = sb_szTempString_4.toString( );}
                           nRC = CompareAttributeToString( vResultSet, szMappingEntityName, szTempString_4, "" );
                           //:IF nRC = 0
                           if ( nRC == 0 )
                           { 
                              //:FormattedValue = ""
                               {StringBuilder sb_FormattedValue;
                              if ( FormattedValue == null )
                                 sb_FormattedValue = new StringBuilder( 32 );
                              else
                                 sb_FormattedValue = new StringBuilder( FormattedValue );
                                                            ZeidonStringCopy( sb_FormattedValue, 1, 0, "", 1, 0, 10001 );
                              FormattedValue = sb_FormattedValue.toString( );}
                              //:ElSE
                           } 
                           else
                           { 
                              //:GetStrFromAttrByContext( FormattedValue, 10000,
                              //:                         vResultSet,
                              //:                         szMappingEntityName,
                              //:                         zqFrame.QueryAttribute.AttributeName, "" )  // Use default context.
                              {StringBuilder sb_szTempString_5;
                              if ( szTempString_5 == null )
                                 sb_szTempString_5 = new StringBuilder( 32 );
                              else
                                 sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                                             GetStringFromAttribute( sb_szTempString_5, zqFrame, "QueryAttribute", "AttributeName" );
                              szTempString_5 = sb_szTempString_5.toString( );}
                              {
                               ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( vResultSet );
                               {StringBuilder sb_FormattedValue;
                              if ( FormattedValue == null )
                                 sb_FormattedValue = new StringBuilder( 32 );
                              else
                                 sb_FormattedValue = new StringBuilder( FormattedValue );
                                                             m_ZGLOBAL1_Operation.GetStrFromAttrByContext( sb_FormattedValue, 10000, vResultSet, szMappingEntityName, szTempString_5, "" );
                              FormattedValue = sb_FormattedValue.toString( );}
                               // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                              }
                           } 

                           //:END
                        } 

                        //:END
                     } 

                     //:END
                     //:IF szTypeFlag = "F"
                     if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
                     { 
                        //:SET CURSOR FIRST zqFrameRoot.CSV_OutputLineItem 
                        //:           WHERE zqFrameRoot.CSV_OutputLineItem.EntityName    = zqFrame.QueryAttribute.MappingEntityName
                        //:             AND zqFrameRoot.CSV_OutputLineItem.AttributeName = zqFrame.QueryAttribute.AttributeName 
                        RESULT = SetCursorFirstEntity( zqFrameRoot, "CSV_OutputLineItem", "" );
                        if ( RESULT > zCURSOR_UNCHANGED )
                        { 
                           while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrameRoot, "CSV_OutputLineItem", "EntityName", zqFrame, "QueryAttribute", "MappingEntityName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrameRoot, "CSV_OutputLineItem", "AttributeName", zqFrame, "QueryAttribute", "AttributeName" ) != 0 ) )
                           { 
                              RESULT = SetCursorNextEntity( zqFrameRoot, "CSV_OutputLineItem", "" );
                           } 

                        } 

                        //:zqFrameRoot.CSV_OutputLineItem.Value = FormattedValue
                        SetAttributeFromString( zqFrameRoot, "CSV_OutputLineItem", "Value", FormattedValue );
                        //:OutputLine = "W"   // Indicate OutputLine is to be formatted and written. We don't check later for a "W", for a not-null.
                        ZeidonStringCopy( OutputLine, 1, 0, "W", 1, 0, 10001 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:OutputLine = OutputLine + QUOTES + FormattedValue + QUOTES + ","
                        ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
                        ZeidonStringConcat( OutputLine, 1, 0, FormattedValue, 1, 0, 10001 );
                        ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
                        ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
                        //:AddColumnAttribute( zqFrameRoot, FormattedValue, szMappingEntityName, zqFrame.QueryAttribute.AttributeName )
                        {StringBuilder sb_szTempString_6;
                        if ( szTempString_6 == null )
                           sb_szTempString_6 = new StringBuilder( 32 );
                        else
                           sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                                 GetStringFromAttribute( sb_szTempString_6, zqFrame, "QueryAttribute", "AttributeName" );
                        szTempString_6 = sb_szTempString_6.toString( );}
                        ozqFrame_AddColumnAttribute( zqFrameRoot, FormattedValue, szMappingEntityName, szTempString_6 );
                     } 

                     //:END
                  } 

                  RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
                  //:END
               } 

               //:END
               //:zqFrame.ParentEntity.CurrentOutputLine = OutputLine
               SetAttributeFromString( zqFrame, "ParentEntity", "CurrentOutputLine", OutputLine.toString( ) );
            } 

            //:END
            //:// Process subentities within an entity loop.
            //:IF zqFrame.ChildEntity EXISTS
            lTempInteger_3 = CheckExistenceOfEntity( zqFrame, "ChildEntity" );
            if ( lTempInteger_3 == 0 )
            { 
               //:SetViewToSubobject( zqFrame, "ChildEntity" )
               SetViewToSubobject( zqFrame, "ChildEntity" );
               //:FormatCSV_EntityRecurs( zqFrame,
               //:                        zqFrameRoot,
               //:                        vResultSet,
               //:                        OutputLine,
               //:                        szTypeFlag,
               //:                        FilePointer,
               //:                        HierarchicalLevel,
               //:                        LastHierarchicalLevel,
               //:                        zqFrame.ParentEntity.EntityName,
               //:                        ColumnCount )
               {StringBuilder sb_szTempString_7;
               if ( szTempString_7 == null )
                  sb_szTempString_7 = new StringBuilder( 32 );
               else
                  sb_szTempString_7 = new StringBuilder( szTempString_7 );
                               GetStringFromAttribute( sb_szTempString_7, zqFrame, "ParentEntity", "EntityName" );
               szTempString_7 = sb_szTempString_7.toString( );}
               ozqFrame_FormatCSV_EntityRecurs( zqFrame, zqFrameRoot, vResultSet, OutputLine, szTypeFlag, FilePointer, HierarchicalLevel, LastHierarchicalLevel, szTempString_7, ColumnCount );
               //:ResetViewFromSubobject( zqFrame )
               ResetViewFromSubobject( zqFrame );
            } 

            //:END
            //:nRC = SetCursorNextEntity( vResultSet, szEntityName, "" )
            nRC = SetCursorNextEntity( vResultSet, szEntityName, "" );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Process subentities for 1-to-1 children.
         //:// No mapping is done for 1-to-1 children, as their attributes were listed as ???
         //:IF zqFrame.ChildEntity EXISTS
         lTempInteger_4 = CheckExistenceOfEntity( zqFrame, "ChildEntity" );
         if ( lTempInteger_4 == 0 )
         { 
            //:SetViewToSubobject( zqFrame, "ChildEntity" )
            SetViewToSubobject( zqFrame, "ChildEntity" );
            //:FormatCSV_EntityRecurs( zqFrame,
            //:                        zqFrameRoot,
            //:                        vResultSet,
            //:                        OutputLine,
            //:                        szTypeFlag,
            //:                        FilePointer,
            //:                        HierarchicalLevel,
            //:                        LastHierarchicalLevel,
            //:                        zqFrame.ParentEntity.EntityName,
            //:                        ColumnCount )
            {StringBuilder sb_szTempString_8;
            if ( szTempString_8 == null )
               sb_szTempString_8 = new StringBuilder( 32 );
            else
               sb_szTempString_8 = new StringBuilder( szTempString_8 );
                         GetStringFromAttribute( sb_szTempString_8, zqFrame, "ParentEntity", "EntityName" );
            szTempString_8 = sb_szTempString_8.toString( );}
            ozqFrame_FormatCSV_EntityRecurs( zqFrame, zqFrameRoot, vResultSet, OutputLine, szTypeFlag, FilePointer, HierarchicalLevel, LastHierarchicalLevel, szTempString_8, ColumnCount );
            //:ResetViewFromSubobject( zqFrame )
            ResetViewFromSubobject( zqFrame );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
      //:END
   } 

   //:END
   //:LastHierarchicalLevel = HierarchicalLevel
   LastHierarchicalLevel.setValue( HierarchicalLevel );
   //:HierarchicalLevel = HierarchicalLevel - 1
   HierarchicalLevel.decrement( );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:InitializeFrameForLOD( VIEW zqFrame BASED ON LOD zqFrame,
//:                       VIEW vLOD_Root )

//:   VIEW zqFrameRoot BASED ON LOD zqFrame
public int 
ozqFrame_InitializeFrameForLOD( zVIEW    zqFrame,
                                View     vLOD_Root )
{
   zVIEW    zqFrameRoot = new zVIEW( );
   //:VIEW vLOD_Recurs
   zVIEW    vLOD_Recurs = new zVIEW( );
   //:STRING ( 32 )  RootEntityName
   String   RootEntityName = null;
   //:STRING ( 400 ) szFileName
   String   szFileName = null;
   //:SHORT nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// Format a zqFrame OI with the characteristics of the LOD Frame.

   //:RootEntityName = vLOD_Root.LOD_EntityParent.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_RootEntityName;
   if ( RootEntityName == null )
      sb_RootEntityName = new StringBuilder( 32 );
   else
      sb_RootEntityName = new StringBuilder( RootEntityName );
       GetVariableFromAttribute( sb_RootEntityName, mi_lTempInteger_0, 'S', 33, vLOD_Root, "LOD_EntityParent", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   RootEntityName = sb_RootEntityName.toString( );}

   //:// Clear any current ObjectEntity entries.
   //:FOR EACH zqFrame.ObjectEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ObjectEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.ObjectEntity NONE
      RESULT = DeleteEntity( zqFrame, "ObjectEntity", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "ObjectEntity", "" );
   } 

   //:END

   //:// Create the null ObjectEntity entry.
   //:CREATE ENTITY zqFrame.ObjectEntity
   RESULT = CreateEntity( zqFrame, "ObjectEntity", zPOS_AFTER );

   //:// Create GeneralParameter entries in zqFrame by calling recursive routine with vLOD_Recurs.
   //:SET CURSOR FIRST vLOD_Root.LOD       // Make sure we have position. (After a Reset View, we sometimes don't have cursor position.)
   RESULT = SetCursorFirstEntity( vLOD_Root, "LOD", "" );
   //:CreateViewFromView( vLOD_Recurs, vLOD_Root )
   CreateViewFromView( vLOD_Recurs, vLOD_Root );
   //:NAME VIEW vLOD_Recurs "vLOD_Recurs"
   SetNameForView( vLOD_Recurs, "vLOD_Recurs", null, zLEVEL_TASK );
   //:CreateViewFromView( zqFrameRoot, zqFrame )
   CreateViewFromView( zqFrameRoot, zqFrame );
   //:NAME VIEW zqFrameRoot "zqFrameRoot"
   SetNameForView( zqFrameRoot, "zqFrameRoot", null, zLEVEL_TASK );
   //:InitializeFrameRecurs( zqFrame, zqFrameRoot, vLOD_Recurs, vLOD_Root, "", RootEntityName, 1 )
   ozqFrame_InitializeFrameRecurs( zqFrame, zqFrameRoot, vLOD_Recurs, vLOD_Root, "", RootEntityName, 1 );
   //:DropView( zqFrameRoot )
   DropView( zqFrameRoot );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:RebuildFrameAttr( VIEW zqFrame BASED ON LOD zqFrame,
//:                  VIEW vLOD_Root )

//:   VIEW  vTZZOLODO
public int 
ozqFrame_RebuildFrameAttr( View     zqFrame,
                           View     vLOD_Root )
{
   zVIEW    vTZZOLODO = new zVIEW( );
   //:VIEW  vLOD_Recurs
   zVIEW    vLOD_Recurs = new zVIEW( );
   int      RESULT = 0;


   //:// Set up the QueryFrameAttribute subobject by making sure that every attribute in the LOD 
   //:// has a QueryFrameAttribute entry and that no QueryFrameAttribute exists that isn't in the LOD.
   //:// The QueryFrameAttribute entries must be created in hierarchical order.

   //:// Just in case there are already some QueryFrameAttribute entries (which there shouldn't be) delete them.
   //:FOR EACH vLOD_Root.QueryFrameAttribute
   RESULT = SetCursorFirstEntity( vLOD_Root, "QueryFrameAttribute", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY vLOD_Root.QueryFrameAttribute NONE
      RESULT = DeleteEntity( vLOD_Root, "QueryFrameAttribute", zREPOS_NONE );
      RESULT = SetCursorNextEntity( vLOD_Root, "QueryFrameAttribute", "" );
   } 

   //:END

   //:// Make sure that each Entity/Attribute combination in the LOD has a corresponding QueryFrameAttribute entry.
   //:CreateViewFromView( vLOD_Recurs, vLOD_Root )
   CreateViewFromView( vLOD_Recurs, vLOD_Root );
   //:NAME VIEW vLOD_Recurs "vLOD_Recurs"
   SetNameForView( vLOD_Recurs, "vLOD_Recurs", null, zLEVEL_TASK );
   //:RebuildFrameAttrRecur( zqFrame, vLOD_Root, vLOD_Recurs ) 
   ozqFrame_RebuildFrameAttrRecur( zqFrame, vLOD_Root, vLOD_Recurs );
   //:DropView( vLOD_Recurs )
   DropView( vLOD_Recurs );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:RebuildFrameAttrRecur( VIEW zqFrame BASED ON LOD zqFrame,
//:                       VIEW vLOD_Root,
//:                       VIEW vLOD_Recurs )

//:   VIEW  vTZZOLODO
public int 
ozqFrame_RebuildFrameAttrRecur( View     zqFrame,
                                View     vLOD_Root,
                                View     vLOD_Recurs )
{
   zVIEW    vTZZOLODO = new zVIEW( );
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 32 ) szAttributeName
   String   szAttributeName = null;
   //:STRING ( 90 ) szPrompt
   String   szPrompt = null;
   //:INTEGER nRC
   int      nRC = 0;
   //:INTEGER Length
   int      Length = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Create QueryFrameAttribute entries from the LOD_EntityParent subobject structure.
   //:// Note that the vLOD_Root view is pointing to the last QueryFrameAttribute for the Entity
   //:// just processed. 

   //:FOR EACH vLOD_Recurs.LOD_EntityParent
   RESULT = SetCursorFirstEntity( vLOD_Recurs, "LOD_EntityParent", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:FOR EACH vLOD_Recurs.LOD_AttributeRec
      RESULT = SetCursorFirstEntity( vLOD_Recurs, "LOD_AttributeRec", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY vLOD_Root.QueryFrameAttribute
         RESULT = CreateEntity( vLOD_Root, "QueryFrameAttribute", zPOS_AFTER );
         //:szEntityName    = vLOD_Recurs.LOD_EntityParent.Name
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szEntityName;
         if ( szEntityName == null )
            sb_szEntityName = new StringBuilder( 32 );
         else
            sb_szEntityName = new StringBuilder( szEntityName );
                   GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, vLOD_Recurs, "LOD_EntityParent", "Name", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szEntityName = sb_szEntityName.toString( );}
         //:szAttributeName = vLOD_Recurs.ER_AttributeRec.Name
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                   GetVariableFromAttribute( sb_szAttributeName, mi_lTempInteger_1, 'S', 33, vLOD_Recurs, "ER_AttributeRec", "Name", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szAttributeName = sb_szAttributeName.toString( );}
         //:vLOD_Root.QueryFrameAttribute.EntityName    = szEntityName
         SetAttributeFromString( vLOD_Root, "QueryFrameAttribute", "EntityName", szEntityName );
         //:vLOD_Root.QueryFrameAttribute.AttributeName = szAttributeName
         SetAttributeFromString( vLOD_Root, "QueryFrameAttribute", "AttributeName", szAttributeName );
         //:szPrompt = szEntityName + "." + szAttributeName
          {StringBuilder sb_szPrompt;
         if ( szPrompt == null )
            sb_szPrompt = new StringBuilder( 32 );
         else
            sb_szPrompt = new StringBuilder( szPrompt );
                  ZeidonStringCopy( sb_szPrompt, 1, 0, szEntityName, 1, 0, 91 );
         szPrompt = sb_szPrompt.toString( );}
          {StringBuilder sb_szPrompt;
         if ( szPrompt == null )
            sb_szPrompt = new StringBuilder( 32 );
         else
            sb_szPrompt = new StringBuilder( szPrompt );
                  ZeidonStringConcat( sb_szPrompt, 1, 0, ".", 1, 0, 91 );
         szPrompt = sb_szPrompt.toString( );}
          {StringBuilder sb_szPrompt;
         if ( szPrompt == null )
            sb_szPrompt = new StringBuilder( 32 );
         else
            sb_szPrompt = new StringBuilder( szPrompt );
                  ZeidonStringConcat( sb_szPrompt, 1, 0, szAttributeName, 1, 0, 91 );
         szPrompt = sb_szPrompt.toString( );}
         //:Length = zstrlen( szPrompt )
         Length = zstrlen( szPrompt );
         //:IF Length > 50 
         if ( Length > 50 )
         { 
            //:// Since QueryFrameAttribute.Prompt is only 50 characters, us Attribute Name is the combined name is to large.
            //:vLOD_Root.QueryFrameAttribute.Prompt = szAttributeName
            SetAttributeFromString( vLOD_Root, "QueryFrameAttribute", "Prompt", szAttributeName );
            //:ELSE
         } 
         else
         { 
            //:vLOD_Root.QueryFrameAttribute.Prompt = szPrompt
            SetAttributeFromString( vLOD_Root, "QueryFrameAttribute", "Prompt", szPrompt );
         } 

         //:END
         //:IF szAttributeName = "ID"
         if ( ZeidonStringCompare( szAttributeName, 1, 0, "ID", 1, 0, 33 ) == 0 )
         { 
            //:vLOD_Root.QueryFrameAttribute.Title  = szPrompt
            SetAttributeFromString( vLOD_Root, "QueryFrameAttribute", "Title", szPrompt );
            //:ELSE
         } 
         else
         { 
            //:vLOD_Root.QueryFrameAttribute.Title  = szAttributeName
            SetAttributeFromString( vLOD_Root, "QueryFrameAttribute", "Title", szAttributeName );
         } 

         RESULT = SetCursorNextEntity( vLOD_Recurs, "LOD_AttributeRec", "" );
         //:END
      } 

      //:END

      //:// Process LOD_EntityChild entries recursively.
      //:IF vLOD_Recurs.LOD_EntityChild EXISTS
      lTempInteger_2 = CheckExistenceOfEntity( vLOD_Recurs, "LOD_EntityChild" );
      if ( lTempInteger_2 == 0 )
      { 
         //:SetViewToSubobject( vLOD_Recurs, "LOD_EntityChild" )
         SetViewToSubobject( vLOD_Recurs, "LOD_EntityChild" );
         //:RebuildFrameAttrRecur( zqFrame, vLOD_Root, vLOD_Recurs )
         ozqFrame_RebuildFrameAttrRecur( zqFrame, vLOD_Root, vLOD_Recurs );
         //:ResetViewFromSubobject( vLOD_Recurs )
         ResetViewFromSubobject( vLOD_Recurs );
      } 

      RESULT = SetCursorNextEntity( vLOD_Recurs, "LOD_EntityParent", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:InitializeFrameRecurs( VIEW zqFrame     BASED ON LOD zqFrame,
//:                       VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                       VIEW vLOD_Recurs,
//:                       VIEW vLOD_Root,
//:                       STRING ( 32 ) ParentEntityName,
//:                       STRING ( 32 ) RootEntityName,
//:                       INTEGER       PreviousHierarchicalLevel )

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_InitializeFrameRecurs( View     zqFrame,
                                View     zqFrameRoot,
                                View     vLOD_Recurs,
                                View     vLOD_Root,
                                String   ParentEntityName,
                                String   RootEntityName,
                                int      PreviousHierarchicalLevel )
{
   zVIEW    zqFrame2 = new zVIEW( );
   //:VIEW zqFrame3 BASED ON LOD zqFrame
   zVIEW    zqFrame3 = new zVIEW( );
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 32 ) szAttributeName
   String   szAttributeName = null;
   //:STRING ( 50 ) szDescription
   String   szDescription = null;
   //:STRING ( 1 )  szRootQualificationFlag
   String   szRootQualificationFlag = null;
   //:STRING ( 90 ) szEntityAttributeDisplayPrefix
   String   szEntityAttributeDisplayPrefix = null;
   //:INTEGER       CardMax
   int      CardMax = 0;
   //:INTEGER       CurrentHierarchicalLevel
   int      CurrentHierarchicalLevel = 0;
   //:INTEGER       Count
   int      Count = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_5 = null;
   int      lTempInteger_7 = 0;


   //:CurrentHierarchicalLevel = PreviousHierarchicalLevel + 1
   CurrentHierarchicalLevel = PreviousHierarchicalLevel + 1;
   //:Count = PreviousHierarchicalLevel - 1
   Count = PreviousHierarchicalLevel - 1;
   //:szEntityAttributeDisplayPrefix = ""
    {StringBuilder sb_szEntityAttributeDisplayPrefix;
   if ( szEntityAttributeDisplayPrefix == null )
      sb_szEntityAttributeDisplayPrefix = new StringBuilder( 32 );
   else
      sb_szEntityAttributeDisplayPrefix = new StringBuilder( szEntityAttributeDisplayPrefix );
      ZeidonStringCopy( sb_szEntityAttributeDisplayPrefix, 1, 0, "", 1, 0, 91 );
   szEntityAttributeDisplayPrefix = sb_szEntityAttributeDisplayPrefix.toString( );}
   //:LOOP WHILE Count > 0
   while ( Count > 0 )
   { 
      //:szEntityAttributeDisplayPrefix = szEntityAttributeDisplayPrefix + "..."
       {StringBuilder sb_szEntityAttributeDisplayPrefix;
      if ( szEntityAttributeDisplayPrefix == null )
         sb_szEntityAttributeDisplayPrefix = new StringBuilder( 32 );
      else
         sb_szEntityAttributeDisplayPrefix = new StringBuilder( szEntityAttributeDisplayPrefix );
            ZeidonStringConcat( sb_szEntityAttributeDisplayPrefix, 1, 0, "...", 1, 0, 91 );
      szEntityAttributeDisplayPrefix = sb_szEntityAttributeDisplayPrefix.toString( );}
      //:Count = Count - 1
      Count = Count - 1;
   } 

   //:END

   //:// Add the General Parameter values for the entity passed. This is recursive and calls itself
   //:// again for subentities.
   //:CreateViewFromView( zqFrame2, zqFrameRoot )
   CreateViewFromView( zqFrame2, zqFrameRoot );
   //:FOR EACH vLOD_Recurs.LOD_EntityParent
   RESULT = SetCursorFirstEntity( vLOD_Recurs, "LOD_EntityParent", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szEntityName = vLOD_Recurs.LOD_EntityParent.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, vLOD_Recurs, "LOD_EntityParent", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szEntityName = sb_szEntityName.toString( );}

      //:// Create ObjectEntity entry for setting RootQualificationFlag.
      //:CreateViewFromView( zqFrame3, zqFrameRoot )
      CreateViewFromView( zqFrame3, zqFrameRoot );
      //:SET CURSOR LAST zqFrame2.ObjectEntity
      RESULT = SetCursorLastEntity( zqFrame2, "ObjectEntity", "" );
      //:CREATE ENTITY zqFrame2.ObjectEntity
      RESULT = CreateEntity( zqFrame2, "ObjectEntity", zPOS_AFTER );
      //:zqFrame2.ObjectEntity.EntityName = szEntityName
      SetAttributeFromString( zqFrame2, "ObjectEntity", "EntityName", szEntityName );
      //:IF vLOD_Recurs.ER_RelLinkRec EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( vLOD_Recurs, "ER_RelLinkRec" );
      if ( lTempInteger_1 == 0 )
      { 
         //:IF vLOD_Recurs.ER_RelLinkRec.CardMax = "1"
         if ( CompareAttributeToString( vLOD_Recurs, "ER_RelLinkRec", "CardMax", "1" ) == 0 )
         { 
            //:CardMax = 1
            CardMax = 1;
            //:ELSE
         } 
         else
         { 
            //:CardMax = 99
            CardMax = 99;
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:CardMax = 0
         CardMax = 0;
      } 

      //:END
      //:DropView( zqFrame3 )
      DropView( zqFrame3 );
      //:zqFrame2.ObjectEntity.MaxCardinality = CardMax
      SetAttributeFromInteger( zqFrame2, "ObjectEntity", "MaxCardinality", CardMax );
      //:szRootQualificationFlag = ""
       {StringBuilder sb_szRootQualificationFlag;
      if ( szRootQualificationFlag == null )
         sb_szRootQualificationFlag = new StringBuilder( 32 );
      else
         sb_szRootQualificationFlag = new StringBuilder( szRootQualificationFlag );
            ZeidonStringCopy( sb_szRootQualificationFlag, 1, 0, "", 1, 0, 2 );
      szRootQualificationFlag = sb_szRootQualificationFlag.toString( );}
      //:IF RootEntityName = szEntityName
      if ( ZeidonStringCompare( RootEntityName, 1, 0, szEntityName, 1, 0, 33 ) == 0 )
      { 
         //:szRootQualificationFlag = "Y"
          {StringBuilder sb_szRootQualificationFlag;
         if ( szRootQualificationFlag == null )
            sb_szRootQualificationFlag = new StringBuilder( 32 );
         else
            sb_szRootQualificationFlag = new StringBuilder( szRootQualificationFlag );
                  ZeidonStringCopy( sb_szRootQualificationFlag, 1, 0, "Y", 1, 0, 2 );
         szRootQualificationFlag = sb_szRootQualificationFlag.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// This is not the root entity so we will get the szRootQualificationFlag value from its parent,
         //:// if this entity has max cardinality of 1.
         //:IF CardMax = 1
         if ( CardMax == 1 )
         { 
            //:CreateViewFromView( zqFrame3, zqFrame )
            CreateViewFromView( zqFrame3, zqFrame );
            //:SET CURSOR FIRST zqFrame3.ObjectEntity
            //:           WHERE zqFrame3.ObjectEntity.EntityName = ParentEntityName
            RESULT = SetCursorFirstEntityByString( zqFrame3, "ObjectEntity", "EntityName", ParentEntityName, "" );
            //:szRootQualificationFlag = zqFrame3.ObjectEntity.RootQualificationFlag
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szRootQualificationFlag;
            if ( szRootQualificationFlag == null )
               sb_szRootQualificationFlag = new StringBuilder( 32 );
            else
               sb_szRootQualificationFlag = new StringBuilder( szRootQualificationFlag );
                         GetVariableFromAttribute( sb_szRootQualificationFlag, mi_lTempInteger_2, 'S', 2, zqFrame3, "ObjectEntity", "RootQualificationFlag", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szRootQualificationFlag = sb_szRootQualificationFlag.toString( );}
            //:DropView( zqFrame3 )
            DropView( zqFrame3 );
         } 

         //:END
      } 

      //:END
      //:zqFrame2.ObjectEntity.RootQualificationFlag = szRootQualificationFlag
      SetAttributeFromString( zqFrame2, "ObjectEntity", "RootQualificationFlag", szRootQualificationFlag );

      //:// Create GeneralParameter entries for each attribute within entity.
      //:FOR EACH vLOD_Recurs.LOD_AttributeRec
      RESULT = SetCursorFirstEntity( vLOD_Recurs, "LOD_AttributeRec", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY zqFrame.GeneralParameter
         RESULT = CreateEntity( zqFrame, "GeneralParameter", zPOS_AFTER );
         //:SET CURSOR FIRST vLOD_Root.QueryFrameAttribute
         //:           WHERE vLOD_Root.QueryFrameAttribute.EntityName    = vLOD_Recurs.LOD_EntityParent.Name
         //:             AND vLOD_Root.QueryFrameAttribute.AttributeName = vLOD_Recurs.ER_AttributeRec.Name
         RESULT = SetCursorFirstEntity( vLOD_Root, "QueryFrameAttribute", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( vLOD_Root, "QueryFrameAttribute", "EntityName", vLOD_Recurs, "LOD_EntityParent", "Name" ) != 0 ||
                    CompareAttributeToAttribute( vLOD_Root, "QueryFrameAttribute", "AttributeName", vLOD_Recurs, "ER_AttributeRec", "Name" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( vLOD_Root, "QueryFrameAttribute", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET 
         if ( RESULT >= zCURSOR_SET )
         { 
            //:zqFrame.GeneralParameter.AttributeName         = vLOD_Root.QueryFrameAttribute.AttributeName
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "AttributeName", vLOD_Root, "QueryFrameAttribute", "AttributeName" );
            //:zqFrame.GeneralParameter.EntityName            = vLOD_Root.QueryFrameAttribute.EntityName
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "EntityName", vLOD_Root, "QueryFrameAttribute", "EntityName" );
            //:zqFrame.GeneralParameter.ExternalDescription   = vLOD_Root.QueryFrameAttribute.ExternalDescription
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "ExternalDescription", vLOD_Root, "QueryFrameAttribute", "ExternalDescription" );
            //:zqFrame.GeneralParameter.DataType              = vLOD_Recurs.DomainRec.DataType
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "DataType", vLOD_Recurs, "DomainRec", "DataType" );
            //:zqFrame.GeneralParameter.Prompt                = szEntityAttributeDisplayPrefix +
            //:                                                 zqFrame.GeneralParameter.EntityName + "." +
            //:                                                 zqFrame.GeneralParameter.AttributeName
            {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_3, 'S', 33, zqFrame, "GeneralParameter", "EntityName", "", 0 );
            lTempInteger_3 = mi_lTempInteger_3.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringCopy( sb_szTempString_0, 1, 0, szEntityAttributeDisplayPrefix, 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, ".", 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
            {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
            StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_4, 'S', 33, zqFrame, "GeneralParameter", "AttributeName", "", 0 );
            lTempInteger_4 = mi_lTempInteger_4.intValue( );
            szTempString_2 = sb_szTempString_2.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_2, 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( zqFrame, "GeneralParameter", "Prompt", szTempString_0 );
            //:zqFrame.GeneralParameter.Title                 = vLOD_Root.QueryFrameAttribute.Title
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "Title", vLOD_Root, "QueryFrameAttribute", "Title" );
            //:zqFrame.GeneralParameter.RootQualificationFlag = szRootQualificationFlag
            SetAttributeFromString( zqFrame, "GeneralParameter", "RootQualificationFlag", szRootQualificationFlag );
            //:zqFrame.GeneralParameter.MaxCardinality        = CardMax
            SetAttributeFromInteger( zqFrame, "GeneralParameter", "MaxCardinality", CardMax );
            //: zqFrame.GeneralParameter.ListDisplayFlag="Y"
            SetAttributeFromString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" );
            //:ELSE
         } 
         else
         { 
            //:zqFrame.GeneralParameter.AttributeName         = vLOD_Recurs.ER_AttributeRec.Name
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "AttributeName", vLOD_Recurs, "ER_AttributeRec", "Name" );
            //:zqFrame.GeneralParameter.EntityName            = vLOD_Recurs.LOD_EntityParent.Name
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "EntityName", vLOD_Recurs, "LOD_EntityParent", "Name" );
            //:szDescription                                  = vLOD_Recurs.ER_AttributeRec.Desc
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_szDescription;
            if ( szDescription == null )
               sb_szDescription = new StringBuilder( 32 );
            else
               sb_szDescription = new StringBuilder( szDescription );
                         GetVariableFromAttribute( sb_szDescription, mi_lTempInteger_5, 'S', 51, vLOD_Recurs, "ER_AttributeRec", "Desc", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            szDescription = sb_szDescription.toString( );}
            //:zqFrame.GeneralParameter.ExternalDescription   = szDescription     // Limit text to 50 characters.
            SetAttributeFromString( zqFrame, "GeneralParameter", "ExternalDescription", szDescription );
            //:zqFrame.GeneralParameter.DataType              = vLOD_Recurs.DomainRec.DataType
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "DataType", vLOD_Recurs, "DomainRec", "DataType" );
            //:zqFrame.GeneralParameter.Prompt                = szEntityAttributeDisplayPrefix +
            //:                                                 zqFrame.GeneralParameter.EntityName + "." +
            //:                                                 zqFrame.GeneralParameter.AttributeName
            {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
            StringBuilder sb_szTempString_4;
            if ( szTempString_4 == null )
               sb_szTempString_4 = new StringBuilder( 32 );
            else
               sb_szTempString_4 = new StringBuilder( szTempString_4 );
                         GetVariableFromAttribute( sb_szTempString_4, mi_lTempInteger_6, 'S', 33, zqFrame, "GeneralParameter", "EntityName", "", 0 );
            lTempInteger_6 = mi_lTempInteger_6.intValue( );
            szTempString_4 = sb_szTempString_4.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringCopy( sb_szTempString_3, 1, 0, szEntityAttributeDisplayPrefix, 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, szTempString_4, 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, ".", 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
            {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
            StringBuilder sb_szTempString_5;
            if ( szTempString_5 == null )
               sb_szTempString_5 = new StringBuilder( 32 );
            else
               sb_szTempString_5 = new StringBuilder( szTempString_5 );
                         GetVariableFromAttribute( sb_szTempString_5, mi_lTempInteger_7, 'S', 33, zqFrame, "GeneralParameter", "AttributeName", "", 0 );
            lTempInteger_7 = mi_lTempInteger_7.intValue( );
            szTempString_5 = sb_szTempString_5.toString( );}
             {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                        ZeidonStringConcat( sb_szTempString_3, 1, 0, szTempString_5, 1, 0, 255 );
            szTempString_3 = sb_szTempString_3.toString( );}
            SetAttributeFromString( zqFrame, "GeneralParameter", "Prompt", szTempString_3 );
            //:zqFrame.GeneralParameter.Title                 = ""
            SetAttributeFromString( zqFrame, "GeneralParameter", "Title", "" );
            //:zqFrame.GeneralParameter.RootQualificationFlag = szRootQualificationFlag
            SetAttributeFromString( zqFrame, "GeneralParameter", "RootQualificationFlag", szRootQualificationFlag );
            //:zqFrame.GeneralParameter.MaxCardinality        = CardMax
            SetAttributeFromInteger( zqFrame, "GeneralParameter", "MaxCardinality", CardMax );
            //:zqFrame.GeneralParameter.ListDisplayFlag       = "Y"
            SetAttributeFromString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" );
         } 

         //:END

         //:// We will set the derived flag for three conditions, 
         //:// 1) the attribute is derived, 
         //:// 2) the entity is derived,
         //:// 3) the entity is a work entity. All of these will trigger post-activate searches instead of SQL searches.
         //:IF vLOD_Recurs.LOD_AttributeRec.Work = "Y" OR 
         //:   vLOD_Recurs.LOD_EntityParent.Derived = "Y" OR 
         //:   vLOD_Recurs.LOD_EntityParent.Work = "Y"
         if ( CompareAttributeToString( vLOD_Recurs, "LOD_AttributeRec", "Work", "Y" ) == 0 || CompareAttributeToString( vLOD_Recurs, "LOD_EntityParent", "Derived", "Y" ) == 0 ||
              CompareAttributeToString( vLOD_Recurs, "LOD_EntityParent", "Work", "Y" ) == 0 )
         { 

            //:zqFrame.GeneralParameter.DerivedAttributeFlag = "Y"
            SetAttributeFromString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "Y" );
         } 

         RESULT = SetCursorNextEntity( vLOD_Recurs, "LOD_AttributeRec", "" );
         //:END
      } 

      //:END

      //:// Process each subentity.
      //:SetViewToSubobject( vLOD_Recurs, "LOD_EntityChild" )
      SetViewToSubobject( vLOD_Recurs, "LOD_EntityChild" );
      //:InitializeFrameRecurs( zqFrame, zqFrameRoot, vLOD_Recurs, vLOD_Root, szEntityName, RootEntityName, CurrentHierarchicalLevel )
      ozqFrame_InitializeFrameRecurs( zqFrame, zqFrameRoot, vLOD_Recurs, vLOD_Root, szEntityName, RootEntityName, CurrentHierarchicalLevel );
      //:ResetViewFromSubobject( vLOD_Recurs )
      ResetViewFromSubobject( vLOD_Recurs );
      RESULT = SetCursorNextEntity( vLOD_Recurs, "LOD_EntityParent", "" );
   } 

   //:END
   //:DropView( zqFrame2 )
   DropView( zqFrame2 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:RebuildObjEntRecurs( VIEW zqFrame     BASED ON LOD zqFrame,
//:                     VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                     VIEW vLOD_Recurs,
//:                     VIEW vLOD_Root,
//:                     STRING ( 32 ) ParentEntityName,
//:                     STRING ( 32 ) RootEntityName )

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_RebuildObjEntRecurs( View     zqFrame,
                              View     zqFrameRoot,
                              View     vLOD_Recurs,
                              View     vLOD_Root,
                              String   ParentEntityName,
                              String   RootEntityName )
{
   zVIEW    zqFrame2 = new zVIEW( );
   //:VIEW zqFrame3 BASED ON LOD zqFrame
   zVIEW    zqFrame3 = new zVIEW( );
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 32 ) szAttributeName
   String   szAttributeName = null;
   //:STRING ( 1 )  szRootQualificationFlag
   String   szRootQualificationFlag = null;
   //:INTEGER       CardMax
   int      CardMax = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// This is the same as InitializeFrameRecurs above except that it only builds the ObjectEntity entries
   //:// and leaves the GeneralParameter entries as is.
   //:CreateViewFromView( zqFrame2, zqFrameRoot )
   CreateViewFromView( zqFrame2, zqFrameRoot );
   //:FOR EACH vLOD_Recurs.LOD_EntityParent
   RESULT = SetCursorFirstEntity( vLOD_Recurs, "LOD_EntityParent", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szEntityName = vLOD_Recurs.LOD_EntityParent.Name
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, vLOD_Recurs, "LOD_EntityParent", "Name", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szEntityName = sb_szEntityName.toString( );}

      //:// Create ObjectEntity entry for setting RootQualificationFlag.
      //:CreateViewFromView( zqFrame3, zqFrameRoot )
      CreateViewFromView( zqFrame3, zqFrameRoot );
      //:SET CURSOR LAST zqFrame2.ObjectEntity
      RESULT = SetCursorLastEntity( zqFrame2, "ObjectEntity", "" );
      //:CREATE ENTITY zqFrame2.ObjectEntity
      RESULT = CreateEntity( zqFrame2, "ObjectEntity", zPOS_AFTER );
      //:zqFrame2.ObjectEntity.EntityName = szEntityName
      SetAttributeFromString( zqFrame2, "ObjectEntity", "EntityName", szEntityName );
      //:IF vLOD_Recurs.ER_RelLinkRec EXISTS
      lTempInteger_1 = CheckExistenceOfEntity( vLOD_Recurs, "ER_RelLinkRec" );
      if ( lTempInteger_1 == 0 )
      { 
         //:IF vLOD_Recurs.ER_RelLinkRec.CardMax = "1"
         if ( CompareAttributeToString( vLOD_Recurs, "ER_RelLinkRec", "CardMax", "1" ) == 0 )
         { 
            //:CardMax = 1
            CardMax = 1;
            //:ELSE
         } 
         else
         { 
            //:CardMax = 99
            CardMax = 99;
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:CardMax = 0
         CardMax = 0;
      } 

      //:END
      //:DropView( zqFrame3 )
      DropView( zqFrame3 );
      //:zqFrame2.ObjectEntity.MaxCardinality = CardMax
      SetAttributeFromInteger( zqFrame2, "ObjectEntity", "MaxCardinality", CardMax );
      //:szRootQualificationFlag = ""
       {StringBuilder sb_szRootQualificationFlag;
      if ( szRootQualificationFlag == null )
         sb_szRootQualificationFlag = new StringBuilder( 32 );
      else
         sb_szRootQualificationFlag = new StringBuilder( szRootQualificationFlag );
            ZeidonStringCopy( sb_szRootQualificationFlag, 1, 0, "", 1, 0, 2 );
      szRootQualificationFlag = sb_szRootQualificationFlag.toString( );}
      //:IF RootEntityName = szEntityName
      if ( ZeidonStringCompare( RootEntityName, 1, 0, szEntityName, 1, 0, 33 ) == 0 )
      { 
         //:szRootQualificationFlag = "Y"
          {StringBuilder sb_szRootQualificationFlag;
         if ( szRootQualificationFlag == null )
            sb_szRootQualificationFlag = new StringBuilder( 32 );
         else
            sb_szRootQualificationFlag = new StringBuilder( szRootQualificationFlag );
                  ZeidonStringCopy( sb_szRootQualificationFlag, 1, 0, "Y", 1, 0, 2 );
         szRootQualificationFlag = sb_szRootQualificationFlag.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// This is not the root entity so we will get the szRootQualificationFlag value from its parent,
         //:// if this entity has max cardinality of 1.
         //:IF CardMax = 1
         if ( CardMax == 1 )
         { 
            //:CreateViewFromView( zqFrame3, zqFrameRoot )
            CreateViewFromView( zqFrame3, zqFrameRoot );
            //:NAME VIEW zqFrame3 "zqFrame3"
            SetNameForView( zqFrame3, "zqFrame3", null, zLEVEL_TASK );
            //:SET CURSOR FIRST zqFrame3.ObjectEntity
            //:           WHERE zqFrame3.ObjectEntity.EntityName = ParentEntityName
            RESULT = SetCursorFirstEntityByString( zqFrame3, "ObjectEntity", "EntityName", ParentEntityName, "" );
            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:szRootQualificationFlag = zqFrame3.ObjectEntity.RootQualificationFlag
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szRootQualificationFlag;
               if ( szRootQualificationFlag == null )
                  sb_szRootQualificationFlag = new StringBuilder( 32 );
               else
                  sb_szRootQualificationFlag = new StringBuilder( szRootQualificationFlag );
                               GetVariableFromAttribute( sb_szRootQualificationFlag, mi_lTempInteger_2, 'S', 2, zqFrame3, "ObjectEntity", "RootQualificationFlag", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szRootQualificationFlag = sb_szRootQualificationFlag.toString( );}
            } 

            //:END
            //:DropView( zqFrame3 )
            DropView( zqFrame3 );
         } 

         //:END
      } 

      //:END
      //:zqFrame2.ObjectEntity.RootQualificationFlag = szRootQualificationFlag
      SetAttributeFromString( zqFrame2, "ObjectEntity", "RootQualificationFlag", szRootQualificationFlag );

      //:// Process each subentity.
      //:SetViewToSubobject( vLOD_Recurs, "LOD_EntityChild" )
      SetViewToSubobject( vLOD_Recurs, "LOD_EntityChild" );
      //:RebuildObjEntRecurs( zqFrame, zqFrameRoot, vLOD_Recurs, vLOD_Root, szEntityName, RootEntityName )
      ozqFrame_RebuildObjEntRecurs( zqFrame, zqFrameRoot, vLOD_Recurs, vLOD_Root, szEntityName, RootEntityName );
      //:ResetViewFromSubobject( vLOD_Recurs )
      ResetViewFromSubobject( vLOD_Recurs );
      RESULT = SetCursorNextEntity( vLOD_Recurs, "LOD_EntityParent", "" );
   } 

   //:END
   //:DropView( zqFrame2 )
   DropView( zqFrame2 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatDisplay( VIEW zqFrame BASED ON LOD zqFrame,
//:               VIEW vLOD )

//:   VIEW zqFrameRoot  BASED ON LOD zqFrame
public int 
ozqFrame_FormatDisplay( View     zqFrame,
                        View     vLOD )
{
   zVIEW    zqFrameRoot = new zVIEW( );
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 1 )  szDisplayOrderFlag
   String   szDisplayOrderFlag = null;
   //:INTEGER       Count
   int      Count = 0;
   int      RESULT = 0;


   //:// Create the ParentEntity subobject for formatting the CSV and Report data efficiently.
   //:// We could have used a combination of the GeneralParameter subobject and vLOD, but it
   //:// wouldn't be as efficient.

   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.DisplayOrder != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "DisplayOrder", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szDisplayOrderFlag = "Y"
       {StringBuilder sb_szDisplayOrderFlag;
      if ( szDisplayOrderFlag == null )
         sb_szDisplayOrderFlag = new StringBuilder( 32 );
      else
         sb_szDisplayOrderFlag = new StringBuilder( szDisplayOrderFlag );
            ZeidonStringCopy( sb_szDisplayOrderFlag, 1, 0, "Y", 1, 0, 2 );
      szDisplayOrderFlag = sb_szDisplayOrderFlag.toString( );}
      //:Count = 0
      Count = 0;
      //:FOR EACH zqFrame.GeneralParameter 
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:Count = Count + 1
         Count = Count + 1;
         //:zqFrame.GeneralParameter.OriginalOrder = Count
         SetAttributeFromInteger( zqFrame, "GeneralParameter", "OriginalOrder", Count );
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

      //:END
      //:OrderEntityForView( zqFrame, "GeneralParameter", "DisplayOrder A" )
      OrderEntityForView( zqFrame, "GeneralParameter", "DisplayOrder A" );
      //:ELSE
   } 
   else
   { 
      //:szDisplayOrderFlag = ""
       {StringBuilder sb_szDisplayOrderFlag;
      if ( szDisplayOrderFlag == null )
         sb_szDisplayOrderFlag = new StringBuilder( 32 );
      else
         sb_szDisplayOrderFlag = new StringBuilder( szDisplayOrderFlag );
            ZeidonStringCopy( sb_szDisplayOrderFlag, 1, 0, "", 1, 0, 2 );
      szDisplayOrderFlag = sb_szDisplayOrderFlag.toString( );}
   } 

   //:END

   //:// Create entity entries recursively, starting with root entity.
   //:CreateViewFromView( zqFrameRoot, zqFrame )
   CreateViewFromView( zqFrameRoot, zqFrame );
   //:NAME VIEW zqFrame "zqFrameRoot"
   SetNameForView( zqFrame, "zqFrameRoot", null, zLEVEL_TASK );
   //:FOR EACH zqFrame.ParentEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.ParentEntity NONE
      RESULT = DeleteEntity( zqFrame, "ParentEntity", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.ParentEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   //:SET CURSOR FIRST vLOD.LOD   // Make sure we have cursor position, as return from subobject may lose it. 
   RESULT = SetCursorFirstEntity( vLOD, "LOD", "" );
   //:FormatDisplayRecur( zqFrame, zqFrameRoot, vLOD )
   ozqFrame_FormatDisplayRecur( zqFrame, zqFrameRoot, vLOD );
   //:SET CURSOR FIRST zqFrame.zqFrame     // Make sure we have good position on top entity. 
   RESULT = SetCursorFirstEntity( zqFrame, "zqFrame", "" );
   //:SET CURSOR FIRST zqFrameRoot.zqFrame // Make sure we have good position on top entity. 
   RESULT = SetCursorFirstEntity( zqFrameRoot, "zqFrame", "" );
   //:DropView( zqFrameRoot )
   DropView( zqFrameRoot );

   //:// Reset order, if necessary.
   //:IF szDisplayOrderFlag = "Y"
   if ( ZeidonStringCompare( szDisplayOrderFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:OrderEntityForView( zqFrame, "GeneralParameter", "OriginalOrder A" )
      OrderEntityForView( zqFrame, "GeneralParameter", "OriginalOrder A" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatDisplayRecur( VIEW zqFrame     BASED ON LOD zqFrame,
//:                    VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                    VIEW vLOD )

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_FormatDisplayRecur( View     zqFrame,
                             View     zqFrameRoot,
                             View     vLOD )
{
   zVIEW    zqFrame2 = new zVIEW( );
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 5 )  szGroupOper
   String   szGroupOper = null;
   //:STRING ( 1 )  RegularMappingFlag
   String   RegularMappingFlag = null;
   //:STRING ( 1 )  DataType
   String   DataType = null;
   //:STRING ( 2 )  szCardMax
   String   szCardMax = null;
   //:STRING ( 1 )  szFoundFlag
   String   szFoundFlag = null;
   //:INTEGER       Length
   int      Length = 0;
   //:SHORT         nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:// Create the ParentEntity subobject for formatting the CSV and Report data efficiently.
   //:// We could have used a combination of the GeneralParameter subobject and vLOD, but it
   //:// wouldn't be as efficient.
   //:// We also need to put attributes with the correct Entity. Normally this is the Entity for
   //:// which they are defined in the LOD. However, there are four conditions when they will be
   //:// put with a different Entity.
   //:// 1. This attribute is defined as a Subgroup Search Type (MAX, AVG, etc.), ON the ROOT entity.
   //://    In this case, no Attribute is created for a ParentEntity subobject. These are handled
   //://    separately as totals.
   //:// 2. This attribute is defined as a Subgroup Search Type (MAX, AVG, etc.), WITHOUT a Scoping
   //://    Entity. In this case the Attribute is put on its parent Entity.
   //:// 3. This attribute is defined as a Subgroup Search Type (MAX, AVG, etc.), WITH a Scoping
   //://    Entity. In this case the Attribute is put on the Scoping Entity.
   //:// 4. The Entity holding the Attribute has max cardinality of 1 with its parent. In this
   //://    case the Attribute is put on the first parent with max cardinality > 1.

   //:// **** NOTE: We're currently NOT supporting group operation like SUM and AVG on the web interface. (DonC 3/13/2019)

   //:// Create ParentEntity values.
   //:CREATE ENTITY zqFrame.ParentEntity 
   RESULT = CreateEntity( zqFrame, "ParentEntity", zPOS_AFTER );
   //:szEntityName = vLOD.LOD_EntityParent.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, vLOD, "LOD_EntityParent", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //://TraceLineS( "@@@@@@@ Entity: ", szEntityName )
   //:zqFrame.ParentEntity.EntityName = szEntityName
   SetAttributeFromString( zqFrame, "ParentEntity", "EntityName", szEntityName );
   //:IF vLOD.ER_RelLinkRec EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( vLOD, "ER_RelLinkRec" );
   if ( lTempInteger_1 == 0 )
   { 
      //:CreateViewFromView( zqFrame2, zqFrameRoot )
      CreateViewFromView( zqFrame2, zqFrameRoot );
      //:IF vLOD.ER_RelLinkRec.CardMax = "1"
      if ( CompareAttributeToString( vLOD, "ER_RelLinkRec", "CardMax", "1" ) == 0 )
      { 
         //:szCardMax = 1
          {StringBuilder sb_szCardMax;
         if ( szCardMax == null )
            sb_szCardMax = new StringBuilder( 32 );
         else
            sb_szCardMax = new StringBuilder( szCardMax );
                  ZeidonStringConvertFromNumber( sb_szCardMax, 1, 0, 2, 1, (double) 0.0, "I" );
         szCardMax = sb_szCardMax.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szCardMax = 99
          {StringBuilder sb_szCardMax;
         if ( szCardMax == null )
            sb_szCardMax = new StringBuilder( 32 );
         else
            sb_szCardMax = new StringBuilder( szCardMax );
                  ZeidonStringConvertFromNumber( sb_szCardMax, 1, 0, 2, 99, (double) 0.0, "I" );
         szCardMax = sb_szCardMax.toString( );}
      } 

      //:END
      //:SetAttributeFromString( zqFrame, "ParentEntity", "LOD_MaxCardinality", szCardMax )
      SetAttributeFromString( zqFrame, "ParentEntity", "LOD_MaxCardinality", szCardMax );
      //:SET CURSOR FIRST zqFrame2.GeneralParameter 
      //:           WHERE zqFrame2.GeneralParameter.EntityName = szEntityName 
      //:             AND zqFrame2.GeneralParameter.ForceFormatMaxCardinalityOne = "Y"
      RESULT = SetCursorFirstEntity( zqFrame2, "GeneralParameter", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame2, "GeneralParameter", "EntityName", szEntityName ) != 0 || CompareAttributeToString( zqFrame2, "GeneralParameter", "ForceFormatMaxCardinalityOne", "Y" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( zqFrame2, "GeneralParameter", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// The entity has been requested for formatting as Max Cardinality 1, so force that cardinality.
         //:szCardMax = 1
          {StringBuilder sb_szCardMax;
         if ( szCardMax == null )
            sb_szCardMax = new StringBuilder( 32 );
         else
            sb_szCardMax = new StringBuilder( szCardMax );
                  ZeidonStringConvertFromNumber( sb_szCardMax, 1, 0, 2, 1, (double) 0.0, "I" );
         szCardMax = sb_szCardMax.toString( );}
      } 

      //:END
      //:SetAttributeFromString( zqFrame, "ParentEntity", "MaxCardinality", szCardMax )
      SetAttributeFromString( zqFrame, "ParentEntity", "MaxCardinality", szCardMax );
      //:DropView( zqFrame2 )
      DropView( zqFrame2 );
      //:ELSE
   } 
   else
   { 
      //:IF vLOD.LOD_EntityParent.Work = "Y" OR vLOD.LOD_EntityParent.Derived = "Y"
      if ( CompareAttributeToString( vLOD, "LOD_EntityParent", "Work", "Y" ) == 0 || CompareAttributeToString( vLOD, "LOD_EntityParent", "Derived", "Y" ) == 0 )
      { 
         //:// Don C modification on 3/19/2012 so that max cardinality could be forced on work entities.
         //:// Set max cardinality of 99 or work entity, unless the force max cardinality attribue it set.
         //:CreateViewFromView( zqFrame2, zqFrameRoot )
         CreateViewFromView( zqFrame2, zqFrameRoot );
         //:SET CURSOR FIRST zqFrame2.GeneralParameter 
         //:           WHERE zqFrame2.GeneralParameter.EntityName = szEntityName 
         //:             AND zqFrame2.GeneralParameter.ForceFormatMaxCardinalityOne = "Y"
         RESULT = SetCursorFirstEntity( zqFrame2, "GeneralParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame2, "GeneralParameter", "EntityName", szEntityName ) != 0 || CompareAttributeToString( zqFrame2, "GeneralParameter", "ForceFormatMaxCardinalityOne", "Y" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrame2, "GeneralParameter", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:// The entity has been requested for formatting as Max Cardinality 1, so force that cardinality.
            //:SetAttributeFromString( zqFrame, "ParentEntity", "MaxCardinality", "1" )
            SetAttributeFromString( zqFrame, "ParentEntity", "MaxCardinality", "1" );
            //:ELSE
         } 
         else
         { 
            //:SetAttributeFromString( zqFrame, "ParentEntity", "MaxCardinality", "99" )
            SetAttributeFromString( zqFrame, "ParentEntity", "MaxCardinality", "99" );
         } 

         //:END
         //:DropView( zqFrame2 )
         DropView( zqFrame2 );
      } 

      //:END
   } 

   //:END

   //:// Add any General Parameter Display Attributes under the appropriate ParentEntity.
   //:FOR EACH zqFrameRoot.GeneralParameter
   //:      WHERE zqFrameRoot.GeneralParameter.ListDisplayFlag = "Y"
   //:        AND zqFrameRoot.GeneralParameter.EntityName = szEntityName
   //:        AND ( zqFrameRoot.GeneralParameter.EntityName != zqFrameRoot.zqFrame.QueryObjectRootEntityName OR
   //:              zqFrameRoot.GeneralParameter.SubgroupSearchType = "" )
   RESULT = SetCursorFirstEntity( zqFrameRoot, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( zqFrameRoot, "GeneralParameter", "ListDisplayFlag", "Y" ) == 0 && CompareAttributeToString( zqFrameRoot, "GeneralParameter", "EntityName", szEntityName ) == 0 &&
           ( CompareAttributeToAttribute( zqFrameRoot, "GeneralParameter", "EntityName", zqFrameRoot, "zqFrame", "QueryObjectRootEntityName" ) != 0 || CompareAttributeToString( zqFrameRoot, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ) )
      { 
         //:        
         //:// Note that for Case 1 above, we skip the code inside the loop based on the WHERE condition.
         //:        
         //:// zqFrame2 will be positioned on the Entity that is to display the Attribute, which may be an
         //:// Entity up the parent tree. For a regular Attribute, the position won't change.
         //:CreateViewFromView( zqFrame2, zqFrame )
         CreateViewFromView( zqFrame2, zqFrame );
         //:NAME VIEW zqFrame2 "zqFrame2"
         SetNameForView( zqFrame2, "zqFrame2", null, zLEVEL_TASK );
         //:szGroupOper = zqFrameRoot.GeneralParameter.SubgroupSearchType
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szGroupOper;
         if ( szGroupOper == null )
            sb_szGroupOper = new StringBuilder( 32 );
         else
            sb_szGroupOper = new StringBuilder( szGroupOper );
                   GetVariableFromAttribute( sb_szGroupOper, mi_lTempInteger_2, 'S', 6, zqFrameRoot, "GeneralParameter", "SubgroupSearchType", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szGroupOper = sb_szGroupOper.toString( );}
         //:IF szGroupOper = "SUM" OR
         //:szGroupOper = "AVG" OR
         //:szGroupOper = "MAX" OR
         //:szGroupOper = "MIN" OR
         //:szGroupOper = "NNULL" OR
         //:szGroupOper = "COUNT"
         if ( ZeidonStringCompare( szGroupOper, 1, 0, "SUM", 1, 0, 6 ) == 0 || ZeidonStringCompare( szGroupOper, 1, 0, "AVG", 1, 0, 6 ) == 0 || ZeidonStringCompare( szGroupOper, 1, 0, "MAX", 1, 0, 6 ) == 0 ||
              ZeidonStringCompare( szGroupOper, 1, 0, "MIN", 1, 0, 6 ) == 0 || ZeidonStringCompare( szGroupOper, 1, 0, "NNULL", 1, 0, 6 ) == 0 || ZeidonStringCompare( szGroupOper, 1, 0, "COUNT", 1, 0, 6 ) == 0 )
         { 

            //:IF zqFrameRoot.GeneralParameter.ScopingEntityName = ""
            if ( CompareAttributeToString( zqFrameRoot, "GeneralParameter", "ScopingEntityName", "" ) == 0 )
            { 
               //:// Case 2 above - The attribute is defined as a Subgroup Search Type without a Scoping
               //:// Entity so just put it on its parent.
               //:ResetViewFromSubobject( zqFrame2 )
               ResetViewFromSubobject( zqFrame2 );
               //:ELSE
            } 
            else
            { 
               //:// Case 3 above - The attribute is defined as a Subgroup Search Type with a Scoping
               //:// Entity so just put it on the Scoping Entity.
               //:nRC = ResetViewFromSubobject( zqFrame2 )
               nRC = ResetViewFromSubobject( zqFrame2 );
               //:LOOP WHILE nRC = 0 AND
               //:        zqFrame2.ParentEntity.EntityName != zqFrameRoot.GeneralParameter.ScopingEntityName AND 
               //:        zqFrame2.ParentEntity.EntityName != zqFrameRoot.zqFrame.QueryObjectRootEntityName
               while ( nRC == 0 && CompareAttributeToAttribute( zqFrame2, "ParentEntity", "EntityName", zqFrameRoot, "GeneralParameter", "ScopingEntityName" ) != 0 &&
                       CompareAttributeToAttribute( zqFrame2, "ParentEntity", "EntityName", zqFrameRoot, "zqFrame", "QueryObjectRootEntityName" ) != 0 )
               { 
                  //:nRC = ResetViewFromSubobject( zqFrame2 )
                  nRC = ResetViewFromSubobject( zqFrame2 );
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF zqFrame.ParentEntity.MaxCardinality = 1
            if ( CompareAttributeToInteger( zqFrame, "ParentEntity", "MaxCardinality", 1 ) == 0 )
            { 
               //:// Make sure that the GeneralParameter has the correct Max Cardinality.
               //:IF zqFrameRoot.GeneralParameter.MaxCardinality != zqFrame.ParentEntity.MaxCardinality
               if ( CompareAttributeToAttribute( zqFrameRoot, "GeneralParameter", "MaxCardinality", zqFrame, "ParentEntity", "MaxCardinality" ) != 0 )
               { 
                  //:zqFrameRoot.GeneralParameter.MaxCardinality = zqFrame.ParentEntity.MaxCardinality
                  SetAttributeFromAttribute( zqFrameRoot, "GeneralParameter", "MaxCardinality", zqFrame, "ParentEntity", "MaxCardinality" );
               } 

               //:END
               //:// Case 4 above - We are a regular Attribute with max cardinality of 1, so go up the
               //:// tree until we get to the top or until we find a parent with max cardinality > 1.
               //:nRC = ResetViewFromSubobject( zqFrame2 )
               nRC = ResetViewFromSubobject( zqFrame2 );
               //:LOOP WHILE nRC = 0 AND 
               //:        zqFrame2.ParentEntity.MaxCardinality = 1 AND 
               //:        zqFrame2.ParentEntity.EntityName != zqFrameRoot.zqFrame.QueryObjectRootEntityName 
               while ( nRC == 0 && CompareAttributeToInteger( zqFrame2, "ParentEntity", "MaxCardinality", 1 ) == 0 && CompareAttributeToAttribute( zqFrame2, "ParentEntity", "EntityName", zqFrameRoot, "zqFrame", "QueryObjectRootEntityName" ) != 0 )
               { 
                  //:nRC = ResetViewFromSubobject( zqFrame2 )
                  nRC = ResetViewFromSubobject( zqFrame2 );
               } 

               //:END
            } 

            //:END
         } 

         //:END
         //:SET CURSOR LAST zqFrame2.QueryAttribute
         RESULT = SetCursorLastEntity( zqFrame2, "QueryAttribute", "" );

         //:// Determine the length of the Attribute, which is taken from the LOD, unless there is an
         //:// override length.
         //:// **** TEMP CHANGE: 3/19/2019, DonC first replaced the SET CURSOR .. WITHIN with the loop below, but then commented out
         //:// everything because the ResetViewFromSubobject for vLOD was not positioning on the parent entity.
         //:/*SET CURSOR FIRST vLOD.LOD_Attribute
         //:szFoundFlag = ""
         //:LOOP WHILE RESULT >= zCURSOR_SET AND szFoundFlag = ""
         //:IF vLOD.ER_AttributeRec.Name = zqFrameRoot.GeneralParameter.AttributeName
         //:   szFoundFlag = "Y"
         //:ELSE
         //:   SET CURSOR NEXT vLOD.LOD_Attribute
         //:END
         //:END
         //:IF szFoundFlag = "Y" 
         //://SET CURSOR FIRST vLOD.ER_AttributeRec WITHIN vLOD.LOD_EntityParent
         //://           WHERE vLOD.ER_AttributeRec.Name = zqFrameRoot.GeneralParameter.AttributeName
         //://IF RESULT >= zCURSOR_SET
         //:IF zqFrameRoot.GeneralParameter.OverrideLength = ""
         //:    // For Table Domains, the Length is always 12.
         //:    IF vLOD.DomainRec.DomainType = "T"
         //:       Length = 12
         //:    ELSE
         //:       // Otherwise, use length from LOD or based on Data Type.
         //:       DataType = vLOD.DomainRec.DataType
         //:       IF DataType = "M" OR DataType = "T" OR DataType = "D"
         //:          // Decimal, DateTime or Date
         //:          Length = 15
         //:       ELSE
         //:       IF DataType = "S"
         //:          // String
         //:          IF vLOD.ER_AttributeRec.Lth != ""
         //:             Length = vLOD.ER_AttributeRec.Lth
         //:          ELSE
         //:             Length = vLOD.DomainRec.MaxStringLth
         //:          END
         //:       ELSE
         //:          // All Others
         //:          Length = 12
         //:       END
         //:       END
         //:    END
         //: ELSE
         //:    // User override length.
         //:    Length = zqFrameRoot.GeneralParameter.OverrideLength
         //: END
         //:END*/
         //:Length = 20
         Length = 20;
         //:CREATE ENTITY zqFrame2.QueryAttribute
         RESULT = CreateEntity( zqFrame2, "QueryAttribute", zPOS_AFTER );
         //:IF szGroupOper = "" OR
         //:szGroupOper = "ANY" OR
         //:szGroupOper = "ALL"
         if ( ZeidonStringCompare( szGroupOper, 1, 0, "", 1, 0, 6 ) == 0 || ZeidonStringCompare( szGroupOper, 1, 0, "ANY", 1, 0, 6 ) == 0 || ZeidonStringCompare( szGroupOper, 1, 0, "ALL", 1, 0, 6 ) == 0 )
         { 

            //:// This is not a group operation, so set regular values.
            //:zqFrame2.QueryAttribute.AttributeName        = zqFrameRoot.GeneralParameter.AttributeName
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "AttributeName", zqFrameRoot, "GeneralParameter", "AttributeName" );
            //:zqFrame2.QueryAttribute.MappingEntityName    = szEntityName
            SetAttributeFromString( zqFrame2, "QueryAttribute", "MappingEntityName", szEntityName );
            //:zqFrame2.QueryAttribute.Prompt               = zqFrameRoot.GeneralParameter.Title
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "Prompt", zqFrameRoot, "GeneralParameter", "Title" );
            //:zqFrame2.QueryAttribute.Length               = Length
            SetAttributeFromInteger( zqFrame2, "QueryAttribute", "Length", Length );
            //:zqFrame2.QueryAttribute.Type                 = zqFrameRoot.GeneralParameter.DataType
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "Type", zqFrameRoot, "GeneralParameter", "DataType" );
            //:zqFrame2.QueryAttribute.DisplayRow           = zqFrameRoot.GeneralParameter.DisplayRow
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "DisplayRow", zqFrameRoot, "GeneralParameter", "DisplayRow" );
            //:zqFrame2.QueryAttribute.DisplayDomainContext = zqFrameRoot.GeneralParameter.DisplayDomainContext 
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "DisplayDomainContext", zqFrameRoot, "GeneralParameter", "DisplayDomainContext" );

            //:ELSE
         } 
         else
         { 
            //:// This is a group operation (SUM, AVG, etc.), so set special values.
            //:zqFrame2.QueryAttribute.GroupOperation              = szGroupOper
            SetAttributeFromString( zqFrame2, "QueryAttribute", "GroupOperation", szGroupOper );
            //:zqFrame2.QueryAttribute.GroupOperationEntityName    = zqFrameRoot.GeneralParameter.EntityName
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "GroupOperationEntityName", zqFrameRoot, "GeneralParameter", "EntityName" );
            //:zqFrame2.QueryAttribute.GroupOperationAttributeName = zqFrameRoot.GeneralParameter.AttributeName
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "GroupOperationAttributeName", zqFrameRoot, "GeneralParameter", "AttributeName" );
            //:zqFrame2.QueryAttribute.Prompt                      = zqFrameRoot.GeneralParameter.Title
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "Prompt", zqFrameRoot, "GeneralParameter", "Title" );
            //:zqFrame2.QueryAttribute.Length                      = Length
            SetAttributeFromInteger( zqFrame2, "QueryAttribute", "Length", Length );
            //:zqFrame2.QueryAttribute.Type                        = zqFrameRoot.GeneralParameter.DataType
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "Type", zqFrameRoot, "GeneralParameter", "DataType" );
            //:zqFrame2.QueryAttribute.DisplayRow                  = zqFrameRoot.GeneralParameter.DisplayRow
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "DisplayRow", zqFrameRoot, "GeneralParameter", "DisplayRow" );
            //:zqFrame2.QueryAttribute.DisplayDomainContext        = zqFrameRoot.GeneralParameter.DisplayDomainContext 
            SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "DisplayDomainContext", zqFrameRoot, "GeneralParameter", "DisplayDomainContext" );

            //:IF zqFrameRoot.GeneralParameter.ScopingEntityName != ""
            if ( CompareAttributeToString( zqFrameRoot, "GeneralParameter", "ScopingEntityName", "" ) != 0 )
            { 
               //:zqFrame2.QueryAttribute.GroupOperationScopingEntityName = zqFrameRoot.GeneralParameter.ScopingEntityName 
               SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "GroupOperationScopingEntityName", zqFrameRoot, "GeneralParameter", "ScopingEntityName" );
               //:ELSE
            } 
            else
            { 
               //:// Scoping default is parent.
               //:zqFrame2.QueryAttribute.GroupOperationScopingEntityName = zqFrame2.ParentEntity.EntityName 
               SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "GroupOperationScopingEntityName", zqFrame2, "ParentEntity", "EntityName" );
            } 

            //:END
         } 

         //:END
         //:// Make sure order is maintained.
         //:zqFrame2.QueryAttribute.DisplayOrder = zqFrameRoot.GeneralParameter.DisplayOrder 
         SetAttributeFromAttribute( zqFrame2, "QueryAttribute", "DisplayOrder", zqFrameRoot, "GeneralParameter", "DisplayOrder" );
         //:OrderEntityForView( zqFrame2, "QueryAttribute", "DisplayOrder A" )
         OrderEntityForView( zqFrame2, "QueryAttribute", "DisplayOrder A" );
         //:DropView( zqFrame2 )
         DropView( zqFrame2 );
      } 

      RESULT = SetCursorNextEntity( zqFrameRoot, "GeneralParameter", "" );
   } 

   //:END

   //:// Create rest of recursive structure.
   //:// On 4/29/2019 DonC switched SetViewToSubobject to after ChildEntity is created because JOE was setting the view to
   //:// the subobject if the entity didn't exist.
   //:IF vLOD.LOD_EntityChild EXISTS
   lTempInteger_3 = CheckExistenceOfEntity( vLOD, "LOD_EntityChild" );
   if ( lTempInteger_3 == 0 )
   { 
      //:SetViewToSubobject( vLOD, "LOD_EntityChild" )
      SetViewToSubobject( vLOD, "LOD_EntityChild" );
      //:SetViewToSubobject( zqFrame, "ChildEntity" )
      SetViewToSubobject( zqFrame, "ChildEntity" );
      //:FOR EACH vLOD.LOD_EntityParent
      RESULT = SetCursorFirstEntity( vLOD, "LOD_EntityParent", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:FormatDisplayRecur( zqFrame, zqFrameRoot, vLOD )
         ozqFrame_FormatDisplayRecur( zqFrame, zqFrameRoot, vLOD );
         RESULT = SetCursorNextEntity( vLOD, "LOD_EntityParent", "" );
      } 

      //:END
      //://szEntityName = vLOD.LOD_EntityParent.Name 
      //://TraceLineS( "@@ Before Reset: ", szEntityName )
      //:ResetViewFromSubobject( vLOD )
      ResetViewFromSubobject( vLOD );
      //://szEntityName = vLOD.LOD_EntityParent.Name 
      //://TraceLineS( "@@ After Reset: ", szEntityName )
      //:ResetViewFromSubobject( zqFrame )
      ResetViewFromSubobject( zqFrame );
   } 

   //:END
   return( 0 );
//    
//    /*SetViewToSubobject( vLOD, "LOD_EntityChild" )
//    SetViewToSubobject( zqFrame, "ChildEntity" )
//    FOR EACH vLOD.LOD_EntityParent
//       FormatDisplayRecur( zqFrame, zqFrameRoot, vLOD )
//    END
//    ResetViewFromSubobject( vLOD )
//    ResetViewFromSubobject( zqFrame )*/
// END
} 


//:TRANSFORMATION OPERATION
//:ComputeOperationValue( VIEW zqFrame BASED ON LOD zqFrame,
//:                       VIEW vResultSetOrig,
//:                       DECIMAL ReturnedValue,
//:                       STRING ( 32 ) OperationName,
//:                       STRING ( 32 ) szEntityName,
//:                       STRING ( 32 ) szAttributeName,
//:                       STRING ( 32 ) szScopingEntityName )

//:   // Perform the group operation of:
//:   //   SUM
//:   //   AVG
//:   //   COUNT
//:   //   NNULL
//:   //   MAX
//:   //   MIN
//:   // on the Entity/Attribute passed and return the value as a decimal.

//:   VIEW vResultSet
public int 
ozqFrame_ComputeOperationValue( View     zqFrame,
                                View     vResultSetOrig,
                                MutableDouble  ReturnedValue,
                                String   OperationName,
                                String   szEntityName,
                                String   szAttributeName,
                                String   szScopingEntityName )
{
   zVIEW    vResultSet = new zVIEW( );
   //:STRING ( 30 ) szNumericValue
   String   szNumericValue = null;
   //:DECIMAL Sum
   double  Sum = 0.0;
   //:DECIMAL Max
   double  Max = 0.0;
   //:DECIMAL Min
   double  Min = 0.0;
   //:DECIMAL Average
   double  Average = 0.0;
   //:DECIMAL AttributeValue
   double  AttributeValue = 0.0;
   //:INTEGER Count
   int      Count = 0;
   //:INTEGER nRC
   int      nRC = 0;


   //:// We'll do everything using temp view.
   //:CreateViewFromView( vResultSet, vResultSetOrig ) 
   CreateViewFromView( vResultSet, vResultSetOrig );
   //:NAME VIEW vResultSet "ResultSetTemp"  
   SetNameForView( vResultSet, "ResultSetTemp", null, zLEVEL_TASK );

   //:IF OperationName = "MAX"
   if ( ZeidonStringCompare( OperationName, 1, 0, "MAX", 1, 0, 33 ) == 0 )
   { 
      //:Count = 0
      Count = 0;
      //:Max   = 0
      Max = 0;
      //:nRC = SetCursorFirstEntity( vResultSet,
      //:                            szEntityName,
      //:                            szScopingEntityName )
      nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 
         //:LOOP WHILE nRC >= zCURSOR_SET
         while ( nRC >= zCURSOR_SET )
         { 

            //:// Null values and invalid decimal values are ignored. 
            //:GetStringFromAttribute( szNumericValue, vResultSet, szEntityName, szAttributeName )
            {StringBuilder sb_szNumericValue;
            if ( szNumericValue == null )
               sb_szNumericValue = new StringBuilder( 32 );
            else
               sb_szNumericValue = new StringBuilder( szNumericValue );
                         GetStringFromAttribute( sb_szNumericValue, vResultSet, szEntityName, szAttributeName );
            szNumericValue = sb_szNumericValue.toString( );}
            //:IF szNumericValue != ""
            if ( ZeidonStringCompare( szNumericValue, 1, 0, "", 1, 0, 31 ) != 0 )
            { 
               //:nRC = IsNumber( szNumericValue )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
                nRC = m_ZGLOBAL1_Operation.IsNumber( szNumericValue );
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
               //:IF nRC != 2
               if ( nRC != 2 )
               { 
                  //:GetDecimalFromAttribute( AttributeValue, vResultSet, szEntityName, szAttributeName )
                  {MutableDouble md_AttributeValue = new MutableDouble( AttributeValue );
                                     GetDecimalFromAttribute( md_AttributeValue, vResultSet, szEntityName, szAttributeName );
                  AttributeValue = md_AttributeValue.doubleValue( );}
                  //:Count = Count + 1
                  Count = Count + 1;
                  //:IF AttributeValue > Max OR Count = 1
                  if ( AttributeValue > Max || Count == 1 )
                  { 
                     //:Max = AttributeValue
                     Max = AttributeValue;
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
            //:nRC = SetCursorNextEntity( vResultSet,
            //:                           szEntityName,
            //:                           szScopingEntityName )
            nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
         } 

         //:END
      } 

      //:END
      //:ReturnedValue = Max
      ReturnedValue.setValue( Max );
      //:ELSE
   } 
   else
   { 
      //:IF OperationName = "MIN"
      if ( ZeidonStringCompare( OperationName, 1, 0, "MIN", 1, 0, 33 ) == 0 )
      { 
         //:Count = 0
         Count = 0;
         //:Min   = 0
         Min = 0;
         //:nRC = SetCursorFirstEntity( vResultSet,
         //:                         szEntityName,
         //:                         szScopingEntityName )
         nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
         //:IF nRC >= zCURSOR_SET
         if ( nRC >= zCURSOR_SET )
         { 
            //:LOOP WHILE nRC >= zCURSOR_SET
            while ( nRC >= zCURSOR_SET )
            { 

               //:// Null values and invalid decimal values are ignored. 
               //:GetStringFromAttribute( szNumericValue, vResultSet, szEntityName, szAttributeName )
               {StringBuilder sb_szNumericValue;
               if ( szNumericValue == null )
                  sb_szNumericValue = new StringBuilder( 32 );
               else
                  sb_szNumericValue = new StringBuilder( szNumericValue );
                               GetStringFromAttribute( sb_szNumericValue, vResultSet, szEntityName, szAttributeName );
               szNumericValue = sb_szNumericValue.toString( );}
               //:IF szNumericValue != ""
               if ( ZeidonStringCompare( szNumericValue, 1, 0, "", 1, 0, 31 ) != 0 )
               { 
                  //:nRC = IsNumber( szNumericValue )
                  {
                   ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
                   nRC = m_ZGLOBAL1_Operation.IsNumber( szNumericValue );
                   // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                  }
                  //:IF nRC != 2
                  if ( nRC != 2 )
                  { 
                     //:GetDecimalFromAttribute( AttributeValue, vResultSet, szEntityName, szAttributeName )
                     {MutableDouble md_AttributeValue = new MutableDouble( AttributeValue );
                                           GetDecimalFromAttribute( md_AttributeValue, vResultSet, szEntityName, szAttributeName );
                     AttributeValue = md_AttributeValue.doubleValue( );}
                     //:Count = Count + 1
                     Count = Count + 1;
                     //:IF AttributeValue < Min OR Count = 1
                     if ( AttributeValue < Min || Count == 1 )
                     { 
                        //:Min = AttributeValue
                        Min = AttributeValue;
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
               //:nRC = SetCursorNextEntity( vResultSet,
               //:                        szEntityName,
               //:                        szScopingEntityName )
               nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
            } 

            //:END
         } 

         //:END
         //:ReturnedValue = Min
         ReturnedValue.setValue( Min );
         //:ELSE
      } 
      else
      { 
         //:IF OperationName = "COUNT"
         if ( ZeidonStringCompare( OperationName, 1, 0, "COUNT", 1, 0, 33 ) == 0 )
         { 
            //:Count = 0
            Count = 0;
            //:nRC = SetCursorFirstEntity( vResultSet,
            //:                      szEntityName,
            //:                      szScopingEntityName )
            nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
            //:LOOP WHILE nRC >= zCURSOR_SET
            while ( nRC >= zCURSOR_SET )
            { 
               //:Count = Count + 1
               Count = Count + 1;
               //:nRC = SetCursorNextEntity( vResultSet,
               //:                     szEntityName,
               //:                     szScopingEntityName )
               nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
            } 

            //:END
            //:ReturnedValue = Count
            ReturnedValue.setValue( Count );
            //:ELSE
         } 
         else
         { 
            //:IF OperationName = "NNULL"
            if ( ZeidonStringCompare( OperationName, 1, 0, "NNULL", 1, 0, 33 ) == 0 )
            { 
               //:Count = 0
               Count = 0;
               //:nRC = SetCursorFirstEntity( vResultSet,
               //:                   szEntityName,
               //:                   szScopingEntityName )
               nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
               //:LOOP WHILE nRC >= zCURSOR_SET
               while ( nRC >= zCURSOR_SET )
               { 
                  //:nRC = CompareAttributeToString( vResultSet,
                  //:                       szEntityName,
                  //:                       szAttributeName,
                  //:                       "" )
                  nRC = CompareAttributeToString( vResultSet, szEntityName, szAttributeName, "" );
                  //:IF nRC != 0
                  if ( nRC != 0 )
                  { 
                     //:Count = Count + 1
                     Count = Count + 1;
                  } 

                  //:END
                  //:nRC = SetCursorNextEntity( vResultSet,
                  //:                  szEntityName,
                  //:                  szScopingEntityName )
                  nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
               } 

               //:END
               //:ReturnedValue = Count
               ReturnedValue.setValue( Count );
               //:ELSE
            } 
            else
            { 
               //:IF OperationName = "SUM" OR OperationName = "AVG"
               if ( ZeidonStringCompare( OperationName, 1, 0, "SUM", 1, 0, 33 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "AVG", 1, 0, 33 ) == 0 )
               { 
                  //:Count = 0
                  Count = 0;
                  //:Sum   = 0
                  Sum = 0;
                  //:nRC = SetCursorFirstEntity( vResultSet,
                  //:                szEntityName,
                  //:                szScopingEntityName )
                  nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
                  //:LOOP WHILE nRC >= zCURSOR_SET
                  while ( nRC >= zCURSOR_SET )
                  { 

                     //:// Null values and invalid decimal values are ignored. 
                     //:GetStringFromAttribute( szNumericValue, vResultSet, szEntityName, szAttributeName )
                     {StringBuilder sb_szNumericValue;
                     if ( szNumericValue == null )
                        sb_szNumericValue = new StringBuilder( 32 );
                     else
                        sb_szNumericValue = new StringBuilder( szNumericValue );
                                           GetStringFromAttribute( sb_szNumericValue, vResultSet, szEntityName, szAttributeName );
                     szNumericValue = sb_szNumericValue.toString( );}
                     //:IF szNumericValue != ""
                     if ( ZeidonStringCompare( szNumericValue, 1, 0, "", 1, 0, 31 ) != 0 )
                     { 
                        //:nRC = IsNumber( szNumericValue )
                        {
                         ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
                         nRC = m_ZGLOBAL1_Operation.IsNumber( szNumericValue );
                         // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                        }
                        //:IF nRC != 2
                        if ( nRC != 2 )
                        { 
                           //:GetDecimalFromAttribute( AttributeValue, vResultSet, szEntityName, szAttributeName )
                           {MutableDouble md_AttributeValue = new MutableDouble( AttributeValue );
                                                       GetDecimalFromAttribute( md_AttributeValue, vResultSet, szEntityName, szAttributeName );
                           AttributeValue = md_AttributeValue.doubleValue( );}
                           //:Count = Count + 1
                           Count = Count + 1;
                           //:Sum = Sum + AttributeValue
                           Sum = Sum + AttributeValue;
                        } 

                        //:END
                     } 

                     //:END
                     //:nRC = SetCursorNextEntity( vResultSet,
                     //:               szEntityName,
                     //:               szScopingEntityName )
                     nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
                  } 

                  //:END
                  //:IF OperationName = "AVG"
                  if ( ZeidonStringCompare( OperationName, 1, 0, "AVG", 1, 0, 33 ) == 0 )
                  { 
                     //:IF Count = 0
                     if ( Count == 0 )
                     { 
                        //:ReturnedValue = 0
                        ReturnedValue.setValue( 0 );
                        //:ELSE
                     } 
                     else
                     { 
                        //:ReturnedValue = Sum / Count
                        ReturnedValue.setValue( Sum / Count );
                     } 

                     //:END
                     //:ELSE
                  } 
                  else
                  { 
                     //:ReturnedValue = Sum
                     ReturnedValue.setValue( Sum );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:DropView( vResultSet )
   DropView( vResultSet );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ActivateQueryObjectO( VIEW zqFrame BASED ON LOD zqFrame,
//:                      VIEW vResultSet )

//:   VIEW wXferO      REGISTERED AS wXferO
public int 
ozqFrame_ActivateQueryObjectO( View     zqFrame,
                               zVIEW    vResultSet )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW zqFrameRoot BASED ON LOD  zqFrame
   zVIEW    zqFrameRoot = new zVIEW( );
   //:VIEW vQualObject
   zVIEW    vQualObject = new zVIEW( );
   //:VIEW vQualTemp
   zVIEW    vQualTemp = new zVIEW( );
   //:VIEW vConvertObject
   zVIEW    vConvertObject = new zVIEW( );
   //:STRING ( 32 )  szObjectName
   String   szObjectName = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:// Format a qualification object from the query criteria in zqFrame and activate the query object3
   //:// based on the qualification. Then apply any postactivate qualification from zqFrame.

   //:GET VIEW vQualObject NAMED "vQualObject"
   RESULT = GetViewByName( vQualObject, "vQualObject", zqFrame, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( vQualObject )
      DropObjectInstance( vQualObject );
   } 

   //:END
   //:GET VIEW vResultSet NAMED "ResultSet"
   RESULT = GetViewByName( vResultSet, "ResultSet", zqFrame, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( vResultSet )
      DropObjectInstance( vResultSet );
   } 

   //:END
   //:GET VIEW vResultSet NAMED "OriginalResultSet"
   RESULT = GetViewByName( vResultSet, "OriginalResultSet", zqFrame, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( vResultSet )
      DropObjectInstance( vResultSet );
   } 

   //:END

   //:// Build the qualification information and activate the object.
   //:// Note that the BuildQualFromFrame operation requires an instance of the object to be
   //:// queried for data conversion.
   //:szObjectName = zqFrame.zqFrame.QueryObjectName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szObjectName;
   if ( szObjectName == null )
      sb_szObjectName = new StringBuilder( 32 );
   else
      sb_szObjectName = new StringBuilder( szObjectName );
       GetVariableFromAttribute( sb_szObjectName, mi_lTempInteger_0, 'S', 33, zqFrame, "zqFrame", "QueryObjectName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szObjectName = sb_szObjectName.toString( );}
   //:ActivateEmptyObjectInstance( vConvertObject, szObjectName, zqFrame, zSINGLE )
   ActivateEmptyObjectInstance( vConvertObject, szObjectName, zqFrame, zSINGLE );
   //:nRC = BuildQualFromFrame( zqFrame, vQualObject, vConvertObject )
   nRC = ozqFrame_BuildQualFromFrame( zqFrame, vQualObject, vConvertObject );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:DropObjectInstance( vConvertObject )
   DropObjectInstance( vConvertObject );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:SET CURSOR FIRST vQualObject.QualAttrib
   RESULT = SetCursorFirstEntity( vQualObject, "QualAttrib", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 

      //:// Fix the "not equal" SQL problem by adding a test for NULL.
      //:FOR EACH vQualObject.QualAttrib
      RESULT = SetCursorFirstEntity( vQualObject, "QualAttrib", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF vQualObject.QualAttrib.Oper = "<>" AND 
         //:   vQualObject.QualAttrib.Value != "" 
         if ( CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "<>" ) == 0 && CompareAttributeToString( vQualObject, "QualAttrib", "Value", "" ) != 0 )
         { 

            //:CreateViewFromView( vQualTemp, vQualObject )
            CreateViewFromView( vQualTemp, vQualObject );
            //:CreateEntity( vQualTemp, "QualAttrib", zPOS_BEFORE )
            CreateEntity( vQualTemp, "QualAttrib", zPOS_BEFORE );
            //:SetAttributeFromString( vQualTemp, "QualAttrib", "Oper", "(" )
            SetAttributeFromString( vQualTemp, "QualAttrib", "Oper", "(" );

            //:CreateEntity( vQualTemp, "QualAttrib", zPOS_AFTER )
            CreateEntity( vQualTemp, "QualAttrib", zPOS_AFTER );
            //:SetMatchingAttributesByName( vQualTemp, "QualAttrib",
            //:                             vQualObject, "QualAttrib", zSET_ALL )
            SetMatchingAttributesByName( vQualTemp, "QualAttrib", vQualObject, "QualAttrib", zSET_ALL );
            //:SetAttributeFromString( vQualTemp, "QualAttrib", "Oper", "IS NULL" )
            SetAttributeFromString( vQualTemp, "QualAttrib", "Oper", "IS NULL" );
            //:SetAttributeFromString( vQualTemp, "QualAttrib", "Value", "" )
            SetAttributeFromString( vQualTemp, "QualAttrib", "Value", "" );

            //:CreateEntity( vQualTemp, "QualAttrib", zPOS_AFTER )
            CreateEntity( vQualTemp, "QualAttrib", zPOS_AFTER );
            //:SetAttributeFromString( vQualTemp, "QualAttrib", "Oper", "OR" )
            SetAttributeFromString( vQualTemp, "QualAttrib", "Oper", "OR" );

            //:CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER )
            CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
            //:SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" )
            SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );

            //:DropView( vQualTemp )
            DropView( vQualTemp );
         } 

         RESULT = SetCursorNextEntity( vQualObject, "QualAttrib", "" );
         //:END
      } 

      //:END

      //:SET CURSOR FIRST vQualObject.QualAttrib
      RESULT = SetCursorFirstEntity( vQualObject, "QualAttrib", "" );
      //:nRC = ActivateObjectInstance( vResultSet, szObjectName, zqFrame, vQualObject, zMULTIPLE )
      nRC = ActivateObjectInstance( vResultSet, szObjectName, zqFrame, vQualObject, zMULTIPLE );
      //:ELSE
   } 
   else
   { 
      //:nRC = ActivateObjectInstance( vResultSet, szObjectName, zqFrame, 0, zMULTIPLE )
      nRC = ActivateObjectInstance( vResultSet, szObjectName, zqFrame, null, zMULTIPLE );
   } 

   //:END

   //:IF nRC >= 0
   if ( nRC >= 0 )
   { 
      //:NAME VIEW vResultSet "ResultSet"
      SetNameForView( vResultSet, "ResultSet", null, zLEVEL_TASK );
   } 

   //:   
   //:   // If the object supports Seasonal Address, call the operation to process.
   //:   // We will do this by searching the object definition for entity, "CurrentMailingAddress" )
   //:   // Seasonal Address check removed by DonC on 9/19/18.
   //:   /*nRC = zGetFirstEntityNameForView( vResultSet, szEntityName )
   //:   LOOP WHILE nRC >= zCURSOR_SET AND szEntityName != "CurrentMailingAddress"
   //:      nRC = zGetNextEntityNameForView( vResultSet, szEntityName )
   //:   END
   //:   IF szEntityName = "CurrentMailingAddress"
   //:      GenerateSeasonalAddresses( vResultSet, wXferO, szObjectName )
   //:   END*/
   //:END

   //:IF nRC = 2
   if ( nRC == 2 )
   { 
      //:MessageSend( zqFrame, "", "Query",
      //:             "The maximum number of Query entries has been reached. Qualify the Query further for correct results.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( zqFrame, "", "Query", "The maximum number of Query entries has been reached. Qualify the Query further for correct results.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
   } 

   //:END

   //:// Go to perform post activate processing.
   //:nRC = PostActivateProc( zqFrame, vResultSet )
   nRC = ozqFrame_PostActivateProc( zqFrame, vResultSet );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:RETURN nRC
      if(8==8)return( nRC );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:AnalyzeReformatResult( VIEW zqFrame BASED ON LOD zqFrame,
//:                       VIEW vResultSet )

//:   VIEW zqSumR   BASED ON LOD zqSumR
public int 
ozqFrame_AnalyzeReformatResult( View     zqFrame,
                                View     vResultSet )
{
   zVIEW    zqSumR = new zVIEW( );
   //:SHORT nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:// If Breakpoint processing is requested, transform the vResultSet just activated into the
   //:// zqSumR display object.
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.BreakpointType != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointType", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:BuildSummaryResultSet( zqSumR, zqFrame, vResultSet )
      {
       zqSumR_Object m_zqSumR_Object = new zqSumR_Object( zqFrame );
       m_zqSumR_Object.ozqSumR_BuildSummaryResultSet( zqSumR, zqFrame, vResultSet );
       // m_zqSumR_Object = null;  // permit gc  (unnecessary)
      }
      //:NAME VIEW zqSumR "zqSumR"
      SetNameForView( zqSumR, "zqSumR", null, zLEVEL_TASK );

      //:// Modify the Display information to map from the zqSumR OI as the Result Set.
      //:BuildDisplayValues( zqSumR, zqFrame )
      {
       zqSumR_Object m_zqSumR_Object = new zqSumR_Object( zqSumR );
       m_zqSumR_Object.ozqSumR_BuildDisplayValues( zqSumR, zqFrame );
       // m_zqSumR_Object = null;  // permit gc  (unnecessary)
      }
      //:SET CURSOR FIRST zqFrame.ParentEntity   // Make sure the ParentEntity has valid cursor position.
      RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:PostActivateProc( VIEW zqFrame BASED ON LOD zqFrame,
//:                  VIEW vResultSet )

//:   VIEW zqFrameRoot BASED ON LOD zqFrame
public int 
ozqFrame_PostActivateProc( View     zqFrame,
                           View     vResultSet )
{
   zVIEW    zqFrameRoot = new zVIEW( );
   //:STRING ( 32 )  RootEntityName
   String   RootEntityName = null;
   //:DECIMAL        OperationResult
   double  OperationResult = 0.0;
   //:INTEGER        RootCount
   int      RootCount = 0;
   //:INTEGER        SubgroupSuffixCount
   int      SubgroupSuffixCount = 0;
   //:SHORT          nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// Format the Query object for Result Set display and do any specified post Activate
   //:// processing on the Result Set.

   //:RootEntityName = zqFrame.zqFrame.QueryObjectRootEntityName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_RootEntityName;
   if ( RootEntityName == null )
      sb_RootEntityName = new StringBuilder( 32 );
   else
      sb_RootEntityName = new StringBuilder( RootEntityName );
       GetVariableFromAttribute( sb_RootEntityName, mi_lTempInteger_0, 'S', 33, zqFrame, "zqFrame", "QueryObjectRootEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   RootEntityName = sb_RootEntityName.toString( );}

   //:// Set up suffixes for Subgroup Display values. This allows the Result Set to be displayed
   //:// in the Root Only list box, showing MAX, MIN, etc. This has to be redone each time the attributes
   //:// to be displayed are changed.
   //:SubgroupSuffixCount = 0
   SubgroupSuffixCount = 0;
   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Build the ListDisplaySuffix value and create the SubgroupDisplayEntry entity.
      //:IF zqFrame.GeneralParameter.ListDisplayFlag = "Y" AND
      //:   zqFrame.GeneralParameter.SubgroupSearchType != ""
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" ) == 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) != 0 )
      { 

         //:IF zqFrame.GeneralParameter.SubgroupSearchType != "ANY" AND
         //:   zqFrame.GeneralParameter.SubgroupSearchType != "ALL"
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ALL" ) != 0 )
         { 

            //:SubgroupSuffixCount = SubgroupSuffixCount + 1
            SubgroupSuffixCount = SubgroupSuffixCount + 1;
            //:IF SubgroupSuffixCount <= 4
            if ( SubgroupSuffixCount <= 4 )
            { 
               //:CREATE ENTITY zqFrame.SubgroupDisplayEntry
               RESULT = CreateEntity( zqFrame, "SubgroupDisplayEntry", zPOS_AFTER );
               //:zqFrame.SubgroupDisplayEntry.EntityName         = zqFrame.GeneralParameter.EntityName
               SetAttributeFromAttribute( zqFrame, "SubgroupDisplayEntry", "EntityName", zqFrame, "GeneralParameter", "EntityName" );
               //:zqFrame.SubgroupDisplayEntry.AttributeName      = zqFrame.GeneralParameter.AttributeName
               SetAttributeFromAttribute( zqFrame, "SubgroupDisplayEntry", "AttributeName", zqFrame, "GeneralParameter", "AttributeName" );
               //:zqFrame.SubgroupDisplayEntry.SubgroupSearchType = zqFrame.GeneralParameter.SubgroupSearchType
               SetAttributeFromAttribute( zqFrame, "SubgroupDisplayEntry", "SubgroupSearchType", zqFrame, "GeneralParameter", "SubgroupSearchType" );
               //:SetAttributeFromInteger( zqFrame, "SubgroupDisplayEntry", "ListDisplaySuffix",
               //:                         SubgroupSuffixCount )
               SetAttributeFromInteger( zqFrame, "SubgroupDisplayEntry", "ListDisplaySuffix", SubgroupSuffixCount );
               //:SetAttributeFromInteger( zqFrame, "GeneralParameter", "ListDisplaySuffix",
               //:                         SubgroupSuffixCount )
               SetAttributeFromInteger( zqFrame, "GeneralParameter", "ListDisplaySuffix", SubgroupSuffixCount );
            } 

            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END

   //:// Loop through all root entities and drop any that don't meet additional selection criteria.
   //:RootCount = 0
   RootCount = 0;
   //:IF vResultSet != 0
   if ( getView( vResultSet ) != null )
   { 
      //:nRC = SetCursorFirstEntity( vResultSet, RootEntityName, "" )
      nRC = SetCursorFirstEntity( vResultSet, RootEntityName, "" );
      //:ELSE
   } 
   else
   { 
      //:nRC = -1
      nRC = -1;
   } 

   //:END

   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 
      //:// Call routine to evaluate subselection criteria.
      //:nRC = CheckSubgroupSelection( zqFrame, vResultSet,
      //:                              zqFrame.zqFrame.QueryObjectRootEntityName,
      //:                              "Y" )   // Y indicates this is Root Entity qualification.
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, zqFrame, "zqFrame", "QueryObjectRootEntityName" );
      szTempString_0 = sb_szTempString_0.toString( );}
      nRC = ozqFrame_CheckSubgroupSelection( zqFrame, vResultSet, szTempString_0, "Y" );
      //:IF nRC < 0
      if ( nRC < 0 )
      { 
         //:// Selection criteria was not met, so drop the entity.
         //:DropEntity( vResultSet, RootEntityName, zREPOS_NONE )
         DropEntity( vResultSet, RootEntityName, zREPOS_NONE );
         //:ELSE
      } 
      else
      { 
         //:RootCount = RootCount + 1
         RootCount = RootCount + 1;
         //:// Perform restricting functionality on any 0-to-m subobject entities, if such a request
         //:// has been made.
         //:SET CURSOR FIRST zqFrame.PostActivateQual
         //:      WHERE zqFrame.PostActivateQual.ScopingEntityName != zqFrame.zqFrame.QueryObjectRootEntityName
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQual", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "PostActivateQual", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrame, "PostActivateQual", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:CreateViewFromView( zqFrameRoot, zqFrame )
            CreateViewFromView( zqFrameRoot, zqFrame );
            //:PostActivateRecurs( zqFrame, zqFrameRoot, vResultSet )
            ozqFrame_PostActivateRecurs( zqFrame, zqFrameRoot, vResultSet );
            //:DropView( zqFrameRoot )
            DropView( zqFrameRoot );
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR FIRST zqFrame.SortOrderEntity
            //:      WHERE zqFrame.SortOrderEntity.EntityName != zqFrame.zqFrame.QueryObjectRootEntityName
            RESULT = SetCursorFirstEntity( zqFrame, "SortOrderEntity", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "SortOrderEntity", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 ) )
               { 
                  RESULT = SetCursorNextEntity( zqFrame, "SortOrderEntity", "" );
               } 

            } 

            //:IF RESULT >= zCURSOR_SET
            if ( RESULT >= zCURSOR_SET )
            { 
               //:CreateViewFromView( zqFrameRoot, zqFrame )
               CreateViewFromView( zqFrameRoot, zqFrame );
               //:PostActivateRecurs( zqFrame, zqFrameRoot, vResultSet )
               ozqFrame_PostActivateRecurs( zqFrame, zqFrameRoot, vResultSet );
               //:DropView( zqFrameRoot )
               DropView( zqFrameRoot );
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:nRC = SetCursorNextEntity( vResultSet, RootEntityName, "" )
      nRC = SetCursorNextEntity( vResultSet, RootEntityName, "" );
   } 

   //:END

   //:zqFrame.zqFrame.CountOfReturnedRoots = RootCount
   SetAttributeFromInteger( zqFrame, "zqFrame", "CountOfReturnedRoots", RootCount );

   //:// Order the OI by the root entity if requested.
   //:IF vResultSet != 0
   if ( getView( vResultSet ) != null )
   { 
      //:OrderRequestedEntity( zqFrame, vResultSet, RootEntityName )
      ozqFrame_OrderRequestedEntity( zqFrame, vResultSet, RootEntityName );
   } 

   //:END

   //:// Give an error message if no entries meet the selection criteria and were are performing
   //:// a new Query (that is we're not working with an existing Result Set).
   //:IF RootCount = 0 AND zqFrame.zqFrame.wPostActivateFlag != "Y"
   if ( RootCount == 0 && CompareAttributeToString( zqFrame, "zqFrame", "wPostActivateFlag", "Y" ) != 0 )
   { 
      //:MessageSend( zqFrame, "", "Query",
      //:             "No entries met the selection criteria.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( zqFrame, "", "Query", "No entries met the selection criteria.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -2
      if(8==8)return( -2 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatCSV_FrmResultSet( VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                        VIEW vLOD,
//:                        VIEW vResultSet,
//:                        STRING ( 1 ) szTypeFlag,
//:                        STRING ( 200 ) OutputFileName )

//:   VIEW vQualObject
public int 
ozqFrame_FormatCSV_FrmResultSet( View     zqFrameRoot,
                                 View     vLOD,
                                 View     vResultSet,
                                 String   szTypeFlag,
                                 String   OutputFileName )
{
   zVIEW    vQualObject = new zVIEW( );
   //:VIEW zqFrame     BASED ON LOD  zqFrame
   zVIEW    zqFrame = new zVIEW( );
   //://VIEW zqFrameRoot BASED ON LOD  zqFrame
   //:VIEW zqSumR      BASED ON LOD  zqSumR
   zVIEW    zqSumR = new zVIEW( );
   //:VIEW wXferO      REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 10000 ) OutputLine
   String   OutputLine = null;
   //:STRING ( 10000 ) FormattedValue
   String   FormattedValue = null;
   //:STRING ( 200 )  szLangConv
   String   szLangConv = null;
   //:STRING ( 500 )  szDisplayValue
   String   szDisplayValue = null;
   //:STRING ( 200 )  szMsg
   String   szMsg = null;
   //:STRING ( 32 )   RootEntityName
   String   RootEntityName = null;
   //:STRING ( 32 )   szObjectName
   String   szObjectName = null;
   //:STRING ( 32 )   szMappingEntityName
   String   szMappingEntityName = null;
   //:STRING ( 32 )   szTraceEntityName
   String   szTraceEntityName = null;
   //:STRING ( 3 )    szHierarchicalLevel
   String   szHierarchicalLevel = null;
   //:STRING ( 3 )    szLastHierarchicalLevel
   String   szLastHierarchicalLevel = null;
   //:STRING ( 100 )  szTraceLine
   String   szTraceLine = null;
   //:STRING ( 1 )    szPivotOptionFlag
   String   szPivotOptionFlag = null;
   //:DECIMAL         OperationResult
   double  OperationResult = 0.0;
   //:INTEGER         FilePointer
   int      FilePointer = 0;
   //:INTEGER         NextColumnNumber
   int      NextColumnNumber = 0;
   //:INTEGER         HierarchicalLevel
   int      HierarchicalLevel = 0;
   //:INTEGER         LastHierarchicalLevel
   int      LastHierarchicalLevel = 0;
   //:INTEGER         ColumnCount
   int      ColumnCount = 0;
   //:SHORT           nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   String   szTempString_7 = null;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrameRoot, zLEVEL_TASK );

   //:// Format a Result Set to CSV file OutputFileName..

   //://CreateViewFromView( zqFrameRoot, zqFrame )
   //:NAME VIEW zqFrameRoot "zqFrameRootA"
   SetNameForView( zqFrameRoot, "zqFrameRootA", null, zLEVEL_TASK );
   //:CreateViewFromView( zqFrame, zqFrameRoot )
   CreateViewFromView( zqFrame, zqFrameRoot );
   //:NAME VIEW zqFrame "zqFrameRecursA"
   SetNameForView( zqFrame, "zqFrameRecursA", null, zLEVEL_TASK );

   //:// Open the file.
   //:TraceLineS( "*** Full File Name: ", OutputFileName )
   TraceLineS( "*** Full File Name: ", OutputFileName );
   //:FilePointer = SysOpenFile( zqFrame, OutputFileName, COREFILE_CREATE )
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    FilePointer = m_zGLOBAL6_Operation.SysOpenFile( zqFrame, OutputFileName, COREFILE_CREATE );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF FilePointer < 0
   if ( FilePointer < 0 )
   { 
      //:GetTextConv(szLangConv, zqFrame, "Invalid Directory for writing CSV Query File: ","")
      {
       ZGLOBALE_Operation m_ZGLOBALE_Operation = new ZGLOBALE_Operation( zqFrame );
       {StringBuilder sb_szLangConv;
      if ( szLangConv == null )
         sb_szLangConv = new StringBuilder( 32 );
      else
         sb_szLangConv = new StringBuilder( szLangConv );
             m_ZGLOBALE_Operation.GetTextConv( sb_szLangConv, zqFrame, "Invalid Directory for writing CSV Query File: ", "" );
      szLangConv = sb_szLangConv.toString( );}
       // m_ZGLOBALE_Operation = null;  // permit gc  (unnecessary)
      }
      //:szMsg = szLangConv + FormattedValue
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, FormattedValue, 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( zqFrame, "", "Query", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( zqFrame, "", "Query", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:// Create the Report title line.
   //:OutputLine = zqFrame.zqFrame.ReportTitle 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_OutputLine;
   if ( OutputLine == null )
      sb_OutputLine = new StringBuilder( 32 );
   else
      sb_OutputLine = new StringBuilder( OutputLine );
       GetVariableFromAttribute( sb_OutputLine, mi_lTempInteger_0, 'S', 10001, zqFrame, "zqFrame", "ReportTitle", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   OutputLine = sb_OutputLine.toString( );}
   //:SysWriteLine( zqFrame, FilePointer, OutputLine )
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF zqFrameRoot.zqFrame.wCurrentDisplayFormat = "R"
   if ( CompareAttributeToString( zqFrameRoot, "zqFrame", "wCurrentDisplayFormat", "R" ) == 0 )
   { 
      //:// We're formatting a Detail result set, so initialize the ResultSetDisplay entity..
      //:FOR EACH zqFrameRoot.ResultSetDisplay
      RESULT = SetCursorFirstEntity( zqFrameRoot, "ResultSetDisplay", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:DELETE ENTITY zqFrameRoot.ResultSetDisplay NONE 
         RESULT = DeleteEntity( zqFrameRoot, "ResultSetDisplay", zREPOS_NONE );
         RESULT = SetCursorNextEntity( zqFrameRoot, "ResultSetDisplay", "" );
      } 

      //:END
      //:CREATE ENTITY zqFrameRoot.ResultSetDisplay  
      RESULT = CreateEntity( zqFrameRoot, "ResultSetDisplay", zPOS_AFTER );
      //:CREATE ENTITY zqFrameRoot.ResultSetDisplayRow  
      RESULT = CreateEntity( zqFrameRoot, "ResultSetDisplayRow", zPOS_AFTER );
   } 

   //:END

   //:// Process types differently.
   //:// First check if this is a Pivot table, because a Pivot may also have a Breakpoint.
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.PivotRowEntityFlag = "Y" 
   RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "PivotRowEntityFlag", "Y", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szPivotOptionFlag = "Y"
       {StringBuilder sb_szPivotOptionFlag;
      if ( szPivotOptionFlag == null )
         sb_szPivotOptionFlag = new StringBuilder( 32 );
      else
         sb_szPivotOptionFlag = new StringBuilder( szPivotOptionFlag );
            ZeidonStringCopy( sb_szPivotOptionFlag, 1, 0, "Y", 1, 0, 2 );
      szPivotOptionFlag = sb_szPivotOptionFlag.toString( );}
      //:MessageSend( zqFrame, "zqFramePTNS", "Query", "Pivot Tables are not suported for generating CSV files.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( zqFrame, "zqFramePTNS", "Query", "Pivot Tables are not suported for generating CSV files.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END

   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.BreakpointType != "" 
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointType", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:// Breakpoint Queries have their own formatting routine,
      //:GET VIEW zqSumR NAMED "zqSumR"
      RESULT = GetViewByName( zqSumR, "zqSumR", zqFrameRoot, zLEVEL_TASK );
      //:FormatCSV_BreakPtsFile( zqSumR, FilePointer )
      {
       zqSumR_Object m_zqSumR_Object = new zqSumR_Object( zqSumR );
       m_zqSumR_Object.ozqSumR_FormatCSV_BreakPtsFile( zqSumR, FilePointer );
       // m_zqSumR_Object = null;  // permit gc  (unnecessary)
      }
      //:ELSE    
   } 
   else
   { 

      //:// For regular queries, build the ParentEntity subobject for formatting attributes flagged for display
      //:// and set the RootEntityName.
      //:// For Pivot Queries, the ParentEntity subobject already exists, so just set the RootEntityName.
      //:IF szPivotOptionFlag = "Y"
      if ( ZeidonStringCompare( szPivotOptionFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
      { 
         //:RootEntityName = "ResultRow"
          {StringBuilder sb_RootEntityName;
         if ( RootEntityName == null )
            sb_RootEntityName = new StringBuilder( 32 );
         else
            sb_RootEntityName = new StringBuilder( RootEntityName );
                  ZeidonStringCopy( sb_RootEntityName, 1, 0, "ResultRow", 1, 0, 33 );
         RootEntityName = sb_RootEntityName.toString( );}
         //:ELSE
      } 
      else
      { 
         //:RootEntityName = zqFrame.zqFrame.QueryObjectRootEntityName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_RootEntityName;
         if ( RootEntityName == null )
            sb_RootEntityName = new StringBuilder( 32 );
         else
            sb_RootEntityName = new StringBuilder( RootEntityName );
                   GetVariableFromAttribute( sb_RootEntityName, mi_lTempInteger_1, 'S', 33, zqFrame, "zqFrame", "QueryObjectRootEntityName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         RootEntityName = sb_RootEntityName.toString( );}
         //:FormatDisplay( zqFrame, vLOD )
         ozqFrame_FormatDisplay( zqFrame, vLOD );
      } 

      //:END

      //:// If there are any operations (COUNT, SUM, etc.) on the root entity, go to create lines for
      //:// both the prompt and the operation value, unless this is a Pivot.
      //:SET CURSOR FIRST zqFrame.GeneralParameter 
      //:           WHERE zqFrame.GeneralParameter.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName 
      //:             AND zqFrame.GeneralParameter.ListDisplayFlag = "Y"
      //:             AND ( zqFrame.GeneralParameter.SubgroupSearchType = "COUNT"
      //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "NNULL"
      //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "MIN"
      //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "MAX"
      //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "SUM"
      //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "AVG" )
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) != 0 ||
                 CompareAttributeToString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" ) != 0 || ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "COUNT" ) != 0 &&
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "NNULL" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "MIN" ) != 0 &&
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "MAX" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "SUM" ) != 0 &&
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "AVG" ) != 0 ) ) )
         { 
            RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET 
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// First format the Title line.
         //:FOR EACH zqFrame.GeneralParameter
         //:        WHERE zqFrame.GeneralParameter.SubgroupSearchType != ""
         //:          AND zqFrame.GeneralParameter.ListDisplayFlag = "Y"
         //:          AND zqFrame.GeneralParameter.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName
         RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" ) == 0 &&
                 CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 )
            { 
               //:       
               //:szDisplayValue = zqFrame.GeneralParameter.Title
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szDisplayValue;
               if ( szDisplayValue == null )
                  sb_szDisplayValue = new StringBuilder( 32 );
               else
                  sb_szDisplayValue = new StringBuilder( szDisplayValue );
                               GetVariableFromAttribute( sb_szDisplayValue, mi_lTempInteger_2, 'S', 501, zqFrame, "GeneralParameter", "Title", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szDisplayValue = sb_szDisplayValue.toString( );}
               //:OutputLine = OutputLine + QUOTES + zqFrame.GeneralParameter.Title + QUOTES + ","
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_3, 'S', 10001, zqFrame, "GeneralParameter", "Title", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, szTempString_0, 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, ",", 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
            } 

            RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         } 

         //:END
         //:SysWriteLine( zqFrame, FilePointer, OutputLine )
         try
         {
             {
          zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
          m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
          // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }
         //:OutputLine = ""
          {StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                  ZeidonStringCopy( sb_OutputLine, 1, 0, "", 1, 0, 10001 );
         OutputLine = sb_OutputLine.toString( );}

         //:// Next format the values line.
         //:FormatCSV_TotalsLine( zqFrame, OutputLine, vResultSet )
          {StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                  ozqFrame_FormatCSV_TotalsLine( zqFrame, sb_OutputLine, vResultSet );
         OutputLine = sb_OutputLine.toString( );}
         //:SysWriteLine( zqFrame, FilePointer, OutputLine )
         try
         {
             {
          zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
          m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
          // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }
      } 

      //:END

      //:// Create the single title line for the displayed attributes, also initializing the file information.
      //:OutputLine = ""
       {StringBuilder sb_OutputLine;
      if ( OutputLine == null )
         sb_OutputLine = new StringBuilder( 32 );
      else
         sb_OutputLine = new StringBuilder( OutputLine );
            ZeidonStringCopy( sb_OutputLine, 1, 0, "", 1, 0, 10001 );
      OutputLine = sb_OutputLine.toString( );}
      //:NextColumnNumber = 0
      NextColumnNumber = 0;
      //:IF szTypeFlag = "F"
      if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
      { 
         //:// Format the title for Full CSV file and build the CSV_OutputLineItem entries for sorting and display column values.
         //:FOR EACH zqFrame.CSV_OutputLineItem
         RESULT = SetCursorFirstEntity( zqFrame, "CSV_OutputLineItem", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:DELETE ENTITY zqFrame.CSV_OutputLineItem NONE 
            RESULT = DeleteEntity( zqFrame, "CSV_OutputLineItem", zREPOS_NONE );
            RESULT = SetCursorNextEntity( zqFrame, "CSV_OutputLineItem", "" );
         } 

         //:END
         //:FormatCSV_TitleRecurs( zqFrame, zqFrameRoot, OutputLine, NextColumnNumber, szTypeFlag, 0 )
         {MutableInt mi_NextColumnNumber = new MutableInt( NextColumnNumber );
         StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                   ozqFrame_FormatCSV_TitleRecurs( zqFrame, zqFrameRoot, sb_OutputLine, mi_NextColumnNumber, szTypeFlag, 0 );
         NextColumnNumber = mi_NextColumnNumber.intValue( );
         OutputLine = sb_OutputLine.toString( );}
         //:OrderEntityForView( zqFrame, "CSV_OutputLineItem", "SortOrder A" )
         OrderEntityForView( zqFrame, "CSV_OutputLineItem", "SortOrder A" );
         //:FormatCSV_FullOutput( zqFrame, OutputLine )
         {StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                   ozqFrame_FormatCSV_FullOutput( zqFrame, sb_OutputLine );
         OutputLine = sb_OutputLine.toString( );}
         //:ELSE
      } 
      else
      { 
         //:// Format the title for Sparse CSV file.
         //:FormatCSV_TitleRecurs( zqFrame, zqFrameRoot, OutputLine, NextColumnNumber, szTypeFlag, 0 )
         {MutableInt mi_NextColumnNumber = new MutableInt( NextColumnNumber );
         StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                   ozqFrame_FormatCSV_TitleRecurs( zqFrame, zqFrameRoot, sb_OutputLine, mi_NextColumnNumber, szTypeFlag, 0 );
         NextColumnNumber = mi_NextColumnNumber.intValue( );
         OutputLine = sb_OutputLine.toString( );}
      } 

      //:END

      //:// Loop through all entities in the Result Set, formatting to the output line those attributes
      //:// which have been marked for display. The zqFrame.ParentEntity subobject has been set up with those
      //:// attributes to be mapped. 
      //:nRC = SetCursorFirstEntity( vResultSet, RootEntityName, "" )
      nRC = SetCursorFirstEntity( vResultSet, RootEntityName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //:// Write out the last line and null output line and CSV_OutputLineItem entries.
         //:IF OutputLine != ""
         if ( ZeidonStringCompare( OutputLine, 1, 0, "", 1, 0, 10001 ) != 0 )
         { 
            //:IF szTypeFlag = "F"
            if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
            { 
               //:FormatCSV_FullOutput( zqFrame, OutputLine )
               {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                               ozqFrame_FormatCSV_FullOutput( zqFrame, sb_OutputLine );
               OutputLine = sb_OutputLine.toString( );}
               //:FOR EACH zqFrame.CSV_OutputLineItem
               RESULT = SetCursorFirstEntity( zqFrame, "CSV_OutputLineItem", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:zqFrame.CSV_OutputLineItem.Value = ""
                  SetAttributeFromString( zqFrame, "CSV_OutputLineItem", "Value", "" );
                  RESULT = SetCursorNextEntity( zqFrame, "CSV_OutputLineItem", "" );
               } 

               //:END
               //://szHierarchicalLevel = HierarchicalLevel
               //://szLastHierarchicalLevel = LastHierarchicalLevel
               //://szTraceLine = szLastHierarchicalLevel + " " + szHierarchicalLevel + " " + OutputLine
               //://IssueError( zqFrame,0,0, szTraceLine )
               //:SysWriteLine( zqFrame, FilePointer, OutputLine )
               try
               {
                   {
                zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
                m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
                // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }
               //:OutputLine = ""
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringCopy( sb_OutputLine, 1, 0, "", 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
               //:ELSE
            } 
            else
            { 
               //:SysWriteLine( zqFrame, FilePointer, OutputLine )
               try
               {
                   {
                zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
                m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
                // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
               };
               }
               catch ( Exception e )
               {
                  throw ZeidonException.wrapException( e );
               }
               //:OutputLine = ""
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringCopy( sb_OutputLine, 1, 0, "", 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
            } 

            //:END
            //:IF zqFrame.zqFrame.wCurrentDisplayFormat = "R"
            if ( CompareAttributeToString( zqFrame, "zqFrame", "wCurrentDisplayFormat", "R" ) == 0 )
            { 
               //:CREATE ENTITY zqFrameRoot.ResultSetDisplayRow
               RESULT = CreateEntity( zqFrameRoot, "ResultSetDisplayRow", zPOS_AFTER );
               //:ColumnCount = 1
               ColumnCount = 1;
            } 

            //:END
         } 

         //:END

         //:// Add root data to output.
         //:// Note that all the attributes to be mapped with the Root entity are listed as QueryAttribute entities,
         //:// even if they come from entities that are not 1-to-1 children under the Root.
         //:FOR EACH zqFrame.QueryAttribute   
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.QueryAttribute.GroupOperation != ""    // Not sure what this is (DonC 5/28/2021)
            if ( CompareAttributeToString( zqFrame, "QueryAttribute", "GroupOperation", "" ) != 0 )
            { 
               //:ComputeOperationValue( zqFrame,
               //:                       vResultSet,
               //:                       OperationResult,
               //:                       zqFrame.QueryAttribute.GroupOperation,
               //:                       zqFrame.QueryAttribute.GroupOperationEntityName,
               //:                       zqFrame.QueryAttribute.GroupOperationAttributeName,
               //:                       zqFrame.QueryAttribute.GroupOperationScopingEntityName )
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, zqFrame, "QueryAttribute", "GroupOperation" );
               szTempString_1 = sb_szTempString_1.toString( );}
               {StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetStringFromAttribute( sb_szTempString_2, zqFrame, "QueryAttribute", "GroupOperationEntityName" );
               szTempString_2 = sb_szTempString_2.toString( );}
               {StringBuilder sb_szTempString_3;
               if ( szTempString_3 == null )
                  sb_szTempString_3 = new StringBuilder( 32 );
               else
                  sb_szTempString_3 = new StringBuilder( szTempString_3 );
                               GetStringFromAttribute( sb_szTempString_3, zqFrame, "QueryAttribute", "GroupOperationAttributeName" );
               szTempString_3 = sb_szTempString_3.toString( );}
               {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetStringFromAttribute( sb_szTempString_4, zqFrame, "QueryAttribute", "GroupOperationScopingEntityName" );
               szTempString_4 = sb_szTempString_4.toString( );}
               {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                               ozqFrame_ComputeOperationValue( zqFrame, vResultSet, md_OperationResult, szTempString_1, szTempString_2, szTempString_3, szTempString_4 );
               OperationResult = md_OperationResult.doubleValue( );}
               //:wXferO.QueryValues.WorkRevenue = OperationResult
               SetAttributeFromDecimal( wXferO, "QueryValues", "WorkRevenue", OperationResult );
               //:FormattedValue = wXferO.QueryValues.WorkRevenue
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_FormattedValue;
               if ( FormattedValue == null )
                  sb_FormattedValue = new StringBuilder( 32 );
               else
                  sb_FormattedValue = new StringBuilder( FormattedValue );
                               GetVariableFromAttribute( sb_FormattedValue, mi_lTempInteger_4, 'S', 10001, wXferO, "QueryValues", "WorkRevenue", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               FormattedValue = sb_FormattedValue.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szMappingEntityName = zqFrame.QueryAttribute.MappingEntityName 
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
               StringBuilder sb_szMappingEntityName;
               if ( szMappingEntityName == null )
                  sb_szMappingEntityName = new StringBuilder( 32 );
               else
                  sb_szMappingEntityName = new StringBuilder( szMappingEntityName );
                               GetVariableFromAttribute( sb_szMappingEntityName, mi_lTempInteger_5, 'S', 33, zqFrame, "QueryAttribute", "MappingEntityName", "", 0 );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );
               szMappingEntityName = sb_szMappingEntityName.toString( );}
               //:IF szMappingEntityName = ""
               if ( ZeidonStringCompare( szMappingEntityName, 1, 0, "", 1, 0, 33 ) == 0 )
               { 
                  //:FormattedValue = ""
                   {StringBuilder sb_FormattedValue;
                  if ( FormattedValue == null )
                     sb_FormattedValue = new StringBuilder( 32 );
                  else
                     sb_FormattedValue = new StringBuilder( FormattedValue );
                                    ZeidonStringCopy( sb_FormattedValue, 1, 0, "", 1, 0, 10001 );
                  FormattedValue = sb_FormattedValue.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:nRC = CheckExistenceOfEntity( vResultSet, szMappingEntityName )
                  nRC = CheckExistenceOfEntity( vResultSet, szMappingEntityName );
                  //:IF nRC < zCURSOR_SET
                  if ( nRC < zCURSOR_SET )
                  { 
                     //:FormattedValue = ""
                      {StringBuilder sb_FormattedValue;
                     if ( FormattedValue == null )
                        sb_FormattedValue = new StringBuilder( 32 );
                     else
                        sb_FormattedValue = new StringBuilder( FormattedValue );
                                          ZeidonStringCopy( sb_FormattedValue, 1, 0, "", 1, 0, 10001 );
                     FormattedValue = sb_FormattedValue.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:// Format the attribute through the Default Context filter, unless value is null.
                     //:nRC = CompareAttributeToString( vResultSet,
                     //:                                szMappingEntityName,
                     //:                                zqFrame.QueryAttribute.AttributeName, "" )
                     {StringBuilder sb_szTempString_5;
                     if ( szTempString_5 == null )
                        sb_szTempString_5 = new StringBuilder( 32 );
                     else
                        sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                           GetStringFromAttribute( sb_szTempString_5, zqFrame, "QueryAttribute", "AttributeName" );
                     szTempString_5 = sb_szTempString_5.toString( );}
                     nRC = CompareAttributeToString( vResultSet, szMappingEntityName, szTempString_5, "" );
                     //:IF nRC = 0
                     if ( nRC == 0 )
                     { 
                        //:FormattedValue = ""
                         {StringBuilder sb_FormattedValue;
                        if ( FormattedValue == null )
                           sb_FormattedValue = new StringBuilder( 32 );
                        else
                           sb_FormattedValue = new StringBuilder( FormattedValue );
                                                ZeidonStringCopy( sb_FormattedValue, 1, 0, "", 1, 0, 10001 );
                        FormattedValue = sb_FormattedValue.toString( );}
                        //:ElSE
                     } 
                     else
                     { 
                        //:GetStrFromAttrByContext( FormattedValue, 10000,
                        //:                         vResultSet,
                        //:                         szMappingEntityName,
                        //:                         zqFrame.QueryAttribute.AttributeName, "" )  // Use default context.
                        {StringBuilder sb_szTempString_6;
                        if ( szTempString_6 == null )
                           sb_szTempString_6 = new StringBuilder( 32 );
                        else
                           sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                                 GetStringFromAttribute( sb_szTempString_6, zqFrame, "QueryAttribute", "AttributeName" );
                        szTempString_6 = sb_szTempString_6.toString( );}
                        {
                         ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( vResultSet );
                         {StringBuilder sb_FormattedValue;
                        if ( FormattedValue == null )
                           sb_FormattedValue = new StringBuilder( 32 );
                        else
                           sb_FormattedValue = new StringBuilder( FormattedValue );
                                                 m_ZGLOBAL1_Operation.GetStrFromAttrByContext( sb_FormattedValue, 10000, vResultSet, szMappingEntityName, szTempString_6, "" );
                        FormattedValue = sb_FormattedValue.toString( );}
                         // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
                        }
                     } 

                     //:END
                  } 

                  //:END
               } 

               //:END
            } 

            //:END

            //:IF szTypeFlag = "F"
            if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
            { 
               //:IF zqFrame.QueryAttribute.GroupOperation = "" 
               if ( CompareAttributeToString( zqFrame, "QueryAttribute", "GroupOperation", "" ) == 0 )
               { 
                  //:SET CURSOR FIRST zqFrame.CSV_OutputLineItem 
                  //:           WHERE zqFrame.CSV_OutputLineItem.EntityName    = zqFrame.QueryAttribute.MappingEntityName 
                  //:             AND zqFrame.CSV_OutputLineItem.AttributeName = zqFrame.QueryAttribute.AttributeName 
                  RESULT = SetCursorFirstEntity( zqFrame, "CSV_OutputLineItem", "" );
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "CSV_OutputLineItem", "EntityName", zqFrame, "QueryAttribute", "MappingEntityName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrame, "CSV_OutputLineItem", "AttributeName", zqFrame, "QueryAttribute", "AttributeName" ) != 0 ) )
                     { 
                        RESULT = SetCursorNextEntity( zqFrame, "CSV_OutputLineItem", "" );
                     } 

                  } 

                  //:ELSE
               } 
               else
               { 
                  //:SET CURSOR FIRST zqFrame.CSV_OutputLineItem 
                  //:           WHERE zqFrame.CSV_OutputLineItem.EntityName    = zqFrame.QueryAttribute.GroupOperationEntityName 
                  //:             AND zqFrame.CSV_OutputLineItem.AttributeName = zqFrame.QueryAttribute.GroupOperationAttributeName 
                  RESULT = SetCursorFirstEntity( zqFrame, "CSV_OutputLineItem", "" );
                  if ( RESULT > zCURSOR_UNCHANGED )
                  { 
                     while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "CSV_OutputLineItem", "EntityName", zqFrame, "QueryAttribute", "GroupOperationEntityName" ) != 0 ||
                        CompareAttributeToAttribute( zqFrame, "CSV_OutputLineItem", "AttributeName", zqFrame, "QueryAttribute", "GroupOperationAttributeName" ) != 0 ) )
                     { 
                        RESULT = SetCursorNextEntity( zqFrame, "CSV_OutputLineItem", "" );
                     } 

                  } 

               } 

               //:END
               //:zqFrame.CSV_OutputLineItem.Value = FormattedValue
               SetAttributeFromString( zqFrame, "CSV_OutputLineItem", "Value", FormattedValue );
               //:OutputLine = "W"   // Indicate OutputLine is to be formatted and written. We don't check later for a "W", for a not-null.
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringCopy( sb_OutputLine, 1, 0, "W", 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
               //:ELSE
            } 
            else
            { 
               //:OutputLine = OutputLine + QUOTES + FormattedValue + QUOTES + ","
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, FormattedValue, 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
                {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                              ZeidonStringConcat( sb_OutputLine, 1, 0, ",", 1, 0, 10001 );
               OutputLine = sb_OutputLine.toString( );}
               //:AddColumnAttribute( zqFrameRoot, FormattedValue, szMappingEntityName, zqFrame.QueryAttribute.AttributeName )
               {StringBuilder sb_szTempString_7;
               if ( szTempString_7 == null )
                  sb_szTempString_7 = new StringBuilder( 32 );
               else
                  sb_szTempString_7 = new StringBuilder( szTempString_7 );
                               GetStringFromAttribute( sb_szTempString_7, zqFrame, "QueryAttribute", "AttributeName" );
               szTempString_7 = sb_szTempString_7.toString( );}
               ozqFrame_AddColumnAttribute( zqFrameRoot, FormattedValue, szMappingEntityName, szTempString_7 );
            } 

            RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
            //:END
         } 

         //:END
         //:IF szTypeFlag = "F"
         if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
         { 
            //:// Format the current data to OutputLine.
            //:FormatCSV_FullOutput( zqFrame, OutputLine )
            {StringBuilder sb_OutputLine;
            if ( OutputLine == null )
               sb_OutputLine = new StringBuilder( 32 );
            else
               sb_OutputLine = new StringBuilder( OutputLine );
                         ozqFrame_FormatCSV_FullOutput( zqFrame, sb_OutputLine );
            OutputLine = sb_OutputLine.toString( );}
            //:ELSE
         } 
         else
         { 
            //:zqFrame.ParentEntity.CurrentOutputLine = OutputLine
            SetAttributeFromString( zqFrame, "ParentEntity", "CurrentOutputLine", OutputLine );
         } 

         //:END

         //:// Add subentity data using recursive structure.
         //:SET CURSOR FIRST zqFrame.zqFrame     // Make sure we're reset to top of ParentEntity subobject. 
         RESULT = SetCursorFirstEntity( zqFrame, "zqFrame", "" );
         //:NAME VIEW  zqFrame  "zqFrameRecurs"
         SetNameForView( zqFrame, "zqFrameRecurs", null, zLEVEL_TASK );
         //:SetViewToSubobject( zqFrame, "ChildEntity" )
         SetViewToSubobject( zqFrame, "ChildEntity" );
         //://szTraceEntityName = zqFrame.ParentEntity.EntityName 
         //://TraceLineS( "### Start Entity: ", szTraceEntityName )
         //:HierarchicalLevel = 1
         HierarchicalLevel = 1;
         //:LastHierarchicalLevel = 1
         LastHierarchicalLevel = 1;
         //:FormatCSV_EntityRecurs( zqFrame,
         //:                        zqFrameRoot,
         //:                        vResultSet,
         //:                        OutputLine,
         //:                        szTypeFlag,
         //:                        FilePointer,
         //:                        HierarchicalLevel,
         //:                        LastHierarchicalLevel,
         //:                        RootEntityName,
         //:                        ColumnCount )
         {MutableInt mi_ColumnCount = new MutableInt( ColumnCount );
         MutableInt mi_LastHierarchicalLevel = new MutableInt( LastHierarchicalLevel );
         MutableInt mi_HierarchicalLevel = new MutableInt( HierarchicalLevel );
         StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                   ozqFrame_FormatCSV_EntityRecurs( zqFrame, zqFrameRoot, vResultSet, sb_OutputLine, szTypeFlag, FilePointer, mi_HierarchicalLevel, mi_LastHierarchicalLevel, RootEntityName, mi_ColumnCount );
         ColumnCount = mi_ColumnCount.intValue( );
         LastHierarchicalLevel = mi_LastHierarchicalLevel.intValue( );
         HierarchicalLevel = mi_HierarchicalLevel.intValue( );
         OutputLine = sb_OutputLine.toString( );}
         //:ResetViewFromSubobject( zqFrame )
         ResetViewFromSubobject( zqFrame );

         //:// If this is a Breakpoint object with summation, etc. entries, then process any sums here.
         //:SET CURSOR FIRST zqFrame.GeneralParameter 
         //:           WHERE zqFrame.GeneralParameter.BreakpointSummationType != "" 
         RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointSummationType", "" ) == 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:SysWriteLine( zqFrame, FilePointer, OutputLine ) // Write out the last output line.
            try
            {
                {
             zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
             m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
             // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }
            //:OutputLine = OutputLine + "Break"
             {StringBuilder sb_OutputLine;
            if ( OutputLine == null )
               sb_OutputLine = new StringBuilder( 32 );
            else
               sb_OutputLine = new StringBuilder( OutputLine );
                        ZeidonStringConcat( sb_OutputLine, 1, 0, "Break", 1, 0, 10001 );
            OutputLine = sb_OutputLine.toString( );}
         } 

         //:END

         //:nRC = SetCursorNextEntity( vResultSet, RootEntityName, "" )
         nRC = SetCursorNextEntity( vResultSet, RootEntityName, "" );
      } 

      //:END

      //:// If this is a Breakpoint object with summation, etc. entries, then process any final breakpoint values here.
      //:SET CURSOR FIRST zqFrame.GeneralParameter 
      //:           WHERE zqFrame.GeneralParameter.BreakpointSummationType != "" 
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointSummationType", "" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:SysWriteLine( zqFrame, FilePointer, OutputLine ) // Write out the last output line.
         try
         {
             {
          zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
          m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
          // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }
         //:OutputLine = OutputLine + "FinalBreak"
          {StringBuilder sb_OutputLine;
         if ( OutputLine == null )
            sb_OutputLine = new StringBuilder( 32 );
         else
            sb_OutputLine = new StringBuilder( OutputLine );
                  ZeidonStringConcat( sb_OutputLine, 1, 0, "FinalBreak", 1, 0, 10001 );
         OutputLine = sb_OutputLine.toString( );}
         //:SysWriteLine( zqFrame, FilePointer, OutputLine )
         try
         {
             {
          zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
          m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
          // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
         };
         }
         catch ( Exception e )
         {
            throw ZeidonException.wrapException( e );
         }
         //:ELSE
      } 
      else
      { 
         //:// Write out any final data.
         //:IF OutputLine != ""
         if ( ZeidonStringCompare( OutputLine, 1, 0, "", 1, 0, 10001 ) != 0 )
         { 
            //:IF szTypeFlag = "F"
            if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
            { 
               //:FormatCSV_FullOutput( zqFrame, OutputLine )
               {StringBuilder sb_OutputLine;
               if ( OutputLine == null )
                  sb_OutputLine = new StringBuilder( 32 );
               else
                  sb_OutputLine = new StringBuilder( OutputLine );
                               ozqFrame_FormatCSV_FullOutput( zqFrame, sb_OutputLine );
               OutputLine = sb_OutputLine.toString( );}
            } 

            //:END
            //:SysWriteLine( zqFrame, FilePointer, OutputLine )
            try
            {
                {
             zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
             m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, OutputLine );
             // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
            };
            }
            catch ( Exception e )
            {
               throw ZeidonException.wrapException( e );
            }
         } 

         //:END
      } 

      //:END
   } 

   //:END

   //:SysCloseFile( zqFrame, FilePointer, 0 )
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    m_zGLOBAL6_Operation.SysCloseFile( zqFrame, FilePointer, 0 );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:DropView( zqFrame )
   DropView( zqFrame );
   return( 0 );
//    //DropView( zqFrameRoot )
// END
} 


//:TRANSFORMATION OPERATION
//:ComputeOperationVDate( VIEW zqFrame BASED ON LOD zqFrame,
//:                       VIEW vResultSetOrig,
//:                       STRING ( 50 ) ReturnedValue,
//:                       STRING ( 32 ) OperationName,
//:                       STRING ( 32 ) szEntityName,
//:                       STRING ( 32 ) szAttributeName,
//:                       STRING ( 32 ) szScopingEntityName )

//:   // Perform the group operation of:
//:   //   COUNT
//:   //   NNULL
//:   //   MAX
//:   //   MIN
//:   // on the Entity/Attribute passed and return the value as a decimal.
//:   // Note that SUM and AVG are ignored.

//:   VIEW vResultSet
public int 
ozqFrame_ComputeOperationVDate( View     zqFrame,
                                View     vResultSetOrig,
                                StringBuilder   ReturnedValue,
                                String   OperationName,
                                String   szEntityName,
                                String   szAttributeName,
                                String   szScopingEntityName )
{
   zVIEW    vResultSet = new zVIEW( );
   //:STRING ( 50 ) szMaxMin
   String   szMaxMin = null;
   //:STRING ( 50 ) AttributeValue
   String   AttributeValue = null;
   //:INTEGER Count
   int      Count = 0;
   //:SHORT   nRC
   int      nRC = 0;


   //:CreateViewFromView( vResultSet, vResultSetOrig )
   CreateViewFromView( vResultSet, vResultSetOrig );
   //:IF OperationName = "MAX"
   if ( ZeidonStringCompare( OperationName, 1, 0, "MAX", 1, 0, 33 ) == 0 )
   { 
      //:nRC = SetCursorFirstEntity( vResultSet,
      //:                            szEntityName,
      //:                            szScopingEntityName )
      nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
      //:IF nRC >= zCURSOR_SET
      if ( nRC >= zCURSOR_SET )
      { 
         //:GetStringFromAttribute( szMaxMin,
         //:                        vResultSet,
         //:                        szEntityName,
         //:                        szAttributeName )
         {StringBuilder sb_szMaxMin;
         if ( szMaxMin == null )
            sb_szMaxMin = new StringBuilder( 32 );
         else
            sb_szMaxMin = new StringBuilder( szMaxMin );
                   GetStringFromAttribute( sb_szMaxMin, vResultSet, szEntityName, szAttributeName );
         szMaxMin = sb_szMaxMin.toString( );}
         //:LOOP WHILE nRC >= zCURSOR_SET
         while ( nRC >= zCURSOR_SET )
         { 
            //:nRC = SetCursorNextEntity( vResultSet,
            //:                           szEntityName,
            //:                           szScopingEntityName )
            nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
            //:IF nRC >= zCURSOR_SET
            if ( nRC >= zCURSOR_SET )
            { 
               //:GetStringFromAttribute( AttributeValue,
               //:                        vResultSet,
               //:                        szEntityName,
               //:                        szAttributeName )
               {StringBuilder sb_AttributeValue;
               if ( AttributeValue == null )
                  sb_AttributeValue = new StringBuilder( 32 );
               else
                  sb_AttributeValue = new StringBuilder( AttributeValue );
                               GetStringFromAttribute( sb_AttributeValue, vResultSet, szEntityName, szAttributeName );
               AttributeValue = sb_AttributeValue.toString( );}

               //:IF AttributeValue > szMaxMin
               if ( ZeidonStringCompare( AttributeValue, 1, 0, szMaxMin, 1, 0, 51 ) < 0 )
               { 
                  //:szMaxMin = AttributeValue
                   {StringBuilder sb_szMaxMin;
                  if ( szMaxMin == null )
                     sb_szMaxMin = new StringBuilder( 32 );
                  else
                     sb_szMaxMin = new StringBuilder( szMaxMin );
                                    ZeidonStringCopy( sb_szMaxMin, 1, 0, AttributeValue, 1, 0, 51 );
                  szMaxMin = sb_szMaxMin.toString( );}
               } 

               //:END
            } 

            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:szMaxMin = ""
          {StringBuilder sb_szMaxMin;
         if ( szMaxMin == null )
            sb_szMaxMin = new StringBuilder( 32 );
         else
            sb_szMaxMin = new StringBuilder( szMaxMin );
                  ZeidonStringCopy( sb_szMaxMin, 1, 0, "", 1, 0, 51 );
         szMaxMin = sb_szMaxMin.toString( );}
      } 

      //:END
      //:ReturnedValue = szMaxMin
      ZeidonStringCopy( ReturnedValue, 1, 0, szMaxMin, 1, 0, 51 );
      //:ELSE
   } 
   else
   { 
      //:IF OperationName = "MIN"
      if ( ZeidonStringCompare( OperationName, 1, 0, "MIN", 1, 0, 33 ) == 0 )
      { 
         //:nRC = SetCursorFirstEntity( vResultSet,
         //:                         szEntityName,
         //:                         szScopingEntityName )
         nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
         //:IF nRC >= zCURSOR_SET
         if ( nRC >= zCURSOR_SET )
         { 
            //:GetStringFromAttribute( szMaxMin,
            //:                     vResultSet,
            //:                     szEntityName,
            //:                     szAttributeName )
            {StringBuilder sb_szMaxMin;
            if ( szMaxMin == null )
               sb_szMaxMin = new StringBuilder( 32 );
            else
               sb_szMaxMin = new StringBuilder( szMaxMin );
                         GetStringFromAttribute( sb_szMaxMin, vResultSet, szEntityName, szAttributeName );
            szMaxMin = sb_szMaxMin.toString( );}
            //:LOOP WHILE nRC >= zCURSOR_SET
            while ( nRC >= zCURSOR_SET )
            { 
               //:nRC = SetCursorNextEntity( vResultSet,
               //:                        szEntityName,
               //:                        szScopingEntityName )
               nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
               //:IF nRC >= zCURSOR_SET
               if ( nRC >= zCURSOR_SET )
               { 
                  //:GetStringFromAttribute( AttributeValue,
                  //:                     vResultSet,
                  //:                     szEntityName,
                  //:                     szAttributeName )
                  {StringBuilder sb_AttributeValue;
                  if ( AttributeValue == null )
                     sb_AttributeValue = new StringBuilder( 32 );
                  else
                     sb_AttributeValue = new StringBuilder( AttributeValue );
                                     GetStringFromAttribute( sb_AttributeValue, vResultSet, szEntityName, szAttributeName );
                  AttributeValue = sb_AttributeValue.toString( );}

                  //:IF AttributeValue < szMaxMin
                  if ( ZeidonStringCompare( AttributeValue, 1, 0, szMaxMin, 1, 0, 51 ) > 0 )
                  { 
                     //:szMaxMin = AttributeValue
                      {StringBuilder sb_szMaxMin;
                     if ( szMaxMin == null )
                        sb_szMaxMin = new StringBuilder( 32 );
                     else
                        sb_szMaxMin = new StringBuilder( szMaxMin );
                                          ZeidonStringCopy( sb_szMaxMin, 1, 0, AttributeValue, 1, 0, 51 );
                     szMaxMin = sb_szMaxMin.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:szMaxMin = ""
             {StringBuilder sb_szMaxMin;
            if ( szMaxMin == null )
               sb_szMaxMin = new StringBuilder( 32 );
            else
               sb_szMaxMin = new StringBuilder( szMaxMin );
                        ZeidonStringCopy( sb_szMaxMin, 1, 0, "", 1, 0, 51 );
            szMaxMin = sb_szMaxMin.toString( );}
         } 

         //:END
         //:ReturnedValue = szMaxMin
         ZeidonStringCopy( ReturnedValue, 1, 0, szMaxMin, 1, 0, 51 );
         //:ELSE
      } 
      else
      { 
         //:IF OperationName = "COUNT"
         if ( ZeidonStringCompare( OperationName, 1, 0, "COUNT", 1, 0, 33 ) == 0 )
         { 
            //:Count = 0
            Count = 0;
            //:nRC = SetCursorFirstEntity( vResultSet,
            //:                      szEntityName,
            //:                      szScopingEntityName )
            nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
            //:LOOP WHILE nRC >= zCURSOR_SET
            while ( nRC >= zCURSOR_SET )
            { 
               //:Count = Count + 1
               Count = Count + 1;
               //:nRC = SetCursorNextEntity( vResultSet,
               //:                     szEntityName,
               //:                     szScopingEntityName )
               nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
            } 

            //:END
            //:ReturnedValue = Count
            ZeidonStringConvertFromNumber( ReturnedValue, 1, 0, 50, Count, (double) 0.0, "I" );
            //:ELSE
         } 
         else
         { 
            //:IF OperationName = "NNULL"
            if ( ZeidonStringCompare( OperationName, 1, 0, "NNULL", 1, 0, 33 ) == 0 )
            { 
               //:Count = 0
               Count = 0;
               //:nRC = SetCursorFirstEntity( vResultSet,
               //:                   szEntityName,
               //:                   szScopingEntityName )
               nRC = SetCursorFirstEntity( vResultSet, szEntityName, szScopingEntityName );
               //:LOOP WHILE nRC >= zCURSOR_SET
               while ( nRC >= zCURSOR_SET )
               { 
                  //:nRC = CompareAttributeToString( vResultSet,
                  //:                       szEntityName,
                  //:                       szAttributeName,
                  //:                       "" )
                  nRC = CompareAttributeToString( vResultSet, szEntityName, szAttributeName, "" );
                  //:IF nRC != 0
                  if ( nRC != 0 )
                  { 
                     //:Count = Count + 1
                     Count = Count + 1;
                  } 

                  //:END
                  //:nRC = SetCursorNextEntity( vResultSet,
                  //:                  szEntityName,
                  //:                  szScopingEntityName )
                  nRC = SetCursorNextEntity( vResultSet, szEntityName, szScopingEntityName );
               } 

               //:END
               //:ReturnedValue = Count
               ZeidonStringConvertFromNumber( ReturnedValue, 1, 0, 50, Count, (double) 0.0, "I" );
               //:ELSE
            } 
            else
            { 
               //:IF OperationName = "SUM" OR OperationName = "AVG"
               if ( ZeidonStringCompare( OperationName, 1, 0, "SUM", 1, 0, 33 ) == 0 || ZeidonStringCompare( OperationName, 1, 0, "AVG", 1, 0, 33 ) == 0 )
               { 
                  //:ReturnedValue = ""
                  ZeidonStringCopy( ReturnedValue, 1, 0, "", 1, 0, 51 );
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:DropView( vResultSet )
   DropView( vResultSet );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:fnEvaluateOperatorDec( VIEW    zqFrame BASED ON LOD zqFrame,
//:                       DECIMAL OperationResult )

//:   DECIMAL       TargetValue
public int 
ozqFrame_fnEvaluateOperatorDec( View     zqFrame,
                                double  OperationResult )
{
   double  TargetValue = 0.0;
   //:STRING ( 10 ) CompareType
   String   CompareType = null;
   //:SHORT         nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;


   //:// This is the evaluate operator routine for SUM, AVG, COUNT, NNULL, MAX and MIN or any decimal
   //:// or integer compare.
   //:// Process as either simple single evaluation or as subselect.

   //:IF zqFrame.PostActivateQualSub DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "PostActivateQualSub" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CompareType = zqFrame.PostActivateQual.SearchType
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_CompareType;
      if ( CompareType == null )
         sb_CompareType = new StringBuilder( 32 );
      else
         sb_CompareType = new StringBuilder( CompareType );
             GetVariableFromAttribute( sb_CompareType, mi_lTempInteger_1, 'S', 11, zqFrame, "PostActivateQual", "SearchType", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      CompareType = sb_CompareType.toString( );}
      //:TargetValue = zqFrame.PostActivateQual.Value
      {MutableDouble md_TargetValue = new MutableDouble( TargetValue );
             GetDecimalFromAttribute( md_TargetValue, zqFrame, "PostActivateQual", "Value" );
      TargetValue = md_TargetValue.doubleValue( );}
      //:// This is the simple case.
      //:IF OperationResult = TargetValue
      if ( OperationResult == TargetValue )
      { 
         //:IF CompareType = "="  OR
         //:   CompareType = ">=" OR
         //:   CompareType = "<="
         if ( ZeidonStringCompare( CompareType, 1, 0, "=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 )
         { 

            //:nRC = 1
            nRC = 1;
            //:ELSE
         } 
         else
         { 
            //:nRC = 0
            nRC = 0;
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:IF OperationResult > TargetValue
         if ( OperationResult > TargetValue )
         { 
            //:IF CompareType = ">"  OR
            //:   CompareType = ">=" OR
            //:   CompareType = "<>"
            if ( ZeidonStringCompare( CompareType, 1, 0, ">", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
            { 

               //:nRC = 1
               nRC = 1;
               //:ELSE
            } 
            else
            { 
               //:nRC = 0
               nRC = 0;
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// OperationResult < TargetValue
            //:IF CompareType = "<"  OR
            //:   CompareType = "<=" OR
            //:   CompareType = "<>"
            if ( ZeidonStringCompare( CompareType, 1, 0, "<", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
            { 

               //:nRC = 1
               nRC = 1;
               //:ELSE
            } 
            else
            { 
               //:nRC = 0
               nRC = 0;
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// This is the subselect case.
      //:// First evaluate each SubParameter the same as above.
      //:FOR EACH zqFrame.PostActivateQualSub
      RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQualSub", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CompareType = zqFrame.PostActivateQualSub.SearchType
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_CompareType;
         if ( CompareType == null )
            sb_CompareType = new StringBuilder( 32 );
         else
            sb_CompareType = new StringBuilder( CompareType );
                   GetVariableFromAttribute( sb_CompareType, mi_lTempInteger_2, 'S', 11, zqFrame, "PostActivateQualSub", "SearchType", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         CompareType = sb_CompareType.toString( );}
         //:TargetValue = zqFrame.PostActivateQualSub.Value
         {MutableDouble md_TargetValue = new MutableDouble( TargetValue );
                   GetDecimalFromAttribute( md_TargetValue, zqFrame, "PostActivateQualSub", "Value" );
         TargetValue = md_TargetValue.doubleValue( );}
         //:IF OperationResult = TargetValue
         if ( OperationResult == TargetValue )
         { 
            //:IF CompareType = "="  OR
            //:   CompareType = ">=" OR
            //:   CompareType = "<="
            if ( ZeidonStringCompare( CompareType, 1, 0, "=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 )
            { 

               //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
               SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
               //:ELSE
            } 
            else
            { 
               //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
               SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF OperationResult > TargetValue
            if ( OperationResult > TargetValue )
            { 
               //:IF CompareType = ">"  OR
               //:   CompareType = ">=" OR
               //:   CompareType = "<>"
               if ( ZeidonStringCompare( CompareType, 1, 0, ">", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
               { 

                  //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
                  SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
                  //:ELSE
               } 
               else
               { 
                  //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
                  SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// OperationResult < TargetValue
               //:IF CompareType = "<"  OR
               //:   CompareType = "<=" OR
               //:   CompareType = "<>"
               if ( ZeidonStringCompare( CompareType, 1, 0, "<", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
               { 

                  //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
                  SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
                  //:ELSE
               } 
               else
               { 
                  //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
                  SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( zqFrame, "PostActivateQualSub", "" );
         //:END
      } 

      //:END

      //:// Next, evaluate the combination based on the boolean operator AND or OR.
      //:IF zqFrame.PostActivateQual.SecondLevelOperator = "AND"
      if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SecondLevelOperator", "AND" ) == 0 )
      { 
         //:// Evaluate for the AND condition.
         //:nRC = 1
         nRC = 1;
         //:FOR EACH zqFrame.PostActivateQualSub
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQualSub", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
            if ( CompareAttributeToString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" ) == 0 )
            { 
               //:nRC = 0
               nRC = 0;
            } 

            RESULT = SetCursorNextEntity( zqFrame, "PostActivateQualSub", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Evaluate for the OR condition.
         //:nRC = -1
         nRC = -1;
         //:FOR EACH zqFrame.PostActivateQualSub
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQualSub", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
            if ( CompareAttributeToString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" ) == 0 )
            { 
               //:nRC = 1
               nRC = 1;
               //:ELSE
            } 
            else
            { 
               //:IF nRC = -1
               if ( nRC == -1 )
               { 
                  //:nRC = 0
                  nRC = 0;
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( zqFrame, "PostActivateQualSub", "" );
            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:RETURN nRC
   return( nRC );
// END
} 


//:LOCAL OPERATION
//:fnEvaluateOperatorStr( VIEW zqFrame BASED ON LOD zqFrame,
//:                       STRING ( 50 ) CompareValue )

//:   // Process operator evaluation for ANY or ALL operators or for regular string or date compares.
//:   // Note that we will do the compare by converting one of the values, CompareValue, to a work
//:   // attribute. This is so that the compare is not sensitive to upper/lower case differences,
//:   // which is the same as the database compare is SQL.
//:   // The only difference to the above is the case of finding a character string in another character string. In
//:   // that case we will convert both strings to uppercase because the find function we use is case senstive.

//:   SHORT            nRC
public int 
ozqFrame_fnEvaluateOperatorStr( View     zqFrame,
                                String   CompareValue )
{
   int      nRC = 0;
   //:STRING ( 10 )    CompareType
   String   CompareType = null;
   //:STRING ( 50 )    TargetValue
   String   TargetValue = null;
   //:STRING ( 50 )    TargetValueUpper
   String   TargetValueUpper = null;
   //:STRING ( 50 )    CompareValueUpper
   String   CompareValueUpper = null;
   //:STRING ( 1 )     DataType
   String   DataType = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      RESULT = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;


   //:DataType = zqFrame.PostActivateQual.DataType
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_DataType;
   if ( DataType == null )
      sb_DataType = new StringBuilder( 32 );
   else
      sb_DataType = new StringBuilder( DataType );
       GetVariableFromAttribute( sb_DataType, mi_lTempInteger_0, 'S', 2, zqFrame, "PostActivateQual", "DataType", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   DataType = sb_DataType.toString( );}
   //:zqFrame.zqFrame.wCompareValue = CompareValue
   SetAttributeFromString( zqFrame, "zqFrame", "wCompareValue", CompareValue );
   //:IF zqFrame.PostActivateQualSub DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( zqFrame, "PostActivateQualSub" );
   if ( lTempInteger_1 != 0 )
   { 
      //:IF DataType = "D" OR DataType = "T"
      if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
      { 
         //:TargetValue = zqFrame.PostActivateQual.ValueDate
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_TargetValue;
         if ( TargetValue == null )
            sb_TargetValue = new StringBuilder( 32 );
         else
            sb_TargetValue = new StringBuilder( TargetValue );
                   GetVariableFromAttribute( sb_TargetValue, mi_lTempInteger_2, 'S', 51, zqFrame, "PostActivateQual", "ValueDate", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         TargetValue = sb_TargetValue.toString( );}
         //:ELSE
      } 
      else
      { 
         //:TargetValue = zqFrame.PostActivateQual.Value
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_TargetValue;
         if ( TargetValue == null )
            sb_TargetValue = new StringBuilder( 32 );
         else
            sb_TargetValue = new StringBuilder( TargetValue );
                   GetVariableFromAttribute( sb_TargetValue, mi_lTempInteger_3, 'S', 51, zqFrame, "PostActivateQual", "Value", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         TargetValue = sb_TargetValue.toString( );}
      } 

      //:END
      //:CompareType = zqFrame.PostActivateQual.SearchType
      {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
      StringBuilder sb_CompareType;
      if ( CompareType == null )
         sb_CompareType = new StringBuilder( 32 );
      else
         sb_CompareType = new StringBuilder( CompareType );
             GetVariableFromAttribute( sb_CompareType, mi_lTempInteger_4, 'S', 11, zqFrame, "PostActivateQual", "SearchType", "", 0 );
      lTempInteger_4 = mi_lTempInteger_4.intValue( );
      CompareType = sb_CompareType.toString( );}
      //:// Simple case, no subgroup entries.
      //:IF CompareType = "contains" AND
      //:   DataType    = "S"
      if ( ZeidonStringCompare( CompareType, 1, 0, "contains", 1, 0, 11 ) == 0 && ZeidonStringCompare( DataType, 1, 0, "S", 1, 0, 2 ) == 0 )
      { 
         //:// The compare is looking for a string within a string.
         //:// Convert both strings to upper case so the find is NOT case sensitive.
         //:zToUpper( TargetValue, TargetValueUpper )
         {StringBuilder sb_TargetValueUpper;
         if ( TargetValueUpper == null )
            sb_TargetValueUpper = new StringBuilder( 32 );
         else
            sb_TargetValueUpper = new StringBuilder( TargetValueUpper );
                   zToUpper( TargetValue, sb_TargetValueUpper );
         TargetValueUpper = sb_TargetValueUpper.toString( );}
         //:zToUpper( CompareValue, CompareValueUpper )
         {StringBuilder sb_CompareValueUpper;
         if ( CompareValueUpper == null )
            sb_CompareValueUpper = new StringBuilder( 32 );
         else
            sb_CompareValueUpper = new StringBuilder( CompareValueUpper );
                   zToUpper( CompareValue, sb_CompareValueUpper );
         CompareValueUpper = sb_CompareValueUpper.toString( );}
         //:zqFrame.zqFrame.wCompareValue = CompareValueUpper
         SetAttributeFromString( zqFrame, "zqFrame", "wCompareValue", CompareValueUpper );
         //:nRC = FindStringInAttribute( TargetValueUpper,
         //:                             zqFrame, "zqFrame", "wCompareValue" )
         {
          ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
          nRC = m_ZGLOBAL1_Operation.FindStringInAttribute( TargetValueUpper, zqFrame, "zqFrame", "wCompareValue" );
          // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
         }
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:nRC = 1
            nRC = 1;
            //:ELSE
         } 
         else
         { 
            //:nRC = 0
            nRC = 0;
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// The compare is normal whole value compare.
         //:IF zqFrame.zqFrame.wCompareValue = TargetValue
         if ( CompareAttributeToString( zqFrame, "zqFrame", "wCompareValue", TargetValue ) == 0 )
         { 
            //:IF CompareType = "="  OR
            //:   CompareType = ">=" OR
            //:   CompareType = "<="
            if ( ZeidonStringCompare( CompareType, 1, 0, "=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 )
            { 

               //:nRC = 1
               nRC = 1;
               //:ELSE
            } 
            else
            { 
               //:nRC = 0
               nRC = 0;
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF zqFrame.zqFrame.wCompareValue > TargetValue
            if ( CompareAttributeToString( zqFrame, "zqFrame", "wCompareValue", TargetValue ) > 0 )
            { 
               //:IF CompareType = ">"  OR
               //:   CompareType = ">=" OR
               //:   CompareType = "<>"
               if ( ZeidonStringCompare( CompareType, 1, 0, ">", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
               { 

                  //:nRC = 1
                  nRC = 1;
                  //:ELSE
               } 
               else
               { 
                  //:nRC = 0
                  nRC = 0;
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:// CompareValue < TargetValue
               //:IF CompareType = "<"  OR
               //:   CompareType = "<=" OR
               //:   CompareType = "<>"
               if ( ZeidonStringCompare( CompareType, 1, 0, "<", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
               { 

                  //:nRC = 1
                  nRC = 1;
                  //:ELSE
               } 
               else
               { 
                  //:nRC = 0
                  nRC = 0;
               } 

               //:END
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// This is the subselect case.
      //:// First evaluate each SubParameter the same as above.
      //:FOR EACH zqFrame.PostActivateQualSub
      RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQualSub", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:IF DataType = "D" OR DataType = "T"
         if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
         { 
            //:TargetValue = zqFrame.PostActivateQualSub.ValueDate
            {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
            StringBuilder sb_TargetValue;
            if ( TargetValue == null )
               sb_TargetValue = new StringBuilder( 32 );
            else
               sb_TargetValue = new StringBuilder( TargetValue );
                         GetVariableFromAttribute( sb_TargetValue, mi_lTempInteger_5, 'S', 51, zqFrame, "PostActivateQualSub", "ValueDate", "", 0 );
            lTempInteger_5 = mi_lTempInteger_5.intValue( );
            TargetValue = sb_TargetValue.toString( );}
            //:ELSE
         } 
         else
         { 
            //:TargetValue = zqFrame.PostActivateQualSub.Value
            {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
            StringBuilder sb_TargetValue;
            if ( TargetValue == null )
               sb_TargetValue = new StringBuilder( 32 );
            else
               sb_TargetValue = new StringBuilder( TargetValue );
                         GetVariableFromAttribute( sb_TargetValue, mi_lTempInteger_6, 'S', 51, zqFrame, "PostActivateQualSub", "Value", "", 0 );
            lTempInteger_6 = mi_lTempInteger_6.intValue( );
            TargetValue = sb_TargetValue.toString( );}
         } 

         //:END
         //:CompareType = zqFrame.PostActivateQualSub.SearchType
         {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
         StringBuilder sb_CompareType;
         if ( CompareType == null )
            sb_CompareType = new StringBuilder( 32 );
         else
            sb_CompareType = new StringBuilder( CompareType );
                   GetVariableFromAttribute( sb_CompareType, mi_lTempInteger_7, 'S', 11, zqFrame, "PostActivateQualSub", "SearchType", "", 0 );
         lTempInteger_7 = mi_lTempInteger_7.intValue( );
         CompareType = sb_CompareType.toString( );}
         //:IF CompareType = "contains" AND
         //:   DataType    = "S"
         if ( ZeidonStringCompare( CompareType, 1, 0, "contains", 1, 0, 11 ) == 0 && ZeidonStringCompare( DataType, 1, 0, "S", 1, 0, 2 ) == 0 )
         { 
            //:// The compare is looking for a string within a string.
            //:nRC = FindStringInAttribute( TargetValue,
            //:                             zqFrame, "zqFrame", "wCompareValue" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
             nRC = m_ZGLOBAL1_Operation.FindStringInAttribute( TargetValue, zqFrame, "zqFrame", "wCompareValue" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:IF nRC = 0
            if ( nRC == 0 )
            { 
               //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
               SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
               //:ELSE
            } 
            else
            { 
               //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
               SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:IF zqFrame.zqFrame.wCompareValue = TargetValue
            if ( CompareAttributeToString( zqFrame, "zqFrame", "wCompareValue", TargetValue ) == 0 )
            { 
               //:IF CompareType = "="  OR
               //:   CompareType = ">=" OR
               //:   CompareType = "<="
               if ( ZeidonStringCompare( CompareType, 1, 0, "=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 )
               { 

                  //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
                  SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
                  //:ELSE
               } 
               else
               { 
                  //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
                  SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //:IF zqFrame.zqFrame.wCompareValue > TargetValue
               if ( CompareAttributeToString( zqFrame, "zqFrame", "wCompareValue", TargetValue ) > 0 )
               { 
                  //:IF CompareType = ">"  OR
                  //:   CompareType = ">=" OR
                  //:   CompareType = "<>"
                  if ( ZeidonStringCompare( CompareType, 1, 0, ">", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, ">=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
                  { 

                     //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
                     SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
                     SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// CompareValue < TargetValue
                  //:IF CompareType = "<"  OR
                  //:   CompareType = "<=" OR
                  //:   CompareType = "<>"
                  if ( ZeidonStringCompare( CompareType, 1, 0, "<", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<=", 1, 0, 11 ) == 0 || ZeidonStringCompare( CompareType, 1, 0, "<>", 1, 0, 11 ) == 0 )
                  { 

                     //:zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
                     SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" );
                     //:ELSE
                  } 
                  else
                  { 
                     //:zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
                     SetAttributeFromString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" );
                  } 

                  //:END
               } 

               //:END
            } 

            //:END
         } 

         RESULT = SetCursorNextEntity( zqFrame, "PostActivateQualSub", "" );
         //:END
      } 

      //:END

      //:// Next, evaluate the combination based on the boolean operator AND or OR.
      //:IF zqFrame.PostActivateQual.SecondLevelOperator = "AND"
      if ( CompareAttributeToString( zqFrame, "PostActivateQual", "SecondLevelOperator", "AND" ) == 0 )
      { 
         //:// Evaluate for the AND condition.
         //:nRC = 1
         nRC = 1;
         //:FOR EACH zqFrame.PostActivateQualSub
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQualSub", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.PostActivateQualSub.SelectTrueFlag = "F"
            if ( CompareAttributeToString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "F" ) == 0 )
            { 
               //:nRC = 0
               nRC = 0;
            } 

            RESULT = SetCursorNextEntity( zqFrame, "PostActivateQualSub", "" );
            //:END
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:// Evaluate for the OR condition.
         //:nRC = -1
         nRC = -1;
         //:FOR EACH zqFrame.PostActivateQualSub
         RESULT = SetCursorFirstEntity( zqFrame, "PostActivateQualSub", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.PostActivateQualSub.SelectTrueFlag = "T"
            if ( CompareAttributeToString( zqFrame, "PostActivateQualSub", "SelectTrueFlag", "T" ) == 0 )
            { 
               //:nRC = 1
               nRC = 1;
               //:ELSE
            } 
            else
            { 
               //:IF nRC = -1
               if ( nRC == -1 )
               { 
                  //:nRC = 0
                  nRC = 0;
               } 

               //:END
            } 

            RESULT = SetCursorNextEntity( zqFrame, "PostActivateQualSub", "" );
            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:RETURN nRC
   return( nRC );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatCSV_StartLine( VIEW zqFrame BASED ON LOD zqFrame,
//:                     STRING ( 10000 ) OutputLine,
//:                     INTEGER          NextColumnCount,
//:                     STRING ( 1 )     szTypeFlag )

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_FormatCSV_StartLine( View     zqFrame,
                              StringBuilder   OutputLine,
                              int      NextColumnCount,
                              String   szTypeFlag )
{
   zVIEW    zqFrame2 = new zVIEW( );
   //:INTEGER CommaCount
   int      CommaCount = 0;
   //:INTEGER nRC
   int      nRC = 0;
   //:STRING ( 32 ) szEntityName
   String   szEntityName = null;
   //:STRING ( 32 ) szLastEntityName
   String   szLastEntityName = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      RESULT = 0;
   int      lTempInteger_4 = 0;


   //:// Format the beginning of OutputLine with values from parent entities and with additional
   //:// commas for skipping columns.
   //:// Do this by going up the recursive parent chain to the last parent with QueryAttribute values.
   //:// For that parent, get its last OutputLine value and determine its column entity count.
   //:CreateViewFromView( zqFrame2, zqFrame )
   CreateViewFromView( zqFrame2, zqFrame );
   //:NAME VIEW zqFrame2 "zqFrameStartLine"
   SetNameForView( zqFrame2, "zqFrameStartLine", null, zLEVEL_TASK );
   //:szLastEntityName = ""
    {StringBuilder sb_szLastEntityName;
   if ( szLastEntityName == null )
      sb_szLastEntityName = new StringBuilder( 32 );
   else
      sb_szLastEntityName = new StringBuilder( szLastEntityName );
      ZeidonStringCopy( sb_szLastEntityName, 1, 0, "", 1, 0, 33 );
   szLastEntityName = sb_szLastEntityName.toString( );}
   //:szEntityName = zqFrame2.ParentEntity.EntityName 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, zqFrame2, "ParentEntity", "EntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:nRC = ResetViewFromSubobject( zqFrame2 )
   nRC = ResetViewFromSubobject( zqFrame2 );
   //:LOOP WHILE nRC = 0
   //:       AND zqFrame2.QueryAttribute DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( zqFrame2, "QueryAttribute" );
   //:       AND szEntityName != szLastEntityName
   while ( nRC == 0 && lTempInteger_1 != 0 && ZeidonStringCompare( szEntityName, 1, 0, szLastEntityName, 1, 0, 33 ) != 0 )
   { 

      //:szLastEntityName = szEntityName
       {StringBuilder sb_szLastEntityName;
      if ( szLastEntityName == null )
         sb_szLastEntityName = new StringBuilder( 32 );
      else
         sb_szLastEntityName = new StringBuilder( szLastEntityName );
            ZeidonStringCopy( sb_szLastEntityName, 1, 0, szEntityName, 1, 0, 33 );
      szLastEntityName = sb_szLastEntityName.toString( );}
      //:nRC = ResetViewFromSubobject( zqFrame2 )
      nRC = ResetViewFromSubobject( zqFrame2 );
      //:szEntityName = zqFrame2.ParentEntity.EntityName 
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_2, 'S', 33, zqFrame2, "ParentEntity", "EntityName", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szEntityName = sb_szEntityName.toString( );}
      lTempInteger_1 = CheckExistenceOfEntity( zqFrame2, "QueryAttribute" );
   } 

   //:END

   //:IF szTypeFlag = "F"
   if ( ZeidonStringCompare( szTypeFlag, 1, 0, "F", 1, 0, 2 ) == 0 )
   { 
      //:// The request is for "Full" lines, meaning fill each line with parent data.
      //:OutputLine = zqFrame2.ParentEntity.CurrentOutputLine
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
             GetVariableFromAttribute( OutputLine, mi_lTempInteger_3, 'S', 10001, zqFrame2, "ParentEntity", "CurrentOutputLine", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );}
      //:// Because attributes on a subobject could generate values for columns not included in
      //:// CurrentOutputLine, we need to add any commas necessary to get up to NextColumnCount.
      //:SET CURSOR LAST zqFrame2.QueryAttribute
      RESULT = SetCursorLastEntity( zqFrame2, "QueryAttribute", "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:CommaCount = zqFrame2.QueryAttribute.CSV_ColumnNumber + 1
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                   GetIntegerFromAttribute( mi_lTempInteger_4, zqFrame2, "QueryAttribute", "CSV_ColumnNumber" );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );}
         CommaCount = lTempInteger_4 + 1;
         //:ELSE
      } 
      else
      { 
         //:CommaCount = 1
         CommaCount = 1;
      } 

      //:END
      //:LOOP WHILE NextColumnCount > CommaCount
      while ( NextColumnCount > CommaCount )
      { 
         //:CommaCount = CommaCount + 1
         CommaCount = CommaCount + 1;
         //:OutputLine = OutputLine + ","
         ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:// The request is for "Sparse" lines, meaning fill each line with commas.
      //:CommaCount = 1
      CommaCount = 1;
      //:LOOP WHILE NextColumnCount > CommaCount
      while ( NextColumnCount > CommaCount )
      { 
         //:CommaCount = CommaCount + 1
         CommaCount = CommaCount + 1;
         //:OutputLine = OutputLine + ","
         ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
      } 

      //:END
   } 

   //:END

   //:DropView( zqFrame2 )
   DropView( zqFrame2 );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatRptDesign( VIEW zqFrame BASED ON LOD zqFrame,
//:                 VIEW vLOD,
//:                 VIEW qBaseR_Return,
//:                 STRING ( 100 ) ReportTitle,
//:                 STRING ( 32 ) QueryViewName )

//:   VIEW wXferO      REGISTERED AS wXferO
public int 
ozqFrame_FormatRptDesign( View     zqFrame,
                          View     vLOD,
                          zVIEW    qBaseR_Return,
                          String   ReportTitle,
                          String   QueryViewName )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW zqFrameRoot BASED ON LOD  zqFrame
   zVIEW    zqFrameRoot = new zVIEW( );
   //:VIEW zqSumR      BASED ON LOD  zqSumR
   zVIEW    zqSumR = new zVIEW( );
   //:VIEW qBaseR
   zVIEW    qBaseR = new zVIEW( );
   //:VIEW qBaseR_Source
   zVIEW    qBaseR_Source = new zVIEW( );
   //:VIEW qBaseR_Prompt
   zVIEW    qBaseR_Prompt = new zVIEW( );
   //:VIEW qBaseR_Value
   zVIEW    qBaseR_Value = new zVIEW( );
   //:VIEW qBaseR_Copy
   zVIEW    qBaseR_Copy = new zVIEW( );
   //:STRING ( 400 ) szFileName
   String   szFileName = null;
   //:STRING ( 40 )  Tag
   String   Tag = null;
   //:SHORT          nRC
   int      nRC = 0;
   //:INTEGER        Count
   int      Count = 0;
   //:INTEGER        OriginalPageHeaderSize
   int      OriginalPageHeaderSize = 0;
   //:INTEGER        IncreasedSize
   int      IncreasedSize = 0;
   //:INTEGER        NewPosition
   int      NewPosition = 0;
   //:INTEGER        NewGroupSize
   int      NewGroupSize = 0;
   //:INTEGER        StartingPositionX
   int      StartingPositionX = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:// Create a report object (TZRPSRCO) for formatting the data defined in the ParentEntity subobject.
   //:// Use the qBaseR report as a frame for creating the design.

   //:// First, go validate the input.
   //:// The following was removed by DonC on 11/18/03 because it was generating an error and it would
   //:// appear that the code is no longer necessary because the Query has already been executed at the
   //:// point where the operation is called here.
   //:/*nRC = ValidateQuery( zqFrame, "CSV Format" )
   //:IF nRC < 0
   //:   RETURN -1
   //:END*/

   //:// For regular queries, build the ParentEntity subobject for formatting attributes flagged for display.
   //:// For Pivot or Breakpoint queries, it is already built.
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.PivotRowEntityFlag = "Y"
   //:              OR zqFrame.GeneralParameter.BreakpointType != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "PivotRowEntityFlag", "Y" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointType", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:IF zqFrame.ParentEntity EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "ParentEntity" );
      if ( lTempInteger_0 == 0 )
      { 
         //:DELETE ENTITY zqFrame.ParentEntity
         RESULT = DeleteEntity( zqFrame, "ParentEntity", zPOS_NEXT );
      } 

      //:END
      //:FormatDisplay( zqFrame, vLOD )
      ozqFrame_FormatDisplay( zqFrame, vLOD );
   } 

   //:END

   //:// Activate the qBaseR object.
   //:GetApplDirectoryFromView( szFileName, zqFrame, zAPPL_DIR_OBJECT, 400 )
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetApplDirectoryFromView( sb_szFileName, zqFrame, zAPPL_DIR_OBJECT, 400 );
   szFileName = sb_szFileName.toString( );}
   //:szFileName = szFileName + "qBaseR.XRP"
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
      ZeidonStringConcat( sb_szFileName, 1, 0, "qBaseR.XRP", 1, 0, 401 );
   szFileName = sb_szFileName.toString( );}
   //:// 536870912 is ACTIVATE_SYSTEM in the following activate statement.
   //:nRC = ActivateOI_FromFile( qBaseR, "TZRPSRCO", zqFrame, szFileName, 536870912 )
   nRC = ActivateOI_FromFile( qBaseR, "TZRPSRCO", zqFrame, szFileName, 536870912 );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( zqFrame, "", "Query Object Setup",
      //:             "Error activate the qBaseR object.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( zqFrame, "", "Query Object Setup", "Error activate the qBaseR object.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN -1
      if(8==8)return( -1 );
   } 

   //:END
   //:NAME VIEW qBaseR "qBaseR"
   SetNameForView( qBaseR, "qBaseR", null, zLEVEL_TASK );

   //:// Make a copy of the Base Report object lest we need it for further processing later.
   //:ActivateOI_FromOI( qBaseR_Copy, qBaseR, zSINGLE )
   ActivateOI_FromOI( qBaseR_Copy, qBaseR, zSINGLE );
   //:NAME VIEW qBaseR_Copy "qBaseR_Copy"
   SetNameForView( qBaseR_Copy, "qBaseR_Copy", null, zLEVEL_TASK );

   //:// Get original page header size.
   //:SET CURSOR FIRST qBaseR.GroupSet WHERE qBaseR.GroupSet.Type = "PH"
   RESULT = SetCursorFirstEntityByString( qBaseR, "GroupSet", "Type", "PH", "" );
   //:OriginalPageHeaderSize = qBaseR.Control.SZDLG_Y
   {MutableInt mi_OriginalPageHeaderSize = new MutableInt( OriginalPageHeaderSize );
       GetIntegerFromAttribute( mi_OriginalPageHeaderSize, qBaseR, "Control", "SZDLG_Y" );
   OriginalPageHeaderSize = mi_OriginalPageHeaderSize.intValue( );}

   //:// Create the wXferO report information for page header and footer mapping.
   //:IF wXferO.QueryValues DOES NOT EXIST
   lTempInteger_1 = CheckExistenceOfEntity( wXferO, "QueryValues" );
   if ( lTempInteger_1 != 0 )
   { 
      //:CREATE ENTITY wXferO.QueryValues
      RESULT = CreateEntity( wXferO, "QueryValues", zPOS_AFTER );
   } 

   //:END
   //:wXferO.QueryValues.Description = ReportTitle
   SetAttributeFromString( wXferO, "QueryValues", "Description", ReportTitle );
   //:SetAttributeFromCurrentDateTime( wXferO, "QueryValues", "CurrentDate" )
   {
    ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( wXferO );
    m_ZGLOBAL1_Operation.SetAttributeFromCurrentDateTime( wXferO, "QueryValues", "CurrentDate" );
    // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
   }
   //:   
   //:// Design the report by creating a Group Set for each ParentEntity with attributes. Use the
   //:// base Group Set, "EntitySet", for defining the characteristics of the new set.
   //:StartingPositionX = 80
   StartingPositionX = 80;
   //:SET CURSOR FIRST zqFrame.GeneralParameter  
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   //:CreateViewFromView( zqFrameRoot, zqFrame )
   CreateViewFromView( zqFrameRoot, zqFrame );
   //:SET CURSOR FIRST qBaseR.GroupSet WHERE qBaseR.GroupSet.Tag = "EntitySet"
   RESULT = SetCursorFirstEntityByString( qBaseR, "GroupSet", "Tag", "EntitySet", "" );
   //:CreateViewFromView( qBaseR_Source, qBaseR )
   CreateViewFromView( qBaseR_Source, qBaseR );
   //:FormatRptDesignRecurs( zqFrame, zqFrameRoot, qBaseR, qBaseR_Source, QueryViewName, StartingPositionX, "" )
   ozqFrame_FormatRptDesignRecurs( zqFrame, zqFrameRoot, qBaseR, qBaseR_Source, QueryViewName, StartingPositionX, "" );
   //:DELETE ENTITY qBaseR_Source.GroupSet     // Delete the Source EntitySet subobject.
   RESULT = DeleteEntity( qBaseR_Source, "GroupSet", zPOS_NEXT );
   //:DropView( qBaseR_Source )
   DropView( qBaseR_Source );

   //:// PAGE HEADER FOR OPERATIONS
   //:// Set up the Page Header, whose format depends on the existence or not of operations (COUNT,
   //:// SUM, etc.) on the root entity. If there are such operations, then their values are added
   //:// to the "page one only" page header and the other page header without them is used starting on
   //:// page two. Since the "Title" controls were added to the first page header Group, they must be
   //:// copied to the  "page one only" Group.
   //:// If there are no operations, then the "page one only" page header is deleted.
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName 
   //:             AND zqFrame.GeneralParameter.ListDisplayFlag = "Y"
   //:             AND ( zqFrame.GeneralParameter.SubgroupSearchType = "COUNT"
   //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "NNULL"
   //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "MIN"
   //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "MAX"
   //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "SUM"
   //:                OR zqFrame.GeneralParameter.SubgroupSearchType = "AVG" )
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) != 0 ||
              CompareAttributeToString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" ) != 0 || ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "COUNT" ) != 0 &&
              CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "NNULL" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "MIN" ) != 0 &&
              CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "MAX" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "SUM" ) != 0 &&
              CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "AVG" ) != 0 ) ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:SET CURSOR FIRST qBaseR.GroupSet WHERE qBaseR.GroupSet.Type = "PH"
      RESULT = SetCursorFirstEntityByString( qBaseR, "GroupSet", "Type", "PH", "" );
      //:qBaseR.Group.SubType = 2
      SetAttributeFromInteger( qBaseR, "Group", "SubType", 2 );

      //:SET CURSOR NEXT qBaseR.Group
      RESULT = SetCursorNextEntity( qBaseR, "Group", "" );
      //:CreateViewFromView( qBaseR_Prompt, qBaseR )
      CreateViewFromView( qBaseR_Prompt, qBaseR );
      //:SET CURSOR FIRST qBaseR_Prompt.Control WHERE qBaseR_Prompt.Control.Tag = "T_TotalPrompt"
      RESULT = SetCursorFirstEntityByString( qBaseR_Prompt, "Control", "Tag", "T_TotalPrompt", "" );
      //:CreateViewFromView( qBaseR_Value, qBaseR )
      CreateViewFromView( qBaseR_Value, qBaseR );
      //:SET CURSOR FIRST qBaseR_Value.Control WHERE qBaseR_Value.Control.Tag = "T_TotalValue"
      RESULT = SetCursorFirstEntityByString( qBaseR_Value, "Control", "Tag", "T_TotalValue", "" );
      //:// Type is always decimal for operations.
      //:Count = 0
      Count = 0;
      //:FOR EACH zqFrame.GeneralParameter
      //:        WHERE zqFrame.GeneralParameter.SubgroupSearchType != ""
      //:          AND zqFrame.GeneralParameter.ListDisplayFlag = "Y"
      //:          AND zqFrame.GeneralParameter.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" ) == 0 &&
              CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 )
         { 
            //:Count = Count + 1
            Count = Count + 1;
            //:wXferO.QueryValues.WorkString = Count
            SetAttributeFromInteger( wXferO, "QueryValues", "WorkString", Count );
            //:Tag = "ReportTotal" + wXferO.QueryValues.WorkString
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_2, 'S', 255, wXferO, "QueryValues", "WorkString", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_Tag;
            if ( Tag == null )
               sb_Tag = new StringBuilder( 32 );
            else
               sb_Tag = new StringBuilder( Tag );
                        ZeidonStringCopy( sb_Tag, 1, 0, "ReportTotal", 1, 0, 41 );
            Tag = sb_Tag.toString( );}
             {StringBuilder sb_Tag;
            if ( Tag == null )
               sb_Tag = new StringBuilder( 32 );
            else
               sb_Tag = new StringBuilder( Tag );
                        ZeidonStringConcat( sb_Tag, 1, 0, szTempString_0, 1, 0, 41 );
            Tag = sb_Tag.toString( );}

            //:// Create Prompt
            //:CREATE ENTITY qBaseR.Control
            RESULT = CreateEntity( qBaseR, "Control", zPOS_AFTER );
            //:SetMatchingAttributesByName( qBaseR,        "Control",
            //:                          qBaseR_Prompt, "Control", zSET_ALL )
            SetMatchingAttributesByName( qBaseR, "Control", qBaseR_Prompt, "Control", zSET_ALL );
            //:INCLUDE qBaseR.ControlDef FROM qBaseR_Prompt.ControlDef
            RESULT = IncludeSubobjectFromSubobject( qBaseR, "ControlDef", qBaseR_Prompt, "ControlDef", zPOS_AFTER );
            //:qBaseR.Control.Text = zqFrame.GeneralParameter.Title 
            SetAttributeFromAttribute( qBaseR, "Control", "Text", zqFrame, "GeneralParameter", "Title" );
            //:qBaseR.Control.PSDLG_X = StartingPositionX
            SetAttributeFromInteger( qBaseR, "Control", "PSDLG_X", StartingPositionX );
            //:IF zqFrame.GeneralParameter.OverrideLength != ""
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "OverrideLength", "" ) != 0 )
            { 
               //:qBaseR.Control.SZDLG_X = zqFrame.GeneralParameter.OverrideLength * 15
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
                               GetIntegerFromAttribute( mi_lTempInteger_3, zqFrame, "GeneralParameter", "OverrideLength" );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );}
               lTempInteger_4 = lTempInteger_3 * 15;
               SetAttributeFromInteger( qBaseR, "Control", "SZDLG_X", lTempInteger_4 );
            } 

            //:END
            //:fnProcessFontOverride( zqFrameRoot, qBaseR )
            ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );

            //:// Create Value
            //:fnCreateMappingControl( qBaseR, qBaseR_Value, Tag, QueryViewName,
            //:                     zqFrame.GeneralParameter.EntityName,
            //:                     zqFrame.GeneralParameter.AttributeName )
            {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetStringFromAttribute( sb_szTempString_2, zqFrame, "GeneralParameter", "EntityName" );
            szTempString_2 = sb_szTempString_2.toString( );}
            {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetStringFromAttribute( sb_szTempString_3, zqFrame, "GeneralParameter", "AttributeName" );
            szTempString_3 = sb_szTempString_3.toString( );}
            ozqFrame_fnCreateMappingControl( qBaseR, qBaseR_Value, Tag, QueryViewName, szTempString_2, szTempString_3 );
            //:qBaseR.Control.wQueryOperation = zqFrame.GeneralParameter.SubgroupSearchType 
            SetAttributeFromAttribute( qBaseR, "Control", "wQueryOperation", zqFrame, "GeneralParameter", "SubgroupSearchType" );
            //:qBaseR.CtrlMapER_Domain.DataType = "L"
            SetAttributeFromString( qBaseR, "CtrlMapER_Domain", "DataType", "L" );
            //:qBaseR.Control.PSDLG_X = StartingPositionX
            SetAttributeFromInteger( qBaseR, "Control", "PSDLG_X", StartingPositionX );
            //:SetMatchingAttributesByName( qBaseR,       "Control",
            //:                          qBaseR_Value, "Control", zSET_NULL )
            SetMatchingAttributesByName( qBaseR, "Control", qBaseR_Value, "Control", zSET_NULL );
            //:IF zqFrame.GeneralParameter.OverrideLength != ""
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "OverrideLength", "" ) != 0 )
            { 
               //:qBaseR.Control.SZDLG_X = zqFrame.GeneralParameter.OverrideLength * 15
               {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                               GetIntegerFromAttribute( mi_lTempInteger_5, zqFrame, "GeneralParameter", "OverrideLength" );
               lTempInteger_5 = mi_lTempInteger_5.intValue( );}
               lTempInteger_6 = lTempInteger_5 * 15;
               SetAttributeFromInteger( qBaseR, "Control", "SZDLG_X", lTempInteger_6 );
            } 

            //:END
            //:StartingPositionX = StartingPositionX + qBaseR.Control.SZDLG_X + 36
            {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                         GetIntegerFromAttribute( mi_lTempInteger_7, qBaseR, "Control", "SZDLG_X" );
            lTempInteger_7 = mi_lTempInteger_7.intValue( );}
            StartingPositionX = StartingPositionX + lTempInteger_7 + 36;
            //:fnProcessFontOverride( zqFrameRoot, qBaseR )
            ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
         } 

         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

      //:END

      //:// Remove the source Controls that were copied and their views.
      //:DELETE ENTITY qBaseR_Prompt.Control
      RESULT = DeleteEntity( qBaseR_Prompt, "Control", zPOS_NEXT );
      //:DELETE ENTITY qBaseR_Value.Control
      RESULT = DeleteEntity( qBaseR_Value, "Control", zPOS_NEXT );
      //:DropView( qBaseR_Prompt )
      DropView( qBaseR_Prompt );
      //:DropView( qBaseR_Value )
      DropView( qBaseR_Value );

      //:// Copy the Title Controls from the first Page Header Group to the "page one only" Group.
      //:// They are the Controls following the DateOfReport Control.
      //:// Their Y Position needs be increased by the additional size of the "page one only" Group.
      //:CreateViewFromView( qBaseR_Source, qBaseR )
      CreateViewFromView( qBaseR_Source, qBaseR );
      //:SET CURSOR FIRST qBaseR_Source.Group
      RESULT = SetCursorFirstEntity( qBaseR_Source, "Group", "" );
      //:IncreasedSize = qBaseR.Group.SZDLG_Y - OriginalPageHeaderSize
      {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
             GetIntegerFromAttribute( mi_lTempInteger_8, qBaseR, "Group", "SZDLG_Y" );
      lTempInteger_8 = mi_lTempInteger_8.intValue( );}
      IncreasedSize = lTempInteger_8 - OriginalPageHeaderSize;
      //:NewGroupSize = qBaseR_Source.Group.SZDLG_Y + IncreasedSize
      {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
             GetIntegerFromAttribute( mi_lTempInteger_9, qBaseR_Source, "Group", "SZDLG_Y" );
      lTempInteger_9 = mi_lTempInteger_9.intValue( );}
      NewGroupSize = lTempInteger_9 + IncreasedSize;
      //:qBaseR.Group.SZDLG_Y = NewGroupSize
      SetAttributeFromInteger( qBaseR, "Group", "SZDLG_Y", NewGroupSize );
      //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "T_DateOfReport"
      RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "T_DateOfReport", "" );
      //:SET CURSOR LAST qBaseR.Control
      RESULT = SetCursorLastEntity( qBaseR, "Control", "" );
      //:SET CURSOR NEXT qBaseR_Source.Control
      RESULT = SetCursorNextEntity( qBaseR_Source, "Control", "" );
      //:LOOP WHILE RESULT >= zCURSOR_SET
      while ( RESULT >= zCURSOR_SET )
      { 
         //:CREATE ENTITY qBaseR.Control
         RESULT = CreateEntity( qBaseR, "Control", zPOS_AFTER );
         //:SetMatchingAttributesByName( qBaseR,        "Control",
         //:                             qBaseR_Source, "Control", zSET_ALL )
         SetMatchingAttributesByName( qBaseR, "Control", qBaseR_Source, "Control", zSET_ALL );
         //:NewPosition = qBaseR_Source.Control.PSDLG_Y
         {MutableInt mi_NewPosition = new MutableInt( NewPosition );
                   GetIntegerFromAttribute( mi_NewPosition, qBaseR_Source, "Control", "PSDLG_Y" );
         NewPosition = mi_NewPosition.intValue( );}
         //:NewPosition = NewPosition + IncreasedSize
         NewPosition = NewPosition + IncreasedSize;
         //:qBaseR.Control.PSDLG_Y = NewPosition
         SetAttributeFromInteger( qBaseR, "Control", "PSDLG_Y", NewPosition );
         //:INCLUDE qBaseR.ControlDef FROM qBaseR_Source.ControlDef
         RESULT = IncludeSubobjectFromSubobject( qBaseR, "ControlDef", qBaseR_Source, "ControlDef", zPOS_AFTER );
         //:fnProcessFontOverride( zqFrameRoot, qBaseR )
         ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
         //:SET CURSOR NEXT qBaseR_Source.Control
         RESULT = SetCursorNextEntity( qBaseR_Source, "Control", "" );
      } 

      //:END 

      //:DropView( qBaseR_Source ) 
      DropView( qBaseR_Source );
      //:ELSE
   } 
   else
   { 
      //:// Delete the additional page header for totals.
      //:SET CURSOR FIRST qBaseR.GroupSet WHERE qBaseR.GroupSet.Type = "PH"
      RESULT = SetCursorFirstEntityByString( qBaseR, "GroupSet", "Type", "PH", "" );
      //:SET CURSOR NEXT qBaseR.Group
      RESULT = SetCursorNextEntity( qBaseR, "Group", "" );
      //:DELETE ENTITY qBaseR.Group PREVIOUS  
      RESULT = DeleteEntity( qBaseR, "Group", zREPOS_PREV );
   } 

   //:END

   //:// If this is a Breakpoint Query, post-process the generated Report object.
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.BreakpointType != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointType", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:GET VIEW zqSumR NAMED "zqSumR"
      RESULT = GetViewByName( zqSumR, "zqSumR", zqFrame, zLEVEL_TASK );
      //:IF RESULT >= 0
      if ( RESULT >= 0 )
      { 
         //:ReformatDisplayReport( zqSumR, qBaseR, qBaseR_Copy )
         {
          zqSumR_Object m_zqSumR_Object = new zqSumR_Object( zqSumR );
          m_zqSumR_Object.ozqSumR_ReformatDisplayReport( zqSumR, qBaseR, qBaseR_Copy );
          // m_zqSumR_Object = null;  // permit gc  (unnecessary)
         }
      } 

      //:END
   } 

   //:END

   //:DropObjectInstance( qBaseR_Copy )
   DropObjectInstance( qBaseR_Copy );
   //:DropView( zqFrameRoot )
   DropView( zqFrameRoot );

   //:// Return the report and activated data objects.
   //:qBaseR_Return = qBaseR
   SetViewFromView( qBaseR_Return, qBaseR );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatRptDesignRecurs( VIEW zqFrame     BASED ON LOD zqFrame,
//:                       VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                       VIEW qBaseR,
//:                       VIEW qBaseR_Source,
//:                       STRING ( 32 ) QueryViewName,
//:                       INTEGER       FirstColumnStartingPosition,
//:                       STRING ( 1 )  ParentDisplayRowFlag )

//:   VIEW wXferO REGISTERED AS wXferO
public int 
ozqFrame_FormatRptDesignRecurs( View     zqFrame,
                                View     zqFrameRoot,
                                View     qBaseR,
                                View     qBaseR_Source,
                                String   QueryViewName,
                                int      FirstColumnStartingPosition,
                                String   ParentDisplayRowFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW qBaseR_H
   zVIEW    qBaseR_H = new zVIEW( );
   //:INTEGER       Count
   int      Count = 0;
   //:INTEGER       PositionX
   int      PositionX = 0;
   //:INTEGER       PositionY
   int      PositionY = 0;
   //:INTEGER       StartingPositionY
   int      StartingPositionY = 0;
   //:INTEGER       IncrementY
   int      IncrementY = 0;
   //:INTEGER       LastDisplayRow
   int      LastDisplayRow = 0;
   //:INTEGER       Size
   int      Size = 0;
   //:INTEGER       NewColumnStartingPosition
   int      NewColumnStartingPosition = 0;
   //:INTEGER       PageHeaderBottom
   int      PageHeaderBottom = 0;
   //:STRING ( 40 ) Tag
   String   Tag = null;
   //:STRING ( 20 ) PromptValue
   String   PromptValue = null;
   //:STRING ( 1 )  DisplayRowFlag
   String   DisplayRowFlag = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   String   szTempString_3 = null;
   String   szTempString_4 = null;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   String   szTempString_7 = null;
   int      lTempInteger_6 = 0;
   String   szTempString_8 = null;
   int      lTempInteger_7 = 0;
   String   szTempString_9 = null;
   int      lTempInteger_8 = 0;
   int      lTempInteger_9 = 0;
   String   szTempString_10 = null;
   int      lTempInteger_10 = 0;
   String   szTempString_11 = null;
   String   szTempString_12 = null;
   String   szTempString_13 = null;
   String   szTempString_14 = null;
   String   szTempString_15 = null;
   int      lTempInteger_11 = 0;
   int      lTempInteger_12 = 0;
   int      lTempInteger_13 = 0;
   String   szTempString_16 = null;
   int      lTempInteger_14 = 0;
   int      lTempInteger_15 = 0;
   String   szTempString_17 = null;
   int      lTempInteger_16 = 0;
   String   szTempString_18 = null;
   int      lTempInteger_17 = 0;
   int      lTempInteger_18 = 0;
   int      lTempInteger_19 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:// Process each ParentEntity subobject recursively, designing the report object, qBaseR,
   //:// from the entities defined in the ParentEntity subobject.
   //:// If any Attributes have a DisplayRow value specified, then format them first.

   //:FOR EACH zqFrame.ParentEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 

      //:IF zqFrame.QueryAttribute EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "QueryAttribute" );
      if ( lTempInteger_0 == 0 )
      { 

         //:// If there are attributes to map, we will indent them.
         //:NewColumnStartingPosition = FirstColumnStartingPosition + 150
         NewColumnStartingPosition = FirstColumnStartingPosition + 150;

         //:// Create GroupSet for Entity
         //:CREATE ENTITY qBaseR.GroupSet
         RESULT = CreateEntity( qBaseR, "GroupSet", zPOS_AFTER );
         //:SetMatchingAttributesByName( qBaseR,        "GroupSet",
         //:                             qBaseR_Source, "GroupSet", zSET_NULL )
         SetMatchingAttributesByName( qBaseR, "GroupSet", qBaseR_Source, "GroupSet", zSET_NULL );
         //:qBaseR.GroupSet.Tag = zqFrame.ParentEntity.EntityName
         SetAttributeFromAttribute( qBaseR, "GroupSet", "Tag", zqFrame, "ParentEntity", "EntityName" );

         //:// Create Entity Group
         //:SET CURSOR FIRST qBaseR_Source.Group WHERE qBaseR_Source.Group.Type = "ga"
         RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Group", "Type", "ga", "" );
         //:CREATE ENTITY qBaseR.Group
         RESULT = CreateEntity( qBaseR, "Group", zPOS_AFTER );
         //:SetMatchingAttributesByName( qBaseR,        "Group",
         //:                             qBaseR_Source, "Group", zSET_NULL )
         SetMatchingAttributesByName( qBaseR, "Group", qBaseR_Source, "Group", zSET_NULL );

         //:// If this is the root entity and if ForcePageBreakForEachRoot has been requested,
         //:// then set ForcePageBreak for the detail Group just created.
         //:IF zqFrameRoot.zqFrame.ForcePageBreakForEachRoot = "Y" AND
         //:   zqFrame.ParentEntity.EntityName = zqFrameRoot.zqFrame.QueryObjectRootEntityName
         if ( CompareAttributeToString( zqFrameRoot, "zqFrame", "ForcePageBreakForEachRoot", "Y" ) == 0 && CompareAttributeToAttribute( zqFrame, "ParentEntity", "EntityName", zqFrameRoot, "zqFrame", "QueryObjectRootEntityName" ) == 0 )
         { 

            //:qBaseR.Group.ForcePageBreak = "Y"
            SetAttributeFromString( qBaseR, "Group", "ForcePageBreak", "Y" );
         } 

         //:END

         //:Count = 0
         Count = 0;

         //:// If DisplayRow exists, order the Attributes by DisplayRow and then by DisplayOrder
         //:SET CURSOR FIRST zqFrame.QueryAttribute WHERE zqFrame.QueryAttribute.DisplayRow != ""
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "QueryAttribute", "DisplayRow", "" ) == 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:OrderEntityForView( zqFrame, "QueryAttribute", "DisplayRow A DisplayOrder A" )
            OrderEntityForView( zqFrame, "QueryAttribute", "DisplayRow A DisplayOrder A" );
         } 

         //:END

         //:// Format Attributes WITH DisplayRow values.
         //:// The starting Y position is taken from the first Control. (They're all the same.)
         //:// The Y position is incremented based on DisplayRow value.
         //:// The starting X position is passed in.
         //:// The X position is incremented for each Prompt and Value on the same Row.
         //:// Note that correctly handling the headers for the remaining nonDisplayRow
         //:// attributes is a little complex. The algorithm is as follows.
         //:// 1. For the remaining attributes, both the headers and the attribute values themselves
         //://    will appear at the end of the current Detail Group.
         //:// 2. For attributes from any child entities, the headers will appear on a Group Header
         //://    for that entity.
         //:StartingPositionY = qBaseR_Source.Control.PSDLG_Y
         {MutableInt mi_StartingPositionY = new MutableInt( StartingPositionY );
                   GetIntegerFromAttribute( mi_StartingPositionY, qBaseR_Source, "Control", "PSDLG_Y" );
         StartingPositionY = mi_StartingPositionY.intValue( );}
         //:DisplayRowFlag    = ""
          {StringBuilder sb_DisplayRowFlag;
         if ( DisplayRowFlag == null )
            sb_DisplayRowFlag = new StringBuilder( 32 );
         else
            sb_DisplayRowFlag = new StringBuilder( DisplayRowFlag );
                  ZeidonStringCopy( sb_DisplayRowFlag, 1, 0, "", 1, 0, 2 );
         DisplayRowFlag = sb_DisplayRowFlag.toString( );}
         //:IncrementY        = qBaseR_Source.Group.SZDLG_Y
         {MutableInt mi_IncrementY = new MutableInt( IncrementY );
                   GetIntegerFromAttribute( mi_IncrementY, qBaseR_Source, "Group", "SZDLG_Y" );
         IncrementY = mi_IncrementY.intValue( );}
         //:LastDisplayRow    = 0
         LastDisplayRow = 0;
         //:FOR EACH zqFrame.QueryAttribute WHERE zqFrame.QueryAttribute.DisplayRow != ""
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            if ( CompareAttributeToString( zqFrame, "QueryAttribute", "DisplayRow", "" ) != 0 )
            { 
               //:Count = Count + 1
               Count = Count + 1;
               //:wXferO.QueryValues.WorkString = Count
               SetAttributeFromInteger( wXferO, "QueryValues", "WorkString", Count );
               //:Tag = "Attribute" + wXferO.QueryValues.WorkString
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_1, 'S', 255, wXferO, "QueryValues", "WorkString", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_Tag;
               if ( Tag == null )
                  sb_Tag = new StringBuilder( 32 );
               else
                  sb_Tag = new StringBuilder( Tag );
                              ZeidonStringCopy( sb_Tag, 1, 0, "Attribute", 1, 0, 41 );
               Tag = sb_Tag.toString( );}
                {StringBuilder sb_Tag;
               if ( Tag == null )
                  sb_Tag = new StringBuilder( 32 );
               else
                  sb_Tag = new StringBuilder( Tag );
                              ZeidonStringConcat( sb_Tag, 1, 0, szTempString_0, 1, 0, 41 );
               Tag = sb_Tag.toString( );}
               //:DisplayRowFlag = "Y"
                {StringBuilder sb_DisplayRowFlag;
               if ( DisplayRowFlag == null )
                  sb_DisplayRowFlag = new StringBuilder( 32 );
               else
                  sb_DisplayRowFlag = new StringBuilder( DisplayRowFlag );
                              ZeidonStringCopy( sb_DisplayRowFlag, 1, 0, "Y", 1, 0, 2 );
               DisplayRowFlag = sb_DisplayRowFlag.toString( );}

               //:// Any attributes with a DisplayRow are formatted as prompt, followed by a value on 
               //:// the same row.

               //:// Set up positions.
               //:PositionY = StartingPositionY + (IncrementY * (zqFrame.QueryAttribute.DisplayRow - 1))
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
                               GetIntegerFromAttribute( mi_lTempInteger_2, zqFrame, "QueryAttribute", "DisplayRow" );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );}
               PositionY = StartingPositionY + ( IncrementY * ( lTempInteger_2 - 1 ) );
               //:IF zqFrame.QueryAttribute.DisplayRow != LastDisplayRow
               if ( CompareAttributeToInteger( zqFrame, "QueryAttribute", "DisplayRow", LastDisplayRow ) != 0 )
               { 
                  //:PositionX = FirstColumnStartingPosition
                  PositionX = FirstColumnStartingPosition;
               } 

               //:END
               //:LastDisplayRow = zqFrame.QueryAttribute.DisplayRow
               {MutableInt mi_LastDisplayRow = new MutableInt( LastDisplayRow );
                               GetIntegerFromAttribute( mi_LastDisplayRow, zqFrame, "QueryAttribute", "DisplayRow" );
               LastDisplayRow = mi_LastDisplayRow.intValue( );}

               //:// CREATE PROMPT
               //:SET CURSOR FIRST qBaseR_Source.Group
               RESULT = SetCursorFirstEntity( qBaseR_Source, "Group", "" );
               //:PromptValue = zqFrame.QueryAttribute.Prompt
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_PromptValue;
               if ( PromptValue == null )
                  sb_PromptValue = new StringBuilder( 32 );
               else
                  sb_PromptValue = new StringBuilder( PromptValue );
                               GetVariableFromAttribute( sb_PromptValue, mi_lTempInteger_3, 'S', 21, zqFrame, "QueryAttribute", "Prompt", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               PromptValue = sb_PromptValue.toString( );}
               //:IF PromptValue != "" 
               if ( ZeidonStringCompare( PromptValue, 1, 0, "", 1, 0, 21 ) != 0 )
               { 
                  //:PositionOnSourceCtrl( qBaseR_Source, zqFrame.QueryAttribute.Type )
                  {StringBuilder sb_szTempString_1;
                  if ( szTempString_1 == null )
                     sb_szTempString_1 = new StringBuilder( 32 );
                  else
                     sb_szTempString_1 = new StringBuilder( szTempString_1 );
                                     GetStringFromAttribute( sb_szTempString_1, zqFrame, "QueryAttribute", "Type" );
                  szTempString_1 = sb_szTempString_1.toString( );}
                  ozqFrame_PositionOnSourceCtrl( qBaseR_Source, szTempString_1 );
                  //:CREATE ENTITY qBaseR.Control
                  RESULT = CreateEntity( qBaseR, "Control", zPOS_AFTER );
                  //:SetMatchingAttributesByName( qBaseR,        "Control",
                  //:                          qBaseR_Source, "Control", zSET_ALL )
                  SetMatchingAttributesByName( qBaseR, "Control", qBaseR_Source, "Control", zSET_ALL );
                  //:INCLUDE qBaseR.ControlDef FROM qBaseR_Source.ControlDef
                  RESULT = IncludeSubobjectFromSubobject( qBaseR, "ControlDef", qBaseR_Source, "ControlDef", zPOS_AFTER );
                  //:qBaseR.Control.Text = PromptValue
                  SetAttributeFromString( qBaseR, "Control", "Text", PromptValue );
                  //:qBaseR.Control.PSDLG_X = PositionX
                  SetAttributeFromInteger( qBaseR, "Control", "PSDLG_X", PositionX );
                  //:qBaseR.Control.PSDLG_Y = PositionY
                  SetAttributeFromInteger( qBaseR, "Control", "PSDLG_Y", PositionY );
                  //:Size = zGetStringLen( PromptValue )
                  Size = zGetStringLen( PromptValue );
                  //:qBaseR.Control.SZDLG_X = Size * 25
                  lTempInteger_4 = Size * 25;
                  SetAttributeFromInteger( qBaseR, "Control", "SZDLG_X", lTempInteger_4 );
                  //:PositionX = PositionX + qBaseR.Control.SZDLG_X + 36  // Increment for next X position
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                                     GetIntegerFromAttribute( mi_lTempInteger_5, qBaseR, "Control", "SZDLG_X" );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );}
                  PositionX = PositionX + lTempInteger_5 + 36;
                  //:fnProcessFontOverride( zqFrameRoot, qBaseR )
                  ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
               } 

               //:END

               //:// CREATE VALUE
               //:SET CURSOR NEXT qBaseR_Source.Group
               RESULT = SetCursorNextEntity( qBaseR_Source, "Group", "" );
               //:IF zqFrame.QueryAttribute.AttributeName != ""
               if ( CompareAttributeToString( zqFrame, "QueryAttribute", "AttributeName", "" ) != 0 )
               { 
                  //:// This is a regular mapping attribute value.
                  //:PositionOnSourceCtrl( qBaseR_Source, zqFrame.QueryAttribute.Type )
                  {StringBuilder sb_szTempString_3;
                  if ( szTempString_3 == null )
                     sb_szTempString_3 = new StringBuilder( 32 );
                  else
                     sb_szTempString_3 = new StringBuilder( szTempString_3 );
                                     GetStringFromAttribute( sb_szTempString_3, zqFrame, "QueryAttribute", "Type" );
                  szTempString_3 = sb_szTempString_3.toString( );}
                  ozqFrame_PositionOnSourceCtrl( qBaseR_Source, szTempString_3 );
                  //:fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName,
                  //:                     zqFrame.QueryAttribute.MappingEntityName,
                  //:                     zqFrame.QueryAttribute.AttributeName )
                  {StringBuilder sb_szTempString_4;
                  if ( szTempString_4 == null )
                     sb_szTempString_4 = new StringBuilder( 32 );
                  else
                     sb_szTempString_4 = new StringBuilder( szTempString_4 );
                                     GetStringFromAttribute( sb_szTempString_4, zqFrame, "QueryAttribute", "MappingEntityName" );
                  szTempString_4 = sb_szTempString_4.toString( );}
                  {StringBuilder sb_szTempString_5;
                  if ( szTempString_5 == null )
                     sb_szTempString_5 = new StringBuilder( 32 );
                  else
                     sb_szTempString_5 = new StringBuilder( szTempString_5 );
                                     GetStringFromAttribute( sb_szTempString_5, zqFrame, "QueryAttribute", "AttributeName" );
                  szTempString_5 = sb_szTempString_5.toString( );}
                  ozqFrame_fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName, szTempString_4, szTempString_5 );
                  //:fnProcessFontOverride( zqFrameRoot, qBaseR )
                  ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
                  //:IF zqFrame.QueryAttribute.DisplayDomainContext != ""
                  if ( CompareAttributeToString( zqFrame, "QueryAttribute", "DisplayDomainContext", "" ) != 0 )
                  { 
                     //:// If Domain Context has been specified, create a Context entity for the Control.
                     //:CREATE ENTITY qBaseR.CtrlMapContext
                     RESULT = CreateEntity( qBaseR, "CtrlMapContext", zPOS_AFTER );
                     //:qBaseR.CtrlMapContext.Name = zqFrame.QueryAttribute.DisplayDomainContext
                     SetAttributeFromAttribute( qBaseR, "CtrlMapContext", "Name", zqFrame, "QueryAttribute", "DisplayDomainContext" );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// This is a derived attribute value (created from an operation).
                  //:// Assume Type to be decimal.
                  //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "Decimal"
                  RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "Decimal", "" );
                  //:fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName,
                  //:                     zqFrame.QueryAttribute.GroupOperationEntityName,
                  //:                     zqFrame.QueryAttribute.GroupOperationAttributeName )
                  {StringBuilder sb_szTempString_6;
                  if ( szTempString_6 == null )
                     sb_szTempString_6 = new StringBuilder( 32 );
                  else
                     sb_szTempString_6 = new StringBuilder( szTempString_6 );
                                     GetStringFromAttribute( sb_szTempString_6, zqFrame, "QueryAttribute", "GroupOperationEntityName" );
                  szTempString_6 = sb_szTempString_6.toString( );}
                  {StringBuilder sb_szTempString_7;
                  if ( szTempString_7 == null )
                     sb_szTempString_7 = new StringBuilder( 32 );
                  else
                     sb_szTempString_7 = new StringBuilder( szTempString_7 );
                                     GetStringFromAttribute( sb_szTempString_7, zqFrame, "QueryAttribute", "GroupOperationAttributeName" );
                  szTempString_7 = sb_szTempString_7.toString( );}
                  ozqFrame_fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName, szTempString_6, szTempString_7 );
                  //:fnProcessFontOverride( zqFrameRoot, qBaseR )
                  ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
                  //:qBaseR.Control.wQueryOperation = zqFrame.QueryAttribute.GroupOperation
                  SetAttributeFromAttribute( qBaseR, "Control", "wQueryOperation", zqFrame, "QueryAttribute", "GroupOperation" );
               } 

               //:END
               //:qBaseR.Control.PSDLG_X = PositionX
               SetAttributeFromInteger( qBaseR, "Control", "PSDLG_X", PositionX );
               //:qBaseR.Control.PSDLG_Y = PositionY
               SetAttributeFromInteger( qBaseR, "Control", "PSDLG_Y", PositionY );

               //:// Set size to that requested.
               //:Size = zqFrame.QueryAttribute.Length * 15
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
                               GetIntegerFromAttribute( mi_lTempInteger_6, zqFrame, "QueryAttribute", "Length" );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );}
               Size = lTempInteger_6 * 15;
               //:IF Size > 900
               if ( Size > 900 )
               { 
                  //:Size = 900
                  Size = 900;
               } 

               //:END
               //:qBaseR.Control.SZDLG_X = Size
               SetAttributeFromInteger( qBaseR, "Control", "SZDLG_X", Size );
               //:PositionX = PositionX + Size + 36  // Increment for next X position
               PositionX = PositionX + Size + 36;
            } 

            RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
         } 

         //:   
         //:END

         //:// Create Header Line for DisplayRow entries.
         //:// If we had DisplayRow attributes, add headers for any other attributes at the bottom
         //:// of the Detail Group.
         //:IF DisplayRowFlag = "Y"
         if ( ZeidonStringCompare( DisplayRowFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:SET CURSOR FIRST zqFrame.QueryAttribute WHERE zqFrame.QueryAttribute.DisplayRow = ""
            RESULT = SetCursorFirstEntityByString( zqFrame, "QueryAttribute", "DisplayRow", "", "" );
            //:IF RESULT >= zCURSOR_SET 
            if ( RESULT >= zCURSOR_SET )
            { 
               //:// Both DisplayRow and nonDisplayRow attributes exist so add the nonDisplayRow
               //:// header to the bottom of the Detail.
               //:SET CURSOR FIRST qBaseR_Source.Group
               RESULT = SetCursorFirstEntity( qBaseR_Source, "Group", "" );
               //:PositionY = StartingPositionY + (IncrementY * LastDisplayRow)
               PositionY = StartingPositionY + ( IncrementY * LastDisplayRow );
               //:PositionX = FirstColumnStartingPosition
               PositionX = FirstColumnStartingPosition;
               //:FOR EACH zqFrame.QueryAttribute WHERE zqFrame.QueryAttribute.DisplayRow = ""
               RESULT = SetCursorFirstEntityByString( zqFrame, "QueryAttribute", "DisplayRow", "", "" );
               while ( RESULT > zCURSOR_UNCHANGED )
               { 
                  //:Count = Count + 1
                  Count = Count + 1;
                  //:wXferO.QueryValues.WorkString = Count
                  SetAttributeFromInteger( wXferO, "QueryValues", "WorkString", Count );
                  //:Tag = zqFrame.QueryAttribute.AttributeName + wXferO.QueryValues.WorkString
                  {StringBuilder sb_Tag;
                  if ( Tag == null )
                     sb_Tag = new StringBuilder( 32 );
                  else
                     sb_Tag = new StringBuilder( Tag );
                                     GetStringFromAttribute( sb_Tag, zqFrame, "QueryAttribute", "AttributeName" );
                  Tag = sb_Tag.toString( );}
                  {StringBuilder sb_szTempString_8;
                  if ( szTempString_8 == null )
                     sb_szTempString_8 = new StringBuilder( 32 );
                  else
                     sb_szTempString_8 = new StringBuilder( szTempString_8 );
                                     GetStringFromAttribute( sb_szTempString_8, wXferO, "QueryValues", "WorkString" );
                  szTempString_8 = sb_szTempString_8.toString( );}
                   {StringBuilder sb_Tag;
                  if ( Tag == null )
                     sb_Tag = new StringBuilder( 32 );
                  else
                     sb_Tag = new StringBuilder( Tag );
                                    ZeidonStringConcat( sb_Tag, 1, 0, szTempString_8, 1, 0, 41 );
                  Tag = sb_Tag.toString( );}
                  //:PromptValue = zqFrame.QueryAttribute.Prompt
                  {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
                  StringBuilder sb_PromptValue;
                  if ( PromptValue == null )
                     sb_PromptValue = new StringBuilder( 32 );
                  else
                     sb_PromptValue = new StringBuilder( PromptValue );
                                     GetVariableFromAttribute( sb_PromptValue, mi_lTempInteger_7, 'S', 21, zqFrame, "QueryAttribute", "Prompt", "", 0 );
                  lTempInteger_7 = mi_lTempInteger_7.intValue( );
                  PromptValue = sb_PromptValue.toString( );}
                  //:PositionOnSourceCtrl( qBaseR_Source, zqFrame.QueryAttribute.Type )
                  {StringBuilder sb_szTempString_9;
                  if ( szTempString_9 == null )
                     sb_szTempString_9 = new StringBuilder( 32 );
                  else
                     sb_szTempString_9 = new StringBuilder( szTempString_9 );
                                     GetStringFromAttribute( sb_szTempString_9, zqFrame, "QueryAttribute", "Type" );
                  szTempString_9 = sb_szTempString_9.toString( );}
                  ozqFrame_PositionOnSourceCtrl( qBaseR_Source, szTempString_9 );
                  //:fnCreatePromptControl( qBaseR, qBaseR_Source, Tag, PromptValue )
                  ozqFrame_fnCreatePromptControl( qBaseR, qBaseR_Source, Tag, PromptValue );

                  //:// Set size to that requested.
                  //:Size = zqFrame.QueryAttribute.Length * 15
                  {MutableInt mi_lTempInteger_8 = new MutableInt( lTempInteger_8 );
                                     GetIntegerFromAttribute( mi_lTempInteger_8, zqFrame, "QueryAttribute", "Length" );
                  lTempInteger_8 = mi_lTempInteger_8.intValue( );}
                  Size = lTempInteger_8 * 15;
                  //:IF Size > 900
                  if ( Size > 900 )
                  { 
                     //:Size = 900
                     Size = 900;
                  } 

                  //:END
                  //:qBaseR.Control.SZDLG_X = Size
                  SetAttributeFromInteger( qBaseR, "Control", "SZDLG_X", Size );

                  //:qBaseR.Control.PSDLG_X = PositionX
                  SetAttributeFromInteger( qBaseR, "Control", "PSDLG_X", PositionX );
                  //:qBaseR.Control.PSDLG_Y = PositionY
                  SetAttributeFromInteger( qBaseR, "Control", "PSDLG_Y", PositionY );
                  //:PositionX = PositionX + qBaseR.Control.SZDLG_X + 36
                  {MutableInt mi_lTempInteger_9 = new MutableInt( lTempInteger_9 );
                                     GetIntegerFromAttribute( mi_lTempInteger_9, qBaseR, "Control", "SZDLG_X" );
                  lTempInteger_9 = mi_lTempInteger_9.intValue( );}
                  PositionX = PositionX + lTempInteger_9 + 36;
                  //:fnProcessFontOverride( zqFrameRoot, qBaseR )
                  ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
                  RESULT = SetCursorNextEntityByString( zqFrame, "QueryAttribute", "DisplayRow", "", "" );
               } 

               //:END
            } 

            //:END
         } 

         //:END

         //:// Format Attributes WITHOUT DisplayRow values.
         //:SET CURSOR LAST qBaseR_Source.Group
         RESULT = SetCursorLastEntity( qBaseR_Source, "Group", "" );
         //:PositionX = FirstColumnStartingPosition
         PositionX = FirstColumnStartingPosition;
         //:IF DisplayRowFlag = "Y"
         if ( ZeidonStringCompare( DisplayRowFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:PositionY = StartingPositionY + (IncrementY * (LastDisplayRow + 1))
            PositionY = StartingPositionY + ( IncrementY * ( LastDisplayRow + 1 ) );
            //:ELSE
         } 
         else
         { 
            //:PositionY = StartingPositionY
            PositionY = StartingPositionY;
         } 

         //:END
         //:// A core error has required the following FOR and IF as opposed to the combination
         //:// statement: FOR EACH zqFrame.QueryAttribute WHERE zqFrame.QueryAttribute.DisplayRow = ""
         //:// The combination statement sometimes does not process correctly. In particular, we were
         //:// never getting an equal condition on Pivot reports.
         //:// At some point this core error should be corrected.
         //:FOR EACH zqFrame.QueryAttribute
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.QueryAttribute.DisplayRow = ""
            if ( CompareAttributeToString( zqFrame, "QueryAttribute", "DisplayRow", "" ) == 0 )
            { 
               //:Count = Count + 1
               Count = Count + 1;
               //:wXferO.QueryValues.WorkString = Count
               SetAttributeFromInteger( wXferO, "QueryValues", "WorkString", Count );
               //:Tag = "Attribute" + wXferO.QueryValues.WorkString
               {MutableInt mi_lTempInteger_10 = new MutableInt( lTempInteger_10 );
               StringBuilder sb_szTempString_10;
               if ( szTempString_10 == null )
                  sb_szTempString_10 = new StringBuilder( 32 );
               else
                  sb_szTempString_10 = new StringBuilder( szTempString_10 );
                               GetVariableFromAttribute( sb_szTempString_10, mi_lTempInteger_10, 'S', 255, wXferO, "QueryValues", "WorkString", "", 0 );
               lTempInteger_10 = mi_lTempInteger_10.intValue( );
               szTempString_10 = sb_szTempString_10.toString( );}
                {StringBuilder sb_Tag;
               if ( Tag == null )
                  sb_Tag = new StringBuilder( 32 );
               else
                  sb_Tag = new StringBuilder( Tag );
                              ZeidonStringCopy( sb_Tag, 1, 0, "Attribute", 1, 0, 41 );
               Tag = sb_Tag.toString( );}
                {StringBuilder sb_Tag;
               if ( Tag == null )
                  sb_Tag = new StringBuilder( 32 );
               else
                  sb_Tag = new StringBuilder( Tag );
                              ZeidonStringConcat( sb_Tag, 1, 0, szTempString_10, 1, 0, 41 );
               Tag = sb_Tag.toString( );}
               //:IF zqFrame.QueryAttribute.AttributeName != ""
               if ( CompareAttributeToString( zqFrame, "QueryAttribute", "AttributeName", "" ) != 0 )
               { 
                  //:// This is a regular mapping attribute value.
                  //:PositionOnSourceCtrl( qBaseR_Source, zqFrame.QueryAttribute.Type )
                  {StringBuilder sb_szTempString_11;
                  if ( szTempString_11 == null )
                     sb_szTempString_11 = new StringBuilder( 32 );
                  else
                     sb_szTempString_11 = new StringBuilder( szTempString_11 );
                                     GetStringFromAttribute( sb_szTempString_11, zqFrame, "QueryAttribute", "Type" );
                  szTempString_11 = sb_szTempString_11.toString( );}
                  ozqFrame_PositionOnSourceCtrl( qBaseR_Source, szTempString_11 );
                  //:fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName,
                  //:                        zqFrame.QueryAttribute.MappingEntityName,
                  //:                        zqFrame.QueryAttribute.AttributeName )
                  {StringBuilder sb_szTempString_12;
                  if ( szTempString_12 == null )
                     sb_szTempString_12 = new StringBuilder( 32 );
                  else
                     sb_szTempString_12 = new StringBuilder( szTempString_12 );
                                     GetStringFromAttribute( sb_szTempString_12, zqFrame, "QueryAttribute", "MappingEntityName" );
                  szTempString_12 = sb_szTempString_12.toString( );}
                  {StringBuilder sb_szTempString_13;
                  if ( szTempString_13 == null )
                     sb_szTempString_13 = new StringBuilder( 32 );
                  else
                     sb_szTempString_13 = new StringBuilder( szTempString_13 );
                                     GetStringFromAttribute( sb_szTempString_13, zqFrame, "QueryAttribute", "AttributeName" );
                  szTempString_13 = sb_szTempString_13.toString( );}
                  ozqFrame_fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName, szTempString_12, szTempString_13 );
                  //:IF zqFrame.QueryAttribute.DisplayDomainContext != ""
                  if ( CompareAttributeToString( zqFrame, "QueryAttribute", "DisplayDomainContext", "" ) != 0 )
                  { 
                     //:// If Domain Context has been specified, create a Context entity for the Control.
                     //:CREATE ENTITY qBaseR.CtrlMapContext
                     RESULT = CreateEntity( qBaseR, "CtrlMapContext", zPOS_AFTER );
                     //:qBaseR.CtrlMapContext.Name = zqFrame.QueryAttribute.DisplayDomainContext
                     SetAttributeFromAttribute( qBaseR, "CtrlMapContext", "Name", zqFrame, "QueryAttribute", "DisplayDomainContext" );
                  } 

                  //:END
                  //:ELSE
               } 
               else
               { 
                  //:// This is a derived attribute value (created from an operation).
                  //:// Assume Type to be decimal.
                  //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "Decimal"
                  RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "Decimal", "" );
                  //:fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName,
                  //:                        zqFrame.QueryAttribute.GroupOperationEntityName,
                  //:                        zqFrame.QueryAttribute.GroupOperationAttributeName )
                  {StringBuilder sb_szTempString_14;
                  if ( szTempString_14 == null )
                     sb_szTempString_14 = new StringBuilder( 32 );
                  else
                     sb_szTempString_14 = new StringBuilder( szTempString_14 );
                                     GetStringFromAttribute( sb_szTempString_14, zqFrame, "QueryAttribute", "GroupOperationEntityName" );
                  szTempString_14 = sb_szTempString_14.toString( );}
                  {StringBuilder sb_szTempString_15;
                  if ( szTempString_15 == null )
                     sb_szTempString_15 = new StringBuilder( 32 );
                  else
                     sb_szTempString_15 = new StringBuilder( szTempString_15 );
                                     GetStringFromAttribute( sb_szTempString_15, zqFrame, "QueryAttribute", "GroupOperationAttributeName" );
                  szTempString_15 = sb_szTempString_15.toString( );}
                  ozqFrame_fnCreateMappingControl( qBaseR, qBaseR_Source, Tag, QueryViewName, szTempString_14, szTempString_15 );
                  //:qBaseR.Control.wQueryOperation = zqFrame.QueryAttribute.GroupOperation
                  SetAttributeFromAttribute( qBaseR, "Control", "wQueryOperation", zqFrame, "QueryAttribute", "GroupOperation" );
               } 

               //:END
               //:// Set size to that requested.
               //:Size = zqFrame.QueryAttribute.Length * 15
               {MutableInt mi_lTempInteger_11 = new MutableInt( lTempInteger_11 );
                               GetIntegerFromAttribute( mi_lTempInteger_11, zqFrame, "QueryAttribute", "Length" );
               lTempInteger_11 = mi_lTempInteger_11.intValue( );}
               Size = lTempInteger_11 * 15;
               //:IF Size > 900
               if ( Size > 900 )
               { 
                  //:Size = 900
                  Size = 900;
               } 

               //:END
               //:qBaseR.Control.SZDLG_X = Size
               SetAttributeFromInteger( qBaseR, "Control", "SZDLG_X", Size );

               //:qBaseR.CtrlMapER_Domain.DataType = zqFrame.QueryAttribute.Type
               SetAttributeFromAttribute( qBaseR, "CtrlMapER_Domain", "DataType", zqFrame, "QueryAttribute", "Type" );
               //:qBaseR.Control.PSDLG_X = PositionX
               SetAttributeFromInteger( qBaseR, "Control", "PSDLG_X", PositionX );
               //:qBaseR.Control.PSDLG_Y = PositionY
               SetAttributeFromInteger( qBaseR, "Control", "PSDLG_Y", PositionY );
               //:PositionX = PositionX + qBaseR.Control.SZDLG_X + 36
               {MutableInt mi_lTempInteger_12 = new MutableInt( lTempInteger_12 );
                               GetIntegerFromAttribute( mi_lTempInteger_12, qBaseR, "Control", "SZDLG_X" );
               lTempInteger_12 = mi_lTempInteger_12.intValue( );}
               PositionX = PositionX + lTempInteger_12 + 36;
               //:fnProcessFontOverride( zqFrameRoot, qBaseR )
               ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
            } 

            RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
            //:END
         } 

         //:END

         //:// If we had DisplayRow attributes, recompute and set the Group length.
         //:IF DisplayRowFlag = "Y"
         if ( ZeidonStringCompare( DisplayRowFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 
            //:Size = qBaseR_Source.Group.SZDLG_Y + PositionY
            {MutableInt mi_lTempInteger_13 = new MutableInt( lTempInteger_13 );
                         GetIntegerFromAttribute( mi_lTempInteger_13, qBaseR_Source, "Group", "SZDLG_Y" );
            lTempInteger_13 = mi_lTempInteger_13.intValue( );}
            Size = lTempInteger_13 + PositionY;
            //:qBaseR.Group.SZDLG_Y = Size
            SetAttributeFromInteger( qBaseR, "Group", "SZDLG_Y", Size );
         } 

         //:END

         //:// Create Header Line when no DisplayRow entries exist.
         //:// If DisplayRow entries exist, then this section is skipped because the Headers were 
         //:// already put on the Detail Group.
         //:// If DisplayRow entries for a Parent exist (the ParentDisplayRowFlag is set), then the
         //:// header line is created as a Group Header for the current Detail Group.
         //:// Otherwise, the header line is created back on the page header.
         //:// In either of the last two cases, use X positions from mapping fields just created.
         //:// Use Y position by adding to bottom of current page header or new Group Header.
         //:// Also increase Header by size of additional header just added.
         //:IF DisplayRowFlag = ""
         if ( ZeidonStringCompare( DisplayRowFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:CreateViewFromView( qBaseR_H, qBaseR )
            CreateViewFromView( qBaseR_H, qBaseR );
            //:SET CURSOR FIRST qBaseR_Source.Group WHERE qBaseR_Source.Group.Type = "gh"
            RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Group", "Type", "gh", "" );
            //:IF ParentDisplayRowFlag = "Y"
            if ( ZeidonStringCompare( ParentDisplayRowFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
            { 
               //:CREATE ENTITY qBaseR_H.Group BEFORE
               RESULT = CreateEntity( qBaseR_H, "Group", zPOS_BEFORE );
               //:SetMatchingAttributesByName( qBaseR_H,      "Group",
               //:                             qBaseR_Source, "Group", zSET_ALL )
               SetMatchingAttributesByName( qBaseR_H, "Group", qBaseR_Source, "Group", zSET_ALL );
               //:Tag = qBaseR.Group.Tag + "H"
               {StringBuilder sb_Tag;
               if ( Tag == null )
                  sb_Tag = new StringBuilder( 32 );
               else
                  sb_Tag = new StringBuilder( Tag );
                               GetStringFromAttribute( sb_Tag, qBaseR, "Group", "Tag" );
               Tag = sb_Tag.toString( );}
                {StringBuilder sb_Tag;
               if ( Tag == null )
                  sb_Tag = new StringBuilder( 32 );
               else
                  sb_Tag = new StringBuilder( Tag );
                              ZeidonStringConcat( sb_Tag, 1, 0, "H", 1, 0, 41 );
               Tag = sb_Tag.toString( );}
               //:qBaseR_H.Group.Tag = Tag
               SetAttributeFromString( qBaseR_H, "Group", "Tag", Tag );
               //:ELSE
            } 
            else
            { 
               //:SET CURSOR FIRST qBaseR_H.GroupSet WHERE qBaseR_H.GroupSet.Type = "PH"
               RESULT = SetCursorFirstEntityByString( qBaseR_H, "GroupSet", "Type", "PH", "" );
               //:SET CURSOR LAST  qBaseR_H.Control
               RESULT = SetCursorLastEntity( qBaseR_H, "Control", "" );
               //:SET CURSOR FIRST qBaseR.Control
               RESULT = SetCursorFirstEntity( qBaseR, "Control", "" );
               //:PageHeaderBottom = qBaseR_H.Group.SZDLG_Y
               {MutableInt mi_PageHeaderBottom = new MutableInt( PageHeaderBottom );
                               GetIntegerFromAttribute( mi_PageHeaderBottom, qBaseR_H, "Group", "SZDLG_Y" );
               PageHeaderBottom = mi_PageHeaderBottom.intValue( );}
               //:qBaseR_H.Group.SZDLG_Y = PageHeaderBottom + qBaseR_Source.Group.SZDLG_Y
               {MutableInt mi_lTempInteger_14 = new MutableInt( lTempInteger_14 );
                               GetIntegerFromAttribute( mi_lTempInteger_14, qBaseR_Source, "Group", "SZDLG_Y" );
               lTempInteger_14 = mi_lTempInteger_14.intValue( );}
               lTempInteger_15 = PageHeaderBottom + lTempInteger_14;
               SetAttributeFromInteger( qBaseR_H, "Group", "SZDLG_Y", lTempInteger_15 );
            } 

            //:END

            //:PositionX = FirstColumnStartingPosition
            PositionX = FirstColumnStartingPosition;
            //:// Note that the combination FOR and IF is used because of a core error as explained
            //:// in the same situation above.
            //:FOR EACH zqFrame.QueryAttribute
            RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:IF zqFrame.QueryAttribute.DisplayRow = ""
               if ( CompareAttributeToString( zqFrame, "QueryAttribute", "DisplayRow", "" ) == 0 )
               { 
                  //:Count = Count + 1
                  Count = Count + 1;
                  //:wXferO.QueryValues.WorkString = Count
                  SetAttributeFromInteger( wXferO, "QueryValues", "WorkString", Count );
                  //:Tag = zqFrame.QueryAttribute.AttributeName + wXferO.QueryValues.WorkString
                  {StringBuilder sb_Tag;
                  if ( Tag == null )
                     sb_Tag = new StringBuilder( 32 );
                  else
                     sb_Tag = new StringBuilder( Tag );
                                     GetStringFromAttribute( sb_Tag, zqFrame, "QueryAttribute", "AttributeName" );
                  Tag = sb_Tag.toString( );}
                  {StringBuilder sb_szTempString_17;
                  if ( szTempString_17 == null )
                     sb_szTempString_17 = new StringBuilder( 32 );
                  else
                     sb_szTempString_17 = new StringBuilder( szTempString_17 );
                                     GetStringFromAttribute( sb_szTempString_17, wXferO, "QueryValues", "WorkString" );
                  szTempString_17 = sb_szTempString_17.toString( );}
                   {StringBuilder sb_Tag;
                  if ( Tag == null )
                     sb_Tag = new StringBuilder( 32 );
                  else
                     sb_Tag = new StringBuilder( Tag );
                                    ZeidonStringConcat( sb_Tag, 1, 0, szTempString_17, 1, 0, 41 );
                  Tag = sb_Tag.toString( );}
                  //:PromptValue = zqFrame.QueryAttribute.Prompt
                  {MutableInt mi_lTempInteger_16 = new MutableInt( lTempInteger_16 );
                  StringBuilder sb_PromptValue;
                  if ( PromptValue == null )
                     sb_PromptValue = new StringBuilder( 32 );
                  else
                     sb_PromptValue = new StringBuilder( PromptValue );
                                     GetVariableFromAttribute( sb_PromptValue, mi_lTempInteger_16, 'S', 21, zqFrame, "QueryAttribute", "Prompt", "", 0 );
                  lTempInteger_16 = mi_lTempInteger_16.intValue( );
                  PromptValue = sb_PromptValue.toString( );}
                  //:PositionOnSourceCtrl( qBaseR_Source, zqFrame.QueryAttribute.Type )
                  {StringBuilder sb_szTempString_18;
                  if ( szTempString_18 == null )
                     sb_szTempString_18 = new StringBuilder( 32 );
                  else
                     sb_szTempString_18 = new StringBuilder( szTempString_18 );
                                     GetStringFromAttribute( sb_szTempString_18, zqFrame, "QueryAttribute", "Type" );
                  szTempString_18 = sb_szTempString_18.toString( );}
                  ozqFrame_PositionOnSourceCtrl( qBaseR_Source, szTempString_18 );
                  //:fnCreatePromptControl( qBaseR_H, qBaseR_Source, Tag, PromptValue )
                  ozqFrame_fnCreatePromptControl( qBaseR_H, qBaseR_Source, Tag, PromptValue );

                  //:// Set size to that requested.
                  //:Size = zqFrame.QueryAttribute.Length * 15
                  {MutableInt mi_lTempInteger_17 = new MutableInt( lTempInteger_17 );
                                     GetIntegerFromAttribute( mi_lTempInteger_17, zqFrame, "QueryAttribute", "Length" );
                  lTempInteger_17 = mi_lTempInteger_17.intValue( );}
                  Size = lTempInteger_17 * 15;
                  //:IF Size > 900
                  if ( Size > 900 )
                  { 
                     //:Size = 900
                     Size = 900;
                  } 

                  //:END
                  //:qBaseR_H.Control.SZDLG_X = Size
                  SetAttributeFromInteger( qBaseR_H, "Control", "SZDLG_X", Size );

                  //:qBaseR_H.Control.PSDLG_X = PositionX
                  SetAttributeFromInteger( qBaseR_H, "Control", "PSDLG_X", PositionX );
                  //:qBaseR_H.Control.PSDLG_Y = PageHeaderBottom
                  SetAttributeFromInteger( qBaseR_H, "Control", "PSDLG_Y", PageHeaderBottom );
                  //:PositionX = PositionX + qBaseR_H.Control.SZDLG_X + 36
                  {MutableInt mi_lTempInteger_18 = new MutableInt( lTempInteger_18 );
                                     GetIntegerFromAttribute( mi_lTempInteger_18, qBaseR_H, "Control", "SZDLG_X" );
                  lTempInteger_18 = mi_lTempInteger_18.intValue( );}
                  PositionX = PositionX + lTempInteger_18 + 36;
                  //:fnProcessFontOverride( zqFrameRoot, qBaseR )
                  ozqFrame_fnProcessFontOverride( zqFrameRoot, qBaseR );
               } 

               RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
               //:END
            } 

            //:END
            //:DropView( qBaseR_H )
            DropView( qBaseR_H );
         } 

         //:END
         //:ELSE
      } 
      else
      { 
         //:NewColumnStartingPosition = FirstColumnStartingPosition
         NewColumnStartingPosition = FirstColumnStartingPosition;
      } 

      //:END

      //:// Process next level down, if it exists.
      //:IF zqFrame.ChildEntity EXISTS
      lTempInteger_19 = CheckExistenceOfEntity( zqFrame, "ChildEntity" );
      if ( lTempInteger_19 == 0 )
      { 
         //:SetViewToSubobject( zqFrame, "ChildEntity" )
         SetViewToSubobject( zqFrame, "ChildEntity" );
         //:IF ParentDisplayRowFlag = "Y" OR
         //:   DisplayRowFlag = "Y"
         if ( ZeidonStringCompare( ParentDisplayRowFlag, 1, 0, "Y", 1, 0, 2 ) == 0 || ZeidonStringCompare( DisplayRowFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
         { 

            //:FormatRptDesignRecurs( zqFrame, zqFrameRoot, qBaseR, qBaseR_Source,
            //:                    QueryViewName, NewColumnStartingPosition, "Y" )
            ozqFrame_FormatRptDesignRecurs( zqFrame, zqFrameRoot, qBaseR, qBaseR_Source, QueryViewName, NewColumnStartingPosition, "Y" );
            //:ELSE
         } 
         else
         { 
            //:FormatRptDesignRecurs( zqFrame, zqFrameRoot, qBaseR, qBaseR_Source,
            //:                    QueryViewName, NewColumnStartingPosition, "" )
            ozqFrame_FormatRptDesignRecurs( zqFrame, zqFrameRoot, qBaseR, qBaseR_Source, QueryViewName, NewColumnStartingPosition, "" );
         } 

         //:END

         //:ResetViewFromSubobject( zqFrame )
         ResetViewFromSubobject( zqFrame );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:PostActivateRecurs( VIEW zqFrame     BASED ON LOD zqFrame,
//:                    VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                    VIEW vResultSet )

//:   SHORT nRC
public int 
ozqFrame_PostActivateRecurs( View     zqFrame,
                             View     zqFrameRoot,
                             View     vResultSet )
{
   int      nRC = 0;
   //:STRING ( 32 )   szEntityName
   String   szEntityName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;


   //:// Process any post activate selection entries for restricting subobject entities.
   //:// Also do any ording of the subobject as requested.
   //:SetViewToSubobject( zqFrame, "ChildEntity" )
   SetViewToSubobject( zqFrame, "ChildEntity" );
   //:FOR EACH zqFrame.ParentEntity
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szEntityName = zqFrame.ParentEntity.EntityName
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, zqFrame, "ParentEntity", "EntityName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szEntityName = sb_szEntityName.toString( );}
      //:nRC = SetCursorFirstEntity( vResultSet, szEntityName, "" )
      nRC = SetCursorFirstEntity( vResultSet, szEntityName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //:// Call routine to evaluate subselection criteria.
         //:// "N" indicates this is NOT Root Entity qualification.
         //:nRC = CheckSubgroupSelection( zqFrameRoot, vResultSet, szEntityName, "N" )
         nRC = ozqFrame_CheckSubgroupSelection( zqFrameRoot, vResultSet, szEntityName, "N" );
         //:IF nRC < 0
         if ( nRC < 0 )
         { 
            //:// Selection criteria was not met, so drop the entity.
            //:DropEntity( vResultSet, szEntityName, zREPOS_NONE )
            DropEntity( vResultSet, szEntityName, zREPOS_NONE );
            //:ELSE
         } 
         else
         { 
            //:// Perform restricting functionality on subobjects.
            //:PostActivateRecurs( zqFrame, zqFrameRoot, vResultSet )
            ozqFrame_PostActivateRecurs( zqFrame, zqFrameRoot, vResultSet );
         } 

         //:END
         //:nRC = SetCursorNextEntity( vResultSet, szEntityName, "" )
         nRC = SetCursorNextEntity( vResultSet, szEntityName, "" );
      } 

      //:END
      //:OrderRequestedEntity( zqFrameRoot, vResultSet, zqFrame.ParentEntity.EntityName )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, zqFrame, "ParentEntity", "EntityName" );
      szTempString_0 = sb_szTempString_0.toString( );}
      ozqFrame_OrderRequestedEntity( zqFrameRoot, vResultSet, szTempString_0 );
      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
   } 

   //:END
   //:ResetViewFromSubobject( zqFrame )
   ResetViewFromSubobject( zqFrame );
   return( 0 );
// END
} 


//:LOCAL OPERATION
public void 
ozqFrame_fnCreateMappingControl( View     qBaseR,
                                 View     qBaseR_Source,
                                 String   Tag,
                                 String   szViewName,
                                 String   szEntityName,
                                 String   szAttributeName )
{
   int      RESULT = 0;

   //:fnCreateMappingControl( VIEW qBaseR,
   //:                     VIEW qBaseR_Source,
   //:                     STRING ( 32 ) Tag,
   //:                     STRING ( 32 ) szViewName,
   //:                     STRING ( 32 ) szEntityName,
   //:                     STRING ( 32 ) szAttributeName )

   //:// Create a control subobject that maps an object instance value.
   //:CREATE ENTITY qBaseR.Control
   RESULT = CreateEntity( qBaseR, "Control", zPOS_AFTER );
   //:SetMatchingAttributesByName( qBaseR,        "Control",
   //:                             qBaseR_Source, "Control", zSET_NULL )
   SetMatchingAttributesByName( qBaseR, "Control", qBaseR_Source, "Control", zSET_NULL );
   //:INCLUDE qBaseR.ControlDef FROM qBaseR_Source.ControlDef
   RESULT = IncludeSubobjectFromSubobject( qBaseR, "ControlDef", qBaseR_Source, "ControlDef", zPOS_AFTER );
   //:qBaseR.Control.Tag  = Tag
   SetAttributeFromString( qBaseR, "Control", "Tag", Tag );
   //:CREATE ENTITY qBaseR.CtrlMap
   RESULT = CreateEntity( qBaseR, "CtrlMap", zPOS_AFTER );
   //:CREATE ENTITY qBaseR.CtrlMapLOD_Attribute
   RESULT = CreateEntity( qBaseR, "CtrlMapLOD_Attribute", zPOS_AFTER );
   //:CREATE ENTITY qBaseR.CtrlMapRelatedEntity
   RESULT = CreateEntity( qBaseR, "CtrlMapRelatedEntity", zPOS_AFTER );
   //:CREATE ENTITY qBaseR.CtrlMapER_Attribute
   RESULT = CreateEntity( qBaseR, "CtrlMapER_Attribute", zPOS_AFTER );
   //:CREATE ENTITY qBaseR.CtrlMapER_Domain
   RESULT = CreateEntity( qBaseR, "CtrlMapER_Domain", zPOS_AFTER );
   //:CREATE ENTITY qBaseR.CtrlMapView
   RESULT = CreateEntity( qBaseR, "CtrlMapView", zPOS_AFTER );
   //:qBaseR.CtrlMapView.Name          = szViewName
   SetAttributeFromString( qBaseR, "CtrlMapView", "Name", szViewName );
   //:qBaseR.CtrlMapRelatedEntity.Name = szEntityName
   SetAttributeFromString( qBaseR, "CtrlMapRelatedEntity", "Name", szEntityName );
   //:qBaseR.CtrlMapER_Attribute.Name  = szAttributeName
   SetAttributeFromString( qBaseR, "CtrlMapER_Attribute", "Name", szAttributeName );
   return;
// END
} 


//:LOCAL OPERATION
public void 
ozqFrame_fnCreatePromptControl( View     qBaseR,
                                View     qBaseR_Source,
                                String   Tag,
                                String   PromptValue )
{
   int      RESULT = 0;

   //:fnCreatePromptControl( VIEW qBaseR,
   //:                    VIEW qBaseR_Source,
   //:                    STRING ( 32 ) Tag,
   //:                    STRING ( 20 ) PromptValue )

   //:// Create a control subobject that presents a prompt value.
   //:CREATE ENTITY qBaseR.Control
   RESULT = CreateEntity( qBaseR, "Control", zPOS_AFTER );
   //:SetMatchingAttributesByName( qBaseR,        "Control",
   //:                             qBaseR_Source, "Control", zSET_NULL )
   SetMatchingAttributesByName( qBaseR, "Control", qBaseR_Source, "Control", zSET_NULL );
   //:INCLUDE qBaseR.ControlDef FROM qBaseR_Source.ControlDef
   RESULT = IncludeSubobjectFromSubobject( qBaseR, "ControlDef", qBaseR_Source, "ControlDef", zPOS_AFTER );
   //:qBaseR.Control.Tag  = Tag
   SetAttributeFromString( qBaseR, "Control", "Tag", Tag );
   //:qBaseR.Control.Text = PromptValue
   SetAttributeFromString( qBaseR, "Control", "Text", PromptValue );
   return;
// END
} 


//:TRANSFORMATION OPERATION
//:ExpandGeneralParms( VIEW zqFrameOrig BASED ON LOD zqFrame,
//:                    VIEW QueryView )

//:   VIEW zqFrame    BASED ON LOD zqFrame
public int 
ozqFrame_ExpandGeneralParms( View     zqFrameOrig,
                             View     QueryView )
{
   zVIEW    zqFrame = new zVIEW( );
   //:VIEW zqFrameWk  BASED ON LOD zqFrame
   zVIEW    zqFrameWk = new zVIEW( );
   //:VIEW zqFrameWk2 BASED ON LOD zqFrame
   zVIEW    zqFrameWk2 = new zVIEW( );
   int      RESULT = 0;


   //:// Expand the GeneralParamter entries with the attributes from the Query Frame (the LOD).
   //:// We will do this in 3 steps:
   //:// 1. Move the existing GeneralParamter entries to a work object.
   //:// 2. Create new GeneralParamter entries from the Query Frame.
   //:// 3. Merge the select values into the new entries.

   //:CreateViewFromView( zqFrame, zqFrameOrig )
   CreateViewFromView( zqFrame, zqFrameOrig );

   //:// Move the existing GeneralParamter entries.
   //:ACTIVATE zqFrameWk EMPTY
   RESULT = ActivateEmptyObjectInstance( zqFrameWk, "zqFrame", zqFrameOrig, zSINGLE );
   //:NAME VIEW zqFrameWk "zqFrameWk"
   SetNameForView( zqFrameWk, "zqFrameWk", null, zLEVEL_TASK );
   //:CREATE ENTITY zqFrameWk.zqFrame
   RESULT = CreateEntity( zqFrameWk, "zqFrame", zPOS_AFTER );
   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY zqFrameWk.GeneralParameter
      RESULT = CreateEntity( zqFrameWk, "GeneralParameter", zPOS_AFTER );
      //:SetMatchingAttributesByName( zqFrameWk, "GeneralParameter",
      //:                             zqFrame,   "GeneralParameter", zSET_ALL )
      SetMatchingAttributesByName( zqFrameWk, "GeneralParameter", zqFrame, "GeneralParameter", zSET_ALL );
      //:FOR EACH zqFrame.GeneralSubParameter
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralSubParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY zqFrameWk.GeneralSubParameter
         RESULT = CreateEntity( zqFrameWk, "GeneralSubParameter", zPOS_AFTER );
         //:SetMatchingAttributesByName( zqFrameWk, "GeneralSubParameter",
         //:                             zqFrame,   "GeneralSubParameter", zSET_ALL )
         SetMatchingAttributesByName( zqFrameWk, "GeneralSubParameter", zqFrame, "GeneralSubParameter", zSET_ALL );
         RESULT = SetCursorNextEntity( zqFrame, "GeneralSubParameter", "" );
      } 

      //:END
      //:DELETE ENTITY zqFrame.GeneralParameter NONE
      RESULT = DeleteEntity( zqFrame, "GeneralParameter", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
   } 

   //:END

   //:// Create new GeneralParameter entries from the Query Frame.
   //:InitializeFrameForLOD( zqFrame, QueryView )
   ozqFrame_InitializeFrameForLOD( zqFrame, QueryView );

   //:// Merge the select values back into the new entries.
   //:FOR EACH zqFrameWk.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrameWk, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST zqFrame.GeneralParameter
      //:     WHERE zqFrame.GeneralParameter.EntityName    = zqFrameWk.GeneralParameter.EntityName
      //:       AND zqFrame.GeneralParameter.AttributeName = zqFrameWk.GeneralParameter.AttributeName
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrameWk, "GeneralParameter", "EntityName" ) != 0 ||
                 CompareAttributeToAttribute( zqFrame, "GeneralParameter", "AttributeName", zqFrameWk, "GeneralParameter", "AttributeName" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// If this is a duplicate line, create new GeneralParameter.
         //:CreateViewFromView( zqFrameWk2, zqFrameWk )
         CreateViewFromView( zqFrameWk2, zqFrameWk );
         //:SET CURSOR PREVIOUS zqFrameWk2.GeneralParameter
         //:     WHERE zqFrameWk2.GeneralParameter.EntityName    = zqFrameWk.GeneralParameter.EntityName
         //:       AND zqFrameWk2.GeneralParameter.AttributeName = zqFrameWk.GeneralParameter.AttributeName
         RESULT = SetCursorPrevEntity( zqFrameWk2, "GeneralParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrameWk2, "GeneralParameter", "EntityName", zqFrameWk, "GeneralParameter", "EntityName" ) != 0 ||
                    CompareAttributeToAttribute( zqFrameWk2, "GeneralParameter", "AttributeName", zqFrameWk, "GeneralParameter", "AttributeName" ) != 0 ) )
            { 
               RESULT = SetCursorPrevEntity( zqFrameWk2, "GeneralParameter", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET
         if ( RESULT >= zCURSOR_SET )
         { 
            //:CREATE ENTITY zqFrame.GeneralParameter
            RESULT = CreateEntity( zqFrame, "GeneralParameter", zPOS_AFTER );
         } 

         //:END
         //:DropView( zqFrameWk2 )
         DropView( zqFrameWk2 );
         //:SetMatchingAttributesByName( zqFrame,   "GeneralParameter",
         //:                             zqFrameWk, "GeneralParameter", zSET_ALL )
         SetMatchingAttributesByName( zqFrame, "GeneralParameter", zqFrameWk, "GeneralParameter", zSET_ALL );
      } 

      //:END
      //:FOR EACH zqFrameWk.GeneralSubParameter
      RESULT = SetCursorFirstEntity( zqFrameWk, "GeneralSubParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:CREATE ENTITY zqFrame.GeneralSubParameter
         RESULT = CreateEntity( zqFrame, "GeneralSubParameter", zPOS_AFTER );
         //:SetMatchingAttributesByName( zqFrame,   "GeneralSubParameter",
         //:                             zqFrameWk, "GeneralSubParameter", zSET_ALL )
         SetMatchingAttributesByName( zqFrame, "GeneralSubParameter", zqFrameWk, "GeneralSubParameter", zSET_ALL );
         RESULT = SetCursorNextEntity( zqFrameWk, "GeneralSubParameter", "" );
      } 

      RESULT = SetCursorNextEntity( zqFrameWk, "GeneralParameter", "" );
      //:END
   } 

   //:END

   //:DropObjectInstance( zqFrameWk )
   DropObjectInstance( zqFrameWk );
   //:DropView( zqFrame )
   DropView( zqFrame );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:LocateEntityRecurs( VIEW zqFrame BASED ON LOD zqFrame,
//:                    STRING ( 32 ) szEntityName )

//:   STRING ( 50 ) szRecursiveEntityName
public int 
ozqFrame_LocateEntityRecurs( View     zqFrame,
                             String   szEntityName )
{
   String   szRecursiveEntityName = null;
   //:INTEGER  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;
   int      lTempInteger_1 = 0;


   //:// Follow the ParentEntity subobject down until the entity is found.
   //://TraceLineS( "Top Entity: ", zqFrame.ParentEntity.EntityName )
   //:IF zqFrame.ChildEntity EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "ChildEntity" );
   if ( lTempInteger_0 == 0 )
   { 
      //:SetViewToSubobject( zqFrame, "ChildEntity" )
      SetViewToSubobject( zqFrame, "ChildEntity" );
      //:FOR EACH zqFrame.ParentEntity
      RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szRecursiveEntityName = zqFrame.ParentEntity.EntityName
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szRecursiveEntityName;
         if ( szRecursiveEntityName == null )
            sb_szRecursiveEntityName = new StringBuilder( 32 );
         else
            sb_szRecursiveEntityName = new StringBuilder( szRecursiveEntityName );
                   GetVariableFromAttribute( sb_szRecursiveEntityName, mi_lTempInteger_1, 'S', 51, zqFrame, "ParentEntity", "EntityName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szRecursiveEntityName = sb_szRecursiveEntityName.toString( );}
         //:IF szRecursiveEntityName = szEntityName
         if ( ZeidonStringCompare( szRecursiveEntityName, 1, 0, szEntityName, 1, 0, 51 ) == 0 )
         { 
            //:RETURN 0
            if(8==8)return( 0 );
         } 

         //:END
         //:nRC = LocateEntityRecurs( zqFrame, szEntityName )
         nRC = ozqFrame_LocateEntityRecurs( zqFrame, szEntityName );
         //:IF nRC = 0
         if ( nRC == 0 )
         { 
            //:RETURN 0
            if(8==8)return( 0 );
         } 

         RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
         //:END
      } 

      //:END
      //:ResetViewFromSubobject( zqFrame )
      ResetViewFromSubobject( zqFrame );
   } 

   //:END
   //:RETURN -1
   return( -1 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetUpOrderingInfo( VIEW zqFrame BASED ON LOD zqFrame )

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_SetUpOrderingInfo( View     zqFrame )
{
   zVIEW    zqFrame2 = new zVIEW( );
   //:STRING ( 32 )  szSortEntity
   String   szSortEntity = null;
   //:STRING ( 254 ) szOrderQualifier
   String   szOrderQualifier = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_2 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_3 = 0;


   //:// Set up the ordering subobject information for ordering entities.
   //:// The top entity in the structure identifies the entity to be ordered, which is either
   //:// the root or a 0-to-m subentity. An attribute from a 0-to-1 subentity will affect the
   //:// ordering of the first entity in its parent chain that is 0-to-m.

   //:FOR EACH zqFrame.SortOrderEntity
   RESULT = SetCursorFirstEntity( zqFrame, "SortOrderEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.SortOrderEntity NONE
      RESULT = DeleteEntity( zqFrame, "SortOrderEntity", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "SortOrderEntity", "" );
   } 

   //:END
   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF zqFrame.GeneralParameter.SortOrder != ""
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SortOrder", "" ) != 0 )
      { 
         //:CreateViewFromView( zqFrame2, zqFrame )
         CreateViewFromView( zqFrame2, zqFrame );
         //:LocateEntityRecurs( zqFrame2, zqFrame.GeneralParameter.EntityName )
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, zqFrame, "GeneralParameter", "EntityName" );
         szTempString_0 = sb_szTempString_0.toString( );}
         ozqFrame_LocateEntityRecurs( zqFrame2, szTempString_0 );
         //:IF zqFrame2.ParentEntity.MaxCardinality = 1
         if ( CompareAttributeToInteger( zqFrame2, "ParentEntity", "MaxCardinality", 1 ) == 0 )
         { 
            //:// Locate the first parent with max cardinality > 1
            //:LOOP WHILE zqFrame2.ParentEntity.MaxCardinality = 1
            while ( CompareAttributeToInteger( zqFrame2, "ParentEntity", "MaxCardinality", 1 ) == 0 )
            { 
               //:ResetViewFromSubobject( zqFrame2 )
               ResetViewFromSubobject( zqFrame2 );
            } 

            //:END
         } 

         //:END
         //:szSortEntity = zqFrame2.ParentEntity.EntityName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szSortEntity;
         if ( szSortEntity == null )
            sb_szSortEntity = new StringBuilder( 32 );
         else
            sb_szSortEntity = new StringBuilder( szSortEntity );
                   GetVariableFromAttribute( sb_szSortEntity, mi_lTempInteger_0, 'S', 33, zqFrame2, "ParentEntity", "EntityName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szSortEntity = sb_szSortEntity.toString( );}
         //:DropView( zqFrame2 )
         DropView( zqFrame2 );
         //:SET CURSOR FIRST zqFrame.SortOrderEntity
         //:      WHERE zqFrame.SortOrderEntity.EntityName = szSortEntity
         RESULT = SetCursorFirstEntityByString( zqFrame, "SortOrderEntity", "EntityName", szSortEntity, "" );
         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:CREATE ENTITY zqFrame.SortOrderEntity
            RESULT = CreateEntity( zqFrame, "SortOrderEntity", zPOS_AFTER );
            //:zqFrame.SortOrderEntity.EntityName = szSortEntity
            SetAttributeFromString( zqFrame, "SortOrderEntity", "EntityName", szSortEntity );
         } 

         //:END
         //:CREATE ENTITY zqFrame.SortOrderAttribute
         RESULT = CreateEntity( zqFrame, "SortOrderAttribute", zPOS_AFTER );
         //:zqFrame.SortOrderAttribute.EntityName             = zqFrame.GeneralParameter.EntityName
         SetAttributeFromAttribute( zqFrame, "SortOrderAttribute", "EntityName", zqFrame, "GeneralParameter", "EntityName" );
         //:zqFrame.SortOrderAttribute.AttributeName          = zqFrame.GeneralParameter.AttributeName
         SetAttributeFromAttribute( zqFrame, "SortOrderAttribute", "AttributeName", zqFrame, "GeneralParameter", "AttributeName" );
         //:zqFrame.SortOrderAttribute.SortOrder              = zqFrame.GeneralParameter.SortOrder
         SetAttributeFromAttribute( zqFrame, "SortOrderAttribute", "SortOrder", zqFrame, "GeneralParameter", "SortOrder" );
         //:IF zqFrame.GeneralParameter.SortOrderAscendingType = ""
         if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SortOrderAscendingType", "" ) == 0 )
         { 
            //:zqFrame.SortOrderAttribute.SortOrderAscendingType = "A"
            SetAttributeFromString( zqFrame, "SortOrderAttribute", "SortOrderAscendingType", "A" );
            //:ELSE
         } 
         else
         { 
            //:zqFrame.SortOrderAttribute.SortOrderAscendingType = zqFrame.GeneralParameter.SortOrderAscendingType
            SetAttributeFromAttribute( zqFrame, "SortOrderAttribute", "SortOrderAscendingType", zqFrame, "GeneralParameter", "SortOrderAscendingType" );
         } 

         //:END
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END

   //:// Go through each entity and set up the ordering text from the attribute information.
   //:OrderEntityForView( zqFrame, "SortOrderAttribute", "SortOrder A" )
   OrderEntityForView( zqFrame, "SortOrderAttribute", "SortOrder A" );
   //:FOR EACH zqFrame.SortOrderEntity
   RESULT = SetCursorFirstEntity( zqFrame, "SortOrderEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:szOrderQualifier = ""
       {StringBuilder sb_szOrderQualifier;
      if ( szOrderQualifier == null )
         sb_szOrderQualifier = new StringBuilder( 32 );
      else
         sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
            ZeidonStringCopy( sb_szOrderQualifier, 1, 0, "", 1, 0, 255 );
      szOrderQualifier = sb_szOrderQualifier.toString( );}
      //:FOR EACH zqFrame.SortOrderAttribute
      RESULT = SetCursorFirstEntity( zqFrame, "SortOrderAttribute", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:szOrderQualifier = szOrderQualifier +
         //:                   zqFrame.SortOrderAttribute.EntityName + "." +
         //:                   zqFrame.SortOrderAttribute.AttributeName + " " +
         //:                   zqFrame.SortOrderAttribute.SortOrderAscendingType + " "
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 33, zqFrame, "SortOrderAttribute", "EntityName", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
          {StringBuilder sb_szOrderQualifier;
         if ( szOrderQualifier == null )
            sb_szOrderQualifier = new StringBuilder( 32 );
         else
            sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
                  ZeidonStringConcat( sb_szOrderQualifier, 1, 0, szTempString_1, 1, 0, 255 );
         szOrderQualifier = sb_szOrderQualifier.toString( );}
          {StringBuilder sb_szOrderQualifier;
         if ( szOrderQualifier == null )
            sb_szOrderQualifier = new StringBuilder( 32 );
         else
            sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
                  ZeidonStringConcat( sb_szOrderQualifier, 1, 0, ".", 1, 0, 255 );
         szOrderQualifier = sb_szOrderQualifier.toString( );}
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetVariableFromAttribute( sb_szTempString_2, mi_lTempInteger_2, 'S', 33, zqFrame, "SortOrderAttribute", "AttributeName", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szTempString_2 = sb_szTempString_2.toString( );}
          {StringBuilder sb_szOrderQualifier;
         if ( szOrderQualifier == null )
            sb_szOrderQualifier = new StringBuilder( 32 );
         else
            sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
                  ZeidonStringConcat( sb_szOrderQualifier, 1, 0, szTempString_2, 1, 0, 255 );
         szOrderQualifier = sb_szOrderQualifier.toString( );}
          {StringBuilder sb_szOrderQualifier;
         if ( szOrderQualifier == null )
            sb_szOrderQualifier = new StringBuilder( 32 );
         else
            sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
                  ZeidonStringConcat( sb_szOrderQualifier, 1, 0, " ", 1, 0, 255 );
         szOrderQualifier = sb_szOrderQualifier.toString( );}
         {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
         StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetVariableFromAttribute( sb_szTempString_3, mi_lTempInteger_3, 'S', 2, zqFrame, "SortOrderAttribute", "SortOrderAscendingType", "", 0 );
         lTempInteger_3 = mi_lTempInteger_3.intValue( );
         szTempString_3 = sb_szTempString_3.toString( );}
          {StringBuilder sb_szOrderQualifier;
         if ( szOrderQualifier == null )
            sb_szOrderQualifier = new StringBuilder( 32 );
         else
            sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
                  ZeidonStringConcat( sb_szOrderQualifier, 1, 0, szTempString_3, 1, 0, 255 );
         szOrderQualifier = sb_szOrderQualifier.toString( );}
          {StringBuilder sb_szOrderQualifier;
         if ( szOrderQualifier == null )
            sb_szOrderQualifier = new StringBuilder( 32 );
         else
            sb_szOrderQualifier = new StringBuilder( szOrderQualifier );
                  ZeidonStringConcat( sb_szOrderQualifier, 1, 0, " ", 1, 0, 255 );
         szOrderQualifier = sb_szOrderQualifier.toString( );}
         RESULT = SetCursorNextEntity( zqFrame, "SortOrderAttribute", "" );
      } 

      //:END
      //:zqFrame.SortOrderEntity.OrderQualifier = szOrderQualifier
      SetAttributeFromString( zqFrame, "SortOrderEntity", "OrderQualifier", szOrderQualifier );
      RESULT = SetCursorNextEntity( zqFrame, "SortOrderEntity", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
public int 
ozqFrame_OrderRequestedEntity( View     zqFrameRoot,
                               View     vResultSet,
                               String   szEntityName )
{
   int      RESULT = 0;
   String   szTempString_0 = null;

   //:OrderRequestedEntity( VIEW zqFrameRoot BASED ON LOD zqFrame,
   //:                   VIEW vResultSet,
   //:                   STRING ( 32 ) szEntityName )

   //:// Order the entity as requested.

   //:SET CURSOR FIRST zqFrameRoot.SortOrderEntity
   //:      WHERE zqFrameRoot.SortOrderEntity.EntityName = szEntityName
   RESULT = SetCursorFirstEntityByString( zqFrameRoot, "SortOrderEntity", "EntityName", szEntityName, "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:OrderEntityForView( vResultSet, szEntityName, zqFrameRoot.SortOrderEntity.OrderQualifier )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, zqFrameRoot, "SortOrderEntity", "OrderQualifier" );
      szTempString_0 = sb_szTempString_0.toString( );}
      OrderEntityForView( vResultSet, szEntityName, szTempString_0 );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:MergeResultSets( VIEW zqFrame BASED ON LOD zqFrame,
//:                 VIEW vResultSetTarget,
//:                 VIEW vResultSetSource,
//:                 STRING ( 32 ) szEntityName )

//:   STRING ( 32 ) szAttributeName
public int 
ozqFrame_MergeResultSets( View     zqFrame,
                          View     vResultSetTarget,
                          View     vResultSetSource,
                          String   szEntityName )
{
   String   szAttributeName = null;
   //:STRING ( 40 ) szOrderValue
   String   szOrderValue = null;
   //:INTEGER       lID
   int      lID = 0;
   //:INTEGER       Count
   int      Count = 0;
   //:SHORT         nRC
   int      nRC = 0;


   //:// Merge the roots of the Source Result Set into the Target Result Set.

   //:// The attribute name of the id is different for some entities in ZDM.
   //:IF szEntityName = "Customer"
   if ( ZeidonStringCompare( szEntityName, 1, 0, "Customer", 1, 0, 33 ) == 0 )
   { 
      //:szAttributeName = "CustomerID"
       {StringBuilder sb_szAttributeName;
      if ( szAttributeName == null )
         sb_szAttributeName = new StringBuilder( 32 );
      else
         sb_szAttributeName = new StringBuilder( szAttributeName );
            ZeidonStringCopy( sb_szAttributeName, 1, 0, "CustomerID", 1, 0, 33 );
      szAttributeName = sb_szAttributeName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:IF szEntityName = "SalesOrder"
      if ( ZeidonStringCompare( szEntityName, 1, 0, "SalesOrder", 1, 0, 33 ) == 0 )
      { 
         //:szAttributeName = "OrderID"
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringCopy( sb_szAttributeName, 1, 0, "OrderID", 1, 0, 33 );
         szAttributeName = sb_szAttributeName.toString( );}
         //:ELSE
      } 
      else
      { 
         //:IF szEntityName = "Employee"
         if ( ZeidonStringCompare( szEntityName, 1, 0, "Employee", 1, 0, 33 ) == 0 )
         { 
            //:szAttributeName = "Empl_ID"
             {StringBuilder sb_szAttributeName;
            if ( szAttributeName == null )
               sb_szAttributeName = new StringBuilder( 32 );
            else
               sb_szAttributeName = new StringBuilder( szAttributeName );
                        ZeidonStringCopy( sb_szAttributeName, 1, 0, "Empl_ID", 1, 0, 33 );
            szAttributeName = sb_szAttributeName.toString( );}
            //:ELSE
         } 
         else
         { 
            //:IF szEntityName = "AccountingCategory"
            if ( ZeidonStringCompare( szEntityName, 1, 0, "AccountingCategory", 1, 0, 33 ) == 0 )
            { 
               //:szAttributeName = "InternalID"
                {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                              ZeidonStringCopy( sb_szAttributeName, 1, 0, "InternalID", 1, 0, 33 );
               szAttributeName = sb_szAttributeName.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szAttributeName = "ID"
                {StringBuilder sb_szAttributeName;
               if ( szAttributeName == null )
                  sb_szAttributeName = new StringBuilder( 32 );
               else
                  sb_szAttributeName = new StringBuilder( szAttributeName );
                              ZeidonStringCopy( sb_szAttributeName, 1, 0, "ID", 1, 0, 33 );
               szAttributeName = sb_szAttributeName.toString( );}
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   //:nRC = SetCursorFirstEntity( vResultSetSource, szEntityName, "" )
   nRC = SetCursorFirstEntity( vResultSetSource, szEntityName, "" );
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 
      //:GetIntegerFromAttribute( lID, vResultSetSource, szEntityName, szAttributeName )
      {MutableInt mi_lID = new MutableInt( lID );
             GetIntegerFromAttribute( mi_lID, vResultSetSource, szEntityName, szAttributeName );
      lID = mi_lID.intValue( );}
      //:nRC = SetCursorFirstEntityByInteger( vResultSetTarget, szEntityName, szAttributeName, lID, "" )
      nRC = SetCursorFirstEntityByInteger( vResultSetTarget, szEntityName, szAttributeName, lID, "" );
      //:IF nRC < zCURSOR_SET
      if ( nRC < zCURSOR_SET )
      { 
         //:SetCursorLastEntity( vResultSetTarget, szEntityName, "" )
         SetCursorLastEntity( vResultSetTarget, szEntityName, "" );
         //:IncludeSubobjectFromSubobject( vResultSetTarget, szEntityName,
         //:                               vResultSetSource, szEntityName, zPOS_AFTER )
         IncludeSubobjectFromSubobject( vResultSetTarget, szEntityName, vResultSetSource, szEntityName, zPOS_AFTER );
      } 

      //:   // Set the work attributes.
      //:   /*SetAttributeFromAttribute( vResultSetTarget, szEntityName, "wSubgroupValue1",
      //:                              vResultSetSource, szEntityName, "wSubgroupValue1" )
      //:   SetAttributeFromAttribute( vResultSetTarget, szEntityName, "wSubgroupValue2",
      //:                              vResultSetSource, szEntityName, "wSubgroupValue2" )
      //:   SetAttributeFromAttribute( vResultSetTarget, szEntityName, "wSubgroupValue3",
      //:                              vResultSetSource, szEntityName, "wSubgroupValue3" )
      //:   SetAttributeFromAttribute( vResultSetTarget, szEntityName, "wSubgroupValue4",
      //:                              vResultSetSource, szEntityName, "wSubgroupValue4" )*/
      //:END
      //:nRC = SetCursorNextEntity( vResultSetSource, szEntityName, "" )
      nRC = SetCursorNextEntity( vResultSetSource, szEntityName, "" );
   } 

   //:END
   //:szOrderValue = szAttributeName + " A"
    {StringBuilder sb_szOrderValue;
   if ( szOrderValue == null )
      sb_szOrderValue = new StringBuilder( 32 );
   else
      sb_szOrderValue = new StringBuilder( szOrderValue );
      ZeidonStringCopy( sb_szOrderValue, 1, 0, szAttributeName, 1, 0, 41 );
   szOrderValue = sb_szOrderValue.toString( );}
    {StringBuilder sb_szOrderValue;
   if ( szOrderValue == null )
      sb_szOrderValue = new StringBuilder( 32 );
   else
      sb_szOrderValue = new StringBuilder( szOrderValue );
      ZeidonStringConcat( sb_szOrderValue, 1, 0, " A", 1, 0, 41 );
   szOrderValue = sb_szOrderValue.toString( );}
   //:OrderEntityForView( vResultSetTarget, szEntityName, szOrderValue )
   OrderEntityForView( vResultSetTarget, szEntityName, szOrderValue );
   //:Count = 0
   Count = 0;
   //:nRC = SetCursorFirstEntity( vResultSetTarget, szEntityName, "" )
   nRC = SetCursorFirstEntity( vResultSetTarget, szEntityName, "" );
   //:LOOP WHILE nRC >= zCURSOR_SET
   while ( nRC >= zCURSOR_SET )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:nRC = SetCursorNextEntity( vResultSetTarget, szEntityName, "" )
      nRC = SetCursorNextEntity( vResultSetTarget, szEntityName, "" );
   } 

   //:END
   //:SetCursorFirstEntity( vResultSetTarget, szEntityName, "" )
   SetCursorFirstEntity( vResultSetTarget, szEntityName, "" );
   //:zqFrame.zqFrame.CountOfReturnedRoots = Count
   SetAttributeFromInteger( zqFrame, "zqFrame", "CountOfReturnedRoots", Count );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildPostQualEntry( VIEW zqFrame BASED ON LOD zqFrame )

//:   STRING ( 1 )   DataType
public int 
ozqFrame_BuildPostQualEntry( View     zqFrame )
{
   String   DataType = null;
   //:STRING ( 10 )  DateString
   String   DateString = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;


   //:// Build a PostActivate qualification entry from the GeneralParameter subobject on which
   //:// the cursor is currently positioned.

   //:CREATE ENTITY zqFrame.PostActivateQual
   RESULT = CreateEntity( zqFrame, "PostActivateQual", zPOS_AFTER );
   //:SetMatchingAttributesByName( zqFrame, "PostActivateQual",
   //:                             zqFrame, "GeneralParameter", zSET_ALL )
   SetMatchingAttributesByName( zqFrame, "PostActivateQual", zqFrame, "GeneralParameter", zSET_ALL );
   //:IF zqFrame.PostActivateQual.ScopingEntityName = ""
   if ( CompareAttributeToString( zqFrame, "PostActivateQual", "ScopingEntityName", "" ) == 0 )
   { 
      //:zqFrame.PostActivateQual.ScopingEntityName = zqFrame.zqFrame.QueryObjectRootEntityName
      SetAttributeFromAttribute( zqFrame, "PostActivateQual", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" );
   } 

   //:END
   //:DataType = zqFrame.PostActivateQual.DataType
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_DataType;
   if ( DataType == null )
      sb_DataType = new StringBuilder( 32 );
   else
      sb_DataType = new StringBuilder( DataType );
       GetVariableFromAttribute( sb_DataType, mi_lTempInteger_0, 'S', 2, zqFrame, "PostActivateQual", "DataType", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   DataType = sb_DataType.toString( );}
   //:IF DataType = "D" OR DataType = "T"
   if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
   { 
      //:// For Date or DateTime, convert the Value.
      //:DateString = zqFrame.PostActivateQual.Value
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_DateString;
      if ( DateString == null )
         sb_DateString = new StringBuilder( 32 );
      else
         sb_DateString = new StringBuilder( DateString );
             GetVariableFromAttribute( sb_DateString, mi_lTempInteger_1, 'S', 11, zqFrame, "PostActivateQual", "Value", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      DateString = sb_DateString.toString( );}
      //:nRC = SetAttrFromStrByContext( zqFrame, "PostActivateQual", "ValueDate",
      //:                               DateString, "M/D/YYYY" )
      {
       ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
       nRC = m_ZGLOBAL1_Operation.SetAttrFromStrByContext( zqFrame, "PostActivateQual", "ValueDate", DateString, "M/D/YYYY" );
       // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
      }
      //:IF nRC < 0
      if ( nRC < 0 )
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
   } 

   //:END

   //:// Build the Sub Qualifiers.
   //:FOR EACH zqFrame.GeneralSubParameter
   //:       WHERE zqFrame.GeneralSubParameter.SearchType != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralSubParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "" ) != 0 )
      { 
         //:CREATE ENTITY zqFrame.PostActivateQualSub
         RESULT = CreateEntity( zqFrame, "PostActivateQualSub", zPOS_AFTER );
         //:SetMatchingAttributesByName( zqFrame, "PostActivateQualSub",
         //:                          zqFrame, "GeneralSubParameter", zSET_ALL )
         SetMatchingAttributesByName( zqFrame, "PostActivateQualSub", zqFrame, "GeneralSubParameter", zSET_ALL );
         //:IF DataType = "D" OR DataType = "T"
         if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 || ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
         { 
            //:// For Date or DateTime, convert the Value.
            //:DateString = zqFrame.PostActivateQualSub.Value
            {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
            StringBuilder sb_DateString;
            if ( DateString == null )
               sb_DateString = new StringBuilder( 32 );
            else
               sb_DateString = new StringBuilder( DateString );
                         GetVariableFromAttribute( sb_DateString, mi_lTempInteger_2, 'S', 11, zqFrame, "PostActivateQualSub", "Value", "", 0 );
            lTempInteger_2 = mi_lTempInteger_2.intValue( );
            DateString = sb_DateString.toString( );}
            //:nRC = SetAttrFromStrByContext( zqFrame, "PostActivateQualSub", "ValueDate",
            //:                            DateString, "M/D/YYYY" )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
             nRC = m_ZGLOBAL1_Operation.SetAttrFromStrByContext( zqFrame, "PostActivateQualSub", "ValueDate", DateString, "M/D/YYYY" );
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:IF nRC < 0
            if ( nRC < 0 )
            { 
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
         } 

      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralSubParameter", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:BuildPostGeoPosEntry( VIEW zqFrame BASED ON LOD zqFrame )

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_BuildPostGeoPosEntry( View     zqFrame )
{
   zVIEW    zqFrame2 = new zVIEW( );
   //:DECIMAL CenterLatitude
   double  CenterLatitude = 0.0;
   //:DECIMAL CenterLongitude
   double  CenterLongitude = 0.0;
   //:DECIMAL DistanceMiles
   double  DistanceMiles = 0.0;
   //:STRING ( 10 ) szZipCode
   String   szZipCode = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Build a PostActivate qualification entry for geographic position.

   //:// Build regular entry.
   //:CREATE ENTITY zqFrame.PostActivateQual
   RESULT = CreateEntity( zqFrame, "PostActivateQual", zPOS_AFTER );
   //:SetMatchingAttributesByName( zqFrame, "PostActivateQual",
   //:                             zqFrame, "GeneralParameter", zSET_ALL )
   SetMatchingAttributesByName( zqFrame, "PostActivateQual", zqFrame, "GeneralParameter", zSET_ALL );
   //:IF zqFrame.PostActivateQual.ScopingEntityName = ""
   if ( CompareAttributeToString( zqFrame, "PostActivateQual", "ScopingEntityName", "" ) == 0 )
   { 
      //:zqFrame.PostActivateQual.ScopingEntityName = zqFrame.zqFrame.QueryObjectRootEntityName
      SetAttributeFromAttribute( zqFrame, "PostActivateQual", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" );
   } 

   //:END

   //:// Build geographic entry.
   //:CREATE ENTITY zqFrame.PostActivateGeographicPosition
   RESULT = CreateEntity( zqFrame, "PostActivateGeographicPosition", zPOS_AFTER );
   //:szZipCode = zqFrame.GeneralParameter.Value
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szZipCode;
   if ( szZipCode == null )
      sb_szZipCode = new StringBuilder( 32 );
   else
      sb_szZipCode = new StringBuilder( szZipCode );
       GetVariableFromAttribute( sb_szZipCode, mi_lTempInteger_0, 'S', 11, zqFrame, "GeneralParameter", "Value", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szZipCode = sb_szZipCode.toString( );}
   //:GetZipLatLong( szZipCode, zqFrame, CenterLatitude, CenterLongitude )
   {
    ZGLOBAL2_Operation m_ZGLOBAL2_Operation = new ZGLOBAL2_Operation( zqFrame );
    {MutableDouble md_CenterLongitude = new MutableDouble( CenterLongitude );
   MutableDouble md_CenterLatitude = new MutableDouble( CenterLatitude );
       m_ZGLOBAL2_Operation.GetZipLatLong( szZipCode, zqFrame, md_CenterLatitude, md_CenterLongitude );
   CenterLongitude = md_CenterLongitude.doubleValue( );
   CenterLatitude = md_CenterLatitude.doubleValue( );}
    // m_ZGLOBAL2_Operation = null;  // permit gc  (unnecessary)
   }
   //:zqFrame.PostActivateGeographicPosition.CenterLatitude  = CenterLatitude
   SetAttributeFromDecimal( zqFrame, "PostActivateGeographicPosition", "CenterLatitude", CenterLatitude );
   //:zqFrame.PostActivateGeographicPosition.CenterLongitude = CenterLongitude
   SetAttributeFromDecimal( zqFrame, "PostActivateGeographicPosition", "CenterLongitude", CenterLongitude );
   //:CreateViewFromView( zqFrame2, zqFrame )
   CreateViewFromView( zqFrame2, zqFrame );
   //:SET CURSOR FIRST zqFrame2.GeneralParameter
   //:           WHERE zqFrame2.GeneralParameter.AttributeName = "GeographicPositionMiles"
   //:             AND zqFrame2.GeneralParameter.EntityName    = zqFrame.GeneralParameter.EntityName
   RESULT = SetCursorFirstEntity( zqFrame2, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame2, "GeneralParameter", "AttributeName", "GeographicPositionMiles" ) != 0 ||
              CompareAttributeToAttribute( zqFrame2, "GeneralParameter", "EntityName", zqFrame, "GeneralParameter", "EntityName" ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame2, "GeneralParameter", "" );
      } 

   } 

   //:DistanceMiles = zqFrame2.GeneralParameter.Value
   {MutableDouble md_DistanceMiles = new MutableDouble( DistanceMiles );
       GetDecimalFromAttribute( md_DistanceMiles, zqFrame2, "GeneralParameter", "Value" );
   DistanceMiles = md_DistanceMiles.doubleValue( );}
   //:zqFrame.PostActivateGeographicPosition.DistanceMiles = DistanceMiles
   SetAttributeFromDecimal( zqFrame, "PostActivateGeographicPosition", "DistanceMiles", DistanceMiles );
   //:zqFrame.PostActivateQual.SearchType = zqFrame2.GeneralParameter.SearchType
   SetAttributeFromAttribute( zqFrame, "PostActivateQual", "SearchType", zqFrame2, "GeneralParameter", "SearchType" );
   //:DropView( zqFrame2 )
   DropView( zqFrame2 );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:BuildConditionSubobj( VIEW zqFrame BASED ON LOD zqFrame,
//:                      VIEW vQualObject,
//:                      VIEW qConvertData )
//:   STRING ( 32 )    szEntityName
public int 
ozqFrame_BuildConditionSubobj( View     zqFrame,
                               View     vQualObject,
                               View     qConvertData )
{
   String   szEntityName = null;
   //:STRING ( 32 )    szAttributeName
   String   szAttributeName = null;
   //:STRING ( 10 )    szSearchType
   String   szSearchType = null;
   //:STRING ( 100 )   szInListValue
   String   szInListValue = null;
   //:STRING ( 100 )   Msg
   String   Msg = null;
   //:STRING ( 10000000 ) szValuesAsFindInList
   String   szValuesAsFindInList = null;
   //:INTEGER nRC
   int      nRC = 0;
   //:INTEGER Count
   int      Count = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      RESULT = 0;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   String   szTempString_3 = null;


   //:szEntityName    = zqFrame.GeneralParameter.EntityName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szEntityName;
   if ( szEntityName == null )
      sb_szEntityName = new StringBuilder( 32 );
   else
      sb_szEntityName = new StringBuilder( szEntityName );
       GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_0, 'S', 33, zqFrame, "GeneralParameter", "EntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szEntityName = sb_szEntityName.toString( );}
   //:szAttributeName = zqFrame.GeneralParameter.AttributeName
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szAttributeName;
   if ( szAttributeName == null )
      sb_szAttributeName = new StringBuilder( 32 );
   else
      sb_szAttributeName = new StringBuilder( szAttributeName );
       GetVariableFromAttribute( sb_szAttributeName, mi_lTempInteger_1, 'S', 33, zqFrame, "GeneralParameter", "AttributeName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szAttributeName = sb_szAttributeName.toString( );}
   //:szSearchType = zqFrame.GeneralParameter.SearchType
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szSearchType;
   if ( szSearchType == null )
      sb_szSearchType = new StringBuilder( 32 );
   else
      sb_szSearchType = new StringBuilder( szSearchType );
       GetVariableFromAttribute( sb_szSearchType, mi_lTempInteger_2, 'S', 11, zqFrame, "GeneralParameter", "SearchType", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szSearchType = sb_szSearchType.toString( );}
   //:IF szSearchType = "contains"
   if ( ZeidonStringCompare( szSearchType, 1, 0, "contains", 1, 0, 11 ) == 0 )
   { 
      //:nRC = BuildQualEntry( zqFrame,
      //:                      szEntityName,
      //:                      szAttributeName,
      //:                      szSearchType,
      //:                      zqFrame.GeneralParameter.dValue,
      //:                      qConvertData, vQualObject )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, zqFrame, "GeneralParameter", "dValue" );
      szTempString_0 = sb_szTempString_0.toString( );}
      nRC = ozqFrame_BuildQualEntry( zqFrame, szEntityName, szAttributeName, szSearchType, szTempString_0, qConvertData, vQualObject );
      //:IF nRC < 0
      if ( nRC < 0 )
      { 
         //:RETURN -1
         if(8==8)return( -1 );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:IF szSearchType = "subselect"
      if ( ZeidonStringCompare( szSearchType, 1, 0, "subselect", 1, 0, 11 ) == 0 )
      { 
         //:// Add any sub parameters within parens.
         //:SET CURSOR FIRST zqFrame.GeneralSubParameter
         //:           WHERE zqFrame.GeneralSubParameter.SearchType != ""
         RESULT = SetCursorFirstEntity( zqFrame, "GeneralSubParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "" ) == 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrame, "GeneralSubParameter", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:Msg = "The subselect operator does not have subparameters."
             {StringBuilder sb_Msg;
            if ( Msg == null )
               sb_Msg = new StringBuilder( 32 );
            else
               sb_Msg = new StringBuilder( Msg );
                        ZeidonStringCopy( sb_Msg, 1, 0, "The subselect operator does not have subparameters.", 1, 0, 101 );
            Msg = sb_Msg.toString( );}
            //:MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
            MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
         //:CREATE ENTITY vQualObject.QualAttrib
         RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
         //:vQualObject.QualAttrib.Oper = "("
         SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
         //:FOR EACH zqFrame.GeneralSubParameter
         RESULT = SetCursorFirstEntity( zqFrame, "GeneralSubParameter", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:IF zqFrame.GeneralSubParameter.SearchType != ""
            if ( CompareAttributeToString( zqFrame, "GeneralSubParameter", "SearchType", "" ) != 0 )
            { 
               //:nRC = BuildQualEntry( zqFrame,
               //:                      szEntityName,
               //:                      szAttributeName,
               //:                      zqFrame.GeneralSubParameter.SearchType,
               //:                      zqFrame.GeneralSubParameter.dValue,
               //:                      qConvertData,
               //:                      vQualObject )
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, zqFrame, "GeneralSubParameter", "SearchType" );
               szTempString_1 = sb_szTempString_1.toString( );}
               {StringBuilder sb_szTempString_2;
               if ( szTempString_2 == null )
                  sb_szTempString_2 = new StringBuilder( 32 );
               else
                  sb_szTempString_2 = new StringBuilder( szTempString_2 );
                               GetStringFromAttribute( sb_szTempString_2, zqFrame, "GeneralSubParameter", "dValue" );
               szTempString_2 = sb_szTempString_2.toString( );}
               nRC = ozqFrame_BuildQualEntry( zqFrame, szEntityName, szAttributeName, szTempString_1, szTempString_2, qConvertData, vQualObject );
               //:IF nRC < 0
               if ( nRC < 0 )
               { 
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:CREATE ENTITY vQualObject.QualAttrib
               RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               //:vQualObject.QualAttrib.Oper = zqFrame.GeneralParameter.SecondLevelOperator
               SetAttributeFromAttribute( vQualObject, "QualAttrib", "Oper", zqFrame, "GeneralParameter", "SecondLevelOperator" );
            } 

            RESULT = SetCursorNextEntity( zqFrame, "GeneralSubParameter", "" );
            //:END
         } 

         //:END
         //:vQualObject.QualAttrib.Oper = ")"
         SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
         //:ELSE
      } 
      else
      { 
         //:IF szSearchType = "is in list"
         if ( ZeidonStringCompare( szSearchType, 1, 0, "is in list", 1, 0, 11 ) == 0 )
         { 
            //:// Add each item in the list as a subparameter with OR.
            //:// KJS 03/31/22 - In looking at executing a report. Added a value but this is in "Value" not "ValuesAsFindInList". 
            //:// Use value if it exists.
            //:IF zqFrame.GeneralParameter.ValuesAsFindInList = "" AND zqFrame.GeneralParameter.Value != ""
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "ValuesAsFindInList", "" ) == 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "Value", "" ) != 0 )
            { 
               //:szValuesAsFindInList = zqFrame.GeneralParameter.Value 
               {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
               StringBuilder sb_szValuesAsFindInList;
               if ( szValuesAsFindInList == null )
                  sb_szValuesAsFindInList = new StringBuilder( 32 );
               else
                  sb_szValuesAsFindInList = new StringBuilder( szValuesAsFindInList );
                               GetVariableFromAttribute( sb_szValuesAsFindInList, mi_lTempInteger_3, 'S', 10000001, zqFrame, "GeneralParameter", "Value", "", 0 );
               lTempInteger_3 = mi_lTempInteger_3.intValue( );
               szValuesAsFindInList = sb_szValuesAsFindInList.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szValuesAsFindInList = zqFrame.GeneralParameter.ValuesAsFindInList 
               {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
               StringBuilder sb_szValuesAsFindInList;
               if ( szValuesAsFindInList == null )
                  sb_szValuesAsFindInList = new StringBuilder( 32 );
               else
                  sb_szValuesAsFindInList = new StringBuilder( szValuesAsFindInList );
                               GetVariableFromAttribute( sb_szValuesAsFindInList, mi_lTempInteger_4, 'S', 10000001, zqFrame, "GeneralParameter", "ValuesAsFindInList", "", 0 );
               lTempInteger_4 = mi_lTempInteger_4.intValue( );
               szValuesAsFindInList = sb_szValuesAsFindInList.toString( );}
            } 

            //:END
            //:// If the list has return characters replace them with commas.
            //:// There will be a comma at the end of the list.           
            //:IF szValuesAsFindInList = ""
            if ( ZeidonStringCompare( szValuesAsFindInList, 1, 0, "", 1, 0, 10000001 ) == 0 )
            { 
               //:Msg = "The 'is in list' operator does not have any values."
                {StringBuilder sb_Msg;
               if ( Msg == null )
                  sb_Msg = new StringBuilder( 32 );
               else
                  sb_Msg = new StringBuilder( Msg );
                              ZeidonStringCopy( sb_Msg, 1, 0, "The 'is in list' operator does not have any values.", 1, 0, 101 );
               Msg = sb_Msg.toString( );}
               //:MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
               MessageSend( zqFrame, "", "Query", Msg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
            //:SearchAndReplace( szValuesAsFindInList, "\r\n", "," ) 
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
             {StringBuilder sb_szValuesAsFindInList;
            if ( szValuesAsFindInList == null )
               sb_szValuesAsFindInList = new StringBuilder( 32 );
            else
               sb_szValuesAsFindInList = new StringBuilder( szValuesAsFindInList );
                         m_ZGLOBAL1_Operation.SearchAndReplace( sb_szValuesAsFindInList, "\\r\\n", "," );
            szValuesAsFindInList = sb_szValuesAsFindInList.toString( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:CREATE ENTITY vQualObject.QualAttrib
            RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
            //:vQualObject.QualAttrib.Oper = "("
            SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
            //:Count = 1
            Count = 1;
            //:RetrieveCommaDeliminatedValueN( szInListValue, szValuesAsFindInList, Count )
            {
             ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
             {StringBuilder sb_szInListValue;
            if ( szInListValue == null )
               sb_szInListValue = new StringBuilder( 32 );
            else
               sb_szInListValue = new StringBuilder( szInListValue );
                         m_ZGLOBAL1_Operation.RetrieveCommaDeliminatedValueN( sb_szInListValue, szValuesAsFindInList, Count );
            szInListValue = sb_szInListValue.toString( );}
             // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
            }
            //:LOOP WHILE szInListValue != ""
            while ( ZeidonStringCompare( szInListValue, 1, 0, "", 1, 0, 101 ) != 0 )
            { 
               //:IF Count > 1
               if ( Count > 1 )
               { 
                  //:CREATE ENTITY vQualObject.QualAttrib
                  RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
                  //:vQualObject.QualAttrib.Oper = "OR"
                  SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "OR" );
               } 

               //:END

               //:nRC = BuildQualEntry( zqFrame,
               //:                      szEntityName,
               //:                      szAttributeName,
               //:                      "=",
               //:                      szInListValue,
               //:                      qConvertData,
               //:                      vQualObject )
               nRC = ozqFrame_BuildQualEntry( zqFrame, szEntityName, szAttributeName, "=", szInListValue, qConvertData, vQualObject );
               //:IF nRC < 0
               if ( nRC < 0 )
               { 
                  //:RETURN -1
                  if(8==8)return( -1 );
               } 

               //:END
               //:Count = Count + 1
               Count = Count + 1;
               //:RetrieveCommaDeliminatedValueN( szInListValue, szValuesAsFindInList, Count )
               {
                ZGLOBAL1_Operation m_ZGLOBAL1_Operation = new ZGLOBAL1_Operation( zqFrame );
                {StringBuilder sb_szInListValue;
               if ( szInListValue == null )
                  sb_szInListValue = new StringBuilder( 32 );
               else
                  sb_szInListValue = new StringBuilder( szInListValue );
                               m_ZGLOBAL1_Operation.RetrieveCommaDeliminatedValueN( sb_szInListValue, szValuesAsFindInList, Count );
               szInListValue = sb_szInListValue.toString( );}
                // m_ZGLOBAL1_Operation = null;  // permit gc  (unnecessary)
               }
            } 

            //:END
            //:CREATE ENTITY vQualObject.QualAttrib  // KJS 03/31/22
            RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
            //:vQualObject.QualAttrib.Oper = ")"
            SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
            //:ELSE
         } 
         else
         { 
            //:nRC = BuildQualEntry( zqFrame,
            //:                      szEntityName,
            //:                      szAttributeName,
            //:                      szSearchType,
            //:                      zqFrame.GeneralParameter.dValue,
            //:                      qConvertData, vQualObject )
            {StringBuilder sb_szTempString_3;
            if ( szTempString_3 == null )
               sb_szTempString_3 = new StringBuilder( 32 );
            else
               sb_szTempString_3 = new StringBuilder( szTempString_3 );
                         GetStringFromAttribute( sb_szTempString_3, zqFrame, "GeneralParameter", "dValue" );
            szTempString_3 = sb_szTempString_3.toString( );}
            nRC = ozqFrame_BuildQualEntry( zqFrame, szEntityName, szAttributeName, szSearchType, szTempString_3, qConvertData, vQualObject );
            //:IF nRC < 0
            if ( nRC < 0 )
            { 
               //:RETURN -1
               if(8==8)return( -1 );
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:CheckBooleanExpression( VIEW zqFrame  BASED ON LOD zqFrame )

//:   VIEW zqFrameT BASED ON LOD zqFrame
public int 
ozqFrame_CheckBooleanExpression( View     zqFrame )
{
   zVIEW    zqFrameT = new zVIEW( );
   //:STRING ( 3 )   szOperation
   String   szOperation = null;
   //:STRING ( 1 )   szDerivedAttributeFlag
   String   szDerivedAttributeFlag = null;
   //:STRING ( 1 )   szPersistentAttributeFlag
   String   szPersistentAttributeFlag = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// This operation checks a boolean expression specified within parens to see if there are any derived
   //:// attributes with or without an OR condition within it. This includes any subexpressions within parens. 
   //:// A return code of -1 indicates a derived attribute was found, but there was no OR condition.
   //:// A return code of -2 indicates a derived attribute was found, along with an OR condition.
   //:// A return code of -3 indicates there was no persistent attribute in the condition.

   //:// We got here because the calling operation came across a Component with value left parens.
   //:// Note that the cursor position of zqFrame.Component changes within this operation.

   //:nRC = SetCursorNextEntity( zqFrame, "Component", "" )
   nRC = SetCursorNextEntity( zqFrame, "Component", "" );
   //:LOOP WHILE nRC >= zCURSOR_SET AND zqFrame.Component.Value != ")"
   while ( nRC >= zCURSOR_SET && CompareAttributeToString( zqFrame, "Component", "Value", ")" ) != 0 )
   { 
      //:IF zqFrame.Component.Value = "("
      if ( CompareAttributeToString( zqFrame, "Component", "Value", "(" ) == 0 )
      { 
         //:CreateViewFromView( zqFrameT, zqFrame )
         CreateViewFromView( zqFrameT, zqFrame );
         //:nRC = CheckBooleanExpression( zqFrameT )
         nRC = ozqFrame_CheckBooleanExpression( zqFrameT );
         //:IF nRC < 0
         if ( nRC < 0 )
         { 
            //:IF nRC <= -2
            if ( nRC <= -2 )
            { 
               //:// If either an OR was found or no persistent attribute was found, we won't check futher.
               //:RETURN nRC
               if(8==8)return( nRC );
            } 

            //:END
            //:szDerivedAttributeFlag = "Y"
             {StringBuilder sb_szDerivedAttributeFlag;
            if ( szDerivedAttributeFlag == null )
               sb_szDerivedAttributeFlag = new StringBuilder( 32 );
            else
               sb_szDerivedAttributeFlag = new StringBuilder( szDerivedAttributeFlag );
                        ZeidonStringCopy( sb_szDerivedAttributeFlag, 1, 0, "Y", 1, 0, 2 );
            szDerivedAttributeFlag = sb_szDerivedAttributeFlag.toString( );}
         } 

         //:END
         //:DropView( zqFrameT )
         DropView( zqFrameT );
         //:ELSE
      } 
      else
      { 
         //:IF zqFrame.Component.Value = "AND" OR zqFrame.Component.Value = "OR"
         if ( CompareAttributeToString( zqFrame, "Component", "Value", "AND" ) == 0 || CompareAttributeToString( zqFrame, "Component", "Value", "OR" ) == 0 )
         { 
            //:IF szOperation = "" AND zqFrame.Component.Value = "OR"
            if ( ZeidonStringCompare( szOperation, 1, 0, "", 1, 0, 4 ) == 0 && CompareAttributeToString( zqFrame, "Component", "Value", "OR" ) == 0 )
            { 
               //:szOperation = "OR"
                {StringBuilder sb_szOperation;
               if ( szOperation == null )
                  sb_szOperation = new StringBuilder( 32 );
               else
                  sb_szOperation = new StringBuilder( szOperation );
                              ZeidonStringCopy( sb_szOperation, 1, 0, "OR", 1, 0, 4 );
               szOperation = sb_szOperation.toString( );}
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:SET CURSOR FIRST zqFrame.Condition 
            //:           WHERE zqFrame.Condition.BooleanConditionName = zqFrame.Component.Value  
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame, "Component", "Value" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = SetCursorFirstEntityByString( zqFrame, "Condition", "BooleanConditionName", szTempString_0, "" );
            //:SET CURSOR FIRST zqFrame.GeneralParameter 
            //:           WHERE zqFrame.GeneralParameter.EntityName    = zqFrame.Condition.EntityName 
            //:             AND zqFrame.GeneralParameter.AttributeName = zqFrame.Condition.AttributeName 
            //:             AND zqFrame.GeneralParameter.SearchType    = zqFrame.Condition.SearchType 
            //:             AND zqFrame.GeneralParameter.Value         = zqFrame.Condition.Value 
            RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "Condition", "EntityName" ) != 0 ||
                       CompareAttributeToAttribute( zqFrame, "GeneralParameter", "AttributeName", zqFrame, "Condition", "AttributeName" ) != 0 ||
                       CompareAttributeToAttribute( zqFrame, "GeneralParameter", "SearchType", zqFrame, "Condition", "SearchType" ) != 0 || CompareAttributeToAttribute( zqFrame, "GeneralParameter", "Value", zqFrame, "Condition", "Value" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
               } 

            } 

            //:IF zqFrame.GeneralParameter.DerivedAttributeFlag = "Y"
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "Y" ) == 0 )
            { 
               //:szDerivedAttributeFlag = "Y"
                {StringBuilder sb_szDerivedAttributeFlag;
               if ( szDerivedAttributeFlag == null )
                  sb_szDerivedAttributeFlag = new StringBuilder( 32 );
               else
                  sb_szDerivedAttributeFlag = new StringBuilder( szDerivedAttributeFlag );
                              ZeidonStringCopy( sb_szDerivedAttributeFlag, 1, 0, "Y", 1, 0, 2 );
               szDerivedAttributeFlag = sb_szDerivedAttributeFlag.toString( );}
               //:ELSE
            } 
            else
            { 
               //:// At least one persistent attribute was found.
               //:szPersistentAttributeFlag = "Y"
                {StringBuilder sb_szPersistentAttributeFlag;
               if ( szPersistentAttributeFlag == null )
                  sb_szPersistentAttributeFlag = new StringBuilder( 32 );
               else
                  sb_szPersistentAttributeFlag = new StringBuilder( szPersistentAttributeFlag );
                              ZeidonStringCopy( sb_szPersistentAttributeFlag, 1, 0, "Y", 1, 0, 2 );
               szPersistentAttributeFlag = sb_szPersistentAttributeFlag.toString( );}
            } 

            //:END
         } 

         //:END
      } 

      //:END
      //:nRC = SetCursorNextEntity( zqFrame, "Component", "" )
      nRC = SetCursorNextEntity( zqFrame, "Component", "" );
   } 

   //:END

   //:// We will return a negative return code if a derived attribute existed and the operator was an OR
   //:// and there was not persistent attribute in the expression.
   //:IF szDerivedAttributeFlag = "Y"
   if ( ZeidonStringCompare( szDerivedAttributeFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:IF szOperation = "OR"
      if ( ZeidonStringCompare( szOperation, 1, 0, "OR", 1, 0, 4 ) == 0 )
      { 
         //:RETURN -2
         if(8==8)return( -2 );
         //:ELSE
      } 
      else
      { 
         //:IF szPersistentAttributeFlag = ""
         if ( ZeidonStringCompare( szPersistentAttributeFlag, 1, 0, "", 1, 0, 2 ) == 0 )
         { 
            //:// No persistent attribute was found.
            //:RETURN -3
            if(8==8)return( -3 );
            //:ELSE
         } 
         else
         { 
            //:// A derived attribute was found.
            //:RETURN -1
            if(8==8)return( -1 );
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return( 0 );
//    
// END
} 


//:LOCAL OPERATION
//:BuildPostBooleanExp( VIEW zqFrame BASED ON LOD zqFrame )

//:   STRING ( 32 )  szEntityName
public int 
ozqFrame_BuildPostBooleanExp( View     zqFrame )
{
   String   szEntityName = null;
   //:STRING ( 32 )  szAttributeName
   String   szAttributeName = null;
   //:STRING ( 10 )  szSearchType
   String   szSearchType = null;
   //:STRING ( 1 )   szDerivedAttributeFlag
   String   szDerivedAttributeFlag = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;


   //:// This operation builds the PostActivateComponent entries for PostActivate boolean criteria.
   //:// It also builds the corresponding PostActivateQual entry for each.

   //:// Build the AND or OR statement preceding the "(".
   //:SET CURSOR LAST zqFrame.PostActivateComponent
   RESULT = SetCursorLastEntity( zqFrame, "PostActivateComponent", "" );
   //:CREATE ENTITY zqFrame.PostActivateComponent 
   RESULT = CreateEntity( zqFrame, "PostActivateComponent", zPOS_AFTER );
   //:zqFrame.PostActivateComponent.Value = "("
   SetAttributeFromString( zqFrame, "PostActivateComponent", "Value", "(" );
   //:nRC = SetCursorNextEntity( zqFrame, "Component", "" )
   nRC = SetCursorNextEntity( zqFrame, "Component", "" );
   //:LOOP WHILE nRC >= zCURSOR_SET AND zqFrame.Component.Value != ")"
   while ( nRC >= zCURSOR_SET && CompareAttributeToString( zqFrame, "Component", "Value", ")" ) != 0 )
   { 
      //:IF zqFrame.Component.Value = "("
      if ( CompareAttributeToString( zqFrame, "Component", "Value", "(" ) == 0 )
      { 
         //:BuildPostBooleanExp( zqFrame )
         ozqFrame_BuildPostBooleanExp( zqFrame );
         //:ELSE
      } 
      else
      { 
         //:// Create Component (ex., C1, C2)
         //:CREATE ENTITY zqFrame.PostActivateComponent 
         RESULT = CreateEntity( zqFrame, "PostActivateComponent", zPOS_AFTER );
         //:zqFrame.PostActivateComponent.Value = zqFrame.Component.Value
         SetAttributeFromAttribute( zqFrame, "PostActivateComponent", "Value", zqFrame, "Component", "Value" );
         //:// Create corresponding PostActivateQual entry for Cn entries.
         //:IF zqFrame.Component.Value != "AND" AND zqFrame.Component.Value != "OR"
         if ( CompareAttributeToString( zqFrame, "Component", "Value", "AND" ) != 0 && CompareAttributeToString( zqFrame, "Component", "Value", "OR" ) != 0 )
         { 
            //:SET CURSOR FIRST zqFrame.Condition 
            //:           WHERE zqFrame.Condition.BooleanConditionName = zqFrame.Component.Value 
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame, "Component", "Value" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = SetCursorFirstEntityByString( zqFrame, "Condition", "BooleanConditionName", szTempString_0, "" );
            //:SET CURSOR FIRST zqFrame.GeneralParameter 
            //:           WHERE zqFrame.GeneralParameter.EntityName    = zqFrame.Condition.EntityName 
            //:             AND zqFrame.GeneralParameter.AttributeName = zqFrame.Condition.AttributeName 
            //:             AND zqFrame.GeneralParameter.SearchType    = zqFrame.Condition.SearchType 
            //:             AND zqFrame.GeneralParameter.Value         = zqFrame.Condition.Value 
            RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "Condition", "EntityName" ) != 0 ||
                       CompareAttributeToAttribute( zqFrame, "GeneralParameter", "AttributeName", zqFrame, "Condition", "AttributeName" ) != 0 ||
                       CompareAttributeToAttribute( zqFrame, "GeneralParameter", "SearchType", zqFrame, "Condition", "SearchType" ) != 0 || CompareAttributeToAttribute( zqFrame, "GeneralParameter", "Value", zqFrame, "Condition", "Value" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
               } 

            } 

            //:BuildPostQualEntry( zqFrame )
            ozqFrame_BuildPostQualEntry( zqFrame );
            //:zqFrame.PostActivateQual.BooleanConditionName = zqFrame.Condition.BooleanConditionName 
            SetAttributeFromAttribute( zqFrame, "PostActivateQual", "BooleanConditionName", zqFrame, "Condition", "BooleanConditionName" );
         } 

         //:END
      } 

      //:END
      //:nRC = SetCursorNextEntity( zqFrame, "Component", "" )
      nRC = SetCursorNextEntity( zqFrame, "Component", "" );
   } 

   //:END
   //:CREATE ENTITY zqFrame.PostActivateComponent 
   RESULT = CreateEntity( zqFrame, "PostActivateComponent", zPOS_AFTER );
   //:zqFrame.PostActivateComponent.Value = ")"
   SetAttributeFromString( zqFrame, "PostActivateComponent", "Value", ")" );
   return( 0 );
//    
// END
} 


//:LOCAL OPERATION
//:BuildBooleanExpression( VIEW zqFrame BASED ON LOD zqFrame,
//:                        VIEW vQualObject,
//:                        VIEW qConvertData )
//:   
//:   VIEW zqFrameT BASED ON LOD zqFrame
public int 
ozqFrame_BuildBooleanExpression( View     zqFrame,
                                 View     vQualObject,
                                 View     qConvertData )
{
   zVIEW    zqFrameT = new zVIEW( );
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )  szAttributeName
   String   szAttributeName = null;
   //:STRING ( 10 )  szSearchType
   String   szSearchType = null;
   //:STRING ( 100 ) Msg
   String   Msg = null;
   //:SHORT          nRC
   int      nRC = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;


   //:// This operation builds the SQL Qualification for a boolean expression specified within parens.
   //:// If any entry within the expression uses a derived attribute, then no SQL Qualification is
   //:// generated and the expression is evaluated in BuildPostQualEntry.

   //:// Check to see if any attribute within the boolean expression is derived and if so, skip building SQL.
   //:CreateViewFromView( zqFrameT, zqFrame )
   CreateViewFromView( zqFrameT, zqFrame );
   //:nRC = CheckBooleanExpression( zqFrameT )
   nRC = ozqFrame_CheckBooleanExpression( zqFrameT );
   //:DropView( zqFrameT )
   DropView( zqFrameT );
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:// Build the PostActivate Component entries.
      //:SET CURSOR LAST zqFrame.PostActivateComponent
      RESULT = SetCursorLastEntity( zqFrame, "PostActivateComponent", "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:// There's already an entry, so add the AND or OR statment that followed it.
         //:CREATE ENTITY zqFrame.PostActivateComponent 
         RESULT = CreateEntity( zqFrame, "PostActivateComponent", zPOS_AFTER );
         //:zqFrame.PostActivateComponent.Value = vQualObject.QualAttrib.Oper
         SetAttributeFromAttribute( zqFrame, "PostActivateComponent", "Value", vQualObject, "QualAttrib", "Oper" );
      } 

      //:END
      //:BuildPostBooleanExp( zqFrame )
      ozqFrame_BuildPostBooleanExp( zqFrame );
      //:IF nRC = -2
      if ( nRC == -2 )
      { 
         //:// A Derived attribute with OR condition was found, so don't continue on to build SQL qualification.
         //:RETURN nRC
         if(8==8)return( nRC );
      } 

      //:END
   } 

   //:END

   //:// No derived attribute or SubgroupSearchType value exists, so build the entries.
   //:CREATE ENTITY vQualObject.QualAttrib
   RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   //:vQualObject.QualAttrib.Oper = "("
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "(" );
   //:nRC = SetCursorNextEntity( zqFrame, "Component", "" )
   nRC = SetCursorNextEntity( zqFrame, "Component", "" );
   //:LOOP WHILE nRC >= zCURSOR_SET AND zqFrame.Component.Value != ")"
   while ( nRC >= zCURSOR_SET && CompareAttributeToString( zqFrame, "Component", "Value", ")" ) != 0 )
   { 
      //:IF zqFrame.Component.Value = "("
      if ( CompareAttributeToString( zqFrame, "Component", "Value", "(" ) == 0 )
      { 
         //:BuildBooleanExpression( zqFrame, vQualObject, qConvertData )
         ozqFrame_BuildBooleanExpression( zqFrame, vQualObject, qConvertData );
         //:ELSE
      } 
      else
      { 
         //:IF zqFrame.Component.Value = "AND" OR
         //:   zqFrame.Component.Value = "OR"
         if ( CompareAttributeToString( zqFrame, "Component", "Value", "AND" ) == 0 || CompareAttributeToString( zqFrame, "Component", "Value", "OR" ) == 0 )
         { 

            //:// Add the operator
            //:IF vQualObject.QualAttrib.Oper != "("
            if ( CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "(" ) != 0 )
            { 
               //:CREATE ENTITY vQualObject.QualAttrib
               RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
               //:vQualObject.QualAttrib.Oper = zqFrame.Component.Value
               SetAttributeFromAttribute( vQualObject, "QualAttrib", "Oper", zqFrame, "Component", "Value" );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:// Add the condition.
            //:SET CURSOR FIRST zqFrame.Condition 
            //:           WHERE zqFrame.Condition.BooleanConditionName = zqFrame.Component.Value  
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame, "Component", "Value" );
            szTempString_0 = sb_szTempString_0.toString( );}
            RESULT = SetCursorFirstEntityByString( zqFrame, "Condition", "BooleanConditionName", szTempString_0, "" );
            //:SET CURSOR FIRST zqFrame.GeneralParameter 
            //:           WHERE zqFrame.GeneralParameter.EntityName    = zqFrame.Condition.EntityName 
            //:             AND zqFrame.GeneralParameter.AttributeName = zqFrame.Condition.AttributeName 
            //:             AND zqFrame.GeneralParameter.SearchType    = zqFrame.Condition.SearchType 
            //:             AND zqFrame.GeneralParameter.Value         = zqFrame.Condition.Value 
            RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
            if ( RESULT > zCURSOR_UNCHANGED )
            { 
               while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "Condition", "EntityName" ) != 0 ||
                       CompareAttributeToAttribute( zqFrame, "GeneralParameter", "AttributeName", zqFrame, "Condition", "AttributeName" ) != 0 ||
                       CompareAttributeToAttribute( zqFrame, "GeneralParameter", "SearchType", zqFrame, "Condition", "SearchType" ) != 0 || CompareAttributeToAttribute( zqFrame, "GeneralParameter", "Value", zqFrame, "Condition", "Value" ) != 0 ) )
               { 
                  RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
               } 

            } 

            //:IF zqFrame.GeneralParameter.DerivedAttributeFlag = "" AND 
            //:   ( zqFrame.GeneralParameter.SubgroupSearchType = "" OR zqFrame.GeneralParameter.SubgroupSearchType = "ANY" )
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "DerivedAttributeFlag", "" ) == 0 && ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ||
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) == 0 ) )
            { 

               //:nRC = BuildConditionSubobj( zqFrame, vQualObject, qConvertData)
               nRC = ozqFrame_BuildConditionSubobj( zqFrame, vQualObject, qConvertData );
               //:IF nRC < 0
               if ( nRC < 0 )
               { 
                  //:RETURN nRC 
                  if(8==8)return( nRC );
               } 

               //:END
               //:ELSE
            } 
            else
            { 
               //://IssueError( zqFrame,0,0, "At Derived Qual" )
               //:IF vQualObject.QualAttrib EXISTS
               lTempInteger_0 = CheckExistenceOfEntity( vQualObject, "QualAttrib" );
               if ( lTempInteger_0 == 0 )
               { 
                  //:IF vQualObject.QualAttrib.Oper = "AND" OR vQualObject.QualAttrib.Oper = "OR"
                  if ( CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "AND" ) == 0 || CompareAttributeToString( vQualObject, "QualAttrib", "Oper", "OR" ) == 0 )
                  { 
                     //:// Add the last AND or OR to PostActivate access and remove from SQL access.
                     //:// We won't add the last AND or OR, if it would be the first in PostActivate.
                     //://IF zqFrame.PostActivateComponent EXISTS
                     //://   CREATE ENTITY zqFrame.PostActivateComponent 
                     //://   zqFrame.PostActivateComponent.Value = vQualObject.QualAttrib.Oper
                     //://END
                     //:DELETE ENTITY vQualObject.QualAttrib
                     RESULT = DeleteEntity( vQualObject, "QualAttrib", zPOS_NEXT );
                  } 

                  //:END
               } 

               //:END
            } 

            //:   //CREATE ENTITY zqFrame.PostActivateComponent 
            //:   //zqFrame.PostActivateComponent.Value = zqFrame.Component.Value
            //:END
         } 

         //:END
      } 

      //:END
      //:nRC = SetCursorNextEntity( zqFrame, "Component", "" )
      nRC = SetCursorNextEntity( zqFrame, "Component", "" );
   } 

   //:END
   //:CREATE ENTITY vQualObject.QualAttrib
   RESULT = CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   //:vQualObject.QualAttrib.Oper = ")"
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", ")" );
   return( 0 );
//    
// END
} 


//:TRANSFORMATION OPERATION
//:FormatResultSetList( VIEW zqFrame BASED ON LOD zqFrame,
//:                     VIEW vResultSet )

//:   VIEW zqSumR   BASED ON LOD zqSumR
public int 
ozqFrame_FormatResultSetList( View     zqFrame,
                              View     vResultSet )
{
   zVIEW    zqSumR = new zVIEW( );
   //:STRING ( 32 )  RS_RootEntityName
   String   RS_RootEntityName = null;
   //:STRING ( 32 )  szMappingEntity
   String   szMappingEntity = null;
   //:STRING ( 32 )  szMappingEntityLast
   String   szMappingEntityLast = null;
   //:STRING ( 32 )  szMappingAttribute
   String   szMappingAttribute = null;
   //:STRING ( 50 )  szDisplayPrompt
   String   szDisplayPrompt = null;
   //:STRING ( 32 )  szDisplayAttribute
   String   szDisplayAttribute = null;
   //:STRING ( 50 )  szPrompt
   String   szPrompt = null;
   //:STRING ( 2 )   szDisplayCount
   String   szDisplayCount = null;
   //:STRING ( 1 )   szDisplayOrderFlag
   String   szDisplayOrderFlag = null;
   //:STRING ( 254 ) szDisplayValue
   String   szDisplayValue = null;
   //:STRING ( 20 )  szDecimalValue
   String   szDecimalValue = null;
   //:STRING ( 20 )  szSpaces
   String   szSpaces = null;
   //:INTEGER        Count
   int      Count = 0;
   //:INTEGER        DisplayCount
   int      DisplayCount = 0;
   //:INTEGER        RootCount
   int      RootCount = 0;
   //:INTEGER        nRC
   int      nRC = 0;
   //:INTEGER        Length
   int      Length = 0;
   //:INTEGER        SpacesLength
   int      SpacesLength = 0;
   //:INTEGER        RootEntityID
   int      RootEntityID = 0;
   //:DECIMAL        OperationResult
   double  OperationResult = 0.0;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_1 = 0;
   String   szTempString_3 = null;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   String   szTempString_4 = null;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      lTempInteger_6 = 0;
   int      lTempInteger_7 = 0;
   String   szTempString_5 = null;
   String   szTempString_6 = null;
   String   szTempString_7 = null;


   //:// Order the Display values, if requested.
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:           WHERE zqFrame.GeneralParameter.DisplayOrder != ""
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "DisplayOrder", "" ) == 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szDisplayOrderFlag = "Y"
       {StringBuilder sb_szDisplayOrderFlag;
      if ( szDisplayOrderFlag == null )
         sb_szDisplayOrderFlag = new StringBuilder( 32 );
      else
         sb_szDisplayOrderFlag = new StringBuilder( szDisplayOrderFlag );
            ZeidonStringCopy( sb_szDisplayOrderFlag, 1, 0, "Y", 1, 0, 2 );
      szDisplayOrderFlag = sb_szDisplayOrderFlag.toString( );}
      //:Count = 0
      Count = 0;
      //:FOR EACH zqFrame.GeneralParameter 
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:Count = Count + 1
         Count = Count + 1;
         //:zqFrame.GeneralParameter.OriginalOrder = Count
         SetAttributeFromInteger( zqFrame, "GeneralParameter", "OriginalOrder", Count );
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

      //:END
      //:OrderEntityForView( zqFrame, "GeneralParameter", "DisplayOrder A" )
      OrderEntityForView( zqFrame, "GeneralParameter", "DisplayOrder A" );
      //:ELSE
   } 
   else
   { 
      //:szDisplayOrderFlag = ""
       {StringBuilder sb_szDisplayOrderFlag;
      if ( szDisplayOrderFlag == null )
         sb_szDisplayOrderFlag = new StringBuilder( 32 );
      else
         sb_szDisplayOrderFlag = new StringBuilder( szDisplayOrderFlag );
            ZeidonStringCopy( sb_szDisplayOrderFlag, 1, 0, "", 1, 0, 2 );
      szDisplayOrderFlag = sb_szDisplayOrderFlag.toString( );}
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );

   //:// Delete any existing display list entities.
   //:FOR EACH zqFrame.zqFrame 
   RESULT = SetCursorFirstEntity( zqFrame, "zqFrame", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.ResultSetDisplay NONE 
      RESULT = DeleteEntity( zqFrame, "ResultSetDisplay", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "zqFrame", "" );
   } 

   //:END

   //:// Set Result Set Display Title values and mapping.
   //:RS_RootEntityName = zqFrame.zqFrame.QueryObjectRootEntityName 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_RS_RootEntityName;
   if ( RS_RootEntityName == null )
      sb_RS_RootEntityName = new StringBuilder( 32 );
   else
      sb_RS_RootEntityName = new StringBuilder( RS_RootEntityName );
       GetVariableFromAttribute( sb_RS_RootEntityName, mi_lTempInteger_0, 'S', 33, zqFrame, "zqFrame", "QueryObjectRootEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   RS_RootEntityName = sb_RS_RootEntityName.toString( );}
   //:DisplayCount = 0
   DisplayCount = 0;
   //:CREATE ENTITY zqFrame.ResultSetDisplay  
   RESULT = CreateEntity( zqFrame, "ResultSetDisplay", zPOS_AFTER );
   //:FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.ListDisplayFlag = "Y"
   RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF DisplayCount < 6
      if ( DisplayCount < 6 )
      { 
         //:SET CURSOR FIRST zqFrame.QueryAttribute 
         //:           WHERE zqFrame.QueryAttribute.MappingEntityName = zqFrame.GeneralParameter.EntityName
         //:             AND zqFrame.QueryAttribute.AttributeName     = zqFrame.GeneralParameter.AttributeName 
         RESULT = SetCursorFirstEntity( zqFrame, "QueryAttribute", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrame, "QueryAttribute", "MappingEntityName", zqFrame, "GeneralParameter", "EntityName" ) != 0 ||
                    CompareAttributeToAttribute( zqFrame, "QueryAttribute", "AttributeName", zqFrame, "GeneralParameter", "AttributeName" ) != 0 ) )
            { 
               RESULT = SetCursorNextEntity( zqFrame, "QueryAttribute", "" );
            } 

         } 

         //:IF RESULT >= zCURSOR_SET 
         if ( RESULT >= zCURSOR_SET )
         { 

            //:// This Attribute is either for the Root entity or for a child entity that has max cardinality of 1 to the Root.

            //:// Set the Display Title, using a suffix made up of the Display Attribute count.
            //:DisplayCount = DisplayCount + 1
            DisplayCount = DisplayCount + 1;
            //:szDisplayCount = DisplayCount
             {StringBuilder sb_szDisplayCount;
            if ( szDisplayCount == null )
               sb_szDisplayCount = new StringBuilder( 32 );
            else
               sb_szDisplayCount = new StringBuilder( szDisplayCount );
                        ZeidonStringConvertFromNumber( sb_szDisplayCount, 1, 0, 2, DisplayCount, (double) 0.0, "I" );
            szDisplayCount = sb_szDisplayCount.toString( );}
            //:szDisplayPrompt = "ColumnTitle" + szDisplayCount
             {StringBuilder sb_szDisplayPrompt;
            if ( szDisplayPrompt == null )
               sb_szDisplayPrompt = new StringBuilder( 32 );
            else
               sb_szDisplayPrompt = new StringBuilder( szDisplayPrompt );
                        ZeidonStringCopy( sb_szDisplayPrompt, 1, 0, "ColumnTitle", 1, 0, 51 );
            szDisplayPrompt = sb_szDisplayPrompt.toString( );}
             {StringBuilder sb_szDisplayPrompt;
            if ( szDisplayPrompt == null )
               sb_szDisplayPrompt = new StringBuilder( 32 );
            else
               sb_szDisplayPrompt = new StringBuilder( szDisplayPrompt );
                        ZeidonStringConcat( sb_szDisplayPrompt, 1, 0, szDisplayCount, 1, 0, 51 );
            szDisplayPrompt = sb_szDisplayPrompt.toString( );}
            //:SetAttributeFromString( zqFrame, "ResultSetDisplay", szDisplayPrompt, zqFrame.GeneralParameter.Title )
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame, "GeneralParameter", "Title" );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( zqFrame, "ResultSetDisplay", szDisplayPrompt, szTempString_0 );

            //:// Create the Entity and Attribute mapping entry.
            //:CREATE ENTITY zqFrame.ResultSetDisplayMapping 
            RESULT = CreateEntity( zqFrame, "ResultSetDisplayMapping", zPOS_AFTER );
            //:zqFrame.ResultSetDisplayMapping.SourceEntityName    = zqFrame.GeneralParameter.EntityName 
            SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SourceEntityName", zqFrame, "GeneralParameter", "EntityName" );
            //:zqFrame.ResultSetDisplayMapping.SourceAttributeName = zqFrame.GeneralParameter.AttributeName            
            SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SourceAttributeName", zqFrame, "GeneralParameter", "AttributeName" );
            //:ELSE
         } 
         else
         { 
            //:// Check to see if the Scoping entity is the Root and the Action is SUM, AVG, MAX, MIN or COUNT.
            //:IF ( zqFrame.GeneralParameter.SubgroupSearchType = "SUM" OR 
            //:     zqFrame.GeneralParameter.SubgroupSearchType = "AVG" OR  
            //:     zqFrame.GeneralParameter.SubgroupSearchType = "MAX" OR  
            //:     zqFrame.GeneralParameter.SubgroupSearchType = "MIN" OR  
            //:     zqFrame.GeneralParameter.SubgroupSearchType = "COUNT" ) AND 
            //:     zqFrame.GeneralParameter.ScopingEntityName  = RS_RootEntityName
            if ( ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "SUM" ) == 0 || CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "AVG" ) == 0 ||
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "MAX" ) == 0 || CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "MIN" ) == 0 ||
                 CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "COUNT" ) == 0 ) && CompareAttributeToString( zqFrame, "GeneralParameter", "ScopingEntityName", RS_RootEntityName ) == 0 )
            { 
               //:  
               //:// Add the display entry as an Action.

               //:// Set the Display Title, using a suffix made up of the Display Attribute count.
               //:DisplayCount = DisplayCount + 1
               DisplayCount = DisplayCount + 1;
               //:szDisplayCount = DisplayCount
                {StringBuilder sb_szDisplayCount;
               if ( szDisplayCount == null )
                  sb_szDisplayCount = new StringBuilder( 32 );
               else
                  sb_szDisplayCount = new StringBuilder( szDisplayCount );
                              ZeidonStringConvertFromNumber( sb_szDisplayCount, 1, 0, 2, DisplayCount, (double) 0.0, "I" );
               szDisplayCount = sb_szDisplayCount.toString( );}
               //:szDisplayPrompt = "ColumnTitle" + szDisplayCount
                {StringBuilder sb_szDisplayPrompt;
               if ( szDisplayPrompt == null )
                  sb_szDisplayPrompt = new StringBuilder( 32 );
               else
                  sb_szDisplayPrompt = new StringBuilder( szDisplayPrompt );
                              ZeidonStringCopy( sb_szDisplayPrompt, 1, 0, "ColumnTitle", 1, 0, 51 );
               szDisplayPrompt = sb_szDisplayPrompt.toString( );}
                {StringBuilder sb_szDisplayPrompt;
               if ( szDisplayPrompt == null )
                  sb_szDisplayPrompt = new StringBuilder( 32 );
               else
                  sb_szDisplayPrompt = new StringBuilder( szDisplayPrompt );
                              ZeidonStringConcat( sb_szDisplayPrompt, 1, 0, szDisplayCount, 1, 0, 51 );
               szDisplayPrompt = sb_szDisplayPrompt.toString( );}
               //:SetAttributeFromString( zqFrame, "ResultSetDisplay", szDisplayPrompt, zqFrame.GeneralParameter.Title )
               {StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetStringFromAttribute( sb_szTempString_1, zqFrame, "GeneralParameter", "Title" );
               szTempString_1 = sb_szTempString_1.toString( );}
               SetAttributeFromString( zqFrame, "ResultSetDisplay", szDisplayPrompt, szTempString_1 );

               //:// Create the Entity and Attribute mapping entry.
               //:CREATE ENTITY zqFrame.ResultSetDisplayMapping 
               RESULT = CreateEntity( zqFrame, "ResultSetDisplayMapping", zPOS_AFTER );
               //:zqFrame.ResultSetDisplayMapping.SourceEntityName    = zqFrame.GeneralParameter.EntityName 
               SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SourceEntityName", zqFrame, "GeneralParameter", "EntityName" );
               //:zqFrame.ResultSetDisplayMapping.SourceAttributeName = zqFrame.GeneralParameter.AttributeName 
               SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SourceAttributeName", zqFrame, "GeneralParameter", "AttributeName" );
               //:zqFrame.ResultSetDisplayMapping.SubgroupAction      = zqFrame.GeneralParameter.SubgroupSearchType 
               SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SubgroupAction", zqFrame, "GeneralParameter", "SubgroupSearchType" );
            } 


            //:END
         } 

         //:END
      } 

      RESULT = SetCursorNextEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
      //:END
   } 

   //:END

   //:// Build Summary Display values.
   //:IF DisplayCount = 0
   if ( DisplayCount == 0 )
   { 
      //: // What to do if we aren't showing data using the root entity (like in our qStuDTrk summary case).
      //: // Really this process should be a recursive one, if we want to show all of the data but I'm not sure that is worth it right at this moment, so
      //: // I am keeping it simple. We will just show one line, if this is not displaying the root data entitites.
      //: FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.ListDisplayFlag = "Y"
      RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //: IF DisplayCount < 6
         if ( DisplayCount < 6 )
         { 

            //: // Set the Display Title, using a suffix made up of the Display Attribute count.
            //: DisplayCount = DisplayCount + 1
            DisplayCount = DisplayCount + 1;
            //: szDisplayCount = DisplayCount
             {StringBuilder sb_szDisplayCount;
            if ( szDisplayCount == null )
               sb_szDisplayCount = new StringBuilder( 32 );
            else
               sb_szDisplayCount = new StringBuilder( szDisplayCount );
                        ZeidonStringConvertFromNumber( sb_szDisplayCount, 1, 0, 2, DisplayCount, (double) 0.0, "I" );
            szDisplayCount = sb_szDisplayCount.toString( );}
            //: szDisplayPrompt = "ColumnTitle" + szDisplayCount
             {StringBuilder sb_szDisplayPrompt;
            if ( szDisplayPrompt == null )
               sb_szDisplayPrompt = new StringBuilder( 32 );
            else
               sb_szDisplayPrompt = new StringBuilder( szDisplayPrompt );
                        ZeidonStringCopy( sb_szDisplayPrompt, 1, 0, "ColumnTitle", 1, 0, 51 );
            szDisplayPrompt = sb_szDisplayPrompt.toString( );}
             {StringBuilder sb_szDisplayPrompt;
            if ( szDisplayPrompt == null )
               sb_szDisplayPrompt = new StringBuilder( 32 );
            else
               sb_szDisplayPrompt = new StringBuilder( szDisplayPrompt );
                        ZeidonStringConcat( sb_szDisplayPrompt, 1, 0, szDisplayCount, 1, 0, 51 );
            szDisplayPrompt = sb_szDisplayPrompt.toString( );}
            //: SetAttributeFromString( zqFrame, "ResultSetDisplay", szDisplayPrompt, zqFrame.GeneralParameter.Title )
            {StringBuilder sb_szTempString_2;
            if ( szTempString_2 == null )
               sb_szTempString_2 = new StringBuilder( 32 );
            else
               sb_szTempString_2 = new StringBuilder( szTempString_2 );
                         GetStringFromAttribute( sb_szTempString_2, zqFrame, "GeneralParameter", "Title" );
            szTempString_2 = sb_szTempString_2.toString( );}
            SetAttributeFromString( zqFrame, "ResultSetDisplay", szDisplayPrompt, szTempString_2 );
            //: 
            //: // Create the Entity and Attribute mapping entry.
            //: CREATE ENTITY zqFrame.ResultSetDisplayMapping 
            RESULT = CreateEntity( zqFrame, "ResultSetDisplayMapping", zPOS_AFTER );
            //: zqFrame.ResultSetDisplayMapping.SourceEntityName    = zqFrame.GeneralParameter.EntityName 
            SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SourceEntityName", zqFrame, "GeneralParameter", "EntityName" );
            //: zqFrame.ResultSetDisplayMapping.SourceAttributeName = zqFrame.GeneralParameter.AttributeName            
            SetAttributeFromAttribute( zqFrame, "ResultSetDisplayMapping", "SourceAttributeName", zqFrame, "GeneralParameter", "AttributeName" );
         } 

         RESULT = SetCursorNextEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
         //: END
      } 

      //: END
      //: RootCount = 0
      RootCount = 0;
      //: nRC = SetCursorFirstEntity( vResultSet, RS_RootEntityName, "" )
      nRC = SetCursorFirstEntity( vResultSet, RS_RootEntityName, "" );
      //: LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //: 
         //: nRC = SetCursorFirstEntity( zqFrame, "ResultSetDisplayMapping", "" )
         nRC = SetCursorFirstEntity( zqFrame, "ResultSetDisplayMapping", "" );
         //: IF nRC >= zCURSOR_SET AND CheckExistenceOfEntity( vResultSet, zqFrame.ResultSetDisplayMapping.SourceEntityName ) >= zCURSOR_SET
         {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetStringFromAttribute( sb_szTempString_3, zqFrame, "ResultSetDisplayMapping", "SourceEntityName" );
         szTempString_3 = sb_szTempString_3.toString( );}
         lTempInteger_1 = CheckExistenceOfEntity( vResultSet, szTempString_3 );
         if ( nRC >= zCURSOR_SET && lTempInteger_1 >= zCURSOR_SET )
         { 

            //: RootCount = RootCount + 1
            RootCount = RootCount + 1;
            //: CREATE ENTITY zqFrame.ResultSetDisplayRow
            RESULT = CreateEntity( zqFrame, "ResultSetDisplayRow", zPOS_AFTER );
            //: szMappingEntityLast = RS_RootEntityName
             {StringBuilder sb_szMappingEntityLast;
            if ( szMappingEntityLast == null )
               sb_szMappingEntityLast = new StringBuilder( 32 );
            else
               sb_szMappingEntityLast = new StringBuilder( szMappingEntityLast );
                        ZeidonStringCopy( sb_szMappingEntityLast, 1, 0, RS_RootEntityName, 1, 0, 33 );
            szMappingEntityLast = sb_szMappingEntityLast.toString( );}
            //:           
            //:  // Set the ID of the Root Entity.
            //:  GetIntegerFromAttribute( RootEntityID, vResultSet, RS_RootEntityName, "ID" )
            {MutableInt mi_RootEntityID = new MutableInt( RootEntityID );
                         GetIntegerFromAttribute( mi_RootEntityID, vResultSet, RS_RootEntityName, "ID" );
            RootEntityID = mi_RootEntityID.intValue( );}
            //:  zqFrame.ResultSetDisplayRow.RootEntityID = RootEntityID
            SetAttributeFromInteger( zqFrame, "ResultSetDisplayRow", "RootEntityID", RootEntityID );
            //:  
            //:  // Set an attribute value for each ResultSetDisplayMapping entry.
            //:  DisplayCount = 0
            DisplayCount = 0;
            //:  FOR EACH zqFrame.ResultSetDisplayMapping 
            RESULT = SetCursorFirstEntity( zqFrame, "ResultSetDisplayMapping", "" );
            while ( RESULT > zCURSOR_UNCHANGED )
            { 
               //:  szMappingEntity    = zqFrame.ResultSetDisplayMapping.SourceEntityName
               {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
               StringBuilder sb_szMappingEntity;
               if ( szMappingEntity == null )
                  sb_szMappingEntity = new StringBuilder( 32 );
               else
                  sb_szMappingEntity = new StringBuilder( szMappingEntity );
                               GetVariableFromAttribute( sb_szMappingEntity, mi_lTempInteger_2, 'S', 33, zqFrame, "ResultSetDisplayMapping", "SourceEntityName", "", 0 );
               lTempInteger_2 = mi_lTempInteger_2.intValue( );
               szMappingEntity = sb_szMappingEntity.toString( );}
               //:  //IF szMappingEntity != szMappingEntityLast
               //:  //   nRC = SetCursorFirstEntity( vResultSet, szMappingEntity, "" )
               //:  //   LOOP WHILE nRC >= zCURSOR_SET
               //:  //      nRC = SetCursorNextEntity( vResultSet, szMappingEntity, "" )
               //:  //   END
               //:  //END
               //:  IF CheckExistenceOfEntity( vResultSet, zqFrame.ResultSetDisplayMapping.SourceEntityName ) >= zCURSOR_SET
               {StringBuilder sb_szTempString_4;
               if ( szTempString_4 == null )
                  sb_szTempString_4 = new StringBuilder( 32 );
               else
                  sb_szTempString_4 = new StringBuilder( szTempString_4 );
                               GetStringFromAttribute( sb_szTempString_4, zqFrame, "ResultSetDisplayMapping", "SourceEntityName" );
               szTempString_4 = sb_szTempString_4.toString( );}
               lTempInteger_3 = CheckExistenceOfEntity( vResultSet, szTempString_4 );
               if ( lTempInteger_3 >= zCURSOR_SET )
               { 
                  //:   DisplayCount = DisplayCount + 1
                  DisplayCount = DisplayCount + 1;
                  //:   szDisplayCount = DisplayCount
                   {StringBuilder sb_szDisplayCount;
                  if ( szDisplayCount == null )
                     sb_szDisplayCount = new StringBuilder( 32 );
                  else
                     sb_szDisplayCount = new StringBuilder( szDisplayCount );
                                    ZeidonStringConvertFromNumber( sb_szDisplayCount, 1, 0, 2, DisplayCount, (double) 0.0, "I" );
                  szDisplayCount = sb_szDisplayCount.toString( );}
                  //:   szDisplayAttribute = "ColumnValue" + szDisplayCount
                   {StringBuilder sb_szDisplayAttribute;
                  if ( szDisplayAttribute == null )
                     sb_szDisplayAttribute = new StringBuilder( 32 );
                  else
                     sb_szDisplayAttribute = new StringBuilder( szDisplayAttribute );
                                    ZeidonStringCopy( sb_szDisplayAttribute, 1, 0, "ColumnValue", 1, 0, 33 );
                  szDisplayAttribute = sb_szDisplayAttribute.toString( );}
                   {StringBuilder sb_szDisplayAttribute;
                  if ( szDisplayAttribute == null )
                     sb_szDisplayAttribute = new StringBuilder( 32 );
                  else
                     sb_szDisplayAttribute = new StringBuilder( szDisplayAttribute );
                                    ZeidonStringConcat( sb_szDisplayAttribute, 1, 0, szDisplayCount, 1, 0, 33 );
                  szDisplayAttribute = sb_szDisplayAttribute.toString( );}
                  //:   szMappingEntity    = zqFrame.ResultSetDisplayMapping.SourceEntityName 
                  {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
                  StringBuilder sb_szMappingEntity;
                  if ( szMappingEntity == null )
                     sb_szMappingEntity = new StringBuilder( 32 );
                  else
                     sb_szMappingEntity = new StringBuilder( szMappingEntity );
                                     GetVariableFromAttribute( sb_szMappingEntity, mi_lTempInteger_4, 'S', 33, zqFrame, "ResultSetDisplayMapping", "SourceEntityName", "", 0 );
                  lTempInteger_4 = mi_lTempInteger_4.intValue( );
                  szMappingEntity = sb_szMappingEntity.toString( );}
                  //:   szMappingAttribute = zqFrame.ResultSetDisplayMapping.SourceAttributeName 
                  {MutableInt mi_lTempInteger_5 = new MutableInt( lTempInteger_5 );
                  StringBuilder sb_szMappingAttribute;
                  if ( szMappingAttribute == null )
                     sb_szMappingAttribute = new StringBuilder( 32 );
                  else
                     sb_szMappingAttribute = new StringBuilder( szMappingAttribute );
                                     GetVariableFromAttribute( sb_szMappingAttribute, mi_lTempInteger_5, 'S', 33, zqFrame, "ResultSetDisplayMapping", "SourceAttributeName", "", 0 );
                  lTempInteger_5 = mi_lTempInteger_5.intValue( );
                  szMappingAttribute = sb_szMappingAttribute.toString( );}
                  //:   GetStringFromAttributeByContext( szDisplayValue, vResultSet, szMappingEntity, szMappingAttribute, "", 254 )
                  {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                     GetStringFromAttributeByContext( sb_szDisplayValue, vResultSet, szMappingEntity, szMappingAttribute, "", 254 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
                  //:   IF DisplayCount = 1
                  if ( DisplayCount == 1 )
                  { 
                     //:   szDisplayValue = "NOT ALL DATA is being displayed, please use Export to see all data."
                      {StringBuilder sb_szDisplayValue;
                     if ( szDisplayValue == null )
                        sb_szDisplayValue = new StringBuilder( 32 );
                     else
                        sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                          ZeidonStringCopy( sb_szDisplayValue, 1, 0, "NOT ALL DATA is being displayed, please use Export to see all data.", 1, 0, 255 );
                     szDisplayValue = sb_szDisplayValue.toString( );}
                  } 

                  //:   END
                  //:   SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szDisplayAttribute, szDisplayValue )
                  SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szDisplayAttribute, szDisplayValue );
               } 

               //:  END
               //:  szMappingEntityLast = szMappingEntity
                {StringBuilder sb_szMappingEntityLast;
               if ( szMappingEntityLast == null )
                  sb_szMappingEntityLast = new StringBuilder( 32 );
               else
                  sb_szMappingEntityLast = new StringBuilder( szMappingEntityLast );
                              ZeidonStringCopy( sb_szMappingEntityLast, 1, 0, szMappingEntity, 1, 0, 33 );
               szMappingEntityLast = sb_szMappingEntityLast.toString( );}
               RESULT = SetCursorNextEntity( zqFrame, "ResultSetDisplayMapping", "" );
            } 

            //:  END
         } 

         //: END
         //: 
         //: nRC = SetCursorNextEntity( vResultSet, RS_RootEntityName, "" )
         nRC = SetCursorNextEntity( vResultSet, RS_RootEntityName, "" );
      } 

      //: END
      //:ELSE
   } 
   else
   { 

      //:// Loop through all root entities in the Result Set and map values to the ResultSetDisplayRow entities.
      //:// Also, compute number of roots.
      //:RootCount = 0
      RootCount = 0;
      //:nRC = SetCursorFirstEntity( vResultSet, RS_RootEntityName, "" )
      nRC = SetCursorFirstEntity( vResultSet, RS_RootEntityName, "" );
      //:LOOP WHILE nRC >= zCURSOR_SET
      while ( nRC >= zCURSOR_SET )
      { 
         //:RootCount = RootCount + 1
         RootCount = RootCount + 1;

         //:CREATE ENTITY zqFrame.ResultSetDisplayRow
         RESULT = CreateEntity( zqFrame, "ResultSetDisplayRow", zPOS_AFTER );

         //:// Set the ID of the Root Entity.
         //:GetIntegerFromAttribute( RootEntityID, vResultSet, RS_RootEntityName, "ID" )
         {MutableInt mi_RootEntityID = new MutableInt( RootEntityID );
                   GetIntegerFromAttribute( mi_RootEntityID, vResultSet, RS_RootEntityName, "ID" );
         RootEntityID = mi_RootEntityID.intValue( );}
         //:zqFrame.ResultSetDisplayRow.RootEntityID = RootEntityID
         SetAttributeFromInteger( zqFrame, "ResultSetDisplayRow", "RootEntityID", RootEntityID );

         //:// Set an attribute value for each ResultSetDisplayMapping entry.
         //:DisplayCount = 0
         DisplayCount = 0;
         //:FOR EACH  zqFrame.ResultSetDisplayMapping 
         RESULT = SetCursorFirstEntity( zqFrame, "ResultSetDisplayMapping", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:DisplayCount = DisplayCount + 1
            DisplayCount = DisplayCount + 1;
            //:szDisplayCount = DisplayCount
             {StringBuilder sb_szDisplayCount;
            if ( szDisplayCount == null )
               sb_szDisplayCount = new StringBuilder( 32 );
            else
               sb_szDisplayCount = new StringBuilder( szDisplayCount );
                        ZeidonStringConvertFromNumber( sb_szDisplayCount, 1, 0, 2, DisplayCount, (double) 0.0, "I" );
            szDisplayCount = sb_szDisplayCount.toString( );}
            //:szDisplayAttribute = "ColumnValue" + szDisplayCount
             {StringBuilder sb_szDisplayAttribute;
            if ( szDisplayAttribute == null )
               sb_szDisplayAttribute = new StringBuilder( 32 );
            else
               sb_szDisplayAttribute = new StringBuilder( szDisplayAttribute );
                        ZeidonStringCopy( sb_szDisplayAttribute, 1, 0, "ColumnValue", 1, 0, 33 );
            szDisplayAttribute = sb_szDisplayAttribute.toString( );}
             {StringBuilder sb_szDisplayAttribute;
            if ( szDisplayAttribute == null )
               sb_szDisplayAttribute = new StringBuilder( 32 );
            else
               sb_szDisplayAttribute = new StringBuilder( szDisplayAttribute );
                        ZeidonStringConcat( sb_szDisplayAttribute, 1, 0, szDisplayCount, 1, 0, 33 );
            szDisplayAttribute = sb_szDisplayAttribute.toString( );}
            //:IF zqFrame.ResultSetDisplayMapping.SubgroupAction = "" 
            if ( CompareAttributeToString( zqFrame, "ResultSetDisplayMapping", "SubgroupAction", "" ) == 0 )
            { 
               //:// Regular Mapping Entity
               //:szMappingEntity    = zqFrame.ResultSetDisplayMapping.SourceEntityName 
               {MutableInt mi_lTempInteger_6 = new MutableInt( lTempInteger_6 );
               StringBuilder sb_szMappingEntity;
               if ( szMappingEntity == null )
                  sb_szMappingEntity = new StringBuilder( 32 );
               else
                  sb_szMappingEntity = new StringBuilder( szMappingEntity );
                               GetVariableFromAttribute( sb_szMappingEntity, mi_lTempInteger_6, 'S', 33, zqFrame, "ResultSetDisplayMapping", "SourceEntityName", "", 0 );
               lTempInteger_6 = mi_lTempInteger_6.intValue( );
               szMappingEntity = sb_szMappingEntity.toString( );}
               //:szMappingAttribute = zqFrame.ResultSetDisplayMapping.SourceAttributeName 
               {MutableInt mi_lTempInteger_7 = new MutableInt( lTempInteger_7 );
               StringBuilder sb_szMappingAttribute;
               if ( szMappingAttribute == null )
                  sb_szMappingAttribute = new StringBuilder( 32 );
               else
                  sb_szMappingAttribute = new StringBuilder( szMappingAttribute );
                               GetVariableFromAttribute( sb_szMappingAttribute, mi_lTempInteger_7, 'S', 33, zqFrame, "ResultSetDisplayMapping", "SourceAttributeName", "", 0 );
               lTempInteger_7 = mi_lTempInteger_7.intValue( );
               szMappingAttribute = sb_szMappingAttribute.toString( );}
               //:GetStringFromAttributeByContext( szDisplayValue, vResultSet, szMappingEntity, szMappingAttribute, "", 254 )
               {StringBuilder sb_szDisplayValue;
               if ( szDisplayValue == null )
                  sb_szDisplayValue = new StringBuilder( 32 );
               else
                  sb_szDisplayValue = new StringBuilder( szDisplayValue );
                               GetStringFromAttributeByContext( sb_szDisplayValue, vResultSet, szMappingEntity, szMappingAttribute, "", 254 );
               szDisplayValue = sb_szDisplayValue.toString( );}
               //:SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szDisplayAttribute, szDisplayValue )
               SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szDisplayAttribute, szDisplayValue );
               //:ELSE
            } 
            else
            { 
               //:// Action Mapping
               //:ComputeOperationValue( zqFrame,
               //:                       vResultSet,
               //:                       OperationResult,
               //:                       zqFrame.ResultSetDisplayMapping.SubgroupAction,
               //:                       zqFrame.ResultSetDisplayMapping.SourceEntityName,
               //:                       zqFrame.ResultSetDisplayMapping.SourceAttributeName,
               //:                       RS_RootEntityName )
               {StringBuilder sb_szTempString_5;
               if ( szTempString_5 == null )
                  sb_szTempString_5 = new StringBuilder( 32 );
               else
                  sb_szTempString_5 = new StringBuilder( szTempString_5 );
                               GetStringFromAttribute( sb_szTempString_5, zqFrame, "ResultSetDisplayMapping", "SubgroupAction" );
               szTempString_5 = sb_szTempString_5.toString( );}
               {StringBuilder sb_szTempString_6;
               if ( szTempString_6 == null )
                  sb_szTempString_6 = new StringBuilder( 32 );
               else
                  sb_szTempString_6 = new StringBuilder( szTempString_6 );
                               GetStringFromAttribute( sb_szTempString_6, zqFrame, "ResultSetDisplayMapping", "SourceEntityName" );
               szTempString_6 = sb_szTempString_6.toString( );}
               {StringBuilder sb_szTempString_7;
               if ( szTempString_7 == null )
                  sb_szTempString_7 = new StringBuilder( 32 );
               else
                  sb_szTempString_7 = new StringBuilder( szTempString_7 );
                               GetStringFromAttribute( sb_szTempString_7, zqFrame, "ResultSetDisplayMapping", "SourceAttributeName" );
               szTempString_7 = sb_szTempString_7.toString( );}
               {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                               ozqFrame_ComputeOperationValue( zqFrame, vResultSet, md_OperationResult, szTempString_5, szTempString_6, szTempString_7, RS_RootEntityName );
               OperationResult = md_OperationResult.doubleValue( );}
               //:szDecimalValue = OperationResult    // Convert decimal value to a string.
                {StringBuilder sb_szDecimalValue;
               if ( szDecimalValue == null )
                  sb_szDecimalValue = new StringBuilder( 32 );
               else
                  sb_szDecimalValue = new StringBuilder( szDecimalValue );
                              ZeidonStringConvertFromNumber( sb_szDecimalValue, 1, 0, 20, 0, OperationResult, "D" );
               szDecimalValue = sb_szDecimalValue.toString( );}
               //:// To right justify the value, prefix the value with enough spaces to make value 12 characters.
               //:Length = zstrlen( szDecimalValue )
               Length = zstrlen( szDecimalValue );
               //:IF Length < 12
               if ( Length < 12 )
               { 
                  //:SpacesLength = 12 - Length
                  SpacesLength = 12 - Length;
                  //:szSpaces       = "             "
                   {StringBuilder sb_szSpaces;
                  if ( szSpaces == null )
                     sb_szSpaces = new StringBuilder( 32 );
                  else
                     sb_szSpaces = new StringBuilder( szSpaces );
                                    ZeidonStringCopy( sb_szSpaces, 1, 0, "             ", 1, 0, 21 );
                  szSpaces = sb_szSpaces.toString( );}
                  //:szDisplayValue = szSpaces[1:SpacesLength] + szDecimalValue
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringCopy( sb_szDisplayValue, 1, 0, szSpaces, 1, SpacesLength, 255 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringConcat( sb_szDisplayValue, 1, 0, szDecimalValue, 1, 0, 255 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:szDisplayValue = szDecimalValue
                   {StringBuilder sb_szDisplayValue;
                  if ( szDisplayValue == null )
                     sb_szDisplayValue = new StringBuilder( 32 );
                  else
                     sb_szDisplayValue = new StringBuilder( szDisplayValue );
                                    ZeidonStringCopy( sb_szDisplayValue, 1, 0, szDecimalValue, 1, 0, 255 );
                  szDisplayValue = sb_szDisplayValue.toString( );}
               } 

               //:END
               //:SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szDisplayAttribute, szDisplayValue )
               SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szDisplayAttribute, szDisplayValue );
            } 

            RESULT = SetCursorNextEntity( zqFrame, "ResultSetDisplayMapping", "" );
            //:END
         } 

         //:END

         //:nRC = SetCursorNextEntity( vResultSet, RS_RootEntityName, "" )
         nRC = SetCursorNextEntity( vResultSet, RS_RootEntityName, "" );
      } 

      //:END
   } 


   //:END
   //:zqFrame.zqFrame.CountOfReturnedRoots = RootCount
   SetAttributeFromInteger( zqFrame, "zqFrame", "CountOfReturnedRoots", RootCount );

   //:// Reset General Parm order, if necessary.
   //:IF szDisplayOrderFlag = "Y"
   if ( ZeidonStringCompare( szDisplayOrderFlag, 1, 0, "Y", 1, 0, 2 ) == 0 )
   { 
      //:OrderEntityForView( zqFrame, "GeneralParameter", "OriginalOrder A" )
      OrderEntityForView( zqFrame, "GeneralParameter", "OriginalOrder A" );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dFinalParameterValue( VIEW zqFrame BASED ON LOD zqFrame,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   VIEW wXferO   REGISTERED AS wXferO
public int 
ozqFrame_dFinalParameterValue( View     zqFrame,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wQueryWk BASED ON LOD  wQueryWk
   zVIEW    wQueryWk = new zVIEW( );
   //:STRING ( 10 )   szDate
   String   szDate = null;
   //:STRING ( 32 )   szEntityName
   String   szEntityName = null;
   //:STRING ( 32 )   szAttributeName
   String   szAttributeName = null;
   //:STRING ( 2000 ) szValue
   String   szValue = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

 
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

          StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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


//:TRANSFORMATION OPERATION
//:FormatCSV_TotalsLine( VIEW zqFrame BASED ON LOD zqFrame,
//:                      STRING ( 10000 ) OutputLine,
//:                      VIEW vResultSet )

//:   VIEW wXferO REGISTERED AS wXferO
public void 
ozqFrame_FormatCSV_TotalsLine( View     zqFrame,
                               StringBuilder   OutputLine,
                               View     vResultSet )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:DECIMAL       OperationResult
   double  OperationResult = 0.0;
   //:STRING ( 20 ) FormattedValue
   String   FormattedValue = null;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   String   szTempString_3 = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );

   //:// Create a line for holding each COUNT, SUM, etc. for the root entity.
   //:// Loop through all root operations and compute the total.

   //:FOR EACH zqFrame.GeneralParameter
   //:        WHERE zqFrame.GeneralParameter.SubgroupSearchType != ""
   //:          AND zqFrame.GeneralParameter.ListDisplayFlag = "Y"
   //:          AND zqFrame.GeneralParameter.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y" ) == 0 &&
           CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 )
      { 
         //:       
         //:ComputeOperationValue( zqFrame,
         //:                    vResultSet,
         //:                    OperationResult,
         //:                    zqFrame.GeneralParameter.SubgroupSearchType,
         //:                    zqFrame.GeneralParameter.EntityName,
         //:                    zqFrame.GeneralParameter.AttributeName,
         //:                    zqFrame.zqFrame.QueryObjectRootEntityName )
         {StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetStringFromAttribute( sb_szTempString_0, zqFrame, "GeneralParameter", "SubgroupSearchType" );
         szTempString_0 = sb_szTempString_0.toString( );}
         {StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetStringFromAttribute( sb_szTempString_1, zqFrame, "GeneralParameter", "EntityName" );
         szTempString_1 = sb_szTempString_1.toString( );}
         {StringBuilder sb_szTempString_2;
         if ( szTempString_2 == null )
            sb_szTempString_2 = new StringBuilder( 32 );
         else
            sb_szTempString_2 = new StringBuilder( szTempString_2 );
                   GetStringFromAttribute( sb_szTempString_2, zqFrame, "GeneralParameter", "AttributeName" );
         szTempString_2 = sb_szTempString_2.toString( );}
         {StringBuilder sb_szTempString_3;
         if ( szTempString_3 == null )
            sb_szTempString_3 = new StringBuilder( 32 );
         else
            sb_szTempString_3 = new StringBuilder( szTempString_3 );
                   GetStringFromAttribute( sb_szTempString_3, zqFrame, "zqFrame", "QueryObjectRootEntityName" );
         szTempString_3 = sb_szTempString_3.toString( );}
         {MutableDouble md_OperationResult = new MutableDouble( OperationResult );
                   ozqFrame_ComputeOperationValue( zqFrame, vResultSet, md_OperationResult, szTempString_0, szTempString_1, szTempString_2, szTempString_3 );
         OperationResult = md_OperationResult.doubleValue( );}
         //:wXferO.QueryValues.WorkRevenue = OperationResult
         SetAttributeFromDecimal( wXferO, "QueryValues", "WorkRevenue", OperationResult );
         //:FormattedValue = wXferO.QueryValues.WorkRevenue
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_FormattedValue;
         if ( FormattedValue == null )
            sb_FormattedValue = new StringBuilder( 32 );
         else
            sb_FormattedValue = new StringBuilder( FormattedValue );
                   GetVariableFromAttribute( sb_FormattedValue, mi_lTempInteger_0, 'S', 21, wXferO, "QueryValues", "WorkRevenue", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         FormattedValue = sb_FormattedValue.toString( );}
         //:OutputLine = OutputLine + QUOTES + FormattedValue + QUOTES + ","
         ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
         ZeidonStringConcat( OutputLine, 1, 0, FormattedValue, 1, 0, 10001 );
         ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
         ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
   } 

   //:END
   return;
// END
} 


//:LOCAL OPERATION
public void 
ozqFrame_PositionOnSourceCtrl( View     qBaseR_Source,
                               String   DataType )
{
   int      RESULT = 0;

   //:PositionOnSourceCtrl( VIEW qBaseR_Source,
   //:                   STRING ( 1 ) DataType )

   //:IF DataType = "L"
   if ( ZeidonStringCompare( DataType, 1, 0, "L", 1, 0, 2 ) == 0 )
   { 
      //:// Type is integer.
      //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "Integer"
      RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "Integer", "" );
      //:ELSE
   } 
   else
   { 
      //:IF DataType = "M"
      if ( ZeidonStringCompare( DataType, 1, 0, "M", 1, 0, 2 ) == 0 )
      { 
         //:// Type is decimal.
         //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "Decimal"
         RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "Decimal", "" );
         //:ELSE
      } 
      else
      { 
         //:IF DataType = "T"
         if ( ZeidonStringCompare( DataType, 1, 0, "T", 1, 0, 2 ) == 0 )
         { 
            //:// Type is datetime.
            //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "DateTime"
            RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "DateTime", "" );
            //:ELSE
         } 
         else
         { 
            //:IF DataType = "D"
            if ( ZeidonStringCompare( DataType, 1, 0, "D", 1, 0, 2 ) == 0 )
            { 
               //:// Type is date.
               //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "Date"
               RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "Date", "" );
               //:ELSE
            } 
            else
            { 
               //:// Type is string.
               //:SET CURSOR FIRST qBaseR_Source.Control WHERE qBaseR_Source.Control.Tag = "Text"
               RESULT = SetCursorFirstEntityByString( qBaseR_Source, "Control", "Tag", "Text", "" );
            } 

            //:END
         } 

         //:END
      } 

      //:END
   } 

   //:END
   return;
// END
} 


//:TRANSFORMATION OPERATION
//:BuildConditionValues( VIEW zqFrameOrig BASED ON LOD zqFrame )

//:   VIEW zqFrame BASED ON LOD zqFrame
public int 
ozqFrame_BuildConditionValues( View     zqFrameOrig )
{
   zVIEW    zqFrame = new zVIEW( );
   //:INTEGER ConditionCount
   int      ConditionCount = 0;
   //:STRING ( 2 ) szConditionCount
   String   szConditionCount = null;
   //:STRING ( 3 ) ConditionName
   String   ConditionName = null;
   //:STRING ( 50 ) szTraceValue
   String   szTraceValue = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;


   //:// Build the Condition entities for the Boolean operator.

   //:CreateViewFromView( zqFrame, zqFrameOrig )
   CreateViewFromView( zqFrame, zqFrameOrig );
   //:NAME VIEW zqFrame "zqFrameTemp"
   SetNameForView( zqFrame, "zqFrameTemp", null, zLEVEL_TASK );

   //:// Make sure that RootQualificationFlag is set for Root Entities.
   //:FOR EACH zqFrame.GeneralParameter 
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF zqFrame.GeneralParameter.EntityName = zqFrame.zqFrame.QueryObjectRootEntityName
      if ( CompareAttributeToAttribute( zqFrame, "GeneralParameter", "EntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 )
      { 
         //:zqFrame.GeneralParameter.RootQualificationFlag = "Y"
         SetAttributeFromString( zqFrame, "GeneralParameter", "RootQualificationFlag", "Y" );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END 
   } 

   //:END

   //:IF zqFrame.BooleanExpression DOES NOT EXIST
   lTempInteger_0 = CheckExistenceOfEntity( zqFrame, "BooleanExpression" );
   if ( lTempInteger_0 != 0 )
   { 
      //:CREATE ENTITY zqFrame.BooleanExpression  
      RESULT = CreateEntity( zqFrame, "BooleanExpression", zPOS_AFTER );
   } 

   //:END
   //:FOR EACH zqFrame.Condition 
   RESULT = SetCursorFirstEntity( zqFrame, "Condition", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.Condition NONE  
      RESULT = DeleteEntity( zqFrame, "Condition", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "Condition", "" );
   } 

   //:END
   //:ConditionCount = 0
   ConditionCount = 0;
   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF zqFrame.GeneralParameter.SearchType != ""
      if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SearchType", "" ) != 0 )
      { 
         //:IF ( zqFrame.GeneralParameter.RootQualificationFlag = "Y" AND
         //:     //zqFrame.GeneralParameter.DerivedAttributeFlag = "" AND
         //:     zqFrame.GeneralParameter.AttributeName != "GeographicPositionZipCode" AND
         //:     zqFrame.GeneralParameter.AttributeName != "GeographicPositionMiles" AND
         //:     zqFrame.GeneralParameter.SubgroupSearchType = "" ) OR
         //:   ( zqFrame.GeneralParameter.SubgroupSearchType = "ANY" AND
         //:     zqFrame.GeneralParameter.ScopingEntityName  = zqFrame.zqFrame.QueryObjectRootEntityName AND
         //:     //zqFrame.GeneralParameter.DerivedAttributeFlag = "" AND
         //:     zqFrame.GeneralParameter.AttributeName != "GeographicPositionZipCode" AND
         //:     zqFrame.GeneralParameter.AttributeName != "GeographicPositionMiles"  ) OR
         //:   ( zqFrame.GeneralParameter.SubgroupSearchType != "" AND
         //:     zqFrame.GeneralParameter.ScopingEntityName  = zqFrame.zqFrame.QueryObjectRootEntityName )
         if ( ( CompareAttributeToString( zqFrame, "GeneralParameter", "RootQualificationFlag", "Y" ) == 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionZipCode" ) != 0 &&
              CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionMiles" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ) ||
              ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "ANY" ) == 0 && CompareAttributeToAttribute( zqFrame, "GeneralParameter", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 &&
              CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionZipCode" ) != 0 && CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", "GeographicPositionMiles" ) != 0 ) ||
              ( CompareAttributeToString( zqFrame, "GeneralParameter", "SubgroupSearchType", "" ) != 0 && CompareAttributeToAttribute( zqFrame, "GeneralParameter", "ScopingEntityName", zqFrame, "zqFrame", "QueryObjectRootEntityName" ) == 0 ) )
         { 

            //:CREATE ENTITY zqFrame.Condition  
            RESULT = CreateEntity( zqFrame, "Condition", zPOS_AFTER );
            //:ConditionCount = ConditionCount + 1
            ConditionCount = ConditionCount + 1;
            //:szConditionCount = ConditionCount
             {StringBuilder sb_szConditionCount;
            if ( szConditionCount == null )
               sb_szConditionCount = new StringBuilder( 32 );
            else
               sb_szConditionCount = new StringBuilder( szConditionCount );
                        ZeidonStringConvertFromNumber( sb_szConditionCount, 1, 0, 2, ConditionCount, (double) 0.0, "I" );
            szConditionCount = sb_szConditionCount.toString( );}
            //:ConditionName = "C" + szConditionCount
             {StringBuilder sb_ConditionName;
            if ( ConditionName == null )
               sb_ConditionName = new StringBuilder( 32 );
            else
               sb_ConditionName = new StringBuilder( ConditionName );
                        ZeidonStringCopy( sb_ConditionName, 1, 0, "C", 1, 0, 4 );
            ConditionName = sb_ConditionName.toString( );}
             {StringBuilder sb_ConditionName;
            if ( ConditionName == null )
               sb_ConditionName = new StringBuilder( 32 );
            else
               sb_ConditionName = new StringBuilder( ConditionName );
                        ZeidonStringConcat( sb_ConditionName, 1, 0, szConditionCount, 1, 0, 4 );
            ConditionName = sb_ConditionName.toString( );}
            //:zqFrame.Condition.BooleanConditionName = ConditionName
            SetAttributeFromString( zqFrame, "Condition", "BooleanConditionName", ConditionName );
            //:SetMatchingAttributesByName( zqFrame, "Condition",
            //:                             zqFrame, "GeneralParameter", zSET_NULL )
            SetMatchingAttributesByName( zqFrame, "Condition", zqFrame, "GeneralParameter", zSET_NULL );
            //:zqFrame.GeneralParameter.wBooleanConditionName = zqFrame.Condition.BooleanConditionName 
            SetAttributeFromAttribute( zqFrame, "GeneralParameter", "wBooleanConditionName", zqFrame, "Condition", "BooleanConditionName" );
            //:IF zqFrame.GeneralParameter.SearchType = "Subselect"
            if ( CompareAttributeToString( zqFrame, "GeneralParameter", "SearchType", "Subselect" ) == 0 )
            { 
               //:// For Subselect search, use dSubSelectQualification value.
               //:zqFrame.Condition.FullSearchCriteria = zqFrame.GeneralParameter.dSubSelectQualification 
               SetAttributeFromAttribute( zqFrame, "Condition", "FullSearchCriteria", zqFrame, "GeneralParameter", "dSubSelectQualification" );
               //:ELSE
            } 
            else
            { 
               //:// For regular search, concatenate Search Oper and Value.
               //:zqFrame.Condition.FullSearchCriteria = zqFrame.GeneralParameter.SearchType + " " +
               //:                                       zqFrame.GeneralParameter.Value 
               {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                               GetStringFromAttribute( sb_szTempString_0, zqFrame, "GeneralParameter", "SearchType" );
               szTempString_0 = sb_szTempString_0.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, " ", 1, 0, 10000001 );
               szTempString_0 = sb_szTempString_0.toString( );}
               {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
               StringBuilder sb_szTempString_1;
               if ( szTempString_1 == null )
                  sb_szTempString_1 = new StringBuilder( 32 );
               else
                  sb_szTempString_1 = new StringBuilder( szTempString_1 );
                               GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 255, zqFrame, "GeneralParameter", "Value", "", 0 );
               lTempInteger_1 = mi_lTempInteger_1.intValue( );
               szTempString_1 = sb_szTempString_1.toString( );}
                {StringBuilder sb_szTempString_0;
               if ( szTempString_0 == null )
                  sb_szTempString_0 = new StringBuilder( 32 );
               else
                  sb_szTempString_0 = new StringBuilder( szTempString_0 );
                              ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 10000001 );
               szTempString_0 = sb_szTempString_0.toString( );}
               SetAttributeFromString( zqFrame, "Condition", "FullSearchCriteria", szTempString_0 );
            } 

            //:END
            //:ELSE
         } 
         else
         { 
            //:zqFrame.GeneralParameter.wBooleanConditionName = ""
            SetAttributeFromString( zqFrame, "GeneralParameter", "wBooleanConditionName", "" );
         } 

         //:END 
         //:ELSE
      } 
      else
      { 
         //:zqFrame.GeneralParameter.wBooleanConditionName = ""
         SetAttributeFromString( zqFrame, "GeneralParameter", "wBooleanConditionName", "" );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END
   //:DropView( zqFrame )
   DropView( zqFrame );
   //:SET CURSOR FIRST zqFrameOrig.GeneralParameter 
   RESULT = SetCursorFirstEntity( zqFrameOrig, "GeneralParameter", "" );
   //:SET CURSOR FIRST zqFrameOrig.Condition 
   RESULT = SetCursorFirstEntity( zqFrameOrig, "Condition", "" );
   return( 0 );
// END
} 


//:LOCAL OPERATION
//:fnProcessFontOverride( VIEW zqFrameRoot BASED ON LOD zqFrame,
//:                       VIEW qBaseR )

//:   // Report Control attributes
//:   SHORT nMultiLineFlag
public void 
ozqFrame_fnProcessFontOverride( View     zqFrameRoot,
                                View     qBaseR )
{
   int      nMultiLineFlag = 0;
   //:SHORT nBoldFlag
   int      nBoldFlag = 0;
   //:SHORT nItalicFlag
   int      nItalicFlag = 0;
   //:SHORT nStrikeoutFlag
   int      nStrikeoutFlag = 0;
   //:SHORT nUnderlineFlag
   int      nUnderlineFlag = 0;
   //:INTEGER lJustify
   int      lJustify = 0;
   //:INTEGER lFontSize
   int      lFontSize = 0;
   //:INTEGER lOrigFontSize
   int      lOrigFontSize = 0;
   //:INTEGER lColor
   int      lColor = 0;
   //:INTEGER lBorderStyle
   int      lBorderStyle = 0;
   //:INTEGER lBorderColor
   int      lBorderColor = 0;
   //:INTEGER lTextBkColor
   int      lTextBkColor = 0;
   //:INTEGER lEscapement
   int      lEscapement = 0;
   //:INTEGER lType
   int      lType = 0;
   //:STRING ( 32 ) szFaceName
   String   szFaceName = null;


   //:IF zqFrameRoot.zqFrame.OverrideFontSize != ""
   if ( CompareAttributeToString( zqFrameRoot, "zqFrame", "OverrideFontSize", "" ) != 0 )
   { 
      //:TX_GetTextRptCtrlBOI( qBaseR, nMultiLineFlag, nBoldFlag,
      //:                      nItalicFlag, nStrikeoutFlag,
      //:                      nUnderlineFlag, lJustify,
      //:                      lOrigFontSize, lColor, lTextBkColor, 
      //:                      lBorderStyle, lBorderColor,
      //:                      lEscapement, lType, szFaceName )
      {MutableInt mi_lType = new MutableInt( lType );
      MutableInt mi_lEscapement = new MutableInt( lEscapement );
      MutableInt mi_lBorderColor = new MutableInt( lBorderColor );
      MutableInt mi_lBorderStyle = new MutableInt( lBorderStyle );
      MutableInt mi_lTextBkColor = new MutableInt( lTextBkColor );
      MutableInt mi_lColor = new MutableInt( lColor );
      MutableInt mi_lOrigFontSize = new MutableInt( lOrigFontSize );
      MutableInt mi_lJustify = new MutableInt( lJustify );
      MutableInt mi_nUnderlineFlag = new MutableInt( nUnderlineFlag );
      MutableInt mi_nStrikeoutFlag = new MutableInt( nStrikeoutFlag );
      MutableInt mi_nItalicFlag = new MutableInt( nItalicFlag );
      MutableInt mi_nBoldFlag = new MutableInt( nBoldFlag );
      MutableInt mi_nMultiLineFlag = new MutableInt( nMultiLineFlag );
             m_ZDRVROPR.TX_GetTextRptCtrlBOI( qBaseR, mi_nMultiLineFlag, mi_nBoldFlag, mi_nItalicFlag, mi_nStrikeoutFlag, mi_nUnderlineFlag, mi_lJustify, mi_lOrigFontSize, mi_lColor, mi_lTextBkColor, mi_lBorderStyle, mi_lBorderColor, mi_lEscapement, mi_lType, szFaceName );
      lType = mi_lType.intValue( );
      lEscapement = mi_lEscapement.intValue( );
      lBorderColor = mi_lBorderColor.intValue( );
      lBorderStyle = mi_lBorderStyle.intValue( );
      lTextBkColor = mi_lTextBkColor.intValue( );
      lColor = mi_lColor.intValue( );
      lOrigFontSize = mi_lOrigFontSize.intValue( );
      lJustify = mi_lJustify.intValue( );
      nUnderlineFlag = mi_nUnderlineFlag.intValue( );
      nStrikeoutFlag = mi_nStrikeoutFlag.intValue( );
      nItalicFlag = mi_nItalicFlag.intValue( );
      nBoldFlag = mi_nBoldFlag.intValue( );
      nMultiLineFlag = mi_nMultiLineFlag.intValue( );}
      //:lFontSize = zqFrameRoot.zqFrame.OverrideFontSize
      {MutableInt mi_lFontSize = new MutableInt( lFontSize );
             GetIntegerFromAttribute( mi_lFontSize, zqFrameRoot, "zqFrame", "OverrideFontSize" );
      lFontSize = mi_lFontSize.intValue( );}
      //:TraceLineI( "**** Orig FontSize: ", lOrigFontSize )
      TraceLineI( "**** Orig FontSize: ", lOrigFontSize );
      //:IF lOrigFontSize > 15
      if ( lOrigFontSize > 15 )
      { 
         //:lFontSize = lFontSize * 10
         lFontSize = lFontSize * 10;
         //:ELSE
      } 
      else
      { 
         //:lFontSize = lFontSize
         lFontSize = lFontSize;
      } 

      //:END
      //:TraceLineI( "**** FontSize: ", lFontSize )
      TraceLineI( "**** FontSize: ", lFontSize );
      //:TX_SetTextRptCtrlBOI( qBaseR, nMultiLineFlag, nBoldFlag,
      //:                      nItalicFlag, nStrikeoutFlag,
      //:                      nUnderlineFlag, lJustify,
      //:                      lFontSize, lColor, lTextBkColor,
      //:                      lBorderStyle, lBorderColor,
      //:                      lEscapement, lType, szFaceName )
      m_ZDRVROPR.TX_SetTextRptCtrlBOI( qBaseR, nMultiLineFlag, nBoldFlag, nItalicFlag, nStrikeoutFlag, nUnderlineFlag, lJustify, lFontSize, lColor, lTextBkColor, lBorderStyle, lBorderColor, lEscapement, lType, szFaceName );
   } 

   //:END
   return;
// END
} 


//:TRANSFORMATION OPERATION
//:FormatCSV_FullOutput( VIEW zqFrame BASED ON LOD zqFrame,
//:                      STRING ( 10000 ) OutputLine )

//:   INTEGER Count
public int 
ozqFrame_FormatCSV_FullOutput( View     zqFrame,
                               StringBuilder   OutputLine )
{
   int      Count = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_1 = 0;


   //:// Format OutputLine from CSV_OutputLineItem.
   //:OutputLine = ""
   ZeidonStringCopy( OutputLine, 1, 0, "", 1, 0, 10001 );
   //:Count = 0
   Count = 0;
   //:FOR EACH zqFrame.CSV_OutputLineItem 
   RESULT = SetCursorFirstEntity( zqFrame, "CSV_OutputLineItem", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:IF Count = 1
      if ( Count == 1 )
      { 
         //:// First attribute does not have beginning comma.
         //:OutputLine = OutputLine + QUOTES + zqFrame.CSV_OutputLineItem.Value + QUOTES
         ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szTempString_0;
         if ( szTempString_0 == null )
            sb_szTempString_0 = new StringBuilder( 32 );
         else
            sb_szTempString_0 = new StringBuilder( szTempString_0 );
                   GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 10000001, zqFrame, "CSV_OutputLineItem", "Value", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szTempString_0 = sb_szTempString_0.toString( );}
         ZeidonStringConcat( OutputLine, 1, 0, szTempString_0, 1, 0, 10001 );
         ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
         //:ELSE
      } 
      else
      { 
         //:// For other attributes, precede the attribute value with a comma.
         //:OutputLine = OutputLine + "," + QUOTES + zqFrame.CSV_OutputLineItem.Value + QUOTES
         ZeidonStringConcat( OutputLine, 1, 0, ",", 1, 0, 10001 );
         ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
         {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
         StringBuilder sb_szTempString_1;
         if ( szTempString_1 == null )
            sb_szTempString_1 = new StringBuilder( 32 );
         else
            sb_szTempString_1 = new StringBuilder( szTempString_1 );
                   GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_1, 'S', 10000001, zqFrame, "CSV_OutputLineItem", "Value", "", 0 );
         lTempInteger_1 = mi_lTempInteger_1.intValue( );
         szTempString_1 = sb_szTempString_1.toString( );}
         ZeidonStringConcat( OutputLine, 1, 0, szTempString_1, 1, 0, 10001 );
         ZeidonStringConcat( OutputLine, 1, 0, QUOTES, 1, 0, 10001 );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "CSV_OutputLineItem", "" );
      //:END
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:ResyncObjectNames( VIEW zqFrame BASED ON LOD zqFrame,
//:                   VIEW vLOD,
//:                   STRING ( 1 ) szLanguageCode )  // Language Code will be used when the ER supports more than one Query Attribute/Entity Name.

//:   STRING ( 1 )   szChangeFlag
public int 
ozqFrame_ResyncObjectNames( View     zqFrame,
                            View     vLOD,
                            String   szLanguageCode )
{
   String   szChangeFlag = null;
   //:STRING ( 100 ) szMsg
   String   szMsg = null;


   //:// If no Entity and Attribute ZKeys exist, build them.
   //:// If Entity and Attribute ZKeys do exist, make sure that the names in the object are the same as the
   //:// names in the LOD, and if not, change them.
   //:// We must change names in GeneralParameter, ObjectEntity and SelectionCriteria. If anything has changed,
   //:// we should also execute BuildConditionValues to rebuild Condition entries.
   //:// The other entities containing EntityName and AttributeName values are rebuilt each time the query
   //:// is run and do not need to be changed.

   //:IssueError( zqFrame,0,0, "This Operation does not appear to be used anywhere." )
   IssueError( zqFrame, 0, 0, "This Operation does not appear to be used anywhere." );
   return( 0 );
//    
//    /*FOR EACH zqFrame.GeneralParameter
//       IF zqFrame.GeneralParameter.EntityZKey = ""
//          // The Entity ZKey has not been set, so set Entity and Attribute ZKeys.
//          SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.Name = zqFrame.GeneralParameter.EntityName 
//          IF RESULT >= zCURSOR_SET 
//             zqFrame.GeneralParameter.EntityZKey = vLOD.LOD_Entity.ZKey
//             SET CURSOR FIRST vLOD.ER_Attribute WITHIN vLOD.LOD_Entity
//                        WHERE vLOD.ER_Attribute.Name = zqFrame.GeneralParameter.AttributeName 
//             IF RESULT >= zCURSOR_SET
//                zqFrame.GeneralParameter.AttributeZKey = vLOD.ER_Attribute.ZKey
//             ELSE
//                IssueError( zqFrame,0,.0, "zqFrame Attribute does not match LOD entity" )
//             END
//             IF zqFrame.GeneralParameter.ScopingEntityName != ""
//                SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.Name = zqFrame.GeneralParameter.ScopingEntityName 
//                IF RESULT >= zCURSOR_SET
//                   zqFrame.GeneralParameter.ScopingEntityZKey = vLOD.LOD_Entity.ZKey
//                ELSE
//                   IssueError( zqFrame,0,.0, "zqFrame Attribute does not match LOD entity" )
//                END
//             END
//          ELSE
//             IssueError( zqFrame,0,.0, "zqFrame Entity does not match LOD entity" )
//          END
//       ELSE
//          // An Entity ZKey exists, so make sure that the Entity and Attribute names in zqFrame are the same
//          // as those in the LOD.
//          
//          SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.ZKey = zqFrame.GeneralParameter.EntityZKey
//          IF RESULT < zCURSOR_SET
//             SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.Name = zqFrame.GeneralParameter.EntityName
//          END
//          IF RESULT >= zCURSOR_SET
//             IF zqFrame.GeneralParameter.EntityName != vLOD.LOD_Entity.Name
//                
//                // Set ObjectEntity Name
//                SET CURSOR FIRST zqFrame.ObjectEntity WHERE zqFrame.ObjectEntity.EntityName = zqFrame.GeneralParameter.EntityName 
//                IF RESULT >= zCURSOR_SET
//                   zqFrame.ObjectEntity.EntityName = vLOD.LOD_Entity.Name
//                ELSE
//                   IssueError( zqFrame,0,.0, "zqFrame ObjectEntity not found" )
//                END
//                
//                // Set SelectionCriteria Scoping Name
//                SET CURSOR FIRST zqFrame.SelectionCriteria WHERE zqFrame.SelectionCriteria.ScopingEntityName = zqFrame.GeneralParameter.EntityName 
//                IF RESULT >= zCURSOR_SET
//                   zqFrame.SelectionCriteria.ScopingEntityName = vLOD.LOD_Entity.Name
//                ELSE
//                   IssueError( zqFrame,0,.0, "zqFrame SelectionCriteria not found" )
//                END
//                
//                // Set General Parameter Name and flag change.
//                zqFrame.GeneralParameter.EntityName = vLOD.LOD_Entity.Name
//                szChangeFlag = "Y"
//             END
//             SET CURSOR FIRST vLOD.ER_Attribute WITHIN vLOD.LOD_Entity
//                        WHERE vLOD.ER_Attribute.ZKey = zqFrame.GeneralParameter.AttributeZKey 
//             IF RESULT < zCURSOR_SET
//                SET CURSOR FIRST vLOD.ER_Attribute WITHIN vLOD.LOD_Entity
//                           WHERE vLOD.ER_Attribute.Name = zqFrame.GeneralParameter.AttributeName
//             END
//             IF RESULT >= zCURSOR_SET
//                IF zqFrame.GeneralParameter.AttributeName != vLOD.ER_Attribute.Name
//                   zqFrame.GeneralParameter.AttributeName = vLOD.ER_Attribute.Name
//                   szChangeFlag = "Y"
//                END
//                zqFrame.GeneralParameter.AttributeZKey = vLOD.ER_Attribute.ZKey
//                
//                // This code makes sure that the DerivedAttributeFlag is set correctly for derived attributes, derived entities and
//                // work entities. This wasn't always set correctly earlier.
//                IF vLOD.LOD_Attribute.Work = "Y" OR 
//                   vLOD.LOD_Entity.Derived = "Y" OR 
//                   vLOD.LOD_Entity.Work = "Y"
//                   
//                   zqFrame.GeneralParameter.DerivedAttributeFlag = "Y"
//                END
//             ELSE
//                IssueError( zqFrame,0,.0, "zqFrame Attribute ZKey not found" )
//             END
//          ELSE
//             szMsg = "Entity, " + zqFrame.GeneralParameter.EntityName + ",in GeneralParameter not found in LOD."
//             IssueError( zqFrame,0,.0, szMsg )
//          END
//          IF zqFrame.GeneralParameter.ScopingEntityName != ""
//             IF zqFrame.GeneralParameter.ScopingEntityZKey != ""
//                SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.ZKey = zqFrame.GeneralParameter.ScopingEntityZKey
//                IF RESULT < zCURSOR_SET
//                   SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.Name = zqFrame.GeneralParameter.ScopingEntityName 
//                END
//                IF RESULT >= zCURSOR_SET
//                   IF zqFrame.GeneralParameter.ScopingEntityName != vLOD.LOD_Entity.Name
//                      zqFrame.GeneralParameter.ScopingEntityName = vLOD.LOD_Entity.Name
//                      szChangeFlag = "Y"
//                   END
//                ELSE
//                   IssueError( zqFrame,0,.0, "zqFrame Scoping Entity ZKey and Name not found" )
//                END
//             ELSE
//                SET CURSOR FIRST vLOD.LOD_Entity WHERE vLOD.LOD_Entity.Name = zqFrame.GeneralParameter.ScopingEntityName 
//                IF RESULT >= zCURSOR_SET
//                   zqFrame.GeneralParameter.ScopingEntityZKey = vLOD.LOD_Entity.ZKey
//                ELSE
//                   IssueError( zqFrame,0,.0, "zqFrame Attribute does not match LOD entity" )
//                END
//             END
//          END
//          
//          // If a GeneralParameter value changed and this Query uses Boolean operators, execute BuildConditionValues 
//          // to rebuild the Condition entries.
//          IF zqFrame.BooleanExpression EXISTS AND szChangeFlag = "Y"
//             BuildConditionValues( zqFrame )
//          END
//          
//       END
//    END*/
// END
} 


//:LOCAL OPERATION
public int 
ozqFrame_SetEntityActivateFlagR( View     zqFrame,
                                 View     zqFrameRoot )
{
   int      RESULT = 0;

   //:SetEntityActivateFlagR( VIEW zqFrame     BASED ON LOD zqFrame,
   //:                     VIEW zqFrameRoot BASED ON LOD zqFrame )

   //:FOR EACH zqFrame.ParentEntity 
   RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:IF zqFrame.ParentEntity.LOD_MaxCardinality != 1
      if ( CompareAttributeToInteger( zqFrame, "ParentEntity", "LOD_MaxCardinality", 1 ) != 0 )
      { 
         //:SET CURSOR FIRST zqFrameRoot.GeneralParameter 
         //:           WHERE zqFrameRoot.GeneralParameter.EntityName = zqFrame.ParentEntity.EntityName 
         //:             AND ( zqFrameRoot.GeneralParameter.SearchType != "" 
         //:                OR zqFrameRoot.GeneralParameter.ForceSubobjectActivateFlag = "Y"
         //:                OR zqFrameRoot.GeneralParameter.ForceFormatMaxCardinalityOne = "Y"
         //:                OR zqFrameRoot.GeneralParameter.SubgroupSearchType != "" )
         RESULT = SetCursorFirstEntity( zqFrameRoot, "GeneralParameter", "" );
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( zqFrameRoot, "GeneralParameter", "EntityName", zqFrame, "ParentEntity", "EntityName" ) != 0 ||
                    ( CompareAttributeToString( zqFrameRoot, "GeneralParameter", "SearchType", "" ) == 0 && CompareAttributeToString( zqFrameRoot, "GeneralParameter", "ForceSubobjectActivateFlag", "Y" ) != 0 &&
                    CompareAttributeToString( zqFrameRoot, "GeneralParameter", "ForceFormatMaxCardinalityOne", "Y" ) != 0 && CompareAttributeToString( zqFrameRoot, "GeneralParameter", "SubgroupSearchType", "" ) == 0 ) ) )
            { 
               RESULT = SetCursorNextEntity( zqFrameRoot, "GeneralParameter", "" );
            } 

         } 

         //:IF RESULT < zCURSOR_SET
         if ( RESULT < zCURSOR_SET )
         { 
            //:zqFrame.ParentEntity.NoActivateSubobjectFlag = "Y"
            SetAttributeFromString( zqFrame, "ParentEntity", "NoActivateSubobjectFlag", "Y" );
         } 

         //:END
      } 

      //:END
      //:SetViewToSubobject( zqFrame, "ChildEntity" )
      SetViewToSubobject( zqFrame, "ChildEntity" );
      //:SetEntityActivateFlagR( zqFrame, zqFrameRoot )
      ozqFrame_SetEntityActivateFlagR( zqFrame, zqFrameRoot );
      //:ResetViewFromSubobject( zqFrame )
      ResetViewFromSubobject( zqFrame );
      RESULT = SetCursorNextEntity( zqFrame, "ParentEntity", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dDisplayBooleanValue( VIEW zqFrame BASED ON LOD zqFrame,
//:                      STRING ( 32 ) InternalEntityStructure,
//:                      STRING ( 32 ) InternalAttribStructure,
//:                      SHORT GetOrSetFlag )

//:   STRING ( 200 ) szCondition
public int 
ozqFrame_dDisplayBooleanValue( View     zqFrame,
                               String InternalEntityStructure,
                               String InternalAttribStructure,
                               Integer   GetOrSetFlag )
{
   String   szCondition = null;
   //:INTEGER Count
   int      Count = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :


         //:END
         //:StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, szCondition )
         StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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
//:dGraphParmMappingName( VIEW zqFrame BASED ON LOD zqFrame,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )

//:   STRING ( 100 )   szDisplayMappingName
public int 
ozqFrame_dGraphParmMappingName( View     zqFrame,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   String   szDisplayMappingName = null;
   int      lTempInteger_0 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_1 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, szDisplayMappingName )
         StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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
//:dSubSelectQual( VIEW zqFrame BASED ON LOD zqFrame,
//:                STRING ( 32 ) InternalEntityStructure,
//:                STRING ( 32 ) InternalAttribStructure,
//:                SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szSubselectQualification
public int 
ozqFrame_dSubSelectQual( View     zqFrame,
                         String InternalEntityStructure,
                         String InternalAttribStructure,
                         Integer   GetOrSetFlag )
{
   String   szSubselectQualification = null;
   //:STRING ( 10 )   szBooleanOperator
   String   szBooleanOperator = null;
   //:STRING ( 10 )   szSelectValue
   String   szSelectValue = null;
   //:INTEGER         Length
   int      Length = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      RESULT = 0;
   int      lTempInteger_2 = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   String   szTempString_1 = null;
   int      lTempInteger_4 = 0;
   String   szTempString_2 = null;
   int      lTempInteger_5 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :


         //:StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, szSubselectQualification )
         StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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
//:dSubSelectQualTree( VIEW zqFrame BASED ON LOD zqFrame,
//:                    STRING ( 32 ) InternalEntityStructure,
//:                    STRING ( 32 ) InternalAttribStructure,
//:                    SHORT GetOrSetFlag )

//:   STRING ( 1000 ) szSubselectQualification
public int 
ozqFrame_dSubSelectQualTree( View     zqFrame,
                             String InternalEntityStructure,
                             String InternalAttribStructure,
                             Integer   GetOrSetFlag )
{
   String   szSubselectQualification = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

          StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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


//:TRANSFORMATION OPERATION
//:ValidateScopingEntity( VIEW zqFrameOrig BASED ON LOD zqFrame )

//:   VIEW zqFrame BASED ON LOD zqFrame
public int 
ozqFrame_ValidateScopingEntity( View     zqFrameOrig )
{
   zVIEW    zqFrame = new zVIEW( );
   //:STRING ( 1 )  FoundFlag
   String   FoundFlag = null;
   //:STRING ( 32 ) szScopingEntityName
   String   szScopingEntityName = null;
   //:STRING ( 32 ) szRootEntityName
   String   szRootEntityName = null;
   //:STRING ( 32 ) szCurrentEntityName
   String   szCurrentEntityName = null;
   //:STRING ( 32 ) szParameterEntityName
   String   szParameterEntityName = null;
   //:INTEGER nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      RESULT = 0;
   String   szTempString_0 = null;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;


   //:// Validate that the Scoping Entity is in the upper path for the qualification entity.
   //:// The Qualification entry must be a parent of the GeneralParameter entry or the Root.
   //:// Because ResetViewFromSubobject is not setting the nRC correctly when it reaches the top, we will
   //:// check for the Root Entity instead.
   //:szRootEntityName      = zqFrameOrig.zqFrame.QueryObjectRootEntityName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szRootEntityName;
   if ( szRootEntityName == null )
      sb_szRootEntityName = new StringBuilder( 32 );
   else
      sb_szRootEntityName = new StringBuilder( szRootEntityName );
       GetVariableFromAttribute( sb_szRootEntityName, mi_lTempInteger_0, 'S', 33, zqFrameOrig, "zqFrame", "QueryObjectRootEntityName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szRootEntityName = sb_szRootEntityName.toString( );}
   //:szParameterEntityName = zqFrameOrig.GeneralParameter.EntityName 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szParameterEntityName;
   if ( szParameterEntityName == null )
      sb_szParameterEntityName = new StringBuilder( 32 );
   else
      sb_szParameterEntityName = new StringBuilder( szParameterEntityName );
       GetVariableFromAttribute( sb_szParameterEntityName, mi_lTempInteger_1, 'S', 33, zqFrameOrig, "GeneralParameter", "EntityName", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szParameterEntityName = sb_szParameterEntityName.toString( );}
   //:szScopingEntityName   = zqFrameOrig.GeneralParameter.ScopingEntityName
   {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
   StringBuilder sb_szScopingEntityName;
   if ( szScopingEntityName == null )
      sb_szScopingEntityName = new StringBuilder( 32 );
   else
      sb_szScopingEntityName = new StringBuilder( szScopingEntityName );
       GetVariableFromAttribute( sb_szScopingEntityName, mi_lTempInteger_2, 'S', 33, zqFrameOrig, "GeneralParameter", "ScopingEntityName", "", 0 );
   lTempInteger_2 = mi_lTempInteger_2.intValue( );
   szScopingEntityName = sb_szScopingEntityName.toString( );}
   //:CreateViewFromView( zqFrame, zqFrameOrig )
   CreateViewFromView( zqFrame, zqFrameOrig );
   //:IF szScopingEntityName != "" AND szScopingEntityName != szParameterEntityName
   if ( ZeidonStringCompare( szScopingEntityName, 1, 0, "", 1, 0, 33 ) != 0 && ZeidonStringCompare( szScopingEntityName, 1, 0, szParameterEntityName, 1, 0, 33 ) != 0 )
   { 

      //:SET CURSOR FIRST zqFrame.ParentEntity
      RESULT = SetCursorFirstEntity( zqFrame, "ParentEntity", "" );
      //:LocateEntityRecurs( zqFrame, zqFrame.GeneralParameter.EntityName )
      {StringBuilder sb_szTempString_0;
      if ( szTempString_0 == null )
         sb_szTempString_0 = new StringBuilder( 32 );
      else
         sb_szTempString_0 = new StringBuilder( szTempString_0 );
             GetStringFromAttribute( sb_szTempString_0, zqFrame, "GeneralParameter", "EntityName" );
      szTempString_0 = sb_szTempString_0.toString( );}
      ozqFrame_LocateEntityRecurs( zqFrame, szTempString_0 );
      //:FoundFlag = "N"
       {StringBuilder sb_FoundFlag;
      if ( FoundFlag == null )
         sb_FoundFlag = new StringBuilder( 32 );
      else
         sb_FoundFlag = new StringBuilder( FoundFlag );
            ZeidonStringCopy( sb_FoundFlag, 1, 0, "N", 1, 0, 2 );
      FoundFlag = sb_FoundFlag.toString( );}
      //://nRC = 0
      //://LOOP WHILE nRC = 0
      //:szCurrentEntityName = zqFrame.ParentEntity.EntityName
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szCurrentEntityName;
      if ( szCurrentEntityName == null )
         sb_szCurrentEntityName = new StringBuilder( 32 );
      else
         sb_szCurrentEntityName = new StringBuilder( szCurrentEntityName );
             GetVariableFromAttribute( sb_szCurrentEntityName, mi_lTempInteger_3, 'S', 33, zqFrame, "ParentEntity", "EntityName", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szCurrentEntityName = sb_szCurrentEntityName.toString( );}
      //:LOOP WHILE szCurrentEntityName != szRootEntityName
      while ( ZeidonStringCompare( szCurrentEntityName, 1, 0, szRootEntityName, 1, 0, 33 ) != 0 )
      { 
         //:nRC = ResetViewFromSubobject( zqFrame )
         nRC = ResetViewFromSubobject( zqFrame );
         //:IF szScopingEntityName = zqFrame.ParentEntity.EntityName
         if ( CompareAttributeToString( zqFrame, "ParentEntity", "EntityName", szScopingEntityName ) == 0 )
         { 
            //:FoundFlag = "Y"
             {StringBuilder sb_FoundFlag;
            if ( FoundFlag == null )
               sb_FoundFlag = new StringBuilder( 32 );
            else
               sb_FoundFlag = new StringBuilder( FoundFlag );
                        ZeidonStringCopy( sb_FoundFlag, 1, 0, "Y", 1, 0, 2 );
            FoundFlag = sb_FoundFlag.toString( );}
         } 

         //:END
         //:szCurrentEntityName = zqFrame.ParentEntity.EntityName
         {MutableInt mi_lTempInteger_4 = new MutableInt( lTempInteger_4 );
         StringBuilder sb_szCurrentEntityName;
         if ( szCurrentEntityName == null )
            sb_szCurrentEntityName = new StringBuilder( 32 );
         else
            sb_szCurrentEntityName = new StringBuilder( szCurrentEntityName );
                   GetVariableFromAttribute( sb_szCurrentEntityName, mi_lTempInteger_4, 'S', 33, zqFrame, "ParentEntity", "EntityName", "", 0 );
         lTempInteger_4 = mi_lTempInteger_4.intValue( );
         szCurrentEntityName = sb_szCurrentEntityName.toString( );}
      } 

      //:END
      //:IF FoundFlag = "N"
      if ( ZeidonStringCompare( FoundFlag, 1, 0, "N", 1, 0, 2 ) == 0 )
      { 
         //:MessageSend( zqFrameOrig, "", "Logical Query",
         //:             "Scoping Entity is not the same entity or a parent entity of the current Entity on the same line.",
         //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
         MessageSend( zqFrameOrig, "", "Logical Query", "Scoping Entity is not the same entity or a parent entity of the current Entity on the same line.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
         //:DropView( zqFrame )
         DropView( zqFrame );
         //:RETURN 2
         if(8==8)return( 2 );
      } 

      //:END
   } 

   //:END

   //:DropView( zqFrame )
   DropView( zqFrame );
   //:RETURN 0
   return( 0 );
//    
// END
} 


//:DERIVED ATTRIBUTE OPERATION
//:dGenParmExternalValue( VIEW zqFrame BASED ON LOD zqFrame,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   VIEW sHost REGISTERED AS sHost
public int 
ozqFrame_dGenParmExternalValue( View     zqFrame,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wXferO  REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:STRING ( 1000 ) szExternalValue
   String   szExternalValue = null;
   //:STRING ( 32 ) szDateFormat
   String   szDateFormat = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

 
   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :
         //:StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, szExternalValue )
         StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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
//:dSelCritExternalValue( VIEW zqFrame BASED ON LOD zqFrame,
//:                       STRING ( 32 ) InternalEntityStructure,
//:                       STRING ( 32 ) InternalAttribStructure,
//:                       SHORT GetOrSetFlag )
//:   VIEW sHost REGISTERED AS sHost
public int 
ozqFrame_dSelCritExternalValue( View     zqFrame,
                                String InternalEntityStructure,
                                String InternalAttribStructure,
                                Integer   GetOrSetFlag )
{
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:VIEW wXferO  REGISTERED AS wXferO
   zVIEW    wXferO = new zVIEW( );
   //:VIEW zqFrameChk BASED ON LOD zqFrame
   zVIEW    zqFrameChk = new zVIEW( );
   //:STRING ( 1000 ) szExternalValue
   String   szExternalValue = null;
   //:STRING ( 32 ) szDateFormat
   String   szDateFormat = null;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;



   //:CASE GetOrSetFlag
   switch( GetOrSetFlag )
   { 
      //:OF   zDERIVED_GET:
      case zDERIVED_GET :

          //:StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, szExternalValue )
         StoreStringInRecord( zqFrame, InternalEntityStructure, InternalAttribStructure, "" );
         break ;

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


//:TRANSFORMATION OPERATION
//:FormatQueryForChart( VIEW zqFrame BASED ON LOD zqFrame,
//:                     VIEW vResultSet,
//:                     STRING ( 20 ) szGraphName )

//:   VIEW mChart BASED ON LOD  mChart
public int 
ozqFrame_FormatQueryForChart( View     zqFrame,
                              View     vResultSet,
                              String   szGraphName )
{
   zVIEW    mChart = new zVIEW( );
   //:STRING ( 50 )  szRootEntityName
   String   szRootEntityName = null;
   //:STRING ( 20 )  szGraphTitleName
   String   szGraphTitleName = null;
   //:VIEW  sHost REGISTERED AS sHost
   zVIEW    sHost = new zVIEW( );
   int      RESULT = 0;
   //:STRING (254) szFile
   String   szFile = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( sHost, "sHost", zqFrame, zLEVEL_TASK );

   //:// Format the Query for the mChart object.

   //:// Set up ordering information as requested.
   //:SetUpOrderingInfo( zqFrame )
   ozqFrame_SetUpOrderingInfo( zqFrame );

   //:// Set up the Root Entity Name, which is different for Pivot Queries.
   //:SET CURSOR FIRST zqFrame.GeneralParameter
   //:           WHERE zqFrame.GeneralParameter.PivotRowEntityFlag = "Y"
   RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "PivotRowEntityFlag", "Y", "" );
   //:IF RESULT >= zCURSOR_SET
   if ( RESULT >= zCURSOR_SET )
   { 
      //:szRootEntityName = "ResultRow"
       {StringBuilder sb_szRootEntityName;
      if ( szRootEntityName == null )
         sb_szRootEntityName = new StringBuilder( 32 );
      else
         sb_szRootEntityName = new StringBuilder( szRootEntityName );
            ZeidonStringCopy( sb_szRootEntityName, 1, 0, "ResultRow", 1, 0, 51 );
      szRootEntityName = sb_szRootEntityName.toString( );}
      //:ELSE
   } 
   else
   { 
      //:SET CURSOR FIRST zqFrame.GeneralParameter
      //:           WHERE zqFrame.GeneralParameter.BreakpointType != ""
      RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "BreakpointType", "" ) == 0 ) )
         { 
            RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
         } 

      } 

      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:szRootEntityName = "SummaryRoot"
          {StringBuilder sb_szRootEntityName;
         if ( szRootEntityName == null )
            sb_szRootEntityName = new StringBuilder( 32 );
         else
            sb_szRootEntityName = new StringBuilder( szRootEntityName );
                  ZeidonStringCopy( sb_szRootEntityName, 1, 0, "SummaryRoot", 1, 0, 51 );
         szRootEntityName = sb_szRootEntityName.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szRootEntityName = zqFrame.zqFrame.QueryObjectRootEntityName
         {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
         StringBuilder sb_szRootEntityName;
         if ( szRootEntityName == null )
            sb_szRootEntityName = new StringBuilder( 32 );
         else
            sb_szRootEntityName = new StringBuilder( szRootEntityName );
                   GetVariableFromAttribute( sb_szRootEntityName, mi_lTempInteger_0, 'S', 51, zqFrame, "zqFrame", "QueryObjectRootEntityName", "", 0 );
         lTempInteger_0 = mi_lTempInteger_0.intValue( );
         szRootEntityName = sb_szRootEntityName.toString( );}
      } 

      //:END
   } 

   //:END
   //:   
   //:// Loop through each Graph Definition and call routine to format it from the current Result Set.
   //:IF zqFrame.GraphDisplay EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( zqFrame, "GraphDisplay" );
   if ( lTempInteger_1 == 0 )
   { 
      //:DELETE ENTITY zqFrame.GraphDisplay  
      RESULT = DeleteEntity( zqFrame, "GraphDisplay", zPOS_NEXT );
   } 

   //:END
   //:CREATE ENTITY zqFrame.GraphDisplay 
   RESULT = CreateEntity( zqFrame, "GraphDisplay", zPOS_AFTER );

   //:// If an mChart object exists, we'll add to it. Otherwise, we'll create a new one.
   //:GET VIEW mChart NAMED "mChart" 
   RESULT = GetViewByName( mChart, "mChart", zqFrame, zLEVEL_TASK );
   //:IF RESULT >= 0 
   if ( RESULT >= 0 )
   { 
      //:SET CURSOR FIRST mChart.Chart WHERE mChart.Chart.Tag = szGraphName
      RESULT = SetCursorFirstEntityByString( mChart, "Chart", "Tag", szGraphName, "" );
      //:IF RESULT >= zCURSOR_SET
      if ( RESULT >= zCURSOR_SET )
      { 
         //:DELETE ENTITY mChart.Chart NONE 
         RESULT = DeleteEntity( mChart, "Chart", zREPOS_NONE );
         //:ELSE
      } 
      else
      { 
         //:SET CURSOR LAST mChart.Chart  
         RESULT = SetCursorLastEntity( mChart, "Chart", "" );
      } 

      //:END
      //:ELSE
   } 
   else
   { 
      //:ACTIVATE mChart EMPTY
      RESULT = ActivateEmptyObjectInstance( mChart, "mChart", zqFrame, zSINGLE );
      //:ActivateEmptyObjectInstance( mChart, "mChart", zqFrame, zMULTIPLE )
      ActivateEmptyObjectInstance( mChart, "mChart", zqFrame, zMULTIPLE );
      //:NAME VIEW mChart "mChart" 
      SetNameForView( mChart, "mChart", null, zLEVEL_TASK );
   } 

   //:END
   //:NAME VIEW mChart "mChartQuery" 
   SetNameForView( mChart, "mChartQuery", null, zLEVEL_TASK );
   //:NAME VIEW zqFrame "zqFrameQuery"
   SetNameForView( zqFrame, "zqFrameQuery", null, zLEVEL_TASK );
   //://FOR EACH zqFrame.GraphDefinition    // Used if multiple graphs are kept in each zqFrame object.

   //:// Format Chart for the single Graph on which we're positioned.
   //:IF zqFrame.GraphDefinition.Type = "Pie Chart"
   if ( CompareAttributeToString( zqFrame, "GraphDefinition", "Type", "Pie Chart" ) == 0 )
   { 
      //:CREATE ENTITY mChart.Chart 
      RESULT = CreateEntity( mChart, "Chart", zPOS_AFTER );
      //:// Put in the tag of the groupbox.
      //:mChart.Chart.Tag = szGraphName
      SetAttributeFromString( mChart, "Chart", "Tag", szGraphName );
      //:mChart.Chart.Type = "Pie"
      SetAttributeFromString( mChart, "Chart", "Type", "Pie" );

      //:CreatePieChartRS( mChart, zqFrame, vResultSet, szGraphName )
      {
       mChart_Object m_mChart_Object = new mChart_Object( mChart );
       m_mChart_Object.omChart_CreatePieChartRS( mChart, zqFrame, vResultSet, szGraphName );
       // m_mChart_Object = null;  // permit gc  (unnecessary)
      }

      //:szGraphTitleName = "GraphTitle1" 
       {StringBuilder sb_szGraphTitleName;
      if ( szGraphTitleName == null )
         sb_szGraphTitleName = new StringBuilder( 32 );
      else
         sb_szGraphTitleName = new StringBuilder( szGraphTitleName );
            ZeidonStringCopy( sb_szGraphTitleName, 1, 0, "GraphTitle1", 1, 0, 21 );
      szGraphTitleName = sb_szGraphTitleName.toString( );}
      //:SetAttributeFromAttribute( zqFrame, "GraphDisplay", szGraphTitleName, zqFrame, "GraphDefinition", "Title" )
      SetAttributeFromAttribute( zqFrame, "GraphDisplay", szGraphTitleName, zqFrame, "GraphDefinition", "Title" );
      //:szFile= sHost.Host.TempDir + "PieChart.txt"
      {StringBuilder sb_szFile;
      if ( szFile == null )
         sb_szFile = new StringBuilder( 32 );
      else
         sb_szFile = new StringBuilder( szFile );
             GetStringFromAttribute( sb_szFile, sHost, "Host", "TempDir" );
      szFile = sb_szFile.toString( );}
       {StringBuilder sb_szFile;
      if ( szFile == null )
         sb_szFile = new StringBuilder( 32 );
      else
         sb_szFile = new StringBuilder( szFile );
            ZeidonStringConcat( sb_szFile, 1, 0, "PieChart.txt", 1, 0, 255 );
      szFile = sb_szFile.toString( );}
      //:WriteChartToFile( mChart,szFile)    // Generate file for debug purposes.
      {
       mChart_Object m_mChart_Object = new mChart_Object( mChart );
       m_mChart_Object.omChart_WriteChartToFile( mChart, szFile );
       // m_mChart_Object = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:IF zqFrame.GraphDefinition.Type = "Basic Bar Chart"
   if ( CompareAttributeToString( zqFrame, "GraphDefinition", "Type", "Basic Bar Chart" ) == 0 )
   { 
      //:CREATE ENTITY mChart.Chart 
      RESULT = CreateEntity( mChart, "Chart", zPOS_AFTER );
      //:// Put in the tag of the groupbox.
      //:mChart.Chart.Tag = szGraphName
      SetAttributeFromString( mChart, "Chart", "Tag", szGraphName );
      //:mChart.Chart.Type = "Bar"
      SetAttributeFromString( mChart, "Chart", "Type", "Bar" );

      //:CreateBarChartRS( mChart, zqFrame, vResultSet, szGraphName )
      {
       mChart_Object m_mChart_Object = new mChart_Object( mChart );
       m_mChart_Object.omChart_CreateBarChartRS( mChart, zqFrame, vResultSet, szGraphName );
       // m_mChart_Object = null;  // permit gc  (unnecessary)
      }

      //:szGraphTitleName = "GraphTitle1"
       {StringBuilder sb_szGraphTitleName;
      if ( szGraphTitleName == null )
         sb_szGraphTitleName = new StringBuilder( 32 );
      else
         sb_szGraphTitleName = new StringBuilder( szGraphTitleName );
            ZeidonStringCopy( sb_szGraphTitleName, 1, 0, "GraphTitle1", 1, 0, 21 );
      szGraphTitleName = sb_szGraphTitleName.toString( );}
      //:SetAttributeFromAttribute( zqFrame, "GraphDisplay", szGraphTitleName, zqFrame, "GraphDefinition", "Title" )
      SetAttributeFromAttribute( zqFrame, "GraphDisplay", szGraphTitleName, zqFrame, "GraphDefinition", "Title" );
      //:szFile= sHost.Host.TempDir +"BarChart.txt"
      {StringBuilder sb_szFile;
      if ( szFile == null )
         sb_szFile = new StringBuilder( 32 );
      else
         sb_szFile = new StringBuilder( szFile );
             GetStringFromAttribute( sb_szFile, sHost, "Host", "TempDir" );
      szFile = sb_szFile.toString( );}
       {StringBuilder sb_szFile;
      if ( szFile == null )
         sb_szFile = new StringBuilder( 32 );
      else
         sb_szFile = new StringBuilder( szFile );
            ZeidonStringConcat( sb_szFile, 1, 0, "BarChart.txt", 1, 0, 255 );
      szFile = sb_szFile.toString( );}
      //:WriteChartToFile( mChart, szFile)    // Generate file for debug purposes.
      {
       mChart_Object m_mChart_Object = new mChart_Object( mChart );
       m_mChart_Object.omChart_WriteChartToFile( mChart, szFile );
       // m_mChart_Object = null;  // permit gc  (unnecessary)
      }
   } 

   //:END

   //:IF zqFrame.GraphDefinition.Type = "2-Level Bar Chart"
   if ( CompareAttributeToString( zqFrame, "GraphDefinition", "Type", "2-Level Bar Chart" ) == 0 )
   { 
      //:CREATE ENTITY mChart.Chart 
      RESULT = CreateEntity( mChart, "Chart", zPOS_AFTER );
      //:// Put in the tag of the groupbox.
      //:mChart.Chart.Tag = szGraphName
      SetAttributeFromString( mChart, "Chart", "Tag", szGraphName );
      //:mChart.Chart.Type = "Bar"
      SetAttributeFromString( mChart, "Chart", "Type", "Bar" );

      //:Create2_LevelBarChart( mChart, zqFrame, vResultSet, szGraphName )
      {
       mChart_Object m_mChart_Object = new mChart_Object( mChart );
       m_mChart_Object.omChart_Create2_LevelBarChart( mChart, zqFrame, vResultSet, szGraphName );
       // m_mChart_Object = null;  // permit gc  (unnecessary)
      }

      //:szGraphTitleName = "GraphTitle1" 
       {StringBuilder sb_szGraphTitleName;
      if ( szGraphTitleName == null )
         sb_szGraphTitleName = new StringBuilder( 32 );
      else
         sb_szGraphTitleName = new StringBuilder( szGraphTitleName );
            ZeidonStringCopy( sb_szGraphTitleName, 1, 0, "GraphTitle1", 1, 0, 21 );
      szGraphTitleName = sb_szGraphTitleName.toString( );}
      //:SetAttributeFromAttribute( zqFrame, "GraphDisplay", szGraphTitleName, zqFrame, "GraphDefinition", "Title" )
      SetAttributeFromAttribute( zqFrame, "GraphDisplay", szGraphTitleName, zqFrame, "GraphDefinition", "Title" );
      //:szFile= sHost.Host.TempDir +"BarChart.txt"
      {StringBuilder sb_szFile;
      if ( szFile == null )
         sb_szFile = new StringBuilder( 32 );
      else
         sb_szFile = new StringBuilder( szFile );
             GetStringFromAttribute( sb_szFile, sHost, "Host", "TempDir" );
      szFile = sb_szFile.toString( );}
       {StringBuilder sb_szFile;
      if ( szFile == null )
         sb_szFile = new StringBuilder( 32 );
      else
         sb_szFile = new StringBuilder( szFile );
            ZeidonStringConcat( sb_szFile, 1, 0, "BarChart.txt", 1, 0, 255 );
      szFile = sb_szFile.toString( );}
      //:WriteChartToFile( mChart, szFile )    // Generate file for debug purposes.
      {
       mChart_Object m_mChart_Object = new mChart_Object( mChart );
       m_mChart_Object.omChart_WriteChartToFile( mChart, szFile );
       // m_mChart_Object = null;  // permit gc  (unnecessary)
      }
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetFrameStructure( VIEW zqFrame BASED ON LOD zqFrame,
//:                   VIEW QueryView )

//:   VIEW zqFrameRoot BASED ON LOD  zqFrame
public int 
ozqFrame_SetFrameStructure( View     zqFrame,
                            View     QueryView )
{
   zVIEW    zqFrameRoot = new zVIEW( );
   //:VIEW QueryViewRecurs
   zVIEW    QueryViewRecurs = new zVIEW( );
   //:STRING ( 50 )  ReportTitle
   String   ReportTitle = null;
   //:STRING ( 32 )  szRootEntityName
   String   szRootEntityName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Go to build the QueryFrameAttribute entries.
   //:RebuildFrameAttr( zqFrame, QueryView )
   ozqFrame_RebuildFrameAttr( zqFrame, QueryView );

   //:// Rebuild the ObjectEntity entries, because an update to the object could make the old ones invalid..
   //:FOR EACH zqFrame.ObjectEntity 
   RESULT = SetCursorFirstEntity( zqFrame, "ObjectEntity", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:DELETE ENTITY zqFrame.ObjectEntity NONE 
      RESULT = DeleteEntity( zqFrame, "ObjectEntity", zREPOS_NONE );
      RESULT = SetCursorNextEntity( zqFrame, "ObjectEntity", "" );
   } 

   //:END
   //:CreateViewFromView( zqFrameRoot, zqFrame )         // We need recursive views for the operation.
   CreateViewFromView( zqFrameRoot, zqFrame );
   //:NAME VIEW zqFrameRoot "zqFrameRoot"
   SetNameForView( zqFrameRoot, "zqFrameRoot", null, zLEVEL_TASK );
   //:CreateViewFromView( QueryViewRecurs, QueryView )
   CreateViewFromView( QueryViewRecurs, QueryView );
   //:szRootEntityName = QueryView.LOD_EntityParent.Name
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szRootEntityName;
   if ( szRootEntityName == null )
      sb_szRootEntityName = new StringBuilder( 32 );
   else
      sb_szRootEntityName = new StringBuilder( szRootEntityName );
       GetVariableFromAttribute( sb_szRootEntityName, mi_lTempInteger_0, 'S', 33, QueryView, "LOD_EntityParent", "Name", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szRootEntityName = sb_szRootEntityName.toString( );}
   //:RebuildObjEntRecurs( zqFrame, zqFrameRoot, QueryViewRecurs, QueryView, szRootEntityName, szRootEntityName )
   ozqFrame_RebuildObjEntRecurs( zqFrame, zqFrameRoot, QueryViewRecurs, QueryView, szRootEntityName, szRootEntityName );
   //:DropView( zqFrameRoot )
   DropView( zqFrameRoot );
   //:DropView( QueryViewRecurs )
   DropView( QueryViewRecurs );
   //:SET CURSOR FIRST zqFrame.ObjectEntity 
   RESULT = SetCursorFirstEntity( zqFrame, "ObjectEntity", "" );

   //:// Make sure that no GeneralParameter exists in the Query that does not exist in the Query Frame.
   //:FOR EACH zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:SET CURSOR FIRST QueryView.QueryFrameAttribute
      //:     WHERE QueryView.QueryFrameAttribute.EntityName    = zqFrame.GeneralParameter.EntityName
      //:       AND QueryView.QueryFrameAttribute.AttributeName = zqFrame.GeneralParameter.AttributeName
      RESULT = SetCursorFirstEntity( QueryView, "QueryFrameAttribute", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( QueryView, "QueryFrameAttribute", "EntityName", zqFrame, "GeneralParameter", "EntityName" ) != 0 ||
                 CompareAttributeToAttribute( QueryView, "QueryFrameAttribute", "AttributeName", zqFrame, "GeneralParameter", "AttributeName" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( QueryView, "QueryFrameAttribute", "" );
         } 

      } 

      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:DELETE ENTITY zqFrame.GeneralParameter NONE
         RESULT = DeleteEntity( zqFrame, "GeneralParameter", zREPOS_NONE );
      } 

      RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );

   //:// Initialize data for QueryUpdate.
   //:InitQueryUpdate( zqFrame, QueryView )
   ozqFrame_InitQueryUpdate( zqFrame, QueryView );
   //:SET CURSOR FIRST QueryView.LOD     // Make sure root is visible.
   RESULT = SetCursorFirstEntity( QueryView, "LOD", "" );

   //:// Make sure that ScopingSourceQueryEntity entries exist for including Scoping Entities.
   //:/*IF zqFrame.ScopingSourceQueryEntity DOES NOT EXIST
   //:   FOR EACH QueryView.LOD_Entity
   //:      CREATE ENTITY zqFrame.ScopingSourceQueryEntity 
   //:      zqFrame.ScopingSourceQueryEntity.EntityName = QueryView.LOD_Entity.Name
   //:   END
   //:END

   //:// Also include appropriate ScopingQueryEntity entities based on GeneralParameter.ScopingEntityName.
   //:FOR EACH zqFrame.GeneralParameter 
   //:   IF zqFrame.GeneralParameter.ScopingEntityName != ""
   //:      IF zqFrame.ScopingQueryEntity DOES NOT EXIST
   //:         SET CURSOR FIRST zqFrame.ScopingSourceQueryEntity 
   //:                    WHERE zqFrame.ScopingSourceQueryEntity.EntityName = zqFrame.GeneralParameter.ScopingEntityName 
   //:         INCLUDE zqFrame.ScopingQueryEntity FROM zqFrame.ScopingSourceQueryEntity 
   //:      END
   //:   END
   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter 
   //:SET CURSOR FIRST zqFrame.ScopingSourceQueryEntity*/

   //:BuildConditionValues( zqFrame )
   ozqFrame_BuildConditionValues( zqFrame );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetTreeParentRecurs( VIEW zqFrameRoot   BASED ON LOD zqFrame,
//:                     VIEW zqFrameRecurs BASED ON LOD zqFrame,
//:                     STRING ( 32 ) szParentEntityName )
//:                     
//:   STRING ( 32 ) szChildParentEntityName
public int 
ozqFrame_SetTreeParentRecurs( View     zqFrameRoot,
                              View     zqFrameRecurs,
                              String   szParentEntityName )
{
   String   szChildParentEntityName = null;
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   String   szTempString_2 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;


   //:// Process setting DisplayTreeEntity Parent Entity Name.
   //:SET CURSOR FIRST zqFrameRoot.DisplayTreeEntity 
   //:           WHERE zqFrameRoot.DisplayTreeEntity.EntityName = zqFrameRecurs.ParentEntity.EntityName 
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, zqFrameRecurs, "ParentEntity", "EntityName" );
   szTempString_0 = sb_szTempString_0.toString( );}
   RESULT = SetCursorFirstEntityByString( zqFrameRoot, "DisplayTreeEntity", "EntityName", szTempString_0, "" );
   //:zqFrameRoot.DisplayTreeEntity.ParentEntityName = szParentEntityName
   SetAttributeFromString( zqFrameRoot, "DisplayTreeEntity", "ParentEntityName", szParentEntityName );
   //:// Add (0-1) or (0-m) suffix depending on cardinality.
   //:IF zqFrameRecurs.ParentEntity.MaxCardinality = 1
   if ( CompareAttributeToInteger( zqFrameRecurs, "ParentEntity", "MaxCardinality", 1 ) == 0 )
   { 
      //:zqFrameRoot.DisplayTreeEntity.Name = zqFrameRoot.DisplayTreeEntity.Name + " (0-1)"
      {StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
             GetStringFromAttribute( sb_szTempString_1, zqFrameRoot, "DisplayTreeEntity", "Name" );
      szTempString_1 = sb_szTempString_1.toString( );}
       {StringBuilder sb_szTempString_1;
      if ( szTempString_1 == null )
         sb_szTempString_1 = new StringBuilder( 32 );
      else
         sb_szTempString_1 = new StringBuilder( szTempString_1 );
            ZeidonStringConcat( sb_szTempString_1, 1, 0, " (0-1)", 1, 0, 255 );
      szTempString_1 = sb_szTempString_1.toString( );}
      SetAttributeFromString( zqFrameRoot, "DisplayTreeEntity", "Name", szTempString_1 );
      //:ELSE
   } 
   else
   { 
      //:zqFrameRoot.DisplayTreeEntity.Name = zqFrameRoot.DisplayTreeEntity.Name + " (0-m)"
      {StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
             GetStringFromAttribute( sb_szTempString_2, zqFrameRoot, "DisplayTreeEntity", "Name" );
      szTempString_2 = sb_szTempString_2.toString( );}
       {StringBuilder sb_szTempString_2;
      if ( szTempString_2 == null )
         sb_szTempString_2 = new StringBuilder( 32 );
      else
         sb_szTempString_2 = new StringBuilder( szTempString_2 );
            ZeidonStringConcat( sb_szTempString_2, 1, 0, " (0-m)", 1, 0, 255 );
      szTempString_2 = sb_szTempString_2.toString( );}
      SetAttributeFromString( zqFrameRoot, "DisplayTreeEntity", "Name", szTempString_2 );
   } 

   //:END

   //:// Process each child.
   //:IF zqFrameRecurs.ChildEntity EXISTS
   lTempInteger_0 = CheckExistenceOfEntity( zqFrameRecurs, "ChildEntity" );
   if ( lTempInteger_0 == 0 )
   { 
      //:// All children have the same parent.
      //:szChildParentEntityName = zqFrameRecurs.ParentEntity.EntityName 
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szChildParentEntityName;
      if ( szChildParentEntityName == null )
         sb_szChildParentEntityName = new StringBuilder( 32 );
      else
         sb_szChildParentEntityName = new StringBuilder( szChildParentEntityName );
             GetVariableFromAttribute( sb_szChildParentEntityName, mi_lTempInteger_1, 'S', 33, zqFrameRecurs, "ParentEntity", "EntityName", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szChildParentEntityName = sb_szChildParentEntityName.toString( );}
      //:SetViewToSubobject( zqFrameRecurs, "ChildEntity"  )
      SetViewToSubobject( zqFrameRecurs, "ChildEntity" );
      //:FOR EACH zqFrameRecurs.ParentEntity 
      RESULT = SetCursorFirstEntity( zqFrameRecurs, "ParentEntity", "" );
      while ( RESULT > zCURSOR_UNCHANGED )
      { 
         //:SetTreeParentRecurs( zqFrameRoot, zqFrameRecurs, szChildParentEntityName )
         ozqFrame_SetTreeParentRecurs( zqFrameRoot, zqFrameRecurs, szChildParentEntityName );
         RESULT = SetCursorNextEntity( zqFrameRecurs, "ParentEntity", "" );
      } 

      //:END
      //:ResetViewFromSubobject( zqFrameRecurs )
      ResetViewFromSubobject( zqFrameRecurs );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:CheckAddLanguageText( VIEW zqFrame BASED ON LOD zqFrame,
//:                      STRING ( 32 ) OriginalTextValue )

//:   VIEW zqLang    BASED ON LOD  zqLang
public int 
ozqFrame_CheckAddLanguageText( View     zqFrame,
                               String   OriginalTextValue )
{
   zVIEW    zqLang = new zVIEW( );
   //:VIEW zqLangLST BASED ON LOD  zqLang
   zVIEW    zqLangLST = new zVIEW( );
   int      RESULT = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   zVIEW    vTempViewVar_1 = new zVIEW( );


   //:// Check to see if the OriginalTextValue is already in zqLangLST. If it isn't, add it.

   //:// Temporary delete current entries.
   //:/*GET VIEW zqLangLST NAMED "zqLangLST"
   //:IF RESULT < 0
   //:   ACTIVATE zqLangLST RootOnlyMultiple WHERE zqLangLST.OriginalDialogText.IsAdvancedQuery = "Y"
   //:   NAME VIEW zqLangLST "zqLangLST"
   //:   FOR EACH zqLangLST.OriginalDialogText 
   //:      ACTIVATE zqLang WHERE zqLang.OriginalDialogText.ID = zqLangLST.OriginalDialogText.ID
   //:      NAME VIEW zqLang "zqLangDelete"
   //:      DELETE ENTITY zqLang.OriginalDialogText  
   //:      COMMIT zqLang
   //:      DropObjectInstance ( zqLang )
   //:   END 
   //:END*/

   //:GET VIEW zqLangLST NAMED "zqLangLST"
   RESULT = GetViewByName( zqLangLST, "zqLangLST", zqFrame, zLEVEL_TASK );
   //:IF RESULT < 0
   if ( RESULT < 0 )
   { 
      //:ACTIVATE zqLangLST RootOnlyMultiple WHERE zqLangLST.OriginalDialogText.IsAdvancedQuery = "Y"
      ozqFrame_fnLocalBuildQual_0( zqFrame, vTempViewVar_0 );
      RESULT = ActivateObjectInstance( zqLangLST, "zqLang", zqFrame, vTempViewVar_0, zACTIVATE_ROOTONLY_MULTIPLE );
      DropView( vTempViewVar_0 );
      //:NAME VIEW zqLangLST "zqLangLST"
      SetNameForView( zqLangLST, "zqLangLST", null, zLEVEL_TASK );
      //:OrderEntityForView( zqLangLST, "OriginalDialogText", "UniqueTextValue A" )
      OrderEntityForView( zqLangLST, "OriginalDialogText", "UniqueTextValue A" );
      //:SET CURSOR FIRST zqLangLST.OriginalDialogText  
      RESULT = SetCursorFirstEntity( zqLangLST, "OriginalDialogText", "" );
   } 

   //:END

   //:SET CURSOR FIRST zqLangLST.OriginalDialogText WHERE zqLangLST.OriginalDialogText.UniqueTextValue = OriginalTextValue
   RESULT = SetCursorFirstEntityByString( zqLangLST, "OriginalDialogText", "UniqueTextValue", OriginalTextValue, "" );
   //:IF RESULT < zCURSOR_SET
   if ( RESULT < zCURSOR_SET )
   { 
      //:DropObjectInstance( zqLangLST )
      DropObjectInstance( zqLangLST );
      //:ACTIVATE zqLang EMPTY 
      RESULT = ActivateEmptyObjectInstance( zqLang, "zqLang", zqFrame, zSINGLE );
      //:NAME VIEW zqLang "zqLang" 
      SetNameForView( zqLang, "zqLang", null, zLEVEL_TASK );
      //:CREATE ENTITY zqLang.OriginalDialogText 
      RESULT = CreateEntity( zqLang, "OriginalDialogText", zPOS_AFTER );
      //:zqLang.OriginalDialogText.UniqueTextValue = OriginalTextValue
      SetAttributeFromString( zqLang, "OriginalDialogText", "UniqueTextValue", OriginalTextValue );
      //:zqLang.OriginalDialogText.IsAdvancedQuery   = "Y" 
      SetAttributeFromString( zqLang, "OriginalDialogText", "IsAdvancedQuery", "Y" );
      //:COMMIT zqLang
      RESULT = CommitObjectInstance( zqLang );
      //:DropObjectInstance( zqLang )
      DropObjectInstance( zqLang );
      //:ACTIVATE zqLangLST RootOnlyMultiple WHERE zqLangLST.OriginalDialogText.IsAdvancedQuery = "Y"
      ozqFrame_fnLocalBuildQual_1( zqFrame, vTempViewVar_1 );
      RESULT = ActivateObjectInstance( zqLangLST, "zqLang", zqFrame, vTempViewVar_1, zACTIVATE_ROOTONLY_MULTIPLE );
      DropView( vTempViewVar_1 );
      //:NAME VIEW zqLangLST "zqLangLST"
      SetNameForView( zqLangLST, "zqLangLST", null, zLEVEL_TASK );
      //:OrderEntityForView( zqLangLST, "OriginalDialogText", "UniqueTextValue A" )
      OrderEntityForView( zqLangLST, "OriginalDialogText", "UniqueTextValue A" );
      //:SET CURSOR FIRST zqLangLST.OriginalDialogText 
      RESULT = SetCursorFirstEntity( zqLangLST, "OriginalDialogText", "" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:AddColumnAttribute( VIEW zqFrame BASED ON LOD zqFrame,
//:                    STRING ( 500 ) szDisplayValue,
//:                    STRING ( 50 )  szEntityName,
//:                    STRING ( 50 )  szAttributeName )

//:   STRING ( 50 ) szColumnAttributeName
public int 
ozqFrame_AddColumnAttribute( View     zqFrame,
                             String   szDisplayValue,
                             String   szEntityName,
                             String   szAttributeName )
{
   String   szColumnAttributeName = null;
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:// Set the ResultSetDisplayRow attribute value based on the Entity/Attribute Names and the GeneralParameter.
   //:SET CURSOR FIRST zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.EntityName    = szEntityName AND
   //:                                                zqFrame.GeneralParameter.AttributeName = szAttributeName
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   if ( RESULT > zCURSOR_UNCHANGED )
   { 
      while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToString( zqFrame, "GeneralParameter", "EntityName", szEntityName ) != 0 || CompareAttributeToString( zqFrame, "GeneralParameter", "AttributeName", szAttributeName ) != 0 ) )
      { 
         RESULT = SetCursorNextEntity( zqFrame, "GeneralParameter", "" );
      } 

   } 

   //:szColumnAttributeName = zqFrame.GeneralParameter.wDisplayValueColumnName 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szColumnAttributeName;
   if ( szColumnAttributeName == null )
      sb_szColumnAttributeName = new StringBuilder( 32 );
   else
      sb_szColumnAttributeName = new StringBuilder( szColumnAttributeName );
       GetVariableFromAttribute( sb_szColumnAttributeName, mi_lTempInteger_0, 'S', 51, zqFrame, "GeneralParameter", "wDisplayValueColumnName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szColumnAttributeName = sb_szColumnAttributeName.toString( );}
   //:// Skip if the name is null, which it would be if we've gone over 6 attributes to display.
   //:IF szColumnAttributeName != ""    
   if ( ZeidonStringCompare( szColumnAttributeName, 1, 0, "", 1, 0, 51 ) != 0 )
   { 
      //:SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szColumnAttributeName, szDisplayValue )
      SetAttributeFromString( zqFrame, "ResultSetDisplayRow", szColumnAttributeName, szDisplayValue );
      //:zqFrame.ResultSetDisplayRow.wMappedFlag = "Y"
      SetAttributeFromString( zqFrame, "ResultSetDisplayRow", "wMappedFlag", "Y" );
   } 

   //:END
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:BuildSummaryDetail( VIEW zqFrame BASED ON LOD zqFrame,
//:                    VIEW vResultSet,
//:                    VIEW vLOD )

//:   VIEW wXferO   REGISTERED AS wXferO
public int 
ozqFrame_BuildSummaryDetail( View     zqFrame,
                             View     vResultSet,
                             View     vLOD )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW sHost    REGISTERED AS sHost
   zVIEW    sHost = new zVIEW( );
   //:VIEW mUser    REGISTERED AS mUser
   zVIEW    mUser = new zVIEW( );
   //:VIEW zqFrameT BASED ON LOD  zqFrame
   zVIEW    zqFrameT = new zVIEW( );
   //:STRING ( 400 ) szFileName
   String   szFileName = null;
   //:STRING ( 1 )   szFoundFlag
   String   szFoundFlag = null;
   //:STRING ( 50 )  szAttributeName
   String   szAttributeName = null;
   //:STRING ( 50 )  szColumnTitle
   String   szColumnTitle = null;
   //:STRING ( 3 )   szCount
   String   szCount = null;
   //:INTEGER nRC
   int      nRC = 0;
   //:INTEGER Count
   int      Count = 0;
   String   szTempString_0 = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;

   RESULT = GetViewByName( wXferO, "wXferO", zqFrame, zLEVEL_TASK );
   RESULT = GetViewByName( sHost, "sHost", zqFrame, zLEVEL_TASK );
   RESULT = GetViewByName( mUser, "mUser", zqFrame, zLEVEL_TASK );

   //:// Format the Query Detail Mapping in the ResultSetDisplay subobject.

   //:// Set the Column Mapping Names, which are sequential according to the display order of the General Parameters.
   //:Count = 0
   Count = 0;
   //:FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.ListDisplayFlag = "Y"
   RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:IF Count <= 6 
      if ( Count <= 6 )
      { 
         //:szCount = Count
          {StringBuilder sb_szCount;
         if ( szCount == null )
            sb_szCount = new StringBuilder( 32 );
         else
            sb_szCount = new StringBuilder( szCount );
                  ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 3, Count, (double) 0.0, "I" );
         szCount = sb_szCount.toString( );}
         //:szAttributeName = "ColumnValue" + szCount
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue", 1, 0, 51 );
         szAttributeName = sb_szAttributeName.toString( );}
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringConcat( sb_szAttributeName, 1, 0, szCount, 1, 0, 51 );
         szAttributeName = sb_szAttributeName.toString( );}
         //:zqFrame.GeneralParameter.wDisplayValueColumnName = szAttributeName
         SetAttributeFromString( zqFrame, "GeneralParameter", "wDisplayValueColumnName", szAttributeName );
      } 

      RESULT = SetCursorNextEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter  
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );

   //:// We will use a copy of zqFrame because the Format process is somehow currently dropping the ParentEntity subobject.
   //:zqFrame.zqFrame.wCurrentDisplayFormat = "R"
   SetAttributeFromString( zqFrame, "zqFrame", "wCurrentDisplayFormat", "R" );
   //:ActivateOI_FromOI( zqFrameT, zqFrame, zSINGLE )
   ActivateOI_FromOI( zqFrameT, zqFrame, zSINGLE );
   //:NAME VIEW zqFrameT "zqFrameT"
   SetNameForView( zqFrameT, "zqFrameT", null, zLEVEL_TASK );
   //:SetUpOrderingInfo( zqFrameT )     // Set up ordering information as requested
   ozqFrame_SetUpOrderingInfo( zqFrameT );
   //:// For FileDownLoad, the FileName will be built on UserName.
   //:szFileName = sHost.Host.TempDir + "QueryReport" + mUser.User.UserName + ".csv"
   {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
       GetStringFromAttribute( sb_szFileName, sHost, "Host", "TempDir" );
   szFileName = sb_szFileName.toString( );}
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
      ZeidonStringConcat( sb_szFileName, 1, 0, "QueryReport", 1, 0, 401 );
   szFileName = sb_szFileName.toString( );}
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetVariableFromAttribute( sb_szTempString_0, mi_lTempInteger_0, 'S', 51, mUser, "User", "UserName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szTempString_0 = sb_szTempString_0.toString( );}
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
      ZeidonStringConcat( sb_szFileName, 1, 0, szTempString_0, 1, 0, 401 );
   szFileName = sb_szFileName.toString( );}
    {StringBuilder sb_szFileName;
   if ( szFileName == null )
      sb_szFileName = new StringBuilder( 32 );
   else
      sb_szFileName = new StringBuilder( szFileName );
      ZeidonStringConcat( sb_szFileName, 1, 0, ".csv", 1, 0, 401 );
   szFileName = sb_szFileName.toString( );}
   //:wXferO.QueryValues.OutputFileName = szFileName
   SetAttributeFromString( wXferO, "QueryValues", "OutputFileName", szFileName );
   //:TraceLineS( "### CSV File: ", szFileName )
   TraceLineS( "### CSV File: ", szFileName );
   //:SET CURSOR FIRST zqFrameT.ParentEntity   // We need to make sure position is on ParentEntity.
   RESULT = SetCursorFirstEntity( zqFrameT, "ParentEntity", "" );
   //:nRC = FormatCSV_FrmResultSet( zqFrameT,
   //:                              vLOD,
   //:                              vResultSet,
   //:                              "",              // This is for format Sparse or Full and we will assume Sparse.
   //:                              szFileName )
   nRC = ozqFrame_FormatCSV_FrmResultSet( zqFrameT, vLOD, vResultSet, "", szFileName );
   //:// Copy any mapped ResultSetDisplayRow entries over to the display zqFrame object.
   //:IF zqFrame.ResultSetDisplay EXISTS
   lTempInteger_1 = CheckExistenceOfEntity( zqFrame, "ResultSetDisplay" );
   if ( lTempInteger_1 == 0 )
   { 
      //:DELETE ENTITY zqFrame.ResultSetDisplay  
      RESULT = DeleteEntity( zqFrame, "ResultSetDisplay", zPOS_NEXT );
   } 

   //:END
   //:CREATE ENTITY zqFrame.ResultSetDisplay  
   RESULT = CreateEntity( zqFrame, "ResultSetDisplay", zPOS_AFTER );
   //:FOR EACH zqFrameT.ResultSetDisplayRow WHERE zqFrameT.ResultSetDisplayRow.wMappedFlag = "Y"
   RESULT = SetCursorFirstEntityByString( zqFrameT, "ResultSetDisplayRow", "wMappedFlag", "Y", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:CREATE ENTITY zqFrame.ResultSetDisplayRow 
      RESULT = CreateEntity( zqFrame, "ResultSetDisplayRow", zPOS_AFTER );
      //:SetMatchingAttributesByName( zqFrame, "ResultSetDisplayRow", zqFrameT, "ResultSetDisplayRow", zSET_ALL ) 
      SetMatchingAttributesByName( zqFrame, "ResultSetDisplayRow", zqFrameT, "ResultSetDisplayRow", zSET_ALL );
      RESULT = SetCursorNextEntityByString( zqFrameT, "ResultSetDisplayRow", "wMappedFlag", "Y", "" );
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.ResultSetDisplayRow
   RESULT = SetCursorFirstEntity( zqFrame, "ResultSetDisplayRow", "" );
   //:DropObjectInstance( zqFrameT )
   DropObjectInstance( zqFrameT );

   //:// Create the Column Heading values.
   //:Count = 0
   Count = 0;
   //:FOR EACH zqFrame.GeneralParameter WHERE zqFrame.GeneralParameter.ListDisplayFlag = "Y"
   RESULT = SetCursorFirstEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:Count = Count + 1
      Count = Count + 1;
      //:IF Count <= 6
      if ( Count <= 6 )
      { 
         //:szCount = Count
          {StringBuilder sb_szCount;
         if ( szCount == null )
            sb_szCount = new StringBuilder( 32 );
         else
            sb_szCount = new StringBuilder( szCount );
                  ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 3, Count, (double) 0.0, "I" );
         szCount = sb_szCount.toString( );}
         //:szAttributeName = "ColumnTitle" + szCount
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnTitle", 1, 0, 51 );
         szAttributeName = sb_szAttributeName.toString( );}
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringConcat( sb_szAttributeName, 1, 0, szCount, 1, 0, 51 );
         szAttributeName = sb_szAttributeName.toString( );}
         //:szColumnTitle = zqFrame.GeneralParameter.Title 
         {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
         StringBuilder sb_szColumnTitle;
         if ( szColumnTitle == null )
            sb_szColumnTitle = new StringBuilder( 32 );
         else
            sb_szColumnTitle = new StringBuilder( szColumnTitle );
                   GetVariableFromAttribute( sb_szColumnTitle, mi_lTempInteger_2, 'S', 51, zqFrame, "GeneralParameter", "Title", "", 0 );
         lTempInteger_2 = mi_lTempInteger_2.intValue( );
         szColumnTitle = sb_szColumnTitle.toString( );}
         //:SetAttributeFromString( zqFrame, "ResultSetDisplay", szAttributeName, szColumnTitle )
         SetAttributeFromString( zqFrame, "ResultSetDisplay", szAttributeName, szColumnTitle );
      } 

      RESULT = SetCursorNextEntityByString( zqFrame, "GeneralParameter", "ListDisplayFlag", "Y", "" );
      //:END
   } 

   //:END
   //:SET CURSOR FIRST zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:FormatRS_DisplayToCSV( VIEW zqFrame BASED ON LOD zqFrame,
//:                       STRING ( 400 ) szOutputFileName )

//:   INTEGER FilePointer
public int 
ozqFrame_FormatRS_DisplayToCSV( View     zqFrame,
                                String   szOutputFileName )
{
   int      FilePointer = 0;
   //:INTEGER Count
   int      Count = 0;
   //:STRING ( 1000 ) szOutputLine
   String   szOutputLine = null;
   //:STRING ( 20 )   szCount
   String   szCount = null;
   //:STRING ( 20 )   szAttributeName
   String   szAttributeName = null;
   //:STRING ( 400 )  szDisplayValue
   String   szDisplayValue = null;
   //:STRING ( 200 )  szMsg
   String   szMsg = null;
   //:STRING ( 200 )  szLangConv
   String   szLangConv = null;
   int      lTempInteger_0 = 0;
   int      RESULT = 0;


   //:// Format the data defined in the zqFrame.ResultSetDisplay subobject to a csv file.

   //:// Open File
   //:FilePointer = SysOpenFile( zqFrame, szOutputFileName, COREFILE_CREATE )
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    FilePointer = m_zGLOBAL6_Operation.SysOpenFile( zqFrame, szOutputFileName, COREFILE_CREATE );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   //:IF FilePointer < 0
   if ( FilePointer < 0 )
   { 
      //:GetTextConv(szLangConv, zqFrame, "Invalid Directory for writing CSV Query File: ","")
      {
       ZGLOBALE_Operation m_ZGLOBALE_Operation = new ZGLOBALE_Operation( zqFrame );
       {StringBuilder sb_szLangConv;
      if ( szLangConv == null )
         sb_szLangConv = new StringBuilder( 32 );
      else
         sb_szLangConv = new StringBuilder( szLangConv );
             m_ZGLOBALE_Operation.GetTextConv( sb_szLangConv, zqFrame, "Invalid Directory for writing CSV Query File: ", "" );
      szLangConv = sb_szLangConv.toString( );}
       // m_ZGLOBALE_Operation = null;  // permit gc  (unnecessary)
      }
      //:szMsg = szLangConv + szOutputFileName
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringCopy( sb_szMsg, 1, 0, szLangConv, 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
       {StringBuilder sb_szMsg;
      if ( szMsg == null )
         sb_szMsg = new StringBuilder( 32 );
      else
         sb_szMsg = new StringBuilder( szMsg );
            ZeidonStringConcat( sb_szMsg, 1, 0, szOutputFileName, 1, 0, 201 );
      szMsg = sb_szMsg.toString( );}
      //:MessageSend( zqFrame, "", "Query", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( zqFrame, "", "Query", szMsg, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END

   //:// Build Title Line.
   //:szDisplayValue = zqFrame.zqFrame.ReportTitle  
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szDisplayValue;
   if ( szDisplayValue == null )
      sb_szDisplayValue = new StringBuilder( 32 );
   else
      sb_szDisplayValue = new StringBuilder( szDisplayValue );
       GetVariableFromAttribute( sb_szDisplayValue, mi_lTempInteger_0, 'S', 401, zqFrame, "zqFrame", "ReportTitle", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szDisplayValue = sb_szDisplayValue.toString( );}
   //:szOutputLine  = QUOTES + szDisplayValue + QUOTES
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringCopy( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringConcat( sb_szOutputLine, 1, 0, szDisplayValue, 1, 0, 1001 );
   szOutputLine = sb_szOutputLine.toString( );}
    {StringBuilder sb_szOutputLine;
   if ( szOutputLine == null )
      sb_szOutputLine = new StringBuilder( 32 );
   else
      sb_szOutputLine = new StringBuilder( szOutputLine );
      ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
   szOutputLine = sb_szOutputLine.toString( );}
   //:SysWriteLine( zqFrame, FilePointer, szOutputLine )
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, szOutputLine );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Build Column Headings from each non-null zqFrame.ResultSetDisplay.ColumnTitlexx entry.
   //:Count = 1
   Count = 1;
   //:LOOP WHILE Count <= 6
   while ( Count <= 6 )
   { 
      //:szCount = Count
       {StringBuilder sb_szCount;
      if ( szCount == null )
         sb_szCount = new StringBuilder( 32 );
      else
         sb_szCount = new StringBuilder( szCount );
            ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 20, Count, (double) 0.0, "I" );
      szCount = sb_szCount.toString( );}
      //:szAttributeName = "ColumnTitle" + szCount
       {StringBuilder sb_szAttributeName;
      if ( szAttributeName == null )
         sb_szAttributeName = new StringBuilder( 32 );
      else
         sb_szAttributeName = new StringBuilder( szAttributeName );
            ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnTitle", 1, 0, 21 );
      szAttributeName = sb_szAttributeName.toString( );}
       {StringBuilder sb_szAttributeName;
      if ( szAttributeName == null )
         sb_szAttributeName = new StringBuilder( 32 );
      else
         sb_szAttributeName = new StringBuilder( szAttributeName );
            ZeidonStringConcat( sb_szAttributeName, 1, 0, szCount, 1, 0, 21 );
      szAttributeName = sb_szAttributeName.toString( );}
      //:GetStringFromAttribute( szDisplayValue, zqFrame, "ResultSetDisplay", szAttributeName )
      {StringBuilder sb_szDisplayValue;
      if ( szDisplayValue == null )
         sb_szDisplayValue = new StringBuilder( 32 );
      else
         sb_szDisplayValue = new StringBuilder( szDisplayValue );
             GetStringFromAttribute( sb_szDisplayValue, zqFrame, "ResultSetDisplay", szAttributeName );
      szDisplayValue = sb_szDisplayValue.toString( );}
      //:IF szDisplayValue != ""
      if ( ZeidonStringCompare( szDisplayValue, 1, 0, "", 1, 0, 401 ) != 0 )
      { 
         //:// There is a value.
         //:IF Count = 1
         if ( Count == 1 )
         { 
            //:szOutputLine  = QUOTES + szDisplayValue + QUOTES
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringCopy( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringConcat( sb_szOutputLine, 1, 0, szDisplayValue, 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
            //:ELSE
         } 
         else
         { 
            //:szOutputLine  = szOutputLine + "," + QUOTES + szDisplayValue + QUOTES
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringConcat( sb_szOutputLine, 1, 0, ",", 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringConcat( sb_szOutputLine, 1, 0, szDisplayValue, 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
             {StringBuilder sb_szOutputLine;
            if ( szOutputLine == null )
               sb_szOutputLine = new StringBuilder( 32 );
            else
               sb_szOutputLine = new StringBuilder( szOutputLine );
                        ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
            szOutputLine = sb_szOutputLine.toString( );}
         } 

         //:END
      } 

      //:END
      //:Count = Count + 1
      Count = Count + 1;
   } 

   //:END
   //:SysWriteLine( zqFrame, FilePointer, szOutputLine )    // Write of column headers line.
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, szOutputLine );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }

   //:// Build zqFrame.ResultSetDisplayRow entries.
   //:FOR EACH zqFrame.ResultSetDisplayRow 
   RESULT = SetCursorFirstEntity( zqFrame, "ResultSetDisplayRow", "" );
   while ( RESULT > zCURSOR_UNCHANGED )
   { 
      //:// Build display values from each non-null zqFrame.ResultSetDisplayRow.ColumnValue1xx entry.
      //:Count = 1
      Count = 1;
      //:LOOP WHILE Count <= 6
      while ( Count <= 6 )
      { 
         //:szCount = Count
          {StringBuilder sb_szCount;
         if ( szCount == null )
            sb_szCount = new StringBuilder( 32 );
         else
            sb_szCount = new StringBuilder( szCount );
                  ZeidonStringConvertFromNumber( sb_szCount, 1, 0, 20, Count, (double) 0.0, "I" );
         szCount = sb_szCount.toString( );}
         //:szAttributeName = "ColumnValue" + szCount
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringCopy( sb_szAttributeName, 1, 0, "ColumnValue", 1, 0, 21 );
         szAttributeName = sb_szAttributeName.toString( );}
          {StringBuilder sb_szAttributeName;
         if ( szAttributeName == null )
            sb_szAttributeName = new StringBuilder( 32 );
         else
            sb_szAttributeName = new StringBuilder( szAttributeName );
                  ZeidonStringConcat( sb_szAttributeName, 1, 0, szCount, 1, 0, 21 );
         szAttributeName = sb_szAttributeName.toString( );}
         //:GetStringFromAttribute( szDisplayValue, zqFrame, "ResultSetDisplayRow", szAttributeName )
         {StringBuilder sb_szDisplayValue;
         if ( szDisplayValue == null )
            sb_szDisplayValue = new StringBuilder( 32 );
         else
            sb_szDisplayValue = new StringBuilder( szDisplayValue );
                   GetStringFromAttribute( sb_szDisplayValue, zqFrame, "ResultSetDisplayRow", szAttributeName );
         szDisplayValue = sb_szDisplayValue.toString( );}
         //:IF szDisplayValue != ""
         if ( ZeidonStringCompare( szDisplayValue, 1, 0, "", 1, 0, 401 ) != 0 )
         { 
            //:IF Count = 1
            if ( Count == 1 )
            { 
               //:szOutputLine  = QUOTES + szDisplayValue + QUOTES
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringCopy( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, szDisplayValue, 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szOutputLine  = szOutputLine + "," + QUOTES + szDisplayValue + QUOTES
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, ",", 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, szDisplayValue, 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, QUOTES, 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
            } 

            //:END
            //:ELSE 
         } 
         else
         { 
            //:IF Count = 1
            if ( Count == 1 )
            { 
               //:szOutputLine  = "" 
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringCopy( sb_szOutputLine, 1, 0, "", 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
               //:ELSE
            } 
            else
            { 
               //:szOutputLine  = szOutputLine + "," 
                {StringBuilder sb_szOutputLine;
               if ( szOutputLine == null )
                  sb_szOutputLine = new StringBuilder( 32 );
               else
                  sb_szOutputLine = new StringBuilder( szOutputLine );
                              ZeidonStringConcat( sb_szOutputLine, 1, 0, ",", 1, 0, 1001 );
               szOutputLine = sb_szOutputLine.toString( );}
            } 

            //:END
         } 

         //:END
         //:Count = Count + 1
         Count = Count + 1;
      } 

      //:END
      //:SysWriteLine( zqFrame, FilePointer, szOutputLine )    // Write of column headers line.
      try
      {
          {
       zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
       m_zGLOBAL6_Operation.SysWriteLine( zqFrame, FilePointer, szOutputLine );
       // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
      };
      }
      catch ( Exception e )
      {
         throw ZeidonException.wrapException( e );
      }
      RESULT = SetCursorNextEntity( zqFrame, "ResultSetDisplayRow", "" );
   } 

   //:END

   //:// Close File.
   //:SysCloseFile( zqFrame, FilePointer, 0 )
   try
   {
       {
    zGLOBAL6_Operation m_zGLOBAL6_Operation = new zGLOBAL6_Operation( zqFrame );
    m_zGLOBAL6_Operation.SysCloseFile( zqFrame, FilePointer, 0 );
    // m_zGLOBAL6_Operation = null;  // permit gc  (unnecessary)
   };
   }
   catch ( Exception e )
   {
      throw ZeidonException.wrapException( e );
   }
   return( 0 );
// END
} 


//:TRANSFORMATION OPERATION
//:SetUpForNewQuery( VIEW zqFrame BASED ON LOD zqFrame,
//:                  VIEW AnyView )

//:   VIEW wXferO    REGISTERED AS wXferO
public int 
ozqFrame_SetUpForNewQuery( zVIEW    zqFrame,
                           View     AnyView )
{
   zVIEW    wXferO = new zVIEW( );
   int      RESULT = 0;
   //:VIEW zqSQuery  BASED ON LOD  zqSQuery
   zVIEW    zqSQuery = new zVIEW( );
   //:VIEW QueryView
   zVIEW    QueryView = new zVIEW( );
   //:VIEW vTZCMLPLO 
   zVIEW    vTZCMLPLO = new zVIEW( );
   //:INTEGER nRC
   int      nRC = 0;
   String   szTempString_0 = null;

   RESULT = GetViewByName( wXferO, "wXferO", AnyView, zLEVEL_TASK );

   //:GET VIEW QueryView NAMED "QueryView"
   RESULT = GetViewByName( QueryView, "QueryView", AnyView, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( QueryView )
      DropObjectInstance( QueryView );
   } 

   //:END

   //:// Activate the LOD (Query View) requested.
   //:nRC = ActivateQueryViewByName( AnyView, QueryView, wXferO.QueryObject.ObjectName )
   {StringBuilder sb_szTempString_0;
   if ( szTempString_0 == null )
      sb_szTempString_0 = new StringBuilder( 32 );
   else
      sb_szTempString_0 = new StringBuilder( szTempString_0 );
       GetStringFromAttribute( sb_szTempString_0, wXferO, "QueryObject", "ObjectName" );
   szTempString_0 = sb_szTempString_0.toString( );}
   {
    zGLOBALQ_Operation m_zGLOBALQ_Operation = new zGLOBALQ_Operation( AnyView );
    nRC = m_zGLOBALQ_Operation.ActivateQueryViewByName( AnyView, QueryView, szTempString_0 );
    // m_zGLOBALQ_Operation = null;  // permit gc  (unnecessary)
   }
   //:IF nRC < 0
   if ( nRC < 0 )
   { 
      //:MessageSend( AnyView, "", "Select Query",
      //:             "Query LOD cannot be loaded.",
      //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
      MessageSend( AnyView, "", "Select Query", "Query LOD cannot be loaded.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
      //:RETURN 2
      if(8==8)return( 2 );
   } 

   //:END
   //:NAME VIEW QueryView "QueryView"
   SetNameForView( QueryView, "QueryView", null, zLEVEL_TASK );

   //:// Create the empty stored query instance and tie it to the stored frame for the new frame.
   //:ACTIVATE zqSQuery EMPTY
   RESULT = ActivateEmptyObjectInstance( zqSQuery, "zqSQuery", AnyView, zSINGLE );
   //:CREATE ENTITY zqSQuery.StoredQuery
   RESULT = CreateEntity( zqSQuery, "StoredQuery", zPOS_AFTER );
   //:NAME VIEW zqSQuery "zqSQuery"
   SetNameForView( zqSQuery, "zqSQuery", null, zLEVEL_TASK );

   //:// Create initial zqFrame from the LOD just activated.
   //:GET VIEW zqFrame NAMED "zqFrame"
   RESULT = GetViewByName( zqFrame, "zqFrame", AnyView, zLEVEL_TASK );
   //:IF RESULT >= 0
   if ( RESULT >= 0 )
   { 
      //:DropObjectInstance( zqFrame )
      DropObjectInstance( zqFrame );
   } 

   //:END

   //:ACTIVATE zqFrame EMPTY
   RESULT = ActivateEmptyObjectInstance( zqFrame, "zqFrame", AnyView, zSINGLE );
   //:CREATE ENTITY zqFrame.zqFrame
   RESULT = CreateEntity( zqFrame, "zqFrame", zPOS_AFTER );
   //:NAME VIEW zqFrame "zqFrame"
   SetNameForView( zqFrame, "zqFrame", null, zLEVEL_TASK );
   //:zqFrame.zqFrame.QueryObjectName           = QueryView.LOD.Name
   SetAttributeFromAttribute( zqFrame, "zqFrame", "QueryObjectName", QueryView, "LOD", "Name" );
   //:zqFrame.zqFrame.QueryObjectRootEntityName = QueryView.LOD_EntityParent.Name
   SetAttributeFromAttribute( zqFrame, "zqFrame", "QueryObjectRootEntityName", QueryView, "LOD_EntityParent", "Name" );

   //:// Build QueryFrameAttribute for LOD, which are used in InitializeFrameForLOD.
   //:RebuildFrameAttr( zqFrame, QueryView )
   ozqFrame_RebuildFrameAttr( zqFrame, QueryView );

   //:// Build out the zqFrame subobject structures.
   //:InitializeFrameForLOD( zqFrame, QueryView )
   ozqFrame_InitializeFrameForLOD( zqFrame, QueryView );
   //:zqFrame.zqFrame.ResultType = "P"
   SetAttributeFromString( zqFrame, "zqFrame", "ResultType", "P" );
   //:SET CURSOR FIRST zqFrame.GeneralParameter
   RESULT = SetCursorFirstEntity( zqFrame, "GeneralParameter", "" );

   //:// Set new Query information in wXferO.
   //:wXferO.QueryValues.CurrentStoredQueryName = ""
   SetAttributeFromString( wXferO, "QueryValues", "CurrentStoredQueryName", "" );
   //:wXferO.QueryValues.QueryObjectName        = zqFrame.zqFrame.QueryObjectName
   SetAttributeFromAttribute( wXferO, "QueryValues", "QueryObjectName", zqFrame, "zqFrame", "QueryObjectName" );
   return( 0 );
// END
} 



}
