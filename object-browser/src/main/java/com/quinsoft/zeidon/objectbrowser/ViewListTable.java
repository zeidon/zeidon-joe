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

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
}