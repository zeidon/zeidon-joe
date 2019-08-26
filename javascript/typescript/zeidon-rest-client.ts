import { ObjectInstance, Activator } from './zeidon';
import { Committer, CommitOptions, ActivateLockError } from './zeidon';

/**
 * Interface for wrapping different HTTP clients into a form that can be used by Zeidon.
 * Response is expected to have the following shape:
 * {
 *      body: "<body returned by HTTP call>"
 * }
 */
export interface HttpClient {
    get( url: string ): Promise<Response>;
    post( url: string, body: string, headers: Object ): Promise<Response>;
}

export class RestActivator extends Activator {
    constructor( private values: ZeidonRestValues, private http: HttpClient ) { super(); }

    activateOi<T extends ObjectInstance>( oi: T, qual?: any ): Promise<T> {
        this.executePreActivateHooks( oi, qual );

        if ( qual == undefined )
            qual = { rootOnly: true };

        let lodName = oi.getLodDef().name;

        let mapResponse = ( response ): T => {
            if ( response.statusCode === 423 )
                throw new ActivateLockError( lodName );

            if ( response.body === undefined )
                throw "response.body is undefined";

            let newOi = oi.createFromJson( response.body, { incrementalsSpecified: true } );
            this.executePostActivateHooks( oi, qual );

            return newOi as T;
        }

        // If qualifcation consists of a single check for id then use the path form of activate.
        // Otherwise pass in the whole
        let url = ( Object.keys( qual ).length === 1 && qual.id ) ?
            `${this.values.restUrl}/${lodName}/${qual.id}` :
            `${this.values.restUrl}/${lodName}?qual=${encodeURIComponent( JSON.stringify( qual ) )}`;

        return this.http.get( url )
            .then( response => mapResponse( response ) );
    }
}

/**
 * These are the values for configuring Zeidon to use a REST server for activate/commits.
 */
export class ZeidonRestValues {
    restUrl: string;
}

export class RestCommitter extends Committer {
    constructor( private values: ZeidonRestValues, private http: HttpClient ) { super(); }

    commitOi( oi: ObjectInstance, options?: CommitOptions ): Promise<ObjectInstance> {
        this.executePreCommitHooks( oi, options );

        let lodName = oi.getLodDef().name;
        let body = JSON.stringify( oi.toZeidonMeta() );
        let url = `${this.values.restUrl}/${lodName}`;

        return this.http.post( url, body, { 'Content-Type': 'application/json' } )
            .then( response => this.parseCommitResponse( oi, response, options ) );
    }

    dropOi( oi: ObjectInstance, options?: CommitOptions ) {
        let lodName = oi.getLodDef().name;
        if ( oi.root.length != 1 )
            throw "The only currently supported option for dropOi is a single root OI."

        let root = oi.root[ 0 ];
        let keyDef = root.keyAttributeDef;
        let qual = {};
        qual[ keyDef.name ] = root.getAttribute( keyDef.name )
        let body = "qual=" + JSON.stringify( qual );
        let url = `${this.values.restUrl}/${lodName}/dropLock`;

        return this.http.post( url, body, { 'Content-Type': 'application/x-www-form-urlencoded' } )
            .then( response => console.log( "DropOi response = " + response.body ) );
    }

    parseCommitResponse( oi: ObjectInstance, response, options?: CommitOptions ): ObjectInstance {
        let newOi: ObjectInstance = undefined;
        if ( response.body === "{}" )
            newOi = oi.createFromJson( undefined );
        else
            newOi = oi.createFromJson( response.body, { incrementalsSpecified: true } );

        this.executePostCommitHooks( newOi, options );

        return newOi;
    }
}

export function parseOiFromJsonResponse<T extends ObjectInstance>( oi: T, response ): T {
    if ( response === "{}" || Object.keys( response ).length === 0 )
        return oi;

    return oi.createFromJson( response, { incrementalsSpecified: true } );
}
