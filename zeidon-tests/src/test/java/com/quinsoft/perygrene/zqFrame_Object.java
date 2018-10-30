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

package com.quinsoft.perygrene;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

import com.quinsoft.zeidon.zeidonoperations.ZDRVROPR;
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;

/**
   @author QuinSoft
**/

public class zqFrame_Object extends VmlObjectOperations
{
   private final ZDRVROPR m_ZDRVROPR;
   private final KZOEP1AA m_KZOEP1AA;
   public zqFrame_Object( View view )
   {
      super( view );
      m_ZDRVROPR = new ZDRVROPR( view );
      m_KZOEP1AA = new KZOEP1AA( view );
   }


//:TRANSFORMATION OPERATION
//:BuildQualFromFrame( VIEW zqFrame   BASED ON LOD zqFrame,
//:                    VIEW vQualObject,
//:                    VIEW qConvertData )

//:   VIEW wXferO   REGISTERED AS wXferO

//:   VIEW zqFrame2 BASED ON LOD zqFrame
public int 
ozqFrame_ObjectConstraint( View     zqFrame,
                           Integer   Event,
                           Integer   State )
{
   zVIEW    zqFrame2 = new zVIEW( );
   int      RESULT = 0;
   String   szTempString_0 = null;
   String   szTempString_1 = null;
   int      lTempInteger_0 = 0;


   //:CASE Event
   switch( Event )
   { 
      //:OF   zOCE_ACTIVATE:
      case zOCE_ACTIVATE :

         //:// Always fill the Prompt attribute with the Entity.Attribute names.
         //:CreateViewFromView( zqFrame2, zqFrame )
         CreateViewFromView( zqFrame2, zqFrame );
         //:FOR EACH zqFrame2.GeneralParameter
         RESULT = SetCursorFirstEntity( zqFrame2, "GeneralParameter", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:zqFrame2.GeneralParameter.Prompt = zqFrame2.GeneralParameter.EntityName + "." +
            //:                             zqFrame2.GeneralParameter.AttributeName
            {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                         GetStringFromAttribute( sb_szTempString_0, zqFrame2, "GeneralParameter", "EntityName" );
            szTempString_0 = sb_szTempString_0.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, ".", 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
            {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
            StringBuilder sb_szTempString_1;
            if ( szTempString_1 == null )
               sb_szTempString_1 = new StringBuilder( 32 );
            else
               sb_szTempString_1 = new StringBuilder( szTempString_1 );
                         GetVariableFromAttribute( sb_szTempString_1, mi_lTempInteger_0, 'S', 33, zqFrame2, "GeneralParameter", "AttributeName", "", 0 );
            lTempInteger_0 = mi_lTempInteger_0.intValue( );
            szTempString_1 = sb_szTempString_1.toString( );}
             {StringBuilder sb_szTempString_0;
            if ( szTempString_0 == null )
               sb_szTempString_0 = new StringBuilder( 32 );
            else
               sb_szTempString_0 = new StringBuilder( szTempString_0 );
                        ZeidonStringConcat( sb_szTempString_0, 1, 0, szTempString_1, 1, 0, 255 );
            szTempString_0 = sb_szTempString_0.toString( );}
            SetAttributeFromString( zqFrame2, "GeneralParameter", "Prompt", szTempString_0 );
            RESULT = SetCursorNextEntity( zqFrame2, "GeneralParameter", "" );
         } 

         //:END
         //:DropView( zqFrame2 )
         DropView( zqFrame2 );

         //:// Make sure that the Domain/Context subobject is empty.
         //:FOR EACH zqFrame.Domain 
         RESULT = SetCursorFirstEntity( zqFrame, "Domain", "" );
         while ( RESULT > zCURSOR_UNCHANGED )
         { 
            //:DELETE ENTITY zqFrame.Domain NONE 
            RESULT = DeleteEntity( zqFrame, "Domain", zREPOS_NONE );
            RESULT = SetCursorNextEntity( zqFrame, "Domain", "" );
         } 

         //:END
         break ;

      //:  /* end zOCE_ACTIVATE */
      //:OF   zOCE_ACTIVATE_EMPTY:
      case zOCE_ACTIVATE_EMPTY :
         break ;

      //:  /* end zOCE_ACTIVATE_EMPTY */
      //:OF   zOCE_COMMIT:
      case zOCE_COMMIT :
         break ;

      //:  /* end zOCE_COMMIT */
      //:OF   zOCE_DROPOI:
      case zOCE_DROPOI :
         break ;
   } 


   //:     /* end zOCE_DROPOI */
   //:END  /* case */
   return( 0 );
// END
} 



}
