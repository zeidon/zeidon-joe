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

package com.quinsoft.zeidon.zeidontools;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import com.quinsoft.zeidon.EntityCursor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

/**
 * @author DKS
 *
**/

public class KZWDLGXO_Object extends VmlObjectOperations
{
   public KZWDLGXO_Object( View view )
   {
      super( view );
   }

   //:DERIVED ATTRIBUTE OPERATION
   public int
   oKZWDLGXO_DerivedControlText( View    vXWD,  //  BASED ON LOD KZWDLGXO
                                 String  InternalEntityStructure,
                                 String  InternalAttribStructure,
                                 Integer GetOrSetFlag )
   {
      String  stringAttr = "";
      Integer lNLS;
      Integer LanguageIndex;
      int    nRC;

      //:CASE GetOrSetFlag
      switch( GetOrSetFlag )
      {
         case zDERIVED_GET:
         {
         // nRC = GetVariableFromAttribute( &lNLS, 0, zTYPE_INTEGER, 0,
         //                                 vXWD, "Ctrl", "NLS", "", zACCEPT_NULL_ENTITY );
            EntityCursor cursor = vXWD.cursor( "Ctrl" );
            if ( cursor == null )
            {
               lNLS = 0;
               nRC = zCURSOR_NULL;
            }
            else
            {
               lNLS = cursor.getAttribute( "NLS" ).getInteger();
               if ( lNLS == null )
                  lNLS = 0;

               nRC = 0;
            }

            if ( nRC < 0 || lNLS == 0 )
            {
               stringAttr = GetAddrForAttribute( stringAttr, vXWD, "Ctrl", "Text" );
            }
            else
            {
               View vXWD2 = vXWD.newView( );
               vXWD2.reset( );

            // SetCursorRelativeEntity( vXWD2, "NLS", lNLS, "" );
               vXWD2.cursor( "NLS" ).setFirst( );
               while ( lNLS > 0 )
               {
                  vXWD2.cursor( "NLS" ).setNext( );
                  lNLS--;
               }

            // LanguageIndex = SysGetLanguageCode( );
            // nRC = SetCursorFirstEntityByInteger( vXWD2, "NLS_Text", "LI", (zLONG) LanguageIndex, "" );
            // if ( nRC >= zCURSOR_SET )
            //     GetAddrForAttribute( &stringAttr, vXWD2, "NLS_Text", "Text" );
            // else
                  stringAttr = GetAddrForAttribute( stringAttr, vXWD, "Ctrl", "Text" );

               DropView( vXWD2 );
            }

            StoreStringInRecord( vXWD, InternalEntityStructure, InternalAttribStructure, stringAttr );
         // StoreValueInRecord( vXWD, lpEntity, lpAttribute, stringAttr, 0 );
            break;
         }

      // case zDERIVED_SET:
      //    break;
      }

      return 0;
   }

   public int
   oKZWDLGXO_DerivedOptionText( View    vXWD,  //  BASED ON LOD KZWDLGXO
                                String  InternalEntityStructure,
                                String  InternalAttribStructure,
                                Integer GetOrSetFlag )
   {
      String stringAttr = "";
      int    lNLS;
      int    LanguageIndex;
      int    nRC;

      //:CASE GetOrSetFlag
      switch( GetOrSetFlag )
      {
         case zDERIVED_GET:
         {
         // nRC = GetVariableFromAttribute( &lNLS, 0, zTYPE_INTEGER, 0,
         //                                 vXWD, "Opt", "NLS", "", zACCEPT_NULL_ENTITY );
            EntityCursor cursor = vXWD.cursor( "Opt" );
            if ( cursor == null )
            {
               lNLS = 0;
               nRC = zCURSOR_NULL;
            }
            else
            {
               lNLS = cursor.getAttribute( "NLS" ).getInteger();
               nRC = 0;
            }

            if ( nRC < 0 || lNLS == 0 )
            {
               stringAttr = GetAddrForAttribute( stringAttr, vXWD, "Opt", "Text" );
            }
            else
            {
               View vXWD2 = vXWD.newView( );
               vXWD2.reset( );

            // SetCursorRelativeEntity( vXWD2, "NLS", lNLS, "" );
               vXWD2.cursor( "NLS" ).setFirst( );
               while ( lNLS > 0 )
               {
                  vXWD2.cursor( "NLS" ).setNext( );
                  lNLS--;
               }

            // LanguageIndex = SysGetLanguageCode( );
            // nRC = SetCursorFirstEntityByInteger( vXWD2, "NLS_Text", "LI", (zLONG) LanguageIndex, "" );
            // if ( nRC >= zCURSOR_SET )
            //     GetAddrForAttribute( &stringAttr, vXWD2, "NLS_Text", "Text" );
            // else
                  stringAttr = GetAddrForAttribute( stringAttr, vXWD, "Opt", "Text" );

               DropView( vXWD2 );
            }

            StoreStringInRecord( vXWD, InternalEntityStructure, InternalAttribStructure, stringAttr );
         // StoreValueInRecord( vXWD, lpEntity, lpAttribute, stringAttr, 0 );
            break;
         }

      // case zDERIVED_SET:
      //    break;
      }

      return 0;
   }

   public int
   oKZWDLGXO_DerivedCaption( View    vXWD,  //  BASED ON LOD KZWDLGXO
                             String  InternalEntityStructure,
                             String  InternalAttribStructure,
                             Integer GetOrSetFlag )
   {
      String stringAttr = "";
      int    lNLS;
      int    LanguageIndex;
      int    nRC;

      //:CASE GetOrSetFlag
      switch( GetOrSetFlag )
      {
         case zDERIVED_GET:
         {
         // nRC = GetVariableFromAttribute( &lNLS, 0, zTYPE_INTEGER, 0,
         //                                 vXWD, "Wnd", "NLS", "", zACCEPT_NULL_ENTITY );
            EntityCursor cursor = vXWD.cursor( "Wnd" );
            if ( cursor == null )
            {
               lNLS = 0;
               nRC = zCURSOR_NULL;
            }
            else
            {
               lNLS = cursor.getAttribute( "NLS" ).getInteger();
               nRC = 0;
            }

            if ( nRC < 0 || lNLS == 0 )
            {
               stringAttr = GetAddrForAttribute( stringAttr, vXWD, "Wnd", "Caption" );
            }
            else
            {
               View vXWD2 = vXWD.newView( );
               vXWD2.reset( );

            // SetCursorRelativeEntity( vXWD2, "NLS", lNLS, "" );
               vXWD2.cursor( "NLS" ).setFirst( );
               while ( lNLS > 0 )
               {
                  vXWD2.cursor( "NLS" ).setNext( );
                  lNLS--;
               }

            // LanguageIndex = SysGetLanguageCode( );
            // nRC = SetCursorFirstEntityByInteger( vXWD2, "NLS_Text", "LI", (zLONG) LanguageIndex, "" );
            // if ( nRC >= zCURSOR_SET )
            //     GetAddrForAttribute( &stringAttr, vXWD2, "NLS_Text", "Text" );
            // else
                  stringAttr = GetAddrForAttribute( stringAttr, vXWD, "Wnd", "Caption" );

               DropView( vXWD2 );
            }

            StoreStringInRecord( vXWD, InternalEntityStructure, InternalAttribStructure, stringAttr );
         // StoreValueInRecord( vXWD, lpEntity, lpAttribute, stringAttr, 0 );
            break;
         }

      // case zDERIVED_SET:
      //    break;
      }

      return 0;
   }

   public int
   oKZWDLGXO_DerivedDIL_Msg( View    vXWD,  //  BASED ON LOD KZWDLGXO
                             String  InternalEntityStructure,
                             String  InternalAttribStructure,
                             Integer GetOrSetFlag )
   {
      String stringAttr = "";
      int    lNLS;
      int    LanguageIndex;
      int    nRC;

      //:CASE GetOrSetFlag
      switch( GetOrSetFlag )
      {
         case zDERIVED_GET:
         {
            if ( vXWD.cursor( "NLS_DIL_Text" ).hasAny( ) == true )
            {
            // LanguageIndex = SysGetLanguageCode( );
            // nRC = SetCursorFirstEntityByInteger( vXWD, "NLS_DIL_Text", "LI", (zLONG) LanguageIndex, "" );
            // if ( nRC >= zCURSOR_SET )
            //     GetAddrForAttribute( &stringAttr, vXWD, "NLS_DIL_Text", "Text" );
            // else
                  stringAttr = GetAddrForAttribute( stringAttr, vXWD, "DIL", "Msg" );
            }
            else
               stringAttr = GetAddrForAttribute( stringAttr, vXWD, "DIL", "Msg" );
         }

         StoreStringInRecord( vXWD, InternalEntityStructure, InternalAttribStructure, stringAttr );
      // StoreValueInRecord( vXWD, lpEntity, lpAttribute, stringAttr, 0 );
         break;

      // case zDERIVED_SET:
      //    break;
      }

      return 0;
   }
}