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

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.OiSourceSelector;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Determines where an OI is activated the oiSourceUrl.  E.g. if
 * URL begins with "jdbc:" then the source is a JDBC database.
 *
 * This is the default OI selector used by Zeidon.  It can be
 * overridden when instantiating a JavaObjectEngine.
 *
 */
public class DefaultOiSourceSelector implements OiSourceSelector
{
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.OiServerSelector#getActivator(com.quinsoft.zeidon.Task, com.quinsoft.zeidon.View)
     */
    @Override
    public Activator getActivator( Task task, Application application, ActivateOptions options )
    {
        Activator activator = options.getActivator();
        if ( activator != null )
            return activator;

        String url = options.getOiSourceUrl();
        if ( StringUtils.isBlank( url ) )
            throw new ZeidonException( "oiSourceUrl has not been specified in config" )
                    .appendMessage( "Configuration source: %s", options.getConfigSource() )
                    .appendMessage( "Application: %s", options.getApplication().getName() );

        if ( url.startsWith( "jdbc:" ) || url.startsWith( "testsql:" ) )
            return new ActivateOiFromDB();

        if ( url.startsWith( "http:" ) || url.startsWith( "https:" ) )
            return getRestActivator( task, url );

        if ( url.startsWith( "file:" ) || url.startsWith( "resource:" ) )
            return new ActivateOiFromFileDb();

        throw new ZeidonException( "oiSourceUrl specifies unknown protocol: %s", url );
    }

    /**
     * Dynamically retrieve the REST activator because it's in a different package.
     * We do this so that all the http code can live in another package.  Some day
     * we should make this more formal.
     */
    private Activator getRestActivator( Task task, String url )
    {
        String className = "com.quinsoft.zeidon.http.ActivateOiFromRestServer";
        Class<?>[] type = { String.class };

        try
        {
            ClassLoader loader = task.getObjectEngine().getClassLoader( className );
            Class<?> clazz = loader.loadClass( className );
            Constructor<?> cons = clazz.getConstructor( type );
            Object[] args = { url };
            return (Activator) cons.newInstance( args );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.OiServerSelector#getCommitter(com.quinsoft.zeidon.Task, java.util.List)
     */
    @Override
    public Committer getCommitter( Task task, List<? extends View> viewList, CommitOptions options )
    {
        String url = options.getOiSourceUrl();
        if ( StringUtils.isBlank( url ) )
            throw new ZeidonException( "oiSourceUrl has not been specified in config" )
                    .appendMessage( "Configuration source: %s", options.getConfigSource() )
                    .appendMessage( "Application: %s", options.getApplication().getName() );

        if ( url.startsWith( "jdbc:" ) )
        {
            View v = viewList.get( 0 );

            // Is there a genkey handler in the LodDef?  If not, then use
            // the committer that will let the DB generate the keys.
            if ( StringUtils.isBlank( v.getLodDef().getGenkeyHandler() ) )
                return new CommitToSqlWithDbGeneratedKeys();
            else
                return new CommitToDbUsingGenkeyHandler();
        }

        if ( url.startsWith( "http:" ) || url.startsWith( "https:" ) )
            return getRestCommitter( task );

        if ( url.startsWith( "file:" ) || url.startsWith( "resource:" ) )
            return new CommitOiToFileDb();

        throw new ZeidonException( "oiSourceUrl specifies unknown protocol: %s", url );
    }

    /**
     * Dynamically retrieve the REST committer because it's in a different package.
     * We do this so that all the http code can live in another package.  Some day
     * we should make this more formal.
     */
    private Committer getRestCommitter( Task task )
    {
        String className = "com.quinsoft.zeidon.standardoe.CommitToRestServer";
        Class<?>[] type = { };

        try
        {
            ClassLoader loader = task.getObjectEngine().getClassLoader( className );
            Class<?> clazz = loader.loadClass( className );
            Constructor<?> cons = clazz.getConstructor( type );
            Object[] args = { };
            return (Committer) cons.newInstance( args );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }
}
