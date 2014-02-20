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

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.quinsoft.zeidon.View;

/**
 * @author DG
 *
 */
class ViewChooser extends JPanel implements ActionListener
{
    interface ViewSelected
    {
        void viewSelected( View view );
    }

    private static final long serialVersionUID = 1L;
    private static final String REFRESH  = "Refresh";
    private static final String WRITEOI  = "WriteOI";
    private static final String SAVE_ENV = "SaveEnv";

    private final BrowserEnvironment env;
    private final ViewList viewList;
    private final TaskList taskList;

    /**
     * @param objectEngine
     * @throws HeadlessException
     */
    ViewChooser(BrowserEnvironment environment, ViewSelected viewSelected )
    {
        super();
        this.env = environment;
        this.setName( "ViewChooser" );

        viewList = new ViewList( this.env, viewSelected );
        taskList = new TaskList( this.env, viewList );

        JScrollPane scrollableTasks = new JScrollPane( taskList );
        JScrollPane scrollableViews = new JScrollPane( viewList );

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                              scrollableTasks, scrollableViews);
        splitPane.setName( "ViewChooserSplitPane" );

        setLayout( new BorderLayout() );

        JPanel buttonPane = new JPanel();
        addButton( buttonPane, "Refresh", REFRESH );
        final JCheckBox showUnnamed = new JCheckBox( "Show Unnamed Views", env.isShowUnnamedViews() );
        showUnnamed.addItemListener( new ItemListener(){
            @Override
            public void itemStateChanged( ItemEvent e )
            {
                env.setShowUnnamedViews( showUnnamed.isSelected() );
                refresh();
            }} );
        buttonPane.add( showUnnamed );
        addButton( buttonPane, "Write OI", WRITEOI );
        addButton( buttonPane, "Save Window Settings", SAVE_ENV );

        add( splitPane, BorderLayout.CENTER );
        add( buttonPane, BorderLayout.SOUTH );
    }

    private void addButton( JPanel buttonPane, String title, String command )
    {
        JButton button = new JButton( title );
        button.setActionCommand( command );
        button.addActionListener( this );
        buttonPane.add( button );
    }
    void refresh()
    {
        taskList.refresh();
        viewList.refresh( taskList.getCurrentTask() );
    }

    private void writeOi()
    {
        View view = viewList.getSelectedView();
        if ( view == null )
        {
            JOptionPane.showMessageDialog( null, "No view selected", "alert",
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog( this );
        if ( returnVal == JFileChooser.APPROVE_OPTION )
        {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            view.writeOiToFile( filename, View.CONTROL_INCREMENTAL );
            env.getOe().getSystemTask().log().info( "OI written to %s", filename );
        }
    }

    @Override
    public void actionPerformed(ActionEvent action)
    {
        if ( action.getActionCommand().equals( REFRESH ) )
            refresh();
        else
        if ( action.getActionCommand().equals( WRITEOI ) )
            writeOi();
        else
        if ( action.getActionCommand().equals( SAVE_ENV ) )
            env.saveEnvironment();
    }
}
