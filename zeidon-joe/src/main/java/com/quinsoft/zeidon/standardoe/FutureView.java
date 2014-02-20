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

package com.quinsoft.zeidon.standardoe;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    private final Future<View> future;

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
    protected ViewImpl getView()
    {
        waitForFuture();
        return super.getView();
    }
    
    /* (non-Javadoc)
     * @see java.util.concurrent.Future#cancel(boolean)
     */
    @Override
    public boolean cancel( boolean mayInterruptIfRunning )
    {
        waitForFuture();
        return future.cancel( mayInterruptIfRunning );
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isCancelled()
     */
    @Override
    public boolean isCancelled()
    {
        return future.isCancelled();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#isDone()
     */
    @Override
    public boolean isDone()
    {
        return future.isDone();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get()
     */
    @Override
    public View get() throws InterruptedException, ExecutionException
    {
        return future.get();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Future#get(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public View get( long timeout, TimeUnit unit ) throws InterruptedException, ExecutionException, TimeoutException
    {
        return future.get( timeout, unit );
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
    public Collection<ZeidonException> validateOi()
    {
        return super.getView().validateOi();
    }
}
