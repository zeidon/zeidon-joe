/**
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quinsoft.zeidon.CommitFlags;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.OiSourceSelector;
import com.quinsoft.zeidon.SubobjectValidationException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.utils.Timer;

/**
 * @author DG
 *
 */
class CommitMultiplOIs
{
    @SuppressWarnings("unused")
    private final EnumSet<CommitFlags> control;
    private       List<ViewImpl>       viewList;
    private final Collection<View>     originalViewList;
    private final TaskImpl             task;
    private final CommitOptions        options;

    /**
     * This will hold the pairings of all the includes that
     * have explicit authority to do the include for all the OIs.
     */
    private Map<Object,Object> includableRelationships;
    
    /**
     * This will hold the pairings of all the excludes that
     * have explicit authority to do the exclude for all the OIs.
     */
    private Map<Object,Object> excludableRelationships;
    
    /**
     * This is used to determine where an OI is activated from.
     * Some day this may be provided by the OE options.
     */
    private final OiSourceSelector selector = new DefaultOiSourceSelector();

    private interface HasPermission
    {
        boolean hasPermission( EntityInstanceImpl ei );
    }

    private static final HasPermission hasDeletePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getEntityDef().isDelete();
        }};

    private static final HasPermission hasUpdatePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getEntityDef().isUpdate();
        }};

    private static final HasPermission hasCreatePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getEntityDef().isCreate();
        }};

    private static final HasPermission hasIncludePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getEntityDef().isInclude();
        }};

    private static final HasPermission hasExcludePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getEntityDef().isExclude();
        }};

    CommitMultiplOIs(TaskImpl task, CommitOptions options, Collection<View> views)
    {
        this.task = task;
        this.options = options;
        originalViewList = views;
        this.control = options.getControl();
    }

    private TaskImpl getTask()
    {
        return task;
    }

    private void validateCommit()
    {
        // Build a list of the non-empty views.
        viewList = new ArrayList<ViewImpl>();
        for ( View v : originalViewList )
        {
            if ( v.isReadOnly() )
                throw new ZeidonException("Attempting to commit read-only view.  View = %s", v.getLodDef().getName() );

            ViewImpl view = ((InternalView) v).getViewImpl();
            ObjectInstance oi = view.getObjectInstance();
            if ( oi.isVersioned() )
                throw new ZeidonException("Attempting to commit a view with outstanding versioned instances.  " +
                                          "View = %s", v );

            viewList.add( view );
        }

        // Run the commit constraints before we do any validation because the constraints might
        // change the OIs.
        executeCommitConstraints();

        // Remove any empty OIs.
        Set<ObjectInstance> oiSet = new HashSet<ObjectInstance>();
        for ( Iterator<ViewImpl> iter = viewList.iterator(); iter.hasNext(); )
        {
            ViewImpl view = iter.next();
            ObjectInstance oi = view.getObjectInstance();

            // If this OI has no instances, remove it.
            if ( oi.getRootEntityInstance() == null )
            {
                iter.remove();
                continue;
            }

            oiSet.add( oi );
        }

        // Call validateOi on all the views.
        List<ZeidonException> validationExceptions = new ArrayList<ZeidonException>();
        for ( ViewImpl view : viewList )
        {
            Collection<ZeidonException> list = view.validateOi();
            if ( list != null )
                validationExceptions.addAll( list );
        }

        if ( validationExceptions.size() > 0 )
            throw new SubobjectValidationException( validationExceptions );

        validatePermissions( oiSet );
    }

    /**
     * See if any linked EI in one of the other OIs has the correct permission.
     *
     * @param ei
     * @param oiSet
     * @param permissionChecker
     * @return
     */
    private boolean validatePermissionForEi( EntityInstanceImpl ei, 
                                             Set<ObjectInstance> oiSet, 
                                             HasPermission permissionChecker,
                                             Map<Object, Object> permissionMap )
    {
        // Sanity check to make sure dbhLoaded is always off by the time we commit.
        assert ei.dbhLoaded == false : ei.toString() + " still has dbhLoaded flag on";

        // Does the EI have permission?
        if ( permissionChecker.hasPermission( ei ) )
            return true;
        
        EntityInstanceImpl parent = ei.getParent();
        if ( permissionMap != null && parent != null )
        {
            // permissionMap has a pairing of all includes/excludes that have explicit
            // permission to do the include/exclude.  If we get here then ei doesn't
            // have explicit permission to do the include/exclude.  We'll use the
            // permission map to see if the same relationship exists in linked EIs
            // that does have permission.
            
            // Check relationship in one direction.
            if ( permissionMap.get( ei.getUniqueLinkedObject() ) == parent.getUniqueLinkedObject() )
                return true;

            // Now check the other direction.
            if ( permissionMap.get( parent.getUniqueLinkedObject() ) == ei.getUniqueLinkedObject() )
                return true;

            // Permission map is defined for includes/excludes.  If we don't have a match
            // then we have a problem.
            return false;
        }
        
        // If we get here then the EI doesn't have permission to perform its operation.
        // We need to go through all the entities linked with ei to see if *they* have
        // permission.  We'll start by creating a list of valid linked EIs.
        Set<EntityInstanceImpl> linkedEis = new HashSet<>();
        for ( EntityInstanceImpl linked : ei.getLinkedInstances() )
        {
            EntityDef linkedEntityDef = linked.getEntityDef();
            assert linkedEntityDef.getErEntityToken() == ei.getEntityDef().getErEntityToken() : "Mismatching ER tokens";
            
            if ( linkedEntityDef.isDerivedPath() )
                continue;

            // We only want EIs that are part of the OIs that are being committed.
            if ( ! oiSet.contains( linked.getObjectInstance() ) )
                continue;

            linkedEis.add( linked );
        }
        
        if ( linkedEis.size() == 0 )
            return false;
        
        // First check the simple case.  Do any of the linked EIs have the same parent
        // relationship with permission?
        for ( EntityInstanceImpl linked : linkedEis )
        {
            if ( permissionChecker.hasPermission( linked ) ) // Does the linked EI have the required authority?
            {
                // We found a linked instance that has the correct authority.
                return true;
            }
        }

        return false;
    }

    /**
     * This creates two maps, one for includes and one for excludes.  Each map will contain
     * all the includes/excludes that have explicit permission (via EntityDef flags) to do
     * the include/exclude.
     * 
     * The keys and values of the maps are taken from ei.getUniqueLinkedObject().  This allows
     * us to do a quick verification for authority for all linked EIs.
     */
    private void accumulatePermissionMaps()
    {
        includableRelationships = new HashMap<>();
        excludableRelationships = new HashMap<>();
        
        for ( ViewImpl view : viewList )
        {
            ObjectInstance oi = view.getObjectInstance();
            for ( EntityInstanceImpl ei : oi.getEntities( true ) )
            {
                EntityInstanceImpl parent = ei.getParent();
                
                if ( parent == null )
                    continue;
                
                EntityDef entityDef = ei.getEntityDef();
                if ( entityDef.isDerivedPath() )
                    continue;

                if ( entityDef.getDataRecord() == null )
                    continue;

                if ( ei.isIncluded() && entityDef.isInclude() )
                {
                    includableRelationships.put( ei.getUniqueLinkedObject(), parent.getUniqueLinkedObject() );
                }

                if ( ei.isExcluded() && entityDef.isExclude() )
                {
                    excludableRelationships.put( ei.getUniqueLinkedObject(), parent.getUniqueLinkedObject() );
                }
            } // each entityInstance
        } // each view
        
    }
    
    /**
     * For each entity instance that has been changed in all the OIs, make sure that
     * one of the OI's has the permission to create/delete/update/etc.
     *
     * @param oiSet
     */
    private void validatePermissions( Set<ObjectInstance> oiSet )
    {
        boolean missingPermission = false;
        accumulatePermissionMaps();
        
        for ( ViewImpl view : viewList )
        {
            ObjectInstance oi = view.getObjectInstance();
            for ( EntityInstanceImpl ei : oi.getEntities( true ) )
            {
                EntityDef entityDef = ei.getEntityDef();

                // Unless we learn otherwise, assume this entity needs to be committed.
                ei.dbhNeedsCommit = false;

                if ( entityDef.isDerivedPath() )
                    continue;

                if ( entityDef.getDataRecord() == null )
                    continue;

                if ( ei.isCreated() && ! ei.isDeleted() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! entityDef.isCreate() && ! validatePermissionForEi( ei, oiSet, hasCreatePermission, null ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have create authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isDeleted() && ! ei.isCreated() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! entityDef.isDelete() && ! validatePermissionForEi( ei, oiSet, hasDeletePermission, null ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have delete authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isUpdated() && ! ei.isDeleted() && ! ei.isCreated() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! entityDef.isExclude() && ! validatePermissionForEi( ei, oiSet, hasUpdatePermission, null ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have update authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isIncluded() && ! ei.isExcluded() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! entityDef.isInclude() && ! validatePermissionForEi( ei, oiSet, hasIncludePermission, includableRelationships ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have include authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isExcluded() && ! ei.isIncluded() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! entityDef.isExclude() && ! validatePermissionForEi( ei, oiSet, hasExcludePermission, excludableRelationships ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have exclude authority:" );
                        ei.logEntity();
                    }
                }
            } // each entityInstance
        } // each view

        if ( missingPermission )
            throw new ZeidonException( "Commit has an entity instance that doesn't have permission.  See log for more." );
    }

    private void executeCommitConstraints()
    {
        // Call commit constraints.
        for ( ViewImpl view : viewList )
            view.getLodDef().executeCommitConstraint( view );
    }

    int commit()
    {
        validateCommit();

        // If there aren't any valid views then we have nothing to commit.
        if ( viewList.size() ==0 )
        	return 0;

        Committer committer = selector.getCommitter( getTask(), viewList, options );
        committer.init( task, viewList, options );

        final Timer timer = new Timer();
        ObjectEngineEventListener listener = task.getObjectEngine().getOeEventListener();
        try
        {
            committer.commit();
            listener.objectInstanceCommitted( originalViewList, timer.getMilliTime(), null );
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e );
            listener.objectInstanceCommitted( originalViewList, timer.getMilliTime(), e );
            throw ze;
        }
        finally
        {
            if ( timer.getMilliTime() > 2000 )
                task.log().info( "==> Commit took %s seconds", timer );
            else
                task.log().info( "==> Commit took %d milliseconds", timer.getMilliTime() );
        }

        return 0;
    }
}
