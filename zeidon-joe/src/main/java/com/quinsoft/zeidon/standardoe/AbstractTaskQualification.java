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
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.StreamReader;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownLodDefException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.CacheMapImpl;

/**
 * @author DG
 *
 */
abstract class AbstractTaskQualification implements TaskQualification, CacheMap
{

    private final Application app;

    /**
     * This is a Concurrent hashmap that can be used by application as a task-level cache.
     */
    private CacheMap cacheMap;

    AbstractTaskQualification( Application app )
    {
        this.app = app;
    }

    @Override
    public abstract TaskImpl getTask();

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#activateEmptyObjectInstance(java.lang.String)
     */
    @Override
    public ViewImpl activateEmptyObjectInstance(String lodDefName) throws UnknownLodDefException
    {
        LodDef lodDef = getApplication().getLodDef( getTask(), lodDefName );
        return activateEmptyObjectInstance( lodDef );
    }

    @Override
    public JavaObjectEngine getObjectEngine()
    {
        return getTask().getObjectEngine();
    }

    @Override
    public ViewImpl activateEmptyObjectInstance( String lodDefName, Application app ) throws UnknownLodDefException
    {
        LodDef lodDef = app.getLodDef( getTask(), lodDefName );
        return activateEmptyObjectInstance( lodDef );
    }

    @Override
    public ViewImpl activateEmptyObjectInstance( String lodDefName, String appName ) throws UnknownLodDefException
    {
        LodDef lodDef = getApplication( appName ).getLodDef( getTask(), lodDefName );
        return activateEmptyObjectInstance( lodDef );
    }

    @Override
    public ViewImpl activateEmptyObjectInstance(LodDef lodDef)
    {
        ViewImpl view = new ViewImpl( getTask(), lodDef );
        return view;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#activateObjectInstance(java.lang.String)
     */
    @Override
    public View activateObjectInstance(String lodDefName, View qual, EnumSet<ActivateFlags> control ) throws UnknownLodDefException
    {
        LodDef lodDef = getApplication().getLodDef( getTask(), lodDefName );
        ActivateOptions options = new ActivateOptions( getTask() );
        options.setLodDef( lodDef );
        options.setQualificationObject( qual );
        options.setActivateFlags( control );
        return activateObjectInstance( options );
    }

    @Override
    public View activateObjectInstance(LodDef lodDef, View qual, EnumSet<ActivateFlags> control )
    {
        ActivateOptions options = new ActivateOptions( getTask() );
        options.setLodDef( lodDef );
        options.setQualificationObject( qual );
        options.setActivateFlags( control );
        return activateObjectInstance( options );
    }

    @Override
    public  View activateObjectInstance( ActivateOptions options ) throws UnknownLodDefException
    {
        ActivateObjectInstance activator = new ActivateObjectInstance( getTask(), options );
        return activator.activate();
    }

    @Override
    public View activateObjectInstance( String lodDefName, View qual, ActivateOptions options )
    {
        LodDef lodDef = getApplication().getLodDef( getTask(), lodDefName );
        options.setLodDef( lodDef );
        options.setQualificationObject( qual );
        return activateObjectInstance( options );
    }

    @Override
    public Application getApplication()
    {
        return app;
    }

    @Override
    public Application getApplication( String appName )
    {
        return getTask().getObjectEngine().getApplication( appName );
    }

    @Override
    public ZeidonLogger log()
    {
        return getTask().log();
    }

    @Override
    public ZeidonLogger dblog()
    {
        return getTask().dblog();
    }

    @Override
    public View activateOiFromFile( String lodDefName,
                                    String filename )
    {
        return activateOiFromFile( lodDefName, filename, null );
    }

    @Override
    public View activateOiFromFile( String lodDefName,
                                    String filename,
                                    EnumSet<ActivateFlags> control) throws UnknownLodDefException
    {
        LodDef lodDef = getApplication().getLodDef( getTask(), lodDefName );
        return activateOiFromFile( lodDef, filename, control );
    }

    @Override
    public View activateOiFromFile( LodDef lodDef,
                                    String filename,
                                    EnumSet<ActivateFlags> control )
    {
        return new DeserializeOi( this )
                        .fromFile( filename )
                        .setLodDef( lodDef )
                        .setFlags( control )
                        .activateFirst();
    }

    @Override
    public List<View> activateOisFromStream( DeserializeOi options ) throws UnknownLodDefException
    {
        try
        {
            StreamReader activator = options.getStreamReader();
            if ( activator == null )
            {
                switch ( options.getFormat() )
                {
                    case POR:
                        activator = new ActivateOiFromPorStream( );
                        break;

                    case JSON:
                        activator = new ActivateOisFromJsonStream( );
                        break;

                    case XML:
                        activator= new ActivateOisFromXmlStream();
                        break;

                    case CSV:
                        activator= new ActivateOisFromCsv();
                        break;

                    default:
                        throw new ZeidonException( "Unknown stream type %s", options.getFormat() );
                }
            }

            return activator.readFromStream( options );
        }
        finally
        {
            if ( options.isCloseStream() )
                IOUtils.closeQuietly( options.getInputStream() );
        }
    }

    /**
     * Take a group name and normalize it by removing any extraneous characters.
     * @param group
     * @return
     */
    private String normalizeGroup( String group )
    {
        if ( group.startsWith( "[" ) )
            group = group.substring( 1, group.length() - 1 ); // Remove [ and ]

        return group;
    }

    @Override
    public String readZeidonConfig(String group, String key)
    {
        return readZeidonConfig( group, key, null );
    }

    @Override
    public String readZeidonConfig(String group, String key, String defaultValue)
    {
        String g = normalizeGroup( group );
        return getObjectEngine().getZeidonPreferences( getApplication() ).get( g, key, defaultValue );
    }

    @Override
    public String readZeidonConfig(Application application, String group, String key, String defaultValue)
    {
        String g = normalizeGroup( group );
        return getObjectEngine().getZeidonPreferences( application ).get( g, key, defaultValue );
    }

    @Override
    public String readZeidonConfig( String application, String group, String key, String defaultValue)
    {
        String g = normalizeGroup( group );
        return getObjectEngine().getZeidonPreferences( getApplication( application ) ).get( g, key, defaultValue );
    }

    @Override
    public String getTempDirectory()
    {
        return getTask().getTempDirectory();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#getCacheMap(java.lang.Class)
     */
    @Override
    synchronized public <T> T getCacheMap(Class<T> key)
    {
        if ( cacheMap == null )
            cacheMap = new CacheMapImpl();

        return cacheMap.getCacheMap( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#putCacheMap(java.lang.Class, java.lang.Object)
     */
    @Override
    synchronized public <T> T putCacheMap(Class<T> key, T value)
    {
        if ( cacheMap == null )
            cacheMap = new CacheMapImpl();

        return cacheMap.putCacheMap( key, value );
    }

    @Override
    public DeserializeOi deserializeOi()
    {
        return new DeserializeOi( this );
    }
}
