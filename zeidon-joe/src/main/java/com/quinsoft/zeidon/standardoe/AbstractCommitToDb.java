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

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.EntityIterator;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.dbhandler.DbHandler;
import com.quinsoft.zeidon.dbhandler.JdbcHandlerUtils;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Implements code common to CommitToSqlWithDbGeneratedKeys and CommitToDbUsingGenkeyHandler.
 *
 */
abstract class AbstractCommitToDb implements Committer
{
    protected List<ViewImpl>       viewList;
    protected CommitOptions        options;
    protected DbHandler            dbHandler;
    protected JdbcHandlerUtils     helper;
    protected Task                 task;

    abstract protected void commitExcludes(View view, ObjectInstance oi, EntityInstanceImpl lastEntityInstance);
    abstract protected void commitDeletes(ViewImpl view, ObjectInstance oi, EntityInstanceImpl lastEntityInstance);
    abstract protected void commitCreates(ViewImpl view, ObjectInstance oi);
    abstract protected void commitIncludes(ViewImpl view, ObjectInstance oi);
    abstract protected void commitUpdates(View view, ObjectInstance oi);

    @Override
    public void init( Task task, List<? extends View> list, CommitOptions options )
    {
        this.options = options;
        this.task = task;
        this.viewList = new ArrayList<ViewImpl>();
        for ( View v : list )
            viewList.add( ((InternalView) v).getViewImpl() );

        // Grab the first view to use as a task qualifier and for getting a dbhandler.
        // TODO: We're using the first view in the list but maybe we should do something different?
        //       We'd like to some day support commits across multiple DBs.
        ViewImpl firstView = viewList.get( 0 );
        helper = new JdbcHandlerUtils( this.options, firstView.getLodDef().getDatabase() );
        dbHandler = helper.getDbHandler();
    }

    protected void commitView(ViewImpl view)
    {
        // Set up view to allow the DBhandler to access hidden entities.
        boolean oldValue = view.setAllowHiddenEntities( true );

        ObjectInstance oi = view.getObjectInstance();

        // TODO: implement optimistic locking check.

        EntityInstanceImpl lastEntityInstance = oi.getLastEntityInstance();

        commitExcludes( view, oi, lastEntityInstance );
        commitDeletes( view, oi, lastEntityInstance );
        commitCreates( view, oi );
        commitIncludes( view, oi );
        commitUpdates( view, oi );

        view.setAllowHiddenEntities( oldValue );
    }

    /**
     * Called after a commit. Reset the entity/attribute update flags to false,
     * remove deleted entities from the OI.
     * @param oi
     */
    protected void cleanupOI(ObjectInstance oi)
    {
        // We can't use an iterator because we are potentially removing entities.
        for ( EntityInstanceImpl ei = oi.getRootEntityInstance(); ei != null; ei = ei.getNextHier() )
        {
            Collection<EntityInstanceImpl> linkedInstances = ei.getAllLinkedInstances();
            for ( EntityInstanceImpl linked : linkedInstances )
            {
                if ( linked.dbhDeleted || linked.dbhExcluded )
                {
                    // Turn off the flags so if we come to these again (via other linked
                    // instances) we don't do it again.
                    EntityIterator<EntityInstanceImpl> linkedChildren = linked.getChildrenHier( true, false );
                    for ( EntityInstanceImpl linkedChild : linkedChildren )
                        linkedChild.dbhDeleted = linkedChild.dbhExcluded = false;

                    // We're going to remove 'linked' and its children from the OI.  If
                    // 'linked' is also ei then reset ei so we don't bother trying to
                    // drop any of its children.
                    if ( linked == ei )
                        ei = ei.getLastChildHier();

                    linked.dropEntity(); // Remove the ei from the chain.
                    continue;
                }

                if ( linked.dbhCreated )
                    linked.setCreated( false );
                if ( linked.dbhIncluded )
                    linked.setIncluded( false );
                if ( linked.dbhUpdated || linked.dbhCreated )
                    linked.setUpdated( false );

                EntityDef entityDef = linked.getEntityDef();

                // For created/included entities we need to indicate that they don't have
                // any children that need to be lazy-loaded.
                if ( linked.dbhCreated || linked.dbhIncluded )
                {
                    linked.flagAllChildrenAsLazyLoaded();
                }

                for ( AttributeDef AttributeDef : entityDef.getAttributes() )
                {
                    if ( AttributeDef.isPersistent() )
                        linked.getInternalAttribute( AttributeDef ).setUpdated( false );
                }
            }
        }

        oi.setUpdated( false );
        oi.setUpdatedFile( false );
    }
}
