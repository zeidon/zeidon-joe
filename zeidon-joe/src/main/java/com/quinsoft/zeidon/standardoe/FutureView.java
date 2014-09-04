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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Level;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * A view used for asynchronous views.  An activate that is performed asynchronously returns
 * a future view.  Any operations that require the OI are blocked until the activate
 * finishes.
 *
 * @author DG
 *
 */
class FutureView extends InternalViewForwarder implements Future<View>
{
    private Future<View> future;

    /**
     * @param view
     */
    FutureView( ViewImpl view, Future<View> future )
    {
        super( view );
        this.future = future;
    }

    /**
     * Waits for the future to finish and come back.
     */
    private void waitForFuture()
    {
        try
        {
            future.get();
            future = null; // Let GC clean up the future.
        }
        catch ( ExecutionException e )
        {
            throw ZeidonException.wrapException( e.getCause() );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    @Override
    synchronized protected ViewImpl getView()
    {
        if ( future != null )
            waitForFuture();

        return super.getView();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#cancel(boolean)
     */
    @Override
    synchronized public boolean cancel( boolean mayInterruptIfRunning )
    {
        if ( future == null )
            return false;

        return future.cancel( mayInterruptIfRunning );
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isCancelled()
     */
    @Override
    synchronized public boolean isCancelled()
    {
        if ( future == null )
            return false;

        return future.isCancelled();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isDone()
     */
    @Override
    synchronized public boolean isDone()
    {
        if ( future == null )
            return true;

        return future.isDone();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get()
     */
    @Override
    public View get() throws InterruptedException, ExecutionException
    {
        return getView();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public View get( long timeout, TimeUnit unit ) throws InterruptedException, ExecutionException, TimeoutException
    {
        return getView();
    }

    @Override
    public void setName( String name )
    {
        // We don't need to wait for the activate to finish before we name it.
        // Call super to get the view without waiting for the future to return.
        super.getView().setName( name );
    }

    @Override
    public void setNameForSubtask( String name, View view )
    {
        // We don't need to wait for the activate to finish before we name it.
        // Call super to get the view without waiting for the future to return.
        super.getView().setNameForSubtask( name, view );
    }

    @Override
    public void setName( String name, Level level )
    {
        // We don't need to wait for the activate to finish before we name it.
        // Call super to get the view without waiting for the future to return.
        super.getView().setName( name, level );
    }

    @Override
    public ActivateOptions getActivateOptions()
    {
        // We don't need to wait for the activate to finish to get the options.
        // Call super to get the view without waiting for the future to return.
        return super.getView().getActivateOptions();
    }
}
