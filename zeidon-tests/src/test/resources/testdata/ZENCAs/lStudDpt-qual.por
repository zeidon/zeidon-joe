z1000-Zeidon    KZDBHQUA KZDBHQUA 06/10/10   11:53:36 1.0a2    
eEntitySpec 1
aEntityName Student

eQualAttrib 2
aEntityName Student
aAttributeName Status
aValue     A
aOper      =

eQualAttrib 2
aOper      AND

eQualAttrib 2
aEntityName MajorDepartment
aAttributeName ID
aValue     3
aOper      =

eQualAttrib 2
aOper      AND

eQualAttrib 2
aEntityName AdministrativeDivision
aAttributeName ID
aValue     1
aOper      =

eQualAttrib 2
aOper      AND

eQualAttrib 2
aEntityName StudentMajorDegreeTrack
aAttributeName GraduationDate
aOper      IS NULL

eEntitySpec 1
aEntityName StudentMajorDegreeTrack

eQualAttrib 2
aEntityName MajorDepartment
aAttributeName ID
aValue     3
aOper      =

