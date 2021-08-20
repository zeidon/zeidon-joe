import { ZeidonConfiguration } from '@zeidon/core';
import { HttpClient, RestActivator, RestCommitter, ZeidonRestValues } from '@zeidon/rest-client';

/**
 * REST configuration for Zeidon that uses 'got' http client.
 */
export class RestZeidonConfiguration extends ZeidonConfiguration {
    constructor( private values: ZeidonRestValues ) {
        super( new RestActivator( values, new GotHttpWrapper( values ) ),
               new RestCommitter( values, new GotHttpWrapper( values ) ) );
        console.log("--- ZeidonRestConfiguration --- " + values.restUrl );
    }
}

/**
 * A wrapper class that uses the 'got' library and makes
 * Zeidon get/post calls
 */
class GotHttpWrapper implements HttpClient {
    private got = require( "got" );

    constructor( private values: ZeidonRestValues ) { }

    get( url: string ) : Promise<Response> {
        let rsp = this.got( url ) as Promise<unknown>;
        return rsp as Promise<Response>;
    }

    post( url: string, body: string, headers?: { [ name: string]: string | string[]; } ) : Promise<Response> {
        let rsp = this.got.post( url, { body: body, headers: headers } ) as Promise<unknown>;
        return rsp as Promise<Response>;
    }
}
