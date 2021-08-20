
/*
  Generated from LOD Product

*/

import * as zeidon from '@zeidon/core';
import { Northwind_DomainList } from './Northwind-DomainList';
import { Northwind_DomainFunctions } from './Northwind-DomainFunctions';



// Product LOD.
export class Product extends zeidon.ObjectInstance {

    constructor( initialize = undefined, options: zeidon.CreateOptions = undefined ) {
        super( Product_LodDef, initialize, options );
    }

    get Product(): zeidon.EntityArray<Product_Product> {
        return this.roots as zeidon.EntityArray<Product_Product>;
    }

    get Product$(): Product_Product {
        return this.roots.selected() as Product_Product;
    }

    // Following allow accessing of child entity instances directly from the OI,
    // similar to Zeidon Views.


    get Supplier(): zeidon.EntityArray<Product_Supplier> {
        return this.Product$?.Supplier;
    }

    get Supplier$(): Product_Supplier {
        return this.Product$?.Supplier$;
    }

    get Category(): zeidon.EntityArray<Product_Category> {
        return this.Product$?.Category;
    }

    get Category$(): Product_Category {
        return this.Product$?.Category$;
    }

    public static activate( qual?: any ): Promise<Product> {
        return zeidon.ObjectInstance.activateOi( new Product(), qual );
    }
}


export class Product_Product extends zeidon.EntityInstance {
    public get entityName(): string { return "Product" };

    get ProductId(): string { return this.getAttribute("ProductId") };
    set ProductId(value: string) { this.setAttribute("ProductId", value) };

    get ProductName(): string { return this.getAttribute("ProductName") };
    set ProductName(value: string) { this.setAttribute("ProductName", value) };

    get ReorderLevel(): number { return this.getAttribute("ReorderLevel") };
    set ReorderLevel(value: number) { this.setAttribute("ReorderLevel", value) };

    get QuantityPerUnit(): string { return this.getAttribute("QuantityPerUnit") };
    set QuantityPerUnit(value: string) { this.setAttribute("QuantityPerUnit", value) };

    get Discontinued(): boolean { return this.getAttribute("Discontinued") };
    set Discontinued(value: boolean) { this.setAttribute("Discontinued", value) };

    get UnitPrice(): number { return this.getAttribute("UnitPrice") };
    set UnitPrice(value: number) { this.setAttribute("UnitPrice", value) };

    get UnitsInStock(): number { return this.getAttribute("UnitsInStock") };
    set UnitsInStock(value: number) { this.setAttribute("UnitsInStock", value) };

    get UnitsOnOrder(): number { return this.getAttribute("UnitsOnOrder") };
    set UnitsOnOrder(value: number) { this.setAttribute("UnitsOnOrder", value) };

    get Supplier(): zeidon.EntityArray<Product_Supplier> {
        return this.getChildEntityArray("Supplier") as zeidon.EntityArray<Product_Supplier>;
    }


    get Supplier$(): Product_Supplier {
        return this.getChildEntityArray("Supplier").selected() as Product_Supplier;
    }

    get Category(): zeidon.EntityArray<Product_Category> {
        return this.getChildEntityArray("Category") as zeidon.EntityArray<Product_Category>;
    }


    get Category$(): Product_Category {
        return this.getChildEntityArray("Category").selected() as Product_Category;
    }
}

export class Product_Supplier extends zeidon.EntityInstance {
    public get entityName(): string { return "Supplier" };

    get SupplierId(): string { return this.getAttribute("SupplierId") };
    set SupplierId(value: string) { this.setAttribute("SupplierId", value) };

    get CompanyName(): string { return this.getAttribute("CompanyName") };
    set CompanyName(value: string) { this.setAttribute("CompanyName", value) };

    get ContactName(): string { return this.getAttribute("ContactName") };
    set ContactName(value: string) { this.setAttribute("ContactName", value) };

    get ContactTitle(): string { return this.getAttribute("ContactTitle") };
    set ContactTitle(value: string) { this.setAttribute("ContactTitle", value) };

    get Phone(): string { return this.getAttribute("Phone") };
    set Phone(value: string) { this.setAttribute("Phone", value) };

    get Fax(): string { return this.getAttribute("Fax") };
    set Fax(value: string) { this.setAttribute("Fax", value) };

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

    get HomePage(): string { return this.getAttribute("HomePage") };
    set HomePage(value: string) { this.setAttribute("HomePage", value) };
}

export class Product_Category extends zeidon.EntityInstance {
    public get entityName(): string { return "Category" };

    get CategoryId(): string { return this.getAttribute("CategoryId") };
    set CategoryId(value: string) { this.setAttribute("CategoryId", value) };

    get CategoryName(): string { return this.getAttribute("CategoryName") };
    set CategoryName(value: string) { this.setAttribute("CategoryName", value) };

    get Description(): string { return this.getAttribute("Description") };
    set Description(value: string) { this.setAttribute("Description", value) };

    get Picture(): string { return this.getAttribute("Picture") };
    set Picture(value: string) { this.setAttribute("Picture", value) };
}

const ProductEntityPrototypes = {
    Product: Product_Product.prototype, 
    Supplier: Product_Supplier.prototype, 
    Category: Product_Category.prototype, 
}

export const Product_LodDefStructure = {
    name: "Product",
    root: "Product",
    applicationName: "Northwind",
    entities: {
        Product: {
            name:        "Product",
            erToken:     "110000054",
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
                Supplier: {},
                Category: {},
            },
            keys: [ "ProductId",  ],
            attributes: {
                ProductId: {
                    name:         "ProductId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                ProductName: {
                    name:         "ProductName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                ReorderLevel: {
                    name:         "ReorderLevel",
                    hidden:       false,
                    required:     false,
                    domainName:   "Integer",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                QuantityPerUnit: {
                    name:         "QuantityPerUnit",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Discontinued: {
                    name:         "Discontinued",
                    hidden:       false,
                    required:     true,
                    domainName:   "Boolean",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                UnitPrice: {
                    name:         "UnitPrice",
                    hidden:       false,
                    required:     false,
                    domainName:   "Double",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                UnitsInStock: {
                    name:         "UnitsInStock",
                    hidden:       false,
                    required:     false,
                    domainName:   "Integer",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                UnitsOnOrder: {
                    name:         "UnitsOnOrder",
                    hidden:       false,
                    required:     false,
                    domainName:   "Integer",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                SUPPLIERID: {
                    name:         "SUPPLIERID",
                    hidden:       true,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   true,
                },
                CATEGORYID: {
                    name:         "CATEGORYID",
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

        Supplier: {
            name:        "Supplier",
            erToken:     "110000077",
            isErRelLink: false,
            relToken:    "110000406",
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
            keys: [ "SupplierId",  ],
            attributes: {
                SupplierId: {
                    name:         "SupplierId",
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
                HomePage: {
                    name:         "HomePage",
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

        Category: {
            name:        "Category",
            erToken:     "110000364",
            isErRelLink: false,
            relToken:    "110000409",
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
            keys: [ "CategoryId",  ],
            attributes: {
                CategoryId: {
                    name:         "CategoryId",
                    hidden:       false,
                    required:     true,
                    domainName:   "GeneratedKey",
                    persistent:   true,
                    key:          true,
                    update:       true,
                    foreignKey:   false,
                },
                CategoryName: {
                    name:         "CategoryName",
                    hidden:       false,
                    required:     true,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Description: {
                    name:         "Description",
                    hidden:       false,
                    required:     false,
                    domainName:   "Text",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
                Picture: {
                    name:         "Picture",
                    hidden:       false,
                    required:     false,
                    domainName:   "Blob",
                    persistent:   true,
                    key:          false,
                    update:       true,
                    foreignKey:   false,
                },
            }
        },

    }
}

export const Product_LodDef = new zeidon.LodDef( Product_LodDefStructure, ProductEntityPrototypes, Northwind_DomainList, Northwind_DomainFunctions );
        
