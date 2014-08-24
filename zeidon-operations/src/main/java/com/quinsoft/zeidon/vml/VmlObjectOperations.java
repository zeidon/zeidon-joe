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

package com.quinsoft.zeidon.vml;

import com.quinsoft.zeidon.View;


/**
 * This is a wrapper around a View that allows object operations to be added to a View.
 * Object operation code extends this class.
 *
 * Normally this would be an abstract class but by making it a normal class we'll catch
 * method-missing errors when we compile JOE.
 *
 * @author DG
 *
 */
public class VmlObjectOperations extends zVIEW
{
    public static final int zOCE_ACTIVATE        = 1; // ObjectConstraintType.ACTIVATE.getVmlValue();
    public static final int zOCE_ACTIVATE_EMPTY  = 2; // ObjectConstraintType.ACTIVATE_EMPTY.getVmlValue();
    public static final int zOCE_COMMIT          = 3; // ObjectConstraintType.COMMIT.getVmlValue();
    public static final int zOCE_DROPOI          = 4; // ObjectConstraintType.DROP_OI.getVmlValue();

    public static final int zECE_ACCEPT          = 1;
    public static final int zECE_CANCEL          = 2;
    public static final int zECE_CREATE          = 3;
    public static final int zECE_DELETE          = 4;
    public static final int zECE_EXCLUDE         = 5;
    public static final int zECE_INCLUDE         = 6;

    static private final Class<?>[] argumentTypes       = new Class<?>[] { View.class, Integer.class, Integer.class };

    public VmlObjectOperations( View view )
    {
        super( view );
    }
}
