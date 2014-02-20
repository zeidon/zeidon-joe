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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.EnumSet;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.ActivateFlags;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Blob;
import com.quinsoft.zeidon.CacheMap;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.UnknownViewOdException;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.utils.BufferedBinaryStreamReader;
import com.quinsoft.zeidon.utils.CacheMapImpl;
import com.quinsoft.zeidon.utils.JoeUtils;

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
    private final CacheMap             cacheMap = new CacheMapImpl();

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
    public ViewImpl activateEmptyObjectInstance(String viewOdName) throws UnknownViewOdException
    {
        ViewOd viewOd = getApplication().getViewOd( getTask(), viewOdName );
        return activateEmptyObjectInstance( viewOd );
    }

    @Override
    public JavaObjectEngine getObjectEngine()
    {
        return getTask().getObjectEngine();
    }

    @Override
    public ViewImpl activateEmptyObjectInstance( String viewOdName, Application app ) throws UnknownViewOdException
    {
        ViewOd viewOd = app.getViewOd( getTask(), viewOdName );
        return activateEmptyObjectInstance( viewOd );
    }

    @Override
    public ViewImpl activateEmptyObjectInstance( String viewOdName, String appName ) throws UnknownViewOdException
    {
        ViewOd viewOd = getApplication( appName ).getViewOd( getTask(), viewOdName );
        return activateEmptyObjectInstance( viewOd );
    }

    @Override
    public View activateEmptyObjectInstance( String viewOdName, TaskQualification taskQual ) throws UnknownViewOdException
    {
        ViewOd viewOd = taskQual.getApplication().getViewOd( getTask(), viewOdName );
        return activateEmptyObjectInstance( viewOd );
    }

    @Override
    public ViewImpl activateEmptyObjectInstance(ViewOd viewOd)
    {
        ViewImpl view = new ViewImpl( getTask(), viewOd );
        return view;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Task#activateObjectInstance(java.lang.String)
     */
    @Override
    public View activateObjectInstance(String viewOdName, View qual, EnumSet<ActivateFlags> control ) throws UnknownViewOdException
    {
        ViewOd viewOd = getApplication().getViewOd( getTask(), viewOdName );
        ActivateOptions options = new ActivateOptions( getTask() );
        options.setViewOd( viewOd );
        options.setQualificationObject( qual );
        options.setActivateFlags( control );
        return activateObjectInstance( options );
    }

    @Override
    public View activateObjectInstance(ViewOd viewOd, View qual, EnumSet<ActivateFlags> control )
    {
        ActivateOptions options = new ActivateOptions( getTask() );
        options.setViewOd( viewOd );
        options.setQualificationObject( qual );
        options.setActivateFlags( control );
        return activateObjectInstance( options );
    }

    @Override
    public View activateObjectInstance(ViewOd viewOd, View qual, ActivateOptions options )
    {
        options.setViewOd( viewOd );
        options.setQualificationObject( qual );
        return activateObjectInstance( options );
    }

    @Override
    public  View activateObjectInstance( ActivateOptions options ) throws UnknownViewOdException
    {
        ActivateObjectInstance activator = new ActivateObjectInstance( getTask(), options );
        return activator.activate();
    }

    @Override
    public View activateObjectInstance( String viewOdName, View qual, ActivateOptions options )
    {
        ViewOd viewOd = getApplication().getViewOd( getTask(), viewOdName );
        return activateObjectInstance( viewOd, qual, options );
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
    public ViewImpl activateOiFromFile(String viewOdName,
                                       String filename )
    {
        return activateOiFromFile( viewOdName, filename, null );
    }

    @Override
    public ViewImpl activateOiFromFile(String viewOdName,
                                       String filename,
                                       EnumSet<ActivateFlags> control) throws UnknownViewOdException
    {
        ViewOd viewOd = getApplication().getViewOd( getTask(), viewOdName );
        return activateOiFromFile( viewOd, filename, control );
    }

    @Override
    public ViewImpl activateOiFromFile(String viewOdName,
                                       Application app,
                                       String filename,
                                       EnumSet<ActivateFlags> control) throws UnknownViewOdException
    {
        ViewOd viewOd = app.getViewOd( getTask(), viewOdName );
        return activateOiFromFile( viewOd, filename, control );
    }

    @Override
    public ViewImpl activateOiFromFile(String viewOdName,
                                       TaskQualification appQualView,
                                       String filename,
                                       EnumSet<ActivateFlags> control)
    {
        return activateOiFromFile( viewOdName, appQualView.getApplication(), filename, control );
    }

    @Override
    public ViewImpl activateOiFromFile(ViewOd viewOd,
                                       String filename,
                                       EnumSet<ActivateFlags> control)
    {
        File file = JoeUtils.getFile( filename );
        if ( !file.exists() )
            throw new ZeidonException("File %s does not exist", filename );

        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream( file );
            return activateOiFromStream( viewOd, inputStream, control );
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    @Override
    public View activateOiFromBlob(Application application, Blob blob, EnumSet<ActivateFlags> control)
    {
        InputStream inputStream = null;
        try
        {
            inputStream = new ByteArrayInputStream( blob.getBytes() );
            ActivateOiFromStream activator = new ActivateOiFromStream(this, application, inputStream, control );
            ViewImpl v = activator.read();
            assert v.getViewOd().getApplication() == application;
            return v;
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }


    @Override
    public ViewImpl activateOiFromStream(String viewOdName, InputStream stream )
    {
        return activateOiFromStream( viewOdName, stream, null );
    }

    @Override
    public ViewImpl activateOiFromStream(String viewOdName,
                                       InputStream stream,
                                       EnumSet<ActivateFlags> control) throws UnknownViewOdException
    {
        ViewOd viewOd = getApplication().getViewOd( getTask(), viewOdName );
        return activateOiFromStream( viewOd, stream, control );
    }

    @Override
    public ViewImpl activateOiFromStream(String viewOdName,
                                       Application app,
                                       InputStream stream,
                                       EnumSet<ActivateFlags> control) throws UnknownViewOdException
    {
        ViewOd viewOd = app.getViewOd( getTask(), viewOdName );
        return activateOiFromStream( viewOd, stream, control );
    }

    @Override
    public ViewImpl activateOiFromStream(String viewOdName,
                                       TaskQualification appQualView,
                                       InputStream stream,
                                       EnumSet<ActivateFlags> control)
    {
        return activateOiFromStream( viewOdName, appQualView.getApplication(), stream, control );
    }

    @Override
    public ViewImpl activateOiFromStream( ViewOd viewOd,
                                          InputStream stream,
                                          EnumSet<ActivateFlags> control )
    {
        try
        {
            ActivateOiFromStream activator = new ActivateOiFromStream(this, viewOd.getApplication(), stream, control );
            activator.setViewOd( viewOd ); // This will allow us to ignore the ViewOD name listed in the stream.
            ViewImpl v = activator.read();
            assert v.getViewOd() == viewOd;
            return v;
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    @Override
    public ViewImpl activateOiFromStream( InputStream stream,
                                          EnumSet<ActivateFlags> control )
    {
        try
        {
            ActivateOiFromStream activator = new ActivateOiFromStream(this, getTask().getApplication(), stream, control );
            ViewImpl v = activator.read();
            return v;
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    @Override
    public ViewImpl activateOiFromStream( String viewOdName, BufferedBinaryStreamReader stream ) throws UnknownViewOdException
    {
        try
        {
            ActivateOiFromStream activator = new ActivateOiFromStream(this, getTask().getApplication(), null, null );
            activator.setStreamReader( stream );
            activator.setViewOd( getTask().getApplication().getViewOd( getTask(), viewOdName ) );
            ViewImpl v = activator.read();
            return v;
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    @Override
    public ViewImpl activateOiFromStream( String viewOdName,
                                          String applicationName,
                                          BufferedBinaryStreamReader stream ) throws UnknownViewOdException
    {
        try
        {
            ActivateOiFromStream activator = new ActivateOiFromStream(this, getTask().getApplication(), null, null );
            activator.setStreamReader( stream );
            activator.setViewOd( getTask().getApplication( applicationName ).getViewOd( getTask(), viewOdName ) );
            ViewImpl v = activator.read();
            return v;
        }
        catch ( Throwable e )
        {
            throw ZeidonException.wrapException( e );
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

    @Override
    public View activateOiFromJsonStream( InputStream stream, EnumSet<ActivateFlags> control )
    {
        ActivateOiFromJsonStream activator = new ActivateOiFromJsonStream( getTask(), stream, control );
        ViewImpl v = activator.read();
        return v;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#getCacheMap(java.lang.Class)
     */
    @Override
    public <T> T getCacheMap(Class<T> key)
    {
        return cacheMap.getCacheMap( key );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.CacheMap#putCacheMap(java.lang.Class, java.lang.Object)
     */
    @Override
    public <T> T putCacheMap(Class<T> key, T value)
    {
        return cacheMap.putCacheMap( key, value );
    }
}
