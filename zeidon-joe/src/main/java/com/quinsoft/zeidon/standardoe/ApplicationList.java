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

import com.google.common.collect.ImmutableMap;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.UnknownApplicationException;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.config.HomeDirectory;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.PortableFileReader;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileAttributeHandler;
import com.quinsoft.zeidon.utils.PortableFileReader.PortableFileEntityHandler.NullEntityHandler;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DG
 *
 */
class ApplicationList
{
    /**
     * List of applications loaded by the system.
     */
    private final ImmutableMap<String, ApplicationImpl> applications;
    private final HomeDirectory home;

    /**
     * Loads the list of applications from %ZEIDON%/zeidon.app
     */
    ApplicationList( ObjectEngine objectEngine, HomeDirectory home, ZeidonLogger logger )
    {
        logger.info( "Loading application list" );

        this.home = home;
        Map<String, ApplicationImpl> apps = new HashMap<String, ApplicationImpl>();

        // First try loading the resources.
        ClassLoader classLoader = getClass().getClassLoader();
        if ( classLoader == null )
            classLoader = ClassLoader.getSystemClassLoader();

        try
        {
            for ( Enumeration<URL> element = classLoader.getResources( "zeidon.app" ); element.hasMoreElements(); )
            {
                URL url = element.nextElement();
                logger.info( "Loading applications from resource %s", url.getFile() );
                InputStream stream = url.openStream();
                ApplicationHandler appHandler = new ApplicationHandler( apps );
                PortableFileReader.ReadPortableFile( objectEngine, stream, logger, appHandler );
            }
        }
        catch( Exception e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "Error while attempting to load zeidon.app" );
        }

        if ( ! StringUtils.isBlank( home.getHomeDirectory() ) )
        {
            String filename = FilenameUtils.concat( home.getHomeDirectory(), "zeidon.app" );
            try
            {
                InputStream inputStream = JoeUtils.getInputStream( null, filename, logger.getClass().getClassLoader() );
                if ( inputStream == null )
                    logger.info( "No zeidon.app found via ZEIDON_HOME." );
                else
                {
                    logger.info( "Loading apps using ZEIDON_HOME %s/zeidon.app", home.getHomeDirectory() );
                    ApplicationHandler appHandler = new ApplicationHandler( apps );
                    PortableFileReader.ReadPortableFile( objectEngine, inputStream, logger, appHandler );
                }
            }
            catch ( Exception e )
            {
                throw ZeidonException.wrapException( e ).prependFilename( filename );
            }
        }

        if ( apps.size() == 0 )
            throw new ZeidonException( "No resources named zeidon.app found." );

        applications = ImmutableMap.copyOf( apps );
    }

    ApplicationImpl getApplication( Task task, String applicationName ) throws UnknownApplicationException
    {
        if ( applications.containsKey( applicationName.toLowerCase() ) )
        {
            ApplicationImpl app = applications.get( applicationName.toLowerCase() );
            app.loadDomains( task );
            return app;
        }

        throw new UnknownApplicationException( applicationName );
    }

    List<ApplicationImpl> getList()
    {
        return new ArrayList<ApplicationImpl>( applications.values() );
    }

    private class ApplicationHandler extends NullEntityHandler
    {
        private final Map<String, ApplicationImpl> appMap;

        public ApplicationHandler( Map<String, ApplicationImpl> appMap )
        {
            this.appMap = appMap;
        }

        @Override
        public PortableFileAttributeHandler createEntity(PortableFileReader reader, int level, long flags)
        {
            if ( reader.getAttributeName().equals( "ZEIDON" ))
                return new SystemApplication( home.getHomeDirectory(), reader.geObjectEngine() );

            return new ApplicationImpl( home.getHomeDirectory(), reader.geObjectEngine() );
        }

        @Override
        public void endEntity(PortableFileReader reader, PortableFileAttributeHandler handler, int level)
        {
            ApplicationImpl app = (ApplicationImpl) handler;

            String appKey = app.getName().toLowerCase();

            // Check to see if we've already loaded the zeidon.app for this app.  We don't mind multiple
            // ZeidonSystem apps because the attribute values are hard-coded and can't change so overwriting
            // an existing value in the map doesn't harm anything.
            if ( appMap.containsKey( appKey ) && ! ObjectEngine.ZEIDON_SYSTEM_APP_NAME.equals( app.getName() ) )
                throw new ZeidonException( "Application '%s' is defined multiple times", app.getName() );

            appMap.put( appKey, app );
        }
    }
}
