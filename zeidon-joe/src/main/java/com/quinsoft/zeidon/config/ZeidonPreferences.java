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
package com.quinsoft.zeidon.config;

/**
 * Defines the interface for retrieving Zeidon preferences.
 *
 * @author dgc
 *
 */
public interface ZeidonPreferences
{
    public String get( String groupName, String key, String defaultValue );
    public ZeidonPreferences set( String groupName, String key, String value );
    public void reload();

    /**
     * Returns a string representation of all the property values.  Intended to
     * be used by the JMX interface for viewing all the values.
     *
     * @return
     */
    public String getAllProperties();

    /**
     * Returns a description of where the ZeidonPreferences are stored.
     *
     * @return
     */
    public String getSourceDescription();
}
