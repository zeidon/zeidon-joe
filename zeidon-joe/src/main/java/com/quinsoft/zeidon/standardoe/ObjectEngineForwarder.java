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

import java.util.List;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownApplicationException;
import com.quinsoft.zeidon.domains.DomainClassLoader;

/**
 * A simple forwarder for the ObjectEngine interface.
 * @author dg
 *
 */
public abstract class ObjectEngineForwarder implements ObjectEngine
{
    private ObjectEngine objectEngine;

    @Override
    public String getId()
    {
        return getObjectEngine().getId();
    }

    @Override
    public List<? extends Application> getApplicationList()
    {
        return getObjectEngine().getApplicationList();
    }

    @Override
    public Application getApplication( String appName ) throws UnknownApplicationException
    {
        return getObjectEngine().getApplication( appName );
    }

    @Override
    public Task createTask( String applicationName ) throws UnknownApplicationException
    {
        return getObjectEngine().createTask( applicationName );
    }

    @Override
    public Task createTask( String applicationName, String taskId ) throws UnknownApplicationException
    {
        return getObjectEngine().createTask( applicationName, taskId );
    }

    @Override
    public Task createTask( String applicationName, boolean persistent ) throws UnknownApplicationException
    {
        return getObjectEngine().createTask( applicationName, persistent );
    }

    @Override
    public Task getSystemTask()
    {
        return getObjectEngine().getSystemTask();
    }

    @Override
    public Task getTaskById( String id )
    {
        return getObjectEngine().getTaskById( id );
    }

    @Override
    public List<? extends Task> getTaskList()
    {
        return getObjectEngine().getTaskList();
    }

    @Override
    public String getHomeDirectory()
    {
        return getObjectEngine().getHomeDirectory();
    }

    @Override
    public String getVersion()
    {
        return getObjectEngine().getVersion();
    }

    @Override
    public ClassLoader getClassLoader( String className )
    {
        return getObjectEngine().getClassLoader( className );
    }

    @Override
    public DomainClassLoader getDomainClassLoader()
    {
        return getObjectEngine().getDomainClassLoader();
    }

    public ObjectEngine getObjectEngine()
    {
        return objectEngine;
    }

    public void setObjectEngine( ObjectEngine objectEngine )
    {
        this.objectEngine = objectEngine;
    }
}
