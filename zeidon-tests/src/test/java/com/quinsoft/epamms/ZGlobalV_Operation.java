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

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;

/**
   @author QuinSoft
**/

public class ZGlobalV_Operation extends VmlDialog
{
   private final ZDRVROPR m_ZDRVROPR;
   public ZGlobalV_Operation( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
   }


//:GLOBAL OPERATION
public int 
InitializeSLC_FromMLC( View     mMasLC,
                       View     mSubLC )
{

   //:InitializeSLC_FromMLC( VIEW mMasLC,
   //:                    VIEW mSubLC )

   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "General", 2 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "General", 2 );
   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "Ingredients", 2 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "Ingredients", 2 );
   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "StorageDisposal", 2 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "StorageDisposal", 2 );
   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "DirectionsForUse", 2 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "DirectionsForUse", 2 );
   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "Marketing", 2 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "Marketing", 2 );
   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "HumanHazard", 1 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "HumanHazard", 1 );
   //:CopyMLC_EntityToSLC( mMasLC, mSubLC, "Usage", 0 )
   CopyMLC_EntityToSLC( mMasLC, mSubLC, "Usage", 0 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:InsertMappingWordsIntoString( VIEW mSPLDef BASED ON LOD mSPLDef,
//:                              STRING ( 10000 ) szSourceString,
//:                              STRING ( 32 ) szUsageTypeEntityName,
//:                              STRING ( 32 ) szLoopingEntityName,
//:                              STRING ( 32 ) szSeparatorCharacters )

//:   VIEW mSPLDef2 BASED ON LOD mSPLDef
public int 
InsertMappingWordsIntoString( View     mSPLDef,
                              StringBuilder   szSourceString,
                              String   szUsageTypeEntityName,
                              String   szLoopingEntityName,
                              String   szSeparatorCharacters )
{
   zVIEW    mSPLDef2 = new zVIEW( );
   //:STRING ( 10000 ) szToString
   String   szToString = null;
   //:STRING ( 32 )    szUsageType
   String   szUsageType = null;
   //:STRING ( 32 )    szAttributeType
   String   szAttributeType = null;
   //:STRING ( 100 )   szUsageValue
   String   szUsageValue = null;
   //:STRING ( 100 )   szInsertValue
   String   szInsertValue = null;
   //:STRING ( 1 )     szSelectUsageType
   String   szSelectUsageType = null;
   //:INTEGER Count
   int      Count = 0;
   //:INTEGER SourceIndex
   int      SourceIndex = 0;
   //:INTEGER Length
   int      Length = 0;
   //:INTEGER TypeLength
   int      TypeLength = 0;
   //:INTEGER SourceStringLength
   int      SourceStringLength = 0;
   //:INTEGER MoveStringLength
   int      MoveStringLength = 0;
   //:INTEGER TargetOffset
   int      TargetOffset = 0;
   //:INTEGER SourceOffset
   int      SourceOffset = 0;
   //:INTEGER TempLength
   int      TempLength = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;


   //:// Insert Usage text into a position in szStringArea that is identified by a Usage Keyword.
   //:// The entries inserted will be separated by one or more characters as identified by the variable szSeparatorCharacters.
   //:// After determining the position of the insertion, we will loop through Usage entries, formatting each entry as we go.
   //://TraceLineS( "### Insert szUsageTypeEntityName: ", szUsageTypeEntityName )
   //://TraceLineS( "### Insert szLoopingEntityName: ", szLoopingEntityName )

   //:SourceIndex  = 1
   SourceIndex = 1;
   //:SourceOffset = 1
   SourceOffset = 1;
   //:MoveStringLength = 1
   MoveStringLength = 1;
   //:szToString = ""
    {StringBuilder sb_szToString;
   if ( szToString == null )
      sb_szToString = new StringBuilder( 32 );
   else
      sb_szToString = new StringBuilder( szToString );
      ZeidonStringCopy( sb_szToString, 1, 0, "", 1, 0, 10001 );
   szToString = sb_szToString.toString( );}
   //:Length = zGetStringLen( szSourceString )
   Length = zGetStringLen( szSourceString.toString( ) );
   //:Length = Length - 4    // We need to back off the end of the string so that the two character compare doesn't go beyond the string.
   Length = Length - 4;
   //:LOOP WHILE SourceIndex <= Length
   while ( SourceIndex <= Length )
   { 
      //:IF szSourceString[SourceIndex:2] = "{{"
      if ( ZeidonStringCompare( szSourceString.toString( ), SourceIndex, 2, "{{", 1, 0, 10001 ) == 0 )
      { 

         //:// First copy over the characters we've just skipped over and reset length (unless {{ are first two characters).
         //:// ZeidonStringCopy( Target, TargetOffset, TargetMaxToReceive, Source, SourceOffset, NoCharsToCopy, TargetMaxLength )
         //:IF SourceIndex > 1
         if ( SourceIndex > 1 )
         { 
            //:TargetOffset = zstrlen( szToString )
            TargetOffset = zstrlen( szToString );
            //:TargetOffset = TargetOffset + 1
            TargetOffset = TargetOffset + 1;
            //:MoveStringLength = MoveStringLength - 1
            MoveStringLength = MoveStringLength - 1;
            //:ZeidonStringCopy( szToString, TargetOffset, 5000, szSourceString, SourceOffset, MoveStringLength, 10000 )
            {StringBuilder sb_szToString;
            if ( szToString == null )
               sb_szToString = new StringBuilder( 32 );
            else
               sb_szToString = new StringBuilder( szToString );
                         ZeidonStringCopy( sb_szToString, TargetOffset, 5000, szSourceString.toString( ), SourceOffset, MoveStringLength, 10000 );
            szToString = sb_szToString.toString( );}
            //:SourceOffset = SourceOffset + MoveStringLength   // Skip past characters just moved.
            SourceOffset = SourceOffset + MoveStringLength;
            //:MoveStringLength = 0
            MoveStringLength = 0;
         } 

         //:END

         //:// Parse the Usage Type out of the string of the form {{xxxxx}}.
         //:SourceIndex  = SourceIndex + 2
         SourceIndex = SourceIndex + 2;
         //:TypeLength  = 0
         TypeLength = 0;
         //:TempLength = Length + 4  // We need to add the characters back to the length in case the Type is at the end of the Source.
         TempLength = Length + 4;
         //:LOOP WHILE szSourceString[SourceIndex:2] != "}}" AND TypeLength <= 32 AND SourceIndex < TempLength
         while ( ZeidonStringCompare( szSourceString.toString( ), SourceIndex, 2, "}}", 1, 0, 10001 ) != 0 && TypeLength <= 32 && SourceIndex < TempLength )
         { 
            //:SourceIndex = SourceIndex + 1
            SourceIndex = SourceIndex + 1;
            //:TypeLength  = TypeLength + 1
            TypeLength = TypeLength + 1;
         } 

         //:END
         //:SourceOffset = SourceOffset + 2  // Skip past closing double parens and length of keyword.
         SourceOffset = SourceOffset + 2;
         //:ZeidonStringCopy( szUsageType, 1, 50, szSourceString, SourceOffset, TypeLength, 100 )
         {StringBuilder sb_szUsageType;
         if ( szUsageType == null )
            sb_szUsageType = new StringBuilder( 32 );
         else
            sb_szUsageType = new StringBuilder( szUsageType );
                   ZeidonStringCopy( sb_szUsageType, 1, 50, szSourceString.toString( ), SourceOffset, TypeLength, 100 );
         szUsageType = sb_szUsageType.toString( );}
         //:SourceIndex = SourceIndex + 2
         SourceIndex = SourceIndex + 2;
         //:SourceOffset = SourceOffset + TypeLength + 2  // Skip past closing double parens and length of keyword.
         SourceOffset = SourceOffset + TypeLength + 2;

         //:// Copy the Usage values into the text. This will depend on Type.

         //:// Claim, Surface, Area of Use, and Application Type.
         //:IF szUsageType = "Claim" OR szUsageType = "Claims" OR 
         //:   szUsageType = "Surface" OR szUsageType = "Surfaces" OR 
         //:   szUsageType = "Area Of Use" OR szUsageType = "Areas Of Use" OR 
         //:   szUsageType = "Application Type" OR  szUsageType = "Application Types"
         if ( ZeidonStringCompare( szUsageType, 1, 0, "Claim", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Claims", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Surface", 1, 0, 33 ) == 0 ||
              ZeidonStringCompare( szUsageType, 1, 0, "Surfaces", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Area Of Use", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Areas Of Use", 1, 0, 33 ) == 0 ||
              ZeidonStringCompare( szUsageType, 1, 0, "Application Type", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Application Types", 1, 0, 33 ) == 0 )
         { 

            //:IF szUsageType = "Claim" OR szUsageType = "Claims"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "Claim", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Claims", 1, 0, 33 ) == 0 )
            { 
               //:szSelectUsageType = "C"
                {StringBuilder sb_szSelectUsageType;
               if ( szSelectUsageType == null )
                  sb_szSelectUsageType = new StringBuilder( 32 );
               else
                  sb_szSelectUsageType = new StringBuilder( szSelectUsageType );
                              ZeidonStringCopy( sb_szSelectUsageType, 1, 0, "C", 1, 0, 2 );
               szSelectUsageType = sb_szSelectUsageType.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF szUsageType = "Surface" OR szUsageType = "Surfaces"
               if ( ZeidonStringCompare( szUsageType, 1, 0, "Surface", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Surfaces", 1, 0, 33 ) == 0 )
               { 
                  //:szSelectUsageType = "S"
                   {StringBuilder sb_szSelectUsageType;
                  if ( szSelectUsageType == null )
                     sb_szSelectUsageType = new StringBuilder( 32 );
                  else
                     sb_szSelectUsageType = new StringBuilder( szSelectUsageType );
                                    ZeidonStringCopy( sb_szSelectUsageType, 1, 0, "S", 1, 0, 2 );
                  szSelectUsageType = sb_szSelectUsageType.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF szUsageType = "Area Of Use" OR szUsageType = "Areas Of Use"
                  if ( ZeidonStringCompare( szUsageType, 1, 0, "Area Of Use", 1, 0, 33 ) == 0 || ZeidonStringCompare( szUsageType, 1, 0, "Areas Of Use", 1, 0, 33 ) == 0 )
                  { 
                     //:szSelectUsageType = "U"
                      {StringBuilder sb_szSelectUsageType;
                     if ( szSelectUsageType == null )
                        sb_szSelectUsageType = new StringBuilder( 32 );
                     else
                        sb_szSelectUsageType = new StringBuilder( szSelectUsageType );
                                          ZeidonStringCopy( sb_szSelectUsageType, 1, 0, "U", 1, 0, 2 );
                     szSelectUsageType = sb_szSelectUsageType.toString( );}
                     //:ELSE
                  } 
                  else
                  { 
                     //:szSelectUsageType = "T"
                      {StringBuilder sb_szSelectUsageType;
                     if ( szSelectUsageType == null )
                        sb_szSelectUsageType = new StringBuilder( 32 );
                     else
                        sb_szSelectUsageType = new StringBuilder( szSelectUsageType );
                                          ZeidonStringCopy( sb_szSelectUsageType, 1, 0, "T", 1, 0, 2 );
                     szSelectUsageType = sb_szSelectUsageType.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END

            //:// Process Usage entries if the Looping Entity Name is specified.
            //:IF szLoopingEntityName != ""
            if ( ZeidonStringCompare( szLoopingEntityName, 1, 0, "", 1, 0, 33 ) != 0 )
            { 
               //:Count = 0
               Count = 0;
               //:nRC = SetCursorFirstEntity( mSPLDef, szLoopingEntityName, "" )
               nRC = SetCursorFirstEntity( mSPLDef, szLoopingEntityName, "" );
               //:LOOP WHILE nRC >= zCURSOR_SET
               while ( nRC >= zCURSOR_SET )
               { 
                  //:nRC = CompareAttributeToString( mSPLDef, szUsageTypeEntityName, "UsageType", szSelectUsageType )
                  nRC = CompareAttributeToString( mSPLDef, szUsageTypeEntityName, "UsageType", szSelectUsageType );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:// There is a match on UsageType.
                     //:// Copy Usage variable to text. If not first entry, put in a separator character.
                     //:Count = Count + 1
                     Count = Count + 1;
                     //:GetStringFromAttribute( szUsageValue, mSPLDef, szUsageTypeEntityName, "Name" )
                     {StringBuilder sb_szUsageValue;
                     if ( szUsageValue == null )
                        sb_szUsageValue = new StringBuilder( 32 );
                     else
                        sb_szUsageValue = new StringBuilder( szUsageValue );
                                           GetStringFromAttribute( sb_szUsageValue, mSPLDef, szUsageTypeEntityName, "Name" );
                     szUsageValue = sb_szUsageValue.toString( );}
                     //:IF Count > 1
                     if ( Count > 1 )
                     { 
                        //:// If szSeparatorCharacters are ", ", substitute " and " for the separator characters before the last
                        //:// Usage entry.
                        //:CreateViewFromView( mSPLDef2, mSPLDef )
                        CreateViewFromView( mSPLDef2, mSPLDef );
                        //:nRC = SetCursorNextEntity( mSPLDef2, szLoopingEntityName, "" )
                        nRC = SetCursorNextEntity( mSPLDef2, szLoopingEntityName, "" );
                        //:IF nRC < zCURSOR_SET
                        if ( nRC < zCURSOR_SET )
                        { 
                           //:szToString = szToString + " and "
                            {StringBuilder sb_szToString;
                           if ( szToString == null )
                              sb_szToString = new StringBuilder( 32 );
                           else
                              sb_szToString = new StringBuilder( szToString );
                                                      ZeidonStringConcat( sb_szToString, 1, 0, " and ", 1, 0, 10001 );
                           szToString = sb_szToString.toString( );}
                           //:ELSE
                        } 
                        else
                        { 
                           //:szToString = szToString + szSeparatorCharacters
                            {StringBuilder sb_szToString;
                           if ( szToString == null )
                              sb_szToString = new StringBuilder( 32 );
                           else
                              sb_szToString = new StringBuilder( szToString );
                                                      ZeidonStringConcat( sb_szToString, 1, 0, szSeparatorCharacters, 1, 0, 10001 );
                           szToString = sb_szToString.toString( );}
                        } 

                        //:END
                        //:DropView( mSPLDef2 )
                        DropView( mSPLDef2 );
                     } 

                     //:END
                     //:szToString = szToString + szUsageValue
                      {StringBuilder sb_szToString;
                     if ( szToString == null )
                        sb_szToString = new StringBuilder( 32 );
                     else
                        sb_szToString = new StringBuilder( szToString );
                                          ZeidonStringConcat( sb_szToString, 1, 0, szUsageValue, 1, 0, 10001 );
                     szToString = sb_szToString.toString( );}
                  } 

                  //:END
                  //:nRC = SetCursorNextEntity( mSPLDef, szLoopingEntityName, "" )
                  nRC = SetCursorNextEntity( mSPLDef, szLoopingEntityName, "" );
               } 

               //:END
            } 

            //:END

            //:ELSE
         } 
         else
         { 
            //:IF szUsageType = "Product Name"
            if ( ZeidonStringCompare( szUsageType, 1, 0, "Product Name", 1, 0, 33 ) == 0 )
            { 
               //:szInsertValue = mSPLDef.SubregPhysicalLabelDef.ProductName 
               {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
               StringBuilder sb_szInsertValue;
               if ( szInsertValue == null )
                  sb_szInsertValue = new StringBuilder( 32 );
               else
                  sb_szInsertValue = new StringBuilder( szInsertValue );
                               GetVariableFromAttribute( sb_szInsertValue, mi_lTempInteger_0, 'S', 101, mSPLDef, "SubregPhysicalLabelDef", "ProductName", "", 0 );
               lTempInteger_0 = mi_lTempInteger_0.intValue( );
               szInsertValue = sb_szInsertValue.toString( );}
               //:szToString = szToString + szInsertValue
                {StringBuilder sb_szToString;
               if ( szToString == null )
                  sb_szToString = new StringBuilder( 32 );
               else
                  sb_szToString = new StringBuilder( szToString );
                              ZeidonStringConcat( sb_szToString, 1, 0, szInsertValue, 1, 0, 10001 );
               szToString = sb_szToString.toString( );}
            } 

            //:END
         } 

         //:END
      } 

      //:END

      //:SourceIndex      = SourceIndex + 1
      SourceIndex = SourceIndex + 1;
      //:MoveStringLength = MoveStringLength + 1
      MoveStringLength = MoveStringLength + 1;
   } 

   //:END

   //:// Move in remaining characters.
   //:MoveStringLength = MoveStringLength + 10    // Add extra characters to length as above was not calculating them correctly.
   MoveStringLength = MoveStringLength + 10;
   //:TargetOffset = zstrlen( szToString )
   TargetOffset = zstrlen( szToString );
   //:TargetOffset = TargetOffset + 1
   TargetOffset = TargetOffset + 1;
   //:ZeidonStringCopy( szToString, TargetOffset, 5000, szSourceString, SourceOffset, MoveStringLength, 10000 )
   {StringBuilder sb_szToString;
   if ( szToString == null )
      sb_szToString = new StringBuilder( 32 );
   else
      sb_szToString = new StringBuilder( szToString );
       ZeidonStringCopy( sb_szToString, TargetOffset, 5000, szSourceString.toString( ), SourceOffset, MoveStringLength, 10000 );
   szToString = sb_szToString.toString( );}
   //:szSourceString = szToString
   ZeidonStringCopy( szSourceString, 1, 0, szToString, 1, 0, 10001 );
   return( 0 );
//     /*****
//        zVIEW  viewT;
//        String  szCurrentType[ 2 ];
//        String  szCurrentName[ 51 ];
//        String pchFromString;
//        String pchToString;
//        String pchToStringHold;
//        int    lMemHandle;
//        int    nUsageKeywordLth;
//        int    nSeparatorCharsLth;
//        int    nNameLth;
//        int    nCount;
//        int    nRC;
//        // Insert Usage text into a position in szStringArea that is identified by a Usage Keyword.
//        // The entries inserted will be separated by one or more characters as identified by the variable szSeparatorCharacters.
//        // After determining the position of the insertion, we will loop through Usage entries, formatting each entry as we go.
//        // Which entries we insert depend on the UsageType as follows:
//        // A - Insert all Usage entries.
//        // C - Insert only Claim Usage entries.
//        // S - Insert only Surface Usage entries.
//        // T - Insert only Application Type Usage entries.
//        // U - Insert only Area of Use Usage entries.
//        // Copy all characters up to the point of the keyword. If there is no keyword match, we simply copy all characters.
//        // If we get a match on the keyword, insert the characters and finish copying the rest of the text.
//        lMemHandle = SysAllocMemory( &pchToString, lMaxLth, 0, zCOREMEM_ALLOC, 0 );
//        pchToStringHold = pchToString;
//        nSeparatorCharsLth = zstrlen( szSeparatorCharacters );
//        nUsageKeywordLth = zstrlen( szUsageKeyword );
//        pchFromString = pchString;
//        while ( *pchFromString )
//        {
//           // Compare the keyword to the characters in the string.
//           if ( zstrncmp( pchFromString, szUsageKeyword, nUsageKeywordLth ) == 0 )
//           {
//              // There was a keyword match ... insert Usage entries.
//              nCount = 0;
//              nRC = SetCursorFirstEntity( view, szUsageEntityName, szUsageEntityNameScope );
//              while ( nRC >= zCURSOR_SET )
//              {
//                 GetVariableFromAttribute( szCurrentType, 0, zTYPE_STRING, 2, view, szUsageEntityName, "UsageType", 0, 0 );
//                 GetVariableFromAttribute( szCurrentName, 0, zTYPE_STRING, 51, view, szUsageEntityName, "Name", 0, 0 );
//                 // Insert this entry, if the Usage entry is the same Type or "All" is requested.
//                 if ( szUsageType[ 0 ] == 'A' || szUsageType[ 0 ] == szCurrentType[ 0 ] )
//                 {
//                    nNameLth = zstrlen( szCurrentName );
//                    // For any entry but the first or last, copy the separator characters.
//                    // For the first entry, don't add any characters at all.
//                    // For the last entry, add the characters "and".
//                    nCount++;
//                    CreateViewFromView( &viewT, view );
//                    if ( szUsageType[ 0 ] == 'A' )
//                       nRC = SetCursorNextEntity( viewT, szUsageEntityName, szUsageEntityNameScope );
//                    else
//                       nRC = SetCursorNextEntityByString( viewT, szUsageEntityName, "UsageType", szUsageType, szUsageEntityNameScope );
//                    DropView( viewT );
//                    if ( nRC < zCURSOR_SET )
//                    {
//                       zstrcpy( pchToString, " and " );
//                       pchToString += 5;
//                    }
//                    else
//                    {
//                       if ( nCount > 1 )
//                       {
//                          zstrcpy( pchToString, szSeparatorCharacters );
//                          pchToString += nSeparatorCharsLth;
//                       }
//                    }
//                    zstrcpy( pchToString, szCurrentName );
//                    nNameLth = zstrlen( szCurrentName );
//                    pchToString += nNameLth;
//                 }
//                 nRC = SetCursorNextEntity( view, szUsageEntityName, szUsageEntityNameScope );
//              }
//              pchFromString = pchFromString + nUsageKeywordLth;    // skip past keyword
//           }
//           else
//           {
//              // There was no keyword match, simply copy the character.
//              *pchToString = *pchFromString;
//              pchToString++;
//              pchFromString++;
//           }
//        }
//        zstrcpy( pchString, pchToStringHold );  // copy data back into original string
//        SysFreeMemory( lMemHandle  );
//     ****/
// END
} 


//:GLOBAL OPERATION
public void 
SyncPhysicalLabel( View     mSPLDef,
                   View     mSubLC,
                   int      bFlag )
{
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;
   int      lTempInteger_4 = 0;
   int      lTempInteger_5 = 0;
   int      RESULT = 0;

   //:SyncPhysicalLabel( VIEW  mSPLDef,
   //:                VIEW  mSubLC,
   //:                SHORT bFlag )  // TRUE ==> from SubLC to SPLDef

   //:IF bFlag = TRUE
   if ( bFlag == TRUE )
   { 

      //:IF mSPLDef.SubregLabelContent DOES NOT EXIST
      lTempInteger_0 = CheckExistenceOfEntity( mSPLDef, "SubregLabelContent" );
      if ( lTempInteger_0 != 0 )
      { 
         //:CreateEntity( mSPLDef, "SubregLabelContent", zPOS_FIRST )
         CreateEntity( mSPLDef, "SubregLabelContent", zPOS_FIRST );
      } 

      //:END

      //:IF mSPLDef.SubregProduct DOES NOT EXIST
      lTempInteger_1 = CheckExistenceOfEntity( mSPLDef, "SubregProduct" );
      if ( lTempInteger_1 != 0 )
      { 
         //:CreateEntity( mSPLDef, "SubregProduct", zPOS_FIRST )
         CreateEntity( mSPLDef, "SubregProduct", zPOS_FIRST );
      } 

      //:END

      //:IF mSPLDef.Subregistrant DOES NOT EXIST
      lTempInteger_2 = CheckExistenceOfEntity( mSPLDef, "Subregistrant" );
      if ( lTempInteger_2 != 0 )
      { 
         //:CreateEntity( mSPLDef, "Subregistrant", zPOS_FIRST )
         CreateEntity( mSPLDef, "Subregistrant", zPOS_FIRST );
      } 

      //:END

      //:IF mSPLDef.MasterProduct DOES NOT EXIST
      lTempInteger_3 = CheckExistenceOfEntity( mSPLDef, "MasterProduct" );
      if ( lTempInteger_3 != 0 )
      { 
         //:CreateEntity( mSPLDef, "MasterProduct", zPOS_FIRST )
         CreateEntity( mSPLDef, "MasterProduct", zPOS_FIRST );
      } 

      //:END

      //:IF mSPLDef.PrimaryRegistrant DOES NOT EXIST
      lTempInteger_4 = CheckExistenceOfEntity( mSPLDef, "PrimaryRegistrant" );
      if ( lTempInteger_4 != 0 )
      { 
         //:CreateEntity( mSPLDef, "PrimaryRegistrant", zPOS_FIRST )
         CreateEntity( mSPLDef, "PrimaryRegistrant", zPOS_FIRST );
      } 

      //:END

      //:IF mSPLDef.MasterLabelContent DOES NOT EXIST
      lTempInteger_5 = CheckExistenceOfEntity( mSPLDef, "MasterLabelContent" );
      if ( lTempInteger_5 != 0 )
      { 
         //:CreateEntity( mSPLDef, "MasterLabelContent", zPOS_FIRST )
         CreateEntity( mSPLDef, "MasterLabelContent", zPOS_FIRST );
      } 

      //:END

      //:SetMatchingAttributesByName( mSPLDef, "SubregLabelContent",
      //:                             mSubLC, "SubregLabelContent", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef, "SubregLabelContent", mSubLC, "SubregLabelContent", zSET_ALL );
      //:SetMatchingAttributesByName( mSPLDef, "SubregProduct",
      //:                             mSubLC, "SubregProduct", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef, "SubregProduct", mSubLC, "SubregProduct", zSET_ALL );
      //:SetMatchingAttributesByName( mSPLDef, "Subregistrant",
      //:                             mSubLC, "Subregistrant", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef, "Subregistrant", mSubLC, "Subregistrant", zSET_ALL );
      //:SetMatchingAttributesByName( mSPLDef, "MasterProduct",
      //:                             mSubLC, "MasterProduct", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef, "MasterProduct", mSubLC, "MasterProduct", zSET_ALL );
      //:SetMatchingAttributesByName( mSPLDef, "PrimaryRegistrant",
      //:                             mSubLC, "PrimaryRegistrant", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef, "PrimaryRegistrant", mSubLC, "PrimaryRegistrant", zSET_ALL );
      //:SetMatchingAttributesByName( mSPLDef, "MasterLabelContent",
      //:                             mSubLC, "MasterLabelContent", zSET_ALL )
      SetMatchingAttributesByName( mSPLDef, "MasterLabelContent", mSubLC, "MasterLabelContent", zSET_ALL );

      //:ELSE
   } 
   else
   { 

      //:SetMatchingAttributesByName( mSubLC, "SubregLabelContent",
      //:                             mSPLDef, "SubregLabelContent", zSET_ALL )
      SetMatchingAttributesByName( mSubLC, "SubregLabelContent", mSPLDef, "SubregLabelContent", zSET_ALL );
      //:// SetMatchingAttributesByName( mSubLC, "SubregProduct",
      //://                              mSPLDef, "SubregProduct", zSET_ALL )
      //:// SetMatchingAttributesByName( mSubLC, "Subregistrant",
      //://                              mSPLDef, "Subregistrant", zSET_ALL )
      //:// SetMatchingAttributesByName( mSubLC, "MasterProduct",
      //://                              mSPLDef, "MasterProduct", zSET_ALL )
      //:// SetMatchingAttributesByName( mSubLC, "PrimaryRegistrant",
      //://                              mSPLDef, "PrimaryRegistrant", zSET_ALL )
      //:// SetMatchingAttributesByName( mSubLC, "MasterLabelContent",
      //://                              mSPLDef, "MasterLabelContent", zSET_ALL )
      //:COMMIT mSubLC
      RESULT = CommitObjectInstance( mSubLC );
   } 


   //:END
   return;
// END
} 


//:GLOBAL OPERATION
//:AcceptSPLDef_TemporalSubobject( VIEW  ViewToWindow,
//:                                STRING ( 32 )  szEntityName,
//:                                SHORT bCommit,
//:                                STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptSPLDef_TemporalSubobject( View     ViewToWindow,
                                String   szEntityName,
                                int      bCommit,
                                String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   BASED ON LOD  mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:STRING ( 32 )  szEntitySection
   String   szEntitySection = null;
   //:STRING ( 32 )  szEntityStatement
   String   szEntityStatement = null;
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   //:STRING ( 1 )   szSelected
   String   szSelected = null;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mSPLDef != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mSPLDef ) != null )
   { 

      //:IF szEntityName = "SPLDI_FirstAidSection" OR
      //:   szEntityName = "SPLDI_PrecautionarySection" OR
      //:   szEntityName = "SPLDI_HazardSection" OR
      //:   szEntityName = "SPLDI_UsageList"
      if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_FirstAidSection", 1, 0, 33 ) == 0 || ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_PrecautionarySection", 1, 0, 33 ) == 0 ||
           ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_HazardSection", 1, 0, 33 ) == 0 || ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_UsageList", 1, 0, 33 ) == 0 )
      { 

         //:szEntitySection = szEntityName
          {StringBuilder sb_szEntitySection;
         if ( szEntitySection == null )
            sb_szEntitySection = new StringBuilder( 32 );
         else
            sb_szEntitySection = new StringBuilder( szEntitySection );
                  ZeidonStringCopy( sb_szEntitySection, 1, 0, szEntityName, 1, 0, 33 );
         szEntitySection = sb_szEntitySection.toString( );}
         //:CreateViewFromView( mTempPL, mSPLDef )
         CreateViewFromView( mTempPL, mSPLDef );

         //:IF szEntitySection = "SPLDI_UsageList"
         if ( ZeidonStringCompare( szEntitySection, 1, 0, "SPLDI_UsageList", 1, 0, 33 ) == 0 )
         { 

            //:nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" )
            nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" );
            //:LOOP WHILE nRC = 0
            while ( nRC == 0 )
            { 
               //:AcceptSubobject( mTempPL, szEntitySection )
               AcceptSubobject( mTempPL, szEntitySection );
               //:nRC = SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, szEntitySection, "" )
               nRC = SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_Usage", mTempPL, szEntitySection, "" );
               //:IF nRC = 0
               if ( nRC == 0 )
               { 
                  //:SetMatchingAttributesByName( mTempPL, "SPLD_Usage",
                  //:                             mTempPL, szEntitySection, zSET_NOTNULL )
                  SetMatchingAttributesByName( mTempPL, "SPLD_Usage", mTempPL, szEntitySection, zSET_NOTNULL );
                  //:GetStringFromAttribute( szSelected, mTempPL, szEntitySection, "Selected" )
                  {StringBuilder sb_szSelected;
                  if ( szSelected == null )
                     sb_szSelected = new StringBuilder( 32 );
                  else
                     sb_szSelected = new StringBuilder( szSelected );
                                     GetStringFromAttribute( sb_szSelected, mTempPL, szEntitySection, "Selected" );
                  szSelected = sb_szSelected.toString( );}
                  //:mTempPL.SPLD_Usage.Selected = szSelected
                  SetAttributeFromString( mTempPL, "SPLD_Usage", "Selected", szSelected );
               } 

               //:END

               //:ExcludeEntity( mTempPL, szEntitySection, zREPOS_NONE )
               ExcludeEntity( mTempPL, szEntitySection, zREPOS_NONE );
               //:nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" )
               nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" );
            } 

            //:END

            //:ELSE
         } 
         else
         { 

            //:IF szEntitySection = "SPLDI_FirstAidSection"
            if ( ZeidonStringCompare( szEntitySection, 1, 0, "SPLDI_FirstAidSection", 1, 0, 33 ) == 0 )
            { 
               //:szEntityStatement = "SPLD_FirstAidStatement"
                {StringBuilder sb_szEntityStatement;
               if ( szEntityStatement == null )
                  sb_szEntityStatement = new StringBuilder( 32 );
               else
                  sb_szEntityStatement = new StringBuilder( szEntityStatement );
                              ZeidonStringCopy( sb_szEntityStatement, 1, 0, "SPLD_FirstAidStatement", 1, 0, 33 );
               szEntityStatement = sb_szEntityStatement.toString( );}
               //:ELSE
            } 
            else
            { 
               //:IF szEntitySection = "SPLDI_PrecautionarySection"
               if ( ZeidonStringCompare( szEntitySection, 1, 0, "SPLDI_PrecautionarySection", 1, 0, 33 ) == 0 )
               { 
                  //:szEntityStatement = "SPLD_PrecautionaryStatement"
                   {StringBuilder sb_szEntityStatement;
                  if ( szEntityStatement == null )
                     sb_szEntityStatement = new StringBuilder( 32 );
                  else
                     sb_szEntityStatement = new StringBuilder( szEntityStatement );
                                    ZeidonStringCopy( sb_szEntityStatement, 1, 0, "SPLD_PrecautionaryStatement", 1, 0, 33 );
                  szEntityStatement = sb_szEntityStatement.toString( );}
                  //:ELSE
               } 
               else
               { 
                  //:IF szEntitySection = "SPLDI_HazardSection"
                  if ( ZeidonStringCompare( szEntitySection, 1, 0, "SPLDI_HazardSection", 1, 0, 33 ) == 0 )
                  { 
                     //:szEntityStatement = "SPLD_HazardStatement"
                      {StringBuilder sb_szEntityStatement;
                     if ( szEntityStatement == null )
                        sb_szEntityStatement = new StringBuilder( 32 );
                     else
                        sb_szEntityStatement = new StringBuilder( szEntityStatement );
                                          ZeidonStringCopy( sb_szEntityStatement, 1, 0, "SPLD_HazardStatement", 1, 0, 33 );
                     szEntityStatement = sb_szEntityStatement.toString( );}
                  } 

                  //:END
               } 

               //:END
            } 

            //:END

            //:nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" )
            nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" );
            //:LOOP WHILE nRC = 0
            while ( nRC == 0 )
            { 
               //:GetIntegerFromAttribute( lID, mTempPL, szEntitySection, "ID" )
               {MutableInt mi_lID = new MutableInt( lID );
                               GetIntegerFromAttribute( mi_lID, mTempPL, szEntitySection, "ID" );
               lID = mi_lID.intValue( );}
               //:SetCursorFirstEntityByInteger( mTempPL, "SPLD_GeneralSection", "ID", lID, "" )
               SetCursorFirstEntityByInteger( mTempPL, "SPLD_GeneralSection", "ID", lID, "" );
               //:nRC = AcceptSubobject( mTempPL, szEntitySection )
               nRC = AcceptSubobject( mTempPL, szEntitySection );
               //:szFullComment = "AcceptSubobject Prior to second Loop ====> " + szEntitySection + "." + szEntityStatement + "  RC: "
                {StringBuilder sb_szFullComment;
               if ( szFullComment == null )
                  sb_szFullComment = new StringBuilder( 32 );
               else
                  sb_szFullComment = new StringBuilder( szFullComment );
                              ZeidonStringCopy( sb_szFullComment, 1, 0, "AcceptSubobject Prior to second Loop ====> ", 1, 0, 301 );
               szFullComment = sb_szFullComment.toString( );}
                {StringBuilder sb_szFullComment;
               if ( szFullComment == null )
                  sb_szFullComment = new StringBuilder( 32 );
               else
                  sb_szFullComment = new StringBuilder( szFullComment );
                              ZeidonStringConcat( sb_szFullComment, 1, 0, szEntitySection, 1, 0, 301 );
               szFullComment = sb_szFullComment.toString( );}
                {StringBuilder sb_szFullComment;
               if ( szFullComment == null )
                  sb_szFullComment = new StringBuilder( 32 );
               else
                  sb_szFullComment = new StringBuilder( szFullComment );
                              ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
               szFullComment = sb_szFullComment.toString( );}
                {StringBuilder sb_szFullComment;
               if ( szFullComment == null )
                  sb_szFullComment = new StringBuilder( 32 );
               else
                  sb_szFullComment = new StringBuilder( szFullComment );
                              ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityStatement, 1, 0, 301 );
               szFullComment = sb_szFullComment.toString( );}
                {StringBuilder sb_szFullComment;
               if ( szFullComment == null )
                  sb_szFullComment = new StringBuilder( 32 );
               else
                  sb_szFullComment = new StringBuilder( szFullComment );
                              ZeidonStringConcat( sb_szFullComment, 1, 0, "  RC: ", 1, 0, 301 );
               szFullComment = sb_szFullComment.toString( );}
               //:TraceLineI( szFullComment, nRC )
               TraceLineI( szFullComment, nRC );
               //:SetMatchingAttributesByName( mTempPL, "SPLD_GeneralSection",
               //:                             mTempPL, szEntitySection, zSET_NOTNULL )
               SetMatchingAttributesByName( mTempPL, "SPLD_GeneralSection", mTempPL, szEntitySection, zSET_NOTNULL );
               //:// DisplayEntityInstance( mTempPL, szEntitySection )
               //:nRC = SetCursorFirstEntity( mTempPL, szEntityStatement, "" )
               nRC = SetCursorFirstEntity( mTempPL, szEntityStatement, "" );
               //:LOOP WHILE nRC = 0
               while ( nRC == 0 )
               { 

                  //:// DisplayEntityInstance( mTempPL, szEntityStatement )
                  //:// TraceLineS( "", "" )

                  //:// nRC = SetCursorFirstEntityByEntityCsr( mTempPL, "SPLD_GeneralStatement", mTempPL, szEntityStatement, "" )
                  //:GetIntegerFromAttribute( lID, mTempPL, szEntityStatement, "ID" )
                  {MutableInt mi_lID = new MutableInt( lID );
                                     GetIntegerFromAttribute( mi_lID, mTempPL, szEntityStatement, "ID" );
                  lID = mi_lID.intValue( );}
                  //:nRC = SetCursorFirstEntityByInteger( mTempPL, "SPLD_GeneralStatement", "ID", lID, "" )
                  nRC = SetCursorFirstEntityByInteger( mTempPL, "SPLD_GeneralStatement", "ID", lID, "" );
                  //:IF nRC = 0
                  if ( nRC == 0 )
                  { 
                     //:// We are situated on the right statement and thereby the right section.
                     //:TraceLineS( "Situated on the right statement", " and thereby the right section" )
                     TraceLineS( "Situated on the right statement", " and thereby the right section" );
                     //:// DisplayEntityInstance( mTempPL, "SPLD_GeneralStatement" )
                     //:// TraceLineS( "", "" )
                     //:SetMatchingAttributesByName( mTempPL, "SPLD_GeneralStatement",
                     //:                             mTempPL, szEntityStatement, zSET_NOTNULL )
                     SetMatchingAttributesByName( mTempPL, "SPLD_GeneralStatement", mTempPL, szEntityStatement, zSET_NOTNULL );
                     //:GetStringFromAttribute( szSelected, mTempPL, szEntityStatement, "Selected" )
                     {StringBuilder sb_szSelected;
                     if ( szSelected == null )
                        sb_szSelected = new StringBuilder( 32 );
                     else
                        sb_szSelected = new StringBuilder( szSelected );
                                           GetStringFromAttribute( sb_szSelected, mTempPL, szEntityStatement, "Selected" );
                     szSelected = sb_szSelected.toString( );}
                     //:IF szSelected = ""
                     if ( ZeidonStringCompare( szSelected, 1, 0, "", 1, 0, 2 ) == 0 )
                     { 
                        //:szSelected = "N"
                         {StringBuilder sb_szSelected;
                        if ( szSelected == null )
                           sb_szSelected = new StringBuilder( 32 );
                        else
                           sb_szSelected = new StringBuilder( szSelected );
                                                ZeidonStringCopy( sb_szSelected, 1, 0, "N", 1, 0, 2 );
                        szSelected = sb_szSelected.toString( );}
                     } 

                     //:END

                     //:mTempPL.SPLD_GeneralStatement.Selected = szSelected
                     SetAttributeFromString( mTempPL, "SPLD_GeneralStatement", "Selected", szSelected );
                     //:TraceLineS( "After set matching ////////////////* selected: ", szSelected )
                     TraceLineS( "After set matching ////////////////* selected: ", szSelected );
                     //:// DisplayEntityInstance( mTempPL, "SPLD_GeneralStatement" )
                     //:// TraceLineS( "", "" )
                     //:ELSE
                  } 
                  else
                  { 
                     //:szFullComment = "AcceptSubobject SetCursorFirstEntityByInteger FAILURE ====> " + szEntityStatement + "  ID: "
                      {StringBuilder sb_szFullComment;
                     if ( szFullComment == null )
                        sb_szFullComment = new StringBuilder( 32 );
                     else
                        sb_szFullComment = new StringBuilder( szFullComment );
                                          ZeidonStringCopy( sb_szFullComment, 1, 0, "AcceptSubobject SetCursorFirstEntityByInteger FAILURE ====> ", 1, 0, 301 );
                     szFullComment = sb_szFullComment.toString( );}
                      {StringBuilder sb_szFullComment;
                     if ( szFullComment == null )
                        sb_szFullComment = new StringBuilder( 32 );
                     else
                        sb_szFullComment = new StringBuilder( szFullComment );
                                          ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityStatement, 1, 0, 301 );
                     szFullComment = sb_szFullComment.toString( );}
                      {StringBuilder sb_szFullComment;
                     if ( szFullComment == null )
                        sb_szFullComment = new StringBuilder( 32 );
                     else
                        sb_szFullComment = new StringBuilder( szFullComment );
                                          ZeidonStringConcat( sb_szFullComment, 1, 0, "  ID: ", 1, 0, 301 );
                     szFullComment = sb_szFullComment.toString( );}
                     //:TraceLineI( szFullComment, lID )
                     TraceLineI( szFullComment, lID );
                     //:MessageSend( ViewToWindow, "", szEntitySection, szFullComment,
                     //:             zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
                     MessageSend( ViewToWindow, "", szEntitySection, szFullComment, zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 );
                  } 

                  //:END

                  //:nRC = SetCursorNextEntity( mTempPL, szEntityStatement, "" )
                  nRC = SetCursorNextEntity( mTempPL, szEntityStatement, "" );
               } 


               //:END

               //:ExcludeEntity( mTempPL, szEntitySection, zREPOS_NONE )
               ExcludeEntity( mTempPL, szEntitySection, zREPOS_NONE );
               //:nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" )
               nRC = SetCursorFirstEntity( mTempPL, szEntitySection, "" );
            } 

            //:END
         } 


         //:END

         //:DropView( mTempPL )
         DropView( mTempPL );

         //:ELSE
      } 
      else
      { 

         //:// IF szEntityName = "SubregPhysicalLabelDef"
         //://    GET VIEW mSubLC NAMED "mSubLC"
         //://    SyncPhysicalLabel( mSPLDef, mSubLC, FALSE )  // FALSE ==> from SPLDef to SubLC
         //:// END

         //:AcceptSubobject( mSPLDef, szEntityName )
         AcceptSubobject( mSPLDef, szEntityName );
      } 

      //:END

      //:IF bCommit = TRUE
      if ( bCommit == TRUE )
      { 
         //:MiSetInstanceUpdateFlag( mSPLDef, TRUE ) // here because the update flag was not always set automatically
         MiSetInstanceUpdateFlag( mSPLDef, TRUE );
         //:COMMIT mSPLDef
         RESULT = CommitObjectInstance( mSPLDef );
         //:szFullComment = "Accept/Commit"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept/Commit", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szFullComment = "Accept"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
      } 

      //:END

      //:szFullComment = szFullComment + " mSPLDef." + szEntityName + " =====================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " mSPLDef.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " =====================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:// MessageSend( ViewToWindow, "", szEntityName, szFullComment,
      //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to accept mSPLDef." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to accept mSPLDef.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:AcceptSubLC_TemporalSubobject( VIEW  ViewToWindow,
//:                               STRING ( 32 )  szEntityName,
//:                               SHORT bCommit,
//:                               STRING ( 256 ) szComment )
public int 
AcceptSubLC_TemporalSubobject( View     ViewToWindow,
                               String   szEntityName,
                               int      bCommit,
                               String   szComment )
{

   return( 0 );
//    /*VIEW wWebXfer REGISTERED AS wWebXfer
//    VIEW mSubLC   REGISTERED AS mSubLC
//    VIEW mTempLC  BASED ON LOD  mSubLC
//    STRING ( 32 )  szEntitySection
//    STRING ( 32 )  szEntityStatement
//    STRING ( 300 ) szFullComment
//    STRING ( 1 )   szSelected
//    INTEGER lID
//    SHORT   nRC
//    IF szEntityName != "" AND mSubLC != 0
//       IF szEntityName = "SI_FirstAidSection" OR
//          szEntityName = "SI_PrecautionarySection" OR
//          szEntityName = "SI_HazardSection" OR
//          szEntityName = "SI_UsageList"
//          szEntitySection = szEntityName
//          CreateViewFromView( mTempLC, mSubLC )
//          IF szEntitySection = "SI_UsageList"
//             nRC = SetCursorFirstEntity( mTempLC, szEntitySection, "" )
//             LOOP WHILE nRC = 0
//                AcceptSubobject( mTempLC, szEntitySection )
//                nRC = SetCursorFirstEntityByEntityCsr( mTempLC, "S_Usage", mTempLC, szEntitySection, "" )
//                IF nRC = 0
//                   SetMatchingAttributesByName( mTempLC, "S_Usage",
//                                                mTempLC, szEntitySection, zSET_NOTNULL )
//                   GetStringFromAttribute( szSelected, mTempLC, szEntitySection, "Selected" )
//                   mTempLC.S_Usage.Selected = szSelected
//                END
//                ExcludeEntity( mTempLC, szEntitySection, zREPOS_NONE )
//                nRC = SetCursorFirstEntity( mTempLC, szEntitySection, "" )
//             END
//          ELSE
//             IF szEntitySection = "SI_FirstAidSection"
//                szEntityStatement = "S_FirstAidStatement"
//             ELSE
//             IF szEntitySection = "SI_PrecautionarySection"
//                szEntityStatement = "S_PrecautionaryStatement"
//             ELSE
//             IF szEntitySection = "SI_HazardSection"
//                szEntityStatement = "S_HazardStatement"
//             END
//             END
//             END
//             nRC = SetCursorFirstEntity( mTempLC, szEntitySection, "" )
//             LOOP WHILE nRC = 0
//                GetIntegerFromAttribute( lID, mTempLC, szEntitySection, "ID" )
//                SetCursorFirstEntityByInteger( mTempLC, "S_GeneralSection", "ID", lID, "" )
//                nRC = AcceptSubobject( mTempLC, szEntitySection )
//             // szFullComment = "AcceptSubobject Prior to second Loop ====> " + szEntitySection + "." + szEntityStatement + "  RC: "
//             // TraceLineI( szFullComment, nRC )
//                SetMatchingAttributesByName( mTempLC, "S_GeneralSection",
//                                             mTempLC, szEntitySection, zSET_NOTNULL )
//             // DisplayEntityInstance( mTempLC, szEntitySection )
//                nRC = SetCursorFirstEntity( mTempLC, szEntityStatement, "" )
//                LOOP WHILE nRC = 0
//                // DisplayEntityInstance( mTempLC, szEntityStatement )
//                // TraceLineS( "", "" )
//                // nRC = SetCursorFirstEntityByEntityCsr( mTempLC, "S_GeneralStatement", mTempLC, szEntityStatement, "" )
//                   GetIntegerFromAttribute( lID, mTempLC, szEntityStatement, "ID" )
//                   nRC = SetCursorFirstEntityByInteger( mTempLC, "S_GeneralStatement", "ID", lID, "" )
//                   IF nRC = 0
//                      // We are situated on the right statement and thereby the right section.
//                   // TraceLineS( "Situated on the right statement", " and thereby the right section" )
//                   // DisplayEntityInstance( mTempLC, "S_GeneralStatement" )
//                   // TraceLineS( "", "" )
//                      SetMatchingAttributesByName( mTempLC, "S_GeneralStatement",
//                                                   mTempLC, szEntityStatement, zSET_NOTNULL )
//                      GetStringFromAttribute( szSelected, mTempLC, szEntityStatement, "Selected" )
//                      IF szSelected = ""
//                         szSelected = "N"
//                      END
//                      mTempLC.S_GeneralStatement.Selected = szSelected
//                   // TraceLineS( "After set matching ////////////////* selected: ", szSelected )
//                   // DisplayEntityInstance( mTempLC, "S_GeneralStatement" )
//                   // TraceLineS( "", "" )
//                   ELSE
//                      szFullComment = "AcceptSubobject SetCursorFirstEntityByInteger FAILURE ====> " + szEntityStatement + "  ID: "
//                      TraceLineI( szFullComment, lID )
//                      MessageSend( ViewToWindow, "", szEntitySection, szFullComment,
//                                   zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//                   END
//                   nRC = SetCursorNextEntity( mTempLC, szEntityStatement, "" )
//                END
//                ExcludeEntity( mTempLC, szEntitySection, zREPOS_NONE )
//                nRC = SetCursorFirstEntity( mTempLC, szEntitySection, "" )
//             END
//          END
//          DropView( mTempLC )
//       ELSE
//          AcceptSubobject( mSubLC, szEntityName )
//       END
//       IF bCommit = TRUE
//          MiSetInstanceUpdateFlag( mSubLC, TRUE )  // here because the update flag was not always set automatically
//          COMMIT mSubLC
//          szFullComment = "Accept/Commit"
//       ELSE
//          szFullComment = "Accept"
//       END
//       szFullComment = szFullComment + " mSubLC." + szEntityName + " ========================> "
//       wWebXfer.Root.CurrentTemporalEntity = ""
//    // MessageSend( ViewToWindow, "", szEntityName, szFullComment,
//    //              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//    ELSE
//       szFullComment = "Nothing to accept mSubLC." + szEntityName + "  //////////////////////////////*> "
//    END*/
// // TraceLineS( szFullComment, szComment )
// END
} 


//:GLOBAL OPERATION
//:AcceptMasLC_TemporalSubobject( VIEW  ViewToWindow,
//:                               STRING ( 32 )  szEntityName,
//:                               SHORT bCommit,
//:                               STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptMasLC_TemporalSubobject( View     ViewToWindow,
                               String   szEntityName,
                               int      bCommit,
                               String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   int      lTempInteger_0 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mMasLC != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mMasLC ) != null )
   { 

      //:AcceptSubobject( mMasLC, szEntityName )
      AcceptSubobject( mMasLC, szEntityName );
      //:IF bCommit = TRUE
      if ( bCommit == TRUE )
      { 
         //:COMMIT mMasLC
         RESULT = CommitObjectInstance( mMasLC );
         //:szFullComment = "Accept/Commit"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept/Commit", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szFullComment = "Accept"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
      } 

      //:END

      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = szFullComment + " mMasLC." + szEntityName + "  =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " mMasLC.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, "  =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to accept mMasLC." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to accept mMasLC.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   //:IF bCommit = TRUE AND ZeidonStringFind( szEntityName, 1, "MI_" ) > 0
   lTempInteger_0 = ZeidonStringFind( szEntityName, 1, "MI_" );
   if ( bCommit == TRUE && lTempInteger_0 > 0 )
   { 
      //:DisplayObjectInstance( mMasLC, szEntityName, "" )
      DisplayObjectInstance( mMasLC, szEntityName, "" );
   } 

   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:AcceptMasProd_TemporalSubobject( VIEW  ViewToWindow,
//:                                 STRING ( 32 )  szEntityName,
//:                                 SHORT bCommit,
//:                                 STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptMasProd_TemporalSubobject( View     ViewToWindow,
                                 String   szEntityName,
                                 int      bCommit,
                                 String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mMasProd != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mMasProd ) != null )
   { 

      //:AcceptSubobject( mMasProd, szEntityName )
      AcceptSubobject( mMasProd, szEntityName );
      //:IF bCommit = TRUE
      if ( bCommit == TRUE )
      { 
         //:COMMIT mMasProd
         RESULT = CommitObjectInstance( mMasProd );
         //:szFullComment = "Accept/Commit"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept/Commit", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szFullComment = "Accept"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
      } 

      //:END

      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = szFullComment + " mMasProd." + szEntityName + "  =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " mMasProd.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, "  =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to accept mMasProd." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to accept mMasProd.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:AcceptEPA_TemporalSubobject( VIEW  ViewToWindow,
//:                             STRING ( 32 )  szEntityName,
//:                             SHORT bCommit,
//:                             STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptEPA_TemporalSubobject( View     ViewToWindow,
                             String   szEntityName,
                             int      bCommit,
                             String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mEPA != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mEPA ) != null )
   { 

      //:AcceptSubobject( mEPA, szEntityName )
      AcceptSubobject( mEPA, szEntityName );
      //:IF bCommit = TRUE
      if ( bCommit == TRUE )
      { 
         //:COMMIT mEPA
         RESULT = CommitObjectInstance( mEPA );
         //:szFullComment = "Accept/Commit"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept/Commit", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
         //:ELSE
      } 
      else
      { 
         //:szFullComment = "Accept"
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringCopy( sb_szFullComment, 1, 0, "Accept", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
      } 

      //:END

      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = szFullComment + " mEPA." + szEntityName + "  =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " mEPA.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, "  =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to accept mEPA." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to accept mEPA.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:AcceptTemplateTemporalSubobject( VIEW  ViewToWindow,
//:                                 STRING ( 32 )  szEntityName,
//:                                 SHORT bCommit,
//:                                 STRING ( 256 ) szComment )
public int 
AcceptTemplateTemporalSubobject( View     ViewToWindow,
                                 String   szEntityName,
                                 int      bCommit,
                                 String   szComment )
{

   return( 0 );
//    /*VIEW wWebXfer REGISTERED AS wWebXfer
//    VIEW mTempl   REGISTERED AS mTempl
//    STRING ( 300 ) szFullComment
//    IF szEntityName != "" AND mTempl != 0
//       AcceptSubobject( mTempl, szEntityName )
//       IF bCommit = TRUE
//          COMMIT mTempl
//          szFullComment = "Accept/Commit"
//       ELSE
//          szFullComment = "Accept"
//       END
//       wWebXfer.Root.CurrentTemporalEntity = ""
//       szFullComment = szFullComment + " mTempl." + szEntityName + "  =================================> "
//    ELSE
//       szFullComment = "Nothing to accept mTempl." + szEntityName + " //////////////////////////////*> "
//    END
//    TraceLineS( szFullComment, szComment )*/
// END
} 


//:GLOBAL OPERATION
//:CreateCurrentTemporalVersion( VIEW  ViewToWindow,
//:                              SHORT nPos,
//:                              STRING ( 64 )  szViewName,
//:                              STRING ( 32 )  szEntityName,
//:                              STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public void 
CreateCurrentTemporalVersion( View     ViewToWindow,
                              int      nPos,
                              String   szViewName,
                              String   szEntityName,
                              String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mOI
   zVIEW    mOI = new zVIEW( );
   //:// VIEW mSubLC   BASED ON LOD  mSubLC
   //:STRING ( 64 )  szViewName1
   String   szViewName1 = null;
   //:STRING ( 32 )  szEntityName1
   String   szEntityName1 = null;
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:GET VIEW mOI NAMED szViewName
   RESULT = GetViewByName( mOI, szViewName, ViewToWindow, zLEVEL_TASK );

   //:szViewName1 = wWebXfer.Root.CurrentTemporalViewName
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
   StringBuilder sb_szViewName1;
   if ( szViewName1 == null )
      sb_szViewName1 = new StringBuilder( 32 );
   else
      sb_szViewName1 = new StringBuilder( szViewName1 );
       GetVariableFromAttribute( sb_szViewName1, mi_lTempInteger_0, 'S', 65, wWebXfer, "Root", "CurrentTemporalViewName", "", 0 );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );
   szViewName1 = sb_szViewName1.toString( );}
   //:szEntityName1 = wWebXfer.Root.CurrentTemporalEntity
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
   StringBuilder sb_szEntityName1;
   if ( szEntityName1 == null )
      sb_szEntityName1 = new StringBuilder( 32 );
   else
      sb_szEntityName1 = new StringBuilder( szEntityName1 );
       GetVariableFromAttribute( sb_szEntityName1, mi_lTempInteger_1, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );
   szEntityName1 = sb_szEntityName1.toString( );}
   //:IF szViewName1 != "" OR szEntityName1 != ""
   if ( ZeidonStringCompare( szViewName1, 1, 0, "", 1, 0, 65 ) != 0 || ZeidonStringCompare( szEntityName1, 1, 0, "", 1, 0, 33 ) != 0 )
   { 
      //:szFullComment = "CreateCurrentTemporalVersion found unexpected Temporal Version of: " + szViewName1
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "CreateCurrentTemporalVersion found unexpected Temporal Version of: ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName1, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
      //:szFullComment = szFullComment + "." + szEntityName1
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName1, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
      //:szFullComment = szFullComment + "  while creating: " + szViewName
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, "  while creating: ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
      //:szFullComment = szFullComment + "." + szEntityName
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
      //:IF MiEntityVersioned( mOI, szEntityName ) > 0
      lTempInteger_2 = MiEntityVersioned( mOI, szEntityName );
      if ( lTempInteger_2 > 0 )
      { 
         //:szFullComment = szFullComment + " @@@@@@@@@@  Entity already versioned  @@@@@@@@@@-->> "
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringConcat( sb_szFullComment, 1, 0, " @@@@@@@@@@  Entity already versioned  @@@@@@@@@@-->> ", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
         //:TraceLineS( szFullComment, szComment )
         TraceLineS( szFullComment, szComment );
         //:RETURN
         return;
         //:ELSE
      } 
      else
      { 
         //:szFullComment = szFullComment + " ##########  ERROR  ###############-->> "
          {StringBuilder sb_szFullComment;
         if ( szFullComment == null )
            sb_szFullComment = new StringBuilder( 32 );
         else
            sb_szFullComment = new StringBuilder( szFullComment );
                  ZeidonStringConcat( sb_szFullComment, 1, 0, " ##########  ERROR  ###############-->> ", 1, 0, 301 );
         szFullComment = sb_szFullComment.toString( );}
         //:TraceLineS( szFullComment, szComment )
         TraceLineS( szFullComment, szComment );
      } 

      //:END
   } 


   //:END

   //:IF nPos = 0 AND CheckExistenceOfEntity( mOI, szEntityName ) = 0
   lTempInteger_3 = CheckExistenceOfEntity( mOI, szEntityName );
   if ( nPos == 0 && lTempInteger_3 == 0 )
   { 

      //:CreateTemporalSubobjectVersion( mOI, szEntityName )
      CreateTemporalSubobjectVersion( mOI, szEntityName );
      //:wWebXfer.Root.CurrentUpdate = "Y"
      SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "Y" );
      //:szFullComment = "CreateTemporalSubobject "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "CreateTemporalSubobject ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:IF nPos = 0
      if ( nPos == 0 )
      { 
         //:nPos = zPOS_AFTER
         nPos = zPOS_AFTER;
      } 

      //:END

      //:CreateTemporalEntity( mOI, szEntityName, nPos )
      CreateTemporalEntity( mOI, szEntityName, nPos );
      //:wWebXfer.Root.CurrentUpdate = "N"
      SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "N" );
      //:szFullComment = "CreateTemporalEntity "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "CreateTemporalEntity ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:szFullComment = szFullComment + szViewName + "." + szEntityName + " =========================> "
    {StringBuilder sb_szFullComment;
   if ( szFullComment == null )
      sb_szFullComment = new StringBuilder( 32 );
   else
      sb_szFullComment = new StringBuilder( szFullComment );
      ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName, 1, 0, 301 );
   szFullComment = sb_szFullComment.toString( );}
    {StringBuilder sb_szFullComment;
   if ( szFullComment == null )
      sb_szFullComment = new StringBuilder( 32 );
   else
      sb_szFullComment = new StringBuilder( szFullComment );
      ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
   szFullComment = sb_szFullComment.toString( );}
    {StringBuilder sb_szFullComment;
   if ( szFullComment == null )
      sb_szFullComment = new StringBuilder( 32 );
   else
      sb_szFullComment = new StringBuilder( szFullComment );
      ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
   szFullComment = sb_szFullComment.toString( );}
    {StringBuilder sb_szFullComment;
   if ( szFullComment == null )
      sb_szFullComment = new StringBuilder( 32 );
   else
      sb_szFullComment = new StringBuilder( szFullComment );
      ZeidonStringConcat( sb_szFullComment, 1, 0, " =========================> ", 1, 0, 301 );
   szFullComment = sb_szFullComment.toString( );}
   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );

   //:// IF szViewName = "mSPLDef" AND szEntityName = "SubregPhysicalLabelDef"
   //://    GET VIEW mSubLC NAMED "mSubLC"
   //://    SyncPhysicalLabel( mOI, mSubLC, TRUE )  // TRUE ==> from SubLC to SPLDef
   //:// END

   //:wWebXfer.Root.CurrentTemporalViewName = szViewName
   SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalViewName", szViewName );
   //:wWebXfer.Root.CurrentTemporalEntity = szEntityName
   SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", szEntityName );
   return;
// END
} 


//:GLOBAL OPERATION
//:AcceptCurrentTemporalSubobject( VIEW  ViewToWindow,
//:                                SHORT bCommit,
//:                                STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
AcceptCurrentTemporalSubobject( View     ViewToWindow,
                                int      bCommit,
                                String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 32 )  szFormValidationDLL
   String   szFormValidationDLL = null;
   //:STRING ( 32 )  szFormValidationOperation
   String   szFormValidationOperation = null;
   //:STRING ( 64 )  szViewName
   String   szViewName = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   //:SHORT  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   int      lTempInteger_2 = 0;
   int      lTempInteger_3 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = 0
   nRC = 0;
   //:IF wWebXfer != 0
   if ( getView( wWebXfer ) != null )
   { 

      //:szFormValidationDLL = wWebXfer.Root.FormValidationDLL
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szFormValidationDLL;
      if ( szFormValidationDLL == null )
         sb_szFormValidationDLL = new StringBuilder( 32 );
      else
         sb_szFormValidationDLL = new StringBuilder( szFormValidationDLL );
             GetVariableFromAttribute( sb_szFormValidationDLL, mi_lTempInteger_0, 'S', 33, wWebXfer, "Root", "FormValidationDLL", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szFormValidationDLL = sb_szFormValidationDLL.toString( );}
      //:szFormValidationOperation = wWebXfer.Root.FormValidationOperation
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szFormValidationOperation;
      if ( szFormValidationOperation == null )
         sb_szFormValidationOperation = new StringBuilder( 32 );
      else
         sb_szFormValidationOperation = new StringBuilder( szFormValidationOperation );
             GetVariableFromAttribute( sb_szFormValidationOperation, mi_lTempInteger_1, 'S', 33, wWebXfer, "Root", "FormValidationOperation", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szFormValidationOperation = sb_szFormValidationOperation.toString( );}
      //:IF szFormValidationDLL != "" AND szFormValidationOperation != ""
      if ( ZeidonStringCompare( szFormValidationDLL, 1, 0, "", 1, 0, 33 ) != 0 && ZeidonStringCompare( szFormValidationOperation, 1, 0, "", 1, 0, 33 ) != 0 )
      { 
         //:nRC = CallDialogOperation( ViewToWindow, szFormValidationDLL, szFormValidationOperation )
         nRC = m_ZDRVROPR.CallDialogOperation( ViewToWindow, szFormValidationDLL, szFormValidationOperation );
         //:// TraceLine( "CallDialogOperation: %s.%s  RC: %d", szFormValidationDLL, szFormValidationOperation, nRC );
         //:IF nRC != 0
         if ( nRC != 0 )
         { 
            //:RETURN nRC
            if(8==8)return( nRC );
         } 

         //:END

         //:wWebXfer.Root.FormValidationDLL = ""
         SetAttributeFromString( wWebXfer, "Root", "FormValidationDLL", "" );
         //:wWebXfer.Root.FormValidationOperation = ""
         SetAttributeFromString( wWebXfer, "Root", "FormValidationOperation", "" );
      } 

      //:END

      //:szViewName = wWebXfer.Root.CurrentTemporalViewName
      {MutableInt mi_lTempInteger_2 = new MutableInt( lTempInteger_2 );
      StringBuilder sb_szViewName;
      if ( szViewName == null )
         sb_szViewName = new StringBuilder( 32 );
      else
         sb_szViewName = new StringBuilder( szViewName );
             GetVariableFromAttribute( sb_szViewName, mi_lTempInteger_2, 'S', 65, wWebXfer, "Root", "CurrentTemporalViewName", "", 0 );
      lTempInteger_2 = mi_lTempInteger_2.intValue( );
      szViewName = sb_szViewName.toString( );}
      //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
      {MutableInt mi_lTempInteger_3 = new MutableInt( lTempInteger_3 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_3, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
      lTempInteger_3 = mi_lTempInteger_3.intValue( );
      szEntityName = sb_szEntityName.toString( );}
      //:szFullComment = "Accepting " + szViewName + "." + szEntityName +  "//////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Accepting ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, "//////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:IF szViewName = "mMasLC"
      if ( ZeidonStringCompare( szViewName, 1, 0, "mMasLC", 1, 0, 65 ) == 0 )
      { 
         //:AcceptMasLC_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment )
         AcceptMasLC_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment );
         //:ELSE
      } 
      else
      { 
         //:IF szViewName = "mSubLC"
         if ( ZeidonStringCompare( szViewName, 1, 0, "mSubLC", 1, 0, 65 ) == 0 )
         { 
            //:AcceptSubLC_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment )
            AcceptSubLC_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment );
            //:ELSE
         } 
         else
         { 
            //:IF szViewName = "mSPLDef"
            if ( ZeidonStringCompare( szViewName, 1, 0, "mSPLDef", 1, 0, 65 ) == 0 )
            { 
               //:AcceptSPLDef_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment )
               AcceptSPLDef_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment );
               //:ELSE
            } 
            else
            { 
               //:IF szViewName = "mMasProd"
               if ( ZeidonStringCompare( szViewName, 1, 0, "mMasProd", 1, 0, 65 ) == 0 )
               { 
                  //:AcceptMasProd_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment )
                  AcceptMasProd_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment );
                  //:ELSE
               } 
               else
               { 
                  //:IF szViewName = "mEPA"
                  if ( ZeidonStringCompare( szViewName, 1, 0, "mEPA", 1, 0, 65 ) == 0 )
                  { 
                     //:AcceptEPA_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment )
                     AcceptEPA_TemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szViewName = "mTempl"
                     if ( ZeidonStringCompare( szViewName, 1, 0, "mTempl", 1, 0, 65 ) == 0 )
                     { 
                        //:AcceptTemplateTemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment )
                        AcceptTemplateTemporalSubobject( ViewToWindow, szEntityName, bCommit, szComment );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szFullComment = "Nothing to accept " + szViewName + "." + szEntityName +  "//////////////////////////////*> "
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to accept ", 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName, 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, "//////////////////////////////*> ", 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
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

      //:TraceLineS( szFullComment, szComment )
      TraceLineS( szFullComment, szComment );
      //:wWebXfer.Root.CurrentUpdate = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
      //:wWebXfer.Root.CurrentTemporalViewName = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalViewName", "" );
      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:GLOBAL OPERATION
//:CancelSPLDef_TemporalSubobject( VIEW  ViewToWindow,
//:                                STRING ( 32 ) szEntityName,
//:                                STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelSPLDef_TemporalSubobject( View     ViewToWindow,
                                String   szEntityName,
                                String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSPLDef  REGISTERED AS mSPLDef
   zVIEW    mSPLDef = new zVIEW( );
   //:VIEW mTempPL  BASED ON LOD  mSPLDef
   zVIEW    mTempPL = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   //:SHORT  nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSPLDef, "mSPLDef", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mSPLDef != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mSPLDef ) != null )
   { 

      //:IF szEntityName = "SPLDI_FirstAidSection" OR
      //:   szEntityName = "SPLDI_PrecautionarySection" OR
      //:   szEntityName = "SPLDI_HazardSection"
      if ( ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_FirstAidSection", 1, 0, 33 ) == 0 || ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_PrecautionarySection", 1, 0, 33 ) == 0 ||
           ZeidonStringCompare( szEntityName, 1, 0, "SPLDI_HazardSection", 1, 0, 33 ) == 0 )
      { 

         //:CreateViewFromView( mTempPL, mSPLDef )
         CreateViewFromView( mTempPL, mSPLDef );
         //:nRC = SetCursorFirstEntity( mTempPL, szEntityName, "" )
         nRC = SetCursorFirstEntity( mTempPL, szEntityName, "" );
         //:LOOP WHILE nRC = 0
         while ( nRC == 0 )
         { 
            //:CancelSubobject( mTempPL, szEntityName )
            CancelSubobject( mTempPL, szEntityName );
            //:ExcludeEntity( mTempPL, szEntityName, zREPOS_NONE )
            ExcludeEntity( mTempPL, szEntityName, zREPOS_NONE );
            //:nRC = SetCursorNextEntity( mTempPL, szEntityName, "" )
            nRC = SetCursorNextEntity( mTempPL, szEntityName, "" );
         } 

         //:END

         //:DropView( mTempPL )
         DropView( mTempPL );
         //:ELSE
      } 
      else
      { 
         //:CancelSubobject( mSPLDef, szEntityName )
         CancelSubobject( mSPLDef, szEntityName );
      } 

      //:END

      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = "Cancel mSPLDef." + szEntityName + " =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Cancel mSPLDef.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to cancel mSPLDef." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to cancel mSPLDef.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CancelSubLC_TemporalSubobject( VIEW  ViewToWindow,
//:                               STRING ( 32 ) szEntityName,
//:                               STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelSubLC_TemporalSubobject( View     ViewToWindow,
                               String   szEntityName,
                               String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mSubLC   REGISTERED AS mSubLC
   zVIEW    mSubLC = new zVIEW( );
   //:VIEW mTempLC  BASED ON LOD  mSubLC
   zVIEW    mTempLC = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   //:SHORT  nRC
   int      nRC = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mSubLC, "mSubLC", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mSubLC != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mSubLC ) != null )
   { 

      //:IF szEntityName = "SI_FirstAidSection" OR
      //:   szEntityName = "SI_PrecautionarySection" OR
      //:   szEntityName = "SI_HazardSection"
      if ( ZeidonStringCompare( szEntityName, 1, 0, "SI_FirstAidSection", 1, 0, 33 ) == 0 || ZeidonStringCompare( szEntityName, 1, 0, "SI_PrecautionarySection", 1, 0, 33 ) == 0 ||
           ZeidonStringCompare( szEntityName, 1, 0, "SI_HazardSection", 1, 0, 33 ) == 0 )
      { 

         //:CreateViewFromView( mTempLC, mSubLC )
         CreateViewFromView( mTempLC, mSubLC );
         //:nRC = SetCursorFirstEntity( mTempLC, szEntityName, "" )
         nRC = SetCursorFirstEntity( mTempLC, szEntityName, "" );
         //:LOOP WHILE nRC = 0
         while ( nRC == 0 )
         { 
            //:CancelSubobject( mTempLC, szEntityName )
            CancelSubobject( mTempLC, szEntityName );
            //:ExcludeEntity( mTempLC, szEntityName, zREPOS_NONE )
            ExcludeEntity( mTempLC, szEntityName, zREPOS_NONE );
            //:nRC = SetCursorNextEntity( mTempLC, szEntityName, "" )
            nRC = SetCursorNextEntity( mTempLC, szEntityName, "" );
         } 

         //:END

         //:DropView( mTempLC )
         DropView( mTempLC );
         //:ELSE
      } 
      else
      { 
         //:CancelSubobject( mSubLC, szEntityName )
         CancelSubobject( mSubLC, szEntityName );
      } 

      //:END

      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = "Cancel mSubLC." + szEntityName + " =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Cancel mSubLC.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to cancel mSubLC." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to cancel mSubLC.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CancelMasLC_TemporalSubobject( VIEW  ViewToWindow,
//:                               STRING ( 32 ) szEntityName,
//:                               STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelMasLC_TemporalSubobject( View     ViewToWindow,
                               String   szEntityName,
                               String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasLC   REGISTERED AS mMasLC
   zVIEW    mMasLC = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasLC, "mMasLC", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mMasLC != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mMasLC ) != null )
   { 

      //:CancelSubobject( mMasLC, szEntityName )
      CancelSubobject( mMasLC, szEntityName );
      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = "Cancel mMasLC." + szEntityName + " =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Cancel mMasLC.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to cancel mMasLC." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to cancel mMasLC.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CancelMasProd_TemporalSubobject( VIEW  ViewToWindow,
//:                                 STRING ( 32 ) szEntityName,
//:                                 STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelMasProd_TemporalSubobject( View     ViewToWindow,
                                 String   szEntityName,
                                 String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mMasProd REGISTERED AS mMasProd
   zVIEW    mMasProd = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mMasProd, "mMasProd", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mMasProd != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mMasProd ) != null )
   { 

      //:CancelSubobject( mMasProd, szEntityName )
      CancelSubobject( mMasProd, szEntityName );
      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = "Cancel mMasProd." + szEntityName + " =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Cancel mMasProd.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to cancel mMasProd." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to cancel mMasProd.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CancelEPA_TemporalSubobject( VIEW  ViewToWindow,
//:                             STRING ( 32 ) szEntityName,
//:                             STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelEPA_TemporalSubobject( View     ViewToWindow,
                             String   szEntityName,
                             String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:VIEW mEPA     REGISTERED AS mEPA
   zVIEW    mEPA = new zVIEW( );
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );
   RESULT = GetViewByName( mEPA, "mEPA", ViewToWindow, zLEVEL_TASK );

   //:IF szEntityName != "" AND mEPA != 0
   if ( ZeidonStringCompare( szEntityName, 1, 0, "", 1, 0, 33 ) != 0 && getView( mEPA ) != null )
   { 

      //:CancelSubobject( mEPA, szEntityName )
      CancelSubobject( mEPA, szEntityName );
      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
      //:szFullComment = "Cancel mEPA." + szEntityName + " =================================> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Cancel mEPA.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " =================================> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}

      //:ELSE
   } 
   else
   { 

      //:szFullComment = "Nothing to cancel mEPA." + szEntityName + " //////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to cancel mEPA.", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, " //////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
   } 


   //:END

   //:TraceLineS( szFullComment, szComment )
   TraceLineS( szFullComment, szComment );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CancelTemplateTemporalSubobject( VIEW  ViewToWindow,
//:                                 STRING ( 32 ) szEntityName,
//:                                 STRING ( 256 ) szComment )
public int 
CancelTemplateTemporalSubobject( View     ViewToWindow,
                                 String   szEntityName,
                                 String   szComment )
{

   return( 0 );
//    /*VIEW wWebXfer REGISTERED AS wWebXfer
//    VIEW mTempl   REGISTERED AS mTempl
//    STRING ( 300 ) szFullComment
//    IF szEntityName != "" AND mTempl != 0
//       CancelSubobject( mTempl, szEntityName )
//       wWebXfer.Root.CurrentTemporalEntity = ""
//       szFullComment = "Cancel mTempl." + szEntityName + " =================================> "
//    ELSE
//       szFullComment = "Nothing to cancel mTempl." + szEntityName + " //////////////////////////////*> "
//    END
//    TraceLineS( szFullComment, szComment )*/
// END
} 


//:GLOBAL OPERATION
//:CancelCurrentTemporalSubobject( VIEW  ViewToWindow,
//:                                STRING ( 256 ) szComment )

//:   VIEW wWebXfer REGISTERED AS wWebXfer
public int 
CancelCurrentTemporalSubobject( View     ViewToWindow,
                                String   szComment )
{
   zVIEW    wWebXfer = new zVIEW( );
   int      RESULT = 0;
   //:STRING ( 64 )  szViewName
   String   szViewName = null;
   //:STRING ( 32 )  szEntityName
   String   szEntityName = null;
   //:STRING ( 300 ) szFullComment
   String   szFullComment = null;
   //:SHORT  nRC
   int      nRC = 0;
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;

   RESULT = GetViewByName( wWebXfer, "wWebXfer", ViewToWindow, zLEVEL_TASK );

   //:nRC = 0
   nRC = 0;
   //:IF wWebXfer != 0
   if ( getView( wWebXfer ) != null )
   { 

      //:wWebXfer.Root.FormValidationDLL = ""
      SetAttributeFromString( wWebXfer, "Root", "FormValidationDLL", "" );
      //:wWebXfer.Root.FormValidationOperation = ""
      SetAttributeFromString( wWebXfer, "Root", "FormValidationOperation", "" );
      //:szViewName = wWebXfer.Root.CurrentTemporalViewName
      {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
      StringBuilder sb_szViewName;
      if ( szViewName == null )
         sb_szViewName = new StringBuilder( 32 );
      else
         sb_szViewName = new StringBuilder( szViewName );
             GetVariableFromAttribute( sb_szViewName, mi_lTempInteger_0, 'S', 65, wWebXfer, "Root", "CurrentTemporalViewName", "", 0 );
      lTempInteger_0 = mi_lTempInteger_0.intValue( );
      szViewName = sb_szViewName.toString( );}
      //:szEntityName = wWebXfer.Root.CurrentTemporalEntity
      {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
      StringBuilder sb_szEntityName;
      if ( szEntityName == null )
         sb_szEntityName = new StringBuilder( 32 );
      else
         sb_szEntityName = new StringBuilder( szEntityName );
             GetVariableFromAttribute( sb_szEntityName, mi_lTempInteger_1, 'S', 33, wWebXfer, "Root", "CurrentTemporalEntity", "", 0 );
      lTempInteger_1 = mi_lTempInteger_1.intValue( );
      szEntityName = sb_szEntityName.toString( );}

      //:szFullComment = "Canceling " + szViewName + "." + szEntityName +  "//////////////////////////////*> "
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringCopy( sb_szFullComment, 1, 0, "Canceling ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
       {StringBuilder sb_szFullComment;
      if ( szFullComment == null )
         sb_szFullComment = new StringBuilder( 32 );
      else
         sb_szFullComment = new StringBuilder( szFullComment );
            ZeidonStringConcat( sb_szFullComment, 1, 0, "//////////////////////////////*> ", 1, 0, 301 );
      szFullComment = sb_szFullComment.toString( );}
      //:IF szViewName = "mMasLC"
      if ( ZeidonStringCompare( szViewName, 1, 0, "mMasLC", 1, 0, 65 ) == 0 )
      { 
         //:CancelMasLC_TemporalSubobject( ViewToWindow, szEntityName, szComment )
         CancelMasLC_TemporalSubobject( ViewToWindow, szEntityName, szComment );
         //:ELSE
      } 
      else
      { 
         //:IF szViewName = "mSubLC"
         if ( ZeidonStringCompare( szViewName, 1, 0, "mSubLC", 1, 0, 65 ) == 0 )
         { 
            //:CancelSubLC_TemporalSubobject( ViewToWindow, szEntityName, szComment )
            CancelSubLC_TemporalSubobject( ViewToWindow, szEntityName, szComment );
            //:ELSE
         } 
         else
         { 
            //:IF szViewName = "mSPLDef"
            if ( ZeidonStringCompare( szViewName, 1, 0, "mSPLDef", 1, 0, 65 ) == 0 )
            { 
               //:CancelSPLDef_TemporalSubobject( ViewToWindow, szEntityName, szComment )
               CancelSPLDef_TemporalSubobject( ViewToWindow, szEntityName, szComment );
               //:ELSE
            } 
            else
            { 
               //:IF szViewName = "mMasProd"
               if ( ZeidonStringCompare( szViewName, 1, 0, "mMasProd", 1, 0, 65 ) == 0 )
               { 
                  //:CancelMasProd_TemporalSubobject( ViewToWindow, szEntityName, szComment )
                  CancelMasProd_TemporalSubobject( ViewToWindow, szEntityName, szComment );
                  //:ELSE
               } 
               else
               { 
                  //:IF szViewName = "mEPA"
                  if ( ZeidonStringCompare( szViewName, 1, 0, "mEPA", 1, 0, 65 ) == 0 )
                  { 
                     //:CancelEPA_TemporalSubobject( ViewToWindow, szEntityName, szComment )
                     CancelEPA_TemporalSubobject( ViewToWindow, szEntityName, szComment );
                     //:ELSE
                  } 
                  else
                  { 
                     //:IF szViewName = "mTempl"
                     if ( ZeidonStringCompare( szViewName, 1, 0, "mTempl", 1, 0, 65 ) == 0 )
                     { 
                        //:CancelTemplateTemporalSubobject( ViewToWindow, szEntityName, szComment )
                        CancelTemplateTemporalSubobject( ViewToWindow, szEntityName, szComment );
                        //:ELSE
                     } 
                     else
                     { 
                        //:szFullComment = "Nothing to cancel " + szViewName + "." + szEntityName +  "//////////////////////////////*> "
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringCopy( sb_szFullComment, 1, 0, "Nothing to cancel ", 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, szViewName, 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, ".", 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, szEntityName, 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
                         {StringBuilder sb_szFullComment;
                        if ( szFullComment == null )
                           sb_szFullComment = new StringBuilder( 32 );
                        else
                           sb_szFullComment = new StringBuilder( szFullComment );
                                                ZeidonStringConcat( sb_szFullComment, 1, 0, "//////////////////////////////*> ", 1, 0, 301 );
                        szFullComment = sb_szFullComment.toString( );}
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

      //:TraceLineS( szFullComment, szComment )
      TraceLineS( szFullComment, szComment );
      //:wWebXfer.Root.CurrentUpdate = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentUpdate", "" );
      //:wWebXfer.Root.CurrentTemporalViewName = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalViewName", "" );
      //:wWebXfer.Root.CurrentTemporalEntity = ""
      SetAttributeFromString( wWebXfer, "Root", "CurrentTemporalEntity", "" );
   } 


   //:END

   //:RETURN nRC
   return( nRC );
// END
} 


//:GLOBAL OPERATION
//:SetDynamicBannerName( VIEW  ViewToWindow,
//:                      STRING ( 32 ) szDialogName,
//:                      STRING ( 32 ) szBannerName )

//:   VIEW KZXMLPGO
public int 
SetDynamicBannerName( View     ViewToWindow,
                      String   szDialogName,
                      String   szBannerName )
{
   zVIEW    KZXMLPGO = new zVIEW( );
   //:STRING ( 64 )  szFullBannerName
   String   szFullBannerName = null;
   //:STRING ( 128 ) szTraceString
   String   szTraceString = null;
   int      RESULT = 0;


   //:GET VIEW KZXMLPGO NAMED "KZXMLPGO"
   RESULT = GetViewByName( KZXMLPGO, "KZXMLPGO", ViewToWindow, zLEVEL_TASK );

   //:IF KZXMLPGO != 0
   if ( getView( KZXMLPGO ) != null )
   { 
      //:// We should ever have at most one DynamicBannerName entity, so check first to see if it exists.
      //:SET CURSOR FIRST KZXMLPGO.DynamicBannerName
      RESULT = SetCursorFirstEntity( KZXMLPGO, "DynamicBannerName", "" );
      //:IF RESULT < zCURSOR_SET
      if ( RESULT < zCURSOR_SET )
      { 
         //:CREATE ENTITY KZXMLPGO.DynamicBannerName
         RESULT = CreateEntity( KZXMLPGO, "DynamicBannerName", zPOS_AFTER );
      } 

      //:END

      //:KZXMLPGO.DynamicBannerName.DialogName = szDialogName
      SetAttributeFromString( KZXMLPGO, "DynamicBannerName", "DialogName", szDialogName );
      //:szFullBannerName = "./include/ePammsBanner" + szBannerName + ".inc"
       {StringBuilder sb_szFullBannerName;
      if ( szFullBannerName == null )
         sb_szFullBannerName = new StringBuilder( 32 );
      else
         sb_szFullBannerName = new StringBuilder( szFullBannerName );
            ZeidonStringCopy( sb_szFullBannerName, 1, 0, "./include/ePammsBanner", 1, 0, 65 );
      szFullBannerName = sb_szFullBannerName.toString( );}
       {StringBuilder sb_szFullBannerName;
      if ( szFullBannerName == null )
         sb_szFullBannerName = new StringBuilder( 32 );
      else
         sb_szFullBannerName = new StringBuilder( szFullBannerName );
            ZeidonStringConcat( sb_szFullBannerName, 1, 0, szBannerName, 1, 0, 65 );
      szFullBannerName = sb_szFullBannerName.toString( );}
       {StringBuilder sb_szFullBannerName;
      if ( szFullBannerName == null )
         sb_szFullBannerName = new StringBuilder( 32 );
      else
         sb_szFullBannerName = new StringBuilder( szFullBannerName );
            ZeidonStringConcat( sb_szFullBannerName, 1, 0, ".inc", 1, 0, 65 );
      szFullBannerName = sb_szFullBannerName.toString( );}
      //:KZXMLPGO.DynamicBannerName.BannerName = szFullBannerName
      SetAttributeFromString( KZXMLPGO, "DynamicBannerName", "BannerName", szFullBannerName );
      //:szTraceString = "SetDynamicBanner: " + szFullBannerName
       {StringBuilder sb_szTraceString;
      if ( szTraceString == null )
         sb_szTraceString = new StringBuilder( 32 );
      else
         sb_szTraceString = new StringBuilder( szTraceString );
            ZeidonStringCopy( sb_szTraceString, 1, 0, "SetDynamicBanner: ", 1, 0, 129 );
      szTraceString = sb_szTraceString.toString( );}
       {StringBuilder sb_szTraceString;
      if ( szTraceString == null )
         sb_szTraceString = new StringBuilder( 32 );
      else
         sb_szTraceString = new StringBuilder( szTraceString );
            ZeidonStringConcat( sb_szTraceString, 1, 0, szFullBannerName, 1, 0, 129 );
      szTraceString = sb_szTraceString.toString( );}
      //:szTraceString = szTraceString + "  for Dialog: "
       {StringBuilder sb_szTraceString;
      if ( szTraceString == null )
         sb_szTraceString = new StringBuilder( 32 );
      else
         sb_szTraceString = new StringBuilder( szTraceString );
            ZeidonStringConcat( sb_szTraceString, 1, 0, "  for Dialog: ", 1, 0, 129 );
      szTraceString = sb_szTraceString.toString( );}
      //:TraceLineS( szTraceString, szDialogName )
      TraceLineS( szTraceString, szDialogName );
   } 


   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
public int 
InitializeSPLD_FromSLC( View     mSubLC,
                        View     mSPLDef )
{

   //:InitializeSPLD_FromSLC( VIEW mSubLC,
   //:                     VIEW mSPLDef )

   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "General", 2 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "General", 2 );
   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "Ingredients", 2 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "Ingredients", 2 );
   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "StorageDisposal", 2 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "StorageDisposal", 2 );
   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "DirectionsForUse", 2 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "DirectionsForUse", 2 );
   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "Marketing", 2 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "Marketing", 2 );
   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "HumanHazard", 1 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "HumanHazard", 1 );
   //:CopySLC_EntityToSPLD( mSubLC, mSPLDef, "Usage", 0 )
   CopySLC_EntityToSPLD( mSubLC, mSPLDef, "Usage", 0 );
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CopySLC_EntityToSPLD( VIEW  mSubLC,
//:                      VIEW  mSPLDef,
//:                      STRING ( 32 ) szEntity,
//:                      SHORT nFlag )

//:   STRING ( 32 ) szM_SectionName
public int 
CopySLC_EntityToSPLD( View     mSubLC,
                      View     mSPLDef,
                      String   szEntity,
                      int      nFlag )
{
   String   szM_SectionName = null;
   //:STRING ( 32 ) szS_SectionName
   String   szS_SectionName = null;
   //:STRING ( 32 ) szM_StatementName
   String   szM_StatementName = null;
   //:STRING ( 32 ) szS_StatementName
   String   szS_StatementName = null;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:IF nFlag = 2 OR nFlag = 1
   if ( nFlag == 2 || nFlag == 1 )
   { 

      //:// If the Flag is 1, we want to copy the Section entity (building the name).
      //:szM_SectionName = "S_" + szEntity + "Section"
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringCopy( sb_szM_SectionName, 1, 0, "S_", 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringConcat( sb_szM_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringConcat( sb_szM_SectionName, 1, 0, "Section", 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
      //:szS_SectionName = "SPLD_" + szEntity + "Section"
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringCopy( sb_szS_SectionName, 1, 0, "SPLD_", 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringConcat( sb_szS_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringConcat( sb_szS_SectionName, 1, 0, "Section", 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
      //:szM_StatementName = "S_" + szEntity + "Statement"
       {StringBuilder sb_szM_StatementName;
      if ( szM_StatementName == null )
         sb_szM_StatementName = new StringBuilder( 32 );
      else
         sb_szM_StatementName = new StringBuilder( szM_StatementName );
            ZeidonStringCopy( sb_szM_StatementName, 1, 0, "S_", 1, 0, 33 );
      szM_StatementName = sb_szM_StatementName.toString( );}
       {StringBuilder sb_szM_StatementName;
      if ( szM_StatementName == null )
         sb_szM_StatementName = new StringBuilder( 32 );
      else
         sb_szM_StatementName = new StringBuilder( szM_StatementName );
            ZeidonStringConcat( sb_szM_StatementName, 1, 0, szEntity, 1, 0, 33 );
      szM_StatementName = sb_szM_StatementName.toString( );}
       {StringBuilder sb_szM_StatementName;
      if ( szM_StatementName == null )
         sb_szM_StatementName = new StringBuilder( 32 );
      else
         sb_szM_StatementName = new StringBuilder( szM_StatementName );
            ZeidonStringConcat( sb_szM_StatementName, 1, 0, "Statement", 1, 0, 33 );
      szM_StatementName = sb_szM_StatementName.toString( );}
      //:szS_StatementName = "SPLD_" + szEntity + "Statement"
       {StringBuilder sb_szS_StatementName;
      if ( szS_StatementName == null )
         sb_szS_StatementName = new StringBuilder( 32 );
      else
         sb_szS_StatementName = new StringBuilder( szS_StatementName );
            ZeidonStringCopy( sb_szS_StatementName, 1, 0, "SPLD_", 1, 0, 33 );
      szS_StatementName = sb_szS_StatementName.toString( );}
       {StringBuilder sb_szS_StatementName;
      if ( szS_StatementName == null )
         sb_szS_StatementName = new StringBuilder( 32 );
      else
         sb_szS_StatementName = new StringBuilder( szS_StatementName );
            ZeidonStringConcat( sb_szS_StatementName, 1, 0, szEntity, 1, 0, 33 );
      szS_StatementName = sb_szS_StatementName.toString( );}
       {StringBuilder sb_szS_StatementName;
      if ( szS_StatementName == null )
         sb_szS_StatementName = new StringBuilder( 32 );
      else
         sb_szS_StatementName = new StringBuilder( szS_StatementName );
            ZeidonStringConcat( sb_szS_StatementName, 1, 0, "Statement", 1, 0, 33 );
      szS_StatementName = sb_szS_StatementName.toString( );}
      //:nRC = SetCursorFirstEntity( mSubLC, szM_SectionName, "" )
      nRC = SetCursorFirstEntity( mSubLC, szM_SectionName, "" );
      //:LOOP WHILE nRC >= 0
      while ( nRC >= 0 )
      { 

         //:CreateEntity( mSPLDef, szS_SectionName, zPOS_AFTER )
         CreateEntity( mSPLDef, szS_SectionName, zPOS_AFTER );
         //:SetMatchingAttributesByName( mSPLDef, szS_SectionName,
         //:                             mSubLC, szM_SectionName, zSET_NOTNULL )
         SetMatchingAttributesByName( mSPLDef, szS_SectionName, mSubLC, szM_SectionName, zSET_NOTNULL );
         //:SetAttributeFromString( mSPLDef, szS_SectionName, "Selected", "Y" )
         SetAttributeFromString( mSPLDef, szS_SectionName, "Selected", "Y" );
         //:IncludeSubobjectFromSubobject( mSPLDef, szM_SectionName,
         //:                               mSubLC, szM_SectionName, zPOS_BEFORE )
         IncludeSubobjectFromSubobject( mSPLDef, szM_SectionName, mSubLC, szM_SectionName, zPOS_BEFORE );
         //:// If the Flag is 2, we want to copy the Statement entity (building the name).
         //:IF nFlag = 2
         if ( nFlag == 2 )
         { 

            //:nRC = SetCursorFirstEntity( mSubLC, szM_StatementName, "" )
            nRC = SetCursorFirstEntity( mSubLC, szM_StatementName, "" );
            //:LOOP WHILE nRC >= 0
            while ( nRC >= 0 )
            { 

               //:CreateEntity( mSPLDef, szS_StatementName, zPOS_AFTER )
               CreateEntity( mSPLDef, szS_StatementName, zPOS_AFTER );
               //:SetMatchingAttributesByName( mSPLDef, szS_StatementName,
               //:                             mSubLC, szM_StatementName, zSET_NOTNULL )
               SetMatchingAttributesByName( mSPLDef, szS_StatementName, mSubLC, szM_StatementName, zSET_NOTNULL );
               //:SetAttributeFromString( mSPLDef, szS_StatementName, "Selected", "Y" )
               SetAttributeFromString( mSPLDef, szS_StatementName, "Selected", "Y" );
               //:IncludeSubobjectFromSubobject( mSPLDef, szM_StatementName,
               //:                               mSubLC, szM_StatementName, zPOS_BEFORE )
               IncludeSubobjectFromSubobject( mSPLDef, szM_StatementName, mSubLC, szM_StatementName, zPOS_BEFORE );

               //:nRC = SetCursorNextEntity( mSubLC, szM_StatementName, "" )
               nRC = SetCursorNextEntity( mSubLC, szM_StatementName, "" );
            } 

            //:END
         } 

         //:END

         //:nRC = SetCursorNextEntity( mSubLC, szM_SectionName, "" )
         nRC = SetCursorNextEntity( mSubLC, szM_SectionName, "" );
      } 

      //:END

      //:ELSE
   } 
   else
   { 

      //:// If the Flag is 0, we want to copy the specified entity.  Even though the names
      //:// are not quite right, in order to maintain parallel code with the above IF, keep
      //:// the names as in the code above.
      //:szM_SectionName = "S_" + szEntity
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringCopy( sb_szM_SectionName, 1, 0, "S_", 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringConcat( sb_szM_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
      //:szS_SectionName = "SPLD_" + szEntity
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringCopy( sb_szS_SectionName, 1, 0, "SPLD_", 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringConcat( sb_szS_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
      //:nRC = SetCursorFirstEntity( mSubLC, szM_SectionName, "" )
      nRC = SetCursorFirstEntity( mSubLC, szM_SectionName, "" );
      //:LOOP WHILE nRC >= 0
      while ( nRC >= 0 )
      { 

         //:CreateEntity( mSPLDef, szS_SectionName, zPOS_AFTER )
         CreateEntity( mSPLDef, szS_SectionName, zPOS_AFTER );
         //:SetAttributeFromString( mSPLDef, szS_SectionName, "Selected", "Y" )
         SetAttributeFromString( mSPLDef, szS_SectionName, "Selected", "Y" );
         //:SetMatchingAttributesByName( mSPLDef, szS_SectionName,
         //:                             mSubLC, szM_SectionName, zSET_NOTNULL )
         SetMatchingAttributesByName( mSPLDef, szS_SectionName, mSubLC, szM_SectionName, zSET_NOTNULL );
         //:IncludeSubobjectFromSubobject( mSPLDef, szM_SectionName,
         //:                               mSubLC, szM_SectionName, zPOS_BEFORE )
         IncludeSubobjectFromSubobject( mSPLDef, szM_SectionName, mSubLC, szM_SectionName, zPOS_BEFORE );
         //:// MessageSend( mMasLC, "", szM_SectionName,
         //://              "Check mSPLDef and mSubLC for entity - CopySLC_EntityToSPLD.",
         //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )

         //:GetIntegerFromAttribute( lID, mSubLC, szM_SectionName, "ID" )
         {MutableInt mi_lID = new MutableInt( lID );
                   GetIntegerFromAttribute( mi_lID, mSubLC, szM_SectionName, "ID" );
         lID = mi_lID.intValue( );}
         //:// IF SetCursorFirstEntityByEntityCsr( mSubLC, "S_MarketingUsage", mSubLC, szM_SectionName, "" ) = 0
         //:SET CURSOR FIRST mSubLC.S_MarketingUsage WHERE mSubLC.S_MarketingUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_MarketingUsage", "ID", lID, "" );
         //:IF RESULT = 0
         if ( RESULT == 0 )
         { 
            //:IncludeSubobjectFromSubobject( mSPLDef, "SPLD_OriginalMarketingUsage",
            //:                               mSPLDef, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSPLDef, "SPLD_OriginalMarketingUsage", mSPLDef, szS_SectionName, zPOS_BEFORE );
            //:IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage",
            //:                               mSPLDef, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSPLDef, "SPLD_MarketingUsage", mSPLDef, szS_SectionName, zPOS_BEFORE );
         } 

         //:END

         //:// IF SetCursorFirstEntityByEntityCsr( mSubLC, "S_DirectionsUsage", mSubLC, szM_SectionName, "" ) = 0
         //:SET CURSOR FIRST mSubLC.S_DirectionsUsage WHERE mSubLC.S_DirectionsUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mSubLC, "S_DirectionsUsage", "ID", lID, "" );
         //:IF RESULT = 0
         if ( RESULT == 0 )
         { 
            //:IncludeSubobjectFromSubobject( mSPLDef, "SPLD_OriginalDirectionsUsage",
            //:                               mSPLDef, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSPLDef, "SPLD_OriginalDirectionsUsage", mSPLDef, szS_SectionName, zPOS_BEFORE );
            //:IncludeSubobjectFromSubobject( mSPLDef, "SPLD_DirectionsUsage",
            //:                               mSPLDef, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSPLDef, "SPLD_DirectionsUsage", mSPLDef, szS_SectionName, zPOS_BEFORE );
         } 

         //:END

         //:nRC = SetCursorNextEntity( mSubLC, szM_SectionName, "" )
         nRC = SetCursorNextEntity( mSubLC, szM_SectionName, "" );
      } 


      //:END
   } 


   //:END
   return( 0 );
// END
} 


//:GLOBAL OPERATION
//:CopyMLC_EntityToSLC( VIEW  mMasLC,
//:                     VIEW  mSubLC,
//:                     STRING ( 32 ) szEntity,
//:                     SHORT nFlag )

//:   STRING ( 32 ) szM_SectionName
public int 
CopyMLC_EntityToSLC( View     mMasLC,
                     View     mSubLC,
                     String   szEntity,
                     int      nFlag )
{
   String   szM_SectionName = null;
   //:STRING ( 32 ) szS_SectionName
   String   szS_SectionName = null;
   //:STRING ( 32 ) szM_StatementName
   String   szM_StatementName = null;
   //:STRING ( 32 ) szS_StatementName
   String   szS_StatementName = null;
   //:INTEGER lID
   int      lID = 0;
   //:SHORT   nRC
   int      nRC = 0;
   int      RESULT = 0;


   //:IF nFlag = 2 OR nFlag = 1
   if ( nFlag == 2 || nFlag == 1 )
   { 

      //:// If the Flag is 1, we want to copy the Section entity (building the name).
      //:szM_SectionName = "M_" + szEntity + "Section"
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringCopy( sb_szM_SectionName, 1, 0, "M_", 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringConcat( sb_szM_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringConcat( sb_szM_SectionName, 1, 0, "Section", 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
      //:szS_SectionName = "S_" + szEntity + "Section"
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringCopy( sb_szS_SectionName, 1, 0, "S_", 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringConcat( sb_szS_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringConcat( sb_szS_SectionName, 1, 0, "Section", 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
      //:szM_StatementName = "M_" + szEntity + "Statement"
       {StringBuilder sb_szM_StatementName;
      if ( szM_StatementName == null )
         sb_szM_StatementName = new StringBuilder( 32 );
      else
         sb_szM_StatementName = new StringBuilder( szM_StatementName );
            ZeidonStringCopy( sb_szM_StatementName, 1, 0, "M_", 1, 0, 33 );
      szM_StatementName = sb_szM_StatementName.toString( );}
       {StringBuilder sb_szM_StatementName;
      if ( szM_StatementName == null )
         sb_szM_StatementName = new StringBuilder( 32 );
      else
         sb_szM_StatementName = new StringBuilder( szM_StatementName );
            ZeidonStringConcat( sb_szM_StatementName, 1, 0, szEntity, 1, 0, 33 );
      szM_StatementName = sb_szM_StatementName.toString( );}
       {StringBuilder sb_szM_StatementName;
      if ( szM_StatementName == null )
         sb_szM_StatementName = new StringBuilder( 32 );
      else
         sb_szM_StatementName = new StringBuilder( szM_StatementName );
            ZeidonStringConcat( sb_szM_StatementName, 1, 0, "Statement", 1, 0, 33 );
      szM_StatementName = sb_szM_StatementName.toString( );}
      //:szS_StatementName = "S_" + szEntity + "Statement"
       {StringBuilder sb_szS_StatementName;
      if ( szS_StatementName == null )
         sb_szS_StatementName = new StringBuilder( 32 );
      else
         sb_szS_StatementName = new StringBuilder( szS_StatementName );
            ZeidonStringCopy( sb_szS_StatementName, 1, 0, "S_", 1, 0, 33 );
      szS_StatementName = sb_szS_StatementName.toString( );}
       {StringBuilder sb_szS_StatementName;
      if ( szS_StatementName == null )
         sb_szS_StatementName = new StringBuilder( 32 );
      else
         sb_szS_StatementName = new StringBuilder( szS_StatementName );
            ZeidonStringConcat( sb_szS_StatementName, 1, 0, szEntity, 1, 0, 33 );
      szS_StatementName = sb_szS_StatementName.toString( );}
       {StringBuilder sb_szS_StatementName;
      if ( szS_StatementName == null )
         sb_szS_StatementName = new StringBuilder( 32 );
      else
         sb_szS_StatementName = new StringBuilder( szS_StatementName );
            ZeidonStringConcat( sb_szS_StatementName, 1, 0, "Statement", 1, 0, 33 );
      szS_StatementName = sb_szS_StatementName.toString( );}
      //:nRC = SetCursorFirstEntity( mMasLC, szM_SectionName, "" )
      nRC = SetCursorFirstEntity( mMasLC, szM_SectionName, "" );
      //:LOOP WHILE nRC >= 0
      while ( nRC >= 0 )
      { 

         //:CreateEntity( mSubLC, szS_SectionName, zPOS_AFTER )
         CreateEntity( mSubLC, szS_SectionName, zPOS_AFTER );
         //:SetMatchingAttributesByName( mSubLC, szS_SectionName,
         //:                             mMasLC, szM_SectionName, zSET_NOTNULL )
         SetMatchingAttributesByName( mSubLC, szS_SectionName, mMasLC, szM_SectionName, zSET_NOTNULL );
         //:SetAttributeFromString( mSubLC, szS_SectionName, "Selected", "Y" )
         SetAttributeFromString( mSubLC, szS_SectionName, "Selected", "Y" );
         //:IncludeSubobjectFromSubobject( mSubLC, szM_SectionName,
         //:                               mMasLC, szM_SectionName, zPOS_BEFORE )
         IncludeSubobjectFromSubobject( mSubLC, szM_SectionName, mMasLC, szM_SectionName, zPOS_BEFORE );
         //:// If the Flag is 2, we want to copy the Statement entity (building the name).
         //:IF nFlag = 2
         if ( nFlag == 2 )
         { 

            //:nRC = SetCursorFirstEntity( mMasLC, szM_StatementName, "" )
            nRC = SetCursorFirstEntity( mMasLC, szM_StatementName, "" );
            //:LOOP WHILE nRC >= 0
            while ( nRC >= 0 )
            { 

               //:CreateEntity( mSubLC, szS_StatementName, zPOS_AFTER )
               CreateEntity( mSubLC, szS_StatementName, zPOS_AFTER );
               //:SetMatchingAttributesByName( mSubLC, szS_StatementName,
               //:                             mMasLC, szM_StatementName, zSET_NOTNULL )
               SetMatchingAttributesByName( mSubLC, szS_StatementName, mMasLC, szM_StatementName, zSET_NOTNULL );
               //:SetAttributeFromString( mSubLC, szS_StatementName, "Selected", "Y" )
               SetAttributeFromString( mSubLC, szS_StatementName, "Selected", "Y" );
               //:IncludeSubobjectFromSubobject( mSubLC, szM_StatementName,
               //:                               mMasLC, szM_StatementName, zPOS_BEFORE )
               IncludeSubobjectFromSubobject( mSubLC, szM_StatementName, mMasLC, szM_StatementName, zPOS_BEFORE );

               //:nRC = SetCursorNextEntity( mMasLC, szM_StatementName, "" )
               nRC = SetCursorNextEntity( mMasLC, szM_StatementName, "" );
            } 

            //:END
         } 

         //:END

         //:nRC = SetCursorNextEntity( mMasLC, szM_SectionName, "" )
         nRC = SetCursorNextEntity( mMasLC, szM_SectionName, "" );
      } 

      //:END

      //:ELSE
   } 
   else
   { 

      //:// If the Flag is 0, we want to copy the specified entity.
      //:szM_SectionName = "M_" + szEntity
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringCopy( sb_szM_SectionName, 1, 0, "M_", 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
       {StringBuilder sb_szM_SectionName;
      if ( szM_SectionName == null )
         sb_szM_SectionName = new StringBuilder( 32 );
      else
         sb_szM_SectionName = new StringBuilder( szM_SectionName );
            ZeidonStringConcat( sb_szM_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szM_SectionName = sb_szM_SectionName.toString( );}
      //:szS_SectionName = "S_" + szEntity
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringCopy( sb_szS_SectionName, 1, 0, "S_", 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
       {StringBuilder sb_szS_SectionName;
      if ( szS_SectionName == null )
         sb_szS_SectionName = new StringBuilder( 32 );
      else
         sb_szS_SectionName = new StringBuilder( szS_SectionName );
            ZeidonStringConcat( sb_szS_SectionName, 1, 0, szEntity, 1, 0, 33 );
      szS_SectionName = sb_szS_SectionName.toString( );}
      //:nRC = SetCursorFirstEntity( mMasLC, szM_SectionName, "" )
      nRC = SetCursorFirstEntity( mMasLC, szM_SectionName, "" );
      //:LOOP WHILE nRC >= 0
      while ( nRC >= 0 )
      { 

         //:CreateEntity( mSubLC, szS_SectionName, zPOS_AFTER )
         CreateEntity( mSubLC, szS_SectionName, zPOS_AFTER );
         //:SetAttributeFromString( mSubLC, szS_SectionName, "Selected", "Y" )
         SetAttributeFromString( mSubLC, szS_SectionName, "Selected", "Y" );
         //:SetMatchingAttributesByName( mSubLC, szS_SectionName,
         //:                             mMasLC, szM_SectionName, zSET_NOTNULL )
         SetMatchingAttributesByName( mSubLC, szS_SectionName, mMasLC, szM_SectionName, zSET_NOTNULL );
         //:IncludeSubobjectFromSubobject( mSubLC, szM_SectionName,
         //:                               mMasLC, szM_SectionName, zPOS_BEFORE )
         IncludeSubobjectFromSubobject( mSubLC, szM_SectionName, mMasLC, szM_SectionName, zPOS_BEFORE );
         //:// MessageSend( mMasLC, "", szM_SectionName,
         //://              "Check mSubLC and mMasLC for entity - CopyMLC_EntityToSLC.",
         //://              zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )

         //:GetIntegerFromAttribute( lID, mMasLC, szM_SectionName, "ID" )
         {MutableInt mi_lID = new MutableInt( lID );
                   GetIntegerFromAttribute( mi_lID, mMasLC, szM_SectionName, "ID" );
         lID = mi_lID.intValue( );}
         //:// IF SetCursorFirstEntityByEntityCsr( mMasLC, "M_MarketingUsage", mMasLC, szM_SectionName, "" ) = 0
         //:SET CURSOR FIRST mMasLC.M_MarketingUsage WHERE mMasLC.M_MarketingUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_MarketingUsage", "ID", lID, "" );
         //:IF RESULT = 0
         if ( RESULT == 0 )
         { 
            //:IncludeSubobjectFromSubobject( mSubLC, "S_OriginalMarketingUsage",
            //:                               mSubLC, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSubLC, "S_OriginalMarketingUsage", mSubLC, szS_SectionName, zPOS_BEFORE );
            //:IncludeSubobjectFromSubobject( mSubLC, "S_MarketingUsage",
            //:                               mSubLC, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSubLC, "S_MarketingUsage", mSubLC, szS_SectionName, zPOS_BEFORE );
         } 

         //:END

         //:// IF SetCursorFirstEntityByEntityCsr( mMasLC, "M_DirectionsUsage", mMasLC, szM_SectionName, "" ) = 0
         //:SET CURSOR FIRST mMasLC.M_DirectionsUsage WHERE mMasLC.M_DirectionsUsage.ID = lID
         RESULT = SetCursorFirstEntityByInteger( mMasLC, "M_DirectionsUsage", "ID", lID, "" );
         //:IF RESULT = 0
         if ( RESULT == 0 )
         { 
            //:IncludeSubobjectFromSubobject( mSubLC, "S_OriginalDirectionsUsage",
            //:                               mSubLC, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSubLC, "S_OriginalDirectionsUsage", mSubLC, szS_SectionName, zPOS_BEFORE );
            //:IncludeSubobjectFromSubobject( mSubLC, "S_DirectionsUsage",
            //:                               mSubLC, szS_SectionName, zPOS_BEFORE )
            IncludeSubobjectFromSubobject( mSubLC, "S_DirectionsUsage", mSubLC, szS_SectionName, zPOS_BEFORE );
         } 

         //:END

         //:nRC = SetCursorNextEntity( mMasLC, szM_SectionName, "" )
         nRC = SetCursorNextEntity( mMasLC, szM_SectionName, "" );
      } 


      //:END
   } 


   //:END
   return( 0 );
// END
} 



}
