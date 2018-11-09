z01001Zeidon    C:\Users\quinsoft\Documents\mSAProf.por mSAProf 2018/11/09 08:57:21
mOIFLAGS    0
eStudentAccountProfile 1,0
aID        363
aAccountBalance 175.0
aCleared   N
aFK_ID_ADMINISTRATIVEDIVISION 1
aFK_ID_STUDENT 11016

eStudent   2,0
aID        11016
aStatus    A
aCurrentLevel 0
aResidencyStatus T
aFK_ID_ADMINISTRATIVEDIVISION 1
aFK_ID_CAMPUSLOCATION 23
aFK_ID_PERSON 10794

ePerson    3,0
aID        10794
aLastName  Cade
aFirstName Joel
aMiddleName W
aCampusID  660010794
aGender    M
aFK_ID_ADDRESS 75009

eAddress   4,0
aID        75009
aFK_ID_PERSON 10794

eProspect  4,0
aID        14633
aType      5
aStatus    S
aFK_ID_ADMINISTRATIVEDIVISION 1
aFK_ID_ADMISSIONTRACK 1
aFK_ID_CAMPUSLOCATION 23
aFK_ID_COLLEGE 2
aFK_ID_PERSON 10794

eDemographics 4,0
aID        18879
aFK_ID_PERSON 10794

eAdministrativeDivision 3,0
aID        1
aName      Main
aDescription Main Location

eStudentAccountTransApplied 2,0
aID        3590
aDescription Payment
aDateEntered 20181109
aAmount    50.0
aTransactionDate 20181109
aFK_ID_BILLINGPERIOD 64
aDEBITID_GLCHARTENTRY 1
aCREDITID_GLCHARTENTRY 37
aFK_ID_SATRANSACTIONCODE 15
aFK_ID_STUDENTACCOUNTPROFILE 363

eSATransactionCode 3,0
aID        15
aType      R
aDescription Payment
aBillType  C
aTransactionCode PAYMENT
aActiveFlag Y
aFK_ID_ADMINISTRATIVEDIVISION 1
aFK_ID_GLCHARTENTRY 1
aCRID_GLCHARTENTRY 37

eDebitGLChartEntry 3,0
aID        1
aChartEntryCode 11015
aEntryDescription Receivable Account
aFK_ID_GLACCOUNTS 2
aFK_ID_GLCHARTOFACCOUNTS 2
aFK_ID_GLCOSTCENTER 3
aFK_ID_GLFUND 3

eCreditGLChartEntry 3,0
aID        37
aChartEntryCode 11099
aEntryDescription Payments
aFK_ID_GLACCOUNTS 5
aFK_ID_GLCHARTOFACCOUNTS 2
aFK_ID_GLCOSTCENTER 3
aFK_ID_GLFUND 3

eAppliedBillingPeriod 3,0
aID        64
aPeriodDesignator 2018-2019 Fall
aBeginDate 20180820
aEndDate   20181206
aFK_ID_STUDENTACCOUNTPROFILE 363

eAppliedBillingPeriodTerm 4,0
aID        348
aAddDropDeadlineDate 20180907
aCollegeYearSemesterOrder 1
aCourseEndDate 20181206
aCourseStartDate 20180820
aCurrentForStudentAccounts Y
aCurrentResidencyTermFlag Y
aCurrentTermFlag Y
aFullTimeMinCredits 9.0
aHalfTimeMinCredits 5.0
aLS_Term   B18Q
aOpenForClassScheduling Y
aOpenForFacultyFlag Y
aOpenForRegistrarFlag Y
aOverloadMinCredits 15.0
aPartTimeMinCredits 0.0
aSemester  01
aYearSemester 2018-2019 Fall
aFK_ID_COLLEGEYEAR 74

eStudentAccountTransApplied 2,0
aID        3587
aDescription Audit Fee
aDateEntered 20181025
aAmount    100.0
aTransactionDate 20181025
aFK_ID_BILLINGPERIOD 64
aDEBITID_GLCHARTENTRY 1
aCREDITID_GLCHARTENTRY 38
aFK_ID_SATRANSACTIONCODE 1
aFK_ID_STUDENTACCOUNTPROFILE 363

eSATransactionCode 3,0
aID        1
aType      C
aDescription Audit Fee
aDefaultAmount 100.0
aBillType  T
aTransactionCode TUIT AUD
aFK_ID_ADMINISTRATIVEDIVISION 1
aFK_ID_GLCHARTENTRY 1
aCRID_GLCHARTENTRY 38
aFK_ID_STUDENTACCOUNTRULESET02 3

eDebitGLChartEntry 3,0
aID        1
aChartEntryCode 11015
aEntryDescription Receivable Account
aSubAccountCode 000000
aActive    Y
aFK_ID_GLACCOUNTS 2
aFK_ID_GLCHARTOFACCOUNTS 2
aFK_ID_GLCOSTCENTER 3
aFK_ID_GLFUND 3

eCreditGLChartEntry 3,0
aID        38
aChartEntryCode 40505
aEntryDescription Tuition
aSubAccountCode 110000
aActive    Y
aFK_ID_GLACCOUNTS 6
aFK_ID_GLCHARTOFACCOUNTS 2
aFK_ID_GLCOSTCENTER 3
aFK_ID_GLFUND 2

eAppliedBillingPeriod 3,0

eAppliedBillingPeriodTerm 4,0
aID        348
aFK_ID_COLLEGEYEAR 74

eAppliedBillingPeriodTerm 4,0
aID        348
aFK_ID_COLLEGEYEAR 74

eStudentAccountTransApplied 2,0
aID        3586
aDescription Late Registration Fee
aDateEntered 20181025
aAmount    25.0
aTransactionDate 20181025
aFK_ID_BILLINGPERIOD 64
aDEBITID_GLCHARTENTRY 1
aCREDITID_GLCHARTENTRY 39
aFK_ID_SATRANSACTIONCODE 5
aFK_ID_STUDENTACCOUNTPROFILE 363

eSATransactionCode 3,0
aID        5
aType      C
aDescription Late Registration Fee
aDefaultAmount 25.0
aBillType  F
aTransactionCode LATE REG
aFK_ID_ADMINISTRATIVEDIVISION 1
aFK_ID_GLCHARTENTRY 1
aCRID_GLCHARTENTRY 39

eDebitGLChartEntry 3,0

eCreditGLChartEntry 3,0
aID        39
aChartEntryCode 41090
aEntryDescription Fees
aSubAccountCode 110000
aActive    Y
aFK_ID_GLACCOUNTS 7
aFK_ID_GLCHARTOFACCOUNTS 2
aFK_ID_GLCOSTCENTER 3
aFK_ID_GLFUND 2

eAppliedBillingPeriod 3,0

eAppliedBillingPeriodTerm 4,0

eBillingPeriod 2,0

ePeriodCollegeTerm 3,0

ePeriodCollegeYear 4,0
aID        74
aYear      2018-2019
aBeginDate 20180820
aEndDate   20190818

ePeriodTransApplied 3,0

ePeriodSATransactionCode 4,0

ePeriodDebitGLChartEntry 4,0

ePeriodCreditGLChartEntry 4,0

ePeriodTransApplied 3,0

ePeriodSATransactionCode 4,0

ePeriodDebitGLChartEntry 4,0

ePeriodCreditGLChartEntry 4,0

ePeriodTransApplied 3,0

ePeriodSATransactionCode 4,0

ePeriodDebitGLChartEntry 4,0

ePeriodCreditGLChartEntry 4,0

eBillingPeriod 2,0
aID        65
aPeriodDesignator 2018-2019 Spring
aBeginDate 20190114
aEndDate   20190502
aFK_ID_STUDENTACCOUNTPROFILE 363

ePeriodCollegeTerm 3,0
aID        123
aSemester  03
aYearSemester 2018-2019 Spring
aCurrentTermFlag N
aCourseStartDate 20190114
aCourseEndDate 20190502
aCollegeYearSemesterOrder 4
aOpenForClassScheduling Y
aLS_Term   B19C
aFullTimeMinCredits 9.0
aPartTimeMinCredits 0.0
aHalfTimeMinCredits 5.0
aFK_ID_COLLEGEYEAR 74

ePeriodCollegeYear 4,0
aID        74
aYear      2018-2019
aBeginDate 20180820
aEndDate   20190818

eBillingPeriod 2,0
aID        66
aPeriodDesignator 2018-2019 Summer
aBeginDate 20190506
aEndDate   20190818
aFK_ID_STUDENTACCOUNTPROFILE 363

ePeriodCollegeTerm 3,0
aID        349
aSemester  04
aYearSemester 2018-2019 Summer
aCurrentTermFlag N
aCourseStartDate 20190506
aCourseEndDate 20190818
aCollegeYearSemesterOrder 5
aLS_Term   B19L
aFK_ID_COLLEGEYEAR 74

ePeriodCollegeYear 4,0
aID        74
aYear      2018-2019
aBeginDate 20180820
aEndDate   20190818

eProfileAdministrativeDivision 2,0
aID        1
aName      Main
aDescription Main Location

i17        11
i22        15
i24        11
i25        18
i26        11
i27        12
i29        13
i30        14
i31        15
i32        16
i33        20
i34        21
i35        15
i36        23
i37        7
i38        8
i39        9
i40        10
ZEND
