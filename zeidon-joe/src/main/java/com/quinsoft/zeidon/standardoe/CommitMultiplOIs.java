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

    Copyright 2009-2012 QuinSoft
 */
/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.quinsoft.zeidon.CommitFlags;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.OiServerSelector;
import com.quinsoft.zeidon.SubobjectValidationException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
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
     * Same day this may be provided by the OE options.
     */
    private final OiServerSelector     selector = new DefaultOiServerSelector();

    private interface HasPermission
    {
        boolean hasPermission( EntityInstanceImpl ei );
    }

    private static final HasPermission hasDeletePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getViewEntity().isDelete();
        }};

    private static final HasPermission hasUpdatePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getViewEntity().isUpdate();
        }};

    private static final HasPermission hasCreatePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getViewEntity().isCreate();
        }};

    private static final HasPermission hasIncludePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getViewEntity().isInclude();
        }};

    private static final HasPermission hasExcludePermission = new HasPermission(){

        @Override
        public boolean hasPermission( EntityInstanceImpl ei )
        {
            return ei.getViewEntity().isExclude();
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
        Set<ObjectInstance> oiSet = new HashSet<ObjectInstance>();

        // Do some verification on the views, cast them to ViewImpl and copy them
        // to the viewList.
        List<ZeidonException> validationExceptions = new ArrayList<ZeidonException>();
        viewList = new ArrayList<ViewImpl>();
        for ( View v : originalViewList )
        {
            if ( v.isReadOnly() )
                throw new ZeidonException("Attempting to commit read-only view.  View = %s", v.getViewOd().getName() );

            ViewImpl view = ((InternalView) v).getViewImpl();
            ObjectInstance oi = view.getObjectInstance();
            if ( oi.isVersioned() )
                throw new ZeidonException("Attempting to commit a view with outstanding versioned instances.  " +
                                          "View = %s", v );

            // If this OI has no instances, skip it.
            if ( oi.getRootEntityInstance() == null )
                continue;

            oiSet.add( oi );

            Collection<ZeidonException> list = view.validateOi();
            if ( list != null )
                validationExceptions.addAll( list );

            viewList.add( view );
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
    private boolean validatePermissionForEi( EntityInstanceImpl ei, Set<ObjectInstance> oiSet, HasPermission permissionChecker )
    {
        for ( EntityInstanceImpl linked : ei.getAllLinkedInstances() )
        {
            ViewEntity viewEntity = ei.getViewEntity();
            if ( viewEntity.isDerivedPath() )
                continue;

            if ( permissionChecker.hasPermission( linked ) && oiSet.contains( linked.getObjectInstance() ) )
                return true;
        }

        return false;
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

        for ( ViewImpl view : viewList )
        {
            ObjectInstance oi = view.getObjectInstance();
            for ( EntityInstanceImpl ei : oi.getEntities( true ) )
            {
                ViewEntity viewEntity = ei.getViewEntity();

                // Unless we learn otherwise, assume this entity needs to be committed.
                ei.dbhNeedsCommit = false;

                if ( viewEntity.isDerivedPath() )
                    continue;

                if ( viewEntity.getDataRecord() == null )
                    continue;

                if ( ei.isCreated() && ! ei.isDeleted() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! viewEntity.isCreate() && ! validatePermissionForEi( ei, oiSet, hasCreatePermission ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have create authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isDeleted() && ! ei.isCreated() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! viewEntity.isDelete() && ! validatePermissionForEi( ei, oiSet, hasDeletePermission ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have delete authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isUpdated() && ! ei.isDeleted() && ! ei.isCreated() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! viewEntity.isExclude() && ! validatePermissionForEi( ei, oiSet, hasUpdatePermission ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have update authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isIncluded() && ! ei.isExcluded() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! viewEntity.isInclude() && ! validatePermissionForEi( ei, oiSet, hasIncludePermission ) )
                    {
                        missingPermission = true;
                        getTask().log().error( "Entity instance does not have include authority:" );
                        ei.logEntity();
                    }
                }

                if ( ei.isExcluded() && ! ei.isIncluded() )
                {
                    ei.dbhNeedsCommit = true;
                    if ( ! viewEntity.isExclude() && ! validatePermissionForEi( ei, oiSet, hasExcludePermission ) )
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
        {
            view.getViewOd().executeCommitConstraint( view );
        }

    }

    int commit()
    {
        validateCommit();
        // If there aren't any valid views then we have nothing to commit.
        if ( viewList.size() ==0 )
        	return 0;
        executeCommitConstraints();

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
