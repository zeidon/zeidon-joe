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

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;


/**
 * A wrapper around log4j Logger that supports the following:
 * 
 * - Messages use String.format() functionality.
 * - Multiple TaskLogger instances can be created for one log4j instance.  This
 *   allows each task to have its own logging level.
 *
 * The wrapper is necessary because log4j caches each logger created and we want a new
 * one for each task.  Without a wrapper we'll have a memory leak.
 * 
 * @author DG
 *
 */
public class TaskLogger extends Logger implements ZeidonLogger
{
    private static ThreadLocal<Formatter> formatterCache = new ThreadLocal<Formatter>() {
        @Override
        protected synchronized Formatter initialValue()
        {
            return new Formatter();
        }
    };
   
    private static Map<Logger,Level> originalLevel = new HashMap<Logger, Level>();
    
    private       Level  level;
    private final String prefix;
    private final Logger parentLogger;
    
    /**
     * @param name
     */
    public TaskLogger(String prefix)
    {
        super( Task.class.getName() );
        
        this.prefix = prefix;
        parentLogger = org.apache.log4j.Logger.getLogger( Task.class );
        
        // We want to set the level of the new TaskLogger to match parentLogger but then we
        // want to change the level of the parent logger to TRACE so that the parent logger
        // will write all messages.  Store the initial level of parentLogger in a map and
        // set it to TRACE.
        synchronized ( originalLevel )
        {
            level = originalLevel.get( parentLogger );
            if ( level == null )
            {
                level = parentLogger.getEffectiveLevel();
                originalLevel.put( parentLogger, level );
                parentLogger.setLevel( Level.TRACE );
            }
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#getLoggerLevel()
     */
    @Override
    public Level getLoggerLevel()
    {
        return level;
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#setLevel(org.apache.log4j.Level)
     */
    @Override
    public void setLevel( Level level)
    {
        this.level = level;
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#setLevel(java.lang.String)
     */
    @Override
    public void setLevel( String level)
    {
        setLevel( Level.toLevel( level ) );
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#isEnabledFor(org.apache.log4j.Priority)
     */
    @Override
    public boolean isEnabledFor( Priority priority )
    {
        return priority.isGreaterOrEqual( level );
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled()
    {
        return isEnabledFor( Level.INFO );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled()
    {
        return isEnabledFor( Level.DEBUG );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled()
    {
        return isEnabledFor( Level.TRACE );
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled()
    {
        return isEnabledFor( Level.WARN );
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#log(org.apache.log4j.Priority, java.lang.Object)
     */
    @Override
    public void log( Priority priority, Object msg )
    {
        parentLogger.log( priority, msg );
    }
    
    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(String format, Object... args)
    {
        if ( !isInfoEnabled() ) 
            return;

        parentLogger.info( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#info(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void info(String format, Throwable t, Object... args)
    {
        if ( !isInfoEnabled() ) 
            return;

        parentLogger.info( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg)
    {
        if ( ! isDebugEnabled() )
            return;
        
        parentLogger.debug( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#info(java.lang.String)
     */
    @Override
    public void info(String msg)
    {
        if ( ! isInfoEnabled() )
            return;
        
        parentLogger.info( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg)
    {
        if ( ! isWarnEnabled() )
            return;
        
        parentLogger.warn( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#trace(java.lang.String)
     */
    @Override
    public void trace(String msg)
    {
        if ( ! isTraceEnabled() )
            return;
        
        parentLogger.trace( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.String)
     */
    @Override
    public void error(String msg)
    {
        parentLogger.error( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(String format, Object... args)
    {
        if ( !isDebugEnabled() ) 
            return;

        parentLogger.debug( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#debug(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void debug(String format, Throwable t, Object... args)
    {
        if ( !isDebugEnabled() ) 
            return;

        parentLogger.debug( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(String format, Object... args)
    {
        if ( !isEnabledFor( Level.ERROR ) ) 
            return;

        parentLogger.error( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void error(String format, Throwable t, Object... args)
    {
        if ( !isEnabledFor( Level.ERROR ) ) 
            return;

        parentLogger.error( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#fatal(java.lang.String, java.lang.Object)
     */
    @Override
    public void fatal(String format, Object... args)
    {
        if ( !isEnabledFor( Level.FATAL ) ) 
            return;

        parentLogger.fatal( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#fatal(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void fatal(String format, Throwable t, Object... args)
    {
        if ( !isEnabledFor( Level.FATAL ) ) 
            return;

        parentLogger.fatal( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(String format, Object... args)
    {
        if ( !isEnabledFor( Level.WARN ) ) 
            return;

        parentLogger.warn( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#warn(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void warn(String format, Throwable t, Object... args)
    {
        if ( !isEnabledFor( Level.WARN ) ) 
            return;

        parentLogger.warn( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(String format, Object... args)
    {
        if ( !isTraceEnabled() ) 
            return;

        parentLogger.trace( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#trace(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void trace(String format, Throwable t, Object... args)
    {
        if ( !isTraceEnabled() ) return;

        parentLogger.trace( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#log(org.apache.log4j.Level, java.lang.String, java.lang.Object)
     */
    @Override
    public void log( Level level, String format, Object...args )
    {
        if ( !isEnabledFor( level ) ) return;
        
        parentLogger.log( level, sprintf( format, args ) );
    }

    private String sprintf(String format, Object... args)
    {
        if ( args == null || args.length == 0 )
            return prefix + format;
        
        Formatter f = formatterCache.get();
        f.format( format, args );

        StringBuilder sb = (StringBuilder) f.out();
        sb.insert( 0, prefix );
        String message = sb.toString();

        // Reset the length of the string builder to 0.  This is necessary because the formatter
        // uses the same StringBuilder between formattings.
        sb.setLength( 0 );

        return message;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.Throwable)
     */
    @Override
    public void error(Throwable t)
    {
        parentLogger.error( t );
    }
}
