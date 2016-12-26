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

package com.quinsoft.zeidon.objectbrowser;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.Task;

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

            @Override
            public void mousePressed( MouseEvent evt )
            {
                if ( evt.isPopupTrigger() )
                {
                    int idx = getSelectedRow();
                    BrowserTask task = getTaskByIdx( idx );
                    TaskPopupMenu menu = new TaskPopupMenu( task, evt.getPoint() );
                    menu.show( evt.getComponent(), evt.getX(), evt.getY() );
                }
            }

            @Override
            public void mouseReleased( MouseEvent evt )
            {
                if ( evt.isPopupTrigger() )
                {
                    int idx = getSelectedRow();
                    BrowserTask task = getTaskByIdx( idx );
                    TaskPopupMenu menu = new TaskPopupMenu( task, evt.getPoint() );
                    menu.show( evt.getComponent(), evt.getX(), evt.getY() );
                }
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

    private class TaskPopupMenu extends JPopupMenu
    {
        private static final long serialVersionUID = 1L;

        public TaskPopupMenu(BrowserTask v, Point p )
        {

            JMenuItem item = new JMenuItem( "Log Level" );
            item.addActionListener( new SetLogLevelMenuListener( v, p ) );
            add( item );

        }
    }

    private class SetLogLevelAction extends AbstractAction
    {
        private static final long serialVersionUID = -3397528069416375971L;
        private final Task task;
        private final JFrame frame;
        private final ButtonGroup group;

        public SetLogLevelAction( Task t, JFrame frame, ButtonGroup group )
        {
            task = t;
            this.frame = frame;
            this.group = group;
        }

        @Override
        public void actionPerformed( ActionEvent e )
        {
            if ( e.getActionCommand().equals( "OK" ) )
            {
                task.log().setLevel( group.getSelection().getActionCommand() );
                task.dblog().setLevel( group.getSelection().getActionCommand() );
                frame.dispose();
                return;
            }

            task.log().setLevel( e.getActionCommand() );
            task.dblog().setLevel( e.getActionCommand() );
        }
    }

    private class SetLogLevelMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        private final BrowserTask task;
        private final Point location;

        public SetLogLevelMenuListener( BrowserTask t, Point p )
        {
            task = t;
            location = p;
        }

        @Override
        public void actionPerformed( ActionEvent e )
        {
            final JFrame frame = new JFrame("Set Logging Level");
            frame.setSize(200, 200);
            frame.setLocation( location );
            Container cont = frame.getContentPane();

            cont.setLayout(new GridLayout(0, 1));
            cont.add(new JLabel("Set log level:"));

            ButtonGroup group = new ButtonGroup();
            JRadioButton radioButton;

            Task t = env.getOe().getTaskById( task.taskId );
            SetLogLevelAction action = new SetLogLevelAction( t, frame, group );

            String currentLevel = t.log().getLoggerLevel().toString();

            radioButton = new JRadioButton( "Error" );
            radioButton.setMnemonic(KeyEvent.VK_E);
            radioButton.setActionCommand( "Error" );
            radioButton.setSelected( "Error".equalsIgnoreCase( currentLevel ) );
            radioButton.addActionListener( action );
            group.add( radioButton );
            cont.add( radioButton );

            radioButton = new JRadioButton( "Warn" );
            radioButton.setMnemonic(KeyEvent.VK_W);
            radioButton.setActionCommand( "Warn" );
            radioButton.setSelected( "Warn".equalsIgnoreCase( currentLevel ) );
            radioButton.addActionListener( action );
            group.add(  radioButton );
            cont.add( radioButton );

            radioButton = new JRadioButton( "Info" );
            radioButton.setMnemonic(KeyEvent.VK_I);
            radioButton.setActionCommand( "Info" );
            radioButton.setSelected( "Info".equalsIgnoreCase( currentLevel ) );
            radioButton.addActionListener( action );
            group.add(  radioButton );
            cont.add( radioButton );

            radioButton = new JRadioButton( "Debug" );
            radioButton.setMnemonic(KeyEvent.VK_D);
            radioButton.setActionCommand( "Debug" );
            radioButton.setSelected( "Debug".equalsIgnoreCase( currentLevel ) );
            radioButton.addActionListener( action );
            group.add(  radioButton );
            cont.add( radioButton );

            radioButton = new JRadioButton( "Trace" );
            radioButton.setMnemonic(KeyEvent.VK_T);
            radioButton.setActionCommand( "Trace" );
            radioButton.setSelected( "Trace".equalsIgnoreCase( currentLevel ) );
            radioButton.addActionListener( action );
            group.add(  radioButton );
            cont.add( radioButton );

            JButton button = new JButton( "OK" );
            button.setActionCommand( "OK" );
            button.setMnemonic(KeyEvent.VK_ENTER);
            button.addActionListener( action );
            cont.add( button );

            frame.getRootPane().setDefaultButton( button );
            frame.setVisible(true);
        }
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
