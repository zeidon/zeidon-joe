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
package com.quinsoft.zeidon.zeidonoperations;

import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.vml.VmlOperation;

/**
 * This file belongs to the c-to-java conversion system.  These files convert functionality
 * defined in older .c files into Java.  They use non-Java naming conventions to hopefully
 * make it easier to generate the correct .java files using the VML generator.
 *
 * This is the list of Zeidon global operations that are defined in tzlodopr.h.
 *
 * @author DG
 *
 */
public class TZLODOPR extends VmlOperation
{

    public TZLODOPR(TaskQualification taskQual)
    {
       super( taskQual );
    }

    public TZLODOPR(Object...objects)
    {
       this( findTaskQual( objects ) );
    }

    public int LoadZeidonPPE( View v, int type, View meta, String title, String text )
    {
       // TODO: Implement full conversion.
       return 0;
    }
}
