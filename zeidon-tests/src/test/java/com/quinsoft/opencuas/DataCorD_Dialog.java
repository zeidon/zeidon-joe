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
package com.quinsoft.opencuas;

//
//#define KZSYSSVC_INCL
//#include "KZOENGAA.H" 
//#include "ZDRVROPR.H"
import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.vml.VmlDialog;
import com.quinsoft.zeidon.vml.zVIEW;

//
//#ifdef __cplusplus
//extern "C"
//{
//#endif
//
//#include "ZEIDONOP.H"
//
//zOPER_EXPORT zSHORT OPERATION
//ANALYZE_Something( zVIEW     ViewToWindow );
//
//
//static zSHORT
//o_fnLocalBuildQual_0( zVIEW     vSubtask,
//                    zPVIEW    vQualObject );
//
//
////:DIALOG OPERATION
////:ANALYZE_Something( VIEW ViewToWindow )
//

/**
 * @author DG
 *
 */
public class DataCorD_Dialog extends VmlDialog
{
    
    public DataCorD_Dialog(Task task)
    {
		super(task);
	}

//    //:   VIEW qGLChrtE  BASED ON LOD qGLChrtE
//    zOPER_EXPORT zSHORT OPERATION
//    ANALYZE_Something( zVIEW     ViewToWindow )
//    {
    public int ANALYZE_Something( zVIEW viewToWindow )
    {
//       zVIEW     qGLChrtE = 0;
        View qGLChrtE = null;
//       //:VIEW wXferO REGISTERED AS wXferO
//       zVIEW     wXferO = 0; 

//       zSHORT    RESULT;
        int RESULT;
//       //:SHORT nRC
//       zSHORT    nRC = 0;
        int nRC;
//       zVIEW     vTempViewVar_0;
        View vTempViewVar_0;
//       zSHORT    nZRetCode;
        int nZRetCode;
//
//       RESULT = GetViewByName( &wXferO, "wXferO", ViewToWindow, zLEVEL_TASK );
        zVIEW wXferO = new zVIEW(); 
        GetViewByName( wXferO, "wXferO", viewToWindow, zLEVEL_TASK );
//
//       //:SET CURSOR FIRST qGLChrtE.GLChartEntry 
//       //:           WHERE qGLChrtE.GLChartEntry.ChartEntryCode = "111"
//       RESULT = SetCursorFirstEntityByString( qGLChrtE, "GLChartEntry",
//          "ChartEntryCode", "111", "" );
        RESULT = SetCursorFirstEntityByString( qGLChrtE, "GLChartEntry", "ChartEntryCode", "111" , "" );
//
//       //:SET CURSOR FIRST qGLChrtE.GLChartEntry 
//       //:           WHERE qGLChrtE.GLChartEntry.ChartEntryCode = "111"
//       //:             AND qGLChrtE.GLChartEntry.ID             = 4
//       //:             AND qGLChrtE.GLAccounts.AccountNumber = "vvv"
//       RESULT = SetCursorFirstEntity( qGLChrtE, "GLChartEntry", "" );
//       if ( RESULT > zCURSOR_UNCHANGED )
//       { 
//          while ( RESULT > zCURSOR_UNCHANGED &&
//             ( CompareAttributeToString( qGLChrtE, "GLChartEntry",
//             "ChartEntryCode", "111" ) != 0 ||
//             CompareAttributeToInteger( qGLChrtE, "GLChartEntry", "ID", 4 ) != 0 ||
//             CompareAttributeToString( qGLChrtE, "GLAccounts", "AccountNumber",
//             "vvv" ) != 0 ) )
//          { 
//             RESULT = SetCursorNextEntity( qGLChrtE, "GLChartEntry", "" );
//          } 
//       }

      // Following is exactly the same as above.
      RESULT = SetCursorFirstEntity( qGLChrtE, "GLChartEntry", "" );
      if ( RESULT > zCURSOR_UNCHANGED )
      { 
         while ( RESULT > zCURSOR_UNCHANGED &&
            ( CompareAttributeToString( qGLChrtE, "GLChartEntry",
            "ChartEntryCode", "111" ) != 0 ||
            CompareAttributeToInteger( qGLChrtE, "GLChartEntry", "ID", 4 ) != 0 ||
            CompareAttributeToString( qGLChrtE, "GLAccounts", "AccountNumber",
            "vvv" ) != 0 ) )
         { 
            RESULT = SetCursorNextEntity( qGLChrtE, "GLChartEntry", "" );
         } 
      }
//
//       //:SET CURSOR FIRST qGLChrtE.GLJournalEntryDetail 
//       //:           WHERE qGLChrtE.GLChartEntry.ChartEntryCode = "111"
//       RESULT = SetCursorFirstEntity( qGLChrtE, "GLJournalEntryDetail", "" );
//       if ( RESULT > zCURSOR_UNCHANGED )
//       { 
//          while ( RESULT > zCURSOR_UNCHANGED &&
//             ( CompareAttributeToString( qGLChrtE, "GLChartEntry",
//             "ChartEntryCode", "111" ) != 0 ) )
//          { 
//             RESULT = SetCursorNextEntity( qGLChrtE, "GLJournalEntryDetail", "" );
//          } 
//
//       } 
//
//       //:SET CURSOR FIRST qGLChrtE.GLJournalEntryDetail 
//       //:           WHERE qGLChrtE.GLJournalEntryDetail.ID = 45
//       //:             AND qGLChrtE.GLChartEntry.ChartEntryCode = "111"
//       //:             AND qGLChrtE.GLAccounts.AccountNumber = "vvv"
//       RESULT = SetCursorFirstEntity( qGLChrtE, "GLJournalEntryDetail", "" );
//       if ( RESULT > zCURSOR_UNCHANGED )
//       { 
//          while ( RESULT > zCURSOR_UNCHANGED &&
//             ( CompareAttributeToInteger( qGLChrtE, "GLJournalEntryDetail", "ID",
//             45 ) != 0 ||
//             CompareAttributeToString( qGLChrtE, "GLChartEntry", "ChartEntryCode",
//             "111" ) != 0 ||
//             CompareAttributeToString( qGLChrtE, "GLAccounts", "AccountNumber",
//             "vvv" ) != 0 ) )
//          { 
//             RESULT = SetCursorNextEntity( qGLChrtE, "GLJournalEntryDetail", "" );
//          } 
//
//       } 
//
//       //:FOR EACH qGLChrtE.GLJournalEntryDetail 
//       RESULT = SetCursorFirstEntity( qGLChrtE, "GLJournalEntryDetail", "" );
//       while ( RESULT > zCURSOR_UNCHANGED )
//       { 
//          //:IF qGLChrtE.GLChartEntry.ChartEntryCode = "111"
//          if ( CompareAttributeToString( qGLChrtE, "GLChartEntry",
//             "ChartEntryCode", "111" ) == 0 )
//          { 
//             //:qGLChrtE.GLAccounts.AccountNumber = qGLChrtE.GLChartEntry.EntryDescription
//             SetAttributeFromAttribute( qGLChrtE, "GLAccounts", "AccountNumber",
//                qGLChrtE, "GLChartEntry", "EntryDescription" );
//          } 
//
//          RESULT = SetCursorNextEntity( qGLChrtE, "GLJournalEntryDetail", "" );
//          //:END 
//       } 
//
//       //:END
//
//       //:ACTIVATE qGLChrtE
//       //:           WHERE qGLChrtE.GLJournalEntryDetail.ID = 45
//       //:             AND qGLChrtE.GLChartEntry.ChartEntryCode = "111" 
//       nZRetCode = o_fnLocalBuildQual_0( ViewToWindow, &vTempViewVar_0 );
        vTempViewVar_0 = o_fnLocalBuildQual_0( viewToWindow  );
//       RESULT = ActivateObjectInstance( &qGLChrtE, "qGLChrtE", ViewToWindow,
//          vTempViewVar_0, zSINGLE );
        qGLChrtE = viewToWindow.activateObjectInstance( "qGLChrtE", vTempViewVar_0, ActivateFlags.SINGLE );
        
//       nZRetCode = DropView( vTempViewVar_0 );
        vTempViewVar_0.drop();  // Not necessary in this case but not harmful.
        
//       return( 0 );
//    // END
//    }
        return 0;
    }
//
//
//    static zSHORT
//    o_fnLocalBuildQual_0( zVIEW     vSubtask,
//                          zPVIEW    vQualObject )
//    {
    private View o_fnLocalBuildQual_0( View vSubtask  )
    {
//       zSHORT    RESULT; 
//       zSHORT    nZRetCode; 
        int RESULT;
        int nZRetCode;
        
//
//       RESULT = SfActivateSysEmptyOI( vQualObject, "KZDBHQUA", vSubtask, zMULTIPLE );
        View vQualObject = vSubtask.activateEmptyObjectInstance( "KZDBHQUA", vSubtask.getSystemTask().getApplication() );
        
//       nZRetCode = CreateEntity( *vQualObject, "EntitySpec", zPOS_AFTER );
        vQualObject.cursor( "EntitySpec" ).createEntity( CursorPosition.NEXT );
        
//       nZRetCode = SetAttributeFromString( *vQualObject, "EntitySpec",
//          "EntityName", "GLJournalEntryDetail" );
        vQualObject.cursor( "EntitySpec" ).setAttribute( "EntityName", "GLJournalEntryDetail" );
        
//       nZRetCode = CreateEntity( *vQualObject, "QualAttrib", zPOS_AFTER );
        vQualObject.cursor( "QualAttrib" ).createEntity( CursorPosition.NEXT );
        
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib",
//          "EntityName", "GLJournalEntryDetail" );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib",
//          "AttributeName", "ID" );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib", "Value",
//          "45" );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib", "Oper", "=" );
//       nZRetCode = CreateEntity( *vQualObject, "QualAttrib", zPOS_AFTER );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib", "Oper",
//          "AND" );
//       nZRetCode = CreateEntity( *vQualObject, "QualAttrib", zPOS_AFTER );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib",
//          "EntityName", "GLChartEntry" );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib",
//          "AttributeName", "ChartEntryCode" );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib", "Value",
//          "111" );
//       nZRetCode = SetAttributeFromString( *vQualObject, "QualAttrib", "Oper", "=" );
//       return( 0 );
//    }
        return vQualObject;
    }
    
    
//
//
//     
//    #ifdef __cplusplus
//    }
//    #endif
//
}
