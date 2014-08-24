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

import com.quinsoft.zeidon.CursorPosition;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * This object contains the logic for spawning creates and includes.
 * 
 * @author DG
 *
 */
class EntitySpawner
{
    /**
     * This is the root of the spawn, not the root of the OI.
     */
    private final EntityInstanceImpl rootInstance;
    private final View               view;
    
    EntitySpawner( EntityInstanceImpl rootInstance )
    {
        this( rootInstance, null );
    }
    
    EntitySpawner( EntityInstanceImpl rootInstance, View view )
    {
        this.rootInstance = rootInstance;
        this.view = view;
    }
    
    void spawnAccept()
    {
        EntityInstanceImpl ei = rootInstance;
        while ( ei != null && 
                ( ei.getLevel() > rootInstance.getLevel() || ei == rootInstance ) )
        {
            if ( ei.isDeleted() )
                spawnDelete( ei );

            if ( ei.isExcluded() )
                spawnExclude( ei );
            
            // Spawn creates and inserts.
            // If ei is hidden then it's been deleted/excluded so there's no reason to
            // spawn the create/include.
            // If ei has a prev version then the instance was created/included before
            // the createTemporal so we don't need to spawn it here.
            if ( ! ei.isHidden() && ei.getPrevVersion() == null )
            {
                if ( ei.isCreated() )
                    spawnCreate( ei );
                
                if ( ei.isIncluded() )
                    spawnInclude( ei );
            }
            
            if ( ei.isHidden() )
                // If ei is hidden then it's been del/exc and the spawn code handled all
                // the children.
                ei = ei.getNextNonDescendant();
            else
                ei = ei.getNextHier();
        }
    }
    
    /**
     * Spawn the create of the root instance.
     */
    void spawnCreate()
    {
        spawnCreate( rootInstance );
        
        // Now spawn any child entities of rootInstance.
        for ( final EntityInstanceImpl child : rootInstance.getChildrenHier() )
        {
            if ( child.isHidden() )
                continue;
            
            ViewEntity childViewEntity = child.getViewEntity();
            if ( childViewEntity.isDerived() )
                continue;
            
            if ( child.isIncluded() )
                spawnInclude( child );
            else
            if ( child.isCreated() )
                spawnCreate( child );
        }
    }
    
    /**
     * This determines if the candidate for spawning already exists.
     * 
     * @return true if the instances are already spawned/linked.
     */
    private boolean checkForSpawnedInstance( EntityInstanceImpl linked, 
                                             ViewEntity         childViewEntity,
                                             EntityInstanceImpl createdInstance )
    {
        // Find all the children under linked and make sure they aren't already
        // linked with createdInstance.
        for ( EntityInstanceImpl search : linked.getDirectChildren() )
        {
            if ( search.isLinked( createdInstance ) )
                return true;
        }
        
        return false;
    }
    
    private void spawnCreate( EntityInstanceImpl entityInstance )
    {
        // Now that the entity has been created, see if we need to call                                  
        // to spawn the create of this same entity under a                                
        // different parent in the same view object. To do this, we check all                            
        // linked instances of the parent entity type from the same object                               
        // instance and see if that linked instance has a definition level                               
        // non-derived child entity type representing the current entity type.                           
        // If we find a spawn condition we create the spawned instance in the
        // appropriate position and                                   
        // link it to the newly created instance. 
        
        // If there is no parent then nothing to spawn.
        if ( entityInstance.getParent() == null )
            return;

        final ViewEntity viewEntity = entityInstance.getViewEntity();
        
        // We don't spawn derived entities.
        if ( viewEntity.isDerived() )
            return;
        
        final EntityInstanceImpl parentInstance = entityInstance.getParent();
        for ( EntityInstanceImpl linked : parentInstance.getLinkedInstances() )
        {
            // If linked is hidden then it's been deleted so don't bother spawning.
            if ( linked.isHidden() )
                continue;
            
            // If they aren't the same version then skip it.
            if ( ! linked.temporalVersionMatch( parentInstance ) )
                continue;
            
            // Go through the                          
            // child entity types of the linked parent and see if one exists which                        
            // matches the current entity type.                                                           
            ViewEntity linkedViewEntity = linked.getViewEntity();
            for ( ViewEntity searchViewEntity : linkedViewEntity.getChildren() )
            {
                if ( searchViewEntity.isDerived() )
                    continue;
                
                // Same relationship?
                if ( searchViewEntity.getErRelToken() != viewEntity.getErRelToken() )
                    continue; // Nope.
                
                if ( searchViewEntity.isErRelLink() != viewEntity.isErRelLink() )
                    continue;
                
                // We found a spawn candidate.  Make sure they aren't already linked.
                if ( checkForSpawnedInstance( linked, searchViewEntity, entityInstance ) )
                    continue;
                
                // Find the position of the entity we're about to spawn.
                CursorPosition spawnPosition = CursorPosition.NEXT;
                EntityInstanceImpl relativeEntity = null;
                if ( entityInstance.getNextTwin() != null )
                {
                    EntityInstanceImpl nextTwin = entityInstance.getNextTwin();
                    for ( EntityInstanceImpl linkedTwin : nextTwin.getLinkedInstances() )
                    {
                        if ( linkedTwin.getParent() == linked )
                        {
                            relativeEntity = linkedTwin;
                            spawnPosition = CursorPosition.PREV;
                            break;
                        }
                    }
                }
                else
                if ( entityInstance.getPrevTwin() != null )
                {
                    EntityInstanceImpl prevTwin = entityInstance.getPrevTwin();
                    for ( EntityInstanceImpl linkedTwin : prevTwin.getLinkedInstances() )
                    {
                        if ( linkedTwin.getParent() == linked )
                        {
                            relativeEntity = linkedTwin;
                            break;
                        }
                    }
                }
                
                // If we didn't find a relativeEntity, see if there's one under linked because
                // createEntity requires it if one exists.
                if ( relativeEntity == null )
                {
                    for ( EntityInstanceImpl search : linked.getDirectChildren() )
                    {
                        if ( search.getViewEntity() == searchViewEntity )
                        {
                            relativeEntity = search;
                            spawnPosition = CursorPosition.LAST;
                            break;
                        }
                    }
                }
                
                EntityInstanceImpl newInstance = 
                        EntityInstanceImpl.createEntity( linked.getObjectInstance(), 
                                                         linked,
                                                         relativeEntity, 
                                                         searchViewEntity,
                                                         spawnPosition );
                newInstance.linkInternalInstances( entityInstance );
            }
        }
    }

    void spawnInclude()
    {
        spawnInclude( rootInstance );
    }
    
    /**
     * This function checks to see if a created Subobject needs spawning anywhere else in the structure or linked
     * structures, if so, spawning is done.
     */
    private void spawnInclude( EntityInstanceImpl entityInstance )
    {
        ViewEntity viewEntity = entityInstance.getViewEntity();
        
        // Can't spawn a ROOT or a derived relationship.                                                 
        if ( entityInstance.getParent() == null || viewEntity.isDerived() )
            return;

        performSpawnPass( viewEntity, entityInstance, entityInstance.getParent(), true );
        performSpawnPass( viewEntity, entityInstance.getParent(), entityInstance, false );
    }
    
    private void performSpawnPass( ViewEntity         rootViewEntity,
                                   EntityInstanceImpl startSearchInstance,
                                   EntityInstanceImpl startParentSearchInstance,
                                   boolean            matchingRelLinks )
    {
        // Spawning pass 1, for every visible linked instance of the entity                              
        // instance, see if the structure is inverted anywhere and needs                                 
        // to be spawned.            
        for ( EntityInstanceImpl searchInstance : startSearchInstance.getLinkedInstances() )
        {
            // Skip hidden instances.
            if ( searchInstance.isHidden() )
                continue;

            if ( ! searchInstance.temporalVersionMatch( startSearchInstance ) )
                continue;

            ViewEntity searchViewEntity = searchInstance.getViewEntity();
            
            // See if the linked entity instance has a child entity type                               
            // which is a non-derived inversion of the target entity type.                             
            // We only want direct children of searchViewEntity
            for ( ViewEntity workViewEntity : searchViewEntity.getChildren() )
            {
                assert workViewEntity.getParent() == searchViewEntity;

                // Make sure it's the same relationship.
                if ( workViewEntity.getErRelToken() != rootViewEntity.getErRelToken() )
                    continue;
                
                if ( ( workViewEntity.isErRelLink() == rootViewEntity.isErRelLink() ) == matchingRelLinks )
                    continue;
                
                if ( ! workViewEntity.isPersistent() )
                    continue;
                
                // If we get here we have found an instance which has a child entity type for spawning. 
                // Make sure this include has not already been spawned.                                                     
                EntityInstanceImpl searchChild = null;
                for ( EntityInstanceImpl linked : startParentSearchInstance.getLinkedInstances() )
                {
                    if ( linked.getParent() != searchInstance )
                        continue;
                    
                    if ( linked.getViewEntity() != workViewEntity )
                        continue;
                    
                    searchChild = linked;
                    break;
                }
                
                if( searchChild != null )
                    continue;

                // Find the first EI under searchInstance that has a ViewEntity matching workViewEntity.
                // This will be our relative instance.
                EntityInstanceImpl relativeEntity = null;
                for ( EntityInstanceImpl ei : searchInstance.getDirectChildren() )
                {
                    if ( ei.getViewEntity() == workViewEntity )
                    {
                        relativeEntity = ei;
                        break;
                    }
                }
                
                // If searchChild is null then we haven't spawned the inversion.  Do it now by calling
                // includeSubobject recursively.
                EntityInstanceIncluder.includeSubobject( relativeEntity,
                                                         workViewEntity,
                                                         searchInstance,
                                                         rootInstance.getObjectInstance(),
                                                         startParentSearchInstance, 
                                                         CursorPosition.LAST, false );
                
            } // for each workViewEntity...
        } // for each linked instance...
    }

    void spawnDelete( EntityInstanceImpl entityInstance )
    {
        for ( EntityInstanceImpl linked : entityInstance.getLinkedInstances() )
        {
            if ( linked.isHidden() )
                continue;

            // If the linked instance is a different version then don't delete it.
            if ( ! rootInstance.temporalVersionMatch( linked ) )
                continue;
                
            // Spawn the delete but set first arg to false so we don't recursively
            // spawn the delete of the root because we're doing it as part of this loop.
            linked.deleteEntity( false, view );
        }
    }

    void spawnExclude( )
    {
        spawnExclude( rootInstance );
    }
        
    private void spawnExclude( EntityInstanceImpl entityInstance )
    {
        ViewEntity rootViewEntity = entityInstance.getViewEntity();

        // Find all linked instances with the same parent relationship and mark them
        // as excluded.
        for ( EntityInstanceImpl linked : entityInstance.getLinkedInstances() )
        {
            // If the linked instance is a different version then don't exclude it.
            if ( ! entityInstance.temporalVersionMatch( linked ) )
                continue;

            ViewEntity linkedViewEntity = linked.getViewEntity();
            
            if ( linkedViewEntity.getErRelToken() == rootViewEntity.getErRelToken() )
            {
                // The linked instance represents the same relationship ...
                // see if its parent is a linked instance to the current parent.
                EntityInstanceImpl linkedParent = linked.getParent();
                for ( EntityInstanceImpl ei : linkedParent.getLinkedInstances() )
                {
                    if ( ei == entityInstance.getParent() )
                    {
                        linked.excludeEntity( false );
                        break;
                    }
                }
            }
            
            // See if the structure is inverted for a linked instance.
            for ( ViewEntity childViewEntity : linkedViewEntity.getChildren() )
            {
                if ( childViewEntity.getErRelToken() == rootViewEntity.getErRelToken() )
                {
                    // We've found an inverted structure in the linked entity
                    // instance, now go and mark the child entity instance
                    // which is linked to the source entity instances parent
                    // as excluded.
                    for ( EntityInstanceImpl linkedParent : entityInstance.getParent().getLinkedInstances() )
                    {
                        if ( linkedParent.getParent() == linked )
                            linkedParent.excludeEntity( false );
                    }
                }
            }
        }
    }
}
