/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

/**
 * @author dgc
 *
 */
public class CommitOiToFileDb implements Committer
{
    private List<ViewImpl>       viewList;
    private Task                 task;
    private View                 genKeyObj;
    private CommitOptions        options;
    private FileDbUtils          fileDbUtils;

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Committer#init(com.quinsoft.zeidon.Task, java.util.List, com.quinsoft.zeidon.CommitOptions)
     */
    @Override
    public void init( Task task, List<? extends View> list, CommitOptions options )
    {
        this.task = task;
        this.viewList = new ArrayList<ViewImpl>();
        for ( View v : list )
            viewList.add( ((InternalView) v).getViewImpl() );
        this.options = options;
        fileDbUtils = new FileDbUtils( options );
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.Committer#commit()
     */
    @Override
    public List<? extends View> commit()
    {
        for ( View v : viewList )
            write( v );

        return null;
    }

    private void write( View view )
    {
        String filename = fileDbUtils.genKeyFilename( view );
        task.dblog().info( "Writing OI to %s", filename );
        Writer writer = null;
        try
        {
            switch ( fileDbUtils.getStreamFormat() )
            {
                case XML:
                    view.writeOiToXml( filename, 0 );
                    return;

                case JSON:
                    writer = new FileWriter( filename );
                    view.writeOiAsJson( writer );
                    return;

                default:
                    view.writeOiToFile( filename, 0 );
                    return;
            }
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).prependFilename( filename );
        }
        finally
        {
            if ( writer != null )
                IOUtils.closeQuietly( writer );
        }
    }
}
