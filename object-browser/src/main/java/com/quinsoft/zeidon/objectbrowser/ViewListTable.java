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

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.View;

/**
 * @author DG
 *
 */
public class ViewListTable extends JTable
{
    private static final long serialVersionUID = 1L;
    private static String[] VIEWLISTCOLS = { "View ID", "OI ID", "Name", "OD Name" };

    private final BrowserEnvironment env;
    private final DefaultTableModel  model;

    /**
     * @param viewSelected
     * @param objectEngine
     */
    ViewListTable( final BrowserEnvironment env )
    {
        super();
        this.env = env;

        this.setName( "TaskList" );
        model = new DefaultTableModel();
        model.setColumnIdentifiers( VIEWLISTCOLS );
        setModel( model );

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int idx = getSelectedRow();
                final BrowserView v = env.getCurrentViewList().get( idx );
                env.viewSelected( v );
            }

            @Override
            public void mousePressed( MouseEvent evt )
            {
                if ( evt.isPopupTrigger() )
                {
                    int idx = getSelectedRow();
                    final BrowserView v = env.getCurrentViewList().get( idx );
                    ViewPopupMenu menu = new ViewPopupMenu( v );
                    menu.show( evt.getComponent(), evt.getX(), evt.getY() );
                }
            }

            @Override
            public void mouseReleased( MouseEvent evt )
            {
                if ( evt.isPopupTrigger() )
                {
                    int idx = getSelectedRow();
                    final BrowserView v = env.getCurrentViewList().get( idx );
                    ViewPopupMenu menu = new ViewPopupMenu( v );
                    menu.show( evt.getComponent(), evt.getX(), evt.getY() );
                }
            }
        });

        env.setViewList( this );
        refresh( null );
    }

    BrowserView getSelectedView()
    {
        if ( env.getCurrentViewList().size() == 0 )
            return null;

        int idx = getSelectedRow();
        if ( idx < 0 )
            return null;

        return env.getCurrentViewList().get( idx );
    }

    void refresh( BrowserTask task )
    {
        Long selectedView = -1L;
        int idx = getSelectedRow();
        if ( idx >= 0 )
            selectedView = env.getCurrentViewList().get( idx ).viewId;

        while ( model.getRowCount() > 0 )
            model.removeRow( 0 );

        Object[] row = new Object[ VIEWLISTCOLS.length ];
        List<BrowserView> viewList = env.refreshBrowserViewList( task );
        idx = -1;
        int count = 0;
        for ( BrowserView view : viewList )
        {
            int col = 0;
            row[col++] = view.viewId;
            row[col++] = view.oiId;
            row[col++] = view.viewName;
            row[col++] = view.lodName;
            model.addRow( row );

            if ( view.viewId == selectedView )
                idx = count;

            count++;
        }

        if ( idx >= 0 )
            setRowSelectionInterval( idx, idx );
    }

    private class ViewPopupMenu extends JPopupMenu
    {
        private static final long serialVersionUID = 1L;

        public ViewPopupMenu(BrowserView v)
        {
            JMenuItem item = new JMenuItem( "Write to POR file" );
            item.addActionListener( new WriteViewMenuListener( v, StreamFormat.POR ) );
            add( item );

            item = new JMenuItem( "Write to JSON file" );
            item.addActionListener( new WriteViewMenuListener( v, StreamFormat.JSON ) );
            add( item );

            item = new JMenuItem( "Drop View Name" );
            item.addActionListener( new DropNameMenuListener( v ) );
            add( item );
            if ( v.viewName.equals( BrowserEnvironment.UNNAMED_VIEW ) )
                item.setEnabled( false );
        }
    }

    private class WriteViewMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        private StreamFormat format;
        private BrowserView view;

        public WriteViewMenuListener( BrowserView v, StreamFormat f )
        {
            format = f;
            view = v;
        }

        @Override
        public void actionPerformed( ActionEvent arg0 )
        {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog( ViewListTable.this );
            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                View v = env.getView( view );
                v.serializeOi().setFormat( format ).withIncremental().toFile( filename );
                env.getOe().getSystemTask().log().info( "OI written to %s", filename );
            }
        }
    }

    private class DropNameMenuListener extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        private BrowserView view;

        public DropNameMenuListener( BrowserView v )
        {
            view = v;
        }

        @Override
        public void actionPerformed( ActionEvent e )
        {
            env.dropViewName( view );
            refresh( view.task );
        }
    }
}