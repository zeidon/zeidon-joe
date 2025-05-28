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

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.CommitFlags;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.OiSourceSelector;
import com.quinsoft.zeidon.SubobjectValidationException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataRecord;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.objectdefinition.RelRecord;
import com.quinsoft.zeidon.utils.Timer;

/**
 * @author DG
 *
 */
class CommitMultipleOIs
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
    private Set<String> includableRelationships;

    /**
     * This will hold the pairings of all the excludes that
     * have explicit authority to do the exclude for all the OIs.
     */
    private Set<String> excludableRelationships;

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

    CommitMultipleOIs(TaskImpl task, CommitOptions options, Collection<View> views)
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

   /**
    *
    */
    private void setAutoSeq( final ObjectInstance oi )
    {
        // Set any autoseq attributes and find the last EI in the OI.
        for ( final EntityInstanceImpl ei : oi.getEntities( true ) )
        {
            final EntityDef entityDef = ei.getEntityDef();
            if ( entityDef.isDerivedPath() )
                continue;
            if ( ! entityDef.isUpdate() )
                continue;

            final AttributeDef autoSeq = entityDef.getAutoSeq();
            if ( autoSeq == null )
                continue;

            if ( ei.getPrevTwin() == null && // Must be first twin
                 ei.getNextTwin() != null )  // Don't bother if only one twin.
            {
                DataRecord dataRecord = entityDef.getDataRecord();
                RelRecord relRecord = dataRecord.getRelRecord();

                // Setting the autoseq for the child of a m-to-m relationship results in an update to
                // the m-to-m table, not the entity table.  Since we aren't really updating the record
                // we won't set the incremental flag.
                boolean setIncremental = (relRecord == null || relRecord.getRelationshipType() != RelRecord.MANY_TO_MANY);

                int seq = 1;
                for ( EntityInstanceImpl twin = ei; twin != null; twin = twin.getNextTwin() )
                {
                    if ( twin.isHidden() )
                        continue;

                    if ( twin.getAttribute( autoSeq ).setInternalValue( seq++, setIncremental ) )
                        twin.dbhSeqUpdated = true;

                    // Turn off the bDBHUpdated flag (if it's on) so that we
                    // make sure the entity is updated. If the entity instance
                    // is linked with someone else it's possible that the
                    // entity was updated through the other link.
                    twin.dbhUpdated = false;
                }
            }
        }
    }

    private void validateCommit()
    {
        // Build a list of the non-empty views.
        viewList = new ArrayList<ViewImpl>();
        for ( View v : originalViewList )
        {
            if ( v.isReadOnly() )
                throw new ZeidonException("Attempting to commit read-only view.  View = %s", v.getLodDef().getName() );

            LodDef lodDef = v.getLodDef();
            if ( ! lodDef.hasPhysicalMappings() )
                throw new ZeidonException("Attempting to commit OI with no physical mappings.  LOD = %s", lodDef.getName() );

            ViewImpl view = ((InternalView) v).getViewImpl();
            ObjectInstance oi = view.getObjectInstance();
            if ( oi.isVersioned() )
                throw new ZeidonException("Attempting to commit a view with outstanding versioned instances.  " +
                                          "View = %s", v );

            setAutoSeq( view.getObjectInstance() );
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
    private EntityInstanceImpl validatePermissionForEi( EntityInstanceImpl ei,
                                                        Set<ObjectInstance> oiSet,
                                                        HasPermission permissionChecker,
                                                        Set<String> permissionMap )
    {
        // Sanity check to make sure dbhLoaded is always off by the time we commit.
        assert ei.dbhLoaded == false : ei.toString() + " still has dbhLoaded flag on";

        // Does the EI have permission?
        if ( permissionChecker.hasPermission( ei ) )
            return ei;

        EntityInstanceImpl parent = ei.getParent();
        EntityDef entityDef = ei.getEntityDef();
        if ( permissionMap != null && parent != null )
        {
            // permissionMap has a pairing of all includes/excludes that have explicit
            // permission to do the include/exclude.  If we get here then ei doesn't
            // have explicit permission to do the include/exclude.  We'll use the
            // permission map to see if the same relationship exists in linked EIs
            // that does have permission.

            // Check relationship in one direction.
            if ( permissionMap.contains( entityDef.getErRelToken() ) )
                return parent;

            // Permission map is defined for includes/excludes.  If we don't have a match
            // then we have a problem.
            return null;
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
            return null;

        // First check the simple case.  Do any of the linked EIs have the same parent
        // relationship with permission?
        for ( EntityInstanceImpl linked : linkedEis )
        {
            if ( permissionChecker.hasPermission( linked ) ) // Does the linked EI have the required authority?
            {
                // We found a linked instance that has the correct authority.
                return linked;
            }
        }

        return null;
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
        includableRelationships = new HashSet<>();
        excludableRelationships = new HashSet<>();

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
                    includableRelationships.add( entityDef.getErRelToken() );  // Rel is includable.

                if ( ei.isExcluded() && entityDef.isExclude() )
                    excludableRelationships.add( entityDef.getErRelToken() );  // Rel is excludable.
            } // each entityInstance
        } // each view

    }

    private boolean assertChildUpdatedSetForParents( EntityInstanceImpl ei )
    {
        for ( EntityInstanceImpl parent = ei.getParent();
              parent != null && ! parent.isChildUpdated();
              parent = parent.getParent() )
        {
            if ( parent.isChildUpdated() )
                return false;
        }

        return true;
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
        String errorMessage = "";
        accumulatePermissionMaps();

        for ( ViewImpl view : viewList )
        {
            ObjectInstance oi = view.getObjectInstance();

            // Get the activate options to see if we are sharing a transaction.
            // activateOptions will be null on a new OI.
            ActivateOptions activateOptions = oi.getActivateOptions();
            if ( activateOptions != null && activateOptions.isSingleTransaction() )
                options.setSingleTransaction( true );

            for ( EntityInstanceImpl ei = oi.getRootEntityInstance();
                  ei != null;
                  ei = ei.getNextHier() )
            {
                EntityDef entityDef = ei.getEntityDef();

                // Unless we learn otherwise, assume this does not need to be committed.
                ei.dbhNeedsCommit = false;
                ei.dbhUpdateForeignKeysOnly = false;

                if ( entityDef.isDerivedPath() )
                    continue;

                if ( entityDef.getDataRecord() == null )
                    continue;

                if ( ei.isIncluded() && ! ei.isExcluded() )
                {
                    ei.dbhNeedsCommit = true;
                    assert assertChildUpdatedSetForParents( ei );
                    if ( ! entityDef.isInclude() && validatePermissionForEi( ei, oiSet, hasIncludePermission, includableRelationships ) == null )
                    {
                        missingPermission = true;
                        errorMessage = String.format("Entity instance in view: %s  entity: %s  does not have include authority:", view, entityDef.getName());
                        getTask().log().error( "Entity instance in view: %s  entity: %s  does not have include authority:", view, entityDef.getName() );
                        ei.logEntity();
                    }
                }

                if ( ei.isExcluded() && ! ei.isIncluded() )
                {
                    ei.dbhNeedsCommit = true;
                    assert assertChildUpdatedSetForParents( ei );
                    if ( ! entityDef.isExclude() && validatePermissionForEi( ei, oiSet, hasExcludePermission, excludableRelationships ) == null )
                    {
                        missingPermission = true;
                        errorMessage = String.format("Entity instance in view: %s  entity: %s  does not have exclude authority:", view, entityDef.getName() );
                        getTask().log().error( "Entity instance in view: %s  entity: %s  does not have exclude authority:", view, entityDef.getName() );
                        ei.logEntity();
                    }
                }

                if ( ei.isCreated() && ! ei.isDeleted() )
                {
                    ei.dbhNeedsCommit = true;
                    assert assertChildUpdatedSetForParents( ei );
                    if ( ! entityDef.isCreate() && validatePermissionForEi( ei, oiSet, hasCreatePermission, null ) == null )
                    {
                        missingPermission = true;
                        errorMessage = String.format("Entity instance in view: %s  entity: %s  does not have create authority:", view, entityDef.getName() );
                        getTask().log().error( "Entity instance in view: %s  entity: %s  does not have create authority:", view, entityDef.getName() );
                        ei.logEntity();
                    }
                }

                if ( ei.isDeleted() && ! ei.isCreated() )
                {
                    ei.dbhNeedsCommit = true;
                    assert assertChildUpdatedSetForParents( ei );
                    if ( ! entityDef.isDelete() && validatePermissionForEi( ei, oiSet, hasDeletePermission, null ) == null )
                    {
                        missingPermission = true;
                        errorMessage = String.format("Entity instance in view: %s  entity: %s  does not have delete authority:", view, entityDef.getName() );
                        getTask().log().error( "Entity instance in view: %s  entity: %s  does not have delete authority:", view, entityDef.getName() );
                        ei.logEntity();
                    }
                }

                if ( ei.isUpdated() && ! ei.isDeleted() && ! ei.isCreated() )
                {
                    if ( entityDef.isUpdate() )
                    {
                        ei.dbhNeedsCommit = true;
                        assert assertChildUpdatedSetForParents( ei );

                        // Make sure we can update the attributes.
                        validateAttributePermission( oiSet, ei );
                    }
                    else
                    {
                        // We don't have update authority.  Does a linked instance have update authority?
                        EntityInstanceImpl updateableEi = validatePermissionForEi( ei, oiSet, hasUpdatePermission, null );
                        if (  updateableEi == null )
                        {
                            // No linked EI has update authority.  One last check: is the ei the root of a read-only
                            // subobject?  If so then we'll perform include/exclude but we'll ignore the update.
                            if ( entityDef.isReadOnlySubobjectRoot() )
                            {
                                // Indicate that we only want to update FKs because they are part of the include/exclude
                                // but we don't have authority to update other values.
                                ei.dbhUpdateForeignKeysOnly = true;
                            }
                            else
                            {
                                missingPermission = true;
                                errorMessage = String.format("Entity instance in view: %s  entity: %s  does not have update authority and at least one of it's sub entities is marked 'update':", view, entityDef.getName());
                                getTask().log().error( "Entity instance in view: %s  entity: %s  does not have update authority:", view, entityDef.getName() );
                                ei.logEntity();
                            }
                        }
                        else
                            validateAttributePermission( oiSet, updateableEi );
                    }
                }

                // We have the necessary permission for this entity.  If the root of a read-only suboject then
                // we won't check the permissions of the children (nor will we attempt to commit them).
                if ( entityDef.isReadOnlySubobjectRoot() ) {
                    ei = ei.getLastChildHier();
                    errorMessage = String.format("Skipping commit of children under %s because it is flagged as readOnlyRelationshipRoot", entityDef.getName());
                    getTask().log().debug(  "Skipping commit of children under %s because it is flagged as readOnlyRelationshipRoot", entityDef.getName() );
                }

            } // each entityInstance
        } // each view

        if ( missingPermission )
            throw new ZeidonException( "Commit has an entity instance that doesn't have permission. " + errorMessage );
    }

    /**
     * Validate that this attribute has update authority if it's updated.
     * TODO: This throws an exception.  Maybe capture the exceptions and return them.
     * @param oiSet
     */
    private boolean validateAttributePermission( Set<ObjectInstance> oiSet, EntityInstanceImpl ei )
    {
        ei.getAttributes();
        for ( AttributeInstance attr : ei.getAttributes() )
        {
            if ( attr.isUpdated() )
                ((AttributeInstanceImpl) attr).validateUpdateAttribute( oiSet );
        }

        return false;
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
        if ( viewList.size() == 0 )
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
