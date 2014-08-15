/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * Methods that are common to multiple commit implementations.
 *
 * @author dgc
 *
 */
class CommitHelper
{
    /**
     * Called after a commit. Reset the entity/attribute update flags to false,
     * remove deleted entities from the OI.
     * @param oi
     */
    static void cleanupOI(ObjectInstance oi)
    {
        // We can't use an iterator because we are potentially removing entities.
        for ( EntityInstanceImpl ei = oi.getRootEntityInstance();
              ei != null;
              ei = ei.getNextHier() )
        {
            for ( EntityInstanceImpl linked : ei.getAllLinkedInstances() )
            {
                if ( linked.dbhDeleted || linked.dbhExcluded )
                {
                    linked.dropEntity();  // Remove the ei from the chain.
                    continue;
                }

                if ( linked.dbhCreated )
                    linked.setCreated( false );
                if ( linked.dbhIncluded )
                    linked.setIncluded( false );
                if ( linked.dbhUpdated )
                    linked.setUpdated( false );

                ViewEntity viewEntity = linked.getViewEntity();

                // For created/included entities we need to indicate that they don't have
                // any children that need to be lazy-loaded.
                if ( linked.dbhCreated || linked.dbhIncluded )
                {
                    linked.flagAllChildrenAsLazyLoaded();
                }

                for ( ViewAttribute viewAttrib : viewEntity.getAttributes() )
                {
                    if ( viewAttrib.isPersistent() )
                        linked.getInternalAttribute( viewAttrib ).setUpdated( false );
                }
            }
        }
    }
}
