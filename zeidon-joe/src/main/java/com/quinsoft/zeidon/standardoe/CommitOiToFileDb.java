/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.util.ArrayList;
import java.util.List;

import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.SerializeOi;

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
        new SerializeOi().setFormat( fileDbUtils.getStreamFormat() )
                           .toFile( filename )
                           .withIncremental()
                           .write( view );
    }
}
