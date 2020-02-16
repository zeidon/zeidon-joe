let configurationInstance: ZeidonConfiguration = undefined;

// Flag used to indicate that any attribute in a entity was updated.
const ANY_ATTRIBUTE_UPDATED = "_updated";
// Flag to indicate that an EI is linked with another EI.
const ENTITY_IS_LINKED = "_linked";

/**
 * Keeps track of the current EI fingerprint.  The fingerprint is used to differentiate between
 * EIs that don't (yet) have a key.
 */
let entityInstanceFingerprintCount = 0;

export class Application {
    lodDefs: Object;

    constructor( lodDefs: Object ) {
        this.lodDefs = lodDefs;
    }
}

export class ObjectInstance {
    protected roots: EntityArray<EntityInstance>;
    public isUpdated = false;
    public readOnly = false;
    public isLocked = false;

    constructor( initialize = undefined, options: CreateOptions = DEFAULT_CREATE_OPTIONS ) {
        this.createFromJson( initialize, options );
    }

    protected rootEntityName(): string { throw "rootEntityName must be overridden" };
    public getPrototype( entityName: string ): any { throw "getPrototype must be overriden" };
    public getLodDef(): any { throw "getLodDef must be overridden" };
    public getApplicationName(): String { throw "getApplicationName must be overriden" };
    public getDomain( name: string ): Domain { throw "getDomain() must be overriden" };
    public getEntityDef( name: string ): any { return this.getLodDef().entities[ name ] }

    public _isUpdatedNonPersist: boolean = false;

    /**
     * Returns true if this OI has any updates to persistent or non-persistent (i.e. work) data.
     */
    get isUpdatedNonPersist(): boolean {
        return this.isUpdated || this._isUpdatedNonPersist;
    }

    public getDomainFunctions( domain: Domain ): DomainFunctions {
        // Can be overwritten but not necessary.
        return undefined;
    }

    // Saves the options used to activate this OI.
    private activateQual: any;

    public toJSON( options: ZeidonToJsonOptions = {} ): Object {
        let jarray = [];
        for ( let root of this.roots.allEntities() ) {
            // TODO: can't use forCommit yet because the OI that comes back doesn't have
            // the missing entities.  We can't use forCommit until we implement a merge.
            // If forCommit is true, only write updated entities.
            // if ( ! options.forCommit || root.childUpdated )
            jarray.push( root.toJSON( options ) );
        };

        let json = {};
        json[ this.rootEntityName() ] = jarray;
        return json;
    }

    public visitEntityInstances( visitor: EntityInstanceVisitor ) {
        for ( let ei of this.roots.allEntities() ) {
            ei.visit( visitor );
        };
    }

    get root(): EntityArray<EntityInstance> {
        return this.roots as EntityArray<EntityInstance>;
    }

    public logOi( incrementals?: boolean ) {
        if ( incrementals )
            console.log( JSON.stringify( this.toZeidonMeta(), null, 2 ) );
        else
            console.log( JSON.stringify( this, null, 2 ) );
    }

    /**
     * Wrap the JSON for this object with Zeidon OI meta.  Used for committing.
     */
    toZeidonMeta( options?: CommitOptions ): any {
        options = options || { meta: true, forCommit: true };

        let wrapper = {
            ".meta": { version: "1" },
            OIs: [ {
                ".oimeta": {
                    application: this.getApplicationName(),
                    odName: this.getLodDef().name,
                    incremental: true,
                    locked: this.isLocked,
                    readOnlyOi: this.readOnly
                }
            }]

        };

        // Add the OI.
        let oi = this.toJSON( options );
        let root = oi[ this.getLodDef().name ];
        wrapper.OIs[ 0 ][ this.getLodDef().name ] = root;

        return wrapper;
    }

    public static activateOi<T extends ObjectInstance>( oi: T, qual?: any ): Promise<T> {
        let config = configurationInstance;
        if ( !config )
            error( "ZeidonConfiguration not properly initiated." )

        oi.activateQual = qual;
        return config.getActivator().activateOi( oi, qual );
    }

    public commit( options?: CommitOptions ): Promise<this> {
        let config = configurationInstance;
        if ( !config )
            error( "ZeidonConfiguration not properly initiated." )

        return config.getCommitter().commitOi( this, options ) as Promise<this>;
    }

    /**
     * Reset this OI so that's empty.
     */
    public reset() {
        this.roots = new EntityArray<EntityInstance>( this.rootEntityName(), this, undefined );
        this.isUpdated = false;
        this._isUpdatedNonPersist = false;
    }

    /**
     * Resets all incremental flags and reset isUpdated to false.
     */
    public resetIncrementals() {
        this.visitEntityInstances( {
            visit( ei: EntityInstance ) {
                ei.resetIncrementals();
            }
        } );
        this.isUpdated = false;
        this._isUpdatedNonPersist = false;
    }

    public reload(): Promise<this> {
        this.reset();
        let obs = ObjectInstance.activateOi( this, this.activateQual );
        return obs;
    }

    public get isEmpty(): boolean {
        return this.roots.length === 0;
    }

    public drop() {
        if ( this.isLocked ) {
            let config = configurationInstance;
            if ( !config )
                error( "ZeidonConfiguration not properly initiated." )

            config.getCommitter().dropOi( this );

        }
    }

    private loadOiMetaFromJson( oimeta, options: CreateOptions ) {
        // If incrementals are set then set the constructor option to
        // not set the update flag when the attribute value is set.  The
        // flags will be set by the incrementals.
        if ( oimeta.incremental ) {
            if ( options.incrementalsSpecified === undefined ) {
                // We're going to change the options so create a new one so we
                // don't override the original one.
                options = Object.assign( {}, options );
                options.incrementalsSpecified = true;
            }
        }

        if ( oimeta.pagination && oimeta.pagination.totalCount ) {
            let p = this.activateQual.pagination;
            p.totalCount = oimeta.pagination.totalCount;
            p.totalPages = oimeta.pagination.totalPages;
        }

        if ( oimeta.locked )
            this.isLocked = true;

        if ( oimeta.readOnlyOi !== undefined )
            this.readOnly = oimeta.readOnlyOi;
    }

    createFromJson( initialize, options: CreateOptions = DEFAULT_CREATE_OPTIONS ): this {
        if ( typeof initialize == "string" ) {
            initialize = JSON.parse( initialize );
        }

        this.reset();
        if ( !initialize || Object.keys( initialize ).length  === 0 ) {
            return this;  // Nothing to initialize so just return an empty OI.
        }

        // if OIs is specified then this has the full Zeidon meta.
        if ( initialize.OIs ) {
            // TODO: Someday we should handle multiple return OIs but for now
            // we'll assume just one and hardcode '[0]'.
            let oimeta = initialize.OIs[ 0 ][ ".oimeta" ];
            if ( oimeta )
                this.loadOiMetaFromJson( oimeta, options );

            let root = initialize.OIs[ 0 ][ this.rootEntityName() ];
            if ( root ) {
                for ( let i of initialize.OIs[ 0 ][ this.rootEntityName() ] ) {
                    this.roots.create( i, options );
                }
            }

            return this;
        }

        if ( initialize.constructor === Array ) {
            for ( let i of initialize ) {
                this.roots.create( i, options );
            }

            return this;
        }

        if ( initialize[ this.rootEntityName() ] && initialize[ this.rootEntityName() ].constructor === Array ) {
            for ( let i of initialize[ this.rootEntityName() ] ) {
                this.roots.create( i, options );
            }

            return this;
        }

        delete initialize.version;  // Ignore version for now.
        this.roots.create( initialize, options );

        return this;
    }

    handleActivateError( e ) {
        console.log( "There was an error: " + e );
    }
}

class Incrementals {
    created = false;
    included = false;
    deleted = false;
    excluded = false;
    updated = false;
}

export class EntityInstance {
    public oi: ObjectInstance; // Parent OI.
    private incrementals = new Incrementals();
    public childUpdated = false;  // True if this entity or one of its children is updated.

    // The persistent attribute values stored as a hash (aka Object).  They key is
    // the attribute name, the value is the attribute value.  The flag indicating
    // that an attribute has been updated is stored with a key of ".attrname".
    // Linked information is stored under key "_linkInfo".
    public attributes: any = {};
    public workAttributes: any = {};  // Work attribute stored same as 'attributes'.

    public validateErrors: any = {};

    // If incomplete = true then this entity did not have all its children
    // loaded and so cannot be deleted.
    public incomplete = false;

    // A value that can be used to compare EIs that don't have a key.
    public readonly fingerprint = String( entityInstanceFingerprintCount++ );

    // Map of child entities and the array associated with each one.
    // Key: entityName
    // Value: EntityArray.
    private childEntityInstances = {};

    // This is the EntityArray of the parent EI that stores 'this'.
    private parentArray: EntityArray<EntityInstance>;

    public get created() { return this.incrementals.created };
    public get deleted() { return this.incrementals.deleted };
    public get included() { return this.incrementals.included };
    public get excluded() { return this.incrementals.excluded };
    public get updated() { return this.incrementals.updated || this.attributes[ ANY_ATTRIBUTE_UPDATED ] === true };

    public get touched() { return this.updated || this.created || this.deleted || this.excluded || this.included };


    /**
     * Returns true if this EI is linked with another.
     */
    get isLinked() { return !!this.attributes[ ENTITY_IS_LINKED ] }

    private setIncremental( v: boolean, flag: string ) {
        if ( v && !this.incrementals[ flag ] ) {
            this.oi.isUpdated = true;
            this.childUpdated = true;
            for ( let parent = this.parentEntityInstance(); parent; parent = parent.parentEntityInstance() ) {
                parent.childUpdated = true;
            }
        }

        this.incrementals[ flag ] = v;
    }

    resetIncrementals() {
        this.incrementals = new Incrementals();
        if ( this.parentArray ) {
            this.parentArray.delegate.hiddenEntities = [];
        }

    }

    public set created( v: boolean ) { this.setIncremental( v, "created" ) }
    public set deleted( v: boolean ) { this.setIncremental( v, "deleted" ) }
    public set included( v: boolean ) { this.setIncremental( v, "included" ) }
    public set excluded( v: boolean ) { this.setIncremental( v, "excluded" ) }
    public set updated( v: boolean ) { this.setIncremental( v, "updated" ) }

    public get entityName(): string { throw "entityName() but be overridden" };
    public get entityDef(): any { return this.oi.getLodDef().entities[ this.entityName ]; }
    public getAttributeDef( attributeName: string ): any {
        let attributeDef = this.entityDef.attributes[ attributeName ];
        if ( !attributeDef )
            return undefined;

        if ( !attributeDef.domain ) {
            let domain = this.oi.getDomain( attributeDef.domainName );
            if ( domain ) {
                attributeDef.domain = domain;
                if ( !domain.domainFunctions )
                    domain.domainFunctions = this.oi.getDomainFunctions( domain );
            }
            else {
                console.log( `Couldn't find domain '${attributeDef.domain}' for attribute ${this.entityDef.name}.${attributeDef.name}` );
            }

        }
        return attributeDef;
    }

    get keyAttributeDef(): any {
        let attributeDefs = this.entityDef.attributes;
        let keyDefs = [];
        for ( let attrName in attributeDefs ) {
            if ( attributeDefs[ attrName ].key )
                keyDefs.push( attributeDefs[ attrName ] );
        }

        if ( keyDefs.length != 1 )
            error( `keyAttributeDef can only be called for entities with a single key. Entity = ${this.entityName}` );

        return keyDefs[ 0 ];
    };

    get key(): string {
        let key = this.keyAttributeDef;
        return this.getAttribute( key.name )
    };
    set key( value: string ) { this.setAttribute( this.keyAttributeDef, value ) };

    constructor( initialize: Object,
        oi: ObjectInstance,
        parentArray: EntityArray<EntityInstance>,
        options: CreateOptions = DEFAULT_CREATE_OPTIONS ) {
        this.oi = oi;
        this.parentArray = parentArray;
        for ( let attr in initialize ) {
            if ( this.getAttributeDef( attr ) ) {
                this.setAttribute( attr, initialize[ attr ], options );
                continue;
            }

            if ( this.entityDef.childEntities[ attr ] ) {
                let init = initialize[ attr ];
                if ( !( init.constructor === Array ) ) {
                    init = [ init ];  // If it's not an array, wrap it.
                }
                for ( let o of init ) {
                    let array = this.getChildEntityArray( attr );
                    array.create( o, options );
                }
                continue;
            }

            if ( attr === ".meta" ) {
                this.parseEntityMeta( initialize[ attr ] );
                continue;
            }

            if ( attr.startsWith( "." ) ) {
                let metaName = attr.substr( 1 ); // Remove leading "."
                if ( this.getAttributeDef( metaName ) ) {
                    let attribs = this.getAttribHash( metaName );
                    attribs[ attr ] = initialize[ attr ];
                    continue;
                }
            }

            error( `Unknown attribute ${attr} for entity ${this.entityName}` );
        }

        if ( !options.incrementalsSpecified ) {
            this.setDefaultAttributeValues();
            this.created = true;
            this.oi.isUpdated = true;
        }
    }

    private setDefaultAttributeValues() {
        let entityDef = this.entityDef;
        if ( !entityDef.hasInit )
            return;

        for ( let attributeName in entityDef.attributes ) {
            let attributeDef = entityDef.attributes[ attributeName ];
            if ( !attributeDef.initialValue )
                continue;

            // If the attribute is already set, skip it.
            if ( this.getAttribute( attributeName ) != undefined )
                continue;

            this.setAttribute( attributeName, attributeDef.initialValue );
        }
    }

    setAttribute( attr: string, value: any, options: CreateOptions = DEFAULT_CREATE_OPTIONS ) {
        //    console.log( `Setting attribute ${attr}`)
        let attributeDef = this.getAttributeDef( attr );

        if ( !attributeDef )
            throw new InvalidAttributeError( attr, this.entityDef );

        // Perform some validations unless incrementals are specified.
        if ( !options.incrementalsSpecified ) {
            if ( !attributeDef.update ) {
                throw new ZeidonError( `Attribute ${this.entityDef.name}.${attr} is read only` );
            }

            if ( this.oi.readOnly )
                throw new ZeidonError( "This OI is read-only." );

            if ( this.deleted || this.excluded )
                throw new ZeidonError( `Can't set attribute for hidden EntityInstance: ${this.entityDef.name}.${attr}` );
        }

        if ( attributeDef.domain && attributeDef.domain && attributeDef.domain.domainFunctions ) {
            value = attributeDef.domain && attributeDef.domain.domainFunctions.convertExternalValue( value, attributeDef );
        }

        let attribs = this.getAttribHash( attr );

        if ( attribs[ attr ] === value )
            return;

        attribs[ attr ] = value;

        if ( options.incrementalsSpecified )
            return;

        let metaAttr = "." + attr;
        if ( !attribs[ metaAttr ] )
            attribs[ metaAttr ] = {} as any;

        attribs[ metaAttr ].updated = true;

        if ( attributeDef.persistent ) {
            this.updated = true;
            this.oi.isUpdated = true;
            this.attributes[ ANY_ATTRIBUTE_UPDATED ] = true;
        } else {
            this.oi._isUpdatedNonPersist = true;
        }
    }

    public getAttribute( attr: string, context: string = undefined ): any {
        let attribs = this.getAttribHash( attr );
        let value = attribs[ attr ];

        let attributeDef = this.getAttributeDef( attr );
        if ( attributeDef.domain && attributeDef.domain.domainFunctions ) {
            value = attributeDef.domain.domainFunctions.convertToJsType( value, attributeDef, context );
        }

        return value;
    }

    public isAttributeUpdated( attr: string ): boolean {
        let attribs = this.getAttribHash( attr );
        let metaName = "." + attr;
        return ( attribs[ metaName ] && attribs[ metaName ].updated );
    }

    private getAttribHash( attr: string ): any {
        let attributeDef = this.getAttributeDef( attr );
        if ( !attributeDef )
            throw new InvalidAttributeError( attr, this.entityDef );

        if ( attributeDef.persistent )
            return this.attributes;
        else
            return this.workAttributes;
    }

    getChildEntityArray( entityName: string ): EntityArray<EntityInstance> {
        let entities = this.childEntityInstances[ entityName ];
        if ( entities === undefined ) {
            entities = new EntityArray<EntityInstance>( entityName, this.oi, this );
            this.childEntityInstances[ entityName ] = entities;
        }

        return entities;
    }

    public delete( options: DeleteOptions = {} ) {
        if ( options.index )
            throw new ZeidonError( "'index' option unexpected when deleting an entity instance.")

        options = { ...options }; // Clone so we can add index.
        options.index = this.parentArray.findIndex( ei => ei === this );
        this.parentArray.delete( options );
    }

    public drop() {
        let idx = this.parentArray.findIndex( ei => ei === this );
        this.parentArray.drop( idx );
    }

    public exclude( options : ExcludeOptions = {} ) {
        if ( options.index )
            throw new ZeidonError( "'index' option unexpected when excluding an entity instance." )

        options = { ...options }; // Clone so we can add index.
        options.index = this.parentArray.findIndex( ei => ei === this );
        this.parentArray.exclude( options );
    }

    public parentEntityInstance(): EntityInstance {
        return this.parentArray.parentEi;
    }

    private buildIncrementalStr(): string {
        let str = "";

        if ( this.updated )
            str += 'U';

        if ( this.created )
            str += 'C';

        if ( this.deleted )
            str += 'D';

        if ( this.included )
            str += 'I';

        if ( this.excluded )
            str += 'X';

        return str;
    }

    private parseEntityMeta( meta: any ) {
        if ( meta.incrementals ) {
            this.created = meta.incrementals.indexOf( "C" ) > -1;
            this.deleted = meta.incrementals.indexOf( "D" ) > -1;
            this.included = meta.incrementals.indexOf( "I" ) > -1;
            this.excluded = meta.incrementals.indexOf( "X" ) > -1;
            this.updated = meta.incrementals.indexOf( "U" ) > -1;
        }

        this.incomplete = !!meta.incomplete;
    }

    /**
     * Updates the attributes of this entity instance and any children that are specified
     * in 'values'.  The entity fingerprint is used to match up entities in 'value' to the
     * entities in the OI.
     *
     * Note: This will not create or re-order entities.  It is expected that every fingerprint
     * in 'values' exists in the OI.
     *
     * Sample input might look like:
     *      {
     *          fingerprint: 22,
     *          Attr1: 'new value',
     *          Attr2: 'another value',
     *          Attr3: true,
     *          Child1: [
     *              {
     *                  fingerprint: 49,
     *                  ChildAttr1: 10,
     *                  ChildAttr2: 'foo'
     *              }
     *          ]
     *      }
     */
    public update( values: any, options: UpdateOptions = {} ) {
        if ( typeof values !== 'object' )
            throw new ZeidonError( "Argument passed to update() must be an object" );

        for ( let key in values ) {
            // Ignore known non-attributes/entities like fingerprint
            if ( key === 'fingerprint' )
                continue;

            let attributeDef = this.getAttributeDef( key );
            if ( attributeDef ) {
                if ( ! attributeDef.update )
                    continue;

                if ( ! this.entityDef.updatable )
                    continue;

                let value = values[ key ];
                this.setAttribute( key, value );
                continue;
            }

            let childDef = this.entityDef.childEntities[ key ];
            if ( !childDef ) {
                if ( options.ignoreUnknownAttributeErrors )
                    continue;
                else
                    throw new ZeidonError( `Key '${key} in values does not match a known entity or attribute` );
            }

            let eiChildren = this.getChildEntityArray( key );
            let valueChildren = values[ key ];

            // Keep track of the fingerprints of the child entities.  We'll use
            // this to determine which children EIs need to be deleted.
            let childFingerprints = {};

            // Children of 1-to-1 relationships are not in an array.  Convert it to
            // an array to make it easier to process.
            if ( !Array.isArray( valueChildren ) )
                valueChildren = [ valueChildren ];

            for ( let valueChild of valueChildren ) {
                let eiChild = eiChildren.find( eiChild => eiChild.fingerprint === valueChild.fingerprint )
                if ( !eiChild )
                    throw new ZeidonError( "Couldn't find EI using fingerprint" );

                childFingerprints[ valueChild.fingerprint ] = true;
                eiChild.update( valueChild );
            }
            // Do we have a fingerprint for every child entity?
            if ( Object.keys( childFingerprints ).length < eiChildren.length ) {
                // No.  Delete all child entities that are missing from the list of fingerprints.
                eiChildren.deleteAll( { filter: ( ei ) => !childFingerprints[ ei.fingerprint ] } );
            }
        }
    }

    public toJSON( options: ZeidonToJsonOptions = {} ): Object {
        // TODO: can't use forCommit yet because the OI that comes back doesn't have
        // the missing entities.  We can't use forCommit until we implement a merge.
        // if ( options.forCommit && ! this.childUpdated )
        //     return undefined;

        let json = {};

        if ( options.meta ) {
            let meta = {} as any;
            let incrementals = this.buildIncrementalStr();
            if ( incrementals != "" )
                meta.incrementals = incrementals;

            if ( Object.keys( meta ).length > 0 )
                json[ ".meta" ] = meta;
        }

        for ( let attrName in this.entityDef.attributes ) {
            let attrValue = this.getAttribute( attrName );
            if ( attrValue !== undefined && attrValue !== null )
                json[ attrName ] = attrValue;

            if ( options.meta && this.isAttributeUpdated( attrName ) ) {
                json[ "." + attrName ] = { updated: true };
            }
        };

        for ( let entityName in this.entityDef.childEntities ) {
            if ( options.childEntities && options.childEntities.indexOf( entityName ) == -1 ) {
                continue;
            }

            let entities = this.getChildEntityArray( entityName ).allEntities();
            if ( entities.length === 0 )
                continue;

            let entityInfo = this.entityDef.childEntities[ entityName ];
            if ( entityInfo.cardMax === 1 ) {
                // TODO: can't use forCommit yet because the OI that comes back doesn't have
                // the missing entities.  We can't use forCommit until we implement a merge.
                //if ( ! options.forCommit || entities[0].childUpdated )
                if ( entities[ 0 ].childUpdated )
                    json[ entityName ] = entities[ 0 ].toJSON( options );
            } else {
                // Filter is used to remove= undefined values; these are returned if options.forCommit
                // is true and the ei wasn't updated.
                json[ entityName ] = entities.map( ei => ei.toJSON( options ) ).filter( ei => ei );
            }
        }

        return json;
    }

    visit( visitor : EntityInstanceVisitor ) {
        visitor.visit( this );

        for ( let entityName in this.entityDef.childEntities ) {
            let entities: EntityArray<EntityInstance> = this.childEntityInstances[ entityName ];
            if ( entities === undefined || entities.length === 0 )
                continue;

            for ( let ei of entities ) {
                ei.visit( visitor );
            }
        }

        if ( visitor.visitPostChildren )
            visitor.visitPostChildren( this );
    }
};

export interface EntityInstanceVisitor {
    visit( ei: EntityInstance ): any;
    visitPostChildren?( ei: EntityInstance ): any;
}

export interface UpdateOptions {
    ignoreUnknownAttributeErrors?: boolean
}

export const Position = {
    First: 'first',
    Last: 'last',
    Next: 'next',
    Prev: 'prev'
}

/**
 * When inserting/including entities, this indicates where to do the insert.  Value
 * may be a number--representing an index--or a string = values in Position (e.g. 'first').
 */
export type CursorPosition = string | number;

export interface IncludeOptions {
    position?: CursorPosition;
    incrementalsSpecified? : boolean;
}

/**
 * Include logic can get pretty hairy.  This class tries to perform it.
 */
class Relinker {
    sourceEi: EntityInstance;

    include( targetArr: EntityArray<EntityInstance>,
        source: EntityInstance,
        includeOptions: IncludeOptions ) {

        this.sourceEi = source;
        this.validateInclude( targetArr );

        // If sourceEi is not linked to anything else, then we need to add all non-hidden
        // attributes to the hash.
        if ( !this.sourceEi.isLinked )
            this.addAllPersistentAttributes();

        this.includeWithChildren( targetArr, source, includeOptions.position );
        targetArr.selected().included = true;
    }

    private includeWithChildren( targetArr: EntityArray<EntityInstance>,
        source: EntityInstance,
        position: CursorPosition ) {
        targetArr.create( {}, { position: position, incrementalsSpecified: true } );
        this.link( source, targetArr.selected() );

        // Now find matching entities under source with the same relationship as target.
        // We need to include those next.

        for ( let srcChildName in source.entityDef.childEntities ) {
            let srcChildDef = source.oi.getLodDef().entities[ srcChildName ];

            for ( let tgtChildName in targetArr.delegate.entityDef.childEntities ) {
                let tgtChildDef = targetArr.delegate.lodDef.entities[ tgtChildName ];
                if ( tgtChildDef.erToken === srcChildDef.erToken &&
                    tgtChildDef.relToken === srcChildDef.relToken &&
                    tgtChildDef.isRelLink === srcChildDef.isRelLink ) {

                    // Same relationship!  Include all the children.
                    let srcChildArr = source.getChildEntityArray( srcChildDef.name );
                    for ( let srcChildEi of srcChildArr ) {
                        this.includeWithChildren( targetArr.selected().getChildEntityArray( tgtChildName ),
                            srcChildEi, Position.Last );
                    }
                }
            }
        }
    }

    private link( source: EntityInstance, target: EntityInstance ) {
        target.attributes = source.attributes;
        target.attributes[ ENTITY_IS_LINKED ] = true;
    }

    /**
     * Make sure the attribute hash has all the target non-hidden, persistent attributes.  If
     * they aren't in the hash we create one and set it to null.  This is how we can determine
     * if all the attributes in a source entity can cover the attributes in a target.
     *
     * @param target
     */
    private addAllPersistentAttributes() {
        const attributes = this.sourceEi.attributes;
        const sourceEntityDef = this.sourceEi.entityDef;

        for ( let attrName in sourceEntityDef.attributes ) {
            let attributeDef = this.sourceEi.getAttributeDef( attrName );
            if ( attributeDef.hidden )
                continue;

            if ( !attributeDef.persistent )
                continue;

            if ( attributes[ attrName ] )  // Already have a value?
                continue;

            attributes[ attrName ] = null;
        };
    }

    private validateLink( targetEntityDef ) {
        let sourceEntityDef = this.sourceEi.entityDef;
        if ( sourceEntityDef.erToken !== targetEntityDef.erToken )
            throw new ZeidonError( `Entities ${targetEntityDef.name} and ${sourceEntityDef.name} are not the same ER entity.` );
    }

    private validateInclude( targetArr: EntityArray<EntityInstance> ) {
        let targetEntityDef = targetArr.delegate.entityDef;

        this.validateLink( targetEntityDef );

        if ( targetArr.length >= targetEntityDef.cardMax )
            throw new ZeidonError( `Including a new instance for ${targetEntityDef.name} voilates max cardinality.` );

        if ( !targetEntityDef.includable )
            throw new ZeidonError( `Entity ${targetEntityDef.name} is not includable.` );

        // TODO: check to see if oi is updatable.
    }
}

/**
 * Array<T> is one of the few classes we can't directly extend so we have to create
 * a delegate class that handles all the real work.  We'll set the appropriate function
 * names when we construct EntityArray<T>.
 *
 * See https://github.com/Microsoft/TypeScript/issues/12013 for more.
 */
class ArrayDelegate<T extends EntityInstance> {
    hiddenEntities: Array<T>;
    currentlySelected: any;

    constructor( private array: Array<T>,
        private entityName: string,
        private oi: ObjectInstance,
        private parentEi: EntityInstance ) {
        this.currentlySelected = 0;
    }

    get lodDef() { return this.oi.getLodDef() }
    get entityDef() { return this.oi.getLodDef().entities[ this.entityName ]; }
    get length() { return this.array.length }

    getHidden() {
        if ( ! this.hiddenEntities )
            this.hiddenEntities = [];

        return this.hiddenEntities;
    }

    create( initialize: object = {}, options: CreateOptions = DEFAULT_CREATE_OPTIONS ): EntityInstance {
        options = options || {};  // Make sure options is at least an empty object.

        if ( !this.entityDef.create && !options.incrementalsSpecified )
            throw new ZeidonError( `Entity ${this.entityDef.name} does not have create authority.` );

        if ( this.oi.readOnly && !options.incrementalsSpecified )
            throw new ZeidonError( "This OI is read-only." );

        let ei = Object.create( this.oi.getPrototype( this.entityName ) );
        ei.constructor.apply( ei, [ initialize, this.oi, this.array, options ] );

        // Figure out where to insert the new ei.
        let position = options.position;
        if ( position === undefined ) {
            // Default is to insert at the end.
            this.array.push( ei );
        }
        else if ( typeof position === "number" ) {
            if ( position < 0 || position > this.array.length )
                throw new ZeidonError( `Invailid position '${position}'.  Must be between 0 and ${this.array.length}` );

            this.array.splice( position, 0, ei );
        }
        else {
            switch ( position ) {
                case Position.Last:
                    this.array.push( ei );
                    break;

                case Position.First:
                    this.array.unshift( ei );
                    break;

                case Position.Next:
                    let newPos = Math.min( this.currentlySelected + 1, this.array.length );
                    this.array.splice( newPos, 0, ei );
                    break;

                case Position.Prev:
                    // If currentlySelected is 0, then put at the beginning.
                    if ( this.currentlySelected == 0 )
                        this.array.unshift( ei );
                    else
                        this.array.splice( this.currentlySelected - 1, 0, ei );
                    break;

                default:
                    error( `Unknown position option: ${position}` );
            }
        }

        this.setSelected( ei );
        return ei;
    }

    include( entityArray: EntityArray<EntityInstance>, sourceEi: EntityInstance, options: IncludeOptions = {} ): EntityInstance {
        if ( !this.entityDef.includable )
            throw new ZeidonError( `Entity ${this.entityDef.name} does not have include authority.` );

        if ( this.oi.readOnly && !options.incrementalsSpecified )
            throw new ZeidonError( "This OI is read-only." );

        options = { ...options }; // Clone the options so we can change the values.
        if ( options.position === undefined )
            options.position = this.currentlySelected;

        let includer = new Relinker();
        includer.include( entityArray, sourceEi, options );
        return null;
    }

    private validateExclude( index?: number ) {
        if ( !this.entityDef.excludable )
            throw new ZeidonError( `Entity ${this.entityDef.name} does not have exclude authority.` );
    }

    excludeAll( options : ExcludeAllOptions = {} ) {
        if ( ! options.incrementalsSpecified )
            this.validateExclude();

        if ( this.array.length == 0 )
            return;

        this.hiddenEntities = this.getHidden().concat( this.array );
        for ( let ei of this.array )
            ( <any>ei ).excluded = true;

        this.oi.isUpdated = true;
        this.array.length = 0;
    }

    private validateDelete( options : DeleteOptions, index?: number ) {
        if ( this.oi.readOnly && !options.incrementalsSpecified )
            throw new ZeidonError( "This OI is read-only." );

        if ( !this.entityDef.deletable )
            throw new ZeidonError( `Entity ${this.entityDef.name} does not have delete authority.` );

        let list = index ? [ this.array[ index ] ] : this.array;
        for ( let ei of list ) {
            if ( ei.incomplete )
                throw new ZeidonError( `Entity ${this.entityDef.name} is incomplete and cannot be deleted.` );
        }
    }

    deleteAll( options : DeleteAllOptions = {} ) {
        options = options || {};
        this.validateDelete( options );
        if ( this.array.length === 0 )
            return;

        this.hiddenEntities = this.getHidden().concat( this.array );
        for ( let ei of this.array ) {
            if ( options.filter === undefined || options.filter( ei ) === true )
                ei.delete();
        }

        this.array.length = 0;
    }

    resetCurrentlySelected( index: number, position : CursorPosition ) {
        position = position || Position.Next;
        if ( typeof position === "number" ) {
            if ( position < 0 || position > this.array.length )
                throw new ZeidonError( `Invailid reposition '${position}'.  Must be between 0 and ${this.array.length}` );

            this.currentlySelected = position;
        }
        else {
            switch ( position ) {
                case Position.Last:
                    this.currentlySelected = this.array.length - 1;
                    break;

                case Position.First:
                    this.currentlySelected = 0;
                    break;

                case Position.Next:
                    this.currentlySelected = Math.min( index + 1, this.array.length );
                    break;

                case Position.Prev:
                    // If currentlySelected is 0, then put at the beginning.
                    this.currentlySelected = Math.max( index - 1, 0 );
                    break;

                default:
                    throw `Unknown reposition option: ${position}`;
            }
        }
    }

    delete( options: DeleteOptions = {} ) {
        let index = options.index || this.currentlySelected;

        if ( ! options.incrementalsSpecified )
            this.validateDelete( options, index );

        let ei = this.array.splice( index, 1 )[ 0 ];

        // If the EI was also created then it's "dead" and no longer needed.
        if ( ! ei.created ) {
            this.getHidden().push( ei );
        }

        this.deleteEntity( ei );
        this.resetCurrentlySelected ( index, options.reposition );
    }

    drop( index?: number ) {
        if ( index == undefined )
            index = this.currentlySelected;

        let ei = this.array.splice( index, 1 )[ 0 ];
        ei.deleted = true;
        while ( ei = ei.parentEntityInstance() as T ) {
            ei.incomplete = true;
        }
    }

    exclude( options : ExcludeOptions = {} ) {
        if ( this.oi.readOnly && !options.incrementalsSpecified )
            throw new ZeidonError( "This OI is read-only." );

        let index = options.index || this.currentlySelected;
        let ei = this.array.splice( index, 1 )[ 0 ];
        ei.excluded = true;
        this.oi.isUpdated = true;

        // If the EI was also included then it's "dead" and no longer needed.
        if ( !ei.created ) {
            this.getHidden().push( ei );
        }

        this.resetCurrentlySelected( index, options.reposition );
    }

    private deleteEntity( ei: EntityInstance ) {
        ei.deleted = true;
        ei.oi.isUpdated = true;
        let entityDef = ei.entityDef;
        for ( let childName in entityDef.childEntities ) {
            let childEntityDef = ei.oi.getEntityDef( childName );
            if ( childEntityDef.parentDelete )
                ei.getChildEntityArray( childName ).deleteAll( { incrementalsSpecified: true });
            else
                ei.getChildEntityArray( childName ).excludeAll( { incrementalsSpecified: true } );
        }
    }

    first( setCurrent? : boolean ): EntityInstance {
        if ( this.array.length === 0 )
            return undefined;

        if ( setCurrent )
            this.currentlySelected = 0;

        return this.array[ 0 ];
    }

    last( setCurrent? : boolean ): EntityInstance {
        if ( this.array.length === 0 )
            return undefined;

        if ( setCurrent )
            this.currentlySelected = this.array.length - 1;

        return this.array[ this.array.length - 1 ];
    }

    setSelected( value: number | EntityInstance ): EntityInstance {
        if ( value instanceof EntityInstance ) {
            this.currentlySelected = this.array.findIndex( ei => value === ei );
            return this.selected();
        }

        if ( typeof value == "number" ) {
            this.currentlySelected = value;
            return this.selected();
        }

        throw `Value must be number or EntityInstance.  Found ${typeof value}`
    }

    selected(): EntityInstance {
        return this.array[ this.currentlySelected ];
    }

    /**
     * Returns all entity instances, including hidden ones.
     */
    allEntities(): Array<EntityInstance> {
        let ret = [];
        for ( let ei of this.array )
            ret.push( ei );
        if ( this.hiddenEntities ) {
            for ( let ei of this.hiddenEntities )
                ret.push( ei );
        }

        return ret;
    }
}

export class EntityArray<T extends EntityInstance> extends Array<T> {
    delegate: ArrayDelegate<T>;
    parentEi: EntityInstance;

    constructor( entityName: string, oi: ObjectInstance, parentEi: EntityInstance ) {
        const _arr: EntityArray<T> = <any>super();

        // See comment starting ArrayDelegate for why we do this.
        this.delegate = new ArrayDelegate( _arr, entityName, oi, parentEi );

        Object.defineProperty( _arr, 'parentEi', {
            get: () => parentEi,
            enumerable: true,
            configurable: true
        } );

        // Add all the functions to EntityArray.
        _arr.create = function ( initialize: object = {}, options: CreateOptions = DEFAULT_CREATE_OPTIONS ): T {
            return this.delegate.create( initialize, options );
        }
        _arr.include = function ( sourceEi: EntityInstance, options?: IncludeOptions ): T {
            return this.delegate.include( this, sourceEi, options );
        };
        _arr.excludeAll = function ( options? : ExcludeAllOptions ) { this.delegate.excludeAll( options ); };
        _arr.deleteAll = function ( options? : DeleteAllOptions ) { this.delegate.deleteAll( options ); };
        _arr.delete = function ( options?: DeleteOptions ) { this.delegate.delete( options ); };
        _arr.drop = function ( index?: number ) { this.delegate.drop( index ); };
        _arr.exclude = function ( options?: ExcludeOptions ) { this.delegate.exclude( options ); };
        _arr.selected = function () { return this.delegate.selected(); };
        _arr.first = function ( setCurrent? : boolean ) { return this.delegate.first( setCurrent ); };
        _arr.last = function ( setCurrent? : boolean ) { return this.delegate.last( setCurrent ); };
        _arr.setSelected = function ( value: number | EntityInstance ) { return this.delegate.setSelected( value ); };
        _arr.allEntities = function () { return this.delegate.allEntities(); };

        return _arr;
    }

    create: ( initialize?: object, options?: CreateOptions ) => T;
    excludeAll: ( options? : ExcludeAllOptions ) => void;
    deleteAll: ( options? : DeleteAllOptions ) => void;
    delete: ( options?: DeleteOptions ) => void;
    drop: ( index?: number ) => void;
    exclude: ( options?: ExcludeOptions ) => void;
    include: ( sourceEi: EntityInstance, options?: IncludeOptions ) => T;
    selected: () => T;
    first: ( setCurrent? : boolean ) => T;
    last: ( setCurrent? : boolean ) => T;
    setSelected: ( value: number | EntityInstance ) => T;

    /**
     * Returns all entity instances, including hidden ones.
     */
    allEntities: () => Array<T>;
}

// Used to create SAFE_INSTANCE.
var safeInstanceHandler = {
    get: function ( target, key ) {
        if ( key.endsWith( '$$' ) )
            return target;

        return undefined;
    }
};

/**
    This is the Zeidon equivalent to safe navigation.  It allows code to reference
    a lower-level entity without blowing up if a mid-tier element doesn't exist.

    Example: assume that Configuration entity is empty (e.g. undefined).  The following
    will blow up:

            newConfig.Configuration$.ThermometerConfig$.AlarmHigh

    Using safe navigation ('$$' instead of '$') will return 'undefined':

            newConfig.Configuration$$.ThermometerConfig$$.AlarmHigh
*/
export const SAFE_INSTANCE = new Proxy( {}, safeInstanceHandler );

export interface CreateOptions {
    incrementalsSpecified?: boolean;
    position?: CursorPosition;
}

export interface DeleteOptions {
    reposition?: CursorPosition;
    incrementalsSpecified?: boolean;
    index?: number;
}

export interface DeleteAllOptions extends DeleteOptions {
    filter?: ( entityInstance: EntityInstance ) => boolean;
}

export interface ExcludeOptions {
    reposition?: CursorPosition;
    incrementalsSpecified?: boolean;
    index?: number;
}

export interface ExcludeAllOptions extends ExcludeOptions {
    filter?: ( entityInstance: EntityInstance ) => boolean;
}

const DEFAULT_CREATE_OPTIONS = {
    incrementalsSpecified: false,
    position: Position.Last
};

export class Activator {
    preActivateHooks: { ( oi: ObjectInstance, options?: any ): void }[] = [];
    postActivateHooks: { ( oi: ObjectInstance, options?: any ): void }[] = [];

    activateOi<T extends ObjectInstance>( oi: T, options?: any ): Promise<T> {
        throw "activateOi has not been implemented"
    }

    registerPreActivateHook( fn: ( oi: ObjectInstance, options?: any ) => void ) {
        this.preActivateHooks.push( fn );
    }

    registerPostActivateHook( fn: ( oi: ObjectInstance, options?: any ) => void ) {
        this.postActivateHooks.push( fn );
    }

    executePreActivateHooks( oi: ObjectInstance, options?: any ) {
        this.preActivateHooks.forEach( ( fn ) => {
            fn( oi, options );
        } );
    }

    executePostActivateHooks( oi: ObjectInstance, options?: any ) {
        this.postActivateHooks.forEach( ( fn ) => {
            fn( oi, options );
        } );
    }

    // Error handler called if there is an error.
    errorHandler?: ( error: any ) => void;
}

export class Committer {
    preCommitHooks: { ( oi: ObjectInstance, options?: CommitOptions ) : void } [] = [];
    postCommitHooks: { ( oi: ObjectInstance, options?: CommitOptions ): void }[] = [];

    commitOi( oi: ObjectInstance, options?: CommitOptions ): Promise<ObjectInstance> {
        throw "commitOi has not been implemented"
    }

    dropOi( oi: ObjectInstance, options?: CommitOptions ) {
        throw "dropOi has not been implemented"
    }

    registerPreCommitHook( fn: ( oi: ObjectInstance, options?: CommitOptions) => void ) {
        this.preCommitHooks.push( fn );
    }

    registerPostCommitHook( fn: ( oi: ObjectInstance, options?: CommitOptions ) => void ) {
        this.postCommitHooks.push( fn );
    }

    executePreCommitHooks( oi: ObjectInstance, options?: CommitOptions ) {
        this.preCommitHooks.forEach( (fn) => {
            fn( oi, options );
        });
    }

    executePostCommitHooks( oi: ObjectInstance, options?: CommitOptions ) {
        this.postCommitHooks.forEach( ( fn ) => {
            fn( oi, options );
        } );
    }

    // Error handler called if there is an error.
    errorHandler?: ( error: any ) => void;
}

/**
 * Initialization of the Zeidon environment is done by instantiating this
 * object.
 */
export class ZeidonConfiguration {
    constructor( private activator: Activator, private committer: Committer ) {
        // Set the private global variable to this configuration.
        configurationInstance = this;
    }

    getActivator(): Activator { return this.activator; }
    getCommitter(): Committer { return this.committer; }

    registerPreCommitHook( fn: ( oi: ObjectInstance, options?: CommitOptions ) => void ) {
        this.getCommitter().registerPreCommitHook( fn );
    }

    registerPostCommitHook( fn: ( oi: ObjectInstance, options?: CommitOptions ) => void ) {
        this.getCommitter().registerPostCommitHook( fn );
    }

    registerPreActivateHook( fn: ( oi: ObjectInstance, options?: any ) => void ) {
        this.getActivator().registerPreActivateHook( fn );
    }

    registerPostActivateHook( fn: ( oi: ObjectInstance, options?: any ) => void ) {
        this.getActivator().registerPostActivateHook( fn );
    }

}

export interface PaginationOptions {
    currentPage?: number;  // Initial page.  Default = 1.
    pageSize?:    number;  // Initial page size.  Default = 20.
}

export class Pagination {
    currentPage: number;
    totalPages: number = null;
    totalCount: number = null;
    pageSize: number;

    constructor( options: PaginationOptions = {} ) {
        this.currentPage = options.currentPage || 1;
        this.pageSize = options.pageSize || 20;
    }

    incrementPage( rootList: ObjectInstance = undefined ) {
        let currentPage = Math.min( this.currentPage + 1, this.totalPages || 9999 );
        if ( currentPage === this.currentPage )
            return false;

        this.currentPage = currentPage;
        return true;
    }

    decrementPage( rootList: ObjectInstance = undefined ) {
        let currentPage = Math.max( this.currentPage - 1, 1 );
        if ( currentPage === this.currentPage )
            return false;

        this.currentPage = currentPage;
        return true;
    }

    getQueryParam() {
        let pageParam = "?page=" + this.currentPage + "&perPage=" + this.pageSize;
        if ( this.totalCount === undefined )
            pageParam += "&getTotal=true";

        return pageParam;
    }

    setFromResuts( json: any ) {
        if ( json.totalRootCount ) {
            this.totalCount = json.totalRootCount;
            this.totalPages = Math.floor( this.totalCount / 20 ) + 1;
        }
    }

    reset() {
        this.currentPage = 1;
        this.totalCount = null;  // Indicate that we need to retrieve the total count.
    }

    firstPage() {
        return this.currentPage === 1;
    }

    lastPage() {
        return this.currentPage === this.totalPages;
    }
}

export interface ZeidonToJsonOptions {
    childEntities?: string[];  // If a non-empty array, only write childEntities listed in the array.
    meta?: boolean;   // Write OI/entity meta (e.g. incrementals).
    forCommit?: boolean;   // Only write entities needed for update.
}

export interface CommitOptions {
}

/**
 * Defines what makes up a Domain.  Domains are generated by Zeidon from zeidon.xdm.
 */
export interface Domain {
    name: string,
    class: string,
    maxLength?: number,
    defaultContext?: string,
    contexts?: any,
    domainType?: string,
    domainFunctions?: any,
}

export interface DomainFunctions {
    convertExternalValue( value: any, attributeDef: any, context?: any ): any;
    convertToJsType( value: any, attributeDef: any, context?: string ): any;
    getTableEntries?( context?: string ): any;
    getTableValues?( context?: string ): Array<string>;
}

// TODO: Is this needed?  Should we just throw Errors?
let error = function ( message: string ) {
    throw message;
}

export class ZeidonError extends Error {
    constructor( errorMessage: string ) {
        super( errorMessage );

        // Set the prototype explicitly.
        Object.setPrototypeOf( this, ZeidonError.prototype );
    }

}

export class ActivateError extends ZeidonError {
    constructor( errorMessage: string, public lodName: string ) {
        super( errorMessage );

        // Set the prototype explicitly.
        Object.setPrototypeOf( this, ActivateError.prototype );
    }
}

export class ActivateLockError extends ActivateError {
    constructor( public lodName: string ) {
        super( "LOD is locked", lodName );

        // Set the prototype explicitly.
        Object.setPrototypeOf( this, ActivateLockError.prototype );
    }
}

export class AttributeValueError extends ZeidonError {
    attributeDef: any;

    constructor( message: string, attributeDef: any ) {
        super( message + `   Attribute: ${attributeDef.name}` )
        this.attributeDef = attributeDef;
    }
}

export class InvalidAttributeError extends ZeidonError {
    constructor( public attrName: string, entityDef ) {
        // entityDef can be either an EntityDef or just the entity name.  Allowing a name makes it
        // easier to construct tests.
        super( `Attribute '${attrName}' is unknown for entity '${entityDef.name || entityDef}'` );

        // Set the prototype explicitly.
        Object.setPrototypeOf( this, InvalidAttributeError.prototype );
    }
}