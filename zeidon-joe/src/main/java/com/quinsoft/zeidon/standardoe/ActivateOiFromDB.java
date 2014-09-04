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

import java.util.EnumSet;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.dbhandler.DbHandler;
import com.quinsoft.zeidon.dbhandler.JdbcHandlerUtils;
import com.quinsoft.zeidon.dbhandler.PessimisticLockingHandler;
import com.quinsoft.zeidon.objectdefinition.LockingLevel;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.utils.Timer;

/**
 * @author DG
 *
 */
class ActivateOiFromDB implements Activator
{
    private TaskImpl  task;
    private ViewImpl  view;
    private View      qual;
    private EnumSet<ActivateFlags> control;
    private ViewOd      viewOd;
    private DbHandler   dbHandler;
    private ActivateOptions options;

    @Override
    public View init(Task task, View view, ActivateOptions options )
    {
        assert options != null;

        this.task = (TaskImpl) task;
        if ( view == null )
            view = task.activateEmptyObjectInstance( options.getViewOd() );
        this.view = ((InternalView) view).getViewImpl();

        this.qual = options.getQualificationObject();
        this.options = options;
        control = options.getActivateFlags();
        viewOd = options.getViewOd();

        JdbcHandlerUtils helper = new JdbcHandlerUtils( options, viewOd.getDatabase() );
        dbHandler = helper.getDbHandler();
        return view;
    }

    /**
     * Activate the OI.
     *
     * @return
     */
    @Override
    public ViewImpl activate()
    {
        Timer timer = new Timer();
        ObjectInstance oi = view.getObjectInstance();

        ViewEntity rootEntity = viewOd.getRoot();
        activate( rootEntity );

        if ( oi.getRootEntityInstance() != null ) // Did we load anything?
		{
            // Check the pessimistic locks.  We need to do this after we load the OI because
            // databases (sqlite--grrrrr) can't handle two open connections updating the DB
            // at the same time.
            if ( options.getLockingLevel() == LockingLevel.PESSIMISTIC )
                acquirePessimisticLocks();

            view.reset();
            view.getViewOd().executeActivateConstraint( view );
		}
        task.getObjectEngine().getOeEventListener().objectInstanceActivated( view, qual, timer.getMilliTime(), null );

        return view;
    }

    /**
     * Activate a subobject with subobjectRootEntity as the root.  If subobjectRootootEntity = ViewOD.root then
     * this activates the whole OI.
     *
     * @param subobjectRootEntity
     * @return
     */
    @Override
    public int activate( ViewEntity subobjectRootEntity )
    {
        Timer timer = new Timer();
        int rc = 0;
        boolean transactionStarted = false;
        boolean commit = false;
        String viewName = "__Load in progress " + viewOd.getName();
        ObjectInstance oi = view.getObjectInstance();
        try
        {
            // Store the activate options for later use.
            view.getObjectInstance().setActivateOptions( options );

            // Set flag to tell cursor processing to not bother with checking for lazy-loaded
            // entities.  Since we're activating we know we don't want to load lazy entities
            // via cursor access.
            oi.setIgnoreLazyLoadEntities( true );

            task.log().debug( "Activating %s from DB", viewOd.getName() );
            view.setName( viewName );

            dbHandler.beginTransaction();
            transactionStarted = true;

            rc = singleActivate( subobjectRootEntity );

            commit = true;
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e ).prependViewOd( view.getViewOd() );
            if ( task.log().isDebugEnabled() )
                ze.appendMessage( "XOD: %s", this.viewOd.getFileName() );
            task.getObjectEngine().getOeEventListener().objectInstanceActivated( view, qual, timer.getMilliTime(), ze );
            throw ze;
        }
        finally
        {
            oi.setIgnoreLazyLoadEntities( false );
            view.dropNameForView( viewName );
            if ( transactionStarted )
                dbHandler.endTransaction( commit );

            if ( timer.getMilliTime() > 2000 )
                task.log().info( "==> Activate for %s took %s seconds", viewOd, timer );
            else
                task.log().info( "==> Activate for %s took %d milliseconds", viewOd, timer.getMilliTime() );
        }


        return rc;
    }

    /**
     * Activate all the child entities for all the twins in parentCursor.
     * @param parentCursor
     */
    private void activateChildren( EntityCursorImpl parentCursor )
    {
        if ( ! parentCursor.setFirst().isSet() )
            return;

        ViewEntity parentViewEntity = parentCursor.getViewEntity();

        /*
         * Is parent recursive?  If so, then we have a situation like this:
         *  A
         *  |
         *  B
         * Where B and A are the same ER entity and parentCursor points to B.
         * We need to iterate through each of the B's and set the recursive
         * structure so that B because the subobject root.
         */
        boolean recursive = parentViewEntity.isRecursive();
        if ( recursive )
        {
            // Reset parentViewEntity to point to A from the example above.
            parentViewEntity = parentViewEntity.getRecursiveParentViewEntity();
        }

        // We can't use setNextContinue() because the recursiveness throws off the cursor.
        // We don't want to use setFirst()/setNext() because it has performance issues, so we'll
        // go through the twins manually and set the parentCursor.
        for ( EntityInstanceImpl parentEi = parentCursor.getEntityInstance();
                                 parentEi != null;
                                 parentEi = parentEi.getNextTwin() )
        {
            parentCursor.setCursor( parentEi );

            if ( recursive )
                parentCursor.setToSubobject(); // B will now become accessed via entity A.

            for ( ViewEntity childViewEntity : parentViewEntity.getChildren() )
            {
                if ( childViewEntity.isDerived() || childViewEntity.isDerivedPath() )
                    continue;

                if ( childViewEntity.getDataRecord() == null )
                    continue;

                if ( isLazyLoad( childViewEntity ) )
                {
                    view.dblog().trace( "Entity %s is flagged as LazyLoad. Skipping activate", childViewEntity.getName() );
                    continue;
                }

                if ( dbHandler.isQualified( childViewEntity ) )
                {
                    parentEi.setIncomplete( true );

                    // Now set the flag for any parents that will cause the child to be deleted
                    // if the parent is deleted.
                    for ( EntityInstanceImpl p = parentEi;
                          p != null && p.getViewEntity().isParentDelete();
                          p = p.getParent() )
                    {
                        p.setIncomplete( true );
                    }
                }

                int load = dbHandler.loadEntity( view, childViewEntity );
                if ( load == DbHandler.LOAD_DONE )
                    activateChildren( view.cursor( childViewEntity ) );
            }

            if ( recursive )
                parentCursor.resetSubobjectToParent();
        }
    }

    /**
     * returns true if viewEntity should be loaded lazily.
     *
     * @param viewEntity
     * @return
     */
    private boolean isLazyLoad( ViewEntity viewEntity )
    {
        return viewEntity.getLazyLoadConfig().isLazyLoad() && ! control.contains(  ActivateFlags.fINCLUDE_LAZYLOAD );
    }

    /**
     * Assert that there are no incremental flags still set.  Called after a commit.
     *
     * @param ei
     * @return
     */
    private boolean assertFlagsAreOff( EntityInstanceImpl ei )
    {
        for ( ViewAttribute viewAttribute : ei.getNonNullAttributeList() )
        {
            AttributeValue attrib = ei.getInternalAttribute( viewAttribute );
            if ( attrib.isUpdated() )
            {
                task.log().error( "Assert: Attribute %s %s is flagged as updated", viewAttribute, attrib );
                return false;
            }
        }

        return true;
    }

    /**
     * Activate a single object instance or subobject, starting with rootViewEntity.
     * @return
     */
    private int singleActivate( ViewEntity rootViewEntity )
    {
        // Are we loading the OI root?  If so we're loading the whole OI.
        boolean isOiRoot = rootViewEntity.getParent() == null;

        assert isOiRoot || rootViewEntity.getLazyLoadConfig().isLazyLoad() : "We're loading an entity that is neither root or lazyload";

        ObjectInstance oi = view.getObjectInstance();

        // We'll be setting the update flag for the OI after we finish the activate.
        // If we're loading the OI then we'll be setting them to false.  If we're
        // doing a lazy load then we need to reset the OI flags to what they are
        // currently.
        boolean isUpdated = false;
        boolean isUpdatedFile = false;
        if ( ! isOiRoot )
        {
            // Save the OI flags for later.
            isUpdated = oi.isUpdated();
            isUpdatedFile = oi.isUpdatedFile();
        }

        dbHandler.beginActivate( view, qual, control );
        int rc = dbHandler.loadEntity( view, rootViewEntity );

        // Activate the child entities unless we're activating the root and ROOT_ONLY is set.
        if ( ! isOiRoot || ! control.contains( ActivateFlags.fROOT_ONLY ) )
            activateChildren( view.cursor( rootViewEntity ) );

        if ( rc == 0 ) // Did we load anything?
        {
            // Loop through the newly loaded EIs and reset their flags.
            if ( isOiRoot )
            {
                for ( EntityInstanceImpl ei : oi.getEntities() )
                {
                    assert assertFlagsAreOff( ei ) : "Error in attrib flags.  See log for more.";
                    ei.setCreated( false );
                    ei.setUpdated( false );
                }
            }
            else
            {
                // Get the parent of the loaded instance.  We don't want to use the rootEntity cursor because
                // that will trigger a recursive loop that attempts to load the lazyload EIs.
                EntityInstanceImpl ei = view.cursor( rootViewEntity.getParent() ).getEntityInstance();

                // Should never be null since we check if any are loaded.
                ei = ei.getFirstChildMatchingViewEntity( rootViewEntity );
                while ( ei != null )
                {
                    assert ! ei.isHidden() : "We have a newly loaded EI that is hidden.";
                    for ( EntityInstanceImpl t : ei.getChildrenHier( true ) )
                    {
                        assert assertFlagsAreOff( t ) : "Error in attrib flags.  See log for more.";
                        t.setCreated( false );
                        t.setUpdated( false );
                    }

                    ei = ei.getNextTwin();
                }
            }

            oi.setUpdated( isUpdated );
            oi.setUpdatedFile( isUpdatedFile );
        }

        return rc;
    }

    private void acquirePessimisticLocks()
    {
        PessimisticLockingHandler locker = view.getPessimisticLockingHandler();
        locker.acquireLocks( view );
        view.getTask().addLockedView( view );
    }
}
