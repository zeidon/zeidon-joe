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

    private boolean closeWriter = true;
    private Writer  writer;
    private StreamFormat format;
    private String resourceName;
    private EnumSet<WriteOiFlags> flags = EnumSet.noneOf( WriteOiFlags.class );
    private StreamWriter streamWriter;

    private AttributeInstance targetAttribute;

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

    public SerializeOi toFile( String filename )
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
        setFormatFromFilename( resourceName );
        return this;
    }

    public SerializeOi toWriter( Writer writer )
    {
        this.writer = writer;
        closeWriter = false;  // We'll assume the caller will close it.
        resourceName = "*External writer*";
        return this;
    }

    public SerializeOi toAttribute( AttributeInstance attribute )
    {
        targetAttribute = attribute;
        toStringWriter();
        return this;
    }

    public String getSourceName()
    {
        return resourceName;
    }

    /**
     * Write the JSON to a StringWriter.  The resulting string can be retrieved
     * using getJsonString();
     * @return
     */
    public SerializeOi toStringWriter()
    {
        writer = new StringWriter();
        resourceName = "*String*";
        return this;
    }

    private void close()
    {
        if ( closeWriter )
        {
            IOUtils.closeQuietly( writer );
            closeWriter = false;
        }
    }

    public String getString()
    {
        try
        {
            if ( writer == null )
                throw new ZeidonException( "No output destination specified." );

            if ( writer instanceof StringWriter )
                return writer.toString();

            throw new ZeidonException( "Writer is not an instance of StringWriter.  Class = %s",
                                       writer.getClass().getCanonicalName() );
        }
        catch ( Exception e )
        {
            close();
            throw e;
        }
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

    public SerializeOi write( View view, View... views )
    {
        viewList.add( view );
        if ( views != null && views.length > 0 )
        {
            for ( View v : views )
                viewList.add( v );
        }

        return write();
    }

    public SerializeOi write( Collection<? extends View> views )
    {
        viewList.addAll( views );
        return write();
    }

    public List<View> getViewList()
    {
        return viewList;
    }

    public Writer getWriter()
    {
        return writer;
    }

    public SerializeOi write()
    {
        try
        {
            if ( viewList.size() == 0 )
                throw new ZeidonException( "No views have been selected to write" );

            if ( writer == null )
                throw new ZeidonException( "No output destination specified." );

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

            streamWriter.writeToStream( this );

            if ( targetAttribute != null )
                targetAttribute.setValue( getString() );

            return this;
        }
        finally
        {
            close();
        }
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
}
