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

import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.JoeUtils;
import com.quinsoft.zeidon.utils.ZeidonInputStream;

/**
 * Encapsulates all options available for activating OI's from streams and offers
 * convenience methods activate() and activateFirst().  Can deserialize an OI
 * from different sources and formats.  The standard example looks like:
 *
 * <pre><code>
 *       View stud2 = zencas.deserializeOi()
 *                           .fromResource( "/tmp/stud2.json" )
 *                           .setLodDef( "lStudDpt" )
 *                           .asJson()
 *                           .activateFirst();
 * </code></pre>
 *
 * Some configuration values can be implied from the other values or from the
 * the serialized stream.  The above sample can be simplified:
 * <pre><code>
 *       View stud2 = zencas.deserializeOi()
 *                           .fromResource( "/tmp/stud2.json" )
 *                           .activateFirst();
 * </code></pre>
 */
public class DeserializeOi
{
    private final Task task;

    private LodDef      lodDef;
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

    /**
     * Create a deserializer.  Client apps should use task.deserializeOi() instead
     * of creating one directly.
     *
     * @param task
     */
    public DeserializeOi( TaskQualification task )
    {
        this.task = task.getTask();
    }

    /**
     * Returns the input stream.
     *
     * @return the inputStream
     */
    public InputStream getInputStream()
    {
        return inputStream;
    }

    /**
     * Sets the inputStream from whence the OI(s) will be loaded.
     *
     * Note: the resource stream will be automatically closed by the activate method unless it is
     * overridden by calling setCloseStream( false ).
     *
     * @param inputStream the inputStream to set
     *
     * @return this
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
     * filename or a resource name on the classpath.  If the resource name is a file
     * then the format of the stream can be determined from the name of the file.
     * For example, if the file is "/tmp/myfile.json" then the format is assumed
     * to be JSON.  This can be overridden by calling setFormat().
     *
     * @param resourceName name of a file or a resource on the classpath.
     *
     * @return this
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

    /**
     * Get the stream from the Zeidon attribute.  The attribute must be retrievable
     * as a string.
     *
     * @param attribute Zeidon attribute.
     *
     * @return this
     */
    public DeserializeOi fromAttribute( AttributeInstance attribute )
    {
        return fromString( attribute.getString() );
    }

    /**
     * Set the input stream by opening the file.  the format of the stream can be determined from the name of the file.
     * For example, if the file is "/tmp/myfile.json" then the format is assumed
     * to be JSON.  This can be overridden by calling setFormat().
     *
     * This is a synonym for fromResource().
     *
     * @param resourceName name of the file.
     *
     * @return this
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
     * @param file the file
     *
     * @return this
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
     * Sets the input stream to be from a string.
     *
     * @param inputString the input string.
     *
     * @return this
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

    /**
     * Activate all the OIs from the stream and return a list of views.
     *
     * @return list of views in the stream.
     */
    public List<View> activate()
    {
        try
        {
            return task.activateOisFromStream( this );
        }
        catch ( Exception e )
        {
            ZeidonException ze = ZeidonException.wrapException( e );
            ze.prependFilename( resourceName );
            throw ze;
        }
    }

    /**
     * Activates the OI(s) from the stream and returns the first one.
     *
     * @return the first View from the stream.
     */
    public View activateFirst()
    {
        List<View> v = activate();
        return v.get( 0 );
    }

    /**
     * @return the lodDef
     */
    public LodDef getLodDef()
    {
        return lodDef;
    }

    /**
     * @param lodDef the lodDef to set
     * @return this
     */
    public DeserializeOi setLodDef( LodDef lodDef )
    {
        this.lodDef = lodDef;
        return this;
    }

    /**
     * Sets the LOD definition by name.
     *
     * @param lodDefName name of the LOD.
     *
     * @return this
     */
    public DeserializeOi setLodDef( String lodDefName )
    {
        lodDef = getApplication().getLodDef( getTask(), lodDefName );
        return this;
    }

    /**
     * Set the format depending on the extension of filename.
     *
     * @param filename
     * @param ifNull if true only set format if it is null.
     * @return this
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

    /**
     * Sets the format of the input stream.  Note that this can be determined if the
     * source stream is a file or filename.
     *
     * @param format
     *
     * @return this
     */
    public DeserializeOi setFormat( StreamFormat format )
    {
        this.format = format;
        return this;
    }

    public DeserializeOi setFormat( String format )
    {
        this.format = StreamFormat.valueOf( format );
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

        if ( lodDef != null )
            return lodDef.getApplication();

        return task.getApplication();
    }

    /**
     * Sets the application for this deserializer.  It is used to find the LodDef and
     * is only necessary to override the default application for the task.
     *
     * @param application the application
     *
     * @return this
     */
    public DeserializeOi setApplication( Application application )
    {
        this.application = application;
        return this;
    }

    /**
     * Sets the application for this deserializer.  It is used to find the LodDef and
     * is only necessary to override the default application for the task.
     *
     * @param appName the name of the application
     *
     * @return this
     */
    public DeserializeOi setApplication( String appName )
    {
        this.application = task.getApplication( appName );
        return this;
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

    /**
     * Specifies that the format of the input stream is JSON.
     *
     * @return this
     */
    public DeserializeOi asJson()
    {
        format = StreamFormat.JSON;
        return this;
    }

    /**
     * Specifies that the format of the input stream is XML.
     *
     * @return this
     */
    public DeserializeOi asXml()
    {
        format = StreamFormat.XML;
        return this;
    }

    /**
     * If true, then the stream will be automatically closed after activation.
     */
    public boolean isCloseStream()
    {
        return closeStream;
    }

    /**
     * If set to true then the stream will be automatically closed after activation.
     * Default is true.
     *
     * @param closeStream
     *
     * @return this
     */
    public DeserializeOi closeStream( boolean closeStream )
    {
        this.closeStream = closeStream;
        return this;
    }

    /**
     * If an unknown attribute name is found for the specified entity, instead of throwing
     * an error or ignoring it a dynamic attribute is created.
     *
     * @param entityName name of the entity that will accept dynamic attributes.
     *
     * @return this
     */
    public DeserializeOi allowDynamicAttributesFor( String entityName )
    {
        if ( allowDynamicAttributes == null )
            allowDynamicAttributes = new HashSet<String>();

        allowDynamicAttributes.add( entityName );
        return this;
    }

    /**
     * Returns list of entity names that allow dynamic attribute to be created.
     * @return
     */
    public Set<String> getAllowableDynamicEntities()
    {
        return allowDynamicAttributes;
    }

    /**
     * Returns the stream reader.
     *
     * @return the stream reader
     */
    public StreamReader getStreamReader()
    {
        return streamReader;
    }

    /**
     * Specifies a different stream reader than the default.
     *
     * @param streamReader
     */
    public void using( StreamReader streamReader )
    {
        this.streamReader = streamReader;
    }

    public String getVersion()
    {
        return version;
    }

    /**
     * Sets the expected version of the input stream and overrides the value in the stream.
     * This allows a deserializer to accept a stream that doesn't have a version specified
     * or overrides it.
     *
     * @param version expected version
     *
     * @return this
     */
    public DeserializeOi setVersion( String version )
    {
        this.version = version;
        return this;
    }
}
