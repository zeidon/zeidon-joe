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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectbrowser.ViewChooser.ViewSelected;

/**
 * @author DG
 *
 */
public class ViewList extends JTable
{
    private static final long serialVersionUID = 1L;
    private static String[] VIEWLISTCOLS = { "View ID", "OI ID", "Name", "OD Name" };
    private static String   UNNAMED_VIEW = "*** unnamed ***";
    
    private final BrowserEnvironment env;
    private final DefaultTableModel  model;
    private final ViewSelected       viewSelected;

    private List<View> currentViewList;
    
    /**
     * @param viewSelected 
     * @param objectEngine
     */
    ViewList(BrowserEnvironment env, ViewSelected selector )
    {
        super();
        this.env = env;
        this.viewSelected = selector;

        this.setName( "TaskList" );
        model = new DefaultTableModel();
        model.setColumnIdentifiers( VIEWLISTCOLS );
        setModel( model );

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int idx = getSelectedRow();
                View v = currentViewList.get( idx );
                viewSelected.viewSelected( v );
            }
        });

        refresh( null );
    }

    View getSelectedView()
    {
        if ( currentViewList.size() == 0 )
            return null;
        
        int idx = getSelectedRow();
        if ( idx < 0 )
            return null;
        
        return currentViewList.get( idx );
    }
    
    void refresh( Task task )
    {
        View selectedView = null;
        int idx = getSelectedRow();
        if ( idx >= 0 )
            selectedView = currentViewList.get( idx );

        while ( model.getRowCount() > 0 )
            model.removeRow( 0 );

        Object[] row = new Object[ VIEWLISTCOLS.length ];
        List<View> viewList = new ArrayList<View>();
        idx = -1;
        if ( task != null )
        {
            for ( View view : task.getViewList() )
            {
                Collection<String> nameList = view.getNameList();
                if ( nameList.size() == 0 )
                {
                    if ( ! env.isShowUnnamedViews() )
                        continue;

                    // We're going to add a name so create a temporary list. 
                    nameList = new ArrayList<String>();
                    nameList.add( UNNAMED_VIEW );
                }

                if ( view == selectedView )
                    idx = viewList.size();

                viewList.add( view );

                for ( String name : nameList )
                {
                    int col = 0;
                    row[col++] = view.getId();
                    row[col++] = view.getOiId();
                    row[col++] = name;
                    row[col++] = view.getViewOd().getName();
                    model.addRow( row );
                }
            }
        }
        
        currentViewList = viewList;

        if ( idx >= 0 )
            setRowSelectionInterval( idx, idx );
    }
}
