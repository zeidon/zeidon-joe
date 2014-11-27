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

import java.util.ArrayList;
import java.util.List;

import com.quinsoft.zeidon.CommitOptions;
import com.quinsoft.zeidon.Committer;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;

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
        view.serializeOi().setFormat( fileDbUtils.getStreamFormat() )
                          .withIncremental()
                          .toFile( filename );
    }
}
