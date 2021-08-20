
/*
  Generated from LOD Customer

*/

import * as zeidon from '@zeidon/core';
import { Northwind_DomainList } from './Northwind-DomainList';
import { Northwind_DomainFunctions } from './Northwind-DomainFunctions';



// Customer LOD.
export class Customer extends zeidon.ObjectInstance {

    constructor( initialize = undefined, options: zeidon.CreateOptions = undefined ) {
        super( Customer_LodDef, initialize, options );
    }

    get Customer(): zeidon.EntityArray<Customer_Customer> {
        return this.roots as zeidon.EntityArray<Customer_Customer>;
    }

    get Customer$(): Customer_Customer {
        return this.roots.selected() as Customer_Customer;
    }

    // Following allow accessing of child entity instances directly from the OI,
    // similar to Zeidon Views.


    get Order(): zeidon.EntityArray<Customer_Order> {
        return this.Customer$?.Order;
    }

    get Order$(): Customer_Order {
        return this.Customer$?.Order$;
    }

    get OrderDetail(): zeidon.EntityArray<Customer_OrderDetail> {
        return this.Customer$?.Order$?.OrderDetail;
    }

    get OrderDetail$(): Customer_OrderDetail {
        return this.Customer$?.Order$?.OrderDetail$;
    }

    public static activate( qual?: any ): Promise<Customer> {
        return zeidon.ObjectInstance.activateOi( new Customer(), qual );
    }
}


export class Customer_Customer extends zeidon.EntityInstance {
    public get entityName(): string { return "Customer" };

    get CustomerId(): string { return this.getAttribute("CustomerId") };
    set CustomerId(value: string) { this.setAttribute("CustomerId", value) };

    get CompanyName(): string { return this.getAttribute("CompanyName") };
    set CompanyName(value: string) { this.setAttribute("CompanyName", value) };

    get ContactName(): string { return this.getAttribute("ContactName") };
    set ContactName(value: string) { this.setAttribute("ContactName", value) };

    get ContactTitle(): string { return this.getAttribute("ContactTitle") };
    set ContactTitle(value: string) { this.setAttribute("ContactTitle", value) };

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

    get Phone(): string { return this.getAttribute("Phone") };
    set Phone(value: string) { this.setAttribute("Phone", value) };

    get Fax(): string { return this.getAttribute("Fax") };
    set Fax(value: string) { this.setAttribute("Fax", value) };

    get Order(): zeidon.EntityArray<Customer_Order> {
        return this.getChildEntityArray("Order") as zeidon.EntityArray<Customer_Order>;
    }


    get Order$(): Customer_Order {
        return this.getChildEntityArray("Order").selected() as Customer_Order;
    }
}

export class Customer_Order extends zeidon.EntityInstance {
    public get entityName(): string { return "Order" };

    get OrderId(): string { return this.getAttribute("OrderId") };
    set OrderId(value: string) { this.setAttribute("OrderId", value) };

    get OrderDate(): Date { return this.getAttribute("OrderDate") };
    set OrderDate(value: Date) { this.setAttribute("OrderDate", value) };

    get ShippedDate(): Date { return this.getAttribute("ShippedDate") };
    set ShippedDate(value: Date) { this.setAttribute("ShippedDate", value) };

    get RequiredDate(): Date { return this.getAttribute("RequiredDate") };
    set RequiredDate(value: Date) { this.setAttribute("RequiredDate", value) };

    get Freight(): number { return this.getAttribute("Freight") };
    set Freight(value: number) { this.setAttribute("Freight", value) };

    get ShipName(): string { return this.getAttribute("ShipName") };
    set ShipName(value: string) { this.setAttribute("ShipName", value) };

    get ShipAddress(): string { return this.getAttribute("ShipAddress") };
    set ShipAddress(value: string) { this.setAttribute("ShipAddress", value) };

    get ShipCity(): string { return this.getAttribute("ShipCity") };
    set ShipCity(value: string) { this.setAttribute("ShipCity", value) };

    get ShipRegion(): string { return this.getAttribute("ShipRegion") };
    set ShipRegion(value: string) { this.setAttribute("ShipRegion", value) };

    get ShipPostalCode(): string { return this.getAttribute("ShipPostalCode") };
    set ShipPostalCode(value: string) { this.setAttribute("ShipPostalCode", value) };

    get ShipCountry(): string { return this.getAttribute("ShipCountry") };
    set ShipCountry(value: string) { this.setAttribute("ShipCountry", value) };

    get OrderDetail(): zeidon.EntityArray<Customer_OrderDetail> {
        return this.getChildEntityArray("OrderDetail") as zeidon.EntityArray<Customer_OrderDetail>;
    }


    get OrderDetail$(): Customer_OrderDetail {
        return this.getChildEntityArray("OrderDetail").selected() as Customer_OrderDetail;
    }
}

export class Customer_OrderDetail extends zeidon.EntityInstance {
    public get entityName(): string { return "OrderDetail" };

    get UnitPrice(): number { return this.getAttribute("UnitPrice") };
    set UnitPrice(value: number) { this.setAttribute("UnitPrice", value) };

    get Quantity(): number { return this.getAttribute("Quantity") };
    set Quantity(value: number) { this.setAttribute("Quantity", value) };

    get Discount(): number { return this.getAttribute("Discount") };
    set Discount(value: number) { this.setAttribute("Discount", value) };
}

const CustomerEntityPrototypes = {
    Customer: Customer_Customer.prototype, 
    Order: Customer_Order.prototype, 
    OrderDetail: Customer_OrderDetail.prototype, 
}

export const Customer_LodDefStructure = {
    name: "Customer",
    root: "Customer",
    applicationName: "Northwind",
    entities: {
        Customer: {
            name:        "Customer",
            erToken:     "110000238",
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
                Order: {},
            },
            keys: [ "CustomerId",  ],
            attributes: {
                CustomerId: {
                    name:         "CustomerId",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                CompanyName: {
                    name:         "CompanyName",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ContactName: {
                    name:         "ContactName",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ContactTitle: {
                    name:         "ContactTitle",
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
                Fax: {
                    name:         "Fax",
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

        Order: {
            name:        "Order",
            erToken:     "110000286",
            isErRelLink: true,
            relToken:    "110000325",
            create:      false,
            cardMax:     999999,
            hasInit:     false,
            creatable:   false,
            includable:  false,
            deletable:   false,
            excludable:  false,
            updatable:   false,
            derived:     false,
            parentDelete: false,
            childEntities: {
                OrderDetail: {},
            },
            keys: [ "OrderId",  ],
            attributes: {
                OrderId: {
                    name:         "OrderId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                OrderDate: {
                    name:         "OrderDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "DateTime",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShippedDate: {
                    name:         "ShippedDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "DateTime",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                RequiredDate: {
                    name:         "RequiredDate",
                    hidden:       false,
                    required:     false,
                    domainName:   "DateTime",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Freight: {
                    name:         "Freight",
                    hidden:       false,
                    required:     false,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShipName: {
                    name:         "ShipName",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShipAddress: {
                    name:         "ShipAddress",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShipCity: {
                    name:         "ShipCity",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShipRegion: {
                    name:         "ShipRegion",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShipPostalCode: {
                    name:         "ShipPostalCode",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ShipCountry: {
                    name:         "ShipCountry",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                EMPLOYEEID: {
                    name:         "EMPLOYEEID",
                    hidden:       true,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
                CUSTOMERID: {
                    name:         "CUSTOMERID",
                    hidden:       true,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
                ShipVia: {
                    name:         "ShipVia",
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

        OrderDetail: {
            name:        "OrderDetail",
            erToken:     "110000399",
            isErRelLink: true,
            relToken:    "110000418",
            create:      false,
            cardMax:     999999,
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
            keys: [ "PRODUCTID",  "ORDERID",  ],
            attributes: {
                UnitPrice: {
                    name:         "UnitPrice",
                    hidden:       false,
                    required:     true,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Quantity: {
                    name:         "Quantity",
                    hidden:       false,
                    required:     true,
                    domainName:   "Integer",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Discount: {
                    name:         "Discount",
                    hidden:       false,
                    required:     true,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                PRODUCTID: {
                    name:         "PRODUCTID",
                    hidden:       true,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   true,
                },
                ORDERID: {
                    name:         "ORDERID",
                    hidden:       true,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   true,
                },
            }
        },

    }
}

export const Customer_LodDef = new zeidon.LodDef( Customer_LodDefStructure, CustomerEntityPrototypes, Northwind_DomainList, Northwind_DomainFunctions );
        
