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

/**
   @author QuinSoft
**/

public class mSAMealP_Object extends VmlObjectOperations
{
   public mSAMealP_Object( View view )
   {
      super( view );
   }


//:TRANSFORMATION OPERATION
public int 
omSAMealP_ActivateMPCurrentSA( zVIEW    mSAMealP,
                               View     lSATermLST )
{
   int      lTempInteger_0 = 0;
   int      lTempInteger_1 = 0;
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateMPCurrentSA( VIEW mSAMealP BASED ON LOD mSAMealP,
   //:                  VIEW lSATermLST BASED ON LOD lTermLST )
   //:ACTIVATE mSAMealP  MULTIPLE 
   //:      WHERE mSAMealP.CollegeYear.ID = lSATermLST.CollegeYear.ID 
   {MutableInt mi_lTempInteger_0 = new MutableInt( lTempInteger_0 );
       GetIntegerFromAttribute( mi_lTempInteger_0, lSATermLST, "CollegeYear", "ID" );
   lTempInteger_0 = mi_lTempInteger_0.intValue( );}
   //:      RESTRICTING mSAMealP.SATransactionTerm TO mSAMealP.CollegeYear.ID = lSATermLST.CollegeYear.ID 
   {MutableInt mi_lTempInteger_1 = new MutableInt( lTempInteger_1 );
       GetIntegerFromAttribute( mi_lTempInteger_1, lSATermLST, "CollegeYear", "ID" );
   lTempInteger_1 = mi_lTempInteger_1.intValue( );}
   omSAMealP_fnLocalBuildQual_0( lSATermLST, vTempViewVar_0, lTempInteger_0, lTempInteger_1 );
   RESULT = ActivateObjectInstance( mSAMealP, "mSAMealP", lSATermLST, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   //:RETURN 0
   return( 0 );
// END
} 


private int 
omSAMealP_fnLocalBuildQual_0( View     vSubtask,
                              zVIEW    vQualObject,
                              int      lTempInteger_0,
                              int      lTempInteger_1 )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "MealPlan" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_0 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionTerm" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "CollegeYear" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromInteger( vQualObject, "QualAttrib", "Value", lTempInteger_1 );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "=" );
   return( 0 );
} 


private int 
omSAMealP_fnLocalBuildQual_1( View     vSubtask,
                              zVIEW    vQualObject )
{
   int      RESULT = 0;

   RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
   CreateEntity( vQualObject, "EntitySpec", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "EntitySpec", "EntityName", "SATransactionCode" );
   CreateEntity( vQualObject, "QualAttrib", zPOS_AFTER );
   SetAttributeFromString( vQualObject, "QualAttrib", "EntityName", "SATransactionCode" );
   SetAttributeFromString( vQualObject, "QualAttrib", "AttributeName", "ID" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Value", "0" );
   SetAttributeFromString( vQualObject, "QualAttrib", "Oper", "<" );
   return( 0 );
} 


//:TRANSFORMATION OPERATION
public int 
omSAMealP_ActivateMPAll( zVIEW    mSAMealP,
                         View     zAnyView )
{
   zVIEW    vTempViewVar_0 = new zVIEW( );
   int      RESULT = 0;

   //:ActivateMPAll( VIEW mSAMealP BASED ON LOD mSAMealP,
   //:            VIEW zAnyView )

   //:ACTIVATE mSAMealP  MULTIPLE 
   //:   RESTRICTING mSAMealP.SATransactionCode TO mSAMealP.SATransactionCode.ID < 0
   omSAMealP_fnLocalBuildQual_1( zAnyView, vTempViewVar_0 );
   RESULT = ActivateObjectInstance( mSAMealP, "mSAMealP", zAnyView, vTempViewVar_0, zMULTIPLE );
   DropView( vTempViewVar_0 );
   return( 0 );
// END
} 



}
