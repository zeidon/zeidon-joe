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

import java.util.EnumSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.OiSourceSelector;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * This class contains the logic for validating an activate and determining
 * what logic is used for the activate.
 *
 * @author dgc
 *
 */
class ActivateObjectInstance
{
    private final TaskImpl         task;
    private final ActivateOptions  options;
    private final OiSourceSelector selector;

    ActivateObjectInstance( TaskImpl task, ActivateOptions options )
    {
        this.task = task;
        this.options = options;
        selector = task.getOiSourceSelector();
    }

    ActivateObjectInstance( ViewImpl view )
    {
        this.task = view.getViewImpl().getTask();
        selector = task.getOiSourceSelector();
        if ( view.getActivateOptions() == null )
        {
            // If we get here then we're in an edge case.  The current OI was created
            // manually and has no ActivateOptions.  However, we're attempting to
            // lazy load the children of an entity that was included from a different
            // OI that was loaded from the db.  In this case we'll assume there is
            // no qualification on the child being lazy-loaded and we'll create an
            // empty ActivateOptions.
            options = new ActivateOptions( view );
        }
        else
            options = view.getActivateOptions();
    }

    View activate()
    {
        // TODO: check for activate continue.

        LodDef lodDef = options.getLodDef();
        if ( lodDef == null )
            throw new ZeidonException( "LodDef not specified" );

        EnumSet<ActivateFlags> control = options.getActivateFlags();

        // Make sure the OD has data records.
        EntityDef entityDef = lodDef.getEntityDef( 0 );
        if ( entityDef.getDataRecord() == null )
            throw new ZeidonException("LodDef %s does not have physical information", lodDef.getName() );

        // If the view defaults to having pessimistic locking and the flags indicate we don't want locking
        // then make sure we are read-only.
        if ( lodDef.getLockingLevel().isPessimisticLock() &&        // Default is locking...
             ! options.getLockingLevel().isPessimisticLock() &&     // But no locking on this activate
             ! control.contains( ActivateFlags.fREAD_ONLY ) )       // Read-only?
        {
            throw new ZeidonException("The LodDef must be activated with pessimistic locking or read-only" )
                        .prependLodDef( lodDef );
        }

        final Activator activator = selector.getActivator( getTask(), getApplication(), options );
        final InternalView view = (InternalView) activator.init( getTask(), null, options );
        if ( ! control.contains( ActivateFlags.fASYNCHRONOUS ) )
        {
            View v = activator.activate();
            if ( control.contains( ActivateFlags.fREAD_ONLY ) )
                ((InternalView) v).getViewImpl().getObjectInstance().setReadOnly( true );

            return v;
        }

        //
        // Doing an asynchronous activate.
        //
        JavaObjectEngine joe = getTask().getObjectEngine();
        Future<View> future = joe.getThreadPool().submit( new Callable<View>() {

            @Override
            public View call() throws Exception
            {
                return activator.activate();
            }} );

        return new FutureView( view.getViewImpl(), future );
    }

    /**
     * This is used by internal code to activate a subobject as part of a lazy load.
     *
     * @param view
     * @param childEntityDef
     */
    void activate( ViewImpl view, EntityDef childEntityDef )
    {
        options.setPerformingLazyLoad( true );
        final Activator activator = selector.getActivator( getTask(), getApplication(), options );
        activator.init( getTask(), view, options );
        activator.activate( childEntityDef );
        options.setPerformingLazyLoad( false );
    }

    private TaskImpl getTask()
    {
        return task;
    }

    /**
     * @return Application assigned to the task.
     * TODO: Should this return the application for the LodDef?
     */
    private Application getApplication()
    {
        return options.getLodDef().getApplication();
    }
}
