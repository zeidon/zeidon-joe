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
/**
 *
 */
package com.quinsoft.zeidon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;

/**
 * Generic logic to read Zeidon Object Instances from a file or stream.  The OI is expected to
 * be in "portable file" format.
 *
 * @author DG
 *
 */
public class PortableFileReader
{
    /**
     * Long strings or strings with control chars (like '\n') are stored as blobs.
     * This char indicates that a string is stored as a blob.
     */
    public static final char   STRING_STORED_AS_BLOB = 0x1f;
    public static final String STRING_STORED_AS_BLOB_REGEX = "\\x1f";

    private static final String EMPTY_STRING = "";

    public interface PortableFileEntityHandler
    {
        void startFile( PortableFileReader reader, String viewOdName );
        PortableFileAttributeHandler createEntity( PortableFileReader reader, int level, long flags );
        void endEntityAttributes( PortableFileReader reader, String entityName, int currentLevel );
        void endEntity( PortableFileReader reader, PortableFileAttributeHandler handler, int currentLevel );
        void link( PortableFileReader reader, int target, int source );
        void endFile();

        /**
         * An entity handler that does nothing.  Makes it easier to create subclasses.
         *
         * @author DG
         */
        public abstract class NullEntityHandler implements PortableFileEntityHandler
        {
            @Override
            public void startFile(PortableFileReader reader, String viewOdName)
            {
            }

            @Override
            public PortableFileAttributeHandler createEntity(PortableFileReader reader, int level, long flags)
            {
                return null;
            }

            @Override
            public void endEntity(PortableFileReader reader, PortableFileAttributeHandler handler, int currentLevel)
            {
            }

            @Override
            public void endEntityAttributes(PortableFileReader reader, String entityName, int currentLevel)
            {
            }

            @Override
            public void endFile()
            {
            }

            @Override
            public void link(PortableFileReader reader, int target, int source)
            {
            }
        }
    }

    public interface PortableFileAttributeHandler
    {
        void setAttribute( PortableFileReader reader );

        /**
         * An attribute handler that ignores all attributes.
         * @author DG
         */
        public class NullAttributeHandler implements PortableFileAttributeHandler
        {
            @Override
            public void setAttribute(PortableFileReader reader)
            {
            }
        }
    }

    private BufferedBinaryStreamReader reader;
    private String line;
    private String attributeName;
    private String attributeValue;
    private byte[] attributeValueBytes;
    private int    lineNumber;
    private PortableFileAttributeHandler attributeHandler;
    private boolean incremental;
    private boolean compressed;
    boolean optimisticOIs;
    private boolean attribFlags;
    private long entityFlags;
    private long attributeFlags;
    private final InputStream inputStream;
    private final PortableFileEntityHandler entityHandler;
    private final ZeidonLogger logger;

    /**
     * Instantiate a reader to parse an OI from a stream.  Note that the inputStream is *NOT* closed.
     *
     * @param logger
     * @param inputStream
     * @param entityHandler
     */
    public PortableFileReader( ZeidonLogger logger, InputStream inputStream, PortableFileEntityHandler entityHandler )
    {
        this.entityHandler = entityHandler;
        this.inputStream = inputStream;
        this.logger = logger;
    }

    public BufferedBinaryStreamReader getStreamReader()
    {
        return reader;
    }

    /**
     * Set the buffered stream reader.  This allows a caller to read multiple OIs from
     * a single stream.  Explicitly setting the stream will prevent this logic from
     * closing the stream.
     *
     * @param reader
     */
    public void setStreamReader( BufferedBinaryStreamReader reader )
    {
        this.reader = reader;
    }

    public void readEntities()
    {
        boolean closeReader = false;
        if ( reader == null )
        {
            reader = new BufferedBinaryStreamReader( inputStream );
            closeReader = true;
        }

        lineNumber = 0;
        try
        {
            ArrayList<PortableFileAttributeHandler> attributeHandlerStack =
                                        new ArrayList<PortableFileAttributeHandler>();

            int currentLevel = 0;
            String currentEntityName = null;

            // If we are reading multiple OIs in a single stream we expect to find 'ZEND'
            // to indicate one OI has ended and the next one begins.  Set endOfCurrentOi
            // to true so we can exit activating the current OI.
            boolean endOfCurrentOi = false;

            while ( ! endOfCurrentOi && ( line = reader.readLine() ) != null )
            {
                lineNumber++;

                if ( StringUtils.isBlank( line ) )
                    continue;

                String values[] = splitLine( line, ' ' );
                attributeName = values[0];
                attributeValue = values[1];

                switch ( attributeName.charAt( 0 ) )
                {
                    case 'e':
                        attributeName = attributeName.substring( 1 );
                        values = splitLine( attributeValue, ',' );
                        int level = Integer.parseInt( values[0] );

                        if ( currentLevel > 0 )
                            entityHandler.endEntityAttributes( this, currentEntityName, currentLevel );

                        if ( values[1] != EMPTY_STRING )
                            entityFlags = Long.parseLong( values[1] );

                        currentEntityName = attributeName;

                        while ( currentLevel >= level )
                        {
                            attributeHandler = attributeHandlerStack.get( currentLevel );
                            entityHandler.endEntity( this, attributeHandler, currentLevel );
                            currentLevel--;
                        }

                        attributeHandler = entityHandler.createEntity( this, level, entityFlags );

                        if ( level >= attributeHandlerStack.size() )
                            attributeHandlerStack.add( attributeHandler );
                        else
                            attributeHandlerStack.set( level, attributeHandler );

                        currentLevel = level;
                        break;

                    case 'a':
                        if ( incremental || attribFlags )
                        {
                            values = splitLine( attributeName, ',' );
                            if ( values[1] != EMPTY_STRING )
                            {
                                attributeName = values[0];
                                attributeFlags = Long.parseLong( values[1] );
                            }
                        }
                        else
                            attributeFlags = 0;

                        attributeName = attributeName.substring( 1 );
                        if ( attributeHandler != null )
                        {
                            try
                            {
                                if ( attributeValue.length() > 0 && attributeValue.charAt( 0 ) == STRING_STORED_AS_BLOB )
                                {
                                    attributeValue = attributeValue.substring(1); // Skip past control char.
                                    int length = Integer.parseInt( attributeValue );
                                    attributeValueBytes = new byte[ length ];
                                    try
                                    {
                                        int read = reader.read( attributeValueBytes, length );
                                        assert read == length;
                                        attributeValue = new String( attributeValueBytes );
                                    }
                                    catch ( IOException e )
                                    {
                                        throw new ZeidonException( "Error reading binary data from portable file" );
                                    }

                                    lineNumber += StringUtils.countMatches( attributeValue, "\n" ) + 1;
                                }
                                attributeHandler.setAttribute( this );
                            }
                            catch ( Throwable e )
                            {
                                throw ZeidonException.prependMessage( e, "Attribute Name '%s'", attributeName );
                            }
                        }

                        break;

                    case 'i':
                        int target = Integer.parseInt( attributeName.substring( 1 ) );
                        int source = Integer.parseInt( attributeValue );

                        entityHandler.link( this, target, source );
                        break;

                    case 'Z':
                        if ( ! "ZEND".equals( line ) )
                            throw new ZeidonException("Expecting 'ZEND' but got %s instead", line );

                        // Set flag to break out of reader loop.
                        endOfCurrentOi = true;
                        break;

                    case 'z':
                        boolean erDate;
                        attributeHandlerStack.add( null );

                        if ( lineNumber > 2 )
                            throw new ZeidonException( "Unexpected beginning of new OI in stream on line %d", lineNumber );

                        // Read the flags from the header.  Sample header:
                        // z1100-Zeidon    MEMPLOY  mEmploy  07/12/09   20:38:04 1.0a2
                        erDate        = line.charAt( 1 ) == '1';
                        incremental   = line.charAt( 2 ) == '1';
                        compressed    = line.charAt( 3 ) == '1';
                        optimisticOIs = line.charAt( 4 ) == '1';
                        attribFlags   = line.charAt( 5 ) == '1';
                        if ( compressed )
                            throw new ZeidonException("Reading compressed streams not supported yet.");

                        String viewOdName = StringUtils.split( line.substring( 24 ), " ", 3 )[0];
                        entityHandler.startFile( this, viewOdName );

                        if ( erDate && isIncremental() )
                        {
                            // File contains the ER date so read it.  We don't support compressed
                            // OIs yet so we don't do anything with it.
                            reader.readLine();
                        }

                        break;

                    case ';':
                        // Comment line, so just ignore.
                        break;

                    case 'm':
                        //TODO: Add support for meta flags.
                        break;

                    default:
                        throw new ZeidonException( "Line %d doesn't start with e, a, i, or z\n==> %s",
                                                    lineNumber, line );
                }
            } // while not EOF...

            if ( currentLevel > 0 )
                entityHandler.endEntityAttributes( this, currentEntityName, currentLevel );

            while ( currentLevel > 0 )
            {
                attributeHandler = attributeHandlerStack.get( currentLevel );
                entityHandler.endEntity( this, attributeHandler, currentLevel );
                currentLevel--;
            }

            entityHandler.endFile();
        }
        catch ( Throwable e )
        {
            // Add line number to the message.
            throw ZeidonException.prependMessage( e, "line:%d", lineNumber );
        }
        finally
        {
            if ( closeReader )
            {
                IOUtils.closeQuietly( reader );
                reader = null;
            }
        }
    }

    /**
     * Splits the line into two parts.  First part is non-spaces, second part is after
     * group of delimiters.
     *
     *     Ex:  "ABC   123 456 " returns ["ABC", "123 456 "]
     *
     * Note: this does not validate that 'line' is non-blank.
     *
     * @param line2
     * @return
     */
    private String[] splitLine( String line, char delimiter )
    {
        String[] array = new String[2];

        int idx = 0;

        // Skip past whitespaces at beginning of the line.
        while ( line.charAt( idx ) == ' ' )
            idx++;

        // Find first set of chars up to first delimiter.
        while ( idx < line.length() && line.charAt( idx ) != delimiter )
            idx++;

        array[0] = line.substring( 0, idx );

        // Now skip over all consecutive delimiters.
        while ( idx < line.length() && line.charAt( idx ) == delimiter )
            idx++;

        if ( idx == line.length() )
            array[1] = EMPTY_STRING;
        else
            array[1] = line.substring( idx );

        return array;
    }

    public String getLine()
    {
        return line;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public String getAttributeValue()
    {
        return attributeValue;
    }

    public byte[] getAttributeValueAsBytes()
    {
        return attributeValueBytes;
    }

    public long getEntityFlags()
    {
        return entityFlags;
    }

    public long getAttributeFlags()
    {
        return attributeFlags;
    }

    public boolean isIncremental()
    {
        return incremental;
    }

    public ZeidonLogger getLogger()
    {
        return logger;
    }

    public PortableFileAttributeHandler getAttributeHandler()
    {
        return attributeHandler;
    }

    public void setAttributeHandler(PortableFileAttributeHandler attributeHandler)
    {
        this.attributeHandler = attributeHandler;
    }

    public static void ReadPortableFile( Task task, String filename, ZeidonLogger logger, PortableFileEntityHandler entityHandler )
    {
        try
        {
            logger.debug( "Reading portable file %s", filename );
            InputStream is = JoeUtils.getInputStream(task, filename, entityHandler.getClass().getClassLoader());
            if ( is == null )
                throw new ZeidonException( "Couldn't find file %s", filename );

            ReadPortableFile( is, logger, entityHandler );
        }
        catch ( Exception e )
        {
            // Add filename to exception.
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
    }

    public static void ReadPortableFile( InputStream inputStream, ZeidonLogger logger, PortableFileEntityHandler entityHandler )
    {
        try
        {
            PortableFileReader reader = new PortableFileReader( logger, inputStream, entityHandler );
            reader.readEntities();
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }
}
