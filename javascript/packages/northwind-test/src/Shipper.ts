
/*
  Generated from LOD Shipper

*/

import * as zeidon from '@zeidon/core';
import { Northwind_DomainList } from './Northwind-DomainList';
import { Northwind_DomainFunctions } from './Northwind-DomainFunctions';



// Shipper LOD.
export class Shipper extends zeidon.ObjectInstance {

    constructor( initialize = undefined, options: zeidon.CreateOptions = undefined ) {
        super( Shipper_LodDef, initialize, options );
    }

    get Shipper(): zeidon.EntityArray<Shipper_Shipper> {
        return this.roots as zeidon.EntityArray<Shipper_Shipper>;
    }

    get Shipper$(): Shipper_Shipper {
        return this.roots.selected() as Shipper_Shipper;
    }

    // Following allow accessing of child entity instances directly from the OI,
    // similar to Zeidon Views.


    public static activate( qual?: any ): Promise<Shipper> {
        return zeidon.ObjectInstance.activateOi( new Shipper(), qual );
    }
}


export class Shipper_Shipper extends zeidon.EntityInstance {
    public get entityName(): string { return "Shipper" };

    get ShipperId(): string { return this.getAttribute("ShipperId") };
    set ShipperId(value: string) { this.setAttribute("ShipperId", value) };

    get CompanyName(): string { return this.getAttribute("CompanyName") };
    set CompanyName(value: string) { this.setAttribute("CompanyName", value) };

    get Phone(): string { return this.getAttribute("Phone") };
    set Phone(value: string) { this.setAttribute("Phone", value) };
}

const ShipperEntityPrototypes = {
    Shipper: Shipper_Shipper.prototype, 
}

export const Shipper_LodDefStructure = {
    name: "Shipper",
    root: "Shipper",
    applicationName: "Northwind",
    entities: {
        Shipper: {
            name:        "Shipper",
            erToken:     "110000337",
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
            },
            keys: [ "ShipperId",  ],
            attributes: {
                ShipperId: {
                    name:         "ShipperId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                CompanyName: {
                    name:         "CompanyName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Phone: {
                    name:         "Phone",
                    hidden:       false,
                    required:     false,
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

export const Shipper_LodDef = new zeidon.LodDef( Shipper_LodDefStructure, ShipperEntityPrototypes, Northwind_DomainList, Northwind_DomainFunctions );
        
