/**
 *
 */
package com.quinsoft.zeidon.utils;

import java.io.InputStream;
import java.io.Writer;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

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
        ViewOd viewOd = view.getViewOd();
        Application app = viewOd.getApplication();
        String filename = app.getObjectDir() + "/" + viewOd.getName() + ".XOD";

        // Load the XOD as a view.
        Application zeidonTools = view.getApplication( "ZeidonTools" );
        InputStream stream = null;
        try
        {
            stream = JoeUtils.getInputStream( filename );
            View xod = view.activateOiFromStream( "TZZOXODO", zeidonTools, stream, null );

            // Write it.
            xod.writeOiAsJson( writer );
        }
        finally
        {
            IOUtils.closeQuietly( stream );
        }
    }
}
