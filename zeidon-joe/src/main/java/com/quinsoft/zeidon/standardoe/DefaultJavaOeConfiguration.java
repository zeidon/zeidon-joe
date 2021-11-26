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

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.quinsoft.zeidon.ObjectEngineEventListener;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.TaskLogger;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonInjector;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.config.DefaultPreferencesFactory;
import com.quinsoft.zeidon.config.HomeDirectory;
import com.quinsoft.zeidon.config.HomeDirectoryFromEnvVar;
import com.quinsoft.zeidon.config.HomeDirectoryFromString;
import com.quinsoft.zeidon.config.UuidGenerator;
import com.quinsoft.zeidon.config.ZeidonIniPreferences;
import com.quinsoft.zeidon.config.ZeidonPreferences;
import com.quinsoft.zeidon.config.ZeidonPreferencesFactory;
import com.quinsoft.zeidon.config.ZeidonPropertyPreferences;
import com.quinsoft.zeidon.domains.DomainClassLoader;
import com.quinsoft.zeidon.jmx.JmxObjectEngineMonitor;
import com.quinsoft.zeidon.standardoe.injection.SimpleZeidonInjector;
import com.quinsoft.zeidon.utils.JoeUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Returns the default configuration options for the Java Object Engine.  This is designed to be
 * easily subclassed.
 *
 */
public class DefaultJavaOeConfiguration implements JavaOeConfiguration
{
    protected HomeDirectory homeDirectory;
    protected DomainClassLoader domainClassLoader;
    protected ZeidonLogger zeidonLogger;
    protected ZeidonPreferencesFactory zeidonPreferencesFactory;
    protected ObjectEngineEventListener oeListener;
    protected ExecutorService activateThreadPool;
    protected UuidGenerator uuidGenerator;
    protected ConcurrentMap<String, Task> taskCacheMap;
    protected String jmxAppName = "DefaultOE";
    protected String preferencesFilename;
    protected ZeidonInjector zeidonInjector;

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.JavaOeOptions#getHomeDirectory()
     */
    @Override
    public HomeDirectory getHomeDirectory()
    {
        if ( homeDirectory == null )
        {
            if ( JoeUtils.getEnvProperty( "ZEIDON_HOME" ) != null )
                homeDirectory = new HomeDirectoryFromEnvVar();
            else
                homeDirectory = new HomeDirectoryFromString( "" );
        }

        return homeDirectory;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.JavaOeOptions#getDomainClassLoader()
     */
    @Override
    public DomainClassLoader getDomainClassLoader()
    {
        if ( domainClassLoader == null )
            domainClassLoader = new DomainLoader();

        return domainClassLoader;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.JavaOeOptions#getZeidonLogger()
     */
    @Override
    public ZeidonLogger getZeidonLogger()
    {
        if ( zeidonLogger == null )
            zeidonLogger = new TaskLogger( "[system] " );

        return zeidonLogger;
    }

    /**
     * Return a ZeidonPreferencesFactory, create one if necessary.  If preferencesFilename is specified
     * then use the extension of the filename to determine what kind of factory to create.  If none is
     * specified then "zeidon.ini" is assumed.
     */
    @Override
    public ZeidonPreferencesFactory getPreferencesFactory()
    {
        if ( zeidonPreferencesFactory == null )
        {
            // By default the value of ZEIDON_INI overrides all.  This allows test environments to set up
            // their own configuration.
            String envvar = JoeUtils.getEnvProperty( "ZEIDON_INI" );
            if ( ! StringUtils.isBlank( envvar ) )
            {
                if ( ! StringUtils.isBlank( preferencesFilename ) )
                {
                    Logger LOG = LoggerFactory.getLogger( DefaultJavaOeConfiguration.class );
                    LOG.info( "Overriding " + preferencesFilename + " with ZEIDON_INI=" + envvar );
                    System.out.println( "Overriding " + preferencesFilename + " with ZEIDON_INI=" + envvar );
                }

                preferencesFilename = envvar;
            }

            if ( StringUtils.isBlank( preferencesFilename ) )
            {
                ZeidonIniPreferences iniPref = new ZeidonIniPreferences( getHomeDirectory(), getJmxAppName() );
                zeidonPreferencesFactory = new DefaultPreferencesFactory( iniPref, getJmxAppName() );
            }
            else
            {
                ZeidonPreferences prefs;
                String extension = FilenameUtils.getExtension( preferencesFilename );
                if ( StringUtils.equalsIgnoreCase( extension, "ini" ) )
                    prefs = new ZeidonIniPreferences( preferencesFilename, getJmxAppName() );
                else
                if ( StringUtils.equalsIgnoreCase( extension, "properties" ) )
                    prefs = new ZeidonPropertyPreferences( preferencesFilename, getJmxAppName() );
                else
                    throw new ZeidonException( "Unknown ZeidonPreferences extension for %s", preferencesFilename );

                zeidonPreferencesFactory = new DefaultPreferencesFactory( prefs, getJmxAppName() );
            }
        }

        return zeidonPreferencesFactory;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.JavaOeOptions#getObjectEngineListener()
     */
    @Override
    public ObjectEngineEventListener getObjectEngineListener()
    {
        if ( oeListener == null )
            oeListener = new JmxObjectEngineMonitor( this );

        return oeListener;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.JavaOeOptions#getActivateThreadPool()
     */
    @Override
    public ExecutorService getActivateThreadPool()
    {
        if ( activateThreadPool == null )
            activateThreadPool = Executors.newCachedThreadPool();

        return activateThreadPool;
    }

    public DefaultJavaOeConfiguration setHomeDirectory( HomeDirectory homeDirectory )
    {
        this.homeDirectory = homeDirectory;
        return this;
    }

    public DefaultJavaOeConfiguration setDomainClassLoader( DomainClassLoader domainClassLoader )
    {
        this.domainClassLoader = domainClassLoader;
        return this;
    }

    public DefaultJavaOeConfiguration setZeidonLogger( ZeidonLogger zeidonLogger )
    {
        this.zeidonLogger = zeidonLogger;
        return this;
    }

    public DefaultJavaOeConfiguration setZeidonPreferencesFactory( ZeidonPreferencesFactory zeidonPreferences )
    {
        this.zeidonPreferencesFactory = zeidonPreferences;
        return this;
    }

    public DefaultJavaOeConfiguration setOeListener( ObjectEngineEventListener oeListener )
    {
        this.oeListener = oeListener;
        return this;
    }

    public DefaultJavaOeConfiguration setActivatePoolThread( ExecutorService activatePoolThread )
    {
        this.activateThreadPool = activatePoolThread;
        return this;
    }

    @Override
    public UuidGenerator getUuidGenerator()
    {
        if ( uuidGenerator == null )
            uuidGenerator = new UuidGeneratorImpl();

        return uuidGenerator;
    }

    public DefaultJavaOeConfiguration setUuidGenerator( UuidGenerator uuidGenerator )
    {
        this.uuidGenerator = uuidGenerator;
        return this;
    }

    private static class UuidGeneratorImpl implements UuidGenerator
    {
        private final TimeBasedGenerator generator;

        private UuidGeneratorImpl()
        {
            EthernetAddress nic = EthernetAddress.fromInterface();
            generator = Generators.timeBasedGenerator(nic);
        }

        @Override
        public UUID generate()
        {
            return generator.generate();
        }
    }

    @Override
    public ConcurrentMap<String, Task> getPersistentTaskCacheMap()
    {
        Cache<String, Task> x;
        if ( taskCacheMap == null )
        {
            x = CacheBuilder.newBuilder().concurrencyLevel( 10 ).build();
            taskCacheMap = x.asMap();
        }

        return taskCacheMap;
    }

    public DefaultJavaOeConfiguration setTaskCacheMap( ConcurrentMap<String, Task> taskCacheMap )
    {
        this.taskCacheMap = taskCacheMap;
        return this;
    }

    @Override
    public String getJmxAppName()
    {
        return jmxAppName;
    }

    public DefaultJavaOeConfiguration setJmxAppName( String jmxAppName )
    {
        this.jmxAppName = jmxAppName;
        return this;
    }

    public String getPreferencesFilename()
    {
        return preferencesFilename;
    }

    public DefaultJavaOeConfiguration setPreferencesFilename( String preferencesFilename )
    {
        this.preferencesFilename = preferencesFilename;
        return this;
    }

    @Override
    public ZeidonInjector getInjector()
    {
        if ( zeidonInjector == null )
            zeidonInjector = new SimpleZeidonInjector();

        return zeidonInjector;
    }
}
