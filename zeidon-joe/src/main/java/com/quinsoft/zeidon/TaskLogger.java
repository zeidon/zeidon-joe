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

import java.util.Formatter;

import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.slf4j.ext.LoggerWrapper;


/**
 * A wrapper around slf4j Logger that supports the following:
 *
 * - Messages use String.format() functionality.
 * - Multiple TaskLogger instances can be created for one logger instance.  This
 *   allows each task to have its own logging level.
 *
 * @author DG
 *
 */
public class TaskLogger extends LoggerWrapper implements ZeidonLogger
{
    private static ThreadLocal<Formatter> formatterCache = new ThreadLocal<Formatter>() {
        @Override
        protected synchronized Formatter initialValue()
        {
            return new Formatter();
        }
    };

    private       Level   level;
    private final Level   parentLoggerLevel;
    private final String  prefix;

    /**
     * If true then we've already logged a warning about setting the logger level.
     */
    private       boolean setWarning = false;

    /**
     * @param name
     */
    public TaskLogger(String prefix)
    {
        super( LoggerFactory.getLogger( Task.class ), Task.class.getName() );

        this.prefix = prefix;
        parentLoggerLevel = intializeLevel();
        level = parentLoggerLevel;
    }

    private Level intializeLevel()
    {
        if ( super.isTraceEnabled() )
            return Level.TRACE;

        if ( super.isDebugEnabled() )
            return Level.DEBUG;

        if ( super.isInfoEnabled() )
            return Level.INFO;

        if ( super.isWarnEnabled() )
            return Level.WARN;

        return Level.ERROR;
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
    public void setLevel( Level newLevel)
    {
        if ( ! setWarning && parentLoggerLevel.compareTo( newLevel ) < 0 )
        {
            super.warn( "Attempting to set logger level to a higher granularity than specified in logger config.  "
                    + "To fix, set log level in logger config to TRACE and set InitialLogLevel in zeidon.ini.  "
                    + "Logger level = {}, new logger level = {}, logger class = {}", parentLoggerLevel.toString(),
                    newLevel.toString(), this.logger.getClass().getSimpleName() );
            setWarning = true;
        }


        this.level = newLevel;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#setLevel(java.lang.String)
     */
    @Override
    public void setLevel( String level)
    {
        setLevel( Level.valueOf( level.toUpperCase() ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#isEnabledFor(org.apache.log4j.Priority)
     */
    @Override
    public boolean isEnabledFor( Level checkLevel )
    {
        return checkLevel.compareTo( this.level ) <= 0;
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
     * @see com.quinsoft.zeidon.ZeidonLogger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(String format, Object... args)
    {
        if ( isInfoEnabled() == false )
            return;

        super.info( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#info(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void info(String format, Throwable t, Object... args)
    {
        if ( isInfoEnabled() == false )
            return;

        super.info( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg)
    {
        if ( isDebugEnabled() == false )
            return;

        super.debug( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#info(java.lang.String)
     */
    @Override
    public void info(String msg)
    {
        if ( isInfoEnabled() == false )
            return;

        super.info( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg)
    {
        if ( isWarnEnabled() == false )
            return;

        super.warn( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#trace(java.lang.String)
     */
    @Override
    public void trace(String msg)
    {
        if ( isTraceEnabled() == false )
            return;

        super.trace( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.String)
     */
    @Override
    public void error(String msg)
    {
        if ( isErrorEnabled() == false )
            return;

        super.error( sprintf( msg ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(String format, Object... args)
    {
        if ( isDebugEnabled() == false )
            return;

        super.debug( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#debug(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void debug(String format, Throwable t, Object... args)
    {
        if ( isDebugEnabled() == false )
            return;

        super.debug( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(String format, Object... args)
    {
        if ( isErrorEnabled() == false )
            return;

        super.error( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#error(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void error(String format, Throwable t, Object... args)
    {
        if ( isErrorEnabled() == false )
            return;

        super.error( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(String format, Object... args)
    {
        if ( isWarnEnabled() == false )
            return;

        super.warn( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#warn(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void warn(String format, Throwable t, Object... args)
    {
        if ( isWarnEnabled() == false )
            return;

        super.warn( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(String format, Object... args)
    {
        if ( isTraceEnabled() == false )
            return;

        super.trace( sprintf( format, args ) );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#trace(java.lang.String, java.lang.Throwable, java.lang.Object)
     */
    @Override
    public void trace(String format, Throwable t, Object... args)
    {
        if ( isTraceEnabled() == false )
            return;

        super.trace( sprintf( format, args ), t );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.ZeidonLogger#log(org.apache.log4j.Level, java.lang.String, java.lang.Object)
     */
    @Override
    public void log( Level level, String format, Object...args )
    {
        switch ( level )
        {
            case DEBUG:
                debug( sprintf( format, args ) );
                break;

            case ERROR:
                error( sprintf( format, args ) );
                break;

            case INFO:
                info( sprintf( format, args ) );
                break;

            case TRACE:
                trace( sprintf( format, args ) );
                break;

            case WARN:
                warn( sprintf( format, args ) );
                break;
        }
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace( String format, Object arg )
    {
        if ( isTraceEnabled() == false )
            return;

        super.trace( sprintf( format, arg ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#trace(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace( String format, Object arg1, Object arg2 )
    {
        if ( isTraceEnabled() == false )
            return;

        super.trace( sprintf( format, arg1, arg2 ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace( String msg, Throwable t )
    {
        if ( isTraceEnabled() == false )
            return;

        super.trace( msg, t );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug( String format, Object arg )
    {
        if ( isDebugEnabled() == false )
            return;

        super.debug( sprintf( format, arg ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#debug(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug( String format, Object arg1, Object arg2 )
    {
        if ( isDebugEnabled() == false )
            return;

        super.debug( sprintf( format, arg1, arg2 ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug( String msg, Throwable t )
    {
        if ( isDebugEnabled() == false )
            return;

        super.debug( msg, t );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info( String format, Object arg )
    {
        if ( isInfoEnabled() == false )
            return;

        super.info( sprintf( format, arg ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#info(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void info( String format, Object arg1, Object arg2 )
    {
        if ( isInfoEnabled() == false )
            return;

        super.info( sprintf( format, arg1, arg2 ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info( String msg, Throwable t )
    {
        if ( isInfoEnabled() == false )
            return;

        super.info( msg, t );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn( String format, Object arg )
    {
        if ( isWarnEnabled() == false )
            return;

        super.warn( sprintf( format, arg ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#warn(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn( String format, Object arg1, Object arg2 )
    {
        if ( isWarnEnabled() == false )
            return;

        super.warn( sprintf( format, arg1, arg2 ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn( String msg, Throwable t )
    {
        if ( isWarnEnabled() == false )
            return;

        super.warn( msg, t );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error( String format, Object arg )
    {
        if ( isErrorEnabled() == false )
            return;

        super.error( sprintf( format, arg ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#error(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void error( String format, Object arg1, Object arg2 )
    {
        if ( isErrorEnabled() == false )
            return;

        super.error( sprintf( format, arg1, arg2 ) );
    }

    /* (non-Javadoc)
     * @see org.slf4j.ext.LoggerWrapper#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error( String msg, Throwable t )
    {
        if ( isErrorEnabled() == false )
            return;

        super.error( msg, t );
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
        if ( isErrorEnabled() == false )
            return;

        error( "", t );
    }
}
