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
package com.quinsoft.zeidon.http;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * Activate an OI from a REST server.
 *
 */
public class ActivateOiFromRestServer implements Activator
{
    final private String serverUrl;

    private Task  task;
    private View  view;
    private ActivateOptions activateOptions;

    public ActivateOiFromRestServer( String serverUrl )
    {
        this.serverUrl = serverUrl;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.Activator#init(com.quinsoft.zeidon.standardoe.TaskImpl, com.quinsoft.zeidon.standardoe.ViewImpl, com.quinsoft.zeidon.ActivateOptions)
     */
    @Override
    public View init( Task task, View initialView, ActivateOptions options )
    {
        assert options != null;

        this.task = task;
        if ( initialView == null )
            view = this.task.activateEmptyObjectInstance( options.getLodDef() );
        else
            view = initialView;

        activateOptions = options;

        return view;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.Activator#activate()
     */
    @Override
    public View activate()
    {
        LodDef lodDef = view.getLodDef();
        Application application = lodDef.getApplication();
        View qual = activateOptions.getQualificationObject();
        String url = String.format("%s/%s/%s", serverUrl, application.getName(), view.getLodDef().getName());

        return ZeidonHttpClientFactory.getInstance( task.getObjectEngine() ).getClient( task )
                .setUrl( url )
                .setQualParam( qual )
                .callGet( view.getLodDef() )
                .getResponseView();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.Activator#activate(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public int activate( EntityDef subobjectRootEntity )
    {
        throw new ZeidonException( "Lazy load activate is not supported for REST (yet)." );
    }

    @Override
    public void dropOutstandingLocks()
    {
        throw new ZeidonException( "Not implemented" );
    }
}
