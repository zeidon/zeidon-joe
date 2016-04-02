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
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.SelectSet;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.AttributeDef;
import com.quinsoft.zeidon.objectdefinition.EntityDef;

/**
 * @author DGC
 *
 */
class TwinInstancesPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static String[] COLS = { "#", "Key", "Attributes" };

    private final BrowserEnvironment env;
    private final JTable table;
    private final DefaultTableModel model;
    private final List<EntityInstance> instanceList = new ArrayList<>();

    TwinInstancesPanel( BrowserEnvironment environment )
    {
        super( new BorderLayout() );
        env = environment;
        setName("TwinDialog");

        DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        table = new JTable();
        table.setSelectionModel( selectionModel );
        table.setName( "TwinTableDialog" );
        model = new TwinTableModel();
        model.setColumnIdentifiers( COLS );
        table.setModel( model );
        table.setCellSelectionEnabled( false );
        table.setRowSelectionAllowed( true );

        table.addMouseListener( new MouseAdapter()
        {
            @Override
            public void mousePressed( MouseEvent me )
            {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint( p );
                if ( me.getClickCount() == 2 ) // Double click.
                {
                    System.out.println( "click count = " + me.getClickCount());
                    EntityInstance ei = instanceList.get( row );
                    EntityDef entityDef = ei.getEntityDef();
                    env.getOiDisplayPanel().oiDisplay.getView().cursor( entityDef ).setCursor( ei );
                    env.getOiDisplayPanel().entitySelected( ei.getEntityDef(), ei );
                    env.getOiDisplayPanel().repaint();
                }
            }
        } );

        JScrollPane scroll = new JScrollPane( table );
        add( scroll );
        env.setTwinInstancesPanel( this );
    }

    private String getAttributeString( EntityInstance ei )
    {
        StringBuilder sb = new StringBuilder();
        EntityDef entityDef = ei.getEntityDef();
        int count = 0;
        for ( AttributeDef attributeDef : entityDef.getAttributes() )
        {
            if ( attributeDef.isKey() )
                continue;

            if ( attributeDef.isHidden() )
                continue;

            try
            {
                sb.append( attributeDef.getName() )
                  .append( ": " )
                  .append( ei.getAttribute( attributeDef ).getString() )
                  .append( "; " );
    
                if ( ++count > 5 )
                    break;
            }
            catch ( Exception e )
            {
                sb.append( "*error*; " );
            }
        }
        
        return sb.toString();
    }

    /**
     * This is called when a new entity instance is selected in the OI view.
     *
     * @param entityInstance
     */
    void setEntityInstance( EntityInstance entityInstance )
    {
        // Remove the current rows.
        while ( model.getRowCount() > 0 )
            model.removeRow( 0 );

        instanceList.clear();

        if ( entityInstance == null )
            return;

        EntityInstance first = entityInstance;
        while ( first.getPrevTwin() != null )
            first = first.getPrevTwin();

        View view = env.getOiDisplayPanel().getDisplayedView();
        Set<Object> sets = view.getSelectSetNames();
        int setCount = Math.min( 3, sets.size() );

        Object[] row = new Object[COLS.length + setCount];
        model.setColumnCount( row.length );
        Vector<String> headers = new Vector<>();
        headers.addAll( Arrays.asList( COLS ) );
        for ( Object setName : sets )
            headers.add( setName.toString() );
        model.setColumnIdentifiers( headers );

        int idx = -1;
        for ( EntityInstance ei = first; ei != null; ei = ei.getNextTwin() )
        {
            if ( ei == entityInstance )
                idx = model.getRowCount();

            instanceList.add( ei );

            int col = 0;
            row[col++] = model.getRowCount() + 1;
            row[col++] = EntitySquare.getKeyString( ei, ei.getEntityDef(), env );
            row[col++] = getAttributeString( ei );

            for ( Object setName : sets )
            {
                SelectSet set = view.getSelectSet( setName );
                row[col++] = set.isSelected( ei ); //new SelectSetCheckBox( setName, ei, set );
            }

            model.addRow( row );
        }

        if ( idx >= 0 )
            table.setRowSelectionInterval( idx, idx );
    }

    class TwinTableModel extends DefaultTableModel
    {
        private static final long serialVersionUID = 1L;

        @Override
        public Class<?> getColumnClass( int columnIndex )
        {
            return getValueAt( 0, columnIndex ).getClass();
        }

        @Override
        public boolean isCellEditable( int row, int column )
        {
            return false;
        }
    }
}
