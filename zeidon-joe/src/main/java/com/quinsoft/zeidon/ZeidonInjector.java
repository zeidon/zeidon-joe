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

/**
 * A simple injector used by the OE for loading objects.  This is intended to be
 * used as a wrapper around other injectors (like Guice and Spring) so that the OE
 * can work with any of them.
 */
public interface ZeidonInjector
{
    /**
     * Retrieves the injected object associated with the class.
     */
    <T> T get( Class<T> key );

    /**
     * Retrieves the injected object associated with the class that has the specified name.
     */
    <T> T get( Class<T> key, String name );

    void initialize( ObjectEngine oe );
}
