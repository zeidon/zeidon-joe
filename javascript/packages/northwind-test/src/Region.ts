
/*
  Generated from LOD Region

*/

import * as zeidon from '@zeidon/core';
import { Northwind_DomainList } from './Northwind-DomainList';
import { Northwind_DomainFunctions } from './Northwind-DomainFunctions';



// Region LOD.
export class Region extends zeidon.ObjectInstance {

    constructor( initialize = undefined, options: zeidon.CreateOptions = undefined ) {
        super( Region_LodDef, initialize, options );
    }

    get Region(): zeidon.EntityArray<Region_Region> {
        return this.roots as zeidon.EntityArray<Region_Region>;
    }

    get Region$(): Region_Region {
        return this.roots.selected() as Region_Region;
    }

    // Following allow accessing of child entity instances directly from the OI,
    // similar to Zeidon Views.


    get Territory(): zeidon.EntityArray<Region_Territory> {
        return this.Region$?.Territory;
    }

    get Territory$(): Region_Territory {
        return this.Region$?.Territory$;
    }

    public static activate( qual?: any ): Promise<Region> {
        return zeidon.ObjectInstance.activateOi( new Region(), qual );
    }
}


export class Region_Region extends zeidon.EntityInstance {
    public get entityName(): string { return "Region" };

    get RegionId(): string { return this.getAttribute("RegionId") };
    set RegionId(value: string) { this.setAttribute("RegionId", value) };

    get RegionDescription(): string { return this.getAttribute("RegionDescription") };
    set RegionDescription(value: string) { this.setAttribute("RegionDescription", value) };

    get Territory(): zeidon.EntityArray<Region_Territory> {
        return this.getChildEntityArray("Territory") as zeidon.EntityArray<Region_Territory>;
    }


    get Territory$(): Region_Territory {
        return this.getChildEntityArray("Territory").selected() as Region_Territory;
    }
}

export class Region_Territory extends zeidon.EntityInstance {
    public get entityName(): string { return "Territory" };

    get TerritoryId(): string { return this.getAttribute("TerritoryId") };
    set TerritoryId(value: string) { this.setAttribute("TerritoryId", value) };

    get TerritoryDescription(): string { return this.getAttribute("TerritoryDescription") };
    set TerritoryDescription(value: string) { this.setAttribute("TerritoryDescription", value) };
}

const RegionEntityPrototypes = {
    Region: Region_Region.prototype, 
    Territory: Region_Territory.prototype, 
}

export const Region_LodDefStructure = {
    name: "Region",
    root: "Region",
    applicationName: "Northwind",
    entities: {
        Region: {
            name:        "Region",
            erToken:     "110000389",
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
                Territory: {},
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

        Territory: {
            name:        "Territory",
            erToken:     "110000394",
            isErRelLink: true,
            relToken:    "110000430",
            create:      true,
            cardMax:     999999,
            hasInit:     false,
            creatable:   true,
            includable:  false,
            deletable:   true,
            excludable:  false,
            updatable:   true,
            derived:     false,
            parentDelete: true,
            childEntities: {
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

    }
}

export const Region_LodDef = new zeidon.LodDef( Region_LodDefStructure, RegionEntityPrototypes, Northwind_DomainList, Northwind_DomainFunctions );
        
