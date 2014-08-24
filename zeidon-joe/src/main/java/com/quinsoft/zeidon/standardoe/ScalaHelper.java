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

import com.quinsoft.zeidon.ObjectConstraintType;
import com.quinsoft.zeidon.View;

/**
 * This is used to call Scala operations (like constraint operations) from JOE.
 * The Zeidon Scala wrapper implements this interface for calling operations.
 *
 * @author dgc
 *
 */
public interface ScalaHelper
{
    Integer executeObjectConstraint( View view, ObjectConstraintType constraintType, ClassLoader loader );
}
