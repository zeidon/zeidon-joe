import { ZeidonConfiguration } from '@zeidon/core';
import { HttpClient, RestActivator, RestCommitter, ZeidonRestValues } from '@zeidon/rest-client';

/**
 * REST configuration for Zeidon that uses 'ky' browser http client.
 */
export class RestZeidonConfiguration  extends ZeidonConfiguration {
    constructor( private values: ZeidonRestValues ) {
        super( new RestActivator( values, new KyHttpWrapper( values ) ),
               new RestCommitter( values, new KyHttpWrapper( values ) ) );
        console.log("--- KyZeidonRestConfiguration --- " + values.restUrl );
    }
}

class KyHttpWrapper implements HttpClient {
    private ky = require( "ky" );

    constructor( private values: ZeidonRestValues ) {}

    get( url: string ) : Promise<Response> {
        let rsp = this.ky( url ) as Promise<unknown>;
        return rsp as Promise<Response>;
    }

    post( url: string, body: string, headers?: { [ name: string]: string | string[]; } ) : Promise<Response> {
        let rsp = this.ky.post( url, { body: body, headers: headers } ) as Promise<unknown>;
        return rsp as Promise<Response>;
    }
}
