
// Generated from ./testdata/northwind/zeidon.xdm
import { Domain } from '@zeidon/core';

export const Northwind_DomainList = {

    "AlphaNumeric" : {
        name:  "AlphaNumeric",
        class: "com.quinsoft.zeidon.domains.RegularExpressionDomain", 
    } as Domain,

    "Blob" : {
        name:  "Blob",
        class: "com.quinsoft.zeidon.domains.Base64BlobDomain", 
    } as Domain,

    "Boolean" : {
        name:  "Boolean",
        class: "com.quinsoft.zeidon.domains.BooleanDomain", 
    } as Domain,

    "Date" : {
        name:  "Date",
        class: "com.quinsoft.zeidon.domains.DateDomain", 
    } as Domain,

    "DateTime" : {
        name:  "DateTime",
        class: "com.quinsoft.zeidon.domains.DateTimeDomain", 
    } as Domain,

    "Double" : {
        name:  "Double",
        class: "com.quinsoft.zeidon.domains.DoubleDomain", 
    } as Domain,

    "GeneratedKey" : {
        name:  "GeneratedKey",
        class: "com.quinsoft.zeidon.domains.GeneratedKeyDomain", 
    } as Domain,

    "Integer" : {
        name:  "Integer",
        class: "com.quinsoft.zeidon.domains.IntegerDomain", 
    } as Domain,

    "Text" : {
        name:  "Text",
        class: "com.quinsoft.zeidon.domains.StringDomain", 
    } as Domain,

    "Y/N" : {
        name:  "Y/N",
        class: "com.quinsoft.zeidon.domains.StaticTableDomain", 
        domainType:  "T",
        contexts: {
            "Default": {
                name: "Default",
                entries: {
                    "": "",
                    "Y": "Default",
                    "N": "",
                }
            },
            "Y/N": {
                name: "Y/N",
                entries: {
                    "": "",
                    "Y": "Y",
                    "N": "N",
                }
            },
        },
        defaultContext: "Y/N" 
    } as Domain,

    "Year" : {
        name:  "Year",
        class: "com.quinsoft.zeidon.domains.StaticTableDomain", 
        domainType:  "T",
        contexts: {
            "Year": {
                name: "Year",
                entries: {
                    "2013": "2013",
                    "2014": "2014",
                    "2015": "2015",
                    "2016": "2016",
                    "2017": "2017",
                }
            },
        },
        defaultContext: "Year" 
    } as Domain,

}
