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

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * @author DG
 *
 */
class ViewChooser extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static final String REFRESH  = "Refresh";
    private static final String SAVE_ENV = "SaveEnv";

    private final BrowserEnvironment env;
    private final ViewListTable viewList;
    private final TaskList taskList;

    /**
     * @param objectEngine
     * @throws HeadlessException
     */
    ViewChooser(BrowserEnvironment environment )
    {
        super();
        this.env = environment;
        this.setName( "ViewChooser" );
        setLayout( new BorderLayout() );

        viewList = new ViewListTable( this.env );
        taskList = new TaskList( this.env );

        JScrollPane scrollableTasks = new JScrollPane( taskList );
        JScrollPane scrollableViews = new JScrollPane( viewList );

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollableTasks, scrollableViews );
        splitPane.setName( "ViewChooserSplitPane" );
        splitPane.setResizeWeight( 0.4 );

        env.addTopLevelKeyAction( this, "alt 1", new SelectView( 0 ) );
        env.addTopLevelKeyAction( this, "alt 2", new SelectView( 1 ) );
        env.addTopLevelKeyAction( this, "alt 3", new SelectView( 2 ) );
        env.addTopLevelKeyAction( this, "alt 4", new SelectView( 3 ) );
        env.addTopLevelKeyAction( this, "alt 5", new SelectView( 4 ) );
        env.addTopLevelKeyAction( this, "alt 6", new SelectView( 5 ) );
        env.addTopLevelKeyAction( this, "alt 7", new SelectView( 6 ) );
        env.addTopLevelKeyAction( this, "alt 8", new SelectView( 7 ) );
        env.addTopLevelKeyAction( this, "alt 9", new SelectView( 8 ) );
        env.addTopLevelKeyAction( this, "alt 0", new SelectView( 9 ) );

        JPanel buttonPane = new JPanel();
        JButton refresh = addButton( buttonPane, "Refresh", REFRESH, "Refresh the task and view lists." );
        refresh.setMnemonic( KeyEvent.VK_R );
        final JCheckBox showUnnamed = new JCheckBox( "Unnamed", env.isShowUnnamedViews() );
        showUnnamed.setToolTipText( "Controls whether unnamed views are to be displayed in the view list" );
        showUnnamed.addItemListener( new ItemListener(){
            @Override
            public void itemStateChanged( ItemEvent e )
            {
                env.setShowUnnamedViews( showUnnamed.isSelected() );
                refresh();
            }} );
        buttonPane.add( showUnnamed );
        addButton( buttonPane, "Save Settings", SAVE_ENV,
                   "<html>Save the window positions/sizes and display options.<br>The browser will be started with these settings.</html>" );

        add( splitPane, BorderLayout.CENTER );
        add( buttonPane, BorderLayout.SOUTH );
    }

    private JButton addButton( JPanel buttonPane, String title, String command, String tooltip )
    {
        JButton button = new JButton( title );
        button.setActionCommand( command );
        button.addActionListener( this );
        button.setToolTipText( tooltip );
        buttonPane.add( button );
        return button;
    }

    void refresh()
    {
        taskList.refresh();
        viewList.refresh( taskList.getCurrentTask() );
    }

    @Override
    public void actionPerformed(ActionEvent action)
    {
        if ( action.getActionCommand().equals( REFRESH ) )
            refresh();
        else
        if ( action.getActionCommand().equals( SAVE_ENV ) )
            env.saveEnvironment();
    }

    private class SelectView extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        private final int index;

        public SelectView( int index )
        {
            super();
            this.index = index;
        }

        @Override
        public void actionPerformed( ActionEvent e )
        {
            System.out.println( "SelectView " + index );
            if ( index >= viewList.getRowCount() )
                return;

            viewList.setRowSelectionInterval( index, index );
        }
    }
}
