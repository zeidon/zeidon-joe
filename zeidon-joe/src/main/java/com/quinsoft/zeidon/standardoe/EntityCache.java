/**
 * 
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * Keeps track of information for an Entity Cache.  An entity cache is used
 * at activation time to load entities from the cache instead of from
 * the DB.
 *
 */
class EntityCache
{
    private final LodDef  lodDef;
    private final View    qualOi;
    private final String  cacheName;
    private final Task    task;
    private final Integer erEntityToken;

    EntityCache(LodDef lodDef, View qualOi, String cacheName, Task task)
    {
        super();
        this.lodDef = lodDef;
        this.qualOi = qualOi;
        this.cacheName = cacheName;
        this.task = task;
        erEntityToken = lodDef.getRoot().getErEntityToken();
    }

    Integer getErEntityToken()
    {
        return erEntityToken;
    }
    

}
