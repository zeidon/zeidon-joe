/**
 *
 */
package com.quinsoft.zeidon.dbhandler;

import java.util.EnumSet;
import java.util.List;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * The interface between Zeidon OE and the databases.
 *
 * @author DG
 *
 */
public interface DbHandler
{
    static final int LOAD_DONE        = 0;
    static final int LOAD_NO_ENTITIES = 1;

    /**
     * This is used in the qualification object to indicate that the qualification
     * is for the root entity.
     */
    static final String ROOT_ENTITY = "*root*";

    void beginTransaction();
    void endTransaction( boolean commit );

    int beginActivate( View view, View qual, EnumSet<ActivateFlags> control );

    /**
     * Load all viewEntities under the parent.
     * @param view View being activated.
     * @param viewEntity
     * @return LOAD_DONE (0)        - Entities loaded OK
     *         LOAD_NO_ENTITIES (1) - No entities loaded.
     */
    int loadEntity( View view, ViewEntity viewEntity );

    int insertEntity( View view, List<? extends EntityInstance> entityInstances );
    int insertRelationship( View view, EntityInstance entityInstance );
    int deleteEntity( View view, EntityInstance entityInstance );
    int deleteAllChildren( View view, EntityInstance parent );
    int deleteRelationship( View view, EntityInstance entityInstance );
    int updateEntity( View view, EntityInstance entityInstance );

    /**
     * Returns any new keys created by the DB as part of an insert.  Ignored by
     * dbhandlers that use genkey handlers.
     *
     * @return
     */
    List<Object> getKeysGeneratedByDb();

    /**
     * If 'set' is true then the DB will autogenerate the genkeys.
     * @param set
     */
    void setDbGenerateKeys( boolean set );
}
