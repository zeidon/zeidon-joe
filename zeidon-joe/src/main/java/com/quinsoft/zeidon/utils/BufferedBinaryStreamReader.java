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
/**
 * 
 */
package com.quinsoft.zeidon.utils;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * A buffered reader that can read strings or bytes from a stream.  This is used
 * to read portable files from streams.
 * 
 * @author DG
 *
 */
public class BufferedBinaryStreamReader implements Closeable
{
    private static final byte[] CARRIAGE_RETURN = "\r".getBytes();
    private static final byte[] LINE_FEED       = "\n".getBytes();

    private static final int BUFFER_SIZE = 1000;
    private byte buffer[] = new byte[BUFFER_SIZE];
    private byte b[] = new byte[1];

    private final BufferedInputStream inputStream;

    public BufferedBinaryStreamReader(InputStream inputStream)
    {
        super();
        if ( inputStream instanceof BufferedInputStream )
            this.inputStream = (BufferedInputStream) inputStream;
        else
            this.inputStream = new BufferedInputStream( inputStream );
    }

    public String readLine() throws IOException
    {
        int eof;
        int bufferLth = 0;
        
        StringBuilder str = new StringBuilder();
        while ( ( eof = inputStream.read(b, 0, 1) ) != -1 )
        {
            if ( b[0] == CARRIAGE_RETURN[0] )
            {
                inputStream.read(); // Skip the \n
                break;
            }

            if ( b[0] == LINE_FEED[0] )
            {
                break;
            }

            buffer[ bufferLth++ ] = b[0];
            if ( bufferLth == BUFFER_SIZE )
            {
                String s = new String(buffer, "UTF8" );
                str.append( s );
                bufferLth = 0;
            }
        }

        if ( bufferLth > 0 )
        {
            String s = new String(buffer, 0, bufferLth, "UTF8" );
            str.append( s );
        }

        if ( eof == -1 && str.length() == 0 )
            return null;
        
        return str.toString();
    }
    
    public int read(byte[] buffer, int length) throws IOException
    {
        return inputStream.read( buffer, 0, length );
    }
    
    @Override
    public void close() throws IOException
    {
        inputStream.close();
    }
}
