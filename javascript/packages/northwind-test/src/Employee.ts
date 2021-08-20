
/*
  Generated from LOD Employee

*/

import * as zeidon from '@zeidon/core';
import { Northwind_DomainList } from './Northwind-DomainList';
import { Northwind_DomainFunctions } from './Northwind-DomainFunctions';



// Employee LOD.
export class Employee extends zeidon.ObjectInstance {

    constructor( initialize = undefined, options: zeidon.CreateOptions = undefined ) {
        super( Employee_LodDef, initialize, options );
    }

    get Employee(): zeidon.EntityArray<Employee_Employee> {
        return this.roots as zeidon.EntityArray<Employee_Employee>;
    }

    get Employee$(): Employee_Employee {
        return this.roots.selected() as Employee_Employee;
    }

    // Following allow accessing of child entity instances directly from the OI,
    // similar to Zeidon Views.


    get DirectReport(): zeidon.EntityArray<Employee_DirectReport> {
        return this.Employee$?.DirectReport;
    }

    get DirectReport$(): Employee_DirectReport {
        return this.Employee$?.DirectReport$;
    }

    get Supervisor(): zeidon.EntityArray<Employee_Supervisor> {
        return this.Employee$?.Supervisor;
    }

    get Supervisor$(): Employee_Supervisor {
        return this.Employee$?.Supervisor$;
    }

    get Territory(): zeidon.EntityArray<Employee_Territory> {
        return this.Employee$?.Territory;
    }

    get Territory$(): Employee_Territory {
        return this.Employee$?.Territory$;
    }

    get Region(): zeidon.EntityArray<Employee_Region> {
        return this.Employee$?.Territory$?.Region;
    }

    get Region$(): Employee_Region {
        return this.Employee$?.Territory$?.Region$;
    }

    public static activate( qual?: any ): Promise<Employee> {
        return zeidon.ObjectInstance.activateOi( new Employee(), qual );
    }
}


export class Employee_Employee extends zeidon.EntityInstance {
    public get entityName(): string { return "Employee" };

    get EmployeeId(): string { return this.getAttribute("EmployeeId") };
    set EmployeeId(value: string) { this.setAttribute("EmployeeId", value) };

    get LastName(): string { return this.getAttribute("LastName") };
    set LastName(value: string) { this.setAttribute("LastName", value) };

    get FirstName(): string { return this.getAttribute("FirstName") };
    set FirstName(value: string) { this.setAttribute("FirstName", value) };

    get Title(): string { return this.getAttribute("Title") };
    set Title(value: string) { this.setAttribute("Title", value) };

    get TitleOfCourtesy(): string { return this.getAttribute("TitleOfCourtesy") };
    set TitleOfCourtesy(value: string) { this.setAttribute("TitleOfCourtesy", value) };

    get BirthDate(): Date { return this.getAttribute("BirthDate") };
    set BirthDate(value: Date) { this.setAttribute("BirthDate", value) };

    get HireDate(): Date { return this.getAttribute("HireDate") };
    set HireDate(value: Date) { this.setAttribute("HireDate", value) };

    get HomePhone(): string { return this.getAttribute("HomePhone") };
    set HomePhone(value: string) { this.setAttribute("HomePhone", value) };

    get Address(): string { return this.getAttribute("Address") };
    set Address(value: string) { this.setAttribute("Address", value) };

    get City(): string { return this.getAttribute("City") };
    set City(value: string) { this.setAttribute("City", value) };

    get Region(): string { return this.getAttribute("Region") };
    set Region(value: string) { this.setAttribute("Region", value) };

    get PostalCode(): string { return this.getAttribute("PostalCode") };
    set PostalCode(value: string) { this.setAttribute("PostalCode", value) };

    get Country(): string { return this.getAttribute("Country") };
    set Country(value: string) { this.setAttribute("Country", value) };

    get Extension(): string { return this.getAttribute("Extension") };
    set Extension(value: string) { this.setAttribute("Extension", value) };

    get Notes(): string { return this.getAttribute("Notes") };
    set Notes(value: string) { this.setAttribute("Notes", value) };

    get Photo(): string { return this.getAttribute("Photo") };
    set Photo(value: string) { this.setAttribute("Photo", value) };

    get PhotoPath(): string { return this.getAttribute("PhotoPath") };
    set PhotoPath(value: string) { this.setAttribute("PhotoPath", value) };

    get Salary(): number { return this.getAttribute("Salary") };
    set Salary(value: number) { this.setAttribute("Salary", value) };

    get DirectReport(): zeidon.EntityArray<Employee_DirectReport> {
        return this.getChildEntityArray("DirectReport") as zeidon.EntityArray<Employee_DirectReport>;
    }


    get DirectReport$(): Employee_DirectReport {
        return this.getChildEntityArray("DirectReport").selected() as Employee_DirectReport;
    }

    get Supervisor(): zeidon.EntityArray<Employee_Supervisor> {
        return this.getChildEntityArray("Supervisor") as zeidon.EntityArray<Employee_Supervisor>;
    }


    get Supervisor$(): Employee_Supervisor {
        return this.getChildEntityArray("Supervisor").selected() as Employee_Supervisor;
    }

    get Territory(): zeidon.EntityArray<Employee_Territory> {
        return this.getChildEntityArray("Territory") as zeidon.EntityArray<Employee_Territory>;
    }


    get Territory$(): Employee_Territory {
        return this.getChildEntityArray("Territory").selected() as Employee_Territory;
    }
}

export class Employee_DirectReport extends zeidon.EntityInstance {
    public get entityName(): string { return "DirectReport" };

    get EmployeeId(): string { return this.getAttribute("EmployeeId") };
    set EmployeeId(value: string) { this.setAttribute("EmployeeId", value) };

    get LastName(): string { return this.getAttribute("LastName") };
    set LastName(value: string) { this.setAttribute("LastName", value) };

    get FirstName(): string { return this.getAttribute("FirstName") };
    set FirstName(value: string) { this.setAttribute("FirstName", value) };

    get Title(): string { return this.getAttribute("Title") };
    set Title(value: string) { this.setAttribute("Title", value) };

    get TitleOfCourtesy(): string { return this.getAttribute("TitleOfCourtesy") };
    set TitleOfCourtesy(value: string) { this.setAttribute("TitleOfCourtesy", value) };

    get BirthDate(): Date { return this.getAttribute("BirthDate") };
    set BirthDate(value: Date) { this.setAttribute("BirthDate", value) };

    get HireDate(): Date { return this.getAttribute("HireDate") };
    set HireDate(value: Date) { this.setAttribute("HireDate", value) };

    get HomePhone(): string { return this.getAttribute("HomePhone") };
    set HomePhone(value: string) { this.setAttribute("HomePhone", value) };

    get Address(): string { return this.getAttribute("Address") };
    set Address(value: string) { this.setAttribute("Address", value) };

    get City(): string { return this.getAttribute("City") };
    set City(value: string) { this.setAttribute("City", value) };

    get Region(): string { return this.getAttribute("Region") };
    set Region(value: string) { this.setAttribute("Region", value) };

    get PostalCode(): string { return this.getAttribute("PostalCode") };
    set PostalCode(value: string) { this.setAttribute("PostalCode", value) };

    get Country(): string { return this.getAttribute("Country") };
    set Country(value: string) { this.setAttribute("Country", value) };

    get Extension(): string { return this.getAttribute("Extension") };
    set Extension(value: string) { this.setAttribute("Extension", value) };

    get Notes(): string { return this.getAttribute("Notes") };
    set Notes(value: string) { this.setAttribute("Notes", value) };

    get Photo(): string { return this.getAttribute("Photo") };
    set Photo(value: string) { this.setAttribute("Photo", value) };

    get PhotoPath(): string { return this.getAttribute("PhotoPath") };
    set PhotoPath(value: string) { this.setAttribute("PhotoPath", value) };

    get Salary(): number { return this.getAttribute("Salary") };
    set Salary(value: number) { this.setAttribute("Salary", value) };
}

export class Employee_Supervisor extends zeidon.EntityInstance {
    public get entityName(): string { return "Supervisor" };

    get EmployeeId(): string { return this.getAttribute("EmployeeId") };
    set EmployeeId(value: string) { this.setAttribute("EmployeeId", value) };

    get LastName(): string { return this.getAttribute("LastName") };
    set LastName(value: string) { this.setAttribute("LastName", value) };

    get FirstName(): string { return this.getAttribute("FirstName") };
    set FirstName(value: string) { this.setAttribute("FirstName", value) };

    get Title(): string { return this.getAttribute("Title") };
    set Title(value: string) { this.setAttribute("Title", value) };

    get TitleOfCourtesy(): string { return this.getAttribute("TitleOfCourtesy") };
    set TitleOfCourtesy(value: string) { this.setAttribute("TitleOfCourtesy", value) };

    get BirthDate(): Date { return this.getAttribute("BirthDate") };
    set BirthDate(value: Date) { this.setAttribute("BirthDate", value) };

    get HireDate(): Date { return this.getAttribute("HireDate") };
    set HireDate(value: Date) { this.setAttribute("HireDate", value) };

    get HomePhone(): string { return this.getAttribute("HomePhone") };
    set HomePhone(value: string) { this.setAttribute("HomePhone", value) };

    get Address(): string { return this.getAttribute("Address") };
    set Address(value: string) { this.setAttribute("Address", value) };

    get City(): string { return this.getAttribute("City") };
    set City(value: string) { this.setAttribute("City", value) };

    get Region(): string { return this.getAttribute("Region") };
    set Region(value: string) { this.setAttribute("Region", value) };

    get PostalCode(): string { return this.getAttribute("PostalCode") };
    set PostalCode(value: string) { this.setAttribute("PostalCode", value) };

    get Country(): string { return this.getAttribute("Country") };
    set Country(value: string) { this.setAttribute("Country", value) };

    get Extension(): string { return this.getAttribute("Extension") };
    set Extension(value: string) { this.setAttribute("Extension", value) };

    get Notes(): string { return this.getAttribute("Notes") };
    set Notes(value: string) { this.setAttribute("Notes", value) };

    get Photo(): string { return this.getAttribute("Photo") };
    set Photo(value: string) { this.setAttribute("Photo", value) };

    get PhotoPath(): string { return this.getAttribute("PhotoPath") };
    set PhotoPath(value: string) { this.setAttribute("PhotoPath", value) };

    get Salary(): number { return this.getAttribute("Salary") };
    set Salary(value: number) { this.setAttribute("Salary", value) };
}

export class Employee_Territory extends zeidon.EntityInstance {
    public get entityName(): string { return "Territory" };

    get TerritoryId(): string { return this.getAttribute("TerritoryId") };
    set TerritoryId(value: string) { this.setAttribute("TerritoryId", value) };

    get TerritoryDescription(): string { return this.getAttribute("TerritoryDescription") };
    set TerritoryDescription(value: string) { this.setAttribute("TerritoryDescription", value) };

    get Region(): zeidon.EntityArray<Employee_Region> {
        return this.getChildEntityArray("Region") as zeidon.EntityArray<Employee_Region>;
    }


    get Region$(): Employee_Region {
        return this.getChildEntityArray("Region").selected() as Employee_Region;
    }
}

export class Employee_Region extends zeidon.EntityInstance {
    public get entityName(): string { return "Region" };

    get RegionId(): string { return this.getAttribute("RegionId") };
    set RegionId(value: string) { this.setAttribute("RegionId", value) };

    get RegionDescription(): string { return this.getAttribute("RegionDescription") };
    set RegionDescription(value: string) { this.setAttribute("RegionDescription", value) };
}

const EmployeeEntityPrototypes = {
    Employee: Employee_Employee.prototype, 
    DirectReport: Employee_DirectReport.prototype, 
    Supervisor: Employee_Supervisor.prototype, 
    Territory: Employee_Territory.prototype, 
    Region: Employee_Region.prototype, 
}

export const Employee_LodDefStructure = {
    name: "Employee",
    root: "Employee",
    applicationName: "Northwind",
    entities: {
        Employee: {
            name:        "Employee",
            erToken:     "110000216",
            create:      true,
            cardMax:     0,
            hasInit:     false,
            creatable:   true,
            includable:  false,
            deletable:   true,
            excludable:  false,
            updatable:   true,
            derived:     false,
            parentDelete: true,
            childEntities: {
                DirectReport: {},
                Supervisor: {},
                Territory: {},
            },
            keys: [ "EmployeeId",  ],
            attributes: {
                EmployeeId: {
                    name:         "EmployeeId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                LastName: {
                    name:         "LastName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                FirstName: {
                    name:         "FirstName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Title: {
                    name:         "Title",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                TitleOfCourtesy: {
                    name:         "TitleOfCourtesy",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                BirthDate: {
                    name:         "BirthDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "Date",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                HireDate: {
                    name:         "HireDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "Date",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                HomePhone: {
                    name:         "HomePhone",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Address: {
                    name:         "Address",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                City: {
                    name:         "City",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Region: {
                    name:         "Region",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PostalCode: {
                    name:         "PostalCode",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Country: {
                    name:         "Country",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Extension: {
                    name:         "Extension",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Notes: {
                    name:         "Notes",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Photo: {
                    name:         "Photo",
                    hidden:       false,
                    required:     false,
                    domainName:   "Blob",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PhotoPath: {
                    name:         "PhotoPath",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Salary: {
                    name:         "Salary",
                    hidden:       false,
                    required:     false,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ReportsTo: {
                    name:         "ReportsTo",
                    hidden:       true,
                    required:     false,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
            }
        },

        DirectReport: {
            name:        "DirectReport",
            erToken:     "110000216",
            isErRelLink: true,
            relToken:    "110000442",
            create:      false,
            cardMax:     999999,
            hasInit:     false,
            creatable:   false,
            includable:  true,
            deletable:   false,
            excludable:  true,
            updatable:   false,
            derived:     false,
            parentDelete: false,
            childEntities: {
            },
            keys: [ "EmployeeId",  ],
            attributes: {
                EmployeeId: {
                    name:         "EmployeeId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                LastName: {
                    name:         "LastName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                FirstName: {
                    name:         "FirstName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Title: {
                    name:         "Title",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                TitleOfCourtesy: {
                    name:         "TitleOfCourtesy",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                BirthDate: {
                    name:         "BirthDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "Date",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                HireDate: {
                    name:         "HireDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "Date",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                HomePhone: {
                    name:         "HomePhone",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Address: {
                    name:         "Address",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                City: {
                    name:         "City",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Region: {
                    name:         "Region",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PostalCode: {
                    name:         "PostalCode",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Country: {
                    name:         "Country",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Extension: {
                    name:         "Extension",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Notes: {
                    name:         "Notes",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Photo: {
                    name:         "Photo",
                    hidden:       false,
                    required:     false,
                    domainName:   "Blob",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PhotoPath: {
                    name:         "PhotoPath",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Salary: {
                    name:         "Salary",
                    hidden:       false,
                    required:     false,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ReportsTo: {
                    name:         "ReportsTo",
                    hidden:       true,
                    required:     false,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
            }
        },

        Supervisor: {
            name:        "Supervisor",
            erToken:     "110000216",
            isErRelLink: false,
            relToken:    "110000442",
            create:      false,
            cardMax:     1,
            hasInit:     false,
            creatable:   false,
            includable:  true,
            deletable:   false,
            excludable:  true,
            updatable:   false,
            derived:     false,
            parentDelete: false,
            childEntities: {
            },
            keys: [ "EmployeeId",  ],
            attributes: {
                EmployeeId: {
                    name:         "EmployeeId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                LastName: {
                    name:         "LastName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                FirstName: {
                    name:         "FirstName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Title: {
                    name:         "Title",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                TitleOfCourtesy: {
                    name:         "TitleOfCourtesy",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                BirthDate: {
                    name:         "BirthDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "Date",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                HireDate: {
                    name:         "HireDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "Date",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                HomePhone: {
                    name:         "HomePhone",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Address: {
                    name:         "Address",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                City: {
                    name:         "City",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Region: {
                    name:         "Region",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PostalCode: {
                    name:         "PostalCode",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Country: {
                    name:         "Country",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Extension: {
                    name:         "Extension",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Notes: {
                    name:         "Notes",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Photo: {
                    name:         "Photo",
                    hidden:       false,
                    required:     false,
                    domainName:   "Blob",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PhotoPath: {
                    name:         "PhotoPath",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Salary: {
                    name:         "Salary",
                    hidden:       false,
                    required:     false,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ReportsTo: {
                    name:         "ReportsTo",
                    hidden:       true,
                    required:     false,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
            }
        },

        Territory: {
            name:        "Territory",
            erToken:     "110000394",
            isErRelLink: false,
            relToken:    "110000424",
            create:      false,
            cardMax:     999999,
            hasInit:     false,
            creatable:   false,
            includable:  true,
            deletable:   false,
            excludable:  true,
            updatable:   false,
            derived:     false,
            parentDelete: false,
            childEntities: {
                Region: {},
            },
            keys: [ "TerritoryId",  ],
            attributes: {
                TerritoryId: {
                    name:         "TerritoryId",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                TerritoryDescription: {
                    name:         "TerritoryDescription",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                REGIONID: {
                    name:         "REGIONID",
                    hidden:       true,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
            }
        },

        Region: {
            name:        "Region",
            erToken:     "110000389",
            isErRelLink: false,
            relToken:    "110000430",
            create:      false,
            cardMax:     1,
            hasInit:     false,
            creatable:   false,
            includable:  false,
            deletable:   false,
            excludable:  false,
            updatable:   false,
            derived:     false,
            parentDelete: false,
            childEntities: {
            },
            keys: [ "RegionId",  ],
            attributes: {
                RegionId: {
                    name:         "RegionId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                RegionDescription: {
                    name:         "RegionDescription",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
            }
        },

    }
}

export const Employee_LodDef = new zeidon.LodDef( Employee_LodDefStructure, EmployeeEntityPrototypes, Northwind_DomainList, Northwind_DomainFunctions );
        
