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

import javax.swing.DefaultListSelectionModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.objectdefinition.ViewAttribute;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;

/**
 * @author DGC
 *
 */
class TwinDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static String[] COLS = { "#", "Key", "Attributes" };

    private final BrowserEnvironment env;
    private final JTable table;
    private final DefaultTableModel model;

    TwinDialog( BrowserEnvironment environment, JFrame parentFrame )
    {
        super( );
        env = environment;
        setName("TwinDialog");
        setTitle( "Twin Dialog -- no entity selected" );
        setSize( 200, 400 );

        DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        
        table = new JTable();
        table.setSelectionModel( selectionModel );
        table.setName( "TwinTableDialog" );
        model = new DefaultTableModel();
        model.setColumnIdentifiers( COLS );
        table.setModel( model );
        table.setCellSelectionEnabled( false );
        table.setRowSelectionAllowed( true );
        
        JScrollPane scroll = new JScrollPane( table );
        add( scroll );
    }
    
    private String getAttributeString( EntityInstance ei )
    {
        StringBuilder sb = new StringBuilder();
        ViewEntity viewEntity = ei.getViewEntity();
        int count = 0;
        for ( ViewAttribute viewAttribute : viewEntity.getAttributes() )
        {
            if ( viewAttribute.isKey() )
                continue;
            
            if ( viewAttribute.isHidden() )
                continue;
            
            sb.append( viewAttribute.getName() )
              .append( ": " )
              .append( ei.getStringFromAttribute( viewAttribute ) )
              .append( "; " );
            
            if ( ++count > 5 )
                break;
        }
        return sb.toString();
    }
    
    void setEntityInstance( EntityInstance entityInstance )
    {
        while ( model.getRowCount() > 0 )
            model.removeRow( 0 );

        if ( entityInstance == null )
        {
            setTitle( "No entity selected" );
            return;
        }

        setTitle( "Twins of " + entityInstance.getViewEntity().getName() );

        EntityInstance first = entityInstance;
        while ( first.getPrevTwin() != null )
            first = first.getPrevTwin();
        
        Object[] row = new Object[COLS.length];

        int idx = -1;
        for ( EntityInstance ei = first; ei != null; ei = ei.getNextTwin() )
        {
            if ( ei == entityInstance )
                idx = model.getRowCount();
            
            int col = 0;
            row[col++] = model.getRowCount() + 1;
            row[col++] = EntitySquare.getKeyString( ei, ei.getViewEntity() );
            row[col++] = getAttributeString( ei );
            model.addRow( row );
        }

        if ( idx >= 0 )
            table.setRowSelectionInterval( idx, idx );
    }
}
