package com.quinsoft.opencuas;

import com.quinsoft.zeidon.CursorResult;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.vml.VmlOperation;


/*************************************************************************************************
**    
**    OPERATION: PERFORM_AuditReus
**    
*************************************************************************************************/
public class wFacultD extends VmlOperation
{
public wFacultD(Task task) {
		super(task);
		// TODO Auto-generated constructor stub
	}

	//LOCAL OPERATION
//PERFORM_AuditReus( VIEW ViewToWindow,
//                   VIEW mDegTrk  BASED ON LOD  mDegTrk )
    public int PERFORM_AuditReus( View viewToWindow, View mDegTrk )
    {
        assert mDegTrk.getViewOd().getName().equals( "mDegTrk" );
//
//   VIEW mStudenC      REGISTERED AS mStudenC
        View mStudenC = viewToWindow.getViewByName( "mStudenC" );
//   VIEW mStudent      REGISTERED AS mStudent
        View mStudent = viewToWindow.getViewByName( "mStudent" );
//   VIEW wStudent      REGISTERED AS wStudent
        View wStudent = viewToWindow.getViewByName( "wStudent" );
//   VIEW wXferO        REGISTERED AS wXferO
        View wXferO   = viewToWindow.getViewByName( "wStudent" );
        
//   VIEW TermslClsLstC BASED ON LOD  lClsLstC
//   VIEW mStuPSch      BASED ON LOD  mStuPSch
//   VIEW lDegTrk       BASED ON LOD  lDegTrk
      View TermslClsLstC;
      View mStuPSch;
      View lDegTrk;
      
//   STRING ( 200 ) szReportName
      String szReportName;
      
//   SHORT nRC
      int nRC;
      int RESULT;
//   
//   // Make sure an entry is selected and position on that entry for the audit.
//   IF wStudent.Work.WebDegreeAuditType = "P"    // Potential Audit needs lDegTrk selection.
      if ( wStudent.cursor( "Work" ).compareAttribute( "WebDegreeAuditType", "P" ) == 0 )
      {
//      GET VIEW lDegTrk NAMED "lDegTrk"
          lDegTrk = viewToWindow.getViewByName( "lDegTrk" );
//      SET CURSOR FIRST lDegTrk.DegreeTrack WHERE lDegTrk.DegreeTrack.wSelectFlag = "Y"
          RESULT = lDegTrk.cursor( "DegreeTrack" ).setFirst( "wSelectFlag", "Y").toInt();
//      IF RESULT < zCURSOR_SET
          if ( RESULT < CursorResult.SET.toInt() )
          {
//         MessageSend( ViewToWindow, "", "Perform Degree Audit", 
//                      "A Degree Track entry must be selected.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//         //SetWindowActionBehavior( ViewToWindow, zWAB_StayOnWindow, "", "" )
//         RETURN 2      // Return 2 to indicate Web client must prompt operator
              return 2;
          }
//      END
      }
//   END
//   
//   // Make sure that no current mDegTrk object exists.
//   GET VIEW mDegTrk NAMED "mDegTrk"
      mDegTrk = viewToWindow.getViewByName( "mDegTrk" );
      RESULT = mDegTrk == null ? -1 : 0;
//   IF RESULT >= 0
      if ( RESULT >= 0 )
      {
//      DropObjectInstance( mDegTrk )
          mDegTrk.drop();
//   END
      }
//   
//   // If this is an audit as a part of a proposed class registration, add the proposed classes to
//   // mStudenC.
//   IF wStudent.Work.WebDegreeAuditType = "S"
      if ( wStudent.cursor( "Work" ).compareAttribute( "WebDegreeAuditType", "S" ) == 0 )
      {
//      GET VIEW mStuPSch NAMED "mStuPSch"
          mStuPSch = viewToWindow.getViewByName( "mStuPSch" );
//      GET VIEW TermslClsLstC NAMED "TermslClsLstC"
          TermslClsLstC = viewToWindow.getViewByName( "TermslClsLstC" );
//      SET CURSOR LAST mStudenC.Registration
          mStudenC.cursor( "Registration" ).setLast();
//      FOR EACH mStuPSch.ProposedEnrollment WHERE mStuPSch.ProposedEnrollment.DeleteEntryFlag != "Y"
          EntityCursor cursor = mStuPSch.cursor( "ProposedEnrollment" );
          for ( RESULT = cursor.setFirst( "DeleteEntryFlag", "Y" ).toInt();
                RESULT >= CursorResult.SET.toInt();
                RESULT = cursor.setNextContinue().toInt() )
          {
//         CREATE ENTITY mStudenC.Registration
              mStudenC.cursor( "Registration" ).createEntity();
//         mStudenC.Registration.Status               = "T"
              mStudenC.cursor( "Registration" ).setAttribute( "Status", "T" );
//         mStudenC.Registration.CreditHours          = mStuPSch.ProposedEnrollment.CreditHours
//         mStudenC.Registration.wCopyFlag            = "Y"      // Use as flag for proposesd entry.
//         mStudenC.Registration.wCollegeYearSemester = TermslClsLstC.CollegeTerm.YearSemester 
//         mStudenC.Registration.wCollegeYear         = TermslClsLstC.CollegeYear.Year 
//         IF TermslClsLstC.CorrespondingCrossListedCourse EXISTS
              if (TermslClsLstC.cursor( "CorrespondingCrossListedCourse" ).checkExistenceOfEntity() == CursorResult.SET )
              {
                  
              }
              // ---- OR -----
              if (TermslClsLstC.cursor( "CorrespondingCrossListedCourse" ).isNull() == false )
              {
                  
              }
//            mStudenC.Registration.wCourseNumber = TermslClsLstC.CorrespondingCrossListedCourse.Number 
//         ELSE
//            mStudenC.Registration.wCourseNumber = TermslClsLstC.Course.Number 
//         END
//         SET CURSOR FIRST TermslClsLstC.Class WHERE TermslClsLstC.Class.ID = mStuPSch.Class.ID 
//         IF RESULT >= zCURSOR_SET
//            INCLUDE mStudenC.RegistrationClass FROM TermslClsLstC.Class
//         ELSE
//            MessageSend( ViewToWindow, "", "Perform Degree Audit", 
//                         "Programming Error: No match in Class list for Term.", zMSGQ_OBJECT_CONSTRAINT_ERROR, 0 )
//         END 
//      END
          }
//   END
      }
//
//   // First create a combined DegreeTrack object for the Major and Minor DegreeTracks selected.
//   // If no Minor track exists, the combined object will be simply the Major DegreeTrack.
//   // For potential Audits, we will simply activate mDegTrk from the lDegTrk selection.
//   IF wStudent.Work.WebDegreeAuditType = "P"
//      ACTIVATE mDegTrk WHERE mDegTrk.DegreeTrack.ID = lDegTrk.DegreeTrack.ID 
//      NAME VIEW mDegTrk "mDegTrk"
//   ELSE
//      CombineDegreeTracks( mDegTrk, mStudent )
//   END
//
//   // Then go to perform the Degree Audit functionality.
//   nRC = AuditStudentCourses( mDegTrk, mStudenC )
//   IF nRC >= 0
//      wXferO.Root.WEB_ErrorMessage = ""
//   ELSE
//      wXferO.Root.WEB_ErrorMessage = "An error occurred when running the Audit."
//      RETURN 2
//   END
//   
//   // If the Audit is "Proposed", remove proposed entries from mStudenC.
//   IF wStudent.Work.WebDegreeAuditType = "S"
//      FOR EACH mStudenC.Registration WHERE mStudenC.Registration.wCopyFlag = "Y"
//         DELETE ENTITY mStudenC.Registration NONE 
//      END
//   END
//   
//END
        return 0;
    }
}
