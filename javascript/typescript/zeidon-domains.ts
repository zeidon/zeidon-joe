import { Domain, DomainFunctions, TableDomainEntry } from "./zeidon"
import { ZeidonError, AttributeValueError } from "./zeidon"

export class BaseDomainFunctions implements DomainFunctions {
    domain: Domain;

    constructor( domain: Domain ) { this.domain = domain }

    checkForRequiredValue( value: any, attributeDef: any ) {
        if ( attributeDef.required && ( value === undefined || value === null || value === "" ) )
            throw new AttributeValueError( `Value is required.`, attributeDef );
    }

    convertExternalValue( value: any, attributeDef: any, context?: any ): any {
        this.checkForRequiredValue( value, attributeDef );
        return value;
    }

    convertToJsType( value: any, attributeDef: any, context = undefined ): any {
        return value;
    }
}

/**
 * User-written code to process domains.
 */
export class StringDomainFunctions extends BaseDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context? : any ): any {
        this.checkForRequiredValue( value, attributeDef );
        if ( value == undefined )
            return undefined;

        let str = value.toString();

        if ( attributeDef.maxLength ) {
            if ( str.length > attributeDef.maxLength )
                throw new AttributeValueError(`Length is longer than max string length: ${value}`, attributeDef );
            else
            if ( str.length > attributeDef.domain.maxLength )
                throw new AttributeValueError(`Length is longer than max string length: ${value}`, attributeDef );
        }

        return str;
    }
}

export class IntegerDomainFunctions extends BaseDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context? : any ): any {
        this.checkForRequiredValue( value, attributeDef );
        if ( value == undefined )
            return undefined;

        if ( typeof value === 'number' ) {
            // Do nothing atm.
        } else
        if ( typeof value === 'string' ) {
            let v = Number(value);
            if ( isNaN( v ) ) {
                throw new AttributeValueError(`Invalid integer value: ${value}`, attributeDef );
            }

            value = v;
        } else {
            throw new AttributeValueError(`Invalid integer value: ${value}`, attributeDef );
        }

        if ( ! Number.isInteger( value ) )
            throw new AttributeValueError(`Invalid integer value: ${value}`, attributeDef );

        return value;
    }
}

export class BooleanDomainFunctions extends BaseDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context? : any ): any {
        this.checkForRequiredValue( value, attributeDef );

        switch ( value ) {
            case true:
            case false:
                return value
            case "true":
            case "TRUE":
                return true;
            case "false":
            case "FALSE":
                return false;
            case null:
            case "":
            case undefined:
                return undefined;
        }

        throw new AttributeValueError(`Invalid boolean value: ${value}`, attributeDef );
    }
}

export class DoubleDomainFunctions extends BaseDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context? : any ): any {
        this.checkForRequiredValue( value, attributeDef );
        if ( value == undefined )
            return undefined;

        if ( typeof value === 'number' ) {
            // Do nothing atm.
        } else
        if ( typeof value === 'string' ) {
            let v = Number(value);
            if ( isNaN( v ) ) {
                throw new AttributeValueError(`Invalid double value: ${value}`, attributeDef );
            }

            value = v;
        } else {
            throw new AttributeValueError(`Invalid double value: ${value}`, attributeDef );
        }

        return value;
    }
}

export class StaticTableDomainFunctions extends BaseDomainFunctions {
    private getImplicitContext( context: string ): string {
        if ( !this.domain.contexts )
            throw new ZeidonError( `Table domain has no contexts.  Domain: ${this.domain.name}` );

        if ( !context )
            context = this.domain.defaultContext;

        if ( !context )
            throw new ZeidonError( `Invalid context ${context}` );

        let entries = this.domain.contexts[ context ];
        if ( !entries )
            throw new ZeidonError( `Context ${context} not found in domain ${this.domain.name}` );

        return context;
    }

    private getEntries( context: string ): any {
        context = this.getImplicitContext( context );
        return this.domain.contexts[ context ].entries;
    }

    convertExternalValue( externalValue: any, attributeDef: any, context? : string ): any {
        this.checkForRequiredValue( externalValue, attributeDef );

        if ( externalValue === null || externalValue === undefined )
            return undefined;

        let entries = this.getEntries( context );

        // If externalValue maps to key in entries then externalValue is actually
        // an internal value.  Just return it.
        if ( entries[ externalValue ] )
            return  externalValue;

        for ( let internalValue in entries ) {
            if ( entries[ internalValue ] === externalValue )
                return internalValue;
        }

        throw new AttributeValueError( `Unknown table value for ${externalValue}`, attributeDef );
    }

    convertToJsType( internalValue: any, attributeDef: any, context? : string ): any {
        let entries = this.getEntries( context );

        // If there is a mapping of internalValue to external value, then return the
        // external value.
        if ( entries[ internalValue ] )
            return entries[ internalValue ];

        // No mapping.  This means that the internalValue is actually an external
        // value, so return that.
        return internalValue;
    }

    /**
     *
     * Returns the table domain entries as a list of external/internal values.
     */
    getTableEntries?( context?: string ): Array<TableDomainEntry> {
        // Returns an object where the keys = internalValue, values = externalValue.
        let entries = this.getEntries( context );
        const keys = Object.keys( entries );
        const tables = keys.map( ( k ) => ( { internalValue: k, externalValue: entries[ k ] } ) );
        return tables;
    }

    getTableValues?( context?: string ): Array<string> {
        let entries = this.getEntries( context );
        return Object.keys( entries ).map( key => entries[ key ] );
    }
}

export class DateTimeDomainFunctions extends BaseDomainFunctions {
    convertToDate?( value: any, attributeDef: any, context? : any ): any {
        this.checkForRequiredValue( value, attributeDef );
        if ( Object.prototype.toString.call( value ) === '[object Date]' )
            return value;

        if ( value === "NOW" )
            return new Date();

        let date = Date.parse( value );
        if ( isNaN( date ) )
            throw new AttributeValueError(`Invalid date/time value: ${value}`, attributeDef );

        return new Date( date );
    }

    convertExternalValue( value: any, attributeDef: any, context?: any ): any {
        let date = this.convertToDate( value, attributeDef );

        if ( Object.prototype.toString.call( date ) === '[object Date]' )
            return date;

        throw new AttributeValueError( `Invalid date/time value: ${value}`, attributeDef );
    }
}

export class DateDomainFunctions extends DateTimeDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context? : any ): any {
        let date = super.convertExternalValue( value, attributeDef ) as Date;
        date.setHours( 0, 0, 0, 0 );
        return date;
    }
}

export class Base64BlobDomainFunctions extends BaseDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context?: any ): any {
        this.checkForRequiredValue( value, attributeDef );

        if ( typeof value === 'string' )
            return value;

        if ( value === undefined || value === null )
            return undefined;

        throw new AttributeValueError( `Invalid value for Base64 Blob: must be an encoded string`, attributeDef );
    }
}

// Dummy class for copying.
export class xxxDomainFunctions extends BaseDomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context?: any ): any {
        this.checkForRequiredValue( value, attributeDef );
        return value;
    }
}
