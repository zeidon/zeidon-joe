/**
 *
 */
package com.quinsoft.opencuas;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Level;
import com.quinsoft.zeidon.View;

/**
 * @author Zeidon generator.
 *
 */
public class RoomSchd_Dialog
{
    static public void PostbuildRoomSchedulingMenu( View viewToWindow )
    {
//        VIEW sAppMgr  BASED ON LOD sAppMgr
//        VIEW wXferO   BASED ON LOD wXferO
//        VIEW wXferO2  BASED ON LOD wXferO
//        VIEW lTermLST BASED ON LOD lTermLST
//        VIEW mBldgLST BASED ON LOD mBldg
//        VIEW vSubtask
//        INTEGER nRC
          View sAppMgr;
          View wXferO;
          View wXferO2;
          View lTermLST;
          View mBldgLST;
          View vSubtask;
          int  nRC;
          int  result;
//
//        // Perform multitasking tasks.
//        nRC = GetViewByName( wXferO, "wXferO", ViewToWindow, zLEVEL_TASK )
          wXferO = viewToWindow.getViewByName( "wXferO" );
          nRC = wXferO == null ? -1 : 0;

//        IF nRC < 0
          if ( nRC < 0 )
          {
//            nRC = GetViewByName( wXferO2, "wXferO", ViewToWindow, zLEVEL_APPLICATION )
              wXferO2 = viewToWindow.getViewByName( "wXferO", Level.APPLICATION );
              nRC = wXferO2 == null ? -1 : 0;

//            IF nRC >= 0
//               ActivateOI_FromOI( wXferO, wXferO2, zSINGLE )
//               NAME VIEW  wXferO "wXferO"
//            END
              if ( nRC >= 0 )
              {
                  wXferO = wXferO2.activateOiFromOi( ActivateFlags.fSINGLE );
                  wXferO.setName( "wXferO" );
              }
          }
//        END

//        IF wXferO.RoomScheduling EXISTS
//           DELETE ENTITY wXferO.RoomScheduling
//        END
          if ( wXferO.cursor( "RoomScheduling" ).getEntityInstance() != null )
          {
              wXferO.cursor( "RoomScheduling" ).deleteEntity();
          }

//        CREATE ENTITY wXferO.RoomScheduling
          wXferO.cursor( "RoomScheduling" ).createEntity();
//
//        GetViewByName( sAppMgr, "sAppMgr", ViewToWindow, zLEVEL_APPLICATION )
          sAppMgr = viewToWindow.getViewByName( "sAppMgr", Level.APPLICATION );

//        SetNameForView( ViewToWindow,
//                        sAppMgr.GeneralTask.SpecificDialogName, ViewToWindow, zLEVEL_APPLICATION )
          viewToWindow.setName( sAppMgr.cursor( "GeneralTask" ).getAttribute( "SpecificDialogName" ).getString(),
                                       Level.APPLICATION );
          // ---- OR ----
          String value = sAppMgr.cursor( "GeneralTask" ).getAttribute( "SpecificDialogName" ).getString();
          viewToWindow.setName( value, Level.APPLICATION );

//        ActivateDomainsForDivision( ViewToWindow )
//
//        // Make sure lTermLST exists and that we're positioned on Current Term.
//        GET VIEW lTermLST NAMED "lTermLST"
          lTermLST = viewToWindow.getViewByName( "lTermLST" );
          result = lTermLST == null ? -1 : 0;

//        IF RESULT < 0
          if ( result < 0 )
          {
//            ACTIVATE lTermLST Multiple
              lTermLST = viewToWindow.activateObjectInstance( "lTermLST", null, new ActivateOptions( viewToWindow ) );
//            NAME VIEW lTermLST "lTermLST"
              lTermLST.setName( "lTermLST" );
//            OrderEntityForView( lTermLST, "CollegeTerm", "CollegeYear.Year D Semester D" )
              lTermLST.cursor( "CollegeTerm" ).orderEntities( "CollegeYear.Year D Semester D" );
          }
//        END

//        SET CURSOR FIRST lTermLST.CollegeTerm WHERE lTermLST.CollegeTerm.CurrentTermFlag = "Y"
          lTermLST.cursor( "CollegeTerm" ).setFirst( "CurrentTermFlag", "Y");
//
//        // Activate Building/Rooms
//        ACTIVATE mBldgLST Multiple
//            RESTRICTING mBldgLST.Room TO mBldgLST.Room.Type = "C"
//        NAME VIEW mBldgLST "mBldgLST"

    }
}
