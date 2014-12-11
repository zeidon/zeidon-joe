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

package com.quinsoft.zeidon.objectbrowser;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author DG
 *
 */
public class TaskList extends JTable
{
    private static final long serialVersionUID = 1L;
    private static String[] TASKLISTCOLS = { "Task ID", "Application Name" };
    private static TaskListComparator TASK_COMPARATOR = new TaskListComparator();

    private final BrowserEnvironment env;

    private List<BrowserTask> currentTaskList;
    private DefaultTableModel model;

    /**
     * @param env
     */
    TaskList(BrowserEnvironment env )
    {
        super();
        this.setName( "TaskList" );
        this.env = env;

        model = new DefaultTableModel();
        model.setColumnIdentifiers( TASKLISTCOLS );
        model.setRowCount( 5 );
        setModel( model );

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskListMouseClicked(evt);
            }
        });

        env.setTaskList( this );
        refresh();
    }

    void taskListMouseClicked( MouseEvent evt )
    {
        int idx = getSelectedRow();
        BrowserTask task = getTaskByIdx( idx );
        env.getViewList().refresh( task );
    }

    public void refresh()
    {
        String selectedTask = "";
        int idx = getSelectedRow();
        if ( idx >= 0 )
            selectedTask = getTaskByIdx( idx ).taskId;

        while ( model.getRowCount() > 0 )
            model.removeRow( 0 );

        Object[] row = new Object[ TASKLISTCOLS.length ];
        env.refreshBrowserTaskList();

        List<BrowserTask> taskList = new ArrayList<>( env.getCurrentTaskList().values() );
        Collections.sort( taskList, TASK_COMPARATOR );
        idx = -1;
        for ( BrowserTask task : taskList )
        {
            if ( task.taskId.equals( selectedTask ) )
                idx = model.getRowCount();

            row[0] = task.taskId;
            row[1] = task.applicationName;
            model.addRow( row );
        }

        currentTaskList = taskList;
        
        if ( idx >= 0 )
            setRowSelectionInterval( idx, idx );
    }

    BrowserTask getTaskByIdx( int idx )
    {
        String id = currentTaskList.get( idx ).taskId;
        return env.getTaskById( id );
    }

    BrowserTask getCurrentTask()
    {
        if ( model.getRowCount() == 0 )
            return null;
        
        int idx = getSelectedRow();
        if ( idx == -1 ) // This means no tasks are selected
            idx = 0;     // Select task 0.  There should always be a system task.
        
        return getTaskByIdx( idx );
    }

    @Override
    public String toString()
    {
        return currentTaskList.toString();
    }
    
    private static class TaskListComparator implements Comparator<BrowserTask>
    {
        @Override
        public int compare( BrowserTask a, BrowserTask b )
        {
            // We'll try comparing the task IDs as integers.  If that fails we'll
            // compare them as strings.
            try
            {
                long id1 = Long.parseLong( a.taskId );
                long id2 = Long.parseLong( b.taskId );
                return Long.compare( id1, id2 ) * -1; // We'll reverse the value to put newest tasks first.
            }
            catch( NumberFormatException e )
            {
                // One of the IDs is not a number so just compare them as strings.
                return a.taskId.compareTo( b.taskId );
            }
        }
        
    }
}
