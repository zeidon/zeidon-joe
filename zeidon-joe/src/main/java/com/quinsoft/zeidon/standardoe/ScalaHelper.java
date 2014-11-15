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
package com.quinsoft.zeidon.standardoe;

import com.quinsoft.zeidon.AttributeInstance;
import com.quinsoft.zeidon.EntityConstraintType;
import com.quinsoft.zeidon.ObjectConstraintType;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * This is used to call Scala operations (like constraint operations) from JOE.
 * The Zeidon Scala wrapper implements this interface for calling operations.
 *
 * @author dgc
 *
 */
public interface ScalaHelper
{
    void setClassLoader( ClassLoader classLoader );
    Integer executeObjectConstraint( View view, ObjectConstraintType constraintType );
    Integer executeEntityConstraint( View view, EntityDef entityDef, EntityConstraintType type );

    /**
     * This is called when the value for a derived attribute is requested.  The implementation
     * of this should set the value in the attributeInstance using setDerivedValue().
     *
     * @param attributeInstance
     * @return ignored
     */
    Integer calculateDerivedAttribute( AttributeInstance attributeInstance );
}
