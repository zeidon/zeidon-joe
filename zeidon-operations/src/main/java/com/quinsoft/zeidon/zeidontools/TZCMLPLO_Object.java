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
import com.quinsoft.zeidon.zeidonoperations.KZOEP1AA;
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

public class TZCMLPLO_Object extends VmlObjectOperations
{
   public TZCMLPLO_Object( View view )
   {
      super( view );
   }

   //:DERIVED ATTRIBUTE OPERATION
   /////////////////////////////////////////////////////////////////////////////
   //
   // OPERATION: oTZCMLPLO_SetCM_ViewName
   //
   // PURPOSE:   This derived attribute determines the view name for an
   //            activated meta instance.
   //
   /////////////////////////////////////////////////////////////////////////////
   public int
   oTZCMLPLO_SetCM_ViewName( View    view,
                             String  InternalEntityStructure,
                             String  InternalAttribStructure,
                             Integer GetOrSetFlag )
   {
      int    lType;
      int    lZKey;
      StringBuilder sbMetaName = new StringBuilder();
      StringBuilder sbCM_ViewName = new StringBuilder();
      int    nReset;

      sbCM_ViewName.setLength( 0 );
      switch( GetOrSetFlag )
      {
         case zDERIVED_GET:
         {
            nReset = ResetViewFromSubobject( view );
            GetStringFromAttribute( sbCM_ViewName, view, "LPLR", "Name" );
            zstrcat( sbCM_ViewName, "." );

            lType = GetIntegerFromAttribute( view, "W_MetaType", "Type" );
            StringBuilder sb = new StringBuilder();
            KZOEP1AA.CM_GetTypePrefix( lType, sb );
            sbCM_ViewName.append( sb );
            lZKey = GetIntegerFromAttribute( view, "W_MetaDef", "CPLR_ZKey" );
            sbMetaName.setLength( 0 );
            sbMetaName.append( Integer.toHexString( lZKey ) );
            zstrcat( sbCM_ViewName, sbMetaName );

            // Update the value of the DIL message in the object.
            StoreStringInRecord( view, InternalEntityStructure, InternalAttribStructure, sbCM_ViewName.toString() );
            if ( nReset == 0 )
            {
               SetViewToSubobject( view, "W_MetaType" );
            }

            if ( sbCM_ViewName.length() == 0 )
            {
               TraceLine( "oTZCMLPLO_SetCM_ViewName cannot set CM_ViewName using VEA: 0x%08x.%s.%s for message: %d",
                          view, InternalEntityStructure, InternalAttribStructure, GetOrSetFlag );
            }

            break;
         }

      // case zDERIVED_SET:
      //    break;
      }

      return 0;
   }

}

