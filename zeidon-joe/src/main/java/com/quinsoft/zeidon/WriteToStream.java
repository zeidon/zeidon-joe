/**
 *
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

import com.quinsoft.zeidon.standardoe.WriteOiToXmlStream;
import com.quinsoft.zeidon.standardoe.WriteOisToJsonStream;

/**
 * Encapsulates options for writing an OI to a file/writer and includes some
 * convenience methods for performing the write.
 *
 * @author dg
 *
 */
public class WriteToStream
{
    private final List<View> viewList;

    private boolean closeWriter = true;
    private Writer  writer;
    private StreamFormat format;
    private String resourceName;
    private EnumSet<WriteOiFlags> flags = EnumSet.noneOf( WriteOiFlags.class );

    public WriteToStream()
    {
        viewList = new ArrayList<>();
    }

    public WriteToStream toFile( String filename )
    {
        try
        {
            File file = new File( filename );
            writer = new FileWriter( file );
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }

        resourceName = filename;
        setFormatFromFilename( resourceName, true );
        return this;
    }

    /**
     * Write the JSON to a StringWriter.  The resulting string can be retrieved
     * using getJsonString();
     * @return
     */
    public WriteToStream toStringWriter()
    {
        writer = new StringWriter();
        resourceName = "*String*";
        return this;
    }

    public String getJsonString()
    {
        if ( writer == null )
            throw new ZeidonException( "No output destination specified." );

        if ( writer instanceof StringWriter )
            return writer.toString();

        throw new ZeidonException( "Writer is not an instance of StringWriter.  Class = %s",
                                   writer.getClass().getCanonicalName() );
    }

    /**
     * Set the format depending on the extension of filename.
     *
     * @param filename
     * @param ifNull if true only set format if it is null.
     * @return
     */
    private WriteToStream setFormatFromFilename( String filename, boolean ifNull )
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

    public WriteToStream setFormat( StreamFormat format )
    {
        this.format = format;
        return this;
    }

    public WriteToStream asJson()
    {
        format = StreamFormat.JSON;
        return this;
    }

    public WriteToStream asXml()
    {
        format = StreamFormat.XML;
        return this;
    }

    public WriteToStream addView( View view, View... views )
    {
        viewList.add( view );
        if ( views != null )
            viewList.addAll( viewList );

        return this;
    }

    public WriteToStream addViews( Collection<View> views )
    {
        viewList.addAll( views );
        return this;
    }

    public void write( View view, View... views )
    {
        viewList.add( view );
        if ( views != null && views.length > 0 )
        {
            for ( View v : views )
                viewList.add( v );
        }

        write();
    }

    public void write( Collection<View> views )
    {
        viewList.addAll( views );
        write();
    }

    public void write()
    {
        try
        {
            if ( viewList.size() == 0 )
                throw new ZeidonException( "No views have been selected to write" );

            if ( writer == null )
                throw new ZeidonException( "No output destination specified." );

            switch ( getFormat() )
            {
                case JSON:
                    WriteOisToJsonStream jsonWriter = new WriteOisToJsonStream( viewList, writer, this );
                    jsonWriter.writeToStream();
                    return;

                case XML:
                    if ( viewList.size() > 1 )
                        throw new ZeidonException( "Only one View may be written to a .XML file" );

                    WriteOiToXmlStream xmlWriter = new WriteOiToXmlStream( viewList.get( 0 ), writer, flags );
                    xmlWriter.writeToStream();
                    return;

                case POR:
                    if ( viewList.size() > 1 )
                        throw new ZeidonException( "Only one View may be written to a .POR file" );

                    viewList.get( 0 ).writeOi( writer, flags );
                    return;

                default:
                    throw new ZeidonException( "Unknown format", getFormat() );
            }
        }
        finally
        {
            if ( closeWriter )
                IOUtils.closeQuietly( writer );
        }
    }

    public EnumSet<WriteOiFlags> getFlags()
    {
        return flags;
    }

    public WriteToStream setFlags( EnumSet<WriteOiFlags> flags )
    {
        this.flags = flags;
        return this;
    }

    public WriteToStream withIncremental()
    {
        flags.add( WriteOiFlags.INCREMENTAL );
        return this;
    }

    /**
     * This turns off headers (e.g. .oimeta) when writing the OI.  This results in a simpler
     * JSON but it won't have incremental information.
     *
     * @return
     */
    public WriteToStream withoutHeaders()
    {
        flags.add( WriteOiFlags.NO_HEADER );
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
}
