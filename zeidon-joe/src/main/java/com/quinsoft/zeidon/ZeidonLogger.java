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
 * Copyright 2009-2015 QuinSoft
 */

package com.quinsoft.zeidon;

import org.slf4j.Logger;
import org.slf4j.event.Level;

/**
 * Extends SLF4J logger with some convenience methods. 
 *
 */
public interface ZeidonLogger extends Logger
{
    Level getLoggerLevel();

    void setLevel( Level level );

    void setLevel( String level );

    boolean isEnabledFor( Level level );

    void info(String format, Throwable t, Object... args);
    void debug(String format, Throwable t, Object... args);
    void error(String format, Throwable t, Object... args);
    void warn(String format, Throwable t, Object... args);
    void trace(String format, Throwable t, Object... args);
    void log( Level level, String format, Object...args );
    void error(Throwable t);
}
