z01001Zeidon    c:\temp\zqFrame.por zqFrame 2022/06/09 12:21:24
mOIFLAGS    0
ezqFrame   1,2
aFirstLevelOperator,2 AND
aQueryObjectName,2 qStuEnr
aQueryObjectRootEntityName,2 Student
aResultType,2 P
aReportTitle,2 CINDY - Cumulative GPA for all Active Students
aContractedFlag,2 Y

eGeneralParameter 2,2
aEntityName,2 Student
aAttributeName,2 Status
aValue,2   A
aSearchType,2 =
aListDisplayFlag,2 
aMaxCardinality,2 0
aRootQualificationFlag,2 Y
aPrompt,2  Student.Status
aTitle,2   Status
aDataType,2 S
aOriginalOrder,2 1
aReportInterfaceFlag,2 Y
awBooleanConditionName,2 C1
awSelectedToShow,2 Y

eGeneralParameter 2,2
aEntityName,2 Person
aAttributeName,2 CampusID
aListDisplayFlag,2 Y
aMaxCardinality,2 1
aRootQualificationFlag,2 
aPrompt,2  ...Person.CampusID
aTitle,2   CampusID
aDataType,2 S
aDisplayOrder,2 1
aOriginalOrder,2 2
awSelectedToShow,2 Y

eGeneralParameter 2,2
aEntityName,2 Person
aAttributeName,2 LastName
aListDisplayFlag,2 Y
aMaxCardinality,2 1
aRootQualificationFlag,2 
aPrompt,2  ...Person.LastName
aTitle,2   LastName
aDataType,2 S
aDisplayOrder,2 2
aOriginalOrder,2 3
awSelectedToShow,2 Y

eGeneralParameter 2,2
aEntityName,2 Person
aAttributeName,2 FirstName
aListDisplayFlag,2 Y
aMaxCardinality,2 1
aRootQualificationFlag,2 
aPrompt,2  ...Person.FirstName
aTitle,2   FirstName
aDataType,2 S
aDisplayOrder,2 3
aOriginalOrder,2 4
awSelectedToShow,2 Y

eGeneralParameter 2,2
aEntityName,2 AcademicStanding
aAttributeName,2 CumulativeGPA
aListDisplayFlag,2 Y
aMaxCardinality,2 99
aPrompt,2  ...AcademicStanding.CumulativeGPA
aTitle,2   CumulativeGPA
aDataType,2 M
aDisplayOrder,2 4
aOriginalOrder,2 5
awSelectedToShow,2 Y

eGeneralParameter 2,2
aEntityName,2 AcademicStanding
aAttributeName,2 AttemptedCredits
aListDisplayFlag,2 Y
aMaxCardinality,2 99
aPrompt,2  ...AcademicStanding.AttemptedCredits
aTitle,2   AttemptedCredits
aDataType,2 M
aDisplayOrder,2 5
aOriginalOrder,2 6
awSelectedToShow,2 Y

eGeneralParameter 2,2
aEntityName,2 AcademicStandingCollegeTerm
aAttributeName,2 YearSemester
aValue,2   2020-2021 Fall
aSearchType,2 =
aSubgroupSearchType,2 ANY
aListDisplayFlag,2 Y
aMaxCardinality,2 1
aPrompt,2  ......AcademicStandingCollegeTerm.YearSemester
aTitle,2   YearSemester
aDataType,2 S
aDisplayOrder,2 6
aOriginalOrder,2 7
aScopingEntityName,2 AcademicStanding
aReportInterfaceFlag,2 Y
awSelectedToShow,2 Y

eScopingQueryEntity 3,38
aEntityName,2 AcademicStanding

eParentEntity 2,6
aEntityName,2 Student

eChildEntity 3,6
aEntityName,2 Person
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 Address
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 Demographics
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 Prospect
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 5,6
aEntityName,2 Counselor
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 CounselorPerson
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 FirstMajorChoice
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 SecondMajorChoice
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 ThirdMajorChoice
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 Church
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 AdministrativeDivision
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 EnrollmentWaiverTransfer
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 4,6
aEntityName,2 Class
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 Course
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 College
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 CollegeTerm
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 CollegeYear
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 Faculty
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 6,6
aEntityName,2 FacultyPrimaryCampusLocation
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 PrimaryFaculty
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 PrimaryFacultyCampusLocation
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 Misconduct
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 EnrollmentModification
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 4,6
aEntityName,2 User
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 EnrollmentModificationClass
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 StudentMajorDegreeTrack
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 4,6
aEntityName,2 DegreeTrack
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 DegreeMajor
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 Department
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 MajorCollege
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 7,6
aEntityName,2 CollegeGradeScale
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 Degree
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 6,6
aEntityName,2 DegreeYearData
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 7,6
aEntityName,2 DegreeGradeScale
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 DegreeCandidacy
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 GraduationDate
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 CeremonyGraduationDate
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 4,6
aEntityName,2 StudentMajorModesOfStudy
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 4,6
aEntityName,2 Advisor
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 AdvisorPerson
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 StudentMinorDegreeTrack
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 4,6
aEntityName,2 MinorDegreeTrack
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 5,6
aEntityName,2 DegreeMinor
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 AcademicStanding
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 4,6
aEntityName,2 AcademicStandingCollegeTerm
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eQueryAttribute 4,6
aAttributeName,2 CumulativeGPA
aDisplayOrder,2 4
aMappingEntityName,2 AcademicStanding
aPrompt,2  CumulativeGPA
aLength,2  20
aType,2    M

eQueryAttribute 4,6
aAttributeName,2 AttemptedCredits
aDisplayOrder,2 5
aMappingEntityName,2 AcademicStanding
aPrompt,2  AttemptedCredits
aLength,2  20
aType,2    M

eQueryAttribute 4,6
aAttributeName,2 YearSemester
aDisplayOrder,2 6
aMappingEntityName,2 AcademicStandingCollegeTerm
aPrompt,2  YearSemester
aLength,2  20
aType,2    S

eChildEntity 3,6
aEntityName,2 PrimaryCampusLocation
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 CampusLocation
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eChildEntity 3,6
aEntityName,2 CurrentDegreeEntryCollegeYear
aMaxCardinality,2 1
aLOD_MaxCardinality,2 1

eChildEntity 3,6
aEntityName,2 LeaveOfAbsence
aMaxCardinality,2 99
aLOD_MaxCardinality,2 99

eQueryAttribute 3,6
aAttributeName,2 CampusID
aDisplayOrder,2 1
aMappingEntityName,2 Person
aPrompt,2  CampusID
aLength,2  20
aType,2    S

eQueryAttribute 3,6
aAttributeName,2 LastName
aDisplayOrder,2 2
aMappingEntityName,2 Person
aPrompt,2  LastName
aLength,2  20
aType,2    S

eQueryAttribute 3,6
aAttributeName,2 FirstName
aDisplayOrder,2 3
aMappingEntityName,2 Person
aPrompt,2  FirstName
aLength,2  20
aType,2    S

eObjectEntity 2,2
aEntityName,2 Student
aMaxCardinality,2 0
aRootQualificationFlag,2 Y

eObjectEntity 2,2
aEntityName,2 Person
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Address
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Demographics
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Prospect
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 Counselor
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 CounselorPerson
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 FirstMajorChoice
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 SecondMajorChoice
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 ThirdMajorChoice
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Church
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 AdministrativeDivision
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 EnrollmentWaiverTransfer
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 Class
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Course
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 College
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 CollegeTerm
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 CollegeYear
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Faculty
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 FacultyPrimaryCampusLocation
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 PrimaryFaculty
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 PrimaryFacultyCampusLocation
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Misconduct
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 EnrollmentModification
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 User
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 EnrollmentModificationClass
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 StudentMajorDegreeTrack
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 DegreeTrack
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 DegreeMajor
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Department
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 MajorCollege
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 CollegeGradeScale
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 Degree
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 DegreeYearData
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 DegreeGradeScale
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 DegreeCandidacy
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 GraduationDate
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 CeremonyGraduationDate
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 StudentMajorModesOfStudy
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 Advisor
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 AdvisorPerson
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 StudentMinorDegreeTrack
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 MinorDegreeTrack
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 DegreeMinor
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 AcademicStanding
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 AcademicStandingCollegeTerm
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 PrimaryCampusLocation
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 CampusLocation
aMaxCardinality,2 99

eObjectEntity 2,2
aEntityName,2 CurrentDegreeEntryCollegeYear
aMaxCardinality,2 1

eObjectEntity 2,2
aEntityName,2 LeaveOfAbsence
aMaxCardinality,2 99

eBooleanExpression 2,2

eCondition 3,2
aEntityName,2 Student
aAttributeName,2 Status
aPrompt,2  Student.Status
aValue,2   A
aSearchType,2 =
aBooleanConditionName,2 C1
aFullSearchCriteria,2 = A

eScopingSourceQueryEntity 2,6
aEntityName,2 Student

eScopingSourceQueryEntity 2,6
aEntityName,2 Person

eScopingSourceQueryEntity 2,6
aEntityName,2 Address

eScopingSourceQueryEntity 2,6
aEntityName,2 Demographics

eScopingSourceQueryEntity 2,6
aEntityName,2 Prospect

eScopingSourceQueryEntity 2,6
aEntityName,2 Counselor

eScopingSourceQueryEntity 2,6
aEntityName,2 CounselorPerson

eScopingSourceQueryEntity 2,6
aEntityName,2 FirstMajorChoice

eScopingSourceQueryEntity 2,6
aEntityName,2 SecondMajorChoice

eScopingSourceQueryEntity 2,6
aEntityName,2 ThirdMajorChoice

eScopingSourceQueryEntity 2,6
aEntityName,2 Church

eScopingSourceQueryEntity 2,6
aEntityName,2 AdministrativeDivision

eScopingSourceQueryEntity 2,6
aEntityName,2 EnrollmentWaiverTransfer

eScopingSourceQueryEntity 2,6
aEntityName,2 Class

eScopingSourceQueryEntity 2,6
aEntityName,2 Course

eScopingSourceQueryEntity 2,6
aEntityName,2 College

eScopingSourceQueryEntity 2,6
aEntityName,2 CollegeTerm

eScopingSourceQueryEntity 2,6
aEntityName,2 CollegeYear

eScopingSourceQueryEntity 2,6
aEntityName,2 Faculty

eScopingSourceQueryEntity 2,6
aEntityName,2 FacultyPrimaryCampusLocation

eScopingSourceQueryEntity 2,6
aEntityName,2 PrimaryFaculty

eScopingSourceQueryEntity 2,6
aEntityName,2 PrimaryFacultyCampusLocation

eScopingSourceQueryEntity 2,6
aEntityName,2 Misconduct

eScopingSourceQueryEntity 2,6
aEntityName,2 EnrollmentModification

eScopingSourceQueryEntity 2,6
aEntityName,2 User

eScopingSourceQueryEntity 2,6
aEntityName,2 EnrollmentModificationClass

eScopingSourceQueryEntity 2,6
aEntityName,2 StudentMajorDegreeTrack

eScopingSourceQueryEntity 2,6
aEntityName,2 DegreeTrack

eScopingSourceQueryEntity 2,6
aEntityName,2 DegreeMajor

eScopingSourceQueryEntity 2,6
aEntityName,2 Department

eScopingSourceQueryEntity 2,6
aEntityName,2 MajorCollege

eScopingSourceQueryEntity 2,6
aEntityName,2 CollegeGradeScale

eScopingSourceQueryEntity 2,6
aEntityName,2 Degree

eScopingSourceQueryEntity 2,6
aEntityName,2 DegreeYearData

eScopingSourceQueryEntity 2,6
aEntityName,2 DegreeGradeScale

eScopingSourceQueryEntity 2,6
aEntityName,2 DegreeCandidacy

eScopingSourceQueryEntity 2,6
aEntityName,2 GraduationDate

eScopingSourceQueryEntity 2,6
aEntityName,2 CeremonyGraduationDate

eScopingSourceQueryEntity 2,6
aEntityName,2 StudentMajorModesOfStudy

eScopingSourceQueryEntity 2,6
aEntityName,2 Advisor

eScopingSourceQueryEntity 2,6
aEntityName,2 AdvisorPerson

eScopingSourceQueryEntity 2,6
aEntityName,2 StudentMinorDegreeTrack

eScopingSourceQueryEntity 2,6
aEntityName,2 MinorDegreeTrack

eScopingSourceQueryEntity 2,6
aEntityName,2 DegreeMinor

eScopingSourceQueryEntity 2,6

eScopingSourceQueryEntity 2,6
aEntityName,2 AcademicStandingCollegeTerm

eScopingSourceQueryEntity 2,6
aEntityName,2 PrimaryCampusLocation

eScopingSourceQueryEntity 2,6
aEntityName,2 CampusLocation

eScopingSourceQueryEntity 2,6
aEntityName,2 CurrentDegreeEntryCollegeYear

eScopingSourceQueryEntity 2,6
aEntityName,2 LeaveOfAbsence

eDisplayTreeEntity 2,2
aName,2    Student (0-m)
aEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    CurrentLevel

eDisplayTreeAttribute 3,2
aName,2    eMailAddress

eDisplayTreeAttribute 3,2
aName,2    ResidencyStatus

eDisplayTreeAttribute 3,2
aName,2    DateOfDeparture

eDisplayTreeAttribute 3,2
aName,2    EntryYearMonth

eDisplayTreeAttribute 3,2
aName,2    dMajors

eDisplayTreeAttribute 3,2
aName,2    dFullNameLFM

eDisplayTreeAttribute 3,2
aName,2    CreationDate

eDisplayTreeAttribute 3,2
aName,2    FinalGPA

eDisplayTreeAttribute 3,2
aName,2    dDegreeList

eDisplayTreeAttribute 3,2
aName,2    TotalEarnedCredits

eDisplayTreeAttribute 3,2
aName,2    dAttemptedCreditsTerm

eDisplayTreeAttribute 3,2
aName,2    dClassificationByTerm

eDisplayTreeAttribute 3,2
aName,2    dCollegeTermLoad

eDisplayTreeAttribute 3,2
aName,2    dCumulativeEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    dCumulativeGPA_Term

eDisplayTreeAttribute 3,2
aName,2    dDepartment

eDisplayTreeAttribute 3,2
aName,2    dDegrees

eDisplayTreeAttribute 3,2
aName,2    dEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    dFinalGPA

eDisplayTreeAttribute 3,2
aName,2    dGradeScaleType

eDisplayTreeAttribute 3,2
aName,2    DirectoryDataBlock

eDisplayTreeAttribute 3,2
aName,2    dMajor

eDisplayTreeAttribute 3,2
aName,2    dPartTimeFullTimeFlag

eDisplayTreeAttribute 3,2
aName,2    dTestTest

eDisplayTreeAttribute 3,2
aName,2    dTotalCredits

eDisplayTreeAttribute 3,2
aName,2    dLastProspectAdmittedDate

eDisplayTreeAttribute 3,2
aName,2    dLastProspectAdmittedYearMonth

eDisplayTreeAttribute 3,2
aName,2    dLastProspectAdmittedYearWeek

eDisplayTreeAttribute 3,2
aName,2    wAssociateCompletionCategory

eDisplayTreeAttribute 3,2
aName,2    wAttemptedCredits

eDisplayTreeAttribute 3,2
aName,2    wBachelorCompletionCategory

eDisplayTreeAttribute 3,2
aName,2    Custom1

eDisplayTreeAttribute 3,2
aName,2    Custom2

eDisplayTreeAttribute 3,2
aName,2    Custom3

eDisplayTreeAttribute 3,2
aName,2    LS_StudentID

eDisplayTreeAttribute 3,2
aName,2    wCumEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    wCumulativeGPA

eDisplayTreeAttribute 3,2
aName,2    wEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    wEnrolledForTerm

eDisplayTreeAttribute 3,2
aName,2    wEnrollmentRequestDate

eDisplayTreeAttribute 3,2
aName,2    wGradAttemptedCredits

eDisplayTreeAttribute 3,2
aName,2    wGradCumEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    wGradCumulativeGPA

eDisplayTreeAttribute 3,2
aName,2    wGradEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    wGradFinalGPA

eDisplayTreeAttribute 3,2
aName,2    wGradStartDate

eDisplayTreeAttribute 3,2
aName,2    wGradTotalEarnedCredits

eDisplayTreeAttribute 3,2
aName,2    WithdrawalReason

eDisplayTreeAttribute 3,2
aName,2    wLastCohort

eDisplayTreeAttribute 3,2
aName,2    wMadeJuniorLevelFlag

eDisplayTreeAttribute 3,2
aName,2    wMadeSeniorLevelFlag

eDisplayTreeAttribute 3,2
aName,2    wMadeSophomoreLevelFlag

eDisplayTreeAttribute 3,2
aName,2    wMasterCompletionCategory

eDisplayTreeAttribute 3,2
aName,2    wProcessedFlag

eDisplayTreeAttribute 3,2
aName,2    wStartingCohort

eDisplayTreeAttribute 3,2
aName,2    wStartingLocation

eDisplayTreeAttribute 3,2
aName,2    wUgradAttemptedCredits

eDisplayTreeAttribute 3,2
aName,2    wUgradCumEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    wUgradCumulativeGPA

eDisplayTreeAttribute 3,2
aName,2    wUgradEarnedCreditsByTerm

eDisplayTreeAttribute 3,2
aName,2    wUgradFinalGPA

eDisplayTreeAttribute 3,2
aName,2    wUndergradStartDate

eDisplayTreeAttribute 3,2
aName,2    wUndergradTotalEarnedCredits

eDisplayTreeAttribute 3,2
aName,2    wUserName

eDisplayTreeEntity 2,2
aName,2    .....Person (0-1)
aEntityName,2 Person
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    InstID

eDisplayTreeAttribute 3,2
aName,2    CampusID

eDisplayTreeAttribute 3,2
aName,2    DateOfBirth

eDisplayTreeAttribute 3,2
aName,2    WorkPhone

eDisplayTreeAttribute 3,2
aName,2    eMailAddress

eDisplayTreeAttribute 3,2
aName,2    WorkEMailAddress

eDisplayTreeAttribute 3,2
aName,2    PreferredEmail

eDisplayTreeAttribute 3,2
aName,2    HomePhone

eDisplayTreeAttribute 3,2
aName,2    Gender

eDisplayTreeAttribute 3,2
aName,2    LastName

eDisplayTreeAttribute 3,2
aName,2    FirstName

eDisplayTreeAttribute 3,2
aName,2    MiddleName

eDisplayTreeAttribute 3,2
aName,2    PreferedFirstName

eDisplayTreeAttribute 3,2
aName,2    Photo

eDisplayTreeAttribute 3,2
aName,2    Suffix

eDisplayTreeAttribute 3,2
aName,2    Salutation

eDisplayTreeAttribute 3,2
aName,2    ChurchDenomination

eDisplayTreeAttribute 3,2
aName,2    dFullNameLFM

eDisplayTreeEntity 2,2
aName,2    ..........Address (0-1)
aEntityName,2 Address
aParentEntityName,2 Person

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Line1

eDisplayTreeAttribute 3,2
aName,2    Line2

eDisplayTreeAttribute 3,2
aName,2    Line3

eDisplayTreeAttribute 3,2
aName,2    City

eDisplayTreeAttribute 3,2
aName,2    StateProvince

eDisplayTreeAttribute 3,2
aName,2    Country

eDisplayTreeAttribute 3,2
aName,2    County

eDisplayTreeAttribute 3,2
aName,2    PostalCode

eDisplayTreeAttribute 3,2
aName,2    Phone1

eDisplayTreeAttribute 3,2
aName,2    Latitude

eDisplayTreeAttribute 3,2
aName,2    Longitude

eDisplayTreeAttribute 3,2
aName,2    GeographicPositionZipCode

eDisplayTreeAttribute 3,2
aName,2    GeographicPositionMiles

eDisplayTreeEntity 2,2
aName,2    ..........Demographics (0-1)
aEntityName,2 Demographics
aParentEntityName,2 Person

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    IPEDS_Ethnicity

eDisplayTreeAttribute 3,2
aName,2    CitizenshipCode

eDisplayTreeAttribute 3,2
aName,2    PrimaryLanguage

eDisplayTreeAttribute 3,2
aName,2    PreferredLanguage

eDisplayTreeAttribute 3,2
aName,2    AlienRegNumber

eDisplayTreeAttribute 3,2
aName,2    Ethnicity

eDisplayTreeAttribute 3,2
aName,2    NationOfCitizenship

eDisplayTreeEntity 2,2
aName,2    ..........Prospect (0-m)
aEntityName,2 Prospect
aParentEntityName,2 Person

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    HSGraduationYear

eDisplayTreeAttribute 3,2
aName,2    ApplicationDate

eDisplayTreeAttribute 3,2
aName,2    AdmissionDate

eDisplayTreeAttribute 3,2
aName,2    DepositDate

eDisplayTreeAttribute 3,2
aName,2    WithdrawDate

eDisplayTreeAttribute 3,2
aName,2    InquiryDate

eDisplayTreeAttribute 3,2
aName,2    LeadDate

eDisplayTreeAttribute 3,2
aName,2    ApplicationFeePaid

eDisplayTreeAttribute 3,2
aName,2    EntranceClassification

eDisplayTreeAttribute 3,2
aName,2    InitEntranceClassification

eDisplayTreeAttribute 3,2
aName,2    ExpectedEntryTerm

eDisplayTreeAttribute 3,2
aName,2    ExpectedEntryYear

eDisplayTreeAttribute 3,2
aName,2    InitialEntryYear

eDisplayTreeAttribute 3,2
aName,2    Resident

eDisplayTreeAttribute 3,2
aName,2    AcceptanceStatus

eDisplayTreeAttribute 3,2
aName,2    StudentID

eDisplayTreeEntity 2,2
aName,2    ...............Counselor (0-1)
aEntityName,2 Counselor
aParentEntityName,2 Prospect

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    CreatedBy

eDisplayTreeAttribute 3,2
aName,2    LastModifiedBy

eDisplayTreeEntity 2,2
aName,2    ....................CounselorPerson (0-1)
aEntityName,2 CounselorPerson
aParentEntityName,2 Counselor

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    LastName

eDisplayTreeAttribute 3,2
aName,2    FirstName

eDisplayTreeAttribute 3,2
aName,2    MiddleName

eDisplayTreeAttribute 3,2
aName,2    Suffix

eDisplayTreeAttribute 3,2
aName,2    MaidenName

eDisplayTreeAttribute 3,2
aName,2    Gender

eDisplayTreeAttribute 3,2
aName,2    HomePhone

eDisplayTreeEntity 2,2
aName,2    ...............FirstMajorChoice (0-1)
aEntityName,2 FirstMajorChoice
aParentEntityName,2 Prospect

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ...............SecondMajorChoice (0-1)
aEntityName,2 SecondMajorChoice
aParentEntityName,2 Prospect

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ...............ThirdMajorChoice (0-1)
aEntityName,2 ThirdMajorChoice
aParentEntityName,2 Prospect

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    InactiveFlag

eDisplayTreeAttribute 3,2
aName,2    WebSiteHyperLinkCode

eDisplayTreeEntity 2,2
aName,2    ..........Church (0-1)
aEntityName,2 Church
aParentEntityName,2 Person

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    SpecialType

eDisplayTreeAttribute 3,2
aName,2    Phone

eDisplayTreeAttribute 3,2
aName,2    DistrictCode

eDisplayTreeAttribute 3,2
aName,2    Denomination

eDisplayTreeEntity 2,2
aName,2    .....AdministrativeDivision (0-1)
aEntityName,2 AdministrativeDivision
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    .....EnrollmentWaiverTransfer (0-m)
aEntityName,2 EnrollmentWaiverTransfer
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    DeliveryMethod

eDisplayTreeAttribute 3,2
aName,2    FinalGradePct

eDisplayTreeAttribute 3,2
aName,2    FinalGradePoint

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    FinalGrade

eDisplayTreeAttribute 3,2
aName,2    TakingClassType

eDisplayTreeAttribute 3,2
aName,2    CreditHours

eDisplayTreeAttribute 3,2
aName,2    DroppedDate

eDisplayTreeAttribute 3,2
aName,2    GradUndergradOverrideFlag

eDisplayTreeAttribute 3,2
aName,2    dCollegeType

eDisplayTreeEntity 2,2
aName,2    ..........Class (0-1)
aEntityName,2 Class
aParentEntityName,2 EnrollmentWaiverTransfer

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    ClassDeliveryMethod

eDisplayTreeAttribute 3,2
aName,2    Section

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    CancelledDate

eDisplayTreeAttribute 3,2
aName,2    CreditHours

eDisplayTreeAttribute 3,2
aName,2    ClassStartDate

eDisplayTreeAttribute 3,2
aName,2    ClassEndDate

eDisplayTreeEntity 2,2
aName,2    ...............Course (0-1)
aEntityName,2 Course
aParentEntityName,2 Class

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Number

eDisplayTreeAttribute 3,2
aName,2    Title

eDisplayTreeEntity 2,2
aName,2    ....................College (0-1)
aEntityName,2 College
aParentEntityName,2 Course

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeEntity 2,2
aName,2    ...............CollegeTerm (0-1)
aEntityName,2 CollegeTerm
aParentEntityName,2 Class

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Semester

eDisplayTreeAttribute 3,2
aName,2    YearSemester

eDisplayTreeAttribute 3,2
aName,2    CourseStartDate

eDisplayTreeAttribute 3,2
aName,2    CourseEndDate

eDisplayTreeEntity 2,2
aName,2    ....................CollegeYear (0-1)
aEntityName,2 CollegeYear
aParentEntityName,2 CollegeTerm

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Year

eDisplayTreeEntity 2,2
aName,2    ...............Faculty (0-m)
aEntityName,2 Faculty
aParentEntityName,2 Class

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    ShortName

eDisplayTreeAttribute 3,2
aName,2    AdministrativeFacultyFlag

eDisplayTreeAttribute 3,2
aName,2    DepartmentHeadFlag

eDisplayTreeAttribute 3,2
aName,2    DivisionChairFlag

eDisplayTreeAttribute 3,2
aName,2    AdjunctFlag

eDisplayTreeAttribute 3,2
aName,2    PartTimeFlag

eDisplayTreeAttribute 3,2
aName,2    CreatedDateTime

eDisplayTreeAttribute 3,2
aName,2    ModifiedDateTime

eDisplayTreeAttribute 3,2
aName,2    CreatedBy

eDisplayTreeAttribute 3,2
aName,2    LastModifiedBy

eDisplayTreeEntity 2,2
aName,2    ....................FacultyPrimaryCampusLocation (0-1)
aEntityName,2 FacultyPrimaryCampusLocation
aParentEntityName,2 Faculty

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    PublicFlag

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Description

eDisplayTreeAttribute 3,2
aName,2    Code

eDisplayTreeAttribute 3,2
aName,2    ReportCode

eDisplayTreeEntity 2,2
aName,2    ...............PrimaryFaculty (0-1)
aEntityName,2 PrimaryFaculty
aParentEntityName,2 Class

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    ShortName

eDisplayTreeAttribute 3,2
aName,2    AdministrativeFacultyFlag

eDisplayTreeAttribute 3,2
aName,2    DepartmentHeadFlag

eDisplayTreeAttribute 3,2
aName,2    DivisionChairFlag

eDisplayTreeAttribute 3,2
aName,2    AdjunctFlag

eDisplayTreeAttribute 3,2
aName,2    PartTimeFlag

eDisplayTreeAttribute 3,2
aName,2    CreatedDateTime

eDisplayTreeAttribute 3,2
aName,2    ModifiedDateTime

eDisplayTreeAttribute 3,2
aName,2    CreatedBy

eDisplayTreeAttribute 3,2
aName,2    LastModifiedBy

eDisplayTreeEntity 2,2
aName,2    ....................PrimaryFacultyCampusLocation (0-1)
aEntityName,2 PrimaryFacultyCampusLocation
aParentEntityName,2 PrimaryFaculty

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    PublicFlag

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Description

eDisplayTreeAttribute 3,2
aName,2    Code

eDisplayTreeAttribute 3,2
aName,2    ReportCode

eDisplayTreeEntity 2,2
aName,2    ..........Misconduct (0-1)
aEntityName,2 Misconduct
aParentEntityName,2 EnrollmentWaiverTransfer

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    Notes

eDisplayTreeAttribute 3,2
aName,2    Action

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeEntity 2,2
aName,2    .....EnrollmentModification (0-m)
aEntityName,2 EnrollmentModification
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    CourseNumber

eDisplayTreeAttribute 3,2
aName,2    YearSemester

eDisplayTreeAttribute 3,2
aName,2    ModifiedDateTime

eDisplayTreeAttribute 3,2
aName,2    NewStatus

eDisplayTreeAttribute 3,2
aName,2    OriginalStatus

eDisplayTreeAttribute 3,2
aName,2    NewFinalGrade

eDisplayTreeAttribute 3,2
aName,2    OriginalFinalGrade

eDisplayTreeEntity 2,2
aName,2    ..........User (0-1)
aEntityName,2 User
aParentEntityName,2 EnrollmentModification

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    UserName

eDisplayTreeEntity 2,2
aName,2    ..........EnrollmentModificationClass (0-1)
aEntityName,2 EnrollmentModificationClass
aParentEntityName,2 EnrollmentModification

eDisplayTreeAttribute 3,2
aName,2    AutoSchedulingThresholdPercent

eDisplayTreeAttribute 3,2
aName,2    BookInformation

eDisplayTreeAttribute 3,2
aName,2    CancelledDate

eDisplayTreeAttribute 3,2
aName,2    CancelledReason

eDisplayTreeAttribute 3,2
aName,2    ClassDeliveryMethod

eDisplayTreeAttribute 3,2
aName,2    ClassEndDate

eDisplayTreeAttribute 3,2
aName,2    ClassReportingDate

eDisplayTreeAttribute 3,2
aName,2    ClassStartDate

eDisplayTreeAttribute 3,2
aName,2    ConvCourseTopic

eDisplayTreeAttribute 3,2
aName,2    CourseTitle

eDisplayTreeAttribute 3,2
aName,2    CreditHours

eDisplayTreeAttribute 3,2
aName,2    DirectedStudyFlag

eDisplayTreeAttribute 3,2
aName,2    DirectedStudyInitByCollegeFlag

eDisplayTreeAttribute 3,2
aName,2    DistributedDate

eDisplayTreeAttribute 3,2
aName,2    DistributedNote

eDisplayTreeAttribute 3,2
aName,2    DistributionDate

eDisplayTreeAttribute 3,2
aName,2    EnrollmentLimit

eDisplayTreeAttribute 3,2
aName,2    FacultyGradeEntryAllowsIP_Flag

eDisplayTreeAttribute 3,2
aName,2    FacultyGradeEntryNote

eDisplayTreeAttribute 3,2
aName,2    FacultyHasSubmittedGradesFlag

eDisplayTreeAttribute 3,2
aName,2    FacultyHasSubmittedMidtermGFlag

eDisplayTreeAttribute 3,2
aName,2    FacultyMidtermGradeEntryNote

eDisplayTreeAttribute 3,2
aName,2    FacultyNote

eDisplayTreeAttribute 3,2
aName,2    FinalExamDate

eDisplayTreeAttribute 3,2
aName,2    FinalExamEndTime

eDisplayTreeAttribute 3,2
aName,2    FinalExamStartTime

eDisplayTreeAttribute 3,2
aName,2    GradesVerifiedFlag

eDisplayTreeAttribute 3,2
aName,2    GradesVerifiedUserName

eDisplayTreeAttribute 3,2
aName,2    HistoricalEnrollmentCount

eDisplayTreeAttribute 3,2
aName,2    HistoricalWaitlistedCount

eDisplayTreeAttribute 3,2
aName,2    HonorsFlag

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    IndependentStudyFlag

eDisplayTreeAttribute 3,2
aName,2    LabFee

eDisplayTreeAttribute 3,2
aName,2    LMSFlag

eDisplayTreeAttribute 3,2
aName,2    LS_ClassID

eDisplayTreeAttribute 3,2
aName,2    LS_CourseID

eDisplayTreeAttribute 3,2
aName,2    LS_InstructorName

eDisplayTreeAttribute 3,2
aName,2    MidtermGradesVerifiedFlag

eDisplayTreeAttribute 3,2
aName,2    MidtermGradesVerifiedUserName

eDisplayTreeAttribute 3,2
aName,2    NoFinalExamFlag

eDisplayTreeAttribute 3,2
aName,2    NoShowRosterNote

eDisplayTreeAttribute 3,2
aName,2    NoShowRosterSubmittedFlag

eDisplayTreeAttribute 3,2
aName,2    NoShowRosterVerifiedFlag

eDisplayTreeAttribute 3,2
aName,2    Note

eDisplayTreeAttribute 3,2
aName,2    NumberOfReservedSeats

eDisplayTreeAttribute 3,2
aName,2    OverrideClassTuition

eDisplayTreeAttribute 3,2
aName,2    OverrideDeliveryMethod

eDisplayTreeAttribute 3,2
aName,2    OverrideFlatPrice

eDisplayTreeAttribute 3,2
aName,2    OverridePricePerCreditHour

eDisplayTreeAttribute 3,2
aName,2    PassFailClass

eDisplayTreeAttribute 3,2
aName,2    PreviousCourseID

eDisplayTreeAttribute 3,2
aName,2    PricePerClass

eDisplayTreeAttribute 3,2
aName,2    SchedulingNote

eDisplayTreeAttribute 3,2
aName,2    Section

eDisplayTreeAttribute 3,2
aName,2    SpecialPermissionFlag

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    Syllabus

eDisplayTreeAttribute 3,2
aName,2    TotalSessions

eDisplayTreeAttribute 3,2
aName,2    VariableCreditFlag

eDisplayTreeAttribute 3,2
aName,2    NoLMSExportFlag

eDisplayTreeAttribute 3,2
aName,2    AuditFlag

eDisplayTreeEntity 2,2
aName,2    .....StudentMajorDegreeTrack (0-m)
aEntityName,2 StudentMajorDegreeTrack
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    EndDate

eDisplayTreeAttribute 3,2
aName,2    StartDate

eDisplayTreeAttribute 3,2
aName,2    GraduationDate

eDisplayTreeAttribute 3,2
aName,2    Ranking

eDisplayTreeAttribute 3,2
aName,2    DegreePathway

eDisplayTreeAttribute 3,2
aName,2    ExamBoardRecommendation

eDisplayTreeAttribute 3,2
aName,2    TranscriptMajorOverride

eDisplayTreeAttribute 3,2
aName,2    wSortDate

eDisplayTreeEntity 2,2
aName,2    ..........DegreeTrack (0-1)
aEntityName,2 DegreeTrack
aParentEntityName,2 StudentMajorDegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    ShortName

eDisplayTreeAttribute 3,2
aName,2    TranscriptDisplayName

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ...............DegreeMajor (0-1)
aEntityName,2 DegreeMajor
aParentEntityName,2 DegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ....................Department (0-1)
aEntityName,2 Department
aParentEntityName,2 DegreeMajor

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ....................MajorCollege (0-1)
aEntityName,2 MajorCollege
aParentEntityName,2 DegreeMajor

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeEntity 2,2
aName,2    .........................CollegeGradeScale (0-1)
aEntityName,2 CollegeGradeScale
aParentEntityName,2 MajorCollege

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ...............Degree (0-1)
aEntityName,2 Degree
aParentEntityName,2 DegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ....................DegreeYearData (0-m)
aEntityName,2 DegreeYearData
aParentEntityName,2 Degree

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    FromYear

eDisplayTreeAttribute 3,2
aName,2    ThroughYear

eDisplayTreeAttribute 3,2
aName,2    MinimumCreditsRequired

eDisplayTreeEntity 2,2
aName,2    .........................DegreeGradeScale (0-1)
aEntityName,2 DegreeGradeScale
aParentEntityName,2 DegreeYearData

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeEntity 2,2
aName,2    ..........DegreeCandidacy (0-1)
aEntityName,2 DegreeCandidacy
aParentEntityName,2 StudentMajorDegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    StudentHeightMeters

eDisplayTreeAttribute 3,2
aName,2    ReceivedDate

eDisplayTreeAttribute 3,2
aName,2    DiplomaFullName

eDisplayTreeAttribute 3,2
aName,2    DiplomaReceivedDate

eDisplayTreeAttribute 3,2
aName,2    DiplomaOrderSentFlag

eDisplayTreeAttribute 3,2
aName,2    DiplomaOrderSentDate

eDisplayTreeAttribute 3,2
aName,2    LateSubmissionFlag

eDisplayTreeAttribute 3,2
aName,2    StudentHeightFeet

eDisplayTreeAttribute 3,2
aName,2    StudentHeightInches

eDisplayTreeAttribute 3,2
aName,2    GownCut

eDisplayTreeAttribute 3,2
aName,2    GownOrderSentFlag

eDisplayTreeAttribute 3,2
aName,2    GownOrderSentDate

eDisplayTreeAttribute 3,2
aName,2    GradParticipationFlag

eDisplayTreeAttribute 3,2
aName,2    PetitionToParticipateState

eDisplayTreeEntity 2,2
aName,2    ...............GraduationDate (0-1)
aEntityName,2 GraduationDate
aParentEntityName,2 DegreeCandidacy

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Date

eDisplayTreeAttribute 3,2
aName,2    Year

eDisplayTreeAttribute 3,2
aName,2    CeremonyFlag

eDisplayTreeAttribute 3,2
aName,2    ActiveFlag

eDisplayTreeAttribute 3,2
aName,2    DegreeCandidacyDeadlineDate

eDisplayTreeEntity 2,2
aName,2    ...............CeremonyGraduationDate (0-1)
aEntityName,2 CeremonyGraduationDate
aParentEntityName,2 DegreeCandidacy

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Date

eDisplayTreeAttribute 3,2
aName,2    Year

eDisplayTreeAttribute 3,2
aName,2    CeremonyFlag

eDisplayTreeAttribute 3,2
aName,2    ActiveFlag

eDisplayTreeAttribute 3,2
aName,2    DegreeCandidacyDeadlineDate

eDisplayTreeEntity 2,2
aName,2    ..........StudentMajorModesOfStudy (0-m)
aEntityName,2 StudentMajorModesOfStudy
aParentEntityName,2 StudentMajorDegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    ModeOfStudy

eDisplayTreeEntity 2,2
aName,2    ..........Advisor (0-1)
aEntityName,2 Advisor
aParentEntityName,2 StudentMajorDegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    ShortName

eDisplayTreeAttribute 3,2
aName,2    AdministrativeFacultyFlag

eDisplayTreeAttribute 3,2
aName,2    DepartmentHeadFlag

eDisplayTreeAttribute 3,2
aName,2    DivisionChairFlag

eDisplayTreeAttribute 3,2
aName,2    AdjunctFlag

eDisplayTreeAttribute 3,2
aName,2    PartTimeFlag

eDisplayTreeAttribute 3,2
aName,2    CreatedDateTime

eDisplayTreeAttribute 3,2
aName,2    ModifiedDateTime

eDisplayTreeAttribute 3,2
aName,2    CreatedBy

eDisplayTreeAttribute 3,2
aName,2    LastModifiedBy

eDisplayTreeEntity 2,2
aName,2    ...............AdvisorPerson (0-1)
aEntityName,2 AdvisorPerson
aParentEntityName,2 Advisor

eDisplayTreeAttribute 3,2
aName,2    CallbackDate

eDisplayTreeAttribute 3,2
aName,2    CampusID

eDisplayTreeAttribute 3,2
aName,2    ChurchDenomination

eDisplayTreeAttribute 3,2
aName,2    CIP_SubCategory

eDisplayTreeAttribute 3,2
aName,2    ContactPreference

eDisplayTreeAttribute 3,2
aName,2    ConvertedKey

eDisplayTreeAttribute 3,2
aName,2    ConvertedString

eDisplayTreeAttribute 3,2
aName,2    CreatedBy

eDisplayTreeAttribute 3,2
aName,2    CreatedDateTime

eDisplayTreeAttribute 3,2
aName,2    DateOfBirth

eDisplayTreeAttribute 3,2
aName,2    Deceased

eDisplayTreeAttribute 3,2
aName,2    DeceasedDate

eDisplayTreeAttribute 3,2
aName,2    DriversLicenseNumber

eDisplayTreeAttribute 3,2
aName,2    DriversLicenseState

eDisplayTreeAttribute 3,2
aName,2    eMailAddress

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressCity

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressCountry

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressLine1

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressLine2

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressLine3

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressState

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactAddressZipCode

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactEmail

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactHomePhone

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactLastUpdDateTime

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactMobilePhone

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactName

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactNote

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactRelationship

eDisplayTreeAttribute 3,2
aName,2    EmergencyContactWorkPhone

eDisplayTreeAttribute 3,2
aName,2    Extension

eDisplayTreeAttribute 3,2
aName,2    Fax

eDisplayTreeAttribute 3,2
aName,2    FirstName

eDisplayTreeAttribute 3,2
aName,2    FiscalIDRG

eDisplayTreeAttribute 3,2
aName,2    DataPermissionReceived

eDisplayTreeAttribute 3,2
aName,2    Gender

eDisplayTreeAttribute 3,2
aName,2    GovernmentID

eDisplayTreeAttribute 3,2
aName,2    HideSSN

eDisplayTreeAttribute 3,2
aName,2    HomePhone

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    InstID

eDisplayTreeAttribute 3,2
aName,2    InternationalHomePhone

eDisplayTreeAttribute 3,2
aName,2    InternationalMobilePhone

eDisplayTreeAttribute 3,2
aName,2    InternationalWorkPhone

eDisplayTreeAttribute 3,2
aName,2    IsConvictedOfFelony

eDisplayTreeAttribute 3,2
aName,2    IsVeteran

eDisplayTreeAttribute 3,2
aName,2    LastDuplicateAnalyzedDate

eDisplayTreeAttribute 3,2
aName,2    LastModifiedBy

eDisplayTreeAttribute 3,2
aName,2    LastName

eDisplayTreeAttribute 3,2
aName,2    MaidenName

eDisplayTreeAttribute 3,2
aName,2    MajorName

eDisplayTreeAttribute 3,2
aName,2    MaritalStatus

eDisplayTreeAttribute 3,2
aName,2    MaritalStatusDate

eDisplayTreeAttribute 3,2
aName,2    MasterPromissorySignedDate

eDisplayTreeAttribute 3,2
aName,2    MiddleName

eDisplayTreeAttribute 3,2
aName,2    MobilePhone

eDisplayTreeAttribute 3,2
aName,2    ModifiedDateTime

eDisplayTreeAttribute 3,2
aName,2    Note

eDisplayTreeAttribute 3,2
aName,2    Occupation

eDisplayTreeAttribute 3,2
aName,2    OccupationCode

eDisplayTreeAttribute 3,2
aName,2    OrdainedFlag

eDisplayTreeAttribute 3,2
aName,2    ParentsEmailAddress

eDisplayTreeAttribute 3,2
aName,2    PatronBarcode

eDisplayTreeAttribute 3,2
aName,2    PatronType

eDisplayTreeAttribute 3,2
aName,2    Photo

eDisplayTreeAttribute 3,2
aName,2    PreferedFirstName

eDisplayTreeAttribute 3,2
aName,2    PreferredEmail

eDisplayTreeAttribute 3,2
aName,2    Prefix

eDisplayTreeAttribute 3,2
aName,2    PhotoRelease

eDisplayTreeAttribute 3,2
aName,2    PreviousName

eDisplayTreeAttribute 3,2
aName,2    RetiredPastorFlag

eDisplayTreeAttribute 3,2
aName,2    Salutation

eDisplayTreeAttribute 3,2
aName,2    SchoolEmail

eDisplayTreeAttribute 3,2
aName,2    SSN

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    Suffix

eDisplayTreeAttribute 3,2
aName,2    Title

eDisplayTreeAttribute 3,2
aName,2    UserName

eDisplayTreeAttribute 3,2
aName,2    WebPassword

eDisplayTreeAttribute 3,2
aName,2    WorkEmail

eDisplayTreeAttribute 3,2
aName,2    WorkEMailAddress

eDisplayTreeAttribute 3,2
aName,2    WorkPhone

eDisplayTreeAttribute 3,2
aName,2    ExternalFinanceID

eDisplayTreeEntity 2,2
aName,2    .....StudentMinorDegreeTrack (0-m)
aEntityName,2 StudentMinorDegreeTrack
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeEntity 2,2
aName,2    ..........MinorDegreeTrack (0-1)
aEntityName,2 MinorDegreeTrack
aParentEntityName,2 StudentMinorDegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Description

eDisplayTreeAttribute 3,2
aName,2    DegreeCode

eDisplayTreeAttribute 3,2
aName,2    ExceptionFlag

eDisplayTreeAttribute 3,2
aName,2    Type

eDisplayTreeAttribute 3,2
aName,2    MinimumCredit

eDisplayTreeAttribute 3,2
aName,2    MinimumGPA

eDisplayTreeAttribute 3,2
aName,2    Status

eDisplayTreeAttribute 3,2
aName,2    TranscriptDisplayName

eDisplayTreeAttribute 3,2
aName,2    AuditTotalCreditsTaken

eDisplayTreeAttribute 3,2
aName,2    AuditTotalCreditsEarned

eDisplayTreeAttribute 3,2
aName,2    AuditGraduationCredits

eDisplayTreeAttribute 3,2
aName,2    AuditDevelopmentalCredits

eDisplayTreeAttribute 3,2
aName,2    AuditEnrolledCredits

eDisplayTreeAttribute 3,2
aName,2    AuditEnrolledCreditsRepeating

eDisplayTreeAttribute 3,2
aName,2    AuditPreTransferCredits

eDisplayTreeAttribute 3,2
aName,2    AuditMinimumCreditsRequired

eDisplayTreeAttribute 3,2
aName,2    AuditGPA

eDisplayTreeAttribute 3,2
aName,2    AuditMajorGPA

eDisplayTreeEntity 2,2
aName,2    ...............DegreeMinor (0-1)
aEntityName,2 DegreeMinor
aParentEntityName,2 MinorDegreeTrack

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    TranscriptNameDisplay

eDisplayTreeAttribute 3,2
aName,2    Description

eDisplayTreeAttribute 3,2
aName,2    InactiveFlag

eDisplayTreeAttribute 3,2
aName,2    DegreeCode

eDisplayTreeEntity 2,2
aName,2    .....AcademicStanding (0-m)
aEntityName,2 AcademicStanding
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    SemesterGPA

eDisplayTreeAttribute 3,2
aName,2    CumulativeGPA

eDisplayTreeAttribute 3,2
aName,2    AttemptedCredits

eDisplayTreeAttribute 3,2
aName,2    DeansListAttemptedCredits

eDisplayTreeAttribute 3,2
aName,2    AcademicLevel

eDisplayTreeAttribute 3,2
aName,2    RecommendedStanding

eDisplayTreeAttribute 3,2
aName,2    AcademicStanding

eDisplayTreeAttribute 3,2
aName,2    FinancialStanding

eDisplayTreeAttribute 3,2
aName,2    DeansListFlag

eDisplayTreeAttribute 3,2
aName,2    HonorsFlag

eDisplayTreeAttribute 3,2
aName,2    PhiDeltaFlag

eDisplayTreeAttribute 3,2
aName,2    SemesterRanking

eDisplayTreeAttribute 3,2
aName,2    Note

eDisplayTreeAttribute 3,2
aName,2    Year

eDisplayTreeEntity 2,2
aName,2    ..........AcademicStandingCollegeTerm (0-1)
aEntityName,2 AcademicStandingCollegeTerm
aParentEntityName,2 AcademicStanding

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Semester

eDisplayTreeAttribute 3,2
aName,2    YearSemester

eDisplayTreeEntity 2,2
aName,2    .....PrimaryCampusLocation (0-1)
aEntityName,2 PrimaryCampusLocation
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Description

eDisplayTreeEntity 2,2
aName,2    .....CampusLocation (0-m)
aEntityName,2 CampusLocation
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Name

eDisplayTreeAttribute 3,2
aName,2    Description

eDisplayTreeEntity 2,2
aName,2    .....CurrentDegreeEntryCollegeYear (0-1)
aEntityName,2 CurrentDegreeEntryCollegeYear
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    Year

eDisplayTreeAttribute 3,2
aName,2    BeginDate

eDisplayTreeAttribute 3,2
aName,2    EndDate

eDisplayTreeEntity 2,2
aName,2    .....LeaveOfAbsence (0-m)
aEntityName,2 LeaveOfAbsence
aParentEntityName,2 Student

eDisplayTreeAttribute 3,2
aName,2    ID

eDisplayTreeAttribute 3,2
aName,2    LeaveReason

eDisplayTreeAttribute 3,2
aName,2    StartDate

eDisplayTreeAttribute 3,2
aName,2    EndDate

eDisplayTreeAttribute 3,2
aName,2    Active

eDisplayTreeAttribute 3,2
aName,2    Note

eDisplayTreeAttribute 3,2
aName,2    CreatedDateTime

eGraphTypeDefinition 2,2
aType,2    Pie Chart

eGraphTypeMappingParameter 3,2
aName,2    Looping Entity
aMappingType,2 E

eGraphTypeMappingParameter 3,2
aName,2    Pie Slice Title Attribute
aMappingType,2 A

eGraphTypeMappingParameter 3,2
aName,2    Pie Slice Size Numeric Attribute
aMappingType,2 A

eGraphTypeDefinition 2,2
aType,2    Basic Bar Chart

eGraphTypeMappingParameter 3,2
aName,2    Looping Entity
aMappingType,2 E

eGraphTypeMappingParameter 3,2
aName,2    Bar Title Attribute
aMappingType,2 A

eGraphTypeMappingParameter 3,2
aName,2    Bar Size Numeric Attribute
aMappingType,2 A

eGraphTypeDefinition 2,2
aType,2    2-Level Bar Chart

eGraphTypeMappingParameter 3,2
aName,2    Looping Entity
aMappingType,2 E

eGraphTypeMappingParameter 3,2
aName,2    Level-1 Title Attribute
aMappingType,2 A

eGraphTypeMappingParameter 3,2
aName,2    Level-2 Looping Entity
aMappingType,2 E

eGraphTypeMappingParameter 3,2
aName,2    Level-2 Title Attribute
aMappingType,2 A

eGraphTypeMappingParameter 3,2
aName,2    Level-2 Size Numeric Attribute
aMappingType,2 A

i161       8
ZEND
