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

    Copyright 2009-2012 QuinSoft
 */

package com.quinsoft.zeidon.objectbrowser;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.Task;

/**
 * @author DG
 *
 */
class TaskList extends JTable
{
    private static final long serialVersionUID = 1L;
    private static String[] TASKLISTCOLS = { "Task ID", "Application Name" };

    private final BrowserEnvironment env;
    private final ViewList           viewList;
    
    private List<String> currentTaskList;
    private DefaultTableModel model;

    /**
     * @param env
     */
    TaskList(BrowserEnvironment env, ViewList vList )
    {
        super();
        this.setName( "TaskList" );
        this.env = env;
        viewList = vList;

        model = new DefaultTableModel();
        model.setColumnIdentifiers( TASKLISTCOLS );
        setModel( model );

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskListMouseClicked(evt);
            }
        });

        refresh();
    }
    
    void taskListMouseClicked( MouseEvent evt )
    {
        int idx = getSelectedRow();
        Task task = getTaskByIdx( idx );
        viewList.refresh( task );
    }

    void refresh()
    {
        Task selectedTask = null;
        int idx = getSelectedRow();
        if ( idx >= 0 )
            selectedTask = getTaskByIdx( idx );
        
        while ( model.getRowCount() > 0 )
            model.removeRow( 0 );
        
        Object[] row = new Object[ TASKLISTCOLS.length ];

        List<String> taskList = new ArrayList<String>();
        idx = -1;
        for ( Task task : env.getOe().getTaskList() )
        {
            if ( ! task.isValid() )
                continue;  // This task was dropped.
            
            if ( task == selectedTask )
                idx = taskList.size();
            
            taskList.add( task.getTaskId() );
            row[0] = task.getTaskId();
            row[1] = task.getApplication().getName();
            model.addRow( row );
        }
        
        currentTaskList = taskList;
        
        if ( idx >= 0 )
            setRowSelectionInterval( idx, idx );
    }
    
    Task getTaskByIdx( int idx )
    {
        String id = currentTaskList.get( idx );
        return env.getOe().getTaskById( id );
    }

    Task getCurrentTask()
    {
        return getTaskByIdx( 1 );
    }
    
    @Override
    public String toString()
    {
        return currentTaskList.toString();
    }
}
