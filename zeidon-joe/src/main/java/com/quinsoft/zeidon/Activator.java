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

import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * Defines the interface for an activator object that is used to activate OIs.  Intended to be
 * used by DB-handler creators.
 *
 */
public interface Activator
{
    View init(Task task, View initialView, ActivateOptions options );

    /**
     * Activate the OI.
     *
     * @return
     */
    View activate();

    /**
     * Activate a subobject with subobjectRootEntity as the root.  If subobjectRootootEntity = LodDef.root then
     * this activates the whole OI.
     *
     * @param subobjectRootEntity
     * @return
     */
    int activate( EntityDef subobjectRootEntity );
}
