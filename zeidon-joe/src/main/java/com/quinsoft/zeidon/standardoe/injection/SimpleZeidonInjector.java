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
package com.quinsoft.zeidon.standardoe.injection;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonInjector;
import com.quinsoft.zeidon.ZeidonLogger;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class SimpleZeidonInjector implements ZeidonInjector
{
    private final static String UNNAMED = "";
    private final static ConcurrentMap<String, Object> emptyMap = new ConcurrentHashMap<>();

    private final ConcurrentMap<Class<?>, ConcurrentMap<String, Object>> injectedObjects = new ConcurrentHashMap<>();

    @Override
    public void initialize( ObjectEngine oe )
    {
        ZeidonLogger logger = oe.getSystemTask().log();
        logger.debug( "Initializing SimpleZeidonInjector" );
        try
        {
            ConfigurationBuilder config = new ConfigurationBuilder()
                    .setUrls( ClasspathHelper.forJavaClassPath() )
                    .addScanners( TypesAnnotated );
            Reflections reflections = new Reflections( config );
            Set<Class<?>> types = reflections.getTypesAnnotatedWith( ZeidonInjectionModule.class );
            logger.debug( "Found %s injection modules", types.size() );

            Set<Class<?>> curatedTypes = new HashSet<>( types );
            for ( Class<?> c : types )
            {
                if ( ZeidonModuleOverride.class.isAssignableFrom( c ) )
                {
                    logger.debug( "Module %s implements ZeidonModuleOverride", c.getCanonicalName() );
                    ZeidonModuleOverride module = (ZeidonModuleOverride) c.getConstructor().newInstance();
                    module.removeModules( curatedTypes );
                }
            }

            logger.debug( "Curated modules count: %s", curatedTypes.size() );

            for ( Class<?> c : curatedTypes )
            {
                if ( ZeidonModuleInitializer.class.isAssignableFrom( c ) )
                {
                    logger.debug( "Module %s implements ZeidonModuleInitializer", c.getCanonicalName() );
                    ZeidonModuleInitializer module = (ZeidonModuleInitializer) c.getConstructor().newInstance();
                    module.initialize( oe, this );
                }
            }
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    @Override
    public <T> T get( Class<T> key )
    {
        return (T) injectedObjects.getOrDefault( key, emptyMap ).get( UNNAMED );
    }

    @Override
    public <T> T get( Class<T> key, String name )
    {
        return (T) injectedObjects.getOrDefault( key, emptyMap ).get( name );
    }

    public SimpleZeidonInjector addObject( Class<?> targetClass, Object object )
    {
        return addNamedObject( targetClass, UNNAMED, object );
    }

    public SimpleZeidonInjector addObject( Object object )
    {
        return addNamedObject( object.getClass(), UNNAMED, object );
    }

    public synchronized SimpleZeidonInjector addNamedObject( Class<?> targetClass, String name, Object object )
    {
        ConcurrentMap<String, Object> map = injectedObjects.get( targetClass );
        if ( map == null )
        {
            map = new ConcurrentHashMap<>();
            injectedObjects.put( targetClass, map );
        }

        if ( map.containsKey( name ) )
            throw new ZeidonException( "SimpleZeidonInjector already has an object with those keys %s:%s",
                                       targetClass.getCanonicalName(), name );

        map.put( name, object );
        return this;
    }
}
