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
package com.quinsoft.zeidon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.ZeidonInputStream;

/**
 * Encapsulates all options available for activating OI's from streams and offers
 * convenience methods activate() and activateFirst().
 *
 * @author dgc
 *
 */
public class DeserializeOi
{
    private final Task task;

    private ViewOd      viewOd;
    private InputStream inputStream;
    private String      resourceName;
    private EnumSet<ActivateFlags> flags = ActivateFlags.MULTIPLE;
    private StreamFormat format;
    private Application application;
    private StreamReader streamReader;
    private String       version;

    /**
     * This is a set of ViewEntities that we will allow to create dynamic
     * work entities if an attribute in the stream does not exist in the
     * entity.
     */
    private Set<String> allowDynamicAttributes;

    /**
     * If true, then automatically close the stream after activating.
     * We will assume it's true unless the user explicitly sets the stream.
     */
    private boolean closeStream = true;

    public DeserializeOi( TaskQualification task )
    {
        this.task = task.getTask();
    }

    /**
     * @return the inputStream
     */
    public InputStream getInputStream()
    {
        return inputStream;
    }

    /**
     * Sets the inputStream from whence the OI(s) will be loaded.
     *
     * Note: the resource stream will be automatically closed by the activate unless it is
     * overridden by calling setCloseStream( false ).
     *
     * @param inputStream the inputStream to set
     * @return
     */
    public DeserializeOi fromInputStream( InputStream inputStream )
    {
        this.inputStream = inputStream;
        if ( inputStream instanceof ZeidonInputStream )
            setFormatFromFilename( ((ZeidonInputStream) inputStream).getDescription(), true );

        return this;
    }

    /**
     * Set the input stream by opening the resource.  The resource can be either a
     * filename or a resource name on the classpath.
     *
     * @param resourceName
     * @return
     */
    public DeserializeOi fromResource( String resourceName )
    {
        this.inputStream = JoeUtils.getInputStream( task, resourceName );
        if ( inputStream == null )
            throw new ZeidonException( "Resource %s not found", resourceName );

        this.resourceName = resourceName;
        setFormatFromFilename( resourceName, true );
        return this;
    }

    public DeserializeOi fromAttribute( AttributeInstance attribute )
    {
        return fromString( attribute.getString() );
    }

    /**
     * Set the input stream by opening the file.
     *
     * @param resourceName
     * @return
     */
    public DeserializeOi fromFile( String filename )
    {
        inputStream = JoeUtils.getInputStream( task, filename );
        if ( inputStream == null )
            throw new ZeidonException( "Filename %s not found", filename );

        this.resourceName = filename;
        setFormatFromFilename( resourceName, true );
        return this;
    }

    /**
     * Set the input stream by opening the file.
     *
     * @param resourceName
     * @return
     */
    public DeserializeOi fromFile( File file )
    {
        try
        {
            resourceName = file.getAbsolutePath();
            inputStream = new FileInputStream( file );
            setFormatFromFilename( resourceName, true );
            return this;
        }
        catch ( FileNotFoundException e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( resourceName );
        }
    }

    /**
     * Sets the input stream to be a string..
     *
     * overridden by calling setCloseStream( false ).
     *
     * @param resourceName
     * @return
     */
    public DeserializeOi fromString( String inputString )
    {
        try
        {
            inputStream = IOUtils.toInputStream( inputString, "UTF-8");
            return this;
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    public List<View> activate()
    {
        return task.activateOisFromStream( this );
    }

    /**
     * Activates the OI(s) from the stream and returns the first one.
     *
     * @return
     */
    public View activateFirst()
    {
        List<View> v = task.activateOisFromStream( this );
        return v.get( 0 );
    }

    /**
     * @return the viewOd
     */
    public ViewOd getViewOd()
    {
        return viewOd;
    }

    /**
     * @param viewOd the viewOd to set
     * @return
     */
    public DeserializeOi setViewOd( ViewOd viewOd )
    {
        this.viewOd = viewOd;
        return this;
    }

    public DeserializeOi setViewOd( String viewOdName )
    {
        viewOd = getApplication().getViewOd( getTask(), viewOdName );
        return this;
    }

    /**
     * Set the format depending on the extension of filename.
     *
     * @param filename
     * @param ifNull if true only set format if it is null.
     * @return
     */
    private DeserializeOi setFormatFromFilename( String filename, boolean ifNull )
    {
        if ( ifNull && format != null )
            return this;

        for ( StreamFormat f : StreamFormat.values() )
        {
            if ( f.matches( filename ) )
            {
                format = f;
                break;
            }
        }

        return this;
    }

    public DeserializeOi setFormat( StreamFormat format )
    {
        this.format = format;
        return this;
    }

    /**
     * @return the format
     */
    public StreamFormat getFormat()
    {
        // If format hasn't been set we'll default to POR.
        if ( format == null )
            return StreamFormat.POR;

        return format;
    }

    public Task getTask()
    {
        return task;
    }

    public Application getApplication()
    {
        if ( application != null )
            return application;

        if ( viewOd != null )
            return viewOd.getApplication();

        return task.getApplication();
    }

    public DeserializeOi setApplication( Application application )
    {
        this.application = application;
        return this;
    }

    /**
     * Add data about the activate options to the exception.
     *
     * @param e
     * @return
     */
    public ZeidonException decorateException( ZeidonException e )
    {
        if ( viewOd != null )
            e.prependViewOd( viewOd );

        if ( inputStream instanceof ZeidonInputStream )
            e.prependFilename( ((ZeidonInputStream) inputStream).getDescription() );
        else
        if ( resourceName != null )
            e.prependFilename( resourceName );

        return e;
    }

    public EnumSet<ActivateFlags> getFlags()
    {
        return flags;
    }

    public DeserializeOi setFlags( EnumSet<ActivateFlags> flags )
    {
        if ( flags != null )
            this.flags = flags;

        return this;
    }

    public DeserializeOi setFlags( Integer control )
    {
        if ( control == null )
            return this;

        return setFlags( ActivateFlags.convertLongFlags( control ) );
    }

    public DeserializeOi asJson()
    {
        format = StreamFormat.JSON;
        return this;
    }

    public DeserializeOi asXml()
    {
        format = StreamFormat.XML;
        return this;
    }

    /**
     * If true, then automatically close the stream after activating.
     */
    public boolean isCloseStream()
    {
        return closeStream;
    }

    public DeserializeOi closeStream( boolean closeStream )
    {
        this.closeStream = closeStream;
        return this;
    }

    public DeserializeOi allowDynamicAttributesFor( String entityName )
    {
        if ( allowDynamicAttributes == null )
            allowDynamicAttributes = new HashSet<String>();

        allowDynamicAttributes.add( entityName );
        return this;
    }

    public Set<String> getAllowableDynamicEntities()
    {
        return allowDynamicAttributes;
    }

    public StreamReader getStreamReader()
    {
        return streamReader;
    }

    public void using( StreamReader streamReader )
    {
        this.streamReader = streamReader;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }
}
