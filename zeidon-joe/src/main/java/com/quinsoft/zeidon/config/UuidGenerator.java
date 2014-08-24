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
/**
 * 
 */
package com.quinsoft.zeidon.config;

import java.util.UUID;

/**
 * Interface for generating UUIDs in Zeidon to give every object a unique ID.
 * 
 * @author dg
 *
 */
public interface UuidGenerator
{
    /**
     * Generate a UUID.  This should be *fast*.
     * 
     * @return
     */
    UUID generate();
}
