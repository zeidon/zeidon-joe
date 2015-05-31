/**
 *
 */
package com.quinsoft.zeidon;

import java.util.HashSet;
import java.util.Set;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;

/**
 * Keeps track of information for an Entity Cache.  An entity cache is used
 * at activation time to load entities from the cache instead of from
 * the DB.
 *
 */
public class EntityCache
{
    private final LodDef    lodDef;
    private final EntityDef root;
    private final AttributeDef key;
    private final View      qualOi;
    private final String    cacheName;
    private final Task      task;
    private final Integer   erEntityToken;
    private final Set<Integer> relationships = new HashSet<>();

    public EntityCache( View cacheView, View qualOi, String cacheName )
    {
        super();
        this.lodDef = cacheView.getLodDef();
        root = lodDef.getRoot();
        key = root.getKeys().get( 0 );
        this.qualOi = qualOi;
        this.cacheName = cacheName;
        this.task = cacheView.getTask();
        erEntityToken = lodDef.getRoot().getErEntityToken();

        for ( EntityDef ed : lodDef.getEntityDefs() )
        {
            if ( ed.getParent() == null )
                continue;  // Skip the root.

            if ( ed.isDerivedPath() )
                continue;

            relationships.add( ed.getErRelToken() );
        }

        // For now we only support entities with a single key.
        if ( root.getKeys().size() != 1 )
            throw new ZeidonException( "Root entity in an EntityCach LOD may only have a single key." );

        if ( cacheView.cursor( root ).isIncomplete() )
            throw new ZeidonException( "EntityCache OI was activated with RESTRICTING or ROOT-ONLY.  EntityCaches must be complete." );

        // Make sure the root entity has hashkey.
        verifyKeyHash();
    }

    private void verifyKeyHash()
    {
        if ( root.hasAttributeHaskKeys() )
        {
            for ( AttributeDef attr : root.getHashKeyAttributes() )
            {
                if ( attr.isKey() )
                    return;  // We found a hashkey that is the root key.  We're good to go.
            }
        }

        throw new ZeidonException( "When using a LOD as an EntityCache the root entity must have an attribute hashkey "
                                 + "specfied on the key ");
    }

    public Integer getErEntityToken()
    {
        return erEntityToken;
    }

    /**
     * Returns the view for the cached OI.  It does this by looking for the view by
     * name in the task.  If it is not found it is assumed that it needs to be reloaded.
     *
     * @return a COPY of the entity cache view.
     */
    public synchronized View getEntityCacheView()
    {
        View view = task.getViewByName( cacheName );
        if ( view == null )
        {
            // The cached view must have been dropped.  Attempt to
            // reload it.
            QualificationBuilder qual = new QualificationBuilder( task, qualOi );
            view = qual.setApplication( lodDef.getApplication() )
                       .setLodDef( lodDef )
                       .cachedAs( cacheName, task )
                       .activate();
        }

        return view.newView();
    }

    /**
     * @return a set of the ER Rel tokens for all the child entities of this LOD.
     */
    public Set<Integer> getRelationships()
    {
        return relationships;
    }

    /**
     * Sets the cursor to the root entity with key = keyValue.  Throws an exception
     * if the entity isn't set.
     *
     * @param cacheView
     * @param keyValue
     */
    public void setCursor( View cacheView, Object keyValue )
    {
        if ( ! cacheView.cursor( root ).setFirst( key, keyValue ).isSet() )
            throw new ZeidonException( "EntityCache OI does not have an entity with key = %s", keyValue )
                            .appendMessage( "Cache view = %s", cacheView.toString() );
    }

    public EntityDef getRoot()
    {
        return root;
    }
}
