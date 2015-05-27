/**
 *
 */
package com.quinsoft.zeidon;

import java.util.HashSet;
import java.util.Set;

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
    private final LodDef  lodDef;
    private final View    qualOi;
    private final String  cacheName;
    private final Task    task;
    private final Integer erEntityToken;
    private final Set<Integer> relationships = new HashSet<>();

    public EntityCache( View cacheView, View qualOi, String cacheName )
    {
        super();
        this.lodDef = cacheView.getLodDef();
        this.qualOi = qualOi;
        this.cacheName = cacheName;
        this.task = cacheView.getTask();
        erEntityToken = lodDef.getRoot().getErEntityToken();

        for ( EntityDef ed : lodDef.getEntityDefs() )
        {
            if ( ed.getParent() == null )
                continue;  // Skip the root.

            relationships.add( ed.getErRelToken() );
        }
    }

    public Integer getErEntityToken()
    {
        return erEntityToken;
    }

    /**
     * Returns the view for the cached OI.  It does this by looking for the view by
     * name in the task.  If it is not found it is assumed that it needs to be reloaded.
     *
     * @return entity cache view.
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
}
