/**
 *
 */
package com.quinsoft.zeidon.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import android.content.ContextWrapper;
import android.util.Log;

import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.TaskQualification;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * @author dgc
 *
 */
public class AndroidUtils
{
    /**
     * Checks to see if the targetDbName exists.  If not it will be copied from emptyDbName,
     * which must be in the assets directory.
     *
     * @param context
     * @param emptyDbName
     * @param targetDbName
     */
    public static void initializeDatabase( ContextWrapper context, String emptyDbName, String targetDbName )
    {
        context.getExternalFilesDir( null ); // Create files directory if necessary.
        File f = new File( targetDbName );
        if ( f.exists() ) // Does DB already exist?
            return;

        Log.i( "AndroidUtils.initializeDatabase", "Initializing DB by copying " + emptyDbName + " to "  + f.getAbsolutePath() );
        InputStream ins = null;
        FileOutputStream fos = null;
        try
        {
            ins = context.getAssets().open( emptyDbName );
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( emptyDbName );
        }

        try
        {
            fos = new FileOutputStream( f );
        }
        catch ( Exception e )
        {
            IOUtils.closeQuietly( ins );
            throw ZeidonException.wrapException( e ).prependFilename( targetDbName );
        }

        try
        {
            byte[] buffer = new byte[1024];
            int size = 0;

            while ( (size=ins.read( buffer,0,buffer.length ) ) >=0 )
                fos.write( buffer, 0, size );
        }
        catch ( IOException e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( emptyDbName );
        }
        finally
        {
            IOUtils.closeQuietly( ins );
            IOUtils.closeQuietly( fos );
        }
    }

    static ZeidonException appendViewInfo( Exception e, android.view.View androidView )
    {
        ZeidonException ze = ZeidonException.wrapException( e );
        ze.appendMessage( "Android View ID: %d", androidView.getId() );
        if ( androidView.getTag() != null )
            ze.appendMessage( "Tag: %s", androidView.getTag() );

        return ze;
    }

    /**
     * Activates an OI from an Android external file.
     *
     * @param task
     * @param filename
     * @return
     */
    public static View activateOiFromFile( TaskQualification task, String viewOdName, String filename )
    {
        return new DeserializeOi( task )
                        .fromFile( filename )
                        .setViewOd( viewOdName )
                        .activateFirst();
    }
}
