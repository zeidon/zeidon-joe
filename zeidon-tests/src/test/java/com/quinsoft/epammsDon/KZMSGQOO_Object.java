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
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.epamms.ZGlobal1_Operation;


/**
 * @author QuinSoft
 *
 */

public class KZMSGQOO_Object extends VmlObjectOperations
{
   public KZMSGQOO_Object( View view )
   {
      super( view );
   }

   // Get the first error message (if any).
   public final String
   FindErrorFields( )
   {
      StringBuilder sbError = new StringBuilder( );

      View vMsgQ = getView( );
      if ( vMsgQ != null )
      {
         vMsgQ = vMsgQ.newView( );
      
      // TraceLineI( "FindErrorFields SetCursorFirst Task RC: ", nRC );
         DisplayObjectInstance( vMsgQ, "", "" );

         int nRC = SetCursorFirstEntityByString( vMsgQ, "Task", "Id", task.getTask( ).getTaskId( ), "" );
         //CursorResult csrRC2 = vMsgQ.cursor( "Task" ).setFirst( "Id", task.getTask( ).getTaskId( ) );
         if ( nRC == 0 )
         {
            // nRC = vMsgQ.cursor( "WebMsg" ).setFirst( );
            nRC = SetCursorFirstEntity( vMsgQ, "WebMsg", "" );
         // TraceLineI( "FindErrorFields SetCursorFirst WebMsg RC: ", nRC );

            // Get only the first error message for presentation.
            while ( nRC == 0 )
            {
               String s = vMsgQ.cursor( "WebMsg" ).getAttribute( "ControlTag" ).toString();
               String strError = GetStringFromAttribute( vMsgQ, "WebMsg", "ErrorMsg" );
               if ( s == null || s.isEmpty( ) )
               {
                  s = vMsgQ.cursor( "WebMsg" ).getAttribute( "Title" ).toString();
                  if ( s == null || s.isEmpty( ) )
                     s = "Unknown Error Title";
                  
                  sbError.append( "X\t" );  // "cross-field edit error"
               }
               else
               if ( s.charAt( 0 ) != 'D' || s.charAt( 1 ) != '\t' )  // leave it alone if it starts with "D\t"
               {
                  if ( strError == null || strError.isEmpty( ) )
                  {
                     strError = "";
                     sbError.append( "N\t" );  // not an error message
                  }
                  else
                     sbError.append( "Y\t" );
               }

               sbError.append( s );
               sbError.append( "\t" );

               sbError.append( strError );  // things should be good here with single tab separator

               // Tab + Newline to separate error message from map value.
               s = vMsgQ.cursor( "WebMsg" ).getAttribute( "ErrorMapValue" ).toString();
               sbError.append( "\t\n" );
               sbError.append( s == null || s.isEmpty( ) ? "" : s );

               // Delete all the error message entities.
               vMsgQ.cursor( "WebMsg" ).logEntity( );
               vMsgQ.cursor( "WebMsg" ).deleteEntity( CursorPosition.NONE );
               //nRC = vMsgQ.cursor( "WebMsg" ).setFirst( );
               nRC = SetCursorNextEntity( vMsgQ, "WebMsg", "" );
               if ( nRC == 0 )
                  sbError.append( "\t\n" );  // separate this message from the next message
            }
         }

      // alert( sbError );
      }

      if ( sbError.length() > 0 )
         TraceLineS( "FindErrorFields Error===========>> ", sbError.toString( ) );

      return sbError.toString( );
   }

}
