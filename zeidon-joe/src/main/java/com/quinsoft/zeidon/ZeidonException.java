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
package com.quinsoft.zeidon;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.DataRecord;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * The base exception class for all Zeidon-specific exceptions.  This class has the following
 * conveniences over regular exceptions:
 *
 * - The exception message can be initialized using String.format parsing.  For example,
 *      new ZeidonException( "Line number: %d, text = %s", line, msg );
 *
 * - Message text can be added to the exception after it's been instantiated.  This allows
 *   code to catch an exception, add more information, and rethrow it.  See appendMessage()
 *   and prependMessage().
 *
 * - The logic is smart enough to ignore duplicate messages.
 *
 * - Checked exceptions can be easily wrapped by ZeidonException using wrapException().  This
 *   will keep the call stack the same as the wrapped exception.
 *
 * @author DG
 *
 */
public class ZeidonException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public interface ExceptionHandler
    {
        void handleException( ZeidonException e );
    }

    /**
     * If set, this runnable is called every time a Zeidon Exception is created.
     */
    private static volatile ExceptionHandler exceptionHandler = null;

    private final String       message;
    private final List<String> additionalInfo = new ArrayList<String>();

    public ZeidonException( String format, Object...strings )
    {
        super(format( format, strings ));
        this.message = getClass().getName() + ": " + format( format, strings );
        runHandler();
    }

    /**
     * This is private because we expect outside code to wrap exceptions using wrapException or addMessage.
     *
     * @param t
     * @param message
     */
    private ZeidonException( Throwable t, String message )
    {
        super(message, t);
        this.message = t.toString();
        runHandler();
    }

    @Override
    public String getMessage()
    {
        return toString();
    }

    private void runHandler()
    {
        if ( exceptionHandler != null )
            exceptionHandler.handleException( this );
    }

    public ZeidonException addMessage( int position, String msg )
    {
        if ( additionalInfo.contains( msg ) )
            return this;

        // Validate the position.  Since this only gets called in the middle of exception
        // handling we don't want to throw an exception so we'll just assume values and
        // keep going.
        if ( position < 0 )
            position = 0;
        else
        if ( position > additionalInfo.size() )
            position = additionalInfo.size();

        additionalInfo.add( position, msg );
        return this;
    }

    /**
     * Adds additional text information to the exception message.  New messages are inserted at
     * the beginning of the "additional info".
     *
     * @param format
     * @param strings
     */
    public ZeidonException prependMessage( String format, Object...strings )
    {
        return addMessage( 0, format( format, strings ) );
    }

    /**
     * Adds additional text information to the exception message.  New messages are inserted at
     * the end of the "additional info".
     *
     * @param format
     * @param strings
     */
    public ZeidonException appendMessage( String format, Object...strings )
    {
        return addMessage( additionalInfo.size(), format( format, strings ) );
    }

    @Override
    public String toString()
    {
        return message + "\n" + StringUtils.join( additionalInfo, "\n" );
    }

    /**
     * Returns a ZeidonException with additional information added to the message of exception 't'.
     *
     * If 't' is a ZeidonException then the text is added and returned.
     * Otherwise a new ZeidonException is created that wraps the original exception and
     * attempts to keep the same message/stack trace.
     *
     * @param t
     * @param format
     * @param args
     * @return
     */
    public static ZeidonException prependMessage( Throwable t, String format, Object...args )
    {
        ZeidonException ze;
        if ( t instanceof ZeidonException )
            ze = (ZeidonException) t;
        else
            ze = wrapException( t );

        ze.prependMessage( format, args );
        return ze;
    }

    /**
     * Wraps 't' with a ZeidonException and changes the call stack for the ZeidonException to
     * match 't'.  This is mostly used to re-throw checked exceptions as unchecked ones.
     *
     * @param t
     * @return
     */
    public static ZeidonException wrapException( Throwable t )
    {
        // If this is already a ZeidonException then we don't need to wrap it.
        if ( t instanceof ZeidonException )
            return (ZeidonException) t;

        ZeidonException ze = new ZeidonException( t, t.toString() );
        ze.setStackTrace( t.getStackTrace() );
        return ze;
    }

    /**
     * Wrap String.format() so that we don't throw an additional exception.
     *
     * @param format
     * @param objs
     * @return
     */
    private static String format( String format, Object...objs )
    {
        try
        {
            if ( objs == null || objs.length == 0 )
                return format;

            return String.format( format, objs );
        }
        catch ( Exception e )
        {
            return format + "(Formatting error: " + e.getMessage() + ")";
        }
    }

    //
    // Convenience methods for formatting standard messages.  This will keep nested code
    // from adding the same message twice.
    //

    public ZeidonException prependAttributeDef( AttributeDef attributeDef )
    {
        return prependMessage( "AttributeDef = %s", attributeDef.toString() );
    }

    public ZeidonException prependEntityDef( EntityDef entityDef )
    {
        return prependMessage( "EntityDef  = %s", entityDef.toString() );
    }

    public ZeidonException prependLodDef( LodDef lodDef )
    {
        return prependMessage( "LodDef  = %s", lodDef.toString() );
    }

    public ZeidonException prependEntityInstance( EntityInstance entityInstance )
    {
        return prependMessage( "EI  = %s", entityInstance.toString() );
    }

    public ZeidonException prependFilename( String filename )
    {
        return prependMessage( "Filename  = %s", filename );
    }

    public ZeidonException prependDataRecord( DataRecord dataRecord )
    {
        return prependMessage( "Table = %s", dataRecord.getRecordName() );
    }

    public ZeidonException setCause( Throwable t )
    {
        initCause( t );
        return this;
    }

    public static ExceptionHandler getExceptionHandler()
    {
        return exceptionHandler;
    }

    public static void setExceptionHandler( ExceptionHandler exceptionHandler )
    {
        ZeidonException.exceptionHandler = exceptionHandler;
    }
}