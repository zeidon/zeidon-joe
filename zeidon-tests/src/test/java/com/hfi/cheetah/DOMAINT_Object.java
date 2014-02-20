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

package com.hfi.cheetah;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlObjectOperations;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.vml.zVIEW;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableInt;

/**
   @author QuinSoft
**/

public class DOMAINT_Object extends VmlObjectOperations
{
   public DOMAINT_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
//:ActivateDomainList( VIEW DOMAINTLST BASED ON LOD DOMAINT,
//:                    VIEW zAnyView )

//:   VIEW DOMAINT BASED ON LOD DOMAINT
public int 
oDOMAINT_ActivateDomainList( zVIEW    DOMAINTLST,
                             View     zAnyView )
{
   zVIEW    DOMAINT = new zVIEW( );
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;
   int      lTempInteger_0 = 0;


   //:ACTIVATE DOMAINTLST MULTIPLE 
   //:   RESTRICTING DOMAINTLST.DomainValue TO DOMAINTLST.DomainValue.ExternalDescription = ""
   oDOMAINT_fnLocalBuildQual_0( zAnyView, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( DOMAINTLST, "DOMAINT", zAnyView, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );

   //:GET VIEW DOMAINT NAMED "DOMAINT"
   RESULT = GetViewByName( DOMAINT, "DOMAINT", zAnyView, zLEVEL_TASK );
   //:IF RESULT > 0
   if ( RESULT > 0 )
   { 
      //:IF DOMAINTLST.Domain EXISTS
      lTempInteger_0 = CheckExistenceOfEntity( DOMAINTLST, "Domain" );
      if ( lTempInteger_0 == 0 )
      { 
         //:SET CURSOR FIRST DOMAINTLST.Domain WHERE
         //:   DOMAINT.Domain.Name = DOMAINTLST.Domain.Name 
         RESULT = DOMAINTLST.cursor( "Domain" ).setFirst().toInt();
         if ( RESULT > zCURSOR_UNCHANGED )
         { 
            while ( RESULT > zCURSOR_UNCHANGED && ( CompareAttributeToAttribute( DOMAINT, "Domain", "Name", DOMAINTLST, "Domain", "Name" ) != 0 ) )
            { 
               RESULT = DOMAINTLST.cursor( "Domain" ).setNextContinue().toInt();
            } 

         } 

      } 

      //:END
      //:DropView ( DOMAINT )
      DropView( DOMAINT );
   } 

   //:END

   //:NAME VIEW DOMAINTLST "DOMAINTLST"
   SetNameForView( DOMAINTLST, "DOMAINTLST", null, zLEVEL_TASK );
   return( 0 );
// END
} 


private int 
oDOMAINT_fnLocalBuildQual_0( View     vSubtask,
                             zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "DomainValue" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "DomainValue" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ExternalDescription" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 



}
