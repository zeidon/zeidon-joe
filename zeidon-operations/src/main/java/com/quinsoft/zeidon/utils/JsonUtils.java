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
package com.quinsoft.zeidon.utils;

import java.io.Writer;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * @author dgc
 *
 */
public class JsonUtils
{
    /**
     * Write the XOD for view as JSON to a writer.
     *
     * @param view
     * @param writer
     */
    public static void writeXodToJsonStream( View view, Writer writer )
    {
        // Determine the name of the XOD for view.
        LodDef lodDef = view.getLodDef();
        Application app = lodDef.getApplication();
        String filename = app.getObjectDir() + "/" + lodDef.getName() + ".XOD";

        // Load the XOD as a view.
        Application zeidonTools = view.getApplication( "ZeidonTools" );
        View xod = new DeserializeOi( view )
                            .fromFile( filename )
                            .setApplication( zeidonTools )
                            .activateFirst();

        // Write it.
        xod.serializeOi().asJson().toWriter( writer );
    }
}
