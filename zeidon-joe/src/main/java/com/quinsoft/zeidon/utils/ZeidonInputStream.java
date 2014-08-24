package com.quinsoft.zeidon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A forwarding wrapper of InputStream that includes a string describing
 * the source of the input stream.
 *
 * @author dgc
 *
 */
public class ZeidonInputStream extends InputStream
{

    private final InputStream source;
    private final String description;

    /**
     *
     */
    public ZeidonInputStream( InputStream sourceInputStream, String desc )
    {
        super();
        source = sourceInputStream;
        description = desc;
    }

    @Override
    public int read() throws IOException
    {
        return source.read();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#available()
     */
    @Override
    public int available() throws IOException
    {
        return source.available();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#close()
     */
    @Override
    public void close() throws IOException
    {
        source.close();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#mark(int)
     */
    @Override
    public void mark( int readlimit )
    {
        source.mark( readlimit );
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#markSupported()
     */
    @Override
    public boolean markSupported()
    {
        return source.markSupported();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#read(byte[], int, int)
     */
    @Override
    public int read( byte[] b, int off, int len ) throws IOException
    {
        int i = source.read( b, off, len );
        return i;
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#read(byte[])
     */
    @Override
    public int read( byte[] b ) throws IOException
    {
        return source.read( b );
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#reset()
     */
    @Override
    public synchronized void reset() throws IOException
    {
        source.reset();
    }

    /* (non-Javadoc)
     * @see java.io.InputStream#skip(long)
     */
    @Override
    public long skip( long n ) throws IOException
    {
        return source.skip( n );
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return "ZeidonInputStream: " + description;
    }

    static public ZeidonInputStream create( File file ) throws FileNotFoundException
    {
        return new ZeidonInputStream ( new FileInputStream( file ), file.getAbsolutePath() );
    }

    static public ZeidonInputStream create( URL url ) throws IOException
    {
        return new ZeidonInputStream ( url.openStream(), url.toString() );
    }

    static public ZeidonInputStream create( ClassLoader classLoader, String resource )
    {
        InputStream is = classLoader.getResourceAsStream( resource );
        if ( is == null )
            return null;

        String desc = String.format( "Resource '%s' from classLoader %s", resource, classLoader.toString() );
        return new ZeidonInputStream ( is, desc );
    }
}
