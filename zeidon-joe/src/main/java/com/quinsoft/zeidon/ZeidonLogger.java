/**
 * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
 * 
 * Zeidon JOE is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Zeidon JOE is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Zeidon JOE. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2009-2014 QuinSoft
 */

package com.quinsoft.zeidon;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;

/**
 * Interface that mirrors Apache's log4j but uses String.format() to format log messages.
 * 
 * @author DG
 *
 */
public interface ZeidonLogger
{
    public abstract Level getLoggerLevel();
    public abstract void setLevel(Level level);
    public abstract void setLevel(String level);
    public abstract boolean isEnabledFor(Priority priority);
    public abstract boolean isInfoEnabled();
    public abstract boolean isDebugEnabled();
    public abstract boolean isTraceEnabled();
    public abstract boolean isWarnEnabled();
    public abstract void log(Priority priority, Object msg);
    public abstract void info(String format, Object... args);
    public abstract void info(String format, Throwable t, Object... args);
    public abstract void debug(String msg);
    public abstract void info(String msg);
    public abstract void warn(String msg);
    public abstract void trace(String msg);
    public abstract void error(String msg);
    public abstract void debug(String format, Object... args);
    public abstract void debug(String format, Throwable t, Object... args);
    public abstract void error(String format, Object... args);
    public abstract void error(String format, Throwable t, Object... args);
    public abstract void error(Throwable t);
    public abstract void fatal(String format, Object... args);
    public abstract void fatal(String format, Throwable t, Object... args);
    public abstract void warn(String format, Object... args);
    public abstract void warn(String format, Throwable t, Object... args);
    public abstract void trace(String format, Object... args);
    public abstract void trace(String format, Throwable t, Object... args);
    public abstract void log(Level level, String format, Object... args);
}