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
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.standardoe.WriteOiToPorStream;
import com.quinsoft.zeidon.standardoe.WriteOiToXmlStream;
import com.quinsoft.zeidon.standardoe.WriteOisToJsonStream;
import com.quinsoft.zeidon.utils.WriteOisToJsonStreamNoIncrementals;

/**
 * Encapsulates options for writing an OI to a file/writer and includes some
 * convenience methods for performing the write.
 *
 * @author dg
 *
 */
public class SerializeOi
{
    private final List<View> viewList;

    private StreamFormat format;
    private String resourceName;
    private EnumSet<WriteOiFlags> flags = EnumSet.noneOf( WriteOiFlags.class );
    private StreamWriter streamWriter;

    public SerializeOi()
    {
        viewList = new ArrayList<>();
    }

    public SerializeOi( View view, View... views )
    {
        this();
        addView( view, views );
    }

    public SerializeOi( List<View> views )
    {
        this();
        addViews( views );
    }

    public String toTempFile()
    {
        if ( viewList.size() == 0 )
            throw new ZeidonException( "Specify at least one view before calling toTempFile()" );

        View view = viewList.get( 0 );
        String prefix = view.getLodDef().getName() + "_";
        FileWriter writer = null;
        try
        {

            File file = File.createTempFile( prefix, getFormat().getExtension() );
            writer = new FileWriter( file );
            resourceName = file.getAbsolutePath();
            view.log().debug( "Writing views to temp file %s", resourceName );
            write( writer );
            return resourceName;
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e );
        }
        finally
        {
            IOUtils.closeQuietly( writer );
        }
    }

    public String toFile( String filename )
    {
        FileWriter writer = null;
        try
        {
            File file = new File( filename );
            writer  = new FileWriter( file );
            resourceName = filename;
            setFormatFromFilename( resourceName );
            write( writer );
            return resourceName;
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
        finally
        {
            IOUtils.closeQuietly( writer );
        }
    }

    public Writer toWriter( Writer writer )
    {
        resourceName = "*External writer*";
        write( writer );
        return writer;
    }

    public void toAttribute( AttributeInstance attribute )
    {
        Writer writer = toStringWriter();
        write( writer );
        attribute.setValue( writer.toString() );
    }

    public String getResourceName()
    {
        return resourceName;
    }

    /**
     * Write the OI to a StringWriter.
     * 
     * @return
     */
    public Writer toStringWriter()
    {
        StringWriter writer = new StringWriter();
        resourceName = "*String*";
        write( writer );
        return writer;
    }

    /**
     * Set the format depending on the extension of filename.
     *
     * @param filename
     * @return
     */
    private SerializeOi setFormatFromFilename( String filename )
    {
        if ( format != null )
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

    public SerializeOi setFormat( StreamFormat format )
    {
        this.format = format;
        return this;
    }

    public SerializeOi setFormat( String format )
    {
        this.format = StreamFormat.valueOf( format );
        return this;
    }

    public SerializeOi asJson()
    {
        format = StreamFormat.JSON;
        return this;
    }

    public SerializeOi asXml()
    {
        format = StreamFormat.XML;
        return this;
    }

    public SerializeOi addView( View view, View... views )
    {
        viewList.add( view );
        if ( views != null )
        {
            for ( View v : views )
                viewList.add( v );
        }

        return this;
    }

    public SerializeOi addViews( Collection<? extends View> views )
    {
        viewList.addAll( views );
        return this;
    }

    public List<View> getViewList()
    {
        return viewList;
    }

    private StreamWriter getStreamWriter()
    {
        if ( streamWriter == null )
        {
            switch ( getFormat() )
            {
                case JSON:
                    if ( flags.contains( WriteOiFlags.INCREMENTAL ) )
                        streamWriter = new WriteOisToJsonStream();
                    else
                        streamWriter = new WriteOisToJsonStreamNoIncrementals();
                    break;

                case XML:
                    streamWriter = new WriteOiToXmlStream();
                    break;

                case POR:
                    streamWriter = new WriteOiToPorStream();
                    break;

                default:
                    throw new ZeidonException( "Unknown format", getFormat() );
            }
        }
        
        return streamWriter;
    }
    
    private void write( Writer writer )
    {
        if ( viewList.size() == 0 )
            throw new ZeidonException( "No views have been selected to write" );

        if ( writer == null )
            throw new ZeidonException( "No output destination specified." );

        getStreamWriter().writeToStream( this, writer );
    }

    public EnumSet<WriteOiFlags> getFlags()
    {
        return flags;
    }

    public SerializeOi setFlags( EnumSet<WriteOiFlags> flags )
    {
        if ( flags == null )
            flags = EnumSet.noneOf( WriteOiFlags.class );

        this.flags = flags;
        return this;
    }

    public SerializeOi setFlags( Long control )
    {
        if ( control == null )
            return setFlags( (EnumSet<WriteOiFlags>) null );

        return setFlags( WriteOiFlags.convertLongFlags( control ) );
    }

    public SerializeOi withIncremental()
    {
        flags.add( WriteOiFlags.INCREMENTAL );
        return this;
    }

    public SerializeOi using( StreamWriter streamWriter )
    {
        this.streamWriter = streamWriter;
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

    public boolean isCompressed()
    {
        return flags.contains( WriteOiFlags.COMPRESSED );
    }

    public SerializeOi setCompressed( boolean compressed )
    {
        if ( compressed )
            flags.add( WriteOiFlags.COMPRESSED );
        else
            flags.remove( WriteOiFlags.COMPRESSED );

        return this;
    }

    public SerializeOi compressed()
    {
        return setCompressed( true );
    }
}
