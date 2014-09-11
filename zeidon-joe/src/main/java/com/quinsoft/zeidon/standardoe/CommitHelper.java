/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

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

                EntityDef entityDef = linked.getEntityDef();

                // For created/included entities we need to indicate that they don't have
                // any children that need to be lazy-loaded.
                if ( linked.dbhCreated || linked.dbhIncluded )
                {
                    linked.flagAllChildrenAsLazyLoaded();
                }

                for ( ViewAttribute viewAttrib : entityDef.getAttributes() )
                {
                    if ( viewAttrib.isPersistent() )
                        linked.getInternalAttribute( viewAttrib ).setUpdated( false );
                }
            }
        }
    }
}
